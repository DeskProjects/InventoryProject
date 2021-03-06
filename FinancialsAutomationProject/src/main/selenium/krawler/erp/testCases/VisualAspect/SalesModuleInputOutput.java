package krawler.erp.testCases.VisualAspect;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import krawler.erp.modules.VisualAspectCashPurchase;
import krawler.erp.modules.VisualAspectCashSales;
import krawler.erp.modules.VisualAspectCommonReports;

public class SalesModuleInputOutput {
	public static String documentid = "doc22jan";
	public static String customerid = "TestCustomer";
	public static String productid = "TestProduct";
	public static WebDriver driver;

	// **********************Cash Sales
	// Report***********************************************************
	protected String CS_expectedReport[] = { "$ 70.00", "$ 47.76", "$ 730.00", "SGD 365.00" };
	protected String CS_debitAcc[] = { "Cash in hand", "Discount Given", "Rounding Difference" };
	protected String CS_expectedDebitAmt[] = { "SGD 365.00", "SGD 35.00", "SGD 0.02" };
	protected String CS_creditAcc[] = { "Sales1", "GST(DS)@7.00%", "Sales2", "GST(SR)@7.00%" };
	protected String CS_expectedCreditAmt[] = { "SGD 188.23", "SGD 11.78", "SGD 187.90", "SGD 12.11" };
	// ----------------------- CS GL
	// details----------------------------------------------------------------
	protected String CS_GLdebitAccounts[] = { "Cash in hand", "Discount Given" };
	protected String CS_GLexpectedDebitAmt[] = { "SGD 365.00", "SGD 35.00" };
	protected String CS_GLcreditAccounts[] = { "Sales1", "Sales2" };
	protected String CS_GLexpectedcreditAmt[] = { "SGD 188.23", "SGD 187.90" };
	// ----------------------- CS Trial
	// Balance----------------------------------------------------------------
	protected String CS_TBaccounts[] = { "Sales1", "Sales2", "Cash in hand", "Discount Given" };
	// ----------------------- CS Profit Loss
	// ----------------------------------------------------------------
	protected String CS_PLaccounts[] = { "Sales1", "Sales1", "Discount Given" };
	// ----------------------- CS Balance sheet
	// ----------------------------------------------------------------
	protected String CS_BLaccounts[] = { "Cash in hand", "GST(DS)@7.00%", "GST(SR)@7.00%" };
	// ----------------------- CS SOA
	// ----------------------------------------------------------------
	protected double CS_SOAexpected = 730.00;

	// **********************Sales Invoice
	// Report***********************************************************
	protected String SI_expectedReport[] = { "$ 70.00", "$ 47.76", "$ 730.00", "SGD 365.00" };
	protected String SI_debitAcc[] = { "Trade Debtors", "Discount Given", "Rounding Difference" };
	protected String SI_expectedDebitAmt[] = { "SGD 365.00", "SGD 35.00", "SGD 0.02" };
	protected String SI_creditAcc[] = { "Sales1", "GST(DS)@7.00%", "Sales2", "GST(SR)@7.00%" };
	protected String SI_expectedCreditAmt[] = { "SGD 188.23", "SGD 11.78", "SGD 187.90", "SGD 12.11" };
	// ----------------------- SI GL
	// details----------------------------------------------------------------
	protected String SI_GLdebitAccounts[] = { "Trade Debtors", "Discount Given" };
	protected String SI_GLexpectedDebitAmt[] = { "SGD 365.00", "SGD 35.00" };
	protected String SI_GLcreditAccounts[] = { "Sales1", "Sales2" };
	protected String SI_GLexpectedcreditAmt[] = { "SGD 188.23", "SGD 187.90" };
	// ----------------------- SI Trial
	// Balance----------------------------------------------------------------
	protected String SI_TBaccounts[] = { "Sales1", "Sales2", "Trade Debtors", "Discount Given" };
	// ----------------------- SI Profit Loss
	// ----------------------------------------------------------------
	protected String SI_PLaccounts[] = { "Sales1", "Sales1", "Discount Given" };
	// ----------------------- SI Balance sheet
	// ----------------------------------------------------------------
	protected String SI_BLaccounts[] = { "Trade Debtors", "GST(DS)@7.00%", "GST(SR)@7.00%" };
	// ----------------------- SI SOA
	// ----------------------------------------------------------------
	protected double SI_SOAexpected = 730.00;
	// ----------------------- SI Expense Age Receivable
	// ----------------------------------------------------------------
	protected double SI_AgPexpected = 365.00;

}
