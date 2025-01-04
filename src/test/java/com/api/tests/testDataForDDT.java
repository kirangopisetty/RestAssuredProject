package com.api.tests;

import org.testng.annotations.DataProvider;

import com.github.javafaker.Faker;

public class testDataForDDT {
	
	Faker faker = new Faker();
	
	@DataProvider (name="createUserDDT")
	public Object[][] createUserDDT() {
		
		return new Object[][] {
			{faker.name().firstName(),faker.internet().emailAddress(),faker.demographic().sex(),"active"},
			{faker.name().firstName(),faker.internet().emailAddress(),faker.demographic().sex(),"active"},
			{faker.name().firstName(),faker.internet().emailAddress(),faker.demographic().sex(),"inactive"},
			{faker.name().firstName(),faker.internet().emailAddress(),faker.demographic().sex(),"inactive"}
		};
	}

	@DataProvider(name="deleteUserDDT")
	public Object[] deleteUserDDT() {
		return new Object[] {
				7574335,7574334,7574333,7574332
		};		
	}
}