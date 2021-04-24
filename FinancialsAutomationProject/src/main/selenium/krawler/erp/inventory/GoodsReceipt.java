package krawler.erp.inventory;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import krawler.erp.page.Utilities;

public class GoodsReceipt {

	public static void create_InventoryGR(boolean QAsetting, String productid, String vendorid, String documentid,
			String actualQty, String receiveQty, String BatchName, String serialSeq, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			Properties pro = Utilities.fetchProValue("OR_GoodReceipt.properties");
			documentid = "GRN" + documentid;
			String LoadingIcon = "//*[contains(text(),'Loading.')]";

			Utilities.waitandClick(pro.getProperty("GoodReceiptIcon"), driver);
			Utilities.disableExpander(driver);
			selectFromNormalDropDown(vendorid, pro.getProperty("Vendor"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("DocumentId"), driver);
			selectFromNormalDropDown("NET 45", pro.getProperty("Term"), driver);

			Utilities.HoverandClick(pro.getProperty("GRaddButton"), driver);
			Utilities.isElementGone(LoadingIcon, 30, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("Search"), driver);
			Utilities.isElementGone(LoadingIcon, 30, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indProdID = 0, indActqty = 0, indRecqty = 0;

			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Product ID")) {
					indProdID = i;
				} else if (HeadeName.equalsIgnoreCase("Actual Quantity")) {
					indActqty = i;
				} else if (HeadeName.equalsIgnoreCase("Received Quantity")) {
					indRecqty = i;
				}
			}
			// System.out.println(indProdID+"<"+indActqty+">"+indRecqty);

			WebElement memo = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Memo"))));
			String grn = "";
			for (int i = 1; i <= totalResult; i++) {
				grn = 	"//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
						+ i + "]/table/tbody/tr/td[" + indActqty + "]/div";
				System.out.println(grn);
				Utilities.HoverandClick(grn, driver);
				Utilities.enter_LinelabelAmount(actualQty, driver);
				memo.click();

				Utilities.HoverandClick(
						"//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[" + indRecqty + "]/div",
						driver);
				Utilities.enter_LinelabelAmount(receiveQty, driver);
				memo.click();
			}

			WebElement wslocationlink = null, batch = null, serial = null, location = null;
			int normal = 0, ser = 0, bat = 0, batchserial = 0;

			for (int j = 1; j <= totalResult; j++) {
				String productIDStr = driver.findElement(By
						.xpath("//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
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
			if (normal > 0) {
				wslocationlink = driver.findElement(By
						.xpath("//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
								+ normal + "]/table/tbody/tr/td//div[contains(@class,'serialNo-gridrow')]"));
				wslocationlink.click();
				Thread.sleep(1000);
				Utilities.clickButton("Submit", 1000, driver);
			}

			// for batch
			if (bat > 0) {
				wslocationlink = driver.findElement(By
						.xpath("//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
								+ bat + "]/table/tbody/tr/td//div[contains(@class,'serialNo-gridrow')]"));
				wslocationlink.click();
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
						Thread.sleep(2000);
						Robot batchRobot = new Robot();
						Utilities.sendText(BatchName);
						Thread.sleep(1000);
						batchRobot.keyPress(KeyEvent.VK_TAB);
						batchRobot.keyRelease(KeyEvent.VK_TAB);
						Thread.sleep(1000);
						Utilities.clickButton("Submit", 1000, driver);
						break;
					}
				}
			}

			Thread.sleep(1500);
			// for serial
			if (ser > 0) {
				wslocationlink = driver.findElement(By
						.xpath("//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
								+ ser + "]/table/tbody/tr/td/div/div[contains(@class,'serialNo-gridrow')]"));
				wslocationlink.click();
				Thread.sleep(3000);
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
						for (int qtycnt = 1; qtycnt <= Integer.parseInt(actualQty); qtycnt++) {
							serial = driver.findElement(By
									.xpath("//div[@class='x-window-body']/div[3]/div/div/div/div/div/div/div[1]/div[2]/div/div["
											+ qtycnt + "]/table/tbody/tr/td[" + i + "]/div"));
							Thread.sleep(1000);
							serial.click();
							Thread.sleep(1000);

							Robot r2 = new Robot();
							r2.keyPress(KeyEvent.VK_CONTROL);
							r2.keyPress(KeyEvent.VK_A);
							r2.keyRelease(KeyEvent.VK_A);
							r2.keyRelease(KeyEvent.VK_CONTROL);
							r2.keyPress(KeyEvent.VK_DELETE);
							r2.keyRelease(KeyEvent.VK_DELETE);
							Utilities.sendText(serialSeq + qtycnt);
							Thread.sleep(2000);
						}
						Utilities.HoverandClick(
								"//div[@class='x-window' and contains(@style,'visible')]//button[text()='Submit']",
								driver);
						Thread.sleep(2000);
						break;
					}
				}
			}
			Thread.sleep(1500);
			// for batchserial
			if (batchserial > 0) {
				wslocationlink = driver.findElement(By
						.xpath("//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
								+ batchserial + "]/table/tbody/tr/td/div/div[contains(@class,'serialNo-gridrow')]"));
				wslocationlink.click();
				Thread.sleep(3000);

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
						Robot batchRobot = new Robot();
						Utilities.sendText(BatchName);
						Thread.sleep(1000);
						batchRobot.keyPress(KeyEvent.VK_TAB);
						batchRobot.keyRelease(KeyEvent.VK_TAB);
						Thread.sleep(1000);
						String moveTo = "//div[@class='x-window' and contains(@style,'visible')]//div[1]/table//div[@class='pwnd delete-gridrow']";
						Utilities.justHover(moveTo, driver);
						Thread.sleep(1000);
					}

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

