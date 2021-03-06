package krawler.erp.IndianGST_TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import krawler.erp.IndianGST.GST_Advanced_Received;
import krawler.erp.IndianGST.GST_Credit_Note;
import krawler.erp.IndianGST.GST_Customer_Master;
import krawler.erp.IndianGST.GST_Debit_Note;
import krawler.erp.IndianGST.GST_Product_Master;
import krawler.erp.IndianGST.GST_Purchase_Invoice;
import krawler.erp.IndianGST.GST_Sales_Invoice;
import krawler.erp.IndianGST.GST_Vendor_Master;
import krawler.erp.page.Login;

@Test
public class Indian_GST_TC {

	public static String Vendor_Code = null;
	public static String Registered_Customer_Code_IntraState = null;
	public static String Registered_Customer_Code_InterState = null;
	public static String UnRegistered_Customer_Code_IntraState = null;
	public static String UnRegistered_Customer_Code_InterState = null;
	public static String Composition_Customer_Code_IntraState = null;
	public static String Composition_Customer_Code_InterState = null;
	public static String SEZ_WPAY = null;
	public static String SEZ_WOPAY = null;
	public static String Export_WPAY = null;
	public static String Export_WOPAY = null;
	public static String UnRegistered_Vendor_Code_Import = null;
	public static String Registered_Vendor_Code_IntraState = null;
	public static String Registered_Vendor_Code_InterState = null;
	public static String Registered_SEZ_WPAY_Vendor_Code_IntraState = null;
	public static String Registered_SEZ_WPAY_Vendor_Code_InterState = null;
	public static String Composition_Vendor_Code_IntraState = null;
	public static String Composition_Vendor_Code_InterState = null;
	public static String Normal_Product_ID = null;
	public static String NIL_Rated_Product_ID = null;
	public static String Exempted_Product_ID = null;
	public static String NonGST_Product_ID = null;
	public static String Service_Product_ID = null;
	public static String RCM_Product_ID = null;
	public static WebDriver driver;
	public static String URL = "http://192.168.0.208:8080/dotcomsmoketesting/a/GSTAutomation/#";
	public static String username = "admin";
	public static String password = "8888";

	public void beforeTestLogin() throws InterruptedException, IOException, AWTException {
		Login ln = new Login();
		WebDriver driver = Login.loginERP(URL, username, password, 0);
		this.driver = driver;

	}

	// ------------------------- Masters -----------------------------

	// Reg + Intra + Customer

	@Test(priority = 1)
	public void Registered_IntraState_Customer_Creationfor_India()
			throws InterruptedException, AWTException, IOException

	{
		GST_Customer_Master CM = new GST_Customer_Master();
		Registered_Customer_Code_IntraState = CM.createCustomer_Registered_IntraState(driver);

	}

	// Reg + Inter + Customer

	@Test(priority = 2)

	public void Registered_InterState_Customer_Creationfor_India()
			throws InterruptedException, AWTException, IOException

	{ // String Customer_Code = "CC00009" ; // Copy
		GST_Customer_Master CM = new GST_Customer_Master();
		Registered_Customer_Code_InterState = CM.copyCustomer_Registered_InterState(driver,
				Registered_Customer_Code_IntraState);

	}

	// Unreg + Intra + Customer

	@Test(priority = 3)
	public void UnRegistered_IntraState_Customer_Creationfor_India()
			throws InterruptedException, AWTException, IOException

	{
		GST_Customer_Master CM = new GST_Customer_Master();
		UnRegistered_Customer_Code_IntraState = CM.createCustomer_UnRegistered_IntraState(driver);

	}

	// Unreg + Inter + Customer

	@Test(priority = 4)

	public void UnRegistered_InterState_Customer_Creationfor_India()
			throws InterruptedException, AWTException, IOException

	{
		GST_Customer_Master CM = new GST_Customer_Master();
		UnRegistered_Customer_Code_InterState = CM.copyCustomer_UnRegistered_InterState(driver,
				UnRegistered_Customer_Code_IntraState);

	}

	// Composition + Intra + Customer

	@Test(priority = 5)
	public void Composition_IntraState_Customer_Creationfor_India()
			throws InterruptedException, AWTException, IOException

	{
		GST_Customer_Master CM = new GST_Customer_Master();
		Composition_Customer_Code_IntraState = CM.createCustomer_Composition_IntraState(driver);

	}

	// Composition + Inter + Customer

	@Test(priority = 6)

	public void Composition_InterState_Customer_Creationfor_India()
			throws InterruptedException, AWTException, IOException

	{
		GST_Customer_Master CM = new GST_Customer_Master();
		Composition_Customer_Code_InterState = CM.createCustomer_Composition_InterState(driver,
				Composition_Customer_Code_IntraState);

	}

	// Product with Product tax class

	@Test(priority = 7)
	public static void Product_Creationfor_India() throws InterruptedException, AWTException, IOException {
		GST_Product_Master PM = new GST_Product_Master();
		Normal_Product_ID = PM.createProduct_Normal(driver);

		System.out.println(Normal_Product_ID);

	}

