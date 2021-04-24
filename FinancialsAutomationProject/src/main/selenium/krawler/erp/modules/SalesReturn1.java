package krawler.erp.modules;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.EmailFunctionality;
import krawler.erp.page.Utilities;

public class SalesReturn1 {

	// *****************************Sales Return
	// Only****************************************************************
	public static void create_SalesReturnOnly(String documentId, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentId = "SRO" + documentId;
			Properties pro = Utilities.fetchProValue("OR_SalesReturn.properties");

			Utilities.waitandClick(pro.getProperty("ReturnOnly"), driver);
			Utilities.waitandClick(pro.getProperty("clickOnSubmit"), driver);
			Thread.sleep(2000);

			selectFromNormalDropDown(customerid, pro.getProperty("customerName"), driver);
			selectFromNormalDropDown("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.enterTextInDropDown(documentId, pro.getProperty("salesReturnNo"), driver);
			selectFromNormalDropDown("Yes", pro.getProperty("tax"), driver);
			selectFromNormalDropDown("GST(DS)@7.00%", pro.getProperty("taxName"), driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("productID"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexActQty = 0, indexRecQty = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
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
						"//div[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexActQty + "]/div",
						driver);
				Utilities.enter_LinelabelAmount("10", driver);
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
				// add receive qty
				Utilities.HoverandClick(
						"//div[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexRecQty + "]/div",
						driver);
				Utilities.enter_LinelabelAmount("10", driver);
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
			}

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			String subjectLine = "Sales Return Only :- testsmoke - " + documentId;
			EmailFunctionality.email_AfterSave(documentId, subjectLine, driver);

			Utilities.HoverandClick(pro.getProperty("clickOnOk"), driver);

