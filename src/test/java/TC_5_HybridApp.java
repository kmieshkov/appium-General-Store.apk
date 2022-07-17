import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import java.util.Set;

public class TC_5_HybridApp extends BaseTest {

	private final String country = "Argentina";
	private final String item1 = "Jordan 6 Rings";
	private final String item2 = "Air Jordan 4 Retro";

	// hybrid app teating
	@Test
	public void hybridAppTest() throws InterruptedException {
		//precondition - fill form, add items to cart
		addItemsToCart();

		//go to web page
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();

		Thread.sleep(5000);

		// switch to Web Model
		Set<String> list = driver.getContextHandles();
		for (String contextName: list) {
			System.out.println(contextName);
		}
		// need to know the exact name of the context
		driver.context("WEBVIEW_com.androidsample.generalstore");

		// Hybrid -> Google webpage
		driver.findElement(By.name("q")).sendKeys("Hybrid app");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

		// close browser by pressing Android Back key
		driver.pressKey(new KeyEvent(AndroidKey.BACK));

		// switch to native app context
		driver.context("NATIVE_APP");
	}


	private void addItemsToCart() throws InterruptedException {
		driver.hideKeyboard();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("First Last");
		driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + country + "\"))"));
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + country + "']")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		// wait for the title to be equal Products
		waitForElementAttributeToBeEqual("com.androidsample.generalstore:id/toolbar_title", "text", "Products");

		//add item to the cart
		addItemToCart(item1);
		addItemToCart(item2);

		// go to cart
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

		// wait for the title to be equal Cart
		waitForElementAttributeToBeEqual("com.androidsample.generalstore:id/toolbar_title", "text", "Cart");

		Thread.sleep(3000);
	}
}
