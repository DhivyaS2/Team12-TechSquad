package JDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.testng.annotations.Test;

import Utility.PropertyFunction;

public class CRUD_DB {
	public static Connection connect;
	Statement statement;
	String b="Mustang";
	String create_query="CREATE TABLE Not_fully_vegan  ( Recipe_id VARCHAR(255), Recipe_name VARCHAR(255),Recipe_category VARCHAR(255),Food_category VARCHAR(255),Ingredients  VARCHAR(1000),Preperation_time VARCHAR(255), Cooking_time VARCHAR(255), Recipe_tag VARCHAR(255), No_serving VARCHAR(255), Cuisine_category VARCHAR(255), Recipe_desc VARCHAR(3000) , Prep_method VARCHAR(4000),nutrient_value VARCHAR(1000) ,link VARCHAR(255));";
	String insert_query;
@Test
	public void connect() throws ClassNotFoundException, SQLException, IOException {
	String DB,username,password;
	DB=PropertyFunction.getDatabase_name();
	username=PropertyFunction.getdb_username();
	password=PropertyFunction.getdb_password();
		
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
public void insert_table( String tb_name,String Recipe_id,String Recipe_name,String Recipe_cg, String Food_cg,List<String> Ingredients,String prep_time, String cooking_time, String Recipe_tag, String serving, String Cuisine_cg, String Recipe_desc, String Prep_method, String nutrient_value, String link ) throws SQLException {
	
	if(tb_name.equals("not_fully_vegan")) {
	insert_query="INSERT INTO Not_fully_vegan (Recipe_id , Recipe_name,Recipe_category,Food_category, Ingredients, Preperation_time, Cooking_time ,Recipe_tag, No_serving, Cuisine_category ,Recipe_desc,Prep_method,nutrient_value,   link ) VALUES('"+Recipe_id+"', '"+Recipe_name+"','"+Recipe_cg+"','"+Food_cg+"','"+Ingredients+"','"+prep_time+"','"+cooking_time+"','"+Recipe_tag+"','"+serving+"','"+Cuisine_cg +"','"+Recipe_desc+"','"+Prep_method+"','"+nutrient_value+"','"+link+"');";
	}
	if(tb_name.equals("Allergy")) {
		insert_query="INSERT INTO Allergy (Recipe_id , Recipe_name,Recipe_category,Food_category, Ingredients, Preperation_time, Cooking_time ,Recipe_tag, No_serving, Cuisine_category ,Recipe_desc,Prep_method,nutrient_value,   link ) VALUES('"+Recipe_id+"', '"+Recipe_name+"','"+Recipe_cg+"','"+Food_cg+"','"+Ingredients+"','"+prep_time+"','"+cooking_time+"','"+Recipe_tag+"','"+serving+"','"+Cuisine_cg +"','"+Recipe_desc+"','"+Prep_method+"','"+nutrient_value+"','"+link+"');";
		}
	statement=connect.createStatement();
	statement.executeUpdate(insert_query);
	System.out.println("Value inserted in the table");
}

//public void insert_table(String tb_name,String RecipeID, String RecipeName, String RecipeCategory, String FoodCategory,String Ingredients,
//        String preparationTime, String cookingTime, String Tags, String servingsize, String CusineCategory,
//        String recipesteps, String prepmethod, String nutrivalues, String url) throws SQLException {
//	if(tb_name.equals("not_fully_vegan")) {
//		insert_query="INSERT INTO Not_fully_vegan (Recipe_id , Recipe_name,Recipe_category,Food_category, Ingredients, Preperation_time, Cooking_time ,Recipe_tag, No_serving, Cuisine_category ,Recipe_desc,Prep_method,nutrient_value, link )VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	}
//	if(tb_name.equals("Allergy")) {
//		insert_query="INSERT INTO Allergy(Recipe_id , Recipe_name,Recipe_category,Food_category, Ingredients, Preperation_time, Cooking_time ,Recipe_tag, No_serving, Cuisine_category ,Recipe_desc,Prep_method,nutrient_value,   link )VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//					}
//PreparedStatement preparedStatement = connect.prepareStatement(insert_query);
//preparedStatement.setString(1, RecipeID);
//preparedStatement.setString(2, RecipeName);
//preparedStatement.setString(3, RecipeCategory);
//preparedStatement.setString(4, FoodCategory);
//preparedStatement.setString(5, Ingredients);
//preparedStatement.setString(6, preparationTime);
//preparedStatement.setString(7, cookingTime);
//preparedStatement.setString(8, Tags);
//preparedStatement.setString(9, servingsize);
//preparedStatement.setString(10, CusineCategory);
//preparedStatement.setString(11, recipesteps);
//preparedStatement.setString(12, prepmethod);
//preparedStatement.setString(13, nutrivalues);
//preparedStatement.setString(14, url);
//
//preparedStatement.executeUpdate(insert_query);
//System.out.println("Value inserted ");
}

