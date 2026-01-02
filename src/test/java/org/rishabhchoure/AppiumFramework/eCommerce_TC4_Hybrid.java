package org.rishabhchoure.AppiumFramework;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.rishabhchoure.pageobjects.android.CartPage;
import org.rishabhchoure.pageobjects.android.ProductsCatalogue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class eCommerce_TC4_Hybrid extends AndroidBaseTest{
	
	@Test(dataProvider="getData")
	public void NativetoHybrid(String name, String gender, String country) throws InterruptedException 
	{
		formPage.SetNameField(name);
		formPage.setGender(gender);
		formPage.setCountrySelection(country);
		ProductsCatalogue productsCatalogue = formPage.submitForm();
		productsCatalogue.addItemToCartByIndex(0);
		productsCatalogue.addItemToCartByIndex(0);
		CartPage cartPage = productsCatalogue.goToCartPage();
		
		Thread.sleep(7000);
		//Alternative Method - (Working)
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 * 
		 * // Step 1: Wait for the element to be present (so it's not stale)
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "com.androidsample.generalstore:id/toolbar_title")));
		 * 
		 * // Step 2: Now safely wait for the text to match "Cart"
		 * wait.until(ExpectedConditions.attributeContains(
		 * driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),
		 * "text", "Cart" ));
		 */
		
		double totalSum = cartPage.getProductSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTnC();
		cartPage.submitOrder();
		Thread.sleep(10);
		
		
		
		  //Hybrid page code 
		
		Set<String> contexts =driver.getContextHandles(); //This is for finding context names of Hybrid App 
		  
		for(String contextName : contexts)
		  { 
			System.out.println(contextName); 
			}
		   
		  driver.context("WEBVIEW_com.androidsample.generalstore");//Switch to WebView
		  driver.findElement(By.name("q")).sendKeys("Wamiqa Gabbi");
		  driver.findElement(By.name("q")).sendKeys(Keys.ENTER); Thread.sleep(4000);
		  driver.pressKey(new KeyEvent(AndroidKey.BACK));
		  driver.context("NATIVE_APP");//Switch Back to Native App
		 	
	}
	
	@DataProvider 
	public Object[][] getData()
	{
		return new Object[][] { {"Wamiqa Gabbi", "female", "Argentina"}   };
	}
	

}
