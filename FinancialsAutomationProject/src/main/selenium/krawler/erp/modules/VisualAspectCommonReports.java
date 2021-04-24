package krawler.erp.modules;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import krawler.erp.page.Utilities;
import krawler.erp.testCases.VisualAspect.PurchaseModule;
import krawler.erp.utils.MasterConfiguration;

public class VisualAspectCommonReports {
	// ---------------------------------------- G L Report
	// -----------------------------------------------------------------------------
	public static void verify_GeneralLedger(String documentid, String moduleName, String JEnumber,
			String GLdebitAccounts[], String GLexpectedDebitAmt[], String GLcreditAccounts[],
			String GLexpectedcreditAmt[], WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_GeneralLedgerLoading.properties");
			Utilities.waitandClick(pro.getProperty("LedgerIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("fromDate"), driver);
			Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Today']", driver);
			// Utilities.HoverandClick(pro.getProperty("toDate"), driver);
			// Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Today']",
			// driver);

			boolean flag = true;
			ArrayList actualGLdebit = new ArrayList();
			for (int i = 0; i < GLdebitAccounts.length; i++) {
				String GLdebitamount = null;
				Utilities.click_element(pro.getProperty("GLdrpdwnOK"), driver);
				Utilities.enterTextandSelect(GLdebitAccounts[i], pro.getProperty("searchAccount"), driver);
				Utilities.click_element(pro.getProperty("GLdrpdwnOK"), driver);
				Utilities.clickButton("Fetch", 500, driver);

				MasterConfiguration.isExpanded(pro.getProperty("GLcollapsed"), pro.getProperty("GLExpanded"), driver);
				GLdebitamount = driver
						.findElement(By.xpath("//*[contains(text(),'" + JEnumber + "')]/following::span[3]/div"))
						.getText();
				Thread.sleep(1100);
				actualGLdebit.add(GLdebitamount);
				if (actualGLdebit.get(i).equals(GLexpectedDebitAmt[i])) {
					System.out.println("*** In GL report verified Debit account [" + GLdebitAccounts[i]
							+ "] with UI amount [" + actualGLdebit.get(i) + "] & expected amount ["
							+ GLexpectedDebitAmt[i] + "] ****");
				} else {
					System.out.println("*** In GL report NOT VERIFIED Debit account [" + GLdebitAccounts[i]
							+ "] with UI amount [" + actualGLdebit.get(i) + "] & expected amount ["
							+ GLexpectedDebitAmt[i] + "] ****");
					flag = false;
				}
				Utilities.click_element(pro.getProperty("GLdrpdwnCancel"), driver);
			}

			ArrayList actualGLcredit = new ArrayList();
			for (int i = 0; i < GLcreditAccounts.length; i++) {
				String GLcreditamount = null;
				Utilities.click_element(pro.getProperty("GLdrpdwnOK"), driver);
				Utilities.enterTextandSelect(GLcreditAccounts[i], pro.getProperty("searchAccount"), driver);
				Utilities.click_element(pro.getProperty("GLdrpdwnOK"), driver);
				Utilities.clickButton("Fetch", 500, driver);

				MasterConfiguration.isExpanded(pro.getProperty("GLcollapsed"), pro.getProperty("GLExpanded"), driver);
				GLcreditamount = driver
						.findElement(By.xpath("//*[contains(text(),'" + JEnumber + "')]/following::span[5]/div"))
						.getText();
				Thread.sleep(1100);
				actualGLcredit.add(GLcreditamount);
				if (actualGLcredit.get(i).equals(GLexpectedcreditAmt[i])) {
					System.out.println("*** In GL report verified credit account [" + GLcreditAccounts[i]
							+ "] with UI amount [" + actualGLcredit.get(i) + "] & expected amount ["
							+ GLexpectedcreditAmt[i] + "] ****");
				} else {
					System.out.println("*** In GL report NOT VERIFIED credit account [" + GLcreditAccounts[i]
							+ "] with UI amount [" + actualGLcredit.get(i) + "] & expected amount ["
							+ GLexpectedcreditAmt[i] + "] ****");
					flag = false;
				}
				Utilities.click_element(pro.getProperty("GLdrpdwnCancel"), driver);
			}

			if (flag == false) {
				System.out.println("!!!!!!! GL validation is FAILED for CP/PI with JE : [" + JEnumber
						+ "] plz check Console !!!!!!! ");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			if (flag == true) {
				System.out.println(">>>>>>> Verified [" + moduleName + "] General Ledger Report for document number : ["
						+ documentid + "] Successfully <<<<<<<<<<<<");
				Utilities.HoverandClick(pro.getProperty("Closereport"), driver); // closeForm
			}
		} catch (Exception Ex) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!! FAILED TO VERIFY [" + moduleName
					+ "] General Ledger Report for document number : [" + documentid
					+ "] plz Check Console !!!!!!!!!!!!!!!!!!!!!!!!!!");
			Utilities.handleError(Ex, driver);
		}
	}

	// ---------------------------------------- Trial Balance Report
	// -----------------------------------------------------------------------------
	public static void verify_TrialBalance(String documentid, String ModuleName, String array[], WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_GeneralLedgerLoading.properties");
			Utilities.waitandClick(pro.getProperty("TrialBalanceIcon"), driver);
			Utilities.disableExpander(driver);

			Utilities.HoverandClick(pro.getProperty("fromDate"), driver);
			Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Today']", driver);
			// Utilities.HoverandClick(pro.getProperty("toDate"), driver);
			// Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Today']",
			// driver);

			int headerSize = Utilities.totalSize("//*[text()='Account']/ancestor::tr/td/div", driver);
			int indOpeningDebit = 0, indPeriodDebit = 0, indEndDebit = 0;
			int indOpeningCredit = 0, indPeriodCredit = 0, indEndCredit = 0;
			boolean flag = true;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver.findElement(By.xpath("//*[text()='Account']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Opening Amount Debit (SG Dollar (SGD))")) {
					indOpeningDebit = i;
				}
				if (headerName.equalsIgnoreCase("Period Amount Debit (SG Dollar (SGD))")) {
					indPeriodDebit = i;
				}
				if (headerName.equalsIgnoreCase("Ending Amount Debit (SG Dollar (SGD))")) {
					indEndDebit = i;
				}
				if (headerName.equalsIgnoreCase("Opening Amount Credit (SG Dollar (SGD))")) {
					indOpeningCredit = i;
				}
				if (headerName.equalsIgnoreCase("Period Amount Credit (SG Dollar (SGD))")) {
					indPeriodCredit = i;
				}
				if (headerName.equalsIgnoreCase("Ending Amount Credit (SG Dollar (SGD))")) {
					indEndCredit = i;
				}
			}
			// Select Account in Trial Balance account
			// ---------------------------------------------
			for (int i = 0; i < array.length; i++) {
				Utilities.HoverandClick(pro.getProperty("dropdownOKcheck"), driver);
				Utilities.enterTextandSelect(array[i], pro.getProperty("TBsearchAccount"), driver);
				Utilities.HoverandClick(pro.getProperty("dropdownOKcheck"), driver);
				Utilities.HoverandClick(pro.getProperty("TrialBalFetchBtn"), driver);
				Utilities.isLoadingDisappear(100, driver);

				String TrialEndingAmt = null;
				String TrialPeriodAmt = null;
				// Debit Total - Trial Balance Details
				String TrialOpnDebitBal = driver.findElement(By.xpath("//*[text()='" + array[i]
						+ "' and @class='jumplink']/ancestor::tr/td[" + indOpeningDebit + "]/div")).getText();
				String TrialPerDebitBal = driver.findElement(By.xpath("//*[text()='" + array[i]
						+ "' and @class='jumplink']/ancestor::tr/td[" + indPeriodDebit + "]/div")).getText();
				String TrialEndDebitBal = driver.findElement(By.xpath("//*[text()='" + array[i]
						+ "' and @class='jumplink']/ancestor::tr/td[" + indEndDebit + "]/div")).getText();
				// Credit Total - Trial Balance Details
				String TrialOpnCreditBal = driver.findElement(By.xpath("//*[text()='" + array[i]
						+ "' and @class='jumplink']/ancestor::tr/td[" + indOpeningCredit + "]/div")).getText();
				String TrialPerCreditBal = driver.findElement(By.xpath("//*[text()='" + array[i]
						+ "' and @class='jumplink']/ancestor::tr/td[" + indPeriodCredit + "]/div")).getText();
				String TrialEndCreditBal = driver.findElement(By.xpath("//*[text()='" + array[i]
						+ "' and @class='jumplink']/ancestor::tr/td[" + indEndCredit + "]/div")).getText();
				// System.out.println(TrialOpnDebitBal+TrialPerDebitBal+TrialEndDebitBal+TrialOpnCreditBal+TrialPerCreditBal+TrialEndCreditBal);
				// Convert String valur into int/double
				double OpnDebitAmt = Utilities.getIntegerFrmString(TrialOpnDebitBal);
				double PerDebitAmt = Utilities.getIntegerFrmString(TrialPerDebitBal);
				double EndDebitAmt = Utilities.getIntegerFrmString(TrialEndDebitBal);
				double OpnCreditAmt = Utilities.getIntegerFrmString(TrialOpnCreditBal);
				double PerCreditAmt = Utilities.getIntegerFrmString(TrialPerCreditBal);
				double EndCreditAmt = Utilities.getIntegerFrmString(TrialEndCreditBal);

				double totalDebitAmt = OpnDebitAmt + PerDebitAmt;
				double totalCreditAmt = OpnCreditAmt + PerCreditAmt;
				// System.out.println(totalDebitAmt);
				// System.out.println(totalCreditAmt);

				// ***** Final End Amount ******
				if (totalDebitAmt > totalCreditAmt) {
					// System.out.println("debit !!!");
					TrialEndingAmt = TrialEndDebitBal;
				} else if (totalCreditAmt > totalDebitAmt) {
					// System.out.println("credit !!!");
					TrialEndingAmt = TrialEndCreditBal;
				} else {
					System.out.println("Nahh Nothing Match !!!");
					flag = false;
				}

				// ***** Final Period Amount ******
				if (PerDebitAmt > PerCreditAmt) {
					TrialPeriodAmt = TrialPerDebitBal;
				} else {
					TrialPeriodAmt = TrialPerCreditBal;
				}

				// SWITCH TO GL TAB
				// --------------------------------------------------------------->
				// > >
				Utilities.HoverandClick("//*[text()='" + array[i] + "' and @class='jumplink']", driver); // Switch
																											// by
																											// Using
																											// 1st
																											// account
				Utilities.isLoadingDisappear(120, driver);
				// GL Details
				String GLperiodAmt = driver
						.findElement(By
								.xpath("//*[text()='Particulars']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td[10]/div"))
						.getText();
				String GLendAmt = driver
						.findElement(By
								.xpath("//*[text()='Particulars']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td[11]/div"))
						.getText();
				// System.out.println(GLperiodAmt);
				// System.out.println(GLendAmt);

				if (GLperiodAmt.contains(TrialPeriodAmt) && GLendAmt.contains(TrialEndingAmt)) {
					System.out.println("*** Verified account [" + array[i] + "] as UI Period amount [" + TrialPeriodAmt
							+ "] with expected Period amount [" + GLperiodAmt + "] ");
					System.out.println("*** Verified account [" + array[i] + "] as UI Ending amount [" + TrialEndingAmt
							+ "] with expected Ending amount [" + GLendAmt + "] ");
				} else {
					System.out.println("*** FAILED to Verify account [" + array[i] + "] as UI Period amount ["
							+ TrialPeriodAmt + "] with expected Period amount [" + GLperiodAmt + "] ");
					System.out.println("*** FAILED to Verify account [" + array[i] + "] as UI Ending amount ["
							+ TrialEndingAmt + "] with expected Ending amount [" + GLendAmt + "] ");
					flag = false;
				}
				Utilities.click_element(pro.getProperty("switchToTrialBal"), driver);
				Utilities.justHover(pro.getProperty("TrialBalFetchBtn"), driver);
				Utilities.click_element(pro.getProperty("dropdownCancelcheck"), driver);
			} // main for block

			if (flag == false) {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!! FAILED TO VERIFY [" + ModuleName
						+ "] Trial Balance Report for document number : [" + documentid
						+ "] plz Check Console !!!!!!!!!!!!!!!!!!!!!!!!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			System.out.println(">>>>>>> Verified [" + ModuleName + "] Trial Balance Report for document number : ["
					+ documentid + "] Successfully <<<<<<<<<<<<");
			Utilities.HoverandClick(pro.getProperty("Closereport"), driver);

		} catch (Exception EX) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!! FAILED TO VERIFY [" + ModuleName
					+ "] Trial Balance Report for document number : [" + documentid
					+ "] plz Check Console !!!!!!!!!!!!!!!!!!!!!!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ---------------------------------------- Trade Profit Loss
	// -----------------------------------------------------------------------------
	public static void verify_TradeProfitLoss(String documentid, String moduleName, String accounts[], WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_TradingAndProfitLoss.properties");
			Utilities.waitandClick(pro.getProperty("PLCalculationIcon"), driver);
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";

			Utilities.HoverandClick(pro.getProperty("fromDate"), driver);
			Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
			Utilities.clickButton("Fetch", 500, driver);
			Utilities.isElementGone(xpathOfLoading, 150, driver);
			Utilities.HoverandClick("//button[text()='Expand']", driver);

			ArrayList<String> PLactualAmount = new ArrayList<String>();
			ArrayList<String> GLexpectedAmount = new ArrayList<String>();
			boolean flag = true;
			for (int i = 0; i < accounts.length; i++) {
				WebElement Textelement = null, clickelement = null;
				String UIamount = null, Expamount;

				Textelement = driver.findElement(By.xpath("//*[@id='newTradingProfitandLoss']//*[text()='" + accounts[i]
						+ "']/ancestor::tr/td[5]/div/div"));
				UIamount = Textelement.getText();
				PLactualAmount.add(UIamount);
				Thread.sleep(500);
				clickelement = driver
						.findElement(By.xpath("//*[@id='newTradingProfitandLoss']//*[text()='" + accounts[i] + "']"));

				// switch to GL tab for verification > >
				clickelement.click();
				Utilities.isLoadingDisappear(120, driver);
				String xpathNeedToExpand = pro.getProperty("xpathNeedToExpand");
				String xpathThatExpanded = pro.getProperty("xpathThatExpanded");

				MasterConfiguration.isExpanded(xpathNeedToExpand, xpathThatExpanded, driver);
				Expamount = driver.findElement(By.xpath(
						"//*[@id='groupDetailReport']//*[text()='" + accounts[i] + "']/ancestor::tr/td[10]/div/div"))
						.getText();
				GLexpectedAmount.add(Expamount);
				Thread.sleep(500);

				double PLactualAmt = 0, GLexpectedAmt = 0;
				PLactualAmt = Utilities.getIntegerFrmString(PLactualAmount.get(i));
				GLexpectedAmt = Utilities.getIntegerFrmString(GLexpectedAmount.get(i));

				if (PLactualAmt == GLexpectedAmt) {
					System.out.println("**** Verified Account [" + accounts[i] + "] as UI [" + PLactualAmount.get(i)
							+ "] amount with Expected amount [" + GLexpectedAmount.get(i) + "] *******");
				} else {
					System.out.println(
							"**** FAILED TO VERIFY Account [" + accounts[i] + "] as UI [" + PLactualAmount.get(i)
									+ "] amount with Expected amount [" + GLexpectedAmount.get(i) + "] *******");
					flag = false;
				}
				Utilities.click_element(pro.getProperty("TradingProfitTab"), driver);
			}

			if (flag == false) {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!! FAILED TO VERIFY [" + moduleName
						+ "] Trading & Profit Loss Report for document number : [" + documentid
						+ "] plz Check Console !!!!!!!!!!!!!!!!!!!!!!!!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			// System.out.println(Arrays.deepToString(PLactualAmount.toArray()));
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Verified [" + moduleName
					+ "] Trading & Profit Loss Report for document number : [" + documentid
					+ "] Successfully <<<<<<<<<<<<<<<<<<<<<<<<");
			Utilities.HoverandClick(pro.getProperty("CloseReport"), driver);
		} catch (Exception EX) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!! FAILED TO VERIFY [" + moduleName
					+ "] Trading & Profit Loss Report for document number : [" + documentid
					+ "] plz Check Console !!!!!!!!!!!!!!!!!!!!!!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ---------------------------------------- Balance sheet
	// -----------------------------------------------------------------------------
	public static void verify_BalanceSheet(String documentid, String moduleName, String accounts[], WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_BalanceSheet.properties");
			Utilities.waitandClick(pro.getProperty("balSheetIcon"), driver);
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";

			Utilities.HoverandClick(pro.getProperty("fromDate"), driver);
			Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
			Utilities.clickButton("Fetch", 500, driver);
			Utilities.isElementGone(xpathOfLoading, 150, driver);
			Utilities.HoverandClick(pro.getProperty("ExpandBtn"), driver);

			ArrayList<String> BLactualAmount = new ArrayList<String>();
			ArrayList<String> GLexpectedAmount = new ArrayList<String>();
			boolean flag = true;
			for (int i = 0; i < accounts.length; i++) {
				WebElement Textelement = null, clickelement = null;
				String UIamount = null, Expamount = null;

				Textelement = driver.findElement(By.xpath(
						"//*[@id='periodviewnewbsheet']//*[text()='" + accounts[i] + "']/ancestor::tr/td[4]/div/div"));
				UIamount = Textelement.getText();
				BLactualAmount.add(UIamount);
				Thread.sleep(500);
				clickelement = driver
						.findElement(By.xpath("//*[@id='periodviewnewbsheet']//*[text()='" + accounts[i] + "']"));

				// switch to GL tab for verification > >
				clickelement.click();
				Utilities.isLoadingDisappear(120, driver);
				String xpathNeedToExpand = pro.getProperty("xpathNeedToExpand");
				String xpathThatExpanded = pro.getProperty("xpathThatExpanded");

				MasterConfiguration.isExpanded(xpathNeedToExpand, xpathThatExpanded, driver);
				Expamount = driver.findElement(By.xpath(
						"//*[@id='groupDetailReport']//*[text()='" + accounts[i] + "']/ancestor::tr/td[10]/div/div"))
						.getText();
				GLexpectedAmount.add(Expamount);
				Thread.sleep(500);

				double BLactualAmt = 0, GLexpectedAmt = 0;
				BLactualAmt = Utilities.getIntegerFrmString(BLactualAmount.get(i));
				GLexpectedAmt = Utilities.getIntegerFrmString(GLexpectedAmount.get(i));

				if (BLactualAmt == GLexpectedAmt) {
					System.out.println("**** Verified Account [" + accounts[i] + "] as UI [" + BLactualAmount.get(i)
							+ "] amount with Expected amount [" + GLexpectedAmount.get(i) + "] *******");
				} else {
					System.out.println(
							"**** FAILED TO VERIFY Account [" + accounts[i] + "] as UI [" + BLactualAmount.get(i)
									+ "] amount with Expected amount [" + GLexpectedAmount.get(i) + "] *******");
					flag = false;
				}
				Utilities.waitandClick(pro.getProperty("BalSheetTab"), driver);
			}

			String sheet1openingAmt = driver.findElement(By.xpath(pro.getProperty("OpeningAmountXpath"))).getText();
			String sheet1PeriodAmount = driver.findElement(By.xpath(pro.getProperty("PeriodAmountXpath"))).getText();
			String sheet1Amount = driver.findElement(By.xpath(pro.getProperty("AmountXpath"))).getText();
			// ************** move to sheet 2 *********************
			String sheet2openingAmt = driver.findElement(By.xpath(pro.getProperty("OpeningAmountXpath2"))).getText();
			String sheet2PeriodAmount = driver.findElement(By.xpath(pro.getProperty("PeriodAmountXpath2"))).getText();
			String sheet2Amount = driver.findElement(By.xpath(pro.getProperty("AmountXpath2"))).getText();

			String Header[] = { "Opening Amount", "Period Amount", "Amount" };
			String debitArray[] = { sheet1openingAmt, sheet1PeriodAmount, sheet1Amount };
			String creditArray[] = { sheet2openingAmt, sheet2PeriodAmount, sheet2Amount };

			for (int i = 0; i < debitArray.length; i++) {
				if (debitArray[i].equalsIgnoreCase(creditArray[i])) {
					System.out.println("*** Balancesheet report verified as [ " + Header[i] + " ] with Debitamount [ "
							+ debitArray[i] + " ] & CreditAmount [ " + creditArray[i] + " ] ****");
				} else {
					System.out.println(
							"*** Balancesheet report FAILED TO VERIFY as [ " + Header[i] + " ] with Debitamount [ "
									+ debitArray[i] + " ] & CreditAmount [ " + creditArray[i] + " ] ****");
					flag = false;
				}
			}

			if (flag == false) {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!! FAILED TO VERIFY [" + moduleName
						+ "] Balance sheet Report for document number : [" + documentid
						+ "] plz Check Console !!!!!!!!!!!!!!!!!!!!!!!!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			System.out.println(
					">>>>>>>>>>>>>>>>>>>>>>> Verified [" + moduleName + "] Balance sheet Report for document number : ["
							+ documentid + "] Successfully <<<<<<<<<<<<<<<<<<<<<<<<");
			Utilities.HoverandClick(pro.getProperty("CloseBtn"), driver);
		} catch (Exception EX) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!! FAILED TO VERIFY [" + moduleName
					+ "] Balance sheet Report for document number : [" + documentid
					+ "] plz Check Console !!!!!!!!!!!!!!!!!!!!!!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ---------------------------------------- SOA Vendor
	// -----------------------------------------------------------------------------
	public static void verifySOA_Vendor(String documentid, String moduleName, String vendorid, double expectedAmtDue,
			String debitORcredit, WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_SoAReportLoading.properties");
			Utilities.waitandClick(pro.getProperty("ReportIcon"), driver);
			Utilities.click_element(pro.getProperty("ReportList"), driver);
			Utilities.clickCheckBox(pro.getProperty("reportLoadedCheck"), "uncheck", driver);
			Utilities.enterTextNormalBox("Vendor Account Statement", pro.getProperty("quickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("reportLoadedCheck"), "check", driver);
			Utilities.click_element(pro.getProperty("viewButton"), driver);
			// Set Calendar
			Utilities.click_element(pro.getProperty("frmCalendar"), driver);
			Utilities.click_element(pro.getProperty("selectToday"), driver);
			Utilities.click_element(pro.getProperty("asCalendar"), driver);
			Utilities.click_element(pro.getProperty("selectToday"), driver);
			Utilities.click_element(pro.getProperty("toCalendar"), driver);
			Utilities.click_element(pro.getProperty("selectToday"), driver);
			// Set Vendor data
			Utilities.click_element(pro.getProperty("vendorinputTextBox"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("vendorinputTextBox"), driver);
			Utilities.click_element(pro.getProperty("selectVendorDrpDwnOk"), driver);
			Utilities.click_element(pro.getProperty("fecthBtn"), driver);
			Utilities.isLoadingDisappear(120, driver);

			int headerCnt = Utilities.totalSize("//*[text()='VendorCode']/ancestor::tr/td/div", driver);
			String actualResult = null;
			for (int i = 1; i <= headerCnt; i++) {
				try {
					Actions action = new Actions(driver);
					WebElement hover = driver
							.findElement(By.xpath("//*[text()='VendorCode']/ancestor::tr/td[" + i + "]/div"));
					action.moveToElement(hover).build().perform();
					String myName = driver
							.findElement(By.xpath("//*[text()='VendorCode']/ancestor::tr/td[" + i + "]/div")).getText();
					// System.out.println(myName);
					if (myName.equalsIgnoreCase("Debit Amount") && debitORcredit.equalsIgnoreCase("Debit")) {
						actualResult = driver
								.findElement(
										By.xpath("//*[text()='" + documentid + "']/ancestor::tr/td[" + i + "]/div/div"))
								.getText();
					}
					if (myName.equalsIgnoreCase("Credit Amount") && debitORcredit.equalsIgnoreCase("Credit")) {
						actualResult = driver
								.findElement(
										By.xpath("//*[text()='" + documentid + "']/ancestor::tr/td[" + i + "]/div/div"))
								.getText();
					}
				} catch (Exception No) {
					// skip blank field
				}
			}

			double actAmount = Utilities.getIntegerFrmString(actualResult);
			// System.out.println(actAmount);
			// System.out.println(expectedAmtDue);
			if (actAmount == expectedAmtDue) {
				System.out.println("********* Verified [" + documentid + "] with UI amt [" + actualResult
						+ "] & expected amt [" + expectedAmtDue + "] *********");
			} else {
				System.out.println("********* Failed Verification [" + documentid + "] with UI amt [" + actualResult
						+ "] & expected amt [" + expectedAmtDue + "] *********");
				System.out.println(
						"!!!!!!!!!!!!! FAILED to Verify [" + moduleName + "] SOA Vendor Report for document number : ["
								+ documentid + "] plz check Console !!!!!!!!!!!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			System.out.println(
					">>>>>>>>>>>>>>>>>>>>>>> Verified [" + moduleName + "] SOA Vendor report for document number : ["
							+ documentid + "] plz check Console <<<<<<<<<<<<<<<<<<<<<<<<");
			Utilities.HoverandClick(pro.getProperty("closeVendorSOA"), driver);
			Utilities.HoverandClick(pro.getProperty("closeReportList"), driver);
		} catch (Exception EX) {
			System.out.println("!!!!!!!!!!!!! FAILED to Verify [" + moduleName
					+ "] SOA Vendor Report for document number : [" + documentid + "] plz check Console !!!!!!!!!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ---------------------------------------- Age Payable
	// -----------------------------------------------------------------------------
	public static void verify_AgePayable(String documentid, String moduleName, String vendorid, double expectedAmtDue,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_AgingReportVendor.properties");
			Utilities.waitandClick(pro.getProperty("viewAgedPayIcon"), driver);
			// switch to report tab
			Utilities.click_element(pro.getProperty("ReportViewTab"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("SelectVendorReport"), driver);
			Utilities.click_element(pro.getProperty("ReportDrpDwnArrow"), driver);
			Utilities.click_element(pro.getProperty("ReportFetchBtn"), driver);
			Utilities.isLoadingDisappear(120, driver);

			int headerSize = Utilities.totalSize("//*[text()='Amount Due']/ancestor::tr/td/div", driver);
			int indOfamtDue = 0;
			boolean flag = true;
			for (int i = 1; i <= headerSize; i++) {
				String headName = driver
						.findElement(By.xpath("//*[text()='Amount Due']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headName.equalsIgnoreCase("Amount Due")) {
					indOfamtDue = i;
				}
			}

			String actualResult = driver
					.findElement(By.xpath("//*[text()='" + documentid + "']/ancestor::tr/td[" + indOfamtDue + "]/*"))
					.getText();
			double actAmount = Utilities.getIntegerFrmString(actualResult);

			if (actAmount == expectedAmtDue) {
				System.out
						.println("******* Verified Age Payable report for [ " + documentid + " ] with amount due of [ "
								+ actualResult + " ] Expected Output : [ " + expectedAmtDue + " ] *******");
			} else {
				System.out.println(
						"******* FAILED TO VERIFY Age Payable report for  [ " + documentid + " ] with amount due of [ "
								+ actualResult + " ] Expected Output : [ " + expectedAmtDue + " ] *******");
				flag = false;
			}

			if (flag == false) {
				System.out.println("!!!!!!! FAILED to Verify [" + moduleName
						+ "] Age Payable Report for document number : [" + documentid + "] plz check Console !!!!!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			System.out.println(">>>>>>>>> Verified [" + moduleName + "] Age Payable report for document number : ["
					+ documentid + "] plz check Console >>>>>>>");
			Utilities.HoverandClick(pro.getProperty("CloseReport"), driver);
		} catch (Exception EX) {
			System.out.println("!!!!!!! FAILED to Verify [" + moduleName
					+ "] Age Payable Report for document number : [" + documentid + "] plz check Console !!!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ---------------------------------------- Age Receivable Customer
	// -----------------------------------------------------------------------------
	public static void verify_AgeReceivable(String documentid, String moduleName, String customerid,
			double expectedAmtDue, WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_AgingReportCustomer.properties");
			Utilities.waitandClick(pro.getProperty("viewAgedreceivablesIcon"), driver);

			Utilities.click_element(pro.getProperty("ReportViewTab"), driver);
			Utilities.enterTextandSelect(customerid, pro.getProperty("SelectCustomerName"), driver);
			Utilities.click_element(pro.getProperty("ReportDrpDwnArrow"), driver);
			Utilities.click_element(pro.getProperty("ReportFetchBtn"), driver);
			Utilities.isLoadingDisappear(120, driver);

			int headerSize = Utilities.totalSize("//*[text()='Amount Due']/ancestor::tr/td/div", driver);
			int indOfamtDue = 0;
			boolean flag = true;
			for (int i = 1; i <= headerSize; i++) {
				String headName = driver
						.findElement(By.xpath("//*[text()='Amount Due']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headName.equalsIgnoreCase("Amount Due")) {
					indOfamtDue = i;
				}
			}

			String actualResult = driver
					.findElement(By.xpath("//*[text()='" + documentid + "']/ancestor::tr/td[" + indOfamtDue + "]/*"))
					.getText();
			double actAmount = Utilities.getIntegerFrmString(actualResult);

			if (actAmount == expectedAmtDue) {
				System.out.println(
						"******* Verified Age Receivable report for SI [ " + documentid + " ] with amount due of [ "
								+ actualResult + " ] Expected Output : [ " + expectedAmtDue + " ] *******");
			} else {
				System.out.println("******* FAILED TO VERIFY Age Receivable report for SI [ " + documentid
						+ " ] with amount due of [ " + actualResult + " ] Expected Output : [ " + expectedAmtDue
						+ " ] *******");
				flag = false;
				System.out.println("!!!!!!!!!!!!! FAILED to Verify [" + moduleName
						+ "] Age Receivable Report for document number : [" + documentid
						+ "] plz check Console !!!!!!!!!!!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Verified [" + moduleName
					+ "] Age Receivable report for document number : [" + documentid
					+ "] plz check Console <<<<<<<<<<<<<<<<<<<<<<<<");
			Utilities.HoverandClick(pro.getProperty("CloseReport"), driver);

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
			System.out.println(
					"!!!!!!!!!!!!! FAILED to Verify [" + moduleName + "] Age Receivable Report for document number : ["
							+ documentid + "] plz check Console !!!!!!!!!!!!!");
		}
	}

	// ---------------------------------------- SOA Customer
	// -----------------------------------------------------------------------------
	public static void verifySOA_Customer(String documentid, String moduleName, String customerid,
			double expectedAmtDue, String debitORcredit, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_SoAReportLoading.properties");
			Utilities.waitandClick(pro.getProperty("ReportIcon"), driver);
			Utilities.click_element(pro.getProperty("ReportList"), driver);
			Utilities.clickCheckBox(pro.getProperty("reportLoadedCheck"), "uncheck", driver);
			Utilities.enterTextNormalBox("Customer Account Statement", pro.getProperty("quickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("reportLoadedCheck"), "check", driver);
			Utilities.click_element(pro.getProperty("viewButton"), driver);
			// Set Calendar
			Utilities.click_element(pro.getProperty("frmCalendar"), driver);
			Utilities.click_element(pro.getProperty("selectToday"), driver);
			Utilities.click_element(pro.getProperty("asCalendar"), driver);
			Utilities.click_element(pro.getProperty("selectToday"), driver);
			Utilities.click_element(pro.getProperty("toCalendar"), driver);
			Utilities.click_element(pro.getProperty("selectToday"), driver);
			// Set Vendor data
			Utilities.click_element("//*[@name='accountmulselectcombo']/following-sibling::input", driver);
			Utilities.enterTextandSelect(customerid, "//*[@name='accountmulselectcombo']/following-sibling::input",
					driver);
			Utilities.click_element("//*[@name='accountmulselectcombo']/following-sibling::input/following::img[2]",
					driver);
			Utilities.click_element(pro.getProperty("customerfecthBtn"), driver);
			Utilities.isLoadingDisappear(120, driver);

			int headerCnt = Utilities.totalSize("//*[text()='CustomerCode']/ancestor::tr/td/div", driver);
			String actualResult = null;
			for (int i = 1; i <= headerCnt; i++) {
				try {
					Actions action = new Actions(driver);
					WebElement hover = driver
							.findElement(By.xpath("//*[text()='CustomerCode']/ancestor::tr/td[" + i + "]/div"));
					action.moveToElement(hover).build().perform();
					String myName = driver
							.findElement(By.xpath("//*[text()='CustomerCode']/ancestor::tr/td[" + i + "]/div"))
							.getText();
					// System.out.println(myName);
					if (myName.equalsIgnoreCase("Debit Amount") && debitORcredit.equalsIgnoreCase("Debit")) {
						actualResult = driver
								.findElement(
										By.xpath("//*[text()='" + documentid + "']/ancestor::tr/td[" + i + "]/div/div"))
								.getText();
					}
					if (myName.equalsIgnoreCase("Credit Amount") && debitORcredit.equalsIgnoreCase("Credit")) {
						actualResult = driver
								.findElement(
										By.xpath("//*[text()='" + documentid + "']/ancestor::tr/td[" + i + "]/div/div"))
								.getText();
					}
				} catch (Exception No) {
					// skip blank field
				}
			}

			double actAmount = Utilities.getIntegerFrmString(actualResult);
			// System.out.println(actAmount);
			// System.out.println(expectedAmtDue);
			if (actAmount == expectedAmtDue) {
				System.out.println("********* Verified [" + documentid + "] with UI amt [" + actualResult
						+ "] & expected amt [" + expectedAmtDue + "] *********");
			} else {
				System.out.println("********* Failed Verification [" + documentid + "] with UI amt [" + actualResult
						+ "] & expected amt [" + expectedAmtDue + "] *********");
				System.out.println("!!!!!!!!!!!!! FAILED to Verify [" + moduleName
						+ "] SOA Customer Report for document number : [" + documentid
						+ "] plz check Console !!!!!!!!!!!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			System.out.println(
					">>>>>>>>>>>>>>>>>>>>>>> Verified [" + moduleName + "] SOA Customer report for document number : ["
							+ documentid + "] plz check Console <<<<<<<<<<<<<<<<<<<<<<<<");
			Utilities.HoverandClick(pro.getProperty("closeCustomerSOA"), driver);
			Utilities.HoverandClick(pro.getProperty("closeReportList"), driver);
		} catch (Exception EX) {
			System.out.println(
					"!!!!!!!!!!!!! FAILED to Verify [" + moduleName + "] SOA Customer Report for document number : ["
							+ documentid + "] plz check Console !!!!!!!!!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ---------------------------------------- JE number
	// -----------------------------------------------------------------------------
	public static void verify_JEreport(String JEnumber, String moduleName, String debitAccouts[],
			double expectedDebitAmt[], String creditAccouts[], double expectedCreditAmt[], WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_NormalJE.properties");
			Utilities.click_element(pro.getProperty("JournalEntryReport"), driver);
			Utilities.enterTextNormalBox(JEnumber, pro.getProperty("QuickSearch"), driver);
			Utilities.click_element(pro.getProperty("FetchButton"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.click_element(
					"//*[text()='Entry Number']/ancestor::div[3]/following::div[1]//*[@class='x-grid3-row-expander']",
					driver);

			// Debit Account ------------------
			ArrayList actualDebitAmt = new ArrayList();
			double tempDebit = 0;
			boolean flag = true;
			// String debitAccouts[] =
			// {"GST(BL)@7.00%","AutomationAsset","GST(IM)@7.00%","AutomationLiability"};
			for (int i = 0; i < debitAccouts.length; i++) {
				String amount = null;
				amount = driver
						.findElement(
								By.xpath("//*[contains(text(),'" + debitAccouts[i] + "')]//following::span[3]/div"))
						.getText();
				actualDebitAmt.add(amount);
				double UIamount = Utilities.getIntegerFrmString(amount);
				if (UIamount == expectedDebitAmt[i]) {
					System.out.println("**** JE report Verified DEBIT account [" + debitAccouts[i] + "] as UI ["
							+ actualDebitAmt.get(i) + "] amount with Expected amount [" + expectedDebitAmt[i]
							+ "] !!!!!");
				} else {
					System.out.println("**** JE report FAILED to Verify DEBIT account [" + debitAccouts[i] + "] as UI ["
							+ actualDebitAmt.get(i) + "] amount with Expected amount [" + expectedDebitAmt[i]
							+ "] !!!!!");
					flag = false;
				}
			}

			// Credit Account ------------------
			ArrayList actualCreditAmt = new ArrayList();
			// String creditAccouts[] = {"Cash in hand","Discount
			// Received","Rounding Difference"};
			for (int i = 0; i < creditAccouts.length; i++) {
				String amount = null;
				amount = driver
						.findElement(
								By.xpath("//*[contains(text(),'" + creditAccouts[i] + "')]//following::span[4]/div"))
						.getText();
				actualCreditAmt.add(amount);
				double UIamount = Utilities.getIntegerFrmString(amount);
				if (UIamount == expectedCreditAmt[i]) {
					System.out.println("**** JE report Verified CREDIT account [" + creditAccouts[i] + "] as UI ["
							+ actualCreditAmt.get(i) + "] amount with Expected amount [" + expectedCreditAmt[i]
							+ "] !!!!!");
				} else {
					System.out.println("**** JE report FAILED to Verify CREDIT account [" + creditAccouts[i]
							+ "] as UI [" + actualCreditAmt.get(i) + "] amount with Expected amount ["
							+ expectedCreditAmt[i] + "] !!!!!");
					flag = false;
				}
			}

			ArrayList debitAcc = new ArrayList();
			ArrayList creditAcc = new ArrayList();
			double totalDebitamount = 0.0, totalCreditamount = 0;
			int totalDebitAcc = Utilities.totalSize(
					"//*[text()='Transaction List']/following-sibling::div[3]//span[contains(@style,'width: 37.5')]",
					driver);
			for (int j = 1; j <= totalDebitAcc; j++) {
				String accName = driver.findElement(By
						.xpath("//*[text()='Transaction List']/following-sibling::div[3]//span[contains(@style,'width: 37.5')]["
								+ j + "]"))
						.getText();
				debitAcc.add(accName);

				String debitAmt = driver.findElement(By
						.xpath("//*[text()='Transaction List']/following-sibling::div[3]//span[contains(@style,'width: 37.5')]["
								+ j + "]/following::span[3]"))
						.getText();
				double temp = Utilities.getIntegerFrmString(debitAmt);
				totalDebitamount = totalDebitamount + temp;
			}
			// System.out.println(totalDebitamount);

			int totalCreditAcc = Utilities.totalSize(
					"//*[text()='Transaction List']/following-sibling::div[3]//span[contains(@style,'width: 30')]",
					driver);
			for (int j = 1; j <= totalCreditAcc; j++) {
				String accName = driver.findElement(By
						.xpath("//*[text()='Transaction List']/following-sibling::div[3]//span[contains(@style,'width: 30')]["
								+ j + "]"))
						.getText();
				creditAcc.add(accName);

				String creditAmt = driver.findElement(By
						.xpath("//*[text()='Transaction List']/following-sibling::div[3]//span[contains(@style,'width: 30')]["
								+ j + "]/following::span[4]"))
						.getText();
				double temp = Utilities.getIntegerFrmString(creditAmt);
				totalCreditamount = totalCreditamount + temp;
			}
			// System.out.println(totalCreditamount);

			if (totalDebitamount == totalCreditamount) {
				System.out.println("**** Verified Amount as total DEBIT [" + totalDebitamount + "] with total CREDIT ["
						+ totalCreditamount + "] amount !!!!!");
			} else {
				System.out.println("**** Failed TO Verify as total DEBIT [" + totalDebitamount + "] with total CREDIT ["
						+ totalCreditamount + "] amount !!!!!");
				flag = false;
			}

			if (flag == false) {
				driver.navigate().refresh();
				System.out.println("!!!!!!!!!!!!! FAILED to Verify [" + moduleName
						+ "] JE Report  plz check Console !!!!!!!!!!!!!");
				Assert.assertTrue(false);
			}
			System.out.println("********* Verified JE report [" + moduleName + "]  plz check Console ************");
			Utilities.click_element(pro.getProperty("CloseJournalEntryReport"), driver);
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
			System.out.println(
					"!!!!!!!!!!!!! FAILED to Verify [" + moduleName + "] JE Report  plz check Console !!!!!!!!!!!!!");
		}
	}

	// *********************************** Verify Amount Due for PI
	// **************************************************************
	public static void verify_CashPurchaseORInvoice(String documentid, double expectedAmtDue, WebDriver driver)
			throws IOException, InterruptedException, AWTException {
		try {
			Properties pro = Utilities.fetchProValue("OR_CashPurchase.properties");
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";
			Utilities.HoverandClick(pro.getProperty("SalesInvoiceReport"), driver);

			Utilities.isElementGone(xpathOfLoading, 180, driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.isLoadingDisappear(180, driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			// scroll
			int headerSize = Utilities.totalSize("//*[text()='Purchase Invoice No']/ancestor::tr/td/div", driver);
			int totalAmt = 0;

			for (int i = 1; i < headerSize; i++) {
				String headerName = driver
						.findElement(By.xpath("//*[text()='Purchase Invoice No']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Amount Due")) {
					totalAmt = i;
				}
			}

			String actualAmtDue = driver.findElement(By
					.xpath("//*[text()='Purchase Invoice No']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr[1]/td["
							+ totalAmt + "]/div"))
					.getText();
			double actualDue = Utilities.getIntegerFrmString(actualAmtDue);

			if (actualDue == expectedAmtDue) {
				System.out.println("******* CP/PI report - Verified amount Due for :[" + documentid + "] as UI amount ["
						+ actualAmtDue + "] with expected amount [" + expectedAmtDue + "] pls checlk LOG *******");
			} else {
				System.out.println("******* CP/PI report - FAILED to verify amount Due for :[" + documentid
						+ "] as UI amount [" + actualAmtDue + "] with expected amount [" + expectedAmtDue
						+ "] pls checlk LOG *******");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			Utilities.HoverandClick("//li[@id='as__GRListMainTabEntry']/a[1]", driver);
		} catch (Exception EX) {
			System.out.println("******* CP/PI is NOT Verified for :[" + documentid + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// *********************************** Verify Amount Due for CN
	// **************************************************************
	public static void verify_CreditNote(String documentid, String Cntype, double expectedAmtDue, WebDriver driver)
			throws IOException, InterruptedException, AWTException {
		try {
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherwise.properties");
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";
			Utilities.HoverandClick(pro.getProperty("CreditNoteReport"), driver);

			Utilities.isElementGone(xpathOfLoading, 180, driver);
			Utilities.enterTextandSelect(Cntype, "//input[@name='typeid']", driver);
			// Utilities.click_element("//input[@name='typeid']/following::img[1]",
			// driver);
			// Utilities.click_element("//*[contains(@style,'visible')]//*[text()='"+Cntype+"']",
			// driver);

			Utilities.isLoadingDisappear(120, driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.isLoadingDisappear(120, driver);

			String actualAmtDue = null;
			int headerCnt = Utilities.totalSize("//*[text()='Credit Note No']/ancestor::tr/td/div", driver);
			for (int i = 1; i <= headerCnt; i++) {
				try {
					Actions action = new Actions(driver);
					WebElement hover = driver
							.findElement(By.xpath("//*[text()='Credit Note No']/ancestor::tr/td[" + i + "]/div"));
					action.moveToElement(hover).build().perform();
					String myName = driver
							.findElement(By.xpath("//*[text()='Credit Note No']/ancestor::tr/td[" + i + "]/div"))
							.getText();
					// System.out.println(myName);
					if (myName.equalsIgnoreCase("Amount Due")) {
						actualAmtDue = driver.findElement(By
								.xpath("//*[text()='Credit Note No']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr[1]/td["
										+ i + "]/div"))
								.getText();
					}
				} catch (Exception No) {
					// skip blank field
				}
			}

			double actualDue = Utilities.getIntegerFrmString(actualAmtDue);
			if (actualDue == expectedAmtDue) {
				System.out.println("******* Credit Note report - Verified amount Due for :[" + documentid
						+ "] as UI amount [" + actualAmtDue + "] with expected amount [" + expectedAmtDue
						+ "] pls checlk LOG *******");
			} else {
				System.out.println("******* Credit Note report - FAILED to verify amount Due for :[" + documentid
						+ "] as UI amount [" + actualAmtDue + "] with expected amount [" + expectedAmtDue
						+ "] pls checlk LOG *******");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			Utilities.click_element(pro.getProperty("CloseReport"), driver);
		} catch (Exception EX) {
			System.out.println("******* Credit Note is NOT Verified for :[" + documentid + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// *********************************** New JE try
	// **************************************************************
	public static void verify_GLreportVersion2(String documentid, String moduleName, String JEnumber,
			String GLdebitAccounts[], double GLexpectedDebitAmt[], String GLcreditAccounts[],
			double GLexpectedcreditAmt[], WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_GeneralLedgerLoading.properties");
			Utilities.waitandClick(pro.getProperty("LedgerIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("fromDate"), driver);
			Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Today']", driver);
			// Utilities.HoverandClick(pro.getProperty("toDate"), driver);
			// Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Today']",
			// driver);

			boolean flag = true;
			for (int i = 0; i < GLdebitAccounts.length; i++) {
				ArrayList<Double> actualGLdebit = new ArrayList<Double>();
				Utilities.click_element(pro.getProperty("GLdrpdwnOK"), driver);
				Utilities.enterTextandSelect(GLdebitAccounts[i], pro.getProperty("searchAccount"), driver);
				Utilities.click_element(pro.getProperty("GLdrpdwnOK"), driver);
				Utilities.clickButton("Fetch", 500, driver);

				MasterConfiguration.isExpanded(pro.getProperty("GLcollapsed"), pro.getProperty("GLExpanded"), driver);
				// new code
				List<WebElement> totalfoundJE = driver
						.findElements(By.xpath("//*[contains(text(),'" + JEnumber + "')]/following::span[3]"));
				for (WebElement we : totalfoundJE) {
					String amount = we.getText();
					double temp = Utilities.getIntegerFrmString(amount);
					actualGLdebit.add(temp);
				}
				double actualTotal = 0;
				for (int j = 0; j < actualGLdebit.size(); j++) {
					actualTotal = actualTotal + actualGLdebit.get(j);
				}
				// System.out.println(actualTotal);

				if (actualTotal == GLexpectedDebitAmt[i]) {
					System.out.println(
							"*** In GL report verified Debit account [" + GLdebitAccounts[i] + "] with UI amount ["
									+ actualTotal + "] & expected amount [" + GLexpectedDebitAmt[i] + "] ****");
				} else {
					System.out.println(
							"*** In GL report NOT VERIFIED Debit account [" + GLdebitAccounts[i] + "] with UI amount ["
									+ actualTotal + "] & expected amount [" + GLexpectedDebitAmt[i] + "] ****");
					flag = false;
				}
				Utilities.click_element(pro.getProperty("GLdrpdwnCancel"), driver);
			} // --Debit for loop
			// System.out.println(Arrays.toString(actualGLdebit.toArray()));

			for (int i = 0; i < GLcreditAccounts.length; i++) {
				ArrayList<Double> actualGLcredit = new ArrayList<Double>();
				Utilities.click_element(pro.getProperty("GLdrpdwnOK"), driver);
				Utilities.enterTextandSelect(GLcreditAccounts[i], pro.getProperty("searchAccount"), driver);
				Utilities.click_element(pro.getProperty("GLdrpdwnOK"), driver);
				Utilities.clickButton("Fetch", 500, driver);

				MasterConfiguration.isExpanded(pro.getProperty("GLcollapsed"), pro.getProperty("GLExpanded"), driver);
				// new code
				List<WebElement> totalfoundJE = driver
						.findElements(By.xpath("//*[contains(text(),'" + JEnumber + "')]/following::span[5]"));
				for (WebElement we : totalfoundJE) {
					String amount = we.getText();
					double temp = Utilities.getIntegerFrmString(amount);
					actualGLcredit.add(temp);
				}
				double actualTotal = 0;
				for (int j = 0; j < actualGLcredit.size(); j++) {
					actualTotal = actualTotal + actualGLcredit.get(j);
				}
				// System.out.println(actualTotal);

				if (actualTotal == GLexpectedcreditAmt[i]) {
					System.out.println(
							"*** In GL report verified Credit account [" + GLcreditAccounts[i] + "] with UI amount ["
									+ actualTotal + "] & expected amount [" + GLexpectedcreditAmt[i] + "] ****");
				} else {
					System.out.println("*** In GL report NOT VERIFIED Credit account [" + GLcreditAccounts[i]
							+ "] with UI amount [" + actualTotal + "] & expected amount [" + GLexpectedcreditAmt[i]
							+ "] ****");
					flag = false;
				}
				Utilities.click_element(pro.getProperty("GLdrpdwnCancel"), driver);
			} // --Credit for loop

			if (flag == false) {
				System.out.println(
						"!!!!!!! GL validation is FAILED for JE : [" + JEnumber + "] plz check Console !!!!!!! ");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			if (flag == true) {
				System.out.println(">>>>>>> Verified [" + moduleName + "] General Ledger Report for document number : ["
						+ documentid + "] Successfully <<<<<<<<<<<<");
				Utilities.HoverandClick(pro.getProperty("Closereport"), driver); // closeForm
			}
		} catch (Exception Ex) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!! FAILED TO VERIFY [" + moduleName
					+ "] General Ledger Report for document number : [" + documentid
					+ "] plz Check Console !!!!!!!!!!!!!!!!!!!!!!!!!!");
			Utilities.handleError(Ex, driver);
		}

	}

}
