package krawler.erp.testCases.Inventory;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import krawler.erp.inventory.InvUtilities;
import krawler.erp.modules.DeliveryOrderModule1;
import krawler.erp.page.Login;
import krawler.erp.page.Utilities;
import krawler.erp.utils.MandotoryChecks;

public class BaseSetUp {
	private static String inputData = "10OCT19";
	public String documentid = "doc" + inputData;

	public static WebDriver driver;
	public String InventoryProducts = "prod" + inputData;
	public String ErpProducts = "ERP" + inputData;
	public String BuildAsProducts = "Build" + inputData;
	public static String vendorid = "iVen" + inputData;
	public static String customerid = "iCus" + inputData;
	public static String purchaseAccount = "Purchases";
	public static String salesAccount = "Sales";

	@Parameters({ "server1", "username", "password" })
	@BeforeTest
	public void beforeTestLogin(String server1, String username, String password)
			throws InterruptedException, IOException, AWTException {
		Login ln = new Login();
		WebDriver driver = Login.loginERP(server1, username, password, 10);
		this.driver = driver;
		// Run > MandotoryChecks < for first time only
		MandotoryChecks.Inventory_MandotoryChecks(driver);
	}

	@BeforeMethod
	public void expander() throws InterruptedException, AWTException {
		String LoadingIcon = "//*[contains(text(),'Loading.')]";
		driver.navigate().refresh();
		Utilities.isElementGone(LoadingIcon, 60, driver);

	}

	@AfterMethod
	public void afterMethod() throws AWTException, InterruptedException {
		System.out.println("\n");
	}

	@AfterTest
	public void afterTest() throws AWTException, InterruptedException {
		driver.quit();
	}

	/*
	 * -------------------------- Set Up That we Need to do before starting
	 * automation suite ( on new/client URL )-------------------------- master
	 * -> inv master -> store and location ///////////// Create following Store
	 * with Description //////////////////////////// 
	 * STORE CODE - Description - Type 
	 * 1. DS 1. Default Store 1. Warehouse 
	 * 2. JOB WORK ORDER 2. Job Work Order 2. Warehouse 
	 * 3. QA 3. QA 3. Warehouse 
	 * 4. REPAIR 4. Repair 4. Repair
	 * 5. SCRAP 5. Scrap 5. Scrap 
	 * 6. PN 6. Pune 6. Warehouse
	 * 
	 * ///////////// Create following Location with Description
	 * //////////////////////////// Location Name - Description 
	 * 1. Default Location 1. DS , QA , REPAIR , JOB WORK ORDER , SCRAP , PN 
	 * 2. Knagar 2. DS , PN
	 * 
	 * ///////////// Go to System Preferences & set up the above mentioned store
	 * ///////////// 1. Store for QA Inspection : QA - QA 2. Store for Stock
	 * Repair : REPAIR - Repair 3. Scrap Store : SCRAP - Scrap 4. Vendor Job
	 * Order Store : JOB WORK ORDER - Job Work Order
	 * 
	 * ///////////// Go to Master - > Approval Rules - > QA approval
	 * ///////////// 1. Enable QA approval for : DS, QA, REPAIR & PN.
	 * 
	 * ///////////// REASON ///////////// 1. Create Reason with any name. 2. Set
	 * Default Location
	 * 
	 * ///////////// Sequence Format ///////////// 1. Create seq format ISST &
	 * Stock Adjustment
	 * 
	 * 
	 */

}
