from functools import reduce

import numpy as np
import pandas as pd
from prophet import Prophet
import matplotlib.pyplot as plt
import seaborn as sns
import warnings
import lightgbm as lgb
from sqlalchemy.orm import Session

from nongstradmus_database import connection, price_history_get_stmt


# RMSE 계산
def rmse(y, y_):
    MSE = np.square(np.subtract(y,y_)).mean()
    RMSE = np.sqrt(MSE)
    return RMSE

## prophet으로 각 변수에 대해 미리 예측
def prophet_features(df, n_train):
    result_df = df[n_train:]['date'].reset_index()

    for col in df:
        if col=='date': continue

        temp = df[[ 'date' , col ]].copy()
        temp.rename(columns={ 'date' : 'ds' , col : 'y' }, inplace=True)

        train, test = temp.iloc[:n_train,:], temp.iloc[n_train:,:]

        # Prophet 모델 정의
        m = Prophet()
        # Prophet 모델 훈련
        m.fit(train)

        # 특징 추출 데이터에서 Prophet을 사용하여 훈련 세트 예측
        predictions_train = m.predict(train.drop( 'y' , axis= 1 ))
        # Prophet을 사용하여 데이터에서 특징을 추출하여 테스트 세트 예측 예측
        predictions_test = m.predict(test.drop( 'y' , axis= 1 ))
        # 훈련 세트와 테스트 세트의 예측을 결합합니다.
        predictions = pd.concat((predictions_train, predictions_test), axis= 0 )

        res_y = predictions[n_train:]['yhat'].reset_index().rename(columns={'yhat':col})
        result_df = pd.concat((result_df, res_y[col]), axis=1)

    result_df.drop('index', axis=1, inplace=True)
    return result_df

