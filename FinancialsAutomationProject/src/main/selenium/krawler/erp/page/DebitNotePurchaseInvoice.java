package krawler.erp.page;

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

public class DebitNotePurchaseInvoice {

	public static void create_debitNoteAgainstpurchaseInvoice(String documentid, String productid, String vendorid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_DebitNotePurchaseInvoice.properties");
			Utilities.waitandClick(pro.getProperty("DebitNotePurchaseInvoiceIcon"), driver);
			Utilities.waitandClick(pro.getProperty("SelectDebitNotePurchaseInvoice"), driver);
			Utilities.waitandClick(pro.getProperty("ClickSubmit"), driver);
			Thread.sleep(2000);

			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.enterTextNormalBox("DnPi" + documentid, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("VendorId"), driver);
			Utilities.HoverandClick(pro.getProperty("memo"), driver);

			// ADD Account
			int headerSize = Utilities.totalSize("//*[text()='Account']/ancestor::tr/td/div", driver);
			int indOfacc = 0, indOfamt = 0, indOfreason = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver.findElement(By.xpath("//*[text()='Account']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Account")) {
					indOfacc = i;
				}
				if (headerName.equalsIgnoreCase("Amount")) {
					indOfamt = i;
				}
			}
			String accountArr[] = { "COA" + documentid };
			for (int j = 0; j < accountArr.length; j++) {
				Utilities.HoverandClick("//*[text()='Account']/ancestor::div[3]/following::div[1]/div/div["
						+ ((j + 1) * 1) + "]/table/tbody/tr/td[" + indOfacc + "]/div", driver);
				Utilities.enterTextandSelect(accountArr[j], "//*[@id='accountid']/following::input[1]", driver);
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
				Utilities.HoverandClick("//*[text()='Account']/ancestor::div[3]/following::div[1]/div/div["
						+ ((j + 1) * 1) + "]/table/tbody/tr/td[" + indOfamt + "]/div", driver);
				Utilities.enter_LinelabelAmount("100", driver);
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
			}

			// hovertilllast
			Utilities.justHover(
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[contains(@class,'x-grid3-cell-last')]",
					driver);
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver.findElement(By.xpath("//*[text()='Account']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Reason")) {
					indOfreason = i;
				}
			}
			for (int j = 0; j < accountArr.length; j++) {
				Utilities.HoverandClick("//*[text()='Account']/ancestor::div[3]/following::div[1]/div/div["
						+ ((j + 1) * 1) + "]/table/tbody/tr/td[" + indOfreason + "]/div", driver);
				// select first Reason
				Utilities.HoverandClick("//*[@id='reason']/following::img[1]", driver);
				Utilities.HoverandClick("//*[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div[2]",
						driver);
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
			}

			// LINK PI
			int headerSize2 = Utilities.totalSize("//*[text()='Purchase Invoice']/ancestor::tr/td/div", driver);
			int indOfPI = 0, indOfpiamt = 0;
			for (int i = 1; i <= headerSize2; i++) {
				String headerName = driver
						.findElement(By.xpath("//*[text()='Purchase Invoice']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Purchase Invoice")) {
					indOfPI = i;
				}
				if (headerName.equalsIgnoreCase("Enter Amount/Percentage")) {
					indOfpiamt = i;
				}
			}
			// Link
			Utilities.HoverandClick(
					"//*[text()='Purchase Invoice']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfPI + "]/div",
					driver);
			Utilities.justHover(pro.getProperty("memo"), driver);
			Utilities.enterTextandSelect("PurInvo" + documentid, "//*[@id='linkInvoice']/following::input[1]", driver);
			// Enter ammount
			Utilities.HoverandClick(
					"//*[text()='Purchase Invoice']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfpiamt + "]/div",
					driver);
			Utilities.enter_LinelabelAmount("50", driver);
			Utilities.HoverandClick(pro.getProperty("memo"), driver);

			Utilities.HoverandClick(pro.getProperty("buttonSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("CloseForm"), driver);
			System.out.println("Debit Note against PI created with >>>> : [DnPi" + documentid);
		}

		catch (Exception EX) {
			System.out.println("Debit Note against PI FAILED to create  with >>>> : [DnPi" + documentid);
			Utilities.handleError(EX, driver);
		}
	}

	public static void verify_debitNoteAgainstpurchaseInvoice(String documentid, String productid, String vendorid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			documentid = "DnPi" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_DebitNoteOtherWise.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DebitNoteReport"))));
			driver.findElement(By.xpath(pro.getProperty("DebitNoteReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();
			System.out.println(e);
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				System.out.println(driver.findElement(By
						.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText());
				String header = driver.findElement(By
						.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Debit Note No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("d1");
				}

				else if (header.equals("Name")) {
					assertEquals(vendorid + "Name",
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("d2");
				}

				else if (header.equals("Vendor Code")) {
					assertEquals(vendorid,
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("d3");
				}

				else if (header.equals("Amount")) {
					assertEquals("SGD 1.00",
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("d4");
				}

			}
			Thread.sleep(500);
			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	// Cop-Edit-Delete ------------->
	public static void EditDelete_debitNoteAgainstpurchaseInvoice(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_DebitNotePurchaseInvoice.properties");

			Utilities.waitandClick(pro.getProperty("DebitNoteReport"), driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(1000);
			Utilities.enterTextandSelect("Debit Note Without Purchase Return", "//*[@name='typeid']", driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			// Edit
			Utilities.HoverandClick(pro.getProperty("EditDNPIButton"), driver);
			String formLoaded = "//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td[3]";
			Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			Utilities.enterTextNormalBox("Performing EDIT test case for Debit Note No:- " + documentid,
					pro.getProperty("DNPIMemo"), driver);
			Utilities.HoverandClick(pro.getProperty("DNPIEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("EditDNPIClose"), driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println("*** EDIT test case for Debit note No. [" + documentid + "] is PASS !!");

			// Delete
			Utilities.HoverandClick(pro.getProperty("deleteDNPIButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteDNPIPermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);

			// Confirmation Delete
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			WebElement deletedMsg = driver
					.findElement(By.xpath("//*[contains(text(),' Get Started by adding a Debit Note now')]"));
			if (deletedMsg.isDisplayed()) {
				System.out.println("*** DELETE test case for Debit note No. [" + documentid + "] is PASS !!");
			}

			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);
		} catch (Exception EX) {
			System.out.println("*** EDIT-DELETE test case for Debit note No. [" + documentid + "] is FAILED !!");
			Utilities.handleError(EX, driver);
		}
	}

}
