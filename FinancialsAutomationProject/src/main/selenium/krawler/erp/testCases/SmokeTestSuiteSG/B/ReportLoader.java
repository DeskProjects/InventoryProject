package krawler.erp.testCases.SmokeTestSuiteSG.B;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import krawler.erp.page.AgingReportCustomer;
import krawler.erp.page.AgingReportVendor;
import krawler.erp.page.BalanceSheet;
import krawler.erp.page.BankReconciliation;
import krawler.erp.page.GeneralLedgerLoading;
import krawler.erp.page.SoAReportLoading;
import krawler.erp.page.TradingAndProfitLoss;
import krawler.erp.testCases.SmokeTestSuiteSG.B.BaseSetUp;

public class ReportLoader extends BaseSetUp {

	String totalCuYearEarnBalSheet = null;

	@Test(priority = 600, groups = { "Report" })
	public void aging_loading_vendor_PrintExport() throws InterruptedException, IOException, AWTException {
		AgingReportVendor.load_Aging_vendor(vendorid, driver);
		AgingReportVendor.AgingReportView_PrintExportVendor(vendorid, driver);
	}

	@Test(priority = 601, groups = { "Report" })
	public void aging_loading_customer_PrintExport() throws InterruptedException, IOException, AWTException {
		AgingReportCustomer.load_Aging_customer(vendorid, driver);
		AgingReportCustomer.AgingReportView_PrintExportCustomer(vendorid, driver);
	}

	@Test(priority = 603, groups = { "Report" })
	public void GeneralLedgerLoadingwithExpander() throws InterruptedException, IOException, AWTException {
		GeneralLedgerLoading.validate_GeneralLedgerLoading(driver);
	}

	@Test(priority = 604, groups = { "Report" })
	public void TrialBalanceLoading() throws InterruptedException, IOException, AWTException {
		GeneralLedgerLoading.validate_TrialBalanceLoading(driver);
	}

	@Test(priority = 605, groups = { "Report" })
	public void validate_ReportLoading() throws InterruptedException, IOException, AWTException {
		SoAReportLoading.validate_ReportLoading(driver);
	}

	@Test(priority = 606, groups = { "Report" })
	public void validate_soaReportLoading() throws InterruptedException, IOException, AWTException {
		SoAReportLoading.validate_common_accStatement(customerid, vendorid, driver);
		SoAReportLoading.validate_customer_accStatement(customerid, driver);
		SoAReportLoading.validate_vendor_accStatement(vendorid, driver);
	}

	@Test(priority = 720, groups = { "Report" })
	public void validate_BalanceSheet() throws InterruptedException, IOException, AWTException {
		totalCuYearEarnBalSheet = BalanceSheet.validate_BlanceSheet(driver);
	}

	@Test(priority = 721, groups = { "Report" })
	public void validate_PLcalculation() throws InterruptedException, IOException, AWTException {
		TradingAndProfitLoss.validate_Plcalculation(totalCuYearEarnBalSheet, driver);
	}

	@Test(priority = 725, groups = { "Report" })
	public void validate_BankReconciliation() throws InterruptedException, IOException, AWTException {
		String BankName = "BANK";
		String startDate = "2018-01-01";
		BankReconciliation.validate_BankReconciliation(BankName, startDate, driver);
	}

}
