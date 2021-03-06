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

public class AssetRFQVerification {
	public void Verification_AssetRFQ(String documentid, String ExpectedResultTA, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			String documentid1 = "ARFQ" + documentid;

			// Asset Sales Return Utilities

			Properties AssetSR = Utilities.fetchProValue("OR_AssetRequisition.properties");
			Thread.sleep(500);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Sales Return

			WebElement ASRRbutton = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("ClickOnARFQR"))));
			ASRRbutton.click();
			Thread.sleep(1000);

			// Click On Fetch button
			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("Fetch"))));
			Search.click();
			Thread.sleep(1000);

			// Enter Quick Search

			WebElement Search1 = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("QuickSearch"))));
			Search1.sendKeys("RFQ100");
			Thread.sleep(2000);

			// Click on records

			WebElement ASRCheck = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("CheckBox"))));
			ASRCheck.click();
			Thread.sleep(2000);

			// Verify Memo

			WebElement GTA = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetSR.getProperty("Memo"))));
			String ActualResultGTA = GTA.getText();
			Thread.sleep(2000);

			String ExpectedResultGTA = "Memo";

			System.out.println("*********Memo  Expected Result = " + ExpectedResultGTA + " **********");
			System.out.println("*********MEMO Actual Result =  " + ActualResultGTA + "    ************");

			if (ExpectedResultGTA.equals(ActualResultGTA)) {
				System.out.println("Matched Memo  [" + ExpectedResultGTA + "]  [" + ActualResultGTA + "]");

			} else {
				System.out.println("FAILED to Matched Memo [" + ExpectedResultGTA + "]  [" + ActualResultGTA + "]");

			}
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

}
