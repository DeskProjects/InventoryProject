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

public class DebitNoteAgainstCustomer {

	public static void create_debitNoteAgainstCustomer(String documentid, String productid, String customerid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_DebitNoteAgainstCustomer.properties");
			Utilities.waitandClick(pro.getProperty("DebitNoteIcon"), driver);
			Utilities.waitandClick(pro.getProperty("DebitNoteCustomer"), driver);
			Utilities.waitandClick(pro.getProperty("ClickOnSubmit"), driver);
			Thread.sleep(2000);

			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox("DNcus" + documentid, pro.getProperty("DocumentName"), driver);
			Utilities.enterTextandSelect(customerid, pro.getProperty("EnterCustomerId"), driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			// ADD Account
			int headerSize = Utilities.totalSize("//*[text()='Account']/ancestor::tr/td/div", driver);
			int indOfacc = 0, indOfamt = 0, indOfreason = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver.findElement(By.xpath("//*[text()='Account']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Account")) {
					indOfacc = i;
				}
				if (headerName.equalsIgnoreCase("Amount")) {
					indOfamt = i;
				}
			}
			String accountArr[] = { "COA" + documentid };
			for (int j = 0; j < accountArr.length; j++) {
				Utilities.HoverandClick("//*[text()='Account']/ancestor::div[3]/following::div[1]/div/div["
						+ ((j + 1) * 1) + "]/table/tbody/tr/td[" + indOfacc + "]/div", driver);
				Utilities.enterTextandSelect(accountArr[j], "//*[@id='accountid']/following::input[1]", driver);
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
				Utilities.HoverandClick("//*[text()='Account']/ancestor::div[3]/following::div[1]/div/div["
						+ ((j + 1) * 1) + "]/table/tbody/tr/td[" + indOfamt + "]/div", driver);
				Utilities.enter_LinelabelAmount("100", driver);
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			}

			// hovertilllast
			Utilities.justHover(
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[contains(@class,'x-grid3-cell-last')]",
					driver);
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver.findElement(By.xpath("//*[text()='Account']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Reason")) {
					indOfreason = i;
				}
			}
			for (int j = 0; j < accountArr.length; j++) {
				Utilities.HoverandClick("//*[text()='Account']/ancestor::div[3]/following::div[1]/div/div["
						+ ((j + 1) * 1) + "]/table/tbody/tr/td[" + indOfreason + "]/div", driver);
				// select first Reason
				Utilities.HoverandClick("//*[@id='reason']/following::img[1]", driver);
				Utilities.HoverandClick("//*[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div[2]",
						driver);
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			}

			Utilities.HoverandClick(pro.getProperty("ClickOnSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("CloseForm"), driver);

			System.out.println("****** Debit Note sucessfully created Against Customer:- [DNcus" + documentid);
		} catch (Exception EX) {
			System.out.println(
					"****** Debit Note NOT created Against Customer:- [DNcus" + documentid + "] check LOG !!!!!!");
			Utilities.handleError(EX, driver);
		}

	}

	// **************************************************************************************************************************************************************************

	public static void verify_debitNoteAgainstCustomer(String documentid, String productid, String customerid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			documentid = "cus" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_DebitNoteOtherWise.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DebitNoteReport"))));
			driver.findElement(By.xpath(pro.getProperty("DebitNoteReport"))).click();
			Thread.sleep(5000);// pro

			WebElement searchtype = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='view22DebitNoteDetails']")));
			searchtype.clear();
			searchtype.sendKeys("Debit Note for Customers");
			Thread.sleep(2000);
			searchtype.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();
			System.out.println(e);
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Debit Note No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d1");
				}

				else if (header.equals("Name")) {
					assertEquals(customerid + "Name",
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d2");
				}
			}
			System.out.println(" Verified Debit Note for Customers [" + documentid + "] !!!! ");
			Thread.sleep(500);
			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);

		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	// Copy-Edit-Delete flow --->>>>>>>>>>>>>>>>>>>>>>>>>>>
	public static void CopyEditDelete_debitNoteAgainstCustomer(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_DebitNoteAgainstCustomer.properties");
			String CopyDNAgainstCustomerID = "Copy" + documentid;

			Utilities.waitandClick(pro.getProperty("DebitNoteReport"), driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			Utilities.enterTextandSelect("Debit Note for Customers", "//*[@id='view22DebitNoteDetails']", driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			// Copy
			Utilities.HoverandClick(pro.getProperty("copyDNAgainstCustomerButton"), driver);
			String formLoaded = "//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td[3]";
			Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			Utilities.enterTextNormalBox(CopyDNAgainstCustomerID, pro.getProperty("copyDNAgainstCustomerId"), driver);
			Utilities.HoverandClick(pro.getProperty("saveCopiedDNAgainstCustomerId"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("CopyDNAgainstCustomerClose"), driver);
			Utilities.enterTextInDropDown(CopyDNAgainstCustomerID, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println("*** COPY test case for Debit note No. [" + CopyDNAgainstCustomerID + "] is PASS !!");

			// Edit
			Utilities.HoverandClick(pro.getProperty("EditDNAgainstCustomerButton"), driver);
			Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			Utilities.enterTextNormalBox("Performing EDIT test case for Debit Note No:- " + CopyDNAgainstCustomerID,
					pro.getProperty("DNAgainstCustomerMemo"), driver);
			Utilities.HoverandClick(pro.getProperty("DNAgainstCustomerEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("EditDNAgainstCustomerClose"), driver);
			Utilities.enterTextInDropDown(CopyDNAgainstCustomerID, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println("*** EDIT test case for Debit note No. [" + CopyDNAgainstCustomerID + "] is PASS !!");

			// Delete
			Utilities.HoverandClick(pro.getProperty("deleteDNAgainstCustomerButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteDNAgainstCustomerPermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);

			// Confirmation Delete
			Utilities.enterTextInDropDown(CopyDNAgainstCustomerID, pro.getProperty("QuickSearch"), driver);
			WebElement deletedMsg = driver
					.findElement(By.xpath("//*[contains(text(),' Get Started by adding a Debit Note now')]"));
			if (deletedMsg.isDisplayed()) {
				System.out.println(
						"*** DELETE test case for Debit note No. [" + CopyDNAgainstCustomerID + "] is PASS !!");
			}

			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);
		} catch (Exception EX) {
			System.out.println("*** COPY-EDIT-DELETE test case for Debit note No. [" + documentid + "] is FAILED !!");
			Utilities.handleError(EX, driver);
		}
	}

}
