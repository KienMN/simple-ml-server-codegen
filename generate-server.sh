java -cp ki-generators/python-ml/target/python-ml-openapi-generator-1.0.0.jar:openapi-generator/modules/openapi-generator-cli/target/openapi-generator-cli.jar \
org.openapitools.codegen.OpenAPIGenerator generate \
  -g python-ml \
  -i samples/yaml/ml-models-spec.yaml \
  -o out/servers/simple-ml-server \
#  -DdebugOperations
