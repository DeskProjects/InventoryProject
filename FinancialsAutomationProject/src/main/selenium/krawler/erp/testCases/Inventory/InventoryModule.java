package krawler.erp.testCases.Inventory;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import krawler.erp.inventory.CreateStockSalesOrStockOut;
import krawler.erp.inventory.CycleCountCalendar;
import krawler.erp.inventory.CycleCountForm;
import krawler.erp.inventory.InterLocationStockTransfer;
import krawler.erp.inventory.InterStoreStockTransfer;
import krawler.erp.inventory.InvUtilities;
import krawler.erp.inventory.InventoryProductMaster;
import krawler.erp.inventory.QAApproval;
import krawler.erp.inventory.StockAdjustment;
import krawler.erp.inventory.StockIssue;
import krawler.erp.inventory.StockRepair;
import krawler.erp.inventory.StockRequest;
import krawler.erp.inventory.reports.BatchwiseStockTracking;
import krawler.erp.inventory.reports.DatewiseBatchStockTracking;
import krawler.erp.inventory.reports.DatewiseStockTracking;
import krawler.erp.inventory.reports.InterLocationStockTransferDetailsReport;
import krawler.erp.inventory.reports.InterStoreStockTransferDetailsRPT;
import krawler.erp.inventory.reports.ReportProductMaster;
import krawler.erp.inventory.reports.StockAdjustmentRegister;
import krawler.erp.inventory.reports.StockAvailabilityByWarehouse;
import krawler.erp.inventory.reports.StockAvailabilitySummaryReport;
import krawler.erp.inventory.reports.StockLedgerReport;
import krawler.erp.inventory.reports.StockMovementSummaryReport;
import krawler.erp.inventory.reports.StockReport;
import krawler.erp.inventory.reports.StockStatusReport;
import krawler.erp.inventory.reports.StockValuationDetailReport;
import krawler.erp.inventory.reports.StockValuationSummaryReport;
import krawler.erp.page.CustomerMaster;
import krawler.erp.page.Utilities;
import krawler.erp.page.VendorMaster;

public class InventoryModule extends BaseSetUp {

	private String productid = InventoryProducts;
	private String productiderp = ErpProducts;
	private String productidBA = BuildAsProducts;
	private String batchValue = "IB New";
	private String serialSeq = "IS"; // chnz serial each time
	String cycleCountDate = Utilities.currentDate("yyyy-MM-dd");
	
	@Test(priority = 8, enabled = true)
	public void create_Vendor() throws InterruptedException, IOException, AWTException {
		VendorMaster.create_Vendor(vendorid, driver);
	}

	@Test(priority = 9, enabled = true)
	public void customerMaster() throws InterruptedException, IOException, AWTException {
		CustomerMaster.create_Customer(customerid, driver);
	}
	
	
	@Test(priority = 10, enabled = true)
	public void Create_INV_NormalProduct() throws InterruptedException, IOException, AWTException {
		InventoryProductMaster.create_Product(productid, "Normal", purchaseAccount, salesAccount, driver);
		InventoryProductMaster.create_Product(productid + "B2", "Batch", purchaseAccount, salesAccount, driver);
		InventoryProductMaster.create_Product(productid + "S3", "Serial", purchaseAccount, salesAccount, driver);
		InventoryProductMaster.create_Product(productid + "BS4", "BatchSerial", purchaseAccount, salesAccount, driver);
	}
	
	
	@Test(priority = 11, enabled = true)
	public void create_abProduct() throws InterruptedException, IOException, AWTException {
		InventoryProductMaster.create_AssemblyNormalProduct(productidBA + "A1", purchaseAccount, salesAccount, driver);
		InventoryProductMaster.create_AssemblyNormalProduct(productidBA + "A2", purchaseAccount, salesAccount, driver);
		InventoryProductMaster.create_AssemblyNormalProduct(productidBA + "A3", purchaseAccount, salesAccount, driver);
	}
	
	
	
