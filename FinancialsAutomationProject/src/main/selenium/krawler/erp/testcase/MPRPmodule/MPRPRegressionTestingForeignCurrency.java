package krawler.erp.testcase.MPRPmodule;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import krawler.erp.BankReconcilation.CreatePaymethodandCOA;
import krawler.erp.BankReconcilation.DocumentCreationForBR;
import krawler.erp.modules.PurchaseInvoice1;
import krawler.erp.modules.SalesInvoice1;
import krawler.erp.page.CreditNoteOtherwise;
import krawler.erp.page.CreditNote_Vendor;
import krawler.erp.page.DebitNoteAgainstCustomer;
import krawler.erp.page.DebitNoteOtherWise;
import krawler.erp.page.Login;
import krawler.erp.page.MakePaymentAgainstCustomer;
import krawler.erp.page.MakePaymentAgainstGL;
import krawler.erp.page.MakePaymentAginstVendor;
import krawler.erp.page.ReceivePaymentAgainstCustomer;
import krawler.erp.page.ReceivePaymentAgainstGL;
import krawler.erp.page.ReceivePaymentAgainstVendor;
import krawler.erp.page.Utilities;

public class MPRPRegressionTestingForeignCurrency {
	public static WebDriver driver;
	public static String server1 = "https://in5.accounting.deskera.com/a/sg12/#";
	public static String username = "sunita";
	public static String password = "1234";
	public static String documentId = "Nsub25f";
	public static String documentid = documentId;
	public static String documentID = documentId;
	public static String vendorid = "Sumittest";
	public static String productid = "Guitar";
	public static String customerid = "Sumittest";
	public static String BankName = "USD Method";
	public static String DocumentCurrency = "US Dollars (USD)";
	public static String ForexCurrecny1 = "US Dollars (USD)";

	@BeforeTest
	public void beforeTestLogin() throws InterruptedException, IOException {
		Login ln = new Login();
		WebDriver driver = Login.loginERP(server1, username, password, 10);
		this.driver = driver;
		Utilities.clickExpander(driver);
	}

	@Test(priority = 1)
	public void createCOA1() throws InterruptedException, AWTException, IOException {
		DocumentCreationForBR.create_ChartOfAccount(documentID, productid, customerid, driver);
	}

	@Test(priority = 2)
	public void createCOA2() throws InterruptedException, AWTException, IOException {
		DocumentCreationForBR.create_ChartOfAccount(documentID + "-2", productid, customerid, driver);
	}

	@Test(priority = 3)
	public void PIForMP() throws InterruptedException, AWTException, IOException {
		PurchaseInvoice1.create_PurchaseInvoice(documentId, productid, vendorid, driver);
	}

	@Test(priority = 4)
	public void PIForMPGL() throws InterruptedException, AWTException, IOException {
		PurchaseInvoice1.createGL_PurchaseInvoice(documentId, productid, vendorid, driver);
	}

	@Test(priority = 5)
	public void CNFORMP() throws InterruptedException, AWTException, IOException {
		CreditNote_Vendor.create_creditNoteAgainstvendor(documentId, productid, vendorid, driver);
	}

	@Test(priority = 6)
	public void CNFORMP2() throws InterruptedException, AWTException, IOException {
		CreditNote_Vendor.create_creditNoteAgainstvendor(documentId + "-2", productid, vendorid, driver);
	}

	@Test(priority = 7)
	public void MPwithMultipleDoc() throws InterruptedException, AWTException, IOException {
		MakePaymentAginstVendor.create_MakePayment_Vendor2(documentId, "10", "10", "10", "10", productid, vendorid,
				driver, BankName, DocumentCurrency, ForexCurrecny1);
	}

	@Test(priority = 8)
	public void CNCustMP() throws InterruptedException, AWTException, IOException {
		CreditNoteOtherwise.create_creditNoteotherwise(documentId, productid, customerid, driver);
	}

	@Test(priority = 9)
	public void CNCustMP_2() throws InterruptedException, AWTException, IOException {
		CreditNoteOtherwise.create_creditNoteotherwise(documentId + "-2", productid, customerid, driver);
	}

