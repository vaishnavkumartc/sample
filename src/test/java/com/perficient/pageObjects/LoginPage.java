package com.perficient.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.perficient.util.CommonUtilities;

public class LoginPage extends CommonUtilities{
	
	public LoginPage() {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(how=How.ID,using="username")
	private WebElement eleUserName;
	
	public LoginPage enterUserName(String data,boolean clear) {
		sendKeys(eleUserName, data, clear);
		return this;
	}
	
	@FindBy(how=How.ID,using="password")
	private WebElement elePassword;
	
	public LoginPage enterPassword(String data,boolean clear) {
		sendKeys(elePassword, data, clear);
		return this;
	}
	
	@FindBy(how=How.CLASS_NAME,using="decorativeSubmit")
	private WebElement eleLogin;
	
	public HomePage clickLogIn() {
		click(eleLogin);
		return new HomePage();		
	}
	
}