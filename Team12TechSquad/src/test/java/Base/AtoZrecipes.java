package Base;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.*;

public class AtoZrecipes {
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
		//r.click_AtoZ(); 
	//list of recipes in page
	List<WebElement> list=	r.collect_recipelist();
	List<String> Title = list.stream().map(s->s.getText()).collect(Collectors.toList());
	System.out.println(Title);	
	 }
	 
}
