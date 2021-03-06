package krawler.erp.inventory;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class PickPackShipModule {

	public static void create_PickDO(boolean qaFlow, String documentid, String productid, String customerid,
			String actQty, String delQty, WebDriver driver, String BatchName, String serialSeq)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "pick" + documentid;
			Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");

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
							.getText().equalsIgnoreCase("Pack Wareshouse")) {
						location = driver.findElement(By
								.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ i + "]/div"));
						location.click();
						Utilities.HoverandClick(pro.getProperty("locationDropdown"), driver);
						Utilities.HoverandClick(pro.getProperty("firstLocation"), driver);
						Thread.sleep(1000);
					}
				}

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
				Utilities.clickButton("OK", 1000, driver);
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
							.getText().equalsIgnoreCase("Pack Wareshouse")) {
						location = driver.findElement(By
								.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ i + "]/div"));
						location.click();
						Utilities.HoverandClick(pro.getProperty("locationDropdown"), driver);
						Utilities.HoverandClick(pro.getProperty("firstLocation"), driver);
						Thread.sleep(1000);
					}
				}

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
							.getText().equalsIgnoreCase("Pack Wareshouse")) {
						location = driver.findElement(By
								.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ i + "]/div"));
						location.click();
						Utilities.HoverandClick(pro.getProperty("locationDropdown"), driver);
						Utilities.HoverandClick(pro.getProperty("firstLocation"), driver);
						Thread.sleep(1000);
					}
				}

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
				Utilities.clickButton("OK", 1000, driver);

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

	// * * * * * Pack Code - > * * * * * *
	public static void create_Pack(String documentid, String packQty, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");

			Utilities.HoverandClick(pro.getProperty("DeliveryOrderReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown("pick" + documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Utilities.clickButton("Pack", 500, driver);
			Thread.sleep(1000);
			// pack
			Utilities.clickCheckBox(pro.getProperty("bulkpacking"), "check", driver);
			Utilities.enterTextNormalBox("pack" + documentid, pro.getProperty("packingNumber"), driver);
			Utilities.enterTextNormalBox("Pkg" + documentid, pro.getProperty("packageNumber"), driver);
			Utilities.HoverandClick(pro.getProperty("pkgSelectionDropDown"), driver);
			Utilities.HoverandClick(pro.getProperty("firstPkg"), driver);

			int headerSize = Utilities.totalSize("//*[text()='UOM']/ancestor::tr[1]/td", driver);
			int indOfqty = 0, indOfDOdetail = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver.findElement(By.xpath("//*[text()='UOM']/ancestor::tr[1]/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Pack Quantity")) {
					indOfqty = i;
				}
				if (headerName.equalsIgnoreCase("DO Details")) {
					indOfDOdetail = i;
				}
			}

			int totalProduct = Utilities.totalSize("//*[text()='UOM']/ancestor::div[3]/following::div[1]/div/div",
					driver);
			for (int i = 1; i <= totalProduct; i++) {
				String xpathOfqty = "//*[text()='UOM']/ancestor::div[3]/following::div[1]/div/div[" + i
						+ "]/table/tbody/tr/td[" + indOfqty + "]/div";
				Utilities.clickAndEnterText(packQty, xpathOfqty, driver);
				// move focus
				Utilities.click_element(pro.getProperty("packageNumber"), driver);

				String xpathOfDOdetails = "//*[text()='UOM']/ancestor::div[3]/following::div[1]/div/div[" + i
						+ "]/table/tbody/tr/td[" + indOfDOdetail + "]/div/div";
				Utilities.HoverandClick(xpathOfDOdetails, driver);
				Thread.sleep(1000);
				Utilities.click_element(pro.getProperty("SubmitBtn"), driver);
			}

			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			try {
				Utilities.clickButton("OK", 0, driver);
				driver.findElement(By.xpath(pro.getProperty("ClsPackForm"))).click();
			} catch (Exception okBtn) {
				// OK btn
			}
			Utilities.HoverandClick(pro.getProperty("CloseReport"), driver);
			System.out.println("**** DO Packed successfully for [" + documentid + "] with Quantity [" + packQty
					+ "] !!!!! *********");
		}

		catch (Exception EX) {
			System.out.println("**** DO NOT SHIP for [" + documentid + "] with Quantity [" + packQty
					+ "] check LOG !!!!! *********");
			Utilities.handleError(EX, driver);
		}
	}

	// *************** SHIP
	// **************************************************************
	public static void create_Ship(String documentid, String shipQty, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");

			Utilities.HoverandClick(pro.getProperty("DeliveryOrderReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown("pick" + documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Utilities.clickButton("Ship", 500, driver);
			Thread.sleep(1000);
			// Ship
			Utilities.enterTextNormalBox("ship" + documentid, pro.getProperty("ShipPackingNumber"), driver);
			Utilities.enterTextNormalBox("Memo for : " + documentid, pro.getProperty("ShipMemo"), driver);

			// 1st line
			Utilities.HoverandClick(
					"//*[@class='pwnd serialNo-gridrow']/ancestor::div[3]/div[1]//*[@class='pwnd serialNo-gridrow']",
					driver);
			Utilities.HoverandClick(pro.getProperty("SubmitBtn"), driver);

			// 2nd line
			Utilities.HoverandClick(
					"//*[@class='pwnd serialNo-gridrow']/ancestor::div[3]/div[2]//*[@class='pwnd serialNo-gridrow']",
					driver);
			Utilities.HoverandClick(pro.getProperty("SubmitBtn"), driver);

			// 3rd line
			Utilities.HoverandClick(
					"//*[@class='pwnd serialNo-gridrow']/ancestor::div[3]/div[3]//*[@class='pwnd serialNo-gridrow']",
					driver);
			Thread.sleep(1500);
			Utilities.justHover("//*[contains(@style,'visible')]//*[@class='x-grid3-cell-inner x-grid3-col-14']",
					driver);
			// enterQty
			int totalHeader = Utilities
					.totalSize("//*[contains(@style,'visible')]//*[text()='Store Code']/ancestor::tr/td/div", driver);
			for (int j = 1; j <= totalHeader; j++) {
				String headeName = driver.findElement(By.xpath(
						"//*[contains(@style,'visible')]//*[text()='Store Code']/ancestor::tr/td[" + j + "]/div"))
						.getText();
				if (headeName.equalsIgnoreCase("Quantity")) {
					String xpathToQty = "//*[contains(@style,'visible')]//*[text()='Store Code']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td["
							+ j + "]/div";
					Utilities.clickAndEnterText(shipQty, xpathToQty, driver);
				}
				if (headeName.equalsIgnoreCase("Serials")) {
					String xpathToSerial = "//*[contains(@style,'visible')]//*[text()='Store Code']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td["
							+ j + "]/div";
					Utilities.HoverandClick(xpathToSerial, driver);
					Utilities.HoverandClick(xpathToSerial, driver);

					int serialCnt = Integer.parseInt(shipQty);
					for (int k = 1; k <= serialCnt; k++) {
						Utilities.HoverandClick(
								"//*[text()='Available Serials']/ancestor::div[2]//*[text()='Name']/ancestor::div[3]/following::div[1]/div/div["
										+ k + "]//td[1]/div/div",
								driver);
					}
					Utilities.HoverandClick("//*[contains(@src,'arrowright')]", driver);
					Utilities.HoverandClick(pro.getProperty("SerialSubmit"), driver);
				}
			}
			Thread.sleep(1000);
			Utilities.HoverandClick(pro.getProperty("SubmitBtn"), driver);

			// 4th line
			Utilities.HoverandClick(
					"//*[@class='pwnd serialNo-gridrow']/ancestor::div[3]/div[4]//*[@class='pwnd serialNo-gridrow']",
					driver);
			Thread.sleep(1500);
			// enterQty
			totalHeader = Utilities
					.totalSize("//*[contains(@style,'visible')]//*[text()='Store Code']/ancestor::tr/td/div", driver);
			for (int j = 1; j <= totalHeader; j++) {
				String headeName = driver.findElement(By.xpath(
						"//*[contains(@style,'visible')]//*[text()='Store Code']/ancestor::tr/td[" + j + "]/div"))
						.getText();
				if (headeName.equalsIgnoreCase("Quantity")) {
					String xpathToQty = "//*[contains(@style,'visible')]//*[text()='Store Code']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td["
							+ j + "]/div";
					Utilities.HoverandClick(xpathToQty, driver);

					StringSelection stringSelection = new StringSelection(shipQty);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_A);
					robot.keyPress(KeyEvent.VK_DELETE);
					robot.keyRelease(KeyEvent.VK_DELETE);
					robot.keyRelease(KeyEvent.VK_A);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					Thread.sleep(1000);
					int serialCnt = Integer.parseInt(shipQty);
					for (int k = 1; k <= serialCnt; k++) {
						Utilities.HoverandClick(
								"//*[text()='Available Serials']/ancestor::div[2]//*[text()='Name']/ancestor::div[3]/following::div[1]/div/div["
										+ k + "]//td[1]/div/div",
								driver);
					}
					Utilities.HoverandClick("//*[contains(@src,'arrowright')]", driver);
					Utilities.HoverandClick(pro.getProperty("SerialSubmit"), driver);
				}
			}
			Thread.sleep(1000);
			Utilities.HoverandClick(pro.getProperty("SubmitBtn"), driver);
			///////////////////////////////////////////////////
			int headerSize = Utilities.totalSize("//*[text()='UOM']/ancestor::tr[1]/td", driver);
			int indOfDOdetail = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver.findElement(By.xpath("//*[text()='UOM']/ancestor::tr[1]/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("DO Details")) {
					indOfDOdetail = i;
				}
			}

			int totalProduct = Utilities.totalSize("//*[text()='UOM']/ancestor::div[3]/following::div[1]/div/div",
					driver);
			for (int i = 1; i <= totalProduct; i++) {
				String xpathOfDOdetails = "//*[text()='UOM']/ancestor::div[3]/following::div[1]/div/div[" + i
						+ "]/table/tbody/tr/td[" + indOfDOdetail + "]/div/div";
				Utilities.HoverandClick(xpathOfDOdetails, driver);
				Thread.sleep(1000);
				Utilities.click_element(pro.getProperty("SubmitBtn"), driver);
			}

			Utilities.clickButton("Save", 2000, driver);
			System.out.println("**** DO SHIP successfully for [" + documentid + "]  !!!!! *********");
			Thread.sleep(3000);

		}

		catch (Exception EX) {
			System.out.println("**** DO NOT Packed for [" + documentid + "] check LOG !!!!! *********");
			EX.printStackTrace();
			// Utilities.handleError(EX, driver);
		}
	}
}
