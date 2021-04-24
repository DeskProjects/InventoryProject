package krawler.erp.MP_RPModule;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.inventory.InvUtilities;
import krawler.erp.page.EmailFunctionality;
import krawler.erp.page.Utilities;

public class WriteOffFuncationalityForSaleInvoice_AdvanceRP {

	/*************************************** CreateCOAforSIwriteOffAccount *********************************************/

	public static void CreateCOA(WebDriver driver, String documentid, String groupid, String accType)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ChartOfAccount.properties");
			// clicked on Sales Return Document

			Utilities.waitandClick(pro.getProperty("iconCAO"), driver);
			Thread.sleep(2000);
			Utilities.HoverandClick(pro.getProperty("addNewAcc"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("accCode"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("accName"), driver);

			// select account type
			Utilities.click_element("//*[@name='accounttype']/following::img[1]", driver);
			Utilities.click_element(
					"//*[contains(@class,'x-combo-list') and contains(@style,'visible')]//*[text()='" + accType + "']",
					driver);
			// select group
			Utilities.enterTextandSelect(groupid, pro.getProperty("groupID"), driver);

			// Select first User
			Utilities.HoverandClick("//*[@id='username']/following::img[1]", driver);
			Utilities.HoverandClick("//*[contains(@class,'x-combo-list') and contains(@style,'visible')]/div/div",
					driver);

			Utilities.click_element(pro.getProperty("saveBtn"), driver);
			Utilities.click_element(pro.getProperty("okBtn"), driver);
			Utilities.click_element(pro.getProperty("coaClsBtn"), driver);
			System.out.println("Chart of account " + documentid + " successfully created !! ");
		} catch (Exception EX) {
			System.out.println("Chart of account " + documentid + " NOT created !! ");
			Utilities.handleError(EX, driver);
		}

	}

	/*************************************** CreateSavingSystemPreference *********************************************/

	public static void mapAccount(WebDriver driver, String SICOA)
			throws InterruptedException, AWTException, IOException {
		try {

			Properties pro = Utilities.fetchProValue("OR_SystemControls.properties");

			Utilities.refresh();
			Utilities.waitandClick(pro.getProperty("PreferencesExpander"), driver);
			Utilities.waitandClick(pro.getProperty("SystemControlsLink"), driver);
			Utilities.waitandClick(pro.getProperty("SIWriteOffAccount"), driver);
			Utilities.enterTextandSelect(SICOA, pro.getProperty("SIWriteOffAccount"), driver);
			Utilities.waitandClick(pro.getProperty("saveAccountPreferencesBtn"), driver);
			Utilities.waitandClick(pro.getProperty("applyNewsettingsBtn"), driver);
			Utilities.waitandClick(pro.getProperty("AccountPreferencesSavedOKBtn"), driver);
			Utilities.clickExpander(driver);
			System.out.println("Sytem preferences is saved Successfully....!! ");
		} catch (Exception EX) {
			System.out.println("Sytem preferences is not saved ....!!");
			Utilities.handleError(EX, driver);
		}
	}

	/*************************************** CreateSavingSystemPreference *********************************************/

	public static void mapAccountRP(WebDriver driver, String RPCOA)
			throws InterruptedException, AWTException, IOException {
		try {

			Properties pro = Utilities.fetchProValue("OR_SystemControls.properties");

			Utilities.refresh();
			Utilities.waitandClick(pro.getProperty("PreferencesExpander"), driver);
			Utilities.waitandClick(pro.getProperty("SystemControlsLink"), driver);
			Utilities.waitandClick(pro.getProperty("RPWriteOffAccount"), driver);
			Utilities.enterTextandSelect(RPCOA, pro.getProperty("RPWriteOffAccount"), driver);
			Utilities.waitandClick(pro.getProperty("saveAccountPreferencesBtn"), driver);
			Utilities.waitandClick(pro.getProperty("applyNewsettingsBtn"), driver);
			Utilities.waitandClick(pro.getProperty("AccountPreferencesSavedOKBtn"), driver);
			Utilities.clickExpander(driver);
			System.out.println("Sytem preferences is saved Successfully....!! ");
		} catch (Exception EX) {
			System.out.println("Sytem preferences is not saved ....!!");
			Utilities.handleError(EX, driver);
		}
	}