	/*
	 * 
	 * // Reg + Intra + Customer
	 * 
	 * @Test(priority=1) public void
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
	 * // Product with Product tax class
	 * 
	 * @Test(priority=2) public static void Product_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException { GST_Product_Master PM =
	 * new GST_Product_Master(); Normal_Product_ID =
	 * PM.createProduct_Normal(driver);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * // Reg + Intra + Sales Invoice
	 * 
	 * @Test(priority=3) public static void
	 * SalesInvoice_Creationfor_India_Registered_IntraState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_IntraState(driver,
	 * Registered_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Customer
	 * 
	 * @Test(priority=4)
	 * 
	 * public void Registered_InterState_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * { //String Customer_Code = "CC00009" ; // Copy GST_Customer_Master CM =
	 * new GST_Customer_Master(); Registered_Customer_Code_InterState =
	 * CM.copyCustomer_Registered_InterState( driver ,
	 * Registered_Customer_Code_IntraState);
	 * 
	 * 
	 * }
	 * 
	 * // Reg + Inter + Sales Invoice
	 * 
	 * @Test(priority=5) public static void
	 * SalesInvoice_Creationfor_India_InterState() throws InterruptedException,
	 * AWTException, IOException { GST_Sales_Invoice SI = new GST_Sales_Invoice
	 * (); SI.createSalesInvoice_InterState(driver,
	 * Registered_Customer_Code_InterState, Normal_Product_ID); }
	 * 
	 * 
	 * // Unreg + Intra + Customer
	 * 
	 * @Test(priority=6) public void
	 * UnRegistered_IntraState_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master();
	 * UnRegistered_Customer_Code_IntraState =
	 * CM.createCustomer_UnRegistered_IntraState( driver );
	 * 
	 * }
	 * 
	 * // Unreg + Intra + Sales Invoice
	 * 
	 * @Test(priority=7) public static void
	 * SalesInvoice_Creationfor_India_UnRegistered_IntraState() throws
	 * InterruptedException, AWTException, IOException { GST_Sales_Invoice SI =
	 * new GST_Sales_Invoice (); SI.createSalesInvoice_IntraState(driver,
	 * UnRegistered_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Unreg + Inter + Customer
	 * 
	 * @Test(priority=8)
	 * 
	 * public void UnRegistered_InterState_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master();
	 * UnRegistered_Customer_Code_InterState =
	 * CM.copyCustomer_UnRegistered_InterState( driver ,
	 * UnRegistered_Customer_Code_IntraState);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Inter + Sales Invoice
	 * 
	 * @Test(priority=9) public static void
	 * SalesInvoice_Creationfor_India_UnRegistered_InterState() throws
	 * InterruptedException, AWTException, IOException { GST_Sales_Invoice SI =
	 * new GST_Sales_Invoice (); SI.createSalesInvoice_InterState(driver,
	 * UnRegistered_Customer_Code_InterState, Normal_Product_ID); }
	 * 
	 * 
	 * 
	 * // Composition + Intra + Customer
	 * 
	 * @Test(priority=10) public void
	 * Composition_IntraState_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master();
	 * Composition_Customer_Code_IntraState =
	 * CM.createCustomer_Composition_IntraState( driver );
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Intra + Sales Invoice
	 * 
	 * @Test(priority=11) public static void
	 * SalesInvoice_Creationfor_India_Composition_IntraState() throws
	 * InterruptedException, AWTException, IOException { GST_Sales_Invoice SI =
	 * new GST_Sales_Invoice (); SI.createSalesInvoice_IntraState(driver,
	 * Composition_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Inter + Customer
	 * 
	 * @Test(priority=12)
	 * 
	 * public void Composition_InterState_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master();
	 * Composition_Customer_Code_InterState =
	 * CM.createCustomer_Composition_InterState( driver ,
	 * Composition_Customer_Code_IntraState );
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Composition + Inter + Sales Invoice
	 * 
	 * @Test(priority=13) public static void
	 * SalesInvoice_Creationfor_India_Composition_InterState() throws
	 * InterruptedException, AWTException, IOException { GST_Sales_Invoice SI =
	 * new GST_Sales_Invoice (); SI.createSalesInvoice_InterState(driver,
	 * Composition_Customer_Code_InterState, Normal_Product_ID); }
	 * 
	 * 
	 * 
	 * // Reg + Intra + Credit Note
	 * 
	 * @Test(priority=14) public static void
	 * CreditNote_Creationfor_India_Registered_IntraState() throws
	 * InterruptedException, AWTException, IOException { GST_Credit_Note CN =
	 * new GST_Credit_Note (); CN.createCreditNote_IntraState(driver,
	 * Registered_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * // Reg + Inter + Credit Note
	 * 
	 * 
	 * @Test(priority=15) public static void
	 * CreditNote_Creationfor_India_Registered_InterState() throws
	 * InterruptedException, AWTException, IOException { GST_Credit_Note CN =
	 * new GST_Credit_Note (); CN.createCreditNote_InterState(driver,
	 * Registered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Intra + Credit Note
	 * 
	 * @Test(priority=16) public static void
	 * CreditNote_Creationfor_India_UnRegistered_IntraState() throws
	 * InterruptedException, AWTException, IOException { GST_Credit_Note CN =
	 * new GST_Credit_Note (); CN.createCreditNote_IntraState(driver,
	 * UnRegistered_Customer_Code_IntraState, Normal_Product_ID);
	 * System.out.println(UnRegistered_Customer_Code_InterState);
	 * 
	 * }
	 * 
	 * // UnReg + Inter + Credit Note
	 * 
	 * @Test(priority=17) public static void
	 * CreditNote_Creationfor_India_UnRegistered_InterState() throws
	 * InterruptedException, AWTException, IOException { GST_Credit_Note CN =
	 * new GST_Credit_Note (); CN.createCreditNote_InterState(driver,
	 * UnRegistered_Customer_Code_InterState, Normal_Product_ID);
	 * System.out.println(UnRegistered_Customer_Code_InterState);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Intra + Credit Note
	 * 
	 * @Test(priority=18) public static void
	 * CreditNote_Creationfor_India_Composition_IntraState() throws
	 * InterruptedException, AWTException, IOException { GST_Credit_Note CN =
	 * new GST_Credit_Note (); CN.createCreditNote_IntraState(driver,
	 * Composition_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * // Composition + Inter + Credit Note
	 * 
	 * @Test(priority=19) public static void
	 * CreditNote_Creationfor_India_Composition_InterState() throws
	 * InterruptedException, AWTException, IOException { GST_Credit_Note CN =
	 * new GST_Credit_Note (); CN.createCreditNote_InterState(driver,
	 * Composition_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * // Reg + Intra + Debit Note
	 * 
	 * @Test(priority=20) //20 public static void
	 * DebitNote_Creationfor_India_Registered_IntraState() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note (); DN.createDebitNote_IntraState(driver,
	 * Registered_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Debit Note
	 * 
	 * 
	 * @Test(priority=21) public static void
	 * DebitNote_Creationfor_India_Registered_InterState() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note (); DN.createDebitNote_InterState(driver,
	 * Registered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * //System.out.println(Registered_Customer_Code_InterState);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Intra + Debit Note
	 * 
	 * @Test(priority=22) public static void
	 * DebitNote_Creationfor_India_UnRegistered_IntraState() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note (); DN.createDebitNote_IntraState(driver,
	 * UnRegistered_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * // UnReg + Inter + Debit Note
	 * 
	 * @Test(priority=23) public static void
	 * DebitNote_Creationfor_India_UnRegistered_InterState() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note (); DN.createDebitNote_InterState(driver,
	 * UnRegistered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Intra + Debit Note
	 * 
	 * @Test(priority=24) public static void
	 * DebitNote_Creationfor_India_Composition_IntraState() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note (); DN.createDebitNote_IntraState(driver,
	 * Composition_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * // Composition + Inter + Debit Note
	 * 
	 * @Test(priority=25) public static void
	 * DebitNote_Creationfor_India_Composition_InterState() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note (); DN.createDebitNote_InterState(driver,
	 * Composition_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + Intra + Advanced Received
	 * 
	 * @Test(priority=26) public static void
	 * AdvancedReceipt_Creationfor_India_Registered_IntraState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_IntraState(driver,
	 * Registered_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * // Reg + Inter + Advanced Received
	 * 
	 * @Test(priority=27) public static void
	 * AdvancedReceipt_Creationfor_India_Registered_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_InterState(driver,
	 * Registered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * // UnReg + Intra + Advanced Received
	 * 
	 * @Test(priority=28) public static void
	 * AdvancedReceipt_Creationfor_India_UnRegistered_IntraState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_IntraState(driver,
	 * UnRegistered_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // UnReg + Inter + Advanced Received
	 * 
	 * @Test(priority=29) public static void
	 * AdvancedReceipt_Creationfor_India_UnRegistered_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_InterState(driver,
	 * UnRegistered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Intra + Advanced Received
	 * 
	 * @Test(priority=30) public static void
	 * AdvancedReceipt_Creationfor_India_Composition_IntraState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_IntraState(driver,
	 * Composition_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Composition + Inter + Advanced Received
	 * 
	 * @Test(priority=31) public static void
	 * AdvancedReceipt_Creationfor_India_Composition_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_InterState(driver,
	 * Composition_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Section - (3.1.B) Outward taxable supplies (zero rated)
	 * 
	 * // SEZ + WPAY + Customer
	 * 
	 * @Test(priority=32)
	 * 
	 * public void Registered_SEZ_WPAY_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master(); SEZ_WPAY =
	 * CM.createCustomer_SEZ_WPAY( driver);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Sales Invoice
	 * 
	 * @Test(priority=33) public static void
	 * SalesInvoice_Creationfor_India_SEZ_WPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_SEZ_WPAY_InterState(driver, SEZ_WPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WOPAY) + Inter + Customer
	 * 
	 * 
	 * @Test(priority=34)
	 * 
	 * public void Registered_SEZ_WOPAY_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master(); SEZ_WOPAY =
	 * CM.copyCustomer_SEZ_WOPAY( driver , SEZ_WPAY);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WOPAY) + Inter + Sales Invoice
	 * 
	 * @Test(priority=35) public static void
	 * SalesInvoice_Creationfor_India_SEZ_WOPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_SEZ_WOPAY_InterState(driver, SEZ_WOPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Credit Note
	 * 
	 * @Test(priority=36) public static void
	 * CreditNote_Creationfor_India_SEZ_WPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_SEZ_WPAY_InterState(driver, SEZ_WPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * // Reg + SEZ(WOPAY) + Inter + Credit Note
	 * 
	 * @Test(priority=37) public static void
	 * CreditNote_Creationfor_India_SEZ_WOPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_SEZ_WOPAY_InterState(driver, SEZ_WOPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Debit Note
	 * 
	 * @Test(priority=38) public static void
	 * DebitNote_Creationfor_India_SEZ_WPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_SEZ_WPAY_InterState(driver, SEZ_WPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * // Reg + SEZ(WOPAY) + Inter + Debit Note
	 * 
	 * @Test(priority=39) public static void
	 * DebitNote_Creationfor_India_SEZ_WOPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebittNote_SEZ_WOPAY_InterState(driver, SEZ_WOPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Advanced Receipt
	 * 
	 * @Test(priority=40) public static void
	 * AdvancedReceipt_Creationfor_India_SEZ_WPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_SEZ_WPAY_InterState(driver, SEZ_WPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * // Reg + SEZ(WOPAY) + Inter + Advanced Receipt // Transction is not
	 * appearing in 3B
	 * 
	 * @Test(priority=160) public static void
	 * AdvancedReceipt_Creationfor_India_SEZ_WOPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_SEZ_WOPAY_InterState(driver, SEZ_WOPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // Unreg. + Export(WPAY) + Customer
	 * 
	 * @Test(priority=42)
	 * 
	 * public void Registered_Export_WPAY_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master(); Export_WPAY =
	 * CM.createCustomer_Export_WPAY( driver);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Export(WOPAY) + Inter + Customer
	 * 
	 * 
	 * @Test(priority=43)
	 * 
	 * public void Registered_Export_WOPAY_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * {
	 * 
	 * GST_Customer_Master CM = new GST_Customer_Master(); Export_WOPAY =
	 * CM.copyCustomer_Export_WOPAY( driver , Export_WPAY);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + Export(WPAY) + Inter + Sales Invoice
	 * 
	 * @Test(priority=44) public static void
	 * SalesInvoice_Creationfor_India_Export_WPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_Export_WPAY_InterState(driver, Export_WPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // Reg + Export(WOPAY) + Inter + Sales Invoice
	 * 
	 * @Test(priority=45) public static void
	 * SalesInvoice_Creationfor_India_Export_WOPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_Export_WOPAY_InterState(driver, Export_WOPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + Export(WPAY) + Inter + Credit Note
	 * 
	 * @Test(priority=46) public static void
	 * CreditNote_Creationfor_India_Export_WPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_Export_WPAY_InterState(driver, Export_WPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * // Reg + Export(WOPAY) + Inter + Credit Note
	 * 
	 * @Test(priority=47) public static void
	 * CreditNote_Creationfor_India_Export_WOPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_Export_WOPAY_InterState(driver, Export_WOPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // Reg + Export(WPAY) + Inter + Debit Note
	 * 
	 * @Test(priority=48) public static void
	 * DebitNote_Creationfor_India_Export_WPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_Export_WPAY_InterState(driver, Export_WPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * // Reg + Export(WOPAY) + Inter + Debit Note
	 * 
	 * @Test(priority=49) public static void
	 * DebitNote_Creationfor_India_Export_WOPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebittNote_Export_WOPAY_InterState(driver, Export_WOPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // Reg + Export(WPAY) + Inter + Advanced Receipt
	 * 
	 * @Test(priority=50) public static void
	 * AdvancedReceipt_Creationfor_India_Export_WPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_Export_WPAY_InterState(driver, Export_WPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * // Reg + Export(WOPAY) + Inter + Advanced Receipt
	 * 
	 * 
	 * @Test(priority=51) public static void
	 * AdvancedReceipt_Creationfor_India_Export_WOPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_Export_WOPAY_InterState(driver, Export_WOPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Section - (3.1.C) Other outward supplies, (Nil rated, exempted)
	 * 
	 * 
	 * // Product with 0% Product tax class
	 * 
	 * @Test(priority=52) public static void
	 * NIL_Rated_Product_Creationfor_India() throws InterruptedException,
	 * AWTException, IOException { GST_Product_Master PM = new
	 * GST_Product_Master(); NIL_Rated_Product_ID =
	 * PM.createProduct_NIL_Rated(driver);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + Intra + Sales Invoice + NIL Rated
	 * 
	 * @Test(priority=53) public static void
	 * SalesInvoice_Creationfor_India_Registered_IntraState_NIL_Rated_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NIL_Rated_Exempted(driver,
	 * Registered_Customer_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + Inter + Sales Invoice + NIL Rated
	 * 
	 * @Test(priority=54) public static void
	 * SalesInvoice_Creationfor_India_Registered_InterState_NIL_Rated_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NIL_Rated_Exempted(driver,
	 * Registered_Customer_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * // UnReg + Intra + Sales Invoice + NIL Rated
	 * 
	 * @Test(priority=55) public static void
	 * SalesInvoice_Creationfor_India_UnRegistered_IntraState_NIL_Rated_Exempted
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NIL_Rated_Exempted(driver,
	 * UnRegistered_Customer_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // UnReg + Inter + Sales Invoice + NIL Rated
	 * 
	 * @Test(priority=56) public static void
	 * SalesInvoice_Creationfor_India_UnRegistered_InterState_NIL_Rated_Exempted
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NIL_Rated_Exempted(driver,
	 * UnRegistered_Customer_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Intra + Sales Invoice + NIL Rated
	 * 
	 * @Test(priority=57) public static void
	 * SalesInvoice_Creationfor_India_Composition_IntraState_NIL_Rated_Exempted(
	 * ) throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NIL_Rated_Exempted(driver,
	 * Composition_Customer_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Composition + Inter + Sales Invoice + NIL Rated
	 * 
	 * @Test(priority=58) public static void
	 * SalesInvoice_Creationfor_India_Composition_InterState_NIL_Rated_Exempted(
	 * ) throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NIL_Rated_Exempted(driver,
	 * Composition_Customer_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Intra + Credit Note + NIL Rated
	 * 
	 * @Test(priority=59) public static void
	 * CreditNote_Creationfor_India_Registered_IntraState_NIL_Rated_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NIL_Rated_Exempted(driver,
	 * Registered_Customer_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * // Reg + Inter + Credit Note + NIL Rated
	 * 
	 * 
	 * @Test(priority=60) public static void
	 * CreditNote_Creationfor_India_Registered_InterState_NIL_Rated_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NIL_Rated_Exempted(driver,
	 * Registered_Customer_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Intra + Credit Note + NIL Rated
	 * 
	 * @Test(priority=61) public static void
	 * CreditNote_Creationfor_India_UnRegistered_IntraState_NIL_Rated_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NIL_Rated_Exempted(driver,
	 * UnRegistered_Customer_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * // UnReg + Inter + Credit Note + NIL Rated
	 * 
	 * 
	 * @Test(priority=62) public static void
	 * CreditNote_Creationfor_India_UnRegistered_InterState_NIL_Rated_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NIL_Rated_Exempted(driver,
	 * UnRegistered_Customer_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * // Composition + Intra + Credit Note + NIL Rated
	 * 
	 * @Test(priority=63) public static void
	 * CreditNote_Creationfor_India_Composition_IntraState_NIL_Rated_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NIL_Rated_Exempted(driver,
	 * Composition_Customer_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * // Composition + Inter + Credit Note + NIL Rated
	 * 
	 * 
	 * @Test(priority=64) public static void
	 * CreditNote_Creationfor_India_Composition_InterState_NIL_Rated_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NIL_Rated_Exempted(driver,
	 * Composition_Customer_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + Intra + Debit Note + NIL Rated
	 * 
	 * @Test(priority=65) public static void
	 * DebitNote_Creationfor_India_Registered_IntraState_NIL_Rated_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_NIL_Rated_Exempted(driver,
	 * Registered_Customer_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Debit Note + NIL Rated
	 * 
	 * 
	 * @Test(priority=66) public static void
	 * DebitNote_Creationfor_India_Registered_InterState_NIL_Rated_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_NIL_Rated_Exempted(driver,
	 * Registered_Customer_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // UnReg + Intra + Debit Note + NIL Rated
	 * 
	 * @Test(priority=67) public static void
	 * DebitNote_Creationfor_India_UnRegistered_IntraState_NIL_Rated_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_NIL_Rated_Exempted(driver,
	 * UnRegistered_Customer_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Inter + Debit Note + NIL Rated
	 * 
	 * 
	 * @Test(priority=68) public static void
	 * DebitNote_Creationfor_India_UnRegistered_InterState_NIL_Rated_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_NIL_Rated_Exempted(driver,
	 * UnRegistered_Customer_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Composition + Intra + Debit Note + NIL Rated
	 * 
	 * @Test(priority=69) public static void
	 * DebitNote_Creationfor_India_Composition_IntraState_NIL_Rated_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_NIL_Rated_Exempted(driver,
	 * Composition_Customer_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Inter + Debit Note + NIL Rated
	 * 
	 * 
	 * @Test(priority=70) public static void
	 * DebitNote_Creationfor_India_Composition_InterState_NIL_Rated_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_NIL_Rated_Exempted(driver,
	 * Composition_Customer_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + Intra + Advanced Received + NIL Rated
	 * 
	 * @Test(priority=71) public static void
	 * AdvancedReceipt_Creationfor_India_Registered_IntraState_NIL_Rated_Exempted
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NIL_Rated_Exempted(driver,
	 * Registered_Customer_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * // Reg + Inter + Advanced Received + NIL Rated
	 * 
	 * @Test(priority=72) public static void
	 * AdvancedReceipt_Creationfor_India_Registered_InterState_NIL_Rated_Exempted
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NIL_Rated_Exempted(driver,
	 * Registered_Customer_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // UnReg + Intra + Advanced Received + NIL Rated
	 * 
	 * @Test(priority=73) public static void
	 * AdvancedReceipt_Creationfor_India_UnRegistered_IntraState_NIL_Rated_Exempted
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NIL_Rated_Exempted(driver,
	 * UnRegistered_Customer_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * // UnReg + Inter + Advanced Received + NIL Rated
	 * 
	 * @Test(priority=74) public static void
	 * AdvancedReceipt_Creationfor_India_UnRegistered_InterState_NIL_Rated_Exempted
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NIL_Rated_Exempted(driver,
	 * UnRegistered_Customer_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Composition + Intra + Advanced Received + NIL Rated
	 * 
	 * @Test(priority=75) public static void
	 * AdvancedReceipt_Creationfor_India_Composition_IntraState_NIL_Rated_Exempted
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NIL_Rated_Exempted(driver,
	 * Composition_Customer_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Inter + Advanced Received + NIL Rated
	 * 
	 * @Test(priority=76) public static void
	 * AdvancedReceipt_Creationfor_India_Composition_InterState_NIL_Rated_Exempted
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NIL_Rated_Exempted(driver,
	 * Composition_Customer_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * //<-------------------------------------------------- Exempt
	 * -------------------------------------------------------------------------
	 * ----------------------->///
	 * 
	 * // Exempted Product
	 * 
	 * 
	 * @Test(priority=77) public static void
	 * Exempted_Product_Creationfor_India() throws InterruptedException,
	 * AWTException, IOException {
	 * 
	 * GST_Product_Master PM = new GST_Product_Master(); Exempted_Product_ID =
	 * PM.createProduct_Exempted(driver);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + Intra + Sales Invoice + Exempted Product
	 * 
	 * @Test(priority=78) public static void
	 * SalesInvoice_Creationfor_India_Registered_IntraState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NIL_Rated_Exempted(driver,
	 * Registered_Customer_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + Intra + Sales Invoice + Exempted Product
	 * 
	 * @Test(priority=79) public static void
	 * SalesInvoice_Creationfor_India_Registered_InterState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NIL_Rated_Exempted(driver,
	 * Registered_Customer_Code_InterState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Intra + Sales Invoice + Exempted Product
	 * 
	 * @Test(priority=80) public static void
	 * SalesInvoice_Creationfor_India_UnRegistered_IntraState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NIL_Rated_Exempted(driver,
	 * UnRegistered_Customer_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // UnReg + Inter + Sales Invoice + Exempted Product
	 * 
	 * @Test(priority=81) public static void
	 * SalesInvoice_Creationfor_India_UnRegistered_InterState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NIL_Rated_Exempted(driver,
	 * UnRegistered_Customer_Code_InterState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Intra + Sales Invoice + Exempted Product
	 * 
	 * @Test(priority=82) public static void
	 * SalesInvoice_Creationfor_India_Composition_IntraState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NIL_Rated_Exempted(driver,
	 * Composition_Customer_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Composition + Inter + Sales Invoice + Exempted Product
	 * 
	 * @Test(priority=83) public static void
	 * SalesInvoice_Creationfor_India_Composition_InterState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NIL_Rated_Exempted(driver,
	 * Composition_Customer_Code_InterState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + Intra + Credit Note + Exempted Product
	 * 
	 * @Test(priority=84) public static void
	 * CreditNote_Creationfor_India_Registered_IntraState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NIL_Rated_Exempted(driver,
	 * Registered_Customer_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * // Reg + Inter + Credit Note + Exempted Product
	 * 
	 * 
	 * @Test(priority=85) public static void
	 * CreditNote_Creationfor_India_Registered_InterState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NIL_Rated_Exempted(driver,
	 * Registered_Customer_Code_InterState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Intra + Credit Note + Exempted Product
	 * 
	 * @Test(priority=86) public static void
	 * CreditNote_Creationfor_India_UnRegistered_IntraState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NIL_Rated_Exempted(driver,
	 * UnRegistered_Customer_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * // UnReg + Inter + Credit Note + Exempted Product
	 * 
	 * 
	 * @Test(priority=87) public static void
	 * CreditNote_Creationfor_India_UnRegistered_InterState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NIL_Rated_Exempted(driver,
	 * UnRegistered_Customer_Code_InterState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Intra + Credit Note + Exempted Product
	 * 
	 * @Test(priority=88) public static void
	 * CreditNote_Creationfor_India_Composition_IntraState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NIL_Rated_Exempted(driver,
	 * Composition_Customer_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * // Composition + Inter + Credit Note + Exempted Product
	 * 
	 * 
	 * @Test(priority=89) public static void
	 * CreditNote_Creationfor_India_Composition_InterState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NIL_Rated_Exempted(driver,
	 * Composition_Customer_Code_InterState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + Intra + Debit Note + Exempted Product
	 * 
	 * @Test(priority=90) public static void
	 * DebitNote_Creationfor_India_Registered_IntraState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_NIL_Rated_Exempted(driver,
	 * Registered_Customer_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Debit Note + Exempted Product
	 * 
	 * 
	 * @Test(priority=91) public static void
	 * DebitNote_Creationfor_India_Registered_InterState_Exempted() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note (); DN.createDebitNote_NIL_Rated_Exempted(driver,
	 * Registered_Customer_Code_InterState, Exempted_Product_ID);
	 * 
	 * //System.out.println(Registered_Customer_Code_InterState);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // UnReg + Intra + Debit Note + Exempted Product
	 * 
	 * @Test(priority=92) public static void
	 * DebitNote_Creationfor_India_UnRegistered_IntraState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_NIL_Rated_Exempted(driver,
	 * UnRegistered_Customer_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Inter + Debit Note + Exempted Product
	 * 
	 * 
	 * @Test(priority=93) public static void
	 * DebitNote_Creationfor_India_UnRegistered_InterState_Exempted() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note (); DN.createDebitNote_NIL_Rated_Exempted(driver,
	 * UnRegistered_Customer_Code_InterState, Exempted_Product_ID);
	 * 
	 * //System.out.println(Registered_Customer_Code_InterState);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Intra + Debit Note + Exempted Product
	 * 
	 * @Test(priority=94) public static void
	 * DebitNote_Creationfor_India_Composition_IntraState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_NIL_Rated_Exempted(driver,
	 * Composition_Customer_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Inter + Debit Note + Exempted Product
	 * 
	 * 
	 * @Test(priority=95) public static void
	 * DebitNote_Creationfor_India_Composition_InterState_Exempted() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note (); DN.createDebitNote_NIL_Rated_Exempted(driver,
	 * Composition_Customer_Code_InterState, Exempted_Product_ID);
	 * 
	 * //System.out.println(Registered_Customer_Code_InterState);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + Intra + Advanced Received + Exempted Product
	 * 
	 * @Test(priority=96) public static void
	 * AdvancedReceipt_Creationfor_India_Registered_IntraState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NIL_Rated_Exempted(driver,
	 * Registered_Customer_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * // Reg + Inter + Advanced Received + Exempted Product
	 * 
	 * @Test(priority=97) public static void
	 * AdvancedReceipt_Creationfor_India_Registered_InterState_Exempted() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NIL_Rated_Exempted(driver,
	 * Registered_Customer_Code_InterState, Exempted_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // UnReg + Intra + Advanced Received + Exempted Product
	 * 
	 * @Test(priority=98) public static void
	 * AdvancedReceipt_Creationfor_India_UnRegistered_IntraState_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NIL_Rated_Exempted(driver,
	 * UnRegistered_Customer_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * // UnReg + Inter + Advanced Received + Exempted Product
	 * 
	 * @Test(priority=99) public static void
	 * AdvancedReceipt_Creationfor_India_UnRegistered_InterState_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NIL_Rated_Exempted(driver,
	 * UnRegistered_Customer_Code_InterState, Exempted_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Composition + Intra + Advanced Received + Exempted Product
	 * 
	 * @Test(priority=100) public static void
	 * AdvancedReceipt_Creationfor_India_Composition_IntraState_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NIL_Rated_Exempted(driver,
	 * Composition_Customer_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * // Composition + Inter + Advanced Received + Exempted Product
	 * 
	 * @Test(priority=101) public static void
	 * AdvancedReceipt_Creationfor_India_Composition_InterState_Exempted()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NIL_Rated_Exempted(driver,
	 * Composition_Customer_Code_InterState, Exempted_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * //------------------ Section 3.1.D
	 * -----------------------------------------
	 * 
	 * // Reg + Intra + Vendor
	 * 
	 * @Test(priority=102) public void
	 * Registered_IntraState_Vendor_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_Vendor_Code_IntraState =
	 * VM.createVendor_Registered_IntraState( driver );
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Vendor
	 * 
	 * @Test(priority=103) public void
	 * Registered_InterState_Vendor_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_Vendor_Code_InterState = VM.copyVendor_Registered_InterState(
	 * driver , Registered_Vendor_Code_IntraState );
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // RCM Type product + Product with Product tax class (Goods type product)
	 * 
	 * @Test(priority=104) public static void RCM_Product_Creationfor_India()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * { GST_Product_Master PM = new GST_Product_Master(); RCM_Product_ID =
	 * PM.createProduct_RCM(driver);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + Intra + Purchase Invoice + RCM
	 * 
	 * @Test(priority=105) public static void
	 * RCM_PurchaseInvoice_Creationfor_India_Registered_IntraState_SalesSide()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createRCMPurchaseInvoice_Registered_IntraState_SalesSide(driver,
	 * Registered_Vendor_Code_IntraState, RCM_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + Inter + Purchase Invoice + RCM
	 * 
	 * @Test(priority=106) public static void
	 * RCM_PurchaseInvoice_Creationfor_India_Registered_InterState_SalesSide()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createRCMPurchaseInvoice_Registered_InterState_SalesSide(driver,
	 * Registered_Vendor_Code_InterState, RCM_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // Section (3.1.E) Non GST outward supplies
	 * 
	 * //<-------------------------------------------------- Non GST
	 * -------------------------------------------------------------------------
	 * ----------------------->///
	 * 
	 * 
	 * // Non GST
	 * 
	 * 
	 * 
	 * 
	 * @Test(priority=107) public static void
	 * Non_GST_Product_Creationfor_India() throws InterruptedException,
	 * AWTException, IOException {
	 * 
	 * GST_Product_Master PM = new GST_Product_Master(); NonGST_Product_ID =
	 * PM.createProduct_NonGST(driver);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Intra + Sales Invoice + Non GST
	 * 
	 * @Test(priority=108) public static void
	 * SalesInvoice_Creationfor_India_Registered_IntraState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NonGST(driver, Registered_Customer_Code_IntraState,
	 * NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + Inter + Sales Invoice + Non GST
	 * 
	 * @Test(priority=109) public static void
	 * SalesInvoice_Creationfor_India_Registered_InterState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NonGST(driver, Registered_Customer_Code_InterState,
	 * NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * // UnReg + Intra + Sales Invoice + Non GST
	 * 
	 * @Test(priority=110) public static void
	 * SalesInvoice_Creationfor_India_UnRegistered_IntraState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NonGST(driver,
	 * UnRegistered_Customer_Code_IntraState, NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // UnReg + Inter + Sales Invoice + Non GST
	 * 
	 * @Test(priority=111) public static void
	 * SalesInvoice_Creationfor_India_UnRegistered_InterState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NonGST(driver,
	 * UnRegistered_Customer_Code_InterState, NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Intra + Sales Invoice + Non GST
	 * 
	 * @Test(priority=112) public static void
	 * SalesInvoice_Creationfor_India_Composition_IntraState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NonGST(driver,
	 * Composition_Customer_Code_IntraState, NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Composition + Inter + Sales Invoice + Non GST
	 * 
	 * @Test(priority=113) public static void
	 * SalesInvoice_Creationfor_India_Composition_InterState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_NonGST(driver,
	 * Composition_Customer_Code_InterState, NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + Intra + Credit Note + Non GST
	 * 
	 * @Test(priority=114) public static void
	 * CreditNote_Creationfor_India_Registered_IntraState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NonGST(driver, Registered_Customer_Code_IntraState,
	 * NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * // Reg + Inter + Credit Note + Non GST
	 * 
	 * 
	 * @Test(priority=115) public static void
	 * CreditNote_Creationfor_India_Registered_InterState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NonGST(driver, Registered_Customer_Code_InterState,
	 * NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Intra + Credit Note + Non GST
	 * 
	 * @Test(priority=116) public static void
	 * CreditNote_Creationfor_India_UnRegistered_IntraState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NonGST(driver, UnRegistered_Customer_Code_IntraState,
	 * NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * // UnReg + Inter + Credit Note + Non GST
	 * 
	 * 
	 * @Test(priority=117) public static void
	 * CreditNote_Creationfor_India_UnRegistered_InterState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NonGST(driver, UnRegistered_Customer_Code_InterState,
	 * NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Intra + Credit Note + Non GST
	 * 
	 * @Test(priority=118) public static void
	 * CreditNote_Creationfor_India_Composition_IntraState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NonGST(driver, Composition_Customer_Code_IntraState,
	 * NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * // Composition + Inter + Credit Note + Non GST
	 * 
	 * 
	 * @Test(priority=119) public static void
	 * CreditNote_Creationfor_India_Composition_InterState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_NonGST(driver, Composition_Customer_Code_InterState,
	 * NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + Intra + Debit Note + Non GST
	 * 
	 * @Test(priority=120) public static void
	 * DebitNote_Creationfor_India_Registered_IntraState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_NonGST(driver, Registered_Customer_Code_IntraState,
	 * NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Debit Note + Non GST
	 * 
	 * 
	 * @Test(priority=121) public static void
	 * DebitNote_Creationfor_India_Registered_InterState_NonGST() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note (); DN.createDebitNote_NonGST(driver,
	 * Registered_Customer_Code_InterState, NonGST_Product_ID);
	 * 
	 * //System.out.println(Registered_Customer_Code_InterState);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // UnReg + Intra + Debit Note + Non GST
	 * 
	 * @Test(priority=122) public static void
	 * DebitNote_Creationfor_India_UnRegistered_IntraState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_NonGST(driver, UnRegistered_Customer_Code_IntraState,
	 * NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Inter + Debit Note + Non GST
	 * 
	 * 
	 * @Test(priority=123) public static void
	 * DebitNote_Creationfor_India_UnRegistered_InterState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_NonGST(driver, UnRegistered_Customer_Code_InterState,
	 * NonGST_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Intra + Debit Note + Non GST
	 * 
	 * @Test(priority=124) public static void
	 * DebitNote_Creationfor_India_Composition_IntraState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_NonGST(driver, Composition_Customer_Code_IntraState,
	 * NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Inter + Debit Note + Non GST
	 * 
	 * 
	 * @Test(priority=125) public static void
	 * DebitNote_Creationfor_India_Composition_InterState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_NonGST(driver, Composition_Customer_Code_InterState,
	 * NonGST_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + Intra + Advanced Received + Non GST
	 * 
	 * @Test(priority=126) public static void
	 * AdvancedReceipt_Creationfor_India_Registered_IntraState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NonGST(driver,
	 * Registered_Customer_Code_IntraState, NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * // Reg + Inter + Advanced Received + Non GST
	 * 
	 * @Test(priority=127) public static void
	 * AdvancedReceipt_Creationfor_India_Registered_InterState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NonGST(driver,
	 * Registered_Customer_Code_InterState, NonGST_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // UnReg + Intra + Advanced Received + Non GST
	 * 
	 * @Test(priority=128) public static void
	 * AdvancedReceipt_Creationfor_India_UnRegistered_IntraState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NonGST(driver,
	 * UnRegistered_Customer_Code_IntraState, NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * // UnReg + Inter + Advanced Received + Non GST
	 * 
	 * @Test(priority=129) public static void
	 * AdvancedReceipt_Creationfor_India_UnRegistered_InterState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NonGST(driver,
	 * UnRegistered_Customer_Code_InterState, NonGST_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Composition + Intra + Advanced Received + Non GST
	 * 
	 * @Test(priority=130) public static void
	 * AdvancedReceipt_Creationfor_India_Composition_IntraState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NonGST(driver,
	 * Composition_Customer_Code_IntraState, NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * // Composition + Inter + Advanced Received + Non GST
	 * 
	 * @Test(priority=131) public static void
	 * AdvancedReceipt_Creationfor_India_Composition_InterState_NonGST() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_NonGST(driver,
	 * Composition_Customer_Code_InterState, NonGST_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // ------------------------------ 3.2.A Section
	 * ----------------------------------------
	 * 
	 * /* // Unreg + Intra + Customer
	 * 
	 * @Test(priority=1) public void
	 * UnRegistered_IntraState_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * {
	 * 
	 * GST_Customer_Master CM = new GST_Customer_Master();
	 * UnRegistered_Customer_Code_IntraState =
	 * CM.createCustomer_UnRegistered_IntraState( driver );
	 * 
	 * }
	 * 
	 * 
	 * // Unreg + Inter + Customer
	 * 
	 * @Test(priority=2)
	 * 
	 * public void UnRegistered_InterState_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * {
	 * 
	 * GST_Customer_Master CM = new GST_Customer_Master();
	 * UnRegistered_Customer_Code_InterState =
	 * CM.copyCustomer_UnRegistered_InterState( driver ,
	 * UnRegistered_Customer_Code_IntraState);
	 * 
	 * 
	 * }
	 * 
	 * // Product with Product tax class
	 * 
	 * @Test(priority=3) public static void Product_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * { GST_Product_Master PM = new GST_Product_Master(); Normal_Product_ID =
	 * PM.createProduct_Normal(driver);
	 * 
	 * }
	 * 
	 */

