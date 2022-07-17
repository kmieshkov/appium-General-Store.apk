import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_3_VerifyCartAmount extends BaseTest {

	private final String country = "Argentina";
	private final String item1 = "Jordan 6 Rings";
	private final String item2 = "Air Jordan 4 Retro";

	// positive test
	// verify amount in the cart
	@Test
	public void verifyCartAmount() throws InterruptedException {
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

		// add prices
		double amount = addPrices();

		// format total amount of the cart
		String price = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().replaceAll("[^\\d.]", "");
		double total = Double.parseDouble(price);

		Assert.assertEquals(amount, total, 0);

		Thread.sleep(3000);
	}

	private double addPrices() {
		int prices = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice")).size();
		double amount = 0;
		for (int i = 0; i < prices; i++) {
			String price = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
			price = price.replaceAll("[^\\d.]", "");
			amount += Double.parseDouble(price);
		}
		return amount;
	}
}
