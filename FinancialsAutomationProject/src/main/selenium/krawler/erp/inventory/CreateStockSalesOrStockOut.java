package krawler.erp.inventory;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

import krawler.erp.page.Utilities;

public class CreateStockSalesOrStockOut {
	public static void writeSerialsToFile(String fileName, String[] serials) throws Exception {
		FileWriter fw = null;
		try {
			String dir = System.getProperty("user.dir");
			String directorypath = dir + "/test-output/" + fileName;
			File serialFile = new File(directorypath);
			// check if file exist, otherwise create the file before writing
			if (!serialFile.exists()) {
				serialFile.createNewFile();
			}
			fw = new FileWriter(directorypath);
			for (int i = 0; i < serials.length; i++) {
				fw.write(serials[i] + "\n");
			}

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			fw.close();
		}
	}

	public static void create_StockAdjustmentforStockOutorStocksale(String documentid, String productid,
			String adjustmentTypeValue, String batchValue, String serialSeq, String quanityValue, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			final Properties pro = Utilities.fetchProValue("OR_StockAdjustment.properties");

			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			WebElement element = null;

			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockAdjustmentLink")));
				}
			});
			element.click();
			Thread.sleep(1000);
			Utilities.clickExpander(driver);

			Utilities.enterTextandSelect("DS - Default Store", "//input[@id='fromstoremarkoutallmarkoutTab']", driver);
			Utilities.enterTextandSelect("NA", pro.getProperty("StockAdjustmentSequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("StockAdjustmentDocumentId"), driver);
			String memoText = adjustmentTypeValue + " Against " + productid;
			WebElement memo = driver.findElement(By.xpath(pro.getProperty("StockAdjustmentMemo")));
			memo.sendKeys(memoText);
			// Select 1st reason
			Utilities.HoverandClick("//*[@id='reason']/following::img[1]", driver);
			Utilities.HoverandClick("//*[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div[2]",
					driver);

			Utilities.HoverandClick(pro.getProperty("StockAdjustmentAddPrdButton"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("StockAdjustmentSearch"), driver);
			Utilities.isLoadingDisappear(60, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);

			int resultSetTotalCnt = 4;

			Utilities.HoverandClick(pro.getProperty("StockAdjustmentAddButton"), driver);
			Utilities.isLoadingDisappear(45, driver);

			int adjTypeRowIndex = 0, qtyRowIndex = 0, wslocationlinkIndex = 18;
			// beginingIndex=0;
			WebElement adjustmentType = null, quantity = null, location = null, wslocationlink = null, batch = null,
					serial = null, quantity1 = null, outQty = null, outSerial = null;
			for (adjTypeRowIndex = 1; adjTypeRowIndex <= driver
					.findElements(By
							.xpath("//div[@id='markoutallmarkoutTab']/div/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size(); adjTypeRowIndex++) {
				if (driver.findElement(By
						.xpath("//div[@id='markoutallmarkoutTab']/div/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ adjTypeRowIndex + "]/div"))
						.getText().equalsIgnoreCase("Adjustment Type")) {
					break;
				}
			}
			for (qtyRowIndex = 1; qtyRowIndex <= driver
					.findElements(By
							.xpath("//div[@id='markoutallmarkoutTab']/div/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size(); qtyRowIndex++) {
				if (driver.findElement(By
						.xpath("//div[@id='markoutallmarkoutTab']/div/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ qtyRowIndex + "]/div"))
						.getText().equalsIgnoreCase("Quantity")) {
					break;
				}
			}
			for (int j = 1; j <= resultSetTotalCnt; j++) {
				adjustmentType = driver.findElement(By
						.xpath("//div[@id='markoutallmarkoutTab']/div/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[" + adjTypeRowIndex + "]/div"));
				adjustmentType.click();
				Thread.sleep(2000);
				Robot r1 = new Robot();
				r1.keyPress(KeyEvent.VK_DOWN);
				r1.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(1000);
				for (int i1 = 1; i1 <= driver.findElements(By.xpath(".//*[text()='Stock Sales']/parent::div/div"))
						.size(); i1++) {
					if (driver.findElement(By.xpath(".//*[text()='Stock Sales']/parent::div[1]/div[" + i1 + "]"))
							.getText().equalsIgnoreCase(adjustmentTypeValue)) {
						location = driver
								.findElement(By.xpath(".//*[text()='Stock Sales']/parent::div[1]/div[" + i1 + "]"));
						location.click();
					}
				}
				Thread.sleep(1000);
			}
			for (int j = 1; j <= resultSetTotalCnt; j++) {
				quantity = driver.findElement(By
						.xpath("//div[@id='markoutallmarkoutTab']/div/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[1]/div["
								+ j + "]/table/tbody/tr/td[" + qtyRowIndex + "]/div"));
				quantity.click();
				Thread.sleep(1000);
				Robot r2 = new Robot();
				r2.keyPress(KeyEvent.VK_CONTROL);
				r2.keyPress(KeyEvent.VK_A);
				r2.keyRelease(KeyEvent.VK_A);
				r2.keyRelease(KeyEvent.VK_CONTROL);
				r2.keyPress(KeyEvent.VK_DELETE);
				r2.keyRelease(KeyEvent.VK_DELETE);
				Utilities.sendText(quanityValue);
				Thread.sleep(1000);
				r2.keyPress(KeyEvent.VK_TAB);
				r2.keyRelease(KeyEvent.VK_TAB);
			}

			// System.out.println("resultSetTotalCnt" + resultSetTotalCnt);
			for (int j = 1; j <= resultSetTotalCnt; j++) {
				Utilities.HoverandClick("//*[text()='Adjustment Type']/ancestor::div[3]/following::div[1]/div[1]/div["
						+ j + "]/table/tbody/tr/td//div[@class='pwnd serialNo-gridrow']", driver);
				Thread.sleep(2000);

				if (adjustmentTypeValue.equalsIgnoreCase("Stock Out")
						|| adjustmentTypeValue.equalsIgnoreCase("Stock Sales")) {
					// String[] selectOutSerialNumbers = new
					// String[Integer.parseInt(quanityValue)];
					// String[] selectOutSerialNumbersFromFile=null;
					for (int i = 1; i <= driver
							.findElements(By
									.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td"))
							.size(); i++) {
						if (driver.findElement(By
								.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
										+ i + "]/div"))
								.getText().equalsIgnoreCase("Quantity*")) {
							outQty = driver.findElement(By
									.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr/td["
											+ i + "]/div"));
						}
					}
					outQty.click();
					Thread.sleep(2000);
					Robot outQtyRobot = new Robot();
					Utilities.sendText(quanityValue);
					Thread.sleep(2000);
					outQtyRobot.keyPress(KeyEvent.VK_ENTER);
					outQtyRobot.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(2000);

					for (int i = 1; i <= driver
							.findElements(By
									.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td"))
							.size(); i++) {
						if (driver.findElement(By
								.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
										+ i + "]/div"))
								.getText().equalsIgnoreCase("Serials*")) {
							outSerial = driver.findElement(By
									.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr/td["
											+ i + "]/div"));
							outSerial.click();
							Thread.sleep(2000);
							String[] selectStockAdjustmentSerialNumbers = new String[Integer.parseInt(quanityValue)];
							for (int selectOutCount = 1; selectOutCount <= Integer
									.parseInt(quanityValue); selectOutCount++) {
								serial = driver.findElement(By
										.xpath(".//*[@id='Northform']/div/div/form/div/div/div/div/div[1]/div/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div["
												+ selectOutCount + "]/table/tbody/tr/td[1]/div/div"));
								serial.click();
								String selectedSerialNo = driver.findElement(By
										.xpath(".//*[@id='Northform']/div/div/form/div/div/div/div/div[1]/div/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div["
												+ selectOutCount + "]/table/tbody/tr/td[3]/div"))
										.getText();
								selectStockAdjustmentSerialNumbers[selectOutCount - 1] = selectedSerialNo;
							}

							element = wait.until(new Function<WebDriver, WebElement>() {
								// apply method- which accept webdriver as input
								// @Override
								public WebElement apply(WebDriver webDriverArg) {
									// find the element
									return webDriverArg
											.findElement(By.xpath(pro.getProperty("StockAdjustmentRightArrowImg")));
								}
							});
							element.click();

							element = wait.until(new Function<WebDriver, WebElement>() {
								// apply method- which accept webdriver as input
								// @Override
								public WebElement apply(WebDriver webDriverArg) {
									// find the element
									return webDriverArg.findElement(
											By.xpath(pro.getProperty("StockAdjustmentSelectAllSerialsSubmit")));
								}
							});
							element.click();
							writeSerialsToFile(
									adjustmentTypeValue + "serials" + Utilities.currentDate("dd-MM-yyyy") + ".txt",
									selectStockAdjustmentSerialNumbers);
						}

					}
					element = wait.until(new Function<WebDriver, WebElement>() {
						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(By.xpath(pro.getProperty("saveStokcOutQtyLocationButton")));
						}
					});
					element.click();
					Thread.sleep(5000);

				}
			}

			String mainWindow = driver.getWindowHandle();
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("saveStockAdjustmentBtn")));
				}
			});

			element.click();
			Thread.sleep(1000);

			Utilities.clickButton("Yes", 500, driver);

			WebElement button = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
			if (button.isDisplayed()) {
				Thread.sleep(1000);
				button.click();
				Thread.sleep(5000);

				try {
					Utilities.sikuliHandle("Print_Cancel");
					Thread.sleep(2000);
				} catch (Exception ex) {

				}

				Set s = driver.getWindowHandles(); // this method will gives you
													// the handles of all opened
													// windows
				Iterator ite = s.iterator();

				while (ite.hasNext()) {
					String popupHandle = ite.next().toString();
					if (!popupHandle.contains(mainWindow)) {
						driver.switchTo().window(popupHandle);
						driver.close(); // close print popup
						driver.switchTo().window(mainWindow);
						Thread.sleep(2000);
						break;
					}
				}
				Thread.sleep(500);
			}

			System.out.println("Stock Out test [" + documentid + "] completed Successfully !!");
			Thread.sleep(2000);

			Utilities.HoverandClick(pro.getProperty("StockAdjustmentCloseBtn"), driver);

		} catch (Exception EX) {
			System.out.println("Stock Out test [" + documentid + "] Failed !!!!!!!");
			Utilities.handleError(EX, driver);
		}
	}
}
