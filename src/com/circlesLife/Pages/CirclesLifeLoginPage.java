package com.circlesLife.Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CirclesLifeLoginPage 
{
	WebDriverWait wait;	
	WebDriver driver;
	
	public CirclesLifeLoginPage(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		
	}	
	
	@FindBy(how=How.NAME, using="email") 
	@CacheLookup
	public WebElement userName;
	
	@FindBy(name="password") 
	@CacheLookup
	WebElement password; 
	
	@FindBy(xpath="//button[contains(.,'Sign In')]") 
	WebElement singnInBtn; 
	
	
	public void verifyLoginPage()
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(userName));
			userName.isDisplayed();
		}
		catch (NoSuchElementException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void loginToWebsite(String user, String passwd)
	{
		try
		{
			userName.sendKeys(user);
			password.sendKeys(passwd);
			singnInBtn.click();
		}
		catch (NoSuchElementException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}
