package krawler.erp.modules;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import krawler.erp.page.EmailFunctionality;
import krawler.erp.page.Utilities;

public class PurchaseRequisition1 {

	public static void create_PurchaseRequisition(String documentId, String productid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentId = "PR" + documentId;
			Properties pro = Utilities.fetchProValue("OR_PurchaseRequisition.properties");
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";
			// clicked on main icon

			Utilities.waitandClick(pro.getProperty("CreatePurReqIcon"), driver);
			Utilities.isElementGone(xpathOfLoading, 45, driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.enterTextInDropDown(documentId, pro.getProperty("PurchaseRequisitionNo"), driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.isLoadingDisappear(45, driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("ProductId"), driver);
			Utilities.isLoadingDisappear(45, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexQty = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='requisitioneditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Quantity")) {
					indexQty = i;
				}
			}
			// Scroll line label
			Utilities.justHover("//*[@class='pwnd delete-gridrow']", driver);

			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='requisitioneditproductdetailsgrid']//div[@class='x-grid3-scroller']/div/div[" + i
								+ "]/table/tbody/tr/td[" + indexQty + "]/div",
						driver);
				Utilities.enter_LinelabelAmount("50", driver);
				Thread.sleep(1000);
			}

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("clickOk"), driver);

			System.out.println("******* Purchase Requisition: [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out
					.println("******* PurchaseRequisition is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}

	}

	// ********************
	// Copy-Edit-Delt******************************************
	public static void PurchaseRequisition_EmailCopyEditDelete(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {
		try {
			documentid = "PR" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_PurchaseRequisition.properties");
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";

			Utilities.HoverandClick(pro.getProperty("PurchaseRequisitionReport"), driver);
			Utilities.isElementGone(xpathOfLoading, 600, driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Utilities.isLoadingDisappear(120, driver);
			String isFormloaded = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr//*[@class='x-grid3-row-checker']";

			// Email
			Utilities.click_element(pro.getProperty("DocumentCheckBox"), driver);
			try {
				Utilities.click_element("//button[text()='Email']", driver);
				// Enter Email address
				Utilities.click_element("//*[@name='othervendoremailsfh']", driver);
				Utilities.enterTextNormalBox("amol.gaikwad@deskera.org", "//*[@name='othervendoremailsfh']", driver);
				// Enter subject
				Utilities.click_element("//*[@name='subject']", driver);
				Utilities.enterTextNormalBox("[PR][CopyEditDelete]Automation - " + Utilities.currentDate("dd/mm/yyyy"),
						"//*[@name='subject']", driver);

				WebElement frame1 = driver.findElement(By.xpath("//*[@name='message']/following::iframe"));
				driver.switchTo().frame(frame1);
				WebElement editable = driver.switchTo().activeElement();
				editable.clear();
				editable.sendKeys("Hello Automation test for PR only");
				driver.switchTo().defaultContent();

				Utilities.click_element("//*[@name='emailcopy']", driver);
				Utilities.clickButton("Send", 1000, driver);
				System.out.println("Email has been successfully sent documnet [" + documentid + "] !!!");
			} catch (Exception EX) {
				System.out.println("Email NOT sent for documnet [" + documentid + "] !!!");
				Utilities.handleError(EX, driver);
			}
			Utilities.clickButton("OK", 0, driver);
			// Copy
			Utilities.HoverandClick(pro.getProperty("copyPurchaseRequistionButton"), driver);
			Utilities.clickCheckBox(isFormloaded, "uncheck", driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("copyPurchaseRequistionId"), driver);
			Utilities.HoverandClick(pro.getProperty("saveCopiedPurchaseRequistionId"), driver);
			Utilities.click_element("//button[text()='Yes']", driver);
			Utilities.click_element("//button[text()='OK']", driver);
			Utilities.click_element(pro.getProperty("CopyPurchaseRequistionClose"), driver);
			// Verify
			Utilities.click_element(pro.getProperty("QuickSearch"), driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Utilities.isElementGone(xpathOfLoading, 600, driver);
			Utilities.click_element(pro.getProperty("DocumentCheckBox"), driver);
			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Purchase Requisition Copy Created !!");
			} else {
				System.out.println("Purchase Requisition Copy NOT Created !!");
			}

			// Edit
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			Utilities.click_element(pro.getProperty("EditPurchaseRequistionButton"), driver);
			Utilities.clickCheckBox(isFormloaded, "uncheck", driver);
			Utilities.click_element(pro.getProperty("PurchaseRequistionMemo"), driver);
			Utilities.enterTextNormalBox("Performing Edit Operation", pro.getProperty("PurchaseRequistionMemo"),
					driver);
			for (int i = 1; i <= 2; i++) {
				Utilities.click_element(
						"//div[" + i + "]/table//td[contains(@class,'x-grid3-col x-grid3-cell x-grid3-td-quantity')]",
						driver);
				Utilities.enterTextNormalBox("55",
						"//*[contains(@class,'x-grid-editor') and contains(@style,'visible')]/input[1]", driver);
			}
			Utilities.HoverandClick(pro.getProperty("PurchaseRequistionEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			// Verify
			Utilities.click_element(pro.getProperty("QuickSearch"), driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Utilities.isElementGone(xpathOfLoading, 600, driver);
			Utilities.click_element(pro.getProperty("DocumentCheckBox"), driver);
			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Purchase Requisition Edited !!");
			} else {
				System.out.println("Purchase Requisition Failed to Edit !!");
			}

			// Delete
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			Utilities.click_element(pro.getProperty("deletePurchaseRequistionButton"), driver);
			Utilities.click_element(pro.getProperty("deletePurchaseRequistionPermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			// Verify
			Utilities.click_element(pro.getProperty("QuickSearch"), driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Utilities.isElementGone(xpathOfLoading, 600, driver);
			WebElement element = new WebDriverWait(driver, 45).until(ExpectedConditions.elementToBeClickable(
					By.xpath("//a[contains(text(),'Get Started by adding a Purchase Requisition now')]")));
			if (element.isDisplayed()) {
				System.out.println("Purchase Requisition Deleted !!!! ");
				System.out.println("Purchase Requisition [" + documentid + "] Email-Copy-Edit-Delete flow PASS !!");
				Utilities.click_element(pro.getProperty("ClosePurchaseRequisitionReportTab"), driver);
			} else {
				System.out.println("Purchase Requisition is NOT Deleted !!!! ");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
		} catch (Exception EX) {
			System.out.println("Purchase Requisition [" + documentid + "] Email-Copy-Edit-Delete flow FAILED !!");
			Utilities.handleError(EX, driver);
		}
	}

}
