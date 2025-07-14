package com.gorest.utils;

import java.util.HashMap;
import java.util.Map;

public class TestDataManager {
	
    private static int userId;
    private static String userName;
    private static String userEmail;

    private static int commentId;

    // ✅ Replaced single postId with a map for multiple post IDs
    private static final Map<String, Integer> postIdMap = new HashMap<>();

    // === USER ===
    public static int getUserId() {
        return userId;
    }
    public static void setUserId(int userId) {
        TestDataManager.userId = userId;
    }

    public static String getUserName() {
        return userName;
    }
    public static void setUserName(String userName) {
        TestDataManager.userName = userName;
    }

    public static String getUserEmail() {
        return userEmail;
    }
    public static void setUserEmail(String userEmail) {
        TestDataManager.userEmail = userEmail;
    }

    // === COMMENT ===
    public static int getCommentId() {
        return commentId;
    }
    public static void setCommentId(int commentId) {
        TestDataManager.commentId = commentId;
    }

    // === POSTS (MULTIPLE SUPPORT) ===
    public static void storePostId(String label, int postId) {
        postIdMap.put(label, postId);
    }

    public static int getPostId(String label) {
        if (!postIdMap.containsKey(label)) {
            throw new IllegalArgumentException("No postId stored with label: " + label);
        }
        return postIdMap.get(label);
    }

    public static void clearPostIds() {
        postIdMap.clear();
    }
}
