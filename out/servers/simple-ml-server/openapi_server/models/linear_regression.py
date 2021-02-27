from typing import List, Dict
from openapi_server import utils

class LinearRegression():
  def __init__(self, fit_intercept=True, normalize=False):
    """
    LinearRegression - a model defined in OpenAPI

    fit_intercept : bool
    
    normalize : bool
    
    """

    self.openapi_types = {
      'fit_intercept': bool,
      'normalize': bool
    }

    self.attribute_map = {
      'fit_intercept': 'fitIntercept',
      'normalize': 'normalize'
    }

    self._LinearRegression = LinearRegression

  @classmethod
  def from_dict(cls, dikt):
    return util.deserialize_model(dikt, cls)


