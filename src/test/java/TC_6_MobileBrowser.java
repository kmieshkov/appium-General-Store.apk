import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import java.util.Set;

public class TC_6_MobileBrowser extends BrowserBaseTest {

	// mobile Chrome browser test
	// here is no Appium since it's browser
	// selenium only
	@Test
	public void browserTest() throws InterruptedException {
		driver.get("https://www.google.com/");
		driver.findElement(By.name("q")).sendKeys("Selenium Webdriver");
		driver.findElement(By.name("q")).click();
	}
}
