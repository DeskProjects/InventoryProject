package krawler.erp.page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PurchaseRequisition {

	public static void create_Purchase_Requisation(String documentID, String product_id, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentID = "PR" + documentID;

			Properties PurchaseRequisition = Utilities.fetchProValue("OR_PurchaseRequisition.properties");

			Thread.sleep(3000);
			// WebDriverWait wait = new WebDriverWait(driver, 30);

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(PurchaseRequisition.getProperty("CreatePurReqIcon"))));
			driver.findElement(By.xpath(PurchaseRequisition.getProperty("CreatePurReqIcon"))).click();
			System.out.println("clicked on customer requisation");
			Thread.sleep(4000);// pro

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(PurchaseRequisition.getProperty("sequenceFormat"))));
			WebElement na = driver.findElement(By.xpath(PurchaseRequisition.getProperty("sequenceFormat")));
			na.clear();
			na.sendKeys("NA");
			Thread.sleep(1000);// pro
			na.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(PurchaseRequisition.getProperty("PurchaseRequisitionNo"))));
			driver.findElement(By.xpath(PurchaseRequisition.getProperty("PurchaseRequisitionNo"))).sendKeys(documentID);
			Thread.sleep(3000);

			new WebDriverWait(driver, 90).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseRequisition.getProperty("addButton"))));
			driver.findElement(By.xpath(PurchaseRequisition.getProperty("addButton"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseRequisition.getProperty("ProductId"))));
			driver.findElement(By.xpath(PurchaseRequisition.getProperty("ProductId"))).sendKeys(product_id);
			Thread.sleep(4000);

			// select check box
			WebElement checkBoxElement = driver.findElement(By.xpath(PurchaseRequisition.getProperty("clickCheckBox")));
			checkBoxElement.click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseRequisition.getProperty("ButtonAdd"))));
			driver.findElement(By.xpath(PurchaseRequisition.getProperty("ButtonAdd"))).click();
			Thread.sleep(3000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='requisitioneditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td"))
					.size(); i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='requisitioneditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				if (driver.findElement(By
						.xpath("//div[@id='requisitioneditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Quantity")) {
					driver.findElement(By
							.xpath("//div[@id='requisitioneditproductdetailsgrid']/div/div/div/div/div[2]/div/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
				}
			}

			Thread.sleep(2000);// pro
			Utilities.sendText("5");
			Thread.sleep(2000);// pro

			/*
			 * //---------------------------------------------------------------
			 * -----------------------------------------------------------------
			 * -------------------------------------------------- //Click on
			 * select NA of customer ID new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(PurchaseRequisition.getProperty(
			 * "EnterQuantity")))); WebElement quantity =
			 * driver.findElement(By.xpath(PurchaseRequisition.getProperty(
			 * "EnterQuantity"))); quantity.click(); Thread.sleep(2000);//pro
			 * Utilities.sendText("5"); Thread.sleep(2000);//pro
			 * 
			 */
			driver.findElement(By.xpath(PurchaseRequisition.getProperty("Memo"))).click();
			// ------------------------------------------------------------------------------------------------------------------------------------------------

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseRequisition.getProperty("ButtonSave"))));
			driver.findElement(By.xpath(PurchaseRequisition.getProperty("ButtonSave"))).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseRequisition.getProperty("clickYes"))));
			driver.findElement(By.xpath(PurchaseRequisition.getProperty("clickYes"))).click();

			// Click on OK
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseRequisition.getProperty("ButtonOk"))));
			driver.findElement(By.xpath(PurchaseRequisition.getProperty("ButtonOk"))).click();
			Thread.sleep(1000);

			// Click on OK
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseRequisition.getProperty("clickOk"))));
			driver.findElement(By.xpath(PurchaseRequisition.getProperty("clickOk"))).click();

			System.out.println("Purchase Requisition " + documentID + " is Created Successfully");
		} catch (Exception EX) {
			System.out.println("Purchase Requisition " + documentID + " is NOT CREATED !!!!");
			Utilities.handleError(EX, driver);

		}
	}

	public static void verify_Purchase_Requisation(String documentid, String productid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "PR" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_PurchaseRequisition.properties");

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PurchaseRequisitionReport"))));
			driver.findElement(By.xpath(pro.getProperty("PurchaseRequisitionReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg57PurchaseRequisitionList']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);
			Thread.sleep(3000);

			for (int i = 1; i < e; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg57PurchaseRequisitionList']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg57PurchaseRequisitionList']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				// System.out.println(header);
				if (header.equals("Purchase Requisition No")) {
					String docno = driver.findElement(By
							.xpath(".//div[@id='gridmsg57PurchaseRequisitionList']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
					assertEquals(documentid, docno);
					break;
				}
			}
			String xpathOfelement = pro.getProperty("ClosePurchaseRequisitionReportTab");
			Utilities.HoverandClick(xpathOfelement, driver);
		}

		catch (Exception EX) {
			EX.printStackTrace();
			Utilities.handleError(EX, driver);
		}

	}

	public static void EmailCopyEditDelete_Purchase_Requisation(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "PR" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_PurchaseRequisition.properties");
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PurchaseRequisitionReport"))));
			driver.findElement(By.xpath(pro.getProperty("PurchaseRequisitionReport"))).click();
			Thread.sleep(5000);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			WebElement checkbox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentCheckBox"))));
			checkbox.click();
			Thread.sleep(2000);

			try {
				WebElement Emailbutton = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Email']")));
				if (Emailbutton.isEnabled()) {
					Thread.sleep(500);
					Emailbutton.click();
					Thread.sleep(1000);

					WebElement To = new WebDriverWait(driver, 30)
							.until(ExpectedConditions.visibilityOfElementLocated(By.name("othervendoremailsfh")));
					To.clear();
					Thread.sleep(1500);
					To.sendKeys("amol.gaikwad@deskera.com"); // rahul.kaushik@krawlernetworks.com");
					Thread.sleep(1500);

					WebElement subject = new WebDriverWait(driver, 30)
							.until(ExpectedConditions.visibilityOfElementLocated(By.name("subject")));
					subject.clear();
					Thread.sleep(500);
					subject.sendKeys("[Automation Test][Email from Report section] PR no :" + documentid);
					Thread.sleep(1000);

					Robot r = new Robot();

					r.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(1000);
					r.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(500);
					r.keyRelease(KeyEvent.VK_TAB);
					Thread.sleep(1000);

					r.keyPress(KeyEvent.VK_CONTROL);
					Thread.sleep(500);
					r.keyPress(KeyEvent.VK_A);
					Thread.sleep(500);
					r.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(500);
					r.keyRelease(KeyEvent.VK_A);
					Thread.sleep(500);

					Utilities.sendText("This email generated from [REPORT MODULE] during Automation Test on ["
							+ Utilities.currentDateTime() + "] for document No. - [" + documentid + "]"
							+ "\n\n -By\n  Amol Gaikwad");
					Thread.sleep(1000);

					Utilities.clickButton("Send", 500, driver);
					Thread.sleep(1000);

					System.out.println("Email has been successfully sent document [" + documentid + "] !!!");
				}
			} catch (Exception EX) {
				System.out.println("Email NOT sent for document [" + documentid + "] !!!");
				Utilities.handleError(EX, driver);
			}
			// end here

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			Thread.sleep(1000);
			search.clear();
			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement copyPurchaseRequistionButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyPurchaseRequistionButton"))));
			copyPurchaseRequistionButton.click();
			Thread.sleep(2000);

			String CopyPurchaseRequisitionID = "Copy" + documentid;

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyPurchaseRequistionId"))));
			driver.findElement(By.xpath(pro.getProperty("copyPurchaseRequistionId")))
					.sendKeys(CopyPurchaseRequisitionID);

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("saveCopiedPurchaseRequistionId"))));

			driver.findElement(By.xpath(pro.getProperty("saveCopiedPurchaseRequistionId"))).click();

			Thread.sleep(3000);
			Robot r = new Robot();
			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(1000);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("CopyPurchaseRequistionClose"))));
				driver.findElement(By.xpath(pro.getProperty("CopyPurchaseRequistionClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {

			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			Thread.sleep(1000);
			search.clear();
			search.sendKeys(CopyPurchaseRequisitionID);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement editPurchaseRequistionButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditPurchaseRequistionButton"))));
			editPurchaseRequistionButton.click();
			Thread.sleep(3000);

			WebElement PurchaseRequistionMemo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PurchaseRequistionMemo"))));
			PurchaseRequistionMemo.sendKeys("Update Memo");

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PurchaseRequistionEditSave"))));
			driver.findElement(By.xpath(pro.getProperty("PurchaseRequistionEditSave"))).click();
			Thread.sleep(3000);

			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(1000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Uncheck", driver);
			Thread.sleep(1000);
			search.clear();
			search.sendKeys(CopyPurchaseRequisitionID);
			search.sendKeys(Keys.TAB);
			Thread.sleep(3000);

			try {
				Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
				Thread.sleep(3000);// pro

				if (driver
						.findElement(By
								.xpath("//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
					System.out.println("Purchase Requisition " + CopyPurchaseRequisitionID + " is edited Successfully");
				} else {
					System.out.println(
							"Purchase Requisition " + CopyPurchaseRequisitionID + " is not edited Successfully");
				}
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);
			}

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deletePurchaseRequistionButton"))));
			driver.findElement(By.xpath(pro.getProperty("deletePurchaseRequistionButton"))).click();

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deletePurchaseRequistionPermButton"))));
			driver.findElement(By.xpath(pro.getProperty("deletePurchaseRequistionPermButton"))).click();
			Thread.sleep(3000);

			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);

			try {
				search.clear();
				search.sendKeys(CopyPurchaseRequisitionID);
				search.sendKeys(Keys.TAB);
				Thread.sleep(3000);
				;
				if (driver
						.findElement(
								By.xpath("//*[contains(text(),'Get Started by adding a Purchase Requisition now')]"))
						.isDisplayed()) {
					System.out
							.println("Purchase Requisition " + CopyPurchaseRequisitionID + "  is Deleted Successfully");
				} else {
					System.out.println(
							"Purchase Requisition " + CopyPurchaseRequisitionID + " is not Deleted Successfully");
				}
			} catch (Exception exp) {
				System.out.println("Purchase Requisition " + CopyPurchaseRequisitionID + " is Successfully deleted");
			}
			String xpathOfelement = pro.getProperty("ClosePurchaseRequisitionReportTab");
			Utilities.HoverandClick(xpathOfelement, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}
	}

	// * * * ** ** * * * * * EXPORT PR * * * * * * * * * *

	public static void Export_PR(WebDriver driver) throws InterruptedException, AWTException, IOException {
		String BtnName2 = "Export List";
		Properties pro = Utilities.fetchProValue("OR_PurchaseRequisition.properties");
		String reportIcon = pro.getProperty("PurchaseRequisitionReport");
		String waitForQuickSearch = pro.getProperty("DocumentCheckBox");
		String closeReportPage = pro.getProperty("ClosePurchaseRequisitionReportTab");
		String ModuleName = "Purchase Requisition";

		ExportFunction.Export_TodayDate(BtnName2, reportIcon, waitForQuickSearch, closeReportPage, ModuleName, driver);

	}

}
