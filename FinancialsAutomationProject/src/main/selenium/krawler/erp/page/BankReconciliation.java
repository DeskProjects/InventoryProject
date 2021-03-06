package krawler.erp.page;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.utils.SikulliUtil;

public class BankReconciliation {

	// ************************************* [Report Loading]
	// ********************************************

	public static void validate_BankReconciliation(String BankName, String StartDate, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_BankReconciliation.properties");
			Properties pro2 = Utilities.fetchProValue("OR_ExportFunction.properties");

			Utilities.enableExpander(driver);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			Utilities.waitandClick(pro.getProperty("expIconLink"), driver);
			Utilities.waitandClick(pro.getProperty("entryExpand"), driver);
			Thread.sleep(1500);

			WebElement BankReconciliationIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("BankReconciliation"))));
			BankReconciliationIcon.click();
			Thread.sleep(2000);

			Utilities.enterTextInDropDown(BankName, pro.getProperty("BankName"), driver);

			Utilities.enterTextInDropDown(StartDate, pro.getProperty("startDate"), driver);

			WebElement statementDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("statementDate"))));
			statementDate.click();
			Thread.sleep(1000);

			statementDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("TodayStatementDay"))));
			statementDate.click();
			Thread.sleep(1000);

			Utilities.clickButton("Continue", 500, driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckforChecksPayments"), "uncheck", driver);

			WebElement doccheckforChecksPayments = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("DocumentCheckforChecksPayments"))));

			try {
				if (doccheckforChecksPayments.isDisplayed()) {
					Thread.sleep(2000);
					Utilities.report_ScreenShot("Bank Reconciliation Report", driver);
					System.out.println("**** Bank Reconciliation Report is LAODED from [" + StartDate + "] To ["
							+ Utilities.currentDate("dd-MM-yyyy") + "] **** !!!!!! ");

					// Export Excel file
					Utilities.clickButton("Export to XLS File", 500, driver);
					Utilities.HoverandClick(pro2.getProperty("WindowExportBtn"), driver);
					Thread.sleep(1000);
					SikulliUtil.sikulli_waitClick(driver, "XLStype");
					SikulliUtil.sikulli_waitClick(driver, "ClsX");
					Thread.sleep(500);
					System.out.println("* * * * * * EXPORT for [.Excel] completed successfully * * * * * * *");

					// Export PDF files
					String XpathOfExportPDF = "//div[@id='reconciliationledger']/div/div/div/div/div[3]//td[7]//button[text()='Export PDF']";

					Utilities.HoverandClick(XpathOfExportPDF, driver);
					List<WebElement> typesOfPdf = driver.findElements(
							By.xpath("//div[@class='x-layer x-menu' and contains(@style,'visible')]/ul/li/a"));
					Utilities.HoverandClick(XpathOfExportPDF, driver);

					String parentWindow = driver.getWindowHandle();

					for (int i = 1; i <= typesOfPdf.size(); i++) {
						Utilities.HoverandClick(XpathOfExportPDF, driver);
						WebElement pdf = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//div[@class='x-layer x-menu' and contains(@style,'visible')]/ul/li[" + i + "]/a")));
						pdf.click();
						SikulliUtil.sikulli_waitClick(driver, "PDFtype");
						Thread.sleep(1500);

						Set s = driver.getWindowHandles(); // this method will
															// gives you the
															// handles of all
															// opened windows
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
						Utilities.sikuliHandleExport(ExportFunction.button_path("BankReco"));
					}
					System.out.println("* * * * * * EXPORT for all [PDF] completed successfully * * * * * * *");
					System.out.println(
							"* * * * * * EXPORT for [Bank Reconciliation Report] completed successfully * * * * * * *");
				}
			} catch (Exception reportNot) {
				System.out.println("**** Bank Reconciliation Report is NOT LAODED (wait > 60 sec) **** ");
				reportNot.printStackTrace();
			}

			Utilities.clickButton("View Reconciled Report", 1000, driver);

			/*
			 * WebElement
			 * doccheckforChecksPayments2=wait.until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(pro.getProperty(
			 * "DocumentCheckforChecksPayments2")))); try{
			 * if(doccheckforChecksPayments2.isDisplayed()){ Thread.sleep(2000);
			 * Utilities.report_ScreenShot("Bank Reconciled Report", driver);
			 * System.out.println("**** Bank Reconciled Report is LAODED from ["
			 * + StartDate +"] To ["+ Utilities.currentDate()
			 * +"] **** !!!!!! ");
			 * 
			 * String
			 * XpathOfExcel="//div[@id='reconciliationledgerReport']/div/div/div/div/div[3]//td[10]//button[text()='Export to XLS File']"
			 * ; Utilities.HoverandClick(XpathOfExcel, driver);
			 * Utilities.HoverandClick(pro2.getProperty("WindowExportBtn"),
			 * driver); Thread.sleep(1000);
			 * 
			 * SikulliUtil.sikulli_waitClick(driver, "XLStype");
			 * SikulliUtil.sikulli_waitClick(driver, "ClsX"); Thread.sleep(500);
			 * System.out.
			 * println("* * * * * * EXPORT for [Bank Reconciled Report .Excel] completed successfully * * * * * * *"
			 * );
			 * 
			 * //pdf String parentWindow=driver.getWindowHandle(); String
			 * xpathPdf=
			 * "//div[@id='reconciliationledgerReport']/div/div/div/div/div[3]//td[7]";
			 * Utilities.HoverandClick(xpathPdf, driver);
			 * Utilities.HoverandClick(pro.getProperty("PDFwithMemoDetails"),
			 * driver); Thread.sleep(5000);
			 * Utilities.sikuliHandleExport(ExportFunction.button_path(
			 * "PDFtype") ); Thread.sleep(1500);
			 * 
			 * Set s=driver.getWindowHandles(); //this method will gives you the
			 * handles of all opened windows Iterator ite=s.iterator();
			 * 
			 * while(ite.hasNext()) { String childWindow=ite.next().toString();
			 * if(!childWindow.contains(parentWindow)) {
			 * driver.switchTo().window(childWindow); Thread.sleep(1000);
			 * System.out.
			 * println("* * * * * * EXPORT for [Bank Reconciled Report PDF] completed successfully * * * * * * *"
			 * ); driver.close(); driver.switchTo().window(parentWindow);
			 * Thread.sleep(1000); } }
			 * 
			 * } } catch(Exception loadingTimout){ System.out.
			 * println("**** Bank Reconciled Report is NOT LAODED (wait > 60 sec) **** "
			 * ); loadingTimout.printStackTrace(); }
			 */
			Utilities.click_element(pro.getProperty("CloseReconciledReport"), driver);
			Utilities.click_element(pro.getProperty("CloseReconcilationReport"), driver);

		} catch (Exception EX) {
			System.out.println("***************** Test case [Bank Reconcilation] is FAILED ******************* ");
			Utilities.handleError(EX, driver);
		}
	}

}
