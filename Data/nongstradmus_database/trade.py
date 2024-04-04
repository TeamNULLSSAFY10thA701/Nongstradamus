from sqlalchemy import MetaData, Table, Column, Integer, String, Date, Double, select

trade = Table(
    "trade",
    MetaData(),
    Column("tradeId", Integer, nullable=False, primary_key=True),
    Column("balance", Integer, nullable=False),
    Column("amount", Integer, nullable=False),
    Column("volume", Integer, nullable=False),
    Column("date", Date, nullable=False),
    Column("productId", Integer, nullable=False)
)

def trade_get_stmt(product_id, start_date, end_date):
  return select(trade).where(trade.c.productId==product_id).where(trade.c.date.between(start_date, end_date)).order_by(trade.c.date)