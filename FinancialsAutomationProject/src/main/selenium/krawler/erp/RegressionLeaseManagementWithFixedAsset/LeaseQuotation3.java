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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class LeaseQuotation3 {

	private static final Properties LM = null;

	public void LM_CreateQuotation(String documentid, String Asset_Id, String customerid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			String documentid1 = "quotation" + documentid;
			Properties LM = Utilities.fetchProValue("OR_LeaseManagement.properties");
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement LMButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseQuotationIcon")))); // examining
			LMButton.click();
			Thread.sleep(4000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("CustomerId"))));
			WebElement customer = driver.findElement(By.xpath(LM.getProperty("CustomerId")));
			customer.clear();
			customer.sendKeys(customerid);
			Thread.sleep(2000);// pro
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);

			// driver.findElement(By.xpath(LM.getProperty("meomo"))).click();
			// Thread.sleep(2000);//pro

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
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseQuotationNumber"))));
			driver.findElement(By.xpath(LM.getProperty("LeaseQuotationNumber"))).sendKeys(documentid1);

			WebElement term = driver.findElement(By.xpath(LM.getProperty("CreditTerm")));
			term.clear();
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// search product id
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("SearchProductId"))));
			driver.findElement(By.xpath(LM.getProperty("SearchProductId"))).click();
			Thread.sleep(2000);// pro

			// Pass productID
			Utilities.sendText(Asset_Id);
			Thread.sleep(2000);

			// Select productID
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("ClickProduct"))));
			driver.findElement(By.xpath(LM.getProperty("ClickProduct"))).click();
			Thread.sleep(2000);// pro
			driver.findElement(By.xpath(LM.getProperty("memo"))).click();

			// Put the quantity and Unit Price for Asset group

			int lsOrderRowIndex = 0, customerRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath("//div[@id='leasequotationnulleditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size();
			Thread.sleep(1000);

			for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
				String header = driver.findElement(By
						.xpath("//div[@id='leasequotationnulleditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equalsIgnoreCase("Quantity")) {
					lsOrderRowIndex = rowIndex;
					driver.findElement(By
							.xpath("//div[@id='leasequotationnulleditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ lsOrderRowIndex + "]/div"))
							.click();
					Thread.sleep(2000);
					Utilities.sendText("1");
					driver.findElement(By.xpath(LM.getProperty("memo"))).click();
					Thread.sleep(2000);

				}

				else if (header.equalsIgnoreCase("Unit Price")) {
					customerRowIndex = rowIndex;
					driver.findElement(By
							.xpath("//div[@id='leasequotationnulleditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ customerRowIndex + "]/div"))
							.click();
					Thread.sleep(2000);
					Utilities.sendText("1000");
					Thread.sleep(2000);

				}
			}

			// Click On Save button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("saveButton"))));
			WebElement Save = driver.findElement(By.xpath(LM.getProperty("saveButton")));
			Save.click();

			Utilities.clickButton("Yes", 1000, driver);
			System.out.println("\"Lease Quotation\" document is created successfully : " + documentid1);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.refresh();

		} catch (Exception EX) {

			Utilities.handleError(EX, driver);
			System.out.println("\"Lease Quotation\" document is  not created.");
		}
		Thread.sleep(2000);// pro

	}

	// ****************************************************************************Verification**********************************************************************************************************

	public static void VerifyLM_Quotation(String documentid, String Asset_Id, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			String documentid1 = "quotation" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_LeaseManagement.properties");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("LeaseQuotationReport"))));
			driver.findElement(By.xpath(pro.getProperty("LeaseQuotationReport"))).click();
			Thread.sleep(5000);// pro

			// Utilities.clickCheckBox(pro.getProperty(""), "unchek", driver);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid1);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			int lsOrderRowIndex = 0, customerRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath("//*[@id='gridmsg35LeaseQuotationListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			Thread.sleep(1000);

			for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
				String header = driver.findElement(By
						.xpath("//*[@id='gridmsg35LeaseQuotationListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equals("Lease Quotation No")) {
					lsOrderRowIndex = rowIndex;
					// //System.out.println(lsOrderRowIndex);

				} else if (header.equals("Customer")) {
					customerRowIndex = rowIndex;
					// //System.out.println(customerRowIndex);

				}
			}

			List<WebElement> totalLinks = driver
					.findElements(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div"));
			int resultTotalCnt = totalLinks.size();
			// // System.out.println("totalResultcnt > > "+resultTotalCnt);

			for (int j = 1; j <= resultTotalCnt; j++) {

				assertEquals(documentid1,
						driver.findElement(
								By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[1]/table/tbody/tr/td["
										+ lsOrderRowIndex + "]/div"))
								.getText());

				System.out.println("UI DOC ID =" + " "
						+ driver.findElement(
								By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[1]/table/tbody/tr/td["
										+ lsOrderRowIndex + "]/div"))
								.getText());

				assertEquals(customerid + "Name",
						driver.findElement(
								By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[1]/table/tbody/tr/td["
										+ customerRowIndex + "]/div"))
								.getText());

				System.out.println("UI customer ID = " + driver
						.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[1]/table/tbody/tr/td["
								+ customerRowIndex + "]/div"))
						.getText());
			}

			System.out.println(
					"Document ID and Customer Name is present in system, hance document verified successfully ["
							+ documentid1 + "]");
			Utilities.refresh();
		}

		catch (Exception EX) {
			System.out.println(" Document is not verified. [" + "quotation" + documentid + "]");
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	// **************************************************************************Edit/Delete
	// ***************************************************************************//

	public void EditDelete_CustomerQuotation(String documentid1, String Asset_Id, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid1 = "quotation" + documentid1;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_LeaseManagement.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("LeaseQuotationReport"))));
			driver.findElement(By.xpath(pro.getProperty("LeaseQuotationReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid1);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			WebElement checkbox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("reportCheckBox"))));
			checkbox.click();
			Thread.sleep(2000);

			WebElement editcustomerButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditLQbutton"))));
			editcustomerButton.click();
			Thread.sleep(3000);

			WebElement LCQmemo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditMemo"))));
			LCQmemo.sendKeys("EditLeaseQuotation");
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
			search.clear();
			Thread.sleep(1000);
			search.sendKeys(documentid1);
			search.sendKeys(Keys.TAB);
			Thread.sleep(3000);

			WebElement checkbox1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("reportCheckBox"))));
			checkbox1.click();

			WebElement DeleteCQButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DeleteButton"))));
			DeleteCQButton.click();
			;
			Thread.sleep(5000);

			WebElement PermanentDeleteButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PermanantDelete"))));
			PermanentDeleteButton.click();
			Thread.sleep(3000);

			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("YesButton"))));
			driver.findElement(By.xpath(pro.getProperty("YesButton"))).click();
			Thread.sleep(5000);

			WebElement search4 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search4.clear();
			search4.sendKeys(documentid1);
			search4.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			// System.out.println(driver.findElement(By.xpath("//div[@id='QuotationListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div")).getText());
			if (driver.findElement(By.xpath(pro.getProperty("BottomPannel"))).getText()
					.equalsIgnoreCase("No results to display")) {
				System.out.println("Lease Quotation is sucessfully deleted");
			} else {
				System.out.println("Lease Quotation is not deleted properly");
			}

			Utilities.refresh();

		}

		catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);

		}
	}

}
