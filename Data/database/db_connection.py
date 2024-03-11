import config
from sqlalchemy import create_engine
class db_connection:
  def __init__(self):
    db = config.db
    self.url = f"mysql+mysqlconnector://{db['user']}:{db['password']}@{db['host']}:{db['port']}/{db['database']}?charset=utf-8"

  def get_engin(self):
    engin = create_engine(self.url, encoding='utf-8', max_overflow=0)
    return engin