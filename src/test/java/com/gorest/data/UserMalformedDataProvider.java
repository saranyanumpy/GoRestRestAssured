package com.gorest.data;

import org.testng.annotations.DataProvider;

public class UserMalformedDataProvider {

	@DataProvider(name = "malformedJsonFiles")
	public Object[][] malformedJsonFiles() {
	    return new Object[][] {
	        {"data/invalidUsersJSON/missing_closing_brace.json"},
	        {"data/invalidUsersJSON/unquoted_keys.json"},
	        {"data/invalidUsersJSON/plain_text.txt"}
	    };
	}
}
