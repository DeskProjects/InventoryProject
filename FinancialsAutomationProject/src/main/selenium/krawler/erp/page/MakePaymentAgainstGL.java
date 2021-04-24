package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakePaymentAgainstGL {

	// ****************************** create makePayment_GL
	// ***********************
	public static void create_makePayment_GL(String documentid, String productid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstGL.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("AgainstGL"), driver);
			Utilities.clickButton("Submit", 1000, driver);
			Thread.sleep(2000);
			Utilities.enterTextandSelect("NA", pro.getProperty("SeqFormat"), driver);
			Utilities.enterTextNormalBox("MPaGL" + documentid, pro.getProperty("paymentNumber"), driver);

			// select 1st paid to user
			Utilities.HoverandClick("//*[@id='paidtoundefinedpaymentwindow']/following::img[1]", driver);
			Utilities.HoverandClick(
					"//*[contains(@style,'visible') and @class='x-layer x-combo-list ']/div/div[2]/table/tbody/tr/td[1]",
					driver);

			int headerSize = Utilities.totalSize("//*[text()='Document Type']/ancestor::tr/td/div", driver);
			int indOfdocType = 0, indOfdocNum = 0, indOfAmount = 0;

			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver
						.findElement(By.xpath("//*[text()='Document Type']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headerName.equalsIgnoreCase("Document Type")) {
					indOfdocType = i;
				} else if (headerName.equalsIgnoreCase("Account")) {
					indOfdocNum = i;
				} else if (headerName.equalsIgnoreCase("Enter Amount")) {
					indOfAmount = i;
				}
			}

			// Enter account
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfdocNum + "]/div",
					driver);
			// search account
			Utilities.enterTextInDropDown("COA" + documentid, pro.getProperty("accQuicksearch"), driver);
			Utilities.Enter();
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("accSelect"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("Submitbtn"), driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			// Enter Amount
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfAmount + "]/div",
					driver);
			Utilities.enter_LinelabelAmount("100", driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			// click on Save
			Utilities.clickButton("Save", 1000, driver);
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
				okToPrint.click();
				System.out.println("MakePaym GL [ MPaGL" + documentid + " ] successfully created !!!!");
			}
			Thread.sleep(2000);

			// Email code
			String subjectLine = "MP against GL - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);

			Utilities.click_element(pro.getProperty("CloseTabBtn"), driver);
		} catch (Exception EX) {
			System.out.println("MakePaym GL [" + documentid + "]  UN-successfully !!!!");
			Utilities.handleError(EX, driver);
		}
	}// create

	// ************************************** Verify
	// *****************************************

	public static void verify_paymentMade_GL(String documentID, String accountType, String PayMethod, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstGL.properties");
			// documentID= "MPaGL"+documentID;
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PaymentMadeReport"))));
			driver.findElement(By.xpath(pro.getProperty("PaymentMadeReport"))).click();
			Thread.sleep(3000);// pro

			// bfore search save time
			WebElement ready = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//div[@id='gridmsg23paymentReport']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td[1]")));
			if (ready.isEnabled()) {

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
						assertEquals(accountType,
								driver.findElement(By
										.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
												+ i + "]/div"))
										.getText());
						System.out.println("Vendor is verified");
					}

					else if (header.equals("Payment Method")) {
						assertEquals(PayMethod,
								driver.findElement(By
										.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
												+ i + "]/div"))
										.getText());
						System.out.println("payment Method verified");
					}
				}
			}
			System.out.println("Make Payment against GL [" + documentID + "] verified Successfully");
			// Click on Close
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ClosePaymentMadeReport"))));
			driver.findElement(By.xpath(pro.getProperty("ClosePaymentMadeReport"))).click();
			Thread.sleep(500);
		} catch (Exception EX) {
			System.out.println("Make Payment against GL [" + documentID + "] is NOT VERIFIED !!!!!!");
			Utilities.handleError(EX, driver);
		}
	}// verify

	// ********************************** Copy-Edit-Delete
	// *************************
	public static void EmailCopyEditDelete_MakePayment_VendorAdvance(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		// Future Update code for multiple accounts
	}

	// ********************* PrintCheque *************************
	public static void create_makePayment_GL_PrintCheque(String documentid, String productid, String customerid,
			String payMethod, String chqNumber, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstGL.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("AgainstGL"), driver);
			Utilities.clickButton("Submit", 1000, driver);
			Thread.sleep(2000);
			Utilities.enterTextandSelect("NA", pro.getProperty("SeqFormat"), driver);
			Utilities.enterTextNormalBox("MPchqGL" + documentid, pro.getProperty("paymentNumber"), driver);

			// select 1st paid to user
			Utilities.HoverandClick("//*[@id='paidtoundefinedpaymentwindow']/following::img[1]", driver);
			Utilities.HoverandClick(
					"//*[contains(@style,'visible') and @class='x-layer x-combo-list ']/div/div[2]/table/tbody/tr/td[1]",
					driver);

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
				} else if (headerName.equalsIgnoreCase("Account")) {
					indOfdocNum = i;
				} else if (headerName.equalsIgnoreCase("Enter Amount")) {
					indOfAmount = i;
				}
			}

			// Enter account
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfdocNum + "]/div",
					driver);
			// search account
			Utilities.enterTextInDropDown("COA" + documentid, pro.getProperty("accQuicksearch"), driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("accSelect"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("Submitbtn"), driver);
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
				System.out.println("MakePaym GL [" + documentid + "] Bank_ChequePrinted successfully !!!!");
			}
			Thread.sleep(2000);

			// Email code
			String subjectLine = "MP against GL Cheque Print - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);

			Utilities.click_element(pro.getProperty("CloseTabBtn"), driver);
		} catch (Exception EX) {
			System.out.println("MakePaym GL [" + documentid + "] Bank_ChequePrinted UN-successfully !!!!");
			Utilities.handleError(EX, driver);
		}
	}
	// *****************************print cheque against vendor
	// *********************************************

	public static void print_MakePayment_GLReport(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "MPchqGL" + documentid;
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
				Thread.sleep(1100);
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
					System.out.println("MP against GL [" + documentid + "] Re-Printed Success !!!");
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
			String subjectLine = "MP against GL Cheque functionality " + documentid;
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);
			Thread.sleep(2000);

			WebElement closeBtn = new WebDriverWait(driver, 20)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("clsRpt"))));
			closeBtn.click();

		} catch (Exception EX) {
			System.out.println("MakePayment_GL [" + documentid + "] Bank_Cheque NOT Printed  !!!!");
			Utilities.handleError(EX, driver);
		}
	}

	/**************************** Payment with Multiple GL ****************/

	public static void create_makePayment_MutilpleGL(String documentid, String productid, String accType,
			WebDriver driver, String BankName, String DocumentCurrency, String ForexCurrecny1)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "MPaGL" + documentid;
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstGL.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("MakePaymentIcon"))));
			driver.findElement(By.xpath(pro.getProperty("MakePaymentIcon"))).click();
			Thread.sleep(5000);// pro

			new WebDriverWait(driver, 40)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AgainstGL"))));
			driver.findElement(By.xpath(pro.getProperty("AgainstGL"))).click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Submit']")));
			driver.findElement(By.xpath("//button[text()='Submit']")).click();
			Thread.sleep(1000);

			// sequen format document no.
			WebElement squenceRP = driver.findElement(By.xpath(pro.getProperty("SeqFormat")));
			squenceRP.clear();
			squenceRP.sendKeys("NA");
			Thread.sleep(1000);// pro
			squenceRP.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("paymentNumber"))));
			driver.findElement(By.xpath(pro.getProperty("paymentNumber"))).sendKeys(documentid);

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

			Utilities.enterTextandSelect(DocumentCurrency, pro.getProperty("PaymentCurrency"), driver);

			if (!LoadedCurrency.equals(DocumentCurrency)) {
				try {
					Utilities.clickButton("Yes", 1000, driver);
				} catch (Exception Ex) {
					// System.out.println("Document Currecny is not Changed");
				}
			}

			// WebElement Che =
			// driver.findElement(By.xpath(pro.getProperty("ChequeSeq")));
			// Che.click();
			// Che.clear();
			// Che.sendKeys("NA");
			if (!BankName.equals("Cash")) {
				Utilities.waitandSendkey(pro.getProperty("ChequeSequence"), driver, "NA");
				Utilities.Enter();
				Utilities.waitandSendkey(pro.getProperty("ChequeNo"), driver, "CNo" + documentid);
				Utilities.Tab();
			}

			Robot r = new Robot();

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Account"))

					for (int J = 1; J <= 2; J++) {
						{
							driver.findElement(By
									.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div[2]/div/div["
											+ J + "]/table/tbody/tr/td[" + i + "]/div"))
									.click();
							Thread.sleep(2000);
							new WebDriverWait(driver, 30).until(ExpectedConditions
									.elementToBeClickable(By.xpath(pro.getProperty("accQuicksearch"))));
							driver.findElement(By.xpath(pro.getProperty("accQuicksearch"))).sendKeys(accType);
							Thread.sleep(5000);

							WebElement selectAc = new WebDriverWait(driver, 30).until(
									ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("accSelect"))));
							if (selectAc.isEnabled()) {
								selectAc.click();
							}

							new WebDriverWait(driver, 30).until(
									ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Submitbtn"))));
							driver.findElement(By.xpath(pro.getProperty("Submitbtn"))).click();
						}
					}
			}
			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Enter Amount")) {
					for (int l = 1; l <= 2; l++) {
						driver.findElement(By
								.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div[2]/div/div["
										+ l + "]/table/tbody/tr/td[" + i + "]/div"))
								.click();
						Thread.sleep(2000);
						Utilities.sendText("100");
					}
				}
			}

			Thread.sleep(1000);
			WebElement savebtn = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("savebtn"))));
			if (savebtn.isEnabled()) {
				savebtn.click();
			}

			Utilities.clickButton("Yes", 1500, driver);

			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}

			} catch (Exception Ex) {

			}

			Utilities.clickButton("OK", 1500, driver);

			// Email code
			// String subjectLine="Make Payment against GL "+documentid;
			// EmailFunctionality.email_AfterSave(documentid,subjectLine,driver);
			// Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseTabBtn")))).click();

			System.out.println("Make Payment against GL [" + documentid + "] created Successfully");

		} catch (Exception EX) {
			System.out.println("Make Payment against GL [" + documentid + "] is FAILED !!!!!!");
			Utilities.handleError(EX, driver);
		}
	}
}