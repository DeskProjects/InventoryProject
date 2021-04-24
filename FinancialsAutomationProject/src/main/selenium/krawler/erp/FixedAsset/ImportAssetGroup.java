package krawler.erp.FixedAsset;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import krawler.erp.page.Utilities;

public class ImportAssetGroup {

	public static void Import_AssetGroup(String fileType, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ImportAssetGroup.properties");

			// Enter Import CSV File Name

			String filPathCSV = Utilities.importFile("Import_AssetGroup.csv");
			WebDriverWait wait = new WebDriverWait(driver, 30);
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Groups Icon

			WebElement AGIcon = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ClickOnIcon"))));
			AGIcon.click();
			Thread.sleep(2000);

			// Click On Import Icon

			Utilities.clickButton("Import Asset Groups", 1000, driver);

			// Import CSV File

			Utilities.click_element(pro.getProperty("ImportCSVFile"), driver);
			Utilities.enterTextNormalBox(filPathCSV, pro.getProperty("browseBtn"), driver);
			Utilities.click_element(pro.getProperty("NextBtn"), driver);
			Thread.sleep(2000);

			// Just to check file loaded or not

			Utilities.click_element("//span[contains(@style,'color:red') and text()='Asset Group ID']", driver);
			Utilities.clickButton("Auto Map Columns", 500, driver);
			Utilities.clickButton("Analyze Data", 1000, driver);

			String isReadytoImport = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("checkImportReady"))))
					.getText();
			// System.out.println(isReadytoImport);
			double errorOrnot = Utilities.getIntegerFrmString(isReadytoImport);
			// System.out.println(errorOrnot);

			if (errorOrnot > 0) {

				// ERROR while import

				int totalProd = Utilities.totalSize(pro.getProperty("totalAddedAG"), driver);
				for (int i = 1; i <= totalProd; i++) {
					driver.findElement(By
							.xpath("//div[text()='Row No.']/ancestor::div[@class='x-grid3-header']/following-sibling::div/div/div["
									+ i + "]/table/tbody/tr/td[1]/div"))
							.click();
					Thread.sleep(1000);
					String invalidRec = driver
							.findElement(By.xpath("//b[contains(text(),'Validation Details')]/parent::*")).getText();
					System.out.println(invalidRec);
				}

				System.out.println("* * * * * * Asset Group [" + fileType
						+ "] file is NOT imported Plz check Log!!!!* * * * * * ");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}

			if (errorOrnot == 0) {
				Utilities.clickButton("Import Data", 1000, driver);
				Utilities.click_element("//button[text()='View Import Log']", driver);
				Thread.sleep(1500);
				String importLogMsg = wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("importLogMessage"))))
						.getText();

				if (importLogMsg.equalsIgnoreCase("All records are imported successfully.")) {
					System.out.println("* * * * * * Asset Group [" + fileType + "] file is Imported!!!!* * * * * * ");
				}

				else if (importLogMsg.contains("Failed to import")) {
					System.out.println("* * * * * * Asset Group [" + fileType
							+ "] file is NOT imported Plz check Log!!!!* * * * * * ");
					driver.navigate().refresh();
					Assert.assertTrue(false);
				}

				else if (importLogMsg.contains("Pending")) {
					System.out.println("* * * * * * Asset Group [" + fileType
							+ "] file is imported but in Pending Plz check after some time !!!!* * * * * * ");
				}
			}
			driver.navigate().refresh();
			Utilities.isLoadingDisappear(120, driver);

		} catch (Exception EX) {

			Utilities.handleError(EX, driver);
		}
	}

	// ************************************* Import CSV File for Fixed Asset
	// Opening *****************************

	public static void Import_AssetOpening(String fileType, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ImportAssetGroup.properties");

			// Enter Import CSV File Name

			String filPathCSV = Utilities.importFile("Import_Fixed_Asset_Opening.csv");
			WebDriverWait wait = new WebDriverWait(driver, 30);
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Groups Icon

			WebElement AGIcon = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ClickOnIcon"))));
			AGIcon.click();
			Thread.sleep(2000);

			// Click On Import Icon

			Utilities.clickButton("Import Opening Fixed Asset Documents", 1000, driver);

			// Import CSV File

			Utilities.click_element(pro.getProperty("ImportCSVFile"), driver);
			Utilities.enterTextNormalBox(filPathCSV, pro.getProperty("browseBtn"), driver);
			Utilities.click_element(pro.getProperty("NextBtn"), driver);
			Thread.sleep(2000);

			// Just to check file weather loaded or not

			Utilities.click_element("//span[contains(@style,'color:red') and text()='Asset Group ID']", driver);
			Utilities.clickButton("Auto Map Columns", 500, driver);
			Utilities.clickButton("Analyze Data", 1000, driver);

			String isReadytoImport = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("checkImportReady"))))
					.getText();

			// System.out.println(isReadytoImport);
			double errorOrnot = Utilities.getIntegerFrmString(isReadytoImport);
			// System.out.println(errorOrnot);

			if (errorOrnot > 0) {

				// ERROR while import

				int totalProd = Utilities.totalSize(pro.getProperty("totalAddedAG"), driver);
				for (int i = 1; i <= totalProd; i++) {
					driver.findElement(By
							.xpath("//div[text()='Row No.']/ancestor::div[@class='x-grid3-header']/following-sibling::div/div/div["
									+ i + "]/table/tbody/tr/td[1]/div"))
							.click();
					Thread.sleep(1000);
					String invalidRec = driver
							.findElement(By.xpath("//b[contains(text(),'Validation Details')]/parent::*")).getText();
					System.out.println(invalidRec);
				}

				System.out.println("* * * * * * Asset Group [" + fileType
						+ "] file is NOT imported Plz check Log!!!!* * * * * * ");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}

			if (errorOrnot == 0) {
				Utilities.clickButton("Import Data", 1000, driver);
				Utilities.click_element("//button[text()='View Import Log']", driver);
				Thread.sleep(1500);
				String importLogMsg = wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("importLogMessage"))))
						.getText();

				if (importLogMsg.equalsIgnoreCase("All records are imported successfully.")) {
					System.out.println("* * * * * * Asset Opening [" + fileType + "] file is Imported!!!!* * * * * * ");
				}

				else if (importLogMsg.contains("Failed to import")) {
					System.out.println("* * * * * * Asset Opening [" + fileType
							+ "] file is NOT imported Plz check Log!!!!* * * * * * ");
					driver.navigate().refresh();
					Assert.assertTrue(false);
				}

				else if (importLogMsg.contains("Pending")) {
					System.out.println("* * * * * * Asset Opening [" + fileType
							+ "] file is imported but in Pending Plz check after some time !!!!* * * * * * ");
				}
			}
			driver.navigate().refresh();
			Utilities.isLoadingDisappear(120, driver);

		} catch (Exception EX) {

			Utilities.handleError(EX, driver);
		}
	}

	// ************************* Verification for Import Asset Group and
	// Opening***************************

	public void Verification_AssetOpening(String Group, String ExpectedResultTA, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			Properties pro = Utilities.fetchProValue("OR_ImportAssetGroup.properties");
			String documentid1 = "AGroup" + Group;
			String AssetGroupName = Group + "BS Name";

			// Click on "Asset Groups" Icon for create Asset Group
			WebDriverWait wait = new WebDriverWait(driver, 60);

			// Click On Asset Groups Icon

			WebElement AGIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ClickOnIcon"))));
			AGIcon.click();
			Thread.sleep(2000);

			// Search Document in Asset Group Report

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch1"))));
			search.sendKeys("ASS004");
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickButton("Fetch", 3000, driver);

			// Click on "check box" for record selection

			driver.findElement(By.xpath(pro.getProperty("CheckBoxIcon"))).isEnabled();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("CheckBoxIcon"))));
			driver.findElement(By.xpath(pro.getProperty("CheckBoxIcon"))).click();
			Thread.sleep(3000);

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

			String ExpectedResultTAB = "SGD 3,600.00";

			System.out.println("*********Asset Opening Rate  Expected Result = " + ExpectedResultTAB + " **********");
			System.out.println("*********Asset Opening Rate Actual Result =  " + ActualResultTAB + "    ************");

			if (ExpectedResultTAB.equals(ActualResultTAB)) {
				System.out
						.println("Matched Asset Opening Rate  [" + ExpectedResultTAB + "]  [" + ActualResultTAB + "]");

			} else {
				System.out.println("");

			}

			Utilities.clickButton("Cancel", 3000, driver);

			System.out.println(
					"*****************Asset Opening Document Verification  Completed Successfully*******************");

		} catch (Exception EX) {

		}

	}

}
