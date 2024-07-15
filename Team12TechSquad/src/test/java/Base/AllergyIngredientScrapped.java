package Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.Recipes;

public class AllergyIngredientScrapped {
	Recipes r;
	WebDriver driver;
	 @BeforeTest
	  public void launch() throws IOException {
	  Browser b= new  Browser();
	    b.beforescraping();
	    b.launchsite();
	   r= new Recipes();
	  }
	 
	 @Test
	 public void Scrapping() {
		r.click_AtoZ(); 
	//list of recipes in page
	List<WebElement> list=	r.collect_recipelist();
	
	
	List<String> Title = list.stream().map(s->s.getText()).collect(Collectors.toList());
	System.out.println(Title);	
	
	List<String> Link = list.stream().map(s->s.getAttribute("href")).collect(Collectors.toList());
	System.out.println(Link);
	
	for(String eachlink : Link) {
		  
		   Browser.driver.navigate().to(eachlink);
		   
	List<WebElement> listIngredients=	r.Collect_ingredientsList();
	List<String>  ingredients = listIngredients.stream().map(s->s.getText()).collect(Collectors.toList());
	System.out.println(ingredients);
	
	
	List<String> allergyIngredients = new ArrayList<String>();
	allergyIngredients.add("milk");
	allergyIngredients.add("soy");
	allergyIngredients.add("almond");
	
	//List<String> listOfCommonItems = (List<String>) CollectionUtils.intersection(ingredients, allergyIngredients) ; 
	
	
	breaklevel:
	for (String eachingredient: ingredients)
	  {
		  for(String eachallergyfilter: allergyIngredients) {
			boolean filter= eachingredient.contains(eachallergyfilter) ;
			 System.out.println(filter);
			 if(filter==true) {
				 break breaklevel;
			 }
		  }
	//boolean containsallergyIngredients = containsallergyIngredients(ingredients, allergyIngredients);
	
//	if (!containsallergyIngredients){
//		System.out.println("recipeList "+ingredients);
//	} else {
//		System.out.println("not present");
//	}  {
//		
//	}
	
	
	
	
	}
	 }


	
	 }}
		   
		 
	
	
	

