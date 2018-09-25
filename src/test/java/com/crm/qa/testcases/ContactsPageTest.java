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
		initialization();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		Thread.sleep(2000);
		homePage = loginPage.loginin(prop.getProperty("Username"), prop.getProperty("Password"));
		testUtil.switchToFrame();
		contactsPage = homePage.verifyContactsLink();
	}
	
	//@Test(priority=1)
	public void verifyContactslabelTest(){
		contactsPage.verifyContactslabel();
		Assert.assertTrue(contactsPage.verifyContactslabel(), "Contacts label is missing on the page");
	}
	
	//@Test(priority=2)
	public void selectSingleContactsByNameTest() throws InterruptedException{
		contactsPage.selectContactsByName("Amrita Bastawade");	
	}
	
	//@Test(priority=3)
	public void selectMultipleContactsByNameTest() throws InterruptedException{
		contactsPage.selectContactsByName("Amita Mani");	
		contactsPage.selectContactsByName("Amit Thakur");	
	}
	
	@DataProvider
	public Object[][] getCreateNewContactTestData(){
		Object data [][] = TestUtil.getTestData(sheetName);
		return data;
		
	}
	
	@Test(dataProvider="getCreateNewContactTestData", priority=4)
	public void createNewContactFromExcelDataTest(String title, String firstname, String lastname, String company){
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact(title, firstname, lastname, company);
	}
	
	
	/*@Test(priority=5)
	public void validateCreateNewContactTest(){
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact("Mr.", "Ketan", "Naik", "BBD");
	}*/

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	

}
