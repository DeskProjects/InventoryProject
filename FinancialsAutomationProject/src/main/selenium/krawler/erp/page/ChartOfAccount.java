package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;

import krawler.erp.utils.SikulliUtil;

public class ChartOfAccount {

	public static void create_ChartOfAccount(String documentid, String accType, String masterType, String groupid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ChartOfAccount.properties");
			// clicked on Sales Return Document

			Utilities.waitandClick(pro.getProperty("iconCAO"), driver);
			Thread.sleep(2000);
			Utilities.click_element(pro.getProperty("addNewAcc"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("accCode"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("accName"), driver);

			// select account type
			Utilities.click_element("//*[@name='accounttype']/following::img[1]", driver);
			Utilities.click_element(
					"//*[contains(@class,'x-combo-list') and contains(@style,'visible')]//*[text()='" + accType + "']",
					driver);

			// select Master Type
			Utilities.click_element("//*[text()='" + masterType + "']/preceding-sibling::input[@name='mastertype']",
					driver);
			// select group
			Utilities.enterTextandSelect(groupid, pro.getProperty("groupID"), driver);

			// Select first User
			Utilities.HoverandClick("//*[@id='username']/following::img[1]", driver);
			Utilities.HoverandClick("//*[contains(@class,'x-combo-list') and contains(@style,'visible')]/div/div",
					driver);

			Utilities.click_element(pro.getProperty("saveBtn"), driver);
			Utilities.click_element(pro.getProperty("okBtn"), driver);
			Utilities.click_element(pro.getProperty("coaClsBtn"), driver);
			System.out.println("Chart of account " + documentid + " successfully created !! ");
		} catch (Exception EX) {
			System.out.println("Chart of account " + documentid + " NOT created !! ");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************************* Verify COA
	// **********************************************************
	public static void verify_ChartOfAccount(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "COA" + documentid;
			Properties pro = Utilities.fetchProValue("OR_ChartOfAccount.properties");
			// clicked on Sales Return Document
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("iconCAO"))));
			driver.findElement(By.xpath(pro.getProperty("iconCAO"))).click();
			Thread.sleep(5000);// pro

			WebElement search = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			int e = driver
					.findElements(By
							.xpath("//div[@id='coaReport']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div/div/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);
			Thread.sleep(3000);
			for (int i = 1; i <= e; i++) {
				String header = driver.findElement(By
						.xpath("//div[@id='coaReport']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Account Code")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath("//div[@id='coaReport']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr/td["
											+ i + "]/div"))
									.getText());
				}

				else if (header.equals("Account Name")) {
					assertEquals("Account " + documentid + "name",
							driver.findElement(By
									.xpath("//div[@id='coaReport']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr/td["
											+ i + "]/div"))
									.getText());
				}

			}
			System.out.println("Chart of account " + documentid + " verified successfully !! ");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("coaClsBtn"))));
			driver.findElement(By.xpath(pro.getProperty("coaClsBtn"))).click();
			Thread.sleep(2000);

		} catch (Exception EX) {
			System.out.println("Chart of account " + documentid + " NOT verified !! ");
			Utilities.handleError(EX, driver);
		}
	}

	// **************************************** Edit - Delete
	// **********************************
	public static void edit_delete_ChartOfAccount(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			// documentid="COA"+documentid;
			String expectedEdit = "Performing Edit operation with descrption of " + documentid;

			Properties pro = Utilities.fetchProValue("OR_ChartOfAccount.properties");
			// clicked on Sales Return Document
			Utilities.waitandClick(pro.getProperty("iconCAO"), driver);
			Thread.sleep(2000);
			String xpathOfFirstElement = "//div[@id='coaReport']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr/td[1]/div";
			Utilities.clickCheckBox(xpathOfFirstElement, "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			// edit
			Utilities.clickCheckBox(xpathOfFirstElement, "check", driver);
			Utilities.HoverandClick(pro.getProperty("EditAccount"), driver);
			Utilities.enterTextNormalBox(expectedEdit, pro.getProperty("accDescr"), driver);
			Utilities.HoverandClick(pro.getProperty("saveBtn"), driver);
			Utilities.HoverandClick(pro.getProperty("okBtn"), driver);
			System.out.println("COA EDIT VERIFIED sucess with desc> " + expectedEdit);

			// delete
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(xpathOfFirstElement, "check", driver);
			Utilities.HoverandClick(pro.getProperty("deleteBtn"), driver);
			Utilities.HoverandClick(pro.getProperty("permDelBtn"), driver);
			Utilities.clickButton("OK", 1000, driver);
			Thread.sleep(1000);

			// verify flow
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			try {
				WebElement afterDeleteLink = new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[text()='There is no record to display.']")));

				if (afterDeleteLink.isDisplayed()) {
					System.out.println("COA with ID " + documentid + "  is Deleted Successfully");
				}
			} catch (Exception deleteexp) {
				System.out.println("COA with ID " + documentid + " is NOT deleted !!!!!");
			}
			String xpathOfelement = pro.getProperty("coaClsBtn");
			Utilities.HoverandClick(xpathOfelement, driver);
		}

		catch (Exception EX) {
			System.out.println("COA with ID " + documentid + " is NOT deleted !!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ***************************** import
	// *************************************************
	public static void import_ChartOfAccount(String fileType, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ChartOfAccount.properties");
			String filPathXLS = Utilities.importFile("ImportCOAExl.xls");
			String filPathCSV = Utilities.importFile("ImportCOACsv.csv");
			WebDriverWait wait = new WebDriverWait(driver, 30);
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";

			Utilities.waitandClick(pro.getProperty("iconCAO"), driver);
			Utilities.clickCheckBox("//*[text()='Account Code']/ancestor::div[3]/following::div[1]/div/div[1]//td[1]",
					"check", driver);

			Utilities.click_element("//button[text()='Import']", driver);

			WebElement condition = driver
					.findElement(By.xpath("//*[@class='x-layer x-menu' and contains(@style,'visible')]/ul/li[1]//*"));
			String newUpdate = condition.getText();

			if (newUpdate.contains("Import Account")) {
				Utilities.justHover(
						"//*[@class='x-layer x-menu' and contains(@style,'visible')]/ul//*[text()='Import Account']",
						driver);
			}

			if (fileType.equalsIgnoreCase("XLS")) {
				Utilities.HoverandClick(pro.getProperty("ImportXLFile"), driver);
				Utilities.enterTextNormalBox(filPathXLS, pro.getProperty("browseBtn"), driver);
				Utilities.click_element(pro.getProperty("NextBtn"), driver);
				Utilities.click_element(pro.getProperty("NextBtn2"), driver);
			}

			else if (fileType.equalsIgnoreCase("CSV")) {
				Utilities.HoverandClick(pro.getProperty("ImportCSVFile"), driver);
				Utilities.enterTextNormalBox(filPathCSV, pro.getProperty("browseBtn"), driver);
				Utilities.click_element(pro.getProperty("NextBtn"), driver);
			}
			Utilities.click_element("//span[contains(@style,'color:red') and text()='Group']", driver); // just
																										// to
																										// check
																										// wether
																										// loaded
																										// or
																										// mot
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
				int totalProd = Utilities.totalSize(pro.getProperty("totalAddedCOA"), driver);
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
				System.out.println(
						"* * * * * * COA [" + fileType + "] file is NOT imported Plz check Log!!!!* * * * * * ");
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
					System.out.println("* * * * * * COA [" + fileType + "] file is Imported!!!!* * * * * * ");
				} else if (importLogMsg.contains("Failed to import")) {
					System.out.println(
							"* * * * * * COA [" + fileType + "] file is NOT imported Plz check Log!!!!* * * * * * ");
					driver.navigate().refresh();
					Assert.assertTrue(false);
				} else if (importLogMsg.contains("Pending")) {
					System.out.println("* * * * * * COA [" + fileType
							+ "] file is imported but in Pending Plz check after some time !!!!* * * * * * ");
				}
			}
			driver.navigate().refresh();
			Utilities.isLoadingDisappear(120, driver);

		} catch (Exception EX) {
			System.out.println("FAILED TO EXPORT COA");
			Utilities.handleError(EX, driver);
		}
	}

	// ************* Import COA verification *********************
	public static void importCOA_validation(String accounts[], WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ChartOfAccount.properties");
			Utilities.HoverandClick(pro.getProperty("iconCAO"), driver);
			Utilities.click_element(pro.getProperty("FirstCoaAccount"), driver);

			int i = 0;
			for (i = i + 1; i < accounts.length; i++) {
				WebElement account = null;
				Utilities.click_element(pro.getProperty("QuickSearch"), driver);
				Utilities.enterTextInDropDown(accounts[i], pro.getProperty("QuickSearch"), driver);
				Utilities.isLoadingDisappear(120, driver);
				account = driver.findElement(
						By.xpath("//*[text()='" + accounts[i] + "']/ancestor::tr/td//*[@class='x-grid3-row-checker']"));
				if (account.isDisplayed()) {
					account.click();
					System.out.println("**** Account [" + accounts[i] + "] Imported ");
					Utilities.click_element(pro.getProperty("deleteBtn"), driver);
					Utilities.click_element(pro.getProperty("permDelBtn"), driver);
					Utilities.clickButton("OK", 500, driver);
					Utilities.clickButton("OK", 0, driver);
					System.out.println("**** Account [" + accounts[i] + "] now Deleted *****");
					// Deleted Go next

				}
			}

		} catch (Exception EX) {
			System.out.println("* * * * * * COA file is NOT imported !!!!* * * * * * ");
			Utilities.handleError(EX, driver);
		}

	}

	// ** * * * * * * ** * * EXPORT * * * * * * ** * * * ** * * * * *

	public static void Export_COA(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_ChartOfAccount.properties");

			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("iconCAO"))));
			element.click();
			Thread.sleep(1000);

			Utilities.isLoadingDisappear(180, driver);
			driver.findElement(By.xpath("//*[text()='Show']/parent::td/following::td[1]//input")).click();
			driver.findElement(By.xpath("//*[contains(@style,'visible')]//*[text()='10']")).click();
			// CSV File
			Utilities.clickButton("Export ", 500, driver);
			Utilities.HoverandClick(pro.getProperty("ExportToCSV"), driver);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
			element.click();
			Thread.sleep(1000);
			SikulliUtil.sikulli_waitClick(driver, "CSVtype");
			SikulliUtil.sikulli_waitClick(driver, "ClsX");
			System.out.println("* * * * * * EXPORT for [.CSV] completed successfully * * * * * * *");
			Thread.sleep(1500);

			// PDF
			Utilities.clickButton("Export ", 500, driver);
			String parentWindow = driver.getWindowHandle();
			Utilities.HoverandClick(pro.getProperty("ExportToPDF"), driver);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("pdfTemplate"))));
			element.click();
			Thread.sleep(1000);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
			element.click();
			Thread.sleep(1000);
			SikulliUtil.sikulli_waitClick(driver, "PDFtype");
			Thread.sleep(1500);
			Set s = driver.getWindowHandles(); // this method will gives you the
												// handles of all opened windows
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

			// Excel
			Utilities.clickButton("Export ", 500, driver);
			Utilities.HoverandClick(pro.getProperty("ExportToExcel"), driver);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
			element.click();
			Thread.sleep(1000);
			SikulliUtil.sikulli_waitClick(driver, "XLStype");
			SikulliUtil.sikulli_waitClick(driver, "ClsX");
			System.out.println("* * * * * * EXPORT for [.Excel] completed successfully * * * * * * *");
			Thread.sleep(1500);

			Utilities.HoverandClick(pro.getProperty("coaClsBtn"), driver);

			System.out.println("* * * * * * EXPORT for [COA MASTER] completed successfully * * * * * * *");
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [COA MASTER] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}
	}

	// get button path for Specific Module
	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;

	}

}// class