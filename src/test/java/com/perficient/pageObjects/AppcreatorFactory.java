package com.perficient.pageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppcreatorFactory {

	WebDriver driver;

	@FindBy(name = "lid")
	WebElement username;

	@FindBy(name = "pwd")
	WebElement password;

	@FindBy(id = "signin_submit")
	WebElement signin;

	@FindBy(linkText = "SIGN-OFF")
	WebElement signOff;

	@FindBy(xpath = "//div[@class='primary-btn btn createNewApp pull-left']")
	public WebElement NewApplicationBtn;

	@FindBy(xpath = "//div[@class='zc-dem-inner-pad']/div[@class='zc-app-logo']")
	public WebElement CreatefrmScratch;

	@FindBy(xpath = "//div[@class='app-scratch-container']")
	public WebElement ScratchContainer;

	@FindBy(xpath = "//div/input[@name='submitBtn' and @value='Create']")
	public WebElement CreateBtn;

	@FindBy(id = "scrAppName")
	public WebElement EnterAppNameText;

	@FindBy(xpath = "//div[@class='upgrade-container']/div[@class='upgrade-header']/div[@class='flRight appNameClose']")
	public WebElement UpgradesubsBtn;

	public AppcreatorFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='zc-app-access']")
	public WebElement MyApp;

	@FindBy(xpath = "//ul[@class='zc-app-opt']//a[text()='Edit']")
	public WebElement editButton;

	@FindBy(xpath = "//div[@id='layoutSwitch']")
	public WebElement ChangeLayout;

	@FindBy(xpath = "//div[@id='layoutSwitch']//li[@layout='double']")
	public WebElement DoubleLayoutOption;

	@FindBy(xpath = "//div[@id='fields-container']//p[text()='Drop Down']")
	public WebElement dropdown;

	@FindBy(xpath = "//div[@id='fields-container']//p[text()='Single Line']")
	public WebElement singleline;

	@FindBy(xpath = "//div[@id='fields-container']//p[text()='Multi Line']")
	public WebElement Multiline;

	@FindBy(xpath = "//div[@id='fields-container']//p[text()='Date']")
	public WebElement date;

	@FindBy(xpath = "//input[@id='displayName']")
	public WebElement DisplayName;

	@FindBy(xpath = "//ul[@id='fieldsList']")
	public WebElement Destination1;

	@FindBy(xpath = "//ul[@id='fieldsList1']")
	public WebElement Destination2;

	@FindBy(xpath = "//div[@class='form_cont']//a")
	public WebElement form;

	@FindBy(xpath = "//input[@id='Ok']")
	public WebElement okBtn_ErrorPopup;

	@FindBy(xpath = "//ul[@id='fieldsList']//li[contains(.,'Project*')]")
	public WebElement projElement;

	@FindBy(xpath = "//ul[@id='fieldsList']//li[contains(.,'IssueType*')]")
	public WebElement issueTypeElement;

	@FindBy(xpath = "//ul[@id='fieldsList']//li[contains(.,'Priority')]")
	public WebElement priorityElement;

	@FindBy(xpath = "//ul[@id='formButtons']//span[text()='Submit']")
	public WebElement submitBtn;

	@FindBy(xpath = "//ul[@id='formButtons']//span[text()='Reset']")
	public WebElement resetBtn;

	@FindBy(xpath = "//div[@id='accessapp']//a")
	public WebElement accessApp;

	@FindBy(xpath = "//div[@class='app-nm' and text()='Creator']")
	public WebElement creatorBtn;

	@FindBy(xpath = "//ul[@id='formTitle']//li[@class='tempFrmWrapper tempHeadContBdr']")
	public WebElement formName;

	@FindBy(xpath = "//input[@name='formDisplayName']")
	public WebElement formDispName;

	@FindBy(xpath = "//div[@id='s2id_autogen2']")
	public WebElement prjdrpAccess;

	@FindBy(xpath = "//div[@id='s2id_autogen2']")
	public WebElement prioritydrpAccess;

	@FindBy(xpath = "//div[@id='s2id_autogen3']")
	public WebElement issuedrpAccess;

	@FindBy(xpath = "//input[@id='zc-Summary']")
	public WebElement summaryAccess;

	@FindBy(xpath = "//textarea[@id='zc-Description']")
	public WebElement descriptionAccess;

	@FindBy(xpath = "//input[@name='submit']")
	public WebElement createAccess;

	@FindBy(xpath = "//div[contains(@class,'form-success-msg')]//p[contains(.,'Data Added Successfully!')]")
	public WebElement DataAddedSuccess;

	@FindBy(xpath = "//input[@id='Due_Date']")
	public WebElement DueDate;

	@FindBy(xpath = "//input[@id='zc-StoryPoints']")
	public WebElement StoryAccess;

	public void EnterDate(String text, WebElement element) throws Exception {
		element.click();
		waitFor(1);
		element.sendKeys(text);
		waitFor(2);
	}

	public void EnterText(String text, WebElement element) throws Exception {

		element.sendKeys(text);
		waitFor(2);
	}

	public void selectDrpdown(int val, String drpdwnOption, WebDriver wdriver) throws Exception {
		// element.click();
		List<WebElement> roleIcon = wdriver.findElements(By.xpath("//span[@role='presentation']"));
		roleIcon.get(val).click();
		waitFor(3);
		wdriver.findElement(
				By.xpath("//div[contains(@style,'display: block;')]//li[contains(.,'" + drpdwnOption + "')]")).click();
		waitFor(1);
	}

	public void EnterFormName(String text) throws Exception {
		formDispName.clear();
		waitFor(5);
		okBtn_ErrorPopup.click();
		waitFor(5);
		formDispName.sendKeys(text);
		formDispName.sendKeys(Keys.ENTER);
		waitFor(5);
	}

	public void clickElement(WebElement element) throws Exception {
		element.click();
		waitFor(3);
	}

	public void loginApp(String uname, String pswd, WebDriver wdriver, String url) throws InterruptedException  {
		wdriver.get(url);
		waitFor(2);
		username.sendKeys(uname);
		password.sendKeys(pswd);
		signin.click();
		waitFor(3);
		creatorBtn.click();
		waitFor(3);
		UpgradesubsBtn.click();
	}

	public void createApp(String text) throws Exception {
		NewApplicationBtn.click();
		waitFor(5);
		CreatefrmScratch.click();
		waitFor(5);
		EnterAppNameText.sendKeys(text);
		waitFor(5);
		CreateBtn.click();
		waitFor(5);
	}

	public void formclick() throws Exception {
		form.click();
		waitFor(2);
	}

	/*
	 * public void scrollToElement(WebDriver driver,WebElement element) {
	 * 
	 * ((JavascriptExecutor) driver).executeScript(
	 * "arguments[0].scrollIntoView();", element); }
	 */

	public void EnterDisplayName(String dispName) throws Exception {
		DisplayName.clear();
		waitFor(5);
		// waitUntilElementClickable(okBtn_ErrorPopup);
		okBtn_ErrorPopup.click();
		waitFor(3);
		DisplayName.sendKeys(dispName);
		waitFor(2);
	}

	public void addNewField(WebElement Source, WebElement Dest, WebDriver driver) throws Exception {

		Actions moveAction = new Actions(driver);
		moveAction.dragAndDrop(Source, Dest).build().perform();
		waitFor(4);
		
	}

	public void changeLayout() throws Exception {
		ChangeLayout.click();
		waitFor(1);
		DoubleLayoutOption.click();
		waitFor(2);

	}

	public void editMyApp(WebDriver driver) throws Exception {

		Actions moveAction = new Actions(driver);
		moveAction.moveToElement(MyApp).build().perform();
		waitFor(1);
		editButton.click();
		waitFor(5);
	}

	public boolean checkPointReached(WebElement check) {
		if (check.isDisplayed())
			return true;
		else
			return false;
	}

	public void getUrl(String text, WebDriver wDriver) {
		wDriver.get(text);
	}

	public void waitFor(long waitSeconds) throws InterruptedException {
		Thread.sleep(waitSeconds * 1000);
		
	}

	public void addDropdownvalues(WebDriver driver, String options) throws Exception {
		String array[] = options.split(",");
		List<WebElement> choiceList = driver.findElements(By.xpath(
				"//div[@class='formActFields']//ul[@class='ui-sortable'and @id='fieldChoices']//input[@name='choices']"));
		for (int i = 0; i < choiceList.size(); i++) {
			choiceList.get(i).clear();
			waitFor(5);
			okBtn_ErrorPopup.click();
			waitFor(5);
			choiceList.get(i).sendKeys(array[i]);
			waitFor(5);
		}

	}

}