	/*
	 * // UnReg + Inter + Sales Invoice
	 * 
	 * @Test(priority=132) public static void
	 * SalesInvoice_Creationfor_India_UnRegistered_InterState_3_2_A() throws
	 * InterruptedException, AWTException, IOException { GST_Sales_Invoice SI =
	 * new GST_Sales_Invoice (); SI.createSalesInvoice_InterState_3_2_A(driver,
	 * UnRegistered_Customer_Code_InterState, Normal_Product_ID); }
	 * 
	 * 
	 * 
	 * // UnReg + Inter + Credit Note
	 * 
	 * @Test(priority=133) public static void
	 * CreditNote_Creationfor_India_UnRegistered_InterState_3_2_A() throws
	 * InterruptedException, AWTException, IOException { GST_Credit_Note CN =
	 * new GST_Credit_Note (); CN.createCreditNote_InterState_3_2_A(driver,
	 * UnRegistered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Inter + Debit Note
	 * 
	 * @Test(priority=134) public static void
	 * DebitNote_Creationfor_India_UnRegistered_InterState_3_2_A() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note (); DN.createDebitNote_InterState_3_2_A(driver,
	 * UnRegistered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * // UnReg + Inter + Advanced Received
	 * 
	 * @Test(priority=135) public static void
	 * AdvancedReceipt_Creationfor_India_UnRegistered_InterState_3_2_A() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_InterState_3_2_A(driver,
	 * UnRegistered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 */

