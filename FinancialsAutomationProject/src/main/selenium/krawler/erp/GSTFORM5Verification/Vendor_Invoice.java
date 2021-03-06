package krawler.erp.GSTFORM5Verification;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.EmailFunctionality;
import krawler.erp.page.Utilities;
import krawler.erp.utils.SikulliUtil;

public class Vendor_Invoice {

	public static void createPurchaseInvoice(String GlobalTax, String documentId, String productid, String vendorid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			documentId = documentId;
			Properties pro = Utilities.fetchProValue("OR_VendorInvoice.properties");

			Utilities.waitandClick(pro.getProperty("CreateVendorInvoiceIcon"), driver);

			Utilities.enterTextandSelect(vendorid, pro.getProperty("VendorId"), driver);

			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceFormat"), driver);

			Utilities.enterTextInDropDown(documentId, pro.getProperty("VendorInvoiceNumber"), driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);

			Utilities.clickCheckBox(pro.getProperty("clickCheckBox"), "uncheck", driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("searchProductId"), driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);

			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);

			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexQty = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Quantity")) {
					indexQty = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[" + i
								+ "]/table/tbody/tr/td[" + indexQty + "]/div",
						driver);
				Utilities.sendText("1");
				Thread.sleep(1000);
			}

			// to scroll till end
			Utilities.justHover("//*[contains(text(),'Outstanding Balance')]", driver);
			Utilities.enterTextandSelect("Yes", pro.getProperty("includeGlobalTax"), driver);
			Utilities.enterTextandSelect(GlobalTax, pro.getProperty("GlobalTaxValue"), driver);

			Utilities.HoverandClick(pro.getProperty("memo"), driver);

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("closeVendorInvoice"), driver);

			System.out.println("******* Purchase Invoice : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Purchase Invoice is NOT Created for :[" + documentId + "] check LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// --------------------------------------------------- Pi in Foreign
	// Currency
	// -----------------------------------------------------------------------------

	public static void createPurchaseInvoicewithForeignCurrency(String documentId, String productid, String vendorid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			documentId = documentId;
			Properties pro = Utilities.fetchProValue("OR_VendorInvoice.properties");

			Utilities.waitandClick(pro.getProperty("CreateVendorInvoiceIcon"), driver);

			Utilities.enterTextandSelect(vendorid, pro.getProperty("VendorId"), driver);
			Utilities.enterTextInDropDown("US Dollars (USD)", pro.getProperty("DocumentCurrency"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceFormat"), driver);

			Utilities.enterTextInDropDown(documentId, pro.getProperty("VendorInvoiceNumber"), driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);

			Utilities.clickCheckBox(pro.getProperty("clickCheckBox"), "uncheck", driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("searchProductId"), driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);

			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);

			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexQty = 0;
			int indexUP = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Quantity")) {
					indexQty = i;
				} else if (HeadeName.equalsIgnoreCase("Unit Price")) {
					indexUP = i;
				}
			}

			Utilities.HoverandClick(
					"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div/table/tbody/tr/td["
							+ indexQty + "]/div",
					driver);
			Utilities.sendText("1");
			Thread.sleep(1000);
			Utilities.HoverandClick(pro.getProperty("memo"), driver);

			// Utilities.HoverandClick("//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div/table/tbody/tr/td["+indexUP+"]/div",
			// driver);
			// Utilities.sendText("81");
			Utilities.clickAndEnterText("81",
					"//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div/table/tbody/tr/td["
							+ indexUP + "]/div",
					driver);
			Thread.sleep(1000);

			// To scroll till end
			Utilities.justHover("//*[contains(text(),'Outstanding Balance')]", driver);

			Utilities.HoverandClick(pro.getProperty("memo"), driver);
			Utilities.HoverandClick(pro.getProperty("ExchRate"), driver);
			Utilities.HoverandClick(pro.getProperty("inputexchange"), driver);
			Utilities.sendText("0.81");
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("closeVendorInvoice"), driver);

			System.out.println("******* Purchase Invoice : [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Purchase Invoice is NOT Created for :[" + documentId + "] check LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

}