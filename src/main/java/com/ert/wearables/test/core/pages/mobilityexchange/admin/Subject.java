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

public class Subject extends BaseSetup{

	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;
	AdminUtils adminUtils;
	
	// Locators
	private By subjectsSideBarButton = By.cssSelector("a[href='#subject']");

	// Subject Page Locators
	private By subjectsHeader = By.xpath("//h2[normalize-space()='Subjects']");
	
	// Subject property locators
	private By subjectsStudy = By.xpath("/html/body/div[1]/main/div/div/form/section/div[2]/div/input");
	private By subjectsSite = By.xpath("/html/body/div[1]/main/div/div/form/section/div[3]/div/input");
	private By subjectsPublicID = By.xpath("//label[contains(text(), \"Public ID\")]/following-sibling::input");
	private By subjectsFirstName = By.xpath("//label[contains(text(), \"First Name\")]/following-sibling::input");
	private By subjectsLastName = By.xpath("//label[contains(text(), \"Last Name\")]/following-sibling::input");
	private By subjectsHeight = By.xpath("//label[contains(text(), \"Height (cm)\")]/following-sibling::input");
	private By subjectsDateOfBirth = By.xpath("/html/body/div[1]/main/div/div/form/section/div[8]/div/input");
	private By subjectsNotes = By.xpath("/html/body/div/main/div/div/form/section/div[10]/textarea");
	private By subjectsDeleted = By.xpath("/html/body/div/main/div/div/form/section/div[12]/div/label");
	private By submitButton = By.xpath("//button[normalize-space()='Submit']");

	// Subject Table's Link Locators
	private By tableIdLink = By.xpath("//table//tbody//tr[1]//td[1]//a");
	private By tablePublicIdLink = By.xpath("//table//tbody//tr[1]//td[2]//a");
	private By tableSessionsLink = By.xpath("//table//tbody//tr[1]//td[3]//a");
	private By tableTrialsLink = By.xpath("//table//tbody//tr[1]//td[4]//a");
	private By tableStudyMembershipsLink = By.xpath("/html/body/div/main/div/div/div[3]/div/table/tbody/tr[1]/td[4]/div/a");
	private By tableDeviceAllocationGroupsLink = By.xpath("/html/body/div/main/div/div/div[3]/div/table/tbody/tr[1]/td[6]/div/a");
	
	// Subject Form's Locators
	private By siteFormSiteId = By.xpath("/html/body/div/main/div/div/form/section/div[1]/div/a");
	private By subjectFormPublicId = By.cssSelector("section > div:nth-child(5) > div");
	private By subjectFormStudyName = By.cssSelector("section > div:nth-child(2) > div");
	private By subjectFormDeleteButton = By.xpath("//button[normalize-space()='Delete']");
	private By subjectFormPageTitle = By.xpath("//h2[@class='es-page-title']");
	
