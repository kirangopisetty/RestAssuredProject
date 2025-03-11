package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import org.json.JSONObject;
import com.github.javafaker.Faker;
public class TC11_UPDATE_USER_PATCH_API_HASHMAP_JAVA_FAKER_LIBRARY {

	Faker faker = new Faker();
	@Test
	public void updateUserAPI() {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", faker.name().prefix()+" "+faker.name().lastName());
		requestBody.put("email", faker.internet().emailAddress());
		requestBody.put("status", "inactive");
		
		given()
			.header("Accept", "application/json")
			.header("Content-Type", "application/json")
			.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
				
		.when()
			.body(requestBody.toString())
			.patch("https://gorest.co.in/public/v2/users/7564009")
		
		.then()
			.log().body()
			.log().status()
			.statusCode(200)
			.assertThat().body("gender", oneOf("male","female"))
			.assertThat().body("status", oneOf("active","inactive"))
			.time(lessThan(2000L))
			//.time(Matchers.greaterThan(1000L))
			//.time(Matchers.both(Matchers.greaterThanOrEqualTo(1000L)).and(Matchers.lessThanOrEqualTo(2000L)));
			.body("status", equalTo("inactive"))
			.header("Content-Type", "application/json; charset=utf-8");	
	}
}