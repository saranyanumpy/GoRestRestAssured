package com.gorest.tests.posts.negative;

import static io.restassured.RestAssured.given;

import com.gorest.base.BaseTest;
import com.gorest.data.PostMalformedDataProvider;
import com.gorest.endpoints.APIEndpoints;
import com.gorest.utils.FileUtil;
import com.gorest.utils.NegativeUserAssertUtil;
import io.restassured.response.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;

public class CreatePostMalformedJsonTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CreatePostMalformedJsonTest.class);

    @Test(dataProvider = "malformedPostJsonFiles", dataProviderClass = PostMalformedDataProvider.class)
    public void testMalformedJson(String fileName) {
        logger.info("📁 Reading malformed file: {}", fileName);

        String malformedBody = FileUtil.readFileFromResources(fileName);

        Response response = given()
                .spec(requestSpec)
                .body(malformedBody)
                .when()
                .post(APIEndpoints.POSTS_ENDPOINT)
                .then()
                .log().all()
                .extract().response();

        logger.info("Request Body: {}", malformedBody);
        System.out.println("Status Code: " + response.getStatusCode());

        NegativeUserAssertUtil.assertMalformedJsonResponse(response);
        logger.info("✅ Malformed JSON test completed.");
    }

    // Optional: keep this for sanity check
    @Test
    public void testFileLoading() {
        String path = "data/invalidPostsJSON/Post_plain_text.txt";
        URL url = CreatePostMalformedJsonTest.class.getClassLoader().getResource(path);
        System.out.println("🔍 Resolved URL: " + url);
        Assert.assertNotNull(url, "❌ File was not found on the classpath!");
    }
}
