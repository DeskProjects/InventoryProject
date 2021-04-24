package krawler.erp.tc.performance;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import krawler.erp.PerformanceTestSuite.PerformanceTest;
import krawler.erp.page.Login;
import krawler.erp.page.Utilities;

public class Performancetest {
	public static WebDriver driver;
	// public static String server1 =
	// "http://192.168.0.208:8080/dotcomsmoketesting/a/fujisigncrafts/";
	// public static String username = "admin";
	// public static String Password = "1234";
	public static String Fromdate = "01012017";
	public static String ASofdate = "21092018";
	public static String Todate = "21092018";

	@Parameters({ "server1", "username", "password" })
	@BeforeTest
	public void beforeTestLogin(String server1, String username, String Password)
			throws InterruptedException, IOException {
		Login ln = new Login();
		WebDriver driver = Login.loginERP(server1, username, Password, 10);
		this.driver = driver;
		Utilities.clickExpander(driver);
	}

	@Test(priority = 1)
	public void openBS() {
		PerformanceTest.BalanceSheetper(driver);
	}
	/*
	 * @Test(priority =2) public void openPnL(){
	 * PerformanceTest.loadPnlreport(driver); }
	 * 
	 * @Test(priority =3) public void openGL(){
	 * PerformanceTest.loadGLreport(driver); }
	 * 
	 * @Test(priority =4) public void openTB(){
	 * PerformanceTest.loadTrailBalancereport(driver); }
	 * 
	 * 
	 * 
	 * @Test(priority =9) public void openJournalEntryreport() throws
	 * IOException, InterruptedException{
	 * PerformanceTest.JournalEntryreport(driver); }
	 * 
	 * @Test(priority =10) public void openPurchaseequisitionreport() throws
	 * IOException, InterruptedException{
	 * PerformanceTest.Purchaseequisitionreport(driver); }
	 * 
	 * @Test(priority =11) public void openVendorQuotationreport() throws
	 * IOException, InterruptedException{
	 * PerformanceTest.VendorQuotationreport(driver); }
	 * 
	 * @Test(priority =12) public void openPurchaseOrderreport() throws
	 * IOException, InterruptedException{
	 * PerformanceTest.PurchaseOrderreport(driver); }
	 * 
	 * @Test(priority =12) public void openPI_CPreport() throws IOException,
	 * InterruptedException{ PerformanceTest.PI_CPreport(driver); }
	 * 
	 * @Test(priority =13) public void openGoodReceipt() throws IOException,
	 * InterruptedException{ PerformanceTest.GoodReceipt(driver); }
	 * 
	 * @Test(priority =14) public void openMakePayment() throws IOException,
	 * InterruptedException{ PerformanceTest.MakePayment(driver); }
	 * 
	 * @Test(priority =15) public void openDebitNote() throws IOException,
	 * InterruptedException{ PerformanceTest.DebitNote(driver); }
	 * 
	 * @Test(priority =16) public void openPurchaseReturn() throws IOException,
	 * InterruptedException{ PerformanceTest.PurchaseReturn(driver); }
	 * 
	 * @Test(priority =17) public void openCustomerQuotation() throws
	 * IOException, InterruptedException{
	 * PerformanceTest.CustomerQuotation(driver); }
	 * 
	 * @Test(priority =18) public void openSalesOrder() throws IOException,
	 * InterruptedException{ PerformanceTest.SalesOrder(driver); }
	 * 
	 * @Test(priority =19) public void openSI_CS() throws IOException,
	 * InterruptedException{ PerformanceTest.SI_CS(driver); }
	 * 
	 * @Test(priority =20) public void openDeliveryOrder() throws IOException,
	 * InterruptedException{ PerformanceTest.DeliveryOrder(driver); }
	 * 
	 * @Test(priority =21) public void openReceivePayment() throws IOException,
	 * InterruptedException{ PerformanceTest.ReceivePayment(driver); }
	 * 
	 * @Test(priority =22) public void openCreditNote() throws IOException,
	 * InterruptedException{ PerformanceTest.CreditNote(driver); }
	 * 
	 * @Test(priority =23) public void openSalesReturn() throws IOException,
	 * InterruptedException{ PerformanceTest.SalesReturn(driver); }
	 * 
	 * 
	 * @Test(priority =24) public void openSOACustomer() throws IOException,
	 * InterruptedException{ PerformanceTest.SOACustomer(driver, Fromdate,
	 * ASofdate,Todate ); }
	 * 
	 * @Test(priority =25) public void openSOAVendor() throws IOException,
	 * InterruptedException{ PerformanceTest.SOAVendor(driver, Fromdate,
	 * ASofdate,Todate ); }
	 * 
	 * @Test(priority =26) public void openAgedpayablesSummaryreport(){
	 * PerformanceTest.AgedpayablesSummaryreport(driver); }
	 * 
	 * @Test(priority =27) public void openAgedpayablesReportViewreport() throws
	 * IOException, InterruptedException{
	 * PerformanceTest.AgedpayablesReportViewreport(driver); }
	 * 
	 * @Test(priority =28) public void openAgedReceivablesSummaryreport() throws
	 * IOException, InterruptedException{
	 * PerformanceTest.AgedReceivablesSummaryreport(driver); }
	 * 
	 * @Test(priority =29) public void openAgedReceivablesReportViewreport()
	 * throws IOException, InterruptedException{
	 * PerformanceTest.AgedReceivablesReportViewreport(driver); }
	 */
}
