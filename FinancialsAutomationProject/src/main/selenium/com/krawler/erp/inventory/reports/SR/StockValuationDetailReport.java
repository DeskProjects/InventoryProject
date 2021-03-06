package com.krawler.erp.inventory.reports.SR;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

import krawler.erp.page.Utilities;

public class StockValuationDetailReport {

	// ********************************************************Stock Valuation
	// Detail Report*********************************************************
	public static void productsQtyMap(String productid, String balanceQty, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_StockValuationDetailReport.properties");
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
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockValuationDetailReportLink")));
				}
			});
			element.click();
			Thread.sleep(2000);

			WebElement element1 = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(".//*[@id='navigationpanel']/div[1]/div"));
				}
			});
			element1.click(); // Most imp expander
			Thread.sleep(2000);

			WebElement search = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("QuickSearch")));
				}
			});
			search.click();
			search.sendKeys(productid);
			Thread.sleep(2000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					return webDriverArg.findElement(By.xpath(pro.getProperty("Fetch")));
				}
			});
			element.click();
			Thread.sleep(5000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					return webDriverArg.findElement(By.xpath(pro.getProperty("TotalResult")));
				}
			});

			String resultText = element.getText();
			String[] resultTextArray = resultText.split("of");
			String resultSetTotalCntString = "";
			for (String resultTextElement : resultTextArray) {
				resultSetTotalCntString = resultTextElement;
			}
			int resultSetTotalCnt = Integer.parseInt(resultSetTotalCntString.trim());
			System.out.println("resultSetTotalCnt > > > " + resultSetTotalCnt);

			int productIDRowIndex = 0, balanceQtyRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By.xpath("//div[@class='x-grid3-header']/div/div[1]/table/thead/tr/td")).size();
			Thread.sleep(1000);

			Map<Object, String> mp1 = null;
			for (int rowIndex = 1; rowIndex < tableHeadersCnt + 1; rowIndex++) {
				String header = driver
						.findElement(By.xpath(
								"//div[@class='x-grid3-header']/div/div[1]/table/thead/tr/td[" + rowIndex + "]/div"))
						.getText();
				if (header.equals("Product ID")) {
					productIDRowIndex = rowIndex;

				} else if (header.equals("Quantity")) {
					balanceQtyRowIndex = rowIndex;

				}
			}

			for (int j = 1; j <= resultSetTotalCnt; j++) {
				int index = 0;
				String productIDStr = driver.findElement(By.xpath(
						"//div[@class='x-grid3-scroller']/div/div[" + j + "]/div/div[contains(text(),'Product ID')]"))
						.getText();
				System.out.println("productIDStr = " + productIDStr);
				if (productIDStr.endsWith("S3")) {
					index = 4;
					if (!Utilities.isNullOrEmpty(productIDStr)) {
						String productName = productIDStr.split(":")[1].trim();
					}
				} else if (productIDStr.endsWith("B2")) {
					index = 2;
					if (!Utilities.isNullOrEmpty(productIDStr)) {
						String productName = productIDStr.split(":")[1].trim();
					}
				} else if (productIDStr.endsWith("BS4")) {
					index = 3;
					if (!Utilities.isNullOrEmpty(productIDStr)) {
						String productName = productIDStr.split(":")[1].trim();
					}
				} else if (productIDStr.equalsIgnoreCase("Product ID: " + productid)) {
					index = 1;
					if (!Utilities.isNullOrEmpty(productIDStr)) {
						String productName = productIDStr.split(":")[1].trim();
					}
				}

				switch (index) {
				case 1:
					assertEquals(
							balanceQty, driver
									.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div[" + index
											+ "]/div[2]/div/table/tbody/tr/td[" + balanceQtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Total Quantity For Normal Product = " + productid
							+ " : Balance Quantity " + balanceQty + " matched with UI");

					break;
				case 2:
					assertEquals(
							balanceQty, driver
									.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div[" + index
											+ "]/div[2]/div/table/tbody/tr/td[" + balanceQtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Total Quantity For Batch Product = " + productid
							+ " : Balance Quantity " + balanceQty + " matched with UI");

					break;
				case 3:
					assertEquals(
							balanceQty, driver
									.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div[" + index
											+ "]/div[2]/div/table/tbody/tr/td[" + balanceQtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Total Quantity For Batch Serial Product = " + productid
							+ " : Balance Quantity " + balanceQty + " matched with UI");
					break;
				case 4:
					assertEquals(
							balanceQty, driver
									.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div[" + index
											+ "]/div[2]/div/table/tbody/tr/td[" + balanceQtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Total Quantity For Serial Product = " + productid
							+ " : Balance Quantity " + balanceQty + " matched with UI");
					break;
				}

			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockValuationDetailReportClsBtn")));
				}
			});
			element.click();
			Thread.sleep(2000);
			System.out.println(
					"************************************************* 'Stock Valuation Detail Report' is verified*************************************************");

		}

		catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);

		}
	}
}
