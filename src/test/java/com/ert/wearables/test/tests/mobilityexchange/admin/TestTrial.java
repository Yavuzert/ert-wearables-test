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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ert.wearables.test.core.base.BaseSetup;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.AuditLog;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.Trial;
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
 * This class tests Trial component in Mobility Exchange Admin Application
 * 
 * @author yavuz.ozturk
 */
@Epic("SB-1871 - https://apdmwearables.atlassian.net/browse/SB-1871")
@Feature("SB-3813 - https://apdmwearables.atlassian.net/browse/SB-3813")
//@Listeners(ExtentReportListener.class)
public class TestTrial {
	
	WebDriver driver;
	BaseSetup baseSetup;
	Properties prop;
	AdminUtils adminUtils;
	Credentials userCred;
	Trial trial;
	ProjectsUtils projectsUtils;
	AuditLog auditLog;
	
	Logger log = Logger.getLogger(TestTrial.class);
	
	@BeforeMethod(groups = "start")
	public void setUp() {
		log.info("BeforeMethod is starting...");
		log.info("TestTrial class is starting for tests");
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
		trial = new Trial(driver);
		auditLog = new AuditLog(driver);
		log.info("setUp is ending... ");
	}
	
	/**
	 * Test full and compact views for trial component
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 1, description = "Test testFullCompactViewsOnTrial", enabled = true, groups = {"smoke"})
	@Story("Test Trial Place in UI MX Admin Tests")
	@Description("Test testFullCompactViewsOnTrial")
	@Severity(SeverityLevel.CRITICAL)
	public void testFullCompactViewsOnTrial() {
		log.info("testFullCompactViewsOnTrial test is starting... ");
		trial.clickTrialsSideBarButton();
		String fullButtonText = adminUtils.getFullButtonText();
		adminUtils.clickFull();
		String compactText = adminUtils.getCompactText();
		if (compactText.equalsIgnoreCase(compactText)) {
			adminUtils.clickCompact();
		}
		assertEquals(fullButtonText, "Full", "verifying full button text");
		assertEquals(compactText, "Compact", "verifying compact button text");
		log.info("testFullCompactViewsOnTrial test is ending... ");
	}
	
	/**
	 * Test create new device with (not set) confirmation status selected, (not set) analysis status selected, (not set) recording type selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 2, description = "Test testCreateNewDeviceWithNotSetConfirmationStatus", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test Trial Place in UI MX Admin Tests")
	@Description("Test testCreateNewDeviceWithNotSetConfirmationStatus")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewDeviceWithNotSetConfirmationStatus() throws InterruptedException {
		log.info("testCreateNewDeviceWithNotSetConfirmationStatus test is starting... ");
		trial.clickTrialsSideBarButton();
		adminUtils.clickCreate();
		String analysisVersion = projectsUtils.randomInteger(5);
		String conditionName = "Test_ConditionName_" + projectsUtils.randomString(5);
		String confirmationStatusString = "Test_ConfirmationStatusString_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		String trLog = "Test_Log_" + projectsUtils.randomString(5);
		String timezone = "Test_Timezone_" + projectsUtils.randomString(5);
		trial.createTrial(analysisVersion, conditionName, "(not set)", confirmationStatusString, "(not set)", "(not set)", notes, trLog, timezone);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedTrialAnalysisVersion = trial.getLastCreatedTrialAnalysisVersionFromTrialTable();
		System.out.println(lastCreatedTrialAnalysisVersion);
		assertEquals(analysisVersion, lastCreatedTrialAnalysisVersion, "verifying the presence of last trial created");
		log.info("testCreateNewDeviceWithNotSetConfirmationStatus test is ending... ");
	}
	
	/**
	 * Test create new device with Unset confirmation status selected, Not analyzed analysis status selected, Clinic recording type selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 3, description = "Test testCreateNewDeviceWithUnsetConfirmationStatus", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test Trial Place in UI MX Admin Tests")
	@Description("Test testCreateNewDeviceWithUnsetConfirmationStatus")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewDeviceWithUnsetConfirmationStatus() throws InterruptedException {
		log.info("testCreateNewDeviceWithUnsetConfirmationStatus test is starting... ");
		trial.clickTrialsSideBarButton();
		adminUtils.clickCreate();
		String analysisVersion = projectsUtils.randomInteger(5);
		String conditionName = "Test_ConditionName_" + projectsUtils.randomString(5);
		String confirmationStatusString = "Test_ConfirmationStatusString_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		String trLog = "Test_Log_" + projectsUtils.randomString(5);
		String timezone = "Test_Timezone_" + projectsUtils.randomString(5);
		trial.createTrial(analysisVersion, conditionName, "Unset", confirmationStatusString, "Not analyzed", "Clinic", notes, trLog, timezone);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedTrialAnalysisVersion = trial.getLastCreatedTrialAnalysisVersionFromTrialTable();
		System.out.println(lastCreatedTrialAnalysisVersion);
		assertEquals(analysisVersion, lastCreatedTrialAnalysisVersion, "verifying the presence of last trial created");
		log.info("testCreateNewDeviceWithUnsetConfirmationStatus test is ending... ");
	}
	
	/**
	 * Test create new device with Not recorded confirmation status selected, Valid analysis status selected, Virtual recording type selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 4, description = "Test testCreateNewDeviceWithNotRecordedConfirmationStatus", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test Trial Place in UI MX Admin Tests")
	@Description("Test testCreateNewDeviceWithNotRecordedConfirmationStatus")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewDeviceWithNotRecordedConfirmationStatus() throws InterruptedException {
		log.info("testCreateNewDeviceWithNotRecordedConfirmationStatus test is starting... ");
		trial.clickTrialsSideBarButton();
		adminUtils.clickCreate();
		String analysisVersion = projectsUtils.randomInteger(5);
		String conditionName = "Test_ConditionName_" + projectsUtils.randomString(5);
		String confirmationStatusString = "Test_ConfirmationStatusString_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		String trLog = "Test_Log_" + projectsUtils.randomString(5);
		String timezone = "Test_Timezone_" + projectsUtils.randomString(5);
		trial.createTrial(analysisVersion, conditionName, "Not recorded", confirmationStatusString, "Valid", "Virtual", notes, trLog, timezone);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedTrialAnalysisVersion = trial.getLastCreatedTrialAnalysisVersionFromTrialTable();
		System.out.println(lastCreatedTrialAnalysisVersion);
		assertEquals(analysisVersion, lastCreatedTrialAnalysisVersion, "verifying the presence of last trial created");
		log.info("testCreateNewDeviceWithNotRecordedConfirmationStatus test is ending... ");
	}
	
	/**
	 * Test create new device with Unconfirmed confirmation status selected, Warning analysis status selected, Home recording type selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 5, description = "Test testCreateNewDeviceWithUnconfirmedConfirmationStatus", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test Trial Place in UI MX Admin Tests")
	@Description("Test testCreateNewDeviceWithUnconfirmedConfirmationStatus")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewDeviceWithUnconfirmedConfirmationStatus() throws InterruptedException {
		log.info("testCreateNewDeviceWithUnconfirmedConfirmationStatus test is starting... ");
		trial.clickTrialsSideBarButton();
		adminUtils.clickCreate();
		String analysisVersion = projectsUtils.randomInteger(5);
		String conditionName = "Test_ConditionName_" + projectsUtils.randomString(5);
		String confirmationStatusString = "Test_ConfirmationStatusString_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		String trLog = "Test_Log_" + projectsUtils.randomString(5);
		String timezone = "Test_Timezone_" + projectsUtils.randomString(5);
		trial.createTrial(analysisVersion, conditionName, "Unconfirmed", confirmationStatusString, "Warning", "Home", notes, trLog, timezone);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedTrialAnalysisVersion = trial.getLastCreatedTrialAnalysisVersionFromTrialTable();
		System.out.println(lastCreatedTrialAnalysisVersion);
		assertEquals(analysisVersion, lastCreatedTrialAnalysisVersion, "verifying the presence of last trial created");
		log.info("testCreateNewDeviceWithUnconfirmedConfirmationStatus test is ending... ");
	}
	
	/**
	 * Test create new device with Keep confirmation status selected, Valid analysis status selected, Clinic recording type selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 6, description = "Test testCreateNewDeviceWithKeepConfirmationStatus", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test Trial Place in UI MX Admin Tests")
	@Description("Test testCreateNewDeviceWithKeepConfirmationStatus")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewDeviceWithKeepConfirmationStatus() throws InterruptedException {
		log.info("testCreateNewDeviceWithKeepConfirmationStatus test is starting... ");
		trial.clickTrialsSideBarButton();
		adminUtils.clickCreate();
		String analysisVersion = projectsUtils.randomInteger(5);
		String conditionName = "Test_ConditionName_" + projectsUtils.randomString(5);
		String confirmationStatusString = "Test_ConfirmationStatusString_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		String trLog = "Test_Log_" + projectsUtils.randomString(5);
		String timezone = "Test_Timezone_" + projectsUtils.randomString(5);
		trial.createTrial(analysisVersion, conditionName, "Keep", confirmationStatusString, "Valid", "Clinic", notes, trLog, timezone);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedTrialAnalysisVersion = trial.getLastCreatedTrialAnalysisVersionFromTrialTable();
		System.out.println(lastCreatedTrialAnalysisVersion);
		assertEquals(analysisVersion, lastCreatedTrialAnalysisVersion, "verifying the presence of last trial created");
		log.info("testCreateNewDeviceWithKeepConfirmationStatus test is ending... ");
	}
	
	/**
	 * Test create new device with Keep with error confirmation status selected, Warning analysis status selected, Home recording type selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 7, description = "Test testCreateNewDeviceWithKeepWithErrorConfirmationStatus", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test Trial Place in UI MX Admin Tests")
	@Description("Test testCreateNewDeviceWithKeepWithErrorConfirmationStatus")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewDeviceWithKeepWithErrorConfirmationStatus() throws InterruptedException {
		log.info("testCreateNewDeviceWithKeepWithErrorConfirmationStatus test is starting... ");
		trial.clickTrialsSideBarButton();
		adminUtils.clickCreate();
		String analysisVersion = projectsUtils.randomInteger(5);
		String conditionName = "Test_ConditionName_" + projectsUtils.randomString(5);
		String confirmationStatusString = "Test_ConfirmationStatusString_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		String trLog = "Test_Log_" + projectsUtils.randomString(5);
		String timezone = "Test_Timezone_" + projectsUtils.randomString(5);
		trial.createTrial(analysisVersion, conditionName, "Keep with error", confirmationStatusString, "Warning", "Home", notes, trLog, timezone);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedTrialAnalysisVersion = trial.getLastCreatedTrialAnalysisVersionFromTrialTable();
		System.out.println(lastCreatedTrialAnalysisVersion);
		assertEquals(analysisVersion, lastCreatedTrialAnalysisVersion, "verifying the presence of last trial created");
		log.info("testCreateNewDeviceWithKeepWithErrorConfirmationStatus test is ending... ");
	}
	
	/**
	 * Test create new device with Keep and redo confirmation status selected, Error analysis status selected, Virtual recording type selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 8, description = "Test testCreateNewDeviceWithKeepAndRedoConfirmationStatus", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test Trial Place in UI MX Admin Tests")
	@Description("Test testCreateNewDeviceWithKeepAndRedoConfirmationStatus")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewDeviceWithKeepAndRedoConfirmationStatus() throws InterruptedException {
		log.info("testCreateNewDeviceWithKeepAndRedoConfirmationStatus test is starting... ");
		trial.clickTrialsSideBarButton();
		adminUtils.clickCreate();
		String analysisVersion = projectsUtils.randomInteger(5);
		String conditionName = "Test_ConditionName_" + projectsUtils.randomString(5);
		String confirmationStatusString = "Test_ConfirmationStatusString_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		String trLog = "Test_Log_" + projectsUtils.randomString(5);
		String timezone = "Test_Timezone_" + projectsUtils.randomString(5);
		trial.createTrial(analysisVersion, conditionName, "Keep and redo", confirmationStatusString, "Error", "Virtual", notes, trLog, timezone);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedTrialAnalysisVersion = trial.getLastCreatedTrialAnalysisVersionFromTrialTable();
		System.out.println(lastCreatedTrialAnalysisVersion);
		assertEquals(analysisVersion, lastCreatedTrialAnalysisVersion, "verifying the presence of last trial created");
		log.info("testCreateNewDeviceWithKeepAndRedoConfirmationStatus test is ending... ");
	}
	
	/**
	 * Test create new device with Delete and redo confirmation status selected, Not analyzed analysis status selected, (not set) recording type selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 9, description = "Test testCreateNewDeviceWithDeleteAndRedoConfirmationStatus", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test Trial Place in UI MX Admin Tests")
	@Description("Test testCreateNewDeviceWithDeleteAndRedoConfirmationStatus")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewDeviceWithDeleteAndRedoConfirmationStatus() throws InterruptedException {
		log.info("testCreateNewDeviceWithDeleteAndRedoConfirmationStatus test is starting... ");
		trial.clickTrialsSideBarButton();
		adminUtils.clickCreate();
		String analysisVersion = projectsUtils.randomInteger(5);
		String conditionName = "Test_ConditionName_" + projectsUtils.randomString(5);
		String confirmationStatusString = "Test_ConfirmationStatusString_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		String trLog = "Test_Log_" + projectsUtils.randomString(5);
		String timezone = "Test_Timezone_" + projectsUtils.randomString(5);
		trial.createTrial(analysisVersion, conditionName, "Delete and redo", confirmationStatusString, "Not analyzed", "(not set)", notes, trLog, timezone);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedTrialAnalysisVersion = trial.getLastCreatedTrialAnalysisVersionFromTrialTable();
		System.out.println(lastCreatedTrialAnalysisVersion);
		assertEquals(analysisVersion, lastCreatedTrialAnalysisVersion, "verifying the presence of last trial created");
		log.info("testCreateNewDeviceWithDeleteAndRedoConfirmationStatus test is ending... ");
	}
	
	/**
	 * Test create new device with Skip confirmation status selected, Warning analysis status selected, Clinic recording type selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 10, description = "Test testCreateNewDeviceWithSkipConfirmationStatus", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test Trial Place in UI MX Admin Tests")
	@Description("Test testCreateNewDeviceWithSkipConfirmationStatus")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewDeviceWithSkipConfirmationStatus() throws InterruptedException {
		log.info("testCreateNewDeviceWithSkipConfirmationStatus test is starting... ");
		trial.clickTrialsSideBarButton();
		adminUtils.clickCreate();
		String analysisVersion = projectsUtils.randomInteger(5);
		String conditionName = "Test_ConditionName_" + projectsUtils.randomString(5);
		String confirmationStatusString = "Test_ConfirmationStatusString_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		String trLog = "Test_Log_" + projectsUtils.randomString(5);
		String timezone = "Test_Timezone_" + projectsUtils.randomString(5);
		trial.createTrial(analysisVersion, conditionName, "Skip", confirmationStatusString, "Warning", "Clinic", notes, trLog, timezone);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedTrialAnalysisVersion = trial.getLastCreatedTrialAnalysisVersionFromTrialTable();
		System.out.println(lastCreatedTrialAnalysisVersion);
		assertEquals(analysisVersion, lastCreatedTrialAnalysisVersion, "verifying the presence of last trial created");
		log.info("testCreateNewDeviceWithSkipConfirmationStatus test is ending... ");
	}
	
	/**
	 * Test create new device with Delete and skip confirmation status selected, (not set) analysis status selected, Home recording type selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 11, description = "Test testCreateNewDeviceWithDeleteAndSkipConfirmationStatus", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test Trial Place in UI MX Admin Tests")
	@Description("Test testCreateNewDeviceWithDeleteAndSkipConfirmationStatus")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewDeviceWithDeleteAndSkipConfirmationStatus() throws InterruptedException {
		log.info("testCreateNewDeviceWithDeleteAndSkipConfirmationStatus test is starting... ");
		trial.clickTrialsSideBarButton();
		adminUtils.clickCreate();
		String analysisVersion = projectsUtils.randomInteger(5);
		String conditionName = "Test_ConditionName_" + projectsUtils.randomString(5);
		String confirmationStatusString = "Test_ConfirmationStatusString_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		String trLog = "Test_Log_" + projectsUtils.randomString(5);
		String timezone = "Test_Timezone_" + projectsUtils.randomString(5);
		trial.createTrial(analysisVersion, conditionName, "Delete and skip", confirmationStatusString, "(not set)", "Home", notes, trLog, timezone);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedTrialAnalysisVersion = trial.getLastCreatedTrialAnalysisVersionFromTrialTable();
		System.out.println(lastCreatedTrialAnalysisVersion);
		assertEquals(analysisVersion, lastCreatedTrialAnalysisVersion, "verifying the presence of last trial created");
		log.info("testCreateNewDeviceWithDeleteAndSkipConfirmationStatustest is ending... ");
	}
	
	/**
	 * Test trial create with valid web table data
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 12, description = "Test validateTrialWebTableData", enabled = true, groups = {"smoke"})
	@Story("Test Trials Place in UI MX Admin Tests")
	@Description("Test validateTrialWebTableData")
	@Severity(SeverityLevel.CRITICAL)
	public void validateTrialWebTableData() throws InterruptedException {
		log.info("validateTrialWebTableData test is starting... ");
		TimeUnit.SECONDS.sleep(5);
		trial.clickTrialsSideBarButton();
		adminUtils.clickCreate();
		Map<String, String> newUserMap = new LinkedHashMap<>();
		String analysisVersion = projectsUtils.randomInteger(5);
		String conditionName = "Test_ConditionName_" + projectsUtils.randomString(5);
		String confirmationStatusString = "Test_ConfirmationStatusString_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		String trLog = "Test_Log_" + projectsUtils.randomString(5);
		String timezone = "Test_Timezone_" + projectsUtils.randomString(5);
		newUserMap.put("AnalysisVersion", analysisVersion);
		newUserMap.put("ConditionName", conditionName);
		newUserMap.put("ConfirmationStatusString", confirmationStatusString);
		newUserMap.put("Notes", notes);
		newUserMap.put("TrLog", trLog);
		newUserMap.put("Timezone", timezone);
		trial.createTrial(analysisVersion, conditionName, "Unset", confirmationStatusString, "Valid", "Clinic", notes, trLog, timezone);
		adminUtils.clickFull();
		List<Map<String, String>> tableDataList = projectsUtils.getTableDataAsMapList();
		for (int i = 0; i < tableDataList.size(); i++) {
			Map<String, String> eachMap = tableDataList.get(i);
			String newAnalysisVersion = newUserMap.get("AnalysisVersion");
			String newConditionName = newUserMap.get("ConditionName");
			String newConfirmationStatusString = newUserMap.get("ConfirmationStatusString");
			String newNotes = newUserMap.get("Notes");
			String newTrLog = newUserMap.get("TrLog");
			String newTimezone = newUserMap.get("Timezone");
			if (eachMap.get("PublicID") != null) {
				if ((eachMap.get("PublicID")).equals(newAnalysisVersion)) {
					assertEquals(eachMap.get("PublicID"), newAnalysisVersion);
					log.info("AnalysisVersion from table: " + eachMap.get("AnalysisVersion"));
					log.info("AnalysisVersion from new trial AnalysisVersion: " + newUserMap.get("AnalysisVersion"));
				}
			}
			if (eachMap.get("ConfirmationStatusString") != null) {
				if ((eachMap.get("ConfirmationStatusString")).equals(newConditionName)) {
					assertEquals(eachMap.get("ConfirmationStatusString"), newConditionName);
				}
			}
			if (eachMap.get("LastName") != null) {
				if ((eachMap.get("LastName")).equals(newConfirmationStatusString)) {
					assertEquals(eachMap.get("LastName"), newConfirmationStatusString);
				}
			}
			if (eachMap.get("Notes") != null) {
				if ((eachMap.get("Notes")).equals(newNotes)) {
					assertEquals(eachMap.get("Notes"), newNotes);
				}
			}
			if (eachMap.get("TrLog") != null) {
				if ((eachMap.get("TrLog")).equals(newTrLog)) {
					assertEquals(eachMap.get("TrLog"), newTrLog);
				}
			}
			if (eachMap.get("Timezone") != null) {
				if ((eachMap.get("Timezone")).equals(newTimezone)) {
					assertEquals(eachMap.get("Timezone"), newTimezone);
				}
			}
		}
		adminUtils.clickCompact();
		log.info("validateTrialWebTableData test is ending... ");
	}
	
	/**
	 * Test trial history via trial table's Id Link from trial form
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 13, description = "Test testTrialHistoryViaTrialTablesIdLink", enabled = true)
	@Story("Test Trial Place in UI MX Admin Tests")
	@Description("Test testTrialHistoryViaTrialTablesIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testTrialHistoryViaTrialTablesIdLink()  {
		log.info("testTrialHistoryViaTrialTablesIdLink test is starting... ");
		trial.clickTrialsSideBarButton();
		String trialTableId = trial.getTrialTableId();
		adminUtils.clickTableIdLink();
		String trialFormTrialId = trial.getTrialFormId();
		adminUtils.clickHistory();
		String auditLogTableObjectId = auditLog.getAuditLogTableObjectId();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("audit.log"));
		assertEquals(trialTableId,auditLogTableObjectId);
		assertEquals(trialFormTrialId,auditLogTableObjectId);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testTrialHistoryViaTrialTablesIdLink test is ending... ");
	}
	
	/**
	 * Test trial history via trial table's Test Date Link from trial form
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 14, description = "Test testTrialHistoryViaTrialTablesTestDateLink", enabled = true)
	@Story("Test Trial Place in UI MX Admin Tests")
	@Description("Test testTrialHistoryViaTrialTablesTestDateLink")
	@Severity(SeverityLevel.NORMAL)
	public void testTrialHistoryViaTrialTablesTestDateLink()  {
		log.info("testTrialHistoryViaTrialTablesTestDateLink test is starting... ");
		trial.clickTrialsSideBarButton();
		//String trialTableTestDate = trial.getTrialTableTestDate();
		trial.clickTrialTableTestDateLink();
		//String trialFormTestDate = trial.getTrialFormTestDate();
		adminUtils.clickHistory();
		adminUtils.clickFull();
		//String auditLogTableDate = auditLog.getAuditLogTableDate();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("audit.log"));
		//assertEquals(trialTableTestDate,trialFormTestDate);
		//assertEquals(trialFormTestDate,auditLogTableDate);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testTrialHistoryViaTrialTablesTestDateLink test is ending... ");
	}
	
	/**
	 * Test Trial form's trial deleting with table ID link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	@Test(priority = 15, description = "Test testTrialFormDeleteTrialWithIdLink", enabled = true)
	@Story("Test Trial Place in UI MX Admin Tests")
	@Description("Test testTrialFormDeleteTrialWithIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testTrialFormDeleteTrialWithIdLink() throws InterruptedException {
		log.info("testTrialFormDeleteTrialWithIdLink test is starting... ");
		trial.clickTrialsSideBarButton();
		adminUtils.navigateToLastPage();
		String lastCreatedTrial = trial.getLastCreatedTestDateFromTrialTable();
		log.info("Last Trial Before Delete " + lastCreatedTrial);
		trial.deleteLastCreatedTrialViaIdLink();
		TimeUnit.SECONDS.sleep(3);
		String lastTrial = trial.getLastCreatedTestDateFromTrialTable();
		log.info("Last Trial After Delete " + lastTrial);
		assertNotEquals(lastCreatedTrial, lastTrial);
		log.info("testTrialFormDeleteTrialWithIdLink test is ending... ");
	}
	
	/**
	 * Test Trial form's trial deleting with Test Date link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	@Test(priority = 16, description = "Test testTrialFormDeleteTrialWithTestDateLink", enabled = true)
	@Story("Test Trial Place in UI MX Admin Tests")
	@Description("Test testTrialFormDeleteTrialWithTestDateLink")
	@Severity(SeverityLevel.NORMAL)
	public void testTrialFormDeleteTrialWithTestDateLink() throws InterruptedException {
		log.info("testTrialFormDeleteTrialWithTestDateLink test is starting... ");
		trial.clickTrialsSideBarButton();
		adminUtils.navigateToLastPage();
		String lastCreatedTrial = trial.getLastCreatedTestDateFromTrialTable();
		log.info("Last Trial Before Delete " + lastCreatedTrial);
		trial.deleteLastCreatedTrialViaTestDateLink();
		TimeUnit.SECONDS.sleep(3);
		String lastTrial = trial.getLastCreatedTestDateFromTrialTable();
		log.info("Last Trial After Delete " + lastTrial);
		assertNotEquals(lastCreatedTrial, lastTrial);
		log.info("testTrialFormDeleteTrialWithTestDateLink test is ending... ");
	}
	
	/**
	 * Test Trial edit button via id link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3813"> SB-3813 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 17, description = "Test testTrialEditButtonViaIdLink", enabled = true)
	@Story("Test Trial Place in UI MX Admin Tests")
	@Description("Test  testTrialEditButtonViaIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void  testTrialEditButtonViaIdLink() throws InterruptedException {
		log.info("testTrialEditButtonViaIdLink test is starting... ");
		trial.clickTrialsSideBarButton();
		adminUtils.clickTableIdLink();
		adminUtils.clickEditButton();
		String analysisVersion = projectsUtils.randomInteger(5);
		String conditionName = "Test_ConditionName_" + projectsUtils.randomString(5);
		String confirmationStatusString = "Test_ConfirmationStatusString_" + projectsUtils.randomString(5);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		String trLog = "Test_Log_" + projectsUtils.randomString(5);
		String timezone = "Test_Timezone_" + projectsUtils.randomString(5);
		trial.editTrial(analysisVersion, conditionName, "Keep with error", confirmationStatusString, "Warning", "Home", notes, trLog, timezone);
		String editedTestDate = trial.getEditedAnalysisVersion();
		assertEquals(editedTestDate, analysisVersion);
		log.info("testTrialEditButtonViaIdLink test is ending... ");
	}
	
	@AfterMethod(groups = "finish")
	public void tearDown() {
		log.info("AfterMethod is starting... ");
		driver.quit();
		log.info("tearDown is ending... ");
	}

}
