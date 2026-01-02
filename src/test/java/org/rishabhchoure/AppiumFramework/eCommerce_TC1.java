package org.rishabhchoure.AppiumFramework;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


import io.appium.java_client.AppiumBy;

public class eCommerce_TC1 extends AndroidBaseTest{
	
	/*
	 * @BeforeMethod public void preSetup() { Activity activity = new
	 * Activity("com.androidsample.generalstore",
	 * "com.androidsample.generalstore.MainActivity"); ((JavascriptExecutor)
	 * driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
	 * "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity")
	 * ); }
	 */
	
	@Test
	public void FillFormErrorValidation() throws InterruptedException 
	{
		
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Wamiqa Gabbi");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("Name");
		Assert.assertEquals(toastMessage, "Please enter your name");		
	}
	
	@Test
	public void FillFormPositiveFlow() throws InterruptedException 
	{
		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Wamiqa Gabbi");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
		

	}
}

