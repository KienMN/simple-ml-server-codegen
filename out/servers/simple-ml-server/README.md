# An example of a Machine Learning server.

## Struture

```bash
├── README.md
├── dev.config.py
├── openapi_server
│   ├── __init__.py
│   ├── controllers
│   │   ├── __init__.py
│   │   └── models.py
│   ├── models
│   │   ├── __init__.py
│   │   ├── additional_parameters.py
│   │   ├── error_response.py
│   │   ├── linear_regression.py
│   │   ├── linear_regression_model.py
│   │   ├── linear_regression_model_all_of.py
│   │   ├── metrics.py
│   │   ├── samples.py
│   │   ├── success_response.py
│   │   ├── targets.py
│   │   ├── training_request_body.py
│   │   └── validation_request_body.py
│   ├── specs
│   │   └── openapi.yaml
│   ├── typing_utils.py
│   └── utils.py
└── wsgi.py
```

## Starting service
Service can be started by command
```bash
gunicorn -c dev.config.py wsgi:application -b :5000
```
