package krawler.erp.FixedAsset;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;
import krawler.erp.utils.SikulliUtil;

public class AssetDetailReport {
	public void Generate_AssetDetailReport(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			String documentid1 = "ADetailReport" + documentid;

			// Asset Detail Report

			Properties AssetDetailReport = Utilities.fetchProValue("OR_AssetDetailReport.properties");
			Thread.sleep(2000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Detail Report

			WebElement ADetailReportbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDetailReport.getProperty("AssetDR"))));
			ADetailReportbutton.click();
			Thread.sleep(3000);

			// Click On Asset DetailReport Group

			String drpDwnArrow = "//input[@name='productname']/following::span/img[2]";
			String itemInputBoxLocator = "//input[@name='productname']";
			String itemName = "Computer";
			String rslTable = "//form[@id='designpanelpreview']/following::div[22]/following::div[5]/table";

			Utilities.selectItemfromDropDown(drpDwnArrow, itemInputBoxLocator, itemName, rslTable, driver);
			Thread.sleep(3000);

			// Click On Asset Detail Report Fetch button

			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDetailReport.getProperty("Fetch"))));
			Search.click();
			Thread.sleep(2000);

			// Click On Asset Net Book Value

			WebElement NBV = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetDetailReport.getProperty("NetBookValue"))));
			String ActualResultNBV = NBV.getText();
			Thread.sleep(2000);

			String ExpectedResultNBV = "SGD 558.33";

			System.out.println("*********Net Book Value Expected Result = " + ExpectedResultNBV + " **********");
			System.out.println("*********Net Book Value Actual Result =  " + ActualResultNBV + "    ************");

			if (ExpectedResultNBV.equals(ExpectedResultNBV) == ActualResultNBV.equalsIgnoreCase(ActualResultNBV)) {
				System.out.println("Matched Net Book Value [" + ExpectedResultNBV + "]  [" + ActualResultNBV + "]");

			} else {
				System.out.println("****FAILED to Matched Net Book Value ************");

			}

			// Click On Asset Depreciation Value

			WebElement D1 = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetDetailReport.getProperty("Depreciation"))));
			String ActualResultDV = D1.getText();
			Thread.sleep(2000);

			String ExpectedResultDV = "SGD 41.67";

			System.out.println(
					"*********Depreciation - Current Dep Expected Result = " + ExpectedResultDV + " **********");
			System.out.println(
					"*********Depreciation - Current Dep Actual Result =  " + ActualResultDV + "    ************");

			if (ExpectedResultDV.equalsIgnoreCase(ExpectedResultDV) == ActualResultDV
					.equalsIgnoreCase(ActualResultDV)) {
				System.out.println(
						"Matched Depreciation - Current Dep  [" + ExpectedResultDV + "]  [" + ActualResultDV + "]");

			} else {
				System.out.println("****FAILED to Matched Depreciation - Current Dep  ************");

			}

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ********************** Export Files ********************

	public void Export_AssetADR(String buttonName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);

			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String ExportButtonName = "Export";
			String reportIcon = "//span[text()='Asset Details Report']";
			String waitForQuickSearch = "//button[text()='Fetch']";
			String closeReportPage = "//li[@id='as__fixedAssetDetailsReportTab']/a[1]";
			// String Export="Export";

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

				Utilities.clickButton(ExportButtonName, 2000, driver);
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

				WebElement Print = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Print"))));
				Print.click();
				Thread.sleep(3000);

				WebElement Print1 = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Print1"))));
				Print1.click();
				Thread.sleep(3000);

				// Utilities.clickButton("Print", 3000, driver);
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
						"* * * * * * EXPORT for module [Asset Detail Report] completed successfully * * * * * * *");
			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				noData.printStackTrace();
			}
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [Asset Detail Report] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}
	}

	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;

	}

}
