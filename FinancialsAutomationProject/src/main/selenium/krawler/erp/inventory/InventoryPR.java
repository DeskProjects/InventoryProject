package krawler.erp.inventory;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class InventoryPR {
	public static void create_PurchaseReturnOnly(String documentid, String productid, String vendorid, String actualQty,
			String receiveQty, WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			documentid = "InPr" + documentid;
			Properties pro = Utilities.fetchProValue("OR_PurchaseReturnOnly.properties");

			Utilities.click_element(pro.getProperty("PurchaseReturnOnlyIcon"), driver);
			Utilities.click_element(pro.getProperty("buttonSubmit"), driver);
			Utilities.disableExpander(driver);
			selectFromNormalDropDown(vendorid, pro.getProperty("VendorId"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("PurchaseReturnOnlyNo"), driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("SearchProductId"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.click_element(
					"//*[contains(@id,'ProductSelectionWindow')]//*[contains(text(),'Product ID')]/ancestor::tr//*[@class='x-grid3-hd-checker']",
					driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.click_element(
					"//*[contains(@id,'ProductSelectionWindow')]//*[@class='x-btn-text' and text()='Add']", driver);
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
						.xpath("//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Actual Quantity")) {
					indexActQty = i;
				}
				if (HeadeName.equalsIgnoreCase("Return Quantity")) {
					indexRecQty = i;
				}
				if (HeadeName.equalsIgnoreCase("Product ID")) {
					indProdID = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) { // add actual qty
				Utilities.HoverandClick(
						"//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexActQty + "]/div",
						driver);
				Utilities.sendText(actualQty);
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
				// add receive qty
				Utilities.HoverandClick(
						"//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexRecQty + "]/div",
						driver);
				Utilities.sendText(receiveQty);
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
			}

			WebElement wslocationlink = null, batch = null, serial = null, location = null;
			int normal = 0, ser = 0, bat = 0, batchserial = 0;

			for (int j = 1; j <= totalResult; j++) {
				String productIDStr = driver.findElement(
						By.xpath("//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
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
				String xpathWlocation = "//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
						+ normal + "]/table/tbody/tr/td[15]/div/div";
				Utilities.click_element(xpathWlocation, driver);
				Thread.sleep(1000);
				Utilities.clickButton("Submit", 1000, driver);
			}

			// for batch
			// *****************************************************************************
			if (bat > 0) {
				String xpathWlocation = "//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
						+ bat + "]/table/tbody/tr/td[15]/div/div";
				Utilities.click_element(xpathWlocation, driver);
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
				Utilities.click_element(xpathOfchec, driver);
				Utilities.HoverandClick("//*[@class='x-window']//button[text()='OK']", driver);
				Utilities.clickButton("Submit", 1000, driver);

			}

			// for serial
			// ******************************************************************************
			if (ser > 0) {
				String xpathWlocation = "//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
						+ ser + "]/table/tbody/tr/td[15]/div/div";
				Utilities.click_element(xpathWlocation, driver);
				Thread.sleep(1000);

				for (int i = 1; i <= driver
						.findElements(By
								.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td/div"))
						.size(); i++) {
					if (driver.findElement(By
							.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText().equalsIgnoreCase("Serial No")) {
						for (int qtycnt = 1; qtycnt <= Integer.parseInt(actualQty); qtycnt++) {
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

				for (int k = 1; k <= Integer.parseInt(actualQty); k++) {
					String xpathOfSerial = "//*[text()='Available Serials']/ancestor::div[2]/div[2]/div[2]//div[@class='x-grid3-scroller']/div/div["
							+ k + "]/table/tbody/tr/td[1]/div/div";
					Utilities.click_element(xpathOfSerial, driver);
				}

				Utilities.click_element("//*[contains(@style,'padding')]/img[contains(@src,'arrowright')]", driver);
				Utilities.click_element(
						"//div[@id='serialSelectWindow' and contains(@style,'visible')]//button[text()='Submit']",
						driver);
				Utilities.HoverandClick(
						"//div[@class='x-window' and contains(@style,'visible')]//button[text()='Submit']", driver);
				Thread.sleep(2000);
			}

			// for batchserial
			// *********************************************************************
			if (batchserial > 0) {
				String xpathWlocation = "//div[@id='purchasereturnbillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
						+ batchserial + "]/table/tbody/tr/td[15]/div/div";
				Utilities.click_element(xpathWlocation, driver);
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

				for (int k = 1; k <= Integer.parseInt(actualQty); k++) {
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
				Thread.sleep(1500);
			}

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("ClosePurchaseReturnOnly"), driver);
			System.out.println("******* Purchase Return Only : [" + documentid + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Purchase Return Only for :[" + documentid + "] checlk LOG *******");
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

}
