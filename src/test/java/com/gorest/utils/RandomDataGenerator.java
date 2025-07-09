package com.gorest.utils;

import java.util.Random;
import java.util.UUID;

public class RandomDataGenerator {
	private static final String[] FIRSTNAMES= {"John", "Jane", "Alex", "Chris" };
	private static final String[] LASTNAMES= {"Smith", "Johnson", "Brown", "Davis" };
	private static final Random RANDOM=new Random();
	
	public static String generateFullName() {
		String firstName=FIRSTNAMES[RANDOM.nextInt(FIRSTNAMES.length)];
		String lastName=LASTNAMES[RANDOM.nextInt(LASTNAMES.length)];
		return firstName + " " + lastName;
	}
	 public static String generateEmail() {
	        String uuid = UUID.randomUUID().toString().substring(0, 6);
	        return "user_" + uuid + "@test.com";
	    }

	    // Optionally still keep this short-name generator
	    public static String generateShortName() {
	        return "User_" + UUID.randomUUID().toString().substring(0, 6);
	    }
	}