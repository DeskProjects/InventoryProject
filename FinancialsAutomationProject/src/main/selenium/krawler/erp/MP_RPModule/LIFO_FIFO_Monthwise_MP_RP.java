package krawler.erp.MP_RPModule;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.EmailFunctionality;
import krawler.erp.page.Utilities;

public class LIFO_FIFO_Monthwise_MP_RP {

	public static void PurchaseInvoice1(WebDriver driver, String documentId, String vendorid, String productid,
			String DocumentCurrency) throws InterruptedException, AWTException, IOException {

		try {

			documentId = "PIFIFO" + documentId;
			Properties pro = Utilities.fetchProValue("OR_VendorInvoice.properties");

			Utilities.waitandClick(pro.getProperty("CreateVendorInvoiceIcon"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("VendorId"), driver);
			Utilities.enterTextandSelect(DocumentCurrency, pro.getProperty("DocumentCurrency"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.clickAndEnterText(documentId, pro.getProperty("VendorInvoiceNumber"), driver);
			Utilities.waitandClick(pro.getProperty("PIDate"), driver);
			Utilities.waitandClick(
					"//div[@class='x-layer x-menu x-menu-plain x-date-menu' and contains(@style,'visible')]//td[@title='Today']/preceding::td[2]",
					driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("searchProductId"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int i = driver
					.findElements(By
							.xpath("//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size();

			for (int j = 1; j <= i; j++) {
				String header = driver.findElement(By
						.xpath("//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ j + "]/div"))
						.getText();
				if (header.equals("Quantity")) {

					Utilities.clickAndEnterText("1",
							"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ j + "]/div",
							driver);
					Utilities.HoverandClick(pro.getProperty("memo"), driver);
				} else if (header.equals("Unit Price")) {

					Utilities.clickAndEnterText("100",
							"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ j + "]/div",
							driver);
				}
			}

			Utilities.HoverandClick(pro.getProperty("memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("closeVendorInvoice"), driver);

			System.out.println("******* Purchase Invoice : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception Ex) {
			Utilities.handleError(Ex, driver);
		}
	}

	public static void PurchaseInvoice2(WebDriver driver, String documentId, String vendorid, String productid,
			String DocumentCurrency) throws InterruptedException, AWTException, IOException {

		try {

			documentId = "PILIFO" + documentId;
			Properties pro = Utilities.fetchProValue("OR_VendorInvoice.properties");

			Utilities.waitandClick(pro.getProperty("CreateVendorInvoiceIcon"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("VendorId"), driver);
			Utilities.enterTextandSelect(DocumentCurrency, pro.getProperty("DocumentCurrency"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.clickAndEnterText(documentId, pro.getProperty("VendorInvoiceNumber"), driver);
			// Utilities.waitandClick(pro.getProperty("PIDate"), driver);
			// Utilities.waitandClick("//div[@class='x-layer x-menu x-menu-plain
			// x-date-menu' and
			// contains(@style,'visible')]//td[@title='Today']/preceding::td[2]",
			// driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("searchProductId"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int i = driver
					.findElements(By
							.xpath("//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size();

			for (int j = 1; j <= i; j++) {
				String header = driver.findElement(By
						.xpath("//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ j + "]/div"))
						.getText();
				if (header.equals("Quantity")) {

					Utilities.clickAndEnterText("1",
							"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ j + "]/div",
							driver);
					Utilities.HoverandClick(pro.getProperty("memo"), driver);
				} else if (header.equals("Unit Price")) {

					Utilities.clickAndEnterText("100",
							"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ j + "]/div",
							driver);
				}
			}

			Utilities.HoverandClick(pro.getProperty("memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("closeVendorInvoice"), driver);

			System.out.println("******* Purchase Invoice : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception Ex) {
			Utilities.handleError(Ex, driver);
		}
	}

	public static void PurchaseInvoice3(WebDriver driver, String documentId, String vendorid, String productid,
			String DocumentCurrency) throws InterruptedException, AWTException, IOException {

		try {

			documentId = "PIMonth" + documentId;
			Properties pro = Utilities.fetchProValue("OR_VendorInvoice.properties");

			Utilities.waitandClick(pro.getProperty("CreateVendorInvoiceIcon"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("VendorId"), driver);
			Utilities.enterTextandSelect(DocumentCurrency, pro.getProperty("DocumentCurrency"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.clickAndEnterText(documentId, pro.getProperty("VendorInvoiceNumber"), driver);
			Utilities.waitandClick(pro.getProperty("PIDate"), driver);
			Utilities.waitandClick(
					"//div[@class='x-layer x-menu x-menu-plain x-date-menu' and contains(@style,'visible')]//td[@title='Today']/preceding::td[1]",
					driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("searchProductId"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int i = driver
					.findElements(By
							.xpath("//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size();

			for (int j = 1; j <= i; j++) {
				String header = driver.findElement(By
						.xpath("//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ j + "]/div"))
						.getText();
				if (header.equals("Quantity")) {

					Utilities.clickAndEnterText("1",
							"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ j + "]/div",
							driver);
					Utilities.HoverandClick(pro.getProperty("memo"), driver);
				} else if (header.equals("Unit Price")) {

					Utilities.clickAndEnterText("100",
							"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ j + "]/div",
							driver);
				}
			}

			Utilities.HoverandClick(pro.getProperty("memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("closeVendorInvoice"), driver);

			System.out.println("******* Purchase Invoice : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception Ex) {
			Utilities.handleError(Ex, driver);
		}
	}

	public static void PurchaseInvoice4(WebDriver driver, String documentId, String vendorid, String productid,
			String DocumentCurrency) throws InterruptedException, AWTException, IOException {

		try {

			documentId = "PIFIFO" + documentId;
			Properties pro = Utilities.fetchProValue("OR_VendorInvoice.properties");

			Utilities.waitandClick(pro.getProperty("CreateVendorInvoiceIcon"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("VendorId"), driver);
			Utilities.enterTextandSelect(DocumentCurrency, pro.getProperty("DocumentCurrency"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.clickAndEnterText(documentId, pro.getProperty("VendorInvoiceNumber"), driver);
			Utilities.waitandClick(pro.getProperty("PIDate"), driver);
			Thread.sleep(2000);
			Utilities.waitandClick(
					"//div[contains(@style,'visible')]//button[text()='Today']/ancestor::tbody[2]/tr[1]/td[1]/a",
					driver);
			Utilities.waitandClick(
					"//div[@class='x-layer x-menu x-menu-plain x-date-menu' and contains(@style,'visible')]//button[text()='Today']/preceding::td[31]",
					driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("searchProductId"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int i = driver
					.findElements(By
							.xpath("//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size();

			for (int j = 1; j <= i; j++) {
				String header = driver.findElement(By
						.xpath("//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ j + "]/div"))
						.getText();
				if (header.equals("Quantity")) {

					Utilities.clickAndEnterText("1",
							"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ j + "]/div",
							driver);
					Utilities.HoverandClick(pro.getProperty("memo"), driver);
				} else if (header.equals("Unit Price")) {

					Utilities.clickAndEnterText("200",
							"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ j + "]/div",
							driver);
				}
			}

			Utilities.HoverandClick(pro.getProperty("memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("closeVendorInvoice"), driver);

			System.out.println("******* Purchase Invoice : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception Ex) {
			Utilities.handleError(Ex, driver);
		}
	}

	public static void PurchaseInvoice5(WebDriver driver, String documentId, String vendorid, String productid,
			String DocumentCurrency) throws InterruptedException, AWTException, IOException {

		try {

			documentId = "PIFIFO" + documentId;
			Properties pro = Utilities.fetchProValue("OR_VendorInvoice.properties");

			Utilities.waitandClick(pro.getProperty("CreateVendorInvoiceIcon"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("VendorId"), driver);
			Utilities.enterTextandSelect(DocumentCurrency, pro.getProperty("DocumentCurrency"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.clickAndEnterText(documentId, pro.getProperty("VendorInvoiceNumber"), driver);
			Utilities.waitandClick(pro.getProperty("PIDate"), driver);
			Utilities.waitandClick(
					"//div[@class='x-layer x-menu x-menu-plain x-date-menu' and contains(@style,'visible')]//td[@title='Today']/preceding::td[3]",
					driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("searchProductId"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int i = driver
					.findElements(By
							.xpath("//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size();

			for (int j = 1; j <= i; j++) {
				String header = driver.findElement(By
						.xpath("//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ j + "]/div"))
						.getText();
				if (header.equals("Quantity")) {

					Utilities.clickAndEnterText("1",
							"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ j + "]/div",
							driver);
					Utilities.HoverandClick(pro.getProperty("memo"), driver);
				} else if (header.equals("Unit Price")) {

					Utilities.clickAndEnterText("100",
							"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ j + "]/div",
							driver);
				}
			}

			Utilities.HoverandClick(pro.getProperty("memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("closeVendorInvoice"), driver);

			System.out.println("******* Purchase Invoice : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception Ex) {
			Utilities.handleError(Ex, driver);
		}
	}

	/******************************************* Payment **************************************************/
	public static void CreateMAkePayment(WebDriver driver, String documentId, String vendorid, String PaymentAmount,
			String ButtonName) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("MakePaymentAgainstVendorCheck"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.clickAndEnterText("MP" + documentId, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("vendorID"), driver);

			Utilities.clickAndEnterText(PaymentAmount, pro.getProperty("PaymentAmount"), driver);

			Utilities.clickButton(ButtonName, 2000, driver);
			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}
			} catch (Exception Ex) {

			}
			Utilities.clickButton("OK", 2000, driver);
			Utilities.waitandClick(pro.getProperty("CloseMakePayment"), driver);
			System.out.println("******* Make Payment : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception Ex) {

			System.out.println("******* Make Payment : [" + documentId + "] is not Created !!!!!! ********");

			Utilities.handleError(Ex, driver);
		}

	}

	public static void CreateMAkePayment2(WebDriver driver, String documentId, String vendorid, String PaymentAmount,
			String ButtonName) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("MakePaymentAgainstVendorCheck"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.clickAndEnterText("MP" + documentId, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("vendorID"), driver);

			Utilities.clickAndEnterText(PaymentAmount, pro.getProperty("PaymentAmount"), driver);

			Utilities.clickButton(ButtonName, 2000, driver);
			Utilities.waitandClick(
					"//*[@id='centerpaninvoicemonthwisewindow']//*[@class='x-grid3-scroller']//tr//*[contains(text(),'100')]",
					driver);
			Utilities.clickButton("Submit", 2000, driver);
			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}
			} catch (Exception Ex) {

			}
			Utilities.clickButton("OK", 2000, driver);
			Utilities.waitandClick(pro.getProperty("CloseMakePayment"), driver);
			System.out.println("******* Make Payment : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception Ex) {

			System.out.println("******* Make Payment : [" + documentId + "] is not Created !!!!!! ********");

			Utilities.handleError(Ex, driver);
		}

	}

	public static void CreateMAkePayment3(WebDriver driver, String documentId, String vendorid, String PaymentAmount,
			String ButtonName) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("MakePaymentAgainstVendorCheck"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.clickAndEnterText("MP" + documentId, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("vendorID"), driver);

			Utilities.clickAndEnterText(PaymentAmount, pro.getProperty("PaymentAmount"), driver);

			Utilities.clickButton(ButtonName, 2000, driver);
			Utilities.waitandClick(
					"//*[@id='centerpaninvoicemonthwisewindow']//*[@class='x-grid3-scroller']//tr//*[contains(text(),'100')]",
					driver);
			Utilities.waitandClick(
					"//*[@id='centerpaninvoicemonthwisewindow']//*[@class='x-grid3-scroller']//tr//*[contains(text(),'200')]",
					driver);

			Utilities.clickButton("Submit", 2000, driver);
			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}
			} catch (Exception Ex) {

			}
			Utilities.clickButton("OK", 2000, driver);
			Utilities.waitandClick(pro.getProperty("CloseMakePayment"), driver);
			System.out.println("******* Make Payment : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception Ex) {

			System.out.println("******* Make Payment : [" + documentId + "] is not Created !!!!!! ********");

			Utilities.handleError(Ex, driver);
		}

	}

	/******************************* VerificationMakePayment **************************************/

	public static void VerificationLIFO_FIFO_Monthwise(WebDriver driver, String documentId, String PIDocumentNO,
			String Text) throws InterruptedException, AWTException, IOException {

		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("PaymentMadeReport"), driver);

			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
					".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td[1]/div")));
			Utilities.clickAndEnterText(documentId, pro.getProperty("QuickSearch"), driver);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
					".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td[1]/div")));

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Payment No")) {
					driver.findElement(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div/a"))
							.click();
				}
			}

			Thread.sleep(3000);
			int f = driver
					.findElements(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();

			String DocumentNo = "";

			for (int k = 1; k < f + 1; k++) {
				String header = driver.findElement(By
						.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ k + "]/div"))
						.getText();

				if (header.equals("Document Number")) {
					DocumentNo = driver.findElement(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				}
			}

			if (DocumentNo.equals(PIDocumentNO)) {
				System.out.println("MP " + Text + " is Created  ");
			} else {
				System.out.println("MP " + Text + " is linked ");

			}
			Utilities.waitandClick(pro.getProperty("CloseViewMP"), driver);
			Utilities.waitandClick(pro.getProperty("ClosePaymentMadeReport"), driver);
			System.out.println("Sucessfully Verified LIFO/FIFO/Monthwise MP ");
		} catch (Exception Ex) {
			System.out.println("Unable to Verify LIFO/FIFO/Monthwise MP ");
			Utilities.handleError(Ex, driver);
		}
	}

	/******************************* CreateSaleInvoice **************************************/

	public static void create_salesInvoice(String documentId, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentId = "SIFIFO" + documentId;
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			// clicked on main icon

			Utilities.HoverandClick(pro.getProperty("SalesInvoiceIcon"), driver);
			Utilities.clickCheckBox(pro.getProperty("includeTax"), "uncheck", driver);
			selectFromNormalDropDown(customerid, pro.getProperty("Customer"), driver);
			selectFromNormalDropDown("NA", pro.getProperty("SequenceFormat"), driver);
			selectFromNormalDropDown("NET 45", pro.getProperty("Term"), driver);
			Utilities.enterTextNormalBox(documentId, pro.getProperty("DocumentNo"), driver);

			Utilities.waitandClick(pro.getProperty("SIDate"), driver);
			Utilities.waitandClick(
					"//div[@class='x-layer x-menu x-menu-plain x-date-menu' and contains(@style,'visible')]//td[@title='Today']/preceding::td[2]",
					driver);

			Utilities.HoverandClick(pro.getProperty("AddButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("ProductSearch"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int i = driver
					.findElements(By
							.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();

			for (int j = 1; j <= i; j++) {
				String header = driver.findElement(By
						.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ j + "]/div"))
						.getText();

				if (header.equals("Quantity")) {
					Utilities.clickAndEnterText("1",
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
									+ j + "]/div",
							driver);
					Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
					Utilities.justHover(
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]//div[@class='pwnd delete-gridrow']",
							driver);

				} else if ((header.equals("Unit Price"))) {
					Utilities.clickAndEnterText("100",
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
									+ j + "]/div",
							driver);
				}
			}

			// move ficus frm Line label
			Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			// String subjectLine="Sales Invoice - testsmoke - "+documentId;
			// EmailFunctionality.email_AfterSave(documentId,subjectLine,driver);

			Utilities.HoverandClick(pro.getProperty("CloseSalesInvoice"), driver);

			System.out.println("******* Sales Invoice: [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Sales Invoice is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}

	}

	public static void create_salesInvoice2(String documentId, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentId = "SILIFO" + documentId;
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			// clicked on main icon

			Utilities.HoverandClick(pro.getProperty("SalesInvoiceIcon"), driver);
			Utilities.clickCheckBox(pro.getProperty("includeTax"), "uncheck", driver);
			selectFromNormalDropDown(customerid, pro.getProperty("Customer"), driver);
			selectFromNormalDropDown("NA", pro.getProperty("SequenceFormat"), driver);
			selectFromNormalDropDown("NET 45", pro.getProperty("Term"), driver);
			Utilities.enterTextNormalBox(documentId, pro.getProperty("DocumentNo"), driver);

			Utilities.HoverandClick(pro.getProperty("AddButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("ProductSearch"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int i = driver
					.findElements(By
							.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();

			for (int j = 1; j <= i; j++) {
				String header = driver.findElement(By
						.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ j + "]/div"))
						.getText();

				if (header.equals("Quantity")) {
					Utilities.clickAndEnterText("1",
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
									+ j + "]/div",
							driver);
					Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
					Utilities.justHover(
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]//div[@class='pwnd delete-gridrow']",
							driver);

				} else if ((header.equals("Unit Price"))) {
					Utilities.clickAndEnterText("100",
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
									+ j + "]/div",
							driver);
				}
			}

			// move ficus frm Line label
			Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			// String subjectLine="Sales Invoice - testsmoke - "+documentId;
			// EmailFunctionality.email_AfterSave(documentId,subjectLine,driver);

			Utilities.HoverandClick(pro.getProperty("CloseSalesInvoice"), driver);

			System.out.println("******* Sales Invoice: [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Sales Invoice is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}

	}

	public static void create_salesInvoice3(String documentId, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentId = "SIMonth" + documentId;
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			// clicked on main icon

			Utilities.HoverandClick(pro.getProperty("SalesInvoiceIcon"), driver);
			Utilities.clickCheckBox(pro.getProperty("includeTax"), "uncheck", driver);
			selectFromNormalDropDown(customerid, pro.getProperty("Customer"), driver);
			selectFromNormalDropDown("NA", pro.getProperty("SequenceFormat"), driver);
			selectFromNormalDropDown("NET 45", pro.getProperty("Term"), driver);
			Utilities.enterTextNormalBox(documentId, pro.getProperty("DocumentNo"), driver);

			Utilities.waitandClick(pro.getProperty("SIDate"), driver);
			Utilities.waitandClick(
					"//div[@class='x-layer x-menu x-menu-plain x-date-menu' and contains(@style,'visible')]//td[@title='Today']/preceding::td[1]",
					driver);

			Utilities.HoverandClick(pro.getProperty("AddButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("ProductSearch"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int i = driver
					.findElements(By
							.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();

			for (int j = 1; j <= i; j++) {
				String header = driver.findElement(By
						.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ j + "]/div"))
						.getText();

				if (header.equals("Quantity")) {
					Utilities.clickAndEnterText("1",
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
									+ j + "]/div",
							driver);
					Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
					Utilities.justHover(
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]//div[@class='pwnd delete-gridrow']",
							driver);
				} else if ((header.equals("Unit Price"))) {
					Utilities.clickAndEnterText("100",
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
									+ j + "]/div",
							driver);
				}
			}

			// move ficus frm Line label
			Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			// String subjectLine="Sales Invoice - testsmoke - "+documentId;
			// EmailFunctionality.email_AfterSave(documentId,subjectLine,driver);

			Utilities.HoverandClick(pro.getProperty("CloseSalesInvoice"), driver);

			System.out.println("******* Sales Invoice: [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Sales Invoice is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}

	}

	public static void create_salesInvoice4(String documentId, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentId = "SIFIFO" + documentId;
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			// clicked on main icon

			Utilities.HoverandClick(pro.getProperty("SalesInvoiceIcon"), driver);
			Utilities.clickCheckBox(pro.getProperty("includeTax"), "uncheck", driver);
			selectFromNormalDropDown(customerid, pro.getProperty("Customer"), driver);
			selectFromNormalDropDown("NA", pro.getProperty("SequenceFormat"), driver);
			selectFromNormalDropDown("NET 45", pro.getProperty("Term"), driver);
			Utilities.enterTextNormalBox(documentId, pro.getProperty("DocumentNo"), driver);

			Utilities.waitandClick(pro.getProperty("SIDate"), driver);
			Thread.sleep(2000);

			Utilities.waitandClick(
					"//div[contains(@style,'visible')]//button[text()='Today']/ancestor::tbody[2]/tr[1]/td[1]/a",
					driver);
			Utilities.waitandClick(
					"//div[@class='x-layer x-menu x-menu-plain x-date-menu' and contains(@style,'visible')]//button[text()='Today']/preceding::td[31]",
					driver);

			Utilities.HoverandClick(pro.getProperty("AddButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("ProductSearch"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int i = driver
					.findElements(By
							.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();

			for (int j = 1; j <= i; j++) {
				String header = driver.findElement(By
						.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ j + "]/div"))
						.getText();

				if (header.equals("Quantity")) {
					Utilities.clickAndEnterText("1",
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
									+ j + "]/div",
							driver);
					Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
					Utilities.justHover(
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]//div[@class='pwnd delete-gridrow']",
							driver);

				} else if ((header.equals("Unit Price"))) {
					Utilities.clickAndEnterText("200",
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
									+ j + "]/div",
							driver);
				}
			}

			// move ficus frm Line label
			Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			// String subjectLine="Sales Invoice - testsmoke - "+documentId;
			// EmailFunctionality.email_AfterSave(documentId,subjectLine,driver);

			Utilities.HoverandClick(pro.getProperty("CloseSalesInvoice"), driver);

			System.out.println("******* Sales Invoice: [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Sales Invoice is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}

	}

	public static void create_salesInvoice5(String documentId, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentId = "SIFIFO" + documentId;
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			// clicked on main icon

			Utilities.HoverandClick(pro.getProperty("SalesInvoiceIcon"), driver);
			Utilities.clickCheckBox(pro.getProperty("includeTax"), "uncheck", driver);
			selectFromNormalDropDown(customerid, pro.getProperty("Customer"), driver);
			selectFromNormalDropDown("NA", pro.getProperty("SequenceFormat"), driver);
			selectFromNormalDropDown("NET 45", pro.getProperty("Term"), driver);
			Utilities.enterTextNormalBox(documentId, pro.getProperty("DocumentNo"), driver);

			Utilities.waitandClick(pro.getProperty("SIDate"), driver);
			Utilities.waitandClick(
					"//div[@class='x-layer x-menu x-menu-plain x-date-menu' and contains(@style,'visible')]//td[@title='Today']/preceding::td[3]",
					driver);

			Utilities.HoverandClick(pro.getProperty("AddButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("ProductSearch"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int i = driver
					.findElements(By
							.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();

			for (int j = 1; j <= i; j++) {
				String header = driver.findElement(By
						.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ j + "]/div"))
						.getText();

				if (header.equals("Quantity")) {
					Utilities.clickAndEnterText("1",
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
									+ j + "]/div",
							driver);
					Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
					Utilities.justHover(
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]//div[@class='pwnd delete-gridrow']",
							driver);

				} else if ((header.equals("Unit Price"))) {
					Utilities.clickAndEnterText("100",
							"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
									+ j + "]/div",
							driver);
				}
			}

			// move ficus frm Line label
			Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			// String subjectLine="Sales Invoice - testsmoke - "+documentId;
			// EmailFunctionality.email_AfterSave(documentId,subjectLine,driver);

			Utilities.HoverandClick(pro.getProperty("CloseSalesInvoice"), driver);

			System.out.println("******* Sales Invoice: [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Sales Invoice is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}

	}

	/********************************** ReceivePayment ****************************************/

	public static void CreateReceivePayment(WebDriver driver, String documentId, String customerid,
			String PaymentAmount, String ButtonName) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReceivePaymentAgainstCustomerCheck"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.clickAndEnterText("RP" + documentId, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect(customerid, pro.getProperty("customerID"), driver);

			Utilities.clickAndEnterText(PaymentAmount, pro.getProperty("PaymentAmount"), driver);

			Utilities.clickButton(ButtonName, 2000, driver);
			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}
			} catch (Exception Ex) {

			}
			Utilities.clickButton("OK", 2000, driver);
			Utilities.waitandClick(pro.getProperty("CloseMakePayment"), driver);
			System.out.println("******* Receive Payment : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception Ex) {

			System.out.println("******* Receive Payment : [" + documentId + "] is not Created !!!!!! ********");

			Utilities.handleError(Ex, driver);
		}

	}

	public static void CreateReceivePayment2(WebDriver driver, String documentId, String customerid,
			String PaymentAmount, String ButtonName) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReceivePaymentAgainstCustomerCheck"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.clickAndEnterText("RP" + documentId, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect(customerid, pro.getProperty("customerID"), driver);

			Utilities.clickAndEnterText(PaymentAmount, pro.getProperty("PaymentAmount"), driver);

			Utilities.clickButton(ButtonName, 2000, driver);
			Utilities.waitandClick(
					"//*[@id='centerpaninvoicemonthwisewindow']//*[@class='x-grid3-scroller']//tr//*[contains(text(),'100')]",
					driver);
			Utilities.clickButton("Submit", 2000, driver);
			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}
			} catch (Exception Ex) {

			}
			Utilities.clickButton("OK", 2000, driver);
			Utilities.waitandClick(pro.getProperty("CloseMakePayment"), driver);
			System.out.println("******* Receive Payment : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception Ex) {

			System.out.println("******* Receive Payment : [" + documentId + "] is not Created !!!!!! ********");

			Utilities.handleError(Ex, driver);
		}

	}

	public static void CreateReceivePayment3(WebDriver driver, String documentId, String customerid,
			String PaymentAmount, String ButtonName) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReceivePaymentAgainstCustomerCheck"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.clickAndEnterText("RP" + documentId, pro.getProperty("DocumentNo"), driver);
			Utilities.enterTextandSelect(customerid, pro.getProperty("customerID"), driver);

			Utilities.clickAndEnterText(PaymentAmount, pro.getProperty("PaymentAmount"), driver);

			Utilities.clickButton(ButtonName, 2000, driver);
			Utilities.waitandClick(
					"//*[@id='centerpaninvoicemonthwisewindow']//*[@class='x-grid3-scroller']//tr//*[contains(text(),'100')]",
					driver);
			Utilities.waitandClick(
					"//*[@id='centerpaninvoicemonthwisewindow']//*[@class='x-grid3-scroller']//tr//*[contains(text(),'200')]",
					driver);

			Utilities.clickButton("Submit", 2000, driver);
			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}
			} catch (Exception Ex) {

			}
			Utilities.clickButton("OK", 2000, driver);
			Utilities.waitandClick(pro.getProperty("CloseMakePayment"), driver);
			System.out.println("******* Receive Payment : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception Ex) {

			System.out.println("******* Receive Payment : [" + documentId + "] is not Created !!!!!! ********");

			Utilities.handleError(Ex, driver);
		}

	}

	/******************************* VerificationReceivePayment **************************************/

	public static void VerificationLIFO_FIFO_MonthwiseRP(WebDriver driver, String documentId, String PIDocumentNO,
			String Text) throws InterruptedException, AWTException, IOException {

		try {
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentReport"), driver);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
					".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td[1]/div")));
			Utilities.clickAndEnterText(documentId, pro.getProperty("QuickSearch"), driver);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
					".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td[1]/div")));

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Receipt No.")) {
					driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div/a"))
							.click();
				}
			}
			Thread.sleep(3000);

			int f = driver
					.findElements(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();

			String DocumentNo = "";

			for (int k = 1; k < f + 1; k++) {
				String header = driver.findElement(By
						.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ k + "]/div"))
						.getText();

				if (header.equals("Document Number")) {
					DocumentNo = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				}
			}

			if (DocumentNo.equals(PIDocumentNO)) {
				System.out.println("RP " + Text + " is Created  ");
			} else {
				System.out.println("RP " + Text + " is linked ");

			}
			Utilities.waitandClick(pro.getProperty("CloseViewRP"), driver);
			Utilities.waitandClick(pro.getProperty("ClosePaymentMadeReport"), driver);
			System.out.println("Sucessfully Verified LIFO/FIFO/Monthwise RP ");
		} catch (Exception Ex) {
			System.out.println("Unable to Verify LIFO/FIFO/Monthwise RP ");
			Utilities.handleError(Ex, driver);
		}
	}

	/******************************* VerificationMakePaymentMultiple **************************************/

	public static void VerificationLIFO_FIFO_MonthwiseMulti(WebDriver driver, String documentId, String PIDocumentNO,
			String PIDocumentNO2, String PIDocumentNO3, String PIDocumentNO4, String Text)
			throws InterruptedException, AWTException, IOException {

		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("PaymentMadeReport"), driver);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
					".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td[1]/div")));
			Utilities.clickAndEnterText(documentId, pro.getProperty("QuickSearch"), driver);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
					".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td[1]/div")));

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Payment No")) {
					driver.findElement(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div/a"))
							.click();
				}
			}

			Thread.sleep(3000);
			int f = driver
					.findElements(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();

			String DocumentNo = "";
			String DocumentNo02 = "";
			String DocumentNo03 = "";
			String DocumentNo04 = "";
			String Documenttype = "";
			String AdvanceAmount = "";
			System.out.println(AdvanceAmount);

			for (int k = 1; k < f + 1; k++) {
				String header = driver.findElement(By
						.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ k + "]/div"))
						.getText();

				if (header.equals("Document Number")) {
					DocumentNo = driver.findElement(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
					DocumentNo02 = driver.findElement(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[2]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
					DocumentNo03 = driver.findElement(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[3]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
					DocumentNo04 = driver.findElement(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[4]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();

				} else if (header.equals("Document Number")) {
					Documenttype = driver.findElement(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[5]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				} else if (header.equals("Enter Amount")) {
					AdvanceAmount = driver.findElement(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[5]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				}

			}

			if (DocumentNo.equals(PIDocumentNO)) {
				System.out.println("MP " + PIDocumentNO + " is at first row  ");
			} else {
				System.out.println("MP " + PIDocumentNO + " is sequence is Missing ");

			}
			if (DocumentNo02.equals(PIDocumentNO2)) {
				System.out.println("MP " + PIDocumentNO2 + " is at Second row  ");
			} else {
				System.out.println("MP " + PIDocumentNO2 + " is sequence is Missing ");
			}
			if (DocumentNo03.equals(PIDocumentNO3)) {
				System.out.println("MP " + PIDocumentNO3 + " is at Third row  ");
			} else {
				System.out.println("MP " + PIDocumentNO3 + " is sequence is Missing ");
			}
			if (DocumentNo04.equals(PIDocumentNO4)) {
				System.out.println("MP " + PIDocumentNO4 + " is at Fourth row  ");
			} else {
				System.out.println("MP " + PIDocumentNO4 + " is sequence is Missing ");
			}
			if (AdvanceAmount.equals("100.00")) {
				System.out.println("MP Extra amount " + AdvanceAmount + " is adjusted in " + Documenttype
						+ " in fifth row ....!!!  ");

			} else {
				System.out.println(
						"MP Extra amount " + AdvanceAmount + " is not  adjusted in " + Documenttype + " ....!!!  ");

			}

			Utilities.waitandClick(pro.getProperty("CloseViewMP"), driver);
			Utilities.waitandClick(pro.getProperty("ClosePaymentMadeReport"), driver);
			System.out.println("Sucessfully Verified LIFO/FIFO/Monthwise MP with sequence of PI ");
		} catch (Exception Ex) {
			System.out.println("Unable to Verify LIFO/FIFO/Monthwise MP ");
			Utilities.handleError(Ex, driver);
		}
	}

	public static void VerificationLIFO_FIFO_MonthwiseMulti3(WebDriver driver, String documentId, String PIDocumentNO,
			String PIDocumentNO2, String Text) throws InterruptedException, AWTException, IOException {

		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("PaymentMadeReport"), driver);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
					".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td[1]/div")));
			Utilities.clickAndEnterText(documentId, pro.getProperty("QuickSearch"), driver);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
					".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td[1]/div")));

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Payment No")) {
					driver.findElement(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div/a"))
							.click();
				}
			}
			Thread.sleep(3000);

			int f = driver
					.findElements(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();

			String DocumentNo = "";
			String DocumentNo02 = "";

			String Documenttype = "";
			String AdvanceAmount = "";
			System.out.println(AdvanceAmount);

			for (int k = 1; k < f + 1; k++) {
				String header = driver.findElement(By
						.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ k + "]/div"))
						.getText();

				if (header.equals("Document Number")) {
					DocumentNo = driver.findElement(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
					DocumentNo02 = driver.findElement(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[2]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();

				} else if (header.equals("Document Number")) {
					Documenttype = driver.findElement(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[3]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				} else if (header.equals("Enter Amount")) {
					AdvanceAmount = driver.findElement(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[3]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				}

			}

			if (DocumentNo.equals(PIDocumentNO)) {
				System.out.println("MP " + PIDocumentNO + " is at first row  ");
			} else {
				System.out.println("MP " + PIDocumentNO + " is sequence is Missing ");

			}
			if (DocumentNo02.equals(PIDocumentNO2)) {
				System.out.println("MP " + PIDocumentNO2 + " is at Second row  ");
			} else {
				System.out.println("MP " + PIDocumentNO2 + " is sequence is Missing ");
			}

			if (AdvanceAmount.equals("100.00")) {
				System.out.println("MP Extra amount " + AdvanceAmount + " is adjusted in " + Documenttype
						+ " in fifth row ....!!!  ");

			} else {
				System.out.println(
						"MP Extra amount " + AdvanceAmount + " is not  adjusted in " + Documenttype + " ....!!!  ");

			}

			Utilities.waitandClick(pro.getProperty("CloseViewMP"), driver);
			Utilities.waitandClick(pro.getProperty("ClosePaymentMadeReport"), driver);
			System.out.println("Sucessfully Verified LIFO/FIFO/Monthwise MP with sequence of PI ");
		} catch (Exception Ex) {
			System.out.println("Unable to Verify LIFO/FIFO/Monthwise MP ");
			Utilities.handleError(Ex, driver);
		}
	}

	public static void VerificationLIFO_FIFO_MonthwiseMulti2(WebDriver driver, String documentId, String PIDocumentNO,
			String PIDocumentNO2, String PIDocumentNO3, String PIDocumentNO4, String Text)
			throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentReport"), driver);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
					".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td[1]/div")));
			Utilities.clickAndEnterText(documentId, pro.getProperty("QuickSearch"), driver);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
					".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td[1]/div")));

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();

			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Receipt No.")) {
					driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div/a"))
							.click();
				}
			}
			Thread.sleep(3000);

			int f = driver
					.findElements(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();

			String DocumentNo = "";
			String DocumentNo02 = "";
			String DocumentNo03 = "";
			String DocumentNo04 = "";
			String Documenttype = "";
			String AdvanceAmount = "";
			System.out.println(AdvanceAmount);

			for (int k = 1; k < f + 1; k++) {
				String header = driver.findElement(By
						.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ k + "]/div"))
						.getText();

				if (header.equals("Document Number")) {
					DocumentNo = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
					DocumentNo02 = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[2]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
					DocumentNo03 = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[3]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
					DocumentNo04 = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[4]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();

				} else if (header.equals("Document Number")) {
					Documenttype = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[5]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				} else if (header.equals("Enter Amount")) {
					AdvanceAmount = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[5]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				}

			}

			if (DocumentNo.equals(PIDocumentNO)) {
				System.out.println("RP " + PIDocumentNO + " is at first row  ");
			} else {
				System.out.println("RP " + PIDocumentNO + " is sequence is Missing ");

			}
			if (DocumentNo02.equals(PIDocumentNO2)) {
				System.out.println("RP " + PIDocumentNO2 + " is at Second row  ");
			} else {
				System.out.println("RP " + PIDocumentNO2 + " is sequence is Missing ");
			}
			if (DocumentNo03.equals(PIDocumentNO3)) {
				System.out.println("RP " + PIDocumentNO3 + " is at third row  ");
			} else {
				System.out.println("RP " + PIDocumentNO3 + " is sequence is Missing ");
			}
			if (DocumentNo04.equals(PIDocumentNO4)) {
				System.out.println("RP " + PIDocumentNO4 + " is at fourth row  ");
			} else {
				System.out.println("RP " + PIDocumentNO4 + " is sequence is Missing ");
			}
			if (AdvanceAmount.equals("100.00")) {
				System.out.println("RP Extra amount " + AdvanceAmount + " is adjusted in " + Documenttype
						+ " in fifth row ....!!!  ");

			} else {
				System.out.println("RP Extra amount " + AdvanceAmount + " is not  adjusted in " + Documenttype
						+ " in fifth row  ....!!!  ");

			}

			Utilities.waitandClick(pro.getProperty("CloseViewRP"), driver);
			Utilities.waitandClick(pro.getProperty("ClosePaymentMadeReport"), driver);
			System.out.println("Sucessfully Verified LIFO/FIFO/Monthwise RP with sequence of SI ");
		} catch (Exception Ex) {
			System.out.println("Unable to Verify LIFO/FIFO/Monthwise RP ");
			Utilities.handleError(Ex, driver);
		}
	}

	public static void VerificationLIFO_FIFO_MonthwiseMulti4(WebDriver driver, String documentId, String PIDocumentNO,
			String PIDocumentNO2, String Text) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentReport"), driver);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
					".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td[1]/div")));
			Utilities.clickAndEnterText(documentId, pro.getProperty("QuickSearch"), driver);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
					".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td[1]/div")));

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();

			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Receipt No.")) {
					driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div/a"))
							.click();
				}
			}

			Thread.sleep(3000);
			int f = driver
					.findElements(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();

			String DocumentNo = "";
			String DocumentNo02 = "";

			String Documenttype = "";
			String AdvanceAmount = "";
			System.out.println(AdvanceAmount);

			for (int k = 1; k < f + 1; k++) {
				String header = driver.findElement(By
						.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ k + "]/div"))
						.getText();

				if (header.equals("Document Number")) {
					DocumentNo = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
					DocumentNo02 = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[2]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();

				} else if (header.equals("Document Number")) {
					Documenttype = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[3]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				} else if (header.equals("Enter Amount")) {
					AdvanceAmount = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[3]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				}

			}

			if (DocumentNo.equals(PIDocumentNO)) {
				System.out.println("RP " + PIDocumentNO + " is at first row  ");
			} else {
				System.out.println("RP " + PIDocumentNO + " is sequence is Missing ");

			}
			if (DocumentNo02.equals(PIDocumentNO2)) {
				System.out.println("RP " + PIDocumentNO2 + " is at Second row  ");
			} else {
				System.out.println("RP " + PIDocumentNO2 + " is sequence is Missing ");
			}

			if (AdvanceAmount.equals("100.00")) {
				System.out.println("RP Extra amount " + AdvanceAmount + " is adjusted in " + Documenttype
						+ " in fifth row ....!!!  ");

			} else {
				System.out.println("RP Extra amount " + AdvanceAmount + " is not  adjusted in " + Documenttype
						+ " in fifth row  ....!!!  ");

			}

			Utilities.waitandClick(pro.getProperty("CloseViewRP"), driver);
			Utilities.waitandClick(pro.getProperty("ClosePaymentMadeReport"), driver);
			System.out.println("Sucessfully Verified LIFO/FIFO/Monthwise RP with sequence of SI ");
		} catch (Exception Ex) {
			System.out.println("Unable to Verify LIFO/FIFO/Monthwise RP ");
			Utilities.handleError(Ex, driver);
		}
	}

	// select item from NormalDrop Down
	public static void selectFromNormalDropDown(String text, String expForname, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		WebElement enterText = null;
		boolean success = false;
		for (int num_try = 0; !success && num_try < 10; num_try++) {
			try {
				enterText = driver.findElement(By.xpath(expForname));
				enterText.clear();
				enterText.sendKeys(text);
				Thread.sleep(1000);
				WebElement element = driver.findElement(
						By.xpath("//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]//*[text()='"
								+ text + "']"));
				element.click();
				Thread.sleep(1500);
				success = true;
			} catch (Exception EX) {
				System.out.println(num_try);
				if (num_try == 9) {
					Utilities.handleError(EX, driver);
				}
			}
		}
	}

}
