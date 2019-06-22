package com.ki.codegen;

import org.openapitools.codegen.*;
import io.swagger.models.properties.*;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.PathItem.HttpMethod;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Schema;

import org.openapitools.codegen.utils.ModelUtils;
import static org.openapitools.codegen.utils.StringUtils.underscore;
import static org.openapitools.codegen.utils.StringUtils.camelize;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.io.File;

public class PythonMlGenerator extends DefaultCodegen implements CodegenConfig {
  private static final Logger LOGGER = LoggerFactory.getLogger(PythonMlGenerator.class);

  // source folder where to write the files
  protected String sourceFolder = "openapi-server";
  protected String apiVersion = "1.0.0";

  /**
   * Configures the type of generator.
   * 
   * @return  the CodegenType for this generator
   * @see     org.openapitools.codegen.CodegenType
   */
  public CodegenType getTag() {
    return CodegenType.OTHER;
  }

  /**
   * Configures a friendly name for the generator.  This will be used by the generator
   * to select the library with the -g flag.
   * 
   * @return the friendly name for the generator
   */
  public String getName() {
    return "python-ml";
  }

  /**
   * Provides an opportunity to inspect and modify operation data before the code is generated.
   */
  @SuppressWarnings("unchecked")
  @Override
  public Map<String, Object> postProcessOperationsWithModels(Map<String, Object> objs, List<Object> allModels) {

    // to try debugging your code generator:
    // set a break point on the next line.
    // then debug the JUnit test called LaunchGeneratorInDebugger

    Map<String, Object> results = super.postProcessOperationsWithModels(objs, allModels);

    Map<String, Object> ops = (Map<String, Object>)results.get("operations");
    ArrayList<CodegenOperation> opList = (ArrayList<CodegenOperation>)ops.get("operation");

    // iterate over the operation and perhaps modify something
    for(CodegenOperation co : opList){
      // example:
      // co.httpMethod = co.httpMethod.toLowerCase();
    }

    return results;
  }

  /**
   * Returns human-friendly help for the generator.  Provide the consumer with help
   * tips, parameters here
   * 
   * @return A string value for the help message
   */
  public String getHelp() {
    return "Generates a python-ml client library.";
  }

  public PythonMlGenerator() {
    super();

    // set the output folder here
    outputFolder = "generated-code/python-ml";

    /**
     * Models.  You can write model files using the modelTemplateFiles map.
     * if you want to create one template for file, you can do so here.
     * for multiple files for model, just put another entry in the `modelTemplateFiles` with
     * a different extension
     */
    modelTemplateFiles.put(
      "model.mustache", // the template to use
      ".py");       // the extension for each file to write

    /**
     * Api classes.  You can write classes for each Api file with the apiTemplateFiles map.
     * as with models, add multiple entries with different extensions for multiple files per
     * class
     */
    apiTemplateFiles.put(
      "api.mustache",   // the template to use
      ".py");       // the extension for each file to write

    /**
     * Template Location.  This is the location which templates will be read from.  The generator
     * will use the resource stream to attempt to read the templates.
     */
    templateDir = "python-ml";

    /**
     * Api Package.  Optional, if needed, this can be used in templates
     */
    //apiPackage = "org.openapitools.api";

    /**
     * Model Package.  Optional, if needed, this can be used in templates
     */
    //modelPackage = sourceFolder + ".models";

    /**
     * Reserved words.  Override this with reserved words specific to your language
     */
    reservedWords = new HashSet<String> (
      Arrays.asList()
    );

    /**
     * Additional Properties.  These values can be passed to the templates and
     * are available in models, apis, and supporting files
     */
    additionalProperties.put("apiVersion", apiVersion);
    additionalProperties.put("sourceFolder", sourceFolder);

    /**
     * Supporting Files.  You can write single files for the generator with the
     * entire object tree available.  If the input file has a suffix of `.mustache
     * it will be processed by the template engine.  Otherwise, it will be copied
     */
    supportingFiles.add(new SupportingFile("myFile.mustache",   // the input template or file
      "",                                                       // the destination folder, relative `outputFolder`
      "myFile.py")                                          // the output file
    );

    /**
     * Language Specific Primitives.  These types will not trigger imports by
     * the client generator
     */
    languageSpecificPrimitives = new HashSet<String>(
      Arrays.asList(
        "int",
        "float",
        "List",
        "Dict",
        "bool",
        "str",
        "datetime",
        "date",
        "file",
        "object")
    );

    /**
     * Type Mapping
     */
    typeMapping = new HashMap<String, String>();
    typeMapping.put("integer", "int");
    typeMapping.put("float", "float");
    typeMapping.put("number", "float");
    typeMapping.put("long", "int");
    typeMapping.put("double", "float");
    typeMapping.put("array", "List");
    typeMapping.put("map", "Dict");
    typeMapping.put("string", "str");
    typeMapping.put("date", "date");
    typeMapping.put("boolean", "bool");
    typeMapping.put("DateTime", "datetime");
    typeMapping.put("object", "object");
    typeMapping.put("file", "file");
    typeMapping.put("UUID", "str");

  }

