package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import org.json.JSONObject;
public class TC07_UPDATE_USER_PATCH_API_JSONOBJECT {

	@Test
	public void updateUserPATCHapiJSONObject() {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", "Lord Ram");
		requestBody.put("email", "rama@laxman.com");
		requestBody.put("status", "active");
			
			given()
				.header("Accept","application/json")
				.header("Content-Type","application/json")
				.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
			
			.when()
				.body(requestBody.toString())
				.patch("https://gorest.co.in/public/v2/users/7745087")
			
			.then()
				.log().all()
				.statusCode(200)
				.header("Content-Type", "application/json; charset=utf-8")
				.time(greaterThan(100L))
				.time(lessThan(3000L))
				.assertThat().body("gender", oneOf("male","female"))
				.assertThat().body("status", oneOf("active","inactive"))
				.body("name", equalTo("Lord Ram"))
				.body("gender", equalTo("male"))
				.body("email", equalTo("rama@laxman.com"))
				.body("status", equalTo("active"));	
		}
}