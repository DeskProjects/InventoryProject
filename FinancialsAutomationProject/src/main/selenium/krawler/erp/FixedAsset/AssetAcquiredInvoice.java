package krawler.erp.FixedAsset;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class AssetAcquiredInvoice {
	public void Create_AssetAI(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			String documentid1 = "AAI" + documentid;

			// Asset Acquired Invoice Utilities
			Properties AssetAI = Utilities.fetchProValue("OR_Asset Acquired Invoice.properties");
			Thread.sleep(2000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Acquired Invoice
			WebElement AAIbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("ClickOnAAI"))));
			AAIbutton.click();
			Thread.sleep(3000);

			// Enter Asset AI Vendor
			WebElement Vendor = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("Vendor"))));
			Thread.sleep(3000);
			Vendor.clear();
			Thread.sleep(3000);
			Vendor.sendKeys("Amol");
			Vendor.click();
			Thread.sleep(3000);
			Vendor.sendKeys(Keys.ENTER);

			// Click On NA Sequence Format

			WebElement AAISequence = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("SequenceFormat"))));
			Thread.sleep(3000);
			AAISequence.clear();
			Thread.sleep(3000);
			AAISequence.sendKeys("NA");
			AAISequence.click();
			Thread.sleep(3000);
			AAISequence.sendKeys(Keys.ENTER);

			// Enter Asset AAI Number
			WebElement AAINo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("AAINo"))));
			Thread.sleep(3000);
			AAINo.sendKeys(documentid1);

			// Enter Asset AAI Memo

			WebElement AAIMemo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("Memo"))));
			AAIMemo.click();
			Thread.sleep(500);
			AAIMemo.sendKeys("Asset Memo");
			Thread.sleep(2000);

			// Enter Asset Group Detail First

			Utilities.HoverandClick(AssetAI.getProperty("AssetGroup1"), driver);
			Utilities.enterTextandSelect("AGID44", "//*[@name='productname']", driver);
			AAIMemo.click();
			Thread.sleep(2000);

			// Enter Asset Unit Price First

			Utilities.HoverandClick(AssetAI.getProperty("UnitPrice1"), driver);
			Utilities.enter_LinelabelAmount("100", driver);
			AAIMemo.click();
			Thread.sleep(1000);

			// Enter Asset Quantity First

			Utilities.HoverandClick(AssetAI.getProperty("Quantity1"), driver);
			Utilities.enter_LinelabelAmount("1", driver);
			AAIMemo.click();
			Thread.sleep(2000);

			// Enter Asset ID First

			Utilities.clickAndEnterText(documentid + "AAIId100", AssetAI.getProperty("AssetID"), driver);
			Thread.sleep(1000);

			Robot r2 = new Robot();
			r2.keyPress(KeyEvent.VK_TAB);
			r2.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			Utilities.sendText("Automation Description1");
			Thread.sleep(2000);

			r2.keyPress(KeyEvent.VK_TAB);
			r2.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r2.keyPress(KeyEvent.VK_TAB);
			r2.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r2.keyPress(KeyEvent.VK_TAB);
			r2.keyRelease(KeyEvent.VK_TAB);
			Utilities.sendText("100");
			Thread.sleep(2000);

			r2.keyPress(KeyEvent.VK_TAB);
			r2.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r2.keyPress(KeyEvent.VK_TAB);
			r2.keyRelease(KeyEvent.VK_TAB);
			Utilities.sendText("100");
			Thread.sleep(1000);

			// Click On Save button Asset Detail First
			WebElement SaveI1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("SaveDetail"))));
			SaveI1.click();
			Thread.sleep(1000);

			// Enter Asset Group Second
			Utilities.HoverandClick(AssetAI.getProperty("AssetGroup2"), driver);
			Utilities.enterTextandSelect("AGID44", "//*[@name='productname']", driver);
			AAIMemo.click();
			Thread.sleep(1000);

			// Enter Asset Unit Price Second
			Utilities.HoverandClick(AssetAI.getProperty("UnitPrice2"), driver);
			Utilities.enter_LinelabelAmount("200", driver);
			AAIMemo.click();
			Thread.sleep(1000);

			// Enter Asset Quantity Second
			Utilities.HoverandClick(AssetAI.getProperty("Quantity2"), driver);
			Utilities.enter_LinelabelAmount("1", driver);
			AAIMemo.click();
			Thread.sleep(2000);

			// Enter Asset ID Second
			Utilities.clickAndEnterText(documentid + "AAIId200", AssetAI.getProperty("AssetID"), driver);
			Thread.sleep(500);

			Robot r3 = new Robot();
			r3.keyPress(KeyEvent.VK_TAB);
			r3.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			Utilities.sendText("Automation Description2");
			Thread.sleep(2000);

			r3.keyPress(KeyEvent.VK_TAB);
			r3.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r2.keyPress(KeyEvent.VK_TAB);
			r2.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r3.keyPress(KeyEvent.VK_TAB);
			r3.keyRelease(KeyEvent.VK_TAB);
			Utilities.sendText("200");
			Thread.sleep(2000);

			r3.keyPress(KeyEvent.VK_TAB);
			r3.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r3.keyPress(KeyEvent.VK_TAB);
			r3.keyRelease(KeyEvent.VK_TAB);
			Utilities.sendText("200");

			// Click On Save button Asset Detail First

			WebElement SaveI2 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("SaveDetail"))));
			SaveI2.click();

			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 2000, driver);

			System.out.println("Asset Acquired Invoice Save Successfully");

			/*
			 * // Click On Asset Report Cross Symbol WebElement AGCross1 =
			 * AGwait.until(ExpectedConditions.elementToBeClickable(
			 * By.xpath(AssetAI.getProperty("CrossSymbol")))); AGCross1.click();
			 * Thread.sleep(2000);
			 */

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// --------------------------------------------------------- Edit Asset
	// Acquired Invoice ---------------------------------------------
	public void Edit_AssetAI(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			String documentid1 = "AAI" + documentid;
			// String AssetId= "AId"+Assetid;

			// Asset Acquired Invoice Utilities
			Properties AssetAI = Utilities.fetchProValue("OR_Asset Acquired Invoice.properties");
			Thread.sleep(500);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Acquired Invoice
			WebElement AAIRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("ClickOnRAAI"))));
			AAIRbutton.click();
			Thread.sleep(1000);

			// Click On Fetch button
			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("Fetch"))));
			Search.click();
			Thread.sleep(1000);

			// Enter Quick Search
			WebElement Search1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("QuickSearch"))));
			Search1.sendKeys(documentid1);
			Thread.sleep(2000);

			// Click on records
			WebElement AAICheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("CheckBox"))));
			AAICheck.click();
			Thread.sleep(2000);

			// Click On Asset Acquired Invoice
			WebElement AGRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("Edit"))));
			AGRbutton.click();
			Thread.sleep(3000);

			// Updated Asset Acquired Invoice Memo
			WebElement AAIMemo = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("Memo"))));
			AAIMemo.click();
			AAIMemo.clear();
			Thread.sleep(2000);

			AAIMemo.sendKeys("Updated Asset Memo");
			Thread.sleep(2000);

			// Enter Asset Group First
			Utilities.HoverandClick(AssetAI.getProperty("AssetGroup3"), driver);
			Utilities.enterTextandSelect("AGID44", "//*[@name='productname']", driver);
			AAIMemo.click();
			Thread.sleep(2000);

			// Enter Asset Unit Price Third
			Utilities.HoverandClick(AssetAI.getProperty("UnitPrice3"), driver);
			Utilities.enter_LinelabelAmount("300", driver);
			AAIMemo.click();
			Thread.sleep(1000);

			// Enter Asset Quantity Third
			Utilities.HoverandClick(AssetAI.getProperty("Quantity3"), driver);
			Utilities.enter_LinelabelAmount("1", driver);
			AAIMemo.click();
			Thread.sleep(2000);

			Utilities.clickAndEnterText(documentid + "AId300", AssetAI.getProperty("AssetID"), driver);

			Robot r1 = new Robot();
			r1.keyPress(KeyEvent.VK_TAB);
			r1.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			Utilities.sendText("Automation Description3");
			Thread.sleep(2000);

			r1.keyPress(KeyEvent.VK_TAB);
			r1.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r1.keyPress(KeyEvent.VK_TAB);
			r1.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r1.keyPress(KeyEvent.VK_TAB);
			r1.keyRelease(KeyEvent.VK_TAB);
			Utilities.sendText("300");
			Thread.sleep(2000);

			r1.keyPress(KeyEvent.VK_TAB);
			r1.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r1.keyPress(KeyEvent.VK_TAB);
			r1.keyRelease(KeyEvent.VK_TAB);

			// Click On Save button Asset Detail
			WebElement SaveI = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("SaveDetail"))));
			SaveI.click();

			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			// Utilities.clickButton("OK", 1000, driver);

			System.out.println("Edited Asset Acquired Invoice Save Successfully");

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// --------------------------------------------------------- Delete Asset
	// Acquired Invoice ---------------------------------------------

	public void Delete_AssetAI(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {
			String documentid1 = "AAI" + documentid;
			// String AssetId= "AId"+Assetid;

			// Asset Purchase Order Utilities
			Properties AssetAI = Utilities.fetchProValue("OR_Asset Acquired Invoice.properties");
			Thread.sleep(3000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Acquired Invoice
			WebElement AAIRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("ClickOnRAAI"))));
			AAIRbutton.click();
			Thread.sleep(1000);

			// Click On Fetch button
			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("Fetch"))));
			Search.click();
			Thread.sleep(1000);

			// Enter Quick Search
			WebElement Search1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("QuickSearch"))));
			Search1.sendKeys(documentid1);
			Thread.sleep(2000);

			// Click on records
			WebElement AAICheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("CheckBox"))));
			AAICheck.click();
			Thread.sleep(2000);

			// Click on Delete button
			WebElement AAIEdit = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("Delete"))));
			AAIEdit.click();
			Thread.sleep(2000);

			Actions Action = new Actions(driver);
			WebElement hoverElementandClick1 = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("DeleteP"))));

			Action.moveToElement(hoverElementandClick1).build().perform();
			Thread.sleep(5000);
			hoverElementandClick1.click();

			// Click On Yes Button
			WebElement AAIDYes = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("Yes"))));
			AAIDYes.click();
			Thread.sleep(2000);

			// Click On Ok Button
			WebElement AAIDOk = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetAI.getProperty("Ok"))));
			AAIDOk.click();
			Thread.sleep(3000);

			System.out.println("Asset Acquired Invoice  Deleted Successfully");
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

}
