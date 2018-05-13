package com.perficient.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.perficient.util.CommonUtilities;

public class HomePage extends CommonUtilities{
	
	public HomePage() {
		PageFactory.initElements(driver,this);
	}
	
	
	
}