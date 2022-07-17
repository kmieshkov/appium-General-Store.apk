import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class TC_6_MobileBrowser extends BrowserBaseTest {

	// mobile Chrome browser test
	// here is no Appium since it's browser
	// selenium only
	@Test
	public void browserTest() {
		driver.get("https://www.google.com/");
		driver.findElement(By.name("q")).sendKeys("Selenium Webdriver");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	}

	// scrolling in mobile browser
	@Test
	public void scrollTest() {
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.xpath("//span[@class='navbar-toggler-icon']")).click();
		driver.findElement(By.xpath("//a[@routerlink='/products']")).click();
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)", "");
		String text = driver.findElement(By.xpath("//a[@href='/angularAppdemo/products/3']")).getText();
		Assert.assertEquals(text, "Devops");
	}
}
