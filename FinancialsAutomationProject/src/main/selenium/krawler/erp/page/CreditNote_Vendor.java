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

public class CreditNote_Vendor {

	public static void create_creditNoteAgainstvendor(String documentid, String productid, String vendorid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_CreditNote_PurchaseInvoice.properties");
			Utilities.waitandClick(pro.getProperty("CreditNoteIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("SelectVendorNote"), driver);
			Utilities.HoverandClick(pro.getProperty("ClickSubmit"), driver);

			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox("CrVen" + documentid, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("EnterVendorId"), driver);

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
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
				Utilities.HoverandClick("//*[text()='Account']/ancestor::div[3]/following::div[1]/div/div["
						+ ((j + 1) * 1) + "]/table/tbody/tr/td[" + indOfamt + "]/div", driver);
				Utilities.enter_LinelabelAmount("100", driver);
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			}
			// hovertilllast
			Utilities.justHover(
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[contains(@class,'pwnd delete-gridrow')]",
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
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			}

			Utilities.HoverandClick(pro.getProperty("ClickOnSave"), driver);
			Utilities.clickButton("Yes", 0, driver);
			Utilities.clickButton("OK", 0, driver);
			Utilities.HoverandClick(pro.getProperty("CloseForm"), driver);
			System.out.println("************ Credit Note against Vendor [ CrVen" + documentid
					+ " ] created Sucessfully !!!! ************************");

		} catch (Exception EX) {
			System.out.println("************ Credit Note against Vendor [ CrVen" + documentid
					+ " ] FAILED !!!! ************************");
			Utilities.handleError(EX, driver);
		}
	}

	public static void Verify_creditNoteAgainstvendor(String documentid, String productid, String venderid,
			WebDriver driver) throws InterruptedException, AWTException, IOException

	{

		try {

			documentid = "CrVen" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherWise.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreditNoteReport"))));
			driver.findElement(By.xpath(pro.getProperty("CreditNoteReport"))).click();
			Thread.sleep(5000);// pro

			WebElement searchtype = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='view19CreditNoteDetails']")));
			searchtype.clear();
			searchtype.sendKeys("Credit Note for Vendors");
			Thread.sleep(2000);
			searchtype.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Credit Note No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d1");
				}

				else if (header.equals("Name")) {
					assertEquals(venderid + "Name",
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d2");
				}

				else if (header.equals("Vendor Code")) {
					assertEquals(venderid,
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d3");
				}

				else if (header.equals("Amount")) {
					assertEquals("SGD 100.00",
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d4");
				}
			}

			System.out.println("Verified creditNoteAgainstvendor [" + documentid + "] !!");
			Thread.sleep(500);

			String xpathOfelement = "//li[@id='as__CreditNoteDetails']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	public static void CopyEditDelete_creditNoteAgainstvendor(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_CreditNote_PurchaseInvoice.properties");
			documentid = "CrVen" + documentid;
			String CopyCNAgainstVendorID = "Copy" + documentid;
			Utilities.waitandClick(pro.getProperty("CreditNoteReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.enterTextandSelect("Credit Note for Vendors", pro.getProperty("CNTypeDD"), driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			// Copy
			Utilities.HoverandClick(pro.getProperty("copyCNAgainstVendorButton"), driver);
			String formLoaded = "//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td[3]";
			Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			Utilities.enterTextNormalBox(CopyCNAgainstVendorID, pro.getProperty("copyCNAgainstVendorId"), driver);
			Utilities.HoverandClick(pro.getProperty("saveCopiedCNAgainstVendorId"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("CopyCNAgainstVendorClose"), driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox(CopyCNAgainstVendorID, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println(
					"**** COPY test case for Credit Note [" + CopyCNAgainstVendorID + "] Against Vendor is PASS !!!!");

			// Edit
			Utilities.HoverandClick(pro.getProperty("EditCNAgainstVendorButton"), driver);
			Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			Utilities.enterTextNormalBox("EDIT Credit Note [" + CopyCNAgainstVendorID + "] Against Vendor Memo",
					pro.getProperty("CNAgainstVendorMemo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("EditCNAgainstVendorClose"), driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox(CopyCNAgainstVendorID, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println(
					"**** EDIT test case for Credit Note [" + CopyCNAgainstVendorID + "] Against Vendor is PASS !!!!");

			// Delete
			Utilities.HoverandClick(pro.getProperty("deleteCNAgainstVendorButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteCNAgainstVendorPermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox(CopyCNAgainstVendorID, pro.getProperty("QuickSearch"), driver);

			// delete confrimation
			WebElement delConfirm = driver
					.findElement(By.xpath("//*[contains(text(),' Get Started by adding a Credit Note now')]"));
			if (delConfirm.isDisplayed()) {
				System.out.println("**** DELETE test case for Credit Note [" + CopyCNAgainstVendorID
						+ "] Against Vendor is PASS !!!!");
			}

			String xpathOfelement = "//li[@id='as__CreditNoteDetails']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);
		} catch (Exception EX) {
			System.out.println("**** EDIT-DELETE test case for Credit Note [" + documentid
					+ "] Against Vendor is FAIL FAIL  !!!!");
			Utilities.handleError(EX, driver);
		}
	}

}
