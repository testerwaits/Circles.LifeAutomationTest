package com.facebook.Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.circlesLife.Utils.Utils;

public class FacebookAppLoginPage {
	WebDriverWait wait;
	AppiumDriver<WebElement> driver;
	// Fetch Test data From XML
	public String fbUserName = Utils.getTestData("FacebookLoginPage.UserName");
	public String fbPassword = Utils.getTestData("FacebookLoginPage.UserPassword");

	public FacebookAppLoginPage(AppiumDriver<WebElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		wait = new WebDriverWait(driver, 40);
	}

	
	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Username']")
	private MobileElement userName;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Password']")
	private MobileElement userPassword;

	@AndroidFindBy(xpath = "android.widget.Button[@index='3']")
	private MobileElement loginBtn;

	public void loginToFacebook() {
		try 
		{
			wait.until(ExpectedConditions.visibilityOf(userName));
			userName.sendKeys(fbUserName);
			userPassword.click();
			userPassword.sendKeys(fbPassword);
			wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
			loginBtn.click();
		} 
		catch (NoSuchElementException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}
