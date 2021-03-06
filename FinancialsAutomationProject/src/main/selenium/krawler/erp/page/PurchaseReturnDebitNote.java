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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PurchaseReturnDebitNote {

	// ********************************************************verification****************************************************************

	public static void verify_purchaseReturnWithDebitNote(String documentid, String productid, String Vendor,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_PurchaseReturnDebitNote.properties");
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PurchaseReturnOnlyReport"))));
			driver.findElement(By.xpath(pro.getProperty("PurchaseReturnOnlyReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys("PRWD" + documentid);
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
					assertEquals("PRWD" + documentid,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg54PurchaseReturnListEntry']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("1");
				}

				else if (header.equals("Vendor")) {
					assertEquals(Vendor + "Name",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg54PurchaseReturnListEntry']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("2");
				}

				else if (header.equals("Debit Note No")) {
					assertEquals("DNoPR" + documentid,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg54PurchaseReturnListEntry']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("3");
				}

			}

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ClosePurchaseReturnOnlyTab"))));
			driver.findElement(By.xpath(pro.getProperty("ClosePurchaseReturnOnlyTab"))).click();

			System.out.println("PurchaseReturn with DebitNote [" + documentid + "] Successfully verified");

		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	public static void EmailCopyEditDelete_purchaseReturnWithDebitNote(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "PRdnPI" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_PurchaseReturnDebitNote.properties");
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PurchaseReturnOnlyReport"))));
			driver.findElement(By.xpath(pro.getProperty("PurchaseReturnOnlyReport"))).click();
			Thread.sleep(5000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			// Report Email code
			String subjectLine = "Purchase Return (Purchase Return with Debit Note) - testsmoke - [PRWD" + documentid
					+ "]";
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			search.clear();
			Thread.sleep(1000);
			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			WebElement copyPurchaseReturnWDNButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyPurchaseReturnWDNButton"))));
			copyPurchaseReturnWDNButton.click();
			Thread.sleep(2000);

			String CopyPurchaseReturnWDNID = "Copy" + documentid;

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyPurchaseReturnWDNId"))));
			driver.findElement(By.xpath(pro.getProperty("copyPurchaseReturnWDNId"))).sendKeys(CopyPurchaseReturnWDNID);
			Thread.sleep(1000);

			WebElement PurchaseReturnWDNMemo = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//textarea[contains(@id,'memo11PurchaseReturnCopy')]")));
			PurchaseReturnWDNMemo.sendKeys("Update Purchase Return With DN Memo");

			// adding code to bypass above issue >>>>>>>>>>>>>>> ERP-40686
			// <<<<<<<<<<<<<<<<<<
			WebElement location = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//div[contains(@id,'billingproductdetailsgrid')]//div[@class='x-grid3-scroller']/div/div[1]/table/tbody/tr/td[15]/div/div")));
			location.click();

			Utilities.clickButton("Submit", 1500, driver);
			Thread.sleep(1000);

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("saveCopiedPurchaseReturnWDNId"))));
			driver.findElement(By.xpath(pro.getProperty("saveCopiedPurchaseReturnWDNId"))).click();

			Thread.sleep(2000);

			Utilities.clickButton("Yes", 200, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 200, driver);
			Thread.sleep(1000);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("CopyPurchaseReturnWDNClose"))));
				driver.findElement(By.xpath(pro.getProperty("CopyPurchaseReturnWDNClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				// r.keyPress(KeyEvent.VK_F5);
				// r.keyRelease(KeyEvent.VK_F5);

			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			search.clear();
			Thread.sleep(1000);
			search.sendKeys(CopyPurchaseReturnWDNID);
			Thread.sleep(1000);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			WebElement editPurchaseReturnWDNButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditPurchaseReturnWDNButton"))));
			editPurchaseReturnWDNButton.click();
			Thread.sleep(3000);

			PurchaseReturnWDNMemo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PurchaseReturnWDNMemo"))));
			PurchaseReturnWDNMemo.sendKeys("Update Purchase Return With DN Memo");

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PurchaseReturnWDNEditSave"))));
			driver.findElement(By.xpath(pro.getProperty("PurchaseReturnWDNEditSave"))).click();

			Thread.sleep(2000);
			Utilities.clickButton("Yes", 200, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 200, driver);
			Thread.sleep(1000);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("EditPurchaseReturnWDNClose"))));
				driver.findElement(By.xpath(pro.getProperty("EditPurchaseReturnWDNClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				// r.keyPress(KeyEvent.VK_F5);
				// r.keyRelease(KeyEvent.VK_F5);

			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Thread.sleep(1000);
			search.clear();
			search.sendKeys(CopyPurchaseReturnWDNID);
			Thread.sleep(2000);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			try {
				Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

				Thread.sleep(3000);
				if (driver
						.findElement(By
								.xpath("//div[@id='PurchaseReturnListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
					System.out.println(
							"Purchase Return With Debit Note " + CopyPurchaseReturnWDNID + " is edited Successfully");
				} else {
					System.out.println("Purchase Return With Debit Note " + CopyPurchaseReturnWDNID
							+ " is not edited Successfully");
				}

			} catch (Exception EX) {
				// r.keyPress(KeyEvent.VK_F5);
				// r.keyRelease(KeyEvent.VK_F5);
			}

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deletePurchaseReturnWDNButton"))));
			driver.findElement(By.xpath(pro.getProperty("deletePurchaseReturnWDNButton"))).click();

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deletePurchaseReturnWDNPermButton"))));
			driver.findElement(By.xpath(pro.getProperty("deletePurchaseReturnWDNPermButton"))).click();

			Thread.sleep(2000);
			Utilities.clickButton("Yes", 200, driver);

			Utilities.clickButton("OK", 0, driver);

			Thread.sleep(2000);

			try {
				Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
				Thread.sleep(1000);
				search.clear();
				search.sendKeys(CopyPurchaseReturnWDNID);
				Thread.sleep(2000);

				if (driver
						.findElement(By
								.xpath("//div[@id='PurchaseReturnListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("No results to display")) {
					System.out.println(
							"Purchase Return With Debit Note " + CopyPurchaseReturnWDNID + "  is Deleted Successfully");
				} else {
					System.out.println("Purchase Return With Debit Note " + CopyPurchaseReturnWDNID
							+ "  is not Deleted Successfully");
				}
			}

			catch (Exception exp) {
				exp.printStackTrace();
			}
			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ClosePurchaseReturnOnlyTab"))));
			driver.findElement(By.xpath(pro.getProperty("ClosePurchaseReturnOnlyTab"))).click();
		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}
	}

	// ********************* for PI
	// ****************************************************************

	public static void verify_purchaseReturnWithDebitNote_linkPI(String documentid, String productid, String Vendor,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_PurchaseReturnDebitNote.properties");
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PurchaseReturnOnlyReport"))));
			driver.findElement(By.xpath(pro.getProperty("PurchaseReturnOnlyReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys("PRdnPI" + documentid);
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
					assertEquals("PRdnPI" + documentid,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg54PurchaseReturnListEntry']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("1");
				}

				else if (header.equals("Vendor")) {
					assertEquals(Vendor + "Name",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg54PurchaseReturnListEntry']/div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					// System.out.println("2");
				}
			}

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ClosePurchaseReturnOnlyTab"))));
			driver.findElement(By.xpath(pro.getProperty("ClosePurchaseReturnOnlyTab"))).click();

			System.out.println("PurchaseReturn with DebitNote with PI Successfully verified !!!1");

		} catch (Exception EX) {
			System.out.println("PurchaseReturn with DebitNote with PI Successfully NOT verified !!!1");

			Utilities.handleError(EX, driver);
		}

	}
}
