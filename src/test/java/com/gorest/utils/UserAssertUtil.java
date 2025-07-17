package com.gorest.utils;

import java.util.List;
import java.util.Map;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
	

/**
 * Utility class for user-related assertions for CRUD operations.
 */
public class UserAssertUtil {

    private static final Logger logger = LoggerFactory.getLogger(UserAssertUtil.class);

    /**
     * Asserts that a user was created successfully (POST).
     */
    public static void assertUserCreated(Response response, String expectedName, String expectedEmail, String expectedGender, String expectedStatus) {
        logger.debug("Asserting user creation fields...");

        AssertUtil.assertStatusCode(response, 201);
        assertUserFields(response, expectedName, expectedEmail, expectedGender, expectedStatus);
        AssertUtil.assertJsonSchemaUsers(response, "src/test/resources/schemas/userSchema.json");
        logger.debug("Schema is working fine ...");
        AssertUtil.assertFieldMatchesRegex(response, "data.email", "^[A-Za-z0-9+_.-]+@(.+)$");
        AssertUtil.assertResponseTimeLessThan(response, 3000);

        logger.info("✅ User creation validated successfully.");
    }

    /**
     * Asserts that a user was updated successfully (PUT).
     */
    public static void assertUserUpdated(Response response, String expectedName, String expectedEmail, String expectedGender, String expectedStatus) {
        logger.debug("Asserting user update fields...");

        AssertUtil.assertStatusCode(response, 200);
        assertUserFields(response, expectedName, expectedEmail, expectedGender, expectedStatus);
        AssertUtil.assertJsonSchemaUsers(response, "src/test/resources/schemas/userSchema.json");
        AssertUtil.assertFieldMatchesRegex(response, "data.email", "^[A-Za-z0-9+_.-]+@(.+)$");
        AssertUtil.assertResponseTimeLessThan(response, 3000);

        logger.info("✅ User update validated successfully.");
    }

    /**
     * Asserts that a user was patched successfully (PATCH).
     */
    public static void assertUserPatch(Response response, String expectedName, String expectedEmail, String expectedGender, String expectedStatus) {
        logger.debug("Asserting user patch fields...");

        AssertUtil.assertStatusCode(response, 200);
        assertUserFields(response, expectedName, expectedEmail, expectedGender, expectedStatus);
        AssertUtil.assertJsonSchemaUsers(response, "src/test/resources/schemas/userSchema.json");
        AssertUtil.assertFieldMatchesRegex(response, "data.email", "^[A-Za-z0-9+_.-]+@(.+)$");
        AssertUtil.assertResponseTimeLessThan(response, 3000);

        logger.info("✅ User partial update (PATCH) validated successfully.");
    }

    /**
     * Asserts that a list of users was retrieved successfully (GET /users).
     */
    public static void assertGetAllUsers(Response response) {
        logger.debug("Asserting GET all users response...");

        AssertUtil.assertStatusCode(response, 200);

        // Assuming the "data" field contains the list of users
        List<Map<String, Object>> users = response.jsonPath().getList("data");  // Adjust path to "data"
        
        // Pass the list to the assertion
        AssertUtil.assertArrayNotEmpty(users);  // Now passing the list of users

        // Sample check on the first item in the list
        AssertUtil.assertResponseHasField(response, "data[0].id");
        AssertUtil.assertResponseHasField(response, "data[0].name");
        AssertUtil.assertResponseHasField(response, "data[0].email");
        AssertUtil.assertResponseHasField(response, "data[0].gender");
        AssertUtil.assertResponseHasField(response, "data[0].status");

        logger.info("✅ GET all users validated successfully.");
    }



    /**
     * Asserts that a single user was retrieved successfully by ID (GET /users/{id}).
     */
    public static void assertGetUserById(Response response, int expectedId, String expectedName, String expectedEmail) {
    //	 public static void assertGetUserById(Response response, int expectedId, String expectedName, String expectedEmail, String expectedGender, String expectedStatus) {
    		 
    	logger.debug("Asserting GET user by ID...");

        AssertUtil.assertStatusCode(response, 200);
        AssertUtil.assertFieldEquals(response, "data.id", expectedId);
        AssertUtil.assertFieldEquals(response, "data.name", expectedName);
        AssertUtil.assertFieldEquals(response, "data.email", expectedEmail);
     //   AssertUtil.assertFieldEquals(response, "data.gender", expectedGender.toLowerCase());
     //   AssertUtil.assertFieldEquals(response, "data.status", expectedStatus.toLowerCase());

       AssertUtil.assertJsonSchemaUsers(response, "src/test/resources/schemas/userSchema.json");
        AssertUtil.assertFieldMatchesRegex(response, "data.email", "^[A-Za-z0-9+_.-]+@(.+)$");
        AssertUtil.assertResponseTimeLessThan(response,3000);

        logger.info("✅ GET user by ID validated successfully.");
    }

    /**
     * Common field-level assertions for user-related validations.
     */
    private static void assertUserFields(Response response, String expectedName, String expectedEmail, String expectedGender, String expectedStatus) {
        AssertUtil.assertResponseHasField(response, "data.id");
        AssertUtil.assertResponseHasField(response, "data.name");
        AssertUtil.assertResponseHasField(response, "data.email");
        AssertUtil.assertResponseHasField(response, "data.gender");
        AssertUtil.assertResponseHasField(response, "data.status");

        AssertUtil.assertFieldEquals(response, "data.name", expectedName);
        AssertUtil.assertFieldEquals(response, "data.email", expectedEmail);
        AssertUtil.assertFieldEquals(response, "data.gender", expectedGender.toLowerCase());
        AssertUtil.assertFieldEquals(response, "data.status", expectedStatus.toLowerCase());
    }
}
