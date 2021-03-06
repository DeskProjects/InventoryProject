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

public class UpdateSchedule {

	public void Update_AssetMaintenance(String buttonName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			// Object Repository for Update Asset Maintenance

			Properties AssetM = Utilities.fetchProValue("OR_UpdateSchedule.properties");
			Thread.sleep(2000);

			WebDriverWait AMwait = new WebDriverWait(driver, 30);

			// Click On Update Asset Maintenance Icon

			WebElement AMbutton = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("ClickOnUAM"))));
			AMbutton.click();
			Thread.sleep(1000);

			// Click On Fetch Button

			WebElement AFetch = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("Fetch"))));
			AFetch.click();
			Thread.sleep(1000);

			// Click On Checkbox Button

			WebElement ACheckbox = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("Checkbox"))));
			ACheckbox.click();
			Thread.sleep(1000);

			// Click On Update Schedules Button

			WebElement Update = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("UpdateSchedules"))));
			Update.click();
			Thread.sleep(1000);

			// Click On Update Fetch Button

			WebElement AFetchB = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("FetchB"))));
			AFetchB.click();
			Thread.sleep(1000);

			// Click On View Button

			WebElement View = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("View"))));
			View.click();
			Thread.sleep(1000);

			// Enter Work Order Number

			WebElement WON = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("WorkOrderNumber"))));
			WON.sendKeys("WON2000");
			Thread.sleep(1000);

			// Select Work Order Date

			Utilities.HoverandClick(AssetM.getProperty("WODate"), driver);
			Utilities.HoverandClick(AssetM.getProperty("selectToday"), driver);
			Thread.sleep(1000);

			// Enter Assigned To

			Utilities.enterTextandSelect("Aklesh", "//input[@name='assetGroupName']/following::div[4]/input[2]",
					driver);

			// Enter Product ID

			Utilities.HoverandClick(AssetM.getProperty("ProductID"), driver);
			Utilities.enterTextandSelect("P1", "//input[@name='pid']", driver);
			WON.click();
			Thread.sleep(2000);

			// Enter Asset Quantity First

			Utilities.HoverandClick(AssetM.getProperty("Quantity"), driver);
			Utilities.enter_LinelabelAmount("1", driver);
			WON.click();
			Thread.sleep(2000);

			// Enter Asset Unit Price First

			Utilities.HoverandClick(AssetM.getProperty("UnitPrice"), driver);
			Utilities.enter_LinelabelAmount("100", driver);
			WON.click();
			Thread.sleep(1000);

			// Click On Save Button

			WebElement Save = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			// Click On Yes Button

			WebElement Yes = AMwait.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1000);

			// Click On Ok Button

			WebElement OK = AMwait.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}
	// ********************** Export Files ********************

	public void Export_UpdateSchedule(String buttonName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);

			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String ExportButtonName = "Export";
			String reportIcon = "//span[text()='Update Asset Maintenance Schedules']";
			String waitForQuickSearch = "//button[text()='Fetch']";
			String closeReportPage = "//li[@id='as__assetAssetDetailsTabForMaintenanceUpdate']/a[1]";
			// String Export="Export";

			if (buttonName.equalsIgnoreCase("Export List")) {
				ExportButtonName = "Export List";
			} else if (buttonName.equalsIgnoreCase("Export")) {
				ExportButtonName = "Export ";
			}

			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(reportIcon)));
			element.click();
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

				// print HTML

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
						"* * * * * * EXPORT for module [Update Schedule Maintenance] completed successfully * * * * * * *");
			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				noData.printStackTrace();
			}
		}

		catch (Exception EX) {
			System.out.println(
					"* * * * * * !!!!!!! EXPORT for [Update Schedule Maintenance] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}
	}

	public static String button_path(String BtnName) {
		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;
	}
}