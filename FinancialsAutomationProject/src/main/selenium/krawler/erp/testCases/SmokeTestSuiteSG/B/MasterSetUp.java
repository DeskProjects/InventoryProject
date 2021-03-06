package krawler.erp.testCases.SmokeTestSuiteSG.B;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.Test;
import krawler.erp.Miscellaneous.ChequeLayoutSetup;
import krawler.erp.Miscellaneous.PaymentMethod;
import krawler.erp.Miscellaneous.Taxes;
import krawler.erp.page.AuditTrailTest1;
import krawler.erp.page.ChartOfAccount;
import krawler.erp.page.CustomDesigner;
import krawler.erp.page.CustomerMaster;
import krawler.erp.page.ImportCustomer;
import krawler.erp.page.ImportProduct;
import krawler.erp.page.ImportVendor;
import krawler.erp.page.ProductMaster;
import krawler.erp.page.Utilities;
import krawler.erp.page.VendorMaster;

public class MasterSetUp extends BaseSetUp {

	// ************ Vendor ****************************
	@Test(priority = 10, groups = { "Create" })
	public void Create_VendorMaster() throws InterruptedException, IOException, AWTException {
		VendorMaster.create_Vendor(vendorid, driver);
	}

	@Test(priority = 11, groups = { "Verify" })
	public void Verify_VendorMaster() throws InterruptedException, IOException, AWTException {
		VendorMaster.verify_Vendor(vendorid, driver);
	}

	@Test(priority = 12, groups = { "CED" })
	public void CopyEditDelete_VendorMaster() throws InterruptedException, IOException, AWTException {
		VendorMaster.CopyEditDelete_Vendor(vendorid, driver);
	}

