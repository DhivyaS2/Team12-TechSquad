package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Base.Browser;

public class Recipes {


	  By recipes = By.xpath("//a[text()='Recipe A To Z']");
	  By recipes_list= By.xpath("//span[@class='rcc_recipename']/a");
	 By ingredients_list = By.xpath("//div[@id='recipe_ingredients']");
	 
	  
public   void click_AtoZ() {

	 Browser.driver.findElement(recipes).click();

	 }

public List<WebElement> collect_recipelist() {
return	Browser.driver.findElements(recipes_list);
	
}
public List<WebElement> Collect_ingredientsList(){
	return Browser.driver.findElements(ingredients_list);
}
}
