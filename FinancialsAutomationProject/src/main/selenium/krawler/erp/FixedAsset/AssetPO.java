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

public class AssetPO {
	public void Create_AssetPO(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			String documentid1 = "APO" + documentid;

			// Asset Purchase Order Utilities
			Properties AssetPO = Utilities.fetchProValue("OR_AssetPO.properties");
			Thread.sleep(2000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Purchase Order
			WebElement APObutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("ClickOnAPO"))));
			APObutton.click();
			Thread.sleep(3000);

			// Click On Create New button
			WebElement APObuttonC = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("CreateNew"))));
			APObuttonC.click();
			Thread.sleep(2000);

			// Enter Asset PO Vendor
			WebElement Vendor = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("Vendor"))));
			Thread.sleep(3000);
			Vendor.clear();
			Thread.sleep(3000);
			Vendor.sendKeys("Amol");
			Vendor.click();
			Thread.sleep(3000);
			Vendor.sendKeys(Keys.ENTER);

			// Click On NA Sequence Format

			WebElement APOSequence = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("SequenceFormat"))));
			Thread.sleep(3000);
			APOSequence.clear();
			Thread.sleep(3000);
			APOSequence.sendKeys("NA");
			APOSequence.click();
			Thread.sleep(3000);
			APOSequence.sendKeys(Keys.ENTER);

			// Enter Asset APO Number
			WebElement APONo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("APONo"))));
			Thread.sleep(3000);
			APONo.sendKeys(documentid1);

			// Enter Asset APO Memo
			WebElement APOMemo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("Memo"))));
			APOMemo.click();
			Thread.sleep(500);
			APOMemo.sendKeys("Asset Memo");
			Thread.sleep(2000);

			// Enter Asset Group Detail First
			Utilities.HoverandClick(AssetPO.getProperty("AssetGroup1"), driver);
			Utilities.enterTextandSelect("AGID44", "//*[@name='productname']", driver);
			APOMemo.click();
			Thread.sleep(2000);

			// Enter Asset Unit Price First
			Utilities.HoverandClick(AssetPO.getProperty("UnitPrice1"), driver);
			Utilities.enter_LinelabelAmount("100", driver);
			APOMemo.click();
			Thread.sleep(1000);

			// Enter Asset Quantity First
			Utilities.HoverandClick(AssetPO.getProperty("Quantity1"), driver);
			Utilities.enter_LinelabelAmount("1", driver);
			APOMemo.click();
			Thread.sleep(2000);

			// Enter Asset ID First
			Utilities.clickAndEnterText(documentid + "APOId100", AssetPO.getProperty("AssetID"), driver);
			Thread.sleep(1000);

			Robot r2 = new Robot();
			r2.keyPress(KeyEvent.VK_TAB);
			r2.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			Utilities.sendText("Automation Description1");
			Thread.sleep(2000);

			r2.keyPress(KeyEvent.VK_TAB);
			r2.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r2.keyPress(KeyEvent.VK_TAB);
			r2.keyRelease(KeyEvent.VK_TAB);
			Utilities.sendText("100");
			Thread.sleep(2000);

			r2.keyPress(KeyEvent.VK_TAB);
			r2.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r2.keyPress(KeyEvent.VK_TAB);
			r2.keyRelease(KeyEvent.VK_TAB);
			Utilities.sendText("100");
			Thread.sleep(1000);

			// Click On Save button Asset Detail First
			WebElement SaveI1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("SaveDetail"))));
			SaveI1.click();
			Thread.sleep(1000);

			// Enter Asset Group Second
			Utilities.HoverandClick(AssetPO.getProperty("AssetGroup2"), driver);
			Utilities.enterTextandSelect("AGID44", "//*[@name='productname']", driver);
			APOMemo.click();
			Thread.sleep(1000);

			// Enter Asset Unit Price Second
			Utilities.HoverandClick(AssetPO.getProperty("UnitPrice2"), driver);
			Utilities.enter_LinelabelAmount("200", driver);
			APOMemo.click();
			Thread.sleep(1000);

			// Enter Asset Quantity Second
			Utilities.HoverandClick(AssetPO.getProperty("Quantity2"), driver);
			Utilities.enter_LinelabelAmount("1", driver);
			APOMemo.click();
			Thread.sleep(2000);

			// Enter Asset ID Second
			Utilities.clickAndEnterText(documentid + "APOId200", AssetPO.getProperty("AssetID"), driver);
			Thread.sleep(500);

			Robot r3 = new Robot();
			r3.keyPress(KeyEvent.VK_TAB);
			r3.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			Utilities.sendText("Automation Description2");
			Thread.sleep(2000);

			r3.keyPress(KeyEvent.VK_TAB);
			r3.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r3.keyPress(KeyEvent.VK_TAB);
			r3.keyRelease(KeyEvent.VK_TAB);
			Utilities.sendText("200");
			Thread.sleep(2000);

			r3.keyPress(KeyEvent.VK_TAB);
			r3.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r3.keyPress(KeyEvent.VK_TAB);
			r3.keyRelease(KeyEvent.VK_TAB);
			Utilities.sendText("200");

			// Click On Save button Asset Detail First
			WebElement SaveI2 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("SaveDetail"))));
			SaveI2.click();

			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 2000, driver);

			System.out.println("Asset Purchase Order Save Successfully");

			/*
			 * // Click On Asset Report Cross Symbol WebElement AGCross1 =
			 * AGwait.until(ExpectedConditions.elementToBeClickable(
			 * By.xpath(AssetPO.getProperty("CrossSymbol")))); AGCross1.click();
			 * Thread.sleep(2000);
			 */

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}
	// --------------------------------------------------------- Edit Asset
	// Purchase Order ---------------------------------------------

	public void Edit_AssetPO(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			String documentid1 = "APO" + documentid;
			// String AssetId= "AId"+Assetid;

			// Asset Purchase Order Utilities
			Properties AssetPO = Utilities.fetchProValue("OR_AssetPO.properties");
			Thread.sleep(500);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Purchase Order
			WebElement AGRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("ClickOnAPO"))));
			AGRbutton.click();
			Thread.sleep(500);

			// Enter Quick Search
			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("QuickSearch"))));
			Search.sendKeys(documentid1);
			Thread.sleep(2000);

			// Click on records
			WebElement APOCheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("CheckBox"))));
			APOCheck.click();
			Thread.sleep(2000);

			// Click on Edit button
			WebElement APOEdit = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("Edit"))));
			APOEdit.click();
			Thread.sleep(2000);

			// Updated Asset Purchase Order Memo
			WebElement APOMemo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("Memo"))));
			APOMemo.click();
			APOMemo.clear();

			Thread.sleep(1000);
			APOMemo.sendKeys("Updated Asset Memo");
			Thread.sleep(1000);

			// Enter Asset Group First
			Utilities.HoverandClick(AssetPO.getProperty("AssetGroup3"), driver);
			Utilities.enterTextandSelect("AGID44", "//*[@name='productname']", driver);
			APOMemo.click();
			Thread.sleep(2000);

			// Enter Asset Unit Price Third
			Utilities.HoverandClick(AssetPO.getProperty("UnitPrice3"), driver);
			Utilities.enter_LinelabelAmount("300", driver);
			APOMemo.click();
			Thread.sleep(1000);

			// Enter Asset Quantity Third
			Utilities.HoverandClick("//*[text()='Quantity']//following::div[144]/ancestor::td", driver);
			Utilities.enter_LinelabelAmount("1", driver);
			APOMemo.click();
			Thread.sleep(2000);

			Utilities.clickAndEnterText(documentid + "AId300", AssetPO.getProperty("AssetID"), driver);

			Robot r1 = new Robot();
			r1.keyPress(KeyEvent.VK_TAB);
			r1.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			Utilities.sendText("Automation Description1");
			Thread.sleep(2000);

			r1.keyPress(KeyEvent.VK_TAB);
			r1.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r1.keyPress(KeyEvent.VK_TAB);
			r1.keyRelease(KeyEvent.VK_TAB);
			Utilities.sendText("300");
			Thread.sleep(2000);

			r1.keyPress(KeyEvent.VK_TAB);
			r1.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r1.keyPress(KeyEvent.VK_TAB);
			r1.keyRelease(KeyEvent.VK_TAB);

			// Click On Save button Asset Detail
			WebElement SaveI = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("SaveDetail"))));
			SaveI.click();

			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			// Utilities.clickButton("OK", 1000, driver);

			System.out.println("Edited Asset Purchase Order Save Successfully");

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// --------------------------------------------------------- Delete Asset
	// Purchase Order ---------------------------------------------

	public void Delete_AssetPO(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {
			String documentid1 = "APO" + documentid;
			// String AssetId= "AId"+Assetid;

			// Asset Purchase Order Utilities
			Properties AssetPO = Utilities.fetchProValue("OR_AssetPO.properties");
			Thread.sleep(500);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Purchase Order
			WebElement APObutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("ClickOnAPO"))));
			APObutton.click();
			Thread.sleep(500);

			// Enter Quick Search
			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("QuickSearch"))));
			Search.sendKeys(documentid1);

			// Click on records
			WebElement APOCheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("CheckBox"))));
			APOCheck.click();
			Thread.sleep(2000);

			// Click on Delete button
			WebElement APOEdit = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("Delete"))));
			APOEdit.click();
			Thread.sleep(2000);

			Actions Action = new Actions(driver);
			WebElement hoverElementandClick1 = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("DeleteP"))));

			Action.moveToElement(hoverElementandClick1).build().perform();
			Thread.sleep(5000);
			hoverElementandClick1.click();

			// Click On Yes Button
			WebElement APODYes = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("Yes"))));
			APODYes.click();
			Thread.sleep(2000);

			// Click On Ok Button
			WebElement APODOk = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPO.getProperty("Ok"))));
			APODOk.click();
			Thread.sleep(3000);

			System.out.println("Asset Purchase Order Deleted Successfully");
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// *************************** Export Files ******************************

	public void Export_AssetPO(String buttonName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);

			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String ExportButtonName = "Export List";
			String reportIcon = "//*[text()='Asset Purchase Order List']";
			String waitForQuickSearch = "//button[text()='Fetch']";
			String closeReportPage = "//li[@id='as__assetPurchaseOrderList']/a[1]";
			String Export = "Export";

			if (buttonName.equalsIgnoreCase("Export List")) {
				ExportButtonName = "Export List";
			} else if (buttonName.equalsIgnoreCase("Export")) {
				ExportButtonName = "Export ";
			}

			Thread.sleep(2000);
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

				// ******************************** Export CSV File
				// *****************************

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
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("pdfTemplate"))));
				element.click();
				Thread.sleep(1000);
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
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

				// ************************************ Export Excel Files
				// ***********************************

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

				// Print To HTML

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
						"* * * * * * EXPORT for module [Asset Purchase Order] completed successfully * * * * * * *");
			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				noData.printStackTrace();
			}
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [Asset Purchase Order] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}
	}

	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;
	}
}
