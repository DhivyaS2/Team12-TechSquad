package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Browser;

public class recipes {

public static String letter="D";

 
 @FindBy(xpath="//a[@title='Recipea A to Z']") WebElement A_zRecipies;
	// By AtoZtab=By.xpath("//*[text()='"+letter+"']");
	// By AtoZtab =By.xpath("//*[@id=\"ctl00_cntleftpanel_mnuAlphabets\"]/tbody/tr/td//a");
 @FindBy(id="ctl00_assetcnt_lblrcpcount")WebElement Recipe_id;
 @FindBy(xpath="//*[@id='ctl00_cntrightpanel_lblRecipeName']")WebElement Recipe_Name;
 @FindBy(xpath="//time[@itemprop='prepTime']")WebElement preperation_time;
 @FindBy(xpath="//time[@itemprop='cookTime']")WebElement cooking_time;
 @FindBy(id="ctl00_cntrightpanel_lblDesc")WebElement Recipe_description;
 @FindBy(id="recipe_tags")WebElement tag;
 @FindBy(id="ctl00_cntrightpanel_lblServes") WebElement serving;
 @FindBy(id="recipe_small_steps")WebElement preperation_method;
 @FindBy(xpath="/table[@id=‘rcpnutrients’]//tr")WebElement nutrition_value;
 @FindBy(xpath="//*[@id='rcpinglist']/div/span")List<WebElement> Ingredients;
 @FindBy(xpath="//*[@id=\"show_breadcrumb\"]/div")WebElement food_course;
	  
 @FindBy(xpath="//*[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht289']")WebElement lowfatdiet;
 @FindBy(xpath="(//span[@class='rcc_recipename'])/a")List<WebElement> recipes_list;
 @FindBy(xpath="//*[@id='maincontent']/div[1]/div[4]/a")List<WebElement> pagination;
@FindBy(xpath="//*[@id='maincontent']//div[4]/a[15]") WebElement page_size;
	  
public   void click_recipes() {
PageFactory.initElements(Browser.driver, this);
A_zRecipies.click();
}

public void lowfat() {
	lowfatdiet.click();	
}


public List<WebElement> collect_recipelist() {
return	recipes_list;	
}

public List<WebElement> pagination_recipelist() {
return	pagination;	
}

public List<WebElement> Ingredients_list() {
return Ingredients;	
}

public String Recipe_id() {
return Recipe_id.getText();	
}

public String Recipe_name() {
return Recipe_Name.getText();	
}

public String Preperation_time() {
return preperation_time.getText();	
}
public String Cooking_time() {
return cooking_time.getText();	
}
public String Recipe_description() {
return Recipe_description.getText();	
}
public String Tag() {
return tag.getText();	
}
public String Serving() {
return serving.getText();	
}

public String Preperation_method() {
return preperation_method.getText();	
}
public String Nutrition_value() {
return nutrition_value.getText();	
}

public String Food_course() {
return food_course.getText();	
}
public String Page_size() {
return page_size.getText();	
}

}
