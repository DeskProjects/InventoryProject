package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.utils.SikulliUtil;

public class ReceivePaymentAgainstVendor {

	// ********************************************************Refud.dep*********************************************************
	public static void create_ReceivePayment_Vendor(String documentid, String productid, String Refudamount,
			String vendorid, WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstVendor.properties");
			WebDriverWait wait = new WebDriverWait(driver, 45);

			Utilities.waitandClick(pro.getProperty("ReceivePaymentIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("ReceivePaymentAgainstVendorCheck"), driver);
			Utilities.HoverandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox("RPVend" + documentid, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("vednorID"), driver);
			WebElement memo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Memo"))));
			memo.click();

			int headerSize = Utilities.totalSize("//*[text()='Document Type']/ancestor::tr/td/div", driver);
			int indOfdocType = 0, indOfdocNum = 0, indOfAmount = 0;

			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver
						.findElement(By.xpath("//*[text()='Document Type']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headerName.equalsIgnoreCase("Document Type")) {
					indOfdocType = i;
				} else if (headerName.equalsIgnoreCase("Document Number")) {
					indOfdocNum = i;
				} else if (headerName.equalsIgnoreCase("Enter Amount")) {
					indOfAmount = i;
				}
			}

			// Enter "Advanced / Deposit"
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfdocType + "]/div",
					driver);
			Utilities.enterTextandSelect("Refund / Deposit", "//*[@name='type']/following::input[1]", driver);
			memo.click();
			// Enter Amount
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfAmount + "]/div",
					driver);
			Utilities.enter_LinelabelAmount(Refudamount, driver);
			memo.click();

			// Link Note
			// -------------------------------------------------------------------------------
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfdocType + "]/div",
					driver);
			Utilities.enterTextandSelect("Debit Note", "//*[@name='type']/following::input[1]", driver);
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfdocNum + "]/div",
					driver);
			// search Note
			Utilities.enterTextNormalBox("DnO" + documentid, pro.getProperty("QuickSearchDebitNote"), driver);
			Utilities.click_element("//button[text()='Fetch']", driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("SelectDebitNote"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("SubmitButton1"), driver);
			// Enter Amount
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfAmount + "]/div",
					driver);
			Utilities.enter_LinelabelAmount("10", driver);
			memo.click();

			Utilities.HoverandClick(pro.getProperty("SaveButton"), driver);
			Utilities.clickButton("Yes", 500, driver);

			WebElement FinalOk = null;
			try {
				FinalOk = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
			} catch (Exception E) {
				Utilities.clickButton("Yes", 100, driver); // continue Extra Yes
			}

			FinalOk = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
			if (FinalOk.isDisplayed()) {
				Thread.sleep(800);
				FinalOk.click();
				System.out.println("Receive Payment against Vendor successfully for doc id : [" + documentid + "]");
			}

			// Email code
			String subjectLine = "Receive Payment Against Vendor - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);
			Utilities.HoverandClick(pro.getProperty("CloseMakePayment"), driver);
		} catch (Exception EX) {
			System.out.println("RP aginst VENDOR failed !!!!!!!!!!" + documentid);
			Utilities.handleError(EX, driver);
		}
	}

	// ************************************************************Verification*********************************************************************

	public static void verify_paymentMade(String documentID, String productID, String vendorID, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstVendor.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentReport"))));
			driver.findElement(By.xpath(pro.getProperty("ReceivePaymentReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentID);
			Thread.sleep(1000);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);
			Utilities.zoomOut();
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Receipt No")) {
					assertEquals(documentID,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("payment no verified");
				}

				else if (header.equals("Name")) {
					assertEquals(vendorID + "Name",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("Vendor is verified");
				}

				else if (header.equals("Payment Method")) {
					assertEquals("Cash",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("payment Method verified");
				}

				// else if(header.equals("Memo"))
				// {assertEquals("memo"+documentID,
				// driver.findElement(By.xpath(".//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["+i+"]/div")).getText());}

			}
			Utilities.zoomIn();
			System.out.println("********** Receive Payment against VENDOR report successfully verified for :["
					+ documentID + "] *********************");

			// Click on Close
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ClosePaymentReceiveReport"))));
			driver.findElement(By.xpath(pro.getProperty("ClosePaymentReceiveReport"))).click();
			Thread.sleep(2000);

		} catch (Exception EX) {
			System.out.println("********** Receive Payment against VENDOR report NOT verified for :[" + documentID
					+ "] *********************");
			Utilities.handleError(EX, driver);
		}

	}

	public static void EmailCopyEditDelete_ReceivePayment_VendorAdvance(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstVendor.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentReport"))));
			driver.findElement(By.xpath(pro.getProperty("ReceivePaymentReport"))).click();
			Thread.sleep(5000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			// Report Email code
			String subjectLine = "Receive Payment Against Vendor Refund/Deposite - " + documentid;
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys(documentid);
			Thread.sleep(500);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement copyReceivePaymentAgainstVendorButton = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("copyReceivePaymentAgainstVendorButton"))));
			copyReceivePaymentAgainstVendorButton.click();
			Thread.sleep(2000);

			try {
				Utilities.clickButton("OK", 1000, driver);
			}

			catch (Exception notP) {
				System.out.println("NOT Fully paid");
			}

			finally {

				String CopyReceivePaymentAgainstVendorAdvanceID = "Copy" + documentid;

				new WebDriverWait(driver, 60).until(ExpectedConditions
						.elementToBeClickable(By.xpath(pro.getProperty("copyReceivePaymentAgainstVendorId"))));
				driver.findElement(By.xpath(pro.getProperty("copyReceivePaymentAgainstVendorId")))
						.sendKeys(CopyReceivePaymentAgainstVendorAdvanceID);
				Thread.sleep(1000);

				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(pro.getProperty("saveCopiedReceivePaymentAgainstVendorId"))));
				driver.findElement(By.xpath(pro.getProperty("saveCopiedReceivePaymentAgainstVendorId"))).click();

				Thread.sleep(3000);

				Robot r = new Robot();

				Utilities.clickButton("Yes", 500, driver);
				Utilities.clickButton("OK", 1500, driver);

				try {
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath(pro.getProperty("CopyReceivePaymentAgainstVendorClose"))));
					driver.findElement(By.xpath(pro.getProperty("CopyReceivePaymentAgainstVendorClose"))).click();
					Thread.sleep(3000);
				} catch (Exception EX) {
					r.keyPress(KeyEvent.VK_F5);
					r.keyRelease(KeyEvent.VK_F5);

				}

				Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
				search.clear();
				search.sendKeys(CopyReceivePaymentAgainstVendorAdvanceID);
				Thread.sleep(500);
				search.sendKeys(Keys.TAB);
				Thread.sleep(2000);

				Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
				Thread.sleep(1000);

				WebElement editReceivePaymentAgainstVendorAdvanceButton = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(pro.getProperty("EditReceivePaymentAgainstVendorButton"))));
				editReceivePaymentAgainstVendorAdvanceButton.click();
				Thread.sleep(3000);

				WebElement ReceivePaymentAgainstVendorAdvanceMemo = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentAgainstVendorMemo"))));
				ReceivePaymentAgainstVendorAdvanceMemo.sendKeys("Update Receive Payment Against Vendor Advance Memo");
				Thread.sleep(1000);

				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentAgainstVendorEditSave"))));
				driver.findElement(By.xpath(pro.getProperty("ReceivePaymentAgainstVendorEditSave"))).click();

				Utilities.clickButton("Yes", 1500, driver);
				Utilities.clickButton("OK", 1500, driver);

				try {
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath(pro.getProperty("EditReceivePaymentAgainstVendorClose"))));
					driver.findElement(By.xpath(pro.getProperty("EditReceivePaymentAgainstVendorClose"))).click();
					Thread.sleep(4000);
				} catch (Exception EX) {
					r.keyPress(KeyEvent.VK_F5);
					r.keyRelease(KeyEvent.VK_F5);

				}

				Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
				search.clear();
				search.sendKeys(CopyReceivePaymentAgainstVendorAdvanceID);
				Thread.sleep(500);
				search.sendKeys(Keys.TAB);
				Thread.sleep(2000);

				try {
					Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
					Thread.sleep(3000);
					if (driver
							.findElement(By
									.xpath("//div[@id='receiptReport']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
							.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
						System.out.println("Receive Payment Against Vendor Advance "
								+ CopyReceivePaymentAgainstVendorAdvanceID + " is edited Successfully");
					} else {
						System.out.println("Receive Payment Against Vendor Advance "
								+ CopyReceivePaymentAgainstVendorAdvanceID + " is not edited Successfully");
					}

				} catch (Exception EX) {
					r.keyPress(KeyEvent.VK_F5);
					r.keyRelease(KeyEvent.VK_F5);
				}

				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(pro.getProperty("deleteReceivePaymentAgainstVendorButton"))));
				driver.findElement(By.xpath(pro.getProperty("deleteReceivePaymentAgainstVendorButton"))).click();

				Thread.sleep(2000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(pro.getProperty("deleteReceivePaymentAgainstVendorPermButton"))));
				driver.findElement(By.xpath(pro.getProperty("deleteReceivePaymentAgainstVendorPermButton"))).click();
				Thread.sleep(2000);

				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("OK", 0, driver);

				Thread.sleep(5000);

				try {
					search.clear();
					search.sendKeys(CopyReceivePaymentAgainstVendorAdvanceID);
					Thread.sleep(500);
					search.sendKeys(Keys.TAB);
					Thread.sleep(2000);

					if (driver
							.findElement(By
									.xpath("//div[@id='receiptReport']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
							.getText().equalsIgnoreCase("There is no record to display.")) {
						System.out.println("Receive Payment Against Vendor Advance "
								+ CopyReceivePaymentAgainstVendorAdvanceID + "  is Deleted Successfully");
					} else {
						System.out.println("Receive Payment Against Vendor Advance "
								+ CopyReceivePaymentAgainstVendorAdvanceID + "  is not Deleted Successfully");
					}
				} catch (Exception exp) {
					System.out.println("Receive Payment Against Vendor Advance "
							+ CopyReceivePaymentAgainstVendorAdvanceID + " is Successfully deleted");
				}

				Utilities.refresh();

			}
		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}
	}

	// * * * * * * * EXPORT Receive Payment * * * * * *

	public static void Export_ReceivePayment(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String ExportButtonName = "Export ";
			Properties pro2 = Utilities.fetchProValue("OR_ReceivePaymentAgainstVendor.properties");
			String reportIcon = pro2.getProperty("ReceivePaymentReport");
			String waitForQuickSearch = pro2.getProperty("DocumentCheckBox");
			String closeReportPage = pro2.getProperty("ClosePaymentReceiveReport");

			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(reportIcon)));
			element.click();
			Thread.sleep(1000);

			Utilities.clickCheckBox(waitForQuickSearch, "uncheck", driver);

			// select from date
			Utilities.HoverandClick(pro.getProperty("fromIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
			Thread.sleep(1000);

			// select To date
			Utilities.HoverandClick(pro.getProperty("toIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
			Thread.sleep(1000);

			Utilities.clickButton("Fetch", 500, driver);
			Thread.sleep(1000);

			try {
				Utilities.clickCheckBox(waitForQuickSearch, "check", driver);

				// CSv File
				Utilities.clickButton(ExportButtonName, 500, driver);
				Utilities.HoverandClick(pro.getProperty("ExportToCSV"), driver);
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "CSVtype");
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
				System.out.println("* * * * * * EXPORT for [.CSV] completed successfully * * * * * * *");
				Thread.sleep(1500);

				// PDF
				Utilities.clickButton(ExportButtonName, 500, driver);
				String parentWindow = driver.getWindowHandle();
				Utilities.HoverandClick(pro.getProperty("ExportToPDF"), driver);
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("pdfTemplate"))));
				element.click();
				Thread.sleep(1000);
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "PDFtype");
				Thread.sleep(1500);
				Set s = driver.getWindowHandles(); // this method will gives you
													// the handles of all opened
													// windows
				Iterator ite = s.iterator();
				while (ite.hasNext()) {
					String childWindow = ite.next().toString();
					if (!childWindow.contains(parentWindow)) {
						driver.switchTo().window(childWindow);
						Thread.sleep(1000);
						System.out.println("* * * * * * EXPORT for [PDF] completed successfully * * * * * * *");
						driver.close();
						driver.switchTo().window(parentWindow);
						Thread.sleep(1000);
					}
				}

				// Excel in Details
				Utilities.clickButton(ExportButtonName, 500, driver);
				Actions action = new Actions(driver);
				WebElement hoverElement = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ExportToExcel"))));
				action.moveToElement(hoverElement).build().perform();
				Thread.sleep(1000);
				Utilities.HoverandClick(pro.getProperty("ExcelInDetail"), driver);
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "XLStype");
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
				System.out.println("* * * * * * EXPORT for [.Excel in Detail ] completed successfully * * * * * * *");
				Thread.sleep(1500);

				// Excel Summary
				Utilities.clickButton(ExportButtonName, 500, driver);
				hoverElement = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ExportToExcel"))));
				action.moveToElement(hoverElement).build().perform();
				Thread.sleep(1000);
				Utilities.HoverandClick(pro.getProperty("ExcelInSummary"), driver);
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "XLStype");
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
				System.out.println("* * * * * * EXPORT for [.Excel Summary ] completed successfully * * * * * * *");
				Thread.sleep(1500);

				// Export to HTML
				Utilities.clickButton(ExportButtonName, 500, driver);
				parentWindow = driver.getWindowHandle();
				Utilities.justHover(pro.getProperty("ExportToHtml2"), driver);
				Utilities.HoverandClick(pro.getProperty("PrintAllRecord"), driver);
				Utilities.clickButton("Print", 1000, driver);
				s = driver.getWindowHandles(); // this method will gives you the
												// handles of all opened windows
				ite = s.iterator();
				while (ite.hasNext()) {
					String childWindow = ite.next().toString();
					if (!childWindow.contains(parentWindow)) {
						driver.switchTo().window(childWindow);
						driver.manage().window().maximize();
						Thread.sleep(1000);
						System.out.println("* * * * * * EXPORT with [Print] completed successfully * * * * * * *");
						driver.close();
						driver.switchTo().window(parentWindow);
						Thread.sleep(1500);
					}
				}

				Utilities.HoverandClick(closeReportPage, driver);
				System.out.println(
						"* * * * * * EXPORT for module [Receive Payment] completed successfully * * * * * * *");

			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				Utilities.handleError(noData, driver);
			}
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [Receive Payment] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}

	}

	// get button path for Sikulli
	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;

	}

	/************ RP Vendor for Multi ****/
	public static void create_ReceivePayment_VendorMulti(String documentid, String productid, String Refudamount,
			String vendorid, String RefunDepositAmt, String DebitNoteAmt, WebDriver driver, String BankName,
			String DocumentCurrency, String ForexCurrecny1) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstVendor.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentIcon"))));
			driver.findElement(By.xpath(pro.getProperty("ReceivePaymentIcon"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentAgainstVendorCheck"))));
			driver.findElement(By.xpath(pro.getProperty("ReceivePaymentAgainstVendorCheck"))).click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
			driver.findElement(By.xpath(pro.getProperty("SubmitButton1"))).click();
			Thread.sleep(1000);

			// sequence format document no.
			WebElement squenceRP = driver.findElement(By.xpath(pro.getProperty("SequenceFormat")));
			squenceRP.clear();
			squenceRP.sendKeys("NA");
			Thread.sleep(1000);
			squenceRP.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentNo"))));
			driver.findElement(By.xpath(pro.getProperty("DocumentNo"))).sendKeys("RPVendMul" + documentid);
			Thread.sleep(1000);

			WebElement vendor = driver.findElement(By.xpath(pro.getProperty("vednorID")));
			vendor.clear();
			vendor.sendKeys(vendorid);
			Thread.sleep(2000);
			vendor.sendKeys(Keys.ENTER);
			Thread.sleep(1000);

			WebElement memo = driver.findElement(By.xpath(pro.getProperty("Memo")));
			memo.click();
			memo.sendKeys("Receive Payment Against Vendor Advance");

			Thread.sleep(1000);

			Utilities.waitandSendkey(pro.getProperty("PaymentMethod"), driver, BankName);
			Utilities.Enter();
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}
			} catch (Exception Ex) {

			}

			Utilities.waitandClick(pro.getProperty("DocumentCurrencyIMg"), driver);
			Thread.sleep(2000);
			String LoadedCurrency = driver
					.findElement(By
							.xpath("//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]//div[@class='x-combo-list-item x-combo-selected']"))
					.getText();

			Utilities.enterTextandSelect(DocumentCurrency, pro.getProperty("DocumentCurrency"), driver);

			if (!LoadedCurrency.equals(DocumentCurrency))
				try {
					WebElement button = new WebDriverWait(driver, 5)
							.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
					if (button.isDisplayed()) {
						Thread.sleep(20);
						button.click();
					}

				} catch (Exception Ex) {
					// System.out.println("Document Currecny is not Changed");
				}

			// WebElement Che =
			// driver.findElement(By.xpath(pro.getProperty("ChequeSeq")));
			// Che.click();
			// Che.clear();
			// Che.sendKeys("NA");
			if (!BankName.equals("Cash")) {

				Utilities.waitandSendkey(pro.getProperty("Chequeno"), driver, "CNo" + documentid);
				Utilities.Tab();

				Utilities.waitandClick(pro.getProperty("BankName2"), driver);
				Robot R = new Robot();
				R.keyPress(KeyEvent.VK_DOWN);
				R.keyRelease(KeyEvent.VK_DOWN);
				R.keyPress(KeyEvent.VK_DOWN);
				Utilities.Enter();

			}

			List<WebElement> header = driver.findElements(By.xpath(
					"//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"));
			int totalHeadCnt = header.size();

			WebDriverWait wait = new WebDriverWait(driver, 500);

			int indexOfDocType = 0, indexOfDocNo = 0, indexOfAmount = 0;
			Robot r = new Robot();
			for (int i = 1; i <= totalHeadCnt; i++) {
				String headName = driver.findElement(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (headName.equalsIgnoreCase("Document Type")) {
					indexOfDocType = i;
				} else if (headName.equalsIgnoreCase("Document Number")) {
					indexOfDocNo = i;
				} else if (headName.equalsIgnoreCase("Enter Amount")) {
					indexOfAmount = i;
				}
			}

			// add code for Refund / Deposit
			WebElement docTyp = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
							+ indexOfDocType + "]/div")));
			docTyp.click();
			Thread.sleep(2000);
			Utilities.sendText("Refund / Deposit");
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			memo.click();
			Thread.sleep(1500);

			WebElement ammount = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
							+ indexOfAmount + "]/div/div")));
			ammount.click();
			Thread.sleep(1500);
			Utilities.sendText(RefunDepositAmt);
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			memo.click();
			Thread.sleep(1500);

			// * * * * Add code for Credit Note
			docTyp = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[2]/table/tbody/tr/td["
							+ indexOfDocType + "]/div")));
			docTyp.click();
			Thread.sleep(2000);
			Utilities.sendText("Debit Note");
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			memo.click();
			Thread.sleep(1500);

			WebElement dcoNumber = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[2]/table/tbody/tr/td["
							+ indexOfDocNo + "]/div")));
			dcoNumber.click();
			Thread.sleep(2000);

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='quickSearchnoteinfowindow']")));
			driver.findElement(By.xpath(".//*[@id='quickSearchnoteinfowindow']")).sendKeys(documentid);
			Thread.sleep(4000);// pro

			// select

			for (int k = 1; k <= 2; k++) {
				WebElement cust = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(".//*[@id='centerpannoteinfowindow']/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
								+ k + "]/table/tbody/tr/td[1]/div/div")));
				cust.click();
				Thread.sleep(1000);
			}
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Submit']")));
			driver.findElement(By.xpath("//button[text()='Submit']")).click();
			Thread.sleep(2000);
			memo.click();
			Thread.sleep(1000);

			for (int l = 2; l <= 3; l++) {
				ammount = wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div["
								+ l + "]/table/tbody/tr/td[" + indexOfAmount + "]/div/div")));
				ammount.click();
				Thread.sleep(1500);
				Utilities.sendText(DebitNoteAmt);
				Thread.sleep(2000);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(1000);
				memo.click();
				Thread.sleep(1500);
			}

			WebElement docTyp2 = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[4]/table/tbody/tr/td["
							+ indexOfDocType + "]/div")));
			docTyp2.click();
			Thread.sleep(2000);
			Utilities.sendText("Refund / Deposit");
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			memo.click();
			Thread.sleep(1500);

			WebElement dcoNumber2 = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[4]/table/tbody/tr/td["
							+ indexOfDocNo + "]/div")));
			dcoNumber2.click();
			Thread.sleep(2000);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='quickSearchadvancepaymentwindow']")));
			driver.findElement(By.xpath(".//*[@id='quickSearchadvancepaymentwindow']")).sendKeys(documentid);
			Thread.sleep(4000);
			for (int m = 1; m <= 2; m++) {
				WebElement cust = wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath(".//*[@id='centerpanadvancepaymentwindow']/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
								+ m + "]/table/tbody/tr/td[1]/div/div")));
				cust.click();
				Thread.sleep(1000);
			}

			Utilities.clickButton("Submit", 2000, driver);

			for (int j = 4; j <= 5; j++) {
				WebElement ammount2 = wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[" + indexOfAmount + "]/div/div")));
				ammount2.click();
				Thread.sleep(1500);
				Utilities.sendText(RefunDepositAmt);
				Thread.sleep(2000);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(1000);
				memo.click();
				Thread.sleep(1500);

			}

			Thread.sleep(2000);
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SaveButton"))));
			driver.findElement(By.xpath(pro.getProperty("SaveButton"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 1000, driver);
			Thread.sleep(500);

			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}

			} catch (Exception ex) {
			}
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(500);

			// Email code
			// String subjectLine="Receive Payment Against Vendor
			// Refund/Deposite - testsmoke - "+documentid;
			// EmailFunctionality.email_AfterSave(documentid,subjectLine,driver);
			// Thread.sleep(1000);

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseMakePayment"))));
			driver.findElement(By.xpath(pro.getProperty("CloseMakePayment"))).click();

			System.out.println("Receive Payment against Vendor successfully for doc id : [" + documentid + "]");
		} catch (Exception EX) {
			System.out.println("RP aginst VENDOR failed !!!!!!!!!!" + documentid);
			Utilities.handleError(EX, driver);
		}
	}

}
