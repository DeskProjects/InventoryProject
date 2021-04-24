package krawler.erp.IndianGST_TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import krawler.erp.IndianGST.GST_Advanced_Received;
import krawler.erp.IndianGST.GST_Credit_Note;
import krawler.erp.IndianGST.GST_Customer_Master;
import krawler.erp.IndianGST.GST_Debit_Note;
import krawler.erp.IndianGST.GST_Product_Master;
import krawler.erp.IndianGST.GST_Sales_Invoice;
import krawler.erp.page.Login;

@Test
public class Indian_GST_GSTR1_Report

{
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

	public void beforeTestLogin() throws InterruptedException, IOException, AWTException

	{
		Login ln = new Login();
		WebDriver driver = Login.loginERP(URL, username, password, 0);
		this.driver = driver;

	}

	/*
	 * // Section B2B Invoices (4A,4B,4C,6B,6C) // Reg + Intra + Customer
	 * 
	 * @Test(priority=1) public void
	 * Registered_IntraState_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * {
	 * 
	 * GST_Customer_Master CM = new GST_Customer_Master();
	 * Registered_Customer_Code_IntraState =
	 * CM.createCustomer_Registered_IntraState( driver );
	 * 
	 * }
	 * 
	 * // Reg + Inter + Customer
	 * 
	 * @Test(priority=2)
	 * 
	 * public void Registered_InterState_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * {
	 * 
	 * GST_Customer_Master CM = new GST_Customer_Master();
	 * Registered_Customer_Code_InterState =
	 * CM.copyCustomer_Registered_InterState( driver ,
	 * Registered_Customer_Code_IntraState);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Product with Product tax class
	 * 
	 * @Test(priority=3) public static void Product_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Product_Master PM = new GST_Product_Master(); Normal_Product_ID =
	 * PM.createProduct_Normal(driver);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + Intra + Sales Invoice
	 * 
	 * @Test(priority=4) public static void
	 * Registered_SalesInvoice_Creationfor_India_Registered_IntraState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_IntraState_GSTR1(driver,
	 * Registered_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + Inter + Sales Invoice
	 * 
	 * @Test(priority=5) public static void
	 * Registered_SalesInvoice_Creationfor_India_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_InterState_GSTR1(driver,
	 * Registered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Composition + Intra + Customer
	 * 
	 * @Test(priority=6) public void
	 * Composition_IntraState_Customer_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * {
	 * 
	 * GST_Customer_Master CM = new GST_Customer_Master();
	 * Composition_Customer_Code_IntraState =
	 * CM.createCustomer_Composition_IntraState( driver );
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Composition + Inter + Customer
	 * 
	 * @Test(priority=7)
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
	 * // Reg + Intra + Sales Invoice
	 * 
	 * @Test(priority=8) public static void
	 * Composition_SalesInvoice_Creationfor_India_Registered_IntraState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_IntraState_GSTR1(driver,
	 * Composition_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + Inter + Sales Invoice
	 * 
	 * @Test(priority=9) public static void
	 * Composition_SalesInvoice_Creationfor_India_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_InterState_GSTR1(driver,
	 * Composition_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // SEZ + WPAY + Customer
	 * 
	 * @Test(priority=10)
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
	 * @Test(priority=11) public static void
	 * SalesInvoice_Creationfor_India_SEZ_WPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_InterState_GSTR1(driver, SEZ_WPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WOPAY) + Inter + Customer
	 * 
	 * 
	 * @Test(priority=12)
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
	 * @Test(priority=13) public static void
	 * SalesInvoice_Creationfor_India_SEZ_WOPAY_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_SEZ_WOPAY_InterState_GSTR1(driver, SEZ_WOPAY,
	 * Normal_Product_ID);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Section B2C Small Invoices
	 * 
	 * 
	 * // Unreg + Intra + Customer
	 * 
	 * @Test(priority=14) public void
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
	 * 
	 * // Unreg + Inter + Customer
	 * 
	 * @Test(priority=15)
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
	 * @Test(priority=16) public static void Product1_Creationfor_India() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * {
	 * 
	 * GST_Product_Master PM = new GST_Product_Master(); Normal_Product_ID =
	 * PM.createProduct_Normal(driver);
	 * 
	 * }
	 * 
	 * 
	 * // Unreg + Intra + Sales Invoice
	 * 
	 * @Test(priority=17) public static void
	 * SalesInvoice_Creationfor_India_UnRegistered_IntraState() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * { GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_UnRegistered_IntraState_GSTR1(driver,
	 * UnRegistered_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Inter + Sales Invoice + Amount < 2.5L
	 * 
	 * @Test(priority=18) public static void
	 * SalesInvoice_Creationfor_India_UnRegistered_InterState_Amount() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_UnRegistered_InterState_GSTR1_Amount_less(driver,
	 * UnRegistered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Intra + Credit Note
	 * 
	 * @Test(priority=19) public static void
	 * CreditNote_Creationfor_India_UnRegistered_IntraState() throws
	 * InterruptedException, AWTException, IOException { GST_Credit_Note CN =
	 * new GST_Credit_Note ();
	 * CN.createCreditNote_Unregistered_IntraState_GSTR1_B2CS(driver,
	 * UnRegistered_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Intra + Debit Note
	 * 
	 * @Test(priority=20) //20 public static void
	 * DebitNote_Creationfor_India_Registered_IntraState() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note ();
	 * DN.createDebitNote_Unregistered_IntraState_GSTR1_B2CS(driver,
	 * UnRegistered_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Section B2C Large Invoices (5A, 5B) // UnReg + Inter + Sales Invoice +
	 * Amount > 2.5L
	 * 
	 * @Test(priority=21)
	 * 
	 * public void UnRegistered_InterState_Customer_Creationfor_India_Amount()
	 * throws InterruptedException, AWTException, IOException
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
	 * 
	 * 
	 * // UnReg + Inter + Sales Invoice
	 * 
	 * @Test(priority=22) public static void
	 * SalesInvoice_Creationfor_India_UnRegistered_InterState() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice ();
	 * SI.createSalesInvoice_UnRegistered_InterState_GSTR1(driver,
	 * UnRegistered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 */
	// Section Credit / Debit Note (Registered - 9B)
	// Credit Note
	// Reg + Intra + Customer
	/*
	 * @Test(priority=23) public void
	 * Registered_IntraState_Customer_Creationfor_India_CN_DN_Reg() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * {
	 * 
	 * GST_Customer_Master CM = new GST_Customer_Master();
	 * Registered_Customer_Code_IntraState =
	 * CM.createCustomer_Registered_IntraState( driver );
	 * 
	 * }
	 * 
	 * // Reg + Inter + Customer
	 * 
	 * @Test(priority=24)
	 * 
	 * public void Registered_InterState_Customer_Creationfor_India_CN_DN_Reg()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * {
	 * 
	 * GST_Customer_Master CM = new GST_Customer_Master();
	 * Registered_Customer_Code_InterState =
	 * CM.copyCustomer_Registered_InterState( driver ,
	 * Registered_Customer_Code_IntraState);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Intra + Customer
	 * 
	 * @Test(priority=25) public void
	 * Composition_IntraState_Customer_Creationfor_India_CN_DN_Reg() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * {
	 * 
	 * GST_Customer_Master CM = new GST_Customer_Master();
	 * Composition_Customer_Code_IntraState =
	 * CM.createCustomer_Composition_IntraState( driver );
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Composition + Inter + Customer
	 * 
	 * @Test(priority=26)
	 * 
	 * public void Composition_InterState_Customer_Creationfor_India_CN_DN_Reg()
	 * throws InterruptedException, AWTException, IOException
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
	 * // SEZ + WPAY + Customer
	 * 
	 * @Test(priority=27)
	 * 
	 * public void Registered_SEZ_WPAY_Customer_Creationfor_India_CN_DN_Reg()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master(); SEZ_WPAY =
	 * CM.createCustomer_SEZ_WPAY( driver);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WOPAY) + Inter + Customer
	 * 
	 * 
	 * @Test(priority=28)
	 * 
	 * public void Registered_SEZ_WOPAY_Customer_Creationfor_India_CN_DN_Reg()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master(); SEZ_WOPAY =
	 * CM.copyCustomer_SEZ_WOPAY( driver , SEZ_WPAY);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Product with Product tax class
	 * 
	 * @Test(priority=29) public static void
	 * Product_Creationfor_India_CN_DN_Reg() throws InterruptedException,
	 * AWTException, IOException {
	 * 
	 * GST_Product_Master PM = new GST_Product_Master(); Normal_Product_ID =
	 * PM.createProduct_Normal(driver);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Intra + Credit Note
	 * 
	 * @Test(priority=30) public static void
	 * CreditNote_Creationfor_India_Registered_IntraState_CN_DN() throws
	 * InterruptedException, AWTException, IOException { GST_Credit_Note CN =
	 * new GST_Credit_Note ();
	 * CN.createCreditNote_Registered_IntraState_GSTR1_CN_DN_Reg(driver,
	 * Registered_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Credit Note
	 * 
	 * @Test(priority=31) public static void
	 * CreditNote_Creationfor_India_Registered_InterState_CN_DN() throws
	 * InterruptedException, AWTException, IOException { GST_Credit_Note CN =
	 * new GST_Credit_Note ();
	 * CN.createCreditNote_Registered_InterState_GSTR1_CN_DN_Reg(driver,
	 * Registered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Intra + Credit Note
	 * 
	 * @Test(priority=32) public static void
	 * CreditNote_Creationfor_India_Composition_IntraState_CN_DN() throws
	 * InterruptedException, AWTException, IOException { GST_Credit_Note CN =
	 * new GST_Credit_Note ();
	 * CN.createCreditNote_Composition_IntraState_GSTR1_CN_DN_Reg(driver,
	 * Composition_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Inter + Credit Note
	 * 
	 * @Test(priority=33) public static void
	 * CreditNote_Creationfor_India_Composition_InterState_CN_DN() throws
	 * InterruptedException, AWTException, IOException { GST_Credit_Note CN =
	 * new GST_Credit_Note ();
	 * CN.createCreditNote_Composition_InterState_GSTR1_CN_DN_Reg(driver,
	 * Composition_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // SEZ(WPAY) + Inter + Credit Note
	 * 
	 * @Test(priority=34) public static void
	 * CreditNote_Creationfor_India_SEZ_WPAY_InterState_CN_DN() throws
	 * InterruptedException, AWTException, IOException { GST_Credit_Note CN =
	 * new GST_Credit_Note ();
	 * CN.createCreditNote_SEZ_WPAY_InterState_GSTR1_CN_DN_Reg(driver, SEZ_WPAY,
	 * Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // SEZ(WOPAY) + Inter + Credit Note
	 * 
	 * @Test(priority=35) public static void
	 * CreditNote_Creationfor_India_SEZ_WOPAY_InterState_CN_DN() throws
	 * InterruptedException, AWTException, IOException { GST_Credit_Note CN =
	 * new GST_Credit_Note ();
	 * CN.createCreditNote_SEZ_WOPAY_InterState_GSTR1_CN_DN_Reg(driver,
	 * SEZ_WOPAY, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority=36) public void
	 * Registered_IntraState_Customer_Creationfor_India_CN_DN_Reg() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * {
	 * 
	 * GST_Customer_Master CM = new GST_Customer_Master();
	 * Registered_Customer_Code_IntraState =
	 * CM.createCustomer_Registered_IntraState( driver );
	 * 
	 * }
	 * 
	 * // Reg + Inter + Customer
	 * 
	 * @Test(priority=37)
	 * 
	 * public void Registered_InterState_Customer_Creationfor_India_CN_DN_Reg()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * {
	 * 
	 * GST_Customer_Master CM = new GST_Customer_Master();
	 * Registered_Customer_Code_InterState =
	 * CM.copyCustomer_Registered_InterState( driver ,
	 * Registered_Customer_Code_IntraState);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Product with Product tax class
	 * 
	 * @Test(priority=38) public static void
	 * Product_Creationfor_India_CN_DN_Reg() throws InterruptedException,
	 * AWTException, IOException {
	 * 
	 * GST_Product_Master PM = new GST_Product_Master(); Normal_Product_ID =
	 * PM.createProduct_Normal(driver);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Intra + Customer
	 * 
	 * @Test(priority=39) public void
	 * Composition_IntraState_Customer_Creationfor_India_CN_DN_Reg() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * {
	 * 
	 * GST_Customer_Master CM = new GST_Customer_Master();
	 * Composition_Customer_Code_IntraState =
	 * CM.createCustomer_Composition_IntraState( driver );
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Inter + Customer
	 * 
	 * @Test(priority=40)
	 * 
	 * public void Composition_InterState_Customer_Creationfor_India_CN_DN_Reg()
	 * throws InterruptedException, AWTException, IOException
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
	 * @Test(priority=41) public static void
	 * DebitNote_Creationfor_India_Registered_IntraState_CN_DN() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note ();
	 * DN.createDebitNote_Registered_IntraState_GSTR1_CN_DN_Reg(driver,
	 * Registered_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority=42) public static void
	 * DebitNote_Creationfor_India_Registered_InterState_CN_DN() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note ();
	 * DN.createDebitNote_Registered_InterState_GSTR1_CN_DN_Reg(driver,
	 * Registered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority=43) public static void
	 * DebitNote_Creationfor_India_Composition_IntraState_CN_DN() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note ();
	 * DN.createDebitNote_Composition_IntraState_GSTR1_CN_DN_Reg(driver,
	 * Composition_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority=44) public static void
	 * DebitNote_Creationfor_India_Composition_InterState_CN_DN() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note ();
	 * DN.createDebitNote_Composition_InterState_GSTR1_CN_DN_Reg(driver,
	 * Composition_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // SEZ + WPAY + Customer
	 * 
	 * @Test(priority=45)
	 * 
	 * public void Registered_SEZ_WPAY_Customer_Creationfor_India_CN_DN_Reg()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master(); SEZ_WPAY =
	 * CM.createCustomer_SEZ_WPAY( driver);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Reg + SEZ(WOPAY) + Inter + Customer
	 * 
	 * 
	 * @Test(priority=46)
	 * 
	 * public void Registered_SEZ_WOPAY_Customer_Creationfor_India_CN_DN_Reg()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master(); SEZ_WOPAY =
	 * CM.copyCustomer_SEZ_WOPAY( driver , SEZ_WPAY);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Test(priority=47) public static void
	 * DebitNote_Creationfor_India_SEZ_WPAY_InterState_CN_DN() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note ();
	 * DN.createDebitNote_SEZ_WPAY_InterState_GSTR1_CN_DN_Reg(driver, SEZ_WPAY,
	 * Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority=48) public static void
	 * DebitNote_Creationfor_India_SEZ_WOPAY_InterState_CN_DN() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note ();
	 * DN.createDebitNote_SEZ_WOPAY_InterState_GSTR1_CN_DN_Reg(driver,
	 * SEZ_WOPAY, Normal_Product_ID);
	 * 
	 * }
	 */

