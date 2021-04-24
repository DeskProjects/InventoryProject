package krawler.erp.SmokeTestSuiteNoDataEntry;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class AllFormLoading {

	public static void cashPurchase(WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {
			Utilities.formloading(driver, "OR_CashPurchase.properties", "CreateCashPurchaseIcon", "\"Cash Purchase \"",
					"closeform");
		} catch (Exception EX) {
			EX.printStackTrace();
		}

	}

	public static void cashSale(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Utilities.formloading(driver, "OR_CashSale.properties", "CreateCashSaleIcon", "\"Cash Sale \"",
					"CloseCashSale");
		} catch (Exception EX) {
			EX.printStackTrace();
		}

	}

	public static void Purchase_Requisation(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Utilities.formloading(driver, "OR_PurchaseRequisition.properties", "CreatePurReqIcon",
					"\"Purchase Requisition \"", "clickOk");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void VendorQuatation(WebDriver driver) {
		try {
			Utilities.formloading(driver, "OR_VendorQuotation.properties", "CreateVendorQuotationIcon",
					"\"Vendor Quotation \"", "quotationOk");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void PurchaseOrder(WebDriver driver) {
		try {
			Utilities.formloading(driver, "OR_PurchaseOrder.properties", "PurchaseOrderIcon", "\"Purchase Order \"",
					"ClosePurchaseOrder");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void vendorInvoice(WebDriver driver) {
		try {
			Utilities.formloading(driver, "OR_VendorInvoice.properties", "CreateVendorInvoiceIcon",
					"\"Purchase Invoice \"", "closeVendorInvoice");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void goodReceipt(WebDriver driver) {
		try {
			Utilities.formloading(driver, "OR_GoodReceipt.properties", "GoodReceiptIcon", "\"Good Receipt \"",
					"CloseGoodReceipt");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void MakePayment_Customer(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("MakePaymentAgainstCustomer"), driver);
			Utilities.formloading(driver, "OR_MakePaymentAgainstCustomer.properties", "SubmitButton1",
					"\"Make Payment Against Customer \"", "CloseMakePayment");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void MakePayment_GL(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstGL.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("AgainstGL"), driver);
			Utilities.formloading(driver, "OR_MakePaymentAgainstGL.properties", "Submitbtn",
					"\"Make Payment Against GL \"", "CloseTabBtn");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void MakePayment_Vendor(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("MakePaymentAgainstVendorCheck"), driver);
			Utilities.formloading(driver, "OR_MakePaymentAgainstVendor.properties", "SubmitButton1",
					"\"Make Payment Against Vendor \"", "CloseMakePayment");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void PurchaseReturn(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			Properties pro = Utilities.fetchProValue("OR_PurchaseReturnOnly.properties");

			Utilities.waitandClick(pro.getProperty("PurchaseReturnOnlyIcon"), driver);
			Utilities.waitandClick(pro.getProperty("Purchasereturnonlyform"), driver);
			Utilities.formloading(driver, "OR_PurchaseReturnOnly.properties", "buttonSubmit",
					"\"Purchase Return Only \"", "ClosePurchaseReturnOnly");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void PurchaseReturn_withDN(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			Properties pro = Utilities.fetchProValue("OR_PurchaseReturnDebitNote.properties");

			Utilities.waitandClick(pro.getProperty("PurchaseReturnIcon"), driver);
			Utilities.waitandClick(pro.getProperty("SelectPurchaseReturnDebit"), driver);
			Utilities.formloading(driver, "OR_PurchaseReturnDebitNote.properties", "ClickSubmit",
					"\"Purchase Return with DN \"", "CloseForm");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void DebitNoteAgainstCustomer(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			Properties pro = Utilities.fetchProValue("OR_DebitNoteAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("DebitNoteIcon"), driver);
			Utilities.waitandClick(pro.getProperty("DebitNoteCustomer"), driver);
			Utilities.formloading(driver, "OR_DebitNoteAgainstCustomer.properties", "ClickOnSubmit",
					"\"Debit Note Against Customer \"", "CloseForm");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void DebitNoteUnderCharge(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			Properties pro = Utilities.fetchProValue("OR_DebitNoteCharged.properties");

			Utilities.waitandClick(pro.getProperty("CreateDebitNoteIcon"), driver);
			Utilities.waitandClick(pro.getProperty("SelectDNunderCharge"), driver);
			Utilities.formloading(driver, "OR_DebitNoteCharged.properties", "submitButton",
					"\"Debit Note for Undercharged Sales Invoice \"", "CloseFormUnder");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void DebitNoteOverCharge(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			Properties pro = Utilities.fetchProValue("OR_DebitNoteCharged.properties");

			Utilities.waitandClick(pro.getProperty("CreateDebitNoteIcon"), driver);
			Utilities.waitandClick(pro.getProperty("SelectDNOverCharge"), driver);
			Utilities.formloading(driver, "OR_DebitNoteCharged.properties", "submitButton",
					"\"Debit Note for Overcharged Purchase Invoice \"", "CloseFormOver");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void DebitNoteOtherwise(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			Properties pro = Utilities.fetchProValue("OR_DebitNoteOtherWise.properties");

			Utilities.waitandClick(pro.getProperty("CreateDebitNoteIcon"), driver);
			Utilities.waitandClick(pro.getProperty("SelectDNOtherwiseOption"), driver);
			Utilities.formloading(driver, "OR_DebitNoteOtherWise.properties", "submitButton",
					"\"Debit Note for Otherwise \"", "clickOk");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void DebitNotePurchaseInvoice(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			Properties pro = Utilities.fetchProValue("OR_DebitNotePurchaseInvoice.properties");

			Utilities.waitandClick(pro.getProperty("DebitNotePurchaseInvoiceIcon"), driver);
			Utilities.waitandClick(pro.getProperty("SelectDebitNotePurchaseInvoice"), driver);
			Utilities.formloading(driver, "OR_DebitNotePurchaseInvoice.properties", "ClickSubmit",
					"\"Debit Note against Purchase Invoice \"", "CloseForm");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void CustomerQuotationForm(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Utilities.formloading(driver, "OR_CustomerQuotation.properties", "CustomerQuotationIcon",
					"\"Customer Quotation\"", "CloseQuotation");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void SalesOrderForm(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Utilities.formloading(driver, "OR_SalesOrder.properties", "CreateSalesOrderIcon", "\"Sales Order\"",
					"clickOk");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void SalesInvoiceform(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Utilities.formloading(driver, "OR_SalesInvoice.properties", "SalesInvoiceIcon", "\"Sales Invoice\"",
					"CloseSalesInvoice");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void DeliveryOrderform(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Utilities.formloading(driver, "OR_DeliveryOrder.properties", "DeliveryOrderIcon", "\"Delivery Order\"",
					"CloseFormDO");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void ReceivePaymentAgainstCustomer(WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReceivePaymentAgainstCustomerCheck"), driver);
			Utilities.formloading(driver, "OR_ReceivePaymentAgainstCustomer.properties", "SubmitButton1",
					"\"Receive Payment Against Customer \"", "CloseMakePayment");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void ReceivePaymentAgainstGL(WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("ReceivePaymentAgainstGL.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReceivePaymentAgainstGLCheck"), driver);
			Utilities.formloading(driver, "ReceivePaymentAgainstGL.properties", "SubmitButton1",
					"\"Receive Payment Against GL \"", "CloseMakePayment");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void ReceivePayment_Vendor(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReceivePaymentAgainstVendorCheck"), driver);
			Utilities.formloading(driver, "OR_ReceivePaymentAgainstVendor.properties", "SubmitButton1",
					"\"Receive Payment against Vendor \"", "CloseMakePayment");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void SaleReturnOnly(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_SalesReturn.properties");

			Utilities.waitandClick(pro.getProperty("ReturnOnly"), driver);
			Utilities.waitandClick(pro.getProperty("Salereturnonly"), driver);
			Utilities.formloading(driver, "OR_SalesReturn.properties", "clickOnSubmit", "\"Sale Return Only \"",
					"clickOnOk");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void SaleReturnWithCN(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_SalesReturn.properties");

			Utilities.waitandClick(pro.getProperty("clickOnDocumentSC"), driver);
			Utilities.waitandClick(pro.getProperty("salesReturnCreditNote"), driver);
			Utilities.formloading(driver, "OR_SalesReturn.properties", "clickOnSubmit", "\"Sale Return With CN \"",
					"clickOnOk");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void CreditNoteAgainstVendor(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			Properties pro = Utilities.fetchProValue("OR_CreditNote_PurchaseInvoice.properties");

			Utilities.waitandClick(pro.getProperty("CreditNoteIcon"), driver);
			Utilities.waitandClick(pro.getProperty("SelectVendorNote"), driver);
			Utilities.formloading(driver, "OR_CreditNote_PurchaseInvoice.properties", "ClickSubmit",
					"\"Credit Note Against Vendor \"", "CloseForm");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void CreditNoteUnderCharge(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			Properties pro = Utilities.fetchProValue("OR_CrediNoteCharged.properties");

			Utilities.waitandClick(pro.getProperty("CreateCrediNoteIcon"), driver);
			Utilities.waitandClick(pro.getProperty("SelectCNunderCharge"), driver);
			Utilities.formloading(driver, "OR_CrediNoteCharged.properties", "submitButton",
					"\"Credit Note for Undercharged Sales Invoice \"", "CNUnderChrageClose");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void CreditNoteOverCharge(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			Properties pro = Utilities.fetchProValue("OR_CrediNoteCharged.properties");

			Utilities.waitandClick(pro.getProperty("CreateCrediNoteIcon"), driver);
			Utilities.waitandClick(pro.getProperty("SelectCNoverCharge"), driver);
			Utilities.formloading(driver, "OR_CrediNoteCharged.properties", "submitButton",
					"\"Credit Note for Overcharged Purchase Invoice \"", "CNoverChrageClose");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void CreditNoteOtherwise(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherwise.properties");

			Utilities.waitandClick(pro.getProperty("CreditNoteIcon"), driver);
			Utilities.waitandClick(pro.getProperty("SelectCreditOther"), driver);
			Utilities.formloading(driver, "OR_CreditNoteOtherwise.properties", "ClickSubmit",
					"\"Credit Note for Otherwise \"", "CloseForm");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void CreditNoteSalesInvoice(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			Properties pro = Utilities.fetchProValue("OR_CreditNoteSalesInvoice.properties");

			Utilities.waitandClick(pro.getProperty("CreditNoteIcon"), driver);
			Utilities.waitandClick(pro.getProperty("CreditSalesInvoice"), driver);
			Utilities.formloading(driver, "OR_CreditNoteSalesInvoice.properties", "ClickSubmit",
					"\"Credit Note against Sales Invoice \"", "CloseForm");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void JENormal(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			Properties pro = Utilities.fetchProValue("OR_NormalJE.properties");

			Utilities.waitandClick(pro.getProperty("ClcikOnJEIcon"), driver);
			Utilities.waitandClick(pro.getProperty("SelectNormalJEOption"), driver);
			Utilities.waitandClick(pro.getProperty("ClickOnSubmit"), driver);

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']")));

			if (Element.isDisplayed()) {
				System.out.println("\"Normal Journal Entry \" Form is Opened Sucessfully");
			} else {
				System.out.println("Unable to Open \"Normal Journal Entry\" Form");
			}

			Utilities.waitandClick(pro.getProperty("CloseForm"), driver);
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void JEFundTranfer(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			Properties pro = Utilities.fetchProValue("OR_FundTransferJE.properties");
			Thread.sleep(2000);

			Utilities.waitandClick(pro.getProperty("ClcikOnJEIcon"), driver);
			Utilities.waitandClick(pro.getProperty("SelectFundsJEOption"), driver);
			Utilities.waitandClick(pro.getProperty("ClickOnSubmit"), driver);

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']")));

			if (Element.isDisplayed()) {
				System.out.println("\"Fund Tranfer JE \" Form is Opened Sucessfully");
			} else {
				System.out.println("Unable to Open \"Fund Tranfer JE\" Form");
			}
			Utilities.waitandClick(pro.getProperty("CloseForm"), driver);
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void JEParty(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		try {
			Properties pro = Utilities.fetchProValue("OR_PartyJE.properties");
			Thread.sleep(2000);
			Utilities.waitandClick(pro.getProperty("ClcikOnJEIcon"), driver);
			Utilities.waitandClick(pro.getProperty("SelectPartyJEOption"), driver);
			Utilities.waitandClick(pro.getProperty("ClickOnSubmit"), driver);

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']")));

			if (Element.isDisplayed()) {
				System.out.println("\"Party Journal Entry \" Form is Opened Sucessfully");
			} else {
				System.out.println("Unable to Open \"Party Journal Entry\" Form");
			}

			Utilities.waitandClick(pro.getProperty("CloseFormPartyJE"), driver);
		}

		catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void VendorMaster(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Utilities.formloading(driver, "OR_VendorMaster.properties", "CreateVendorIcon", "\"Vendor Master\"",
					"ClosedFrom");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void CustomerMaster(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Utilities.formloading(driver, "OR_CustomerMaster.properties", "CreateCustomerIcon", "\"Customer Master\"",
					"closeCustomerTab");
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void ProductMaster(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Utilities.formloading(driver, "OR_ProductMaster.properties", "createProductIcon", "\"Product Master\"",
					"CloseMainProductTab");
			Utilities.refresh();

		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}
}
