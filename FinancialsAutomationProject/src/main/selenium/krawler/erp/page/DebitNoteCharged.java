package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.utils.SikulliUtil;

public class DebitNoteCharged {

	// * * * * * * * * * * Debit Note UnderCharge * * * * * * *
	public static void create_debitNoteUnderCharge(String documentid, String productid, String customerid,
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
			String drpDwnArrow = "//input[@name='ordernumber']/following-sibling::span/img[2]";
			String itemInputBoxLocator = "//input[@name='ordernumber']/following-sibling::input";
			String itemName = "SI" + documentid;
			String rslTable = "//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div/table/tbody/tr/td[1]";

			Utilities.selectItemfromDropDown(drpDwnArrow, itemInputBoxLocator, itemName, rslTable, driver);
			Utilities.click_element(pro.getProperty("memo"), driver);
			Thread.sleep(1000);

			List<WebElement> headers = driver
					.findElements(By.xpath("//div[@class='x-grid3-header']/div/div/table/thead/tr/td/div"));
			int headSize = headers.size();
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
				Utilities.HoverandClick(
						"*//div[@class='x-grid3-scroller']/div/div[" + i + "]/table/tbody/tr/td[" + amtIndex + "]/div",
						driver);
				Utilities.enter_LinelabelAmount(Quantity, driver);
				// to move focus
				Utilities.click_element(pro.getProperty("memo"), driver);
			}

			// to scroll end
			Utilities.HoverandClick(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table//*[contains(@class,'pwnd delete-gridrow')]",
					driver);
			Utilities.clickButton("No", 10, driver);
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
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
			}

			Utilities.clickButton("Save", 800, driver);
			Utilities.clickButton("Yes", 800, driver);
			Utilities.clickButton("OK", 800, driver);

			WebElement selectExit = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//li[@id='as__debitnoteagaintcustomerformalaysia']/a[1]")));
			selectExit.click();

			System.out.println(
					"* * * Debit Note Undercharged created successfully for : [" + documentid + "] * * * * * ");
		}

		catch (Exception EX) {
			System.out.println("* * * Debit Note Undercharged FAILED to create for : [" + documentid + "] * * * * * ");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************* Overcharged
	// ********************************************

	// * * * * * * * * * * Debit Note OverCharge * * * * * * *
	public static void create_debitNoteOverCharge(String documentid, String productid, String vendorid, String Quantity,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
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
			String drpDwnArrow = "//input[@name='ordernumber']/following-sibling::span/img[2]";
			String itemInputBoxLocator = "//input[@name='ordernumber']/following-sibling::input";
			String itemName = "PurInvo" + documentid;
			String rslTable = "//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div/table/tbody/tr/td[1]";

			Utilities.selectItemfromDropDown(drpDwnArrow, itemInputBoxLocator, itemName, rslTable, driver);
			Utilities.click_element(pro.getProperty("memo"), driver);
			Thread.sleep(1000);

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
				Utilities.HoverandClick(
						"*//div[@class='x-grid3-scroller']/div/div[" + i + "]/table/tbody/tr/td[" + amtIndex + "]/div",
						driver);
				Utilities.enter_LinelabelAmount(Quantity, driver);
				// to move focus
				Utilities.click_element(pro.getProperty("memo"), driver);
			}

			Utilities.HoverandClick(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table//*[contains(@class,'pwnd delete-gridrow')]",
					driver);
			Utilities.clickButton("No", 10, driver);
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
				Utilities.click_element(pro.getProperty("memo"), driver);
			}

			Utilities.clickButton("Save", 800, driver);
			Utilities.clickButton("Yes", 800, driver);
			Utilities.clickButton("OK", 800, driver);

			WebElement selectExit = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='as__debitnoteForOvercharge']/a[1]")));
			selectExit.click();
			System.out
					.println("* * * Debit Note Overcharged created successfully for : [" + documentid + "] * * * * * ");
		} catch (Exception EX) {
			System.out.println("* * * Debit Note Overcharged FAILED to Create for : [" + documentid + "] * * * * * ");
			Utilities.handleError(EX, driver);
		}
	}

	// * * * * * * * * * * * Verification * * * * * * * * *

	public static void verify_DebitNoteCharged(String documentid, String underORover, String accName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
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
			element.sendKeys(documentid);
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

				assertEquals(documentid,
						driver.findElement(By
								.xpath("//div[text()='Debit Note No']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ customerIdIndex + "]/div"))
								.getText());

				assertEquals(accName + "Name",
						driver.findElement(By
								.xpath("//div[text()='Debit Note No']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ nameIndex + "]/div"))
								.getText());
			}

			System.out.println("* * * * * Verified DebitNote for [" + underORover + "] with document id : ["
					+ documentid + "] with acc Name [" + accName + "Name" + "] * * * *");
			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);
		}

		catch (Exception EX) {
			System.out.println("* * * * * Verification FAILED DebitNote for [" + underORover + "] with document id : "
					+ documentid);
			Utilities.handleError(EX, driver);
		}
	}

	// * * * * * * * EXPORT Debit Note * * * * * *

	public static void Export_DebitNote(String buttonName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);
			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String reportIcon = "//area[@onclick='callDebitNoteDetails()']";
			String waitForQuickSearch = "//div[text()='Debit Note No']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr[1]/td[1]";
			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";

			String ExportButtonName = null;

			if (buttonName.equalsIgnoreCase("Export List")) {
				ExportButtonName = "Export List";
			} else if (buttonName.equalsIgnoreCase("Export")) {
				ExportButtonName = "Export ";
			}

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

			try {
				// WebElement resultFound=new WebDriverWait(driver,
				// 30).until(ExpectedConditions.elementToBeClickable(By.xpath(waitForQuickSearch)));
				Thread.sleep(1000);
				Utilities.clickCheckBox(waitForQuickSearch, "check", driver);

				// ***** CSV File
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
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "PDFtype");
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

				Utilities.HoverandClick(closeReportPage, driver);
				System.out.println("* * * * * * EXPORT for module [Debit Note] completed successfully * * * * * * *");
			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				noData.printStackTrace();
			}
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [Debit Note] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}

	}

	// get button path for Sikulli
	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;

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

}
