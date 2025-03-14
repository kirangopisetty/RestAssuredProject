package com.api.tests;

import org.testng.annotations.DataProvider;
import com.github.javafaker.Faker;

public class TC34_DDT_DATA_PROVIDERS_APPROACH3 {
	
	Faker faker = new Faker();
	
	@DataProvider(name="createUserDDT")
	public Object[][] DDT_CreateUserAPI() {
	
	return new Object[][]	 
	{
		{faker.name().fullName(), faker.demographic().sex(), faker.internet().emailAddress(), "active"},
		{faker.name().fullName(), faker.demographic().sex(), faker.internet().emailAddress(), "inactive"},
		{faker.name().fullName(), faker.demographic().sex(), faker.internet().emailAddress(), "active"},
		{faker.name().fullName(), faker.demographic().sex(), faker.internet().emailAddress(), "inactive"}
	};
	}
	
	@DataProvider(name="updateUserDDT")
	public Object[][] DDT_UpdateUserAPI() {
		
	return new Object[][]	 
		{
			{faker.name().fullName(), faker.internet().emailAddress(), "active"},
			{faker.name().fullName(), faker.internet().emailAddress(), "inactive"},
			{faker.name().fullName(), faker.internet().emailAddress(), "active"},
			{faker.name().fullName(), faker.internet().emailAddress(), "inactive"}
		};	
	}
	
	@DataProvider(name="deleteUserDDT")
	public Object[][] DDT_DeleteUserAPI() {
		
		return new Object[][]	// 4 rows, 1 column	 
				{
					{"7763009"},
					{"7763010"},
					{"7763012"},
					{"7763230"}
				};
	}
}