package krawler.erp.modules;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import krawler.erp.page.Utilities;

public class VisualAspectPurchaseInvoice {

	// ------ Create Cash Purcashe with discount,
	public static void create_PurchaseInvoice(String documentId, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentId = "PI" + documentId;
			Properties pro = Utilities.fetchProValue("OR_VendorInvoice.properties");

			Utilities.waitandClick(pro.getProperty("CreateVendorInvoiceIcon"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("VendorId"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentId, pro.getProperty("VendorInvoiceNumber"), driver);
			Utilities.enterTextandSelect("US Dollars (USD)", pro.getProperty("currencyType"), driver);
			Utilities.enterTextandSelect("Yes", pro.getProperty("includeproducttax"), driver);
			Utilities.HoverandClick(pro.getProperty("memo"), driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.isLoadingDisappear(45, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("searchProductId"), driver);
			Utilities.isLoadingDisappear(45, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("clickAdd"), driver);
			Utilities.isLoadingDisappear(45, driver);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexQty = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (HeadeName.equalsIgnoreCase("Quantity")) {
					indexQty = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[" + i
								+ "]/table/tbody/tr/td[" + indexQty + "]/div",
						driver);
				Utilities.enter_LinelabelAmount("1", driver);
			}

			// Scroll line label
			Utilities.justHover("//*[@class='pwnd delete-gridrow']", driver);

			// enter line level Tax
			String taxNme[] = { "GST(BL)@7.00%", "GST(IM)@7.00%" };
			int sizeOfArr = taxNme.length;
			int indexTax = 0, indOfdisc = 0, indOftypdisc = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
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
					"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfdisc + "]/div",
					driver);
			Utilities.enter_LinelabelAmount("10", driver);

			Utilities.HoverandClick(
					"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[2]/table/tbody/tr/td["
							+ indOftypdisc + "]/div",
					driver);
			Utilities.enterTextandSelect("Flat", "//*[@name='typeid']", driver);
			Utilities.HoverandClick(
					"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfdisc + "]/div",
					driver);
			Utilities.enter_LinelabelAmount("30", driver);
			Utilities.HoverandClick(pro.getProperty("memo"), driver);

			// apply tax
			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[" + i
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

			Utilities.HoverandClick(pro.getProperty("closeVendorInvoice"), driver);
			System.out.println("******* Purchase Invoice : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Purchase Invoice is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// ***************************************** EXPENSE
	// **************************************************************************
	// ------ Create Purchase Invoice with EXPENSE,
	public static void create_PIExpense(String documentid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "PiExp" + documentid;
			Properties pro = Utilities.fetchProValue("OR_VendorInvoice.properties");

			Utilities.waitandClick(pro.getProperty("CreateVendorInvoiceIcon"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("VendorId"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("VendorInvoiceNumber"), driver);
			Utilities.enterTextandSelect("US Dollars (USD)", pro.getProperty("currencyType"), driver);

			// switch to expense tab
			Utilities.HoverandClick("//*[text()='Expense']", driver);
			Utilities.clickButton("Yes", 500, driver);
			Utilities.waitandClick(pro.getProperty("includingGST"), driver);
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
			Utilities.justHover("//*[@id='goodsreceiptexpensegrid']//*[@class='pwnd delete-gridrow']", driver);
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
				System.out.println("******* Purchase Invoice Expense: [" + documentid
						+ "] is Created & verified total on Form !!!!!! ********");
				Utilities.HoverandClick(pro.getProperty("closeVendorInvoice"), driver);
			} else {
				System.out.println(
						"******* Purchase Invoice Expense: [" + documentid + "] is NOT Verified on form !!!!!!!!!!!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
		} catch (Exception EX) {
			System.out.println(
					"!!!!!!!!! Failed to create Purchase Invoice Expense for [ " + documentid + "] : ) !!!!!!!!!");
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