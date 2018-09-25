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
		initialization();
		loginPage = new LoginPage();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "#1 Free CRM software in the cloud for sales and service");
	}
	
	@Test(priority=2)
	public void loginPageLogoTest(){
		boolean logo = loginPage.validateCRMLogoImage();
		Assert.assertTrue(logo);
	}
	
	//@Test(priority=3)
	public void loginTest() throws InterruptedException {
		homePage = loginPage.loginin(prop.getProperty("Username"), prop.getProperty("Password"));
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	 
	
}
