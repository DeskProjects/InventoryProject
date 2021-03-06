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

public class AssetRFQ {
	public void Create_AssetRFQ(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			String documentid1 = "ARFQ" + documentid;

			// Asset RFQ Utilities
			Properties AssetRFQ = Utilities.fetchProValue("OR_AssetRFQ.properties");
			Thread.sleep(500);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset RFQ
			WebElement ARFQbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("ClickOnARFQ"))));
			ARFQbutton.click();
			Thread.sleep(3000);

			// Click On Create New button

			WebElement AGRbuttonC = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("CreateNew"))));
			AGRbuttonC.click();
			Thread.sleep(1000);

			// Click On NA Sequence Format

			WebElement RFQSequence = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("SequenceFormat"))));
			Thread.sleep(3000);
			RFQSequence.clear();
			Thread.sleep(3000);
			RFQSequence.sendKeys("NA");
			RFQSequence.click();
			Thread.sleep(3000);
			RFQSequence.sendKeys(Keys.ENTER);

			// Enter Asset RFQ Number
			WebElement RFQNo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("RFQNo"))));
			Thread.sleep(3000);
			RFQNo.sendKeys(documentid1);

			// Enter Asset RFQ Memo
			WebElement AGRMemo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("Memo"))));
			AGRMemo.click();
			Thread.sleep(500);
			AGRMemo.sendKeys("Asset Memo");
			Thread.sleep(2000);

			// Enter Asset RFQ Memo
			WebElement Vendor = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("Vendor"))));
			Vendor.click();
			Thread.sleep(500);
			Vendor.sendKeys("Amol");
			Thread.sleep(2000);

			// Enter Asset Group
			WebElement AGroup = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("AssetGroup1"))));
			AGroup.click();
			Thread.sleep(1000);

			// Select Asset Group
			WebElement AGroup1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("SelectG"))));
			AGroup1.click();
			Thread.sleep(1000);

			AGroup1.sendKeys("AGN0001");
			AGroup1.sendKeys(Keys.ENTER);
			Thread.sleep(1000);

			// Enter Asset Group Memo
			WebElement RFQescription = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("ADescription1"))));
			RFQescription.click();
			Thread.sleep(500);
			Utilities.sendText("Asset Description");

			// Enter View icon

			WebElement View = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("View1"))));
			View.click();
			Thread.sleep(500);

			WebElement View1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("View1"))));
			View1.click();

			Utilities.clickAndEnterText(documentid + "AGN0001", AssetRFQ.getProperty("AssetID"), driver);

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
			Utilities.sendText("100");
			Thread.sleep(2000);

			// Click On Save button Asset Detail
			WebElement SaveI = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("SaveDetail"))));
			SaveI.click();

			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out.println("Asset RFQ Save Successfully");

			// Click On Asset Report Cross Symbol
			WebElement AGCross = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("CrossSymbol"))));
			AGCross.click();
			Thread.sleep(1000);
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}

	// --------------------------------------------------------- Copy Asset
	// Purchase RFQ---------------------------------------------

	public void Copy_AssetRFQ(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {
			String documentid1 = "ARFQ" + documentid;
			// String AssetId= "AId"+Assetid;

			// Asset RFQ Utilities
			Properties AssetRFQ = Utilities.fetchProValue("OR_AssetRFQ.properties");
			Thread.sleep(2000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset RFQ
			WebElement AGRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("ClickOnARFQ"))));
			AGRbutton.click();
			Thread.sleep(2000);

			// Enter Quick Search
			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("QuickSearch"))));
			Search.sendKeys(documentid1);

			// Click on records
			WebElement ARCheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("CheckBox"))));
			ARCheck.click();
			Thread.sleep(2000);

			// Click on Copy button
			WebElement ARCopy = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("Copy"))));
			ARCopy.click();
			Thread.sleep(2000);

			// Enter Asset RFQ Number
			WebElement AGID = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("RFQNo"))));

			AGID.sendKeys("CopyDoc26Sep");
			Thread.sleep(1000);

			// Enter Asset RFQ Memo
			WebElement AGRMemo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("Memo"))));
			AGRMemo.clear();
			AGRMemo.click();
			Thread.sleep(500);
			AGRMemo.sendKeys("Copy Asset Memo");
			Thread.sleep(2000);

			// Enter Asset Details
			WebElement View = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("View2"))));
			View.click();
			Thread.sleep(3000);

			Utilities.clickAndEnterText(documentid + "AId11", AssetRFQ.getProperty("AssetID1"), driver);
			Thread.sleep(2000);

			Robot r1 = new Robot();
			r1.keyPress(KeyEvent.VK_TAB);
			r1.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			Utilities.sendText("Automation Description2");
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
			WebElement ARDYes1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("SaveDetail"))));
			ARDYes1.click();
			Thread.sleep(2000);

			// Click Asset Group
			WebElement AGroup1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("AssetGroup2"))));
			AGroup1.click();
			Thread.sleep(2000);

			AGroup1.sendKeys("AGN0001");
			AGroup1.sendKeys(Keys.ENTER);
			Thread.sleep(3000);

			/*
			 * Utilities.clickAndEnterText("AGN0001",
			 * AssetRFQ.getProperty("AssetGroup1"), driver); Thread.sleep(3000);
			 * 
			 * Robot r2 = new Robot(); Thread.sleep(1000);
			 * 
			 * r2.keyPress(KeyEvent.VK_TAB); r2.keyRelease(KeyEvent.VK_TAB);
			 * Thread.sleep(500); Utilities.sendText("Automation Description1");
			 * Thread.sleep(500);
			 * 
			 * r2.keyPress(KeyEvent.VK_TAB); r2.keyRelease(KeyEvent.VK_TAB);
			 * Thread.sleep(500);
			 * 
			 * r2.keyPress(KeyEvent.VK_TAB); r2.keyRelease(KeyEvent.VK_TAB);
			 * Thread.sleep(500);
			 * 
			 * 
			 * 
			 * 
			 * Utilities.clickAndEnterText(documentid+"AId12",
			 * AssetRFQ.getProperty("AssetID2"), driver); Thread.sleep(2000);
			 * 
			 * /* Robot r3 = new Robot(); r3 .keyPress(KeyEvent.VK_TAB); r3
			 * .keyRelease(KeyEvent.VK_TAB); Thread.sleep(1000);
			 * Utilities.sendText("Automation Description1");
			 * Thread.sleep(2000);
			 * 
			 * r3 .keyPress(KeyEvent.VK_TAB); r3 .keyRelease(KeyEvent.VK_TAB);
			 * Thread.sleep(1000);
			 * 
			 * r3 .keyPress(KeyEvent.VK_TAB); r3 .keyRelease(KeyEvent.VK_TAB);
			 * Utilities.sendText("100"); Thread.sleep(2000);
			 * 
			 * r3 .keyPress(KeyEvent.VK_TAB); r3 .keyRelease(KeyEvent.VK_TAB);
			 * Thread.sleep(1000);
			 * 
			 * r3 .keyPress(KeyEvent.VK_TAB); r3 .keyRelease(KeyEvent.VK_TAB);
			 * Utilities.sendText("100");
			 * 
			 * // Enter Asset Details WebElement View3 =
			 * AGwait.until(ExpectedConditions.elementToBeClickable(
			 * By.xpath(AssetRFQ.getProperty("View3")))); View3.click();
			 * Thread.sleep(2000);
			 * 
			 * // Click On Yes Button WebElement ARDYes2 =
			 * AGwait.until(ExpectedConditions.elementToBeClickable(
			 * By.xpath(AssetRFQ.getProperty("SaveDetail")))); ARDYes2.click();
			 * Thread.sleep(2000);
			 */

			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out.println("Copy Asset RFQ  Save Successfully");

		}

		catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}
	// --------------------------------------------------------- Delete Asset
	// Purchase RFQ---------------------------------------------

	public void Delete_RFQ(String documentid, WebDriver driver) throws InterruptedException, IOException, AWTException {
		try {
			String documentid1 = "ARFQ" + documentid;
			// String AssetId= "AId"+Assetid;

			// Asset RFQ Utilities
			Properties AssetRFQ = Utilities.fetchProValue("OR_AssetRFQ.properties");
			Thread.sleep(1000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset RFQ
			WebElement ARFQbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("ClickOnARFQ"))));
			ARFQbutton.click();
			Thread.sleep(500);

			// Enter Quick Search
			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("QuickSearch"))));
			Search.sendKeys(documentid1);
			Thread.sleep(2000);

			// Click on records
			WebElement ARCheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("CheckBox"))));
			ARCheck.click();
			Thread.sleep(2000);

			// Click on Delete button
			WebElement AREdit = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("Delete"))));
			AREdit.click();
			Thread.sleep(2000);

			Actions Action = new Actions(driver);
			WebElement hoverElementandClick1 = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("DeleteP"))));

			Action.moveToElement(hoverElementandClick1).build().perform();
			Thread.sleep(5000);
			hoverElementandClick1.click();

			// Click On Yes Button
			WebElement ARDYes = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("Yes"))));
			ARDYes.click();
			Thread.sleep(2000);

			// Click On Ok Button
			WebElement ARDOk = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetRFQ.getProperty("Ok"))));
			ARDOk.click();
			Thread.sleep(3000);

		}

		catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}

	// *************************** Export Files ******************************

	public void Export_AssetRFQ(String buttonName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);

			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String ExportButtonName = "Export List";
			String reportIcon = "//*[text()='Asset RFQ List']";
			String waitForQuickSearch = "//button[text()='Fetch']";
			String closeReportPage = "//li[@id='as__assetRequestForQuotationReport']/a[1]";
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
				System.out.println("* * * * * * EXPORT for module [Asset RFQ] completed successfully * * * * * * *");
			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				noData.printStackTrace();
			}
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [Asset RFQ] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}
	}

	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;

	}
}
