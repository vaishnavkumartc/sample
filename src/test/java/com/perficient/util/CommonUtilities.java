package com.perficient.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.perficient.core.TestDriver;
import com.relevantcodes.extentreports.LogStatus;
import com.perficient.core.TestDriver;


public class CommonUtilities {
	public static WebDriver driver;
	
	//create screen shots
	public String getScreenshot(WebDriver driver, String Status) throws Exception {
		 File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 File destfile = new File(new SimpleDateFormat("'reports/screenshots/" + TestDriver.parameters.get("TC_Name") + "_" + Status + "_Screenshot_'yyyyMMdd_hhmmss'.png'").format(new Date()));
		 FileUtils.copyFile(srcfile,destfile);
		 return destfile.getAbsolutePath();
	}
	
	//type in input field
	//accepts element, text to type, clearing the field before typing true/false 
	public static void sendKeys(WebElement element, String text, boolean clear){
		if(clear){
			element.clear();
		}
			element.sendKeys(text);
	}
	
	//clear input field
	public static void clear(WebElement element){
		element.clear();
	}
	
	//get element text
	public static String getText(WebElement element){
		return element.getText();
	}
	
	
	//click on element
	public static void click(WebElement element){
		element.click();
	}
	
	//check if element is present by size
	public static boolean isElementPresent(WebElement element){
		try {
			if(element.getSize() != null )
				return true;
			else
				return false;
		} catch (NoSuchElementException e){
			return false;
		}
	}
	
	//check Element present by By
	public static boolean isElementPresent(WebDriver driver,By by){
		try {
			driver.findElement(by); 
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	//wait for page to load
	public static void pageLoadTimeout(WebDriver driver,int seconds){
		driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
	}

	//This is the reporting function for the Excel Reports. This  Sets the values into a linked hash map that is then written into the Excel file in IOUTIL
	public void report(String Status, String Description, String ExpectedResult, String ActualResult, String Exception, boolean isScreenshotNeeded) throws Exception{
		LinkedHashMap<String, String> resultStep = new LinkedHashMap<String,String>();
		resultStep.put("Status", Status.toUpperCase());
		resultStep.put("Description", Description);
		resultStep.put("ExpectedResult", ExpectedResult);
		resultStep.put("ActualResult", ActualResult);
		resultStep.put("Exception", Exception);
		resultStep.put("Browser", TestDriver.parameters.get("browser_version"));
		TestDriver.results.add(resultStep);
		
		if (isScreenshotNeeded) {
			getScreenshot(TestDriver.driver, Status.toUpperCase());
		}
		
		
		
		switch (Status.toUpperCase()) {
		
			//case "PASS": TestDriver.parentTest.get()
			case "PASS": TestDriver.test.log(LogStatus.PASS, Description); break;
			case "FAIL": TestDriver.test.log(LogStatus.FAIL, Description); break;
			case "WARNING": TestDriver.test.log(LogStatus.WARNING, Description); break;
			case "INFO": TestDriver.test.log(LogStatus.INFO, Description); break;
			case "ERROR": TestDriver.test.log(LogStatus.ERROR, Description); break;
			default: TestDriver.test.log(LogStatus.UNKNOWN, Description); break;
		}
		
	}
	
	//Recovery function in case the test program encounters an Exception. Graceful exit from current test, with appropriate report added to both the excel and HTML reports.
	public void recover(String Exception) throws Exception{
		LinkedHashMap<String, String> resultStep = new LinkedHashMap<String,String>();
		resultStep.put("Status", "ERROR");
		resultStep.put("Description", "An error occured during the execution");
		resultStep.put("Exception", Exception);
		TestDriver.results.add(resultStep);
		getScreenshot(TestDriver.driver, "ERROR");
		TestDriver.test.log(LogStatus.ERROR, Exception);		
		Assert.fail(Exception);
	}
	
	
}
