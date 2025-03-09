package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers;

public class oAuth1AuthenticationAPItest {
	
	@Test
	public void oAuth1API() {
		
		given()
			.header("Accept", "application/json")
			.auth().oauth("consumerKey", "consumerSecret", "accessToken", "secretToken")
		 // .auth().oauth(consumerKey, consumerSecret, accessToken, secretToken, signature)
		.when()
			.get("type your OAuth1 enabled API URL here")
		
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(2000L))
		//	.body("authenticated", equalTo(true))
			.header("Content-Type", "application/json")
			.log().all();			
	}
}