	// ------------------------------ 3.2.B Section
	// ----------------------------------------

	// (3.2.B) Supplies made to Composition Taxable Persons

	/*
	 * 
	 * // Composition + Intra + Customer
	 * 
	 * @Test(priority=1) public void
	 * Composition_IntraState_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master();
	 * Composition_Customer_Code_IntraState =
	 * CM.createCustomer_Composition_IntraState( driver );
	 * 
	 * }
	 * 
	 * // Composition + Inter + Customer
	 * 
	 * @Test(priority=2)
	 * 
	 * public void Composition_InterState_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master();
	 * Composition_Customer_Code_InterState =
	 * CM.createCustomer_Composition_InterState( driver ,
	 * Composition_Customer_Code_IntraState );
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Product with Product tax class
	 * 
	 * @Test(priority=3) public static void Product_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * { GST_Product_Master PM = new GST_Product_Master(); Normal_Product_ID =
	 * PM.createProduct_Normal(driver);
	 * 
	 * }
	 * 
	 */

	/*
	 * // Composition + Inter + Sales Invoice
	 * 
	 * @Test(priority=136) public static void
	 * SalesInvoice_Creationfor_India_Composition_InterState_3_2_B() throws
	 * InterruptedException, AWTException, IOException { GST_Sales_Invoice SI =
	 * new GST_Sales_Invoice (); SI.createSalesInvoice_InterState_3_2_B(driver,
	 * Composition_Customer_Code_InterState, Normal_Product_ID); }
	 * 
	 * 
	 * 
	 * // Composition + Inter + Credit Note
	 * 
	 * @Test(priority=137) public static void
	 * CreditNote_Creationfor_India_Composition_InterState_3_2_B() throws
	 * InterruptedException, AWTException, IOException { GST_Credit_Note CN =
	 * new GST_Credit_Note (); CN.createCreditNote_InterState_3_2_B(driver,
	 * Composition_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Inter + Debit Note
	 * 
	 * @Test(priority=138) public static void
	 * DebitNote_Creationfor_India_UnRegistered_InterState_3_2_B() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note (); DN.createDebitNote_InterState_3_2_B(driver,
	 * Composition_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * // Composition + Inter + Advanced Received
	 * 
	 * @Test(priority=139) public static void
	 * AdvancedReceipt_Creationfor_India_UnRegistered_InterState_3_2_B() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_InterState_3_2_B(driver,
	 * Composition_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 */

