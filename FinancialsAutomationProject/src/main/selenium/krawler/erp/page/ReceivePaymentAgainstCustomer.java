package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReceivePaymentAgainstCustomer {

	// ****** all 4 in 1 *******

	public static void create_ReceivePaymentAgainstCustomer(String documentid, String AdvanceAmt, String InvoiceAmt,
			String CreditNoteAmt, String GLAmt, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");
			WebDriverWait wait = new WebDriverWait(driver, 45);

			Utilities.waitandClick(pro.getProperty("ReceivePaymentIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("ReceivePaymentAgainstCustomerCheck"), driver);
			Utilities.HoverandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox("RPcust" + documentid, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect(customerid, pro.getProperty("customerID"), driver);
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
			Utilities.enterTextandSelect("Advanced / Deposit", "//*[@name='type']/following::input[1]", driver);
			memo.click();
			// Enter Amount
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfAmount + "]/div",
					driver);
			Utilities.enter_LinelabelAmount(AdvanceAmt, driver);
			memo.click();

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
			Utilities.enterTextNormalBox("SI" + documentid, pro.getProperty("QuickSearchInvoice"), driver);
			Utilities.click_element("//button[text()='Fetch']", driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("SelectInvoice"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("SubmitButton1"), driver);
			// Enter Amount
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfAmount + "]/div",
					driver);
			Utilities.enter_LinelabelAmount(InvoiceAmt, driver);
			memo.click();

			// Link Note
			// ----------------------------------------------------------------------------
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[3]/table/tbody/tr/td["
							+ indOfdocType + "]/div",
					driver);
			Utilities.enterTextandSelect("Debit Note", "//*[@name='type']/following::input[1]", driver);
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[3]/table/tbody/tr/td["
							+ indOfdocNum + "]/div",
					driver);
			// search Note
			Utilities.enterTextNormalBox("Cus" + documentid, pro.getProperty("QuickSearchDebitNote"), driver);
			Utilities.click_element("//button[text()='Fetch']", driver);
			Utilities.clickCheckBox(pro.getProperty("SelectDebitNote"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("SubmitButton1"), driver);
			// Enter Amount
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[3]/table/tbody/tr/td["
							+ indOfAmount + "]/div",
					driver);
			Utilities.enter_LinelabelAmount(CreditNoteAmt, driver);
			memo.click();

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
				System.out.println("Receive Payment Against Customer is Pass for doc : -> " + documentid);
			}

			// Email code
			String subjectLine = "Receive Payment Against Customer - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);

			Utilities.HoverandClick(pro.getProperty("CloseMakePayment"), driver);

		} catch (Exception EX) {
			System.out.println("Receive Payment Against Customer >>>>>FAIL<<<<<< doc : - " + documentid);
			Utilities.handleError(EX, driver);
		}
	}

	// ************************************************************Verification*********************************************************************

	public static void verify_paymentMade(String documentid, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentReport"))));
			driver.findElement(By.xpath(pro.getProperty("ReceivePaymentReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentid);
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
					assertEquals(documentid,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("payment no verified");
				}

				else if (header.equals("Name")) {
					assertEquals(customerid + "Name",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("Vendor");
				}

				else if (header.equals("Payment Method")) {
					assertEquals("Cash",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("payment Method verified");
				}

				// else if(header.equals("Memo"))
				// {assertEquals("memo"+documentid,
				// driver.findElement(By.xpath(".//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["+i+"]/div")).getText());}

			}
			Utilities.zoomIn();
			System.out.println("********** Receive Payment against CUSTOMER report successfully verified for :["
					+ documentid + "] *********************");

			// Click on Close
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ClosePaymentMadeReport"))));
			driver.findElement(By.xpath(pro.getProperty("ClosePaymentMadeReport"))).click();
			Thread.sleep(2000);

		} catch (Exception EX) {
			System.out.println("********** Receive Payment against CUSTOMER report NOT verified for :[" + documentid
					+ "] check LOG !!! *********************");
			Utilities.handleError(EX, driver);
		}
	}

	// Common Copy-Edit-Delete Flow
	public static void EmailCopyEditDelete_ReceivePayment(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");
			Utilities.waitandClick(pro.getProperty("ReceivePaymentReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(1000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(500);
			// Report Email code
			String subjectLine = "Recieve Payment Against Customer Advance - testsmoke - " + documentid;
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(500);
			String CopyReceivePaymentAgainst = "Copy" + documentid;

			// Copy
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("copyReceivePaymentAgainstCustomerAdvanceButton"), driver);
			String checkFormfullyLoad = "//*[text()='Action']/ancestor::div[3]/following::div[1]/div/div[2]/table/tbody/tr/td[4]";
			Utilities.clickCheckBox(checkFormfullyLoad, "uncheck", driver);
			Utilities.enterTextNormalBox(CopyReceivePaymentAgainst,
					pro.getProperty("copyReceivePaymentAgainstCustomerAdvanceId"), driver);
			Utilities.clickButton("Save", 1000, driver);
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
			Utilities.HoverandClick(pro.getProperty("CopyReceivePaymentAgainstCustomerAdvanceClose"), driver);
			System.out.println("Receive Payment Against [" + documentid + "] COPY test case Pass Successfully");

			// Edit
			Utilities.enterTextInDropDown(CopyReceivePaymentAgainst, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("EditReceivePaymentAgainstCustomerAdvanceButton"), driver);
			Utilities.clickCheckBox(checkFormfullyLoad, "uncheck", driver);
			Utilities.enterTextNormalBox("*** Performing Edit test case for [" + documentid + "]",
					pro.getProperty("ReceivePaymentAgainstCustomerAdvanceMemo"), driver);
			Utilities.clickButton("Save", 1000, driver);
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
			Utilities.HoverandClick(pro.getProperty("EditReceivePaymentAgainstCustomerAdvanceClose"), driver);
			Utilities.enterTextInDropDown(CopyReceivePaymentAgainst, pro.getProperty("QuickSearch"), driver);
			System.out.println("Receive Payment Against [" + documentid + "] EDIT test case Pass Successfully");

			// Delete
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);
			Utilities.HoverandClick(pro.getProperty("deleteReceivePaymentAgainstCustomerAdvanceButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteReceivePaymentAgainstCustomerAdvancePermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);

			// Verify Copy - Edit - Delete flow
			Utilities.enterTextInDropDown(CopyReceivePaymentAgainst, pro.getProperty("QuickSearch"), driver);
			if (driver.findElement(By.xpath("//a[contains(text(),'Get Started by adding a Receipt')]")).isDisplayed()) {
				System.out.println("Receive Payment Against [" + documentid + "] DELETE test case Pass Successfully");
			}

			Utilities.HoverandClick("//*[@id='as__receiptReport']/a[1]", driver); // close
																					// Foem

		} catch (Exception EX) {
			System.out.println("Receive Payment Against [" + documentid + "] FLOW Copy-Edit-Delete FAILED !!!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	/**********************************
	 * RP with Multiple Document
	 *********************/

	public static void create_ReceivePaymentAgainstCustomerMultiple(String documentid, String AdvanceAmt,
			String InvoiceAmt, String CreditNoteAmt, String GLAmt, String productid, String customerid,
			WebDriver driver, String BankName, String DocumentCurrency, String ForexCurrecny1)
			throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			WebDriverWait wait = new WebDriverWait(driver, 45);

			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentIcon"))));
			element.click();
			Thread.sleep(2000);// pro

			element = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentAgainstCustomerCheck"))));
			element.click();
			Thread.sleep(1000);

			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
			element.click();
			Thread.sleep(1000);

			Utilities.enterTextInDropDown("NA", pro.getProperty("SequenceFormat"), driver);

			Utilities.enterTextInDropDown("RPcustMul" + documentid, pro.getProperty("DocumentNo"), driver);

			Utilities.enterTextInDropDown(customerid, pro.getProperty("customerID"), driver);
			Thread.sleep(1000);
			Utilities.Enter();

			WebElement memo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Memo"))));
			memo.click();
			Thread.sleep(1000);

			// new WebDriverWait(driver, 30).until(
			// ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentNo"))));
			// driver.findElement(By.xpath(pro.getProperty("DocumentNo"))).sendKeys(documentid);

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

				Utilities.waitandSendkey(pro.getProperty("ChequeNo"), driver, "CNo" + documentid);
				Utilities.Tab();

				Utilities.waitandClick(pro.getProperty("CheBankNameD"), driver);
				Robot R = new Robot();
				R.keyPress(KeyEvent.VK_DOWN);
				R.keyRelease(KeyEvent.VK_DOWN);
				R.keyPress(KeyEvent.VK_DOWN);
				Utilities.Enter();

			}

			List<WebElement> header = driver.findElements(By.xpath(
					"//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"));
			int totalHeadCnt = header.size();

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

			// System.out.println(indexOfDocType);System.out.println(indexOfDocNo);System.out.println(indexOfAmount);
			// add code for Advance-Deposite

			WebElement docTyp = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
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
					.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
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
					.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[2]/table/tbody/tr/td["
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
					.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[2]/table/tbody/tr/td["
							+ indexOfDocNo + "]/div")));
			dcoNumber.click();
			Thread.sleep(2000);

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearchInvoice"))));
			driver.findElement(By.xpath(pro.getProperty("QuickSearchInvoice"))).sendKeys("SI" + documentid);
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
			WebElement cust2 = driver.findElement(By.xpath(pro.getProperty("SelectInvoice2")));
			Thread.sleep(1000);
			cust2.click();
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
			driver.findElement(By.xpath(pro.getProperty("SubmitButton1"))).click();
			Thread.sleep(2000);
			memo.click();
			Thread.sleep(1500);

			for (int j = 2; j <= 3; j++) {
				ammount = wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[" + indexOfAmount + "]/div/div")));
				ammount.click();
				Thread.sleep(1500);
				Utilities.sendText(InvoiceAmt);
				Thread.sleep(2000);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(1000);
				memo.click();
				Thread.sleep(1500);
			}
			// Add code for Debit Note
			docTyp = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[4]/table/tbody/tr/td["
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

			dcoNumber = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[4]/table/tbody/tr/td["
							+ indexOfDocNo + "]/div")));
			dcoNumber.click();
			Thread.sleep(2000);

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearchDebitNote"))));
			driver.findElement(By.xpath(pro.getProperty("QuickSearchDebitNote"))).sendKeys(documentid);
			Thread.sleep(4000);// pro

			// enter vendor
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("SelectDebitNote"))));
			cust = driver.findElement(By.xpath(pro.getProperty("SelectDebitNote")));
			cust.click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("SelectDebitNote2"))));
			cust = driver.findElement(By.xpath(pro.getProperty("SelectDebitNote2")));
			cust.click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
			driver.findElement(By.xpath(pro.getProperty("SubmitButton1"))).click();
			Thread.sleep(2000);
			memo.click();
			Thread.sleep(1000);

			for (int k = 4; k <= 5; k++) {
				ammount = wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div["
								+ k + "]/table/tbody/tr/td[" + indexOfAmount + "]/div/div")));
				ammount.click();
				Thread.sleep(1500);
				Utilities.sendText(CreditNoteAmt);
				Thread.sleep(2000);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(1000);
				memo.click();
				Thread.sleep(1500);
			}
			// Add code for G L

			for (int l = 6; l <= 7; l++) {
				docTyp = wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div["
								+ l + "]/table/tbody/tr/td[" + indexOfDocType + "]/div")));
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
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div["
								+ l + "]/table/tbody/tr/td[" + indexOfDocNo + "]/div")));
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
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div["
								+ l + "]/table/tbody/tr/td[" + indexOfAmount + "]/div/div")));
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

			Thread.sleep(500);
			Utilities.clickButton("Yes", 500, driver);

			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}

			} catch (Exception Ex) {

			}
			Thread.sleep(500);
			Utilities.clickButton("OK", 500, driver);

			// Email code
			// //String subjectLine="Receive Payment Against Customer -
			// testsmoke - "+documentid;
			// EmailFunctionality.email_AfterSave(documentid,subjectLine,driver);

			Thread.sleep(1500);
			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseMakePayment"))));
			driver.findElement(By.xpath(pro.getProperty("CloseMakePayment"))).click();

			System.out.println("Receive Payment Against Customer is Pass for doc : -> " + documentid);

		} catch (Exception EX) {
			System.out.println("Receive Payment Against Customer >>>>>FAIL<<<<<< doc : - " + documentid);
			EX.printStackTrace();
			// Utilities.handleError(EX, driver);
		}
	}

}