	// WebTable Headers and Rows Locators
	private By tableHeaderPublicID = By.xpath("//table//thead//tr[1]//th[2]");
	private By tableRowsPublicID = By.xpath("//table//tbody//tr//td[2]");
	private By tableHeaderSessions = By.xpath("//table//thead//tr[1]//th[3]");
	private By tableRowsSessions = By.xpath("//table//tbody//tr//td[3]");
	private By tableHeaderTrials = By.xpath("//table//thead//tr[1]//th[4]");
	private By tableRowsTrials = By.xpath("//table//tbody//tr//td[4]");
	private By tableHeaderStudy = By.xpath("//table//thead//tr[1]//th[2]");
	private By tableRowsStudy = By.xpath("//table//tbody//tr//td[2]");
	private By tableHeaderSite = By.xpath("//table//thead//tr[1]//th[3]");
	private By tableRowsSite = By.xpath("//table//tbody//tr//td[3]");
	private By tableHeaderStudyMemberships = By.xpath("//table//thead//tr[1]//th[4]");
	private By tableRowsStudyMemberships = By.xpath("//table//tbody//tr//td[4]");
	private By tableHeaderDeviceAllocationGroups = By.xpath("//table//thead//tr[1]//th[6]");
	private By tableRowsDeviceAllocationGroups = By.xpath("//table//tbody//tr//td[6]");
	private By tableHeaderFirstName = By.xpath("//table//thead//tr[1]//th[8]");
	private By tableRowsFirstName = By.xpath("//table//tbody//tr//td[8]");
	private By tableHeaderLastName = By.xpath("//table//thead//tr[1]//th[9]");
	private By tableRowsLastName = By.xpath("//table//tbody//tr//td[9]");
	private By tableHeaderHeigh = By.xpath("//table//thead//tr[1]//th[10]");
	private By tableRowsHeigh = By.xpath("//table//tbody//tr//td[10]");
	private By tableHeaderDoB = By.xpath("//table//thead//tr[1]//th[12]");
	private By tableRowsDoB = By.xpath("//table//tbody//tr//td[12]");
	private By tableHeaderGender = By.xpath("//table//thead//tr[1]//th[13]");
	private By tableRowsGender = By.xpath("//table//tbody//tr//td[13]");
	private By tableHeaderNotes = By.xpath("//table//thead//tr[1]//th[14]");
	private By tableRowsNotes = By.xpath("//table//tbody//tr//td[14]");
	private By tableHeaderCreationDate = By.xpath("//table//thead//tr[1]//th[15]");
	private By tableRowsCreationDate = By.xpath("//table//tbody//tr//td[15]");
	private By tableHeaderDeleted = By.xpath("//table//thead//tr[1]//th[16]");
	private By tableRowsDeleted = By.xpath("//table//tbody//tr//td[16]");
	private By tableHeaderLastModified = By.xpath("//table//thead//tr[1]//th[17]");
	private By tableRowsLastModified = By.xpath("//table//tbody//tr//td[17]");
	
	
	public By getTableIdLink() {
		return tableIdLink;
	}

	public By getTablePublicIdLink() {
		return tablePublicIdLink;
	}

	public By getTableSessionsLink() {
		return tableSessionsLink;
	}

	public By getTableTrialsLink() {
		return tableTrialsLink;
	}

	public By getTableStudyMembershipsLink() {
		return tableStudyMembershipsLink;
	}

	public By getTableDeviceAllocationGroupsLink() {
		return tableDeviceAllocationGroupsLink;
	}

	public By getTableHeaderPublicID() {
		return tableHeaderPublicID;
	}

	public By getTableRowsPublicID() {
		return tableRowsPublicID;
	}

	public By getTableHeaderSessions() {
		return tableHeaderSessions;
	}

	public By getTableRowsSessions() {
		return tableRowsSessions;
	}

	public By getTableHeaderTrials() {
		return tableHeaderTrials;
	}

	public By getTableRowsTrials() {
		return tableRowsTrials;
	}

	public By getTableHeaderStudy() {
		return tableHeaderStudy;
	}

	public By getTableRowsStudy() {
		return tableRowsStudy;
	}

	public By getTableHeaderSite() {
		return tableHeaderSite;
	}

	public By getTableRowsSite() {
		return tableRowsSite;
	}

	public By getTableHeaderStudyMemberships() {
		return tableHeaderStudyMemberships;
	}

	public By getTableRowsStudyMemberships() {
		return tableRowsStudyMemberships;
	}

	public By getTableHeaderDeviceAllocationGroups() {
		return tableHeaderDeviceAllocationGroups;
	}

	public By getTableRowsDeviceAllocationGroups() {
		return tableRowsDeviceAllocationGroups;
	}

	public By getTableHeaderFirstName() {
		return tableHeaderFirstName;
	}

	public By getTableRowsFirstName() {
		return tableRowsFirstName;
	}

	public By getTableHeaderLastName() {
		return tableHeaderLastName;
	}

	public By getTableRowsLastName() {
		return tableRowsLastName;
	}

	public By getTableHeaderHeigh() {
		return tableHeaderHeigh;
	}

	public By getTableRowsHeigh() {
		return tableRowsHeigh;
	}

	public By getTableHeaderDoB() {
		return tableHeaderDoB;
	}

