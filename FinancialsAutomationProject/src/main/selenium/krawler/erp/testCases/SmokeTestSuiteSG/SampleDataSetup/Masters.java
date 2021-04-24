package krawler.erp.testCases.SmokeTestSuiteSG.SampleDataSetup;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import krawler.erp.page.ChartOfAccount;
import krawler.erp.page.CustomerMaster;
import krawler.erp.page.ProductMaster;
import krawler.erp.page.VendorMaster;

public class Masters extends BaseSetUpProper {

	@Test(priority = 11)
	public void CreateProducts() throws InterruptedException, IOException, AWTException {
		ProductMaster.create_Product_Proper("PID00001", "Computer", purchaseAccount, salesAccount, driver);
		ProductMaster.create_Product_Proper("PID00002", "Hard Drive", purchaseAccount, salesAccount, driver);
		ProductMaster.create_Product_Proper("PID00003", "KeyBoard Normal", purchaseAccount, salesAccount, driver);
		ProductMaster.create_Product_Proper("PID00004", "KeyBoard Mechanical", purchaseAccount, salesAccount, driver);
		ProductMaster.create_Product_Proper("PID00005", "Monitor", purchaseAccount, salesAccount, driver);
		ProductMaster.create_Product_Proper("PID00006", "Speaker", purchaseAccount, salesAccount, driver);
		ProductMaster.create_Product_Proper("PID00007", "Headphone", purchaseAccount, salesAccount, driver);
		ProductMaster.create_Product_Proper("PID00008", "Mouse", purchaseAccount, salesAccount, driver);
		ProductMaster.create_Product_Proper("PID00009", "Table", purchaseAccount, salesAccount, driver);
		ProductMaster.create_Product_Proper("PID000010", "Chair", purchaseAccount, salesAccount, driver);
	}

	@Test(priority = 21)
	public void CreateCustomers() throws InterruptedException, IOException, AWTException {
		CustomerMaster.create_Customer_Proper("CID00001", "John", driver);
		CustomerMaster.create_Customer_Proper("CID00002", "Calvin", driver);
		CustomerMaster.create_Customer_Proper("CID00003", "Cally", driver);
		CustomerMaster.create_Customer_Proper("CID00004", "Jack", driver);
		CustomerMaster.create_Customer_Proper("CID00005", "Arnold", driver);
		CustomerMaster.create_Customer_Proper("CID00006", "Peeter", driver);
		CustomerMaster.create_Customer_Proper("CID00007", "David", driver);
		CustomerMaster.create_Customer_Proper("CID00008", "Kalvin", driver);
		CustomerMaster.create_Customer_Proper("CID00009", "Michel", driver);
		CustomerMaster.create_Customer_Proper("CID000010", "Robert", driver);
	}

	@Test(priority = 31)
	public void CreateVendors() throws InterruptedException, IOException, AWTException {
		VendorMaster.create_Vendor_Proper("VID00001", "Victor", driver);
		VendorMaster.create_Vendor_Proper("VID00002", "Ned", driver);
		VendorMaster.create_Vendor_Proper("VID00003", "Rodger", driver);
		VendorMaster.create_Vendor_Proper("VID00004", "Mc Glain", driver);
		VendorMaster.create_Vendor_Proper("VID00005", "Rick", driver);
		VendorMaster.create_Vendor_Proper("VID00006", "Bob", driver);
		VendorMaster.create_Vendor_Proper("VID00007", "Martin", driver);
		VendorMaster.create_Vendor_Proper("VID00008", "Morrison", driver);
		VendorMaster.create_Vendor_Proper("VID00009", "Sandy", driver);
		VendorMaster.create_Vendor_Proper("VID000010", "Ben", driver);
	}

}
