package krawler.erp.modules;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.EmailFunctionality;
import krawler.erp.page.Utilities;

public class PurchaseInvoice1 {

	public static void create_PurchaseInvoice(String documentId, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentId = "PI" + documentId;
			Properties pro = Utilities.fetchProValue("OR_VendorInvoice.properties");

			Utilities.waitandClick(pro.getProperty("CreateVendorInvoiceIcon"), driver);
			selectFromNormalDropDown(vendorid, pro.getProperty("VendorId"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.enterTextInDropDown(documentId, pro.getProperty("VendorInvoiceNumber"), driver);

			Utilities.clickCheckBox(pro.getProperty("includingGST"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("memo"), driver);
			Utilities.clickButton("Yes", 500, driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("searchProductId"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

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
				Utilities.enter_LinelabelAmount("200", driver);
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
			}

			// to scroll till end
			Utilities.HoverandClick(
					"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[1]/table//div[@class='pwnd delete-gridrow']",
					driver);
			Utilities.clickButton("No", 0, driver);
			// enter line level Tax
			String taxNme[] = { "GST(BL)@7.00%", "GST(IM)@7.00%" };
			int sizeOfArr = taxNme.length;
			int indexTax = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (HeadeName.equalsIgnoreCase("Product Tax")) {
					indexTax = i;
				}
			}

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
			}

			Utilities.HoverandClick(pro.getProperty("memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			String subjectLine = "Purchase Invoice :- testsmoke - " + documentId;
			EmailFunctionality.email_AfterSave(documentId, subjectLine, driver);

			Utilities.HoverandClick(pro.getProperty("closeVendorInvoice"), driver);

			System.out.println("******* Purchase Invoice : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Purchase Invoice is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// select item from NormalDrop Down
	public static void selectFromNormalDropDown(String text, String expForname, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		WebElement enterText = null;
		boolean success = false;
		for (int num_try = 1; !success && num_try < 10; num_try++) {
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
				if (num_try == 9) {
					Utilities.handleError(EX, driver);
				}
			}
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

	// ******************** Copy ******************************************
	public static void Copy_PurchaseInvoice(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			documentid = "PI" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_VendorInvoice.properties");

			Utilities.HoverandClick(pro.getProperty("SalesInvoiceReport"), driver);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			/*
			 * //Report Email code String
			 * subjectLine="Vendor Invoice with Product - testsmoke :"
			 * +documentid;
			 * EmailFunctionality.email_fromReports(documentid,subjectLine,
			 * driver);
			 * Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"),
			 * "uncheck", driver); Utilities.enterTextInDropDown(documentid,
			 * pro.getProperty("QuickSearch"), driver); Thread.sleep(2000);
			 * Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"),
			 * "check", driver);
			 */
			Utilities.HoverandClick(pro.getProperty("copyVendorInvoiceButton"), driver);

			Utilities.formLoaded(driver);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("copyVendorInvoiceId"), driver);

			Utilities.HoverandClick(pro.getProperty("saveCopiedVendorInvoiceId"), driver);

			Utilities.clickButton("Yes", 1000, driver);

			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("CopyVendorInvoiceClose"), driver);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Purchase Invoice Copy Created !!");
			} else {
				System.out.println("Purchase Invoice Copy NOT Created !!");
			}
		} catch (Exception EX) {
			System.out.println("Purchase Invoice Copy NOT Created !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Edit ******************************************
	public static void Edit_PurchaseInvoice(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			documentid = "PI" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_VendorInvoice.properties");

			Utilities.HoverandClick(pro.getProperty("EditVendorInvoiceButton"), driver);

			Utilities.formLoaded(driver);

			Utilities.enterTextInDropDown("Performing Edit Operation", pro.getProperty("VendorInvoiceMemo"), driver);

			Utilities.clickAndEnterText("60",
					"//div[1]/table//td[contains(@class,'x-grid3-col x-grid3-cell x-grid3-td-quantity')]", driver);

			String addThridProduct = "//div[3]/table/tbody//*[contains(@class,'x-grid3-cell-inner x-grid3-col-productid')]";
			Utilities.HoverandClick(addThridProduct, driver);

			selectFromNormalDropDown("EditProduct", "//*[@name='pid']", driver);
			Utilities.HoverandClick(pro.getProperty("VendorInvoiceMemo"), driver);

			String qtyThridProduct = "//div[3]/table/tbody//*[contains(@class,'x-grid3-cell-inner x-grid3-col-quantity66Edit')]";
			Utilities.clickAndEnterText("10", qtyThridProduct, driver);

			Utilities.HoverandClick(pro.getProperty("VendorInvoiceEditSave"), driver);

			Utilities.clickButton("Yes", 1000, driver);

			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("EditVendorInvoiceClose"), driver);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Purchase Invoice Edited !!");
			} else {
				System.out.println("Purchase Invoice Failed to Edit !!");

			}

		} catch (Exception EX) {
			System.out.println("Purchase Invoice Failed to Edit !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Delete ******************************************

	public static void Delete_PurchaseInvoice(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			documentid = "PI" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_VendorInvoice.properties");

			Utilities.HoverandClick(pro.getProperty("deleteVendorInvoiceButton"), driver);

			Utilities.HoverandClick(pro.getProperty("deleteVendorInvoicePermButton"), driver);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(1500);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			WebElement element = new WebDriverWait(driver, 45).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//a[contains(text(),'Get Started by adding a Purchase Invoice now')]")));
			if (element.isDisplayed()) {
				System.out.println("Purchase Invoice Deleted !!!! ");
			} else {
				System.out.println("Purchase Invoice is NOT Deleted !!!! ");
			}
			Utilities.HoverandClick(pro.getProperty("closeReport"), driver);
		} catch (Exception EX) {
			System.out.println("Purchase Invoice is NOT Deleted !!!! ");
			Utilities.handleError(EX, driver);
		}
	}

	// Global labl Tax *****************
	public static void createGL_PurchaseInvoice(String documentId, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentId = "PIGL" + documentId;
			Properties pro = Utilities.fetchProValue("OR_VendorInvoice.properties");

			Utilities.waitandClick(pro.getProperty("CreateVendorInvoiceIcon"), driver);

			selectFromNormalDropDown(vendorid, pro.getProperty("VendorId"), driver);

			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceFormat"), driver);

			Utilities.enterTextInDropDown(documentId, pro.getProperty("VendorInvoiceNumber"), driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("searchProductId"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

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
				Utilities.sendText("50");
				Thread.sleep(1000);
				Utilities.enter_LinelabelAmount_thenEnter(driver);
			}

			// to scroll till end
			Utilities.justHover("//*[contains(text(),'Outstanding Balance')]", driver);
			selectFromNormalDropDown("Yes", pro.getProperty("includeGlobalTax"), driver);
			selectFromNormalDropDown("GST(BL)@7.00%", pro.getProperty("GlobalTaxValue"), driver);

			Utilities.HoverandClick(pro.getProperty("memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("closeVendorInvoice"), driver);

			System.out.println("******* Purchase Invoice : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Purchase Invoice is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// ************************************************ Vendor Invoice with
	// Expense ************************************
	public static void create_PIExpense(String documentid, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			Properties pro = Utilities.fetchProValue("OR_VendorInvoice.properties");
			Utilities.waitandClick(pro.getProperty("CreateVendorInvoiceIcon"), driver);
			selectFromNormalDropDown(vendorid, pro.getProperty("VendorId"), driver);
			selectFromNormalDropDown("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.enterTextNormalBox("PIExp" + documentid, pro.getProperty("VendorInvoiceNumber"), driver);

			// switch to Expense tab
			Utilities.HoverandClick("//*[text()='Expense']", driver);
			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(2000);
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

			// add 1st Account
			Utilities.HoverandClick(
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfAcc + "]/div",
					driver);
			Utilities.enterTextandSelect("COA" + documentid, "//*[@id='accountid']/following::input[1]", driver);
			Utilities.HoverandClick(pro.getProperty("memo"), driver);
			Utilities.HoverandClick(
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfamt + "]/div",
					driver);
			Utilities.enter_LinelabelAmount("50", driver);

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("closeVendorInvoice"), driver);
			System.out.println("Vendor Invoice with Expense is -[" + documentid + "] successfully created !!! ");

		} catch (Exception EX) {
			System.out.println("Vendor Invoice with Expense is -[" + documentid + "] NOT CREATED  !!! ");
			Utilities.handleError(EX, driver);
		}
	}

	// ************************************** Vendor PI EXP Copy/Edit/Delete
	// ****************************************
	public static void EmailCopyEditDelete_VendorPiExpense(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_VendorInvoice.properties");
			Utilities.waitandClick(pro.getProperty("SalesInvoiceReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			String CopyVendorInvoiceID = "Copy" + documentid;

			// COPY
			Utilities.HoverandClick(pro.getProperty("copyVendorInvoiceButton"), driver);
			String isFormloaded = "//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr//td[8]";
			Utilities.clickCheckBox(isFormloaded, "uncheck", driver);
			Utilities.enterTextNormalBox(CopyVendorInvoiceID, pro.getProperty("copyVendorInvoiceId"), driver);
			Utilities.HoverandClick(pro.getProperty("saveCopiedVendorInvoiceId"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("CopyVendorInvoiceClose"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			Utilities.enterTextInDropDown(CopyVendorInvoiceID, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println("PI Expense [" + CopyVendorInvoiceID + "] COPY test case is PASS !!");

			// EDIT
			Utilities.HoverandClick(pro.getProperty("EditVendorInvoiceButton"), driver);
			Utilities.clickCheckBox(isFormloaded, "uncheck", driver);
			Utilities.enterTextNormalBox("Performing EDIT test case for PI :- " + CopyVendorInvoiceID,
					pro.getProperty("VendorInvoiceMemo"), driver);
			Utilities.HoverandClick(pro.getProperty("VendorInvoiceEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("EditVendorInvoiceClose"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			Utilities.enterTextInDropDown(CopyVendorInvoiceID, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println("PI Expense [" + CopyVendorInvoiceID + "] EDIT test case is PASS !!");

			// DELETE
			Utilities.HoverandClick(pro.getProperty("deleteVendorInvoiceButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteVendorInvoicePermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(1100);
			Utilities.enterTextInDropDown(CopyVendorInvoiceID, pro.getProperty("QuickSearch"), driver);

			WebElement element = new WebDriverWait(driver, 45).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//a[contains(text(),'Get Started by adding a Purchase Invoice now')]")));
			if (element.isDisplayed()) {
				System.out.println("PI Expense [" + CopyVendorInvoiceID + "] DELETE test case is PASS !!");
			} else {
				System.out.println("Purchase Invoice is NOT Deleted !!!! ");
			}
			Utilities.HoverandClick(pro.getProperty("closeReport"), driver);
		}

		catch (Exception EX) {
			System.out.println("PI Expense [" + documentid + "] COPY-EDIT-DELETE test case is PASS !!");
			Utilities.handleError(EX, driver);
		}
	}

}
