package com.crm.qa.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	DealsPage dealsPage;
	ContactsPage contactsPage;
	TasksPage tasksPage;
	
	public HomePageTest(){
		super();
	}
	/* Every time test case will login because, it should be independent of each other 
	 * else same browser then it will be exhausted
	 * before every test launch the browser then @test & close the browser
	*/
	
	@BeforeMethod
	public void setUp() throws InterruptedException{
		logInfoStartTest();
		initialization();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		dealsPage = new DealsPage();
		contactsPage = new ContactsPage();
		tasksPage = new TasksPage();
		homePage = loginPage.loginin(prop.getProperty("Username"), prop.getProperty("Password"));
		log.info("Login Successful");
	}
		
	@Test(priority=1)
	public void verifyHomePageTitleTest(){
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO", "Home Page Title Not Matched");
		log.info("Verified Home Page Title");
	}
	
	@Test(priority=2)
	public void verifyLoggedInUserTest() throws InterruptedException{
		testUtil.switchToFrame();
		Thread.sleep(2000);
		Assert.assertTrue(homePage.verifyLoggedInUser());
		log.info("Logged in user verified on Home page");
	}
	
	@Test(priority=3)
	public void verifyHomePageLogoTest(){
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyHomePageLogo());
		log.info("Verified Home Page Logo");
	}
	
	@Test(priority=4)
	public void verifyDealsLinkTest(){
		testUtil.switchToFrame();
		dealsPage = homePage.verifyDealsLink();
		log.info("Verifed Deals Link on Home page");
	}
	
	@Test(priority=5)
	public void verifyContactsLinkTest() throws InterruptedException{
		testUtil.switchToFrame();
		contactsPage = homePage.verifyContactsLink();
		log.info("Verified Contacts Link on Home page");
		//contactsPage.selectContactsByName("Aaaron Jacob");
	}
	
	@Test(priority=6)
	public void verifyTasksLinkTest(){
		testUtil.switchToFrame();
		tasksPage = homePage.verifyTasksLink();
		log.info("Verified Tasks Link on Home page");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
		log.info("Close Driver Successful");
		logInfoEndTest();
	}


}
