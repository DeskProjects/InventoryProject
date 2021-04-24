package krawler.erp.testCases.SmokeTestSuiteSG.B;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import krawler.erp.utils.MandotoryChecks;
import krawler.erp.utils.MasterConfiguration;
import krawler.erp.inventory.InvUtilities;
import krawler.erp.page.Login;
import krawler.erp.page.Utilities;

public class PrerequiristSettings extends BaseSetUp {

	@BeforeTest(groups = { "Setting" })
	public void MandotoryChecks() throws InterruptedException, IOException, AWTException {
		// Run > MandotoryChecks < for first time only
		MandotoryChecks.ERP_MandotoryChecks(driver);
		Utilities.refresh();
	}

	@Test(priority = 3, groups = { "Setting" })
	public void disable_dimension() throws InterruptedException, AWTException, IOException {
		MasterConfiguration.disabledim(driver);
	}
}
