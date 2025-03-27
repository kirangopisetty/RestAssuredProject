package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;

public class TC44_BASIC_AUTH_API {
	
	@Test
	public void basicAuthAPI() {
		
		given()
			.header("Accept", "application/json")
			.auth().basic("user", "passwd")
			
		.when()
			.get("https://httpbin.org/basic-auth/user/passwd")
		
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(3000L))
			.assertThat().body("authenticated", oneOf(true,false))
			.body("authenticated", equalTo(true))
			.body("authenticated", is(true))
			.body("user", equalTo("user"))
			.body("authenticated", notNullValue())
			.body("user", notNullValue())
			.header("Content-Type", "application/json")
			.log().all();
	}
}