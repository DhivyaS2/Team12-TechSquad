package Base;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.recipes;
import Utility.DataDriven;

public class Recipes_scrapping {
	 
	public static Integer no;
	 public static String Recipe_id, Recipe_name, Recipe_link;
	 public static List<String> Recipe_ingredients;
	static boolean filter =false;
	List<String> Ingredients_list;
	List<String> excel_list;
	Recipes_scrapping scrape;
	String list[]={"A","B","C","D","E"};
	//CRUD_DB db ;
	int n, size;
	static recipes r;
	 @BeforeTest
	  public void launch() throws IOException, ClassNotFoundException, SQLException {
		  Browser b= new  Browser();
		  b.beforescraping();
		// db = new CRUD_DB();
		 //db.connect();
		// db.create_table();
	
	    b.launchsite();
	   r= new recipes();
	   scrape=new Recipes_scrapping(); 
	   excel_list=DataDriven.reader();  
	  }
	 
	// @Test(priority=1)
	 public void IterateAtoZ_tab() {
		 r.click_recipes(); 
		 for (String letter : list) {
		 WebElement AtoZ =Browser.driver.findElement(By.xpath("//*[text()='"+letter+"']"));
		  AtoZ.click();
		  System.out.println("****A to Z Order Recipies launched on ----- :"+letter);
	     try {
					scrape.NotVegan_Receipes(letter);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    
			}
		 
	 }
	 
	 public void NotVegan_Receipes( String l) throws InterruptedException, IOException {
		
		//recipes.letter=eachATOZ;//itereration from Ato Z
       
		Thread.sleep(2000);
	//List<WebElement> pagination_list =r.pagination_recipelist();//list the pages
		try {
		String Pagesize=Browser.driver.findElement(By.xpath("//*[@id='maincontent']//div[4]/a[15]")).getText();
		size=Integer.parseInt(Pagesize);
	System.out.println(" NO. OF PAGES IN ---   "+l+ "   ARE --------: "+size);
		}
		catch(Exception e) {
			e.getMessage();
		}
//	List<String> page_Link = pagination_list.stream().map(s->s.getAttribute("href")).collect(Collectors.toList());
	for(int i=1;i<=size;i++) {
	   String url ="https://www.tarladalal.com/RecipeAtoZ.aspx?beginswith="+l+"&pageindex="+i+"";
		Browser.driver.navigate().to(url);
		Thread.sleep(1000);
		List<WebElement> list=	r.collect_recipelist();
		System.out.println(" NO. OF RECIPES IN PAGE --------: "+i+" are "+list.size());
		if(list.size()!=0) {
		List<String> Title = list.stream().map(s->s.getText()).collect(Collectors.toList());
		List<String> Link = list.stream().map(s->s.getAttribute("href")).collect(Collectors.toList());
		System.out.println(" TITLES OF RECIPES -----------: "+Title);	
		System.out.println(" LINKS OF RECIPES ----------: "+Link);//all link in a page	
		for(String eachlink : Link) {
			scrape.Recipe_scraping_link(eachlink);
			
		}}
		
		
		}
	
	}
	
	 
	 
	//@Test
	 public  void Recipe_scraping_link(String eachlink) throws InterruptedException, IOException {
	
		
			Browser.driver.navigate().to(eachlink);
			Thread.sleep(2000);
			try {
			List<WebElement> Ingredients=	r.Ingredients_list();
			 Ingredients_list = Ingredients.stream().map(s->s.getText()).collect(Collectors.toList());
			n=Ingredients_list.size();
			}
			catch (Exception e) {
		       	 Recipe_name="N/A" ;
		       	 System.out.println("Recipe Name is not available");
				e.getMessage() ;
			 }
			
			System.out.println(" NO. OF INGREDIENTS --------: "+n);
			System.out.println(" INGREDIENT LIST OF A RECIPE --------:"+Ingredients_list);	
			
			try {
				breaklevel:
					  for (String eachingredient: Ingredients_list)
					  {
						  for(String eachexcelfilter: excel_list) {
							  if(eachexcelfilter!=null) {
							 filter= eachingredient.contains(eachexcelfilter) ;
							 //System.out.println(eachingredient);
							 //System.out.println(eachexcelfilter);
							System.out.println(filter);
							 if(filter==true) {
								 break breaklevel;
							 }
							  }
						  }
					  }
				if(filter==false) {
				System.out.println("This Recipe doesn't have the TO ADD list for not fully Vegan....");	
				}
				else {
					try { 
						Recipe_id= r.Reciepe_id();}
				         catch (Exception e) {
				        	 Recipe_id="N/A" ;
				        	 System.out.println("Recipe id is not available");
						e.getMessage() ;
					 }
						try {
						Recipe_name=r.Reciepe_name();
						}
						catch (Exception e) {
				       	 Recipe_name="N/A" ;
				       	 System.out.println("Recipe Name is not available");
						e.getMessage() ;
					 }
						Recipe_link=eachlink;
						Recipe_ingredients=Ingredients_list;
						System.out.println("******* "+Recipe_id+" ******* "+Recipe_name+" ****** ");
//						try {
//							db.insert_table();
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
					 
					//System.out.println(filter);
					//scrape.output_list(eachlink,Ingredients_list);
				System.out.println("This Recipe has   TO ADD list for not fully Vegan ....");	
			
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}

		}
//	
//	 @Test
//	 public void output_list(String eachlink,List<String> Ingredients) {
//		try { 
//		Recipe_id= r.Reciepe_id();}
//         catch (Exception e) {
//        	 Recipe_id="N/A" ;
//        	 System.out.println("Recipe id is not available");
//		e.getMessage() ;
//	 }
//		try {
//		Recipe_name=r.Reciepe_name();
//		}
//		catch (Exception e) {
//       	 Recipe_name="N/A" ;
//       	 System.out.println("Recipe Name is not available");
//		e.getMessage() ;
//	 }
//		Recipe_link=eachlink;
//		Recipe_ingredients=Ingredients;
//		System.out.println("******* "+Recipe_id+" ******* "+Recipe_name+" ****** ");
//		try {
//			db.insert_table();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	 }
}
