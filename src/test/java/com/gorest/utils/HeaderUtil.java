package com.gorest.utils;

import com.gorest.config.ConfigLoader;
import com.gorest.config.Config; // <--- your POJO

import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.util.ArrayList;
import java.util.List;

public class HeaderUtil {

	 private static final Config config = ConfigLoader.getInstance().getConfig();

	    public static Headers getDefaultHeaders() {
	        List<Header> headers = new ArrayList<>();
	        headers.add(new Header("Authorization", "Bearer " + config.getToken()));
	        headers.add(new Header("Content-Type", "application/json"));
	        return new Headers(headers);
	    }
	    
	    public static Headers getHeadersWithoutAuth() {
	        List<Header> headers = new ArrayList<>();
	        headers.add(new Header("Content-Type", "application/json"));
	        return new Headers(headers);
	    }
	}