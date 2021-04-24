package krawler.erp.GSTFORM5Verification;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import krawler.erp.page.Utilities;
import krawler.erp.utils.HandlePrintWindow;

public class MakePaymentAginstVendorgst {
	public static void create_MakePayment_VendorInvoice(String documentid, String vendorid, String InvoiceAmt,
			String PIdocumentid, WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("MakePaymentAgainstVendorCheck"), driver);
			Utilities.HoverandClick(pro.getProperty("SubmitButton1"), driver);
			Thread.sleep(2000);
			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid + "ForeignCurrency", pro.getProperty("DocumentNo"), driver); // MyPIDoc1ForeignCurrency
			Utilities.enterTextandSelect(vendorid, pro.getProperty("vendorID"), driver);
			Utilities.enterTextandSelect("Cash", pro.getProperty("PaymentMethod"), driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			int headerSize = Utilities.totalSize("//*[text()='Document Type']/ancestor::tr/td/div", driver);
			int indOfdocType = 0, indOfdocNum = 0, indOfExch = 0, indOfAmount = 0;

			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver
						.findElement(By.xpath("//*[text()='Document Type']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headerName.equalsIgnoreCase("Document Type")) {
					indOfdocType = i;
				} else if (headerName.equalsIgnoreCase("Document Number")) {
					indOfdocNum = i;
				} else if (headerName.equalsIgnoreCase("Exchange Rate")) {
					indOfExch = i;
				} else if (headerName.equalsIgnoreCase("Enter Amount")) {
					indOfAmount = i;
				}

			}

			// Link PI
			// -------------------------------------------------------------------------------
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfdocType + "]/div",
					driver);
			Utilities.enterTextandSelect("Invoice", "//*[@name='type']/following::input[1]", driver);
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfdocNum + "]/div",
					driver);
			// search invoice
			Utilities.enterTextNormalBox(PIdocumentid + "ForeignCurrency", pro.getProperty("QuickSearchInvoice"),
					driver);
			Utilities.click_element("//button[text()='Fetch']", driver);
			Utilities.clickCheckBox(pro.getProperty("SelectInvoice"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfExch + "]/div",
					driver);
			Utilities.enterTextNormalBox("1.20",
					"//*[@class='x-layer x-editor x-small-editor x-grid-editor' and contains(@style,'visible')]/input[1]",
					driver);

			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			// Enter Amount
			Utilities.HoverandClick(
					"//*[text()='Document Type']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfAmount + "]/div",
					driver);
			Utilities.enter_LinelabelAmount(InvoiceAmt, driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);

			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			Utilities.HoverandClick(pro.getProperty("SaveButton"), driver);

			Utilities.clickButton("Yes", 1000, driver);
			WebElement FinalOk = null;
			try {
				FinalOk = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
			} catch (Exception E) {
				Utilities.clickButton("Yes", 100, driver); // continue Extra Yes
			}

			FinalOk = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
			if (FinalOk.isDisplayed()) {
				Thread.sleep(800);
				FinalOk.click();
				System.out.println(
						"*** Make Payment against Vendor is Pass for doc : -> [ Mpvend" + documentid + "] ******");
			}
			Utilities.click_element(pro.getProperty("CloseMakePayment"), driver);
		}

		catch (Exception Ex) {
			System.out.println(
					"!!!!! Make Payment against Vendor is FAILEDD for doc : -> [ Mpvend" + documentid + "] !!!!!");
			Utilities.handleError(Ex, driver);
		}

	}

}
