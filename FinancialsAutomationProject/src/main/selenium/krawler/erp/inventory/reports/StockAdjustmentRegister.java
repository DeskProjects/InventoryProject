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

public class StockAdjustmentRegister {

	// ********************************************************StockAdjustment
	// Register *********************************************************
	public static void validate_stockAdjustmentReport(String productid, String documentID, String AdjustmentType,
			String Quantity, WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			final Properties pro = Utilities.fetchProValue("OR_StockAdjustmentRegister.properties");
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
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockAdjustmentRegisterLink")));
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
			Thread.sleep(1000);

			String waitForQicksearch = "//div[text()='Store']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr[1]/td[2]/div/div";
			Utilities.clickCheckBox(waitForQicksearch, "uncheck", driver);

			WebElement search = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("QuickSearch")));
				}
			});
			search.click();
			search.sendKeys(documentID);
			Thread.sleep(3000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockAdjustmentRegisterResultCount")));
				}
			});

			String resultText = element.getText();
			String[] resultTextArray = resultText.split("of");
			String resultSetTotalCntString = "";
			for (String resultTextElement : resultTextArray) {
				resultSetTotalCntString = resultTextElement;
			}
			int resultSetTotalCnt = Integer.parseInt(resultSetTotalCntString.trim());

			int productIDRowIndex = 0, AdjTypeRowIndex = 0, QtyRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath("//div[@id='markoutListmarkoutListDetailCmp']/div/div[2]/div/div/div/div/div/table/thead/tr/td"))
					.size();
			Thread.sleep(1000);
			for (int rowIndex = 1; rowIndex < tableHeadersCnt + 1; rowIndex++) {
				String header = driver.findElement(By
						.xpath("//div[@id='markoutListmarkoutListDetailCmp']/div/div[2]/div/div/div/div/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equals("Product ID")) {
					productIDRowIndex = rowIndex;

				} else if (header.equals("Adjustment Type")) {
					AdjTypeRowIndex = rowIndex;

				} else if (header.equals("Quantity")) {
					QtyRowIndex = rowIndex;

				}
			}
			System.out.println("> >>> >>" + resultSetTotalCnt);
			for (int j = 1; j <= resultSetTotalCnt; j++) {
				int index = 0;
				String productIDStr = driver.findElement(
						By.xpath("//div[@id='markoutListmarkoutListDetailCmp']/div/div[2]/div/div/div/div/div[" + j
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
							AdjustmentType, driver
									.findElement(By
											.xpath("//div[@id='markoutListmarkoutListDetailCmp']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + AdjTypeRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Adjustment Type For Normal Product = " + productIDStr
							+ " : Adjustment Type " + AdjustmentType + " matched with UI");
					assertEquals(Quantity,
							driver.findElement(By
									.xpath("//div[@id='markoutListmarkoutListDetailCmp']/div/div[2]/div/div/div/div/div["
											+ j + "]/table/tbody/tr/td[" + QtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified quantity For Normal Product = " + productIDStr + " : Quantity "
							+ Quantity + " matched with UI");
					break;
				case 2:
					assertEquals(
							AdjustmentType, driver
									.findElement(By
											.xpath("//div[@id='markoutListmarkoutListDetailCmp']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + AdjTypeRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Adjustment Type For Batch Product = " + productIDStr
							+ " : Adjustment Type " + AdjustmentType + " matched with UI");
					assertEquals(Quantity,
							driver.findElement(By
									.xpath("//div[@id='markoutListmarkoutListDetailCmp']/div/div[2]/div/div/div/div/div["
											+ j + "]/table/tbody/tr/td[" + QtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified quantity For Batch Product = " + productIDStr + " : Quantity "
							+ Quantity + " matched with UI");
					break;
				case 3:
					assertEquals(
							AdjustmentType, driver
									.findElement(By
											.xpath("//div[@id='markoutListmarkoutListDetailCmp']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + AdjTypeRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Adjustment Type For Batch & Serial Product = " + productIDStr
							+ " : Adjustment Type " + AdjustmentType + " matched with UI");
					assertEquals(Quantity,
							driver.findElement(By
									.xpath("//div[@id='markoutListmarkoutListDetailCmp']/div/div[2]/div/div/div/div/div["
											+ j + "]/table/tbody/tr/td[" + QtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified quantity For Batch & Serial Product = " + productIDStr + " : Quantity "
							+ Quantity + " matched with UI");
					break;
				case 4:
					assertEquals(
							AdjustmentType, driver
									.findElement(By
											.xpath("//div[@id='markoutListmarkoutListDetailCmp']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + AdjTypeRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Adjustment Type For Serial Product = " + productIDStr
							+ " : Adjustment Type " + AdjustmentType + " matched with UI");
					assertEquals(Quantity,
							driver.findElement(By
									.xpath("//div[@id='markoutListmarkoutListDetailCmp']/div/div[2]/div/div/div/div/div["
											+ j + "]/table/tbody/tr/td[" + QtyRowIndex + "]/div"))
									.getText());
					System.out.println("Verified quantity For Serial Product = " + productIDStr + " : Quantity "
							+ Quantity + " matched with UI");
					break;
				}
			}
			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockAdjustmentRegisterCloseTab")));
				}
			});
			element.click();
			Thread.sleep(2000);
			System.out
					.println("************************************************* 'Stock Adjustment Register Report for ["
							+ AdjustmentType + "] is verified*************************************************");

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);

		}
	}
}
