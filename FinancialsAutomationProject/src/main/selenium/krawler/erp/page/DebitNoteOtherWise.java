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

public class DebitNoteOtherWise {

	public static void create_debitNoteOtherwise(String documentid, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties DebitNoteOtherwise = Utilities.fetchProValue("OR_DebitNoteOtherWise.properties");

			Utilities.waitandClick(DebitNoteOtherwise.getProperty("CreateDebitNoteIcon"), driver);
			Utilities.waitandClick(DebitNoteOtherwise.getProperty("SelectDNOtherwiseOption"), driver);
			Utilities.waitandClick(DebitNoteOtherwise.getProperty("submitButton"), driver);
			Thread.sleep(2000);// pro

			// sequen format document no.
			Utilities.enterTextandSelect("NA", DebitNoteOtherwise.getProperty("sequenceFormat"), driver);
			Utilities.enterTextNormalBox("DnO" + documentid, DebitNoteOtherwise.getProperty("debitNoteNo"), driver);
			Utilities.enterTextandSelect(vendorid, DebitNoteOtherwise.getProperty("vendorId"), driver);
			WebElement memo = driver.findElement(By.xpath(DebitNoteOtherwise.getProperty("memo")));
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
				memo.click();
				Utilities.HoverandClick("//*[text()='Account']/ancestor::div[3]/following::div[1]/div/div["
						+ ((j + 1) * 1) + "]/table/tbody/tr/td[" + indOfamt + "]/div", driver);
				Utilities.enter_LinelabelAmount("100", driver);
				memo.click();
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
				memo.click();
			}

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(DebitNoteOtherwise.getProperty("clickOk"), driver);

			System.out.println("* * * Debit Note Otherwise created successfully for : " + documentid);
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	public static void verify_debitNoteOtherwise(String documentid, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "DnO" + documentid;
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
				// System.out.println(driver.findElement(By.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["+i+"]/div")).getText());
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
					// System.out.println("d1");
				}

				else if (header.equals("Name")) {
					assertEquals(vendorid + "Name",
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d2");
				}

				else if (header.equals("Vendor Code")) {
					assertEquals(vendorid,
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d3");
				}

				else if (header.equals("Amount")) {
					assertEquals("SGD 100.00",
							driver.findElement(By
									.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d4");
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

	// Copy-Edit-Delete ->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public static void CopyEditDelete_debitNoteOtherwise(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_DebitNoteOtherWise.properties");
			String CopyDNOtherwiseID = "Copy" + documentid;

			Utilities.waitandClick(pro.getProperty("DebitNoteReport"), driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			// Copy
			Utilities.HoverandClick(pro.getProperty("copyDNOtherwiseButton"), driver);
			String formLoaded = "//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td[3]";
			Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			Utilities.enterTextNormalBox(CopyDNOtherwiseID, pro.getProperty("copyDNOtherwiseId"), driver);
			Utilities.HoverandClick(pro.getProperty("saveCopiedDNOtherwiseId"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("CopyDNOtherwiseClose"), driver);
			Utilities.enterTextInDropDown(CopyDNOtherwiseID, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println("*** COPY test case for Debit note No. [" + CopyDNOtherwiseID + "] is PASS !!");

			// Edit
			Utilities.HoverandClick(pro.getProperty("EditDNOtherwiseButton"), driver);
			Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			Utilities.enterTextNormalBox("Performing EDIT test case for Debit Note No:- " + CopyDNOtherwiseID,
					pro.getProperty("DNOtherwiseMemo"), driver);
			Utilities.HoverandClick(pro.getProperty("DNOtherwiseEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("EditDNOtherwiseClose"), driver);
			Utilities.enterTextInDropDown(CopyDNOtherwiseID, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println("*** EDIT test case for Debit note No. [" + CopyDNOtherwiseID + "] is PASS !!");

			// Delete
			Utilities.HoverandClick(pro.getProperty("deleteDNOtherwiseButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteDNOtherwisePermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);

			// Confirmation Delete
			Utilities.enterTextInDropDown(CopyDNOtherwiseID, pro.getProperty("QuickSearch"), driver);
			Utilities.isLoadingDisappear(100, driver);
			WebElement deletedMsg = driver
					.findElement(By.xpath("//*[contains(text(),' Get Started by adding a Debit Note now')]"));
			if (deletedMsg.isDisplayed()) {
				System.out.println("*** DELETE test case for Debit note No. [" + CopyDNOtherwiseID + "] is PASS !!");
			}

			String closeReportPage = "//li[@id='as__DebitNoteDetails']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);

		} catch (Exception EX) {
			System.out.println("*** COPY-EDIT-DELETE test case for Debit note No. [" + documentid + "] is FAILED !!");
			Utilities.handleError(EX, driver);
		}
	}

	// * * * * * * Bulkdelete * * * * * * *

	public static void BulkDelete_debitNoteOtherwise(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "DnO" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_DebitNoteOtherWise.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DebitNoteReport"))));
			driver.findElement(By.xpath(pro.getProperty("DebitNoteReport"))).click();
			Thread.sleep(5000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			// div[@class='x-paging-info']/preceding-sibling::table/tbody/tr/td[14]/div/img

			WebElement arrow = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[@class='x-paging-info']/preceding-sibling::table/tbody/tr/td[14]/div/img")));
			arrow.click();
			Thread.sleep(1000);

			WebElement selectPageNumber = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='30']")));
			selectPageNumber.click();
			Thread.sleep(3000);

			WebElement selectAllChekBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div/div/div/div/table/thead/tr/td[1]/div/div")));
			selectAllChekBox.click();

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteDNOtherwiseButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteDNOtherwiseButton"))).click();

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteDNOtherwisePermButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteDNOtherwisePermButton"))).click();

			Utilities.clickButton("Yes", 1100, driver);
			// div[@class='x-window-bwrap']/div/div/div/div/div[1]/div[2]/span

			WebElement message = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[@class='x-window-bwrap']/div/div/div/div/div[1]/div[2]/span")));
			if (message.isDisplayed()) {
				System.out.println(" >>>>>>>> [" + message.getText() + "] <<<<<<<<");
			}

		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}
	}

}
