package krawler.erp.inventory.reports;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import krawler.erp.page.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class StockValuationSummaryReport {

	// ********************************************************StockValuationSummary
	// Report*********************************************************
	public static void validate_StockValuationSummary(String searchByCriteria, String searchByValue,
			String valuationAmount, WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_StockValuationSummaryReport.properties");

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
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockValuationSummaryReportLink")));
				}
			});
			element.click();

			Thread.sleep(2000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockValuationSummarySearchBy")));
				}
			});
			Thread.sleep(1000);
			element.clear();
			Thread.sleep(1000);
			element.sendKeys(searchByCriteria);
			Thread.sleep(1000);
			element.sendKeys(Keys.ENTER);

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockValuationSummaryReportFetchBtn")));
				}
			});
			element.click();
			Thread.sleep(12000);

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg
							.findElement(By.xpath(pro.getProperty("StockValuationSummaryReportResultCount")));
				}
			});

			String resultText = element.getText();
			if (!Utilities.isNullOrEmpty(resultText)
					&& !resultText.equalsIgnoreCase("There is no record to display.")) {
				String[] resultTextArray = resultText.split("of");
				String resultSetTotalCntString = "";
				for (String resultTextElement : resultTextArray) {
					resultSetTotalCntString = resultTextElement;
				}
				int resultSetTotalCnt = Integer.parseInt(resultSetTotalCntString.trim());
				int searchByCriteriaRowIndex = 0, ValuationRowIndex = 0;
				int tableHeadersCnt = driver
						.findElements(By
								.xpath("//div[@id='locationsummary']/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[1]/div/div/table/thead/tr/td/div"))
						.size();
				Thread.sleep(1000);

				for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
					String header = driver.findElement(By
							.xpath("//div[@id='locationsummary']/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ rowIndex + "]/div"))
							.getText();
					if (header.equals(searchByCriteria)) {
						searchByCriteriaRowIndex = rowIndex;

					} else if (header.equals("Value")) {
						ValuationRowIndex = rowIndex;

					}
				}
				for (int j = 1; j <= resultSetTotalCnt; j++) {

					String searchValueStr = driver
							.findElement(By
									.xpath("//div[@id='locationsummary']/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
											+ j + "]/table/tbody/tr/td[" + searchByCriteriaRowIndex + "]/div"))
							.getText();
					if (searchByValue.equalsIgnoreCase(searchValueStr.trim())) {
						String valueFromUI = driver.findElement(By
								.xpath("//div[@id='locationsummary']/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
										+ j + "]/table/tbody/tr/td[" + ValuationRowIndex + "]/div"))
								.getText();
						assertEquals(valuationAmount, valueFromUI.trim());
						System.out.println("Verified Value for [" + searchByValue + "] : " + valuationAmount.trim()
								+ " matched [" + valuationAmount + "] with UI : [" + valueFromUI + "]");
					}

				}
			} else {
				System.out.println("No Data found for Stock Valuation SummaryReport for " + searchByCriteria
						+ " and Value : " + valuationAmount.trim());
			}
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockValuationSummaryReportCloseBtn")));
				}
			});
			element.click();
			Thread.sleep(2000);
			System.out.println(
					"************************************************* 'Stock Valuation Summary Report' is verified*************************************************");

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}
	}
}
