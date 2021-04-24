package krawler.erp.inventory.reports;

import static org.testng.Assert.assertEquals;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import com.google.common.base.Function;

import krawler.erp.inventory.InvUtilities;
import krawler.erp.page.Utilities;

public class ReportProductMaster {

	// **************************** Verify Multiple Products
	// ****************************
	public static void verify_ProductMaster_MultipleProducts(String documentid, String productid[],
			double expectedQty[], WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_ReportProductMaster.properties");
			Utilities.openProductMaster(driver);
			Utilities.disableExpander(driver);

			int headerSize = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[1]/table/thead/tr/td/div",
					driver);
			int productIDRowIndex = 0, balanceQtyRowIndex = 0;
			boolean flag = true;
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

			for (int i = 0; i < productid.length; i++) {
				Utilities.enterTextNormalBox(productid[i], pro.getProperty("QuickSearch"), driver);
				driver.findElement(By.xpath(pro.getProperty("QuickSearch"))).sendKeys(Keys.ENTER);
				Utilities.isLoadingDisappear(120, driver);
				String actualQty = driver
						.findElement(By.xpath(
								"//*[text()='" + productid[i] + "']/ancestor::tr/td[" + balanceQtyRowIndex + "]/div"))
						.getText();

				// verification
				double UIvalue = Utilities.getIntegerFrmString(actualQty);
				if (UIvalue == expectedQty[i]) {
					System.out.println("*** Verified product [" + productid[i] + "] as UI quantity [" + actualQty
							+ "] with expected quantity [[" + expectedQty[i] + "]] ****");
				} else {
					System.out.println("*** FAILED to verify product [" + productid[i] + "] as UI quantity ["
							+ actualQty + "] with expected quantity [[" + expectedQty[i] + "]] ****");
					flag = false;
				}
			}
			// System.out.println(Arrays.toString(actualResult.toArray()));
			if (flag == false) {
				System.out.println("!!!! Failed to verify Product Master report for [ " + documentid
						+ " ] Report pls chec Log !!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			System.out
					.println("!!!! Verified Product Master report for [ " + documentid + " ] Report pls chec Log !!!!");
			Utilities.click_element(pro.getProperty("ProductDetailsClosebtn"), driver);
		} catch (Exception EX) {
			System.out.println(
					"!!!! Failed to verify Product Master report for [ " + documentid + " ] Report pls chec Log !!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ----------------- Product master Report for : Normal, Serial, Batch
	// Prdoucts -----------------
	public static void validate_ProductMaster(String productid, String expavailableQty, String expBalanceQty,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_ReportProductMaster.properties");
			Utilities.openProductMaster(driver);
			Utilities.disableExpander(driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("QuickSearch"), driver);
			Utilities.isLoadingDisappear(100, driver);
			int totalResult = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div",
					driver);
			boolean flag = true;
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
				} else if (headName.equals("Available Quantity")) {
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

}