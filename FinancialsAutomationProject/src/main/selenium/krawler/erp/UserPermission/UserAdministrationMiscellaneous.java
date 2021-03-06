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

public class UserAdministrationMiscellaneous {

	public static void UserPermission_ForMiscellaneous(WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			Properties UP = Utilities.fetchProValue("OR_UserPermission.properties");
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			//
			// // Click on User Administration button
			//
			// WebElement UserADBtn =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("Administrationbtn"))));
			// UserADBtn.click();
			// Thread.sleep(1000);
			//
			// // new
			// //
			// WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("CustomerId"))));
			// // WebElement customer =
			// // driver.findElement(By.xpath(LM.getProperty("CustomerId")));
			//
			// // Click on User administration link
			// WebElement UserADlink = wait
			// .until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("Administrationlink"))));
			// UserADlink.click();
			// Thread.sleep(1000);
			//
			// // Selection of user
			//
			// WebElement UserSel = wait
			// .until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("Smithradio"))));
			// UserSel.isEnabled();
			// UserSel.click();
			// Thread.sleep(2000);
			//
			// // Click on assign permissions
			//
			// WebElement assignper = wait
			// .until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("assignpermissionbtn"))));
			// assignper.click();
			// Thread.sleep(1000);
			//

			// Expand Tax

			WebElement TaxExp = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("TaxExpand"))));
			TaxExp.click();

			WebElement TaxV = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("TaxView"))));
			if (TaxV.isSelected()) {
				TaxV.click();
				Thread.sleep(1000);

				if (!TaxV.isSelected()) {
					TaxV.click();

				}

			}

			// Collapse Tax

			TaxExp.click();

			// Expand payment Term

			WebElement PayTerEXP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("PayTerm"))));
			PayTerEXP.click();
			Thread.sleep(1000);

			// permission to view payment term

			WebElement PayTerView = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ViewPayTerm"))));

			if (PayTerView.isSelected()) {
				PayTerView.click();

			}
			Thread.sleep(2000);

			if (!PayTerView.isSelected()) {
				PayTerView.click();
			}

			// Collapse payment term Thread.sleep(1000);

			PayTerEXP.click();

			// Expand master configuration

			WebElement MasterExpand = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("MasterConfig"))));

			MasterExpand.click();

			// permission for master configuration

			WebElement ViewMasterconf = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ViewMasterConfig"))));

			if (!ViewMasterconf.isSelected()) {
				ViewMasterconf.click();
			}

			Thread.sleep(2000);

			// permission of custom layout

			WebElement ViewCuslay = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CusLayout"))));
			Thread.sleep(2000);
			ViewCuslay.click();

			Thread.sleep(2000);

			// Permission of custom Designer

			WebElement

			ViewCusDes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CusDesign"))));

			ViewCusDes.click();

			Thread.sleep(2000);

			// Permission of Active Date Range

			WebElement ViewActivedate = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ActiveDate"))));

			ViewActivedate.click();

			Thread.sleep(2000);

			MasterExpand.click();

			// Expand Import Log

			WebElement ImportExp = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ImportLog"))));
			ImportExp.click();

			Thread.sleep(1000);

			// Permission of Import Log

			WebElement ViewImportLog = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ViewImport"))));

			if (!ViewImportLog.isSelected())

			{
				ViewImportLog.click();

			} else {

				System.out.println("Import Log is already selected");

			}
			Thread.sleep(1000);

			// Collapse Import Log

			ImportExp.click();

			// Write off and recover projects
			// Expand
			WebElement ExpandWriteoff = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandWriteoff"))));
			ExpandWriteoff.click();

			// write off
			WebElement Writeoff = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("Writeoff"))));

			if (!Writeoff.isSelected()) {

				Writeoff.click();
			}

			else {
				System.out.println("checkbox for write off receipts is already selected");
			}

			// view/recover Write off and recover projects
			WebElement element111 = null;

			String writeoff[] = { "ViewWriteoff", "RecoverWriteoff" };

			for (int i = 0; i < writeoff.length; i++) {

				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(writeoff[i]))));
				if (!element111.isSelected()) {

					Thread.sleep(2000);

					element111.click();

					System.out.println("    Check now ENABLED for [" + writeoff[i] + "]    ");
				}

				else {
					System.out.println("    Check Already ENABLED for [" + writeoff[i] + "]    ");
				}
			}

			// collapse write off

			ExpandWriteoff.click();

			// Payment method

			// Expand payment method

			WebElement ExpandPayMethod = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandPayMethod"))));

			ExpandPayMethod.click();

			Thread.sleep(2000);

			// view/modify

			String pay[] = { "ViewPay", "ModPay" };
			for (int i = 0; i < pay.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(pay[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + pay[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + pay[i] + "]    ");
				}
			}

			// Bank type acc balance

			WebElement BankType = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("BankType"))));
			if (!BankType.isSelected()) {

				BankType.click();
			}

			else {
				System.out.println("checkbox for View Bank Type Account Balance is already selected");

			}

			// cash type acc balance

			WebElement Cashtype = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("Cashtype"))));
			if (!Cashtype.isSelected()) {

				Cashtype.click();
			}

			else {
				System.out.println("checkbox for View Cash Type Account Balance is already selected");

			}

			// collapse payment method

			ExpandPayMethod.click();

			// User administration

			// Expand

			WebElement UserAdmin = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("UserAdmin"))));

			UserAdmin.click();

			Thread.sleep(2000);

			// View permissions

			WebElement ViewPer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ViewPer"))));
			if (!ViewPer.isSelected()) {

				ViewPer.click();
			}

			else {
				System.out.println("checkbox for View Permissions is already selected");

			}
			Thread.sleep(2000);

			// Assign permissions

			WebElement AssignPer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("AssignPer"))));
			if (!AssignPer.isSelected()) {

				AssignPer.click();
			}

			else {
				System.out.println("checkbox for Assign Permissions is already selected");

			}

			// collapse user permissionss

			UserAdmin.click();

			// Audit Trail

			// Expand Audit Trail

			WebElement ExpandAudit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandAudit"))));

			ExpandAudit.click();

			Thread.sleep(2000);

			// view permission to Audit trail

			WebElement ViewAudit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ViewAudit"))));
			if (!ViewAudit.isSelected()) {

				ViewAudit.click();
			}

			else {
				System.out.println("checkbox for View Audit trail is already selected");

			}

			// Collapse Audit trail

			ExpandAudit.click();

			// Expand UOM

			WebElement ExpandUOM = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandUOM"))));
			ExpandUOM.click();

			// View/ modify/ Import UOM

			String uom[] = { "ViewUOM", "ModiUOM", "ImportUOM" };
			for (int i = 0; i < uom.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(uom[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + uom[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + uom[i] + "]    ");
				}
			}

			// collapse UOM

			ExpandUOM.click();
			Thread.sleep(2000);

			// Approval rule

			// Expand Approval Rule

			WebElement ExApproval = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExApproval"))));
			ExApproval.click();

			// view Approval Rule

			WebElement ViewApproval = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ViewApproval"))));
			if (!ViewApproval.isSelected()) {

				ViewApproval.click();
			}

			else {
				System.out.println("checkbox for View Approval Rule is already selected");

			} // Collapse Approval Rule

			ExApproval.click();

			// Miscellaneous

			// Expand

			WebElement ExpandMisc = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandMisc"))));
			ExpandMisc.click();

			// View Customize Template Logo:

			String templogo[] = { "ViewCusLogo", "Uploadlogo" };

			for (int i = 0; i < templogo.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(templogo[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + templogo[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + templogo[i] + "]    ");
				}
			}

			// View Customize PDF Template:

			String pdftemp[] = { "ViewCusTemp", "ModCusTemp" };

			for (int i = 0; i < pdftemp.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(pdftemp[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + pdftemp[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + pdftemp[i] + "]    ");
				}
			}

			// View Sales Commission:

			String commission[] = { "ViewSalesCom", "ModSalesCom" };

			for (int i = 0; i < commission.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(commission[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + commission[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + commission[i] + "]    ");
				}
			}

			// View Document Templates:

			WebElement ViewDocTemp = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ViewDocTemp"))));

			if (!ViewDocTemp.isSelected()) {
				Thread.sleep(2000);
				ViewDocTemp.click();
				System.out.println("    Check now ENABLED for Document Template    ");
			} else {
				System.out.println("    Check Already ENABLED for Document template   ");
			}

			// Account revaluation

			String reval[] = { "ViewAccRev", "PostAccRev" };

			for (int i = 0; i < reval.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(reval[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + reval[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + reval[i] + "]    ");
				}
			}

			// IBG

			String ibg[] = { "ViewIbg", "ApplyIbg", "GenIbg" };

			for (int i = 0; i < ibg.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(ibg[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + ibg[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + ibg[i] + "]    ");
				}
			}

			// View Check Layout Setup:
			String chklay[] = { "Chklayout", "ModChkLayout" };

			for (int i = 0; i < chklay.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(chklay[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + chklay[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + chklay[i] + "]    ");
				}
			}

			// Set WIP And CP Accounts Data

			String wipacc[] = { "WipAcc", "ModWipAcc" };

			for (int i = 0; i < wipacc.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(wipacc[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + wipacc[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + wipacc[i] + "]    ");
				}
			}

			// View UOM Schema:

			String uomsch[] = { "ViewSchema", "CrSchema", "EditSchema", "DelSchema", "ConfgSchema", "ImpSchema" };

			for (int i = 0; i < uomsch.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(uomsch[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + uomsch[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + uomsch[i] + "]    ");
				}
			}

			// Collapse miscellaneous

			ExpandMisc.click();

			// Save All permissions

			// WebElement ApplyButton =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ApplyButton"))));
			// ApplyButton.click();
			// Thread.sleep(3000);

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);
		}

		Thread.sleep(2000);

	}

}
