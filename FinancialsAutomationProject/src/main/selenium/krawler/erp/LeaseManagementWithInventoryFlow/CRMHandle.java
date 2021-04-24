package krawler.erp.LeaseManagementWithInventoryFlow;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class CRMHandle {
	public static String MaintenanceNumberforUse = null; // Declare for
															// Maintenance
															// Number.
	public static String MyCreatedno = null; // Declare for Replacement Number.

	public void CRM_Case(String documentid, WebDriver driver, String customerid)
			throws InterruptedException, IOException, AWTException {
		try {
			String documentid1 = documentid;
			Properties LM = Utilities.fetchProValue("OR_CRM.properties");
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CaseIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("CRMCase"))));
			CaseIcon.click();
			Thread.sleep(2000);

			// Click on Add button for Case
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("AddBtn"))));
			driver.findElement(By.xpath(LM.getProperty("AddBtn"))).click();
			Thread.sleep(1000);

			// Fill the details for Case
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Subject"))));
			driver.findElement(By.xpath(LM.getProperty("Subject"))).sendKeys("NewCase" + documentid1);
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Status"))));
			driver.findElement(By.xpath(LM.getProperty("Status"))).sendKeys("New Case");
			Thread.sleep(2000);
			driver.findElement(By.xpath(LM.getProperty("StatusClick"))).click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Priority"))));
			driver.findElement(By.xpath(LM.getProperty("Priority"))).sendKeys("Normal");
			Thread.sleep(2000);
			driver.findElement(By.xpath(LM.getProperty("PriorityClick"))).click();
			Thread.sleep(1000);

			selectFromNormalDropDown(customerid + "Name", LM.getProperty("Account"), driver);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Submit"))));
			driver.findElement(By.xpath(LM.getProperty("Submit"))).click();
			Thread.sleep(3000);

			driver.findElement(By.xpath(LM.getProperty("CloseCasetab"))).click();
			Thread.sleep(2000);
			System.out.println(" A case is created successfully from CRM application");
			Utilities.refresh();

		} catch (Exception EX) {

			Utilities.handleError(EX, driver);
			System.out.println("CRM Case is not created successfully ");
		}
		Thread.sleep(2000);// pro
	}

	// ---------------------------------------- Maintenance Request Raised from
	// CRM Side.------------------------------------------

	public void CRM_MaintenanceRequest(WebDriver driver, String customerid, String documentid, String productid)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentid1 = "contract" + documentid;
			Properties LM = Utilities.fetchProValue("OR_CRM.properties");
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement AccountIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("AccountIcon"))));
			AccountIcon.click();
			Thread.sleep(2000);

			// Click on Lease Sales Contract Button given under the account
			// module.
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseSalesCotractbutton"))));
			driver.findElement(By.xpath(LM.getProperty("LeaseSalesCotractbutton"))).click();
			Thread.sleep(2000);

			// Click on Maintenance Service List button.
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("MaintenanceButton"))));
			driver.findElement(By.xpath(LM.getProperty("MaintenanceButton"))).click();
			Thread.sleep(2000);

			// Click on Add button given in Maintenance Service Report.
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("AddMaintenance"))));
			driver.findElement(By.xpath(LM.getProperty("AddMaintenance"))).click();
			Thread.sleep(2000);

			// Fill the information in Maintenance Entry form
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("MaintenanceAmount"))));
			driver.findElement(By.xpath(LM.getProperty("MaintenanceAmount"))).sendKeys("1000");
			Thread.sleep(2000);

			selectFromNormalDropDown(customerid + "Name", LM.getProperty("AccountNameforMaintenance"), driver);

			selectFromNormalDropDown("NewCase" + documentid, LM.getProperty("CaseName"), driver);

			selectFromNormalDropDown(documentid1, LM.getProperty("LeaseContractID"), driver);

			String drpDwnArrow = LM.getProperty("ProductDropdown");
			String itemInputBoxLocator = LM.getProperty("ProductDropdown1");
			String itemName = productid + "BS4 Name";
			String rslTable = "//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]//*[text()='"
					+ itemName + "']";
			Utilities.selectItemfromDropDown(drpDwnArrow, itemInputBoxLocator, itemName, rslTable, driver);

			// selectFromNormalDropDown(productid+"BS4 Name"
			// ,LM.getProperty("ProductDropdown1"), driver);

			selectFromNormalDropDown("Open", LM.getProperty("MaintenanceStatus"), driver);

			selectFromNormalDropDown("Planned", LM.getProperty("MaintenanceType"), driver);

			driver.findElement(By.xpath(LM.getProperty("MemoforMaint")))
					.sendKeys("Document created by Automation Script");
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("MaintetnaceDate"))));
			driver.findElement(By.xpath(LM.getProperty("MaintetnaceDate"))).click();
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("NextDate"))));
			driver.findElement(By.xpath(LM.getProperty("NextDate"))).click();
			Thread.sleep(2000);

			driver.findElement(By.xpath(LM.getProperty("SaveforMaint"))).click();
			Thread.sleep(2000);

			// For capturing Maintenance Number.
			String SuccessMessageContain = driver.findElement(By.xpath(LM.getProperty("Suceesmessgaecontai")))
					.getText();
			MaintenanceNumberforUse = mainNumber(SuccessMessageContain, "MF0");
			// System.out.println(MaintenanceNumberforUse);
			Thread.sleep(1000);

			Utilities.clickButton("OK", 1000, driver);

			driver.findElement(By.xpath(LM.getProperty("CloseMaintancetab"))).click();
			Thread.sleep(2000);
			System.out.println("Maintenance Request is created successfully from CRM application");
			driver.quit();
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
			System.out.println("Maintenance Request is not created successfully  ");
		}
		Thread.sleep(2000);// pro
	}

	// Method for storing Maintenance Number from Success message.
	public static String mainNumber(String text, String prefix) {
		String mainNum = "";
		StringTokenizer st = new StringTokenizer(text);
		while (st.hasMoreTokens()) {
			String subString = st.nextToken();
			if (subString.startsWith(prefix)) {
				mainNum = subString;
				break;
			}
		}
		return mainNum;
	}

	// --------------------------------------------------------Maintenance
	// Request Verification--------------------------------------------------

	public void CRM_MaintenanceRequest_Verification(WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties LM = Utilities.fetchProValue("OR_CRM.properties");
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement AccountIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("AccountIcon"))));
			AccountIcon.click();
			Thread.sleep(2000);

			// Click on Lease Sales Contract Button given under the account
			// module.
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseSalesCotractbutton"))));
			driver.findElement(By.xpath(LM.getProperty("LeaseSalesCotractbutton"))).click();
			Thread.sleep(2000);

			// Click on Maintenance Service List button.
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("MaintenanceButton"))));
			driver.findElement(By.xpath(LM.getProperty("MaintenanceButton"))).click();
			Thread.sleep(2000);

			// Search Maintenance Number
			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("QuickSearch1"))));
			search.sendKeys(MaintenanceNumberforUse);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			int lsOrderRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath("//div[@id='maintenanceformGridID']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td/div"))
					.size();
			Thread.sleep(1000);

			for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
				String header = driver.findElement(By
						.xpath("//div[@id='maintenanceformGridID']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equals("Maintenance Status")) {
					lsOrderRowIndex = rowIndex;
				}
			}
			String actual = "Closed";
			String expected = driver.findElement(
					By.xpath("//div[@id='maintenanceformGridID']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
							+ lsOrderRowIndex + "]/div"))
					.getText();
			if (actual.equalsIgnoreCase(expected)) {
				System.out.println("Maintenance Status is being Closed now after created Sales Order");
			} else {
				System.out.println("Maintenance Status is not being Closed. Please check the reason behind of this");
			}
			Utilities.refresh();
		}

		catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
		Thread.sleep(2000);// pro
	}

	// ------------------------------------------------------Replacement Request
	// ---------------------------------------------

	public void CRM_ReplacementRequest(WebDriver driver, String customerid, String documentid, String productid)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentid1 = "contract" + documentid;
			Properties LM = Utilities.fetchProValue("OR_CRM.properties");
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement AccountIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("AccountIcon"))));
			AccountIcon.click();
			Thread.sleep(2000);

			// Click on Lease Sales Contract button given under the Account
			// module.
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseSalesCotractbutton"))));
			driver.findElement(By.xpath(LM.getProperty("LeaseSalesCotractbutton"))).click();
			Thread.sleep(2000);

			// Click on Replacement Request List button.
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("ReplacementButton"))));
			driver.findElement(By.xpath(LM.getProperty("ReplacementButton"))).click();
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("AddREplacement"))));
			driver.findElement(By.xpath(LM.getProperty("AddREplacement"))).click();
			Thread.sleep(2000);

			selectFromNormalDropDown(customerid + "Name", LM.getProperty("CustomerSelction"), driver);

			selectFromNormalDropDown(documentid1, LM.getProperty("Contractid"), driver);

			selectFromNormalDropDown("NewCase" + documentid, LM.getProperty("CaseID"), driver);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Description"))));
			driver.findElement(By.xpath(LM.getProperty("Description")))
					.sendKeys("Replacement Request is created via Automation script.");
			Thread.sleep(2000);

			// Select the Product and quantity and then update the serial
			// information
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath(LM.getProperty("ProductSelection0")))).doubleClick()
					.perform();
			selectFromNormalDropDown(productid + "BS4 Name", LM.getProperty("ProductSelection1"), driver);

			action.moveToElement(driver.findElement(By.xpath(LM.getProperty("Quantityput")))).doubleClick().perform();
			Utilities.sendText("1");
			Thread.sleep(1000);

			driver.findElement(By.xpath(LM.getProperty("Description"))).click();
			Thread.sleep(2000);

			action.moveToElement(driver.findElement(By.xpath(LM.getProperty("SerialUpdate")))).doubleClick().perform();

			// Select the serial number from inner serial window.
			action.moveToElement(driver.findElement(By.xpath(LM.getProperty("SerialSelection")))).doubleClick()
					.perform();
			selectFromNormalDropDown("Serial1", LM.getProperty("SerialSelection1"), driver);

			// Click on Save button given in Serial Number inner window.
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("SaveButtonforInner"))));
			driver.findElement(By.xpath(LM.getProperty("SaveButtonforInner"))).click();
			Thread.sleep(2000);

			// Click on Save button given in Replacement Window
			Utilities.clickButton("Save", 1000, driver);

			Utilities.clickButton("OK", 1000, driver);

			System.out.println("Replacement Request is raised successfully from CRM application");

			// To Store Replacement Number for further operations
			int indexofCustomerName = 0, rpNumber = 0;
			for (int i = 1; i <= driver.findElements(By.xpath("//*[text()='Replacement No.']/ancestor::tr/td/div"))
					.size(); i++) {
				String Headername = driver
						.findElement(By.xpath("//*[text()='Replacement No.']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (Headername.equalsIgnoreCase("Account Name")) {
					indexofCustomerName = i;
				} else if (Headername.equalsIgnoreCase("Replacement No.")) {
					rpNumber = i;
				}
			}
			for (int i = 1; i <= driver
					.findElements(
							By.xpath("//*[text()='Replacement No.']/ancestor::div[3]/following::div[1]/div/div/table"))
					.size(); i++) {
				String cusName = driver.findElement(
						By.xpath("//*[text()='Replacement No.']/ancestor::div[3]/following::div[1]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexofCustomerName + "]/div"))
						.getText();

				if (cusName.equalsIgnoreCase(customerid + "Name")) {
					String MyCreatedno = driver.findElement(
							By.xpath("//*[text()='Replacement No.']/ancestor::div[3]/following::div[1]/div/div[" + i
									+ "]/table/tbody/tr/td[" + rpNumber + "]/div"))
							.getText();
					// System.out.println(MyCreatedno);
				}
			}
			driver.quit();
		}

		catch (Exception EX) {
			Utilities.handleError(EX, driver);
			System.out.println("Replacement Request is not being raised");
		}
		Thread.sleep(2000);
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
