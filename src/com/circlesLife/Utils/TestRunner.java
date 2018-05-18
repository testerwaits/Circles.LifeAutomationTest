package com.circlesLife.Utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestRunner 
{
	public static AppiumDriver<WebElement> mobiledriver;
	private static AppiumDriverLocalService service=null;
	
	static WebDriver driver;
	public static String driverPath =  System.getProperty("user.dir");
	
	//Get Configuration Details from XML
	public static String deviceName = Utils.getTestData("ConfigurationData.deviceName");
	public static  String udid = Utils.getTestData("ConfigurationData.udid");
	public static String pltVersion = Utils.getTestData("ConfigurationData.pltVersion");
	public static String url = Utils.getTestData("ConfigurationData.url");
	public static String platform = Utils.getTestData("ConfigurationData.platform");
	public static String apkName = Utils.getTestData("ConfigurationData.apkName");
	public static String appActivity = Utils.getTestData("ConfigurationData.appActivity");
	public static String packageName = Utils.getTestData("ConfigurationData.packageName");	
	public static String bp = Utils.getTestData("ConfigurationData.bootstrapport");
	public static String nodePath = Utils.getTestData("ConfigurationData.nodePath");
	public static String appiumPath = Utils.getTestData("ConfigurationData.appiumPath");
	
	public static WebDriver startBrowser(String browserName, String url)
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{			
			String exePath = driverPath+"/chromedriver.exe";
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", exePath);
			driver = new ChromeDriver(options);
		}
		else
		{
			driver = null;
		}
		
		if(driver!=null)
		{
			driver.manage().window().maximize();
			driver.get(url);		
		}
		return driver;	
	}
	
	//Create new instance of AppiumDriver
    public static AppiumDriver<WebElement> createInstance()  
    {    	
    	AppiumDriver<WebElement> mobiledriver = null;		
    	
    	// Set Desired Capabilities 
    	DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
    			
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, pltVersion);		
		desiredCapabilities.setCapability(MobileCapabilityType.UDID, udid);
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM, platform);
		desiredCapabilities.setCapability("appPackage", packageName);
		desiredCapabilities.setCapability("appActivity", appActivity);
		desiredCapabilities.setCapability("locale", "US");
		desiredCapabilities.setCapability("deviceReadyTimeout", 450);
		desiredCapabilities.setCapability("noReset", true);	
		
		//Set App Path
		File classpathRoot=new File(System.getProperty("user.dir"));
		File appDir = null;		
		appDir=new File(classpathRoot,"//apk");	
		File app= new File(appDir, apkName);
		desiredCapabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60000);
		
		//Initialize driver
		try 
		{
			mobiledriver = new AndroidDriver<WebElement>(new URL("http://"+url), desiredCapabilities);
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
        return mobiledriver;
    }
    
    // This Method is used for start Appium server programmatically 
    public static AppiumDriverLocalService createAppiumSession()
    {
		String osName = System.getProperty("os.name").toLowerCase();
		String portNum = Utils.getTestData("ConfigurationData.port");
		int portNo = Integer.parseInt(portNum);
		String nodePath = null;
		String appiumPath = null;
		
		if(osName.contains("windows")){
			//windows path
			
			nodePath = "C:\\Program Files\\nodejs\\node.exe";			
			appiumPath = "C:\\Users\\suraj.vijayaraghavan\\AppData\\Local\\Programs\\appium-desktop\\resources\\app\\node_modules\\appium\\build\\lib\\main.js";

		}
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File(nodePath))				
				.withAppiumJS(new File(appiumPath))
				.withIPAddress("127.0.0.1")
				.usingPort(portNo)
				.withArgument(GeneralServerFlag.CALLBACK_PORT, Integer.toString(portNo))
				.withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, bp)
				.withArgument(GeneralServerFlag.SESSION_OVERRIDE));
		if(service!=null)
		{
			service.start();
		}
		return service;
	}   
    
    public static void quiteBrowser()
	{
		driver.quit();
	}
}
