from sqlalchemy import MetaData, Table, Column, Integer, String, Date, Double, select

weather = Table(
    'weather',
    MetaData(),
    Column('weatherId', Integer, nullable=False, primary_key=True),
    Column('date', Date, nullable=False),
    Column('avgTemperature', Integer, nullable=False),
    Column('maxTemperature', Integer, nullable=False),
    Column('minTemperature', Integer, nullable=False),
    Column('rain', Integer, nullable=False),
    Column('wind', Integer, nullable=False),
    Column('humidity', Integer, nullable=False),
    Column('sunDuration', Integer, nullable=False),
    Column('code', String(2), nullable=False),
    Column('productId', Integer, nullable=False)
)

def weather_get_stmt(product_id, start_date, end_date):
  return select(weather).where(weather.c.productId==product_id).where(weather.c.date.between(start_date, end_date)).order_by(weather.c.date)