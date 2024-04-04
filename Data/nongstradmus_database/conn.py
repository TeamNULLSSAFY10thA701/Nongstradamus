import config
from sqlalchemy import create_engine
class connection:
  def __init__(self):
    db = config.db
    self.url = f"mysql+mysqlconnector://{db['user']}:{db['password']}@{db['host']}:{db['port']}/{db['data_base']}"

  def get_engin(self):
    engin = create_engine(self.url, max_overflow=0)
    return engin