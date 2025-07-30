package com.gorest.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;

public class posts {
	@CsvBindByName(column = "testType") // ⚠️ Must match header exactly
    private String testType;
	
	@CsvBindByName(column = "label")    // e.g., post1, post2
    private String label;
	
	@CsvBindByName(column = "title")
    private String title;
	
	@CsvBindByName(column = "body")
    private String body;
	
	@CsvBindByName(column = "postId")
    private String postId;
	
	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	// Required to associate post with user
	@JsonProperty("user_id")
    private int userId;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

    public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	@Override
	public String toString() {
	    return "posts{" +
	            "userId=" + userId +
	            ", title='" + title + '\'' +
	            ", body='" + body + '\'' +
	            '}';
	}


  
   
}
