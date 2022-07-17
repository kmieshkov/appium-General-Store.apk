import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BrowserBaseTest {

	public AndroidDriver driver;
	AppiumDriverLocalService service;

	@BeforeClass
	public void configureAppium() throws MalformedURLException {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("/Users/kmieshkov/.nvm/versions/node/v18.0.0/lib/node_modules/appium/build/lib/main.js"))
				.withIPAddress("127.0.0.1")
				.withArgument(() -> "--base-path", "/wd/hub/")
				.withArgument(GeneralServerFlag.LOG_LEVEL, "error")
				.usingPort(4723)
				.build();
		service.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel_2_XL_API_30");
		options.setChromedriverExecutable("/Users/kmieshkov/Projects/IdeaProjects/appium-General-Store/src/test/resources/chromedriver");
		options.setCapability("browserName", "Chrome");

		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver(url, options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}



	@AfterClass
	public void TearDown() {
		driver.quit();
		service.stop();
	}
}
