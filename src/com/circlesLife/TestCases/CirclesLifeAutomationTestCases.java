package com.circlesLife.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.circlesLife.Pages.CirclesLifeHomePage;
import com.circlesLife.Pages.CirclesLifeLoginPage;
import com.circlesLife.Pages.CirclesLifeMyAccountPage;
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
	CirclesLifeMyAccountPage objCirclesLifeMyAccountPage = PageFactory.initElements(driver, CirclesLifeMyAccountPage.class);
	
	
	@Test(priority = 1)
	public void verifyWebsiteLaunch()
	{
		Assert.assertTrue(driver!=null);
		objCirclesLifeHomePage.verifyHomePage();
		
	}	
	
	@Test(priority = 2,dataProvider="UserCredentials")
	public void loginToCirclesLifeWebsite(String userEmail,String userPassword)
	{
		objCirclesLifeHomePage.accessLoginPage();
		objCirclesLifeLoginPage.verifyLoginPage();
		objCirclesLifeLoginPage.loginToWebsite(userEmail, userPassword);
		objCirclesLifeHomePage.verifySuccessfulLogin();
	}
	
	@Test(priority = 3)
	public void verifyUserNameInMyAccountPage()
	{
		objCirclesLifeHomePage.accessMyAccountPage();
		objCirclesLifeMyAccountPage.verifyMyAccountPage();
		objCirclesLifeMyAccountPage.verifyUserNameInMyAccountPage();
	}
	
	// If we need to Pass multiple Credentials for Login Test we can Pass Using This Data Provider	
	@DataProvider(name="UserCredentials")
	public Object[][] getLoginTestData()
	{
		// Create object with one paraments			
		Object[][] data = new Object[1][2];
		
		data[0][0] = Utils.getTestData("CirclesLifeLoginPage.UserName");
		data[0][1] = Utils.getTestData("CirclesLifeLoginPage.UserPassword");
				
		return data;
	}
	

}
