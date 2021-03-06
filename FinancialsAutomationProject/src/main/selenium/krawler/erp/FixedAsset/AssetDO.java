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
import org.openqa.selenium.support.ui.Select;

import krawler.erp.page.Utilities;
import krawler.erp.utils.SikulliUtil;

public class AssetDO {
	public void Create_AssetDO(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			String documentid1 = "ADO" + documentid;

			// Asset Delivery Order Utilities

			Properties AssetDO = Utilities.fetchProValue("OR_AssetDO.properties");
			Thread.sleep(2000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Delivery Order

			WebElement ADObutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDO.getProperty("ClickOnADO"))));
			ADObutton.click();
			Thread.sleep(3000);

			// Enter Asset DO Vendor

			WebElement Vendor = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDO.getProperty("Customer"))));
			Thread.sleep(3000);
			Vendor.clear();
			Thread.sleep(3000);
			Vendor.sendKeys("Amol");
			Vendor.click();
			Thread.sleep(3000);
			Vendor.sendKeys(Keys.ENTER);

			// Click On NA Sequence Format

			WebElement ADOSequence = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDO.getProperty("SequenceFormat"))));
			Thread.sleep(3000);
			ADOSequence.clear();
			Thread.sleep(3000);
			ADOSequence.sendKeys("NA");
			ADOSequence.click();
			Thread.sleep(3000);
			ADOSequence.sendKeys(Keys.ENTER);

			// Enter Asset DO Number
			WebElement ADONo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDO.getProperty("ADONo"))));
			Thread.sleep(3000);
			ADONo.sendKeys(documentid1);

			/*
			 * // Link Document Asset Disposal Invoice
			 * 
			 * Utilities.enterTextInDropDown("Yes", AssetDO.getProperty("Link"),
			 * driver); Utilities.enterTextInDropDown("Purchase Invoice",
			 * AssetDO.getProperty("Linkto"), driver); WebElement
			 * memo=AGwait.until(ExpectedConditions.elementToBeClickable(By.
			 * xpath(AssetDO.getProperty("Memo")))); memo.click();
			 * 
			 * String drpDwnArrow =
			 * "//input[@id='poNumberID11FixedAssetgoodsreceiptdelivery']/following::span[1]/img[2]";
			 * String itemInputBoxLocator=
			 * "//input[@id='poNumberID11FixedAssetgoodsreceiptdelivery']";
			 * String itemName = "ADO"+documentid; String rslTable=
			 * "//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div/table/tbody/tr/td[1]"
			 * ;
			 * 
			 * Utilities.selectItemfromDropDown(drpDwnArrow,
			 * itemInputBoxLocator, itemName, rslTable, driver); memo.click();
			 */

			// Enter Asset Asset Delivery Order Memo

			WebElement ADOMemo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDO.getProperty("Memo"))));
			ADOMemo.click();
			Thread.sleep(500);
			ADOMemo.sendKeys("Asset Memo");
			Thread.sleep(2000);

			// Enter Asset Group Detail First

			Utilities.HoverandClick(AssetDO.getProperty("AssetGroup1"), driver);
			Utilities.enterTextandSelect("AGID44", "//*[@name='productname']", driver);
			ADOMemo.click();
			Thread.sleep(2000);

			// Enter Asset Unit Price First

			Utilities.HoverandClick(AssetDO.getProperty("UnitPrice1"), driver);
			Utilities.enter_LinelabelAmount("100", driver);
			ADOMemo.click();
			Thread.sleep(1000);

			/*
			 * // Enter Asset Quantity First
			 * Utilities.HoverandClick(AssetDO.getProperty("Quantity1"),
			 * driver); Utilities.enter_LinelabelAmount("1", driver);
			 * ADOMemo.click(); Thread.sleep(2000);
			 */

			// Enter Asset Unit Price First

			Utilities.HoverandClick(AssetDO.getProperty("View"), driver);
			Thread.sleep(1000);

			/*
			 * // Enter Asset ID First
			 * Utilities.clickAndEnterText(documentid+"ADOId100",
			 * AssetDO.getProperty("AssetID"), driver); Thread.sleep(1000);
			 * 
			 * 
			 * // Enter Asset Group Detail First
			 * Utilities.HoverandClick(AssetDO.getProperty("AssetID"), driver);
			 * Utilities.enterTextandSelect("C111",
			 * "//*[text()='Asset Id']/following::div[88]", driver);
			 */

			Utilities.clickAndEnterText(documentid + "ADOId100", AssetDO.getProperty("AssetID1"), driver);
			Thread.sleep(3000);
			// Utilities.sendText("C111");
			Utilities.Enter();

			Thread.sleep(3000);

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

			// Click On Save Button Asset Detail First

			WebElement SaveI1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDO.getProperty("SaveDetail"))));
			SaveI1.click();
			Thread.sleep(1000);

			Utilities.clickButton("Save", 3000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 2000, driver);

			System.out.println("*********** Automation Result *************");
			System.out.println("Asset Delivery Order Save Successfully");

			// Enter Asset Delivery Order form tab close

			WebElement ADOCross = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDO.getProperty("Close"))));
			Thread.sleep(3000);
			ADOCross.click();

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// --------------------------------------------------------- Delete Asset
	// Delivery Order ---------------------------------------------

	public void Delete_AssetDO(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {
			String documentid1 = "ADO" + documentid;
			// String AssetId= "DOd"+Assetid;

			// Asset Delivery Order Utilities
			Properties AssetDO = Utilities.fetchProValue("OR_AssetDO.properties");
			Thread.sleep(3000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Acquired Invoice
			WebElement ADORbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDO.getProperty("ClickOnADOR"))));
			ADORbutton.click();
			Thread.sleep(1000);

			// Click On Fetch button
			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDO.getProperty("Fetch"))));
			Search.click();
			Thread.sleep(1000);

			// Enter Quick Search
			WebElement Search1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDO.getProperty("QuickSearch"))));
			Search1.sendKeys("ADO100");
			Thread.sleep(2000);

			// Click on records
			WebElement ADOCheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDO.getProperty("CheckBox"))));
			ADOCheck.click();
			Thread.sleep(2000);

			// Click on Delete button
			WebElement ADOEdit = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDO.getProperty("DeleteB"))));
			ADOEdit.click();
			Thread.sleep(2000);

			Actions Action = new Actions(driver);
			WebElement hoverElementandClick1 = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDO.getProperty("DeleteP"))));

			Action.moveToElement(hoverElementandClick1).build().perform();
			Thread.sleep(5000);
			hoverElementandClick1.click();

			// Click On Yes Button
			WebElement ADODYes = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDO.getProperty("Yes"))));
			ADODYes.click();
			Thread.sleep(2000);

			// Click On Ok Button
			WebElement ADODOk = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDO.getProperty("Ok"))));
			ADODOk.click();
			Thread.sleep(3000);

			System.out.println("Asset Delivery Order  Deleted Successfully");
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ********************** Export Files ********************

	public void Export_AssetDO(String buttonName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);

			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String ExportButtonName = "Export List";
			String reportIcon = "//*[text()='Asset Delivery List']";
			String waitForQuickSearch = "//button[text()='Fetch']";
			String closeReportPage = "//li[@id='as__FixedAssetDeliveryOrderListEntry']/a[1]";
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
						"* * * * * * EXPORT for module [Asset Delivery Order] completed successfully * * * * * * *");
			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				noData.printStackTrace();
			}
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [Asset Delivery Order] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}
	}

	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;

	}

	private By ByXPath(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
