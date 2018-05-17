package com.facebook.Pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FacebookHomePage 
{
	WebDriverWait wait;	
	String str = "";
	public FacebookHomePage(AppiumDriver<WebElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, 30);
	} 
	 
	 @AndroidFindBy(xpath = "//android.widget.FrameLayout[@index='4']")
     private MobileElement fbMainMenu;
	 
	// @AndroidFindBy(xpath = "//android.support.v7.widget.RecyclerView//android.widget.ImageView[2]")
    // private MobileElement fbUserProfile;
	 
	 @AndroidFindBy(xpath = "//android.widget.FrameLayout[@index='2']")
     private MobileElement fbUserProfile;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Activity Log']")
     private MobileElement fbActivityLogs;
	 
	 @AndroidFindBy(xpath = "//android.view.ViewGroup[@index='1']//android.widget.TextView[@index='2']")
     private MobileElement fbLatestPost;
	 
	 public void accessFacebookProfile()
	 {
		 try
		 {
			 wait.until(ExpectedConditions.visibilityOf(fbUserProfile));
			 fbUserProfile.click();
			 wait.until(ExpectedConditions.visibilityOf(fbActivityLogs));
			 fbActivityLogs.click();
			 wait.until(ExpectedConditions.visibilityOf(fbLatestPost));
			 str = fbLatestPost.getAttribute("text"); 
		 }
		 catch(Exception e)
		 {
			 
		 }
		 
	 }
	 
	
	 
	
}
