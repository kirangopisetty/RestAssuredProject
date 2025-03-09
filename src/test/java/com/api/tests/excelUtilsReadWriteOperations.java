package com.api.tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


public class excelUtilsReadWriteOperations {
	
	String xlfile = "C:\\Users\\Kiran\\Downloads\\REST ASSURED TESTING\\testData.xlsx";
	String xlsheet = "Sheet1";
	int rowNum = 1;
	int colNum=2;
	@Test
	public void getRowsCount() throws IOException {
		
		FileInputStream fi = new FileInputStream(xlfile); // opens a new connection with the specified file
		XSSFWorkbook wb = new XSSFWorkbook(fi);	// opening the file
		XSSFSheet ws = wb.getSheet(xlsheet);	// pointing to the specified sheet in the opened workbook
		int rowCount = ws.getLastRowNum();
		int rowCountwithHeader = ws.getPhysicalNumberOfRows();
		System.out.println("# of rows : "+rowCount);
		System.out.println("# of rows with header : "+rowCountwithHeader);
		wb.close();
		fi.close();
	}
	
	@Test
	public void getColumnsCount() throws IOException {
		
		FileInputStream fi = new FileInputStream(xlfile);
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet ws = wb.getSheet(xlsheet);
		XSSFRow row = ws.getRow(1);
		int columnCount = row.getLastCellNum();
		System.out.println("# of columns : "+columnCount);
		wb.close();
		fi.close();	
	}
	
	@Test
	public void getCellData() throws IOException {
		
		FileInputStream fi = new FileInputStream(xlfile);
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet ws = wb.getSheet(xlsheet);
		XSSFRow row = ws.getRow(rowNum);
		XSSFCell cell = row.getCell(colNum);
		String cellData = cell.getStringCellValue();
		
	/*	DataFormatter formatter = new DataFormatter(); // this is used to read cell data irrespective of the cell datatype
		String cellData = formatter.formatCellValue(cell);
		return cellData;
	*/	
		System.out.println("cell Data : "+cellData);
		wb.close();
		fi.close();
	}
	
	@Test
	public void setCellData() throws IOException {
		
		FileInputStream fi = new FileInputStream(xlfile);
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet ws = wb.getSheet(xlsheet);
		XSSFRow row = ws.getRow(rowNum);
		XSSFCell cell = row.createCell(4);
		cell.setCellValue("Automation");
		FileOutputStream fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}	
}