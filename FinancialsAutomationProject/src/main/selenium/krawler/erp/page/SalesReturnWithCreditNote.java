package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SalesReturnWithCreditNote {

	public static void create_salesReturnWithCreditNote(String documentid, String productid, String customerid,
			WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			documentid = "SrCrdNt" + documentid;
			Properties salesReturnProperties = Utilities.fetchProValue("OR_SalesReturn.properties");

			// clicked on Document
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("clickOnDocumentSC"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("clickOnDocumentSC"))).click();
			Thread.sleep(2000);// pro
			// Clicked on Sales Return with Credit Note
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("salesReturnCreditNote"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("salesReturnCreditNote"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("clickOnSubmit"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("clickOnSubmit"))).click();
			Thread.sleep(8000);

			// enter Customer name
			WebElement vendor = driver.findElement(By.xpath(salesReturnProperties.getProperty("CustomerNameSC")));
			vendor.clear();
			vendor.sendKeys(customerid);
			Thread.sleep(3000);// pro
			vendor.sendKeys(Keys.ENTER);

			// sequence format document no.
			WebElement squence = driver.findElement(By.xpath(salesReturnProperties.getProperty("sequenceFormatSC")));
			squence.clear();
			squence.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// Sales Return Number
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("salesReturnNoSC"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("salesReturnNoSC"))).sendKeys(documentid);
			Thread.sleep(2000);// pro

			// credit Note Number
			WebElement squence1 = driver.findElement(By.xpath(salesReturnProperties.getProperty("creditNotSequence")));
			squence1.clear();
			squence1.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence1.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("creditNoteNo"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("creditNoteNo")))
					.sendKeys("CusCrn" + documentid);

			// click on add button
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("addbuttonSC"))));
			driver.findElement(By.xpath((salesReturnProperties.getProperty("addbuttonSC")))).click();

			// search product id
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("productIDSC"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("productIDSC"))).sendKeys(productid);
			Thread.sleep(2000);// pro

			// select check box
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("checkbox"))));
			driver.findElement(By.xpath((salesReturnProperties.getProperty("checkbox")))).click();
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("addProduct"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("addProduct"))).click();
			Thread.sleep(3000);

			/*
			 * //Click on select NA of customer ID new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(
			 * ".//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[12]/div"
			 * ))); WebElement quantity = driver.findElement(By.xpath(
			 * ".//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[12]/div"
			 * )); quantity.click(); Thread.sleep(1000);//pro
			 * 
			 * 
			 * Utilities.sendText("100"); Thread.sleep(2000);//pro
			 * 
			 * driver.findElement(By.xpath(salesReturnProperties.getProperty(
			 * "memo"))).click();
			 * 
			 * //Click on select NA of customer ID new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(
			 * ".//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div"
			 * ))); WebElement quantity1 = driver.findElement(By.xpath(
			 * ".//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div"
			 * )); quantity1.click(); Thread.sleep(1000);//pro
			 * 
			 * Utilities.sendText("100"); Thread.sleep(2000);//pro
			 * 
			 * driver.findElement(By.xpath(salesReturnProperties.getProperty(
			 * "memo"))).click();
			 * 
			 * //Click on select NA of customer ID new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(
			 * ".//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[15]/div/div"
			 * ))); WebElement quantity2 = driver.findElement(By.xpath(
			 * ".//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[15]/div/div"
			 * )); quantity2.click(); Thread.sleep(2000);//pro
			 * 
			 */
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
			 * 
			 * 
			 * 
			 * new WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath("//button[text()='Submit']")));
			 * driver.findElement(By.xpath("//button[text()='Submit']")).click()
			 * ; Thread.sleep(2000);
			 */
			// ------------------------------------------------------------------------------------------------------------------------------------------------
			// Click on select NA of customer ID
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
					".//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[31]/div")));
			WebElement reson = driver.findElement(By.xpath(
					".//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[31]/div"));
			reson.click();
			Thread.sleep(2000);// pro

			Robot robot2 = new Robot(); // Robot class throws AWT Exception
			robot2.keyPress(KeyEvent.VK_DOWN);
			robot2.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);
			robot2.keyPress(KeyEvent.VK_DOWN);
			robot2.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(2000);
			robot2.keyPress(KeyEvent.VK_ENTER);
			robot2.keyRelease(KeyEvent.VK_ENTER);

			driver.findElement(By.xpath(salesReturnProperties.getProperty("memo"))).click();

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
			Thread.sleep(1000);
			driver.findElement(By.xpath(salesReturnProperties.getProperty("okButton"))).click();
			Thread.sleep(1000);

			// Email code
			String subjectLine = "Sales Return with Credit Note - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);
			Thread.sleep(1000);

			System.out.println(
					"***** Sales Return with Credit Note successfully created for : [" + documentid + "] ********* ");
			// Click on OK
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("clickOnOk"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("clickOnOk"))).click();

		} catch (Exception EX) {
			System.out.println("***** Sales Return with Credit Note is NOT created for : [" + documentid
					+ "] plz check Log !********* ");
			Utilities.handleError(EX, driver);
		}

	}

	public static void verify_salesReturnWithCreditNote(String documentid, String productid, String Customer,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			// documentid="SrCrdNt"+documentid;
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
			Utilities.zoomOut();
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
					assertEquals(Customer + "Name",
							driver.findElement(By
									.xpath("//div[@id='gridmsg53SalesReturnListEntry']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

			}
			Utilities.zoomIn();
			Thread.sleep(500);
			System.out.println("Sales Return with Credit Note VERIFIED  : " + documentid);
			Utilities.refresh();
		} catch (Exception EX) {
			System.out.println("Sales Return with Credit Note is NOT VERIFIED  : " + documentid);
			Utilities.handleError(EX, driver);
		}
	}

	public static void EmailCopyEditDelete_salesReturnWithCreditNote(String documentid, String creditnoteid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			// documentid="SrCrdNt"+documentid;
			creditnoteid = "copyCrNoteNo" + documentid;

			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_SalesReturnCreditNote.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesReturnReport"))));
			driver.findElement(By.xpath(pro.getProperty("SalesReturnReport"))).click();
			Thread.sleep(5000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			// Report Email code
			String subjectLine = "Sales Return with Credit Note - testsmoke - " + documentid;
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			Thread.sleep(500);
			search.sendKeys(documentid);
			Thread.sleep(500);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement copySalesReturnWCNButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copySalesReturnWCNButton"))));
			copySalesReturnWCNButton.click();
			Thread.sleep(2000);

			String CopySalesReturnWCNID = "Copy" + documentid;

			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copySalesReturnWCNId"))));
			driver.findElement(By.xpath(pro.getProperty("copySalesReturnWCNId"))).sendKeys(CopySalesReturnWCNID);
			Thread.sleep(1000);
			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copySalesReturnWCNNo"))));
			driver.findElement(By.xpath(pro.getProperty("copySalesReturnWCNNo"))).sendKeys(creditnoteid);
			Thread.sleep(1000);
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("saveCopiedSalesReturnWCNId"))));
			driver.findElement(By.xpath(pro.getProperty("saveCopiedSalesReturnWCNId"))).click();

			Thread.sleep(2000);

			Robot r = new Robot();
			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(1000);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("CopySalesReturnWCNClose"))));
				driver.findElement(By.xpath(pro.getProperty("CopySalesReturnWCNClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			Thread.sleep(500);
			search.sendKeys(CopySalesReturnWCNID);
			Thread.sleep(500);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement editSalesReturnWCNButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditSalesReturnWCNButton"))));
			editSalesReturnWCNButton.click();
			Thread.sleep(3000);

			WebElement SalesReturnWCNMemo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesReturnWCNMemo"))));
			SalesReturnWCNMemo.sendKeys("Update Sales Return With CN Memo");

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesReturnWCNEditSave"))));
			driver.findElement(By.xpath(pro.getProperty("SalesReturnWCNEditSave"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(1000);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("EditSalesReturnWCNClose"))));
				driver.findElement(By.xpath(pro.getProperty("EditSalesReturnWCNClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			Thread.sleep(500);
			search.sendKeys(CopySalesReturnWCNID);
			Thread.sleep(500);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			try {
				Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
				Thread.sleep(3000);
				if (driver
						.findElement(By
								.xpath("//div[@id='SalesReturnListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
					System.out.println(
							"Sales Return With Credit Note " + CopySalesReturnWCNID + " is edited Successfully");
				} else {
					System.out.println(
							"Sales Return With Credit Note " + CopySalesReturnWCNID + " is not edited Successfully");
				}

			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);
			}

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteSalesReturnWCNButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteSalesReturnWCNButton"))).click();

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteSalesReturnWCNPermButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteSalesReturnWCNPermButton"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 500, driver);

			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(3000);

			try {
				search.clear();
				search.clear();
				Thread.sleep(500);
				search.sendKeys(CopySalesReturnWCNID);
				Thread.sleep(500);
				search.sendKeys(Keys.TAB);
				Thread.sleep(3000);

				if (driver
						.findElement(By
								.xpath("//div[@id='SalesReturnListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("No results to display")) {
					System.out.println(
							"Sales Return With Credit Note " + CopySalesReturnWCNID + "  is Deleted Successfully");
				} else {
					System.out.println(
							"Sales Return With Credit Note " + CopySalesReturnWCNID + "  is not Deleted Successfully");
				}
			} catch (Exception exp) {
				System.out.println("Sales Return With Debit Note " + CopySalesReturnWCNID + " is Successfully deleted");
			}

		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}
	}

	// ******************************** Sales Return with CN link Sale Invoice
	// *****************************
	public static void create_salesReturnWithCreditNote_linkSI(String documentid, String productid, String customerid,
			WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			// documentid="SrCrdNtSi"+documentid;
			Properties salesReturnProperties = Utilities.fetchProValue("OR_SalesReturn.properties");

			// clicked on Document
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("clickOnDocumentSC"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("clickOnDocumentSC"))).click();
			Thread.sleep(2000);// pro
			// Clicked on Sales Return with Credit Note
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("salesReturnCreditNote"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("salesReturnCreditNote"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("clickOnSubmit"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("clickOnSubmit"))).click();
			Thread.sleep(8000);

			// enter Customer name
			WebElement vendor = driver.findElement(By.xpath(salesReturnProperties.getProperty("CustomerNameSC")));
			vendor.clear();
			Thread.sleep(1000);
			Utilities.sendText(customerid);
			// vendor.sendKeys(customerid);
			Thread.sleep(3000);// pro
			vendor.sendKeys(Keys.ENTER);

			// sequence format document no.
			WebElement squence = driver.findElement(By.xpath(salesReturnProperties.getProperty("sequenceFormatSC")));
			squence.clear();
			squence.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// Sales Return Number
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("salesReturnNoSC"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("salesReturnNoSC")))
					.sendKeys("SrCrdNtSi" + documentid);
			Thread.sleep(2000);// pro

			// si code *******
			Utilities.enterTextInDropDown("Yes", salesReturnProperties.getProperty("linkBtn"), driver);
			Thread.sleep(1000);

			Utilities.enterTextInDropDown("Sales Invoice", salesReturnProperties.getProperty("fromLink"), driver);
			Thread.sleep(1000);

			String drpDwnArrow = "//input[@id='poNumberID11salesreturn']/following-sibling::span/img[2]";
			String itemInputBoxLocator = "//input[@id='poNumberID11salesreturn']";
			String itemName = "SIEx" + documentid;
			String rslTable = "//div[@class='x-combo-list-inner']/div/table/tbody/tr/td[contains(text(),'SI')]";
			// "//div[@class='x-combo-list-inner']/div/table/tbody/tr/td[text()='"
			// +itemName+ "']";

			Utilities.selectItemfromDropDown(drpDwnArrow, itemInputBoxLocator, itemName, rslTable, driver);
			Thread.sleep(1000);

			driver.findElement(By.xpath(salesReturnProperties.getProperty("memo"))).click();
			Thread.sleep(2000);

			// credit Note Number
			WebElement squence1 = driver.findElement(By.xpath(salesReturnProperties.getProperty("creditNotSequence")));
			squence1.clear();
			squence1.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence1.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("creditNoteNo"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("creditNoteNo")))
					.sendKeys("CusCrn" + documentid);

			Thread.sleep(2000);
			driver.findElement(By.xpath(salesReturnProperties.getProperty("memo"))).click();

			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
					".//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[31]/div")));
			WebElement reson = driver.findElement(By.xpath(
					".//*[@id='salesreturnbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[31]/div"));
			reson.click();
			Thread.sleep(2000);// pro

			Robot robot2 = new Robot(); // Robot class throws AWT Exception
			robot2.keyPress(KeyEvent.VK_DOWN);
			robot2.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);
			robot2.keyPress(KeyEvent.VK_DOWN);
			robot2.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(2000);
			robot2.keyPress(KeyEvent.VK_ENTER);
			robot2.keyRelease(KeyEvent.VK_ENTER);

			driver.findElement(By.xpath(salesReturnProperties.getProperty("memo"))).click();

			// Click on Save Button
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("saveButton"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("saveButton"))).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("yesButton"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("yesButton"))).click();

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("okButton"))));
			Thread.sleep(1000);
			driver.findElement(By.xpath(salesReturnProperties.getProperty("okButton"))).click();
			Thread.sleep(1000);

			// Email code
			String subjectLine = "Sales Return with Credit Note with Link SI - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);
			Thread.sleep(1000);

			System.out.println("***** Sales Return with Credit with Link SI Note successfully created for : ["
					+ documentid + "] ********* ");
			// Click on OK
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("clickOnOk"))));
			driver.findElement(By.xpath(salesReturnProperties.getProperty("clickOnOk"))).click();

		} catch (Exception EX) {
			System.out.println("***** Sales Return with Credit Note with Link SI is NOT created for : [" + documentid
					+ "] plz check Log !********* ");
			Utilities.handleError(EX, driver);
		}

	}

}
