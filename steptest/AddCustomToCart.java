package steptest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddCustomToCart {
	private WebDriver driver;
	private String baseUrl;

	@Given("^Click the custom on navigation bar$")
	public void click_the_custom_on_navigation_bar() throws Throwable {
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "C:\\chromedriver.exe"); driver = new ChromeDriver();
		 */
		driver = Hooks.getWebDriver();
		baseUrl = "http://hyb-pre-prod-uk.oakley.com/";
		driver.get(baseUrl + "/en/");
		driver.findElement(
				By.xpath(".//*[@id='global-header-dropdown']/div[3]/ul/li[5]/a"))
				.click();
	}

	@When("^Choose the second custom product$")
	public void choose_the_second_custom_product() throws Throwable {
		Thread.sleep(20000);
		driver.findElement(
				By.xpath(".//*[@id='skipToMainContent']/div/div[2]/div/a"))
				.click();
	}

	@When("^Click continue button$")
	public void click_continue_button() throws Throwable {
		Thread.sleep(60000);
		driver.findElement(
				By.xpath(".//*[@id='continueToPersonalizationMainContainer']/div/div/span"))
				.click();
	}

	@When("^Check checkbox$")
	public void check_checkbox() throws Throwable {
		Thread.sleep(5000);

		if (!driver
				.findElement(
						By.cssSelector(".jqTransformCheckbox.ui-icon.kineticClickableChild"))
				.isSelected()) {
			driver.findElement(
					By.cssSelector(".jqTransformCheckbox.ui-icon.kineticClickableChild"))
					.click();
		}
	}

	@When("^Fill into QA in etching field$")
	public void fill_into_QA_in_etching_field() throws Throwable {
		Thread.sleep(2000);

		driver.findElement(
				By.id("selector-component-1479-p_20859-p_20859_ca_36676__p_20859_ca_36692-textInput"))
				.sendKeys("QA");
	}

	@When("^Verify additional Cost$")
	public void verify_additional_Cost() throws Throwable {
		Thread.sleep(10000);
		assertEquals(
				"£15.00",
				driver.findElement(
						(By.xpath(".//*[@id='personalization-attribute-container']/div[2]/span[2]")))
						.getText());
	}

	@When("^Click Add Etching button$")
	public void click_Add_Etching_button() throws Throwable {
		driver.findElement(
				By.xpath(".//*[@id='oakley-personalization-add-and-continue-button']"))
				.click();
	}

	@Then("^Verify cart page$")
	public void verify_cart_page() throws Throwable {
		Thread.sleep(30000);
		assertEquals("Your Shopping Bag", driver.getTitle());

		// remove the product
		Thread.sleep(12000);
		driver.findElement(By.id("RemoveProduct_0")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.boxFormWrapper.clearfix"))
				.click();
		Thread.sleep(5000);
		assertTrue(driver
				.findElement(By.cssSelector("div.boxFormWrapper.clearfix"))
				.getText().contains("Your cart is empty"));
		Thread.sleep(10000);
		//driver.close();
		driver.quit();
	}
}
