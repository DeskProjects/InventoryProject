package krawler.erp.GSTFORM5Verification;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class Sales_Invoice {

	public static void create_salesInvoice(String GlobalTax, String documentId, String productid, String customerid,
			WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {
			documentId = documentId;
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			// clicked on main icon

			Utilities.HoverandClick(pro.getProperty("SalesInvoiceIcon"), driver);

			Utilities.clickCheckBox(pro.getProperty("includeTax"), "uncheck", driver);

			Utilities.enterTextandSelect(customerid, pro.getProperty("Customer"), driver);

			Utilities.enterTextInDropDown("NA", pro.getProperty("SequenceFormat"), driver);

			Utilities.enterTextInDropDown("NET 45", pro.getProperty("Term"), driver);

			Utilities.enterTextInDropDown(documentId, pro.getProperty("DocumentNo"), driver);

			Utilities.HoverandClick(pro.getProperty("AddButton"), driver);

			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "uncheck", driver);

			Utilities.enterTextInDropDown(productid, pro.getProperty("ProductSearch"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(
					"//*[text()='Product Category: (None)']/parent::div/following-sibling::div//table/tbody/tr/td[1]/div/div",
					driver);
			// System.out.println(totalResult);

			driver.findElement(By.xpath(pro.getProperty("addProducts"))).click();
			Thread.sleep(2000);

			List<WebElement> header = driver.findElements(By.xpath(
					"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td/div"));
			int headRsize = header.size();
			int indexQty = 0, indexTax = 0;

			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Quantity")) {
					indexQty = i;
				}
			}

			// enter line level quantity
			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div["
						+ i + "]/table/tbody/tr/td[" + indexQty + "]/div", driver);
				Utilities.sendText("1");
				Thread.sleep(1000);
			}

			// to scroll till down
			Utilities.justHover("//*[contains(text(),'Outstanding Balance')]", driver);

			Utilities.enterTextandSelect("Yes", pro.getProperty("includeGlobalTax"), driver);
			Utilities.enterTextandSelect(GlobalTax, pro.getProperty("GlobalTaxValue"), driver);

			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("CloseSalesInvoice"), driver);

			System.out.println("******* Sales Invoice: [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Sales Invoice is NOT Created for :[" + documentId + "] check LOG *******");
			Utilities.handleError(EX, driver);
		}

	}

}
