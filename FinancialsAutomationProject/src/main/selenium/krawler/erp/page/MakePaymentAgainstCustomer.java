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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MakePaymentAgainstCustomer {
	// Make Paymnet Against Customer
	public static void MakePayment_Customer(String documentid, String customerid, String RefunDepositAmt,
			String CreditNoteAmt, WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("MakePaymentAgainstCustomer"), driver);
			Utilities.HoverandClick(pro.getProperty("SubmitButton1"), driver);
			Thread.sleep(2000);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox("MPCust" + documentid, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect(customerid, pro.getProperty("vendorID"), driver);
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
			Utilities.enterTextandSelect("Refund / Deposit", "//*[@name='type']/following::input[1]", driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			// Enter Amount
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfAmount + "]/div",
					driver);
			Utilities.enter_LinelabelAmount(RefunDepositAmt, driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			// Link Note
			// -------------------------------------------------------------------------------
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfdocType + "]/div",
					driver);
			Utilities.enterTextandSelect("Credit Note", "//*[@name='type']/following::input[1]", driver);
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfdocNum + "]/div",
					driver);
			// search Note
			Utilities.enterTextNormalBox("CnOthr" + documentid, pro.getProperty("QuickSearchCreditNote"), driver);
			Utilities.click_element("//button[text()='Fetch']", driver);
			Utilities.clickCheckBox(pro.getProperty("firstCreditNote"), "check", driver);
			Utilities.clickButton("Submit", 1000, driver);
			// Enter Amount
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfAmount + "]/div",
					driver);
			Utilities.enter_LinelabelAmount(CreditNoteAmt, driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

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
				System.out.println(
						"*** Make Payment against Customer is Pass for doc : -> [ MPCust" + documentid + "] ******");
			}
			Thread.sleep(2000);

			// Email code
			String subjectLine = "Make Payment Against Customer - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);

			Utilities.click_element(pro.getProperty("CloseMakePayment"), driver); // close
																					// form
		} catch (Exception Ex) {
			System.out.println(
					"!!!!! Make Payment against Customer is FAILEDD for doc : -> [ MPCust" + documentid + "] !!!!!");
			Utilities.handleError(Ex, driver);
		}
	}

	// ********************* PrintCheque *************************
	public static void create_MakePayment_Customer_Bank_ChequePrint(String documentid, String productid,
			String customerid, String payMethod, String chqNumber, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "MPchqCus" + documentid;
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("MakePaymentAgainstCustomer"), driver);
			Utilities.HoverandClick(pro.getProperty("SubmitButton1"), driver);
			Thread.sleep(2000);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect(customerid, pro.getProperty("vendorID"), driver);
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
			Utilities.enterTextandSelect("Refund / Deposit", "//*[@name='type']/following::input[1]", driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			// Enter Amount
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfAmount + "]/div",
					driver);
			Utilities.enter_LinelabelAmount("100", driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			// click on Save and Print Cheque
			Utilities.clickButton("Save and Print Cheque", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);

			WebElement okToPrint = null;
			try {
				okToPrint = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
			} catch (Exception E) {
				Utilities.clickButton("Yes", 100, driver); // continue Extra Yes
			}

			okToPrint = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
			if (okToPrint.isDisplayed()) {
				Thread.sleep(800);
				// Handle print button
				Thread.sleep(2000);
				Utilities.sikuliHandle("Print_Cancel");
				okToPrint.click();
				System.out.println("MakePaym Customer [" + documentid + "] Bank_ChequePrinted successfully !!!!");
			}
			Thread.sleep(2000);

			// Email code
			String subjectLine = "MP against Customer Cheque Print - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);

			Utilities.click_element(pro.getProperty("CloseMakePayment"), driver);
		} catch (Exception EX) {
			System.out.println("MakePaym Customer [" + documentid + "] Bank_ChequePrinted UN-successfully !!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// *****************************print cheque against vendor
	// *********************************************

	public static void print_MakePayment_CustomerReport(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "MPchqCus" + documentid;
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
					System.out.println("MP against Customer [" + documentid + "] Re-Printed Success !!!");
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
			String subjectLine = "MP against Customer Cheque functionality " + documentid;
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);
			Thread.sleep(2000);

			WebElement closeBtn = new WebDriverWait(driver, 20)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("clsRpt"))));
			closeBtn.click();

		} catch (Exception EX) {
			System.out.println("MakePayment_Customer [" + documentid + "] Bank_Cheque NOT Printed  !!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ************************************************************Verification*********************************************************************

	public static void verify_paymentMade(String documentID, String customerid, String PaymentMethod, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PaymentMadeReport"))));
			driver.findElement(By.xpath(pro.getProperty("PaymentMadeReport"))).click();
			Thread.sleep(5000);// pro

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
					System.out.println("payment no verified");
				}

				else if (header.equals("Name")) {
					assertEquals(customerid + "Name",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("customer is verified");
				}

				else if (header.equals("Payment Method")) {
					assertEquals(PaymentMethod,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("payment Method verified");
				}
			}

			System.out.println("Make Paymnet with Bank Payment method [" + documentID + "] is Verified !!!");

			// Click on Close
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ClosePaymentMadeReport"))));
			driver.findElement(By.xpath(pro.getProperty("ClosePaymentMadeReport"))).click();
			Thread.sleep(500);

		} catch (Exception EX) {
			System.out.println("Make Paymnet with Bank Payment method [" + documentID + "] is NOT Verified !!!");
			Utilities.handleError(EX, driver);
		}

	}

	// ***************************COpyEditDelete*******************************

	public static void EmailCopyEditDelete_MakePayment_GL(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			// documentid="MPcusRfnd" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";

			Utilities.waitandClick(pro.getProperty("PaymentMadeReport"), driver);
			Utilities.isElementGone(xpathOfLoading, 120, driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.isLoadingDisappear(60, driver);

			// Report Email code
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Check", driver);
			String subjectLine = "Make Payment Against Customer : " + documentid;
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);
			Utilities.isLoadingDisappear(60, driver);

			// Copy
			String CopyMakePaymentVendorAdvanceID = "Copy" + documentid;
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.isLoadingDisappear(60, driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Check", driver);
			Utilities.HoverandClick(pro.getProperty("copyMakePaymentVendorAdvanceButton"), driver);
			Thread.sleep(2000);
			Utilities.enterTextNormalBox(CopyMakePaymentVendorAdvanceID,
					pro.getProperty("copyMakePaymentVendorAdvanceId"), driver);
			Utilities.click_element(pro.getProperty("saveCopiedMakePaymentVendorAdvanceId"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("CopyMakePaymentVendorAdvanceClose"), driver);
			System.out.println("**** COPY test case success for MP against GL ****");

			// Edit
			Utilities.enterTextNormalBox(CopyMakePaymentVendorAdvanceID, pro.getProperty("QuickSearch"), driver);
			Utilities.isLoadingDisappear(60, driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Check", driver);
			Utilities.HoverandClick(pro.getProperty("EditMakePaymentVendorAdvanceButton"), driver);
			Thread.sleep(2000);
			Utilities.enterTextNormalBox("EDIT test case Make Payment Vendor Against GL",
					pro.getProperty("MakePaymentVendorAdvanceMemo"), driver);
			Utilities.click_element(pro.getProperty("MakePaymentVendorAdvanceEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("EditMakePaymentVendorAdvanceClose"), driver);
			System.out.println("**** EDIT test case success for MP against GL ****");

			// Delete
			Utilities.enterTextNormalBox(CopyMakePaymentVendorAdvanceID, pro.getProperty("QuickSearch"), driver);
			Utilities.isLoadingDisappear(60, driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Check", driver);
			Utilities.HoverandClick(pro.getProperty("deleteMakePaymentVendorAdvanceButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteMakePaymentVendorAdvancePermButton"), driver);

			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);
			Utilities.isLoadingDisappear(60, driver);
			Utilities.enterTextNormalBox(CopyMakePaymentVendorAdvanceID, pro.getProperty("QuickSearch"), driver);
			Utilities.isLoadingDisappear(60, driver);

			try {
				WebElement confirmation = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//*[contains(text(),'Get Started by adding a Payment')]")));
				if (confirmation.isDisplayed()) {
					System.out.println("Make Payment Against Customer " + CopyMakePaymentVendorAdvanceID
							+ "  is Deleted Successfully");
				} else {
					System.out.println("Make Payment Against Customer " + CopyMakePaymentVendorAdvanceID
							+ "  is not Deleted Successfully");
				}
			} catch (Exception exp) {
				System.out
						.println("Make Payment Against Customer " + CopyMakePaymentVendorAdvanceID + " is NOT deleted");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}

			Utilities.click_element("//li[@id='as__PaymentMainTabPanel']/a[1]", driver);
		}

		catch (Exception EX) {
			System.out.println("!!!!!!! FAILED flow of Copy-Edit-Delete fir Make Payment Against GL " + documentid
					+ " plz checl Console !!!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	/**************************
	 * MP Cust with Multiple Doc
	 **********************/
	public static void MakePayment_CustomerWithMutilple(String documentid, String customerid, String RefunDepositAmt,
			String CreditNoteAmt, WebDriver driver, String BankName, String DocumentCurrency, String ForexCurrecny1)
			throws InterruptedException, AWTException, IOException {

		try {

			// documentid ="MPCust" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 45);
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstCustomer.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("MakePaymentIcon"))));
			driver.findElement(By.xpath(pro.getProperty("MakePaymentIcon"))).click();
			Thread.sleep(3000);// pro

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("MakePaymentAgainstCustomer"))));
			driver.findElement(By.xpath(pro.getProperty("MakePaymentAgainstCustomer"))).click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
			driver.findElement(By.xpath(pro.getProperty("SubmitButton1"))).click();
			Thread.sleep(1000);

			Utilities.enterTextInDropDown("NA", pro.getProperty("SequenceFormat"), driver);

			Utilities.enterTextInDropDown("MPCust" + documentid, pro.getProperty("DocumentNo"), driver);

			WebElement vendlocaotr = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("vendorID"))));
			vendlocaotr.clear();
			Thread.sleep(500);
			vendlocaotr.sendKeys(customerid);
			Thread.sleep(2000);
			vendlocaotr.sendKeys(Keys.ENTER);
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
			String LoadedCurrency = driver.findElement(By.xpath("//div[@class='x-combo-list-item x-combo-selected']"))
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
				Utilities.waitandSendkey(pro.getProperty("ChequeSequence"), driver, "NA");
				Utilities.Tab();
				Utilities.waitandSendkey(pro.getProperty("ChequeNo"), driver, "CNo" + documentid);
				Utilities.Tab();
			}

			WebElement memo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Memo"))));
			memo.click();

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

			// add code for Refund / Deposit
			WebElement docTyp = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
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
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
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
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[2]/table/tbody/tr/td["
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

			WebElement dcoNumber = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[2]/table/tbody/tr/td["
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
						.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div["
								+ l + "]/table/tbody/tr/td[" + indexOfAmount + "]/div/div")));
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

			WebElement docTyp2 = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[4]/table/tbody/tr/td["
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
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[4]/table/tbody/tr/td["
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
						.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div["
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

			Utilities.clickButton("Save", 500, driver);
			Thread.sleep(1000);
			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);

			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}

			} catch (Exception EX) {

			}
			Utilities.clickButton("OK", 500, driver);

			// Email code
			// String subjectLine="Make Payment Against Customer - testsmoke -
			// "+documentid;
			// EmailFunctionality.email_AfterSave(documentid,subjectLine,driver);

			WebElement closeTab = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='as__paymentwindow']/a[1]")));
			Thread.sleep(1000);
			closeTab.click();

			System.out.println("MP against Customer is Pass for doc : -> " + documentid);

		}

		catch (Exception EX) {
			System.out.println("MakePayment_Customer [" + documentid + "] NOT CREATED  !!!!");
			Utilities.handleError(EX, driver);
		}

	}

}
