package steptest;

import static org.junit.Assert.assertEquals;


//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


//import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class checkoutCCLoginExistingBillingAndShipping {

	private WebDriver driver;
	private String baseUrl;

	@Given("^Add Product To Cart with Login$")
	public void add_Product_To_Cart_with_Login() throws Throwable {
		// driver = new FirefoxDriver();
		driver = Hooks.getWebDriver();
		//driver.manage().window().maximize();
		baseUrl = "http://hyb-pre-prod-uk.oakley.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl + "/");

		driver.findElement(
				By.xpath(".//*[@id='global-header-dropdown']/div[3]/ul/li[1]/a"))
				.click();
		Thread.sleep(5000);
		driver.findElement(
				By.xpath(".//*[@id='global-header-dropdown']/div[4]/div/div/div[1]/div/div[1]/div/ul/li[7]/a"))
				.click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a.image.product-url")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(20000);
		driver.findElement(By.cssSelector(".login-button>a")).click();
		Thread.sleep(20000);
		driver.findElement(By.id("j_username")).clear();
		driver.findElement(By.id("j_username")).sendKeys(
				"20151104143540@163.com");
		driver.findElement(By.id("j_password")).clear();
		driver.findElement(By.id("j_password")).sendKeys("Password!@");

		driver.findElement(By.cssSelector("input.button.highlight")).click();
	}

	@When("^checkout with CC existing billing and shipping address$")
	public void checkout_with_CC_existing_billing_and_shipping_address()
			throws Throwable {
		Thread.sleep(20000);
		driver.findElement(By.cssSelector(".o-icon-cart")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(".header>span")).click();
		Thread.sleep(5000);

		driver.findElement(
				By.xpath(".//*[@id='container']/div[6]/div/div/div/div[4]/div[2]/div/div[4]/div/form[1]/button"))
				.click();
		Thread.sleep(30000);

		driver.findElement(
				By.xpath(".//*[@id='container']/div[6]/div/section[1]/fieldset[1]/div[2]/div[1]/label/div/ul/li[2]"))
				.click();
		Thread.sleep(10000);
		driver.findElement(By.id("saved-card-ccv")).clear();
		driver.findElement(By.id("saved-card-ccv")).sendKeys("123");
		Thread.sleep(1000);
		driver.findElement(
				By.xpath(".//*[@id='shippingAddressForm']/fieldset[1]/header/div/label"))
				.click();
		Thread.sleep(10000);
		driver.findElement(By.id("shipping-address-id")).click();
		driver.findElement(By.id("shipping-address-id"))
				.findElement(By.xpath("option[2]")).click();
		Thread.sleep(2000);
		String mouseclickScript = "arguments[0].click();";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(mouseclickScript, driver.findElement(By.id("legal1")));
		Thread.sleep(3000);
		driver.findElement(
				By.xpath(".//*[@id='placeOrderForm']/section/fieldset[1]/div/div[2]/div/input"))
				.click();
		Thread.sleep(20000);
		assertEquals("Order Confirmation", driver.getTitle());
	}

	@Then("^Place Order Successfully With CC$")
	public void place_Order_Successfully_With_CC() throws Throwable {
		String ordernumber = driver.findElement(
				By.cssSelector("div.headline-order-number")).getText();
		Thread.sleep(13000);
		driver.findElement(By.linkText("Order Status")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("order-number")).clear();
		driver.findElement(By.id("order-number")).sendKeys(ordernumber);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("zx_person@163.com");
		driver.findElement(By.xpath("//form[@id='order-detail']/div[3]"))
				.click();
		Thread.sleep(15000);
		driver.findElement(By.cssSelector("input.button.black")).click();
		Thread.sleep(5000);
		assertEquals("Order Details", driver.getTitle());
		Thread.sleep(20000);
		//log out
		driver.findElement(By.xpath(".//*[@id='global-header-dropdown']/div[1]/div[4]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(20000);	
		//driver.close();
		driver.quit();
	}

}
