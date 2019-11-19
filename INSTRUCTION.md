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
Note:  
openapi-generator-version in pom.xml has to be found in https://mvnrepository.com/artifact/org.openapitools/openapi-generator-maven-plugin.  
GeneratorTest class need reviewing to make sure the paths is set properly.
Now, `python-ml` is an option for `-g`. Generating code by running the command.
```bash
java -cp ki-generators/python-ml/target/python-ml-openapi-generator-1.0.0.jar:openapi-generator/modules/openapi-generator-cli/target/openapi-generator-cli.jar \
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
Note: generator aims to create a map String to Object from OpenAPI specs, before generating files.  
* Attributes
  * sourceFolder: folder contains source code files.
  * apiVersion: version of api.
  * outputFolder: root folder for all generated code (outputFolder contains sourceFolder).
  * modelTemplateFiles: list of model template files, each models will be generated in a sigle file.
  * apiTemplateFiles: list of api template files, each files contains api in the same tag.
  * supportingFiles: list of supporting files, which is generated after generating model files and api files.
  * templateDir: folder contains all template files.
  * reservedWords: a Set of reserved words specific to the language.
  * additionalProperties: additional information for generation process.
  * languageSpecificPrimitives: a Set of primitive data types of specific programming language (such as Python).
  * typeMapping: a Map from OpenApi data types to programming language data types.
* Methods
  * modelFileFolder: Location to write model files.
  * apiFileFolder: Location to write api files.
  * toModelName: Return the name of model according to the conventions of the language (for example: PhoneNumber in Python)
  * toModelFilename: Return the name of model file according to the conventions of the language (for example: phone_number in Python)
  * toModelImport: Return the import statement of the model (for example: from openapi-server.models.phone_number import PhoneNumber)
  * toApiFilename: Return the name of api file according to the conventions of the language (for example: PetController => pet_controller in Python).
  * toOperationId: Return the name of operation id according to the conventions of the language (for example: getPet => get_pet in Python).
  * toVarName: Return the name of variable according to the conventions of the language (for example: pedId => pet_id in Python)
  * toParamName: Return the name of parameter according to the conventions of the language (for example: pedId => pet_id in Python)
  * getSchemaType: Return primitive data type if the type of schema is primitive data type or model name if it is not.
  * toDefaultValue: Return the default value of the property. Note that, value has to be compatible with language data types, for example true => True in Python.
  * toExampleValue: Return the example value of the property.
  * preprocessOpenAPI: Preprocess openAPI spec to add, modify,... information of the specification.
  * postProcessAllModels: Process data after storing all models.
  * postProcessSupportingFileData: Process data after processing supporting files.
