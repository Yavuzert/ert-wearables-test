package com.ert.wearables.test.tests.mobilityexchange.admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.Allocation;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.AuditLog;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.Device;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.DeviceAllocation;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.DeviceLogMessage;
import com.ert.wearables.test.core.utilities.AdminUtils;
import com.ert.wearables.test.core.utilities.AppConstants;
import com.ert.wearables.test.core.utilities.Credentials;
import com.ert.wearables.test.core.utilities.ProjectsUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

/**
 * This class tests Device component in Mobility Exchange Admin Application
 * 
 * @author yavuz.ozturk
 */
@Epic("SB-1871 - https://apdmwearables.atlassian.net/browse/SB-1871")
@Feature("SB-3811 - https://apdmwearables.atlassian.net/browse/SB-3811")
//@Listeners(ExtentReportListener.class)
public class TestDevice {
	
	WebDriver driver;
	BaseSetup baseSetup;
	Properties prop;
	AdminUtils adminUtils;
	Credentials userCred;
	Device device;
	ProjectsUtils projectsUtils;
	AuditLog auditLog;
	Allocation allocation;
	DeviceAllocation deviceAllocation;
	DeviceLogMessage deviceLogMessage;
	
	Logger log = Logger.getLogger(TestDevice.class);
	
	@BeforeMethod(groups = "start")
	public void setUp() {
		log.info("BeforeMethod is starting...");
		log.info("TestDevice class is starting for tests");
		baseSetup = new BaseSetup();
		prop = baseSetup.init_properties();
		String browserName = prop.getProperty("browser");
		driver = baseSetup.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		log.info("url is launched: " + prop.getProperty("url"));
		adminUtils = new AdminUtils(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		adminUtils.doLogin(userCred);
		projectsUtils = new ProjectsUtils(driver);
		device = new Device(driver);
		auditLog = new AuditLog(driver);
		deviceAllocation = new DeviceAllocation(driver);
		deviceLogMessage = new DeviceLogMessage(driver);
		log.info("setUp is ending... ");
	}
	
	/**
	 * Test full and compact views for device component
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 1, description = "Test testFullCompactViewsOnDevice", enabled = true, groups = {"smoke"})
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testFullCompactViewsOnDevice")
	@Severity(SeverityLevel.CRITICAL)
	public void testFullCompactViewsOnDevice() {
		log.info("testFullCompactViewsOnDevice test is starting... ");
		device.clickDevicesSideBarButton();
		String fullButtonText = adminUtils.getFullButtonText();
		adminUtils.clickFull();
		String compactText = adminUtils.getCompactText();
		if (compactText.equalsIgnoreCase(compactText)) {
			adminUtils.clickCompact();
		}
		assertEquals(fullButtonText, "Full", "verifying full button text");
		assertEquals(compactText, "Compact", "verifying compact button text");
		log.info("testFullCompactViewsOnDevice test is ending... ");
	}
	
	/**
	 * Test create new device with (not set) type selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 2, description = "Test testCreateNewDeviceWithNotSetType", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testCreateNewDeviceWithNotSetType")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewDeviceWithNotSetType() throws InterruptedException {
		log.info("testCreateNewDeviceWithNotSetType test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.clickCreate();
		String deviceId = "Test_DeviceId_" + projectsUtils.randomString(5);
		String uploadedFiles = projectsUtils.randomInteger(3);
		String uploadedBytes = projectsUtils.randomInteger(5);
		String lastMessage = "Test_LastMessage_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		device.createDevice(deviceId, "(not set)", uploadedFiles, uploadedBytes, lastMessage, notes);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedDeviceId = device.getLastCreatedDeviceIdFromDeviceTable();
		System.out.println(lastCreatedDeviceId);
		assertEquals(deviceId, lastCreatedDeviceId, "verifying the presence of last device created");
		log.info("testCreateNewDeviceWithNotSetType test is ending... ");
	}
	
	/**
	 * Test create new device with Opal type selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 3, description = "Test testCreateNewDeviceWithOpalType", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testCreateNewDeviceWithOpalType")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewDeviceWithOpalType() throws InterruptedException {
		log.info("testCreateNewDeviceWithOpalType test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.clickCreate();
		String deviceId = "Test_DeviceId_" + projectsUtils.randomString(5);
		String uploadedFiles = projectsUtils.randomInteger(3);
		String uploadedBytes = projectsUtils.randomInteger(5);
		String lastMessage = "Test_LastMessage_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		device.createDevice(deviceId, "Opal", uploadedFiles, uploadedBytes, lastMessage, notes);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedDeviceId = device.getLastCreatedDeviceIdFromDeviceTable();
		System.out.println(lastCreatedDeviceId);
		assertEquals(deviceId, lastCreatedDeviceId, "verifying the presence of last device created");
		log.info("testCreateNewDeviceWithOpalType test is ending... ");
	}
	
	/**
	 * Test create new device with Opal Data Hub type selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 4, description = "Test testCreateNewDeviceWithOpalDataHubType", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testCreateNewDeviceWithOpalDataHubType")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewDeviceWithOpalDataHubType() throws InterruptedException {
		log.info("testCreateNewDeviceWithOpalDataHubType test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.clickCreate();
		String deviceId = "Test_DeviceId_" + projectsUtils.randomString(5);
		String uploadedFiles = projectsUtils.randomInteger(3);
		String uploadedBytes = projectsUtils.randomInteger(5);
		String lastMessage = "Test_LastMessage_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		device.createDevice(deviceId, "Opal data hub", uploadedFiles, uploadedBytes, lastMessage, notes);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedDeviceId = device.getLastCreatedDeviceIdFromDeviceTable();
		System.out.println(lastCreatedDeviceId);
		assertEquals(deviceId, lastCreatedDeviceId, "verifying the presence of last device created");
		log.info("testCreateNewDeviceWithOpalDataHubType test is ending... ");
	}
	
	/**
	 * Test device create with valid web table data
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 5, description = "Test validateDeviceWebTableData", enabled = true, groups = {"smoke"})
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test validateDeviceWebTableData")
	@Severity(SeverityLevel.CRITICAL)
	public void validateDeviceWebTableData() throws InterruptedException {
		log.info("validateDeviceWebTableData test is starting... ");
		TimeUnit.SECONDS.sleep(3);
		device.clickDevicesSideBarButton();
		adminUtils.clickCreate();
		Map<String, String> newUserMap = new LinkedHashMap<>();
		String deviceId = "Test_DeviceId_" + projectsUtils.randomString(5);
		String uploadedFiles = projectsUtils.randomInteger(3);
		String uploadedBytes = projectsUtils.randomInteger(5);
		String lastMessage = "Test_LastMessage_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		newUserMap.put("DeviceId", deviceId);
		newUserMap.put("UploadedFiles", uploadedFiles);
		newUserMap.put("UploadedBytes", uploadedBytes);
		newUserMap.put("LastMessage", lastMessage);
		newUserMap.put("Notes", notes);
		device.createDevice(deviceId, "Opal", uploadedFiles, uploadedBytes, lastMessage, notes);
		adminUtils.clickFull();
		List<Map<String, String>> tableDataList = projectsUtils.getTableDataAsMapList();
		for (int i = 0; i < tableDataList.size(); i++) {
			Map<String, String> eachMap = tableDataList.get(i);
			String newDeviceId = newUserMap.get("DeviceId");
			String newUploadedFiles = newUserMap.get("UploadedFiles");
			String newUploadedBytes = newUserMap.get("UploadedBytes");
			String newHeight = newUserMap.get("LastMessage");
			String newNotes = newUserMap.get("Notes");
			if (eachMap.get("DeviceId") != null) {
				if ((eachMap.get("DeviceId")).equals(newDeviceId)) {
					assertEquals(eachMap.get("DeviceId"), newDeviceId);
					log.info("DeviceId from table: " + eachMap.get("DeviceId"));
					log.info("DeviceId from new device deviceId: " + newUserMap.get("DeviceId"));
				}
			}
			if (eachMap.get("UploadedFiles") != null) {
				if ((eachMap.get("UploadedFiles")).equals(newUploadedFiles)) {
					assertEquals(eachMap.get("UploadedFiles"), newUploadedFiles);
				}
			}
			if (eachMap.get("UploadedBytes") != null) {
				if ((eachMap.get("UploadedBytes")).equals(newUploadedBytes)) {
					assertEquals(eachMap.get("UploadedBytes"), newUploadedBytes);
				}
			}
			if (eachMap.get("LastMessage") != null) {
				if ((eachMap.get("LastMessage")).equals(newHeight)) {
					assertEquals(eachMap.get("LastMessage"), newHeight);
				}
			}
			if (eachMap.get("Notes") != null) {
				if ((eachMap.get("Notes")).equals(newNotes)) {
					assertEquals(eachMap.get("Notes"), newNotes);
				}
			}
		}
		adminUtils.clickCompact();
		log.info("validateDeviceWebTableData test is ending... ");
	}
	
	/**
	 * Test device history via device table's Id Link from device form
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 6, description = "Test testDeviceHistoryViaDeviceTablesIdLink", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceHistoryViaDeviceTablesIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteHistoryViaSiteTableIdLink()  {
		log.info("testDeviceHistoryViaDeviceTablesIdLink test is starting... ");
		device.clickDevicesSideBarButton();
		String deviceTableId = device.getDeviceTableId();
		adminUtils.clickTableIdLink();
		String deviceFormDeviceId = device.getDeviceFormId();
		adminUtils.clickHistory();
		String auditLogTableObjectId = auditLog.getAuditLogTableObjectId();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("audit.log"));
		assertEquals( deviceTableId,auditLogTableObjectId);
		assertEquals(deviceFormDeviceId,auditLogTableObjectId);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testDeviceHistoryViaDeviceTablesIdLink test is ending... ");
	}
	
	/**
	 * Test device history via device table's Device ID Link from device form
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 7, description = "Test testDeviceHistoryViaDeviceTablesDeviceIdLink", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceHistoryViaDeviceTablesDeviceIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceHistoryViaDeviceTablesDeviceIdLink()  {
		log.info("testDeviceHistoryViaDeviceTablesDeviceIdLink test is starting... ");
		device.clickDevicesSideBarButton();
		String deviceTableDeviceId = device.getDeviceTableDeviceId();
		device.clickDeviceTableDeviceIdLink();
		String deviceFormDeviceId = device.getDeviceFormDeviceId();
		adminUtils.clickHistory();
		adminUtils.clickFull();
		String auditLogTableObjectId = auditLog.getAuditLogTableObjectId();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("audit.log"));
		assertEquals(deviceTableDeviceId,deviceFormDeviceId);
		assertEquals(deviceFormDeviceId,auditLogTableObjectId);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testDeviceHistoryViaDeviceTablesDeviceIdLink test is ending... ");
	}
	
	/**
	 * Test device history via device table's Id Link from device form when editing subject
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk 
	 */
	@Test(priority = 8, description = "Test testDeviceHistoryViaDeviceTablesIdLinkWhenEditingDevice", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceHistoryViaDeviceTablesIdLinkWhenEditingDevice")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceHistoryViaDeviceTablesIdLinkWhenEditingDevice() {
		log.info("testDeviceHistoryViaDeviceTablesIdLinkWhenEditingDevice test is starting... ");
		device.clickDevicesSideBarButton();
		String deviceTableDeviceId = device.getDeviceTableId();
		adminUtils.clickTableIdLink();
		String deviceFormDeviceId = device.getDeviceFormId();
		adminUtils.clickEditButton();
		String editId = adminUtils.getEditId();
		adminUtils.clickHistory();
		adminUtils.clickFull();
		String auditLogTableObjectId = auditLog.getAuditLogTableObjectId();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("audit.log"));
		assertEquals(deviceTableDeviceId,deviceFormDeviceId);
		assertEquals(editId,auditLogTableObjectId);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testDeviceHistoryViaDeviceTablesIdLinkWhenEditingDevice test is ending... ");
	}
	
