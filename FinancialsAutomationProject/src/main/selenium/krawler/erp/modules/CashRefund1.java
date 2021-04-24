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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.EmailFunctionality;
import krawler.erp.page.Utilities;

public class CashRefund1 {
	// *****************************Purchase Return
	// Only****************************************************************
	public static void create_SR_CashRefund(String documentid, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentId = "SRrefund" + documentid;
			Properties pro = Utilities.fetchProValue("OR_SalesReturn.properties");
			WebDriverWait wait = new WebDriverWait(driver, 30);

			Utilities.waitandClick(pro.getProperty("ReturnOnly"), driver);
			Utilities.waitandClick(pro.getProperty("cashSaleRefund"), driver);
			Utilities.waitandClick(pro.getProperty("clickOnSubmit"), driver);
			Thread.sleep(2000);

			selectFromNormalDropDown(customerid, pro.getProperty("customerName"), driver);
			selectFromNormalDropDown("NA", pro.getProperty("seqNArefund"), driver);
			Utilities.enterTextInDropDown(documentId, pro.getProperty("salesReturnNoSC"), driver);

			Utilities.enterTextInDropDown("Yes", pro.getProperty("linkBtn"), driver);
			Utilities.enterTextInDropDown("Cash Sales", pro.getProperty("fromLink"), driver);
			WebElement memo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("memo"))));
			memo.click();

			String drpDwnArrow = "//input[@id='poNumberID11salesreturn']/following::span[1]/img[2]";
			String itemInputBoxLocator = "//input[@id='poNumberID11salesreturn']";
			String itemName = "CS" + documentid;
			String rslTable = "//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div/table/tbody/tr/td[1]";

			Utilities.selectItemfromDropDown(drpDwnArrow, itemInputBoxLocator, itemName, rslTable, driver);
			memo.click();

			Utilities.enterTextInDropDown("NA", pro.getProperty("seqNAforMP"), driver);
			Utilities.enterTextInDropDown("MP" + documentId, pro.getProperty("MpPaymentNo"), driver);
			memo.click();
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

			int addedPro = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div",
					driver);
			for (int i = 1; i <= addedPro - 1; i++) {
				// add receive qty
				Utilities.HoverandClick("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
						+ "]/table/tbody/tr/td[" + indexRecQty + "]/div", driver);
				Utilities.enter_LinelabelAmount("10", driver);
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
			}

			Utilities.justHover(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[contains(@class,'x-grid3-cell-last')]",
					driver);
			int indReason = 0;
			for (int i = 1; i <= headRsize; i++) {
				String found = driver.findElement(By.xpath("//*[text()='Product ID']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (found.equalsIgnoreCase("Reason")) {
					indReason = i;
				}
			}

			for (int i = 1; i <= addedPro - 1; i++) {
				Utilities.HoverandClick("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
						+ "]/table/tbody/tr/td[" + indReason + "]/div", driver);
				// select first created reason
				Utilities.HoverandClick("//*[@id='reason']/following::img[1]", driver);
				Utilities.HoverandClick("//*[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div[2]",
						driver);
			}
			memo.click();

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			String subjectLine = "Sales Return- Cash Refund :- testsmoke - " + documentId;
			EmailFunctionality.email_AfterSave(documentId, subjectLine, driver);

			Utilities.HoverandClick(pro.getProperty("clickOnOk"), driver);

			System.out.println("******* Cash Refund Only : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Cash Refund Only for :[" + documentid + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Copy ******************************************
	public static void Copy_SalesReturn(String documentid, String productid, WebDriver driver)
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
			formLoaded(productid, driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("copySalesReturnWCNId"), driver);
			Utilities.enterTextNormalBox("COpyMP" + documentid, "//*[@name='paymentNumber']", driver);
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
	public static void Edit_SalesReturn(String documentid, WebDriver driver, String productid)
			throws IOException, InterruptedException, AWTException {

		try {
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_SalesReturnCreditNote.properties");

			Utilities.HoverandClick(pro.getProperty("EditSalesReturnWCNButton"), driver);

			formLoaded(productid, driver);

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

			Utilities.justHover(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[contains(@class,'x-grid3-cell-last')]",
					driver);

			int indOfReason = 0;
			for (int i = 1; i <= headerSize; i++) {
				String found = driver.findElement(By.xpath("//*[text()='Product ID']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (found.equalsIgnoreCase("Reason")) {
					indOfReason = i;
				}
			}
			// add reason
			Utilities.HoverandClick(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td["
							+ indOfReason + "]/div",
					driver);
			// select first created reason
			Utilities.HoverandClick("//*[@id='reason']/following::img[1]", driver);
			Utilities.HoverandClick("//*[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div[2]",
					driver);

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
			throws InterruptedException {
		WebElement enterText = new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(expForname)));
		enterText.clear();
		enterText.sendKeys(text);
		Thread.sleep(1000);
		WebElement element = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]//*[text()='" + text + "']")));
		Thread.sleep(500);
		element.click();
		Thread.sleep(500);
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

	public static void formLoaded(String productid, WebDriver driver) throws InterruptedException {
		WebElement chkBox = new WebDriverWait(driver, 45).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[1]/table/tbody//*[text()='" + productid + "']")));

		if (chkBox.isEnabled()) {
			Thread.sleep(1500);
			// System.out.println("**** form loaded ***** ");
		}
	}
}
