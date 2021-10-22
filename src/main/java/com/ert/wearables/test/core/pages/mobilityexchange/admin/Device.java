package com.ert.wearables.test.core.pages.mobilityexchange.admin;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.utilities.AdminUtils;
import com.ert.wearables.test.core.utilities.ElementUtils;


public class Device extends BaseSetup{
	
	WebDriver driver;
	ElementUtils elementUtils;
	AdminUtils adminUtils;
	
	Logger log = Logger.getLogger(Study.class);
	
	// Site Page Locators
	private By deviceHeader = By.xpath("//h2[normalize-space()='Devices']");
	
	// Locators
	private By devicesSideBarButton = By.xpath("//a[@href='#device']//span[@class='es-icon-expected-visit es-icon']");
	
	// Device property locators
	private By deviceDeviceId = By.xpath("//label[contains(text(), \"Device id\")]/following-sibling::input");
	private By deviceUploadedFiles = By.xpath("//label[contains(text(), \"Uploaded files\")]/following-sibling::input");
	private By deviceUploadedBytes = By.xpath("//label[contains(text(), \"Uploaded bytes\")]/following-sibling::input");
	private By deviceLastMesage = By.xpath("//label[contains(text(), \"Last message\")]/following-sibling::input");
	private By deviceNotes = By.xpath("/html/body/div/main/div/div/form/section/div[7]/textarea");
	private By submitButton = By.xpath("//button[normalize-space()='Submit']");
	
	// Device Table's Link Locators
	private By tableIdLink = By.xpath("//table//tbody//tr[1]//td[1]//a");
	private By tableDeviceIdLink = By.xpath("//table//tbody//tr[1]//td[2]//a");
	private By tableDataFilesLink = By.xpath("//table//tbody//tr[1]//td[3]//a");
	private By tableDeviceAllocationsLink = By.xpath("//table//tbody//tr[1]//td[4]//a");
	private By tableLogMessagesLink = By.xpath("/html/body/div/main/div/div/div[3]/div/table/tbody/tr[1]/td[5]/div/a");
	
	// Device Form's Locators
	private By deviceFormId = By.xpath("/html/body/div/main/div/div/form/section/div[1]/div/a");
	private By DeviceFormDeviceId = By.cssSelector("section > div:nth-child(2) > div");
	private By deviceFormDeleteButton = By.xpath("//button[normalize-space()='Delete']");
	private By deviceFormPageTitle = By.xpath("//h2[@class='es-page-title']");
	
	// WebTable Headers and Rows Locators
	private By tableHeaderDeviceId = By.xpath("//table//thead//tr[1]//th[2]");
	private By tableRowsDeviceId = By.xpath("//table//tbody//tr//td[2]");
	private By tableHeaderType = By.xpath("//table//thead//tr[1]//th[3]");
	private By tableRowsType = By.xpath("//table//tbody//tr//td[3]");
	private By tableHeaderDataFiles = By.xpath("//table//thead//tr[1]//th[3]");
	private By tableRowsDataFiles = By.xpath("//table//tbody//tr//td[3]");
	private By tableHeaderDeviceAllocations = By.xpath("//table//thead//tr[1]//th[4]");
	private By tableRowsDeviceAllocations = By.xpath("//table//tbody//tr//td[4]");
	private By tableHeaderLogMessages = By.xpath("//table//thead//tr[1]//th[5]");
	private By tableRowsLogMessages = By.xpath("//table//tbody//tr//td[5]");
	private By tableHeaderUploadedFiles = By.xpath("//table//thead//tr[1]//th[7]");
	private By tableRowsUploadedFiles = By.xpath("//table//tbody//tr//td[7]");
	private By tableHeaderUploadedBytes = By.xpath("//table//thead//tr[1]//th[8]");
	private By tableRowsUploadedBytes = By.xpath("//table//tbody//tr//td[8]");
	private By tableHeaderLastMessage = By.xpath("//table//thead//tr[1]//th[9]");
	private By tableRowsLastMessage = By.xpath("//table//tbody//tr//td[9]");
	private By tableHeaderNotes = By.xpath("//table//thead//tr[1]//th[10]");
	private By tableRowsNotes = By.xpath("//table//tbody//tr//td[10]");
	private By tableHeaderCreationDate = By.xpath("//table//thead//tr[1]//th[11]");
	private By tableRowsCreationDate = By.xpath("//table//tbody//tr//td[11]");
	private By tableHeaderDeleted = By.xpath("//table//thead//tr[1]//th[12]");
	private By tableRowsDeleted = By.xpath("//table//tbody//tr//td[12]");
	private By tableHeaderLastModified = By.xpath("//table//thead//tr[1]//th[13]");
	private By tableRowsLastModified = By.xpath("//table//tbody//tr//td[13]");

	public By getTableHeaderDeviceId() {
		return tableHeaderDeviceId;
	}

