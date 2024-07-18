package Base;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Utility.DataDriven;

public class Not_fully_vegan {
	ExtentReports extent;
	public static ExtentTest test;
	
@Test
	public void Recipes() throws ClassNotFoundException, IOException, SQLException {
		List<String> excel_list = DataDriven.reader("LFV_Elimination","To_Add_if_not_fully_vegan"); 
		Recipes_scrapping r = new Recipes_scrapping();
		r.launch();
		test.pass("launched");
		r.IterateAtoZ_tab(excel_list,"not_fully_vegan");
	     test.pass("Scraped Not fully Vegan");
	}
@BeforeSuite
public void t() {
	ExtentSparkReporter spark = new ExtentSparkReporter("Target/Spark.html");
	 extent = new ExtentReports();
	 extent.attachReporter(spark);
	 test=extent.createTest("Recipe Scraping").log(Status.PASS, "This is a logging event for Reciepe Scrapping it passed!");
}
@AfterSuite
public void tt() {
extent.flush();
}
}
