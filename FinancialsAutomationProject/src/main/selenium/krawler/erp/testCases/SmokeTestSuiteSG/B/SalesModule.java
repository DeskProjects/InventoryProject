package krawler.erp.testCases.SmokeTestSuiteSG.B;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import krawler.erp.modules.CashRefund1;
import krawler.erp.modules.CashSale1;
import krawler.erp.modules.CustomerQuotation1;
import krawler.erp.modules.DeliveryOrderModule1;
import krawler.erp.modules.SalesInvoice1;
import krawler.erp.modules.SalesOrder1;
import krawler.erp.modules.SalesReturn1;
import krawler.erp.page.AuditTrailTest1;
import krawler.erp.page.CashSale;
import krawler.erp.page.CreditNoteOtherwise;
import krawler.erp.page.CustomerQuotation;
import krawler.erp.page.DeliveryOrder;
import krawler.erp.page.SalesInvoice;
import krawler.erp.page.SalesOrder;
import krawler.erp.page.SalesReturnOnly;
import krawler.erp.page.SalesReturnWithCreditNote;

public class SalesModule extends BaseSetUp {

	// Customer Quotation
	@Test(priority = 300, groups = { "Create" })
	public void Create_CQ() throws InterruptedException, IOException, AWTException {
		CustomerQuotation1.create_CustomerQuotation(documentid, productid, customerid, driver);
	}

	@Test(priority = 301, groups = { "Verify" })
	public void Verify_CQ() throws InterruptedException, IOException, AWTException {
		CustomerQuotation.verify_CustomerQuotation(documentid, productid, customerid, driver);
	}

	@Test(priority = 302, groups = { "CED" })
	public void CopyEditDelete_CQ() throws InterruptedException, IOException, AWTException {
		CustomerQuotation1.Copy_CustomerQuotation(documentid, driver);
		CustomerQuotation1.Edit_CustomerQuotation(documentid, driver);
		CustomerQuotation1.Delete_CustomerQuotation(documentid, driver);
	}

