import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_4 extends BaseTest {

	private final String country = "Argentina";
	private final String item1 = "Jordan 6 Rings";
	private final String item2 = "Air Jordan 4 Retro";

	// positive test
	// review Term and COnditions
	@Test
	public void verifyAlertTitle() throws InterruptedException {
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

		longPressAction(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton")));
		String alertTitle = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle, "Terms Of Conditions");
		Thread.sleep(3000);
	}
}
