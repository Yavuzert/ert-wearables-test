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

public class Group extends BaseSetup{
	
	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;
	AdminUtils adminUtils;
	
	Logger log = Logger.getLogger(Study.class);
	
	// Group Page Locators
	private By userHeader = By.xpath("//h2[normalize-space()='Users']");
	
	// Locators
	private By groupSideBarButton = By.xpath("//a[@href='#group']//span[@class='es-icon-portal-administration es-icon']");
	
	// Group property locators
	private By groupName = By.xpath("//label[contains(text(), \"Name\")]/following-sibling::input");
	private By submitButton = By.xpath("//button[normalize-space()='Submit']");
	
	// User Table's Link Locators
	private By tableIdLink = By.xpath("//table//tbody//tr[3]//td[1]//a");
	private By tableNameLink = By.xpath("//table//tbody//tr[6]//td[2]//a");
	
	// User Form's Locators
	private By groupFormId = By.xpath("/html/body/div/main/div/div/form/section/div[1]/div/a");
	private By groupFormName = By.cssSelector("section > div:nth-child(2) > div");
	private By groupFormDeleteButton = By.xpath("//button[normalize-space()='Delete']");
	private By groupFormPageTitle = By.xpath("//h2[@class='es-page-title']");
	
	// WebTable Headers and Rows Locators
	private By tableHeaderName = By.xpath("//table//thead//tr[1]//th[2]");
	private By tableRowsName = By.xpath("//table//tbody//tr//td[2]");
	private By tableHeaderMemberUsers = By.xpath("//table//thead//tr[1]//th[3]");
	private By tableRowsMemberUsers = By.xpath("//table//tbody//tr//td[3]");
	private By tableHeaderCreationDate = By.xpath("//table//thead//tr[1]//th[4]");
	private By tableRowsCreationDate = By.xpath("//table//tbody//tr//td[4]");
	private By tableHeaderDeleted = By.xpath("//table//thead//tr[1]//th[5]");
	private By tableRowsDeleted = By.xpath("//table//tbody//tr//td[5]");
	private By tableHeaderLastModified = By.xpath("//table//thead//tr[1]//th[6]");
	private By tableRowsLastModified = By.xpath("//table//tbody//tr//td[6]");
	
	public By getTableHeaderName() {
		return tableHeaderName;
	}

	public void setTableHeaderName(By tableHeaderName) {
		this.tableHeaderName = tableHeaderName;
	}

	public By getTableRowsName() {
		return tableRowsName;
	}

	public void setTableRowsName(By tableRowsName) {
		this.tableRowsName = tableRowsName;
	}

	public By getTableHeaderMemberUsers() {
		return tableHeaderMemberUsers;
	}

	public void setTableHeaderMemberUsers(By tableHeaderMemberUsers) {
		this.tableHeaderMemberUsers = tableHeaderMemberUsers;
	}

	public By getTableRowsMemberUsers() {
		return tableRowsMemberUsers;
	}

