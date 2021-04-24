package krawler.erp.testCases.VisualAspect;

import org.openqa.selenium.WebDriver;

public class PurchaseModuleInputOutput {

	public static String documentid = "doc28jan";
	public static String vendorid = "TestVendor";
	public static String customerid = "TestCustomer";
	public static String productid = "TestProduct";
	public static WebDriver driver;

	// **********************Cash Purchase
	// Report***********************************************************
	protected String CP_expectedReport[] = { "$ 50.00", "$ 24.50", "$ 374.50", "SGD 187.25" };
	protected String CP_debitAcc[] = { "Purchases1", "GST(BL)@7.00%", "Purchases2", "GST(IM)@7.00%" };
	protected String CP_expectedDebitAmt[] = { "SGD 100.00", "SGD 6.30", "SGD 100.00", "SGD 5.95" };
	protected String CP_creditAcc[] = { "Cash in hand", "Discount Received" };
	protected String CP_expectedCreditAmt[] = { "SGD 187.25", "SGD 25.00" };
	// ----------------------- CP GL
	// details----------------------------------------------------------------
	protected String CP_GLdebitAccounts[] = { "Purchases1", "Purchases2" };
	protected String CP_GLexpectedDebitAmt[] = { "SGD 100.00", "SGD 100.00" };
	protected String CP_GLcreditAccounts[] = { "Cash in hand", "Discount Received" };
	protected String CP_GLexpectedcreditAmt[] = { "SGD 187.25", "SGD 25.00" };
	// ----------------------- CP Trial
	// Balance----------------------------------------------------------------
	protected String CP_TBaccounts[] = { "Purchases1", "Purchases2", "Cash in hand", "Discount Received" };
	// ----------------------- CP Profit Loss
	// ----------------------------------------------------------------
	protected String CP_PLaccounts[] = { "Discount Received", "Purchases1", "Purchases2" };
	// ----------------------- CP Balance sheet
	// ----------------------------------------------------------------
	protected String CP_BLaccounts[] = { "Cash in hand", "GST(BL)@7.00%", "GST(IM)@7.00%" };
	// ----------------------- CP SOA
	// ----------------------------------------------------------------
	protected double CP_SOAexpected = 374.50;

	// **********************Cash Purchase - Expense
	// Report***********************************************************
	protected String CpExp_expectedReport[] = { "$ 70.00", "$ 34.67", "$ 530.00", "SGD 265.00" };
	protected String CpExp_debitAcc[] = { "GST(BL)@7.00%", "AutomationAsset", "GST(IM)@7.00%", "AutomationLiability" };
	protected String CpExp_expectedDebitAmt[] = { "SGD 11.78", "SGD 188.23", "SGD 5.56", "SGD 94.44" };
	protected String CpExp_creditAcc[] = { "Cash in hand", "Discount Received", "Rounding Difference" };
	protected String CpExp_expectedCreditAmt[] = { "SGD 265.00", "SGD 35.00", "SGD 0.01" };
	// ----------------------- CP Expense GL
	// details----------------------------------------------------------------
	protected String CpExp_GLdebitAccounts[] = { "AutomationAsset", "AutomationLiability" };
	protected String CpExp_GLexpectedDebitAmt[] = { "SGD 188.23", "SGD 94.44" };
	protected String CpExp_GLcreditAccounts[] = { "Cash in hand", "Discount Received", "Rounding Difference" };
	protected String CpExp_GLexpectedcreditAmt[] = { "SGD 265.00", "SGD 35.00", "SGD 0.01" };
	// ----------------------- CP Expense Trial
	// Balance----------------------------------------------------------------
	protected String CpExp_TBaccounts[] = { "AutomationAsset", "AutomationLiability", "Cash in hand",
			"Discount Received" };
	// ----------------------- CP Expense Profit Loss
	// ----------------------------------------------------------------
	protected String CpExp_PLaccounts[] = { "Discount Received", "Rounding Difference" };
	// ----------------------- CP Expense Balance sheet
	// ----------------------------------------------------------------
	protected String CpExp_BLaccounts[] = { "AutomationAsset", "AutomationLiability", "GST(BL)@7.00%",
			"GST(IM)@7.00%" };
	// ----------------------- CP Expense SOA
	// ----------------------------------------------------------------
	protected double CpExp_SOAexpected = 530.00;

