package krawler.erp.inventory.reports;

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

public class StockReport {

	// ********************************************************Stock
	// Report*********************************************************
	public static void validate_StockReport(String productid, String OpeningStock, String StockIN, String StockOut,
			String qtyOnHand, WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_StockReport.properties");
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
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockReportLink")));
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

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("QuickSearch")));
				}
			});
			Thread.sleep(1000);
			search.click();
			search.sendKeys(productid);
			Thread.sleep(5000);

			List<WebElement> totalLinks = driver
					.findElements(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div"));
			int resultTotalCnt = totalLinks.size();
			System.out.println("totalResultcnt > > " + resultTotalCnt);

			int productIDRowIndex = 0, qtyOnHandRowIndex = 0, opnStockRowIndex = 0, stockINRowIndex = 0,
					StockOutRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By.xpath("//div[@class='x-panel-bwrap ']/div/div/div/div/div/div/table/thead/tr/td"))
					.size();
			Thread.sleep(1000);

			for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
				String header = driver.findElement(
						By.xpath("//div[@class='x-panel-bwrap ']/div/div/div/div/div/div/table/thead/tr/td[" + rowIndex
								+ "]/div"))
						.getText();
				if (header.equals("Product ID")) {
					productIDRowIndex = rowIndex;

				} else if (header.equals("Opening Stock")) {
					opnStockRowIndex = rowIndex;

				} else if (header.equals("Stock IN")) {
					stockINRowIndex = rowIndex;

				} else if (header.equals("Stock OUT")) {
					StockOutRowIndex = rowIndex;

				} else if (header.equals("Quantity On Hand")) {
					qtyOnHandRowIndex = rowIndex;

				}
			}

			for (int j = 1; j <= resultTotalCnt; j++) {
				int index = 0;
				String productIDStr = driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
						+ "]/table/tbody/tr/td[" + productIDRowIndex + "]/div")).getText();
				System.out.println("productIDStr" + productIDStr);
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
					assertEquals(OpeningStock,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
									+ "]/table/tbody/tr/td[" + opnStockRowIndex + "]/div")).getText());
					System.out.println("Verified Opening Stock For Normal Product = " + productIDStr
							+ " : Opening Stock " + OpeningStock + " matched with UI");
					assertEquals(StockIN, driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div["
							+ j + "]/table/tbody/tr/td[" + stockINRowIndex + "]/div")).getText());
					System.out.println("Verified StockIN quantity For Normal Product = " + productIDStr
							+ " : StockIN Quantity " + StockIN + " matched with UI");
					assertEquals(StockOut,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
									+ "]/table/tbody/tr/td[" + StockOutRowIndex + "]/div")).getText());
					System.out.println("Verified StockOut Quantity For Normal Product = " + productIDStr
							+ " : StockOut Quantity " + StockOut + " matched with UI");
					assertEquals(qtyOnHand,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
									+ "]/table/tbody/tr/td[" + qtyOnHandRowIndex + "]/div")).getText());
					System.out.println("Verified Quantity On Hand For Normal Product = " + productIDStr
							+ " : Quantity On Hand " + qtyOnHand + " matched with UI");
					break;

				case 2:
					assertEquals(OpeningStock,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
									+ "]/table/tbody/tr/td[" + opnStockRowIndex + "]/div")).getText());
					System.out.println("Verified Opening Stock For Batch Product = " + productIDStr
							+ " : Opening Quantity " + OpeningStock + " matched with UI");
					assertEquals(StockIN, driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div["
							+ j + "]/table/tbody/tr/td[" + stockINRowIndex + "]/div")).getText());
					System.out.println("Verified StockIN quantity For Batch Product = " + productIDStr
							+ " : StockIN Quantity " + StockIN + " matched with UI");
					assertEquals(StockOut,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
									+ "]/table/tbody/tr/td[" + StockOutRowIndex + "]/div")).getText());
					System.out.println("Verified StockOut Quantity For Batch Product = " + productIDStr
							+ " : StockOut Quantity " + StockOut + " matched with UI");
					assertEquals(qtyOnHand,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
									+ "]/table/tbody/tr/td[" + qtyOnHandRowIndex + "]/div")).getText());
					System.out.println("Verified Quantity On Hand For Batch Product = " + productIDStr
							+ " : Quantity On Hand " + qtyOnHand + " matched with UI");
					break;

				case 3:
					assertEquals(OpeningStock,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
									+ "]/table/tbody/tr/td[" + opnStockRowIndex + "]/div")).getText());
					System.out.println("Verified Opening Stock For Batch with Serial Product = " + productIDStr
							+ " : Opening Quantity " + OpeningStock + " matched with UI");
					assertEquals(StockIN, driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div["
							+ j + "]/table/tbody/tr/td[" + stockINRowIndex + "]/div")).getText());
					System.out.println("Verified StockIN quantity For Batch with Serial Product = " + productIDStr
							+ " : StockIN Quantity " + StockIN + " matched with UI");
					assertEquals(StockOut,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
									+ "]/table/tbody/tr/td[" + StockOutRowIndex + "]/div")).getText());
					System.out.println("Verified StockOut Quantity For Batch with Serial Product = " + productIDStr
							+ " : StockOut Quantity " + StockOut + " matched with UI");
					assertEquals(qtyOnHand,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
									+ "]/table/tbody/tr/td[" + qtyOnHandRowIndex + "]/div")).getText());
					System.out.println("Verified Quantity On Hand For Batch with Serial Product = " + productIDStr
							+ " : Quantity On Hand " + qtyOnHand + " matched with UI");
					break;

				case 4:
					assertEquals(OpeningStock,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
									+ "]/table/tbody/tr/td[" + opnStockRowIndex + "]/div")).getText());
					System.out.println("Verified Opening Stock For Serial Product = " + productIDStr
							+ " : Opening Quantity " + OpeningStock + " matched with UI");
					assertEquals(StockIN, driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div["
							+ j + "]/table/tbody/tr/td[" + stockINRowIndex + "]/div")).getText());
					System.out.println("Verified StockIN quantity For Serial Product = " + productIDStr
							+ " : StockIN Quantity " + StockIN + " matched with UI");
					assertEquals(StockOut,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
									+ "]/table/tbody/tr/td[" + StockOutRowIndex + "]/div")).getText());
					System.out.println("Verified StockOut Quantity For Serial Product = " + productIDStr
							+ " : StockOut Quantity " + StockOut + " matched with UI");
					assertEquals(qtyOnHand,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
									+ "]/table/tbody/tr/td[" + qtyOnHandRowIndex + "]/div")).getText());
					System.out.println("Verified Quantity On Hand For Serial Product = " + productIDStr
							+ " : Quantity On Hand " + qtyOnHand + " matched with UI");
					break;
				}

			}

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockReportCloseBtn")));
				}
			});
			element.click();
			Thread.sleep(2000);
			System.out.println(
					"************************************************* 'Stock Report' is verified*************************************************");

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);

		}
	}
}