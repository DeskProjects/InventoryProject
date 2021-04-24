package krawler.erp.testCases.VisualAspect;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import krawler.erp.inventory.InvUtilities;
import krawler.erp.modules.VisualAspectCashPurchase;
import krawler.erp.modules.VisualAspectCommonReports;
import krawler.erp.modules.VisualAspectMakePayment;
import krawler.erp.modules.VisualAspectPurchaseInvoice;
import krawler.erp.page.Login;
import krawler.erp.page.Utilities;

public class PurchaseModule extends PurchaseModuleInputOutput {
	public static String JEnumber = null;

	// ------------------------ Cash Purchase
	// ----------------------------------------------
	@Test(priority = 10)
	public void create_CashPurchase() throws InterruptedException, IOException, AWTException {
		VisualAspectCashPurchase.create_CashPurchase(documentid, productid, vendorid, driver);
	}

	@Test(priority = 11)
	public void verify_CashPurchaseReports() throws InterruptedException, IOException, AWTException {
		String moduleName = "Cash Purchase";
		// verify Report & JE
		VisualAspectCashPurchase.verify_CashPurchaseORInvoice("CP" + documentid, CP_expectedReport, CP_debitAcc,
				CP_expectedDebitAmt, CP_creditAcc, CP_expectedCreditAmt, driver);
		System.out.println("\n");
		// verify GL report
		VisualAspectCommonReports.verify_GeneralLedger("CP" + documentid, moduleName, JEnumber, CP_GLdebitAccounts,
				CP_GLexpectedDebitAmt, CP_GLcreditAccounts, CP_GLexpectedcreditAmt, driver);
		System.out.println("\n");
		// verify TB report
		VisualAspectCommonReports.verify_TrialBalance("CP" + documentid, moduleName, CP_TBaccounts, driver);
		System.out.println("\n");
		// verify PL report
		VisualAspectCommonReports.verify_TradeProfitLoss("CP" + documentid, moduleName, CP_PLaccounts, driver);
		System.out.println("\n");
		// verify BL report
		VisualAspectCommonReports.verify_BalanceSheet("CP" + documentid, moduleName, CP_BLaccounts, driver);
		System.out.println("\n");
		// verify SOA vendor report
		VisualAspectCommonReports.verifySOA_Vendor("CP" + documentid, moduleName, vendorid, CP_SOAexpected, "credit",
				driver);
	}

	// ------------------------ Cash Purchase - Expense
	// ----------------------------------------------
	@Test(priority = 20)
	public void create_CashPurchaseExpense() throws InterruptedException, IOException, AWTException {
		VisualAspectCashPurchase.create_CashPurchaseExpense(documentid, vendorid, driver);
	}

	@Test(priority = 21)
	public void verify_CPExpenseReports() throws InterruptedException, IOException, AWTException {
		String moduleName = "CP Expense";
		// verify Report & JE
		VisualAspectCashPurchase.verify_CashPurchaseORInvoice("CpExp" + documentid, CpExp_expectedReport,
				CpExp_debitAcc, CpExp_expectedDebitAmt, CpExp_creditAcc, CpExp_expectedCreditAmt, driver);
		System.out.println("\n");
		// verify GL report
		VisualAspectCommonReports.verify_GeneralLedger("CpExp" + documentid, moduleName, JEnumber,
				CpExp_GLdebitAccounts, CpExp_GLexpectedDebitAmt, CpExp_GLcreditAccounts, CpExp_GLexpectedcreditAmt,
				driver);
		System.out.println("\n");
		// verify TB report
		VisualAspectCommonReports.verify_TrialBalance("CpExp" + documentid, moduleName, CpExp_TBaccounts, driver);
		System.out.println("\n");
		// verify PL report
		VisualAspectCommonReports.verify_TradeProfitLoss("CpExp" + documentid, moduleName, CpExp_PLaccounts, driver);
		System.out.println("\n");
		// verify BL report
		VisualAspectCommonReports.verify_BalanceSheet("CpExp" + documentid, moduleName, CpExp_BLaccounts, driver);
		System.out.println("\n");
		// verify SOA vendor report
		VisualAspectCommonReports.verifySOA_Vendor("CpExp" + documentid, moduleName, vendorid, CpExp_SOAexpected,
				"credit", driver);
	}

