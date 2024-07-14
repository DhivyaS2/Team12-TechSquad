package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Utility.CommonFunction;

public class ExcelReader {

	public static ArrayList<String> ExcelReaderHelper(String sheetName, String columnName) {

		ArrayList<String> excelList = new ArrayList<String>();

		System.out.println("Inside ExcelReader");
		//String excelFilePath = "C:\\MyProject\\IngredientsAndComorbidities-ScrapperHackathon.xlsx";
		// String sheetName = "Final list for LCHFElimination "; // Name of the sheet
		// String columnName = "Add"; // Name of the column
		String excelFilePath="";
		try {
			excelFilePath = CommonFunction.getexcelfilepath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try (FileInputStream fis = new FileInputStream(excelFilePath); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				System.out.println("Sheet with name " + sheetName + " not found.");
				return new ArrayList<String>();
			}

			// Find the column index based on column name
			Row headerRow = sheet.getRow(1);
			int columnIndex = -1;
			for (Cell cell : headerRow) {
				if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
					columnIndex = cell.getColumnIndex();
					break;
				}
			}

			if (columnIndex == -1) {
				System.out.println("Column with name " + columnName + " not found.");
				return new ArrayList<String>();
			}

			// Read the values from the specified column
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					Cell cell = row.getCell(columnIndex);
					if (cell != null) {
						switch (cell.getCellType()) {
						case STRING:
							excelList.add(cell.getStringCellValue().toLowerCase());
							break;
						default:
							// System.out.println("Cell type not supported.");
						}
					} else {
						System.out.println("Cell is empty.");
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error ->" + e.getMessage());
		}

		System.out.println("Done with ExcelReader");

		return excelList;
	}

}
