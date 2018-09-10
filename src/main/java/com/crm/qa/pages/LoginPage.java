package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//Page factory - OR:
	@FindBy(name = "username")
	WebElement Username;

	@FindBy(name= "password")
	WebElement Password;
	
	@FindBy(xpath = "//input[@value='Login' or type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath = "//button[contains(text(),'Sign Up')]")
	WebElement signUpBtn;
	
	@FindBy(xpath = "//img[contains(@class, 'img-responsive')]")
	WebElement crmLogo;
	
	//Initializing the page objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions/methods/features:
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	public boolean validateCRMLogoImage(){
		return crmLogo.isDisplayed();
	}
	
	public HomePage loginin(String un, String pwd) throws InterruptedException{
		Username.sendKeys(un);
		Password.sendKeys(pwd);
		Thread.sleep(2000);
		loginBtn.click();
		return new HomePage();
	}
	
	
	
	
	
}
