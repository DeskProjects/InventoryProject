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

public class LeaseDeliveryOrder2 {
	private static final Properties LM = null;

	public void LM_CreateDeliveryOrder(String documentid, String productid, String customerid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			String documentid1 = "delivery" + documentid;
			Properties LM = Utilities.fetchProValue("OR_LeaseDelivery.properties");
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement LMButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseDOIcon")))); // examining
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

			Utilities.enterTextInDropDown("Yes", LM.getProperty("Link"), driver);

			Utilities.enterTextInDropDown("Lease Order", LM.getProperty("linkTo"), driver);

			String check = "order" + productid;
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
			Utilities.sendText("order" + documentid);
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
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseDOOrderNumber"))));
			driver.findElement(By.xpath(LM.getProperty("LeaseDOOrderNumber"))).sendKeys(documentid1);
			Thread.sleep(2000);

			// For Serial Number Information
			// For Icon
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("serialicon"))));
			driver.findElement(By.xpath(LM.getProperty("serialicon"))).click();
			Thread.sleep(2000);

			// For Batch Information
			/*
			 * new WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(LM.getProperty("Batch1"))));
			 * driver.findElement(By.xpath(LM.getProperty("Batch1"))).click();
			 * Thread.sleep(2000);
			 * 
			 * // For Batch Selection new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(LM.getProperty("Selcetion1"))));
			 * driver.findElement(By.xpath(LM.getProperty("Selcetion1"))).click(
			 * ); Thread.sleep(1000);
			 * 
			 * new WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(LM.getProperty("OK1"))));
			 * driver.findElement(By.xpath(LM.getProperty("OK1"))).click();
			 * Thread.sleep(1000);
			 * 
			 * //For Serial Information new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(LM.getProperty("S1"))));
			 * driver.findElement(By.xpath(LM.getProperty("S1"))).click();
			 * Thread.sleep(1000);
			 * 
			 * //Set first Serial Information new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(LM.getProperty("S100"))));
			 * driver.findElement(By.xpath(LM.getProperty("S100"))).click();
			 * Thread.sleep(1000);
			 * 
			 * 
			 * //Clicking on arrow image for set serial number new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(LM.getProperty("Arrow1"))));
			 * driver.findElement(By.xpath(LM.getProperty("Arrow1"))).click();
			 * Thread.sleep(1000);
			 * 
			 * //Clicking on submit(internal) button given in at warehouse
			 * window. new WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(LM.getProperty(
			 * "Warehousewindowsubmit"))));
			 * driver.findElement(By.xpath(LM.getProperty(
			 * "Warehousewindowsubmit"))).click(); Thread.sleep(1000);
			 * 
			 * //Clicking on submit(External) button given in at waherhouse
			 * window. new WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(LM.getProperty("submitbutton"))));
			 * driver.findElement(By.xpath(LM.getProperty("submitbutton"))).
			 * click(); Thread.sleep(2000);
			 */

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("saveButton"))));
			WebElement Save = driver.findElement(By.xpath(LM.getProperty("saveButton")));
			Save.click();
			Thread.sleep(1000);

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);

			Utilities.clickButton("OK", 1000, driver);

			Utilities.refresh();

			System.out.println("\"Lease Delivery Order\" document is created successfully: " + documentid1);

		} catch (Exception EX) {

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(2000);// pro

	}

	// --------------------------------------------- Create Lease Delivery Oder
	// Linked with second Lease Order
	// ----------------------------------------------------------------------------

	public void LM_CreateLDO_With_SecondLO(String documentid, String productid, String customerid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			String documentid1 = "delivery1" + documentid;
			Properties LM = Utilities.fetchProValue("OR_LeaseDelivery.properties");
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement LMButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseDOIcon")))); // examining
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

			Utilities.enterTextInDropDown("Yes", LM.getProperty("Link"), driver);

			Utilities.enterTextInDropDown("Lease Order", LM.getProperty("linkTo"), driver);

			// String check="order"+Asset_ID;
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
			Utilities.sendText("order1" + documentid);
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
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseDOOrderNumber"))));
			driver.findElement(By.xpath(LM.getProperty("LeaseDOOrderNumber"))).sendKeys(documentid1);
			Thread.sleep(2000);

			// For Serial Number Information
			// For Icon
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("serialicon"))));
			driver.findElement(By.xpath(LM.getProperty("serialicon"))).click();
			Thread.sleep(2000);

			// For Batch Information
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Batch1"))));
			driver.findElement(By.xpath(LM.getProperty("Batch1"))).click();
			Thread.sleep(2000);

			// For Batch Selection
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Selcetion1"))));
			driver.findElement(By.xpath(LM.getProperty("Selcetion1"))).click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("OK1"))));
			driver.findElement(By.xpath(LM.getProperty("OK1"))).click();
			Thread.sleep(1000);

			// For Serial Information
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("S1"))));
			driver.findElement(By.xpath(LM.getProperty("S1"))).click();
			Thread.sleep(1000);

			// Set first Serial Information
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("S100"))));
			driver.findElement(By.xpath(LM.getProperty("S100"))).click();
			Thread.sleep(1000);

			// Clicking on arrow image for set serial number
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Arrow1"))));
			driver.findElement(By.xpath(LM.getProperty("Arrow1"))).click();
			Thread.sleep(1000);

			// Clicking on submit(internal) button given in at warehouse window.
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("Warehousewindowsubmit"))));
			driver.findElement(By.xpath(LM.getProperty("Warehousewindowsubmit"))).click();
			Thread.sleep(1000);

			// Clicking on submit(External) button given in at warehouse window.
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("submitbutton"))));
			driver.findElement(By.xpath(LM.getProperty("submitbutton"))).click();
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("saveButton"))));
			WebElement Save = driver.findElement(By.xpath(LM.getProperty("saveButton")));
			Save.click();

			Utilities.clickButton("Yes", 1000, driver);

			Utilities.clickButton("OK", 1000, driver);

			Utilities.refresh();

			System.out.println("\"Lease Delivery Order\" document is created successfully: " + documentid1);

		} catch (Exception EX) {

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(2000);// pro

	}

}
