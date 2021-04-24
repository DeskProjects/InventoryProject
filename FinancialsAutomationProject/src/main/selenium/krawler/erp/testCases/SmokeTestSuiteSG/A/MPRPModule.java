package krawler.erp.testCases.SmokeTestSuiteSG.A;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import krawler.erp.page.AuditTrailTest1;
import krawler.erp.page.ChartOfAccount;
import krawler.erp.page.FundTransferJE;
import krawler.erp.page.JENormal;
import krawler.erp.page.MakePaymentAgainstCustomer;
import krawler.erp.page.MakePaymentAgainstGL;
import krawler.erp.page.MakePaymentAginstVendor;
import krawler.erp.page.PartyJE;
import krawler.erp.page.ReceivePaymentAgainstCustomer;
import krawler.erp.page.ReceivePaymentAgainstGL;
import krawler.erp.page.ReceivePaymentAgainstVendor;
import krawler.erp.page.Utilities;

public class MPRPModule extends BaseSetUp {

	@Test(priority = 500, groups = { "Create" })
	public void Create_MPVednor() throws InterruptedException, IOException, AWTException {
		String AdvanceAmt = "150";
		String InvoiceAmt = "20";
		String CreditNoteAmt = "50";
		String GLAmt = "150";
		MakePaymentAginstVendor.create_MakePayment_Vendor(documentid, AdvanceAmt, InvoiceAmt, CreditNoteAmt, GLAmt,
				productid, vendorid, driver);
	}

	@Test(priority = 501, groups = { "Verify" })
	public void Verify_MPVednor() throws InterruptedException, IOException, AWTException {
		MakePaymentAginstVendor.verify_paymentMade(documentid, vendorid, payMethod, driver);
	}

	@Test(priority = 502, groups = { "CED" })
	public void CopyEditDelete_MPVednor() throws InterruptedException, IOException, AWTException {
		MakePaymentAginstVendor.EmailCopyEditDelete_MakePayment("MPvend" + documentid, driver);
	}

