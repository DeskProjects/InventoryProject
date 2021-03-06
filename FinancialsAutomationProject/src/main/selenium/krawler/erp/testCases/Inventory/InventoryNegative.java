package krawler.erp.testCases.Inventory;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import krawler.erp.inventory.GoodsReceipt;
import krawler.erp.inventory.InventoryDO;
import krawler.erp.inventory.InventoryProductMaster;
import krawler.erp.inventory.StockRepair;
import krawler.erp.page.Utilities;

public class InventoryNegative extends BaseSetUp {

	public String productid = "NegPrd4sep";
	private String batchValue = "AB";
	private String serialSeq = "Asr";

	@Test(priority = 500)
	public void setNegative_ON() throws InterruptedException, AWTException, IOException {
		InventoryDO.enable_NegativeDO(true, driver);
	}

	@Test(priority = 501)
	public void Create_NormalProduct() throws InterruptedException, IOException, AWTException {
		Utilities.disableExpander(driver);
		InventoryProductMaster.create_Product(productid, "Normal", purchaseAccount, salesAccount, driver);
		InventoryProductMaster.create_Product(productid + "B2", "Batch", purchaseAccount, salesAccount, driver);
		InventoryProductMaster.create_Product(productid + "S3", "Serial", purchaseAccount, salesAccount, driver);
		InventoryProductMaster.create_Product(productid + "BS4", "BatchSerial", purchaseAccount, salesAccount, driver);
		InventoryProductMaster.verify_allProducts(productid, driver);
	}

	// ****************************>DO<***************************************************
	@Test(priority = 510)
	public void create_DOwithoutQA() throws InterruptedException, IOException, AWTException {
		String actQty = "3";
		String delQty = "3";
		InventoryDO.create_NegativeDO(false, documentid, productid, customerid, actQty, delQty, driver, batchValue,
				serialSeq);
	}

	@Test(priority = 520)
	public void create_DOwithQA() throws InterruptedException, IOException, AWTException {
		String actQty = "6";
		String delQty = "6";
		InventoryDO.create_NegativeDO(true, "QA" + documentid, productid, customerid, actQty, delQty, driver,
				batchValue, serialSeq);
	}

	@Test(priority = 521)
	public void DO_QAapproveANDreject() throws InterruptedException, AWTException, IOException {
		String approvedQuanity = "2";
		String rejectedQuanity = "3";
		String newdocumentid = "NegDoQA" + documentid;
		GoodsReceipt.approveRequest(newdocumentid, approvedQuanity, productid, driver);
		GoodsReceipt.rejectRequest(newdocumentid, rejectedQuanity, productid, driver, false);
	}

	@Test(priority = 522)
	public void DO_QARepair_ApproveandReject() throws InterruptedException, AWTException, IOException {
		String approvedQuanity = "1";
		String rejectedQuanity = "1";
		String newdocumentid = "NegDoQA" + documentid;
		StockRepair.stockRepair_approve(newdocumentid, approvedQuanity, productid, driver);
		StockRepair.stockRepair_reject(newdocumentid, rejectedQuanity, productid, driver);
	}

}
