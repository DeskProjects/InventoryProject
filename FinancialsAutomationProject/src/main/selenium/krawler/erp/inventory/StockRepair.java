package krawler.erp.inventory;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import krawler.erp.page.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class StockRepair {

	// ---------------- StockRepair : Approve Request ----------------
	public static void stockRepair_approve(String documentid, String approvedQuanity, String productid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_StockRepair.properties");

			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);
			String LoadingIcon = "//*[contains(text(),'Loading.')]";

			Utilities.HoverandClick(pro.getProperty("StockRepairLink"), driver);
			Thread.sleep(1500);
			Utilities.clickExpander(driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			Utilities.clickButton("Search", 1000, driver);
			Thread.sleep(2000);

			int headerCnt = Utilities.totalSize(
					"//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td",
					driver);
			int indOfprid = 0, qtyRowIndex = 0, batchRowIndex = 0, serialRowIndex = 0, repairStatusRowIndex;

			for (int i = 1; i <= headerCnt; i++) {
				String headName = driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Quantity")) {
					qtyRowIndex = i;
				} else if (driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Batch/Challan No")) {
					batchRowIndex = i;
				} else if (driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Serial")) {
					serialRowIndex = i;
				} else if (driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Product ID")) {
					indOfprid = i;
				} else if (driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Repair Status")) {
					repairStatusRowIndex = i;
				}
			}
			int totalFoundTran = Utilities
					.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div", driver);

			for (int i = 1; i <= totalFoundTran; i++) {
				String foundline = driver
						.findElement(By.xpath("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indOfprid + "]/div"))
						.getText();

				// Normal product
				if (foundline.equals(productid)) {
					String xpath = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
							+ "]/table/tbody/tr/td[" + qtyRowIndex + "]/div";
					Utilities.HoverandClick(xpath, driver);
					Utilities.enter_LinelabelAmount(approvedQuanity, driver);
					Utilities.click_element(pro.getProperty("QuickSearch"), driver);
				}

				if (foundline.endsWith("B2")) {
					String xpath = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
							+ "]/table/tbody/tr/td[" + qtyRowIndex + "]/div";
					Utilities.HoverandClick(xpath, driver);
					Utilities.enter_LinelabelAmount(approvedQuanity, driver);
					Thread.sleep(1000);
				}
			}

			int serialCheckedCount = 0;
			for (int j = 1; j <= totalFoundTran; j++) {
				String prdname = driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[" + indOfprid + "]/div"))
						.getText();
				WebElement checkBoxElement = driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[2]"));
				if (serialCheckedCount < Integer.parseInt(approvedQuanity) && prdname.endsWith("S3")) {
					serialCheckedCount++;
					checkBoxElement.click();
				}
			}
			Thread.sleep(2000);
			int batchserialCheckedCount = 0;
			for (int j = 1; j <= totalFoundTran; j++) {
				String prdname = driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[" + indOfprid + "]/div"))
						.getText();
				WebElement checkBoxElement = driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[2]"));
				if (batchserialCheckedCount < Integer.parseInt(approvedQuanity) && prdname.endsWith("BS4")) {
					batchserialCheckedCount++;
					checkBoxElement.click();
				}
			}
			Thread.sleep(1000);
			System.out.println("*** Stock Repair - Approve for [" + documentid + "] successfully !! ****");

			Utilities.clickButton("Approve", 1000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("Save", 2000, driver);
			Utilities.isElementGone(LoadingIcon, 20, driver);
			Utilities.clickButton("OK", 500, driver);
			Utilities.HoverandClick("//*[@id='as__stockrepaireTab']/a[1]", driver);

		} catch (Exception Ex) {
			System.out.println("*** Stock Repair - Approve for [" + documentid + "] FAILEDD !! ****");
			Utilities.handleError(Ex, driver);
		}
	}

	// Reject
	public static void stockRepair_reject(String documentid, String rejectQty, String productid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			String LoadingIcon = "//*[contains(text(),'Loading.')]";
			final Properties pro = Utilities.fetchProValue("OR_StockRepair.properties");

			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);

			Utilities.HoverandClick(pro.getProperty("StockRepairLink"), driver);
			Thread.sleep(1500);
			Utilities.clickExpander(driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			Utilities.clickButton("Search", 1000, driver);
			Thread.sleep(2000);

			int headerCnt = Utilities.totalSize(
					"//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td",
					driver);
			int indOfprid = 0, qtyRowIndex = 0, batchRowIndex = 0, serialRowIndex = 0, repairStatusRowIndex;

			for (int i = 1; i <= headerCnt; i++) {
				String headName = driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Quantity")) {
					qtyRowIndex = i;
				} else if (driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Batch/Challan No")) {
					batchRowIndex = i;
				} else if (driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Serial")) {
					serialRowIndex = i;
				} else if (driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Product ID")) {
					indOfprid = i;
				} else if (driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Repair Status")) {
					repairStatusRowIndex = i;
				}
			}

			int totalFoundTran = Utilities
					.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div", driver);

			for (int i = 1; i <= totalFoundTran; i++) {
				String foundline = driver
						.findElement(By.xpath("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indOfprid + "]/div"))
						.getText();

				// Normal product
				if (foundline.equals(productid)) {
					String xpath = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
							+ "]/table/tbody/tr/td[" + qtyRowIndex + "]/div";
					Utilities.HoverandClick(xpath, driver);
					Utilities.enter_LinelabelAmount(rejectQty, driver);
					Utilities.HoverandClick(pro.getProperty("QuickSearch"), driver);
				}

				else if (foundline.endsWith("B2")) {
					String xpath = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
							+ "]/table/tbody/tr/td[" + qtyRowIndex + "]/div";
					Utilities.HoverandClick(xpath, driver);
					Utilities.enter_LinelabelAmount(rejectQty, driver);
					Thread.sleep(1000);
				}
			}

			int serialCheckedCount = 0;
			for (int j = 1; j <= totalFoundTran; j++) {
				String prdname = driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[" + indOfprid + "]/div"))
						.getText();
				WebElement checkBoxElement = driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[2]"));
				if (serialCheckedCount < Integer.parseInt(rejectQty) && prdname.endsWith("S3")) {
					serialCheckedCount++;
					checkBoxElement.click();
				}
			}
			Thread.sleep(1000);

			int batchserialCheckedCount = 0;
			for (int j = 1; j <= totalFoundTran; j++) {
				String prdname = driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[" + indOfprid + "]/div"))
						.getText();
				WebElement checkBoxElement = driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[2]"));
				if (batchserialCheckedCount < Integer.parseInt(rejectQty) && prdname.endsWith("BS4")) {
					batchserialCheckedCount++;
					checkBoxElement.click();
				}
			}
			Thread.sleep(1000);

			Utilities.clickButton("Reject", 2000, driver);
			Utilities.clickButton("Submit", 3000, driver);
			Thread.sleep(1500);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.isElementGone(LoadingIcon, 20, driver);
			Utilities.clickButton("Save", 100, driver);
			Utilities.isElementGone(LoadingIcon, 20, driver);
			Utilities.clickButton("OK", 1500, driver);

			Utilities.HoverandClick("//*[@id='as__stockrepaireTab']/a[1]", driver);
			System.out.println("*** Stock Repair - Reject for [" + documentid + "] successfully !! ****");

		} catch (Exception Ex) {
			System.out.println("*** Stock Repair - Reject for [" + documentid + "] FAILEDD !! ****");
			Utilities.handleError(Ex, driver);
		}
	}

	// Scrap
	public static void stockRepair_Scrap(String documentid, String approvedQuanity, String productid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_StockRepair.properties");
			String LoadingIcon = "//*[contains(text(),'Loading.')]";
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);

			Utilities.HoverandClick(pro.getProperty("StockRepairLink"), driver);
			Thread.sleep(1500);
			Utilities.clickExpander(driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			Utilities.clickButton("Search", 1000, driver);
			Thread.sleep(2000);

			int headerCnt = Utilities.totalSize(
					"//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td",
					driver);
			int indOfprid = 0, qtyRowIndex = 0, batchRowIndex = 0, serialRowIndex = 0, repairStatusRowIndex;

			for (int i = 1; i <= headerCnt; i++) {
				String headName = driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Quantity")) {
					qtyRowIndex = i;
				} else if (driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Batch/Challan No")) {
					batchRowIndex = i;
				} else if (driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Serial")) {
					serialRowIndex = i;
				} else if (driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Product ID")) {
					indOfprid = i;
				} else if (driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Repair Status")) {
					repairStatusRowIndex = i;
				}
			}

			int totalFoundTran = Utilities
					.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div", driver);

			for (int i = 1; i <= totalFoundTran; i++) {
				String foundline = driver
						.findElement(By.xpath("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indOfprid + "]/div"))
						.getText();

				// Normal product
				if (foundline.equals(productid)) {
					String xpath = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
							+ "]/table/tbody/tr/td[" + qtyRowIndex + "]/div";
					Utilities.clickAndEnterText(approvedQuanity, xpath, driver);
					Thread.sleep(1000);
				}

				else if (foundline.endsWith("B2")) {
					String xpath = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
							+ "]/table/tbody/tr/td[" + qtyRowIndex + "]/div";
					Utilities.clickAndEnterText(approvedQuanity, xpath, driver);
					Thread.sleep(1000);
				}
			}
			Utilities.HoverandClick(pro.getProperty("QuickSearch"), driver);

			int serialCheckedCount = 0;
			for (int j = 1; j <= totalFoundTran; j++) {
				String prdname = driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[" + indOfprid + "]/div"))
						.getText();
				WebElement checkBoxElement = driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[2]"));
				if (serialCheckedCount < Integer.parseInt(approvedQuanity) && prdname.endsWith("S3")) {
					serialCheckedCount++;
					checkBoxElement.click();
				}
			}
			Thread.sleep(1000);

			int batchserialCheckedCount = 0;
			for (int j = 1; j <= totalFoundTran; j++) {
				String prdname = driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[" + indOfprid + "]/div"))
						.getText();
				WebElement checkBoxElement = driver.findElement(By
						.xpath("//div[contains(@id,'stockrepaireTab')]/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[2]"));
				if (batchserialCheckedCount < Integer.parseInt(approvedQuanity) && prdname.endsWith("BS4")) {
					batchserialCheckedCount++;
					checkBoxElement.click();
				}
			}
			Thread.sleep(1000);

			Utilities.clickButton("Reject", 2000, driver);

			String xpathScrap = "//*[contains(text(),'Send Stock to Scrap Warehouse')]/following-sibling::div//*[@name='flowtype']";
			Utilities.HoverandClick(xpathScrap, driver);

			Utilities.clickButton("Submit", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("Save", 2000, driver);
			Utilities.isElementGone(LoadingIcon, 20, driver);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(1000);

			Utilities.HoverandClick("//*[@id='as__stockrepaireTab']/a[1]", driver);
			System.out.println("*** Stock Repair - Scrap for [" + documentid + "] successfully !! ****");

		} catch (Exception Ex) {
			System.out.println("*** Stock Repair - Scrap for [" + documentid + "] FAILEDD !! ****");
			Utilities.handleError(Ex, driver);
		}
	}

}
