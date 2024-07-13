package Base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Utility.CommonFunction;


public class Browser {
	public static WebDriver driver;

	String browser,url,head;
	
	

public void beforescraping() throws IOException {

			browser = CommonFunction.getBrowser();
			
			head=CommonFunction.getBrowserOptions();
			if(browser.equals("chrome")) {
				ChromeOptions chromeOptions = new ChromeOptions();
		        
		        if(head.equalsIgnoreCase("YES"))
		        	chromeOptions.addArguments("-headless");
		        else
		        	chromeOptions.addArguments("-headed");
		       
		        driver = new ChromeDriver(chromeOptions);
				driver.manage().window().maximize();
				
			}
		}
		
	
		public static void quitdriver() {
			driver.close();
		}
		public void launchsite() throws IOException {
			url = CommonFunction.getUrl();
			driver.get(url);
	
		}
		
		
}


