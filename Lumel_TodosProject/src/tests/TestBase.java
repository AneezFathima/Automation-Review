package tests;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
 
public class TestBase {
	
public static WebDriver driver = null;

@BeforeSuite
public void initialize() throws IOException{
	
	System.setProperty("webdriver.chrome.driver","/Users/aneez/Desktop/Aneez/Learnings/Selenium/Installer/Drivers/chromedriver");
	driver = new ChromeDriver();
	
	driver.manage().window().maximize();
	System.out.println("The window is maximized ");
	
	//Wait for 5 seconds between each statement
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	
	//To open todos list
	driver.get("https://todomvc.com/examples/angularjs/#/");
}

@AfterSuite
//Test cleanup
public void TeardownTest()
    {
        TestBase.driver.quit();
    }
 
}