package tests;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import data.SampleData;
import pages.TodosPage;

public class CompletetodosTest extends TestBase {
	
	@Test(groups = "completeList", dependsOnGroups="itemlist")
	public void CompletetodosTest_init() throws Exception{
		
		TodosPage todopage = PageFactory.initElements(driver, TodosPage.class);
		
		SampleData objSampleData = new SampleData();
		List<String> strAryCompletedtodoListData = objSampleData.gettodosCompletedlistData();
		List <WebElement> todovaluelist = driver.findElements(By.xpath("//section/section/ul/li"));
		int todovalueListSize = todovaluelist.size();
		int count = todovaluelist.size();
		int no_value=0;
		 
		for(int i = 1; i<=todovalueListSize; i++) {
			String actualTodoListValue = driver.findElement(By.xpath("//section/section/ul/li["+i+"]/div/label")).getText();
				
			for (int j=0; j<strAryCompletedtodoListData.size();j++) {
				if (actualTodoListValue.equals(strAryCompletedtodoListData.get(j))) {
					driver.findElement(By.xpath("//ul/li["+i+"]/div/input")).click();
					no_value =no_value+1;
					
				if(todopage.verifyremainingcount()== (count-1)) {
					Reporter.log("\n The item "+actualTodoListValue+" is marked complete in the list \n");
					count=count-1;
					Reporter.log("\n The item count value is reduced to "+count);
							 
					//Once removed checking the strikethrough functionality
					todopage.checkStrikeThrough(strAryCompletedtodoListData.get(j),i);	
				}
			}
		}
	}
		 if (no_value == strAryCompletedtodoListData.size())
			 Reporter.log("Given todoslist is marked as complete");
		 else
			 Reporter.log("The entered todolist is not available in the list"); 
  }
}