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

public class VendorQuotation {

	public static void create_VendorQuatation(String documentid, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{

		try {
			documentid = "VQ" + documentid;
			Properties vendorQuotationProperties = Utilities.fetchProValue("OR_VendorQuotation.properties");
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			// Click on Vendor Quatation
			WebElement Module = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath(vendorQuotationProperties.getProperty("CreateVendorQuotationIcon")))); // examining

			Thread.sleep(2000);
			Module.click();

			// Click on Vendor
			Thread.sleep(10000);
			WebElement Vendor = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(vendorQuotationProperties.getProperty("vendorName"))));
			Vendor.sendKeys(vendorid);
			Thread.sleep(2000);
			Vendor.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			// Click on NA
			WebElement NA = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(vendorQuotationProperties.getProperty("sequenceformat"))));
			Thread.sleep(500);
			NA.clear();

			NA.sendKeys("NA");
			NA.sendKeys(Keys.ENTER);
			Thread.sleep(5000);

			// Click on Vendor Quatation number
			WebElement VQN = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(vendorQuotationProperties.getProperty("VendorQuatationId"))));

			VQN.sendKeys(documentid);

			// Click on Add Button
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(vendorQuotationProperties.getProperty("addButton"))));
			driver.findElement(By.xpath("//*[@class='pwnd add-gridrow']")).click();

			// Click on search product
			WebElement SearchPro = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(vendorQuotationProperties.getProperty("productSearch"))));

			SearchPro.sendKeys(productid);
			Thread.sleep(5000);

			// Click on select product
			WebElement SelectPro = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(vendorQuotationProperties.getProperty("productSelect"))));

			SelectPro.click();

			// Click on Add product window button

			WebElement ADD2 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(vendorQuotationProperties.getProperty("productButton"))));

			ADD2.click();
			Thread.sleep(2000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath(".//*[@id='vendorquotationundefinededitproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size(); i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='requisitioneditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				if (driver.findElement(By
						.xpath(".//*[@id='vendorquotationundefinededitproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Quantity")) {
					driver.findElement(By
							.xpath(".//*[@id='vendorquotationundefinededitproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
				}
			}

			/*
			 * // Click on Quantity button WebElement Quantity =
			 * wait.until(ExpectedConditions
			 * .elementToBeClickable(By.xpath(vendorQuotationProperties.
			 * getProperty("quantityButton"))));
			 * 
			 * Quantity.click();
			 */

			Thread.sleep(2000);
			Utilities.sendText("1");
			Thread.sleep(2000);

			// Click on Memo Field
			WebElement memo = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(vendorQuotationProperties.getProperty("memoField"))));

			memo.click();
			Thread.sleep(2000);

			// Click on Save Button
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(vendorQuotationProperties.getProperty("saveButton"))));
			driver.findElement(By.xpath(vendorQuotationProperties.getProperty("saveButton"))).click();
			Thread.sleep(2000);

			// Click on yes Button

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(vendorQuotationProperties.getProperty("yesButton"))));
			driver.findElement(By.xpath(vendorQuotationProperties.getProperty("yesButton"))).click();
			Thread.sleep(3000);

			// Click on OK Button

			new WebDriverWait(driver, 60).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(vendorQuotationProperties.getProperty("okButton"))));
			driver.findElement(By.xpath(vendorQuotationProperties.getProperty("okButton"))).click();
			Thread.sleep(3000);

			// Email code
			String subjectLine = "Vendor Quotation - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(vendorQuotationProperties.getProperty("quotationOk"))));
			driver.findElement(By.xpath(vendorQuotationProperties.getProperty("quotationOk"))).click();

			System.out.println("Vendor Qautation [" + documentid + "] created Success !!! ");
		} catch (Exception EX) {
			System.out.println("Vendor Qautation [" + documentid + "] NOT CREATED !!! ");
			Utilities.handleError(EX, driver);
		}

	}

	// ********************************************verification**************************************************

	public static void verify_VendorQuatation(String documentid, String productid, String Vendor, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "VQ" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_VendorQuotation.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("VendorQuotationReport"))));
			driver.findElement(By.xpath(pro.getProperty("VendorQuotationReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg35VendorQuotationList']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg35VendorQuotationList']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath("//div[@id='gridmsg35VendorQuotationList']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Vendor Quotation No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg35VendorQuotationList']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
											+ i + "]/div"))
									.getText());
				}

				else if (header.equals("Vendor")) {
					assertEquals(Vendor + "Name",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg35VendorQuotationList']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
											+ i + "]/div"))
									.getText());
				}

				else if (header.equals("Vendor Code")) {
					assertEquals(Vendor,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg35VendorQuotationList']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
											+ i + "]/div"))
									.getText());
				}

			}

			String xpathOfelement = pro.getProperty("CloseVendorQuotationTab");
			Utilities.HoverandClick(xpathOfelement, driver);

			System.out.println("Vendor Quation Successfully verified");

		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	public static void EmailCopyEditDelete_VendorQuatation(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "VQ" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_VendorQuotation.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("VendorQuotationReport"))));
			driver.findElement(By.xpath(pro.getProperty("VendorQuotationReport"))).click();
			Thread.sleep(5000);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			WebElement checkbox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentCheckBox"))));
			checkbox.click();
			Thread.sleep(2000);

			// Email code
			String subjectLine = "Vendor Quotation - testsmoke - " + documentid;
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);
			Thread.sleep(2000);

			search.clear();
			search.sendKeys(documentid);
			Thread.sleep(2000);
			search.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			checkbox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentCheckBox"))));
			checkbox.click();
			Thread.sleep(2000);

			WebElement copyVendorQuotationButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyVendorQuotationButton"))));
			copyVendorQuotationButton.click();
			Thread.sleep(2000);

			String CopyVendorQuotationID = "Copy" + documentid;

			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyVendorQuotationId"))));
			driver.findElement(By.xpath(pro.getProperty("copyVendorQuotationId"))).sendKeys(CopyVendorQuotationID);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("saveCopiedVendorQuotationId"))));

			driver.findElement(By.xpath(pro.getProperty("saveCopiedVendorQuotationId"))).click();

			Thread.sleep(3000);

			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			Thread.sleep(3000);
			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("CopyVendorQuotationClose"))));
				driver.findElement(By.xpath(pro.getProperty("CopyVendorQuotationClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}
			search.clear();
			search.sendKeys(CopyVendorQuotationID);
			Thread.sleep(2000);
			search.sendKeys(Keys.TAB);

			Thread.sleep(2000);
			checkbox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentCheckBox"))));
			checkbox.click();
			Thread.sleep(2000);

			WebElement editVendorQuotationButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditVendorQuotationButton"))));
			editVendorQuotationButton.click();
			Thread.sleep(3000);

			WebElement VendorQuotationMemo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("VendorQuotationMemo"))));
			VendorQuotationMemo.sendKeys("Update VQ Memo");

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("VendorQuotationEditSave"))));
			driver.findElement(By.xpath(pro.getProperty("VendorQuotationEditSave"))).click();
			Thread.sleep(3000);

			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			Thread.sleep(3000);
			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);
			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("EditVendorQuotationClose"))));
				driver.findElement(By.xpath(pro.getProperty("EditVendorQuotationClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Thread.sleep(3000);
			search.clear();
			search.sendKeys(CopyVendorQuotationID);
			Thread.sleep(2000);
			search.sendKeys(Keys.TAB);

			Thread.sleep(2000);

			try {
				new WebDriverWait(driver, 30).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("DocumentCheckBox"))));
				driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).click();
				Thread.sleep(3000);// pro

				if (driver
						.findElement(By
								.xpath("//div[@id='VendorQuotationList']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
					System.out.println("Vendor Quotation " + CopyVendorQuotationID + " is edited Successfully");
				} else {
					System.out.println("Vendor Quotation " + CopyVendorQuotationID + " is not edited Successfully");
				}
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);
			}

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteVendorQuotationButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteVendorQuotationButton"))).click();

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteVendorQuotationPermButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteVendorQuotationPermButton"))).click();
			Thread.sleep(2000);

			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			Thread.sleep(2000);

			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			Thread.sleep(2000);

			try {
				search.clear();
				search.sendKeys(CopyVendorQuotationID);
				Thread.sleep(2000);
				if (driver
						.findElement(By
								.xpath("//div[@id='VendorQuotationList']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("No results to display")) {
					System.out.println("Vendor Quotation " + CopyVendorQuotationID + "  is Deleted Successfully");
				} else {
					System.out.println("Vendor Quotation " + CopyVendorQuotationID + "  is not Deleted Successfully");
				}
			} catch (Exception exp) {
				System.out.println("Vendor Quotation " + CopyVendorQuotationID + " is Successfully deleted");
			}
			String xpathOfelement = pro.getProperty("CloseVendorQuotationTab");
			Utilities.HoverandClick(xpathOfelement, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}
	}

	// * * * * * * * EXPORT VQ * * * * * *

	public static void Export_VQ(WebDriver driver) throws InterruptedException, AWTException, IOException {
		String BtnName2 = "Export List";
		Properties pro = Utilities.fetchProValue("OR_VendorQuotation.properties");
		String reportIcon = pro.getProperty("VendorQuotationReport");
		String waitForQuickSearch = pro.getProperty("DocumentCheckBox");
		String closeReportPage = pro.getProperty("CloseVendorQuotationTab");
		String ModuleName = "Vendor Quotation";

		ExportFunction.Export_TodayDate(BtnName2, reportIcon, waitForQuickSearch, closeReportPage, ModuleName, driver);

	}

}