	/*************************************** CreateSaleInvoice *********************************************/

	public static void create_salesInvoice(String documentId, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentId = "SI" + documentId;
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			// clicked on main icon

			Utilities.HoverandClick(pro.getProperty("SalesInvoiceIcon"), driver);
			Utilities.clickCheckBox(pro.getProperty("includeTax"), "uncheck", driver);
			selectFromNormalDropDown(customerid, pro.getProperty("Customer"), driver);
			selectFromNormalDropDown("NA", pro.getProperty("SequenceFormat"), driver);
			selectFromNormalDropDown("NET 45", pro.getProperty("Term"), driver);
			Utilities.enterTextNormalBox(documentId, pro.getProperty("DocumentNo"), driver);

			Utilities.HoverandClick(pro.getProperty("AddButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("ProductSearch"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int i = driver
					.findElements(By
							.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size();

			for (int j = 1; j <= i; j++) {
				String header = driver.findElement(By
						.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ j + "]/div"))
						.getText();
				if (header.equals("Quantity")) {

					Utilities.clickAndEnterText("1",
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
									+ j + "]/div",
							driver);
					// to scroll till end
					Utilities.justHover(
							"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[contains(@class,'x-grid3-cell-last')]",
							driver);

				} else if (header.equals("Unit Price")) {
					Utilities.clickAndEnterText("100",
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
									+ j + "]/div",
							driver);

				}
			}

			// move focus frm Line label
			Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("CloseSalesInvoice"), driver);

			System.out.println("******* Sales Invoice: [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Sales Invoice is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}

	}

	/**************************************** WriteOffSaleInvoice ******************/

	public static void writeoffSI(WebDriver driver, String documentId, String SICOA)
			throws InterruptedException, AWTException, IOException {

		try {
			Properties pro = Utilities.fetchProValue("OR_CashSale.properties");
			Properties pro1 = Utilities.fetchProValue("OR_NormalJE.properties");

			Utilities.waitandClick(pro.getProperty("SalesInvoiceReport"), driver);
			Thread.sleep(3000);

			Utilities.clickAndEnterText(documentId, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath(".//*[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();

			String Amountdue = "";
			String InvoiceNo = "";
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//*[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath(".//*[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Amount Due")) {
					Amountdue = driver.findElement(By
							.xpath(".//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}

				else if (header.equals("Invoice No")) {
					InvoiceNo = driver.findElement(By
							.xpath(".//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}

			}

			System.out.println("Sales Invoice: [" + InvoiceNo + "] have amount due of " + Amountdue + " ...!");

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);

			InvUtilities.enableExpander(driver);

			Utilities.waitandClick(pro.getProperty("AccountReceivablelink"), driver);
			Utilities.waitandClick(pro.getProperty("Entrylink"), driver);
			Utilities.waitandClick(pro.getProperty("WriteOfflink"), driver);
			Utilities.waitandClick(pro.getProperty("SIwriteofflink"), driver);
			Utilities.clickAndEnterText(documentId, pro.getProperty("writeoffquicksearch"), driver);
			Thread.sleep(2000);
			Utilities.waitandClick(pro.getProperty("InvoiceCheckbox"), driver);
			Utilities.clickButton("Write Off Invoice(s)", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickAndEnterText("Write off Sales Invoice: [" + documentId + "]", pro.getProperty("writeofmemo"),
					driver);
			Utilities.clickButton("Save", 2000, driver);

			String SIWriteOffJE = driver
					.findElement(By
							.xpath("//div[@class='x-window x-window-plain x-window-dlg' and contains(@style,'visible')]/div[2]/div[1]/div/div/div/div/div[2]/span/b[2]"))
					.getText();

			Utilities.clickButton("OK", 2000, driver);
			Utilities.waitandClick(pro.getProperty("CloseWriteofftab"), driver);

			Utilities.clickExpander(driver);

			Utilities.waitandClick(pro.getProperty("SIreportTab"), driver);
			Utilities.clickButton("Fetch", 200, driver);

			String Amountdue1 = "";
			String InvoiceNo1 = "";
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//*[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath(".//*[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Amount Due")) {
					Amountdue1 = driver.findElement(By
							.xpath(".//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}

				else if (header.equals("Invoice No")) {
					InvoiceNo1 = driver.findElement(By
							.xpath(".//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}

			}

			String AmounDue2 = Amountdue1.substring(Amountdue1.indexOf(" ") + 1, Amountdue1.length());
			AmounDue2 = AmounDue2.replace(",", "");
			double b = Double.parseDouble(AmounDue2);

			if (b == 0) {

				System.out.println("Sales Invoice: [" + InvoiceNo1 + "] write off successfully have amount due of SGD "
						+ AmounDue2 + " ...!");
			} else {
				System.out.println("Sales Invoice: [" + InvoiceNo1 + "] write off failed as SI have amount due of SGD "
						+ AmounDue2 + " ...!");

			}

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);

			Utilities.waitandClick(pro1.getProperty("JournalEntryReport"), driver);
			Thread.sleep(3000);

			Utilities.clickAndEnterText(SIWriteOffJE, pro1.getProperty("QuickSearch"), driver);
			Thread.sleep(1000);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);

			Thread.sleep(1000);
			Utilities.waitandClick(pro1.getProperty("fetchButton2"), driver);
			Thread.sleep(2000);
			Utilities.waitandClick(pro1.getProperty("ExpandJE"), driver);

			String JEAmount = driver
					.findElement(By.xpath("//span[starts-with(text(),'[" + SICOA + "]')]/parent::div/span[4]/div"))
					.getText();

			String JEAmount2 = JEAmount.substring(JEAmount.indexOf(" ") + 1, JEAmount.length());
			JEAmount2 = JEAmount2.replace(",", "");
			double JEFinal = Double.parseDouble(JEAmount2);

			if (JEFinal == 100) {
				System.out.println("Write off JE is Verified Successfully");
				// System.out.println("Sale Invoice Write off account
				// ["+SICOA+"] is Sucessfully debit by "+ JEAmount+"..!!!");
			} else {
				System.out.println("Unable to verify Write off JE ");
				// System.out.println("Sale Invoice Write off account
				// ["+SICOA+"] is showing incorrect debit amount "+
				// JEAmount+"..!!!");
			}

			System.out.println("Sale Invoice is Write off and Verified Sucessfully...!!!");
			Utilities.refresh();
		} catch (Exception EX) {
			System.out.println("Sale Invoice  Write off and Verification Failed...!!!");
			Utilities.handleError(EX, driver);
		}
	}

	/**************************************** WriteOffRPAdvance ******************/

	public static void writeoffRP(WebDriver driver, String documentId, String RPCOA)
			throws InterruptedException, AWTException, IOException {

		try {
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");
			Properties pro1 = Utilities.fetchProValue("OR_NormalJE.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentReport"), driver);
			Thread.sleep(3000);

			Utilities.clickAndEnterText(documentId, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath(".//*[@id='gridmsg20receiptReport']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();

			String Amountdue = "";
			String RPNo = "";
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//*[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath(".//*[@id='gridmsg20receiptReport']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Amount Due")) {
					Amountdue = driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}

				else if (header.equals("Receipt No.")) {
					RPNo = driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}

			}

			System.out.println("Receive Payment: [" + RPNo + "] have amount due of " + Amountdue + " ...!");

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);

			InvUtilities.enableExpander(driver);

			Utilities.waitandClick(pro.getProperty("AccountReceivablelink"), driver);
			Utilities.waitandClick(pro.getProperty("Entrylink"), driver);
			Utilities.waitandClick(pro.getProperty("WriteOfflink"), driver);
			Utilities.waitandClick(pro.getProperty("RPwriteofflink"), driver);
			Utilities.clickAndEnterText(documentId, pro.getProperty("writeoffquicksearch"), driver);
			Thread.sleep(2000);
			Utilities.waitandClick(pro.getProperty("RPCheckbox"), driver);
			Utilities.clickButton("Write Off Receipts", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickAndEnterText("Write off Receive Paymnet : [" + documentId + "]",
					pro.getProperty("writeofmemo"), driver);
			Utilities.clickButton("Save", 2000, driver);

			String RPWriteOffJE = driver
					.findElement(By
							.xpath("//div[@class='x-window x-window-plain x-window-dlg' and contains(@style,'visible')]/div[2]/div[1]/div/div/div/div/div[2]/span/b[2]"))
					.getText();

			{
				try {
					WebElement button = new WebDriverWait(driver, 5)
							.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
					if (button.isDisplayed()) {
						Thread.sleep(20);
						button.click();
					}

				} catch (Exception Ex) {
				}
			}
			Utilities.waitandClick(pro.getProperty("CloseWriteofftab"), driver);

			Utilities.clickExpander(driver);

			Utilities.waitandClick(pro.getProperty("RPreportTab"), driver);
			Utilities.clickButton("Fetch", 200, driver);

			String Amountdue1 = "";
			String RPNo1 = "";
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//*[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath(".//*[@id='gridmsg20receiptReport']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Amount Due")) {
					Amountdue1 = driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}

				else if (header.equals("Receipt No.")) {
					RPNo1 = driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}

			}

			String AmounDue2 = Amountdue1.substring(Amountdue1.indexOf(" ") + 1, Amountdue1.length());
			AmounDue2 = AmounDue2.replace(",", "");
			double b = Double.parseDouble(AmounDue2);

			if (b == 0) {

				System.out.println("Receive Payment: [" + RPNo1 + "] write off successfully have amount due of SGD "
						+ AmounDue2 + " ...!");
			} else {
				System.out.println("Receive Payment: [" + RPNo1 + "] write off failed as SI have amount due of SGD "
						+ AmounDue2 + " ...!");

			}

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);

			Utilities.waitandClick(pro1.getProperty("JournalEntryReport"), driver);
			Thread.sleep(3000);

			Utilities.clickAndEnterText(RPWriteOffJE, pro1.getProperty("QuickSearch"), driver);
			Thread.sleep(1000);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);

			Thread.sleep(1000);
			Utilities.waitandClick(pro1.getProperty("fetchButton2"), driver);
			Thread.sleep(2000);
			Utilities.waitandClick(pro1.getProperty("ExpandJE"), driver);

			String JEAmount = driver
					.findElement(By.xpath("//span[starts-with(text(),'To [" + RPCOA + "]')]/parent::div/span[11]/div"))
					.getText();

			String JEAmount2 = JEAmount.substring(JEAmount.indexOf(" ") + 1, JEAmount.length());
			JEAmount2 = JEAmount2.replace(",", "");
			double JEFinal = Double.parseDouble(JEAmount2);

			if (JEFinal == 100) {
				System.out.println("Write off JE is Verified Successfully");
				// System.out.println("Sale Invoice Write off account
				// ["+SICOA+"] is Sucessfully debit by "+ JEAmount+"..!!!");
			} else {
				System.out.println("Unable to verify Write off JE ");
				// System.out.println("Sale Invoice Write off account
				// ["+SICOA+"] is showing incorrect debit amount "+
				// JEAmount+"..!!!");
			}

			System.out.println("Receive Payment is Write off and Verified Sucessfully...!!!");
			Utilities.refresh();
		} catch (Exception EX) {
			System.out.println("Receive Payment  Write off and Verification Failed...!!!");
			Utilities.handleError(EX, driver);
		}
	}

	/**************************************** RevertWriteOffSaleInvoice ******************/

	public static void RevertWriteOffSaleInvoice(WebDriver driver, String documentId, String SICOA)
			throws InterruptedException, AWTException, IOException {

		try {
			Properties pro = Utilities.fetchProValue("OR_CashSale.properties");
			Properties pro1 = Utilities.fetchProValue("OR_NormalJE.properties");

			Utilities.waitandClick(pro.getProperty("AccountReceivablelink"), driver);
			Utilities.waitandClick(pro.getProperty("Writeoffreport"), driver);
			Utilities.waitandClick(pro.getProperty("SIWriteoffreport"), driver);
			Thread.sleep(2000);
			Utilities.clickAndEnterText(documentId, pro.getProperty("ReportquickSearch"), driver);
			Thread.sleep(2000);

			Utilities.waitandClick(pro.getProperty("doccheckbox"), driver);
			Thread.sleep(2000);
			Utilities.clickButton("Recover Sales Invoice", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 2000, driver);

			int l = driver
					.findElements(By
							.xpath("//*[@id='writeOffCustomerInvoiceReport']/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size();
			String REvertJENo = "";

			for (int k = 1; k <= l; k++) {

				String header = driver.findElement(By
						.xpath("//*[@id='writeOffCustomerInvoiceReport']/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ k + "]/div"))
						.getText();

				if (header.equals("Recovered Journal Entry Number")) {
					REvertJENo = driver.findElement(By
							.xpath("//*[@id='writeOffCustomerInvoiceReport']/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				}
			}

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);
			Utilities.clickExpander(driver);

			Utilities.waitandClick(pro.getProperty("SalesInvoiceReport"), driver);
			Thread.sleep(5000);

			Utilities.clickAndEnterText(documentId, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath(".//*[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();

			String Amountdue = "";
			String InvoiceNo = "";
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//*[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath(".//*[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Amount Due")) {
					Amountdue = driver.findElement(By
							.xpath(".//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}

				else if (header.equals("Invoice No")) {
					InvoiceNo = driver.findElement(By
							.xpath(".//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}

			}

			String AmounDue2 = Amountdue.substring(Amountdue.indexOf(" ") + 1, Amountdue.length());
			AmounDue2 = AmounDue2.replace(",", "");
			double b = Double.parseDouble(AmounDue2);

			if (b == 100) {
				System.out.println("Sale Invoice [" + InvoiceNo + "]wrtite off reverted successfully and Amount due ["
						+ Amountdue + "] updated Successfully...!!");
			} else {
				System.out.println("Sale Invoice [" + InvoiceNo + "]wrtite off reverted failed and Amount due ["
						+ Amountdue + "] updated wrong...!!");

			}

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);

			Utilities.waitandClick(pro1.getProperty("JournalEntryReport"), driver);
			Thread.sleep(3000);

			Utilities.clickAndEnterText(REvertJENo, pro1.getProperty("QuickSearch"), driver);
			Thread.sleep(1000);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);

			Thread.sleep(1000);
			Utilities.waitandClick(pro1.getProperty("fetchButton2"), driver);
			Thread.sleep(2000);
			Utilities.waitandClick(pro1.getProperty("ExpandJE"), driver);

			String JEAmount = driver
					.findElement(By.xpath("//span[starts-with(text(),'To [" + SICOA + "]')]/parent::div/span[11]/div"))
					.getText();

			String JEAmount2 = JEAmount.substring(JEAmount.indexOf(" ") + 1, JEAmount.length());
			JEAmount2 = JEAmount2.replace(",", "");
			double JEFinal = Double.parseDouble(JEAmount2);

			if (JEFinal == 100) {
				System.out.println("Reverse Write off JE is Verified Successfully");
				// System.out.println("Sale Invoice Write off account
				// ["+SICOA+"] is Sucessfully debit by "+ JEAmount+"..!!!");
			} else {
				System.out.println("Unable to verify Reverse Write off JE ");
				// System.out.println("Sale Invoice Write off account
				// ["+SICOA+"] is showing incorrect debit amount "+
				// JEAmount+"..!!!");
			}

		} catch (Exception EX) {

			System.out.println("Sale Invoice  Write off  failed to revert and Verification Failed...!!!");
			Utilities.handleError(EX, driver);
		}

	}

	/**************************************** RevertWriteOffReceivePayment ******************/

	public static void RevertWriteOffRPAD(WebDriver driver, String documentId, String RPCOA)
			throws InterruptedException, AWTException, IOException {

		try {
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");
			Properties pro1 = Utilities.fetchProValue("OR_NormalJE.properties");

			Utilities.waitandClick(pro.getProperty("AccountReceivablelink"), driver);
			Utilities.waitandClick(pro.getProperty("Writeoffreport"), driver);
			Utilities.waitandClick(pro.getProperty("RPWriteoffreport"), driver);
			Thread.sleep(2000);
			Utilities.clickAndEnterText(documentId, pro.getProperty("ReportquickSearch"), driver);
			Thread.sleep(2000);

			Utilities.waitandClick(pro.getProperty("doccheckbox"), driver);
			Thread.sleep(2000);
			Utilities.clickButton("Recover Receipt", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 2000, driver);

			int l = driver
					.findElements(By
							.xpath("//*[@id='writeOffReceiptReport']/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size();
			String REvertJENo = "";

			for (int k = 1; k <= l; k++) {

				String header = driver.findElement(By
						.xpath("//*[@id='writeOffReceiptReport']/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ k + "]/div"))
						.getText();

				if (header.equals("Recovered Journal Entry Number")) {
					REvertJENo = driver.findElement(By
							.xpath("//*[@id='writeOffReceiptReport']/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				}
			}

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);
			Utilities.clickExpander(driver);

			Utilities.waitandClick(pro.getProperty("ReceivePaymentReport"), driver);
			Thread.sleep(5000);

			Utilities.clickAndEnterText(documentId, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath(".//*[@id='gridmsg20receiptReport']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();

			String Amountdue = "";
			String RPNo = "";
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//*[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath(".//*[@id='gridmsg20receiptReport']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Amount Due")) {
					Amountdue = driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}

				else if (header.equals("Receipt No.")) {
					RPNo = driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}

			}

			String AmounDue2 = Amountdue.substring(Amountdue.indexOf(" ") + 1, Amountdue.length());
			AmounDue2 = AmounDue2.replace(",", "");
			double b = Double.parseDouble(AmounDue2);

			if (b == 100) {
				System.out.println("Receive Payment [" + RPNo + "]wrtite off reverted successfully and Amount due ["
						+ Amountdue + "] updated Successfully...!!");
			} else {
				System.out.println("Receive Payment [" + RPNo + "]wrtite off reverted failed and Amount due ["
						+ Amountdue + "] updated wrong...!!");

			}

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);

			Utilities.waitandClick(pro1.getProperty("JournalEntryReport"), driver);
			Thread.sleep(3000);

			Utilities.clickAndEnterText(REvertJENo, pro1.getProperty("QuickSearch"), driver);
			Thread.sleep(1000);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);

			Thread.sleep(1000);
			Utilities.waitandClick(pro1.getProperty("fetchButton2"), driver);
			Thread.sleep(2000);
			Utilities.waitandClick(pro1.getProperty("ExpandJE"), driver);

			String JEAmount = driver
					.findElement(By.xpath("//span[starts-with(text(),'[" + RPCOA + "]')]/parent::div/span[4]/div"))
					.getText();

			String JEAmount2 = JEAmount.substring(JEAmount.indexOf(" ") + 1, JEAmount.length());
			JEAmount2 = JEAmount2.replace(",", "");
			double JEFinal = Double.parseDouble(JEAmount2);

			if (JEFinal == 100) {
				System.out.println("Reverse Write off JE is Verified Successfully");
				// System.out.println("Receive Payment Write off account
				// ["+RPCOA+"] is Sucessfully debit by "+ JEAmount+"..!!!");
			} else {
				System.out.println("Unable to verify Reverse Write off JE ");
				// System.out.println("Receive Payment Write off account
				// ["+RPCOA+"] is showing incorrect debit amount "+
				// JEAmount+"..!!!");
			}

		} catch (Exception EX) {

			System.out.println("Receive Payment  Write off  failed to revert and Verification Failed...!!!");
			Utilities.handleError(EX, driver);
		}

	}

	/****************************************/

	public static void selectFromNormalDropDown(String text, String expForname, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		WebElement enterText = null;
		boolean success = false;
		for (int num_try = 0; !success && num_try < 10; num_try++) {
			try {
				enterText = driver.findElement(By.xpath(expForname));
				enterText.clear();
				enterText.sendKeys(text);
				Thread.sleep(1000);
				WebElement element = driver.findElement(
						By.xpath("//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]//*[text()='"
								+ text + "']"));
				element.click();
				Thread.sleep(1500);
				success = true;
			} catch (Exception EX) {
				System.out.println(num_try);
				if (num_try == 9) {
					Utilities.handleError(EX, driver);
				}
			}
		}
	}

}
