package com.api.tests;

import static io.restassured.RestAssured.*;
import java.io.File;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class TC42_FILE_UPLOAD_API {

	@Test
	public void fileUpload1() {
	
	// this is the file we want to upload
	File fileToUpload = new File("C:\\Users\\Kiran\\Downloads\\REST ASSURED TESTING\\allUsersResponse.json");	
	
	Response response=	given()
		//	.header("Content-Type", "multipart/form-data")
			.multiPart("file", fileToUpload, "multipart/form-data")
			
		.when()
			.post("https://the-internet.herokuapp.com/upload")
			.thenReturn();
	
		System.out.println("Response code >>"+response.getStatusCode());
		System.out.println("Response body >>"+response.prettyPrint());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	// OR
	
	@Test
	public void fileUpload2(){
		
		File fileToUpload = new File("C:\\Users\\Kiran\\Downloads\\REST ASSURED-API AUTOMATION TESTING\\downloadGetAPIresponse.json");
		
		given()
			.multiPart("file", fileToUpload, "multipart/form-data")
			
		.when()
			.post("https://the-internet.herokuapp.com/upload")
		
		.then()
			.log().body()
			.statusCode(200)
			.log().status();
	}
}