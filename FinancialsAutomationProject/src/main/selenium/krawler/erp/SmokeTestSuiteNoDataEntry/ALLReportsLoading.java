package krawler.erp.SmokeTestSuiteNoDataEntry;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class ALLReportsLoading {

	public static void BS_loading(WebDriver driver) throws IOException, InterruptedException {

		Utilities.Freportloading(driver, "OR_BalanceSheet.properties", "balSheetIcon", "NoRecord", "\"Balance Sheet\"",
				"CloseBtn");
	}

	public static void CustomerAging(WebDriver driver) throws IOException, InterruptedException {

		Utilities.Freportloading(driver, "OR_AgingReportCustomer.properties", "viewAgedreceivablesIcon", "NoRecord",
				"\"Aged Receivables\"", "CloseReport");
	}

	public static void VendorAging(WebDriver driver) throws IOException, InterruptedException {

		Utilities.Freportloading(driver, "OR_AgingReportVendor.properties", "viewAgedPayIcon", "NoRecord",
				"\"Aged Payables\"", "CloseReport");
	}

	public static void PnLReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.Freportloading(driver, "OR_TradingAndProfitLoss.properties", "PLCalculationIcon", "NoRecord",
				"\"Trading and Profit/Loss Statement\"", "CloseReport");
	}

	public static void GLReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.Freportloading(driver, "OR_GeneralLedgerLoading.properties", "LedgerIcon", "NoRecord",
				"\"General Ledger \"", "ClsButton");
	}

	public static void TrailBalance(WebDriver driver) throws IOException, InterruptedException {

		Utilities.Freportloading(driver, "OR_GeneralLedgerLoading.properties", "TrialBalanceIcon", "NoRecord1",
				"\"Trail Balance \"", "Closereport");
	}

	public static void JEReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.Freportloading(driver, "OR_PartyJE.properties", "JournalEntryReport", "NoRecord",
				"\"Journal Entry \"", "CloseJournalEntryReport");
	}

	public static void CP_PIReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.ReportLoading(driver, "OR_CashPurchase.properties", "SalesInvoiceReport",
				"\"Cash Purchase\\Purchase Invoice\"", "CloseReport");
	}

	public static void PurchaseRequisition(WebDriver driver) throws IOException, InterruptedException {

		Utilities.ReportLoading(driver, "OR_PurchaseRequisition.properties", "PurchaseRequisitionReport",
				"\"Purchase Requisition\"", "ClosePurchaseRequisitionReportTab");
	}

	public static void VendorQuotationReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.ReportLoading(driver, "OR_VendorQuotation.properties", "VendorQuotationReport",
				"\"Vendor Quotation\"", "CloseVendorQuotationTab");
	}

	public static void PurchaseOrderReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.ReportLoading(driver, "OR_PurchaseOrder.properties", "PurchaseOrderReport", "\"Purchase Order\"",
				"ClosePurchaseOrderReportTab");
	}

	public static void GoodReceiptReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.ReportLoading(driver, "OR_GoodReceipt.properties", "GoodReceiptReport", "\"Good Receipt\"",
				"reportCloseBtn");
	}

	public static void MakePaymentReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.ReportLoading(driver, "OR_MakePaymentAgainstVendor.properties", "PaymentMadeReport",
				"\"Make Payment\"", "clsRpt");
	}

	public static void PurchaseReturnReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.ReportLoading(driver, "OR_PurchaseReturnDebitNote.properties", "PurchaseReturnOnlyReport",
				"\"Purchase Return\"", "ClosePurchaseReturnOnlyTab");
	}

	public static void DebitNoteReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.ReportLoading(driver, "OR_DebitNoteOtherWise.properties", "DebitNoteReport", "\"Debit Note\"",
				"CloseReport");
	}

	public static void CustomerQuotationReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.ReportLoading(driver, "OR_CustomerQuotation.properties", "CustomerQuotationReport",
				"\"Customer Quotation\"", "CloseReport");
	}

	public static void SalesOrderReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.ReportLoading(driver, "OR_SalesOrder.properties", "SalesOrderReport", "\"Sales Order\"",
				"CloseReport");
	}

	public static void CS_SIReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.ReportLoading(driver, "OR_SalesInvoice.properties", "SalesInvoiceReport",
				"\"Cash Sale/Sales Invoice\"", "CloseCustomerReport");
	}

	public static void DeliveryOrderReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.ReportLoading(driver, "OR_DeliveryOrder.properties", "DeliveryOrderReport", "\"Delivery Order\"",
				"CloseReport");
	}

	public static void ReceivePaymentReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.ReportLoading(driver, "OR_ReceivePaymentAgainstVendor.properties", "ReceivePaymentReport",
				"\"Receive Payment\"", "ClosePaymentReceiveReport");
	}

	public static void SalesReturnReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.ReportLoading(driver, "OR_SalesReturn.properties", "SalesReturnReport", "\"Sales Return\"",
				"CloseReport");
	}

	public static void CreditNoteReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.ReportLoading(driver, "OR_CreditNoteOtherwise.properties", "CreditNoteReport", "\"Credit Note\"",
				"CloseReport");
	}

	public static void JournalEntryReport(WebDriver driver) throws IOException, InterruptedException {

		Utilities.ReportLoading(driver, "OR_NormalJE.properties", "JournalEntryReport", "\"Journal Entry\"",
				"CloseJournalEntryReport");
	}

	public static void VendorMasterReport(WebDriver driver) throws IOException, InterruptedException {

		Properties pro = Utilities.fetchProValue("OR_VendorMaster.properties");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Utilities.waitandClick(pro.getProperty("VendorMasterIcon"), driver);

		WebElement Element = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Export ']")));

		if (Element.isDisplayed()) {
			System.out.println("\"Vendor Master\" report is Opened Sucessfully");
		} else {
			System.out.println("Unable to Open \"Vendor Master\" report");
		}
		Thread.sleep(5000);
		Utilities.waitandClick(pro.getProperty("CloseReport"), driver);

	}

	public static void CustomerMasterReport(WebDriver driver) throws IOException, InterruptedException {
		Properties pro = Utilities.fetchProValue("OR_CustomerMaster.properties");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Utilities.waitandClick(pro.getProperty("customerReportIcon"), driver);

		WebElement Element = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Export ']")));

		if (Element.isDisplayed()) {
			System.out.println("\"Customer Master\" report is Opened Sucessfully");
		} else {
			System.out.println("Unable to Open \"Customer Master\" report");
		}
		Thread.sleep(5000);

		Utilities.waitandClick(pro.getProperty("closeReportTab"), driver);

	}

	public static void ProductMasterReport(WebDriver driver) throws IOException, InterruptedException {

		try {
			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			Utilities.waitandClick(pro.getProperty("createProductIcon"), driver);
			Thread.sleep(1000);
			Utilities.waitandClick(pro.getProperty("closeCreateproductTab"), driver);
			Thread.sleep(2000);
			Utilities.clickButton("Yes", 2000, driver);

			WebElement Element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Export ']")));

			if (Element.isDisplayed()) {
				System.out.println("\"Product Master\" report is Opened Sucessfully");
			} else {
				System.out.println("Unable to Open \"Product Master\" report");
			}
			Thread.sleep(5000);

			Utilities.waitandClick(pro.getProperty("CloseReport"), driver);
		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

	// ************************************* [Report Loading]
	// ********************************************

	public static void SOAReportLoading(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_SoAReportLoading.properties");

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement balSheetIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReportIcon"))));
			balSheetIcon.click();
			Thread.sleep(2000);

			WebElement rptList = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReportList"))));
			rptList.click();
			Thread.sleep(3000);
			Utilities.report_ScreenShot("Report Loading", driver);

			WebElement quickSearch = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("quickSearch"))));
			quickSearch.sendKeys("statement of Accounts");
			Thread.sleep(2000);

			WebElement viewBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("viewButton"))));
			viewBtn.click();
			Thread.sleep(2000);

			System.out.println("\"SOA\" report is opened Successfully");
			Utilities.refresh();

		} catch (Exception EX) {
			System.out.println("Unable to open \"SOA\" report ");
			Utilities.handleError(EX, driver);
		}
	}

}