	// **********************Purchase Invoice
	// Report***********************************************************
	protected String PI_expectedReport[] = { "$ 50.00", "$ 24.50", "$ 374.50", "SGD 187.25" };
	protected String PI_debitAcc[] = { "Purchases1", "GST(BL)@7.00%", "Purchases2", "GST(IM)@7.00%" };
	protected String PI_expectedDebitAmt[] = { "SGD 100.00", "SGD 6.30", "SGD 100.00", "SGD 5.95" };
	protected String PI_creditAcc[] = { "Trade Creditors", "Discount Received" };
	protected String PI_expectedCreditAmt[] = { "SGD 187.25", "SGD 25.00" };
	// ----------------------- CP GL
	// details----------------------------------------------------------------
	protected String PI_GLdebitAccounts[] = { "Purchases1", "Purchases2" };
	protected String PI_GLexpectedDebitAmt[] = { "SGD 100.00", "SGD 100.00" };
	protected String PI_GLcreditAccounts[] = { "Trade Creditors", "Discount Received" };
	protected String PI_GLexpectedcreditAmt[] = { "SGD 187.25", "SGD 25.00", "" };
	// ----------------------- CP Trial
	// Balance----------------------------------------------------------------
	protected String PI_TBaccounts[] = { "Purchases1", "Purchases2", "Trade Creditors", "Discount Received" };
	// ----------------------- CP Profit Loss
	// ----------------------------------------------------------------
	protected String PI_PLaccounts[] = { "Discount Received", "Purchases1", "Purchases2" };
	// ----------------------- CP Balance sheet
	// ----------------------------------------------------------------
	protected String PI_BLaccounts[] = { "Trade Creditors", "GST(BL)@7.00%", "GST(IM)@7.00%" };
	// ----------------------- CP SOA
	// ----------------------------------------------------------------
	protected double PI_SOAexpected = 374.50;
	// ----------------------- PI Expense Age Payable
	// ----------------------------------------------------------------
	protected double PI_AgPexpected = 374.50;

	// **********************Purchase Invoice - Expense
	// Report***********************************************************
	protected String PiExp_expectedReport[] = { "$ 70.00", "$ 34.67", "$ 530.00", "SGD 265.00" };
	protected String PiExp_debitAcc[] = { "GST(BL)@7.00%", "AutomationAsset", "GST(IM)@7.00%", "AutomationLiability" };
	protected String PiExp_expectedDebitAmt[] = { "SGD 11.78", "SGD 188.23", "SGD 5.56", "SGD 94.44" };
	protected String PiExp_creditAcc[] = { "Trade Creditors", "Discount Received", "Rounding Difference" };
	protected String PiExp_expectedCreditAmt[] = { "SGD 265.00", "SGD 35.00", "SGD 0.01" };
	// ----------------------- PI Expense GL
	// details----------------------------------------------------------------
	protected String PiExp_GLdebitAccounts[] = { "AutomationAsset", "AutomationLiability" };
	protected String PiExp_GLexpectedDebitAmt[] = { "SGD 188.23", "SGD 94.44" };
	protected String PiExp_GLcreditAccounts[] = { "Trade Creditors", "Discount Received", "Rounding Difference" };
	protected String PiExp_GLexpectedcreditAmt[] = { "SGD 265.00", "SGD 35.00", "SGD 0.01" };
	// ----------------------- PI Expense Trial
	// Balance----------------------------------------------------------------
	protected String PiExp_TBaccounts[] = { "AutomationAsset", "AutomationLiability", "Trade Creditors",
			"Discount Received" };
	// ----------------------- PI Expense Profit Loss
	// ----------------------------------------------------------------
	protected String PiExp_PLaccounts[] = { "Discount Received", "Rounding Difference" };
	// ----------------------- PI Expense Balance sheet
	// ----------------------------------------------------------------
	protected String PiExp_BLaccounts[] = { "AutomationAsset", "AutomationLiability", "Trade Creditors",
			"GST(BL)@7.00%", "GST(IM)@7.00%" };
	// ----------------------- PI Expense SOA
	// ----------------------------------------------------------------
	protected double PiExp_SOAexpected = 530.00;
	// ----------------------- PI Expense Age Payable
	// ----------------------------------------------------------------
	protected double PiExp_AgPexpected = 530.00;

	// ************************************* MAKE PAYMENT
	// ******************************************************************
	// --------------------------------------- Against Vendor [Advance / Deposit
	// ] -------------------------------------------------------------
	// verify Report
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected double[] Mven_report = { 100, 100 };
	// verify JE
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected String[] Mven_debitAcc = { "Trade Creditors" };
	protected double[] Mven_expectedDebitAmt = { 50 };
	protected String[] Mven_creditAcc = { "Cash in hand" };
	protected double[] Mven_expectedCreditAmt = { 50 };
	// verify GL
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected String Mven_GLdebitAccounts[] = { "Trade Creditors" };
	protected String Mven_GLexpectedDebitAmt[] = { "SGD 50.00" };
	protected String Mven_GLcreditAccounts[] = { "Cash in hand" };
	protected String Mven_GLexpectedcreditAmt[] = { "SGD 50.00" };
	// ----------------------- Trial
	// Balance----------------------------------------------------------------
	protected String Mven_TBaccounts[] = { "Trade Creditors", "Cash in hand" };
	// ----------------------- Balance sheet
	// ----------------------------------------------------------------
	protected String Mven_BLaccounts[] = { "Trade Creditors", "Cash in hand" };
	// ----------------------- SOA
	// ----------------------------------------------------------------
	protected double Mven_SOAexpected = 100.00;
	// ----------------------- Age Payable
	// ----------------------------------------------------------------
	protected double Mven_AgPexpected = 100.00;

