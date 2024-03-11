from flask import Flask
from apscheduler.schedulers.background import BackgroundScheduler
from database import db_connection
from sqlalchemy.orm import Session

app = Flask(__name__)

def calculate_price() :
  engin = db_connection().get_engin()
  session = Session(engin)


scheduler = BackgroundScheduler()
scheduler.add_job(calculate_price, 'interval', seconds=10)
scheduler.add_job(calculate_price, 'cron', hour=1)
scheduler.start()

if __name__ == "__main__":
  app.run()