	/**
	 * Test device history via device table's device Id Link from subject form when editing subject
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk 
	 */
	@Test(priority = 9, description = "Test testDeviceHistoryViaDeviceTablesIdLinkWhenEditingDevice", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceHistoryViaDeviceTablesIdLinkWhenEditingDevice")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceHistoryViaDeviceTablesDeviceIdLinkWhenEditingDevice() {
		log.info("testDeviceHistoryViaDeviceTablesDeviceIdLinkWhenEditingDevice test is starting... ");
		device.clickDevicesSideBarButton();
		String deviceTableDeviceId = device.getDeviceTableDeviceId();
		device.clickDeviceTableDeviceIdLink();
		String deviceFormDeviceId = device.getDeviceFormDeviceId();
		adminUtils.clickEditButton();
		String editId = adminUtils.getEditId();
		adminUtils.clickHistory();
		adminUtils.clickFull();
		String auditLogTableObjectId = auditLog.getAuditLogTableObjectId();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("audit.log"));
		assertEquals(deviceTableDeviceId,deviceFormDeviceId);
		assertEquals(editId,auditLogTableObjectId);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testDeviceHistoryViaDeviceTablesDeviceIdLinkWhenEditingDevice test is ending... ");
	}
	
	/**
	 * Test device form's device deleting with table ID link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	@Test(priority = 10, description = "Test testDeviceFormDeleteDeviceWithIdLink", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceFormDeleteDeviceWithIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceFormDeleteDeviceWithIdLink() throws InterruptedException {
		log.info("testDeviceFormDeleteDeviceWithIdLink test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.navigateToLastPage();
		String lastCreatedDevice = device.getLastCreatedDeviceIdFromDeviceTable();
		log.info("Last Device Before Delete " + lastCreatedDevice);
		device.deleteLastCreatedDeviceViaIdLink();
		TimeUnit.SECONDS.sleep(3);
		String lastDevice = device.getLastCreatedDeviceIdFromDeviceTable();
		log.info("Last Device After Delete " + lastDevice);
		assertNotEquals(lastCreatedDevice, lastDevice);
		log.info("testDeviceFormDeleteDeviceWithIdLink test is ending... ");
	}
	
	/**
	 * Test device form's device deleting with device ID link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	@Test(priority = 11, description = "Test testDeviceFormDeleteDeviceWithDeviceIdLink", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceFormDeleteDeviceWithDeviceIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceFormDeleteDeviceWithDeviceIdLink() throws InterruptedException {
		log.info("testDeviceFormDeleteDeviceWithDeviceIdLink test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.navigateToLastPage();
		String lastCreatedDevice = device.getLastCreatedDeviceIdFromDeviceTable();
		log.info("Last Device Before Delete " + lastCreatedDevice);
		device.deleteLastCreatedDeviceViaDeviceIdLink();
		TimeUnit.SECONDS.sleep(3);
		String lastDevice = device.getLastCreatedDeviceIdFromDeviceTable();
		log.info("Last Device After Delete " + lastDevice);
		assertNotEquals(lastCreatedDevice, lastDevice);
		log.info("testDeviceFormDeleteDeviceWithDeviceIdLink test is ending... ");
	}
	
	/**
	 * Test device edit button via id link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 12, description = "Test testDeviceEditButtonViaIdLink", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test  testDeviceEditButtonViaIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void  testDeviceEditButtonViaIdLink() throws InterruptedException {
		log.info("testDeviceEditButtonViaIdLink test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.clickTableIdLink();
		adminUtils.clickEditButton();
		String deviceId = "Test_DeviceId_" + projectsUtils.randomString(5);
		String uploadedFiles = projectsUtils.randomInteger(3);
		String uploadedBytes = projectsUtils.randomInteger(5);
		String lastMessage = "Test_LastMessage_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		device.editDevice(deviceId, "Opal" ,uploadedFiles, uploadedBytes, lastMessage, notes);
		String editedDeviceId = device.getEditedDeviceId();
		assertEquals(editedDeviceId, deviceId);
		log.info("testDeviceEditButtonViaIdLink test is ending... ");
	}
	
	/**
	 * Test device edit button via device id link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 13, description = "Test testDeviceEditButtonViaDeviceIdLink", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test  testDeviceEditButtonViaDeviceIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void  testDeviceEditButtonViaDeviceIdLink() throws InterruptedException {
		log.info("testDeviceEditButtonViaDeviceIdLink test is starting... ");
		device.clickDevicesSideBarButton();
		device.clickDeviceTableDeviceIdLink();
		adminUtils.clickEditButton();
		String deviceId = "Test_DeviceId_" + projectsUtils.randomString(5);
		String uploadedFiles = projectsUtils.randomInteger(3);
		String uploadedBytes = projectsUtils.randomInteger(5);
		String lastMessage = "Test_LastMessage_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		device.editDevice(deviceId, "Opal" ,uploadedFiles, uploadedBytes, lastMessage, notes);
		String editedDeviceId = device.getEditedDeviceId();
		assertEquals(editedDeviceId, deviceId);
		log.info("testDeviceEditButtonViaDeviceIdLink test is ending... ");
	}
	
	/**
	 * Test device table's id link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 14, description = "Test testDeviceTableIdLink", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableIdLink() {
		log.info("testDeviceTableIdLink test is starting... ");
		device.clickDevicesSideBarButton();
		String deviceTableId = adminUtils.getTableId();
		String deviceFormId = adminUtils.getFormId();
		log.info("Device Table Id: " + deviceTableId);
		log.info("Device Form Id: " + deviceFormId);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		assertTrue(curentURL.endsWith(deviceFormId));
		assertEquals(deviceTableId, deviceFormId);
		log.info("testDeviceTableIdLink test is ending... ");
	}
	
	/**
	 * Test device table's device id link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 15, description = "Test testDeviceTableDeviceIdLink", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableDeviceIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableDeviceIdLink() {
		log.info("testDeviceTableDeviceIdLink test is starting... ");
		device.clickDevicesSideBarButton();
		String deviceTableDeviceId = device.getDeviceTableDeviceId();
		device.clickDeviceTableDeviceIdLink();
		String deviceFormPageTitle = device.getDeviceFormPageTitle();
		String deviceFormDeviceId = device.getDeviceFormDeviceId();
		log.info("Device Table Device Id: " + deviceTableDeviceId);
		log.info("Device Form Page Title: " + deviceFormPageTitle);
		log.info("Device Form Device Id: " + deviceFormDeviceId);
		assertTrue(deviceTableDeviceId.equalsIgnoreCase(deviceFormDeviceId));
		assertTrue(deviceTableDeviceId.equalsIgnoreCase(deviceFormPageTitle));
		assertTrue(deviceFormPageTitle.equalsIgnoreCase(deviceFormDeviceId));
		log.info("testDeviceTableDeviceIdLink test is ending... ");
	}
	
	/**
	 * Test device table's Data Files link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk 
	 */
	@Test(priority = 16, description = "Test testDeviceTableDataFilesLink", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableDataFilesLink")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableDataFilesLink() {
		log.info("testDeviceTableDataFilesLink test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.clickFull();
		int deviceTableDataFilesCount = device.getDeviceTableDataFilesCount();
		int deviceDataFilesTableElementCount = allocation.getDeviceDataFilesCount();
		log.info("Device Table Data Files Count: " + deviceTableDataFilesCount);
		log.info("Device Data Files Table Element Count: " + deviceDataFilesTableElementCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String deviceDataFilesHeader = allocation.getDeviceDataFilesHeader();
		String deviceDataFilesPageTitle = allocation.getDeviceDataFilesPageTitle();
		assertTrue(curentURL.contains("allocation"));
		assertEquals(deviceTableDataFilesCount, deviceDataFilesTableElementCount);
		assertEquals(deviceDataFilesHeader, AppConstants.DEVICEDATAFILES_PAGE_HEADER);
		assertEquals(deviceDataFilesPageTitle, AppConstants.DEVICEDATAFILES_PAGE_TITLE);
		log.info("testDeviceTableDataFilesLink test is ending... ");
	}
	
	/**
	 * Test device table's Log Message link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk 
	 */
	@Test(priority = 17, description = "Test testDeviceTableLogMessageLink", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableLogMessageLink")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableLogMessageLink() {
		log.info("testDeviceTableLogMessageLink test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.clickFull();
		int deviceTableLogMessagesCount = device.getDeviceTableLogMessageCount();
		int deviceLogMessagesTableElementCount = deviceLogMessage.getDeviceLogMessagesCount();
		log.info("Device Table Log Message Count: " + deviceTableLogMessagesCount);
		log.info("Device Allocations Table Element Count: " + deviceLogMessagesTableElementCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String logMessagesHeader = deviceAllocation.getDeviceAllocationHeader();
		String logMessagesPageTitle = deviceAllocation.getDeviceAllocationsPageTitle();
		assertTrue(curentURL.contains("device.log.message"));
		assertEquals(deviceTableLogMessagesCount, deviceLogMessagesTableElementCount);
		assertEquals(logMessagesHeader, AppConstants.DEVICELOGMESSAGE_PAGE_HEADER);
		assertEquals(logMessagesPageTitle, AppConstants.DEVICELOGMESSAGE_PAGE_TITLE);
		log.info("testDeviceTableLogMessageLink test is ending... ");
	}
	
	/**
	 * Test sorting of the id rows in the device table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 18, description = "Test testDeviceTableIdSort", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableIdSort")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableIdSort() {
		log.info("testDeviceTableIdSort test is starting... ");
		device.clickDevicesSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(adminUtils.getTableRowsId(), adminUtils.getTableHeaderId());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(adminUtils.getTableRowsId(),adminUtils.getTableHeaderId());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testDeviceTableIdSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Device Id rows in the device table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 19, description = "Test testDeviceTableDeviceIdSort", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableDeviceIdSort")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableDeviceIdSort() {
		log.info("testDeviceTableDeviceIdSort test is starting... ");
		device.clickDevicesSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(device.getTableRowsDeviceId(),device.getTableHeaderDeviceId());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(device.getTableRowsDeviceId(),device.getTableHeaderDeviceId());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testDeviceTableDeviceIdSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Type rows in the device table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 20, description = "Test testDeviceTableTypeSort", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableTypeSort")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableTypeSort() {
		log.info("testDeviceTableTypeSort test is starting... ");
		device.clickDevicesSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(device.getTableRowsType(),device.getTableHeaderType());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(device.getTableRowsType(),device.getTableHeaderType());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testDeviceTableTypeSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Data files rows in the device table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 21, description = "Test testDeviceTableDataFilesSort", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableDataFilesSort")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableDataFilesSort() {
		log.info("testDeviceTableDataFilesSort test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(device.getTableRowsDataFiles(),device.getTableHeaderDataFiles());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(device.getTableRowsDataFiles(),device.getTableHeaderDataFiles());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testDeviceTableDataFilesSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Device allocations rows in the device table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 22, description = "Test testDeviceTableDeviceAllocationsSort", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableDeviceAllocationsSort")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableDeviceAllocationsSort() {
		log.info("testDeviceTableDeviceAllocationsSort test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(device.getTableRowsDeviceAllocations(),device.getTableHeaderDeviceAllocations());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(device.getTableRowsDeviceAllocations(),device.getTableHeaderDeviceAllocations());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testDeviceTableDeviceAllocationsSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Log messages rows in the device table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 23, description = "Test testDeviceTableLogMessagesSort", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableLogMessagesSort")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableLogMessagesSort() {
		log.info("testDeviceTableLogMessagesSort test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(device.getTableRowsLogMessages(),device.getTableHeaderLogMessages());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(device.getTableRowsLogMessages(),device.getTableHeaderLogMessages());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testDeviceTableLogMessagesSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Uploaded files rows in the device table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 24, description = "Test testDeviceTableUploadedFilesSort", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableUploadedFilesSort")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableUploadedFilesSort() {
		log.info("testDeviceTableUploadedFilesSort test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(device.getTableRowsUploadedFiles(),device.getTableHeaderUploadedFiles());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(device.getTableRowsUploadedFiles(),device.getTableHeaderUploadedFiles());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testDeviceTableUploadedFilesSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Uploaded bytes rows in the device table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 25, description = "Test testDeviceTableUploadedBytesSort", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableUploadedBytesSort")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableUploadedBytesSort() {
		log.info("testDeviceTableUploadedBytesSort test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(device.getTableRowsUploadedBytes(),device.getTableHeaderUploadedBytes());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(device.getTableRowsUploadedBytes(),device.getTableHeaderUploadedBytes());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testDeviceTableUploadedBytesSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Last message rows in the device table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 26, description = "Test testDeviceTableLastMessageSort", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableLastMessageSort")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableLastMessageSort() {
		log.info("testDeviceTableLastMessageSort test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(device.getTableRowsLastMessage(),device.getTableHeaderLastMessage());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(device.getTableRowsLastMessage(),device.getTableHeaderLastMessage());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testDeviceTableLastMessageSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Notes rows in the device table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 27, description = "Test testDeviceTableNotesSort", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableNotesSort")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableNotesSort() {
		log.info("testDeviceTableNotesSort test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(device.getTableRowsNotes(),device.getTableHeaderNotes());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(device.getTableRowsNotes(),device.getTableHeaderNotes());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testDeviceTableNotesSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Creation date rows in the device table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 28, description = "Test testDeviceTableCreationDateSort", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableCreationDateSort")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableCreationDateSort() {
		log.info("testDeviceTableNotesSort test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(device.getTableRowsCreationDate(),device.getTableHeaderCreationDate());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(device.getTableRowsCreationDate(),device.getTableHeaderCreationDate());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testDeviceTableCreationDateSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Deleted rows in the device table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 29, description = "Test testDeviceTableDeletedSort", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableDeletedSort")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableDeletedSort() {
		log.info("testDeviceTableDeletedSort test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(device.getTableRowsDeleted(),device.getTableHeaderDeleted());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(device.getTableRowsDeleted(),device.getTableHeaderDeleted());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testDeviceTableDeletedSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Last modified rows in the device table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3811"> SB-3811 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 30, description = "Test testDeviceTableLastModifiedSort", enabled = true)
	@Story("Test Devices Place in UI MX Admin Tests")
	@Description("Test testDeviceTableLastModifiedSort")
	@Severity(SeverityLevel.NORMAL)
	public void testDeviceTableLastModifiedSort() {
		log.info("testDeviceTableLastModifiedSort test is starting... ");
		device.clickDevicesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(device.getTableRowsLastModified(),device.getTableHeaderLastModified());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(device.getTableRowsLastModified(),device.getTableHeaderLastModified());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testDeviceTableLastModifiedSort test is ending... ");
	}
	
	@AfterMethod(groups = "finish")
	public void tearDown() {
		log.info("AfterMethod is starting... ");
		driver.quit();
		log.info("tearDown is ending... ");
	}
	
}