	// --------------------------------------- Against Vendor [ Invoice ]
	// -------------------------------------------------------------
	// verify Report
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected double[] Mven2_report = { 0, 50 };
	// verify JE
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected String[] Mven2_debitAcc = { "Trade Creditors" };
	protected double[] Mven2_expectedDebitAmt = { 25 };
	protected String[] Mven2_creditAcc = { "Cash in hand" };
	protected double[] Mven2_expectedCreditAmt = { 25 };
	// verify GL
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected String Mven2_GLdebitAccounts[] = { "Trade Creditors" };
	protected String Mven2_GLexpectedDebitAmt[] = { "SGD 25.00" };
	protected String Mven2_GLcreditAccounts[] = { "Cash in hand" };
	protected String Mven2_GLexpectedcreditAmt[] = { "SGD 25.00" };
	// ----------------------- Trial
	// Balance----------------------------------------------------------------
	protected String Mven2_TBaccounts[] = { "Trade Creditors", "Cash in hand" };
	// ----------------------- Balance sheet
	// ----------------------------------------------------------------
	protected String Mven2_BLaccounts[] = { "Trade Creditors", "Cash in hand" };
	// ----------------------- SOA
	// ----------------------------------------------------------------
	protected double Mven2_SOAexpected = 50.00;
	// ----------------------- Age Payable
	// ----------------------------------------------------------------
	protected double Mven2_AgPexpected = 300.00;
	// ----------------------- Age Payable
	// ----------------------------------------------------------------
	protected double Mven2_PIexpected = 300.00;

	// --------------------------------------- Against Vendor [ Credit Note ]
	// -------------------------------------------------------------
	// verify Report
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected double[] Mven3_report = { 0, 80 };
	// verify JE
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected String[] Mven3_debitAcc = { "Trade Creditors" };
	protected double[] Mven3_expectedDebitAmt = { 40 };
	protected String[] Mven3_creditAcc = { "Cash in hand" };
	protected double[] Mven3_expectedCreditAmt = { 40 };
	// verify GL
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected String Mven3_GLdebitAccounts[] = { "Trade Creditors" };
	protected String Mven3_GLexpectedDebitAmt[] = { "SGD 40.00" };
	protected String Mven3_GLcreditAccounts[] = { "Cash in hand" };
	protected String Mven3_GLexpectedcreditAmt[] = { "SGD 40.00" };
	// ----------------------- Trial
	// Balance----------------------------------------------------------------
	protected String Mven3_TBaccounts[] = { "Trade Creditors", "Cash in hand" };
	// ----------------------- Balance sheet
	// ----------------------------------------------------------------
	protected String Mven3_BLaccounts[] = { "Trade Creditors", "Cash in hand" };
	// ----------------------- SOA
	// ----------------------------------------------------------------
	protected double Mven3_SOAexpected = 80.00;
	// ----------------------- Age Payable
	// ----------------------------------------------------------------
	protected double Mven3_AgPexpected = 420.00;
	// ----------------------- Age Payable
	// ----------------------------------------------------------------
	protected double Mven3_CNexpected = 420.00;

	// ------------------Against Vendor [ Adv, PI, CN & GL ]
	// -------------------------------------------------------------
	// verify Report
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected double[] Mven4_report = { 100, 400 };
	// verify JE
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected String[] Mven4_debitAcc = { "Trade Creditors", "MPaccountGL" };
	protected double[] Mven4_expectedDebitAmt = { 50, 50 };
	protected String[] Mven4_creditAcc = { "Cash in hand" };
	protected double[] Mven4_expectedCreditAmt = { 200 };
	// verify GL
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected String Mven4_GLdebitAccounts[] = { "Trade Creditors", "MPaccountGL" };
	protected double Mven4_GLexpectedDebitAmt[] = { 150, 50 };
	protected String Mven4_GLcreditAccounts[] = { "Cash in hand" };
	protected double Mven4_GLexpectedcreditAmt[] = { 200 };
	// ----------------------- Trial
	// Balance----------------------------------------------------------------
	protected String Mven4_TBaccounts[] = { "Trade Creditors", "Cash in hand" };
	// ----------------------- Balance sheet
	// ----------------------------------------------------------------
	protected String Mven4_BLaccounts[] = { "Trade Creditors", "Cash in hand" };
	// ----------------------- SOA
	// ----------------------------------------------------------------
	protected double Mven4_SOAexpected = 400.00;
	// ----------------------- Age Payable
	// ----------------------------------------------------------------
	protected double Mven4_AgPexpectedCN = 320.00;
	// ----------------------- Credit Note report
	// ----------------------------------------------------------------
	protected double Mven4_CNexpected = 320.00;
	// ----------------------- Age Payable
	// PI----------------------------------------------------------------
	protected double Mven4_AgPexpectedPI = 200.00;
	// ----------------------- Age Payable
	// ----------------------------------------------------------------
	protected double Mven4_PIexpected = 200.00;

