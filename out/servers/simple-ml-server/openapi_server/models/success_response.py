from typing import List, Dict
from openapi_server import utils

class SuccessResponse():
  def __init__(self, code=None, message=None):
    """
    SuccessResponse - a model defined in OpenAPI

    code : int
    
    message : str
    
    """

    self.openapi_types = {
      'code': int,
      'message': str
    }

    self.attribute_map = {
      'code': 'code',
      'message': 'message'
    }

    self._SuccessResponse = SuccessResponse

  @classmethod
  def from_dict(cls, dikt):
    return util.deserialize_model(dikt, cls)


