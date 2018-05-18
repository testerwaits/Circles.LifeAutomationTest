package com.circlesLife.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import com.circlesLife.Utils.TestRunner;
import com.circlesLife.Utils.Utils;
import com.facebook.Pages.FacebookAppActivityLogPage;
import com.facebook.Pages.FacebookAppHomePage;
import com.facebook.Pages.FacebookAppLoginPage;
import com.facebook.Pages.FacebookWebsiteHomePage;
import com.facebook.Pages.FacebookWebsiteLoginPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class FacebookAutomationTestCase {
	
	// Fetch Test data From XML
	public String fbURL = Utils.getTestData("ConfigurationData.Facebook_URL");
	public String browser = Utils.getTestData("ConfigurationData.Browser");
	public static String fbPostMessage;
	
	// Launch website and initialize wait
	WebDriver driver = TestRunner.startBrowser(browser, fbURL);	
	
	//Start Appium Session and Create App Instance
	AppiumDriverLocalService service;
	AppiumDriver<WebElement> mobiledriver;
	
	//Create Page Objects using Page Factory
	FacebookWebsiteHomePage objFacebookWebsiteHomePage = PageFactory.initElements(driver, FacebookWebsiteHomePage.class);
	FacebookWebsiteLoginPage objFacebookWebsiteLoginPage = PageFactory.initElements(driver, FacebookWebsiteLoginPage.class);
	FacebookAppLoginPage objFacebookLoginPage;
	FacebookAppHomePage objFacebookHomePage;
	FacebookAppActivityLogPage objFacebookAppActivityLogPage;
	
	
	@Test(priority = 1, enabled= false)
	public void loginToFacebookWebsite()
	{
		objFacebookWebsiteLoginPage.loginToFacebook();
	}
	
	@Test(priority = 2, enabled= false)
	public void addNewPostInFacebook()
	{
		fbPostMessage = "Test Post"+objFacebookWebsiteHomePage.randomGenerator();
		objFacebookWebsiteHomePage.verifyHomePage();
		objFacebookWebsiteHomePage.postMessageInFacebook(fbPostMessage);
	}
	
	@Test(priority = 3, enabled= false)
	public void verifyAppiumConnectionAndAppLuanch() 
	{	//Start Appium Session and Create App Instance
		service=TestRunner.createAppiumSession();
		mobiledriver= TestRunner.createInstance();	
		
		//Create Page Objects using Page Factory	
		objFacebookLoginPage = new FacebookAppLoginPage(mobiledriver);
		objFacebookHomePage = new FacebookAppHomePage(mobiledriver);
		objFacebookAppActivityLogPage = new FacebookAppActivityLogPage(mobiledriver);
		Assert.assertTrue(service!=null);
		Assert.assertTrue(mobiledriver!=null);
	}

	@Test(priority = 4,  enabled= false)
	public void verifyFacebookLoginTest()
	{		
		objFacebookLoginPage.loginToFacebook();
		objFacebookHomePage.verifySuccessfulLogin();
	}
	
	@Test(priority = 5,  enabled= false)
	public void verifyLatestPostFromActivityLog()
	{
		objFacebookHomePage.accessFacebookProfile();		
		objFacebookHomePage.accessActivityLog();
		objFacebookAppActivityLogPage.verifyActivityLogPage();
		objFacebookAppActivityLogPage.verifyWebsitePostedCommentInActivityLog(fbPostMessage);
	}
	
	@AfterTest
	public void end()
	{
		driver.quit();
		mobiledriver.removeApp("com.facebook.katana");
		mobiledriver.closeApp();
		service.stop();
	} 
}
