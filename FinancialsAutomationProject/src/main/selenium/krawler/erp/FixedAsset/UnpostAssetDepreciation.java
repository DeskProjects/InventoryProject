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

public class UnpostAssetDepreciation {
	public void Unpost_AssetDepreciation(String documentid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {

		try {
			String documentid1 = "ADepreciation" + documentid;

			// Asset Depreciation
			Properties AssetDepreciation = Utilities.fetchProValue("OR_UnpostAssetDepreciation.properties");
			Thread.sleep(2000);

			WebDriverWait AGwait = new WebDriverWait(driver, 30);

			// Click On Asset Depreciation
			WebElement ADepreciationbutton = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetDepreciation.getProperty("ClickOnUAD"))));
			ADepreciationbutton.click();
			Thread.sleep(3000);

			// Click On Asset Depreciation Group
			String drpDwnArrow = "//input[@name='productname']/following::span/img[2]";
			String itemInputBoxLocator = "//input[@name='productname']";
			String itemName = "AGName";
			String rslTable = "//form[@id='designpanelpreview']/following::div[22]/table/tbody/tr";

			Utilities.selectItemfromDropDown(drpDwnArrow, itemInputBoxLocator, itemName, rslTable, driver);
			Thread.sleep(3000);

			// Click On Asset Depreciation ID

			Utilities.HoverandClick(AssetDepreciation.getProperty("Cross"), driver);
			String drpDwnArrow1 = "//div[@id='DepreciationUnpost']//following::div[5]/input/following::span/img[2]";
			String itemInputBoxLocator1 = "//div[@id='DepreciationUnpost']//following::div[5]/input";
			String itemName1 = "C111";
			String rslTable1 = "//form[@id='designpanelpreview']/following::div[41]/table";

			Utilities.selectItemfromDropDown(drpDwnArrow1, itemInputBoxLocator1, itemName1, rslTable1, driver);
			Thread.sleep(3000);

			// Click On Asset Depreciation Fetch button
			WebElement Search = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDepreciation.getProperty("Fetch"))));
			Search.click();
			Thread.sleep(2000);

			// Click On Check Box
			WebElement Check = AGwait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(AssetDepreciation.getProperty("CheckBox"))));
			Check.click();
			Thread.sleep(2000);

			// Click On Post button
			WebElement Post = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDepreciation.getProperty("UnPost"))));
			Post.click();
			Thread.sleep(2000);

			// Click On Yes button
			WebElement Yes = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDepreciation.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			// Click On Ok button
			WebElement Ok = AGwait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AssetDepreciation.getProperty("Ok"))));
			Ok.click();
			Thread.sleep(1000);

			System.out.println("***************** Unpost Asset Depreciation Successfully*******************");

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

}
