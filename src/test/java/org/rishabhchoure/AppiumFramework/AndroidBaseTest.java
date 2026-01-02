package org.rishabhchoure.AppiumFramework;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.rishabhchoure.pageobjects.android.FormPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AndroidBaseTest {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;
	
	@BeforeClass 
	public void configureAppium() throws IOException, InterruptedException 
	{
		// Step 1: Kill any existing Node.js processes
		try {
			System.out.println("Killing existing node processes...");
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			Thread.sleep(3000); // Wait for processes to fully terminate
		} catch (Exception e) {
			System.out.println("No node process to kill: " + e.getMessage());
		}
		
		// Step 2: Build Appium service with proper configuration
		System.out.println("Starting Appium server...");
		
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:\\Users\\Rishabh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
				.withIPAddress("127.0.0.1")
				.usingPort(4723)
				.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
				.withArgument(GeneralServerFlag.LOG_LEVEL, "info")
				.withTimeout(Duration.ofSeconds(120))  // Increased timeout
				.build();
		
		// Step 3: Start the service
		try {
			service.start();
			System.out.println("Appium server started successfully!");
			System.out.println("Server URL: " + service.getUrl());
		} catch (Exception e) {
			System.err.println("Failed to start Appium server: " + e.getMessage());
			throw e;
		}
		
		// Step 4: Verify server is running
		if (!service.isRunning()) {
			throw new RuntimeException("Appium server failed to start!");
		}
		
		// Give server extra time to fully initialize
		Thread.sleep(2000);
		
		// Step 5: Configure Android options
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Rishabh Pixel 6a");
		options.setPlatformName("Android");
		
		// Chrome driver configuration (for hybrid apps)
		options.setChromedriverExecutable("F:\\Eclipse Application\\chromedriver-win64\\chromedriver.exe");
		
		// App configuration
		options.setApp("F:\\Eclipse Application\\eclipse-workspace\\AppiumFramework\\src\\test\\java\\org\\rishabhchoure\\Utils\\General-Store.apk");
		
		// Additional stability options
		options.setAutoGrantPermissions(true);
		options.setNoReset(false);
		options.setNewCommandTimeout(Duration.ofSeconds(300));
		
		// Step 6: Initialize driver
		System.out.println("Initializing AndroidDriver...");
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			System.out.println("AndroidDriver initialized successfully!");
		} catch (Exception e) {
			System.err.println("Failed to initialize AndroidDriver: " + e.getMessage());
			if (service != null && service.isRunning()) {
				service.stop();
			}
			throw e;
		}
		
		// Step 7: Initialize page object
		formPage = new FormPage(driver);
	}
	
	public void LongPressAction(WebElement elem1)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", 
				ImmutableMap.of("elementId", ((RemoteWebElement)elem1).getId(), 
						"duration", 2000));
	}
	
	public void ScrollToEndAction()
	{
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", 
					ImmutableMap.of(
							"left", 100, "top", 100, "width", 200, "height", 200,
							"direction", "down",
							"percent", 1.0
					));
		} while(canScrollMore);
	}
	
	public void swipeAction(WebElement ele1, String direction) 
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", 
				ImmutableMap.of(
						"elementId", ((RemoteWebElement)ele1).getId(),
						"direction", direction,
						"percent", 0.75
				));
	}
	
	public Double getFormattedAmount(String amount)
	{
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}
	
	@AfterClass 
	public void TearDown() 
	{
		try {
			if (driver != null) {
				System.out.println("Quitting driver...");
				driver.quit();
			}
		} catch (Exception e) {
			System.err.println("Error quitting driver: " + e.getMessage());
		}
		
		try {
			if (service != null && service.isRunning()) {
				System.out.println("Stopping Appium server...");
				service.stop();
			}
		} catch (Exception e) {
			System.err.println("Error stopping service: " + e.getMessage());
		}
	}
}