	/*
	 * 
	 * 
	 * // ------------------ Purchase side of GSTR3B
	 * report-----------------------------------------
	 * 
	 * 
	 * //------------------ Section 4.A.1
	 * -----------------------------------------
	 * 
	 * 
	 * 
	 * 
	 * // UnReg + Import + Vendor + Inter
	 * 
	 * @Test(priority=110) public void UnRegistered_Import_Vendor() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * {
	 * 
	 * GST_Vendor_Master VM = new GST_Vendor_Master();
	 * UnRegistered_Vendor_Code_Import = VM.createVendor_UnRegistered_Import(
	 * driver );
	 * 
	 * }
	 * 
	 * // Product with Product tax class (Goods type product)
	 * 
	 * @Test(priority=112) public static void Product_Creationfor_India1()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * { GST_Product_Master PM = new GST_Product_Master(); Normal_Product_ID =
	 * PM.createProduct_Normal(driver);
	 * 
	 * 
	 * }
	 * 
	 * // UnReg + Import + Inter + Purchase Invoice + Goods
	 * 
	 * @Test(priority=113)
	 * 
	 * public static void PurchaseInvoice_Creationfor_India_Import_Intrastate()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * { GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_UnRegistered_Import(driver,
	 * UnRegistered_Vendor_Code_Import , Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Import + Inter + Debit Note (Otherwise) + Goods
	 * 
	 * @Test(priority=114) public static void
	 * DebitNote_Creationfor_India_Import_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createdebitNote_Import_InterState(driver,
	 * UnRegistered_Vendor_Code_Import, Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Import + Inter + Credit Note (Vendor) + Goods
	 * 
	 * @Test(priority=115) public static void
	 * CreditNote_Creationfor_India_Import_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createcreditNote_Import_InterState(driver,
	 * UnRegistered_Vendor_Code_Import, Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * //------------------ Section 4.A.2 --------------------------------------
	 * ---
	 * 
	 * 
	 * // UnReg + Import + Vendor + Inter
	 * 
	 * @Test(priority=116) public void UnRegistered_Import_Vendor_Service()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * 
	 * {
	 * 
	 * GST_Vendor_Master VM = new GST_Vendor_Master();
	 * UnRegistered_Vendor_Code_Import = VM.createVendor_UnRegistered_Import(
	 * driver );
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Product with Product tax class (Service type product)
	 * 
	 * @Test(priority=117) public static void
	 * Service_type_Product_Creationfor_India() throws InterruptedException,
	 * AWTException, IOException
	 * 
	 * { GST_Product_Master PM = new GST_Product_Master(); Service_Product_ID =
	 * PM.createServiceTypeProduct(driver);
	 * 
	 * 
	 * }
	 * 
	 * // UnReg + Import + Inter + Purchase Invoice + Service
	 * 
	 * @Test(priority=8)
	 * 
	 * public static void
	 * PurchaseInvoice_Creationfor_India_Import_Intrastate_Service_type() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * { GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_UnRegistered_Import_Service(driver,
	 * UnRegistered_Vendor_Code_Import , Service_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Import + Inter + Debit Note (Otherwise) + Service
	 * 
	 * @Test(priority=9) public static void
	 * DebitNote_Creationfor_India_Import_InterState_Service_type() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createdebitNote_Import_InterState_Service(driver,
	 * UnRegistered_Vendor_Code_Import, Service_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Import + Inter + Credit Note (Vendor) + Service
	 * 
	 * @Test(priority=10) public static void
	 * CreditNote_Creationfor_India_Import_InterState_Service_type() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createcreditNote_Import_InterState_Service(driver,
	 * UnRegistered_Vendor_Code_Import, Service_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * //------------------ Section 4.A.3
	 * -----------------------------------------
	 * 
	 * // Reg + Intra + Vendor
	 * 
	 * @Test(priority=11) public void
	 * Registered_IntraState_Vendor_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_Vendor_Code_IntraState =
	 * VM.createVendor_Registered_IntraState( driver );
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Vendor
	 * 
	 * @Test(priority=12) public void
	 * Registered_InterState_Vendor_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_Vendor_Code_InterState = VM.copyVendor_Registered_InterState(
	 * driver , Registered_Vendor_Code_IntraState );
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // RCM Type product + Product with Product tax class (Goods type product)
	 * 
	 * @Test(priority=13) public static void RCM_Product_Creationfor_India()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * { GST_Product_Master PM = new GST_Product_Master(); RCM_Product_ID =
	 * PM.createProduct_RCM(driver);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + Intra + Purchase Invoice + RCM
	 * 
	 * @Test(priority=14) public static void
	 * RCM_PurchaseInvoice_Creationfor_India_Registered_IntraState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createRCMPurchaseInvoice_Registered_IntraState(driver,
	 * Registered_Vendor_Code_IntraState, RCM_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + Inter + Purchase Invoice + RCM
	 * 
	 * @Test(priority=15) public static void
	 * RCM_PurchaseInvoice_Creationfor_India_Registered_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createRCMPurchaseInvoice_Registered_InterState(driver,
	 * Registered_Vendor_Code_InterState, RCM_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * //------------------ Section (4.A.5) All other ITC
	 * -----------------------------------------
	 * 
	 * // Reg + Intra + Vendor
	 * 
	 * @Test(priority=16) public void
	 * Registered_IntraState_Vendor_Creationfor_India_Section_4_A_5() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_Vendor_Code_IntraState =
	 * VM.createVendor_Registered_IntraState( driver );
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Vendor
	 * 
	 * @Test(priority=17) public void
	 * Registered_InterState_Vendor_Creationfor_India_Section_4_A_5() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_Vendor_Code_InterState = VM.copyVendor_Registered_InterState(
	 * driver , Registered_Vendor_Code_IntraState );
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Product with Product tax class (Goods type product)
	 * 
	 * @Test(priority=18) public static void Product_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * { GST_Product_Master PM = new GST_Product_Master(); Normal_Product_ID =
	 * PM.createProduct_Normal(driver);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Intra + Purchase Invoice
	 * 
	 * @Test(priority=19) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_IntraState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_IntraState(driver,
	 * Registered_Vendor_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + Inter + Purchase Invoice
	 * 
	 * @Test(priority=20) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_InterState(driver,
	 * Registered_Vendor_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * @Test(priority=21) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_IntraState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_IntraState_Purchase_side(driver,
	 * Registered_Vendor_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * 
	 * @Test(priority=22) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_InterState_Purchase_side(driver,
	 * Registered_Vendor_Code_InterState, Normal_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Credit Note + Purchase side (CN against vendor)
	 * 
	 * @Test(priority=23) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_IntraState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_IntraState_Purchase_side(driver,
	 * Registered_Vendor_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Credit Note + Purchase side (CN against vendor)
	 * 
	 * 
	 * @Test(priority=24) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_InterState_Purchase_side(driver,
	 * Registered_Vendor_Code_InterState, Normal_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Intra + Vendor
	 * 
	 * @Test(priority=25) public void
	 * Registered_SEZ_WPAY_IntraState_Vendor_Creationfor_India_Section_4_A_5()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState =
	 * VM.createVendor_Registered_SEZ_WPAY_IntraState( driver);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Vendor
	 * 
	 * @Test(priority=26) public void
	 * Registered_SEZ_WPAY_IntraState_Vendor_Creationfor_India_Section4_A_5()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_SEZ_WPAY_Vendor_Code_InterState =
	 * VM.copyVendor_Registered_SEZ_WPAY_InterState( driver ,
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Intra + Purchase Invoice
	 * 
	 * @Test(priority=27) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_SEZ_WPAY_IntraState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_SEZ_WPAY_IntraState(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Purchase Invoice
	 * 
	 * @Test(priority=28) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_SEZ_WPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_SEZ_WPAY_IntraState(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * @Test(priority=29) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_SEZ_WPAY_IntraState(
	 * ) throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_SEZ_WPAY_IntraState_Purchase_side(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * 
	 * @Test(priority=30) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_SEZ_WPAY_InterState(
	 * ) throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_SEZ_WPAY_IntraState_Purchase_side(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_InterState, Normal_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Credit Note + Purchase side (CN against
	 * vendor)
	 * 
	 * @Test(priority=31) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_SEZ_WPAY_IntraState
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_SEZ_WPAY_IntraState_Purchase_side(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Credit Note + Purchase side (CN against
	 * vendor)
	 * 
	 * 
	 * @Test(priority=32) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_SEZ_WPAY_InterState
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_SEZ_WPAY_IntraState_Purchase_side(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_InterState, Normal_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * //------------------ Section 5.1 Exempted section
	 * -------------------------------------- ---
	 * 
	 * 
	 * // Reg + Intra + Vendor
	 * 
	 * @Test(priority=33) public void
	 * Registered_IntraState_Vendor_Creationfor_India_Section_5_1() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_Vendor_Code_IntraState =
	 * VM.createVendor_Registered_IntraState( driver );
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Vendor
	 * 
	 * @Test(priority=34) public void
	 * Registered_InterState_Vendor_Creationfor_India_Section_5_1() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_Vendor_Code_InterState = VM.copyVendor_Registered_InterState(
	 * driver , Registered_Vendor_Code_IntraState );
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Exempted product (Goods type product)
	 * 
	 * @Test(priority=35) public static void
	 * Exempted_Product_Creationfor_India_Section_5_1() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * { GST_Product_Master PM = new GST_Product_Master(); Exempted_Product_ID =
	 * PM.createProduct_Exempted(driver);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Intra + Purchase Invoice
	 * 
	 * @Test(priority=36) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_IntraState_Section_5_1()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_IntraState_Section_5_1(driver,
	 * Registered_Vendor_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + Inter + Purchase Invoice
	 * 
	 * @Test(priority=37) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_InterState_Section_5_1()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_InterState_Section_5_1(driver,
	 * Registered_Vendor_Code_InterState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * @Test(priority=38) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_IntraState_5_1()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_IntraState_Purchase_side_Section_5_1(driver,
	 * Registered_Vendor_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * 
	 * @Test(priority=39) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_InterState_5_1()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_IntraState_Purchase_side_Section_5_1(driver,
	 * Registered_Vendor_Code_InterState, Exempted_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Credit Note + Purchase side (CN against vendor)
	 * 
	 * @Test(priority=40) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_IntraState_5_1()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_IntraState_Purchase_side_Section_5_1(driver,
	 * Registered_Vendor_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Credit Note + Purchase side (CN against vendor)
	 * 
	 * 
	 * @Test(priority=41) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_InterState_5_1()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_InterState_Purchase_side_Section_5_1(driver,
	 * Registered_Vendor_Code_InterState, Exempted_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Intra + Vendor
	 * 
	 * @Test(priority=42) public void
	 * Registered_SEZ_WPAY_IntraState_Vendor_Creationfor_India_Section_5_1()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState =
	 * VM.createVendor_Registered_SEZ_WPAY_IntraState( driver);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Vendor
	 * 
	 * @Test(priority=43) public void
	 * Registered_SEZ_WPAY_InterState_Vendor_Creationfor_India_Section_5_1()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_SEZ_WPAY_Vendor_Code_InterState =
	 * VM.copyVendor_Registered_SEZ_WPAY_InterState( driver ,
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Intra + Purchase Invoice
	 * 
	 * @Test(priority=44) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_SEZ_WPAY_IntraState_5_1()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_IntraState_Section_5_1(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Purchase Invoice
	 * 
	 * @Test(priority=45) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_SEZ_WPAY_InterState_5_1()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_IntraState_Section_5_1(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_InterState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * @Test(priority=46) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_SEZ_WPAY_IntraState_5_1
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_IntraState_Purchase_side_Section_5_1(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * 
	 * @Test(priority=47) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_SEZ_WPAY_InterState_5_1
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_IntraState_Purchase_side_Section_5_1(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_InterState, Exempted_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Credit Note + Purchase side (CN against
	 * vendor)
	 * 
	 * @Test(priority=48) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_SEZ_WPAY_IntraState_5_1
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_InterState_Purchase_side_Section_5_1(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState, Exempted_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Credit Note + Purchase side (CN against
	 * vendor)
	 * 
	 * 
	 * @Test(priority=49) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_SEZ_WPAY_InterState_5_1
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_InterState_Purchase_side_Section_5_1(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_InterState, Exempted_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * //------------------ Section 5.2 Non GST product section
	 * -------------------------------------- ---
	 * 
	 * 
	 * // Reg + Intra + Vendor
	 * 
	 * @Test(priority=50) public void
	 * Registered_IntraState_Vendor_Creationfor_India_Section_5_2() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_Vendor_Code_IntraState =
	 * VM.createVendor_Registered_IntraState( driver );
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Vendor
	 * 
	 * @Test(priority=51) public void
	 * Registered_InterState_Vendor_Creationfor_India_Section_5_2() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_Vendor_Code_InterState = VM.copyVendor_Registered_InterState(
	 * driver , Registered_Vendor_Code_IntraState );
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Exempted product (Goods type product)
	 * 
	 * @Test(priority=52) public static void
	 * Exempted_Product_Creationfor_India_Section_5_2() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * { GST_Product_Master PM = new GST_Product_Master(); NonGST_Product_ID =
	 * PM.createProduct_NonGST(driver);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Intra + Purchase Invoice
	 * 
	 * @Test(priority=53) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_IntraState_Section_5_2()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_IntraState_Section_5_2(driver,
	 * Registered_Vendor_Code_IntraState, NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + Inter + Purchase Invoice
	 * 
	 * @Test(priority=54) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_InterState_Section_5_2()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_InterState_Section_5_2(driver,
	 * Registered_Vendor_Code_InterState, NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * @Test(priority=55) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_IntraState_5_2()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_IntraState_Purchase_side_Section_5_2(driver,
	 * Registered_Vendor_Code_IntraState, NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * 
	 * @Test(priority=56) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_InterState_5_2()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_InterState_Purchase_side_Section_5_2(driver,
	 * Registered_Vendor_Code_InterState, NonGST_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Credit Note + Purchase side (CN against vendor)
	 * 
	 * @Test(priority=57) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_IntraState_5_2()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_IntraState_Purchase_side_Section_5_2(driver,
	 * Registered_Vendor_Code_IntraState, NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Credit Note + Purchase side (CN against vendor)
	 * 
	 * 
	 * @Test(priority=58) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_InterState_5_2()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_InterState_Purchase_side_Section_5_2(driver,
	 * Registered_Vendor_Code_InterState, NonGST_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Intra + Vendor
	 * 
	 * @Test(priority=59) public void
	 * Registered_SEZ_WPAY_IntraState_Vendor_Creationfor_India_Section_5_2()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * 
	 * {
	 * 
	 * GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState =
	 * VM.createVendor_Registered_SEZ_WPAY_IntraState( driver);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Vendor
	 * 
	 * @Test(priority=60) public void
	 * Registered_SEZ_WPAY_InterState_Vendor_Creationfor_India_Section_5_2()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * 
	 * {
	 * 
	 * GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_SEZ_WPAY_Vendor_Code_InterState =
	 * VM.copyVendor_Registered_SEZ_WPAY_InterState( driver ,
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Intra + Purchase Invoice
	 * 
	 * @Test(priority=61) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_SEZ_WPAY_IntraState_5_2()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_IntraState_Section_5_2(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState, NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Purchase Invoice
	 * 
	 * @Test(priority=62) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_SEZ_WPAY_InterState_5_2()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_IntraState_Section_5_2(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_InterState, NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * @Test(priority=63) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_SEZ_WPAY_IntraState_5_2
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_IntraState_Purchase_side_Section_5_2(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState, NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * 
	 * @Test(priority=64) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_SEZ_WPAY_InterState_5_2
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_IntraState_Purchase_side_Section_5_2(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_InterState, NonGST_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Credit Note + Purchase side (CN against
	 * vendor)
	 * 
	 * @Test(priority=65) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_SEZ_WPAY_IntraState_5_2
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_InterState_Purchase_side_Section_5_2(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState, NonGST_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Credit Note + Purchase side (CN against
	 * vendor)
	 * 
	 * 
	 * @Test(priority=66) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_SEZ_WPAY_InterState_5_2
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_InterState_Purchase_side_Section_5_2(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_InterState, NonGST_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * //------------------ Section 5.3 NIL Rated section
	 * -------------------------------------- ---
	 * 
	 * 
	 * // Reg + Intra + Vendor
	 * 
	 * @Test(priority=67) public void
	 * Registered_IntraState_Vendor_Creationfor_India_Section_5_3() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_Vendor_Code_IntraState =
	 * VM.createVendor_Registered_IntraState( driver );
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Vendor
	 * 
	 * @Test(priority=68) public void
	 * Registered_InterState_Vendor_Creationfor_India_Section_5_3() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_Vendor_Code_InterState = VM.copyVendor_Registered_InterState(
	 * driver , Registered_Vendor_Code_IntraState );
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // NIL Rated product (Goods type product)
	 * 
	 * @Test(priority=69) public static void
	 * Exempted_Product_Creationfor_India_Section_5_3() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * { GST_Product_Master PM = new GST_Product_Master(); NIL_Rated_Product_ID
	 * = PM.createProduct_NIL_Rated(driver);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Intra + Purchase Invoice
	 * 
	 * @Test(priority=70) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_IntraState_Section_5_3()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_IntraState_Section_5_3(driver,
	 * Registered_Vendor_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + Inter + Purchase Invoice
	 * 
	 * @Test(priority=71) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_InterState_Section_5_3()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_InterState_Section_5_3(driver,
	 * Registered_Vendor_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * @Test(priority=72) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_IntraState_5_3()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_IntraState_Purchase_side_Section_5_3(driver,
	 * Registered_Vendor_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * 
	 * @Test(priority=73) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_InterState_5_3()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_InterState_Purchase_side_Section_5_3(driver,
	 * Registered_Vendor_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Credit Note + Purchase side (CN against vendor)
	 * 
	 * @Test(priority=74) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_IntraState_5_3()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_IntraState_Purchase_side_Section_5_3(driver,
	 * Registered_Vendor_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Credit Note + Purchase side (CN against vendor)
	 * 
	 * 
	 * @Test(priority=75) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_InterState_5_3()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_InterState_Purchase_side_Section_5_3(driver,
	 * Registered_Vendor_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Intra + Vendor
	 * 
	 * @Test(priority=76) public void
	 * Registered_SEZ_WPAY_IntraState_Vendor_Creationfor_India_Section_5_3()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * 
	 * {
	 * 
	 * GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState =
	 * VM.createVendor_Registered_SEZ_WPAY_IntraState( driver);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Vendor
	 * 
	 * @Test(priority=77) public void
	 * Registered_SEZ_WPAY_InterState_Vendor_Creationfor_India_Section_5_3()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * 
	 * {
	 * 
	 * GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Registered_SEZ_WPAY_Vendor_Code_InterState =
	 * VM.copyVendor_Registered_SEZ_WPAY_InterState( driver ,
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Intra + Purchase Invoice
	 * 
	 * @Test(priority=78) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_SEZ_WPAY_IntraState_5_3()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_IntraState_Section_5_3(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Purchase Invoice
	 * 
	 * @Test(priority=79) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_SEZ_WPAY_InterState_5_3()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_IntraState_Section_5_3(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * @Test(priority=80) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_SEZ_WPAY_IntraState_5_3
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_IntraState_Purchase_side_Section_5_3(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * 
	 * @Test(priority=81) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_SEZ_WPAY_InterState_5_3
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_IntraState_Purchase_side_Section_5_3(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Credit Note + Purchase side (CN against
	 * vendor)
	 * 
	 * @Test(priority=82) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_SEZ_WPAY_IntraState_5_3
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_InterState_Purchase_side_Section_5_3(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_IntraState, NIL_Rated_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WPAY) + Inter + Credit Note + Purchase side (CN against
	 * vendor)
	 * 
	 * 
	 * @Test(priority=83) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_SEZ_WPAY_InterState_5_3
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_InterState_Purchase_side_Section_5_3(driver,
	 * Registered_SEZ_WPAY_Vendor_Code_InterState, NIL_Rated_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * //------------------ Section 5.4 Composition Supplies section
	 * -------------------------------------- ---
	 * 
	 * 
	 * 
	 * 
	 * // Comosition + Intra + Vendor
	 * 
	 * @Test(priority=84) public void
	 * Composition_IntraState_Vendor_Creationfor_India_Section_5_4() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Composition_Vendor_Code_IntraState =
	 * VM.createVendor_Composition_IntraState( driver );
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Inter + Vendor
	 * 
	 * @Test(priority=85) public void
	 * Composition_InterState_Vendor_Creationfor_India_Section_5_4() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Vendor_Master VM = new GST_Vendor_Master();
	 * Composition_Vendor_Code_InterState =
	 * VM.copyVendor_Composition_InterState( driver ,
	 * Composition_Vendor_Code_IntraState );
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Product with Product tax class
	 * 
	 * @Test(priority=86) public static void
	 * Product_Creationfor_India_Section_5_4() throws InterruptedException,
	 * AWTException, IOException {
	 * 
	 * GST_Product_Master PM = new GST_Product_Master(); Normal_Product_ID =
	 * PM.createProduct_Normal(driver);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Intra + Purchase Invoice
	 * 
	 * @Test(priority=87) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_IntraState_Section_5_4()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_IntraState_Section_5_4(driver,
	 * Composition_Vendor_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // Reg + Inter + Purchase Invoice
	 * 
	 * @Test(priority=88) public static void
	 * PurchaseInvoice_Creationfor_India_Registered_InterState_Section_5_4()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Purchase_Invoice PI = new GST_Purchase_Invoice();
	 * PI.createPurchaseInvoice_InterState_Section_5_4(driver,
	 * Composition_Vendor_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * @Test(priority=89) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_IntraState_5_4()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_IntraState_Purchase_side_Section_5_4(driver,
	 * Composition_Vendor_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Debit Note + Purchase side (DN otherwise)
	 * 
	 * 
	 * @Test(priority=90) public static void
	 * Purchase_Side_DebitNote_Creationfor_India_Registered_InterState_5_4()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_InterState_Purchase_side_Section_5_4(driver,
	 * Composition_Vendor_Code_InterState, Normal_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Credit Note + Purchase side (CN against vendor)
	 * 
	 * @Test(priority=91) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_IntraState_5_4()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_IntraState_Purchase_side_Section_5_4(driver,
	 * Composition_Vendor_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Credit Note + Purchase side (CN against vendor)
	 * 
	 * 
	 * @Test(priority=92) public static void
	 * Purchase_Side_CreditNote_Creationfor_India_Registered_InterState_5_4()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_InterState_Purchase_side_Section_5_4(driver,
	 * Composition_Vendor_Code_InterState, Normal_Product_ID);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 */

}