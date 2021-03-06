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

public class CycleCountStatus {

	// ************************************************ Cycle Count
	// Status********************************************************
	public static void validate_CycleCountStatus(String productid, String storeCode, String itemCount, String status,
			WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_CycleCountStatus.properties");
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

			element = wait.until(new Function<WebDriver, WebElement>() {

				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("CycleCountStatusTab")));
				}
			});
			element.click();

			element = wait.until(new Function<WebDriver, WebElement>() {

				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("selectDate")));
				}
			});
			element.click();
			Thread.sleep(2000);

			element = wait.until(new Function<WebDriver, WebElement>() {

				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("selectToday")));
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

			int productIDRowIndex = 0, storeCodeRowIndex = 0, itemCntRowIndex = 0, statusRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath("//div[@id='CycleCountReportTabId']/div/div/div/div/div/div/div/div/div/div/div/div/div/div/div/div[1]/table/thead/tr/td"))
					.size();
			Thread.sleep(1000);

			for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
				String header = driver.findElement(By
						.xpath("//div[@id='CycleCountReportTabId']/div/div/div/div/div/div/div/div/div/div/div/div/div/div/div/div[1]/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equals("Store Code")) {
					storeCodeRowIndex = rowIndex;

				} else if (header.equals("Item Count")) {
					itemCntRowIndex = rowIndex;

				} else if (header.equals("Status")) {
					statusRowIndex = rowIndex;

				}
			}

			for (int j = 1; j <= resultSetTotalCnt; j++) {

				assertEquals(storeCode,
						driver.findElement(By
								.xpath("//div[@id='CycleCountReportTabId']/div/div/div/div/div/div/div/div/div/div/div/div/div/div[2]/div/div["
										+ j + "]/table/tbody/tr/td[" + storeCodeRowIndex + "]/div"))
								.getText());
				System.out.println("Verified Store Code  [" + storeCode + "] matched with UI");

				assertEquals(itemCount,
						driver.findElement(By
								.xpath("//div[@id='CycleCountReportTabId']/div/div/div/div/div/div/div/div/div/div/div/div/div/div[2]/div/div["
										+ j + "]/table/tbody/tr/td[" + itemCntRowIndex + "]/div"))
								.getText());
				System.out.println("Verified Item count  [" + itemCount + "] matched with UI");

				assertEquals(status,
						driver.findElement(By
								.xpath("//div[@id='CycleCountReportTabId']/div/div/div/div/div/div/div/div/div/div/div/div/div/div[2]/div/div["
										+ j + "]/table/tbody/tr/td[" + statusRowIndex + "]/div"))
								.getText());
				System.out.println("Verified Status as  [" + status + "] matched with UI");

			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("CycleCountReportClsBtn")));
				}
			});
			element.click();

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}
	}
}