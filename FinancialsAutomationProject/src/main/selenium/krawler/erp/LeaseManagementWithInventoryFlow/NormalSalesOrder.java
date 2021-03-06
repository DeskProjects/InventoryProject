package krawler.erp.LeaseManagementWithInventoryFlow;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.EmailFunctionality;
import krawler.erp.page.Utilities;

public class NormalSalesOrder {
	public static void create_salesOrder(String documentid, String productid, String customerid, WebDriver driver,
			String MaintenanceNumberforUse) throws InterruptedException, AWTException, IOException {

		try {

			documentid = "So" + documentid;
			Properties SalesOrder = Utilities.fetchProValue("OR_SalesOrder.properties");

			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			// Clicked on main icon
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("CreateSalesOrderIcon"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("CreateSalesOrderIcon"))).click();
			Thread.sleep(10000);// pro

			// enter source name
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("customerName"))));
			WebElement customer = driver.findElement(By.xpath(SalesOrder.getProperty("customerName")));
			customer.clear();
			customer.sendKeys(customerid);
			Thread.sleep(3000);// pro
			customer.sendKeys(Keys.ENTER);

			// sequence format document no.
			WebElement squence = driver.findElement(By.xpath(SalesOrder.getProperty("sequenceFormat")));
			Thread.sleep(2000);
			squence.clear();
			squence.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// sequence format document no.
			WebElement term = driver.findElement(By.xpath(SalesOrder.getProperty("CreditTerm")));
			term.clear();
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("SalesOrderNo"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("SalesOrderNo"))).sendKeys(documentid);

			// Click on Is Maintenance option
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("IsMaintenanceRequest"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("IsMaintenanceRequest"))).click();

			// Select the Maintenance Number
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("MaintenanceNumber"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("MaintenanceNumber"))).sendKeys(MaintenanceNumberforUse);

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(SalesOrder.getProperty("MaintenanceNumberSelection"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("MaintenanceNumberSelection"))).click();
			Thread.sleep(1000);

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("addButton"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("addButton"))).click();
			Thread.sleep(2000);

			// search product id
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("productId"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("productId"))).sendKeys(productid + "Service");
			Thread.sleep(4000);// pro

			// select check box
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("checkBox"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("checkBox"))).click();
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("clickAdd"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("clickAdd"))).click();
			Thread.sleep(3000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//*[@id='salesordereditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size(); i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='requisitioneditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				if (driver.findElement(By
						.xpath("//*[@id='salesordereditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Quantity")) {
					driver.findElement(By
							.xpath("//*[@id='salesordereditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					break;
				}
			}

			Thread.sleep(2000);
			// Utilities.sendText("1");
			Thread.sleep(2000);

			driver.findElement(By.xpath(SalesOrder.getProperty("Memo"))).click();

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("saveButton"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("saveButton"))).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("yesButton"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("yesButton"))).click();

			Thread.sleep(2000);
			Utilities.clickButton("OK", 300, driver);
			Thread.sleep(1000);

			System.out.println("Sales Order Created for Maintenance Request: " + documentid);

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("clickOk"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("clickOk"))).click();
			driver.quit();
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}
}
