package steptest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	private static WebDriver driver;
	
	private static Map<String, String> stringCache;

	public static WebDriver getWebDriver(){
		return driver;
	}
	
	public static Map<String, String> getStringCache(){
		return stringCache;
	}
	
	
	@Before
	public void before() {
		if (driver == null) 
		{
			System.setProperty("webdriver.chrome.driver",
					"C:\\selenium\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
			
//			driver = new FirefoxDriver();
		
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		if (stringCache == null)
		{
			stringCache = new HashMap<String, String>();
	
		}
	}
	
	@After
	public void after() 
	{
		driver.quit();
		driver = null;
		stringCache = null;
	}
}
