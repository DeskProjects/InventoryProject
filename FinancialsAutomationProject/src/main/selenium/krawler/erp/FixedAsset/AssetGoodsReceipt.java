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

public class AssetGoodsReceipt {
	public void Verification_AssetGR(String documentid, String ExpectedResultTA, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			String documentid1 = "AGR" + documentid;

			// Asset Goods Receipt Utilities

			Properties AssetGR = Utilities.fetchProValue("OR_AssetGR.properties");
			Thread.sleep(500);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Goods Receipt

			WebElement AGRRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("ClickOnAGRR"))));
			AGRRbutton.click();
			Thread.sleep(1000);

			// Click On Fetch button
			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("Fetch"))));
			Search.click();
			Thread.sleep(1000);

			// Enter Quick Search

			WebElement Search1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("QuickSearch"))));
			Search1.sendKeys("AGR1000");
			Thread.sleep(2000);

			// Click on records

			WebElement AGRCheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("CheckBox"))));
			AGRCheck.click();
			Thread.sleep(2000);

			// Verify Asset Total Amount

			WebElement TA = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("TotalAmount"))));
			String ActualResultTA = TA.getText();
			Thread.sleep(2000);

			System.out.println("********* Verification Result **********");

			System.out.println("*********Total Amount Expected Result = " + ExpectedResultTA + " **********");
			System.out.println("*********Total Amount Actual Result =  " + ActualResultTA + "    ************");

			if (ExpectedResultTA.equals(ActualResultTA)) {
				System.out.println("Matched Total Amount [" + ExpectedResultTA + "]  [" + ActualResultTA + "]");

			} else {
				System.out
						.println("FAILED to Matched Total Amount [" + ExpectedResultTA + "]  [" + ActualResultTA + "]");

			}

			// Verify Total Amount In Base Currency

			WebElement TAB = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("TotalAmountInBase"))));
			String ActualResultTAB = TAB.getText();
			Thread.sleep(2000);

			String ExpectedResultTAB = "SGD 100.00";

			System.out.println(
					"*********Total Amount In Basce Currency  Expected Result = " + ExpectedResultTAB + " **********");
			System.out.println(
					"*********Total Amount In Basce Currency Actual Result =  " + ActualResultTAB + "    ************");

			if (ExpectedResultTAB.equals(ActualResultTAB)) {
				System.out.println("Matched Total Amount In Basce Currency  [" + ExpectedResultTAB + "]  ["
						+ ActualResultTAB + "]");

			} else {
				System.out.println("FAILED to Total Amount In Basce Currency [" + ExpectedResultTAB + "]  ["
						+ ActualResultTAB + "]");

			}

			// Verify Gross Total Amount

			WebElement GTA = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetGR.getProperty("GrossTotalAmount"))));
			String ActualResultGTA = GTA.getText();
			Thread.sleep(2000);

			String ExpectedResultGTA = "SGD 100.00";

			System.out.println("*********Gross Total Amount  Expected Result = " + ExpectedResultGTA + " **********");
			System.out.println("*********Gross Total Amount Result =  " + ActualResultGTA + "    ************");

			if (ExpectedResultGTA.equals(ActualResultGTA)) {
				System.out
						.println("Matched Gross Total Amount  [" + ExpectedResultGTA + "]  [" + ActualResultGTA + "]");

			} else {
				System.out.println(
						"FAILED to Matched Gross Total Amount [" + ExpectedResultGTA + "]  [" + ActualResultGTA + "]");

			}
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

}
