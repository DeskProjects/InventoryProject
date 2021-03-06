package com.krawler.erp.inventory.reports.SR;

import static org.testng.Assert.assertEquals;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import com.google.common.base.Function;

import krawler.erp.inventory.InvUtilities;
import krawler.erp.page.Utilities;

public class ReportProductMaster {

	// ----------------- Product master Report for : Normal, Serial, Batch
	// Prdoucts -----------------
	public static void validate_ProductMaster(String productid, String expavailableQty, String expBalanceQty,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_ReportProductMaster.properties");
			Utilities.waitandClick(pro.getProperty("ProductMasterIcon"), driver);
			Thread.sleep(1500);
			Utilities.click_element(pro.getProperty("ProductCloseBtn"), driver);
			Utilities.clickButton("Yes", 500, driver);
			// Utilities.clickCheckBox("//*[text()='Product
			// ID']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td[2]/div",
			// "uncheck", driver);
			Utilities.disableExpander(driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			int totalResult = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div",
					driver);
			int headerSize = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[1]/table/thead/tr/td/div",
					driver);
			int productIDRowIndex = 0, indOfavailbleQty = 0, indOfbalQty = 0;

			for (int i = 1; i <= headerSize; i++) {
				String headName = driver
						.findElement(
								By.xpath("//*[text()='Product ID']/ancestor::div[1]/table/thead/tr/td[" + i + "]/div"))
						.getText();
				if (headName.equals("Product ID")) {
					productIDRowIndex = i;
				}
			}
			Utilities.justHover(
					"//*[text()='Status']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td[25]/div",
					driver);

			System.out.println(totalResult + "TOTAL RESULT");

