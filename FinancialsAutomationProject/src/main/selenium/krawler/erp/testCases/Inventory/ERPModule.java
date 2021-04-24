package krawler.erp.testCases.Inventory;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import krawler.erp.inventory.GoodsReceipt;
import krawler.erp.inventory.InvUtilities;
import krawler.erp.inventory.InventoryDO;
import krawler.erp.inventory.InventoryPR;
import krawler.erp.inventory.InventoryProductMaster;
import krawler.erp.inventory.QAApproval;
import krawler.erp.inventory.StockRepair;
import krawler.erp.inventory.reports.ReportProductMaster;
import krawler.erp.inventory.reports.StockAvailabilityByWarehouse;
import krawler.erp.inventory.reports.StockLedgerReport;
import krawler.erp.modules.DeliveryOrderModule1;
import krawler.erp.page.CustomerMaster;
import krawler.erp.page.GoodReceipt;
import krawler.erp.page.Utilities;
import krawler.erp.page.VendorMaster;

public class ERPModule extends BaseSetUp {
	private String productid = ErpProducts;
	private String batchValue = "EB New";
	private String serialSeq = "Esr";

	// * * * * Create all 4 type product, verify & also create vendor &
	// customer* * * *
	
	// ****************************GRN***************************************************
	
	@Test(priority = 300)
	public void create_GRNwithoutQA() throws InterruptedException, IOException, AWTException {
		String actualQty = "10";
		String receiveQty = "10";
		GoodsReceipt.create_InventoryGR(false, productid, vendorid, documentid, actualQty, receiveQty, batchValue,
				serialSeq, driver);
	}
	
	@Test(priority = 301)
	public void create_GRNwithQA() throws InterruptedException, IOException, AWTException {
		String actualQty = "6";
		String receiveQty = "6";
		GoodsReceipt.create_InventoryGR(true, productid, vendorid, "QA" + documentid, actualQty, receiveQty, batchValue,
				serialSeq + "QA", driver);
	}
	
	
	@Test(priority = 303, enabled = false)
	public void verifyReports_GRN() throws InterruptedException, AWTException, IOException {
		String availableQty = "10.0000";
		String blockedQty = "0.0000";
		String underQA = "6.0000";
		String underRepair = "0.0000";
		String balQty = "16.0000";
		StockAvailabilityByWarehouse.validate_StockAvailabilityByWarehouse(productid, availableQty, blockedQty, underQA,
				underRepair, driver);
		StockLedgerReport.validate_StockLedgerReport(productid, balQty, driver);
		ReportProductMaster.validate_ProductMaster(productid, availableQty, balQty, driver);
	}

	
	@Test(priority = 305)
	public void GRN_QAapproveANDreject() throws InterruptedException, AWTException, IOException {
		String approvedQuanity = "2";
		String rejectedQuanity = "3";
		String newdocumentid = "GRNQA" + documentid;
		GoodsReceipt.approveRequest(newdocumentid, approvedQuanity, productid, driver);
		GoodsReceipt.rejectRequest(newdocumentid, rejectedQuanity, productid, driver, true);
	}
	
	@Test(priority = 307)
	public void GRN_QA_Repair() throws InterruptedException, AWTException, IOException {
		String approvedQuanity = "1";
		String rejectedQuanity = "1";
		String newdocumentid = "GRNQA" + documentid;
		StockRepair.stockRepair_approve(newdocumentid, approvedQuanity, productid, driver);
		StockRepair.stockRepair_reject(newdocumentid, rejectedQuanity, productid, driver);
		StockRepair.stockRepair_Scrap(newdocumentid, approvedQuanity, productid, driver);
	}
		
	@Test(priority = 309, enabled = false)
	public void verifyReports_GRNQA() throws InterruptedException, AWTException, IOException {
		String availableQty = "14.0000";
		String blockedQty = "0.0000";
		String underQA = "1.0000";
		String underRepair = "0.0000";
		String balQty = "15.0000";
		StockAvailabilityByWarehouse.validate_StockAvailabilityByWarehouse(productid, availableQty, blockedQty, underQA,
				underRepair, driver);
		StockLedgerReport.validate_StockLedgerReport(productid, balQty, driver);
		ReportProductMaster.validate_ProductMaster(productid, availableQty, balQty, driver);
	}
	
