package com.ert.wearables.test.core.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class provides all common methods for all Mobility Exchange Admin application
 * 
 * @author yavuz.ozturk
 */
public class AdminUtils {

	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;
	JavaScriptUtil javaScriptUtil;
	Credentials userCred;

	JavascriptExecutor jse = (JavascriptExecutor) driver;
	Logger log = Logger.getLogger(AdminUtils.class);
	
	//Locators
	private By username = By.id("__localdom__1");
	private By password = By.id("__localdom__3");
	private By nextButton = By.xpath("//span[@class='gwt-InlineLabel label']");
	private By fullButton = By.xpath("//button[normalize-space()='Full']");
	private By compactButton = By.xpath("//button[normalize-space()='Compact']");
	private By createButton = By.xpath("//button[normalize-space()='Create']");
	private By historyButton = By.xpath("//button[normalize-space()='History']");
//	private By formDeleteButton = By.xpath("//button[normalize-space()='Delete']");
	private By formEditButton = By.xpath("//button[normalize-space()='Edit']");
	private By enterReason = By.xpath("/html/body/div[2]/div/div[2]/div/form/section/div/input");
	private By reasonSubmitButton = By.xpath("/html/body/div[2]/div/div[2]/div/form/div/ul/li[2]/button");
	private By tableIdLink = By.xpath("//table//tbody//tr[1]//td[1]//a");
	private By tableHeaderId = By.xpath("//table//thead//tr[1]//th[1]");
	private By tableRowsId = By.xpath("//table//tbody//tr//td[1]");


	public By getTableHeaderId() {
		return tableHeaderId;
	}

	public By getTableRowsId() {
		return tableRowsId;
	}

	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public AdminUtils(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}
	
	/**
	 * This method is used to login Admin Pages
	 * 
	 * @param userCred
	 * @author yavuz.ozturk
	 */
	public void doLogin(Credentials userCred) {
		elementUtils.waitForElementPresent(username);
		elementUtils.doSendKeys(username, userCred.getAppUsername());
		elementUtils.waitForElementPresent(nextButton);
		elementUtils.doClick(nextButton);
		elementUtils.waitForElementPresent(password);
		elementUtils.doSendKeys(password, userCred.getAppPassword());
		elementUtils.waitForElementPresent(nextButton);
		elementUtils.doClick(nextButton);
	}
	
	/**
	 * This method is used to make web table full size
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickFull() {
		elementUtils.waitForElementPresent(fullButton);
		elementUtils.doClick(fullButton);
	}

	/**
	 * This method is used to get full button text
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getFullButtonText() {
		elementUtils.waitForElementPresent(fullButton);
		return elementUtils.doGetText(fullButton);
	}

	/**
	 * This method is used to click compact button
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickCompact() {
		elementUtils.waitForElementPresent(compactButton);
		elementUtils.doClick(compactButton);
	}
	
	/**
	 * This method is used to get compact button text
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getCompactText() {
		elementUtils.waitForElementPresent(compactButton);
		return elementUtils.doGetText(compactButton);
	}
	
	/**
	 * This method is used to click create button
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickCreate() {
		elementUtils.waitForElementPresent(createButton);
		elementUtils.doClick(createButton);
	}

	/**
	 * This method is used to click history button
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickHistory() {
		elementUtils.waitForElementPresent(historyButton);
		elementUtils.doClick(historyButton);
	}
	
	/**
	 * This method is used to click study form's edit button
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickEditButton() {
		elementUtils.waitForElementPresent(formEditButton);
		elementUtils.doClick(formEditButton);
	}
	
	/**
	 * This method is used to enter a reason for changes
	 * 
	 * @param reason
	 * @author yavuz.ozturk
	 */
	public void enterAreasonForTheseChanges(String reason){
		elementUtils.waitForElementPresent(enterReason);
		elementUtils.doSendKeys(enterReason, reason);
		elementUtils.waitForElementPresent(reasonSubmitButton);
		elementUtils.doClick(reasonSubmitButton);
	}
	
	/**
	 * This method is used to click OK for alert pop ups
	 * 
	 * @param driver
	 * @author yavuz.ozturk
	 */
	public void clickAlertOk() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	/**
	 * This method is used to click table's id link
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickTableIdLink() {
		elementUtils.waitForElementPresent(tableIdLink);
		elementUtils.doClick(tableIdLink);
	}
	
	/**
	 * This method is used to get table's id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getTableId() {
		elementUtils.waitForElementPresent(tableIdLink);
		return elementUtils.doGetText(tableIdLink);
	}

	/**
	 * This method is used to get form's id
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getFormId() {
		elementUtils.waitForElementPresent(tableIdLink);
		elementUtils.doClick(tableIdLink);
		return elementUtils.doGetText(By.xpath("(//form//div//div//a)[1]"));
	}
	
	/**
	 * This method clicks the next button
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickNextButton() {
		elementUtils.waitForElementPresent(By.xpath("//button[2]"));
		elementUtils.doClick(By.xpath("//button[2]"));
	}

	/**
	 * This method clicks the back button
	 * 
	 * @author yavuz.ozturk
	 */
	public void clickBackButton() {
		elementUtils.waitForElementPresent(By.xpath("/html/body/div/main/div/div/div[1]/ul[2]/li[1]/div/button[1]"));
		elementUtils.doClick(By.xpath("/html/body/div/main/div/div/div[1]/ul[2]/li[1]/div/button[1]"));
	}

