package krawler.erp.FixedAsset;

import java.awt.AWTException;
import java.awt.Robot;
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

public class AssetGroup {

	public void Create_AssetGroup(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {
			documentid = "AGroup" + documentid;

			// Asset Group Utilities
			Properties AssetGroup = Utilities.fetchProValue("OR_AssetsGroup.properties");
			Thread.sleep(2000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Groups Icon

			WebElement AGIcon = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("ClickOnIcon"))));
			AGIcon.click();
			Thread.sleep(2000);

			// Click On Create New button

			WebElement AGCbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("CreateNew"))));
			AGCbutton.click();
			Thread.sleep(2000);

			// Click On NA Sequence Format
			WebElement AGSequence = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("SequenceFormat"))));
			Thread.sleep(3000);
			AGSequence.clear();
			Thread.sleep(3000);
			AGSequence.sendKeys("NA");
			AGSequence.click();
			Thread.sleep(3000);
			AGSequence.sendKeys(Keys.ENTER);

			// Enter Asset Group ID
			WebElement AGID = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("AssetGID"))));
			Thread.sleep(3000);
			AGID.sendKeys(documentid);

			// Enter Asset Group Name
			WebElement AGName = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("AGroupName"))));
			AGName.click();
			Thread.sleep(500);
			AGName.sendKeys(documentid);

			// Enter Asset Description
			WebElement AGDescription = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("ADescription"))));
			AGDescription.click();
			Thread.sleep(500);
			AGDescription.sendKeys("Asset Description");

			// Click On Control Account
			WebElement AGCAccount = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("ControlAccount"))));
			Thread.sleep(500);
			AGCAccount.click();
			Thread.sleep(500);

			// Select Control Account
			WebElement AcccountSelect = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("SelectAccount"))));
			Thread.sleep(500);
			AcccountSelect.click();

			// Click On Depreciation GL(Profit & Loss) Account
			WebElement AGGLAccount = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("DepreciationGL"))));
			Thread.sleep(500);
			AGGLAccount.click();
			Thread.sleep(500);

			// Select Depreciation GL(Profit & Loss) Account
			WebElement GLAcccountSelect = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("SelectAccount1"))));
			Thread.sleep(500);
			GLAcccountSelect.click();

			// Click On Sale Of Assets
			WebElement AGSale = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("SaleOfAssets"))));
			Thread.sleep(1000);
			AGSale.sendKeys("Fixed Asset Depreciation");
			AGSale.sendKeys(Keys.ENTER);

			// Select Provision For Depreciation Account
			WebElement AGProvisionGLSelect = AGwait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(AssetGroup.getProperty("ProvisionForDepreciation"))));
			Thread.sleep(1000);
			AGProvisionGLSelect.sendKeys("Fixed Assets");
			AGProvisionGLSelect.sendKeys(Keys.ENTER);

			// Select Write Of Account
			WebElement AGWriteOf = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("Write-OffAccount"))));
			Thread.sleep(1000);
			AGWriteOf.sendKeys("Fixed Asset Depreciation");
			AGWriteOf.sendKeys(Keys.ENTER);

			// Enter Rate of Depreciation
			WebElement AGRate = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("RateOfDepreciation"))));
			AGRate.click();
			AGRate.clear();
			Thread.sleep(500);
			AGRate.sendKeys("10");

			// Click On Save Button
			WebElement AGSave = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("SaveButton"))));
			AGSave.click();
			Thread.sleep(500);

			// Click On Yes Button
			WebElement AGYes = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("Yes"))));
			AGYes.click();
			Thread.sleep(2000);

			// Click On Ok Button
			WebElement AGOk = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("Ok"))));
			AGOk.click();
			Thread.sleep(3000);

			System.out.println("Asset Group Save Successfully");

		}

		catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}

	/*
	 * //-------------------------------------------------------------- Verify
	 * Asset Group
	 * -------------------------------------------------------------------------
	 * ---
	 * 
	 * public void Verify_AssetGroup (WebDriver driver) throws
	 * InterruptedException, IOException, AWTException { try { Properties
	 * AssetGroup = Utilities.fetchProValue("OR_AssetsGroup.properties");
	 * Thread.sleep(500); WebDriverWait AGwait = new WebDriverWait(driver, 30);
	 * 
	 * //Click on Verify Quick Search WebElement AGSearch =
	 * AGwait.until(ExpectedConditions.elementToBeClickable(
	 * By.xpath(AssetGroup.getProperty("QuickSearch"))));
	 * AGSearch.sendKeys("AGID12345"); Thread.sleep(2000);
	 * 
	 * } catch(Exception EX) { Utilities.handleError(EX, driver); } }
	 */
	// -------------------------------------------------------------- Verify and
	// Edit Asset Group
	// ----------------------------------------------------------------------------

	public void Edit_AssetGroup(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {
			String documentid1 = "AGroup" + documentid;

			Properties AssetGroup = Utilities.fetchProValue("OR_AssetsGroup.properties");
			Thread.sleep(500);
			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click on Quick Search Edit
			WebElement AGSearch = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("QuickSearch"))));
			AGSearch.sendKeys(documentid1);
			Thread.sleep(2000);

			// Click on Check Box
			WebElement AGCheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("CheckBox"))));
			AGCheck.click();
			Thread.sleep(2000);

			// Click On Edit Button

			WebElement AGEbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("Edit"))));
			AGEbutton.click();
			Thread.sleep(500);

			// Enter Asset Description
			WebElement AGDescriptionE = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("ADescription"))));
			AGDescriptionE.click();
			Thread.sleep(500);
			AGDescriptionE.clear();
			AGDescriptionE.sendKeys("Edited Asset Description");
			Thread.sleep(500);

			// Click On Save Button
			WebElement AGSave = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("SaveButton"))));
			AGSave.click();
			Thread.sleep(500);

			// Click On Yes Button
			WebElement AGYes = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("Yes"))));
			AGYes.click();
			Thread.sleep(1000);

			// Click On Ok Button
			WebElement AGOk = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("Ok"))));
			AGOk.click();
			Thread.sleep(3000);

			System.out.println("Asset Group Edited Successfully");

			// --------------------------------------------------------------
			// Delete Asset Group
			// ----------------------------------------------------------------------------

			// Click on Quick Search Delete
			WebElement AGSearch1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("QuickSearch"))));
			AGSearch1.clear();
			AGSearch1.sendKeys(documentid1);
			Thread.sleep(2000);

			// Click on Check Box
			WebElement AGCheck1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("CheckBox"))));
			AGCheck1.click();
			Thread.sleep(2000);

			// Click On Delete Button

			WebElement AGFetch = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("Delete"))));
			AGFetch.click();
			Thread.sleep(500);

			// Click On Waring Delete Ok Button
			WebElement AGOkD = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("Ok"))));
			AGOkD.click();
			Thread.sleep(500);

			// Click On Waring Delete Successfully Save Ok Button
			WebElement AGOkDS = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("Ok"))));
			AGOkDS.click();
			Thread.sleep(3000);

			System.out.println("Asset Group Deleted Successfully");

			// Click On Asset Report Cross Symbol
			WebElement AGCross = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("CrossSymbol"))));
			AGCross.click();
			Thread.sleep(1000);

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ********************** Export Files ********************

	public void Export_AssetGP(String buttonName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);

			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String ExportButtonName = "Export";
			String reportIcon = "//span[text()='Asset Groups']";
			String waitForQuickSearch = "//button[text()='Fetch']";
			String closeReportPage = "//li[@id='as__assetGroupTab']/a[1]";
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

	// *********************************** Fixed Asset New Flow [Create Asset
	// Group] ***********************

	public void Create_NewAssetGroup(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			// Asset Group Utilities
			Properties AssetGroup = Utilities.fetchProValue("OR_AssetsGroup.properties");
			Thread.sleep(2000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Groups Icon

			WebElement AGIcon = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("ClickOnIcon"))));
			AGIcon.click();
			Thread.sleep(2000);

			// Create Multiple Asset Group at a time.

			String AGID[] = { "1000", "2000" };
			String AGName[] = { "Fan", "Chair" };

			for (int i = 0; i < AGID.length; i++) {
				String AssetGroupID = AGName[i] + AGID[i];
				String AssetGroupName = AGName[i];

				// Click On Create New button

				WebElement AGCbutton = AGwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("CreateNew"))));
				AGCbutton.click();
				Thread.sleep(2000);

				// Click On NA Sequence Format
				WebElement AGSequence = AGwait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("SequenceFormat"))));
				Thread.sleep(3000);
				AGSequence.clear();
				Thread.sleep(3000);
				AGSequence.sendKeys("NA");
				AGSequence.click();
				Thread.sleep(3000);
				AGSequence.sendKeys(Keys.ENTER);

				// Enter Asset Group ID
				WebElement AGID1 = AGwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("AssetGID"))));
				Thread.sleep(3000);
				AGID1.sendKeys(AssetGroupID);

				// Enter Asset Group Name
				WebElement Name = AGwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("AGroupName"))));
				Name.click();
				Thread.sleep(500);
				Name.sendKeys(AssetGroupName);

				// Enter Asset Description
				WebElement AGDescription = AGwait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("ADescription"))));
				AGDescription.click();
				Thread.sleep(500);
				AGDescription.sendKeys("Asset Description");

				// Click On Control Account
				WebElement AGCAccount = AGwait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("ControlAccount"))));
				Thread.sleep(500);
				AGCAccount.click();
				Thread.sleep(500);

				// Select Control Account
				WebElement AcccountSelect = AGwait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("SelectAccount"))));
				Thread.sleep(500);
				AcccountSelect.click();

				// Click On Depreciation GL(Profit & Loss) Account
				WebElement AGGLAccount = AGwait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("DepreciationGL"))));
				Thread.sleep(500);
				AGGLAccount.click();
				Thread.sleep(500);

				// Select Depreciation GL(Profit & Loss) Account
				WebElement GLAcccountSelect = AGwait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("SelectAccount1"))));
				Thread.sleep(500);
				GLAcccountSelect.click();

				// Click On Sale Of Assets
				WebElement AGSale = AGwait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("SaleOfAssets"))));
				Thread.sleep(1000);
				AGSale.sendKeys("Fixed Asset Depreciation");
				AGSale.sendKeys(Keys.ENTER);

				// Select Provision For Depreciation Account
				WebElement AGProvisionGLSelect = AGwait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(AssetGroup.getProperty("ProvisionForDepreciation"))));
				Thread.sleep(1000);
				AGProvisionGLSelect.sendKeys("Fixed Assets");
				AGProvisionGLSelect.sendKeys(Keys.ENTER);

				// Select Write Of Account
				WebElement AGWriteOf = AGwait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("Write-OffAccount"))));
				Thread.sleep(1000);
				AGWriteOf.sendKeys("Fixed Asset Depreciation");
				AGWriteOf.sendKeys(Keys.ENTER);

				// Enter Rate of Depreciation
				WebElement AGRate = AGwait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(AssetGroup.getProperty("RateOfDepreciation"))));
				AGRate.click();
				AGRate.clear();
				Thread.sleep(500);
				AGRate.sendKeys("10");

				// Click On Save Button
				WebElement AGSave = AGwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("SaveButton"))));
				AGSave.click();
				Thread.sleep(500);

				// Click On Yes Button
				WebElement AGYes = AGwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("Yes"))));
				AGYes.click();
				Thread.sleep(2000);

				// Click On Ok Button
				WebElement AGOk = AGwait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("Ok"))));
				AGOk.click();
				Thread.sleep(3000);

				System.out.println("Asset Group Save Successfully" + AGName[i] + AGID[i]);

			}
			// Close Asset Group Tab

			WebElement AGCross = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("CrossSymbol"))));
			AGCross.click();
			Thread.sleep(3000);

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}
}
