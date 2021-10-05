package com.ert.wearables.test.core.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ert.wearables.test.core.base.BaseSetup;

public class ProjectsUtils extends BaseSetup {

	WebDriver driver;
	ElementUtils elementUtils;
	WebElement element;

	Logger log = Logger.getLogger(ElementUtils.class);

	// Web Table Locators
	private By tableHeaders = By.xpath("//table/thead/tr/th");
	private By date = By.xpath("//div[@class='es-date-input']/input");
	private By tableDate = By.xpath("//table[@class='datePickerDays']");

	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public ProjectsUtils(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}

	/**
	 * This method is used to add wait times to test cases with a specified parameter
	 * 
	 * @param seconds
	 * @author yavuz.ozturk
	 */
	public static void pause(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method generates alphanumeric string values
	 * 
	 * @param n
	 * @return
	 * @author yavuz.ozturk
	 */
	public String generateAlphaNumericString(int n) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	/**
	 * This method is used to pick date
	 * 
	 * @param locator
	 * @author yavuz.ozturk
	 */
	public void datepicker(By locator) {
		List<WebElement> allDates = driver.findElements(locator);
		
		// now we will iterate all values and will capture the text. We will select when
		// date is 28
		for (WebElement ele : allDates) {

			String date = ele.getText();

			// once date is 28 then click and break
			if (date.equalsIgnoreCase("1")) {
				ele.click();
				break;
			}
		}
	}

	/**
	 * This method get date value
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	/**
	 * This method is used to pick date
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public void datepicker() {
		elementUtils.waitForElementPresent(date);
		elementUtils.doClick(date);
		elementUtils.waitForElementPresent(tableDate);
		datepicker(tableDate);
	}

	/**
	 * This method is used to get all web table data
	 * 
	 * @return
	 * @author yavuz.ozturk
	 */
	public List<Map<String, String>> getTableDataAsMapList() {
		List<Map<String, String>> tableDataList = new ArrayList<>();
		List<WebElement> tableHeaderList = driver.findElements(tableHeaders);
		List<WebElement> tableRowList = driver.findElements(By.xpath("//table/tbody/tr"));

		for (int i = 0; i < tableRowList.size(); i++) {
			for (int j = 0; j < tableHeaderList.size(); j++) {
				LinkedHashMap<String, String> eachRowData = new LinkedHashMap<String, String>();
				String eachColumnHeader = tableHeaderList.get(j).getText();
				String xpathString = "//table/tbody/tr[" + (i + 1) + "]/td[" + (j + 1) + "]/*";
				String eachColumnValueFromEachRow = driver.findElement(By.xpath(xpathString)).getText();
				eachRowData.put(eachColumnHeader, eachColumnValueFromEachRow);
				tableDataList.add(eachRowData);
			}
		}
		return tableDataList;
	}

	/**
	 * This is used to get row list
	 * 
	 * @param locator
	 * @return
	 * @author yavuz.ozturk
	 */
	public List<WebElement> getRowsList(By locator) {
		return driver.findElements(locator);
	}
	
}