			System.out.println("******* Sales Return Only : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Sales Return Only for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// ***************************** Sales Return Credit Note
	// *******************************************
	public static void create_SaleswithCreditNote(Boolean linkSI, String documentid, String productid,
			String customerid, WebDriver driver) throws InterruptedException, AWTException, IOException {
		String documentID = null;
		try {
			Properties pro = Utilities.fetchProValue("OR_SalesReturn.properties");

			if (linkSI == false) {
				documentID = "SrCrdNt" + documentid;
			} else if (linkSI == true) {
				documentID = "SrCrdNtSi" + documentid;
			}

			Utilities.waitandClick(pro.getProperty("clickOnDocumentSC"), driver);
			Utilities.waitandClick(pro.getProperty("salesReturnCreditNote"), driver);
			Utilities.waitandClick(pro.getProperty("clickOnSubmit"), driver);
			Thread.sleep(2000);

			selectFromNormalDropDown(customerid, pro.getProperty("CustomerNameSC"), driver);
			selectFromNormalDropDown("NA", pro.getProperty("sequenceFormatSC"), driver);
			Utilities.enterTextInDropDown(documentID, pro.getProperty("salesReturnNoSC"), driver);

			selectFromNormalDropDown("NA", pro.getProperty("creditNotSequence"), driver);
			if (linkSI == false) {
				Utilities.enterTextNormalBox("CusCrn" + documentid, pro.getProperty("creditNoteNo"), driver);
			} else if (linkSI == true) {
				Utilities.enterTextNormalBox("CusCrnSI" + documentid, pro.getProperty("creditNoteNo"), driver);
			}

			if (linkSI == true) {
				selectFromNormalDropDown("Yes", pro.getProperty("linkBtn"), driver);
				selectFromNormalDropDown("Sales Invoice", pro.getProperty("fromLink"), driver);
				Utilities.waitandClick("//input[@id='poNumberID11salesreturn']/following-sibling::span/img[2]", driver);
				selectFromNormalDropDown("SI" + documentid, "//input[@id='poNumberID11salesreturn']", driver);
				Utilities.waitandClick("//input[@id='poNumberID11salesreturn']/following-sibling::span/img[2]", driver);
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
				Thread.sleep(2500);
				int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
				int indexActQty = 0, indexRecQty = 0;
				for (int i = 1; i <= headRsize; i++) {
					String HeadeName = driver.findElement(By
							.xpath("//div[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();

					if (HeadeName.equalsIgnoreCase("Actual Quantity")) {
						indexActQty = i;
					}
					if (HeadeName.equalsIgnoreCase("Return Quantity")) {
						indexRecQty = i;
					}
				}

				int totalResult = Utilities
						.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div", driver);
				for (int i = 1; i <= totalResult - 1; i++) {
					// add receive qty
					Utilities.HoverandClick(
							"//div[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
									+ "]/table/tbody/tr/td[" + indexRecQty + "]/div",
							driver);
					Utilities.enter_LinelabelAmount("10", driver);
					Utilities.HoverandClick(pro.getProperty("memo"), driver);
				}
			}

			if (linkSI == false) {
				Utilities.HoverandClick(pro.getProperty("addButton"), driver);
				Utilities.isLoadingDisappear(100, driver);
				Utilities.enterTextNormalBox(productid, pro.getProperty("productID"), driver);
				Utilities.isLoadingDisappear(100, driver);
				Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
				int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
				Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
				Thread.sleep(2000);

				int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
				selectFromNormalDropDown("Yes", pro.getProperty("tax"), driver);
				selectFromNormalDropDown("GST(DS)@7.00%", pro.getProperty("taxName"), driver);
				int indexActQty = 0, indexRecQty = 0;
				for (int i = 1; i <= headRsize; i++) {
					String HeadeName = driver.findElement(By
							.xpath("//div[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
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
							"//div[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
									+ "]/table/tbody/tr/td[" + indexActQty + "]/div",
							driver);
					Utilities.enter_LinelabelAmount("10", driver);
					Utilities.HoverandClick(pro.getProperty("memo"), driver);
					// add receive qty
					Utilities.HoverandClick(
							"//div[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
									+ "]/table/tbody/tr/td[" + indexRecQty + "]/div",
							driver);
					Utilities.enter_LinelabelAmount("10", driver);
					Utilities.HoverandClick(pro.getProperty("memo"), driver);
				}
			}

			// to scroll end
			Utilities.HoverandClick(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[contains(@class,'pwnd delete-gridrow')]",
					driver);
			Utilities.clickButton("No", 10, driver);
			Thread.sleep(1000);
			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indOfReason = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (HeadeName.equalsIgnoreCase("Reason")) {
					indOfReason = i;
				}
			}

			int reason = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div",
					driver);
			for (int i = 1; i <= reason - 1; i++) {
				Utilities.HoverandClick(
						".//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[" + i
								+ "]/table/tbody/tr/td[" + indOfReason + "]/div",
						driver);
				// select first created reason
				Utilities.HoverandClick("//*[@id='reason']/following::img[1]", driver);
				Utilities.HoverandClick("//*[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div[2]",
						driver);
			}

			Utilities.HoverandClick(pro.getProperty("memo"), driver);
			Utilities.clickButton("Save", 1000, driver);

			if (linkSI == true) {
				Utilities.clickButton("Yes", 1000, driver); // not fully used
			}
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			String subjectLine = "Sales Return Creditnote :- testsmoke - " + documentID;
			EmailFunctionality.email_AfterSave(documentID, subjectLine, driver);

			Utilities.HoverandClick(pro.getProperty("clickOnOk"), driver);

			System.out.println("******* Sales Return DebitNote : [" + documentID + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Sales Return DebitNote for :[" + documentID + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Copy ******************************************
	public static void Copy_SalesReturn(boolean withCrdNote, String documentid, String productid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			// documentid="PurInvo"+documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_SalesReturnCreditNote.properties");

			Utilities.HoverandClick(pro.getProperty("SalesReturnReport"), driver);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			/*
			 * //Report Email code String
			 * subjectLine="Sales Return - testsmoke :"+documentid;
			 * EmailFunctionality.email_fromReports(documentid,subjectLine,
			 * driver);
			 * Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"),
			 * "uncheck", driver); Utilities.enterTextInDropDown(documentid,
			 * pro.getProperty("QuickSearch"), driver); Thread.sleep(2000);
			 * Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"),
			 * "check", driver);
			 */
			Utilities.HoverandClick(pro.getProperty("copySalesReturnWCNButton"), driver);
			Utilities.formLoaded(driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("copySalesReturnWCNId"), driver);

			if (withCrdNote == true) {
				Utilities.enterTextInDropDown("copyCN" + copyDocno, pro.getProperty("copySalesReturnWCNNo"), driver);
			}

			Utilities.HoverandClick(pro.getProperty("saveCopiedSalesReturnWCNId"), driver);

			Utilities.clickButton("Yes", 1000, driver);

			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("CopySalesReturnWCNClose"), driver);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Sales Return Copy Created !!");
			} else {
				System.out.println("Sales Return Copy NOT Created !!");
			}
		} catch (Exception EX) {
			System.out.println("Sales Return Copy NOT Created !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Edit ******************************************
	public static void Edit_SalesReturn(boolean withCrdNote, String documentid, WebDriver driver, String productid)
			throws IOException, InterruptedException, AWTException {

		try {
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_SalesReturnCreditNote.properties");

			Utilities.HoverandClick(pro.getProperty("EditSalesReturnWCNButton"), driver);

			Utilities.formLoaded(driver);
			Utilities.enterTextInDropDown("Performing Edit Operation", pro.getProperty("SalesReturnWCNMemo"), driver);

			int headerSize = Utilities.totalSize("//*[text()='Product ID']/ancestor::tr/td/div", driver);
			int indexOfPrID = 0, inofAct = 0, indOfRec = 0;

			for (int i = 1; i <= headerSize; i++) {
				String found = driver.findElement(By.xpath("//*[text()='Product ID']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (found.equalsIgnoreCase("Product ID")) {
					indexOfPrID = i;
				} else if (found.equalsIgnoreCase("Actual Quantity")) {
					inofAct = i;
				} else if (found.equalsIgnoreCase("Return Quantity")) {
					indOfRec = i;
				}
			}

			String addThridProduct = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td["
					+ indexOfPrID + "]/div";
			Utilities.HoverandClick(addThridProduct, driver);

			selectFromNormalDropDown("EditProduct", "//*[@name='pid']", driver);
			Utilities.HoverandClick(pro.getProperty("SalesReturnWCNMemo"), driver);

			String qtyThridProduct = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td["
					+ inofAct + "]/div";
			Utilities.clickAndEnterText("5", qtyThridProduct, driver);
			Utilities.HoverandClick(pro.getProperty("SalesReturnWCNMemo"), driver);

			qtyThridProduct = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td["
					+ indOfRec + "]/div";
			Utilities.clickAndEnterText("5", qtyThridProduct, driver);
			Utilities.HoverandClick(pro.getProperty("SalesReturnWCNMemo"), driver);

			if (withCrdNote == true) {
				Utilities.justHover("//div[1]/table//*[@class='pwnd delete-gridrow']", driver);
				int indReason = 0;
				for (int i = 1; i <= headerSize; i++) {
					String found = driver
							.findElement(By.xpath("//*[text()='Product ID']/ancestor::tr/td[" + i + "]/div")).getText();
					if (found.equalsIgnoreCase("Reason")) {
						indReason = i;
					}
				}
				qtyThridProduct = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td["
						+ indReason + "]/div";
				Utilities.HoverandClick(qtyThridProduct, driver);
				Thread.sleep(1000);
				Robot robot2 = new Robot(); // Robot class throws AWT Exception
				robot2.keyPress(KeyEvent.VK_DOWN);
				robot2.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);
				robot2.keyPress(KeyEvent.VK_DOWN);
				robot2.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(2000);
				robot2.keyPress(KeyEvent.VK_ENTER);
				robot2.keyRelease(KeyEvent.VK_ENTER);
				Utilities.HoverandClick(pro.getProperty("SalesReturnWCNMemo"), driver);
			}

			Utilities.HoverandClick(pro.getProperty("SalesReturnWCNEditSave"), driver);

			Utilities.clickButton("Yes", 1000, driver);

			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("EditSalesReturnWCNClose"), driver);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Sales Return Edited !!");
			} else {
				System.out.println("Sales Return Failed to Edit !!");
			}
		} catch (Exception EX) {
			System.out.println("Sales Return Failed to Edit !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Delete ******************************************
	public static void Delete_SalesReturn(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_SalesReturnCreditNote.properties");

			Utilities.HoverandClick(pro.getProperty("deleteSalesReturnWCNButton"), driver);

			Utilities.HoverandClick(pro.getProperty("deleteSalesReturnWCNPermButton"), driver);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(1500);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			WebElement element = new WebDriverWait(driver, 45).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//a[contains(text(),'Get Started by adding a Sales Return now')]")));
			if (element.isDisplayed()) {
				System.out.println("Sales Return Deleted !!!! ");
			} else {
				System.out.println("Sales Return is NOT Deleted !!!! ");
			}
			Utilities.HoverandClick(pro.getProperty("closeReport"), driver);
		} catch (Exception EX) {
			System.out.println("Sales Return is NOT Deleted !!!! ");
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

}