	/*
	 * // Section Credit / Debit Note (UnRegistered - 9B)
	 * 
	 * // Unreg + Intra + Customer
	 * 
	 * @Test(priority=49) public void
	 * UnRegistered_IntraState_Customer_Creationfor_India_CN_DN_UnReg() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master();
	 * UnRegistered_Customer_Code_IntraState =
	 * CM.createCustomer_UnRegistered_IntraState( driver );
	 * 
	 * }
	 * 
	 * 
	 * // Unreg + Inter + Customer
	 * 
	 * @Test(priority=50)
	 * 
	 * public void
	 * UnRegistered_InterState_Customer_Creationfor_India_CN_DN_UnReg() throws
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
	 * 
	 * // Product with Product tax class
	 * 
	 * @Test(priority=51) public static void
	 * Product_Creationfor_India_CN_DN_UnReg() throws InterruptedException,
	 * AWTException, IOException {
	 * 
	 * GST_Product_Master PM = new GST_Product_Master(); Normal_Product_ID =
	 * PM.createProduct_Normal(driver);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Inter + Credit Note
	 * 
	 * @Test(priority=52) public static void
	 * CreditNote_Creationfor_India_UnRegistered_InterState_CN_DN() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_UnRegistered_InterState_GSTR1_CN_DN_UnReg(driver,
	 * UnRegistered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * // UnReg + Inter + Debit Note
	 * 
	 * @Test(priority=53) public static void
	 * DebitNote_Creationfor_India_UnRegistered_InterState_CN_DN() throws
	 * InterruptedException, AWTException, IOException { GST_Debit_Note DN = new
	 * GST_Debit_Note ();
	 * DN.createDebitNote_UnRegistered_InterState_GSTR1_CN_DN_UnReg(driver,
	 * UnRegistered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Unreg. + Export(WPAY) + Customer
	 * 
	 * @Test(priority=54)
	 * 
	 * public void UnRegistered_Export_WPAY_Customer_Creationfor_India() throws
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
	 * @Test(priority=55)
	 * 
	 * public void UnRegistered_Export_WOPAY_Customer_Creationfor_India() throws
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
	 * // UnReg + Export(WPAY) +Inter + Credit Note
	 * 
	 * @Test(priority=56) public static void
	 * CreditNote_Creationfor_India_UnRegistered_Export_WPAY_InterState_CN_DN()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note ();
	 * CN.createCreditNote_UnRegistered_Export_WPAY_InterState_GSTR1_CN_DN_UnReg
	 * (driver, Export_WPAY, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Export(WPAY) +Inter + Credit Note
	 * 
	 * @Test(priority=57) public static void
	 * CreditNote_Creationfor_India_UnRegistered_Export_WOPAY_InterState_CN_DN()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Credit_Note CN = new GST_Credit_Note (); CN.
	 * createCreditNote_UnRegistered_Export_WOPAY_InterState_GSTR1_CN_DN_UnReg(
	 * driver, Export_WOPAY, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Export(WPAY) + Inter + Debit Note
	 * 
	 * @Test(priority=58) public static void
	 * DebitNote_Creationfor_India_UnRegistered_Export_WPAY_InterState_CN_DN()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Debit_Note DN = new GST_Debit_Note ();
	 * DN.createDebitNote_UnRegistered_Export_WPAY_InterState_GSTR1_CN_DN_UnReg(
	 * driver, Export_WPAY, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * // UnReg + Export(WOPAY) + Inter + Debit Note
	 * 
	 * public static void
	 * DebitNote_Creationfor_India_UnRegistered_Export_WOPAY_InterState_CN_DN()
	 * throws InterruptedException, AWTException, IOException { GST_Debit_Note
	 * DN = new GST_Debit_Note ();
	 * DN.createDebitNote_UnRegistered_Export_WOPAY_InterState_GSTR1_CN_DN_UnReg
	 * (driver, Export_WOPAY, Normal_Product_ID);
	 * 
	 * }
	 */

