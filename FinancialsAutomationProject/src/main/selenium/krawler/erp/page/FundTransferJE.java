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

public class FundTransferJE {

	public static void create_fundTransferJE(String documentid, String productid, String vendorid, String debitAccount,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties NormalJE = Utilities.fetchProValue("OR_FundTransferJE.properties");
			// documentid= "FuTr"+documentid;
			Utilities.waitandClick(NormalJE.getProperty("ClcikOnJEIcon"), driver);
			Utilities.click_element(NormalJE.getProperty("SelectFundsJEOption"), driver);
			Utilities.HoverandClick(NormalJE.getProperty("ClickOnSubmit"), driver);
			Thread.sleep(2000);

			Utilities.enterTextandSelect("NA", NormalJE.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox("FuTr" + documentid, NormalJE.getProperty("EnterJENo."), driver);

			Utilities.enterTextandSelect("Cash", NormalJE.getProperty("SelectPaymentMethod"), driver);
			Utilities.HoverandClick(NormalJE.getProperty("memo"), driver);

			// select 1st user PaidTo
			Utilities.HoverandClick("//*[@name='paidToCmb']/following::img[1]", driver);
			Utilities.HoverandClick(
					"//*[contains(@style,'visible') and @class='x-layer x-combo-list ']/div/div[2]/table/tbody/tr/td[1]",
					driver);
			Utilities.HoverandClick(NormalJE.getProperty("memo"), driver);

			int headerSize = Utilities.totalSize("//*[text()='Account']/ancestor::tr/td/div", driver);
			int indOfAcc = 0, indOfdebit = 0, indOfcredit = 0;

			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver.findElement(By.xpath("//*[text()='Account']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Account")) {
					indOfAcc = i;
				} else if (headerName.equalsIgnoreCase("Debit Amount")) {
					indOfdebit = i;
				} else if (headerName.equalsIgnoreCase("Credit Amount")) {
					indOfcredit = i;
				}
			}

			// enter Credit Account
			Utilities.clickAndEnterText("10",
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfcredit + "]/div",
					driver);
			Utilities.HoverandClick("//*[@name='memo']", driver);

			// enter Debit Account
			Utilities.HoverandClick(
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfAcc + "]/div",
					driver);
			Utilities.enterTextandSelect(debitAccount, "//*[@name='accountid']/following::input[1]", driver);
			Utilities.clickAndEnterText("10",
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfdebit + "]/div",
					driver);
			Utilities.HoverandClick("//*[@name='memo']", driver);

			Utilities.HoverandClick(NormalJE.getProperty("ClickOnSaveButtton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(NormalJE.getProperty("CloseForm"), driver);
			System.out.println("JE for Fund Transfer[" + "FuTr" + documentid + "] created success !!!!");
		} catch (Exception EX) {
			System.out.println("JE for Fund Transfer[" + "FuTr" + documentid + "] NOT created !!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ************************************* verification
	// *********************************************

	public static void verify_fundTransfer(String documentID, String description, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_FundTransferJE.properties");
			// documentID= "FuTr"+documentID;

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("JournalEntryReport"))));
			driver.findElement(By.xpath(pro.getProperty("JournalEntryReport"))).click();
			Thread.sleep(5000);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentID);
			search.sendKeys(Keys.TAB);

			WebElement fetchbtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("FetchButton"))));
			fetchbtn.click();
			Thread.sleep(2000);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg24NormalJournalEntryDetails']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg24NormalJournalEntryDetails']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				// System.out.println(header);
				if (header.equals("Entry Number")) {
					assertEquals(documentID,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg24NormalJournalEntryDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("JE Number Verified");
				}

				else if (header.equals("Description")) {
					assertEquals(description,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg24NormalJournalEntryDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}
			}
			System.out.println("Funds Transfer JE [" + documentID + "] verified Success !!!");

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseJournalEntryReport"))));
			driver.findElement(By.xpath(pro.getProperty("CloseJournalEntryReport"))).click();
			Thread.sleep(500);

		} catch (Exception EX) {
			System.out.println("Funds Transfer JE [" + documentID + "] NOT verified !!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ********************** Copy Edit Delete
	// ****************************************

	public static void CopyEditDelete_fundTransferJE(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		// Add code if We want to use EXTRA Account while Editing
	}

	// *************************************** Cheque Print for Fund transfer
	// ***********************

	public static void create_fundTransferJE_PrintCheque(String documentid, String payMethod, String chqNumber,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties NormalJE = Utilities.fetchProValue("OR_FundTransferJE.properties");
			documentid = "FuTrChq" + documentid;
			Utilities.waitandClick(NormalJE.getProperty("ClcikOnJEIcon"), driver);
			Utilities.click_element(NormalJE.getProperty("SelectFundsJEOption"), driver);
			Utilities.HoverandClick(NormalJE.getProperty("ClickOnSubmit"), driver);
			Thread.sleep(2000);

			Utilities.enterTextandSelect("NA", NormalJE.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid, NormalJE.getProperty("EnterJENo."), driver);

			Utilities.enterTextandSelect(payMethod, NormalJE.getProperty("SelectPaymentMethod"), driver);
			Utilities.HoverandClick(NormalJE.getProperty("memo"), driver);

			// select 1st user PaidTo
			Utilities.HoverandClick("//*[@name='paidToCmb']/following::img[1]", driver);
			Utilities.HoverandClick(
					"//*[contains(@style,'visible') and @class='x-layer x-combo-list ']/div/div[2]/table/tbody/tr/td[1]",
					driver);
			Utilities.HoverandClick(NormalJE.getProperty("memo"), driver);

			Utilities.enterTextandSelect("NA", NormalJE.getProperty("chqSeqFormat"), driver);
			Utilities.enterTextNormalBox(chqNumber, NormalJE.getProperty("chqNumb"), driver);

			int headerSize = Utilities.totalSize("//*[text()='Account']/ancestor::tr/td/div", driver);
			int indOfAcc = 0, indOfdebit = 0, indOfcredit = 0;

			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver.findElement(By.xpath("//*[text()='Account']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Account")) {
					indOfAcc = i;
				} else if (headerName.equalsIgnoreCase("Debit Amount")) {
					indOfdebit = i;
				} else if (headerName.equalsIgnoreCase("Credit Amount")) {
					indOfcredit = i;
				}
			}

			// enter Credit Account
			Utilities.clickAndEnterText("10",
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfcredit + "]/div",
					driver);
			Utilities.HoverandClick("//*[@name='memo']", driver);

			// enter Debit Account
			Utilities.HoverandClick(
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfAcc + "]/div",
					driver);
			Utilities.enterTextandSelect("Cheque Account", "//*[@name='accountid']/following::input[1]", driver);
			Utilities.clickAndEnterText("10",
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfdebit + "]/div",
					driver);
			Utilities.HoverandClick("//*[@name='memo']", driver);

			// click on Save and Print Cheque
			Utilities.clickButton("Save and Print Cheque", 400, driver);
			Utilities.clickButton("Yes", 300, driver);

			WebElement okToPrint = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
			if (okToPrint.isDisplayed()) {
				Thread.sleep(800);
				// Handle print button
				Utilities.sikuliHandle("maximize");
				Thread.sleep(3000);
				Utilities.sikuliHandle("Print_Cancel");
				okToPrint.click();
				System.out.println(
						"JE Fund transfer PrintCheque [" + documentid + "] Bank_ChequePrinted successfully !!!!");
			}

			Utilities.HoverandClick(NormalJE.getProperty("CloseForm"), driver);
		} catch (Exception EX) {
			System.out.println("JE for Fund Transfer Print Cheque [" + documentid + "] NOT created !!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// *****************************print cheque against vendor
	// *********************************************

	public static void print_JE_FundTrns_Report(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "FuTrChq" + documentid;
			;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_FundTransferJE.properties");
			// documentID= "FuTr"+documentID;

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("JournalEntryReport"))));
			driver.findElement(By.xpath(pro.getProperty("JournalEntryReport"))).click();
			Thread.sleep(5000);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);

			WebElement fetchbtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("FetchButton"))));
			fetchbtn.click();
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

					/*
					 * Utilities.sikuliHandle("Change_Print");
					 * Thread.sleep(3000);
					 * 
					 * Utilities.sikuliHandle("PDF"); Thread.sleep(3000);
					 */
					System.out.println("JE for Fund transfer Cheque [" + documentid + "] Re-Printed Success !!!");
				}
			}

			Thread.sleep(1000);
			WebElement closeBtn = new WebDriverWait(driver, 20).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("CloseJournalEntryReport"))));
			closeBtn.click();

		} catch (Exception EX) {
			System.out.println("JE for Fund transfer Cheque [" + documentid + "] Bank_Cheque NOT Printed  !!!!");
			Utilities.handleError(EX, driver);
		}
	}

}