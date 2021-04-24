package krawler.erp.page;

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

public class PurchaseReturnOnly {

	public static void create_purchaseReturn(String documentid, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{
		try {
			documentid = "PRN" + documentid;

			Properties PurchaseReturnOnly = Utilities.fetchProValue("OR_PurchaseReturnOnly.properties");

			Thread.sleep(3000);
			// WebDriverWait wait = new WebDriverWait(driver, 30);

			// clicked on Document
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(PurchaseReturnOnly.getProperty("PurchaseReturnOnlyIcon"))));
			driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("PurchaseReturnOnlyIcon"))).click();
			Thread.sleep(1000);// pro

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseReturnOnly.getProperty("buttonSubmit"))));
			driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("buttonSubmit"))).click();
			Thread.sleep(8000);

			// enter vendor name
			WebElement vendor = driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("VendorId")));
			vendor.clear();
			vendor.sendKeys(vendorid);
			Thread.sleep(3000);// pro
			vendor.sendKeys(Keys.ENTER);

			// sequen format document no.
			WebElement squence = driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("sequenceFormat")));
			squence.clear();
			squence.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(PurchaseReturnOnly.getProperty("PurchaseReturnOnlyNo"))));
			driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("PurchaseReturnOnlyNo"))).sendKeys(documentid);
			Thread.sleep(2000);// pro

			// click on add button
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseReturnOnly.getProperty("addButton"))));
			driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("addButton"))).click();

			// search product id
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(PurchaseReturnOnly.getProperty("SearchProductId"))));
			driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("SearchProductId"))).sendKeys(productid);
			Thread.sleep(3000);// pro

			// select check box
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseReturnOnly.getProperty("ClickCheckBox"))));
			driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("ClickCheckBox"))).click();
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseReturnOnly.getProperty("buttonAdd"))));
			driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("buttonAdd"))).click();
			Thread.sleep(3000);

			// Click on select NA of customer ID
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(PurchaseReturnOnly.getProperty("ActualQuantity"))));
			WebElement quantity = driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("ActualQuantity")));
			quantity.click();
			Thread.sleep(1000);// pro

			Thread.sleep(2000);
			Utilities.sendText("5");
			Thread.sleep(2000);

			driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("memo"))).click();

			// Click on select NA of customer ID
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(PurchaseReturnOnly.getProperty("ReturnQuantity"))));
			WebElement quantity1 = driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("ReturnQuantity")));
			quantity1.click();
			Thread.sleep(1000);// pro

			Thread.sleep(2000);
			Utilities.sendText("5");
			Thread.sleep(2000);

			driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("memo"))).click();
			/*
			 * //Click on select NA of customer ID new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(PurchaseReturnOnly.getProperty(
			 * "BatchSelect")))); WebElement quantity2 =
			 * driver.findElement(By.xpath(PurchaseReturnOnly.getProperty(
			 * "BatchSelect"))); quantity2.click(); Thread.sleep(2000);//pro
			 * 
			 * new WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(PurchaseReturnOnly.getProperty(
			 * "buttonSubmit"))));
			 * driver.findElement(By.xpath(PurchaseReturnOnly.getProperty(
			 * "buttonSubmit"))).click(); Thread.sleep(2000);
			 */
			// ------------------------------------------------------------------------------------------------------------------------------------------------

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseReturnOnly.getProperty("buttonSave"))));
			driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("buttonSave"))).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseReturnOnly.getProperty("ButtonYes"))));
			driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("ButtonYes"))).click();

			// new
			// WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
			// driver.findElement(By.xpath("//button[text()='No']")).click();

			// Click on OK
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseReturnOnly.getProperty("clickOk"))));
			driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("clickOk"))).click();
			Thread.sleep(1000);

			// Email code
			String subjectLine = "Purchase Return (Return Only) - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);
			Thread.sleep(1000);

			// Click on Close
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(PurchaseReturnOnly.getProperty("ClosePurchaseReturnOnly"))));
			driver.findElement(By.xpath(PurchaseReturnOnly.getProperty("ClosePurchaseReturnOnly"))).click();

			System.out.println("Purchase Return (Return Only) - testsmoke - " + documentid + " Created !!!!!");

		} catch (Exception EX)

		{
			System.out.println("Purchase Return (Return Only) - testsmoke - " + documentid + " NOT Created !!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ********************************************************verification****************************************************************

	public static void verify_purchaseReturn(String documentid, String productid, String Vendor, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "PRN" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_PurchaseReturnOnly.properties");
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PurchaseReturnOnlyReport"))));
			driver.findElement(By.xpath(pro.getProperty("PurchaseReturnOnlyReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg54PurchaseReturnListEntry']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg54PurchaseReturnListEntry']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg54PurchaseReturnListEntry']/div/div[2]/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Purchase Return No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg54PurchaseReturnListEntry']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (header.equals("Vendor")) {
					assertEquals(Vendor + "Name",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg54PurchaseReturnListEntry']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

			}

			Thread.sleep(2000);
			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.elementToBeClickable(By.xpath(pro.getProperty("ClosePurchaseReturnOnlyTab"))));
				driver.findElement(By.xpath(pro.getProperty("ClosePurchaseReturnOnlyTab"))).click();

				System.out.println("Purchase ReturnOnly [" + documentid + "] Successfully verified");
			} catch (Exception EX) {
				Utilities.refresh();
			}

		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	// * * * * * * * EXPORT Goods Receipt * * * * * *

	public static void Export_PurchaseReturn(WebDriver driver) throws InterruptedException, AWTException, IOException {
		String BtnName2 = "Export List";
		Properties pro = Utilities.fetchProValue("OR_PurchaseReturnOnly.properties");
		String reportIcon = pro.getProperty("PurchaseReturnOnlyReport");
		String waitForQuickSearch = pro.getProperty("DocumentCheckBox");
		String closeReportPage = pro.getProperty("ClosePurchaseReturnOnlyTab");
		String ModuleName = "Purchase Return";

		ExportFunction.Export_TodayDate(BtnName2, reportIcon, waitForQuickSearch, closeReportPage, ModuleName, driver);

	}
}