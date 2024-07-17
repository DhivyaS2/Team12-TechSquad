package LCHF;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;
import Base.Browser;
import pageobjects.LCHF_PageObjects;
import Utility.ExcelReader;
import Utility.LoggerLoad;


public class LCHFRecipes {
	
	private  ArrayList<String> elimateLCHF = new ArrayList<>();
	private  ArrayList<String> AddListLCHF  = new ArrayList<>();
	private ArrayList<String> CuisineList = new ArrayList<>();
	public static final ArrayList<String> AllrecipeUrlList = new ArrayList<>();
	public LCHF_PageObjects LCHFpgObj;
	private int Keto_recipe_count=0;
	public CRUD_DB db= new CRUD_DB();
	
	@BeforeTest
	public void launch() throws IOException, ClassNotFoundException, SQLException {
		
		Browser b= new  Browser();
	    b.beforescraping();
	    b.launchsite();
	    LCHFpgObj = new LCHF_PageObjects();
	    CRUD_DB db= new CRUD_DB();
	    db.connect();
	    db.create_table();
	    LoggerLoad.logInfo("LCHF Before Test Excecuted");
	  }
	 
	
	//Iterating through A to Z and storing the URLs in an Arraylist
	//@Test(priority=0)
	 public  void AScrappingAtoZ() throws InterruptedException, IOException {

		System.out.println("Title "+ Browser.driver.getTitle());
		AllrecipeUrlList.clear();
		WebDriverWait wait = new WebDriverWait(Browser.driver, Duration.ofSeconds(60));		
		try {
		// Loop through each letter from A to Z
		for (char letter = 'A'; letter <='Z'; letter++) {   	
			
			WebElement AtoZ = Browser.driver.findElement(By.xpath("//a[@title='Recipea A to Z']"));
			//WebDriverWait waitAtoZ = new WebDriverWait(Browser.driver, Duration.ofSeconds(60));
			//waitAtoZ.until(ExpectedConditions.elementToBeClickable(AtoZ)).click();
			Actions actions = new Actions(Browser.driver);
            actions.moveToElement(AtoZ).click().perform();
            
	        // Locate the link for the current letter
	    	System.out.println("Current Alphabet is : "+letter);
	        String letterXpath = String.format("//a[normalize-space()='%s']", letter);
	        WebElement letterLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(letterXpath)));
	        
	        // Click the letter link
	        letterLink.click();
	       
	        WebElement currentPage = LCHFpgObj.getcurrntPage();
	        boolean hasNextPage = true;
	        int pageCount =0;   
	    	while (hasNextPage) {
	        try {
	        	pageCount= pageCount+1;
	            //List<WebElement> CurrPageRecipeList = Browser.driver.findElements(By.xpath("//*[@class ='rcc_recipecard']"));
	            System.out.println("No: of recipes in currentPage:" + LCHFpgObj.getCurrPage_linksCount());
	               List <WebElement> urlLinks = Browser.driver.findElements(By.xpath("//span[@class='rcc_recipename']/a"));
	                for (WebElement webElementlink : urlLinks) {
						AllrecipeUrlList.add(webElementlink.getAttribute("href"));	
	                }    
	            try {
	            	WebDriverWait waitNP = new WebDriverWait(Browser.driver, Duration.ofSeconds(60));
	                WebElement nextPage = Browser.driver.findElement(By.xpath("//*[@class='rescurrpg']/following-sibling::a"));
	                waitNP.until(ExpectedConditions.elementToBeClickable(nextPage)).click();
	                wait.until(ExpectedConditions.stalenessOf(currentPage));
	                currentPage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='rescurrpg']")));
	            } catch (Exception e) {
	                hasNextPage = false;
	                LoggerLoad.logError("Exception captured :"+e.getMessage());
	    	    	
	            }
	        }catch (UnhandledAlertException e) {
	            	// Handle the alert if it appears during the loop
	            try {
	                Alert alert = Browser.driver.switchTo().alert();
	                LoggerLoad.logError("Unexpected alert:" + alert.getText());
	                alert.dismiss(); 
	            } catch (NoAlertPresentException ex) {
	            	LoggerLoad.logError("No alert present.");
	            }
	            hasNextPage = false;
	        } 
	    }// loop ending of All pages in an Alphabet
	    LoggerLoad.logInfo("No: of Pages in letter : "+letter+" is : "+pageCount);
    	
	} // loop ending of All Alphabet 
		LoggerLoad.logInfo("Sucessfully Iterated from A to Z");
    
	  }catch(Exception e) {
		  LoggerLoad.logError("Exception captured while looping A to Z:"+e.getMessage());
	    	e.printStackTrace();
	    }
	}
	
