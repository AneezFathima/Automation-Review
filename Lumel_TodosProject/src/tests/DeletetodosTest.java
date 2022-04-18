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

public class DeletetodosTest extends TestBase{
	
	@Test(groups = "DeleteList", dependsOnGroups="itemlist")
	public void DeletetodosTest_init() throws Exception{
		
		TodosPage todopage = PageFactory.initElements(driver, TodosPage.class);
		SampleData objSampleData = new SampleData();
		List<String> strAryDeletetodoListData = objSampleData.gettodosDeletelistData();
		
		
		int initialcount = todopage.verifyremainingcount();
		List <WebElement> todovaluelist = driver.findElements(By.xpath("//section/section/ul/li"));
		int todovalueListSize = todovaluelist.size();
			 
		for(int i = 1; i<=todovalueListSize; i++) {
			try{
				WebElement delete_list = driver.findElement(By.xpath("//section/section/ul/li["+i+"]/div/label"));
				String actualTodoListValue = driver.findElement(By.xpath("//section/section/ul/li["+i+"]/div/label")).getText();
				
				for (int j=0; j<strAryDeletetodoListData.size();j++) {
					 if (actualTodoListValue.equals(strAryDeletetodoListData.get(j))) {
						 
						 //Mouse Hovering on the element
						 Actions act = new Actions(driver);
						 act.moveToElement(delete_list).build().perform();
					
						 //Delete button of the corresponding item
						 WebElement delete_btn = driver.findElement(By.xpath("//section/section/ul/li["+i+"]/div/button"));
						 act.click(delete_btn).build().perform();
						 
					
						 //check if the item is removed from the list
						 List <WebElement> currentvaluelist = driver.findElements(By.xpath("//section/section/ul/li"));
						 todovalueListSize = currentvaluelist.size();
						 
						 if(todovalueListSize==0)
							 Reporter.log("ToDo List is Empty");
						 else {
							 for(int k=1;k<=currentvaluelist.size();k++) {
								 WebElement current_item = driver.findElement(By.xpath("//section/section/ul/li["+k+"]/div/label"));
								 
								 if(current_item.getText().equalsIgnoreCase(strAryDeletetodoListData.get(j)))
									 Reporter.log("Given item is not removed from the list");
								 else
									 Reporter.log("Given item "+strAryDeletetodoListData.get(j)+" is not available in row "+k);
							 }
							 //check if the count is reduced
							 int currentcount = todopage.verifyremainingcount();
							 
							if (initialcount==currentcount)
								Reporter.log("The counter value is not reduced");
							else {
								Reporter.log("Itemlist reduced from "+initialcount+" to "+currentcount);
	
							}
						 }
					 }
				}
			 }
			catch(Exception e){
					     System.out.println(e.getMessage());
			}
		}
	}
}