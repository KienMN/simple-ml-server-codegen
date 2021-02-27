from typing import List, Dict
from openapi_server import utils

class Samples():
  def __init__(self, samples=None):
    """
    Samples - a model defined in OpenAPI

    samples : List
    
    """

    self.openapi_types = {
      'samples': List
    }

    self.attribute_map = {
      'samples': 'samples'
    }

    self._Samples = Samples

  @classmethod
  def from_dict(cls, dikt):
    return util.deserialize_model(dikt, cls)