	@Test(priority = 310)
	public void validate_GRN() throws InterruptedException, IOException, AWTException {
		GoodReceipt.verify_GoodReceipt("GRN" + documentid, productid, vendorid, driver);
		GoodReceipt.verify_GoodReceipt("GRNQA" + documentid, productid, vendorid, driver);
	}
	

	// **************************** DO -ve OFF
	// ***************************************************
	
	@Test(priority = 319, enabled = false)
	public void setNegative_off() throws InterruptedException, AWTException, IOException {
		InventoryDO.enable_NegativeDO(false, driver);
	}
	
	@Test(priority = 320)
	public void create_DeleiveryOrder_woQA() throws InterruptedException, IOException, AWTException {
		String actQty = "3";
		String delQty = "3";
		InventoryDO.create_DeliveryOrder(false, documentid, productid, customerid, actQty, delQty, driver, batchValue,
				serialSeq);
	}

	@Test(priority = 321, enabled = false)
	public void CopyEditDelete_DO() throws InterruptedException, IOException, AWTException {
		String newdocid = "InDo" + documentid + "Ex";
		InventoryDO.create_DeliveryOrder(false, documentid + "Ex", productid, customerid, "1", "1", driver, batchValue,
				serialSeq);
		InventoryDO.Edit_DeliveryOrder(newdocid, driver);
		InventoryDO.Delete_DeliveryOrder(newdocid, driver);
	}

	@Test(priority = 325)
	public void create_DeleiveryOrder_withQA() throws InterruptedException, IOException, AWTException {
		String actQty = "6";
		String delQty = "6";
		InventoryDO.create_DeliveryOrder(true, "QA" + documentid, productid, customerid, actQty, delQty, driver,
				batchValue, serialSeq);
	}

	@Test(priority = 326)
	public void DO_QAapproveANDreject() throws InterruptedException, AWTException, IOException {
		String approvedQuanity = "2";
		String rejectedQuanity = "3";
		String newdocumentid = "InDoQA" + documentid;
		GoodsReceipt.approveRequest(newdocumentid, approvedQuanity, productid, driver);
		GoodsReceipt.rejectRequest(newdocumentid, rejectedQuanity, productid, driver, false);
	}

	@Test(priority = 327)
	public void DO_QARepair_ApproveandReject() throws InterruptedException, AWTException, IOException {
		String approvedQuanity = "1";
		String rejectedQuanity = "1";
		String newdocumentid = "InDoQA" + documentid;
		StockRepair.stockRepair_approve(newdocumentid, approvedQuanity, productid, driver);
		StockRepair.stockRepair_reject(newdocumentid, rejectedQuanity, productid, driver);
	}
	
	
	@Test(priority = 329, enabled = false)
	public void verifyReports_DO() throws InterruptedException, AWTException, IOException {
		String availableQty = "5.0000";
		String blockedQty = "0.0000";
		String underQA = "2.0000";
		String underRepair = "1.0000";
		String balQty = "8.0000";
		StockAvailabilityByWarehouse.validate_StockAvailabilityByWarehouse(productid, availableQty, blockedQty, underQA,
				underRepair, driver);
		StockLedgerReport.validate_StockLedgerReport(productid, balQty, driver);
		ReportProductMaster.validate_ProductMaster(productid, availableQty, balQty, driver);
	}
	
	// **************** PR **********************************
	@Test(priority = 330)
	public void create_PurchaseReturn() throws InterruptedException, IOException, AWTException {
		String actQty = "2";
		String recQty = "2";

		InventoryPR.create_PurchaseReturnOnly(documentid, productid, vendorid, actQty, recQty, driver);
	}
	
	@Test(priority = 332, enabled = false)
	public void verifyReports_PR() throws InterruptedException, AWTException, IOException {
		String availableQty = "3.0000";
		String blockedQty = "0.0000";
		String underQA = "2.0000";
		String underRepair = "1.0000";
		String balQty = "6.0000";
		StockAvailabilityByWarehouse.validate_StockAvailabilityByWarehouse(productid, availableQty, blockedQty, underQA,
				underRepair, driver);
		StockLedgerReport.validate_StockLedgerReport(productid, balQty, driver);
		ReportProductMaster.validate_ProductMaster(productid, availableQty, balQty, driver);
	}
	
}
