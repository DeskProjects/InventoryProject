package krawler.erp.inventory;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import krawler.erp.page.Utilities;
import krawler.erp.utils.HandlePrintWindow;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.google.common.base.Function;

public class StockRequest {

	// ---------------- Create_StockRequest --------------------------
	public static void create_StockRequest(String documentid, String productid, String forStore, String quanityValue,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_StockRequest.properties");
			documentid = "StockRequest" + documentid;
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);
			Utilities.HoverandClick(pro.getProperty("StockRequestLink"), driver);
			Thread.sleep(1000);
			Utilities.disableExpander(driver);

			Utilities.enterTextandSelect(forStore, pro.getProperty("StockRequestFromStore"), driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("StockRequestSequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("StockRequestDocumentId"), driver);

			Utilities.HoverandClick(pro.getProperty("StockRequestAddPrdButton"), driver);
			Utilities.isLoadingDisappear(45, driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("StockRequestSearch"), driver);
			Utilities.isLoadingDisappear(45, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("StockRequestAddButton"), driver);
			Utilities.isLoadingDisappear(45, driver);

			int totalPrd = Utilities.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div",
					driver);
			for (int i = 1; i < totalPrd; i++) {
				int headerSize = Utilities.totalSize("//*[text()='Product ID']/ancestor::tr/td/div", driver);
				for (int j = 1; j <= headerSize; j++) {
					String headerName = driver
							.findElement(By.xpath("//*[text()='Product ID']/ancestor::tr/td[" + j + "]/div")).getText();
					if (headerName.equalsIgnoreCase("Requested Quantity")) {
						Utilities
								.HoverandClick("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div["
										+ i + "]/table/tbody/tr/td[" + j + "]/div", driver);
						Utilities.enter_LinelabelAmount(quanityValue, driver);
					}
				}
			}

			String ParentWindow = driver.getWindowHandle(); // beforesave button
			System.out.println(ParentWindow);
			Utilities.clickButton("Save", 1000, driver);

			HandlePrintWindow.closePrintWindow(ParentWindow, driver);
			Utilities.clickButton("OK", 1000, driver);
			System.out.println("******** Stock Request [" + documentid + "] created successfully ");
			Utilities.HoverandClick(pro.getProperty("StockRequestCloseBtn"), driver);

		} catch (Exception Ex) {
			System.out.println("!!!!!!!!!!!!! Stock Request [" + documentid + "] FAILED to CREATE !!!!!!!!!!!!!");
			Utilities.handleError(Ex, driver);
		}
	}

	// ********************************************************StockIssue
	// Deletion*********************************************************
	public static void delete_StockRequest(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_StockRequest.properties");
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
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockRequestLink")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockRequestGoodsPendingOrdersTabLink")));
				}
			});
			element.click();
			Thread.sleep(2000);
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg
							.findElement(By.xpath(pro.getProperty("StockRequestGoodsPendingOrdersQuickSearch")));
				}
			});
			element.sendKeys(documentid);
			Thread.sleep(1000);
			element.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			if (driver
					.findElement(By
							.xpath("//div[@id='goodsEditorGridPanelgoodsPendingOrdersgoodsOrderParentTabb']/div/div[3]/div/table/following-sibling::div"))
					.getText().equalsIgnoreCase("No results to display")) {
				System.out.println("Stock Request " + documentid + "  doesn't Exist");
			} else {
				System.out.println("Stock Request " + documentid + "  found Successfully");
				element = wait.until(new Function<WebDriver, WebElement>() {

					// apply method- which accept webdriver as input
					// @Override
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg.findElement(
								By.xpath(pro.getProperty("StockRequestGoodsPendingOrdersQuickSearchResultText")));
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
							return webDriverArg.findElement(
									By.xpath(pro.getProperty("StockRequestGoodsPendingOrdersSelectAllChkBox")));
						}
					});
					element.click();
					Thread.sleep(1000);
					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(By.xpath(pro.getProperty("StockRequestdeleteBtn")));
						}
					});
					element.click();
					Thread.sleep(2000);
					Robot r = new Robot();
					r.keyPress(KeyEvent.VK_SPACE);
					r.keyRelease(KeyEvent.VK_SPACE);

					Thread.sleep(5000);
					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg
									.findElement(By.xpath(pro.getProperty("StockRequestdeletedSuccessOKBtn")));
						}
					});
					element.click();
					Thread.sleep(2000);
					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(
									By.xpath(pro.getProperty("StockRequestGoodsPendingOrdersQuickSearch")));
						}
					});
					element.clear();
					Thread.sleep(3000);
					element.sendKeys(documentid);
					Thread.sleep(3000);
					if (driver
							.findElement(By
									.xpath("//div[@id='goodsEditorGridPanelgoodsPendingOrdersgoodsOrderParentTabb']/div/div[3]/div/table/following-sibling::div"))
							.getText().equalsIgnoreCase("No data to display")) {
						System.out.println("Stock Request " + documentid + "  deleted successfully");
					}

				}
			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockRequestCloseBtn")));
				}
			});
			element.click();

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ------------------------------- Stock Rquest Collect
	// -------------------------------
	public static void collect_StockRequest(String documentid, String collectedQuanity, String selectLocation,
			WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {
			final Properties pro = Utilities.fetchProValue("OR_StockRequest.properties");
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);

			Utilities.HoverandClick(pro.getProperty("StockRequestLink"), driver);
			Thread.sleep(1000);
			Utilities.HoverandClick(pro.getProperty("StockRequestGoodsPendingOrdersTabLink"), driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("StockRequestGoodsPendingOrdersQuickSearch"),
					driver);
			Thread.sleep(2000);
			WebElement moveFocus = driver
					.findElement(By.xpath(pro.getProperty("StockRequestGoodsPendingOrdersQuickSearch")));

			try {
				String xpathOfresult = "//*[contains(@id,'goodsPendingOrdersgoodsOrder')]//*[text()='To Store']/ancestor::div[3]/following::div[1]/div[1]/div/div[1]/div";
				Utilities.HoverandClick(xpathOfresult, driver);
				System.out.println("******** Result found for document No: [" + documentid + "] **************");
				Thread.sleep(1000);
			} catch (Exception NoData) {
				System.out.println("!!!!!!!!!! No Result found for document No: [" + documentid + "] !!!!!!!!!!");
			}

			// -------- Enter Delivered Qty --------
			int headerSize = Utilities.totalSize(
					"//*[contains(@id,'goodsPendingOrdersgoodsOrder')]//*[text()='From Store']/ancestor::tr/td/div",
					driver);
			int totalPrd = Utilities.totalSize(
					"//*[contains(@id,'goodsPendingOrdersgoodsOrder')]//*[text()='From Store']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div",
					driver);

			for (int k = 1; k <= totalPrd; k++) {
				for (int i = 1; i <= headerSize; i++) {
					String headerName = driver.findElement(By
							.xpath("//*[contains(@id,'goodsPendingOrdersgoodsOrder')]//*[text()='From Store']/ancestor::tr/td["
									+ i + "]/div"))
							.getText();
					if (headerName.equalsIgnoreCase("Delivered Quantity")) {
						Utilities.HoverandClick(
								"//*[contains(@id,'goodsPendingOrdersgoodsOrder')]//*[text()='From Store']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div["
										+ k + "]/table/tbody/tr/td[" + i + "]/div",
								driver);
						Utilities.enter_LinelabelAmount(collectedQuanity, driver);
						moveFocus.click();
					}
				}
			}

			// ----- Enter Qauntity & serial
			for (int k = 1; k <= totalPrd; k++) {
				String xpathOflocation = null;
				xpathOflocation = "//*[contains(@id,'goodsPendingOrdersgoodsOrder')]//*[text()='From Store']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div["
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
						Utilities.enter_LinelabelAmount(collectedQuanity, driver);
						Utilities.enter_LinelabelAmount_thenEnter(driver);
					}

					if (headName.equalsIgnoreCase("Serials*")) {
						Utilities.HoverandClick(
								"//*[text()='Issue Location ']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ head + "]/div",
								driver);
						Thread.sleep(1000);
						// transfer serials
						for (int serial = 1; serial <= Integer.parseInt(collectedQuanity); serial++) {
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
						Utilities.enterTextandSelect(selectLocation, "//*[contains(@style,'visible')]/div/input",
								driver);
					}

				}
				Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Save']", driver); // save
																										// button
				Thread.sleep(1000);
			}

			String selectAllxpath = "//*[contains(@id,'goodsPendingOrdersgoodsOrder')]//*[text()='From Store']/ancestor::div[3]/following::div[1]/div[1]/div/div[1]/div/input";
			Utilities.HoverandClick(selectAllxpath, driver);
			Utilities.HoverandClick(pro.getProperty("collectStockRequestBtn"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.isLoadingDisappear(60, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("StockRequestCloseBtn"), driver);
			System.out.println("***** Successfully Collected quantity : [" + collectedQuanity + "] for Stock Request ["
					+ documentid + "] *****");
		} catch (Exception Ex) {
			System.out.println("!!!!!!!!!!!!!!!! FAILED to Collected quantity : [" + collectedQuanity
					+ "] for Stock Request [" + documentid + "] !!!!!!!!!!!!!!!! ");
			Utilities.handleError(Ex, driver);
		}
	}

	// ********************************************************Stock Request
	// Fulfilled Orders*********************************************************
	public static void Fulfilled_Orders_StockRequest(String documentid, String productid, String orderedQty,
			String issuedQuanity, String deliveredQuanity, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_StockRequest.properties");
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			WebElement element = null;

			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);

			Utilities.HoverandClick(pro.getProperty("StockRequestLink"), driver);
			Thread.sleep(3000);
			Utilities.HoverandClick(pro.getProperty("StockRequestFulfilledOrdersTabLink"), driver);

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
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("totalResult")));
				}
			});

			String resultText = element.getText();
			String[] resultTextArray = resultText.split("of");
			String resultSetTotalCntString = "";
			for (String resultTextElement : resultTextArray) {
				resultSetTotalCntString = resultTextElement;
			}
			int resultSetTotalCnt = Integer.parseInt(resultSetTotalCntString.trim());

			int productIDRowIndex = 0, orderedQtyRowIndex = 0, issuedQuanityRowIndex = 0, delvQuanityRowIndex = 0;
			int tableHeadersCnt = driver
					.findElements(By
							.xpath("//div[@id='goodsEditorGridPanelgoodsapprovedgoodsOrderParentTabb']/div/div[2]/div/div/div/div/div/table/thead/tr/td"))
					.size();
			Thread.sleep(1000);

			for (int rowIndex = 1; rowIndex <= tableHeadersCnt; rowIndex++) {
				String header = driver.findElement(By
						.xpath("//div[@id='goodsEditorGridPanelgoodsapprovedgoodsOrderParentTabb']/div/div[2]/div/div/div/div/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText();
				if (header.equals("Product ID")) {
					productIDRowIndex = rowIndex;

				} else if (header.equals("Ordered Quantity")) {
					orderedQtyRowIndex = rowIndex;

				} else if (header.equals("Issued Quantity")) {
					issuedQuanityRowIndex = rowIndex;

				} else if (header.equals("Delivered Quantity")) {
					delvQuanityRowIndex = rowIndex;

				}
			}

			for (int j = 1; j <= resultSetTotalCnt; j++) {
				int index = 0;
				String productIDStr = driver.findElement(By
						.xpath("//div[@id='goodsEditorGridPanelgoodsapprovedgoodsOrderParentTabb']/div/div[2]/div/div/div[2]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + productIDRowIndex + "]/div"))
						.getText();
				System.out.println("productIDStr =" + productIDStr);
				if (productIDStr.endsWith("S3")) {
					index = 4;
				} else if (productIDStr.endsWith("B2")) {
					index = 2;
				} else if (productIDStr.endsWith("BS4")) {
					index = 3;
				} else if (productIDStr.equalsIgnoreCase(productid)) {
					index = 1;
				}
				// orderedQty,String issuedQuanity,String deliveredQuanity

				switch (index) {
				case 1:
					assertEquals(orderedQty, driver
							.findElement(By
									.xpath("//div[@id='goodsEditorGridPanelgoodsapprovedgoodsOrderParentTabb']/div/div[2]/div/div/div[2]/div/div/div[2]/div["
											+ index + "]/table/tbody/tr/td[" + orderedQtyRowIndex + "]/div"))
							.getText());
					System.out.println("Verified Ordered Quantity For Normal Product = " + productIDStr
							+ " : Ordered Quantity [" + orderedQty + "] matched with UI");
					assertEquals(issuedQuanity, driver
							.findElement(By
									.xpath("//div[@id='goodsEditorGridPanelgoodsapprovedgoodsOrderParentTabb']/div/div[2]/div/div/div[2]/div/div/div[2]/div["
											+ index + "]/table/tbody/tr/td[" + issuedQuanityRowIndex + "]/div"))
							.getText());
					System.out.println("Verified issued Quanity For Normal Product = " + productIDStr
							+ " : Issued Quantity [" + issuedQuanity + "] matched with UI");
					assertEquals(deliveredQuanity, driver
							.findElement(By
									.xpath("//div[@id='goodsEditorGridPanelgoodsapprovedgoodsOrderParentTabb']/div/div[2]/div/div/div[2]/div/div/div[2]/div["
											+ index + "]/table/tbody/tr/td[" + delvQuanityRowIndex + "]/div"))
							.getText());
					System.out.println("Verified delivered Quanity For Normal Product = " + productIDStr
							+ " : Delivered Quantity [" + deliveredQuanity + "] matched with UI");
					break;

				case 2:
					assertEquals(orderedQty, driver
							.findElement(By
									.xpath("//div[@id='goodsEditorGridPanelgoodsapprovedgoodsOrderParentTabb']/div/div[2]/div/div/div[2]/div/div/div[2]/div["
											+ index + "]/table/tbody/tr/td[" + orderedQtyRowIndex + "]/div"))
							.getText());
					System.out.println("Verified Ordered Quantity For Batch Product = " + productIDStr
							+ " : Ordered Quantity [" + orderedQty + "] matched with UI");
					assertEquals(issuedQuanity, driver
							.findElement(By
									.xpath("//div[@id='goodsEditorGridPanelgoodsapprovedgoodsOrderParentTabb']/div/div[2]/div/div/div[2]/div/div/div[2]/div["
											+ index + "]/table/tbody/tr/td[" + issuedQuanityRowIndex + "]/div"))
							.getText());
					System.out.println("Verified issued Quanity For Batch Product = " + productIDStr
							+ " : Issued Quantity [" + issuedQuanity + "] matched with UI");
					assertEquals(deliveredQuanity, driver
							.findElement(By
									.xpath("//div[@id='goodsEditorGridPanelgoodsapprovedgoodsOrderParentTabb']/div/div[2]/div/div/div[2]/div/div/div[2]/div["
											+ index + "]/table/tbody/tr/td[" + delvQuanityRowIndex + "]/div"))
							.getText());
					System.out.println("Verified delivered Quanity For Batch Product = " + productIDStr
							+ " : Delivered Quantity [" + deliveredQuanity + "] matched with UI");
					break;

				case 3:
					assertEquals(orderedQty, driver
							.findElement(By
									.xpath("//div[@id='goodsEditorGridPanelgoodsapprovedgoodsOrderParentTabb']/div/div[2]/div/div/div[2]/div/div/div[2]/div["
											+ index + "]/table/tbody/tr/td[" + orderedQtyRowIndex + "]/div"))
							.getText());
					System.out.println("Verified Ordered Quantity For Batch with Serial Product = " + productIDStr
							+ " : Ordered Quantity [" + orderedQty + "] matched with UI");
					assertEquals(issuedQuanity, driver
							.findElement(By
									.xpath("//div[@id='goodsEditorGridPanelgoodsapprovedgoodsOrderParentTabb']/div/div[2]/div/div/div[2]/div/div/div[2]/div["
											+ index + "]/table/tbody/tr/td[" + issuedQuanityRowIndex + "]/div"))
							.getText());
					System.out.println("Verified issued Quanity For Batch with Serial Product = " + productIDStr
							+ " : Issued Quantity [" + issuedQuanity + "] matched with UI");
					assertEquals(deliveredQuanity, driver
							.findElement(By
									.xpath("//div[@id='goodsEditorGridPanelgoodsapprovedgoodsOrderParentTabb']/div/div[2]/div/div/div[2]/div/div/div[2]/div["
											+ index + "]/table/tbody/tr/td[" + delvQuanityRowIndex + "]/div"))
							.getText());
					System.out.println("Verified delivered Quanity For Batch with Serial Product = " + productIDStr
							+ " : Delivered Quantity [" + deliveredQuanity + "] matched with UI");
					break;

				case 4:
					assertEquals(orderedQty, driver
							.findElement(By
									.xpath("//div[@id='goodsEditorGridPanelgoodsapprovedgoodsOrderParentTabb']/div/div[2]/div/div/div[2]/div/div/div[2]/div["
											+ index + "]/table/tbody/tr/td[" + orderedQtyRowIndex + "]/div"))
							.getText());
					System.out.println("Verified Ordered Quantity For Serial Product = " + productIDStr
							+ " : Ordered Quantity [" + orderedQty + "] matched with UI");
					assertEquals(issuedQuanity, driver
							.findElement(By
									.xpath("//div[@id='goodsEditorGridPanelgoodsapprovedgoodsOrderParentTabb']/div/div[2]/div/div/div[2]/div/div/div[2]/div["
											+ index + "]/table/tbody/tr/td[" + issuedQuanityRowIndex + "]/div"))
							.getText());
					System.out.println("Verified issued Quanity For Serial Product = " + productIDStr
							+ " : Issued Quantity [" + issuedQuanity + "] matched with UI");
					assertEquals(deliveredQuanity, driver
							.findElement(By
									.xpath("//div[@id='goodsEditorGridPanelgoodsapprovedgoodsOrderParentTabb']/div/div[2]/div/div/div[2]/div/div/div[2]/div["
											+ index + "]/table/tbody/tr/td[" + delvQuanityRowIndex + "]/div"))
							.getText());
					System.out.println("Verified delivered Quanity For Serial Product = " + productIDStr
							+ " : Delivered Quantity [" + deliveredQuanity + "] matched with UI");
					break;

				}
			}

			Thread.sleep(1000);
			System.out.println("Stock Request successfully VERIFIED for [" + documentid + "] !");

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockRequestCloseBtn")));
				}
			});
			element.click();
			Thread.sleep(2000);

		} catch (Exception EX) {
			System.out.println("Stock Request Document NOT VERIFIED for [" + documentid + "] !!!!!!!!!!!");
			Utilities.handleError(EX, driver);
		}

	}
}
