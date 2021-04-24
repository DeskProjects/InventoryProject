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

public class PurchaseOrder1 {

	public static void create_PurchaseOrder(String documentId, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentId = "PO" + documentId;
			Properties pro = Utilities.fetchProValue("OR_PurchaseOrder.properties");
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";

			Utilities.waitandClick(pro.getProperty("PurchaseOrderIcon"), driver);
			Utilities.isElementGone(xpathOfLoading, 45, driver);
			selectFromNormalDropDown(vendorid, pro.getProperty("VendorId"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("sequencFormat"), driver);
			Utilities.enterTextInDropDown(documentId, pro.getProperty("PurchaseOrderNo"), driver);

			Utilities.clickCheckBox(pro.getProperty("includingGST"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("memo"), driver);
			Utilities.clickButton("Yes", 500, driver);

			Utilities.HoverandClick(pro.getProperty("AddButton"), driver);
			Utilities.isLoadingDisappear(60, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("searchProductId"), driver);
			Utilities.isLoadingDisappear(60, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexQty = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='purchaseordereditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Quantity")) {
					indexQty = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='purchaseordereditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[" + i
								+ "]/table/tbody/tr/td[" + indexQty + "]/div",
						driver);
				Utilities.enter_LinelabelAmount("50", driver);
				Thread.sleep(1000);
			}

			// Scroll line label
			Utilities.justHover("//*[@class='pwnd delete-gridrow']", driver);
			// enter line level Tax
			String taxNme[] = { "GST(BL)@7.00%", "GST(IM)@7.00%" };
			int sizeOfArr = taxNme.length;
			int indexTax = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='purchaseordereditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (HeadeName.equalsIgnoreCase("Product Tax")) {
					indexTax = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='purchaseordereditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[" + i
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
			String subjectLine = "Purchase Order :- testsmoke - " + documentId;
			EmailFunctionality.email_AfterSave(documentId, subjectLine, driver);

			Utilities.HoverandClick(pro.getProperty("ClosePurchaseOrder"), driver);

			System.out.println("******* Purchase Order: [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Purchase Order is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}

	}

	// ******************** Copy ******************************************

	public static void Copy_PurchaseOrder(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {

			documentid = "PO" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_PurchaseOrder.properties");

			Utilities.HoverandClick(pro.getProperty("PurchaseOrderReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(1500);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			Utilities.HoverandClick(pro.getProperty("copyPurchaseOrderButton"), driver);
			String isFormloaded = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr//*[@class='x-grid3-row-checker']";
			Utilities.clickCheckBox(isFormloaded, "uncheck", driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("copyPurchaseOrderId"), driver);
			Utilities.HoverandClick(pro.getProperty("saveCopiedPurchaseOrderId"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("CopyPurchaseOrderClose"), driver);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Purchase Order Copy Created !!");
			} else {
				System.out.println("Purchase Order Copy NOT Created !!");
			}
		} catch (Exception EX) {
			System.out.println("Purchase Order Copy NOT Created !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Edit ******************************************

	public static void Edit_PurchaseOrder(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {

			documentid = "PO" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_PurchaseOrder.properties");

			Utilities.HoverandClick(pro.getProperty("EditPurchaseOrderButton"), driver);
			Utilities.formLoaded(driver);
			Utilities.enterTextInDropDown("Performing Edit Operation", pro.getProperty("PurchaseOrderMemo"), driver);
			Utilities.clickAndEnterText("60",
					"//div[1]/table//td[contains(@class,'x-grid3-col x-grid3-cell x-grid3-td-quantity')]", driver);

			String addThridProduct = "//div[3]/table/tbody//*[contains(@class,'x-grid3-cell-inner x-grid3-col-productid')]";
			Utilities.HoverandClick(addThridProduct, driver);
			selectFromNormalDropDown("EditProduct", "//*[@name='pid']", driver);
			Utilities.HoverandClick(pro.getProperty("PurchaseOrderMemo"), driver);

			String qtyThridProduct = "//div[3]/table/tbody//*[contains(@class,'x-grid3-cell-inner x-grid3-col-quantity69PurchaseOrderEdit')]";
			Utilities.clickAndEnterText("10", qtyThridProduct, driver);
			Utilities.HoverandClick(pro.getProperty("PurchaseOrderEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("EditPurchaseOrderClose"), driver);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Purchase Order Edited !!");
			} else {
				System.out.println("Purchase Order Failed to Edit !!");
			}
		} catch (Exception EX) {
			System.out.println("Purchase Order Failed to Edit !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Delete ******************************************

	public static void Delete_PurchaseOrder(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {

			documentid = "PO" + documentid;
			String copyDocno = "Copy" + documentid;

			Properties pro = Utilities.fetchProValue("OR_PurchaseOrder.properties");

			Utilities.HoverandClick(pro.getProperty("deletePurchaseOrdernButton"), driver);

			Utilities.HoverandClick(pro.getProperty("deletePurchaseOrderPermButton"), driver);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			WebElement element = new WebDriverWait(driver, 45).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//a[contains(text(),'Get Started by adding a Purchase Order now')]")));
			if (element.isDisplayed()) {
				System.out.println("Purchase Order Deleted !!!! ");
			} else {
				System.out.println("Purchase Order is NOT Deleted !!!! ");
			}

			Utilities.HoverandClick("//li[@id='as__PurchaseOrderListEntry']/a[1]", driver);
		} catch (Exception EX) {
			System.out.println("Purchase Order is NOT Deleted !!!! ");
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

	// ************************************************ purchase order with
	// Expense ************************************
	public static void create_POExpense(String documentid, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_PurchaseOrder.properties");

			Utilities.waitandClick(pro.getProperty("PurchaseOrderIcon"), driver);
			Utilities.clickCheckBox(pro.getProperty("includingGST"), "uncheck", driver);
			selectFromNormalDropDown(vendorid, pro.getProperty("VendorId"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("sequencFormat"), driver);
			Utilities.enterTextNormalBox("POExp" + documentid, pro.getProperty("PurchaseOrderNo"), driver);

			// switch to Expense tab
			Utilities.HoverandClick("//*[text()='Expense']", driver);
			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1500);
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

			Utilities.HoverandClick(pro.getProperty("ClosePurchaseOrder"), driver);

			System.out.println("******* Purchase Order for EXPENSE account: [" + "POExp" + documentid
					+ "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("Purchase Order with Expense is -[" + "POExp" + documentid + "] NOT CREATED  !!! ");
			Utilities.handleError(EX, driver);
		}
	}

	// ***************************Global tax label***************
	public static void createGL_PurchaseOrder(String documentId, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentId = "PoGL" + documentId;
			Properties pro = Utilities.fetchProValue("OR_PurchaseOrder.properties");

			Utilities.waitandClick(pro.getProperty("PurchaseOrderIcon"), driver);

			selectFromNormalDropDown(vendorid, pro.getProperty("VendorId"), driver);

			Utilities.enterTextInDropDown("NA", pro.getProperty("sequencFormat"), driver);

			Utilities.enterTextInDropDown(documentId, pro.getProperty("PurchaseOrderNo"), driver);

			Utilities.HoverandClick(pro.getProperty("AddButton"), driver);

			Utilities.clickCheckBox(pro.getProperty("clickCheckBox"), "uncheck", driver);

			Utilities.enterTextNormalBox(productid, pro.getProperty("searchProductId"), driver);

			Utilities.clickCheckBox(pro.getProperty("SelectproductSS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);

			Utilities.HoverandClick(pro.getProperty("clickAdd"), driver);
			Thread.sleep(2000);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexQty = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='purchaseordereditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Quantity")) {
					indexQty = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='purchaseordereditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[" + i
								+ "]/table/tbody/tr/td[" + indexQty + "]/div",
						driver);
				Utilities.sendText("50");
				Thread.sleep(1000);
			}

			// to scroll till end
			Utilities.justHover("//*[contains(text(),'Outstanding Balance')]", driver);
			selectFromNormalDropDown("Yes", pro.getProperty("includeGlobalTax"), driver);
			selectFromNormalDropDown("GST(BL)@7.00%", pro.getProperty("GlobalTaxValue"), driver);

			Utilities.HoverandClick(pro.getProperty("memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("ClosePurchaseOrder"), driver);

			System.out.println("******* Purchase Order: [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Purchase Order is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}

	}

	// ************************************** PO Expense Copy/Edit/Delete
	// ****************************************

	public static void CopyEditDelete_POExpense(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_PurchaseOrder.properties");

			Utilities.waitandClick(pro.getProperty("PurchaseOrderReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();
			String CopyPurchaseOrderID = "Copy" + documentid;

			// Copy
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("copyPurchaseOrderButton"), driver);
			String isFormloaded = "//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr//td[8]";
			Utilities.clickCheckBox(isFormloaded, "uncheck", driver);
			Utilities.enterTextNormalBox(CopyPurchaseOrderID, pro.getProperty("copyPurchaseOrderId"), driver);
			Utilities.HoverandClick(pro.getProperty("saveCopiedPurchaseOrderId"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("CopyPurchaseOrderClose"), driver);
			Utilities.enterTextInDropDown(CopyPurchaseOrderID, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println("PO Expense [" + CopyPurchaseOrderID + "] COPY test case is PASS !!");

			// Edit
			Utilities.HoverandClick(pro.getProperty("EditPurchaseOrderButton"), driver);
			Utilities.clickCheckBox(isFormloaded, "uncheck", driver);
			Utilities.enterTextNormalBox("Performing EDIT test case for PO Expense [" + CopyPurchaseOrderID + "]",
					pro.getProperty("PurchaseOrderMemo"), driver);
			Utilities.HoverandClick(pro.getProperty("PurchaseOrderEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("EditPurchaseOrderClose"), driver);
			Utilities.enterTextInDropDown(CopyPurchaseOrderID, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println("PO Expense [" + CopyPurchaseOrderID + "] EDIT test case is PASS !!");

			// Delete
			Utilities.HoverandClick(pro.getProperty("deletePurchaseOrdernButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deletePurchaseOrderPermButton"), driver);
			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);

			Utilities.enterTextInDropDown(CopyPurchaseOrderID, pro.getProperty("QuickSearch"), driver);
			WebElement element = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(
					By.xpath("//a[contains(text(),'Get Started by adding a Purchase Order now')]")));
			if (element.isDisplayed()) {
				System.out.println("Purchase Order [" + CopyPurchaseOrderID + "] DELETE test case is PASS !!!! ");
			}

			Utilities.HoverandClick("//li[@id='as__PurchaseOrderListEntry']/a[1]", driver);

		} catch (Exception EX) {
			System.out.println("Purchase Order [" + documentid + "] COPY-EDIT-DELETE test case is FAILED !!!! ");
			Utilities.handleError(EX, driver);
		}
	}
}
