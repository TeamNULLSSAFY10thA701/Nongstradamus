from sqlalchemy import MetaData, Table, Column, Integer, String, Date, Double, select

global_oil = Table(
    'globalOilPrice',
    MetaData(),
  Column('globalOilPriceId', Integer, nullable=False, primary_key=True),
    Column("wtiPrice", Double, nullable=False),
    Column("brentPrice", Double, nullable=False),
    Column("gasolinePrice", Double, nullable=False),
    Column('dieselPrice', Double, nullable=False),
    Column('date', Date, nullable=False)
)

def global_oil_get_stmt(start_date, end_date):
  return select(global_oil).where(global_oil.c.date.between(start_date, end_date)).order_by(global_oil.c.date)