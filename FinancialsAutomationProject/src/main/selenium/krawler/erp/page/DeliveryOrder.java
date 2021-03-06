package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import krawler.erp.utils.SikulliUtil;

public class DeliveryOrder {

	public static void create_deliveryOrder(String documentid, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "DO" + documentid;
			Properties DeliveryOrder = Utilities.fetchProValue("OR_DeliveryOrder.properties");

			Thread.sleep(3000);
			// WebDriverWait wait = new WebDriverWait(driver, 30);

			// clicked on main icon
			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(DeliveryOrder.getProperty("DeliveryOrderIcon"))));
			driver.findElement(By.xpath(DeliveryOrder.getProperty("DeliveryOrderIcon"))).click();
			Thread.sleep(10000);// pro

			// enter source name
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(DeliveryOrder.getProperty("CustomerId"))));
			WebElement customer = driver.findElement(By.xpath(DeliveryOrder.getProperty("CustomerId")));
			customer.clear();
			customer.sendKeys(customerid);
			Thread.sleep(3000);// pro
			customer.sendKeys(Keys.ENTER);

			// sequence format document no.
			WebElement squence = driver.findElement(By.xpath(DeliveryOrder.getProperty("SequenceFormat")));
			squence.clear();
			squence.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// sequence format document no.
			WebElement term = driver.findElement(By.xpath(DeliveryOrder.getProperty("CreditTerm")));
			term.clear();
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(DeliveryOrder.getProperty("DeliveryOrderNo"))));
			driver.findElement(By.xpath(DeliveryOrder.getProperty("DeliveryOrderNo"))).sendKeys(documentid);

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(DeliveryOrder.getProperty("AddProduct"))));
			driver.findElement(By.xpath(DeliveryOrder.getProperty("AddProduct"))).click();

			// search product id
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(DeliveryOrder.getProperty("SearchProduct"))));
			driver.findElement(By.xpath(DeliveryOrder.getProperty("SearchProduct"))).sendKeys(productid);
			Thread.sleep(2000);// pro

			// select check box
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(DeliveryOrder.getProperty("SelectCheckBox"))));
			driver.findElement(By.xpath(DeliveryOrder.getProperty("SelectCheckBox"))).click();
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(DeliveryOrder.getProperty("ClickOnAdd"))));
			driver.findElement(By.xpath(DeliveryOrder.getProperty("ClickOnAdd"))).click();
			Thread.sleep(3000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//*[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//*[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Actual Quantity")) {
					driver.findElement(By
							.xpath("//*[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					Thread.sleep(2000);
					Utilities.sendText("1");
					break;
				}
			}
			driver.findElement(By.xpath(DeliveryOrder.getProperty("Memo"))).click();

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//*[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//*[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Delivered Quantity")) {
					driver.findElement(By
							.xpath("//*[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					Thread.sleep(2000);
					Utilities.sendText("1");

					// driver.findElement(By.xpath("//*[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["+(i+1)+"]/div")).click();

					break;
				}
			}

			driver.findElement(By.xpath(DeliveryOrder.getProperty("Memo"))).click();

			// ------------------------------------------------------------------------------------------------------------------------------------------------

			// Click on Quantity at line level for Batch Serial Number details.
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//*[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td/div/div[@class='pwnd serialNo-gridrow']")));
			WebElement deliveredquantity1 = driver.findElement(By.xpath(
					"//*[@id='deliveryorderbillingproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td/div/div[@class='pwnd serialNo-gridrow']"));
			deliveredquantity1.click();
			Thread.sleep(5000);// pro
			// div[@class='x-window']/div[2]/div/div/div/div/div[2]/div/div/div/div/div/div/div/div[2]/div/div/table/tbody/tr/td[14]/div

			WebElement qtyInsideLoc = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[contains(@id,'centerpanwtf')]/div/div/div/div/div/div/div/div[2]/div/div[1]/table/tbody/tr/td[14]")));
			qtyInsideLoc.click();
			Thread.sleep(1000);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_A);
			Thread.sleep(1000);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_A);
			Thread.sleep(1000);
			Utilities.sendText("1");
			Thread.sleep(2000);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(DeliveryOrder.getProperty("SubmitButton1"))));
			driver.findElement(By.xpath(DeliveryOrder.getProperty("SubmitButton1"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(DeliveryOrder.getProperty("ClickOnSave"))));
			driver.findElement(By.xpath(DeliveryOrder.getProperty("ClickOnSave"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 1000, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(1000);

			// Email code
			String subjectLine = "Delivery Order" + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);
			Thread.sleep(1000);

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(DeliveryOrder.getProperty("CloseFormDO"))));
			driver.findElement(By.xpath(DeliveryOrder.getProperty("CloseFormDO"))).click();
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}

	public static void verify_deliveryOrder(String documentid, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "DO" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DeliveryOrderReport"))));
			driver.findElement(By.xpath(pro.getProperty("DeliveryOrderReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath(".//*[@id='gridmsg52DeliveryOrderListEntry']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg52DeliveryOrderListEntry']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (header.equals("Delivery Order No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath("//div[@id='gridmsg52DeliveryOrderListEntry']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (header.equals("Customer")) {
					assertEquals(customerid + "Name",
							driver.findElement(By
									.xpath("//div[@id='gridmsg52DeliveryOrderListEntry']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

			}

			System.out.println("Delivery Order Successfully verified");
			String closeReportPage = "//li[@id='as__DeliveryOrderListEntry']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);
		}

		catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	public static void EmailCopyEditDelete_deliveryOrder(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "DO" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DeliveryOrderReport"))));
			driver.findElement(By.xpath(pro.getProperty("DeliveryOrderReport"))).click();
			Thread.sleep(5000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(3000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			// Report Email code
			String subjectLine = "Delivery Order - testsmoke - " + documentid;
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

			WebElement copyDeliveryOrderButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyDeliveryOrderButton"))));
			copyDeliveryOrderButton.click();
			Thread.sleep(2000);

			String CopyDeliveryOrderID = "Copy" + documentid;

			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyDeliveryOrderId"))));
			driver.findElement(By.xpath(pro.getProperty("copyDeliveryOrderId"))).sendKeys(CopyDeliveryOrderID);

			Thread.sleep(2000);
			Robot r = new Robot();

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("saveCopiedDeliveryOrderId"))));

			driver.findElement(By.xpath(pro.getProperty("saveCopiedDeliveryOrderId"))).click();
			Thread.sleep(1000);
			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 500, driver);
			Thread.sleep(1500);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("CopyDeliveryOrderClose"))));
				driver.findElement(By.xpath(pro.getProperty("CopyDeliveryOrderClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			Thread.sleep(500);
			search.sendKeys(CopyDeliveryOrderID);
			Thread.sleep(500);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement editDeliveryOrderButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditDeliveryOrderButton"))));
			editDeliveryOrderButton.click();
			Thread.sleep(3000);

			WebElement DeliveryOrderMemo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DeliveryOrderMemo"))));
			DeliveryOrderMemo.sendKeys("Update Delivery Order Memo");

			Thread.sleep(2000);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DeliveryOrdereEditSave"))));
			driver.findElement(By.xpath(pro.getProperty("DeliveryOrdereEditSave"))).click();
			Thread.sleep(3000);

			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1000, driver);
			Thread.sleep(1500);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("EditDeliveryOrderClose"))));
				driver.findElement(By.xpath(pro.getProperty("EditDeliveryOrderClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			Thread.sleep(500);
			search.sendKeys(CopyDeliveryOrderID);
			Thread.sleep(500);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			try {
				Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
				Thread.sleep(3000);
				if (driver
						.findElement(By
								.xpath("//div[@id='DeliveryOrderListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
					System.out.println("Delivery Order " + CopyDeliveryOrderID + " is edited Successfully");
				} else {
					System.out.println("Delivery Order " + CopyDeliveryOrderID + " is not edited Successfully");
				}

			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);
			}

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteDeliveryOrderButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteDeliveryOrderButton"))).click();

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteDeliveryOrderPermButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteDeliveryOrderPermButton"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 1000, driver);

			Utilities.clickButton("OK", 0, driver);

			Thread.sleep(3000);

			try {
				search.clear();
				Thread.sleep(500);
				search.sendKeys(CopyDeliveryOrderID);
				Thread.sleep(500);
				search.sendKeys(Keys.TAB);
				Thread.sleep(5000);

				if (driver
						.findElement(By
								.xpath("//div[@id='DeliveryOrderListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("No results to display")) {
					System.out.println("Delivery Order " + CopyDeliveryOrderID + "  is Deleted Successfully");
				} else {
					System.out.println("Delivery Order " + CopyDeliveryOrderID + "  is not Deleted Successfully");
				}
			} catch (Exception exp) {
				System.out.println("Delivery Order " + CopyDeliveryOrderID + " is NOT  deleted");
			}
			String closeReportPage = "//li[@id='as__DeliveryOrderListEntry']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}
	}

	public static void setting_QaFlow(String onoroff, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");
			Utilities.refresh();
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			WebElement element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("PreferencesExpander")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("SystemControlsLink")));
				}
			});
			element.click();
			Thread.sleep(10000);

			if (onoroff.equalsIgnoreCase("off")) {
				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg
								.findElement(By.xpath(pro.getProperty("DeliveryOrderQAApprovalFlowCheckBox")));
					}
				});
				if (element.isSelected()) {
					element.click();
					System.out.println("QA Approval Flow in Delivery Order is Deactivated");
				} else {
					System.out.println("QA Approval Flow in Delivery Order is Already Deactivated");
				}
				element = wait.until(new Function<WebDriver, WebElement>() {

					// apply method- which accept webdriver as input
					// @Override
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg.findElement(By.xpath(pro.getProperty("saveAccountPreferencesBtn")));
					}
				});
				element.click();
				Thread.sleep(2000);
				element = wait.until(new Function<WebDriver, WebElement>() {

					// apply method- which accept webdriver as input
					// @Override
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg.findElement(By.xpath(pro.getProperty("applyNewsettingsBtn")));
					}
				});
				element.click();
				Thread.sleep(2000);
				element = wait.until(new Function<WebDriver, WebElement>() {

					// apply method- which accept webdriver as input
					// @Override
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg.findElement(By.xpath(pro.getProperty("AccountPreferencesSavedOKBtn")));
					}
				});
				Thread.sleep(2000);
				element.click();
				Thread.sleep(3000);

			}

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// *************************** Export DeliveryOrder
	// ***************************************
	public static void Export_DeliveryOrder(WebDriver driver) throws InterruptedException, AWTException, IOException {
		Properties pro2 = Utilities.fetchProValue("OR_DeliveryOrder.properties");
		String reportIcon = pro2.getProperty("DeliveryOrderReport");
		String waitForQuickSearch = pro2.getProperty("DocumentCheckBox");
		String closeReportPage = "//li[@id='as__DeliveryOrderListEntry']/a[1]";
		String ModuleName = "Delivery Order ";

		// ExportFunction.Export_TodayDate(BtnName2, reportIcon,
		// waitForQuickSearch, closeReportPage, ModuleName, driver);
		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);
			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");

			String ExportButtonName = "Export List";
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(reportIcon)));
			element.click();
			Thread.sleep(1000);

			Utilities.clickCheckBox(waitForQuickSearch, "uncheck", driver);

			// select from date
			Utilities.HoverandClick(pro.getProperty("fromIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
			Thread.sleep(1000);

			// select To date
			Utilities.HoverandClick(pro.getProperty("toIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
			Thread.sleep(1000);

			Utilities.clickButton("Fetch", 500, driver);
			Thread.sleep(1000);
			try {
				Utilities.clickCheckBox(waitForQuickSearch, "check", driver);

				// ****** CSV FILE *****
				Utilities.clickButton(ExportButtonName, 500, driver);
				Utilities.HoverandClick(pro.getProperty("ExportToCSV"), driver);
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "CSVtype");
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
				System.out.println("* * * * * * EXPORT for [.CSV] completed successfully * * * * * * *");
				Thread.sleep(1500);

				// PDF
				Utilities.clickButton(ExportButtonName, 500, driver);
				String parentWindow = driver.getWindowHandle();
				Utilities.HoverandClick(pro.getProperty("ExportToPDF"), driver);
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("pdfTemplate"))));
				element.click();
				Thread.sleep(1000);
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "DOpdf");
				Thread.sleep(1500);
				Set s = driver.getWindowHandles(); // this method will gives you
													// the handles of all opened
													// windows
				Iterator ite = s.iterator();
				while (ite.hasNext()) {
					String childWindow = ite.next().toString();
					if (!childWindow.contains(parentWindow)) {
						driver.switchTo().window(childWindow);
						Thread.sleep(1000);
						System.out.println("* * * * * * EXPORT for [PDF] completed successfully * * * * * * *");
						driver.close();
						driver.switchTo().window(parentWindow);
						Thread.sleep(1000);
					}
				}

				// Excel in Details
				Utilities.clickButton(ExportButtonName, 500, driver);
				Actions action = new Actions(driver);
				WebElement hoverElement = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ExportToExcel"))));
				action.moveToElement(hoverElement).build().perform();
				Thread.sleep(1000);
				Utilities.HoverandClick(pro.getProperty("ExcelInDetail"), driver);
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "XLStype");
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
				System.out.println("* * * * * * EXPORT for [.Excel in Detail ] completed successfully * * * * * * *");
				Thread.sleep(1500);

				// Excel Summary
				Utilities.clickButton(ExportButtonName, 500, driver);
				hoverElement = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ExportToExcel"))));
				action.moveToElement(hoverElement).build().perform();
				Thread.sleep(1000);
				Utilities.HoverandClick(pro.getProperty("ExcelInSummary"), driver);
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "XLStype");
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
				System.out.println("* * * * * * EXPORT for [.Excel Summary ] completed successfully * * * * * * *");
				Thread.sleep(1500);

				// Export to HTML
				Utilities.clickButton(ExportButtonName, 500, driver);
				parentWindow = driver.getWindowHandle();
				Utilities.HoverandClick(pro.getProperty("ExportToHtml"), driver);
				Utilities.clickButton("Print", 1000, driver);
				s = driver.getWindowHandles(); // this method will gives you the
												// handles of all opened windows
				ite = s.iterator();
				while (ite.hasNext()) {
					String childWindow = ite.next().toString();
					if (!childWindow.contains(parentWindow)) {
						driver.switchTo().window(childWindow);
						driver.manage().window().maximize();
						Thread.sleep(1000);
						System.out.println("* * * * * * EXPORT with [Print] completed successfully * * * * * * *");
						driver.close();
						driver.switchTo().window(parentWindow);
						Thread.sleep(1500);
					}
				}
				// Exported

				Utilities.HoverandClick(closeReportPage, driver);
				System.out.println(
						"* * * * * * EXPORT for module [" + ModuleName + "] completed successfully * * * * * * *");
			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				Utilities.handleError(noData, driver);
			}
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [" + ModuleName + "] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}

	}

}
