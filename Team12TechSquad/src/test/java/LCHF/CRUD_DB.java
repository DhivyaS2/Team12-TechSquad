package LCHF;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.annotations.Test;

//import scraping.Recipes_scrapping;

public class CRUD_DB {
	public static Connection connect;
	Statement statement;
	String b="Mustang";
	String create_query="CREATE TABLE LCHFRecipes(Recipe_no SERIAL PRIMARY KEY, Recipe_id VARCHAR(255), Recipe_name VARCHAR(2000),recipeCategory VARCHAR(255),foodCategory VARCHAR(255),Ingredients VARCHAR(4000), PrepTime VARCHAR(2000),CookTime VARCHAR(2000), Tags VARCHAR(2000), Servings VARCHAR(2000),CuisineCategory VARCHAR(255), Description VARCHAR(7000), Methods VARCHAR(7000), Nutrients VARCHAR(2000), URL VARCHAR(255))";
	
@Test
	public void connect() throws ClassNotFoundException, SQLException, IOException {
	String DB,username,password;
//	DB=CommonFunction.getDatabase_name();
//	username=CommonFunction.getdb_username();
//	password=CommonFunction.getdb_password();
	DB="ReceipeScrapping";
	username="postgres";
	password="admin123";
		
		Class.forName("org.postgresql.Driver");
		connect=DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+DB,username,password);
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

@Test
public void insert_table(String Recipe_id,String Recipe_name,String recipeCategory,String foodCategory, String Ingredients, String PrepTime, String CookTime, String Tags ,String Servings,String cuisineCategory, String Description,String Methods ,String Nutrients, String URL) throws SQLException {
	String insert_query="INSERT INTO LCHFRecipes(Recipe_id , Recipe_name,recipeCategory,foodCategory, Ingredients, PrepTime,CookTime,Tags,Servings,CuisineCategory ,Description,Methods,Nutrients,URL) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	//statement=connect.createStatement();
	//statement.executeUpdate(insert_query);
	//System.out.println("Value inserted ");
	PreparedStatement pstmt = connect.prepareStatement(insert_query);
	 pstmt.setString(1, Recipe_id);
     pstmt.setString(2, Recipe_name);
     pstmt.setString(3, recipeCategory);
     pstmt.setString(4, foodCategory);
     pstmt.setString(5, Ingredients);
     pstmt.setString(6, PrepTime);
     pstmt.setString(7, CookTime);
     pstmt.setString(8, Tags);
     pstmt.setString(9, Servings);
     pstmt.setString(10, cuisineCategory);
     pstmt.setString(11, Description);
     pstmt.setString(12, Methods);
     pstmt.setString(13, Nutrients);
     pstmt.setString(14, URL);

    pstmt.executeUpdate();
    System.out.println("Value inserted");
    
}
}