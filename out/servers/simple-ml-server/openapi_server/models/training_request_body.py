from typing import List, Dict
from openapi_server import utils

class TrainingRequestBody():
  def __init__(self, samples=None, targets=None, sample_weight=None):
    """
    TrainingRequestBody - a model defined in OpenAPI

    samples : List
    
    targets : List
    
    sample_weight : List
    
    """

    self.openapi_types = {
      'samples': List,
      'targets': List,
      'sample_weight': List
    }

    self.attribute_map = {
      'samples': 'samples',
      'targets': 'targets',
      'sample_weight': 'sampleWeight'
    }

    self._TrainingRequestBody = TrainingRequestBody

  @classmethod
  def from_dict(cls, dikt):
    return util.deserialize_model(dikt, cls)


