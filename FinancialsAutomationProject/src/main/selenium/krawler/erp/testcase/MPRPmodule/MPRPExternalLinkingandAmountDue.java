package krawler.erp.testcase.MPRPmodule;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import krawler.erp.BankReconcilation.CreatePaymethodandCOA;
import krawler.erp.BankReconcilation.DocumentCreationForBR;
import krawler.erp.MP_RPModule.ExternalLinkingMP_RP_WithDocuments;
import krawler.erp.MP_RPModule.MPDocumentCreationforAmountDue;
import krawler.erp.MP_RPModule.MP_RPAmountDueEx;
import krawler.erp.MP_RPModule.RPDocumentCreationForAmountDue;
import krawler.erp.SmokeTestSuiteNoDataEntry.ALLReportsLoading;
import krawler.erp.SmokeTestSuiteNoDataEntry.AllFormLoading;
import krawler.erp.modules.PurchaseInvoice1;
import krawler.erp.modules.SalesInvoice1;
import krawler.erp.page.CreditNote_Vendor;
import krawler.erp.page.DebitNoteAgainstCustomer;
import krawler.erp.page.Login;
import krawler.erp.page.Utilities;

public class MPRPExternalLinkingandAmountDue {

	public static WebDriver driver;
	public static String server1 = "http://192.168.0.208:8080/dotcomsmoketestingv21/a/rahulv21/#";
	public static String username = "admin";
	public static String password = "1234";
	public static String documentID = "DecJ2101";
	public static String productid = "Test Product";
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

	@Test(priority = 02)
	public void MPVenAd() throws InterruptedException, AWTException, IOException {
		DocumentCreationForBR.Create_Makepayment(driver, documentId, "Cash", vendorid, "SG Dollar (SGD)",
				"US Dollars (USD)");

	}

	@Test(priority = 03)
	public void MPandPIExternallinking() throws InterruptedException, AWTException, IOException {
		ExternalLinkingMP_RP_WithDocuments.MP_PI_CN_RPAdExternalLinking(driver, "MPVenAd" + documentID,
				"PIGL" + documentID, "200");
	}

	@Test(priority = 04)
	public void MPandPIAmountDue() throws InterruptedException, AWTException, IOException {
		MP_RPAmountDueEx.MP_PIEXlinkAmountDueVeri(driver, "MPVenAd" + documentId, "PIGL" + documentID, "200");
	}

	@Test(priority = 5)
	public void Createcoa() throws InterruptedException, AWTException, IOException {
		CreatePaymethodandCOA.create_ChartOfAccount(documentID, BankName, productid, customerid, driver);
	}

	@Test(priority = 6)
	public void CNFORMP() throws InterruptedException, AWTException, IOException {
		CreditNote_Vendor.create_creditNoteAgainstvendor(documentId, productid, vendorid, driver);
	}

	@Test(priority = 7)
	public void MPVenAd2() throws InterruptedException, AWTException, IOException {
		DocumentCreationForBR.Create_Makepayment(driver, documentId + "-2", "Cash", vendorid, "SG Dollar (SGD)",
				"US Dollars (USD)");

	}

	@Test(priority = 8)
	public void MPandCNExternallinking() throws InterruptedException, AWTException, IOException {
		ExternalLinkingMP_RP_WithDocuments.MP_PI_CN_RPAdExternalLinking(driver, "MPVenAd" + documentID + "-2",
				"CrVen" + documentID, "50");
	}

	@Test(priority = 9)
	public void MPandCNVenAmountDue() throws InterruptedException, AWTException, IOException {
		MP_RPAmountDueEx.MP_CNVenEXlinkAmountDueVeri(driver, "MPVenAd" + documentId + "-2", "CrVen" + documentID, "50");
	}

	@Test(priority = 10)
	public void RPCustAdvance() throws InterruptedException, AWTException, IOException {
		DocumentCreationForBR.create_ReceivePaymentAgainstCustomer(documentId + "EX1", "500", "InvoiceAmt",
				"CreditNoteAmt", "GLAmt", "CheBankname", customerid, driver, "Cash", "SG Dollar (SGD)",
				"US Dollars (USD)");
	}

