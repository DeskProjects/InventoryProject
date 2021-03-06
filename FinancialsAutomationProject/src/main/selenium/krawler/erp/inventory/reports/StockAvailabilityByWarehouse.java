package krawler.erp.inventory.reports;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import com.google.common.base.Function;

import java.util.concurrent.TimeUnit;

import krawler.erp.inventory.InvUtilities;
import krawler.erp.page.Utilities;

public class StockAvailabilityByWarehouse {

	// ----------------------- Verification for
	// Norma-Serial-Batch-BatchSerial-------------------------
	public static void validate_StockAvailabilityByWarehouse(String productid, String expbalanceQty, String blockedQty,
			String underQAQty, String underRepairQty, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_StockAvailabilityByWarehouseReport.properties");
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandReport(driver);
			Utilities.HoverandClick(pro.getProperty("StockAvailabilityByWarehouseLink"), driver);
			Utilities.disableExpander(driver);
			Utilities.clickCheckBox(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td[2]/div",
					"uncheck", driver);

			Utilities.enterTextNormalBox(productid, pro.getProperty("QuickSearch"), driver);
			Utilities.click_element("//*[@id='inventoryList1']//*[text()='Search']", driver);

			System.out.println(
					"<<<<<<<<<<<<<<< Stock Availability By Warehouse for [ Product ID] - [Balance Quantity] - [Blocked Quantity] - [Under QA] - [Under Repair] >>>>>>>>>>>>>>>>");
			int headerSize = Utilities
					.totalSize("//*[@id='inventoryList1']//*[text()='Product ID']/ancestor::tr/td/div", driver);
			int indOfBalQty = 0, indOfBlokQty = 0, indOfQAqty = 0, indOfRepQty = 0, indOfprd = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver
						.findElement(By.xpath(
								"//*[@id='inventoryList1']//*[text()='Product ID']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Balance Quantity")) {
					indOfBalQty = i;
				}
				if (headerName.equalsIgnoreCase("Blocked Quantity")) {
					indOfBlokQty = i;
				}
				if (headerName.equalsIgnoreCase("Under QA")) {
					indOfQAqty = i;
				}
				if (headerName.equalsIgnoreCase("Under Repair")) {
					indOfRepQty = i;
				}
				if (headerName.equalsIgnoreCase("Product ID")) {
					indOfprd = i;
				}
			}

			int totalProducts = Utilities.totalSize(
					"//*[@id='inventoryList1']//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div/table",
					driver);
			boolean flag = true;
			for (int j = 1; j <= totalProducts; j++) {
				String ProdID = null;
				String actualBalanceQty = null;
				String actualBlockedQty = null;
				String actualUnderQAQty = null;
				String actualRepairQty = null;

				ProdID = driver.findElement(By
						.xpath("//*[@id='inventoryList1']//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div["
								+ j + "]/table/tbody/tr/td[" + indOfprd + "]/div"))
						.getText();
				actualBalanceQty = driver.findElement(By
						.xpath("//*[@id='inventoryList1']//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div["
								+ j + "]/table/tbody/tr/td[" + indOfBalQty + "]/div"))
						.getText();
				actualBlockedQty = driver.findElement(By
						.xpath("//*[@id='inventoryList1']//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div["
								+ j + "]/table/tbody/tr/td[" + indOfBlokQty + "]/div"))
						.getText();
				actualUnderQAQty = driver.findElement(By
						.xpath("//*[@id='inventoryList1']//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div["
								+ j + "]/table/tbody/tr/td[" + indOfQAqty + "]/div"))
						.getText();
				actualRepairQty = driver.findElement(By
						.xpath("//*[@id='inventoryList1']//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div["
								+ j + "]/table/tbody/tr/td[" + indOfRepQty + "]/div"))
						.getText();

				if (actualBalanceQty.equalsIgnoreCase(expbalanceQty) && actualBlockedQty.equalsIgnoreCase(blockedQty)
						&& actualUnderQAQty.equalsIgnoreCase(underQAQty)
						&& actualRepairQty.equalsIgnoreCase(underRepairQty)) {
					System.out.println("**** Verified Stock Availability By Warehouse for [" + ProdID + "] ["
							+ actualBalanceQty + "] [" + actualBlockedQty + "] [" + actualUnderQAQty + "] ["
							+ actualRepairQty + "] **********");
				} else {
					System.out.println("!!!!!!!!!! FAILED to VERIFY Stock Availability By Warehouse for [" + ProdID
							+ "] [" + actualBalanceQty + "] [" + actualBlockedQty + "] [" + actualUnderQAQty + "] ["
							+ actualRepairQty + "] !!!!!!!!!!");
					flag = false;
				}
			}

