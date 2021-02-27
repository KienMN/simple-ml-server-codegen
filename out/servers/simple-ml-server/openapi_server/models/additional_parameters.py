from typing import List, Dict
from openapi_server import utils

class AdditionalParameters():
  def __init__(self, sample_weight=None):
    """
    AdditionalParameters - a model defined in OpenAPI

    sample_weight : List
    
    """

    self.openapi_types = {
      'sample_weight': List
    }

    self.attribute_map = {
      'sample_weight': 'sampleWeight'
    }

    self._AdditionalParameters = AdditionalParameters

  @classmethod
  def from_dict(cls, dikt):
    return util.deserialize_model(dikt, cls)