	// ------------------------ Purchase Invoice
	// ----------------------------------------------
	@Test(priority = 30)
	public void create_PurchaseInvoice() throws InterruptedException, IOException, AWTException {
		VisualAspectPurchaseInvoice.create_PurchaseInvoice(documentid, productid, vendorid, driver);
	}

	@Test(priority = 31)
	public void verify_PurchaseInvoice() throws InterruptedException, IOException, AWTException {
		String moduleName = "Purchase Invoice";
		// verify Report & JE
		VisualAspectCashPurchase.verify_CashPurchaseORInvoice("PI" + documentid, PI_expectedReport, PI_debitAcc,
				PI_expectedDebitAmt, PI_creditAcc, PI_expectedCreditAmt, driver);
		System.out.println("\n");
		// verify GL report
		VisualAspectCommonReports.verify_GeneralLedger("PI" + documentid, moduleName, JEnumber, PI_GLdebitAccounts,
				PI_GLexpectedDebitAmt, PI_GLcreditAccounts, PI_GLexpectedcreditAmt, driver);
		System.out.println("\n");
		// verify TB report
		VisualAspectCommonReports.verify_TrialBalance("PI" + documentid, moduleName, PI_TBaccounts, driver);
		System.out.println("\n");
		// verify PL report
		VisualAspectCommonReports.verify_TradeProfitLoss("PI" + documentid, moduleName, PI_PLaccounts, driver);
		System.out.println("\n");
		// verify BL report
		VisualAspectCommonReports.verify_BalanceSheet("PI" + documentid, moduleName, PI_BLaccounts, driver);
		System.out.println("\n");
		// verify SOA vendor report
		VisualAspectCommonReports.verifySOA_Vendor("PI" + documentid, moduleName, vendorid, PI_SOAexpected, "credit",
				driver);
		// verify Age Payable
		VisualAspectCommonReports.verify_AgePayable("PI" + documentid, moduleName, vendorid, PI_AgPexpected, driver);
	}

	// ------------------------ Purchase Invoice - Expense
	// ----------------------------------------------
	@Test(priority = 40)
	public void create_PIExpense() throws InterruptedException, IOException, AWTException {
		VisualAspectPurchaseInvoice.create_PIExpense(documentid, vendorid, driver);
	}

	@Test(priority = 41)
	public void verify_PIExpense() throws InterruptedException, IOException, AWTException {
		String moduleName = "PI Expense";
		// verify Report & JE
		VisualAspectCashPurchase.verify_CashPurchaseORInvoice("PiExp" + documentid, PiExp_expectedReport,
				PiExp_debitAcc, PiExp_expectedDebitAmt, PiExp_creditAcc, PiExp_expectedCreditAmt, driver);
		System.out.println("\n");
		// verify GL report
		VisualAspectCommonReports.verify_GeneralLedger("PiExp" + documentid, moduleName, JEnumber,
				PiExp_GLdebitAccounts, PiExp_GLexpectedDebitAmt, PiExp_GLcreditAccounts, PiExp_GLexpectedcreditAmt,
				driver);
		System.out.println("\n");
		// verify TB report
		VisualAspectCommonReports.verify_TrialBalance("PiExp" + documentid, moduleName, PiExp_TBaccounts, driver);
		System.out.println("\n");
		// verify PL report
		VisualAspectCommonReports.verify_TradeProfitLoss("PiExp" + documentid, moduleName, PiExp_PLaccounts, driver);
		System.out.println("\n");
		// verify BL report
		VisualAspectCommonReports.verify_BalanceSheet("PiExp" + documentid, moduleName, PiExp_BLaccounts, driver);
		System.out.println("\n");
		// verify SOA vendor report
		VisualAspectCommonReports.verifySOA_Vendor("PiExp" + documentid, moduleName, vendorid, PiExp_SOAexpected,
				"credit", driver);
		// verify Age Payable
		VisualAspectCommonReports.verify_AgePayable("PiExp" + documentid, moduleName, vendorid, PiExp_AgPexpected,
				driver);
	}

