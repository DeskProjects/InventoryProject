package krawler.erp.FixedAsset;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;
import krawler.erp.utils.SikulliUtil;

public class AssetDisposalInvoice {
	public void Create_AssetDI(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			String documentid1 = "ADI" + documentid;

			// Asset Disposal Invoice Utilities
			Properties AssetDI = Utilities.fetchProValue("OR_AssetDisposalInvoice.properties");
			Thread.sleep(2000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Disposal Invoice
			WebElement ADIbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("ClickOnADI"))));
			ADIbutton.click();
			Thread.sleep(3000);

			// Enter Asset Disposal Invoice Vendor
			WebElement Vendor = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("Customer"))));
			Thread.sleep(3000);
			Vendor.clear();
			Thread.sleep(3000);
			Vendor.sendKeys("Amol1");
			Vendor.click();
			Thread.sleep(3000);
			Vendor.sendKeys(Keys.ENTER);

			// Click On NA Sequence Format

			WebElement ADISequence = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("SequenceFormat"))));
			Thread.sleep(3000);
			ADISequence.clear();
			Thread.sleep(3000);
			ADISequence.sendKeys("NA");
			ADISequence.click();
			Thread.sleep(3000);
			ADISequence.sendKeys(Keys.ENTER);

			// Enter Asset ADI Number
			WebElement ADINo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("ADINo"))));
			Thread.sleep(3000);
			ADINo.sendKeys(documentid1);

			// Enter Asset ADI Memo
			WebElement ADIMemo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("Memo"))));
			ADIMemo.click();
			Thread.sleep(500);
			ADIMemo.sendKeys("Asset Memo");
			Thread.sleep(2000);

			// Enter Asset Group Detail First
			Utilities.HoverandClick(AssetDI.getProperty("AssetGroup1"), driver);
			Utilities.enterTextandSelect("AGID44", "//*[@name='productname']", driver);
			ADIMemo.click();
			Thread.sleep(2000);

			// Enter Asset Unit Price First
			Utilities.HoverandClick(AssetDI.getProperty("UnitPrice1"), driver);
			Utilities.enter_LinelabelAmount("2200", driver);
			ADIMemo.click();
			Thread.sleep(1000);

			// Enter Asset Quantity First
			Utilities.HoverandClick(AssetDI.getProperty("Quantity1"), driver);
			Utilities.enter_LinelabelAmount("1", driver);
			ADIMemo.click();
			Thread.sleep(2000);

			// Enter Asset ID First

			// Utilities.clickAndEnterText(documentid+"AAIId100",
			// AssetDI.getProperty("AssetID"), driver);
			Utilities.clickAndEnterText("3543555", AssetDI.getProperty("AssetID1"), driver);
			Thread.sleep(3000);

			Utilities.Enter();
			Thread.sleep(500);

			Robot r2 = new Robot();
			r2.keyPress(KeyEvent.VK_TAB);
			r2.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			// Click On Save button Asset Detail First
			WebElement SaveI1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("SaveDetail"))));
			SaveI1.click();
			Thread.sleep(1000);

			/*
			 * // Enter Asset Group Second
			 * Utilities.HoverandClick(AssetDI.getProperty("AssetGroup2"),
			 * driver); Utilities.enterTextandSelect("AGID44",
			 * "//*[@name='productname']", driver); ADIMemo.click();
			 * Thread.sleep(1000);
			 * 
			 * // Enter Asset Unit Price Second
			 * Utilities.HoverandClick(AssetDI.getProperty("UnitPrice2"),
			 * driver); Utilities.enter_LinelabelAmount("200", driver);
			 * ADIMemo.click(); Thread.sleep(1000);
			 * 
			 * // Enter Asset Quantity Second
			 * Utilities.HoverandClick(AssetDI.getProperty("Quantity2"),
			 * driver); Utilities.enter_LinelabelAmount("1", driver);
			 * ADIMemo.click(); Thread.sleep(2000);
			 * 
			 * // Enter Asset ID Second
			 * 
			 * //Utilities.clickAndEnterText(documentid+"ADIId200",
			 * AssetDI.getProperty("AssetID"), driver);
			 * 
			 * 
			 * Utilities.clickAndEnterText("3543555",
			 * AssetDI.getProperty("AssetID1"), driver); Thread.sleep(3000);
			 * 
			 * Utilities.Enter(); Thread.sleep(500);
			 * 
			 * Robot r3 = new Robot(); r3 .keyPress(KeyEvent.VK_TAB); r3
			 * .keyRelease(KeyEvent.VK_TAB); Thread.sleep(2000);
			 * 
			 * r2 .keyPress(KeyEvent.VK_TAB); r2 .keyRelease(KeyEvent.VK_TAB);
			 * Thread.sleep(1000);
			 * 
			 * 
			 * r2 .keyPress(KeyEvent.VK_TAB); r2 .keyRelease(KeyEvent.VK_TAB);
			 * Thread.sleep(1000);
			 * 
			 * r2 .keyPress(KeyEvent.VK_TAB); r2 .keyRelease(KeyEvent.VK_TAB);
			 * Thread.sleep(1000);
			 * 
			 * r2 .keyPress(KeyEvent.VK_TAB); r2 .keyRelease(KeyEvent.VK_TAB);
			 * Thread.sleep(1000);
			 * 
			 * r2 .keyPress(KeyEvent.VK_TAB); r2 .keyRelease(KeyEvent.VK_TAB);
			 * Thread.sleep(1000);
			 * 
			 * r2 .keyPress(KeyEvent.VK_TAB); r2 .keyRelease(KeyEvent.VK_TAB);
			 * r2.keyPress(KeyEvent.VK_ENTER); r2.keyRelease(KeyEvent.VK_ENTER);
			 * Utilities.sendText("200"); Thread.sleep(2000);
			 * 
			 * // Click On Save button Asset Detail First WebElement SaveI2 =
			 * AGwait.until(ExpectedConditions.elementToBeClickable(
			 * By.xpath(AssetDI.getProperty("SaveDetail")))); SaveI2.click();
			 * Thread.sleep(2000);
			 */

			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 2000, driver);
			// Utilities.clickButton("OK", 2000, driver);

			System.out.println("Asset Disposal Invoice Save Successfully");

			// Click On Asset Report Cross Symbol

			WebElement AGCross1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("CrossSymbol"))));
			AGCross1.click();
			Thread.sleep(2000);

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// --------------------------------------------------------- Edit Asset
	// Disposal Invoice ---------------------------------------------
	public void Edit_AssetDI(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			String documentid1 = "ADI" + documentid;
			// String AssetId= "AId"+Assetid;

			// Asset Acquired Invoice Utilities
			Properties AssetDI = Utilities.fetchProValue("OR_AssetDisposalInvoice.properties");
			Thread.sleep(500);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Acquired Invoice
			WebElement AAIRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("ClickOnADIR"))));
			AAIRbutton.click();
			Thread.sleep(1000);

			// Click On Fetch button
			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("Fetch"))));
			Search.click();
			Thread.sleep(1000);

			// Enter Quick Search
			WebElement Search1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("QuickSearch"))));
			Search1.sendKeys(documentid1);
			Thread.sleep(2000);

			// Click on records
			WebElement AAICheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("CheckBox"))));
			AAICheck.click();
			Thread.sleep(2000);

			// Click On Asset Acquired Invoice
			WebElement AGRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("Edit"))));
			AGRbutton.click();
			Thread.sleep(3000);

			// Updated Asset Acquired Invoice Memo
			WebElement AAIMemo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("Memo"))));
			AAIMemo.click();
			AAIMemo.clear();
			Thread.sleep(2000);

			AAIMemo.sendKeys("Updated Asset Memo");
			Thread.sleep(2000);

			// Enter Asset Group First
			Utilities.HoverandClick(AssetDI.getProperty("AssetGroup2"), driver);
			Utilities.enterTextandSelect("AGID44", "//*[@name='productname']", driver);
			AAIMemo.click();
			Thread.sleep(2000);

			// Enter Asset Unit Price Third
			Utilities.HoverandClick(AssetDI.getProperty("UnitPrice2"), driver);
			Utilities.enter_LinelabelAmount("1", driver);
			AAIMemo.click();
			Thread.sleep(1000);

			// Enter Asset Quantity Third
			Utilities.HoverandClick(AssetDI.getProperty("Quantity2"), driver);
			Utilities.enter_LinelabelAmount("1", driver);
			AAIMemo.click();
			Thread.sleep(2000);

			Utilities.clickAndEnterText("54466", AssetDI.getProperty("AssetID1"), driver);
			Thread.sleep(3000);

			Utilities.Enter();
			Thread.sleep(500);

			Robot r2 = new Robot();
			r2.keyPress(KeyEvent.VK_TAB);
			r2.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			// Click On Save button Asset Detail
			WebElement SaveI = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("SaveDetail"))));
			SaveI.click();

			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			// Utilities.clickButton("OK", 1000, driver);

			System.out.println("Edited Asset Acquired Invoice Save Successfully");

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// --------------------------------------------------------- Delete Asset
	// Disposal Invoice ---------------------------------------------

	public void Delete_AssetDI(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {
			String documentid1 = "ADI" + documentid;
			// String AssetId= "AId"+Assetid;

			// Asset Purchase Order Utilities
			Properties AssetAI = Utilities.fetchProValue("OR_AssetDisposalInvoice.properties");
			Thread.sleep(3000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Acquired Invoice
			WebElement AAIRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("ClickOnADIR"))));
			AAIRbutton.click();
			Thread.sleep(1000);

			// Click On Fetch button
			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("Fetch"))));
			Search.click();
			Thread.sleep(1000);

			// Enter Quick Search
			WebElement Search1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("QuickSearch"))));
			Search1.sendKeys("35435");
			Thread.sleep(2000);

			// Click on records
			WebElement AAICheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("CheckBox"))));
			AAICheck.click();
			Thread.sleep(2000);

			// Click on Delete button
			WebElement AAIEdit = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("Delete"))));
			AAIEdit.click();
			Thread.sleep(2000);

			Actions Action = new Actions(driver);
			WebElement hoverElementandClick1 = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("DeleteP"))));

			Action.moveToElement(hoverElementandClick1).build().perform();
			Thread.sleep(5000);
			hoverElementandClick1.click();

			// Click On Yes Button
			WebElement AAIDYes = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("Yes"))));
			AAIDYes.click();
			Thread.sleep(2000);

			// Click On Ok Button
			WebElement AAIDOk = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("Ok"))));
			AAIDOk.click();
			Thread.sleep(3000);

			System.out.println("Asset Disposal Invoice  Deleted Successfully");
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ***************Export CSV,Excel, PDF and Print Files
	// **************************

	public void Export_AssetDI(String buttonName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);

			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String ExportButtonName = "Export List";
			String reportIcon = "//*[text()='Asset Disposal Invoice List ']";
			String waitForQuickSearch = "//button[text()='Fetch']";
			String closeReportPage = "//li[@id='as__FixedAssetInvoiceListEntry']/a[1]";
			String Export = "Export";

			if (buttonName.equalsIgnoreCase("Export List")) {
				ExportButtonName = "Export List";
			} else if (buttonName.equalsIgnoreCase("Export")) {
				ExportButtonName = "Export ";
			}

			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(reportIcon)));
			element.click();
			Thread.sleep(1000);

			Utilities.clickCheckBox(waitForQuickSearch, "uncheck", driver);

			// Select From Date

			Utilities.HoverandClick(pro.getProperty("fromIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
			Thread.sleep(1000);

			// Select To Date

			Utilities.HoverandClick(pro.getProperty("toIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
			Thread.sleep(1000);

			Utilities.clickButton("Fetch", 500, driver);

			try {

				// WebElement resultFound=new WebDriverWait(driver,
				// 30).until(ExpectedConditions.elementToBeClickable(By.xpath(waitForQuickSearch)));
				Thread.sleep(1000);
				Utilities.clickCheckBox(waitForQuickSearch, "check", driver);

				// **************** CSV File **********

				Utilities.clickButton(ExportButtonName, 1000, driver);
				Utilities.HoverandClick(pro.getProperty("ExportToCSV"), driver);
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "CSVtype");
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
				System.out.println("* * * * * * EXPORT for [CSV] completed successfully * * * * * * *");
				Thread.sleep(1500);

				// **************** Export PDF Files *****************

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

				// ***************************** Export Excel Files
				// ***************************

				Utilities.clickButton(ExportButtonName, 500, driver);
				Utilities.HoverandClick(pro.getProperty("ExportToExcel"), driver);
				Thread.sleep(3500);
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "XLStype");
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
				System.out.println("* * * * * * EXPORT for [Excel] completed successfully * * * * * * *");
				Thread.sleep(1500);

				// ******************************Print To HTML
				// *****************************

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
				System.out.println(
						"* * * * * * EXPORT for module [Asset Disposal Invoice] completed successfully * * * * * * *");
			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				noData.printStackTrace();
			}
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [Asset Disposal Invoice] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}
	}

	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;

	}
}