def do_process(conn, product, grade, start_date, end_date,
               domestic_oil, global_oil, price_history, wholesale_market, trade, weather):

    # 필요없는 ID 제거
    domestic_oil.drop(['domesticOilPriceId'], axis=1, inplace=True)
    global_oil.drop(['globalOilPriceId'], axis=1, inplace=True)
    trade.drop(['tradeId'], axis=1, inplace=True)
    weather.drop(['weatherId'], axis=1, inplace=True)
    weather.drop(['productId'], axis=1, inplace=True)
    weather.drop(['code'], axis=1, inplace=True)

    # date형식으로 변경
    domestic_oil['date'] = pd.to_datetime(domestic_oil['date'])
    global_oil['date'] = pd.to_datetime(global_oil['date'])
    trade['date'] = pd.to_datetime(trade['date'])
    weather['date'] = pd.to_datetime(weather['date'])
    price_history['date'] = pd.to_datetime(price_history['date'])

    # 날짜 순으로 정렬
    domestic_oil = domestic_oil.sort_values(by='date' ,ascending=True)
    global_oil = global_oil.sort_values(by='date' ,ascending=True)
    trade = trade.sort_values(by='date' ,ascending=True)
    weather = weather.sort_values(by='date' ,ascending=True)
    price_history = price_history.sort_values(by='date' ,ascending=True)

    ### 테이블 병합
    dataframes = [price_history, domestic_oil, global_oil, trade, weather]
    train = reduce(lambda left,right: pd.merge(left,right,how='left', on='date'), dataframes)
    train = train.fillna(0)

    test_dates = pd.DataFrame({'date':pd.date_range(start=start_date, end=end_date)})
    test = pd.concat([train,test_dates]).reset_index()

    # sns.lineplot(x=train['date'], y=train['price'], color='r', linestyle='--', marker='o')
    all_data = pd.concat((train, test)).reset_index(drop=True)
    n_train = train.shape[0]

    ################### 통계 검증용 코드 ###################
    # ## skewness 계산
    # numeric_features = all_data.dtypes[all_data.dtypes != "datetime64[ns]"].index
    #
    # skewed_feats = all_data[numeric_features].apply(lambda x: skew(x.dropna())).sort_values(ascending=False, axis=0)
    # skewness = pd.DataFrame({'Skew': skewed_feats})
    # # skewness = skewness[abs(skewness) > 0.7]
    # print(skewness)
    #
    # ## correlation 계산
    # corr = train.corr(method='pearson').drop(['price']).sort_values('price', ascending=False)
    #
    # ## p-value & vif 계산
    # features = " gasolinePrice_x, kerosenePrice, dieselPrice_x, wtiPrice, brentPrice, gasolinePrice_y, dieselPrice_y, amount, volume, avgTemperature, maxTemperature, minTemperature, rain, wind, humidity, sunDuration"
    # y, X = dmatrices("price ~" + features.replace(", ", "+"), data=train, return_type="dataframe")
    # vif = [variance_inflation_factor(X.values, i) for i in range(X.shape[1])]
    # result = sm.OLS(y,X).fit()
    # print(result.summary())
    #
    # # 다중공선성 체크 (10 이상 수치는 처리 : RMSE 확인)
    # vif = pd.DataFrame()
    # vif["VIF Factor"] = [variance_inflation_factor(X.values, i) for i in range(X.shape[1])]
    # vif["features"] = X.columns
    # print("VIF")
    # print(vif.round(1))
    #######################################################

    ### 4
    # 데이터 재설정
    test = prophet_features(test, n_train)

    all_data = pd.concat((train, test)).reset_index(drop=True)
    all_data = pd.get_dummies(all_data)
    res_date = all_data['date']  # 마지막에 lgb결과 나타낼 자료# 로 복사
    all_data.drop('date', axis=1, inplace=True)

    train = all_data[:n_train]
    test = all_data[n_train:]

    y_train = train["price"]
    # 전체 date만큼 그래프 그릴 수 있도록 이어붙임
    all_data = (pd.concat((train, test)).reset_index(drop=True))

    ## 모델링 시작
    pd.options.mode.chained_assignment = None
    warnings.filterwarnings("ignore", category=DeprecationWarning)

    model_lgb = lgb.LGBMRegressor(objective='regression',num_leaves=5,
                                  learning_rate=0.05, n_estimators=720,
                                  max_bin = 55, bagging_fraction = 0.8,
                                  bagging_freq = 5, feature_fraction = 0.2319,
                                  feature_fraction_seed=9, bagging_seed=9,
                                  min_data_in_leaf =6, min_sum_hessian_in_leaf = 11)

    model_lgb.fit(train, y_train)
    lgb_train_pred = model_lgb.predict(train)
    lgb_pred = model_lgb.predict(test.values)

    lgb_res = np.concatenate((lgb_train_pred,lgb_pred))
    # sns.lineplot(x=res_date[-365:-335], y=lgb_res[-365:-335], color='b', linestyle='--')

    # 예측 범위 계산 코드 (예시: 95% 신뢰 구간)
    lgb_train_lower, lgb_train_upper = np.percentile(lgb_train_pred, [2.5, 97.5])
    lgb_lower, lgb_upper = np.percentile(lgb_pred, [2.5, 97.5])


    # Calculate standard deviation of predictions
    lgb_train_std = np.std(lgb_train_pred)
    lgb_std = np.std(lgb_pred)

    # Define number of standard deviations for the band (e.g., 2)
    num_std = 2

    # Calculate upper and lower bounds
    lgb_train_upper_band = lgb_train_pred + num_std * lgb_train_std
    lgb_train_lower_band = lgb_train_pred - num_std * lgb_train_std
    lgb_upper_band = lgb_pred + num_std * lgb_std
    lgb_lower_band = lgb_pred - num_std * lgb_std

    # Plot

    # Fill between lines for upper and lower bands
    # plt.fill_between(res_date[-365:], lgb_lower_band, lgb_upper_band, color='gray', label='Prediction Band')


    # plt.show()

    # 예측 데이터 저장
    n_test = test.shape[0]
    grades = [grade] * n_test
    productIds = [product[0]] * n_test
    ratios = pd.DataFrame({'price': lgb_res}).pct_change(1).mul(100)[-n_test:]
    ratios = ratios.price.to_list()
    ratios[0] = 0

    result = pd.DataFrame({"date": test['date'],
                           "price": lgb_pred,
                           'ratio': ratios,
                           'grade': grades,
                           'productId': productIds})


    result.to_sql(name='pricePredict', con=conn, index=False, if_exists='append')

    return
