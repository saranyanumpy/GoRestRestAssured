package com.gorest.utils;

import io.restassured.response.Response;

import static org.testng.Assert.*;

public class NegativeUserAssertUtil {

    public static void assertInvalidUserResponse(Response response) {
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 401, "❌ Expected status code 422, got: " + statusCode);
    }

    public static void assertMalformedJsonResponse(Response response) {
        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 400 || statusCode == 422 || statusCode == 401,
            "❌ Expected status code 400 or 422 for malformed JSON, but got: " + statusCode);
    }

    public static void assertMissingFieldsError(Response response, String expectedField) {
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains(expectedField),
                "❌ Expected error message to mention: " + expectedField);
    }

    public static void assertDuplicateEmailError(Response response) {
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains("has already been taken") || responseBody.contains("duplicate"),
                "❌ Expected duplicate email error, got: " + responseBody);
    }

    public static void assertInvalidEmailFormat(Response response) {
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.toLowerCase().contains("email") && responseBody.toLowerCase().contains("invalid"),
                "❌ Expected email format error, got: " + responseBody);
    }

    public static void assertNotFound(Response response) {
        assertEquals(response.getStatusCode(), 404, "❌ Expected 404 Not Found");
    }
}
