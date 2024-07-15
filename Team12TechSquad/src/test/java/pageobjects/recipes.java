package pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Base.Browser;

public class recipes {

	 By recipes = By.xpath("//*[text()='Recipe A To Z']");
	 // By recipes_list= By.className("rcc_recipename");
	  
	  By recipes_list = By.xpath("//span[@class='rcc_recipename']/a");
	  By recipes_ingredients = By.id("rcpinglist");
	  By preptime = By.cssSelector("time[itemprop='prepTime']");
	  By cooktime = By.cssSelector("time[itemprop='cookTime']");
	  By Tags = By.xpath("//div[@id='recipe_tags']/a/span");
	  By Numberofservings = By.xpath("//span[@id='ctl00_cntrightpanel_lblServes']");
	  By preparationmethod = By.xpath("//div[@id='ctl00_cntrightpanel_pnlRcpMethod']");
	  By Nutrientvalue = By.xpath("//span[@itemprop='nutrition']");
	  //By recipe_id = By.xpath("//div[@class='rcc_recipecard']");
	  
			  
public void click_AtoZ() {

	 Browser.driver.findElement(recipes).click();

	 }

public List<WebElement> collect_recipelist() {
return	Browser.driver.findElements(recipes_list);
	
}

public List<WebElement> collect_ingridientsList(){
	return Browser.driver.findElements(recipes_ingredients);
	
}

//public List<WebElement> collect_recipeid(){
	//return Browser.driver.findElements(recipe_id);
	
public List<WebElement>	collect_preptime(){
	
	return Browser.driver.findElements(preptime);
}

public List<WebElement>	collect_cooktime(){
	
	return Browser.driver.findElements(cooktime);
}

public List<WebElement>	collect_recipetags(){
	
	return Browser.driver.findElements(Tags);
}

public List<WebElement>	collect_noofservings(){
	
	return Browser.driver.findElements(Numberofservings);
}

public List<WebElement>	collect_preparationmethod(){
	
	return Browser.driver.findElements(preparationmethod);
}

public List<WebElement>	collect_nutrientvalue(){
	
	return Browser.driver.findElements(Nutrientvalue);
}

}
  
	
	
  
    
      


 