	// --------------------------------------- Against Customer [Refund /
	// Deposit ] -------------------------------------------------------------
	// ----------------------- Create & return amount
	// ----------------------------------------------------------------
	protected double ExpectedPaidAmount = 0;
	// verify Report
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected double[] Mcus1_report = { 100, 100 };
	// verify JE
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected String[] Mcus1_debitAcc = { "Trade Debtors" };
	protected double[] Mcus1_expectedDebitAmt = { 50 };
	protected String[] Mcus1_creditAcc = { "Cash in hand" };
	protected double[] Mcus1_expectedCreditAmt = { 50 };
	// verify GL
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected String Mcus1_GLdebitAccounts[] = { "Trade Debtors" };
	protected double Mcus1_GLexpectedDebitAmt[] = { 50 };
	protected String Mcus1_GLcreditAccounts[] = { "Cash in hand" };
	protected double Mcus1_GLexpectedcreditAmt[] = { 50 };
	// ----------------------- Trial
	// Balance----------------------------------------------------------------
	protected String Mcus1_TBaccounts[] = { "Trade Debtors", "Cash in hand" };
	// ----------------------- Balance sheet
	// ----------------------------------------------------------------
	protected String Mcus1_BLaccounts[] = { "Trade Debtors", "Cash in hand" };
	// ----------------------- SOA
	// ----------------------------------------------------------------
	protected double Mcus1_SOAexpected = 100.00;
	// ----------------------- Age
	// Rec----------------------------------------------------------------
	protected double Mcus1_AgRecRef = 100;

	// ----------------------- CREDIT NOTE
	// ----------------------------------------------------------------
	protected double ExpectedPaidAmount2 = 0;
	// verify Report
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected double[] Mcus2_report = { 0, 100 };
	// verify JE
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected String[] Mcus2_debitAcc = { "Trade Debtors" };
	protected double[] Mcus2_expectedDebitAmt = { 50 };
	protected String[] Mcus2_creditAcc = { "Cash in hand" };
	protected double[] Mcus2_expectedCreditAmt = { 50 };
	// verify GL
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected String Mcus2_GLdebitAccounts[] = { "Trade Debtors" };
	protected double Mcus2_GLexpectedDebitAmt[] = { 50 };
	protected String Mcus2_GLcreditAccounts[] = { "Cash in hand" };
	protected double Mcus2_GLexpectedcreditAmt[] = { 50 };
	// ----------------------- Trial
	// Balance----------------------------------------------------------------
	protected String Mcus2_TBaccounts[] = { "Trade Debtors", "Cash in hand" };
	// ----------------------- Balance sheet
	// ----------------------------------------------------------------
	protected String Mcus2_BLaccounts[] = { "Trade Debtors", "Cash in hand" };
	// ----------------------- SOA
	// ----------------------------------------------------------------
	protected double Mcus2_SOAexpected = 100.00;
	// ----------------------- Credit Note report
	// ----------------------------------------------------------------
	protected double Mcus2_CNexpected = 435.00;
	// ----------------------- Age
	// Rec----------------------------------------------------------------
	protected double Mcus2_AgRecCN = 435.00;

	// ----------------------- Against GL [ General Ledger ]
	// ----------------------------------------------------------------
	protected double ExpectedPaidAmountGL = 0;
	// verify Report
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected double[] MPGL_report = { 0, 100 };
	// verify JE
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected String[] MPGL_debitAcc = { "MPaccountGL" };
	protected double[] MPGL_expectedDebitAmt = { 50 };
	protected String[] MPGL_creditAcc = { "Cash in hand" };
	protected double[] MPGL_expectedCreditAmt = { 50 };
	// verify GL
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected String MPGL_GLdebitAccounts[] = { "MPaccountGL" };
	protected double MPGL_GLexpectedDebitAmt[] = { 50 };
	protected String MPGL_GLcreditAccounts[] = { "Cash in hand" };
	protected double MPGL_GLexpectedcreditAmt[] = { 50 };
	// ----------------------- Trial
	// Balance----------------------------------------------------------------
	protected String MPGL_TBaccounts[] = { "MPaccountGL", "Cash in hand" };
	// ----------------------- Balance sheet
	// ----------------------------------------------------------------
	protected String MPGL_BLaccounts[] = { "MPaccountGL", "Cash in hand" };
}
