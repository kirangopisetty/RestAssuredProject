package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;

public class TC46_BEARER_ACCESS_TOKEN_AUTH_API {
	
	@Test
	public void bearerAuthAPI() {
		
		given()
			.header("Accept", "application/json")
			.header("Authorization", "Bearer 12345")
		
		.when()
			.get("https://httpbin.org/bearer")
		
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(2000L))
			.assertThat().body("authenticated", oneOf(true,false))
			.body("authenticated", equalTo(true))
			.body("token", equalTo("12345"))
			.header("Content-Type", "application/json")
			.log().body();
	}
}