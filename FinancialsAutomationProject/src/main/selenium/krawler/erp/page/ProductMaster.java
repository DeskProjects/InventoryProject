package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
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
import org.testng.Assert;

import krawler.erp.utils.SikulliUtil;

public class ProductMaster {
	WebDriver driver;

	public static void create_product(String productid, String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		String productName = productid + " Name";
		String productDes = productid + " ERP normal Product created on : [" + Utilities.currentDate("dd-MM-yyyy")
				+ "]";
		try {
			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");

			Utilities.waitandClick(pro.getProperty("createProductIcon"), driver);
			Thread.sleep(2000);
			Utilities.enterTextNormalBox(productName, pro.getProperty("productName"), driver);
			Utilities.enterTextNormalBox(productDes, pro.getProperty("productDes"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceformat"), driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("productId"), driver);
			// Utilities.clickCheckBox(pro.getProperty("qaenable"), "check",
			// driver);
			Utilities.enterTextNormalBox("1000", pro.getProperty("initialquantity"), driver);
			Utilities.click_element(pro.getProperty("productId"), driver);
			try {
				Utilities.clickButton("Yes", 1000, driver);
			} catch (Exception ex) {

			}
			Thread.sleep(1000);

			// enter WareHouse
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
			Utilities.click_element("//*[contains(@style,'visible')]//button[text()='Submit']", driver);

			// GO to Purcahse TAB
			Utilities.click_element(pro.getProperty("purchaseTab"), driver);
			Utilities.enterTextInDropDown("Purchase" + documentid, pro.getProperty("purchaseAccount"), driver);
			Utilities.enterTextNormalBox("10", pro.getProperty("initialPurchasePrice"), driver);

			Utilities.HoverandClick(pro.getProperty("salesTab"), driver);
			Utilities.enterTextInDropDown("Sales" + documentid, pro.getProperty("salesAccount"), driver);
			Utilities.enterTextNormalBox("20", pro.getProperty("initialSalesPrice"), driver);

			Utilities.HoverandClick(pro.getProperty("inventoryDataTab"), driver);
			Utilities.enterTextInDropDown("Unit", pro.getProperty("umo"), driver);
			Utilities.enterTextInDropDown("DS - Default Store", pro.getProperty("warehouse"), driver);
			Utilities.enterTextInDropDown("Default Location", pro.getProperty("location"), driver);

			Utilities.HoverandClick(pro.getProperty("saveButton"), driver);
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";
			Utilities.isElementGone(xpathOfLoading, 180, driver);
			try {
				WebElement duplicateProductID = driver.findElement(By.xpath("//*[contains(text(),'already exists')]"));
				if (duplicateProductID.isDisplayed()) {
					driver.navigate().refresh();
					Utilities.isLoadingDisappear(120, driver);
					System.out.println("********* This product [" + productid
							+ "] is already present so Not creating this One !!!!!! *******");
				}
			} catch (Exception ex) {
				// Continue as New Product
				try {
					productAfterSaveOKBtn(driver);
				} catch (Exception noOk) {
					// No Ok button
				}
				Utilities.click_element(pro.getProperty("CloseReport"), driver);
				Utilities.click_element(pro.getProperty("closeCreateproductTab"), driver);
				System.out
						.println("********** Normal Product [" + productName + "] Successfully created *************");
			}
		} catch (Exception EX) {
			System.out.println("********** Failed to Create Normal Product [" + productName
					+ "] Successfully created *************");
			Utilities.handleError(EX, driver);
		}
	}

	public static void productAfterSaveOKBtn(WebDriver driver) throws InterruptedException {
		WebElement button = driver.findElement(By.xpath("//button[text()='OK']"));
		if (button.isDisplayed()) {
			Thread.sleep(0);
			button.click();
		}
	}

	// ***************************************************************Inventory
	// Automation***********************************************************************************

	public static void create_ServiceProduct(String[] product_ID, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
			String productName = product_ID + " Name";
			String productDes = product_ID + " Description";

			Thread.sleep(2000);// pro
			WebDriverWait wait = new WebDriverWait(driver, 60);
			// System.out.println(driver.findElement(By.xpath(pro.getProperty("createProductIcon"))).isEnabled());
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("createProductIcon")))); // examining
																														// the
																														// xpath
																														// for
																														// a
																														// search
																														// box
			driver.findElement(By.xpath(pro.getProperty("createProductIcon"))).click();
			Thread.sleep(2000);// pro
			// System.out.println(driver.findElement(By.xpath(pro.getProperty("productType"))).isEnabled());
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("productType")))); // examining
																												// the
																												// xpath
																												// for
																												// a
																												// search
																												// box
			WebElement producttype = driver.findElement(By.xpath(pro.getProperty("productType")));
			producttype.clear();
			Thread.sleep(1000);
			producttype.sendKeys("Service");
			Thread.sleep(2000);// pro
			producttype.sendKeys(Keys.ENTER);

			// open Product master

			System.out.println("clicked on product master master");
			Thread.sleep(3000);// pro

			// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
			// Product Name
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("productName"))));
			// Click on select NA of Product ID
			driver.findElement(By.xpath(pro.getProperty("productName"))).sendKeys(productName);// working
																								// fine
			Thread.sleep(1000);// pro

			// Product Description
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("productDes"))));
			// Click on select NA of Product ID
			driver.findElement(By.xpath(pro.getProperty("productDes"))).sendKeys(productDes);// working
																								// fine
			Thread.sleep(1000);// pro

			// select sequence format
			WebElement format = driver.findElement(By.xpath(pro.getProperty("sequenceformat")));
			format.clear();
			format.sendKeys("NA");
			Thread.sleep(1000);// pro
			format.sendKeys(Keys.ENTER);

			// product ID
			driver.findElement(By.xpath(pro.getProperty("productId"))).click();// clicking
																				// on
																				// NA
			// enter product ID
			driver.findElement(By.xpath(pro.getProperty("productId"))).sendKeys(product_ID);// adding
																							// new
																							// Product
																							// ID

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
			driver.findElement(By.xpath(pro.getProperty("initialPurchasePrice"))).sendKeys("1");// adding
																								// new
																								// Product
																								// ID

			driver.findElement(By.xpath(pro.getProperty("salesTab"))).click();// click
																				// on
																				// sales
			Thread.sleep(2000);// pro
			// ----------------------------------------------------------------------------------------------------------------------
			WebElement sales = driver.findElement(By.xpath(pro.getProperty("salesAccount")));
			sales.sendKeys("Advertising");
			Thread.sleep(2000);// pro
			sales.sendKeys(Keys.DOWN);
			Thread.sleep(2000);// pro
			sales.sendKeys(Keys.ENTER);

			// product ID
			driver.findElement(By.xpath(pro.getProperty("initialSalesPrice"))).click();// clicking
																						// on
																						// NA
			// enter product ID
			driver.findElement(By.xpath(pro.getProperty("initialSalesPrice"))).sendKeys("10");// adding
																								// new
																								// Product
																								// ID

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
			System.out.println("Value of 'warehouse': " + pro.getProperty("warehouse"));

			driver.findElement(By.xpath(pro.getProperty("saveButton"))).click();// click
																				// on
																				// Save
			Thread.sleep(8000);// pro

			// Navigate to home page
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("productReportTab"))));
			// Click on select NA of Product ID
			driver.findElement(By.xpath(pro.getProperty("productReportTab"))).click();// working
																						// fine
			Thread.sleep(1000);// pro

			// wait until close Product will appear
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("closeCreateproductTab"))));
			// Click on close Product
			driver.findElement(By.xpath(pro.getProperty("closeCreateproductTab"))).click();// working
																							// fine
			Thread.sleep(1000);// pro
			setPriceList_MultipleProducts(product_ID, "Purchase Price", "100", driver);
			Thread.sleep(3000);
			// Navigate to home page
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("closeProductReport"))));
			// Click on select NA of Product ID
			driver.findElement(By.xpath(pro.getProperty("closeProductReport"))).click();// working
																						// fine
			Thread.sleep(1000);// pro*/

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ************************************************ Batch Product
	// ****************************************************

	public static void create_BatchProduct(String[] product_ID, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
			String productName = product_ID + "B2 Name";
			String productDes = product_ID + "B2 Description";
			Thread.sleep(3000);// pro
			// wait until component appears
			WebDriverWait wait = new WebDriverWait(driver, 60);
			// System.out.println(driver.findElement(By.xpath(pro.getProperty("createProductIcon"))).isEnabled());
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("createProductIcon")))); // examining
																														// the
																														// xpath
																														// for
																														// a
																														// search
																														// box

			// open Product master
			driver.findElement(By.xpath(pro.getProperty("createProductIcon"))).click();
			System.out.println("clicked on product master master");
			Thread.sleep(3000);// pro

			// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
			// Product Name
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("productName"))));
			// Click on select NA of Product ID
			driver.findElement(By.xpath(pro.getProperty("productName"))).sendKeys(productName);// working
																								// fine
			Thread.sleep(1000);// pro

			// Product Description
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("productDes"))));
			// Click on select NA of Product ID
			driver.findElement(By.xpath(pro.getProperty("productDes"))).sendKeys(productDes);// working
																								// fine
			Thread.sleep(1000);// pro

			// select sequence format
			WebElement format = driver.findElement(By.xpath(pro.getProperty("sequenceformat")));
			format.clear();
			format.sendKeys("NA");
			Thread.sleep(1000);// pro
			format.sendKeys(Keys.ENTER);

			// product ID
			driver.findElement(By.xpath(pro.getProperty("productId"))).click();// clicking
																				// on
																				// NA
			// enter product ID
			driver.findElement(By.xpath(pro.getProperty("productId"))).sendKeys(product_ID + "B2");// adding
																									// new
																									// Product
																									// ID
			Thread.sleep(1000);// pro
			if (driver.findElement(By.xpath(pro.getProperty("BatchCheck"))).isEnabled()) {
				driver.findElement(By.xpath(pro.getProperty("BatchCheck"))).click();// clicking
																					// on
																					// NA
			}

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
			driver.findElement(By.xpath(pro.getProperty("initialPurchasePrice"))).sendKeys("1");// adding
																								// new
																								// Product
																								// ID

			driver.findElement(By.xpath(pro.getProperty("salesTab"))).click();// click
																				// on
																				// sales
			Thread.sleep(2000);// pro
			// ----------------------------------------------------------------------------------------------------------------------
			WebElement sales = driver.findElement(By.xpath(pro.getProperty("salesAccount")));
			sales.sendKeys("Advertising");
			Thread.sleep(2000);// pro
			sales.sendKeys(Keys.DOWN);
			Thread.sleep(2000);// pro
			sales.sendKeys(Keys.ENTER);

			// product ID
			driver.findElement(By.xpath(pro.getProperty("initialSalesPrice"))).click();// clicking
																						// on
																						// NA
			// enter product ID
			driver.findElement(By.xpath(pro.getProperty("initialSalesPrice"))).sendKeys("10");// adding
																								// new
																								// Product
																								// ID

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
			System.out.println("Value of 'warehouse': " + pro.getProperty("warehouse"));

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
			WebElement countable = driver.findElement(By.xpath(pro.getProperty("countable")));
			if (countable.isEnabled()) {
				if (!countable.isSelected()) {
					countable.click();
				}
				WebElement cyclecountfrequency = driver.findElement(By.xpath(pro.getProperty("cyclecountfrequency")));
				cyclecountfrequency.click();
				Robot r3 = new Robot();
				r3.keyPress(KeyEvent.VK_DOWN);
				r3.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(2000);
				List<WebElement> comboItems = driver.findElements(
						By.cssSelector(".x-combo-list[style*='visibility: visible;'] .x-combo-list-item"));
				for (int i = 0; i < comboItems.size(); i++) {
					WebElement item = comboItems.get(i);
					if (item.getText().equals("Daily") || item.getText().equals("Weekly")
							|| item.getText().equals("Fortnightly") || item.getText().equals("Monthly")) {
						item.click();
					}
				}
			}
			driver.findElement(By.xpath(pro.getProperty("saveButton"))).click();// click
																				// on
																				// Save
			Thread.sleep(8000);// pro

			// Navigate to home page
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("productReportTab"))));
			// Click on select NA of Product ID
			driver.findElement(By.xpath(pro.getProperty("productReportTab"))).click();// working
																						// fine
			Thread.sleep(1000);// pro

			// wait until close Product will appear
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("closeCreateproductTab"))));
			// Click on close Product
			driver.findElement(By.xpath(pro.getProperty("closeCreateproductTab"))).click();// working
																							// fine
			Thread.sleep(1000);// pro
			setPriceList_MultipleProducts(product_ID, "Purchase Price", "100", driver);
			Thread.sleep(3000);
			new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(
					By.xpath(".//div[@id='ProductReportGrid_one']/div/div[1]/div[1]/table/tbody/tr/td[1]/input")));
			WebElement search = driver.findElement(
					By.xpath(".//div[@id='ProductReportGrid_one']/div/div[1]/div[1]/table/tbody/tr/td[1]/input"));
			search.sendKeys(product_ID + "B2");
			search.sendKeys(Keys.TAB);
			Utilities.zoomOut();

			Thread.sleep(3000);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			for (int i = 1; i < e + 1; i++) {

				// System.out.println(driver.findElement(By.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				if (driver.findElement(By
						.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Product Name")) {
					System.out.println(driver.findElement(By
							.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText());
					assertEquals(productName,
							driver.findElement(By
									.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (driver.findElement(By
						.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Product ID")) {
					assertEquals(product_ID + "B2",
							driver.findElement(By
									.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (driver.findElement(By
						.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Product Description")) {
					assertEquals(productDes,
							driver.findElement(By
									.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (driver.findElement(By
						.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Default Warehouse")) {
					assertEquals("DS",
							driver.findElement(By
									.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (driver.findElement(By
						.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Default Location")) {
					assertEquals("Default Location",
							driver.findElement(By
									.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

			}
			driver.findElement(By
					.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td[1]/div"))
					.click();

			Utilities.zoomIn();
			Thread.sleep(3000);
			System.out.println(
					"****************************************************Batch product Successfully created and verified*************************************************");

			// Navigate to home page
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("closeProductReport"))));
			// Click on select NA of Product ID
			driver.findElement(By.xpath(pro.getProperty("closeProductReport"))).click();// working
																						// fine
			Thread.sleep(1000);// pro*/
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}

	// ***********************************************************Serial
	// Product*********************************************************************8*

	public static void create_SerialProduct(String[] product_ID, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
			String productName = product_ID + "S3 Name";
			String productDes = product_ID + "S3 Description";
			Thread.sleep(3000);// pro
			// wait until component appears
			WebDriverWait wait = new WebDriverWait(driver, 60);
			// System.out.println(driver.findElement(By.xpath(pro.getProperty("createProductIcon"))).isEnabled());
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("createProductIcon")))); // examining
																														// the
																														// xpath
																														// for
																														// a
																														// search
																														// box

			// open Product master
			driver.findElement(By.xpath(pro.getProperty("createProductIcon"))).click();
			System.out.println("clicked on product master master");
			Thread.sleep(3000);// pro

			// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
			// Product Name
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("productName"))));
			// Click on select NA of Product ID
			driver.findElement(By.xpath(pro.getProperty("productName"))).sendKeys(productName);// working
																								// fine
			Thread.sleep(1000);// pro

			// Product Description
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("productDes"))));
			// Click on select NA of Product ID
			driver.findElement(By.xpath(pro.getProperty("productDes"))).sendKeys(productDes);// working
																								// fine
			Thread.sleep(1000);// pro

			// select sequence format
			WebElement format = driver.findElement(By.xpath(pro.getProperty("sequenceformat")));
			format.clear();
			format.sendKeys("NA");
			Thread.sleep(1000);// pro
			format.sendKeys(Keys.ENTER);

			// product ID
			driver.findElement(By.xpath(pro.getProperty("productId"))).click();// clicking
																				// on
																				// NA
			// enter product ID
			driver.findElement(By.xpath(pro.getProperty("productId"))).sendKeys(product_ID + "S3");// adding
																									// new
																									// Product
																									// ID
			Thread.sleep(1000);// pro
			if (driver.findElement(By.xpath(pro.getProperty("SerialCheck"))).isEnabled()) {
				driver.findElement(By.xpath(pro.getProperty("SerialCheck"))).click();// clicking
																						// on
																						// NA
			}

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
			driver.findElement(By.xpath(pro.getProperty("initialPurchasePrice"))).sendKeys("1");// adding
																								// new
																								// Product
																								// ID

			driver.findElement(By.xpath(pro.getProperty("salesTab"))).click();// click
																				// on
																				// sales
			Thread.sleep(2000);// pro
			// ----------------------------------------------------------------------------------------------------------------------
			WebElement sales = driver.findElement(By.xpath(pro.getProperty("salesAccount")));
			sales.sendKeys("Advertising");
			Thread.sleep(2000);// pro
			sales.sendKeys(Keys.DOWN);
			Thread.sleep(2000);// pro
			sales.sendKeys(Keys.ENTER);

			// product ID
			driver.findElement(By.xpath(pro.getProperty("initialSalesPrice"))).click();// clicking
																						// on
																						// NA
			// enter product ID
			driver.findElement(By.xpath(pro.getProperty("initialSalesPrice"))).sendKeys("10");// adding
																								// new
																								// Product
																								// ID

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
			WebElement countable = driver.findElement(By.xpath(pro.getProperty("countable")));
			if (countable.isEnabled()) {
				if (!countable.isSelected()) {
					countable.click();
				}
				WebElement cyclecountfrequency = driver.findElement(By.xpath(pro.getProperty("cyclecountfrequency")));
				cyclecountfrequency.click();
				Robot r3 = new Robot();
				r3.keyPress(KeyEvent.VK_DOWN);
				r3.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(2000);
				List<WebElement> comboItems = driver.findElements(
						By.cssSelector(".x-combo-list[style*='visibility: visible;'] .x-combo-list-item"));
				for (int i = 0; i < comboItems.size(); i++) {
					WebElement item = comboItems.get(i);
					if (item.getText().equals("Daily") || item.getText().equals("Weekly")
							|| item.getText().equals("Fortnightly") || item.getText().equals("Monthly")) {
						item.click();
					}
				}
			}
			driver.findElement(By.xpath(pro.getProperty("saveButton"))).click();// click
																				// on
																				// Save
			Thread.sleep(8000);// pro

			// Navigate to home page
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("productReportTab"))));
			// Click on select NA of Product ID
			driver.findElement(By.xpath(pro.getProperty("productReportTab"))).click();// working
																						// fine
			Thread.sleep(1000);// pro

			// wait until close Product will appear
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("closeCreateproductTab"))));
			// Click on close Product
			driver.findElement(By.xpath(pro.getProperty("closeCreateproductTab"))).click();// working
																							// fine
			Thread.sleep(1000);// pro

			setPriceList_MultipleProducts(product_ID, "Purchase Price", "100", driver);
			Thread.sleep(3000);
			new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(
					By.xpath(".//div[@id='ProductReportGrid_one']/div/div[1]/div[1]/table/tbody/tr/td[1]/input")));
			WebElement search = driver.findElement(
					By.xpath(".//div[@id='ProductReportGrid_one']/div/div[1]/div[1]/table/tbody/tr/td[1]/input"));
			search.sendKeys(product_ID + "S3");
			search.sendKeys(Keys.TAB);
			Utilities.zoomOut();
			Thread.sleep(3000);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			for (int i = 1; i < e + 1; i++) {

				// System.out.println(driver.findElement(By.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				if (driver.findElement(By
						.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Product Name")) {
					System.out.println(driver.findElement(By
							.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText());
					assertEquals(productName,
							driver.findElement(By
									.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (driver.findElement(By
						.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Product ID")) {
					assertEquals(product_ID + "S3",
							driver.findElement(By
									.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (driver.findElement(By
						.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Product Description")) {
					assertEquals(productDes,
							driver.findElement(By
									.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (driver.findElement(By
						.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Default Warehouse")) {
					assertEquals("DS",
							driver.findElement(By
									.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (driver.findElement(By
						.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Default Location")) {
					assertEquals("Default Location",
							driver.findElement(By
									.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}
			}
			driver.findElement(By
					.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td[1]/div"))
					.click();

			Utilities.zoomIn();
			Thread.sleep(3000);
			System.out.println(
					"****************************************************Serial Product Successfully created and verified*************************************************");

			// Navigate to home page
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("closeProductReport"))));
			// Click on select NA of Product ID
			driver.findElement(By.xpath(pro.getProperty("closeProductReport"))).click();// working
																						// fine

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}

	// ******************************************************************************Batch
	// Serial
	// Product*************************************************************************************************************

	public static void create_BatchSerialProduct(String[] product_ID, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
			String productName = product_ID + "BS4 Name";
			String productDes = product_ID + "BS4 Description";
			Thread.sleep(3000);// pro
			// wait until component appears
			WebDriverWait wait = new WebDriverWait(driver, 60);
			// System.out.println(driver.findElement(By.xpath(pro.getProperty("createProductIcon"))).isEnabled());
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("createProductIcon")))); // examining
																														// the
																														// xpath
																														// for
																														// a
																														// search
																														// box

			// open Product master
			driver.findElement(By.xpath(pro.getProperty("createProductIcon"))).click();
			System.out.println("clicked on product master master");
			Thread.sleep(3000);// pro

			// ---------------------------------------------------------------------------------------------------------------------------------------------------------------
			// Product Name
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("productName"))));
			// Click on select NA of Product ID
			driver.findElement(By.xpath(pro.getProperty("productName"))).sendKeys(productName);// working
																								// fine
			Thread.sleep(1000);// pro

			// Product Description
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("productDes"))));
			// Click on select NA of Product ID
			driver.findElement(By.xpath(pro.getProperty("productDes"))).sendKeys(productDes);// working
																								// fine
			Thread.sleep(1000);// pro

			// select sequence format
			WebElement format = driver.findElement(By.xpath(pro.getProperty("sequenceformat")));
			format.clear();
			format.sendKeys("NA");
			Thread.sleep(1000);// pro
			format.sendKeys(Keys.ENTER);

			// product ID
			driver.findElement(By.xpath(pro.getProperty("productId"))).click();// clicking
																				// on
																				// NA
			// enter product ID
			driver.findElement(By.xpath(pro.getProperty("productId"))).sendKeys(product_ID + "BS4");// adding
																									// new
																									// Product
																									// ID
			Thread.sleep(1000);// pro
			if (driver.findElement(By.xpath(pro.getProperty("SerialCheck"))).isEnabled()) {
				driver.findElement(By.xpath(pro.getProperty("SerialCheck"))).click();// clicking
																						// on
																						// NA
			}

			if (driver.findElement(By.xpath(pro.getProperty("BatchCheck"))).isEnabled()) {
				driver.findElement(By.xpath(pro.getProperty("BatchCheck"))).click();// clicking
																					// on
																					// NA
			}

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
			driver.findElement(By.xpath(pro.getProperty("initialPurchasePrice"))).sendKeys("1");// adding
																								// new
																								// Product
																								// ID

			driver.findElement(By.xpath(pro.getProperty("salesTab"))).click();// click
																				// on
																				// sales
			Thread.sleep(2000);// pro
			// ----------------------------------------------------------------------------------------------------------------------
			WebElement sales = driver.findElement(By.xpath(pro.getProperty("salesAccount")));
			sales.sendKeys("Advertising");
			Thread.sleep(2000);// pro
			sales.sendKeys(Keys.DOWN);
			Thread.sleep(2000);// pro
			sales.sendKeys(Keys.ENTER);

			// product ID
			driver.findElement(By.xpath(pro.getProperty("initialSalesPrice"))).click();// clicking
																						// on
																						// NA
			// enter product ID
			driver.findElement(By.xpath(pro.getProperty("initialSalesPrice"))).sendKeys("10");// adding
																								// new
																								// Product
																								// ID

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
			WebElement countable = driver.findElement(By.xpath(pro.getProperty("countable")));
			if (countable.isEnabled()) {
				if (!countable.isSelected()) {
					countable.click();
				}
				WebElement cyclecountfrequency = driver.findElement(By.xpath(pro.getProperty("cyclecountfrequency")));
				cyclecountfrequency.click();
				Robot r3 = new Robot();
				r3.keyPress(KeyEvent.VK_DOWN);
				r3.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(2000);
				List<WebElement> comboItems = driver.findElements(
						By.cssSelector(".x-combo-list[style*='visibility: visible;'] .x-combo-list-item"));
				for (int i = 0; i < comboItems.size(); i++) {
					WebElement item = comboItems.get(i);
					if (item.getText().equals("Daily") || item.getText().equals("Weekly")
							|| item.getText().equals("Fortnightly") || item.getText().equals("Monthly")) {
						item.click();
					}
				}
			}
			driver.findElement(By.xpath(pro.getProperty("saveButton"))).click();// click
																				// on
																				// Save
			Thread.sleep(8000);// pro

			// Navigate to home page
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("productReportTab"))));
			// Click on select NA of Product ID
			driver.findElement(By.xpath(pro.getProperty("productReportTab"))).click();// working
																						// fine
			Thread.sleep(1000);// pro

			// wait until close Product will appear
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("closeCreateproductTab"))));
			// Click on close Product
			driver.findElement(By.xpath(pro.getProperty("closeCreateproductTab"))).click();// working
																							// fine
			Thread.sleep(1000);// pro

			setPriceList_MultipleProducts(product_ID, "Purchase Price", "100", driver);

			Thread.sleep(3000);
			new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(
					By.xpath(".//div[@id='ProductReportGrid_one']/div/div[1]/div[1]/table/tbody/tr/td[1]/input")));
			WebElement search = driver.findElement(
					By.xpath(".//div[@id='ProductReportGrid_one']/div/div[1]/div[1]/table/tbody/tr/td[1]/input"));
			search.sendKeys(product_ID + "BS4");
			search.sendKeys(Keys.TAB);
			Utilities.zoomOut();

			Thread.sleep(3000);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			for (int i = 1; i < e + 1; i++) {

				// System.out.println(driver.findElement(By.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				if (driver.findElement(By
						.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Product Name")) {
					System.out.println(driver.findElement(By
							.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText());
					assertEquals(productName,
							driver.findElement(By
									.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (driver.findElement(By
						.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Product ID")) {
					assertEquals(product_ID + "BS4",
							driver.findElement(By
									.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (driver.findElement(By
						.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Product Description")) {
					assertEquals(productDes,
							driver.findElement(By
									.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (driver.findElement(By
						.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Default Warehouse")) {
					assertEquals("DS",
							driver.findElement(By
									.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (driver.findElement(By
						.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Default Location")) {
					assertEquals("Default Location",
							driver.findElement(By
									.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}
			}
			driver.findElement(By
					.xpath(".//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td[1]/div"))
					.click();
			Utilities.zoomIn();
			Thread.sleep(3000);
			System.out.println(
					"****************************************************Batch-Serial product Successfully created and verified*************************************************");

			// Navigate to home page
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("closeProductReport"))));
			// Click on select NA of Product ID
			driver.findElement(By.xpath(pro.getProperty("closeProductReport"))).click();// working
																						// fine
			Thread.sleep(1000);// pro*/
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ********************************* CloneEditDelete
	// ********************************
	public static void CopyEditDelete_NormalProduct(String productid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
			Utilities.openProductMaster(driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("quickSearch"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.click_element(pro.getProperty("DocumentCheckBox"), driver);
			String copyProdId = "Copy" + productid;
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";

			// copy
			Utilities.click_element(pro.getProperty("productAndServiceMenuBtn"), driver);
			Utilities.click_element(pro.getProperty("cloneproductAndServiceBtn"), driver);
			Utilities.isElementGone(xpathOfLoading, 120, driver);
			Utilities.enterTextNormalBox(copyProdId, pro.getProperty("productCopyId"), driver);
			Utilities.click_element("//*[text()='Inventory Data']", driver);
			Utilities.enterTextandSelect("Unit", "//div[contains(@id,'x-form-el-uomidclone')]/div/input[2]", driver);
			Utilities.click_element(pro.getProperty("productCopySaveBtn"), driver);
			try {
				driver.findElement(By.xpath("//*[text()='OK']")).click();
			} catch (Exception e) {
				// sometimes Ok don't appear
			}
			Utilities.click_element("//*[contains(@id,'as__clone')]/a[1]", driver);
			// Utilities.click_element(pro.getProperty("productReportTab"),
			// driver);
			System.out.println("**** Product [" + productid + "] Copied successfully !!!");

			// edit
			Utilities.click_element(pro.getProperty("quickSearch"), driver);
			Utilities.enterTextInDropDown(copyProdId, pro.getProperty("quickSearch"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.click_element(pro.getProperty("DocumentCheckBox"), driver);
			Utilities.click_element(pro.getProperty("productAndServiceMenuBtn"), driver);
			Utilities.click_element(pro.getProperty("editproductAndServiceBtn"), driver);
			Utilities.isElementGone(xpathOfLoading, 120, driver);
			Utilities.enterTextNormalBox("**** Performing EDIT test case !!!! *****",
					"//textarea[contains(@id,'foreigndescription')]", driver);
			Utilities.HoverandClick("//*[text()='Inventory Data']", driver);
			Utilities.enterTextandSelect("Unit", pro.getProperty("editUOMid"), driver);
			Utilities.click_element(pro.getProperty("editproductSaveBtn"), driver);
			try {
				driver.findElement(By.xpath("//*[text()='OK']")).click();
			} catch (Exception e) {
				// sometimes Ok don't appear
			}
			Utilities.click_element(pro.getProperty("productReportTab"), driver);
			System.out.println("**** Product [" + copyProdId + "] Edited successfully !!!");

			// delete
			Utilities.click_element(pro.getProperty("quickSearch"), driver);
			Utilities.enterTextInDropDown(copyProdId, pro.getProperty("quickSearch"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.click_element(pro.getProperty("DocumentCheckBox"), driver);
			Utilities.click_element(pro.getProperty("productAndServiceMenuBtn"), driver);
			Utilities.click_element(pro.getProperty("deleteproductAndServicePermanentlyBtn"), driver);
			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 0, driver);

			// verify flow
			Utilities.click_element(pro.getProperty("quickSearch"), driver);
			Utilities.enterTextInDropDown(copyProdId, pro.getProperty("quickSearch"), driver);
			Utilities.isLoadingDisappear(120, driver);
			WebElement confirmation = new WebDriverWait(driver, 10).until(ExpectedConditions
					.elementToBeClickable(By.xpath("//a[contains(text(),'Get Started by adding a Product now')]")));
			if (confirmation.isDisplayed()) {
				System.out.println("**** Product [" + copyProdId + "] is DELETED successfully !!!");
				driver.navigate().refresh();
				Utilities.isLoadingDisappear(120, driver);
			} else {
				System.out.println("**** Product [" + copyProdId + "] is NOT DELETED plz check  !!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
		} catch (Exception EX) {
			System.out.println("**** Product [" + productid + "] is NOT DELETED plz check  !!!");
			Utilities.handleError(EX, driver);
		}
	}

	// **************************** Muliple Product Price List
	// **********************************
	public static void setPriceList_MultipleProducts(String[] productIDs, String priceType, String amount,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
			Utilities.openProductMaster(driver);
			Utilities.HoverandClick(pro.getProperty("priceListReportButton"), driver);
			Utilities.HoverandClick(pro.getProperty("generatePriceListReportButton"), driver);
			Utilities.HoverandClick(pro.getProperty("addButton"), driver);

			// Add price type
			Utilities.enterTextandSelect(priceType, "//*[@id='carryin']/following::input[1]", driver);

			// Select Products
			String drpDwnArrow = "//*[contains(@style,'visible')]//*[@id='productid']/following::span[1]/img[2]";
			String itemInputBoxLocator = "//*[contains(@style,'visible')]//*[@id='productid']/following::input[1]";
			for (int i = 0; i < productIDs.length; i++) {
				Utilities.click_element(drpDwnArrow, driver);
				Utilities.enterTextNormalBox(productIDs[i], itemInputBoxLocator, driver);
				Utilities.click_element("//*[contains(@style,'visible')]//*[text()='" + productIDs[i] + "']", driver);
				Utilities.click_element(drpDwnArrow, driver);
			}

			// set UOM
			Utilities.enterTextandSelect("Unit", ".//*[@id='uomidpricewindow']", driver);
			// set Price
			Utilities.enterTextNormalBox(amount, "//*[@class='currency-view']/parent::label/following::div[1]/input",
					driver);

			Utilities.HoverandClick(pro.getProperty("savePriceButton"), driver);

			try {
				// if price list is already set
				WebElement button = driver.findElement(By.xpath("//button[text()='Yes']"));
				if (button.isDisplayed()) {
					Thread.sleep(1000);
					button.click();
				}
				System.out.println("**** Updated : new price list as [" + priceType + "] for prducts = "
						+ Arrays.toString(productIDs));
			} catch (Exception Ex) {
				// Continue as New Product
			}

			try {
				Utilities.clickButton("OK", 0, driver);
				System.out.println("Price list for [" + Arrays.toString(productIDs) + "] successfully Generated !!");
			} catch (Exception Ex) {
				System.out.println("**** WARNING !! Price list is NOT generated for [" + Arrays.toString(productIDs)
						+ "] *********!!!");
			}

			Utilities.HoverandClick(pro.getProperty("closePriceReport"), driver);
			Utilities.click_element(pro.getProperty("CloseReport"), driver);

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}
	// ** * * * * * * ** * * EXPORT * * * * * * ** * * * ** * * * * *

	public static void Export_Product(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
			WebElement element = null;

			Utilities.openProductMaster(driver);
			Utilities.selectRecords(5, driver);
			Utilities.clickCheckBox(pro.getProperty("AllDocumentCheckBox"), "check", driver);
			// CSV File
			Utilities.clickButton("Export selected Record(s)", 500, driver);
			Utilities.HoverandClick(pro.getProperty("ExportToCSV"), driver);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
			element.click();
			Thread.sleep(1000);
			SikulliUtil.sikulli_waitClick(driver, "CSVtype");
			SikulliUtil.sikulli_waitClick(driver, "ClsX");
			System.out.println("* * * * * * EXPORT for [.CSV] completed successfully * * * * * * *");
			Thread.sleep(1500);

			// PDF
			Utilities.clickButton("Export selected Record(s)", 500, driver);
			String parentWindow = driver.getWindowHandle();
			Utilities.HoverandClick(pro.getProperty("ExportToPDF"), driver);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("pdfTemplate"))));
			element.click();
			Thread.sleep(1000);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
			element.click();
			Thread.sleep(1000);
			SikulliUtil.sikulli_waitClick(driver, "PDFtype");
			Thread.sleep(1500);
			Set s = driver.getWindowHandles(); // this method will gives you the
												// handles of all opened windows
			Iterator ite = s.iterator();
			while (ite.hasNext()) {
				String childWindow = ite.next().toString();
				if (!childWindow.contains(parentWindow)) {
					driver.switchTo().window(childWindow);
					Thread.sleep(1000);
					System.out.println("* * * * * * EXPORT for [PDF] completed successfully * * * * * * *");
					driver.close();
					driver.switchTo().window(parentWindow);
					Thread.sleep(1000);
				}
			}

			// Excel
			Utilities.clickButton("Export selected Record(s)", 500, driver);
			Utilities.HoverandClick(pro.getProperty("ExportToExcel"), driver);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
			element.click();
			Thread.sleep(1000);
			SikulliUtil.sikulli_waitClick(driver, "XLStype");
			SikulliUtil.sikulli_waitClick(driver, "ClsX");
			System.out.println("* * * * * * EXPORT for [.Excel] completed successfully * * * * * * *");
			Thread.sleep(1500);

			Utilities.HoverandClick(pro.getProperty("closeProductReport"), driver);
			System.out.println("* * * * * * EXPORT for [PRODUCT MASTER] completed successfully * * * * * * *");
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [PRODUCT MASTER] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}
	}

	// get button path for Specific Module
	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;

	}

	// ******** Delete all Imported Data **********************

	public static void deleteImport_Products(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
			Utilities.waitandClick(pro.getProperty("createProductIcon"), driver);
			Thread.sleep(1500);
			Utilities.HoverandClick(pro.getProperty("closeCreateproductTab"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown("Automation9", pro.getProperty("quickSearch"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);

			// delete
			Utilities.clickCheckBox("//*[text()='Product ID']/ancestor::tr/td[1]/div/div", "check", driver);
			Utilities.HoverandClick(pro.getProperty("productAndServiceMenuBtn"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteproductAndServicePermanentlyBtn"), driver);
			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 1000, driver);
			Thread.sleep(2000);// pro

			// verify flow
			Utilities.enterTextInDropDown("Automation9", pro.getProperty("quickSearch"), driver);
			Thread.sleep(1500);
			WebElement confirmation = new WebDriverWait(driver, 10).until(ExpectedConditions
					.elementToBeClickable(By.xpath("//a[contains(text(),'Get Started by adding a Product now')]")));
			if (confirmation.isDisplayed()) {
				System.out.println("**** ALL Imported Products are DELETED successfully !!!");
			} else {
			}

			Utilities.refresh();

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ********************Cretae with Proper Name
	// *********************************************
	public static void create_Product_Proper(String productid, String productName, String purchaseAccount,
			String salesAccount, WebDriver driver) throws IOException, InterruptedException, AWTException {
		Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
		String productDes = productName;
		String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";
		try {
			Utilities.click_element(pro.getProperty("createProductIcon"), driver);
			// ------------- General Tab
			// ----------------------------------------------
			Utilities.click_element(pro.getProperty("productName"), driver);
			Utilities.enterTextNormalBox(productName, pro.getProperty("productName"), driver);
			Utilities.enterTextNormalBox(productDes, pro.getProperty("productDes"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceformat"), driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("productId"), driver);
			Utilities.enterTextNormalBox("1000", pro.getProperty("initialquantity"), driver);
			Utilities.click_element(pro.getProperty("productId"), driver);
			try {
				Utilities.clickButton("Yes", 1000, driver);
			} catch (Exception ex) {

			}
			Thread.sleep(1000);

			// enter WareHouse
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
			Utilities.click_element("//*[contains(@style,'visible')]//button[text()='Submit']", driver);

			// ------------- Purchase Tab
			// ----------------------------------------------
			Utilities.HoverandClick(pro.getProperty("purchaseTab"), driver);
			Utilities.enterTextandSelect(purchaseAccount, pro.getProperty("purchaseAccount"), driver);
			Utilities.enterTextNormalBox("10", pro.getProperty("initialPurchasePrice"), driver);

			// ------------- Sales Tab
			// ----------------------------------------------
			Utilities.HoverandClick(pro.getProperty("salesTab"), driver);
			Utilities.enterTextandSelect(salesAccount, pro.getProperty("salesAccount"), driver);
			Utilities.enterTextNormalBox("20", pro.getProperty("initialSalesPrice"), driver);

			// ------------- Inventory Tab
			// ----------------------------------------------
			Utilities.HoverandClick(pro.getProperty("inventoryDataTab"), driver);
			Utilities.enterTextandSelect("Unit", pro.getProperty("umo"), driver);
			Utilities.enterTextandSelect("DS - Default Store", pro.getProperty("warehouse"), driver);
			Utilities.enterTextandSelect("Default Location", pro.getProperty("location"), driver);
			Utilities.HoverandClick(pro.getProperty("saveButton"), driver);
			Utilities.isElementGone(xpathOfLoading, 120, driver);
			try {
				productAfterSaveOKBtn(driver);
			} catch (Exception noOk) {
				// System.out.println("No Ok button");
			}

			Utilities.click_element(pro.getProperty("closeProductReport"), driver);
			Utilities.click_element(pro.getProperty("CloseMainProductTab"), driver);
			System.out.println("********** Product[ " + productid + " ]  Successfully created *************");
		} catch (Exception Ex) {
			Utilities.handleError(Ex, driver);
		}
	}

}