	public By getTableRowsDoB() {
		return tableRowsDoB;
	}

	public By getTableHeaderGender() {
		return tableHeaderGender;
	}

	public By getTableRowsGender() {
		return tableRowsGender;
	}

	public By getTableHeaderNotes() {
		return tableHeaderNotes;
	}

	public By getTableRowsNotes() {
		return tableRowsNotes;
	}

	public By getTableHeaderCreationDate() {
		return tableHeaderCreationDate;
	}

	public By getTableRowsCreationDate() {
		return tableRowsCreationDate;
	}

	public By getTableHeaderDeleted() {
		return tableHeaderDeleted;
	}

	public By getTableRowsDeleted() {
		return tableRowsDeleted;
	}

	public By getTableHeaderLastModified() {
		return tableHeaderLastModified;
	}

	public By getTableRowsLastModified() {
		return tableRowsLastModified;
	}

	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public Subject(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}

	/**
	 * This method is used to click Subject side bar button
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickSubjectsSideBarButton() {
		elementUtils.waitForElementPresent(subjectsSideBarButton);
		elementUtils.doClick(subjectsSideBarButton);
	}
	
	/**
	 * This method provides to get subjects page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSubjectsPageTitle() {
		elementUtils.waitForTitlePresent(AppConstants.SUBJECTS_PAGE_TITLE);
		return elementUtils.doGetPageTitle();
	}

	/**
	 * This method retrieves the header from the subjects page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSubjectsHeader() {
		elementUtils.waitForElementPresent(subjectsHeader);
		return elementUtils.doGetText(subjectsHeader);
	}

	/**
	 * This method returns subject count from subjects page
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getSubjectCount() {
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		elementUtils.waitForElementPresent(tableRowsXpath);
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int subjectCount = tableRowList.size();
		return subjectCount;
	}
	
	/**
	 * This method create new site
	 * 
	 * @param name
	 * @param addLineFirst
	 * @param addLineSecond
	 * @param city
	 * @param state
	 * @param guid
	 * @param country
	 * @param phone
	 * @param zip
	 * @param geolocation
	 * @param notes
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	public void createSubject(String publicID, String firstName, String lastName, String genderParam, String notes) {
		By genderOption = By.xpath("//option[contains(text(), '" + genderParam + "')]");
		elementUtils.waitForElementPresent(subjectsStudy);
		elementUtils.doClick(subjectsStudy);
		elementUtils.doSendKeys(subjectsStudy, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(subjectsStudy, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(subjectsStudy, Keys.ENTER);
		elementUtils.waitForElementPresent(subjectsSite);
		elementUtils.doClick(subjectsSite);
		elementUtils.doSendKeys(subjectsSite, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(subjectsSite, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(subjectsSite, Keys.ENTER);
		elementUtils.waitForElementPresent(subjectsPublicID);
		elementUtils.doSendKeys(subjectsPublicID, publicID);
		elementUtils.waitForElementPresent(subjectsFirstName);
		elementUtils.doSendKeys(subjectsFirstName, firstName);
		elementUtils.waitForElementPresent(subjectsLastName);
		elementUtils.doSendKeys(subjectsLastName, lastName);
		elementUtils.waitForElementPresent(subjectsHeight);
		elementUtils.doSendKeys(subjectsHeight, "180");
		elementUtils.waitForElementPresent(subjectsDateOfBirth);
		elementUtils.doSendKeys(subjectsDateOfBirth, "1999-01-01");
		driver.findElement(By.xpath("//div[@class='es-date-input']/input")).sendKeys(Keys.ENTER);
		elementUtils.waitForElementPresent(genderOption);
		elementUtils.doClick(genderOption);
		elementUtils.waitForElementPresent(subjectsNotes);
		elementUtils.doSendKeys(subjectsNotes, notes);
		elementUtils.waitForElementVisible(submitButton);
		elementUtils.doClick(submitButton);
	}
	
	/**
	 * This method is used to get Last Created Subject First Name from Subject Table
	 * 
	 * @return
	 * @author yavuz.ozturk
	 * @throws InterruptedException 
	 */
	public String getLastCreatedSubjectNameFromSubjectTable() {
		String beforeXpath = "//table//tbody//tr[";
		String afterXpath = "]//td[8]";
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int lastRowIndex = tableRowList.size();
		WebElement lastCreatedUser = elementUtils.getElement(By.xpath(beforeXpath + lastRowIndex + afterXpath));
		return lastCreatedUser.getText();
	}
	