	public void setTableRowsMemberUsers(By tableRowsMemberUsers) {
		this.tableRowsMemberUsers = tableRowsMemberUsers;
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

	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public Group(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}

	/**
	 * This method is used to click Groups side bar button
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickGroupsSideBarButton() {
		elementUtils.waitForElementPresent(groupSideBarButton);
		elementUtils.doClick(groupSideBarButton);
	}
	
	/**
	 * This method create new group on Groups component
	 * 
	 * @param name
	 * @param memberUsers
	 * @author yavuz.ozturk
	 */
	public void createUser(String name, String memberUsers) {
		By memberUsersOption = By.xpath("//option[contains(text(), '" + memberUsers + "')]");
		elementUtils.waitForElementPresent(groupName);
		elementUtils.doSendKeys(groupName, name);
		elementUtils.waitForElementPresent(memberUsersOption);
		elementUtils.doClick(memberUsersOption);
		elementUtils.waitForElementVisible(submitButton);
		elementUtils.doClick(submitButton);
	}

	/**
	 * This method is used to get Last Created Group from Group Table
	 * 
	 * @return
	 * @author yavuz.ozturk
	 * @throws InterruptedException 
	 */
	public String getLastCreatedGroupFromGroupTable() {
		String beforeXpath = "//table//tbody//tr[";
		String afterXpath = "]//td[2]";
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int lastRowIndex = tableRowList.size();
		WebElement lastCreatedUser = elementUtils.getElement(By.xpath(beforeXpath + lastRowIndex + afterXpath));
		return lastCreatedUser.getText();
	}
	
	/**
	 * This method is used to delete last created Group from Group Table Via ID Link
	 * 
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	public void deleteLastCreatedGroupViaIdLink() throws InterruptedException {
		elementUtils.doClick(By.linkText(getLastCreatedGroupFromGroupTable()));
		elementUtils.doClick(groupFormDeleteButton);
		TimeUnit.SECONDS.sleep(10);
		adminUtils.clickAlertOk();
	}
	
	/**
	 * This method is used to delete last created Group from Group Table Via Name Link
	 * 
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	public void deleteLastCreatedGroupViaNameLink() throws InterruptedException {
		elementUtils.doClick(By.linkText(getLastCreatedGroupFromGroupTable()));
		elementUtils.doClick(groupFormDeleteButton);
		TimeUnit.SECONDS.sleep(3);
		adminUtils.clickAlertOk();
	}
	
	/**
	 * This method is used to click group table's id link
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickGroupTableIdLink() {
		elementUtils.waitForElementPresent(tableIdLink);
		elementUtils.doClick(tableIdLink);
	}
	
	/**
	 * This method is used to get group table's Id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getGroupTableId() {
		elementUtils.waitForElementPresent(tableIdLink);
		String groupTableUserId = elementUtils.doGetText(tableIdLink);
		return groupTableUserId;
	}
	
	/**
	 * This method is used to get Group form's group Id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getGroupFormId() {
		elementUtils.waitForElementPresent(groupFormId);
		return elementUtils.doGetText(groupFormId);
	}
	
	/**
	 * This method is used to get group table's name
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getGroupTableName() {
		elementUtils.waitForElementPresent(tableNameLink);
		String studyTableObjectName = elementUtils.doGetText(tableNameLink);
		return studyTableObjectName;
	}
	
	/**
	 * This method is used to click Group table's name link
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickGroupTableName() {
		elementUtils.waitForElementPresent(tableNameLink);
		elementUtils.doClick(tableNameLink);
	}
	
	/**
	 * This method is used to get group form's name
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getGroupFormName() {
		elementUtils.waitForElementPresent(groupFormName);
		return elementUtils.doGetText(By.cssSelector("section > div:nth-child(2) > div"));
	}
	
	/**
	 * This method is used to click group table's name link
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickGroupTableNameLink() {
		elementUtils.waitForElementPresent(tableNameLink);
		elementUtils.doClick(tableNameLink);
	}
	
	/**
	 * This method is used to edit Group
	 * 
	 * @param name
	 * @param memberUsers
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	public void editGroup(String name, String memberUsers) throws InterruptedException {
		By memberUsersOption = By.xpath("//option[contains(text(), '" + memberUsers + "')]");
		elementUtils.waitForElementPresent(groupName);
		elementUtils.doSendKeys(groupName, name);
		elementUtils.waitForElementPresent(memberUsersOption);
		elementUtils.doClick(memberUsersOption);
		elementUtils.waitForElementVisible(submitButton);
		elementUtils.doClick(submitButton);
	}
	
	/**
	 * This method is used to get edited Group
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getEditedName() {
		elementUtils.waitForElementPresent(By.cssSelector("div:nth-child(2) > div:nth-child(2)"));
		return elementUtils.doGetText(By.cssSelector("div:nth-child(2) > div:nth-child(2)"));

	}
	
	/**
	 * This method is used to get Group form page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getGroupFormPageTitle() {
		elementUtils.waitForElementPresent(groupFormPageTitle);
		return elementUtils.doGetText(groupFormPageTitle);
	}
	
	
}
