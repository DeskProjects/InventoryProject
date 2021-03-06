package krawler.erp.RegressionLeaseManagementWithInventory;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class LeaseContract {
	private static final Properties LM = null;

	public void LM_CreateContract(String documentid, String productid, String customerid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			String documentid1 = "contract" + documentid;
			String ContactPerson = "Sonam";
			String duration = "10";
			Properties LM = Utilities.fetchProValue("OR_LeaseContract.properties");
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement LMButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseContractIcon")))); // examining
			LMButton.click();

			Thread.sleep(4000);// proLeaseProduct123

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("CustomerId"))));
			WebElement customer = driver.findElement(By.xpath(LM.getProperty("CustomerId")));
			customer.clear();
			customer.sendKeys(customerid);
			Thread.sleep(2000);// pro
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);

			Utilities.enterTextInDropDown("order" + documentid, LM.getProperty("LeaseOrderDLink"), driver);

			// To given Sequence format document no.
			WebElement squence = driver.findElement(By.xpath(LM.getProperty("sequenceFormat")));
			Thread.sleep(2000);
			squence.clear();
			Thread.sleep(1000);
			squence.sendKeys("NA");
			Thread.sleep(2000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// To given Sequence format document no.
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("ContractId"))));
			driver.findElement(By.xpath(LM.getProperty("ContractId"))).sendKeys(documentid1);

			// String check="contract"+documentid;
			// System.out.println(">>>>"+check);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("ContactPerson"))));
			driver.findElement(By.xpath(LM.getProperty("ContactPerson"))).sendKeys(ContactPerson);

			Utilities.enterTextInDropDown("Day", LM.getProperty("ContractTerm1"), driver);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("ContractTerm2"))));
			driver.findElement(By.xpath(LM.getProperty("ContractTerm2"))).sendKeys(duration);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("ContractStartDate"))));
			driver.findElement(By.xpath(LM.getProperty("ContractStartDate"))).click();

			Utilities.clickButton("Today", 2000, driver);

			driver.findElement(By.xpath(LM.getProperty("meomo"))).click();
			Thread.sleep(2000);// pro

			// Click on Maintenance Schedule Button.
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("MaintenanceSchedulebtn"))));
			driver.findElement(By.xpath(LM.getProperty("MaintenanceSchedulebtn"))).click();
			Thread.sleep(2000);

			// Fill the details for Maintenance Scheduler
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("ScheduleName"))));
			driver.findElement(By.xpath(LM.getProperty("ScheduleName"))).sendKeys("Schd" + documentid);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Scheduledate1"))));
			driver.findElement(By.xpath(LM.getProperty("Scheduledate1"))).click();
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("TodayBtn"))));
			driver.findElement(By.xpath(LM.getProperty("TodayBtn"))).click();
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Scheduleduration"))));
			driver.findElement(By.xpath(LM.getProperty("Scheduleduration"))).sendKeys("1");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Adhocsch"))));
			driver.findElement(By.xpath(LM.getProperty("Adhocsch"))).click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("TotalEvent"))));
			driver.findElement(By.xpath(LM.getProperty("TotalEvent"))).sendKeys("1");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Adhocbtn"))));
			driver.findElement(By.xpath(LM.getProperty("Adhocbtn"))).click();
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Gridentry"))));
			driver.findElement(By.xpath(LM.getProperty("Gridentry"))).click();
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Griddate"))));
			driver.findElement(By.xpath(LM.getProperty("Griddate"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("TodayBtn1"))));
			driver.findElement(By.xpath(LM.getProperty("TodayBtn1"))).click();
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Blankspace"))));
			driver.findElement(By.xpath(LM.getProperty("Blankspace"))).click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Gridsave"))));
			driver.findElement(By.xpath(LM.getProperty("Gridsave"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("TableSave"))));
			driver.findElement(By.xpath(LM.getProperty("TableSave"))).click();
			Thread.sleep(3000);

			// Click on save button.
			Utilities.clickButton("Yes", 1000, driver);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("saveButton"))));
			WebElement Save = driver.findElement(By.xpath(LM.getProperty("saveButton")));
			Save.click();

			Utilities.clickButton("Yes", 1000, driver);

			Utilities.clickButton("OK", 1000, driver);
			System.out.println("Lease Contract is created successfully : " + documentid1);
			Utilities.refresh();

		} catch (Exception EX) {

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(2000);// pro

	}

	// ****************************************************************************Verification**********************************************************************************************************

	public static void VerifyLM_Contract(String documentid, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			String documentid1 = "contract" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_LeaseContract.properties");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("LeaseContractReport"))));
			driver.findElement(By.xpath(pro.getProperty("LeaseContractReport"))).click();
			Thread.sleep(5000);// pro

			// Utilities.clickCheckBox(pro.getProperty(""), "unchek", driver);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid1);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			int lsOrderRowIndex = 0, customerRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath("//div[@id='contractorderreportEntry']/div/div/div/div/div/div/div/div[2]/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			Thread.sleep(1000);

			for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
				String header = driver.findElement(By
						.xpath("//div[@id='contractorderreportEntry']/div/div/div/div/div/div/div/div[2]/div/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equals("Contract ID")) {
					lsOrderRowIndex = rowIndex;
					// System.out.println(lsOrderRowIndex);

				} else if (header.equals("Customer")) {
					customerRowIndex = rowIndex;
					// System.out.println(customerRowIndex);

				}
			}

			List<WebElement> totalLinks = driver
					.findElements(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div"));
			int resultTotalCnt = totalLinks.size();
			// System.out.println("totalResultcnt > > "+resultTotalCnt);

			for (int j = 1; j <= resultTotalCnt; j++) {

				assertEquals(documentid1,
						driver.findElement(
								By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div/table/tbody/tr[1]/td["
										+ lsOrderRowIndex + "]/div"))
								.getText());

				System.out.println("UI DOC ID = " + driver
						.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div/table/tbody/tr[1]/td["
								+ lsOrderRowIndex + "]/div"))
						.getText());

				assertEquals(customerid + "Name",
						driver.findElement(
								By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div/table/tbody/tr[1]/td["
										+ customerRowIndex + "]/div"))
								.getText());

				System.out.println("UI customer ID = " + driver
						.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div/table/tbody/tr[1]/td["
								+ customerRowIndex + "]/div"))
						.getText());
			}

			System.out.println(
					"Document ID and Customer Name is present in system, hance Document verified successfully ["
							+ documentid1 + "]");
			Utilities.refresh();
		}

		catch (Exception EX) {
			System.out.println(" Document is not verified. [" + "contract" + documentid + "]");
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	// **************************************************************************Edit/Delete
	// ***************************************************************************//

	public void Delete_LeaseContract(String documentid1, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid1 = "contract" + documentid1;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_LeaseContract.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("LeaseContractReport"))));
			driver.findElement(By.xpath(pro.getProperty("LeaseContractReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid1);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			WebElement checkbox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("reportCheckBox"))));
			checkbox.click();
			Thread.sleep(2000);

			WebElement editcustomerButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditLObutton"))));
			editcustomerButton.click();
			Thread.sleep(3000);

			WebElement LCQmemo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditMemo"))));
			LCQmemo.sendKeys("EdotLeaseContractMemo");
			Thread.sleep(3000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("buttonSave"))));
			driver.findElement(By.xpath(pro.getProperty("buttonSave"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1500, driver);

			WebElement clsEditBtn = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("closeEditWin"))));
			clsEditBtn.click();

			Thread.sleep(3000);
			search.clear();
			Thread.sleep(1000);
			search.sendKeys(documentid1);
			search.sendKeys(Keys.TAB);
			Thread.sleep(3000);

			WebElement checkbox1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("reportCheckBox"))));
			checkbox1.click();

			WebElement TermButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Terminate"))));
			TermButton.click();
			;

			Thread.sleep(3000);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(3000);

			WebElement checkbox2 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("reportCheckBox"))));
			checkbox2.click();
			WebElement DeleteCQButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DeleteButton"))));
			DeleteCQButton.click();
			;
			Thread.sleep(5000);

			/*
			 * WebElement PermanentDeleteButton =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.
			 * getProperty("PermanantDelete")))); PermanentDeleteButton.click();
			 * Thread.sleep(3000);
			 */

			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("YesButton"))));
			driver.findElement(By.xpath(pro.getProperty("YesButton"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(1000);
			String expected = "There is no record to display.";

			WebElement search4 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search4.clear();
			search4.sendKeys(documentid1);
			search4.sendKeys(Keys.TAB);
			Thread.sleep(4000);

			WebElement expResul = driver.findElement(By.xpath(pro.getProperty("Title")));

			if (expResul.isDisplayed()) {
				assertEquals(expected, expResul.getText());
				System.out.println("Lease Contract is sucessfully deleted");
			} else {
				System.out.println("Lease Contract is not deleted");
			}

			Utilities.refresh();

		}

		catch (Exception EX) {
			Thread.sleep(3000);// pro
			EX.printStackTrace();
			// Utilities.handleError(EX, driver);

		}
	}
}
