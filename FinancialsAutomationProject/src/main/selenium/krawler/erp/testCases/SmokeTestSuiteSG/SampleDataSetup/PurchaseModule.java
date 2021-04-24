package krawler.erp.testCases.SmokeTestSuiteSG.SampleDataSetup;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import krawler.erp.modules.CashPurchase1;
import krawler.erp.modules.PurchaseInvoice1;
import krawler.erp.modules.PurchaseOrder1;
import krawler.erp.modules.PurchaseRequisition1;
import krawler.erp.modules.VendorQuotation1;
import krawler.erp.page.ProductMaster;

public class PurchaseModule extends BaseSetUpProper {

	@Test(priority = 102)
	public void set_PurchasePriceList() throws InterruptedException, IOException, AWTException {
		String[] productIDs = { productid + " Normal", productid + " Mechanical" };
		String pricePurchase = "Purchase Price";
		String priceSales = "Sales Price";
		ProductMaster.setPriceList_MultipleProducts(productIDs, pricePurchase, "100", driver);
		ProductMaster.setPriceList_MultipleProducts(productIDs, priceSales, "200", driver);
	}

	@Test(priority = 111)
	public void Create_PurchaseReq() throws InterruptedException, IOException, AWTException {
		PurchaseRequisition1.create_PurchaseRequisition("00001", productid, driver);
		PurchaseRequisition1.create_PurchaseRequisition("00002", productid, driver);
		PurchaseRequisition1.create_PurchaseRequisition("00003", productid, driver);
		PurchaseRequisition1.create_PurchaseRequisition("00004", productid, driver);
		PurchaseRequisition1.create_PurchaseRequisition("00005", productid, driver);
	}

	@Test(priority = 121)
	public void Create_VendorQuotation() throws InterruptedException, IOException, AWTException {
		VendorQuotation1.create_VendorQuotation("00001", productid, "Victor", driver);
		VendorQuotation1.create_VendorQuotation("00002", productid, "Ned", driver);
		VendorQuotation1.create_VendorQuotation("00003", productid, "Rodger", driver);
		VendorQuotation1.create_VendorQuotation("00004", productid, "Rick", driver);
		VendorQuotation1.create_VendorQuotation("00005", productid, "Bob", driver);
	}

	@Test(priority = 131)
	public void Create_PurchaseOrder() throws InterruptedException, IOException, AWTException {
		PurchaseOrder1.create_PurchaseOrder("00001", productid, "Victor", driver);
		PurchaseOrder1.create_PurchaseOrder("00002", productid, "Ned", driver);
		PurchaseOrder1.create_PurchaseOrder("00003", productid, "Rodger", driver);
		PurchaseOrder1.create_PurchaseOrder("00004", productid, "Rick", driver);
		PurchaseOrder1.create_PurchaseOrder("00005", productid, "Bob", driver);
	}

	@Test(priority = 141)
	public void Create_CashPurchase() throws InterruptedException, IOException, AWTException {
		CashPurchase1.create_CashPurchase("00001", productid, "Victor", driver);
		CashPurchase1.create_CashPurchase("00002", productid, "Ned", driver);
		CashPurchase1.create_CashPurchase("00003", productid, "Rodger", driver);
		CashPurchase1.create_CashPurchase("00004", productid, "Rick", driver);
		CashPurchase1.create_CashPurchase("00005", productid, "Bob", driver);
	}

	@Test(priority = 151)
	public void Create_PurchaseInvoice() throws InterruptedException, IOException, AWTException {
		PurchaseInvoice1.create_PurchaseInvoice("00001", productid, "Victor", driver);
		PurchaseInvoice1.create_PurchaseInvoice("00002", productid, "Ned", driver);
		PurchaseInvoice1.create_PurchaseInvoice("00003", productid, "Rodger", driver);
		PurchaseInvoice1.create_PurchaseInvoice("00004", productid, "Rick", driver);
		PurchaseInvoice1.create_PurchaseInvoice("00005", productid, "Bob", driver);
	}

}