			if (flag == false) {
				System.out.println("!!!!!!!!!!!!!! Failed to Verify Stock Availability By Warehouse for [" + productid
						+ "] !!!!!!!!!!!!!!!!!!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}

			Utilities.HoverandClick(pro.getProperty("StockAvailabilityByWarehouseCloseBtn"), driver);
			System.out.println("******** Verified Stock Availability By Warehouse for [" + productid + "] ********");

		} catch (Exception Ex) {
			System.out.println("!!!!!!!!!!!!!! Failed to Verify Stock Availability By Warehouse for [" + productid
					+ "] !!!!!!!!!!!!!!!!!!!!");
			Utilities.handleError(Ex, driver);
		}
	}

	// ************ Build Assembly Stock Availablity For Build Assembly
	// **********************
	public static void validate_StockAvailabilityByWarehouseBuilAssembly(WebDriver driver, String productid,
			String expbalanceQty, String blockedQty, String underQAQty, String underRepairQty)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_StockAvailabilityByWarehouseReport.properties");
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandReport(driver);

			Utilities.HoverandClick(pro.getProperty("StockAvailabilityByWarehouseLink"), driver);
			Thread.sleep(1500);
			Utilities.clickExpander(driver);
			Utilities.clickCheckBox(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td[2]/div",
					"uncheck", driver);

			Utilities.enterTextNormalBox(productid, pro.getProperty("QuickSearch"), driver);
			Utilities.click_element("//button[text()='Search' and contains(@class,'accountingbase fetch')]", driver);
			Thread.sleep(2000);

			String[] expBalQty = expbalanceQty.split(",");
			String[] expBlockQty = blockedQty.split(",");
			String[] expQAQty = underQAQty.split(",");
			String[] expRepairQty = underRepairQty.split(",");