	// ------------------------ Make Payment - Vendor
	// ----------------------------------------------
	@Test(priority = 50)
	public void createMP_vendor_Advance() throws InterruptedException, IOException, AWTException {
		String mpvendAdv[] = { "Advanced / Deposit" };
		String linkDocumentsAll[] = { "NA", "gggg", "gggg", "MPaccountGL" };
		VisualAspectMakePayment.createMP_vendor("MpVeAdv" + documentid, vendorid, mpvendAdv, linkDocumentsAll, "100",
				driver);
	}

	@Test(priority = 51)
	public void verifyMP_vendor_Advance() throws InterruptedException, IOException, AWTException {
		String moduleName = "MP Vendor : Advance";
		// verify Report & get JE
		JEnumber = VisualAspectMakePayment.getJEverify_makePayment("MpVeAdv" + documentid, Mven_report, driver);
		System.out.println("\n");
		// verify JE
		VisualAspectCommonReports.verify_JEreport(JEnumber, moduleName, Mven_debitAcc, Mven_expectedDebitAmt,
				Mven_creditAcc, Mven_expectedCreditAmt, driver);
		System.out.println("\n");
		// verify GL report
		VisualAspectCommonReports.verify_GeneralLedger("MpVeAdv" + documentid, moduleName, JEnumber,
				Mven_GLdebitAccounts, Mven_GLexpectedDebitAmt, Mven_GLcreditAccounts, Mven_GLexpectedcreditAmt, driver);
		System.out.println("\n");
		// verify TrialBalance report
		VisualAspectCommonReports.verify_TrialBalance("MpVeAdv" + documentid, moduleName, Mven_TBaccounts, driver);
		System.out.println("\n");
		// verify BL report
		VisualAspectCommonReports.verify_BalanceSheet("MpVeAdv" + documentid, moduleName, Mven_BLaccounts, driver);
		System.out.println("\n");
		// verify SOA vendor report
		VisualAspectCommonReports.verifySOA_Vendor("MpVeAdv" + documentid, moduleName, vendorid, Mven_SOAexpected,
				"debit", driver);
		// verify Age Payable
		VisualAspectCommonReports.verify_AgePayable("MpVeAdv" + documentid, moduleName, vendorid, Mven_AgPexpected,
				driver);
	}

	// --------------------- MP - Invoice
	// -------------------------------------------------------------
	@Test(priority = 60)
	public void createMP_vendor_Invoice() throws InterruptedException, IOException, AWTException {
		String mpvendPI[] = { "Invoice" };
		String linkDocumentsPI[] = { "Invoice" };
		VisualAspectMakePayment.createMP_vendor("MpVePI" + documentid, vendorid, mpvendPI, linkDocumentsPI, "50",
				driver);
	}

	@Test(priority = 61)
	public void verifyMP_vendor_Invoice() throws InterruptedException, IOException, AWTException {
		String moduleName = "MP Vendor : Invoice";
		// verify Report & get JE
		JEnumber = VisualAspectMakePayment.getJEverify_makePayment("MpVePI" + documentid, Mven2_report, driver);
		System.out.println("\n");
		// verify JE
		VisualAspectCommonReports.verify_JEreport(JEnumber, moduleName, Mven2_debitAcc, Mven2_expectedDebitAmt,
				Mven2_creditAcc, Mven2_expectedCreditAmt, driver);
		System.out.println("\n");
		// verify GL report
		VisualAspectCommonReports.verify_GeneralLedger("MpVePI" + documentid, moduleName, JEnumber,
				Mven2_GLdebitAccounts, Mven2_GLexpectedDebitAmt, Mven2_GLcreditAccounts, Mven2_GLexpectedcreditAmt,
				driver);
		System.out.println("\n");
		// verify TrialBalance report
		VisualAspectCommonReports.verify_TrialBalance("MpVePI" + documentid, moduleName, Mven2_TBaccounts, driver);
		System.out.println("\n");
		// verify BL report
		VisualAspectCommonReports.verify_BalanceSheet("MpVePI" + documentid, moduleName, Mven2_BLaccounts, driver);
		System.out.println("\n");
		// verify SOA vendor report
		VisualAspectCommonReports.verifySOA_Vendor("MpVePI" + documentid, moduleName, vendorid, Mven2_SOAexpected,
				"debit", driver);
		// verify Age Payable
		VisualAspectCommonReports.verify_AgePayable("Invoice", moduleName, vendorid, Mven2_AgPexpected, driver);
		// verify Invoice
		VisualAspectCommonReports.verify_CashPurchaseORInvoice("Invoice", Mven2_PIexpected, driver);
	}

