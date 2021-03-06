package krawler.erp.testcase.MPRP_BankReconcilationmodule;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import krawler.erp.BankReconcilation.BankReconcilation;
import krawler.erp.BankReconcilation.CreatePaymethodandCOA;
import krawler.erp.BankReconcilation.DocumentCreationForBR;
import krawler.erp.Miscellaneous.PaymentMethod;
import krawler.erp.page.ChartOfAccount;
import krawler.erp.page.CustomerMaster;
import krawler.erp.page.Login;
import krawler.erp.page.ProductMaster;
import krawler.erp.page.Utilities;
import krawler.erp.page.VendorMaster;

public class BankReconcilationtc {

	public static WebDriver driver;
	public static String server1 = "https://in5.accounting.deskera.com/a/sg12/#";
	public static String username = "sunita";
	public static String password = "1234";
	public static String documentID = "BR01";
	public static String productid = "Guitar";
	public static String vendor_ID = "Sumittest";
	public static String customer = "Sumittest";
	public static String BankName = "Automation BR01";// +documentID;
	public static String BankName2 = BankName + "2";
	public static String AccountFOrGL = "Test Account";
	public static String BaseCurrency = "SG Dollar (SGD)";
	public static String ForexCurrecny = "Malaysian Ringgit (MYR)";

	@BeforeTest
	public void beforeTestLogin() throws InterruptedException, IOException {
		Login ln = new Login();
		WebDriver driver = Login.loginERP(server1, username, password, 10);
		this.driver = driver;
		Utilities.clickExpander(driver);
	}

	@Test(priority = 1)
	public void createCOA() throws InterruptedException, AWTException, IOException {
		CreatePaymethodandCOA.create_ChartOfAccount(documentID, BankName, "product", "customerid", driver);
	}

	@Test(priority = 2)
	public void createPaymentMethod() throws InterruptedException, AWTException, IOException {
		CreatePaymethodandCOA.create_PaymentMethod(BankName, BankName, "Yes", "Yes", driver);
	}

	@Test(priority = 3)
	public void createVendor() throws InterruptedException, AWTException, IOException {
		VendorMaster.create_Vendor(vendor_ID, driver);
	}

	@Test(priority = 4)
	public void createCustomer() throws InterruptedException, AWTException, IOException {
		CustomerMaster.create_Customer(customer, driver);
	}

	@Test(priority = 5)
	public void createProduct() throws InterruptedException, AWTException, IOException {
		ProductMaster.create_product(productid, documentID, driver);
	}

	@Test(priority = 6)
	public void Create_MPbase() throws IOException, InterruptedException, AWTException {
		Utilities.refresh();
		Utilities.clickExpander(driver);
		DocumentCreationForBR.Create_Makepayment(driver, documentID, BankName, vendor_ID, BaseCurrency, ForexCurrecny);
	}

	@Test(priority = 7)
	public void Create_MPfor() throws IOException, InterruptedException, AWTException {
		DocumentCreationForBR.Create_Makepayment(driver, documentID + "For", BankName, vendor_ID, ForexCurrecny,
				ForexCurrecny);
	}

	@Test(priority = 8)
	public void Create_MPCustBase() throws IOException, InterruptedException, AWTException {
		DocumentCreationForBR.MakePayment_Customer(documentID, customer, "100", "CreditNoteAmt", driver, BankName,
				BaseCurrency, ForexCurrecny);
	}

	@Test(priority = 9)
	public void Create_MPCustForex() throws IOException, InterruptedException, AWTException {
		DocumentCreationForBR.MakePayment_Customer(documentID + "For", customer, "100", "CreditNoteAmt", driver,
				BankName, ForexCurrecny, ForexCurrecny);
	}

	@Test(priority = 10)
	public void Create_MPGLBase() throws IOException, InterruptedException, AWTException {
		DocumentCreationForBR.create_makePayment_GL(documentID, productid, AccountFOrGL, driver, BankName, BaseCurrency,
				ForexCurrecny);
	}

	@Test(priority = 11)
	public void Create_MPGLForex() throws IOException, InterruptedException, AWTException {
		DocumentCreationForBR.create_makePayment_GL(documentID + "For", productid, AccountFOrGL, driver, BankName,
				ForexCurrecny, ForexCurrecny);
	}

