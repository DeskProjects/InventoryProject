package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import krawler.erp.utils.HandlePrintWindow;

public class MakePaymentAginstVendor {
	public static void create_MakePayment_Vendor(String documentid, String AdvanceAmt, String InvoiceAmt,
			String CreditNoteAmt, String GLAmt, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("MakePaymentAgainstVendorCheck"), driver);
			Utilities.HoverandClick(pro.getProperty("SubmitButton1"), driver);
			Thread.sleep(2000);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox("MPvend" + documentid, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("vendorID"), driver);
			Utilities.enterTextandSelect("Cash", pro.getProperty("PaymentMethod"), driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

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

			// Enter Advanced / Deposit
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfdocType + "]/div",
					driver);
			Utilities.enterTextandSelect("Advanced / Deposit", "//*[@name='type']/following::input[1]", driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			// Enter Amount
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfAmount + "]/div",
					driver);
			Utilities.enter_LinelabelAmount(AdvanceAmt, driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			// Link PI
			// -------------------------------------------------------------------------------
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfdocType + "]/div",
					driver);
			Utilities.enterTextandSelect("Invoice", "//*[@name='type']/following::input[1]", driver);
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfdocNum + "]/div",
					driver);
			// search invoice
			Utilities.enterTextNormalBox("PIExp" + documentid, pro.getProperty("QuickSearchInvoice"), driver);
			Utilities.click_element("//button[text()='Fetch']", driver);
			Utilities.clickCheckBox(pro.getProperty("SelectInvoice"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("SubmitButton1"), driver);
			// Enter Amount
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfAmount + "]/div",
					driver);
			Utilities.enter_LinelabelAmount(InvoiceAmt, driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			// Link Note
			// ----------------------------------------------------------------------------
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[3]/table/tbody/tr/td["
							+ indOfdocType + "]/div",
					driver);
			Utilities.enterTextandSelect("Credit Note", "//*[@name='type']/following::input[1]", driver);
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[3]/table/tbody/tr/td["
							+ indOfdocNum + "]/div",
					driver);
			// search Note
			Utilities.enterTextNormalBox("CrVen" + documentid, pro.getProperty("QuickSearchCreditNote"), driver);
			Utilities.click_element("//button[text()='Fetch']", driver);
			Utilities.clickCheckBox(pro.getProperty("SelectCreditNote"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("SubmitButton1"), driver);
			// Enter Amount
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[3]/table/tbody/tr/td["
							+ indOfAmount + "]/div",
					driver);
			Utilities.enter_LinelabelAmount(CreditNoteAmt, driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			// Against GL
			// ----------------------------------------------------------------------------
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[4]/table/tbody/tr/td["
							+ indOfdocType + "]/div",
					driver);
			Utilities.enterTextandSelect("General Ledger Code", "//*[@name='type']/following::input[1]", driver);
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[4]/table/tbody/tr/td["
							+ indOfdocNum + "]/div",
					driver);
			// search account
			Utilities.enterTextInDropDown("COA" + documentid, "//input[@id='quickSearchaccountinfowindow']", driver);
			Utilities.Enter();
			String xpathSEARChaccount = "//*[text()='Account Name']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td[1]/div/div";
			Utilities.clickCheckBox(xpathSEARChaccount, "check", driver);
			Utilities.HoverandClick(pro.getProperty("SubmitButton1"), driver);
			// Enter Amount
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[4]/table/tbody/tr/td["
							+ indOfAmount + "]/div",
					driver);
			Utilities.enter_LinelabelAmount(GLAmt, driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			Utilities.HoverandClick(pro.getProperty("SaveButton"), driver);

			Utilities.clickButton("Yes", 1000, driver);
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
				System.out.println(
						"*** Make Payment against Vendor is Pass for doc : -> [ Mpvend" + documentid + "] ******");
			}
			Thread.sleep(2000);

			// Email code
			String subjectLine = "Make Payment Against Vendor - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);

			Utilities.click_element(pro.getProperty("CloseMakePayment"), driver);
		} catch (Exception Ex) {
			System.out.println(
					"!!!!! Make Payment against Vendor is FAILEDD for doc : -> [ Mpvend" + documentid + "] !!!!!");
			Utilities.handleError(Ex, driver);
		}
	}

	// ************************************************************Verification*********************************************************************

	public static void verify_paymentMade(String documentID, String vendorID, String PaymentMethod, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			// documentID= "MPvend"+documentID;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PaymentMadeReport"))));
			driver.findElement(By.xpath(pro.getProperty("PaymentMadeReport"))).click();
			Thread.sleep(2000);// pro

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentID);
			Thread.sleep(1000);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Payment No")) {
					assertEquals(documentID,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("MP payment number verified");
				}

				else if (header.equals("Name")) {
					assertEquals(vendorID + "Name",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("Vendor is verified");
				}

				else if (header.equals("Payment Method")) {
					assertEquals(PaymentMethod,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("payment Method verified");
				}
			}

			System.out.println("Make Paymnet against Vendor with [" + PaymentMethod + "] Bank Payment method ["
					+ documentID + "] is Verified !!!");

			// Click on Close
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ClosePaymentMadeReport"))));
			driver.findElement(By.xpath(pro.getProperty("ClosePaymentMadeReport"))).click();
			Thread.sleep(500);

		} catch (Exception EX) {
			System.out.println("Make Paymnet against Vendor with [" + PaymentMethod + "] Payment method [" + documentID
					+ "] is NOT Verified !!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ********************************** Copy-Edit-Delete
	// *************************
	public static void EmailCopyEditDelete_MakePayment(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstGL.properties");

			Utilities.waitandClick(pro.getProperty("PaymentMadeReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			// Report Email code
			String subjectLine = "Make payment Against Vendor : " + documentid;
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);
			Thread.sleep(2000);

			String CopyMakePaymentVendorAdvanceID = "Copy" + documentid;
			// Copy
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Utilities.click_element(pro.getProperty("copyMakePaymentVendorAdvanceButton"), driver);
			Thread.sleep(1000);
			String checkFormfullyLoad = "//*[text()='Action']/ancestor::div[3]/following::div[1]/div/div[2]/table/tbody/tr/td[4]";
			Utilities.clickCheckBox(checkFormfullyLoad, "uncheck", driver);
			Utilities.enterTextNormalBox(CopyMakePaymentVendorAdvanceID,
					pro.getProperty("copyMakePaymentVendorAdvanceId"), driver);
			Utilities.click_element(pro.getProperty("saveCopiedMakePaymentVendorAdvanceId"), driver);
			Utilities.clickButton("Yes", 1000, driver);

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
			}
			Utilities.click_element(pro.getProperty("CopyMakePaymentVendorAdvanceClose"), driver);
			System.out.println("******* Make Payment for [" + documentid + "] Copy test is PASS");

			// Edit
			Utilities.enterTextInDropDown(CopyMakePaymentVendorAdvanceID, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("EditMakePaymentVendorAdvanceButton"), driver);
			Utilities.clickCheckBox(checkFormfullyLoad, "uncheck", driver);
			Utilities.enterTextNormalBox("** Performing Edit test case for [" + documentid + "]",
					pro.getProperty("MakePaymentVendorAdvanceMemo"), driver);
			Utilities.click_element(pro.getProperty("MakePaymentVendorAdvanceEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);

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
			}
			Utilities.click_element(pro.getProperty("EditMakePaymentVendorAdvanceClose"), driver);
			System.out.println("******* Make Payment for [" + documentid + "] EDIT test is PASS");

			// delete
			Utilities.enterTextInDropDown(CopyMakePaymentVendorAdvanceID, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("deleteMakePaymentVendorAdvanceButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteMakePaymentVendorAdvancePermButton"), driver);
			Utilities.clickButton("Yes", 1100, driver);
			Utilities.clickButton("OK", 0, driver);

			// cop-edit-delete flow check
			Utilities.enterTextInDropDown(CopyMakePaymentVendorAdvanceID, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(1500);

			WebElement afterDeleteLink = new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath("//a[contains(@onclick,'javascript:openRecNew')]")));
			if (afterDeleteLink.isDisplayed()) {
				System.out.println("******* Make Payment for [" + documentid + "] DELETE test case is PASS ******");
			}

			Utilities.click_element("//li[@id='as__PaymentMainTabPanel']/a[1]", driver);
		} catch (Exception EX) {
			System.out.println("******* Make Payment for [" + documentid + "] Copy-Edit-Delete test case is FAILED");
			Utilities.handleError(EX, driver);
		}
	}

	// ********************************************************Bank with Cheque
	// print
	// functionality*********************************************************
	public static void create_MakePayment_VendorAdvance_Bank_ChequePrint(String documentid, String productid,
			String vendorid, String payMethod, String chqNumber, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "MPchqVe" + documentid;
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("MakePaymentAgainstVendorCheck"), driver);
			Utilities.HoverandClick(pro.getProperty("SubmitButton1"), driver);
			Thread.sleep(2000);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("vendorID"), driver);
			Utilities.enterTextandSelect(payMethod, pro.getProperty("PaymentMethod"), driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			// enter cheque detail
			Utilities.enterTextandSelect("NA", pro.getProperty("chqSeqFormat"), driver);
			Utilities.enterTextNormalBox(chqNumber, pro.getProperty("chqNumb"), driver);

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

			// Enter Advanced / Deposit
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfdocType + "]/div",
					driver);
			Utilities.enterTextandSelect("Advanced / Deposit", "//*[@name='type']/following::input[1]", driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			// Enter Amount
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfAmount + "]/div",
					driver);
			Utilities.enter_LinelabelAmount("100", driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			// click on Save and Print Cheque
			String ParentWindow = driver.getWindowHandle();
			Utilities.clickButton("Save and Print Cheque", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);

			HandlePrintWindow.closePrintWindow(ParentWindow, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			String subjectLine = "MP against Vendor Cheque Print - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);

			Utilities.click_element(pro.getProperty("CloseMakePayment"), driver);
			System.out.println("MakePayment_VendorAdvance [" + documentid + "] Bank_ChequePrinted successfull !!!!");

		} catch (Exception EX) {
			System.out.println("MakePayment_VendorAdvance [" + documentid + "] Bank_ChequePrinted UN-successfull !!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// *****************************print cheque against vendor
	// *********************************************

	public static void print_MakePayment_VendorfromReport(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "MPchqVe" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PaymentMadeReport"))));
			driver.findElement(By.xpath(pro.getProperty("PaymentMadeReport"))).click();
			Thread.sleep(5000);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			WebElement checkbox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentCheckBox"))));

			if (checkbox.isEnabled()) {
				Thread.sleep(500);
				checkbox.click();
				Utilities.clickButton("Print Cheque ", 500, driver);

				try {
					Thread.sleep(2000);
					// Handle print button
					Utilities.sikuliHandle("maximize");
					Thread.sleep(3000);
					Utilities.sikuliHandle("Print_Cancel");
					System.out.println("MP against Vendor [" + documentid + "] Printed Success !!!");
				} catch (Exception NewPrint) {
					WebElement rePrint = new WebDriverWait(driver, 5)
							.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Yes']")));
					Thread.sleep(500);
					rePrint.click();
					Thread.sleep(2000);

					// Handle print button
					Utilities.sikuliHandle("maximize");
					Thread.sleep(3000);
					Utilities.sikuliHandle("Print_Cancel");
					System.out.println("MP against Vendor [" + documentid + "] Re-Printed Success !!!");
				}
			}
			Thread.sleep(2000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Thread.sleep(500);
			search.clear();
			search.sendKeys(documentid);
			Thread.sleep(500);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			// Report Email code
			String subjectLine = "MP against Vendor Cheque functionality " + documentid;
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);
			Thread.sleep(2000);

			WebElement closeBtn = new WebDriverWait(driver, 20)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("clsRpt"))));
			closeBtn.click();

		} catch (Exception EX) {
			System.out.println("MakePayment_VendorAdvance [" + documentid + "] Bank_Cheque NOT Printed  !!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// * * * * * * * EXPORT Make Payment * * * * * *

	public static void Export_MakePayment(WebDriver driver) throws InterruptedException, AWTException, IOException {
		String BtnName2 = "Export";
		Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");
		String reportIcon = pro.getProperty("PaymentMadeReport");
		String waitForQuickSearch = pro.getProperty("DocumentCheckBox");
		String closeReportPage = pro.getProperty("clsRpt");
		String ModuleName = "Make Payment";

		ExportFunction.Export_TodayDate(BtnName2, reportIcon, waitForQuickSearch, closeReportPage, ModuleName, driver);

	}

	public static void create_MakePayment_Vendor2(String documentid, String AdvanceAmt, String InvoiceAmt,
			String CreditNoteAmt, String GLAmt, String productid, String vendorid, WebDriver driver, String BankName,
			String DocumentCurrency, String ForexCurrecny1) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			WebDriverWait wait = new WebDriverWait(driver, 45);

			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("MakePaymentIcon"))));
			element.click();
			Thread.sleep(2000);// pro

			element = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("MakePaymentAgainstVendorCheck"))));
			element.click();
			Thread.sleep(1000);

			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
			element.click();
			Thread.sleep(1000);

			Utilities.enterTextInDropDown("NA", pro.getProperty("SequenceFormat"), driver);

			Utilities.enterTextInDropDown("MPvend" + documentid, pro.getProperty("DocumentNo"), driver);

			Utilities.enterTextandSelect(vendorid, pro.getProperty("vendorID"), driver);
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
			String LoadedCurrency = driver.findElement(By.xpath("//div[@class='x-combo-list-item  x-combo-selected']"))
					.getText();
			// Utilities.waitandSendkey(pro.getProperty("DocumentCurrency"),
			// driver, DocumentCurrency);
			// Utilities.Enter();
			// Utilities.waitandClick(pro.getProperty("Memo"), driver);

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
				Utilities.waitandSendkey(pro.getProperty("ChequeSequence"), driver, "NA");
				Utilities.Tab();
				Utilities.waitandSendkey(pro.getProperty("ChequeNo"), driver, "CNo" + documentid);
				Utilities.Tab();
			}

			WebElement memo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Memo"))));
			memo.click();
			Thread.sleep(1000);

			List<WebElement> header = driver.findElements(By.xpath(
					"//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"));
			int totalHeadCnt = header.size();

			int indexOfDocType = 0, indexOfDocNo = 0, indexOfAmount = 0;
			Robot r = new Robot();
			for (int i = 1; i <= totalHeadCnt; i++) {
				String headName = driver.findElement(By
						.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
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

			// System.out.println(indexOfDocType);System.out.println(indexOfDocNo);System.out.println(indexOfAmount);
			// add code for Advance-Deposite

			WebElement docTyp = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
							+ indexOfDocType + "]/div")));
			docTyp.click();
			Thread.sleep(2000);
			Utilities.sendText("Advanced / Deposit");
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			memo.click();
			Thread.sleep(1500);

			WebElement ammount = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
							+ indexOfAmount + "]/div/div")));
			ammount.click();
			Thread.sleep(1500);
			Utilities.sendText(AdvanceAmt);
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			memo.click();
			Thread.sleep(1500);

			// Add code for Invoice
			docTyp = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[2]/table/tbody/tr/td["
							+ indexOfDocType + "]/div")));
			docTyp.click();
			Thread.sleep(2000);
			Utilities.sendText("Invoice");
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			memo.click();
			Thread.sleep(1500);

			WebElement dcoNumber = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[2]/table/tbody/tr/td["
							+ indexOfDocNo + "]/div")));
			dcoNumber.click();
			Thread.sleep(2000);

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearchInvoice"))));
			driver.findElement(By.xpath(pro.getProperty("QuickSearchInvoice"))).sendKeys(documentid);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(4000);// pro

			// enter vendor
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("SelectInvoice"))));
			WebElement cust = driver.findElement(By.xpath(pro.getProperty("SelectInvoice")));
			Thread.sleep(1000);
			cust.click();
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("SelectInvoice2"))));
			WebElement cust1 = driver.findElement(By.xpath(pro.getProperty("SelectInvoice2")));
			Thread.sleep(1000);
			cust1.click();
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
			driver.findElement(By.xpath(pro.getProperty("SubmitButton1"))).click();
			Thread.sleep(2000);
			memo.click();
			Thread.sleep(1500);

			ammount = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[2]/table/tbody/tr/td["
							+ indexOfAmount + "]/div/div")));
			ammount.click();
			Thread.sleep(1500);
			Utilities.sendText(InvoiceAmt);
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			memo.click();
			Thread.sleep(1500);

			ammount = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[3]/table/tbody/tr/td["
							+ indexOfAmount + "]/div/div")));
			ammount.click();
			Thread.sleep(1500);
			Utilities.sendText(InvoiceAmt);
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			memo.click();
			Thread.sleep(1500);

			// Add code for Credit Note
			docTyp = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[4]/table/tbody/tr/td["
							+ indexOfDocType + "]/div")));
			docTyp.click();
			Thread.sleep(2000);
			Utilities.sendText("Credit Note");
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			memo.click();
			Thread.sleep(1500);

			dcoNumber = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[4]/table/tbody/tr/td["
							+ indexOfDocNo + "]/div")));
			dcoNumber.click();
			Thread.sleep(2000);

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearchCreditNote"))));
			driver.findElement(By.xpath(pro.getProperty("QuickSearchCreditNote"))).sendKeys(documentid);
			Thread.sleep(4000);// pro

			// enter vendor
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("SelectCreditNote"))));
			cust = driver.findElement(By.xpath(pro.getProperty("SelectCreditNote")));
			cust.click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
			driver.findElement(By.xpath(pro.getProperty("SubmitButton1"))).click();
			Thread.sleep(2000);
			memo.click();
			Thread.sleep(1000);

			ammount = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[4]/table/tbody/tr/td["
							+ indexOfAmount + "]/div/div")));
			ammount.click();
			Thread.sleep(1500);
			Utilities.sendText(CreditNoteAmt);
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			memo.click();
			Thread.sleep(1500);

			ammount = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[5]/table/tbody/tr/td["
							+ indexOfAmount + "]/div/div")));
			ammount.click();
			Thread.sleep(1500);
			Utilities.sendText(CreditNoteAmt);
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			memo.click();
			Thread.sleep(1500);

			for (int J = 6; J <= 7; J++) {

				// Add code for G L
				docTyp = wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div["
								+ J + "]/table/tbody/tr/td[" + indexOfDocType + "]/div")));
				docTyp.click();
				Thread.sleep(2000);
				Utilities.sendText("General Ledger Code");
				Thread.sleep(1000);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(1000);
				memo.click();
				Thread.sleep(1500);

				dcoNumber = wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div["
								+ J + "]/table/tbody/tr/td[" + indexOfDocNo + "]/div")));
				dcoNumber.click();
				Thread.sleep(2000);

				WebElement quikSearch = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//input[@id='quickSearchaccountinfowindow']")));
				quikSearch.click();
				quikSearch.sendKeys("Utilities");
				Thread.sleep(2000);

				String checkBoxXpath = "//div[@id='centerpanaccountinfowindow']/div/div[2]/div/div/div/div/div/div[2]/div/div[1]/table/tbody/tr/td[1]/div/div";

				Utilities.clickCheckBox(checkBoxXpath, "check", driver);
				Thread.sleep(2000);

				new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
				driver.findElement(By.xpath(pro.getProperty("SubmitButton1"))).click();
				Thread.sleep(2000);
				memo.click();
				Thread.sleep(1000);

				ammount = wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div["
								+ J + "]/table/tbody/tr/td[" + indexOfAmount + "]/div/div")));
				ammount.click();
				Thread.sleep(1500);
				Utilities.sendText(GLAmt);
				Thread.sleep(2000);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(1000);
				memo.click();
				Thread.sleep(1000);
			}
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SaveButton"))));
			driver.findElement(By.xpath(pro.getProperty("SaveButton"))).click();

			Thread.sleep(1000);
			Utilities.clickButton("Yes", 500, driver);
			try {
				Utilities.clickButton("Yes", 500, driver);
			} catch (Exception Ex) {

			}
			Thread.sleep(1000);
			Utilities.clickButton("OK", 500, driver);

			// Email code
			String subjectLine = "Make Payment Against Vendor - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);

			Thread.sleep(1500);
			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseMakePayment"))));
			driver.findElement(By.xpath(pro.getProperty("CloseMakePayment"))).click();

			System.out.println("MP against Vendor is Pass for doc : -> " + documentid);

		} catch (Exception EX) {
			System.out.println("MP against Vendor>>>>>FAIL<<<<<< doc : - " + documentid);
			Utilities.handleError(EX, driver);
		}
	}
}