//**************************************************************************
	
	//Validation for the Ingredients from Eliminate & Add List from excel.
	public static boolean LCHFChckIngredientList(List<String> recipeIngredients, List<String> ExcelList) {
	    for (String ingredient : ExcelList) {
	        if(recipeIngredients.stream().anyMatch(s -> s.toLowerCase().contains(ingredient))){
	            return true;
	        }
	    }
	    return false;
	}
	
	//Categorize CuisineCategory 
	public static String checkCuisineCategory(List<String> tagsList, List<String> CuisineList) {		
		String category ="";
		for (String cuisine : CuisineList) {
	    	if(tagsList.stream().anyMatch(s -> s.toLowerCase().contains(cuisine))){
	    		category = cuisine;
	        }else {
	        	category = "N/A";
	        }
	    }
		return category;
	}
	
    //Categorize fodCategory
    public String foodCategory(ArrayList<String> tagsL) {
        String category = "";
        if (tagsL.stream().anyMatch(s -> s.toLowerCase().contains("jain")) ) {
            category = "Jain";
        }else if (tagsL.stream().anyMatch(s -> s.toLowerCase().contains("egg"))) {
            category = "Eggitarian";
        }else if (tagsL.stream().anyMatch(s -> s.toLowerCase().contains("non-veg"))) {
            category = "Non-Veg";
        }else if (tagsL.stream().anyMatch(s -> s.toLowerCase().contains("vegan"))) {
            category = "Vegan";
        }else if (tagsL.stream().anyMatch(s -> s.toLowerCase().contains("vegetarian")) || tagsL.stream().anyMatch(s -> s.toLowerCase().contains("veg"))) {
            category = "Vegetarian";
        }else {
        	category = "N/A";
        }
        return category;
    }
    
    //Categorize recipe Category
    public String recipeCategory(ArrayList<String> tagsL) {
        String category = "";

        if (tagsL.stream().anyMatch(s -> s.toLowerCase().contains("breakfast"))) {
            category = "Breakfast";
        } else if (tagsL.stream().anyMatch(s -> s.toLowerCase().contains("lunch"))) {
            category = "Lunch";
        } else if (tagsL.stream().anyMatch(s -> s.toLowerCase().contains("dinner"))) {
            category = "Dinner";
        } else if ((tagsL.stream().anyMatch(s -> s.toLowerCase().contains("snacks"))) || (tagsL.stream().anyMatch(s -> s.toLowerCase().contains("snack")))) {
            category = "Snacks";
        }else {
        	category = "N/A";
        }

        return category;
    }

	@Test(priority=1)
	//Iterate through all the URLs Collected and Load the Table.
	public void EliminateLCHF_fn() throws InterruptedException, IOException {
			
		System.out.println("Test Reached Eliminate function----------");
		elimateLCHF = ExcelReader.ExcelReaderHelper("Final list for LCHFElimination ","Eliminate");
		AddListLCHF = ExcelReader.ExcelReaderHelper("Final list for LCHFElimination ","Add");
		CuisineList = ExcelReader.ExcelReaderHelper("Category_list","cuisinecategory");
		
		ArrayList<String> ingrediantsList = new ArrayList<> ();
		ArrayList<String> tagsList = new ArrayList<> ();
		
		System.out.println("Total URLs Collected is : "+AllrecipeUrlList.size());
		LoggerLoad.logInfo("Total URLs Collected is : "+AllrecipeUrlList.size());
		int checkCount=0;
		for(String eachUrl : AllrecipeUrlList) {	
			ingrediantsList.clear();
			Browser.driver.get(eachUrl);
			try {
			Thread.sleep(2000);
			ingrediantsList = LCHFpgObj.getIngrediant();				
			}catch (UnhandledAlertException e) {
	            // Handle the alert if it appears during the loop
	            try {
	                Alert alert = Browser.driver.switchTo().alert();
	                LoggerLoad.logError("Unexpected alert: " + alert.getText());
	                alert.dismiss();
	            } catch (NoAlertPresentException ex) {
	            	LoggerLoad.logError("No alert present.");
	            }
			}
			boolean containsEliminatedIngredient = LCHFChckIngredientList(ingrediantsList, elimateLCHF);
			try {
				if(!containsEliminatedIngredient) {
					boolean containsAddIngredient = LCHFChckIngredientList(ingrediantsList, AddListLCHF);
					
					if(containsAddIngredient) {
						try {
						String servings=""; String nutrients =""; String tags=""; String methods="";
						String desc ="";String prep_time =""; String cook_time="";
						String recipe_ingrediants = ingrediantsList.stream().map(Object::toString)
									.collect(Collectors.joining(", "));		
						String id = LCHFpgObj.getRecipeID(eachUrl);
						
						String name = LCHFpgObj.getRecipeName();
						LoggerLoad.logError("**Name :"+name);
						try {
							tagsList = LCHFpgObj.getTags();
						}catch(Exception e) {
							tags = "N/A";
						}
						String foodCategory = foodCategory(tagsList);
						String recipeCategory = recipeCategory(tagsList);
						tags = tagsList.stream().map(Object::toString)
								.collect(Collectors.joining(", "));
						try {
							prep_time = LCHFpgObj.getPreptime();
						}catch(NoSuchElementException e) {
							prep_time="N/A";
							e.printStackTrace();
						}
						try {
							cook_time = LCHFpgObj.getCooktime();
						}catch(NoSuchElementException e) {
							cook_time="N/A";
							e.printStackTrace();
						}								
						try {
							servings = LCHFpgObj.getNoServings();
						}catch(NoSuchElementException e) {
							servings = "N/A";
							e.printStackTrace();
						}
						System.out.println("Recipe Url : "+eachUrl);
						System.out.println("**Recipe  ID :"+id);
						System.out.println("**Name :"+name);
						System.out.println("**No: servings:  " + servings);
						System.out.println("**tags----:"+tags);
						System.out.println("**foodcategory----:"+foodCategory);
						System.out.println("**recipecategory----:"+recipeCategory);
						//ArrayList<String>breadcrumpCuisineList = LCHFpgObj.getCuisine();
						//System.out.println("Cuisines-- :"+breadcrumpCuisineList);
						String cuisineCategory = checkCuisineCategory(tagsList,CuisineList);
						System.out.println("**Cuisine Category :"+cuisineCategory);
						try {
							desc = LCHFpgObj.getRecipeDesc();
						}catch(NoSuchElementException e) {
							desc = "N/A";
							e.printStackTrace();
						}
						try {
							methods = LCHFpgObj.getRecipeMothod();
						}catch(NoSuchElementException e) {
							methods = "N/A";
							e.printStackTrace();
						}
						try {
							nutrients = LCHFpgObj.getNutrientsList();
						}catch(NoSuchElementException e) {
							nutrients = "N/A";
							e.printStackTrace();
						}
						Keto_recipe_count= Keto_recipe_count+1;
						System.out.println("-------------------------------------------------END-------------------------------------------");
						System.out.println("Current No of Keto recipe is : "+Keto_recipe_count);
						try {
							db.insert_table(id,name,recipeCategory,foodCategory,recipe_ingrediants,prep_time,cook_time,tags,servings,cuisineCategory,desc,methods,nutrients,eachUrl);
							LoggerLoad.logInfo("Sucessfully inserted a LCHF(Keto)Receipe");
						} catch (Exception e) {
							e.printStackTrace();
							LoggerLoad.logError(e.getMessage());
						}
					}catch (Exception e) {
						e.printStackTrace();
						LoggerLoad.logError(e.getMessage());
					}
					}else {
						System.out.println("Recipe ingrediant is not present in add list");
					}            
				} else {
	        	System.out.println("The recipe contain an ingredient from the elimination list.");
	            
				}
				}catch(Exception e){
					e.printStackTrace();
					LoggerLoad.logError("Exception caught while validating Eliminate & Add List :"+e.getMessage());
				}
			checkCount= checkCount+1;
		} // end of eachURL Iteration	
		LoggerLoad.logInfo("Number of Recipe Url Scanned :"+checkCount);
		LoggerLoad.logInfo("Total No: of LCHF(Keto) recipe scrapped: "+Keto_recipe_count);
		
	} 
	
}
