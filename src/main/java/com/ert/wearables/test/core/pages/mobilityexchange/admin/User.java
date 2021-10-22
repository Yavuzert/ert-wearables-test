package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.utilities.AdminUtils;
import com.ert.wearables.test.core.utilities.ElementUtils;
import com.ert.wearables.test.core.utilities.JavaScriptUtil;

public class User extends BaseSetup{
	
	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;
	AdminUtils adminUtils;
	
	Logger log = Logger.getLogger(Study.class);
	
	// User Page Locators
	private By userHeader = By.xpath("//h2[normalize-space()='Users']");
	
	// Locators
	private By userSideBarButton = By.xpath("//a[@href='#user']//span[@class='es-icon-user-management-study-permission es-icon']");
	
	// User property locators
	private By userUserName = By.xpath("//label[contains(text(), \"User name (Email)\")]/following-sibling::input");
	private By userFirstName = By.xpath("//label[contains(text(), \"First name\")]/following-sibling::input");
	private By userLastName = By.xpath("//label[contains(text(), \"Last name\")]/following-sibling::input");
	private By userDateOfTraining = By.xpath("/html/body/div[1]/main/div/div/form/section/div[5]/div/input");
	private By submitButton = By.xpath("//button[normalize-space()='Submit']");
	
	// User Table's Link Locators
	private By tableIdLink = By.xpath("//table//tbody//tr[4]//td[1]//a");
	private By tableUserNameAndEmailLink = By.xpath("//table//tbody//tr[4]//td[2]//a");
	private By tableStudyMembershipsLink = By.xpath("//table//tbody//tr[1]//td[3]//a");
	
	// User Form's Locators
	private By userFormId = By.xpath("/html/body/div/main/div/div/form/section/div[1]/div/a");
	private By userFormUserNameAndEmail = By.cssSelector("section > div:nth-child(5) > div");
	private By userFormDeleteButton = By.xpath("//button[normalize-space()='Delete']");
	private By userFormPageTitle = By.xpath("//h2[@class='es-page-title']");
	
	// WebTable Headers and Rows Locators
	private By tableHeaderUserNameAndEmail = By.xpath("//table//thead//tr[1]//th[2]");
	private By tableRowsUserNameAndEmail = By.xpath("//table//tbody//tr//td[2]");
	private By tableHeaderStudyMemberships = By.xpath("//table//thead//tr[1]//th[3]");
	private By tableRowsStudyMemberships = By.xpath("//table//tbody//tr//td[3]");
	private By tableHeaderGroups = By.xpath("//table//thead//tr[1]//th[4]");
	private By tableRowsGroups = By.xpath("//table//tbody//tr//td[4]");
	private By tableHeaderFirstName = By.xpath("//table//thead//tr[1]//th[3]");
	private By tableRowsFirstName = By.xpath("//table//tbody//tr//td[3]");
	private By tableHeaderLastName = By.xpath("//table//thead//tr[1]//th[4]");
	private By tableRowsLastName = By.xpath("//table//tbody//tr//td[4]");
	private By tableHeaderDateOfTraining = By.xpath("//table//thead//tr[1]//th[6]");
	private By tableRowsDateOfTraining = By.xpath("//table//tbody//tr//td[6]");
	private By tableHeaderCreationDate = By.xpath("//table//thead//tr[1]//th[8]");
	private By tableRowsCreationDate = By.xpath("//table//tbody//tr//td[8]");
	private By tableHeaderLastLogin = By.xpath("//table//thead//tr[1]//th[9]");
	private By tableRowsLastLogin = By.xpath("//table//tbody//tr//td[9]");
	private By tableHeaderAccountDisabled = By.xpath("//table//thead//tr[1]//th[10]");
	private By tableRowsAccountDisabled = By.xpath("//table//tbody//tr//td[10]");
	private By tableHeaderDeleted = By.xpath("//table//thead//tr[1]//th[11]");
	private By tableRowsDeleted = By.xpath("//table//tbody//tr//td[11]");
	private By tableHeaderLastModified = By.xpath("//table//thead//tr[1]//th[12]");
	private By tableRowsLastModified = By.xpath("//table//tbody//tr//td[12]");
	private By tableHeaderPasswordLastModified = By.xpath("//table//thead//tr[1]//th[13]");
	private By tableRowsPasswordLastModified = By.xpath("//table//tbody//tr//td[13]");
	private By tableHeaderMustChangePassword = By.xpath("//table//thead//tr[1]//th[14]");
	private By tableRowsMustChangePassword = By.xpath("//table//tbody//tr//td[14]");
	private By tableHeaderSystem = By.xpath("//table//thead//tr[1]//th[15]");
	private By tableRowsSystem = By.xpath("//table//tbody//tr//td[15]");
	