			boolean flag = true;
			System.out.println(headerSize + "Header size is....");
			for (int i = 1; i <= headerSize; i++) {
				String headName = driver
						.findElement(
								By.xpath("//*[text()='Product ID']/ancestor::div[1]/table/thead/tr/td[" + i + "]/div"))
						.getText();
				// if(headName.equals("Product ID"))
				// {
				// productIDRowIndex=i;
				// }
				if (headName.equals("Available Quantity")) {
					indOfavailbleQty = i;
				} else if (headName.equals("Balance Quantity")) {
					indOfbalQty = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) {
				String prdID = null;
				String actualavailbleQty = null;
				String actualBalanceQty = null;

				prdID = driver
						.findElement(By.xpath("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
								+ "]/table/tbody/tr/td[" + productIDRowIndex + "]/div"))
						.getText();
				System.out.println(prdID + "PRDID is....");
				actualavailbleQty = driver
						.findElement(By.xpath("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indOfavailbleQty + "]/div"))
						.getText();
				actualBalanceQty = driver
						.findElement(By.xpath("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indOfbalQty + "]/div"))
						.getText();

				if (actualavailbleQty.equalsIgnoreCase(expavailableQty + " Unit")
						&& actualBalanceQty.equalsIgnoreCase(expBalanceQty + " Unit")) {
					System.out.println("****** Verified Product Master Report for Product ID :[" + prdID
							+ "] with Available Qauntity [" + actualavailbleQty + "] & Balance Qauntity ["
							+ actualBalanceQty + "] **********");
				} else {
					System.out.println("****** FAILED to Verify Product Master Report for Product ID :[" + prdID
							+ "] with Available Qauntity [" + actualavailbleQty + "] & Balance Qauntity ["
							+ actualBalanceQty + "]**********");
					flag = false;
				}
			}

			if (flag == false) {
				System.out.println("!!!!!!!!!!!!!! Failed to Verify Product Master Report for Product ID :[" + productid
						+ "] !!!!!!!!!!!!!!!!!!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}

			Utilities.HoverandClick(pro.getProperty("ProductDetailsClosebtn"), driver);
			System.out.println("******** Verified Product Master for Product ID :[" + productid + "] ********");
		}

		catch (Exception EX) {
			System.out.println("!!!!!!!!!!!!!! Failed to Verify Product Master Report for Product ID :[" + productid
					+ "] !!!!!!!!!!!!!!!!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ************ Build Assembly Stock Availablity For Build Assembly
	// **********************
	public static void validate_ProductMasterBuilAssembly(WebDriver driver, String productid, String expbalanceQty)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_ReportProductMaster.properties");

			Utilities.waitandClick(pro.getProperty("ProductMasterIcon"), driver);
			Thread.sleep(1500);
			Utilities.click_element(pro.getProperty("ProductCloseBtn"), driver);
			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td[2]/div",
					"uncheck", driver);

			Utilities.enterTextNormalBox(productid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			String[] expBalQty = expbalanceQty.split(",");

			int headerSize = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[1]/table/thead/tr/td/div",
					driver);
			int productIDRowIndex = 0, balanceQtyRowIndex = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headName = driver
						.findElement(
								By.xpath("//*[text()='Product ID']/ancestor::div[1]/table/thead/tr/td[" + i + "]/div"))
						.getText();
				if (headName.equals("Product ID")) {
					productIDRowIndex = i;
				} else if (headName.equals("Available Quantity")) {
					balanceQtyRowIndex = i;
				}
			}

			int totalResult = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div",
					driver);
			for (int i = 1; i <= totalResult; i++) {
				int switchToCase = 0;
				String actualBalQty = null;
				String prodID = driver
						.findElement(By.xpath("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
								+ "]/table/tbody/tr/td[" + productIDRowIndex + "]/div"))
						.getText();
				if (prodID.endsWith("A1")) {
					switchToCase = 1;
				}
				if (prodID.endsWith("A2")) {
					switchToCase = 2;
				}
				if (prodID.endsWith("A3")) {
					switchToCase = 3;
				}
				if (prodID.startsWith("AsB")) {
					switchToCase = 4;
				}

				actualBalQty = driver
						.findElement(By.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div[" + i
								+ "]/table/tbody/tr/td[" + balanceQtyRowIndex + "]/div"))
						.getText();

				switch (switchToCase) {
				case 1:
					assertEquals(expBalQty[0] + " Unit", actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : Balance Quantity ["
							+ actualBalQty + "] matched with UI");
					break;

				case 2:
					assertEquals(expBalQty[1] + " Unit", actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : Balance Quantity ["
							+ actualBalQty + "] matched with UI");
					break;

				case 3:
					assertEquals(expBalQty[2] + " Unit", actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : Balance Quantity ["
							+ actualBalQty + "] matched with UI");
					break;

				case 4:
					assertEquals(expBalQty[3] + " Unit", actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : Balance Quantity ["
							+ actualBalQty + "] matched with UI");
					break;
				}
			}

			Utilities.HoverandClick(pro.getProperty("ProductDetailsClosebtn"), driver);
			System.out.println("****** Product master successfully verified for Assemble product [" + productid
					+ "] **************");

		} catch (Exception Ex) {
			System.out
					.println("****** Product master FAILED to verified for Assemble products check LOG**************");
			Utilities.handleError(Ex, driver);
		}
	}
	// ************************************PickPAckShip****************************************************

	public static void validate_ProductMasterPickPackShip(WebDriver driver, String productid, String expbalanceQty)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_ReportProductMaster.properties");

			Utilities.waitandClick(pro.getProperty("ProductMasterIcon"), driver);
			Thread.sleep(1500);
			Utilities.click_element(pro.getProperty("ProductCloseBtn"), driver);
			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td[2]/div",
					"uncheck", driver);

			Utilities.enterTextNormalBox(productid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			String[] expBalQty = expbalanceQty.split(",");

			int headerSize = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[1]/table/thead/tr/td/div",
					driver);
			int productIDRowIndex = 0, balanceQtyRowIndex = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headName = driver
						.findElement(
								By.xpath("//*[text()='Product ID']/ancestor::div[1]/table/thead/tr/td[" + i + "]/div"))
						.getText();
				if (headName.equals("Product ID")) {
					productIDRowIndex = i;
				} else if (headName.equals("Available Quantity")) {
					balanceQtyRowIndex = i;
				}
			}

			int totalResult = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div",
					driver);
			for (int i = 1; i <= totalResult; i++) {
				int switchToCase = 0;
				String actualBalQty = null;
				String prodID = driver
						.findElement(By.xpath("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
								+ "]/table/tbody/tr/td[" + productIDRowIndex + "]/div"))
						.getText();
				if (prodID.equalsIgnoreCase(productid)) {
					switchToCase = 1;
				}
				if (prodID.endsWith("B2")) {
					switchToCase = 2;
				}
				if (prodID.endsWith("S3")) {
					switchToCase = 3;
				}
				if (prodID.endsWith("BS4")) {
					switchToCase = 4;
				}

				actualBalQty = driver
						.findElement(By.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div[" + i
								+ "]/table/tbody/tr/td[" + balanceQtyRowIndex + "]/div"))
						.getText();

				switch (switchToCase) {
				case 1:
					assertEquals(expBalQty[0] + " Unit", actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : Balance Quantity ["
							+ actualBalQty + "] matched with UI");
					break;

				case 2:
					assertEquals(expBalQty[1] + " Unit", actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : Balance Quantity ["
							+ actualBalQty + "] matched with UI");
					break;

				case 3:
					assertEquals(expBalQty[2] + " Unit", actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : Balance Quantity ["
							+ actualBalQty + "] matched with UI");
					break;

				case 4:
					assertEquals(expBalQty[3] + " Unit", actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : Balance Quantity ["
							+ actualBalQty + "] matched with UI");
					break;
				}
			}

			Utilities.HoverandClick(pro.getProperty("ProductDetailsClosebtn"), driver);
			System.out.println("****** Product master successfully verified for [" + productid + "] **************");

		} catch (Exception Ex) {
			System.out.println(
					"****** Product master FAILED to verified for [" + productid + "]  check LOG**************");
			Utilities.handleError(Ex, driver);
		}

	}

	// ********************************************************
	public static void reportProductMaster(String productid, String AvailableQuantity, String BlockQuantity,
			String BalanceQuantity, WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			final Properties pro = Utilities.fetchProValue("OR_ReportProductMaster.properties");
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);

			WebElement element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("ProductMasterIcon")));
				}
			});
			element.click();

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("ProductCloseBtn")));
				}
			});
			element.click();
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);
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

			List<WebElement> totalLinks = driver.findElements(
					By.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table"));
			int resultSetTotalCnt = totalLinks.size();
			System.out.println("totalResultcnt > > " + resultSetTotalCnt);

			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_SUBTRACT);
			r.keyRelease(KeyEvent.VK_SUBTRACT);
			r.keyRelease(KeyEvent.VK_CONTROL);

			int productIDRowIndex = 0, AvailableQuantityRowIndex = 0, BlockQuantityRowIndex = 0,
					BalanceQuantityRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div/table/thead/tr/td"))
					.size();
			Thread.sleep(1000);
			for (int rowIndex = 1; rowIndex < tableHeadersCnt + 1; rowIndex++) {
				String header = driver.findElement(
						By.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equals("Product ID")) {
					productIDRowIndex = rowIndex;

				} else if (header.equals("Available Quantity")) {
					AvailableQuantityRowIndex = rowIndex;

				} else if (header.equals("Block Quantity")) {
					BlockQuantityRowIndex = rowIndex;

				} else if (header.equals("Balance Quantity")) {
					BalanceQuantityRowIndex = rowIndex;
				}
			}

			for (int j = 1; j <= resultSetTotalCnt; j++) {
				int index = 0;
				String productIDStr = driver
						.findElement(By.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div[" + j
								+ "]/table/tbody/tr/td[" + productIDRowIndex + "]/div  "))
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
							AvailableQuantity, driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + AvailableQuantityRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Available Quantity For Normal Product = " + productIDStr
							+ " : Available Quantity " + AvailableQuantity + " matched with UI");
					assertEquals(
							BlockQuantity, driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + BlockQuantityRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Blocked Quantity For Normal Product = " + productIDStr
							+ " : Blocked Quantity " + BlockQuantity + " matched with UI");
					assertEquals(
							BalanceQuantity, driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + BalanceQuantityRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Balance Quantity For Normal Product = " + productIDStr
							+ " : Balance Quantity Type " + BalanceQuantity + " matched with UI");
					break;
				case 2:
					assertEquals(
							AvailableQuantity, driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + AvailableQuantityRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Available Quantity For Batch Product = " + productIDStr
							+ " : Available Quantity " + AvailableQuantity + " matched with UI");
					assertEquals(
							BlockQuantity, driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + BlockQuantityRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Blocked Quantity For Batch Product = " + productIDStr
							+ " : Blocked Quantity " + BlockQuantity + " matched with UI");
					assertEquals(
							BalanceQuantity, driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + BalanceQuantityRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Balance Quantity For Batch Product = " + productIDStr
							+ " : Balance Quantity Type " + BalanceQuantity + " matched with UI");
					break;
				case 3:
					assertEquals(
							AvailableQuantity, driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + AvailableQuantityRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Available Quantity For Batch with Serial Product = " + productIDStr
							+ " : Available Quantity " + AvailableQuantity + " matched with UI");
					assertEquals(
							BlockQuantity, driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + BlockQuantityRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Blocked Quantity For Batch with Serial Product = " + productIDStr
							+ " : Blocked Quantity " + BlockQuantity + " matched with UI");
					assertEquals(
							BalanceQuantity, driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + BalanceQuantityRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Balance Quantity For Batch with Serial Product = " + productIDStr
							+ " : Balance Quantity Type " + BalanceQuantity + " matched with UI");
					break;
				case 4:
					assertEquals(
							AvailableQuantity, driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + AvailableQuantityRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Available Quantity For Serial Product = " + productIDStr
							+ " : Available Quantity " + AvailableQuantity + " matched with UI");
					assertEquals(
							BlockQuantity, driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + BlockQuantityRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Blocked Quantity For Serial Product = " + productIDStr
							+ " : Blocked Quantity " + BlockQuantity + " matched with UI");
					assertEquals(
							BalanceQuantity, driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div/div/div["
													+ j + "]/table/tbody/tr/td[" + BalanceQuantityRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Balance Quantity For Serial Product = " + productIDStr
							+ " : Balance Quantity Type " + BalanceQuantity + " matched with UI");
					break;
				}

			}
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_ADD);
			r.keyRelease(KeyEvent.VK_ADD);
			r.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("ProductDetailsClosebtn")));
				}
			});
			element.click();
			System.out.println(
					"************************************************* 'Product Master Report' is verified*************************************************");

		}

		catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}
	}
}