package krawler.erp.testcase.MPRP_BankReconcilationmodule;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import krawler.erp.BankReconcilation.CreatePaymethodandCOA;
import krawler.erp.MP_RPModule.LIFO_FIFO_Monthwise_MP_RP;
import krawler.erp.page.CustomerMaster;
import krawler.erp.page.Login;
import krawler.erp.page.Utilities;
import krawler.erp.page.VendorMaster;

public class MPRP_LIFO_FIFO {
	public static WebDriver driver;
	public static String server1 = "https://in5.accounting.deskera.com/a/sg12/";
	public static String username = "Sunita";
	public static String password = "1234";
	public static String documentId = "F11";
	public static String vendorid = "Automation11";
	public static String customerid = "Automation11";
	public static String productid = "prodFeb08";
	public static String DocumentCurrency = "SG Dollar (SGD)";

	@BeforeTest
	public void beforeTestLogin() throws InterruptedException, IOException {
		Login ln = new Login();
		WebDriver driver = Login.loginERP(server1, username, password, 10);
		this.driver = driver;
		Utilities.clickExpander(driver);
	}

	@BeforeMethod(alwaysRun = true)
	public void closeExpander() throws InterruptedException, AWTException, IOException {
		Utilities.disableExpander(driver);
	}

	@Test(priority = 1)
	public void Create_VendorMaster() throws InterruptedException, IOException, AWTException {
		VendorMaster.create_Vendor(vendorid, driver);
	}

	@Test(priority = 2)
	public void createPI() throws InterruptedException, AWTException, IOException {
		LIFO_FIFO_Monthwise_MP_RP.PurchaseInvoice1(driver, documentId, vendorid, productid, DocumentCurrency);
		LIFO_FIFO_Monthwise_MP_RP.PurchaseInvoice2(driver, documentId, vendorid, productid, DocumentCurrency);
		LIFO_FIFO_Monthwise_MP_RP.PurchaseInvoice3(driver, documentId, vendorid, productid, DocumentCurrency);
	}

	@Test(priority = 3)
	public void CreateMP() throws InterruptedException, IOException, AWTException {
		LIFO_FIFO_Monthwise_MP_RP.CreateMAkePayment(driver, documentId + "FIFO", vendorid, "100", "Invoice Date FIFO");
		LIFO_FIFO_Monthwise_MP_RP.CreateMAkePayment(driver, documentId + "LIFO", vendorid, "100", "Invoice Date LIFO");
		LIFO_FIFO_Monthwise_MP_RP.CreateMAkePayment2(driver, documentId + "Monthwise", vendorid, "100",
				"Invoice Month-wise");

	}

	@Test(priority = 4)
	public void Verifylifo_Fifo_monthwise_MP() throws InterruptedException, AWTException, IOException {
		LIFO_FIFO_Monthwise_MP_RP.VerificationLIFO_FIFO_Monthwise(driver, documentId + "FIFO", "PIFIFO" + documentId,
				"is successfully linked to FIFO PI");
		LIFO_FIFO_Monthwise_MP_RP.VerificationLIFO_FIFO_Monthwise(driver, documentId + "LIFO", "PILIFO" + documentId,
				"is successfully linked to LIFO PI");
		LIFO_FIFO_Monthwise_MP_RP.VerificationLIFO_FIFO_Monthwise(driver, documentId + "Monthwise",
				"PIMonth" + documentId, "is successfully linked to Monthly PI");

	}

	@Test(priority = 5)
	public void Create_CustomerMaster() throws InterruptedException, IOException, AWTException {
		CustomerMaster.create_Customer(customerid, driver);
	}

	@Test(priority = 6)
	public void createSI() throws InterruptedException, AWTException, IOException {
		LIFO_FIFO_Monthwise_MP_RP.create_salesInvoice(documentId, productid, customerid, driver);
		LIFO_FIFO_Monthwise_MP_RP.create_salesInvoice2(documentId, productid, customerid, driver);
		LIFO_FIFO_Monthwise_MP_RP.create_salesInvoice3(documentId, productid, customerid, driver);

	}

	@Test(priority = 7)
	public void CreateRP() throws InterruptedException, IOException, AWTException {
		LIFO_FIFO_Monthwise_MP_RP.CreateReceivePayment(driver, documentId + "FIFO", customerid, "100",
				"Invoice Date FIFO");
		LIFO_FIFO_Monthwise_MP_RP.CreateReceivePayment(driver, documentId + "LIFO", customerid, "100",
				"Invoice Date LIFO");
		LIFO_FIFO_Monthwise_MP_RP.CreateReceivePayment2(driver, documentId + "Monthwise", customerid, "100",
				"Invoice Month-wise");
	}