	/**
	 * This method is used to get subject table's subject Id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSubjectTableSubjectId() {
		elementUtils.waitForElementPresent(tableIdLink);
		String subjectTableSubjectId = elementUtils.doGetText(tableIdLink);
		return subjectTableSubjectId;
	}
	
	/**
	 * This method is used to get subjects form's subject Id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSubjectFormSubjectId() {
		elementUtils.waitForElementPresent(siteFormSiteId);
		return elementUtils.doGetText(siteFormSiteId);
	}
	
	/**
	 * This method is used to click subject table's Public ID link
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickSubjectTablePublicIdLink() {
		elementUtils.waitForElementPresent(tablePublicIdLink);
		elementUtils.doClick(tablePublicIdLink);
	}
	
	/**
	 * This method is used to get subject table's Public ID
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSujectTablePuclicId() {
		elementUtils.waitForElementPresent(tablePublicIdLink);
		String subjectTablePublicId = elementUtils.doGetText(tablePublicIdLink);
		return subjectTablePublicId;
	}
	
	/**
	 * This method is used to get subject form's Public ID
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSubjectFormPublicID() {
		elementUtils.waitForElementPresent(subjectFormPublicId);
		return elementUtils.doGetText(By.cssSelector("section > div:nth-child(5) > div"));
	}
	
	/**
	 * This method is used to get subject form's Study Name
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSubjectFormStudyName() {
		elementUtils.waitForElementPresent(subjectFormStudyName);
		return elementUtils.doGetText(By.cssSelector("section > div:nth-child(2) > div"));
	}
	
	/**
	 * This method is used to get Last Created Public Id from Subject Table
	 * 
	 * @return
	 * @author yavuz.ozturk
	 * @throws InterruptedException 
	 */
	public String getLastCreatedSubjectPublicIdFromSubjectTable() throws InterruptedException {
		String beforeXpath = "//table//tbody//tr[";
		String afterXpath = "]//td[2]";
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int lastRowIndex = tableRowList.size();
		WebElement lastCreatedUser = elementUtils.getElement(By.xpath(beforeXpath + lastRowIndex + afterXpath));
		return lastCreatedUser.getText();
	}
	
