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

public class CustomerMaster {

	public static void create_Customer(String customerid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_CustomerMaster.properties");
			String customerName = customerid + "Name";

			Utilities.waitandClick(pro.getProperty("CreateCustomerIcon"), driver);
			Thread.sleep(3000);
			Utilities.clickCheckBox("//input[@name='customervenodoravailabletosalespersonagent']", "uncheck", driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceformat"), driver);
			Utilities.enterTextNormalBox(customerid, pro.getProperty("customerId"), driver);
			Utilities.enterTextNormalBox(customerName, pro.getProperty("customerName"), driver);
			Utilities.enterTextandSelect("NET 45", pro.getProperty("term"), driver);
			Utilities.enterTextNormalBox("999999999", pro.getProperty("CreditTerm"), driver);
			// first save
			Utilities.click_element(pro.getProperty("customerPersonalDetailsaveButton"), driver);
			Utilities.click_element(pro.getProperty("yesButton"), driver);
			Utilities.click_element(pro.getProperty("okButton"), driver);

			// ContactDetail Tab
			Utilities.HoverandClick(pro.getProperty("customerContactDetail"), driver);
			// click on contact of customer
			driver.findElement(By.xpath(pro.getProperty("address"))).click();
			driver.findElement(By.xpath(pro.getProperty("address"))).sendKeys("office");
			driver.findElement(By.xpath(pro.getProperty("city"))).sendKeys("pune");// address
			driver.findElement(By.xpath(pro.getProperty("state"))).sendKeys("Maharashtra");// maharashtra
			driver.findElement(By.xpath(pro.getProperty("country"))).sendKeys("India");// country
			driver.findElement(By.xpath(pro.getProperty("postalCode"))).sendKeys("44002");
			driver.findElement(By.xpath(pro.getProperty("mobileNo"))).sendKeys("8421279427");
			driver.findElement(By.xpath(pro.getProperty("emailID"))).sendKeys("amol.gaikwad@deskera.com");
			driver.findElement(By.xpath(pro.getProperty("contactPerson"))).sendKeys("Amol G");
			driver.findElement(By.xpath(pro.getProperty("contactPersonNo"))).sendKeys("9988774466");
			driver.findElement(By.xpath(pro.getProperty("contactPersonDesignstion"))).sendKeys("Automation");
			driver.findElement(By.xpath(pro.getProperty("website"))).sendKeys("www.deskera.com");
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("sameAsBillingCheck"), "check", driver);
			Utilities.click_element(pro.getProperty("customerContactDetailsaveButton"), driver);
			Utilities.click_element(pro.getProperty("customerContactDetailOkButton"), driver);

			Utilities.HoverandClick(pro.getProperty("dashBoardtab"), driver);
			Utilities.HoverandClick(pro.getProperty("closeCustomerTab"), driver);

			System.out.println("**************** Customer created successfully :[" + customerid + "]****************");
		} catch (Exception EX) {
			System.out.println(
					"**************** Customer NOT CREATED :[" + customerid + "] plz check log file ************");
			Utilities.handleError(EX, driver);
		}

	}

	// *****************************************************
	public static void create_Customer1(String customer, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_CustomerMaster.properties");
			String customerName = customer + "Name";
			// wait until component appears
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreateCustomerIcon"))));

			// open customer master
			driver.findElement(By.xpath(pro.getProperty("CreateCustomerIcon"))).click();
			System.out.println("clicked on customer master");
			Thread.sleep(5000);// pro

			// Click on select NA of customer ID
			WebElement NA = driver.findElement(By.xpath(pro.getProperty("sequenceformat")));
			NA.clear();
			NA.sendKeys("NA");
			Thread.sleep(1000);// pro
			NA.sendKeys(Keys.ENTER);

			Thread.sleep(2000);// pro
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("customerId"))));
			driver.findElement(By.xpath(pro.getProperty("customerId"))).sendKeys(customer);
			;

			Thread.sleep(2000);// pro
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("customerName"))));
			driver.findElement(By.xpath(pro.getProperty("customerName"))).sendKeys(customerName);
			;

			// click on Term
			WebElement term = driver.findElement(By.xpath(pro.getProperty("term")));
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);

			// credit term
			driver.findElement(By.xpath(pro.getProperty("CreditTerm"))).sendKeys("999999999");// adding
																								// new
																								// Customer
																								// ID

			// to disable salespersonagent
			WebElement salespersonagentCheckbox = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(
					driver.findElement(By.xpath("//input[@name='customervenodoravailabletosalespersonagent']"))));
			if (salespersonagentCheckbox.isSelected()) {
				salespersonagentCheckbox.click();
			}

			// first save
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(pro.getProperty("customerPersonalDetailsaveButton"))));
			driver.findElement(By.xpath(pro.getProperty("customerPersonalDetailsaveButton"))).click();
			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("yesButton"))));

			driver.findElement(By.xpath(pro.getProperty("yesButton"))).click();
			Thread.sleep(3000);// pro
			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("okButton"))));

			driver.findElement(By.xpath(pro.getProperty("okButton"))).click();
			Thread.sleep(2000);// pro
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("customerContactDetail"))));
			driver.findElement(By.xpath(pro.getProperty("customerContactDetail"))).click();

			// click on contact of customer
			driver.findElement(By.xpath(pro.getProperty("address"))).click();
			driver.findElement(By.xpath(pro.getProperty("address"))).sendKeys("office");
			driver.findElement(By.xpath(pro.getProperty("city"))).sendKeys("pune");// address
			driver.findElement(By.xpath(pro.getProperty("state"))).sendKeys("maharashtar");// maharashtra
			driver.findElement(By.xpath(pro.getProperty("country"))).sendKeys("India");// country
			driver.findElement(By.xpath(pro.getProperty("postalCode"))).sendKeys("44002");
			driver.findElement(By.xpath(pro.getProperty("mobileNo"))).sendKeys("8421279427");
			driver.findElement(By.xpath(pro.getProperty("emailID"))).sendKeys("amol.gaikwad@deskera.com");
			driver.findElement(By.xpath(pro.getProperty("contactPerson"))).sendKeys("Amol G");
			driver.findElement(By.xpath(pro.getProperty("contactPersonNo"))).sendKeys("9988774466");
			driver.findElement(By.xpath(pro.getProperty("contactPersonDesignstion"))).sendKeys("Automation");
			driver.findElement(By.xpath(pro.getProperty("website"))).sendKeys("www.deskera.com");

			WebElement checkBoxElement = driver.findElement(By.xpath(pro.getProperty("sameAsBillingCheck")));
			checkBoxElement.click();
			// first save
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(pro.getProperty("customerContactDetailsaveButton"))));
			driver.findElement(By.xpath(pro.getProperty("customerContactDetailsaveButton"))).click();

			// close
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("customerContactDetailOkButton"))));
			driver.findElement(By.xpath(pro.getProperty("customerContactDetailOkButton"))).click();
			Thread.sleep(3000);// pro

			// close
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("dashBoardtab"))));
			driver.findElement(By.xpath(pro.getProperty("dashBoardtab"))).click();
			Thread.sleep(3000);// pro

			// close customer panel
			Thread.sleep(2000);// pro
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("closeCustomerTab"))));
			driver.findElement(By.xpath(pro.getProperty("closeCustomerTab"))).click();
			System.out.println("**************** Customer created successfully :" + customer + "****************");
		} catch (Exception EX) {
			System.out
					.println("**************** Customer NOT CREATED :" + customer + " plz check log file ************");
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	// *****************************************Customer
	// Verification**************************************************

	public static void verify_Customer(String customer, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			Properties pro = Utilities.fetchProValue("OR_CustomerMaster.properties");
			WebDriverWait wait = new WebDriverWait(driver, 100);
			WebElement createcustomerIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("customerReportIcon"))));
			createcustomerIcon.click();
			Thread.sleep(4000);

			// add this before QUICK SEARCh
			WebElement longwait = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//div[@id='CustomerDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div/div[2]/div/div[1]/table/tbody/tr/td[1]")));
			if (longwait.isEnabled()) {

				WebElement search = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("quickSearch"))));
				search.sendKeys(customer);
				search.sendKeys(Keys.TAB);

				Thread.sleep(4000);

				int e = driver
						.findElements(By
								.xpath(".//*[@id='CustomerDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
						.size();
				// System.out.println(e);

				Thread.sleep(3000);

				for (int i = 1; i < e + 1; i++) {

					String columntext = driver.findElement(By
							.xpath(".//*[@id='CustomerDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();
					if (columntext.equals("Customer Code")) {
						assertEquals(customer,
								driver.findElement(By
										.xpath(".//*[@id='CustomerDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
												+ i + "]/div"))
										.getText());
					}

					// else if(columntext.equals("Name"))
					// {assertEquals(customer+"Name",
					// driver.findElement(By.xpath(".//*[@id='CustomerDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["+i+"]/div")).getText());}

					// else if(columntext.equals("Billing Address"))
					// {assertEquals("office",
					// driver.findElement(By.xpath(".//*[@id='CustomerDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["+i+"]/div")).getText());}

					else if (columntext.equals("Billing State")) {
						assertEquals("maharashtar",
								driver.findElement(By
										.xpath(".//*[@id='CustomerDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
												+ i + "]/div"))
										.getText());
					}

					// else if(columntext.equals("Contact Person"))
					// {assertEquals("Test Engineer",
					// driver.findElement(By.xpath(".//*[@id='CustomerDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["+i+"]/div")).getText());}

					else if (columntext.equals("Contact Person No.")) {
						assertEquals("9988774466",
								driver.findElement(By
										.xpath(".//*[@id='CustomerDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
												+ i + "]/div"))
										.getText());
					}

					else if (columntext.equals("Credit Term")) {
						assertEquals("NET 45",
								driver.findElement(By
										.xpath(".//*[@id='CustomerDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
												+ i + "]/div"))
										.getText());
					}

				}
				WebElement element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("closeReportTab"))));
				element.click();
				System.out.println("* * * Customer [" + customer + "] Successfully created and verified * * * *");
			}
		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	// ********************************** CopyEditDelete_Customer
	// *****************************************
	public static void CopyEditDelete_Customer(String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_CustomerMaster.properties");
			Utilities.waitandClick(pro.getProperty("customerReportIcon"), driver);
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";
			Utilities.isElementGone(xpathOfLoading, 600, driver);
			Utilities.enterTextInDropDown(customerid, pro.getProperty("quickSearch"), driver);
			Utilities.isLoadingDisappear(300, driver);

			String copyCusid = "Copy" + customerid;
			// copy
			Utilities.click_element(pro.getProperty("DocumentCheckBox"), driver);
			Utilities.click_element(pro.getProperty("manageCustomerButton"), driver);
			Utilities.click_element(pro.getProperty("copyCustomerButton"), driver);
			Utilities.isElementGone(xpathOfLoading, 600, driver);
			Utilities.enterTextNormalBox(copyCusid, pro.getProperty("customerCopyId"), driver);
			// to disable salespersonagent
			Utilities.clickCheckBox("//input[@name='customervenodoravailabletosalespersonagent']", "uncheck", driver);
			Utilities.click_element(pro.getProperty("personaldetailsCopySave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("CopycustomerClose"), driver);
			System.out.println("**** Customer [" + customerid + "] Copied successfully !!!");

			// edit
			Utilities.click_element(pro.getProperty("quickSearch"), driver);
			Utilities.enterTextInDropDown(copyCusid, pro.getProperty("quickSearch"), driver);
			Utilities.isLoadingDisappear(300, driver);
			Utilities.click_element(pro.getProperty("DocumentCheckBox"), driver);
			Utilities.click_element(pro.getProperty("manageCustomerButton"), driver);
			Utilities.click_element(pro.getProperty("EditCustomerButton"), driver);
			// to disable agent
			Utilities.clickCheckBox("//input[@name='customervenodoravailabletosalespersonagent']", "uncheck", driver);
			Utilities.enterTextNormalBox("CustomerUEN", pro.getProperty("CustomerUEN"), driver);
			Utilities.click_element(pro.getProperty("personaldetailsEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("EditcustomerClose"), driver);
			System.out.println("**** Customer [" + copyCusid + "] Edited successfully !!!");

			// delete
			Utilities.click_element(pro.getProperty("quickSearch"), driver);
			Utilities.enterTextInDropDown(copyCusid, pro.getProperty("quickSearch"), driver);
			Utilities.click_element(pro.getProperty("DocumentCheckBox"), driver);
			Utilities.click_element(pro.getProperty("manageCustomerButton"), driver);
			Utilities.click_element(pro.getProperty("DeleteCustomerButton"), driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			// verify flow
			Utilities.click_element(pro.getProperty("quickSearch"), driver);
			Utilities.enterTextInDropDown(copyCusid, pro.getProperty("quickSearch"), driver);
			Utilities.isLoadingDisappear(120, driver);
			WebElement confirmation = new WebDriverWait(driver, 10).until(ExpectedConditions
					.elementToBeClickable(By.xpath("//a[contains(text(),'Get Started by adding a Customer now')]")));
			if (confirmation.isDisplayed()) {
				System.out.println("**** Customer [" + copyCusid + "] is DELETED successfully !!!");
			} else {
				System.out.println("**** Customer [" + copyCusid + "] is NOT DELETED plz check  !!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}

			Utilities.HoverandClick(pro.getProperty("closeReportTab"), driver);
		} catch (Exception EX) {
			System.out.println("**** Customer [" + customerid + "] is NOT DELETED plz check  !!!");
			Utilities.handleError(EX, driver);
		}
	}

	// * * * * * ** * * * * * * * EXPORT Customer Master * * * * * * ** * * * *
	// * * *
	public static void Export_Customer(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_CustomerMaster.properties");
			Utilities.click_element(pro.getProperty("customerReportIcon"), driver);
			WebElement element = null;

			Utilities.selectRecords(10, driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			// CSV
			Utilities.clickButton("Export selected Record(s)", 500, driver);
			Utilities.HoverandClick(pro.getProperty("ExportToCSV"), driver);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
			element.click();
			Thread.sleep(1000);
			SikulliUtil.sikulli_waitClick(driver, "CSVtype");
			SikulliUtil.sikulli_waitClick(driver, "ClsX");
			System.out.println("* * * * * * EXPORT for [.CSV] completed successfully * * * * * * *");
			Thread.sleep(1500);

			// PDF
			Utilities.clickButton("Export selected Record(s)", 500, driver);
			String parentWindow = driver.getWindowHandle();
			Utilities.HoverandClick(pro.getProperty("ExportToPDF"), driver);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("pdfTemplate"))));
			element.click();
			Thread.sleep(1000);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
			element.click();
			Thread.sleep(2000);
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
			Utilities.clickButton("Export selected Record(s)", 500, driver);
			Utilities.HoverandClick(pro.getProperty("ExportToExcel"), driver);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
			element.click();
			Thread.sleep(1000);
			SikulliUtil.sikulli_waitClick(driver, "XLStype");
			SikulliUtil.sikulli_waitClick(driver, "ClsX");
			System.out.println("* * * * * * EXPORT for [.Excel] completed successfully * * * * * * *");

			Utilities.HoverandClick(pro.getProperty("closeReportTab"), driver);
			System.out.println("* * * * * * EXPORT for [CUSTOMER MASTER] completed successfully * * * * * * *");
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [Customer MASTER] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}
	}

	// get button path for Specific Module
	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;

	}

	// ******** Delete all Imported Data **********************

	public static void deleteImport_Customers(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_CustomerMaster.properties");
			Utilities.waitandClick(pro.getProperty("customerReportIcon"), driver);
			// Thread.sleep(3000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown("Automation9", pro.getProperty("quickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);

			// delete
			Utilities.clickCheckBox("//*[text()='Customer Code']/ancestor::tr/td[1]/div/div", "check", driver);
			Utilities.HoverandClick(pro.getProperty("manageCustomerButton"), driver);
			Utilities.HoverandClick(pro.getProperty("DeleteCustomerButton"), driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);// pro

			// verify flow
			Utilities.enterTextInDropDown("Automation9", pro.getProperty("quickSearch"), driver);
			Thread.sleep(1500);
			WebElement confirmation = new WebDriverWait(driver, 10).until(ExpectedConditions
					.elementToBeClickable(By.xpath("//a[contains(text(),'Get Started by adding a Customer now')]")));
			if (confirmation.isDisplayed()) {
				System.out.println("**** ALL Imported Customer are DELETED successfully !!!");
			} else {
			}

			Utilities.HoverandClick(pro.getProperty("closeReportTab"), driver);
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// *****************************************************************************************************
	public static void create_Customer_Proper(String customerid, String customerName, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_CustomerMaster.properties");

			Utilities.waitandClick(pro.getProperty("CreateCustomerIcon"), driver);
			Utilities.click_element("//input[@name='customervenodoravailabletosalespersonagent']", driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceformat"), driver);
			Utilities.enterTextNormalBox(customerid, pro.getProperty("customerId"), driver);
			Utilities.enterTextNormalBox(customerName, pro.getProperty("customerName"), driver);
			Utilities.enterTextandSelect("NET 45", pro.getProperty("term"), driver);
			Utilities.enterTextNormalBox("1000", pro.getProperty("CreditTerm"), driver);
			// first save
			Utilities.click_element(pro.getProperty("customerPersonalDetailsaveButton"), driver);
			Utilities.click_element(pro.getProperty("yesButton"), driver);
			Utilities.click_element(pro.getProperty("okButton"), driver);

			// ContactDetail Tab
			Utilities.HoverandClick(pro.getProperty("customerContactDetail"), driver);
			// click on contact of customer
			driver.findElement(By.xpath(pro.getProperty("address"))).click();
			driver.findElement(By.xpath(pro.getProperty("address"))).sendKeys("Singapore");
			driver.findElement(By.xpath(pro.getProperty("city"))).sendKeys("Singapore ");// address
			driver.findElement(By.xpath(pro.getProperty("state"))).sendKeys("Singapore ");// maharashtra
			driver.findElement(By.xpath(pro.getProperty("country"))).sendKeys("Singapore ");// country
			driver.findElement(By.xpath(pro.getProperty("postalCode"))).sendKeys("44002");
			driver.findElement(By.xpath(pro.getProperty("mobileNo"))).sendKeys("9900012345");
			driver.findElement(By.xpath(pro.getProperty("emailID"))).sendKeys(customerName + "@deskera.com");
			driver.findElement(By.xpath(pro.getProperty("contactPerson"))).sendKeys(customerName);
			driver.findElement(By.xpath(pro.getProperty("contactPersonNo"))).sendKeys("9900012345");
			driver.findElement(By.xpath(pro.getProperty("contactPersonDesignstion"))).sendKeys("QA");
			driver.findElement(By.xpath(pro.getProperty("website"))).sendKeys("www.deskera.com");
			Thread.sleep(1000);
			Utilities.click_element(pro.getProperty("sameAsBillingCheck"), driver);
			Utilities.click_element(pro.getProperty("customerContactDetailsaveButton"), driver);
			Utilities.click_element(pro.getProperty("customerContactDetailOkButton"), driver);

			Utilities.HoverandClick(pro.getProperty("dashBoardtab"), driver);
			Utilities.HoverandClick(pro.getProperty("closeCustomerTab"), driver);
			System.out
					.println("**************** Customer [" + customerName + "] created successfully ****************");
		} catch (Exception EX) {
			System.out.println("**************** Failed to create Customer [" + customerName
					+ "] plz check console ****************");
			Utilities.handleError(EX, driver);
		}

	}

}
