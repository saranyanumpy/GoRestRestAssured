package com.gorest.utils;

import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;

/**
 * Utility class for reusable assertions on API responses.
 */
public class AssertUtil {

    /**
     * Asserts the status code of the response.
     */
    public static void assertStatusCode(Response response, int expectedStatus) {
        Assert.assertEquals(response.getStatusCode(), expectedStatus,
                "Expected status code " + expectedStatus + " but got " + response.getStatusCode());
    }

    /**
     * Asserts that the response contains a specific key at the root level.
     */
    public static void assertKeyExists(Response response, String key) {
        Assert.assertTrue(response.jsonPath().getMap("$").containsKey(key),
                "Key '" + key + "' was not found in the response.");
    }

    /**
     * Asserts that the response contains expected value for a given field.
     */
    public static void assertFieldEquals(Response response, String jsonPath, Object expectedValue) {
        Object actualValue = response.jsonPath().get(jsonPath);
        Assert.assertEquals(actualValue, expectedValue,
                "Expected value for '" + jsonPath + "' was '" + expectedValue + "' but got '" + actualValue + "'");
    }

    /**
     * Asserts that the response is a non-empty array (used for list endpoints).
     */
    public static void assertArrayNotEmpty(Response response) {
        List<?> list = response.jsonPath().getList("$");
        Assert.assertNotNull(list, "Expected array response but got null");
        Assert.assertTrue(!list.isEmpty(), "Expected non-empty array but got empty list");
    }
}
