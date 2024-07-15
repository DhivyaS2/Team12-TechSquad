package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;

public class CommonFunction {
	public static Properties prop;
	public static String path = ".//src/test/resources/Properties/Config.properties";
	
	public static void readConfig() throws IOException {

		FileInputStream fis = new FileInputStream(path);
		prop = new Properties();
		prop.load(fis);
	}
	public static String getUrl() throws IOException {
		readConfig();
		String url = prop.getProperty("URL");
		return url;
	}


	public static String getBrowser() throws IOException {
		readConfig();
		String browser = prop.getProperty("browser");
		return browser;
	}
	public static String getBrowserOptions() throws IOException {
		readConfig();
		String browseroptions = prop.getProperty("headless");
		return browseroptions;
	}
	
	public static String getexcelfilepath() throws IOException {
		readConfig();
		String excelfilelpath = prop.getProperty("excelfilepath");
		if (excelfilelpath != null)
			return excelfilelpath;
		else
			throw new RuntimeException("Excel file path not specified in the Config.properties file.");
	}
	
}
