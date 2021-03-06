package krawler.erp.testCases.SmokeTestSuiteSG.A;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import krawler.erp.utils.MandotoryChecks;
import krawler.erp.utils.MasterConfiguration;

import krawler.erp.UserPermission.LoginSetup;
import krawler.erp.UserPermission.UserAdministrationMiscellaneous;
import krawler.erp.UserPermission.UserAdministrationOther;
import krawler.erp.UserPermission.UserAdministrationPurchase;
import krawler.erp.UserPermission.UserAdministrationSales;
import krawler.erp.UserPermission.UserAdmistrationMaster;
import krawler.erp.inventory.InvUtilities;
import krawler.erp.page.ChartOfAccount;
import krawler.erp.page.CustomerMaster;
import krawler.erp.page.ImportVendor;
import krawler.erp.page.Login;
import krawler.erp.page.ProductMaster;
import krawler.erp.page.Utilities;
import krawler.erp.page.VendorMaster;

public class PrerequiristSettings extends BaseSetUp {
	@BeforeTest(groups = { "Setting" })
	public void ERPMandotoryChecks() throws InterruptedException, IOException, AWTException {
		// Run > MandotoryChecks < for first time only
		MandotoryChecks.ERP_MandotoryChecks(driver);
	}

	@Test(priority = 3, groups = { "Setting" })
	public void Disable_dimension() throws InterruptedException, AWTException, IOException {
		MasterConfiguration.disabledim(driver);
	}

	@Test(priority = 4, groups = { "PreDelete" })
	public void Delete_Import() throws InterruptedException, IOException, AWTException {
		ImportVendor.deleteImport_Vendors(driver);
		CustomerMaster.deleteImport_Customers(driver);
		ProductMaster.deleteImport_Products(driver);
	}

	/*
	 * @Test (priority = 5, groups={"Userpermission"}) public void
	 * UserPermission() throws InterruptedException, IOException, AWTException {
	 * UserAdmistrationMaster.UserPermission_ForMaster(driver);
	 * UserAdministrationPurchase.UserPermission_ForPurchase(driver);
	 * UserAdministrationSales.UserPermission_ForSales(driver);
	 * UserAdministrationMiscellaneous.UserPermission_ForMiscellaneous(driver);
	 * UserAdministrationOther.UserPermission_ForOther(driver);
	 * 
	 * LoginSetup.Signout(driver); LoginSetup.SignIn("Kapil", "1234", driver); }
	 */

}
