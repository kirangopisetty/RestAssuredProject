package com.api.tests;

import static io.restassured.RestAssured.*;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class fileUploadAPI {

	@Test
	public void fileUpload() {
	
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
}