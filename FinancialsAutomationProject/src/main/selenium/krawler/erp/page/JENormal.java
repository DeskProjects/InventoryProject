package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.utils.SikulliUtil;

public class JENormal {

	public static void create_normalJE(String documentid, String debitaccount, String creditaccount, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties NormalJE = Utilities.fetchProValue("OR_NormalJE.properties");
			documentid = "Nje" + documentid;
			Utilities.waitandClick(NormalJE.getProperty("ClcikOnJEIcon"), driver);
			Utilities.click_element(NormalJE.getProperty("SelectNormalJEOption"), driver);
			Utilities.HoverandClick(NormalJE.getProperty("ClickOnSubmit"), driver);
			Thread.sleep(2000);

			Utilities.enterTextandSelect("NA", NormalJE.getProperty("SequenceFormat"), driver);
			Utilities.HoverandClick("//*[@name='memo']", driver);
			Utilities.enterTextNormalBox(documentid, NormalJE.getProperty("EnterJENo."), driver);

			int headerSize = Utilities.totalSize(
					".//*[@id='JournalEntry1']/div[1]/div[1]/div/div/div/div[3]/div/div[1]/div/div[1]/div/div[1]/div[1]/table/thead/tr/td",
					driver);
			int indOfAcc = 0, indOfdebit = 0, indOfcredit = 0;

			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver.findElement(By
						.xpath(".//*[@id='JournalEntry1']/div[1]/div[1]/div/div/div/div[3]/div/div[1]/div/div[1]/div/div[1]/div[1]/table/thead/tr/td["
								+ i + "]"))
						.getText();
				if (headerName.equalsIgnoreCase("Account")) {
					indOfAcc = i;
				} else if (headerName.equalsIgnoreCase("Debit Amount")) {
					indOfdebit = i;
				} else if (headerName.equalsIgnoreCase("Credit Amount")) {
					indOfcredit = i;
				}
			}

			// enter Debit Account
			Utilities.HoverandClick(
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfAcc + "]/div",
					driver);
			Utilities.enterTextandSelect(debitaccount, "//*[@id='accountid']/following::input[1]", driver);
			Utilities.clickAndEnterText("10",
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfdebit + "]/div",
					driver);
			Utilities.HoverandClick("//*[@name='memo']", driver);
			// enter Credit Account
			Utilities.HoverandClick(
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfAcc + "]/div",
					driver);
			Utilities.enterTextandSelect(creditaccount, "//*[@id='accountid']/following::input[1]", driver);
			Utilities.clickAndEnterText("10",
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfcredit + "]/div",
					driver);
			Utilities.HoverandClick("//*[@name='memo']", driver);

