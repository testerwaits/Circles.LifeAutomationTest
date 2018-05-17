package com.facebook.Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.circlesLife.Utils.Utils;

public class FacebookLoginPage 
{
	WebDriverWait wait;
	AppiumDriver<WebElement> driver;
	// Fetch Test data From XML
	public String fbUserName = Utils.getTestData("FacebookLoginPage.UserName");
	public String fbPassword = Utils.getTestData("FacebookLoginPage.UserPassword");
		
	public FacebookLoginPage(AppiumDriver<WebElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}	

	 @AndroidFindBy(xpath = "//android.widget.EditText[@text='Phone or Email']")
     private MobileElement userName;
	 
	 @AndroidFindBy(xpath = "//android.widget.LinearLayout//android.widget.RelativeLayout[2]/android.widget.EditText")
     private MobileElement userPassword;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='LOG IN']")
     private MobileElement loginBtn;
	 
	 public void loginToFacebook()
	 {
		 wait.until(ExpectedConditions.visibilityOf(userName));
		 userName.sendKeys(fbUserName);
		 userPassword.click();
		 userPassword.sendKeys(fbPassword);
		 loginBtn.click();  
	 }
}
