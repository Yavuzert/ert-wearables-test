package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.utilities.AdminUtils;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;

public class Trial extends BaseSetup{
	
	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;
	AdminUtils adminUtils;
	
	// Trial Page Locators
	private By trialsHeader = By.xpath("//h2[normalize-space()='Trials']");
	
	// Locators
	private By trialSideBarButton = By.xpath("//a[@href='#trial']//span[@class='es-icon-expected-visit es-icon']");
	
	// Trial property locators
	private By clickSession = By.xpath("/html/body/div[1]/main/div/div/form/section/div[2]/div/input");
	private By clickSubject = By.xpath("/html/body/div[1]/main/div/div/form/section/div[3]/div/input");
	private By trialAnalysisVersion = By.xpath("//label[contains(text(), \"Analysis Version\")]/following-sibling::input");
	private By testDate = By.xpath("/html/body/div[1]/main/div/div/form/section/div[5]/div/input");
	private By clickTestDefinition = By.xpath("/html/body/div[1]/main/div/div/form/section/div[6]/div/input");
	private By trialConditionName = By.xpath("//label[contains(text(), \"Condition Name\")]/following-sibling::input");
	private By stopTime = By.xpath("/html/body/div[1]/main/div/div/form/section/div[8]/div/input");
	private By uploadedToMobilityExchange = By.xpath("/html/body/div/main/div/div/form/section/div[10]/div/label");
//	private By clickConfirmationStatus = By.xpath("//label[contains(text(), \"Confirmation Status\")]/following-sibling::input");
	private By trialConfirmationStatusString = By.xpath("//label[contains(text(), \"Confirmation Status String\")]/following-sibling::input");
//	private By clickAnalysisStatus = By.xpath("//label[contains(text(), \"Analysis Status\")]/following-sibling::input");
//	private By clickRecordingType = By.xpath("//label[contains(text(), \"Recording Type\")]/following-sibling::input");
	private By trialNotes = By.xpath("//label[contains(text(), \"Notes\")]/following-sibling::input");
	private By trialLog = By.xpath("//label[contains(text(), \"Log\")]/following-sibling::input");
	private By trialTimezone = By.xpath("//label[contains(text(), \"Timezone\")]/following-sibling::input");
	private By submitButton = By.xpath("//button[normalize-space()='Submit']");
	
	// Trial Table's Link Locators
	private By tableIdLink = By.xpath("//table//tbody//tr[1]//td[1]//a");
	private By tableTestDateLink = By.xpath("//table//tbody//tr[1]//td[2]//a");
	private By tableDataFilesLink = By.xpath("//table//tbody//tr[1]//td[3]//a");
	private By tableDeviceAllocationsLink = By.xpath("//table//tbody//tr[1]//td[4]//a");
	private By tableLogMessagesLink = By.xpath("/html/body/div/main/div/div/div[3]/div/table/tbody/tr[1]/td[5]/div/a");
	
	// Trial Form's Locators
	private By trialFormId = By.xpath("/html/body/div/main/div/div/form/section/div[1]/div/a");
	private By trialFormTestDate = By.cssSelector("section > div:nth-child(5) > div");
	private By trialFormDeleteButton = By.xpath("//button[normalize-space()='Delete']");
	private By deviceFormPageTitle = By.xpath("//h2[@class='es-page-title']");
	
	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public Trial(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}
	