	@Test(priority = 12)
	public void Create_RPBase() throws IOException, InterruptedException, AWTException {
		DocumentCreationForBR.create_ReceivePaymentAgainstCustomer(documentID, "100", "InvoiceAmt", "CreditNoteAmt",
				"GLAmt", "American Express Bank Ltd.", customer, driver, BankName, BaseCurrency, ForexCurrecny);
	}

	@Test(priority = 13)
	public void Create_RPForex() throws IOException, InterruptedException, AWTException {
		DocumentCreationForBR.create_ReceivePaymentAgainstCustomer(documentID + "For", "100", "InvoiceAmt",
				"CreditNoteAmt", "GLAmt", "American Express Bank Ltd.", customer, driver, BankName, ForexCurrecny,
				ForexCurrecny);
	}

	@Test(priority = 14)
	public void Create_RPVendBase() throws IOException, InterruptedException, AWTException {
		DocumentCreationForBR.create_ReceivePayment_Vendor(documentID, productid, "100", vendor_ID, driver, BankName,
				BaseCurrency, ForexCurrecny);
	}

	@Test(priority = 15)
	public void Create_RPVendForex() throws IOException, InterruptedException, AWTException {
		DocumentCreationForBR.create_ReceivePayment_Vendor(documentID + "For", productid, "100", vendor_ID, driver,
				BankName, ForexCurrecny, ForexCurrecny);
	}

	@Test(priority = 16)
	public void Create_RPGLBase() throws IOException, InterruptedException, AWTException {
		DocumentCreationForBR.create_ReceivePayment_AgainstGL(documentID, AccountFOrGL, driver, BankName,
				"American Express Bank Ltd.", BaseCurrency, ForexCurrecny);
	}

	@Test(priority = 17)
	public void Create_RPGLForex() throws IOException, InterruptedException, AWTException {
		DocumentCreationForBR.create_ReceivePayment_AgainstGL(documentID + "For", AccountFOrGL, driver, BankName,
				"American Express Bank Ltd.", ForexCurrecny, ForexCurrecny);
	}

	@Test(priority = 18)
	public void createCashpurchase() throws InterruptedException, AWTException, IOException {
		DocumentCreationForBR.create_cashPurchase(documentID, productid, vendor_ID, driver, BankName);
	}

	@Test(priority = 19)
	public void createCashSales() throws InterruptedException, AWTException, IOException {
		DocumentCreationForBR.create_cashSale(documentID, productid, customer, BankName, driver,
				"American Express Bank Ltd.");
	}

	@Test(priority = 20)
	public void createCOA2() throws InterruptedException, AWTException, IOException {

		CreatePaymethodandCOA.create_ChartOfAccount(documentID, BankName2, "product", "customerid", driver);
	}

	@Test(priority = 21)
	public void createFundtJE() throws InterruptedException, AWTException, IOException {
		DocumentCreationForBR.create_fundTransferJE(documentID, productid, vendor_ID, BankName, driver, BankName2);
	}

	@Test(priority = 22)
	public void OpenBR() throws IOException, AWTException, InterruptedException {
		Utilities.refresh();
		BankReconcilation.open_reconcilation(driver, BankName);
	}

	@Test(priority = 23)
	public void exportBR() throws IOException, AWTException, InterruptedException {
		BankReconcilation.Export_BR(driver);
	}

	@Test(priority = 24)
	public void BankRecon() throws IOException, AWTException, InterruptedException {
		BankReconcilation.Bank_reconcilation(driver, documentID);
	}

	@Test(priority = 25)
	public void VerifyBankRecon() throws IOException, AWTException, InterruptedException {
		BankReconcilation.Verify_BR(driver, documentID, BankName);
	}

	@Test(priority = 26)
	public void ExportUnREBankRecon() throws IOException, AWTException, InterruptedException {
		BankReconcilation.Export_BankUnrecocilation(driver, documentID);
	}

	@Test(priority = 26)
	public void UnREBankRecon() throws IOException, AWTException, InterruptedException {
		BankReconcilation.Bank_Unrecocilation(driver, documentID);
	}

	@Test(priority = 28)
	public void VerifyUnREBankRecon() throws IOException, AWTException, InterruptedException {
		BankReconcilation.Verify_Bank_Unrecocilation(driver, documentID, BankName);
		Utilities.refresh();
	}

	@AfterClass
	public void quit() {
		driver.close();
	}

}
