package krawler.erp.testCases.SmokeTestSuiteSG.B;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import krawler.erp.page.Login;
import krawler.erp.page.Utilities;
import krawler.erp.utils.MandotoryChecks;

public class BaseSetUp {

	public static WebDriver driver;
	public static String customerid = "cus05Nov";
	public static String documentid = "doc05Nov";
	public static String vendorid = "ved05Nov";
	public static String productid = "prd05Nov";
	public String payMethod = "PayM" + documentid;

	// here add date format for EXPORT test case
	public String dateType1 = "05-11-2018";
	public String dateFormat2 = "2018-11-08";

	@Parameters({ "server1", "username", "password" })
	@BeforeSuite(alwaysRun = true)
	public void OpenBrowser(String server1, String username, String password)
			throws InterruptedException, IOException, AWTException {
		driver = Login.loginERP(server1, username, password, 5);
	}

	@BeforeMethod(alwaysRun = true)
	public void closeExpander() throws InterruptedException, AWTException, IOException {
		Utilities.disableExpander(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() throws AWTException, InterruptedException {
		System.out.println("\n");
		Thread.sleep(300);
	}

}
