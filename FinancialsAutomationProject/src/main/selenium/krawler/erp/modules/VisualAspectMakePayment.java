package krawler.erp.modules;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import krawler.erp.page.EmailFunctionality;
import krawler.erp.page.Utilities;

public class VisualAspectMakePayment {

	// --------------------------- Against Vendor ---------------------------
	public static void createMP_vendor(String documentid, String vendorid, String documentTypes[],
			String linkDocument[], String amount, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("MakePaymentAgainstVendorCheck"), driver);
			Utilities.HoverandClick(pro.getProperty("SubmitButton1"), driver);
			Thread.sleep(2000);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("vendorID"), driver);
			Utilities.enterTextandSelect("US Dollars (USD)", "//*[@id='currencyundefinedpaymentwindow']", driver);
			Utilities.clickButton("Yes", 500, driver);
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

			for (int i = 0; i < documentTypes.length; i++) {
				Utilities.HoverandClick("//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div["
						+ (i + 1) + "]/table/tbody/tr/td[" + indOfdocType + "]/div", driver);
				Utilities.enterTextandSelect(documentTypes[i], "//*[@name='type']/following::input[1]", driver);
				Utilities.click_element(pro.getProperty("Memo"), driver);
				// Enter Amount for adv
				if (documentTypes[i].contains("Advanced / Deposit")) {
					Utilities.HoverandClick("//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div["
							+ (i + 1) + "]/table/tbody/tr/td[" + indOfAmount + "]/div", driver);
					Utilities.enter_LinelabelAmount(amount, driver);
					Utilities.click_element(pro.getProperty("Memo"), driver);
				}

				if (documentTypes[i].contains("Invoice")) {
					// link invoice
					Utilities.HoverandClick("//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div["
							+ (i + 1) + "]/table/tbody/tr/td[" + indOfdocNum + "]/div", driver);
					Utilities.enterTextNormalBox(linkDocument[i], pro.getProperty("QuickSearchInvoice"), driver);
					Utilities.click_element("//button[text()='Fetch']", driver);
					Utilities.isLoadingDisappear(120, driver);
					Utilities.click_element(pro.getProperty("SelectInvoice"), driver);
					Utilities.click_element(pro.getProperty("SubmitButton1"), driver);
					// Enter Amount
					Utilities.HoverandClick("//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div["
							+ (i + 1) + "]/table/tbody/tr/td[" + indOfAmount + "]/div", driver);
					Utilities.enter_LinelabelAmount(amount, driver);
					Utilities.click_element(pro.getProperty("Memo"), driver);
				}

				if (documentTypes[i].contains("Credit Note")) {
					// link CN
					Utilities.HoverandClick("//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div["
							+ (i + 1) + "]/table/tbody/tr/td[" + indOfdocNum + "]/div", driver);
					Utilities.enterTextNormalBox(linkDocument[i], pro.getProperty("QuickSearchCreditNote"), driver);
					Utilities.click_element("//button[text()='Fetch']", driver);
					Utilities.isLoadingDisappear(120, driver);
					Utilities.click_element(pro.getProperty("SelectCreditNote"), driver);
					Utilities.click_element(pro.getProperty("SubmitButton1"), driver);
					// Enter Amount
					Utilities.HoverandClick("//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div["
							+ (i + 1) + "]/table/tbody/tr/td[" + indOfAmount + "]/div", driver);
					Utilities.enter_LinelabelAmount(amount, driver);
					Utilities.click_element(pro.getProperty("Memo"), driver);
				}

				if (documentTypes[i].contains("General Ledger Code")) {
					Utilities.HoverandClick("//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div["
							+ (i + 1) + "]/table/tbody/tr/td[" + indOfdocNum + "]/div", driver);
					Utilities.enterTextInDropDown(linkDocument[i], "//input[@id='quickSearchaccountinfowindow']",
							driver);
					Utilities.isLoadingDisappear(100, driver);
					String xpathSEARChaccount = "//*[text()='Account Name']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td[1]/div/div";
					Utilities.click_element(xpathSEARChaccount, driver);
					Utilities.click_element(pro.getProperty("SubmitButton1"), driver);
					// Enter Amount
					Utilities.HoverandClick("//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div["
							+ (i + 1) + "]/table/tbody/tr/td[" + indOfAmount + "]/div", driver);
					Utilities.enter_LinelabelAmount(amount, driver);
					Utilities.click_element(pro.getProperty("Memo"), driver);
				}
			} // for loop

			Utilities.HoverandClick(pro.getProperty("SaveButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);

			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait')]";
			Utilities.isElementGone(xpathOfLoading, 180, driver);
			Utilities.clickButton("OK", 0, driver);
			System.out.println("*** Make Payment against Vendor is Pass for doc : -> [ " + documentid + "] ******");
			Utilities.click_element(pro.getProperty("CloseMakePayment"), driver);
		} catch (Exception Ex) {
			System.out.println("!!!!! Make Payment against Vendor is FAILEDD for doc : -> [" + documentid + "] !!!!!");
			Utilities.handleError(Ex, driver);
		}
	}

	// ************************** Verify make Payment & get JE
	// ******************************************************
	public static String getJEverify_makePayment(String documentid, double expectedAmount[], WebDriver driver) {
		String myJEnumber = null;
		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");
			Utilities.click_element(pro.getProperty("PaymentMadeReport"), driver);
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";
			Utilities.isElementGone(xpathOfLoading, 180, driver);

			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.click_element(pro.getProperty("DocumentCheckBox"), driver);
			// get JE number
			myJEnumber = driver
					.findElement(
							By.xpath("//*[text()='" + documentid + "']/ancestor::tr[1]/td//*[contains(text(),'JE')]"))
					.getText();

			int headerCnt = Utilities.totalSize("//*[text()='Payment No']/ancestor::tr/td/div", driver);
			int indOfamtDue = 0, indOfamtPaid = 0;
			for (int i = 1; i <= headerCnt; i++) {
				try {
					Actions action = new Actions(driver);
					WebElement hover = driver
							.findElement(By.xpath("//*[text()='Payment No']/ancestor::tr/td[" + i + "]/div"));
					action.moveToElement(hover).build().perform();
					String myName = driver
							.findElement(By.xpath("//*[text()='Payment No']/ancestor::tr/td[" + i + "]/div")).getText();
					// System.out.println(myName);
					if (myName.equalsIgnoreCase("Amount Due")) {
						indOfamtDue = i;
					}
					if (myName.equalsIgnoreCase("Amount Paid")) {
						indOfamtPaid = i;
					}
				} catch (Exception No) {
					// skip blank field
				}
			}

			String actualAmountDue = driver.findElement(By
					.xpath("//*[text()='Payment No']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfamtDue + "]/div"))
					.getText();
			String actualAmountPaid = driver.findElement(By
					.xpath("//*[text()='Payment No']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfamtPaid + "]/div"))
					.getText();
			// System.out.println(actualAmountDue);
			// System.out.println(actualAmountPaid);
			boolean flag = true;
			String headerName[] = { "Amount Due", "Amount Paid" };
			double actualData[] = { Utilities.getIntegerFrmString(actualAmountDue),
					Utilities.getIntegerFrmString(actualAmountPaid) };

			for (int i = 0; i < expectedAmount.length; i++) {
				if (actualData[i] == expectedAmount[i]) {
					System.out.println("**** Verified [" + headerName[i] + "] as UI amount [" + actualData[i]
							+ "] with Expected amount [" + expectedAmount[i] + "] *****");
				} else {
					System.out.println("!!!!!! FAILED to Verify [" + headerName[i] + "] as UI amount [" + actualData[i]
							+ "] with Expected amount [" + expectedAmount[i] + "] !!!!!!");
					flag = false;
				}
			}

			if (flag == false) {
				System.out.println("!!!!!! FAILED to Verify [" + documentid + "] Make Payment Report !!!!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			System.out.println("!!!!!! Verified [" + documentid + "] Make Payment Report !!!!!!");
			Utilities.click_element("//li[@id='as__PaymentMainTabPanel']/a[1]", driver);
		} catch (Exception Ex) {
			System.out.println("!!!!!! FAILED to Verify [" + documentid + "] Make Payment Report !!!!!!");
			Ex.printStackTrace();
		}
		return myJEnumber;
	}

	// --------------------------- Against Customer ---------------------------
	public static double createMP_customer(String documentid, String customerid, String documentTypes[],
			String linkDocument[], String amount, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		double totalpaidAmt = 0;
		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstCustomer.properties");
			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.click_element(pro.getProperty("MakePaymentAgainstCustomer"), driver);
			Utilities.click_element(pro.getProperty("SubmitButton1"), driver);
			Thread.sleep(2000);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect(customerid, pro.getProperty("vendorID"), driver);
			Utilities.enterTextandSelect("US Dollars (USD)", "//*[@id='currencyundefinedpaymentwindow']", driver);
			Utilities.clickButton("Yes", 500, driver);
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

			for (int i = 0; i < documentTypes.length; i++) {
				Utilities.HoverandClick("//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div["
						+ (i + 1) + "]/table/tbody/tr/td[" + indOfdocType + "]/div", driver);
				Utilities.enterTextandSelect(documentTypes[i], "//*[@name='type']/following::input[1]", driver);
				Utilities.click_element(pro.getProperty("Memo"), driver);
				// Enter Amount for adv
				if (documentTypes[i].contains("Refund / Deposit")) {
					Utilities.HoverandClick("//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div["
							+ (i + 1) + "]/table/tbody/tr/td[" + indOfAmount + "]/div", driver);
					Utilities.enter_LinelabelAmount(amount, driver);
					Utilities.click_element(pro.getProperty("Memo"), driver);
				}

				if (documentTypes[i].contains("Credit Note")) {
					// link CN
					Utilities.HoverandClick("//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div["
							+ (i + 1) + "]/table/tbody/tr/td[" + indOfdocNum + "]/div", driver);
					Utilities.enterTextNormalBox(linkDocument[i], pro.getProperty("QuickSearchCreditNote"), driver);
					Utilities.click_element("//button[text()='Fetch']", driver);
					Utilities.isLoadingDisappear(120, driver);
					Utilities.click_element(pro.getProperty("SelectCreditNote"), driver);
					Utilities.click_element(pro.getProperty("SubmitButton1"), driver);
					// Enter Amount
					Utilities.HoverandClick("//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div["
							+ (i + 1) + "]/table/tbody/tr/td[" + indOfAmount + "]/div", driver);
					Utilities.enter_LinelabelAmount(amount, driver);
					Utilities.click_element(pro.getProperty("Memo"), driver);
				}
			} // for loop

			Utilities.HoverandClick(pro.getProperty("SaveButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			try {
				driver.findElement(By.xpath("//*[text()='Yes']")).click();
			} catch (Exception creditLimit) {
				// continue
			}
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait')]";
			Utilities.isElementGone(xpathOfLoading, 180, driver);
			Utilities.clickButton("OK", 0, driver);

			String totalAmount = driver.findElement(By.xpath("//*[text()='Amount Paid:']/ancestor::tr/td[2]/div"))
					.getText();
			System.out.println("Total Amount: " + totalAmount);

			totalpaidAmt = Utilities.getIntegerFrmString(totalAmount);
			System.out.println("*** Make Payment against Vendor is Pass for doc : -> [ " + documentid + "] ******");
			Utilities.click_element(pro.getProperty("CloseMakePayment"), driver);
		} catch (Exception Ex) {
			System.out.println("!!!!! Make Payment against Vendor is FAILEDD for doc : -> [" + documentid + "] !!!!!");
			Utilities.handleError(Ex, driver);
		}
		return totalpaidAmt;
	}

	// --------------------------- Against General Ledeger
	// ---------------------------
	public static double createMP_GL(String documentid, String paidTo, String documentTypes[], String linkDocument[],
			String amount, WebDriver driver) throws InterruptedException, AWTException, IOException {
		double totalpaidAmt = 0;
		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstGL.properties");
			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.click_element(pro.getProperty("AgainstGL"), driver);
			Utilities.click_element(pro.getProperty("Submitbtn"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("SeqFormat"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("paymentNumber"), driver);
			Utilities.enterTextandSelect("US Dollars (USD)", "//*[@id='currencyundefinedpaymentwindow']", driver);
			Utilities.clickButton("Yes", 500, driver);
			Utilities.enterTextandSelect("Cash", pro.getProperty("PaymentMethod"), driver);
			Utilities.click_element(pro.getProperty("Memo"), driver);
			// Select paid TO
			Utilities.click_element(pro.getProperty("paidToarrow"), driver);
			Utilities.click_element("//*[contains(@style,'visible')]//*[text()='" + paidTo + "']", driver);

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

			for (int i = 0; i < documentTypes.length; i++) {
				Utilities.HoverandClick("//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div["
						+ (i + 1) + "]/table/tbody/tr/td[" + indOfdocType + "]/div", driver);
				Utilities.enterTextandSelect(documentTypes[i], "//*[@name='type']/following::input[1]", driver);
				Utilities.click_element(pro.getProperty("Memo"), driver);

				if (documentTypes[i].contains("General Ledger Code")) {
					Utilities.HoverandClick("//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div["
							+ (i + 1) + "]/table/tbody/tr/td[" + indOfdocNum + "]/div", driver);
					String xpathSEARChaccount = "//*[text()='Account Name']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td[1]/div/div";
					Utilities.clickCheckBox(xpathSEARChaccount, "check", driver);
					Utilities.enterTextInDropDown(linkDocument[i], "//input[@id='quickSearchaccountinfowindow']",
							driver);
					Utilities.isLoadingDisappear(100, driver);
					Utilities.click_element("//*[text()='" + linkDocument[i]
							+ "']/../preceding-sibling::td[1]//*[@class='x-grid3-row-checker']", driver);
					Utilities.click_element(pro.getProperty("Submitbtn"), driver);
					// Enter Amount
					Utilities.HoverandClick("//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div["
							+ (i + 1) + "]/table/tbody/tr/td[" + indOfAmount + "]/div", driver);
					Utilities.enter_LinelabelAmount(amount, driver);
					Utilities.click_element(pro.getProperty("Memo"), driver);
				}
			} // for loop

			Utilities.HoverandClick(pro.getProperty("savebtn"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait')]";
			Utilities.isElementGone(xpathOfLoading, 180, driver);
			Utilities.clickButton("OK", 0, driver);

			String totalAmount = driver.findElement(By.xpath("//*[text()='Amount Paid:']/ancestor::tr/td[2]/div"))
					.getText();
			System.out.println("Total Amount: " + totalAmount);
			totalpaidAmt = Utilities.getIntegerFrmString(totalAmount);
			System.out.println("*** Make Payment against GL is Pass for doc : -> [ " + documentid + "] ******");
			Utilities.click_element(pro.getProperty("CloseTabBtn"), driver);
		} catch (Exception Ex) {
			System.out.println("!!!!! Make Payment against GL is FAILED for doc : -> [" + documentid + "] !!!!!");
			Utilities.handleError(Ex, driver);
		}
		return totalpaidAmt;
	}

}
