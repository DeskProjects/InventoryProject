package krawler.erp.testCases.SmokeTestSuiteWithNoDataEntry;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import krawler.erp.SmokeTestSuiteNoDataEntry.ALLReportsLoading;
import krawler.erp.SmokeTestSuiteNoDataEntry.AllFormLoading;
import krawler.erp.page.Login;
import krawler.erp.page.Utilities;

public class All_FormAndReportloading {

	public static WebDriver driver;

	@Parameters({ "server1", "username", "password" })
	@BeforeTest
	public void beforeTestLogin(String server1, String username, String Password)
			throws InterruptedException, IOException {
		Login ln = new Login();
		WebDriver driver = Login.loginERP(server1, username, Password, 5);
		this.driver = driver;
		Utilities.clickExpander(driver);
	}

	@Test(priority = 1)
	public void openCP() throws InterruptedException, AWTException, IOException {
		AllFormLoading.cashPurchase(driver);
	}

	@Test(priority = 2)
	public void openCS() throws InterruptedException, AWTException, IOException {
		AllFormLoading.cashSale(driver);
	}

	@Test(priority = 3)
	public void openPRe() throws InterruptedException, AWTException, IOException {
		AllFormLoading.Purchase_Requisation(driver);
	}

	@Test(priority = 4)
	public void VendorQuotation() throws InterruptedException, AWTException, IOException {
		AllFormLoading.VendorQuatation(driver);
	}

	@Test(priority = 5)
	public void PurchaseOrder() throws InterruptedException, AWTException, IOException {
		AllFormLoading.PurchaseOrder(driver);
	}

	@Test(priority = 6)
	public void purchaseInvoice() throws InterruptedException, AWTException, IOException {
		AllFormLoading.vendorInvoice(driver);
	}

	@Test(priority = 7)
	public void GoodReceipt() throws InterruptedException, AWTException, IOException {
		AllFormLoading.goodReceipt(driver);
	}

	@Test(priority = 8)
	public void MakePayment_Customer() throws InterruptedException, AWTException, IOException {
		AllFormLoading.MakePayment_Customer(driver);
	}

	@Test(priority = 9)
	public void MakePayment_GL() throws InterruptedException, AWTException, IOException {
		AllFormLoading.MakePayment_GL(driver);
	}

	@Test(priority = 10)
	public void MakePayment_Vendor() throws InterruptedException, AWTException, IOException {
		AllFormLoading.MakePayment_Vendor(driver);
	}

	@Test(priority = 11)
	public void PurchaseReturn_Only() throws InterruptedException, AWTException, IOException {
		AllFormLoading.PurchaseReturn(driver);
	}

	@Test(priority = 12)
	public void PurchaseReturn_withDN() throws InterruptedException, AWTException, IOException {
		AllFormLoading.PurchaseReturn_withDN(driver);
	}

	@Test(priority = 13)
	public void DebitNoteAgainstCustomer() throws InterruptedException, AWTException, IOException {
		AllFormLoading.DebitNoteAgainstCustomer(driver);
	}

	@Test(priority = 14)
	public void DebitNoteUnderCharge() throws InterruptedException, AWTException, IOException {
		AllFormLoading.DebitNoteUnderCharge(driver);
	}

	@Test(priority = 15)
	public void DebitNoteOverCharge() throws InterruptedException, AWTException, IOException {
		AllFormLoading.DebitNoteOverCharge(driver);
	}

	@Test(priority = 16)
	public void DebitNoteOtherwise() throws InterruptedException, AWTException, IOException {
		AllFormLoading.DebitNoteOtherwise(driver);
	}

	@Test(priority = 17)
	public void DebitNotePurchaseInvoice() throws InterruptedException, AWTException, IOException {
		AllFormLoading.DebitNotePurchaseInvoice(driver);
	}

