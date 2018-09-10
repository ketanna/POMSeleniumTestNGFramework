package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	
	
	//**********TestBase class constructor is used here to initialize & load the properties file**************************/
	
	public TestBase(){
		
		try{
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\bbdnet10082\\Desktop\\NedBank-Automation\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			//FileInputStream ip = new FileInputStream("/FreeCRMTest/src/main/java/com/crm/qa/config/config.properties");
			prop.load(ip);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	//***********WebDriver Initialization and get web url********************************************************************************
	
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\bbdnet10082\\Desktop\\NedBank-Automation\\FreeCRMTest\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if (browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\bbdnet10082\\Desktop\\NedBank-Automation\\MyTestProject\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if (browserName.equals("IE")){
			System.setProperty("webdriver.InternetExplorer.driver", "C:\\Users\\bbdnet10082\\Desktop\\NedBank-Automation\\MyTestProject\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}else {
			System.out.println("No Browser details found in Properties file");
		}	
		
		e_driver = new EventFiringWebDriver(driver);
		//Now create object of EventListenerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
				
				
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
