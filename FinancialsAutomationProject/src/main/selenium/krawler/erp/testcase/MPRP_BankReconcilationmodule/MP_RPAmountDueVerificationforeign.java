package krawler.erp.testcase.MPRP_BankReconcilationmodule;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import krawler.erp.BankReconcilation.CreatePaymethodandCOA;
import krawler.erp.BankReconcilation.DocumentCreationForBR;
import krawler.erp.MP_RPModule.MPDocumentCreationforAmountDue;
import krawler.erp.MP_RPModule.MPDocumentCreationforAmountDueForeign;
import krawler.erp.MP_RPModule.MP_RPAmountDue;
import krawler.erp.MP_RPModule.MP_RPAmountDueForeign;
import krawler.erp.MP_RPModule.RPDocumentCreationForAmountDue;
import krawler.erp.MP_RPModule.RPDocumentCreationForAmountDueForeign;
import krawler.erp.modules.PurchaseInvoice1;
import krawler.erp.modules.SalesInvoice1;
import krawler.erp.page.CreditNoteOtherwise;
import krawler.erp.page.CreditNote_Vendor;
import krawler.erp.page.DebitNoteAgainstCustomer;
import krawler.erp.page.Login;
import krawler.erp.page.MakePaymentAgainstGL;
import krawler.erp.page.Utilities;

public class MP_RPAmountDueVerificationforeign {

	public static WebDriver driver;
	public static String server1 = "http://192.168.0.208:8080/dotcomsmoketesting/a/rahultestv101/";
	public static String username = "admin";
	public static String password = "1234";
	public static String documentID = "AutomaF0301";
	public static String productid = "Guitar";
	public static String vendorid = "SumitTest";
	public static String vendor_ID = vendorid;
	public static String customerid = "SumitTest";
	public static String customer = "SumitTest";
	public static String documentId = documentID;
	public static String documentid = documentID;
	public static String PaymentMethod = "USD Bank";
	public static String BankName = "COA" + documentID;

	@BeforeTest
	public void beforeTestLogin() throws InterruptedException, IOException {
		Login ln = new Login();
		WebDriver driver = Login.loginERP(server1, username, password, 10);
		this.driver = driver;
		Utilities.clickExpander(driver);
	}

	@Test(priority = 1)
	public void PIForMP() throws InterruptedException, AWTException, IOException {
		PurchaseInvoice1.createGL_PurchaseInvoice(documentId, productid, vendorid, driver);
	}

	@Test(priority = 2)
	public void MP_PIcreate() {
		MPDocumentCreationforAmountDueForeign.MP_PI(documentID, vendorid, driver, PaymentMethod);
	}

	@Test(priority = 3)
	public void MP_PIAmountdue() {
		MP_RPAmountDueForeign.MPAmountDue("MPPI" + documentID, driver);
	}

	@Test(priority = 4)
	public void Createcoa() throws InterruptedException, AWTException, IOException {
		CreatePaymethodandCOA.create_ChartOfAccount(documentID, BankName, productid, customerid, driver);
	}

	@Test(priority = 5)
	public void CNFORMP() throws InterruptedException, AWTException, IOException {
		CreditNote_Vendor.create_creditNoteAgainstvendor(documentId, productid, vendorid, driver);
	}

	@Test(priority = 6)
	public void MP_CNcreate() {
		MPDocumentCreationforAmountDueForeign.MP_CN(documentID, vendorid, driver, PaymentMethod);
	}

	@Test(priority = 7)
	public void MP_PCNAmountdue() {
		MP_RPAmountDueForeign.MPAmountDue("MPCN" + documentID, driver);
	}

	@Test(priority = 8)
	public void RPCustFORmpCust() throws InterruptedException, AWTException, IOException {
		DocumentCreationForBR.create_ReceivePaymentAgainstCustomer(documentId, "100", "InvoiceAmt", "CreditNoteAmt",
				"GLAmt", "CheBankname", customerid, driver, "Cash", "SG Dollar (SGD)", "US Dollars (USD)");
	}

	@Test(priority = 9)
	public void MPCustForRE() throws InterruptedException, AWTException, IOException {
		MPDocumentCreationforAmountDueForeign.MPCust_RE(documentID, vendorid, driver, PaymentMethod);

	}

	@Test(priority = 10)
	public void MPRECUSMPAmountDue() {
		MP_RPAmountDueForeign.MPAmountDue("MPCusRE" + documentID, driver);
	}

	@Test(priority = 11)
	public void CustomerCN() throws InterruptedException, AWTException, IOException {
		CreditNoteOtherwise.create_creditNoteotherwise(documentId, productid, customerid, driver);
	}

