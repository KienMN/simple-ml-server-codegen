# Instruction to work with OpenApiGenerator
## Cloning the project as a submodule
```bash
git submodule add https://github.com/OpenAPITools/openapi-generator.git
```
## Building the OpenApiGenerator tools.
Go to directory containing `pom.xml` file and run `mvn package`.
```bash
cd openapi-generator
mvn package
```
## Customizing generator
Using `meta` tag of module openapi-generator-cli to create a new generator
```bash
java -jar openapi-generator/modules/openapi-generator-cli/target/openapi-generator-cli.jar meta \
  -o ki-generators/python-ml -n python-ml -p com.ki.codegen
```
## Using new generator
To build new generator, go to the `ki-generators/python-ml` directory (which contains `pom.xml` file), run `mvn package`, go back to root directory and execute the command.
```bash
java -cp ki-generators/python-ml/target/python-ml-openapi-generator-1.0.0.jar:openapi-generator/modules/openapi-generator-cli/target/openapi-generator-cli.jar org.openapitools.codegen.OpenAPIGenerator
```
Now, `python-ml` is an option for `-g`. Generating code by running the command.
```bash
java -cp ki-generator/python-ml/target/python-ml-openapi-generator-1.0.0.jar:openapi-generator/modules/openapi-generator-cli/target/openapi-generator-cli.jar \
  org.openapitools.codegen.OpenAPIGenerator generate -g python-ml \
  -i path/to/openapi.yaml \
  -o path/to/server/directory
```
## Generation process
Source: modules/openapi-generator/src/main/java/org/openapitools/codegen/DefaultGenerator.java
* configureGeneratorProperties()
  * Setting attributes of generator, such as generateApis, generateModels, generateSupportingFiles, etc.
  * processOpts(): Processing several options of generators, such as templateDir, etc.
  * preprocessOpenApi(openAPI): Handling entire openApi specs before being used for generating.
* configureOpenApiInfo(): Adding additional properties, such as appName, appVersion, etc.
* configPostProcessMustacheCompiler(): Setting mustache compiler.
* generateModels()
  * processModels(definitions): Creating a HashMap object containing "models" as a List of "model" which is CodegenModel appearing in definitions.
  * allProcessedModel: Storing all processed models created by processModels() method.
  * generateModel(): Generating each models to files.
* generateApis()
  * processOperations(): Creating a Map of tags and operations in that tags.
  * allOperations: Storing all operations created by processOperations() method.
  * Generating api files.
* generateSupportingFiles(): Generating supporting files.
## Generator modification