	@Test(priority = 12, enabled = true)
	public void create_AssemblyProduct() throws InterruptedException, IOException, AWTException {
		InventoryProductMaster.create_InventoryAssemblyProduct(productidBA, purchaseAccount, salesAccount, driver);
	}
	
	
	@Test(priority = 13, enabled = true)
	public void Create_ERP_NormalProduct() throws InterruptedException, IOException, AWTException {
		Utilities.disableExpander(driver);
		InventoryProductMaster.create_Product(productiderp, "Normal", purchaseAccount, salesAccount, driver);
		InventoryProductMaster.create_Product(productiderp + "B2", "Batch", purchaseAccount, salesAccount, driver);
		InventoryProductMaster.create_Product(productiderp + "S3", "Serial", purchaseAccount, salesAccount, driver);
		InventoryProductMaster.create_Product(productiderp + "BS4", "BatchSerial", purchaseAccount, salesAccount, driver);
		//InventoryProductMaster.verify_allProducts(productiderp, driver);
	}
	
	@Test(priority = 16, enabled = false)
	public void verifyProducts() throws InterruptedException, IOException, AWTException {
		InventoryProductMaster.verify_allProducts(productid, driver);
	}

	@Test(priority = 17, enabled = false)
	public void Report_ProductMaster1() throws InterruptedException, IOException, AWTException {
		double expectedQty[] = { 500, 500, 500, 0 };
		String prods[] = { "BA" + productidBA + "A1", "BA" + productidBA + "A2", "BA" + productidBA + "A3",
				"AsB" + productidBA };
		ReportProductMaster.verify_ProductMaster_MultipleProducts(documentid, prods, expectedQty, driver);
	}
	
	@Test(priority = 20, enabled = true)
	public void StockIN_without_QA() throws InterruptedException, AWTException, IOException {
		// "Stock IN w/o QA Flow");
		String quanityValue = "10";
		String perUnitPrice = "10";
		StockAdjustment.createStockAdjustment_StockIn(documentid, productid, batchValue, serialSeq, quanityValue,
				perUnitPrice, false, driver);
	}

	@Test(priority = 22)
	public void StockOUT_without_QA() throws InterruptedException, AWTException, IOException {
		// "Stock OUT w/o QA");
		String adjustmentTypeValue = "Stock Out";
		String quanityValue = "1"; // pass 1 to verify
		CreateStockSalesOrStockOut.create_StockAdjustmentforStockOutorStocksale("StockOutwoQA" + documentid, productid,
				adjustmentTypeValue, batchValue, serialSeq, quanityValue, driver);
	}

	@Test(priority = 23)
	public void StockSales_without_QA() throws InterruptedException, AWTException, IOException {
		// "Stock SALE w/o QA");
		String adjustmentTypeValue = "Stock Sales";
		String quanityValue = "1";
		CreateStockSalesOrStockOut.create_StockAdjustmentforStockOutorStocksale("StockSalewoQA" + documentid, productid,
				adjustmentTypeValue, batchValue, serialSeq, quanityValue, driver);
	}
	
	@Test(priority = 25, enabled = false)
	public void verifyReports_StockAdjWithOutQA() throws InterruptedException, AWTException, IOException {
		String availableQty = "8.0000";
		String blockedQty = "0.0000";
		String underQA = "0.0000";
		String underRepair = "0.0000";
		String balQty = "8.0000";
		ReportProductMaster.validate_ProductMaster(productid, availableQty, balQty, driver);
		StockAvailabilityByWarehouse.validate_StockAvailabilityByWarehouse(productid, availableQty, blockedQty, underQA,
				underRepair, driver);
		StockLedgerReport.validate_StockLedgerReport(productid, availableQty, driver);
	}
	
	@Test(priority = 40)
	public void Create_StockIn_withQA() throws InterruptedException, AWTException, IOException {
		// "Stock IN with QA");
		String quanityValue = "6";
		String perUnitPrice = "10";
		StockAdjustment.createStockAdjustment_StockIn(documentid + "QA", productid, batchValue, "QA" + serialSeq,
				quanityValue, perUnitPrice, true, driver);
	}