	/**
	 * This method gets the elements as a string and stores them in a list
	 * 
	 * @param locator
	 * @return
	 * @author yavuz.ozturk
	 */
	public List<String> getRowsStringAsList(By locator) {
		elementUtils.waitForElementPresent(locator);
		List<WebElement> rowsElementList = driver.findElements(locator);
		List<String> rowsStringList = new ArrayList<>();
		for (WebElement webElement : rowsElementList) {
			rowsStringList.add(webElement.getText());
		}
		return rowsStringList;
	}
	
	/**
	 * This method gets sorted rows based on the locater and stores them in a list
	 * 
	 * @param tableRow
	 * @param tableHeader
	 * @return
	 * @author yavuz.ozturk
	 */
	public List<String> getPaginatedSortedRowsList(By tableRow, By tableHeader) {
		elementUtils.waitForElementPresent(tableHeader);
		elementUtils.doClick(tableHeader);
		ProjectsUtils.pause(1);
		
		List<String> sortedStringRows = getRowsStringAsList(tableRow);
		boolean morePages = areThereMorePages();
		while (morePages) {
			clickNextButton();
			ProjectsUtils.pause(3);
			sortedStringRows.addAll(getRowsStringAsList(tableRow));
			morePages = areThereMorePages();
		}
		//log.info("Sorted List " + sortedStringRows);
		return sortedStringRows;
	}

	/**
	 * This method gets rows and sorts them and adds them in a list
	 * 
	 * @param tableRow
	 * @param tableHeader
	 * @return
	 * @author yavuz.ozturk
	 */
	public List<String> getPaginatedUnsortedRowsList(By tableRow, By tableHeader) {
		elementUtils.waitForElementPresent(tableHeader);
		elementUtils.doClick(tableHeader);
		ProjectsUtils.pause(1);
		
		boolean lessPages = areThereLessPages();
		while (lessPages) {
			clickBackButton();
			ProjectsUtils.pause(3);
			lessPages = areThereLessPages();
		}

		List<String> unSortedStringRowsList = getRowsStringAsList(tableRow);
		boolean morePages = areThereMorePages();
		while (morePages) {
			clickNextButton();
			ProjectsUtils.pause(3);
			unSortedStringRowsList.addAll(getRowsStringAsList(tableRow));
			morePages = areThereMorePages();
		}
		//log.info("UNsorted List" + unSortedStringRowsList);
		Collections.reverse(unSortedStringRowsList);
		//log.info("Reverse UNsorted List" + unSortedStringRowsList);
		return unSortedStringRowsList;
	}

	/**
	 * This method is checking if there are more pages to navigate forward
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public boolean areThereMorePages() {
		elementUtils.waitForElementPresent(By.xpath("//div[@class='es-number']"));
		String navigationText = elementUtils.doGetText(By.xpath("//div[@class='es-number']"));
		String firstPart = navigationText.substring(0, navigationText.indexOf(" "));
		if (firstPart.endsWith(" ")) {
			firstPart = firstPart.trim();
		}
		//log.info(firstPart);
		String secondPart = navigationText.substring(navigationText.indexOf("f") + 2);
		//log.info(secondPart);
		return !firstPart.equals(secondPart);
	}

	/**
	 * This method is checking if there are more pages to navigate backward
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public boolean areThereLessPages() {
		elementUtils.waitForElementPresent(By.xpath("//div[@class='es-number']"));
		String navigationText = elementUtils.doGetText(By.xpath("//div[@class='es-number']"));
		String firstPart = navigationText.substring(0, navigationText.indexOf(" "));
		if (firstPart.endsWith(" ")) {
			firstPart = firstPart.trim();
		}
		//log.info(firstPart);
		String secondPart = navigationText.substring(navigationText.indexOf("f") + 2);
		//log.info(secondPart);
		return !firstPart.equals("1");
	}
	
	/**
	 * This method is navigate to last page
	 * 
	 * @author yavuz.ozturk
	 * @throws InterruptedException 
	 */
	public void navigateToLastPage() throws InterruptedException {
		boolean morePages = areThereMorePages();
		while (morePages) {
			clickNextButton();
			TimeUnit.SECONDS.sleep(3);
			morePages = areThereMorePages();
		}
	}
	
	
}
