from sqlalchemy import MetaData, Table, Column, Integer, String, Date, Double, select

wholesale_market = Table(
    "wholesaleMarket",
    MetaData(),
    Column("wholesaleMarketId", Integer, nullable=False, primary_key=True),
    Column("date", Date, nullable=False),
    Column("price", Integer, nullable=False),
    Column("productId", Integer, nullable=False),
    Column("originId", Integer, nullable=False),
    Column("grade", Integer, nullable=False)
)

def wholesale_market_get_stmt(product_id, grade, start_date, end_date):
  return (select(wholesale_market).where(wholesale_market.c.productId == product_id)
          .where(wholesale_market.c.grade == grade)
          .where(wholesale_market.c.date.between(start_date, end_date))
          .order_by(wholesale_market.c.date))