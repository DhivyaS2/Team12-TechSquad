package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;

public class CommonFunctionutil {
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
	public static List<String> readElminationListFromPropertiesFile() {
        List<String> eliminationList = new ArrayList<>();
        
        try {
            readConfig();
            String eliminationListString = prop.getProperty("ElminationList");
            String[] items = eliminationListString.split(",");
            for (String item : items) {
                eliminationList.add(item.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return eliminationList;
	
}
	
	public static List<String> readcusinedetails() {
        List<String> cusineList = new ArrayList<>();
        
        try {
            readConfig();
            String cusinestring = prop.getProperty("ElminationList");
            String[] items = cusinestring.split(",");
            for (String item : items) {
            	cusineList.add(item.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return cusineList;
	
}
	public static String getexcelfilepath() throws IOException {
		readConfig();
		String excelfilelpath = prop.getProperty("excelfilepath");
		if (excelfilelpath != null)
			return excelfilelpath;
		else
			throw new RuntimeException("Excel file path not specified in the Config.properties file.");
	}
	
	public static String getDatabase_name() 
	{
		try {
			readConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty("dbname");
	}
	
	public static String getdb_username()
	{
		try {
			readConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty("username");
	}
	
	public static String getdb_password()
	{
		try {
			readConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty("password");
	}
	
}
