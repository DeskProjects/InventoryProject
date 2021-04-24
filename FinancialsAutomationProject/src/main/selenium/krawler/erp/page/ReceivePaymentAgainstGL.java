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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReceivePaymentAgainstGL {

	// ********************************************************ADVANCE*********************************************************
	public static void create_ReceivePayment_AgainstGL(String documentid, String accountname, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstGL.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("ReceivePaymentAgainstGLCheck"), driver);
			Utilities.clickButton("Submit", 1000, driver);
			Thread.sleep(2000);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox("RPGL" + documentid, pro.getProperty("DocumentNo"), driver);

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
			Utilities.enterTextInDropDown("COA" + documentid, pro.getProperty("QuickSearchGLAccount"), driver);
			Utilities.Enter();
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("SelectGLAccount"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("SubmitButton1"), driver);
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
				System.out.println(
						"Receive Payment against GL completed successfully for doc id : [" + documentid + "]***");
			}

			// Email code
			String subjectLine = "Receive Payment Against GL - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);
			Thread.sleep(1000);

			Utilities.HoverandClick(pro.getProperty("CloseMakePayment"), driver);
		} catch (Exception EX) {
			System.out.println("RP against GL NOT CREATED !!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ************************************************************Verification*********************************************************************

	public static void verify_paymentReceived(String documentID, String accountname, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentID = "RPGL" + documentID;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstGL.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentReport"))));
			driver.findElement(By.xpath(pro.getProperty("ReceivePaymentReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.clear();
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

				if (header.equals("Receipt No.")) {
					assertEquals(documentID,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("payment no verified");
				}

				else if (header.equals("Name")) {
					assertEquals(accountname,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("accountname " + accountname);
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
				// {assertEquals("memo"+documentID,
				// driver.findElement(By.xpath(".//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["+i+"]/div")).getText());}

			}
			System.out.println("RP against GL Document [" + documentID + "] verified successfully !!");
			Utilities.zoomIn();

			// Click on Close
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ClosePaymentReceiveReport"))));
			driver.findElement(By.xpath(pro.getProperty("ClosePaymentReceiveReport"))).click();
			Thread.sleep(500);

		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	public static void EmailCopyEditDelete_ReceivePayment_AgainstGL(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "RPGL" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstGL.properties");
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
			String subjectLine = "Receive Payment against GL - " + documentid;
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

			WebElement copyReceivePaymentAgainstGLButton = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("copyReceivePaymentAgainstGLButton"))));
			copyReceivePaymentAgainstGLButton.click();
			Thread.sleep(2000);

			String CopyReceivePaymentAgainstGLID = "Copy" + documentid;

			new WebDriverWait(driver, 60).until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("copyReceivePaymentAgainstGLId"))));
			driver.findElement(By.xpath(pro.getProperty("copyReceivePaymentAgainstGLId")))
					.sendKeys(CopyReceivePaymentAgainstGLID);
			Thread.sleep(1000);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("saveCopiedReceivePaymentAgainstGLId"))));
			driver.findElement(By.xpath(pro.getProperty("saveCopiedReceivePaymentAgainstGLId"))).click();

			Thread.sleep(3000);
			Robot r = new Robot();

			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(1000);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("CopyReceivePaymentAgainstGLClose"))));
				driver.findElement(By.xpath(pro.getProperty("CopyReceivePaymentAgainstGLClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys(CopyReceivePaymentAgainstGLID);
			Thread.sleep(500);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement editReceivePaymentAgainstGLButton = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("EditReceivePaymentAgainstGLButton"))));
			editReceivePaymentAgainstGLButton.click();
			Thread.sleep(3000);

			WebElement ReceivePaymentAgainstGLMemo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentAgainstGLMemo"))));
			ReceivePaymentAgainstGLMemo.sendKeys("Update Receive Payment Against GL Memo");
			Thread.sleep(1000);

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentAgainstGLEditSave"))));
			driver.findElement(By.xpath(pro.getProperty("ReceivePaymentAgainstGLEditSave"))).click();

			Thread.sleep(2000);
			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(1000);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("EditReceivePaymentAgainstGLClose"))));
				driver.findElement(By.xpath(pro.getProperty("EditReceivePaymentAgainstGLClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys(CopyReceivePaymentAgainstGLID);
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
					System.out.println(
							"Receive Payment Against GL " + CopyReceivePaymentAgainstGLID + " is edited Successfully");
				} else {
					System.out.println("Receive Payment Against GL " + CopyReceivePaymentAgainstGLID
							+ " is not edited Successfully");
				}

			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);
			}

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteReceivePaymentAgainstGLButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteReceivePaymentAgainstGLButton"))).click();

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteReceivePaymentAgainstGLPermButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteReceivePaymentAgainstGLPermButton"))).click();

			Thread.sleep(2000);
			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(5000);

			search.clear();
			search.sendKeys(CopyReceivePaymentAgainstGLID);
			Thread.sleep(500);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			try {
				WebElement afterDeleteLink = new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath("//a[contains(@onclick,'javascript:openRecNew')]")));

				if (afterDeleteLink.isDisplayed()) {
					System.out.println("Receive Payment Against GL " + CopyReceivePaymentAgainstGLID
							+ "  is Deleted Successfully");
				}
			} catch (Exception deleteexp) {
				System.out.println(
						"Receive Payment Against GL " + CopyReceivePaymentAgainstGLID + " is NOT deleted !!!!!");
			}

			Utilities.refresh();

		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}
	}

	/*******************
	 * Receive Payment for GL with Multiple Account
	 *************/
	public static void create_ReceivePayment_AgainstGLMul(String documentid, String accountname, WebDriver driver,
			String BankName, String DocumentCurrency, String ForexCurrecny1)
			throws InterruptedException, AWTException, IOException {

		try {
			documentid = "RPGL" + documentid;
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstGL.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentIcon"))));
			driver.findElement(By.xpath(pro.getProperty("ReceivePaymentIcon"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentAgainstGLCheck"))));
			driver.findElement(By.xpath(pro.getProperty("ReceivePaymentAgainstGLCheck"))).click();
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
			driver.findElement(By.xpath(pro.getProperty("DocumentNo"))).sendKeys(documentid);

			WebElement memo = driver.findElement(By.xpath(pro.getProperty("Memo")));
			memo.click();
			memo.sendKeys("Receive Payment Against GL");

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

			Thread.sleep(1000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Account")) {

					for (int j = 1; j <= 2; j++) {
						driver.findElement(By
								.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div["
										+ j + "]/table/tbody/tr/td[" + i + "]/div"))
								.click();
						Thread.sleep(2000);

						new WebDriverWait(driver, 30).until(ExpectedConditions
								.elementToBeClickable(By.xpath(pro.getProperty("QuickSearchGLAccount"))));
						driver.findElement(By.xpath(pro.getProperty("QuickSearchGLAccount"))).sendKeys(accountname);

						Thread.sleep(4000);// pro

						// enter vendor
						new WebDriverWait(driver, 30).until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath(pro.getProperty("SelectGLAccount"))));
						WebElement selectedGL = driver.findElement(By.xpath(pro.getProperty("SelectGLAccount")));
						selectedGL.click();

						new WebDriverWait(driver, 30).until(
								ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
						driver.findElement(By.xpath(pro.getProperty("SubmitButton1"))).click();
						Thread.sleep(2000);
					}
				}
			}

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='receiptwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='receiptwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Enter Amount")) {
					for (int l = 1; l <= 2; l++) {
						driver.findElement(By
								.xpath("//div[@id='receiptwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div[2]/div/div["
										+ l + "]/table/tbody/tr/td[" + i + "]/div"))
								.click();
						Thread.sleep(2000);
						Utilities.sendText("100");
					}
				}
			}

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

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseMakePayment"))));
			driver.findElement(By.xpath(pro.getProperty("CloseMakePayment"))).click();
			System.out
					.println("Receive Payment against GL completed successfully for doc id : [" + documentid + "]***");

		} catch (Exception EX) {
			System.out.println("RP against GL NOT CREATED !!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

}
