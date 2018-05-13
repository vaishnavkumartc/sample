/**
 * TestNG class which gets called for every test case. 
 * This is the driver class which runs for each test case. 
 * It reads the corresponding testdata from the input sheet and runs the actual test method 
 * and writes back the test results to the output sheet.
 * 
 */

package com.perficient.core;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Properties;
//import java.util.logging.FileHandler;
//import java.util.logging.Logger;
//import java.util.logging.SimpleFormatter;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.logging.LogEntries;
//import org.openqa.selenium.logging.LogEntry;
//import org.openqa.selenium.logging.LogType;
//import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;

import com.perficient.util.BrowserUtilities;
import com.perficient.util.CommonUtilities;
import com.perficient.util.IOUtil;
import com.perficient.util.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestDriver extends CommonUtilities{

	public static LinkedHashMap<String, String> parameters;
	public static String outputExcelFileName;
	public static LinkedList<LinkedHashMap<String, String>> results = new LinkedList<LinkedHashMap<String, String>>();
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static Properties props;
	public static int intIterationSize;
	
	//BeforeSuite sets up the configuration file and sets up the excel & html output files
	@BeforeSuite
	public void suiteSetup() throws IOException {
		File configFile = new File("src/test/java/com/perficient/core/user_config.properties");
		FileReader reader = new FileReader(configFile);
		props = new Properties();
		// load the properties file:
		props.load(reader);
		// Setup Excel Output file
		outputExcelFileName = IOUtil.setupExcelOutput();
		//Setup Extent HTML report
		extent = ExtentManager.Instance();
	}

	
	@BeforeMethod()
	public void InitializeTestIteration(Method result, Object[] BrowserData,ITestContext context){
		//Based on the Test Method Name, we get the entire row of cells from the Input.xls file into hashmap
		parameters = IOUtil.getInputData(result.getName());
		//*******Obtain the driver object from data provider and Browser Utilities******
		driver = BrowserUtilities.getBrowser(BrowserData[0].toString(),context.getCurrentXmlTest().getParameter("remote"));
		//Set the desired capabilities for the driver
		Capabilities cap = ((RemoteWebDriver) TestDriver.driver).getCapabilities();
		//Add a field "browser version into the parameters hashmap
		parameters.put("browser_version", cap.getBrowserName().toString() + " " + cap.getVersion().toString());
		//Start the HTML reports
		test = extent.startTest(parameters.get("TC_Name") + " [" + BrowserData[0].toString() + "]", parameters.get("TC_Description"));
		intIterationSize--;
	}

	//This returns the Object which is the WebDriver 
	@DataProvider(name = "data-provider")
	public static Object[][] dataProvider(ITestContext context) {
		String[] browsersArray = context.getCurrentXmlTest().getParameter("browsers").split(",");
		intIterationSize = browsersArray.length;
		Object[][] obj = new Object[browsersArray.length][1];
		for (int i = 0; i < browsersArray.length; i++) {
			//Invokes getBrowser method to obtain the  corresponding WebDriver object
			obj[i][0] = browsersArray[i];
		}
		return obj;
	}			

	//All the tear down operations occur. Since Reporting is done at the end of every test method
	@AfterMethod
	public void tearDown(){	
		if (driver != null)
	        driver.quit();
	    extent.endTest(test);
		
		if (intIterationSize == 0) {
			IOUtil.writeExcelOutput(outputExcelFileName, parameters, results);
			results.clear();
		}
		
		//The parameters Object is cleared and readied for the next test
		parameters.clear();
	}
	
	
	//The extent reporting is done for each suite. The extent/HTML report is flushed and closed
	@AfterSuite
	public void flush() {
		extent.flush();
		extent.close();
	}

}
