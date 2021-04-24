package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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
import org.testng.Assert;

import krawler.erp.utils.SikulliUtil;

public class VendorMaster {
	static WebDriver driver;

	public static void create_Vendor(String vendor_ID, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {
			Properties values = Utilities.fetchProValue("OR_VendorMaster.properties");
			String vendorName = vendor_ID + "Name";

			// wait until component appears
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(values.getProperty(
					"CreateVendorIcon"))));/* examining the xpath search box */

			// open vendor master
			driver.findElement(By.xpath(values.getProperty("CreateVendorIcon"))).click();
			System.out.println("clicked on vendor");
			Thread.sleep(2000);// pro

			// click on Term
			WebElement NA = driver.findElement(By.xpath(values.getProperty("sequenceformat")));
			NA.clear();
			NA.sendKeys("NA");
			Thread.sleep(1000);// pro
			NA.sendKeys(Keys.ENTER);

			Thread.sleep(3000);// pro

			driver.findElement(By.xpath(values.getProperty("vendorID"))).sendKeys(vendor_ID);// adding
																								// new
																								// vendor
																								// ID
			driver.findElement(By.xpath(values.getProperty("vendorName"))).sendKeys(vendorName);// adding
																								// new
																								// Vendor
																								// name

			WebElement term = driver.findElement(By.xpath(values.getProperty("debitTerm")));
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);

			// credit term
			driver.findElement(By.xpath(values.getProperty("CreditTerm"))).sendKeys("999999999");// adding
																									// new
																									// vendor
																									// ID

			// to disable agent
			WebElement agentCheckbox = new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//input[@name='venodorAvailableToAgentCheck']"))));
			if (agentCheckbox.isSelected()) {
				agentCheckbox.click();
			}

			// first save visible wait
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(values.getProperty("personaldetailsSave"))));
			// first save
			driver.findElement(By.xpath(values.getProperty("personaldetailsSave"))).click();

			// second save
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(values.getProperty("confirmYes"))));
			driver.findElement(By.xpath(values.getProperty("confirmYes"))).click();
			Thread.sleep(3000);// pro

			// second save
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(values.getProperty("successOk"))));
			driver.findElement(By.xpath(values.getProperty("successOk"))).click();

			driver.findElement(By.xpath(values.getProperty("vendorContactDetail"))).click();
			driver.findElement(By.xpath(values.getProperty("billingAddress"))).sendKeys("12, vishwakarma nagar");
			driver.findElement(By.xpath(values.getProperty("billingCity"))).sendKeys("Nagpur");// address
			driver.findElement(By.xpath(values.getProperty("billingState"))).sendKeys("maharashtra");// maharashtra
			driver.findElement(By.xpath(values.getProperty("billingCountry"))).sendKeys("country");// country
			driver.findElement(By.xpath(values.getProperty("billingPostal"))).sendKeys("440027");
			driver.findElement(By.xpath(values.getProperty("billingMobile"))).sendKeys("8421279427");
			driver.findElement(By.xpath(values.getProperty("billingEmail"))).sendKeys("amol.gaikwad@deskera.com");
			driver.findElement(By.xpath(values.getProperty("billingRecipientName"))).sendKeys("receipt");
			driver.findElement(By.xpath(values.getProperty("billingContactPerson"))).sendKeys("abcXyz");
			driver.findElement(By.xpath(values.getProperty("billingContactPersonNumber"))).sendKeys("9988774466");
			driver.findElement(By.xpath(values.getProperty("billingContactPersonDesignation")))
					.sendKeys("QA Automation");
			driver.findElement(By.xpath(values.getProperty("billingWebsite"))).sendKeys("www.deskera.com");

			// select check box
			WebElement checkBoxElement = driver.findElement(By.xpath(values.getProperty("SameasBillingAddressCheck")));
			checkBoxElement.click();

			// second save
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(values.getProperty("vendorContactDetailSave"))));
			driver.findElement(By.xpath(values.getProperty("vendorContactDetailSave"))).click();
			Thread.sleep(3000);// pro

			// clic on ok
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(values.getProperty("vendorContactDetailOk"))));
			driver.findElement(By.xpath(values.getProperty("vendorContactDetailOk"))).click();
			Thread.sleep(3000);// pro

			Utilities.HoverandClick("//*[@id='as__mainVendorPanel']/a[1]", driver);
			System.out.println("******** Vendor Created  : [" + vendor_ID + "] !!!! ********");

		} catch (Exception EX) {
			System.out.println("******** Vendor NOT Created  : " + vendor_ID + " check log pls ********");
			Utilities.handleError(EX, driver);
		}
	}
	// ********************************************verification**************************************************

	public static void verify_Vendor(String vendor_ID, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_VendorMaster.properties");
			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("VendorMasterIcon"))));
			driver.findElement(By.xpath(pro.getProperty("VendorMasterIcon"))).click();
			Thread.sleep(5000);// pro

			// search lonnnnnng
			WebElement longwait = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//div[@id='VendorDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div/div[2]/div/div[1]/table/tbody/tr/td[1]")));
			if (longwait.isDisplayed()) {

				// div[@id='VendorDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div/div[2]/div/div[1]

				WebElement search = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

				search.sendKeys(vendor_ID);
				search.sendKeys(Keys.TAB);
				Thread.sleep(2000);
				int e = driver
						.findElements(By
								.xpath(".//*[@id='VendorDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td/div"))
						.size();
				// System.out.println(e);

				Thread.sleep(3000);
				for (int i = 1; i < e + 1; i++) {
					// System.out.println(driver.findElement(By.xpath(".//*[@id='VendorDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["+i+"]/div")).getText());
					String header = driver.findElement(By
							.xpath(".//*[@id='VendorDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();

					if (header.equals("Name")) {
						assertEquals(vendor_ID + "Name",
								driver.findElement(By
										.xpath(".//*[@id='VendorDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
												+ i + "]/div"))
										.getText());
					}

					else if (header.equals("Vendor Code")) {
						assertEquals(vendor_ID,
								driver.findElement(By
										.xpath(".//*[@id='VendorDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
												+ i + "]/div"))
										.getText());
					}

					else if (header.equals("Debit Term")) {
						assertEquals("NET 45",
								driver.findElement(By
										.xpath(".//*[@id='VendorDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
												+ i + "]/div"))
										.getText());
					}

					else if (header.equals("Status")) {
						assertEquals("Active",
								driver.findElement(By
										.xpath(".//*[@id='VendorDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
												+ i + "]/div"))
										.getText());
					}

				}
				System.out.println("* * * Vendor [" + vendor_ID + "] Successfully created and verified * * *");

			}
			String xpathOfelement = "//li[@id='as__mainVendorDetails']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	// *************************************************************************
	public static void CopyEditDelete_Vendor(String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_VendorMaster.properties");
			Utilities.waitandClick(pro.getProperty("VendorMasterIcon"), driver);
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";
			Utilities.isElementGone(xpathOfLoading, 600, driver);
			Utilities.enterTextInDropDown(vendorid, pro.getProperty("QuickSearch"), driver);
			String copyVenid = "Copy" + vendorid;

			// Copy
			Utilities.click_element(pro.getProperty("DocumentCheckBox"), driver);
			Utilities.click_element(pro.getProperty("manageVendorButton"), driver);
			Utilities.click_element(pro.getProperty("copyVendorButton"), driver);
			Utilities.isElementGone(xpathOfLoading, 600, driver);
			Utilities.enterTextNormalBox(copyVenid, pro.getProperty("vendorID"), driver);
			Utilities.clickCheckBox("//input[@name='venodorAvailableToAgentCheck']", "uncheck", driver);
			Utilities.click_element(pro.getProperty("personaldetailsCopySave"), driver);
			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 500, driver);
			Utilities.click_element(pro.getProperty("CopyvendorClose"), driver);
			System.out.println("**** Vendor [" + copyVenid + "] Copy successfully !!!");

			// edit
			Utilities.click_element(pro.getProperty("QuickSearch"), driver);
			Utilities.enterTextInDropDown(copyVenid, pro.getProperty("QuickSearch"), driver);
			Utilities.click_element(pro.getProperty("DocumentCheckBox"), driver);
			Utilities.click_element(pro.getProperty("manageVendorButton"), driver);
			Utilities.click_element(pro.getProperty("EditVendorButton"), driver);
			// to disable agent
			Utilities.clickCheckBox("//input[@name='venodorAvailableToAgentCheck']", "uncheck", driver);
			Utilities.enterTextNormalBox("VendorUEN", pro.getProperty("VendorUEN"), driver);
			Utilities.click_element(pro.getProperty("personaldetailsEditSave"), driver);
			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 500, driver);
			Utilities.click_element(pro.getProperty("EditvendorClose"), driver);
			System.out.println("**** Vendor [" + copyVenid + "] Edited successfully !!!");

			// delete
			Utilities.click_element(pro.getProperty("QuickSearch"), driver);
			Utilities.enterTextInDropDown(copyVenid, pro.getProperty("QuickSearch"), driver);
			Utilities.click_element(pro.getProperty("DocumentCheckBox"), driver);
			Utilities.click_element(pro.getProperty("manageVendorButton"), driver);
			Utilities.click_element(pro.getProperty("DeleteVendorButton"), driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.clickButton("OK", 0, driver);

			// verify flow
			Utilities.click_element(pro.getProperty("QuickSearch"), driver);
			Utilities.enterTextInDropDown(copyVenid, pro.getProperty("QuickSearch"), driver);
			Utilities.isLoadingDisappear(120, driver);
			WebElement confirmation = new WebDriverWait(driver, 10).until(ExpectedConditions
					.elementToBeClickable(By.xpath("//a[contains(text(),'Get Started by adding a Vendor now')]")));
			if (confirmation.isDisplayed()) {
				System.out.println("**** Vendor [" + copyVenid + "] is DELETED successfully !!!");
			} else {
				System.out.println("**** Vendor [" + copyVenid + "] is NOT DELETED plz check  !!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}

			String xpathOfelement = "//li[@id='as__mainVendorDetails']/a[1]";
			Utilities.click_element(xpathOfelement, driver);
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// * * * * * * * * * * * * * EXPORT * * * * * * * * * * * * * * *
	public static void Export_Vendor(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_VendorMaster.properties");
			WebElement element = null;
			Utilities.waitandClick(pro.getProperty("VendorMasterIcon"), driver);
			Utilities.selectRecords(5, driver);

			Utilities.clickButton("Export ", 500, driver);
			// CSV
			Utilities.HoverandClick(pro.getProperty("ExportToCSV"), driver);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
			element.click();
			Thread.sleep(2000);
			SikulliUtil.sikulli_waitClick(driver, "exportCSV");
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
			// ------ here need to add PDF template code handle
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
			element.click();
			Thread.sleep(2000);
			SikulliUtil.sikulli_waitClick(driver, "exportPdf");
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
			Thread.sleep(3000);
			SikulliUtil.sikulli_waitClick(driver, "XLStype");
			SikulliUtil.sikulli_waitClick(driver, "ClsX");
			System.out.println("* * * * * * EXPORT for [.Excel] completed successfully * * * * * * *");
			Thread.sleep(1500);

			Utilities.HoverandClick(pro.getProperty("accountspayableClose"), driver);
			System.out.println("* * * * * * EXPORT for [VENDOR MASTER] completed successfully * * * * * * *");
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [VENDOR MASTER] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}
	}

	// get button path for Specific Module
	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\VendorMaster\\" + BtnName
				+ ".PNG";
		return file_path;

	}

	public static void Mukesh_Export_Vendor(String searchVendors, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 10);
			Properties pro = Utilities.fetchProValue("OR_VendorMaster.properties");

			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("VendorMasterIcon"))));
			element.click();
			Thread.sleep(3000);// pro

			String waitForQuickSearch = "//div[text()='Vendor Code']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td[1]/div";

			Utilities.clickCheckBox(waitForQuickSearch, "uncheck", driver);
			Utilities.enterTextInDropDown(searchVendors, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			Utilities.clickButton("Export ", 500, driver);

			// CSV
			Utilities.HoverandClick(pro.getProperty("ExportToCSV"), driver);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
			element.click();
			Thread.sleep(2000);
			SikulliUtil.sikulli_waitClick(driver, "exportCSV");
			SikulliUtil.sikulli_waitClick(driver, "ClsX");
			System.out.println("* * * * * * EXPORT for [.CSV] completed successfully * * * * * * *");
			Thread.sleep(1500);

			// PDF
			Utilities.clickButton("Export ", 500, driver);

			String parentWindow = driver.getWindowHandle();
			Utilities.HoverandClick(pro.getProperty("ExportToPDF"), driver);
			Thread.sleep(1000);
			try {
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("pdfTemplate"))));
				element.click();
				System.out.println("Template Already Present !! ");
				Thread.sleep(5000);
			} catch (Exception NT1) {

				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreateNewTemplate"))));
				// element =
				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Create
				// New']")));
				element.click();
				Thread.sleep(5000);
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SavePdftemplate"))));
				element.click();
				Thread.sleep(5000);
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ClickOnYes"))));
				element.click();
				Thread.sleep(5000);
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EnterTemplateName"))));
				element.click();
				Thread.sleep(5000);
				element.sendKeys("Default Pdf");
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SaveTemplate"))));
				element.click();
				Thread.sleep(5000);
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ClickOnOk"))));
				element.click();
				Thread.sleep(5000);
				System.out.println("Pdf Template is created successfully");
				Thread.sleep(5000);

				Utilities.clickButton("Export ", 500, driver);

				String parentWindow1 = driver.getWindowHandle();
				Utilities.HoverandClick(pro.getProperty("ExportToPDF"), driver);
				Thread.sleep(1000);

				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("pdfTemplate"))));
				element.click();
				Thread.sleep(5000);

			}

			// ------ here need to add PDF template code handle
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
			element.click();
			Thread.sleep(2000);
			SikulliUtil.sikulli_waitClick(driver, "exportPdf");
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
			Thread.sleep(3000);
			SikulliUtil.sikulli_waitClick(driver, "XLStype");
			SikulliUtil.sikulli_waitClick(driver, "ClsX");
			System.out.println("* * * * * * EXPORT for [.Excel] completed successfully * * * * * * *");
			Thread.sleep(1500);

			Utilities.HoverandClick(pro.getProperty("accountspayableClose"), driver);
			System.out.println("* * * * * * EXPORT for [VENDOR MASTER] completed successfully * * * * * * *");
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [VENDOR MASTER] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}
	}

	// get button path for Specific Module
	public static String button_path1(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\VendorMaster\\" + BtnName
				+ ".PNG";
		return file_path;

	}

	// ****************************************************************************************************
	public static void create_Vendor_Proper(String vendorid, String vendorName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {
			Properties values = Utilities.fetchProValue("OR_VendorMaster.properties");
			Utilities.HoverandClick(values.getProperty("CreateVendorIcon"), driver);
			Utilities.click_element("//input[@name='venodorAvailableToAgentCheck']", driver);

			// click on Term
			WebElement NA = driver.findElement(By.xpath(values.getProperty("sequenceformat")));
			NA.clear();
			NA.sendKeys("NA");
			Thread.sleep(1000);// pro
			NA.sendKeys(Keys.ENTER);

			Thread.sleep(3000);// pro

			driver.findElement(By.xpath(values.getProperty("vendorID"))).sendKeys(vendorid);// adding
																							// new
																							// vendor
																							// ID
			driver.findElement(By.xpath(values.getProperty("vendorName"))).sendKeys(vendorName);// adding
																								// new
																								// Vendor
																								// name

			WebElement term = driver.findElement(By.xpath(values.getProperty("debitTerm")));
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);

			// credit term
			driver.findElement(By.xpath(values.getProperty("CreditTerm"))).sendKeys("999999999");// adding
																									// new
																									// vendor
																									// ID

			// first save visible wait
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(values.getProperty("personaldetailsSave"))));
			// first save
			driver.findElement(By.xpath(values.getProperty("personaldetailsSave"))).click();

			// second save
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(values.getProperty("confirmYes"))));
			driver.findElement(By.xpath(values.getProperty("confirmYes"))).click();
			Thread.sleep(3000);// pro

			// second save
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(values.getProperty("successOk"))));
			driver.findElement(By.xpath(values.getProperty("successOk"))).click();

			driver.findElement(By.xpath(values.getProperty("vendorContactDetail"))).click();
			driver.findElement(By.xpath(values.getProperty("billingAddress"))).sendKeys("Singapore");
			driver.findElement(By.xpath(values.getProperty("billingCity"))).sendKeys("Singapore");// address
			driver.findElement(By.xpath(values.getProperty("billingState"))).sendKeys("Singapore");// maharashtra
			driver.findElement(By.xpath(values.getProperty("billingCountry"))).sendKeys("Singapore");// country
			driver.findElement(By.xpath(values.getProperty("billingPostal"))).sendKeys("440027");
			driver.findElement(By.xpath(values.getProperty("billingMobile"))).sendKeys("8400012345");
			driver.findElement(By.xpath(values.getProperty("billingEmail"))).sendKeys(vendorName + "@deskera.com");
			driver.findElement(By.xpath(values.getProperty("billingRecipientName"))).sendKeys("receipt");
			driver.findElement(By.xpath(values.getProperty("billingContactPerson"))).sendKeys(vendorName);
			driver.findElement(By.xpath(values.getProperty("billingContactPersonNumber"))).sendKeys("9988774466");
			driver.findElement(By.xpath(values.getProperty("billingContactPersonDesignation")))
					.sendKeys("QA Automation");
			driver.findElement(By.xpath(values.getProperty("billingWebsite"))).sendKeys("www.deskera.com");

			Utilities.click_element(values.getProperty("SameasBillingAddressCheck"), driver);
			Utilities.click_element(values.getProperty("vendorContactDetailSave"), driver);
			Utilities.click_element(values.getProperty("vendorContactDetailOk"), driver);
			Utilities.click_element("//*[@id='as__mainVendorPanel']/a[1]", driver);
			System.out.println("******** Vendor Created  : [" + vendorName + "] !!!! ********");

		} catch (Exception EX) {
			System.out.println("******** Vendor NOT Created  : [" + vendorName + "] check log pls ********");
			Utilities.handleError(EX, driver);
		}
	}

}