  /**
   * Escapes a reserved word as defined in the `reservedWords` array. Handle escaping
   * those terms here.  This logic is only called if a variable matches the reserved words
   * 
   * @return the escaped term
   */
  @Override
  public String escapeReservedWord(String name) {
    return "_" + name;  // add an underscore to the name
  }

  /**
   * Location to write model files.  You can use the modelPackage() as defined when the class is
   * instantiated
   */
  public String modelFileFolder() {
    return outputFolder + "/" + sourceFolder + "/models";
  }

  /**
   * Location to write api files.  You can use the apiPackage() as defined when the class is
   * instantiated
   */
  @Override
  public String apiFileFolder() {
    return outputFolder + "/" + sourceFolder + "/controllers";
  }

  /**
   * override with any special text escaping logic to handle unsafe
   * characters so as to avoid code injection
   *
   * @param input String to be cleaned up
   * @return string with unsafe characters removed or escaped
   */
  @Override
  public String escapeUnsafeCharacters(String input) {
    //TODO: check that this logic is safe to escape unsafe characters to avoid code injection
    return input;
  }

  /**
   * Escape single and/or double quote to avoid code injection
   *
   * @param input String to be cleaned up
   * @return string with quotation mark removed or escaped
   */
  public String escapeQuotationMark(String input) {
    //TODO: check that this logic is safe to escape quotation mark to avoid code injection
    return input.replace("\"", "\\\"");
  }

  private static String dropDots(String str) {
    return str.replaceAll("\\.", "_");
  }

  @Override
  public String toModelName(String name) {
    // sanitize name, replace unexpected character.
    name = sanitizeName(name);

    // remove dollar sign
    name = name.replaceAll("$", "");

    // camelize the model name, phone_number => PhoneNumber
    return org.openapitools.codegen.utils.StringUtils.camelize(name);
  }

  @Override
  public String toModelFilename(String name) {
    // underscore the model file name, PhoneNumber => phone_number
    return org.openapitools.codegen.utils.StringUtils.underscore(dropDots(toModelName(name)));
  }

  @Override
  public String toModelImport(String name) {
    String modelImport;
    if (StringUtils.startsWithAny(name, "import", "from")) {
      modelImport = name;
    } else {
      modelImport = "from ";
      if (!"".equals(modelPackage())) {
        modelImport += modelPackage() + "."; // for example: openapi_server.models.
      }
      modelImport += toModelFilename(name) + " import " + name; // for example: from openapi_server.models.phone_number import PhoneNumber
    }
    return modelImport;
  }

  @Override
  public String toApiFilename(String name) {
    return org.openapitools.codegen.utils.StringUtils.underscore(name);
  }

  @Override
  public String toOperationId(String operationId) {
    return org.openapitools.codegen.utils.StringUtils.underscore(sanitizeName(operationId));
  }

