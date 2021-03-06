package krawler.erp.modules;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.inventory.InvUtilities;
import krawler.erp.page.EmailFunctionality;
import krawler.erp.page.Utilities;

public class DeliveryOrderModule1 {

	public static void create_DeliveryOrder(String documentid, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "DO" + documentid;
			Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");

			Utilities.waitandClick(pro.getProperty("DeliveryOrderIcon"), driver);
			selectFromNormalDropDown(customerid, pro.getProperty("CustomerId"), driver);
			selectFromNormalDropDown("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("DeliveryOrderNo"), driver);

			Utilities.clickCheckBox(pro.getProperty("includingGST"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Yes", 200, driver);

			Utilities.HoverandClick(pro.getProperty("AddProduct"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("SearchProduct"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("ClickOnAdd"), driver);
			Thread.sleep(2000);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexActQty = 0, indexRecQty = 0;
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
			}

			for (int i = 1; i <= totalResult; i++) { // add actual qty
				Utilities.HoverandClick(
						"//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexActQty + "]/div",
						driver);
				Utilities.enter_LinelabelAmount("10", driver);
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
				// add receive qty
				Utilities.HoverandClick(
						"//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexRecQty + "]/div",
						driver);
				Utilities.enter_LinelabelAmount("10", driver);
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			}

			// to scroll till end
			Utilities.justHover(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[contains(@class,'x-grid3-cell-last')]",
					driver);

			// enter line level Tax
			String taxNme[] = { "GST(DS)@7.00%", "GST(SR)@7.00%" };
			int sizeOfArr = taxNme.length;
			int indexTax = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (HeadeName.equalsIgnoreCase("Product Tax")) {
					indexTax = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[" + i
								+ "]/table/tbody/tr/td[" + indexTax + "]/div",
						driver);
				Thread.sleep(1000);

				if (i <= sizeOfArr) {
					selectFromNormalDropDown(taxNme[i - 1], "//input[@id='prtaxid']/following::input[1]", driver);
				} else {
					int index = getArrayIndex(i, sizeOfArr);
					selectFromNormalDropDown(taxNme[index - 1], "//input[@id='prtaxid']/following::input[1]", driver);
				}
			}

			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("No", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			String subjectLine = "Delivery Order- testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);

			Utilities.HoverandClick(pro.getProperty("CloseFormDO"), driver);

			System.out.println("******* Delivery Order : [" + documentid + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Delivery Order is NOT Created for :[" + documentid + "] checlk LOG *******");
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
	public static void Copy_DeliveryOrder(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			documentid = "DO" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");

			Utilities.HoverandClick(pro.getProperty("DeliveryOrderReport"), driver);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			/*
			 * //Report Email code String
			 * subjectLine="Delivery Order testsmoke - "+documentid;
			 * EmailFunctionality.email_fromReports(documentid,subjectLine,
			 * driver);
			 * Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"),
			 * "uncheck", driver); Utilities.enterTextInDropDown(documentid,
			 * pro.getProperty("QuickSearch"), driver); Thread.sleep(2000);
			 * Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"),
			 * "check", driver);
			 */
			Utilities.HoverandClick(pro.getProperty("copyDeliveryOrderButton"), driver);

			Utilities.formLoaded(driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("copyDeliveryOrderId"), driver);
			Utilities.HoverandClick(pro.getProperty("saveCopiedDeliveryOrderId"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("No", 1000, driver); // No QA
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("CopyDeliveryOrderClose"), driver);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Delivery Order Copy Created !!");
			} else {
				System.out.println("Delivery Order Copy NOT Created !!");
			}
		} catch (Exception EX) {
			System.out.println("Delivery Order Copy NOT Created !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Edit ******************************************

	public static void Edit_DeliveryOrder(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			documentid = "DO" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");

			Utilities.HoverandClick(pro.getProperty("EditDeliveryOrderButton"), driver);
			// to Load page properly
			Utilities.formLoaded(driver);
			Utilities.enterTextInDropDown("Performing Edit Operation", pro.getProperty("DeliveryOrderMemo"), driver);

			int headerSize = Utilities.totalSize("//*[text()='Product ID']/ancestor::tr/td", driver);
			int indOfPrdID = 0, indOfact = 0, indOfrec = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver
						.findElement(By.xpath("//*[text()='Product ID']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headerName.equalsIgnoreCase("Product ID")) {
					indOfPrdID = i;
				}
				if (headerName.equalsIgnoreCase("Actual Quantity")) {
					indOfact = i;
				}
				if (headerName.equalsIgnoreCase("Delivered Quantity")) {
					indOfrec = i;
				}
			}

			// add 3rd prd
			String addThridProduct = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td["
					+ indOfPrdID + "]/div";
			Utilities.HoverandClick(addThridProduct, driver);

			selectFromNormalDropDown("EditProduct", "//*[@name='pid']", driver);
			Utilities.HoverandClick(pro.getProperty("DeliveryOrderMemo"), driver);

			String actualQty = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td["
					+ indOfact + "]/div";
			Utilities.HoverandClick(actualQty, driver);
			Utilities.enter_LinelabelAmount("5", driver);
			Utilities.HoverandClick(pro.getProperty("DeliveryOrderMemo"), driver);

			String receQty = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td["
					+ indOfrec + "]/div";
			Utilities.HoverandClick(receQty, driver);
			Utilities.enter_LinelabelAmount("5", driver);
			Utilities.HoverandClick(pro.getProperty("DeliveryOrderMemo"), driver);

			String wlocation = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[3]//*[@class='pwnd serialNo-gridrow']";
			Utilities.HoverandClick(wlocation, driver);
			Utilities.clickButton("Submit", 800, driver);

			Utilities.HoverandClick(pro.getProperty("DeliveryOrdereEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("No", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("EditDeliveryOrderClose"), driver);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
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
			documentid = "DO" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");

			Utilities.HoverandClick(pro.getProperty("deleteDeliveryOrderButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteDeliveryOrderPermButton"), driver);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(1500);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
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

	// *************** Mandotory checks *************************

	public static void Inventory_Mandotorychecks(WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_SystemControls.properties");
			WebElement element = null;
			Actions action = new Actions(driver);

			InvUtilities.enableExpander(driver);
			Utilities.waitandClick(pro.getProperty("PreferencesExpander"), driver);
			Utilities.waitandClick(pro.getProperty("SystemControlsLink"), driver);
			Thread.sleep(10000);
			Utilities.clickExpander(driver);

			String arr[] = { "unitPriceInDO", "unitPriceInGR", "unitPriceInSR", "unitPriceInPR", "isQaApprovalFlowInDO",
					"isQaApprovalFlow", "stockInQAApproval" };
			for (int i = 0; i < arr.length; i++) {
				element = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='" + arr[i] + "']")));

				if (!element.isSelected()) {
					action.moveToElement(element).build().perform();
					Thread.sleep(500);
					element.click();
					System.out.println("*** Check now ENABLED for [" + arr[i] + "] ***");
				} else {
					System.out.println("*** Check Already ENABLED for [" + arr[i] + "] ***");
				}
			}

			Utilities.HoverandClick(pro.getProperty("saveAccountPreferencesBtn"), driver);
			Utilities.HoverandClick(pro.getProperty("applyNewsettingsBtn"), driver);
			Utilities.clickButton("OK", 1500, driver);

		} catch (Exception EX) {
			;
			Utilities.handleError(EX, driver);
		}
	}

	// ********************Global Tax*******************************
	public static void createGL_DeliveryOrder(String documentid, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "DoGL" + documentid;
			Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");

			Utilities.waitandClick(pro.getProperty("DeliveryOrderIcon"), driver);

			selectFromNormalDropDown(customerid, pro.getProperty("CustomerId"), driver);

			Utilities.enterTextInDropDown("NA", pro.getProperty("SequenceFormat"), driver);

			Utilities.enterTextInDropDown(documentid, pro.getProperty("DeliveryOrderNo"), driver);

			Utilities.HoverandClick(pro.getProperty("AddProduct"), driver);

			Utilities.clickCheckBox(pro.getProperty("SelectCheckBox"), "uncheck", driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("SearchProduct"), driver);
			Thread.sleep(2000);
			Utilities.clickCheckBox(pro.getProperty("SelectproductSS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);

			Utilities.HoverandClick(pro.getProperty("ClickOnAdd"), driver);
			Thread.sleep(1500);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexActQty = 0, indexRecQty = 0;
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
			}

			for (int i = 1; i <= totalResult; i++) { // add actual qty
				Utilities.HoverandClick(
						"//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexActQty + "]/div",
						driver);
				Utilities.sendText("10");
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
				// add receive qty
				Utilities.HoverandClick(
						"//div[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexRecQty + "]/div",
						driver);
				Utilities.sendText("10");
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			}

			// to scroll till end
			Utilities.justHover("//*[contains(text(),'Outstanding Balance')]", driver);
			selectFromNormalDropDown("Yes", pro.getProperty("includeGlobalTax"), driver);
			selectFromNormalDropDown("GST(DS)@7.00%", pro.getProperty("GlobalTaxValue"), driver);

			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("No", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("CloseFormDO"), driver);
			System.out.println("******* Delivery Order : [" + documentid + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Delivery Order is NOT Created for :[" + documentid + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

}
