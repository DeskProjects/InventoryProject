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

import krawler.erp.page.Utilities;

public class LeaseContract2 {
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

			// Set Sequence format document no.
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

			// String check="contract"+Asset_ID;
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
			System.out.println("\"Lease Contract\" is created successfully : " + documentid1);
			Utilities.refresh();

		} catch (Exception EX) {

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(2000);// pro

	}

}
