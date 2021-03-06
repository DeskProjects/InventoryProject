package krawler.erp.inventory;

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

import krawler.erp.page.Utilities;

public class InventoryDO {

	public static void create_DeliveryOrder(boolean qaFlow, String documentid, String productid, String customerid,
			String actQty, String delQty, WebDriver driver, String BatchName, String serialSeq)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "InDo" + documentid;
			Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");

			Utilities.waitandClick(pro.getProperty("DeliveryOrderIcon"), driver);
			Utilities.disableExpander(driver);
			Utilities.clickCheckBox(pro.getProperty("includingGST"), "uncheck", driver);
			selectFromNormalDropDown(customerid, pro.getProperty("CustomerId"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("DeliveryOrderNo"), driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			Utilities.HoverandClick(pro.getProperty("AddProduct"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("SearchProduct"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("ClickOnAdd"), driver);
			Thread.sleep(1000);
			try {
				WebElement button = new WebDriverWait(driver, 3)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
				if (button.isDisplayed()) {
					Thread.sleep(1000);
					button.click();
				}
			} catch (Exception Ex) {
				// handle (Please ensure all product(s) should be mapped with
				// Product Tax Class)
			}

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexActQty = 0, indexRecQty = 0, indProdID = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Actual Quantity")) {
					indexActQty = i;
				}
				if (HeadeName.equalsIgnoreCase("Delivered Quantity")) {
					indexRecQty = i;
				}
				if (HeadeName.equalsIgnoreCase("Product ID")) {
					indProdID = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) { // add actual qty
				Utilities.HoverandClick(
						"//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexActQty + "]/div",
						driver);
				Utilities.sendText(actQty);
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
				// add receive qty
				Utilities.HoverandClick(
						"//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexRecQty + "]/div",
						driver);
				Utilities.sendText(delQty);
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			}

			WebElement wslocationlink = null, batch = null, serial = null, location = null;
			int normal = 0, ser = 0, bat = 0, batchserial = 0;

			for (int j = 1; j <= totalResult; j++) {
				String productIDStr = driver.findElement(
						By.xpath("//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
								+ j + "]/table/tbody/tr/td[" + indProdID + "]/div"))
						.getText();
				// System.out.println("productIDStr = " + productIDStr);
				if (productIDStr.endsWith("S3")) {
					ser = j;
				} else if (productIDStr.endsWith("B2")) {
					bat = j;
				} else if (productIDStr.endsWith("BS4")) {
					batchserial = j;
				} else if (productIDStr.equalsIgnoreCase(productid)) {
					normal = j;
				}
			}

			// for normal
			// ******************************************************************************
			if (normal > 0) {
				String wslocationlinkXpath = "//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
						+ normal + "]/table/tbody/tr/td//div[contains(@class,'serialNo-gridrow')]";
				Utilities.click_element(wslocationlinkXpath, driver);
				Thread.sleep(1000);
				Utilities.clickButton("Submit", 1000, driver);
			}

			Thread.sleep(3000);
			// for batch
			// *****************************************************************************
			if (bat > 0) {
				String wslocationlinkXpath = "//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
						+ bat + "]/table/tbody/tr/td//div[contains(@class,'serialNo-gridrow')]";
				Utilities.click_element(wslocationlinkXpath, driver);
				Thread.sleep(1000);
				for (int i = 1; i <= driver
						.findElements(By
								.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td/div"))
						.size(); i++) {
					if (driver.findElement(By
							.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText().equalsIgnoreCase("Batch")) {
						batch = driver.findElement(By
								.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ i + "]/div"));
						batch.click();
						Thread.sleep(1000);
					}
				}
				String xpathOfchec = "//*[text()='Name']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td[1]/div/div";
				Utilities.waitandClick(xpathOfchec, driver);
				Utilities.HoverandClick("//*[@class='x-window']//button[text()='OK']", driver);
				Utilities.clickButton("Submit", 1000, driver);

			}

			Thread.sleep(3000);
			// for serial
			// ******************************************************************************
			if (ser > 0) {
				String wslocationlinkXpath = "//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
						+ ser + "]/table/tbody/tr/td//div[contains(@class,'serialNo-gridrow')]";
				Utilities.click_element(wslocationlinkXpath, driver);
				Thread.sleep(1000);

				for (int i = 1; i <= driver
						.findElements(By
								.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td/div"))
						.size(); i++) {
					if (driver.findElement(By
							.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText().equalsIgnoreCase("Serial No")) {
						for (int qtycnt = 1; qtycnt <= Integer.parseInt(actQty); qtycnt++) {
							serial = driver.findElement(By
									.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[2]/div/div["
											+ qtycnt + "]/table/tbody/tr/td[" + i + "]/div"));
							Thread.sleep(1000);
							serial.click();
							Thread.sleep(1000);
							break;
						}
					}
				}

				for (int k = 1; k <= Integer.parseInt(actQty); k++) {
					String xpathOfSerial = "//*[text()='Available Serials']/ancestor::div[2]/div[2]/div[2]//div[@class='x-grid3-scroller']/div/div["
							+ k + "]/table/tbody/tr/td[1]/div/div";
					Utilities.HoverandClick(xpathOfSerial, driver);
				}
				Utilities.waitandClick("//*[contains(@style,'padding')]/img[contains(@src,'arrowright')]", driver);
				Utilities.HoverandClick(
						"//div[@id='serialSelectWindow' and contains(@style,'visible')]//button[text()='Submit']",
						driver);
				// 2nd submit
				Utilities.HoverandClick(
						"//div[@class='x-window' and contains(@style,'visible')]//button[text()='Submit']", driver);
				Thread.sleep(3000);
			}

			// for batchserial
			// *********************************************************************
			if (batchserial > 0) {
				String xpathExpression = "//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
						+ batchserial + "]/table/tbody/tr/td/div/div[contains(@class,'serialNo-gridrow')]";
				Utilities.click_element(xpathExpression, driver);
				Thread.sleep(1000);

				for (int i = 1; i <= driver
						.findElements(By
								.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td/div"))
						.size(); i++) {
					if (driver.findElement(By
							.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText().equalsIgnoreCase("Batch")) {
						batch = driver.findElement(By
								.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ i + "]/div"));
						batch.click();
						Thread.sleep(1000);
					}
				}
				String xpathOfchec = "//*[text()='Name']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td[1]/div/div";
				Utilities.waitandClick(xpathOfchec, driver);
				Utilities.HoverandClick("//*[@class='x-window']//button[text()='OK']", driver);

				String moveTo = "//div[@class='x-window' and contains(@style,'visible')]//div[1]/table//div[@class='pwnd delete-gridrow']";
				Utilities.justHover(moveTo, driver);
				Thread.sleep(1000);

				for (int i = 1; i <= driver
						.findElements(By
								.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td/div"))
						.size(); i++) {
					if (driver.findElement(By
							.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText().equalsIgnoreCase("Serial No")) {
						serial = driver.findElement(By
								.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ i + "]/div"));
						Thread.sleep(1000);
						serial.click();
						Thread.sleep(1000);
					}
				}

				for (int k = 1; k <= Integer.parseInt(actQty); k++) {
					String xpathOfSerial = "//*[text()='Available Serials']/ancestor::div[2]/div[2]/div[2]//div[@class='x-grid3-scroller']/div/div["
							+ k + "]/table/tbody/tr/td[1]/div/div";
					Utilities.HoverandClick(xpathOfSerial, driver);
				}
				Utilities.waitandClick("//*[contains(@style,'padding')]/img[contains(@src,'arrowright')]", driver);
				Utilities.HoverandClick(
						"//div[@id='serialSelectWindow' and contains(@style,'visible')]//button[text()='Submit']",
						driver);
				// 2nd submit
				Utilities.HoverandClick(
						"//div[@class='x-window' and contains(@style,'visible')]//button[text()='Submit']", driver);
				Thread.sleep(3000);
			}

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);

			if (qaFlow == true) {
				Utilities.clickButton("Yes", 1000, driver);
			} else if (qaFlow == false) {
				Utilities.clickButton("No", 1000, driver);
			}

			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("CloseFormDO"), driver);
			System.out.println("******* Delivery Order is Created for :[" + documentid + "] Successfully !! *******");
		} catch (Exception EX) {
			System.out.println("******* Delivery Order is NOT Created for :[" + documentid + "] checlk LOG *******");
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

	// *********** Edit *******************************

	public static void Edit_DeliveryOrder(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {

			Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");

			Utilities.HoverandClick(pro.getProperty("DeliveryOrderReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			Utilities.HoverandClick(pro.getProperty("EditDeliveryOrderButton"), driver);
			// to Load page properly
			Utilities.justHover(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td[7]",
					driver);
			Utilities.enterTextInDropDown("Performing Edit Operation", pro.getProperty("DeliveryOrderMemo"), driver);

			Utilities.justHover("//div[@class='pwnd delete-gridrow']", driver);

			int indexOfPrice = 0;
			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[contains(@id,'DeliveryOrderEdit')]/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[contains(@id,'DeliveryOrderEdit')]/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Unit Price")) {
					indexOfPrice = i;
				}
			}

			int totalResult = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div",
					driver);
			WebElement price = null;
			for (int j = 1; j <= totalResult - 1; j++) {
				price = driver
						.findElement(By.xpath("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + j
								+ "]/table/tbody/tr/td[" + indexOfPrice + "]/div"));
				price.click();
				Thread.sleep(1000);
				Utilities.sendText("10");
				Thread.sleep(1000);
				Utilities.waitandClick(pro.getProperty("DeliveryOrderMemo"), driver);
			}

			Utilities.HoverandClick(pro.getProperty("DeliveryOrdereEditSave"), driver);

			Utilities.clickButton("Yes", 1000, driver);

			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("EditDeliveryOrderClose"), driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Delivery Order Edited !!");
			} else {
				System.out.println("Delivery Order Failed to Edit !!");

			}
		} catch (Exception EX) {
			System.out.println("Delivery Order Failed to Edit !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Delete ******************************************

	public static void Delete_DeliveryOrder(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {

			Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");

			Utilities.HoverandClick(pro.getProperty("deleteDeliveryOrderButton"), driver);

			Utilities.HoverandClick(pro.getProperty("deleteDeliveryOrderPermButton"), driver);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(1500);

			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			WebElement element = new WebDriverWait(driver, 45).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//a[contains(text(),'Get Started by adding a Delivery Order now')]")));
			if (element.isDisplayed()) {
				System.out.println("Delivery Order Deleted !!!! ");
			} else {
				System.out.println("Delivery Order is NOT Deleted !!!! ");
			}
			Utilities.HoverandClick(pro.getProperty("reportCloseBtn"), driver);
		} catch (Exception EX) {
			System.out.println("Delivery Order is NOT Deleted !!!! ");
			Utilities.handleError(EX, driver);
		}
	}

	// ************************** -ve DO On or Off setup
	// ***************************************************************
	public static void enable_NegativeDO(boolean isNegative, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_SystemControls.properties");
			WebElement element = null;
			Actions action = new Actions(driver);

			InvUtilities.enableExpander(driver);
			Utilities.waitandClick(pro.getProperty("PreferencesExpander"), driver);
			Utilities.waitandClick(pro.getProperty("SystemControlsLink"), driver);
			Thread.sleep(10000);

			element = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='isnegativestockforlocwar']")));

			if (isNegative == true) {
				if (!element.isSelected()) {
					action.moveToElement(element).build().perform();
					Thread.sleep(500);
					element.click();
					System.out.println("*** Negative check now Enabled ***");
				} else {
					System.out.println("*** Negative check Already ENABLED***");
				}
			}

			else if (isNegative == false) {
				if (element.isSelected()) {
					action.moveToElement(element).build().perform();
					Thread.sleep(500);
					element.click();
					System.out.println("*** Negative check now Disabled ***");
				} else {
					System.out.println("*** Negative check Already Disabled***");
				}
			}

			Utilities.HoverandClick(pro.getProperty("saveAccountPreferencesBtn"), driver);
			try {
				element = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				element.click();
				Thread.sleep(500);
				Utilities.HoverandClick(pro.getProperty("saveAccountPreferencesBtn"), driver);
			} catch (Exception Ex) {
				// setting already set
			}

			Utilities.HoverandClick(pro.getProperty("applyNewsettingsBtn"), driver);
			Utilities.clickButton("OK", 1500, driver);

		} catch (Exception EX) {
			;
			Utilities.handleError(EX, driver);
		}
	}

	// *************************************************NEgative Stock test
	// *******************************************************

	public static void create_NegativeDO(boolean qaFlow, String documentid, String productid, String customerid,
			String actQty, String delQty, WebDriver driver, String BatchName, String serialSeq)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "NegDo" + documentid;
			Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");
			WebElement element = null;

			Utilities.waitandClick(pro.getProperty("DeliveryOrderIcon"), driver);
			Utilities.disableExpander(driver);
			Utilities.clickCheckBox(pro.getProperty("includingGST"), "uncheck", driver);
			selectFromNormalDropDown(customerid, pro.getProperty("CustomerId"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("DeliveryOrderNo"), driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			Utilities.HoverandClick(pro.getProperty("AddProduct"), driver);

			Utilities.clickCheckBox(pro.getProperty("SelectCheckBox"), "uncheck", driver);

			Utilities.enterTextNormalBox(productid, pro.getProperty("SearchProduct"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("SelectCheckBox"), "uncheck", driver);
			Utilities.clickCheckBox(pro.getProperty("SelectproductSS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);

			Utilities.HoverandClick(pro.getProperty("ClickOnAdd"), driver);
			Thread.sleep(1500);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexActQty = 0, indexRecQty = 0, indProdID = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Actual Quantity")) {
					indexActQty = i;
				}
				if (HeadeName.equalsIgnoreCase("Delivered Quantity")) {
					indexRecQty = i;
				}
				if (HeadeName.equalsIgnoreCase("Product ID")) {
					indProdID = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) { // add actual qty
				Utilities.HoverandClick(
						"//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexActQty + "]/div",
						driver);
				Utilities.sendText(actQty);
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
				// add receive qty
				Utilities.HoverandClick(
						"//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexRecQty + "]/div",
						driver);
				Utilities.sendText(delQty);
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
				Utilities.clickButton("Yes", 300, driver);
			}

			Utilities.clickButton("Save", 1000, driver);
			WebElement alert = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[contains(@class,'x-window') and contains(@style,'visible')]//*[text()='Alert']")));
			if (alert.isDisplayed()) {
				String error = driver
						.findElement(By
								.xpath("//*[contains(@class,'x-window') and contains(@style,'visible')]//*[contains(@class,'wtf-mb-content')]/span"))
						.getText();
				System.out.println(error);
				Utilities.clickButton("OK", 1000, driver);
			}

			// to remove 3 prodcuts
			for (int i = 1; i <= 3; i++) {
				Thread.sleep(1000);
				Utilities.HoverandClick(
						"//*[@class='pwnd delete-gridrow']/ancestor::div[3]/div[2]/table/tbody//*[@class='pwnd delete-gridrow']",
						driver);
				Utilities.clickButton("Yes", 500, driver);
			}

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);

			if (qaFlow == true) {
				Utilities.clickButton("Yes", 1000, driver);
			} else if (qaFlow == false) {
				Utilities.clickButton("No", 1000, driver);
			}

			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("CloseFormDO"), driver);
			System.out.println("******* Delivery Order [ Negative stock ] is Created for :[" + documentid
					+ "] Successfully !! *******");

		} catch (Exception EX) {
			System.out.println("******* Delivery Order [ Negative stock ] is NOT Created for :[" + documentid
					+ "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************* Assembly DO ****************************
	public static void create_AssemblyDeliveryOrder(boolean qaFlow, String documentid, String productid,
			String customerid, String actQty, String delQty, WebDriver driver, String BatchName, String serialSeq)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "AsBDo" + documentid;
			Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");

			Utilities.waitandClick(pro.getProperty("DeliveryOrderIcon"), driver);
			Utilities.disableExpander(driver);
			Utilities.clickCheckBox(pro.getProperty("includingGST"), "uncheck", driver);
			selectFromNormalDropDown(customerid, pro.getProperty("CustomerId"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("DeliveryOrderNo"), driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			Utilities.HoverandClick(pro.getProperty("AddProduct"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.enterTextNormalBox("AsB" + productid, pro.getProperty("SearchProduct"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("ClickOnAdd"), driver);
			try {
				WebElement button = new WebDriverWait(driver, 3)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
				if (button.isDisplayed()) {
					Thread.sleep(1000);
					button.click();
				}
			} catch (Exception Ex) {
				// handle (Please ensure all product(s) should be mapped with
				// Product Tax Class)
			}

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexActQty = 0, indexRecQty = 0, indProdID = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Actual Quantity")) {
					indexActQty = i;
				}
				if (HeadeName.equalsIgnoreCase("Delivered Quantity")) {
					indexRecQty = i;
				}
				if (HeadeName.equalsIgnoreCase("Product ID")) {
					indProdID = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) { // add actual qty
				Utilities.HoverandClick(
						"//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexActQty + "]/div",
						driver);
				Utilities.sendText(actQty);
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
				// add receive qty
				Utilities.HoverandClick(
						"//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexRecQty + "]/div",
						driver);
				Utilities.sendText(delQty);
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			}

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);

			if (qaFlow == true) {
				Utilities.clickButton("Yes", 1000, driver);
			} else if (qaFlow == false) {
				Utilities.clickButton("No", 1000, driver);
			}

			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("CloseFormDO"), driver);
			System.out.println("******* Auto Assembly Delivery Order is Created for :[" + documentid
					+ "] Successfully !! *******");
		} catch (Exception EX) {
			System.out.println(
					"******* Auto Assembly Delivery Order is NOT Created for :[" + documentid + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

}
