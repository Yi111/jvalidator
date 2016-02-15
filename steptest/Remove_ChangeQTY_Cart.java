package steptest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Remove_ChangeQTY_Cart {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	public Remove_ChangeQTY_Cart() throws Throwable {
		driver = Hooks.getWebDriver();
	}

	@When("^click Best Sellers under WOMEN SUNGLASSES$")
	public void click_Best_Sellers_under_WOMEN_SUNGLASSES() throws Throwable {
		Thread.sleep(5000);
		driver.findElement(
				By.xpath(".//*[@id='global-header-dropdown']/div[5]/div/div/div[1]/div/div[1]/div/ul/li[4]/a"))
				.click();
		Thread.sleep(2000);
	}

	@When("^click on a product under best sellers$")
	public void click_on_a_product_under_best_sellers() throws Throwable {
		driver.findElement(By.cssSelector("a.image.product-url")).click();
		Thread.sleep(2000);
	}

	@When("^Change the QTY to (\\d+)$")
	public void change_the_QTY_to(int arg1) throws Throwable {
		new Select(driver.findElement(By.name("qty"))).selectByVisibleText("5");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("option[value=\"5\"]")).click();
		Thread.sleep(2000);
	}

	@When("^Click Add to Cart button in this best sellers women sunglasses detail page$")
	public void click_Add_to_Cart_button_in_this_best_sellers_women_sunglasses_detail_page()
			throws Throwable {
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(20000);
	}

	@When("^Goto cart page of this best sellers women sunglasses detail page$")
	public void goto_cart_page_of_this_best_sellers_women_sunglasses_detail_page()
			throws Throwable {
		// driver.findElement(By.cssSelector("span")).click();
		driver.findElement(By.id("global-header-dropdown"))
				.findElement(By.className("cart-button")).click();
		Thread.sleep(20000);
		driver.findElement(By.className("cart-flyout"))
				.findElement(By.className("header"))
				.findElement(By.xpath("span[1]")).click();
		Thread.sleep(30000);
	}

	@When("^Verify (\\d+) of this product are added to cart$")
	public void verify_of_this_product_are_added_to_cart(int arg1)
			throws Throwable {
		assertEquals("POLARIZED DISPUTE™",
				driver.findElement(By.cssSelector("span.item-title")).getText());
		assertEquals("5",
				driver.findElement(By.cssSelector("span.option-text-value"))
						.getText());
	}

	@When("^Change the product QTY to (\\d+)$")
	public void Change_QTY_to(int arg1) throws Throwable {
		driver.findElement(By.cssSelector("span.option-text-arrow")).click();
		Thread.sleep(2000);
		driver.findElement(
				By.xpath(".//*[@id='updateCartForm0']/div/div/ul/li[3]"))
				.click();
		Thread.sleep(12000);
	}

	@When("^Verify QTY is (\\d+)$")
	public void verify_QTY_is(int arg1) throws Throwable {
		assertEquals("3",
				driver.findElement(By.cssSelector("span.option-text-value"))
						.getText());
	}

	@When("^Click Remove link$")
	public void click_Remove_link() throws Throwable {
		driver.findElement(By.id("RemoveProduct_0")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.boxFormWrapper.clearfix"))
				.click();
		Thread.sleep(5000);
	}

	@Then("^Verify thisbest sellers product is removed from cart$")
	public void verify_thisbest_sellers_product_is_removed_from_cart()
			throws Throwable {
		assertTrue(driver
				.findElement(By.cssSelector("div.boxFormWrapper.clearfix"))
				.getText().contains("Your cart is empty"));
		Thread.sleep(15000);
		//driver.close();
		driver.quit();
	 	}
	

}