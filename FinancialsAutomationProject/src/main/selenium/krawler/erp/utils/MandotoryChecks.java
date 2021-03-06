package krawler.erp.utils;

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

public class MandotoryChecks {
	// ----------------------- Check for Inventory setup
	// --------------------------------------------------------------------
	public static void Inventory_MandotoryChecks(WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_SystemControls.properties");
			WebElement element = null;
			Actions action = new Actions(driver);
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";

			Utilities.waitandClick(pro.getProperty("PreferencesExpander"), driver);
			Utilities.waitandClick(pro.getProperty("SystemControlsLink"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.isElementGone(xpathOfLoading, 120, driver);

			String arr[] = { "CycleCountActivate", "EnableQAflow", "BuildAssemblyQA", "StockINQA", "ISSTwithQA",
					"StockRequestQA", "GRwithQA", "DOwithQA", "UnitPriceforDO", "UnitPriceforGR", "UnitPriceforSR",
					"UnitPriceforPR", "EnableAccWithCode", "EnableBatch", "EnableSerial" };
			for (int i = 0; i < arr.length; i++) {
				element = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty(arr[i]))));

				if (!element.isSelected()) {
					action.moveToElement(element).build().perform();
					Thread.sleep(500);
					element.click();
					System.out.println("*** Check now ENABLED for [" + arr[i] + "] ***");
				} else {
					System.out.println("*** Check Already ENABLED for [" + arr[i] + "] ***");
				}
			}
			
			/*
			WebElement negativeButton = driver.findElement(By.xpath(pro.getProperty("MainNegativeButton")));
			if (negativeButton.isSelected()) {
				System.out.println("Activate Negative Stock For Location Warehouse is ALREADY ENABLED !!");
			} else {
				negativeButton.click();
				Utilities.HoverandClick(pro.getProperty("saveAccountPreferencesBtn"), driver);
				Utilities.clickButton("Yes", 1500, driver);
			}
			*/
			
			String arr2[] = {"negativeStockFormulaSI", "creditControl", "creditControlOrder", "creditControlVendorOrder",
					"vendorCreditLimit", "minBudgetControl", "productSortByIdCase", "Importcurrencyname" };
			/*
			String arr2[] = { "negativeStockDO", "negativeStockSO", "negativeStockFormulaSI", "negativeStockSICS",
					"negativeStockPR", "creditControl", "creditControlOrder", "creditControlVendorOrder",
					"vendorCreditLimit", "minBudgetControl", "productSortByIdCase", "Importcurrencyname" };
			*/
			
			for (int k = 0; k < arr2.length; k++) {
				element = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty(arr2[k]))));

				if (!element.isSelected()) {
					action.moveToElement(element).build().perform();
					Thread.sleep(500);
					element.click();
					System.out.println("*** Check now ENABLED for [" + arr2[k] + "] ***");
				} else {
					System.out.println("*** Check Already ENABLED for [" + arr2[k] + "] ***");
				}
			}

			Utilities.report_ScreenShot("System Preferences", driver);
			Utilities.HoverandClick(pro.getProperty("saveAccountPreferencesBtn"), driver);
			Utilities.HoverandClick(pro.getProperty("applyNewsettingsBtn"), driver);
			Utilities.click_element("//button[text()='OK']", driver);
			driver.navigate().refresh();
			Utilities.isLoadingDisappear(120, driver);
			System.out.println("*************** !!!! Settings has been Set !!!! ******************");
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ----------------------- Check for ERP setup
	// --------------------------------------------------------------------
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

			// ****************************** If we need to UNCHECK option
			// **************************************************
			String uncheckOption[] = { "alertMinMaxOrder" };
			for (int k = 0; k < uncheckOption.length; k++) {
				element = driver.findElement(By.xpath(pro.getProperty(uncheckOption[k])));
				if (element.isSelected()) {
					element.click();
					System.out.println("**** Check for [" + uncheckOption[k] + "] is NOW Disabled ****");
				} else {
					System.out.println("**** Check for [" + uncheckOption[k] + "] is Already Disabled ****");
				}
			}

			// ****************************** If we need to CHECK option
			// **************************************************
			String arr[] = { "unitPriceInDO", "unitPriceInGR", "unitPriceInSR", "unitPriceInPR",
					"autorefrshreportonsave" };
			for (int i = 0; i < arr.length; i++) {
				element = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='" + arr[i] + "']")));

				if (!element.isSelected()) {
					action.moveToElement(element).build().perform();
					Thread.sleep(500);
					element.click();
					System.out.println("*** Check now ENABLED for [" + arr[i] + "] ***");
				} else {
					System.out.println("*** Check Already ENABLED for [" + arr[i] + "] ***");
				}
			}

			WebElement negativeButton = driver.findElement(By.xpath(pro.getProperty("MainNegativeButton")));
			if (negativeButton.isSelected()) {
				System.out.println("Activate Negative Stock For Location Warehouse is ALREADY ENABLED !!");
			} else {
				negativeButton.click();
				Utilities.HoverandClick(pro.getProperty("saveAccountPreferencesBtn"), driver);
				Utilities.clickButton("Yes", 1500, driver);
			}

			String arr2[] = { "negativeStockDO", "negativeStockSO", "negativeStockFormulaSI", "negativeStockSICS",
					"negativeStockPR", "creditControl", "creditControlOrder", "creditControlVendorOrder",
					"vendorCreditLimit", "minBudgetControl", "productSortByIdCase", "Importcurrencyname", "GRwithQA",
					"DOwithQA", "EnableAccWithCode" };
			for (int k = 0; k < arr2.length; k++) {
				element = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty(arr2[k]))));

				if (!element.isSelected()) {
					action.moveToElement(element).build().perform();
					Thread.sleep(500);
					element.click();
					System.out.println("*** Check now ENABLED for [" + arr2[k] + "] ***");
				} else {
					System.out.println("*** Check Already ENABLED for [" + arr2[k] + "] ***");
				}
			}

			Utilities.report_ScreenShot("System Preferences", driver);
			Utilities.HoverandClick(pro.getProperty("saveAccountPreferencesBtn"), driver);
			Utilities.HoverandClick(pro.getProperty("applyNewsettingsBtn"), driver);
			Utilities.click_element("//button[text()='OK']", driver);
			driver.navigate().refresh();
			Utilities.isLoadingDisappear(120, driver);
			System.out.println("*************** !!!! Settings has been Set !!!! ******************");
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}
}
