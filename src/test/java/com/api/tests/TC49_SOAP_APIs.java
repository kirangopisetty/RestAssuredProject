package com.api.tests;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static org.hamcrest.Matchers.equalTo;

public class TC49_SOAP_APIs {
	
	@Test
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
			.log().all();
	}
	
	@Test
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
			.log().all();
	}
	
	
	@Test
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
			.log().all();
	}
	
	
	@Test
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
			.log().all();
	}	
}