	/*
	 * 
	 * // Section Export Invoices (6A)
	 * 
	 * // Unreg. + Export(WPAY) + Customer
	 * 
	 * @Test(priority=59)
	 * 
	 * public void
	 * UnRegistered_Export_WPAY_Customer_Creationfor_India_Export_Invoices()
	 * throws InterruptedException, AWTException, IOException
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
	 * @Test(priority=60)
	 * 
	 * public void
	 * UnRegistered_Export_WOPAY_Customer_Creationfor_India_Export_Invoices()
	 * throws InterruptedException, AWTException, IOException
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
	 * // Product with Product tax class
	 * 
	 * @Test(priority=61) public static void Product_Creationfor_India_Export()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Product_Master PM = new GST_Product_Master(); Normal_Product_ID =
	 * PM.createProduct_Normal(driver);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * // UnReg + Export(WPAY) + Inter + Sales Invoice
	 * 
	 * @Test(priority=62) public static void
	 * SalesInvoice_Creationfor_India_UnRegistered_Export_WPAY_InterState()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice (); SI.
	 * createSalesInvoice_UnRegistered_Export_WPAY_InterState_GSTR1_Export_Invoices
	 * (driver, Export_WPAY, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * // UnReg + Export(WOPAY) + Inter + Sales Invoice
	 * 
	 * @Test(priority=63) public static void
	 * SalesInvoice_Creationfor_India_UnRegistered_Export_WOPAY_InterState()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Sales_Invoice SI = new GST_Sales_Invoice (); SI.
	 * createSalesInvoice_UnRegistered_Export_WOPAY_InterState_GSTR1_Export_Invoices
	 * (driver, Export_WOPAY, Normal_Product_ID);
	 * 
	 * }
	 * 
	 */
	/*
	 * // Tax Liability(Advances received) - 11A(1), 11A(2)
	 * 
	 * // Reg + Intra + Customer
	 * 
	 * @Test(priority=64) public void
	 * Registered_IntraState_Customer_Creationfor_India_Advanced_Received()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master();
	 * Registered_Customer_Code_IntraState =
	 * CM.createCustomer_Registered_IntraState( driver );
	 * 
	 * }
	 * 
	 * // Reg + Inter + Customer
	 * 
	 * @Test(priority=65)
	 * 
	 * public void
	 * Registered_InterState_Customer_Creationfor_India_Advanced_Received()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * { //String Customer_Code = "CC00009" ; // Copy GST_Customer_Master CM =
	 * new GST_Customer_Master(); Registered_Customer_Code_InterState =
	 * CM.copyCustomer_Registered_InterState( driver ,
	 * Registered_Customer_Code_IntraState);
	 * 
	 * 
	 * }
	 * 
	 * // Unreg + Intra + Customer
	 * 
	 * @Test(priority=66) public void
	 * UnRegistered_IntraState_Customer_Creationfor_India_Advanced_Received()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master();
	 * UnRegistered_Customer_Code_IntraState =
	 * CM.createCustomer_UnRegistered_IntraState( driver );
	 * 
	 * }
	 * 
	 * 
	 * // Unreg + Inter + Customer
	 * 
	 * @Test(priority=67)
	 * 
	 * public void
	 * UnRegistered_InterState_Customer_Creationfor_India_Advanced_Received()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master();
	 * UnRegistered_Customer_Code_InterState =
	 * CM.copyCustomer_UnRegistered_InterState( driver ,
	 * UnRegistered_Customer_Code_IntraState);
	 * 
	 * 
	 * }
	 * 
	 * // Composition + Intra + Customer
	 * 
	 * @Test(priority=68) public void
	 * Composition_IntraState_Customer_Creationfor_India_Advanced_Received()
	 * throws InterruptedException, AWTException, IOException
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
	 * @Test(priority=69)
	 * 
	 * public void
	 * Composition_InterState_Customer_Creationfor_India_Advanced_Received()
	 * throws InterruptedException, AWTException, IOException
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
	 * // SEZ + WPAY + Customer
	 * 
	 * @Test(priority=70)
	 * 
	 * public void
	 * Registered_SEZ_WPAY_Customer_Creationfor_India_Advanced_Received() throws
	 * InterruptedException, AWTException, IOException
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master(); SEZ_WPAY =
	 * CM.createCustomer_SEZ_WPAY( driver);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * // Reg + SEZ(WOPAY) + Inter + Customer
	 * 
	 * 
	 * @Test(priority=71)
	 * 
	 * public void
	 * Registered_SEZ_WOPAY_Customer_Creationfor_India_Advanced_Received()
	 * throws InterruptedException, AWTException, IOException
	 * 
	 * { GST_Customer_Master CM = new GST_Customer_Master(); SEZ_WOPAY =
	 * CM.copyCustomer_SEZ_WOPAY( driver , SEZ_WPAY);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Unreg. + Export(WPAY) + Customer
	 * 
	 * @Test(priority=72)
	 * 
	 * public void
	 * Registered_Export_WPAY_Customer_Creationfor_India_Advanced_Received()
	 * throws InterruptedException, AWTException, IOException
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
	 * @Test(priority=73)
	 * 
	 * public void
	 * Registered_Export_WOPAY_Customer_Creationfor_India_Advanced_Received()
	 * throws InterruptedException, AWTException, IOException
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
	 * // Product with Product tax class
	 * 
	 * @Test(priority=74) public static void
	 * Product_Creationfor_India_Advanced_Received() throws
	 * InterruptedException, AWTException, IOException {
	 * 
	 * GST_Product_Master PM = new GST_Product_Master(); Normal_Product_ID =
	 * PM.createProduct_Normal(driver);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Intra + Advanced Received
	 * 
	 * @Test(priority=75) public static void
	 * AdvancedReceipt_Creationfor_India_Registered_IntraState_Advanced_Received
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_IntraState_GSTR1_Advanced_Received(driver,
	 * Registered_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Advanced Received
	 * 
	 * @Test(priority=76) public static void
	 * AdvancedReceipt_Creationfor_India_Registered_InterState_Advanced_Received
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_InterState_GSTR1_Advanced_Received(driver,
	 * Registered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // UnReg + Intra + Advanced Received
	 * 
	 * @Test(priority=77) public static void
	 * AdvancedReceipt_Creationfor_India_UnRegistered_IntraState_Advanced_Received
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_IntraState_GSTR1_Advanced_Received(driver,
	 * UnRegistered_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Reg + Inter + Advanced Received
	 * 
	 * @Test(priority=78) public static void
	 * AdvancedReceipt_Creationfor_India_UnRegistered_InterState_Advanced_Received
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_InterState_GSTR1_Advanced_Received(driver,
	 * UnRegistered_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Composition + Intra + Advanced Received
	 * 
	 * @Test(priority=79) public static void
	 * AdvancedReceipt_Creationfor_India_Composition_IntraState_Advanced_Received
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_IntraState_GSTR1_Advanced_Received(driver,
	 * Composition_Customer_Code_IntraState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // Composition + Inter + Advanced Received
	 * 
	 * @Test(priority=80) public static void
	 * AdvancedReceipt_Creationfor_India_Composition_InterState_Advanced_Received
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_InterState_GSTR1_Advanced_Received(driver,
	 * Composition_Customer_Code_InterState, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // SEZ(WPAY) + Inter + Advanced Received
	 * 
	 * @Test(priority=81) public static void
	 * AdvancedReceipt_Creationfor_India_SEZ_WPAY_InterState_Advanced_Received()
	 * throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_InterState_GSTR1_Advanced_Received(driver,
	 * SEZ_WPAY, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * // SEZ(WOPAY) + Inter + Advanced Received
	 * 
	 * @Test(priority=82) public static void
	 * AdvancedReceipt_Creationfor_India_SEZ_WOPAY_InterState_Advanced_Received(
	 * ) throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_InterState_GSTR1_Advanced_Received_WOPAY(
	 * driver, SEZ_WOPAY, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * // Export(WPAY) + Inter + Advanced Received
	 * 
	 * 
	 * @Test(priority=83) public static void
	 * AdvancedReceipt_Creationfor_India_Export_WPAY_InterState_Advanced_Received
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_InterState_GSTR1_Advanced_Received_Export_WPAY(
	 * driver, SEZ_WPAY, Normal_Product_ID);
	 * 
	 * }
	 * 
	 * // Export(WOPAY) + Inter + Advanced Received
	 * 
	 * @Test(priority=84) public static void
	 * AdvancedReceipt_Creationfor_India_Export_WOPAY_InterState_Advanced_Received
	 * () throws InterruptedException, AWTException, IOException {
	 * 
	 * GST_Advanced_Received AR = new GST_Advanced_Received ();
	 * AR.createAdvancedReceived_InterState_GSTR1_Advanced_Received_WOPAY(
	 * driver, Export_WOPAY, Normal_Product_ID);
	 * 
	 * }
	 */

