package com.api.tests;

import static org.hamcrest.Matchers.oneOf;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
public class createUserPOSTAPIDDTApproach1 {
	
	@DataProvider (name="createUserDDT")
	public Object[][] DDT() {
		Object[][] reqBody = new Object[4][4];	// 4 rows, 4 columns
		
		reqBody[0][0]="Mounika";
		reqBody[0][1]="mounika@restassured.com";
		reqBody[0][2]="female";
		reqBody[0][3]="active";
		
		reqBody[1][0]="Rizwana";
		reqBody[1][1]="rizwana@restassured.com";
		reqBody[1][2]="female";
		reqBody[1][3]="active";
		
		reqBody[2][0]="Aviraj";
		reqBody[2][1]="aviraj@restassured.com";
		reqBody[2][2]="male";
		reqBody[2][3]="inactive";
		
		reqBody[3][0]="Gayathri";
		reqBody[3][1]="gayathri@restassured.com";
		reqBody[3][2]="female";
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