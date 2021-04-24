package krawler.erp.inventory.reports;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.sikuli.script.Key;

import com.google.common.base.Function;

import krawler.erp.page.Utilities;

public class StockMovementSummaryReport {
	// ********************************************************Stock
	// Availability Summary
	// Report*********************************************************
	public static void validate_StockMovementSummaryReport(String productid, String selectStore, String stockIn,
			String StockOut, String StockAdjustment, String BalanceQty, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_StockMovementSummaryReport.properties");
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
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockMovementSummaryReportLink")));
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

			WebElement search = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("QuickSearch")));
				}
			});
			search.click();
			search.sendKeys(productid);

			WebElement SelectStore = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("SelectStore")));
				}
			});
			SelectStore.click();
			SelectStore.sendKeys(selectStore);
			Thread.sleep(1000);
			SelectStore.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			WebElement searchb = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("SerachButton")));
				}
			});
			searchb.click();
			Thread.sleep(6000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockSummaryResultCount")));
				}
			});

			String resultText = element.getText();
			String[] resultTextArray = resultText.split("of");
			String resultSetTotalCntString = "";
			for (String resultTextElement : resultTextArray) {
				resultSetTotalCntString = resultTextElement;
			}
			int resultSetTotalCnt = Integer.parseInt(resultSetTotalCntString.trim());
			// System.out.println("resultSetTotalCnt > > > "+
			// resultSetTotalCnt);

			int productIDRowIndex = 0, stockInRowIndex = 0, StockOutRowIndex = 0, StockAdjRowIndex = 0,
					BalanceQtyRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div/table/thead/tr/td"))
					.size();
			Thread.sleep(1000);
			for (int rowIndex = 1; rowIndex < tableHeadersCnt + 1; rowIndex++) {
				String header = driver.findElement(By
						.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equals("Product ID")) {
					productIDRowIndex = rowIndex;

				} else if (header.equals("Stock IN")) {
					stockInRowIndex = rowIndex;

				} else if (header.equals("Stock OUT")) {
					StockOutRowIndex = rowIndex;

				} else if (header.equals("Stock Adjustment")) {
					StockAdjRowIndex = rowIndex;

				} else if (header.equals("Balance Quantity")) {
					BalanceQtyRowIndex = rowIndex;
				}
			}

			for (int j = 1; j <= resultSetTotalCnt; j++) {
				int index = 0;
				String productIDStr = driver.findElement(
						By.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div[" + j
								+ "]/table/tbody/tr/td[" + productIDRowIndex + "]/div"))
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
							stockIn, driver
									.findElement(By
											.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + stockInRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Stock In quantity For Normal Product = " + productIDStr
							+ " : Stock In quantity " + stockIn + " matched with UI");
					assertEquals(
							StockOut, driver
									.findElement(By
											.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + StockOutRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Stock Out quantity For Normal Product = " + productIDStr
							+ " : Stock Out quantity " + StockOut + " matched with UI");
					assertEquals(
							StockAdjustment, driver
									.findElement(By
											.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + StockAdjRowIndex + "]/div"))
									.getText());
					System.out.println("Verified StockAdjustment Quantity For Normal Product = " + productIDStr
							+ " : StockAdjustment Quantity " + StockAdjustment + " matched with UI");
					assertEquals(
							BalanceQty, driver
									.findElement(By
											.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + BalanceQtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Balance Quantity For Normal Product = " + productIDStr
							+ " : Balance Quantity " + BalanceQty + " matched with UI");
					break;
				case 2:
					assertEquals(
							stockIn, driver
									.findElement(By
											.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + stockInRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Stock In quantity For Batch Product = " + productIDStr
							+ " : Stock In quantity " + stockIn + " matched with UI");
					assertEquals(
							StockOut, driver
									.findElement(By
											.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + StockOutRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Stock Out quantity For Batch Product = " + productIDStr
							+ " : Stock Out quantity " + StockOut + " matched with UI");
					assertEquals(
							StockAdjustment, driver
									.findElement(By
											.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + StockAdjRowIndex + "]/div"))
									.getText());
					System.out.println("Verified StockAdjustment Quantity For Batch Product = " + productIDStr
							+ " : StockAdjustment Quantity " + StockAdjustment + " matched with UI");
					assertEquals(
							BalanceQty, driver
									.findElement(By
											.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + BalanceQtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Balance Quantity For Batch Product = " + productIDStr
							+ " : Balance Quantity " + BalanceQty + " matched with UI");
					break;
				case 3:
					assertEquals(
							stockIn, driver
									.findElement(By
											.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + stockInRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Stock In quantity For Batch with Serial Product = " + productIDStr
							+ " : Stock In quantity " + stockIn + " matched with UI");
					assertEquals(
							StockOut, driver
									.findElement(By
											.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + StockOutRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Stock Out quantity For Batch with Serial Product = " + productIDStr
							+ " : Stock Out quantity " + StockOut + " matched with UI");
					assertEquals(
							StockAdjustment, driver
									.findElement(By
											.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + StockAdjRowIndex + "]/div"))
									.getText());
					System.out.println("Verified StockAdjustment Quantity For Batch with Serial Product = "
							+ productIDStr + " : StockAdjustment Quantity " + StockAdjustment + " matched with UI");
					assertEquals(
							BalanceQty, driver
									.findElement(By
											.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + BalanceQtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Balance Quantity For Batch with Serial Product = " + productIDStr
							+ " : Balance Quantity " + BalanceQty + " matched with UI");
					break;
				case 4:
					assertEquals(
							stockIn, driver
									.findElement(By
											.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + stockInRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Stock In quantity For Serial Product = " + productIDStr
							+ " : Stock In quantity " + stockIn + " matched with UI");
					assertEquals(
							StockOut, driver
									.findElement(By
											.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + StockOutRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Stock Out quantity For Serial Product = " + productIDStr
							+ " : Stock Out quantity " + StockOut + " matched with UI");
					assertEquals(
							StockAdjustment, driver
									.findElement(By
											.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + StockAdjRowIndex + "]/div"))
									.getText());
					System.out.println("Verified StockAdjustment Quantity For Serial Product = " + productIDStr
							+ " : StockAdjustment Quantity " + StockAdjustment + " matched with UI");
					assertEquals(
							BalanceQty, driver
									.findElement(By
											.xpath("//div[@id='StockSummaryReportTab']/div/div/div/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + BalanceQtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Balance Quantity For Serial Product = " + productIDStr
							+ " : Balance Quantity " + BalanceQty + " matched with UI");
					break;
				}
			}
			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockSummaryReportCloseTab")));
				}
			});
			element.click();
			Thread.sleep(2000);
			System.out.println(
					"************************************************* 'Stock Movement Summary Report' is verified*************************************************");

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);

		}
	}
}