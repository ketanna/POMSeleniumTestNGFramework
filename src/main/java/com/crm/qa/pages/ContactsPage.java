package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{

	@FindBy (xpath = "//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy (id = "first_name")
	WebElement firstName;
	
	@FindBy (id = "surname")
	WebElement lastName;
	
	@FindBy (name = "client_lookup")
	WebElement comapany;
	
	@FindBy (xpath = "//input[@type='submit' and @value='Save']")
	WebElement saveBtn;
	
	/*
	 * Constructor - To initialize page objects;
	 */
	public ContactsPage(){
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Methods/actions on contact page
	 */
	public boolean verifyContactslabel(){
		return contactsLabel.isDisplayed();
	}
	
	public void selectContactsByName(String name) throws InterruptedException{
	driver.findElement(By.xpath("//a[text()='"+ name +"']//parent::td[@class='datalistrow']"+
	"//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
	Thread.sleep(2000);
	}
	
	public void createNewContact(String title,String ftName,String ltName,String comp){
		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);
		
		firstName.sendKeys(ftName);
		lastName.sendKeys(ltName);
		comapany.sendKeys(comp);
		saveBtn.click();
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
