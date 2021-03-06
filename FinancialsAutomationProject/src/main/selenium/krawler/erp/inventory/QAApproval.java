package krawler.erp.inventory;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.jboss.netty.util.internal.SystemPropertyUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

import krawler.erp.page.Utilities;

public class QAApproval {

	// ---------------------QA -Approve -------------------------------
	public static void approveQA(String documentid, String productid, String approvedQuanity, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_QAApproval.properties");
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);

			Utilities.HoverandClick(pro.getProperty("QAApprovalLink"), driver);
			Utilities.clickCheckBox("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div[1]//td[1]",
					"uncheck", driver);
			Utilities.clickExpander(driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			driver.findElement(By.xpath(pro.getProperty("QuickSearch"))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			int headerCnt = Utilities.totalSize("//*[text()='Transaction No']/ancestor::tr/td/div", driver);
			int indOfprid = 0, indOfDetails = 0;

			for (int i = 1; i <= headerCnt; i++) {
				String headName = driver
						.findElement(By.xpath("//*[text()='Transaction No']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headName.equalsIgnoreCase("Product ID")) {
					indOfprid = i;
				}
				if (headName.equalsIgnoreCase("View Details")) {
					indOfDetails = i;
				}
			}

			StringBuilder stb = new StringBuilder();
			stb.append("ProductId,Batch,Serial");
			List<String> QAApprovalList = new ArrayList<String>();
			QAApprovalList.add(stb.toString());

			int NormalPrd = 0, BatchPrd = 0, SerialPrd = 0, BatchSer = 0;
			String Quantity = null;
			int headerOfdetails = 0;
			int totalFound = Utilities
					.totalSize("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div[1]/div", driver);
			for (int j = 1; j <= totalFound; j++) {
				String prdType = driver
						.findElement(By.xpath("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
								+ j + "]/table/tbody/tr/td[" + indOfprid + "]"))
						.getText();
				if (prdType.equalsIgnoreCase(productid)) {
					NormalPrd = j;
				}
				if (prdType.endsWith("B2")) {
					BatchPrd = j;
				}
				if (prdType.endsWith("S3")) {
					SerialPrd = j;
				}
				if (prdType.endsWith("BS4")) {
					BatchSer = j;
				}
			}

			String prodName = null;
			String serialNo = null;
			if (NormalPrd > 0) {
				Utilities.HoverandClick("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
						+ NormalPrd + "]/table/tbody/tr/td[" + indOfDetails + "]", driver);
				Thread.sleep(250);
				headerOfdetails = Utilities.totalSize(
						"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td/div", driver);
				for (int k = 1; k <= headerOfdetails; k++) {
					Quantity = driver.findElement(By.xpath(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td[" + k + "]/div"))
							.getText();
					if (Quantity.equalsIgnoreCase("Quantity")) {
						Utilities.HoverandClick(
								"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
										+ k + "]/div",
								driver);
						Utilities.enter_LinelabelAmount(approvedQuanity, driver);
						break;
					}
				}
				Utilities.HoverandClick("//*[contains(@id,'StockoutQADetail')]//button[text()='Approve']", driver);
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("Save", 1000, driver);
				Utilities.clickButton("OK", 1500, driver);
				Utilities.click_element(pro.getProperty("QAApprovalDetailsTabCloseBtn"), driver);
				Thread.sleep(250);
			}

			if (BatchPrd > 0) {
				Utilities.HoverandClick("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
						+ BatchPrd + "]/table/tbody/tr/td[" + indOfDetails + "]", driver);
				Thread.sleep(250);
				headerOfdetails = Utilities.totalSize(
						"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td/div", driver);
				for (int k = 1; k <= headerOfdetails; k++) {
					Quantity = driver.findElement(By.xpath(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td[" + k + "]/div"))
							.getText();
					if (Quantity.equalsIgnoreCase("Quantity")) {
						Utilities.HoverandClick(
								"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
										+ k + "]/div",
								driver);
						Utilities.enter_LinelabelAmount(approvedQuanity, driver);
						break;
					}
				}
				Utilities.HoverandClick("//*[contains(@id,'StockoutQADetail')]//button[text()='Approve']", driver);
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("Save", 1000, driver);
				Utilities.clickButton("OK", 1500, driver);
				Utilities.click_element(pro.getProperty("QAApprovalDetailsTabCloseBtn"), driver);
				Thread.sleep(250);
			}

			if (SerialPrd > 0) {
				prodName = driver
						.findElement(By.xpath("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
								+ SerialPrd + "]/table/tbody/tr/td[" + indOfprid + "]"))
						.getText();
				Utilities.HoverandClick("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
						+ SerialPrd + "]/table/tbody/tr/td[" + indOfDetails + "]", driver);
				Thread.sleep(250);

				for (int k = 1; k <= Integer.parseInt(approvedQuanity); k++) {
					Utilities.HoverandClick(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div["
									+ k + "]/table/tbody//*[@class='x-grid3-row-checker']",
							driver);
				}

				for (int k = 1; k <= headerOfdetails; k++) {
					Quantity = driver.findElement(By.xpath(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td[" + k + "]/div"))
							.getText();
					if (Quantity.equalsIgnoreCase("Serial")) {
						for (int sr = 1; sr <= Integer.parseInt(approvedQuanity); sr++) {
							serialNo = driver.findElement(By
									.xpath("//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div["
											+ sr + "]/table/tbody/tr/td[" + k + "]/div"))
									.getText();
							stb.setLength(0);
							stb.append(prodName).append(",").append("Batch").append(",").append(serialNo);
							QAApprovalList.add(stb.toString());
						}
						break;
					}
				}
				Utilities.HoverandClick("//*[contains(@id,'StockoutQADetail')]//button[text()='Approve']", driver);
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("Save", 1000, driver);
				Utilities.clickButton("OK", 1500, driver);
				Utilities.click_element(pro.getProperty("QAApprovalDetailsTabCloseBtn"), driver);
				Thread.sleep(250);
			}

			if (BatchSer > 0) {
				prodName = driver
						.findElement(By.xpath("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
								+ BatchSer + "]/table/tbody/tr/td[" + indOfprid + "]"))
						.getText();
				Utilities.HoverandClick("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
						+ BatchSer + "]/table/tbody/tr/td[" + indOfDetails + "]", driver);
				Thread.sleep(250);

				for (int k = 1; k <= Integer.parseInt(approvedQuanity); k++) {
					Utilities.HoverandClick(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div["
									+ k + "]/table/tbody//*[@class='x-grid3-row-checker']",
							driver);
				}

				for (int k = 1; k <= headerOfdetails; k++) {
					Quantity = driver.findElement(By.xpath(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td[" + k + "]/div"))
							.getText();
					if (Quantity.equalsIgnoreCase("Serial")) {
						for (int sr = 1; sr <= Integer.parseInt(approvedQuanity); sr++) {
							serialNo = driver.findElement(By
									.xpath("//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div["
											+ sr + "]/table/tbody/tr/td[" + k + "]/div"))
									.getText();
							stb.setLength(0);
							stb.append(prodName).append(",").append("BatchSerial").append(",").append(serialNo);
							QAApprovalList.add(stb.toString());
						}
						break;
					}
				}
				Utilities.HoverandClick("//*[contains(@id,'StockoutQADetail')]//button[text()='Approve']", driver);
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("Save", 1000, driver);
				Utilities.clickButton("OK", 1500, driver);
				Utilities.click_element(pro.getProperty("QAApprovalDetailsTabCloseBtn"), driver);
				Thread.sleep(250);
			}
			String[] array = QAApprovalList.toArray(new String[0]);
			Utilities.writeSerialsToFile(
					documentid + "[QA_APPROVED_serials]" + Utilities.currentDate("dd-MM-yyyy") + ".txt", array);

			Utilities.click_element(pro.getProperty("QAApprovalCloseBtn"), driver);
			System.out.println("************ QA - Approval test case for [" + documentid + "] is PASS ************");
		} catch (Exception Ex) {
			System.out.println(
					"!!!!-WARNING-!!!!!!!!! QA - Approval test case for [" + documentid + "] is FAIL !!!!!!!!!!!!!");
			Utilities.handleError(Ex, driver);
		}
	}

	// ****** QA - Reject ****************************
	public static void rejectQA(String documentid, String productid, String rejectedQuanity, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_QAApproval.properties");
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);

			Utilities.HoverandClick(pro.getProperty("QAApprovalLink"), driver);
			Utilities.clickCheckBox("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div[1]//td[1]",
					"uncheck", driver);
			Utilities.clickExpander(driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			driver.findElement(By.xpath(pro.getProperty("QuickSearch"))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			int headerCnt = Utilities.totalSize("//*[text()='Transaction No']/ancestor::tr/td/div", driver);
			int indOfprid = 0, indOfDetails = 0;

			for (int i = 1; i <= headerCnt; i++) {
				String headName = driver
						.findElement(By.xpath("//*[text()='Transaction No']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headName.equalsIgnoreCase("Product ID")) {
					indOfprid = i;
				}
				if (headName.equalsIgnoreCase("View Details")) {
					indOfDetails = i;
				}
			}

			StringBuilder stb = new StringBuilder();
			stb.append("ProductId,Batch,Serial");
			List<String> QAApprovalList = new ArrayList<String>();
			QAApprovalList.add(stb.toString());

			int NormalPrd = 0, BatchPrd = 0, SerialPrd = 0, BatchSer = 0;
			String Quantity = null;
			int headerOfdetails = 0;
			int totalFound = Utilities
					.totalSize("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div[1]/div", driver);
			for (int j = 1; j <= totalFound; j++) {
				String prdType = driver
						.findElement(By.xpath("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
								+ j + "]/table/tbody/tr/td[" + indOfprid + "]"))
						.getText();
				if (prdType.equalsIgnoreCase(productid)) {
					NormalPrd = j;
				}
				if (prdType.endsWith("B2")) {
					BatchPrd = j;
				}
				if (prdType.endsWith("S3")) {
					SerialPrd = j;
				}
				if (prdType.endsWith("BS4")) {
					BatchSer = j;
				}
			}

			String prodName = null;
			String serialNo = null;
			if (NormalPrd > 0) {
				Utilities.HoverandClick("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
						+ NormalPrd + "]/table/tbody/tr/td[" + indOfDetails + "]", driver);
				Thread.sleep(250);
				headerOfdetails = Utilities.totalSize(
						"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td/div", driver);
				for (int k = 1; k <= headerOfdetails; k++) {
					Quantity = driver.findElement(By.xpath(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td[" + k + "]/div"))
							.getText();
					if (Quantity.equalsIgnoreCase("Quantity")) {
						Utilities.HoverandClick(
								"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
										+ k + "]/div",
								driver);
						Utilities.enter_LinelabelAmount(rejectedQuanity, driver);
						break;
					}
				}
				Utilities.HoverandClick("//*[contains(@id,'StockoutQADetail')]//button[text()='Reject']", driver);
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("Save", 1000, driver);
				Utilities.clickButton("OK", 1500, driver);
				Utilities.click_element(pro.getProperty("QAApprovalDetailsTabCloseBtn"), driver);
				Thread.sleep(250);
			}

			if (BatchPrd > 0) {
				Utilities.HoverandClick("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
						+ BatchPrd + "]/table/tbody/tr/td[" + indOfDetails + "]", driver);
				Thread.sleep(250);
				headerOfdetails = Utilities.totalSize(
						"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td/div", driver);
				for (int k = 1; k <= headerOfdetails; k++) {
					Quantity = driver.findElement(By.xpath(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td[" + k + "]/div"))
							.getText();
					if (Quantity.equalsIgnoreCase("Quantity")) {
						Utilities.HoverandClick(
								"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
										+ k + "]/div",
								driver);
						Utilities.enter_LinelabelAmount(rejectedQuanity, driver);
						break;
					}
				}
				Utilities.HoverandClick("//*[contains(@id,'StockoutQADetail')]//button[text()='Reject']", driver);
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("Save", 1000, driver);
				Utilities.clickButton("OK", 1500, driver);
				Utilities.click_element(pro.getProperty("QAApprovalDetailsTabCloseBtn"), driver);
				Thread.sleep(250);
			}

			if (SerialPrd > 0) {
				prodName = driver
						.findElement(By.xpath("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
								+ SerialPrd + "]/table/tbody/tr/td[" + indOfprid + "]"))
						.getText();
				Utilities.HoverandClick("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
						+ SerialPrd + "]/table/tbody/tr/td[" + indOfDetails + "]", driver);
				Thread.sleep(250);

				for (int k = 1; k <= Integer.parseInt(rejectedQuanity); k++) {
					Utilities.HoverandClick(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div["
									+ k + "]/table/tbody//*[@class='x-grid3-row-checker']",
							driver);
				}

				for (int k = 1; k <= headerOfdetails; k++) {
					Quantity = driver.findElement(By.xpath(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td[" + k + "]/div"))
							.getText();
					if (Quantity.equalsIgnoreCase("Serial")) {
						for (int sr = 1; sr <= Integer.parseInt(rejectedQuanity); sr++) {
							serialNo = driver.findElement(By
									.xpath("//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div["
											+ sr + "]/table/tbody/tr/td[" + k + "]/div"))
									.getText();
							stb.setLength(0);
							stb.append(prodName).append(",").append("Batch").append(",").append(serialNo);
							QAApprovalList.add(stb.toString());
						}
						break;
					}
				}
				Utilities.HoverandClick("//*[contains(@id,'StockoutQADetail')]//button[text()='Reject']", driver);
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("Save", 1000, driver);
				Utilities.clickButton("OK", 1500, driver);
				Utilities.click_element(pro.getProperty("QAApprovalDetailsTabCloseBtn"), driver);
				Thread.sleep(250);
			}

			if (BatchSer > 0) {
				prodName = driver
						.findElement(By.xpath("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
								+ BatchSer + "]/table/tbody/tr/td[" + indOfprid + "]"))
						.getText();
				Utilities.HoverandClick("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
						+ BatchSer + "]/table/tbody/tr/td[" + indOfDetails + "]", driver);
				Thread.sleep(250);

				for (int k = 1; k <= Integer.parseInt(rejectedQuanity); k++) {
					Utilities.HoverandClick(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div["
									+ k + "]/table/tbody//*[@class='x-grid3-row-checker']",
							driver);
				}

				for (int k = 1; k <= headerOfdetails; k++) {
					Quantity = driver.findElement(By.xpath(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td[" + k + "]/div"))
							.getText();
					if (Quantity.equalsIgnoreCase("Serial")) {
						for (int sr = 1; sr <= Integer.parseInt(rejectedQuanity); sr++) {
							serialNo = driver.findElement(By
									.xpath("//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div["
											+ sr + "]/table/tbody/tr/td[" + k + "]/div"))
									.getText();
							stb.setLength(0);
							stb.append(prodName).append(",").append("BatchSerial").append(",").append(serialNo);
							QAApprovalList.add(stb.toString());
						}
						break;
					}
				}
				Utilities.HoverandClick("//*[contains(@id,'StockoutQADetail')]//button[text()='Reject']", driver);
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("Save", 1000, driver);
				Utilities.clickButton("OK", 1500, driver);
				Utilities.click_element(pro.getProperty("QAApprovalDetailsTabCloseBtn"), driver);
				Thread.sleep(250);
			}
			String[] array = QAApprovalList.toArray(new String[0]);
			Utilities.writeSerialsToFile(
					documentid + "[QA_REJECT_serials]" + Utilities.currentDate("dd-MM-yyyy") + ".txt", array);

			Utilities.click_element(pro.getProperty("QAApprovalCloseBtn"), driver);
			System.out.println("************ QA - Reject test case for [" + documentid + "] is PASS ************");
		} catch (Exception Ex) {
			System.out.println(
					"!!!!-WARNING-!!!!!!!!! QA - Reject test case for [" + documentid + "] is FAIL !!!!!!!!!!!!!");
			Utilities.handleError(Ex, driver);
		}
	}

}
