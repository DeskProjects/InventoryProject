package krawler.erp.modules;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import krawler.erp.page.Utilities;

public class VisualAspectSalesInvoice {
	public static String JEnumber = null;

	public static void create_salesInvoice(String documentId, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentId = "SI" + documentId;
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			// clicked on main icon

			Utilities.HoverandClick(pro.getProperty("SalesInvoiceIcon"), driver);
			Utilities.clickCheckBox(pro.getProperty("includeTax"), "uncheck", driver);
			Utilities.enterTextandSelect(customerid, pro.getProperty("Customer"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextandSelect("NET 45", pro.getProperty("Term"), driver);
			Utilities.enterTextNormalBox(documentId, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect("US Dollars (USD)", "//*[@id='currencyid']/following::input[1]", driver);
			Utilities.clickCheckBox("//*[@name='includingGST']", "check", driver);
			Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
			Utilities.clickButton("Yes", 10, driver);

			Utilities.HoverandClick(pro.getProperty("AddButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("ProductSearch"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			List<WebElement> header = driver.findElements(By.xpath(
					"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td/div"));
			int headRsize = header.size();
			int indexQty = 0, indexTax = 0, indOfdisc = 0, indOftypdisc = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Quantity")) {
					indexQty = i;
				}
			}
			// System.out.println(indexQty +"&"+ indexTax );

			// enter line level quantity
			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div["
						+ i + "]/table/tbody/tr/td[" + indexQty + "]/div", driver);
				Utilities.enter_LinelabelAmount("1", driver);
				Thread.sleep(400);
			}
			// to scroll till end
			Utilities.justHover(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[contains(@class,'x-grid3-cell-last')]",
					driver);

			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (HeadeName.equalsIgnoreCase("Product Tax")) {
					indexTax = i;
				}
				if (HeadeName.equalsIgnoreCase("Discount")) {
					indOfdisc = i;
				}
				if (HeadeName.equalsIgnoreCase("Discount Type")) {
					indOftypdisc = i;
				}
			}
			// apply discounts
			Utilities.HoverandClick(
					"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfdisc + "]/div",
					driver);
			Utilities.enter_LinelabelAmount("10", driver);

			Utilities.HoverandClick(
					"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[2]/table/tbody/tr/td["
							+ indOftypdisc + "]/div",
					driver);
			Utilities.enterTextandSelect("Flat", "//*[@name='typeid']", driver);
			Utilities.HoverandClick(
					"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfdisc + "]/div",
					driver);
			Utilities.enter_LinelabelAmount("30", driver);
			Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);

			// enter line level Tax
			String taxNme[] = { "GST(DS)@7.00%", "GST(SR)@7.00%" };
			int sizeOfArr = taxNme.length;
			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div["
						+ i + "]/table/tbody/tr/td[" + indexTax + "]/div", driver);
				Thread.sleep(1000);

				if (i <= sizeOfArr) {
					Utilities.enterTextInDropDown(taxNme[i - 1], "//input[@id='prtaxid']/following::input[1]", driver);
				} else {
					int index = getArrayIndex(i, sizeOfArr);
					Utilities.enterTextInDropDown(taxNme[index - 1], "//input[@id='prtaxid']/following::input[1]",
							driver);
				}
			}

			// move ficus frm Line label
			Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			String totalAmount = driver.findElement(By.xpath("//*[text()='Total Amount: ']/ancestor::tr/td[2]/div"))
					.getText();
			System.out.println("Total Amount: " + totalAmount);
			String totalAmountInBaseCurr = driver
					.findElement(By.xpath("//*[text()='Total Amount(In Base Currency): ']/ancestor::tr/td[2]/div"))
					.getText();
			System.out.println("Total Amount(In Base Currency): " + totalAmountInBaseCurr);
			Utilities.HoverandClick(pro.getProperty("CloseSalesInvoice"), driver);
			System.out.println("******* Sales Invoice: [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Sales Invoice is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// to enter different tax
	public static int getArrayIndex(int c, int al) {
		if (c >= al) {
			c = c - al;
			getArrayIndex(c, al);
		} else {
			return c;
		}
		return c;
	}

	// ******************* Verify SI Report & JE
	// *********************************************
	public static void verify_SalesInvoice(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			Properties pro = Utilities.fetchProValue("OR_CashSale.properties");
			boolean flag = true;
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";
			Utilities.HoverandClick(pro.getProperty("SalesInvoiceReport"), driver);

			Utilities.isElementGone(xpathOfLoading, 120, driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.isLoadingDisappear(120, driver);
			JEnumber = driver
					.findElement(By
							.xpath("//*[text()='Invoice No']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table//td//*[@class='jumplink' and contains(text(),'JE')]"))
					.getText();

			// scroll
			Utilities.justHover(
					"//*[text()='Invoice No']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[contains(@class,'td-memoundefinedInvoiceListEntry ')]",
					driver);
			int headerSize = Utilities.totalSize("//*[text()='Invoice No']/ancestor::tr/td/div", driver);
			int indOfdis = 0, indOftax = 0, totalAmt = 0, totalBaseCurrAmnt = 0, indOfJE = 0;
			for (int i = 1; i < headerSize; i++) {
				String headerName = driver
						.findElement(By.xpath("//*[text()='Invoice No']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headerName.equalsIgnoreCase("Discount")) {
					indOfdis = i;
				}
				if (headerName.equalsIgnoreCase("Tax Amount")) {
					indOftax = i;
				}
				if (headerName.equalsIgnoreCase("Total Amount")) {
					totalAmt = i;
				}
				if (headerName.equalsIgnoreCase("Total Amount (In Base Currency) (SG Dollar (SGD))")) {
					totalBaseCurrAmnt = i;
				}
			}

			String discount = driver.findElement(By
					.xpath("//*[text()='Invoice No']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr[1]/td["
							+ indOfdis + "]/div"))
					.getText();
			String taxAmt = driver.findElement(By
					.xpath("//*[text()='Invoice No']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr[1]/td["
							+ indOftax + "]/div"))
					.getText();
			String TotalAmt = driver.findElement(By
					.xpath("//*[text()='Invoice No']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr[1]/td["
							+ totalAmt + "]/div"))
					.getText();
			String TotalAmt2 = driver.findElement(By
					.xpath("//*[text()='Invoice No']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr[1]/td["
							+ totalBaseCurrAmnt + "]/div"))
					.getText();
			// System.out.println(discount+taxAmt+TotalAmt+TotalAmt2);

			String headtitle[] = { "Total Discount", "Total Tax", "Total Amount", "Total Amount(In Base Currency)" };
			String actualResult[] = { discount, taxAmt, TotalAmt, TotalAmt2 };
			String expectedResult[] = { "$ 70.00", "$ 47.76", "$ 730.00", "SGD 365.00" };

			for (int i = 0; i < expectedResult.length; i++) {
				if (actualResult[i].equalsIgnoreCase(expectedResult[i])) {
					System.out.println(
							"** Sales Invoice report VERIFIED [" + headtitle[i] + "] with [" + actualResult[i] + " ]");
				} else {
					System.out.println("** Sales Invoice report NOT VERIFIED [" + headtitle[i] + "] with ["
							+ actualResult[i] + " ]");
					flag = false;
				}
			}
			if (flag == false) {
				driver.navigate().refresh();
				Assert.assertTrue(false);
				System.out.println("!!!! Sales Invoice report NOT VERIFIED for transaction [" + documentid + "] !!!!");
			}
			System.out.println("\n");
			// --------- Open JE report ---------
			Utilities.HoverandClick(
					"//*[text()='Invoice No']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody//*[@class='jumplink' and contains(text(),'JE')]",
					driver);
			Utilities.isLoadingDisappear(112, driver); // form loaded wait
			// Debit trans ------------------
			String FirstAcc = driver.findElement(By.xpath("//*[@class='expanderContainer1']/div[3]/span[1]")).getText();
			String DebitAmtFirstAcc = driver
					.findElement(By.xpath("//*[@class='expanderContainer1']/div[3]/span[1]/following::span[3]/div"))
					.getText();
			String SecAcc = driver.findElement(By.xpath("//*[@class='expanderContainer1']/div[3]/span[7]")).getText();
			String DebitAmtSecAcc = driver
					.findElement(By.xpath("//*[@class='expanderContainer1']/div[3]/span[7]/following::span[3]/div"))
					.getText();
			String ThirdAcc = driver.findElement(By.xpath("//*[@class='expanderContainer1']/div[3]/span[13]"))
					.getText();
			String DebitAmtThirdAcc = driver
					.findElement(By.xpath("//*[@class='expanderContainer1']/div[3]/span[13]/following::span[3]/div"))
					.getText();
			// Credit trans ------------------
			String SecondAccTax = driver.findElement(By.xpath("//*[@class='expanderContainer1']/div[3]/span[19]"))
					.getText();
			String DebitAmtSecondAccTax = driver
					.findElement(By.xpath("//*[@class='expanderContainer1']/div[3]/span[19]/following::span[4]/div"))
					.getText();
			String FirstCreditAcc = driver.findElement(By.xpath("//*[@class='expanderContainer1']/div[3]/span[25]"))
					.getText();
			String creditAmtFirstAcc = driver
					.findElement(By.xpath("//*[@class='expanderContainer1']/div[3]/span[26]/following::span[3]/div"))
					.getText();
			String SecndCreditAcc = driver.findElement(By.xpath("//*[@class='expanderContainer1']/div[3]/span[31]"))
					.getText();
			String creditAmtSecndAcc = driver
					.findElement(By.xpath("//*[@class='expanderContainer1']/div[3]/span[32]/following::span[3]/div"))
					.getText();
			String lastTaxCreditAcc = driver.findElement(By.xpath("//*[@class='expanderContainer1']/div[3]/span[37]"))
					.getText();
			String lastTaxCreditAmt = driver
					.findElement(By.xpath("//*[@class='expanderContainer1']/div[3]/span[37]/following::span[4]/div"))
					.getText();

			String actualJEResult[] = { FirstAcc, DebitAmtFirstAcc, SecAcc, DebitAmtSecAcc, ThirdAcc, DebitAmtThirdAcc,
					SecondAccTax, DebitAmtSecondAccTax, FirstCreditAcc, creditAmtFirstAcc, SecndCreditAcc,
					creditAmtSecndAcc };
			String expJEResult[] = { "Trade Debtors ", "SGD 365.00", "Discount Given", "SGD 35.00",
					"Rounding Difference ", "SGD 0.02", "To Sales ", "SGD 188.23", "To GST(DS)@7.00% ", "SGD 11.78",
					"To Sales ", "SGD 187.90", };

			for (int i = 0; i < expJEResult.length; i++) {
				if (actualJEResult[i].contains(expJEResult[i])) {
					System.out.println("******** Matched UI - [" + actualJEResult[i] + "] with expected data ["
							+ expJEResult[i] + "] Successfully ********");
				} else {
					System.out.println("!!!!!!!! NOT Matched UI - [" + actualJEResult[i] + "] with expected data ["
							+ expJEResult[i] + "] CHECK CONSOLE !!!!!!!!");
					flag = false;
				}
			}
			if (flag == false) {
				System.out.println(
						"!!!!!!!! JE not verified for document [" + documentid + "] plz check Console !!!!!!!!\n");
			} else {
				System.out.println(
						"******** JE is Verified for document [" + documentid + "] plz check Console ******** \n");
			}

			// Compare Credit & Debit Ammount
			double acc1Debit = Double.parseDouble(DebitAmtFirstAcc.substring(DebitAmtFirstAcc.indexOf("D") + 2));
			double acc2Debit = Double.parseDouble(DebitAmtSecAcc.substring(DebitAmtSecAcc.indexOf("D") + 2));
			double acc3Debit = Double.parseDouble(DebitAmtThirdAcc.substring(DebitAmtThirdAcc.indexOf("D") + 2));

			// convert Credit amt
			double acc1Credit = Double
					.parseDouble(DebitAmtSecondAccTax.substring(DebitAmtSecondAccTax.indexOf("D") + 2));
			double acc2Credit = Double.parseDouble(creditAmtFirstAcc.substring(creditAmtFirstAcc.indexOf("D") + 2));
			double acc3Credit = Double.parseDouble(creditAmtSecndAcc.substring(creditAmtSecndAcc.indexOf("D") + 2));
			double acc4Credit = Double.parseDouble(lastTaxCreditAmt.substring(lastTaxCreditAmt.indexOf("D") + 2));

			double AmountCredit = acc1Credit + acc2Credit + acc3Credit + acc4Credit;
			double AmountDebit = acc1Debit + acc2Debit + acc3Debit;

			if (AmountDebit == AmountCredit) {
				System.out.println("******** Matched Debit amount [" + AmountDebit + "] with Credit amount ["
						+ AmountCredit + "] for document No. [" + documentid + "] ********");
			} else {
				System.out.println("!!!!!!!! FAILED to Match Debit amount [" + AmountDebit + "] with Credit amount ["
						+ AmountCredit + "] for document No. [" + documentid + "] !!!!!!!!");
				flag = false;
			}

			if (flag == false) {
				driver.navigate().refresh();
				Assert.assertTrue(false);
				System.out.println("******* JE report for Sales Invoice report Failed to Verify for :[" + documentid
						+ "] checlk LOG *******");
			}
			Utilities.HoverandClick("//li[@id='as__InvoiceListMainTabEntry']/a[1]", driver);
			Utilities.HoverandClick("//li[@id='as__JEMainTab']/a[1]", driver);
			System.out.println("******* Sales Invoice report & JE report is VERIFIED for :[" + documentid
					+ "] checlk LOG *******");
		} catch (Exception EX) {
			System.out.println(
					"******* Sales Invoice report Failed to Verify for :[" + documentid + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// ********************* Age Receible **********************************
	public static void verify_SI_AgeReceivable(String documentid, String customerid, String expectedAmtDue,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_AgingReportCustomer.properties");
			Utilities.waitandClick(pro.getProperty("viewAgedreceivablesIcon"), driver);

			Utilities.click_element(pro.getProperty("ReportViewTab"), driver);
			Utilities.enterTextandSelect(customerid, pro.getProperty("SelectCustomerName"), driver);
			Utilities.click_element(pro.getProperty("ReportDrpDwnArrow"), driver);
			Utilities.click_element(pro.getProperty("ReportFetchBtn"), driver);
			Utilities.isLoadingDisappear(120, driver);

			int headerSize = Utilities.totalSize("//*[text()='Amount Due']/ancestor::tr/td/div", driver);
			int indOfamtDue = 0;
			boolean flag = true;
			for (int i = 1; i <= headerSize; i++) {
				String headName = driver
						.findElement(By.xpath("//*[text()='Amount Due']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headName.equalsIgnoreCase("Amount Due")) {
					indOfamtDue = i;
				}
			}
			indOfamtDue = indOfamtDue - 3; // Workaround to Handle more col
											// issues
			String amountDue = driver
					.findElement(By.xpath("//*[text()='" + documentid
							+ "']/ancestor::tr//td[contains(@style,'text-align:right')]//*[contains(text(),'SGD') and @class='currency']"))
					.getText();

			if (amountDue.equalsIgnoreCase(expectedAmtDue)) {
				System.out.println(
						"******* Verified Age Payable report for SI [ " + documentid + " ] with amount due of [ "
								+ amountDue + " ] Expected Output : [ " + expectedAmtDue + " ] *******");
			} else {
				System.out.println("******* FAILED TO VERIFY Age Payable report for SI [ " + documentid
						+ " ] with amount due of [ " + amountDue + " ] Expected Output : [ " + expectedAmtDue
						+ " ] *******");
				flag = false;
			}

			if (flag == false) {
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			System.out.println(
					"********** Successfully Verified Age Payable report for SI [ " + documentid + "] : ) **********");
			Utilities.HoverandClick(pro.getProperty("CloseReport"), driver);

		} catch (Exception EX) {
			System.out.println(
					"!!!!!!!!! Failed to Verify Balancesheet report for SI [ " + documentid + "] : ) !!!!!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

}
