package com.gorest.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TestDataManager {
    
    private static final Logger logger = LoggerFactory.getLogger(TestDataManager.class);

    // User data
    private static int userId;
    private static String userName;
    private static String userEmail;
    private static String postBody;
    private static int commentPostId;
   
    public static boolean hasPostId(String label) {
        return getPostidmap().get().containsKey(label);
    }
    
    public static int getCommentPostId() {
		return commentPostId;
	}

	public static void setCommentPostId(int commentPostId) {
		TestDataManager.commentPostId = commentPostId;
	}

	public static String getPostBody() {
		return postBody;
	}

	public static void setPostBody(String postBody) {
		TestDataManager.postBody = postBody;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static ThreadLocal<Map<String, Integer>> getPostidmap() {
		return postIdMap;
	}

	// Comment data
    private static int commentId;

    // Thread-local map for storing post IDs in parallel testing
    private static final ThreadLocal<Map<String, Integer>> postIdMap = ThreadLocal.withInitial(HashMap::new);

    // === USER DATA ===

    // Getter and Setter for userId
    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        TestDataManager.userId = userId;
    }

    // Getter and Setter for userName
    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        TestDataManager.userName = userName;
    }

    // Getter and Setter for userEmail
    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmail) {
        TestDataManager.userEmail = userEmail;
    }

    // === COMMENT DATA ===

    // Getter and Setter for commentId
    public static int getCommentId() {
        return commentId;
    }

    public static void setCommentId(int commentId) {
        TestDataManager.commentId = commentId;
    }

    // === POSTS (MULTIPLE SUPPORT) ===

    // Store the post ID for a given label in the current thread's map
    public static void storePostId(String label, int postId) {
        postIdMap.get().put(label, postId);  // Thread-safe access to the map for each thread
    }

    // Retrieve the post ID for a given label
    public static int getPostId(String label) {
        if (!postIdMap.get().containsKey(label)) {
            logger.error("No postId stored with label: {}", label);  // Log the issue if needed
            throw new IllegalArgumentException("No postId stored with label: " + label);
        }
        return postIdMap.get().get(label);  // Retrieve postId safely from ThreadLocal map
    }

    // Check if the post ID is stored for a given label
    public static boolean containsPostId(String label) {
        return postIdMap.get().containsKey(label);  // Check if postId exists for the label
    }

    // Clear the post IDs stored in the current thread's map (for cleanup)
    public static void clearPostIds() {
        postIdMap.get().clear();  // Clear post IDs for the current thread
    }
}
