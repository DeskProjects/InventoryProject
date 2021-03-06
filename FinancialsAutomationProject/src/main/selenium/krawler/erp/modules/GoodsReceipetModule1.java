package krawler.erp.modules;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.EmailFunctionality;
import krawler.erp.page.Utilities;

public class GoodsReceipetModule1 {

	public static void create_GoodsReceipet(String documentid, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "GR" + documentid;
			Properties pro = Utilities.fetchProValue("OR_GoodReceipt.properties");

			Utilities.waitandClick(pro.getProperty("GoodReceiptIcon"), driver);
			selectFromNormalDropDown(vendorid, pro.getProperty("Vendor"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("SequenceFormat"), driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("DocumentId"), driver);

			Utilities.clickCheckBox(pro.getProperty("includingGST"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Yes", 200, driver);

			Utilities.HoverandClick(pro.getProperty("GRaddButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("Search"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexActQty = 0, indexRecQty = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Actual Quantity")) {
					indexActQty = i;
				}
				if (HeadeName.equalsIgnoreCase("Received Quantity")) {
					indexRecQty = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) { // add actual qty
				Utilities.HoverandClick(
						"//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[" + indexActQty + "]/div",
						driver);
				Utilities.enter_LinelabelAmount("10", driver);
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
				// add receive qty
				Utilities.HoverandClick(
						"//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[" + indexRecQty + "]/div",
						driver);
				Utilities.enter_LinelabelAmount("10", driver);
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			}

			// to scroll till end
			Utilities.HoverandClick(
					"//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[1]/table//div[@class='pwnd delete-gridrow']",
					driver);
			Utilities.clickButton("No", 50, driver);

			// enter line level Tax
			String taxNme[] = { "GST(BL)@7.00%", "GST(IM)@7.00%" };
			int sizeOfArr = taxNme.length;
			int indexTax = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (HeadeName.equalsIgnoreCase("Product Tax")) {
					indexTax = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div["
								+ i + "]/table/tbody/tr/td[" + indexTax + "]/div",
						driver);
				Thread.sleep(1000);

				if (i <= sizeOfArr) {
					selectFromNormalDropDown(taxNme[i - 1], "//input[@id='prtaxid']/following::input[1]", driver);
				} else {
					int index = getArrayIndex(i, sizeOfArr);
					selectFromNormalDropDown(taxNme[index - 1], "//input[@id='prtaxid']/following::input[1]", driver);
				}
			}

			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("No", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			String subjectLine = "Goods Receipt:- testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);

			Utilities.HoverandClick(pro.getProperty("CloseGoodReceipt"), driver);

			System.out.println("******* Goods Receipt : [" + documentid + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Goods Receipt is NOT Created for :[" + documentid + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// select item from NormalDrop Down
	public static void selectFromNormalDropDown(String text, String expForname, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		WebElement enterText = null;
		boolean success = false;
		for (int num_try = 1; !success && num_try < 10; num_try++) {
			try {
				enterText = driver.findElement(By.xpath(expForname));
				enterText.clear();
				enterText.sendKeys(text);
				Thread.sleep(1000);
				WebElement element = driver.findElement(
						By.xpath("//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]//*[text()='"
								+ text + "']"));
				element.click();
				Thread.sleep(1500);
				success = true;
			} catch (Exception EX) {
				if (num_try == 9) {
					Utilities.handleError(EX, driver);
				}
			}
		}
	}

	// to enter different tax
	public static int getArrayIndex(int c, int al) {
		if (c >= al) {
			c = c - al;
			getArrayIndex(c, al);
		} else {
			return c;
		}
		return c;
	}

	// ******************** Copy ******************************************
	public static void Copy_GoodReceipt(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			documentid = "GR" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_GoodReceipt.properties");

			Utilities.HoverandClick(pro.getProperty("GoodReceiptReport"), driver);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			/*
			 * //Report Email code String
			 * subjectLine="Goods Receipt testsmoke -:"+documentid;
			 * EmailFunctionality.email_fromReports(documentid,subjectLine,
			 * driver);
			 * Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"),
			 * "uncheck", driver); Utilities.enterTextInDropDown(documentid,
			 * pro.getProperty("QuickSearch"), driver); Thread.sleep(2000);
			 * Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"),
			 * "check", driver);
			 */
			Utilities.HoverandClick(pro.getProperty("copyGoodReceiptButton"), driver);
			Utilities.formLoaded(driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("copyGoodReceiptId"), driver);
			Utilities.HoverandClick(pro.getProperty("saveCopiedGoodReceiptId"), driver);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("No", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("CopyGoodReceiptClose"), driver);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Goods Receipt Copy Created !!");
			} else {
				System.out.println("Goods Receipt Copy NOT Created !!");
			}
		} catch (Exception EX) {
			System.out.println("Goods Receipt Copy NOT Created !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Edit ******************************************

	public static void Edit_GoodReceipt(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			documentid = "GR" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_GoodReceipt.properties");

			Utilities.HoverandClick(pro.getProperty("EditGoodReceiptButton"), driver);
			// to Load page properly
			Utilities.formLoaded(driver);
			Utilities.enterTextInDropDown("Performing Edit Operation", pro.getProperty("GoodReceiptMemo"), driver);

			String addThridProduct = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td[7]";
			Utilities.HoverandClick(addThridProduct, driver);

			selectFromNormalDropDown("EditProduct", "//*[@name='pid']", driver);
			Utilities.HoverandClick(pro.getProperty("GoodReceiptMemo"), driver);

			String actualQty = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td[16]";
			Utilities.clickAndEnterText("5", actualQty, driver);
			Utilities.HoverandClick(pro.getProperty("GoodReceiptMemo"), driver);

			String receQty = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[3]/table/tbody/tr/td[17]";
			Utilities.clickAndEnterText("5", receQty, driver);
			Utilities.HoverandClick(pro.getProperty("GoodReceiptMemo"), driver);

			String wlocation = "//*[contains(@id,'GoodsReceiptEdit')]/div/div/div/div/div[2]/div/div[3]/table/tbody/tr/td[19]/div/div";
			Utilities.HoverandClick(wlocation, driver);
			Utilities.HoverandClick(pro.getProperty("saveGoodReceiptWSLocationButton"), driver);
			Utilities.HoverandClick(pro.getProperty("GoodReceipteEditSave"), driver);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("No", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("EditGoodReceiptClose"), driver);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Goods Receipt Edited !!");
			} else {
				System.out.println("Goods Receipt Failed to Edit !!");
			}

		} catch (Exception EX) {
			System.out.println("Goods Receipt Failed to Edit !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Delete ******************************************

	public static void Delete_GoodReceipt(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			documentid = "GR" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_GoodReceipt.properties");

			Utilities.HoverandClick(pro.getProperty("deleteGoodReceiptButton"), driver);

			Utilities.HoverandClick(pro.getProperty("deleteGoodReceiptPermButton"), driver);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(1500);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			WebElement element = new WebDriverWait(driver, 45).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//a[contains(text(),'Get Started by adding a Goods Receipt now')]")));
			if (element.isDisplayed()) {
				System.out.println("Good Receipt Deleted !!!! ");
			} else {
				System.out.println("GoodReceipt is NOT Deleted !!!! ");
			}
			Utilities.HoverandClick(pro.getProperty("reportCloseBtn"), driver);
		} catch (Exception EX) {
			System.out.println("Good Receipt is NOT Deleted !!!! ");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************* Global tax **************************************
	public static void createGL_GoodsReceipet(String documentid, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "GrGL" + documentid;
			Properties pro = Utilities.fetchProValue("OR_GoodReceipt.properties");

			Utilities.waitandClick(pro.getProperty("GoodReceiptIcon"), driver);

			selectFromNormalDropDown(vendorid, pro.getProperty("Vendor"), driver);

			Utilities.enterTextInDropDown("NA", pro.getProperty("SequenceFormat"), driver);

			Utilities.enterTextInDropDown(documentid, pro.getProperty("DocumentId"), driver);

			Utilities.HoverandClick(pro.getProperty("GRaddButton"), driver);

			Utilities.clickCheckBox(pro.getProperty("Selectproduct"), "uncheck", driver);

			Utilities.enterTextNormalBox(productid, pro.getProperty("Search"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("SelectproductSS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);

			Utilities.HoverandClick(pro.getProperty("AddButton"), driver);
			Thread.sleep(1500);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexActQty = 0, indexRecQty = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Actual Quantity")) {
					indexActQty = i;
				}
				if (HeadeName.equalsIgnoreCase("Received Quantity")) {
					indexRecQty = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) { // add actual qty
				Utilities.HoverandClick(
						"//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[" + indexActQty + "]/div",
						driver);
				Utilities.sendText("10");
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
				// add receive qty
				Utilities.HoverandClick(
						"//div[@id='GoodsReceiptgoodsreceiptdeliverybillingproductdetailsgrid']/div/div/div/div/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[" + indexRecQty + "]/div",
						driver);
				Utilities.sendText("10");
				Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			}

			// to scroll till end
			Utilities.justHover("//*[contains(text(),'Outstanding Balance')]", driver);
			selectFromNormalDropDown("Yes", pro.getProperty("includeGlobalTax"), driver);
			selectFromNormalDropDown("GST(BL)@7.00%", pro.getProperty("GlobalTaxValue"), driver);

			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("No", 1000, driver); // No to QA flow
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("CloseGoodReceipt"), driver);

			System.out.println("******* Goods Receipt : [" + documentid + "] is Created !!!!!! ********");
		} catch (Exception EX) {
			System.out.println("******* Goods Receipt is NOT Created for :[" + documentid + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

}