	@Test(priority = 303, groups = { "Audit Trail" })
	public void AuditTrail_CQ() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate2("CQ" + documentid, "Customer Quotation", driver);
	}

	@Test(priority = 304, groups = { "Export" })
	public void Export_CQ() throws InterruptedException, IOException, AWTException {
		CustomerQuotation.Export_CustomerQuotation(driver);
	}

	// ------------------------------------------------------------------------------------------------------------------------------
	// Sales Order

	@Test(priority = 310, groups = { "Create" })
	public void Create_SO() throws InterruptedException, IOException, AWTException {
		SalesOrder1.create_SalesOrder(documentid, productid, customerid, driver);
	}

	@Test(priority = 311, groups = { "Verify" })
	public void Verify_SO() throws InterruptedException, IOException, AWTException {
		SalesOrder.verify_salesOrder(documentid, productid, customerid, driver);
	}

	@Test(priority = 312, groups = { "CED" })
	public void CopyEditDelete_SO() throws InterruptedException, IOException, AWTException {
		SalesOrder1.Copy_SalesOrder(documentid, driver);
		SalesOrder1.Edit_SalesOrder(documentid, driver);
		SalesOrder1.Delete_SalesOrder(documentid, driver);
	}

	@Test(priority = 313, groups = { "Audit Trail" })
	public void AuditTrail_SO() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate1("SO" + documentid, "Sales Order", driver);
	}

	@Test(priority = 314, groups = { "Export" })
	public void Export_SO() throws InterruptedException, IOException, AWTException {
		SalesOrder.Export_SalesOrder(driver);
	}

	// ---------------------------------------------------------------------------------------
	// Cash Sales
	@Test(priority = 320, groups = { "Create" })
	public void Create_CashSales() throws InterruptedException, IOException, AWTException {
		CashSale1.create_CashSale(documentid, productid, customerid, driver);
	}

	@Test(priority = 321, groups = { "Verify" })
	public void Verify_CashSales() throws InterruptedException, IOException, AWTException {
		CashSale.verify_cashSale(documentid, productid, customerid, driver);
	}

	@Test(priority = 322, groups = { "CED" })
	public void CopyEditDelete_CashSales() throws InterruptedException, IOException, AWTException {
		CashSale1.Copy_CashSale(documentid, driver);
		CashSale1.Edit_CashSales(documentid, driver);
		CashSale1.Delete_CashSale(documentid, driver);
	}

	@Test(priority = 323, groups = { "Audit Trail" })
	public void AuditTrail_CashSales() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate3("CS" + documentid, "Cash Invoice", driver);
	}

	// -----------------------------------------------------------------------------------------------
	// Sales Invoice

	@Test(priority = 330, groups = { "Create" })
	public void Create_Invoice() throws InterruptedException, IOException, AWTException {
		SalesInvoice1.create_salesInvoice(documentid, productid, customerid, driver);
	}

	@Test(priority = 331, groups = { "Verify" })
	public void Verify_Invoice() throws InterruptedException, IOException, AWTException {
		SalesInvoice.verify_SalesInvoice(documentid, productid, customerid, driver);
	}

	@Test(priority = 332, groups = { "CED" })
	public void CopyEditDelete_Invoice() throws InterruptedException, IOException, AWTException {
		SalesInvoice1.Copy_salesInvoice(documentid, driver);
		SalesInvoice1.Edit_salesInvoice(documentid, driver);
		SalesInvoice1.Delete_salesInvoice(documentid, driver);
	}

	@Test(priority = 333, groups = { "Audit Trail" })
	public void AuditTrail_Invoice() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate3("SI" + documentid, "Sales Invoice", driver);
	}

	@Test(priority = 334, groups = { "Export" })
	public void Export_Invoice() throws InterruptedException, IOException, AWTException {
		CashSale.Export_TodayDate(driver);
	}

	// ----------------------------------------------------------------------------------------------------------------------------
	// Delivery Order

	@Test(priority = 340, groups = { "Create" })
	public void Create_DO() throws InterruptedException, IOException, AWTException {
		DeliveryOrderModule1.create_DeliveryOrder(documentid, productid, customerid, driver);
	}

	@Test(priority = 341, groups = { "Verify" })
	public void Verify_DO() throws InterruptedException, IOException, AWTException {
		DeliveryOrder.verify_deliveryOrder(documentid, productid, customerid, driver);
	}

	@Test(priority = 342, groups = { "CED" })
	public void CopyEditDelete_DO() throws InterruptedException, IOException, AWTException {
		DeliveryOrderModule1.Copy_DeliveryOrder(documentid, driver);
		DeliveryOrderModule1.Edit_DeliveryOrder(documentid, driver);
		DeliveryOrderModule1.Delete_DeliveryOrder(documentid, driver);
	}

	@Test(priority = 343, groups = { "Audit Trail" })
	public void AuditTrail_DO() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate7("DO" + documentid, "Delivery Order", driver);
	}

	@Test(priority = 344, groups = { "Export" })
	public void Export_DO() throws InterruptedException, IOException, AWTException {
		DeliveryOrder.Export_DeliveryOrder(driver);
	}

	// ----------------------------------------------------------------------------------------------------------------------------
	// Sales Return Only

	@Test(priority = 350, groups = { "Create" })
	public void Create_SROnly() throws InterruptedException, IOException, AWTException {
		SalesReturn1.create_SalesReturnOnly(documentid, productid, customerid, driver);
	}

	@Test(priority = 351, groups = { "Verify" })
	public void Verify_SROnly() throws InterruptedException, IOException, AWTException {
		SalesReturnOnly.verify_salesReturnOnly("SRO" + documentid, productid, customerid, driver);
	}

	@Test(priority = 352, groups = { "CED" })
	public void CopyEditDelete_SROnly() throws InterruptedException, IOException, AWTException {
		SalesReturn1.Copy_SalesReturn(false, "SRO" + documentid, productid, driver);
		SalesReturn1.Edit_SalesReturn(false, "SRO" + documentid, driver, productid);
		SalesReturn1.Delete_SalesReturn("SRO" + documentid, driver);
	}

	@Test(priority = 353, groups = { "Audit Trail" })
	public void AuditTrail_SROnly() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate13("SRO" + documentid, "Sales Return", driver);
	}

	// Sales Return Credit Note

	@Test(priority = 360, groups = { "Create" })
	public void Create_SRCredit() throws InterruptedException, IOException, AWTException {
		SalesReturn1.create_SaleswithCreditNote(false, documentid, productid, customerid, driver);
	}

	@Test(priority = 361, groups = { "Verify" })
	public void Verify_SRCredit() throws InterruptedException, IOException, AWTException {
		SalesReturnWithCreditNote.verify_salesReturnWithCreditNote("SrCrdNt" + documentid, productid, customerid,
				driver);
	}

	@Test(priority = 362, groups = { "CED" })
	public void CopyEditDelete_SRCredit() throws InterruptedException, IOException, AWTException {
		SalesReturn1.Copy_SalesReturn(true, "SrCrdNt" + documentid, productid, driver);
		SalesReturn1.Edit_SalesReturn(true, "SrCrdNt" + documentid, driver, productid);
		SalesReturn1.Delete_SalesReturn("SrCrdNt" + documentid, driver);
	}

	@Test(priority = 363, groups = { "Audit Trail" })
	public void AuditTrail_SRCredit() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate13("SrCrdNt" + documentid, "Sales Return", driver);
	}

	// Sales Return Credit Note link SI

	@Test(priority = 365, groups = { "Create" })
	public void Create_SRCreditSI() throws InterruptedException, IOException, AWTException {
		SalesReturn1.create_SaleswithCreditNote(true, documentid, productid, customerid, driver);
	}

	@Test(priority = 366, groups = { "Verify" })
	public void Verify_SRCreditSI() throws InterruptedException, IOException, AWTException {
		SalesReturnWithCreditNote.verify_salesReturnWithCreditNote("SrCrdNtSi" + documentid, productid, customerid,
				driver);
	}

	@Test(priority = 367, groups = { "CED" })
	public void CopyEditDelete_SRCreditSI() throws InterruptedException, IOException, AWTException {
		SalesReturn1.Copy_SalesReturn(true, "SrCrdNtSi" + documentid, productid, driver);
		SalesReturn1.Edit_SalesReturn(true, "SrCrdNtSi" + documentid, driver, productid);
		SalesReturn1.Delete_SalesReturn("SrCrdNtSi" + documentid, driver);
	}

	@Test(priority = 368, groups = { "Audit Trail" })
	public void AuditTrail_SRCreditSI() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate13("SrCrdNtSi" + documentid, "Sales Return", driver);
	}

	// Sales Return Cash Refund

	@Test(priority = 370, groups = { "Create" })
	public void Create_SRCash() throws InterruptedException, IOException, AWTException {
		CashRefund1.create_SR_CashRefund(documentid, productid, customerid, driver);
	}

	@Test(priority = 371, groups = { "Verify" })
	public void Verify_SRCash() throws InterruptedException, IOException, AWTException {
		// Code not present
	}

	@Test(priority = 372, groups = { "CED" })
	public void CopyEditDelete_SRCash() throws InterruptedException, IOException, AWTException {
		CashRefund1.Copy_SalesReturn("SRrefund" + documentid, productid, driver);
		CashRefund1.Edit_SalesReturn("SRrefund" + documentid, driver, productid);
		CashRefund1.Delete_SalesReturn("SRrefund" + documentid, driver);
	}

	@Test(priority = 373, groups = { "Audit Trail" })
	public void AuditTrail_SRCash() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate13("SRrefund" + documentid, "Sales Return", driver);
	}

	@Test(priority = 374, groups = { "Export" })
	public void Export_SalesReturn() throws InterruptedException, IOException, AWTException {
		SalesReturnOnly.Export_SalesReturn(driver);
	}

}
