from typing import List, Dict
from openapi_server import utils

class Targets():
  def __init__(self, targets=None):
    """
    Targets - a model defined in OpenAPI

    targets : List
    
    """

    self.openapi_types = {
      'targets': List
    }

    self.attribute_map = {
      'targets': 'targets'
    }

    self._Targets = Targets

  @classmethod
  def from_dict(cls, dikt):
    return util.deserialize_model(dikt, cls)


