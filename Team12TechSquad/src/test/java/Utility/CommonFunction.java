package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonFunction {
	public static Properties prop;
	public static String path = ".//src/test/resources/Properties/Config.properties";
	
	public static void readConfig() throws IOException {

		FileInputStream fis = new FileInputStream(path);
		prop = new Properties();
		prop.load(fis);
		}
	
	public static String getBrowser() throws IOException {
		
		String browser = prop.getProperty("browser");
		return browser;
	}
	
	public static String getBrowserOptions() throws IOException {
		
		String browseroptions = prop.getProperty("headless");
		return browseroptions;
	}

	public static String getUrl() throws IOException {
		
		String url = prop.getProperty("URL");
		return url;
	}
	public static String getDatabase_name() throws IOException {
		
		String DB = prop.getProperty("Database_name");
		return DB;
	}
	public static String getdb_username() throws IOException {
		
		String user = prop.getProperty("Username");
		return user;
	}
	public static String getdb_password() throws IOException {
		
		String pwd = prop.getProperty("Password");
		return pwd;
	}
	public static String getPath() throws IOException {
		readConfig();
		String path = prop.getProperty("path");
		return path;
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
