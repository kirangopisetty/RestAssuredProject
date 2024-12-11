package com.api.tests;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.IOException;
public class apachePOIexcel {
	
	@DataProvider (name="createUserDDT")
	public Object[][] DDT() throws IOException {
		
	String excelFilePath="./src/test/resources/createUserTestData.xlsx";
	int rowNum=XLUtils.getRowCount(excelFilePath,"Sheet1");
	int colCount=XLUtils.getCellCount(excelFilePath, "Sheet1",1);

	String empdata[][]=new String[rowNum][colCount];

	for (int i=1; i<=rowNum; i++) {
		for (int j=0;j<colCount;j++){
			empdata[i-1][j]=XLUtils.getCellData(excelFilePath,"Sheet1",i,j);
		}
	}
	return (empdata);
	}
	
	@Test (dataProvider = "createUserDDT")
	public void createUserPostAPIdataDrivenAutomationTest(String name, String email, String gender, String status) {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", name);
		requestBody.put("email", email);
		requestBody.put("gender", gender);
		requestBody.put("status", status);
		
		given().
			header("Accept", "application/json").
			header("Content-Type", "application/json").
			header("Authorization", "Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29").
		
		when().
			body(requestBody.toString()).
			post("https://gorest.co.in/public/v2/users").
		
		then().
		//	statusCode(201).
			header("Content-Type", "application/json; charset=utf-8").
			log().all();
	}
}