  @Override
  public String toVarName(String name) {
    // sanitize name, replace unexpected character.
    name = sanitizeName(name);

    // remove dollar sign
    name = name.replaceAll("$", "");

    // if it's all uppercase, convert to lower case
    if (name.matches("^[A-Z_]*$")) {
      name = name.toLowerCase(Locale.ROOT);
    }

    // underscore the variable name, pedId => pet_id
    name = org.openapitools.codegen.utils.StringUtils.underscore(name);

    // remove leading underscore
    name = name.replaceAll("^_*", "");

    // for reserved word or word starting with number, append _
    if (isReservedWord(name) || name.matches("^\\d.*")) {
      name = escapeReservedWord(name);
    }

    return name;
  }

  @Override
  public String toParamName(String name) {
    return toVarName(name);
  }

  @Override
  public String getSchemaType(Schema p) {
    String schemaType = super.getSchemaType(p);
    String type = null;
    if (typeMapping.containsKey(schemaType)) {
      type = typeMapping.get(schemaType);
      if (languageSpecificPrimitives.contains(type)) {
        return type;
      }
    } else {
      type = toModelName(schemaType);
    }
    return type;
  }

  /**
   * Return the default value of the property
   *
   * @param p OpenAPI property object
   * @return string representation of the default value of the property
   */
  @Override
  public String toDefaultValue(Schema p) {
    if (ModelUtils.isBooleanSchema(p)) {
      if (p.getDefault() != null) {
        if (p.getDefault().toString().equalsIgnoreCase("false"))
          return "False";
        else
          return "True";
      }
    } else if (ModelUtils.isDateSchema(p)) {
      // TODO
    } else if (ModelUtils.isDateTimeSchema(p)) {
      // TODO
    } else if (ModelUtils.isNumberSchema(p)) {
      if (p.getDefault() != null) {
        return p.getDefault().toString();
      }
    } else if (ModelUtils.isIntegerSchema(p)) {
      if (p.getDefault() != null) {
        return p.getDefault().toString();
      }
    } else if (ModelUtils.isStringSchema(p)) {
      if (p.getDefault() != null) {
        return "'" + (String) p.getDefault() + "'";
      }
    }

    return null;
  }

  /**
   * Return the example value of the property
   *
   * @param p OpenAPI property object
   * @return string representation of the example value of the property
   */
  @Override
  public String toExampleValue(Schema p) {
    if (ModelUtils.isBooleanSchema(p)) {
      if (p.getExample() != null) {
        if (p.getExample().toString().equalsIgnoreCase("false"))
          return "False";
        else
          return "True";
      }
    } else if (ModelUtils.isDateSchema(p)) {
      // TODO
    } else if (ModelUtils.isDateTimeSchema(p)) {
      // TODO
    } else if (ModelUtils.isNumberSchema(p)) {
      if (p.getExample() != null) {
        return p.getExample().toString();
      }
    } else if (ModelUtils.isIntegerSchema(p)) {
      if (p.getExample() != null) {
        return p.getExample().toString();
      }
    } else if (ModelUtils.isStringSchema(p)) {
      if (p.getExample() != null) {
        return "'" + (String) p.getExample() + "'";
      }
    }

    return null;
  }