	public By getTableHeaderUserNameAndEmail() {
		return tableHeaderUserNameAndEmail;
	}

	public void setTableHeaderUserNameAndEmail(By tableHeaderUserNameAndEmail) {
		this.tableHeaderUserNameAndEmail = tableHeaderUserNameAndEmail;
	}

	public By getTableRowsUserNameAndEmail() {
		return tableRowsUserNameAndEmail;
	}

	public void setTableRowsUserNameAndEmail(By tableRowsUserNameAndEmail) {
		this.tableRowsUserNameAndEmail = tableRowsUserNameAndEmail;
	}

	public By getTableHeaderStudyMemberships() {
		return tableHeaderStudyMemberships;
	}

	public void setTableHeaderStudyMemberships(By tableHeaderStudyMemberships) {
		this.tableHeaderStudyMemberships = tableHeaderStudyMemberships;
	}

	public By getTableRowsStudyMemberships() {
		return tableRowsStudyMemberships;
	}

	public void setTableRowsStudyMemberships(By tableRowsStudyMemberships) {
		this.tableRowsStudyMemberships = tableRowsStudyMemberships;
	}

	public By getTableHeaderGroups() {
		return tableHeaderGroups;
	}

	public void setTableHeaderGroups(By tableHeaderGroups) {
		this.tableHeaderGroups = tableHeaderGroups;
	}

	public By getTableRowsGroups() {
		return tableRowsGroups;
	}

	public void setTableRowsGroups(By tableRowsGroups) {
		this.tableRowsGroups = tableRowsGroups;
	}

	public By getTableHeaderFirstName() {
		return tableHeaderFirstName;
	}

	public void setTableHeaderFirstName(By tableHeaderFirstName) {
		this.tableHeaderFirstName = tableHeaderFirstName;
	}

	public By getTableRowsFirstName() {
		return tableRowsFirstName;
	}

	public void setTableRowsFirstName(By tableRowsFirstName) {
		this.tableRowsFirstName = tableRowsFirstName;
	}

	public By getTableHeaderLastName() {
		return tableHeaderLastName;
	}

	public void setTableHeaderLastName(By tableHeaderLastName) {
		this.tableHeaderLastName = tableHeaderLastName;
	}

	public By getTableRowsLastName() {
		return tableRowsLastName;
	}

	public void setTableRowsLastName(By tableRowsLastName) {
		this.tableRowsLastName = tableRowsLastName;
	}

	public By getTableHeaderDateOfTraining() {
		return tableHeaderDateOfTraining;
	}

	public void setTableHeaderDateOfTraining(By tableHeaderDateOfTraining) {
		this.tableHeaderDateOfTraining = tableHeaderDateOfTraining;
	}

	public By getTableRowsDateOfTraining() {
		return tableRowsDateOfTraining;
	}

	public void setTableRowsDateOfTraining(By tableRowsDateOfTraining) {
		this.tableRowsDateOfTraining = tableRowsDateOfTraining;
	}

	public By getTableHeaderCreationDate() {
		return tableHeaderCreationDate;
	}

	public void setTableHeaderCreationDate(By tableHeaderCreationDate) {
		this.tableHeaderCreationDate = tableHeaderCreationDate;
	}

	public By getTableRowsCreationDate() {
		return tableRowsCreationDate;
	}

	public void setTableRowsCreationDate(By tableRowsCreationDate) {
		this.tableRowsCreationDate = tableRowsCreationDate;
	}

	public By getTableHeaderLastLogin() {
		return tableHeaderLastLogin;
	}

	public void setTableHeaderLastLogin(By tableHeaderLastLogin) {
		this.tableHeaderLastLogin = tableHeaderLastLogin;
	}

	public By getTableRowsLastLogin() {
		return tableRowsLastLogin;
	}

	public void setTableRowsLastLogin(By tableRowsLastLogin) {
		this.tableRowsLastLogin = tableRowsLastLogin;
	}

	public By getTableHeaderAccountDisabled() {
		return tableHeaderAccountDisabled;
	}

	public void setTableHeaderAccountDisabled(By tableHeaderAccountDisabled) {
		this.tableHeaderAccountDisabled = tableHeaderAccountDisabled;
	}

	public By getTableRowsAccountDisabled() {
		return tableRowsAccountDisabled;
	}

	public void setTableRowsAccountDisabled(By tableRowsAccountDisabled) {
		this.tableRowsAccountDisabled = tableRowsAccountDisabled;
	}

	public By getTableHeaderDeleted() {
		return tableHeaderDeleted;
	}

