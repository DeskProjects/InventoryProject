package krawler.erp.inventory;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

import krawler.erp.page.Utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

import com.google.common.base.Function;

import java.util.concurrent.TimeUnit;

public class StockAdjustment {

	// ******************************** Stock In
	// ********************************
	public static void createStockAdjustment_StockIn(String documentid, String productid, String batchValue,
			String serialSeq, String quanityValue, String perUnitPrice, boolean qaApprovalFlow, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			final Properties pro = Utilities.fetchProValue("OR_StockAdjustment.properties");
			documentid = "StockIN" + documentid;
			String adjustmentTypeValue = "Stock IN";
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);
			Utilities.waitandClick(pro.getProperty("StockAdjustmentLink"), driver);
			Thread.sleep(1500);
			Utilities.clickExpander(driver);

			Utilities.enterTextandSelect("DS - Default Store", "//input[@id='fromstoremarkoutallmarkoutTab']", driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("StockAdjustmentSequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("StockAdjustmentDocumentId"), driver);
			String memoText = adjustmentTypeValue + " Against " + productid;
			WebElement memo = driver.findElement(By.xpath(pro.getProperty("StockAdjustmentMemo")));
			memo.sendKeys(memoText);
			// Select 1st reason
			Utilities.HoverandClick("//*[@id='reason']/following::img[1]", driver);
			Utilities.HoverandClick("//*[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div[2]",
					driver);

			Utilities.HoverandClick(pro.getProperty("StockAdjustmentAddPrdButton"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("StockAdjustmentSearch"), driver);
			Utilities.isLoadingDisappear(60, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalProducts = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("StockAdjustmentAddButton"), driver);
			Thread.sleep(1500);

			int headerSize = Utilities.totalSize("//*[text()='Adjustment Type']/ancestor::tr/td/div", driver);
			int indOfprdid = 0, indOfadjTyp = 0, indOfqty = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver
						.findElement(By.xpath("//*[text()='Adjustment Type']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Product ID")) {
					indOfprdid = i;
				}
				if (headerName.equalsIgnoreCase("Adjustment Type")) {
					indOfadjTyp = i;
				}
				if (headerName.equalsIgnoreCase("Quantity")) {
					indOfqty = i;
				}
			}

			for (int i = 1; i <= totalProducts; i++) {
				// enter Addj type
				Utilities.HoverandClick("//*[text()='Adjustment Type']/ancestor::div[3]/following::div[1]/div[1]/div["
						+ i + "]/table/tbody/tr/td[" + indOfadjTyp + "]/div", driver);
				Utilities.HoverandClick(
						"//*[@class='x-layer x-editor x-small-editor x-grid-editor' and contains(@style,'visible')]//input[@type='text']/following::img[1]",
						driver);
				Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Stock IN']", driver);
				memo.click();
				// enter Quantity
				Utilities.HoverandClick("//*[text()='Adjustment Type']/ancestor::div[3]/following::div[1]/div[1]/div["
						+ i + "]/table/tbody/tr/td[" + indOfqty + "]/div", driver);
				Utilities.enter_LinelabelAmount(quanityValue, driver);
				memo.click();
			}

			// enter location & serial
			for (int i = 1; i <= totalProducts; i++) {
				int locHeaderSize = 0;
				String headerName = null;
				Utilities.HoverandClick("//*[text()='Adjustment Type']/ancestor::div[3]/following::div[1]/div[1]/div["
						+ i + "]/table/tbody/tr/td//div[@class='pwnd serialNo-gridrow']", driver);

				locHeaderSize = Utilities.totalSize("//*[text()='Location']/ancestor::tr/td/div", driver);
				for (int j = 1; j <= locHeaderSize; j++) {
					headerName = driver.findElement(By.xpath("//*[text()='Location']/ancestor::tr/td[" + j + "]/div"))
							.getText();
					if (headerName.equalsIgnoreCase("Location")) {
						Utilities.HoverandClick(
								"//*[text()='Location']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ j + "]/div",
								driver);
						Utilities.enterTextandSelect("Default Location", "//*[@id='tolocationid']/following::input[1]",
								driver);
					}
				}

				// Enter Qauntity
				for (int j = 1; j <= locHeaderSize; j++) {
					headerName = driver.findElement(By.xpath("//*[text()='Location']/ancestor::tr/td[" + j + "]/div"))
							.getText();
					if (headerName.equalsIgnoreCase("Quantity")) {
						Utilities.HoverandClick(
								"//*[text()='Location']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ j + "]/div",
								driver);
						Utilities.enter_LinelabelAmount(quanityValue, driver);
						Utilities.enter_LinelabelAmount_thenEnter(driver);
					}
				}
				// Enter Batch Number
				for (int j = 1; j <= locHeaderSize; j++) {
					headerName = driver.findElement(By.xpath("//*[text()='Location']/ancestor::tr/td[" + j + "]/div"))
							.getText();
					if (headerName.equalsIgnoreCase("Batch")) {
						Utilities.HoverandClick(
								"//*[text()='Location']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ j + "]/div",
								driver);
						Utilities.enter_LinelabelAmount(batchValue, driver);
						Utilities.enter_LinelabelAmount_thenEnter(driver);
					}
				}

