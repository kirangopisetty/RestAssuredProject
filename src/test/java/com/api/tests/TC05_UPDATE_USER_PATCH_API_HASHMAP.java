package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.oneOf;
import java.util.HashMap;

public class TC05_UPDATE_USER_PATCH_API_HASHMAP {
	
	@Test
	public void updateUserPATCHapiHashMap() {
		
		HashMap<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("name", "RAM");
		requestBody.put("email", "ram@laxman.com");
		requestBody.put("status", "inactive");
		
		
			given()
				.header("Accept","application/json")
				.header("Content-Type","application/json")
				.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
			
			.when()
				.body(requestBody)
				.patch("https://gorest.co.in/public/v2/users/7745087")
			
			.then()
				.log().all()
				.statusCode(200)
				.header("Content-Type", "application/json; charset=utf-8")
				.time(lessThan(3000L))
				.assertThat().body("gender", oneOf("male","female"))
				.assertThat().body("status", oneOf("active","inactive"))
				.body("name", equalTo("RAM"))
				.body("gender", equalTo("male"))
				.body("status", equalTo("inactive"));
		}
}
