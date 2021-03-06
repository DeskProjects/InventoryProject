package krawler.erp.GSTFORM5Verification;

import static org.testng.Assert.assertEquals;

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

public class DebitNote_OtherWise {

	public static void create_debitNoteOtherwise(String VenTax, String documentid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = documentid;
			Properties DebitNoteOtherwise = Utilities.fetchProValue("OR_DebitNoteOtherWise.properties");

			Utilities.waitandClick(DebitNoteOtherwise.getProperty("CreateDebitNoteIcon"), driver);
			Utilities.waitandClick(DebitNoteOtherwise.getProperty("SelectDNOtherwiseOption"), driver);
			Utilities.waitandClick(DebitNoteOtherwise.getProperty("submitButton"), driver);
			Thread.sleep(2000);// pro

			// sequen format document no.
			Utilities.enterTextandSelect("NA", DebitNoteOtherwise.getProperty("sequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid, DebitNoteOtherwise.getProperty("debitNoteNo"), driver);
			Utilities.enterTextandSelect(vendorid, DebitNoteOtherwise.getProperty("vendorId"), driver);

			WebElement accountselect = driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("vendorName")));
			accountselect.click();
			Thread.sleep(2000);// pro
			Robot acc1 = new Robot();

			Utilities.sendText("Utilities");
			Thread.sleep(2000);// pro
			acc1.keyPress(KeyEvent.VK_ENTER);
			acc1.keyRelease(KeyEvent.VK_ENTER);

			driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("memo"))).click();
			Thread.sleep(2000);// pro
			WebElement ammount1 = driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("Amount")));
			ammount1.click();
			Thread.sleep(2000);// pro

			Utilities.sendText("100");
			Thread.sleep(2000);// pro

			driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("memo"))).click();
			Thread.sleep(2000);// pro

			driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("DNTax"))).click();
			Thread.sleep(2000);

			Utilities.sendText(VenTax);
			Thread.sleep(2000);// pro
			acc1.keyPress(KeyEvent.VK_DOWN);
			acc1.keyRelease(KeyEvent.VK_DOWN);
			acc1.keyPress(KeyEvent.VK_ENTER);
			acc1.keyRelease(KeyEvent.VK_ENTER);

			driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("memo"))).click();
			Thread.sleep(2000);// pro

			WebElement reason = driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("reason")));
			reason.click();
			Thread.sleep(2000);// pro

			acc1.keyPress(KeyEvent.VK_DOWN);
			acc1.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);// pro
			acc1.keyPress(KeyEvent.VK_DOWN);
			acc1.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);// pro
			acc1.keyPress(KeyEvent.VK_ENTER);
			acc1.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);// pro
			driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("memo"))).click();

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(DebitNoteOtherwise.getProperty("clickOk"), driver);

			System.out.println("**** Debit Note OtherWise : [" + documentid + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out
					.println("**** Debit Note OtherWise is NOT Created for : [ " + documentid + " ] check LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

}
