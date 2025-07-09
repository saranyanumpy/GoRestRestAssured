package com.gorest.data;

import com.gorest.utils.RandomDataGenerator;
import org.testng.annotations.DataProvider;

public class UserDataProvider {

    @DataProvider(name = "validUsers")
    public Object[][] provideValidUsers() {
        return new Object[][]{
                {RandomDataGenerator.generateFullName(), RandomDataGenerator.generateEmail(), "male", "active"},
                {RandomDataGenerator.generateFullName(), RandomDataGenerator.generateEmail(), "female", "inactive"}
        };
    }

    @DataProvider(name = "invalidUsers")
    public Object[][] provideInvalidUsers() {
        return new Object[][]{
                {"", "", "", ""},                            // Empty fields
                {"John Doe", "invalidEmail", "other", "on"}  // Bad formats
        };
    }
}
