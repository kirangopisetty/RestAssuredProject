package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class bearerAuthenticationAPItest {
	
	@Test
	public void bearerAuthAPI() {
		
		given()
			.header("Accept", "application/json")
			.header("Authorization", "Bearer 12345")
		
		.when()
			.get("https://httpbin.org/bearer")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.header("Content-Type", "application/json")
			.log().all();
	}
}