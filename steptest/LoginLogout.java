package steptest;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginLogout {
	private WebDriver driver;
	private String baseUrl;

	@Given("^Click Sign in icon on navigation bar$")
	public void click_Sign_in_icon_on_navigation_bar() throws Throwable {
		/*System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();*/
		driver = Hooks.getWebDriver();
		baseUrl = "http://hyb-pre-prod-uk.oakley.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl + "/en/");
				driver.findElement(
				By.xpath(".//*[@id='global-header-dropdown']/div[1]/div[4]/a"))
				.click();
		}

	@When("^Fill into a valid username and password$")
	public void fill_into_a_valid_username_and_password() throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.id("j_username")).clear();
		driver.findElement(By.id("j_username")).sendKeys("zx_person@163.com");
		Thread.sleep(1000);
		driver.findElement(By.id("j_password")).click();
		driver.findElement(By.id("j_password")).clear();
		driver.findElement(By.id("j_password")).sendKeys("ZX7758lb");
		}

	@When("^Click sign in button$")
	public void click_sign_in_button() throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.button.highlight")).click();
		Thread.sleep(5000);
		assertEquals("Profile", driver.getTitle());
		}

	@When("^Click the current account name on navigation bar$")
	public void click_the_current_account_name_on_navigation_bar()
			throws Throwable {
		Thread.sleep(5000);
		driver.findElement(
				By.xpath(".//*[@id='global-header-dropdown']/div[1]/div[4]/a"))
				.click();
		}

	@When("^Click sign out button$")
	public void click_sign_out_button() throws Throwable {
		Thread.sleep(5000);
		/*driver.findElement(
				By.xpath(".//*[@id='profile-menu-modal']/div[2]/ul/li[5]/a"))
				.click();*/
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(5000);
		}

	@Then("^Login and Logout successful$")
	public void login_and_Logout_successful() throws Throwable {
		 assertEquals("SIGN IN", driver.findElement(By.xpath(".//*[@id='global-header-dropdown']/div[1]/div[4]/a")).getText());
		 Thread.sleep(10000);
		 driver.quit();
	}
}