	public By getTableRowsDeviceId() {
		return tableRowsDeviceId;
	}

	public By getTableHeaderType() {
		return tableHeaderType;
	}

	public By getTableRowsType() {
		return tableRowsType;
	}

	public By getTableHeaderDataFiles() {
		return tableHeaderDataFiles;
	}

	public By getTableRowsDataFiles() {
		return tableRowsDataFiles;
	}

	public By getTableHeaderDeviceAllocations() {
		return tableHeaderDeviceAllocations;
	}

	public By getTableRowsDeviceAllocations() {
		return tableRowsDeviceAllocations;
	}

	public By getTableHeaderLogMessages() {
		return tableHeaderLogMessages;
	}

	public By getTableRowsLogMessages() {
		return tableRowsLogMessages;
	}

	public By getTableHeaderUploadedFiles() {
		return tableHeaderUploadedFiles;
	}

	public By getTableRowsUploadedFiles() {
		return tableRowsUploadedFiles;
	}

	public By getTableHeaderUploadedBytes() {
		return tableHeaderUploadedBytes;
	}

	public By getTableRowsUploadedBytes() {
		return tableRowsUploadedBytes;
	}

	public By getTableHeaderLastMessage() {
		return tableHeaderLastMessage;
	}

	public By getTableRowsLastMessage() {
		return tableRowsLastMessage;
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
	 * @author yavuz.ozturk
	 */
	public Device(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}

	/**
	 * This method is used to click Device side bar button
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickDevicesSideBarButton() {
		elementUtils.waitForElementPresent(devicesSideBarButton);
		elementUtils.doClick(devicesSideBarButton);
	}
	
	/**
	 * This method create new device
	 * 
	 * @param deviceId
	 * @param type
	 * @param uploadedFiles
	 * @param uploadedBytes
	 * @param lastMessage
	 * @param notes
	 * @author yavuz.ozturk
	 */
	public void createDevice(String deviceId, String type, String uploadedFiles, String uploadedBytes, String lastMessage, String notes) {
		By typeOption = By.xpath("//option[contains(text(), '" + type + "')]");
		elementUtils.waitForElementPresent(deviceDeviceId);
		elementUtils.doSendKeys(deviceDeviceId, deviceId);
		elementUtils.waitForElementPresent(typeOption);
		elementUtils.doClick(typeOption);
		elementUtils.waitForElementPresent(deviceUploadedFiles);
		elementUtils.doSendKeys(deviceUploadedFiles, uploadedFiles);
		elementUtils.waitForElementPresent(deviceUploadedBytes);
		elementUtils.doSendKeys(deviceUploadedBytes, uploadedBytes);
		elementUtils.waitForElementPresent(deviceLastMesage);
		elementUtils.doSendKeys(deviceLastMesage, lastMessage);
		elementUtils.waitForElementPresent(deviceNotes);
		elementUtils.doSendKeys(deviceNotes, notes);
		elementUtils.waitForElementVisible(submitButton);
		elementUtils.doClick(submitButton);
	}
	
	/**
	 * This method is used to get Last Created Device from Device Table
	 * 
	 * @return
	 * @author yavuz.ozturk
	 * @throws InterruptedException 
	 */
	public String getLastCreatedDeviceIdFromDeviceTable() {
		String beforeXpath = "//table//tbody//tr[";
		String afterXpath = "]//td[2]";
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int lastRowIndex = tableRowList.size();
		WebElement lastCreatedUser = elementUtils.getElement(By.xpath(beforeXpath + lastRowIndex + afterXpath));
		return lastCreatedUser.getText();
	}
	
	/**
	 * This method is used to get Last Created Device ID from Device Table
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getLastCreatedIdFromDeviceTable() {
		String beforeXpath = "//table//tbody//tr[";
		String afterXpath = "]//td[1]";
		By tableRowsXpath = By.xpath("//table//tbody//tr");
		List<WebElement> tableRowList = driver.findElements(tableRowsXpath);
		int lastRowIndex = tableRowList.size();
		WebElement lastCreatedUser = elementUtils.getElement(By.xpath(beforeXpath + lastRowIndex + afterXpath));
		return lastCreatedUser.getText();
	}
	
	/**
	 * This method is used to delete last created Device from Device Table Via ID Link
	 * 
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	public void deleteLastCreatedDeviceViaIdLink() throws InterruptedException {
		elementUtils.doClick(By.linkText(getLastCreatedIdFromDeviceTable()));
		elementUtils.doClick(deviceFormDeleteButton);
		TimeUnit.SECONDS.sleep(10);
		adminUtils.clickAlertOk();
	}
	
	/**
	 * This method is used to delete last created Subject from Subject Table Via Public ID Link
	 * 
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	public void deleteLastCreatedDeviceViaDeviceIdLink() throws InterruptedException {
		elementUtils.doClick(By.linkText(getLastCreatedDeviceIdFromDeviceTable()));
		elementUtils.doClick(deviceFormDeleteButton);
		TimeUnit.SECONDS.sleep(3);
		adminUtils.clickAlertOk();
	}
	
	/**
	 * This method is used to get device table's Id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getDeviceTableId() {
		elementUtils.waitForElementPresent(tableIdLink);
		String deviceTableDeviceId = elementUtils.doGetText(tableIdLink);
		return deviceTableDeviceId;
	}
	
	/**
	 * This method is used to get Device form's device Id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getDeviceFormId() {
		elementUtils.waitForElementPresent(deviceFormId);
		return elementUtils.doGetText(deviceFormId);
	}
	
	/**
	 * This method is used to get Device table's device Id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getDeviceTableDeviceId() {
		elementUtils.waitForElementPresent(tableDeviceIdLink);
		String deviceTableDeviceId = elementUtils.doGetText(tableDeviceIdLink);
		return deviceTableDeviceId;
	}
	
	/**
	 * This method is used to click Device table's device Id link
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickDeviceTableDeviceIdLink() {
		elementUtils.waitForElementPresent(tableDeviceIdLink);
		elementUtils.doClick(tableDeviceIdLink);
	}
	
	/**
	 * This method is used to get device form's Device Id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getDeviceFormDeviceId() {
		elementUtils.waitForElementPresent(DeviceFormDeviceId);
		return elementUtils.doGetText(By.cssSelector("section > div:nth-child(2) > div"));
	}
	
	/**
	 * This method is used to edit device
	 * 
	 * @param deviceId
	 * @param type
	 * @param uploadedFiles
	 * @param uploadedBytes
	 * @param lastMessage
	 * @param notes
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	public void editDevice(String deviceId, String type, String uploadedFiles, String uploadedBytes, String lastMessage, String notes) throws InterruptedException {
		By typeOption = By.xpath("//option[contains(text(), '" + type + "')]");
		elementUtils.waitForElementPresent(deviceDeviceId);
		elementUtils.doSendKeys(deviceDeviceId, deviceId);
		elementUtils.waitForElementPresent(typeOption);
		elementUtils.doClick(typeOption);
		elementUtils.waitForElementPresent(deviceUploadedFiles);
		elementUtils.doSendKeys(deviceUploadedFiles, uploadedFiles);
		elementUtils.waitForElementPresent(deviceUploadedBytes);
		elementUtils.doSendKeys(deviceUploadedBytes, uploadedBytes);
		elementUtils.waitForElementPresent(deviceLastMesage);
		elementUtils.doSendKeys(deviceLastMesage, lastMessage);
		elementUtils.waitForElementPresent(deviceNotes);
		elementUtils.doSendKeys(deviceNotes, notes);
		elementUtils.waitForElementVisible(submitButton);
		elementUtils.doClick(submitButton);
	}
	
	/**
	 * This method is used to get edited device Id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getEditedDeviceId() {
		elementUtils.waitForElementPresent(By.xpath("/html/body/div/main/div/div/form/section/div[2]/div"));
		return elementUtils.doGetText(By.xpath("/html/body/div/main/div/div/form/section/div[2]/div"));
	}
	
	/**
	 * This method is used to get device form page title
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getDeviceFormPageTitle() {
		elementUtils.waitForElementPresent(deviceFormPageTitle);
		return elementUtils.doGetText(deviceFormPageTitle);
	}
	
	/**
	 * This method is used to get device table's data files count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getDeviceTableDataFilesCount() {
		elementUtils.waitForElementPresent(tableDataFilesLink);
		String dataFiles = elementUtils.doGetText(tableDataFilesLink);
		int dataFilesCount = Integer.parseInt(dataFiles.substring(0, 1));
		elementUtils.doClick(tableDataFilesLink);
		return dataFilesCount;
	}
	
	/**
	 * This method is used to get device table's device allocations count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getDeviceTableDeviceAllocationsCount() {
		elementUtils.waitForElementPresent(tableDeviceAllocationsLink);
		String deviceAllocations = elementUtils.doGetText(tableDeviceAllocationsLink);
		int deviceAllocationsCount = Integer.parseInt(deviceAllocations.substring(0, 1));
		elementUtils.doClick(tableDeviceAllocationsLink);
		return deviceAllocationsCount;
	}
	
	/**
	 * This method is used to get device table's log message count
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public int getDeviceTableLogMessageCount() {
		elementUtils.waitForElementPresent(tableLogMessagesLink);
		String logMessages = elementUtils.doGetText(tableLogMessagesLink);
		int logMessagesCount = Integer.parseInt(logMessages.substring(0, 1));
		elementUtils.doClick(tableLogMessagesLink);
		return logMessagesCount;
	}
	
}