	// Section Nil Rated Invoices - 8A, 8B, 8C, 8D

	// Reg + Intra + Customer

	@Test(priority = 1)
	public void Registered_IntraState_Customer_Creationfor_India_NIL_Rated()
			throws InterruptedException, AWTException, IOException

	{
		GST_Customer_Master CM = new GST_Customer_Master();
		Registered_Customer_Code_IntraState = CM.createCustomer_Registered_IntraState(driver);

	}

	// Reg + Inter + Customer

	@Test(priority = 2)

	public void Registered_InterState_Customer_Creationfor_India_NIL_Rated()
			throws InterruptedException, AWTException, IOException

	{ // String Customer_Code = "CC00009" ; // Copy
		GST_Customer_Master CM = new GST_Customer_Master();
		Registered_Customer_Code_InterState = CM.copyCustomer_Registered_InterState(driver,
				Registered_Customer_Code_IntraState);

	}

	// Unreg + Intra + Customer

	@Test(priority = 3)
	public void UnRegistered_IntraState_Customer_Creationfor_India_NIL_Rated()
			throws InterruptedException, AWTException, IOException

	{
		GST_Customer_Master CM = new GST_Customer_Master();
		UnRegistered_Customer_Code_IntraState = CM.createCustomer_UnRegistered_IntraState(driver);

	}

