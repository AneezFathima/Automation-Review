package pages;

import static org.testng.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class TodosPage{
	WebDriver driver;
	 
    public TodosPage(WebDriver driver){
       this.driver=driver;
    }
    
    //Using FindBy for locating elements
    @FindBy(how=How.XPATH, using="//ng-view/section/header/h1") WebElement todoHeader;
    @FindBy(how=How.XPATH, using="//form/input") WebElement todoInput;
    @FindBy(how=How.XPATH, using="//footer/span/strong") WebElement footerText;
    @FindBy(how=How.XPATH, using="//ng-view/section/footer/ul/li[2]/a") WebElement activeLink;
    @FindBy(how=How.XPATH, using="//ng-view/section/footer/ul/li[3]/a") WebElement CompletedLink;
   
    
	// Method to add values in to-do list
	public void enterValuesInputbox(String todolistValue){
		
		todoInput.sendKeys(todolistValue);
		Reporter.log("\n\n The value "+todolistValue+" is entered in the textbox \n\n");
		
		Actions act = new Actions(driver);
		act.sendKeys(Keys.RETURN).build().perform();
	}
	
	//Method to add check the added values
	public void verifyOpenScreen() {
			String TodoHeaderValue = todoHeader.getText();
			
			if (TodoHeaderValue.equalsIgnoreCase("todos"))
				Reporter.log("The todos page is getting displayed");
			else {
				Reporter.log("The todos page is not getting displayed");
				assertFalse(false, "The todos page is not getting displayed");
			}
	}
	
	// Method to verify the todos list values
	public void checktodolistValues(String todolistValue) {
			
		//Check whether the values are added to the list
		 List <WebElement> todovaluelist = driver.findElements(By.xpath("//section/section/ul/li"));
		 int todovalueListSize = todovaluelist.size();
			 
		for(int i = 1; i<=todovalueListSize; i++) {
			 String actualTodoListValue = driver.findElement(By.xpath("//section/section/ul/li["+i+"]/div/label")).getText();
			 if (actualTodoListValue.equals(todolistValue)) {
				Reporter.log("\n Given value "+todolistValue+" is added to the todos list \n\n");
				break;
			}
	    }
	}
	
	// Method to verify remaining count
	public int verifyremainingcount() {
		String footervalue = footerText.getText();
		int number = Integer.parseInt(footervalue);
		return number;
	}
	
	// Method to check Strike-through functionality
	public void checkStrikeThrough(String todoList,int list) throws InterruptedException {
		
		String buttonval =driver.findElement(By.xpath("/html/body/ng-view/section/section/ul/li["+list+"]/div/label")).getCssValue("text-decoration-line");
		
		if (buttonval.equalsIgnoreCase("line-through"))
			Reporter.log("\n The todo list "+todoList+" is striked through in the display \n");
		else
			Reporter.log("\n The todo list "+todoList+" is not striked through in the display \n");
	}
	
	//Method to verify active todos list
	public void verifyActivetodoslist() throws InterruptedException {
		
		 // note down count values
		 List <WebElement> todovaluelist = driver.findElements(By.xpath("//section/section/ul/li"));
		 int totalitemscount = todovaluelist.size(); //4
		 
		 List<String> strAryCompleted = new ArrayList<String>();
		 List<String> strAryActive = new ArrayList<String>();
		 
		 
		 // note down available active items and completed items
		 for(int i = 1; i<=totalitemscount; i++) {
			 
			 String CompVal = driver.findElement(By.xpath("//section/section/ul/li["+i+"]/div/label")).getCssValue("text-decoration-line");
			 
			 if (CompVal.equalsIgnoreCase("line-through")) {
				 strAryCompleted.add(driver.findElement(By.xpath("//section/section/ul/li["+i+"]/div/label")).getText());	 
			 }
			 else {
				 strAryActive.add(driver.findElement(By.xpath("//section/section/ul/li["+i+"]/div/label")).getText());
			 }
		 }
		 
		 // Verify items in Completed link 
		 CompletedLink.click();
		 WebDriverWait wait=new WebDriverWait(driver, 10);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ng-view/section/section/ul/li")));
		 List <WebElement> completedList = driver.findElements(By.xpath("//ng-view/section/section/ul/li"));
		 List <String> strcurCompleted = new ArrayList <String> ();
		 int completeditemscount = completedList.size();
		
		 
		 for(int i = 1; i <= completeditemscount; i++) { 
			 String val= driver.findElement(By.xpath("//ng-view/section/section/ul/li["+i+"]/div/label")).getText();
			 strcurCompleted.add(val);
		 }
		 
		 if (strAryCompleted.equals(strcurCompleted) == true) 
	         Reporter.log("\n Completed todo list items are displaying correctly \n"+strcurCompleted);
	     else
	         Reporter.log("\n Completed todo list items are not displaying correctly \n"+strcurCompleted);

		 //Verify items in Active link
		 activeLink.click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section/section/ul/li")));
		 List <WebElement> activeList = driver.findElements(By.xpath("//section/section/ul/li"));
		 List <String>strcurActive = new ArrayList <String>();
		 int activeitemscount = activeList.size();
		 
		 for(int i = 1; i <= activeitemscount; i++) { 
			 String a = driver.findElement(By.xpath("//section/section/ul/li["+i+"]/div/label")).getText();
			 strcurActive.add(a);
		 }
		 
		 if (strAryActive.equals(strcurActive) == true) 
	         Reporter.log("\n Active todo list items are displaying correctly \n"+strcurActive);
	     else
	         Reporter.log("\n Active todo list items are not displaying correctly \n"+strcurActive);
	}
}