package com.circlesLife.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.circlesLife.Pages.CirclesLifeHomePage;
import com.circlesLife.Pages.CirclesLifeLoginPage;
import com.circlesLife.Utils.TestRunner;
import com.circlesLife.Utils.Utils;




public class CirclesLifeAutomationTestCases 
{	
	// Fetch Test data From XML
	public String wesiteURL = Utils.getTestData("ConfigurationData.Website_URL");
	public String browser = Utils.getTestData("ConfigurationData.Browser");
	
	// Launch website and initialize wait
	WebDriver driver = TestRunner.startBrowser(browser, wesiteURL);	
	
	//Create Page Objects using Page Factory
	
	CirclesLifeHomePage objCirclesLifeHomePage = PageFactory.initElements(driver, CirclesLifeHomePage.class);
	CirclesLifeLoginPage objCirclesLifeLoginPage = PageFactory.initElements(driver, CirclesLifeLoginPage.class);
	
	@Test(priority = 1)
	public void verifyWebsiteLaunch()
	{
		Assert.assertTrue(driver!=null);
		objCirclesLifeHomePage.verifyHomePage();
		
	}	
	
	@Test(priority = 2)
	public void loginToCirclesLifeWebsite()
	{
		objCirclesLifeHomePage.accessLoginPage();
		objCirclesLifeLoginPage.verifyLoginPage();
		objCirclesLifeLoginPage.loginToWebsite();
	}
	
	// If we need to Pass multiple Credentials for Login Test we can Pass Using This Data Provider	
	@DataProvider(name="UserCredentials")
	public Object[][] getLoginTestData()
	{
		// Create object with two paraments			
		Object[][] data = new Object[2][2];
		
		data[0][0] = Utils.getTestData("LoginPage.InvalidUser_Email1");
		data[0][1] = Utils.getTestData("LoginPage.InvalidUser_Password1");
				
		return data;
	}
	

}
