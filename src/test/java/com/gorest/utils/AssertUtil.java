package com.gorest.utils;

import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;

import java.io.File;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Utility class for reusable assertions on API responses.
 * Helps keep test cases clean, consistent, and readable.
 */
public class AssertUtil {

    /**
     * Validates that the API response matches the given JSON schema file.
     * 
     * @param response        The API response object.
     * @param schemaFilePath  Path to the JSON schema file.
     */
    public static void assertJsonSchemaUsers(Response response, String schemaFilePath) {
        File schema = new File(schemaFilePath);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema));
    }

    /**
     * Validates that the response has the expected HTTP status code.
     * 
     * @param response             The API response.
     * @param expectedStatusCode   The expected status code (e.g., 200, 201).
     */
    public static void assertStatusCode(Response response, int expectedStatusCode) {
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode());
    }

    /**
     * Asserts that a key exists at the root of the response JSON.
     * 
     * @param response   The API response.
     * @param key        The expected key in the root JSON object.
     */
    public static void assertKeyExists(Response response, String key) {
        assertTrue(response.jsonPath().getMap("$").containsKey(key),
                "Key '" + key + "' was not found in the response.");
    }

    /**
     * Asserts that a specific JSON field equals the expected value.
     * 
     * @param response       The API response.
     * @param jsonPath       JSON path to the field.
     * @param expectedValue  Expected value to match.
     */
    public static void assertFieldEquals(Response response, String jsonPath, Object expectedValue) {
        Object actualValue = response.jsonPath().get(jsonPath);
        assertEquals(actualValue, expectedValue,
                "Expected value for '" + jsonPath + "' was '" + expectedValue + "' but got '" + actualValue + "'");

    }

    /**
     * Asserts that a specific JSON field contains the expected substring.
     * Useful for partial message matches (e.g., "Invalid email format").
     * 
     * @param response           The API response.
     * @param jsonPath           JSON path to the field.
     * @param expectedSubstring  Substring expected in the field value.
     */
    public static void assertFieldContains(Response response, String jsonPath, String expectedSubstring) {
        String actual = response.jsonPath().getString(jsonPath);
        assertTrue(actual.contains(expectedSubstring),
                "Expected substring '" + expectedSubstring + "' not found in field: " + jsonPath);
    }

    /**
     * Asserts that a specific JSON field exists and is not null.
     * 
     * @param response   The API response.
     * @param jsonPath   JSON path to the field.
     */
    public static void assertResponseHasField(Response response, String jsonPath) {
        Object value = response.jsonPath().get(jsonPath);
        assertNotNull(value, "Field is missing or null: " + jsonPath);
    }

    /**
     * Asserts that the response body is a non-empty array.
     * Useful for validating list endpoints like GET /posts, GET /users, etc.
     * 
     * @param response  The API response.
     */
    public static void assertArrayNotEmpty(List<?> list) {
        assertNotNull(list, "Expected array response but got null");
        assertFalse(list.isEmpty(), "Expected non-empty array but got empty list");
    }

    /**
     * Asserts that a specific field in the JSON response is not null.
     * 
     * @param response   The API response.
     * @param jsonPath   JSON path to the field.
     */
    public static void assertFieldNotNull(Response response, String jsonPath) {
        Object field = response.jsonPath().get(jsonPath);
        assertNotNull(field, "Field at '" + jsonPath + "' is null.");
    }

    /**
     * Asserts that a specific field in the JSON response is a string.
     * 
     * @param response   The API response.
     * @param jsonPath   JSON path to the field.
     */
    public static void assertFieldIsString(Response response, String jsonPath) {
        Object field = response.jsonPath().get(jsonPath);
        assertNotNull(field, "Field at " + jsonPath + " is null.");
        assertTrue(field instanceof String, "Field at " + jsonPath + " is not a String. Actual type: " + field.getClass().getSimpleName());
    }

    /**
     * Asserts that a specific field in the JSON response is an integer.
     * 
     * @param response   The API response.
     * @param jsonPath   JSON path to the field.
     */
    public static void assertFieldIsInteger(Response response, String jsonPath) {
        Object field = response.jsonPath().get(jsonPath);
        assertNotNull(field, "Field at " + jsonPath + " is null.");
        assertTrue(field instanceof Integer, "Field at " + jsonPath + " is not an Integer. Actual type: " + field.getClass().getSimpleName());
    }
    /**
     * Custom null check with a message override.
     * 
     * @param actual   Object to check.
     * @param message  Message to show if null.
     */
    public static void assertNotNull(Object actual, String message) {
        assert actual != null : message;
    }
    /**
     * Asserts that a specific field in the JSON response matches a given regex pattern.
     * Useful for validating emails, UUIDs, phone numbers, etc.
     * 
     * @param response   The API response.
     * @param jsonPath   JSON path to the field.
     * @param regex      Regular expression to match.
     */
    public static void assertFieldMatchesRegex(Response response, String jsonPath, String regex) {
        String actualValue = response.jsonPath().getString(jsonPath);
        assertNotNull(actualValue, "Field '" + jsonPath + "' is null.");
        assertTrue(actualValue.matches(regex),
                "Field '" + jsonPath + "' value '" + actualValue + "' does not match regex: " + regex);
    }
    /**
     * Asserts that the response time is within an acceptable threshold.
     * 
     * @param response            The API response.
     * @param maxResponseTimeMs   Maximum acceptable time in milliseconds.
     */
    public static void assertResponseTimeLessThan(Response response, long maxResponseTimeMs) {
        long actualTime = response.getTime();
        assertTrue(actualTime <= maxResponseTimeMs,
                "Response time " + actualTime + " ms exceeded the limit of " + maxResponseTimeMs + " ms");
    }

}