	@Test(priority = 11)
	public void MP_REfund() throws InterruptedException, AWTException, IOException {

		MPDocumentCreationforAmountDue.MPCust_RE2(documentID + "EX1", vendorid, driver);

	}

	@Test(priority = 12)
	public void MPReandRPAdExternallinking() throws InterruptedException, AWTException, IOException {
		ExternalLinkingMP_RP_WithDocuments.MP_PI_CN_RPAdExternalLinking(driver, "MPCusRE" + documentID + "EX1",
				"RPcust" + documentID + "EX1", "50");
	}

	@Test(priority = 13)
	public void MPReandRPADenAmountDue() throws InterruptedException, AWTException, IOException {
		MP_RPAmountDueEx.MP_RPEXlinkAmountDueVeri(driver, "MPCusRE" + documentID + "EX1", "RPcust" + documentID + "EX1",
				"50");
	}

	@Test(priority = 14)
	public void SIIForRP() throws InterruptedException, AWTException, IOException {
		SalesInvoice1.create_salesInvoice(documentID, productid, customerid, driver);
	}

	@Test(priority = 15)
	public void CreateRPAdvance() {
		RPDocumentCreationForAmountDue.RP_Advance(driver, documentID, customerid, "200");
	}

	@Test(priority = 16)
	public void RPandSIExternallinking() throws InterruptedException, AWTException, IOException {
		ExternalLinkingMP_RP_WithDocuments.RP_SI_DN_MPAdExternalLinking(driver, "RPAD" + documentId, "SI" + documentID,
				"50");
	}

	@Test(priority = 17)
	public void RPandSIAmountDue() throws InterruptedException, AWTException, IOException {
		MP_RPAmountDueEx.RP_SIEXlinkAmountDueVeri(driver, "RPAD" + documentId, "SI" + documentID, "50");
	}

	@Test(priority = 18)
	public void DNFORRP() throws InterruptedException, AWTException, IOException {
		DebitNoteAgainstCustomer.create_debitNoteAgainstCustomer(documentid, productid, customerid, driver);
	}

	@Test(priority = 19)
	public void CreateRPAdvance2() {
		RPDocumentCreationForAmountDue.RP_Advance(driver, documentID + "EX2", customerid, "200");
	}

	@Test(priority = 20)
	public void RPandDNExternallinking() throws InterruptedException, AWTException, IOException {
		ExternalLinkingMP_RP_WithDocuments.RP_SI_DN_MPAdExternalLinking(driver, "RPAD" + documentId + "EX2",
				"DNcus" + documentID, "50");
	}

	@Test(priority = 21)
	public void RPandDIAmountDue() throws InterruptedException, AWTException, IOException {
		MP_RPAmountDueEx.RP_DNEXlinkAmountDueVeri(driver, "RPAD" + documentId + "EX2", "DNcus" + documentID, "50");
	}

	@Test(priority = 22)
	public void MPVenAd3() throws InterruptedException, AWTException, IOException {
		DocumentCreationForBR.Create_Makepayment(driver, documentId + "-Ex2", "Cash", vendorid, "SG Dollar (SGD)",
				"US Dollars (USD)");

	}

	@Test(priority = 23)
	public void RPVenForDE() throws InterruptedException, AWTException, IOException {
		RPDocumentCreationForAmountDue.RPCust_DE(documentID, vendorid, driver);
	}

	@Test(priority = 24)
	public void RPandMPAdlinking() throws InterruptedException, AWTException, IOException {
		ExternalLinkingMP_RP_WithDocuments.RP_SI_DN_MPAdExternalLinking(driver, "RPCusDE" + documentId,
				"MPVenAd" + documentID + "-Ex2", "50");
	}

	@Test(priority = 25)
	public void RPReandMPADenAmountDue() throws InterruptedException, AWTException, IOException {
		MP_RPAmountDueEx.RP_MPEXlinkAmountDueVeri(driver, "RPCusDE" + documentId, "MPVenAd" + documentID + "-Ex2",
				"50");
	}

	@AfterTest
	public void close() {
		driver.close();
	}

}
