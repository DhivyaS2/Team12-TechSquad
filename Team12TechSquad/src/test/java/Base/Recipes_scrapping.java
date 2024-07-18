package Base;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import JDBC.CRUD_DB;
import Utility.DataDriven;
import pageobjects.recipes;

public class Recipes_scrapping {
	 
	public static Integer no=0;
	 public static String Recipe_id, Recipe_name, Recipe_link, Recipe_tag, Recipe_desc, serving, cooking_time, prep_time,Prep_method,nutrient_value, Recipe_cg, Food_cg, Cuisine_cg;
	 public static List<String> Recipe_ingredients;
	static boolean filter =false;
	static List<String> Ingredients_list, excel_list, Cuisine_list, Food_list, Recipe_list, Allergy_list;
	String list[]={"A","B","C","D","E","F","G","H","J","K"};
	static CRUD_DB db ;
	static int n;
	static int size;
	static recipes r;
	static String table_name_toinsert;
	// @BeforeTest
	  public void launch() throws IOException, ClassNotFoundException, SQLException {
		  Browser b= new  Browser();
		  b.beforescraping();
		 db = new CRUD_DB();
		 db.connect();
		//db.create_table();
	    b.launchsite();
	    Not_fully_vegan.test.pass("Chrome is launched");
	   r= new recipes();
	  
	   

		Cuisine_list=DataDriven.reader("Category_list","cuisinecategory"); 
	   //System.out.println(Cuisine_list);
	   Food_list=DataDriven.reader("Category_list","food"); 
	   //System.out.println(Food_list);
	   Recipe_list=DataDriven.reader("Category_list","recipecategory"); 	   
	    
	  }
	 
