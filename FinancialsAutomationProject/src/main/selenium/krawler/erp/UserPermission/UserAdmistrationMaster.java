package krawler.erp.UserPermission;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class UserAdmistrationMaster {

	public static void UserPermission_ForMaster(WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			Properties UP = Utilities.fetchProValue("OR_UserPermission.properties");
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			// Click on User Administration button

			WebElement UserADBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("Administrationbtn"))));
			UserADBtn.click();
			Thread.sleep(1000);

			// new
			// WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("CustomerId"))));
			// WebElement customer =
			// driver.findElement(By.xpath(LM.getProperty("CustomerId")));

			// Click on User administration link
			WebElement UserADlink = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("Administrationlink"))));
			UserADlink.click();
			Thread.sleep(1000);

			// Selection of user

			WebElement UserSel = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("Smithradio"))));
			UserSel.isEnabled();
			UserSel.click();
			Thread.sleep(2000);

			// Click on assign permissions

			WebElement assignper = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("assignpermissionbtn"))));
			assignper.click();
			Thread.sleep(1000);

			// select account preferences expander

			WebElement AccPrefEx = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("AccPrefExpander"))));

			AccPrefEx.click();

			Thread.sleep(1000);

			// click on view check box to enable all check boxes

			WebElement Viecheckbox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("AccPrefViewCB"))));

			if (!Viecheckbox.isSelected()) {
				Viecheckbox.click();

				Thread.sleep(1000);
			} else {
				System.out.println("Already selected");
			}

			// Viecheckbox.click(); Thread.sleep(3000); //
			Viecheckbox.click();

			WebElement AccPrefcollapse = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("AccPrefExpander"))));

			AccPrefcollapse.click();

			// click on customer drop down to expand

			WebElement CusEx = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("cusexpander"))));
			Thread.sleep(1000);

			CusEx.click();

			// permission of create

			WebElement CusCR = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CusCreateCB"))));

			if (!CusCR.isSelected()) {

				CusCR.click();

				Thread.sleep(2000);

			} else

			{
				System.out.println("Already selected");
			}

			Thread.sleep(2000);

			// permission of view/modify/delete
			WebElement element11 = null;

			String customer[] = { "cusViewCB", "ModCus", "ChartCus", "DelCus", "ImpCus", "ExpCus", "PrintCus",
					"ViewAllCus", "openingBalCus", "ViewIBGCus", "EditIBGCus" };

			for (int i = 0; i < customer.length; i++) {
				element11 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(customer[i]))));
				if (!element11.isSelected()) {
					Thread.sleep(2000);
					element11.click();
					System.out.println("    Check now ENABLED for [" + customer[i] + "]    ");
					System.out.println("\n");
				}

				else {
					System.out.println("    Check Already ENABLED for [" + customer[i] + "]    ");

					System.out.println("\n");
				}
			}

			Thread.sleep(2000);

			CusEx.click();

			// ********* product management Expand

			WebElement ProExpand = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ProExpander"))));

			ProExpand.click();
			Thread.sleep(2000);

			WebElement ProCr = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ProCreate"))));

			if (!ProCr.isSelected()) {
				ProCr.click();
			}

			else {

				System.out.println("product create dropdown checked box is already selected");
			}
			Thread.sleep(2000);

			// Utilities.refresh();

			WebElement ProV = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ProView"))));

			if (!ProV.isSelected()) {
				ProV.click();
			} else {
				System.out.println("product view dropdown checked box is already selected");

			}
			Thread.sleep(3000);

			WebElement ProCollapse = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ProExpander"))));

			ProCollapse.click();

			Thread.sleep(1000);

			// Expand bank Reconciliation

			WebElement BankRec = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("BankReconsile"))));

			BankRec.click();

			// Permission of Bank reconciliation

			WebElement ViewBankRec = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ViewReconsile"))));

			if (!ViewBankRec.isSelected()) {
				Thread.sleep(2000);
				ViewBankRec.click();

				System.out.println("    Bank reconcillation is selected  ");

			} else {
				System.out.println("Bank reconcillation is already selected");
			}

			Thread.sleep(2000);

			// Collapse Bank reconcillation
			BankRec.click();

			// permission for Aged receivable

			// Expand

			WebElement ExpandAR = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ArExpand"))));

			Thread.sleep(2000);

			ExpandAR.click();

			WebElement element1 = null;

			// WebElement ExpandAr =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.
			// getProperty("ArExpand"))));

			// ExpandAr.click();

			// Thread.sleep(2000);

			String arr1[] = { "ARView", "ARChart", "ARExport", "ARPrint" };

			for (int i = 0; i < arr1.length; i++) {

				element1 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(arr1[i]))));

				if (!element1.isSelected()) {
					Thread.sleep(2000);
					element1.click();
					System.out.println("    Check now ENABLED for [" + arr1[i] + "]    ");

				} else {
					System.out.println("    Check Already ENABLED for [" + arr1[i] + "]    ");
				}

			}

			Thread.sleep(2000);
			// collapse AR

			ExpandAR.click();

			// WebElement element11 = null;

			// WebElement element11 =
			// driver.findElement(By.xpath("//div[@id='AP-col0']/div/div/fieldset[15]/legend/div"));
			// Actions
			// actions = new Actions(driver);
			// actions.moveToElement(element11);
			// actions.perform();

			// Custom field Dimension

			// Expand

			WebElement ExpandCustomField = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandCustomField"))));

			ExpandCustomField.click();

			// Add master item

			WebElement element111 = null;

			String customfield[] = { "ItemMenu", "MasterItem" };
			for (int i = 0; i < customfield.length; i++) {

				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(customfield[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + customfield[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + customfield[i] + "]    ");
				}
			}

			// Collapse

			ExpandCustomField.click();

			// Export Log

			WebElement ExportLog = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExportLog"))));

			ExportLog.click();

			WebElement ViewExportLog = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ViewExportLog"))));
			if (!ViewExportLog.isSelected()) {

				ViewExportLog.click();
			}

			else {
				System.out.println("checkbox for Export Log is already selected");
			}

			// collapse

			ExportLog.click();
			Thread.sleep(2000);

			// Vendor

			WebElement ExpandVen = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandVen"))));
			ExpandVen.click();

			// Create vendor

			WebElement CrVen = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrVen"))));
			if (!CrVen.isSelected()) {

				CrVen.click();
			}

			else {
				System.out.println("checkbox for Create Vendor is already selected");

			}

			// View/Delete/copy

			String Ven[] = { "ViewVen", "ModifyVen", "DelVen", "ChartVen", "ImportVen", "ExportVen", "PrintVen",
					"ViewAllVen", "OpengBalVen", "ViewIBG", "EditIBG", };

			for (int i = 0; i < Ven.length; i++) {

				element11 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(Ven[i]))));
				if (!element11.isSelected()) {
					Thread.sleep(2000);

					element11.click();

					System.out.println("    Check now ENABLED for [" + Ven[i] + "]    ");

				}

				else {

					System.out.println("    Check Already ENABLED for [" + Ven[i] + "]    ");

				}

			}

			// Collapse vendor

			ExpandVen.click();

			// Financial statements

			// Trial Balance // Expand

			WebElement ExpandFinancial = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandFinancial"))));
			ExpandFinancial.click();

			// View/Export/Print

			String tb[] = { "ViewTB", "ExTB", "PrintTB" };

			for (int i = 0; i < tb.length; i++) {
				element11 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(tb[i]))));
				if (!element11.isSelected()) {
					Thread.sleep(2000);
					element11.click();
					System.out.println("    Check now ENABLED for [" + tb[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + tb[i] + "]    ");
				}
			}

			// View Trading and P&L:

			String pnl[] = { "ViewPnl", "ExportPnl", "PrintPnl" };

			for (int i = 0; i < pnl.length; i++) {

				element11 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(pnl[i]))));

				if (!element11.isSelected()) {
					Thread.sleep(2000);
					element11.click();
					System.out.println("    Check now ENABLED for [" + pnl[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + pnl[i] + "]    ");
				}
			}

			// Balance sheet

			String bs[] = { "ViewBs", "ExBs", "PrintBs" };

			for (int i = 0; i < bs.length; i++) {
				element11 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(bs[i]))));
				if (!element11.isSelected()) {
					Thread.sleep(2000);
					element11.click();
					System.out.println("    Check now ENABLED for [" + bs[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + bs[i] + "]    ");
				}
			}

			// Cash Book

			String cash[] = { "ViewCashBook", "ExportCashBook", "PrintCashBook" };

			for (int i = 0; i < cash.length; i++) {
				element11 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(cash[i]))));

				if (!element11.isSelected()) {
					Thread.sleep(2000);
					element11.click();
					System.out.println("    Check now ENABLED for [" + cash[i] + "]    ");
				}

				else {
					System.out.println("    Check Already ENABLED for [" + cash[i] + "]    ");
				}
			}

			// Bank Book

			String Bank[] = { "ViewBankBook", "ExportBankBook", "PrintBankBook" };

			for (int i = 0; i < Bank.length; i++) {
				element11 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(Bank[i]))));
				if (!element11.isSelected()) {
					Thread.sleep(2000);
					element11.click();

					System.out.println("    Check now ENABLED for [" + Bank[i] + "]    ");
				}

				else {
					System.out.println("    Check Already ENABLED for [" + Bank[i] + "]    ");
				}
			}

			ExpandFinancial.click();

			Thread.sleep(2000);

			// Account management and Journal entry

			// expand

			WebElement ExpandAccManage = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandAccManage"))));

			ExpandAccManage.click();

			// Create

			WebElement CrCoa = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrCoa"))));

			if (!CrCoa.isSelected()) {

				CrCoa.click();
			}

			else {
				System.out.println("checkbox for Create COA is already selected");
			}

			// View/modify/delete COA

			WebElement elementcoa = null;

			String accmanagement[] = { "ViewCoa", "ModyCoa", "DelCoa", "ExpCoa", "ImpCoa", "PrintCoa" };

			for (int i = 0; i < accmanagement.length; i++) {
				elementcoa = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(accmanagement[i]))));

				if (!elementcoa.isSelected()) {

					Thread.sleep(2000);

					elementcoa.click();

					System.out.println("    Check now ENABLED for [" + accmanagement[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + accmanagement[i] + "]    ");
				}
			}

			// Journal entry

			WebElement CrJournal = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrJournal"))));

			if (!CrJournal.isSelected()) {

				CrJournal.click();
			}

			else {
				System.out.println("checkbox for Create Journal is already selected");
			}

			// Vie/Export/Delete/copy journal Entry

			String accmanagement1[] = { "ViewJournal", "ExportJournal", "PrintJournal", "DelJournal", "CopyJournal",
					"EditJournal", "ImportJournal" };

			for (int i = 0; i < accmanagement1.length; i++) {
				elementcoa = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(accmanagement1[i]))));
				if (!elementcoa.isSelected()) {
					Thread.sleep(2000);
					elementcoa.click();
					System.out.println("    Check now ENABLED for [" + accmanagement1[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + accmanagement1[i] + "]    ");
				}
			}

			ExpandAccManage.click();

			// WebElement elementcoa=null;

			// Aged Payable

			WebElement ExpandAp = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandAp"))));
			ExpandAp.click();
			String Ap[] = { "ViewExpandAp", "ChartExpandAp", "ExpExpandAp", "PrintExpandAp" };

			for (int i = 0; i < Ap.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(Ap[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + Ap[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + Ap[i] + "]    ");
				}
			}

			// collpase aged payable

			ExpandAp.click();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);
		}

		Thread.sleep(2000);

	}

}
