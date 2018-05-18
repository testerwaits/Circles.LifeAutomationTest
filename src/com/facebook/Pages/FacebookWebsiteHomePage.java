package com.facebook.Pages;

import java.util.Random;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FacebookWebsiteHomePage {
	WebDriverWait wait;
	WebDriver driver;

	public FacebookWebsiteHomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}

	@FindBy(name = "xhpc_message")
	public WebElement postMessage;

	@FindBy(xpath = "//button[@data-testid='react-composer-post-button']")
	public WebElement postBtn;

	public void verifyHomePage() 
	{
		try 
		{
			wait.until(ExpectedConditions.visibilityOf(postMessage));
			Assert.assertTrue(postMessage.isDisplayed());
		} 
		catch (NoSuchElementException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public void postMessageInFacebook(String fbPostMessage) {
		try 
		{
			postMessage.isDisplayed();
			postMessage.sendKeys(fbPostMessage);
			wait.until(ExpectedConditions.elementToBeClickable(postBtn));
			postBtn.click();
		} 
		catch (NoSuchElementException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public String randomGenerator() 
	{
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(10000);
		String randomvalue = Integer.toString(randomInt);
		return randomvalue;
	}
}