	/**
	 * This method is used to click Trials side bar button
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickTrialsSideBarButton() {
		elementUtils.waitForElementPresent(trialSideBarButton);
		elementUtils.doClick(trialSideBarButton);
	}
	
	/**
	 * This method create new site
	 * 
	 * @param analysisVersion
	 * @param conditionName
	 * @param confirmationStatus
	 * @param confirmationStatusString
	 * @param analysisStatus
	 * @param recordingType
	 * @param notes
	 * @param trLog
	 * @param timezone
	 * @author yavuz.ozturk
	 */
	public void createTrial(String analysisVersion, String conditionName, String confirmationStatus,String confirmationStatusString, String analysisStatus, String recordingType,String notes, String trLog, String timezone) {
		By confirmationStatusOption = By.xpath("//option[contains(text(), '" + confirmationStatus + "')]");
		By analysisStatusOption = By.xpath("//option[contains(text(), '" + analysisStatus + "')]");
		By recordingTypeOption = By.xpath("//option[contains(text(), '" + recordingType + "')]");
		elementUtils.doSendKeys(clickSession, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(clickSession, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(clickSession, Keys.ENTER);
		elementUtils.doSendKeys(clickSubject, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(clickSubject, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(clickSubject, Keys.ENTER);
		elementUtils.waitForElementPresent(trialAnalysisVersion);
		elementUtils.doSendKeys(trialAnalysisVersion, analysisVersion);
		elementUtils.waitForElementPresent(testDate);
		elementUtils.doSendKeys(testDate, "2021-10-14T02:05:23.514-04:00");
		elementUtils.waitForElementPresent(By.xpath("/html/body/div[1]/main/div/div/form/section/div[5]/div/input"));
		driver.findElement(By.xpath("/html/body/div[1]/main/div/div/form/section/div[5]/div/input")).sendKeys(Keys.ENTER);
		elementUtils.doSendKeys(clickTestDefinition, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(clickTestDefinition, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(clickTestDefinition, Keys.ENTER);
		elementUtils.waitForElementPresent(trialConditionName);
		elementUtils.doSendKeys(trialConditionName, conditionName);
		elementUtils.waitForElementPresent(stopTime);
		elementUtils.doSendKeys(stopTime, "2021-10-31T00:00:00.000-04:00");
		elementUtils.waitForElementPresent(By.xpath("/html/body/div[1]/main/div/div/form/section/div[8]/div/input"));
		driver.findElement(By.xpath("/html/body/div[1]/main/div/div/form/section/div[8]/div/input")).sendKeys(Keys.ENTER);
		elementUtils.waitForElementVisible(uploadedToMobilityExchange);
		elementUtils.doClick(uploadedToMobilityExchange);
		elementUtils.waitForElementPresent(confirmationStatusOption);
		elementUtils.doClick(confirmationStatusOption);
		elementUtils.waitForElementPresent(trialConfirmationStatusString);
		elementUtils.doSendKeys(trialConfirmationStatusString, confirmationStatusString);
		elementUtils.waitForElementPresent(analysisStatusOption);
		elementUtils.doClick(analysisStatusOption);
		elementUtils.waitForElementPresent(recordingTypeOption);
		elementUtils.doClick(recordingTypeOption);
		elementUtils.waitForElementPresent(trialNotes);
		elementUtils.doSendKeys(trialNotes, notes);
		elementUtils.waitForElementPresent(trialLog);
		elementUtils.doSendKeys(trialLog, trLog);
		elementUtils.waitForElementPresent(trialTimezone);
		elementUtils.doSendKeys(trialTimezone, timezone);
		elementUtils.doClick(submitButton);
	}
	
	/**
	 * This method is used to get Last Created Trial Analysis Version from Trial Table
	 * 
	 * @return
	 * @author yavuz.ozturk
	 * @throws InterruptedException 
	 */
	public String getLastCreatedTrialAnalysisVersionFromTrialTable() {
		String beforeXpath = "//table//tbody//tr[";
		String afterXpath = "]//td[4]";
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int lastRowIndex = tableRowList.size();
		WebElement lastCreatedUser = elementUtils.getElement(By.xpath(beforeXpath + lastRowIndex + afterXpath));
		return lastCreatedUser.getText();
	}
	
	/**
	 * This method is used to get Last Created Trial Date from Trial Table
	 * 
	 * @return
	 * @author yavuz.ozturk
	 * @throws InterruptedException 
	 */
	public String getLastCreatedTestDateFromTrialTable() {
		String beforeXpath = "//table//tbody//tr[";
		String afterXpath = "]//td[2]";
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int lastRowIndex = tableRowList.size();
		WebElement lastCreatedUser = elementUtils.getElement(By.xpath(beforeXpath + lastRowIndex + afterXpath));
		return lastCreatedUser.getText();
	}
	
	/**
	 * This method is used to get Last Created Trial ID from Trial Table
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getLastCreatedIdFromTrialTable() {
		String beforeXpath = "//table//tbody//tr[";
		String afterXpath = "]//td[1]";
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int lastRowIndex = tableRowList.size();
		WebElement lastCreatedUser = elementUtils.getElement(By.xpath(beforeXpath + lastRowIndex + afterXpath));
		return lastCreatedUser.getText();
	}
	
	/**
	 * This method is used to delete last created Trial from Trial Table Via ID Link
	 * 
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	public void deleteLastCreatedTrialViaIdLink() throws InterruptedException {
		elementUtils.doClick(By.linkText(getLastCreatedIdFromTrialTable()));
		elementUtils.doClick(trialFormDeleteButton);
		TimeUnit.SECONDS.sleep(10);
		adminUtils.clickAlertOk();
	}
	
	/**
	 * This method is used to delete last created Trial from Trial Table Via Test Date Link
	 * 
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	public void deleteLastCreatedTrialViaTestDateLink() throws InterruptedException {
		elementUtils.doClick(By.linkText(getLastCreatedTestDateFromTrialTable()));
		elementUtils.doClick(trialFormDeleteButton);
		TimeUnit.SECONDS.sleep(3);
		adminUtils.clickAlertOk();
	}
	
	/**
	 * This method is used to get trial table's Id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getTrialTableId() {
		elementUtils.waitForElementPresent(tableIdLink);
		String trialTableTrialId = elementUtils.doGetText(tableIdLink);
		return trialTableTrialId;
	}
	
	/**
	 * This method is used to get Trial form's trial Id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getTrialFormId() {
		elementUtils.waitForElementPresent(trialFormId);
		return elementUtils.doGetText(trialFormId);
	}
	
	/**
	 * This method is used to get Trial table's Test Date
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getTrialTableTestDate() {
		elementUtils.waitForElementPresent(tableTestDateLink);
		String trialTableTestDate = elementUtils.doGetText(tableTestDateLink);
		return trialTableTestDate;
	}
	
	/**
	 * This method is used to click Trial table's Test Date link
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickTrialTableTestDateLink() {
		elementUtils.waitForElementPresent(tableTestDateLink);
		elementUtils.doClick(tableTestDateLink);
	}
	
	/**
	 * This method is used to get Trial form's Test Date
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getTrialFormTestDate() {
		elementUtils.waitForElementPresent(trialFormTestDate);
		return elementUtils.doGetText(By.cssSelector("section > div:nth-child(5) > div"));
	}
	
	/**
	 * This method provides to get trial page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getTrialsPageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.TRIALS_PAGE_TITLE);
		return elementUtils.doGetPageTitle();
	}

	/**
	 * This method retrieves the header from the trials page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getTrialsHeader() {
		elementUtils.waitForElementPresent(trialsHeader);
		return elementUtils.doGetText(trialsHeader);
	}
	
	/**
	 * This method returns trial count from trials page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getTrialCount() {
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		elementUtils.waitForElementPresent(tableRowsXpath);
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int trialCount = tableRowList.size();
		return trialCount;
	}
	
	/**
	 * This method is used to edit trial
	 * 
	 * @param analysisVersion
	 * @param conditionName
	 * @param confirmationStatus
	 * @param confirmationStatusString
	 * @param analysisStatus
	 * @param recordingType
	 * @param notes
	 * @param trLog
	 * @param timezone
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	public void editTrial(String analysisVersion, String conditionName, String confirmationStatus,String confirmationStatusString, String analysisStatus, String recordingType,String notes, String trLog, String timezone) throws InterruptedException {
		By confirmationStatusOption = By.xpath("//option[contains(text(), '" + confirmationStatus + "')]");
		By analysisStatusOption = By.xpath("//option[contains(text(), '" + analysisStatus + "')]");
		By recordingTypeOption = By.xpath("//option[contains(text(), '" + recordingType + "')]");
		elementUtils.doSendKeys(clickSession, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(clickSession, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(clickSession, Keys.ENTER);
		elementUtils.doSendKeys(clickSubject, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(clickSubject, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(clickSubject, Keys.ENTER);
		elementUtils.waitForElementPresent(trialAnalysisVersion);
		elementUtils.doSendKeys(trialAnalysisVersion, analysisVersion);
		elementUtils.waitForElementPresent(testDate);
		elementUtils.doSendKeys(testDate, "2021-10-14T02:05:23.514-04:00");
		elementUtils.waitForElementPresent(By.xpath("/html/body/div[1]/main/div/div/form/section/div[5]/div/input"));
		driver.findElement(By.xpath("/html/body/div[1]/main/div/div/form/section/div[5]/div/input")).sendKeys(Keys.ENTER);
		elementUtils.doSendKeys(clickTestDefinition, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(clickTestDefinition, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(clickTestDefinition, Keys.ENTER);
		elementUtils.waitForElementPresent(trialConditionName);
		elementUtils.doSendKeys(trialConditionName, conditionName);
		elementUtils.waitForElementPresent(stopTime);
		elementUtils.doSendKeys(stopTime, "2021-10-31T00:00:00.000-04:00");
		elementUtils.waitForElementPresent(By.xpath("/html/body/div[1]/main/div/div/form/section/div[8]/div/input"));
		driver.findElement(By.xpath("/html/body/div[1]/main/div/div/form/section/div[8]/div/input")).sendKeys(Keys.ENTER);
		elementUtils.waitForElementVisible(uploadedToMobilityExchange);
		elementUtils.doClick(uploadedToMobilityExchange);
		elementUtils.waitForElementPresent(confirmationStatusOption);
		elementUtils.doClick(confirmationStatusOption);
		elementUtils.waitForElementPresent(trialConfirmationStatusString);
		elementUtils.doSendKeys(trialConfirmationStatusString, confirmationStatusString);
		elementUtils.waitForElementPresent(analysisStatusOption);
		elementUtils.doClick(analysisStatusOption);
		elementUtils.waitForElementPresent(recordingTypeOption);
		elementUtils.doClick(recordingTypeOption);
		elementUtils.waitForElementPresent(trialNotes);
		elementUtils.doSendKeys(trialNotes, notes);
		elementUtils.waitForElementPresent(trialLog);
		elementUtils.doSendKeys(trialLog, trLog);
		elementUtils.waitForElementPresent(trialTimezone);
		elementUtils.doSendKeys(trialTimezone, timezone);
		elementUtils.doClick(submitButton);
	}
	
	/**
	 * This method is used to get edited Test Date
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getEditedAnalysisVersion() {
		elementUtils.waitForElementPresent(By.xpath("/html/body/div/main/div/div/form/section/div[4]/div"));
		return elementUtils.doGetText(By.xpath("/html/body/div/main/div/div/form/section/div[4]/div"));
	}
	
}
