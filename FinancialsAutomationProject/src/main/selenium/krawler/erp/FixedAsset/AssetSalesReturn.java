package krawler.erp.FixedAsset;

import java.awt.AWTException;
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

public class AssetSalesReturn {
	public void Create_AssetSR(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			String documentid1 = "ASR" + documentid;

			// Asset Sales Return Utilities

			Properties AssetSR = Utilities.fetchProValue("OR_AssetSalesReturn.properties");
			Thread.sleep(2000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Sales Return

			WebElement ASRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("ClickOnASR"))));
			ASRbutton.click();
			Thread.sleep(3000);

			// Click On Create New button

			WebElement ASRbuttonC = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("CreateNew"))));
			ASRbuttonC.click();
			Thread.sleep(2000);

			// Enter Asset SR Vendor

			WebElement Vendor = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("Customer"))));
			Thread.sleep(3000);
			Vendor.clear();
			Thread.sleep(3000);
			Vendor.sendKeys("Amol1");
			Vendor.click();
			Thread.sleep(3000);
			Vendor.sendKeys(Keys.ENTER);

			// Click On NA Sequence Format

			WebElement ASRSequence = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("SequenceFormat"))));
			Thread.sleep(3000);
			ASRSequence.clear();
			Thread.sleep(3000);
			ASRSequence.sendKeys("NA");
			ASRSequence.click();
			Thread.sleep(3000);
			ASRSequence.sendKeys(Keys.ENTER);

			// Enter Asset SR Number
			WebElement ASRNo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("ASRNo"))));
			Thread.sleep(3000);
			ASRNo.sendKeys(documentid1);

			Utilities.enterTextInDropDown("Yes", AssetSR.getProperty("Link"), driver);
			Utilities.enterTextInDropDown("Asset Delivery Order", AssetSR.getProperty("Linkto"), driver);
			WebElement memo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("Memo"))));
			memo.click();

			String drpDwnArrow = "//input[@id='poNumberIDundefinedassetSalesReturn']/following::span[1]/img[2]";
			String itemInputBoxLocator = "//input[@id='poNumberIDundefinedassetSalesReturn']";
			String itemName = "ADODoc22Nov";
			String rslTable = "//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div/table/tbody/tr/td[1]";

			Utilities.selectItemfromDropDown(drpDwnArrow, itemInputBoxLocator, itemName, rslTable, driver);
			memo.click();

			// Enter Asset Sales Return Memo

			WebElement ASRMemo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("Memo"))));
			ASRMemo.click();
			Thread.sleep(500);
			ASRMemo.sendKeys("Asset Sales Return Memo");
			Thread.sleep(2000);

			Utilities.clickButton("Save", 3000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 2000, driver);

			System.out.println("*********** Automation Result *************");
			System.out.println("Asset Sales Return Save Successfully");

			// Enter Asset Sales Return form tab close

			WebElement ASRCross = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("Close"))));
			Thread.sleep(3000);
			ASRCross.click();

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ******************************* Asset Purchase Return Edit
	// **********************

	public void Edit_AssetSR(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			String documentid1 = "ASR" + documentid;
			// String AssetId= "AId"+Assetid;

			// Asset Sales Return Utilities
			Properties AssetSR = Utilities.fetchProValue("OR_AssetSalesReturn.properties");
			Thread.sleep(500);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Acquired Invoice
			WebElement ASRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("ClickOnASR"))));
			ASRbutton.click();
			Thread.sleep(1000);

			// Click On Fetch button

			WebElement Fetch = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("Fetch"))));
			Fetch.click();
			Thread.sleep(1000);

			// Enter Quick Search
			WebElement Search1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("QuickSearch"))));
			Search1.sendKeys(documentid1);
			Thread.sleep(2000);

			// Click on records
			WebElement ASRCheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("CheckBox"))));
			ASRCheck.click();
			Thread.sleep(2000);

			// Click On Asset Sales Return

			WebElement Edit = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("Edit"))));
			Edit.click();
			Thread.sleep(3000);

			// Updated Asset Acquired Invoice Memo

			WebElement ASRMemo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("Memo"))));
			ASRMemo.click();
			ASRMemo.clear();
			Thread.sleep(2000);

			ASRMemo.sendKeys("Updated Asset Memo");
			Thread.sleep(2000);

			// Enter Asset Group Detail First

			Utilities.HoverandClick(AssetSR.getProperty("AssetGroup1"), driver);
			Utilities.enterTextandSelect("Computer", "//*[@name='productname']", driver);
			ASRMemo.click();
			Thread.sleep(2000);

			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out.println("Edited Asset Sales Return Save Successfully");

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ******************************* Asset Purchase Return Delete
	// **********************

	public void Delete_AssetSR(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {
			String documentid1 = "ASR" + documentid;
			// String AssetId= "SRd"+Assetid;

			// Asset Sales Return Utilities
			Properties AssetSR = Utilities.fetchProValue("OR_AssetSalesReturn.properties");
			Thread.sleep(3000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Acquired Invoice
			WebElement ASRRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("ClickOnASR"))));
			ASRRbutton.click();
			Thread.sleep(1000);

			// Click On Fetch button
			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("Fetch"))));
			Search.click();
			Thread.sleep(1000);

			// Enter Quick Search
			WebElement Search1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("QuickSearch"))));
			Search1.sendKeys("ASRDoc22Nov");
			Thread.sleep(2000);

			// Click on records
			WebElement ASRCheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("CheckBox"))));
			ASRCheck.click();
			Thread.sleep(2000);

			// Click on Delete button
			WebElement ASREdit = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("DeleteB"))));
			ASREdit.click();
			Thread.sleep(2000);

			Actions Action = new Actions(driver);
			WebElement hoverElementandClick1 = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("DeleteP"))));

			Action.moveToElement(hoverElementandClick1).build().perform();
			Thread.sleep(5000);
			hoverElementandClick1.click();

			// Click On Yes Button
			WebElement ASRDYes = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("Yes"))));
			ASRDYes.click();
			Thread.sleep(2000);

			// Click On Ok Button
			WebElement ASRDOk = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("Ok"))));
			ASRDOk.click();
			Thread.sleep(3000);

			System.out.println("Asset Sales Return Deleted Successfully");

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// *************************** Export Files ******************************

	public void Export_AssetSR(String buttonName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);

			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String ExportButtonName = "Export List";
			String reportIcon = "//*[text()='Asset Sales Return List']";
			String waitForQuickSearch = "//button[text()='Fetch']";
			String closeReportPage = "//li[@id='as__assetSalesReturnList']/a[1]";
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

				// ********************* Export to Excel File(Details)
				// ******************

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

				// ******************* Export to Excel File Summary
				// ******************

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

}
