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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.EmailFunctionality;
import krawler.erp.page.Utilities;

public class MakePaymentAgainstGL1 {

	public static void create_makePayment_GL(String Tax, String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = documentid;
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstGL.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("MakePaymentIcon"))));
			driver.findElement(By.xpath(pro.getProperty("MakePaymentIcon"))).click();
			Thread.sleep(5000);// pro

			new WebDriverWait(driver, 40)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AgainstGL"))));
			driver.findElement(By.xpath(pro.getProperty("AgainstGL"))).click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Submit']")));
			driver.findElement(By.xpath("//button[text()='Submit']")).click();
			Thread.sleep(1000);

			// Sequence format document no.
			WebElement squenceRP = driver.findElement(By.xpath(pro.getProperty("SeqFormat")));
			squenceRP.clear();
			squenceRP.sendKeys("NA");
			Thread.sleep(1000);// pro
			squenceRP.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("paymentNumber"))));
			driver.findElement(By.xpath(pro.getProperty("paymentNumber"))).sendKeys(documentid);

			Robot r = new Robot();

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Account"))

					for (int J = 1; J <= 1; J++) {
						{
							driver.findElement(By
									.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div[2]/div/div["
											+ J + "]/table/tbody/tr/td[" + i + "]/div"))
									.click();
							Thread.sleep(2000);
							new WebDriverWait(driver, 30).until(ExpectedConditions
									.elementToBeClickable(By.xpath(pro.getProperty("accQuicksearch"))));
							driver.findElement(By.xpath(pro.getProperty("accQuicksearch"))).sendKeys("Deposits Paid");
							Thread.sleep(5000);

							WebElement selectAc = new WebDriverWait(driver, 30).until(
									ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("accSelect"))));
							if (selectAc.isEnabled()) {
								selectAc.click();
							}

							new WebDriverWait(driver, 30).until(
									ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Submitbtn"))));
							driver.findElement(By.xpath(pro.getProperty("Submitbtn"))).click();
						}
					}
			}
			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Tax Code")) {
					for (int l = 1; l <= 1; l++) {

						WebElement taxdropdown = driver.findElement(By
								.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div[2]/div/div["
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
							.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Enter Amount")) {
					for (int l = 1; l <= 1; l++) {
						driver.findElement(By
								.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div[2]/div/div["
										+ l + "]/table/tbody/tr/td[" + i + "]/div"))
								.click();
						Thread.sleep(2000);
						Utilities.sendText("100");
					}
				}
			}

			Thread.sleep(1000);
			WebElement savebtn = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("savebtn"))));
			if (savebtn.isEnabled()) {
				savebtn.click();
			}

			Utilities.clickButton("Yes", 1500, driver);
			try {
				driver.findElement(By.xpath("//button[text()='Yes']")).click();
			} catch (Exception e) {

			}

			Utilities.clickButton("OK", 1500, driver);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseTabBtn")))).click();

			System.out.println("Make Payment against GL [" + documentid + "] created Successfully");

		} catch (Exception EX) {
			System.out.println("Make Payment against GL [" + documentid + "] is FAILED !!!!!!");
			Utilities.handleError(EX, driver);
		}
	}
}
