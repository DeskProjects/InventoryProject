package krawler.erp.page;

import static org.testng.Assert.assertEquals;

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

public class AgingReportCustomer {

	public static String summaryGrandTotal = null;

	// ************************ Customer Summery report loading
	// **************************************
	public static void load_Aging_customer(String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{

		try {
			Properties pro = Utilities.fetchProValue("OR_AgingReportCustomer.properties");

			Thread.sleep(1000);
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("viewAgedreceivablesIcon"))));
			driver.findElement(By.xpath(pro.getProperty("viewAgedreceivablesIcon"))).click();
			Utilities.isLoadingDisappear(180, driver);

			Utilities.clickButton("Fetch", 0, driver); // DocumentCheckBox
			Utilities.isLoadingDisappear(180, driver);
			WebElement checkBox = new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentCheckBox"))));

			if (!checkBox.isDisplayed()) {
				System.out.println("NO Vendor present with the name :[" + vendorid + "] Create new !!!!! ");
			} else {
				WebElement arrowToselectAll = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("arrowImg"))));
				Thread.sleep(1000);
				arrowToselectAll.click();
				Thread.sleep(2000);

				WebElement textAll = new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath("//*[contains(@style,'visible')]//div[text()='All']")));
				textAll.click();
				Utilities.isLoadingDisappear(180, driver);

				WebElement grandTotal = new WebDriverWait(driver, 30).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("grandTotalSummary"))));

				String summaryTotal = grandTotal.getText();
				summaryGrandTotal = summaryTotal;
				// summaryGrandTotal=summaryTotal;
				System.out.println("******** Customer aging Summary tab Grand Total is : " + summaryTotal);
				System.out.println("******** Customer aging Summary tab Loaded successfully !! ******* ");
				Utilities.report_ScreenShot("Customer aging Summary", driver);
				Thread.sleep(3000);
			}
		} catch (Exception EX) {
			System.out.println("Customer Report loading Failed for : :  " + vendorid);
			Utilities.handleError(EX, driver);
		}
	}

	// ************************ Customer Report View Print / Export
	// **************************************

	public static void AgingReportView_PrintExportCustomer(String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{
		try {
			Properties pro = Utilities.fetchProValue("OR_AgingReportCustomer.properties");

			Thread.sleep(1000);
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReportViewTab"))));
			driver.findElement(By.xpath(pro.getProperty("ReportViewTab"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("reportTabFetch"))));
			driver.findElement(By.xpath(pro.getProperty("reportTabFetch"))).click();
			Thread.sleep(2000);

			WebElement checkBox = new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("reportDocumentCheckBox"))));

			if (!checkBox.isDisplayed()) {
				System.out.println("NO Customer Aging present with the name :[" + vendorid + "] Create new !!!!! ");
			} else {
				WebElement arrowToselectAll = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("arrowImg2"))));
				Thread.sleep(1000);
				arrowToselectAll.click();
				Thread.sleep(2000);

				WebElement textAll = new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath("//*[contains(@style,'visible')]//div[text()='All']")));
				textAll.click();
				Utilities.isLoadingDisappear(180, driver);

				WebElement grandTotal = new WebDriverWait(driver, 30).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("grandTotalSummary2"))));
				String reportTotal = grandTotal.getText();
				System.out.println("******** Customer Aging Report tab Grand Total is : " + reportTotal);
				System.out.println("******** Customer Aging Report tab Loaded successfully !! ******* ");
				Utilities.report_ScreenShot("Customer aging Report", driver);

				try {
					if (grandTotal.isDisplayed()) {
						assertEquals(summaryGrandTotal, reportTotal);
						System.out.println("Grand total from Summary [" + summaryGrandTotal
								+ "] matched with UI of Grand total of Report [" + reportTotal + "] !!!!!");
					}
				} catch (Exception verificationFail) {
					System.out.println("Grand total from Summary [" + summaryGrandTotal
							+ "] NOT MATCHED with UI of Grand total of Report [" + reportTotal + "] !!!!!");
				}
				// *************** verification Complete
				// **************************************
				String mainWindow = driver.getWindowHandle(); // beforesave
																// button
				/*
				 * WebElement printOption= new
				 * WebDriverWait(driver,30).until(ExpectedConditions.
				 * visibilityOfElementLocated(By.xpath(pro.getProperty(
				 * "PrintBtn")))); printOption.click(); Thread.sleep(1000);
				 * 
				 * WebElement printSelPage= new
				 * WebDriverWait(driver,30).until(ExpectedConditions.
				 * visibilityOfElementLocated(By.xpath(pro.getProperty(
				 * "FramePrintBtn")))); printSelPage.click();
				 * Thread.sleep(5000); //Give More time to load report fully
				 * 
				 * Set s=driver.getWindowHandles(); //this method will gives you
				 * the handles of all opened windows Iterator ite=s.iterator();
				 * 
				 * while(ite.hasNext()) { String
				 * popupHandle=ite.next().toString();
				 * if(!popupHandle.contains(mainWindow)) {
				 * driver.switchTo().window(popupHandle); driver.close();
				 * //close print popup driver.switchTo().window(mainWindow);
				 * Thread.sleep(2000); } } System.out.
				 * println("***** Print test case for Customer Aging Report Successfully Completed !!!!! "
				 * );
				 * 
				 * WebElement exportBtn= new
				 * WebDriverWait(driver,30).until(ExpectedConditions.
				 * visibilityOfElementLocated(By.xpath(pro.getProperty(
				 * "ExportBtn")))); exportBtn.click(); Thread.sleep(1000);
				 * 
				 * WebElement excelExport= new
				 * WebDriverWait(driver,30).until(ExpectedConditions.
				 * visibilityOfElementLocated(By.
				 * xpath("//*[contains(@style,'visible')]//span[text()='Export to Excel File']"
				 * ))); excelExport.click(); Thread.sleep(1000);
				 * 
				 * exportBtn= new
				 * WebDriverWait(driver,30).until(ExpectedConditions.
				 * visibilityOfElementLocated(By.xpath(pro.getProperty(
				 * "FrameExportBtn")))); exportBtn.click(); Thread.sleep(3000);
				 * 
				 * repeat_tryblock(10, "XLStype"); repeat_tryblock(10,
				 * "CloseButton"); repeat_tryblock(10, "ClsX");
				 */
				Utilities.HoverandClick("//*[@id='as__mainAgedRecievable']/a[1]", driver);

				System.out.println("***** EXPORT test case for Customer Aging Report Successfully Completed !!!!! ");

			}
		} catch (Exception EX) {
			System.out.println("Customer Aging Report Failed for : :  " + vendorid);
			Utilities.handleError(EX, driver);
		}
	}

	// get button path for Sikulli
	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;
	}

	public static void repeat_tryblock(int noOftry, String buttonName) {
		boolean success = false;

		for (int num_try = 0; !success && num_try < noOftry; num_try++) {
			try {
				Thread.sleep(2000);
				Utilities.sikuliHandleExport(button_path(buttonName));
				success = true;
			} catch (Exception e) {
				// waitMore
			}
		}
	}

}
