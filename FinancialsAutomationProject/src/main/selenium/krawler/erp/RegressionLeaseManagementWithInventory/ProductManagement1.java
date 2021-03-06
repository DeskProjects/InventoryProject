package krawler.erp.RegressionLeaseManagementWithInventory;

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

public class ProductManagement1 {
	WebDriver driver;

	public static void create_Product(String product_ID, String intQauntity, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
			String productName = product_ID + "BS4 Name";
			String productDes = product_ID + " Batch Serial No Product Description";
			Thread.sleep(3000);// pro
			// wait until component appears
			WebDriverWait wait = new WebDriverWait(driver, 60);
			// System.out.println(driver.findElement(By.xpath(pro.getProperty("createProductIcon"))).isEnabled());
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("createProductIcon"))));

			// Open Product master
			driver.findElement(By.xpath(pro.getProperty("createProductIcon"))).click();
			// System.out.println("clicked on product master");
			Thread.sleep(3000);// pro

			// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
			// Product Name
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("productName"))));
			// Click on select NA of customer ID
			driver.findElement(By.xpath(pro.getProperty("productName"))).sendKeys(productName);// working
																								// fine
			Thread.sleep(1000);// pro

			// Product Description
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("productDes"))));
			driver.findElement(By.xpath(pro.getProperty("productDes"))).sendKeys(productDes);// working
																								// fine
			Thread.sleep(1000);// pro

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
			driver.findElement(By.xpath(pro.getProperty("productId"))).sendKeys(product_ID + "BS4");// adding
																									// new
																									// Product
																									// ID
			Thread.sleep(1000);// pro
			if (driver.findElement(By.xpath(pro.getProperty("SerialCheck"))).isEnabled()) {
				driver.findElement(By.xpath(pro.getProperty("SerialCheck"))).click();
			}

			if (driver.findElement(By.xpath(pro.getProperty("BatchCheck"))).isEnabled()) {
				driver.findElement(By.xpath(pro.getProperty("BatchCheck"))).click();
			}

			if (driver.findElement(By.xpath(pro.getProperty("Makeavailable"))).isEnabled()) {
				driver.findElement(By.xpath(pro.getProperty("Makeavailable"))).click();
				Thread.sleep(1000);
			}

			// Put Initial Quantity
			WebElement init = driver.findElement(By.xpath(pro.getProperty("Initialquantity")));
			init.sendKeys(intQauntity);
			Thread.sleep(2000);

			// click on Blank place
			driver.findElement(By.xpath(pro.getProperty("BlankPlace"))).click();
			Thread.sleep(2000);

			// Fill Waherehouse and Location detail in window

			WebElement W1 = driver.findElement(By.xpath(pro.getProperty("Warehouse1")));
			W1.click();
			Thread.sleep(2000);
			Utilities.sendText("DS - Default Store");
			Thread.sleep(1000);

			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			Utilities.sendText("Default Location");
			Thread.sleep(2000);

			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);

			r.keyPress(KeyEvent.VK_TAB);
			Utilities.sendText("Batch");
			Thread.sleep(2000);

			r.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(2000);

			/*
			 * r.keyPress(KeyEvent.VK_TAB); Thread.sleep(2000);
			 * 
			 * r.keyPress(KeyEvent.VK_TAB); Thread.sleep(2000);
			 * 
			 * r.keyPress(KeyEvent.VK_TAB); Thread.sleep(2000);
			 */
			String quanityValue = intQauntity;

			for (int qtycnt = 1; qtycnt <= Integer.parseInt(quanityValue); qtycnt++) {
				WebElement serialSeq = driver.findElement(By
						.xpath("//div[@class='x-window' and contains(@style,'visible')]/div[2]/div[1]/div/div/div/div[3]/div/div/div/div/div/div/div[1]/div[2]/div/div["
								+ qtycnt + "]/table/tbody/tr/td[14]/div"));
				Thread.sleep(1000);
				serialSeq.click();
				Thread.sleep(1000);
				Robot r2 = new Robot();
				r2.keyPress(KeyEvent.VK_CONTROL);
				r2.keyPress(KeyEvent.VK_A);
				r2.keyRelease(KeyEvent.VK_A);
				r2.keyRelease(KeyEvent.VK_CONTROL);
				r2.keyPress(KeyEvent.VK_DELETE);
				r2.keyRelease(KeyEvent.VK_DELETE);
				Utilities.sendText("Serial" + qtycnt);
				Thread.sleep(2000);
			}

			// Click on submit button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton"))));
			driver.findElement(By.xpath(pro.getProperty("SubmitButton"))).click();// working
																					// fine
			Thread.sleep(1000);// pro

			// click on purchase page
			driver.findElement(By.xpath(pro.getProperty("purchaseTab"))).click();// click
																					// on
																					// purchase

			Thread.sleep(2000);// pro
			WebElement purchase = driver.findElement(By.xpath(pro.getProperty("purchaseAccount")));
			purchase.sendKeys("Purchase");
			Thread.sleep(1000);// pro
			purchase.sendKeys(Keys.DOWN);
			Thread.sleep(1000);// pro
			purchase.sendKeys(Keys.ENTER);

			// product ID
			driver.findElement(By.xpath(pro.getProperty("initialPurchasePrice"))).click();// clicking
																							// on
																							// NA
			// enter product ID
			driver.findElement(By.xpath(pro.getProperty("initialPurchasePrice"))).sendKeys("100");// adding
																									// purchase
																									// price

			driver.findElement(By.xpath(pro.getProperty("salesTab"))).click();
			Thread.sleep(2000);// pro
			// ----------------------------------------------------------------------------------------------------------------------
			WebElement sales = driver.findElement(By.xpath(pro.getProperty("salesAccount")));
			sales.sendKeys("Advertising");
			Thread.sleep(2000);// pro
			sales.sendKeys(Keys.DOWN);
			Thread.sleep(2000);// pro
			sales.sendKeys(Keys.ENTER);

			// product ID
			driver.findElement(By.xpath(pro.getProperty("initialSalesPrice"))).click();
			// enter product ID
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
			Utilities.sendText(product_ID + "BS4 Name");
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

			// Sync Product
			WebElement SyncPr1 = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(productOR.getProperty("SyncProduct"))));
			SyncPr1.click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(productOR.getProperty("DataSyncto"))))
					.click();
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1000, driver);
			Thread.sleep(1000);

			Utilities.clickButton("OK", 1000, driver);
			Thread.sleep(1000);

			System.out.println("Product is synced Successfully");
			Thread.sleep(1000);
			Utilities.refresh();

		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}

	}
	// -----------------------------------------------------------------------Service
	// Type Product
	// Creation----------------------------------------------------------------

	public static void Create_ServiceProduct(String product_ID, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
			String productName = product_ID + "Service";
			String productDes = product_ID
					+ " Service Type Product which are creating for Sales Order with Maintenance Request ";

			Thread.sleep(3000);// pro

			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("createProductIcon"))));

			// Open Product master
			driver.findElement(By.xpath(pro.getProperty("createProductIcon"))).click();
			Thread.sleep(3000);// pro

			// Select Product Type
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ProductType1"))));
			WebElement ProductType = driver.findElement(By.xpath(pro.getProperty("ProductType1")));
			ProductType.clear();
			Thread.sleep(10000);
			ProductType.sendKeys("Service");
			Thread.sleep(1000);

			driver.findElement(By.xpath(pro.getProperty("ProductType1chose"))).click();
			Thread.sleep(2000);// pro

			// Product Name
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("productName"))));
			driver.findElement(By.xpath(pro.getProperty("productName"))).sendKeys(productName);// working
																								// fine
			Thread.sleep(1000);// pro

			// Product Description
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("productDes"))));
			driver.findElement(By.xpath(pro.getProperty("productDes"))).sendKeys(productDes);// working
																								// fine
			Thread.sleep(1000);// pro

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
			driver.findElement(By.xpath(pro.getProperty("productId"))).sendKeys(product_ID + "Service");// adding
																										// new
																										// Product
																										// ID
			Thread.sleep(1000);// pro

			// click on Purchase page

			driver.findElement(By.xpath(pro.getProperty("purchaseTab"))).click();// click
																					// on
																					// purchase

			Thread.sleep(2000);// pro
			WebElement purchase = driver.findElement(By.xpath(pro.getProperty("purchaseAccount")));
			purchase.sendKeys("Purchase");
			Thread.sleep(1000);// pro
			purchase.sendKeys(Keys.DOWN);
			Thread.sleep(1000);// pro
			purchase.sendKeys(Keys.ENTER);

			// Putting Initial Purchase Price.
			driver.findElement(By.xpath(pro.getProperty("initialPurchasePrice"))).click();

			driver.findElement(By.xpath(pro.getProperty("initialPurchasePrice"))).sendKeys("100");// adding
																									// purchase
																									// price

			// click on Sales page
			driver.findElement(By.xpath(pro.getProperty("salesTab"))).click();
			Thread.sleep(2000);// pro

			WebElement sales = driver.findElement(By.xpath(pro.getProperty("salesAccount")));
			sales.sendKeys("Advertising");
			Thread.sleep(2000);// pro
			sales.sendKeys(Keys.DOWN);
			Thread.sleep(2000);// pro
			sales.sendKeys(Keys.ENTER);

			driver.findElement(By.xpath(pro.getProperty("initialSalesPrice"))).click();
			driver.findElement(By.xpath(pro.getProperty("initialSalesPrice"))).sendKeys("1000");// adding
																								// sales
																								// price

			Thread.sleep(2000);// pro

			// click on Inventory page
			driver.findElement(By.xpath(pro.getProperty("inventoryDataTab"))).click();// click
																						// on
																						// UMo
																						// page
			Thread.sleep(2000);// pro

			WebElement UMO = driver.findElement(By.xpath(pro.getProperty("UMO1")));
			UMO.clear();
			UMO.sendKeys("Unit");
			Thread.sleep(3000);// pro
			UMO.sendKeys(Keys.ENTER);

			driver.findElement(By.xpath(pro.getProperty("saveButton"))).click();// click
																				// on
																				// Save
			Thread.sleep(3000);// pro
			Utilities.clickButton("OK", 2000, driver);

			System.out.println("Service Type Product created Successfully:" + productName + "");

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

}
