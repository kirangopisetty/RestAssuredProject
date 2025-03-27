package com.api.tests;

import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

@Epic("EPIC-Achieve end to end test case automation")
public class TC43_ALLURE_HTML_TEST_REPORTS_E2E_API_AUTOMATION {
	
	int extractedID;
	
	// E2E Workflow : 
	// CREATE A USER (POST) >> VERIFY IF USER IS CREATED (GET) >> UPDATE THE CREATED USER (PATCH) >> 
	// VERIFY IF USER IS UPDATED (GET) >> DELETE THE CREATED USER (DELETE) >> VERIFY IF USER IS DELETED (GET)
	
	@Feature("CREATE A USER USING POST HTTP METHOD")
	@Description("Description:This API uses random java functions to generate test data")
	@Story("As a user, I can create a user in the database")
	@Severity(SeverityLevel.BLOCKER)
	@Test (priority = 0)
	public void createUserAPI() {
	
	Faker faker = new Faker();	
		
	HashMap<String, String> requestBody = new HashMap<String, String>();
	requestBody.put("name", faker.name().firstName());
	requestBody.put("gender", faker.demographic().sex());
	requestBody.put("email", faker.internet().emailAddress());
	requestBody.put("status","active");
	
	extractedID = given()
			.header("Accept","application/json")
			.header("User-Agent","Mozilla/5.0 (X11; Linux i686; rv:136.0) Gecko/20100101 Firefox/136.0")	
			.header("Content-Type", "application/json")
			.header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.body(requestBody)
			.post("https://gorest.co.in/public/v2/users")
		//	.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaPOSTapi.json"))
			.jsonPath().getInt("id");	// to extract ID from the POST API response body
			System.out.println("The user is created with ID >> "+extractedID);
			Allure.step("The user is created with ID >> "+extractedID);
	}
	
