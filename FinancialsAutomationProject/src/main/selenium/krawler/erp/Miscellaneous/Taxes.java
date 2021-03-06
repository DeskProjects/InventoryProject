package krawler.erp.Miscellaneous;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import com.google.common.base.Function;

import krawler.erp.inventory.InvUtilities;
import krawler.erp.page.Utilities;

public class Taxes {
	public static void create_Tax(String documentid, String taxPercent, String accountName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_Taxes.properties");
			driver.navigate().refresh();
			Utilities.isLoadingDisappear(150, driver);
			Utilities.click_element(pro.getProperty("MiscellaneousExpander"), driver);
			Utilities.click_element(pro.getProperty("TaxesLink"), driver);
			Thread.sleep(2000);

			int taxCodeIndex = 0, AccNameIndex = 0, indOfdescr = 0, indOfname = 0, indOfpercent = 0, indOftype = 0;
			int totalHeader = Utilities.totalSize("//div[@class='x-grid3-header']/div/div/table/thead/tr/td", driver);
			for (int i = 1; i <= totalHeader; i++) {
				String HeaderName = driver
						.findElement(
								By.xpath("*//div[@class='x-grid3-header']/div/div/table/thead/tr/td[" + i + "]/div"))
						.getText();
				if (HeaderName.equalsIgnoreCase("Tax Code")) {
					taxCodeIndex = i;
				}
				if (HeaderName.equalsIgnoreCase("Account Name")) {
					AccNameIndex = i;
				}
				if (HeaderName.equalsIgnoreCase("Description")) {
					indOfdescr = i;
				}
				if (HeaderName.equalsIgnoreCase("Name")) {
					indOfname = i;
				}
				if (HeaderName.equalsIgnoreCase("Percent")) {
					indOfpercent = i;
				}
				if (HeaderName.equalsIgnoreCase("Type")) {
					indOftype = i;
				}
			}

			int totalPresentTax = Utilities.totalSize("//div[@class='x-grid3-viewport']/div[2]/div/div", driver);
			String xpathOfInput = "//*[contains(@class,'x-grid-editor') and contains(@style,'visible')]/input[1]";
			for (int lastRow = 1; lastRow <= totalPresentTax; lastRow++) {
				if (lastRow == totalPresentTax) {
					// Enter Tax Name
					Utilities.click_element("//div[@class='x-grid3-viewport']/div[2]/div/div[" + lastRow
							+ "]/table/tbody/tr/td[" + indOfname + "]/div", driver);
					Utilities.enterTextInDropDown("Tax" + documentid, xpathOfInput, driver);
					// Enter Description
					Utilities.click_element("//div[@class='x-grid3-viewport']/div[2]/div/div[" + lastRow
							+ "]/table/tbody/tr/td[" + indOfdescr + "]/div", driver);
					Utilities.enterTextInDropDown("Automation tax [" + documentid + "]", xpathOfInput, driver);
					// Enter Percent
					Utilities.click_element("//div[@class='x-grid3-viewport']/div[2]/div/div[" + lastRow
							+ "]/table/tbody/tr/td[" + indOfpercent + "]/div", driver);
					Utilities.enterTextInDropDown(taxPercent, xpathOfInput, driver);
					// Enter Tax code
					Utilities.click_element("//div[@class='x-grid3-viewport']/div[2]/div/div[" + lastRow
							+ "]/table/tbody/tr/td[" + taxCodeIndex + "]/div", driver);
					Utilities.enterTextInDropDown("Automation" + documentid, xpathOfInput, driver);
					// Enter Account Name
					Utilities.click_element("//div[@class='x-grid3-viewport']/div[2]/div/div[" + lastRow
							+ "]/table/tbody/tr/td[" + AccNameIndex + "]/div", driver);
					Utilities.enterTextandSelect(accountName, "//*[@id='accountid']/following::input[1]", driver);
					// Enter Tax type
					Utilities.click_element("//div[@class='x-grid3-viewport']/div[2]/div/div[" + lastRow
							+ "]/table/tbody/tr/td[" + indOftype + "]/div", driver);
					Utilities.enterTextandSelect("Both", "//*[@id='taxtypeid']/following::input[1]", driver);
				}
			}
			Utilities.HoverandClick(pro.getProperty("updateBtn"), driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("closeBtn"), driver);
			System.out.println("Tax acc [" + documentid + "] is CREATED !!");
		} catch (Exception Ex) {
			System.out.println("Failed to create Tax acc [" + documentid + "] is CREATED !!");
			Utilities.handleError(Ex, driver);
		}
	}

	// **************************************** Verification
	// **********************************************

	public static void verify_Tax(String documentid, String taxPercent, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			final Properties pro = Utilities.fetchProValue("OR_Taxes.properties");
			Utilities.refresh();
			documentid = "Tax" + documentid;
			Utilities.enableExpander(driver);
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			WebElement element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("MiscellaneousExpander")));
				}
			});
			element.click();
			Thread.sleep(1000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("TaxesLink")));
				}
			});
			element.click();
			Thread.sleep(3000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("allArrow")));
				}
			});
			element.click();
			Thread.sleep(2000);

			WebElement checkAll = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath("//div[text()='All']"));
				}
			});
			if (checkAll.isDisplayed()) {
				checkAll.click();
				Thread.sleep(2000);
			}

			List<WebElement> tablehead = driver
					.findElements(By.xpath("//div[@class='x-grid3-viewport']/div/div/div/table/thead/tr/td/div"));
			int totalsize = tablehead.size();
			int taxNameIndex = 0, percentIndex = 0;

			for (int rowIndex = 1; rowIndex <= totalsize; rowIndex++) {
				String header = driver
						.findElement(By.xpath(
								"//div[@class='x-grid3-viewport']/div/div/div/table/thead/tr/td[" + rowIndex + "]/div"))
						.getText();
				if (header.equals("Name")) {
					taxNameIndex = rowIndex;

				} else if (header.equals("Percent")) {
					percentIndex = rowIndex;
				}
			}

			List<WebElement> totalresult = driver
					.findElements(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div"));
			int totalResult = totalresult.size();
			// System.out.println("Total Resulssss > "+ totalResult);

			for (int j = 1; j <= totalResult; j++) {
				String accName = driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
						+ "]/table/tbody/tr/td[" + taxNameIndex + "]/div")).getText();

				if (accName.equals(documentid)) {
					// System.out.println(">>>>"+accName);
					assertEquals(documentid,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
									+ "]/table/tbody/tr/td[" + taxNameIndex + "]/div")).getText());
					System.out.println("Verified Tax Name = " + documentid + " : Balance Quantity [" + documentid
							+ "] matched with UI");

					assertEquals(taxPercent + "%",
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
									+ "]/table/tbody/tr/td[" + percentIndex + "]/div")).getText());
					System.out.println("Verified Tax Percentage = " + taxPercent + " : Balance Quantity [" + taxPercent
							+ "] matched with UI");
				}
			}
			System.out.println("Tax [" + documentid + "] Verified Successfully !!");
			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("closeBtn")));
				}
			});
			element.click();
			Thread.sleep(3000);

		} catch (Exception EX) {
			System.out.println("Tax acc [" + documentid + "] NOT VERIFIED !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************************** Delete
	// **************************************************

	public static void delete_Tax(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_Taxes.properties");
			driver.navigate().refresh();
			Utilities.isLoadingDisappear(150, driver);
			documentid = "Tax" + documentid;
			Utilities.click_element(pro.getProperty("MiscellaneousExpander"), driver);
			Utilities.click_element(pro.getProperty("TaxesLink"), driver);
			Utilities.click_element(pro.getProperty("allArrow"), driver);
			Utilities.click_element(pro.getProperty("selectAll"), driver);
			Utilities.isLoadingDisappear(120, driver);

			// Click Delete button
			Utilities.click_element(
					"//*[text()='" + documentid + "']/ancestor::tr/td/div/div[contains(@class,'delete')]", driver);
			Utilities.clickButton("Yes", 500, driver);
			Utilities.click_element(pro.getProperty("updateBtn"), driver);
			Utilities.clickButton("OK", 1000, driver);

			// verify
			try {
				WebElement element = driver.findElement(By.xpath("//*[text()='" + documentid + "']"));
				if (element.isDisplayed()) {
					System.out.println("!!!! Failed To delete TAX [" + documentid + "] check it !!!!");
					driver.navigate().refresh();
					Assert.assertTrue(false);
				}
			} catch (Exception pass) {
				System.out.println("!!!! TAX deleted [" + documentid + "] successfully !!!!");
				Utilities.click_element(pro.getProperty("closeBtn"), driver);
			}
		} catch (Exception EX) {
			System.out.println("Tax acc [" + documentid + "] NOT Delted !!");
			Utilities.handleError(EX, driver);
		}
	}
}// clas
