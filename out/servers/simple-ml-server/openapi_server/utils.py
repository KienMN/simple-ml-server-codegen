import datetime

import six
from openapi_server import typing_utils

def _deserialize(data, klass):
  """Deserializes dict, list, str into an object.

  Parameters
  ----------
  data : dict, list or str
    The input data.

  klass : 
    Class literal, or string of class name.

  Returns
  -------
  object : object
    Value or object of the data
  """

  if data is None:
    return None

  if klass in six.integer_types or klass in (float, str, bool):
    return _deserialize_primitive(data, klass)
  elif klass == object:
    return _deserialize_object(data)
  elif typing_utils.is_generic(klass):
    if typing_utils.is_list(klass):
      return _deserialize_list(data, klass.__args__[0])
    if typing_utils.is_dict(klass):
      return _deserialize_dict(data, klass.__args__[1])
  else:
    return deserialize_model(data, klass)

def _deserialize_primitive(data, klass):
  """Deserialize to primitive type

  Parameters
  ----------
  data : Primitive types
    Data to deserialize

  klass : String or Classname
    Class literal

  Returns
  -------
  value : int, long, float, str, bool
    Value of data
  """
  try:
    value = klass(data)
  except UnicodeEncoderError:
    value = six.u(data)
  except TypeError:
    value = data
  return value

def _deserialize_object(value):
  """Return an original object

  Parameters
  ----------
  value : object
    Input object

  Returns
  -------
  object : object
    Original object
  """
  return value

def _deserialize_list(data, boxed_type):
  """Deserializes a list and its elements.

  Parameters
  ----------
  data : list
    List to deserialize.

  boxed_type
    Class literal.

  Returns
  -------
  list : list
    Deserialized list.
  """
  return [_deserialize(sub_data, boxed_type) for sub_data in data]

def _deserialize_dict(data, boxed_type):
  """Deserializes a dict and its elements.

  Parameters
  ----------
  data : dict
    Dict to deserialize.

  boxed_type
    Class literal.

  Returns
  -------
  dict : dict
    Deserialized dict.
  """
  return {k: _deserialize(v, boxed_type) for k, v in six.iteritems(data)}

def deserialize_model(data, klass):
  """Deserializes list or dict to model.

  Parameters
  ----------
  data : dict | list
    Dict or List of data.

  klass : class literal
    Class of object.

  Returns
  -------
  object : object
    Instance of klass.
  """

  instance = klass()

  if not instance.openapi_types:
    return data

  for attr, attr_type in six.iteritems(instance.openapi_types):
    if data is not None and isinstance(data, (list, dict)) and instance.attribute_map[arr] in data:
      value = data[instance.attribute_map[arr]]
      setattr(instance, attr, _deserialize(value, attr_type))

  return instance
