package krawler.erp.FixedAsset;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class AssetMenu {

	public static void FixedAssetExpander(WebDriver driver) throws IOException, InterruptedException {

		// Asset Menu Utilities
		Properties Asset = Utilities.fetchProValue("OR_AssetMenu.properties");
		Thread.sleep(1000);

		// Click on System Icon
		WebDriverWait wait = new WebDriverWait(driver, 30);

		WebElement SyButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(Asset.getProperty("ClickOnSystem"))));
		SyButton.click();
		Thread.sleep(1000);

		// Click on Asset Icon
		WebElement Assetbutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(Asset.getProperty("ClickOnAssetIcon"))));
		Assetbutton.click();
		Thread.sleep(1000);

		// Click on Entry Button
		WebElement Entrybutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(Asset.getProperty("ClickOnEntry"))));
		Entrybutton.click();
		Thread.sleep(1000);

		// Click On Asset Report
		WebElement AReport = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(Asset.getProperty("ClickOnReport"))));
		AReport.click();
		Thread.sleep(1000);

	}
}
