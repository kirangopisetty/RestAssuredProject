package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class listUsersGetAPI {
	
	@Test
	public void listUsersAPI() {
		
	given()
		.header("Accept", "application/json")
		.header("Content-Type", "application/json")
		.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29");
	
	when()
		.get("https://gorest.co.in/public/v2/users").
	
	then()
		.statusCode(200)
		.log().status()
		.body("gender", hasItems("male", "female"))
		.body("status", hasItems("active", "inactive"))
		.header("Content-Type", "application/json; charset=utf-8")
	//	.body("[0].name", equalTo("Nawal Bhattathiri"))
	//	.body("[1].id", equalTo(7561792))
	//	.body("[2].email", equalTo("shwet_tandon@reichel-ledner.test"))
		.log().all();	// prints response body + response headers
	//	.log().body();	// prints only response body
	//	.log().headers();	// prints only response headers
	}
}