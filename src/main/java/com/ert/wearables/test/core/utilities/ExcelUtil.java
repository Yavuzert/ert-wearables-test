package com.ert.wearables.test.core.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static Workbook book;
	public static Sheet sheet;
	
	public static String TESTDATA_SHEET_DATA = "C:\\Users\\yavuz.ozturk\\eclipse-workspace\\com.ert.wearables.test\\src\\main\\java\\com\\ert\\wearables\\test\\core\\testdata\\data.xlsx";
	
	public static Object[][] getTestData(String sheetName) {
		
		try {
			FileInputStream ip = new FileInputStream(TESTDATA_SHEET_DATA);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			
			Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i = 0; i < sheet.getLastRowNum(); i++) {
				
				for(int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					
					data[i][k] = sheet.getRow(i+1).getCell(k).toString();
				}
			}
			return data;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
