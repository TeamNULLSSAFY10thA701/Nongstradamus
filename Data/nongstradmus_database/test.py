import numpy as np
import pandas as pd
from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import cross_val_score, KFold
from sqlalchemy import create_engine
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn import metrics
import warnings
import lightgbm as lgb

from scipy import stats
from scipy.stats import norm,skew

from patsy.highlevel import dmatrices
import statsmodels.api as sm
from statsmodels.stats.outliers_influence import variance_inflation_factor


# # import lightgbm as lgb
# 가격 가져오기
market_df = pd.read_excel("C:/Users/SSAFY/Downloads/price.xlsx",engine='openpyxl')

# conn = pymysql.connect(host='localhost', port=3306, user='ssafy', password='ssafy', db='ssafy')
engine = create_engine(
    "mysql+pymysql://ssafy:ssafy@localhost:3306/ssafy"
)

conn = engine.connect()
query = "SELECT * from globaloilentity"
# SQL에서 필요한 레코드만 가져옴
oil_df = pd.read_sql(query, conn)
oil_df.drop(['globalOilPriceId'], axis=1, inplace=True)
oil_df = oil_df[838:993].reset_index(drop=True)
oil_df['date'] = pd.to_datetime(oil_df['date'])


market_df.drop(['시장', '단위', '등급', '품목'], axis=1, inplace=True) # 필요없는 컬럼 제거
# 데이터 형식 변환
market_df['date'] = pd.to_datetime(market_df['date'])
market_df['price'] = market_df['price'].replace([",", '-'], '', regex=True)
market_df['price'] = pd.to_numeric(market_df['price'])
# 예측할 데이터는 10행부터 시작하는 15일치
test_df = market_df[10:10+15].iloc[::-1].reset_index(drop=True)
market_df = market_df[10+15:145].iloc[::-1].reset_index(drop=True)


### 테이블 병합z
train = pd.merge(market_df, oil_df)
train = train.dropna(axis=0)
# test = pd.read_sql(query, conn)
test = pd.merge(test_df, oil_df, how='left')
# x축 y축 들어갈 데이터 선택
price = train['price']
date = train['date']

# sns.lineplot(x=date, y=price, color='r', linestyle='--', marker='o')
# plt.savefig("testImage.png")

ntrain = train.shape[0]  # 학습 횟수
ntest = test.shape[0]  # 예측 횟수

y_train = train["price"]
# 전체 date만큼 그래프 그릴 수 있도록 이어붙임
all_data = (pd.concat((train, test)).reset_index(drop=True))
# 전체 그래프에서 학습구간 나눔
train = all_data[:ntrain]
test = all_data[ntrain:]
res = all_data.copy()  # 마지막에 lgb결과 나타낼 자료로 복사

sns.lineplot(x=all_data['date'], y=all_data['price'], color='r', linestyle='--', marker='o')
# plt.savefig("originalImage.png")

### 2

## skewness 계산
numeric_features = all_data.dtypes[all_data.dtypes != "datetime64[ns]"].index

skewed_feats = all_data[numeric_features].apply(lambda x: skew(x.dropna())).sort_values(ascending=False, axis=0)
skewness = pd.DataFrame({'Skew': skewed_feats})
# skewness = skewness[abs(skewness) > 0.7]
print(skewness)

## correlation 계산
corr = train.corr(method='pearson').drop(['price']).sort_values('price', ascending=False)

## p-value & vif 계산
features = "brentPrice+dxlPrice+gasolinePrice+wtiPrice"
y, X = dmatrices("price ~" + features, data=train, return_type="dataframe")
vif = [variance_inflation_factor(X.values, i) for i in range(X.shape[1])]
result = sm.OLS(y,X).fit()
print(result.summary())

# 다중공선성 체크 (10 이상 수치는 처리 : RMSE 확인)
vif = pd.DataFrame()
vif["VIF Factor"] = [variance_inflation_factor(X.values, i) for i in range(X.shape[1])]
vif["features"] = X.columns
print("VIF")
print(vif.round(1))


### 4
# 데이터 재설정
all_data = pd.concat((train, test)).reset_index(drop=True)
all_data = pd.get_dummies(all_data)
all_data.drop('date', axis=1, inplace=True)

train = all_data[:ntrain]
test = all_data[ntrain:]


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
print(lgb_train_pred)
lgb_pred = model_lgb.predict(test.values)
print(lgb_pred)
lgb_res = np.concatenate((lgb_train_pred,lgb_pred))
print(lgb_res)
sns.lineplot(x=res['date'], y=lgb_res, color='b', linestyle='--', marker='o')
plt.savefig("resultImage.png")

# rmse 계산
print(rmse(y_train,lgb_train_pred))