	// Unreg + Inter + Customer

	@Test(priority = 4)

	public void UnRegistered_InterState_Customer_Creationfor_India_NIL_Rated()
			throws InterruptedException, AWTException, IOException

	{
		GST_Customer_Master CM = new GST_Customer_Master();
		UnRegistered_Customer_Code_InterState = CM.copyCustomer_UnRegistered_InterState(driver,
				UnRegistered_Customer_Code_IntraState);

	}

	// Composition + Intra + Customer

	@Test(priority = 5)
	public void Composition_IntraState_Customer_Creationfor_India_NIL_Rated()
			throws InterruptedException, AWTException, IOException

	{
		GST_Customer_Master CM = new GST_Customer_Master();
		Composition_Customer_Code_IntraState = CM.createCustomer_Composition_IntraState(driver);

	}

	// Composition + Inter + Customer

	@Test(priority = 6)

	public void Composition_InterState_Customer_Creationfor_India_NIL_Rated()
			throws InterruptedException, AWTException, IOException

	{
		GST_Customer_Master CM = new GST_Customer_Master();
		Composition_Customer_Code_InterState = CM.createCustomer_Composition_InterState(driver,
				Composition_Customer_Code_IntraState);

	}

	// SEZ + WPAY + Customer

	@Test(priority = 7)

	public void Registered_SEZ_WPAY_Customer_Creationfor_India_NIL_Rated()
			throws InterruptedException, AWTException, IOException

	{
		GST_Customer_Master CM = new GST_Customer_Master();
		SEZ_WPAY = CM.createCustomer_SEZ_WPAY(driver);

	}

	// Reg + SEZ(WOPAY) + Inter + Customer

	@Test(priority = 8)

	public void Registered_SEZ_WOPAY_Customer_Creationfor_India_NIL_Rated()
			throws InterruptedException, AWTException, IOException

	{
		GST_Customer_Master CM = new GST_Customer_Master();
		SEZ_WOPAY = CM.copyCustomer_SEZ_WOPAY(driver, SEZ_WPAY);

	}

	// Product with 0% Product tax class

	@Test(priority = 9)
	public static void NIL_Rated_Product_Creationfor_India() throws InterruptedException, AWTException, IOException {
		GST_Product_Master PM = new GST_Product_Master();
		NIL_Rated_Product_ID = PM.createProduct_NIL_Rated(driver);

	}

	// Reg + Intra + Sales Invoice + NIL Rated

