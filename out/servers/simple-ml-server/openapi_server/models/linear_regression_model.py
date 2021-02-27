from typing import List, Dict
from openapi_server import utils

class LinearRegressionModel():
  def __init__(self, model_id=None, fit_intercept=True, normalize=False):
    """
    LinearRegressionModel - a model defined in OpenAPI

    model_id : str
    
    fit_intercept : bool
    
    normalize : bool
    
    """

    self.openapi_types = {
      'model_id': str,
      'fit_intercept': bool,
      'normalize': bool
    }

    self.attribute_map = {
      'model_id': 'modelId',
      'fit_intercept': 'fitIntercept',
      'normalize': 'normalize'
    }

    self._LinearRegressionModel = LinearRegressionModel

  @classmethod
  def from_dict(cls, dikt):
    return util.deserialize_model(dikt, cls)


