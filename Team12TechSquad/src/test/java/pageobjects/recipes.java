package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Base.Browser;

public class recipes {

public static String letter="D";
	  By recipes = By.xpath("//div[normalize-space()='RECIPES']");
	  By A_zRecipies=By.xpath("//a[@title='Recipea A to Z']");
	// By AtoZtab=By.xpath("//*[text()='"+letter+"']");
	// By AtoZtab =By.xpath("//*[@id=\"ctl00_cntleftpanel_mnuAlphabets\"]/tbody/tr/td//a");
	  By Recipe_id=By.xpath("//*[@id='ctl00_assetcnt_lblrcpcount']");
	  By Recipe_Name=By.xpath("//*[@id='ctl00_cntrightpanel_lblRecipeName']");
	  By preperation_time=By.xpath("//time[@itemprop='prepTime']");
	  By cooking_time=By.xpath("//time[@itemprop='cookTime']");
	  By Recipe_description=By.id("recipehead");
	  By tag=By.id("recipe_tags");
	  By serving=By.id("ctl00_cntrightpanel_lblServes");
	  By preperation_method=By.id("recipe_small_steps");
	  By nutrition_value=By.xpath("/table[@id=‘rcpnutrients’]//tr");
	  By Ingredients=By.xpath("//*[@id='rcpinglist']/div/span");
	  By food_course=By.xpath("//*[@id='show_breadcrumb']/div/span[5]/a/span");
	  
	  By lowfatdiet=By.xpath("//*[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht289']");
	  By recipes_list= By.xpath("(//span[@class='rcc_recipename'])/a");
	  By pagination =By.xpath("//*[@id='maincontent']/div[1]/div[4]/a");
	  
	  
public   void click_recipes() {
Browser.driver.findElement(A_zRecipies).click();
}

public void lowfat() {
	Browser.driver.findElement(lowfatdiet).click();	
}


public List<WebElement> collect_recipelist() {
return	Browser.driver.findElements(recipes_list);	
}

public List<WebElement> pagination_recipelist() {
return	Browser.driver.findElements(pagination);	
}

public List<WebElement> Ingredients_list() {
return	Browser.driver.findElements(Ingredients);	
}

public String Reciepe_id() {
return Browser.driver.findElement(Recipe_id).getText();	
}

public String Reciepe_name() {
return Browser.driver.findElement(Recipe_Name).getText();	
}

public String Preperation_time() {
return Browser.driver.findElement(preperation_time).getText();	
}
public String Cooking_time() {
return Browser.driver.findElement(cooking_time).getText();	
}
public String Recipe_description() {
return Browser.driver.findElement(Recipe_description).getText();	
}
public String Tag() {
return Browser.driver.findElement(tag).getText();	
}
public String Serving() {
return Browser.driver.findElement(serving).getText();	
}

public String Preperation_method() {
return Browser.driver.findElement(preperation_method).getText();	
}
public String Nutrition_value() {
return Browser.driver.findElement(nutrition_value).getText();	
}

public String Food_course() {
return Browser.driver.findElement(food_course).getText();	
}
}
