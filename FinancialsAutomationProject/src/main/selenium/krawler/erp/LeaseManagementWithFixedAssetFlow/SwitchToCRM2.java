package krawler.erp.LeaseManagementWithFixedAssetFlow;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class SwitchToCRM2 {
	public static void SwitchToCRMApplication(WebDriver driver) throws InterruptedException, IOException, AWTException {
		try {
			Properties LM = Utilities.fetchProValue("OR_CRM.properties");
			Utilities.refresh();
			WebDriverWait wait = new WebDriverWait(driver, 30);

			String parentWindow = driver.getWindowHandle();
			Utilities.HoverandClick(LM.getProperty("SwitchTo"), driver);
			Utilities.HoverandClick(LM.getProperty("ClickCRM"), driver);
			Thread.sleep(4000);

			// **here perform SwitchToCRM.click()

			Set s = driver.getWindowHandles(); // this method will gives you the
												// handles of all opened windows
			Iterator ite = s.iterator();

			while (ite.hasNext()) {
				String childWindow = ite.next().toString();
				if (!childWindow.contains(parentWindow)) {
					driver.switchTo().window(childWindow);
					// System.out.println(childWindow);
					Thread.sleep(1000);
					Utilities.enterTextInDropDown("admin", LM.getProperty("CRMUSerName"), driver);
					Utilities.enterTextInDropDown("1234", LM.getProperty("CRMPasword"), driver);
					System.out.println("User is Logged In CRM Application Now..");
					// Utilities.clickButton("Login", 1000, driver);
					// driver.switchTo().window(parentWindow);
					Thread.sleep(1000);
					break;
				}
			}

			Utilities.refresh();

		} catch (Exception EX) {

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(2000);// pro

	}

}
