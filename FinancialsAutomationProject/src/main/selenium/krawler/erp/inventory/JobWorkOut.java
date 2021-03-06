package krawler.erp.inventory;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;
import krawler.erp.utils.HandlePrintWindow;
import krawler.erp.utils.SikulliUtil;

public class JobWorkOut {

	public static void CreateJobWorkOut_Order(String documentid, String vendorid, String productid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		String jobworkoutNumber = "JWO" + documentid;
		try {
			Properties pro = Utilities.fetchProValue("OR_JobWorkOut.properties");
			Utilities.HoverandClick(pro.getProperty("ExpandJobWorkOutMenu"), driver);
			Utilities.HoverandClick(pro.getProperty("ExpandJobWorkOutEntry"), driver);
			Utilities.HoverandClick(pro.getProperty("JobWorkOutOrderMenu"), driver);
			Utilities.HoverandClick(pro.getProperty("CreateJobWorkOutOrder"), driver);
			Thread.sleep(30000);
			Utilities.clickCheckBox(pro.getProperty("includingGSTCheck"), "uncheck", driver);
			Utilities.clickExpander(driver);

			Utilities.enterTextandSelect(vendorid, pro.getProperty("vendorID"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceformat"), driver);
			Utilities.enterTextNormalBox(jobworkoutNumber, pro.getProperty("jobworkoutNumber"), driver);
			Utilities.HoverandClick(pro.getProperty("addProduct"), driver);
			Utilities.clickCheckBox(pro.getProperty("firstProduct"), "uncheck", driver);
			Utilities.enterTextNormalBox("AsB" + productid, pro.getProperty("quicksearch"), driver);
			Thread.sleep(1500);
			Utilities.clickCheckBox(pro.getProperty("firstProduct"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("AddBtn"), driver);
			try {
				WebElement button = new WebDriverWait(driver, 3)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
				if (button.isDisplayed()) {
					Thread.sleep(1000);
					button.click();
				}
			} catch (Exception Ex) {
				// handle (Please ensure all product(s) should be mapped with
				// Product Tax Class)
			}
			try {
				WebElement alert = driver.findElement(By.xpath(pro.getProperty("priceAlert")));
				if (alert.isDisplayed()) {
					Utilities.clickButton("OK", 1, driver);
				}
			} catch (Exception ex) {

			}

			int headerSize = Utilities.totalSize("//*[text()='Product ID']/ancestor::tr/td/div", driver);
			int indOfqty = 0, indOfunitprice = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver
						.findElement(By.xpath("//*[text()='Product ID']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headerName.equalsIgnoreCase("Quantity")) {
					indOfqty = i;
					System.out.println(indOfqty);
				}
				String header1Name = driver
						.findElement(By.xpath("//*[text()='Product ID']/ancestor::tr/td[" + i + "]/div"))
						.getAttribute("innerText");
				if (header1Name.equalsIgnoreCase("Unit Price")) {
					indOfunitprice = i;
					System.out.println(indOfunitprice);
				}
			}

			// add qty
			Utilities.HoverandClick("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[1]//td["
					+ indOfqty + "]/div", driver);
			Utilities.enter_LinelabelAmount("10", driver);
			// scroll
			Utilities.justHover(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[contains(@class,'x-grid3-cell-last')]",
					driver);
			// add Price
			Utilities.HoverandClick("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[1]//td["
					+ indOfunitprice + "]/div", driver);
			Utilities.enter_LinelabelAmount("10", driver);

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("closeJobWorkoutorder"), driver);
			Utilities.HoverandClick(pro.getProperty("closeJobWorkoutorderMenu"), driver);
			System.out.println("** Job Work Order [" + jobworkoutNumber + "] successfully Created for Vendor - [ "
					+ vendorid + " ] !!! ");
		} catch (Exception Ex) {
			System.out.println("** Job Work Order [" + jobworkoutNumber + "] FAILED to Create !!! ");
			Utilities.handleError(Ex, driver);
		}
	}

	// **************************** Add Job Work Out Stock Transfer
	// ***************************************************
	public static void CreateJobWorkOut_StockTransfer(String documentid, String vendorid, String productid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		String jobworkoutNumber = "JWOST" + documentid;
		try {
			Properties pro = Utilities.fetchProValue("OR_JobWorkOut.properties");
			Utilities.HoverandClick(pro.getProperty("ExpandJobWorkOutMenu"), driver);
			Utilities.HoverandClick(pro.getProperty("ExpandJobWorkOutEntry"), driver);
			Utilities.HoverandClick(pro.getProperty("JobWorkStockTranRegMenu"), driver);
			Utilities.HoverandClick(pro.getProperty("AddJobWorkStockTran"), driver);
			Thread.sleep(30000);

			Utilities.clickExpander(driver);

			Utilities.enterTextandSelect("DS - Default Store", pro.getProperty("fromStore"), driver);
			// Utilities.enterTextandSelect("JOB WORK ORDER - Job Work Order",
			// pro.getProperty("toStore"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("StockTransferVendorid"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("seqFormatStockRegister"), driver);
			Utilities.enterTextNormalBox(jobworkoutNumber, pro.getProperty("StockRegisterOutNumber"), driver);
			Utilities.HoverandClick(pro.getProperty("closeStockTransferRegisterMenu"), driver);
			// link Order
			Utilities.enterTextandSelect("JWO" + documentid, pro.getProperty("JobWorkOutOrderLink"), driver);
			Utilities.HoverandClick("//label[contains(text(),'Job Work Out Order')]/following::div[1]/div/img", driver);
			Utilities.HoverandClick(pro.getProperty("JobStockTraRegMemo"), driver);
			Utilities.enterTextNormalBox("Challan" + documentid, pro.getProperty("challanNumber"), driver);

			int header = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[1]/table/thead/tr/td/div", driver);
			int indOfproid = 0, indOfqty = 0;
			for (int i = 1; i <= header; i++) {
				String headerName = driver
						.findElement(
								By.xpath("//*[text()='Product ID']/ancestor::div[1]/table/thead/tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equals("Product ID")) {
					indOfproid = i;
				}
				if (headerName.equals("Quantity (in Transfer UoM)")) {
					indOfqty = i;
				}
			}
			int totalProduct = Utilities
					.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div", driver);
			int indOfA1 = 0, indOfA2 = 0, indOfA3 = 0, sizeOfStockDetails = 0;
			for (int i = 1; i < totalProduct; i++) {
				String prodName = driver
						.findElement(By.xpath("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + i
								+ "]/table/tbody/tr/td[" + indOfproid + "]/div"))
						.getText();
				if (prodName.endsWith("A1")) {
					indOfA1 = i;
				}
				if (prodName.endsWith("A2")) {
					indOfA2 = i;
				}
				if (prodName.endsWith("A3")) {
					indOfA3 = i;
				}
			}

			// update A1
			Utilities.HoverandClick("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + indOfA1
					+ "]/table/tbody/tr/td[" + indOfqty + "]/div", driver);
			Utilities.enter_LinelabelAmount("7", driver);
			Utilities.HoverandClick(pro.getProperty("JobStockTraRegMemo"), driver);
			Utilities.HoverandClick("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + indOfA1
					+ "]/table/tbody/tr//*[@class='pwnd serialNo-gridrow']", driver);

			sizeOfStockDetails = Utilities.totalSize("//*[text()='Issue Location ']/ancestor::tr/td/div", driver);
			for (int j = 1; j <= sizeOfStockDetails; j++) {
				String heName = driver
						.findElement(By.xpath("//*[text()='Issue Location ']/ancestor::tr/td[" + j + "]/div"))
						.getText();
				if (heName.equalsIgnoreCase("Quantity*")) {
					Utilities.HoverandClick(
							"//*[text()='Issue Location ']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td["
									+ j + "]/div",
							driver);
					Utilities.enter_LinelabelAmount("7", driver);
					Utilities.HoverandClick(pro.getProperty("saveStockWindow"), driver);
					break;
				}
			}

			// Update A2
			Utilities.HoverandClick("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + indOfA2
					+ "]/table/tbody/tr/td[" + indOfqty + "]/div", driver);
			Utilities.enter_LinelabelAmount("14", driver);
			Utilities.HoverandClick(pro.getProperty("JobStockTraRegMemo"), driver);
			Utilities.HoverandClick("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + indOfA2
					+ "]/table/tbody/tr//*[@class='pwnd serialNo-gridrow']", driver);

			sizeOfStockDetails = Utilities.totalSize("//*[text()='Issue Location ']/ancestor::tr/td/div", driver);
			for (int j = 1; j <= sizeOfStockDetails; j++) {
				String heName = driver
						.findElement(By.xpath("//*[text()='Issue Location ']/ancestor::tr/td[" + j + "]/div"))
						.getText();
				if (heName.equalsIgnoreCase("Quantity*")) {
					Utilities.HoverandClick(
							"//*[text()='Issue Location ']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td["
									+ j + "]/div",
							driver);
					Utilities.enter_LinelabelAmount("14", driver);
					Utilities.HoverandClick(pro.getProperty("saveStockWindow"), driver);
					break;
				}
			}

			// Update A3
			Utilities.HoverandClick("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + indOfA3
					+ "]/table/tbody/tr/td[" + indOfqty + "]/div", driver);
			Utilities.enter_LinelabelAmount("21", driver);
			Utilities.HoverandClick(pro.getProperty("JobStockTraRegMemo"), driver);
			Utilities.HoverandClick("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + indOfA3
					+ "]/table/tbody/tr//*[@class='pwnd serialNo-gridrow']", driver);

			sizeOfStockDetails = Utilities.totalSize("//*[text()='Issue Location ']/ancestor::tr/td/div", driver);
			for (int j = 1; j <= sizeOfStockDetails; j++) {
				String heName = driver
						.findElement(By.xpath("//*[text()='Issue Location ']/ancestor::tr/td[" + j + "]/div"))
						.getText();
				if (heName.equalsIgnoreCase("Quantity*")) {
					Utilities.HoverandClick(
							"//*[text()='Issue Location ']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td["
									+ j + "]/div",
							driver);
					Utilities.enter_LinelabelAmount("21", driver);
					Utilities.HoverandClick(pro.getProperty("saveStockWindow"), driver);
					break;
				}
			}

			String parentWindow = driver.getWindowHandle();
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			HandlePrintWindow.closePrintWindow(parentWindow, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("closeJobWorkStockTransfer"), driver);

			System.out.println("** Job Work Out Stock Transfer [" + jobworkoutNumber + "] Created Suceesfully !!! ");
			verify_StockTransfer(documentid, vendorid, productid, driver);

		} catch (Exception Ex) {
			System.out.println("** Job Work Out Stock Transfer [" + jobworkoutNumber + "] FAILED to Create !!! ");
			Utilities.handleError(Ex, driver);
		}
	}

	// **************************** Verify Job Work Out Stock Transfer
	// ***************************************************
	public static void verify_StockTransfer(String documentid, String vendorid, String productid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_JobWorkOut.properties");
			InvUtilities.enableExpander(driver);
			Utilities.HoverandClick(pro.getProperty("JobWorkStockTranRegMenu"), driver);
			Utilities.clickCheckBox(pro.getProperty("firstItemCheck"), "uncheck", driver);
			Thread.sleep(2000);
			Utilities.clickExpander(driver);
			Utilities.clickCheckBox(pro.getProperty("firstItemCheck"), "uncheck", driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("quickSearchStockTransfer"), driver);
			Utilities.HoverandClick(pro.getProperty("SearchBtn"), driver);
			Utilities.clickCheckBox(pro.getProperty("firstItemCheck"), "uncheck", driver);

			int headerSize = Utilities.totalSize("//*[text()='Vendor Name']/ancestor::tr/td/div", driver);
			int indOfprd = 0, indOfbal = 0, indOfstatus = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver
						.findElement(By.xpath("//*[text()='Vendor Name']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headerName.equalsIgnoreCase("Product Name")) {
					indOfprd = i;
				}
				if (headerName.equalsIgnoreCase("Collected Quantity")) {
					indOfbal = i;
				}
				if (headerName.equalsIgnoreCase("Status")) {
					indOfstatus = i;
				}
			}

			int totalPrd = Utilities.totalSize(
					"//*[text()='Vendor Name']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div", driver);
			for (int j = 1; j <= totalPrd; j++) {
				String prdName = null;
				String balanceQty = null;
				String status = null;
				prdName = driver.findElement(
						By.xpath("//*[text()='Vendor Name']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfprd + "]/div"))
						.getText();
				balanceQty = driver.findElement(
						By.xpath("//*[text()='Vendor Name']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfbal + "]/div"))
						.getText();
				status = driver.findElement(
						By.xpath("//*[text()='Vendor Name']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfstatus + "]/div"))
						.getText();

				assertEquals(status, "Accepted");
				System.out.println("Job Work Out Stock transfer Report verified for product [" + prdName
						+ "] with Qauntity [" + balanceQty + "] & status [" + status + "] !!");
			}
			Utilities.HoverandClick(pro.getProperty("closeStockTransferRegisterMenu"), driver);
		} catch (Exception Ex) {
			System.out.println("** FAILED to verify Stock Report [" + documentid + "] for Job Work out  !!! ");
			Utilities.handleError(Ex, driver);
		}
	}

	// **************************** Add Job Work Out Goods Receipt
	// ***************************************************
	public static void CreateJobWorkOut_GoodsReceipt(String documentid, String vendorid, String productid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		String GoodsReceiptNumber = "JwGR" + documentid;
		try {
			Properties pro = Utilities.fetchProValue("OR_JobWorkOut.properties");
			Utilities.HoverandClick(pro.getProperty("ExpandJobWorkOutMenu"), driver);
			Utilities.HoverandClick(pro.getProperty("ExpandJobWorkOutEntry"), driver);
			Utilities.HoverandClick(pro.getProperty("JobWorkGoodsReceiptMenu"), driver);
			Utilities.click_element(pro.getProperty("CreateJobWorkGoodsReceipt"), driver);
			Thread.sleep(30000);
			Utilities.clickExpander(driver);

			Utilities.enterTextandSelect("NA", pro.getProperty("seqFormatGoodsReceipt"), driver);
			Utilities.enterTextNormalBox(GoodsReceiptNumber, pro.getProperty("GoodsReceiptOrderNumber"), driver);
			Utilities.HoverandClick(pro.getProperty("closeGoodsReceiptOrderMenu"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("GoodsReceiptVendorid"), driver);

			// link
			Utilities.enterTextandSelect("Yes", pro.getProperty("isLink"), driver);
			Utilities.enterTextandSelect("Job Work Out Order", pro.getProperty("LinkTO"), driver);
			Utilities.HoverandClick(pro.getProperty("dropdownForDocument"), driver);
			Utilities.enterTextandSelect("JWO" + documentid, pro.getProperty("linkDocumentNumber"), driver);
			Utilities.HoverandClick(pro.getProperty("dropdownForDocument"), driver);
			Utilities.HoverandClick(pro.getProperty("GoodsReceiptMemo"), driver);
			// tocheck Linelabel loaded
			Utilities.clickCheckBox(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[1]/table//*[contains(text(),'Assembly Product')]",
					"uncheck", driver);

			int headerSize = Utilities.totalSize("//*[text()='Product ID']/ancestor::tr/td/div", driver);
			int indOfact = 0, indOfrec = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver
						.findElement(By.xpath("//*[text()='Product ID']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headerName.equalsIgnoreCase("Actual Quantity")) {
					indOfact = i;
				}
				if (headerName.equalsIgnoreCase("Received Quantity")) {
					indOfrec = i;
				}
			}
			// Updateqty
			Utilities.HoverandClick(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td["
							+ indOfrec + "]/div",
					driver);
			Utilities.enter_LinelabelAmount("7", driver);

			// open window
			Utilities.doubleClick(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody//*[@class='pwnd serialNo-gridrow']",
					driver);
			Thread.sleep(1000);
			Utilities.HoverandClick(
					"//*[text()='Import Batch Serial Details']/ancestor::tbody[2]/tr/td//*[text()='Submit']", driver);
			Thread.sleep(1000);

			// to scroll till end
			Utilities.justHover(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[contains(@class,'x-grid3-cell-last')]",
					driver);
			// add BOM details
			Utilities.click_element(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr//*[@class='pwnd doDetails-gridrow']",
					driver);
			Thread.sleep(2000);
			int challanBoxHeader = Utilities.totalSize(
					"//*[@class='x-window' and contains(@style,'visible')]//*[text()='BOM quantity']/ancestor::tr/td/div",
					driver);
			int indOfQty = 0, indOfavaQty = 0;

			for (int j = 1; j <= challanBoxHeader; j++) {
				String headerName = driver.findElement(By
						.xpath("//*[@class='x-window' and contains(@style,'visible')]//*[text()='BOM quantity']/ancestor::tr/td["
								+ j + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Quantity")) {
					indOfQty = j;
				}
				if (headerName.equalsIgnoreCase("Available Quantity")) {
					indOfavaQty = j;
				}
			}
			for (int i = 1; i <= 3; i++) {
				String sendQty = null;
				sendQty = driver.findElement(By
						.xpath("//*[@class='x-window' and contains(@style,'visible')]//*[text()='BOM quantity']/ancestor::div[3]/following::div[1]/div/div["
								+ i + "]/table/tbody/tr/td[" + indOfavaQty + "]/div"))
						.getText();
				Utilities.HoverandClick(
						"//*[@class='x-window' and contains(@style,'visible')]//*[text()='BOM quantity']/ancestor::div[3]/following::div[1]/div/div["
								+ i + "]/table/tbody/tr/td[" + indOfQty + "]/div",
						driver);
				Utilities.enter_LinelabelAmount(sendQty, driver);
			}
			Utilities.clickButton("Submit", 1500, driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("No", 500, driver); // QA flow
			Utilities.clickButton("OK", 500, driver);
			Utilities.HoverandClick(pro.getProperty("closeJobWorkGoodsReceiptOrder"), driver);
			System.out.println("** Job Work Out Goods Receipt [" + GoodsReceiptNumber + "] is Created !!! ");

			Verify_JobWorkGoodsReceipt(documentid, vendorid, productid, driver);

		} catch (Exception Ex) {
			System.out.println("** Job Work Out Goods Receipt [" + GoodsReceiptNumber + "] FAILED to Create !!! ");
			Utilities.handleError(Ex, driver);
		}
	}

	// **************************** Verify Job Work Out GoodsReceipt
	// ***************************************************
	public static void Verify_JobWorkGoodsReceipt(String documentid, String vendorid, String productid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_JobWorkOut.properties");
			InvUtilities.enableExpander(driver);
			Utilities.HoverandClick(pro.getProperty("JobWorkGoodsReceiptMenu"), driver);
			Thread.sleep(2000);
			Utilities.clickExpander(driver);
			Utilities.clickCheckBox(pro.getProperty("firstItemCheckGRreport"), "uncheck", driver);
			Utilities.enterTextNormalBox("JwGR" + documentid, pro.getProperty("quickSearchGoodsReceiptOrder"), driver);
			Utilities.clickButton("Fetch", 1000, driver);
			Utilities.clickCheckBox(pro.getProperty("firstItemCheckGRreport"), "check", driver);

			String finalPrice = driver
					.findElement(By
							.xpath("//*[text()='Goods Receipt No']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr[1]//td[31]/div"))
					.getText();
			System.out.println("**Verified JobWork Goods Receipt Order for product [" + productid + "] with Price ["
					+ finalPrice + "] !! ");
			Utilities.HoverandClick(pro.getProperty("closeGoodsReceiptOrderMenu"), driver);
		} catch (Exception Ex) {
			System.out.println("** FAILED to verify Goods Receipt [" + documentid + "] for Job Work out  !!! ");
			Utilities.handleError(Ex, driver);
		}
	}

	// **************************** Job Work Out Ageing Report
	// ***************************************************
	public static void JobWorkOut_AgeingReport(String documentid, String vendorid, String productid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_JobWorkOut.properties");
			Utilities.HoverandClick(pro.getProperty("ExpandJobWorkOutMenu"), driver);
			Utilities.HoverandClick(pro.getProperty("ExpandJobWorkOutReport"), driver);
			Utilities.HoverandClick(pro.getProperty("JobWorkAgeingReport"), driver);
			Thread.sleep(2000);
			Utilities.clickExpander(driver);
			Utilities.clickCheckBox(pro.getProperty("firstItemCheck"), "uncheck", driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("reportsQuickSearch"), driver);
			Utilities.HoverandClick(pro.getProperty("SearchBtn"), driver);
			Utilities.clickCheckBox(pro.getProperty("firstItemCheck"), "uncheck", driver);

			int headerSize = Utilities.totalSize("//*[text()='Vendor Name']/ancestor::tr/td/div", driver);
			int indOfprd = 0, indOfbal = 0, indOfstatus = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver
						.findElement(By.xpath("//*[text()='Vendor Name']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headerName.equalsIgnoreCase("Product Name")) {
					indOfprd = i;
				}
				if (headerName.equalsIgnoreCase("Balance Quantity")) {
					indOfbal = i;
				}
				if (headerName.equalsIgnoreCase("Status")) {
					indOfstatus = i;
				}
			}

			int totalPrd = Utilities.totalSize(
					"//*[text()='Vendor Name']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div", driver);
			for (int j = 1; j <= totalPrd; j++) {
				String prdName = null;
				String balanceQty = null;
				String status = null;
				prdName = driver.findElement(
						By.xpath("//*[text()='Vendor Name']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfprd + "]/div"))
						.getText();
				balanceQty = driver.findElement(
						By.xpath("//*[text()='Vendor Name']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfbal + "]/div"))
						.getText();
				status = driver.findElement(
						By.xpath("//*[text()='Vendor Name']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfstatus + "]/div"))
						.getText();

				assertEquals(status, "Open");
				System.out.println("Job Work Out Report verified for product [" + prdName + "] with Qauntity ["
						+ balanceQty + "] & status [" + status + "] !!");
			}
			Utilities.HoverandClick(pro.getProperty("closeJobWorkAgeingReport"), driver);
		} catch (Exception Ex) {
			System.out.println("** Job Work Out report [" + documentid + "] FAILED to VERIFY !!! ");
			Utilities.handleError(Ex, driver);
		}
	}

	// **************************** Add Job Work Out Invoice Credit
	// ***************************************************
	public static void CreateJobWorkOut_Invoice(String documentid, String vendorid, String productid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		String InvoiceNumber = "JWCrP" + documentid;
		try {
			Properties pro = Utilities.fetchProValue("OR_JobWorkOut.properties");
			Utilities.HoverandClick(pro.getProperty("ExpandJobWorkOutMenu"), driver);
			Utilities.HoverandClick(pro.getProperty("ExpandJobWorkOutEntry"), driver);
			Utilities.HoverandClick(pro.getProperty("JobWorkPurchaseMenu"), driver);
			Utilities.click_element(pro.getProperty("CreateJobWorkPurchase"), driver);
			// Utilities.clickCheckBox(pro.getProperty("SelectCreditPurchase"),
			// "check", driver); //----In future if Credit Purchase not selected
			Utilities.clickButton("Submit", 500, driver);
			Thread.sleep(30000);
			Utilities.clickExpander(driver);
			Utilities.click_element(pro.getProperty("closejobworkoutPurchaseMenu"), driver);
			Utilities.enterTextandSelect(vendorid, pro.getProperty("jobworkPurchaseVendorid"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceformat"), driver);
			Utilities.enterTextNormalBox(InvoiceNumber, pro.getProperty("jobworkoutPurchaseNumber"), driver);

			// link
			Utilities.enterTextandSelect("Yes", pro.getProperty("isLink"), driver);
			Utilities.enterTextandSelect("Goods Receipt", pro.getProperty("LinkTO"), driver);
			Utilities.HoverandClick(pro.getProperty("dropdownForDocument"), driver);
			Utilities.enterTextandSelect("JwGR" + documentid, pro.getProperty("linkDocumentNumber"), driver);
			Utilities.HoverandClick(pro.getProperty("dropdownForDocument"), driver);
			Utilities.HoverandClick(pro.getProperty("GoodsReceiptMemo"), driver);
			// tocheck Linelabel loaded
			Utilities.clickCheckBox(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[1]/table//*[contains(text(),'Assembly Product')]",
					"uncheck", driver);

			Utilities.enterTextandSelect("No", "//*[@id='includetax']/following::input[1]", driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("closeJobWorkPIOrder"), driver);
			System.out.println("** Job Work Out Purchase Invoice [" + InvoiceNumber + "] is Created !!! ");

			Verify_JobWorkPurchaseInvoice(documentid, vendorid, productid, driver);

		} catch (Exception Ex) {
			System.out.println("** Job Work Out Purchase Invoice [" + InvoiceNumber + "] FAILED to Create !!! ");
			Utilities.handleError(Ex, driver);
		}
	}

	// **************************** Verify Job Work Out Purchase Invoice
	// ***************************************************
	public static void Verify_JobWorkPurchaseInvoice(String documentid, String vendorid, String productid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_JobWorkOut.properties");
			InvUtilities.enableExpander(driver);
			Utilities.HoverandClick(pro.getProperty("JobWorkPurchaseMenu"), driver);
			Thread.sleep(2000);
			Utilities.clickExpander(driver);
			Utilities.clickCheckBox(pro.getProperty("firstItemCheckPIreport"), "uncheck", driver);
			Utilities.enterTextNormalBox("JWCrP" + documentid, pro.getProperty("quickSearchPIjobWork"), driver);
			Utilities.clickButton("Fetch", 1000, driver);
			Utilities.clickCheckBox(pro.getProperty("firstItemCheckPIreport"), "check", driver);

			String finalPrice = driver
					.findElement(By
							.xpath("//*[text()='Purchase Invoice No']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr[1]//td[31]/div"))
					.getText();
			System.out.println("**Verified JobWork PurchaseInvoice for product [" + productid + "] with total Amount ["
					+ finalPrice + "] !! ");
			Utilities.HoverandClick(pro.getProperty("closejobworkoutPurchaseMenu"), driver);
		} catch (Exception Ex) {
			System.out.println("** FAILED to verify PurchaseInvoice [" + documentid + "] for Job Work out  !!! ");
			Utilities.handleError(Ex, driver);
		}
	}

}
