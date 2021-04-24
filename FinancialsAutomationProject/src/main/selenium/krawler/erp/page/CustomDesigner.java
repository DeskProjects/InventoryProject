package krawler.erp.page;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomDesigner {

	public static void create_CustomDesigner(String ModuleName, String fileNamewithExt, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties salesReturnProperties = Utilities.fetchProValue("OR_CustomDesigner.properties");
			WebDriverWait wait = new WebDriverWait(driver, 40);
			String filPath = null;
			String[] fileNam = fileNamewithExt.split(",");

			WebElement element = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("AdminButton"))));
			element.click();
			Thread.sleep(1000);

			element = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("CustomDesigner"))));
			element.click();
			Thread.sleep(1000);

			WebElement selectModule = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//span[text()='" + ModuleName + "']/ancestor::td/preceding::td[1]/div/div")));
			selectModule.click();
			Thread.sleep(1500);
			String parentWindow = driver.getWindowHandle(); // parent window

			for (int k = 0; k < fileNam.length; k++) {
				Utilities.clickButton("Import", 1500, driver);
				filPath = Utilities.importFile(fileNam[k]);

				element = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(salesReturnProperties.getProperty("FileXPath"))));
				element.sendKeys(filPath);
				Thread.sleep(1000);

				Utilities.clickButton("Upload", 500, driver);
				Utilities.clickButton("OK", 500, driver);
			}

			List<WebElement> totalUploadedFil = driver.findElements(By.xpath(
					"//div[text()='Template Name']/ancestor::div[3]/following::div[1]/div/div/table/tbody/tr/td[3]"));
			int totalSize = totalUploadedFil.size();

			for (int i = 1; i <= totalSize; i++) {
				String uploadedFilename = driver.findElement(
						By.xpath("*//div[text()='Template Name']/ancestor::div[3]/following::div[1]/div/div[" + i
								+ "]/table/tbody/tr/td[3]"))
						.getText();
				System.out.println(uploadedFilename);

				WebElement item = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//div[text()='Template Name']/ancestor::div[3]/following::div[1]/div/div[" + i
								+ "]/table/tbody/tr/td[3]/div/a")));
				item.click();
				Thread.sleep(1000);

				Set s = driver.getWindowHandles(); // this method will gives you
													// the handles of all opened
													// windows
				Iterator ite = s.iterator();

				while (ite.hasNext()) {
					String childWindow = ite.next().toString();
					if (!childWindow.contains(parentWindow)) {
						driver.switchTo().window(childWindow);

						try {
							WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
									By.xpath(salesReturnProperties.getProperty("templateSaveBtn"))));
							Thread.sleep(1000);
							saveBtn.click();
							Thread.sleep(2500);
						} catch (Exception EX) {
							// default template
						}

						driver.close(); // close template
						driver.switchTo().window(parentWindow);
						Thread.sleep(2000);
					}
				}
				System.out.println("* * * * * * File Uploaded sucessfully [" + uploadedFilename + "] * * * * * *");
			}
			Utilities.refresh();

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// * * * * * * * * * * * * * PRINT using Default Template * * * * * * * *

	public static void Print_CustomDesigner(String documentid, String DesignerName, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			Properties pro = Utilities.fetchProValue("OR_CustomDesigner.properties");
			WebDriverWait wait = new WebDriverWait(driver, 40);
			String[] fileNam = DesignerName.split(",");

			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CashSaleReport"))));
			element.click();
			Thread.sleep(1000);

			WebElement docCheckbox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("documentCheckBox"))));

			if (docCheckbox.isDisplayed()) {
				Thread.sleep(1500);
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("quickSearch"))));
				element.clear();
				element.sendKeys(documentid);
				element.sendKeys(Keys.TAB);
				Thread.sleep(2000);

				docCheckbox = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("documentCheckBox"))));
				docCheckbox.click();

				String parentWindow = driver.getWindowHandle();

				for (int i = 0; i < fileNam.length; i++) {

					Utilities.clickButton("Print Record(s)", 1000, driver);

					Actions action = new Actions(driver);
					WebElement templName = driver.findElement(By.xpath("*//a[text()='" + fileNam[i] + "']"));
					action.moveToElement(templName).build().perform();
					Thread.sleep(1000);
					templName.click();

					Set s = driver.getWindowHandles(); // this method will gives
														// you the handles of
														// all opened windows
					Iterator ite = s.iterator();

					while (ite.hasNext()) {
						String childWindow = ite.next().toString();
						if (!childWindow.contains(parentWindow)) {
							driver.switchTo().window(childWindow);
							Thread.sleep(3000);

							WebElement customerName = wait.until(ExpectedConditions
									.elementToBeClickable(By.xpath("*//div[contains(text(),'" + customerid + "')]")));
							if (customerName.isDisplayed()) {
								System.out.println("* * * For template [" + fileNam[i] + "] Customer Name is ["
										+ customerName.getText() + "] for Invoice number [" + documentid + "]* * * *");

							}
							driver.close(); // close template
							driver.switchTo().window(parentWindow);
						}
					}
				}
			}

			System.out.println("Printed");
			Utilities.refresh();

		} catch (Exception EX) {
			System.out.println("* * * * * *Custom Designer failed to Print template * * * * * * ");
			Utilities.handleError(EX, driver);
		}
	}

}
