package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		logInfoStartTest();
		initialization();
		loginPage = new LoginPage();
		log.info("Landed on to Login page verified");
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "#1 Free CRM software in the cloud for sales and service");
		log.info("Verified Login Page Title");
	}
	
	@Test(priority=2)
	public void loginPageLogoTest(){
		boolean logo = loginPage.validateCRMLogoImage();
		Assert.assertTrue(logo);
		log.info("Verified Login page Logo");
	}
	
	@Test(priority=3)
	public void loginTest() throws InterruptedException {
		homePage = loginPage.loginin(prop.getProperty("Username"), prop.getProperty("Password"));
		log.info("Verified Login has been sucessful");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
		log.info("Close Driver Successful");
		logInfoEndTest();
	}
	
	
	 
	
}
