package com.krawler.erp.inventory.reports.SR;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import krawler.erp.page.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class StockAgeingReport {
	// ********************************************************StockAgeingReport
	// Report*********************************************************
	public static void validate_StockAgeingReport(String productid, Map<String, Object> productsQtyMap,
			WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_StockAgeingReport.properties");

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
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockAgeingReportLink")));
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

			Thread.sleep(2000);
			int resultSetTotalCnt = 4;
			int qtyLessThan45RowIndex = 0, amtLessThan45RowIndex = 0, qtyBetween45to90RowIndex = 0,
					amtBetween45to90RowIndex = 0, qtyOver90RowIndex = 0, amtOver90RowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td/div"))
					.size();
			Map<Object, String> mp1 = null;
			Thread.sleep(1000);

			String searchText = driver
					.findElement(By
							.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div[1]/div[1]"))
					.getText();
			if (!Utilities.isNullOrEmpty(searchText)
					&& !searchText.equalsIgnoreCase("There is no record to display.")) {

				for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
					String header = driver.findElement(By
							.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ rowIndex + "]/div"))
							.getText();
					if (header.equals("Quantity (0 - 45 Days)")) {
						qtyLessThan45RowIndex = rowIndex;

					} else if (header.equals("Amount (0 - 45 Days)")) {
						amtLessThan45RowIndex = rowIndex;

					} else if (header.equals("Quantity (45 - 90 Days)")) {
						qtyBetween45to90RowIndex = rowIndex;

					} else if (header.equals("Amount (45 - 90 Days)")) {
						amtBetween45to90RowIndex = rowIndex;

					} else if (header.equals("Quantity (Over 90 Days)")) {
						qtyOver90RowIndex = rowIndex;

					} else if (header.equals("Amount (Over 90 Days)")) {
						amtOver90RowIndex = rowIndex;

					}
				}
				for (int j = 1; j <= resultSetTotalCnt; j++) {
					int index = 0;
					String productIDStr = driver.findElement(
							By.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["
									+ j + "]/div[1]/div"))
							.getText();
					if (productIDStr.endsWith("S3")) {
						index = 4;
						if (!Utilities.isNullOrEmpty(productIDStr)) {
							String productName = productIDStr.split(":")[1].trim();
							mp1 = (Map<Object, String>) productsQtyMap.get(productName);
						}
					} else if (productIDStr.endsWith("B2")) {
						index = 2;
						if (!Utilities.isNullOrEmpty(productIDStr)) {
							String productName = productIDStr.split(":")[1].trim();
							mp1 = (Map<Object, String>) productsQtyMap.get(productName);
						}
					} else if (productIDStr.endsWith("BS4")) {
						index = 3;
						if (!Utilities.isNullOrEmpty(productIDStr)) {
							String productName = productIDStr.split(":")[1].trim();
							mp1 = (Map<Object, String>) productsQtyMap.get(productName);
						}
					} else if (productIDStr.equalsIgnoreCase("Product ID: " + productid)) {
						index = 1;
						if (!Utilities.isNullOrEmpty(productIDStr)) {
							String productName = productIDStr.split(":")[1].trim();
							mp1 = (Map<Object, String>) productsQtyMap.get(productName);
							// for (Map.Entry<Object, String> entry :
							// mp1.entrySet()) {
							// System.out.println("Key = " + entry.getKey() + ",
							// Value = " + entry.getValue());
							// }
						}
					}
					switch (index) {
					case 1:
						String qtyLessThan45 = driver.findElement(By
								.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["
										+ index + "]/div[3]/table/tbody/tr/td[" + qtyLessThan45RowIndex + "]/div"))
								.getText();
						// String amtLessThan45 =
						// driver.findElement(By.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["+index+"]/div[3]/table/tbody/tr/td["+amtLessThan45RowIndex+"]/div")).getText();
						String qtyBetween45to90 = driver.findElement(By
								.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["
										+ index + "]/div[3]/table/tbody/tr/td[" + qtyBetween45to90RowIndex + "]/div"))
								.getText();
						// String amtBetween45to90 =
						// driver.findElement(By.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["+index+"]/div[3]/table/tbody/tr/td["+amtBetween45to90RowIndex+"]/div")).getText();
						String qtyOver90 = driver
								.findElement(By
										.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["
												+ index + "]/div[3]/table/tbody/tr/td[" + qtyOver90RowIndex + "]/div"))
								.getText();
						// String amtOver90 =
						// driver.findElement(By.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["+index+"]/div[3]/table/tbody/tr/td["+amtOver90RowIndex+"]/div")).getText();
						assertEquals(mp1.get("Quantity (0 - 45 Days)"), qtyLessThan45.trim());
						System.out.println("Verified Quantity (0 - 45 Days) For Normal Product = " + productid
								+ " : Quantity (0 - 45 Days) " + qtyLessThan45.trim() + " matched with UI");
						assertEquals(mp1.get("Quantity (45 - 90 Days)"), qtyBetween45to90.trim());
						System.out.println("Verified Quantity (45 - 90 Days) For Normal Product = " + productid
								+ " : Quantity (45 - 90 Days) " + qtyBetween45to90.trim() + " matched with UI");
						assertEquals(mp1.get("Quantity (Over 90 Days)"), qtyOver90.trim());
						System.out.println("Verified Quantity (Over 90 Days) For Normal Product = " + productid
								+ " : Quantity (Over 90 Days) " + qtyOver90.trim() + " matched with UI");
						break;
					case 2:
						qtyLessThan45 = driver.findElement(By
								.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["
										+ index + "]/div[3]/table/tbody/tr/td[" + qtyLessThan45RowIndex + "]/div"))
								.getText();
						// amtLessThan45 =
						// driver.findElement(By.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["+index+"]/div[3]/table/tbody/tr/td["+amtLessThan45RowIndex+"]/div")).getText();
						qtyBetween45to90 = driver.findElement(By
								.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["
										+ index + "]/div[3]/table/tbody/tr/td[" + qtyBetween45to90RowIndex + "]/div"))
								.getText();
						// amtBetween45to90 =
						// driver.findElement(By.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["+index+"]/div[3]/table/tbody/tr/td["+amtBetween45to90RowIndex+"]/div")).getText();
						qtyOver90 = driver
								.findElement(By
										.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["
												+ index + "]/div[3]/table/tbody/tr/td[" + qtyOver90RowIndex + "]/div"))
								.getText();
						// amtOver90 =
						// driver.findElement(By.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["+index+"]/div[3]/table/tbody/tr/td["+amtOver90RowIndex+"]/div")).getText();
						assertEquals(mp1.get("Quantity (0 - 45 Days)"), qtyLessThan45.trim());
						System.out.println("Verified Quantity (0 - 45 Days) For Batch Product = " + productid
								+ " : Quantity (0 - 45 Days) " + qtyLessThan45.trim() + " matched with UI");
						assertEquals(mp1.get("Quantity (45 - 90 Days)"), qtyBetween45to90.trim());
						System.out.println("Verified Quantity (45 - 90 Days) For Batch Product = " + productid
								+ " : Quantity (45 - 90 Days) " + qtyBetween45to90.trim() + " matched with UI");
						assertEquals(mp1.get("Quantity (Over 90 Days)"), qtyOver90.trim());
						System.out.println("Verified Quantity (Over 90 Days) For Batch Product = " + productid
								+ " : Quantity (Over 90 Days) " + qtyOver90.trim() + " matched with UI");
						break;
					case 3:
						qtyLessThan45 = driver.findElement(By
								.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["
										+ index + "]/div[3]/table/tbody/tr/td[" + qtyLessThan45RowIndex + "]/div"))
								.getText();
						// amtLessThan45 =
						// driver.findElement(By.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["+index+"]/div[3]/table/tbody/tr/td["+amtLessThan45RowIndex+"]/div")).getText();
						qtyBetween45to90 = driver.findElement(By
								.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["
										+ index + "]/div[3]/table/tbody/tr/td[" + qtyBetween45to90RowIndex + "]/div"))
								.getText();
						// amtBetween45to90 =
						// driver.findElement(By.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["+index+"]/div[3]/table/tbody/tr/td["+amtBetween45to90RowIndex+"]/div")).getText();
						qtyOver90 = driver
								.findElement(By
										.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["
												+ index + "]/div[3]/table/tbody/tr/td[" + qtyOver90RowIndex + "]/div"))
								.getText();
						// amtOver90 =
						// driver.findElement(By.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["+index+"]/div[3]/table/tbody/tr/td["+amtOver90RowIndex+"]/div")).getText();
						assertEquals(mp1.get("Quantity (0 - 45 Days)"), qtyLessThan45.trim());
						System.out.println("Verified Quantity (0 - 45 Days) For Batch Serial Product = " + productid
								+ " : Quantity (0 - 45 Days) " + qtyLessThan45.trim() + " matched with UI");
						assertEquals(mp1.get("Quantity (45 - 90 Days)"), qtyBetween45to90.trim());
						System.out.println("Verified Quantity (45 - 90 Days) For Batch Serial Product = " + productid
								+ " : Quantity (45 - 90 Days) " + qtyBetween45to90.trim() + " matched with UI");
						assertEquals(mp1.get("Quantity (Over 90 Days)"), qtyOver90.trim());
						System.out.println("Verified Quantity (Over 90 Days) For Batch Serial Product = " + productid
								+ " : Quantity (Over 90 Days) " + qtyOver90.trim() + " matched with UI");
						break;
					case 4:
						qtyLessThan45 = driver.findElement(By
								.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["
										+ index + "]/div[3]/table/tbody/tr/td[" + qtyLessThan45RowIndex + "]/div"))
								.getText();
						// amtLessThan45 =
						// driver.findElement(By.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["+index+"]/div[3]/table/tbody/tr/td["+amtLessThan45RowIndex+"]/div")).getText();
						qtyBetween45to90 = driver.findElement(By
								.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["
										+ index + "]/div[3]/table/tbody/tr/td[" + qtyBetween45to90RowIndex + "]/div"))
								.getText();
						// amtBetween45to90 =
						// driver.findElement(By.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["+index+"]/div[3]/table/tbody/tr/td["+amtBetween45to90RowIndex+"]/div")).getText();
						qtyOver90 = driver
								.findElement(By
										.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["
												+ index + "]/div[3]/table/tbody/tr/td[" + qtyOver90RowIndex + "]/div"))
								.getText();
						// amtOver90 =
						// driver.findElement(By.xpath(".//div[@id='stockageingdetail']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div["+index+"]/div[3]/table/tbody/tr/td["+amtOver90RowIndex+"]/div")).getText();
						assertEquals(mp1.get("Quantity (0 - 45 Days)"), qtyLessThan45.trim());
						System.out.println("Verified Quantity (0 - 45 Days) For Serial Product = " + productid
								+ " : Quantity (0 - 45 Days) " + qtyLessThan45.trim() + " matched with UI");
						assertEquals(mp1.get("Quantity (45 - 90 Days)"), qtyBetween45to90.trim());
						System.out.println("Verified Quantity (45 - 90 Days) For Serial Product = " + productid
								+ " : Quantity (45 - 90 Days) " + qtyBetween45to90.trim() + " matched with UI");
						assertEquals(mp1.get("Quantity (Over 90 Days)"), qtyOver90.trim());
						System.out.println("Verified Quantity (Over 90 Days) For Serial Product = " + productid
								+ " : Quantity (Over 90 Days) " + qtyOver90.trim() + " matched with UI");
						break;
					}

				}
			} else {
				System.out.println("No Stock Ageing Report found for the given Product = " + productid);
			}
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockAgeingReportCloseBtn")));
				}
			});
			element.click();
			Thread.sleep(2000);
		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}
	}

}
