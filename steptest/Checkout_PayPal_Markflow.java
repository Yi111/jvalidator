package steptest;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Checkout_PayPal_Markflow {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	public Checkout_PayPal_Markflow() throws Throwable {
		driver = Hooks.getWebDriver();
	}

	@When("^Search SKU OO(\\d+)-(\\d+)$")
	public void search_SKU_OO(int arg1, int arg2) throws Throwable {
		driver.findElement(By.name("text")).sendKeys("OO9314-02");
	}

	@When("^Add this product to cart$")
	public void add_this_product_to_cart() throws Throwable {
		driver.findElement(
				By.xpath(".//*[@id='global-header-dropdown']/div[2]/div[1]/form"))
				.submit();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(10000);
	}

	@When("^Go to cart page to click checkout button$")
	public void click_Checkout_in_right_slide_mini_cart() throws Throwable {
		driver.findElement(By.id("global-header-dropdown"))
				.findElement(By.className("cart-button")).click();
		Thread.sleep(20000);
		driver.findElement(By.className("cart-flyout"))
				.findElement(By.className("header"))
				.findElement(By.xpath("span[1]")).click();
		Thread.sleep(30000);
		driver.findElement(By.cssSelector("button.button.highlight")).click();
		Thread.sleep(30000);
	}

	@When("^Log in in checkout page of OO(\\d+)-(\\d+)$")
	public void log_in_in_checkout_page_of_OO(int arg1, int arg2)
			throws Throwable {
		driver.findElement(By.id("container"))
				.findElement(By.className("signsupport"))
				.findElement(By.className("signin"))
				.findElement(By.tagName("button")).click();
		Thread.sleep(15000);
		driver.findElement(By.id("returning-customer-email-address")).click();
		driver.findElement(By.id("returning-customer-email-address")).clear();
		driver.findElement(By.id("returning-customer-email-address")).sendKeys(
				"zx_person@163.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("ZX7758lb");
		driver.findElement(By.xpath("//input[@value='SIGN IN']")).click();
		Thread.sleep(30000);

	}

	@When("^Select PayPal as Billing in checkout page of OO(\\d+)-(\\d+)$")
	public void select_PayPal_as_Billing_in_checkout_page_of_OO(int arg1,
			int arg2) throws Throwable {
		driver.findElement(
				By.xpath(".//*[@id='container']/div[6]/div/section[1]/fieldset[1]/div[2]/div[3]/label/div/ul/li[1]"))
				.click();
		Thread.sleep(30000);
	}

	@When("^Input a new shipping address$")
	public void input_a_new_shipping_address() throws Throwable {
		driver.findElement(By.id("shipping-first-name")).clear();
		driver.findElement(By.id("shipping-first-name")).sendKeys("QA");
		driver.findElement(By.id("shipping-last-name")).clear();
		driver.findElement(By.id("shipping-last-name")).sendKeys("test");
		driver.findElement(By.id("shipping-address1")).clear();
		driver.findElement(By.id("shipping-address1")).sendKeys(
				"49 featherstone");
		driver.findElement(By.id("shipping-city")).clear();
		driver.findElement(By.id("shipping-city")).sendKeys("London");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("shipping-state")))
				.selectByVisibleText("Londonderry");
		Thread.sleep(3000);
		driver.findElement(By.id("shipping-zip")).clear();
		driver.findElement(By.id("shipping-zip")).sendKeys("8055");
		Thread.sleep(10000);
	}

	@When("^Check Accept Terms and Conditions in checkout page of OO(\\d+)-(\\d+)$")
	public void check_Accept_Terms_and_Conditions_in_checkout_page_of_OO(
			int arg1, int arg2) throws Throwable {
		String mouseclickScript = "arguments[0].click();";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(mouseclickScript, driver.findElement(By.id("legal1")));
		Thread.sleep(3000);
	}

	@When("^Click Pay with PayPal in checkout page of OO(\\d+)-(\\d+)$")
	public void click_Pay_with_PayPal_in_checkout_page_of_OO(int arg1, int arg2)
			throws Throwable {
		driver.findElement(By.xpath("//input[@value='Place Order']")).click();
		Thread.sleep(40000);
	}

	@When("^Log in PayPal to Pay this order$")
	public void log_in_PayPal_to_Pay_this_order() throws Throwable {
		driver.findElement(By.id("login_email")).clear();
		driver.findElement(By.id("login_email")).sendKeys("kjensen@oakley.com");
		driver.findElement(By.id("login_password")).clear();
		driver.findElement(By.id("login_password")).sendKeys("oakley#123");
		driver.findElement(By.id("submitLogin")).click();
		Thread.sleep(30000);
		// name=1446630771764 | ]]
		driver.findElement(By.id("continue_abovefold")).click();
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("div.subnav-header")).click();
		Thread.sleep(40000);
	}

	@Then("^Place success to get the order confirm page$")
	public void place_success_to_get_the_order_confirm_page() throws Throwable {
		assertEquals("Order Confirmation", driver.getTitle());
		driver.findElement(By.xpath(".//*[@id='global-header-dropdown']/div[1]/div[4]/a")).click();
		
		driver.findElement(By.linkText("Sign Out")).click();
		//driver.findElement(By.id("profile-menu-modal")).findElement(By.className("modal-box-body")).findElement(By.xpath("/ul/li[6]/a")).click();
		//driver.findElement(By.xpath(".//*[@id='profile-menu-modal']/div[2]/ul/li[6]/a")).click();
		Thread.sleep(20000);
		//driver.close();
		driver.quit();
	}

}
