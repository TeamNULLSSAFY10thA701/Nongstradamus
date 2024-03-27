from functools import reduce

import numpy as np
import pandas as pd
import scipy
from arch import arch_model
from prophet import Prophet
from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import cross_val_score, KFold
from sqlalchemy import create_engine
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn import metrics
import warnings
import lightgbm as lgb
import arch

from statsmodels.tsa.arima.model import ARIMA
import arch

from scipy import stats
from scipy.stats import norm,skew

from patsy.highlevel import dmatrices
import statsmodels.api as sm
from sqlalchemy.dialects.mysql import pymysql
from statsmodels.stats.outliers_influence import variance_inflation_factor

from nongstradmus_database import connection
#
# # # import lightgbm as lgb
# # 가격 가져오기
# market_df = pd.read_excel("C:/Users/SSAFY/Downloads/price.xlsx",engine='openpyxl')
# # conn = pymysql.connect(host='localhost', port=3306, user='ssafy', password='ssafy', db='ssafy')
# engine = connection().get_engin()
# conn = engine.connect()
#
# query = "SELECT * from domesticOilPrice"
# # SQL에서 필요한 레코드만 가져옴
# oil_df = pd.read_sql(query, conn)
####################################################

# market_df = pd.read_excel("C:/Users/SSAFY/Downloads/price.xlsx",engine='openpyxl')

###conn = pymysql.connect(host='localhost', port=3306, user='ssafy', password='ssafy', db='ssafy')

### DB 연결 ###
engine = connection().get_engin()
conn = engine.connect()
# 국내 주유소 가격
query = "SELECT * from domesticOilPrice"
domestic_oil = pd.read_sql(query, conn)
# 무역수지
query = "SELECT * from trade where productId=1"
trade = pd.read_sql(query, conn)
# 날씨
query = "SELECT * from weather where productId=1 and code=41"
weather = pd.read_sql(query, conn)
# 농산물 가격
productId = 2
grade = 3

query = "SELECT date, price from priceHistory where productId={} and grade={}".format(productId, grade)
price_history = pd.read_sql(query, conn)
# 국제 유가
query = "SELECT * from globalOilPrice"
global_oil = pd.read_sql(query, conn)


### 필요없는 ID 제거 ###
domestic_oil.drop(['domesticOilPriceId'], axis=1, inplace=True)
global_oil.drop(['globalOilPriceId'], axis=1, inplace=True)
trade.drop(['tradeId'], axis=1, inplace=True)
weather.drop(['weatherId'], axis=1, inplace=True)
weather.drop(['productId'], axis=1, inplace=True)
weather.drop(['code'], axis=1, inplace=True)
# price_history.drop(['productId'], axis=1, inplace=True)
# price_history.drop(['priceHistoryId'], axis=1, inplace=True)
# price_history.drop(['grade'], axis=1, inplace=True)
# price_history.drop(['ratio'], axis=1, inplace=True)

domestic_oil['date'] = pd.to_datetime(domestic_oil['date'])
global_oil['date'] = pd.to_datetime(global_oil['date'])
trade['date'] = pd.to_datetime(trade['date'])
weather['date'] = pd.to_datetime(weather['date'])
price_history['date'] = pd.to_datetime(price_history['date'])

domestic_oil = domestic_oil.sort_values(by='date' ,ascending=True)
global_oil = global_oil.sort_values(by='date' ,ascending=True)
trade = trade.sort_values(by='date' ,ascending=True)
weather = weather.sort_values(by='date' ,ascending=True)
price_history = price_history.sort_values(by='date' ,ascending=True)

### 테이블 병합
dataframes = [price_history, domestic_oil, global_oil, trade, weather]
train = reduce(lambda left,right: pd.merge(left,right,how='left', on='date'), dataframes)
train = train.fillna(0)

start_day = '2024-03-16'
end_day = '2024-03-30'

test_date = pd.DataFrame({'date':pd.date_range(start=start_day, end=end_day)})
test = pd.concat([train,test_date]).reset_index(drop=True)
print(test)

# sns.lineplot(x=train['date'], y=train['price'], color='r', linestyle='--', marker='o')
all_data = pd.concat((train, test)).reset_index(drop=True)
ntrain = train.shape[0]
ntest = test.shape[0]

