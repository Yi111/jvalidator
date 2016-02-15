package steptest;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

//import org.openqa.selenium.firefox.FirefoxDriver;

public class CheckoutWithCC {
	private WebDriver driver;
	private String baseUrl;

	@Given("^Expand women drop drow list$")
	public void expand_women_drop_drow_list() throws Throwable {
		driver = Hooks.getWebDriver();
		baseUrl = "http://hyb-pre-prod-uk.oakley.com/";
		driver.get(baseUrl + "/");
		Thread.sleep(2000);
		driver.findElement(
				By.xpath(".//*[@id='global-header-dropdown']/div[3]/ul/li[2]/a"))
				.click();
	}

	@When("^Click tops under Apparel$")
	public void click_tops_under_Apparel() throws Throwable {
		Thread.sleep(2000);
		driver.findElement(
				By.xpath(".//*[@id='global-header-dropdown']/div[5]/div/div/div[1]/div/div[3]/div/ul/li[3]/a"))
				.click();
	}

	@When("^Select a product go to PDP page$")
	public void select_a_product_go_to_PDP_page() throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("a.image.product-url")).click();
	}

	@When("^Click ADD to cart$")
	public void click_ADD_to_cart() throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@When("^Continue check out$")
	public void continue_check_out() throws Throwable {
		// Not delete this sleep
		Thread.sleep(20000);
		driver.findElement(By.id("global-header-dropdown"))
				.findElement(By.className("cart-button")).click();
		Thread.sleep(20000);
		driver.findElement(By.className("cart-flyout"))
				.findElement(By.className("header"))
				.findElement(By.xpath("span[1]")).click();
		Thread.sleep(30000);
		driver.findElement(
				By.xpath(".//*[@id='container']/div[6]/div/div/div/div[4]/div[2]/div/div[4]/div/form[1]/button"))
				.click();
		// throw new PendingException();
	}

	@When("^Select CC radio button$")
	public void select_CC_radio_button() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Thread.sleep(50000);
		driver.findElement(
				By.xpath(".//*[@id='container']/div[6]/div/section[1]/fieldset[1]/div[2]/div[1]/label"))
				.click();
		// throw new PendingException();
	}

	@When("^Fill into all information$")
	public void fill_into_all_information() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Thread.sleep(50000);
		waitForElement(driver, By.id("card-name")).sendKeys("Test");
		Thread.sleep(2000);
		driver.findElement(By.id("card-number")).sendKeys("4111111111111111");
		Thread.sleep(2000);
		driver.findElement(By.id("ccv")).sendKeys("123");
		Thread.sleep(2000);
		new Select(driver.findElement(By.id("credit-card-month")))
				.selectByVisibleText("March");
		Thread.sleep(2000);
		new Select(driver.findElement(By.id("credit-card-year")))
				.selectByVisibleText("2017");

		Thread.sleep(2000);
		driver.findElement(By.id("country")).click();
		new Select(driver.findElement(By.id("country")))
				.selectByVisibleText("United Kingdom");
		Thread.sleep(2000);
		driver.findElement(By.id("first-name")).sendKeys("Test");
		// driver.findElement(By.id("last-name")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("last-name")).sendKeys("Test");
		// driver.findElement(By.id("email-address")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("email-address"))
				.sendKeys("zx_person@163.com");
		// driver.findElement(By.id("phone-number")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("phone-number")).sendKeys("9499336654");
		// driver.findElement(By.id("address1")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("address1")).sendKeys("49 featherstone");
		// driver.findElement(By.id("city")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("city")).sendKeys("London");
		Thread.sleep(2000);
		new Select(driver.findElement(By.id("state")))
				.selectByVisibleText("Londonderry");
		Thread.sleep(2000);
		driver.findElement(By.id("zip")).sendKeys("8055");
		Thread.sleep(5000);
		// throw new PendingException();
	}

	@When("^Select Terms of Service of Legal Agreemen checkbox$")
	public void select_Terms_of_Service_of_Legal_Agreemen_checkbox()
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String mouseclickScript = "arguments[0].click();";

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript(mouseclickScript, driver.findElement(By.id("legal1")));

		driver.findElement(By.xpath(".//*[@id='container']/div[6]/div"))
				.click();
		Thread.sleep(10000);
		// throw new PendingException();
	}

	@When("^Click place order button$")
	public void click_place_order_button() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driver.findElement(
				By.xpath(".//*[@id='placeOrderForm']/section/fieldset[1]/div/div[2]/div/input"))
				.click();
		// throw new PendingException();
	}

	@When("^Check order status$")
	public void check_order_status() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Thread.sleep(20000);
		assertEquals("Order Confirmation", driver.getTitle());
		// order status
		String ordernumber = driver.findElement(
				By.cssSelector("div.headline-order-number")).getText();
		driver.findElement(By.linkText("Order Status")).click();

		Thread.sleep(10000);
		driver.findElement(By.id("order-number")).clear();
		driver.findElement(By.id("order-number")).sendKeys(ordernumber);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("zx_person@163.com");
		driver.findElement(By.xpath("//form[@id='order-detail']/div[3]"))
				.click();
		driver.findElement(By.cssSelector("input.button.black")).click();
	}

	@Then("^Check order status successfully$")
	public void check_order_status_successfully() throws Throwable {
		Thread.sleep(20000);
		assertEquals("Order Details", driver.getTitle());
		Thread.sleep(10000);
		driver.quit();
	}

	private WebElement waitForElement(WebDriver driver, By path)
			throws InterruptedException {
		boolean flag = true;
		int maxCount = 600;
		int count = 0;
		WebElement ele = null;
		while (flag && count < maxCount) {
			try {
				ele = driver.findElement(path);
				count++;
				flag = false;
			} catch (Exception e) {
				Thread.sleep(1000);

			}
		}
		return ele;
	}
}
