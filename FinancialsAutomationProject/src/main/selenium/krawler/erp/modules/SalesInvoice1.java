package krawler.erp.modules;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.EmailFunctionality;
import krawler.erp.page.Utilities;

public class SalesInvoice1 {

	public static void create_salesInvoice(String documentId, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentId = "SI" + documentId;
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			// clicked on main icon

			Utilities.HoverandClick(pro.getProperty("SalesInvoiceIcon"), driver);
			Utilities.clickCheckBox(pro.getProperty("includeTax"), "uncheck", driver);
			selectFromNormalDropDown(customerid, pro.getProperty("Customer"), driver);
			selectFromNormalDropDown("NA", pro.getProperty("SequenceFormat"), driver);
			selectFromNormalDropDown("NET 45", pro.getProperty("Term"), driver);
			Utilities.enterTextNormalBox(documentId, pro.getProperty("DocumentNo"), driver);

			Utilities.clickCheckBox("//*[@name='includingGST']", "check", driver);
			Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
			Utilities.clickButton("Yes", 10, driver);

			Utilities.HoverandClick(pro.getProperty("AddButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("ProductSearch"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			List<WebElement> header = driver.findElements(By.xpath(
					"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td/div"));
			int headRsize = header.size();
			int indexQty = 0, indexTax = 0;

			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Quantity")) {
					indexQty = i;
				}
			}
			// System.out.println(indexQty +"&"+ indexTax );

			// enter line level quantity
			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div["
						+ i + "]/table/tbody/tr/td[" + indexQty + "]/div", driver);
				Utilities.enter_LinelabelAmount("200", driver);
				Thread.sleep(400);
			}

			// to scroll till end
			Utilities.justHover(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[contains(@class,'x-grid3-cell-last')]",
					driver);

			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (HeadeName.equalsIgnoreCase("Product Tax")) {
					indexTax = i;
				}
			}

			// enter line level Tax
			String taxNme[] = { "GST(DS)@7.00%", "GST(SR)@7.00%" };
			int sizeOfArr = taxNme.length;

			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div["
						+ i + "]/table/tbody/tr/td[" + indexTax + "]/div", driver);
				Thread.sleep(1000);

				if (i <= sizeOfArr) {
					Utilities.enterTextInDropDown(taxNme[i - 1], "//input[@id='prtaxid']/following::input[1]", driver);
				} else {
					int index = getArrayIndex(i, sizeOfArr);
					Utilities.enterTextInDropDown(taxNme[index - 1], "//input[@id='prtaxid']/following::input[1]",
							driver);
				}
			}

			// move ficus frm Line label
			Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			String subjectLine = "Sales Invoice - testsmoke - " + documentId;
			EmailFunctionality.email_AfterSave(documentId, subjectLine, driver);

			Utilities.HoverandClick(pro.getProperty("CloseSalesInvoice"), driver);

			System.out.println("******* Sales Invoice: [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Sales Invoice is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
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

	public static void Copy_salesInvoice(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {

			documentid = "SI" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");

			Utilities.HoverandClick(pro.getProperty("SalesInvoiceReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(1500);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("copySalesInvoiceButton"), driver);

			Utilities.formLoaded(driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("copySalesInvoiceId"), driver);
			Utilities.HoverandClick(pro.getProperty("saveCopiedSalesInvoiceId"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("CopySalesInvoiceClose"), driver);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(1500);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Sales Invoice Copy Created !!");
			} else {
				System.out.println("Sales Invoice Copy NOT Created !!");
			}
		} catch (Exception EX) {
			System.out.println("Sales Invoice Copy NOT Created !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Edit ******************************************

	public static void Edit_salesInvoice(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			documentid = "SI" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			Utilities.HoverandClick(pro.getProperty("EditSalesInvoiceButton"), driver);

			Utilities.formLoaded(driver);
			Utilities.enterTextInDropDown("Performing Edit Operation", pro.getProperty("SalesInvoiceMemo"), driver);
			Utilities.clickAndEnterText("60",
					"//div[1]/table//td[contains(@class,'x-grid3-col x-grid3-cell x-grid3-td-quantity')]", driver);

			String addThridProduct = "//div[3]/table/tbody//*[contains(@class,'x-grid3-cell-inner x-grid3-col-productid')]";
			Utilities.HoverandClick(addThridProduct, driver);

			selectFromNormalDropDown("EditProduct", "//*[@name='pid']", driver);
			Utilities.HoverandClick("//textarea[contains(@id,'memo')]", driver);

			String qtyThridProduct = "//div[3]/table/tbody//*[contains(@class,'x-grid3-cell-inner x-grid3-col-quantity67Edit')]";
			Utilities.clickAndEnterText("10", qtyThridProduct, driver);

			Utilities.HoverandClick(pro.getProperty("SalesInvoiceEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("EditSalesInvoiceClose"), driver);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Sales Invoice Edited !!");
			} else {
				System.out.println("Sales Invoice Failed to Edit !!");
			}
		} catch (Exception EX) {
			System.out.println("Sales Invoice Failed to Edit !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Delete ******************************************

	public static void Delete_salesInvoice(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {
			documentid = "SI" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");

			Utilities.HoverandClick(pro.getProperty("deleteSalesInvoiceButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteSalesInvoicePermButton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			WebElement element = new WebDriverWait(driver, 45).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//a[contains(text(),' Get Started by adding a Invoice now')]")));
			if (element.isDisplayed()) {
				System.out.println("Sales Invoice Deleted !!!! ");
			} else {
				System.out.println("Sales Invoice is NOT Deleted !!!! ");
			}
			Utilities.HoverandClick(pro.getProperty("CloseCustomerReport"), driver);

		}

		catch (Exception EX) {
			System.out.println("Sales Invoice is NOT Deleted !!!! ");
			Utilities.handleError(EX, driver);
		}
	}

	// ************************** GlobalTax & Terms
	// ******************************************

	public static void createGL_salesInvoice(String documentId, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentId = "SiGL" + documentId;
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			// clicked on main icon

			Utilities.HoverandClick(pro.getProperty("SalesInvoiceIcon"), driver);

			Utilities.clickCheckBox(pro.getProperty("includeTax"), "uncheck", driver);

			selectFromNormalDropDown(customerid, pro.getProperty("Customer"), driver);

			Utilities.enterTextInDropDown("NA", pro.getProperty("SequenceFormat"), driver);

			Utilities.enterTextInDropDown("NET 45", pro.getProperty("Term"), driver);

			Utilities.enterTextInDropDown(documentId, pro.getProperty("DocumentNo"), driver);

			Utilities.HoverandClick(pro.getProperty("AddButton"), driver);

			Utilities.clickCheckBox(pro.getProperty("SelectProduct"), "uncheck", driver);

			Utilities.enterTextInDropDown(productid, pro.getProperty("ProductSearch"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(
					"//*[text()='Product Category: (None)']/parent::div/following-sibling::div//table/tbody/tr/td[1]/div/div",
					driver);
			// System.out.println(totalResult);

			Utilities.clickButton("Add", 1000, driver);
			Thread.sleep(2000);

			List<WebElement> header = driver.findElements(By.xpath(
					"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td/div"));
			int headRsize = header.size();
			int indexQty = 0, indexTax = 0;

			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Quantity")) {
					indexQty = i;
				}
			}

			// enter line level quantity
			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div["
						+ i + "]/table/tbody/tr/td[" + indexQty + "]/div", driver);
				Utilities.sendText("100");
				Thread.sleep(1000);
			}

			// to scroll till down
			Utilities.justHover("//*[contains(text(),'Outstanding Balance')]", driver);

			selectFromNormalDropDown("Yes", pro.getProperty("includeGlobalTax"), driver);
			selectFromNormalDropDown("GST(DS)@7.00%", pro.getProperty("GlobalTaxValue"), driver);

			int termHead = Utilities.totalSize(
					"//*[contains(@id,'termgridpanel')]//*[@class='x-grid3-header']/div/div/table/thead/tr/td", driver);
			int indOfPercentage = 0;
			for (int i = 1; i <= termHead; i++) {
				String headName = driver.findElement(By
						.xpath("//*[contains(@id,'termgridpanel')]//*[@class='x-grid3-header']/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (headName.equalsIgnoreCase("Percentage")) {
					indOfPercentage = i;
				}
			}
			// add + term
			Utilities.clickAndEnterText("10",
					"//*[contains(@id,'termgridpanel')]//*[@class='x-grid3-scroller']/div/div[1]/table/tbody/tr/td["
							+ indOfPercentage + "]/div",
					driver);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);

			// add - term
			Utilities.clickAndEnterText("5",
					"//*[contains(@id,'termgridpanel')]//*[@class='x-grid3-scroller']/div/div[2]/table/tbody/tr/td["
							+ indOfPercentage + "]/div",
					driver);
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("CloseSalesInvoice"), driver);

			System.out.println("******* Sales Invoice: [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Sales Invoice is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}

	}

	// select item from NormalDrop Down
	public static void selectFromNormalDropDown(String text, String expForname, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		WebElement enterText = null;
		boolean success = false;
		for (int num_try = 0; !success && num_try < 10; num_try++) {
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
				System.out.println(num_try);
				if (num_try == 9) {
					Utilities.handleError(EX, driver);
				}
			}
		}
	}

	// ********************************** NEw Reqirements
	// 12/02/2019***************************************
	public static void create_salesInvoice_Proper(String documentId, String productid, String customerid,
			String quantity, WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			documentId = "SI" + documentId;
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			// clicked on main icon

			Utilities.HoverandClick(pro.getProperty("SalesInvoiceIcon"), driver);
			Utilities.clickCheckBox(pro.getProperty("includeTax"), "uncheck", driver);
			selectFromNormalDropDown(customerid, pro.getProperty("Customer"), driver);
			selectFromNormalDropDown("NA", pro.getProperty("SequenceFormat"), driver);
			selectFromNormalDropDown("NET 45", pro.getProperty("Term"), driver);
			Utilities.enterTextNormalBox(documentId, pro.getProperty("DocumentNo"), driver);

			Utilities.clickCheckBox("//*[@name='includingGST']", "check", driver);
			Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
			Utilities.clickButton("Yes", 10, driver);

			Utilities.HoverandClick(pro.getProperty("AddButton"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.enterTextInDropDown(productid, pro.getProperty("ProductSearch"), driver);
			Utilities.isLoadingDisappear(100, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			List<WebElement> header = driver.findElements(By.xpath(
					"//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td/div"));
			int headRsize = header.size();
			int indexQty = 0, indexTax = 0;

			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Quantity")) {
					indexQty = i;
				}
			}
			// System.out.println(indexQty +"&"+ indexTax );

			// enter line level quantity
			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div["
						+ i + "]/table/tbody/tr/td[" + indexQty + "]/div", driver);
				Utilities.enter_LinelabelAmount(quantity, driver);
				Thread.sleep(400);
			}

			// to scroll till end
			Utilities.justHover(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[1]//*[contains(@class,'x-grid3-cell-last')]",
					driver);

			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (HeadeName.equalsIgnoreCase("Product Tax")) {
					indexTax = i;
				}
			}

			// enter line level Tax
			String taxNme[] = { "GST(DS)@7.00%", "GST(SR)@7.00%" };
			int sizeOfArr = taxNme.length;

			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick("//div[@id='Invoiceeditproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div["
						+ i + "]/table/tbody/tr/td[" + indexTax + "]/div", driver);
				Thread.sleep(1000);

				if (i <= sizeOfArr) {
					Utilities.enterTextInDropDown(taxNme[i - 1], "//input[@id='prtaxid']/following::input[1]", driver);
				} else {
					int index = getArrayIndex(i, sizeOfArr);
					Utilities.enterTextInDropDown(taxNme[index - 1], "//input[@id='prtaxid']/following::input[1]",
							driver);
				}
			}

			// move ficus frm Line label
			Utilities.HoverandClick("//*[@id='memo2Invoice']", driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			try {
				driver.findElement(By.xpath("//button[text()='Yes']")).click();
			} catch (Exception skipp) {

			}
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("CloseSalesInvoice"), driver);

			System.out.println("******* Sales Invoice: [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Sales Invoice is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}
	}

}