			int headerSize = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[1]/table/thead/tr/td/div",
					driver);
			int productIDRowIndex = 0, balanceQtyRowIndex = 0, blockedQtyRowIndex = 0, underQAQtyRowIndex = 0,
					underRepairQtyRowIndex = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headName = driver
						.findElement(
								By.xpath("//*[text()='Product ID']/ancestor::div[1]/table/thead/tr/td[" + i + "]/div"))
						.getText();
				if (headName.equals("Product ID")) {
					productIDRowIndex = i;

				} else if (headName.equals("Balance Quantity")) {
					balanceQtyRowIndex = i;

				} else if (headName.equals("Blocked Quantity")) {
					blockedQtyRowIndex = i;

				} else if (headName.equals("Under QA")) {
					underQAQtyRowIndex = i;

				} else if (headName.equals("Under Repair")) {
					underRepairQtyRowIndex = i;
				}
			}

			int totalResult = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div",
					driver);

			for (int i = 1; i <= totalResult; i++) {
				int switchToCase = 0;
				String actualBalQty = null;
				String actualBlockQty = null;
				String actualQAQty = null;
				String actualRepairQty = null;

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

				actualBalQty = driver.findElement(By
						.xpath("//div[@id='storewiseInventoryLevel']/div/div/div/div/div/div[1]/div/div[2]/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[" + balanceQtyRowIndex + "]/div"))
						.getText();
				actualBlockQty = driver.findElement(By
						.xpath("//div[@id='storewiseInventoryLevel']/div/div/div/div/div/div[1]/div/div[2]/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[" + blockedQtyRowIndex + "]/div"))
						.getText();
				actualQAQty = driver.findElement(By
						.xpath("//div[@id='storewiseInventoryLevel']/div/div/div/div/div/div[1]/div/div[2]/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[" + underQAQtyRowIndex + "]/div"))
						.getText();
				actualRepairQty = driver.findElement(By
						.xpath("//div[@id='storewiseInventoryLevel']/div/div/div/div/div/div[1]/div/div[2]/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[" + underRepairQtyRowIndex + "]/div"))
						.getText();

				switch (switchToCase) {
				case 1:
					assertEquals(expBalQty[0], actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : Balance Quantity ["
							+ actualBalQty + "] matched with UI");
					assertEquals(expBlockQty[0], actualBlockQty);
					System.out.println("Verified Blocked Quantity For Product = [" + prodID + "] : Blocked Quantity ["
							+ actualBlockQty + "] matched with UI");
					assertEquals(expQAQty[0], actualQAQty);
					System.out.println("Verified Under QA Quantity For Product = [" + prodID + "] : Under QA Quantity ["
							+ actualQAQty + "] matched with UI");
					assertEquals(expRepairQty[0], actualRepairQty);
					System.out.println("Verified Under Repair Quantity For Product = [" + prodID
							+ "]:  Under Repair Quantity [" + actualRepairQty + "] matched with UI");
					break;

				case 2:
					assertEquals(expBalQty[1], actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : Balance Quantity ["
							+ actualBalQty + "] matched with UI");
					assertEquals(expBlockQty[1], actualBlockQty);
					System.out.println("Verified Blocked Quantity For Product = [" + prodID + "] : Blocked Quantity ["
							+ actualBlockQty + "] matched with UI");
					assertEquals(expQAQty[1], actualQAQty);
					System.out.println("Verified Under QA Quantity For Product = [" + prodID + "] : Under QA Quantity ["
							+ actualQAQty + "] matched with UI");
					assertEquals(expRepairQty[1], actualRepairQty);
					System.out.println("Verified Under Repair Quantity For Product = [" + prodID
							+ "]:  Under Repair Quantity [" + actualRepairQty + "] matched with UI");
					break;

				case 3:
					assertEquals(expBalQty[2], actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : Balance Quantity ["
							+ actualBalQty + "] matched with UI");
					assertEquals(expBlockQty[2], actualBlockQty);
					System.out.println("Verified Blocked Quantity For Product = [" + prodID + "] : Blocked Quantity ["
							+ actualBlockQty + "] matched with UI");
					assertEquals(expQAQty[2], actualQAQty);
					System.out.println("Verified Under QA Quantity For Product = [" + prodID + "] : Under QA Quantity ["
							+ actualQAQty + "] matched with UI");
					assertEquals(expRepairQty[2], actualRepairQty);
					System.out.println("Verified Under Repair Quantity For Product = [" + prodID
							+ "]:  Under Repair Quantity [" + actualRepairQty + "] matched with UI");
					break;

				case 4:
					assertEquals(expBalQty[3], actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : Balance Quantity ["
							+ actualBalQty + "] matched with UI");
					assertEquals(expBlockQty[3], actualBlockQty);
					System.out.println("Verified Blocked Quantity For Product = [" + prodID + "] : Blocked Quantity ["
							+ actualBlockQty + "] matched with UI");
					assertEquals(expQAQty[3], actualQAQty);
					System.out.println("Verified Under QA Quantity For Product = [" + prodID + "] : Under QA Quantity ["
							+ actualQAQty + "] matched with UI");
					assertEquals(expRepairQty[3], actualRepairQty);
					System.out.println("Verified Under Repair Quantity For Product = [" + prodID
							+ "]:  Under Repair Quantity [" + actualRepairQty + "] matched with UI");
					break;
				}
			}

			Utilities.HoverandClick(pro.getProperty("StockAvailabilityByWarehouseCloseBtn"), driver);
			System.out.println("****** StockAvailabilityByWarehouse successfully verified for Assemble product ["
					+ productid + "] **************");

		} catch (Exception Ex) {
			System.out.println(
					"****** StockAvailabilityByWarehouse FAILED to verified for Assemble products check LOG**************");
			Utilities.handleError(Ex, driver);
		}
	}

	// *******************************PICKPACKSHIP******************************************************************
	public static void validate_StockAvailabilityByWarehousePickPackShip(WebDriver driver, String productid,
			String expbalanceQty, String blockedQty, String underQAQty, String underRepairQty)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_StockAvailabilityByWarehouseReport.properties");
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandReport(driver);

			Utilities.HoverandClick(pro.getProperty("StockAvailabilityByWarehouseLink"), driver);
			Thread.sleep(1500);
			Utilities.clickExpander(driver);
			Utilities.clickCheckBox(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td[2]/div",
					"uncheck", driver);

			Utilities.enterTextNormalBox(productid, pro.getProperty("QuickSearch"), driver);
			Utilities.HoverandClick("//button[text()='Search' and contains(@class,'accountingbase fetch')]", driver);
			Thread.sleep(2000);

			String[] expBalQty = expbalanceQty.split(",");
			String[] expBlockQty = blockedQty.split(",");
			String[] expQAQty = underQAQty.split(",");
			String[] expRepairQty = underRepairQty.split(",");

			int headerSize = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[1]/table/thead/tr/td/div",
					driver);
			int productIDRowIndex = 0, balanceQtyRowIndex = 0, blockedQtyRowIndex = 0, underQAQtyRowIndex = 0,
					underRepairQtyRowIndex = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headName = driver
						.findElement(
								By.xpath("//*[text()='Product ID']/ancestor::div[1]/table/thead/tr/td[" + i + "]/div"))
						.getText();
				if (headName.equals("Product ID")) {
					productIDRowIndex = i;

				} else if (headName.equals("Balance Quantity")) {
					balanceQtyRowIndex = i;

				} else if (headName.equals("Blocked Quantity")) {
					blockedQtyRowIndex = i;

				} else if (headName.equals("Under QA")) {
					underQAQtyRowIndex = i;

				} else if (headName.equals("Under Repair")) {
					underRepairQtyRowIndex = i;
				}
			}

			int totalResult = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div",
					driver);

			for (int i = 1; i <= totalResult; i++) {
				int switchToCase = 0;
				String actualBalQty = null;
				String actualBlockQty = null;
				String actualQAQty = null;
				String actualRepairQty = null;

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

				actualBalQty = driver.findElement(By
						.xpath("//div[@id='storewiseInventoryLevel']/div/div/div/div/div/div[1]/div/div[2]/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[" + balanceQtyRowIndex + "]/div"))
						.getText();
				actualBlockQty = driver.findElement(By
						.xpath("//div[@id='storewiseInventoryLevel']/div/div/div/div/div/div[1]/div/div[2]/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[" + blockedQtyRowIndex + "]/div"))
						.getText();
				actualQAQty = driver.findElement(By
						.xpath("//div[@id='storewiseInventoryLevel']/div/div/div/div/div/div[1]/div/div[2]/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[" + underQAQtyRowIndex + "]/div"))
						.getText();
				actualRepairQty = driver.findElement(By
						.xpath("//div[@id='storewiseInventoryLevel']/div/div/div/div/div/div[1]/div/div[2]/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[" + underRepairQtyRowIndex + "]/div"))
						.getText();

				switch (switchToCase) {
				case 1:
					assertEquals(expBalQty[0], actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : Balance Quantity ["
							+ actualBalQty + "] matched with UI");
					assertEquals(expBlockQty[0], actualBlockQty);
					System.out.println("Verified Blocked Quantity For Product = [" + prodID + "] : Blocked Quantity ["
							+ actualBlockQty + "] matched with UI");
					assertEquals(expQAQty[0], actualQAQty);
					System.out.println("Verified Under QA Quantity For Product = [" + prodID + "] : Under QA Quantity ["
							+ actualQAQty + "] matched with UI");
					assertEquals(expRepairQty[0], actualRepairQty);
					System.out.println("Verified Under Repair Quantity For Product = [" + prodID
							+ "]:  Under Repair Quantity [" + actualRepairQty + "] matched with UI");
					break;

				case 2:
					assertEquals(expBalQty[1], actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : Balance Quantity ["
							+ actualBalQty + "] matched with UI");
					assertEquals(expBlockQty[1], actualBlockQty);
					System.out.println("Verified Blocked Quantity For Product = [" + prodID + "] : Blocked Quantity ["
							+ actualBlockQty + "] matched with UI");
					assertEquals(expQAQty[1], actualQAQty);
					System.out.println("Verified Under QA Quantity For Product = [" + prodID + "] : Under QA Quantity ["
							+ actualQAQty + "] matched with UI");
					assertEquals(expRepairQty[1], actualRepairQty);
					System.out.println("Verified Under Repair Quantity For Product = [" + prodID
							+ "]:  Under Repair Quantity [" + actualRepairQty + "] matched with UI");
					break;

				case 3:
					assertEquals(expBalQty[2], actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : Balance Quantity ["
							+ actualBalQty + "] matched with UI");
					assertEquals(expBlockQty[2], actualBlockQty);
					System.out.println("Verified Blocked Quantity For Product = [" + prodID + "] : Blocked Quantity ["
							+ actualBlockQty + "] matched with UI");
					assertEquals(expQAQty[2], actualQAQty);
					System.out.println("Verified Under QA Quantity For Product = [" + prodID + "] : Under QA Quantity ["
							+ actualQAQty + "] matched with UI");
					assertEquals(expRepairQty[2], actualRepairQty);
					System.out.println("Verified Under Repair Quantity For Product = [" + prodID
							+ "]:  Under Repair Quantity [" + actualRepairQty + "] matched with UI");
					break;

				case 4:
					assertEquals(expBalQty[3], actualBalQty);
					System.out.println("Verified Balance Quantity for Product = [" + prodID + "] : Balance Quantity ["
							+ actualBalQty + "] matched with UI");
					assertEquals(expBlockQty[3], actualBlockQty);
					System.out.println("Verified Blocked Quantity For Product = [" + prodID + "] : Blocked Quantity ["
							+ actualBlockQty + "] matched with UI");
					assertEquals(expQAQty[3], actualQAQty);
					System.out.println("Verified Under QA Quantity For Product = [" + prodID + "] : Under QA Quantity ["
							+ actualQAQty + "] matched with UI");
					assertEquals(expRepairQty[3], actualRepairQty);
					System.out.println("Verified Under Repair Quantity For Product = [" + prodID
							+ "]:  Under Repair Quantity [" + actualRepairQty + "] matched with UI");
					break;
				}
			}

			Utilities.HoverandClick(pro.getProperty("StockAvailabilityByWarehouseCloseBtn"), driver);
			System.out.println(
					"****** StockAvailabilityByWarehouse successfully verified for [" + productid + "] **************");

		} catch (Exception Ex) {
			System.out.println("****** StockAvailabilityByWarehouse FAILED to verified for check LOG**************");
			Utilities.handleError(Ex, driver);
		}
	}

}