			Utilities.HoverandClick(NormalJE.getProperty("ClickOnSaveButtton"), driver);
			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 2000, driver);
			Utilities.HoverandClick(NormalJE.getProperty("CloseForm"), driver);

			System.out.println("Normal JE [" + documentid + "] created sucess !! ");
		} catch (Exception EX) {
			System.out.println("Normal JE [" + documentid + "] NOT CREATED !! ");
			Utilities.handleError(EX, driver);
		}
	}
	// ************************************************************Verification*********************************************************************

	public static void verify_normalJE(String documentID, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			documentID = "Nje" + documentID;
			Properties pro = Utilities.fetchProValue("OR_NormalJE.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("JournalEntryReport"))));
			driver.findElement(By.xpath(pro.getProperty("JournalEntryReport"))).click();
			Thread.sleep(5000);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentID);
			search.sendKeys(Keys.TAB);

			WebElement fetchbtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("FetchButton"))));
			fetchbtn.click();
			Thread.sleep(2000);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg24NormalJournalEntryDetails']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg24NormalJournalEntryDetails']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				// System.out.println(header);
				if (header.equals("Entry Number")) {
					assertEquals(documentID,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg24NormalJournalEntryDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("JE Number Verified");
				}

				else if (header.equals("Description")) {
					assertEquals("Normal Journal Entry",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg24NormalJournalEntryDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}
			}

			System.out.println("Normal Journal Entry for [" + documentID + "] verified !!!!");
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseJournalEntryReport"))));
			driver.findElement(By.xpath(pro.getProperty("CloseJournalEntryReport"))).click();
			Thread.sleep(500);

		} catch (Exception EX) {
			System.out.println("Normal Journal Entry for [" + documentID + "] NOT verified !!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// *************************************** CopyEditDelete
	// ***************************************
	public static void CopyEditDelete_normalJE(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_NormalJE.properties");
			String copyNormalJEID = "Copy" + documentid;

			Utilities.waitandClick(pro.getProperty("JournalEntryReport"), driver);
			Thread.sleep(1000);
			Utilities.clickAndEnterText(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.HoverandClick(pro.getProperty("FetchButton"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			// Copy
			Utilities.HoverandClick(pro.getProperty("copyNormalJEButton"), driver);
			Thread.sleep(2000);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox(copyNormalJEID, pro.getProperty("copyNormalJEId"), driver);

			String formLoadedXpath = "//*[text()='Account']/ancestor::div[3]/following::div[1]/div/div[2]/table/tbody/tr/td[3]/div";
			Utilities.clickCheckBox(formLoadedXpath, "uncheck", driver);
			Utilities.HoverandClick(pro.getProperty("saveCopiedNormalJEId"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Utilities.click_element(pro.getProperty("CopyNormalJEClose"), driver);
			Utilities.clickAndEnterText(copyNormalJEID, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println(">>>> JE [" + documentid + "] COPY successfully !!! ");

			// Edit
			Utilities.HoverandClick(pro.getProperty("EditNormalJEButton"), driver);
			Thread.sleep(2000);
			Utilities.clickCheckBox(formLoadedXpath, "uncheck", driver);
			Utilities.enterTextNormalBox("Performing Edit test case for JE : [" + documentid + "]", "//*[@name='memo']",
					driver);
			Utilities.click_element(pro.getProperty("NormalJEEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Utilities.click_element(pro.getProperty("EditNormalJEClose"), driver);
			Utilities.clickAndEnterText(copyNormalJEID, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			System.out.println(">>>> JE [" + documentid + "] EDIT successfully !!! ");

			// Delete
			Utilities.HoverandClick(pro.getProperty("deleteNormalJEButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteNormalJEPermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);

			Utilities.clickAndEnterText(copyNormalJEID, pro.getProperty("QuickSearch"), driver);
			Utilities.Enter();

			WebElement delConfirmation = driver
					.findElement(By.xpath("//*[contains(text(),'There is no record to display')]"));
			if (delConfirmation.isDisplayed()) {
				System.out.println(">>>> JE [" + documentid + "] DELETE successfully !!! ");
			} else {
				System.out.println(">>>> JE [" + documentid + "] is NOT DELETED  !!! ");
			}

			Utilities.HoverandClick(pro.getProperty("CloseJournalEntryReport"), driver);
		} catch (Exception EX) {
			System.out.println("***** Failed to Copy-Edit-Delete flow fr JE [" + documentid + "] check log !!!");
			Utilities.handleError(EX, driver);
		}
	}

	// * * * * * * * EXPORT Jornal Entry * * * * * *

	public static void Export_JournalEntry(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);
			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			String ExportButtonName = "Export ";
			Properties pro2 = Utilities.fetchProValue("OR_NormalJE.properties");
			String reportIcon = pro2.getProperty("JournalEntryReport");
			String waitForQuickSearch = pro2.getProperty("DocumentCheckBox");
			String closeReportPage = pro2.getProperty("CloseJournalEntryReport");

			Set s = null; // this method will gives you the handles of all
							// opened windows
			Iterator ite = null;
			String parentWindow = null;

			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(reportIcon)));
			element.click();
			Thread.sleep(2000);

			// select from date
			Utilities.HoverandClick(pro.getProperty("fromIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
			Thread.sleep(1000);

			// select To date
			Utilities.HoverandClick(pro.getProperty("toIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
			Thread.sleep(1000);

			Utilities.clickButton("Fetch", 500, driver);
			Thread.sleep(1000);

			try {

				Utilities.clickCheckBox(waitForQuickSearch, "check", driver);
				Utilities.report_ScreenShot("Journal Entry", driver);

				// CSV File
				String exportTocsv[] = { pro.getProperty("JESummary"), pro.getProperty("JEDetailed") };
				for (int i = 0; i < exportTocsv.length; i++) {
					Utilities.clickButton(ExportButtonName, 500, driver);
					Utilities.justHover(pro.getProperty("ExportToCSV"), driver);
					Utilities.HoverandClick(exportTocsv[i], driver);
					Thread.sleep(1000);
					SikulliUtil.sikulli_waitClick(driver, "CSVtype");
					SikulliUtil.sikulli_waitClick(driver, "ClsX");
					System.out.println("* * * * * * EXPORT for [.CSV] completed successfully * * * * * * *");
					Thread.sleep(1500);
				}
				System.out.println(
						"* * * * * * EXPORT for [ Summary & Detailed .CSV ] completed successfully * * * * * * *");

				// full PDF Portrait view
				Utilities.clickButton(ExportButtonName, 500, driver);
				parentWindow = driver.getWindowHandle();
				Utilities.justHover(pro.getProperty("ExportToPDF"), driver);
				Utilities.HoverandClick(pro.getProperty("JEPortrait"), driver);
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "PDFtype");
				Thread.sleep(1500);
				s = driver.getWindowHandles(); // this method will gives you the
												// handles of all opened windows
				ite = s.iterator();
				while (ite.hasNext()) {
					String childWindow = ite.next().toString();
					if (!childWindow.contains(parentWindow)) {
						driver.switchTo().window(childWindow);
						Thread.sleep(1000);
						System.out.println(
								"* * * * * * EXPORT for [all Records PDF] completed successfully * * * * * * *");
						driver.close();
						driver.switchTo().window(parentWindow);
						Thread.sleep(1000);
					}
				}

				// Excel in Sumary & Details
				for (int i = 0; i < exportTocsv.length; i++) {
					Utilities.clickButton(ExportButtonName, 500, driver);
					Utilities.justHover(pro.getProperty("ExportToExcel"), driver);
					Utilities.HoverandClick(exportTocsv[i], driver);
					Utilities.clickButton("No", 500, driver);
					Thread.sleep(1000);
					SikulliUtil.sikulli_waitClick(driver, "JEExcel");
					SikulliUtil.sikulli_waitClick(driver, "ClsX");
					System.out.println("* * * * * * EXPORT for [EXCEl] completed successfully * * * * * * *");
					Thread.sleep(1500);
				}
				System.out.println(
						"* * * * * * EXPORT for [ Summary & Detailed .EXCEl ] completed successfully * * * * * * *");

				// Excel for Selected Record in Sumary & Details
				for (int i = 0; i < exportTocsv.length; i++) {
					Utilities.clickButton(ExportButtonName, 500, driver);
					Utilities.justHover(pro.getProperty("ExportselectedtoXLS"), driver);
					Utilities.HoverandClick(exportTocsv[i], driver);
					Thread.sleep(1000);
					SikulliUtil.sikulli_waitClick(driver, "JEExcel");
					SikulliUtil.sikulli_waitClick(driver, "ClsX");

					System.out.println("* * * * * * EXPORT for [EXCEl] completed successfully * * * * * * *");
					Thread.sleep(1500);
				}
				System.out.println(
						"* * * * * * EXPORT for [ SELECTED JE Summary & Detailed .EXCEL ] completed successfully * * * * * * *");

				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(closeReportPage)));
				element.click();
				System.out.println(
						"* * * * * * EXPORT for module [Receive Payment] completed successfully * * * * * * *");

			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				Utilities.handleError(noData, driver);
			}
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [Journal Entry] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}

	}

	// get button path for Sikulli
	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;

	}

	// Check or Uncheck checkBox button
	public static void clickCheckBoxExport(String checkBoxXpath, String chkOrUnchk, WebDriver driver)
			throws InterruptedException {
		WebElement chkBox = new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(checkBoxXpath)));

		if (chkBox.isEnabled()) {
			Thread.sleep(1300);
			if (chkOrUnchk.equalsIgnoreCase("CHECK")) {
				if (!chkBox.isSelected()) {
					chkBox.click();
					Thread.sleep(1500);
				}
			} else if (chkOrUnchk.equalsIgnoreCase("UNCHECK")) {
				if (chkBox.isSelected()) {
					chkBox.click();
					Thread.sleep(1000);
				}
			}
		}
	}
}
