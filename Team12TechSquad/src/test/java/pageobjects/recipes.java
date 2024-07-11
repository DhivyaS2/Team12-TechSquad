package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Base.Browser;

public class recipes {


	  By recipes = By.xpath("//*[text()='Recipe A To Z']");
	  By recipes_list= By.className("rcc_recipename");
	 
	 
	  
public   void click_AtoZ() {

	 Browser.driver.findElement(recipes).click();

	 }

public List<WebElement> collect_recipelist() {
return	Browser.driver.findElements(recipes_list);
	
}
}
