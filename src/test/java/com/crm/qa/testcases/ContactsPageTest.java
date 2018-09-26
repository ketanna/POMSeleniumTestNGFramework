package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	String sheetName = "contacts";
	/*
	 * super constructor
	 */
	public ContactsPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException{
		logInfoStartTest();
		initialization();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		Thread.sleep(2000);
		homePage = loginPage.loginin(prop.getProperty("Username"), prop.getProperty("Password"));
		log.info("Login Successful");
		testUtil.switchToFrame();
		contactsPage = homePage.verifyContactsLink();
		log.info("Verified Contact Link");
	}
	
	@Test(priority=1)
	public void verifyContactslabelTest(){
		contactsPage.verifyContactslabel();
		Assert.assertTrue(contactsPage.verifyContactslabel(), "Contacts label is missing on the page");
		log.info("Verified Contact Label");
	}
	
	@Test(priority=2)
	public void selectSingleContactByNameTest() throws InterruptedException{
		contactsPage.selectContactsByName("Akshay Kumar");	
		log.info("Verified Select Single Contact By Name");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsByNameTest() throws InterruptedException{
		contactsPage.selectContactsByName("Aman sang");	
		contactsPage.selectContactsByName("Agnimitra Paul");	
		log.info("Verified Select Multiple Contacts By Name");
	}
	
	@DataProvider
	public Object[][] getCreateNewContactTestData(){
		Object data [][] = TestUtil.getTestData(sheetName);
		log.info("Taken Excel sheet data");
		return data;
		
	}
	
	@Test(dataProvider="getCreateNewContactTestData", priority=4)
	public void createNewContactFromExcelDataTest(String title, String firstname, String lastname, String company){
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact(title, firstname, lastname, company);
		log.info("New Contact has been created via Contacts Page");
	}
	
	
	/*@Test(priority=5)
	public void validateCreateNewContactTest(){
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact("Mr.", "Ketan", "Naik", "BBD");
		log.info("New contact has been created via contacts page");
	}*/

	@AfterMethod
	public void tearDown(){
		driver.quit();
		log.info("Close Driver Successful");
		logInfoEndTest();
	}
	
	
	

}
