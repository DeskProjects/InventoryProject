package krawler.erp.page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class SystemControls {

	// ********************************************************Check QA Approval
	// Flow Status*********************************************************
	public static boolean isQAApprovalFlowEnabled(WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		boolean isQAApprovalFlowActive = false;
		try {
			final Properties pro = Utilities.fetchProValue("OR_SystemControls.properties");
			Utilities.refresh();
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			WebElement element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("PreferencesExpander")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("SystemControlsLink")));
				}
			});
			element.click();
			Thread.sleep(10000);
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("ActivateQAApprovalFlowCheckBox")));
				}
			});
			if (element.isSelected()) {
				System.out.println("QA Inspection Flow is already Activated");
				isQAApprovalFlowActive = true;
			} else {
				System.out.println("QA Inspection Flow is not Activated");
			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("BuildAssemblyQAApprovalFlowCheckBox")));
				}
			});
			if (element.isSelected()) {
				System.out.println("QA Inspection for Build Assembly Items is already Activated");
				isQAApprovalFlowActive = true;
			} else {
				System.out.println("QA Inspection for Build Assembly Items is not Activated");
			}
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockINApprovalQAFlowCheckBox")));
				}
			});
			if (element.isSelected()) {
				System.out.println("Stock IN Approval Flow is already Activated");
				isQAApprovalFlowActive = true;
			} else {
				System.out.println("Stock IN Approval Flow is not Activated");
			}
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg
							.findElement(By.xpath(pro.getProperty("InterStoreStockReturnQAApprovalFlowCheckBox")));
				}
			});
			if (element.isSelected()) {
				System.out.println("Inter Store Stock Return Approval Flow is already Activated");
				isQAApprovalFlowActive = true;
			} else {
				System.out.println("Inter Store Stock Return Approval Flow is not Activated");
			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg
							.findElement(By.xpath(pro.getProperty("StockRequestReturnQAApprovalFlowCheckBox")));
				}
			});
			if (element.isSelected()) {
				System.out.println("Stock Request Return Approval Flow is already Activated");
				isQAApprovalFlowActive = true;
			} else {
				System.out.println("Stock Request Return Approval Flow is not Activated");
			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg
							.findElement(By.xpath(pro.getProperty("GoodsReceiptNoteQAApprovalFlowCheckBox")));
				}
			});
			if (element.isSelected()) {
				System.out.println("QA Approval Flow in Goods Receipt Note is already Activated");
				isQAApprovalFlowActive = true;
			} else {
				System.out.println("QA Approval Flow in Goods Receipt Note is not Activated");
			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("DeliveryOrderQAApprovalFlowCheckBox")));
				}
			});
			if (element.isSelected()) {
				System.out.println("QA Approval Flow in Delivery Order is already Activated");
				isQAApprovalFlowActive = true;
			} else {
				System.out.println("QA Approval Flow in Delivery Order is not Activated");
			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath("//input[@name='restrictDuplicateBatch']"));
				}
			});
			if (element.isSelected()) {
				System.out.println("Allowing duplicate Batch No. ");
				element.click();
			} else {
				System.out.println("Duplicate Batch no already allowed !!!");
			}
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("AccountPreferencesTabCloseBtn")));
				}
			});
			element.click();

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("yesButton")));
				}
			});
			element.click();

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}

		return isQAApprovalFlowActive;
	}

	// ********************************************************Check QA Approval
	// Flow Status*********************************************************
	public static void enableQAApprovalFlow(String inspectionStore, String repairStore, String scrapStore,
			WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {
			final Properties pro = Utilities.fetchProValue("OR_SystemControls.properties");
			Utilities.refresh();
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			WebElement element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("PreferencesExpander")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("SystemControlsLink")));
				}
			});
			element.click();
			Thread.sleep(10000);
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("ActivateQAApprovalFlowCheckBox")));
				}
			});
			if (!element.isSelected()) {
				element.click();
				System.out.println("QA Inspection Flow is Activated");

			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("BuildAssemblyQAApprovalFlowCheckBox")));
				}
			});
			if (!element.isSelected()) {
				element.click();
				System.out.println("QA Inspection for Build Assembly Items is Activated");

			}
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("StockINApprovalQAFlowCheckBox")));
				}
			});
			if (!element.isSelected()) {
				element.click();
				System.out.println("Stock IN Approval Flow is Activated");

			}
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg
							.findElement(By.xpath(pro.getProperty("InterStoreStockReturnQAApprovalFlowCheckBox")));
				}
			});
			if (!element.isSelected()) {
				element.click();
				System.out.println("Inter Store Stock Return Approval Flow is Activated");

			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg
							.findElement(By.xpath(pro.getProperty("StockRequestReturnQAApprovalFlowCheckBox")));
				}
			});
			if (!element.isSelected()) {
				element.click();
				System.out.println("Stock Request Return Approval Flow is Activated");

			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg
							.findElement(By.xpath(pro.getProperty("GoodsReceiptNoteQAApprovalFlowCheckBox")));
				}
			});
			if (!element.isSelected()) {
				element.click();
				System.out.println("QA Approval Flow in Goods Receipt Note is Activated");

			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("DeliveryOrderQAApprovalFlowCheckBox")));
				}
			});
			if (!element.isSelected()) {
				element.click();
				System.out.println("QA Approval Flow in Delivery Order is Activated");

			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("QAInspectionStore")));
				}
			});
			element.click();
			Thread.sleep(2000);
			Robot inspectionRobot = new Robot();
			Utilities.sendText(inspectionStore);
			Thread.sleep(2000);
			inspectionRobot.keyPress(KeyEvent.VK_ENTER);
			inspectionRobot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("QARepairStore")));
				}
			});
			element.click();
			Thread.sleep(2000);
			Robot repairRobot = new Robot();
			Utilities.sendText(repairStore);
			Thread.sleep(2000);
			repairRobot.keyPress(KeyEvent.VK_ENTER);
			repairRobot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("QAScrapStore")));
				}
			});
			element.click();
			Thread.sleep(2000);
			Robot scrapRobot = new Robot();
			Utilities.sendText(scrapStore);
			Thread.sleep(2000);
			scrapRobot.keyPress(KeyEvent.VK_ENTER);
			scrapRobot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("saveAccountPreferencesBtn")));
				}
			});
			element.click();
			Thread.sleep(2000);
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("applyNewsettingsBtn")));
				}
			});
			element.click();
			Thread.sleep(2000);
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("AccountPreferencesSavedOKBtn")));
				}
			});
			element.click();

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}

	}

	// ********************************************************Activate Cycle
	// Count*********************************************************
	public static void activateCycleCount(WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {
			final Properties pro = Utilities.fetchProValue("OR_SystemControls.properties");
			Utilities.refresh();
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			WebElement element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("PreferencesExpander")));
				}
			});
			element.click();
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("SystemControlsLink")));
				}
			});
			element.click();
			Thread.sleep(10000);
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("ActivateCycleCountCheckBox")));
				}
			});
			if (!element.isSelected()) {
				element.click();
				System.out.println("Cycle Count is Activated");

			} else {
				System.out.println("Cycle Count is already Activated");
			}

			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("saveAccountPreferencesBtn")));
				}
			});
			element.click();
			Thread.sleep(2000);
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("applyNewsettingsBtn")));
				}
			});
			element.click();
			Thread.sleep(2000);
			element = wait.until(new Function<WebDriver, WebElement>() {

				// apply method- which accept webdriver as input
				// @Override
				public WebElement apply(WebDriver webDriverArg) {
					// find the element
					return webDriverArg.findElement(By.xpath(pro.getProperty("AccountPreferencesSavedOKBtn")));
				}
			});
			element.click();

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}

	}

}
