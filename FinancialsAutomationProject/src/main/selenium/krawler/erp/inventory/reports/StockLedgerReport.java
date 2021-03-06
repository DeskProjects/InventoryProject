package krawler.erp.inventory.reports;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import krawler.erp.inventory.InvUtilities;
import krawler.erp.page.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;

public class StockLedgerReport {

	// StockLedgerReport for Build-Assembly / UnBuild-Assembly ***************
	public static void validate_StockLedgerReport(String productid, String expectedQty, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_StockLedgerReport.properties");
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandReport(driver);
			String LoadingIcon = "//*[contains(text(),'Loading.')]";

			Utilities.HoverandClick(pro.getProperty("StockLedgerReportLink"), driver);
			Utilities.clickExpander(driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("QuickSearch"), driver);
			Utilities.HoverandClick(pro.getProperty("StockLedgerReportFetchBtn"), driver);
			Utilities.isElementGone(LoadingIcon, 20, driver);
			Utilities.click_element("//*[text()='Show']/parent::td/following::td[1]/div/img", driver);
			Utilities.click_element("//*[contains(@style,'visible')]//*[text()='All']", driver);
			Utilities.isElementGone(LoadingIcon, 20, driver);
			Utilities.clickButton("Expand", 500, driver);
			Thread.sleep(1000);
			int totalResult = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div",
					driver);
			boolean flag = true;
			for (int i = 1; i <= totalResult; i++) {
				String prdID = null;
				String actualBalanceQty = null;

				prdID = driver.findElement(By.xpath(
						"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i + "]/div[1]/div"))
						.getText();
				actualBalanceQty = driver
						.findElement(By.xpath("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
								+ "]/div//*[contains(text(),'Quantity on Hand:')]"))
						.getText();

				if (actualBalanceQty.equalsIgnoreCase("Quantity on Hand: " + expectedQty)) {
					System.out.println("****** Verified Stock Ledger Report for Product ID :[" + prdID
							+ "] with Available Qauntity [" + actualBalanceQty + "] **********");
				} else {
					System.out.println("****** FAILED to Verify Stock Ledger Report for Product ID :[" + prdID
							+ "] with Available Qauntity [" + actualBalanceQty + "] **********");
					flag = false;
				}
			}
			if (flag == false) {
				System.out.println("!!!!!!!!!!!!!! Failed to Verify Stock Ledger Report for Product ID :[" + productid
						+ "] !!!!!!!!!!!!!!!!!!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}

			Utilities.HoverandClick(pro.getProperty("StockLedgerReportCloseBtn"), driver);
			System.out.println(
					"******** Verified Stock Availability By Warehouse for Product ID :[" + productid + "] ********");

		} catch (Exception EX) {
			System.out.println("!!!!!!!!!!!!!! Failed to Verify Stock Ledger Report for Product ID :[" + productid
					+ "] !!!!!!!!!!!!!!!!!!!!");
			Utilities.handleError(EX, driver);

		}
	}

	// StockLedgerReport for Build-Assembly / UnBuild-Assembly ***************
	public static void validate_StockLedgerBuilAssembly(String documentid, String products[], double expectedQty[],
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_StockLedgerReport.properties");
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandReport(driver);
			Utilities.click_element(pro.getProperty("StockLedgerReportLink"), driver);
			Utilities.clickExpander(driver);

			boolean flag = true;
			for (int i = 0; i < products.length; i++) {
				Utilities.enterTextNormalBox(products[i], pro.getProperty("QuickSearch"), driver);
				Utilities.click_element(pro.getProperty("StockLedgerReportFetchBtn"), driver);
				Utilities.isLoadingDisappear(150, driver);
				Utilities.clickButton("Expand", 100, driver);

				String actualQty = driver.findElement(By.xpath(
						"//*[text()='" + products[i] + "']/ancestor::tr/td//*[contains(text(),'Quantity on Hand')]"))
						.getText();
				// System.out.println(actualQty);
				double actQty = Utilities.getIntegerFrmString(actualQty);
				if (actQty == expectedQty[i]) {
					System.out.println("*** Verified product [" + products[i] + "] as UI quantity [" + actualQty
							+ "] with expected quantity [[" + expectedQty[i] + "]] ****");
				} else {
					System.out.println("*** FAILED to verify product [" + products[i] + "] as UI quantity [" + actualQty
							+ "] with expected quantity [[" + expectedQty[i] + "]] ****");
					flag = false;
				}
			}

			if (flag == false) {
				System.out.println("!!!! Failed to verify Stock Ledger report for [ " + documentid
						+ " ] Report pls chec Log !!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			System.out.println("!!!! Verified Stock Ledger report for [ " + documentid + " ] Report pls chec Log !!!!");
			Utilities.click_element(pro.getProperty("StockLedgerReportCloseBtn"), driver);
		} catch (Exception Ex) {
			System.out.println(
					"!!!! Failed to verify Stock Ledger report for [ " + documentid + " ] Report pls chec Log !!!!");
			Utilities.handleError(Ex, driver);
		}

	}

	// **********************************************************PickPAckShip*******************************************************
	public static void validate_StockLedgerPickPackShip(String productid, String expectedQty, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_StockLedgerReport.properties");
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandReport(driver);

			Utilities.HoverandClick(pro.getProperty("StockLedgerReportLink"), driver);
			Thread.sleep(1500);
			Utilities.clickExpander(driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("QuickSearch"), driver);
			Utilities.HoverandClick(pro.getProperty("StockLedgerReportFetchBtn"), driver);

			// select all
			Utilities.clickCheckBox("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[1]", "uncheck",
					driver);
			Utilities.HoverandClick("//*[text()='Show']/parent::td/following::td[1]/div/img", driver);
			Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='All']", driver);
			Utilities.clickButton("Expand", 1000, driver);
			Thread.sleep(2000);

			String[] expBalQty = expectedQty.split(",");
			// int headerSize=Utilities.totalSize("//*[text()='Product
			// ID']/ancestor::div[1]/table/thead/tr/td/div", driver);

			int totalResult = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div",
					driver);
			for (int i = 1; i <= totalResult; i++) {
				int switchToCase = 0;
				String actualBalQty = null;

				String prodID = driver.findElement(By.xpath(
						"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i + "]/div[1]/div"))
						.getText();
				if (prodID.equalsIgnoreCase(productid)) {
					switchToCase = 1;
				}
				if (prodID.endsWith("B2")) {
					switchToCase = 2;
				}
				if (prodID.endsWith("S3")) {
					switchToCase = 3;
				}
				if (prodID.endsWith("BS4")) {
					switchToCase = 4;
				}

				actualBalQty = driver
						.findElement(By.xpath("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
								+ "]/div[2]/div/table/tbody/tr//*[contains(text(),'Quantity on Hand:')]"))
						.getText();
				switch (switchToCase) {
				case 1:
					assertEquals("Quantity on Hand: " + expBalQty[0], actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : is [" + actualBalQty
							+ "] matched with UI");
					break;

				case 2:
					assertEquals("Quantity on Hand: " + expBalQty[1], actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : is [" + actualBalQty
							+ "] matched with UI");
					break;

				case 3:
					assertEquals("Quantity on Hand: " + expBalQty[2], actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : is [" + actualBalQty
							+ "] matched with UI");
					break;

				case 4:
					assertEquals("Quantity on Hand: " + expBalQty[3], actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : is [" + actualBalQty
							+ "] matched with UI");
					break;
				}
			}

			Utilities.HoverandClick(pro.getProperty("StockLedgerReportCloseBtn"), driver);
			System.out.println("****** Stock Ledger successfully verified for  [" + productid + "] **************");

		} catch (Exception Ex) {
			System.out.println("****** Stock Ledger FAILED to verify for [" + productid + "]  check LOG**************");
			Utilities.handleError(Ex, driver);
		}

	}

	// ********************************************************StockLedgerReport
	// Report*********************************************************
	public static void validate_QuantityonHand(String productid, String qtyOnHand, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_StockLedgerReport.properties");

			Utilities.refresh();
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			WebElement element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("InventoryExpander")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("ReportsExpander")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockLedgerReportLink")));
				}
			});
			element.click();
			Thread.sleep(2000);
			WebElement search = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("QuickSearch")));
				}
			});
			search.click();
			search.sendKeys(productid);

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockLedgerReportFetchBtn")));
				}
			});
			element.click();
			Thread.sleep(5000);

			List<WebElement> ResultCount = driver.findElements(By.xpath(
					"//div[@id='stockledger']/div/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div/div"));
			int resultSetTotalCnt = ResultCount.size();

			for (int j = 1; j <= resultSetTotalCnt; j++) {
				WebElement rowExpander = driver.findElement(By
						.xpath(".//div[@id='stockledger']/div/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div/div["
								+ j + "]"));
				rowExpander.click();
			}

			int qtyOnHandyRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath(".//div[@id='stockledger']/div/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td/div"))
					.size();
			Thread.sleep(1000);
			for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
				String header = driver.findElement(By
						.xpath(".//div[@id='stockledger']/div/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equals("Stock Rate(SGD)")) {
					qtyOnHandyRowIndex = rowIndex;
				}
			}

			for (int j = 1; j <= resultSetTotalCnt; j++) {
				int index = 0;
				String productIDStr = driver.findElement(By
						.xpath(".//div[@id='stockledger']/div/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div/div["
								+ j + "]/div[1]/div"))
						.getText();
				System.out.println("productIDStr > > " + productIDStr);
				if (productIDStr.endsWith("S3")) {
					index = 4;
				} else if (productIDStr.endsWith("B2")) {
					index = 2;
				} else if (productIDStr.endsWith("BS4")) {
					index = 3;
				} else if (productIDStr.equalsIgnoreCase(productid)) {
					index = 1;
				}
				switch (index) {
				case 1:
					String qtyOnHandStr = driver.findElement(By
							.xpath(".//div[@id='stockledger']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div[2]/div/div["
									+ index + "]/div[2]/div[3]/table/tbody/tr/td[" + qtyOnHandyRowIndex + "]/div"))
							.getText();
					if (!Utilities.isNullOrEmpty(qtyOnHandStr)) {
						String[] qtyOnHandStrArray = qtyOnHandStr.split(":");
						if (qtyOnHandStrArray != null && qtyOnHandStrArray.length > 1) {
							String qty = qtyOnHandStrArray[1];
							assertEquals(qtyOnHand, qty.trim());
							System.out.println("Verified Quantity on Hand For Normal Product = " + productIDStr
									+ " : Quantity on Hand [" + qty.trim() + "] matched with UI");
						}
					}
					break;
				case 2:
					qtyOnHandStr = driver.findElement(By
							.xpath(".//div[@id='stockledger']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div[2]/div/div["
									+ index + "]/div[2]/div[3]/table/tbody/tr/td[" + qtyOnHandyRowIndex + "]/div"))
							.getText();
					if (!Utilities.isNullOrEmpty(qtyOnHandStr)) {
						String[] qtyOnHandStrArray = qtyOnHandStr.split(":");
						if (qtyOnHandStrArray != null && qtyOnHandStrArray.length > 1) {
							String qty = qtyOnHandStrArray[1];
							assertEquals(qtyOnHand, qty.trim());
							System.out.println("Verified Quantity on Hand For Batch Product = " + productIDStr
									+ " : Quantity on Hand [" + qty.trim() + "] matched with UI");
						}
					}
					break;
				case 3:
					qtyOnHandStr = driver.findElement(By
							.xpath(".//div[@id='stockledger']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div[2]/div/div["
									+ index + "]/div[2]/div[3]/table/tbody/tr/td[" + qtyOnHandyRowIndex + "]/div"))
							.getText();
					if (!Utilities.isNullOrEmpty(qtyOnHandStr)) {
						String[] qtyOnHandStrArray = qtyOnHandStr.split(":");
						if (qtyOnHandStrArray != null && qtyOnHandStrArray.length > 1) {
							String qty = qtyOnHandStrArray[1];
							assertEquals(qtyOnHand, qty.trim());
							System.out.println("Verified Quantity on Hand For Batch Serial Product = " + productIDStr
									+ " : Quantity on Hand [" + qty.trim() + "] matched with UI");
						}
					}
					break;
				case 4:
					qtyOnHandStr = driver.findElement(By
							.xpath(".//div[@id='stockledger']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div[2]/div/div["
									+ index + "]/div[2]/div[3]/table/tbody/tr/td[" + qtyOnHandyRowIndex + "]/div"))
							.getText();
					if (!Utilities.isNullOrEmpty(qtyOnHandStr)) {
						String[] qtyOnHandStrArray = qtyOnHandStr.split(":");
						if (qtyOnHandStrArray != null && qtyOnHandStrArray.length > 1) {
							String qty = qtyOnHandStrArray[1];
							assertEquals(qtyOnHand, qty.trim());
							System.out.println("Verified Quantity on Hand For Serial Product = " + productIDStr
									+ " : Quantity on Hand [" + qty.trim() + "] matched with UI");
						}
					}
					break;
				}

			}
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockLedgerReportCloseBtn")));
				}
			});
			element.click();
			Thread.sleep(2000);
			System.out.println(
					"************************************************* 'Stock Ledger Report' is verified*************************************************");

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}
	}

}
