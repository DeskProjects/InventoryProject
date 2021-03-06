package krawler.erp.IndianGST_TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import krawler.erp.IndianGST.GST_Customer_Master;
import krawler.erp.IndianGST.GST_Entity_Master;
import krawler.erp.IndianGST.GST_HSN_Master;
import krawler.erp.IndianGST.GST_Input_Rule;
import krawler.erp.IndianGST.GST_Output_Rule;
import krawler.erp.IndianGST.GST_Vendor_Master;
import krawler.erp.page.Login;

@Test
public class IndianGST_Basic_SetUp {
	public static String Entity = null;
	public static String HSN_Code = null;
	public static String Registered_Vendor_Code_IntraState = null;
	public static String Registered_Customer_Code_IntraState = null;
	public static WebDriver driver;
	public static String URL = "http://192.168.0.208:8080/dotcomsmoketesting/a/GSTAutomation/#";
	public static String username = "admin";
	public static String password = "8888";

	public void beforeTestLogin() throws InterruptedException, IOException, AWTException {
		Login ln = new Login();
		WebDriver driver = Login.loginERP(URL, username, password, 0);
		this.driver = driver;

	}

	@Test(priority = 1)
	public void Entity_Creation_for_India() throws InterruptedException, AWTException, IOException

	{
		GST_Entity_Master EM = new GST_Entity_Master();
		Entity = EM.create_GST_Entity(driver);

	}

	@Test(priority = 2)
	public void Entity_Edit_for_India() throws InterruptedException, AWTException, IOException

	{
		GST_Entity_Master EM = new GST_Entity_Master();
		EM.edit_GST_Entity(driver, Entity);

	}

	@Test(priority = 7)
	public void Entity_Delete_for_India() throws InterruptedException, AWTException, IOException

	{
		GST_Entity_Master EM = new GST_Entity_Master();
		EM.delete_GST_Entity(driver);

	}

	@Test(priority = 3)
	public void HSN_SAC_Creation_for_India() throws InterruptedException, AWTException, IOException

	{
		GST_HSN_Master HS = new GST_HSN_Master();
		HSN_Code = HS.create_GST_HSN(driver);

	}

	@Test(priority = 4)
	public void HSN_SAC_Edit_for_India() throws InterruptedException, AWTException, IOException

	{
		GST_HSN_Master HS = new GST_HSN_Master();
		HS.edit_GST_HSN(driver);

	}

	@Test(priority = 8)
	public void HSN_SAC_Delete_for_India() throws InterruptedException, AWTException, IOException

	{
		GST_HSN_Master HS = new GST_HSN_Master();
		HS.delete_GST_HSN(driver);

	}

	@Test(priority = 5)
	public void Import_Input_GST_Rule_for_India() throws InterruptedException, AWTException, IOException

	{
		GST_Input_Rule IR = new GST_Input_Rule();
		IR.Import_Input_GST_Rule(driver);

	}

	@Test(priority = 6)
	public void Import_Output_GST_Rule_for_India() throws InterruptedException, AWTException, IOException

	{
		GST_Output_Rule OR = new GST_Output_Rule();
		OR.Import_Output_GST_Rule(driver);

	}

	/*
	 * // Reg + Intra + Vendor
	 * 
	 * @Test(priority=3) public void
	 * Registered_IntraState_Vendor_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_Vendor_Code_IntraState =
	 * VM.createVendor_Registered_IntraState_Basic_SetUp( driver , Entity );
	 * 
	 * }
	 * 
	 * // Reg + Intra + Customer
	 * 
	 * @Test(priority=15) public void
	 * Registered_IntraState_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master();
	 * Registered_Customer_Code_IntraState =
	 * CM.createCustomer_Registered_IntraState( driver );
	 * 
	 * }
	 * 
	 * //createVendor_Registered_IntraState_Basic_SetUp
	 * 
	 * 
	 */

}
