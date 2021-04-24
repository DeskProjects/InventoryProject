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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SalesReturnOnly {

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
					assertEquals(Customer + "Name",
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

	public static void EmailCopyEditDelete_salesReturnOnly(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "SRO" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_SalesReturn.properties");
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
			String subjectLine = "Sales Return Only - testsmoke - " + documentid;
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys(documentid);
			Thread.sleep(500);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement copySalesReturnButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copySalesReturnOnlyButton"))));
			copySalesReturnButton.click();
			Thread.sleep(2000);

			String CopySalesReturnID = "Copy" + documentid;

			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copySalesReturnOnlyId"))));
			driver.findElement(By.xpath(pro.getProperty("copySalesReturnOnlyId"))).sendKeys(CopySalesReturnID);
			Thread.sleep(1000);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("saveCopiedSalesReturnOnlyId"))));
			driver.findElement(By.xpath(pro.getProperty("saveCopiedSalesReturnOnlyId"))).click();

			Thread.sleep(2000);
			Robot r = new Robot();

			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(1000);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("CopySalesReturnOnlyClose"))));
				driver.findElement(By.xpath(pro.getProperty("CopySalesReturnOnlyClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys(CopySalesReturnID);
			Thread.sleep(500);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement editSalesReturnButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditSalesReturnOnlyButton"))));
			editSalesReturnButton.click();
			Thread.sleep(3000);

			WebElement SalesReturnMemo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesReturnOnlyMemo"))));
			SalesReturnMemo.sendKeys("Update Sales Return Only Memo");
			Thread.sleep(1000);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesReturnOnlyEditSave"))));
			driver.findElement(By.xpath(pro.getProperty("SalesReturnOnlyEditSave"))).click();
			Thread.sleep(1500);

			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(1000);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("EditSalesReturnOnlyClose"))));
				driver.findElement(By.xpath(pro.getProperty("EditSalesReturnOnlyClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys(CopySalesReturnID);
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
					System.out.println("Sales Return Only " + CopySalesReturnID + " is edited Successfully");
				} else {
					System.out.println("Sales Return Only " + CopySalesReturnID + " is not edited Successfully");
				}

			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);
			}

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteSalesReturnOnlyButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteSalesReturnOnlyButton"))).click();

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteSalesReturnOnlyPermButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteSalesReturnOnlyPermButton"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(3000);

			try {
				search.clear();
				search.sendKeys(CopySalesReturnID);
				Thread.sleep(500);
				search.sendKeys(Keys.TAB);
				Thread.sleep(4000);

				if (driver
						.findElement(By
								.xpath("//div[@id='SalesReturnListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("No results to display")) {
					System.out.println("Sales Return Only " + CopySalesReturnID + "  is Deleted Successfully");
				} else {
					System.out.println("Sales Return Only " + CopySalesReturnID + "  is not Deleted Successfully");
				}
			} catch (Exception exp) {
				System.out.println("Sales Return Only " + CopySalesReturnID + " is Successfully deleted");
			}

		} catch (Exception EX) {
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

	// *************************** Export SalesReturn
	// ***************************************
	public static void Export_SalesReturn(WebDriver driver) throws InterruptedException, AWTException, IOException {
		String BtnName2 = "Export List";
		Properties pro = Utilities.fetchProValue("OR_SalesReturn.properties");
		String reportIcon = pro.getProperty("SalesReturnReport");
		String waitForQuickSearch = pro.getProperty("DocumentCheckBox");
		String closeReportPage = "//li[@id='as__SalesReturnListEntry']/a[1]";
		String ModuleName = "Sales Return";

		ExportFunction.Export_TodayDate(BtnName2, reportIcon, waitForQuickSearch, closeReportPage, ModuleName, driver);

	}
}
