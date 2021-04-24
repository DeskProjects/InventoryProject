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

public class AssetPurchaseReturn {
	public void Create_AssetPR(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			String documentid1 = "APR" + documentid;

			// Asset Purchase Return Utilities

			Properties AssetPR = Utilities.fetchProValue("OR_AssetPurchaseReturn.properties");
			Thread.sleep(2000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Purchase Return

			WebElement APRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("ClickOnAPR"))));
			APRbutton.click();
			Thread.sleep(3000);

			// Click On Create New button

			WebElement APRbuttonC = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("CreateNew"))));
			APRbuttonC.click();
			Thread.sleep(2000);

			// Enter Asset PR Vendor

			WebElement Vendor = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("Vendor"))));
			Thread.sleep(3000);
			Vendor.clear();
			Thread.sleep(3000);
			Vendor.sendKeys("Amol");
			Vendor.click();
			Thread.sleep(3000);
			Vendor.sendKeys(Keys.ENTER);

			// Click On NA Sequence Format

			WebElement APRSequence = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("SequenceFormat"))));
			Thread.sleep(3000);
			APRSequence.clear();
			Thread.sleep(3000);
			APRSequence.sendKeys("NA");
			APRSequence.click();
			Thread.sleep(3000);
			APRSequence.sendKeys(Keys.ENTER);

			// Enter Asset PR Number
			WebElement APRNo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("APRNo"))));
			Thread.sleep(3000);
			APRNo.sendKeys(documentid1);

			Utilities.enterTextInDropDown("Yes", AssetPR.getProperty("Link"), driver);
			Utilities.enterTextInDropDown("Asset Goods Receipt", AssetPR.getProperty("Linkto"), driver);
			WebElement memo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("Memo"))));
			memo.click();

			String drpDwnArrow = "//input[@id='poNumberIDundefinedassetPurchaseReturn']/following::span[1]/img[2]";
			String itemInputBoxLocator = "//input[@id='poNumberIDundefinedassetPurchaseReturn']";
			String itemName = "AGR1000";
			String rslTable = "//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div/table/tbody/tr/td[1]";

			Utilities.selectItemfromDropDown(drpDwnArrow, itemInputBoxLocator, itemName, rslTable, driver);
			memo.click();

			// Enter Asset Purchase Return Memo

			WebElement APRMemo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("Memo"))));
			APRMemo.click();
			Thread.sleep(500);
			APRMemo.sendKeys("Asset Purchase Return Memo");
			Thread.sleep(2000);

			Utilities.clickButton("Save", 3000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 2000, driver);

			System.out.println("*********** Automation Result *************");
			System.out.println("Asset Purchase Return Save Successfully");

			// Enter Asset Purchase Return form tab close

			WebElement APRCross = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("Close"))));
			Thread.sleep(3000);
			APRCross.click();

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	public void Edit_AssetPR(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			String documentid1 = "APR" + documentid;
			// String AssetId= "AId"+Assetid;

			// Asset Purchase Return Utilities
			Properties AssetPR = Utilities.fetchProValue("OR_AssetPurchaseReturn.properties");
			Thread.sleep(500);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Acquired Invoice
			WebElement APRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("ClickOnAPR"))));
			APRbutton.click();
			Thread.sleep(1000);

			// Click On Fetch button

			WebElement Fetch = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("Fetch"))));
			Fetch.click();
			Thread.sleep(1000);

			// Enter Quick Search
			WebElement Search1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("QuickSearch"))));
			Search1.sendKeys(documentid1);
			Thread.sleep(2000);

			// Click on records
			WebElement APRCheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("CheckBox"))));
			APRCheck.click();
			Thread.sleep(2000);

			// Click On Asset Purchase Return

			WebElement Edit = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("Edit"))));
			Edit.click();
			Thread.sleep(3000);

			// Updated Asset Acquired Invoice Memo

			WebElement APRMemo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("Memo"))));
			APRMemo.click();
			APRMemo.clear();
			Thread.sleep(2000);

			APRMemo.sendKeys("Updated Asset Memo");
			Thread.sleep(2000);

			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out.println("Edited Asset Purchase Return Save Successfully");

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	public void Delete_AssetPR(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {
			String documentid1 = "APR" + documentid;
			// String AssetId= "PRd"+Assetid;

			// Asset Purchase Return Utilities
			Properties AssetPR = Utilities.fetchProValue("OR_AssetPurchaseReturn.properties");
			Thread.sleep(3000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Acquired Invoice
			WebElement APRRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("ClickOnAPR"))));
			APRRbutton.click();
			Thread.sleep(1000);

			// Click On Fetch button
			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("Fetch"))));
			Search.click();
			Thread.sleep(1000);

			// Enter Quick Search
			WebElement Search1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("QuickSearch"))));
			Search1.sendKeys("APRDoc22Nov");
			Thread.sleep(2000);

			// Click on records
			WebElement APRCheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("CheckBox"))));
			APRCheck.click();
			Thread.sleep(2000);

			// Click on Delete button
			WebElement APREdit = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("DeleteB"))));
			APREdit.click();
			Thread.sleep(2000);

			Actions Action = new Actions(driver);
			WebElement hoverElementandClick1 = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("DeleteP"))));

			Action.moveToElement(hoverElementandClick1).build().perform();
			Thread.sleep(5000);
			hoverElementandClick1.click();

			// Click On Yes Button
			WebElement APRDYes = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("Yes"))));
			APRDYes.click();
			Thread.sleep(2000);

			// Click On Ok Button
			WebElement APRDOk = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetPR.getProperty("Ok"))));
			APRDOk.click();
			Thread.sleep(3000);

			System.out.println("Asset Purchase Return Deleted Successfully");

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}

	// *************************** Export Files ******************************

	public void Export_AssetPR(String buttonName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);

			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String ExportButtonName = "Export List";
			String reportIcon = "//*[text()='Asset Purchase Return List']";
			String waitForQuickSearch = "//button[text()='Fetch']";
			String closeReportPage = "//li[@id='as__assetPurchaseReturnList']/a[1]";
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
						"* * * * * * EXPORT for module [Asset Purchase Return] completed successfully * * * * * * *");
			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				noData.printStackTrace();
			}
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [Asset Purchase Return] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}
	}

	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;
	}

	public void Verification_AssetPRV(String documentid, String string, WebDriver driver)

	{
		// TODO Auto-generated method stub

	}
}
