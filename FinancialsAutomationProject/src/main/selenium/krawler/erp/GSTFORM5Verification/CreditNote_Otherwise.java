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

public class CreditNote_Otherwise {

	public static void create_creditNoteotherwise(String CNOtherWiseTax, String documentid, String customerid,
			WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {

			Properties CreditNoteOtherwise = Utilities.fetchProValue("OR_CreditNoteOtherwise.properties");

			Thread.sleep(3000);
			// WebDriverWait wait = new WebDriverWait(driver, 30);

			// clicked on Document
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(CreditNoteOtherwise.getProperty("CreditNoteIcon"))));
			driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("CreditNoteIcon"))).click();
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(CreditNoteOtherwise.getProperty("SelectCreditOther"))));
			driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("SelectCreditOther"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CreditNoteOtherwise.getProperty("ClickSubmit"))));
			driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("ClickSubmit"))).click();
			Thread.sleep(8000);

			// sequen format document no.
			WebElement squence = driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("SequenceFormat")));
			squence.clear();
			squence.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(CreditNoteOtherwise.getProperty("EnterCreditNo."))));
			driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("EnterCreditNo.")))
					.sendKeys("CnOthr" + documentid);
			Thread.sleep(2000);// pro

			// enter vendor name
			WebElement customer = driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("EnterCustomerId")));
			customer.clear();
			customer.sendKeys(customerid);
			Thread.sleep(3000);// pro
			customer.sendKeys(Keys.ENTER);

			WebElement accountselect = driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("EnterAccount")));
			accountselect.click();
			Thread.sleep(2000);// pro

			Utilities.enterTextandSelect("Sales", "//*[@name='accountid']/following::input[1]", driver);

			Robot acc1 = new Robot();

			driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("Memo"))).click();
			Thread.sleep(2000);// pro
			WebElement ammount1 = driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("EnterAmount")));
			ammount1.click();
			Thread.sleep(1000);// pro

			Utilities.sendText("1000");
			Thread.sleep(2000);// pro

			driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("Memo"))).click();
			Thread.sleep(2000);// pro

			driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("CNTax"))).click();
			Thread.sleep(2000);

			Utilities.sendText(CNOtherWiseTax);
			Thread.sleep(2000);// pro
			acc1.keyPress(KeyEvent.VK_DOWN);
			acc1.keyRelease(KeyEvent.VK_DOWN);
			acc1.keyPress(KeyEvent.VK_ENTER);
			acc1.keyRelease(KeyEvent.VK_ENTER);

			driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("Memo"))).click();
			Thread.sleep(2000);// pro

			WebElement reason = driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("EnterReason")));
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
			driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("Memo"))).click();

			// Click on Save
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CreditNoteOtherwise.getProperty("ClickOnSave"))));
			driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("ClickOnSave"))).click();
			Thread.sleep(2000);

			// Click on Yes
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CreditNoteOtherwise.getProperty("ClickOnYes"))));
			driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("ClickOnYes"))).click();
			Thread.sleep(1000);

			// Click on OK
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CreditNoteOtherwise.getProperty("ClickOnOk"))));
			driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("ClickOnOk"))).click();
			Thread.sleep(1000);

			// Click on close
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CreditNoteOtherwise.getProperty("CloseForm"))));
			driver.findElement(By.xpath(CreditNoteOtherwise.getProperty("CloseForm"))).click();

			System.out.println("**** Credit Note OtherWise : [" + documentid + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println(
					"**** Credit Note OtherWise is NOT Created for : [ " + documentid + " ] check LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

}