	@Test(priority = 8)
	public void Verifylifo_Fifo_monthwise_RP() throws InterruptedException, AWTException, IOException {
		LIFO_FIFO_Monthwise_MP_RP.VerificationLIFO_FIFO_MonthwiseRP(driver, documentId + "FIFO", "PIFIFO" + documentId,
				"is successfully linked to FIFO SI");
		LIFO_FIFO_Monthwise_MP_RP.VerificationLIFO_FIFO_MonthwiseRP(driver, documentId + "LIFO", "PILIFO" + documentId,
				"is successfully linked to LIFO SI");
		LIFO_FIFO_Monthwise_MP_RP.VerificationLIFO_FIFO_MonthwiseRP(driver, documentId + "Monthwise",
				"PIMonth" + documentId, "is successfully linked to Monthwise SI");
	}

	@Test(priority = 9)
	public void createPI2() throws InterruptedException, AWTException, IOException {
		LIFO_FIFO_Monthwise_MP_RP.PurchaseInvoice5(driver, documentId + "-1", vendorid, productid, DocumentCurrency);
		LIFO_FIFO_Monthwise_MP_RP.PurchaseInvoice1(driver, documentId + "-2", vendorid, productid, DocumentCurrency);
		LIFO_FIFO_Monthwise_MP_RP.PurchaseInvoice2(driver, documentId + "-1", vendorid, productid, DocumentCurrency);
		LIFO_FIFO_Monthwise_MP_RP.PurchaseInvoice3(driver, documentId + "-1", vendorid, productid, DocumentCurrency);

	}

	@Test(priority = 10)
	public void CreateMP2() throws InterruptedException, IOException, AWTException {
		LIFO_FIFO_Monthwise_MP_RP.CreateMAkePayment(driver, documentId + "FIFO-1", vendorid, "500",
				"Invoice Date FIFO");
	}

	@Test(priority = 11)
	public void Verifylifo_Fifo_monthwise_MP3() throws InterruptedException, AWTException, IOException {
		LIFO_FIFO_Monthwise_MP_RP.VerificationLIFO_FIFO_MonthwiseMulti(driver, "MP" + documentId + "FIFO-1",
				"PIFIFO" + documentId + "-1", "PIFIFO" + documentId + "-2", "PIMonth" + documentId + "-1",
				"PILIFO" + documentId + "-1", "");
	}

	@Test(priority = 12)
	public void createPI3() throws InterruptedException, AWTException, IOException {
		LIFO_FIFO_Monthwise_MP_RP.PurchaseInvoice5(driver, documentId + "-3", vendorid, productid, DocumentCurrency);
		LIFO_FIFO_Monthwise_MP_RP.PurchaseInvoice1(driver, documentId + "-4", vendorid, productid, DocumentCurrency);
		LIFO_FIFO_Monthwise_MP_RP.PurchaseInvoice2(driver, documentId + "-2", vendorid, productid, DocumentCurrency);
		LIFO_FIFO_Monthwise_MP_RP.PurchaseInvoice3(driver, documentId + "-2", vendorid, productid, DocumentCurrency);

	}

	@Test(priority = 13)
	public void CreateMP3() throws InterruptedException, IOException, AWTException {
		LIFO_FIFO_Monthwise_MP_RP.CreateMAkePayment(driver, documentId + "LIFO-1", vendorid, "500",
				"Invoice Date LIFO");

	}

	@Test(priority = 14)
	public void Verifylifo_Fifo_monthwise_MP2() throws InterruptedException, AWTException, IOException {
		LIFO_FIFO_Monthwise_MP_RP.VerificationLIFO_FIFO_MonthwiseMulti(driver, "MP" + documentId + "LIFO-1",
				"PILIFO" + documentId + "-2", "PIMonth" + documentId + "-2", "PIFIFO" + documentId + "-4",
				"PIFIFO" + documentId + "-3", "");
	}

	@Test(priority = 15)
	public void createSI2() throws InterruptedException, AWTException, IOException {
		LIFO_FIFO_Monthwise_MP_RP.create_salesInvoice5(documentId + "-1", productid, customerid, driver);
		LIFO_FIFO_Monthwise_MP_RP.create_salesInvoice(documentId + "-2", productid, customerid, driver);
		LIFO_FIFO_Monthwise_MP_RP.create_salesInvoice2(documentId + "-1", productid, customerid, driver);
		LIFO_FIFO_Monthwise_MP_RP.create_salesInvoice3(documentId + "-1", productid, customerid, driver);
	}

