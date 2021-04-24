package krawler.erp.FixedAsset;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class AssetOpening {

	public void Create_AssetGroup(String Group, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {
			String documentid1 = "AGroup" + Group;

			// Asset Group Utilities
			Properties AssetGroup = Utilities.fetchProValue("OR_AssetOpening.properties");
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
			AGID.sendKeys(documentid1);

			// Enter Asset Group Name
			WebElement AGName = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("AGroupName"))));
			AGName.click();
			Thread.sleep(500);
			AGName.sendKeys("AssetManagement");

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
			Thread.sleep(2000);

			// Click On Cross Symbol Button
			WebElement AGCross = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGroup.getProperty("CrossSymbol"))));
			Thread.sleep(3000);

			System.out.println("********************Asset Group Save Successfully     " + Group);

		}

		catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}

	// ************************* Create Opening Document for Asset
	// Group*****************************

	public static void Create_OpeningDoument(String Group, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_AssetOpening.properties");
			String documentid1 = "AGroup" + Group;
			String AssetGroupName = Group + "BS Name";

			// Click on "Asset Groups" Icon for create Asset Group
			WebDriverWait wait = new WebDriverWait(driver, 60);
			/*
			 * driver.findElement(By.xpath(pro.getProperty("ClickOnIcon"))).
			 * isEnabled();
			 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
			 * pro.getProperty("ClickOnIcon"))));
			 * 
			 * driver.findElement(By.xpath(pro.getProperty("ClickOnIcon"))).
			 * click(); Thread.sleep(3000);
			 * 
			 * // Click on "Fetch" button for fetching data
			 * driver.findElement(By.xpath(pro.getProperty("FetchBtn"))).click()
			 * ; Thread.sleep(1000);
			 */

			// Search Document in Asset Group Report
			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch1"))));
			search.sendKeys(documentid1);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			// Click on "check box" for record selection
			driver.findElement(By.xpath(pro.getProperty("CheckBoxIcon"))).isEnabled();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("CheckBoxIcon"))));
			driver.findElement(By.xpath(pro.getProperty("CheckBoxIcon"))).click();
			Thread.sleep(3000);

			// Click On "Opening" button to given opening details for Asset
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("OpeningBtn"))));
			driver.findElement(By.xpath(pro.getProperty("OpeningBtn"))).click();
			Thread.sleep(2000);

			// Click On "Create New" button for fill opening document
			// information
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("CreateNewbtnforopening"))));
			driver.findElement(By.xpath(pro.getProperty("CreateNewbtnforopening"))).click();
			Thread.sleep(2000);

			// Fill the information for Opening Asset

			String OpeningDoc = "Opening" + documentid1; // Opening Document
															// declare
			driver.findElement(By.xpath(pro.getProperty("OpeningDocNo"))).sendKeys(OpeningDoc);
			Thread.sleep(1000);
			driver.findElement(By.xpath(pro.getProperty("Openingquant"))).sendKeys("2");
			driver.findElement(By.xpath(pro.getProperty("OepningRate"))).sendKeys("200");
			Thread.sleep(1000);

			// Click On "Asset Detail" button for provide the asset detail
			// information.
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("AssetDetailBtn"))));
			driver.findElement(By.xpath(pro.getProperty("AssetDetailBtn"))).click();
			Thread.sleep(2000);

			// Click On "Asset Detail" button for provide the asset detail
			// information.
			Utilities.clickAndEnterText(documentid1 + "FA1", pro.getProperty("AssetID1"), driver);

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
			Thread.sleep(1000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Utilities.sendText("100");
			Thread.sleep(2000);

			/*
			 * // Click on Serial and Batch's icon
			 * 
			 * Utilities.justHover("//*[@class='pwnd serialNo-gridrow']",
			 * driver); Utilities.HoverandClick(pro.getProperty("SerialIcon"),
			 * driver); Thread.sleep(3000);
			 * 
			 * 
			 * // File the warehouse and batch information
			 * 
			 * driver.findElement(By.xpath(pro.getProperty("WarehouseInf"))).
			 * click(); Thread.sleep(2000);
			 * Utilities.sendText("DS - Default Store"); Thread.sleep(1000);
			 * 
			 * r.keyPress(KeyEvent.VK_ENTER); r.keyRelease(KeyEvent.VK_ENTER);
			 * 
			 * r.keyPress(KeyEvent.VK_TAB); r.keyRelease(KeyEvent.VK_TAB);
			 * 
			 * Thread.sleep(2000); Utilities.sendText("Default Location");
			 * Thread.sleep(1000); r.keyPress(KeyEvent.VK_ENTER);
			 * r.keyRelease(KeyEvent.VK_ENTER);
			 * 
			 * r.keyPress(KeyEvent.VK_TAB); r.keyRelease(KeyEvent.VK_TAB);
			 * 
			 * Utilities.sendText("Batch1"); Thread.sleep(2000);
			 * r.keyPress(KeyEvent.VK_ENTER); r.keyRelease(KeyEvent.VK_ENTER);
			 * Thread.sleep(1000);
			 * 
			 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
			 * pro.getProperty("SerialNumberinfo"))));
			 * driver.findElement(By.xpath(pro.getProperty("SerialNumberinfo")))
			 * .click(); Thread.sleep(1000); Utilities.sendText("S1");
			 * Thread.sleep(1000);
			 * 
			 * 
			 * // Click on Submit button given in Warehouse and Serial
			 * Information window.
			 * driver.findElement(By.xpath(pro.getProperty("Submitbtn"))).click(
			 * ); Thread.sleep(2000);
			 */

			// Again fill the Asset Details and Warehouse information for second
			// asset(second line)
			Utilities.justHover(pro.getProperty("AssetID2"), driver);
			Utilities.clickAndEnterText(documentid1 + "FA2", pro.getProperty("AssetID2"), driver);
			Thread.sleep(1000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			Utilities.sendText("Automation Description2");
			Thread.sleep(2000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Utilities.sendText("100");
			Thread.sleep(2000);

			/*
			 * // Hover for Serial and Batch's second icon
			 * 
			 * Utilities.justHover("//*[@class='pwnd serialNo-gridrow']",
			 * driver); Utilities.HoverandClick(pro.getProperty("SerialIcon2"),
			 * driver); Thread.sleep(3000);
			 * 
			 * 
			 * // File the warehouse and batch information for second Asset
			 * 
			 * driver.findElement(By.xpath(pro.getProperty("WarehouseInf"))).
			 * click(); Thread.sleep(2000);
			 * Utilities.sendText("DS - Default Store"); Thread.sleep(1000);
			 * 
			 * r.keyPress(KeyEvent.VK_ENTER); r.keyRelease(KeyEvent.VK_ENTER);
			 * 
			 * r.keyPress(KeyEvent.VK_TAB); r.keyRelease(KeyEvent.VK_TAB);
			 * 
			 * Thread.sleep(2000); Utilities.sendText("Default Location");
			 * Thread.sleep(1000); r.keyPress(KeyEvent.VK_ENTER);
			 * r.keyRelease(KeyEvent.VK_ENTER);
			 * 
			 * r.keyPress(KeyEvent.VK_TAB); r.keyRelease(KeyEvent.VK_TAB);
			 * 
			 * Utilities.sendText("Batch1"); Thread.sleep(2000);
			 * r.keyPress(KeyEvent.VK_ENTER); r.keyRelease(KeyEvent.VK_ENTER);
			 * Thread.sleep(1000);
			 * 
			 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
			 * pro.getProperty("SerialNumberinfo"))));
			 * driver.findElement(By.xpath(pro.getProperty("SerialNumberinfo")))
			 * .click(); Thread.sleep(1000); Utilities.sendText("S2");
			 * Thread.sleep(1000);
			 * 
			 * 
			 * // Click on Submit button given in Warehouse and Serial
			 * Information window.
			 * driver.findElement(By.xpath(pro.getProperty("Submitbtn"))).click(
			 * ); Thread.sleep(2000);
			 */

			// Click on "Save" button present at Asset Details window.
			driver.findElement(By.xpath(pro.getProperty("SaveInner1"))).click();
			Thread.sleep(1000);

			// Click on "Save" button present at Opening Form of asset.
			Utilities.clickButton("Save", 2000, driver);

			Utilities.clickButton("OK", 1000, driver);

			Utilities.clickButton("Cancel", 3000, driver);

			System.out.println("*****************Asset Opening Document is Created Successfully   " + OpeningDoc);

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ************************** Verification for Asset Opening Transaction

	public void Verification_AssetOpening(String Group, String ExpectedResultTA, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			Properties pro = Utilities.fetchProValue("OR_AssetOpening.properties");
			String documentid1 = "AGroup" + Group;
			String AssetGroupName = Group + "BS Name";

			// Click on "Asset Groups" Icon for create Asset Group
			WebDriverWait wait = new WebDriverWait(driver, 60);

			// Asset Opening Utilities

			Properties AssetGroup = Utilities.fetchProValue("OR_AssetOpening.properties");
			Thread.sleep(500);

			// Click On Asset Groups Icon
			/*
			 * WebElement AGIcon =
			 * wait.until(ExpectedConditions.elementToBeClickable(
			 * By.xpath(AssetGroup.getProperty("ClickOnIcon")))); AGIcon
			 * .click(); Thread.sleep(2000);
			 * 
			 * // Search Document in Asset Group Report
			 * 
			 * WebElement search =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.
			 * getProperty("QuickSearch1")))); search.sendKeys(documentid1);
			 * search.sendKeys(Keys.TAB); Thread.sleep(2000);
			 * 
			 * //Click on "check box" for record selection
			 * driver.findElement(By.xpath(pro.getProperty("CheckBoxIcon"))).
			 * isEnabled();
			 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
			 * pro.getProperty("CheckBoxIcon"))));
			 * driver.findElement(By.xpath(pro.getProperty("CheckBoxIcon"))).
			 * click(); Thread.sleep(3000);
			 */

			// Click On "Opening" button to given opening details for Asset
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("OpeningBtn"))));
			driver.findElement(By.xpath(pro.getProperty("OpeningBtn"))).click();
			Thread.sleep(2000);

			// Verify Asset Opening Quantity

			WebElement TA = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Quantity"))));
			String ActualResultTA = TA.getText();
			Thread.sleep(2000);

			System.out.println("********* Verification Result **********");

			System.out
					.println("*********Asset  Opening Quantity Expected Result = " + ExpectedResultTA + " **********");
			System.out.println("*********Asset  Opening Quantity Result =  " + ActualResultTA + "    ************");

			if (ExpectedResultTA.equals(ActualResultTA)) {
				System.out.println(
						"Matched Asset  Opening Quantity [" + ExpectedResultTA + "]  [" + ActualResultTA + "]");

			} else {
				System.out.println("FAILED to Matched Asset  Opening Quantity [" + ExpectedResultTA + "]  ["
						+ ActualResultTA + "]");

			}

			// Verify Asset Opening Rate

			WebElement TAB = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Rate"))));
			String ActualResultTAB = TAB.getText();
			Thread.sleep(2000);

			String ExpectedResultTAB = "SGD 200.00";

			System.out.println("*********Asset Opening Rate  Expected Result = " + ExpectedResultTAB + " **********");
			System.out.println("*********Asset Opening Rate Actual Result =  " + ActualResultTAB + "    ************");

			if (ExpectedResultTAB.equals(ActualResultTAB)) {
				System.out
						.println("Matched Asset Opening Rate  [" + ExpectedResultTAB + "]  [" + ActualResultTAB + "]");

			} else {
				System.out
						.println("FAILED to Asset Opening Rate [" + ExpectedResultTAB + "]  [" + ActualResultTAB + "]");

			}

			Utilities.clickButton("Cancel", 3000, driver);

			System.out.println(
					"*****************Asset Opening Document Verification  Completed Successfully*******************");

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ***************************** Edit Asset Opening Document
	// ***************************

	public static void Edit_OpeningDoument(String Group, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_AssetOpening.properties");
			String documentid1 = "AGroup" + Group;
			String AssetGroupName = Group + "BS Name";
			String OpeningDoc = "Opening" + documentid1; // Opening Document
															// declare

			// Click on "Asset Groups" Icon for create Asset Group
			WebDriverWait wait = new WebDriverWait(driver, 60);

			// Click On "Opening" button to given opening details for Asset
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("OpeningBtn"))));
			driver.findElement(By.xpath(pro.getProperty("OpeningBtn"))).click();
			Thread.sleep(2000);

			// Click On "Check Box" button for fill opening document information
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("CheckBoxEdit"))));
			driver.findElement(By.xpath(pro.getProperty("CheckBoxEdit"))).click();
			Thread.sleep(2000);

			// Click On "Edit" button for fill opening document information
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("EditBtn"))));
			driver.findElement(By.xpath(pro.getProperty("EditBtn"))).click();
			Thread.sleep(2000);

			// Fill the information for Opening Asset Quantity and Rate

			Utilities.enterTextNormalBox("3", "//input[@id='quantityeditopeningFormIdFAOpeningWindowId']", driver);
			Utilities.enterTextNormalBox("300", "//input[@id='rateeditopeningFormIdFAOpeningWindowId']", driver);

			// Click On "Asset Detail" button for provide the asset detail
			// information.
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("AssetDetailBtn"))));
			driver.findElement(By.xpath(pro.getProperty("AssetDetailBtn"))).click();
			Thread.sleep(2000);

			// Click On "Asset Detail" button for provide the asset detail
			// information.
			Utilities.clickAndEnterText(documentid1 + "FA3", pro.getProperty("AssetID1"), driver);

			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			Utilities.sendText("Automation Description3");
			Thread.sleep(2000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Utilities.sendText("100");
			Thread.sleep(2000);

			/*
			 * // Click on Serial and Batch's icon
			 * Utilities.justHover("//*[@class='pwnd serialNo-gridrow']",
			 * driver); Utilities.HoverandClick(pro.getProperty("SerialIcon"),
			 * driver); Thread.sleep(3000);
			 * 
			 * 
			 * // File the warehouse and batch information
			 * 
			 * driver.findElement(By.xpath(pro.getProperty("WarehouseInf"))).
			 * click(); Thread.sleep(2000);
			 * Utilities.sendText("DS - Default Store"); Thread.sleep(1000);
			 * 
			 * r.keyPress(KeyEvent.VK_ENTER); r.keyRelease(KeyEvent.VK_ENTER);
			 * 
			 * r.keyPress(KeyEvent.VK_TAB); r.keyRelease(KeyEvent.VK_TAB);
			 * 
			 * Thread.sleep(2000); Utilities.sendText("Default Location");
			 * Thread.sleep(1000); r.keyPress(KeyEvent.VK_ENTER);
			 * r.keyRelease(KeyEvent.VK_ENTER);
			 * 
			 * r.keyPress(KeyEvent.VK_TAB); r.keyRelease(KeyEvent.VK_TAB);
			 * 
			 * Utilities.sendText("Batch1"); Thread.sleep(2000);
			 * r.keyPress(KeyEvent.VK_ENTER); r.keyRelease(KeyEvent.VK_ENTER);
			 * Thread.sleep(1000);
			 * 
			 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
			 * pro.getProperty("SerialNumberinfo"))));
			 * driver.findElement(By.xpath(pro.getProperty("SerialNumberinfo")))
			 * .click(); Thread.sleep(1000); Utilities.sendText("S1");
			 * Thread.sleep(1000);
			 * 
			 * 
			 * // Click on Submit button given in Warehouse and Serial
			 * Information window.
			 * driver.findElement(By.xpath(pro.getProperty("Submitbtn"))).click(
			 * ); Thread.sleep(2000);
			 * 
			 * // Again fill the Asset Details and Warehouse information for
			 * second asset(second line)
			 * Utilities.justHover(pro.getProperty("AssetID2"), driver);
			 * Utilities.clickAndEnterText(documentid1+"FA2",
			 * pro.getProperty("AssetID2"), driver); Thread.sleep(1000);
			 * 
			 * r.keyPress(KeyEvent.VK_TAB); r.keyRelease(KeyEvent.VK_TAB);
			 * Thread.sleep(1000);
			 * Utilities.sendText("Automation Description2");
			 * Thread.sleep(2000);
			 * 
			 * r.keyPress(KeyEvent.VK_TAB); r.keyRelease(KeyEvent.VK_TAB);
			 * Thread.sleep(1000);
			 * 
			 * r.keyPress(KeyEvent.VK_TAB); r.keyRelease(KeyEvent.VK_TAB);
			 * Thread.sleep(1000);
			 * 
			 * r.keyPress(KeyEvent.VK_TAB); r.keyRelease(KeyEvent.VK_TAB);
			 * Utilities.sendText("100"); Thread.sleep(2000);
			 * 
			 * 
			 * /* // Hover for Serial and Batch's second icon
			 * Utilities.justHover("//*[@class='pwnd serialNo-gridrow']",
			 * driver); Utilities.HoverandClick(pro.getProperty("SerialIcon2"),
			 * driver); Thread.sleep(3000);
			 * 
			 * 
			 * // File the warehouse and batch information for second Asset
			 * 
			 * driver.findElement(By.xpath(pro.getProperty("WarehouseInf"))).
			 * click(); Thread.sleep(2000);
			 * Utilities.sendText("DS - Default Store"); Thread.sleep(1000);
			 * 
			 * r.keyPress(KeyEvent.VK_ENTER); r.keyRelease(KeyEvent.VK_ENTER);
			 * 
			 * r.keyPress(KeyEvent.VK_TAB); r.keyRelease(KeyEvent.VK_TAB);
			 * 
			 * Thread.sleep(2000); Utilities.sendText("Default Location");
			 * Thread.sleep(1000); r.keyPress(KeyEvent.VK_ENTER);
			 * r.keyRelease(KeyEvent.VK_ENTER);
			 * 
			 * r.keyPress(KeyEvent.VK_TAB); r.keyRelease(KeyEvent.VK_TAB);
			 * 
			 * Utilities.sendText("Batch1"); Thread.sleep(2000);
			 * r.keyPress(KeyEvent.VK_ENTER); r.keyRelease(KeyEvent.VK_ENTER);
			 * Thread.sleep(1000);
			 * 
			 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
			 * pro.getProperty("SerialNumberinfo"))));
			 * driver.findElement(By.xpath(pro.getProperty("SerialNumberinfo")))
			 * .click(); Thread.sleep(1000); Utilities.sendText("S2");
			 * Thread.sleep(1000);
			 * 
			 * 
			 * // Click on Submit button given in Warehouse and Serial
			 * Information window.
			 * driver.findElement(By.xpath(pro.getProperty("Submitbtn"))).click(
			 * ); Thread.sleep(2000);
			 */

			// Click on "Save" button present at Asset Details window.
			driver.findElement(By.xpath(pro.getProperty("SaveInner1"))).click();
			Thread.sleep(1000);

			// Click on "Save" button present at Opening Form of asset.
			Utilities.clickButton("Save", 2000, driver);

			Utilities.clickButton("OK", 1000, driver);

			Utilities.clickButton("Cancel", 3000, driver);

			System.out.println("*****************Asset Opening Document is Edited Successfully   " + OpeningDoc);

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ************************ Delete Asset Opening Document
	// ***********************

	public static void Delete_OpeningDoument(String Group, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_AssetOpening.properties");
			String documentid1 = "AGroup" + Group;
			String CrossSymbol = "//li[@id='as__assetGroupTab']/a[1]";
			String OpeningDoc = "Opening" + documentid1;

			// Click on "Asset Groups" Icon for create Asset Group
			WebDriverWait wait = new WebDriverWait(driver, 60);

			// Click On "Opening" button to given opening details for Asset
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("OpeningBtn"))));
			driver.findElement(By.xpath(pro.getProperty("OpeningBtn"))).click();
			Thread.sleep(2000);

			// Click On "Check Box" button for fill opening document information
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("CheckBoxEdit"))));
			driver.findElement(By.xpath(pro.getProperty("CheckBoxEdit"))).click();
			Thread.sleep(2000);

			// Click On "Delete" button for fill opening document information
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("DeleteBtn"))));
			driver.findElement(By.xpath(pro.getProperty("DeleteBtn"))).click();
			Thread.sleep(2000);

			// Click on Confirmation "Yes" button present at Opening Form of
			// asset.

			Utilities.clickButton("Yes", 2000, driver);

			Utilities.clickButton("OK", 1000, driver);

			Utilities.clickButton("Cancel", 3000, driver);

			System.out.println("*****************Asset Opening Document is Deleted Successfully   " + OpeningDoc);

			Utilities.HoverandClick(CrossSymbol, driver);

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}
}
