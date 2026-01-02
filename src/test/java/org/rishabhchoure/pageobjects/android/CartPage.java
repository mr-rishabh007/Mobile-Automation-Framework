package org.rishabhchoure.pageobjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rishabhchoure.pageobjects.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{
	
	AndroidDriver driver;
	
	
	public CartPage(AndroidDriver driver)//Going Forward do not touch this code
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	public List<WebElement> productList;
	
	//driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"))
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement totalAmount;
	
	//driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"))
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	public WebElement terms;
	
	/*
	 * //driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle"))
	 * 
	 * @AndroidFindBy(id="com.androidsample.generalstore:id/alertTitle") public
	 * List<WebElement> termsTitle;
	 */
	
	//driver.findElement(By.id("android:id/button1"))
	@AndroidFindBy(id="android:id/button1")
	public WebElement acceptButton;
	
	//driver.findElement(AppiumBy.className("android.widget.CheckBox"))
	@AndroidFindBy(className="android.widget.CheckBox")
	public WebElement tickCheckbox;
	
	//driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed"))
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	public WebElement proceed;
	
	
	public List<WebElement> getProductList()
	{
		return productList;
		
	}
	
	public double getProductSum()
	{
		int count = productList.size();
		double totalSum = 0;
		
		for (int i = 0; i<count; i++ )
		{
			String amountString = productList.get(i).getText();
			//substring is used to remove currency from the amount to add the prices and make it's total
			Double price = getFormattedAmount(amountString);
			totalSum = totalSum + price;
			
		}
		return totalSum;
	}
	
	public Double getTotalAmountDisplayed()
	{
		return getFormattedAmount(totalAmount.getText());
		
	}
	
	
	
	public void acceptTnC()
	{
		LongPressAction(terms);
		acceptButton.click();
	}
	
	public Double getFormattedAmount(String amount)
	{
		
		Double price = Double.parseDouble(amount.substring(1));
		return price;
		
	}
	
	public void submitOrder()
	{
		tickCheckbox.click();
		proceed.click();
	}
	
	

}

	
	




