  @Override
  public void preprocessOpenAPI(OpenAPI openAPI) {
    Map<String, PathItem> paths = openAPI.getPaths();
    if (paths != null) {
      List<String> pathnames = new ArrayList(paths.keySet());
      for (String pathname : pathnames) {
        PathItem path = paths.get(pathname);
        // Converts path parameters to snake_case
        if (pathname.contains("{")) {
          String fixedPath = new String();
          for (String token : pathname.substring(1).split("/")) {
            if (token.startsWith("{")) {
              String snake_case_token = "{" + this.toParamName(token.substring(1, token.length() - 1)) + "}";
              if (!token.equals(snake_case_token)) {
                token = snake_case_token;
              }
            }
            fixedPath += "/" + token;
          }
          if (!fixedPath.equals(pathname)) {
            LOGGER.warn("Path '" + pathname + "' is not consistant with Python variable names. It will be replaced by '" + fixedPath + "'");
            paths.remove(pathname);
            path.addExtension("x-python-connexion-openapi-name", pathname);
            paths.put(fixedPath, path);
          }
        }
        Map<HttpMethod, Operation> operationMap = path.readOperationsMap();
        if (operationMap != null) {
          for (HttpMethod method : operationMap.keySet()) {
            Operation operation = operationMap.get(method);
            String tag = "default";
            if (operation.getTags() != null && operation.getTags().size() > 0) {
              tag = operation.getTags().get(0);
            }
            String operationId = operation.getOperationId();
            if (operationId == null) {
              operationId = getOrGenerateOperationId(operation, pathname, method.toString());
            }
            operation.setOperationId(toOperationId(operationId));
            if (operation.getExtensions() == null || operation.getExtensions().get("x-openapi-router-controller") == null) {
              operation.addExtension(
                "x-openapi-router-controller", sourceFolder + ".controllers." + toApiFilename(tag)
              );
            }
            if (operation.getParameters() != null) {
              for (Parameter parameter : operation.getParameters()) {
                String swaggerParameterName = parameter.getName();
                String pythonParameterName = this.toParamName(swaggerParameterName);
                if (!swaggerParameterName.equals(pythonParameterName)) {
                  LOGGER.warn("Parameter name '" + swaggerParameterName + "' is not consistant with Python variable names. It will be replaced by '" + pythonParameterName + "'");
                  parameter.addExtension("x-python-connexion-openapi-name", swaggerParameterName);
                  parameter.setName(pythonParameterName);
                }
                if (swaggerParameterName.isEmpty()) {
                  LOGGER.error("Missing parameter name in " + pathname + "." + parameter.getIn());
                }
              }
            }
            RequestBody body = operation.getRequestBody();
            if (body != null) {
              if (body.getExtensions() == null || !body.getExtensions().containsKey("x-body-name")) {
                String bodyParameterName = "body";
                if (operation.getExtensions() != null && operation.getExtensions().containsKey("x-codegen-request-body-name")) {
                  bodyParameterName = (String) operation.getExtensions().get("x-codegen-request-body-name");
                } else {
                  // Used by code generator
                  operation.addExtension("x-codegen-request-name", bodyParameterName);
                }
                // Used by connexion
                body.addExtension("x-body-name", bodyParameterName);
              }
            }
          }
        }
      }
      // Sort path names after variable name fix
      List<String> fixedPathnames = new ArrayList(paths.keySet());
      Collections.sort(fixedPathnames);
      for (String pathname : fixedPathnames) {
        PathItem pathItem = paths.remove(pathname);
        paths.put(pathname, pathItem);
      }
    }
  }

  @Override
  public Map<String, Object> postProcessAllModels(Map<String, Object> objs) {
    Map<String, Object> result = super.postProcessAllModels(objs);
    for (Map.Entry<String, Object> entry : result.entrySet()) {
      Map<String, Object> inner = (Map<String, Object>) entry.getValue();
      List<Map<String, Object>> models = (List<Map<String, Object>>) inner.get("models");
      for (Map<String, Object> mo : models) {
        CodegenModel cm = (CodegenModel) mo.get("model");
        // Add additional filename information for imports
        mo.put("pyImports", toPyImports(cm, cm.imports));
      }
    }
    return result;
  }

  private List<Map<String, String>> toPyImports(CodegenModel cm, Set<String> imports) {
    List<Map<String, String>> pyImports = new ArrayList<>();
    for (String im : imports) {
      if (!im.equals(cm.classname)) {
        HashMap<String, String> pyImport = new HashMap<>();
        pyImport.put("import", toModelImport(im));
        pyImports.add(pyImport);
      }
    }
    return pyImports;
  }

  @Override
  public Map<String, Object> postProcessSupportingFileData(Map<String, Object> objs) {
    generateYAMLSpecFile(objs);
    return super.postProcessSupportingFileData(objs);
  }

}