							Robot r2 = new Robot();
							r2.keyPress(KeyEvent.VK_CONTROL);
							r2.keyPress(KeyEvent.VK_A);
							r2.keyRelease(KeyEvent.VK_A);
							r2.keyRelease(KeyEvent.VK_CONTROL);
							r2.keyPress(KeyEvent.VK_DELETE);
							r2.keyRelease(KeyEvent.VK_DELETE);
							Utilities.sendText(serialSeq + qtycnt);
							Thread.sleep(2000);
						}
					}
				}
				Utilities.HoverandClick(
						"//div[@class='x-window' and contains(@style,'visible')]//button[text()='Submit']", driver);
				Thread.sleep(2000);
			}

			// Enter price
			WebElement price = null;
			String delXpath = "//*[@class='pwnd delete-gridrow']/ancestor::div[3]/div[1]/table//*[@class='pwnd delete-gridrow']";
			Utilities.justHover(delXpath, driver);
			Thread.sleep(1000);

			int indexOfPrice = 0;
			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Unit Price")) {
					indexOfPrice = i;
				}
			}

			for (int j = 1; j <= totalResult; j++) {
				price = driver.findElement(By
						.xpath("//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[" + indexOfPrice + "]/div"));
				price.click();
				Thread.sleep(1000);
				Utilities.sendText("10");
				Thread.sleep(1000);
				memo.click();
			}

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);

			if (QAsetting == true) {
				Utilities.clickButton("Yes", 1000, driver);
			} else if (QAsetting == false) {
				Utilities.clickButton("No", 1000, driver);
			}

			Utilities.clickButton("OK", 1500, driver);

			String xpathOfelement = "//*[@id='as__GoodsReceiptgoodsreceiptdelivery']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);
			System.out.println("******* GRN successfully created [" + documentid + "] ************ ");
		} catch (Exception Ex) {
			System.out.println("******* GRN FAILED to create [" + documentid + "] ************ ");
			Utilities.handleError(Ex, driver);
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

	// ******************************** QA flow On or Off
	// **************************

	public static void setting_QaFlow(boolean QAsetUp, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_SystemControls.properties");
			WebElement element = null;
			WebDriverWait wait = new WebDriverWait(driver, 60);

			InvUtilities.enableExpander(driver);
			Utilities.waitandClick(pro.getProperty("PreferencesExpander"), driver);
			Utilities.waitandClick(pro.getProperty("SystemControlsLink"), driver);
			Thread.sleep(10000);

			// ************************ if we want QA flow off
			// **************************

			Utilities.justHover(pro.getProperty("GoodsReceiptNoteQAApprovalFlowCheckBox"), driver);
			Thread.sleep(1000);

			if (QAsetUp == false) {
				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						return webDriverArg
								.findElement(By.xpath(pro.getProperty("GoodsReceiptNoteQAApprovalFlowCheckBox")));
					}
				});
				Thread.sleep(1000);
				if (element.isSelected()) {
					element.click();
					System.out.println("QA Approval Flow for GRN is Deactivated");
				} else {
					System.out.println("QA Approval Flow for GRN is Already Deactivated");
				}
			}

			// ********************* if we want QA flow
			// ON********************************
			else if (QAsetUp == true) {

				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg
								.findElement(By.xpath(pro.getProperty("GoodsReceiptNoteQAApprovalFlowCheckBox")));
					}
				});
				Thread.sleep(1000);
				if (element.isSelected()) {
					System.out.println("QA Approval Flow for GRN is already Activated");
				} else {
					element.click();
					System.out.println("QA Approval Flow for GRN is NOW Activated");
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

	// Approve
	public static void approveRequest(String documentid, String approvedQuanity, String productid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_QAApproval.properties");
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);
			String LoadingIcon = "//*[contains(text(),'Loading.')]";

			Utilities.HoverandClick(pro.getProperty("QAApprovalLink"), driver);
			Utilities.clickCheckBox("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div[1]//td[1]",
					"uncheck", driver);
			Utilities.clickExpander(driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			driver.findElement(By.xpath(pro.getProperty("QuickSearch"))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			int headerCnt = Utilities.totalSize("//*[text()='Transaction No']/ancestor::tr/td/div", driver);
			int indOfprid = 0, indOfDetails = 0;

			for (int i = 1; i <= headerCnt; i++) {
				String headName = driver
						.findElement(By.xpath("//*[text()='Transaction No']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headName.equalsIgnoreCase("Product ID")) {
					indOfprid = i;
				}
				if (headName.equalsIgnoreCase("View Details")) {
					indOfDetails = i;
				}
			}

			StringBuilder stb = new StringBuilder();
			stb.append("ProductId,Batch,Serial");
			List<String> QAApprovalList = new ArrayList<String>();
			QAApprovalList.add(stb.toString());

			int NormalPrd = 0, BatchPrd = 0, SerialPrd = 0, BatchSer = 0;
			String Quantity = null;
			int headerOfdetails = 0;
			int totalFound = Utilities
					.totalSize("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div[1]/div", driver);
			for (int j = 1; j <= totalFound; j++) {
				String prdType = driver
						.findElement(By.xpath("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
								+ j + "]/table/tbody/tr/td[" + indOfprid + "]"))
						.getText();
				if (prdType.equalsIgnoreCase(productid)) {
					NormalPrd = j;
				}
				if (prdType.endsWith("B2")) {
					BatchPrd = j;
				}
				if (prdType.endsWith("S3")) {
					SerialPrd = j;
				}
				if (prdType.endsWith("BS4")) {
					BatchSer = j;
				}
			}

			String prodName = null;
			String serialNo = null;
			if (NormalPrd > 0) {
				Utilities.HoverandClick("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
						+ NormalPrd + "]/table/tbody/tr/td[" + indOfDetails + "]", driver);
				Thread.sleep(250);
				headerOfdetails = Utilities.totalSize(
						"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td/div", driver);
				for (int k = 1; k <= headerOfdetails; k++) {
					Quantity = driver.findElement(By.xpath(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td[" + k + "]/div"))
							.getText();
					if (Quantity.equalsIgnoreCase("Quantity")) {
						Utilities.HoverandClick(
								"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
										+ k + "]/div",
								driver);
						Utilities.enter_LinelabelAmount(approvedQuanity, driver);
						break;
					}
				}
				Utilities.HoverandClick("//*[contains(@id,'StockoutQADetail')]//button[text()='Approve']", driver);
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("Save", 1000, driver);
				Utilities.isElementGone(LoadingIcon, 20, driver);
				Utilities.clickButton("OK", 500, driver);
				Utilities.click_element(pro.getProperty("QAApprovalDetailsTabCloseBtn"), driver);
				Thread.sleep(250);
			}

			if (BatchPrd > 0) {
				Utilities.HoverandClick("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
						+ BatchPrd + "]/table/tbody/tr/td[" + indOfDetails + "]", driver);
				Thread.sleep(250);
				headerOfdetails = Utilities.totalSize(
						"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td/div", driver);
				for (int k = 1; k <= headerOfdetails; k++) {
					Quantity = driver.findElement(By.xpath(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td[" + k + "]/div"))
							.getText();
					if (Quantity.equalsIgnoreCase("Quantity")) {
						Utilities.HoverandClick(
								"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
										+ k + "]/div",
								driver);
						Utilities.enter_LinelabelAmount(approvedQuanity, driver);
						break;
					}
				}
				Utilities.HoverandClick("//*[contains(@id,'StockoutQADetail')]//button[text()='Approve']", driver);
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("Save", 1000, driver);
				Utilities.isElementGone(LoadingIcon, 20, driver);
				Utilities.clickButton("OK", 500, driver);
				Utilities.click_element(pro.getProperty("QAApprovalDetailsTabCloseBtn"), driver);
				Thread.sleep(250);
			}

			if (SerialPrd > 0) {
				prodName = driver
						.findElement(By.xpath("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
								+ SerialPrd + "]/table/tbody/tr/td[" + indOfprid + "]"))
						.getText();
				Utilities.HoverandClick("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
						+ SerialPrd + "]/table/tbody/tr/td[" + indOfDetails + "]", driver);
				Thread.sleep(250);

				for (int k = 1; k <= Integer.parseInt(approvedQuanity); k++) {
					Utilities.HoverandClick(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div["
									+ k + "]/table/tbody//*[@class='x-grid3-row-checker']",
							driver);
				}

				for (int k = 1; k <= headerOfdetails; k++) {
					Quantity = driver.findElement(By.xpath(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td[" + k + "]/div"))
							.getText();
					if (Quantity.equalsIgnoreCase("Serial")) {
						for (int sr = 1; sr <= Integer.parseInt(approvedQuanity); sr++) {
							serialNo = driver.findElement(By
									.xpath("//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div["
											+ sr + "]/table/tbody/tr/td[" + k + "]/div"))
									.getText();
							stb.setLength(0);
							stb.append(prodName).append(",").append("Batch").append(",").append(serialNo);
							QAApprovalList.add(stb.toString());
						}
						break;
					}
				}
				Utilities.HoverandClick("//*[contains(@id,'StockoutQADetail')]//button[text()='Approve']", driver);
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("Save", 1000, driver);
				Utilities.isElementGone(LoadingIcon, 20, driver);
				Utilities.clickButton("OK", 500, driver);
				Utilities.click_element(pro.getProperty("QAApprovalDetailsTabCloseBtn"), driver);
				Thread.sleep(250);
			}

			if (BatchSer > 0) {
				prodName = driver
						.findElement(By.xpath("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
								+ BatchSer + "]/table/tbody/tr/td[" + indOfprid + "]"))
						.getText();
				Utilities.HoverandClick("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
						+ BatchSer + "]/table/tbody/tr/td[" + indOfDetails + "]", driver);
				Thread.sleep(250);

				for (int k = 1; k <= Integer.parseInt(approvedQuanity); k++) {
					Utilities.HoverandClick(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div["
									+ k + "]/table/tbody//*[@class='x-grid3-row-checker']",
							driver);
				}

				for (int k = 1; k <= headerOfdetails; k++) {
					Quantity = driver.findElement(By.xpath(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td[" + k + "]/div"))
							.getText();
					if (Quantity.equalsIgnoreCase("Serial")) {
						for (int sr = 1; sr <= Integer.parseInt(approvedQuanity); sr++) {
							serialNo = driver.findElement(By
									.xpath("//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div["
											+ sr + "]/table/tbody/tr/td[" + k + "]/div"))
									.getText();
							stb.setLength(0);
							stb.append(prodName).append(",").append("BatchSerial").append(",").append(serialNo);
							QAApprovalList.add(stb.toString());
						}
						break;
					}
				}
				Utilities.HoverandClick("//*[contains(@id,'StockoutQADetail')]//button[text()='Approve']", driver);
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("Save", 1000, driver);
				Utilities.isElementGone(LoadingIcon, 20, driver);
				Utilities.clickButton("OK", 500, driver);
				Utilities.click_element(pro.getProperty("QAApprovalDetailsTabCloseBtn"), driver);
				Thread.sleep(250);
			}
			String[] array = QAApprovalList.toArray(new String[0]);
			Utilities.writeSerialsToFile(
					documentid + "[QA_APPROVED_serials]" + Utilities.currentDate("dd-MM-yyyy") + ".txt", array);

			Utilities.click_element(pro.getProperty("QAApprovalCloseBtn"), driver);
			System.out.println("************ QA - Approval test case for [" + documentid + "] is PASS ************");
		} catch (Exception Ex) {
			System.out.println(
					"!!!!-WARNING-!!!!!!!!! QA - Approval test case for [" + documentid + "] is FAIL !!!!!!!!!!!!!");
			Utilities.handleError(Ex, driver);
		}

	}

	// Reject
	public static void rejectRequest(String documentid, String rejectedQuanity, String productid, WebDriver driver,
			boolean isGRN) throws InterruptedException, AWTException, IOException {

		try {
			final Properties pro = Utilities.fetchProValue("OR_QAApproval.properties");
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);
			String LoadingIcon = "//*[contains(text(),'Loading.')]";

			Utilities.HoverandClick(pro.getProperty("QAApprovalLink"), driver);
			Utilities.clickCheckBox("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div[1]//td[1]",
					"uncheck", driver);
			Utilities.clickExpander(driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			driver.findElement(By.xpath(pro.getProperty("QuickSearch"))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			int headerCnt = Utilities.totalSize("//*[text()='Transaction No']/ancestor::tr/td/div", driver);
			int indOfprid = 0, indOfDetails = 0;

			for (int i = 1; i <= headerCnt; i++) {
				String headName = driver
						.findElement(By.xpath("//*[text()='Transaction No']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headName.equalsIgnoreCase("Product ID")) {
					indOfprid = i;
				}
				if (headName.equalsIgnoreCase("View Details")) {
					indOfDetails = i;
				}
			}

			StringBuilder stb = new StringBuilder();
			stb.append("ProductId,Batch,Serial");
			List<String> QAApprovalList = new ArrayList<String>();
			QAApprovalList.add(stb.toString());

			int NormalPrd = 0, BatchPrd = 0, SerialPrd = 0, BatchSer = 0;
			String Quantity = null;
			int headerOfdetails = 0;
			int totalFound = Utilities
					.totalSize("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div[1]/div", driver);
			for (int j = 1; j <= totalFound; j++) {
				String prdType = driver
						.findElement(By.xpath("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
								+ j + "]/table/tbody/tr/td[" + indOfprid + "]"))
						.getText();
				if (prdType.equalsIgnoreCase(productid)) {
					NormalPrd = j;
				}
				if (prdType.endsWith("B2")) {
					BatchPrd = j;
				}
				if (prdType.endsWith("S3")) {
					SerialPrd = j;
				}
				if (prdType.endsWith("BS4")) {
					BatchSer = j;
				}
			}

			String prodName = null;
			String serialNo = null;
			if (NormalPrd > 0) {
				Utilities.HoverandClick("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
						+ NormalPrd + "]/table/tbody/tr/td[" + indOfDetails + "]", driver);
				Thread.sleep(250);
				headerOfdetails = Utilities.totalSize(
						"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td/div", driver);
				for (int k = 1; k <= headerOfdetails; k++) {
					Quantity = driver.findElement(By.xpath(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td[" + k + "]/div"))
							.getText();
					if (Quantity.equalsIgnoreCase("Quantity")) {
						Utilities.HoverandClick(
								"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
										+ k + "]/div",
								driver);
						Utilities.enter_LinelabelAmount(rejectedQuanity, driver);
						break;
					}
				}
				Utilities.HoverandClick("//*[contains(@id,'StockoutQADetail')]//button[text()='Reject']", driver);
				Utilities.isElementGone(LoadingIcon, 20, driver);
				if (isGRN == true) {
					Utilities.clickButton("Submit", 500, driver);
				}
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("Save", 1000, driver);
				Utilities.isElementGone(LoadingIcon, 20, driver);
				Utilities.clickButton("OK", 500, driver);
				Utilities.click_element(pro.getProperty("QAApprovalDetailsTabCloseBtn"), driver);
				Thread.sleep(250);
			}

			if (BatchPrd > 0) {
				Utilities.HoverandClick("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
						+ BatchPrd + "]/table/tbody/tr/td[" + indOfDetails + "]", driver);
				Thread.sleep(250);
				headerOfdetails = Utilities.totalSize(
						"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td/div", driver);
				for (int k = 1; k <= headerOfdetails; k++) {
					Quantity = driver.findElement(By.xpath(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td[" + k + "]/div"))
							.getText();
					if (Quantity.equalsIgnoreCase("Quantity")) {
						Utilities.HoverandClick(
								"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
										+ k + "]/div",
								driver);
						Utilities.enter_LinelabelAmount(rejectedQuanity, driver);
						break;
					}
				}
				Utilities.HoverandClick("//*[contains(@id,'StockoutQADetail')]//button[text()='Reject']", driver);
				Utilities.isElementGone(LoadingIcon, 20, driver);
				if (isGRN == true) {
					Utilities.clickButton("Submit", 500, driver);
				}
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("Save", 1000, driver);
				Utilities.isElementGone(LoadingIcon, 20, driver);
				Utilities.clickButton("OK", 500, driver);
				Utilities.click_element(pro.getProperty("QAApprovalDetailsTabCloseBtn"), driver);
				Thread.sleep(250);
			}

			if (SerialPrd > 0) {
				prodName = driver
						.findElement(By.xpath("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
								+ SerialPrd + "]/table/tbody/tr/td[" + indOfprid + "]"))
						.getText();
				Utilities.HoverandClick("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
						+ SerialPrd + "]/table/tbody/tr/td[" + indOfDetails + "]", driver);
				Thread.sleep(250);

				for (int k = 1; k <= Integer.parseInt(rejectedQuanity); k++) {
					Utilities.HoverandClick(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div["
									+ k + "]/table/tbody//*[@class='x-grid3-row-checker']",
							driver);
				}

				for (int k = 1; k <= headerOfdetails; k++) {
					Quantity = driver.findElement(By.xpath(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td[" + k + "]/div"))
							.getText();
					if (Quantity.equalsIgnoreCase("Serial")) {
						for (int sr = 1; sr <= Integer.parseInt(rejectedQuanity); sr++) {
							serialNo = driver.findElement(By
									.xpath("//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div["
											+ sr + "]/table/tbody/tr/td[" + k + "]/div"))
									.getText();
							stb.setLength(0);
							stb.append(prodName).append(",").append("Batch").append(",").append(serialNo);
							QAApprovalList.add(stb.toString());
						}
						break;
					}
				}
				Utilities.HoverandClick("//*[contains(@id,'StockoutQADetail')]//button[text()='Reject']", driver);
				Utilities.isElementGone(LoadingIcon, 20, driver);
				if (isGRN == true) {
					Utilities.clickButton("Submit", 500, driver);
				}
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("Save", 1000, driver);
				Utilities.isElementGone(LoadingIcon, 20, driver);
				Utilities.clickButton("OK", 500, driver);
				Utilities.click_element(pro.getProperty("QAApprovalDetailsTabCloseBtn"), driver);
				Thread.sleep(250);
			}

			if (BatchSer > 0) {
				prodName = driver
						.findElement(By.xpath("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
								+ BatchSer + "]/table/tbody/tr/td[" + indOfprid + "]"))
						.getText();
				Utilities.HoverandClick("//*[text()='Transaction No']/ancestor::div[3]/following::div[1]/div/div["
						+ BatchSer + "]/table/tbody/tr/td[" + indOfDetails + "]", driver);
				Thread.sleep(250);

				for (int k = 1; k <= Integer.parseInt(rejectedQuanity); k++) {
					Utilities.HoverandClick(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div["
									+ k + "]/table/tbody//*[@class='x-grid3-row-checker']",
							driver);
				}

				for (int k = 1; k <= headerOfdetails; k++) {
					Quantity = driver.findElement(By.xpath(
							"//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::tr/td[" + k + "]/div"))
							.getText();
					if (Quantity.equalsIgnoreCase("Serial")) {
						for (int sr = 1; sr <= Integer.parseInt(rejectedQuanity); sr++) {
							serialNo = driver.findElement(By
									.xpath("//*[contains(@id,'StockoutQADetail')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div[1]/div["
											+ sr + "]/table/tbody/tr/td[" + k + "]/div"))
									.getText();
							stb.setLength(0);
							stb.append(prodName).append(",").append("BatchSerial").append(",").append(serialNo);
							QAApprovalList.add(stb.toString());
						}
						break;
					}
				}
				Utilities.HoverandClick("//*[contains(@id,'StockoutQADetail')]//button[text()='Reject']", driver);
				Utilities.isElementGone(LoadingIcon, 20, driver);
				if (isGRN == true) {
					Utilities.clickButton("Submit", 500, driver);
				}
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("Save", 1000, driver);
				Utilities.isElementGone(LoadingIcon, 20, driver);
				Utilities.clickButton("OK", 500, driver);
				Utilities.click_element(pro.getProperty("QAApprovalDetailsTabCloseBtn"), driver);
				Thread.sleep(250);
			}
			String[] array = QAApprovalList.toArray(new String[0]);
			Utilities.writeSerialsToFile(
					documentid + "[QA_REJECT_serials]" + Utilities.currentDate("dd-MM-yyyy") + ".txt", array);

			Utilities.click_element(pro.getProperty("QAApprovalCloseBtn"), driver);
			System.out.println("************ QA - Reject test case for [" + documentid + "] is PASS ************");
		} catch (Exception Ex) {
			System.out.println(
					"!!!!-WARNING-!!!!!!!!! QA - Reject test case for [" + documentid + "] is FAIL !!!!!!!!!!!!!");
			Utilities.handleError(Ex, driver);
		}

	}
}