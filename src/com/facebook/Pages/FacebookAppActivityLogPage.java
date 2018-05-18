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

public class FacebookAppActivityLogPage {
	WebDriverWait wait;
	String actualLatestPost = "";

	public FacebookAppActivityLogPage(AppiumDriver<WebElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, 30);
	}

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@index='1']//android.widget.TextView[@index='2']")
	private MobileElement fbLatestPost;

	public void verifyActivityLogPage() 
	{
		try 
		{
			wait.until(ExpectedConditions.visibilityOf(fbLatestPost));
			Assert.assertTrue(fbLatestPost.isDisplayed());
		} 
		catch (NoSuchElementException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public void verifyWebsitePostedCommentInActivityLog(String expectedLatestPost) {
		try 
		{
			wait.until(ExpectedConditions.visibilityOf(fbLatestPost));
			actualLatestPost = fbLatestPost.getAttribute("text");
			Assert.assertTrue(actualLatestPost.contains(expectedLatestPost));
		} 
		catch (NoSuchElementException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}
