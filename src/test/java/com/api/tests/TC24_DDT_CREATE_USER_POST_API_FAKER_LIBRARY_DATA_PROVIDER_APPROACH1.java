package com.api.tests;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static org.hamcrest.Matchers.*;
import org.json.JSONObject;

public class TC24_DDT_CREATE_USER_POST_API_FAKER_LIBRARY_DATA_PROVIDER_APPROACH1 {
	
	Faker faker = new Faker();
	
	@DataProvider(name="createUserDDT")
	public Object[][] DDT() {
		
		Object[][] requestBody = new Object[4][4];	// 4 rows, 4 columns
		requestBody[0][0] = faker.name().firstName();
		requestBody[0][1] = faker.demographic().sex();
		requestBody[0][2] = faker.internet().emailAddress();
		requestBody[0][3] = "active";
		
		requestBody[1][0] = faker.name().firstName();
		requestBody[1][1] = faker.demographic().sex();
		requestBody[1][2] = faker.internet().emailAddress();
		requestBody[1][3] = "inactive";
		
		requestBody[2][0] = faker.name().firstName();
		requestBody[2][1] = faker.demographic().sex();
		requestBody[2][2] = faker.internet().emailAddress();
		requestBody[2][3] = "active";
		
		requestBody[3][0] = faker.name().firstName();
		requestBody[3][1] = faker.demographic().sex();
		requestBody[3][2] = faker.internet().emailAddress();
		requestBody[3][3] = "inactive";
		
		return requestBody;
	}

	@Test(dataProvider="createUserDDT")
	public void createUserPOSTapiDDT(String name, String gender, String email, String status) {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", name);
		requestBody.put("gender", gender);
		requestBody.put("email", email);
		requestBody.put("status", status);
			
			given()
				.header("Accept","application/json")
				.header("Content-Type","application/json")
				.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
			
			.when()
				.body(requestBody.toString())
				.post("https://gorest.co.in/public/v2/users")
			
			.then()
				.log().status()
				.log().body()
				.assertThat().body("gender", oneOf("male","female"))
				.assertThat().body("status", oneOf("active","inactive"))
				.statusCode(201)
				.statusLine("HTTP/1.1 201 Created")
				.time(lessThan(4000L))
				.header("Content-Type", "application/json; charset=utf-8");
		}
}