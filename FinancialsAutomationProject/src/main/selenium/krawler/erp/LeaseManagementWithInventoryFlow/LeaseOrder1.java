package krawler.erp.LeaseManagementWithInventoryFlow;

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

public class LeaseOrder1 {
	private static final Properties LM = null;

	public void LM_CreateOrder(String documentid, String productid, String customerid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			String documentid1 = "order" + documentid;
			Properties LM = Utilities.fetchProValue("OR_LM_CreateOrder.properties");
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement LMButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseOrderIcon")))); // examining
			LMButton.click();
			Thread.sleep(4000);

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

			Utilities.enterTextInDropDown("Yes", LM.getProperty("Link"), driver);

			Utilities.enterTextInDropDown("Lease Quotation", LM.getProperty("linkTo"), driver);

			// String check="order"+documentid;
			// System.out.println(">>>>"+check);

			// Utilities.enterTextInDropDown(check, LM.getProperty("number"),
			// driver);

			// To open Drop down
			WebElement drpDwBtn = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LM.getProperty("Opendropdown"))));
			drpDwBtn.click();

			// Send doc id
			WebElement inpfield = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LM.getProperty("senddocid"))));
			inpfield.click();
			Thread.sleep(1000);
			Utilities.sendText("quotation" + documentid);
			Thread.sleep(2000);

			// Select listed product
			WebElement result = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("sellistprod"))));
			result.click();
			Thread.sleep(2000);

			// Select ok arrow
			WebElement selectdrpDwBtn = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("okarrow"))));
			selectdrpDwBtn.click();
			Thread.sleep(1500);

			driver.findElement(By.xpath(LM.getProperty("meomo"))).click();
			Thread.sleep(2000);// pro

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
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseOrderNumber"))));
			driver.findElement(By.xpath(LM.getProperty("LeaseOrderNumber"))).sendKeys(documentid1);

			WebElement term = driver.findElement(By.xpath(LM.getProperty("CreditTerm")));
			term.clear();
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("saveButton"))));
			WebElement Save = driver.findElement(By.xpath(LM.getProperty("saveButton")));
			Save.click();

			Utilities.clickButton("Yes", 1000, driver);

			Utilities.clickButton("OK", 1000, driver);
			System.out.println("\"Lease Order doucment\"is created successfully : " + documentid1);
			Utilities.refresh();

		} catch (Exception EX) {

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(2000);// pro
	}

	// ------------------------------------------------------Create Second Lease
	// Order linked with Replcement Request
	// -----------------------------------------------------------

	public void LM_CreateOrde_linked_WithReplacement(String documentid, String productid, String customerid,
			WebDriver driver, String MyCreatedno) throws InterruptedException, IOException, AWTException {
		try {

			String documentid1 = "order1" + documentid;
			Properties LM = Utilities.fetchProValue("OR_LM_CreateOrder.properties");
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement LMButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseOrderIcon")))); // examining
			LMButton.click();
			Thread.sleep(4000);

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

			Utilities.enterTextInDropDown("Yes", LM.getProperty("Link"), driver);

			Utilities.enterTextInDropDown("Replacement Number", LM.getProperty("linkTo"), driver);

			// Utilities.enterTextInDropDown(check, LM.getProperty("number"),
			// driver);

			// To open Drop down
			WebElement drpDwBtn = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LM.getProperty("Opendropdown"))));
			drpDwBtn.click();

			// Send doc id
			WebElement inpfield = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LM.getProperty("senddocid"))));
			inpfield.click();
			Thread.sleep(1000);

			Utilities.sendText(MyCreatedno);// Fetch from already stored
											// doucment in CRMHandle.java
											// Class's CRM_ReplacementRequest
											// method
			Thread.sleep(2000);

			// Select listed product
			WebElement result = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("sellistprod"))));
			result.click();
			Thread.sleep(2000);

			// Select ok arrow
			WebElement selectdrpDwBtn = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("okarrow"))));
			selectdrpDwBtn.click();
			Thread.sleep(1500);

			driver.findElement(By.xpath(LM.getProperty("meomo"))).click();
			Thread.sleep(2000);// pro

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
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseOrderNumber"))));
			driver.findElement(By.xpath(LM.getProperty("LeaseOrderNumber"))).sendKeys(documentid1);

			WebElement term = driver.findElement(By.xpath(LM.getProperty("CreditTerm")));
			term.clear();
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// For put the Unit price
			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='fixedassetleasesalesordereditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='fixedassetleasesalesordereditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Unit Price")) {
					driver.findElement(By
							.xpath("//div[@id='fixedassetleasesalesordereditproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table[1]/tbody[1]/tr[1]/td["
									+ i + "]/div"))
							.click();
					break;
				}
			}
			Thread.sleep(2000);
			Utilities.sendText("100");
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("saveButton"))));
			WebElement Save = driver.findElement(By.xpath(LM.getProperty("saveButton")));
			Save.click();

			Utilities.clickButton("Yes", 1000, driver);

			Utilities.clickButton("OK", 1000, driver);
			System.out.println("\"Lease Order\" linked with Replacement  is created successfully: " + documentid1);
			Utilities.refresh();

		} catch (Exception EX) {

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(2000);// pro
	}
}
