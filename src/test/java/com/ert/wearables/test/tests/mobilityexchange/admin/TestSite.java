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
import com.ert.wearables.test.core.pages.mobilityexchange.admin.AuditLog;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.Subject;
import com.ert.wearables.test.core.pages.mobilityexchange.admin.Site;
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
 * This class tests Site component in Mobility Exchange Admin Application
 * 
 * @author yavuz.ozturk
 */
@Epic("SB-1871 - https://apdmwearables.atlassian.net/browse/SB-1871")
@Feature("SB-3810 - https://apdmwearables.atlassian.net/browse/SB-3810")
//@Listeners(ExtentReportListener.class)
public class TestSite {
	
	WebDriver driver;
	BaseSetup baseSetup;
	Properties prop;
	AdminLogin adminLogin;
	Credentials userCred;
	AdminUtils adminUtils;
	Site site;
	ProjectsUtils projectsUtils;
	AuditLog auditLog;
	Subject subject;
	
	Logger log = Logger.getLogger(TestSite.class);
	
	@BeforeMethod(groups = "start")
	public void setUp() {
		log.info("BeforeMethod is starting...");
		log.info("TestSite class is starting for tests");
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
		site = new Site(driver);
		auditLog = new AuditLog(driver);
		subject = new Subject(driver);
		log.info("setUp is ending... ");
	}
	
