package com.api.tests;

import static org.hamcrest.Matchers.oneOf;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
public class createUserPOSTAPIDDTFakerLibraryApproach1 {
	
	Faker faker = new Faker();
	
	@DataProvider (name="createUserDDT")
	public Object[][] DDT() {
		Object[][] reqBody = new Object[4][4];	// 4 rows, 4 columns
		
		reqBody[0][0]=faker.name().firstName();
		reqBody[0][1]=faker.internet().emailAddress();
		reqBody[0][2]=faker.demographic().sex();
		reqBody[0][3]="active";
		
		reqBody[1][0]=faker.name().firstName();
		reqBody[1][1]=faker.internet().emailAddress();
		reqBody[1][2]=faker.demographic().sex();
		reqBody[1][3]="active";
		
		reqBody[2][0]=faker.name().firstName();
		reqBody[2][1]=faker.internet().emailAddress();
		reqBody[2][2]=faker.demographic().sex();
		reqBody[2][3]="inactive";
		
		reqBody[3][0]=faker.name().firstName();
		reqBody[3][1]=faker.internet().emailAddress();
		reqBody[3][2]=faker.demographic().sex();
		reqBody[3][3]="inactive";
		
		return reqBody;
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