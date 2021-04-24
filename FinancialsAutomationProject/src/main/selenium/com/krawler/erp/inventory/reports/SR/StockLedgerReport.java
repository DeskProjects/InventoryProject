package com.krawler.erp.inventory.reports.SR;

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
			Utilities.disableExpander(driver);

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