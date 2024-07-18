package Utility;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.google.common.collect.Table.Cell;
import com.microsoft.schemas.office.visio.x2012.main.CellType;
public class DataDriven {
	static String path;
	
@Test
	public static List<String> reader(String Sheet_name, String Col_name) throws IOException { 
	 List<String> list = new ArrayList<String>();	
    path=PropertyFunction.getPath();
	FileInputStream file = new FileInputStream(path);
	XSSFWorkbook workbook=new XSSFWorkbook(file);
	 //System.out.println(path);
	int count=workbook.getNumberOfSheets();

	for(int k=0;k<count;k++) {
	if(workbook.getSheetName(k).equalsIgnoreCase(Sheet_name)) {//irrespective of case(upper/lower)

	    XSSFSheet sheet=workbook.getSheetAt(k);
	    DataFormatter dataf = new DataFormatter();
	    int rows=sheet.getLastRowNum();// count of rows
        int cols=sheet.getRow(1).getLastCellNum();// count of cells
        
        for(int j=0;j<cols;j++) {
         XSSFRow first_row = sheet.getRow(1);
         
        if(dataf.formatCellValue(first_row.getCell(j)).equalsIgnoreCase(Col_name)) //reachs specific column
        {	
	    for(int i=2; i<=rows;i++) {
        XSSFRow row = sheet.getRow(i);// reads each row under column header
        if (row != null) {
			XSSFCell cell = row.getCell(j);
			if (cell != null) {
				switch (cell.getCellType()) {
				case STRING:
				list.add(cell.getStringCellValue().toLowerCase());
					break;
				default:
					// System.out.println("Cell type not supported.");
				}
			} else {
				System.out.println("Cell is empty.");
			}

	   }}}

workbook.close();
	   }
        }}
	//System.out.println(list);
return list;	}
}