	//@Test(priority=1)
	 public void IterateAtoZ_tab(List<String> datafrom_excel, String table_name) {
		 excel_list= datafrom_excel;
		 table_name_toinsert=table_name;
		try {
			 r.click_recipes(); 
			 Not_fully_vegan.test.pass("Ato Z recipes ");
		}
		catch(Exception e) {
			System.out.println("AtoZ Recipes is not available");
			Browser.driver.navigate().refresh();
		}
		 for (String letter : list) {
		 WebElement AtoZ =Browser.driver.findElement(By.xpath("//*[text()='"+letter+"']"));
		 try {
		  AtoZ.click();
		  Not_fully_vegan.test.pass("Ato Z tab Iterating ");}
		 catch(Exception e) {
				System.out.println("AtoZ tab is not available");
				Browser.driver.navigate().refresh();
			}
		  System.out.println("****A to Z Order Recipies launched on ----- :"+letter);
	     try {
	    	   Recipes_scrapping.AtoZ_Receipes(letter);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	     try {
			 r.click_recipes(); 	
		}
		catch(Exception e) {
			System.out.println("AtoZ Reciepe is not available");
			Browser.driver.navigate().refresh();
		}
			}
		 System.out.println("Total recipe Not fully vegan we scrapped are------" +no);
	 }
	 
	 public static void AtoZ_Receipes( String l) throws InterruptedException, IOException {
		
		//recipes.letter=eachATOZ;//itereration from Ato Z
       
		Thread.sleep(2000);
	//List<WebElement> pagination_list =r.pagination_recipelist();//list the pages
		try {
		String Pagesize=r.Page_size();
		try {
			size=Integer.parseInt(Pagesize);
			 Not_fully_vegan.test.pass("Page Size is collected ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	System.out.println(" NO. OF PAGES IN ---   "+l+ "   ARE --------: "+size);
		}
		catch(Exception e) {
			e.getMessage();
		}
//	List<String> page_Link = pagination_list.stream().map(s->s.getAttribute("href")).collect(Collectors.toList());
	for(int i=1;i<=size;i++) {
	   try {
		String url ="https://www.tarladalal.com/RecipeAtoZ.aspx?beginswith="+l+"&pageindex="+i+"";
		Browser.driver.navigate().to(url);
		 Not_fully_vegan.test.pass("Navaigated from page 1 to size ");
		Thread.sleep(1000);
	   }
	   catch(Exception e) {
		   System.out.println("URL is not available");
		   Browser.driver.navigate().refresh();
		   e.getMessage();
	   }
		List<WebElement> list=	r.collect_recipelist();
		System.out.println(" NO. OF RECIPES IN PAGE --------: "+i+" are "+list.size());
		 Not_fully_vegan.test.pass("No of Recipes in a page is listed ");
		if(list.size()!=0) {
		List<String> Title = list.stream().map(s->s.getText()).collect(Collectors.toList());
		List<String> Link = list.stream().map(s->s.getAttribute("href")).collect(Collectors.toList());
		System.out.println(" TITLES OF RECIPES -----------: "+Title);	
		System.out.println(" LINKS OF RECIPES ----------: "+Link);//all link in a page	
		for(String eachlink : Link) {
			Recipes_scrapping.Recipe_scraping_link(eachlink);
			 Not_fully_vegan.test.pass("Navigated to each link of page ");
		}}
		}
	
	
	}
	
	
	 public static  void Recipe_scraping_link(String eachlink) throws InterruptedException, IOException {
	
		try {
			Browser.driver.navigate().to(eachlink);
		} catch( Exception e) {
			 System.out.println("URL is not available");
			 Browser.driver.navigate().refresh();
				e.getMessage() ;	
		}
			Thread.sleep(1000);
			try {
			List<WebElement> Ingredients=	r.Ingredients_list();
			 Ingredients_list = Ingredients.stream().map(s->s.getText()).collect(Collectors.toList());
			n=Ingredients_list.size();
			 Not_fully_vegan.test.pass("Ingredients list is collected ");
			}
			catch (Exception e) {
					Ingredients_list.add("N/A") ;
			       	 System.out.println("Ingredients list is not available");
					e.getMessage() ;
				 }
			 
			
			System.out.println(" NO. OF INGREDIENTS --------: "+n);
			System.out.println(" INGREDIENT LIST OF A RECIPE --------:"+Ingredients_list);	
			
			try {
				breaklevel:
					  for (String eachingredient: Ingredients_list)
					  {
						  for(String eachexcelfilter:excel_list) {
							  if(eachexcelfilter!=null) {
							 filter= eachingredient.contains(eachexcelfilter) ;// Checks "To Add" is available in Ingredients
						
							 if(filter==true) {
								 break breaklevel;
							 }
							  }
						  }
					  }
				if(filter==false) {
					if(table_name_toinsert.equals("Allergy")) {
						Recipes_scrapping.output_list(eachlink);	}
				//System.out.println("This Recipe doesn't have the TO ADD list for not fully Vegan....");	
				}
				else {
					no=no+1;
					if(table_name_toinsert.equals("not_fully_vegan")) {
						Recipes_scrapping.output_list(eachlink);	
						 try {
							Not_fully_vegan.test.pass("Scraping Recipes based on filter ");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}}
       catch (Exception e) {
	// TODO Auto-generated catch block
	e.getMessage();
   }
			
	 
}
			
			public static void output_list(String eachlink) {
				String[] splitstr=eachlink.split("-");
				String last=splitstr[splitstr.length-1];
			    Recipe_id=last.substring(0,last.length()-1);
			    System.out.println("Recipe_id  ------   "+Recipe_id);
				try {
				Recipe_name=r.Recipe_name();
				   System.out.println("Recipe_name ------   "+Recipe_name);
				}
				catch (Exception e) {
		       	 Recipe_name="N/A" ;
		       	 System.out.println("Recipe Name is not available");
				e.getMessage() ;
			 }
				try {
					String data=r.Food_course();
					 if(data!=null) {
						 data =data.toLowerCase() ;
						 for(String eachCuisine : Cuisine_list) {//get cuisine category
			             filter= data.contains(eachCuisine.toLowerCase()) ;
						 if(filter==true) {
							 Cuisine_cg=eachCuisine;
							   System.out.println("Cuisine category ------   "+eachCuisine);
			 } //else {Cuisine_cg="N/A";}
						 }
						 Food_list.add("veg");
						 food:
						 for(String eachfood : Food_list) {// get food category 
				             filter= data.contains(eachfood) ;
							 if(filter==true) {
								 Food_cg=eachfood;
								   System.out.println(" Food category ------   "+eachfood);
								   break food;
							 }//else {Food_cg="N/A";}
							 }
						 for(String eachRecipe : Recipe_list) {// get Recipe category 
				             filter= data.contains(eachRecipe.toLowerCase()) ;
							 if(filter==true) {
								 Recipe_cg=eachRecipe;
								   System.out.println("Recipe category ------   "+eachRecipe);
							 }//else {Recipe_cg="N/A";}
							 }
					}
				}
					catch (Exception e) {
						Food_cg="N/A" ;
						Recipe_cg="N/A" ;
						Cuisine_cg="N/A" ;
			       	 System.out.println("Food , Recipe, Cuisine Category is not available");
					e.getMessage() ;
				 }
				try {
					prep_time=r.Preperation_time();
					   System.out.println("Preperation time ------   "+prep_time);
					}
					catch (Exception e) {
			       	 prep_time="N/A" ;
			       	 System.out.println("Preperation time is not available");
					e.getMessage() ;
				 }
				try {
					cooking_time=r.Cooking_time();
					   System.out.println(" Cooking time ------   "+cooking_time);
					}
					catch (Exception e) {
			       	 cooking_time="N/A" ;
			       	 System.out.println("Cooking time is not available");
					e.getMessage() ;
				 }
				try {
					Recipe_tag=r.Tag();
					   System.out.println("Tags ------   "+Recipe_tag);
					}
					catch (Exception e) {
			       	 Recipe_tag="N/A" ;
			       	 System.out.println("Tags is not available");
					e.getMessage() ;
				 }
				try {
					serving=r.Serving();
					   System.out.println("No. of Serving ------   "+serving);
					}
					catch (Exception e) {
			       	serving="N/A" ;
			       	 System.out.println("No. Of serving is not available");
					e.getMessage() ;
				 }
				try {
					Recipe_desc=r.Recipe_description();
					   System.out.println("Recipe Description ------   "+Recipe_desc);
					}
					catch (Exception e) {
			       	 Recipe_desc="N/A" ;
			       	 System.out.println("Recipe Description is not available");
					e.getMessage() ;
				 }
				try {
					Prep_method=r.Preperation_method();
					   System.out.println("Preperation time ------   "+Prep_method);
					}
					catch (Exception e) {
			       	 Prep_method="N/A" ;
			       	 System.out.println("Preperation time is not available");
					e.getMessage() ;
				 }
				try {
					nutrient_value=r.Nutrition_value();
					   System.out.println("Nutrient value ------   "+nutrient_value);
					}
					catch (Exception e) {
			       	 nutrient_value="N/A" ;
			       	 System.out.println("Nutrient value is not available");
					e.getMessage() ;
				 }
				Recipe_link=eachlink;
				 Recipe_ingredients=Ingredients_list;
				System.out.println("******* "+Recipe_id+" ******* "+Recipe_name+" ****** ");
				try {
					try {
						db.insert_table(table_name_toinsert,Recipe_id,Recipe_name,Recipe_cg,Food_cg, Recipe_ingredients,prep_time,cooking_time,Recipe_tag,serving,Cuisine_cg,Recipe_desc,Prep_method,nutrient_value,eachlink);
						 Not_fully_vegan.test.pass(" Scraped Recipe details are Inserted ");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}
			 

		System.out.println("This Recipe has   TO ADD list for not fully Vegan ....");	
	
		}
			
		}
