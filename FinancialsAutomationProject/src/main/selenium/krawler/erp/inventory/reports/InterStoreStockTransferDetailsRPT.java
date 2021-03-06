package krawler.erp.inventory.reports;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import com.google.common.base.Function;

import krawler.erp.inventory.InvUtilities;
import krawler.erp.page.Utilities;

public class InterStoreStockTransferDetailsRPT {

	// ************************************************ Inter Store stock
	// TransferDetails Report
	// ********************************************************
	public static void validate_InterStoreTransferDetails(String documentid, String productid, String fromStore,
			String toStore, String IssuesQty, String DeliveredQty, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			final Properties pro = Utilities.fetchProValue("OR_InterLocationTransferDetailsReport.properties");
			documentid = "ISST" + documentid;

			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandReport(driver);
			Utilities.HoverandClick(pro.getProperty("InterLocationTransferDetailsReportLink"), driver);
			Utilities.HoverandClick(pro.getProperty("StoreTransferHistoryBtn"), driver);
			Utilities.disableExpander(driver);

			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(
					"//*[contains(@id,'interstoretransferRequest')]//*[text()='To Store']/ancestor::div[3]/following::div[1]/div/div[1]/div[1]/div",
					"uncheck", driver);

			int headerSize = Utilities.totalSize(
					"//*[contains(@id,'interstoretransferRequest')]//*[text()='To Store']/ancestor::tr/td/div", driver);
			int indOfFrmStore = 0, indOftoStore = 0, indOfissuQty = 0, indOfCollect = 0, indOfprd = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver.findElement(
						By.xpath("//*[contains(@id,'interstoretransferRequest')]//*[text()='To Store']/ancestor::tr/td["
								+ i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("From Store")) {
					indOfFrmStore = i;
				}
				if (headerName.equalsIgnoreCase("To Store")) {
					indOftoStore = i;
				}
				if (headerName.equalsIgnoreCase("Issued Quantity")) {
					indOfissuQty = i;
				}
				if (headerName.equalsIgnoreCase("Collected Quantity")) {
					indOfCollect = i;
				}
				if (headerName.equalsIgnoreCase("Product ID")) {
					indOfprd = i;
				}
			}

			int totalProducts = Utilities.totalSize(
					"//*[contains(@id,'interstoretransferRequest')]//*[text()='To Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div",
					driver);
			boolean flag = true;
			for (int j = 1; j <= totalProducts; j++) {
				String ProdID = null;
				String actualFromStore = null;
				String actualToStore = null;
				String actualIssuedQuantity = null;
				String actualDeliveredQuantity = null;

				ProdID = driver.findElement(By
						.xpath("//*[contains(@id,'interstoretransferRequest')]//*[text()='To Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfprd + "]/div"))
						.getText();
				actualFromStore = driver.findElement(By
						.xpath("//*[contains(@id,'interstoretransferRequest')]//*[text()='To Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfFrmStore + "]/div"))
						.getText();
				actualToStore = driver.findElement(By
						.xpath("//*[contains(@id,'interstoretransferRequest')]//*[text()='To Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOftoStore + "]/div"))
						.getText();
				actualIssuedQuantity = driver.findElement(By
						.xpath("//*[contains(@id,'interstoretransferRequest')]//*[text()='To Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfissuQty + "]/div"))
						.getText();
				actualDeliveredQuantity = driver.findElement(By
						.xpath("//*[contains(@id,'interstoretransferRequest')]//*[text()='To Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfCollect + "]/div"))
						.getText();

				if (actualFromStore.equalsIgnoreCase(fromStore) && actualToStore.equalsIgnoreCase(toStore)
						&& actualIssuedQuantity.equalsIgnoreCase(IssuesQty)
						&& actualDeliveredQuantity.equalsIgnoreCase(DeliveredQty)) {
					System.out.println("**** Verified Inter Store Stock Transfer for [" + documentid + "] with ["
							+ ProdID + "] [" + actualFromStore + "] [" + actualToStore + "] [" + actualIssuedQuantity
							+ "] [" + actualDeliveredQuantity + "] **********");
				} else {
					System.out.println("!!!!!!! FAILED to verify Inter Store Stock Transfer for [" + documentid
							+ "] with [" + ProdID + "] [" + actualFromStore + "] [" + actualToStore + "] ["
							+ actualIssuedQuantity + "] [" + actualDeliveredQuantity + "] !!!!!!! ");
					System.out.println("!!!!!!! with EXPECTED DATA AS -------------- > [" + documentid + "] with ["
							+ ProdID + "] [" + fromStore + "] [" + toStore + "] [" + IssuesQty + "] [" + DeliveredQty
							+ "] !!!!!!! ");
					flag = false;
				}
			}

			if (flag == false) {
				System.out.println("!!!!!!!!!!!!!! Failed to verify Inter Store Stock Transfer [" + documentid
						+ "] !!!!!!!!!!!!!!!!!!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			Utilities.HoverandClick(pro.getProperty("InterLocationTransferDetailsClsBtn"), driver);
			System.out.println("************** VERIFIED Inter Store Stock Transfer [" + documentid
					+ "] successfully **************");

		} catch (Exception EX) {
			System.out.println("******************* Document Inter Store Stock Transfer is NOT VERIFIED for : ["
					+ documentid + "] check LOG! ***************");
			Utilities.handleError(EX, driver);
		}
	}
}
