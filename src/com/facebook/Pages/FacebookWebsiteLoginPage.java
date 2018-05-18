package com.facebook.Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.circlesLife.Utils.Utils;

public class FacebookWebsiteLoginPage {
	WebDriverWait wait;
	WebDriver driver;
	// Fetch Test data From XML
	public String fbUserName = Utils.getTestData("FacebookLoginPage.UserName");
	public String fbPassword = Utils.getTestData("FacebookLoginPage.UserPassword");

	public FacebookWebsiteLoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);

	}

	@FindBy(how = How.ID, using = "email")
	@CacheLookup
	public WebElement userName;

	@FindBy(id = "pass")
	public WebElement password;

	@FindBy(xpath = "//input[@value='Log In']")
	public WebElement loginBtn;

	public void verifyLoginPage() {
		try 
		{
			wait.until(ExpectedConditions.visibilityOf(userName));
			Assert.assertTrue(userName.isDisplayed());
		} 
		catch (NoSuchElementException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public void loginToFacebook() {
		try 
		{
			userName.sendKeys(fbUserName);
			password.sendKeys(fbPassword);
			loginBtn.click();
		} 
		catch (NoSuchElementException e) 
		{
			System.out.println(e.getMessage());
		}

	}
}
