from sqlalchemy import MetaData, Table, Column, Integer, String, Date, Double, select

product = Table(
    "product",
    MetaData(),
    Column("productId", Integer, nullable=False, primary_key=True),
  Column("productName", String(21), nullable=False),
  Column("productUnit", String(90))
)

def product_get_stmt():
  return select(product)
