package com.api.tests;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static org.hamcrest.Matchers.equalTo;

@Epic("Epic:SOAP APIs Automation Testing")
public class TC50_SOAP_APIs {
	
	@Feature("ADDITION-SOAP API")
	@Description("This is a SOAP API that uses POST http method")
	@Story("As a user, I can add 2 integers to get addResult")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 1)
	public void testSOAPapiAdd() throws IOException {
		
		File file = new File("./src/test/resources/addRequestBody.xml"); // to locate the file
		FileInputStream fileInputStream = new FileInputStream(file); // to read the opened file in bytes stream
		String requestBody = IOUtils.toString(fileInputStream, "UTF-8"); // to convert bytes stream into string format
		
		given()
			.header("Content-Type","text/xml; charset=utf-8")
			.header("SOAPAction","http://tempuri.org/Add")
		
		.when()
			.body(requestBody)
			.post("http://www.dneonline.com/calculator.asmx")
			
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(2000L))
			.header("Content-Type", "text/xml; charset=utf-8")
			.body("//*:AddResult.text()", equalTo("20"))
			.time(Matchers.lessThan(3000L))
			.log().all();
			System.out.println("Addition SOAP API test execution is Completed");
	}
	
	@Feature("SUBTRACTION-SOAP API")
	@Description("This is a SOAP API that uses POST http method")
	@Story("As a user, I can subtract 2 integers to get subtractResult")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority = 2)
	public void testSOAPapiSubtract() throws IOException {
		
		File file = new File("./src/test/resources/subtractRequestBody.xml"); // to locate the file
		FileInputStream fileInputStream = new FileInputStream(file); // to read the opened file in bytes stream
		String requestBody = IOUtils.toString(fileInputStream, "UTF-8"); // to convert bytes stream into string format
		
		given()
			.header("Content-Type","text/xml; charset=utf-8")
			.header("SOAPAction","http://tempuri.org/Subtract")
		
		.when()
			.body(requestBody)
			.post("http://www.dneonline.com/calculator.asmx")
			
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(2000L))
			.header("Content-Type", "text/xml; charset=utf-8")
			.body("//*:SubtractResult.text()", equalTo("10"))
			.time(Matchers.lessThan(3000L))
			.log().all();
			System.out.println("Subtraction SOAP API test execution is Completed");
	}
	
	
	@Feature("MULTIPLY-SOAP API")
	@Description("This is a SOAP API that uses POST http method")
	@Story("As a user, I can multiply 2 integers to get multiplyResult")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority = 3)
	public void testSOAPapiMultiply() throws IOException {
		
		File file = new File("./src/test/resources/multiplyRequestBody.xml"); // to locate the file
		FileInputStream fileInputStream = new FileInputStream(file); // to read the opened file in bytes stream
		String requestBody = IOUtils.toString(fileInputStream, "UTF-8"); // to convert bytes stream into string format
		
		given()
			.header("Content-Type","text/xml; charset=utf-8")
			.header("SOAPAction","http://tempuri.org/Multiply")
		
		.when()
			.body(requestBody)
			.post("http://www.dneonline.com/calculator.asmx")
			
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(2000L))
			.header("Content-Type", "text/xml; charset=utf-8")
			.body("//*:MultiplyResult.text()", equalTo("300"))
			.time(Matchers.lessThan(3000L))
			.log().all();
			System.out.println("Multiplication SOAP API test execution is Completed");
	}
	
	
	@Feature("DIVISION-SOAP API")
	@Description("This is a SOAP API that uses POST http method")
	@Story("As a user, I can divide 2 integers to get divideResult")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority = 4)
	public void testSOAPapiDivide() throws IOException {
		
		File file = new File("./src/test/resources/divideRequestBody.xml"); // to locate the file
		FileInputStream fileInputStream = new FileInputStream(file); // to read the opened file in bytes stream
		String requestBody = IOUtils.toString(fileInputStream, "UTF-8"); // to convert bytes stream into string format
		
		given()
			.header("Content-Type","text/xml; charset=utf-8")
			.header("SOAPAction","http://tempuri.org/Divide")
		
		.when()
			.body(requestBody)
			.post("http://www.dneonline.com/calculator.asmx")
			
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(2000L))
			.header("Content-Type", "text/xml; charset=utf-8")
			.body("//*:DivideResult.text()", equalTo("3"))
			.time(Matchers.lessThan(3000L))
			.log().all();
			System.out.println("Division SOAP API test execution is Completed");
	}	
}