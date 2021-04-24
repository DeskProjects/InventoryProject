package krawler.erp.inventory.reports;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
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

public class StockValuationDetailReport {

	// ********************************************************Stock Valuation
	// Detail Report*********************************************************
	public static void verify_StockValuationDetailReport(String productid[], double expectedQty[], WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_StockValuationDetailReport.properties");
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandReport(driver);

			Utilities.click_element(pro.getProperty("StockValuationDetailReportLink"), driver);
			Utilities.disableExpander(driver);

			int headercnt = Utilities.totalSize(
					"//*[@id='stockvaluationdetailsreportnew']//*[text()='Product ID']/ancestor::tr/td/div", driver);
			int indOfQty = 0;
			boolean flag = true;
			for (int j = 1; j <= headercnt; j++) {
				String headerName = driver.findElement(
						By.xpath("//*[@id='stockvaluationdetailsreportnew']//*[text()='Product ID']/ancestor::tr/td["
								+ j + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Quantity")) {
					indOfQty = j;
				}
			}

			ArrayList actualResult = new ArrayList();
			for (int i = 0; i < productid.length; i++) {
				Utilities.enterTextNormalBox(productid[i], pro.getProperty("QuickSearch"), driver);
				Utilities.click_element(pro.getProperty("Fetch"), driver);
				Utilities.isLoadingDisappear(120, driver);
				String qty = driver
						.findElement(
								By.xpath("//*[text()='" + productid[i] + "']/ancestor::tr/td[" + indOfQty + "]/div"))
						.getText();
				actualResult.add(qty);

				// verification
				double UIvalue = Utilities.getIntegerFrmString(qty);
				if (UIvalue == expectedQty[i]) {
					System.out.println("*** Verified product [" + productid[i] + "] as UI quantity ["
							+ actualResult.get(i) + "] with expected quantity [[" + expectedQty[i] + "]] ****");
				} else {
					System.out.println("*** FAILED to verify product [" + productid[i] + "] as UI quantity ["
							+ actualResult.get(i) + "] with expected quantity [[" + expectedQty[i] + "]] ****");
					flag = false;
				}
			}
			// System.out.println(Arrays.toString(actualResult.toArray()));
			if (flag == false) {
				System.out.println("!!!! Failed to verify [Stock Valuation Details] Report pls chec Log !!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			System.out.println("!!!! Verify [Stock Valuation Details] Report Successfully !!!!");
			Utilities.click_element(pro.getProperty("StockValuationDetailReportClsBtn"), driver);
		} catch (Exception EX) {
			System.out.println("!!!! Failed to verify [Stock Valuation Details] Report pls chec Log !!!!");
			Utilities.handleError(EX, driver);
		}
	}

}
