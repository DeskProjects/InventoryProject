
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

public class AssetPurchaseRequisition {

	public void Create_AssetRequisition(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			// documentid= "APur"+documentid;

			// Asset Requisition Utilities
			Properties AssetRequisition = Utilities.fetchProValue("OR_AssetRequisition.properties");
			Thread.sleep(1000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Requisition
			WebElement AGRbutton = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("ClickonAPur"))));
			AGRbutton.click();
			Thread.sleep(2000);

			// Click On Create New button

			WebElement AGRbuttonC = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("CreateNew"))));
			AGRbuttonC.click();
			Thread.sleep(2000);

			// Click On NA Sequence Format
			WebElement AGRSequence = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("SequenceFormat"))));
			Thread.sleep(3000);
			AGRSequence.clear();
			Thread.sleep(3000);
			AGRSequence.sendKeys("NA");
			AGRSequence.click();
			Thread.sleep(3000);
			AGRSequence.sendKeys(Keys.ENTER);

			// Enter Asset Requisition Number
			WebElement AGID = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("AGRNumber"))));
			Thread.sleep(3000);
			AGID.sendKeys("APur" + documentid);
			Thread.sleep(3000);

			// Enter Asset Requisition Memo
			WebElement AGRMemo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("Memo"))));
			Thread.sleep(1000);
			AGRMemo.sendKeys("Asset Memo");
			Thread.sleep(2000);

			// Enter Asset Group Detail First
			Utilities.HoverandClick(AssetRequisition.getProperty("AssetGroup"), driver);
			Utilities.enterTextandSelect("AGroup" + documentid, "//*[@name='productname']", driver);
			AGRMemo.click();
			Thread.sleep(1000);

			// Enter Asset Requisition Description
			WebElement AGRDescription = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("ADescription"))));
			AGRDescription.click();
			Thread.sleep(1000);
			Utilities.sendText("Asset Description");

			// Enter Unit Price

			/*
			 * WebElement Unitprice =
			 * AGwait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			 * AssetRequisition.getProperty("UnitPrice")))); Unitprice.click();
			 * Utilities.sendText("100");
			 * 
			 * 
			 * WebElement AGRPrice =
			 * AGwait.until(ExpectedConditions.elementToBeClickable(
			 * By.xpath(AssetRequisition.getProperty("UnitPrice")))); //
			 * AGRPrice.click(); Thread.sleep(1000); // AGRPrice.clear();
			 * Thread.sleep(1000); Utilities.sendText("100");
			 * Thread.sleep(1000);
			 * 
			 * // Enter Asset Unit Price First
			 * Utilities.HoverandClick(AssetRequisition.getProperty("UnitPrice")
			 * , driver); Utilities.enter_LinelabelAmount("100", driver);
			 * Thread.sleep(1000);
			 */
			Actions action = new Actions(driver);
			WebElement hoverElementandClick = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//div[@id='assetRequisitioneditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td[21]/div/div")));
			action.doubleClick(hoverElementandClick).build().perform();
			Utilities.sendText("100");
			Thread.sleep(1000);
			// Enter View

			WebElement View = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("View"))));
			View.click();
			Thread.sleep(1000);

			WebElement View1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("View"))));
			View1.click();

			Utilities.clickAndEnterText("AGN0001", AssetRequisition.getProperty("AssetID"), driver);

			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			Utilities.sendText("Automation Description1");
			Thread.sleep(2000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Utilities.sendText("100");
			Thread.sleep(2000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);

			Thread.sleep(2000);

			// Click On Save button Asset Detail
			WebElement SaveI = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("SaveDetail"))));
			SaveI.click();

			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out.println("Asset Purchase Requistion Save Successfully");

			/*
			 * // Click On Asset Report Cross Symbol
			 * Utilities.HoverandClick(closeReportPage, driver);
			 * Thread.sleep(1000);
			 */
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// --------------------------------------------------------- Edit Asset
	// Purchase Requisition ---------------------------------------------

	public void Edit_AssetRequisition(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			String documentid1 = "APur" + documentid;
			// String AssetId= "AId"+Assetid;

			// Asset Requisition Utilities
			Properties AssetRequisition = Utilities.fetchProValue("OR_AssetRequisition.properties");
			Thread.sleep(500);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Requisition
			WebElement AGRbutton = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("ClickonAPur"))));
			AGRbutton.click();
			Thread.sleep(500);

			// Enter Quick Search
			WebElement Search = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("QuickSearch"))));
			Search.sendKeys(documentid1);

			// Click on records
			WebElement ARCheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("CheckBox"))));
			ARCheck.click();
			Thread.sleep(2000);

			// Click on records
			WebElement AREdit = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("Edit"))));
			AREdit.click();
			Thread.sleep(2000);

			// Updated Asset Requisition Memo
			WebElement AGRMemo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("Memo"))));
			AGRMemo.click();
			AGRMemo.clear();

			Thread.sleep(500);
			AGRMemo.sendKeys("Updated Asset Memo");
			Thread.sleep(1000);

			// Added another Asset Group
			Utilities.clickAndEnterText("AGN0001", AssetRequisition.getProperty("AssetGroup1"), driver);

			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			Utilities.sendText("Automation Description1");
			Thread.sleep(2000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Utilities.sendText("100");
			Thread.sleep(1000);

			// Enter View

			/*
			 * Actions action = new Actions(driver); // WebElement View =
			 * driver.findElement(By.xpath(
			 * "//div[@id='FixedAssetPurchaseRequisitionListEntry']/following::td[72]/following::div[1]/table/tbody/tr/td[36]/div/div"
			 * )); action.moveToElement(driver.findElement(By.xpath(
			 * "//div[@id='FixedAssetPurchaseRequisitionListEntry']/following::td[72]/following::div[1]/table/tbody/tr/td[36]/div/div"
			 * ))).doubleClick().perform(); //
			 * action.doubleClick(View).perform();
			 * action.doubleClick(View).build().perform();
			 */

			WebElement View = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("View1"))));
			View.click();
			Thread.sleep(500);

			WebElement View1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("View1"))));
			View1.click();
			Thread.sleep(500);

			Utilities.clickAndEnterText(documentid + "AId1", AssetRequisition.getProperty("AssetID"), driver);

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
			Utilities.sendText("100");
			Thread.sleep(2000);

			r1.keyPress(KeyEvent.VK_TAB);
			r1.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r1.keyPress(KeyEvent.VK_TAB);
			r1.keyRelease(KeyEvent.VK_TAB);

			// Click On Save button Asset Detail
			WebElement SaveI = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("SaveDetail"))));
			SaveI.click();

			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out.println("Edited Asset Purchase Requistion Save Successfully");

			// Click On Asset Report Cross Symbol
			WebElement AGCross = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("CrossSymbol"))));
			AGCross.click();
			Thread.sleep(1000);

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// --------------------------------------------------------- Delete Asset
	// Purchase Requisition ---------------------------------------------

	public void Delete_AssetRequisition(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {
			String documentid1 = "APur" + documentid;
			// String AssetId= "AId"+Assetid;
			String closeReportPage = "//li[@id='as__assetRequisition']/a[1]";

			// Asset Requisition Utilities
			Properties AssetRequisition = Utilities.fetchProValue("OR_AssetRequisition.properties");
			Thread.sleep(500);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Requisition
			WebElement AGRbutton = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("ClickonAPur"))));
			AGRbutton.click();
			Thread.sleep(500);

			// Enter Quick Search
			WebElement Search = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("QuickSearch"))));
			Search.sendKeys(documentid1);

			// Click on records
			WebElement ARCheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("CheckBox"))));
			ARCheck.click();
			Thread.sleep(2000);

			// Click on Delete button
			WebElement AREdit = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("Delete"))));
			AREdit.click();
			Thread.sleep(2000);

			Actions Action = new Actions(driver);
			WebElement hoverElementandClick1 = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("DeleteP"))));

			Action.moveToElement(hoverElementandClick1).build().perform();
			Thread.sleep(5000);
			hoverElementandClick1.click();

			// Click On Yes Button
			WebElement ARDYes = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("Yes"))));
			ARDYes.click();
			Thread.sleep(2000);

			// Click On Ok Button
			WebElement ARDOk = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("Ok"))));
			ARDOk.click();
			Thread.sleep(3000);

			Utilities.HoverandClick(closeReportPage, driver);

			/*
			 * // Enter Asset Requisition Number WebElement AGID =
			 * AGwait.until(ExpectedConditions.elementToBeClickable(
			 * By.xpath(AssetRequisition.getProperty("AGRNumber"))));
			 * Thread.sleep(3000); AGID.sendKeys("CopyDoc18Sep");
			 */

		}

		catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}

	// --------------------------------------------------------- Copy Asset
	// Purchase Requisition ---------------------------------------------

	public void Copy_AssetRequisition(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {
			String documentid1 = "APur" + documentid;
			// String AssetId= "AId"+Assetid;

			// Asset Requisition Utilities
			Properties AssetRequisition = Utilities.fetchProValue("OR_AssetRequisition.properties");
			Thread.sleep(500);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Requisition
			WebElement AGRbutton = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("ClickonAPur"))));
			AGRbutton.click();
			Thread.sleep(500);

			// Enter Quick Search
			WebElement Search = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("QuickSearch"))));
			Search.sendKeys(documentid1);

			// Click on records
			WebElement ARCheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("CheckBox"))));
			ARCheck.click();
			Thread.sleep(2000);

			// Click on Copy button
			WebElement AREdit = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("Copy"))));
			AREdit.click();
			Thread.sleep(2000);

			// Enter Asset Requisition Number
			WebElement AGID = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("AGRNumber"))));

			AGID.sendKeys("CopyDoc18Sep");
			Thread.sleep(1000);

			// Enter Asset Details
			WebElement View = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("View2"))));
			View.click();
			Thread.sleep(3000);

			Utilities.clickAndEnterText(documentid + "AId11", AssetRequisition.getProperty("AssetId2"), driver);
			Thread.sleep(500);

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
			Utilities.sendText("100");
			Thread.sleep(2000);

			r1.keyPress(KeyEvent.VK_TAB);
			r1.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r1.keyPress(KeyEvent.VK_TAB);
			r1.keyRelease(KeyEvent.VK_TAB);
			Utilities.sendText("100");

			// Click On Yes Button
			WebElement ARDYes = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("SaveDetail"))));
			ARDYes.click();
			Thread.sleep(2000);

			// Enter Asset Details
			WebElement View3 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("View3"))));
			View3.click();
			Thread.sleep(3000);

			Utilities.clickAndEnterText(documentid + "AId12", AssetRequisition.getProperty("AssetId2"), driver);

			Thread.sleep(500);

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

			// Click On Yes Button
			WebElement ARDYes1 = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetRequisition.getProperty("SaveDetail"))));
			ARDYes1.click();
			Thread.sleep(2000);

			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out.println("Copy Asset Purchase Requistion Save Successfully");

		}

		catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}

	// ********************** Export Files ********************

	public void Export_AssetRequisition(String buttonName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);

			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String ExportButtonName = "Export List";
			String reportIcon = "//*[text()='Asset Purchase Requisition List']";
			String waitForQuickSearch = "//button[text()='Fetch']";
			String closeReportPage = "//li[@id='as__FixedAssetPurchaseRequisitionList']/a[1]";
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
						"* * * * * * EXPORT for module [Asset Purchase Requision] completed successfully * * * * * * *");
			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				noData.printStackTrace();
			}
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [Asset Purchase Requision] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}
	}

	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;
	}
}
