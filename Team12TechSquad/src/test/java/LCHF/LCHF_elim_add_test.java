package LCHF;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import LCHF.commonBaseAtoZ;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.Browser;
import pageobjects.recipes;

public class LCHF_elim_add_test {
	
	public WebDriver driver;
	public  ArrayList<String> elimateLCHF = new ArrayList<>();
	public  List<String> AddListLCHF  = new ArrayList<>();
	public commonBaseAtoZ commonAtoZObj = new commonBaseAtoZ();
	public  int Keto_receipe_count=0;
	
	public static boolean containsEliminatedIngredient(List<String> recipeIngredients, List<String> eliminationList) {
	    for (String ingredient : eliminationList) {
	        if (recipeIngredients.contains(ingredient)) {
	            return true;
	        }
	    }
	    return false;
	}
	public static boolean containsAddIngredient(List<String> recipeIngredients, List<String> AddList) {
	    for (String ingredient : AddList) {
	        if (recipeIngredients.contains(ingredient)) {
	            return true;
	        }
	    }
	    return false;
	}

	//Iterate througough all the urls Collected......
	public void EliminateLCHF_fn(ArrayList<String> AllreceipeUrlList) throws InterruptedException, IOException {
			
		
		elimateLCHF = ExcelReader.ExcelReaderHelper("Final list for LCHFElimination","Eliminate");
		AddListLCHF = ExcelReader.ExcelReaderHelper("Final list for LCHFElimination","Add");
		
		List<String> ingrediantsList = new ArrayList<> ();
		
		for(String eachUrl : AllreceipeUrlList) {
			ingrediantsList.clear();
			System.out.println("Receipe Url : "+eachUrl);
			Thread.sleep(2000);
			driver.navigate().to(eachUrl);
			List<WebElement> recp_ingredns = driver.findElements(By.xpath("//div[@id='rcpinglist']//span"));
			for (WebElement each_ingrediant : recp_ingredns) {
				String ingrediant = each_ingrediant.getText().toLowerCase();
				ingrediantsList.add(ingrediant);
			}
						
			boolean containsEliminatedIngredient = containsEliminatedIngredient(ingrediantsList, elimateLCHF);
			
			// Print the result
			try {
				if(!containsEliminatedIngredient) {
					boolean containsAddIngredient = containsAddIngredient(ingrediantsList, AddListLCHF);
					
					System.out.println("****** "+AddListLCHF);
					if(containsAddIngredient) {
						 Keto_receipe_count= Keto_receipe_count+1;
						//System.out.println("The recipe does not contain any ingredients from the elimination list.");
			           
						String receipe_Name = driver.findElement(By.xpath("//span[@id='ctl00_cntrightpanel_lblRecipeName']")).getText();
						String prep_time = driver.findElement(By.cssSelector("time[itemprop='prepTime']")).getText();
						String cook_time = driver.findElement(By.cssSelector("time[itemprop='cookTime']")).getText();
						String tags = driver.findElement(By.xpath("//div[@id='recipe_tags']/a/span")).getText();
						System.out.println("**Receipe Name:  " + receipe_Name);
						System.out.println("**prep_time:  " + prep_time);
						System.out.println("**cook_time:  " + cook_time);
						System.out.println("**tags:  " + tags);
						
			            
					}else {
						System.out.println("Ingrediants not in add List");
					}            
				} else {
	        	System.out.println("The recipe contain any ingredients from the elimination list.");
	            
				}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			
			System.out.println("No: of LCHF(Keto) receipe scrapped: "+Keto_receipe_count);
			}
			//}
			
			
			
		
		}
	
/*	public  ArrayList readLCHFEliminateData() throws IOException {
		
		String excelPath = "C:\\Users\\anoop\\Downloads\\IngrediantsEliminate.xlsx";
	    FileInputStream inputStream = new FileInputStream(excelPath);
	    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
	    XSSFSheet sheet = workbook.getSheet("LCHFElimination ");  
	    int rows = sheet.getLastRowNum();
	    for (int r = 2; r <= rows; r++) {
	        Row row = sheet.getRow(r);
	        Cell cell = row.getCell(0);
	        if (cell != null && cell.getCellType() != CellType.BLANK) {
	            String value = cell.getStringCellValue();
	            String[] splitArray = value.split(",");
	            for (String substring: splitArray) {
	            	elimateLCHF.add(substring.trim().toLowerCase());
	            	
	            }
	
	        }
	    }
	    return elimateLCHF;
	}
	*/
		 
	}

