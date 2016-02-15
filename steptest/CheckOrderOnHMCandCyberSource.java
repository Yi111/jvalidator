package steptest;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CheckOrderOnHMCandCyberSource {

	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	public CheckOrderOnHMCandCyberSource() throws Throwable {
		driver = Hooks.getWebDriver();
		baseUrl = "http://hyb-pre-prod-uk.oakley.com";
		driver.get(baseUrl + "/");
	}

	@When("^Add a product to cart$")
	public void add_a_product_to_cart() throws Throwable {
		driver.findElement(
				By.xpath(".//*[@id='global-header-dropdown']/div[3]/ul/li[1]/a"))
				.click();
		Thread.sleep(5000);
		String mouseclickScript = "arguments[0].click();";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				mouseclickScript,
				driver.findElement(By
						.xpath(".//*[@id='global-header-dropdown']/div[4]/div/div/div[1]/div/div[1]/div/ul/li[7]/a")));
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a.image.product-url")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(20000);
	}

	@When("^Log In with your account$")
	public void log_In_with_your_account() throws Throwable {
		driver.findElement(By.cssSelector(".login-button>a")).click();
		Thread.sleep(20000);
		driver.findElement(By.id("j_username")).clear();
		driver.findElement(By.id("j_username")).sendKeys("test1217@126.com");
		driver.findElement(By.id("j_password")).clear();
		driver.findElement(By.id("j_password")).sendKeys("Temp1234");
      	driver.findElement(By.cssSelector("input.button.highlight")).click();
	}

	@When("^Checkout with CC existing addresses$")
	public void checkout_with_CC_existing_addresses() throws Throwable {
		Thread.sleep(30000);
		driver.findElement(By.id("global-header-dropdown"))
				.findElement(By.className("cart-button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("html/body/div[5]/div[1]/div/div[2]/a")).click();
		Thread.sleep(30000);
		driver.findElement(
				By.xpath(".//*[@id='container']/div[6]/div/section[1]/fieldset[1]/div[2]/div[1]/label"))
				.click();
		Thread.sleep(10000);
		driver.findElement(By.id("saved-card-ccv")).clear();
		driver.findElement(By.id("saved-card-ccv")).sendKeys("123");
		Thread.sleep(1000);
		String mouseclickScript = "arguments[0].click();";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(mouseclickScript, driver.findElement(By.id("legal1")));
		Thread.sleep(3000);
		driver.findElement(
				By.xpath(".//*[@id='placeOrderForm']/section/fieldset[1]/div/div[2]/div/input"))
				.click();
	}

	@When("^Place Order Successfully With CC for check in hmc$")
	public void place_Order_Successfully_With_CC() throws Throwable {
		Thread.sleep(20000);
		assertEquals("Order Confirmation", driver.getTitle());
	}

	@When("^Remember the order number$")
	public void remember_the_order_number() throws Throwable {
		Hooks.getStringCache().put(
				"orderId",
				driver.findElement(By.className("headline-order-number"))
						.getText());
	}

	@When("^Log in HMC$")
	public void log_in_HMC() throws Throwable {
		driver.get("http://preprod-bo-www.oakley.com/hmc/hybris");
		driver.findElement(By.id("Main_user")).clear();
		driver.findElement(By.id("Main_user")).sendKeys("jenny.fan");
		driver.findElement(By.id("Main_password")).clear();
		driver.findElement(By.id("Main_password")).sendKeys("jfan1");
		driver.findElement(By.id("Main_label")).click();
		Thread.sleep(5000);
	}

	@Then("^Can see the order is shown in HMC$")
	public void can_see_the_order_is_shown_in_HMC() throws Throwable {
		String orderId = Hooks.getStringCache().get("orderId");
		// String orderId = "H00012495652";
		driver.findElement(
				By.id("Tree/GenericExplorerMenuTreeNode[order]_treeicon"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.id("Tree/GenericLeafNode[Order]_label")).click();
		Thread.sleep(5000);
		driver.findElement(
				By.id("Content/StringEditor[in Content/GenericCondition[Order.code]]_input"))
				.clear();
		Thread.sleep(2000);
		driver.findElement(
				By.id("Content/StringEditor[in Content/GenericCondition[Order.code]]_input"))
				.sendKeys(orderId);
		driver.findElement(By.id("Content/OrganizerSearch[Order]_searchbutton"))
				.click();
		Thread.sleep(5000);
		String orderIDhmc = driver
				.findElement(
						By.id("Content/ClassificationOrganizerList[Order]_innertable"))
				.findElement(By.xpath("tbody/tr[2]/td[4]/div/div")).getText();
		assertEquals(orderId, orderIDhmc);
	}

	@When("^Log in CyberSource$")
	public void log_in_CyberSource() throws Throwable {
		driver.get("https://ebctest.cybersource.com/ebctest/login/");
		driver.manage().deleteAllCookies();
		Thread.sleep(10000);
		driver.findElement(By.id("organizationId")).clear();
		driver.findElement(By.id("organizationId"))
				.sendKeys("oakleydigital_us");
		Thread.sleep(1000);
		driver.findElement(By.id("usernameId")).clear();
		driver.findElement(By.id("usernameId")).sendKeys("ginazhao");
		Thread.sleep(1000);
		driver.findElement(By.id("passwordId")).clear();
		driver.findElement(By.id("passwordId")).sendKeys("oakley001");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		Thread.sleep(15000);
		Alert alert = driver.switchTo().alert(); // ??alert
		alert.accept();
	}

	@Then("^Can see the order is shown in CyberSource$")
	public void can_see_the_order_is_shown_in_CyberSource() throws Throwable {
		String orderId = Hooks.getStringCache().get("orderId");
		driver.findElement(By.xpath(" .//*[@id='s9']")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("tools.decisionmanager.casemanagement"))
				.click();
		Thread.sleep(5000);
		driver.findElement(By.id("fvTabLink")).click();
		Thread.sleep(20000);
		driver.findElement(By.id("t1presetDate")).click();
		Thread.sleep(20000);
		driver.findElement(By.id("t1dr_lasthour")).click();
		Thread.sleep(20000);
		driver.findElement(By.id("searchValue")).sendKeys(orderId.trim());
		driver.findElement(By.xpath("(//a[contains(text(),'Search')])[10]"))
				.click();
		Thread.sleep(15000);
		assertEquals(
				orderId,
				driver.findElement(
						By.xpath(".//*[@id='orderInfoDataTbl']/tbody/tr[3]/td[2]"))
						.getText());
	}
}
