package krawler.erp.modules;

import java.awt.AWTException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import krawler.erp.page.EmailFunctionality;
import krawler.erp.page.Utilities;
import krawler.erp.testCases.VisualAspect.PurchaseModule;
import krawler.erp.utils.MasterConfiguration;

public class VisualAspectCashPurchase {
	// private static String JEnumber="JE000411";

	// ------ Create Cash Purcashe with discount,
	public static void create_CashPurchase(String documentId, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentId = "CP" + documentId;
			Properties pro = Utilities.fetchProValue("OR_CashPurchase.properties");

			Utilities.waitandClick(pro.getProperty("CreateCashPurchaseIcon"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("vendorName"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentId, pro.getProperty("vendorInvoiceNo"), driver);
			Utilities.enterTextandSelect("US Dollars (USD)", pro.getProperty("currencyType"), driver);
			Utilities.enterTextandSelect("Yes", pro.getProperty("includeproducttax"), driver);
			Utilities.HoverandClick(pro.getProperty("memo"), driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("productId"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexQty = 0, indOfUprice = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='purchasereceipteditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Quantity")) {
					indexQty = i;
				}
				if (HeadeName.equalsIgnoreCase("Unit Price")) {
					indOfUprice = i;
				}
			}
			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='purchasereceipteditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[" + i
								+ "]/table/tbody/tr/td[" + indexQty + "]/div",
						driver);
				Utilities.enter_LinelabelAmount("1", driver);
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
			}

			// Scroll line label
			Utilities.justHover("//*[@class='pwnd delete-gridrow']", driver);

			// enter line level Tax
			String taxNme[] = { "GST(BL)@7.00%", "GST(IM)@7.00%" };
			int sizeOfArr = taxNme.length;
			int indexTax = 0, indOfdisc = 0, indOftypdisc = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='purchasereceipteditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
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
					"//div[@id='purchasereceipteditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfdisc + "]/div",
					driver);
			Utilities.enter_LinelabelAmount("10", driver);

			Utilities.HoverandClick(
					"//div[@id='purchasereceipteditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[2]/table/tbody/tr/td["
							+ indOftypdisc + "]/div",
					driver);
			Utilities.enterTextandSelect("Flat", "//*[@name='typeid']", driver);
			Utilities.HoverandClick(
					"//div[@id='purchasereceipteditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfdisc + "]/div",
					driver);
			Utilities.enter_LinelabelAmount("30", driver);
			Utilities.HoverandClick(pro.getProperty("memo"), driver);

			// apply tax
			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='purchasereceipteditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[" + i
								+ "]/table/tbody/tr/td[" + indexTax + "]/div",
						driver);
				Thread.sleep(1000);

				if (i <= sizeOfArr) {
					Utilities.enterTextInDropDown(taxNme[i - 1], "//input[@id='prtaxid']/following::input[1]", driver);
				} else {
					int index = getArrayIndex(i, sizeOfArr);
					Utilities.enterTextInDropDown(taxNme[index - 1], "//input[@id='prtaxid']/following::input[1]",
							driver);
				}
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
			}

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

			Utilities.HoverandClick(pro.getProperty("clickOk"), driver);

			System.out.println("******* Cash Purchase : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Cash Purchase is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// **************************************** Verify CP or PI Report
	// ****************************************
	public static void verify_CashPurchaseORInvoice(String documentid, String expectedResultCP[], String debitAccouts[],
			String expectedDebitAmt[], String creditAccouts[], String expectedCreditAmt[], WebDriver driver)
			throws IOException, InterruptedException, AWTException {
		String getJEnumber = null;
		try {
			Properties pro = Utilities.fetchProValue("OR_CashPurchase.properties");
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";
			boolean flag = true;
			Utilities.HoverandClick(pro.getProperty("SalesInvoiceReport"), driver);

			Utilities.isElementGone(xpathOfLoading, 180, driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.isLoadingDisappear(180, driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			// TO USE Generated JE for next documents
			getJEnumber = driver
					.findElement(By
							.xpath("//*[text()='Purchase Invoice No']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table//td//*[@class='jumplink' and contains(text(),'JE')]"))
					.getText();
			PurchaseModule.JEnumber = getJEnumber;
			// scroll
			Utilities.justHover(
					"//*[text()='Purchase Invoice No']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[contains(@class,'td-memoundefinedGRListEntry')]",
					driver);
			int headerSize = Utilities.totalSize("//*[text()='Purchase Invoice No']/ancestor::tr/td/div", driver);
			int indOfdis = 0, indOftax = 0, totalAmt = 0, totalBaseCurrAmnt = 0, indOfJE = 0;

			for (int i = 1; i < headerSize; i++) {
				String headerName = driver
						.findElement(By.xpath("//*[text()='Purchase Invoice No']/ancestor::tr/td[" + i + "]/div"))
						.getText();
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
					.xpath("//*[text()='Purchase Invoice No']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr[1]/td["
							+ indOfdis + "]/div"))
					.getText();
			String taxAmt = driver.findElement(By
					.xpath("//*[text()='Purchase Invoice No']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr[1]/td["
							+ indOftax + "]/div"))
					.getText();
			String TotalAmt = driver.findElement(By
					.xpath("//*[text()='Purchase Invoice No']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr[1]/td["
							+ totalAmt + "]/div"))
					.getText();
			String TotalAmt2 = driver.findElement(By
					.xpath("//*[text()='Purchase Invoice No']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr[1]/td["
							+ totalBaseCurrAmnt + "]/div"))
					.getText();

			String head[] = { "Discount", "Tax Amount", "Total Amount", "Total Amount (In Base Currency)" };
			String actualResultCP[] = { discount, taxAmt, TotalAmt, TotalAmt2 };

			for (int i = 0; i < actualResultCP.length; i++) {
				if (actualResultCP[i].equalsIgnoreCase(expectedResultCP[i])) {
					System.out.println("*** Matched [" + head[i] + "] as UI amount:[" + actualResultCP[i]
							+ "] with expected amount [" + expectedResultCP[i] + "] ***");
				} else {
					System.out.println("*** FAILED to Match [" + head[i] + "] as UI amount:[" + actualResultCP[i]
							+ "] with expected amount [" + expectedResultCP[i] + "] ***");
					System.out.println(
							"******* Cash Purchase is NOT Verified for :[" + documentid + "] checlk LOG *******");
					flag = false;
				}
			}
			System.out.println("\n");

			// --------- Open JE report ---------
			Utilities.HoverandClick(
					"//*[text()='Purchase Invoice No']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody//*[@class='jumplink' and contains(text(),'JE')]",
					driver);
			Utilities.clickCheckBox("//*[@class='jumplink' and text()='" + documentid + "']", "uncheck", driver); // form
																													// loaded
																													// wait
			Utilities.isLoadingDisappear(120, driver);

			// Debit Account ------------------
			ArrayList actualDebitAmt = new ArrayList();
			double tempDebit = 0;
			// String debitAccouts[] =
			// {"GST(BL)@7.00%","AutomationAsset","GST(IM)@7.00%","AutomationLiability"};
			for (int i = 0; i < debitAccouts.length; i++) {
				String amount = null;
				amount = driver
						.findElement(
								By.xpath("//*[contains(text(),'" + debitAccouts[i] + "')]//following::span[3]/div"))
						.getText();
				actualDebitAmt.add(amount);
				if (actualDebitAmt.get(i).equals(expectedDebitAmt[i])) {
					System.out.println("**** In JE report Verified DEBIT account [" + debitAccouts[i] + "] as UI ["
							+ actualDebitAmt.get(i) + "] amount with Expected amount [" + expectedDebitAmt[i]
							+ "] !!!!!");
				} else {
					System.out.println("**** In JE report FAILED to Verify DEBIT account [" + debitAccouts[i]
							+ "] as UI [" + actualDebitAmt.get(i) + "] amount with Expected amount ["
							+ expectedDebitAmt[i] + "] !!!!!");
					flag = false;
				}
			}

			// Credit Account ------------------
			ArrayList actualCreditAmt = new ArrayList();
			// String creditAccouts[] = {"Cash in hand","Discount
			// Received","Rounding Difference"};
			for (int i = 0; i < creditAccouts.length; i++) {
				String amount = null;
				amount = driver
						.findElement(
								By.xpath("//*[contains(text(),'" + creditAccouts[i] + "')]//following::span[4]/div"))
						.getText();
				actualCreditAmt.add(amount);
				if (actualCreditAmt.get(i).equals(expectedCreditAmt[i])) {
					System.out.println("**** In JE report Verified CREDIT account [" + creditAccouts[i] + "] as UI ["
							+ actualCreditAmt.get(i) + "] amount with Expected amount [" + expectedCreditAmt[i]
							+ "] !!!!!");
				} else {
					System.out.println("**** In JE report FAILED to Verify CREDIT account [" + creditAccouts[i]
							+ "] as UI [" + actualCreditAmt.get(i) + "] amount with Expected amount ["
							+ expectedCreditAmt[i] + "] !!!!!");
					flag = false;
				}
			}
			if (flag == false) {
				driver.navigate().refresh();
				System.out.println("!!!!!!!!!!! FAILED TO VERIFY CP & JE report for [" + documentid
						+ "] plze check Console !!!!!!!!!!!!!");
				Assert.assertTrue(false);
			}

			Utilities.HoverandClick("//li[@id='as__GRListMainTabEntry']/a[1]", driver);
			Utilities.HoverandClick("//li[@id='as__JEMainTab']/a[1]", driver);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>> Verified CP/PI [" + documentid + "] with JE number ["
					+ getJEnumber + "]<<<<<<<<<<<<<<<<<<<<<<<<<<");
		} catch (Exception EX) {
			System.out.println("******* CP/PI is NOT Verified for :[" + documentid + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// ***************************************** EXPENSE
	// **************************************************************************
	// ------ Create Cash Purcashe with EXPENSE,
	public static void create_CashPurchaseExpense(String documentid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "CpExp" + documentid;
			Properties pro = Utilities.fetchProValue("OR_CashPurchase.properties");

			Utilities.waitandClick(pro.getProperty("CreateCashPurchaseIcon"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("vendorName"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("vendorInvoiceNo"), driver);
			Utilities.enterTextandSelect("US Dollars (USD)", pro.getProperty("currencyType"), driver);
			Utilities.HoverandClick(pro.getProperty("ExpenseTab"), driver);
			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickCheckBox(pro.getProperty("includingGST"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("memo"), driver);
			Utilities.clickButton("Yes", 500, driver);

			// add acc
			int headerSize = Utilities.totalSize("//*[text()='Account']/ancestor::tr/td/div", driver);
			int indOfAcc = 0, indOfamt = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver.findElement(By.xpath("//*[text()='Account']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Account")) {
					indOfAcc = i;
				}
				if (headerName.equalsIgnoreCase("Amount")) {
					indOfamt = i;
				}
			}

			String accounts[] = { "AutomationAsset", "AutomationLiability" };
			String ammount[] = { "400", "200" };

			// add 1st Account
			for (int i = 0; i < accounts.length; i++) {
				Utilities.HoverandClick("//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[" + (i + 1)
						+ "]/table/tbody/tr/td[" + indOfAcc + "]/div", driver);
				Utilities.enterTextandSelect(accounts[i], "//*[@id='accountid']/following::input[1]", driver);
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
				Utilities.HoverandClick("//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[" + (i + 1)
						+ "]/table/tbody/tr/td[" + indOfamt + "]/div", driver);
				Utilities.enter_LinelabelAmount(ammount[i], driver);
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
			}

			// Scroll line label
			Utilities.justHover("//*[@id='purchasereceiptexpensegrid']//*[@class='pwnd delete-gridrow']", driver);
			// enter line level Tax
			String taxNme[] = { "GST(BL)@7.00%", "GST(IM)@7.00%" };
			int sizeOfArr = taxNme.length;
			int indexTax = 0, indOfdisc = 0, indOftypdisc = 0;
			for (int i = 1; i <= headerSize; i++) {
				String HeadeName = driver.findElement(By.xpath("//*[text()='Account']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (HeadeName.equalsIgnoreCase("Tax")) {
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
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td[" + indOfdisc
							+ "]/div",
					driver);
			Utilities.enter_LinelabelAmount("10", driver);

			Utilities.HoverandClick(
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div/div[2]/table/tbody/tr/td["
							+ indOftypdisc + "]/div",
					driver);
			Utilities.enterTextandSelect("Flat", "//*[@name='typeid']", driver);
			Utilities.HoverandClick(
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div/div[2]/table/tbody/tr/td[" + indOfdisc
							+ "]/div",
					driver);
			Utilities.enter_LinelabelAmount("30", driver);
			Utilities.HoverandClick(pro.getProperty("memo"), driver);

			// apply tax
			for (int i = 1; i <= accounts.length; i++) {
				Utilities.HoverandClick("//*[text()='Account']/ancestor::div[3]/following::div[1]/div/div[" + i
						+ "]/table/tbody/tr/td[" + indexTax + "]/div", driver);
				Thread.sleep(1000);

				if (i <= sizeOfArr) {
					Utilities.enterTextInDropDown(taxNme[i - 1], "//input[@id='prtaxid']/following::input[1]", driver);
				} else {
					int index = getArrayIndex(i, sizeOfArr);
					Utilities.enterTextInDropDown(taxNme[index - 1], "//input[@id='prtaxid']/following::input[1]",
							driver);
				}
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
			}

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			String totalAmount = driver.findElement(By.xpath("//*[text()='Total Amount: ']/ancestor::tr/td[2]/div"))
					.getText();
			// System.out.println("Total Amount: "+totalAmount);
			String totalAmountInBaseCurr = driver
					.findElement(By.xpath("//*[text()='Total Amount(In Base Currency): ']/ancestor::tr/td[2]/div"))
					.getText();
			// System.out.println("Total Amount(In Base Currency):
			// "+totalAmountInBaseCurr);

			if (totalAmount.equalsIgnoreCase("$ 530.00") && totalAmountInBaseCurr.equalsIgnoreCase("SGD 265.00")) {
				System.out.println("******* Cash Purchase Expense: [" + documentid
						+ "] is Created & verified total on Form !!!!!! ********");
				Utilities.HoverandClick(pro.getProperty("clickOk"), driver);
			} else {
				System.out.println(
						"******* Cash Purchase Expense: [" + documentid + "] is NOT Verified on form !!!!!!!!!!!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
		} catch (Exception EX) {
			System.out.println(
					"!!!!!!!!! Failed to create Cash Purchase Expense for [ " + documentid + "] : ) !!!!!!!!!");
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

}
