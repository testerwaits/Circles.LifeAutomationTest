package com.circlesLife.Pages;

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
		wait.until(ExpectedConditions.visibilityOf(userName));
		userName.isDisplayed();
	}
	
	public void loginToWebsite()
	{
		userName.sendKeys("surajwaits@gmal.com");
		password.sendKeys("testerwaits");
		//((JavascriptExecutor) driver).executeScript("arguments[0].click();", singnInBtn);	
		singnInBtn.click();
	}
}