	/**
	 * Test full and compact views for site component
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 1, description = "Test testFullCompactViewsOnSite", enabled = true, groups = {"smoke"})
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testFullCompactViewsOnSite")
	@Severity(SeverityLevel.CRITICAL)
	public void testFullCompactViewsOnSite() {
		log.info("testFullCompactViewsOnSite test is starting... ");
		site.clickSitesSideBarButton();
		String fullButtonText = adminUtils.getFullButtonText();
		adminUtils.clickFull();
		String compactText = adminUtils.getCompactText();
		if (compactText.equalsIgnoreCase(compactText)) {
			adminUtils.clickCompact();
		}
		assertEquals(fullButtonText, "Full", "verifying full button text");
		assertEquals(compactText, "Compact", "verifying compact button text");
		log.info("testFullCompactViewsOnSite test is ending... ");
	}
	
	/**
	 * Test create new site
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 2, description = "Test testCreateNewSite", invocationCount = 1, enabled = true, groups = {"smoke"})
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testCreateNewSite")
	@Severity(SeverityLevel.CRITICAL)
	public void testCreateNewSite() throws InterruptedException {
		log.info("testCreateNewSite test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickCreate();

		String name = "Test_name_" + projectsUtils.generateAlphaNumericString(5);
		String addressLine1 = "Test_AddressLine1_" + projectsUtils.generateAlphaNumericString(10);
		String addressLine2 = "Test_AddressLine2_" + projectsUtils.generateAlphaNumericString(10);
		String city = "Test_City_" + projectsUtils.generateAlphaNumericString(5);
		String state = "Test_State_" + projectsUtils.generateAlphaNumericString(5);
		String guid = "Test_GUID_" + projectsUtils.generateAlphaNumericString(5);
		String country = "Test_Country_" + projectsUtils.generateAlphaNumericString(5);
		String phone = "Test_Phone_" + projectsUtils.generateAlphaNumericString(5);
		String zip = "Test_Zip_" + projectsUtils.generateAlphaNumericString(5);
		String geolocation = "Test_Geolocation_" + projectsUtils.generateAlphaNumericString(5);
		String notes = "Test_Notes_" + projectsUtils.generateAlphaNumericString(5);
		site.createSite(name, addressLine1, addressLine2, city, state, guid, country, phone, zip, geolocation, notes);
		adminUtils.navigateToLastPage();
		String lastCreatedObjectName = site.getLastCreatedSiteNameFromSiteTable();
		
		assertEquals(name, lastCreatedObjectName, "verifying the presence of last site created");
		log.info("testCreateNewSite test is ending... ");
	}
	
	/**
	 * Test sites create with valid web table data
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 3, description = "Test validateSiteWebTableData", enabled = true, groups = {"smoke"})
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test validateSiteWebTableData")
	@Severity(SeverityLevel.CRITICAL)
	public void testSiteWebTableData() throws InterruptedException {
		log.info("testSiteWebTableData test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickCreate();
		
		Map<String, String> newUserMap = new LinkedHashMap<>();
		String name = "Test_name_" + projectsUtils.generateAlphaNumericString(5);
		String addressLine1 = "Test_AddressLine1_" + projectsUtils.generateAlphaNumericString(10);
		String addressLine2 = "Test_AddressLine2_" + projectsUtils.generateAlphaNumericString(10);
		String city = "Test_City_" + projectsUtils.generateAlphaNumericString(5);
		String state = "Test_State_" + projectsUtils.generateAlphaNumericString(5);
		String guid = "Test_GUID_" + projectsUtils.generateAlphaNumericString(5);
		String country = "Test_Country_" + projectsUtils.generateAlphaNumericString(5);
		String phone = "Test_Phone_" + projectsUtils.generateAlphaNumericString(5);
		String zip = "Test_Zip_" + projectsUtils.generateAlphaNumericString(5);
		String geolocation = "Test_Geolocation_" + projectsUtils.generateAlphaNumericString(5);
		String notes = "Test_Notes_" + projectsUtils.generateAlphaNumericString(5);
		newUserMap.put("Name", name);
		newUserMap.put("AddressLineFirst", addressLine1);
		newUserMap.put("AddressLineSecond", addressLine2);
		newUserMap.put("City", city);
		newUserMap.put("State", state);
		newUserMap.put("GUID", guid);
		newUserMap.put("Country", country);
		newUserMap.put("Phone", phone);
		newUserMap.put("Zip", zip);
		newUserMap.put("Geolocation", geolocation);
		newUserMap.put("Notes", notes);
		site.createSite(name, addressLine1, addressLine2, city, state, guid, country, phone, zip, geolocation, notes);
		
		adminUtils.clickFull();

		List<Map<String, String>> tableDataList = projectsUtils.getTableDataAsMapList();
		for (int i = 0; i < tableDataList.size(); i++) {
			Map<String, String> eachMap = tableDataList.get(i);
			String newSiteNameString = newUserMap.get("Name");
			String newAddressLineFirstString = newUserMap.get("AddressLineFirst");
			String newAddressLineSecondString = newUserMap.get("AddressLineSecond");
			String newCityString = newUserMap.get("City");
			String newStateString = newUserMap.get("State");
			String newSiteGuidString = newUserMap.get("GUID");
			String newCountryString = newUserMap.get("Country");
			String newPhoneString = newUserMap.get("Phone");
			String newZipString = newUserMap.get("Zip");
			String newGeolocationString = newUserMap.get("Geolocation");
			String newNotesString = newUserMap.get("Notes");
			
			if (eachMap.get("Name") != null) {
				if ((eachMap.get("Name")).equals(newSiteNameString)) {
					assertEquals(eachMap.get("Name"), newSiteNameString);
					log.info("Name from table: " + eachMap.get("Name"));
					log.info("Name from new site name: " + newUserMap.get("Name"));
				}
			}
			if (eachMap.get("AddressLineFirst") != null) {
				if ((eachMap.get("AddressLineFirst")).equals(newAddressLineFirstString)) {
					assertEquals(eachMap.get("AddressLineFirst"), newAddressLineFirstString);
				}
			}
			if (eachMap.get("AddressLineSecond") != null) {
				if ((eachMap.get("AddressLineSecond")).equals(newAddressLineSecondString)) {
					assertEquals(eachMap.get("AddressLineSecond"), newAddressLineSecondString);
				}
			}
			if (eachMap.get("City") != null) {
				if ((eachMap.get("City")).equals(newCityString)) {
					assertEquals(eachMap.get("City"), newCityString);
				}
			}
			if (eachMap.get("State") != null) {
				if ((eachMap.get("State")).equals(newStateString)) {
					assertEquals(eachMap.get("State"), newStateString);
				}
			}
			if (eachMap.get("GUID") != null) {
				if ((eachMap.get("GUID")).equals(newSiteGuidString)) {
					assertEquals(eachMap.get("GUID"), newSiteGuidString);
				}
			}
			if (eachMap.get("Country") != null) {
				if ((eachMap.get("Country")).equals(newCountryString)) {
					assertEquals(eachMap.get("Country"), newCountryString);
				}
			}
			if (eachMap.get("Phone") != null) {
				if ((eachMap.get("Phone")).equals(newPhoneString)) {
					assertEquals(eachMap.get("Phone"), newPhoneString);
				}
			}
			if (eachMap.get("Zip") != null) {
				if ((eachMap.get("Zip")).equals(newZipString)) {
					assertEquals(eachMap.get("Zip"), newZipString);
				}
			}
			if (eachMap.get("Geolocation") != null) {
				if ((eachMap.get("Geolocation")).equals(newGeolocationString)) {
					assertEquals(eachMap.get("Geolocation"), newGeolocationString);
				}
			}
			if (eachMap.get("Notes") != null) {
				if ((eachMap.get("Notes")).equals(newNotesString)) {
					assertEquals(eachMap.get("Notes"), newNotesString);
				}
			}
		}
		adminUtils.clickCompact();
		log.info("testSiteWebTableData test is ending... ");
	}

	/**
	 * Test site history via site table's Id Link from site form
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 4, description = "Test testSiteHistoryViaSiteTablesIdLink", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteHistoryViaSiteTableIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteHistoryViaSiteTableIdLink()  {
		log.info("testSiteHistoryViaSiteTableIdLink test is starting... ");
		site.clickSitesSideBarButton();
		String siteTableSiteId = site.getSiteTableSiteId();
		adminUtils.clickTableIdLink();
		String siteFormSiteId = site.getSiteFormSiteId();
		
		adminUtils.clickHistory();
		
		String auditLogTableObjectId = auditLog.getAuditLogTableObjectId();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
		
		assertTrue(curentURL.contains("DomainTransformEventInfo"));
		assertEquals(siteTableSiteId,auditLogTableObjectId);
		assertEquals(siteFormSiteId,auditLogTableObjectId);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testSiteHistoryViaSiteTableIdLink test is ending... ");
	}

	/**
	 * Test site history via site table's Name Link from site form
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 5, description = "Test testSiteHistoryViaSiteTablesNameLink", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteHistoryViaSiteTablesNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteHistoryViaSiteTablesNameLink() {
		log.info("testSiteHistoryViaSiteTablesNameLink test is starting... ");
		site.clickSitesSideBarButton();
		String siteTableSiteName = site.getSiteTableSiteName();
		site.clickSiteTableNameLink();
		String siteFormSiteName = site.getSiteFormSiteName();
		
		adminUtils.clickHistory();
		adminUtils.clickFull();
		
		String auditLogTableSiteName = auditLog.getAuditLogTableSiteName();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
	
		assertTrue(curentURL.contains("DomainTransformEventInfo"));
		assertEquals(siteTableSiteName,auditLogTableSiteName);
		assertEquals(siteFormSiteName,auditLogTableSiteName);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testSiteHistoryViaSiteTablesNameLink test is ending... ");
	}
	
	/**
	 * Test site history via site table's Id Link from site form when editing site
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk 
	 */
	@Test(priority = 6, description = "Test testSiteHistoryViaSiteTablesIdLinkWhenEditingSite", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteHistoryViaSiteTablesIdLinkWhenEditingSite")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteHistoryViaSiteTablesIdLinkWhenEditingSite() {
		log.info("testSiteHistoryViaSiteTablesIdLinkWhenEditingSite test is starting... ");
		site.clickSitesSideBarButton();
		String siteTableSiteName = site.getSiteTableSiteName();
		adminUtils.clickTableIdLink();
		String siteFormSiteName = site.getSiteFormSiteName();
		
		adminUtils.clickEditButton();
		adminUtils.clickHistory();
		adminUtils.clickFull();
		
		String auditLogTableSiteName = auditLog.getAuditLogTableSiteName();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
	
		assertTrue(curentURL.contains("DomainTransformEventInfo"));
		assertEquals(siteTableSiteName,auditLogTableSiteName);
		assertEquals(siteFormSiteName,auditLogTableSiteName);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testSiteHistoryViaSiteTablesIdLinkWhenEditingSite test is ending... ");
	}
	