	public void setTableHeaderDeleted(By tableHeaderDeleted) {
		this.tableHeaderDeleted = tableHeaderDeleted;
	}

	public By getTableRowsDeleted() {
		return tableRowsDeleted;
	}

	public void setTableRowsDeleted(By tableRowsDeleted) {
		this.tableRowsDeleted = tableRowsDeleted;
	}

	public By getTableHeaderLastModified() {
		return tableHeaderLastModified;
	}

	public void setTableHeaderLastModified(By tableHeaderLastModified) {
		this.tableHeaderLastModified = tableHeaderLastModified;
	}

	public By getTableRowsLastModified() {
		return tableRowsLastModified;
	}

	public void setTableRowsLastModified(By tableRowsLastModified) {
		this.tableRowsLastModified = tableRowsLastModified;
	}

	public By getTableHeaderPasswordLastModified() {
		return tableHeaderPasswordLastModified;
	}

	public void setTableHeaderPasswordLastModified(By tableHeaderPasswordLastModified) {
		this.tableHeaderPasswordLastModified = tableHeaderPasswordLastModified;
	}

	public By getTableRowsPasswordLastModified() {
		return tableRowsPasswordLastModified;
	}

	public void setTableRowsPasswordLastModified(By tableRowsPasswordLastModified) {
		this.tableRowsPasswordLastModified = tableRowsPasswordLastModified;
	}

	public By getTableHeaderMustChangePassword() {
		return tableHeaderMustChangePassword;
	}

	public void setTableHeaderMustChangePassword(By tableHeaderMustChangePassword) {
		this.tableHeaderMustChangePassword = tableHeaderMustChangePassword;
	}

	public By getTableRowsMustChangePassword() {
		return tableRowsMustChangePassword;
	}

	public void setTableRowsMustChangePassword(By tableRowsMustChangePassword) {
		this.tableRowsMustChangePassword = tableRowsMustChangePassword;
	}

	public By getTableHeaderSystem() {
		return tableHeaderSystem;
	}

	public void setTableHeaderSystem(By tableHeaderSystem) {
		this.tableHeaderSystem = tableHeaderSystem;
	}

	public By getTableRowsSystem() {
		return tableRowsSystem;
	}

	public void setTableRowsSystem(By tableRowsSystem) {
		this.tableRowsSystem = tableRowsSystem;
	}

	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public User(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}

	/**
	 * This method is used to click Users side bar button
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickUsersSideBarButton() {
		elementUtils.waitForElementPresent(userSideBarButton);
		elementUtils.doClick(userSideBarButton);
	}
	
	/**
	 * This method create new user on Users component
	 * 
	 * @param userName
	 * @param firstName
	 * @param lastName
	 * @param groups
	 * @param mustChange
	 * @author yavuz.ozturk
	 */
	public void createUser(String userName, String firstName, String lastName, String groups, String mustChangePassword) {
		By groupOption = By.xpath("//option[contains(text(), '" + groups + "')]");
		By mustChangePasswordOption = By.xpath("//option[contains(text(), '" + mustChangePassword + "')]");
		elementUtils.waitForElementPresent(userUserName);
		elementUtils.doSendKeys(userUserName, userName);
		elementUtils.waitForElementPresent(userFirstName);
		elementUtils.doSendKeys(userFirstName, firstName);
		elementUtils.waitForElementPresent(userLastName);
		elementUtils.doSendKeys(userLastName, lastName);
		elementUtils.waitForElementPresent(userDateOfTraining);
		elementUtils.doSendKeys(userDateOfTraining, "12/12/2021");
		driver.findElement(By.xpath("//div[@class='es-date-input']/input")).sendKeys(Keys.ENTER);
		elementUtils.waitForElementVisible(groupOption);
		elementUtils.doClick(groupOption);
		elementUtils.waitForElementPresent(mustChangePasswordOption);
		elementUtils.doClick(mustChangePasswordOption);
		elementUtils.waitForElementVisible(submitButton);
		elementUtils.doClick(submitButton);
	}
	
	/**
	 * This method is used to get Last Created User from User Table
	 * 
	 * @return
	 * @author yavuz.ozturk
	 * @throws InterruptedException 
	 */
	public String getLastCreatedUserNameOrEmailFromUserTable() {
		String beforeXpath = "//table//tbody//tr[";
		String afterXpath = "]//td[2]";
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int lastRowIndex = tableRowList.size();
		WebElement lastCreatedUser = elementUtils.getElement(By.xpath(beforeXpath + lastRowIndex + afterXpath));
		return lastCreatedUser.getText();
	}
	