	@Test(priority = 16)
	public void CreateRP2() throws InterruptedException, IOException, AWTException {
		LIFO_FIFO_Monthwise_MP_RP.CreateReceivePayment(driver, documentId + "FIFO-1", customerid, "500",
				"Invoice Date FIFO");

	}

	@Test(priority = 17)
	public void Verifylifo_Fifo_monthwise_RP2() throws InterruptedException, AWTException, IOException {
		LIFO_FIFO_Monthwise_MP_RP.VerificationLIFO_FIFO_MonthwiseMulti2(driver, "RP" + documentId + "FIFO-1",
				"SIFIFO" + documentId + "-1", "SIFIFO" + documentId + "-2", "SIMonth" + documentId + "-1",
				"SILIFO" + documentId + "-1", "");
	}

	@Test(priority = 18)
	public void createSI3() throws InterruptedException, AWTException, IOException {
		LIFO_FIFO_Monthwise_MP_RP.create_salesInvoice5(documentId + "-3", productid, customerid, driver);
		LIFO_FIFO_Monthwise_MP_RP.create_salesInvoice(documentId + "-4", productid, customerid, driver);
		LIFO_FIFO_Monthwise_MP_RP.create_salesInvoice2(documentId + "-2", productid, customerid, driver);
		LIFO_FIFO_Monthwise_MP_RP.create_salesInvoice3(documentId + "-2", productid, customerid, driver);
	}

	@Test(priority = 19)
	public void CreateRP3() throws InterruptedException, IOException, AWTException {
		LIFO_FIFO_Monthwise_MP_RP.CreateReceivePayment(driver, documentId + "LIFO-1", customerid, "500",
				"Invoice Date LIFO");

	}

	@Test(priority = 20)
	public void Verifylifo_Fifo_monthwise_RP3() throws InterruptedException, AWTException, IOException {
		LIFO_FIFO_Monthwise_MP_RP.VerificationLIFO_FIFO_MonthwiseMulti2(driver, "RP" + documentId + "LIFO-1",
				"SILIFO" + documentId + "-2", "SIMonth" + documentId + "-2", "SIFIFO" + documentId + "-4",
				"SIFIFO" + documentId + "-3", "");
		Utilities.refresh();
	}

	@Test(priority = 21)
	public void createPI4() throws InterruptedException, AWTException, IOException {
		LIFO_FIFO_Monthwise_MP_RP.PurchaseInvoice4(driver, documentId + "-5", vendorid, productid, DocumentCurrency);
		LIFO_FIFO_Monthwise_MP_RP.PurchaseInvoice2(driver, documentId + "-3", vendorid, productid, DocumentCurrency);

	}

	@Test(priority = 22)
	public void CreateMP4() throws InterruptedException, IOException, AWTException {
		LIFO_FIFO_Monthwise_MP_RP.CreateMAkePayment3(driver, documentId + "Monthwise-1", vendorid, "500",
				"Invoice Month-wise");
	}

	@Test(priority = 23)
	public void Verifylifo_Fifo_monthwise_MP4() throws InterruptedException, AWTException, IOException {
		LIFO_FIFO_Monthwise_MP_RP.VerificationLIFO_FIFO_MonthwiseMulti3(driver, "MP" + documentId + "Monthwise-1",
				"PIFIFO" + documentId + "-5", "PILIFO" + documentId + "-3", "");
		Utilities.refresh();
	}

	@Test(priority = 24)
	public void createSI4() throws InterruptedException, AWTException, IOException {
		LIFO_FIFO_Monthwise_MP_RP.create_salesInvoice4(documentId + "-5", productid, customerid, driver);
		LIFO_FIFO_Monthwise_MP_RP.create_salesInvoice2(documentId + "-3", productid, customerid, driver);
	}

	@Test(priority = 25)
	public void CreateRP4() throws InterruptedException, IOException, AWTException {
		LIFO_FIFO_Monthwise_MP_RP.CreateReceivePayment3(driver, documentId + "Monthwise-1", customerid, "500",
				"Invoice Month-wise");
	}

	@Test(priority = 26)
	public void Verifylifo_Fifo_monthwise_RP4() throws InterruptedException, AWTException, IOException {
		LIFO_FIFO_Monthwise_MP_RP.VerificationLIFO_FIFO_MonthwiseMulti4(driver, "RP" + documentId + "Monthwise-1",
				"SIFIFO" + documentId + "-5", "SILIFO" + documentId + "-3", "");
	}

	@AfterTest
	public void close() {
		driver.close();
	}

}
