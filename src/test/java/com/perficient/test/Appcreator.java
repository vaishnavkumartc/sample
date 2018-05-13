package com.perficient.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.perficient.core.TestDriver;
import com.perficient.pageObjects.AppcreatorFactory;
//import com.perficient.pageObjects.LoginScreen;

public class Appcreator extends TestDriver {

	// The @Test requires data from the data provider and the data provider class
	// which is TestDriver, hence this declaration is necessary
	@Test(dataProvider = "data-provider", dataProviderClass = TestDriver.class)
	public void LoginLogout(String BrowserName) throws Exception {

		AppcreatorFactory appFactory = new AppcreatorFactory(driver);
		
		// login into zoho creator
		
		appFactory.loginApp(parameters.get("UserName"), parameters.get("Password"), driver, parameters.get("URL"));

		if (appFactory.checkPointReached(appFactory.NewApplicationBtn))

			report("Pass", "Login Success", "The checkpoint passed", "The checkpoint is successfully passed", "", true);

		else
			report("Fail", "Login Failed", "The checkpoint failed", "The checkpoint is failed", "", true);

		// create app
		appFactory.createApp(parameters.get("AppName"));

		if (appFactory.checkPointReached(appFactory.CreatefrmScratch))

			report("Pass", "Create from Scratch clicked", "The checkpoint passed",
					"The checkpoint is successfully passed", "", true);

		else
			report("Fail", "Create from Scratch not clicked", "The checkpoint failed", "The checkpoint is failed", "",
					true);

		appFactory.clickElement(appFactory.formName);
		
		appFactory.EnterFormName(parameters.get("FormName"));
		
		appFactory.waitFor(5);
		
		appFactory.changeLayout();
		
		appFactory.addNewField(appFactory.dropdown, appFactory.Destination1, driver);

		appFactory.EnterDisplayName(parameters.get("ProjectName"));

		appFactory.addNewField(appFactory.dropdown, appFactory.Destination1, driver);

		appFactory.EnterDisplayName(parameters.get("IssueType"));
		
		appFactory.addNewField(appFactory.singleline, appFactory.Destination1, driver);

		appFactory.EnterDisplayName(parameters.get("Summary"));
		
		appFactory.addNewField(appFactory.Multiline, appFactory.Destination1, driver);

		appFactory.EnterDisplayName(parameters.get("Description"));
				
		appFactory.addNewField(appFactory.singleline, appFactory.Destination2, driver);

		appFactory.EnterDisplayName(parameters.get("StoryPoints"));

		appFactory.addNewField(appFactory.dropdown, appFactory.Destination1, driver);

		appFactory.EnterDisplayName(parameters.get("Priority"));

		appFactory.addNewField(appFactory.date, appFactory.Destination2, driver);

		appFactory.EnterDisplayName(parameters.get("DueDate"));

		appFactory.waitFor(3);
		
		//appFactory.scrollToElement(driver, appFactory.projElement);
		
		appFactory.clickElement(appFactory.projElement);

		appFactory.addDropdownvalues(driver, parameters.get("ProjChoices"));

		appFactory.clickElement(appFactory.issueTypeElement);

		appFactory.addDropdownvalues(driver, parameters.get("IssueChoices"));

		appFactory.clickElement(appFactory.priorityElement);

		appFactory.addDropdownvalues(driver, parameters.get("PriorityChoices"));

		appFactory.clickElement(appFactory.submitBtn);

		appFactory.EnterDisplayName(parameters.get("Create"));

		appFactory.clickElement(appFactory.resetBtn);

		appFactory.EnterDisplayName(parameters.get("Cancel"));

		appFactory.clickElement(appFactory.accessApp);
		
		appFactory.waitFor(5);
		// driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MICROSECONDS);
		
		// access application
		
		appFactory.selectDrpdown(0, parameters.get("ProjectAccess"), driver);
		
		appFactory.selectDrpdown(1, parameters.get("IssueTypeAccess"), driver);
		
		appFactory.EnterText(parameters.get("SummaryAccess"), appFactory.summaryAccess);
		
		appFactory.EnterText(parameters.get("DescriptionAccess"), appFactory.descriptionAccess);
		
		appFactory.EnterDate(parameters.get("DueDateAccess"),appFactory.DueDate);
		
		appFactory.EnterText(parameters.get("StoryAccess"), appFactory.StoryAccess);
		
		appFactory.selectDrpdown(2, parameters.get("PriorityAccess"), driver);
		
		appFactory.clickElement(appFactory.createAccess);
		
		if (appFactory.checkPointReached(appFactory.DataAddedSuccess))

			report("Pass", "Data added successfully", "The checkpoint passed",
					"The checkpoint is successfully passed", "", true);

		else
			report("Fail", "Data Not added", "The checkpoint failed", "The checkpoint is failed", "",
					true);
	}

}
