package com.api.tests;

import org.testng.annotations.DataProvider;

public class testDataForDDT {
	
	@DataProvider (name="createUserDDT")
	public Object[][] createUserDDT() {
		
		return new Object[][] {
			{"Haritha","haritha@restassured.com","female","active"},
			{"Chandran","chandran@restassured.com","male","active"},
			{"Mohit","mohit@restassured.com","male","inactive"},
			{"Mallikarjuna","mallikarjuna@restassured.com","male","inactive"}
		};
	}

	@DataProvider(name="deleteUserDDT")
	public Object[] deleteUserDDT() {
		return new Object[] {
				7574335,7574334,7574333,7574332
		};		
	}
}