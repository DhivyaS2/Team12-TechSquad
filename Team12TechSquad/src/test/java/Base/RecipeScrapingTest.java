	package Base;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Utility.ExcelReader;
import pageobjects.HomePage;
import database_conn.Jdbc_recipedb;

public class RecipeScrapingTest {
	
	private HomePage homepage;
	private Jdbc_recipedb db;
	private WebDriver driver;
	String RecipeID;
	String RecipeName;
	String RecipeCategory;
	String CusineCategory;
	String NoOfServings;
	 String Preparationmethod;
	 String nutrientvalues;
	 String FoodCategory;
	 List<String> Ingredients = new ArrayList<>();
	List<String> excel_list;
	@BeforeClass
	public void setup() throws IOException, ClassNotFoundException, SQLException
	{
		Browser b= new  Browser();
	    b.beforescraping();
	    b.launchsite();
	   // excel_list=ExcelReader.reader(); 
	    homepage=new HomePage();
	    db=new Jdbc_recipedb();
	    db.connect();
	    
	    //db.create_table();
}
	
	@Test
	public void testgetrecipes() throws InterruptedException, IOException
	{
		
		int maxpage;
		ArrayList<String>arrayallurls=new ArrayList<>();
		homepage.recipeall();
		
		
		
		//***************
		
	
			
		List<String> allRecipeUrls = new ArrayList<>();
//	   
//		List<WebElement> paginationLinks = homepage.returnpages();
//	    maxpage=paginationLinks.size();
	    
	    //System.out.println("maximum size:"+maxpage);
	    

	    List<String> recipeUrls = homepage.getrecipeurls();
	    	        arrayallurls.addAll(recipeUrls);
	    	        Thread.sleep(2000);
	    	    
	

	   for (String url : arrayallurls) {
                //System.out.println("Navigating to URL: " + url);
                homepage.navigatetorecipedetailspage(url);
                
                
                

        List<String>elminlist=homepage.getleliminationList();
      
        ArrayList<String>Elminationitems=new ArrayList<>(elminlist);
        
            List<String> ingredientsList = homepage.getingredientsrecipe(url);
            ArrayList<String> arrayingredients=new ArrayList<>(ingredientsList);
            boolean containsElimination = false;
            for(String ingredients:arrayingredients)
            for(String Elminationlist: Elminationitems ) {
            	
            	
            	{
            		 if (ingredients.contains(Elminationlist)) {
            		//if (ingredients.toLowerCase().contains(excelItem .toLowerCase())) {
                         containsElimination = true;
                         break;
            	}
            }
            	if (containsElimination) {
                    break;
                }
            }

           
            if (containsElimination) {
            	
                
                System.out.println("Contains elimination list. Skipping details."+url);
                }
             else {
            	 
            	 //1:Recipe ID
            	 	String RecipeID=homepage.extractrecpieno(url);
            	 	System.out.println("Recipe ID:"+RecipeID);
            	 //2:RecipeName
            		try
            	    {
            	 	RecipeName=homepage.getrecipename();
            	    }catch (NoSuchElementException e)
            		{
            	    	RecipeName="N/A";
            	    	System.out.println("No name Found " + url);
            		}
            		System.out.println("Recipe Title: " + RecipeName);
            		
            		//3RecipeCategory
            		
            		try
            	    {
            	 	RecipeCategory=homepage.getrecipecatgories();
            	    }catch (NoSuchElementException e)
            		{
            	    	RecipeCategory="N/A";
            	    	System.out.println("No category Found " + url);
            		}
            		System.out.println("Recipe Category: " + RecipeCategory);
            		
            		
           		
//            		
            		//4Food Category(Veg/non-veg/vegan/Jain)
            		
            		try
            	    {
            	 	FoodCategory=homepage.getfoodcatgories();
            	    }catch (NoSuchElementException e)
            		{
            	    	FoodCategory="N/A";
            	    	System.out.println("No category Found " + url);
            		}
            		System.out.println("Food Category: " + FoodCategory);
//            		
//            		//5 Ingredients
            		System.out.println("Ingredients for " + url + ":");
                    for (String ingredient : arrayingredients) {
                        System.out.println("- " + ingredient);
            		
            		String Ingredients = "";
            		List<WebElement>ingelement=homepage.gettheingredients();
            		for(WebElement inglist:ingelement)
            		
        			try
            		{
            		Ingredients=inglist.getText().trim();
            		}catch (NoSuchElementException e) {
            			Ingredients = "N/A"; // Default value when preparation time is not found
            		  System.out.println("No Ingredients Found " + url);
            		  
            		}
            		
            		System.out.println("Ingredients:"+Ingredients);

            		
            		
            		
            		
//            		//6 & 7  preperation and cooking time 
            		String preparationTime;
                    try {
                        preparationTime = homepage.getpreptime();
                    } catch (NoSuchElementException e) {
                        preparationTime = "N/A"; // Default value when preparation time is not found
                        System.out.println("Preparation Time not found for " + url);
                    }
                    System.out.println("Preparation Time: " + preparationTime);
                    String cookingTime;
                    try {
                        cookingTime = homepage.getcookingtime();
                    } catch (NoSuchElementException e) {
                        cookingTime = "N/A"; // Default value when cooking time is not found
                        System.out.println("Cooking Time not found for " + url);
                    }

                   System.out.println("Cooking Time: " + cookingTime);
                    
                    
                    // 8 Tags
            	    List<WebElement>tagelements=homepage.getrecipetags();
            	    String Tags = "";
            	    for(WebElement taglist:tagelements)
            	    	
            	    	try
            	    {
            	    Tags=taglist.getText().trim();
            	    }catch (NoSuchElementException e) {
            	    	Tags = "N/A"; // Default value when preparation time is not found
            	      System.out.println("No Tags Found " + url);
            	      
            	 }
             
            	   System.out.println("Tags:"+Tags);
           
            		
          	   
            	 //9.servings
            	   try
           	    {
            		   NoOfServings=homepage.gettotalservings();
           	    }catch (NoSuchElementException e) {
           	    	NoOfServings = "N/A"; // Default value when preparation time is not found
          	      System.out.println("No servingsize Found " + url);
           	    }
                System.out.println("No Of Servings:"+NoOfServings);
                
                //10.Cuisine category
               
                try
        	    {
        	 	CusineCategory=homepage.getcusinelist();
        	    }catch (NoSuchElementException e)
        		{
        	    	CusineCategory="N/A";
        	    	System.out.println("No category Found " + url);
        		}
        		System.out.println("Cusine Category: " + CusineCategory);
                
              //11 Recipe Description
                String RecipeDescription;
                try {
                	RecipeDescription=homepage.getrecipedescription();
                }catch (NoSuchElementException e)
               		 {
                	RecipeDescription = "N/A"; // Default value when preparation time is not found
                    System.out.println("Recipe Decription not found for " + url);
                }
                System.out.println("Recipe Description:"+RecipeDescription);
                
             //12. Preperation method
              try {
            	  Preparationmethod=homepage.getpreperationmethod();
              }catch(Exception e)
              {
            	  Preparationmethod = "N/A";
             	   System.out.println("No prepmethod Found for " + url);
              }
               System.out.println("Preperation Methos:"+Preparationmethod);
                
//             //13. nutrition
               try {
            	   nutrientvalues=homepage.getnutritionvalues();
               }catch (NoSuchElementException e) {
            	   nutrientvalues = "N/A"; // Default value when cooking time is not found
                   System.out.println("Nutrition values not found for " + url);
               }
 	    System.out.println("Nutrient values:"+nutrientvalues); 
                
                
//            	  14.Recipeurl
            	   System.out.println("Recipe URL:"+url);
            	   
            	// Handle the alert if it appears during the loop
   	            try {
   	                Alert alert = Browser.driver.switchTo().alert();
   	             System.out.println("Unexpected alert:" + alert.getText());
   	                alert.dismiss(); 
   	            } catch (NoAlertPresentException ex) {
   	            	System.out.println("No alert present.");
   	            }
            	   
    
             	   try {
					db.insert_table(RecipeID,RecipeName,RecipeCategory,FoodCategory,Ingredients,preparationTime,cookingTime,Tags,NoOfServings,CusineCategory,RecipeDescription,Preparationmethod,nutrientvalues,url);
             		   //db.insert_table(RecipeID,Ingredients);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


                 }
             }         
          	}
                  }
}
    

 