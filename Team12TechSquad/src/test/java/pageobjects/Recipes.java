package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Base.Browser;

public class Recipes {


	  By recipes = By.xpath("//a[text()='Recipe A To Z']");
	  By recipes_list= By.xpath("//span[@class='rcc_recipename']/a");
	  By ingredients_list = By.xpath("//div[@id='recipe_ingredients']");
	 
	  By receipe_Name = By.xpath("//span[@id='ctl00_cntrightpanel_lblRecipeName']");
	  By preparation_time = By.cssSelector("time[itemprop='prepTime']");
	  By cooking_time = By.cssSelector("time[itemprop='cookTime']");
	  By recipe_tags = By.xpath("//div[@id='recipe_tags']/a/span");
	  
public   void click_AtoZ() {

	 Browser.driver.findElement(recipes).click();

	 }

public List<WebElement> collect_recipelist() {
return	Browser.driver.findElements(recipes_list);
	
}
public List<WebElement> Collect_ingredientsList(){
	return Browser.driver.findElements(ingredients_list);
}
public List<WebElement> Collect_Recipe_Name (){
	return Browser.driver.findElements(receipe_Name);
	
}
public List<WebElement> Collect_Recipe_Preparation_Time(){
	return Browser.driver.findElements(preparation_time);
}
public List<WebElement> Collect_Recipe_Cooking_Time (){
	return Browser.driver.findElements(cooking_time);
}
public List<WebElement> Collect_Recipe_Tags (){
	return Browser.driver.findElements(recipe_tags);
}


}
