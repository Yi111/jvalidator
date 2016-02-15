package steptest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddAFAtoCart {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Given("^Open home page$")
	public void open_home_page() throws Throwable {
		driver = Hooks.getWebDriver();
		// driver = new FirefoxDriver();
		String baseUrl = "http://hyb-pre-prod-uk.oakley.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl + "/");
		Thread.sleep(20000);
	}

	@When("^click WOMEN$")
	public void click_WOMEN() throws Throwable {
		driver.findElement(
				By.xpath(".//*[@id='global-header-dropdown']/div[3]/ul/li[2]/a"))
				.click();
		Thread.sleep(2000);
	}

	@When("^click Tops under APPAREL$")
	public void click_Tops_under_APPAREL() throws Throwable {
		driver.findElement(By.linkText("Tops")).click();
		Thread.sleep(5000);
	}

	@When("^click on a product under Apparel$")
	public void click_on_a_product_under_Apparel() throws Throwable {
		driver.findElement(By.cssSelector("a.image.product-url")).click();
		Thread.sleep(20000);
	}

	@When("^Change size to XL$")
	public void change_size_to_XL() throws Throwable {
		driver.findElement(
				By.xpath(".//*[@id='pdhero']/div[2]/form/div/div[3]/div[1]/div[2]/div/label[5]"))
				.click();
		Thread.sleep(1000);
	}

	@When("^Change QTY to (\\d+)$")
	public void change_QTY_to(int arg1) throws Throwable {
		new Select(driver.findElement(By.name("qty"))).selectByVisibleText("3");
		driver.findElement(By.cssSelector("option[value=\"3\"]")).click();
		Thread.sleep(1000);
	}

	@When("^Click ADD TO CART button in Appearel Product detail page$")
	public void Click_ADD_TO_CART_button_in_Appearel_Product_detail_page()
			throws Throwable {
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(15000);
	}

	@When("^Click Items in Cart Link in Appearel Product detail page$")
	public void Click_Items_in_Cart_Link_in_Appearel_Product_detail_page()
			throws Throwable {
		/*driver.findElement(By.cssSelector("span")).click();
		driver.findElement(By.cssSelector("span.item-desc")).click();
		Thread.sleep(5000);*/
		
		driver.findElement(By.id("global-header-dropdown")).findElement(By.className("cart-button")).click();
		Thread.sleep(20000);
		driver.findElement(By.className("cart-flyout")).findElement(By.className("header")).findElement(By.xpath("span[1]")).click();
		Thread.sleep(30000);
	}

	@Then("^Verify size is XL QTY is (\\d+)$")
	public void verify_size_is_XL_QTY_is(int arg1) throws Throwable {
		assertEquals("Size: XL",
				driver.findElement(By.cssSelector("span.item-desc")).getText());
		assertEquals("3",
				driver.findElement(By.cssSelector("span.option-text-value"))
						.getText());

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
