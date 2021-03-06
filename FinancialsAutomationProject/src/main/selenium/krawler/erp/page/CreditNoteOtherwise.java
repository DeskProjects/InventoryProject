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

public class CreditNoteOtherwise {

	public static void create_creditNoteotherwise(String documentid, String productid, String customerid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherwise.properties");
			Utilities.waitandClick(pro.getProperty("CreditNoteIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("SelectCreditOther"), driver);
			Utilities.HoverandClick(pro.getProperty("ClickSubmit"), driver);

			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox("CnOthr" + documentid, pro.getProperty("EnterCreditNo."), driver);
			Utilities.enterTextandSelect(customerid, pro.getProperty("EnterCustomerId"), driver);

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
			Utilities.HoverandClick(pro.getProperty("ClickOnYes"), driver);
			Utilities.HoverandClick(pro.getProperty("ClickOnOk"), driver);
			Utilities.HoverandClick(pro.getProperty("CloseForm"), driver);
			System.out.println("************ Credit Note OtherWise [ CnOthr" + documentid
					+ " ] created Sucessfully !!!! ************************");

		} catch (Exception EX) {
			System.out.println("************ Credit Note OtherWise [ CnOthr" + documentid
					+ " ] FAILED !!!! ************************");
			Utilities.handleError(EX, driver);
		}
	}

	public static void verify_creditNoteotherwise(String documentid, String productid, String customerid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			documentid = "CnOthr" + documentid;
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
			System.out.println(" verified Credit Note OtherWise for > > [" + documentid + "] Successfully !!");
			Thread.sleep(500);
			String xpathOfelement = "//li[@id='as__CreditNoteDetails']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	// ---------- Copy-Edit-Delete flow -------------------------------
	public static void CopyEditDelete_creditNoteotherwise(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherwise.properties");
			documentid = "CnOthr" + documentid;
			String CopyCNOtherwiseID = "Copy" + documentid;
			Utilities.waitandClick(pro.getProperty("CreditNoteReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			// Copy
			Utilities.HoverandClick(pro.getProperty("copyCNOtherwiseButton"), driver);
			String formLoaded = "//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td[3]";
			Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			Utilities.enterTextNormalBox(CopyCNOtherwiseID, pro.getProperty("copyCNOtherwiseId"), driver);
			Utilities.HoverandClick(pro.getProperty("saveCopiedCNOtherwiseId"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("CopyCNOtherwiseClose"), driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox(CopyCNOtherwiseID, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out
					.println("**** COPY test case for Credit Note [" + CopyCNOtherwiseID + "] Otherwise is PASS !!!!");

			// Edit
			Utilities.HoverandClick(pro.getProperty("EditCNOtherwiseButton"), driver);
			Utilities.clickCheckBox(formLoaded, "Uncheck", driver);
			Utilities.enterTextNormalBox("EDIT Credit Note Otherwise [" + CopyCNOtherwiseID + "] Memo",
					pro.getProperty("CNOtherwiseMemo"), driver);
			Utilities.HoverandClick(pro.getProperty("CNOtherwiseEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("EditCNOtherwiseClose"), driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox(CopyCNOtherwiseID, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out
					.println("**** EDIT test case for Credit Note [" + CopyCNOtherwiseID + "] Otherwise is PASS !!!!");

			// Delete
			Utilities.HoverandClick(pro.getProperty("deleteCNOtherwiseButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteCNOtherwisePermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(1000);
			Utilities.enterTextNormalBox(CopyCNOtherwiseID, pro.getProperty("QuickSearch"), driver);

			// delete confrimation
			WebElement delConfirm = driver
					.findElement(By.xpath("//*[contains(text(),' Get Started by adding a Credit Note now')]"));
			if (delConfirm.isDisplayed()) {
				System.out.println(
						"**** DELETE test case for Credit Note [" + CopyCNOtherwiseID + "] Otherwise is PASS !!!!");
			}

			String xpathOfelement = "//li[@id='as__CreditNoteDetails']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);

		} catch (Exception EX) {
			System.out.println("**** COPY-EDIT-DELETE test case for Credit Note [" + documentid
					+ "] Otherwise is FAIL FAIL  !!!!");
			Utilities.handleError(EX, driver);
		}
	}

}
