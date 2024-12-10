package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class oAuth1AuthenticationAPItest {
	
	@Test
	public void oAuth1API() {
		
		given()
			.header("Accept", "application/json")
			.auth().oauth("consumerKey", "consumerSecret", "accessToken", "secretToken")
			
		.when()
			.get("your API URL goes here")
		
		.then()
			.statusCode(200)
		//	.body("authenticated", equalTo(true))
			.header("Content-Type", "application/json")
			.log().all();			
	}
}