## prophet
def prophet_features(df):
    result_df = df[ntrain:]['date'].reset_index()

    for col in df:
        if col=='date': continue

        temp = df[[ 'date' , col ]].copy()
        temp.rename(columns={ 'date' : 'ds' , col : 'y' }, inplace=True)

        train, test = temp.iloc[:ntrain,:], temp.iloc[ntrain:,:]

        # Prophet 모델 정의
        m = Prophet()
        # Prophet 모델 훈련
        m.fit(train)

        # 특징 추출 데이터에서 Prophet을 사용하여 훈련 세트 예측
        predictions_train = m.predict(train.drop( 'y' , axis= 1 ))
        # Prophet을 사용하여 데이터에서 특징을 추출하여 테스트 세트 예측 예측
        predictions_test = m.predict( test.drop( 'y' , axis= 1 ))
        # 훈련 세트와 테스트 세트의 예측을 결합합니다.
        predictions = pd.concat((predictions_train, predictions_test), axis= 0 )

        res_y = predictions[ntrain:]['yhat'].reset_index().rename(columns={'yhat':col})
        result_df = pd.concat((result_df, res_y[col]), axis=1)

    result_df.drop('index', axis=1, inplace=True)
    return result_df


### 2

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


print(train.tail(5))

### 4
# 데이터 재설정
test = prophet_features(test)

all_data = pd.concat((train, test)).reset_index(drop=True)
all_data = pd.get_dummies(all_data)
res_date = all_data['date']  # 마지막에 lgb결과 나타낼 자료# 로 복사
all_data.drop('date', axis=1, inplace=True)

train = all_data[:ntrain]
test = all_data[ntrain:]



# x축 y축 들어갈 데이터 선택

# sns.lineplot(x=res_date, y=train['price'], color='r', linestyle='--', marker='o')
# plt.savefig("testImage.png")

y_train = train["price"]
# 전체 date만큼 그래프 그릴 수 있도록 이어붙임
all_data = (pd.concat((train, test)).reset_index(drop=True))


## 모델링 시작
pd.options.mode.chained_assignment = None
warnings.filterwarnings("ignore", category=DeprecationWarning)

# def rmsle(y, y_, convertExp = True):
#     if convertExp:
#         y= np.exp(y),
#         y_ = np.exp(y_)
#     log1 = np.nan_to_num(np.array([np.log(v+1) for v in y]))
#     log2 = np.nan_to_num(np.array([np.log(v+1) for v in y_]))
#     calc = (log1 - log2) ** 2
#     return np.sqrt(np.mean(calc))

def rmse(y, y_):
    MSE = np.square(np.subtract(y,y_)).mean()
    RMSE = np.sqrt(MSE)
    return RMSE

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
sns.lineplot(x=res_date[-ntest:], y=lgb_res[-ntest:], color='b', linestyle='--')
print(lgb_res)


# 예측



# 역변환 (예시: 예측 변수)



# 예측 범위 계산 (예시: 95% 신뢰 구간)
lgb_train_lower, lgb_train_upper = np.percentile(lgb_train_pred, [2.5, 97.5])
lgb_lower, lgb_upper = np.percentile(lgb_pred, [2.5, 97.5])


# Calculate standard deviation of predictions
lgb_train_std = np.std(lgb_train_pred)
lgb_std = np.std(lgb_pred)

# Define number of standard deviations for the band (e.g., 2)
num_std = 0.5

# Calculate upper and lower bounds
lgb_train_upper_band = lgb_train_pred + num_std * lgb_train_std
lgb_train_lower_band = lgb_train_pred - num_std * lgb_train_std
lgb_upper_band = lgb_pred + num_std * lgb_std
lgb_lower_band = lgb_pred - num_std * lgb_std

# Plot

# Fill between lines for upper and lower bands
# plt.fill_between(res_date[-365:], lgb_lower_band, lgb_upper_band, color='gray', label='Prediction Band')

plt.show()


grades = [grade] * ntest
productIds = [productId] * ntest
prices = lgb_pred

ratios = pd.DataFrame({'price': prices}).pct_change(1).mul(100)
ratios = ratios.price.to_list()
ratios[0] = ((lgb_pred[0] - train['price']) / train['price']) * 100

result = pd.DataFrame({"date": test_date,
                       "price": prices,
                       'ratio': ratios,
                       'grade': grades,
                       'productId': productIds})

result.to_sql(name='pricePredict', con=conn, index=False, if_exists='append')
