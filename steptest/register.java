package steptest;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class register {
	
	private WebDriver driver;
	  private String baseUrl;
	  
	  Date date= new Date();
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	  String str = sdf.format(date);
	  String email= str + "@163.com";
	
	@Given("^Navigate register account page$")
	public void navigate_register_account_page() throws Throwable  {
		//driver = new FirefoxDriver();
		driver = Hooks.getWebDriver();
	    baseUrl = "http://hyb-pre-prod-uk.oakley.com";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get(baseUrl + "/en/");
	    driver.findElement(By.cssSelector(".login-button>a")).click();
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}

	@When("^Register with valid credentials$")
	public void register_with_valid_credentials() throws Throwable  {
		driver.findElement(By.id("register.firstName")).click();
	    driver.findElement(By.id("register.firstName")).clear();
	    driver.findElement(By.id("register.firstName")).sendKeys("Grace");
	    Thread.sleep(2000);
	    driver.findElement(By.id("register.lastName")).clear();
	    driver.findElement(By.id("register.lastName")).sendKeys("Gao");
	    Thread.sleep(2000);
	    driver.findElement(By.id("register.email")).clear();
	    driver.findElement(By.id("register.email")).sendKeys(email);
	    Thread.sleep(2000);
	    driver.findElement(By.id("pwd")).clear();
	    driver.findElement(By.id("pwd")).sendKeys("Password!@");
	    Thread.sleep(2000);
	    driver.findElement(By.id("register.checkPwd")).clear();
	    driver.findElement(By.id("register.checkPwd")).sendKeys("Password!@");
	    Thread.sleep(2000);
	    if ( !driver.findElement(By.xpath(".//*[@id='registerForm']/div[6]/label")).isSelected() )
	    {
	         driver.findElement(By.xpath(".//*[@id='registerForm']/div[6]/label")).click();
	    }
	    driver.findElement(By.cssSelector("#registerForm > input.button.highlight")).click();
	 }

	@Then("^Login automatically$")
	public void login_automatically() throws Throwable  {
		//boolean username=driver.findElement(By.linkText("GRACE")).isDisplayed();
		//return username;
	    Thread.sleep(5000);
		assertEquals("Profile", driver.getTitle());
		Thread.sleep(10000);
		driver.findElement(By.xpath(".//*[@id='global-header-dropdown']/div[1]/div[4]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(10000);
		//driver.close();
		driver.quit();
	}

}