	@Test(priority = 10)
	public void RPCustFORmpCust() throws InterruptedException, AWTException, IOException {
		DocumentCreationForBR.create_ReceivePaymentAgainstCustomer(documentId, "100", "InvoiceAmt", "CreditNoteAmt",
				"GLAmt", "CheBankname", customerid, driver, "Cash", "SG Dollar (SGD)", "US Dollars (USD)");
	}

	@Test(priority = 11)
	public void RPCustFORmpCust2() throws InterruptedException, AWTException, IOException {
		DocumentCreationForBR.create_ReceivePaymentAgainstCustomer(documentId + "2", "100", "InvoiceAmt",
				"CreditNoteAmt", "GLAmt", "CheBankname", customerid, driver, "Cash", "SG Dollar (SGD)",
				"US Dollars (USD)");
	}

	@Test(priority = 12)
	public void MPCustWithMultpleForexDoc() throws InterruptedException, AWTException, IOException {
		MakePaymentAgainstCustomer.MakePayment_CustomerWithMutilple(documentid, customerid, "10", "10", driver,
				BankName, DocumentCurrency, ForexCurrecny1);
	}

	@Test(priority = 13)
	public void MPGLWithMultpleForexAcc() throws InterruptedException, AWTException, IOException {
		MakePaymentAgainstGL.create_makePayment_MutilpleGL(documentId, productid, "Utilities", driver, BankName,
				DocumentCurrency, ForexCurrecny1);
	}

	@Test(priority = 14)
	public void SIforRP() throws InterruptedException, AWTException, IOException {
		SalesInvoice1.create_salesInvoice(documentId, productid, customerid, driver);
	}

	@Test(priority = 15)
	public void SI2forRP() throws InterruptedException, AWTException, IOException {
		SalesInvoice1.create_salesInvoice(documentId + "-2", productid, customerid, driver);
	}

	@Test(priority = 16)
	public void DNCustforRP() throws InterruptedException, AWTException, IOException {
		DebitNoteAgainstCustomer.create_debitNoteAgainstCustomer(documentId, productid, customerid, driver);
	}

	@Test(priority = 17)
	public void DNCust2forRP() throws InterruptedException, AWTException, IOException {
		DebitNoteAgainstCustomer.create_debitNoteAgainstCustomer(documentId + "-2", productid, customerid, driver);
	}

	@Test(priority = 18)
	public void RPCustWithMultipleDoc() throws InterruptedException, AWTException, IOException {
		ReceivePaymentAgainstCustomer.create_ReceivePaymentAgainstCustomerMultiple(documentId, "10", "10", "10", "10",
				productid, customerid, driver, BankName, DocumentCurrency, ForexCurrecny1);
	}

	@Test(priority = 19)
	public void DNForRPVen() throws InterruptedException, AWTException, IOException {
		DebitNoteOtherWise.create_debitNoteOtherwise(documentId, productid, vendorid, driver);
	}

	@Test(priority = 20)
	public void DN2ForRPVen() throws InterruptedException, AWTException, IOException {
		DebitNoteOtherWise.create_debitNoteOtherwise(documentId + "-2", productid, vendorid, driver);
	}

	@Test(priority = 21)
	public void MPVenAd1() throws InterruptedException, AWTException, IOException {
		DocumentCreationForBR.Create_Makepayment(driver, documentId, "Cash", vendorid, "SG Dollar (SGD)",
				"US Dollars (USD)");

	}

	@Test(priority = 22)
	public void MPVenAd2() throws InterruptedException, AWTException, IOException {
		DocumentCreationForBR.Create_Makepayment(driver, documentId + "2", "Cash", vendorid, "SG Dollar (SGD)",
				"US Dollars (USD)");

	}

	@Test(priority = 23)
	public void RPVenWithMultiple() throws InterruptedException, AWTException, IOException {
		ReceivePaymentAgainstVendor.create_ReceivePayment_VendorMulti(documentId, productid, "10", vendorid, "10", "10",
				driver, BankName, DocumentCurrency, ForexCurrecny1);
	}

	@Test(priority = 24)
	public void RPGLwithMult() throws InterruptedException, AWTException, IOException {
		ReceivePaymentAgainstGL.create_ReceivePayment_AgainstGLMul(documentId, "Utilities", driver, BankName,
				DocumentCurrency, ForexCurrecny1);
	}

	@AfterClass
	public void quit() {
		driver.close();
	}

}
