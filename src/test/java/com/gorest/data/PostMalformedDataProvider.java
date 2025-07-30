package com.gorest.data;

import org.testng.annotations.DataProvider;

public class PostMalformedDataProvider {

	@DataProvider(name = "malformedPostJsonFiles")
	public Object[][] malformedJsonFiles() {
	    return new Object[][] {
	        {"data/invalidPostsJSON/missing_closing_brace_Post.json"},
	        {"data/invalidPostsJSON/Post_unquoted_keys.json"},
	        {"data/invalidPostsJSON/Post_plain_text.txt"}
	    };
	}

}
