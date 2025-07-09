
package com.gorest.utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Utility class for validating JSON response schema using Rest Assured.
 * Schema files must be stored under: src/test/resources/schemas/
 */
public class SchemaValidator {

    /**
     * Validates the response body against a given schema file.
     *
     * @param response        the REST-assured response object
     * @param schemaFileName  the name of the schema file (e.g., "userListSchema.json")
     */
    public static void validate(Response response, String schemaFileName) {
        // Construct the full path to the schema file
        File schemaFile = new File("src/test/resources/schemas/" + schemaFileName);

        // Fail fast if schema file doesn't exist
        if (!schemaFile.exists()) {
            throw new RuntimeException("Schema file not found: " + schemaFile.getAbsolutePath());
        }

        // Perform schema validation using Rest Assured's built-in matcher
        assertThat("JSON schema validation failed!",
                response.getBody().asString(),
                JsonSchemaValidator.matchesJsonSchema(schemaFile));
    }
}
