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

public class AssetDepreciationDetailReport {
	public void Generate_AssetDepreciationDetailReport(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			String documentid1 = "ADepreciationDetailReport" + documentid;

			// Asset Depreciation Detail Report

			Properties AssetDetailReport = Utilities.fetchProValue("OR_AssetDepreciationDetailReport.properties");
			Thread.sleep(2000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Depreciation Detail Report

			WebElement ADetailReportbutton = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetDetailReport.getProperty("ClickOnADR"))));
			ADetailReportbutton.click();
			Thread.sleep(3000);

			/*
			 * // Click On Asset Menu
			 * 
			 * WebElement AMenu =
			 * AGwait.until(ExpectedConditions.elementToBeClickable(
			 * By.xpath(AssetDetailReport.getProperty("ClikOnMenu")))); AMenu
			 * .click(); Thread.sleep(3000);
			 */

			// Click On Asset Depreciation Detail Group

			String drpDwnArrow = "//input[@name='productname']/following::span/img[2]";
			String itemInputBoxLocator = "//input[@name='productname']";
			String itemName = "Computer";
			String rslTable = "//form[@id='designpanelpreview']/following::div[22]/following::div[5]/table";

			Utilities.selectItemfromDropDown(drpDwnArrow, itemInputBoxLocator, itemName, rslTable, driver);
			Thread.sleep(4000);

			// Click On Asset Depreciation Detail Fetch button

			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDetailReport.getProperty("Fetch"))));
			Search.click();
			Thread.sleep(2000);

			// Click On Current Asset Value

			WebElement CAV = AGwait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(AssetDetailReport.getProperty("CurrentAssetValue"))));
			String ActualResultCAV = CAV.getText();
			Thread.sleep(2000);

			String ExpectedResultCAV = "SGD 600";

			System.out.println("**********Current Asset Value Result ***********");
			System.out.println("*********Current Asset Expected Result = " + ExpectedResultCAV + "**********");
			System.out.println("*********Current Asset Value Actual Result =  " + ActualResultCAV + "    ************");

			if (ExpectedResultCAV.equals(ExpectedResultCAV) == ActualResultCAV.equalsIgnoreCase(ActualResultCAV)) {

				System.out
						.println("Matched Current Asset Value [" + ExpectedResultCAV + "]  [" + ActualResultCAV + "]");

			} else {
				System.out.println("****FAILED to Matched Current Asset Value ************");

			}

			// Click On Asset Net Book Value

			WebElement NBV = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetDetailReport.getProperty("NetBookValue"))));
			String ActualResultNBV = NBV.getText();
			Thread.sleep(2000);

			String ExpectedResultNBV = "SGD 558.33";

			System.out.println("*********Net Book Value Result  **********");
			System.out.println("*********Net Book Value Expected Result = " + ExpectedResultNBV + " **********");
			System.out.println("*********Net Book Value Actual Result =  " + ActualResultNBV + "    ************");

			if (ExpectedResultNBV.equals(ExpectedResultNBV) == ActualResultNBV.equalsIgnoreCase(ActualResultNBV)) {

				System.out.println("Matched Net Book Value [" + ExpectedResultNBV + "]  [" + ActualResultNBV + "]");

			} else {
				System.out.println("****FAILED to Matched Net Book Value ************");

			}

			// Click On Asset Depreciation for the year

			WebElement DY = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetDetailReport.getProperty("Depreciation"))));
			String ActualResultDY = DY.getText();
			Thread.sleep(2000);

			String ExpectedResultDY = "SGD 41.67";

			System.out.println("*********Depreciation for the year Result  ************");
			System.out.println(
					"*********Depreciation for the year Expected Result = " + ExpectedResultDY + " **********");
			System.out.println(
					"*********Depreciation for the year Actual Result =  " + ActualResultDY + "    ************");

			if (ExpectedResultDY.equalsIgnoreCase(ExpectedResultDY) == ActualResultDY
					.equalsIgnoreCase(ActualResultDY)) {
				System.out.println(
						"Matched Depreciation for the year  [" + ExpectedResultDY + "]  [" + ActualResultDY + "]");

			} else {
				System.out.println("****FAILED to Matched Depreciation for the year  ************");

			}

			// Click On Asset Depreciation (Month)

			WebElement DM = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDetailReport.getProperty("Month"))));
			String ActualResultDM = DM.getText();
			Thread.sleep(2000);

			String ExpectedResultDM = "SGD 41.67";

			System.out.println("*********Depreciation for one Month Result**********");
			System.out.println(
					"*********Depreciation for one Month Expected Result = " + ExpectedResultDM + " **********");
			System.out.println(
					"*********Depreciation for one Month Actual Result =  " + ActualResultDM + "    ************");

			if (ExpectedResultDM.equalsIgnoreCase(ExpectedResultDM) == ActualResultDM
					.equalsIgnoreCase(ActualResultDM)) {
				System.out.println(
						"Matched Depreciation for one Month  [" + ExpectedResultDM + "]  [" + ActualResultDM + "]");

			} else {
				System.out.println("****FAILED to Matched Depreciation for one Month  ************");

			}

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ********************** Export Files ********************

	public void Export_AssetADDR(String buttonName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);

			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String ExportButtonName = "Export";
			String reportIcon = "//*[text()='Asset Depreciation Details Report']";
			String waitForQuickSearch = "//button[text()='Fetch']";
			String closeReportPage = "//li[@id='as__fixedAssetDepreciationDetailsReportTab']/a[1]";
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

			/*
			 * //Select From Date
			 * 
			 * Utilities.HoverandClick(pro.getProperty("fromIcon"), driver);
			 * Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
			 * Thread.sleep(1000);
			 * 
			 * //Select To Date
			 * 
			 * Utilities.HoverandClick(pro.getProperty("toIcon"), driver);
			 * Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
			 * Thread.sleep(1000);
			 */

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

				/*
				 * Utilities.clickButton(ExportButtonName, 500, driver);
				 * parentWindow=driver.getWindowHandle();
				 * Utilities.HoverandClick(pro.getProperty("Print"), driver);
				 */

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
						"* * * * * * EXPORT for module [Asset Depreciation Detail Report] completed successfully * * * * * * *");
			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				noData.printStackTrace();
			}
		}

		catch (Exception EX) {
			System.out.println(
					"* * * * * * !!!!!!! EXPORT for [Asset Depreciation Detail Report] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}
	}

	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;

	}

}