	@Test(priority = 50)
	public void QA_ApprovalApproveReject() throws InterruptedException, AWTException, IOException {
		// "QA Approval");
		String approvedQuanit = "2";
		String rejectQty = "3";
		String documentidApprove = "StockIN" + documentid + "QA";
		QAApproval.approveQA(documentidApprove, productid, approvedQuanit, driver);
		QAApproval.rejectQA(documentidApprove, productid, rejectQty, driver);
		
	}
	@Test(priority = 60)
	public void StockRepair_ApproveReject() throws InterruptedException, AWTException, IOException {
		String approvedQuanity = "1";
		String rejectedQuanity = "1";
		String newdocumentid = "StockIN" + documentid + "QA";
		StockRepair.stockRepair_approve(newdocumentid, approvedQuanity, productid, driver);
		StockRepair.stockRepair_reject(newdocumentid, rejectedQuanity, productid, driver);
	}
	
	@Test(priority = 65, enabled = false)
	public void verifyReports_StockAdjustmentQA() throws InterruptedException, AWTException, IOException {
		String availableQty = "11.0000";
		String blockedQty = "0.0000";
		String underQA = "1.0000";
		String underRepair = "1.0000";
		String balQty = "13.0000";
		ReportProductMaster.validate_ProductMaster(productid, availableQty, balQty, driver);
		StockAvailabilityByWarehouse.validate_StockAvailabilityByWarehouse(productid, availableQty, blockedQty, underQA,
				underRepair, driver);
		StockLedgerReport.validate_StockLedgerReport(productid, availableQty, driver);
	}
	

	// ------------------- Stock Request -------------------
	@Test(priority = 70)
	public void create_StockRequest() throws InterruptedException, AWTException, IOException {
		String forStore = "DS - Default Store";
		String reqQuanityValue = "1";
		StockRequest.create_StockRequest(documentid, productid, forStore, reqQuanityValue, driver);
	}

	@Test(priority = 71)
	public void StockRequest_IssueandCollect() throws InterruptedException, AWTException, IOException {
		String issuedQuanity = "1";
		String collectedQuanity = "1";
		String selectLocation = "Knagar";
		StockIssue.Issue_StoreOrders("StockRequest" + documentid, issuedQuanity, driver);
		StockRequest.collect_StockRequest("StockRequest" + documentid, collectedQuanity, selectLocation, driver);
	}

	@Test(priority = 72)
	public void validate_StockRequest() throws InterruptedException, AWTException, IOException {
		StockRequest.Fulfilled_Orders_StockRequest("StockRequest" + documentid, productid, "1", "1", "1", driver);
	}
	
	@Test(priority = 73, enabled = false)
	public void verifyReports_StockRequest() throws InterruptedException, AWTException, IOException {
		String availableQty = "11.0000";
		String blockedQty = "0.0000";
		String underQA = "1.0000";
		String underRepair = "1.0000";
		String balQty = "13.0000";
		ReportProductMaster.validate_ProductMaster(productid, availableQty, balQty, driver);
		StockAvailabilityByWarehouse.validate_StockAvailabilityByWarehouse(productid, availableQty, blockedQty, underQA,
				underRepair, driver);
		StockLedgerReport.validate_StockLedgerReport(productid, availableQty, driver);
	}

	// ------------------- Stock Issue -------------------
	@Test(priority = 80)
	public void create_StockIssue() throws InterruptedException, AWTException, IOException {
		String fromStore = "DS - Default Store";
		String toStore = "PN - Pune";
		String quanity = "1";
		StockIssue.create_StockIssue(documentid, productid, fromStore, toStore, quanity, driver);
	}

	@Test(priority = 81)
	public void validate_StockIssue() throws InterruptedException, AWTException, IOException {
		String fromStore = "Default Store";
		String toStore = "Pune";
		String IssueQty = "1";
		String DeliveredQty = "1";
		StockIssue.FulfilledOrders_StockIssue("StockIssue" + documentid, productid, fromStore, toStore, IssueQty,
				DeliveredQty, driver);
	}
	
