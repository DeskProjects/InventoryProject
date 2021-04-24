package krawler.erp.FixedAsset;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class AssetDisposalInvoiceVerification {
	public void Verification_AssetDI(String documentid, String ExpectedResultTA, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			String documentid1 = "ADI" + documentid;

			// Asset Acquired Invoice Utilities

			Properties AssetDI = Utilities.fetchProValue("OR_AssetDisposalInvoice.properties");
			Thread.sleep(500);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Disposal Invoice
			WebElement ADIRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("ClickOnADIR"))));
			ADIRbutton.click();
			Thread.sleep(1000);

			// Click On Fetch button
			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("Fetch"))));
			Search.click();
			Thread.sleep(1000);

			// Enter Quick Search

			WebElement Search1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("QuickSearch"))));
			Search1.sendKeys("ADIDoc22Nov");
			Thread.sleep(2000);

			// Click on records

			WebElement ADICheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("CheckBox"))));
			ADICheck.click();
			Thread.sleep(2000);

			// Verify Asset Total Amount

			WebElement TA = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("TotalAmount"))));
			String ActualResultTA = TA.getText();
			Thread.sleep(2000);

			System.out.println("********* Verification Result **********");

			System.out.println("*********Total Amount Expected Result = " + ExpectedResultTA + " **********");
			System.out.println("*********Total Amount Actual Result =  " + ActualResultTA + "    ************");

			if (ExpectedResultTA.equals(ActualResultTA)) {
				System.out.println("Matched Total Amount [" + ExpectedResultTA + "]  [" + ActualResultTA + "]");

			} else {
				System.out.println("****FAILED to Matched Total Amount************");

			}

			// Verify Total Amount In Base Currency

			WebElement TAB = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("TotalAmountInBase"))));
			String ActualResultTAB = TAB.getText();
			Thread.sleep(2000);

			String ExpectedResultTAB = "SGD  2,200.00";

			System.out.println(
					"*********Total Amount In Basce Currency  Expected Result = " + ExpectedResultTAB + " **********");
			System.out.println(
					"*********Total Amount In Basce Currency Actual Result =  " + ActualResultTAB + "    ************");

			if (ExpectedResultTAB.equals(ActualResultTAB)) {
				System.out.println("Matched Total Amount In Basce Currency  [" + ExpectedResultTAB + "]  ["
						+ ActualResultTAB + "]");

			} else {
				System.out.println("****FAILED to Total Amount In Basce Currency   ************");

			}

			// Verify Gross Total Amount

			WebElement GTA = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDI.getProperty("GrossTotalAmount"))));
			String ActualResultGTA = GTA.getText();
			Thread.sleep(2000);

			String ExpectedResultGTA = "SGD 2,200.00";

			System.out.println("*********Gross Total Amount  Expected Result = " + ExpectedResultGTA + " **********");
			System.out.println("*********Gross Total Amount Result =  " + ActualResultGTA + "    ************");

			if (ExpectedResultGTA.equals(ActualResultGTA)) {
				System.out
						.println("Matched Gross Total Amount  [" + ExpectedResultGTA + "]  [" + ActualResultGTA + "]");

			} else {
				System.out.println("****FAILED to Matched Gross Total Amount  ************");

			}
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}
}
