from typing import List, Dict
from openapi_server import utils

class LinearRegressionModelAllOf():
  def __init__(self, model_id=None):
    """
    LinearRegressionModelAllOf - a model defined in OpenAPI

    model_id : str
    
    """

    self.openapi_types = {
      'model_id': str
    }

    self.attribute_map = {
      'model_id': 'modelId'
    }

    self._LinearRegressionModel_allOf = LinearRegressionModel_allOf

  @classmethod
  def from_dict(cls, dikt):
    return util.deserialize_model(dikt, cls)


