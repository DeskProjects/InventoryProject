package krawler.erp.testCases.Inventory;

import java.awt.AWTException;
import java.io.IOException;
import java.util.*;

import krawler.erp.modules.SalesReturn1;
import krawler.erp.page.AuditTrailTest1;
import krawler.erp.page.Login;
import krawler.erp.page.Utilities;
import krawler.erp.OpeningDocuments.CustomerMasterForOpening;
import krawler.erp.inventory.reports.BatchwiseStockTracking;
import krawler.erp.inventory.reports.DatewiseStockTracking;
import krawler.erp.inventory.reports.StockLedgerReport;
import krawler.erp.inventory.reports.StockStatusReport;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.krawler.erp.inventory.reports.SR.DatewiseBatchStockTracking;
import com.krawler.erp.inventory.reports.SR.ReportProductMaster;
import com.krawler.erp.inventory.reports.SR.SalesReturnOnly;
import com.krawler.erp.inventory.reports.SR.StockAgeingReport;
import com.krawler.erp.inventory.reports.SR.StockAvailabilitySummaryReport;
import com.krawler.erp.inventory.reports.SR.StockReport;
import com.krawler.erp.inventory.reports.SR.StockValuationDetailReport;

public class SalesReturn

{
	public static WebDriver driver;
	public static String documentid = "Document27Dec";
	public static String customerid = "Customer27Dec";
	// public static String VendorID= "Vendor19";
	public static String productid = "Product27Dec";

	String OpeningStock = "";
	String StockIN = "";
	String StockOut = "";
	String blockedQty = "0.0000";
	String batch = "";
	String underQA = "0.00";
	String underRepair = "0.00";
	String selectStore = "default";
	String AvailableQuantity = "1010.00";
	String BlockQuantity = "0.00";
	String BalanceQuantity = "1010.00";

	// Login into ERP Application
	@BeforeTest
	public void beforeTestLogin() throws InterruptedException, IOException, AWTException {
		Login ln = new Login();
		WebDriver driver = Login.loginERP("http://192.168.0.208:8080/dotcomsmoketesting/a/mydebugtest1/", "admin",
				"1234", 10);
		this.driver = driver;
		Utilities.clickExpander(driver);

	}

	@BeforeMethod
	public void expander() throws InterruptedException, AWTException {
		String LoadingIcon = "//*[contains(text(),'Loading.')]";
		driver.navigate().refresh();
		Utilities.isElementGone(LoadingIcon, 60, driver);

	}

	@AfterMethod
	public void afterTest() throws AWTException, InterruptedException {
		System.out.println("\n");

	}

	// Sales Return Only
	@Test(priority = 11)
	public void Create_SROnly() throws InterruptedException, IOException, AWTException {
		SalesReturnOnly.create_SalesReturnOnly(documentid, productid, customerid, driver);
		Utilities.refresh();
	}

	// Sales Return Verify
	@Test(priority = 20)
	public void Verify_SROnly() throws InterruptedException, IOException, AWTException {
		SalesReturnOnly.verify_salesReturnOnly("SRO" + documentid, productid, customerid, driver);
		Utilities.refresh();
	}

	// Sales return CopyEditDelete
	@Test(priority = 30)
	public void CopyEditDelete_SROnly() throws InterruptedException, IOException, AWTException {
		SalesReturnOnly.Copy_SalesReturn(false, "SRO" + documentid, productid, driver);
		SalesReturnOnly.Edit_SalesReturn(false, "SRO" + documentid, driver, productid);
		SalesReturnOnly.Delete_SalesReturn("SRO" + documentid, driver);
	}

	// ---------------------------- REPORTS VERIFICATION ---------------------
	// //

	// --------------------- For Stock Ageing Report --------------- //

	// @Test(priority = 50)
	// public void StockAgeing_SR() throws
	// InterruptedException,IOException,AWTException
	// {
	// StockAgeingReport.validate_StockAgeingReport(productid, productsQtyMap,
	// driver);
	// }

	// ------------------ For Stock Valuation Detail Report -------------- //

	// @Test(priority = 60)
	// public void StockValuation_SR() throws
	// InterruptedException,IOException,AWTException
	// {
	// String balanceQty="0.0";
	// StockValuationDetailReport.productsQtyMap(productid, balanceQty, driver);
	// }

	// --------------For -------------//

	// --- Stock Report Validation ---//
	//
	// @Test(priority = 70)
	// public void StockReport_SR() throws
	// InterruptedException,IOException,AWTException
	// {
	// String qtyOnHand ="0.0";
	// StockReport.validate_StockReport(productid, OpeningStock, StockIN,
	// StockOut, qtyOnHand, driver);
	//
	// }

	// --- Stock Availability For SR Sales Return ----//

	@Test(priority = 80)
	public void StockAvail_SR() throws InterruptedException, IOException, AWTException {
		String balanceQty = "1010.0000";
		StockAvailabilitySummaryReport.validate_QuantitiesForProduct(productid, balanceQty, blockedQty, driver);
		Utilities.refresh();
	}

	// ---Stock Status Report --------- //

	// @Test(priority = 90)
	// public void StockStatus_SR() throws InterruptedException, AWTException,
	// IOException
	// {
	// String qtyOnHand="1010.0000";
	// StockStatusReport.validate_StockStatusReport(productid, qtyOnHand,
	// driver);
	// Utilities.refresh();
	//
	// }
	// ------BatchWise Stock ----//

	// @Test(priority = 100)
	// public void BatchWiseStock_SR() throws InterruptedException,
	// AWTException, IOException
	// {
	// BatchwiseStockTracking.validate_BatchwiseStockTracking(productid,
	// balanceQty, batch, driver);
	//
	// }

	// -----DateWise Batch Stock ---//

	// @Test(priority = 110)
	// public void DateWiseBatchStock_SR() throws InterruptedException,
	// AWTException, IOException
	// {
	// DatewiseBatchStockTracking.validate_DatewiseBatchStockTracking(productid,
	// selectStore, balanceQty, batch, driver);
	// }

	// ----DateWise Stock -----//

	// @Test(priority=120)
	// public void DateWiseStock_SR() throws
	// InterruptedException,AWTException,IOException
	// {
	// DatewiseStockTracking.validate_DatewiseStockTracking(productid,
	// balanceQty, underQA, underRepair, driver);
	// }

	// ------- Stock Ledger Report ---- //
	//

	@Test(priority = 140)
	public void StockLedger_Report() throws InterruptedException, AWTException, IOException {
		String expectedQty = "1010.0000";
		StockLedgerReport.validate_StockLedgerReport(productid, expectedQty, driver);
	}
	//
	// --- Product Master ---//

	@Test(priority = 150)
	public void ProductMaster_SR() throws InterruptedException, AWTException, IOException {
		String expavailableQty = "";
		String expBalanceQty = "";
		ReportProductMaster.validate_ProductMaster(productid, expavailableQty, expBalanceQty, driver);
	}
	//

}
