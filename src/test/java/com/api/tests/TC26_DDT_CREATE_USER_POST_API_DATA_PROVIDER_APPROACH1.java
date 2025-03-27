package com.api.tests;

import static io.restassured.RestAssured.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;

public class TC26_DDT_CREATE_USER_POST_API_DATA_PROVIDER_APPROACH1 {
	
	@DataProvider(name="createUserDDT")
	public Object[][] DDT() {
		Object[][] requestBody = new Object[4][4];	// 4 rows, 4 columns
		requestBody[0][0] = "Divya";
		requestBody[0][1] = "female";
		requestBody[0][2] = "divya11@api.com";
		requestBody[0][3] = "active";
		
		requestBody[1][0] = "Umesh";
		requestBody[1][1] = "male";
		requestBody[1][2] = "umesh1@gmail.com";
		requestBody[1][3] = "inactive";
		
		requestBody[2][0] = "Rahul";
		requestBody[2][1] = "male";
		requestBody[2][2] = "rahul1@tool.com";
		requestBody[2][3] = "active";
		
		requestBody[3][0] = "Reshma";
		requestBody[3][1] = "female";
		requestBody[3][2] = "reshma1@automation.com";
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