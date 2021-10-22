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
import com.ert.wearables.test.core.pages.mobilityexchange.admin.AdminLogin;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.AllocationGroup;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.AuditLog;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.Session;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.StudyMembership;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.Subject;
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
 * This class tests Subject component in Mobility Exchange Admin Application
 * 
 * @author yavuz.ozturk
 */
@Epic("SB-1871 - https://apdmwearables.atlassian.net/browse/SB-1871")
@Feature("SB-3812 - https://apdmwearables.atlassian.net/browse/SB-3812")
//@Listeners(ExtentReportListener.class)
public class TestSubject {

	WebDriver driver;
	BaseSetup baseSetup;
	Properties prop;
	AdminUtils adminUtils;
	Credentials userCred;
	ProjectsUtils projectsUtils;
	AdminLogin adminLogin;
	Subject subject;
	AuditLog auditLog;
	Session session;
	Trial trial;
	StudyMembership studyMembership;
	AllocationGroup allocationGroup;
	
	Logger log = Logger.getLogger(TestSite.class);
	
	@BeforeMethod(groups = "start")
	public void setUp() {
		log.info("BeforeMethod is starting...");
		log.info("TestSubject class is starting for tests");
		baseSetup = new BaseSetup();
		prop = baseSetup.init_properties();
		String browserName = prop.getProperty("browser");
		driver = baseSetup.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		projectsUtils = new ProjectsUtils(driver);
		adminUtils = new AdminUtils(driver);
		log.info("url is launched: " + prop.getProperty("url"));
		adminLogin = new AdminLogin(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		adminUtils.doLogin(userCred);
		subject = new Subject(driver);
		auditLog = new AuditLog(driver);
		session = new Session(driver);
		trial = new Trial(driver);
		studyMembership = new StudyMembership(driver);
		allocationGroup = new AllocationGroup(driver);
		log.info("setUp is ending... ");
	}
	
	/**
	 * Test full and compact views for subject component
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 1, description = "Test testFullCompactViewsOnSubject", enabled = true, groups = {"smoke"})
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testFullCompactViewsOnSubject")
	@Severity(SeverityLevel.CRITICAL)
	public void testFullCompactViewsOnSubject() {
		log.info("testFullCompactViewsOnSubject test is starting... ");
		subject.clickSubjectsSideBarButton();
		String fullButtonText = adminUtils.getFullButtonText();
		adminUtils.clickFull();
		String compactText = adminUtils.getCompactText();
		if (compactText.equalsIgnoreCase(compactText)) {
			adminUtils.clickCompact();
		}
		assertEquals(fullButtonText, "Full", "verifying full button text");
		assertEquals(compactText, "Compact", "verifying compact button text");
		log.info("testFullCompactViewsOnSubject test is ending... ");
	}
	
	/**
	 * Test create new subject with (not set) gender selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 2, description = "Test testCreateNewSubjectWithNotSetGender", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testCreateNewSubjectNotSetGender")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewSubjectWithNotSetGender() throws InterruptedException {
		log.info("testCreateNewSubjectWithNotSetGender test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickCreate();
		String publicID = "Test_PublicID_" + projectsUtils.randomString(5);
		String firstName = "Test_FirstName_" + projectsUtils.randomString(5);
		String lastName = "Test_LastName_" + projectsUtils.randomString(5);
		String height = projectsUtils.randomInteger(3);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		subject.createSubject(publicID, firstName, lastName, height, "not set", notes);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedSubjectFirstName = subject.getLastCreatedSubjectNameFromSubjectTable();
		assertEquals(firstName, lastCreatedSubjectFirstName, "verifying the presence of last subject created");
		log.info("testCreateNewSubjectWithNotSetGender test is ending... ");
	}

	/**
	 * Test create new subject with male gender selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 3, description = "Test testCreateNewSubjectWithMaleGender", enabled = true, groups = {"smoke"})
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testCreateNewSubjectWithMaleGender")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewSubjectWithMaleGender() throws InterruptedException {
		log.info("testCreateNewSubjectWithMaleGender test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickCreate();
		String publicID = "Test_PublicID_" + projectsUtils.randomString(5);
		String firstName = "Test_FirstName_" + projectsUtils.randomString(5);
		String lastName = "Test_LastName_" + projectsUtils.randomString(5);
		String height = projectsUtils.randomInteger(3);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		subject.createSubject(publicID, firstName, lastName, height, "Male", notes);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedSubjectFirstName = subject.getLastCreatedSubjectNameFromSubjectTable();
		assertEquals(firstName, lastCreatedSubjectFirstName, "verifying the presence of last subject created");
		log.info("testCreateNewSubjectWithMaleGender test is ending... ");
	}
	
	/**
	 * Test create new subject with female gender selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 4, description = "Test testCreateNewSubjectWithFemaleGender", enabled = true, groups = {"smoke"})
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testCreateNewSubjectWithFemaleGender")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewSubjectWithFemaleGender() throws InterruptedException {
		log.info("testCreateNewSubjectWithFemaleGender test is starting... ");
		TimeUnit.SECONDS.sleep(5);
		subject.clickSubjectsSideBarButton();
		adminUtils.clickCreate();
		String publicID = "Test_PublicID_" + projectsUtils.randomString(5);
		String firstName = "Test_FirstName_" + projectsUtils.randomString(5);
		String lastName = "Test_LastName_" + projectsUtils.randomString(5);
		String height = projectsUtils.randomInteger(3);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		subject.createSubject(publicID, firstName, lastName, height, "Female", notes);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedSubjectFirstName = subject.getLastCreatedSubjectNameFromSubjectTable();
		assertEquals(firstName, lastCreatedSubjectFirstName, "verifying the presence of last subject created");
		log.info("testCreateNewSubjectWithFemaleGender test is ending... ");
	}
	
	/**
	 * Test create new subject with Intersex gender selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 5, description = "Test testCreateNewSubjectWithIntersexGender", enabled = true, groups = {"smoke"})
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testCreateNewSubjectWithIntersexGender")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewSubjectWithIntersexGender() throws InterruptedException {
		log.info("testCreateNewSubjectWithIntersexGender test is starting... ");
		TimeUnit.SECONDS.sleep(5);
		subject.clickSubjectsSideBarButton();
		adminUtils.clickCreate();
		String publicID = "Test_PublicID_" + projectsUtils.randomString(5);
		String firstName = "Test_FirstName_" + projectsUtils.randomString(5);
		String lastName = "Test_LastName_" + projectsUtils.randomString(5);
		String height = projectsUtils.randomInteger(3);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		subject.createSubject(publicID, firstName, lastName, height, "Intersex", notes);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedSubjectFirstName = subject.getLastCreatedSubjectNameFromSubjectTable();
		assertEquals(firstName, lastCreatedSubjectFirstName, "verifying the presence of last subject created");
		log.info("testCreateNewSubjectWithIntersexGender test is ending... ");
	}
	
	/**
	 * Test create new subject with unspecified gender selected
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 6, description = "Test testCreateNewSubjectWithUnspecifiedGender", enabled = true, groups = {"smoke"})
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testCreateNewSubjectWithUnspecifiedGender")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewSubjectWithUnspecifiedGender() throws InterruptedException {
		log.info("testCreateNewSubjectWithUnspecifiedGender test is starting... ");
		TimeUnit.SECONDS.sleep(5);
		subject.clickSubjectsSideBarButton();
		adminUtils.clickCreate();
		String publicID = "Test_PublicID_" + projectsUtils.randomString(5);
		String firstName = "Test_FirstName_" + projectsUtils.randomString(5);
		String lastName = "Test_LastName_" + projectsUtils.randomString(5);
		String height = projectsUtils.randomInteger(3);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		subject.createSubject(publicID, firstName, lastName, height, "Unspecified", notes);
		adminUtils.clickFull();
		adminUtils.navigateToLastPage();
		String lastCreatedSubjectFirstName = subject.getLastCreatedSubjectNameFromSubjectTable();
		assertEquals(firstName, lastCreatedSubjectFirstName, "verifying the presence of last subject created");
		log.info("testCreateNewSubjectWithUnspecifiedGender test is ending... ");
	}
	
	/**
	 * Test subject create with valid web table data
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 7, description = "Test validateSubjectWebTableData", enabled = true, groups = {"smoke"})
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test validateSubjectWebTableData")
	@Severity(SeverityLevel.CRITICAL)
	public void validateSubjectWebTableData() throws InterruptedException {
		log.info("validateSubjectWebTableData test is starting... ");
		TimeUnit.SECONDS.sleep(5);
		subject.clickSubjectsSideBarButton();
		adminUtils.clickCreate();
		Map<String, String> newUserMap = new LinkedHashMap<>();
		String publicID = "Test_PublicID_" + projectsUtils.randomString(5);
		String firstName = "Test_FirstName_" + projectsUtils.randomString(5);
		String lastName = "Test_LastName_" + projectsUtils.randomString(5);
		String height = projectsUtils.randomInteger(3);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		newUserMap.put("PublicID", publicID);
		newUserMap.put("FirstName", firstName);
		newUserMap.put("LastName", lastName);
		newUserMap.put("Height", height);
		newUserMap.put("Notes", notes);
		subject.createSubject(publicID, firstName, lastName, height, "Unspecified", notes);
		adminUtils.clickFull();
		List<Map<String, String>> tableDataList = projectsUtils.getTableDataAsMapList();
		for (int i = 0; i < tableDataList.size(); i++) {
			Map<String, String> eachMap = tableDataList.get(i);
			String newSubjectPublicID = newUserMap.get("PublicID");
			String newFirstName = newUserMap.get("FirstName");
			String newLastName = newUserMap.get("LastName");
			String newHeight = newUserMap.get("Height");
			String newNotes = newUserMap.get("Notes");
			if (eachMap.get("PublicID") != null) {
				if ((eachMap.get("PublicID")).equals(newSubjectPublicID)) {
					assertEquals(eachMap.get("PublicID"), newSubjectPublicID);
					log.info("PublicID from table: " + eachMap.get("PublicID"));
					log.info("PublicID from new subject publicID: " + newUserMap.get("PublicID"));
				}
			}
			if (eachMap.get("FirstName") != null) {
				if ((eachMap.get("FirstName")).equals(newFirstName)) {
					assertEquals(eachMap.get("FirstName"), newFirstName);
				}
			}
			if (eachMap.get("LastName") != null) {
				if ((eachMap.get("LastName")).equals(newLastName)) {
					assertEquals(eachMap.get("LastName"), newLastName);
				}
			}
			if (eachMap.get("Height") != null) {
				if ((eachMap.get("Height")).equals(newHeight)) {
					assertEquals(eachMap.get("Height"), newHeight);
				}
			}
			if (eachMap.get("Notes") != null) {
				if ((eachMap.get("Notes")).equals(newNotes)) {
					assertEquals(eachMap.get("Notes"), newNotes);
				}
			}
		}
		adminUtils.clickCompact();
		log.info("validateSubjectWebTableData test is ending... ");
	}
	
	/**
	 * Test subject history via subject table's Id Link from subject form
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 8, description = "Test testSubjectHistoryViaSubjectTablesIdLink", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectHistoryViaSubjectTablesIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteHistoryViaSiteTableIdLink()  {
		log.info("testSubjectHistoryViaSubjectTablesIdLink test is starting... ");
		subject.clickSubjectsSideBarButton();
		String subjectTableSubjectId = subject.getSubjectTableSubjectId();
		adminUtils.clickTableIdLink();
		String subjectFormSubjectId = subject.getSubjectFormSubjectId();
		adminUtils.clickHistory();
		String auditLogTableObjectId = auditLog.getAuditLogTableObjectId();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("DomainTransformEventInfo"));
		assertEquals(subjectTableSubjectId,auditLogTableObjectId);
		assertEquals(subjectFormSubjectId,auditLogTableObjectId);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testSubjectHistoryViaSubjectTablesIdLink test is ending... ");
	}
	
	/**
	 * Test subject history via subject table's Public ID Link from subject form
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 9, description = "Test testSubjectHistoryViaSubjectTablesPublicIdLink", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectHistoryViaSubjectTablesPublicIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectHistoryViaSubjectTablesPublicIdLink()  {
		log.info("testSubjectHistoryViaSubjectTablesPublicIdLink test is starting... ");
		subject.clickSubjectsSideBarButton();
		String subjectTablePublicId = subject.getSubjectTablePuclicId();
		subject.clickSubjectTablePublicIdLink();
		String subjectFormPublicId = subject.getSubjectFormPublicId();
		String subjectFormStudyName = subject.getSubjectFormStudyName();
		adminUtils.clickHistory();
		adminUtils.clickFull();
		String auditLogTableStudyName = auditLog.getAuditLogTableStudyName();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("DomainTransformEventInfo"));
		assertEquals(subjectTablePublicId,subjectFormPublicId);
		assertEquals(subjectFormStudyName,auditLogTableStudyName);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testSubjectHistoryViaSubjectTablesPublicIdLink test is ending... ");
	}
	
	/**
	 * Test subject history via subject table's Id Link from subject form when editing subject
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk 
	 */
	@Test(priority = 10, description = "Test testSubjectHistoryViaSubjectTablesIdLinkWhenEditingSubject", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectHistoryViaSubjectTablesIdLinkWhenEditingSubject")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectHistoryViaSubjectTablesIdLinkWhenEditingSubject() {
		log.info("testSubjectHistoryViaSubjectTablesIdLinkWhenEditingSubject test is starting... ");
		subject.clickSubjectsSideBarButton();
		String subjectTablePublicId = subject.getSubjectTablePuclicId();
		adminUtils.clickTableIdLink();
		String subjectFormPublicId = subject.getSubjectFormPublicId();
		String subjectFormStudyName = subject.getSubjectFormStudyName();
		adminUtils.clickEditButton();
		adminUtils.clickHistory();
		adminUtils.clickFull();
		String auditLogTableStudyName = auditLog.getAuditLogTableStudyName();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("DomainTransformEventInfo"));
		assertEquals(subjectTablePublicId,subjectFormPublicId);
		assertEquals(subjectFormStudyName,auditLogTableStudyName);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testSubjectHistoryViaSubjectTablesIdLinkWhenEditingSubject test is ending... ");
	}
	
