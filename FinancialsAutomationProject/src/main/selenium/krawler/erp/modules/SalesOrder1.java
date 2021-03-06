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

public class SalesOrder1 {

	public static void create_SalesOrder(String documentId, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentId = "SO" + documentId;
			Properties pro = Utilities.fetchProValue("OR_SalesOrder.properties");

			Utilities.waitandClick(pro.getProperty("CreateSalesOrderIcon"), driver);
			selectFromNormalDropDown(customerid, pro.getProperty("customerName"), driver);
			selectFromNormalDropDown("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.enterTextNormalBox(documentId, pro.getProperty("SalesOrderNo"), driver);

			Utilities.clickCheckBox(pro.getProperty("includingGST"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Yes", 500, driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("productId"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexQty = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='salesordereditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (HeadeName.equalsIgnoreCase("Quantity")) {
					indexQty = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='salesordereditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[" + i
								+ "]/table/tbody/tr/td[" + indexQty + "]/div",
						driver);
				Utilities.enter_LinelabelAmount("50", driver);
				Thread.sleep(500);
			}

			// to scroll till end
			Utilities.justHover(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[contains(@class,'x-grid3-cell-last')]",
					driver);

			// enter line level Tax
			String taxNme[] = { "GST(DS)@7.00%", "GST(SR)@7.00%" };
			int sizeOfArr = taxNme.length;
			int indexTax = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='salesordereditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (HeadeName.equalsIgnoreCase("Product Tax")) {
					indexTax = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='salesordereditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[" + i
								+ "]/table/tbody/tr/td[" + indexTax + "]/div",
						driver);
				Thread.sleep(1000);

				if (i <= sizeOfArr) {
					Utilities.enterTextInDropDown(taxNme[i - 1], "//input[@id='prtaxid']/following::input[1]", driver);
				} else {
					int index = getArrayIndex(i, sizeOfArr);
					Utilities.enterTextInDropDown(taxNme[index - 1], "//input[@id='prtaxid']/following::input[1]",
							driver);
				}
			}

			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			String subjectLine = "Sales Order :- testsmoke - " + documentId;
			EmailFunctionality.email_AfterSave(documentId, subjectLine, driver);

			Utilities.HoverandClick(pro.getProperty("clickOk"), driver);

			System.out.println("******* Sales Order: [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Sales Order is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Copy ******************************************

	public static void Copy_SalesOrder(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			documentid = "SO" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_SalesOrder.properties");

			Utilities.waitandClick(pro.getProperty("SalesOrderReport"), driver);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			/*
			 * //Report Email code String
			 * subjectLine="Sales Order - testsmoke - "+documentid;
			 * EmailFunctionality.email_fromReports(documentid,subjectLine,
			 * driver);
			 * Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"),
			 * "uncheck", driver); Utilities.enterTextInDropDown(documentid,
			 * pro.getProperty("QuickSearch"), driver); Thread.sleep(2000);
			 * Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"),
			 * "check", driver);
			 */
			Utilities.HoverandClick(pro.getProperty("copySalesOrderButton"), driver);

			Utilities.formLoaded(driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("copySalesOrderId"), driver);
			Utilities.HoverandClick(pro.getProperty("saveCopiedSalesOrderId"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("CopySalesOrderClose"), driver);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(1500);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Sales Order Copy Created !!");
			} else {
				System.out.println("Sales Order Copy NOT Created !!");
			}
		} catch (Exception EX) {
			System.out.println("Sales Order Copy NOT Created !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Edit ******************************************

	public static void Edit_SalesOrder(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			documentid = "SO" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_SalesOrder.properties");
			Utilities.HoverandClick(pro.getProperty("EditSalesOrderButton"), driver);

			Utilities.formLoaded(driver);
			Utilities.enterTextInDropDown("Performing Edit Operation", pro.getProperty("SalesOrderMemo"), driver);
			Utilities.clickAndEnterText("60",
					"//div[1]/table//td[contains(@class,'x-grid3-col x-grid3-cell x-grid3-td-quantity')]", driver);

			String addThridProduct = "//div[3]/table/tbody//*[contains(@class,'x-grid3-cell-inner x-grid3-col-productid')]";
			Utilities.HoverandClick(addThridProduct, driver);

			selectFromNormalDropDown("EditProduct", "//*[@name='pid']", driver);
			Utilities.HoverandClick(pro.getProperty("SalesOrderMemo"), driver);

			String qtyThridProduct = "//div[3]/table/tbody//*[contains(@class,'x-grid3-cell-inner x-grid3-col-quantity68SalesOrderEdit')]";
			Utilities.clickAndEnterText("10", qtyThridProduct, driver);

			Utilities.HoverandClick(pro.getProperty("SalesOrderEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("EditSalesOrderClose"), driver);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Sales Order Edited !!");
			} else {
				System.out.println("Sales Order Failed to Edit !!");
			}

		} catch (Exception EX) {
			System.out.println("Sales Order Failed to Edit !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Delete ******************************************

	public static void Delete_SalesOrder(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			documentid = "SO" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_SalesOrder.properties");

			Utilities.HoverandClick(pro.getProperty("deleteSalesOrdernButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteSalesOrderPermButton"), driver);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);

			WebElement element = new WebDriverWait(driver, 45).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//a[contains(text(),'Get Started by adding a Sales Order now')]")));
			if (element.isDisplayed()) {
				System.out.println("Sales Order Deleted !!!! ");
			} else {
				System.out.println("Sales Order is NOT Deleted !!!! ");
			}

			Utilities.HoverandClick("//li[@id='as__SalesOrderMainTabEntry']/a[1]", driver);

		}

		catch (Exception EX) {
			System.out.println("Sales Order is NOT Deleted !!!! ");
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

	// **********************Global Level Tz*******************
	public static void createGL_SalesOrder(String documentId, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentId = "SoGL" + documentId;
			Properties pro = Utilities.fetchProValue("OR_SalesOrder.properties");

			Utilities.waitandClick(pro.getProperty("CreateSalesOrderIcon"), driver);

			selectFromNormalDropDown(customerid, pro.getProperty("customerName"), driver);

			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceFormat"), driver);

			Utilities.enterTextInDropDown(documentId, pro.getProperty("SalesOrderNo"), driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.clickCheckBox(pro.getProperty("checkBox"), "uncheck", driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("productId"), driver);
			Utilities.clickCheckBox(pro.getProperty("SelectproductSS"), "check", driver);

			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);

			Utilities.HoverandClick(pro.getProperty("clickAdd"), driver);
			Thread.sleep(2000);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexQty = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='salesordereditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Quantity")) {
					indexQty = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='salesordereditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div[" + i
								+ "]/table/tbody/tr/td[" + indexQty + "]/div",
						driver);
				Utilities.sendText("50");
				Thread.sleep(1000);
			}

			// to scroll till end
			Utilities.justHover("//*[contains(text(),'Outstanding Balance')]", driver);
			selectFromNormalDropDown("Yes", pro.getProperty("includeGlobalTax"), driver);
			selectFromNormalDropDown("GST(DS)@7.00%", pro.getProperty("GlobalTaxValue"), driver);

			Utilities.HoverandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("clickOk"), driver);

			System.out.println("******* Sales Order: [" + documentId + "] is Created !!!!!! ********");
		} catch (Exception EX) {
			System.out.println("******* Sales Order is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

}
