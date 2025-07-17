package com.gorest.utils;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.restassured.response.Response;

public class CommentsAssertUtil {

    private static final Logger logger = LoggerFactory.getLogger(CommentsAssertUtil.class);

    /**
     * ✅ Asserts comment creation success with full field validation
     */
    public static void assertCommentCreated(Response response, int expectedPostId, String expectedName, String expectedEmail, String expectedBody) {
        AssertUtil.assertStatusCode(response, 201);
        AssertUtil.assertFieldNotNull(response, "data");

        // Required field presence
        AssertUtil.assertResponseHasField(response, "data.id");
        AssertUtil.assertResponseHasField(response, "data.post_id");
        AssertUtil.assertResponseHasField(response, "data.name");
        AssertUtil.assertResponseHasField(response, "data.email");
        AssertUtil.assertResponseHasField(response, "data.body");

        // Field types
        AssertUtil.assertFieldIsInteger(response, "data.id");
        AssertUtil.assertFieldIsInteger(response, "data.post_id");
        AssertUtil.assertFieldIsString(response, "data.name");
        AssertUtil.assertFieldIsString(response, "data.email");
        AssertUtil.assertFieldIsString(response, "data.body");

        // Field values
        AssertUtil.assertFieldEquals(response, "data.post_id", expectedPostId);
        AssertUtil.assertFieldEquals(response, "data.name", expectedName);
        AssertUtil.assertFieldEquals(response, "data.email", expectedEmail);
        AssertUtil.assertFieldEquals(response, "data.body", expectedBody);

        // Optional: schema + timing
        AssertUtil.assertCommentsJsonSchema(response, "src/test/resources/schemas/commentsSchema.json");
        AssertUtil.assertResponseTimeLessThan(response, 3000);

        logger.info("✅ Comment created and validated successfully.");
    }

    /**
     * ✅ Asserts GET /comments/{id}
     */
    public static void assertGetCommentById(Response response, int expectedCommentId, int expectedPostId, String expectedName, String expectedEmail, String expectedBody) {
        AssertUtil.assertStatusCode(response, 200);
        AssertUtil.assertFieldEquals(response, "data.id", expectedCommentId);
        AssertUtil.assertFieldEquals(response, "data.post_id", expectedPostId);
        AssertUtil.assertFieldEquals(response, "data.name", expectedName);
        AssertUtil.assertFieldEquals(response, "data.email", expectedEmail);
        AssertUtil.assertFieldEquals(response, "data.body", expectedBody);

        AssertUtil.assertCommentsJsonSchema(response, "src/test/resources/schemas/commentsSchema.json");
        AssertUtil.assertResponseTimeLessThan(response, 3000);

        logger.info("✅ GET comment by ID validated successfully.");
    }
    public static void assertUpdateCommentById(Response response, int expectedCommentId, int expectedPostId, String expectedName, String expectedEmail, String expectedBody) {
        AssertUtil.assertStatusCode(response, 200);
        AssertUtil.assertFieldEquals(response, "data.id", expectedCommentId);
        AssertUtil.assertFieldEquals(response, "data.post_id", expectedPostId);
        AssertUtil.assertFieldEquals(response, "data.name", expectedName);
        AssertUtil.assertFieldEquals(response, "data.email", expectedEmail);
        AssertUtil.assertFieldEquals(response, "data.body", expectedBody);

        AssertUtil.assertCommentsJsonSchema(response, "src/test/resources/schemas/commentsSchema.json");
        AssertUtil.assertResponseTimeLessThan(response, 3000);

        logger.info("✅ GET comment by ID validated successfully.");
    }
    public static void assertPatchCommentById(Response response, int expectedCommentId, int expectedPostId, String expectedName, String expectedEmail, String expectedBody) {
        AssertUtil.assertStatusCode(response, 200);
        AssertUtil.assertFieldEquals(response, "data.id", expectedCommentId);
        AssertUtil.assertFieldEquals(response, "data.post_id", expectedPostId);
        AssertUtil.assertFieldEquals(response, "data.name", expectedName);
        AssertUtil.assertFieldEquals(response, "data.email", expectedEmail);
        AssertUtil.assertFieldEquals(response, "data.body", expectedBody);

        AssertUtil.assertCommentsJsonSchema(response, "src/test/resources/schemas/commentsSchema.json");
        AssertUtil.assertResponseTimeLessThan(response, 3000);

        logger.info("✅ GET comment by ID validated successfully.");
    }

