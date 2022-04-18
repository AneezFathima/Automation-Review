package tests;

import java.util.List;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import data.SampleData;
import pages.TodosPage;

public class OpentodosTest extends TestBase{
	
	@Test(groups = "itemlist")
	public void OpentodosTest_init() throws Exception{
		
		TodosPage todopage = PageFactory.initElements(driver, TodosPage.class);
		SampleData objSampleData = new SampleData();
		List<String> strArytodoListData = objSampleData.gettodoslistData();
		
		todopage.verifyOpenScreen();
		
		for (int i=0; i<strArytodoListData.size(); i++) {
			todopage.enterValuesInputbox(strArytodoListData.get(i));
			todopage.checktodolistValues(strArytodoListData.get(i));
		}
	}
}