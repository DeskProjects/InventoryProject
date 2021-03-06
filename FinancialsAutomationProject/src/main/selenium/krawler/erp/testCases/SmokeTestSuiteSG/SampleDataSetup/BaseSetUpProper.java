package krawler.erp.testCases.SmokeTestSuiteSG.SampleDataSetup;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import krawler.erp.page.Login;
import krawler.erp.page.Utilities;
import krawler.erp.utils.MandotoryChecks;

public class BaseSetUpProper {

	public static WebDriver driver;

	public String purchaseAccount = "Purchases";
	public String salesAccount = "Sales";
	public String productid = "KeyBoard";

	@Parameters({ "server1", "username", "password" })
	@BeforeSuite(alwaysRun = true)
	public void OpenBrowser(String server1, String username, String password)
			throws InterruptedException, IOException, AWTException {
		driver = Login.loginERP(server1, username, password, 3);
		Utilities.isLoadingDisappear(120, driver);
		MandotoryChecks.ERP_MandotoryChecks(driver);
	}

	@BeforeMethod(alwaysRun = true)
	public void BeforeMethod() throws InterruptedException, AWTException, IOException {
		Utilities.disableExpander(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void AfterMethod() throws InterruptedException {
		driver.navigate().refresh();
		Utilities.isLoadingDisappear(120, driver);
		System.out.println("\n");
	}

}
