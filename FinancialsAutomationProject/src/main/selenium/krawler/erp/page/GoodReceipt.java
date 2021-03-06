package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class GoodReceipt {
	public static void create_goodReceipt(String documentid, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_GoodReceipt.properties");
			documentid = "GR" + documentid;
			// clicked on Purchase Invoice
			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("GoodReceiptIcon"))));
			driver.findElement(By.xpath(pro.getProperty("GoodReceiptIcon"))).click();
			Thread.sleep(5000);// pro

			// enter vendor name
			WebElement vendor = driver.findElement(By.xpath(pro.getProperty("Vendor")));
			vendor.clear();
			vendor.sendKeys(vendorid);
			Thread.sleep(3000);// pro
			vendor.sendKeys(Keys.ENTER);

			// sequence format document no.
			WebElement squence = driver.findElement(By.xpath(pro.getProperty("SequenceFormat")));
			squence.clear();
			squence.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentId"))));
			driver.findElement(By.xpath(pro.getProperty("DocumentId"))).sendKeys(documentid);

			// enter vendor name
			WebElement term = driver.findElement(By.xpath(pro.getProperty("Term")));
			term.clear();
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("GRaddButton"))));
			driver.findElement(By.xpath(pro.getProperty("GRaddButton"))).click();

			// search product id
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Search"))));
			driver.findElement(By.xpath(pro.getProperty("Search"))).sendKeys(productid);
			Thread.sleep(2000);// pro

			// select check box
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Selectproduct"))));
			driver.findElement(By.xpath(pro.getProperty("Selectproduct"))).click();
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AddButton"))));
			driver.findElement(By.xpath(pro.getProperty("AddButton"))).click();
			Thread.sleep(3000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//*[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//*[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Actual Quantity")) {
					driver.findElement(By
							.xpath("//*[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[2]/div/div/table/tbody/tr/td["
									+ i + "]"))
							.click();
					Thread.sleep(2000);
					Utilities.sendText("10000");
					break;
				}
			}
			driver.findElement(By.xpath(pro.getProperty("Memo"))).click();

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//*[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//*[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Received Quantity")) {
					driver.findElement(By
							.xpath("//*[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[2]/div/div/table/tbody/tr/td["
									+ i + "]"))
							.click();
					Thread.sleep(2000);
					Utilities.sendText("10000");
					break;
				}
			}
			/*
			 * // Click on Actual Quantity new WebDriverWait(driver,
			 * 30).until(ExpectedConditions.elementToBeClickable(By.xpath(pro.
			 * getProperty("ActualQuantity")))); WebElement quantity =
			 * driver.findElement(By.xpath(pro.getProperty("ActualQuantity")));
			 * quantity.click(); Thread.sleep(1000);// pro
			 * 
			 * Thread.sleep(2000); Utilities.sendText("100");
			 * Thread.sleep(2000);
			 * 
			 * driver.findElement(By.xpath(pro.getProperty("Memo"))).click();
			 * 
			 * // Click on select NA of customer ID new WebDriverWait(driver,
			 * 30).until(ExpectedConditions.elementToBeClickable(By.xpath(pro.
			 * getProperty("ReceivedQuantity")))); WebElement quantity1 =
			 * driver.findElement(By.xpath(pro.getProperty("ReceivedQuantity")))
			 * ; quantity1.click(); Thread.sleep(1000);// pro
			 * 
			 * Thread.sleep(2000); Utilities.sendText("100");
			 * Thread.sleep(2000);
			 */
			driver.findElement(By.xpath(pro.getProperty("Memo"))).click();

			// Click on select NA of customer ID
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("BatchSerialSelectionWindow"))));
			WebElement quantity2 = driver.findElement(By.xpath(pro.getProperty("BatchSerialSelectionWindow")));
			quantity2.click();
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("BatchSerialSelectionWindowSubmit"))));
			driver.findElement(By.xpath(pro.getProperty("BatchSerialSelectionWindowSubmit"))).click();
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SaveButton"))));
			driver.findElement(By.xpath(pro.getProperty("SaveButton"))).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("YesButton"))));
			driver.findElement(By.xpath(pro.getProperty("YesButton"))).click();

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("OkButton"))));
			driver.findElement(By.xpath(pro.getProperty("OkButton"))).click();
			Thread.sleep(1000);

			// Email code
			String subjectLine = "Goods Reciept Order - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseGoodReceipt"))));
			driver.findElement(By.xpath(pro.getProperty("CloseGoodReceipt"))).click();
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	public static void verify_GoodReceipt(String documentid, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			// documentid="GR"+documentid;
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);

			final Properties pro = Utilities.fetchProValue("OR_GoodReceipt.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("GoodReceiptReport"))));
			driver.findElement(By.xpath(pro.getProperty("GoodReceiptReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentid);
			Thread.sleep(4000);

			WebElement element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					return webDriverArg.findElement(By.xpath(pro.getProperty("TotalResult")));
				}
			});

			String resultText = element.getText();
			String[] resultTextArray = resultText.split("of");
			String resultSetTotalCntString = "";
			for (String resultTextElement : resultTextArray) {
				resultSetTotalCntString = resultTextElement;
			}
			int resultSetTotalCnt = Integer.parseInt(resultSetTotalCntString.trim());
			// System.out.println("resultSetTotalCnt > > > "+resultSetTotalCnt);

			int goodRecnoRowIndex = 0, vendorRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg21GoodsReceiptDeliveryOrderListEntry']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			Thread.sleep(1000);

			for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg21GoodsReceiptDeliveryOrderListEntry']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equals("Goods Receipt No")) {
					goodRecnoRowIndex = rowIndex;
					// System.out.println("goodRecnoRowIndex > "+
					// goodRecnoRowIndex);

				} else if (header.equals("Vendor")) {
					vendorRowIndex = rowIndex;
					// System.out.println("vendorRowIndex > "+ vendorRowIndex);

				}
			}

			for (int i = 1; i <= resultSetTotalCnt; i++) {
				assertEquals(
						documentid, driver
								.findElement(By
										.xpath(".//div[@id='gridmsg21GoodsReceiptDeliveryOrderListEntry']/div/div[2]/div/div[1]/div[2]/div/div["
												+ i + "]/table/tbody/tr[1]/td[" + goodRecnoRowIndex + "]/div"))
								.getText());
				// System.out.println("Verified Goods Receipt No [" + documentid
				// + "] matched with UI");
				assertEquals(vendorid + "Name",
						driver.findElement(By
								.xpath(".//div[@id='gridmsg21GoodsReceiptDeliveryOrderListEntry']/div/div[2]/div/div[1]/div[2]/div/div["
										+ i + "]/table/tbody/tr[1]/td[" + vendorRowIndex + "]/div"))
								.getText());
				// System.out.println("Verified Vendor Name [" + vendorid+"Name"
				// + "] matched with UI");
				break;
			}

			String tab_ID = pro.getProperty("reportCloseBtn");
			Utilities.HoverandClick(tab_ID, driver);
			System.out.println("Good Receipt Successfully verified");

		}

		catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	public static void EmailCopyEditDelete_goodReceipt(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "GR" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_GoodReceipt.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("GoodReceiptReport"))));
			driver.findElement(By.xpath(pro.getProperty("GoodReceiptReport"))).click();
			Thread.sleep(5000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentid);
			Thread.sleep(1000);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			// Report Email code
			String subjectLine = "Goods Reciept Order - testsmoke - " + documentid;
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);
			Thread.sleep(3000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			search.clear();
			Thread.sleep(1000);
			search.sendKeys(documentid);
			Thread.sleep(1000);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			WebElement copyGoodReceiptButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyGoodReceiptButton"))));
			copyGoodReceiptButton.click();
			Thread.sleep(2000);

			String CopyGoodReceiptID = "Copy" + documentid;

			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyGoodReceiptId"))));
			driver.findElement(By.xpath(pro.getProperty("copyGoodReceiptId"))).sendKeys(CopyGoodReceiptID);

			WebElement warehouselocationlnk = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("WareHouseAndLocationGoodReceiptLink"))));
			warehouselocationlnk.click();
			Thread.sleep(2000);
			Robot warehouseRobot = new Robot();
			/*
			 * WebElement warehouse =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			 * ".//*[contains(@id,'centerpanwtf-comp-')]/div/div/div/div/div/div/div/div[2]/div/div[1]/table[1]/tbody/tr/td[2]/div"
			 * ))); warehouse.click(); Thread.sleep(2000);
			 * 
			 * 
			 * Utilities.sendText("DS - Default Store"); Thread.sleep(2000);
			 * warehouseRobot.keyPress(KeyEvent.VK_ENTER);
			 * warehouseRobot.keyRelease(KeyEvent.VK_ENTER); Thread.sleep(2000);
			 * 
			 * WebElement location =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			 * ".//*[contains(@id,'centerpanwtf-comp-')]/div/div/div/div/div/div/div/div[2]/div/div[1]/table[1]/tbody/tr/td[6]/div"
			 * ))); location.click(); Thread.sleep(2000); Robot
			 * locationRobot=new Robot();
			 * 
			 * Utilities.sendText("Default Location"); Thread.sleep(2000);
			 * locationRobot.keyPress(KeyEvent.VK_ENTER);
			 * locationRobot.keyRelease(KeyEvent.VK_ENTER);
			 * locationRobot.keyPress(KeyEvent.VK_ENTER);
			 * locationRobot.keyRelease(KeyEvent.VK_ENTER); Thread.sleep(2000);
			 */
			WebElement warehouselocsubmit = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("saveGoodReceiptWSLocationButton"))));
			warehouselocsubmit.click();
			Thread.sleep(2000);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("saveCopiedGoodReceiptId"))));

			driver.findElement(By.xpath(pro.getProperty("saveCopiedGoodReceiptId"))).click();

			Utilities.clickButton("Yes", 1000, driver);
			Thread.sleep(2000);
			Utilities.clickButton("OK", 500, driver);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("CopyGoodReceiptClose"))));
				driver.findElement(By.xpath(pro.getProperty("CopyGoodReceiptClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				EX.printStackTrace();
			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			search.clear();
			Thread.sleep(1000);
			search.sendKeys(CopyGoodReceiptID);
			Thread.sleep(1000);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			WebElement editGoodReceiptButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditGoodReceiptButton"))));
			editGoodReceiptButton.click();
			Thread.sleep(3000);

			WebElement GoodReceiptMemo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("GoodReceiptMemo"))));
			GoodReceiptMemo.sendKeys("Update Good Receipt Memo");

			warehouselocationlnk = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("WareHouseAndLocationGoodReceiptEditLink"))));
			warehouselocationlnk.click();
			Thread.sleep(2000);
			/*
			 * warehouse =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			 * ".//*[contains(@id,'centerpanwtf-comp-')]/div/div/div/div/div/div/div/div[2]/div/div[1]/table[1]/tbody/tr/td[2]/div"
			 * ))); warehouse.click(); Thread.sleep(2000); warehouseRobot=new
			 * Robot();
			 * 
			 * Utilities.sendText("DS - Default Store"); Thread.sleep(2000);
			 * warehouseRobot.keyPress(KeyEvent.VK_ENTER);
			 * warehouseRobot.keyRelease(KeyEvent.VK_ENTER); Thread.sleep(2000);
			 * 
			 * location =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			 * ".//*[contains(@id,'centerpanwtf-comp-')]/div/div/div/div/div/div/div/div[2]/div/div[1]/table[1]/tbody/tr/td[6]/div"
			 * ))); location.click(); Thread.sleep(2000); locationRobot=new
			 * Robot();
			 * 
			 * Utilities.sendText("Default Location"); Thread.sleep(2000);
			 * locationRobot.keyPress(KeyEvent.VK_ENTER);
			 * locationRobot.keyRelease(KeyEvent.VK_ENTER);
			 * locationRobot.keyPress(KeyEvent.VK_ENTER);
			 * locationRobot.keyRelease(KeyEvent.VK_ENTER); Thread.sleep(2000);
			 */
			warehouselocsubmit = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("saveGoodReceiptWSLocationButton"))));
			warehouselocsubmit.click();
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("GoodReceipteEditSave"))));
			driver.findElement(By.xpath(pro.getProperty("GoodReceipteEditSave"))).click();

			Utilities.clickButton("Yes", 1000, driver);
			Thread.sleep(2000);
			Utilities.clickButton("OK", 500, driver);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("EditGoodReceiptClose"))));
				driver.findElement(By.xpath(pro.getProperty("EditGoodReceiptClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				EX.printStackTrace();
			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			search.clear();
			Thread.sleep(1000);
			search.sendKeys(CopyGoodReceiptID);
			Thread.sleep(1000);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			try {
				Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
				;

				Thread.sleep(3000);
				if (driver
						.findElement(By
								.xpath("//div[@id='GoodsReceiptDeliveryOrderListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
					System.out.println("Goods Receipt " + CopyGoodReceiptID + " is edited Successfully");
				} else {
					System.out.println("Goods Receipt " + CopyGoodReceiptID + " is not edited Successfully");
				}

			} catch (Exception EX) {
				System.out.println("Edit failed");
			}

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteGoodReceiptButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteGoodReceiptButton"))).click();

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteGoodReceiptPermButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteGoodReceiptPermButton"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);

			try {
				search.clear();
				search.sendKeys(CopyGoodReceiptID);
				Thread.sleep(1000);
				search.sendKeys(Keys.TAB);
				Thread.sleep(1000);

				if (driver
						.findElement(By
								.xpath("//div[@id='GoodsReceiptDeliveryOrderListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("No results to display")) {
					System.out.println("Goods Receipt " + CopyGoodReceiptID + "  is Deleted Successfully");
				} else {
					System.out.println("Goods Receipt " + CopyGoodReceiptID + "  is not Deleted Successfully");
				}
			} catch (Exception exp) {
				System.out.println("Goods Receipt " + CopyGoodReceiptID + " is Successfully deleted");
			}

			String tab_ID = pro.getProperty("reportCloseBtn");
			Utilities.HoverandClick(tab_ID, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}
	}

	public static void setting_QaFlow(String onoroff, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_SystemControls.properties");
			Utilities.refresh();
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			WebElement element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("PreferencesExpander")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("SystemControlsLink")));
				}
			});
			element.click();
			Thread.sleep(10000);

			if (onoroff.equalsIgnoreCase("off")) {
				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg
								.findElement(By.xpath(pro.getProperty("GoodsReceiptNoteQAApprovalFlowCheckBox")));
					}
				});
				if (element.isSelected()) {
					element.click();
					System.out.println("QA Approval Flow in Delivery Order is Deactivated");
				} else {
					System.out.println("QA Approval Flow in Delivery Order is Already Deactivated");
				}
				element = wait.until(new Function<WebDriver, WebElement>() {

					// apply method- which accept webdriver as input
					// @Override
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg.findElement(By.xpath(pro.getProperty("saveAccountPreferencesBtn")));
					}
				});
				element.click();
				Thread.sleep(2000);
				element = wait.until(new Function<WebDriver, WebElement>() {

					// apply method- which accept webdriver as input
					// @Override
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg.findElement(By.xpath(pro.getProperty("applyNewsettingsBtn")));
					}
				});
				element.click();
				Thread.sleep(2000);
				element = wait.until(new Function<WebDriver, WebElement>() {

					// apply method- which accept webdriver as input
					// @Override
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg.findElement(By.xpath(pro.getProperty("AccountPreferencesSavedOKBtn")));
					}
				});
				element.click();

			}

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}
	}

	// * * * * * * * EXPORT Goods Receipt * * * * * *

	public static void Export_GoodsReceipt(WebDriver driver) throws InterruptedException, AWTException, IOException {
		String BtnName2 = "Export List";
		Properties pro = Utilities.fetchProValue("OR_GoodReceipt.properties");
		String reportIcon = pro.getProperty("GoodReceiptReport");
		String waitForQuickSearch = pro.getProperty("DocumentCheckBox");
		String closeReportPage = pro.getProperty("reportCloseBtn");
		String ModuleName = "Goods Receipt";

		ExportFunction.Export_TodayDate(BtnName2, reportIcon, waitForQuickSearch, closeReportPage, ModuleName, driver);

	}
}
