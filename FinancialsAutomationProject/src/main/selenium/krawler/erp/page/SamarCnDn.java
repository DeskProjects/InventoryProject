
package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SamarCnDn {

	public static void main(String[] args) {

	}

	public static void create_Vendor(String vendor_ID, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {
			Properties values = Utilities.fetchProValue("OR_VendorMaster.properties");
			String vendorName = vendor_ID;

			// wait until component appears
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(values.getProperty(
					"CreateVendorIcon"))));/* examining the xpath search box */

			// open vendor master
			driver.findElement(By.xpath(values.getProperty("CreateVendorIcon"))).click();
			System.out.println("clicked on vendor");
			Thread.sleep(2000);// pro

			// click on Term
			WebElement NA = driver.findElement(By.xpath(values.getProperty("sequenceformat")));
			NA.clear();
			NA.sendKeys("NA");
			Thread.sleep(1000);// pro
			NA.sendKeys(Keys.ENTER);

			Thread.sleep(3000);// pro

			driver.findElement(By.xpath(values.getProperty("vendorID"))).sendKeys(vendor_ID);// adding
																								// new
																								// vendor
																								// ID
			driver.findElement(By.xpath(values.getProperty("vendorName"))).sendKeys(vendorName);// adding
																								// new
																								// Vendor
																								// name

			WebElement term = driver.findElement(By.xpath(values.getProperty("debitTerm")));
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);

			// credit term
			driver.findElement(By.xpath(values.getProperty("CreditTerm"))).sendKeys("999999999");// adding
																									// new
																									// vendor
																									// ID

			// to disable agent
			WebElement agentCheckbox = new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//input[@name='venodorAvailableToAgentCheck']"))));
			if (agentCheckbox.isSelected()) {
				agentCheckbox.click();
			}

			// first save visible wait
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(values.getProperty("personaldetailsSave"))));
			// first save
			driver.findElement(By.xpath(values.getProperty("personaldetailsSave"))).click();

			// second save
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(values.getProperty("confirmYes"))));
			driver.findElement(By.xpath(values.getProperty("confirmYes"))).click();
			Thread.sleep(3000);// pro

			// second save
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(values.getProperty("successOk"))));
			driver.findElement(By.xpath(values.getProperty("successOk"))).click();

			driver.findElement(By.xpath(values.getProperty("vendorContactDetail"))).click();
			driver.findElement(By.xpath(values.getProperty("billingAddress"))).sendKeys("12, vishwakarma nagar");
			driver.findElement(By.xpath(values.getProperty("billingCity"))).sendKeys("Nagpur");// address
			driver.findElement(By.xpath(values.getProperty("billingState"))).sendKeys("maharashtarr");// maharashtra
			driver.findElement(By.xpath(values.getProperty("billingCountry"))).sendKeys("country");// country
			driver.findElement(By.xpath(values.getProperty("billingPostal"))).sendKeys("440027");
			driver.findElement(By.xpath(values.getProperty("billingMobile"))).sendKeys("8421279427");
			driver.findElement(By.xpath(values.getProperty("billingEmail"))).sendKeys("amol.gaikwad@deskera.com");
			driver.findElement(By.xpath(values.getProperty("billingRecipientName"))).sendKeys("receipt");
			driver.findElement(By.xpath(values.getProperty("billingContactPerson"))).sendKeys("abcXyz");
			driver.findElement(By.xpath(values.getProperty("billingContactPersonNumber"))).sendKeys("9988774466");
			driver.findElement(By.xpath(values.getProperty("billingContactPersonDesignation")))
					.sendKeys("QA Automation");
			driver.findElement(By.xpath(values.getProperty("billingWebsite"))).sendKeys("www.deskera.com");

			// select check box
			WebElement checkBoxElement = driver.findElement(By.xpath(values.getProperty("SameasBillingAddressCheck")));
			checkBoxElement.click();

			// second save
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(values.getProperty("vendorContactDetailSave"))));
			driver.findElement(By.xpath(values.getProperty("vendorContactDetailSave"))).click();
			Thread.sleep(3000);// pro

			// clic on ok
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(values.getProperty("vendorContactDetailOk"))));
			driver.findElement(By.xpath(values.getProperty("vendorContactDetailOk"))).click();
			Thread.sleep(3000);// pro

			Utilities.HoverandClick("//*[@id='as__mainVendorPanel']/a[1]", driver);
			System.out.println("******** Vendor Created  : [" + vendor_ID + "] !!!! ********");

		} catch (Exception EX) {
			System.out.println("******** Vendor NOT Created  : " + vendor_ID + " check log pls ********");
			Utilities.handleError(EX, driver);
		}
	}

	public static void create_Customer(String customerid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_CustomerMaster.properties");
			String customerName = customerid + "Name";

			Utilities.waitandClick(pro.getProperty("CreateCustomerIcon"), driver);
			Thread.sleep(3000);
			Utilities.clickCheckBox("//input[@name='customervenodoravailabletosalespersonagent']", "uncheck", driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceformat"), driver);
			Utilities.enterTextNormalBox(customerid, pro.getProperty("customerId"), driver);
			Utilities.enterTextNormalBox(customerName, pro.getProperty("customerName"), driver);
			Utilities.enterTextandSelect("NET 45", pro.getProperty("term"), driver);
			Utilities.enterTextNormalBox("999999999", pro.getProperty("CreditTerm"), driver);
			// first save
			Utilities.click_element(pro.getProperty("customerPersonalDetailsaveButton"), driver);
			Utilities.click_element(pro.getProperty("yesButton"), driver);
			Utilities.click_element(pro.getProperty("okButton"), driver);

			// ContactDetail Tab
			Utilities.HoverandClick(pro.getProperty("customerContactDetail"), driver);
			// click on contact of customer
			driver.findElement(By.xpath(pro.getProperty("address"))).click();
			driver.findElement(By.xpath(pro.getProperty("address"))).sendKeys("office");
			driver.findElement(By.xpath(pro.getProperty("city"))).sendKeys("pune");// address
			driver.findElement(By.xpath(pro.getProperty("state"))).sendKeys("Maharashtra");// maharashtra
			driver.findElement(By.xpath(pro.getProperty("country"))).sendKeys("India");// country
			driver.findElement(By.xpath(pro.getProperty("postalCode"))).sendKeys("44002");
			driver.findElement(By.xpath(pro.getProperty("mobileNo"))).sendKeys("8421279427");
			driver.findElement(By.xpath(pro.getProperty("emailID"))).sendKeys("amol.gaikwad@deskera.com");
			driver.findElement(By.xpath(pro.getProperty("contactPerson"))).sendKeys("Amol G");
			driver.findElement(By.xpath(pro.getProperty("contactPersonNo"))).sendKeys("9988774466");
			driver.findElement(By.xpath(pro.getProperty("contactPersonDesignstion"))).sendKeys("Automation");
			driver.findElement(By.xpath(pro.getProperty("website"))).sendKeys("www.deskera.com");
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("sameAsBillingCheck"), "check", driver);
			Utilities.click_element(pro.getProperty("customerContactDetailsaveButton"), driver);
			Utilities.click_element(pro.getProperty("customerContactDetailOkButton"), driver);

			Utilities.HoverandClick(pro.getProperty("dashBoardtab"), driver);
			Utilities.HoverandClick(pro.getProperty("closeCustomerTab"), driver);

			System.out.println("**************** Customer created successfully :[" + customerid + "]****************");
		} catch (Exception EX) {
			System.out.println(
					"**************** Customer NOT CREATED :[" + customerid + "] plz check log file ************");
			Utilities.handleError(EX, driver);
		}

	}

	public static void create_product(String productid, String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		String productName = productid;
		String productDes = productid + " ERP normal Product created on : [" + Utilities.currentDate("dd-MM-yyyy")
				+ "]";
		try {
			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");

			Utilities.waitandClick(pro.getProperty("createProductIcon"), driver);
			Thread.sleep(2000);
			Utilities.enterTextNormalBox(productName, pro.getProperty("productName"), driver);
			// Utilities.enterTextNormalBox(productDes,
			// pro.getProperty("productDes"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceformat"), driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("productId"), driver);
			// Utilities.clickCheckBox(pro.getProperty("qaenable"), "check",
			// driver);
			Utilities.enterTextNormalBox("1000", pro.getProperty("initialquantity"), driver);
			Utilities.click_element(pro.getProperty("productId"), driver);
			try {
				Utilities.clickButton("Yes", 1000, driver);
			} catch (Exception ex) {

			}
			Thread.sleep(1000);

			// enter WareHouse
			int header = Utilities.totalSize(
					"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[1]/table/thead/tr/td/div",
					driver);
			for (int i = 1; i <= header; i++) {
				String headerName = driver.findElement(By
						.xpath("//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[1]/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Warehouse")) {
					Utilities.click_element(
							"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div",
							driver);
					Utilities.enterTextandSelect("DS - Default Store",
							"//*[contains(@style,'visible')]//*[@class='x-grid3-viewport']//*[@id='warehouse']/following::input[1]",
							driver);
				}

				if (headerName.equalsIgnoreCase("Locations")) {
					Utilities.click_element(
							"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div",
							driver);
					Utilities.enterTextandSelect("Default Location",
							"//*[contains(@style,'visible')]//*[@class='x-grid3-viewport']//*[@id='location']/following::input[1]",
							driver);
				}
			}
			Utilities.click_element("//*[contains(@style,'visible')]//button[text()='Submit']", driver);

			// GO to Purcahse TAB
			Utilities.click_element(pro.getProperty("purchaseTab"), driver);
			Utilities.enterTextInDropDown("Purchases", pro.getProperty("purchaseAccount"), driver);
			Utilities.enterTextNormalBox("10", pro.getProperty("initialPurchasePrice"), driver);

			Utilities.HoverandClick(pro.getProperty("salesTab"), driver);
			Utilities.enterTextInDropDown("Sales", pro.getProperty("salesAccount"), driver);
			Utilities.enterTextNormalBox("20", pro.getProperty("initialSalesPrice"), driver);

			Utilities.HoverandClick(pro.getProperty("inventoryDataTab"), driver);
			Utilities.enterTextInDropDown("Unit", pro.getProperty("umo"), driver);
			Utilities.enterTextInDropDown("DS - Default Store", pro.getProperty("warehouse"), driver);
			Utilities.enterTextInDropDown("Default Location", pro.getProperty("location"), driver);

			WebElement countable = driver.findElement(By.xpath(pro.getProperty("countable")));
			if (countable.isEnabled()) {
				if (!countable.isSelected()) {
					countable.click();
				}
				WebElement cyclecountfrequency = driver.findElement(By.xpath(pro.getProperty("cyclecountfrequency")));
				cyclecountfrequency.click();
				Robot r3 = new Robot();
				r3.keyPress(KeyEvent.VK_DOWN);
				r3.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(2000);
				List<WebElement> comboItems = driver.findElements(
						By.cssSelector(".x-combo-list[style*='visibility: visible;'] .x-combo-list-item"));
				for (int i = 0; i < comboItems.size(); i++) {
					WebElement item = comboItems.get(i);
					if (item.getText().equals("Daily") || item.getText().equals("Weekly")
							|| item.getText().equals("Fortnightly") || item.getText().equals("Monthly")) {
						item.click();
					}
				}
			}
			Utilities.HoverandClick(pro.getProperty("saveButton"), driver);

			try {
				WebElement duplicateProductID = new WebDriverWait(driver, 3).until(
						ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'already exists')]")));
				if (duplicateProductID.isDisplayed()) {
					Utilities.refresh();
					System.out.println("********* This product [" + productid
							+ "] is already present so Not creating this One !!!!!! *******");
				}
			} catch (Exception ex) {
				// Continue as New Product
				try {
					productAfterSaveOKBtn(driver);
				} catch (Exception noOk) {
					// No Ok button
				}
				Utilities.click_element(pro.getProperty("CloseReport"), driver);
				Utilities.click_element(pro.getProperty("closeCreateproductTab"), driver);
				System.out
						.println("********** Normal Product [" + productName + "] Successfully created *************");
			}
		} catch (Exception EX) {
			System.out.println("********** Failed to Create Normal Product [" + productName
					+ "] Successfully created *************");
			Utilities.handleError(EX, driver);
		}
	}

	// ---------------------------------------------------------------DN with
	// multiple line
	// items-------------------------------------------------------------------

	private static void productAfterSaveOKBtn(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public static void create_debitNoteAgainstpurchaseInvoice2(String documentid, String documentid21, String productid,
			String vendor_ID, WebDriver driver) throws InterruptedException, AWTException, IOException {

		{
			try {

				Properties DebitNotePurchaseInvoice = Utilities.fetchProValue("OR_DebitNotePurchaseInvoice.properties");

				Thread.sleep(3000);
				// WebDriverWait wait = new WebDriverWait(driver, 30);
				// clicked on Document
				new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(
						By.xpath(DebitNotePurchaseInvoice.getProperty("DebitNotePurchaseInvoiceIcon"))));
				driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("DebitNotePurchaseInvoiceIcon")))
						.click();
				Thread.sleep(2000);// pro

				new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(
						By.xpath(DebitNotePurchaseInvoice.getProperty("SelectDebitNotePurchaseInvoice"))));
				driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("SelectDebitNotePurchaseInvoice")))
						.click();
				Thread.sleep(3000);

				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(DebitNotePurchaseInvoice.getProperty("ClickSubmit"))));
				driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("ClickSubmit"))).click();
				Thread.sleep(8000);

				// sequen format document no.
				WebElement squence = driver
						.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("sequenceFormat")));
				squence.clear();
				squence.sendKeys("NA");
				Thread.sleep(1000);// pro
				squence.sendKeys(Keys.ENTER);
				Thread.sleep(2000);// pro

				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(DebitNotePurchaseInvoice.getProperty("DocumentNo"))));
				driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("DocumentNo")))
						.sendKeys("DnPi" + documentid);

				Thread.sleep(2000);// pro

				// enter vendor name
				WebElement vendor = driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("VendorId")));
				vendor.clear();
				vendor.sendKeys(vendor_ID);
				Thread.sleep(3000);// pro
				vendor.sendKeys(Keys.ENTER);

				for (int i = 1; i <= driver
						.findElements(By
								.xpath(".//*[@id='centerpandebitnotepanel']/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
						.size(); i++) {
					if (driver.findElement(By
							.xpath(".//*[@id='centerpandebitnotepanel']/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText().equalsIgnoreCase("Purchase Invoice")) {
						driver.findElement(By
								.xpath(".//*[@id='centerpandebitnotepanel']/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
										+ i + "]"))
								.click();
						Thread.sleep(2000);
						Utilities.sendText("PurInvo" + documentid);
						Thread.sleep(2000);
						Robot robot12 = new Robot();
						robot12.keyPress(KeyEvent.VK_ENTER);
						Thread.sleep(1000);
						robot12.keyRelease(KeyEvent.VK_ENTER);

						break;
					}
				}

				for (int i = 1; i <= driver
						.findElements(By
								.xpath(".//*[@id='centerpandebitnotepanel']/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
						.size(); i++) {
					if (driver.findElement(By
							.xpath(".//*[@id='centerpandebitnotepanel']/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText().equalsIgnoreCase("Enter Amount/Percentage")) {
						driver.findElement(By
								.xpath(".//*[@id='centerpandebitnotepanel']/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
										+ i + "]"))
								.click();
						Thread.sleep(2000);
						Utilities.sendText("1");
						break;
					}
				}

				// --------------------------------------------------------------------------------------------------------------------------------------------------------

				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(DebitNotePurchaseInvoice.getProperty("EnterAmount"))));
				WebElement amount2 = driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("EnterAmount")));
				amount2.click();
				Thread.sleep(1000);// pro
				Robot acc1 = new Robot();
				Utilities.sendText("1");
				Thread.sleep(2000);// pro

				for (int i = 1; i <= driver
						.findElements(By
								.xpath(".//*[@id='centerpandebitnotepanel']/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
						.size(); i++) {
					if (driver.findElement(By
							.xpath(".//*[@id='centerpandebitnotepanel']/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText().equalsIgnoreCase("Purchase Invoice")) {
						driver.findElement(By
								.xpath(".//*[@id='centerpandebitnotepanel']/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[2]/table/tbody/tr/td["
										+ i + "]"))
								.click();
						Thread.sleep(2000);
						Utilities.sendText("PurInvoEx" + documentid21);
						Thread.sleep(2000);
						Robot robot12 = new Robot();
						robot12.keyPress(KeyEvent.VK_ENTER);
						Thread.sleep(1000);
						robot12.keyRelease(KeyEvent.VK_ENTER);

						break;
					}
				}

				for (int i = 1; i <= driver
						.findElements(By
								.xpath(".//*[@id='centerpandebitnotepanel']/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
						.size(); i++) {
					if (driver.findElement(By
							.xpath(".//*[@id='centerpandebitnotepanel']/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText().equalsIgnoreCase("Enter Amount/Percentage")) {
						driver.findElement(By
								.xpath(".//*[@id='centerpandebitnotepanel']/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[2]/table/tbody/tr/td["
										+ i + "]"))
								.click();
						Thread.sleep(2000);
						Utilities.sendText("1");
						break;
					}
				}

				for (int i = 1; i <= driver
						.findElements(By
								.xpath(".//*[@id='centerpandebitnotepanel']/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
						.size(); i++) {
					if (driver.findElement(By
							.xpath(".//*[@id='centerpandebitnotepanel']/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText().equalsIgnoreCase("Purchase Invoice")) {
						driver.findElement(By
								.xpath(".//*[@id='centerpandebitnotepanel']/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[2]/table/tbody/tr/td["
										+ i + "]"))
								.click();
						Thread.sleep(2000);
						Utilities.sendText("Ex" + documentid21);
						Thread.sleep(2000);
						Robot robot12 = new Robot();
						robot12.keyPress(KeyEvent.VK_ENTER);
						Thread.sleep(1000);
						robot12.keyRelease(KeyEvent.VK_ENTER);

						break;
					}
				}

				/*
				 * 
				 * //-----------------------------------------------------------
				 * -------------------------------------------------------------
				 * --------------------------------
				 * 
				 * new WebDriverWait(driver,30).until(ExpectedConditions.
				 * visibilityOfElementLocated(By.xpath(DebitNotePurchaseInvoice.
				 * getProperty("EnterAmount")))); WebElement amount3 =
				 * driver.findElement(By.xpath(DebitNotePurchaseInvoice.
				 * getProperty("EnterAmount"))); amount3.click();
				 * Thread.sleep(1000);//pro Robot acc2=new Robot();
				 * Utilities.sendText("1"); Thread.sleep(2000);//pro
				 */

				// enter vendor name
				// WebElement accountselect =
				// driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("AccountName")));

				WebElement accountselect = driver
						.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("AccountName2")));
				accountselect.click();
				Thread.sleep(2000);// pro

				Utilities.sendText("Utilities");
				Thread.sleep(2000);// pro
				acc1.keyPress(KeyEvent.VK_ENTER);
				acc1.keyRelease(KeyEvent.VK_ENTER);

				driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("memo"))).click();
				Thread.sleep(2000);// pro
				WebElement ammount1 = driver
						.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("AccountAmount")));
				ammount1.click();
				Thread.sleep(2000);// pro

				Utilities.sendText("1");
				Thread.sleep(2000);// pro

				driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("memo"))).click();
				Thread.sleep(2000);// pro
				WebElement reason = driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("EnterReason")));
				reason.click();
				Thread.sleep(2000);// pro

				acc1.keyPress(KeyEvent.VK_DOWN);
				acc1.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				acc1.keyPress(KeyEvent.VK_DOWN);
				acc1.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				acc1.keyPress(KeyEvent.VK_ENTER);
				acc1.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);// pro
				driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("memo"))).click();

				WebElement accountselect1 = driver
						.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("AccountName3")));
				accountselect1.click();
				Thread.sleep(2000);// pro

				Utilities.sendText("Travel");
				Thread.sleep(2000);// pro
				acc1.keyPress(KeyEvent.VK_ENTER);
				acc1.keyRelease(KeyEvent.VK_ENTER);

				driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("memo"))).click();
				Thread.sleep(2000);// pro
				WebElement ammount2 = driver
						.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("Accountamount2")));
				ammount2.click();
				Thread.sleep(2000);// pro

				Utilities.sendText("1");
				Thread.sleep(2000);// pro

				driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("memo"))).click();
				Thread.sleep(2000);// pro
				WebElement reason1 = driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("EnterReason2")));
				reason1.click();
				Thread.sleep(2000);// pro

				acc1.keyPress(KeyEvent.VK_DOWN);
				acc1.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				acc1.keyPress(KeyEvent.VK_DOWN);
				acc1.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				acc1.keyPress(KeyEvent.VK_ENTER);
				acc1.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);// pro
				driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("memo"))).click();

				// Click on Save
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(DebitNotePurchaseInvoice.getProperty("buttonSave"))));
				driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("buttonSave"))).click();
				Thread.sleep(2000);

				// Click on Yes
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(DebitNotePurchaseInvoice.getProperty("buttonYes"))));
				driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("buttonYes"))).click();
				Thread.sleep(1000);

				// Click on OK
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(DebitNotePurchaseInvoice.getProperty("clickOk"))));
				driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("clickOk"))).click();
				Thread.sleep(1000);

				// Click on close
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(DebitNotePurchaseInvoice.getProperty("CloseForm"))));
				driver.findElement(By.xpath(DebitNotePurchaseInvoice.getProperty("CloseForm"))).click();

				System.out.println("Debit Note created with >>>> : " + "DnPi" + documentid);
			}

			catch (Exception EX) {
				Utilities.handleError(EX, driver);
			}

		}

	}

	/////////////////////////////////////////////////////// EDIT DELETE DN
	/////////////////////////////////////////////////////// AGAINST PI
	/////////////////////////////////////////////////////// MULTIPLE////////////////////////////////////////////////////////////////////

	public static void EditDelete_debitNoteAgainstpurchaseInvoice2(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "DnPi" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_DebitNotePurchaseInvoice.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DebitNoteReport"))));
			driver.findElement(By.xpath(pro.getProperty("DebitNoteReport"))).click();

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			Thread.sleep(5000);
			driver.findElement(By.xpath(pro.getProperty("QuickSearch"))).clear();
			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(5000);

			WebElement checkbox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentCheckBox"))));
			checkbox.click();
			Thread.sleep(5000);

			WebElement editDNPIButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditDNPIButton"))));
			editDNPIButton.click();
			Thread.sleep(3000);

			WebElement DNPIMemo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DNPIMemo"))));
			DNPIMemo.sendKeys("Update Debit Note Against Purchase Invoice Memo");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DNPIEditSave"))));
			driver.findElement(By.xpath(pro.getProperty("DNPIEditSave"))).click();
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 500, driver);
			Thread.sleep(3000);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			Thread.sleep(3000);

			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			try {
				new WebDriverWait(driver, 30).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("EditDNPIClose"))));
				driver.findElement(By.xpath(pro.getProperty("EditDNPIClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Thread.sleep(5000);
			search.clear();
			search.sendKeys(documentid);
			Thread.sleep(2000);
			search.sendKeys(Keys.TAB);

			Thread.sleep(2000);

			try {
				new WebDriverWait(driver, 30).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("DocumentCheckBox"))));
				driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).click();
				Thread.sleep(3000);
				if (driver
						.findElement(By
								.xpath("//div[@id='DebitNoteDetails']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
					System.out.println("Debit Note Against Vendor Invoice " + documentid + " is edited Successfully");
				} else {
					System.out
							.println("Debit Note Against Vendor Invoice " + documentid + " is not edited Successfully");
				}

			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);
			}
			// DELETE
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteDNPIButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteDNPIButton"))).click();

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteDNPIPermButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteDNPIPermButton"))).click();
			Thread.sleep(2000);

			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			Thread.sleep(2000);

			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			Thread.sleep(2000);

			try {
				search.clear();
				search.sendKeys(documentid);

				driver.wait(10000);

				if (driver
						.findElement(By
								.xpath("//div[@id='DebitNoteDetails']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("No results to display")) {
					System.out.println("Debit Note Against Vendor Invoice " + documentid + "  is Deleted Successfully");
				} else {
					System.out.println(
							"Debit Note Against Vendor Invoice " + documentid + "  is not Deleted Successfully");
				}
			} catch (Exception exp) {
				System.out.println("Debit Note Against Vendor Invoice " + documentid + " is Successfully deleted");
			}

			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}
	}

	//////////////////////////////////////////////////////////// Verification of
	//////////////////////////////////////////////////////////// DN AGAINST PI
	//////////////////////////////////////////////////////////// Multiple///////////////////////////////////////////////////////////////////////////

	public static void verify_debitNoteAgainstpurchaseInvoice2(String documentid, String productid, String vendor_ID,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			documentid = "DnPi" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_DebitNoteOtherWise.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DebitNoteReport"))));
			driver.findElement(By.xpath(pro.getProperty("DebitNoteReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();
			System.out.println(e);
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				System.out.println(driver.findElement(By
						.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText());
				String header = driver.findElement(By
						.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Debit Note No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("d1");
				}

				else if (header.equals("Name")) {
					assertEquals(vendor_ID,
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("d2");
				}

				else if (header.equals("Vendor Code")) {
					assertEquals(vendor_ID,
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("d3");
				}

				else if (header.equals("Amount")) {
					assertEquals("SGD 2.00",
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("d4");
				}

			}
			Thread.sleep(500);
			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	///////////////////////////////////////////////// DN OTHERWISE WITH MULTIPLE
	///////////////////////////////////////////////// ACCOUNT AT THE LINE
	///////////////////////////////////////////////// LEVEL//////////////////////////////////////////////////////////////

	public static void create_debitNoteOtherwise2(String documentid, String productid, String vendorid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			documentid = "DnO" + documentid;
			Properties DebitNoteOtherwise = Utilities.fetchProValue("OR_DebitNoteOtherWise.properties");

			Utilities.waitandClick(DebitNoteOtherwise.getProperty("CreateDebitNoteIcon"), driver);
			Utilities.waitandClick(DebitNoteOtherwise.getProperty("SelectDNOtherwiseOption"), driver);
			Utilities.waitandClick(DebitNoteOtherwise.getProperty("submitButton"), driver);
			Thread.sleep(2000);// pro

			// sequen format document no.
			Utilities.enterTextandSelect("NA", DebitNoteOtherwise.getProperty("sequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid, DebitNoteOtherwise.getProperty("debitNoteNo"), driver);
			Utilities.enterTextandSelect(vendorid, DebitNoteOtherwise.getProperty("vendorId"), driver);

			WebElement accountselect = driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("vendorName")));
			accountselect.click();
			Thread.sleep(2000);// pro
			Robot acc1 = new Robot();

			Utilities.sendText("Utilities");
			Thread.sleep(2000);// pro
			acc1.keyPress(KeyEvent.VK_ENTER);
			acc1.keyRelease(KeyEvent.VK_ENTER);

			driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("memo"))).click();
			Thread.sleep(2000);// pro
			WebElement ammount1 = driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("Amount")));
			ammount1.click();
			Thread.sleep(2000);// pro

			Utilities.sendText("100");
			Thread.sleep(2000);// pro

			driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("memo"))).click();
			Thread.sleep(2000);// pro
			WebElement reason = driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("reason")));
			reason.click();
			Thread.sleep(2000);// pro

			acc1.keyPress(KeyEvent.VK_DOWN);
			acc1.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);// pro
			acc1.keyPress(KeyEvent.VK_DOWN);
			acc1.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);// pro
			acc1.keyPress(KeyEvent.VK_ENTER);
			acc1.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);// pro
			driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("memo"))).click();

			WebElement accountselect2 = driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("AccountName")));
			accountselect2.click();
			Thread.sleep(2000);// pro
			Robot acc10 = new Robot();

			Utilities.sendText("Travel");
			Thread.sleep(2000);// pro
			acc10.keyPress(KeyEvent.VK_ENTER);
			acc10.keyRelease(KeyEvent.VK_ENTER);

			driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("memo"))).click();
			Thread.sleep(2000);// pro
			WebElement ammount2 = driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("Amount2")));
			ammount2.click();
			Thread.sleep(2000);// pro

			Utilities.sendText("100");
			Thread.sleep(2000);// pro

			driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("memo"))).click();
			Thread.sleep(2000);// pro
			WebElement reason2 = driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("reason2")));
			reason2.click();
			Thread.sleep(2000);// pro

			acc1.keyPress(KeyEvent.VK_DOWN);
			acc1.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);// pro
			acc1.keyPress(KeyEvent.VK_DOWN);
			acc1.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);// pro
			acc1.keyPress(KeyEvent.VK_ENTER);
			acc1.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);// pro
			driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("memo"))).click();

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(DebitNoteOtherwise.getProperty("buttonSave"))));
			driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("buttonSave"))).click();
			Thread.sleep(2000);

			// Click on OK
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(DebitNoteOtherwise.getProperty("buttonYes"))));
			driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("buttonYes"))).click();
			Thread.sleep(1000);

			// Utilities.clickButton("Save", 1000, driver);
			// Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(DebitNoteOtherwise.getProperty("clickOk"), driver);

			System.out.println("* * * Debit Note Otherwise created successfully for : " + documentid);
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	//////////////////////////////////////////////// Verification of DN
	//////////////////////////////////////////////// Otherwise
	//////////////////////////////////////////////// Multiple////////////////////////////////////////////////////////////////////////

	public static void verify_debitNoteOtherwise2(String documentid, String productid, String vendor_ID,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			documentid = "DnO" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_DebitNoteOtherWise.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DebitNoteReport"))));
			driver.findElement(By.xpath(pro.getProperty("DebitNoteReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();
			System.out.println(e);
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Debit Note No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d1");
				}

				else if (header.equals("Name")) {
					assertEquals(vendor_ID,
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d2");
				}

				else if (header.equals("Vendor Code")) {
					assertEquals(vendor_ID,
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d3");
				}

				else if (header.equals("Amount")) {
					assertEquals("SGD 200.00",
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d4");
				}

			}
			Thread.sleep(10000);
			String closeReportPage = "//div[@id='as']/div[1]/div[1]/ul/li[2]/a[1]";
			Thread.sleep(10000);
			Utilities.HoverandClick(closeReportPage, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	/////////////////////////////////////////////// Copy Edit Delete DN
	/////////////////////////////////////////////// Otherwise
	/////////////////////////////////////////////// Multiple///////////////////////////////////////////////////////////////////////////

	public static void CopyEditDelete_debitNoteOtherwise2(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "DnO" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_DebitNoteOtherWise.properties");
			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DebitNoteReport"))));
			Thread.sleep(10000);
			driver.findElement(By.xpath(pro.getProperty("DebitNoteReport"))).click();
			Thread.sleep(5000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement copyOtherwiseButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyDNOtherwiseButton"))));
			copyOtherwiseButton.click();
			Thread.sleep(2000);

			String CopyDNOtherwiseID = "Copy" + documentid;

			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyDNOtherwiseId"))));
			driver.findElement(By.xpath(pro.getProperty("copyDNOtherwiseId"))).sendKeys(CopyDNOtherwiseID);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("saveCopiedDNOtherwiseId"))));

			driver.findElement(By.xpath(pro.getProperty("saveCopiedDNOtherwiseId"))).click();

			Thread.sleep(3000);

			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			Thread.sleep(3000);
			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("CopyDNOtherwiseClose"))));
				driver.findElement(By.xpath(pro.getProperty("CopyDNOtherwiseClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Thread.sleep(1500);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);

			search.clear();
			search.sendKeys(CopyDNOtherwiseID);
			Thread.sleep(2000);
			search.sendKeys(Keys.TAB);
			Thread.sleep(1000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement editDNOtherwiseButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditDNOtherwiseButton"))));
			editDNOtherwiseButton.click();
			Thread.sleep(3000);

			WebElement DNOtherwiseMemo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DNOtherwiseMemo"))));
			DNOtherwiseMemo.sendKeys("Update Debit Note Otherwise Memo");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DNOtherwiseEditSave"))));
			driver.findElement(By.xpath(pro.getProperty("DNOtherwiseEditSave"))).click();
			Thread.sleep(10000);

			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			Thread.sleep(10000);

			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("EditDNOtherwiseClose"))));
				driver.findElement(By.xpath(pro.getProperty("EditDNOtherwiseClose"))).click();
				Thread.sleep(5000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);

			search.clear();
			search.sendKeys(CopyDNOtherwiseID);
			Thread.sleep(2000);
			search.sendKeys(Keys.TAB);

			Thread.sleep(10000);

			try {
				Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

				Thread.sleep(3000);
				if (driver
						.findElement(By
								.xpath("//div[@id='DebitNoteDetails']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
					System.out.println("Debit Note Otherwise " + CopyDNOtherwiseID + " is edited Successfully");
				} else {
					System.out.println("Debit Note Otherwise " + CopyDNOtherwiseID + " is not edited Successfully");
				}

			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);
			}

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteDNOtherwiseButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteDNOtherwiseButton"))).click();

			Thread.sleep(5000);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteDNOtherwisePermButton2"))));
			driver.findElement(By.xpath(pro.getProperty("deleteDNOtherwisePermButton2"))).click();

			Thread.sleep(5000);

			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			Thread.sleep(5000);

			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			Thread.sleep(10000);
			Utilities.clickButton("OK", 500, driver);
			/*
			 * try{ Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"),
			 * "Uncheck", driver); search.clear();
			 * search.sendKeys(CopyDNOtherwiseID); Thread.sleep(2000);
			 * 
			 * if(driver.findElement(By.xpath(
			 * "//div[@id='DebitNoteDetails']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"
			 * )).getText().equalsIgnoreCase("No results to display")) {
			 * System.out.println("Debit Note Otherwise "
			 * +CopyDNOtherwiseID+"  is Deleted Successfully"); } else {
			 * System.out.println("Debit Note Otherwise "
			 * +CopyDNOtherwiseID+"  is not Deleted Successfully"); } }
			 * catch(Exception exp) { System.out.println("Debit Note Otherwise "
			 * +CopyDNOtherwiseID+" is Successfully deleted"); }
			 */

			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);

		} catch (Exception EX) {
			Thread.sleep(10000);
			Utilities.handleError(EX, driver);
		}
	}

	////////////////////////////////////////// DN AGAINST CUSTOMER WITH MULTIPLE
	////////////////////////////////////////// ACCOUNTS AT THE LINE
	////////////////////////////////////////// LEVEL/////////////////////////////////////////////////////

	public static void create_debitNoteAgainstCustomer2(String documentid, String productid, String customerid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			Properties DebitNoteAgainstCustomer = Utilities.fetchProValue("OR_DebitNoteAgainstCustomer.properties");

			Thread.sleep(3000);
			// WebDriverWait wait = new WebDriverWait(driver, 30);
			// clicked on Document
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(DebitNoteAgainstCustomer.getProperty("DebitNoteIcon"))));
			driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("DebitNoteIcon"))).click();
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(DebitNoteAgainstCustomer.getProperty("DebitNoteCustomer"))));
			driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("DebitNoteCustomer"))).click();
			Thread.sleep(3000);
			// .//*[text()='Debit Note against
			// Customer:']//following-sibling::div[1]/div/input
			// .//*[text()='Debit Note against Purchase
			// Invoice:']//following-sibling::div[1]/div/input
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(DebitNoteAgainstCustomer.getProperty("ClickOnSubmit"))));
			driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("ClickOnSubmit"))).click();
			Thread.sleep(8000);

			// sequen format document no.
			WebElement squence = driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("SequenceFormat")));
			squence.clear();
			squence.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(DebitNoteAgainstCustomer.getProperty("DocumentName"))));

			driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("DocumentName")))
					.sendKeys("DNC" + documentid);

			// driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("DocumentName"))).sendKeys("DNcus"+documentid);

			Thread.sleep(2000);// pro

			// enter vendor name
			WebElement customer = driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("EnterCustomerId")));
			customer.clear();
			customer.sendKeys(customerid);
			Thread.sleep(3000);// pro
			customer.sendKeys(Keys.ENTER);
			// ----------------------------------------------------------------------------------------------------------------

			// enter account name
			WebElement accountselect = driver
					.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("EnterAccount")));
			accountselect.click();
			Thread.sleep(2000);// pro
			Robot acc1 = new Robot();

			Utilities.sendText("Utilities");
			Thread.sleep(2000);// pro
			acc1.keyPress(KeyEvent.VK_ENTER);
			acc1.keyRelease(KeyEvent.VK_ENTER);

			driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("Memo"))).click();
			Thread.sleep(2000);// pro
			WebElement ammount1 = driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("EnterAmount")));
			ammount1.click();
			Thread.sleep(2000);// pro

			Utilities.sendText("100");
			Thread.sleep(2000);// pro

			driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("Memo"))).click();
			Thread.sleep(2000);// pro
			WebElement reason = driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("EnterReason")));
			reason.click();
			Thread.sleep(2000);// pro

			acc1.keyPress(KeyEvent.VK_DOWN);
			acc1.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);// pro
			acc1.keyPress(KeyEvent.VK_DOWN);
			acc1.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);// pro
			acc1.keyPress(KeyEvent.VK_ENTER);
			acc1.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);// pro
			driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("Memo"))).click();

			WebElement accountselect2 = driver
					.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("EnterAccount2")));
			accountselect2.click();
			Thread.sleep(2000);// pro
			Robot acc2 = new Robot();

			Utilities.sendText("Travel");
			Thread.sleep(2000);// pro
			acc2.keyPress(KeyEvent.VK_ENTER);
			acc2.keyRelease(KeyEvent.VK_ENTER);

			driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("Memo"))).click();
			Thread.sleep(2000);// pro
			WebElement ammount2 = driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("EnterAmount2")));
			ammount2.click();
			Thread.sleep(2000);// pro

			Utilities.sendText("100");
			Thread.sleep(2000);// pro

			driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("Memo"))).click();
			Thread.sleep(2000);// pro
			WebElement reason2 = driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("EnterReason2")));
			reason2.click();
			Thread.sleep(2000);// pro

			acc1.keyPress(KeyEvent.VK_DOWN);
			acc1.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);// pro
			acc1.keyPress(KeyEvent.VK_DOWN);
			acc1.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);// pro
			acc1.keyPress(KeyEvent.VK_ENTER);
			acc1.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);// pro
			driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("Memo"))).click();

			// Click on Save
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(DebitNoteAgainstCustomer.getProperty("ClickOnSave"))));
			driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("ClickOnSave"))).click();
			Thread.sleep(2000);

			// Click on Yes
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(DebitNoteAgainstCustomer.getProperty("ClickOnYes"))));
			driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("ClickOnYes"))).click();
			Thread.sleep(1000);

			// Click on OK
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(DebitNoteAgainstCustomer.getProperty("ClickOnOk"))));
			driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("ClickOnOk"))).click();
			Thread.sleep(1000);

			// Click on close
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(DebitNoteAgainstCustomer.getProperty("CloseForm"))));
			driver.findElement(By.xpath(DebitNoteAgainstCustomer.getProperty("CloseForm"))).click();

			System.out.println("****** Debit Note sucessfully created : " + "cus" + documentid);
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}

	///////////////////////////////////////// Verification of DN against
	///////////////////////////////////////// Customer
	///////////////////////////////////////// Multiple/////////////////////////////////////////////////////////////////////

	public static void verify_debitNoteAgainstCustomer2(String documentid, String productid, String customer_ID,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			documentid = "DNC" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_DebitNoteOtherWise.properties");
			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DebitNoteReport"))));
			driver.findElement(By.xpath(pro.getProperty("DebitNoteReport"))).click();
			Thread.sleep(5000);// pro

			WebElement searchtype = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='view22DebitNoteDetails']")));
			searchtype.clear();
			searchtype.sendKeys("Debit Note for Customers");
			Thread.sleep(2000);
			searchtype.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();
			System.out.println(e);
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				Thread.sleep(10000);
				if (header.equals("Debit Note No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d1");
				}

				else if (header.equals("Name")) {
					assertEquals(customer_ID + "Name",
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d2");
				}
			}
			System.out.println(" Verified Debit Note for Customers [" + documentid + "] !!!! ");
			Thread.sleep(500);
			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);

		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	/////////////////////////////////////////////// Copy edit delete DN against
	/////////////////////////////////////////////// Customer
	/////////////////////////////////////////////// Multiple///////////////////////////////////////////////////////////////////////

	public static void CopyEditDelete_debitNoteAgainstCustomer2(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "DNC" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_DebitNoteAgainstCustomer.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DebitNoteReport"))));
			driver.findElement(By.xpath(pro.getProperty("DebitNoteReport"))).click();
			Thread.sleep(5000);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			WebElement dnType = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DNTypeDD"))));
			dnType.click();
			Thread.sleep(2000);
			Robot dnTypeRobot = new Robot();

			Utilities.sendText("Debit Note for Customers");
			Thread.sleep(2000);
			dnTypeRobot.keyPress(KeyEvent.VK_ENTER);
			dnTypeRobot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);

			WebElement checkbox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentCheckBox"))));
			checkbox.click();
			Thread.sleep(2000);

			WebElement copyAgainstCustomerButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyDNAgainstCustomerButton"))));
			copyAgainstCustomerButton.click();
			Thread.sleep(2000);

			String CopyDNAgainstCustomerID = "Copy" + documentid;

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyDNAgainstCustomerId"))));
			driver.findElement(By.xpath(pro.getProperty("copyDNAgainstCustomerId"))).sendKeys(CopyDNAgainstCustomerID);

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("saveCopiedDNAgainstCustomerId"))));

			driver.findElement(By.xpath(pro.getProperty("saveCopiedDNAgainstCustomerId"))).click();

			Thread.sleep(3000);

			Robot r = new Robot();
			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 1500, driver);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("CopyDNAgainstCustomerClose"))));
				driver.findElement(By.xpath(pro.getProperty("CopyDNAgainstCustomerClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			search.clear();
			search.sendKeys(CopyDNAgainstCustomerID);
			Thread.sleep(5000);
			search.sendKeys(Keys.TAB);

			Thread.sleep(5000);
			checkbox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentCheckBox"))));
			checkbox.click();
			Thread.sleep(5000);

			WebElement editDNAgainstCustomerButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditDNAgainstCustomerButton"))));
			editDNAgainstCustomerButton.click();
			Thread.sleep(3000);

			WebElement DNAgainstCustomerMemo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DNAgainstCustomerMemo"))));
			DNAgainstCustomerMemo.sendKeys("Update Debit Note Against Customer Memo");

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DNAgainstCustomerEditSave"))));
			driver.findElement(By.xpath(pro.getProperty("DNAgainstCustomerEditSave"))).click();
			Thread.sleep(3000);

			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 1500, driver);
			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("EditDNAgainstCustomerClose"))));
				driver.findElement(By.xpath(pro.getProperty("EditDNAgainstCustomerClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Thread.sleep(3000);
			search.clear();
			search.sendKeys(CopyDNAgainstCustomerID);
			Thread.sleep(3000);
			search.sendKeys(Keys.TAB);

			Thread.sleep(3000);

			try {
				new WebDriverWait(driver, 30).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("DocumentCheckBox"))));
				driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).click();
				Thread.sleep(3000);
				if (driver
						.findElement(By
								.xpath("//div[@id='DebitNoteDetails']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
					System.out.println(
							"Debit Note Against Customer " + CopyDNAgainstCustomerID + " is edited Successfully");
				} else {
					System.out.println(
							"Debit Note Against Customer " + CopyDNAgainstCustomerID + " is not edited Successfully");
				}

			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);
			}

			/*
			 * new WebDriverWait(driver,30).until(ExpectedConditions.
			 * visibilityOfElementLocated(By.xpath(pro.getProperty(
			 * "deleteDNAgainstCustomerButton"))));
			 * driver.findElement(By.xpath(pro.getProperty(
			 * "deleteDNAgainstCustomerButton"))).click();
			 * 
			 * Thread.sleep(2000); new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * visibilityOfElementLocated(By.xpath(pro.getProperty(
			 * "deleteDNAgainstCustomerPermButton2")))); Thread.sleep(5000);
			 * driver.findElement(By.xpath(pro.getProperty(
			 * "deleteDNAgainstCustomerPermButton2"))).click();
			 * Thread.sleep(3000);
			 */

			Utilities.HoverandClick(pro.getProperty("deleteDNAgainstCustomerButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteDNAgainstCustomerPermButton2"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);

			Thread.sleep(3000);

			try {
				search.clear();
				search.sendKeys(CopyDNAgainstCustomerID);
				Thread.sleep(3000);

				if (driver
						.findElement(By
								.xpath("//div[@id='DebitNoteDetails']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("No results to display")) {
					System.out.println(
							"Debit Note Against Customer " + CopyDNAgainstCustomerID + "  is Deleted Successfully");
				} else {
					System.out.println(
							"Debit Note Against Customer " + CopyDNAgainstCustomerID + "  is not Deleted Successfully");
				}
			} catch (Exception exp) {
				System.out
						.println("Debit Note Against Customer " + CopyDNAgainstCustomerID + " is Successfully deleted");
			}

			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);

		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}
	}
	///////////////////////////////////////////////////////// DN UNDERCHARGE
	///////////////////////////////////////////////////////// WITH MULTIPLE
	///////////////////////////////////////////////////////// SI//////////////////////////////////////////////////////////////////////

	public static void create_debitNoteUnderCharge2(String documentid, String productid, String customerid,
			String Quantity, WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			// documentid = "DnUC"+documentid;
			Properties pro = Utilities.fetchProValue("OR_DebitNoteCharged.properties");
			WebDriverWait wait = new WebDriverWait(driver, 40);

			Utilities.waitandClick(pro.getProperty("CreateDebitNoteIcon"), driver);
			Utilities.waitandClick(pro.getProperty("SelectDNunderCharge"), driver);
			Utilities.waitandClick(pro.getProperty("submitButton"), driver);

			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.enterTextNormalBox("DnUC" + documentid, pro.getProperty("debitNoteNo"), driver);

			Utilities.enterTextandSelect(customerid, pro.getProperty("customerInput"), driver);

			Utilities.enterTextInDropDown("Yes", pro.getProperty("link"), driver);
			Utilities.enterTextInDropDown("Sales Invoice", pro.getProperty("linkTo"), driver);
			Utilities.waitandClick(pro.getProperty("Search"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.waitandClick(pro.getProperty("Date"), driver);
			Utilities.waitandClick(pro.getProperty("TodaysDate"), driver);
			Utilities.waitandClick(pro.getProperty("Fetch"), driver);
			Utilities.waitandClick(pro.getProperty("Checkbox"), driver);
			Utilities.waitandClick(pro.getProperty("Addbutton"), driver);

			/*
			 * String drpDwnArrow=
			 * "//input[@name='ordernumber']/following-sibling::span/img[2]";
			 * String itemInputBoxLocator =
			 * "//input[@name='ordernumber']/following-sibling::input"; String
			 * itemName="SI"+documentid; String
			 * rslTable="//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div/table/tbody/tr/td[1]"
			 * ;
			 * 
			 * Utilities.selectItemfromDropDown(drpDwnArrow,
			 * itemInputBoxLocator, itemName, rslTable, driver);
			 * Utilities.click_element(pro.getProperty("memo"), driver);
			 * Thread.sleep(1000);
			 */

			List<WebElement> headers = driver
					.findElements(By.xpath("//div[@class='x-grid3-header']/div/div/table/thead/tr/td/div"));
			int headSize = headers.size();

			Robot r = new Robot();
			int amtIndex = 0;
			int TotalAdded = Utilities.totalSize(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[contains(@class,'x-grid3-row')]",
					driver);

			for (int i = 1; i <= headSize; i++) {

				String headName = driver
						.findElement(
								By.xpath("*//div[@class='x-grid3-header']/div/div/table/thead/tr/td[" + i + "]/div"))
						.getText();
				if (headName.equalsIgnoreCase("Quantity")) {
					amtIndex = i;
				}
			}
			// add Qty
			for (int i = 1; i <= TotalAdded - 1; i++) {
				Utilities.clickAndEnterText(Quantity,
						"*//div[@class='x-grid3-scroller']/div/div[" + i + "]/table/tbody/tr/td[" + amtIndex + "]/div",
						driver);
				// to move focus
				Utilities.click_element(pro.getProperty("memo"), driver);
			}

			// to scroll end
			Utilities.justHover("//*[@class='pwnd delete-gridrow']", driver);
			Thread.sleep(1000);
			int headRsn = 0;
			for (int i = 1; i <= headSize; i++) {
				String headName = driver
						.findElement(
								By.xpath("*//div[@class='x-grid3-header']/div/div/table/thead/tr/td[" + i + "]/div"))
						.getText();
				if (headName.equalsIgnoreCase("Reason")) {
					headRsn = i;
				}
			}
			for (int j = 1; j <= TotalAdded - 1; j++) {
				Utilities.HoverandClick(
						"//div[@class='x-grid3-scroller']/div/div[" + j + "]/table/tbody/tr/td[" + headRsn + "]/div",
						driver);
				Thread.sleep(1000);
				r.keyPress(KeyEvent.VK_DOWN);
				r.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				r.keyPress(KeyEvent.VK_DOWN);
				r.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);// pro
			}

			Utilities.click_element(pro.getProperty("memo"), driver);

			Utilities.clickButton("Save", 800, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 800, driver);

			WebElement selectExit = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//li[@id='as__debitnoteagaintcustomerformalaysia']/a[1]")));
			selectExit.click();

			System.out.println(
					"* * * Debit Note Undercharged created successfully for : [" + documentid + "] * * * * * ");

		}

		catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	/////////////////////////////////////////////////////////// DN OVERCHARGE
	/////////////////////////////////////////////////////////// WITH MULTIPLE
	/////////////////////////////////////////////////////////// PI/////////////////////////////////////////////////////////////////////////

	public static void create_debitNoteOverCharge2(String documentid, String productid, String vendorid,
			String Quantity, WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			// documentid = "DnUC"+documentid;
			Properties pro = Utilities.fetchProValue("OR_DebitNoteCharged.properties");
			WebDriverWait wait = new WebDriverWait(driver, 40);

			Utilities.waitandClick(pro.getProperty("CreateDebitNoteIcon"), driver);
			Utilities.waitandClick(pro.getProperty("SelectDNOverCharge"), driver);
			Utilities.waitandClick(pro.getProperty("submitButton"), driver);

			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.enterTextNormalBox("DnOC" + documentid, pro.getProperty("debitNoteNo"), driver);

			Utilities.enterTextandSelect(vendorid, pro.getProperty("vendorInput"), driver);
			Utilities.click_element(pro.getProperty("memo"), driver);

			Utilities.enterTextInDropDown("Yes", pro.getProperty("link"), driver);
			Utilities.enterTextInDropDown("Purchase Invoice", pro.getProperty("linkTo"), driver);

			Utilities.waitandClick(pro.getProperty("Search"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.waitandClick(pro.getProperty("Date2"), driver);
			Utilities.waitandClick(pro.getProperty("TodaysDate2"), driver);
			Utilities.waitandClick(pro.getProperty("Fetch"), driver);
			Thread.sleep(10000);
			Utilities.waitandClick(pro.getProperty("Checkbox2"), driver);
			Utilities.waitandClick(pro.getProperty("Addbutton2"), driver);

			/*
			 * String drpDwnArrow=
			 * "//input[@name='ordernumber']/following-sibling::span/img[2]";
			 * String itemInputBoxLocator =
			 * "//input[@name='ordernumber']/following-sibling::input"; String
			 * itemName="PurInvo"+documentid; String
			 * rslTable="//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div/table/tbody/tr/td[1]"
			 * ;
			 * 
			 * Utilities.selectItemfromDropDown(drpDwnArrow,
			 * itemInputBoxLocator, itemName, rslTable, driver);
			 * Utilities.click_element(pro.getProperty("memo"), driver);
			 * Thread.sleep(1000);
			 */

			List<WebElement> headers = driver
					.findElements(By.xpath("//div[@class='x-grid3-header']/div/div/table/thead/tr/td/div"));
			int headSize = headers.size();
			Robot r = new Robot();
			int amtIndex = 0;
			int TotalAdded = Utilities.totalSize(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[contains(@class,'x-grid3-row')]",
					driver);

			/*
			 * for(int i=1; i<=headSize; i++) { String headName=
			 * driver.findElement(By.xpath(
			 * "//div[@class='x-grid3-header']/div/div/table/thead/tr/td["+i+
			 * "]/div")).getText(); if(headName.equalsIgnoreCase("Quantity")){
			 * amtIndex=i; }
			 * 
			 * 
			 * // add Qty for(int i=1; i<=TotalAdded-1; i++) {
			 * Utilities.clickAndEnterText(Quantity,
			 * "//div[@class='x-grid3-scroller']/div/div["+i+
			 * "]/table/tbody/tr/td["+amtIndex+"]/div", driver); //to move focus
			 * Utilities.click_element(pro.getProperty("memo"), driver); }
			 */

			// to scroll end
			Utilities.justHover("//*[@class='pwnd delete-gridrow']", driver);
			Thread.sleep(10000);
			int headRsn = 0;
			for (int i = 1; i <= headSize; i++) {
				String headName = driver
						.findElement(
								By.xpath("*//div[@class='x-grid3-header']/div/div/table/thead/tr/td[" + i + "]/div"))
						.getText();
				if (headName.equalsIgnoreCase("Reason")) {
					headRsn = i;
				}
			}
			for (int j = 1; j <= TotalAdded - 1; j++) {
				Utilities.HoverandClick(
						"//div[@class='x-grid3-scroller']/div/div[" + j + "]/table/tbody/tr/td[" + headRsn + "]/div",
						driver);
				Thread.sleep(1000);
				r.keyPress(KeyEvent.VK_DOWN);
				r.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				r.keyPress(KeyEvent.VK_DOWN);
				r.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);// pro
			}

			Utilities.click_element(pro.getProperty("memo"), driver);

			Utilities.clickButton("Save", 800, driver);
			Utilities.clickButton("Yes", 800, driver);
			Utilities.clickButton("OK", 800, driver);

			WebElement selectExit = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='as__debitnoteForOvercharge']/a[1]")));
			selectExit.click();

			System.out
					.println("* * * Debit Note Overcharged created successfully for : [" + documentid + "] * * * * * ");

		}

		catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// Edit-Delete
	public static void EditDelete_DebitNoteCharged(String documentid, String UNDorOVR, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			Properties pro = Utilities.fetchProValue("OR_DebitNoteAgainstCustomer.properties");
			Utilities.waitandClick(pro.getProperty("DebitNoteReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			if (UNDorOVR.equalsIgnoreCase("UND")) {
				Utilities.enterTextandSelect("Debit Note for Undercharged Sales Invoice",
						"//*[@id='view22DebitNoteDetails']", driver);
			}
			if (UNDorOVR.equalsIgnoreCase("OVR")) {
				Utilities.enterTextandSelect("Debit Note for Overcharged Purchase Invoice",
						"//*[@id='view22DebitNoteDetails']", driver);
			}
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			// Edit
			Utilities.HoverandClick(pro.getProperty("EditDNAgainstCustomerButton"), driver);
			Utilities.formLoaded(driver);
			Utilities.enterTextNormalBox("**EDit test case for Credit Note [" + documentid + "]",
					"//textarea[@name='memo']", driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);

			if (UNDorOVR.equalsIgnoreCase("UND")) {
				Utilities.click_element("//*[contains(@id,'as__debitnoteagaintcustomerformalaysiaedit')]/a[1]", driver); // close
																															// Undercharge
																															// form
			}
			if (UNDorOVR.equalsIgnoreCase("OVR")) {
				Utilities.click_element("//*[contains(@id,'as__debitnoteForOverchargeEdit')]/a[1]", driver); // close
																												// Oercharge
																												// form
			}

			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println("**** EDIT test case for Credit Note [" + documentid + "] is PASS !!!!");

			// Delete
			Utilities.HoverandClick(pro.getProperty("deleteDNAgainstCustomerButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteDNAgainstCustomerPermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);

			// Confirmation Delete
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			WebElement deletedMsg = driver
					.findElement(By.xpath("//*[contains(text(),' Get Started by adding a Debit Note now')]"));
			if (deletedMsg.isDisplayed()) {
				System.out.println("*** DELETE test case for Debit note No. [" + documentid + "] is PASS !!");
			}

			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);

		} catch (Exception EX) {
			System.out.println("**** EDIT-DELETE test case for Debit Note [" + documentid + "]  is FAIL FAIL  !!!!");
			Utilities.handleError(EX, driver);
		}
	}

	//////////////////////////////////////// Verification of DN UNDERCHARGE AND
	//////////////////////////////////////// OVERCHARGE
	//////////////////////////////////////// MULTIPLE///////////////////////////////////////////////////////////////////////
	public static void verify_DebitNoteCharged2(String documentid, String underORover, String customer_ID,
			String vendor_ID, WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_DebitNoteCharged.properties");
			WebDriverWait wait = new WebDriverWait(driver, 40);

			// clicked on Document
			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DebitNoteReport"))));
			element.click();
			Thread.sleep(2000);// pro

			Utilities.clickCheckBox(pro.getProperty("documentCheck"), "uncheck", driver);
			WebElement selectType = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("selectTyp"))));
			selectType.click();

			if (underORover.equalsIgnoreCase("Undercharged")) {
				selectType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]//div[text()='Debit Note for Undercharged Sales Invoice']")));
				selectType.click();
			}

			else if (underORover.equalsIgnoreCase("Overcharged")) {
				selectType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]//div[text()='Debit Note for Overcharged Purchase Invoice']")));
				selectType.click();
			}
			Thread.sleep(3000);

			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			element.clear();
			element.sendKeys("DnUC" + documentid);
			Thread.sleep(3000);

			List<WebElement> headers = driver.findElements(By.xpath("//div[text()='Debit Note No']/ancestor::tr/td"));
			int headSize = headers.size();
			int customerIdIndex = 0, nameIndex = 0;

			for (int i = 1; i <= headSize; i++) {
				String headName = driver
						.findElement(By.xpath("//div[text()='Debit Note No']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				// System.out.println(headName);
				if (headName.equalsIgnoreCase("Debit Note No")) {
					customerIdIndex = i;
				}

				else if (headName.equalsIgnoreCase("Name")) {
					nameIndex = i;
				}
			}
			// div[text()='Credit Note
			// No']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["++"]/div
			WebElement docCheckBox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("documentCheck"))));

			if (docCheckBox.isDisplayed()) {

				assertEquals("DnUC" + documentid,
						driver.findElement(By
								.xpath("//div[text()='Debit Note No']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ customerIdIndex + "]/div"))
								.getText());

				assertEquals(customer_ID + "Name",
						driver.findElement(By
								.xpath("//div[text()='Debit Note No']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ nameIndex + "]/div"))
								.getText());
			}

			System.out.println("* * * * * Verified DebitNote for [" + underORover + "] with document id : ["
					+ documentid + "] with acc Name [" + customer_ID + "Name" + "] * * * *");
			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);

		}

		catch (Exception EX) {
			System.out.println("* * * * * Verification FAILED DebitNote for [" + underORover + "] with document id : "
					+ documentid);
			Utilities.handleError(EX, driver);
		}

	}

	public static void verify_DebitNoteCharged3(String documentid, String underORover, String customer_ID,
			String vendor_ID, WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_DebitNoteCharged.properties");
			WebDriverWait wait = new WebDriverWait(driver, 40);

			// clicked on Document
			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DebitNoteReport"))));
			element.click();
			Thread.sleep(2000);// pro

			Utilities.clickCheckBox(pro.getProperty("documentCheck"), "uncheck", driver);
			WebElement selectType = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("selectTyp"))));
			selectType.click();

			if (underORover.equalsIgnoreCase("Overcharged")) {
				selectType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]//div[text()='Debit Note for Overcharged Purchase Invoice']")));
				selectType.click();
			}
			Thread.sleep(3000);

			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			element.clear();
			element.sendKeys("DnOC" + documentid);
			Thread.sleep(3000);

			List<WebElement> headers = driver.findElements(By.xpath("//div[text()='Debit Note No']/ancestor::tr/td"));
			int headSize = headers.size();
			int customerIdIndex = 0, nameIndex = 0;

			for (int i = 1; i <= headSize; i++) {
				String headName = driver
						.findElement(By.xpath("//div[text()='Debit Note No']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				// System.out.println(headName);
				if (headName.equalsIgnoreCase("Debit Note No")) {
					customerIdIndex = i;
				}

				else if (headName.equalsIgnoreCase("Name")) {
					nameIndex = i;
				}
			}
			// div[text()='Credit Note
			// No']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["++"]/div
			WebElement docCheckBox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("documentCheck"))));

			if (docCheckBox.isDisplayed()) {

				assertEquals("DnOC" + documentid,
						driver.findElement(By
								.xpath("//div[text()='Debit Note No']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ customerIdIndex + "]/div"))
								.getText());

				assertEquals(vendor_ID,
						driver.findElement(By
								.xpath("//div[text()='Debit Note No']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ nameIndex + "]/div"))
								.getText());
			}

			System.out.println("* * * * * Verified DebitNote for [" + underORover + "] with document id : ["
					+ documentid + "] with acc Name [" + vendor_ID + "] * * * *");
			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);

		}

		catch (Exception EX) {
			System.out.println("* * * * * Verification FAILED DebitNote for [" + underORover + "] with document id : "
					+ documentid);
			Utilities.handleError(EX, driver);
		}

	}

	////////////////////////////////////////////////// Edit-Delete DN
	////////////////////////////////////////////////// Undercharge and
	////////////////////////////////////////////////// Overcharge///////////////////////////////////////////////////////////////////////

	// OVERCHARGE

	public static void EditDelete_debitNoteOvercharged(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_DebitNoteCharged.properties");

			Utilities.waitandClick(pro.getProperty("DebitNoteReport"), driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			Utilities.enterTextInDropDown("DnOC" + documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			// Edit
			Utilities.HoverandClick(pro.getProperty("EditDNOVRButton"), driver);
			// String
			// formLoaded="//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td[3]";
			// Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			Utilities.enterTextNormalBox("Performing EDIT test case for Debit Note No:- " + documentid,
					pro.getProperty("memo"), driver);
			Utilities.HoverandClick(pro.getProperty("DNOVREditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("EditDNOVRandUNDRClose"), driver);
			Utilities.enterTextInDropDown("DnOC" + documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println("*** EDIT test case for Debit note No. [DnOC" + documentid + "] is PASS !!");

			// Delete
			Utilities.HoverandClick(pro.getProperty("deleteDNOVRButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteDNOVRPermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);

			// Confirmation Delete
			Utilities.enterTextInDropDown("DnOC" + documentid, pro.getProperty("QuickSearch"), driver);
			WebElement deletedMsg = driver.findElement(
					By.xpath("//div[@id='as']/div[2]/div/div[2]/div/div/div/div/div/div[2]/div/div[3]/div/div"));
			if (deletedMsg.isDisplayed()) {
				System.out.println("*** DELETE test case for Debit note No. [DnOC" + documentid + "] is PASS !!");
			}

			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);
		} catch (Exception EX) {
			System.out.println("*** EDIT-DELETE test case for Debit note No. [" + documentid + "] is FAILED !!");
			Utilities.handleError(EX, driver);
		}
	}

	// UNDERCHARGE

	public static void EditDelete_debitNoteUndercharged(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_DebitNoteCharged.properties");

			Utilities.waitandClick(pro.getProperty("DebitNoteReport"), driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			Utilities.enterTextandSelect("Debit Note for Customers", "//*[@id='view22DebitNoteDetails']", driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Utilities.enterTextInDropDown("DnUC" + documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(10000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			// Edit
			Utilities.HoverandClick(pro.getProperty("EditDNUNDRButton"), driver);
			Utilities.enterTextNormalBox("Performing EDIT test case for Debit Note No:- " + documentid,
					pro.getProperty("memo"), driver);
			Utilities.HoverandClick(pro.getProperty("DNUNDREditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("EditDNOVRandUNDRClose"), driver);
			Utilities.enterTextInDropDown("DnUC" + documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println("*** EDIT test case for Debit note No. [DnUC" + documentid + "] is PASS !!");

			// Delete
			Utilities.HoverandClick(pro.getProperty("deleteDNUNDRButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteDNUNDRPermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);

			// Confirmation Delete
			Utilities.enterTextInDropDown("DnUC" + documentid, pro.getProperty("QuickSearch"), driver);
			WebElement deletedMsg = driver.findElement(
					By.xpath("//div[@id='as']/div[2]/div/div[2]/div/div/div/div/div/div[2]/div/div[3]/div/div"));
			if (deletedMsg.isDisplayed()) {
				System.out.println("*** DELETE test case for Debit note No. [DnUC" + documentid + "] is PASS !!");
			}

			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);
		} catch (Exception EX) {
			System.out.println("*** COPY-EDIT-DELETE test case for Debit note No. [" + documentid + "] is FAILED !!");
			Utilities.handleError(EX, driver);
		}
	}

	////////////////////////////////////////////////////////////// CN Against
	////////////////////////////////////////////////////////////// With Multiple
	////////////////////////////////////////////////////////////// SI//////////////////////////////////////////////////////////////////////////

	public static void create_creditNoteAgainstsalesInvoice2(String documentid, String documentid21, String productid,
			String customer_ID, WebDriver driver) throws InterruptedException, AWTException, IOException {

		{
			try {

				Properties CreditNoteSalesInvoice = Utilities.fetchProValue("OR_CreditNoteSalesInvoice.properties");

				Thread.sleep(3000);
				// WebDriverWait wait = new WebDriverWait(driver, 30);
				// clicked on Document
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(CreditNoteSalesInvoice.getProperty("CreditNoteIcon"))));
				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("CreditNoteIcon"))).click();
				Thread.sleep(2000);// pro

				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(CreditNoteSalesInvoice.getProperty("CreditSalesInvoice"))));
				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("CreditSalesInvoice"))).click();
				Thread.sleep(3000);

				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(CreditNoteSalesInvoice.getProperty("ClickSubmit"))));
				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("ClickSubmit"))).click();
				Thread.sleep(8000);

				// sequen format document no.
				WebElement squence = driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("CreditSequence")));
				squence.clear();
				squence.sendKeys("NA");
				Thread.sleep(1000);// pro
				squence.sendKeys(Keys.ENTER);
				Thread.sleep(2000);// pro

				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(CreditNoteSalesInvoice.getProperty("CreditNoteNo"))));
				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("CreditNoteNo")))
						.sendKeys("CrSi" + documentid);

				Thread.sleep(2000);// pro

				// enter customer name
				WebElement customer = driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("Customerid")));
				customer.clear();
				customer.sendKeys(customer_ID);
				Thread.sleep(3000);// pro
				customer.sendKeys(Keys.ENTER);

				WebElement accountselect1 = driver
						.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("AccountName1")));
				accountselect1.click();
				Thread.sleep(2000);// pro

				Utilities.sendText("Utilities");

				Thread.sleep(2000);// pro
				Robot acc1 = new Robot();
				acc1.keyPress(KeyEvent.VK_ENTER);
				acc1.keyRelease(KeyEvent.VK_ENTER);

				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("memo"))).click();
				Thread.sleep(2000);// pro
				WebElement ammount1 = driver
						.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("AccountAmount1")));
				ammount1.click();
				Thread.sleep(2000);// pro

				Utilities.sendText("1");
				Thread.sleep(2000);// pro

				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("memo"))).click();
				Thread.sleep(2000);// pro
				WebElement reason = driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("Reason")));
				reason.click();
				Thread.sleep(2000);// pro

				acc1.keyPress(KeyEvent.VK_DOWN);
				acc1.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				acc1.keyPress(KeyEvent.VK_DOWN);
				acc1.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				acc1.keyPress(KeyEvent.VK_ENTER);
				acc1.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);// pro
				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("memo"))).click();

				Thread.sleep(3000);

				WebElement accountselect2 = driver
						.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("AccountName2")));
				accountselect2.click();
				Thread.sleep(2000);// pro

				Utilities.sendText("Travel");

				Thread.sleep(2000);// pro
				Robot acc2 = new Robot();
				acc2.keyPress(KeyEvent.VK_ENTER);
				acc2.keyRelease(KeyEvent.VK_ENTER);

				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("memo"))).click();
				Thread.sleep(2000);// pro
				WebElement ammount2 = driver
						.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("AccountAmount2")));
				ammount2.click();
				Thread.sleep(2000);// pro

				Utilities.sendText("1");
				Thread.sleep(2000);// pro

				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("memo"))).click();
				Thread.sleep(2000);// pro
				WebElement reason2 = driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("Reason2")));
				reason2.click();
				Thread.sleep(2000);// pro

				acc1.keyPress(KeyEvent.VK_DOWN);
				acc1.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				acc1.keyPress(KeyEvent.VK_DOWN);
				acc1.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				acc1.keyPress(KeyEvent.VK_ENTER);
				acc1.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);// pro
				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("memo"))).click();

				WebElement salesinvoiceselect1 = driver
						.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("SalesInvoice1")));
				salesinvoiceselect1.click();
				Thread.sleep(2000);// pro

				Utilities.sendText("SI" + documentid);

				Thread.sleep(2000);// pro
				Robot acc3 = new Robot();
				acc3.keyPress(KeyEvent.VK_ENTER);
				acc3.keyRelease(KeyEvent.VK_ENTER);

				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("memo"))).click();
				Thread.sleep(2000);// pro
				WebElement invoiceamount1 = driver
						.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("SalesInvoiceAmount1")));
				invoiceamount1.click();
				Thread.sleep(2000);// pro

				Utilities.sendText("1");
				Thread.sleep(2000);// pro

				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("memo"))).click();
				Thread.sleep(2000);// pro

				WebElement salesinvoiceselect2 = driver
						.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("SalesInvoice2")));
				salesinvoiceselect2.click();
				Thread.sleep(2000);// pro

				Utilities.sendText("SIEx" + documentid21);

				Thread.sleep(2000);// pro
				Robot acc4 = new Robot();
				acc4.keyPress(KeyEvent.VK_ENTER);
				acc4.keyRelease(KeyEvent.VK_ENTER);

				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("memo"))).click();
				Thread.sleep(2000);// pro
				WebElement invoiceamount2 = driver
						.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("SalesInvoiceAmount2")));
				invoiceamount2.click();
				Thread.sleep(2000);// pro

				Utilities.sendText("1");
				Thread.sleep(2000);// pro

				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("memo"))).click();
				Thread.sleep(2000);// pro

				// Click on Save
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(CreditNoteSalesInvoice.getProperty("ClickSave"))));
				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("ClickSave"))).click();
				Thread.sleep(2000);

				// Click on Yes
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(CreditNoteSalesInvoice.getProperty("ClickYes"))));
				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("ClickYes"))).click();
				Thread.sleep(1000);

				// Click on OK
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(CreditNoteSalesInvoice.getProperty("ClickOk"))));
				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("ClickOk"))).click();
				Thread.sleep(1000);

				// Click on close
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(CreditNoteSalesInvoice.getProperty("CloseForm"))));
				driver.findElement(By.xpath(CreditNoteSalesInvoice.getProperty("CloseForm"))).click();

				System.out.println("Debit Note created with >>>> : " + "CrSi" + documentid);
			}

			catch (Exception EX) {
				Utilities.handleError(EX, driver);
			}

		}

	}

	private static String getProperty(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	////////////////////////////////////////////////////// Edit and Delete CN
	////////////////////////////////////////////////////// against SI
	////////////////////////////////////////////////////// Multiple/////////////////////////////////////////////////////////////////////////

	public static void EditDelete_creditNoteAgainstSalesInvoice2(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "CrSi" + documentid;
			Properties pro = Utilities.fetchProValue("OR_CreditNoteSalesInvoice.properties");

			Utilities.waitandClick(pro.getProperty("CreditNoteReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			// Edit
			Utilities.HoverandClick(pro.getProperty("EditCNPIButton"), driver);
			String formLoaded = "//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td[3]";
			Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			try {
				if (driver.findElement(By.xpath("//div[@class='x-window x-window-plain x-window-dlg']"))
						.isDisplayed()) {
					driver.findElement(By.xpath("//button[text()='Yes']")).click();
				}
			} catch (Exception Ex) {
				// no action needed
			}
			Utilities.enterTextNormalBox("Update Credit Note [" + documentid + "]Against Sales Invoice Memo",
					pro.getProperty("CNPIMemo"), driver);
			Utilities.HoverandClick(pro.getProperty("CNPIEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 500, driver);
			Utilities.click_element(pro.getProperty("EditCNPIClose"), driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println(
					"**** EDIT test case for Credit Note [" + documentid + "] Against Sales Invoice is PASS !!!!");

			// delete
			Utilities.HoverandClick(pro.getProperty("deleteCNPIButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteCNPIPermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(10000);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);

			// delete confrimation
			WebElement delConfirm = driver.findElement(
					By.xpath("//div[@id='as']/div[2]/div/div[2]/div/div/div/div/div/div[2]/div/div[3]/div/div"));
			if (delConfirm.isDisplayed()) {
				System.out.println("**** DELETE test case for Credit Note [" + documentid
						+ "] Against Sales Invoice is PASS !!!!");
			}

			String xpathOfelement = "//li[@id='as__CreditNoteDetails']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);
		} catch (Exception EX) {
			System.out.println("**** EDIT-DELETE test case for Credit Note [" + documentid
					+ "] Against Sales Invoice is FAIL FAIL  !!!!");
			Utilities.handleError(EX, driver);
		}

		///////////////////////////////////////////////////////// Verification
		///////////////////////////////////////////////////////// of CN gainst
		///////////////////////////////////////////////////////// SI////////////////////////////////////////////////////////////////////////////////
	}

	public static void verify_creditNoteAgainstSalesInvoice2(String documentid, String productid, String customer_id,
			WebDriver driver) throws InterruptedException, AWTException, IOException

	{

		try {

			documentid = "CrSi" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherWise.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreditNoteReport"))));
			driver.findElement(By.xpath(pro.getProperty("CreditNoteReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Credit Note No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d1");
				}

				else if (header.equals("Name")) {
					assertEquals(customer_id + "Name",
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d2");
				}

				else if (header.equals("Customer Code")) {
					assertEquals(customer_id,
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d3");
				}

				else if (header.equals("Amount")) {
					assertEquals("SGD 100.00",
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d4");
				}

			}
			System.out.println("verified credit Note Against SalesInvoice >[" + documentid + "] !!!");
			Thread.sleep(500);
			String xpathOfelement = "//li[@id='as__CreditNoteDetails']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	/////////////////////////////////////////////////////// CN OTHERWISE WITH
	/////////////////////////////////////////////////////// MULTIPLE
	/////////////////////////////////////////////////////// ACCOUNTS///////////////////////////////////////////////////////////////////////////

	public static void create_creditnoteotherwise2(String documentid, String documentid21, String productid,
			String customer_ID, WebDriver driver) throws InterruptedException, AWTException, IOException {

		{
			try {
				Properties CreditNoteOtherwise = Utilities.fetchProValue("OR_CreditNoteOtherwise.properties");

				Thread.sleep(3000);
				// WebDriverWait wait = new WebDriverWait(driver, 30);
				// clicked on Document
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(CreditNoteOtherwise.getProperty("CreditNoteIcon"))));
				driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("CreditNoteIcon"))).click();
				Thread.sleep(2000);// pro

				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(CreditNoteOtherwise.getProperty("SelectCreditOther"))));
				driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("SelectCreditOther"))).click();
				Thread.sleep(3000);

				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(CreditNoteOtherwise.getProperty("ClickSubmit"))));
				driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("ClickSubmit"))).click();
				Thread.sleep(8000);

				// sequen format document no.
				WebElement squence = driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("SequenceFormat")));
				squence.clear();
				squence.sendKeys("NA");
				Thread.sleep(1000);// pro
				squence.sendKeys(Keys.ENTER);
				Thread.sleep(2000);// pro

				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(CreditNoteOtherwise.getProperty("EnterCreditNo."))));
				driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("EnterCreditNo.")))
						.sendKeys("CrOther" + documentid);

				Thread.sleep(2000);// pro

				// enter customer name
				WebElement customer = driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("EnterCustomerId")));
				customer.clear();
				customer.sendKeys(customer_ID);
				Thread.sleep(3000);// pro
				customer.sendKeys(Keys.ENTER);

				WebElement accountselect1 = driver
						.findElement(By.xpath(CreditNoteOtherwise.getProperty("AccountName1")));
				accountselect1.click();
				Thread.sleep(2000);// pro

				Utilities.sendText("Utilities");

				Thread.sleep(2000);// pro
				Robot acc1 = new Robot();
				acc1.keyPress(KeyEvent.VK_ENTER);
				acc1.keyRelease(KeyEvent.VK_ENTER);

				driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("Memo"))).click();
				Thread.sleep(2000);// pro
				WebElement ammount1 = driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("AccountAmount1")));
				ammount1.click();
				Thread.sleep(2000);// pro

				Utilities.sendText("1");
				Thread.sleep(2000);// pro

				driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("Memo"))).click();
				Thread.sleep(2000);// pro
				WebElement reason = driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("EnterReason")));
				reason.click();
				Thread.sleep(2000);// pro

				acc1.keyPress(KeyEvent.VK_DOWN);
				acc1.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				acc1.keyPress(KeyEvent.VK_DOWN);
				acc1.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				acc1.keyPress(KeyEvent.VK_ENTER);
				acc1.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);// pro
				driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("Memo"))).click();

				Thread.sleep(3000);

				WebElement accountselect2 = driver
						.findElement(By.xpath(CreditNoteOtherwise.getProperty("AccountName2")));
				accountselect2.click();
				Thread.sleep(2000);// pro

				Utilities.sendText("Travel");

				Thread.sleep(2000);// pro
				Robot acc2 = new Robot();
				acc2.keyPress(KeyEvent.VK_ENTER);
				acc2.keyRelease(KeyEvent.VK_ENTER);

				driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("Memo"))).click();
				Thread.sleep(2000);// pro
				WebElement ammount2 = driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("AccountAmount2")));
				ammount2.click();
				Thread.sleep(2000);// pro

				Utilities.sendText("1");
				Thread.sleep(2000);// pro

				driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("Memo"))).click();
				Thread.sleep(2000);// pro
				WebElement reason2 = driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("EnterReason2")));
				reason2.click();
				Thread.sleep(2000);// pro

				acc1.keyPress(KeyEvent.VK_DOWN);
				acc1.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				acc1.keyPress(KeyEvent.VK_DOWN);
				acc1.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				acc1.keyPress(KeyEvent.VK_ENTER);
				acc1.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);// pro
				driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("Memo"))).click();

				// Click on Save
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(CreditNoteOtherwise.getProperty("ClickOnSave"))));
				driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("ClickOnSave"))).click();
				Thread.sleep(2000);

				// Click on Yes
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(CreditNoteOtherwise.getProperty("ClickOnYes"))));
				driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("ClickOnYes"))).click();
				Thread.sleep(1000);

				// Click on OK
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(CreditNoteOtherwise.getProperty("ClickOnOk"))));
				driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("ClickOnOk"))).click();
				Thread.sleep(1000);

				// Click on close
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(CreditNoteOtherwise.getProperty("CloseForm"))));
				driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("CloseForm"))).click();

				System.out.println("Debit Note created with >>>> : " + "CrOther" + documentid);

			} catch (Exception EX) {
				Utilities.handleError(EX, driver);
			}

		}

	}

	///////////////////////////////////////////////////////// Verification of CN
	///////////////////////////////////////////////////////// Otherwise
	///////////////////////////////////////////////////////// multiple/////////////////////////////////////////////////////////////////////////
	public static void verify_creditNoteotherwise2(String documentid, String productid, String customerid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			documentid = "CrOther" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherWise.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreditNoteReport"))));
			driver.findElement(By.xpath(pro.getProperty("CreditNoteReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();

			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Credit Note No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d1");
				}

				else if (header.equals("Name")) {
					assertEquals(customerid + "Name",
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d2");
				}

				else if (header.equals("Customer Code")) {
					assertEquals(customerid,
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d3");
				}

				else if (header.equals("Amount")) {
					assertEquals("SGD 100.00",
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d4");
				}

			}
			System.out.println(" verified Credit Note OtherWise for > > [" + documentid + "] Successfully !!");
			Thread.sleep(500);
			String xpathOfelement = "//li[@id='as__CreditNoteDetails']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	///////////////////////////////////////////////////////////// Edit-Delete CN
	///////////////////////////////////////////////////////////// Otherwise
	///////////////////////////////////////////////////////////// Multiple/////////////////////////////////////////////////////////////////

	public static void CopyEditDelete_creditNoteotherwise2(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherwise.properties");
			documentid = "CrOther" + documentid;
			String CopyCNOtherwiseID = "Copy" + documentid;
			Utilities.waitandClick(pro.getProperty("CreditNoteReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			// Copy
			Utilities.HoverandClick(pro.getProperty("copyCNOtherwiseButton"), driver);
			String formLoaded = "//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td[3]";
			Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			Utilities.enterTextNormalBox(CopyCNOtherwiseID, pro.getProperty("copyCNOtherwiseId"), driver);
			Utilities.HoverandClick(pro.getProperty("saveCopiedCNOtherwiseId"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("CopyCNOtherwiseClose"), driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox(CopyCNOtherwiseID, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out
					.println("**** COPY test case for Credit Note [" + CopyCNOtherwiseID + "] Otherwise is PASS !!!!");

			// Edit
			Utilities.HoverandClick(pro.getProperty("EditCNOtherwiseButton"), driver);
			Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			Utilities.enterTextNormalBox("EDIT Credit Note Otherwise [" + CopyCNOtherwiseID + "] Memo",
					pro.getProperty("CNOtherwiseMemo"), driver);
			Utilities.HoverandClick(pro.getProperty("CNOtherwiseEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("EditCNOtherwiseClose"), driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox(CopyCNOtherwiseID, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out
					.println("**** EDIT test case for Credit Note [" + CopyCNOtherwiseID + "] Otherwise is PASS !!!!");

			// Delete
			Utilities.HoverandClick(pro.getProperty("deleteCNOtherwiseButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteCNOtherwisePermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(10000);
			Utilities.enterTextNormalBox(CopyCNOtherwiseID, pro.getProperty("QuickSearch"), driver);

			// delete confrimation
			WebElement delConfirm = driver.findElement(
					By.xpath("//div[@id='CreditNoteDetails']/div/div/div/div/div/div[2]/div/div[3]/div/div"));

			Thread.sleep(5000);
			if (delConfirm.isDisplayed()) {
				System.out.println(
						"**** DELETE test case for Credit Note [" + CopyCNOtherwiseID + "] Otherwise is PASS !!!!");
			}
			Thread.sleep(5000);
			String xpathOfelement = "//li[@id='as__CreditNoteDetails']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);

		} catch (Exception EX) {
			System.out.println("**** COPY-EDIT-DELETE test case for Credit Note [" + documentid
					+ "] Otherwise is FAIL FAIL  !!!!");
			Utilities.handleError(EX, driver);
		}
	}

	////////////////////////////////////////////////// CN against vendor with
	////////////////////////////////////////////////// multiple
	////////////////////////////////////////////////// accounts/////////////////////////////////////////////////////////////////////////

	public static void create_creditNoteAgainstvendor2(String documentid, String productid, String vendor_ID,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			Properties CreditNoteVendor = Utilities.fetchProValue("OR_CreditNote_PurchaseInvoice.properties");

			Thread.sleep(3000);
			// WebDriverWait wait = new WebDriverWait(driver, 30);
			// clicked on Document
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CreditNoteVendor.getProperty("CreditNoteIcon"))));
			driver.findElement(By.xpath(CreditNoteVendor.getProperty("CreditNoteIcon"))).click();
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(CreditNoteVendor.getProperty("SelectVendorNote"))));
			driver.findElement(By.xpath(CreditNoteVendor.getProperty("SelectVendorNote"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CreditNoteVendor.getProperty("ClickSubmit"))));
			driver.findElement(By.xpath(CreditNoteVendor.getProperty("ClickSubmit"))).click();
			Thread.sleep(8000);

			// sequen format document no.
			WebElement squence = driver.findElement(By.xpath(CreditNoteVendor.getProperty("SequenceFormat")));
			squence.clear();
			squence.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CreditNoteVendor.getProperty("DocumentNo"))));
			driver.findElement(By.xpath(CreditNoteVendor.getProperty("DocumentNo"))).sendKeys("CrVendor" + documentid);

			Thread.sleep(2000);// pro

			// enter customer name
			WebElement customer = driver.findElement(By.xpath(CreditNoteVendor.getProperty("EnterVendorId")));
			customer.clear();
			customer.sendKeys(vendor_ID);
			Thread.sleep(3000);// pro
			customer.sendKeys(Keys.ENTER);

			WebElement accountselect1 = driver.findElement(By.xpath(CreditNoteVendor.getProperty("AccountName1")));
			accountselect1.click();
			Thread.sleep(2000);// pro

			Utilities.sendText("Utilities");

			Thread.sleep(2000);// pro
			Robot acc1 = new Robot();
			acc1.keyPress(KeyEvent.VK_ENTER);
			acc1.keyRelease(KeyEvent.VK_ENTER);

			driver.findElement(By.xpath(CreditNoteVendor.getProperty("Memo"))).click();
			Thread.sleep(2000);// pro
			WebElement ammount1 = driver.findElement(By.xpath(CreditNoteVendor.getProperty("AccountAmount1")));
			ammount1.click();
			Thread.sleep(2000);// pro

			Utilities.sendText("1");
			Thread.sleep(2000);// pro

			driver.findElement(By.xpath(CreditNoteVendor.getProperty("Memo"))).click();
			Thread.sleep(2000);// pro
			WebElement reason = driver.findElement(By.xpath(CreditNoteVendor.getProperty("EnterReason")));
			reason.click();
			Thread.sleep(2000);// pro

			acc1.keyPress(KeyEvent.VK_DOWN);
			acc1.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);// pro
			acc1.keyPress(KeyEvent.VK_DOWN);
			acc1.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);// pro
			acc1.keyPress(KeyEvent.VK_ENTER);
			acc1.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);// pro
			driver.findElement(By.xpath(CreditNoteVendor.getProperty("Memo"))).click();

			Thread.sleep(3000);

			WebElement accountselect2 = driver.findElement(By.xpath(CreditNoteVendor.getProperty("AccountName2")));
			accountselect2.click();
			Thread.sleep(2000);// pro

			Utilities.sendText("Travel");

			Thread.sleep(2000);// pro
			Robot acc2 = new Robot();
			acc2.keyPress(KeyEvent.VK_ENTER);
			acc2.keyRelease(KeyEvent.VK_ENTER);

			driver.findElement(By.xpath(CreditNoteVendor.getProperty("Memo"))).click();
			Thread.sleep(2000);// pro
			WebElement ammount2 = driver.findElement(By.xpath(CreditNoteVendor.getProperty("AccountAmount2")));
			ammount2.click();
			Thread.sleep(2000);// pro

			Utilities.sendText("1");
			Thread.sleep(2000);// pro

			driver.findElement(By.xpath(CreditNoteVendor.getProperty("Memo"))).click();
			Thread.sleep(2000);// pro
			WebElement reason2 = driver.findElement(By.xpath(CreditNoteVendor.getProperty("EnterReason2")));
			reason2.click();
			Thread.sleep(2000);// pro

			acc1.keyPress(KeyEvent.VK_DOWN);
			acc1.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);// pro
			acc1.keyPress(KeyEvent.VK_DOWN);
			acc1.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);// pro
			acc1.keyPress(KeyEvent.VK_ENTER);
			acc1.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);// pro
			driver.findElement(By.xpath(CreditNoteVendor.getProperty("Memo"))).click();

			// Click on Save
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CreditNoteVendor.getProperty("ClickOnSave"))));
			driver.findElement(By.xpath(CreditNoteVendor.getProperty("ClickOnSave"))).click();
			Thread.sleep(2000);

			// Click on Yes
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CreditNoteVendor.getProperty("ClickOnYes"))));
			driver.findElement(By.xpath(CreditNoteVendor.getProperty("ClickOnYes"))).click();
			Thread.sleep(1000);

			// Click on OK
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CreditNoteVendor.getProperty("ClickOnOk"))));
			driver.findElement(By.xpath(CreditNoteVendor.getProperty("ClickOnOk"))).click();
			Thread.sleep(1000);

			// Click on close
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CreditNoteVendor.getProperty("CloseForm"))));
			driver.findElement(By.xpath(CreditNoteVendor.getProperty("CloseForm"))).click();

			System.out.println("Debit Note created with >>>> : " + "CrVendor" + documentid);

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	///////////////////////////////////////////////////////// Verification of CN
	///////////////////////////////////////////////////////// against
	///////////////////////////////////////////////////////// Vendor///////////////////////////////////////////////////////////////////////////

	public static void Verify_creditNoteagainstvendor2(String documentid, String productid, String vendor_id,
			WebDriver driver) throws InterruptedException, AWTException, IOException

	{

		try {

			documentid = "CrVendor" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherWise.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreditNoteReport"))));
			driver.findElement(By.xpath(pro.getProperty("CreditNoteReport"))).click();
			Thread.sleep(5000);// pro

			WebElement searchtype = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='view19CreditNoteDetails']")));
			searchtype.clear();
			searchtype.sendKeys("Credit Note for Vendors");
			Thread.sleep(2000);
			searchtype.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["+i+"]/div")).getText());
				Thread.sleep(10000);
				String header = driver.findElement(By
						.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				Thread.sleep(10000);
				if (header.equals("Credit Note No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d1");
				}

				else if (header.equals("Name")) {
					assertEquals(vendor_id,
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d2");
				}

				else if (header.equals("Vendor Code")) {
					assertEquals(vendor_id,
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d3");
				}

				else if (header.equals("Amount")) {
					assertEquals("SGD 100.00",
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d4");
				}
			}

			System.out.println("Verified creditNoteAgainstvendor [" + documentid + "] !!");
			Thread.sleep(500);

			String xpathOfelement = "//li[@id='as__CreditNoteDetails']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	////////////////////////////////////////////////////////////////// CopyEdit-Delete
	////////////////////////////////////////////////////////////////// CN
	////////////////////////////////////////////////////////////////// against
	////////////////////////////////////////////////////////////////// Vendor//////////////////////////////////////////////////////////////

	public static void CopyEditDelete_creditNoteAgainstvendor2(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_CreditNote_PurchaseInvoice.properties");
			documentid = "CrVendor" + documentid;
			String CopyCNAgainstVendorID = "Copy" + documentid;
			Utilities.waitandClick(pro.getProperty("CreditNoteReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.enterTextandSelect("Credit Note for Vendors", pro.getProperty("CNTypeDD"), driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			// Copy
			Utilities.HoverandClick(pro.getProperty("copyCNAgainstVendorButton"), driver);
			String formLoaded = "//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td[3]";
			Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			Utilities.enterTextNormalBox(CopyCNAgainstVendorID, pro.getProperty("copyCNAgainstVendorId"), driver);
			Utilities.HoverandClick(pro.getProperty("saveCopiedCNAgainstVendorId"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("CopyCNAgainstVendorClose"), driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox(CopyCNAgainstVendorID, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println(
					"**** COPY test case for Credit Note [" + CopyCNAgainstVendorID + "] Against Vendor is PASS !!!!");

			// Edit
			Utilities.HoverandClick(pro.getProperty("EditCNAgainstVendorButton"), driver);
			Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			Utilities.enterTextNormalBox("EDIT Credit Note [" + CopyCNAgainstVendorID + "] Against Vendor Memo",
					pro.getProperty("CNAgainstVendorMemo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("EditCNAgainstVendorClose"), driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox(CopyCNAgainstVendorID, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println(
					"**** EDIT test case for Credit Note [" + CopyCNAgainstVendorID + "] Against Vendor is PASS !!!!");

			// Delete
			Utilities.HoverandClick(pro.getProperty("deleteCNAgainstVendorButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteCNAgainstVendorPermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox(CopyCNAgainstVendorID, pro.getProperty("QuickSearch"), driver);

			// delete confrimation
			WebElement delConfirm = driver.findElement(
					By.xpath("//div[@id='CreditNoteDetails']/div/div/div/div/div/div[2]/div/div[3]/div/div"));
			if (delConfirm.isDisplayed()) {
				System.out.println("**** DELETE test case for Credit Note [" + CopyCNAgainstVendorID
						+ "] Against Vendor is PASS !!!!");
			}

			String xpathOfelement = "//li[@id='as__CreditNoteDetails']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);
		} catch (Exception EX) {
			System.out.println("**** EDIT-DELETE test case for Credit Note [" + documentid
					+ "] Against Vendor is FAIL FAIL  !!!!");
			Utilities.handleError(EX, driver);
		}
	}
	//////////////////////////////////////////////////////////////// CN
	//////////////////////////////////////////////////////////////// UNDERCHARGE
	//////////////////////////////////////////////////////////////// WITH
	//////////////////////////////////////////////////////////////// MULTIPLE
	//////////////////////////////////////////////////////////////// DOCUMENTS//////////////////////////////////////////////////////////

	public static void create_CreditNoteUnderCharge2(String documentid, String productid, String vendor_ID,
			String Quantity, WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			Properties pro1 = Utilities.fetchProValue("OR_CrediNoteCharged.properties");
			WebDriverWait wait = new WebDriverWait(driver, 40);

			Utilities.waitandClick(pro1.getProperty("CreateCreditNoteIcon"), driver);
			Utilities.waitandClick(pro1.getProperty("SelectCNunderCharge"), driver);
			Utilities.waitandClick(pro1.getProperty("submitButton"), driver);

			Utilities.enterTextandSelect("NA", pro1.getProperty("sequenceFormat"), driver);
			Utilities.enterTextNormalBox("CnUC" + documentid, pro1.getProperty("crediNoteNo"), driver);

			Utilities.enterTextandSelect(vendor_ID, pro1.getProperty("vendorInput"), driver);

			Utilities.enterTextInDropDown("Yes", pro1.getProperty("link"), driver);
			Utilities.enterTextInDropDown("Purchase Invoice", pro1.getProperty("linkTo"), driver);
			Utilities.waitandClick(pro1.getProperty("Search"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.waitandClick(pro1.getProperty("Date"), driver);
			Utilities.waitandClick(pro1.getProperty("TodaysDate2"), driver);
			Utilities.waitandClick(pro1.getProperty("Fetch"), driver);
			Utilities.waitandClick(pro1.getProperty("Checkbox"), driver);
			Utilities.waitandClick(pro1.getProperty("Addbutton"), driver);

			List<WebElement> headers = driver
					.findElements(By.xpath("//div[@class='x-grid3-header']/div/div/table/thead/tr/td/div"));
			int TotalAdded = Utilities.totalSize(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[contains(@class,'x-grid3-row')]",
					driver);

			int headSize = headers.size();
			Robot r = new Robot();
			int amtIndex = 0;

			// to scroll end
			Thread.sleep(10000);
			Utilities.justHover("//*[@class='pwnd delete-gridrow']", driver);
			Thread.sleep(1000);
			int headRsn = 0;
			for (int i = 1; i <= headSize; i++) {
				String headName = driver
						.findElement(
								By.xpath("*//div[@class='x-grid3-header']/div/div/table/thead/tr/td[" + i + "]/div"))
						.getText();
				if (headName.equalsIgnoreCase("Reason")) {
					headRsn = i;
				}
			}
			for (int j = 1; j <= TotalAdded - 1; j++) {
				Utilities.HoverandClick(
						"//div[@class='x-grid3-scroller']/div/div[" + j + "]/table/tbody/tr/td[" + headRsn + "]/div",
						driver);
				Utilities.HoverandClick("//*[@id='reason']/following::img[1]", driver);
				Utilities.HoverandClick("//*[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div[2]",
						driver);
				Utilities.click_element(pro1.getProperty("memo"), driver);
			}

			Utilities.clickButton("Save", 800, driver);
			Utilities.clickButton("Yes", 800, driver);
			Utilities.clickButton("OK", 800, driver);

			WebElement selectExit = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//li[@id='as__creditnoteagaintvendorformalaysia']/a[1]")));
			selectExit.click();

			System.out.println(
					"* * * Credit Note Undercharged created successfully for : [" + documentid + "] * * * * * ");
		}

		catch (Exception EX) {
			System.out.println("* * * Credit Note Undercharged [" + documentid + "] FAILED to create * * * * * ");
			Utilities.handleError(EX, driver);
		}
	}

	//////////////////////////////////////////////////////////// CN OVERCHARGE
	//////////////////////////////////////////////////////////// WITH MULTIPLE
	//////////////////////////////////////////////////////////// SI//////////////////////////////////////////////////////////////////////////////////

	public static void create_CreditNoteOverCharge2(String documentid, String productid, String customer_ID,
			String vendor_ID, String Quantity, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			// documentid = "CnOC"+documentid;
			Properties pro2 = Utilities.fetchProValue("OR_CrediNoteCharged.properties");
			WebDriverWait wait = new WebDriverWait(driver, 40);

			Utilities.waitandClick(pro2.getProperty("CreateCreditNoteIcon"), driver);
			Utilities.waitandClick(pro2.getProperty("SelectCNoverCharge"), driver);
			Utilities.waitandClick(pro2.getProperty("submitButton"), driver);
			Utilities.enterTextandSelect(customer_ID + "Name", pro2.getProperty("customerInput"), driver);
			Utilities.enterTextandSelect("NA", pro2.getProperty("sequenceFormat"), driver);
			Utilities.enterTextNormalBox("CnOC" + documentid, pro2.getProperty("crediNoteNo"), driver);

			Utilities.enterTextInDropDown("Yes", pro2.getProperty("link"), driver);
			Utilities.enterTextInDropDown("Sales Invoice", pro2.getProperty("linkTo"), driver);

			Utilities.waitandClick(pro2.getProperty("Search"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.waitandClick(pro2.getProperty("Date2"), driver);
			Utilities.waitandClick(pro2.getProperty("TodaysDate2"), driver);
			Utilities.waitandClick(pro2.getProperty("Fetch"), driver);
			Utilities.waitandClick(pro2.getProperty("Checkbox2"), driver);
			Utilities.waitandClick(pro2.getProperty("Addbutton2"), driver);

			/*
			 * String drpDwnArrow=
			 * "//input[@name='ordernumber']/following-sibling::span/img[2]";
			 * String itemInputBoxLocator =
			 * "//input[@name='ordernumber']/following-sibling::input"; String
			 * itemName="PurInvo"+documentid; String
			 * rslTable="//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div/table/tbody/tr/td[1]"
			 * ;
			 * 
			 * Utilities.selectItemfromDropDown(drpDwnArrow,
			 * itemInputBoxLocator, itemName, rslTable, driver);
			 * Utilities.click_element(pro.getProperty("memo"), driver);
			 * Thread.sleep(1000);
			 */

			List<WebElement> headers = driver
					.findElements(By.xpath("//div[@class='x-grid3-header']/div/div/table/thead/tr/td/div"));
			int headSize = headers.size();
			Robot r = new Robot();
			int amtIndex = 0;
			int TotalAdded = Utilities.totalSize(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[contains(@class,'x-grid3-row')]",
					driver);

			/*
			 * for(int i=1; i<=headSize; i++) { String headName=
			 * driver.findElement(By.xpath(
			 * "//div[@class='x-grid3-header']/div/div/table/thead/tr/td["+i+
			 * "]/div")).getText(); if(headName.equalsIgnoreCase("Quantity")){
			 * amtIndex=i; }
			 * 
			 * 
			 * // add Qty for(int i=1; i<=TotalAdded-1; i++) {
			 * Utilities.clickAndEnterText(Quantity,
			 * "//div[@class='x-grid3-scroller']/div/div["+i+
			 * "]/table/tbody/tr/td["+amtIndex+"]/div", driver); //to move focus
			 * Utilities.click_element(pro.getProperty("memo"), driver); }
			 */

			// to scroll end
			Utilities.justHover("//*[@class='pwnd delete-gridrow']", driver);
			Thread.sleep(1000);
			int headRsn = 0;
			for (int i = 1; i <= headSize; i++) {
				String headName = driver
						.findElement(
								By.xpath("*//div[@class='x-grid3-header']/div/div/table/thead/tr/td[" + i + "]/div"))
						.getText();
				if (headName.equalsIgnoreCase("Reason")) {
					headRsn = i;
				}
			}
			for (int j = 1; j <= TotalAdded - 1; j++) {
				Utilities.HoverandClick(
						"//div[@class='x-grid3-scroller']/div/div[" + j + "]/table/tbody/tr/td[" + headRsn + "]/div",
						driver);
				Thread.sleep(1000);
				r.keyPress(KeyEvent.VK_DOWN);
				r.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				r.keyPress(KeyEvent.VK_DOWN);
				r.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);// pro
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);// pro
			}

			Utilities.click_element(pro2.getProperty("memo"), driver);

			Utilities.clickButton("Save", 800, driver);
			Thread.sleep(10000);
			Utilities.clickButton("Yes", 800, driver);
			Utilities.clickButton("OK", 800, driver);

			WebElement selectExit = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='as__creditnoteForOvercharge']/a[1]")));
			selectExit.click();

			System.out.println(
					"* * * Credit Note Overcharged created successfully for : [" + documentid + "] * * * * * ");

		}

		catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	////////////////////////////////////////////// Verification of CN
	////////////////////////////////////////////// Undercharge and
	////////////////////////////////////////////// Overcharge/////////////////////////////////////////////////////////////////////////////////

	public static void verify_CreditNoteCharged2(String documentid, String underORover, String vendor_ID,
			String customer_ID, WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_CrediNoteCharged.properties");
			WebDriverWait wait = new WebDriverWait(driver, 40);

			// clicked on Document
			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreditNoteReport"))));
			element.click();
			Thread.sleep(2000);// pro

			// WebElement
			// docCheckBox=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("documentCheck"))));

			Utilities.clickCheckBox(pro.getProperty("documentCheck"), "uncheck", driver);
			WebElement selectType = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("selectTyp"))));
			selectType.click();

			if (underORover.equalsIgnoreCase("Undercharged")) {
				selectType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]//div[text()='Credit Note for Undercharged Purchase Invoice']")));
				selectType.click();
			}

			else if (underORover.equalsIgnoreCase("Overcharged")) {
				selectType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]//div[text()='Credit Note for Overcharged Sales Invoice']")));
				selectType.click();
			}
			Thread.sleep(3000);

			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			element.clear();
			element.sendKeys("CnUC" + documentid);
			Thread.sleep(3000);

			List<WebElement> headers = driver.findElements(By.xpath("//div[text()='Credit Note No']/ancestor::tr/td"));
			int headSize = headers.size();
			Robot r = new Robot();
			int customerIdIndex = 0, nameIndex = 0;

			for (int i = 1; i <= headSize; i++) {
				String headName = driver
						.findElement(By.xpath("//div[text()='Credit Note No']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				// System.out.println(headName);
				if (headName.equalsIgnoreCase("Credit Note No")) {
					customerIdIndex = i;
				}

				else if (headName.equalsIgnoreCase("Name")) {
					nameIndex = i;
				}
			}

			WebElement docCheckBox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("documentCheck"))));

			if (docCheckBox.isDisplayed()) {

				assertEquals("CnUC" + documentid,
						driver.findElement(By
								.xpath("//div[text()='Credit Note No']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ customerIdIndex + "]/div"))
								.getText());

				assertEquals(vendor_ID,
						driver.findElement(By
								.xpath("//div[text()='Credit Note No']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ nameIndex + "]/div"))
								.getText());
			}

			System.out.println("* * * * * Verified CreditNote for [" + underORover + "] with document id : ["
					+ documentid + "] with acc Name [" + vendor_ID + "] * * * *");
			String xpathOfelement = "//li[@id='as__CreditNoteDetails']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);

		}

		catch (Exception EX) {
			System.out.println("* * * * * Verification FAILED CreditNote for [" + underORover + "] with document id : "
					+ documentid);
			Utilities.handleError(EX, driver);
		}
	}

	///////////////////////////////////////////// Verification of CN
	///////////////////////////////////////////// Overcharge//////////////////////////////////////////////////////////////////////////////////

	public static void verify_CreditNoteCharged3(String documentid, String underORover, String customer_ID,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_CrediNoteCharged.properties");
			WebDriverWait wait = new WebDriverWait(driver, 40);

			// clicked on Document
			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreditNoteReport"))));
			element.click();
			Thread.sleep(2000);// pro

			Utilities.clickCheckBox(pro.getProperty("documentCheck"), "uncheck", driver);
			WebElement selectType = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("selectTyp"))));
			selectType.click();

			if (underORover.equalsIgnoreCase("Overcharged")) {
				selectType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]//div[text()='Credit Note for Overcharged Sales Invoice']")));
				selectType.click();
			}
			Thread.sleep(3000);

			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			element.clear();
			element.sendKeys("CnOC" + documentid);
			Thread.sleep(3000);

			List<WebElement> headers = driver.findElements(By.xpath("//div[text()='Credit Note No']/ancestor::tr/td"));
			int headSize = headers.size();
			int customerIdIndex = 0, nameIndex = 0;

			for (int i = 1; i <= headSize; i++) {
				String headName = driver
						.findElement(By.xpath("//div[text()='Credit Note No']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				// System.out.println(headName);
				if (headName.equalsIgnoreCase("Credit Note No")) {
					customerIdIndex = i;
				}

				else if (headName.equalsIgnoreCase("Name")) {
					nameIndex = i;
				}
			}

			WebElement docCheckBox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("documentCheck"))));

			if (docCheckBox.isDisplayed()) {

				assertEquals("CnOC" + documentid,
						driver.findElement(By
								.xpath("//div[text()='Credit Note No']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ customerIdIndex + "]/div"))
								.getText());

				assertEquals(customer_ID + "Name",
						driver.findElement(By
								.xpath("//div[text()='Credit Note No']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ nameIndex + "]/div"))
								.getText());
			}

			System.out.println("* * * * * VerifiedCreditNote for [" + underORover + "] with document id : ["
					+ documentid + "] with acc Name [" + customer_ID + "Name" + "] * * * *");
			String closeReportPage = "//li[@id='as__CreditNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);

		}

		catch (Exception EX) {
			System.out.println("* * * * * Verification FAILED DebitNote for [" + underORover + "] with document id : "
					+ documentid);
			Utilities.handleError(EX, driver);
		}

	}

	//////////////////////////////////////////////////////// Edit-Delete CN
	//////////////////////////////////////////////////////// Overcharge and
	//////////////////////////////////////////////////////// Undercharge////////////////////////////////////////////////////////////////

	// OVERCHARGE

	public static void EditDelete_creditNoteOvercharge(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			Properties pro = Utilities.fetchProValue("OR_CrediNoteCharged.properties");

			Utilities.waitandClick(pro.getProperty("CreditNoteReport"), driver);
			Thread.sleep(10000);
			Utilities.enterTextNormalBox("CnOC" + documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			// Edit
			Utilities.HoverandClick(pro.getProperty("EditCNOVRButton"), driver);
			// String
			// formLoaded="//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td[3]";
			// Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			try {
				if (driver.findElement(By.xpath("//div[@class='x-window x-window-plain x-window-dlg']"))
						.isDisplayed()) {
					driver.findElement(By.xpath("//button[text()='Yes']")).click();
				}
			} catch (Exception Ex) {
				// no action needed
			}
			Utilities.enterTextNormalBox("Update Credit Note [" + documentid + "] OVERCHARGE Memo",
					pro.getProperty("memo"), driver);
			Utilities.HoverandClick(pro.getProperty("CNOVREditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 500, driver);
			Utilities.click_element(pro.getProperty("EditCNOVRClose"), driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox("CnOC" + documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println("**** EDIT test case for Credit Note [+documentid+] OVERCHARGE is PASS !!!!");

			// delete
			Utilities.HoverandClick(pro.getProperty("deleteCNOVRButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteCNOVRPermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(10000);
			Utilities.enterTextNormalBox("CnOC" + documentid, pro.getProperty("QuickSearch"), driver);

			// delete confrimation
			WebElement delConfirm = driver.findElement(
					By.xpath("//div[@id='as']/div[2]/div/div[2]/div/div/div/div/div/div[2]/div/div[3]/div/div"));
			if (delConfirm.isDisplayed()) {
				System.out
						.println("**** DELETE test case for Credit Note [" + documentid + "] OVERCHARGE is PASS !!!!");
			}

			String xpathOfelement = "//li[@id='as__CreditNoteDetails']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);
		} catch (Exception EX) {
			System.out.println(
					"**** EDIT-DELETE test case for Credit Note [" + documentid + "] OVERCHARGE is FAIL FAIL  !!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// UNDERCHARGE

	public static void EditDelete_creditNoteUndercharge(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_CrediNoteCharged.properties");

			Utilities.waitandClick(pro.getProperty("CreditNoteReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextNormalBox("CnUC" + documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(10000);
			Utilities.enterTextandSelect("Credit Note for Vendors", pro.getProperty("CNTypeDD"), driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			// Edit
			Utilities.HoverandClick(pro.getProperty("EditCNAgainstVendorButton"), driver);

			Utilities.enterTextNormalBox("EDIT Credit Note [" + documentid + "] Undercharge", pro.getProperty("memo"),
					driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("EditCNAgainstVendorClose"), driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox("CnUC" + documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println("**** EDIT test case for Credit Note [" + documentid + "] Undercharge is PASS !!!!");

			// Delete
			Utilities.HoverandClick(pro.getProperty("deleteCNAgainstVendorButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteCNAgainstVendorPermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox("CnUC" + documentid, pro.getProperty("QuickSearch"), driver);

			// delete confrimation
			WebElement delConfirm = driver.findElement(
					By.xpath("//div[@id='as']/div[2]/div/div[2]/div/div/div/div/div/div[2]/div/div[3]/div/div"));
			if (delConfirm.isDisplayed()) {
				System.out
						.println("**** DELETE test case for Credit Note [" + documentid + "] Undercharge is PASS !!!!");
			}

			String xpathOfelement = "//li[@id='as__CreditNoteDetails']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);
		} catch (Exception EX) {
			System.out.println(
					"**** EDIT-DELETE test case for Credit Note [" + documentid + "] Undercharge is FAIL FAIL  !!!!");
			Utilities.handleError(EX, driver);
		}
	}
}
