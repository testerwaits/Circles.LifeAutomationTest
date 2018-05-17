package com.circlesLife.TestCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.circlesLife.Utils.TestRunner;
import com.circlesLife.Utils.Utils;
import com.facebook.Pages.FacebookHomePage;
import com.facebook.Pages.FacebookLoginPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class FacebookAutomationTestCase {


	//Start Appium Session and Create App Instance
	AppiumDriverLocalService service;
	AppiumDriver<WebElement> mobiledriver;
	
	//Create Page Objects using Page Factory	
	FacebookLoginPage objFacebookLoginPage;
	FacebookHomePage objFacebookHomePage;
		
	@Test(priority = 1, enabled= true)
	public void verifyAppiumConnectionAndAppLuanch() 
	{	//Start Appium Session and Create App Instance
		service=TestRunner.createAppiumSession();
		mobiledriver= TestRunner.createInstance();	
		
		//Create Page Objects using Page Factory	
		objFacebookLoginPage = new FacebookLoginPage(mobiledriver);
		objFacebookHomePage = new FacebookHomePage(mobiledriver);
		
		Assert.assertTrue(service!=null);
		Assert.assertTrue(mobiledriver!=null);
	}

	@Test(priority = 2,  enabled= true)
	public void verifyFacebookLoginTest()
	{		
		//objFacebookLoginPage.loginToFacebook();
		objFacebookHomePage.accessFacebookProfile();
		
	}
	
	
	@AfterTest
	public void end()
	{
		//driver.removeApp("com.facebook.katana");
		mobiledriver.closeApp();
		service.stop();
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
