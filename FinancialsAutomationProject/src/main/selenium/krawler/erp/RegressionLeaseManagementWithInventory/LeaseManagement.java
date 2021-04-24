package krawler.erp.RegressionLeaseManagementWithInventory;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class LeaseManagement {

	public static void leaseManagementExpander(WebDriver driver) throws InterruptedException, IOException

	{
		Properties LM = Utilities.fetchProValue("OR_LeaseManagement.properties");
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement LMButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("LeaseMenubtn")))); // examining
		LMButton.click();
		Thread.sleep(500);

		WebElement Entrybutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("EntryButton")))); // examining
		Entrybutton.click();
		Thread.sleep(500);

		WebElement Reportbutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(LM.getProperty("ReportButton")))); // examining
		Reportbutton.click();
		Thread.sleep(500);
	}

}
