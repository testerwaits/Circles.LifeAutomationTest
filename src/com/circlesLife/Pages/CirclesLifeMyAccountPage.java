package com.circlesLife.Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.circlesLife.Utils.Utils;

public class CirclesLifeMyAccountPage 
{
	WebDriverWait wait;	
	WebDriver driver;
	String myAccountEmail;
	
	
	public CirclesLifeMyAccountPage(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}	
	
	@FindBy(xpath="//label[contains(.,'Email')]/following-sibling::div/input")	 
	public WebElement myAccountEmailLbl;
	
	public void verifyMyAccountPage()
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(myAccountEmailLbl));
			myAccountEmailLbl.isDisplayed();
		}
		catch (NoSuchElementException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void verifyUserNameInMyAccountPage()
	{
		try
		{
			String userEmail = Utils.getTestData("CirclesLifeLoginPage.UserName");
			myAccountEmail = myAccountEmailLbl.getAttribute("value");
			Assert.assertTrue(myAccountEmail.contains(userEmail));
			driver.quit();
		}
		catch (NoSuchElementException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}
