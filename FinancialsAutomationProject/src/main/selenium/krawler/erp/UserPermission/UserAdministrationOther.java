package krawler.erp.UserPermission;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.thread.ThreadExecutionException;

import krawler.erp.page.Utilities;

public class UserAdministrationOther {

	public static void UserPermission_ForOther(WebDriver driver)
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
			// WebElement UserADlink =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("Administrationlink"))));
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
			// Expand for currency exchange

			WebElement CurExcExp = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CurExchange"))));

			CurExcExp.click();

			Thread.sleep(1000);

			// Permission for currency exchange

			WebElement element = null;
			String curexchange[] = { "ViewCurExchange", "ModifyCurExc" };

			for (int i = 0; i < curexchange.length; i++) {
				element = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(curexchange[i]))));
				if (!element.isSelected()) {

					Thread.sleep(2000);

					element.click();

					System.out.println("    Check now ENABLED for [" + curexchange[i] + "]    ");
				}

				else {

					System.out.println("    Check Already ENABLED for [" + curexchange[i] + "]    ");
				}
			}

			// Collapse currency exchange

			CurExcExp.click();

			// consignment stock sales

			// WebElement element2 =
			// driver.findElement(By.xpath("//div[@id='AP-col0']/div/div/fieldset[15]/legend/div"));

			// Actions action1 = new Actions(driver);
			// action1.moveToElement(element2);
			// action1.perform();

			// Expand

			WebElement ConExp = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ConsignmentExpand"))));

			ConExp.click();

			// Create consignment

			WebElement CrCon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CreateCon"))));

			if (!CrCon.isSelected()) {
				CrCon.click();
			}

			else {

				System.out.println("Create consignment checkbox is already selected");
			}

			// View consignment

			WebElement element11 = null;

			String arr3[] = { "ViewConsign", "ModifyConsign", "DelConsign", "ExportConsign", "PrintConsign" };

			for (int i = 0; i < arr3.length; i++) {
				element11 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(arr3[i]))));
				if (!element11.isSelected()) {
					Thread.sleep(2000);
					element11.click();
					System.out.println("    Check now ENABLED for [" + arr3[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + arr3[i] + "]    ");
				}
			}

			Thread.sleep(2000);

			// Create consignment DO

			WebElement CrConsignDO = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrConsignDO"))));

			if (!CrConsignDO.isSelected()) {
				CrConsignDO.click();
			}

			else {
				System.out.println("Consignment DO is already selected");
			}

			// View Consignment DO

			WebElement element111 = null;

			String arr4[] = { "ViewConsignDO", "ModifyConsignDO", "delConsignDO", "ExpoConsignDO", "PrintConsignDO" };

			for (int i = 0; i < arr4.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(arr4[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + arr4[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + arr4[i] + "]    ");
				}
			}

			Thread.sleep(2000);

			// Create consignment sales invoice/consignment DO as label

			WebElement CrConsignSi = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrConsignInvoice"))));
			if (!CrConsignSi.isSelected()) {
				CrConsignSi.click();
			} else {
				System.out.println("Consignment SI is already selected");
			}

			String arr5[] = { "ViewConsignInvoice", "ModifyConsignInvoice", "DelConsignInvoice", "ExportConsignInvoice",
					"PrintConsignInvoice" };

			for (int i = 0; i < arr5.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(arr5[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + arr5[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + arr5[i] + "]    ");
				}
			}

			Thread.sleep(2000);

			// Create consignment return

			WebElement CrConsignmentReturn1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrConsignmentReturn"))));

			if (!CrConsignmentReturn1.isSelected()) {
				CrConsignmentReturn1.click();
			} else {
				System.out.println("create consignment is already checked");
			}

			Thread.sleep(2000);

			String conreturn[] = { "ViewConReturn", "ModifyConReturn", "DelConReturn", "ExpConReturn",
					"PrintConReturn" };

			for (int i = 0; i < conreturn.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(conreturn[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + conreturn[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + conreturn[i] + "]    ");
				}
			}

			// consignment loan
			String conloan[] = { "ViewconsignmentLoan", "ExpconsignmentLoan" };

			for (int i = 0; i < conloan.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(conloan[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + conloan[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + conloan[i] + "]    ");
				}
			}

			// consignment outstanding loan

			String conoutloan[] = { "ViewCoonOutstaLoan", "ExpCoonOutstaLoan" };

			for (int i = 0; i < conoutloan.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(conoutloan[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + conoutloan[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + conoutloan[i] + "]    ");
				}
			}

			// stock availability by customer warehouse

			WebElement StockAvailability = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("StockAvailability"))));
			if (!StockAvailability.isSelected()) {
				StockAvailability.click();

			} else {
				System.out.println("Stock availability by customer warehouse is already selected");
			}

			Thread.sleep(2000);

			ConExp.click();

			// Stock request

			// Expand

			WebElement StockReq = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("StockReq"))));

			StockReq.click();

			// View Stock request

			String stockreq[] = { "ViewStockReq", "IssueStock", "CollectStock", "PrintStock", "ExpStock" };

			for (int i = 0; i < stockreq.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(stockreq[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + stockreq[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + stockreq[i] + "]    ");
				}
			}

			// Collapse stock request

			StockReq.click();

			// Stock Adjustment Thread.sleep(2000); //Expand stock adjustment
			WebElement StockAdjustment = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("StockAdjustment"))));

			StockAdjustment.click();

			// Create stock adjustment

			WebElement CrStocjAdjust = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrStocjAdjust"))));

			if (!CrStocjAdjust.isSelected()) {
				CrStocjAdjust.click();
			} else {
				System.out.println("Stock adjustment checkbox is already selected");

			}

			// View stock adjustment

			String stocadj[] = { "ViewStocjAdjust", "ExpStocjAdjust" };

			for (int i = 0; i < stocadj.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(stocadj[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + stocadj[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + stocadj[i] + "]    ");
				}
			}
			StockAdjustment.click();

			Thread.sleep(2000);

			// Inter location stock transfer

			WebElement InterLoc = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("InterLoc"))));

			InterLoc.click();

			// create inter location stock transfer.

			WebElement CrInterLoc = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrInterLoc"))));
			if (!CrInterLoc.isSelected()) {
				CrInterLoc.click();
			} else {
				System.out.println("Create inter location stock transfer is already selected");

			}
			Thread.sleep(2000);

			InterLoc.click();

			// Inventory Reports //expandInventory Reports

			WebElement InvReport = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("InvReport"))));

			InvReport.click();

			//

			WebElement StockAvail = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("StockAvail"))));
			if (!StockAvail.isSelected()) {
				StockAvail.click();
			} else {
				System.out.println("Stock Availability By Warehouse: is already selected");
			}

			// Batch Wise Stock Tracking

			WebElement stocktracking = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("stocktracking"))));
			if (!stocktracking.isSelected()) {
				stocktracking.click();
			} else {
				System.out.println("Batch Wise Stock Tracking is already selected");

			}

			// Material In/Out Register

			WebElement MatInOutReg = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("MatInOutReg"))));
			if (!MatInOutReg.isSelected()) {
				MatInOutReg.click();
			} else {
				System.out.println("Material In/Out Register is already selected");

			}

			// Stock Movement Register

			WebElement StockMov = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("StockMov"))));
			if (!StockMov.isSelected()) {
				StockMov.click();
			} else {
				System.out.println("Stock Movement Register: is already selected");

			}

			// View product threshold Report:

			WebElement ProThresh = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ProThresh"))));
			if (!ProThresh.isSelected()) {
				ProThresh.click();
			} else {
				System.out.println("View product threshold Report: is already selected");

			}

			// "View Inventory Configuration

			WebElement InvConfig = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("InvConfig"))));
			if (!InvConfig.isSelected()) {
				InvConfig.click();
			} else {
				System.out.println("View Inventory Configuration: is already selected");

			}
			Thread.sleep(2000);
			InvReport.click();

			// Asset sales management

			// Expand

			WebElement ExpandAssetSalem = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandAssetSalem"))));

			ExpandAssetSalem.click();

			Thread.sleep(3000);

			// create asset DO

			WebElement CrAssetDo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrAssetDo"))));

			if (!CrAssetDo.isSelected()) {
				CrAssetDo.click();
			} else {
				System.out.println("Check box for Create Asset delivery Order is already selected");
			}

			// view/Edit/Del/Copy/Export/Print

			String assetdo[] = { "ViewAssetDo", "EditAssetDo", "DelAssetDo", "ExportAssetDo", "PrintAssetDo" };

			for (int i = 0; i < assetdo.length; i++)

			{

				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(assetdo[i]))));
				if (!element111.isSelected()) {

					Thread.sleep(2000);

					element111.click();
				}

				else

				{

					System.out.println("    Check Already ENABLED for [" + assetdo[i] + "]    ");

				}

			}

			Thread.sleep(2000);

			// Disposal invoice

			// create

			WebElement CrDispInv = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrDispInv"))));

			if (!CrDispInv.isSelected()) {
				CrDispInv.click();
			} else {
				System.out.println("Check box for Create Asset Disposal invoice is already selected");
			}

			Thread.sleep(2000);

			// View/edit/Del/copy/Export/print

			String assetsi[] = { "ViewDispInv", "EditDispInv", "DelDispInv", "ExportDispInv", "PrintDispInv" };

			for (int i = 0; i < assetsi.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(assetsi[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + assetsi[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + assetsi[i] + "]    ");
				}
			}

			Thread.sleep(2000);

			ExpandAssetSalem.click();

			// Lease Management

			// Expand

			WebElement ExpandLeaseMana = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandLeaseMana"))));

			ExpandLeaseMana.click();

			// Lease quotation

			// Create

			WebElement CrLQ = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrLQ"))));

			if (!CrLQ.isSelected()) {
				CrLQ.click();
			} else {
				System.out.println("Check box for Create Lease quotation invoice is already selected");

			}

			// View/edit/del/export/print

			String leasequo[] = { "ViewLQ", "EditLQ", "DelLQ", "ExportLQ", "PrintLQ" };

			for (int i = 0; i < leasequo.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(leasequo[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + leasequo[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + leasequo[i] + "]    ");
				}
			}

			// lease Order

			// create

			WebElement CrLO = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrLO"))));

			if (!CrLO.isSelected()) {
				CrLO.click();
			} else {
				System.out.println("Check box for Create Lease Order is already selected");

			}

			// view/edit/del/export/print

			String leaorder[] = { "ViewLO", "EditLO", "DelLO", "ExportLO", "PrintLO" };

			for (int i = 0; i < leaorder.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(leaorder[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + leaorder[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + leaorder[i] + "]    ");
				}
			}

			// Lease Delivery order

			// create

			WebElement CrLDO = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrLDO"))));
			if (!CrLDO.isSelected()) {
				CrLDO.click();
			} else {
				System.out.println("Check box for Create Lease Delivery Order is already selected");

			}

			// View/Edit/Del/Export

			String leasedo[] = { "ViewLDO", "EditLDO", "DelLDO", "ExportLDO", "PrintLDO" };

			for (int i = 0; i < leasedo.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(leasedo[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + leasedo[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + leasedo[i] + "]    ");
				}
			}

			// lease Invoice

			// create

			WebElement CrLeaseInv = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrLeaseInv"))));
			if (!CrLeaseInv.isSelected()) {
				CrLeaseInv.click();
			} else {
				System.out.println("Check box for Create Lease Invoice is already selected");

			}

			// view/edit/del lease invoice lease

			String leassi[] = { "ViewLeaseInv", "EditLeaseInv", "DelLeaseInv", "ExportLeaseInv", "PrintLeaseInv" };

			for (int i = 0; i < leassi.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(leassi[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + leassi[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + leassi[i] + "]    ");
				}
			}

			// lease Sales return

			// Create

			WebElement CrLeaseRet = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrLeaseRet"))));
			if (!CrLeaseRet.isSelected()) {
				CrLeaseRet.click();
			} else {
				System.out.println("Check box for Create Lease Return is already selected");

			}

			// View/Edir/Del Lease return

			String learet[] = { "ViewLeaseRet", "EditLeaseRet", "DelLeaseRet", "ExportLeaseRet", "PrintLeaseRet" };

			for (int i = 0; i < learet.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(learet[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + learet[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + learet[i] + "]    ");
				}
			}

			Thread.sleep(2000);

			ExpandLeaseMana.click();

			Thread.sleep(2000);

			// Stock repair

			// Expand

			WebElement ExpandStkRepair = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandStkRepair"))));

			ExpandStkRepair.click();

			// View /Approve stock repair

			String stkrepair[] = { "ViewStkRepair", "ApprovStkRepair" };

			for (int i = 0; i < stkrepair.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(stkrepair[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + stkrepair[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + stkrepair[i] + "]    ");
				}
			}

			Thread.sleep(2000);
			ExpandStkRepair.click();

			Thread.sleep(2000);

			// Asset Purchase requisition

			// expand

			WebElement ExpaAssetReq = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpaAssetReq"))));
			ExpaAssetReq.click();

			// Create asset purchase requisition

			WebElement CrAssetReq = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrAssetReq"))));

			if (!CrAssetReq.isSelected()) {
				CrAssetReq.click();
			} else {
				System.out.println("    Check box for asset purchase requisition is laready selected   ");
			}

			// View/edit/del/expo

			String assetreq1[] = { "ViewAssetReq", "EditAssetReq", "DelAssetReq", "ExportAssetReq", "PrintAssetReq",
					"CopyAssetReq" };

			for (int i = 0; i < assetreq1.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(assetreq1[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + assetreq1[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + assetreq1[i] + "]    ");
				}
			}

			// asset RFQ

			// create

			WebElement Crrfq = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("Crrfq"))));
			if (!Crrfq.isSelected()) {
				Crrfq.click();
			} else {
				System.out.println("    Check  Box is checked for asset RFQ   ");
			}

			// View/edit/ Del asset RFQ

			String assetrfq1[] = { "Viewrfq", "Editrfq", "Delrfq", "Exportrfq", "Printrfq", "Copyrfq" };

			for (int i = 0; i < assetrfq1.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(assetrfq1[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + assetrfq1[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + assetrfq1[i] + "]    ");
				}
			}

			Thread.sleep(2000);

			ExpaAssetReq.click();

			Thread.sleep(2000);

			// Delivery planner

			// Expand

			WebElement ExpandDevPlanner = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandDevPlanner"))));

			ExpandDevPlanner.click();

			// View/Delete delivery planner

			String devplanner[] = { "ViewDevPlanner", "DelDevPlanner" };

			for (int i = 0; i < devplanner.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(devplanner[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + devplanner[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + devplanner[i] + "]    ");
				}
			}

			Thread.sleep(2000);

			ExpandDevPlanner.click();

			// Asset Sales return

			// Expand

			WebElement ExpandAssSalRet = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandAssSalRet"))));

			ExpandAssSalRet.click();

			// Create

			WebElement CrAssSalRet = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrAssSalRet"))));

			if (!CrAssSalRet.isSelected()) {

				CrAssSalRet.click();
			}

			else {
				System.out.println("checkbox for asset sales return is already selected");
			}

			// View/edit/del/export/print

			String asssaleret[] = { "ViewAssSalRet", "EditAssSalRet", "DelAssSalRet", "ExpAssSalRet",
					"PrintAssSalRet" };

			for (int i = 0; i < asssaleret.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(asssaleret[i]))));

				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + asssaleret[i] + "]    ");
				}

				else {
					System.out.println("    Check Already ENABLED for [" + asssaleret[i] + "]    ");
				}
			}

			Thread.sleep(2000);

			ExpandAssSalRet.click();

			Thread.sleep(2000);

			// Labour master

			// Expand

			WebElement ExpandLabour = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandLabour"))));

			ExpandLabour.click();

			Thread.sleep(2000);

			// Create labour

			WebElement CrLabour = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrLabour"))));
			if (!CrLabour.isSelected()) {

				CrLabour.click();
			}

			else {
				System.out.println("checkbox for labour master is already selected");

			}

			// View/modify/del/export

			String labourmas[] = { "ViewLabour", "ModiLabour", "DelLabour", "ExpoLabour" };

			for (int i = 0; i < labourmas.length; i++) {

				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(labourmas[i]))));
				if (!element111.isSelected()) {

					Thread.sleep(2000);

					element111.click();
					System.out.println("    Check now ENABLED for [" + labourmas[i] + "]    ");
				} else {

					System.out.println("    Check Already ENABLED for [" + labourmas[i] + "]    ");

				}
			}

			// Collapse

			ExpandLabour.click();

			Thread.sleep(2000);

			// Work centre master

			// Expand

			WebElement WorkCentre = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("WorkCentre"))));

			WorkCentre.click();

			Thread.sleep(2000);

			// create work centre

			WebElement CrWorkCentre = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrWorkCentre"))));
			if (!CrWorkCentre.isSelected()) {

				CrWorkCentre.click();
			}

			else {
				System.out.println("checkbox for work centre master is already selected");
			}

			// View/edit/clone/del/export

			String work[] = { "ViewCrWorkCentre", "ModiCrWorkCentre", "CloneCrWorkCentre", "DelCrWorkCentre",
					"ExpCrWorkCentre" };

			for (int i = 0; i < work.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(work[i]))));

				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + work[i] + "]    ");
				}

				else {
					System.out.println("    Check Already ENABLED for [" + work[i] + "]    ");
				}
			}

			// collapse work centre

			WorkCentre.click();

			// Master

			// Expand

			WebElement ExpandContract = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandContract"))));
			ExpandContract.click();

			Thread.sleep(2000);

			// Create

			WebElement CrContract = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrContract"))));
			if (!CrContract.isSelected()) {

				CrContract.click();
			}

			else {
				System.out.println("checkbox for contract master is already selected");
			}

			// view/modify/del/export

			String mastcontract[] = { "ViewContract", "ModiContract", "DelContract", "ExpContract" };

			for (int i = 0; i < mastcontract.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(mastcontract[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + mastcontract[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + mastcontract[i] + "]    ");
				}
			}

			// collapse

			ExpandContract.click();

			// Dishonoured Cheque

			// Expand

			WebElement ExpandDischeque = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandDischeque"))));
			ExpandDischeque.click();

			// View

			String dishonoured[] = { "ViwDischeque", "MarkDischeque" };

			for (int i = 0; i < dishonoured.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(dishonoured[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + dishonoured[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + dishonoured[i] + "]    ");
				}
			}

			ExpandDischeque.click();

			// Expand Account type

			WebElement ExpandAccType = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandAccType"))));

			ExpandAccType.click();

			// Create and View Account type

			// Permission for account type

			String acctype[] = { "CrAccType", "ViewAccType" };

			for (int i = 0; i < acctype.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(acctype[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + acctype[i] + "]    ");

				} else {
					System.out.println("    Check Already ENABLED for [" + acctype[i] + "]    ");
				}
			}

			// collapse Account type

			ExpandAccType.click();

			Thread.sleep(2000);

			Thread.sleep(2000); // Unit of Measure

			// Asset

			// Expand Asset

			WebElement ExpAsset = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpAsset"))));

			ExpAsset.click();

			// View Asset Groups

			String assgroup[] = { "ViewAssetGrp", "CrAssetGrp", "EdiAssetGrp", "DelAssetGrp" };

			for (int i = 0; i < assgroup.length; i++)

			{
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(assgroup[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + assgroup[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + assgroup[i] + "]    ");
				}
			}
			Thread.sleep(2000);

			// Generate Asset Depreciation

			String assdep[] = { "AssDep", "PostDep", "ExpDep", "PrintDep" };
			for (int i = 0; i < assdep.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(assdep[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + assdep[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + assdep[i] + "]    ");
				}
			}
			Thread.sleep(2000);

			// Unpost asset Depreciation

			String unpost[] = { "UnpostAssDep", "UnpoDep" };
			for (int i = 0; i < unpost.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(unpost[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + unpost[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + unpost[i] + "]    ");
				}
			}

			// View Asset Maintainance schedule

			String maintainance[] = { "ViewAssMaintainance", "CrAssMaintainance", "DelAssMaintainance",
					"EditAssMaintainance" };
			for (int i = 0; i < maintainance.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(maintainance[i]))));

				if (!element111.isSelected()) {
					Thread.sleep(2000);

					element111.click();

					System.out.println("    Check now ENABLED for [" + maintainance[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + maintainance[i] + "]    ");
				}
			}

			Thread.sleep(2000);

			// View Asset Report

			String assetreport[] = { "ViewAssRep", "ExpAssRep", "PrintAssRep" };
			for (int i = 0; i < assetreport.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(assetreport[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + assetreport[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + assetreport[i] + "]    ");
				}
			}

			Thread.sleep(2000);

			// View Maintainance Work order

			String maintainWO[] = { "ViewMainWO", "DelMainWO", "ExpMainWO", "PrintMainWO" };
			for (int i = 0; i < maintainWO.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(maintainWO[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + maintainWO[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + maintainWO[i] + "]    ");
				}
			}

			Thread.sleep(2000);

			// View Asset Depreciation Details Report

			String AssetDepDet[] = { "ViewAssetDepDet", "ExpAssetDepDet", "PrintAssetDepDet" };
			for (int i = 0; i < AssetDepDet.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(AssetDepDet[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + AssetDepDet[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + AssetDepDet[i] + "]    ");
				}
			}
			Thread.sleep(1000);

			// View Asset Maintainance Schedules

			String AssetSch[] = { "AssetSched", "UpdateAssetSched" };

			for (int i = 0; i < AssetSch.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(AssetSch[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + AssetSch[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + AssetSch[i] + "]    ");
				}
			}
			Thread.sleep(1000);

			// Opening Balance

			WebElement AssetOpBal = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("AssetOpBal"))));

			if (!AssetOpBal.isSelected()) {
				Thread.sleep(2000);
				AssetOpBal.click();

			} else {

				System.out.println("    Check Already ENABLED for Opening Balance    ");

			}

			Thread.sleep(2000);

			// Collapse Asset//

			ExpAsset.click();

			// ********** Consignment Stock-Purchase

			// Expand Consignment Stock-Purchase

			WebElement ExpandConsiStk = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandConsiStk"))));

			ExpandConsiStk.click();

			// Create Vendor Consignment Request

			WebElement CrVenReq = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrVenReq"))));

			if (!CrVenReq.isSelected()) {
				Thread.sleep(2000);
				CrVenReq.click();
				System.out.println("    Check now ENABLED for Create Consignment Stock-Purchase     ");
			} else {
				System.out.println("    Check Already ENABLED forCreate Consignment Stock-Purchase    ");
			}

			// View/edit/Del

			String venconsign[] = { "ViewVenReq", "ModVenReq", "DelVenReq", "ExpVenReq", "PrintVenReq" };
			for (int i = 0; i < venconsign.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(venconsign[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + venconsign[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + venconsign[i] + "]    ");
				}
			}

			// Consignment goods receipt

			// create

			WebElement CrConsGr = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrConsGr"))));

			if (!CrConsGr.isSelected()) {
				Thread.sleep(2000);
				CrConsGr.click();
				System.out.println("    Check now ENABLED for Create Consignment Goods receipt   ");
			} else {
				System.out.println("    Check Already ENABLED forCreate Consignment Goods receipt     ");
			}

			Thread.sleep(2000);

			// View
			String consigngr[] = { "ViewConsGr", "ModConsGr", "ExpConsGr", "PrintConsGr" };
			for (int i = 0; i < consigngr.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(consigngr[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + consigngr[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + consigngr[i] + "]    ");
				}
			}
			Thread.sleep(2000);

			// Create Consignment Purchase Invoice:

			WebElement CrConsPi = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrConsPi"))));

			if (!CrConsPi.isSelected()) {

				CrConsPi.click();
				System.out.println("    Check now ENABLED for Create Consignment Purchase Invoice  ");
			} else {
				System.out.println("    Check Already ENABLED for Create ConsignmentPurchase Invoice     ");
			}
			Thread.sleep(2000);

			// View/Edit/Del

			String conspi[] = { "ViewConsPi", "ModConsPi", "DelConsPi", "ExpConsPi", "PrintConsPi" };
			for (int i = 0; i < conspi.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(conspi[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + conspi[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + conspi[i] + "]    ");
				}
			}

			// Create Consignment Purchase Return:

			// Create

			WebElement CrConsPr = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrConsPr"))));

			if (!CrConsPr.isSelected()) {

				CrConsPr.click();
				System.out.println("    Check now ENABLED for Create Consignment Purchase Return  ");
			} else {
				System.out.println("    Check Already ENABLED for Create Consignment Purchase Return     ");
			}
			Thread.sleep(2000);

			// View/Edit/Del consignment Purchase return

			String conspr[] = { "ViewConsPr", "ModConsPr", "DelConsPr", "ExpConsPr", "PrintConsPr" };
			for (int i = 0; i < conspr.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(conspr[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + conspr[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + conspr[i] + "]    ");
				}
			}

			// collapse Consignment Stock Purchase

			ExpandConsiStk.click();

			// Issue Note

			// Create

			WebElement ExpIssNote = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpIssNote"))));
			ExpIssNote.click();

			// Create

			WebElement CrIssNote = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrIssNote"))));

			if (!CrIssNote.isSelected()) {

				CrIssNote.click();
				System.out.println("    Check now ENABLED for Create Issue Note  ");
			} else {
				System.out.println("    Check Already ENABLED for Create Issue Note    ");
			}
			Thread.sleep(2000);
			// Collapse
			ExpIssNote.click();

			// Inter Store Stock Transfer

			// Expand
			WebElement ExpandIST = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandIST"))));
			ExpandIST.click();

			// create

			WebElement CrIST = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrIST"))));
			if (!CrIST.isSelected()) {
				Thread.sleep(2000);
				CrIST.click();
				System.out.println("    Check now ENABLED for Create IST");
			} else {
				System.out.println("    Check Already ENABLED for Create IST   ");
			}
			Thread.sleep(2000);
			// View/Edit/Del

			String CreateIST[] = { "ViewIST", "AcceptIST", "PrintIST", "ExpIST" };
			for (int i = 0; i < CreateIST.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(CreateIST[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + CreateIST[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + CreateIST[i] + "]    ");
				}
			}

			// collapse IST

			ExpandIST.click();

			// QA Approval

			// Expand

			WebElement ExpandQaApp = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandQaApp"))));

			ExpandQaApp.click();

			// view/Approve

			String qaapp[] = { "ViewQaApp", "QaApp" };
			for (int i = 0; i < qaapp.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(qaapp[i]))));

				if (!element111.isSelected()) {

					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + qaapp[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + qaapp[i] + "]    ");
				}
			}

			// collapse

			ExpandQaApp.click();

			// Inventory Masters

			// Expand Inventory Master

			WebElement ExpInvMas = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpInvMas"))));
			ExpInvMas.click();

			String invmas[] = { "StoreMast", "LocMas", "PackMas", "InvSetup", "CusWareMas", "Budgeting", "InspTem" };

			for (int i = 0; i < invmas.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(invmas[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + invmas[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + invmas[i] + "]    ");
				}
			}

			// collapse Inventory Master

			ExpInvMas.click();
			// Asset Purchase Management

			WebElement ExpandAssPur = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandAssPur"))));
			ExpandAssPur.click();

			// Asset goods receipt

			// Create

			WebElement CrAssetGr = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrAssetGr"))));

			if (!CrAssetGr.isSelected()) {
				Thread.sleep(2000);
				CrAssetGr.click();
				System.out.println("    Check now ENABLED for create Asset Goods receipt   ");
			} else {
				System.out.println("    Check Already ENABLED for  Asset Goods receipt    ");

			}

			// View/Edit/Del Asset Goods Receipt

			String assetgr[] = { "ViewAssetGr", "EditAssetGr", "DelAssetGr", "ExpAssetGr", "PrintAssetGr" };

			for (int i = 0; i < assetgr.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(assetgr[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + assetgr[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + assetgr[i] + "]    ");
				}
			}

			// Asset Acquired Invoice

			WebElement CrAcqInv = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrAcqInv"))));

			if (!CrAcqInv.isSelected()) {
				Thread.sleep(2000);
				CrAcqInv.click();
				System.out.println("    Check now ENABLED for create Asset Acquired Invoice   ");
			} else {
				System.out.println("    Check Already ENABLED for  Asset Acquired Invoice    ");

			}

			// View/Edit/Del Asset Acquired Invoice

			String assetsi1[] = { "ViewAcqInv", "EditAcqInv", "DelAcqInv", "ExpAcqInv", "PrintAcqInv" };

			for (int i = 0; i < assetsi1.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(assetsi1[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + assetsi1[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + assetsi1[i] + "]    ");
				}
			}

			// Asset purchase order
			// create

			WebElement CrAssetPo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrAssetPo"))));

			if (!CrAssetPo.isSelected()) {
				Thread.sleep(2000);
				CrAssetPo.click();
				System.out.println("    Check now ENABLED for create Asset purchase order   ");
			} else {
				System.out.println("    Check Already ENABLED for  Asset purchase order    ");

			}

			// View/Edit/Del Asset purchase order

			String assetpo[] = { "ViewAssetPo", "EditAssetPo", "DelAssetPo", "ExpAssetPo", "PrintAssetPo" };

			for (int i = 0; i < assetpo.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(assetpo[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + assetpo[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + assetpo[i] + "]    ");
				}
			}

			// Asset vendor quotation

			// create

			WebElement CrAssetVq = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrAssetVq"))));

			if (!CrAssetVq.isSelected()) {
				Thread.sleep(2000);
				CrAssetVq.click();
				System.out.println("    Check now ENABLED for create Asset vendor quotation   ");
			} else {
				System.out.println("    Check Already ENABLED for  Asset vendor quotation    ");

			}

			// View/Edit/Del Asset vendor quotation

			String assetvq[] = { "ViewAssetVq", "EditAssetVq", "DelAssetVq", "ExpAssetVq", "PrintAssetVq" };

			for (int i = 0; i < assetvq.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(assetvq[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + assetvq[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + assetvq[i] + "]    ");
				}
			}

			// Collapse Asset purchase management

			ExpandAssPur.click();

			// Contract

			// expand

			WebElement ExpandContr = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandContr"))));

			ExpandContr.click();

			// create lease contract

			WebElement CrLeaseContr = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrLeaseContr"))));

			if (!CrLeaseContr.isSelected()) {
				Thread.sleep(2000);
				CrLeaseContr.click();
				System.out.println("    Check now ENABLED for create lease contract   ");
			} else {
				System.out.println("    Check Already ENABLED for Lease contract   ");

			}

			// view/ edit/Terminate/Renew

			String leascontr[] = { "ViewLeaseContr", "EditLeaseContr", "TerminateLeaseContr", "RenewLeaseContr",
					"ExpLeaseContr" };

			for (int i = 0; i < leascontr.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(leascontr[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + leascontr[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + leascontr[i] + "]    ");
				}
			}

			// create sales contract

			WebElement CrSalesContr = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrLeaseContr"))));

			if (!CrSalesContr.isSelected()) {
				Thread.sleep(2000);
				CrSalesContr.click();
				System.out.println("    Check now ENABLED for create sales contract   ");
			} else {
				System.out.println("    Check Already ENABLED for sales  contract   ");

			}

			// view/ edit/Terminate/Renew

			String salecontr[] = { "ViewSaleContr", "EditSaleContr", "TermiSaleContr", "RenewSaleContr",
					"ExpSaleContr" };

			for (int i = 0; i < salecontr.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(salecontr[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + salecontr[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + salecontr[i] + "]    ");
				}
			}

			// Collapse Contract

			ExpandContr.click();

			// Consignment Request Approval

			// expand

			WebElement ExpandConsinReq = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandConsinReq"))));
			ExpandConsinReq.click();

			// view/Approval

			String consignappr[] = { "ViewConsinReq", "AppConsinReq" };

			for (int i = 0; i < consignappr.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(consignappr[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + consignappr[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + consignappr[i] + "]    ");
				}
			}

			// Collapse

			ExpandConsinReq.click();
			// Asset purchase return

			// Expand Asset purchase return

			WebElement ExpandAssetPurret = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandAssetPurret"))));

			ExpandAssetPurret.click();

			// Create

			WebElement CrAssetPurret = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrAssetPurret"))));

			if (!CrAssetPurret.isSelected()) {
				Thread.sleep(2000);
				CrAssetPurret.click();
				System.out.println("    Check now ENABLED for Asset Purchase Return    ");
			} else {
				System.out.println("    Check Already ENABLED for Asset purhcase return   ");
			}

			// view/Edit/Delete

			String asspurret[] = { "ViewAssetPurret", "EditAssetPurret", "DelAssetPurret", "ExpAssetPurret",
					"PrintAssetPurret" };

			for (int i = 0; i < asspurret.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(asspurret[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + asspurret[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + asspurret[i] + "]    ");
				}
			}

			// collapse Asset purchase return

			ExpandAssetPurret.click();

			// Write Off and Recover Invoices

			// Expand

			WebElement ExpandRecoInv = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandRecoInv"))));

			ExpandRecoInv.click();

			Thread.sleep(2000);
			// Create

			WebElement WriteOff = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("WriteOff"))));

			if (!WriteOff.isSelected()) {
				Thread.sleep(2000);
				WriteOff.click();
				System.out.println("    Check now ENABLED for Write Off    ");
			} else {
				System.out.println("    Check Already ENABLED for Write Off   ");

			}

			// View/Recover

			String Rec[] = { "ViewWriteOff", "RecWriteOff" };

			for (int i = 0; i < Rec.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(Rec[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + Rec[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + Rec[i] + "]    ");
				}
			}

			// collapse

			ExpandRecoInv.click();

			Thread.sleep(2000);
			// loan discursement

			// Expand

			WebElement ExpandLoanDis = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandLoanDis"))));

			ExpandLoanDis.click();

			// Mana eligibility rules

			String elegrules[] = { "ElegRule", "NewRule", "DelRule", "SubmitRule", "UpdateRule" };

			for (int i = 0; i < elegrules.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(elegrules[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + elegrules[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + elegrules[i] + "]    ");
				}
			}

			// disbursement
			WebElement Disburs = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("Disburs"))));

			if (!Disburs.isSelected()) {
				Thread.sleep(2000);
				Disburs.click();
				System.out.println("    Check now ENABLED for disbursement    ");
			} else {
				System.out.println("    Check Already ENABLED for disbursement   ");

			}

			// Disbursement and Repayment Report:

			String disrepay[] = { "DisAndPay", "EditDis", "DelDis", "RepayDis" };

			for (int i = 0; i < disrepay.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(disrepay[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + disrepay[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + disrepay[i] + "]    ");
				}
			}

			// collapse disbursement

			ExpandLoanDis.click();

			Thread.sleep(2000);

			// Machine Master

			WebElement ExpandMachinemas = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandMachinemas"))));
			ExpandMachinemas.click();

			Thread.sleep(2000);

			WebElement CrMachine = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrMachine"))));

			if (!CrMachine.isSelected()) {
				Thread.sleep(2000);
				CrMachine.click();
				System.out.println("    Check now ENABLED for Machine Master    ");
			} else {
				System.out.println("    Check Already ENABLED for Machine Master    ");

			}

			// View/Edit/Del

			String machine[] = { "ViewMachine", "ModMachine", "DelMachine", "Expmachine" };

			for (int i = 0; i < machine.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(machine[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + machine[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + machine[i] + "]    ");
				}
			}

			// collapse Machine master

			ExpandMachinemas.click();
			Thread.sleep(2000);

			// Routing Template

			WebElement ExpandRouting = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandRouting"))));
			ExpandRouting.click();

			Thread.sleep(2000);

			WebElement CreRouting = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CreRouting"))));

			if (!CreRouting.isSelected()) {
				Thread.sleep(2000);
				CreRouting.click();
				System.out.println("    Check now ENABLED for Routing temaplate   ");
			} else {
				System.out.println("    Check Already ENABLED for  Routing temaplate    ");

			}

			// View/Edit/Del

			String routing[] = { "ViewRouting", "ModRouting", "DelRouting", "ExpRounting" };

			for (int i = 0; i < routing.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(routing[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + routing[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + routing[i] + "]    ");
				}
			}

			// collapse routing template

			ExpandRouting.click();
			Thread.sleep(2000);

			// Work order

			WebElement ExpandWO = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandWO"))));
			ExpandWO.click();

			Thread.sleep(2000);

			WebElement CrWo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrWo"))));

			if (!CrWo.isSelected()) {
				Thread.sleep(2000);
				CrWo.click();
				System.out.println("    Check now ENABLED for Work Order   ");
			} else {
				System.out.println("    Check Already ENABLED for Work Order   ");

			}

			// View/Edit/ Delete

			String work1[] = { "ViewWO", "ModWO", "StrtWO", "CloseWO", "DelWO", "ExpWO" };

			for (int i = 0; i < work1.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(work1[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + work1[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + work1[i] + "]    ");
				}
			}

			// Collapse Work order
			ExpandWO.click();

			Thread.sleep(2000);

			// Unit price and Amount

			WebElement ExpandUnitPrice = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandUnitPrice"))));
			ExpandUnitPrice.click();

			Thread.sleep(2000);

			WebElement UnitPur = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("UnitPur"))));

			if (!UnitPur.isSelected()) {
				Thread.sleep(2000);
				UnitPur.click();
				System.out.println("    Check now ENABLED for Display Unit Price & Amount in Purchase Document:   ");
			} else {
				System.out
						.println("    Check Already ENABLED for Display Unit Price & Amount in Purchase Document:    ");

			}

			// Display Unit Price & Amount in Sales Document:

			WebElement UnitSales = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("UnitSales"))));

			if (!UnitSales.isSelected()) {
				Thread.sleep(2000);
				UnitSales.click();
				System.out.println("    Check now ENABLED for Display Unit Price & Amount in sales Document:   ");
			} else {
				System.out.println("    Check Already ENABLED for Display Unit Price & Amount in Sales Document:    ");

			}

			// Collapse

			ExpandUnitPrice.click();

			Thread.sleep(2000);
			// Discount Master

			// Expand

			WebElement ExpandDiscMas = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandDiscMas"))));
			ExpandDiscMas.click();

			// Discount Master Menu:/ Add

			String discmas[] = { "Menu", "AddDisc" };

			for (int i = 0; i < discmas.length; i++) {
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(discmas[i]))));
				if (!element111.isSelected()) {
					Thread.sleep(2000);
					element111.click();
					System.out.println("    Check now ENABLED for [" + discmas[i] + "]    ");
				} else {
					System.out.println("    Check Already ENABLED for [" + discmas[i] + "]    ");
				}
			}

			// collapse

			ExpandDiscMas.click();

			// Save All permissions

			WebElement ApplyButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ApplyButton"))));
			ApplyButton.click();
			Thread.sleep(3000);

			// Utilities.refresh();
		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);
		}

		Thread.sleep(2000);

	}

}