	// --------------------- MP - Credit Note
	// -------------------------------------------------------------
	@Test(priority = 70)
	public void createMP_vendor_CreditNote() throws InterruptedException, IOException, AWTException {
		String mpvendCN[] = { "Credit Note" };
		String linkDocumentsCN[] = { "CreditNote" };
		VisualAspectMakePayment.createMP_vendor("MpVeCN" + documentid, vendorid, mpvendCN, linkDocumentsCN, "80",
				driver);
	}

	@Test(priority = 71)
	public void verifyMP_vendor_CreditNote() throws InterruptedException, IOException, AWTException {
		String moduleName = "MP Vendor : Credit Note";
		// verify Report & get JE
		JEnumber = VisualAspectMakePayment.getJEverify_makePayment("MpVeCN" + documentid, Mven3_report, driver);
		System.out.println("\n");
		// verify JE
		VisualAspectCommonReports.verify_JEreport(JEnumber, moduleName, Mven3_debitAcc, Mven3_expectedDebitAmt,
				Mven3_creditAcc, Mven3_expectedCreditAmt, driver);
		System.out.println("\n");
		// verify GL report
		VisualAspectCommonReports.verify_GeneralLedger("MpVeCN" + documentid, moduleName, JEnumber,
				Mven3_GLdebitAccounts, Mven3_GLexpectedDebitAmt, Mven3_GLcreditAccounts, Mven3_GLexpectedcreditAmt,
				driver);
		System.out.println("\n");
		// verify TrialBalance report
		VisualAspectCommonReports.verify_TrialBalance("MpVeCN" + documentid, moduleName, Mven3_TBaccounts, driver);
		System.out.println("\n");
		// verify BL report
		VisualAspectCommonReports.verify_BalanceSheet("MpVeCN" + documentid, moduleName, Mven3_BLaccounts, driver);
		System.out.println("\n");
		// verify SOA vendor report
		VisualAspectCommonReports.verifySOA_Vendor("MpVeCN" + documentid, moduleName, vendorid, Mven3_SOAexpected,
				"debit", driver);
		System.out.println("\n");
		// verify Age Payable
		VisualAspectCommonReports.verify_AgePayable("CreditNote", moduleName, vendorid, Mven3_AgPexpected, driver);
		System.out.println("\n");
		// verify CN report
		VisualAspectCommonReports.verify_CreditNote("CreditNote", "Credit Note for Vendors", Mven3_CNexpected, driver);
		System.out.println("\n");
	}

	// --------------------- MP - Adv, PI ,CN & GL
	// -------------------------------------------------------------
	@Test(priority = 80)
	public void createMP_vendor_Glall() throws InterruptedException, IOException, AWTException {
		String mpvendALL[] = { "Advanced / Deposit", "Invoice", "Credit Note", "General Ledger Code" };
		String linkDocumentsAll[] = { "NA", "Invoice", "CreditNote", "MPaccountGL" };
		VisualAspectMakePayment.createMP_vendor("MpVeGL" + documentid, vendorid, mpvendALL, linkDocumentsAll, "100",
				driver);
	}