	/**
	 * This method is used to get Last Created Site ID from Site Table
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getLastCreatedSubjectIdFromSubjectTable() {
		String beforeXpath = "//table//tbody//tr[";
		String afterXpath = "]//td[1]";
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int lastRowIndex = tableRowList.size();
		WebElement lastCreatedUser = elementUtils.getElement(By.xpath(beforeXpath + lastRowIndex + afterXpath));
		return lastCreatedUser.getText();
	}
	
	/**
	 * This method is used to delete last created Subject from Subject Table Via ID Link
	 * 
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	public void deleteLastCreatedSubjectViaIdLink() throws InterruptedException {
		elementUtils.doClick(By.linkText(getLastCreatedSubjectIdFromSubjectTable()));
		elementUtils.doClick(subjectFormDeleteButton);
		TimeUnit.SECONDS.sleep(3);
		adminUtils.clickAlertOk();
	}
	
	/**
	 * This method is used to edit subject
	 * 
	 * @param publicID
	 * @param firstName
	 * @param lastName
	 * @param genderParam
	 * @param notes
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	public void editSubject(String publicID, String firstName, String lastName, String genderParam, String notes) throws InterruptedException {
		By genderOption = By.xpath("//option[contains(text(), '" + genderParam + "')]");
		elementUtils.waitForElementPresent(subjectsStudy);
		elementUtils.doSendKeys(subjectsStudy, Keys.DELETE);
		elementUtils.doClick(subjectsStudy);
		elementUtils.doSendKeys(subjectsStudy, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(subjectsStudy, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(subjectsStudy, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(subjectsStudy, Keys.ENTER);
		elementUtils.waitForElementPresent(subjectsSite);
		elementUtils.doSendKeys(subjectsSite, Keys.DELETE);
		elementUtils.doClick(subjectsSite);
		elementUtils.doSendKeys(subjectsSite, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(subjectsSite, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(subjectsSite, Keys.ARROW_DOWN);
		elementUtils.doSendKeys(subjectsSite, Keys.ENTER);
		elementUtils.waitForElementPresent(subjectsPublicID);
		elementUtils.doSendKeys(subjectsPublicID, publicID);
		elementUtils.waitForElementPresent(subjectsFirstName);
		elementUtils.doSendKeys(subjectsFirstName, firstName);
		elementUtils.waitForElementPresent(subjectsLastName);
		elementUtils.doSendKeys(subjectsLastName, lastName);
		elementUtils.waitForElementPresent(subjectsHeight);
		elementUtils.doSendKeys(subjectsHeight, "175");
		elementUtils.waitForElementPresent(subjectsDateOfBirth);
		elementUtils.doSendKeys(subjectsDateOfBirth, "1990-02-02");
		driver.findElement(By.xpath("//div[@class='es-date-input']/input")).sendKeys(Keys.ENTER);
		elementUtils.waitForElementPresent(genderOption);
		elementUtils.doClick(genderOption);
		elementUtils.waitForElementPresent(subjectsNotes);
		elementUtils.doSendKeys(subjectsNotes, notes);
		elementUtils.waitForElementVisible(submitButton);
		elementUtils.doClick(submitButton);
	}
	
	/**
	 * This method is used to get edited subject Public ID
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getEditedSubjectPublicId() {
		elementUtils.waitForElementPresent(By.xpath("/html/body/div/main/div/div/form/section/div[5]/div"));
		return elementUtils.doGetText(By.xpath("/html/body/div/main/div/div/form/section/div[5]/div"));
	}

	/**
	 * This method is used to get subject form page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getSubjectFormPageTitle() {
		elementUtils.waitForElementPresent(subjectFormPageTitle);
		return elementUtils.doGetText(subjectFormPageTitle);
	}
	
	/**
	 * This method is used to get subject table's sessions count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getSubjectTableSessionsCount() {
		elementUtils.waitForElementPresent(tableSessionsLink);
		String sessions = elementUtils.doGetText(tableSessionsLink);
		int sessionsCount = Integer.parseInt(sessions.substring(0, 1));
		elementUtils.doClick(tableSessionsLink);
		return sessionsCount;
	}
	
	/**
	 * This method is used to get subject table's trials count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getSubjectTableTrialsCount() {
		elementUtils.waitForElementPresent(tableTrialsLink);
		String trials = elementUtils.doGetText(tableTrialsLink);
		int trialsCount = Integer.parseInt(trials.substring(0, 1));
		elementUtils.doClick(tableTrialsLink);
		return trialsCount;
	}
	
	/**
	 * This method is used to get subject table's Study Memberships count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getSubjectTableStudyMembershipsCount() {
		elementUtils.waitForElementPresent(tableStudyMembershipsLink);
		String studyMemberships = elementUtils.doGetText(tableStudyMembershipsLink);
		int studyMembershipsCount = Integer.parseInt(studyMemberships.substring(0, 1));
		elementUtils.doClick(tableStudyMembershipsLink);
		return studyMembershipsCount;
	}
	
	/**
	 * This method is used to get subject table's Device Allocation Groups count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getSubjectTableDeviceAllocationGroupsCount() {
		elementUtils.waitForElementPresent(tableDeviceAllocationGroupsLink);
		String deviceAllocationGroups = elementUtils.doGetText(tableDeviceAllocationGroupsLink);
		int deviceAllocationGroupsCount = Integer.parseInt(deviceAllocationGroups.substring(0, 1));
		elementUtils.doClick(tableDeviceAllocationGroupsLink);
		return deviceAllocationGroupsCount;
	}
	
}
