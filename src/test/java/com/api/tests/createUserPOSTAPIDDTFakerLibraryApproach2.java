package com.api.tests;

import static org.hamcrest.Matchers.oneOf;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
public class createUserPOSTAPIDDTFakerLibraryApproach2 {
	
	Faker faker = new Faker();
	
	@DataProvider (name="createUserDDT")
	public Object[][] DDT() {
		
		return new Object[][] {
			{faker.name().firstName(),faker.internet().emailAddress(),faker.demographic().sex(),"active"},
			{faker.name().firstName(),faker.internet().emailAddress(),faker.demographic().sex(),"active"},
			{faker.name().firstName(),faker.internet().emailAddress(),faker.demographic().sex(),"inactive"},
			{faker.name().firstName(),faker.internet().emailAddress(),faker.demographic().sex(),"inactive"}
		};
	}
	
	@Test (dataProvider = "createUserDDT")
	public void createUserPostAPIdataDrivenAutomationTest(String name, String email, String gender, String status) {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", name);
		requestBody.put("email", email);
		requestBody.put("gender", gender);
		requestBody.put("status", status);
		
		given()
			.header("Accept", "application/json")
			.header("Content-Type", "application/json")
			.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
	
		.when()
			.body(requestBody.toString())
			.post("https://gorest.co.in/public/v2/users")
	
		.then()
			.statusCode(201)
			.assertThat().body("gender", oneOf("male","female"))
			.assertThat().body("status", oneOf("active","inactive"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
}