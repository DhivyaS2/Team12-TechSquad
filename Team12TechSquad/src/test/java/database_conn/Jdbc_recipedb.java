package database_conn;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.testng.annotations.Test;
import Utility.CommonFunctionutil;
import Utility.*;
import Base.RecipeScrapingTest;

public class Jdbc_recipedb {
	//String JDBC_DRIVER = "org.postgresql.Driver";
	//String DB_URL = "jdbc:postgresql://localhost:5432/Sampledb";
	public static Connection connect;
	Statement statement;
	
	String create_query="CREATE TABLE IF NOT EXISTS lfv_test(Recipe_no SERIAL NOT NULL, RecipeID VARCHAR(255), RecipeName VARCHAR(555),RecipeCategory VARCHAR(3000),FoodCategory VARCHAR(500),Ingredients VARCHAR(3000),preparationTime VARCHAR(355),cookingTime VARCHAR(355),Tags VARCHAR(3000),servingsize VARCHAR(400),CusineCategory VARCHAR(400),recipesteps VARCHAR(5000),prepmethod VARCHAR(5000),nutrivalues varchar(500),url VARCHAR(400));";
	//String create_query="CREATE TABLE LFV_Elimination1(RecipeID VARCHAR(255), RecipeName VARCHAR(255),RecipeCategory VARCHAR(1000),ingredient VARCHAR(2000),preparationTime VARCHAR(200));";
	//String create_query="create table lfv_test(RecipeID VARCHAR(255),Ingredients VARCHAR(3000));";
	@Test
	public void connect() throws ClassNotFoundException, SQLException, IOException {
	String DB,username,password;
	DB=CommonFunctionutil.getDatabase_name();
	username=CommonFunctionutil.getdb_username();
	password=CommonFunctionutil.getdb_password();
		
		
		Class.forName("org.postgresql.Driver");
		connect=DriverManager.getConnection(DB,username,password);
		if (connect != null) {
            System.out.println("Connected to the PostgreSQL server successfully.");
        } else {
            System.out.println("Failed to make connection!");
        }
	}
	@Test
	public void create_table() throws SQLException {
		statement=connect.createStatement();
		statement.executeUpdate(create_query);
		System.out.println("Table Created ");
	}

//		@Test
//
//	//public void insert_table( String RecipeID,String RecipeName,String RecipeCategory,List<String>ingredient,String preparationTime,String cookingTime,String Tags,String servingsize,String recipesteps,String prepmethod,String nutrivalues,String url) throws SQLException
//			//String RecipeName,String RecipeCategory, List<String>ingredient,String preparationTime,String cookingTime,String Tags,String servingsize,String recipesteps,String prepmethod,String nutrivalues,String url) throws SQLException {
//	public void insert_table(String RecipeID,String Ingredients) throws SQLException
//	{
//			//String insert_query="INSERT INTO LFV_Elimination1(RecipeID , RecipeName, RecipeCategory,ingredient,preparationTime)VALUES('"+RecipeID+"', '"+RecipeName+"','"+RecipeCategory+"','"+ingredient+",'"+preparationTime+"');";
//			
//		//	RecipeID,RecipeName,RecipeCategory,arrayingredients,preparationTime,cookingTime,Tags,servingsize,recipesteps,prepmethod,nutrivalues,url
//		//String insert_query="INSERT INTO lfv_test(RecipeID , RecipeName, RecipeCategory,ingredient,preparationTime,cookingTime,Tags,servingsize,recipesteps,prepmethod,nutrivalues,url) VALUES('"+RecipeID+"', '"+RecipeName+"','"+RecipeCategory+"','"+ingredient+",'"+preparationTime+"','"+cookingTime+"','"+Tags+"','"+servingsize+"','"+recipesteps+"','"+prepmethod+"','"+nutrivalues+"','"+url+"');";
//		String insert_query="INSERT INTO lfv_test(RecipeID,Ingredients)values('"+RecipeID+"','"+Ingredients+"');";
//	statement=connect.createStatement();
//		statement.executeUpdate(insert_query);
//
//		System.out.println("Value inserted ");
//		
//	}	

	@Test
	public void insert_table(String RecipeID, String RecipeName, String RecipeCategory, String FoodCategory, String Ingredients,
	                         String preparationTime, String cookingTime, String Tags, String servingsize, String CusineCategory,
	                         String recipesteps, String prepmethod, String nutrivalues, String url) throws SQLException {

	    String insert_query = "INSERT INTO lfv_test(RecipeID, RecipeName, RecipeCategory, FoodCategory, Ingredients, " +
	                          "preparationTime, cookingTime, Tags, servingsize, CusineCategory, " +
	                          "recipesteps, prepmethod, nutrivalues, url) " +
	                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    PreparedStatement preparedStatement = connect.prepareStatement(insert_query);
	    preparedStatement.setString(1, RecipeID);
	    preparedStatement.setString(2, RecipeName);
	    preparedStatement.setString(3, RecipeCategory);
	    preparedStatement.setString(4, FoodCategory);
	    preparedStatement.setString(5, Ingredients);
	    preparedStatement.setString(6, preparationTime);
	    preparedStatement.setString(7, cookingTime);
	    preparedStatement.setString(8, Tags);
	    preparedStatement.setString(9, servingsize);
	    preparedStatement.setString(10, CusineCategory);
	    preparedStatement.setString(11, recipesteps);
	    preparedStatement.setString(12, prepmethod);
	    preparedStatement.setString(13, nutrivalues);
	    preparedStatement.setString(14, url);

	    preparedStatement.executeUpdate();
	    System.out.println("Value inserted successfully.");
	}


	}	
			

	
	
	
	
	

