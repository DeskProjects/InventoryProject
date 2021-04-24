package krawler.erp.BankReconcilation;

import static org.testng.Assert.assertEquals;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.xpath.operations.Equals;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsByXPath;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.ExportFunction;
import krawler.erp.page.Utilities;
import krawler.erp.utils.SikulliUtil;

public class BankReconcilation {

	public static WebDriver open_reconcilation(WebDriver driver, String BankName) throws IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_BankReconciliation.properties");
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement GeneralLedgerCashBank = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("MainPanel"))));
			Thread.sleep(2000);

			GeneralLedgerCashBank.click();
			Thread.sleep(2000);

			WebElement EntryBR = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ExpandEntry"))));
			EntryBR.click();
			Thread.sleep(2000);

			WebElement ReportBR = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReportEntry"))));
			ReportBR.click();
			Thread.sleep(2000);

			WebElement BR = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("BankReconcilation"))));
			BR.click();
			Thread.sleep(2000);

			WebElement Bankname = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("BankName"))));
			Bankname.sendKeys(BankName);
			Thread.sleep(2000);

			Robot Enter = new Robot();
			Enter.keyPress(KeyEvent.VK_ENTER);
			Enter.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);

			WebElement StartDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("StartDate"))));
			StartDate.click();
			Thread.sleep(2000);

			Utilities.waitandClick(pro.getProperty("TodayStart"), driver);

			WebElement Statementdate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Statementdate"))));
			Statementdate.click();
			Thread.sleep(2000);

			WebElement Todaydate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("TodayDate"))));
			Todaydate.click();
			Thread.sleep(2000);

			WebElement Continuebutton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ContinueButton"))));
			Continuebutton.click();
			Thread.sleep(4000);

			System.out.println("Bank Reconcilation Tab is opened Sucessfully");

		} catch (Exception Ex) {
			System.out.println("Failed to open Bank Reconcilation");
			Ex.printStackTrace();
		}
		return driver;
	}

	/*************************** Export Test ************************/
	public static WebDriver Export_BR(WebDriver driver) {

		try {

			Properties pro = Utilities.fetchProValue("OR_BankReconciliation.properties");
			Properties pro2 = Utilities.fetchProValue("OR_ExportFunction.properties");
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement doccheckforChecksPayments = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("DocumentCheckforChecksPayments"))));

			if (doccheckforChecksPayments.isDisplayed()) {
				Thread.sleep(2000);
				// Utilities.report_ScreenShot("Bank Reconciliation Report",
				// driver);
				// System.out.println("**** Bank Reconciliation Report is LAODED
				// from ["+ StartDate +"] To ["+ Utilities.currentDate() +"]
				// **** !!!!!! ");

				// Export Excel file
				Utilities.clickButton("Export to XLS File", 500, driver);
				Utilities.HoverandClick(pro2.getProperty("WindowExportBtn"), driver);
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "XLStype");
				Thread.sleep(2000);
				Utilities.Enter();
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
				Thread.sleep(500);
				System.out.println("* * * * * * EXPORT for [.Excel] completed successfully * * * * * * *");

				// Export PDF files
				String XpathOfExportPDF = "//div[@id='reconciliationledger']/div/div/div/div/div[3]//td[7]//button[text()='Export PDF']";

				Utilities.HoverandClick(XpathOfExportPDF, driver);
				List<WebElement> typesOfPdf = driver.findElements(
						By.xpath("//div[@class='x-layer x-menu' and contains(@style,'visible')]/ul/li/a"));
				Utilities.HoverandClick(XpathOfExportPDF, driver);

				String parentWindow = driver.getWindowHandle();

				for (int i = 1; i <= typesOfPdf.size(); i++) {
					Utilities.HoverandClick(XpathOfExportPDF, driver);
					WebElement pdf = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//div[@class='x-layer x-menu' and contains(@style,'visible')]/ul/li[" + i + "]/a")));
					pdf.click();
					Utilities.sikuliHandleExport(ExportFunction.button_path("PDFtype"));
					Thread.sleep(1500);

					Set s = driver.getWindowHandles(); // this method will gives
														// you the handles of
														// all opened windows
					Iterator ite = s.iterator();
					while (ite.hasNext()) {
						String childWindow = ite.next().toString();
						if (!childWindow.contains(parentWindow)) {
							driver.switchTo().window(childWindow);
							Thread.sleep(1000);
							System.out.println("* * * * * * EXPORT for [PDF] completed successfully * * * * * * *");
							driver.close();
							driver.switchTo().window(parentWindow);
							Thread.sleep(1000);
						}
					}
					Utilities.sikuliHandleExport(ExportFunction.button_path("BankReco"));
				}
				System.out.println("* * * * * * EXPORT for all [PDF] completed successfully * * * * * * *");
				System.out.println(
						"* * * * * * EXPORT for [Bank Reconciliation Report] completed successfully * * * * * * *");
			}

		} catch (Exception Ex) {
			Ex.printStackTrace();
		}

		return driver;
	}

	public static WebDriver Bank_reconcilation(WebDriver driver, String documentID) throws IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_BankReconciliation.properties");
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement DepositsAndOtherCredits = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DepositsandOtherCredits"))));
			DepositsAndOtherCredits.click();
			Thread.sleep(2000);

			WebElement CheckAndPayment = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CheckandPayment"))));
			CheckAndPayment.click();
			Thread.sleep(2000);

			WebElement ReconcileAndSave = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReconcileButton"))));
			ReconcileAndSave.click();
			Thread.sleep(2000);

			WebElement ReconcilationDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReconcileDate"))));
			ReconcilationDate.click();
			Thread.sleep(2000);

			WebElement TodayReconcileS = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("TodayReconcile"))));
			TodayReconcileS.click();
			Thread.sleep(2000);

			WebElement ReconcilationSeqFormat = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReconcilationSeqFormat"))));
			ReconcilationSeqFormat.clear();
			ReconcilationSeqFormat.sendKeys("NA");
			Thread.sleep(1000);
			Utilities.Enter();

			WebElement RecocileNo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("RecocileNo"))));
			RecocileNo.sendKeys("RE" + documentID);
			Thread.sleep(2000);

			Utilities.clickButton("Save", 1000, driver);
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 1000, driver);
			Thread.sleep(2000);

			Utilities.clickButton("OK", 1000, driver);
			Thread.sleep(2000);

			System.out.println("Record are UnReconciled Sucessfully");

		} catch (Exception EX) {
			System.out.println("Failed to UnReconciled Record ");
			EX.printStackTrace();
		}
		return driver;

	}

	/************************************ Verify_BankR ***************************************/

	public static WebDriver Verify_BR(WebDriver driver, String documentID, String BankName) {
		try {
			String documentiD = "RE" + documentID;
			Properties pro = Utilities.fetchProValue("OR_BankReconciliation.properties");

			WebDriverWait wait = new WebDriverWait(driver, 30);

			Utilities.clickButton("Reconciled Details", 1000, driver);

			WebElement FromDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("FromDate"))));
			FromDate.click();
			Thread.sleep(2000);

			WebElement SelectFromDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SelectFromDate"))));
			SelectFromDate.click();
			Thread.sleep(2000);

			WebElement ToDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ToDate"))));
			ToDate.click();
			Thread.sleep(2000);

			WebElement SelectToDate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SelectToDate"))));
			SelectToDate.click();
			Thread.sleep(2000);

			WebElement FetchButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("FetchButton"))));
			FetchButton.click();
			Thread.sleep(2000);

			int e = driver
					.findElements(By
							.xpath("//div[@id='tabdashboard']/following-sibling::div[2]//*[@class='x-grid3-hd-row']/td"))
					.size();
			Thread.sleep(2000);
			// System.out.println(e);

			for (int i = 1; i < e + 1; i++) {
				{
					// System.out.println("driver.findElement(By.xpath(pro.getProperty('Verificationheader'))).getText();");
					String header = driver
							.findElement(By.xpath(
									"//*[text()='Action']/ancestor::div[3]/div/table/thead/tr/td[" + i + "]/div"))
							.getText();
					// System.out.println(header);

					if (header.equals("Reconcile No.")) {
						assertEquals(documentiD,
								driver.findElement(By.xpath(
										"//*[@class='x-grid3-row-expander']//ancestor::tbody/tr/td[" + i + "]/div"))
										.getText());
						// System.out.println("d1");
					}

					else if (header.equals("Account")) {
						assertEquals(BankName,
								driver.findElement(By.xpath(
										"//*[@class='x-grid3-row-expander']//ancestor::tbody/tr/td[" + i + "]/div"))
										.getText());
					}
				}
			}

			WebElement CheckBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//div[@id='reconciliationledger']/following::div[1]/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td[1]")));
			if (CheckBox.isDisplayed()) {
				Thread.sleep(2000);
				Utilities.waitandClick(pro.getProperty("ExportXLS"), driver);
				Thread.sleep(1000);
				Utilities.HoverandClick(pro.getProperty("Exportbutton"), driver);
				Thread.sleep(2000);
				SikulliUtil.sikulli_waitClick(driver, "XLStype");
				Thread.sleep(2000);
				Utilities.Enter();
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
			}

			Utilities.waitandClick(pro.getProperty("CloseReport"), driver);
			System.out.println("Reconciled Record are Sucessfully Verified");

		} catch (Exception EX) {
			System.out.println("Failed to verify Reconciled Record ");
			EX.printStackTrace();
		}

		return driver;
	}

	/********************* UnReconcile record **********************/

	public static WebDriver Bank_Unrecocilation(WebDriver driver, String documentID) {
		try {
			Properties pro = Utilities.fetchProValue("OR_BankReconciliation.properties");

			Utilities.clickButton("View Reconciled Report", 2000, driver);
			Thread.sleep(2000);
			Utilities.waitandClick(pro.getProperty("UnDepositsandOtherCredits"), driver);
			Utilities.waitandClick(pro.getProperty("UnCheckandPayment"), driver);
			Utilities.clickButton("Unreconcile and Save", 2000, driver);
			Utilities.waitandClick(pro.getProperty("UnreconciliationDate"), driver);
			Utilities.waitandClick(pro.getProperty("Tommorrowdate"), driver);
			Utilities.waitandSendkey(pro.getProperty("UnreconcileDateFormat"), driver, "NA");
			Utilities.waitandSendkey(pro.getProperty("UnreconcileNO"), driver, "UNRE" + documentID);
			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.waitandClick(pro.getProperty("CloseUNRE"), driver);

			System.out.println("Record are UnReconciled Sucessfully");
		} catch (Exception EX) {
			System.out.println("Failed to UnReconciled Record");
			EX.printStackTrace();
		}

		return driver;
	}

	/************************** Export of UN reconciliation ******************/

	public static WebDriver Export_BankUnrecocilation(WebDriver driver, String documentID) {
		try {
			Properties pro = Utilities.fetchProValue("OR_BankReconciliation.properties");
			Properties pro2 = Utilities.fetchProValue("OR_ExportFunction.properties");

			WebDriverWait wait = new WebDriverWait(driver, 30);
			Utilities.clickButton("View Reconciled Report", 2000, driver);
			Thread.sleep(2000);

			WebElement doccheckforChecksPayments2 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("DocumentCheckforChecksPayments2"))));

			if (doccheckforChecksPayments2.isDisplayed()) {
				Thread.sleep(2000);
				// Utilities.report_ScreenShot("Bank Reconciled Report",
				// driver);
				// System.out.println("**** Bank Reconciled Report is LAODED
				// from ["+ StartDate +"] To ["+ Utilities.currentDate() +"]
				// **** !!!!!! ");

				String XpathOfExcel = "//div[@id='reconciliationledgerReport']/div/div/div/div/div[3]//td[10]//button[text()='Export to XLS File']";
				Utilities.HoverandClick(XpathOfExcel, driver);
				Utilities.HoverandClick(pro2.getProperty("WindowExportBtn"), driver);
				Thread.sleep(1000);

				SikulliUtil.sikulli_waitClick(driver, "XLStype");
				Thread.sleep(2000);
				Utilities.Enter();
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
				Thread.sleep(500);
				System.out.println(
						"* * * * * * EXPORT for [Bank Reconciled Report .Excel] completed successfully * * * * * * *");

				// pdf
				String parentWindow = driver.getWindowHandle();
				String xpathPdf = "//div[@id='reconciliationledgerReport']/div/div/div/div/div[3]//td[7]";
				Utilities.HoverandClick(xpathPdf, driver);
				Utilities.HoverandClick(pro.getProperty("PDFwithMemoDetails"), driver);
				Thread.sleep(5000);
				Utilities.sikuliHandleExport(ExportFunction.button_path("PDFtype"));
				Thread.sleep(1500);

				Set s = driver.getWindowHandles(); // this method will gives you
													// the handles of all opened
													// windows
				Iterator ite = s.iterator();

				while (ite.hasNext()) {
					String childWindow = ite.next().toString();
					if (!childWindow.contains(parentWindow)) {
						driver.switchTo().window(childWindow);
						Thread.sleep(1000);
						System.out.println(
								"* * * * * * EXPORT for [Bank Reconciled Report PDF] completed successfully * * * * * * *");
						driver.close();
						driver.switchTo().window(parentWindow);
						Thread.sleep(1000);
					}
				}

			}

			Utilities.waitandClick(pro.getProperty("CloseUNRE"), driver);
		}

		catch (Exception loadingTimout) {
			System.out.println("**** Bank Reconciled Report is NOT LAODED (wait > 60 sec) **** ");
			loadingTimout.printStackTrace();
		}

		return driver;

	}

	/************************** Verification ******************/
	public static WebDriver Verify_Bank_Unrecocilation(WebDriver driver, String documentID, String BankName) {
		try {
			String documentiD = "UNRE" + documentID;
			Properties pro = Utilities.fetchProValue("OR_BankReconciliation.properties");

			Utilities.clickButton("Reconciled Details", 1000, driver);

			Utilities.waitandClick(pro.getProperty("FromDate"), driver);
			Utilities.waitandClick(pro.getProperty("TodayDateF"), driver);
			Thread.sleep(1000);
			Utilities.waitandClick(pro.getProperty("FromDate"), driver);
			Utilities.waitandClick(pro.getProperty("Tommorrowdate"), driver);

			Utilities.waitandClick(pro.getProperty("ToDate"), driver);
			Utilities.waitandClick(pro.getProperty("TodayDateF"), driver);
			Thread.sleep(1000);
			Utilities.waitandClick(pro.getProperty("ToDate"), driver);
			Utilities.waitandClick(pro.getProperty("Tommorrowdate"), driver);
			Utilities.waitandClick(pro.getProperty("FetchButton"), driver);

			int e = driver
					.findElements(By
							.xpath("//div[@id='tabdashboard']/following-sibling::div[2]//*[@class='x-grid3-hd-row']/td"))
					.size();
			Thread.sleep(2000);
			// System.out.println(e);

			for (int i = 1; i < e + 1; i++) {
				{
					// System.out.println("driver.findElement(By.xpath(pro.getProperty('Verificationheader'))).getText();");
					String header = driver
							.findElement(By.xpath(
									"//*[text()='Action']/ancestor::div[3]/div/table/thead/tr/td[" + i + "]/div"))
							.getText();
					// System.out.println(header);

					if (header.equals("Reconcile No.")) {
						assertEquals(documentiD,
								driver.findElement(By.xpath(
										"//*[@class='x-grid3-row-expander']//ancestor::tbody/tr/td[" + i + "]/div"))
										.getText());
						// System.out.println("d1");
					}

					else if (header.equals("Account")) {
						assertEquals(BankName,
								driver.findElement(By.xpath(
										"//*[@class='x-grid3-row-expander']//ancestor::tbody/tr/td[" + i + "]/div"))
										.getText());
					}

				}

			}

			WebDriverWait wait = new WebDriverWait(driver, 500);
			WebElement CheckBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//div[@id='reconciliationledger']/following::div[1]/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td[1]")));
			if (CheckBox.isDisplayed()) {
				Thread.sleep(2000);
				Utilities.waitandClick(pro.getProperty("ExportXLS"), driver);
				Thread.sleep(1000);
				Utilities.HoverandClick(pro.getProperty("Exportbutton"), driver);
				Thread.sleep(2000);
				SikulliUtil.sikulli_waitClick(driver, "XLStype");
				Thread.sleep(2000);
				Utilities.Enter();
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
			}

			Utilities.waitandClick(pro.getProperty("CloseReport"), driver);
			System.out.println("UnReconciled Record are Sucessfully Verified");
		} catch (Exception EX) {
			System.out.println("Failed to verify UnReconciled Record");
			EX.printStackTrace();
		}

		return driver;
	}
}
