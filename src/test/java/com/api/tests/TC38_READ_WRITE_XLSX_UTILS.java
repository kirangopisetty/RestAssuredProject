package com.api.tests;

import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TC38_READ_WRITE_XLSX_UTILS {

	String xlfile = "C:\\Users\\Kiran\\Downloads\\REST ASSURED-API AUTOMATION TESTING\\createUserTestData.xlsx";
	String xlsheet = "Sheet1";
	int rowNum = 1;
	int colNum=2;
	
	@Test
	public void getRowsCount() throws IOException {		// let us get the # of rows
		
		FileInputStream fi = new FileInputStream(xlfile);  // opens a new connection with the file specified
		XSSFWorkbook wb = new XSSFWorkbook(fi);				// opens the specified xlsx file
		XSSFSheet ws = wb.getSheet(xlsheet);			// switch to Sheet1
		int rowsCount = ws.getLastRowNum();
		System.out.println("# of Rows >> "+rowsCount);
		
		int rowsCountWithHeader = ws.getPhysicalNumberOfRows();
		System.out.println("# of Rows with header >> "+rowsCountWithHeader);
		
	//	wb.close();
		fi.close();
	}
	
	@Test
	public void getColumnsCount() throws IOException {		// let us get the # of columns
		
		FileInputStream fi = new FileInputStream(xlfile); 	// locate the file
		XSSFWorkbook wb = new XSSFWorkbook(fi);				// open the file
		XSSFSheet ws = wb.getSheet(xlsheet);				// switch to the sheet specified
		XSSFRow row = ws.getRow(rowNum);							// pointing to the row specified
		int columnsCount = row.getLastCellNum();			// get the # of columns in row#1
		System.out.println("# of Columns >> "+columnsCount);
		
	//	wb.close();
		fi.close();
	}
	
	@Test
	public void getCellData() throws IOException {
		
		FileInputStream fi = new FileInputStream(xlfile); 	// locate the file
		XSSFWorkbook wb = new XSSFWorkbook(fi);				// open the file
		XSSFSheet ws = wb.getSheet(xlsheet);				// switch to the sheet specified
		XSSFRow row = ws.getRow(rowNum);							// pointing to the row specified
		XSSFCell cell = row.getCell(colNum);			
		String cellData = cell.getStringCellValue();		// retrieve the cell content
		
	/*	DataFormatter formatter = new DataFormatter(); // this is used to read cell data irrespective of the cell datatype
		String cellData = formatter.formatCellValue(cell);
		return cellData;
	*/	
		
		System.out.println("The cell data is >> "+cellData);
	//	wb.close();
		fi.close();
	}
	
	@Test
	public void setCellData() throws IOException {
		
		FileInputStream fi = new FileInputStream(xlfile); 	// locate the file
		XSSFWorkbook wb = new XSSFWorkbook(fi);				// open the file
		XSSFSheet ws = wb.getSheet(xlsheet);				// switch to the sheet specified
		XSSFRow row = ws.getRow(rowNum);							// pointing to the row specified
		XSSFCell cell = row.createCell(4);
		cell.setCellValue("KiranTrainer");
		FileOutputStream fo = new FileOutputStream(xlfile);  // privileges to write into the file specified
		wb.write(fo);										// write into the workbook using fo object
		fi.close();
		fo.close();
	}
}