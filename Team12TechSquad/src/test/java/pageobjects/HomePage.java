package pageobjects;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import Utility.CommonFunctionutil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.Browser;
public class HomePage {
	
	@FindBy(xpath="//a[@title='Recipea A to Z']")
	WebElement recipe;
	
	@FindBy(xpath="//span[@id='ctl00_cntrightpanel_lblRecipeName']")
	WebElement recipetitle;
	
	@FindBy(className="rcc_recipename")
	private List<WebElement> recipes_url;

	@FindBy(xpath="//div[@id='rcpinglist']")
	private List<WebElement> ingredients;
	
	@FindBy(xpath="//span[contains(text(), 'Recipe#')]")
    private WebElement recipeno;
	
	@FindBy(xpath="//time[@itemprop='prepTime']")
	WebElement preperationtime;
	
	@FindBy(xpath="//time[@itemprop='cookTime']")
	WebElement cookingtime;
	
	@FindBy(xpath="//time[@itemprop='totalTime']")
	WebElement totaltime;
	
	@FindBy(xpath="//div[@style='text-align:right;padding-bottom:15px;']/a")
	private List<WebElement> gotopages;
	
	@FindBy(xpath="//a[@itemprop='recipeCategory']")
	private List<WebElement> recipetaglist;
	
	@FindBy(xpath="//span[@id='ctl00_cntrightpanel_lblServes']")
	WebElement totalserving;
	
	@FindBy(xpath="//span[@id='ctl00_cntrightpanel_lblDesc']")
	WebElement recipesteps;
	@FindBy(xpath="//div[@id='recipe_small_steps']")
	WebElement recipemethod;
	@FindBy(xpath="//div[@id='rcpnuts']")
	WebElement nutrition;
//    @FindBy(xpath = "//table[@id='ctl00_cntleftpanel_mnuAlphabets']//a")
//    private List<WebElement> alphabeticalIndexLinks;
	
    @FindBy(xpath = "//table[@id='ctl00_cntleftpanel_mnuAlphabets']//a")
    private List<WebElement> alphabeticalIndexLinks;
	
	private List<String> eliminationlist;
	public String baseurl;
	 public HomePage() throws IOException {
	        PageFactory.initElements(Browser.driver, this);
	        this.eliminationlist = CommonFunctionutil.readElminationListFromPropertiesFile();
	        this.baseurl=CommonFunctionutil.getUrl();
	    }
	 
	    public void clickAlphabeticalIndex(String index) throws InterruptedException {
	        for (WebElement indexLink : alphabeticalIndexLinks) {
	            if (indexLink.getText().equalsIgnoreCase(index)) {
	                indexLink.click();
	                // Optionally wait for page to load
	                Thread.sleep(2000);
	                break; // Exit loop after clicking the correct index link
	            }
	        }
	        
	        
	     }
	    

	    public List<WebElement> getAlphabeticalIndexLinks() {
	        return alphabeticalIndexLinks;
	    }

	 
	 
	 
	 
	 
	 
	public void recipeall()
	{
		recipe.click();
		Browser.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	
	public WebElement getrecipetitletlement() {
	    
	    return Browser.driver.findElement(By.xpath("//h1[@class='recipe-title']"));
	}
    public void navigatetorecipedetailspage(String recipeUrl) {
        Browser.driver.get(recipeUrl);
    }
	
	public List<WebElement> collectrecipes()
	{
		return recipes_url;
	}
	public WebElement collectreciperumbers() {
        return recipeno;
    }
	

	public String getpreptime() {
	    WebElement preparationTimeElement = preperationtime;
	    String preparationTime = preparationTimeElement.getText().trim();
	    return preparationTime;
	}


	public String getcookingtime()
	{
		WebElement cookingTimeElement = cookingtime;
	    String cookingTime = cookingTimeElement.getText().trim();
		return cookingTime;
	}
	

	public String gettotaltime()
	{

		WebElement totalTimeElement = totaltime;
	    String totalTime = totalTimeElement.getText().trim();
		return totalTime;
	}
	
	public String getrecipename()
	{
		WebElement name=recipetitle;
		String recipename=name.getText().trim();
		int pipeIndex = recipename.indexOf('|');
	    if (pipeIndex != -1) {
	        recipename = recipename.substring(0, pipeIndex).trim();
	    }
		return recipename;
	}
	
	public String extractrecpieno(String url)
	{
		String[] splitstr=url.split("-");
		String last=splitstr[splitstr.length-1];
		String recipenumber=last.substring(0,last.length()-1);
		return recipenumber;
		
		
	}
	
    public List <String> getrecipeurls(int pageIndex) {
    	
    	
    	List<String> recipeurls = new ArrayList<>();
    	String pageurl = baseurl + "RecipeAtoZ.aspx?pageindex=" + pageIndex;
    	
    	Browser.driver.get(pageurl);
    	String currenturl = Browser.driver.getCurrentUrl();
        
        List<WebElement> recipeitems = Browser.driver.findElements(By.xpath("//div[@class='rcc_recipecard']"));
        


        for (WebElement items : recipeitems) {
           WebElement linkElement = items.findElement(By.tagName("a"));
            String url = linkElement.getAttribute("href");
           // System.out.println("urldata"+url);
            if (url != null && !url.isEmpty()) {
                recipeurls.add(url);
            }
        }
        return recipeurls;
    }
    
    public List<WebElement>getrecipetags(){
    	return recipetaglist;
    }
       
    public String gettotalservings()
    {
    	WebElement total=totalserving;
    	String servingsize=total.getText().trim();
    	return servingsize;
    }
    
    public List<String> getingredientsrecipe(String recipeUrl) {
        Browser.driver.get(recipeUrl);
        
        List<String> ingredientslist = new ArrayList<>();
        for (WebElement ingredient : ingredients) {
            ingredientslist.add(ingredient.getText().trim());
        }
        
        return ingredientslist;
    }
    
    

	public List<String> getleliminationList() {
		List<String> eliminationlist = new ArrayList<>();
     eliminationlist = CommonFunctionutil.readElminationListFromPropertiesFile();
      
      return this.eliminationlist;
	}
	
	public List<WebElement> returnpages()
	{
		
		return gotopages;
	}
	
	public String getrecipedescription()
	{
		WebElement getrecipedesc=recipesteps;
		String recipedesc=getrecipedesc.getText().trim();
		return recipedesc;
	}
	
//	public String getpreperationmethod()
//	{
//		WebElement getrecipesteps=recipemethod;
//		String recipemethod=getrecipesteps.getText().trim();
//		return recipemethod;
//	}
	public String getpreperationmethod() {
		WebElement getrecipesteps=recipemethod;
		String recipemethod=getrecipesteps.getText().trim();
		return recipemethod;
	}
	
	public String getnutritionvalues()
	{
		WebElement getnutrition=nutrition;
		String nutritionvalue=getnutrition.getText().trim();
				return nutritionvalue;
	}
	
	
}




