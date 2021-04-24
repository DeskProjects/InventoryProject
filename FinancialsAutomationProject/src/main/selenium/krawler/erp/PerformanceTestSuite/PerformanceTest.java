package krawler.erp.PerformanceTestSuite;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class PerformanceTest {

	public static void BalanceSheetper(WebDriver driver) {
		Utilities.reportloadtime(driver, "OR_BalanceSheet.properties", "balSheetIcon", "FirstAccount", "Balance Sheet",
				"CloseBtn");
	}

	public static void loadPnlreport(WebDriver driver) {
		Utilities.reportloadtime(driver, "OR_TradingAndProfitLoss.properties", "PLCalculationIcon", "FirstAccountGroup",
				"Trading Profit/Loss ", "CloseReport");
	}

	public static void loadGLreport(WebDriver driver) {
		Utilities.reportloadtime(driver, "OR_GeneralLedgerLoading.properties", "LedgerIcon", "FirstAccount",
				"General Ledger ", "Closereport");
	}

	public static void loadTrailBalancereport(WebDriver driver) {
		Utilities.reportloadtime(driver, "OR_GeneralLedgerLoading.properties", "TrialBalanceIcon", "TBFirstAccount",
				"Trail Balance ", "Closereport");
	}

	public static void AgedpayablesSummaryreport(WebDriver driver) {
		Utilities.reportloadtime(driver, "OR_AgingReportVendor.properties", "viewAgedPayIcon", "SummaryFirstrow",
				"Aged payables Summary View ", "CloseReport");
	}

	public static void AgedpayablesReportViewreport(WebDriver driver) throws IOException, InterruptedException {

		try {
			Properties pro = Utilities.fetchProValue("OR_AgingReportVendor.properties");
			WebDriverWait wait = new WebDriverWait(driver, 2000);

			Utilities.waitandClick(pro.getProperty("viewAgedPayIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReportViewTab"), driver);
			Utilities.waitandClick(pro.getProperty("reportTabFetch"), driver);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(new Date());

			WebElement BS = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReportViewfirst"))));

			if (BS.isDisplayed()) {
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				long diff = cal2.getTimeInMillis() - cal1.getTimeInMillis();
				System.out.println("Aged Payables Report View  loaded in Seconds = " + diff / 1000 % 60
						+ " | Milli Seconds = " + diff);
			} else {
				System.out.println("unable to load Report Aged Payables Report View ");
			}
			Utilities.waitandClick(pro.getProperty("CloseReport"), driver);
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void AgedReceivablesSummaryreport(WebDriver driver) {
		Utilities.reportloadtime(driver, "OR_AgingReportCustomer.properties", "viewAgedreceivablesIcon",
				"ARSummaryFirstRow", "Aged Receivables Summary View ", "CloseReport");
	}

	public static void AgedReceivablesReportViewreport(WebDriver driver) throws IOException, InterruptedException {

		try {
			Properties pro = Utilities.fetchProValue("OR_AgingReportCustomer.properties");
			WebDriverWait wait = new WebDriverWait(driver, 2000);

			Utilities.waitandClick(pro.getProperty("viewAgedreceivablesIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReportViewTab"), driver);
			Utilities.waitandClick(pro.getProperty("reportTabFetch"), driver);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(new Date());

			WebElement BS = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ARReportView"))));

			if (BS.isDisplayed()) {
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				long diff = cal2.getTimeInMillis() - cal1.getTimeInMillis();
				System.out.println("Aged Receivables Report View  loaded in Seconds = " + diff / 1000 % 60
						+ " | Milli Seconds = " + diff);
			} else {
				System.out.println("unable to load Report Aged Receivables Report View ");
			}
			Utilities.waitandClick(pro.getProperty("CloseReport"), driver);
		} catch (Exception EX) {
			EX.printStackTrace();
		}

	}

	public static void JournalEntryreport(WebDriver driver) {
		Utilities.reportloadtime(driver, "OR_FundTransferJE.properties", "JournalEntryReport", "Firtrowcheck",
				"Journal Entry ", "CloseJournalEntryReport");
	}

	public static void Purchaseequisitionreport(WebDriver driver) {
		Utilities.reportloadtimeWithoutfetch(driver, "OR_PurchaseRequisition.properties", "PurchaseRequisitionReport",
				"FirstRow", "Purchase Requisition ", "ClosePurchaseRequisitionReportTab");
	}

	public static void VendorQuotationreport(WebDriver driver) {
		Utilities.reportloadtimeWithoutfetch(driver, "OR_VendorQuotation.properties", "VendorQuotationReport",
				"FirstRow", "Vendor Quotation ", "CloseVendorQuotationTab");
	}

	public static void PurchaseOrderreport(WebDriver driver) {

		Utilities.reportloadtimeWithoutfetch(driver, "OR_PurchaseOrder.properties", "PurchaseOrderReport", "FirstRow",
				"Purchase Order ", "ClosePurchaseOrderReportTab");

	}

	public static void PI_CPreport(WebDriver driver) {

		Utilities.reportloadtimeWithoutfetch(driver, "OR_CashPurchase.properties", "SalesInvoiceReport", "FirstRow",
				"Purchase Invoice/Cash Purchase ", "CloseReport");

	}

	public static void GoodReceipt(WebDriver driver) {

		Utilities.reportloadtimeWithoutfetch(driver, "OR_GoodReceipt.properties", "GoodReceiptReport", "FirstRow",
				"Good Receipt ", "reportCloseBtn");

	}

	public static void MakePayment(WebDriver driver) {

		Utilities.reportloadtimeWithoutfetch(driver, "OR_MakePaymentAgainstGL.properties", "PaymentMadeReport",
				"FirstRow", "Payment Made ", "ClosePaymentMadeReport");

	}

	public static void DebitNote(WebDriver driver) {
		Utilities.reportloadtimeWithoutfetch(driver, "OR_DebitNoteOtherWise.properties", "DebitNoteReport", "FirstRow",
				"Debit Note ", "CloseReport");
	}

	public static void PurchaseReturn(WebDriver driver) {
		Utilities.reportloadtimeWithoutfetch(driver, "OR_PurchaseReturnDebitNote.properties",
				"PurchaseReturnOnlyReport", "FirstRow", "Purchase Return ", "ClosePurchaseReturnOnlyTab");
	}

	public static void CustomerQuotation(WebDriver driver) {
		Utilities.reportloadtimeWithoutfetch(driver, "OR_CustomerQuotation.properties", "CustomerQuotationReport",
				"FirstRow", "Customer Quotation ", "CloseReport");
	}

	public static void SalesOrder(WebDriver driver) {
		Utilities.reportloadtimeWithoutfetch(driver, "OR_SalesOrder.properties", "SalesOrderReport", "FirstRow",
				"Sales Order ", "CloseReport");
	}

	public static void SI_CS(WebDriver driver) {
		Utilities.reportloadtimeWithoutfetch(driver, "OR_SalesInvoice.properties", "SalesInvoiceReport", "FirstRow",
				"Sales Invoice/Cash Sale ", "CloseCustomerReport");
	}

	public static void DeliveryOrder(WebDriver driver) {
		Utilities.reportloadtimeWithoutfetch(driver, "OR_DeliveryOrder.properties", "DeliveryOrderReport", "FirstRow",
				"Delivery Order ", "CloseReport");
	}

	public static void ReceivePayment(WebDriver driver) {
		Utilities.reportloadtimeWithoutfetch(driver, "OR_ReceivePaymentAgainstGL.properties", "ReceivePaymentReport",
				"FirstRow", "Receive Payment ", "ClosePaymentReceiveReport");
	}

	public static void CreditNote(WebDriver driver) {
		Utilities.reportloadtimeWithoutfetch(driver, "OR_CreditNoteOtherwise.properties", "CreditNoteReport",
				"FirstRow", "Credit Note ", "CloseReport");
	}

	public static void SalesReturn(WebDriver driver) {
		Utilities.reportloadtimeWithoutfetch(driver, "OR_SalesReturn.properties", "SalesReturnReport", "FirstRow",
				"Sales Return ", "CloseReport");
	}

	public static void SOACustomer(WebDriver driver, String Fromdate, String ASofdate, String Todate) {
		try {
			Properties pro = Utilities.fetchProValue("OR_SoAReportLoading.properties");
			WebDriverWait wait = new WebDriverWait(driver, 2000);

			Utilities.waitandClick(pro.getProperty("ReportIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReportList"), driver);
			Utilities.waitandSendkey(pro.getProperty("quickSearch"), driver, "Statement of Accounts");
			Thread.sleep(2000);
			Utilities.waitandClick(pro.getProperty("viewButton"), driver);
			Utilities.waitandSendkey(pro.getProperty("startDate"), driver, Fromdate);
			Utilities.waitandSendkey(pro.getProperty("asOfDate"), driver, ASofdate);
			Utilities.waitandSendkey(pro.getProperty("Enddate"), driver, Todate);

			Utilities.clickButton("Fetch", 2000, driver);

			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(new Date());

			WebElement BS = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("docCheck"))));

			if (BS.isDisplayed()) {
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				long diff = cal2.getTimeInMillis() - cal1.getTimeInMillis();
				System.out.println("\"SOA Customer \" Report loaded in Seconds = " + diff / 1000 % 60
						+ " | Milli Seconds = " + diff);
			} else {
				System.out.println("unable to load Report \"SOA Customer \"");
			}
			Utilities.refresh();
		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

	public static void SOAVendor(WebDriver driver, String Fromdate, String ASofdate, String Todate) {
		try {
			Properties pro = Utilities.fetchProValue("OR_SoAReportLoading.properties");
			WebDriverWait wait = new WebDriverWait(driver, 2000);

			Utilities.waitandClick(pro.getProperty("ReportIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReportList"), driver);
			Utilities.waitandSendkey(pro.getProperty("quickSearch"), driver, "Statement of Accounts");
			Thread.sleep(2000);
			Utilities.waitandClick(pro.getProperty("viewButton"), driver);
			Utilities.waitandClick(pro.getProperty("vendorAccStatementTab"), driver);
			Utilities.waitandSendkey(pro.getProperty("startDate2"), driver, Fromdate);
			Utilities.waitandSendkey(pro.getProperty("asOfDate2"), driver, ASofdate);
			Utilities.waitandSendkey(pro.getProperty("Enddate2"), driver, Todate);

			Utilities.waitandClick(pro.getProperty("vendorFetchBtn"), driver);

			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(new Date());

			WebElement BS = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("FirstAccount"))));

			if (BS.isDisplayed()) {
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				long diff = cal2.getTimeInMillis() - cal1.getTimeInMillis();
				System.out.println("\"SOA Vendor \" Report loaded in Seconds = " + diff / 1000 % 60
						+ " | Milli Seconds = " + diff);
			} else {
				System.out.println("unable to load Report \"SOA Vendor \"");
			}
			Utilities.refresh();

		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

}
