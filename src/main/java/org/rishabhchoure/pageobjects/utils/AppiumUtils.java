package org.rishabhchoure.pageobjects.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;


public class AppiumUtils {
	
AppiumDriver driver;
	
    // 1. ADD THIS: Default no-argument constructor
    public AppiumUtils() {
    }

	public AppiumUtils(AppiumDriver driver)
	{
		this.driver = driver;
	}
	
	public Double getFormattedAmount(String amount)
	{
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}
	
	public void waitForElementToAppear(WebElement ele)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		  
		 // Step 1: Wait for the element to be present (so it's not stale)
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 "com.androidsample.generalstore:id/toolbar_title")));
		  
		 // Step 2: Now safely wait for the text to match "Cart"
		  wait.until(ExpectedConditions.attributeContains((ele),"text", "Cart" ));
	}
	
	public String getScreenshot(String testCaseName, AppiumDriver driver) throws IOException {
	    File source = driver.getScreenshotAs(OutputType.FILE);
	    String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
	    FileUtils.copyFile(source, new File(destinationFile));
	    return destinationFile; // This return is crucial for the listener
	}

}