	@Test(priority = 13, groups = { "Audit Trail" })
	public void AuditTrail_VendorMaster() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate5(vendorid, "Vendor", driver);
	}

	@Test(priority = 14, groups = { "Import" })
	public void Import_VendorMaster() throws InterruptedException, IOException, AWTException {
		ImportVendor.import_vendor(vendorid, "csv", dateType1, driver);
		Utilities.refresh();
		ImportVendor.import_vendor(vendorid, "xls", dateType1, driver);
	}

	@Test(priority = 15, groups = { "Export" })
	public void Export_VendorMaster() throws InterruptedException, IOException, AWTException {
		Utilities.refresh();
		String searchVendors = "Automation";
		VendorMaster.Export_Vendor(driver);
	}

	// ************ Customer ****************************

	@Test(priority = 20, groups = { "Create" })
	public void Create_CustomerMaster() throws InterruptedException, IOException, AWTException {
		CustomerMaster.create_Customer(customerid, driver);
	}

	@Test(priority = 21, groups = { "Verify" })
	public void Verify_CustomerMaster() throws InterruptedException, IOException, AWTException {
		CustomerMaster.verify_Customer(customerid, driver);
	}

	@Test(priority = 22, groups = { "CED" })
	public void CopyEditDelete_CustomerMaster() throws InterruptedException, IOException, AWTException {
		CustomerMaster.CopyEditDelete_Customer(customerid, driver);
	}

	@Test(priority = 23, groups = { "Audit Trail" })
	public void AuditTrail_CustomerMaster() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate4(customerid, "Customer", driver);
	}

	@Test(priority = 24, groups = { "Import" })
	public void Import_CustomerMaster() throws InterruptedException, IOException, AWTException {
		ImportCustomer.import_customer(customerid, "csv", dateFormat2, driver);
		ImportCustomer.import_customer(customerid, "xls", dateFormat2, driver);
	}

	@Test(priority = 25, groups = { "Export" })
	public void Export_CustomerMaster() throws InterruptedException, IOException, AWTException {
		String searchCustomers = "Automation";
		CustomerMaster.Export_Customer(driver);
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------
	// Product Master
	@Test(priority = 30, groups = { "Create" })
	public void Create_ProductMaster() throws InterruptedException, IOException, AWTException {
		ProductMaster.create_product(productid, documentid, driver);
		ProductMaster.create_product(productid + "Ex", documentid, driver);
		ProductMaster.create_product("EditProduct", documentid, driver);
	}

	@Test(priority = 31, groups = { "Create" })
	public void set_PurchasePriceList() throws InterruptedException, IOException, AWTException {
		String[] productIDs = { productid, productid + "Ex", "EditProduct" };
		String priceType = "Purchase Price";
		ProductMaster.setPriceList_MultipleProducts(productIDs, priceType, "100", driver);
	}

	@Test(priority = 32, groups = { "Verify" })
	public void Verify_ProductMaster() throws InterruptedException, IOException, AWTException {
		// add from different pkg
	}

	@Test(priority = 33, groups = { "CED" })
	public void CopyEditDelete_ProductMaster() throws InterruptedException, IOException, AWTException {
		ProductMaster.CopyEditDelete_NormalProduct(productid, driver);
	}

	@Test(priority = 34, groups = { "Audit Trail" })
	public void AuditTrail_ProductMaster() throws InterruptedException, IOException, AWTException {
		AuditTrailTest1.Audit_testCreate6(productid, "Product", driver);
	}

	@Test(priority = 35, groups = { "Import" })
	public void Import_ProductMaster() throws InterruptedException, IOException, AWTException {
		ImportProduct.importProduct(productid, "csv", dateType1, driver);
		Utilities.refresh();
		ImportProduct.importProduct(productid, "xls", dateType1, driver);
	}

	@Test(priority = 36, groups = { "Export" })
	public void Export_ExportMaster() throws InterruptedException, IOException, AWTException {
		Utilities.refresh();
		String searchProducts = "Automation";
		ProductMaster.Export_Product(driver);
	}

	// Chart of Account
	@Test(priority = 40, groups = { "Create" })
	public void Create_COA() throws InterruptedException, IOException, AWTException {
		ChartOfAccount.create_ChartOfAccount("COA" + documentid, "Balance Sheet", "General Ledger", "Bank", driver);
		ChartOfAccount.create_ChartOfAccount("Purchase" + documentid, "Profit & Loss", "General Ledger", "Expense",
				driver);
		ChartOfAccount.create_ChartOfAccount("Sales" + documentid, "Profit & Loss", "General Ledger", "Income", driver);
		ChartOfAccount.create_ChartOfAccount("GST" + documentid, "Profit & Loss", "GST", "Income", driver);
	}

	@Test(priority = 41, groups = { "Verify" })
	public void Verify_COA() throws InterruptedException, IOException, AWTException {
		ChartOfAccount.verify_ChartOfAccount(documentid, driver);
	}

	@Test(priority = 42, groups = { "CED" })
	public void CopyEditDelete_COA() throws InterruptedException, IOException, AWTException {
		ChartOfAccount.create_ChartOfAccount("Extra" + documentid, "Balance Sheet", "General Ledger", "Bank", driver);
		ChartOfAccount.edit_delete_ChartOfAccount("Extra" + documentid, driver);
	}

	@Test(priority = 43, groups = { "Import" })
	public void Import_COA() throws InterruptedException, IOException, AWTException {
		ChartOfAccount.import_ChartOfAccount("csv", driver);
		Utilities.refresh();
		ChartOfAccount.import_ChartOfAccount("xls", driver);
	}

	@Test(priority = 44, groups = { "Export" })
	public void Export_COA() throws InterruptedException, IOException, AWTException {
		Utilities.refresh();
		ChartOfAccount.Export_COA(driver);
	}

	// Tax
	@Test(priority = 50, groups = { "Create" })
	public void Create_Tax() throws InterruptedException, IOException, AWTException {
		Taxes.create_Tax(documentid, "8", "GST" + documentid, driver);
	}

	@Test(priority = 51, groups = { "Verify" })
	public void Verify_Tax() throws InterruptedException, IOException, AWTException {
		Taxes.verify_Tax(documentid, "10", driver);
	}

	@Test(priority = 52, groups = { "CED" })
	public void CopyEditDelete_Tax() throws InterruptedException, IOException, AWTException {
		// Taxes.delete_Tax(documentid, "10", driver);
	}

	// Payment Method
	@Test(priority = 60, groups = { "Create" })
	public void create_PaymentMethod() throws InterruptedException, IOException, AWTException {
		String accName = "Bank";
		PaymentMethod.create_PaymentMethod(documentid, accName, "Yes", "Yes", driver);
		PaymentMethod.create_PaymentMethod(documentid + "Ex", accName, "Yes", "Yes", driver);
		PaymentMethod.verify_EditDeletePaymentMethod(documentid + "Ex", accName, driver);
	}

	@Test(priority = 61, groups = { "Create" })
	public void create_ChequeLayout() throws InterruptedException, IOException, AWTException {
		ChequeLayoutSetup.create_ChequeLayout(documentid, driver);
	}

	@Test(priority = 65, groups = { "Create" })
	public void Custom_Designer_Print() throws InterruptedException, IOException, AWTException {
		// ("Custom_Designer_Print");
		String docID = "CS" + documentid;
		String DesignedName = "Extended Border SI,Pre Printed SI,SI Default Template";

		String ModuleName = "Sales Invoice";
		String fileNamewithExt = "Template_SI Default .xlsx,Template_Extended Border SI .xlsx,Template_Pre Printed SI  .xlsx";
		CustomDesigner.create_CustomDesigner(ModuleName, fileNamewithExt, driver);
		// CustomDesigner.Print_CustomDesigner(docID, DesignedName, customerid,
		// driver);
	}
	// add Logo - Dimension disable -

}
