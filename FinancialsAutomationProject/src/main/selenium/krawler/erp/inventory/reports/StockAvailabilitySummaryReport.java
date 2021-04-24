package krawler.erp.inventory.reports;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

import krawler.erp.page.Utilities;

public class StockAvailabilitySummaryReport {
	// ********************************************************Stock
	// Availability Summary
	// Report*********************************************************
	public static void validate_QuantitiesForProduct(String productid, String balanceQty, String blockedQty,
			WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_StockAvailabilitySummaryReport.properties");
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
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockAvailabilitySummaryReportLink")));
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

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("QuickSearch")));
				}
			});
			search.click();
			search.sendKeys(productid);
			Thread.sleep(4000);
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockAvailabilitySummaryResultCount")));
				}
			});

			String resultText = element.getText();
			String[] resultTextArray = resultText.split("of");
			String resultSetTotalCntString = "";
			for (String resultTextElement : resultTextArray) {
				resultSetTotalCntString = resultTextElement;
			}
			int resultSetTotalCnt = Integer.parseInt(resultSetTotalCntString.trim());

			int productIDRowIndex = 0, balanceQtyRowIndex = 0, blockedQtyRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath(".//div[@id='inventoryList1storewiseInventoryLevelSummary']/div/div[2]/div/div/div/div/div/table/thead/tr/td"))
					.size();
			Thread.sleep(1000);
			for (int rowIndex = 1; rowIndex < tableHeadersCnt + 1; rowIndex++) {
				String header = driver.findElement(By
						.xpath("//div[@id='inventoryList1storewiseInventoryLevelSummary']/div/div[2]/div/div/div/div/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equals("Product ID")) {
					productIDRowIndex = rowIndex;

				} else if (header.equals("Total Quantity")) {
					balanceQtyRowIndex = rowIndex;

				} else if (header.equals("Blocked Quantity")) {
					blockedQtyRowIndex = rowIndex;

				}
			}

			for (int j = 1; j <= resultSetTotalCnt; j++) {
				int index = 0;
				String productIDStr = driver.findElement(By
						.xpath("//div[@id='inventoryList1storewiseInventoryLevelSummary']/div/div[2]/div/div/div/div/div["
								+ j + "]/table/tbody/tr/td[" + productIDRowIndex + "]/div"))
						.getText();
				System.out.println("productIDStr = " + productIDStr);
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
							balanceQty, driver
									.findElement(By
											.xpath("//div[@id='inventoryList1storewiseInventoryLevelSummary']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + balanceQtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Total Quantity For Normal Product = " + productid
							+ " : Balance Quantity " + balanceQty + " matched with UI");
					assertEquals(
							blockedQty, driver
									.findElement(By
											.xpath("//div[@id='inventoryList1storewiseInventoryLevelSummary']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + blockedQtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Blocked Quantity For Normal Product = " + productid
							+ " : Blocked Quantity " + blockedQty + " matched with UI");
					break;
				case 2:
					assertEquals(
							balanceQty, driver
									.findElement(By
											.xpath("//div[@id='inventoryList1storewiseInventoryLevelSummary']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + balanceQtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Total Quantity For Batch Product = " + productid
							+ " : Balance Quantity " + balanceQty + " matched with UI");
					assertEquals(
							blockedQty, driver
									.findElement(By
											.xpath("//div[@id='inventoryList1storewiseInventoryLevelSummary']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + blockedQtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Blocked Quantity For Batch Product = " + productid
							+ " : Blocked Quantity " + blockedQty + " matched with UI");
					break;
				case 3:
					assertEquals(
							balanceQty, driver
									.findElement(By
											.xpath("//div[@id='inventoryList1storewiseInventoryLevelSummary']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + balanceQtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Total Quantity For Batch Serial Product = " + productid
							+ " :  Balance Quantity " + balanceQty + " matched with UI");
					assertEquals(
							blockedQty, driver
									.findElement(By
											.xpath("//div[@id='inventoryList1storewiseInventoryLevelSummary']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + blockedQtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Blocked Quantity For Batch Serial Product = " + productid
							+ " :  Blocked Quantity " + blockedQty + " matched with UI");
					break;
				case 4:
					assertEquals(
							balanceQty, driver
									.findElement(By
											.xpath("//div[@id='inventoryList1storewiseInventoryLevelSummary']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + balanceQtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Total Quantity For Serial Product = " + productid
							+ " : Balance Quantity " + balanceQty + " matched with UI");
					assertEquals(
							blockedQty, driver
									.findElement(By
											.xpath("//div[@id='inventoryList1storewiseInventoryLevelSummary']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + blockedQtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Blocked Quantity For Serial Product = " + productid
							+ " : Blocked Quantity " + blockedQty + " matched with UI");
					break;
				}
			}
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockAvailabilitySummaryReportBtn")));
				}
			});
			element.click();
			Thread.sleep(2000);
			System.out.println(
					"************************************************* 'Stock Availability Summary Report' is verified*************************************************");

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);

		}
	}
}