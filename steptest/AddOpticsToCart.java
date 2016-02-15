package steptest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddOpticsToCart {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	

	public AddOpticsToCart()throws Throwable  {
		driver = Hooks.getWebDriver();
	}

	@When("^click MEN$")
	public void click_MEN() throws Throwable {
		Thread.sleep(10000);
		
		driver.findElement(
		By.xpath(".//*[@id='global-header-dropdown']/div[3]/ul/li[1]/a"))
		.click();
		 
		
		Thread.sleep(5000);
	}

	@When("^click Best Sellers under MEN SUNGLASSES$")
	public void click_Best_Sellers_under_MEN_SUNGLASSES() throws Throwable {
		driver.findElement(By.linkText("Best Sellers")).click();
		Thread.sleep(10000);
	}

	@When("^click on a product under Best Sellers$")
	public void click_on_a_product_under_Best_Sellers() throws Throwable {
		driver.findElement(By.cssSelector("a.image.product-url")).click();
		Thread.sleep(10000);
	}

	@When("^Click ADD TO CART button in men best sellers detail page$")
	public void Click_ADD_TO_CART_button_in_men_best_sellers_detail_page()
			throws Throwable {
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(15000);
	}

	@When("^Click Items in Cart Link in men best sellers detail page$")
	public void Click_Items_in_Cart_Link_in_men_best_sellers_detail_page()
			throws Throwable {
		driver.findElement(By.id("global-header-dropdown"))
				.findElement(By.className("cart-button")).click();
		Thread.sleep(20000);
		driver.findElement(By.className("cart-flyout"))
				.findElement(By.className("header"))
				.findElement(By.xpath("span[1]")).click();
		Thread.sleep(30000);
	}

	@Then("^Verify the product is added to cart$")
	public void Verify_the_product_is_added_to_cart() throws Throwable {
		assertEquals(
				"1",
				driver.findElement(
						By.xpath(".//*[@id='updateCartForm0']/div/div/span/span[1]"))
						.getText());
		// remove the product
		driver.findElement(By.id("RemoveProduct_0")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.boxFormWrapper.clearfix"))
				.click();
		Thread.sleep(5000);
		assertTrue(driver
				.findElement(By.cssSelector("div.boxFormWrapper.clearfix"))
				.getText().contains("Your cart is empty"));
		Thread.sleep(10000);
		// driver.close();
		driver.quit();
	}
}
