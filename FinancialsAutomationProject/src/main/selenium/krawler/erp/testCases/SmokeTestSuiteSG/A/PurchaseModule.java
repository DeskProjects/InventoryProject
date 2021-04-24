package krawler.erp.testCases.SmokeTestSuiteSG.A;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import krawler.erp.modules.CashPurchase1;
import krawler.erp.modules.GoodsReceipetModule1;
import krawler.erp.modules.PurchaseInvoice1;
import krawler.erp.modules.PurchaseOrder1;
import krawler.erp.modules.PurchaseRequisition1;
import krawler.erp.modules.PurchaseReturn1;
import krawler.erp.modules.VendorQuotation1;
import krawler.erp.page.AuditTrailTest1;
import krawler.erp.page.CashPurchase;
import krawler.erp.page.GoodReceipt;
import krawler.erp.page.PurchaseOrder;
import krawler.erp.page.PurchaseRequisition;
import krawler.erp.page.PurchaseReturnDebitNote;
import krawler.erp.page.PurchaseReturnOnly;
import krawler.erp.page.VendorInvoice;
import krawler.erp.page.VendorQuotation;

public class PurchaseModule extends BaseSetUp {

	// Purchase Requisition
	@Test(priority = 200, groups = { "Create" })
	public void Create_PurRequisition() throws InterruptedException, IOException, AWTException {
		PurchaseRequisition1.create_PurchaseRequisition(documentid, productid, driver);
	}

	@Test(priority = 201, groups = { "Verify" })
	public void Verify_PurRequistion() throws InterruptedException, IOException, AWTException {
		PurchaseRequisition.verify_Purchase_Requisation(documentid, productid, driver);
	}

	@Test(priority = 202, groups = { "CED" })
	public void CopyEditDelete_PurRequisiton() throws InterruptedException, IOException, AWTException {
		PurchaseRequisition1.PurchaseRequisition_EmailCopyEditDelete(documentid, driver);
	}

