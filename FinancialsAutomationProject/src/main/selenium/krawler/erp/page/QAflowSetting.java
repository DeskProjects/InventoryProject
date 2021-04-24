package krawler.erp.page;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class QAflowSetting {

	public void set_QA_flow(String OnorOff, WebDriver driver) throws InterruptedException, AWTException, IOException {
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

			if (OnorOff.equalsIgnoreCase("Off")) {

				element = wait.until(new Function<WebDriver, WebElement>() {

					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg.findElement(By.xpath(pro.getProperty("ActivateQAApprovalFlowCheckBox")));
					}
				});
				if (element.isSelected()) {
					element.click();
					System.out.println("QA Inspection Flow is Deactivated");
				}

				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg
								.findElement(By.xpath(pro.getProperty("BuildAssemblyQAApprovalFlowCheckBox")));
					}
				});
				if (element.isSelected()) {
					element.click();
					System.out.println("QA Inspection for Build Assembly Items is Deactivated");

				}

				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg.findElement(By.xpath(pro.getProperty("StockINApprovalQAFlowCheckBox")));
					}
				});
				if (element.isSelected()) {
					element.click();
					System.out.println("Stock IN Approval Flow is Deactivated");
				}

				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg
								.findElement(By.xpath(pro.getProperty("InterStoreStockReturnQAApprovalFlowCheckBox")));
					}
				});
				if (element.isSelected()) {
					element.click();
					System.out.println("Inter Store Stock Return Approval Flow is Deactivated");
				}

				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg
								.findElement(By.xpath(pro.getProperty("StockRequestReturnQAApprovalFlowCheckBox")));
					}
				});
				if (element.isSelected()) {
					element.click();
					System.out.println("Stock Request Return Approval Flow is Deactivated");

				}

				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg
								.findElement(By.xpath(pro.getProperty("GoodsReceiptNoteQAApprovalFlowCheckBox")));
					}
				});
				if (element.isSelected()) {
					element.click();
					System.out.println("QA Approval Flow in Goods Receipt Note is Deactivated");

				}

				element = wait.until(new Function<WebDriver, WebElement>() {

					// apply method- which accept webdriver as input
					// @Override
					public WebElement apply(WebDriver webDriverArg) {
						// find the element
						return webDriverArg
								.findElement(By.xpath(pro.getProperty("DeliveryOrderQAApprovalFlowCheckBox")));
					}
				});
				if (element.isSelected()) {
					element.click();
					System.out.println("QA Approval Flow in Delivery Order is Deactivated");

				}

			} // off if

		} catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}

	}// m
}// c