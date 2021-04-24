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

public class CreditNoteSalesInvoice {

	public static void create_creditNoteAgainstSalesInvoice(String documentid, String productid, String customerid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_CreditNoteSalesInvoice.properties");
			Utilities.waitandClick(pro.getProperty("CreditNoteIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("CreditSalesInvoice"), driver);
			Utilities.HoverandClick(pro.getProperty("ClickSubmit"), driver);

			Utilities.enterTextandSelect("NA", pro.getProperty("CreditSequence"), driver);
			Utilities.enterTextNormalBox("CrSi" + documentid, pro.getProperty("CreditNoteNo"), driver);
			Utilities.enterTextandSelect(customerid, pro.getProperty("CustomerName"), driver);

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
				Utilities.HoverandClick(pro.getProperty("memo"), driver);
			}

			// LINK SI
			int headerSize2 = Utilities.totalSize("//*[text()='Sales Invoice']/ancestor::tr/td/div", driver);
			int indOfPI = 0, indOfpiamt = 0;
			for (int i = 1; i <= headerSize2; i++) {
				String headerName = driver
						.findElement(By.xpath("//*[text()='Sales Invoice']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headerName.equalsIgnoreCase("Sales Invoice")) {
					indOfPI = i;
				}
				if (headerName.equalsIgnoreCase("Enter Amount/Percentage")) {
					indOfpiamt = i;
				}
			}
			// Link
			Utilities.HoverandClick(
					"//*[text()='Sales Invoice']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfPI + "]/div",
					driver);
			Utilities.justHover(pro.getProperty("memo"), driver);
			Utilities.enterTextandSelect("SI" + documentid, "//*[@id='linkInvoice']/following::input[1]", driver);
			// Enter ammount
			Utilities.HoverandClick(
					"//*[text()='Sales Invoice']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfpiamt + "]/div",
					driver);
			Utilities.enter_LinelabelAmount("50", driver);
			Utilities.HoverandClick(pro.getProperty("memo"), driver);

			Utilities.HoverandClick(pro.getProperty("ClickSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("CloseForm"), driver);
			System.out.println("Credit Note against SI created with >>>> : [CrSi" + documentid);
		} catch (Exception EX) {
			System.out.println("**** Credit Note against Sales Invoice FAILED TO CREATED " + documentid);
			Utilities.handleError(EX, driver);
		}
	}

	public static void verify_creditNoteAgainstSalesInvoice(String documentid, String productid, String customerid,
			WebDriver driver) throws InterruptedException, AWTException, IOException

	{

		try {

			documentid = "CrSi" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherWise.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreditNoteReport"))));
			driver.findElement(By.xpath(pro.getProperty("CreditNoteReport"))).click();
			Thread.sleep(5000);// pro

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
					assertEquals(customerid + "Name",
							driver.findElement(By
									.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("d2");
				}

				else if (header.equals("Customer Code")) {
					assertEquals(customerid,
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
			System.out.println("verified credit Note Against SalesInvoice >[" + documentid + "] !!!");
			Thread.sleep(500);
			String xpathOfelement = "//li[@id='as__CreditNoteDetails']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	// Edit-Delete
	public static void EditDelete_creditNoteAgainstSalesInvoice(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "CrSi" + documentid;
			Properties pro = Utilities.fetchProValue("OR_CreditNoteSalesInvoice.properties");

			Utilities.waitandClick(pro.getProperty("CreditNoteReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			// Edit
			Utilities.HoverandClick(pro.getProperty("EditCNPIButton"), driver);
			String formLoaded = "//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td[3]";
			Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			try {
				if (driver.findElement(By.xpath("//div[@class='x-window x-window-plain x-window-dlg']"))
						.isDisplayed()) {
					driver.findElement(By.xpath("//button[text()='Yes']")).click();
				}
			} catch (Exception Ex) {
				// no action needed
			}
			Utilities.enterTextNormalBox("Update Credit Note [" + documentid + "]Against Sales Invoice Memo",
					pro.getProperty("CNPIMemo"), driver);
			Utilities.HoverandClick(pro.getProperty("CNPIEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 500, driver);
			Utilities.click_element(pro.getProperty("EditCNPIClose"), driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println(
					"**** EDIT test case for Credit Note [" + documentid + "] Against Sales Invoice is PASS !!!!");

			// delete
			Utilities.HoverandClick(pro.getProperty("deleteCNPIButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteCNPIPermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);

			// delete confrimation
			WebElement delConfirm = driver
					.findElement(By.xpath("//*[contains(text(),' Get Started by adding a Credit Note now')]"));
			if (delConfirm.isDisplayed()) {
				System.out.println("**** DELETE test case for Credit Note [" + documentid
						+ "] Against Sales Invoice is PASS !!!!");
			}

			String xpathOfelement = "//li[@id='as__CreditNoteDetails']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);
		} catch (Exception EX) {
			System.out.println("**** EDIT-DELETE test case for Credit Note [" + documentid
					+ "] Against Sales Invoice is FAIL FAIL  !!!!");
			Utilities.handleError(EX, driver);
		}
	}
}