	@Test(priority = 10)
	public static void SalesInvoice_Creationfor_India_Registered_IntraState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_IntraState,
				NIL_Rated_Product_ID);

	}

	// Reg + Inter + Sales Invoice + NIL Rated

	@Test(priority = 11)
	public static void SalesInvoice_Creationfor_India_Registered_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_InterState,
				NIL_Rated_Product_ID);

	}

	// UnReg + Intra + Sales Invoice + NIL Rated

	@Test(priority = 12)
	public static void SalesInvoice_Creationfor_India_UnRegistered_IntraState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_IntraState,
				NIL_Rated_Product_ID);

	}

	// UnReg + Inter + Sales Invoice + NIL Rated

	@Test(priority = 13)
	public static void SalesInvoice_Creationfor_India_UnRegistered_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_InterState,
				NIL_Rated_Product_ID);

	}

	// Composition + Intra + Sales Invoice + NIL Rated

	@Test(priority = 14)
	public static void SalesInvoice_Creationfor_India_Composition_IntraState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_IntraState,
				NIL_Rated_Product_ID);

	}

	// Composition + Inter + Sales Invoice + NIL Rated

	@Test(priority = 15)
	public static void SalesInvoice_Creationfor_India_Composition_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_InterState,
				NIL_Rated_Product_ID);

	}

	// SEZ(WPAY) + Inter + Sales Invoice + NIL Rated

	@Test(priority = 16)
	public static void SalesInvoice_Creationfor_India_SEZ_WPAY_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, SEZ_WPAY, NIL_Rated_Product_ID);

	}

	// SEZ(WOPAY) + Inter + Sales Invoice + NIL Rated

	@Test(priority = 17)
	public static void SalesInvoice_Creationfor_India_SEZ_WOPAY_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, SEZ_WOPAY, NIL_Rated_Product_ID);

	}

	// Credit Note

	// Reg + Intra + Credit Note + NIL Rated

	@Test(priority = 18)
	public static void CreditNote_Creationfor_India_Registered_IntraState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_IntraState,
				NIL_Rated_Product_ID);

	}

	// Reg + Inter + Credit Note + NIL Rated

	@Test(priority = 19)
	public static void CreditNote_Creationfor_India_Registered_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_InterState,
				NIL_Rated_Product_ID);

	}

	// UnReg + Intra + Credit Note + NIL Rated

	@Test(priority = 20)
	public static void CreditNote_Creationfor_India_UnRegistered_IntraState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_IntraState,
				NIL_Rated_Product_ID);

	}

	// UnReg + Inter + Credit Note + NIL Rated

	@Test(priority = 21)
	public static void CreditNote_Creationfor_India_UnRegistered_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_InterState,
				NIL_Rated_Product_ID);

	}

	// Composition + Intra + Credit Note + NIL Rated

	@Test(priority = 22)
	public static void CreditNote_Creationfor_India_Composition_IntraState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_IntraState,
				NIL_Rated_Product_ID);

	}

	// Composition + Inter + Credit Note + NIL Rated

	@Test(priority = 23)
	public static void CreditNote_Creationfor_India_Composition_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_InterState,
				NIL_Rated_Product_ID);

	}

	// SEZ(WPAY) + Inter + Credit Note + NIL Rated

	@Test(priority = 24)
	public static void CreditNote_Creationfor_India_SEZ_WPAY_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, SEZ_WPAY, NIL_Rated_Product_ID);

	}

	// SEZ(WOPAY) + Inter + Credit Note + NIL Rated

	@Test(priority = 25)
	public static void CreditNote_Creationfor_India_SEZ_WOPAY_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, SEZ_WOPAY, NIL_Rated_Product_ID);

	}

	// Debit Note

	// Reg + Intra + Debit Note + NIL Rated

	@Test(priority = 26)
	public static void DebitNote_Creationfor_India_Registered_IntraState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_IntraState,
				NIL_Rated_Product_ID);

	}

	// Reg + Inter + Debit Note + NIL Rated

	@Test(priority = 27)
	public static void DebitNote_Creationfor_India_Registered_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_InterState,
				NIL_Rated_Product_ID);

	}

	// UnReg + Intra + Debit Note + NIL Rated

	@Test(priority = 28)
	public static void DebitNote_Creationfor_India_UnRegistered_IntraState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_IntraState,
				NIL_Rated_Product_ID);

	}

	// UnReg + Inter + Debit Note + NIL Rated

	@Test(priority = 29)
	public static void DebitNote_Creationfor_India_UnRegistered_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_InterState,
				NIL_Rated_Product_ID);

	}

	// Composition + Intra + Debit Note + NIL Rated

	@Test(priority = 30)
	public static void DebitNote_Creationfor_India_Composition_IntraState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_IntraState,
				NIL_Rated_Product_ID);

	}

	// Composition + Inter + Debit Note + NIL Rated

	@Test(priority = 31)
	public static void DebitNote_Creationfor_India_Composition_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_InterState,
				NIL_Rated_Product_ID);

	}

	// SEZ(WPAY) + Intra + Debit Note + NIL Rated

	@Test(priority = 32)
	public static void DebitNote_Creationfor_India_SEZ_WPAY_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, SEZ_WPAY, NIL_Rated_Product_ID);

	}

	// SEZ(WOPAY) + Inter + Debit Note + NIL Rated

	@Test(priority = 33)
	public static void DebitNote_Creationfor_India_SEZ_WOPAY_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, SEZ_WOPAY, NIL_Rated_Product_ID);

	}

	// Reg + Intra + Advanced Received + NIL Rated
	// Advances comes under Advanced_Received section only

	@Test(priority = 34)
	public static void AdvancedReceipt_Creationfor_India_Registered_IntraState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_IntraState,
				NIL_Rated_Product_ID);

	}

	// Reg + Inter + Advanced Received + NIL Rated

	@Test(priority = 35)
	public static void AdvancedReceipt_Creationfor_India_Registered_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_InterState,
				NIL_Rated_Product_ID);

	}

	// UnReg + Intra + Advanced Received + NIL Rated

	@Test(priority = 36)
	public static void AdvancedReceipt_Creationfor_India_UnRegistered_IntraState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_IntraState,
				NIL_Rated_Product_ID);

	}

	// UnReg + Inter + Advanced Received + NIL Rated

	@Test(priority = 37)
	public static void AdvancedReceipt_Creationfor_India_UnRegistered_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_InterState,
				NIL_Rated_Product_ID);

	}

	// Composition + Intra + Advanced Received + NIL Rated

	@Test(priority = 38)
	public static void AdvancedReceipt_Creationfor_India_Composition_IntraState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_IntraState,
				NIL_Rated_Product_ID);

	}

	// Composition + Inter + Advanced Received + NIL Rated
	@Test(priority = 39)
	public static void AdvancedReceipt_Creationfor_India_Composition_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_InterState,
				NIL_Rated_Product_ID);

	}

	// SEZ(WPAY) + Inter + Advanced Received + NIL Rated
	@Test(priority = 40)
	public static void AdvancedReceipt_Creationfor_India_SEZ_WPAY_InterState_NIL_Rated()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, SEZ_WPAY, NIL_Rated_Product_ID);

	}

	// ----------- Exempted Product -------------------------

	// Exempted Product

	@Test(priority = 41)
	public static void Exempted_Product_Creationfor_India() throws InterruptedException, AWTException, IOException {

		GST_Product_Master PM = new GST_Product_Master();
		Exempted_Product_ID = PM.createProduct_Exempted(driver);

	}

	// Reg + Intra + Sales Invoice + Exempted

	@Test(priority = 42)
	public static void SalesInvoice_Creationfor_India_Registered_IntraState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_IntraState,
				Exempted_Product_ID);

	}

	// Reg + Inter + Sales Invoice + Exempted

	@Test(priority = 43)
	public static void SalesInvoice_Creationfor_India_Registered_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_InterState,
				Exempted_Product_ID);

	}

	// UnReg + Intra + Sales Invoice + Exempted

	@Test(priority = 44)
	public static void SalesInvoice_Creationfor_India_UnRegistered_IntraState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_IntraState,
				Exempted_Product_ID);

	}

	// UnReg + Inter + Sales Invoice + Exempted

	@Test(priority = 45)
	public static void SalesInvoice_Creationfor_India_UnRegistered_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_InterState,
				Exempted_Product_ID);

	}

	// Composition + Intra + Sales Invoice + Exempted

	@Test(priority = 46)
	public static void SalesInvoice_Creationfor_India_Composition_IntraState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_IntraState,
				Exempted_Product_ID);

	}

	// Composition + Inter + Sales Invoice + Exempted

	@Test(priority = 47)
	public static void SalesInvoice_Creationfor_India_Composition_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_InterState,
				Exempted_Product_ID);

	}

	// SEZ(WPAY) + Inter + Sales Invoice + Exempted

	@Test(priority = 48)
	public static void SalesInvoice_Creationfor_India_SEZ_WPAY_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, SEZ_WPAY, Exempted_Product_ID);

	}

	// SEZ(WOPAY) + Inter + Sales Invoice + Exempted

	@Test(priority = 49)
	public static void SalesInvoice_Creationfor_India_SEZ_WOPAY_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, SEZ_WOPAY, Exempted_Product_ID);

	}

	// Credit Note

	// Reg + Intra + Credit Note + Exempted

	@Test(priority = 50)
	public static void CreditNote_Creationfor_India_Registered_IntraState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_IntraState,
				Exempted_Product_ID);

	}

	// Reg + Inter + Credit Note + Exempted

	@Test(priority = 51)
	public static void CreditNote_Creationfor_India_Registered_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_InterState,
				Exempted_Product_ID);

	}

	// UnReg + Intra + Credit Note + Exempted

	@Test(priority = 52)
	public static void CreditNote_Creationfor_India_UnRegistered_IntraState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_IntraState,
				Exempted_Product_ID);

	}

	// UnReg + Inter + Credit Note + Exempted

	@Test(priority = 53)
	public static void CreditNote_Creationfor_India_UnRegistered_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_InterState,
				Exempted_Product_ID);

	}

	// Composition + Intra + Credit Note + Exempted

	@Test(priority = 54)
	public static void CreditNote_Creationfor_India_Composition_IntraState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_IntraState,
				Exempted_Product_ID);

	}

	// Composition + Inter + Credit Note + Exempted

	@Test(priority = 55)
	public static void CreditNote_Creationfor_India_Composition_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_InterState,
				Exempted_Product_ID);

	}

	// SEZ(WPAY) + Inter + Credit Note + Exempted

	@Test(priority = 56)
	public static void CreditNote_Creationfor_India_SEZ_WPAY_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, SEZ_WPAY, Exempted_Product_ID);

	}

	// SEZ(WOPAY) + Inter + Credit Note + Exempted

	@Test(priority = 57)
	public static void CreditNote_Creationfor_India_SEZ_WOPAY_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, SEZ_WOPAY, Exempted_Product_ID);

	}

	// Debit Note

	// Reg + Intra + Debit Note + Exempted

	@Test(priority = 58)
	public static void DebitNote_Creationfor_India_Registered_IntraState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_IntraState, Exempted_Product_ID);

	}

	// Reg + Inter + Debit Note + Exempted

	@Test(priority = 59)
	public static void DebitNote_Creationfor_India_Registered_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_InterState, Exempted_Product_ID);

	}

	// UnReg + Intra + Debit Note + Exempted

	@Test(priority = 60)
	public static void DebitNote_Creationfor_India_UnRegistered_IntraState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_IntraState,
				Exempted_Product_ID);

	}

	// UnReg + Inter + Debit Note + Exempted

	@Test(priority = 61)
	public static void DebitNote_Creationfor_India_UnRegistered_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_InterState,
				Exempted_Product_ID);

	}

	// Composition + Intra + Debit Note + Exempted

	@Test(priority = 62)
	public static void DebitNote_Creationfor_India_Composition_IntraState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_IntraState,
				Exempted_Product_ID);

	}

	// Composition + Inter + Debit Note + Exempted

	@Test(priority = 63)
	public static void DebitNote_Creationfor_India_Composition_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_InterState,
				Exempted_Product_ID);

	}

	// SEZ(WPAY) + Intra + Debit Note + Exempted

	@Test(priority = 64)
	public static void DebitNote_Creationfor_India_SEZ_WPAY_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, SEZ_WPAY, Exempted_Product_ID);

	}

	// SEZ(WOPAY) + Inter + Debit Note + Exempted

	@Test(priority = 65)
	public static void DebitNote_Creationfor_India_SEZ_WOPAY_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, SEZ_WOPAY, Exempted_Product_ID);

	}

	// Reg + Intra + Advanced Received + Exempted
	// Advances comes under Advanced_Received section only

	@Test(priority = 66)
	public static void AdvancedReceipt_Creationfor_India_Registered_IntraState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_IntraState,
				Exempted_Product_ID);

	}

	// Reg + Inter + Advanced Received + Exempted

	@Test(priority = 67)
	public static void AdvancedReceipt_Creationfor_India_Registered_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_InterState,
				Exempted_Product_ID);

	}

	// UnReg + Intra + Advanced Received + Exempted

	@Test(priority = 68)
	public static void AdvancedReceipt_Creationfor_India_UnRegistered_IntraState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_IntraState,
				Exempted_Product_ID);

	}

	// UnReg + Inter + Advanced Received + Exempted

	@Test(priority = 69)
	public static void AdvancedReceipt_Creationfor_India_UnRegistered_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_InterState,
				Exempted_Product_ID);

	}

	// Composition + Intra + Advanced Received + Exempted

	@Test(priority = 70)
	public static void AdvancedReceipt_Creationfor_India_Composition_IntraState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_IntraState,
				Exempted_Product_ID);

	}

	// Composition + Inter + Advanced Received + Exempted
	@Test(priority = 71)
	public static void AdvancedReceipt_Creationfor_India_Composition_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_InterState,
				Exempted_Product_ID);

	}

	// SEZ(WPAY) + Inter + Advanced Received + Exempted
	@Test(priority = 72)
	public static void AdvancedReceipt_Creationfor_India_SEZ_WPAY_InterState_Exempted()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, SEZ_WPAY, Exempted_Product_ID);

	}

	// ----------- Non-GST Product -------------------------

	// Non-GST Product

	@Test(priority = 73)
	public static void Non_GST_Product_Creationfor_India() throws InterruptedException, AWTException, IOException {

		GST_Product_Master PM = new GST_Product_Master();
		NonGST_Product_ID = PM.createProduct_NonGST(driver);

	}

	// Reg + Intra + Sales Invoice + NonGST

	@Test(priority = 74)
	public static void SalesInvoice_Creationfor_India_Registered_IntraState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_IntraState,
				NonGST_Product_ID);

	}

	// Reg + Inter + Sales Invoice + NonGST

	@Test(priority = 75)
	public static void SalesInvoice_Creationfor_India_Registered_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_InterState,
				NonGST_Product_ID);

	}

	// UnReg + Intra + Sales Invoice + NonGST

	@Test(priority = 76)
	public static void SalesInvoice_Creationfor_India_UnRegistered_IntraState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_IntraState,
				NonGST_Product_ID);

	}

	// UnReg + Inter + Sales Invoice + NonGST

	@Test(priority = 77)
	public static void SalesInvoice_Creationfor_India_UnRegistered_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_InterState,
				NonGST_Product_ID);

	}

	// Composition + Intra + Sales Invoice + NonGST

	@Test(priority = 78)
	public static void SalesInvoice_Creationfor_India_Composition_IntraState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_IntraState,
				NonGST_Product_ID);

	}

	// Composition + Inter + Sales Invoice + NonGST

	@Test(priority = 79)
	public static void SalesInvoice_Creationfor_India_Composition_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_InterState,
				NonGST_Product_ID);

	}

	// SEZ(WPAY) + Inter + Sales Invoice + NonGST

	@Test(priority = 80)
	public static void SalesInvoice_Creationfor_India_SEZ_WPAY_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, SEZ_WPAY, NonGST_Product_ID);

	}

	// SEZ(WOPAY) + Inter + Sales Invoice + NonGST

	@Test(priority = 81)
	public static void SalesInvoice_Creationfor_India_SEZ_WOPAY_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Sales_Invoice SI = new GST_Sales_Invoice();
		SI.createSalesInvoice_NIL_Rated_Exempted_Non_GST(driver, SEZ_WOPAY, NonGST_Product_ID);

	}

	// Credit Note

	// Reg + Intra + Credit Note + NonGST

	@Test(priority = 82)
	public static void CreditNote_Creationfor_India_Registered_IntraState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_IntraState, NonGST_Product_ID);

	}

	// Reg + Inter + Credit Note + NonGST

	@Test(priority = 83)
	public static void CreditNote_Creationfor_India_Registered_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_InterState, NonGST_Product_ID);

	}

	// UnReg + Intra + Credit Note + NonGST

	@Test(priority = 84)
	public static void CreditNote_Creationfor_India_UnRegistered_IntraState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_IntraState,
				NonGST_Product_ID);

	}

	// UnReg + Inter + Credit Note + NonGST

	@Test(priority = 85)
	public static void CreditNote_Creationfor_India_UnRegistered_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_InterState,
				NonGST_Product_ID);

	}

	// Composition + Intra + Credit Note + NonGST

	@Test(priority = 86)
	public static void CreditNote_Creationfor_India_Composition_IntraState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_IntraState, NonGST_Product_ID);

	}

	// Composition + Inter + Credit Note + NonGST

	@Test(priority = 87)
	public static void CreditNote_Creationfor_India_Composition_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_InterState, NonGST_Product_ID);

	}

	// SEZ(WPAY) + Inter + Credit Note + NonGST

	@Test(priority = 88)
	public static void CreditNote_Creationfor_India_SEZ_WPAY_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, SEZ_WPAY, NonGST_Product_ID);

	}

	// SEZ(WOPAY) + Inter + Credit Note + NonGST

	@Test(priority = 89)
	public static void CreditNote_Creationfor_India_SEZ_WOPAY_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Credit_Note CN = new GST_Credit_Note();
		CN.createCreditNote_NIL_Rated_Exempted_Non_GST(driver, SEZ_WOPAY, NonGST_Product_ID);

	}

	// Debit Note

	// Reg + Intra + Debit Note + NonGST

	@Test(priority = 90)
	public static void DebitNote_Creationfor_India_Registered_IntraState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_IntraState, NonGST_Product_ID);

	}

	// Reg + Inter + Debit Note + NonGST

	@Test(priority = 91)
	public static void DebitNote_Creationfor_India_Registered_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_InterState, NonGST_Product_ID);

	}

	// UnReg + Intra + Debit Note + NonGST

	@Test(priority = 92)
	public static void DebitNote_Creationfor_India_UnRegistered_IntraState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_IntraState, NonGST_Product_ID);

	}

	// UnReg + Inter + Debit Note + NonGST

	@Test(priority = 93)
	public static void DebitNote_Creationfor_India_UnRegistered_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_InterState, NonGST_Product_ID);

	}

	// Composition + Intra + Debit Note + NonGST

	@Test(priority = 94)
	public static void DebitNote_Creationfor_India_Composition_IntraState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_IntraState, NonGST_Product_ID);

	}

	// Composition + Inter + Debit Note + NonGST

	@Test(priority = 95)
	public static void DebitNote_Creationfor_India_Composition_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_InterState, NonGST_Product_ID);

	}

	// SEZ(WPAY) + Intra + Debit Note + NonGST

	@Test(priority = 96)
	public static void DebitNote_Creationfor_India_SEZ_WPAY_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, SEZ_WPAY, NonGST_Product_ID);

	}

	// SEZ(WOPAY) + Inter + Debit Note + NonGST

	@Test(priority = 97)
	public static void DebitNote_Creationfor_India_SEZ_WOPAY_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Debit_Note DN = new GST_Debit_Note();
		DN.createDebitNote_NIL_Rated_Exempted_Non_GST(driver, SEZ_WOPAY, NonGST_Product_ID);

	}

	// Reg + Intra + Advanced Received + NonGST
	// Advances comes under Advanced_Received section only

	@Test(priority = 98)
	public static void AdvancedReceipt_Creationfor_India_Registered_IntraState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_IntraState,
				NonGST_Product_ID);

	}

	// Reg + Inter + Advanced Received + NonGST

	@Test(priority = 99)
	public static void AdvancedReceipt_Creationfor_India_Registered_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, Registered_Customer_Code_InterState,
				NonGST_Product_ID);

	}

	// UnReg + Intra + Advanced Received + NonGST

	@Test(priority = 100)
	public static void AdvancedReceipt_Creationfor_India_UnRegistered_IntraState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_IntraState,
				NonGST_Product_ID);

	}

	// UnReg + Inter + Advanced Received + NonGST

	@Test(priority = 101)
	public static void AdvancedReceipt_Creationfor_India_UnRegistered_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, UnRegistered_Customer_Code_InterState,
				NonGST_Product_ID);

	}

	// Composition + Intra + Advanced Received + NonGST

	@Test(priority = 102)
	public static void AdvancedReceipt_Creationfor_India_Composition_IntraState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_IntraState,
				NonGST_Product_ID);

	}

	// Composition + Inter + Advanced Received + NonGST
	@Test(priority = 103)
	public static void AdvancedReceipt_Creationfor_India_Composition_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, Composition_Customer_Code_InterState,
				NonGST_Product_ID);

	}

	// SEZ(WPAY) + Inter + Advanced Received + NonGST
	@Test(priority = 104)
	public static void AdvancedReceipt_Creationfor_India_SEZ_WPAY_InterState_NonGST()
			throws InterruptedException, AWTException, IOException {

		GST_Advanced_Received AR = new GST_Advanced_Received();
		AR.createAdvancedReceived_NIL_Rated_Exempted_Non_GST(driver, SEZ_WPAY, NonGST_Product_ID);

	}

}
