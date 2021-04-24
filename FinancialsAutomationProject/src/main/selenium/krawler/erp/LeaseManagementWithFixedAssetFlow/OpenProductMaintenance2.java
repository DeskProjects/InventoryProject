package krawler.erp.LeaseManagementWithFixedAssetFlow;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class OpenProductMaintenance2 {
	private static final Properties LM = null;

	public void LM_VerifyProductMaintenance(String expected, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties LM = Utilities.fetchProValue("OR_ProductMaintenance.properties");
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement LMButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("ProductMaintenance")))); // examining
			LMButton.click();
			Thread.sleep(1000);
			String expResul = driver.findElement(By.xpath(LM.getProperty("Title"))).getText();

			if (expResul.equals(expected)) {
				System.out.println("Product Maintenance Report is getting opened Successfully");
			} else {
				System.out.println("Product Maintenance Report is not getting opened");
			}
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}
}
