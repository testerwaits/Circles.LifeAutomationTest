package com.circlesLife.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CirclesLifeHomePage 
{
	WebDriverWait wait;	
	WebDriver driver;
	Actions actions;
	
	public CirclesLifeHomePage(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		actions = new Actions(driver);
	}	
	
	@FindBy(xpath="//ul[@id='menu-footer-one']/li[1]/a")	 
	public WebElement loginLink;
	
	@FindBy(xpath="//div[@class='hidden-md-down']//a[@href='/my_profile']")	 
	public WebElement myAccountLink;
	
	@FindBy(xpath="//label[contains(.,'Email')]/following-sibling::div/input")	 
	public WebElement myAccountEmailLbl;
		
	public void verifyHomePage()
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(loginLink));
			loginLink.isDisplayed();			
		}
		catch (NoSuchElementException e) 
		{
			System.out.println(e.getMessage());
		}	
	}
	
	public void accessLoginPage()
	{	
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginLink);		
	}
	
	public void verifySuccessfulLogin()
	{
		wait.until(ExpectedConditions.visibilityOf(myAccountLink));
		myAccountLink.isDisplayed();	
	}
	
	public void accessMyAccountPage()
	{
		myAccountLink.click();
	}
	
	public void verifyMyAccountPage()
	{
		wait.until(ExpectedConditions.visibilityOf(myAccountEmailLbl));
		myAccountEmailLbl.isDisplayed();
	}
	
	public void verifyUserNameInMyAccountPage()
	{
		String str = myAccountEmailLbl.getAttribute("value");
	}
		
	 
	 
	 
}


