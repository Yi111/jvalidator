package steptest;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SlideCart_Remove {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	
	public SlideCart_Remove() throws Throwable
	{
		driver = Hooks.getWebDriver();
	}

	@When("^click Polarized under MEN SUNGLASSES$")
	public void click_Polarized_under_MEN_SUNGLASSES() throws Throwable {
		driver.findElement(By.linkText("Polarized")).click();
		//driver.findElement(By.linkText("Best Sellers")).click();
		Thread.sleep(5000);
	}

	@When("^click on a product in Polarized PLP under MEN SUNGLASSES$")
	public void click_on_a_product_in_Polarized_PLP_under_MEN_SUNGLASSES() throws Throwable {
		driver.findElement(By.cssSelector("a.image.product-url")).click();
		Thread.sleep(5000);
	}

	@When("^Click ADD TO CART button in Polarized PDP under MEN SUNGLASSES$")
	public void click_ADD_TO_CART_button_in_Polarized_PDP_under_MEN_SUNGLASSES() throws Throwable {
		 driver.findElement(By.xpath("//button[@type='submit']")).click();
		 Thread.sleep(5000);
	}

	@When("^Click Remove icon in slide cart$")
	public void click_Remove_icon_in_slide_cart() throws Throwable {
		driver.findElement(By.xpath("//a/div/div[3]")).click();
	}

	@Then("^Verify the polarized product is removed from cart$")
	public void verify_the_polarized_product_is_removed_from_cart() throws Throwable {
		Thread.sleep(1000);
		assertEquals("SHOP MEN", driver.findElement(By.linkText("SHOP MEN")).getText());
		Thread.sleep(10000);
		//driver.close();
		driver.quit();
	}

}