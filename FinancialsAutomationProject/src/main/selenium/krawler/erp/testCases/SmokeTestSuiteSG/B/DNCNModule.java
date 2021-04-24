package krawler.erp.testCases.SmokeTestSuiteSG.B;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import krawler.erp.page.AuditTrailTest1;
import krawler.erp.page.CreditNoteCharged;
import krawler.erp.page.CreditNoteOtherwise;
import krawler.erp.page.CreditNoteSalesInvoice;
import krawler.erp.page.CreditNote_Vendor;
import krawler.erp.page.DebitNoteAgainstCustomer;
import krawler.erp.page.DebitNoteCharged;
import krawler.erp.page.DebitNoteOtherWise;
import krawler.erp.page.DebitNotePurchaseInvoice;

public class DNCNModule extends BaseSetUp {

	// ----------------------------------------------------------------------------------------------------------
	// DebitNote Otherwise

	@Test(priority = 400, groups = { "Create" })
	public void Create_Dnother() throws InterruptedException, IOException, AWTException {
		DebitNoteOtherWise.create_debitNoteOtherwise(documentid, productid, vendorid, driver);
	}

	@Test(priority = 401, groups = { "Verify" })
	public void Verify_Dnother() throws InterruptedException, IOException, AWTException {
		DebitNoteOtherWise.verify_debitNoteOtherwise(documentid, productid, vendorid, driver);
	}

	@Test(priority = 402, groups = { "CED" })
	public void CopyEditDelete_Dnother() throws InterruptedException, IOException, AWTException {
		DebitNoteOtherWise.CopyEditDelete_debitNoteOtherwise("DnO" + documentid, driver);
	}

