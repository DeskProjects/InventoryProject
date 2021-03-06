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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.google.common.base.Function;

public class InterStoreStockTransfer {

	// ********************************************************Create
	// InterStoreStockTransfer*********************************************************
	public static void create_InterStoreStockTransfer(String documentid, String productid, String fromStore,
			String toStore, String quanityValue, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_InterStoreStockTransfer.properties");
			documentid = "ISST" + documentid;
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);

			Utilities.HoverandClick(pro.getProperty("InterStoreStockTransferLink"), driver);
			Utilities.disableExpander(driver);
			InvUtilities.selectFromNormalDropDown(fromStore, pro.getProperty("InterStoreStockTransferFromStore"),
					driver);
			InvUtilities.selectFromNormalDropDown(toStore, pro.getProperty("InterStoreStockTransferToStore"), driver);
			InvUtilities.selectFromNormalDropDown("NA", pro.getProperty("InterStoreStockTransferSequenceFormat"),
					driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("InterStoreStockTransferDocumentId"), driver);

			Utilities.HoverandClick(pro.getProperty("InterStoreStockTransferAddPrdButton"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("InterStoreStockTransferSearch"), driver);
			Utilities.isLoadingDisappear(120, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			Utilities.click_element(pro.getProperty("InterStoreStockTransferAddButton"), driver);
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
						Utilities.enter_LinelabelAmount(quanityValue, driver);
					}
				}
			}

			// ----- Enter Qauntity & serial
			for (int k = 1; k < totalProducts; k++) {
				String xpathOflocation = null;
				xpathOflocation = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[" + k
						+ "]/table/tbody/tr//*[@class='pwnd serialNo-gridrow']";
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
				}
				Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Save']", driver); // save
																										// button
				Thread.sleep(1000);
			}

			String ParentWindow = driver.getWindowHandle(); // beforesave button
			Utilities.HoverandClick(pro.getProperty("saveInterStoreStockTransferBtn"), driver);
			Utilities.clickButton("Yes", 600, driver);
			HandlePrintWindow.closePrintWindow(ParentWindow, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("InterStoreStockTransferCloseBtn"), driver);
			System.out.println(
					"******** Inter Store Stock Transfer :[" + documentid + "] has been succesfully Created ********");

		} catch (Exception EX) {
			System.out.println("!!!!!!!!!!!!!!!! FAILED to create Inter Store Stock Transfer :[" + documentid
					+ "] check log !!!!!!!!!!!!!!!! ");
			;
			Utilities.handleError(EX, driver);
		}
	}

	// ********************************************************InterStoreStockTransfer
	// Creation*********************************************************
	public static void create_InterStoreStockTransfer_NiteshSir(String documentid, String productid, String fromStore,
			String toStore, String quanityValue, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_InterStoreStockTransfer.properties");
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			WebElement element = null;
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);

			Utilities.HoverandClick(pro.getProperty("InterStoreStockTransferLink"), driver);
			Thread.sleep(3000);
			Utilities.clickExpander(driver);

			InvUtilities.selectFromNormalDropDown(fromStore, pro.getProperty("InterStoreStockTransferFromStore"),
					driver);
			InvUtilities.selectFromNormalDropDown(toStore, pro.getProperty("InterStoreStockTransferToStore"), driver);
			InvUtilities.selectFromNormalDropDown("NA", pro.getProperty("InterStoreStockTransferSequenceFormat"),
					driver);
			Utilities.enterTextNormalBox(documentid, pro.getProperty("InterStoreStockTransferDocumentId"), driver);

			Thread.sleep(1000);

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("InterStoreStockTransferAddPrdButton")));
				}
			});
			element.click();
			Thread.sleep(3000);

			Utilities.clickCheckBox(pro.getProperty("firstProduct"), "uncheck", driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("InterStoreStockTransferSearch"), driver);
			Thread.sleep(3000);
			Utilities.clickCheckBox(pro.getProperty("SelectproductSS"), "check", driver);

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg
							.findElement(By.xpath(pro.getProperty("InterStoreStockTransferSelectProductsResultCount")));
				}
			});

			String resultText = element.getText();
			String[] resultTextArray = resultText.split("of");
			String resultSetTotalCntString = "";
			for (String resultTextElement : resultTextArray) {
				resultSetTotalCntString = resultTextElement;
			}
			int resultSetTotalCnt = Integer.parseInt(resultSetTotalCntString.trim());
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("InterStoreStockTransferAddButton")));
				}
			});
			element.click();
			Thread.sleep(2000);

			int qtyRowIndex = 0, productIdRowIndex = 0, wslocationlinkIndex = 14;
			WebElement quantity = null, wslocationlink = null, serial = null, productIDWE = null,
					stockTransferQty = null, stockTransferSerial = null;
			List<String> pidserArrayList = new ArrayList<String>();

			for (int tableheaderRowIndex = 1; tableheaderRowIndex <= driver
					.findElements(By
							.xpath("//div[@id='interstoretransfer']/div/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size(); tableheaderRowIndex++) {
				if (driver
						.findElement(By
								.xpath("//div[@id='interstoretransfer']/div/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
										+ tableheaderRowIndex + "]/div"))
						.getText().equalsIgnoreCase("Quantity (in Transfer UoM)")) {
					qtyRowIndex = tableheaderRowIndex;
					break;
				} else if (driver.findElement(By
						.xpath("//div[@id='interstoretransfer']/div/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ tableheaderRowIndex + "]/div"))
						.getText().contains("Product ID")) {
					productIdRowIndex = tableheaderRowIndex;
				}
			}
			for (int j = 1; j <= resultSetTotalCnt; j++) {
				quantity = driver.findElement(By
						.xpath("//div[@id='interstoretransfer']/div/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[1]/div["
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

				// r2.keyPress(KeyEvent.VK_CONTROL);
				// r2.keyPress(KeyEvent.VK_A);
				// r2.keyRelease(KeyEvent.VK_A);
				// r2.keyRelease(KeyEvent.VK_CONTROL);
				// r2.keyPress(KeyEvent.VK_DELETE);
				// r2.keyRelease(KeyEvent.VK_DELETE);
				// Utilities.sendText(quanityValue);
				// Thread.sleep(1000);

			}

			for (int j = 1; j <= resultSetTotalCnt; j++) {
				productIDWE = driver.findElement(By
						.xpath("//div[@id='interstoretransfer']/div/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div/div/div/div[1]/div[2]/div/div["
								+ j + "]/table/tbody/tr/td[" + productIdRowIndex + "]/div")); // last
																								// div
																								// removed
				String proudctID = productIDWE.getText();
				wslocationlink = driver.findElement(By
						.xpath("//div[@id='interstoretransfer']/div/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div/div/div/div[1]/div[2]/div/div["
								+ j + "]/table/tbody/tr/td[" + wslocationlinkIndex + "]/div/div"));
				Thread.sleep(1000);
				wslocationlink.click();
				Thread.sleep(1000);
				for (int i = 1; i <= driver
						.findElements(By
								.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td"))
						.size(); i++) {
					if (driver.findElement(By
							.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText().equalsIgnoreCase("Quantity*")) {
						stockTransferQty = driver.findElement(By
								.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr/td["
										+ i + "]/div"));
					}
				}
				stockTransferQty.click();
				Thread.sleep(2000);
				Robot stockTransferQtyRobot = new Robot();
				Utilities.sendText(quanityValue);
				Thread.sleep(2000);
				stockTransferQtyRobot.keyPress(KeyEvent.VK_ENTER);
				stockTransferQtyRobot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);

				for (int i = 1; i <= driver
						.findElements(By
								.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td"))
						.size(); i++) {
					if (driver.findElement(By
							.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText().equalsIgnoreCase("Serials*")) {
						stockTransferSerial = driver.findElement(By
								.xpath(".//*[@class='x-window-mc']/div/div/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr/td["
										+ i + "]/div"));
						stockTransferSerial.click();
						Thread.sleep(2000);
						// String[] selectStockIssueSerialNumbers = new
						// String[Integer.parseInt(quanityValue)];
						StringBuilder stb = new StringBuilder();
						stb.append(proudctID).append(", ");
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
							// selectStockIssueSerialNumbers[selectOutCount-1]=selectedSerialNo;
							stb.append(selectedSerialNo).append(", ");
						}

						String issuedSerialsFromUICommaSep = stb.substring(0, stb.length() - 2);
						pidserArrayList.add(issuedSerialsFromUICommaSep);

						element = wait.until(new Function<WebDriver, WebElement>() {
							public WebElement apply(WebDriver webDriverArg) {
								// find the element
								return webDriverArg
										.findElement(By.xpath(pro.getProperty("InterStoreStockTransferRightArrowImg")));
							}
						});
						element.click();

						element = wait.until(new Function<WebDriver, WebElement>() {
							// apply method- which accept webdriver as input
							// @Override
							public WebElement apply(WebDriver webDriverArg) {
								// find the element
								return webDriverArg.findElement(
										By.xpath(pro.getProperty("InterStoreStockTransferSelectAllSerialsSubmit")));
							}
						});
						element.click();

						Object[] objArr = pidserArrayList.toArray();
						String[] selectStockIssueSerialNumbers = new String[objArr.length];
						if (objArr != null && objArr.length > 0) {
							int count = 0;
							for (Object objectElement : objArr) {
								System.out.println("Element: " + objectElement);
								selectStockIssueSerialNumbers[count] = objectElement.toString();
								count++;
							}
						}
						Utilities.writeSerialsToFile(documentid + "InterStoreStockTransferserials"
								+ Utilities.currentDate("dd-MM-yyyy") + ".txt", selectStockIssueSerialNumbers);
					}

				}

				element = wait.until(new Function<WebDriver, WebElement>() {
					// apply method- which accept webdriver as input
					// @Override
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg
								.findElement(By.xpath(pro.getProperty("saveInterStoreStockTransferQtyLocationButton")));
					}
				});
				element.click();

			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("saveInterStoreStockTransferBtn")));
				}
			});
			String currentWindow = driver.getWindowHandle(); // beforesave
																// button
			element.click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 1000, driver);

			try {
				// Handle print button
				Screen screen = new Screen();
				Pattern cancel_button = new Pattern("E:\\Sikuli Snaps\\Print_Cancel.PNG");
				screen.click(cancel_button);
				Thread.sleep(3000);
			} catch (Exception Ex) {
			}

			Set s = driver.getWindowHandles(); // this method will gives you the
												// handles of all opened windows
			Iterator ite = s.iterator();

			while (ite.hasNext()) {
				String popupHandle = ite.next().toString();
				if (!popupHandle.contains(currentWindow)) {
					driver.switchTo().window(popupHandle);
					driver.close(); // close print popup
					driver.switchTo().window(currentWindow);
					Thread.sleep(2000);
				}
			}

			Thread.sleep(1000);
			Utilities.clickButton("OK", 1000, driver);
			Thread.sleep(1000);

			System.out.println("Inter Store Stock Transfer created successfully for : [" + documentid + "]");

			Utilities.HoverandClick(pro.getProperty("InterStoreStockTransferCloseBtn"), driver);
			Thread.sleep(1500);

		} catch (Exception EX) {
			System.out.println(
					"Inter Store Stock Transfer is NOT created for : [" + documentid + "] Please check Log !!!!!");
			;
			Utilities.handleError(EX, driver);
		}
	}

	// ********************************************************InterStoreStockTransfer
	// Deletion*********************************************************
	public static void delete_InterStoreStockTransfer(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_InterStoreStockTransfer.properties");
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
					return webDriverArg.findElement(By.xpath(pro.getProperty("InterStoreStockTransferDetailsLink")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg
							.findElement(By.xpath(pro.getProperty("InterStoreStockTransferOutGoingRequestTabLink")));
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
							.findElement(By.xpath(pro.getProperty("InterStoreStockTransferDetailsQuickSearch")));
				}
			});
			element.sendKeys(documentid);
			Thread.sleep(1000);
			element.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			if (driver
					.findElement(By
							.xpath("//div[@id='inventEditorGridPaneloutgoingRequest']/div/div[3]/div/table/following-sibling::div"))
					.getText().equalsIgnoreCase("No data to display")) {
				System.out.println("Inter Store Stock Transfer " + documentid + "  doesn't Exist");
			} else {
				System.out.println("Inter Store Stock Transfer " + documentid + "  found Successfully");
				element = wait.until(new Function<WebDriver, WebElement>() {

					// apply method- which accept webdriver as input
					// @Override
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg.findElement(
								By.xpath(pro.getProperty("InterStoreStockTransferDetailsQuickSearchResultText")));
					}
				});

				String text = element.getText();
				if (text.trim().equalsIgnoreCase("Transfer Note No.: " + documentid)) {
					System.out.println("Inter Store Stock Transfer " + documentid + "  listed Successfully");
					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(
									By.xpath(pro.getProperty("InterStoreStockTransferDetailsSelectAllChkBox")));
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
									.findElement(By.xpath(pro.getProperty("InterStoreStockTransferdeleteBtn")));
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
							return webDriverArg.findElement(
									By.xpath(pro.getProperty("InterStoreStockTransferdeletedSuccessOKBtn")));
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
									By.xpath(pro.getProperty("InterStoreStockTransferDetailsQuickSearch")));
						}
					});
					element.clear();
					Thread.sleep(3000);
					element.sendKeys(documentid);
					Thread.sleep(3000);
					int statusRowIndex = 0;
					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(
									By.xpath(pro.getProperty("InterStoreStockTransferDetailsQuickSearchCnt")));
						}
					});

					String resultText = element.getText();
					String[] resultTextArray = resultText.split("of");
					String resultSetTotalCntString = "";
					for (String resultTextElement : resultTextArray) {
						resultSetTotalCntString = resultTextElement;
					}
					int resultSetTotalCnt = Integer.parseInt(resultSetTotalCntString.trim());
					for (statusRowIndex = 1; statusRowIndex <= driver
							.findElements(By
									.xpath("//div[@id='inventEditorGridPaneloutgoingRequest']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td"))
							.size(); statusRowIndex++) {
						if (driver.findElement(By
								.xpath("//div[@id='inventEditorGridPaneloutgoingRequest']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
										+ statusRowIndex + "]/div"))
								.getText().equalsIgnoreCase("Status")) {
							break;
						}
					}

					for (int j = 1; j <= resultSetTotalCnt; j++) {
						String documentStatus = driver.findElement(By
								.xpath("//div[@id='inventEditorGridPaneloutgoingRequest']/div/div[2]/div/div[1]/div[2]/div//div/div[2]/div["
										+ resultSetTotalCnt + "]/table/tbody/tr[1]/td[" + statusRowIndex + "]/div"))
								.getText();
						assertEquals(documentStatus, "Deleted");

					}

				}
			}

			Thread.sleep(1100);
			Utilities.HoverandClick(pro.getProperty("InterStoreStockTransferDetailsCloseBtn"), driver);

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ------------------------ Accept InterStoreStockTransfer
	// ---------------------------------------
	public static void accept_InterStoreStockTransfer(String documentid, String acceptedQuanity, String toLocation,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_InterStoreStockTransfer.properties");
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandReport(driver);
			documentid = "ISST" + documentid;

			Utilities.HoverandClick(pro.getProperty("InterStoreStockTransferDetailsLink"), driver);
			Utilities.HoverandClick(pro.getProperty("InterStoreStockTransferIncomingRequestTabLink"), driver);
			Utilities.disableExpander(driver);

			WebElement moveFocus = driver
					.findElement(By.xpath(pro.getProperty("InterStoreStockTransferDetailsIncomingQuickSearch")));
			Utilities.enterTextInDropDown(documentid,
					pro.getProperty("InterStoreStockTransferDetailsIncomingQuickSearch"), driver);
			Thread.sleep(1100);

			if (driver
					.findElement(
							By.xpath(pro.getProperty("InterStoreStockTransferDetailsIncomingQuickSearchResultCount")))
					.getText().equalsIgnoreCase("No data to display")) {
				System.out.println("Inter Store Stock Transfer " + documentid + "  doesn't Exist");
			} else {

				// -------- Enter Accpeted Qty --------
				int headerSize = Utilities.totalSize(
						"//*[contains(@id,'inventEditorGridPanelincomingRequest')]//*[text()='From Store']/ancestor::tr/td/div",
						driver);
				int totalPrd = Utilities.totalSize(
						"//*[contains(@id,'inventEditorGridPanelincomingRequest')]//*[text()='From Store']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div",
						driver);
				for (int k = 1; k <= totalPrd; k++) {
					for (int i = 1; i <= headerSize; i++) {
						String headerName = driver.findElement(By
								.xpath("//*[contains(@id,'inventEditorGridPanelincomingRequest')]//*[text()='From Store']/ancestor::tr/td["
										+ i + "]/div"))
								.getText();
						if (headerName.equalsIgnoreCase("Issued Quantity")) {
							Utilities.HoverandClick(
									"//*[contains(@id,'inventEditorGridPanelincomingRequest')]//*[text()='From Store']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div["
											+ k + "]/table/tbody/tr/td[" + i + "]/div",
									driver);
							Utilities.enter_LinelabelAmount(acceptedQuanity, driver);
							moveFocus.click();
						}
					}
				}

				// ----- Enter Qauntity & serial
				for (int k = 1; k <= totalPrd; k++) {
					String xpathOflocation = null;
					xpathOflocation = "//*[contains(@id,'inventEditorGridPanelincomingRequest')]//*[text()='From Store']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div["
							+ k + "]/table/tbody/tr/td[@style='width:38px;']//*[@class='pwnd serialNo-gridrow']";
					Utilities.HoverandClick(xpathOflocation, driver);
					Thread.sleep(1500);

					int headerTransferSize = 0;
					headerTransferSize = Utilities.totalSize("//*[text()='Issue Location ']/ancestor::tr/td/div",
							driver);
					for (int head = 1; head <= headerTransferSize; head++) {
						String headName = driver
								.findElement(
										By.xpath("//*[text()='Issue Location ']/ancestor::tr/td[" + head + "]/div"))
								.getText();
						if (headName.equalsIgnoreCase("Quantity*")) {
							Utilities.HoverandClick(
									"//*[text()='Issue Location ']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
											+ head + "]/div",
									driver);
							Utilities.enter_LinelabelAmount(acceptedQuanity, driver);
							Utilities.enter_LinelabelAmount_thenEnter(driver);
						}

						if (headName.equalsIgnoreCase("Serials*")) {
							Utilities.HoverandClick(
									"//*[text()='Issue Location ']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
											+ head + "]/div",
									driver);
							Thread.sleep(1000);
							// transfer serials
							for (int serial = 1; serial <= Integer.parseInt(acceptedQuanity); serial++) {
								Utilities
										.HoverandClick(
												"//*[@id='serialSelectWindow']//*[@class='x-column-inner']/div[1]//*[text()='Name']/ancestor::div[3]/following::div[1]/div/div["
														+ serial + "]/table/tbody/tr//*[@class='x-grid3-row-checker']",
												driver);
							}
							// transfer
							Utilities.HoverandClick(
									"//*[@id='serialSelectWindow']//*[contains(@src,'images/arrowright')]", driver);
							Utilities.HoverandClick("//*[@id='serialSelectWindow']//*[text()='Submit']", driver);
						}

						if (headName.equalsIgnoreCase("Collect Location *")) {
							Utilities.HoverandClick(
									"//*[text()='Issue Location ']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td["
											+ head + "]/div",
									driver);
							Thread.sleep(250);
							Utilities.enterTextandSelect(toLocation, "//*[contains(@style,'visible')]/div/input",
									driver);
						}
					}
					Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Save']", driver); // save
																											// button
				}

				String selectAllxpath = "//*[contains(@id,'inventEditorGridPanelincomingRequest')]//*[text()='From Store']/ancestor::tr/td//*[@class='x-grid3-hd-checker']";
				Utilities.HoverandClick(selectAllxpath, driver);
				Utilities.HoverandClick(pro.getProperty("acceptInterStoreStockTransferDetailsIncomingBtn"), driver);
				Utilities.clickButton("Yes", 1000, driver);
				Utilities.clickButton("OK", 1500, driver);

				System.out.println("***** Successfully Accepted quantity : [" + acceptedQuanity
						+ "] for Inter Store Stock transfer [" + documentid + "] *****");
			}

			Utilities.HoverandClick(pro.getProperty("InterStoreStockTransferDetailsIncomingCloseBtn"), driver);
		} catch (Exception EX) {
			System.out.println("***** FAILED to Accept quantity : [" + acceptedQuanity
					+ "] for Inter Store Stock transfer [" + documentid + "] *****");
			Utilities.handleError(EX, driver);
		}
	}

	// ********************************************************InterStoreStockTransfer
	// Accept Returned
	// Qty*********************************************************
	public static void acceptReturnedQty_InterStoreStockTransfer(String documentid, String acceptReturnQty,
			WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {
			final Properties pro = Utilities.fetchProValue("OR_InterStoreStockTransfer.properties");
			documentid = "RISST" + documentid;

			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandReport(driver);

			Utilities.HoverandClick(pro.getProperty("InterStoreStockTransferDetailsLink"), driver);
			Utilities.HoverandClick(pro.getProperty("InterStoreStockTransferIncomingRequestTabLink"), driver);
			Utilities.disableExpander(driver);

			Utilities.enterTextInDropDown(documentid,
					pro.getProperty("InterStoreStockTransferDetailsIncomingQuickSearch"), driver);
			Thread.sleep(1100);
			if (driver
					.findElement(
							By.xpath(pro.getProperty("InterStoreStockTransferDetailsIncomingQuickSearchResultCount")))
					.getText().equalsIgnoreCase("No data to display")) {
				System.out.println("Inter Store Stock Transfer " + documentid + "  doesn't Exist");
			} else {

				// -------- Enter Accpeted Qty --------
				int headerSize = Utilities.totalSize(
						"//*[contains(@id,'inventEditorGridPanelincomingRequest')]//*[text()='From Store']/ancestor::tr/td/div",
						driver);
				int totalPrd = Utilities.totalSize(
						"//*[contains(@id,'inventEditorGridPanelincomingRequest')]//*[text()='From Store']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div",
						driver);
				for (int k = 1; k <= totalPrd; k++) {
					for (int i = 1; i <= headerSize; i++) {
						String headerName = driver.findElement(By
								.xpath("//*[contains(@id,'inventEditorGridPanelincomingRequest')]//*[text()='From Store']/ancestor::tr/td["
										+ i + "]/div"))
								.getText();
						if (headerName.equalsIgnoreCase("Issued Quantity")) {
							Utilities.HoverandClick(
									"//*[contains(@id,'inventEditorGridPanelincomingRequest')]//*[text()='From Store']/ancestor::div[3]/following::div[1]/div[1]/div/div[2]/div[1]/table/tbody/tr/td["
											+ i + "]/div",
									driver);
							Utilities.enter_LinelabelAmount(acceptReturnQty, driver);
							Utilities.clickButton("Accept", 1000, driver);
							Utilities.clickButton("No", 1000, driver);
							Utilities.clickButton("OK", 1500, driver);
						}
					}
				}
				System.out.println("***** Successfully Accepted Return quantity : [" + acceptReturnQty
						+ "] for Inter Store Stock transfer [" + documentid + "] *****");
			}
			Utilities.HoverandClick(pro.getProperty("InterStoreStockTransferDetailsIncomingCloseBtn"), driver);
		} catch (Exception EX) {
			System.out.println("***** FAILED to Accept Return quantity : [" + acceptReturnQty
					+ "] for Inter Store Stock transfer [" + documentid + "] *****");
			Utilities.handleError(EX, driver);
		}
	}

	// ********************************************************InterStoreStockTransfer
	// Reject*********************************************************
	public static void reject_InterStoreStockTransfer(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			final Properties pro = Utilities.fetchProValue("OR_InterStoreStockTransfer.properties");
			Utilities.refresh();
			documentid = "InterStoreStockTran" + documentid;

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
					return webDriverArg.findElement(By.xpath(pro.getProperty("InterStoreStockTransferDetailsLink")));
				}
			});
			element.click();

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg
							.findElement(By.xpath(pro.getProperty("InterStoreStockTransferIncomingRequestTabLink")));
				}
			});
			element.click();
			Thread.sleep(3000);
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(
							By.xpath(pro.getProperty("InterStoreStockTransferDetailsIncomingQuickSearch")));
				}
			});
			element.sendKeys(documentid);
			Thread.sleep(1000);
			element.sendKeys(Keys.ENTER);
			Thread.sleep(3000);

			if (driver
					.findElement(
							By.xpath(pro.getProperty("InterStoreStockTransferDetailsIncomingQuickSearchResultCount")))
					.getText().equalsIgnoreCase("No data to display")) {
				System.out.println("Inter Store Stock Transfer " + documentid + "  doesn't Exist");
			} else {
				System.out.println("Inter Store Stock Transfer " + documentid + "  found Successfully");
				element = wait.until(new Function<WebDriver, WebElement>() {

					// apply method- which accept webdriver as input
					// @Override
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg.findElement(By
								.xpath(pro.getProperty("InterStoreStockTransferDetailsIncomingQuickSearchResultText")));
					}
				});

				String text = element.getText();
				if (text.trim().equalsIgnoreCase("Transfer Note No.: " + documentid)) {
					System.out.println("Inter Store Stock Transfer " + documentid + "  listed Successfully");
					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(
									By.xpath(pro.getProperty("InterStoreStockTransferDetailsIncomingSelectAllChkBox")));
						}
					});
					element.click();
					Thread.sleep(1000);

					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(
									By.xpath(pro.getProperty("rejectInterStoreStockTransferDetailsIncomingBtn")));
						}
					});
					element.click();
					Thread.sleep(3000);

					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(
									By.xpath(pro.getProperty("InterStoreStockTransferDetailsIncomingRemarkText")));
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
							return webDriverArg.findElement(
									By.xpath(pro.getProperty("InterStoreStockTransferDetailsIncomingRemarkSubmitBtn")));
						}
					});
					element.click();

					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(By.xpath(
									pro.getProperty("InterStoreStockTransferDetailsIncomingRejectConfirmationBtn")));
						}
					});
					element.click();
					Thread.sleep(5000);
					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(By.xpath(
									pro.getProperty("InterStoreStockTransferDetailsIncomingRejectedSuccessOKBtn")));
						}
					});
					element.click();
					Thread.sleep(2000);
					System.out.println("Returned Inter Store Stock Transfer " + documentid + "  rejected Successfully");
					element = wait.until(new Function<WebDriver, WebElement>() {

						// apply method- which accept webdriver as input
						// @Override
						public WebElement apply(WebDriver webDriverArg) {
							// find the element
							return webDriverArg.findElement(
									By.xpath(pro.getProperty("InterStoreStockTransferDetailsIncomingCloseBtn")));
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
