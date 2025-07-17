package com.gorest.data;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.gorest.models.comments;
import com.gorest.utils.CsvReaderUtil;

public class CommentsDataProvider {
	@DataProvider(name = "UpdateCommentTest")
	public Object[][] provideUpdateCommentsData() {
	    List<comments> commentList = CsvReaderUtil.readCsv("commentsPositive.csv", comments.class);

	    List<comments> commentRows = commentList.stream()
	            .filter(c -> "PUT".equalsIgnoreCase(c.getTestType()))
	            .toList();

	    Object[][] testData = new Object[commentRows.size()][3];
	    for (int i = 0; i < commentRows.size(); i++) {
	        comments p = commentRows.get(i);
	        testData[i][0] = p.getName();
	        testData[i][1] = p.getEmail();
	        testData[i][2] = p.getBody();
	    }

	    return testData;
	}
	
	@DataProvider(name = "PatchCommentTest")
	public Object[][] providePatchCommentsData() {
	    List<comments> commentList = CsvReaderUtil.readCsv("commentsPositive.csv", comments.class);

	    List<comments> commentRows = commentList.stream()
	            .filter(c -> "PATCH".equalsIgnoreCase(c.getTestType()))
	            .toList();

	    Object[][] testData = new Object[commentRows.size()][3];
	    for (int i = 0; i < commentRows.size(); i++) {
	        comments p = commentRows.get(i);
	        testData[i][0] = p.getName();
	        testData[i][1] = p.getEmail();
	        testData[i][2] = p.getBody();
	    }

	    return testData;
	}
}