	@Test(priority = 81)
	public void verifyMP_vendor_Glall() throws InterruptedException, IOException, AWTException {
		String moduleName = "MP Vendor:-GL with Adv, PI & CN";
		// verify Report & get JE
		JEnumber = VisualAspectMakePayment.getJEverify_makePayment("MpVeGL" + documentid, Mven4_report, driver);
		System.out.println("\n");
		// verify JE
		VisualAspectCommonReports.verify_JEreport(JEnumber, moduleName, Mven4_debitAcc, Mven4_expectedDebitAmt,
				Mven4_creditAcc, Mven4_expectedCreditAmt, driver);
		System.out.println("\n");
		// verify GL report
		VisualAspectCommonReports.verify_GLreportVersion2("MpVeGL" + documentid, moduleName, JEnumber,
				Mven4_GLdebitAccounts, Mven4_GLexpectedDebitAmt, Mven4_GLcreditAccounts, Mven4_GLexpectedcreditAmt,
				driver);
		System.out.println("\n");
		// verify TrialBalance report
		VisualAspectCommonReports.verify_TrialBalance("MpVeGL" + documentid, moduleName, Mven4_TBaccounts, driver);
		System.out.println("\n");
		// verify BL report
		VisualAspectCommonReports.verify_BalanceSheet("MpVeGL" + documentid, moduleName, Mven4_BLaccounts, driver);
		System.out.println("\n");
		// verify SOA vendor report
		VisualAspectCommonReports.verifySOA_Vendor("MpVeGL" + documentid, moduleName, vendorid, Mven4_SOAexpected,
				"debit", driver);
		System.out.println("\n");
		// verify Age Payable - Cn
		VisualAspectCommonReports.verify_AgePayable("CreditNote", moduleName, vendorid, Mven4_AgPexpectedCN, driver);
		System.out.println("\n");
		// verify CN report
		VisualAspectCommonReports.verify_CreditNote("CreditNote", "Credit Note for Vendors", Mven4_CNexpected, driver);
		System.out.println("\n");
		// verify Age Payable - PI
		VisualAspectCommonReports.verify_AgePayable("Invoice", moduleName, vendorid, Mven4_AgPexpectedPI, driver);
		System.out.println("\n");
		// verify Invoice
		VisualAspectCommonReports.verify_CashPurchaseORInvoice("Invoice", Mven4_PIexpected, driver);
	}

	@Test(priority = 90)
	public void createMP_customer_Refund() throws InterruptedException, IOException, AWTException {
		String mpcustRef[] = { "Refund / Deposit" };
		String linkDocuments[] = { "NA" };
		ExpectedPaidAmount = VisualAspectMakePayment.createMP_customer("MpCuRef" + documentid, customerid, mpcustRef,
				linkDocuments, "100", driver);
	}

	@Test(priority = 91)
	public void verifyMP_customer_Refund() throws InterruptedException, IOException, AWTException {
		String moduleName = "MP Customer:-Refund";
		// verify Report & get JE
		JEnumber = VisualAspectMakePayment.getJEverify_makePayment("MpCuRef" + documentid, Mcus1_report, driver);
		System.out.println("\n");
		// verify JE
		VisualAspectCommonReports.verify_JEreport(JEnumber, moduleName, Mcus1_debitAcc, Mcus1_expectedDebitAmt,
				Mcus1_creditAcc, Mcus1_expectedCreditAmt, driver);
		System.out.println("\n");
		// verify GL report
		VisualAspectCommonReports.verify_GLreportVersion2("MpCuRef" + documentid, moduleName, JEnumber,
				Mcus1_GLdebitAccounts, Mcus1_GLexpectedDebitAmt, Mcus1_GLcreditAccounts, Mcus1_GLexpectedcreditAmt,
				driver);
		System.out.println("\n");
		// verify TrialBalance report
		VisualAspectCommonReports.verify_TrialBalance("MpCuRef" + documentid, moduleName, Mcus1_TBaccounts, driver);
		System.out.println("\n");
		// verify BL report
		VisualAspectCommonReports.verify_BalanceSheet("MpCuRef" + documentid, moduleName, Mcus1_BLaccounts, driver);
		System.out.println("\n");
		// verify SOA vendor report
		VisualAspectCommonReports.verifySOA_Customer("MpCuRef" + documentid, moduleName, customerid, Mcus1_SOAexpected,
				"debit", driver);
		// verify Age Rec report
		VisualAspectCommonReports.verify_AgeReceivable("MpCuRef" + documentid, moduleName, customerid, Mcus1_AgRecRef,
				driver);
	}

	@Test(priority = 100)
	public void createMP_customer_CreditNote() throws InterruptedException, IOException, AWTException {
		String mpcustCN[] = { "Credit Note" };
		String linkDocuments[] = { "CrditNoteOther" };
		ExpectedPaidAmount2 = VisualAspectMakePayment.createMP_customer("MpCuCrN" + documentid, customerid, mpcustCN,
				linkDocuments, "100", driver);
	}

