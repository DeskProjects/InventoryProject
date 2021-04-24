package krawler.erp.BankReconcilation;

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

import krawler.erp.inventory.InvUtilities;
import krawler.erp.page.Utilities;

public class CreatePaymethodandCOA {
	public static void create_ChartOfAccount(String documentID, String BankName, String productid, String customerid,
			WebDriver driver) throws InterruptedException, AWTException, IOException

	{

		try {
			Properties pro = Utilities.fetchProValue("OR_ChartOfAccount.properties");
			// clicked on Sales Return Document
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("iconCAO"))));
			driver.findElement(By.xpath(pro.getProperty("iconCAO"))).click();
			Thread.sleep(1000);// pro

			// Click On Submit Button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("addNewAcc"))));
			driver.findElement(By.xpath(pro.getProperty("addNewAcc"))).click();

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("accCode"))));
			driver.findElement(By.xpath(pro.getProperty("accCode"))).sendKeys(BankName);
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("accName"))));
			driver.findElement(By.xpath(pro.getProperty("accName"))).sendKeys(BankName);
			Thread.sleep(2000);

			Utilities.waitandClick(pro.getProperty("MasterType"), driver);

			WebElement grpid = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("groupID"))));
			grpid.clear();
			grpid.sendKeys("Bank");
			Thread.sleep(3000);// pro
			grpid.sendKeys(Keys.ENTER);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("saveBtn"))));
			driver.findElement(By.xpath(pro.getProperty("saveBtn"))).click();

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("okBtn"))));
			driver.findElement(By.xpath(pro.getProperty("okBtn"))).click();

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("coaClsBtn"))));
			driver.findElement(By.xpath(pro.getProperty("coaClsBtn"))).click();
			System.out.println("Chart of account " + documentID + " successfully created !! ");

		} catch (Exception EX) {
			System.out.println("Chart of account " + documentID + " NOT created !! ");
			Utilities.handleError(EX, driver);
		}
	}

	public static void create_PaymentMethod(String BankName, String accontName, String showCsCP, String showLoan,
			WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {
			Utilities.refresh();
			final Properties pro = Utilities.fetchProValue("OR_PaymentMethod.properties");

			InvUtilities.enableExpander(driver);
			Utilities.HoverandClick(pro.getProperty("MiscellaneousExpander"), driver);
			Utilities.HoverandClick(pro.getProperty("PaymentMethodLink"), driver);
			Thread.sleep(2000);

			int totalsize = Utilities.totalSize("//div[@class='x-grid3-viewport']/div[2]/div/div", driver);
			Robot r = new Robot();

			for (int lastRow = 1; lastRow <= totalsize; lastRow++) {
				if (lastRow == totalsize) {

					driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + lastRow
							+ "]/table/tbody/tr/td[1]/div")).click();
					Thread.sleep(2000);
					// enter Method name
					Utilities.sendText(BankName);
					Thread.sleep(3000);// pro
					r.keyPress(KeyEvent.VK_TAB);

					Thread.sleep(2000);
					Utilities.sendText(BankName);
					Thread.sleep(3000);
					r.keyPress(KeyEvent.VK_ENTER);
					r.keyPress(KeyEvent.VK_ENTER);
					r.keyRelease(KeyEvent.VK_ENTER);
					r.keyPress(KeyEvent.VK_TAB);

					Thread.sleep(2000);
					r.keyPress(KeyEvent.VK_CONTROL);
					r.keyPress(KeyEvent.VK_A);
					r.keyRelease(KeyEvent.VK_A);
					r.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(1000);
					WebElement coshow = driver
							.findElement(By.xpath("//input[@name='autopopulateincpcs']/following-sibling::input"));
					coshow.sendKeys(showCsCP);
					Thread.sleep(2000);
					r.keyPress(KeyEvent.VK_ENTER);
					r.keyRelease(KeyEvent.VK_ENTER);
					r.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);

					Thread.sleep(2000);
					r.keyPress(KeyEvent.VK_CONTROL);
					r.keyPress(KeyEvent.VK_A);
					r.keyRelease(KeyEvent.VK_A);
					r.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(1000);
					WebElement loan = driver
							.findElement(By.xpath("//input[@name='autopopulateinloan']/following-sibling::input"));
					loan.sendKeys(showLoan);
					Thread.sleep(2000);
					r.keyPress(KeyEvent.VK_ENTER);
					r.keyRelease(KeyEvent.VK_ENTER);

					// remove focus
					driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + lastRow
							+ "]/table/tbody/tr/td[6]/div")).click();
					Thread.sleep(1000);
				}
			}

			Utilities.clickButton("Update", 200, driver);
			Utilities.clickButton("OK", 200, driver);
			Utilities.clickButton("Close", 200, driver);

			System.out.println("Payment Method [" + BankName + "] created !!!");
			Thread.sleep(2000);

		} catch (Exception EX) {
			System.out.println("Payment Method [" + BankName + "] NOT created !!!");
			Utilities.handleError(EX, driver);
		}
	}

}
