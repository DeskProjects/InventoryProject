package com.krawler.erp.inventory.reports.SR;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import krawler.erp.page.EmailFunctionality;
import krawler.erp.page.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SalesReturnOnly {

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
			// selectFromNormalDropDown("Yes",pro.getProperty("tax"), driver );
			// selectFromNormalDropDown("GST(DS)@7.00%",pro.getProperty("taxName"),
			// driver );

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.clickCheckBox(pro.getProperty("checkBox"), "uncheck", driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("productID"), driver);
			Thread.sleep(1000);

			Utilities.clickCheckBox(pro.getProperty("checkBox"), "uncheck", driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Thread.sleep(1000);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
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

			// //Email code
			// String subjectLine="Sales Return Only :- testsmoke -
			// "+documentId;
			// EmailFunctionality.email_AfterSave(documentId,subjectLine,driver);

			Utilities.HoverandClick(pro.getProperty("clickOnOk"), driver);

			System.out.println("******* Sales Return Only : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Sales Return Only for :[" + documentId + "] checlk LOG *******");
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
			// driver.findElement(By.xpath("//*[text()='Sales Return
			// No']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr[1]/td[1]/div/div")).click();

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

			// int headerSize=Utilities.totalSize("//*[text()='Product
			// ID']/ancestor::tr/td/div", driver);
			// int indexOfPrID=0, inofAct=0, indOfRec=0;
			//
			// System.out.println("First Time header size ..... "+headerSize);
			//
			//
			// for(int i=1; i<=headerSize;i++){
			//
			// System.out.println("inside For loop for first
			// time..."+headerSize);
			//
			//
			// String found=driver.findElement(By.xpath("//*[text()='Product
			// ID']/ancestor::tr/td["+i+"]/div")).getText();
			//
			//
			// System.out.println("Found is"+found);
			//
			// if(found.equalsIgnoreCase("Product ID")){
			// indexOfPrID=i;
			//
			// System.out.println("indexofPrID is for first if case is
			// ...."+indexOfPrID);
			//
			// }
			// else if(found.equalsIgnoreCase("Actual Quantity")){
			// inofAct=i;
			//
			// System.out.println("inofAct is for elseif case is
			// ......"+inofAct);
			//
			// }
			// else if(found.equalsIgnoreCase("Return Quantity")){
			// indOfRec=i;
			//
			// System.out.println("indOfRec is for second elseif
			// case...."+indOfRec);
			//
			// }
			// }
			//
			// String addThridProduct="//*[text()='Product
			// ID']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td["+indexOfPrID+"]/div";
			// Utilities.HoverandClick(addThridProduct, driver);
			//
			// System.out.println("Before Hover and Click"+addThridProduct);
			//
			// selectFromNormalDropDown("EditProduct", "//*[@name='pid']",
			// driver);
			// Utilities.HoverandClick(pro.getProperty("SalesReturnWCNMemo"),
			// driver);
			//
			// String qtyThridProduct="//*[text()='Product
			// ID']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td["+inofAct+"]/div";
			// Utilities.clickAndEnterText("5", qtyThridProduct, driver);
			// Utilities.HoverandClick(pro.getProperty("SalesReturnWCNMemo"),
			// driver);
			//
			// qtyThridProduct="//*[text()='Product
			// ID']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td["+indOfRec+"]/div";
			// Utilities.clickAndEnterText("5", qtyThridProduct, driver);
			// Utilities.HoverandClick(pro.getProperty("SalesReturnWCNMemo"),
			// driver);
			//
			// if(withCrdNote==true){
			// Utilities.justHover("//div[1]/table//*[@class='pwnd
			// delete-gridrow']", driver);
			// int indReason=0;
			// for(int i=1; i<=headerSize;i++){
			// String found=driver.findElement(By.xpath("//*[text()='Product
			// ID']/ancestor::tr/td["+i+"]/div")).getText();
			// if(found.equalsIgnoreCase("Reason")){
			// indReason=i;
			// }
			// }
			// qtyThridProduct="//*[text()='Product
			// ID']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td["+indReason+"]/div";
			// Utilities.HoverandClick(qtyThridProduct, driver);
			// Thread.sleep(1000);
			// Robot robot2 = new Robot(); // Robot class throws AWT Exception
			// robot2.keyPress(KeyEvent.VK_DOWN);
			// robot2.keyRelease(KeyEvent.VK_DOWN);
			// Thread.sleep(1000);
			// robot2.keyPress(KeyEvent.VK_DOWN);
			// robot2.keyRelease(KeyEvent.VK_DOWN);
			// Thread.sleep(2000);
			// robot2.keyPress(KeyEvent.VK_ENTER);
			// robot2.keyRelease(KeyEvent.VK_ENTER);
			// Utilities.HoverandClick(pro.getProperty("SalesReturnWCNMemo"),
			// driver);
			// }
			//
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

	public static void create_salesReturnOnly(String documentid, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{

		try {
			documentid = "SRO" + documentid;
			Properties salesReturnProperties = Utilities.fetchProValue("OR_SalesReturn.properties");
			// clicked on Sales Return Document
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("clickOnDocument"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("ReturnOnly"))).click();
			Thread.sleep(1000);// pro

			// Click On Submit Button
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("clickOnSubmit"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("clickOnSubmit"))).click();
			Thread.sleep(8000);

			// enter Customer name
			WebElement Customer = driver.findElement(By.xpath(salesReturnProperties.getProperty("customerName")));
			Customer.clear();
			Customer.sendKeys(customerid);
			Thread.sleep(3000);// pro
			Customer.sendKeys(Keys.ENTER);

			// sequence format document no.
			WebElement sequence = driver.findElement(By.xpath(salesReturnProperties.getProperty("sequenceFormat")));
			sequence.clear();
			sequence.sendKeys("NA");
			Thread.sleep(1000);// pro
			sequence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// Enter Sales Return number
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("salesReturnNo"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("salesReturnNo"))).sendKeys(documentid);
			Thread.sleep(2000);// pro

			// click on Add button at line level
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("addButton"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("addButton"))).click();

			// search product id
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("productID"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("productID"))).sendKeys(productid);
			Thread.sleep(2000);// pro

			// select check box
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("checkBox"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("checkBox"))).click();
			Thread.sleep(2000);// pro

			// Click on Add Button on Product Selection Window
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("addProduct"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("addProduct"))).click();
			Thread.sleep(3000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Actual Quantity")) {
					driver.findElement(By
							.xpath("//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					Thread.sleep(2000);
					Utilities.sendText("1");
					break;
				}
			}
			driver.findElement(By.xpath(salesReturnProperties.getProperty("memo"))).click();

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Return Quantity")) {
					driver.findElement(By
							.xpath("//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					Thread.sleep(2000);
					Utilities.sendText("1");
					break;
				}
			}

			/*
			 * //Click Actual Quantity new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(salesReturnProperties.getProperty(
			 * "actualQuantity")))); WebElement quantity =
			 * driver.findElement(By.xpath(salesReturnProperties.getProperty(
			 * "actualQuantity"))); quantity.click(); Thread.sleep(1000);//pro
			 * 
			 * Thread.sleep(2000); Utilities.sendText("1"); Thread.sleep(2000);
			 * 
			 * driver.findElement(By.xpath(salesReturnProperties.getProperty(
			 * "memo"))).click();
			 * 
			 * //Click on Return Quantity new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(salesReturnProperties.getProperty(
			 * "returnQuantity")))); WebElement quantity1 =
			 * driver.findElement(By.xpath(salesReturnProperties.getProperty(
			 * "returnQuantity"))); quantity1.click(); Thread.sleep(1000);//pro
			 * 
			 * Thread.sleep(2000); Utilities.sendText("1"); Thread.sleep(2000);
			 */

			driver.findElement(By.xpath(salesReturnProperties.getProperty("memo"))).click();
			/*
			 * //Click on Batch Serial Window new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(salesReturnProperties.getProperty(
			 * "batchSerialWindow")))); WebElement quantity2 =
			 * driver.findElement(By.xpath(salesReturnProperties.getProperty(
			 * "batchSerialWindow"))); quantity2.click();
			 * Thread.sleep(2000);//pro
			 * 
			 * // Click on Submit Button new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(salesReturnProperties.getProperty(
			 * "submitButton"))));
			 * driver.findElement(By.xpath(salesReturnProperties.getProperty(
			 * "submitButton"))).click(); Thread.sleep(2000);
			 * 
			 */

			// Click on Save Button
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("saveButton"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("saveButton"))).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("yesButton"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("yesButton"))).click();

			// new
			// WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
			// driver.findElement(By.xpath("//button[text()='No']")).click();

			// Click on OK
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("okButton"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("okButton"))).click();
			Thread.sleep(1000);

			// Email code
			String subjectLine = "Sales Return Only - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);
			Thread.sleep(1000);

			// Click on OK
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("clickOnOk"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("clickOnOk"))).click();

			System.out.println("***** Sales Return Only successfully created for : [" + documentid + "] ********* ");
		} catch (Exception EX) {
			System.out.println("***** Sales Return Only NOT created for : [" + documentid + "] check LOG ********* ");
			Utilities.handleError(EX, driver);
		}

	}

	public static void verify_salesReturnOnly(String documentid, String productid, String Customer, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			// documentid="SRO"+documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_SalesReturn.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesReturnReport"))));
			driver.findElement(By.xpath(pro.getProperty("SalesReturnReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath("//div[@id='gridmsg53SalesReturnListEntry']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='gridmsg53SalesReturnListEntry']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath("//div[@id='gridmsg53SalesReturnListEntry']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Sales Return No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath("//div[@id='gridmsg53SalesReturnListEntry']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (header.equals("Customer")) {
					assertEquals(Customer,
							driver.findElement(By
									.xpath("//div[@id='gridmsg53SalesReturnListEntry']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

			}
			Thread.sleep(500);
			System.out.println("* * * Verified sales Return for document [" + documentid + "]* * * * * * *");
		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}
	}

	// * * * * * * * * * Sales Return with Refund * * * * * * * * * * ** *

	public static void create_salesReturn_Refund(String documentid, String productid, String customerid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {
			// documentid="SRrefund"+documentid;
			Properties salesReturnProperties = Utilities.fetchProValue("OR_SalesReturn.properties");
			// clicked on Sales Return Document

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement element = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("clickOnDocument"))));
			element.click();
			Thread.sleep(1000);

			element = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("cashSaleRefund"))));
			element.click();
			Thread.sleep(1000);

			element = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("clickOnSubmit"))));
			element.click();
			Thread.sleep(2000);

			Utilities.enterTextInDropDown("NA", salesReturnProperties.getProperty("seqNArefund"), driver);

			element = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("salesReturnNoSC"))));
			element.clear();
			element.sendKeys("SRrefund" + documentid);
			Thread.sleep(1000);

			Utilities.enterTextInDropDown(customerid, salesReturnProperties.getProperty("CustomerNameSC"), driver);

			WebElement memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("memo"))));
			memo.click();

			Utilities.enterTextInDropDown("Yes", salesReturnProperties.getProperty("linkBtn"), driver);
			Utilities.enterTextInDropDown("Cash Sales", salesReturnProperties.getProperty("fromLink"), driver);
			memo.click();

			String drpDwnArrow = "//input[@id='poNumberID11salesreturn']/following::span[1]/img[2]";
			String itemInputBoxLocator = "//input[@id='poNumberID11salesreturn']";
			String itemName = "CSref" + documentid;
			String rslTable = "//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div/table/tbody/tr/td[1]";

			Utilities.selectItemfromDropDown(drpDwnArrow, itemInputBoxLocator, itemName, rslTable, driver);
			memo.click();
			Thread.sleep(1000);

			Utilities.enterTextInDropDown("NA", salesReturnProperties.getProperty("seqNAforMP"), driver);
			Utilities.enterTextInDropDown("MP" + documentid, salesReturnProperties.getProperty("MpPaymentNo"), driver);

			WebElement selectReason = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//div[@class='x-grid3-scroller']/div/div[1]//div[@class='x-grid3-cell-inner x-grid3-col-30']")));
			Actions action = new Actions(driver);
			action.moveToElement(selectReason).build().perform();
			Thread.sleep(3000);

			Robot r = new Robot();
			selectReason = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//div[@class='x-grid3-scroller']/div/div[1]//div[@class='x-grid3-cell-inner x-grid3-col-30']")));
			selectReason.click();
			Thread.sleep(2000);

			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);// pro
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);// pro
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);// pro
			memo.click();

			Utilities.clickButton("Save", 500, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			element = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("clickOnOk"))));
			element.click();
			Thread.sleep(1000);

			System.out.println(
					"* * * * Sales Return Refund created success for [" + "CSref" + documentid + "] * * * * * * * ");
		} catch (Exception EX) {
			System.out.println("* * * * Sales Return Refund FAILED for [" + "CSref" + documentid + "] * * * * * * * ");
			Utilities.handleError(EX, driver);
		}
	}

}
