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

public class VendorQuotation1 {

	public static void create_VendorQuotation(String documentId, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentId = "VQ" + documentId;
			Properties pro = Utilities.fetchProValue("OR_VendorQuotation.properties");
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";

			Utilities.waitandClick(pro.getProperty("CreateVendorQuotationIcon"), driver);
			Utilities.isElementGone(xpathOfLoading, 45, driver);
			selectFromNormalDropDown(vendorid, pro.getProperty("vendorName"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceformat"), driver);
			Utilities.enterTextInDropDown(documentId, pro.getProperty("VendorQuatationId"), driver);

			Utilities.clickCheckBox(pro.getProperty("includingGST"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("memoField"), driver);
			Utilities.clickButton("Yes", 500, driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			Utilities.isLoadingDisappear(45, driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("productSearch"), driver);
			Utilities.isLoadingDisappear(45, driver);
			Utilities.clickCheckBox(pro.getProperty("SelectProductS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);
			Utilities.HoverandClick(pro.getProperty("addProducts"), driver);
			Thread.sleep(2000);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexQty = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='vendorquotationundefinededitproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Quantity")) {
					indexQty = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='vendorquotationundefinededitproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div["
								+ i + "]/table/tbody/tr/td[" + indexQty + "]/div",
						driver);
				Utilities.enter_LinelabelAmount("50", driver);
				Thread.sleep(1000);
			}

			// Scroll line label
			Utilities.justHover("//*[@class='pwnd delete-gridrow']", driver);

			int indexTax = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='vendorquotationundefinededitproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (HeadeName.equalsIgnoreCase("Product Tax")) {
					indexTax = i;
				}
			}

			// enter line level Tax
			String taxNme[] = { "GST(BL)@7.00%", "GST(IM)@7.00%" };
			int sizeOfArr = taxNme.length;

			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='vendorquotationundefinededitproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div["
								+ i + "]/table/tbody/tr/td[" + indexTax + "]/div",
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

			Utilities.HoverandClick(pro.getProperty("memoField"), driver);

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			// Email code
			String subjectLine = "Vendor Quotation: - testsmoke - " + documentId;
			EmailFunctionality.email_AfterSave(documentId, subjectLine, driver);

			Utilities.HoverandClick(pro.getProperty("quotationOk"), driver);

			System.out.println("******* Vendor Quotation: [" + documentId + "] is Created !!!!!! ********");

		} catch (Exception EX) {
			System.out.println("******* Vendor Quotation is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}

	}

	// ******************** Copy ******************************************

	public static void Copy_VendorQuotation(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {

			documentid = "VQ" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_VendorQuotation.properties");

			Utilities.HoverandClick(pro.getProperty("VendorQuotationReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(1500);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Utilities.HoverandClick(pro.getProperty("copyVendorQuotationButton"), driver);

			String isFormloaded = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr//*[@class='x-grid3-row-checker']";
			Utilities.clickCheckBox(isFormloaded, "uncheck", driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("copyVendorQuotationId"), driver);
			Utilities.HoverandClick(pro.getProperty("saveCopiedVendorQuotationId"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Utilities.HoverandClick(pro.getProperty("CopyVendorQuotationClose"), driver);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Vendor Quotation Copy Created !!");
			} else {
				System.out.println("Vendor Quotation Copy NOT Created !!");
			}
		} catch (Exception EX) {
			System.out.println("Vendor Quotation Copy NOT Created !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Edit ******************************************

	public static void Edit_VendorQuotation(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {

			documentid = "VQ" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_VendorQuotation.properties");
			Utilities.HoverandClick(pro.getProperty("EditVendorQuotationButton"), driver);

			String isFormloaded = "//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr//*[@class='x-grid3-row-checker']";
			Utilities.clickCheckBox(isFormloaded, "uncheck", driver);
			Utilities.enterTextInDropDown("Performing Edit Operation", pro.getProperty("VendorQuotationMemo"), driver);
			Utilities.clickAndEnterText("60",
					"//div[1]/table//td[contains(@class,'x-grid3-col x-grid3-cell x-grid3-td-quantity')]", driver);

			String addThridProduct = "//div[3]/table/tbody//*[contains(@class,'x-grid3-cell-inner x-grid3-col-productid')]";
			Utilities.HoverandClick(addThridProduct, driver);

			selectFromNormalDropDown("EditProduct", "//*[@name='pid']", driver);
			Utilities.HoverandClick(pro.getProperty("VendorQuotationMemo"), driver);

			String qtyThridProduct = "//div[3]/table/tbody//*[contains(@class,'x-grid3-cell-inner x-grid3-col-quantity62vendorquotationEdit')]";
			Utilities.clickAndEnterText("10", qtyThridProduct, driver);

			Utilities.HoverandClick(pro.getProperty("VendorQuotationEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("EditVendorQuotationClose"), driver);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Vendor Quotation Edited !!");
			} else {
				System.out.println("Vendor Quotation Failed to Edit !!");
			}

		} catch (Exception EX) {
			System.out.println("Vendor Quotation Failed to Edit !!");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Delete ******************************************

	public static void Delete_VendorQuotation(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {

			documentid = "VQ" + documentid;
			String copyDocno = "Copy" + documentid;
			Properties pro = Utilities.fetchProValue("OR_VendorQuotation.properties");

			Utilities.HoverandClick(pro.getProperty("deleteVendorQuotationButton"), driver);
			Utilities.HoverandClick(pro.getProperty("deleteVendorQuotationPermButton"), driver);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);

			Utilities.enterTextInDropDown(copyDocno, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			WebElement element = new WebDriverWait(driver, 45).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//a[contains(text(),'Get Started by adding a Vendor Quotation now')]")));
			if (element.isDisplayed()) {
				System.out.println("Vendor Quotation Deleted !!!! ");
			} else {
				System.out.println("Vendor Quotation is NOT Deleted !!!! ");
			}

			Utilities.HoverandClick(pro.getProperty("CloseVendorQuotationTab"), driver);

		}

		catch (Exception EX) {
			System.out.println("Vendor Quotation is NOT Deleted !!!! ");
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

	// * * * * * * * * * Global Label Tax * * * * * * * *
	public static void createGL_VendorQuotation(String documentId, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentId = "VqGL" + documentId;
			Properties pro = Utilities.fetchProValue("OR_VendorQuotation.properties");

			Utilities.waitandClick(pro.getProperty("CreateVendorQuotationIcon"), driver);

			selectFromNormalDropDown(vendorid, pro.getProperty("vendorName"), driver);

			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceformat"), driver);

			Utilities.enterTextInDropDown(documentId, pro.getProperty("VendorQuatationId"), driver);

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);

			Utilities.clickCheckBox(pro.getProperty("productSelect"), "uncheck", driver);

			Utilities.enterTextNormalBox(productid, pro.getProperty("productSearch"), driver);
			Thread.sleep(1000);

			Utilities.clickCheckBox(pro.getProperty("productSelect"), "uncheck", driver);
			Utilities.clickCheckBox(pro.getProperty("SelectproductSS"), "check", driver);
			int totalResult = Utilities.totalSize(pro.getProperty("TotalFindProducts"), driver);

			Utilities.clickButton("Add", 1000, driver);
			Thread.sleep(2000);

			int headRsize = Utilities.totalSize(pro.getProperty("HeaderNames"), driver);
			int indexQty = 0;
			for (int i = 1; i <= headRsize; i++) {
				String HeadeName = driver.findElement(By
						.xpath("//div[@id='vendorquotationundefinededitproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (HeadeName.equalsIgnoreCase("Quantity")) {
					indexQty = i;
				}
			}

			for (int i = 1; i <= totalResult; i++) {
				Utilities.HoverandClick(
						"//div[@id='vendorquotationundefinededitproductdetailsgrid']/div/div/div/div/div[2]/div[1]/div["
								+ i + "]/table/tbody/tr/td[" + indexQty + "]/div",
						driver);
				Utilities.sendText("50");
				Thread.sleep(1000);
			}

			// to scroll till end
			Utilities.justHover("//*[contains(text(),'Outstanding Balance')]", driver);
			selectFromNormalDropDown("Yes", pro.getProperty("includeGlobalTax"), driver);
			selectFromNormalDropDown("GST(BL)@7.00%", pro.getProperty("GlobalTaxValue"), driver);

			Utilities.HoverandClick(pro.getProperty("memoField"), driver);

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			System.out.println("******* Vendor Quotation: [" + documentId + "] is Created !!!!!! ********");
			Utilities.HoverandClick(pro.getProperty("quotationOk"), driver);

		} catch (Exception EX) {
			System.out.println("******* Vendor Quotation is NOT Created for :[" + documentId + "] checlk LOG *******");
			Utilities.handleError(EX, driver);
		}

	}

}