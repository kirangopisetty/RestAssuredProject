package com.api.tests;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static org.hamcrest.Matchers.*;
import org.json.JSONObject;

public class TC28_DDT_UPDATE_USER_PATCH_API_FAKER_LIBRARY_DATA_PROVIDER_APPROACH1 {
	
	Faker faker = new Faker();
	
	@DataProvider(name="updateUserDDT")
	public Object[][] DDT() {
		
		Object[][] requestBody = new Object[4][3];	// 4 rows, 3 columns
		requestBody[0][0] = faker.name().firstName();
		requestBody[0][1] = faker.internet().emailAddress();
		requestBody[0][2] = "active";
		
		requestBody[1][0] = faker.name().firstName();
		requestBody[1][1] = faker.internet().emailAddress();
		requestBody[1][2] = "inactive";
		
		requestBody[2][0] = faker.name().firstName();
		requestBody[2][1] = faker.internet().emailAddress();
		requestBody[2][2] = "active";
		
		requestBody[3][0] = faker.name().firstName();
		requestBody[3][1] = faker.internet().emailAddress();
		requestBody[3][2] = "inactive";
		
		return requestBody;
	}

	@Test(dataProvider="updateUserDDT")
	public void updateUserPATCHapiDDT(String name, String email, String status) {
		
		JSONObject reqBody = new JSONObject();
		reqBody.put("name", name);
		reqBody.put("email", email);
		reqBody.put("status", status);
			
			given()
				.header("Accept","application/json")
				.header("Content-Type","application/json")
				.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
			
			.when()
				.body(reqBody.toString())
				.patch("https://gorest.co.in/public/v2/users/7761133")
			
			.then()
				.log().status()
				.log().body()
				.assertThat().body("gender", oneOf("male","female"))
				.assertThat().body("status", oneOf("active","inactive"))
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.time(lessThan(4000L))
				.header("Content-Type", "application/json; charset=utf-8");
		}
}