	/**
	 * Test site history via site table's Name Link from site form when editing object
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 7, description = "Test testSiteHistoryViaSiteTablesNameLinkWhenEditingSite", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteHistoryViaSiteTablesNameLinkWhenEditingSite")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteHistoryViaSiteTablesNameLinkWhenEditingSite() {
		log.info("testSiteHistoryViaSiteTablesNameLinkWhenEditingSite test is starting... ");
		site.clickSitesSideBarButton();
		String siteTableSiteName = site.getSiteTableSiteName();
		site.clickSiteTableNameLink();;
		String siteFormSiteName = site.getSiteFormSiteName();
		
		adminUtils.clickEditButton();
		adminUtils.clickHistory();
		adminUtils.clickFull();
		
		String auditLogTableSiteName = auditLog.getAuditLogTableSiteName();
		String curentURL = driver.getCurrentUrl();
		String auditLogHeader = auditLog.getAuditLogHeader();
		String auditLogPageTitle = auditLog.getAuditLogPageTitle();
	
		assertTrue(curentURL.contains("DomainTransformEventInfo"));
		assertEquals(siteTableSiteName,auditLogTableSiteName);
		assertEquals(siteFormSiteName,auditLogTableSiteName);
		assertEquals(auditLogHeader, AppConstants.AUDITLOG_PAGE_HEADER);
		assertEquals(auditLogPageTitle, AppConstants.AUDITlOG_PAGE_TITLE);
		log.info("testSiteHistoryViaSiteTablesNameLinkWhenEditingSite test is ending... ");
	}
	
	/**
	 * Test site form's site deleting with ID link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	@Test(priority = 8, description = "Test testSiteFormDeleteObjectWithIdLink", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteFormDeleteObjectWithIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteFormDeleteObjectWithIdLink() throws InterruptedException {
		log.info("testSiteFormDeleteObjectWithIdLink test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.navigateToLastPage();
		String lastCreatedSite = site.getLastCreatedSiteNameFromSiteTable();
		log.info("Last Site Before Delete " + lastCreatedSite);

		site.deleteLastCreatedSiteViaIdLink();
		TimeUnit.SECONDS.sleep(3);
		
		String lastSite = site.getLastCreatedSiteNameFromSiteTable();
		log.info("Last Site After Delete " + lastSite);
		
		assertNotEquals(lastCreatedSite, lastSite);
		log.info("testSiteFormDeleteObjectWithIdLink test is ending... ");
	}
	
	/**
	 * Test site form's site deleting with name link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @throws InterruptedException 
	 * @author yavuz.ozturk
	 */
	@Test(priority = 9, description = "Test testSiteFormDeleteSiteWithNameLink", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteFormDeleteSiteWithNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteFormDeleteSiteWithNameLink() throws InterruptedException {
		log.info("testSiteFormDeleteSiteWithNameLink test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.navigateToLastPage();
		String lastCreatedSite = site.getLastCreatedSiteNameFromSiteTable();
		log.info("Last Site Before Delete " + lastCreatedSite);

		site.deleteLastCreatedSiteViaNameLink();
		TimeUnit.SECONDS.sleep(3);
		
		String lastSite = site.getLastCreatedSiteNameFromSiteTable();
		log.info("Last Site After Delete " + lastSite);
		
		assertNotEquals(lastCreatedSite, lastSite);
		log.info("testSiteFormDeleteSiteWithNameLink test is ending... ");
	}
	
	/**
	 * Test site edit button via id link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 10, description = "Test testSiteEditButtonViaIdLink", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test  testSiteEditButtonViaIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void  testSiteEditButtonViaIdLink() throws InterruptedException {
		log.info("testSiteEditButtonViaIdLink test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickTableIdLink();
		adminUtils.clickEditButton();

		String name = "Test_name_" + projectsUtils.generateAlphaNumericString(5);
		String addressLine1 = "Test_AddressLine1_" + projectsUtils.generateAlphaNumericString(10);
		String addressLine2 = "Test_AddressLine2_" + projectsUtils.generateAlphaNumericString(10);
		String city = "Test_City_" + projectsUtils.generateAlphaNumericString(5);
		String state = "Test_State_" + projectsUtils.generateAlphaNumericString(5);
		String guid = "Test_GUID_" + projectsUtils.generateAlphaNumericString(5);
		String country = "Test_Country_" + projectsUtils.generateAlphaNumericString(5);
		String phone = "Test_Phone_" + projectsUtils.generateAlphaNumericString(5);
		String zip = "Test_GUID_" + projectsUtils.generateAlphaNumericString(5);
		String geolocation = "Test_Geolocation_" + projectsUtils.generateAlphaNumericString(5);
		String notes = "Test_Notes_" + projectsUtils.generateAlphaNumericString(5);
		site.editSite(name, addressLine1, addressLine2, city, state, guid, country, phone, zip, geolocation, notes);
		String editedSiteName = site.getEditedSiteName();
		
		assertEquals(editedSiteName, name);
		log.info("testSiteEditButtonViaIdLink test is ending... ");
	}

	/**
	 * Test site edit button via name link
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @throws InterruptedException
	 * @author yavuz.ozturk
	 */
	@Test(priority = 11, description = "Test testSiteEditButtonViaNameLink", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test  testSiteEditButtonViaNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void  testSiteEditButtonViaNameLink() throws InterruptedException {
		log.info("testSiteEditButtonViaNameLink test is starting... ");
		site.clickSitesSideBarButton();
		site.clickSiteTableNameLink();
		adminUtils.clickEditButton();

		String name = "Test_name_" + projectsUtils.generateAlphaNumericString(5);
		String addressLine1 = "Test_AddressLine1_" + projectsUtils.generateAlphaNumericString(10);
		String addressLine2 = "Test_AddressLine2_" + projectsUtils.generateAlphaNumericString(10);
		String city = "Test_City_" + projectsUtils.generateAlphaNumericString(5);
		String state = "Test_State_" + projectsUtils.generateAlphaNumericString(5);
		String guid = "Test_GUID_" + projectsUtils.generateAlphaNumericString(5);
		String country = "Test_Country_" + projectsUtils.generateAlphaNumericString(5);
		String phone = "Test_Phone_" + projectsUtils.generateAlphaNumericString(5);
		String zip = "Test_GUID_" + projectsUtils.generateAlphaNumericString(5);
		String geolocation = "Test_Geolocation_" + projectsUtils.generateAlphaNumericString(5);
		String notes = "Test_Notes_" + projectsUtils.generateAlphaNumericString(5);
		site.editSite(name, addressLine1, addressLine2, city, state, guid, country, phone, zip, geolocation, notes);
		String editedSiteName = site.getEditedSiteName();
		
		assertEquals(editedSiteName, name);
		log.info("testSiteEditButtonViaNameLink test is ending... ");
	}
	
	/**
	 * Test site table's site id link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 12, description = "Test testSiteTableSiteIdLink", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableSiteIdLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableSiteIdLink() {
		log.info("testSiteTableSiteIdLink test is starting... ");
		site.clickSitesSideBarButton();
		String siteTableSiteId = adminUtils.getTableId();
		String siteFormSiteId = adminUtils.getFormId();
		log.info("Site Table Site Id: " + siteTableSiteId);
		log.info("Site Id: " + siteFormSiteId);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		
		assertTrue(curentURL.endsWith(siteFormSiteId));
		assertEquals(siteTableSiteId, siteFormSiteId);
		log.info("testSiteTableSiteIdLink test is ending... ");
	}
	
	/**
	 * Test site table's site name link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 13, description = " Test testSiteTableSiteNameLink", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableSiteNameLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableSiteNameLink() {
		log.info("testSiteTableSiteNameLink test is starting... ");
		site.clickSitesSideBarButton();
		String siteTableSiteName = site.getSiteTableSiteName();	
		site.clickSiteTableNameLink();
		String siteFormPageTitle = site.getSiteFormPageTitle();
		String siteFormSiteName = site.getSiteFormSiteName();
		log.info("Site Table site Name: " + siteTableSiteName);
		log.info("Site Form Page Title: " + siteFormPageTitle);
		log.info("Site Name: " + siteFormSiteName);

		assertTrue(siteTableSiteName.equalsIgnoreCase(siteFormPageTitle));
		assertTrue(siteTableSiteName.equalsIgnoreCase(siteFormPageTitle));
		assertTrue(siteFormPageTitle.equalsIgnoreCase(siteFormSiteName));
		log.info("testSiteTableSiteNameLink test is ending... ");
	}
	
	/**
	 * Test site table's subject link is clickable and active
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk 
	 */
	@Test(priority = 14, description = "Test testSiteTableSubjectLink", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableSubjectLink")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableSubjectLink() {
		log.info("testSiteTableSubjectLink test is starting... ");
		site.clickSitesSideBarButton();
		int siteTableSubjectCount = site.getSiteTableSubjectCount();
		int subjectTableElementCount = subject.getSubjectCount();
		log.info("Site Table Subject Count: " + siteTableSubjectCount);
		log.info("Subject Table Element Count: " + subjectTableElementCount);
		String curentURL = driver.getCurrentUrl();
		log.info(curentURL);
		String subjectsHeader = subject.getSubjectsHeader();
		String subjectsPageTitle = subject.getSubjectsPageTitle();

		assertTrue(curentURL.contains("subject"));
		assertEquals(siteTableSubjectCount, subjectTableElementCount);
		assertEquals(subjectsHeader, AppConstants.SUBJECTS_PAGE_HEADER);
		assertEquals(subjectsPageTitle, AppConstants.SUBJECTS_PAGE_TITLE);
		log.info("testSiteTableSubjectLink test is ending... ");
	}
	
	/**
	 * Test sorting of the id rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 15, description = "Test testSiteTableIdSort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableIdSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableIdSort() {
		log.info("testSiteTableIdSort test is starting... ");
		site.clickSitesSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(adminUtils.getTableRowsId(), adminUtils.getTableHeaderId());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(adminUtils.getTableRowsId(),adminUtils.getTableHeaderId());
		
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTableIdSort test is ending... ");
	}
	
	/**
	 * Test sorting of the name rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 16, description = "Test testSiteTableNameSort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableNameSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableNameSort() {
		log.info("testSiteTableNameSort test is starting... ");
		site.clickSitesSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(site.getTableRowsName(),site.getTableHeaderName());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(site.getTableRowsName(),site.getTableHeaderName());
		
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTableNameSort test is ending... ");
	}
	
	/**
	 * Test sorting of the subjects rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 17, description = "Test testSiteTableSubjectsSort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableSubjectsSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableSubjectsSort() {
		log.info("testSiteTableSubjectsSort test is starting... ");
		site.clickSitesSideBarButton();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(site.getTableRowsSubjects(),site.getTableHeaderSubjects());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(site.getTableRowsSubjects(),site.getTableHeaderSubjects());
		
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTableSubjectsSort test is ending... ");
	}
	
	/**
	 * Test sorting of the contact user rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 18, description = "Test testSiteTableContactUserSort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableContactUserSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableContactUserSort() {
		log.info("testSiteTableContactUserSort test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(site.getTableRowsContactUser(),site.getTableHeaderContactUser());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(site.getTableRowsContactUser(),site.getTableHeaderContactUser());
		
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTableContactUserSort test is ending... ");
	}
	
	/**
	 * Test sorting of the address line 1 rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 19, description = "Test testSiteTableAddressLineOneSort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableAddressLineOneSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableAddressLineOneSort() {
		log.info("testSiteTableAddressLineOneSort test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(site.getTableRowsAdressLineOne(),site.getTableHeaderAdressLineOne());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(site.getTableRowsAdressLineOne(),site.getTableHeaderAdressLineOne());
		
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTableAddressLineOneSort test is ending... ");
	}
	
	/**
	 * Test sorting of the Address Line 2 rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 20, description = "Test testSiteTableAddressLineTwoSort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableAddressLineTwoSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableAddressLineTwoSort() {
		log.info("testSiteTableAddressLineTwoSort test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(site.getTableRowsAdressLineTwo(),site.getTableHeaderAdressLineTwo());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(site.getTableRowsAdressLineTwo(),site.getTableHeaderAdressLineTwo());
		
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTableAddressLineTwoSort test is ending... ");
	}
	
	/**
	 * Test sorting of the city rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 21, description = "Test testSiteTableCitySort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableCitySort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableCitySort() {
		log.info("testSiteTableCitySort test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(site.getTableRowsCity(),site.getTableHeaderCity());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(site.getTableRowsCity(),site.getTableHeaderCity());
		
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTableCitySort test is ending... ");
	}
	
	/**
	 * Test sorting of the state rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 22, description = "Test testSiteTableStateSort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableStateSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableStateSort() {
		log.info("testSiteTableStateSort test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(site.getTableRowsState(),site.getTableHeaderState());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(site.getTableRowsState(),site.getTableHeaderState());
		
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTableStateSort test is ending... ");
	}
	
	/**
	 * Test sorting of the GUID rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 23, description = "Test testSiteTableGuidSort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableGuidSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableGuidSort() {
		log.info("testSiteTableGuidSort test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(site.getTableRowsGuid(),site.getTableHeaderGuid());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(site.getTableRowsGuid(),site.getTableHeaderGuid());
		
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTableGuidSort test is ending... ");
	}
	
	/**
	 * Test sorting of the country rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 24, description = "Test testSiteTableCountrySort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableCountrySort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableCountrySort() {
		log.info("testSiteTableCountrySort test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(site.getTableRowsCountry(),site.getTableHeaderCountry());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(site.getTableRowsCountry(),site.getTableHeaderCountry());
	
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTableCountrySort test is ending... ");
	}
	
	/**
	 * Test sorting of the phone rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 25, description = "Test testSiteTablePhoneSort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTablePhoneSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTablePhoneSort() {
		log.info("testSiteTablePhoneSort test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(site.getTableRowsPhone(),site.getTableHeaderPhone());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(site.getTableRowsPhone(),site.getTableHeaderPhone());
		
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTablePhoneSort test is ending... ");
	}
	
	/**
	 * Test sorting of the zip rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 26, description = "Test testSiteTableZipSort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableZipSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableZipSort() {
		log.info("testSiteTableZipSort test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(site.getTableRowsZip(),site.getTableHeaderZip());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(site.getTableRowsZip(),site.getTableHeaderZip());
		
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTableZipSort test is ending... ");
	}
	
	/**
	 * Test sorting of the creation date rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk 
	 */
	@Test(priority = 27, description = "Test testSiteTableCreationDateSort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableCreationDateSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableCreationDateSort() {
		log.info("testSiteTableCreationDateSort test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(site.getTableRowsCreationDate(),site.getTableHeaderCreationDate());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(site.getTableRowsCreationDate(),site.getTableHeaderCreationDate());
		
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTableCreationDateSort test is ending... ");
	}
	
	/**
	 * Test sorting of the geolocation rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 28, description = "Test testSiteTableGeolocationSort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableGeolocationSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableGeolocationSort() throws InterruptedException {
		log.info("testSiteTableGeolocationSort test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(site.getTableRowsGeolocation(),site.getTableHeaderGeolocation());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(site.getTableRowsGeolocation(),site.getTableHeaderGeolocation());
	
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTableGeolocationSort test is ending... ");
	}
	
	/**
	 * Test sorting of the notes rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 29, description = "Test testSiteTableNotesSort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableNotesSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableNotesSort() {
		log.info("testSiteTableNotesSort test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(site.getTableRowsNotes(),site.getTableHeaderNotes());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(site.getTableRowsNotes(),site.getTableHeaderNotes());
		
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTableNotesSort test is ending... ");
	}
	
	/**
	 * Test sorting of the deleted rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 30, description = "Test testSiteTableDeletedSort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableDeletedSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableDeletedSort() {
		log.info("testSiteTableDeletedSort test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(site.getTableRowsDeleted(),site.getTableHeaderDeleted());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(site.getTableRowsDeleted(),site.getTableHeaderDeleted());
	
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTableDeletedSort test is ending... ");
	}
	
	/**
	 * Test sorting of the last modified rows in the site table
	 * 
	 * @see <a href="https://apdmwearables.atlassian.net/browse/SB-3810"> SB-3810 </a>
	 * @author yavuz.ozturk
	 */
	@Test(priority = 31, description = "Test testSiteTableLastModifiedSort", enabled = true)
	@Story("Test Sites Place in UI MX Admin Tests")
	@Description("Test testSiteTableLastModifiedSort")
	@Severity(SeverityLevel.NORMAL)
	public void testSiteTableLastModifiedSort() {
		log.info("testSiteTableDeletedSort test is starting... ");
		site.clickSitesSideBarButton();
		adminUtils.clickFull();
		List<String> sortedList = adminUtils.getPaginatedSortedRowsList(site.getTableRowsLastModified(),site.getTableHeaderLastModified());
		List<String> unsortedList = adminUtils.getPaginatedUnsortedRowsList(site.getTableRowsLastModified(),site.getTableHeaderLastModified());
		
		Assert.assertEquals(sortedList.size(), unsortedList.size());
		Assert.assertTrue(sortedList.equals(unsortedList));
		log.info("testSiteTableLastModifiedSort test is ending... ");
	}
	
	@AfterMethod(groups = "finish")
	public void tearDown() {
		log.info("AfterMethod is starting... ");
		driver.quit();
		log.info("tearDown is ending... ");
	}
	
}
