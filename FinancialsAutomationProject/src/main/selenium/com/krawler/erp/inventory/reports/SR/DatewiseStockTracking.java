package com.krawler.erp.inventory.reports.SR;

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

public class DatewiseStockTracking {

	// ********************************************************Datewise Stock
	// Tracking ********************************************************
	public static void validate_DatewiseStockTracking(String productid, String balanceQty, String underQA,
			String underRepair, WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_DatewiseStockTracking.properties");
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
					return webDriverArg.findElement(By.xpath(pro.getProperty("DatewiseStockTrackingLink")));
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
			Thread.sleep(3000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("totalResult")));
				}
			});

			String resultText = element.getText();
			String[] resultTextArray = resultText.split("of");
			String resultSetTotalCntString = "";
			for (String resultTextElement : resultTextArray) {
				resultSetTotalCntString = resultTextElement;
			}
			int resultSetTotalCnt = Integer.parseInt(resultSetTotalCntString.trim());

			int productIDRowIndex = 0, qtyRowIndex = 0, underQARowIndex = 0, uRepairRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath("//div[@id='datewiseInventoryLevel']/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/table/thead/tr/td"))
					.size();
			Thread.sleep(1000);

			for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
				String header = driver.findElement(By
						.xpath("//div[@id='datewiseInventoryLevel']/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equals("Product ID")) {
					productIDRowIndex = rowIndex;

				} else if (header.equals("Available Quantity")) {
					qtyRowIndex = rowIndex;

				} else if (header.equals("Under QA")) {
					underQARowIndex = rowIndex;

				} else if (header.equals("Under Repair")) {
					uRepairRowIndex = rowIndex;

				}
			}

			for (int j = 1; j <= resultSetTotalCnt; j++) {
				int index = 0;
				String productIDStr = driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div[" + j
						+ "]/table/tbody/tr/td[" + productIDRowIndex + "]/div")).getText();
				System.out.println("productIDStr =" + productIDStr);
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
					assertEquals(balanceQty, driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div["
							+ index + "]/table/tbody/tr/td[" + qtyRowIndex + "]/div")).getText());
					System.out.println("Verified Available Quantity For Normal Product = " + productIDStr
							+ " : Available Quantity " + balanceQty + " matched with UI");
					assertEquals(underQA, driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div["
							+ index + "]/table/tbody/tr/td[" + underQARowIndex + "]/div")).getText());
					System.out.println("Verified Under QA qty For Normal Product = " + productIDStr + " : Under QA qty "
							+ underQA + " matched with UI");
					assertEquals(underRepair, driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div["
							+ index + "]/table/tbody/tr/td[" + uRepairRowIndex + "]/div")).getText());
					System.out.println("Verified Under Repair qty For Normal Product = " + productIDStr
							+ " : Under Repair qty " + underRepair + " matched with UI");
					break;
				case 2:
					assertEquals(balanceQty, driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div["
							+ index + "]/table/tbody/tr/td[" + qtyRowIndex + "]/div")).getText());
					System.out.println("Verified Available Quantity For Batch Product = " + productIDStr
							+ " : Available Quantity " + balanceQty + " matched with UI");
					assertEquals(underQA, driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div["
							+ index + "]/table/tbody/tr/td[" + underQARowIndex + "]/div")).getText());
					System.out.println("Verified Under QA qty For Batch Product = " + productIDStr + " : Under QA qty "
							+ underQA + " matched with UI");
					assertEquals(underRepair, driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div["
							+ index + "]/table/tbody/tr/td[" + uRepairRowIndex + "]/div")).getText());
					System.out.println("Verified Under Repair qty For Batch Product = " + productIDStr
							+ " : Under Repair qty " + underRepair + " matched with UI");
					break;
				case 3:
					assertEquals(balanceQty, driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div["
							+ index + "]/table/tbody/tr/td[" + qtyRowIndex + "]/div")).getText());
					System.out.println("Verified Available Quantity For Batch with Serial Product = " + productIDStr
							+ " : Available Quantity " + balanceQty + " matched with UI");
					assertEquals(underQA, driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div["
							+ index + "]/table/tbody/tr/td[" + underQARowIndex + "]/div")).getText());
					System.out.println("Verified Under QA qty For Batch with Serial Product = " + productIDStr
							+ " : Under QA qty " + underQA + " matched with UI");
					assertEquals(underRepair, driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div["
							+ index + "]/table/tbody/tr/td[" + uRepairRowIndex + "]/div")).getText());
					System.out.println("Verified Under Repair qty For Batch with Serial Product = " + productIDStr
							+ " : Under Repair qty " + underRepair + " matched with UI");
					break;
				case 4:
					assertEquals(balanceQty, driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div["
							+ index + "]/table/tbody/tr/td[" + qtyRowIndex + "]/div")).getText());
					System.out.println("Verified Available Quantity For Serial Product = " + productIDStr
							+ " : Available Quantity " + balanceQty + " matched with UI");
					assertEquals(underQA, driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div["
							+ index + "]/table/tbody/tr/td[" + underQARowIndex + "]/div")).getText());
					System.out.println("Verified Under QA qty For Serial Product = " + productIDStr + " : Under QA qty "
							+ underQA + " matched with UI");
					assertEquals(underRepair, driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div["
							+ index + "]/table/tbody/tr/td[" + uRepairRowIndex + "]/div")).getText());
					System.out.println("Verified Under Repair qty For Serial Product = " + productIDStr
							+ " : Under Repair qty " + underRepair + " matched with UI");
					break;
				}

			}
			element = wait.until(new Function<WebDriver, WebElement>() {

				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("DatewiseStockClsBtn")));
				}
			});
			element.click();
			Thread.sleep(2000);
			System.out.println(
					"************************************************* 'Datewise Stock Tracking Report' is verified*************************************************");

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}
	}
}
