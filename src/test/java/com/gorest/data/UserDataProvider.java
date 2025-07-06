package com.gorest.data;

import org.testng.annotations.*;

import com.gorest.utils.RandomDataGenerator;
public class UserDataProvider {
	@DataProvider(name="validUsers")
	public Object[][] validUsers(){
		return new Object[][] {
				
				{ RandomDataGenerator.generateName(), RandomDataGenerator.generateEmail() },
	            { RandomDataGenerator.generateName(), RandomDataGenerator.generateEmail()},
	        };
			}
		}
	}

}
