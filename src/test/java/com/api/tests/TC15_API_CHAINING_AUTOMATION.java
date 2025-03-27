package com.api.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.oneOf;
import java.util.HashMap;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import static io.restassured.RestAssured.*;

public class TC15_API_CHAINING_AUTOMATION {

	int extractedID;
	@Test (priority = 1)
	public void createUserPOSTapiJavaFakerLibrary() {
		
		Faker faker = new Faker();	
			
		HashMap<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("name", faker.name().firstName());
		requestBody.put("gender", faker.demographic().sex());
		requestBody.put("email", faker.internet().emailAddress());
		requestBody.put("status","active");
		
		extractedID = given()
				.header("Accept","application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
			
			.when()
				.body(requestBody)
				.post("https://gorest.co.in/public/v2/users")
				.jsonPath().getInt("id");	// to extract ID from the POST API response body
				System.out.println("The user is created with ID >> "+extractedID);
		
		/*	.then()
				.log().status()
				.log().body()
				.statusCode(201)
				.statusLine("HTTP/1.1 201 Created")
				.assertThat().body("gender", oneOf("male","female"))
				.assertThat().body("status", oneOf("active","inactive"))
				.time(Matchers.lessThan(4000L))
				.body("status", equalTo("active"))
				.header("Content-Type", "application/json; charset=utf-8");	*/
		}	
	
	@Test (priority = 2)
	public void updateUserPATCHapiHashMap() {
		
		Faker faker = new Faker();	
		HashMap<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("name", faker.name().fullName());
		requestBody.put("email", faker.internet().emailAddress());
		requestBody.put("status", "inactive");
		
		
			given()
				.header("Accept","application/json")
				.header("Content-Type","application/json")
				.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
			
			.when()
				.body(requestBody)
				.patch("https://gorest.co.in/public/v2/users/"+extractedID)
			
			.then()
				.log().body()
				.statusCode(200)
				.header("Content-Type", "application/json; charset=utf-8")
				.time(lessThan(3000L))
				.time(greaterThan(100L))
				.time(lessThan(3000L)).and().time(greaterThan(100L))
				.time(lessThanOrEqualTo(3000L)).and().time(greaterThanOrEqualTo(100L))
				.assertThat().body("gender", oneOf("male","female"))
				.assertThat().body("status", oneOf("active","inactive"))
				.body("status", equalTo("inactive"));
				System.out.println("The user is updated with ID >> "+extractedID);
		}
	
	@Test (priority = 3)
	public void deleteUserAPI() {
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.delete("https://gorest.co.in/public/v2/users/"+extractedID)
		
		.then()
			.log().status()
			.time(lessThanOrEqualTo(3000L))
			.time(greaterThanOrEqualTo(100L))
			
			.statusCode(204)
			.statusLine("HTTP/1.1 204 No Content")
			.body(isEmptyOrNullString());	
			System.out.println("The user is deleted with ID >> "+extractedID);
	}
}
