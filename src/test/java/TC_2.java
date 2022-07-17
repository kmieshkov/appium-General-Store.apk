import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_2 extends BaseTest {

	private final String country = "Argentina";
	private final String needed = "Jordan 6 Rings";

	// positive tests

	@Test
	public void fillForm() throws InterruptedException {
		driver.hideKeyboard();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("First Last");
		driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + country + "\"))"));
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + country + "']")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		// wait for the title to be equal Products
		waitForElementAttributeToBeEqual("com.androidsample.generalstore:id/toolbar_title", "text", "Products");

		String title = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")).getText();
		Assert.assertEquals(title, "Products");
		Thread.sleep(3000);
	}

	@Test(dependsOnMethods = {"fillForm"})
	public void addItemToCart() throws InterruptedException {
		// scroll to needed item
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + needed + "\"))"));

		// Add to cart using xpath
//		String xpath = "//android.widget.LinearLayout/android.widget.TextView[@text='" + needed + "']/following-sibling::android.widget.LinearLayout[2]/android.widget.TextView[@text='ADD TO CART']";
//		driver.findElement(AppiumBy.xpath(xpath)).click();

		// Add to cart using loop
		int itemCount = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).size();
		for (int i = 0; i < itemCount; i++) {
			String itemName = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if (itemName.equalsIgnoreCase(needed)) {
				driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
			}
		}

		// verify amount in the cart
		String cartCount = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/counterText")).getText();
		Assert.assertEquals(cartCount, "1");
		Thread.sleep(3000);
	}

	@Test(dependsOnMethods = {"addItemToCart"})
	public void goToCart() throws InterruptedException {
		// go to cart
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

		// wait for the title to be equal Cart
		waitForElementAttributeToBeEqual("com.androidsample.generalstore:id/toolbar_title", "text", "Cart");

		String title = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")).getText();
		// verify title in the cart
		Assert.assertEquals(title, "Cart");
		Thread.sleep(3000);
	}

	@Test(dependsOnMethods = {"goToCart"})
	public void verifyItemName() throws InterruptedException {
		//verify item name in the cart
		String actual = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getText();
		Assert.assertEquals(actual, needed);
		Thread.sleep(3000);
	}
}
