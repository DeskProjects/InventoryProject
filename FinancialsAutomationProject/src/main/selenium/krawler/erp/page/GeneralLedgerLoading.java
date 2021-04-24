package krawler.erp.page;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.utils.SikulliUtil;

public class GeneralLedgerLoading {

	// ************************ General Ledger Loading with Expander
	// **************************************

	public static void validate_GeneralLedgerLoading(WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_GeneralLedgerLoading.properties");

			Utilities.waitandClick(pro.getProperty("LedgerIcon"), driver);
			Thread.sleep(1000);
			Utilities.clickButton("Fetch", 500, driver);
			try {
				Utilities.isLoadingDisappear(120, driver);
				// Utilities.clickCheckBox(pro.getProperty("DocCheckBox"),
				// "uncheck", driver);
				Utilities.report_ScreenShot("General Ledger", driver);
				Export_GeneralLedger(driver);
				System.out.println(
						"***************** Test case [General Ledger Loading with Expander] is PASS ******************* ");
			} catch (Exception LoadFail) {
				LoadFail.getMessage();
				LoadFail.printStackTrace();
				System.out.println(
						"***************** Test case [General Ledger Loading with Expander] is FAILED ******************* ");
			}
			Utilities.HoverandClick(pro.getProperty("ClsButton"), driver);
		} catch (Exception EX) {
			System.out.println(
					"***************** Test case [General Ledger Loading with Expander] is FAILED ******************* ");
			Utilities.handleError(EX, driver);
		}
	}

	// ************************ Export Ledger
	// *********************************************

	public static void Export_GeneralLedger(WebDriver driver) throws InterruptedException, AWTException, IOException {
		/*
		 * 
		 * try { Properties pro =
		 * Utilities.fetchProValue("OR_GeneralLedgerLoading.properties");
		 * Properties pro2 =
		 * Utilities.fetchProValue("OR_ExportFunction.properties");
		 * 
		 * 
		 * //export CSV String
		 * CSVtypes[]={pro.getProperty("CSVFileDetails"),pro.getProperty(
		 * "CSVFileSummary")};
		 * 
		 * for(int i=0; i<CSVtypes.length; i++){
		 * Utilities.clickButton("Export ", 500, driver);
		 * Utilities.justHover(pro2.getProperty("ExportToCSV"), driver);
		 * Utilities.HoverandClick(CSVtypes[i], driver);
		 * if(CSVtypes[i].contains("Summary")){
		 * Utilities.HoverandClick(pro2.getProperty("WindowExportBtn"), driver);
		 * } SikulliUtil.sikulli_waitClick(driver, "CSVtype"); //
		 * SikulliUtil.sikulli_waitClick(driver, "CloseButton");
		 * SikulliUtil.sikulli_waitClick(driver, "ClsX"); } System.out.
		 * println("* * * * * * EXPORT for [.CSV] completed successfully * * * * * * *"
		 * );
		 * 
		 * //export Excel String
		 * XLStypes[]={pro.getProperty("XLSFileDetails"),pro.getProperty(
		 * "XLSFileSummary")}; for(int i=0; i<XLStypes.length; i++){
		 * Utilities.clickButton("Export ", 500, driver);
		 * Utilities.justHover(pro2.getProperty("ExportToExcel"), driver);
		 * Utilities.HoverandClick(XLStypes[i], driver);
		 * if(XLStypes[i].contains("Summary")){
		 * Utilities.HoverandClick(pro2.getProperty("WindowExportBtn"), driver);
		 * } SikulliUtil.sikulli_waitClick(driver, "JEExcel"); //
		 * SikulliUtil.sikulli_waitClick(driver, "CloseButton");
		 * SikulliUtil.sikulli_waitClick(driver, "ClsX"); } System.out.
		 * println("* * * * * * EXPORT for [Excel] completed successfully * * * * * * *"
		 * );
		 * 
		 * //export Excel String
		 * PDFtypes[]={pro.getProperty("PDFDetails"),pro.getProperty(
		 * "PDFSummary")}; String parentWindow=driver.getWindowHandle();
		 * 
		 * for(int i=0; i<PDFtypes.length; i++){
		 * Utilities.clickButton("Export ", 500, driver);
		 * Utilities.justHover(PDFtypes[i], driver);
		 * Utilities.HoverandClick(pro.getProperty("PortraitPDF"), driver);
		 * 
		 * SikulliUtil.sikulli_waitClick(driver, "PDFtype"); Thread.sleep(1500);
		 * 
		 * Set s=driver.getWindowHandles(); //this method will gives you the
		 * handles of all opened windows Iterator ite=s.iterator();
		 * 
		 * while(ite.hasNext()) { String childWindow=ite.next().toString();
		 * if(!childWindow.contains(parentWindow)) {
		 * driver.switchTo().window(childWindow); Thread.sleep(1000);
		 * System.out.
		 * println("* * * * * * EXPORT for [PDF] completed successfully * * * * * * *"
		 * ); driver.close(); driver.switchTo().window(parentWindow);
		 * Thread.sleep(1000); } } SikulliUtil.sikulli_waitClick(driver,
		 * "GLicon"); } System.out.
		 * println("*******> > >  EXPORT LEDGER COMPLETED < < <***************"
		 * );
		 * 
		 * } catch(Exception EX) { System.out.
		 * println("***************** Test case [General Ledger Export] is FAILED ******************* "
		 * ); Utilities.handleError(EX, driver); }
		 */}

	// ************************ Trial Balance Loading
	// **************************************

	public static void validate_TrialBalanceLoading(WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_GeneralLedgerLoading.properties");

			Utilities.waitandClick(pro.getProperty("TrialBalanceIcon"), driver);
			Utilities.disableExpander(driver);
			Utilities.clickButton("Fetch", 1000, driver);
			try {
				Utilities.isLoadingDisappear(120, driver);
				System.out.println("***************** Test case [Trial Balance Loading] is PASS ******************* ");
				Utilities.report_ScreenShot("Trial Balance", driver);
			} catch (Exception LoadFail) {
				LoadFail.getMessage();
				System.out
						.println("***************** Test case [Trial Balance Loading] is FAILED ******************* ");
			}
			Utilities.waitandClick(pro.getProperty("ClsButton"), driver);

		} catch (Exception EX) {
			System.out.println("***************** Test case [Trial Balance Loading] is FAILED ******************* ");
			Utilities.handleError(EX, driver);
		}

	}

	// ******************** Trial Balance Loading EXPORT
	// ***********************************

	public static void Export_TrialBalanceReport(WebDriver driver) throws Exception {
		/*
		 * { Properties pro =
		 * Utilities.fetchProValue("OR_ExportFunction.properties");
		 * WebDriverWait wait = new WebDriverWait(driver, 60);
		 * 
		 * String ExportButtonName="Export "; Properties pro2 =
		 * Utilities.fetchProValue("OR_GeneralLedgerLoading.properties"); String
		 * reportIcon= pro2.getProperty("TrialBalanceIcon"); String
		 * waitForQuickSearch= pro2.getProperty("TrialDocCheckBox"); String
		 * closeReportPage=pro2.getProperty("ClsButton");
		 * 
		 * try {
		 * 
		 * WebElement element
		 * =wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * reportIcon))); element.click(); Thread.sleep(1000);
		 * 
		 * //select from date
		 * Utilities.HoverandClick(pro.getProperty("fromIcon"), driver);
		 * Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
		 * Thread.sleep(1000);
		 * 
		 * //select To date Utilities.HoverandClick(pro.getProperty("toIcon"),
		 * driver); Utilities.HoverandClick(pro.getProperty("selectToday"),
		 * driver); Thread.sleep(1000);
		 * 
		 * Utilities.clickButton("Fetch", 500, driver);
		 * 
		 * try { // WebElement resultFound=new WebDriverWait(driver,
		 * 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * waitForQuickSearch))); Thread.sleep(1000);
		 * clickCheckBoxExport(waitForQuickSearch, "uncheck" , driver);
		 * 
		 * Utilities.clickButton(ExportButtonName, 500, driver);
		 * 
		 * Utilities.HoverandClick(pro.getProperty("ExportToCSV"), driver);
		 * 
		 * element
		 * =wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.
		 * getProperty("WindowExportBtn")))); element.click();
		 * Thread.sleep(3000);
		 * 
		 * Utilities.sikuliHandleExport(button_path("CSVtype") );
		 * Thread.sleep(1500); //
		 * Utilities.sikuliHandleExport(button_path("CloseButton") );
		 * Thread.sleep(1000); Utilities.sikuliHandleExport(button_path("ClsX")
		 * );
		 * 
		 * System.out.
		 * println("* * * * * * EXPORT for [.CSV] completed successfully * * * * * * *"
		 * ); Thread.sleep(1500);
		 * 
		 * // PDF Export Trial Balance Seperate
		 * Utilities.clickButton("Export Trial Balance", 500, driver);
		 * 
		 * String parentWindow=driver.getWindowHandle();
		 * Utilities.HoverandClick(pro.getProperty("JEPortrait"), driver);
		 * Thread.sleep(3000);
		 * 
		 * Utilities.sikuliHandleExport(button_path("PDFtype") );
		 * Thread.sleep(1500);
		 * 
		 * Set s=driver.getWindowHandles(); //this method will gives you the
		 * handles of all opened windows Iterator ite=s.iterator();
		 * 
		 * while(ite.hasNext()) { String childWindow=ite.next().toString();
		 * if(!childWindow.contains(parentWindow)) {
		 * driver.switchTo().window(childWindow); Thread.sleep(1000);
		 * System.out.
		 * println("* * * * * * EXPORT for [PDF] completed successfully * * * * * * *"
		 * ); driver.close(); driver.switchTo().window(parentWindow);
		 * Thread.sleep(1000); } }
		 * 
		 * // Excel Utilities.clickButton(ExportButtonName, 500, driver);
		 * Utilities.HoverandClick(pro.getProperty("ExportToExcel"), driver);
		 * 
		 * element
		 * =wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.
		 * getProperty("WindowExportBtn")))); element.click();
		 * Thread.sleep(3000);
		 * 
		 * Utilities.sikuliHandleExport(button_path("XLStype") );
		 * Thread.sleep(1500); //
		 * Utilities.sikuliHandleExport(button_path("CloseButton") );
		 * Thread.sleep(1000); Utilities.sikuliHandleExport(button_path("ClsX")
		 * ); System.out.
		 * println("* * * * * * EXPORT for [.Excel] completed successfully * * * * * * *"
		 * ); Thread.sleep(1500);
		 * 
		 * // PDF Utilities.clickButton(ExportButtonName, 500, driver);
		 * 
		 * parentWindow=driver.getWindowHandle();
		 * Utilities.HoverandClick(pro.getProperty("ExportToPDF"), driver);
		 * element
		 * =wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.
		 * getProperty("pdfTemplate")))); element.click(); Thread.sleep(1000);
		 * 
		 * element
		 * =wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.
		 * getProperty("WindowExportBtn")))); element.click();
		 * Thread.sleep(2000);
		 * 
		 * Utilities.sikuliHandleExport(button_path("PDFtype") );
		 * Thread.sleep(1500);
		 * 
		 * s=driver.getWindowHandles(); //this method will gives you the handles
		 * of all opened windows ite=s.iterator();
		 * 
		 * while(ite.hasNext()) { String childWindow=ite.next().toString();
		 * if(!childWindow.contains(parentWindow)) {
		 * driver.switchTo().window(childWindow); Thread.sleep(1000);
		 * System.out.
		 * println("* * * * * * EXPORT for [PDF] completed successfully * * * * * * *"
		 * ); driver.close(); driver.switchTo().window(parentWindow);
		 * Thread.sleep(1000); } }
		 * 
		 * element =wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * closeReportPage))); element.click(); System.out.
		 * println("* * * * * * EXPORT for module [Trial Balance Report] completed successfully * * * * * * *"
		 * );
		 * 
		 * }
		 * 
		 * catch(Exception noData){ System.out.
		 * println("* * * * * * Today's data are Not Present ! ! ! * * * * * * "
		 * ); Utilities.handleError(noData, driver); } }
		 * 
		 * catch (Exception EX) { System.out.
		 * println("* * * * * * !!!!!!! EXPORT for [Trial Balance Report] FAILED !!!!!! * * * * * * *"
		 * ); Utilities.handleError(EX, driver); }
		 * 
		 * }
		 * 
		 */}

	// get button path for Sikulli
	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;

	}

	// Check or Uncheck checkBox button
	public static void clickCheckBoxExport(String checkBoxXpath, String chkOrUnchk, WebDriver driver)
			throws InterruptedException {
		WebElement chkBox = new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(checkBoxXpath)));

		if (chkBox.isEnabled()) {
			Thread.sleep(1300);
			if (chkOrUnchk.equalsIgnoreCase("CHECK")) {
				if (!chkBox.isSelected()) {
					chkBox.click();
					Thread.sleep(1500);
				}
			} else if (chkOrUnchk.equalsIgnoreCase("UNCHECK")) {
				if (chkBox.isSelected()) {
					chkBox.click();
					Thread.sleep(1000);
				}
			}
		}
	}
}
