package Base;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.recipes;

public class Scraping {
	
	recipes r;
	WebDriver driver;
	 @BeforeTest
	  public void launch() throws IOException {
	  Browser b= new  Browser();
	    b.beforescraping();
	    b.launchsite();
	   r= new recipes();
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

			List<WebElement> listIngredients =r.collect_ingridientsList();
			List<String> Ingredients = listIngredients.stream().map(s->s.getText()).collect(Collectors.toList());
			System.out.println(Ingredients);
			
			//List<WebElement> recipeid = r.collect_recipeid();
			//List<String> Recipe_id= recipeid.stream().map(s->s.getText()).collect(Collectors.toList());
			//System.out.println(Recipe_id);
			
			List<WebElement> preparationtime =r.collect_preptime();
			List<String> preptime= preparationtime.stream().map(s->s.getText()).collect(Collectors.toList());
			System.out.println("preparation Time:" +preptime);
			
			List<WebElement> cookingtime =r.collect_cooktime();
			List<String> cooktime= cookingtime.stream().map(s->s.getText()).collect(Collectors.toList());
			System.out.println("Cooking Time:" +cooktime);
			
			List<WebElement> Tags =r.collect_recipetags();
			List<String> RecipeTags= Tags.stream().map(s->s.getText()).collect(Collectors.toList());
			System.out.println("Tags:" +RecipeTags);
			
			List<WebElement> Numberofservings =r.collect_noofservings();
			List<String> Noofservings= Numberofservings.stream().map(s->s.getText()).collect(Collectors.toList());
			System.out.println("Number of servings:" +Noofservings);
			
			List<WebElement> prepmethod =r.collect_preparationmethod();
			List<String> PreparationMethod= prepmethod.stream().map(s->s.getText()).collect(Collectors.toList());
			System.out.println("Preparation Method:" +PreparationMethod);
			

			List<WebElement> NutritionTable =r.collect_nutrientvalue();
			List<String> Nutritionvalues= NutritionTable.stream().map(s->s.getText()).collect(Collectors.toList());
			System.out.println("NutritionTable:" +Nutritionvalues);
			
	}
}
}