	/**
	 * This method is used to delete last created User from User Table Via ID Link
	 * 
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	public void deleteLastCreatedUserViaIdLink() throws InterruptedException {
		elementUtils.doClick(By.linkText(getLastCreatedUserNameOrEmailFromUserTable()));
		elementUtils.doClick(userFormDeleteButton);
		TimeUnit.SECONDS.sleep(10);
		adminUtils.clickAlertOk();
	}
	
	/**
	 * This method is used to delete last created User from User Table Via User name (Email) Link
	 * 
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	public void deleteLastCreatedUserViaUserNameAndEmailLink() throws InterruptedException {
		elementUtils.doClick(By.linkText(getLastCreatedUserNameOrEmailFromUserTable()));
		elementUtils.doClick(userFormDeleteButton);
		TimeUnit.SECONDS.sleep(3);
		adminUtils.clickAlertOk();
	}
	
	/**
	 * This method is used to click user table's id link
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickUserTableIdLink() {
		elementUtils.waitForElementPresent(tableIdLink);
		elementUtils.doClick(tableIdLink);
	}
	
	/**
	 * This method is used to get user table's Id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getUserTableId() {
		elementUtils.waitForElementPresent(tableIdLink);
		String userTableUserId = elementUtils.doGetText(tableIdLink);
		return userTableUserId;
	}
	
	/**
	 * This method is used to get User form's user Id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getUserFormId() {
		elementUtils.waitForElementPresent(userFormId);
		return elementUtils.doGetText(userFormId);
	}
	
	/**
	 * This method is used to get User table's User name (email)
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getUserTableUserNameAndEmail() {
		elementUtils.waitForElementPresent(tableUserNameAndEmailLink);
		String trialTableTestDate = elementUtils.doGetText(tableUserNameAndEmailLink);
		return trialTableTestDate;
	}
	
	/**
	 * This method is used to click User table's User name (email) link
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickUserTableUserNameAndEmail() {
		elementUtils.waitForElementPresent(tableUserNameAndEmailLink);
		elementUtils.doClick(tableUserNameAndEmailLink);
	}
	
	/**
	 * This method is used to get User form's User name (email)
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getUserFormUserNameAndEmail() {
		elementUtils.waitForElementPresent(userFormUserNameAndEmail);
		return elementUtils.doGetText(By.cssSelector("section > div:nth-child(2) > div"));
	}
	
	/**
	 * This method is used to edit User
	 * 
	 * @param userName
	 * @param firstName
	 * @param lastName
	 * @param groups
	 * @param mustChange
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	public void editUser(String userName, String firstName, String lastName, String groups, String mustChange) throws InterruptedException {
		By mustChangePasswordOption = By.xpath("//option[contains(text(), '" + mustChange + "')]");
		By groupOption = By.xpath("//option[contains(text(), '" + groups + "')]");
		elementUtils.waitForElementPresent(userUserName);
		elementUtils.doSendKeys(userUserName, userName);
		elementUtils.waitForElementPresent(userFirstName);
		elementUtils.doSendKeys(userFirstName, firstName);
		elementUtils.waitForElementPresent(userLastName);
		elementUtils.doSendKeys(userLastName, lastName);
		elementUtils.waitForElementPresent(userDateOfTraining);
		elementUtils.doSendKeys(userDateOfTraining, "12/12/2021");
		driver.findElement(By.xpath("//div[@class='es-date-input']/input")).sendKeys(Keys.ENTER);
		elementUtils.waitForElementPresent(mustChangePasswordOption);
		elementUtils.doClick(mustChangePasswordOption);
		elementUtils.waitForElementVisible(groupOption);
		elementUtils.doClick(groupOption);
		elementUtils.waitForElementVisible(submitButton);
		elementUtils.doClick(submitButton);
	}
	
	/**
	 * This method is used to get edited User name and Email
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getEditedUserNameandEmail() {
		elementUtils.waitForElementPresent(By.cssSelector("div:nth-child(2) > div:nth-child(2)"));
		return elementUtils.doGetText(By.cssSelector("div:nth-child(2) > div:nth-child(2)"));

	}
	
	/**
	 * This method is used to get User form page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getUserFormPageTitle() {
		elementUtils.waitForElementPresent(userFormPageTitle);
		return elementUtils.doGetText(userFormPageTitle);
	}
	
	/**
	 * This method is used to get user table's study memberships count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getUserTableMembershipsCount() {
		elementUtils.waitForElementPresent(tableStudyMembershipsLink);
		String studyMemberships = elementUtils.doGetText(tableStudyMembershipsLink);
		int userMembershipsCount = Integer.parseInt(studyMemberships.substring(0, studyMemberships.indexOf(" ")));
		elementUtils.doClick(tableStudyMembershipsLink);
		return userMembershipsCount;
	}
	
}