	@Test(priority = 503, groups = { "Audit Trail" })
	public void AuditTrail_MPVednor() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate10("MPvend" + documentid, "Payment", driver);
	}

	// Make Payment Customer

	@Test(priority = 510, groups = { "Create" })
	public void Create_MPCustomer() throws InterruptedException, IOException, AWTException {
		String RefunDepositAmt = "100";
		String CreditNoteAmt = "50";
		MakePaymentAgainstCustomer.MakePayment_Customer(documentid, customerid, RefunDepositAmt, CreditNoteAmt, driver);
	}

	@Test(priority = 511, groups = { "Verify" })
	public void Verify_MPCustomer() throws InterruptedException, IOException, AWTException {
		MakePaymentAgainstCustomer.verify_paymentMade(documentid, customerid, payMethod, driver);
	}

	@Test(priority = 512, groups = { "CED" })
	public void CopyEditDelete_MPCustomer() throws InterruptedException, IOException, AWTException {
		MakePaymentAginstVendor.EmailCopyEditDelete_MakePayment("MPCust" + documentid, driver);
	}

	@Test(priority = 513, groups = { "Audit Trail" })
	public void AuditTrail_MPCustomer() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate10("MPCust" + documentid, "Payment", driver);
	}

	// Make Payment GL

	@Test(priority = 520, groups = { "Create" })
	public void Create_MPGL() throws InterruptedException, IOException, AWTException {
		MakePaymentAgainstGL.create_makePayment_GL(documentid, documentid, driver);
	}

	@Test(priority = 521, groups = { "Verify" })
	public void Verify_MPGL() throws InterruptedException, IOException, AWTException {
		MakePaymentAgainstCustomer.verify_paymentMade("MPaGL" + documentid, customerid, payMethod, driver);
	}

	@Test(priority = 522, groups = { "CED" })
	public void CopyEditDelete_MPGL() throws InterruptedException, IOException, AWTException {
		MakePaymentAgainstCustomer.EmailCopyEditDelete_MakePayment_GL("MPaGL" + documentid, driver);
	}

	@Test(priority = 523, groups = { "Audit Trail" })
	public void AuditTrail_MPGL() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate10("MPaGL" + documentid, "Payment", driver);
	}

	@Test(priority = 524, groups = { "Export" })
	public void Export_MPGL() throws InterruptedException, IOException, AWTException {
		MakePaymentAginstVendor.Export_MakePayment(driver);
	}

	// ------------------------------------------------------------------------------------------------------------------------
	// Receive Payment Customer

	@Test(priority = 530, groups = { "Create" })
	public void Create_RPCustomer() throws InterruptedException, IOException, AWTException {
		String AdvanceAmt = "50";
		String InvoiceAmt = "50";
		String DebitNoteAmt = "50";
		String GLAmt = "50";
		ReceivePaymentAgainstCustomer.create_ReceivePaymentAgainstCustomer(documentid, AdvanceAmt, InvoiceAmt,
				DebitNoteAmt, GLAmt, productid, customerid, driver);
	}

	@Test(priority = 531, groups = { "Verify" })
	public void Verify_RPCustomer() throws InterruptedException, IOException, AWTException {
		// No code
	}

	@Test(priority = 532, groups = { "CED" })
	public void CopyEditDelete_RPCustomer() throws InterruptedException, IOException, AWTException {
		ReceivePaymentAgainstCustomer.EmailCopyEditDelete_ReceivePayment("RPcust" + documentid, driver);
	}

	@Test(priority = 533, groups = { "Audit Trail" })
	public void AuditTrail_RPCustomer() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate11("RPcust" + documentid, "Receipt", driver);
	}

	// Receive Payment Vendor

	@Test(priority = 540, groups = { "Create" })
	public void Create_RPVednor() throws InterruptedException, IOException, AWTException {
		String Refudamount = "50";
		ReceivePaymentAgainstVendor.create_ReceivePayment_Vendor(documentid, productid, Refudamount, vendorid, driver);
	}

	@Test(priority = 541, groups = { "Verify" })
	public void Verify_RPVednor() throws InterruptedException, IOException, AWTException {
		// No code
	}

	@Test(priority = 542, groups = { "CED" })
	public void CopyEditDelete_RPVednor() throws InterruptedException, IOException, AWTException {
		ReceivePaymentAgainstCustomer.EmailCopyEditDelete_ReceivePayment("RPVend" + documentid, driver);
	}

	@Test(priority = 543, groups = { "Audit Trail" })
	public void AuditTrail_RPVednor() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate11("RPVend" + documentid, "Receipt", driver);
	}

	// Receive Payment GL

	@Test(priority = 550, groups = { "Create" })
	public void Create_RPGL() throws InterruptedException, IOException, AWTException {
		ReceivePaymentAgainstGL RPV = new ReceivePaymentAgainstGL();
		RPV.create_ReceivePayment_AgainstGL(documentid, "Utilities", driver);
	}

	@Test(priority = 551, groups = { "Verify" })
	public void Verify_RPGL() throws InterruptedException, IOException, AWTException {
		// No code
	}

	@Test(priority = 552, groups = { "CED" })
	public void CopyEditDelete_RPGL() throws InterruptedException, IOException, AWTException {
		ReceivePaymentAgainstCustomer.EmailCopyEditDelete_ReceivePayment("RPGL" + documentid, driver);
	}

	@Test(priority = 553, groups = { "Audit Trail" })
	public void AuditTrail_RPGL() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate11("RPGL" + documentid, "Receipt", driver);
	}

	@Test(priority = 554, groups = { "Export" })
	public void Export_ReceivePay() throws InterruptedException, IOException, AWTException {
		ReceivePaymentAgainstVendor.Export_ReceivePayment(driver);
		Utilities.refresh();
	}

	// ***************************** JE
	// **********************************************

	@Test(priority = 560, groups = { "Create" })
	public void Create_JENormal() throws InterruptedException, IOException, AWTException {
		JENormal.create_normalJE(documentid, "Purchase" + documentid, "Sales" + documentid, driver);
	}

	@Test(priority = 561, groups = { "Verify" })
	public void Verify_JENormal() throws InterruptedException, IOException, AWTException {
		JENormal.verify_normalJE(documentid, driver);
	}

	@Test(priority = 562, groups = { "CED" })
	public void CopyEditDelete_JENormal() throws InterruptedException, IOException, AWTException {
		JENormal.CopyEditDelete_normalJE("Nje" + documentid, driver);
	}

	@Test(priority = 563, groups = { "Audit Trail" })
	public void AuditTrail_JENormal() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate12("Nje" + documentid, "Journal Entry", driver);
	}

	// Journal Entry Party

	@Test(priority = 570, groups = { "Create" })
	public void Create_JEParty() throws InterruptedException, IOException, AWTException {
		PartyJE.create_partyJE(documentid, customerid, vendorid, driver);
	}

	@Test(priority = 571, groups = { "Verify" })
	public void Verify_JEParty() throws InterruptedException, IOException, AWTException {
		PartyJE.verify_partyJE(documentid, driver);
	}

	@Test(priority = 572, groups = { "CED" })
	public void CopyEditDelete_JEParty() throws InterruptedException, IOException, AWTException {
		JENormal.CopyEditDelete_normalJE("Pje" + documentid, driver);
	}

	@Test(priority = 573, groups = { "Audit Trail" })
	public void AuditTrail_JEParty() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate12("Pje" + documentid, "Journal Entry", driver);
	}

	// Journal FundTransfer

	@Test(priority = 580, groups = { "Create" })
	public void Create_JEFund() throws InterruptedException, IOException, AWTException {
		FundTransferJE.create_fundTransferJE(documentid, productid, vendorid, "COA" + documentid, driver);
	}

	@Test(priority = 581, groups = { "Verify" })
	public void Verify_JEFund() throws InterruptedException, IOException, AWTException {
		FundTransferJE.verify_fundTransfer(documentid, "Description", driver);
	}

	@Test(priority = 582, groups = { "CED" })
	public void CopyEditDelete_JEFund() throws InterruptedException, IOException, AWTException {
		JENormal.CopyEditDelete_normalJE("FuTr" + documentid, driver);
	}

	@Test(priority = 583, groups = { "Audit Trail" })
	public void AuditTrail_JEFund() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate12("FuTr" + documentid, "Journal Entry", driver);
	}

	@Test(priority = 584, groups = { "Export" })
	public void Export_JE() throws InterruptedException, IOException, AWTException {
		JENormal.Export_JournalEntry(driver);
	}

	// *************************** PrintChq ************************************
	@Test(priority = 590, groups = { "Create" })
	public void create_makePayVendor_ChequePrint() throws InterruptedException, IOException, AWTException {
		MakePaymentAginstVendor.create_MakePayment_VendorAdvance_Bank_ChequePrint(documentid, productid, vendorid,
				payMethod, "chqno.123", driver);
	}

	@Test(priority = 591, groups = { "Create" })
	public void create_makePayCustomer_ChequePrint() throws InterruptedException, IOException, AWTException {
		// ("Cheque Print functionality MP Against Customer");
		MakePaymentAgainstCustomer.create_MakePayment_Customer_Bank_ChequePrint(documentid, productid, customerid,
				payMethod, "chqno.123", driver);
	}

	@Test(priority = 592, groups = { "Create" })
	public void create_makePaymentAgainstGL_ChequePrint() throws InterruptedException, IOException, AWTException {
		// ("Cheque Print functionality MP Against GL");
		String accType = "COADebit";
		MakePaymentAgainstGL.create_makePayment_GL_PrintCheque(documentid, customerid, accType, payMethod, "chqNo21",
				driver);
	}

}
