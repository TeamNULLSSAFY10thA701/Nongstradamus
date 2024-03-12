from flask import Flask
from apscheduler.schedulers.background import BackgroundScheduler
from nongstradmus_database import connection, price_history_get_stmt, product_get_stmt, wholesale_market_get_stmt, trade_get_stmt, domestic_oil_get_stmt, global_oil_get_stmt
from sqlalchemy.orm import Session
import datetime
import pandas

app = Flask(__name__)

def calculate_price() :
  engin = connection().get_engin()
  session = Session(engin)
  conn = engin.connect()
  products = session.execute(product_get_stmt()).fetchall()
  start_date = datetime.date.today() - datetime.timedelta(days=366)
  end_date = datetime.date.today() - datetime.timedelta(days=1)
  domestic_oil = pandas.read_sql(domestic_oil_get_stmt(start_date, end_date), conn)
  global_oil = pandas.read_sql(global_oil_get_stmt(start_date, end_date), conn)
  for product in products:
    price_history = pandas.read_sql(price_history_get_stmt(product[0], start_date, end_date), conn)
    wholesale_market = pandas.read_sql(wholesale_market_get_stmt(product[0], start_date, end_date), conn)
    trade = pandas.read_sql(trade_get_stmt(product[0], start_date, end_date), conn)



scheduler = BackgroundScheduler()
scheduler.add_job(calculate_price, 'interval', seconds=10)
scheduler.add_job(calculate_price, 'cron', hour=1)
scheduler.start()

if __name__ == "__main__":
  app.run()
