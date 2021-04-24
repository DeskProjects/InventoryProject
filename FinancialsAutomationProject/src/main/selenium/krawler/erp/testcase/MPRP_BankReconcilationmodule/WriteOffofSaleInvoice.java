package krawler.erp.testcase.MPRP_BankReconcilationmodule;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import krawler.erp.MP_RPModule.RPDocumentCreationForAmountDue;
import krawler.erp.MP_RPModule.WriteOffFuncationalityForSaleInvoice_AdvanceRP;
import krawler.erp.page.Login;
import krawler.erp.page.Utilities;

public class WriteOffofSaleInvoice {

	public static WebDriver driver;
	public static String server1 = "http://192.168.0.208:8080/dotcomsmoketestingv21/a/rahulv21/#";
	public static String username = "admin";
	public static String password = "1234";
	public static String documentid = "RV07";
	public static String documentId = documentid;
	public static String productid = "Test Product";
	public static String customerid = "SumitTest";

	@BeforeTest
	public void beforeTestLogin() throws InterruptedException, IOException {
		Login ln = new Login();
		WebDriver driver = Login.loginERP(server1, username, password, 10);
		this.driver = driver;
		Utilities.clickExpander(driver);
	}

	@Test(priority = 1)
	public void CreateCOA() throws InterruptedException, AWTException, IOException {
		WriteOffFuncationalityForSaleInvoice_AdvanceRP.CreateCOA(driver, "SIAccount" + documentid, "Expense",
				"Profit & Loss");
	}

	@Test(priority = 2)
	public void MaPAccount() throws InterruptedException, AWTException, IOException {
		WriteOffFuncationalityForSaleInvoice_AdvanceRP.mapAccount(driver, "SIAccount" + documentid);

	}

	@Test(priority = 3)
	public void CreateSI() throws InterruptedException, AWTException, IOException {
		WriteOffFuncationalityForSaleInvoice_AdvanceRP.create_salesInvoice(documentId, productid, customerid, driver);
	}

	@Test(priority = 4)
	public void WriteOffSIandVerification() throws InterruptedException, AWTException, IOException {
		WriteOffFuncationalityForSaleInvoice_AdvanceRP.writeoffSI(driver, "SI" + documentid, "SIAccount" + documentid);
	}

	@Test(priority = 5)
	public void RevertSIwriteOff() throws InterruptedException, AWTException, IOException {

		WriteOffFuncationalityForSaleInvoice_AdvanceRP.RevertWriteOffSaleInvoice(driver, "SI" + documentid,
				"SIAccount" + documentid);
		Utilities.refresh();
		Utilities.clickExpander(driver);
	}

	@Test(priority = 6)
	public void CreateCOA2() throws InterruptedException, AWTException, IOException {
		WriteOffFuncationalityForSaleInvoice_AdvanceRP.CreateCOA(driver, "RPAccount" + documentid, "Expense",
				"Profit & Loss");
	}

	@Test(priority = 7)
	public void MaPAccountRP() throws InterruptedException, AWTException, IOException {
		WriteOffFuncationalityForSaleInvoice_AdvanceRP.mapAccountRP(driver, "RPAccount" + documentid);

	}

	@Test(priority = 8)
	public void CreateRP() throws InterruptedException, AWTException, IOException {
		RPDocumentCreationForAmountDue.RP_Advance(driver, documentId + "WF", customerid, "100");
	}

	@Test(priority = 9)
	public void WriteOffRPandVerification() throws InterruptedException, AWTException, IOException {
		WriteOffFuncationalityForSaleInvoice_AdvanceRP.writeoffRP(driver, "RPAD" + documentId + "WF",
				"RPAccount" + documentid);
	}

	@Test(priority = 10)
	public void RevertRPwriteOff() throws InterruptedException, AWTException, IOException {

		WriteOffFuncationalityForSaleInvoice_AdvanceRP.RevertWriteOffRPAD(driver, "RPAD" + documentId + "WF",
				"RPAccount" + documentid);
		Utilities.refresh();
	}

	@AfterTest
	public void close() {
		driver.close();
	}
}
