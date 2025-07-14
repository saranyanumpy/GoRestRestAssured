package com.gorest.base;

import com.gorest.config.Config;
import com.gorest.config.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected Config config;
    protected RequestSpecification requestSpec;
    protected Map<String, String> headers = new HashMap<>();

    public BaseTest() {
        // ✅ Load config.yaml values
        this.config = ConfigLoader.getInstance().getConfig();

        // ✅ Set base URI
        RestAssured.baseURI = config.getBaseUrl();

        // ✅ Set timeouts from config
        RestAssured.config = RestAssured.config().httpClient(
                HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", config.getTimeout())
                        .setParam("http.socket.timeout", config.getTimeout())
        );

        // ✅ Prepare headers (you can make Bearer token dynamic later)
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + config.getToken());

        // ✅ Build RequestSpecification for reuse in all tests
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(RestAssured.baseURI)
                .addHeaders(headers)
                .build();
    }
}
