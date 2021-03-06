package com.krawler.erp.inventory.reports.SR;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import krawler.erp.page.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class StockStatusReport {
	// ********************************************************Stock Status
	// Report*********************************************************
	public static void validate_StockStatusReport(String productid, String qtyOnHand, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_StockStatusReport.properties");

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
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockStatusReportLink")));
				}
			});
			element.click();
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
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockStatusReportFetchBtn")));
				}
			});
			element.click();
			Thread.sleep(4000);

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockStatusReportResultCount")));
				}
			});

			String resultText = element.getText();
			String[] resultTextArray = resultText.split("of");
			String resultSetTotalCntString = "";
			for (String resultTextElement : resultTextArray) {
				resultSetTotalCntString = resultTextElement;
			}
			int resultSetTotalCnt = Integer.parseInt(resultSetTotalCntString.trim());
			int productIDRowIndex = 0, qtyOnHandRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath("//div[@id='stockStatus']/div/div[2]/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td/div"))
					.size();
			Thread.sleep(1000);

			for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
				String header = driver.findElement(By
						.xpath("//div[@id='stockStatus']/div/div[2]/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equals("Product ID")) {
					productIDRowIndex = rowIndex;

				} else if (header.equals("Quantity On Hand")) {
					qtyOnHandRowIndex = rowIndex;

				}
			}
			for (int j = 1; j <= resultSetTotalCnt; j++) {
				int index = 0;
				String productIDStr = driver.findElement(
						By.xpath("//div[@id='stockStatus']/div/div[2]/div/div/div/div/div[1]/div[2]/div/div[" + j
								+ "]/table/tbody/tr/td[" + productIDRowIndex + "]/div"))
						.getText();
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
					assertEquals(
							qtyOnHand, driver
									.findElement(By
											.xpath("//div[@id='stockStatus']/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
													+ j + "]/table/tbody/tr/td[" + qtyOnHandRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Quantity On Hand For Normal Product = " + productIDStr
							+ " : Balance Quantity [" + qtyOnHand + "] matched with UI");

					break;
				case 2:
					assertEquals(
							qtyOnHand, driver
									.findElement(By
											.xpath("//div[@id='stockStatus']/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
													+ j + "]/table/tbody/tr/td[" + qtyOnHandRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Quantity On Hand For Batch Product = " + productIDStr
							+ " : Balance Quantity [" + qtyOnHand + "] matched with UI");
					break;
				case 3:
					assertEquals(
							qtyOnHand, driver
									.findElement(By
											.xpath("//div[@id='stockStatus']/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
													+ j + "]/table/tbody/tr/td[" + qtyOnHandRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Quantity On Hand For Batch Serial Product = " + productIDStr
							+ " :  Balance Quantity [" + qtyOnHand + "] matched with UI");
					break;
				case 4:
					assertEquals(
							qtyOnHand, driver
									.findElement(By
											.xpath("//div[@id='stockStatus']/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
													+ j + "]/table/tbody/tr/td[" + qtyOnHandRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Quantity On Hand For Serial Product = " + productIDStr
							+ " : Balance Quantity [" + qtyOnHand + "] matched with UI");
					break;
				}

			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockStatusReportCloseBtn")));
				}
			});
			element.click();
			Thread.sleep(2000);
			System.out.println(
					"************************************************* 'Stock Status Report' is verified*************************************************");

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}
	}

}