    /**
     * ✅ Asserts GET /comments - All records
     */
    public static void assertGetAllComments(Response response) {
        logger.debug("Asserting GET all comments response...");
        AssertUtil.assertStatusCode(response, 200);

        List<?> comments = response.jsonPath().getList("data");
        AssertUtil.assertArrayNotEmpty(comments);

        for (int i = 0; i < comments.size(); i++) {
            String path = "data[" + i + "]";
            AssertUtil.assertResponseHasField(response, path + ".id");
            AssertUtil.assertResponseHasField(response, path + ".post_id");
            AssertUtil.assertResponseHasField(response, path + ".name");
            AssertUtil.assertResponseHasField(response, path + ".email");
            AssertUtil.assertResponseHasField(response, path + ".body");

            AssertUtil.assertFieldIsInteger(response, path + ".id");
            AssertUtil.assertFieldIsInteger(response, path + ".post_id");
            AssertUtil.assertFieldIsString(response, path + ".name");
            AssertUtil.assertFieldIsString(response, path + ".email");
            AssertUtil.assertFieldIsString(response, path + ".body");
        }

        AssertUtil.assertResponseTimeLessThan(response, 3000);
        logger.info("✅ GET all comments validated successfully.");
    }
    public static void assertUpdateCommentById(Response response, int expectedPostId, String expectedName, String expectedEmail, String expectedBody) {
        AssertUtil.assertStatusCode(response, 200);
        AssertUtil.assertFieldNotNull(response, "data");

        // Required field presence
        AssertUtil.assertResponseHasField(response, "data.id");
        AssertUtil.assertResponseHasField(response, "data.post_id");
        AssertUtil.assertResponseHasField(response, "data.name");
        AssertUtil.assertResponseHasField(response, "data.email");
        AssertUtil.assertResponseHasField(response, "data.body");

        // Field types
        AssertUtil.assertFieldIsInteger(response, "data.id");
        AssertUtil.assertFieldIsInteger(response, "data.post_id");
        AssertUtil.assertFieldIsString(response, "data.name");
        AssertUtil.assertFieldIsString(response, "data.email");
        AssertUtil.assertFieldIsString(response, "data.body");

        // Field values
        AssertUtil.assertFieldEquals(response, "data.post_id", expectedPostId);
        AssertUtil.assertFieldEquals(response, "data.name", expectedName);
        AssertUtil.assertFieldEquals(response, "data.email", expectedEmail);
        AssertUtil.assertFieldEquals(response, "data.body", expectedBody);

        // Optional: schema + timing
        AssertUtil.assertCommentsJsonSchema(response, "src/test/resources/schemas/commentsSchema.json");
        AssertUtil.assertResponseTimeLessThan(response, 3000);

        logger.info("✅ Comment created and validated successfully.");
    }
    public static void assertPatchCommentById(Response response, int expectedPostId, String expectedName, String expectedEmail, String expectedBody) {
        AssertUtil.assertStatusCode(response, 200);
        AssertUtil.assertFieldNotNull(response, "data");

        // Required field presence
        AssertUtil.assertResponseHasField(response, "data.id");
        AssertUtil.assertResponseHasField(response, "data.post_id");
        AssertUtil.assertResponseHasField(response, "data.name");
        AssertUtil.assertResponseHasField(response, "data.email");
        AssertUtil.assertResponseHasField(response, "data.body");

        // Field types
        AssertUtil.assertFieldIsInteger(response, "data.id");
        AssertUtil.assertFieldIsInteger(response, "data.post_id");
        AssertUtil.assertFieldIsString(response, "data.name");
        AssertUtil.assertFieldIsString(response, "data.email");
        AssertUtil.assertFieldIsString(response, "data.body");

        // Field values
        AssertUtil.assertFieldEquals(response, "data.post_id", expectedPostId);
        AssertUtil.assertFieldEquals(response, "data.name", expectedName);
        AssertUtil.assertFieldEquals(response, "data.email", expectedEmail);
        AssertUtil.assertFieldEquals(response, "data.body", expectedBody);

        // Optional: schema + timing
        AssertUtil.assertCommentsJsonSchema(response, "src/test/resources/schemas/commentsSchema.json");
        AssertUtil.assertResponseTimeLessThan(response, 3000);

        logger.info("✅ Comment created and validated successfully.");
    }

}