	@Test(priority = 101)
	public void verifyMP_customer_CreditNote() throws InterruptedException, IOException, AWTException {
		String moduleName = "MP Customer:-Credit Note";
		// verify Report & get JE
		JEnumber = VisualAspectMakePayment.getJEverify_makePayment("MpCuCrN" + documentid, Mcus2_report, driver);
		System.out.println("\n");
		// verify JE
		VisualAspectCommonReports.verify_JEreport(JEnumber, moduleName, Mcus2_debitAcc, Mcus2_expectedDebitAmt,
				Mcus2_creditAcc, Mcus2_expectedCreditAmt, driver);
		System.out.println("\n");
		// verify GL report
		VisualAspectCommonReports.verify_GLreportVersion2("MpCuCrN" + documentid, moduleName, JEnumber,
				Mcus2_GLdebitAccounts, Mcus2_GLexpectedDebitAmt, Mcus2_GLcreditAccounts, Mcus2_GLexpectedcreditAmt,
				driver);
		System.out.println("\n");
		// verify TrialBalance report
		VisualAspectCommonReports.verify_TrialBalance("MpCuCrN" + documentid, moduleName, Mcus2_TBaccounts, driver);
		System.out.println("\n");
		// verify BL report
		VisualAspectCommonReports.verify_BalanceSheet("MpCuCrN" + documentid, moduleName, Mcus2_BLaccounts, driver);
		System.out.println("\n");
		// verify SOA vendor report
		VisualAspectCommonReports.verifySOA_Customer("MpCuCrN" + documentid, moduleName, customerid, Mcus2_SOAexpected,
				"debit", driver);
		System.out.println("\n");
		// verify CN report
		VisualAspectCommonReports.verify_CreditNote("CrditNoteOther", "Credit Note for Customers", Mcus2_CNexpected,
				driver);
		System.out.println("\n");
		// verify Age Rec report
		VisualAspectCommonReports.verify_AgeReceivable("CrditNoteOther", moduleName, customerid, Mcus2_AgRecCN, driver);
	}

	@Test(priority = 110)
	public void createMP_GL() throws InterruptedException, IOException, AWTException {
		String mpcustGL[] = { "General Ledger Code" };
		String linkDocuments[] = { "MPaccountGL" };
		ExpectedPaidAmountGL = VisualAspectMakePayment.createMP_GL("MPGL" + documentid, "Amol", mpcustGL, linkDocuments,
				"100", driver);
	}

	@Test(priority = 111)
	public void verifyMP_GL() throws InterruptedException, IOException, AWTException {
		String moduleName = "MP - General Ledger";
		// verify Report & get JE
		JEnumber = VisualAspectMakePayment.getJEverify_makePayment("MPGL" + documentid, MPGL_report, driver);
		System.out.println("\n");
		// verify JE
		VisualAspectCommonReports.verify_JEreport(JEnumber, moduleName, MPGL_debitAcc, MPGL_expectedDebitAmt,
				MPGL_creditAcc, MPGL_expectedCreditAmt, driver);
		System.out.println("\n");
		// verify GL report
		VisualAspectCommonReports.verify_GLreportVersion2("MPGL" + documentid, moduleName, JEnumber,
				MPGL_GLdebitAccounts, MPGL_GLexpectedDebitAmt, MPGL_GLcreditAccounts, MPGL_GLexpectedcreditAmt, driver);
		System.out.println("\n");
		// verify TrialBalance report
		VisualAspectCommonReports.verify_TrialBalance("MPGL" + documentid, moduleName, MPGL_TBaccounts, driver);
		System.out.println("\n");
		// verify BL report
		VisualAspectCommonReports.verify_BalanceSheet("MPGL" + documentid, moduleName, MPGL_BLaccounts, driver);
		System.out.println("\n");

	}

	@BeforeSuite(alwaysRun = true)
	public void OpenBrowser() throws InterruptedException, IOException, AWTException {
		driver = Login.loginERP("http://192.168.0.208:8080/dotcomsmoketesting/a/automationv10/", "admin", "1234", 1);
		Utilities.isLoadingDisappear(120, driver);
	}

	@BeforeMethod(alwaysRun = true)
	public void closeExpander() throws InterruptedException, AWTException, IOException {
		Utilities.disableExpander(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		System.out.println("\n");
	}

}