	@Test(priority = 18)
	public void CustomerQuotationForm() throws InterruptedException, AWTException, IOException {
		AllFormLoading.CustomerQuotationForm(driver);
	}

	@Test(priority = 19)
	public void SalesOrder() throws InterruptedException, AWTException, IOException {
		AllFormLoading.SalesOrderForm(driver);
	}

	@Test(priority = 20)
	public void SalesInvoice() throws InterruptedException, AWTException, IOException {
		AllFormLoading.SalesInvoiceform(driver);
	}

	@Test(priority = 21)
	public void DeliveryOrder() throws InterruptedException, AWTException, IOException {
		AllFormLoading.DeliveryOrderform(driver);
	}

	@Test(priority = 22)
	public void ReceivePaymentAgainstCustomer() throws InterruptedException, AWTException, IOException {
		AllFormLoading.ReceivePaymentAgainstCustomer(driver);
	}

	@Test(priority = 23)
	public void ReceivePaymentAgainstGL() throws InterruptedException, AWTException, IOException {
		AllFormLoading.ReceivePaymentAgainstCustomer(driver);
	}

	@Test(priority = 24)
	public void ReceivePayment_Vendor() throws InterruptedException, AWTException, IOException {
		AllFormLoading.ReceivePayment_Vendor(driver);
	}

	@Test(priority = 25)
	public void SaleReturnOnly() throws InterruptedException, AWTException, IOException {
		AllFormLoading.SaleReturnOnly(driver);
	}

	@Test(priority = 26)
	public void SaleReturnWithCN() throws InterruptedException, AWTException, IOException {
		AllFormLoading.SaleReturnWithCN(driver);
	}

	@Test(priority = 27)
	public void CreditNoteOtherwise() throws InterruptedException, AWTException, IOException {
		AllFormLoading.CreditNoteOtherwise(driver);
	}

	@Test(priority = 28)
	public void CreditNoteUnderCharge() throws InterruptedException, AWTException, IOException {
		AllFormLoading.CreditNoteUnderCharge(driver);
	}

	@Test(priority = 29)
	public void CreditNoteOverCharge() throws InterruptedException, AWTException, IOException {
		AllFormLoading.CreditNoteOverCharge(driver);
	}

	@Test(priority = 30)
	public void CreditNoteAgainstVendor() throws InterruptedException, AWTException, IOException {
		AllFormLoading.CreditNoteAgainstVendor(driver);
	}

	@Test(priority = 31)
	public void CreditNoteSalesInvoice() throws InterruptedException, AWTException, IOException {
		AllFormLoading.CreditNoteSalesInvoice(driver);
	}

	@Test(priority = 32)
	public void NormalJE() throws InterruptedException, AWTException, IOException {
		AllFormLoading.JENormal(driver);
	}

	@Test(priority = 33)
	public void PartyJE() throws InterruptedException, AWTException, IOException {
		AllFormLoading.JEParty(driver);
	}

	@Test(priority = 34)
	public void FundTranferJE() throws InterruptedException, AWTException, IOException {
		AllFormLoading.JEFundTranfer(driver);
	}

	@Test(priority = 35)
	public void VendorMaster() throws InterruptedException, AWTException, IOException {
		AllFormLoading.VendorMaster(driver);
	}

	@Test(priority = 36)
	public void CustomerMaster() throws InterruptedException, AWTException, IOException {
		AllFormLoading.CustomerMaster(driver);
	}

	@Test(priority = 37)
	public void ProductMaster() throws InterruptedException, AWTException, IOException {
		AllFormLoading.ProductMaster(driver);
	}

	@Test(priority = 38)
	public void Bsload() throws IOException, InterruptedException {
		ALLReportsLoading.BS_loading(driver);
	}

	@Test(priority = 39)
	public void CustomerAging() throws IOException, InterruptedException {
		ALLReportsLoading.CustomerAging(driver);
	}

	@Test(priority = 40)
	public void VendorAging() throws IOException, InterruptedException {
		ALLReportsLoading.VendorAging(driver);
	}

