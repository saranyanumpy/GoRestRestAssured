package com.gorest.models;

public class posts {
	private int postId;
	private String title;
    private String body;
  
    public int getPost_Id() {
		return postId;
	}
	public void setPost_Id(int post_Id) {
		this.postId = post_Id;
	}
	public String getPosts_title() {
		return title;
	}
	public void setPosts_title(String posts_title) {
		this.title = posts_title;
	}
	public String getPosts_body() {
		return body;
	}
	public void setPosts_body(String posts_body) {
		this.body = posts_body;
	}

}
