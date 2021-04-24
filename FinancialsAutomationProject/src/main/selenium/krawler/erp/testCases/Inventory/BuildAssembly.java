package krawler.erp.testCases.Inventory;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import krawler.erp.inventory.InventoryCS;
import krawler.erp.inventory.InventoryDO;
import krawler.erp.inventory.InventoryProductMaster;
import krawler.erp.inventory.JobWorkOut;
import krawler.erp.inventory.reports.StockLedgerReport;
import krawler.erp.page.CustomerMaster;
import krawler.erp.page.VendorMaster;
import krawler.erp.inventory.reports.ReportProductMaster;
import krawler.erp.inventory.reports.StockAvailabilityByWarehouse;

public class BuildAssembly extends BaseSetUp {
	private String productid = BuildAsProducts;
	private String batchValue = "AB New";
	private String serialSeq = "Asr";

	// ************ Create 3 normal products with 500 quantity
	
	// ***************** AutoBuild ******************************

	@Test(priority = 410, enabled = true)
	public void create_AutoBuildDO() throws InterruptedException, IOException, AWTException {
		InventoryDO.create_AssemblyDeliveryOrder(false, documentid, productid, customerid, "10", "10", driver,
				batchValue, serialSeq);
	}
	
	
	@Test(priority = 412)
	public void create_AutoBuildCS() throws InterruptedException, IOException, AWTException {
		InventoryCS.create_AutoDoCashSale(documentid, productid, customerid, driver);
	}

	
	@Test(priority = 414)
	public void create_AutoBuildSI() throws InterruptedException, IOException, AWTException {
		InventoryCS.create_AutoDosalesInvoice(documentid, productid, customerid, driver);
	}

	@Test(priority = 418, enabled = false)
	public void Report_StockAvailabilityByWarehouse() throws InterruptedException, IOException, AWTException {
		String expbalanceQty = "470.0000,440.0000,410.0000,0.0000";
		String expblockedQty = "0.0000,0.0000,0.0000,0.0000";
		String expunderQAQty = "0.0000,0.0000,0.0000,0.0000";
		String expunderRepairQty = "0.0000,0.0000,0.0000,0.0000";
		StockAvailabilityByWarehouse.validate_StockAvailabilityByWarehouseBuilAssembly(driver, productid, expbalanceQty,
				expblockedQty, expunderQAQty, expunderRepairQty);
	}

	@Test(priority = 419, enabled = false)
	public void Report_ProductMaster() throws InterruptedException, IOException, AWTException {
		double expectedQty[] = { 470, 440, 410, 0 };
		String prods[] = { "BA" + productid + "A1", "BA" + productid + "A2", "BA" + productid + "A3",
				"AsB" + productid };
		ReportProductMaster.verify_ProductMaster_MultipleProducts(documentid, prods, expectedQty, driver);
	}

	@Test(priority = 420, enabled = false)
	public void Report_StockLedger() throws InterruptedException, IOException, AWTException {
		double expectedQty[] = { 470, 440, 410, 0 };
		String prods[] = { "BA" + productid + "A1", "BA" + productid + "A2", "BA" + productid + "A3",
				"AsB" + productid };
		StockLedgerReport.validate_StockLedgerBuilAssembly(documentid, prods, expectedQty, driver);
	}
	
	// **************************** JObWorkOrder Flow
	// *************************************************
	
	@Test(priority = 430)
	public void create_JobWorkOut_Order() throws InterruptedException, IOException, AWTException {
		JobWorkOut.CreateJobWorkOut_Order(documentid, vendorid, productid, driver);
	}
		
	
	@Test(priority = 433, enabled = true)
	public void createANDverify_JobWorkOut_StockTransfer() throws InterruptedException, IOException, AWTException {
		JobWorkOut.CreateJobWorkOut_StockTransfer(documentid, vendorid, productid, driver);
		JobWorkOut.verify_StockTransfer(documentid, vendorid, productid, driver);
	}

	@Test(priority = 435)
	public void verify_JobWorkOut_Order() throws InterruptedException, IOException, AWTException {
		JobWorkOut.JobWorkOut_AgeingReport(documentid, vendorid, productid, driver);
	}
	
	@Test(priority = 438)
	public void createANDverify_JobWorkOut_GR() throws InterruptedException, IOException, AWTException {
		JobWorkOut.CreateJobWorkOut_GoodsReceipt(documentid, vendorid, productid, driver);
	}
	
	
	
	@Test(priority = 440)
	public void createANDverify_JobWorkOut_PI() throws InterruptedException, IOException, AWTException {
		JobWorkOut.CreateJobWorkOut_Invoice(documentid, vendorid, productid, driver);
	}
	
	// ****************** Build Assemble *********************
	
	@Test(priority = 450, enabled = true)
	public void create_buildAssemble() throws InterruptedException, IOException, AWTException {
		InventoryProductMaster.create_BuildAssembly(documentid, productid, driver);
	}
	

	@Test(priority = 455)
	public void create_UNbuildAssemble() throws InterruptedException, IOException, AWTException {
		InventoryProductMaster.create_UnBuildAssembly(documentid, productid, driver);
	}

	@Test(priority = 460, enabled = false)
	public void Final_BuildAssemblyReport() throws InterruptedException, IOException, AWTException {
		double expectedQty[] = { 458, 416, 374, 12 };
		String prods[] = { "BA" + productid + "A1", "BA" + productid + "A2", "BA" + productid + "A3",
				"AsB" + productid };

		ReportProductMaster.verify_ProductMaster_MultipleProducts(documentid, prods, expectedQty, driver);
		StockLedgerReport.validate_StockLedgerBuilAssembly(documentid, prods, expectedQty, driver);

		String expbalanceQty = "458.0000,416.0000,374.0000,12.0000";
		String expblockedQty = "0.0000,0.0000,0.0000,0.0000";
		String expunderQAQty = "0.0000,0.0000,0.0000,0.0000";
		String expunderRepairQty = "0.0000,0.0000,0.0000,0.0000";
		StockAvailabilityByWarehouse.validate_StockAvailabilityByWarehouseBuilAssembly(driver, productid, expbalanceQty,
				expblockedQty, expunderQAQty, expunderRepairQty);
	}
	
	
	/*
	 * //******* Extra test cases ***************
	 * 
	 * @Test(priority=421, enabled =false) public void delete_BuildAssembly()
	 * throws InterruptedException, IOException, AWTException {
	 * InventoryProductMaster.delete_BuildAssembly(documentid, productid,
	 * driver); }
	 * 
	 * @Test(priority=421,enabled =false) public void EditDelette_AutoDO()
	 * throws InterruptedException, IOException, AWTException {
	 * InventoryProductMaster.EditDelete_DeliveryOrder("AutoDoSI"+documentid,
	 * driver); }
	 */
}
