from sqlalchemy import MetaData, Table, Column, Integer, String, Date, Double, select

domestic_oil = Table(
    "domesticOilPrice",
    MetaData(),
    Column("domesticOilPriceId", Integer, nullable=False, primary_key=True),
    Column("gasolinePrice", Double, nullable=False),
    Column("kerosenePrice", Double, nullable=False),
    Column("dieselPrice", Double, nullable=False),
    Column("date", Date, nullable=False)
)

def domestic_oil_get_stmt(start_date, end_date):
  return select(domestic_oil).where(domestic_oil.c.date.between(start_date, end_date)).order_by(domestic_oil.c.date)