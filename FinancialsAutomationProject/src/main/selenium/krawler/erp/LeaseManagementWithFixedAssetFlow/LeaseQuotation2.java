package krawler.erp.LeaseManagementWithFixedAssetFlow;

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

public class LeaseQuotation2 {
	private static final Properties LM = null;

	public void LM_CreateQuotation(String documentid, String Asset_ID, String customerid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			String documentid1 = "quotation" + documentid;
			Properties LM = Utilities.fetchProValue("OR_LeaseManagement.properties");
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement LMButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseQuotationIcon")))); // examining
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

			// driver.findElement(By.xpath(LM.getProperty("meomo"))).click();
			// Thread.sleep(2000);//pro

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
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseQuotationNumber"))));
			driver.findElement(By.xpath(LM.getProperty("LeaseQuotationNumber"))).sendKeys(documentid1);

			WebElement term = driver.findElement(By.xpath(LM.getProperty("CreditTerm")));
			term.clear();
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// search product id
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("SearchProductId"))));
			driver.findElement(By.xpath(LM.getProperty("SearchProductId"))).click();
			Thread.sleep(2000);// pro

			// Pass productID
			Utilities.sendText(Asset_ID);
			Thread.sleep(2000);

			// Select productID
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("ClickProduct"))));
			driver.findElement(By.xpath(LM.getProperty("ClickProduct"))).click();
			Thread.sleep(2000);// pro
			driver.findElement(By.xpath(LM.getProperty("memo"))).click();

			// Put the quantity and Unit Price for Asset group

			int lsOrderRowIndex = 0, customerRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath("//div[@id='leasequotationnulleditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size();
			Thread.sleep(1000);

			for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
				String header = driver.findElement(By
						.xpath("//div[@id='leasequotationnulleditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equalsIgnoreCase("Quantity")) {
					lsOrderRowIndex = rowIndex;
					driver.findElement(By
							.xpath("//div[@id='leasequotationnulleditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ lsOrderRowIndex + "]/div"))
							.click();
					Thread.sleep(2000);
					Utilities.sendText("1");
					driver.findElement(By.xpath(LM.getProperty("memo"))).click();
					Thread.sleep(2000);

				}

				else if (header.equalsIgnoreCase("Unit Price")) {
					customerRowIndex = rowIndex;
					driver.findElement(By
							.xpath("//div[@id='leasequotationnulleditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ customerRowIndex + "]/div"))
							.click();
					Thread.sleep(2000);
					Utilities.sendText("1000");
					Thread.sleep(2000);

				}
			}

			// Click On Save button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("saveButton"))));
			WebElement Save = driver.findElement(By.xpath(LM.getProperty("saveButton")));
			Save.click();

			Utilities.clickButton("Yes", 1000, driver);
			System.out.println("\"Lease Quotation\" document is created successfully : " + documentid1);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.refresh();

		} catch (Exception EX) {

			Utilities.handleError(EX, driver);
			System.out.println("\"Lease Quotation\" document is  not created.");
		}
		Thread.sleep(2000);// pro

	}

}
