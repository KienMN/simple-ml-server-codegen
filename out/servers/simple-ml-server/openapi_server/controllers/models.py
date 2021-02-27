import connexion
import six

from openapi_server.models.error_response import ErrorResponse
from openapi_server.models.linear_regression_model import LinearRegressionModel
from openapi_server.models.metrics import Metrics
from openapi_server.models.samples import Samples
from openapi_server.models.success_response import SuccessResponse
from openapi_server.models.targets import Targets
from openapi_server.models.training_request_body import TrainingRequestBody
from openapi_server.models.validation_request_body import ValidationRequestBody
from openapi_server import utils


def create_model():
  """
  Create a model

  Parameters
  ----------
  
  
  Returns
  -------
  SuccessResponse
  """

  if connexion.request.is_json:
    print(connexion.request.get_json())
      #linear_regression_model = LinearRegressionModel.from_dict(connexion.request.get_json())
  return 'do some magic!'


def list_models():
  """
  List all models

  Parameters
  ----------
  
  Returns
  -------
  List
  """

  if connexion.request.is_json:
    print(connexion.request.get_json())
  return 'do some magic!'


def model_predict(model_id, ):
  """
  Predict using a trained model

  Parameters
  ----------
  model_id
  
  
  
  Returns
  -------
  Targets
  """

  if connexion.request.is_json:
    print(connexion.request.get_json())
      #model_id = .from_dict(connexion.request.get_json())
      #samples = Samples.from_dict(connexion.request.get_json())
  return 'do some magic!'


def model_train(model_id, ):
  """
  Train a model

  Parameters
  ----------
  model_id
  
  
  
  Returns
  -------
  SuccessResponse
  """

  if connexion.request.is_json:
    print(connexion.request.get_json())
      #model_id = .from_dict(connexion.request.get_json())
      #training_request_body = TrainingRequestBody.from_dict(connexion.request.get_json())
  return 'do some magic!'


def model_validate(model_id, ):
  """
  Validate a model

  Parameters
  ----------
  model_id
  
  
  
  Returns
  -------
  Metrics
  """

  if connexion.request.is_json:
    print(connexion.request.get_json())
      #model_id = .from_dict(connexion.request.get_json())
      #validation_request_body = ValidationRequestBody.from_dict(connexion.request.get_json())
  return 'do some magic!'


def show_model_by_id(model_id):
  """
  Info for a specific model

  Parameters
  ----------
  model_id
  
  Returns
  -------
  LinearRegressionModel
  """

  if connexion.request.is_json:
    print(connexion.request.get_json())
      #model_id = .from_dict(connexion.request.get_json())
  return 'do some magic!'

