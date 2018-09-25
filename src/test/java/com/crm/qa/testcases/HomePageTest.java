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
		initialization();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		dealsPage = new DealsPage();
		contactsPage = new ContactsPage();
		tasksPage = new TasksPage();
		homePage = loginPage.loginin(prop.getProperty("Username"), prop.getProperty("Password"));
	}
		
	@Test(priority=1)
	public void verifyHomePageTitleTest(){
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO", "Home Page Title Not Matched");
	}
	
	//@Test(priority=2)
	public void verifyLoggedInUserTest() throws InterruptedException{
		testUtil.switchToFrame();
		Thread.sleep(2000);
		Assert.assertTrue(homePage.verifyLoggedInUser());
	}
	
	@Test(priority=3)
	public void verifyHomePageLogoTest(){
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyHomePageLogo());
	}
	
	@Test(priority=4)
	public void verifyDealsLinkTest(){
		testUtil.switchToFrame();
		dealsPage = homePage.verifyDealsLink();
	}
	
	@Test(priority=5)
	public void verifyContactsLinkTest() throws InterruptedException{
		testUtil.switchToFrame();
		contactsPage = homePage.verifyContactsLink();
		//contactsPage.selectContactsByName("Aaaron Jacob");
	}
	
	@Test(priority=6)
	public void verifyTasksLinkTest(){
		testUtil.switchToFrame();
		tasksPage = homePage.verifyTasksLink();
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}


}
