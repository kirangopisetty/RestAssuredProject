package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class basicAuthenticationAPItest {
	
	@Test
	public void basicAuthAPI() {
		
		given()
			.header("Accept", "application/json")
			.auth().basic("lion", "tiger")
			
		.when()
			.get("https://httpbin.org/basic-auth/lion/tiger")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.header("Content-Type", "application/json")
			.log().all();			
	}
}