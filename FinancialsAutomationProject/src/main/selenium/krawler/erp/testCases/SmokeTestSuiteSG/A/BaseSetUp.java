package krawler.erp.testCases.SmokeTestSuiteSG.A;

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
	public static String customerid = "cus11Feb";
	public static String documentid = "doc11Feb";
	public static String vendorid = "ved11Feb";
	public static String productid = "prd11Feb";
	public String payMethod = "PayM" + documentid;

	public static String emailAddress = "ramesh.madane@deskera.org";

	@Parameters({ "server1", "username", "password" })
	@BeforeSuite(alwaysRun = true)
	public void OpenBrowser(String server1, String username, String password)
			throws InterruptedException, IOException, AWTException {
		driver = Login.loginERP(server1, username, password, 3);
		Utilities.isLoadingDisappear(120, driver);
	}

	@BeforeMethod(alwaysRun = true)
	public void closeExpander() throws InterruptedException, AWTException, IOException {
		Utilities.disableExpander(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() throws AWTException, InterruptedException {
		System.out.println("\n");
	}

}
