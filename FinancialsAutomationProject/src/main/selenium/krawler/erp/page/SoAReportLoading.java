package krawler.erp.page;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SoAReportLoading {

	// ************************************* [Report Loading]
	// ********************************************

	public static void validate_ReportLoading(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_SoAReportLoading.properties");
			Utilities.waitandClick(pro.getProperty("ReportIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReportList"), driver);
			Thread.sleep(1500);

			Utilities.report_ScreenShot("Report Loading", driver);
			Utilities.enterTextInDropDown("statement of Accounts", pro.getProperty("quickSearch"), driver);
			Utilities.click_element(pro.getProperty("viewButton"), driver);

			System.out.println("***************** Test case [Report Loading] is PASS ******************* ");
			Utilities.click_element("//*[@id='as__tabreportperm']/a[1]", driver);
			Utilities.click_element("//*[@id='as__customerVendorLedger']/a[1]", driver);
		} catch (Exception EX) {
			System.out.println("***************** Test case [Report Loading] is FAILED ******************* ");
			Utilities.handleError(EX, driver);
		}
	}

	// ************************ common SOA
	// **************************************

	public static void validate_common_accStatement(String customerName, String vendorName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_SoAReportLoading.properties");

			WebDriverWait wait = new WebDriverWait(driver, 30);

			Utilities.HoverandClick(pro.getProperty("ReportIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("ReportList"), driver);
			Utilities.enterTextNormalBox("statement of Accounts", pro.getProperty("quickSearch"), driver);
			Thread.sleep(2000);
			Utilities.click_element(pro.getProperty("viewButton"), driver);

			// Opened
			Utilities.click_element(pro.getProperty("selectCustomerDrpDwnOk"), driver);
			Utilities.enterTextNormalBox(customerName, pro.getProperty("customerinputTextBox"), driver);
			Utilities.click_element("//*[@class='x-layer x-combo-list ' and contains(@style,'visible')]//*[text()='"
					+ customerName + "']", driver);
			Utilities.click_element(pro.getProperty("selectCustomerDrpDwnOk"), driver);

			WebElement strtDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("startDate"))));
			WebElement asDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("asOfDate"))));
			WebElement edDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("endDate"))));
			Thread.sleep(1000);

			strtDate.click();
			strtDate.sendKeys("01012018");
			Thread.sleep(1000);

			asDate.click();
			asDate.sendKeys("01012018");
			Thread.sleep(1000);

			edDate.click();
			Thread.sleep(1000);
			WebElement today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("selectToday"))));
			today.click();
			Thread.sleep(1000);

			Utilities.clickButton("Fetch", 1000, driver);
			Utilities.isLoadingDisappear(120, driver);

			WebElement resultShow = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("docCheck"))));
			if (resultShow.isDisplayed()) {
				Thread.sleep(1500);
				WebElement alArrow = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("allArrow"))));
				alArrow.click();
				Thread.sleep(1000);

				WebElement textAll = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AllText"))));
				textAll.click();
				Thread.sleep(3000);

				Utilities.report_ScreenShot("SOA - Customer Statement", driver);
				System.out.println("** CustomerAccount Statement Loaded Successfully **");
			} else {
				System.out.println("********* CustomerAccount Statement NOT Loaded !!!! ***************");
			}

			// switch 2 vendor statement TAB
			WebElement vendorTAB = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("vendorAccStatementTab"))));
			vendorTAB.click();

			Utilities.click_element(
					"//*[@id='vendorLedgerRpt']//*[@name='accountmulselectcombo']/following-sibling::span/img[2]",
					driver);
			Utilities.enterTextNormalBox(vendorName,
					"//*[@id='vendorLedgerRpt']//*[@name='accountmulselectcombo']/following-sibling::input", driver);
			Utilities.click_element("//*[@class='x-layer x-combo-list ' and contains(@style,'visible')]//*[text()='"
					+ vendorName + "']", driver);
			Utilities.click_element(
					"//*[@id='vendorLedgerRpt']//*[@name='accountmulselectcombo']/following-sibling::span/img[2]",
					driver);

			strtDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("startDate2"))));
			asDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("asOfDate2"))));
			edDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("endDate2"))));
			Thread.sleep(1000);

			strtDate.click();
			strtDate.sendKeys("01012018");
			Thread.sleep(1000);

			asDate.click();
			asDate.sendKeys("01012018");
			Thread.sleep(1000);

			edDate.click();
			Thread.sleep(1000);
			today = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("selectToday"))));
			today.click();
			Thread.sleep(1000);

			Utilities.HoverandClick("//*[@id='vendorLedgerRpt']//*[text()='Fetch']", driver);
			Utilities.isLoadingDisappear(120, driver);
			resultShow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("docCheck2"))));

			if (resultShow.isDisplayed()) {
				Thread.sleep(1500);
				WebElement alArrow2 = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("allArrow2"))));
				alArrow2.click();
				Thread.sleep(1000);

				WebElement textAll2 = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AllText"))));
				textAll2.click();
				Thread.sleep(3000);

				Utilities.report_ScreenShot("SOA - Vendor Statement", driver);
				System.out.println("**VendorAccount Statement Loaded Successfully ***");
			} else {
				System.out.println("********* VendorAccount Statement NOT Loaded !!!! ***************");
			}

			System.out.println("*********** Statement of Accounts test case Completed ******************");
			Utilities.click_element("//*[@id='as__tabreportperm']/a[1]", driver);
			Utilities.click_element("//*[@id='as__customerVendorLedger']/a[1]", driver);
		} catch (Exception EX) {
			System.out.println("***************** Test case [SOA Common Statement] is FAILED ******************* ");
			Utilities.handleError(EX, driver);
		}
	}

	// ************************ Customer Accout
	// **************************************

	public static void validate_customer_accStatement(String customerName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_SoAReportLoading.properties");

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement balSheetIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReportIcon"))));
			balSheetIcon.click();
			Thread.sleep(2000);

			WebElement rptList = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReportList"))));
			rptList.click();
			Thread.sleep(2000);

			WebElement quickSearch = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("quickSearch"))));
			quickSearch.sendKeys("SOA - Customer Account Statement");
			Thread.sleep(2000);

			WebElement viewBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("viewButton"))));
			viewBtn.click();
			Thread.sleep(2000);

			String drpDwnArrow = "//input[@id='accountmulselectcombo']/following-sibling::span/img[2]";
			String itemInputBoxLocator = "//input[@id='accountmulselectcombo']/following-sibling::input";
			String itemName = customerName;
			String rslTable = "//div[@class='x-layer x-combo-list ' and contains(@style,'visible;')]/div[1]/div[1]/table/tbody/tr/td[2]";
			Utilities.selectItemfromDropDown(drpDwnArrow, itemInputBoxLocator, itemName, rslTable, driver);

			WebElement strtDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("startDate"))));
			WebElement asDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("asOfDate"))));
			WebElement edDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("endDate"))));
			Thread.sleep(1000);

			strtDate.click();
			strtDate.sendKeys("01012018");
			Thread.sleep(1000);

			asDate.click();
			asDate.sendKeys("01012018");
			Thread.sleep(1000);

			edDate.click();
			Thread.sleep(1000);
			WebElement today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("selectToday"))));
			today.click();
			Thread.sleep(1000);

			Utilities.clickButton("Fetch", 1000, driver);
			Utilities.isLoadingDisappear(120, driver);

			WebElement resultShow = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("customerDocCheck"))));
			if (resultShow.isDisplayed()) {
				WebElement alArrow = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("cusallArrow"))));
				alArrow.click();
				Thread.sleep(1000);

				WebElement textAll = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AllText"))));
				textAll.click();
				Utilities.isLoadingDisappear(120, driver);

				// Report Email code
				String subjectLine = "CustomerAccount Statement - testsmoke - " + customerName;
				EmailFunctionality.email_fromReports(customerName, subjectLine, driver);
				Thread.sleep(2000);

				Utilities.report_ScreenShot("CustomerAccount Statement", driver);
				System.out.println("********* CustomerAccount Statement Loaded Successfully ***************");
			} else {
				System.out.println("********* CustomerAccount Statement NOT Loaded !!!! ***************");
			}

			Utilities.click_element("//*[@id='as__tabreportperm']/a[1]", driver);
		} catch (Exception EX) {
			System.out.println(
					"***************** Test case CustomerAccount Statement Loaded is FAILED ******************* ");
			Utilities.handleError(EX, driver);
		}
	}

	// ************************ Vendor Accout
	// **************************************

	public static void validate_vendor_accStatement(String vendorName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_SoAReportLoading.properties");

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement balSheetIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReportIcon"))));
			balSheetIcon.click();
			Thread.sleep(2000);

			WebElement rptList = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReportList"))));
			rptList.click();
			Thread.sleep(2000);

			WebElement quickSearch = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("quickSearch"))));
			quickSearch.sendKeys("SOA - Vendor Account Statement");
			Thread.sleep(2000);

			WebElement viewBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("viewButton"))));
			viewBtn.click();
			Thread.sleep(2000);

			String drpDwnArrow = "//input[@id='accountmulselectcombo']/following-sibling::span/img[2]";
			String itemInputBoxLocator = "//input[@id='accountmulselectcombo']/following-sibling::input";
			String itemName = vendorName;
			String rslTable = "//div[@class='x-layer x-combo-list ' and contains(@style,'visible;')]/div[1]/div[1]/table/tbody/tr/td[2]";
			Utilities.selectItemfromDropDown(drpDwnArrow, itemInputBoxLocator, itemName, rslTable, driver);

			WebElement strtDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("startDate"))));
			WebElement asDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("asOfDate"))));
			WebElement edDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("endDate"))));
			Thread.sleep(1000);

			strtDate.click();
			strtDate.sendKeys("01012018");
			Thread.sleep(1000);

			asDate.click();
			asDate.sendKeys("01012018");
			Thread.sleep(1000);

			edDate.click();
			Thread.sleep(1000);
			WebElement today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("selectToday"))));
			today.click();
			Thread.sleep(1000);

			Utilities.clickButton("Fetch", 1000, driver);
			Utilities.isLoadingDisappear(120, driver);

			WebElement resultShow = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("vendorDocCheck"))));

			if (resultShow.isDisplayed()) {
				Thread.sleep(1500);
				WebElement alArrow = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("venallArrow"))));
				alArrow.click();
				Thread.sleep(1000);

				WebElement textAll = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AllText"))));
				textAll.click();
				Utilities.isLoadingDisappear(120, driver);

				Utilities.report_ScreenShot("VendorAccount Statement", driver);
				System.out.println("********* VendorAccount Statement Loaded Successfully ***************");
			} else {
				System.out.println("********* VendorAccount Statement NOT Loaded !!!! ***************");
			}
			Utilities.click_element("//*[@id='as__tabreportperm']/a[1]", driver);

		} catch (Exception EX) {
			System.out.println(
					"***************** Test case VendorAccount Statement Loaded is FAILED ******************* ");
			Utilities.handleError(EX, driver);
		}
	}
}
