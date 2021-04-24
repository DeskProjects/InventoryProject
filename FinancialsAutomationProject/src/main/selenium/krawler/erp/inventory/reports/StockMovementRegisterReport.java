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

public class StockMovementRegisterReport {

	// ********************************************************StockMovementRegister
	// Report*********************************************************
	public static void validate_StockMovementRegisterReportQuantityINOUT(String productid,
			Map<String, Object> productsQtyMap, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_StockMovementRegisterReport.properties");

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
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockMovementRegisterReportLink")));
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
			Thread.sleep(3000);

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg
							.findElement(By.xpath(pro.getProperty("StockMovementRegisterReportResultCount")));
				}
			});

			String resultText = element.getText();
			String[] resultTextArray = resultText.split("of");
			String resultSetTotalCntString = "";
			for (String resultTextElement : resultTextArray) {
				resultSetTotalCntString = resultTextElement;
			}
			int resultSetTotalCnt = Integer.parseInt(resultSetTotalCntString.trim());
			int productIDRowIndex = 0, qtyINRowIndex = 0, qtyOUTRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath(".//div[@id='StockMovementReportTab']/div/div/div/div/div[2]/div/div[1]/div/div/div/table/thead/tr/td/div"))
					.size();
			Map<Object, String> mp1 = null;
			Thread.sleep(1000);
			String searchText = driver
					.findElement(By
							.xpath(".//div[@id='StockMovementReportTab']/div/div/div/div/div[2]/div/div[1]/div[2]/div"))
					.getText();
			Utilities.zoomOut();
			Thread.sleep(2000);
			if (!Utilities.isNullOrEmpty(searchText)) {
				System.out.println("Stock Movement Register Details found for the given Product = " + productid);
				for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
					String header = driver.findElement(By
							.xpath(".//div[@id='StockMovementReportTab']/div/div/div/div/div[2]/div/div[1]/div/div/div/table/thead/tr/td["
									+ rowIndex + "]/div"))
							.getText();
					if (header.equals("Product ID")) {
						productIDRowIndex = rowIndex;

					} else if (header.equals("Quantity IN")) {
						qtyINRowIndex = rowIndex;

					} else if (header.equals("Quantity OUT")) {
						qtyOUTRowIndex = rowIndex;

					}
				}
				for (int j = 1; j <= resultSetTotalCnt; j++) {
					int index = 0;
					String productIDStr = driver.findElement(By
							.xpath(".//div[@id='StockMovementReportTab']/div/div/div/div/div[2]/div/div/div[2]/div/div["
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
						String qtyIN = driver
								.findElement(By
										.xpath(".//div[@id='StockMovementReportTab']/div/div/div/div/div[2]/div/div/div[2]/div/div["
												+ index + "]/div[3]/table/tbody/tr/td[" + qtyINRowIndex + "]/div"))
								.getText();
						String qtyOUT = driver
								.findElement(By
										.xpath(".//div[@id='StockMovementReportTab']/div/div/div/div/div[2]/div/div/div[2]/div/div["
												+ index + "]/div[3]/table/tbody/tr/td[" + qtyOUTRowIndex + "]/div"))
								.getText();
						if (!Utilities.isNullOrEmpty(qtyIN)) {
							String[] qtyINArr = qtyIN.split(":");
							if (qtyINArr != null && qtyINArr.length > 0) {
								assertEquals(mp1.get("Quantity IN"), qtyINArr[1].trim());
								System.out.println("Verified Quantity IN For Normal Product = " + productid
										+ " : Quantity IN " + qtyINArr[1].trim() + " matched with UI");
							}
						}
						if (!Utilities.isNullOrEmpty(qtyOUT)) {
							String[] qtyOUTArr = qtyOUT.split(":");
							if (qtyOUTArr != null && qtyOUTArr.length > 0) {
								assertEquals(mp1.get("Quantity OUT"), qtyOUTArr[1].trim());
								System.out.println("Verified Quantity OUT For Normal Product = " + productid
										+ " : Quantity IN " + qtyOUTArr[1].trim() + " matched with UI");
							}
						}

						break;
					case 2:
						qtyIN = driver
								.findElement(By
										.xpath(".//div[@id='StockMovementReportTab']/div/div/div/div/div[2]/div/div/div[2]/div/div["
												+ index + "]/div[3]/table/tbody/tr/td[" + qtyINRowIndex + "]/div"))
								.getText();
						qtyOUT = driver
								.findElement(By
										.xpath(".//div[@id='StockMovementReportTab']/div/div/div/div/div[2]/div/div/div[2]/div/div["
												+ index + "]/div[3]/table/tbody/tr/td[" + qtyOUTRowIndex + "]/div"))
								.getText();
						if (!Utilities.isNullOrEmpty(qtyIN)) {
							String[] qtyINArr = qtyIN.split(":");
							if (qtyINArr != null && qtyINArr.length > 0) {
								assertEquals(mp1.get("Quantity IN"), qtyINArr[1].trim());
								System.out.println("Verified Quantity IN For Batch Product = " + productid
										+ " : Quantity IN " + qtyINArr[1].trim() + " matched with UI");
							}
						}
						if (!Utilities.isNullOrEmpty(qtyOUT)) {
							String[] qtyOUTArr = qtyOUT.split(":");
							if (qtyOUTArr != null && qtyOUTArr.length > 0) {
								assertEquals(mp1.get("Quantity OUT"), qtyOUTArr[1].trim());
								System.out.println("Verified Quantity OUT For Batch Product = " + productid
										+ " : Quantity IN " + qtyOUTArr[1].trim() + " matched with UI");
							}
						}
						break;
					case 3:
						qtyIN = driver
								.findElement(By
										.xpath(".//div[@id='StockMovementReportTab']/div/div/div/div/div[2]/div/div/div[2]/div/div["
												+ index + "]/div[3]/table/tbody/tr/td[" + qtyINRowIndex + "]/div"))
								.getText();
						qtyOUT = driver
								.findElement(By
										.xpath(".//div[@id='StockMovementReportTab']/div/div/div/div/div[2]/div/div/div[2]/div/div["
												+ index + "]/div[3]/table/tbody/tr/td[" + qtyOUTRowIndex + "]/div"))
								.getText();
						if (!Utilities.isNullOrEmpty(qtyIN)) {
							String[] qtyINArr = qtyIN.split(":");
							if (qtyINArr != null && qtyINArr.length > 0) {
								assertEquals(mp1.get("Quantity IN"), qtyINArr[1].trim());
								System.out.println("Verified Quantity IN For Batch Serial Product = " + productid
										+ " : Quantity IN " + qtyINArr[1].trim() + " matched with UI");
							}
						}
						if (!Utilities.isNullOrEmpty(qtyOUT)) {
							String[] qtyOUTArr = qtyOUT.split(":");
							if (qtyOUTArr != null && qtyOUTArr.length > 0) {
								assertEquals(mp1.get("Quantity OUT"), qtyOUTArr[1].trim());
								System.out.println("Verified Quantity OUT For Batch Serial Product = " + productid
										+ " : Quantity IN " + qtyOUTArr[1].trim() + " matched with UI");
							}
						}
						break;
					case 4:
						qtyIN = driver
								.findElement(By
										.xpath(".//div[@id='StockMovementReportTab']/div/div/div/div/div[2]/div/div/div[2]/div/div["
												+ index + "]/div[3]/table/tbody/tr/td[" + qtyINRowIndex + "]/div"))
								.getText();
						qtyOUT = driver
								.findElement(By
										.xpath(".//div[@id='StockMovementReportTab']/div/div/div/div/div[2]/div/div/div[2]/div/div["
												+ index + "]/div[3]/table/tbody/tr/td[" + qtyOUTRowIndex + "]/div"))
								.getText();
						if (!Utilities.isNullOrEmpty(qtyIN)) {
							String[] qtyINArr = qtyIN.split(":");
							if (qtyINArr != null && qtyINArr.length > 0) {
								assertEquals(mp1.get("Quantity IN"), qtyINArr[1].trim());
								System.out.println("Verified Quantity IN For Serial Product = " + productid
										+ " : Quantity IN " + qtyINArr[1].trim() + " matched with UI");
							}
						}
						if (!Utilities.isNullOrEmpty(qtyOUT)) {
							String[] qtyOUTArr = qtyOUT.split(":");
							if (qtyOUTArr != null && qtyOUTArr.length > 0) {
								assertEquals(mp1.get("Quantity OUT"), qtyOUTArr[1].trim());
								System.out.println("Verified Quantity OUT For Serial Product = " + productid
										+ " : Quantity IN " + qtyOUTArr[1].trim() + " matched with UI");
							}
						}

						break;
					}

				}
			} else {
				System.out.println("No Stock Movement Register Details found for the given Product = " + productid);
			}
			Utilities.zoomIn();
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg
							.findElement(By.xpath(pro.getProperty("StockMovementRegisterReportResultCloseBtn")));
				}
			});
			element.click();

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}
	}

}
