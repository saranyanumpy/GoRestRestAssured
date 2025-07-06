package com.gorest.base;

import com.gorest.config.Config;
import com.gorest.config.ConfigLoader;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;

public class BaseTest {
	protected Config config;
	
public BaseTest() {
	
	// Load config.yaml through ConfigLoader (Singleton)
	this.config=ConfigLoader.getInstance().getConfig();
	
	// Set REST Assured base URI globally
	RestAssured.baseURI=config.getBaseUrl();
	
	// Set REST Assured timeout configuration
	RestAssured.config=RestAssured.config().httpClient(HttpClientConfig.httpClientConfig()
			.setParam("http.connection.timeout", config.getTimeout())
			.setParam("http.socket.timeout", config.getTimeout()));
}
}
