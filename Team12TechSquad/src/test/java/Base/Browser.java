package Base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Utility.PropertyFunction;


public class Browser {
	public static WebDriver driver;

	String browser,url,head;
	
public Browser() throws IOException {
	 PropertyFunction.readConfig();
}

public void beforescraping() throws IOException {

	       	browser = PropertyFunction.getBrowser();
			
			head=PropertyFunction.getBrowserOptions();
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
			url = PropertyFunction.getUrl();
			try {
			driver.get(url);}
			catch(Exception e) {
				System.out.println("Home URL is not available");
				Browser.driver.navigate().refresh();
			}
	
		}
		
		
}


