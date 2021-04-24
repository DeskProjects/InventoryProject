package krawler.erp.inventory.reports;

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

public class MaterialInOutRegisterReport {

	// ********************************************************MaterialInOutRegister
	// Report*********************************************************
	public static void validate_MaterialInOutRegisterReport(String productid, Map<String, Object> productsQtyMap,
			WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_MaterialInOutRegisterReport.properties");

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
					return webDriverArg.findElement(By.xpath(pro.getProperty("MaterialInOutRegisterReportLink")));
				}
			});
			element.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='navigationpanel']/div[1]/div[1]")).click();
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
					return webDriverArg.findElement(By.xpath(pro.getProperty("MaterialInOutRegisterReportSearchBtn")));
				}
			});
			element.click();
			Thread.sleep(5000);

			int resultSetTotalCnt = 4;
			int productIDRowIndex = 0, qtyRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath(".//div[@id='TransactionBalanceReportTab']/div/div/div/div/div[2]/div/div[1]/div/div/div/table/thead/tr/td/div"))
					.size();
			Map<Object, String> mp1 = null;
			Thread.sleep(1000);
			String searchText = driver
					.findElement(By
							.xpath(".//div[@id='TransactionBalanceReportTab']/div/div/div/div/div[2]/div/div[1]/div[2]/div"))
					.getText();

			if (!Utilities.isNullOrEmpty(searchText)) {
				System.out.println("MaterialIn/Out found for the given Product = " + productid);
				for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
					String header = driver.findElement(By
							.xpath(".//div[@id='TransactionBalanceReportTab']/div/div/div/div/div[2]/div/div[1]/div/div/div/table/thead/tr/td["
									+ rowIndex + "]/div"))
							.getText();
					if (header.equals("Product ID")) {
						productIDRowIndex = rowIndex;

					} else if (header.equals("Quantity")) {
						qtyRowIndex = rowIndex;

					}
				}
				for (int j = 1; j <= resultSetTotalCnt; j++) {
					int index = 0;
					String productIDStr = driver.findElement(By
							.xpath(".//div[@id='TransactionBalanceReportTab']/div/div/div/div/div[2]/div/div/div[2]/div/div["
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
						String balanceQty = driver
								.findElement(By
										.xpath(".//div[@id='TransactionBalanceReportTab']/div/div/div/div/div[2]/div/div/div[2]/div/div["
												+ index + "]/div[3]/table/tbody/tr/td[" + qtyRowIndex + "]/div"))
								.getText();
						assertEquals(mp1.get("Quantity"), balanceQty.trim());
						System.out.println("Verified Balance Quantity For Normal Product = " + productid
								+ " : Balance Quantity " + balanceQty.trim() + " matched with UI");
						break;
					case 2:
						balanceQty = driver
								.findElement(By
										.xpath(".//div[@id='TransactionBalanceReportTab']/div/div/div/div/div[2]/div/div/div[2]/div/div["
												+ index + "]/div[3]/table/tbody/tr/td[" + qtyRowIndex + "]/div"))
								.getText();
						//
						assertEquals(mp1.get("Quantity"), balanceQty.trim());
						System.out.println("Verified Balance Quantity For Batch Product = " + productid
								+ " : Balance Quantity " + balanceQty.trim() + " matched with UI");

						break;
					case 3:
						balanceQty = driver
								.findElement(By
										.xpath(".//div[@id='TransactionBalanceReportTab']/div/div/div/div/div[2]/div/div/div[2]/div/div["
												+ index + "]/div[3]/table/tbody/tr/td[" + qtyRowIndex + "]/div"))
								.getText();

						assertEquals(mp1.get("Quantity"), balanceQty.trim());
						System.out.println("Verified Balance Quantity For Batch Serial Product = " + productid
								+ " : Balance Quantity " + balanceQty.trim() + " matched with UI");

						break;
					case 4:
						balanceQty = driver
								.findElement(By
										.xpath(".//div[@id='TransactionBalanceReportTab']/div/div/div/div/div[2]/div/div/div[2]/div/div["
												+ index + "]/div[3]/table/tbody/tr/td[" + qtyRowIndex + "]/div"))
								.getText();
						assertEquals(mp1.get("Quantity"), balanceQty.trim());
						System.out.println("Verified Balance Quantity  For Serial Product = " + productid
								+ " : Balance Quantity " + balanceQty.trim() + " matched with UI");
						break;
					}

				}
			} else {
				System.out.println("No MaterialIn/Out found for the given Product = " + productid);
			}
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("MaterialInOutRegisterReportCloseBtn")));
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
