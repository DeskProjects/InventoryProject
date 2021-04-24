package krawler.erp.Miscellaneous;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import krawler.erp.inventory.InvUtilities;
import krawler.erp.page.Utilities;

public class ChequeLayoutSetup {

	// *********************************** Create ChequeLayoutSetup
	// ****************************************
	public static void create_ChequeLayout(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			final Properties pro = Utilities.fetchProValue("OR_ChequeLayoutSetup.properties");
			;
			documentid = "PayM" + documentid;
			Utilities.refresh();
			InvUtilities.enableExpander(driver);
			Utilities.HoverandClick(pro.getProperty("MiscellaneousExpander"), driver);
			Utilities.HoverandClick(pro.getProperty("ChequeLayoutSetupLink"), driver);
			Thread.sleep(2000);

			Utilities.enterTextandSelect(documentid, pro.getProperty("selectPayMethod"), driver);
			Utilities.enterTextandSelect("Plain", pro.getProperty("selectFontstyle"), driver);

			enterCoordinateFor(pro.getProperty("dateLeft"), "12.5", driver);
			enterCoordinateFor(pro.getProperty("dateTop"), "2.5", driver);
			enterCoordinateFor(pro.getProperty("nameLeft"), "1.5", driver);
			enterCoordinateFor(pro.getProperty("nameTop"), "3.3", driver);
			enterCoordinateFor(pro.getProperty("amtinwordLeft"), "2", driver);
			enterCoordinateFor(pro.getProperty("amtinwordTop"), "4.2", driver);
			enterCoordinateFor(pro.getProperty("amtinwordLeft2"), "1", driver);
			enterCoordinateFor(pro.getProperty("amtinwordTop2"), "5.2", driver);
			enterCoordinateFor(pro.getProperty("amtLeft"), "12.5", driver);
			enterCoordinateFor(pro.getProperty("amtTop"), "4.3", driver);

			Utilities.clickButton("Update", 200, driver);
			Utilities.clickButton("OK", 500, driver);

			System.out.println("Cheque Layout [" + documentid + "] has been created & verified successfully");

			Utilities.HoverandClick(
					"//*[contains(@class,'x-window') and contains(@style,'visible')]//div[contains(@class,'close')]",
					driver);

		} catch (Exception EX) {
			System.out.println("Cheque Layout [" + documentid + "] Not Created !!!!!!!!!!!! ");
			Utilities.handleError(EX, driver);
		}
	}

	// *****************Enter coordinate for methoid **********************

	public static void enterCoordinateFor(String forCoord, String enterCordNumber, WebDriver driver)
			throws InterruptedException {
		WebElement box = new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(forCoord)));

		box.clear();
		Thread.sleep(500);
		box.sendKeys(enterCordNumber);
		Thread.sleep(1500);
	}
}
