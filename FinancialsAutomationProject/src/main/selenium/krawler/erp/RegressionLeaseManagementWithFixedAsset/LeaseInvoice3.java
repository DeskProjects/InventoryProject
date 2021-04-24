package krawler.erp.RegressionLeaseManagementWithFixedAsset;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class LeaseInvoice3 {

	private static final Properties LM = null;

	public void LM_CreateInvoice(String documentid, String Asset_Id, String customerid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			String documentid1 = "invoice" + documentid;
			Properties LM = Utilities.fetchProValue("OR_LeaseInvoice.properties");
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement LMButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseInvIcon"))));
			LMButton.click();
			Thread.sleep(4000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("CustomerId"))));
			WebElement customer = driver.findElement(By.xpath(LM.getProperty("CustomerId")));
			customer.clear();
			customer.sendKeys(customerid);
			Thread.sleep(2000);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);

			// Utilities.enterTextInDropDown(check, LM.getProperty("number"),
			// driver);
			Utilities.enterTextInDropDown("Yes", LM.getProperty("Link"), driver);

			Utilities.enterTextInDropDown("Lease Delivery Order", LM.getProperty("linkTo"), driver);

			// String check="invoice"+documentid;
			// System.out.println(">>>>"+check);

			// To Open Drop down
			WebElement drpDwBtn = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LM.getProperty("Opendropdown"))));
			drpDwBtn.click();

			// Send Doc id
			WebElement inpfield = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LM.getProperty("senddocid"))));
			inpfield.click();
			Thread.sleep(1000);
			Utilities.sendText("delivery" + documentid);
			Thread.sleep(2000);

			// Select listed product
			WebElement result = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("sellistprod"))));
			result.click();
			Thread.sleep(3000);

			// Select Ok arrow
			WebElement selectdrpDwBtn = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("okarrow"))));
			selectdrpDwBtn.click();
			Thread.sleep(3000);

			driver.findElement(By.xpath(LM.getProperty("meomo"))).click();
			Thread.sleep(2000);

			// To given Sequence format document no.
			WebElement squence = driver.findElement(By.xpath(LM.getProperty("sequenceFormat")));
			Thread.sleep(2000);
			squence.clear();
			Thread.sleep(1000);
			squence.sendKeys("NA");
			Thread.sleep(2000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// To given Sequence format document no.
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseInvoiceNumber"))));
			driver.findElement(By.xpath(LM.getProperty("LeaseInvoiceNumber"))).sendKeys(documentid1);
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("saveButton"))));
			WebElement Save = driver.findElement(By.xpath(LM.getProperty("saveButton")));
			Save.click();

			Utilities.clickButton("Yes", 1000, driver);

			Utilities.clickButton("OK", 1000, driver);

			Utilities.refresh();

			System.out.println("Document is created successfully : " + documentid1);

		} catch (Exception EX) {

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(2000);// pro

	}

	// ****************************************************************************Verification**********************************************************************************************************

	public static void VerifyLM_Invoice(String documentid, String Aset_Id, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			String documentid1 = "invoice" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_LeaseInvoice.properties");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("LeaseInvreport"))));
			driver.findElement(By.xpath(pro.getProperty("LeaseInvreport"))).click();
			Thread.sleep(5000);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentid1);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			int lsOrderRowIndex = 0, customerRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath("//div[@id='gridmsg16LeaseFixedAssetInvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			Thread.sleep(1000);

			for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
				String header = driver.findElement(By
						.xpath("//div[@id='gridmsg16LeaseFixedAssetInvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equals("Invoice No")) {
					lsOrderRowIndex = rowIndex;
					// System.out.println(lsOrderRowIndex);

				} else if (header.equals("Customer")) {
					customerRowIndex = rowIndex;
					// System.out.println(customerRowIndex);

				}
			}

			List<WebElement> totalLinks = driver
					.findElements(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div"));
			int resultTotalCnt = totalLinks.size();
			// System.out.println("totalResultcnt > > "+resultTotalCnt);

			for (int j = 1; j <= resultTotalCnt; j++) {

				assertEquals(documentid1,
						driver.findElement(
								By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div/table/tbody/tr[1]/td["
										+ lsOrderRowIndex + "]/div"))
								.getText());

				System.out.println("UI DOC ID = " + driver
						.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div/table/tbody/tr[1]/td["
								+ lsOrderRowIndex + "]/div"))
						.getText());

				assertEquals(customerid + "Name",
						driver.findElement(
								By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div/table/tbody/tr[1]/td["
										+ customerRowIndex + "]/div"))
								.getText());

				System.out.println("UI customer ID = " + driver
						.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div/table/tbody/tr[1]/td["
								+ customerRowIndex + "]/div"))
						.getText());
			}

			System.out.println(
					"Document ID and Customer Name is present in system, hance document verified successfully ["
							+ documentid1 + "]");
			Utilities.refresh();
		}

		catch (Exception EX) {
			System.out.println(" Document is not present [" + documentid + "]");
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	// **************************************************************************Edit/Delete
	// ***************************************************************************//

	public void LM_Edit_LeaseInvoice(String documentid1, String Asset_Id, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid1 = "invoice" + documentid1;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_LeaseInvoice.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("LeaseInvreport"))));
			driver.findElement(By.xpath(pro.getProperty("LeaseInvreport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid1);
			search.sendKeys(Keys.TAB);
			Thread.sleep(3000);

			WebElement checkbox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("reportCheckBox"))));
			checkbox.click();
			Thread.sleep(2000);

			WebElement editcustomerButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditLSIbutton"))));
			editcustomerButton.click();
			Thread.sleep(20000);

			WebElement LCQmemo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditMemo"))));
			LCQmemo.clear();
			LCQmemo.sendKeys("EditMemo");
			Thread.sleep(3000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("buttonSave"))));
			driver.findElement(By.xpath(pro.getProperty("buttonSave"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1500, driver);

			WebElement clsEditBtn = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("closeEditWin"))));
			clsEditBtn.click();
			Thread.sleep(3000);
			System.out.println("Lease Invoice is  editted Successfully");
			search.clear();
			Thread.sleep(1000);
			search.sendKeys(documentid1);
			search.sendKeys(Keys.TAB);
			Thread.sleep(3000);

			/*
			 * WebElement checkbox1 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.
			 * getProperty("reportCheckBox")))); checkbox1.click();
			 * Thread.sleep(3000);
			 * 
			 * WebElement DeleteCQButton =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.
			 * getProperty("DeleteButton")))); DeleteCQButton.click();;
			 * Thread.sleep(5000);
			 * 
			 * Actions actions = new Actions(driver); WebElement mainMenu =
			 * driver.findElement(By.xpath(pro.getProperty("PermanantDelete")));
			 * actions.moveToElement(mainMenu);
			 * actions.click().build().perform(); Thread.sleep(3000);
			 * 
			 * WebElement PermanentDeleteButton =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.
			 * getProperty("PermanantDelete")))); PermanentDeleteButton.click();
			 * Thread.sleep(3000);
			 * 
			 * //Click on yes new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(pro.getProperty("YesButton"))));
			 * driver.findElement(By.xpath(pro.getProperty("YesButton"))).click(
			 * ); Thread.sleep(3000);
			 * 
			 * Utilities.clickButton("OK", 0, driver); Thread.sleep(1000);
			 * String expected = "Get Started by adding a Sales Return now...";
			 * 
			 * 
			 * WebElement search4 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.
			 * getProperty("QuickSearch")))); search4.clear();
			 * search4.sendKeys(documentid1); search4.sendKeys(Keys.TAB);
			 * Thread.sleep(4000);
			 * 
			 * WebElement expResul=
			 * driver.findElement(By.xpath(pro.getProperty("Title")));
			 * 
			 * if(expResul.isDisplayed()){ assertEquals(expected,
			 * expResul.getText());
			 * System.out.println("Lease Sales Return is successfully deleted");
			 * } else { System.out.println("Lease Sales Return is not deleted");
			 * }
			 */
			Utilities.refresh();

		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);

		}

	}
}