	@Test(priority = 203, groups = { "Audit Trail" })
	public void AuditTrail_PurRequisition() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate1("PR" + documentid, "Purchase Requisition", driver);
	}

	@Test(priority = 204, groups = { "Export" })
	public void Export_PurRequisition() throws InterruptedException, IOException, AWTException {
		PurchaseRequisition.Export_PR(driver);
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------
	// Vendor Quotation

	@Test(priority = 210, groups = { "Create" })
	public void Create_VendorQuotation() throws InterruptedException, IOException, AWTException {
		VendorQuotation1.create_VendorQuotation(documentid, productid, vendorid, driver);
	}

	@Test(priority = 211, groups = { "Verify" })
	public void Verify_VednorQuotation() throws InterruptedException, IOException, AWTException {
		VendorQuotation.verify_VendorQuatation(documentid, productid, vendorid, driver);
	}

	@Test(priority = 212, groups = { "CED" })
	public void CopyEditDelete_VendorQoutation() throws InterruptedException, IOException, AWTException {
		VendorQuotation1.Copy_VendorQuotation(documentid, driver);
		VendorQuotation1.Edit_VendorQuotation(documentid, driver);
		VendorQuotation1.Delete_VendorQuotation(documentid, driver);
	}

	@Test(priority = 213, groups = { "Audit Trail" })
	public void AuditTrail_VendorQuotation() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate1("VQ" + documentid, "Vendor Quotation", driver);
	}

	@Test(priority = 214, groups = { "Export" })
	public void Export_VendorQuotation() throws InterruptedException, IOException, AWTException {
		VendorQuotation.Export_VQ(driver);
	}

	// Purchase Order Product
	@Test(priority = 220, groups = { "Create" })
	public void Create_PurchaseOrder() throws InterruptedException, IOException, AWTException {
		PurchaseOrder1.create_PurchaseOrder(documentid, productid, vendorid, driver);
	}

	@Test(priority = 221, groups = { "Verify" })
	public void Verify_PurchaseOrder() throws InterruptedException, IOException, AWTException {
		PurchaseOrder.verify_PurchaseOrder(documentid, productid, vendorid, driver);
	}

	@Test(priority = 223, groups = { "CED" })
	public void CopyEditDelete_PurchaseOrder() throws InterruptedException, IOException, AWTException {
		PurchaseOrder1.Copy_PurchaseOrder(documentid, driver);
		PurchaseOrder1.Edit_PurchaseOrder(documentid, driver);
		PurchaseOrder1.Delete_PurchaseOrder(documentid, driver);
	}

	@Test(priority = 224, groups = { "Audit Trail" })
	public void AuditTrail_PurchaseOrder() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate1("PO" + documentid, "Purchase Order", driver);
	}

	// ----------------------------------------------------------------------------------------------------------
	// Purchase Order (Expense)
	@Test(priority = 225, groups = { "Create" })
	public void Create_PurchaseOrderEx() throws InterruptedException, IOException, AWTException {
		PurchaseOrder1.create_POExpense(documentid, productid, vendorid, driver);
	}

	@Test(priority = 226, groups = { "Verify" })
	public void Verify_PurchaseOrderEx() throws InterruptedException, IOException, AWTException {
		// Code not present for Verification
		PurchaseOrder.verify_PurchaseOrder(documentid, productid, vendorid, driver);
	}

	@Test(priority = 227, groups = { "CED" })
	public void CopyEditDelete_PurchaseOrderEx() throws InterruptedException, IOException, AWTException {
		PurchaseOrder1.CopyEditDelete_POExpense("POExp" + documentid, driver);
	}

	@Test(priority = 228, groups = { "Export" })
	public void Export_PurchaseOrderEx() throws InterruptedException, IOException, AWTException {
		PurchaseOrder.Export_PurchaseOrder(driver);
	}

	// ----------------------------------------------------------------------------------------------------------
	// Cash Purchase

	@Test(priority = 230, groups = { "Create" })
	public void Create_CashPurchase() throws InterruptedException, IOException, AWTException {
		CashPurchase1.create_CashPurchase(documentid, productid, vendorid, driver);
	}

	@Test(priority = 231, groups = { "Verify" })
	public void Verify_CashPurchase() throws InterruptedException, IOException, AWTException {
		CashPurchase.verify_CashPurchase(documentid, productid, vendorid, driver);
	}

	@Test(priority = 232, groups = { "CED" })
	public void CopyEditDelete_CashPurchase() throws InterruptedException, IOException, AWTException {
		CashPurchase1.Copy_CashPurchase("CshPur" + documentid, driver);
		CashPurchase1.Edit_CashPurchase("CshPur" + documentid, driver);
		CashPurchase1.Delete_CashPurchase("CshPur" + documentid, driver);
	}

	// ----------------------------------------------------------------------------------------------------------
	// Cash Purchase (Expense)

	@Test(priority = 235, groups = { "Create" })
	public void Create_CashPurchaseEx() throws InterruptedException, IOException, AWTException {
		CashPurchase1.create_CPExpense(documentid, productid, vendorid, driver);
	}

	@Test(priority = 236, groups = { "Verify" })
	public void Verify_CashPurchaseEx() throws InterruptedException, IOException, AWTException {
		// Code not present for Verification
	}

	@Test(priority = 237, groups = { "CED" })
	public void CopyEditDelete_CashPurchaseEx() throws InterruptedException, IOException, AWTException {
		CashPurchase1.CopyEditDelete_CashPurchase_Expense("CPExp" + documentid, driver);
	}

	// Vednor Invoice

	@Test(priority = 240, groups = { "Create" })
	public void Create_VendorInvoice() throws InterruptedException, IOException, AWTException {
		PurchaseInvoice1.create_PurchaseInvoice(documentid, productid, vendorid, driver);
	}

	@Test(priority = 241, groups = { "Verify" })
	public void Verify_VendorInvoice() throws InterruptedException, IOException, AWTException {
		// Code not present for Verification
		VendorInvoice.verify_VendorInvoice(documentid, productid, vendorid, driver);
	}

	@Test(priority = 242, groups = { "CED" })
	public void CopyEditDelete_VendorInvoice() throws InterruptedException, IOException, AWTException {
		PurchaseInvoice1.Copy_PurchaseInvoice(documentid, driver);
		PurchaseInvoice1.Edit_PurchaseInvoice(documentid, driver);
		PurchaseInvoice1.Delete_PurchaseInvoice(documentid, driver);
	}

	@Test(priority = 243, groups = { "Audit Trail" })
	public void AuditTrail_VendorInvoice() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate3("PurInvo" + documentid, "Vendor Invoice", driver);
	}

	// ----------------------------------------------------------------------------------------------------------
	// Vednor Invoice (Expense)

	@Test(priority = 245, groups = { "Create" })
	public void Create_VendorInvoiceEx() throws InterruptedException, IOException, AWTException {
		PurchaseInvoice1.create_PIExpense(documentid, productid, vendorid, driver);
	}

	@Test(priority = 246, groups = { "Verify" })
	public void Verify_VendorInvoiceEx() throws InterruptedException, IOException, AWTException {
		// Code not present for Verification
		VendorInvoice.verify_VendorInvoice(documentid, productid, vendorid, driver);
	}

	@Test(priority = 247, groups = { "CED" })
	public void CopyEditDelete_VendorInvoiceEx() throws InterruptedException, IOException, AWTException {
		PurchaseInvoice1.EmailCopyEditDelete_VendorPiExpense("PIExp" + documentid, driver);
	}

	@Test(priority = 248, groups = { "Export" })
	public void Export_VendorInvoice() throws InterruptedException, IOException, AWTException {
		VendorInvoice.Export_PurchaseInvoice(driver);
	}

	// ----------------------------------------------------------------------------------------------------------
	// Goods Receipt
	@Test(priority = 250, groups = { "Create" })
	public void Create_GoodsReceipt() throws InterruptedException, IOException, AWTException {
		GoodsReceipetModule1.create_GoodsReceipet(documentid, productid, vendorid, driver);
	}

	@Test(priority = 251, groups = { "Verify" })
	public void Verify_GoodsReceipt() throws InterruptedException, IOException, AWTException {
		GoodReceipt.verify_GoodReceipt(documentid, productid, vendorid, driver);
	}

	@Test(priority = 252, groups = { "CED" })
	public void CopyEditDelete_GoodsReceipt() throws InterruptedException, IOException, AWTException {
		GoodsReceipetModule1.Copy_GoodReceipt(documentid, driver);
		GoodsReceipetModule1.Edit_GoodReceipt(documentid, driver);
		GoodsReceipetModule1.Delete_GoodReceipt(documentid, driver);
	}

	@Test(priority = 253, groups = { "Audit Trail" })
	public void AuditTrail_GoodsReceipt() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate7("GR" + documentid, "Goods Receipt", driver);
	}

	@Test(priority = 254, groups = { "Export" })
	public void Export_GoodsReceipt() throws InterruptedException, IOException, AWTException {
		GoodReceipt.Export_GoodsReceipt(driver);
	}

	// ----------------------------------------------------------------------------------------------------------
	// Purchase Return Only

	@Test(priority = 260, groups = { "Create" })
	public void Create_PurchaseReturnOnly() throws InterruptedException, IOException, AWTException {
		PurchaseReturn1.create_PurchaseReturnOnly(documentid, productid, vendorid, driver);
	}

	@Test(priority = 261, groups = { "Verify" })
	public void Verify_PurchaseReturnOnly() throws InterruptedException, IOException, AWTException {
		PurchaseReturnOnly.verify_purchaseReturn(documentid, productid, vendorid, driver);
	}

	@Test(priority = 262, groups = { "CED" })
	public void CopyEditDelete_PurchaseReturnOnly() throws InterruptedException, IOException, AWTException {
		PurchaseReturn1.CopyEditDelete_PurchaseReturn(false, "PRN" + documentid, productid, driver);
	}

	@Test(priority = 263, groups = { "Audit Trail" })
	public void AuditTrail_PurchaseReturnOnly() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate13("PRN" + documentid, "Purchase Return", driver);
	}

	// ----------------------------------------------------------------------------------------------------------
	// Purchase Return DebitNote
	@Test(priority = 270, groups = { "Create" })
	public void Create_PurchaseReturnDn() throws InterruptedException, IOException, AWTException {
		PurchaseReturn1.create_PurchaseReturnDebitNote(false, documentid, productid, vendorid, driver);
	}

	@Test(priority = 271, groups = { "Verify" })
	public void Verify_PurchaseReturnDn() throws InterruptedException, IOException, AWTException {
		PurchaseReturnDebitNote.verify_purchaseReturnWithDebitNote(documentid, productid, vendorid, driver);
	}

	@Test(priority = 272, groups = { "CED" })
	public void CopyEditDelete_PurchaseReturnDn() throws InterruptedException, IOException, AWTException {
		PurchaseReturn1.CopyEditDelete_PurchaseReturn(true, "PRDN" + documentid, productid, driver);
	}

	@Test(priority = 273, groups = { "Audit Trail" })
	public void AuditTrail_PurchaseReturnDn() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate13("PRDN" + documentid, "Purchase Return", driver);
	}

	// ----------------------------------------------------------------------------------------------------------
	// Purchase Return DebitNote Link PI

	@Test(priority = 275, groups = { "Create" })
	public void Create_PurchaseReturnDnPi() throws InterruptedException, IOException, AWTException {
		PurchaseReturn1.create_PurchaseReturnDebitNote(true, documentid, productid, vendorid, driver);
	}

	@Test(priority = 276, groups = { "Verify" })
	public void Verify_PurchaseReturnDnPi() throws InterruptedException, IOException, AWTException {
		PurchaseReturnDebitNote.verify_purchaseReturnWithDebitNote_linkPI(documentid, productid, vendorid, driver);
	}

	@Test(priority = 277, groups = { "CED" })
	public void CopyEditDelete_PurchaseReturnDnPi() throws InterruptedException, IOException, AWTException {
		PurchaseReturn1.CopyEditDelete_PurchaseReturn(true, "PRdnPI" + documentid, productid, driver);
	}

	@Test(priority = 278, groups = { "Audit Trail" })
	public void AuditTrail_PurchaseReturnDnPi() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate13("PRdnPI" + documentid, "Purchase Return", driver);
	}

	@Test(priority = 279, groups = { "Export" })
	public void Export_PurchaseReturnDnPi() throws InterruptedException, IOException, AWTException {
		PurchaseReturnOnly.Export_PurchaseReturn(driver);
	}

}
