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

public class AddAccessoriesToCart {
	private WebDriver driver;
	private String baseUrl;

	@Given("^Click the bag&packbags under Accessroies on women navigation$")
	public void click_the_bag_packbags_under_Accessroies_on_women_navigation()
			throws Throwable {
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "C:\\chromedriver.exe"); driver = new ChromeDriver();
		 */
		driver = Hooks.getWebDriver();
		baseUrl = "http://hyb-pre-prod-uk.oakley.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl + "/");
		Thread.sleep(5000);
		driver.findElement(
				By.xpath(".//*[@id='global-header-dropdown']/div[3]/ul/li[2]/a"))
				.click();
	}

	@When("^Choose the a bag$")
	public void choose_the_a_bag() throws Throwable {
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("(//a[contains(text(),'Bags & Backpacks')])[2]"))
				.click();
	}

	@When("^Click add to cart button$")
	public void click_add_to_cart_button() throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("a.image.product-url")).click();
	}

	@When("^Click the cart  view the product$")
	public void click_the_cart_view_the_product() throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@Then("^Add sucessfully$")
	public void add_sucessfully() throws Throwable {
		Thread.sleep(2000);
		assertEquals("1",driver.findElement(By.xpath(".//*[@id='updateCartForm0']/div/div/span/span[1]")).getText());
		//driver.findElement(By.cssSelector("span")).click();
		//remove the product
		Thread.sleep(12000);
		driver.findElement(By.id("RemoveProduct_0")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[2]")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.boxFormWrapper.clearfix"))
				.click();
		Thread.sleep(5000);
		assertTrue(driver
				.findElement(By.cssSelector("div.boxFormWrapper.clearfix"))
				.getText().contains("Your cart is empty"));
		Thread.sleep(10000);
		//driver.close();
//		driver.quit();
	}
}
