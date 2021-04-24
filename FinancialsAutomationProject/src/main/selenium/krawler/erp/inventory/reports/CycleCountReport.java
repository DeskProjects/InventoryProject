package krawler.erp.inventory.reports;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.google.common.base.Function;

import krawler.erp.page.Utilities;

public class CycleCountReport {

	// ************************************************ Cycle Count
	// Report********************************************************
	public static void validate_CycleCountReport(String productid, String docID, String Actualqty, String Systemqty,
			String VarianceQty, WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_CycleCountReport.properties");
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
					return webDriverArg.findElement(By.xpath(pro.getProperty("CycleCountReportLink")));
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
			search.sendKeys(docID);
			Thread.sleep(3000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("FromDateBtn")));
				}
			});
			element.click();
			Thread.sleep(2000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("fromDateToday")));
				}
			});
			element.click();
			Thread.sleep(2000);

			/*
			 * int clickIndex=0; for(int index=1;
			 * index<=driver.findElements(By.xpath(
			 * "//form[@id='designpanelpreview']/following-sibling::div")).size(
			 * );index++){ String button=driver.findElement(By.xpath(
			 * "//form[@id='designpanelpreview']/following-sibling::div[" +
			 * index +
			 * "]/ul/li/div/table/tbody/tr[3]/td/table/tbody/tr/td[2]/em/button"
			 * )).getText(); if(button.equalsIgnoreCase("Today")) {
			 * clickIndex=index;
			 * System.out.println("clickIndex GGGGG >> "+clickIndex); } }
			 * System.out.println("clickIndex >> "+clickIndex);
			 */

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("ToDateBtn")));
				}
			});
			element.click();
			Thread.sleep(2000);
			WebElement element3 = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("toDateToday")));
				}
			});
			element3.click();
			Thread.sleep(2000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("searchButton")));
				}
			});
			element.click();
			Thread.sleep(2000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("TotalFindResult")));
				}
			});

			String resultText = element.getText();
			String[] resultTextArray = resultText.split("of");
			String resultSetTotalCntString = "";
			for (String resultTextElement : resultTextArray) {
				resultSetTotalCntString = resultTextElement;
			}
			int resultSetTotalCnt = Integer.parseInt(resultSetTotalCntString.trim());
			System.out.println("resultSetTotalCnt > " + resultSetTotalCnt);

			Robot r2 = new Robot();
			r2.keyPress(KeyEvent.VK_CONTROL);
			r2.keyPress(KeyEvent.VK_SUBTRACT);
			r2.keyRelease(KeyEvent.VK_SUBTRACT);
			r2.keyRelease(KeyEvent.VK_CONTROL);

			int productIDRowIndex = 0, ActualqtyRowIndex = 0, SystemqtyRowIndex = 0, VarianceQtyRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(
							By.xpath("//div[contains(@id,'id')]/div/div[2]/div/div/div/div/div/table/thead/tr/td"))
					.size();
			Thread.sleep(1000);

			for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
				String header = driver.findElement(
						By.xpath("//div[contains(@id,'id')]/div/div[2]/div/div/div/div/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equals("Product Code")) {
					productIDRowIndex = rowIndex;
					System.out.println();

				} else if (header.equals("Actual Quantity")) {
					ActualqtyRowIndex = rowIndex;

				} else if (header.equals("System Quantity")) {
					SystemqtyRowIndex = rowIndex;

				} else if (header.equals("Variance Quantity")) {
					VarianceQtyRowIndex = rowIndex;
				}
			}

			for (int j = 1; j <= resultSetTotalCnt; j++) {
				int index = 0;
				String productIDStr = driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div/div[2]/div["
						+ j + "]/table/tbody/tr/td[" + productIDRowIndex + "]/div")).getText();
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
					assertEquals(Actualqty,
							driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div/div[2]/div[" + j
									+ "]/table/tbody/tr/td[" + ActualqtyRowIndex + "]/div")).getText());
					System.out.println("Verified Actual Quantity For Normal Product = " + productIDStr
							+ " : Actual Quantity " + Actualqty + " matched with UI");
					assertEquals(Systemqty,
							driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div/div[2]/div[" + j
									+ "]/table/tbody/tr/td[" + SystemqtyRowIndex + "]/div")).getText());
					System.out.println("Verified System Quantity For Normal Product = " + productIDStr
							+ " : System Quantity " + Systemqty + " matched with UI");
					assertEquals(VarianceQty,
							driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div/div[2]/div[" + j
									+ "]/table/tbody/tr/td[" + VarianceQtyRowIndex + "]/div")).getText());
					System.out.println("Verified Variance Quantity For Normal Product = " + productIDStr
							+ " : Variance Quantity [" + VarianceQty + "] matched with UI");
					break;

				case 2:
					assertEquals(Actualqty,
							driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div/div[2]/div[" + j
									+ "]/table/tbody/tr/td[" + ActualqtyRowIndex + "]/div")).getText());
					System.out.println("Verified Actual Quantity For Batch Product = " + productIDStr
							+ " : Actual Quantity " + Actualqty + " matched with UI");
					assertEquals(Systemqty,
							driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div/div[2]/div[" + j
									+ "]/table/tbody/tr/td[" + SystemqtyRowIndex + "]/div")).getText());
					System.out.println("Verified System Quantity For Batch Product = " + productIDStr
							+ " : System Quantity " + Systemqty + " matched with UI");
					assertEquals(VarianceQty,
							driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div/div[2]/div[" + j
									+ "]/table/tbody/tr/td[" + VarianceQtyRowIndex + "]/div")).getText());
					System.out.println("Verified Variance Quantity For Batch Product = " + productIDStr
							+ " : Variance Quantity [" + VarianceQty + "] matched with UI");
					break;

				case 3:
					assertEquals(Actualqty,
							driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div/div[2]/div[" + j
									+ "]/table/tbody/tr/td[" + ActualqtyRowIndex + "]/div")).getText());
					System.out.println("Verified Actual Quantity For Batch with serial Product = " + productIDStr
							+ " : Actual Quantity " + Actualqty + " matched with UI");
					assertEquals(Systemqty,
							driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div/div[2]/div[" + j
									+ "]/table/tbody/tr/td[" + SystemqtyRowIndex + "]/div")).getText());
					System.out.println("Verified System Quantity For Batch with serial Product = " + productIDStr
							+ " : System Quantity " + Systemqty + " matched with UI");
					assertEquals(VarianceQty,
							driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div/div[2]/div[" + j
									+ "]/table/tbody/tr/td[" + VarianceQtyRowIndex + "]/div")).getText());
					System.out.println("Verified Variance Quantity For Batch with serial Product = " + productIDStr
							+ " : Variance Quantity [" + VarianceQty + "] matched with UI");
					break;

				case 4:
					assertEquals(Actualqty,
							driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div/div[2]/div[" + j
									+ "]/table/tbody/tr/td[" + ActualqtyRowIndex + "]/div")).getText());
					System.out.println("Verified Actual Quantity For serial Product = " + productIDStr
							+ " : Actual Quantity " + Actualqty + " matched with UI");
					assertEquals(Systemqty,
							driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div/div[2]/div[" + j
									+ "]/table/tbody/tr/td[" + SystemqtyRowIndex + "]/div")).getText());
					System.out.println("Verified System Quantity For serial Product = " + productIDStr
							+ " : System Quantity " + Systemqty + " matched with UI");
					assertEquals(VarianceQty,
							driver.findElement(By.xpath("//div[@class='x-grid3-scroller']/div/div/div[2]/div[" + j
									+ "]/table/tbody/tr/td[" + VarianceQtyRowIndex + "]/div")).getText());
					System.out.println("Verified Variance Quantity For serial Product = " + productIDStr
							+ " : Variance Quantity [" + VarianceQty + "] matched with UI");
					break;

				}
			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("CycleCountReportClsBtn")));
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
