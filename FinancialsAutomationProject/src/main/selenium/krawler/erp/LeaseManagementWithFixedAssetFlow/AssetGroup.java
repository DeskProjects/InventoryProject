package krawler.erp.LeaseManagementWithFixedAssetFlow;

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
import org.sikuli.script.Key;

import krawler.erp.page.Utilities;

public class AssetGroup {

	WebDriver driver;

	public static void create_AssetGroup(String Asset_ID, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_AssetGroup1.properties");
			String documentid = "asset" + Asset_ID;
			String AssetGroupName = Asset_ID + "BS Name";
			String AssetGroupDes = Asset_ID + " Contains 'Batch', 'Serial' information";
			Thread.sleep(3000);

			// Click on "Asset Groups" Icon for create Asset Group
			WebDriverWait wait = new WebDriverWait(driver, 60);
			driver.findElement(By.xpath(pro.getProperty("ClickOnIcon"))).isEnabled();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("ClickOnIcon"))));

			driver.findElement(By.xpath(pro.getProperty("ClickOnIcon"))).click();
			Thread.sleep(3000);

			// Click On "Create" New button given in Asset Group report
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("CreateNew"))));
			driver.findElement(By.xpath(pro.getProperty("CreateNew"))).click();
			Thread.sleep(2000);

			// Select NA Sequence format
			WebElement Sequenceformat = driver.findElement(By.xpath(pro.getProperty("Sequenceformat")));
			Sequenceformat.clear();
			Sequenceformat.sendKeys("NA");
			Thread.sleep(1000);// pro
			Sequenceformat.sendKeys(Keys.ENTER);

			// Putting Asset Group ID
			WebElement GID = driver.findElement(By.xpath(pro.getProperty("GroupId")));
			GID.click();
			GID.sendKeys(documentid);// Adding new group ID

			// Putting Asset Group Name
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("GroupName"))));
			driver.findElement(By.xpath(pro.getProperty("GroupName"))).sendKeys(AssetGroupName);
			Thread.sleep(1000);// pro

			// Putting Asset Group Description
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("GroupDes"))));
			driver.findElement(By.xpath(pro.getProperty("GroupDes"))).sendKeys(AssetGroupDes);
			Thread.sleep(1000);

			// True Location, Warehouse , Batch and Serial Number option
			if (driver.findElement(By.xpath(pro.getProperty("LocationCheck"))).isEnabled()) {
				driver.findElement(By.xpath(pro.getProperty("LocationCheck"))).click();
			}

			if (driver.findElement(By.xpath(pro.getProperty("WarehouseCheck"))).isEnabled()) {
				driver.findElement(By.xpath(pro.getProperty("WarehouseCheck"))).click();
			}

			if (driver.findElement(By.xpath(pro.getProperty("SerialCheck"))).isEnabled()) {
				driver.findElement(By.xpath(pro.getProperty("SerialCheck"))).click();
			}

			if (driver.findElement(By.xpath(pro.getProperty("BatchCheck"))).isEnabled()) {
				driver.findElement(By.xpath(pro.getProperty("BatchCheck"))).click();
			}

			if (driver.findElement(By.xpath(pro.getProperty("Makeavailable"))).isEnabled()) {
				driver.findElement(By.xpath(pro.getProperty("Makeavailable"))).click();
				Thread.sleep(1000);
			}

			// Putting Initial Purchase Price
			WebElement InitPurPrice = driver.findElement(By.xpath(pro.getProperty("initialPurchasePrice")));
			InitPurPrice.click();
			InitPurPrice.sendKeys("100");

			// Putting Initial Sales Price
			WebElement InitSalPrice = driver.findElement(By.xpath(pro.getProperty("initialSalesPrice")));
			InitSalPrice.click();
			InitSalPrice.sendKeys("1000");

			// Select account from respective account combo

			selectFromNormalDropDown("Fixed Assets", pro.getProperty("ControlAcc"), driver);

			selectFromNormalDropDown("Depreciation", pro.getProperty("DepGL"), driver);

			selectFromNormalDropDown("Payroll Liabilities", pro.getProperty("ProvisionforDep"), driver);

			selectFromNormalDropDown("Other Charges", pro.getProperty("ProfitLossInSales"), driver);

			selectFromNormalDropDown("Other income", pro.getProperty("WriteOff"), driver);

			// Putting rate of depreciation percentage
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("RateOfDep"))));
			WebElement RateOfDepr = driver.findElement(By.xpath(pro.getProperty("RateOfDep")));
			RateOfDepr.clear();
			RateOfDepr.sendKeys("10");
			Thread.sleep(2000);

			// Click on Save button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SaveBtn"))));
			driver.findElement(By.xpath(pro.getProperty("SaveBtn"))).click();// working
																				// fine
			Thread.sleep(1000);

			// Click On "Yes" button
			Utilities.clickButton("Yes", 1000, driver);

			// Click On "Ok" button
			Utilities.clickButton("OK", 1000, driver);
			System.out.println("\"Asset Group\" is created successfully: " + Asset_ID);
			Thread.sleep(3000);
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ------------------------------------ Create Opening Document for Asset
	// Group--------------------------------------------------------

	public static void Create_OpeningDoument(String Asset_ID, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_AssetGroup1.properties");
			String documentid = "asset" + Asset_ID;
			String AssetGroupName = Asset_ID + "BS Name";

			// Click on "Asset Groups" Icon for create Asset Group
			WebDriverWait wait = new WebDriverWait(driver, 60);
			driver.findElement(By.xpath(pro.getProperty("ClickOnIcon"))).isEnabled();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("ClickOnIcon"))));

			driver.findElement(By.xpath(pro.getProperty("ClickOnIcon"))).click();
			Thread.sleep(3000);

			// Click on "Fetch" button for fetching data
			driver.findElement(By.xpath(pro.getProperty("FetchBtn"))).click();
			Thread.sleep(1000);

			// Search Document in Asset Group Report
			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch1"))));
			search.sendKeys(documentid);
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
			String OpeningDoc = "Opening" + documentid; // Opening Document
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
			Utilities.clickAndEnterText(documentid + "FA1", pro.getProperty("AssetID1"), driver);

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

			// Click on Serial and Batch's icon
			Utilities.justHover("//*[@class='pwnd serialNo-gridrow']", driver);
			Utilities.HoverandClick(pro.getProperty("SerialIcon"), driver);
			Thread.sleep(3000);

			// File the warehouse and batch information

			driver.findElement(By.xpath(pro.getProperty("WarehouseInf"))).click();
			Thread.sleep(2000);
			Utilities.sendText("DS - Default Store");
			Thread.sleep(1000);

			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);

			Thread.sleep(2000);
			Utilities.sendText("Default Location");
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);

			Utilities.sendText("Batch1");
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("SerialNumberinfo"))));
			driver.findElement(By.xpath(pro.getProperty("SerialNumberinfo"))).click();
			Thread.sleep(1000);
			Utilities.sendText("S1");
			Thread.sleep(1000);

			// Click on Submit button given in Warehouse and Serial Information
			// window.
			driver.findElement(By.xpath(pro.getProperty("Submitbtn"))).click();
			Thread.sleep(2000);

			// Again fill the Asset Details and Warehouse information for second
			// asset(second line)
			Utilities.justHover(pro.getProperty("AssetID2"), driver);
			Utilities.clickAndEnterText(documentid + "FA2", pro.getProperty("AssetID2"), driver);
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

			// Hover for Serial and Batch's second icon
			Utilities.justHover("//*[@class='pwnd serialNo-gridrow']", driver);
			Utilities.HoverandClick(pro.getProperty("SerialIcon2"), driver);
			Thread.sleep(3000);

			// File the warehouse and batch information for second Asset

			driver.findElement(By.xpath(pro.getProperty("WarehouseInf"))).click();
			Thread.sleep(2000);
			Utilities.sendText("DS - Default Store");
			Thread.sleep(1000);

			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);

			Thread.sleep(2000);
			Utilities.sendText("Default Location");
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);

			Utilities.sendText("Batch1");
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("SerialNumberinfo"))));
			driver.findElement(By.xpath(pro.getProperty("SerialNumberinfo"))).click();
			Thread.sleep(1000);
			Utilities.sendText("S2");
			Thread.sleep(1000);

			// Click on Submit button given in Warehouse and Serial Information
			// window.
			driver.findElement(By.xpath(pro.getProperty("Submitbtn"))).click();
			Thread.sleep(2000);

			// Click on "Save" button present at Asset Details window.
			driver.findElement(By.xpath(pro.getProperty("SaveInner1"))).click();
			Thread.sleep(1000);

			// Click on "Save" button present at Opening Form of asset.
			Utilities.clickButton("Save", 2000, driver);

			Utilities.clickButton("OK", 1000, driver);

			Utilities.clickButton("Cancel", 3000, driver);

			System.out.println("Opening Document is created now : " + OpeningDoc);

			// ----------------------------------Sync Asset into CRM
			// application------------------------------

			// Click On "Sync" button
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("Datasync1"))));
			driver.findElement(By.xpath(pro.getProperty("Datasync1"))).click();

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("Datasync2"))));
			driver.findElement(By.xpath(pro.getProperty("Datasync2"))).click();

			// Click on "Ok" button

			Utilities.clickButton("OK", 2000, driver);

			Utilities.clickButton("OK", 2000, driver);

			Utilities.clickButton("Fetch", 1000, driver);

			System.out.println("Asset Group is getting Synced successfully into CRM application...");

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// Method for select item from NormalDrop Down
	public static void selectFromNormalDropDown(String text, String expForname, WebDriver driver)
			throws InterruptedException {
		WebElement enterText = new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(expForname)));
		enterText.clear();
		enterText.sendKeys(text);
		Thread.sleep(1000);
		WebElement element = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]//*[text()='" + text + "']")));
		Thread.sleep(500);
		element.click();
		Thread.sleep(500);
	}
}