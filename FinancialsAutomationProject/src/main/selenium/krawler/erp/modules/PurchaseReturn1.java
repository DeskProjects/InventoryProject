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

public class PurchaseReturn1 {
	// *****************************Purchase Return
	// Only****************************************************************
	public static void create_PurchaseReturnOnly(String documentId, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentId = "PRN" + documentId;
			Properties pro = Utilities.fetchProValue("OR_PurchaseReturnOnly.properties");
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";

			Utilities.waitandClick(pro.getProperty("PurchaseReturnOnlyIcon"), driver);
			Utilities.waitandClick(pro.getProperty("buttonSubmit"), driver);
			Utilities.isElementGone(xpathOfLoading, 100, driver);

			selectFromNormalDropDown(vendorid, pro.getProperty("VendorId"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.enterTextInDropDown(documentId, pro.getProperty("PurchaseReturnOnlyNo"), driver);

			selectFromNormalDropDown("Yes", pro.getProperty("tax"), driver);
			selectFromNormalDropDown("GST(BL)@7.00%", pro.getProperty("taxName"), driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("SearchProductId"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexActQty = 0, indexRecQty = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Actual Quantity")) {
					indexActQty = i;
				}
				if (HeadeName.equalsIgnoreCase("Return Quantity")) {
					indexRecQty = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) { // add actual qty
				Utilities.HoverandClick(
						"//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexActQty + "]/div",
						driver);
				Utilities.enter_LinelabelAmount("10", driver);
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
				// add receive qty
				Utilities.HoverandClick(
						"//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexRecQty + "]/div",
						driver);
				Utilities.enter_LinelabelAmount("10", driver);
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
			}

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			String subjectLine = "Purchase Return Only :- testsmoke - " + documentId;
			EmailFunctionality.email_AfterSave(documentId, subjectLine, driver);

			Utilities.HoverandClick(pro.getProperty("ClosePurchaseReturnOnly"), driver);

			System.out.println("******* Purchase Return Only : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Purchase Return Only for :[" + documentId + "] checlk LOG *******");
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

	// ***************************** Purchase Return Debit Note
	// *******************************************
	public static void create_PurchaseReturnDebitNote(Boolean linkPI, String documentid, String productid,
			String vendorid, WebDriver driver) throws InterruptedException, AWTException, IOException {
		String documentID = null;
		try {
			Properties pro = Utilities.fetchProValue("OR_PurchaseReturnDebitNote.properties");
			int headRsize = 0;
			int totalResult = 0;

			if (linkPI == false) {
				documentID = "PRDN" + documentid;
			} else if (linkPI == true) {
				documentID = "PRdnPI" + documentid;
			}

			Utilities.waitandClick(pro.getProperty("PurchaseReturnIcon"), driver);
			Utilities.waitandClick(pro.getProperty("SelectPurchaseReturnDebit"), driver);
			Utilities.waitandClick(pro.getProperty("ClickSubmit"), driver);
			Thread.sleep(2000);

			selectFromNormalDropDown(vendorid, pro.getProperty("EnterVendorId"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextInDropDown(documentID, pro.getProperty("PurchaseReturnNo."), driver);

			Utilities.enterTextInDropDown("NA", pro.getProperty("DebitSequenceFormat"), driver);

			if (linkPI == true) {
				Utilities.enterTextNormalBox("LinkDn" + documentID, pro.getProperty("DebitNoteNo."), driver);
				selectFromNormalDropDown("Yes", pro.getProperty("linkBtn"), driver);
				selectFromNormalDropDown("Purchase Invoice", pro.getProperty("fromLink"), driver);
				Utilities.waitandClick("//input[@id='poNumberID11purchasereturn']/following-sibling::span/img[2]",
						driver);
				selectFromNormalDropDown("PI" + documentid, "//input[@name='ordernumber']/following-sibling::input",
						driver);
				Utilities.waitandClick("//input[@id='poNumberID11purchasereturn']/following-sibling::span/img[2]",
						driver);
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
				Thread.sleep(3000);
				headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
				int indexActQty = 0, indexRecQty = 0;
				for (int i = 1; i <= headRsize; i++) {
					String HeadeName = driver.findElement(By
							.xpath("//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();

					if (HeadeName.equalsIgnoreCase("Actual Quantity")) {
						indexActQty = i;
					}
					if (HeadeName.equalsIgnoreCase("Return Quantity")) {
						indexRecQty = i;
					}
				}

				for (int i = 1; i <= 2; i++) {
					// add receive qty
					Utilities.clickAndEnterText("10",
							"//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
									+ "]/table/tbody/tr/td[" + indexRecQty + "]/div",
							driver);
					Utilities.HoverandClick(pro.getProperty("Memo"), driver);
				}
			} // if Link PI

			if (linkPI == false) {
				Utilities.enterTextNormalBox("LinkDn" + documentID, pro.getProperty("DebitNoteNo."), driver);
				Utilities.HoverandClick(pro.getProperty("AddProduct"), driver);
				Utilities.isLoadingDisappear(100, driver);
				Utilities.enterTextNormalBox(productid, pro.getProperty("SearchProductId"), driver);
				Utilities.isLoadingDisappear(100, driver);
				Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
				totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
				Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
				Thread.sleep(2000);

				headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
				selectFromNormalDropDown("Yes", pro.getProperty("tax"), driver);
				selectFromNormalDropDown("GST(BL)@7.00%", pro.getProperty("taxName"), driver);

				int indexActQty = 0, indexRecQty = 0;
				for (int i = 1; i <= headRsize; i++) {
					String HeadeName = driver.findElement(By
							.xpath("//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();

					if (HeadeName.equalsIgnoreCase("Actual Quantity")) {
						indexActQty = i;
					}
					if (HeadeName.equalsIgnoreCase("Return Quantity")) {
						indexRecQty = i;
					}
				}

				for (int i = 1; i <= totalResult; i++) { // add actual qty
					Utilities.HoverandClick(
							"//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
									+ "]/table/tbody/tr/td[" + indexActQty + "]/div",
							driver);
					Utilities.enter_LinelabelAmount("10", driver);
					Utilities.HoverandClick(pro.getProperty("Memo"), driver);
					// add receive qty
					Utilities.HoverandClick(
							"//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
									+ "]/table/tbody/tr/td[" + indexRecQty + "]/div",
							driver);
					Utilities.enter_LinelabelAmount("10", driver);
					Utilities.HoverandClick(pro.getProperty("Memo"), driver);
				}
			} // end loop NO link

			// to scroll end
			Utilities.HoverandClick(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[@class='pwnd delete-gridrow']",
					driver);
			Utilities.clickButton("No", 10, driver);

			headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indOfReason = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Reason")) {
					indOfReason = i;
				}
			}
			// System.out.println(indOfReason);
			int reasonTotal = Utilities.totalSize(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[contains(@class,'x-grid3-row')]",
					driver);
			for (int j = 1; j <= reasonTotal - 1; j++) {
				Utilities.HoverandClick(
						".//*[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[" + j
								+ "]/table/tbody/tr/td[" + indOfReason + "]/div",
						driver);
				// select first created reason
				Utilities.HoverandClick("//*[@id='reason']/following::img[1]", driver);
				Utilities.HoverandClick("//*[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div[2]",
						driver);
			}

			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Save", 1000, driver);

			if (linkPI == true) {
				// if Invoice not utilize
				Utilities.clickButton("Yes", 1000, driver);
			}
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			String subjectLine = "Purchase Return DebitNote :- testsmoke - " + documentID;
			EmailFunctionality.email_AfterSave(documentID, subjectLine, driver);

			Utilities.HoverandClick(pro.getProperty("CloseForm"), driver);

			System.out.println("******* Purchase Return DebitNote : [" + documentID + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Purchase Return DebitNote for :[" + documentID + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// Copy-Edit-Delete flow
	public static void CopyEditDelete_PurchaseReturn(boolean isDNrquired, String documentid, String productid,
			WebDriver driver) throws IOException, InterruptedException, AWTException {
		try {
			Properties pro = Utilities.fetchProValue("OR_PurchaseReturnOnly.properties");
			Utilities.waitandClick(pro.getProperty("PurchaseReturnOnlyReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			String CopyPurchaseReturnID = "Copy" + documentid;

			// Copy
			Utilities.HoverandClick(pro.getProperty("copyPurchaseReturnOnlyButton"), driver);
			Utilities.formLoaded(driver);
			Utilities.enterTextNormalBox(CopyPurchaseReturnID, pro.getProperty("copyPurchaseReturnOnlyId"), driver);
			if (isDNrquired == true) {
				Utilities.enterTextNormalBox("copyDN" + documentid, "//*[@name='cndnNumber']", driver);
			}
			// select location warehouse
			for (int i = 1; i <= 2; i++) {
				Utilities.HoverandClick(
						"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[" + i
								+ "]//*[contains(@style,'px;text-align:center;')]//*[@class='pwnd serialNo-gridrow']",
						driver);
				int header = Utilities.totalSize(
						"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[1]/table/thead/tr/td/div",
						driver);
				for (int j = 1; j <= header; j++) {
					String headerName = driver.findElement(By
							.xpath("//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[1]/table/thead/tr/td["
									+ j + "]/div"))
							.getText();
					if (headerName.equalsIgnoreCase("Warehouse")) {
						Utilities.click_element(
								"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
										+ j + "]/div",
								driver);
						Utilities.enterTextandSelect("DS - Default Store",
								"//*[contains(@style,'visible')]//*[@class='x-grid3-viewport']//*[@id='warehouse']/following::input[1]",
								driver);
					}

					if (headerName.equalsIgnoreCase("Locations")) {
						Utilities.click_element(
								"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
										+ j + "]/div",
								driver);
						Utilities.enterTextandSelect("Default Location",
								"//*[contains(@style,'visible')]//*[@class='x-grid3-viewport']//*[@id='location']/following::input[1]",
								driver);
					}
				}
				Utilities.clickButton("Submit", 1500, driver);
			}
			Utilities.HoverandClick(pro.getProperty("saveCopiedPurchaseReturnOnlyId"), driver);
			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 500, driver);
			Utilities.HoverandClick(pro.getProperty("CopyPurchaseReturnOnlyClose"), driver);
			Utilities.enterTextInDropDown(CopyPurchaseReturnID, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println("Purchase Return for transaction [" + CopyPurchaseReturnID + "] COPY test case is PASS");

			// EDIT
			Utilities.HoverandClick(pro.getProperty("EditPurchaseReturnOnlyButton"), driver);
			Utilities.formLoaded(driver);
			Utilities.enterTextNormalBox("Performing EDIT test case for PR :- " + CopyPurchaseReturnID,
					pro.getProperty("PurchaseReturnOnlyMemo"), driver);
			// select location warehouse
			for (int i = 1; i <= 2; i++) {
				Utilities.HoverandClick(
						"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[" + i
								+ "]//*[contains(@style,'px;text-align:center;')]//*[@class='pwnd serialNo-gridrow']",
						driver);
				int header = Utilities.totalSize(
						"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[1]/table/thead/tr/td/div",
						driver);
				for (int j = 1; j <= header; j++) {
					String headerName = driver.findElement(By
							.xpath("//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[1]/table/thead/tr/td["
									+ j + "]/div"))
							.getText();
					if (headerName.equalsIgnoreCase("Warehouse")) {
						Utilities.click_element(
								"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
										+ j + "]/div",
								driver);
						Utilities.enterTextandSelect("DS - Default Store",
								"//*[contains(@style,'visible')]//*[@class='x-grid3-viewport']//*[@id='warehouse']/following::input[1]",
								driver);
					}

					if (headerName.equalsIgnoreCase("Locations")) {
						Utilities.click_element(
								"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
										+ j + "]/div",
								driver);
						Utilities.enterTextandSelect("Default Location",
								"//*[contains(@style,'visible')]//*[@class='x-grid3-viewport']//*[@id='location']/following::input[1]",
								driver);
					}
				}
				Utilities.clickButton("Submit", 1500, driver);
			}
			Utilities.HoverandClick(pro.getProperty("PurchaseReturnOnlyEditSave"), driver);
			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 500, driver);
			Utilities.HoverandClick(pro.getProperty("EditPurchaseReturnOnlyClose"), driver);
			Utilities.enterTextInDropDown(CopyPurchaseReturnID, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println("Purchase Return for transaction [" + CopyPurchaseReturnID + "] EDIT test case is PASS");

			// DELETE
			Utilities.HoverandClick(pro.getProperty("deletePurchaseReturnOnlyButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deletePurchaseReturnOnlyPermButton"), driver);
			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 0, driver);
			Utilities.enterTextInDropDown(CopyPurchaseReturnID, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();

			WebElement element = driver
					.findElement(By.xpath("//*[contains(text(),' Get Started by adding a Purchase Return')]"));
			if (element.isDisplayed()) {
				System.out.println(
						"Purchase Return for transaction [" + CopyPurchaseReturnID + "] DELETE test case is PASS");
			}

			Utilities.HoverandClick(pro.getProperty("ClosePurchaseReturnOnlyTab"), driver);

		} catch (Exception EX) {
			System.out.println("******* Purchase Return COPY_EDIT_DELETE flow for :[" + documentid
					+ "] is FAIL pls checlk LOG *******");
			Utilities.handleError(EX, driver);
		}

	}

	// *********
	public static void formLoaded(String productid, WebDriver driver) throws InterruptedException {
		WebElement chkBox = new WebDriverWait(driver, 45).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[1]/table/tbody//*[text()='" + productid + "']")));

		if (chkBox.isEnabled()) {
			Thread.sleep(1500);
			// System.out.println("**** form loaded ***** ");
		}
	}

	// ******************** Copy ******************************************
	public static void Copy_PurchaseReturnDebitNote(String documentid, String productid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_PurchaseReturnDebitNote.properties");

			Utilities.HoverandClick(pro.getProperty("PurchaseReturnOnlyReport"), driver);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			/*
			 * //Report Email code String
			 * subjectLine="Purchase Return (Purchase Return with Debit Note) :"
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

			formLoaded(productid, driver);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("copyPurchaseReturnWDNButton"), driver);

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

	public static void Edit_PurchaseReturn(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			documentid = "PurInvo" + documentid;
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

	public static void Delete_PurchaseReturn(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			documentid = "PurInvo" + documentid;
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
}
