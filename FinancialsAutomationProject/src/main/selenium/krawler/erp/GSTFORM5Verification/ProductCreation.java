package krawler.erp.GSTFORM5Verification;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class ProductCreation {
	WebDriver driver;

	public static void create_Product(String productid, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
			String productName = productid + "Name";
			Thread.sleep(3000);// pro
			// Wait until component appears
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("createProductIcon"))));

			// Open Product master
			driver.findElement(By.xpath(pro.getProperty("createProductIcon"))).click();
			Thread.sleep(3000);

			// Product Name
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("productName"))));
			// Click on select NA of customer ID
			driver.findElement(By.xpath(pro.getProperty("productName"))).sendKeys(productName);
			Thread.sleep(1000);

			// Select Sequence format
			WebElement format = driver.findElement(By.xpath(pro.getProperty("sequenceformat")));
			format.clear();
			format.sendKeys("NA");
			Thread.sleep(1000);// pro
			format.sendKeys(Keys.ENTER);

			// Product ID
			driver.findElement(By.xpath(pro.getProperty("productId"))).click();// clicking
																				// on
																				// NA
			// Enter product ID
			driver.findElement(By.xpath(pro.getProperty("productId"))).sendKeys(productid + "Name");// adding
																									// new
																									// Product
																									// ID
			Thread.sleep(1000);

			// Put Initial Quantity
			WebElement init = driver.findElement(By.xpath(pro.getProperty("Initialquantity")));
			init.sendKeys("100");
			Thread.sleep(2000);

			// click on Blank place
			driver.findElement(By.xpath(pro.getProperty("BlankPlace"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 1000, driver);

			// Fill Waherehouse and Location detail in window

			int header = Utilities.totalSize(
					"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[1]/table/thead/tr/td/div",
					driver);
			for (int i = 1; i <= header; i++) {
				String headerName = driver.findElement(By
						.xpath("//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[1]/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Warehouse")) {
					Utilities.click_element(
							"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div",
							driver);
					Utilities.enterTextandSelect("DS - Default Store",
							"//*[contains(@style,'visible')]//*[@class='x-grid3-viewport']//*[@id='warehouse']/following::input[1]",
							driver);
				}

				if (headerName.equalsIgnoreCase("Locations")) {
					Utilities.click_element(
							"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div",
							driver);
					Utilities.enterTextandSelect("Default Location",
							"//*[contains(@style,'visible')]//*[@class='x-grid3-viewport']//*[@id='location']/following::input[1]",
							driver);
				}
			}

			// Click on Submit button
			Utilities.click_element("//*[contains(@style,'visible')]//button[text()='Submit']", driver);

			// Click on purchase page
			driver.findElement(By.xpath(pro.getProperty("purchaseTab"))).click();// click
																					// on
																					// purchase

			Thread.sleep(2000);
			WebElement purchase = driver.findElement(By.xpath(pro.getProperty("purchaseAccount")));
			purchase.sendKeys("Purchase");
			Thread.sleep(1000);// pro
			purchase.sendKeys(Keys.DOWN);
			Thread.sleep(1000);// pro
			purchase.sendKeys(Keys.ENTER);

			// Putting Product Purchase Price
			driver.findElement(By.xpath(pro.getProperty("initialPurchasePrice"))).click();// clicking
																							// on
																							// NA
			driver.findElement(By.xpath(pro.getProperty("initialPurchasePrice"))).sendKeys("100");// adding
																									// purchase
																									// price

			driver.findElement(By.xpath(pro.getProperty("salesTab"))).click();
			Thread.sleep(2000);// pro
			WebElement sales = driver.findElement(By.xpath(pro.getProperty("salesAccount")));
			sales.sendKeys("Sales");
			Thread.sleep(2000);// pro
			sales.sendKeys(Keys.DOWN);
			Thread.sleep(2000);// pro
			sales.sendKeys(Keys.ENTER);

			// Putting Product Sales Price
			driver.findElement(By.xpath(pro.getProperty("initialSalesPrice"))).click();
			driver.findElement(By.xpath(pro.getProperty("initialSalesPrice"))).sendKeys("1000");// adding
																								// sales
																								// price

			Thread.sleep(2000);// pro
			// -------------------------------------------------------------------------------------------------------------------

			driver.findElement(By.xpath(pro.getProperty("inventoryDataTab"))).click();// click
																						// on
																						// UMo
																						// page
			Thread.sleep(2000);// pro

			WebElement UMO = driver.findElement(By.xpath(pro.getProperty("umo")));
			UMO.clear();
			UMO.sendKeys("Unit");
			Thread.sleep(3000);// pro
			UMO.sendKeys(Keys.ENTER);
			// System.out.println("Value of 'warehouse':
			// "+pro.getProperty("warehouse"));

			WebElement warehouse = driver.findElement(By.xpath("//*[@id='warehouseproductwin']"));// click
																									// on
																									// dropdown
																									// of
																									// warehouse
			warehouse.sendKeys("DS - Default Store");
			Thread.sleep(3000);// pro
			warehouse.sendKeys(Keys.ENTER);

			WebElement location = driver.findElement(By.xpath(pro.getProperty("location")));// click
																							// on
																							// dropdown
																							// of
																							// warehouse
			Thread.sleep(1000);// pro
			location.sendKeys("Default Location");
			Thread.sleep(3000);// pro
			location.sendKeys(Keys.ENTER);

			driver.findElement(By.xpath(pro.getProperty("saveButton"))).click();// click
																				// on
																				// Save
			Thread.sleep(3000);// pro
			Utilities.clickButton("OK", 2000, driver);

			System.out.println("Product created Successfully:" + productName + "");
			// Navigate to home page
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("productReportTab"))));

			driver.findElement(By.xpath(pro.getProperty("productReportTab"))).click();// working
																						// fine
			Thread.sleep(2000);// pro

			// wait until close customer will appear
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("closeCreateproductTab"))));
			// Click on close customer
			driver.findElement(By.xpath(pro.getProperty("closeCreateproductTab"))).click();// working
																							// fine
			Thread.sleep(2000);// pro
			Utilities.refresh();
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ***************************** Add new Purchase price list
	// code**************************

	public static void debug_setProductPurchasePrice(String product_ID, String purchasePrice, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			Properties productOR = Utilities.fetchProValue("OR_ProductMaster.properties");

			WebDriverWait wait = new WebDriverWait(driver, 60);
			WebElement icon = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(productOR.getProperty("createProductIcon"))));
			icon.click();
			Thread.sleep(2000);
			// Utilities.HoverandClick(productOR.getProperty("createProductIcon"),
			// driver);
			Utilities.HoverandClick(productOR.getProperty("closeCreateproductTab"), driver);
			Utilities.clickButton("Yes", 1000, driver);

			WebDriverWait setProductPricewait = new WebDriverWait(driver, 60);
			WebElement priceLisrReportButton = setProductPricewait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(productOR.getProperty("priceListReportButton"))));
			priceLisrReportButton.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(productOR.getProperty("generatePriceListReportButton")))); // generatePriceList
			driver.findElement(By.xpath(productOR.getProperty("generatePriceListReportButton"))).click();
			Thread.sleep(5000);
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productOR.getProperty("addButton"))));
			driver.findElement(By.xpath(productOR.getProperty("addButton"))).click();

			Thread.sleep(5000);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					".//*[@id='pricewindow']/div[2]/div[1]/div/div/div/div[2]/div/div/div/div/div/form/div[1]/div/div")));
			driver.findElement(By
					.xpath(".//*[@id='pricewindow']/div[2]/div[1]/div/div/div/div[2]/div/div/div/div/div/form/div[1]/div/div"))
					.click();

			WebElement we = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					".//*[@id='pricewindow']/div[2]/div[1]/div/div/div/div[2]/div/div/div/div/div/form/div[1]/div/div/input[2]")));
			we.sendKeys("Purchase Price");
			Thread.sleep(1000);
			Robot r2 = new Robot();
			r2.keyPress(KeyEvent.VK_TAB);
			r2.keyRelease(KeyEvent.VK_TAB);

			// to open dropdown
			WebElement drpDwBtn = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath(".//*[@id='pricewindow']/div[2]/div[1]/div/div/div/div[2]/div/div/div/div/div/form/div[2]/div/div")));
			drpDwBtn.click();

			// send product Id
			WebElement inpfield = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath(".//*[@id='pricewindow']/div[2]/div[1]/div/div/div/div[2]/div/div/div/div/div/form/div[2]/div/div/input[2]")));
			inpfield.click();
			Thread.sleep(1000);
			Utilities.sendText(product_ID + "Name");
			Thread.sleep(2000);

			// select listed product
			WebElement result = new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[@class='x-combo-list-inner']/div/table/tbody/tr/td[1]")));
			result.click();
			Thread.sleep(2000);

			// select ok arrow
			WebElement selectdrpDwBtn = new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath("//form[@method='POST']/div[2]/div/div/span/img[2]")));
			selectdrpDwBtn.click();
			Thread.sleep(1000);

			// set UOM
			we = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='uomidpricewindow']")));
			we.click();
			Thread.sleep(2000);
			we.clear();
			Thread.sleep(2000);
			we.sendKeys("Unit");
			we.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			// set Price
			we = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					".//*[@id='pricewindow']/div[2]/div[1]/div/div/div/div[2]/div/div/div/div/div/form/div[7]/div/input")));
			we.sendKeys(purchasePrice);
			Thread.sleep(2000);
			Robot r4 = new Robot();
			r4.keyPress(KeyEvent.VK_TAB);
			r4.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(2000);

			// set date
			we = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					".//*[@id='pricewindow']/div[2]/div[1]/div/div/div/div[2]/div/div/div/div/div/form/div[8]/div/div/img")));
			we.click();
			Thread.sleep(1000);

			we = new WebDriverWait(driver, 30).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(productOR.getProperty("datePickerToday"))));
			we.click();
			Thread.sleep(1000);

			// save
			we = new WebDriverWait(driver, 30).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(productOR.getProperty("savePriceButton"))));
			we.click();

			WebElement generatedOk = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
			if (generatedOk.isDisplayed()) {
				generatedOk.click();
				System.out.println("Price list for [" + product_ID + "] successfully Updated !!");
			} else {
				System.out
						.println("!!!!!!!!!!!!!!!! Price list for [" + product_ID + "] is not Updated !!!!!!!!!!!!! ");
			}

			Thread.sleep(2000);
			Robot r6 = new Robot();
			r6.keyPress(KeyEvent.VK_SPACE);
			r6.keyRelease(KeyEvent.VK_SPACE);
			we = new WebDriverWait(driver, 30).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(productOR.getProperty("closePriceReport"))));
			we.click();
			Thread.sleep(2000);

		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}

	}

}
