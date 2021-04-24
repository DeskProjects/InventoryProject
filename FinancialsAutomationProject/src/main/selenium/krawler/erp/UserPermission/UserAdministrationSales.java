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

public class UserAdministrationSales {

	public static void UserPermission_ForSales(WebDriver driver)
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
			//
			// UserADlink.click();
			// Thread.sleep(1000);
			//
			// // Selection of user
			//
			// WebElement UserSel =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("Smithradio"))));
			// UserSel.isEnabled();
			// UserSel.click();
			// Thread.sleep(2000);
			//
			// // Click on assign permissions
			//
			// WebElement assignper =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("assignpermissionbtn"))));
			// assignper.click();
			// Thread.sleep(1000);
			//

			// Expand sales and billing management

			WebElement SalesExp = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("SaleManagementExp"))));
			SalesExp.click();
			Thread.sleep(1000);

			WebElement SaleCQ = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrCQ"))));

			if (!SaleCQ.isSelected()) {
				SaleCQ.click();

			} else {
				System.out.println("sales management create CQ checked box is already selected");

			}

			Thread.sleep(1000);

			// Assign permission for - View customer quotation report

			WebElement ViewCq = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ViewCQ"))));

			if (!ViewCq.isSelected()) {
				ViewCq.click();
			} else {
				System.out.println("View Customer Quotation report permission is assigned already");

			}
			Thread.sleep(1000);

			// Permission to create SO

			WebElement CrSO = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrSO"))));
			Thread.sleep(1000);

			if (!CrSO.isSelected()) {

				CrSO.click();
			}

			else {

				System.out.println("Create sales order permission is assigned already");

			}

			Thread.sleep(2000);

			// permission to view SO report to user

			WebElement ViewSO = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ViewSO"))));

			if (!ViewSO.isSelected()) {

				ViewSO.click();

			}

			else {

				System.out.println("View sales order permission is assigned already");

			}

			// collapse sales and billing management

			WebElement SalesCollapse = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("SaleManagementExp"))));

			SalesCollapse.click();

			Thread.sleep(2000);

			// Expand Delivery order

			WebElement DOExp = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("DOExpand"))));

			DOExp.click();

			// Create DO

			WebElement DOCre = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CreateDo"))));
			if (!DOCre.isSelected()) {
				DOCre.click();

			} else {
				System.out.println("Permission for Create Do is already Given");
			}

			// View DO

			WebElement DOView = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ViewDO"))));

			if (!DOView.isSelected()) {

				DOView.click();

			}

			else {

				System.out.println("View DO permission is already Given");

			}

			DOExp.click();
			Thread.sleep(2000);

			// permission to view credit note
			WebElement element1 = null;

			WebElement ExpandCn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CnExpand"))));

			ExpandCn.click();

			Thread.sleep(2000);

			// Import CN

			WebElement CNImp = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ImportCn"))));

			if (!CNImp.isSelected()) {
				CNImp.click();

			} else {
				System.out.println("Import CN is already selected.");

				Thread.sleep(2000);

				// create CN

				WebElement CnCreate = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CreateCn"))));

				if (!CnCreate.isSelected()) {

					CnCreate.click();
				} else {
					System.out.println("Create Cn check is already selected.");

				}

				// view/edit/del/export

				WebElement element11 = null;

				String arr2[] = { "ViewCn", "EditCn", "DelCn", "ExpCn" };

				for (int i = 0; i < arr2.length; i++) {
					element11 = new WebDriverWait(driver, 30)
							.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(arr2[i]))));

					if (!element11.isSelected())

					{

						Thread.sleep(2000);

						element11.click();

						System.out.println("    Check now ENABLED for [" + arr2[i] + "]    ");

					}

					else {

						System.out.println("    Check Already ENABLED for [" + arr2[i] + "]    ");
					}
				}

				// Receive payment

				// Expand

				WebElement ExpandReceivePay = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandReceivePay"))));

				ExpandReceivePay.click();

				// import

				WebElement ImportReceivePay = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ImportReceivePay"))));

				if (!ImportReceivePay.isSelected()) {
					ImportReceivePay.click();
				} else {
					System.out.println("Check box for Import receive payment is already selected");
				}

				// create

				WebElement CreateReceivePay = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CreateReceivePay"))));

				if (!CreateReceivePay.isSelected()) {
					CreateReceivePay.click();
				} else {
					System.out.println("Check box for Create receive payment is already selected");
				}

				// View
				WebElement element111 = null;

				String receivepay[] = { "ViewReceivePay", "ModifyReceivePay", "copyReceivePay", "DelReceivePay",
						"EmailReceivePay", "ExportReceivePay", "printReceivePay" };

				for (int i = 0; i < receivepay.length; i++) {
					element111 = new WebDriverWait(driver, 30)
							.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(receivepay[i]))));
					if (!element111.isSelected()) {
						Thread.sleep(2000);
						element111.click();
						System.out.println("    Check now ENABLED for [" + receivepay[i] + "]    ");
					} else {
						System.out.println("    Check Already ENABLED for [" + receivepay[i] + "]    ");
					}
				}

				Thread.sleep(2000);

				ExpandReceivePay.click();

			}

			Thread.sleep(2000);

			ExpandCn.click();

			// Cn1Expand

			// WebElement
			// Cn1Expand=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("Cn1Expand"))));

			// Cn1Expand.click();

			// Sales return

			// Expand Sales retun

			WebElement ExpandSalesaRet = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("ExpandSalesaRet"))));

			ExpandSalesaRet.click();

			// create Sales return

			WebElement CrSalesaRet = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty("CrSalesaRet"))));
			if (!CrSalesaRet.isSelected()) {
				Thread.sleep(2000);
				CrSalesaRet.click();

			} else {

				System.out.println("    Check Already ENABLED for Sales Retun   ");

			}

			// View/Edit/Delete/Copy
			WebElement element111 = null;

			String SalesRet[] = { "ViewSalesaRet", "EditSalesaRet", "DelSalesaRet", "ExpSalesaRet", "PrintSalesaRet",
					"CpySalesaRet", "UnlSalesaRet" };

			for (int i = 0; i < SalesRet.length; i++)

			{
				element111 = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(UP.getProperty(SalesRet[i]))));
				if (!element111.isSelected()) {

					Thread.sleep(2000);

					element111.click();

					System.out.println("    Check now ENABLED for [" + SalesRet[i] + "]    ");

				}

				else {
					System.out.println("    Check Already ENABLED for [" + SalesRet[i] + "]    ");

				}
			}
			ExpandSalesaRet.click();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);
		}

		Thread.sleep(2000);

	}

}
