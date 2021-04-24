package krawler.erp.inventory;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import krawler.erp.page.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class InterLocationStockTransfer {

	// ********************************************************InterLocationStockTransfer
	// Location
	// Creation*********************************************************
	public static void create_LocationMasterItem(String locationName, String[] storeCodes, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		boolean isLocationExists = false;
		try {

			final Properties pro = Utilities.fetchProValue("OR_InterLocationStockTransfer.properties");
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			WebElement element = null;

			InvUtilities.enableExpander(driver);
			Utilities.HoverandClick(pro.getProperty("MastersExpander"), driver);
			Utilities.HoverandClick(pro.getProperty("InventoryMasterLink"), driver);
			Utilities.HoverandClick(pro.getProperty("LocationMasterLink"), driver);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					return webDriverArg.findElement(By.xpath(pro.getProperty("QuickSearchInput")));
				}
			});
			element.click();
			element.sendKeys(locationName);
			Thread.sleep(3000);
			if (driver
					.findElement(By
							.xpath("//div[@id='LocationMasterGrid']/div/div/div/div/div[3]/div/table/following-sibling::div"))
					.getText().equalsIgnoreCase("No data to display")) {
				System.out.println("Location Name :" + locationName + " :   doesn't exist");
			} else {
				System.out.println("Location Name :" + locationName + " : exist");
				isLocationExists = true;
			}
			if (!isLocationExists) {

				Utilities.HoverandClick(pro.getProperty("NewLocationAddBtn"), driver);
				Utilities.enterTextNormalBox(locationName, pro.getProperty("NewLocationNameInput"), driver);

				for (String storeCode : storeCodes) {
					element = wait.until(new Function<WebDriver, WebElement>() {
						public WebElement apply(WebDriver webDriverArg) {
							return webDriverArg.findElement(By.xpath(pro.getProperty("NewLocationTypeInput")));
						}
					});
					element.click();
					element.clear();
					element.sendKeys(storeCode);
					Thread.sleep(2000);
					Robot r3 = new Robot();
					r3.keyPress(KeyEvent.VK_DOWN);
					r3.keyRelease(KeyEvent.VK_DOWN);
					Thread.sleep(2000);
					r3.keyPress(KeyEvent.VK_CONTROL);
					r3.keyPress(KeyEvent.VK_A);
					r3.keyRelease(KeyEvent.VK_A);
					r3.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(1000);
					r3.keyPress(KeyEvent.VK_DOWN);
					r3.keyRelease(KeyEvent.VK_DOWN);
					Thread.sleep(1000);
					r3.keyPress(KeyEvent.VK_ENTER);
					r3.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(1000);
					r3.keyPress(KeyEvent.VK_TAB);
					r3.keyRelease(KeyEvent.VK_TAB);
					Thread.sleep(2000);

				}

				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						return webDriverArg.findElement(By.xpath(pro.getProperty("NewLocationSaveBtn")));
					}
				});
				element.click();
				Thread.sleep(2000);
				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_SPACE);
				r.keyRelease(KeyEvent.VK_SPACE);

				Thread.sleep(2000);
				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						return webDriverArg.findElement(By.xpath(pro.getProperty("QuickSearchInput")));
					}
				});
				element.clear();
				element.sendKeys(locationName);
				Thread.sleep(2000);
				if (driver
						.findElement(By
								.xpath("//div[@id='LocationMasterGrid']/div/div/div/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
					System.out.println("Location Name :" + locationName + " created successfully");
				} else {
					System.out.println("Location Name :" + locationName + " creation failed");
				}

			} else {
				System.out.println("Location Name :" + locationName + " exist so won't creating new one");
			}

			Thread.sleep(1500);
			Utilities.HoverandClick(pro.getProperty("LocationMasterCloseBtn"), driver);

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ********************************************************InterLocationStockTransfer
	// Creation*********************************************************
	public static void create_InterLocationStockTransfer(String documentid, String productid, String fromStore,
			String toLocation, String quantity, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_InterLocationStockTransfer.properties");
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);
			documentid = "ILST" + documentid;
			Utilities.HoverandClick(pro.getProperty("InterLocationStockTransferLink"), driver);
			Utilities.disableExpander(driver);

			InvUtilities.selectFromNormalDropDown(fromStore, pro.getProperty("InterLocationStockTransferFromStore"),
					driver);
			InvUtilities.selectFromNormalDropDown("NA", pro.getProperty("InterLocationStockTransferSequenceFormat"),
					driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("InterLocationStockTransferDocumentId"), driver);
			Utilities.enterTextNormalBox(" Inter Location Stock Transfer Memo for " + productid,
					pro.getProperty("InterLocationStockTransferMemo"), driver);

			Utilities.HoverandClick(pro.getProperty("InterLocationStockTransferAddPrdButton"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("InterLocationStockTransferSearch"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("InterLocationStockTransferAddButton"), driver);
			Thread.sleep(2000);

			int totalProducts = Utilities
					.totalSize("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div", driver);
			int headerSize = Utilities.totalSize("//*[text()='Product ID']/ancestor::tr/td/div", driver);

			for (int i = 1; i < totalProducts; i++) {
				for (int j = 1; j <= headerSize; j++) {
					String headerName = driver
							.findElement(By.xpath("//*[text()='Product ID']/ancestor::tr/td[" + j + "]/div")).getText();
					if (headerName.startsWith("Quantity")) {
						Utilities
								.HoverandClick("//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div["
										+ i + "]/table/tbody/tr/td[" + j + "]/div", driver);
						Utilities.enter_LinelabelAmount(quantity, driver);
					}
				}
			}

			// ----- Enter Qauntity & serial
			for (int k = 1; k < totalProducts; k++) {
				String xpathOflocation = null;
				xpathOflocation = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[" + k
						+ "]/table//*[@class='pwnd serialNo-gridrow']";
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
						Utilities.enter_LinelabelAmount(quantity, driver);
						Utilities.enter_LinelabelAmount_thenEnter(driver);
					}

					if (headName.equalsIgnoreCase("Serials*")) {
						Utilities.HoverandClick(
								"//*[text()='Issue Location ']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
										+ head + "]/div",
								driver);
						Thread.sleep(1000);
						// transfer serials
						for (int serial = 1; serial <= Integer.parseInt(quantity); serial++) {
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
						Utilities.enterTextandSelect(toLocation, "//*[contains(@style,'visible')]/div/input", driver);
					}
				}
				Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Save']", driver); // save
																										// button
			}

			Utilities.HoverandClick(pro.getProperty("saveInterLocationStockTransferBtn"), driver);
			Utilities.clickButton("OK", 1500, driver);
			System.out.println("********** Inter Location Stock Transfer created successfully for [" + documentid
					+ "] !! *********************");
			Utilities.HoverandClick(pro.getProperty("InterLocationStockTransferCloseBtn"), driver);

		} catch (Exception EX) {
			System.out.println("!!!!!!!!!!!! FAILED to Create Inter Location Stock Transfer for [" + documentid
					+ "] !!!!!!!!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ********************************************************InterLocationStockTransfer
	// Deletion*********************************************************
	public static void delete_InterLocationStockTransfer(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_InterLocationStockTransfer.properties");
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
					return webDriverArg.findElement(By.xpath(pro.getProperty("ReportsExpander")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("InterLocationTransferDetailsLink")));
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
							.findElement(By.xpath(pro.getProperty("InterLocationTransferDetailsQuickSearch")));
				}
			});
			element.sendKeys(documentid);
			Thread.sleep(1000);
			element.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			if (driver
					.findElement(By
							.xpath("//div[@id='interLocationTransferReportTab']/div/div/div/div/div[3]/div/table/following-sibling::div"))
					.getText().equalsIgnoreCase("No data to display")) {
				System.out.println("Inter Location Stock Transfer Request " + documentid + "  doesn't Exist");
			} else {
				System.out.println("Inter Location Stock Transfer Request " + documentid + "  found Successfully");
				element = wait.until(new Function<WebDriver, WebElement>() {

					// apply method- which accept webdriver as input
					// @Override
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg.findElement(
								By.xpath(pro.getProperty("InterLocationTransferDetailsQuickSearchResultText")));
					}
				});

				String text = element.getText();
				// System.out.println(text);
				if (text.trim().equalsIgnoreCase("Transfer Note No.: " + documentid)) {
					System.out.println("Inter Location Stock Transfer Request " + documentid + "  listed Successfully");
					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(
									By.xpath(pro.getProperty("InterLocationTransferDetailsSelectAllChkBox")));
						}
					});
					element.click();
					Thread.sleep(1000);
					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg
									.findElement(By.xpath(pro.getProperty("InterLocationTransferdeleteBtn")));
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
									.findElement(By.xpath(pro.getProperty("InterLocationTransferdeletedSuccessOKBtn")));
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
									.findElement(By.xpath(pro.getProperty("InterLocationTransferDetailsQuickSearch")));
						}
					});
					element.clear();
					Thread.sleep(3000);
					element.sendKeys(documentid);
					Thread.sleep(3000);
					if (driver
							.findElement(By
									.xpath("//div[@id='interLocationTransferReportTab']/div/div/div/div/div[3]/div/table/following-sibling::div"))
							.getText().equalsIgnoreCase("No data to display")) {
						System.out.println(
								"Inter Location Stock Transfer Request " + documentid + "  deleted successfully");
					}

				}
			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("InterLocationTransferDetailsCloseBtn")));
				}
			});
			element.click();

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}
	}
}
