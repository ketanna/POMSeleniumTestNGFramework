package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{

	@FindBy (xpath = "//td[@class='logo_text']" )
	WebElement logoImage;
	
	@FindBy (xpath = "html/body/table[1]/tbody/tr[1]/td/table/tbody/tr/td[1]")
	//html/body/table[1]/tbody/tr[1]/td/table/tbody/tr/td[1]/font
	//@FindBy (xpath = "//font[contains(.,'   User: Ketan Naik  ')]")
	WebElement loggedUserName;
	
	@FindBy (xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy (xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy (xpath = "//a[contains(text(),'New Contact')]")
	WebElement newContactsLink;
	
	@FindBy (xpath = "//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
	
	public boolean verifyHomePageLogo(){
		return logoImage.isDisplayed();
	}
	
	public boolean verifyLoggedInUser(){
		return loggedUserName.isDisplayed();
	}
	
	public DealsPage verifyDealsLink(){
		dealsLink.click();
		return new DealsPage();
	}
	
	public ContactsPage verifyContactsLink(){
		contactsLink.click();
		return new ContactsPage();
	}
	
	public TasksPage verifyTasksLink(){
		tasksLink.click();
		return new TasksPage();
	}
	
	public void clickOnNewContactLink(){
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		newContactsLink.click();
	}
	
	
}
