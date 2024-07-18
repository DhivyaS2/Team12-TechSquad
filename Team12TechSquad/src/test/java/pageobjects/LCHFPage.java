package pageobjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.Browser;

public class LCHFPage  {


	@FindBy(xpath="//*[@class='rescurrpg']") private WebElement curr_page;
	@FindBy(xpath="//*[@class ='rcc_recipecard']") private List<WebElement> currPage_recpList;
	@FindBy(xpath="//span[@class='rcc_recipename']/a") private WebElement url_List;
	@FindBy(xpath="//*[@class='rescurrpg']/following-sibling::a") private WebElement nextpage;

	@FindBy(xpath="//div[@id='rcpinglist']//a/span") private List<WebElement> Ingrediants_list;
	@FindBy(xpath="//span[@id='ctl00_cntrightpanel_lblRecipeName']") private WebElement Receip_name;
	@FindBy(css = "time[itemprop='prepTime']") private WebElement pre_time;
	@FindBy(css="time[itemprop='cookTime']") private WebElement cook_time;
	@FindBy(xpath="//div[@id='recipe_tags']//a/span") private List<WebElement> tags;
	@FindBy(xpath="//span[@id='ctl00_cntrightpanel_lblServes']") private WebElement servings;
	@FindBy(xpath="//p[@id='recipe_description']") private WebElement receipe_desc;
	@FindBy(xpath="//div[@id='ctl00_cntrightpanel_pnlRcpMethod']/div[1]") private WebElement receipe_method;
	@FindBy(xpath="//*[@id='rcpnutrients']") private WebElement nutrients_list;
	@FindBy(xpath="//*[@class='breadcrumb-link-wrap']/a") private List<WebElement> breadcrump_CuisineList;

	private ArrayList<String> ingrediantsList = new ArrayList<>();
	private ArrayList<String> cuisinesList = new ArrayList<>();
	private ArrayList<String> tagList = new ArrayList<>();

	public LCHFPage() {
		PageFactory.initElements(Browser.driver, this);

	}
	public WebElement getcurrntPage() {
	    WebDriverWait wait = new WebDriverWait(Browser.driver, Duration.ofSeconds(60));
	    WebElement currentPage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='rescurrpg']")));
	    return currentPage;
	}
	public int getCurrPage_linksCount() {
        int size = currPage_recpList.size();
        return size;
	}
//	public ArrayList<WebElement> getUrlLinksInCurrentPage(){
//		//url_List
//	}
	public ArrayList<String> getIngrediant() {
		for (WebElement each_ingrediant : Ingrediants_list) {
			String ingrediant = each_ingrediant.getText().toLowerCase().trim();		
			ingrediantsList.add(ingrediant);
		}
		return ingrediantsList;
	}
	public ArrayList<String> getCuisine() {
		for (WebElement cuisines : breadcrump_CuisineList) {
			String c_name = cuisines.getText().toLowerCase().trim();		
			cuisinesList.add(c_name);
		}
		return cuisinesList;
	}	
	public String getRecipeID(String eachUrl) {	 
		String[] splitstr=eachUrl.split("-");
		String last=splitstr[splitstr.length-1];
		String receipe_id = last.substring(0,last.length()-1);
		return receipe_id;
	}
	public String getRecipeName() {
		String receipe_Name = Receip_name.getText().trim();
		return receipe_Name;
	}
	public String getPreptime() {
		return pre_time.getText().trim();
	}
	public String getCooktime() {
		return cook_time.getText().trim();
	}
	public ArrayList<String> getTags() {
		for (WebElement each_tag : tags) {
			String Tag = each_tag.getText().toLowerCase().trim();
			tagList.add(Tag);
		}
		return tagList;
	}
	public String getNoServings() {
		return servings.getText().trim();
	}
	public String getRecipeDesc() {
		return receipe_desc.getText().trim();
	}
	public String getRecipeMothod() {
		return receipe_method.getText().trim();
	}
	public String getNutrientsList() {
		return nutrients_list.getText().trim();
	}

}