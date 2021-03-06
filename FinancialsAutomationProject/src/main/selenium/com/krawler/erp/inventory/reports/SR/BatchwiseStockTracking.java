package com.krawler.erp.inventory.reports.SR;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

import krawler.erp.page.Utilities;

public class BatchwiseStockTracking {

	// ********************************************************Batch wise
	// StockTracking ********************************************************
	public static void validate_BatchwiseStockTracking(String productid, String balanceQty, String batch,
			WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_BatchwiseStockTracking.properties");
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
					return webDriverArg.findElement(By.xpath(pro.getProperty("BatchwiseStockTrackingLink")));
				}
			});
			element.click();
			Thread.sleep(3000);

			WebElement element1 = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(".//*[@id='navigationpanel']/div[1]/div"));
				}
			});
			element1.click(); // Most imp expander
			Thread.sleep(3000);

			WebElement search = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("QuickSearch")));
				}
			});
			search.click();
			search.sendKeys(productid);
			Thread.sleep(3000);

			List<WebElement> totalLinks = driver
					.findElements(By.xpath("//div[@id='inventoryList']/div/div[2]/div/div/div[2]/div/div"));
			int resultTotalCnt = totalLinks.size();
			System.out.println("totalResultcnt > > " + resultTotalCnt);

			int productIDRowIndex = 0, qtyRowIndex = 0, batchRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(
							By.xpath("//div[@id='inventoryList']/div/div[2]/div/div/div/div/div/table/thead/tr/td"))
					.size();
			Thread.sleep(1000);

			for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
				String header = driver.findElement(
						By.xpath("//div[@id='inventoryList']/div/div[2]/div/div/div/div/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equals("Product ID")) {
					productIDRowIndex = rowIndex;

				} else if (header.equals("Quantity")) {
					qtyRowIndex = rowIndex;

				} else if (header.equals("LOT/BATCH #")) {
					batchRowIndex = rowIndex;

				}
			}

			for (int j = 1; j <= resultTotalCnt; j++) {
				int index = 0;
				String productIDStr = driver
						.findElement(By.xpath(
								"//div[@id='inventoryList']/div/div[2]/div/div/div[2]/div/div[" + j + "]/div[1]/div"))
						.getText();
				System.out.println("productIDStr > " + productIDStr);
				if (productIDStr.endsWith("S3")) {
					index = 4;
				} else if (productIDStr.endsWith("B2")) {
					index = 2;
				} else if (productIDStr.endsWith("BS4")) {
					index = 3;
				} else if (productIDStr.equalsIgnoreCase("Product ID: " + productid)) {
					index = 1;
				}

				switch (index) {
				case 1:
					assertEquals(balanceQty,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div[1]/div[" + index
									+ "]/div[3]/table/tbody/tr/td[" + qtyRowIndex + "]/div")).getText());
					System.out.println("Verified Total Quantity For Normal Product = " + productIDStr
							+ " : Balance Quantity " + balanceQty + " matched with UI");
					System.out.println("NO BATCH > > > Normal Product ");
					break;
				case 2:
					assertEquals(balanceQty,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div[1]/div[" + index
									+ "]/div[3]/table/tbody/tr/td[" + qtyRowIndex + "]/div")).getText());
					System.out.println("Verified Total Quantity For Batch Product = " + productIDStr
							+ " : Balance Quantity " + balanceQty + " matched with UI");
					assertEquals(batch, driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div["
							+ index + "]/div[2]/div/table/tbody/tr/td[" + batchRowIndex + "]/div")).getText());
					System.out.println("Verified batch/LOT For Batch Product = " + productIDStr + " : batch/LOT ["
							+ batch + "] matched with UI");
					break;
				case 3:
					assertEquals(balanceQty,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div[1]/div[" + index
									+ "]/div[3]/table/tbody/tr/td[" + qtyRowIndex + "]/div")).getText());
					System.out.println("Verified Total Quantity For Batch Serial Product = " + productIDStr
							+ " : Balance Quantity " + balanceQty + " matched with UI");
					assertEquals(
							batch, driver
									.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + index
											+ "]/div[2]/div/table/tbody/tr/td[" + batchRowIndex + "]/div  "))
									.getText());
					System.out.println("Verified batch/LOT For Batch Serial Product = " + productIDStr
							+ " : batch/LOT [" + batch + "] matched with UI");
					break;
				case 4:
					assertEquals(balanceQty,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div[1]/div[" + index
									+ "]/div[3]/table/tbody/tr/td[" + qtyRowIndex + "]/div")).getText());
					System.out.println("Verified Total Quantity For Serial Product = " + productIDStr
							+ " : Balance Quantity " + balanceQty + " matched with UI");
					System.out.println("NO BATCH > > > Serial Product \n");
					break;
				}

			}
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("batchWiseStockClsBtn")));
				}
			});
			element.click();
			Thread.sleep(2000);
			System.out.println(
					"************************************************* 'Batchwise Stock Tracking Report' is verified*************************************************");

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}
	}
}