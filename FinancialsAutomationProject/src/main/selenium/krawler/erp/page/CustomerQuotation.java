package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerQuotation {

	public static void create_customerQutotation(String documentid, String productid, String customerid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			documentid = "CQ" + documentid;
			Properties CustomerQuotation = Utilities.fetchProValue("OR_CustomerQuotation.properties");

			Thread.sleep(3000);
			// WebDriverWait wait = new WebDriverWait(driver, 30);

			// clicked on Purchase Invoice
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(CustomerQuotation.getProperty("CustomerQuotationIcon"))));
			driver.findElement(By.xpath(CustomerQuotation.getProperty("CustomerQuotationIcon"))).click();
			Thread.sleep(10000);// pro

			// enter vendor name
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CustomerQuotation.getProperty("CustomerId"))));
			WebElement customer = driver.findElement(By.xpath(CustomerQuotation.getProperty("CustomerId")));
			customer.clear();
			customer.sendKeys(customerid);
			Thread.sleep(3000);// pro
			customer.sendKeys(Keys.ENTER);

			// sequen format document no.
			WebElement squence = driver.findElement(By.xpath(CustomerQuotation.getProperty("sequenceFormat")));
			Thread.sleep(2000);
			squence.clear();
			Thread.sleep(1000);
			squence.sendKeys("NA");
			Thread.sleep(2000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// sequen format document no.
			WebElement term = driver.findElement(By.xpath(CustomerQuotation.getProperty("CreditTerm")));
			term.clear();
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(CustomerQuotation.getProperty("CustomerQuotationNo"))));
			driver.findElement(By.xpath(CustomerQuotation.getProperty("CustomerQuotationNo"))).sendKeys(documentid);

			// click on add button
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CustomerQuotation.getProperty("ButtonAdd"))));
			driver.findElement(By.xpath(CustomerQuotation.getProperty("ButtonAdd"))).click();

			// search product id
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(CustomerQuotation.getProperty("SearchProductId"))));
			driver.findElement(By.xpath(CustomerQuotation.getProperty("SearchProductId"))).sendKeys(productid);
			Thread.sleep(2000);// pro

			// select check box
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CustomerQuotation.getProperty("ClickCheckBox"))));
			driver.findElement(By.xpath(CustomerQuotation.getProperty("ClickCheckBox"))).click();
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CustomerQuotation.getProperty("buttonAdd"))));
			driver.findElement(By.xpath(CustomerQuotation.getProperty("buttonAdd"))).click();
			Thread.sleep(3000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//*[@id='quotationundefinededitproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size(); i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='requisitioneditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				if (driver.findElement(By
						.xpath("//*[@id='quotationundefinededitproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Quantity")) {
					driver.findElement(By
							.xpath("//*[@id='quotationundefinededitproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					break;
				}
			}

			Thread.sleep(2000);
			Utilities.sendText("1");
			Thread.sleep(2000);

			driver.findElement(By.xpath(CustomerQuotation.getProperty("memo"))).click();
			// ------------------------------------------------------------------------------------------------------------------------------------------------

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CustomerQuotation.getProperty("buttonSave"))));
			driver.findElement(By.xpath(CustomerQuotation.getProperty("buttonSave"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(2000);

			// Email code
			String subjectLine = "Customer Quotation - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);
			Thread.sleep(1000);

			System.out.println("* * * *Customer Quotation Created for :" + documentid);
			// Click on OK
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CustomerQuotation.getProperty("CloseQuotation"))));
			driver.findElement(By.xpath(CustomerQuotation.getProperty("CloseQuotation"))).click();
		}

		catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	public static void verify_CustomerQuotation(String documentid, String productid, String Customer, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "CQ" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_CustomerQuotation.properties");
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CustomerQuotationReport"))));
			driver.findElement(By.xpath(pro.getProperty("CustomerQuotationReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath("//div[@id='gridmsg35QuotationListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='gridmsg35QuotationListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath("//div[@id='gridmsg35QuotationListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Customer Quotation No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath("//div[@id='gridmsg35QuotationListEntry']/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (header.equals("Customer")) {
					assertEquals(Customer + "Name",
							driver.findElement(By
									.xpath("//div[@id='gridmsg35QuotationListEntry']/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (header.equals("Customer Code")) {
					assertEquals(Customer,
							driver.findElement(By
									.xpath("//div[@id='gridmsg35QuotationListEntry']/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

			}
			Thread.sleep(500);
			System.out.println("Customer Quotation Successfully verified");
			String closeReportPage = "//li[@id='as__QuotationListEntry']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	public static void EmailcopyEditDelete_CustomerQuotation(String documentid, String productid, String customerid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_CustomerQuotation.properties");
			documentid = "CQ" + documentid;
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CustomerQuotationReport"))));
			driver.findElement(By.xpath(pro.getProperty("CustomerQuotationReport"))).click();
			Thread.sleep(5000);// pro

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.clear();
			search.sendKeys(documentid);
			Thread.sleep(500);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			// Report Email code
			String subjectLine = "Customer Quotation - testsmoke - " + documentid;
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys(documentid);
			Thread.sleep(500);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);
			WebElement copyButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyButton"))));
			copyButton.click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentIDCopyQuotation"))));
			driver.findElement(By.xpath(pro.getProperty("DocumentIDCopyQuotation"))).sendKeys("CopyCQ" + documentid);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("buttonSave"))));
			driver.findElement(By.xpath(pro.getProperty("buttonSave"))).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("buttonYes"))));
			driver.findElement(By.xpath(pro.getProperty("buttonYes"))).click();

			Thread.sleep(3000);
			// ------------------------------------------------------------------------------------------------------------------
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("buttonOk"))));
			WebElement element = driver.findElement(By.xpath(pro.getProperty("buttonOk")));
			Actions action = new Actions(driver);
			action.moveToElement(element).click().perform();
			Thread.sleep(1000);

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseCopyQuotation"))));
			driver.findElement(By.xpath(pro.getProperty("CloseCopyQuotation"))).click();

			// -------------------------------------------------

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys("CopyCQ" + documentid);
			Thread.sleep(500);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement editVendorButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditCQButton"))));
			editVendorButton.click();
			;
			Thread.sleep(3000);

			WebElement CQmemo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditMemo"))));
			CQmemo.sendKeys("CQmenoeditcase");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("buttonSave"))));
			driver.findElement(By.xpath(pro.getProperty("buttonSave"))).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("buttonYes"))));
			driver.findElement(By.xpath(pro.getProperty("buttonYes"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("OK", 1000, driver);
			;
			Thread.sleep(1000);
			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseEditQuotation"))));
			driver.findElement(By.xpath(pro.getProperty("CloseEditQuotation"))).click();
			Thread.sleep(1000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys("CopyCQ" + documentid);
			Thread.sleep(500);
			search.sendKeys(Keys.TAB);
			Thread.sleep(3000);

			// System.out.println(driver.findElement(By.xpath("//div[@id='QuotationListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div")).getText());
			if (driver
					.findElement(By
							.xpath("//div[@id='QuotationListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
					.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
				System.out.println("Customer Quotation is sucessfully edited");
			} else {
				System.out.println("Customer Quotation is not edited properly");
			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			WebElement DeleteCQButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DeleteButton"))));
			DeleteCQButton.click();
			;
			Thread.sleep(3000);

			WebElement PermanentDeleteButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PermanantDelete"))));
			PermanentDeleteButton.click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(000);

			search.clear();
			search.sendKeys("CopyCQ" + documentid);
			Thread.sleep(500);
			search.sendKeys(Keys.TAB);
			Thread.sleep(3000);

			// System.out.println(driver.findElement(By.xpath("//div[@id='QuotationListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div")).getText());
			if (driver
					.findElement(By
							.xpath("//div[@id='QuotationListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
					.getText().equalsIgnoreCase("No results to display")) {
				System.out.println("Customer Quotation is sucessfully deleted");
			} else {
				System.out.println("Customer Quotation is not deleted properly");
			}

			String closeReportPage = "//li[@id='as__QuotationListEntry']/a[1]";
			Utilities.HoverandClick(closeReportPage, driver);
		}

		catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	// * * * * * * * EXPORT Customer Quotation * * * * * *

	public static void Export_CustomerQuotation(WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		String BtnName2 = "Export List";
		Properties pro = Utilities.fetchProValue("OR_CustomerQuotation.properties");
		String reportIcon = pro.getProperty("CustomerQuotationReport");
		String waitForQuickSearch = pro.getProperty("DocumentCheckBox");
		String closeReportPage = "//li[@id='as__QuotationListEntry']/a[1]";
		String ModuleName = "Customer Quotation";

		ExportFunction.Export_TodayDate(BtnName2, reportIcon, waitForQuickSearch, closeReportPage, ModuleName, driver);

	}

}
