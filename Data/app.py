import numpy as np
import pandas as pd
from flask import Flask
from apscheduler.schedulers.background import BackgroundScheduler
from sqlalchemy import delete, Table, MetaData, Column, Date, Integer, Double, between

from nongstradmus_database import connection, price_history_get_stmt, product_get_stmt, wholesale_market_get_stmt, \
    trade_get_stmt, domestic_oil_get_stmt, global_oil_get_stmt, weather_get_stmt, light_gbm
from sqlalchemy.orm import Session
import datetime
import pandas

from nongstradmus_database.price_predict import price_predict_get_stmt

app = Flask(__name__)


def calculate_price():
    engin = connection().get_engin()
    session = Session(engin)
    conn = engin.connect()
    products = session.execute(product_get_stmt()).fetchall()
    start_date = datetime.date.today() - datetime.timedelta(days=1825)
    end_date = datetime.date.today() - datetime.timedelta(days=1)
    domestic_oil = pandas.read_sql(domestic_oil_get_stmt(start_date, end_date), conn)
    global_oil = pandas.read_sql(global_oil_get_stmt(start_date, end_date), conn)

    # 필요없는 ID 제거
    domestic_oil.drop(['domesticOilPriceId'], axis=1, inplace=True)
    global_oil.drop(['globalOilPriceId'], axis=1, inplace=True)

    # date형식으로 변경
    domestic_oil['date'] = pd.to_datetime(domestic_oil['date'])
    global_oil['date'] = pd.to_datetime(global_oil['date'])

    # 날짜 순으로 정렬
    domestic_oil = domestic_oil.sort_values(by='date' ,ascending=True)
    global_oil = global_oil.sort_values(by='date' ,ascending=True)

    price_predict = Table('pricePredict', MetaData(),
                          Column("pricePredictId", Integer, nullable=False, primary_key=True),
                          Column("date", Date, nullable=False),
                          Column("price", Integer, nullable=False),
                          Column("ratio", Double, nullable=False),
                          Column("grade", Integer, nullable=False),
                          Column("productId", Integer, nullable=False)
                          )

    # Get today's date
    today = datetime.date.today()

    # Construct the delete query
    delete_query = delete(price_predict).where(price_predict.c.date <= today)

    # Execute the delete query using a session object
    session.execute(delete_query)
    session.commit()

    for product in products:
        trade = pandas.read_sql(trade_get_stmt(product[0], start_date, end_date), conn)
        weather = pandas.read_sql(weather_get_stmt(product[0], start_date, end_date), conn)

        trade.drop(['tradeId'], axis=1, inplace=True)
        weather.drop(['weatherId'], axis=1, inplace=True)
        weather.drop(['productId'], axis=1, inplace=True)
        weather.drop(['code'], axis=1, inplace=True)

        trade['date'] = pd.to_datetime(trade['date'])
        weather['date'] = pd.to_datetime(weather['date'])

        trade = trade.sort_values(by='date' ,ascending=True)
        weather = weather.sort_values(by='date' ,ascending=True)



        for grade in (1, 2, 3, 4):
            price_history = pandas.read_sql(price_history_get_stmt(product[0], grade, start_date, end_date), conn)
            wholesale_market = pandas.read_sql(wholesale_market_get_stmt(product[0], grade, start_date, end_date), conn)

            if not len(price_history):
                continue
            price_history.drop(['priceHistoryId'], axis=1, inplace=True)
            price_history['date'] = pd.to_datetime(price_history['date'])
            price_history = price_history.sort_values(by='date' ,ascending=True)

            # price 평균 및 표준편차 계산
            mean = np.mean(price_history['price'])
            std = np.std(price_history['price'])
            for i in range(1, len(price_history['price'])):
                z = (price_history.loc[i, 'price'] - mean)/std
                if z > 3: # z-score 3 이상이면 제거
                    print(price_history.loc[i, 'price'], "값을 제거합니다")
                    price_history.loc[i, 'price'] = np.nan

            # 결측치 보간
            price_history = price_history.interpolate()

            print(product, grade, "계산 시작")
            light_gbm.do_process(conn, product, grade, start_date, end_date,
                                 domestic_oil, global_oil, price_history, wholesale_market, trade, weather)

            print(product, grade, "계산 완료")

def sayHello():
    print("Hello!")

scheduler = BackgroundScheduler()
scheduler.add_job(calculate_price, 'cron', hour="04", minute="00")
scheduler.start()

if __name__ == "__main__":
    app.run()
