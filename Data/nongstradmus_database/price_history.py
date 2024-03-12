from sqlalchemy import MetaData, Table, Column, Integer, String, Date, Double, select

price_history = Table(
    "priceHistory",
    MetaData(),
    Column("priceHistoryId", Integer(), primary_key=True, nullable=False),
    Column("date", Date, nullable=False),
    Column("price", Integer(), nullable=False),
    Column("ratio", Double, nullable=False),
    Column("grade", Integer, nullable=False),
    Column("productId", Integer(), nullable=False)
)

def price_history_get_stmt(product_id, start_date, end_date):
  return select(price_history).where(price_history.c.productId == product_id).where(price_history.c.date.between(start_date, end_date)).order_by(price_history.c.date)