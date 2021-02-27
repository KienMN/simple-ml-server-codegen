from typing import List, Dict
from openapi_server import utils

class Metrics():
  def __init__(self, mean_squared_error=None, mean_absolute_error=None, accuracy=None):
    """
    Metrics - a model defined in OpenAPI

    mean_squared_error : float
    
    mean_absolute_error : float
    
    accuracy : float
    
    """

    self.openapi_types = {
      'mean_squared_error': float,
      'mean_absolute_error': float,
      'accuracy': float
    }

    self.attribute_map = {
      'mean_squared_error': 'meanSquaredError',
      'mean_absolute_error': 'meanAbsoluteError',
      'accuracy': 'accuracy'
    }

    self._Metrics = Metrics

  @classmethod
  def from_dict(cls, dikt):
    return util.deserialize_model(dikt, cls)


