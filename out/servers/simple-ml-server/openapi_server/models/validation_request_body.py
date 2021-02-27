from typing import List, Dict
from openapi_server import utils

class ValidationRequestBody():
  def __init__(self, samples=None, targets=None):
    """
    ValidationRequestBody - a model defined in OpenAPI

    samples : List
    
    targets : List
    
    """

    self.openapi_types = {
      'samples': List,
      'targets': List
    }

    self.attribute_map = {
      'samples': 'samples',
      'targets': 'targets'
    }

    self._ValidationRequestBody = ValidationRequestBody

  @classmethod
  def from_dict(cls, dikt):
    return util.deserialize_model(dikt, cls)


