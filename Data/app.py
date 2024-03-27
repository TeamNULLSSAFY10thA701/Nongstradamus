from flask import Flask
from apscheduler.schedulers.background import BackgroundScheduler
from nongstradmus_database import connection, price_history_get_stmt, product_get_stmt, wholesale_market_get_stmt, \
    trade_get_stmt, domestic_oil_get_stmt, global_oil_get_stmt, weather_get_stmt, light_gbm
from sqlalchemy.orm import Session
import datetime
import pandas

app = Flask(__name__)


def calculate_price():
    engin = connection().get_engin()
    session = Session(engin)
    conn = engin.connect()
    products = session.execute(product_get_stmt()).fetchall()
    start_date = datetime.date.today() - datetime.timedelta(days=1)
    end_date = datetime.date.today() - datetime.timedelta(days=1)
    domestic_oil = pandas.read_sql(domestic_oil_get_stmt(start_date, end_date), conn)
    global_oil = pandas.read_sql(global_oil_get_stmt(start_date, end_date), conn)
    for product in products:
        trade = pandas.read_sql(trade_get_stmt(product[0], start_date, end_date), conn)
        wheater = pandas.read_sql(weather_get_stmt(product[0], start_date, end_date), conn)

        for grade in (1, 2, 3, 4):
            price_history = pandas.read_sql(price_history_get_stmt(product[0], grade, start_date, end_date), conn)
            wholesale_market = pandas.read_sql(wholesale_market_get_stmt(product[0], grade, start_date, end_date), conn)

            if price_history or wholesale_market:
                continue

            light_gbm.do_process(conn, product, grade, start_date, end_date,
                                 domestic_oil, global_oil, price_history, wholesale_market, trade, wheater)


scheduler = BackgroundScheduler()
scheduler.add_job(calculate_price, 'interval', seconds=10)
scheduler.add_job(calculate_price, 'cron', hour=1)
scheduler.start()

if __name__ == "__main__":
    app.run()
