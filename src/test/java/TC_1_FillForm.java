import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_1_FillForm extends BaseTest {

	// positive test
	@Test
	public void fillForm() throws InterruptedException {
		driver.hideKeyboard();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("First Last");
		driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		String country = "Argentina";
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + country + "\"))"));
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + country + "']")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(3000);
	}

	// error message verification
	@Test
	public void toastErrorMessage() {
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String error = driver.findElement(AppiumBy.xpath("(//android.widget.Toast)[1]")).getText();
		String error2 = driver.findElement(AppiumBy.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		System.out.println(error);
		System.out.println(error2);
		Assert.assertEquals(error2, "Please enter your name");
	}
}