				// Enter Serial Number
				for (int j = 1; j <= locHeaderSize; j++) {
					headerName = driver.findElement(By.xpath("//*[text()='Location']/ancestor::tr/td[" + j + "]/div"))
							.getText();
					if (headerName.equalsIgnoreCase("Serial")) {
						for (int serialNo = 1; serialNo <= Integer.parseInt(quanityValue); serialNo++) {
							Utilities.HoverandClick("//*[text()='Location']/ancestor::div[3]/following::div[1]/div/div["
									+ serialNo + "]/table/tbody/tr/td[" + j + "]/div", driver);
							Utilities.enter_LinelabelAmount(serialSeq + serialNo, driver);
							// Utilities.enter_LinelabelAmount_thenEnter(driver);
						}
					}
				}
				Utilities.HoverandClick("//*[@class='x-window-footer']//*[text()='Submit']", driver);
				Thread.sleep(1000);
			}

			// Unit Price
			Thread.sleep(1000);
			String xpathOfelement = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody//*[@class='pwnd delete-gridrow']";
			Utilities.justHover(xpathOfelement, driver);
			Thread.sleep(1000);
			int indOfUnitPrice = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver
						.findElement(By.xpath("//*[text()='Adjustment Type']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Per Unit Price")) {
					indOfUnitPrice = i;
				}
			}

			for (int i = 1; i <= totalProducts; i++) {
				// enter Price
				Utilities.HoverandClick("//*[text()='Adjustment Type']/ancestor::div[3]/following::div[1]/div[1]/div["
						+ i + "]/table/tbody/tr/td[" + indOfUnitPrice + "]/div", driver);
				Utilities.enter_LinelabelAmount(perUnitPrice, driver);
				memo.click();
			}

			String mainWindow = driver.getWindowHandle();
			List<String> StockAdjustmentForApprovalList = new ArrayList<String>();
			Utilities.clickButton("Save", 500, driver);
			Utilities.clickButton("Yes", 500, driver);
			// without QA
			if (qaApprovalFlow == false) {
				Utilities.clickButton("No", 1000, driver);
			}
			// with QA flow
			if (qaApprovalFlow == true) {
				Utilities.clickButton("Yes", 1000, driver);
				Thread.sleep(1000);
				int serialColumnIndex = 0, productColumnIndex = 0, batchColumnIndex = 0;
				WebElement serial = null;
				StringBuilder stb = new StringBuilder();
				stb.append("Serial,Product,Batch");
				for (serialColumnIndex = 1; serialColumnIndex <= driver
						.findElements(By
								.xpath(".//*[@id='approvalserialselectionwindowid']/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
						.size(); serialColumnIndex++) {
					if (driver.findElement(By
							.xpath(".//*[@id='approvalserialselectionwindowid']/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ serialColumnIndex + "]/div"))
							.getText().equalsIgnoreCase("Serial")) {
						break;
					}
				}
				for (productColumnIndex = 1; productColumnIndex <= driver
						.findElements(By
								.xpath(".//*[@id='approvalserialselectionwindowid']/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
						.size(); productColumnIndex++) {
					if (driver.findElement(By
							.xpath(".//*[@id='approvalserialselectionwindowid']/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ productColumnIndex + "]/div"))
							.getText().equalsIgnoreCase("Product")) {
						break;
					}
				}
				for (batchColumnIndex = 1; batchColumnIndex <= driver
						.findElements(By
								.xpath(".//*[@id='approvalserialselectionwindowid']/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
						.size(); batchColumnIndex++) {
					if (driver.findElement(By
							.xpath(".//*[@id='approvalserialselectionwindowid']/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ batchColumnIndex + "]/div"))
							.getText().equalsIgnoreCase("Batch")) {
						break;
					}
				}

				StockAdjustmentForApprovalList.add(stb.toString());
				// String[] selectStockAdjustmentForApprovalRecords = new
				// String[Integer.parseInt(quanityValue)];
				for (int selectOutCount = 1; selectOutCount <= (Integer.parseInt(quanityValue) * 2); selectOutCount++) {
					serial = driver.findElement(By
							.xpath(".//*[@id='approvalserialselectionwindowid']/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div/div[1]/div[2]/div/div["
									+ selectOutCount + "]/table/tbody/tr/td[1]/div/div"));
					serial.click();
					String selectedSerialNoForApproval = driver
							.findElement(By
									.xpath(".//*[@id='approvalserialselectionwindowid']/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div/div[1]/div[2]/div/div["
											+ selectOutCount + "]/table/tbody/tr/td[" + serialColumnIndex + "]/div"))
							.getText();
					String selectedBatchNo = driver
							.findElement(By
									.xpath(".//*[@id='approvalserialselectionwindowid']/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div/div[1]/div[2]/div/div["
											+ selectOutCount + "]/table/tbody/tr/td[" + batchColumnIndex + "]/div"))
							.getText();
					String selectedProductID = driver
							.findElement(By
									.xpath(".//*[@id='approvalserialselectionwindowid']/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div/div[1]/div[2]/div/div["
											+ selectOutCount + "]/table/tbody/tr/td[" + productColumnIndex + "]/div"))
							.getText();
					stb.setLength(0);
					stb.append(selectedSerialNoForApproval).append(",").append(selectedProductID).append(",")
							.append(selectedBatchNo);
					StockAdjustmentForApprovalList.add(stb.toString());
				}
				Utilities.HoverandClick(pro.getProperty("StockAdjustmentQAFlowSaveBtn"), driver);
			} // close QA flow

			WebElement buttonOk = new WebDriverWait(driver, 40)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
			if (buttonOk.isDisplayed()) {
				Thread.sleep(1000);
				String[] array = StockAdjustmentForApprovalList.toArray(new String[0]);
				writeSerialsToFile(documentid + "StockINApprovalserials" + Utilities.currentDate("dd-MM-yyyy") + ".txt",
						array);

				Utilities.clickButton("OK", 1000, driver);
				Thread.sleep(5000);

				try {
					Utilities.sikuliHandle("Print_Cancel");
					Thread.sleep(3000);
				} catch (Exception ex) {

				}

				Set s = driver.getWindowHandles(); // this method will gives you
													// the handles of all opened
													// windows
				Iterator ite = s.iterator();
				while (ite.hasNext()) {
					String popupHandle = ite.next().toString();
					if (!popupHandle.contains(mainWindow)) {
						driver.switchTo().window(popupHandle);
						driver.close(); // close print popup
						driver.switchTo().window(mainWindow);
						Thread.sleep(2000);
						break;
					}
				}
				Thread.sleep(500);
			}

			Utilities.HoverandClick(pro.getProperty("StockAdjustmentCloseBtn"), driver);
			System.out.println("****** Stock In for [" + documentid + "] with Qauntity [" + quanityValue
					+ "] is successfully created ******");

		} catch (Exception Ex) {
			System.out.println("!!!!!!!! FAILED to create Stock In for [" + documentid + "] with Qauntity ["
					+ quanityValue + "] !!!!!!!!");
			Utilities.handleError(Ex, driver);
		}

	}

	// ********************************************************StockAdjustment
	// Deletion*********************************************************
	public static void delete_StockAdjustment(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_StockAdjustment.properties");
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandReport(driver);

			Utilities.HoverandClick(pro.getProperty("StockAdjustmentRegisterLink"), driver);
			Utilities.click_element(pro.getProperty("QuickSearch"), driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.isLoadingDisappear(160, driver);
			Utilities.click_element(pro.getProperty("DocumentCheckBox"), driver);
			// delete perman
			Utilities.clickButton("Delete", 500, driver);
			Utilities.click_element("//*[contains(@style,'visible')]//*[text()='Delete Permanently']", driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);

			// verify
			Utilities.click_element(pro.getProperty("QuickSearch"), driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.isLoadingDisappear(160, driver);

			try {
				if (driver.findElement(By.xpath("//*[text()='No data to display']")).isDisplayed()) {
					System.out.println("**** Stock Adjustment [" + documentid + "] Deleted suceesfully*****");
					Utilities.click_element(pro.getProperty("StockAdjustmentRegisterCloseBtn"), driver);
				}
			} catch (Exception failedTOdelete) {
				System.out.println("**** FAILED to delete Stock Adjustment [" + documentid + "] Plz check Log *****");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
		} catch (Exception EX) {
			System.out.println("**** FAILED to delete Stock Adjustment [" + documentid + "] Plz check Log *****");
			Utilities.handleError(EX, driver);
		}
	}

	public static void writeSerialsToFile(String fileName, String[] serials) throws Exception {
		FileWriter fw = null;
		try {
			String dir = System.getProperty("user.dir");
			String directorypath = dir + "/test-output/" + fileName;
			File serialFile = new File(directorypath);
			// check if file exist, otherwise create the file before writing
			if (!serialFile.exists()) {
				serialFile.createNewFile();
			}
			fw = new FileWriter(directorypath);
			for (int i = 0; i < serials.length; i++) {
				fw.write(serials[i] + "\n");
			}

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			fw.close();
		}
	}

	public static String[] readSerialsFromFile(String fileName) throws Exception {
		FileReader fr = null;
		String dir = System.getProperty("user.dir");
		String directorypath = dir + "/test-output/" + fileName;
		List<String> lines = new ArrayList<String>();
		BufferedReader bufferedReader = null;
		List<String> listWithoutDuplicates = null;
		try {
			File serialFile = new File(directorypath);
			if (serialFile != null && serialFile.exists()) {
				fr = new FileReader(directorypath);
				bufferedReader = new BufferedReader(fr);

				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					lines.add(line);
				}
				HashSet<String> listToSet = new HashSet<String>(lines);
				listWithoutDuplicates = new ArrayList<String>(listToSet);
			}

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (fr != null) {
				fr.close();
			}

		}
		if (listWithoutDuplicates != null) {
			return listWithoutDuplicates.toArray(new String[listWithoutDuplicates.size()]);
		} else {
			return null;
		}

	}

}