	@Test(priority = 85, enabled = false)
	public void verifyReports_StockIssue() throws InterruptedException, AWTException, IOException {
		String availableQty = "11.0000";
		String blockedQty = "0.0000";
		String underQA = "1.0000";
		String underRepair = "1.0000";
		String balQty = "13.0000";
		StockAvailabilityByWarehouse.validate_StockAvailabilityByWarehouse(productid, availableQty, blockedQty, underQA,
				underRepair, driver);
		StockLedgerReport.validate_StockLedgerReport(productid, availableQty, driver);
		ReportProductMaster.validate_ProductMaster(productid, availableQty, balQty, driver);
	}
	
	// ------------------- Inter Store Stock Transfer -------------------
	@Test(priority = 90)
	public void create_InterStoreStockTransfer() throws InterruptedException, AWTException, IOException {
		String fromStore = "DS";
		String toStore = "PN";
		String quanityValue = "1";
		InterStoreStockTransfer.create_InterStoreStockTransfer(documentid, productid, fromStore, toStore, quanityValue,
				driver);
	}
	
	@Test(priority = 91)
	public void acceptANDreturn_InterStoreStockTransfer() throws InterruptedException, AWTException, IOException {
		String acceptedQuanity = "1";
		String toLocation = "Default Location";
		String acceptReturnQty = "1";
		InterStoreStockTransfer.accept_InterStoreStockTransfer(documentid, acceptedQuanity, toLocation, driver);
		InterStoreStockTransfer.acceptReturnedQty_InterStoreStockTransfer(documentid, acceptReturnQty, driver);
	}

	@Test(priority = 92)
	public void validate_InterStoreStockTransfer() throws InterruptedException, AWTException, IOException {
		String fromStore = "Default Store";
		String toStore = "Pune";
		String IssueQty = "1";
		String CollectedQty = "1";
		InterStoreStockTransferDetailsRPT.validate_InterStoreTransferDetails(documentid, productid, fromStore, toStore,
				IssueQty, CollectedQty, driver);
	}

	// ------------------- Inter Location Stock Transfer -------------------
	@Test(priority = 100)
	public void create_InterLocationStockTransfer() throws InterruptedException, AWTException, IOException {
		String fromStore = "DS";
		String toLocation = "Knagar";
		String interLocStkTrnsferQtyValue = "1";
		InterLocationStockTransfer.create_InterLocationStockTransfer(documentid, productid, fromStore, toLocation,
				interLocStkTrnsferQtyValue, driver);
	}

	@Test(priority = 101)
	public void validate_InterLocationStockTransfer() throws InterruptedException, AWTException, IOException {
		String qty = "1.0000";
		String fromStore = "W001";
		String fromLocation = "Default Location";
		String toLocation = "Knagar";
		InterLocationStockTransferDetailsReport.validate_InterLocationTransferDetails(documentid, productid, fromStore,
				fromLocation, toLocation, qty, driver);
	}

	// ------------------- Cycle Count -------------------
	@Test(priority = 120)
	public void testCycleCountCalendar() throws InterruptedException, AWTException, IOException {
		CycleCountCalendar.setCycleCountFrequencies(cycleCountDate, driver);
	}

	@Test(priority = 121)
	public void testCycleCountForm() throws InterruptedException, AWTException, IOException {
		String store = "DS";
		String cycleQty = "2";
		String cycleBatch = "B12";
		CycleCountForm.create_CycleCountForm(documentid, productid, store, cycleCountDate, cycleQty, cycleBatch,
				driver);
	}
	
	@Test(priority = 130, enabled = false)
	public void verifyReports_FullInventoryRun() throws InterruptedException, AWTException, IOException {
		String availableQty = "4.0000";
		String blockedQty = "0.0000";
		String underQA = "1.0000";
		String underRepair = "1.0000";
		String balQty = "6.0000";
		ReportProductMaster.validate_ProductMaster(productid, availableQty, balQty, driver);
		StockAvailabilityByWarehouse.validate_StockAvailabilityByWarehouse(productid, availableQty, blockedQty, underQA,
				underRepair, driver);
		StockLedgerReport.validate_StockLedgerReport(productid, availableQty, driver);
	}	
	
}
