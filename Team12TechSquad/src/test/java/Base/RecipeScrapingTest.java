package Base;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.HomePage;

public class RecipeScrapingTest {
	
	private HomePage homepage;
	private WebDriver driver;
	
	@BeforeClass
	public void setup() throws IOException
	{
		Browser b= new  Browser();
	    b.beforescraping();
	    b.launchsite();
	    homepage=new HomePage();
	    
	}
	///List<WebElement> list = homepage.collectrecipes();
			//ArrayList<WebElement> arrayList = new ArrayList<>(list);
	        //List<String> Titles = new ArrayList<>();
	        
	        //List<String> allRecipeUrls = new ArrayList<>();
	@Test
	public void testgetrecipes() throws InterruptedException, IOException
	{
		ArrayList<String>arrayallurls=new ArrayList<>();
		homepage.recipeall();
		int maxpage;
		List<String> allRecipeUrls = new ArrayList<>();
	   
		
	//int pageNumber=1;
		//for (char index = 'A'; index <= 'Z'; index++) {
//	        String indexString = String.valueOf(index);
//	        homepage.clickAlphabeticalIndex(indexString);
	       

	        
	    List<WebElement> paginationLinks = homepage.returnpages();
	    maxpage=paginationLinks.size();
	    
	    System.out.println("maximum size:"+maxpage);
	    
	    for(int pageno=22;pageno<=maxpage;pageno++)
	    {
	    List<String> recipeUrls = homepage.getrecipeurls(pageno);
	    	        arrayallurls.addAll(recipeUrls);
	    	        Thread.sleep(2000);
	    	        //System.out.println("Collected " + arrayallurls.size() + " URLs from page " + pageNumber);
	    	        //System.out.println(arrayallurls);
	    	       // Thread.sleep(2000);
	    }

		
//		System.out.println("Collected " + arrayallurls.size() );
//        System.out.println(arrayallurls);
	

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
            		 if (ingredients.toLowerCase().contains(Elminationlist.toLowerCase())) {
                         containsElimination = true;
                         break;
            	}
            }
            	if (containsElimination) {
                    break;
                }
            }

           
            if (containsElimination) {
            	
                
                System.out.println("Contains elimination list. Skipping details.");}
             else {
            	 
            	 //1:Recipe ID
            	 	String RecipeID=homepage.extractrecpieno(url);
            	 	System.out.println("Recipe ID:"+RecipeID);
            	 //2:RecipeName
            	 	String RecipeName=homepage.getrecipename();
            	    System.out.println("Recipe Title: " + RecipeName);
//            //Tags
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
            	 //servings
            	    String servingsize=homepage.gettotalservings();
            	    System.out.println("No Of Servings:"+servingsize);
            	    //Recipeurl
            	    System.out.println("Recipe URL:"+url);
            	    //Preperation method
            	    String prepmethod=homepage.getpreperationmethod();
            	    System.out.println("Preperation Methos:"+prepmethod);
            	
            	    
            	    
            	    //Ingredients
            	 System.out.println("Ingredients for " + url + ":");
                 for (String ingredient : arrayingredients) {
                     System.out.println("- " + ingredient);
                    //Recipe Description
                     String recipesteps=homepage.getrecipedescription();
                     System.out.println("Recipe Description:"+recipesteps);
                     
                     String preparationTime;
                     try {
                         preparationTime = homepage.getpreptime();
                     } catch (NoSuchElementException e) {
                         preparationTime = "N/A"; // Default value when preparation time is not found
                         System.out.println("Preparation Time not found for " + url);
                     }
                     
                     String cookingTime;
                     try {
                         cookingTime = homepage.getcookingtime();
                     } catch (NoSuchElementException e) {
                         cookingTime = "N/A"; // Default value when cooking time is not found
                         System.out.println("Cooking Time not found for " + url);
                     }

                     String totalTime;
                     try {
                         totalTime = homepage.gettotaltime();
                     } catch (NoSuchElementException e) {
                         totalTime = "N/A"; // Default value when total time is not found
                         System.out.println("Total Time not found for " + url);
                     }
                     
                     System.out.println("Preparation Time: " + preparationTime);
                     System.out.println("Cooking Time: " + cookingTime);
                     System.out.println("Total Time: " + totalTime);
                     
                     //nutrition
                     
                     String nutrivalues=homepage.getnutritionvalues();
             	    System.out.println("Nutrient values:"+nutrivalues); 
                 }
                 
          	}
                  }
         	}
          }

  