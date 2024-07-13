package LCHF;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.Browser;
import pageobjects.recipes;
import LCHF.LCHF_ElimAddTest;


public class LCHFBaseAtoZ {
	
	recipes r;
	WebDriver driver;
	public static final ArrayList<String> AllreceipeUrlList = new ArrayList<>();
	public LCHF_ElimAddTest LCHFObj = new LCHF_ElimAddTest();
	
	@BeforeTest
	public void launch() throws IOException {
	  Browser b= new  Browser();
	    b.beforescraping();
	    b.launchsite();
	   r= new recipes();
	  }
	 
	 @Test(priority=0)
	 public  ArrayList<String> ScrappingAtoZ() {
		 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Actions actions = new Actions(driver);
//		WebElement AtoZ = driver.findElement(By.xpath("//a[contains(@href,'RecipeAtoZ')]"));
//		actions.moveToElement(AtoZ).click().perform();	

		r.click_AtoZ(); 
	
	AllreceipeUrlList.clear();
	driver.findElement(By.xpath("//a[normalize-space()='C']")).click();
	
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    WebElement currentPage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='rescurrpg']")));

    boolean hasNextPage = true;
    while (hasNextPage) {
        try {
            List<WebElement> CurrPageRecipeList = driver.findElements(By.xpath("//*[@class ='rcc_recipecard']"));
            System.out.println("No: of receipes in currentPage:" + CurrPageRecipeList.size());

            for (WebElement eachRecipe : CurrPageRecipeList) {
                WebElement recipeNameElement = eachRecipe.findElement(By.xpath(".//span[@class='rcc_recipename']"));
                List <WebElement> urlLinks = driver.findElements(By.xpath("//span[@class='rcc_recipename']/a"));
                for (WebElement webElementlink : urlLinks) {
					AllreceipeUrlList.add(webElementlink.getAttribute("href"));					
                }	
            }
            try {
                WebElement nextBtn = driver.findElement(By.xpath("//*[@class='rescurrpg']/following-sibling::a"));
                nextBtn.click();
                wait.until(ExpectedConditions.stalenessOf(currentPage));
                currentPage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='rescurrpg']")));
            } catch (Exception e) {
                hasNextPage = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            hasNextPage = false;
        }
    }
    System.out.println("Total URLs in array -> "+AllreceipeUrlList.size());	
	return AllreceipeUrlList;
	 }
	
	 @Test(priority=1)
	public void callLCHFTestcase() throws InterruptedException, IOException {
		LCHFObj.EliminateLCHF_fn(AllreceipeUrlList);
		
	}

}
