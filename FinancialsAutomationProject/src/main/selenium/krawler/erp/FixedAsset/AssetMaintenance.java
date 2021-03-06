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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;
import krawler.erp.utils.SikulliUtil;

public class AssetMaintenance {
	public void Create_AssetMaintenance(String buttonName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			// Object Repository for Asset Maintenance

			Properties AssetM = Utilities.fetchProValue("OR_AssetMaintenance.properties");
			Thread.sleep(2000);

			WebDriverWait AMwait = new WebDriverWait(driver, 30);

			// Click On Create Asset Maintenance Icon

			WebElement AMbutton = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("ClickOnAM"))));
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

			// Click On Create Maintenance Schedule Button

			WebElement Create = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("ClickOnCreate"))));
			Create.click();
			Thread.sleep(1000);

			String Mtype[] = { "Regular", "Breakdown" };

			for (int i = 0; i < Mtype.length; i++) {
				String SheduleName = "SheduleName" + Mtype[i];

				// Click On Create New Button

				WebElement CreateN = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("CreateNew"))));
				CreateN.click();
				Thread.sleep(1000);

				// Enter Schedule Name

				WebElement Name = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("SheduleName"))));
				Name.sendKeys(SheduleName);
				Thread.sleep(1000);

				// Enter Schedule Duration

				WebElement Duration = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("Duration"))));
				Duration.sendKeys("3");
				Thread.sleep(1000);

				// Enter Maintenance Type

				Utilities.enterTextandSelect(Mtype[i], "//span[text()='Maintenance Type']/following::div[2]/input[2]",
						driver);

				// Enter Repeat Event in every

				WebElement Repeat = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("Repeat"))));
				Repeat.sendKeys("1");
				Thread.sleep(1000);

				// Enter Total Events

				WebElement Event = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("Event"))));
				Event.sendKeys("2");
				Thread.sleep(1000);

				// Click On Save Button

				WebElement Save = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("Save"))));
				Save.click();
				Thread.sleep(1000);

				// Click On Yes Button

				WebElement Yes = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("Yes"))));
				Yes.click();
				Thread.sleep(1000);

				// Click On Ok Button

				WebElement Ok = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("Ok"))));
				Ok.click();
				Thread.sleep(1000);
			}
			// Click On Checkbox Button

			WebElement ACheckboxE = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("CheckboxE"))));
			ACheckboxE.click();
			Thread.sleep(1000);

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ***********************Edit Asset Maintenance
	// ******************************

	public void Edit_AssetMaintenance(String buttonName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			WebDriverWait AMwait = new WebDriverWait(driver, 30);

			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String ExportButtonName = "Export";
			String waitForQuickSearch = "//button[text()='Fetch']";
			String closeReportPage = "//div[@id='maintenanceSchedulerId']//button[text()='Cancel']";
			String Cancel = "//button[text()='Cancel']";
			String CloseW = "//li[@id='as__assetAssetDetailsTabForMaintenanceCreate']/a[1]";

			// Asset Maintenance

			Properties AssetM = Utilities.fetchProValue("OR_AssetMaintenance.properties");
			Thread.sleep(2000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Create Asset Maintenance Icon

			WebElement AMbutton = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("ClickOnAM"))));
			AMbutton.click();
			Thread.sleep(2000);

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

			// Click On Create Maintenance Schedule Button

			WebElement Create = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("ClickOnCreate"))));
			Create.click();
			Thread.sleep(1000);

			// Click On Create Maintenance Schedule Button

			WebElement CheckboxE = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("CheckboxE"))));
			CheckboxE.click();
			Thread.sleep(1000);

			// Click On Edit Button

			WebElement Edit = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("Edit"))));
			Edit.click();
			Thread.sleep(1000);

			if (buttonName.equalsIgnoreCase("Export List")) {
				ExportButtonName = "Export List";
			} else if (buttonName.equalsIgnoreCase("Export")) {
				ExportButtonName = "Export ";
			}

			WebElement element = AMwait.until(ExpectedConditions.elementToBeClickable(By.xpath(waitForQuickSearch)));

			Thread.sleep(2000);

			// Select From Date

			Utilities.HoverandClick(pro.getProperty("fromIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
			Thread.sleep(1000);

			// Select To Date

			Utilities.HoverandClick(pro.getProperty("toIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
			Thread.sleep(1000);

			// Click On Report Fetch Button

			WebElement FetchR = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("FetchR"))));
			FetchR.click();
			Thread.sleep(1000);

			Utilities.isLoadingDisappear(200, driver);

			try {

				// ******************************** Export CSV File
				// *****************************

				WebElement Export = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("Export"))));
				Export.click();

				Utilities.HoverandClick(pro.getProperty("ExportToCSV"), driver);

				element = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "CSVtype");
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
				System.out.println("* * * * * * EXPORT for [CSV] completed successfully * * * * * * *");
				Thread.sleep(1500);

				// ******************* Export PDF Files ********************

				WebElement ExportPDF = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("Export"))));
				ExportPDF.click();

				String parentWindow = driver.getWindowHandle();
				Utilities.HoverandClick(pro.getProperty("ExportToPDF"), driver);
				element = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("pdfTemplate"))));
				element.click();
				Thread.sleep(1000);
				element = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
				Thread.sleep(1000);

				// SikulliUtil.sikulli_waitClick(driver, "PDFtype");

				Set s = driver.getWindowHandles(); // this method will gives you
													// the handles of all opened
													// windows
				Iterator ite = s.iterator();
				System.out.println("* * * * * * EXPORT for [PDF] completed successfully * * * * * * *");

				/*
				 * while(ite.hasNext()) { String
				 * childWindow=ite.next().toString();
				 * if(!childWindow.contains(parentWindow)) {
				 * driver.switchTo().window(childWindow); Thread.sleep(1000);
				 * System.out.
				 * println("* * * * * * EXPORT for [PDF] completed successfully * * * * * * *"
				 * ); driver.close(); driver.switchTo().window(parentWindow);
				 * Thread.sleep(1000); } }
				 */

				// ************************************ Export Excel Files
				// ***********************************

				WebElement ExportEx = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("Export"))));
				ExportEx.click();

				Utilities.HoverandClick(pro.getProperty("ExportToExcel"), driver);
				Thread.sleep(3500);
				element = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "XLStype");
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
				System.out.println("* * * * * * EXPORT for [Excel] completed successfully * * * * * * *");
				Thread.sleep(1500);

				// ************************** Print To HTML
				// *******************************

				WebElement Print = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AssetPrint"))));
				Print.click();
				Thread.sleep(3000);

				WebElement Print1 = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Print1"))));
				Print1.click();
				Thread.sleep(3000);

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
				Utilities.HoverandClick(Cancel, driver);
				Thread.sleep(2000);
				Utilities.HoverandClick(CloseW, driver);

				System.out.println(
						"* * * * * * EXPORT CSV,Excel,PDF and Print Files are  completed successfully * * * * * * *");
			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				noData.printStackTrace();
			}

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ****************************Export CSV, Excel, PDF and Print
	// Files*************************

	public void Export_AssetM(String buttonName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			WebDriverWait AMwait = new WebDriverWait(driver, 30);

			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String ExportButtonName = "Export";
			String waitForQuickSearch = "//button[text()='Fetch']";
			String closeReportPage = "//li[@id='as__assetAssetDetailsTabForMaintenanceCreate']/a[1]";

			// Asset Maintenance

			Properties AssetM = Utilities.fetchProValue("OR_AssetMaintenance.properties");
			Thread.sleep(2000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Create Asset Maintenance Icon

			WebElement AMbutton = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("ClickOnAM"))));
			AMbutton.click();
			Thread.sleep(2000);

			// Click On Fetch Button

			WebElement AFetch = AMwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetM.getProperty("Fetch"))));
			AFetch.click();
			Thread.sleep(1000);

			if (buttonName.equalsIgnoreCase("Export List")) {
				ExportButtonName = "Export List";
			} else if (buttonName.equalsIgnoreCase("Export")) {
				ExportButtonName = "Export ";
			}

			WebElement element = AMwait.until(ExpectedConditions.elementToBeClickable(By.xpath(waitForQuickSearch)));
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
				element = AMwait
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
				element = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("pdfTemplate"))));
				element.click();
				Thread.sleep(1000);
				element = AMwait
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
				element = AMwait
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

				WebElement Print = AMwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Print"))));
				Print.click();
				Thread.sleep(3000);

				WebElement Print1 = AMwait
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

				System.out.println("* * * * * * EXPORT for module [Asset Group] completed successfully * * * * * * *");
			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				noData.printStackTrace();
			}
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [Asset Group] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}
	}

	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;
	}
}
