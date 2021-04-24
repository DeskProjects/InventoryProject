package krawler.erp.testCases.VisualAspect;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import krawler.erp.modules.VisualAspectCashSales;
import krawler.erp.modules.VisualAspectCommonReports;
import krawler.erp.modules.VisualAspectSalesInvoice;
import krawler.erp.page.Login;
import krawler.erp.page.Utilities;

public class SalesModule extends SalesModuleInputOutput {
	public static String JEnumber = "JE000010";

	// ------------------------ Cash Sales
	// ----------------------------------------------
	@Test(priority = 10)
	public void create_CashSales() throws InterruptedException, IOException, AWTException {
		VisualAspectCashSales.create_CashSale(documentid, productid, customerid, driver);
	}

	@Test(priority = 11)
	public void verify_CashSalesReports() throws InterruptedException, IOException, AWTException {
		String moduleName = "Cash Sales";
		// verify Report & JE
		VisualAspectCashSales.verify_CashSalesORInvoice("CS" + documentid, CS_expectedReport, CS_debitAcc,
				CS_expectedDebitAmt, CS_creditAcc, CS_expectedCreditAmt, driver);
		// verify GL report
		VisualAspectCommonReports.verify_GeneralLedger("CS" + documentid, moduleName, JEnumber, CS_GLdebitAccounts,
				CS_GLexpectedDebitAmt, CS_GLcreditAccounts, CS_GLexpectedcreditAmt, driver);
		System.out.println("\n");
		// verify TB report
		VisualAspectCommonReports.verify_TrialBalance("CP" + documentid, moduleName, CS_TBaccounts, driver);
		System.out.println("\n");
		// verify PL report
		VisualAspectCommonReports.verify_TradeProfitLoss("CS" + documentid, moduleName, CS_PLaccounts, driver);
		System.out.println("\n");
		// verify BL report
		VisualAspectCommonReports.verify_BalanceSheet("CS" + documentid, moduleName, CS_BLaccounts, driver);
		System.out.println("\n");
		// verify SOA vendor report
		VisualAspectCommonReports.verifySOA_Customer("CS" + documentid, moduleName, customerid, CS_SOAexpected, "debit",
				driver);
	}

	// ------------------------ Sales Invoice
	// ----------------------------------------------
	@Test(priority = 20)
	public void create_SalesInvoice() throws InterruptedException, IOException, AWTException {
		VisualAspectSalesInvoice.create_salesInvoice(documentid, productid, customerid, driver);
	}

	@Test(priority = 21)
	public void verify_SalesInvoiceReports() throws InterruptedException, IOException, AWTException {
		String moduleName = "Sales Invoice";
		// verify Report & JE
		VisualAspectCashSales.verify_CashSalesORInvoice("SI" + documentid, SI_expectedReport, SI_debitAcc,
				SI_expectedDebitAmt, SI_creditAcc, SI_expectedCreditAmt, driver);
		// verify GL report
		VisualAspectCommonReports.verify_GeneralLedger("SI" + documentid, moduleName, JEnumber, SI_GLdebitAccounts,
				SI_GLexpectedDebitAmt, SI_GLcreditAccounts, SI_GLexpectedcreditAmt, driver);
		System.out.println("\n");
		// verify TB report
		VisualAspectCommonReports.verify_TrialBalance("SI" + documentid, moduleName, SI_TBaccounts, driver);
		System.out.println("\n");
		// verify PL report
		VisualAspectCommonReports.verify_TradeProfitLoss("SI" + documentid, moduleName, SI_PLaccounts, driver);
		System.out.println("\n");
		// verify BL report
		VisualAspectCommonReports.verify_BalanceSheet("SI" + documentid, moduleName, SI_BLaccounts, driver);
		System.out.println("\n");
		// verify SOA vendor report
		VisualAspectCommonReports.verifySOA_Customer("SI" + documentid, moduleName, customerid, SI_SOAexpected, "debit",
				driver);
		System.out.println("\n");
		// verify Aging report
		VisualAspectCommonReports.verify_AgeReceivable("SI" + documentid, moduleName, customerid, SI_AgPexpected,
				driver);
	}

	@BeforeSuite(alwaysRun = true)
	public void OpenBrowser() throws InterruptedException, IOException, AWTException {
		driver = Login.loginERP("http://192.168.0.208:8080/dotcomsmoketesting/a/automationv10/", "admin", "1234", 3);
	}

	@BeforeMethod(alwaysRun = true)
	public void closeExpander() throws InterruptedException, AWTException, IOException {
		Utilities.disableExpander(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() throws AWTException, InterruptedException {
		System.out.println("\n");
	}
}
