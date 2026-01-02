package org.rishabhchoure.pageobjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rishabhchoure.pageobjects.utils.AndroidActions;

import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsCatalogue extends AndroidActions {
	
	AndroidDriver driver;
	
	
	public ProductsCatalogue(AndroidDriver driver)//Going Forward do not touch this code
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='ADD TO CART']")
	public List<WebElement> addToCart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement CartPage;
	
	public void addItemToCartByIndex(int index)
	{
		addToCart.get(index).click();
		
	}
	
	public org.rishabhchoure.pageobjects.android.CartPage goToCartPage() throws InterruptedException
	{
		CartPage.click();
		Thread.sleep(3000);
		return new CartPage(driver);
		
	}
	
	

}
