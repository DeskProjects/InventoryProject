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

public class UserAdministrationPurchase {

	public static void UserPermission_ForPurchase(WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			Properties UP = Utilities.fetchProValue("OR_UserPermission.properties");
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

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
			//// // Click on User administration link
			//// WebElement UserADlink = wait
			//// .until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("Administrationlink"))));
			//// UserADlink.click();
			//// Thread.sleep(1000);
			////
			//// // Selection of user
			////
			//// WebElement UserSel = wait
			//// .until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("Smithradio"))));
			//// UserSel.isEnabled();
			//// UserSel.click();
			//// Thread.sleep(2000);
			////
			//// // Click on assign permissions
			////
			//// WebElement assignper = wait
			//// .until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("assignpermissionbtn"))));
			//// assignper.click();
			//// Thread.sleep(1000);
			//
			//

			// Expand of Purchase requisition

			WebElement PurchaseReqExp = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("PurchaseReq"))));

			PurchaseReqExp.click();

			// Permission of purchase requisition

			WebElement CreatePurchaseReq = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CreatePurReq"))));

			if (!CreatePurchaseReq.isSelected())

			{
				CreatePurchaseReq.click();

			} else {

				System.out.println(" Create purchase requisition checkbox is already selected");

			}

			WebElement elementrequest = null;

			String purequisition[] = { "ViewPurReq", "ModPurReq", "DelPurReq", "exportPurReq", "PrintPurReq",
					"CopyPurReq" };

			for (int i = 0; i < purequisition.length; i++) {

				elementrequest = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(purequisition[i]))));

				if (!elementrequest.isSelected())

				{

					Thread.sleep(2000);

					elementrequest.click();

					System.out.println("    Check now ENABLED for [" + purequisition[i] + "]    ");

				}
			}

			// View RFQ
			WebElement elementrequest1 = null;

			String rfq1[] = { "ViewRfq", "InitiateRfq", "DelRfq", "ExpRfq", "PrintRfq", "CpyRfq", "EditRfq" };

			for (int i1 = 0; i1 < rfq1.length; i1++) {

				elementrequest1 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(rfq1[i1]))));

				if (!elementrequest1.isSelected())

				{

					Thread.sleep(2000);

					elementrequest.click();

					System.out.println("    Check now ENABLED for [" + rfq1[i1] + "]  ");

				}

			}

			// Collpse purchase requisition

			PurchaseReqExp.click();
			Thread.sleep(2000);

			// expand Purchase return

			WebElement ExpandPReturn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandPurRet"))));

			ExpandPReturn.click();
			Thread.sleep(2000);

			// permission to create purchase return

			WebElement CrPReturn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CreatePurRet"))));

			if (!CrPReturn.isSelected()) {

				CrPReturn.click();
			}

			System.out.println("Create permission is given");
			Thread.sleep(2000);

			// permission to view

			WebElement element22 = null;

			String arr[] = { "CreatePurRet", "ViewPuReturn", "EditPurRet", "DelPurRet", "ExpPurRet", "PrintPurRet",
					"CopPurRet", "UnlinkPurRet" };

			for (int i1 = 0; i1 < arr.length; i1++) {
				element22 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(arr[i1]))));

				if (!element22.isSelected()) {

					Thread.sleep(2000);

					element22.click();

					System.out.println("    Check now ENABLED for [" + arr[i1] + "]    ");
				} else {

					System.out.println("    Check Already ENABLED for [" + arr[i1] + "]    ");
				}
			}

			Thread.sleep(2000);

			// Collapse Purchase return

			ExpandPReturn.click();

			// Purchase management

			// Expand

			WebElement ExpandPurchaseMana = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandPurchaseMana"))));

			ExpandPurchaseMana.click();

			// vendor quotation

			// create VQ

			WebElement CrVq = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrVq"))));
			if (!CrVq.isSelected()) {

				CrVq.click();
			}

			else {
				System.out.println("checkbox for Create Vendor Quotation already selected");

			}

			// View/Edit/Del/Copy Vq

			WebElement element111 = null;

			String Vq[] = { "ViewVq", "EditVq", "DelVq", "ExportVq", "PrintVq", "CopyVq", "UnlinkVq" };
			for (int i1 = 0; i1 < Vq.length; i1++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(Vq[i1]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + Vq[i1] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + Vq[i1] + "]    ");
				}
			}

			// Purchase Order

			// create PO
			WebElement CrPo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrPo"))));
			if (!CrPo.isSelected()) {

				CrPo.click();
			}

			else {
				System.out.println("checkbox for Create Vendor Quotation already selected");

			}

			// View/Edit/Del PO

			String PO[] = { "ViewPo", "EditPo", "DelPo", "ExportPo", "ExportPo", "CopyPo", "UnlinkPo" };

			for (int i1 = 0; i1 < PO.length; i1++) {

				element111 = new WebDriverWait(driver, 30)

						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(PO[i1]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + PO[i1] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + PO[i1] + "]    ");
				}
			}

			// Purchase Invoices

			// import PI

			WebElement ImportPi = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ImportPi"))));
			if (!ImportPi.isSelected()) {

				ImportPi.click();
			}

			else {
				System.out.println("checkbox for Import purchase invoiceis already selected");

			}

			// create cash purchase

			WebElement CrCP = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrCP"))));
			if (!CrCP.isSelected()) {

				CrCP.click();
			}

			else {
				System.out.println("checkbox for Cash Purchase is already selected");

			}

			// Create purchase Invoice

			WebElement CrPi = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrPi"))));
			if (!CrPi.isSelected()) {

				CrPi.click();
			}

			else {
				System.out.println("checkbox for Cash Purchase is already selected");

			}

			// View PI

			String pi[] = { "ViewPi", "EditPi", "CopyPi", "DelPi", "EmailPi", "ExpotPi", "printPi", "unlinkPi",
					"RecurringPi" };

			for (int i1 = 0; i1 < pi.length; i1++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(pi[i1]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);

					element111.click();

					System.out.println("    Check now ENABLED for [" + pi[i1] + "]    ");

				}

				else {
					System.out.println("    Check Already ENABLED for [" + pi[i1] + "]    ");
				}
			}
			Thread.sleep(2000);

			// Collapse purchase management

			ExpandPurchaseMana.click();

			// Goods Receipt

			// Expand GR

			WebElement ExpandGoods = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandGoods"))));

			ExpandGoods.click();

			// create GR

			WebElement CreateGoods = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CreateGoods"))));
			if (!CreateGoods.isSelected()) {

				CreateGoods.click();
			}

			else {
				System.out.println("checkbox for Create Goods receipt is already selected");

			} // view/Modify/Export/Print GR

			String gr[] = { "ViewGoods", "ModiGoods", "DelGoods", "ExportGoods", "PrintGoods", "CopyGoods",
					"UnlinkGoods" };
			for (int i1 = 0; i1 < gr.length; i1++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(gr[i1]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + gr[i1] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + gr[i1] + "]    ");
				}
			}

			// Collapse GR

			ExpandGoods.click();

			// Debit Note

			// Expand Dn

			WebElement ExpandDn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandDn"))));
			ExpandDn.click();

			// Import Dn

			WebElement ImportDn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ImportDn"))));

			if (!ImportDn.isSelected()) {
				Thread.sleep(2000);
				ImportDn.click();
				System.out.println("    Check now ENABLED for Debit Note    ");
			} else {
				System.out.println("    Check Already ENABLED for Debit Note    ");
			}

			// Create

			WebElement CrDn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrDn"))));
			if (!CrDn.isSelected()) {
				Thread.sleep(2000);

				CrDn.click();

				System.out.println("    Check now ENABLED for Create Debit Note    ");

			} else {

				System.out.println("    Check Already ENABLED for Create Debit Note    ");

			}
			// view/Edit/Del

			String Dn[] = { "ViewDn", "EditDn", "DelDn", "ExpDn", "PrintDn", "CpyDn" };

			for (int i1 = 0; i1 < Dn.length; i1++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(Dn[i1]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);

					element111.click();

					System.out.println("    Check now ENABLED for [" + Dn[i1] + "]    ");

				} else {

					System.out.println("    Check Already ENABLED for [" + Dn[i1] + "]    ");
				}
			}

			// Collapse Debit Note

			ExpandDn.click();

			// Make Payment

			// Expand

			WebElement MakPay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("MakPay"))));
			MakPay.click();

			// Import

			WebElement ImportMakPay = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ImportMakPay"))));

			if (!ImportMakPay.isSelected()) {
				Thread.sleep(2000);
				ImportMakPay.click();
				System.out.println("    Check now ENABLED for import Make payment   ");
			} else {
				System.out.println("    Check Already ENABLED for import Make Payment    ");

			}

			// create

			WebElement CrMakPay = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrMakPay"))));
			if (!CrMakPay.isSelected()) {
				Thread.sleep(2000);
				CrMakPay.click();
				System.out.println("    Check now ENABLED for create Make payment   ");
			} else {
				System.out.println("    Check Already ENABLED for create Make Payment    ");

			}

			// View/ Modify/ copy

			String mp[] = { "ViewMakPay", "ModMakPay", "CpyMakPay", "DelMakPay", "EmaiMakPay", "ExpMakPay",
					"PrintMakPay" };

			for (int i1 = 0; i1 < mp.length; i1++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(mp[i1]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + mp[i1] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + mp[i1] + "]    ");
				}
			}

			// Collapse Make Payment

			MakPay.click();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);
		}

	}

}