	/**
	 * Test subject history via subject table's Public Id Link from subject form when editing subject
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk 
	 */
	@Test(priority = 11, description = "Test testSubjectHistoryViaSubjectTablesPublicIdLinkWhenEditingSubject", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectHistoryViaSubjectTablesPublicIdLinkWhenEditingSubject")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectHistoryViaSubjectTablesPublicIdLinkWhenEditingSubject() {
		log.info("testSubjectHistoryViaSubjectTablesPublicIdLinkWhenEditingSubject test is starting... ");
		subject.clickSubjectsSideBarButton();
		String subjectTablePublicId = subject.getSubjectTablePuclicId();
		subject.clickSubjectTablePublicIdLink();
		String subjectFormPublicId = subject.getSubjectFormPublicId();
		String subjectFormStudyName = subject.getSubjectFormStudyName();
		adminUtils.clickEditButton();
		adminUtils.clickHistory();
		adminUtils.clickFull();
		String auditLogTableStudyName = auditLog.getAuditLogTableStudyName();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		assertTrue(curentURL.contains("DomainTransformEventInfo"));
		assertEquals(subjectTablePublicId,subjectFormPublicId);
		assertEquals(subjectFormStudyName,auditLogTableStudyName);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testSubjectHistoryViaSubjectTablesPublicIdLinkWhenEditingSubject test is ending... ");
	}
	