	@Test(priority = 403, groups = { "Audit Trail" })
	public void AuditTrail_Dnother() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate9("DnO" + documentid, vendorid, "Debit Note", driver);
	}

	// ----------------------------------------------------------------------------------------------------------
	// DebitNote Purchase Invoice

	@Test(priority = 410, groups = { "Create" })
	public void Create_DnPurchaseInv() throws InterruptedException, IOException, AWTException {
		DebitNotePurchaseInvoice.create_debitNoteAgainstpurchaseInvoice(documentid, productid, vendorid, driver);
	}

	@Test(priority = 411, groups = { "Verify" })
	public void Verify_DnPurchaseInv() throws InterruptedException, IOException, AWTException {
		DebitNotePurchaseInvoice.verify_debitNoteAgainstpurchaseInvoice(documentid, productid, vendorid, driver);
	}

	@Test(priority = 412, groups = { "CED" })
	public void EditDelete_DnPurchaseInv() throws InterruptedException, IOException, AWTException {
		// DebitNotePurchaseInvoice.EditDelete_debitNoteAgainstpurchaseInvoice("DnPi"+documentid,
		// driver);
	}

	@Test(priority = 413, groups = { "Audit Trail" })
	public void AuditTrail_DnPurchaseInv() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate9("DnPi" + documentid, vendorid, "Debit Note", driver);
	}

	// ----------------------------------------------------------------------------------------------------------
	// DebitNote Customer

	@Test(priority = 420, groups = { "Create" })
	public void Create_DnCustomer() throws InterruptedException, IOException, AWTException {
		DebitNoteAgainstCustomer.create_debitNoteAgainstCustomer(documentid, productid, customerid, driver);
	}

	@Test(priority = 421, groups = { "Verify" })
	public void Verify_DnCustomer() throws InterruptedException, IOException, AWTException {
		DebitNoteAgainstCustomer.verify_debitNoteAgainstCustomer(documentid, productid, customerid, driver);
	}

	@Test(priority = 422, groups = { "CED" })
	public void CopyEditDelete_DnCustomer() throws InterruptedException, IOException, AWTException {
		DebitNoteAgainstCustomer.CopyEditDelete_debitNoteAgainstCustomer("DNcus" + documentid, driver);
	}

	@Test(priority = 423, groups = { "Audit Trail" })
	public void AuditTrail_DnCustomer() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate9("DNcus" + documentid, vendorid, "Debit Note", driver);
	}

	// -------------------------------------------------------------------------------------------------------------------------------
	// DebitNote Undercharge

	@Test(priority = 430, groups = { "Create" })
	public void Create_DnUnder() throws InterruptedException, IOException, AWTException {
		DebitNoteCharged.create_debitNoteUnderCharge(documentid, productid, customerid, "10", driver);
	}

	@Test(priority = 431, groups = { "Verify" })
	public void Verify_DnUnder() throws InterruptedException, IOException, AWTException {
		DebitNoteCharged.verify_DebitNoteCharged(documentid, "Undercharged", customerid, driver);
	}

	@Test(priority = 432, groups = { "CED" })
	public void CopyEditDelete_DnUnder() throws InterruptedException, IOException, AWTException {
		DebitNoteCharged.EditDelete_DebitNoteCharged("DnUC" + documentid, "UND", driver);
	}

	@Test(priority = 433, groups = { "Audit Trail" })
	public void AuditTrail_DnUnder() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate9("DnUC" + documentid, customerid, "Debit Note", driver);
	}

	// ----------------------------------------------------------------------------------------------------------------------

	// DebitNote Over

	@Test(priority = 440, groups = { "Create" })
	public void Create_DnOver() throws InterruptedException, IOException, AWTException {
		DebitNoteCharged.create_debitNoteOverCharge(documentid, productid, vendorid, "10", driver);
	}

	@Test(priority = 441, groups = { "Verify" })
	public void Verify_DnOver() throws InterruptedException, IOException, AWTException {
		DebitNoteCharged.verify_DebitNoteCharged(documentid, "Overcharged", vendorid, driver);
	}

	@Test(priority = 442, groups = { "CED" })
	public void CopyEditDelete_DnOver() throws InterruptedException, IOException, AWTException {
		DebitNoteCharged.EditDelete_DebitNoteCharged("DnOC" + documentid, "OVR", driver);
	}

	@Test(priority = 443, groups = { "Audit Trail" })
	public void AuditTrail_DnOver() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate9("DnOC" + documentid, vendorid, "Debit Note", driver);
	}

	@Test(priority = 444, groups = { "Export" })
	public void Export_DebitNote() throws InterruptedException, IOException, AWTException {
		DebitNoteCharged.Export_DebitNote("Export", driver);
	}

	// ****************************** Sales Side
	// ***********************************************************

	// Credit Note Sales Invoice

	@Test(priority = 450, groups = { "Create" })
	public void Create_CNInvoice() throws InterruptedException, IOException, AWTException {
		CreditNoteSalesInvoice.create_creditNoteAgainstSalesInvoice(documentid, productid, customerid, driver);
	}

	@Test(priority = 451, groups = { "Verify" })
	public void Verify_CNInvoice() throws InterruptedException, IOException, AWTException {
		CreditNoteSalesInvoice.verify_creditNoteAgainstSalesInvoice(documentid, productid, customerid, driver);
	}

	@Test(priority = 452, groups = { "CED" })
	public void CopyEditDelete_CNInvoice() throws InterruptedException, IOException, AWTException {
		CreditNoteSalesInvoice.EditDelete_creditNoteAgainstSalesInvoice(documentid, driver);
	}

	@Test(priority = 453, groups = { "Audit Trail" })
	public void AuditTrail_CNInvoice() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate9("CrSi" + documentid, customerid, "Credit Note", driver);
	}

	// Credit Note Sales Invoice
	@Test(priority = 460, groups = { "Create" })
	public void Create_CNVendor() throws InterruptedException, IOException, AWTException {
		CreditNote_Vendor.create_creditNoteAgainstvendor(documentid, productid, vendorid, driver);
	}

	@Test(priority = 461, groups = { "Verify" })
	public void Verify_CNVendor() throws InterruptedException, IOException, AWTException {
		CreditNote_Vendor.Verify_creditNoteAgainstvendor(documentid, productid, vendorid, driver);
	}

	@Test(priority = 462, groups = { "CED" })
	public void CopyEditDelete_CNVendor() throws InterruptedException, IOException, AWTException {
		CreditNote_Vendor.CopyEditDelete_creditNoteAgainstvendor(documentid, driver);
	}

	@Test(priority = 463, groups = { "Audit Trail" })
	public void AuditTrail_CNVendor() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate9("CrVen" + documentid, vendorid, "Credit Note", driver);
	}

	// Credit Note Undercharge

	@Test(priority = 470, groups = { "Create" })
	public void Create_CNUnder() throws InterruptedException, IOException, AWTException {
		CreditNoteCharged.create_CreditNoteUnderCharge(documentid, productid, vendorid, "10", driver);
	}

	@Test(priority = 471, groups = { "Verify" })
	public void Verify_CNUnder() throws InterruptedException, IOException, AWTException {
		CreditNoteCharged.verify_CreditNoteCharged(documentid, "Undercharged", vendorid, driver);
	}

	@Test(priority = 472, groups = { "CED" })
	public void CopyEditDelete_CNUnder() throws InterruptedException, IOException, AWTException {
		CreditNoteCharged.EditDelete_CreditNoteCharged("CnUC" + documentid, "UND", driver);
	}

	@Test(priority = 473, groups = { "Audit Trail" })
	public void AuditTrail_CNUnder() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate9("CnUC" + documentid, vendorid, "Credit Note", driver);
	}

	// Credit Note Over

	@Test(priority = 480, groups = { "Create" })
	public void Create_CNOver() throws InterruptedException, IOException, AWTException {
		CreditNoteCharged.create_CreditNoteOverCharge(documentid, productid, customerid, "10", driver);
	}

	@Test(priority = 481, groups = { "Verify" })
	public void Verify_CNOver() throws InterruptedException, IOException, AWTException {
		CreditNoteCharged.verify_CreditNoteCharged(documentid, "Overcharged", customerid, driver);
	}

	@Test(priority = 482, groups = { "CED" })
	public void CopyEditDelete_CNOver() throws InterruptedException, IOException, AWTException {
		CreditNoteCharged.EditDelete_CreditNoteCharged("CnOC" + documentid, "OVR", driver);
	}

	@Test(priority = 483, groups = { "Audit Trail" })
	public void AuditTrail_CNOver() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate9("CnOC" + documentid, customerid, "Credit Note", driver);
	}

	// Credit Note Otherwise

	@Test(priority = 490, groups = { "Create" })
	public void Create_CNOther() throws InterruptedException, IOException, AWTException {
		CreditNoteOtherwise.create_creditNoteotherwise(documentid, productid, customerid, driver);
	}

	@Test(priority = 491, groups = { "Verify" })
	public void Verify_CNOther() throws InterruptedException, IOException, AWTException {
		CreditNoteOtherwise.verify_creditNoteotherwise(documentid, productid, customerid, driver);
	}

	@Test(priority = 492, groups = { "CED" })
	public void CopyEditDelete_CNOther() throws InterruptedException, IOException, AWTException {
		CreditNoteOtherwise.CopyEditDelete_creditNoteotherwise(documentid, driver);
	}

	@Test(priority = 493, groups = { "Audit Trail" })
	public void AuditTrail_CNOther() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate9("CnOthr" + documentid, customerid, "Credit Note", driver);
	}

	@Test(priority = 494, groups = { "Export" })
	public void Export_CreditNote() throws InterruptedException, IOException, AWTException {
		CreditNoteCharged.Export_CreditNote("Export", driver);
	}

}