	@Test(priority = 41)
	public void PnLReport() throws IOException, InterruptedException {
		ALLReportsLoading.PnLReport(driver);
	}

	@Test(priority = 42)
	public void TrailBalance() throws IOException, InterruptedException {
		ALLReportsLoading.TrailBalance(driver);
	}

	@Test(priority = 43)
	public void GLReport() throws IOException, InterruptedException {
		ALLReportsLoading.GLReport(driver);
	}

	@Test(priority = 44)
	public void CP_PIReport() throws IOException, InterruptedException {
		ALLReportsLoading.CP_PIReport(driver);
	}

	@Test(priority = 46)
	public void PurchaseRequisition() throws IOException, InterruptedException {
		ALLReportsLoading.PurchaseRequisition(driver);
	}

	@Test(priority = 47)
	public void VendorQuotationReport() throws IOException, InterruptedException {
		ALLReportsLoading.VendorQuotationReport(driver);
	}

	@Test(priority = 48)
	public void PurchaseOrderReport() throws IOException, InterruptedException {
		ALLReportsLoading.PurchaseOrderReport(driver);
	}

	@Test(priority = 49)
	public void GoodReceiptReport() throws IOException, InterruptedException {
		ALLReportsLoading.GoodReceiptReport(driver);
	}

	@Test(priority = 50)
	public void MakePaymentReport() throws IOException, InterruptedException {
		ALLReportsLoading.MakePaymentReport(driver);
	}

	@Test(priority = 51)
	public void PurchaseReturnReport() throws IOException, InterruptedException {
		ALLReportsLoading.PurchaseReturnReport(driver);
	}

	@Test(priority = 52)
	public void DebitNoteReport() throws IOException, InterruptedException {
		ALLReportsLoading.DebitNoteReport(driver);
	}

	@Test(priority = 53)
	public void CustomerQuotationReport() throws IOException, InterruptedException {
		ALLReportsLoading.CustomerQuotationReport(driver);
	}

	@Test(priority = 54)
	public void SalesOrderReport() throws IOException, InterruptedException {
		ALLReportsLoading.SalesOrderReport(driver);
	}

	@Test(priority = 54)
	public void CS_SIReport() throws IOException, InterruptedException {
		ALLReportsLoading.CS_SIReport(driver);
	}

	@Test(priority = 55)
	public void DeliveryOrderReport() throws IOException, InterruptedException {
		ALLReportsLoading.DeliveryOrderReport(driver);
	}

	@Test(priority = 56)
	public void ReceivePaymentReport() throws IOException, InterruptedException {
		ALLReportsLoading.ReceivePaymentReport(driver);
	}

	@Test(priority = 57)
	public void SalesReturnReport() throws IOException, InterruptedException {
		ALLReportsLoading.ReceivePaymentReport(driver);
	}

	@Test(priority = 58)
	public void CreditNoteReport() throws IOException, InterruptedException {
		ALLReportsLoading.CreditNoteReport(driver);
	}

	@Test(priority = 59)
	public void JournalEntryReport() throws IOException, InterruptedException {
		ALLReportsLoading.JournalEntryReport(driver);
	}

	@Test(priority = 60)
	public void VendorMasterReport() throws IOException, InterruptedException {
		ALLReportsLoading.VendorMasterReport(driver);
	}

	@Test(priority = 61)
	public void CustomerMasterReport() throws IOException, InterruptedException {
		ALLReportsLoading.CustomerMasterReport(driver);
	}

	@Test(priority = 62)
	public void ProductMasterReport() throws IOException, InterruptedException {
		ALLReportsLoading.ProductMasterReport(driver);
	}

	@Test(priority = 63)
	public void SOAReportLoading() throws IOException, InterruptedException, AWTException {
		ALLReportsLoading.SOAReportLoading(driver);
	}

}
