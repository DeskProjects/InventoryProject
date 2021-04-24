package krawler.erp.GSTFORM5Verification;

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

import krawler.erp.page.Utilities;
import krawler.erp.utils.SikulliUtil;

public class VendorCreation {
	public static WebDriver driver;

	public static void Create_Vendor(String VendorID, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {
			Properties pro = Utilities.fetchProValue("OR_VendorMaster.properties");
			String vendorName = VendorID + "Name";

			// wait until component appears
			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreateVendorIcon"))));

			// Open Vendor master
			driver.findElement(By.xpath(pro.getProperty("CreateVendorIcon"))).click();
			Thread.sleep(2000);// pro

			// Click on select NA of customer ID
			WebElement NA = driver.findElement(By.xpath(pro.getProperty("sequenceformat")));
			NA.clear();
			NA.sendKeys("NA");
			Thread.sleep(1000);
			NA.sendKeys(Keys.ENTER);

			Thread.sleep(3000);

			driver.findElement(By.xpath(pro.getProperty("vendorID"))).sendKeys(VendorID);// adding
																							// new
																							// vendor
																							// ID
			driver.findElement(By.xpath(pro.getProperty("vendorName"))).sendKeys(vendorName);// adding
																								// new
																								// Vendor
																								// name

			WebElement term = driver.findElement(By.xpath(pro.getProperty("debitTerm")));
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);

			// Credit on term
			driver.findElement(By.xpath(pro.getProperty("CreditTerm"))).sendKeys("999999999");// adding
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
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("personaldetailsSave"))));
			// first save
			driver.findElement(By.xpath(pro.getProperty("personaldetailsSave"))).click();

			// second save
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("confirmYes"))));
			driver.findElement(By.xpath(pro.getProperty("confirmYes"))).click();
			Thread.sleep(3000);// pro

			// second save
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("successOk"))));
			driver.findElement(By.xpath(pro.getProperty("successOk"))).click();

			Utilities.HoverandClick("//*[@id='as__mainVendorPanel']/a[1]", driver);
			System.out.println("Vendor created successfully : " + VendorID);

		} catch (Exception EX) {
			System.out.println("Vendor is not created");
			Utilities.handleError(EX, driver);
		}
	}

}
