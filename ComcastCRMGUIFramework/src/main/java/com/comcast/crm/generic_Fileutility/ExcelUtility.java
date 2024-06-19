package com.comcast.crm.generic_Fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String getDataFromExcel(String sheetName,int rownum,int celnum) throws EncryptedDocumentException, IOException {
		
		FileInputStream efis= new FileInputStream("./testData/Book1.xlsx");
		Workbook wb= WorkbookFactory.create(efis);
		String data = wb.getSheet(sheetName).getRow(rownum).getCell(celnum).getStringCellValue();
		
		return data;
	}
	
	public int getRowCount(String sheetName) throws Throwable {

		FileInputStream efis= new FileInputStream("./testData/Book1.xlsx");
		Workbook wb= WorkbookFactory.create(efis);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		return rowCount;
		
	}
	
	public void setDataIntoExcel(String sheetName,int rownum,int celnum) throws Throwable {
		
		FileInputStream efis= new FileInputStream("./testData/Book1.xlsx");
		Workbook wb= WorkbookFactory.create(efis);
		wb.getSheet(sheetName).getRow(rownum).getCell(celnum);
		
		FileOutputStream fos = new FileOutputStream("./testData/Book1.xlsx");
		wb.write(fos);
		wb.close();
		
	}
}
