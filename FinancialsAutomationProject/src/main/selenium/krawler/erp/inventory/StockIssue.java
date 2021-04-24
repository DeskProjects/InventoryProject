package krawler.erp.inventory;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import krawler.erp.page.Utilities;
import krawler.erp.utils.HandlePrintWindow;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

import com.google.common.base.Function;

public class StockIssue {

	// ********************************************************StockIssue Store
	// Creation*********************************************************
	public static void create_StoreMasterItem(String storeCode, String storeDesc, String storeType, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		boolean isStoreExists = false;
		try {

			final Properties pro = Utilities.fetchProValue("OR_StockIssue.properties");
			Utilities.refresh();
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			WebElement element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					return webDriverArg.findElement(By.xpath(pro.getProperty("MastersExpander")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					return webDriverArg.findElement(By.xpath(pro.getProperty("InventoryMasterLink")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					return webDriverArg.findElement(By.xpath(pro.getProperty("StoreMasterLink")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					return webDriverArg.findElement(By.xpath(pro.getProperty("QuickSearchInput")));
				}
			});
			element.click();
			element.sendKeys(storeCode);
			Thread.sleep(1000);
			element.sendKeys(Keys.ENTER);
			Thread.sleep(4000);
			if (driver
					.findElement(
							By.xpath("//div[@id='invstoremastergrid']/div/div[3]/div/table/following-sibling::div"))
					.getText().equalsIgnoreCase("No data to display")) {
				System.out.println(storeCode + " : " + storeDesc + " doesn't exist");
			} else {
				System.out.println(storeCode + " : " + storeDesc + " exist");
				isStoreExists = true;
			}
			if (!isStoreExists) {
				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						return webDriverArg.findElement(By.xpath(pro.getProperty("NewStoreAddBtn")));
					}
				});
				element.click();
				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						return webDriverArg.findElement(By.xpath(pro.getProperty("NewStoreCodeInput")));
					}
				});
				element.click();
				element.sendKeys(storeCode);

				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						return webDriverArg.findElement(By.xpath(pro.getProperty("NewStoreDescInput")));
					}
				});
				element.click();
				element.sendKeys(storeDesc);

				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						return webDriverArg.findElement(By.xpath(pro.getProperty("NewStoreAddressInput")));
					}
				});
				element.click();
				element.sendKeys("Test Address for store " + storeCode);
				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						return webDriverArg.findElement(By.xpath(pro.getProperty("NewStoreTypeInput")));
					}
				});
				element.click();
				Thread.sleep(1000);
				Robot reasonRobot = new Robot();
				reasonRobot.keyPress(KeyEvent.VK_DOWN);
				reasonRobot.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);
				// Utilities.sendText(storeType);
				List<WebElement> comboItems = driver.findElements(
						By.cssSelector(".x-combo-list[style*='visibility: visible;'] .x-combo-list-item"));
				for (int i = 0; i <= comboItems.size(); i++) {
					WebElement item = comboItems.get(i);
					if (item.getText().equals(storeType)) {
						item.click();
						break;
					}
				}
				Thread.sleep(1000);
				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						return webDriverArg.findElement(By.xpath(pro.getProperty("NewStoreManagerInput")));
					}
				});
				element.click();
				Thread.sleep(2000);
				Robot nsmi = new Robot();
				nsmi.keyPress(KeyEvent.VK_DOWN);
				nsmi.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);

				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						return webDriverArg.findElement(By.xpath(
								"//div[@id='StoreformIdAddstoreMasterGrid']/following-sibling::div[3]/div/div/table/tbody/tr/td"));
					}
				});
				element.click();
				Thread.sleep(1000);
				nsmi.keyPress(KeyEvent.VK_TAB);
				nsmi.keyRelease(KeyEvent.VK_TAB);
				Thread.sleep(1000);

				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						return webDriverArg.findElement(By.xpath(pro.getProperty("NewStoreExecutiveInput")));
					}
				});
				element.click();
				Thread.sleep(2000);
				Robot nsei = new Robot();
				nsei.keyPress(KeyEvent.VK_DOWN);
				nsei.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);

				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						return webDriverArg.findElement(By.xpath(
								"//div[@id='StoreformIdAddstoreMasterGrid']/following-sibling::div[4]/div/div/table/tbody/tr/td"));
					}
				});
				element.click();
				Thread.sleep(1000);
				nsei.keyPress(KeyEvent.VK_TAB);
				nsei.keyRelease(KeyEvent.VK_TAB);
				Thread.sleep(1000);

				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						return webDriverArg.findElement(By.xpath(pro.getProperty("NewStoreSubmitBtn")));
					}
				});
				element.click();
				Thread.sleep(3000);

				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_SPACE);
				r.keyRelease(KeyEvent.VK_SPACE);
				Thread.sleep(1000);
				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						return webDriverArg.findElement(By.xpath(pro.getProperty("QuickSearchInput")));
					}
				});
				element.click();
				element.clear();
				element.sendKeys(storeCode);
				Thread.sleep(2000);
				if (driver
						.findElement(
								By.xpath("//div[@id='invstoremastergrid']/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
					System.out.println(storeCode + " : " + storeDesc + " created successfully");
				} else {
					System.out.println(storeCode + " : " + storeDesc + " creation failed");
				}

			} else {

				System.out.println(storeCode + " : " + storeDesc + " exist so won't creating new one");

			}

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}
	}

	// -------------- Create Stock Issue --------------
	public static void create_StockIssue(String documentid, String productid, String fromStore, String toStore,
			String quanityValue, WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_StockIssue.properties");
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);
			documentid = "StockIssue" + documentid;

			Utilities.HoverandClick(pro.getProperty("StockIssueLink"), driver);
			Utilities.disableExpander(driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("StockIssueSequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("StockIssueDocumentId"), driver);
			// From Store
			Utilities.HoverandClick(pro.getProperty("StockIssueFromStoreArrow"), driver);
			Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='" + fromStore + "']", driver);
			// To Store
			Utilities.enterTextandSelect(toStore, pro.getProperty("StockIssueToStore"), driver);

			Utilities.HoverandClick(pro.getProperty("StockIssueAddPrdButton"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("StockIssueSearch"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			Utilities.click_element(pro.getProperty("StockIssueAddButton"), driver);
			Thread.sleep(2000);

			int totalPrd = Utilities.totalSize(
					"//*[@id='goodissue111goodsIssueParentTab']//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div",
					driver);
			int headerSize = Utilities.totalSize(
					"//*[@id='goodissue111goodsIssueParentTab']//*[text()='Product ID']/ancestor::tr/td/div", driver);

			for (int i = 1; i < totalPrd; i++) {
				for (int j = 1; j <= headerSize; j++) {
					String headerName = driver.findElement(By
							.xpath("//*[@id='goodissue111goodsIssueParentTab']//*[text()='Product ID']/ancestor::tr/td["
									+ j + "]/div"))
							.getText();
					if (headerName.startsWith("Quantity")) {
						Utilities.HoverandClick(
								"//*[@id='goodissue111goodsIssueParentTab']//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div["
										+ i + "]/table/tbody/tr/td[" + j + "]/div",
								driver);
						Utilities.enter_LinelabelAmount(quanityValue, driver);
					}
				}
			}
			// ----- Enter Qauntity & serial
			for (int k = 1; k < totalPrd; k++) {
				String xpathOflocation = null;
				xpathOflocation = "//*[contains(@id,'goodissue111goodsIssueParentTab')]//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div["
						+ k + "]/table/tbody/tr//*[@class='pwnd serialNo-gridrow']";
				Utilities.HoverandClick(xpathOflocation, driver);
				Thread.sleep(1500);

				int headerTransferSize = 0;
				headerTransferSize = Utilities.totalSize("//*[text()='Issue Location ']/ancestor::tr/td/div", driver);
				for (int head = 1; head <= headerTransferSize; head++) {
					String headName = driver
							.findElement(By.xpath("//*[text()='Issue Location ']/ancestor::tr/td[" + head + "]/div"))
							.getText();
					if (headName.equalsIgnoreCase("Quantity*")) {
						Utilities.HoverandClick(
								"//*[text()='Issue Location ']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ head + "]/div",
								driver);
						Utilities.enter_LinelabelAmount(quanityValue, driver);
						Utilities.enter_LinelabelAmount_thenEnter(driver);
					}

					if (headName.equalsIgnoreCase("Serials*")) {
						Utilities.HoverandClick(
								"//*[text()='Issue Location ']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ head + "]/div",
								driver);
						Thread.sleep(1000);
						// transfer serials
						for (int serial = 1; serial <= Integer.parseInt(quanityValue); serial++) {
							Utilities
									.HoverandClick(
											"//*[@id='serialSelectWindow']//*[@class='x-column-inner']/div[1]//*[text()='Name']/ancestor::div[3]/following::div[1]/div/div["
													+ serial + "]/table/tbody/tr//*[@class='x-grid3-row-checker']",
											driver);
						}
						// transfer
						Utilities.HoverandClick("//*[@id='serialSelectWindow']//*[contains(@src,'images/arrowright')]",
								driver);
						Utilities.HoverandClick("//*[@id='serialSelectWindow']//*[text()='Submit']", driver);
					}

					if (headName.equalsIgnoreCase("Collect Location *")) {
						Utilities.HoverandClick(
								"//*[text()='Issue Location ']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ head + "]/div",
								driver);
						Thread.sleep(250);
						Utilities.enterTextandSelect("Default Location", "//*[contains(@style,'visible')]/div/input",
								driver);
					}
				}
				Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Save']", driver); // save
																										// button
				Thread.sleep(1000);
			}

			String ParentWindow = driver.getWindowHandle(); // beforesave button
			Utilities.HoverandClick(pro.getProperty("saveStockIssueBtn"), driver);
			Utilities.clickButton("Yes", 600, driver);
			Utilities.clickButton("OK", 1000, driver);
			HandlePrintWindow.closePrintWindow(ParentWindow, driver);
			Utilities.HoverandClick(pro.getProperty("StockIssueCloseBtn"), driver);
			System.out.println("******** Stock Issue :[" + documentid + "] has been succesfully Created ********");
		} catch (Exception EX) {
			System.out.println(
					"!!!!!!!!!!!!!!!! FAILED to create Stock Issue :[" + documentid + "] check log !!!!!!!!!!!!!!!! ");
			Utilities.handleError(EX, driver);
		}
	}

	// ********************** StockIssue Fulfilled Orders
	// ****************************************
	public static void FulfilledOrders_StockIssue(String documentid, String productid, String fromStore, String toStore,
			String IssuesQty, String DeliveredQty, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_StockIssue.properties");
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);
			Utilities.HoverandClick(pro.getProperty("StockIssueLink"), driver);
			Utilities.HoverandClick(pro.getProperty("StockIssueFulfilledOrdersTabLink"), driver);
			Utilities.disableExpander(driver);

			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			Utilities.clickCheckBox(
					"//*[contains(@id,'goodsapprovedgoodsIssue')]//*[text()='To Store']/ancestor::div[3]/following::div[1]/div/div/div[1]/div",
					"uncheck", driver);

			int headerSize = Utilities.totalSize(
					"//*[contains(@id,'goodsapprovedgoodsIssue')]//*[text()='To Store']/ancestor::tr/td/div", driver);
			int indOfFrmStore = 0, indOftoStore = 0, indOfissuQty = 0, indOfdeli = 0, indOfprd = 0;
			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver.findElement(
						By.xpath("//*[contains(@id,'goodsapprovedgoodsIssue')]//*[text()='To Store']/ancestor::tr/td["
								+ i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("From Store")) {
					indOfFrmStore = i;
				}
				if (headerName.equalsIgnoreCase("To Store")) {
					indOftoStore = i;
				}
				if (headerName.equalsIgnoreCase("Issued Quantity")) {
					indOfissuQty = i;
				}
				if (headerName.equalsIgnoreCase("Delivered Quantity")) {
					indOfdeli = i;
				}
				if (headerName.equalsIgnoreCase("Product ID")) {
					indOfprd = i;
				}
			}

			int totalProducts = Utilities.totalSize(
					"//*[contains(@id,'goodsapprovedgoodsIssue')]//*[text()='To Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div",
					driver);
			boolean flag = true;
			for (int j = 1; j <= totalProducts; j++) {
				String ProdID = null;
				String actualFromStore = null;
				String actualToStore = null;
				String actualIssuedQuantity = null;
				String actualDeliveredQuantity = null;

				ProdID = driver.findElement(By
						.xpath("//*[contains(@id,'goodsapprovedgoodsIssue')]//*[text()='To Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfprd + "]/div"))
						.getText();
				actualFromStore = driver.findElement(By
						.xpath("//*[contains(@id,'goodsapprovedgoodsIssue')]//*[text()='To Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfFrmStore + "]/div"))
						.getText();
				actualToStore = driver.findElement(By
						.xpath("//*[contains(@id,'goodsapprovedgoodsIssue')]//*[text()='To Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOftoStore + "]/div"))
						.getText();
				actualIssuedQuantity = driver.findElement(By
						.xpath("//*[contains(@id,'goodsapprovedgoodsIssue')]//*[text()='To Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfissuQty + "]/div"))
						.getText();
				actualDeliveredQuantity = driver.findElement(By
						.xpath("//*[contains(@id,'goodsapprovedgoodsIssue')]//*[text()='To Store']/ancestor::div[3]/following::div[1]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + indOfdeli + "]/div"))
						.getText();

				if (actualFromStore.equalsIgnoreCase(fromStore) && actualToStore.equalsIgnoreCase(toStore)
						&& actualIssuedQuantity.equalsIgnoreCase(IssuesQty)
						&& actualDeliveredQuantity.equalsIgnoreCase(DeliveredQty)) {
					System.out.println("**** Verified Stock Issue for [" + documentid + "] with [" + ProdID + "] ["
							+ actualFromStore + "] [" + actualToStore + "] [" + actualIssuedQuantity + "] ["
							+ actualDeliveredQuantity + "] **********");
				} else {
					System.out.println("!!!!!!! FAILED to verify Stock Issue for [" + documentid + "] with [" + ProdID
							+ "] [" + actualFromStore + "] [" + actualToStore + "] [" + actualIssuedQuantity + "] ["
							+ actualDeliveredQuantity + "] !!!!!!! ");
					flag = false;
				}
			}

			if (flag == false) {
				System.out.println(
						"!!!!!!!!!!!!!! Failed to verify Stock Issue [" + documentid + "] !!!!!!!!!!!!!!!!!!!!");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			Utilities.HoverandClick(pro.getProperty("StockIssueCloseBtn"), driver);
			System.out.println("************** VERIFIED Stock Issue [" + documentid + "] successfully **************");
		} catch (Exception EX) {
			System.out.println("!!!!!!!!!!!!!! Failed to verify Stock Issue [" + documentid + "] !!!!!!!!!!!!!!!!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// *********Nitesh Sir
	// :***********************************************StockIssue Fulfilled
	// Orders*********************************************************
	public static void Fulfilled_Orders_StockIssue(String documentid, String productid, String fromStore,
			String toStore, String quanityValue, String issuedQuanityValue, String collectedQuanityValue,
			WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_StockIssue.properties");
			String[] storeDescriptions = fetch_StoreDescription(new String[] { fromStore, toStore }, driver);

			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			WebElement element = null;

			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);

			Utilities.HoverandClick(pro.getProperty("StockIssueLink"), driver);
			Thread.sleep(3000);
			Utilities.HoverandClick(pro.getProperty("StockIssueFulfilledOrdersTabLink"), driver);
			Utilities.clickExpander(driver);

			Thread.sleep(2000);
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("QuickSearch")));
				}
			});
			element.sendKeys(documentid);
			Thread.sleep(5000);
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("QuickSearchResultCount")));
				}
			});

			String resultText = element.getText();
			String[] resultTextArray = resultText.split("of");
			String resultSetTotalCntString = "";
			for (String resultTextElement : resultTextArray) {
				resultSetTotalCntString = resultTextElement;
			}
			int resultSetTotalCnt = Integer.parseInt(resultSetTotalCntString.trim());
			int tableHeadersCnt = driver
					.findElements(By
							.xpath(".//div[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td/div"))
					.size();
			Thread.sleep(1000);
			for (int i = 1; i < tableHeadersCnt + 1; i++) {
				String header = driver.findElement(By
						.xpath(".//div[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Order Note No.")) {
					for (int j = 1; j <= resultSetTotalCnt; j++) {
						assertEquals(documentid,
								driver.findElement(By
										.xpath(".//div[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div["
												+ j + "]/table/tbody/tr/td[" + i + "]/div"))
										.getText());
					}
				} else if (header.equals("From Store")) {
					for (int j = 1; j <= resultSetTotalCnt; j++) {
						assertEquals(storeDescriptions[0],
								driver.findElement(By
										.xpath(".//div[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div["
												+ j + "]/table/tbody/tr/td[" + i + "]/div"))
										.getText());
					}
				} else if (header.equals("To Store")) {
					for (int j = 1; j <= resultSetTotalCnt; j++) {
						assertEquals(storeDescriptions[1],
								driver.findElement(By
										.xpath(".//div[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div["
												+ j + "]/table/tbody/tr/td[" + i + "]/div"))
										.getText());
					}
				} else if (header.equals("Product ID")) {
					for (int j = 1; j <= resultSetTotalCnt; j++) {
						int index = 0;
						String productIDStr = driver.findElement(By
								.xpath(".//div[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div["
										+ j + "]/table/tbody/tr/td[" + i + "]/div"))
								.getText();
						System.out.println("productIDStr = " + productIDStr);
						if (productIDStr.endsWith("S3")) {
							index = 4;
						} else if (productIDStr.endsWith("B2")) {
							index = 2;
						} else if (productIDStr.endsWith("BS4")) {
							index = 3;
						} else if (productIDStr.endsWith("S3")) {
							index = 1;
						}
						switch (index) {
						case 1:
							assertEquals(productid,
									driver.findElement(By
											.xpath(".//div[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div["
													+ j + "]/table/tbody/tr/td[" + i + "]/div"))
											.getText());
							System.out.println("Verified Normal Product = " + productid);
							break;
						case 2:
							assertEquals(
									productid
											+ "B2",
									driver.findElement(By
											.xpath(".//div[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div["
													+ j + "]/table/tbody/tr/td[" + i + "]/div"))
											.getText());
							System.out.println("Verified Batch Product =" + productid + "B2");
							break;
						case 3:
							assertEquals(
									productid
											+ "BS4",
									driver.findElement(By
											.xpath(".//div[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div["
													+ j + "]/table/tbody/tr/td[" + i + "]/div"))
											.getText());
							System.out.println("Verified Batch Serial Product = " + productid + "BS4");
							WebElement batchSerialProductRowExpander = driver.findElement(By
									.xpath(".//*[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div["
											+ j + "]/table/tbody/tr[1]/td[3]/div/div"));
							batchSerialProductRowExpander.click();
							int batchSerialProductRowExpanderRow = driver.findElements(By
									.xpath(".//*[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div["
											+ j + "]/table/tbody/tr[2]/td/div/table/tbody/tr/th"))
									.size();
							String[] issuedSerialsFromFile = Utilities.readSerialsFromFile(
									documentid + "StockIssueserials" + Utilities.currentDate("dd-MM-yyyy") + ".txt");
							Arrays.sort(issuedSerialsFromFile);
							StringBuilder stb = null;
							String issuedSerialsFromFileCommaSep = null;

							if (issuedSerialsFromFile != null && issuedSerialsFromFile.length > 0) {
								for (int k = 0; k <= issuedSerialsFromFile.length - 1; k++) {
									if (issuedSerialsFromFile[k].contains(productid + "BS4")) {
										issuedSerialsFromFileCommaSep = issuedSerialsFromFile[k].substring(
												issuedSerialsFromFile[k].indexOf(",") + 2,
												issuedSerialsFromFile[k].length());
									}

								}

								stb = new StringBuilder();
								/*
								 * for (String issuedSerialsFromFileElement :
								 * issuedSerialsFromFile) {
								 * stb.append(issuedSerialsFromFileElement).
								 * append(", "); } issuedSerialsFromFileCommaSep
								 * = stb.substring(0, stb.length()-2);
								 */
							}
							String[] collectedSerialsFromFile = Utilities.readSerialsFromFile(documentid
									+ "StockCollectedserials" + Utilities.currentDate("dd-MM-yyyy") + ".txt");
							if (collectedSerialsFromFile != null && collectedSerialsFromFile.length > 0) {
								Arrays.sort(collectedSerialsFromFile);
							}
							stb = null;
							String collectedSerialsFromFileCommaSep = null;
							if (collectedSerialsFromFile != null && collectedSerialsFromFile.length > 0) {
								stb = new StringBuilder();
								for (String collectedSerialsFromFileElement : collectedSerialsFromFile) {
									stb.append(collectedSerialsFromFileElement).append(", ");
								}
								collectedSerialsFromFileCommaSep = stb.substring(0, stb.length() - 2);
							}
							for (int k = 1; k <= batchSerialProductRowExpanderRow; k++) {
								String expanderRowHeader = driver.findElement(By
										.xpath(".//*[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div["
												+ j + "]/table/tbody/tr[2]/td/div/table/tbody/tr/th[" + k + "]"))
										.getText();
								if (expanderRowHeader.equals("Issued Serials")) {// here
									String issuedSerials = driver.findElement(By
											.xpath(".//*[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div["
													+ j + "]/table/tbody/tr[2]/td/div/table/tbody/tr[3]/td[" + k + "]"))
											.getText();
									if (issuedSerialsFromFileCommaSep != null) {
										System.out
												.println("issued Serials From File = " + issuedSerialsFromFileCommaSep);
										System.out.println("issued Serials From UI = " + issuedSerials);
										assertEquals(issuedSerialsFromFileCommaSep, issuedSerials);
									}
								} else if (expanderRowHeader.equals("Collected Serials")) {
									String issuedSerials = driver.findElement(By
											.xpath(".//*[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div["
													+ j + "]/table/tbody/tr[2]/td/div/table/tbody/tr[3]/td[" + k + "]"))
											.getText();
									if (collectedSerialsFromFileCommaSep != null) {
										System.out.println(
												"Collected Serials From File = " + collectedSerialsFromFileCommaSep);
										System.out.println("Collected Serials From UI = " + issuedSerials);
										assertEquals(collectedSerialsFromFileCommaSep, issuedSerials);
									}
								} else if (expanderRowHeader.equals("Issued Quantity")) {
									String issuedQty = driver.findElement(By
											.xpath(".//*[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div["
													+ j + "]/table/tbody/tr[2]/td/div/table/tbody/tr[3]/td[" + k + "]"))
											.getText();
									assertEquals(issuedQuanityValue, issuedQty);
								} else if (expanderRowHeader.equals("Collected Quantity")) {
									String issuedQty = driver.findElement(By
											.xpath(".//*[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div["
													+ j + "]/table/tbody/tr[2]/td/div/table/tbody/tr[3]/td[" + k + "]"))
											.getText();
									assertEquals(collectedQuanityValue, issuedQty);
								}

							}
							break;
						case 4:
							assertEquals(
									productid
											+ "S3",
									driver.findElement(By
											.xpath(".//div[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div["
													+ j + "]/table/tbody/tr/td[" + i + "]/div"))
											.getText());
							System.out.println("Verified Serial Product = " + productid + "S3");
							batchSerialProductRowExpander = driver.findElement(By
									.xpath(".//*[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div["
											+ j + "]/table/tbody/tr[1]/td[3]/div/div"));
							batchSerialProductRowExpander.click();
							batchSerialProductRowExpanderRow = driver.findElements(By
									.xpath(".//*[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div["
											+ j + "]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/th"))
									.size();
							issuedSerialsFromFile = Utilities.readSerialsFromFile(
									documentid + "StockIssueserials" + Utilities.currentDate("dd-MM-yyyy") + ".txt");
							Arrays.sort(issuedSerialsFromFile);
							stb = null;
							issuedSerialsFromFileCommaSep = null;
							if (issuedSerialsFromFile != null && issuedSerialsFromFile.length > 0) {
								for (int k = 0; k <= issuedSerialsFromFile.length - 1; k++) {
									if (issuedSerialsFromFile[k].contains(productid + "S3")) {
										issuedSerialsFromFileCommaSep = issuedSerialsFromFile[k].substring(
												issuedSerialsFromFile[k].indexOf(",") + 2,
												issuedSerialsFromFile[k].length());
									}

								}

								stb = new StringBuilder();
								/*
								 * for (String issuedSerialsFromFileElement :
								 * issuedSerialsFromFile) {
								 * stb.append(issuedSerialsFromFileElement).
								 * append(", "); } issuedSerialsFromFileCommaSep
								 * = stb.substring(0, stb.length()-2);
								 */
							}
							collectedSerialsFromFile = Utilities.readSerialsFromFile(documentid
									+ "StockCollectedserials" + Utilities.currentDate("dd-MM-yyyy") + ".txt");
							if (collectedSerialsFromFile != null && collectedSerialsFromFile.length > 0) {
								Arrays.sort(collectedSerialsFromFile);
							}
							stb = null;
							collectedSerialsFromFileCommaSep = null;
							if (collectedSerialsFromFile != null && collectedSerialsFromFile.length > 0) {
								stb = new StringBuilder();
								for (String collectedSerialsFromFileElement : collectedSerialsFromFile) {
									stb.append(collectedSerialsFromFileElement).append(", ");
								}
								collectedSerialsFromFileCommaSep = stb.substring(0, stb.length() - 2);
							}
							for (int k = 1; k <= batchSerialProductRowExpanderRow; k++) {
								String expanderRowHeader = driver.findElement(By
										.xpath(".//*[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div["
												+ j + "]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/th[" + k + "]"))
										.getText();
								System.out.println(expanderRowHeader);
								if (expanderRowHeader.equals("Issued Serials")) {
									String issuedSerials = driver.findElement(By
											.xpath(".//*[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div["
													+ j + "]/table/tbody/tr[2]/td/div/table/tbody/tr[3]/td[" + k + "]"))
											.getText();
									if (issuedSerialsFromFileCommaSep != null) {
										System.out
												.println("issued Serials From File = " + issuedSerialsFromFileCommaSep);
										System.out.println("issued Serials From UI = " + issuedSerials);
										assertEquals(issuedSerialsFromFileCommaSep, issuedSerials);
									}
								} else if (expanderRowHeader.equals("Collected Serials")) {
									String issuedSerials = driver.findElement(By
											.xpath(".//*[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div["
													+ j + "]/table/tbody/tr[2]/td/div/table/tbody/tr[3]/td[" + k + "]"))
											.getText();
									if (collectedSerialsFromFileCommaSep != null) {
										System.out.println(
												"Collected Serials From File = " + collectedSerialsFromFileCommaSep);
										System.out.println("Collected Serials From UI = " + issuedSerials);
										assertEquals(collectedSerialsFromFileCommaSep, issuedSerials);
									}
								} else if (expanderRowHeader.equals("Issued Quantity")) {
									String issuedQty = driver.findElement(By
											.xpath(".//*[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div["
													+ j + "]/table/tbody/tr[2]/td/div/table/tbody/tr[3]/td[" + k + "]"))
											.getText();
									assertEquals(issuedQuanityValue, issuedQty);
								} else if (expanderRowHeader.equals("Collected Quantity")) {
									String issuedQty = driver.findElement(By
											.xpath(".//*[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div/div/div[2]/div["
													+ j + "]/table/tbody/tr[2]/td/div/table/tbody/tr[3]/td[" + k + "]"))
											.getText();
									assertEquals(collectedQuanityValue, issuedQty);
								}

							}
							break;
						}

					}
				} else if (header.equals("Ordered Quantity")) {
					for (int j = 1; j <= resultSetTotalCnt; j++) {
						assertEquals(quanityValue,
								driver.findElement(By
										.xpath(".//div[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div["
												+ j + "]/table/tbody/tr/td[" + i + "]/div"))
										.getText());
					}
				} else if (header.equals("Issued Quantity")) {
					for (int j = 1; j <= resultSetTotalCnt; j++) {
						assertEquals(issuedQuanityValue,
								driver.findElement(By
										.xpath(".//div[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div["
												+ j + "]/table/tbody/tr/td[" + i + "]/div"))
										.getText());
					}
				} else if (header.equals("Delivered Quantity")) {
					for (int j = 1; j <= resultSetTotalCnt; j++) {
						assertEquals(collectedQuanityValue,
								driver.findElement(By
										.xpath(".//div[@id='goodsEditorGridPanelgoodsapprovedgoodsIssueParentTab']/div/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div["
												+ j + "]/table/tbody/tr/td[" + i + "]/div"))
										.getText());
					}
				}

			}
			Thread.sleep(1000);
			System.out.println("Stock Issue verified successfully for document : [" + documentid + "]");

			Utilities.HoverandClick(pro.getProperty("StockIssueCloseBtn"), driver);
			Thread.sleep(2000);

		} catch (Exception EX) {
			System.out.println("Stock Issue NOT verified for document > > : [" + documentid + "] !!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ********************************************************StockIssue Store
	// Creation*********************************************************
	public static String[] fetch_StoreDescription(String storeCodes[], WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		String[] storeDesc = new String[storeCodes.length];
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		int cnt = 0;
		try {

			final Properties pro = Utilities.fetchProValue("OR_StockIssue.properties");
			InvUtilities.enableExpander(driver);
			Utilities.HoverandClick(pro.getProperty("MastersExpander"), driver);
			Utilities.HoverandClick(pro.getProperty("InventoryMasterLink"), driver);
			Utilities.HoverandClick(pro.getProperty("StoreMasterLink"), driver);
			Thread.sleep(2000);

			for (String storeCodeElement : storeCodes) {
				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						return webDriverArg.findElement(By.xpath(pro.getProperty("QuickSearchInput")));
					}
				});
				element.click();
				Thread.sleep(1000);
				element.clear();
				Thread.sleep(1000);
				element.sendKeys(storeCodeElement);
				Thread.sleep(2000);
				if (driver
						.findElement(
								By.xpath("//div[@id='invstoremastergrid']/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
					int tableHeadersCnt = driver
							.findElements(By
									.xpath(".//div[@id='invstoremastergrid']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td/div"))
							.size();
					Thread.sleep(1000);
					for (int i = 1; i < tableHeadersCnt + 1; i++) {
						String header = driver.findElement(By
								.xpath(".//div[@id='invstoremastergrid']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
										+ i + "]/div"))
								.getText();
						if (header.equals("Description")) {
							storeDesc[cnt] = driver.findElement(By
									.xpath(".//div[@id='invstoremastergrid']/div/div[2]/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
											+ i + "]/div"))
									.getText();
							cnt++;
						}
					}

				}
				Thread.sleep(2000);
			}
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StoreMasterCloseBtn")));
				}
			});
			element.click();

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
		return storeDesc;
	}

	// *********** Issue Stock Request ***********************
	public static void Issue_StoreOrders(String documentid, String issuedQuanity, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			final Properties pro = Utilities.fetchProValue("OR_StockIssue.properties");
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);

			Utilities.HoverandClick(pro.getProperty("StockIssueLink"), driver);
			Thread.sleep(30000);
			Utilities.HoverandClick(pro.getProperty("StoreOrdersTabLink"), driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("StoreOrdersQuickSearch"), driver);
			Thread.sleep(2000);
			WebElement moveFocus = driver.findElement(By.xpath(pro.getProperty("StoreOrdersQuickSearch")));

			try {
				String xpathOfresult = "//*[contains(@id,'transferreqgoodsIssueParentTab')]//*[text()='From Store']/ancestor::div[3]/following::div[1]/div[1]/div/div[1]/div";
				Utilities.HoverandClick(xpathOfresult, driver);
				System.out.println("******** Result found for document No: [" + documentid + "] **************");
				Thread.sleep(1000);
			} catch (Exception NoData) {
				System.out.println("!!!!!!!!!! No Result found for document No: [" + documentid + "] !!!!!!!!!!");
			}
			// -------- Enter Issues Qty --------
			int headerSize = Utilities.totalSize(
					"//*[contains(@id,'transferreqgoodsIssueParentTab')]//*[text()='From Store']/ancestor::tr/td/div",
					driver);
			int totalPrd = Utilities.totalSize(
					"//*[contains(@id,'transferreqgoodsIssueParentTab')]//*[text()='From Store']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div",
					driver);

			for (int k = 1; k <= totalPrd; k++) {
				for (int i = 1; i <= headerSize; i++) {
					String headerName = driver.findElement(By
							.xpath("//*[contains(@id,'transferreqgoodsIssueParentTab')]//*[text()='From Store']/ancestor::tr/td["
									+ i + "]/div"))
							.getText();
					if (headerName.equalsIgnoreCase("Issued Quantity")) {
						Utilities.HoverandClick(
								"//*[contains(@id,'transferreqgoodsIssueParentTab')]//*[text()='From Store']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div["
										+ k + "]/table/tbody/tr/td[" + i + "]/div",
								driver);
						Utilities.enter_LinelabelAmount(issuedQuanity, driver);
						moveFocus.click();
					}
				}
			}
			// ----- Enter Qauntity & serial
			for (int k = 1; k <= totalPrd; k++) {
				String xpathOflocation = null;
				xpathOflocation = "//*[contains(@id,'transferreqgoodsIssueParentTab')]//*[text()='From Store']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div["
						+ k + "]/table/tbody/tr/td[@style='width:38px;']//*[@class='pwnd serialNo-gridrow']";
				Utilities.HoverandClick(xpathOflocation, driver);
				Thread.sleep(1500);

				int headerTransferSize = 0;
				headerTransferSize = Utilities.totalSize("//*[text()='Issue Location ']/ancestor::tr/td/div", driver);
				for (int head = 1; head <= headerTransferSize; head++) {
					String headName = driver
							.findElement(By.xpath("//*[text()='Issue Location ']/ancestor::tr/td[" + head + "]/div"))
							.getText();
					if (headName.equalsIgnoreCase("Quantity*")) {
						Utilities.HoverandClick(
								"//*[text()='Issue Location ']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ head + "]/div",
								driver);
						Utilities.enter_LinelabelAmount(issuedQuanity, driver);
						Utilities.enter_LinelabelAmount_thenEnter(driver);
					}

					if (headName.equalsIgnoreCase("Serials*")) {
						Utilities.HoverandClick(
								"//*[text()='Issue Location ']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ head + "]/div",
								driver);
						Thread.sleep(1000);
						// transfer serials
						for (int serial = 1; serial <= Integer.parseInt(issuedQuanity); serial++) {
							Utilities
									.HoverandClick(
											"//*[@id='serialSelectWindow']//*[@class='x-column-inner']/div[1]//*[text()='Name']/ancestor::div[3]/following::div[1]/div/div["
													+ serial + "]/table/tbody/tr//*[@class='x-grid3-row-checker']",
											driver);
						}
						// transfer
						Utilities.HoverandClick("//*[@id='serialSelectWindow']//*[contains(@src,'images/arrowright')]",
								driver);
						Utilities.HoverandClick("//*[@id='serialSelectWindow']//*[text()='Submit']", driver);
					}
				}
				Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Save']", driver); // save
																										// button
				Thread.sleep(1000);
			}

			String selectAllxpath = "//*[contains(@id,'transferreqgoodsIssueParentTab')]//*[text()='From Store']/ancestor::div[3]/following::div[1]/div[1]/div/div[1]/div/input";
			Utilities.HoverandClick(selectAllxpath, driver);
			Utilities.HoverandClick(pro.getProperty("StoreOrdersIssueBtn"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1500, driver);
			Utilities.HoverandClick(pro.getProperty("StockIssueCloseBtn"), driver);
			System.out.println("***** Successfully issues quantity : [" + issuedQuanity + "] for Stock Request ["
					+ documentid + "] *****");
		} catch (Exception Ex) {
			System.out.println("!!!!!!!!!!!!!!!! FAILED to Issue quantity : [" + issuedQuanity + "] for Stock Request ["
					+ documentid + "] !!!!!!!!!!!!!!!! ");
			Utilities.handleError(Ex, driver);
		}
	}

	// **************** Reject *******************************

	public static void Reject_StoreOrders(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_StockIssue.properties");
			Utilities.refresh();

			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			WebElement element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("InventoryExpander")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("EntryExpander")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockIssueLink")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StoreOrdersTabLink")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StoreOrdersQuickSearch")));
				}
			});
			element.sendKeys(documentid);
			Thread.sleep(1000);
			element.sendKeys(Keys.ENTER);
			Thread.sleep(3000);

			if (driver.findElement(By.xpath(pro.getProperty("StoreOrdersQuickSearchResultCount"))).getText()
					.equalsIgnoreCase("No results to display")) {
				System.out.println("Stock Request " + documentid + "  doesn't Exist");
			} else {
				System.out.println("Stock Request " + documentid + "  found Successfully");
				element = wait.until(new Function<WebDriver, WebElement>() {

					// apply method- which accept webdriver as input
					// @Override
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg.findElement(By.xpath(pro.getProperty("StoreOrdersQuickSearchResultText")));
					}
				});

				String text = element.getText();
				System.out.println(text);
				if (text.trim().equalsIgnoreCase("Order Note No.: " + documentid)) {
					System.out.println("Stock Request " + documentid + "  listed Successfully");
					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(By.xpath(pro.getProperty("StoreOrdersSelectAllChkBox")));
						}
					});
					element.click();
					Thread.sleep(1000);
					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(By.xpath(pro.getProperty("StoreOrdersRejectBtn")));
						}
					});
					element.click();
					Thread.sleep(3000);

					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(By.xpath(pro.getProperty("StoreOrdersRemarkText")));
						}
					});
					element.click();
					Utilities.sendText("Rejecting Test Order");

					//
					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(By.xpath(pro.getProperty("StoreOrdersRemarkSubmitBtn")));
						}
					});
					element.click();

					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg
									.findElement(By.xpath(pro.getProperty("StoreOrdersRejectConfirmationBtn")));
						}
					});
					element.click();
					Thread.sleep(5000);
					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg
									.findElement(By.xpath(pro.getProperty("StoreOrdersRejectedSuccessOKBtn")));
						}
					});
					element.click();
					Thread.sleep(2000);
					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(By.xpath(pro.getProperty("StockIssueCloseBtn")));
						}
					});
					element.click();
				}
			}

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}
	}

}
