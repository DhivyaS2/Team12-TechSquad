package Base;
	import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.testng.annotations.Test;

import Utility.DataDriven;

	public class Allergies {
		 
		@Test
		public void Recipes() throws ClassNotFoundException, IOException, SQLException {
			List<String> excel_list=DataDriven.reader("Allergies_list","Allergies");
			Recipes_scrapping r = new Recipes_scrapping();
			r.launch();
			r.IterateAtoZ_tab(excel_list,"Allergy");
			
		}


}
