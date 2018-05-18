package com.facebook.Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FacebookAppHomePage {
	WebDriverWait wait;
	String str = "";

	public FacebookAppHomePage(AppiumDriver<WebElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, 30);
	}

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@index='4']")
	private MobileElement fbMainMenu;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@index='2']")
	private MobileElement fbUserProfile;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Activity Log']")
	private MobileElement fbActivityLogs;

	public void accessFacebookProfile() {
		try 
		{
			wait.until(ExpectedConditions.visibilityOf(fbUserProfile));
			fbUserProfile.click();
		} 
		catch (NoSuchElementException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public void accessActivityLog() {
		try 
		{
			wait.until(ExpectedConditions.visibilityOf(fbActivityLogs));
			fbActivityLogs.click();
		} 
		catch (NoSuchElementException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public void verifySuccessfulLogin() {
		try 
		{
			wait.until(ExpectedConditions.visibilityOf(fbUserProfile));
			Assert.assertTrue(fbUserProfile.isDisplayed());
		} 
		catch (NoSuchElementException e) 
		{
			System.out.println(e.getMessage());
		}
	}

}
