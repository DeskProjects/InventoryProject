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

public class AssetGR {
	public void Create_AssetGR(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			String documentid1 = "AGR" + documentid;

			// Asset Goods Receipt Utilities

			Properties AssetGR = Utilities.fetchProValue("OR_AssetGR.properties");
			Thread.sleep(2000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Goods Receipt

			WebElement AGRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("ClickOnAGR"))));
			AGRbutton.click();
			Thread.sleep(3000);

			// Enter Asset GR Vendor

			WebElement Vendor = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("Vendor"))));
			Thread.sleep(3000);
			Vendor.clear();
			Thread.sleep(3000);
			Vendor.sendKeys("Amol");
			Vendor.click();
			Thread.sleep(3000);
			Vendor.sendKeys(Keys.ENTER);

			// Click On NA Sequence Format

			WebElement AGRSequence = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("SequenceFormat"))));
			Thread.sleep(3000);
			AGRSequence.clear();
			Thread.sleep(3000);
			AGRSequence.sendKeys("NA");
			AGRSequence.click();
			Thread.sleep(3000);
			AGRSequence.sendKeys(Keys.ENTER);

			// Enter Asset GR Number
			WebElement AGRNo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("AGRNo"))));
			Thread.sleep(3000);
			AGRNo.sendKeys(documentid1);

			Utilities.enterTextInDropDown("Yes", AssetGR.getProperty("Link"), driver);
			Utilities.enterTextInDropDown("Purchase Invoice", AssetGR.getProperty("Linkto"), driver);
			WebElement memo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("Memo"))));
			memo.click();

			String drpDwnArrow = "//input[@id='poNumberID11FixedAssetgoodsreceiptdelivery']/following::span[1]/img[2]";
			String itemInputBoxLocator = "//input[@id='poNumberID11FixedAssetgoodsreceiptdelivery']";
			String itemName = "AGR" + documentid;
			String rslTable = "//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div/table/tbody/tr/td[1]";

			Utilities.selectItemfromDropDown(drpDwnArrow, itemInputBoxLocator, itemName, rslTable, driver);
			memo.click();

			Utilities.clickButton("Save", 3000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 2000, driver);

			System.out.println("*********** Automation Result *************");
			System.out.println("Asset Goods Receipt Save Successfully");

			// Enter Asset Goods Receipt form tab close

			WebElement AGRCross = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("Close"))));
			Thread.sleep(3000);
			AGRCross.click();

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	public void Edit_AssetGR(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			String documentid1 = "AGR" + documentid;

			// Asset Goods Receipt Utilities

			Properties AssetGR = Utilities.fetchProValue("OR_AssetGR.properties");
			Thread.sleep(2000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Goods Receipt List

			WebElement AGRL = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("ClickOnAGRL"))));
			AGRL.click();
			Thread.sleep(3000);

			// Click On Fetch button
			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("Fetch"))));
			Search.click();
			Thread.sleep(1000);

			// Enter Quick Search
			WebElement Search1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("QuickSearch"))));
			Search1.sendKeys(documentid1);
			Thread.sleep(2000);

			// Click on records
			WebElement AGRCheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("CheckBox"))));
			AGRCheck.click();
			Thread.sleep(2000);

			// Click On Asset Goods Receipt
			WebElement AGRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("Edit"))));
			AGRbutton.click();
			Thread.sleep(3000);

			// Updated Asset Goods Receipt Memo
			WebElement AGRMemo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("Memo"))));
			AGRMemo.click();
			AGRMemo.clear();
			Thread.sleep(2000);

			AGRMemo.sendKeys("Updated Asset Memo");
			Thread.sleep(2000);

			// Click On Asset Goods Receipt
			WebElement AGRD = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("Delete"))));
			AGRD.click();
			Thread.sleep(3000);

			// Click On Asset Goods Receipt
			WebElement AYes = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("Yes"))));
			AYes.click();
			Thread.sleep(3000);

			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);

			System.out.println("Asset Goods Receipt Edited Successfully");
			Thread.sleep(3000);

			// ******************** ******Deleted Asset Good
			// Receipt***********************

			// Click on records

			WebElement AGRCheck1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("CheckBox"))));
			AGRCheck1.click();
			Thread.sleep(3000);

			// Click on Delete button
			WebElement AGREdit = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("DeleteB"))));
			AGREdit.click();
			Thread.sleep(2000);

			Actions Action = new Actions(driver);
			WebElement hoverElementandClick1 = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("DeleteP"))));

			Action.moveToElement(hoverElementandClick1).build().perform();
			Thread.sleep(5000);
			hoverElementandClick1.click();

			// Click On Yes Button
			WebElement AGRDYes = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("Yes"))));
			AGRDYes.click();
			Thread.sleep(2000);

			// Click On Ok Button

			WebElement AGRDOk = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("Ok"))));
			AGRDOk.click();
			Thread.sleep(3000);

			System.out.println("Asset Goods Receipt Deleted Successfully");

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}

	// ********************** Export Files ********************

	public void Export_AssetGR(String buttonName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);

			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String ExportButtonName = "Export List";
			String reportIcon = "//*[text()='Asset Receipt List']";
			String waitForQuickSearch = "//button[text()='Fetch']";
			String closeReportPage = "//li[@id='as__GoodsReceiptDeliveryOrderListEntryFixedAsset']/a[1]";
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
						"* * * * * * EXPORT for module [Asset Goods Receipt] completed successfully * * * * * * *");
			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				noData.printStackTrace();
			}
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [Asset Goods Receipt] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}
	}

	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;

	}
}