	@Test(priority = 12)
	public void MPCustForCN() throws InterruptedException, AWTException, IOException {
		MPDocumentCreationforAmountDueForeign.MPCust_CN(documentID, vendorid, driver, PaymentMethod);

	}

	@Test(priority = 13)
	public void MPCNCUSMPAmountDue() {
		MP_RPAmountDueForeign.MPAmountDue2("MPCusCN" + documentID, driver);
	}

	@Test(priority = 14)
	public void MPGLWithMultpleAcc() throws InterruptedException, AWTException, IOException {
		MakePaymentAgainstGL.create_makePayment_MutilpleGL(documentId, productid, "Utilities", driver, PaymentMethod,
				"US Dollars (USD)", "SG Dollar (SGD)");
	}

	@Test(priority = 15)
	public void MPGLAmountDue() {
		MP_RPAmountDueForeign.AmountDueGL("MPaGL" + documentID, driver);
	}

	@Test(priority = 16)
	public void MPVenAd2() throws InterruptedException, AWTException, IOException {
		DocumentCreationForBR.Create_Makepayment(driver, documentId, PaymentMethod, vendorid, "US Dollars (USD)",
				"SG Dollar (SGD)");

	}

	@Test(priority = 17)
	public void MPAdAmountDue() {
		MP_RPAmountDueForeign.AmountDueAdvance("MPVenAd" + documentID, driver);
	}

	@Test(priority = 18)
	public void CreateRPAdvance() {
		RPDocumentCreationForAmountDueForeign.RP_Advance(driver, documentID, customerid, "100", PaymentMethod);
	}

	@Test(priority = 19)
	public void RPAdAmountDue() {
		MP_RPAmountDueForeign.AmountDueRPAdvance("RPAD" + documentID, driver);
	}

	@Test(priority = 20)
	public void SIIForRP() throws InterruptedException, AWTException, IOException {
		SalesInvoice1.create_salesInvoice(documentID, productid, customerid, driver);
	}

	@Test(priority = 21)
	public void RP_SIcreate() {
		RPDocumentCreationForAmountDueForeign.RP_Invoice(driver, documentID, vendorid, PaymentMethod);
	}

	@Test(priority = 22)
	public void RP_SIAmountdue() {
		MP_RPAmountDueForeign.RPAmountDue("RPPI" + documentID, driver);
	}

	@Test(priority = 23)
	public void DNFORRP() throws InterruptedException, AWTException, IOException {
		DebitNoteAgainstCustomer.create_debitNoteAgainstCustomer(documentid, productid, customerid, driver);
	}

	@Test(priority = 24)
	public void RP_DNcreate() {
		RPDocumentCreationForAmountDueForeign.RP_DebitNote(driver, documentID, vendorid, PaymentMethod);
	}

	@Test(priority = 25)
	public void RP_DNAmountdue() {
		MP_RPAmountDueForeign.RPAmountDue("RPDN" + documentID, driver);
	}

	@Test(priority = 26)
	public void MPAdFORRPVen() throws InterruptedException, AWTException, IOException {
		DocumentCreationForBR.Create_Makepayment(driver, documentId + "-2", "Cash", vendor_ID, "SG Dollar (SGD)",
				"US Dollars (USD)");
	}

	@Test(priority = 27)
	public void RPVenForRE() throws InterruptedException, AWTException, IOException {
		RPDocumentCreationForAmountDueForeign.RPCust_RE(documentID, vendorid, driver, PaymentMethod);
	}

	@Test(priority = 28)
	public void RP_REAmountdue() {
		MP_RPAmountDueForeign.RPAmountDue("RPCusRE" + documentID, driver);
	}

	@Test(priority = 29)
	public void RPVenForDE() throws InterruptedException, AWTException, IOException {
		RPDocumentCreationForAmountDueForeign.RPCust_DE(documentID, vendorid, driver, PaymentMethod);
	}

	@Test(priority = 30)
	public void RP_DEAmountdue() {
		MP_RPAmountDueForeign.AmountDueRPVenDE("RPCusDE" + documentID, driver);
	}

	@Test(priority = 31)
	public void RPVenForGL() throws InterruptedException, AWTException, IOException {
		RPDocumentCreationForAmountDueForeign.RPCust_GL(documentID, vendorid, driver, BankName, PaymentMethod);
	}

	@Test(priority = 32)
	public void RP_GLAmountdue() {
		MP_RPAmountDueForeign.RPAmountDueGL("RPCusGL" + documentID, driver);
	}

	@AfterTest
	public void close() {
		driver.close();
	}
}
