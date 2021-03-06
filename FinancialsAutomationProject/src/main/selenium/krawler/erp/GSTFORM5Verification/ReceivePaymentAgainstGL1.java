package krawler.erp.GSTFORM5Verification;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class ReceivePaymentAgainstGL1 {

	public static void create_ReceivePayment_AgainstGLMul(String Tax, String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentid = documentid;
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstGL.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentIcon"))));
			driver.findElement(By.xpath(pro.getProperty("ReceivePaymentIcon"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentAgainstGLCheck"))));
			driver.findElement(By.xpath(pro.getProperty("ReceivePaymentAgainstGLCheck"))).click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
			driver.findElement(By.xpath(pro.getProperty("SubmitButton1"))).click();
			Thread.sleep(1000);

			// sequence format document no.
			WebElement squenceRP = driver.findElement(By.xpath(pro.getProperty("SequenceFormat")));
			squenceRP.clear();
			squenceRP.sendKeys("NA");
			Thread.sleep(1000);
			squenceRP.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentNo"))));
			driver.findElement(By.xpath(pro.getProperty("DocumentNo"))).sendKeys(documentid);

			WebElement memo = driver.findElement(By.xpath(pro.getProperty("Memo")));
			memo.click();
			memo.sendKeys("Receive Payment Against GL");

			Thread.sleep(1000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Account")) {

					for (int j = 1; j <= 1; j++) {
						driver.findElement(By
								.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div["
										+ j + "]/table/tbody/tr/td[" + i + "]/div"))
								.click();
						Thread.sleep(2000);

						new WebDriverWait(driver, 30).until(ExpectedConditions
								.elementToBeClickable(By.xpath(pro.getProperty("QuickSearchGLAccount"))));
						driver.findElement(By.xpath(pro.getProperty("QuickSearchGLAccount"))).sendKeys("Sales");

						Thread.sleep(4000);// pro

						// enter vendor
						new WebDriverWait(driver, 30).until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath(pro.getProperty("SelectGLAccount1"))));
						WebElement selectedGL = driver.findElement(By.xpath(pro.getProperty("SelectGLAccount1")));
						selectedGL.click();

						new WebDriverWait(driver, 30).until(
								ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
						driver.findElement(By.xpath(pro.getProperty("SubmitButton1"))).click();
						Thread.sleep(2000);
					}
				}
			}

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Tax Code")) {
					for (int l = 1; l <= 1; l++) {

						WebElement taxdropdown = driver.findElement(By
								.xpath("//div[@id='receiptwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div[2]/div/div["
										+ l + "]/table/tbody/tr/td[" + i + "]/div"));
						taxdropdown.click();
						Thread.sleep(2000);
						Utilities.sendText(Tax);
						Thread.sleep(1000);

						driver.findElement(By
								.xpath("//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div/table/tbody/tr/td"))
								.click();
						Thread.sleep(1000);
					}
				}
			}

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='receiptwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='receiptwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Enter Amount")) {
					for (int l = 1; l <= 1; l++) {
						driver.findElement(By
								.xpath("//div[@id='receiptwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div[2]/div/div["
										+ l + "]/table/tbody/tr/td[" + i + "]/div"))
								.click();
						Thread.sleep(2000);
						Utilities.sendText("1000");
					}
				}
			}

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SaveButton"))));
			driver.findElement(By.xpath(pro.getProperty("SaveButton"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 1000, driver);
			Thread.sleep(1000);
			// Utilities.clickButton("Yes", 3000, driver);
			Thread.sleep(000);
			Utilities.clickButton("OK", 1000, driver);
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseMakePayment"))));
			driver.findElement(By.xpath(pro.getProperty("CloseMakePayment"))).click();
			System.out.println(
					"****Receive Payment against GL completed successfully for doc id : [" + documentid + "]***");

		} catch (Exception EX) {
			System.out.println("RP against GL NOT CREATED !!!!!!");
			Utilities.handleError(EX, driver);
		}
	}
}