	@Feature("VERIFY IF USER IS CREATED USING GET HTTP METHOD")
	@Story("As a user, I can verify if the user is created in the database")
	@Description("Description:This API uses the extracted ID to generate the user details")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority = 1, dependsOnMethods = {"createUserAPI"})
	public void verifyIfUserIsCreated() {
		
		given()	// api request section
			.header("Accept", "application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
			.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36")
		.when()		// api request section
			.get("https://gorest.co.in/public/v2/users")
			
		.then()		// api response section
			.log().body()
		 // .body("id", equalTo(+extractedID))
			.body("[0].id", equalTo(+extractedID))
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaGETapi.json"))
			.time(Matchers.lessThan(2000L))	//Verify if response time < 2 seconds
			.statusCode(200)	// Verify if response code=200 OK
			.statusLine("HTTP/1.1 200 OK")	// Verify if status line = 200 OK
			.header("Content-Type", "application/json; charset=utf-8")	// Verify if response body is in JSON format
			.body("gender", hasItems("male","female"))		// Verify if gender is either male or female
			.body("status", hasItems("active","inactive"));	// Verify if status is either active or inactive
			System.out.println("The user is successfully created with ID >> "+extractedID);
			Allure.step("The user is successfully created with ID >>"+extractedID);
	}
		
	@Feature("VERIFY IF USER IS UPDATED USING PATCH HTTP METHOD")
	@Story("As a user, I can update an existing user in the database")
	@Description("Description:This API uses random java functions to generate test data")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority = 2, dependsOnMethods = {"createUserAPI"})
	public void updateUserAPI() {
		
	Faker faker = new Faker();	
	HashMap<String, String> requestBody = new HashMap<String, String>();
	requestBody.put("name", faker.name().prefix()+" "+faker.name().lastName());
	requestBody.put("email", faker.internet().emailAddress());
	requestBody.put("status", "inactive");
	
		given()
			.header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 14_7_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.3 Safari/605.1.15")
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.body(requestBody)
			.patch("https://gorest.co.in/public/v2/users/"+extractedID)
		
		.then()
			.log().body()
			.statusCode(200)
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaPATCHapi.json"))
			.header("Content-Type", "application/json; charset=utf-8")
			.time(lessThan(3000L))
			.assertThat().body("gender", oneOf("male","female"))
			.assertThat().body("status", oneOf("active","inactive"))
			.body("status", equalTo("inactive"));
			System.out.println("The user is updated with ID >> "+extractedID);
			Allure.step("The user is updated with ID >> "+extractedID);
	}
	
	@Feature("VERIFY IF USER IS UPDATED USING GET HTTP METHOD")
	@Story("As a user, I can verify if the user is updated in the database")
	@Description("Description:This API uses the extracted ID to generate the updated user details")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3, dependsOnMethods = {"createUserAPI"})
	public void verifyIfUserIsUpdated() {
		
		given()	// api request section
			.header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 14_7_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.3 Safari/605.1.15")
			.header("Accept", "application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()		// api request section
			.get("https://gorest.co.in/public/v2/users")
			
		.then()		// api response section
			.log().body()
	     //	.body("id", not(equalTo(+extractedID)))	
			.body("[0].id", equalTo(+extractedID))
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaGETapi.json"))
			.time(Matchers.lessThan(2000L))	//Verify if response time < 2 seconds
			.statusCode(200)	// Verify if response code=200 OK
			.statusLine("HTTP/1.1 200 OK")	// Verify if status line = 200 OK
			.header("Content-Type", "application/json; charset=utf-8")	// Verify if response body is in JSON format
			.body("gender", hasItems("male","female"))		// Verify if gender is either male or female
			.body("status", hasItems("active","inactive"));	// Verify if status is either active or inactive
			System.out.println("The user is successfully updated with ID >> "+extractedID);
			Allure.step("The user is successfully updated with ID >> "+extractedID);
	}
	
	@Feature("VERIFY IF USER IS DELETED USING DELETE HTTP METHOD")
	@Story("As a user, I can delete an existing user in the database")
	@Description("Description:This API uses the extracted ID to delete the user")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority = 4, dependsOnMethods = {"createUserAPI"})
	public void deleteUserAPI() {
		
		given()
			.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36 Edg/131.0.2903.86")
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.delete("https://gorest.co.in/public/v2/users/"+extractedID)
		
		.then()
			.log().status()
			.statusCode(204)
			.statusLine("HTTP/1.1 204 No Content")
			.body(isEmptyOrNullString());
			System.out.println("The user is deleted with ID >> "+extractedID);
			Allure.step("The user is deleted with ID >> "+extractedID);
	}
	
	@Feature("VERIFY IF USER IS DELETED USING DELETE HTTP METHOD")
	@Story("As a user, I can verify if the user is deleted from the database")
	@Description("Description:This API uses extracted ID to verify if the user is deleted")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority = 5, dependsOnMethods = {"createUserAPI"})
	public void verifyIfUserIsDeleted() {
		
		given()	// api request section
			.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36 Edg/131.0.2903.86")
			.header("Accept", "application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()		// api request section
			.get("https://gorest.co.in/public/v2/users")
			
		.then()		// api response section
			.log().body()
		//	.body("id", not(equalTo(+extractedID)))
			.body("[0].id", not(equalTo(+extractedID)))
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaGETapi.json"))
			.time(Matchers.lessThan(4000L))	//Verify if response time < 4 seconds
			.statusCode(200)	// Verify if response code=200 OK
			.statusLine("HTTP/1.1 200 OK")	// Verify if status line = 200 OK
			.header("Content-Type", "application/json; charset=utf-8")	// Verify if response body is in JSON format
			.body("gender", hasItems("male","female"))		// Verify if gender is either male or female
			.body("status", hasItems("active","inactive"));	// Verify if status is either active or inactive
			System.out.println("The user is successfully deleted with ID >> "+extractedID);
			Allure.step("The user is successfully deleted with ID >> "+extractedID);
	}
}