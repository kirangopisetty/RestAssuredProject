package com.api.tests;

import java.io.*;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	public static int getRowCount(String xlfile, String xlsheet) throws IOException

	{
		FileInputStream fi = new FileInputStream(xlfile);
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet ws = wb.getSheet(xlsheet);
		int rowCount=ws.getLastRowNum();
		// or 
		// int rowCountWithHeaders=ws.getPhysicalNumberOfRows();
	
		wb.close();
		fi.close();
		return rowCount;
	}
	
	public static int getCellCount(String xlfile, String xlsheet, int rowNum) throws IOException

	{
		FileInputStream fi = new FileInputStream(xlfile);
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet ws = wb.getSheet(xlsheet);
		XSSFRow row=ws.getRow(rowNum);
		int cellCount=row.getLastCellNum();
		
		wb.close();
		fi.close();
		return cellCount;
	}
	
	public static String getCellData(String xlfile, String xlsheet, int rowNum, int colNum) throws IOException

	{
		FileInputStream fi = new FileInputStream(xlfile);
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet ws = wb.getSheet(xlsheet);
		XSSFRow row=ws.getRow(rowNum);
		XSSFCell cell=row.getCell(colNum);
	//	String cellData = cell.getStringCellValue(); use this if cell data is a string data type
	//	int cellData = cell.getNumericCellValue();	use this if cell data is a int data type
		DataFormatter formatter = new DataFormatter();
		String cellData = formatter.formatCellValue(cell); // use this if cell data is is of any data type
		
		wb.close();
		fi.close();
		return cellData;
	}
	
	public static void setCellData(String xlfile, String xlsheet, int rowNum, int colNum, String data) throws IOException

	{
		FileInputStream fi = new FileInputStream(xlfile);
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet ws = wb.getSheet(xlsheet);
		XSSFRow row=ws.getRow(rowNum);
		XSSFCell cell=row.createCell(colNum);
		cell.setCellValue(data);
		FileOutputStream fo=new FileOutputStream(xlfile);
		
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
}