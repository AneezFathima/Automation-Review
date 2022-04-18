package tests;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.TodosPage;

public class FilteringtodosTest extends TestBase{
	
	@Test(groups = "FilterList", dependsOnGroups={"itemlist","completeList"})
	public void FilteringtodosTest_init() throws Exception{
		TodosPage todopage = PageFactory.initElements(driver, TodosPage.class);
		todopage.verifyActivetodoslist();
	}
}