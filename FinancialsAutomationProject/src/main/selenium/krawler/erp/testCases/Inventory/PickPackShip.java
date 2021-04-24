package krawler.erp.testCases.Inventory;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import krawler.erp.inventory.GoodsReceipt;
import krawler.erp.inventory.InventoryDO;
import krawler.erp.inventory.InventoryProductMaster;
import krawler.erp.inventory.PickPackShipModule;
import krawler.erp.page.Login;
import krawler.erp.utils.MandotoryChecks;

public class PickPackShip {

	public String documentid = "doc01aug";
	public static WebDriver driver;
	public static String batchValue = "PPS";
	public static String serialSeq = "PpSr"; // chnz serial each time

	public String productid = "PPS22oct";
	private String server1 = "http://192.168.0.208:8080/dotcomsmoketesting/a/pickpacktest/";
	private String server2 = "https://us2.accounting.deskera.com/a/spus2/";
	private String username = "admin";
	private String password = "1234";
	private String vendorid = "PPvendor";
	private String customerid = "PPcustomer";

	@Test(priority = 400, alwaysRun = true)
	public void loginURL_PickPackShip() throws InterruptedException, IOException, AWTException {
		driver = Login.loginERP(server2, username, password, 5);
	}

	@Test(priority = 410)
	public void create_abProduct() throws InterruptedException, IOException, AWTException {
		String purchaseAccount = "Purchases";
		String salesAccount = "Sales";
		InventoryProductMaster.create_Product(productid, "Normal", purchaseAccount, salesAccount, driver);
		InventoryProductMaster.create_Product(productid + "B2", "Batch", purchaseAccount, salesAccount, driver);
		InventoryProductMaster.create_Product(productid + "S3", "Serial", purchaseAccount, salesAccount, driver);
		InventoryProductMaster.create_Product(productid + "BS4", "BatchSerial", purchaseAccount, salesAccount, driver);
	}

	@Test(priority = 411)
	public void create_GRN() throws InterruptedException, IOException, AWTException {
		String actualQty = "10";
		String receiveQty = "10";
		GoodsReceipt.create_InventoryGR(false, productid, vendorid, documentid, actualQty, receiveQty, batchValue,
				serialSeq, driver);
	}

	@Test(priority = 412)
	public void create_PickDO() throws InterruptedException, IOException, AWTException {
		String actQty = "6";
		String delQty = "6";
		PickPackShipModule.create_PickDO(false, documentid, productid, customerid, actQty, delQty, driver, batchValue,
				serialSeq);
	}

	@Test(priority = 420)
	public void create_PackDO() throws InterruptedException, IOException, AWTException {
		String packQty = "4";
		PickPackShipModule.create_Pack(documentid, packQty, driver);
	}

	@Test(priority = 430)
	public void create_ShipDO() throws InterruptedException, IOException, AWTException {
		String shipQty = "4";
		PickPackShipModule.create_Ship(documentid, shipQty, driver);
	}

}