	/**
	 * Test subject form's subject deleting with Subject ID link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	@Test(priority = 12, description = "Test testSubjectFormDeleteSubjectWithIdLink", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectFormDeleteSubjectWithIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectFormDeleteSubjectWithIdLink() throws InterruptedException {
		log.info("testSubjectFormDeleteSubjectWithIdLink test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.navigateToLastPage();
		String lastCreatedSubject = subject.getLastCreatedSubjectPublicIdFromSubjectTable();
		log.info("Last Subject Before Delete " + lastCreatedSubject);
		subject.deleteLastCreatedSubjectViaIdLink();
		TimeUnit.SECONDS.sleep(3);
		String lastSubject = subject.getLastCreatedSubjectIdFromSubjectTable();
		log.info("Last Subject After Delete " + lastSubject);
		assertNotEquals(lastCreatedSubject, lastSubject);
		log.info("testSubjectFormDeleteSubjectWithIdLink test is ending... ");
	}
	
	/**
	 * Test subject form's subject deleting with Public ID link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	@Test(priority = 13, description = "Test testSubjectFormDeleteSubjectWithPublicIdLink", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectFormDeleteSubjectWithPublicIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectFormDeleteSubjectWithPublicIdLink() throws InterruptedException {
		log.info("testSubjectFormDeleteSubjectWithPublicIdLink test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.navigateToLastPage();
		String lastCreatedSubject = subject.getLastCreatedSubjectPublicIdFromSubjectTable();
		log.info("Last Subject Before Delete " + lastCreatedSubject);
		subject.deleteLastCreatedSubjectViaPublicIdLink();
		TimeUnit.SECONDS.sleep(3);
		String lastSubject = subject.getLastCreatedSubjectPublicIdFromSubjectTable();
		log.info("Last Site After Delete " + lastSubject);
		assertNotEquals(lastCreatedSubject, lastSubject);
		log.info("testSubjectFormDeleteSubjectWithPublicIdLink test is ending... ");
	}
	
	/**
	 * Test subject edit button via subject id link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 14, description = "Test testSubjectEditButtonViaSubjectIdLink", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test  testSubjectEditButtonViaSubjectIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void  testSubjectEditButtonViaSubjectIdLink() throws InterruptedException {
		log.info("testSubjectEditButtonViaSubjectIdLink test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickTableIdLink();
		adminUtils.clickEditButton();
		String publicID = "Test_PublicID_" + projectsUtils.randomString(5);
		String firstName = "Test_FirstName_" + projectsUtils.randomString(5);
		String lastName = "Test_LastName_" + projectsUtils.randomString(5);
		String height = projectsUtils.randomInteger(3);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		subject.editSubject(publicID, firstName, lastName, height, "Intersex" , notes);
		//String reasonOfChange = "Reason_for_change_" + projectsUtils.randomString(5);
		//adminUtils.enterAreasonForTheseChanges(reasonOfChange);
		String editedSubjectPublicId = subject.getEditedSubjectPublicId();
		assertEquals(editedSubjectPublicId, publicID);
		log.info("testSubjectEditButtonViaSubjectIdLink test is ending... ");
	}
	
	/**
	 * Test subject edit button via Public Id link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 15, description = "Test testSubjectEditButtonViaPublicIdLink", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test  testSubjectEditButtonViaPublicIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void  testSubjectEditButtonViaPublicIdLink() throws InterruptedException {
		log.info("testSubjectEditButtonViaSubjectIdLink test is starting... ");
		subject.clickSubjectsSideBarButton();
		subject.clickSubjectTablePublicIdLink();
		adminUtils.clickEditButton();
		String publicID = "Test_PublicID_" + projectsUtils.randomString(5);
		String firstName = "Test_FirstName_" + projectsUtils.randomString(5);
		String lastName = "Test_LastName_" + projectsUtils.randomString(5);
		String height = projectsUtils.randomInteger(3);
		String notes = "Test_Notes_" + projectsUtils.randomString(5);
		subject.editSubject(publicID, firstName, lastName, height, "Intersex" ,notes);
		//String reasonOfChange = "Reason_for_change_" + projectsUtils.randomString(5);
		//adminUtils.enterAreasonForTheseChanges(reasonOfChange);
		String editedSubjectPublicId = subject.getEditedSubjectPublicId();
		assertEquals(editedSubjectPublicId, publicID);
		log.info("testSubjectEditButtonViaPublicIdLink test is ending... ");
	}
	
	/**
	 * Test subject table's subject id link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 15, description = "Test testSubjectTableSubjectIdLink", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableSubjectIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableSubjectIdLink() {
		log.info("testSubjectTableSubjectIdLink test is starting... ");
		subject.clickSubjectsSideBarButton();
		String subjectTableSubjectId = adminUtils.getTableId();
		String subjectFormSubjectId = adminUtils.getFormId();
		log.info("Subject Table Subject Id: " + subjectTableSubjectId);
		log.info("Subject Form Subject Id: " + subjectFormSubjectId);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		assertTrue(curentURL.endsWith(subjectFormSubjectId));
		assertEquals(subjectTableSubjectId, subjectFormSubjectId);
		log.info("testSubjectTableSubjectIdLink test is ending... ");
	}
	
	/**
	 * Test subject table's Public Id link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 16, description = " Test testSubjectTablePublicIdLink", enabled = true)
	@Story("Test Subject Place in UI MX Admin Tests")
	@Description("Test testSubjectTablePublicIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTablePublicIdLink() {
		log.info("testSubjectTablePublicIdLink test is starting... ");
		subject.clickSubjectsSideBarButton();
		String subjectTablePublicId = subject.getSubjectTablePuclicId();
		subject.clickSubjectTablePublicIdLink();
		String subjectFormPageTitle = subject.getSubjectFormPageTitle();
		String subjectFormPublicId = subject.getSubjectFormPublicId();
		log.info("Subject Table Public Id: " + subjectTablePublicId);
		log.info("Subject Form Page Title: " + subjectFormPageTitle);
		log.info("Public ID: " + subjectFormPublicId);
		assertTrue(subjectTablePublicId.equalsIgnoreCase(subjectFormPageTitle));
		assertTrue(subjectTablePublicId.equalsIgnoreCase(subjectFormPageTitle));
		assertTrue(subjectFormPageTitle.equalsIgnoreCase(subjectFormPublicId));
		log.info("testSubjectTablePublicIdLink test is ending... ");
	}
	
	
	/**
	 * Test subject table's Sessions link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk 
	 */
	@Test(priority = 17, description = "Test testSubjectTableSessionsLink", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableSessionsLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableSessionsLink() {
		log.info("testSubjectTableSessionsLink test is starting... ");
		subject.clickSubjectsSideBarButton();
		int subjectTableSessionsCount = subject.getSubjectTableSessionsCount();
		int sessionTableElementCount = session.getSessionCount();
		log.info("Subject Table Session Count: " + subjectTableSessionsCount);
		log.info("Session Table Element Count: " + sessionTableElementCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String sessionsHeader = session.getSessionsHeader();
		String sessionsPageTitle = session.getSessionsPageTitle();
		assertTrue(curentURL.contains("session"));
		assertEquals(subjectTableSessionsCount, sessionTableElementCount);
		assertEquals(sessionsHeader, AppConstants.SESSIONS_PAGE_HEADER);
		assertEquals(sessionsPageTitle, AppConstants.SESSIONS_PAGE_TITLE);
		log.info("testSubjectTableSessionsLink test is ending... ");
	}
	
	/**
	 * Test subject table's Trials link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk 
	 */
	@Test(priority = 18, description = "Test testSubjectTableTrialsLink", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableTrialsLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableTrialsLink() {
		log.info("testSubjectTableTrialsLink test is starting... ");
		subject.clickSubjectsSideBarButton();
		int subjectTableTrialsCount = subject.getSubjectTableTrialsCount();
		int TrialsTableElementCount = trial.getTrialCount();
		log.info("Subject Table Trials Count: " + subjectTableTrialsCount);
		log.info("Trials Table Element Count: " + TrialsTableElementCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String trialsHeader = trial.getTrialsHeader();
		String trialsPageTitle = trial.getTrialsPageTitle();
		assertTrue(curentURL.contains("trial"));
		assertEquals(subjectTableTrialsCount, TrialsTableElementCount);
		assertEquals(trialsHeader, AppConstants.TRIALS_PAGE_HEADER);
		assertEquals(trialsPageTitle, AppConstants.TRIALS_PAGE_TITLE);
		log.info("testSubjectTableTrialsLink test is ending... ");
	}
	
	/**
	 * Test subject table's Study memberships link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk 
	 */
	@Test(priority = 19, description = "Test testSubjectTableStudyMembershipsLink", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableStudyMembershipsLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableStudyMembershipsLink() {
		log.info("testSubjectTableStudyMembershipsLink test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickFull();
		int subjectTableStudyMembershipsCount = subject.getSubjectTableStudyMembershipsCount();
		int StudyMembershipsTableElementCount = studyMembership.getStudyMembershipsCount();
		log.info("Subject Table Study Memberships Count: " + subjectTableStudyMembershipsCount);
		log.info("Study Memberships Table Element Count: " + StudyMembershipsTableElementCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String studyMembershipsHeader = studyMembership.getStudyMembershipsHeader();
		String studyMemberhsipsPageTitle = studyMembership.getStudyMembershipsPageTitle();
		assertTrue(curentURL.contains("studyMembership"));
		assertEquals(subjectTableStudyMembershipsCount, StudyMembershipsTableElementCount);
		assertEquals(studyMembershipsHeader, AppConstants.STUDYMEMBERSHIPS_PAGE_HEADER);
		assertEquals(studyMemberhsipsPageTitle, AppConstants.STUDYMEMBERSHIPS_PAGE_TITLE);
		log.info("testSubjectTableStudyMembershipsLink test is ending... ");
	}
	
	/**
	 * Test subject table's Device Allocation Groups link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk 
	 */
	@Test(priority = 20, description = "Test testSubjectTableDeviceAllocationGroupsLink", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableDeviceAllocationGroupsLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableDeviceAllocationGroupsLink() {
		log.info("testSubjectTableDeviceAllocationGroupsLink test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickFull();
		int subjectTableDeviceAllocationGroupsCount = subject.getSubjectTableDeviceAllocationGroupsCount();
		int deviceAllocationGroupsTableElementCount = allocationGroup.getDeviceAllocationGroupsCount();
		log.info("Subject Table Device Allocation Group Count: " + subjectTableDeviceAllocationGroupsCount);
		log.info("Device Allocation Group Table Element Count: " + deviceAllocationGroupsTableElementCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String deviceAllocationGroupsHeader = allocationGroup.getDeviceAllocationGroupsHeader();
		String deviceAllocationGroupsPageTitle = allocationGroup.getDeviceAllocationGroupsPageTitle();
		assertTrue(curentURL.contains("allocationGroup"));
		assertEquals(subjectTableDeviceAllocationGroupsCount, deviceAllocationGroupsTableElementCount);
		assertEquals(deviceAllocationGroupsHeader, AppConstants.DEVICEALLOCATIONGROUPS_PAGE_HEADER);
		assertEquals(deviceAllocationGroupsPageTitle, AppConstants.DEVICEALLOCATIONGROUPS_PAGE_TITLE);
		log.info("testSubjectTableDeviceAllocationGroupsLink test is ending... ");
	}
	
	/**
	 * Test sorting of the id rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 21, description = "Test testSubjectTableIdSort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableIdSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableIdSort() {
		log.info("testSubjectTableIdSort test is starting... ");
		subject.clickSubjectsSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(adminUtils.getTableRowsId(), adminUtils.getTableHeaderId());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(adminUtils.getTableRowsId(),adminUtils.getTableHeaderId());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTableIdSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Public ID rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 22, description = "Test testSubjectTablePublicIdSort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTablePublicIdSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTablePublicIdSort() {
		log.info("testSubjectTablePublicIdSort test is starting... ");
		subject.clickSubjectsSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(subject.getTableRowsPublicID(),subject.getTableHeaderPublicID());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(subject.getTableRowsPublicID(),subject.getTableHeaderPublicID());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTablePublicIdSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Sessions rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 23, description = "Test testSubjectTableSessionsSort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableSessionsSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableSessionsSort() {
		log.info("testSubjectTableSessionsSort test is starting... ");
		subject.clickSubjectsSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(subject.getTableRowsSessions(),subject.getTableHeaderSessions());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(subject.getTableRowsSessions(),subject.getTableHeaderSessions());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTableSessionsSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Trials rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 23, description = "Test testSubjectTableTrialsSort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableTrialsSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableTrialsSort() {
		log.info("testSubjectTableTrialsSort test is starting... ");
		subject.clickSubjectsSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(subject.getTableRowsTrials(),subject.getTableHeaderTrials());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(subject.getTableRowsTrials(),subject.getTableHeaderTrials());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTableTrialsSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Study rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 24, description = "Test testSubjectTableStudySort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableStudySort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableStudySort() {
		log.info("testSubjectTableStudySort test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(subject.getTableRowsStudy(),subject.getTableHeaderStudy());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(subject.getTableRowsStudy(),subject.getTableHeaderStudy());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTableStudySort test is ending... ");
	}
	
	/**
	 * Test sorting of the Site rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 25, description = "Test testSubjectTableSiteSort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableSiteSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableSiteSort() {
		log.info("testSubjectTableSiteSort test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(subject.getTableRowsSite(),subject.getTableHeaderSite());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(subject.getTableRowsSite(),subject.getTableHeaderSite());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTableSiteSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Study Memberships rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 26, description = "Test testSubjectTableStudyMembershipsSort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableStudyMembershipsSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableStudyMembershipsSort() {
		log.info("testSubjectTableStudyMembershipsSort test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(subject.getTableRowsStudyMemberships(),subject.getTableHeaderStudyMemberships());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(subject.getTableRowsStudyMemberships(),subject.getTableHeaderStudyMemberships());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTableStudyMembershipsSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Device Allocation Groups rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 27, description = "Test testSubjectTableDeviceAllocationGroupsSort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableDeviceAllocationGroupsSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableDeviceAllocationGroupsSort() {
		log.info("testSubjectTableDeviceAllocationGroupsSort test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(subject.getTableRowsDeviceAllocationGroups(),subject.getTableHeaderDeviceAllocationGroups());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(subject.getTableRowsDeviceAllocationGroups(),subject.getTableHeaderDeviceAllocationGroups());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTableDeviceAllocationGroupsSort test is ending... ");
	}
	
	/**
	 * Test sorting of the First Name rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 28, description = "Test testSubjectTableFirstNameSort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableFirstNameSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableFirstNameSort() {
		log.info("testSubjectTableFirstNameSort test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(subject.getTableRowsFirstName(),subject.getTableHeaderFirstName());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(subject.getTableRowsFirstName(),subject.getTableHeaderFirstName());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTableFirstNameSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Last Name rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 29, description = "Test testSubjectTableLastNameSort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableLastNameSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableLastNameSort() {
		log.info("testSubjectTableFirstNameSort test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(subject.getTableRowsLastName(),subject.getTableHeaderLastName());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(subject.getTableRowsLastName(),subject.getTableHeaderLastName());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTableLastNameSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Height rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 30, description = "Test testSubjectTableHeightSort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableHeightSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableHeightSort() {
		log.info("testSubjectTableHeightSort test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(subject.getTableRowsHeigh(),subject.getTableHeaderHeigh());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(subject.getTableRowsHeigh(),subject.getTableHeaderHeigh());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTableHeightSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Date of Birth rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 31, description = "Test testSubjectTableDateOfBirthSort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableDateOfBirthSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableDateOfBirthSort() {
		log.info("testSubjectTableDateOfBirthSort test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(subject.getTableRowsDoB(),subject.getTableHeaderDoB());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(subject.getTableRowsDoB(),subject.getTableHeaderDoB());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTableDateOfBirthSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Gender rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 32, description = "Test testSubjectTableGenderSort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableDateOfBirthSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableGenderSort() {
		log.info("testSubjectTableGenderSort test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(subject.getTableRowsGender(),subject.getTableHeaderGender());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(subject.getTableRowsGender(),subject.getTableHeaderGender());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTableGenderSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Notes rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 33, description = "Test testSubjectTableNotesSort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableNotesSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableNotesSort() {
		log.info("testSubjectTableNotesSort test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(subject.getTableRowsNotes(),subject.getTableHeaderNotes());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(subject.getTableRowsNotes(),subject.getTableHeaderNotes());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTableNotesSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Creation Date rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 34, description = "Test testSubjectTableCreationDateSort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableCreationDateSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableCreationDateSort() {
		log.info("testSubjectTableCreationDateSort test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(subject.getTableRowsCreationDate(),subject.getTableHeaderCreationDate());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(subject.getTableRowsCreationDate(),subject.getTableHeaderCreationDate());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTableCreationDateSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Deleted rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 35, description = "Test testSubjectTableDeletedSort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableDeletedSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableDeletedSort() {
		log.info("testSubjectTableDeletedSort test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(subject.getTableRowsDeleted(),subject.getTableHeaderDeleted());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(subject.getTableRowsDeleted(),subject.getTableHeaderDeleted());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTableDeletedSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Last Modified rows in the subject table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3812"> SB-3812 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 36, description = "Test testSubjectTableLastModifiedSort", enabled = true)
	@Story("Test Subjects Place in UI MX Admin Tests")
	@Description("Test testSubjectTableLastModifiedSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSubjectTableLastModifiedSort() {
		log.info("testSubjectTableLastModifiedSort test is starting... ");
		subject.clickSubjectsSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(subject.getTableRowsLastModified(),subject.getTableHeaderLastModified());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(subject.getTableRowsLastModified(),subject.getTableHeaderLastModified());
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSubjectTableLastModifiedSort test is ending... ");
	}
	
	@AfterMethod(groups = "finish")
	public void tearDown() {
		log.info("AfterMethod is starting... ");
		driver.quit();
		log.info("tearDown is ending... ");
	}
	
}
