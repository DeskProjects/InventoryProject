package krawler.erp.GSTFORM5Verification;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.inventory.InvUtilities;
import krawler.erp.page.Utilities;

public class PreRequisite {

	public static void ERP_MandotoryChecks(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_SystemControls.properties");
			WebElement element = null;
			Actions action = new Actions(driver);
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";

			Utilities.waitandClick(pro.getProperty("PreferencesExpander"), driver);
			Utilities.waitandClick(pro.getProperty("SystemControlsLink"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.isElementGone(xpathOfLoading, 120, driver);

			Utilities.waitandClick(pro.getProperty("PostManualJEforControlAccounts"), driver);
			Utilities.waitandClick(pro.getProperty("AllBtn1"), driver);
			Utilities.waitandClick(pro.getProperty("AllBtn2"), driver);
			Thread.sleep(2000);
			WebElement element2 = null;
			try {
				element2 = driver.findElement(By.xpath(
						"//*[text()='Sales']/ancestor::td[1]/following::td[1]//*[contains(@class,'check-col-on')]"));
				if (element2.isDisplayed()) {
					System.out.println("Already Enabled !!");
				}
			} catch (Exception es) {
				element2 = driver.findElement(By.xpath(
						"//*[text()='Sales']/ancestor::td[1]/following::td[1]//*[contains(@class,'check-col')]"));
				element2.click();
				System.out.println("Now Enabled !!");
			}

			Utilities.HoverandClick(pro.getProperty("UpdateBtn"), driver);
			Utilities.HoverandClick(pro.getProperty("WarnYes"), driver);
			Utilities.HoverandClick(pro.getProperty("OKBtn"), driver);
			Utilities.HoverandClick(pro.getProperty("ClosedBtn"), driver);

			Utilities.HoverandClick(pro.getProperty("saveAccountPreferencesBtn"), driver);
			Utilities.HoverandClick(pro.getProperty("applyNewsettingsBtn"), driver);
			Utilities.clickButton("OK", 1500, driver);

		} catch (Exception EX) {
			;
			Utilities.handleError(EX, driver);
		}
	}
}
