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

public class InterLocationStockTransferDetailsReport {

	public static void validate_InterLocationTransferDetails(String documentid, String productid, String fromStore,
			String fromLocation, String toLocation, String DeliveredQty, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			final Properties pro = Utilities.fetchProValue("OR_InterLocationStock.properties");
			documentid = "ILST" + documentid;

			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandReport(driver);
			Utilities.HoverandClick(pro.getProperty("InterLocationTransferDetailsReportLink"), driver);
			Utilities.disableExpander(driver);

			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.clickCheckBox(
					"//*[contains(@id,'interLocationTransferReportTab')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div/div[1]/div[1]/div",
					"uncheck", driver);

			int headerSize = Utilities.totalSize(
					"//*[contains(@id,'interLocationTransferReportTab')]//*[text()='Store']/ancestor::tr/td/div",
					driver);
			int indOfFrmStore = 0, indOfFrmLoc = 0, indOftoLoc = 0, indOfQty = 0, indOfprd = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver.findElement(By
						.xpath("//*[contains(@id,'interLocationTransferReportTab')]//*[text()='Store']/ancestor::tr/td["
								+ i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Store")) {
					indOfFrmStore = i;
				}
				if (headerName.equalsIgnoreCase("From Location")) {
					indOfFrmLoc = i;
				}
				if (headerName.equalsIgnoreCase("To Location")) {
					indOftoLoc = i;
				}
				if (headerName.equalsIgnoreCase("Quantity")) {
					indOfQty = i;
				}
				if (headerName.equalsIgnoreCase("Product ID")) {
					indOfprd = i;
				}
			}

			int totalProducts = Utilities.totalSize(
					"//*[contains(@id,'interLocationTransferReportTab')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div",
					driver);
			boolean flag = true;
			for (int j = 1; j <= totalProducts; j++) {
				String ProdID = null;
				String actualFromStore = null;
				String actualFromLoc = null;
				String actualToLoc = null;
				String actualQuantity = null;

				ProdID = driver.findElement(By
						.xpath("//*[contains(@id,'interLocationTransferReportTab')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfprd + "]/div"))
						.getText();
				actualFromStore = driver.findElement(By
						.xpath("//*[contains(@id,'interLocationTransferReportTab')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfFrmStore + "]/div"))
						.getText();
				actualFromLoc = driver.findElement(By
						.xpath("//*[contains(@id,'interLocationTransferReportTab')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfFrmLoc + "]/div"))
						.getText();
				actualToLoc = driver.findElement(By
						.xpath("//*[contains(@id,'interLocationTransferReportTab')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOftoLoc + "]/div"))
						.getText();
				actualQuantity = driver.findElement(By
						.xpath("//*[contains(@id,'interLocationTransferReportTab')]//*[text()='Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfQty + "]/div"))
						.getText();

				if (actualFromStore.equalsIgnoreCase(fromStore) && actualFromLoc.equalsIgnoreCase(fromLocation)
						&& actualToLoc.equalsIgnoreCase(toLocation) && actualQuantity.equalsIgnoreCase(DeliveredQty)) {
					System.out.println("**** Verified Inter Location Stock Transfer for [" + documentid + "] with ["
							+ ProdID + "] [" + actualFromStore + "] [" + actualFromLoc + "] [" + actualToLoc + "] ["
							+ actualQuantity + "] **********");
				} else {
					System.out.println("!!!!!!! FAILED to verify Inter Location Stock Transfer for [" + documentid
							+ "] with [" + ProdID + "] [" + actualFromStore + "] [" + actualFromLoc + "] ["
							+ actualToLoc + "] [" + actualQuantity + "] !!!!!!! ");
					System.out.println("!!!!!!! FAILED to verify Inter Location Stock Transfer for [" + documentid
							+ "] with [" + ProdID + "] [" + fromStore + "] [" + fromLocation + "] [" + toLocation
							+ "] [" + DeliveredQty + "] !!!!!!! ");

					flag = false;
				}
			}

			if (flag == false) {
				System.out.println("!!!!!!!!!!!!!! Failed to verify Inter Location Stock Transfer [" + documentid
						+ "] !!!!!!!!!!!!!!!!!!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			Utilities.HoverandClick(pro.getProperty("ClsBtn"), driver);
			System.out.println("************** VERIFIED Inter Location Stock Transfer [" + documentid
					+ "] successfully **************");

		} catch (Exception EX) {
			System.out.println("******************* Document Inter Location Stock Transfer is NOT VERIFIED for : ["
					+ documentid + "] check LOG! ***************");
			Utilities.handleError(EX, driver);
		}
	}

}
