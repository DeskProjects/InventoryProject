package krawler.erp.inventory;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import krawler.erp.page.SetDateforCycleCount;
import krawler.erp.page.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class CycleCountForm {

	// ********************************************************Set Cycle Count
	// Frequencies*********************************************************
	public static void create_CycleCountForm(String documentid, String productid, String store, String businessDate,
			String cycleQty, String cycleBatch, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_CycleCountForm.properties");
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			WebElement element = null;
			documentid = "CCF" + documentid;
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);

			Utilities.HoverandClick(pro.getProperty("CycleCountFormLink"), driver);
			Thread.sleep(2000);
			Utilities.clickExpander(driver);
			Thread.sleep(1000);

			InvUtilities.selectFromNormalDropDown(store, pro.getProperty("CycleCountFormStore"), driver);
			InvUtilities.selectFromNormalDropDown("NA", pro.getProperty("CycleCountFormSequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("CycleCountFormDocumentId"), driver);

			// SetDateforCycleCount.setCalendar(businessDate, driver); >>> Old
			// code
			Utilities.HoverandClick("//*[@name='countingdate1']/following::img[1]", driver);
			Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Today']", driver);
			Thread.sleep(1500);

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("ExtraItemNoClick")));
				}
			});
			element.click();
			Thread.sleep(1000);

			int itemCodeIndex = 0, looseUOMRowIndex = 0, wslocationlinkIndex = 10;
			for (int rowIndex = 1; rowIndex <= driver
					.findElements(By
							.xpath("//*[@id='CycleCountFormTabId']/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div[1]/div/div[1]/div/div/table/thead/tr/td"))
					.size(); rowIndex++) {
				if (driver.findElement(By
						.xpath("//*[@id='CycleCountFormTabId']/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div[1]/div/div[1]/div/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText().equalsIgnoreCase("Item Code")) {
					itemCodeIndex = rowIndex;
				} else if (driver.findElement(By
						.xpath("//*[@id='CycleCountFormTabId']/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div[1]/div/div[1]/div/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText().equalsIgnoreCase("Loose UoM")) {
					looseUOMRowIndex = rowIndex;
				}
			}
			Thread.sleep(2000);
			int resultRowsCount = driver
					.findElements(By
							.xpath("//*[@id='CycleCountFormTabId']/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div[1]/div/div[2]/div/div/div[2]/div"))
					.size();

			Double sysQuantityValue = 0.0;
			String availbleSerialsStr = null;
			WebElement looseUOMquantity = null, wslocationlink = null, location = null, actualQty = null, sysQty = null,
					batch = null, serial = null, availbleSerials = null;

			for (int j = 1; j <= resultRowsCount; j++) {

				String itemCode = driver.findElement(By
						.xpath("//*[@id='CycleCountFormTabId']/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div[1]/div/div[2]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + itemCodeIndex + "]/div/div"))
						.getText();
				if (!Utilities.isNullOrEmpty(itemCode) && (itemCode.equalsIgnoreCase(productid)
						|| itemCode.equalsIgnoreCase(productid + "B2") || itemCode.equalsIgnoreCase(productid + "S3")
						|| itemCode.equalsIgnoreCase(productid + "BS4"))) {

					looseUOMquantity = driver.findElement(By
							.xpath("//*[@id='CycleCountFormTabId']/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div[1]/div/div[2]/div/div/div[2]/div["
									+ j + "]/table/tbody/tr/td[" + looseUOMRowIndex + "]/div"));
					looseUOMquantity.click();
					Thread.sleep(1000);
					Robot r2 = new Robot();
					r2.keyPress(KeyEvent.VK_CONTROL);
					r2.keyPress(KeyEvent.VK_A);
					r2.keyRelease(KeyEvent.VK_A);
					r2.keyRelease(KeyEvent.VK_CONTROL);
					r2.keyPress(KeyEvent.VK_DELETE);
					r2.keyRelease(KeyEvent.VK_DELETE);
					Utilities.sendText(cycleQty);
					Thread.sleep(1000);
					r2.keyPress(KeyEvent.VK_TAB);
					r2.keyRelease(KeyEvent.VK_TAB);

				}

			}

			for (int j = 1; j <= resultRowsCount; j++) {
				String itemCode = driver.findElement(By
						.xpath("//*[@id='CycleCountFormTabId']/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div[1]/div/div[2]/div/div/div[2]/div["
								+ j + "]/table/tbody/tr/td[" + itemCodeIndex + "]/div/div"))
						.getText();
				if (!Utilities.isNullOrEmpty(itemCode) && (itemCode.equalsIgnoreCase(productid)
						|| itemCode.equalsIgnoreCase(productid + "B2") || itemCode.equalsIgnoreCase(productid + "S3")
						|| itemCode.equalsIgnoreCase(productid + "BS4"))) {
					wslocationlink = driver.findElement(By
							.xpath("//*[@id='CycleCountFormTabId']/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div[1]/div/div[2]/div/div/div[2]/div["
									+ j + "]/table/tbody/tr/td[" + wslocationlinkIndex + "]/div"));
					Thread.sleep(1000);
					wslocationlink.click();
					Thread.sleep(3000);

					for (int i = 1; i <= driver
							.findElements(By
									.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td"))
							.size(); i++) {
						if (driver.findElement(By
								.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
										+ i + "]/div"))
								.getText().equalsIgnoreCase("Location")) {
							location = driver.findElement(By
									.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
											+ i + "]/div"));
							location.click();
							Thread.sleep(1000);
							Robot locationRobot = new Robot();
							Utilities.sendText("Default Location");
							Thread.sleep(1000);
							locationRobot.keyPress(KeyEvent.VK_ENTER);
							locationRobot.keyRelease(KeyEvent.VK_ENTER);
							locationRobot.keyPress(KeyEvent.VK_ENTER);
							locationRobot.keyRelease(KeyEvent.VK_ENTER);
							Thread.sleep(1000);
						}
						if (driver.findElement(By
								.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
										+ i + "]/div"))
								.getText().equalsIgnoreCase("Actual Quantity*")) {
							actualQty = driver.findElement(By
									.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
											+ i + "]/div"));
							actualQty.click();
							Thread.sleep(2000);
							Robot stockTrnsferQtyRobot = new Robot();
							Utilities.sendText(cycleQty);
							Thread.sleep(2000);
							stockTrnsferQtyRobot.keyPress(KeyEvent.VK_ENTER);
							stockTrnsferQtyRobot.keyRelease(KeyEvent.VK_ENTER);
							Thread.sleep(2000);
						}
						if (driver.findElement(By
								.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
										+ i + "]/div"))
								.getText().equalsIgnoreCase("Batch")) {
							batch = driver.findElement(By
									.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
											+ i + "]/div"));
							batch.click();
							Thread.sleep(1000);
							Robot batchRobot = new Robot();
							Utilities.sendText(cycleBatch);
							Thread.sleep(1000);
							batchRobot.keyPress(KeyEvent.VK_ENTER);
							batchRobot.keyRelease(KeyEvent.VK_ENTER);
							Thread.sleep(1000);
						}
						if (driver.findElement(By
								.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
										+ i + "]/div"))
								.getText().equalsIgnoreCase("System Quantity")) {
							sysQty = driver.findElement(By
									.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
											+ i + "]/div"));
							String sysQuantityStr = sysQty.getText();
							sysQuantityValue = Double.parseDouble(sysQuantityStr);
						}
						if (driver.findElement(By
								.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
										+ i + "]/div"))
								.getText().equalsIgnoreCase("Available Serials")) {
							availbleSerials = driver.findElement(By
									.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
											+ i + "]/div"));
							availbleSerialsStr = availbleSerials.getText().trim();

						}
						if (driver.findElement(By
								.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
										+ i + "]/div"))
								.getText().equalsIgnoreCase("Serials *")) {
							serial = driver.findElement(By
									.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
											+ i + "]/div"));
							if (sysQuantityValue < Double.parseDouble(cycleQty)) {
								serial.click();
								if (!Utilities.isNullOrEmpty(availbleSerialsStr)
										&& !availbleSerialsStr.equalsIgnoreCase("")) {
									String[] availbleSerialsStrArray = availbleSerialsStr.split(",");
									if (availbleSerialsStrArray != null && availbleSerialsStrArray.length > 0) {
										// int serialsWindow =
										// driver.findElements(By.xpath(".//*[text()='Serial']/ancestor::div[4]/div[2]/div/div")).size();
										for (int k = 1; k <= availbleSerialsStrArray.length; k++) {
											Robot enteredSerialRobot = new Robot();
											WebElement enteredSerialCheckBox = driver.findElement(
													By.xpath(".//*[text()='Serial']/ancestor::div[4]/div[2]/div/div["
															+ k + "]/table/tbody/tr/td[1]/div/div"));
											enteredSerialCheckBox.click();
											Thread.sleep(1000);
											enteredSerialRobot.keyPress(KeyEvent.VK_TAB);
											enteredSerialRobot.keyRelease(KeyEvent.VK_TAB);
										}
										for (int k = availbleSerialsStrArray.length + 1; k <= Double
												.parseDouble(cycleQty); k++) {
											WebElement enteredSerial = driver.findElement(
													By.xpath(".//*[text()='Serial']/ancestor::div[4]/div[2]/div/div["
															+ k + "]/table/tbody/tr/td[3]/div"));
											enteredSerial.click();
											Thread.sleep(2000);
											Robot enteredSerialRobot = new Robot();
											Utilities.sendText("Ser" + k);
											enteredSerialRobot.keyPress(KeyEvent.VK_ENTER);
											enteredSerialRobot.keyRelease(KeyEvent.VK_ENTER);
											Thread.sleep(2000);
											WebElement enteredSerialCheckBox = driver.findElement(
													By.xpath(".//*[text()='Serial']/ancestor::div[4]/div[2]/div/div["
															+ k + "]/table/tbody/tr/td[1]/div/div"));
											enteredSerialCheckBox.click();
											Thread.sleep(2000);
											enteredSerialRobot.keyPress(KeyEvent.VK_TAB);
											enteredSerialRobot.keyRelease(KeyEvent.VK_TAB);
										}
									}
								} else {
									for (int k = 1; k <= Double.parseDouble(cycleQty); k++) {
										WebElement enteredSerial = driver.findElement(
												By.xpath(".//*[text()='Serial']/ancestor::div[4]/div[2]/div/div[" + k
														+ "]/table/tbody/tr/td[3]/div"));
										enteredSerial.click();
										Thread.sleep(2000);
										Robot enteredSerialRobot = new Robot();
										Utilities.sendText("Ser" + k);
										enteredSerialRobot.keyPress(KeyEvent.VK_ENTER);
										enteredSerialRobot.keyRelease(KeyEvent.VK_ENTER);
										Thread.sleep(2000);
										WebElement enteredSerialCheckBox = driver.findElement(
												By.xpath(".//*[text()='Serial']/ancestor::div[4]/div[2]/div/div[" + k
														+ "]/table/tbody/tr/td[1]/div/div"));
										enteredSerialCheckBox.click();
										Thread.sleep(2000);
										enteredSerialRobot.keyPress(KeyEvent.VK_TAB);
										enteredSerialRobot.keyRelease(KeyEvent.VK_TAB);
									}
								}
								element = wait.until(new Function<WebDriver, WebElement>() {

									// apply method- which accept webdriver as
									// input
									// @Override
									public WebElement apply(WebDriver webDriverArg) {
										// find the element
										return webDriverArg
												.findElement(By.xpath(pro.getProperty("saveSerialWinowButton")));
									}
								});
								element.click();
								// }
							} else {
								serial.click();
								if (!Utilities.isNullOrEmpty(availbleSerialsStr)
										&& !availbleSerialsStr.equalsIgnoreCase("")) {
									String[] availbleSerialsStrArray = availbleSerialsStr.split(",");
									if (availbleSerialsStrArray != null && availbleSerialsStrArray.length > 0) {
										// int serialsWindow =
										// driver.findElements(By.xpath(".//*[text()='Serial']/ancestor::div[4]/div[2]/div/div")).size();
										for (int k = 1; k <= Double.parseDouble(cycleQty); k++) {
											Robot enteredSerialRobot = new Robot();
											WebElement enteredSerialCheckBox = driver.findElement(
													By.xpath(".//*[text()='Serial']/ancestor::div[4]/div[2]/div/div["
															+ k + "]/table/tbody/tr/td[1]/div/div"));
											enteredSerialCheckBox.click();
											Thread.sleep(1000);
											enteredSerialRobot.keyPress(KeyEvent.VK_TAB);
											enteredSerialRobot.keyRelease(KeyEvent.VK_TAB);
										}
										element = wait.until(new Function<WebDriver, WebElement>() {

											// apply method- which accept
											// webdriver as input
											// @Override
											public WebElement apply(WebDriver webDriverArg) {
												// find the element
												return webDriverArg.findElement(
														By.xpath(pro.getProperty("saveSerialWinowButton")));
											}
										});
										element.click();
									}
								}
							}

						}

					}
					element = wait.until(new Function<WebDriver, WebElement>() {
						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg
									.findElement(By.xpath(pro.getProperty("saveCycleCountFormQtyLocationButton")));
						}
					});
					element.click();

				}

			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("saveCycleCountFormBtn")));
				}
			});

			element.click();
			Thread.sleep(2000);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);
			Thread.sleep(1000);

			Utilities.HoverandClick(pro.getProperty("CycleCountFormCloseBtn"), driver);
			System.out.println("***** Cycle form Completed for [" + businessDate + "] date *********");

		} catch (Exception EX) {
			System.out.println("***** Cycle form Failed for [" + businessDate + "] date *********");
			Utilities.handleError(EX, driver);
		}
	}

}
