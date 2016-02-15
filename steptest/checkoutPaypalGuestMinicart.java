package steptest;

import static org.junit.Assert.assertEquals;











//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;











import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class checkoutPaypalGuestMinicart {
	
	private WebDriver driver;
	private String baseUrl;
	  

@Given("^Add Product To Cart$")
public void add_Product_To_Cart() throws Throwable {
	//driver = new FirefoxDriver();
	//System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
	  //driver = new ChromeDriver();
	driver = Hooks.getWebDriver();
    driver.manage().window().maximize();
    baseUrl = "http://hyb-pre-prod-uk.oakley.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get(baseUrl + "/");
    driver.findElement(By.xpath(".//*[@id='global-header-dropdown']/div[3]/ul/li[2]/a")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("(//a[contains(text(),'Polarized')])[2]")).click();
    Thread.sleep(2000);
    driver.findElement(By.cssSelector("a.image.product-url")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(4000);
  }

@When("^checkout with paypal on mini cart$")
public void checkout_with_paypal_on_mini_cart() throws Throwable {
	driver.findElement(By.cssSelector("button.button.paypal")).click();
    
	Thread.sleep(30000);
    driver.findElement(By.id("login_email")).clear();
    driver.findElement(By.id("login_email")).sendKeys("kjensen@oakley.com");
    driver.findElement(By.id("login_password")).clear();
    driver.findElement(By.id("login_password")).sendKeys("oakley#123");
    driver.findElement(By.id("submitLogin")).click();
    driver.findElement(By.id("continue_abovefold")).click();
   
    Thread.sleep(60000);
    //driver.findElement(By.id("same-shipping")).click();
    driver.findElement(By.id("shipping-country")).click();
    new Select(driver.findElement(By.id("shipping-country"))).selectByVisibleText("United Kingdom");
    driver.findElement(By.id("shipping-first-name")).clear();
    driver.findElement(By.id("shipping-first-name")).sendKeys("Yanzhi");
    driver.findElement(By.id("shipping-last-name")).clear();
    driver.findElement(By.id("shipping-last-name")).sendKeys("Gao");
    driver.findElement(By.id("shipping-address1")).clear();
    driver.findElement(By.id("shipping-address1")).sendKeys("49 featherstone");
    driver.findElement(By.id("shipping-city")).clear();
    driver.findElement(By.id("shipping-city")).sendKeys("London");
    driver.findElement(By.id("shipping-state")).click();
    new Select(driver.findElement(By.id("shipping-state"))).selectByVisibleText("Londonderry");
    driver.findElement(By.cssSelector("#shipping-state > option[value=\"GB-LD\"]")).click();
    driver.findElement(By.id("shipping-state")).click();
    driver.findElement(By.cssSelector("#shipping-state > option[value=\"GB-LD\"]")).click();
    driver.findElement(By.id("shipping-zip")).clear();
    driver.findElement(By.id("shipping-zip")).sendKeys("8055");
    
    String mouseclickScript = "arguments[0].click();";

    JavascriptExecutor js = (JavascriptExecutor) driver;

    js.executeScript(mouseclickScript, driver.findElement(By.id("legal1")));
   
    driver.findElement(By.xpath(".//*[@id='placeOrderForm']/section/fieldset[1]/div/div[2]/div/input")).click();
    
    Thread.sleep(30000);
   
    assertEquals("Order Confirmation", driver.getTitle());
 }

@Then("^Place Order Successfully Paypal Express$")
public void place_Order_Successfully_Paypal_Express() throws Throwable {
   	String ordernumber = driver.findElement(By.cssSelector("div.headline-order-number")).getText();
    driver.findElement(By.linkText("Order Status")).click();
    driver.findElement(By.id("order-number")).clear();
    driver.findElement(By.id("order-number")).sendKeys(ordernumber);
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("kjensen@oakley.com");
    driver.findElement(By.xpath("//form[@id='order-detail']/div[3]")).click();
    driver.findElement(By.cssSelector("input.button.black")).click();
    Thread.sleep(20000);
    assertEquals("Order Details", driver.getTitle());
    Thread.sleep(10000);
    //driver.close();
    driver.quit();
  }

}
