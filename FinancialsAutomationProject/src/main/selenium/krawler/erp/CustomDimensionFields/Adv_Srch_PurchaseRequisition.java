package krawler.erp.CustomDimensionFields;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class Adv_Srch_PurchaseRequisition {

	public static void PurReqAdvSrch(String documentid, String CustomText, String NumericField, String DropDown,
			String productid, WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_PurchaseRequisition.properties");
			// clicked on main icon

			Utilities.waitandClick(pro.getProperty("CreatePurReqIcon"), driver);
			Utilities.clickCheckBox(pro.getProperty("addButton"), "uncheck", driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceFormat"), driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("PurchaseRequisitionNo"), driver);

			// -------- Enter Custom Fields Values

			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Custom_Numeric Field']")));
			driver.findElement(By.xpath("//input[@name='Custom_Numeric Field']")).click();
			driver.findElement(By.xpath("//input[@name='Custom_Numeric Field']")).sendKeys(NumericField);
			Thread.sleep(5000);

			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='Custom_CustomText']")));
			driver.findElement(By.xpath("//input[@name='Custom_CustomText']")).click();
			driver.findElement(By.xpath("//input[@name='Custom_CustomText']")).sendKeys(CustomText);
			Thread.sleep(5000);

			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[text()='Drop Down']/following ::div[2]/input[2]/following::img[1]")));
			driver.findElement(By.xpath("//*[text()='Drop Down']/following ::div[2]/input[2]/following::img[1]"))
					.click();

			Utilities.clickAndEnterText(DropDown,
					"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='" + DropDown
							+ "']",
					driver);

			// -----------Add Products------------------------------------------

			Utilities.HoverandClick(pro.getProperty("addButton"), driver);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(
					By.xpath("//span[text()='Product Category']/parent::td/preceding-sibling::td[2]/input")));
			driver.findElement(By.xpath("//span[text()='Product Category']/parent::td/preceding-sibling::td[2]/input"))
					.sendKeys("Mobile 1");
			Thread.sleep(4000);

			WebElement checkBoxElement = driver.findElement(By.xpath(
					".//*[text()='Product Category: (None)']/parent::div/following-sibling::div/div[1]/table/tbody/tr/td[1]/div/div"));
			checkBoxElement.click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(".//button[text()='Add' and @class='x-btn-text']")));
			driver.findElement(By.xpath(".//button[text()='Add' and @class='x-btn-text']")).click();
			Thread.sleep(3000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='requisitioneditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td"))
					.size(); i++) {

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

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']")));
			driver.findElement(By.xpath("//button[text()='Save']")).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
			driver.findElement(By.xpath("//button[text()='Yes']")).click();

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
			driver.findElement(By.xpath("//button[text()='OK']")).click();
			Thread.sleep(1000);

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='as__requisition']/a[1]")));
			driver.findElement(By.xpath(".//*[@id='as__requisition']/a[1]")).click();

			System.out.println("Purchase Requisition is Created Successfully : [" + documentid
					+ "] with Custom Field :[" + CustomText + "] , Numeric Field : [" + NumericField
					+ "] & Drop Down Value :[" + DropDown + "]");

		}

		catch (Exception EX) {
			System.out
					.println("******* PurchaseRequisition is NOT Created for :[" + documentid + "] checlk LOG *******");
			Utilities.handleError(EX, driver);

		}

	}

	public static void EnterPR_Report(WebDriver driver, String documentid, String CustomText, String NumericField,
			String DropDown) throws InterruptedException, AWTException {
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//map[@name='AlienAreas3']/area[26]")));
		driver.findElement(By.xpath("//map[@name='AlienAreas3']/area[26]")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Advanced Search']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Advanced Search']")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class=' widthforCmb']/div/img")));
		driver.findElement(By.xpath("//td[@class=' widthforCmb']/div/img")).click();
		Thread.sleep(5000);

		// -------------------- AND CASE 1 : CF=A & NF=2 Result : PRDoc2
		// -------------------------------------------------------------
		// -------------------- AND CASE 2 : CF=A & NF=1 Result : PRDoc1
		// -------------------------------------------------------------

		Utilities.clickAndEnterText("CustomText",
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='CustomText']",
				driver);

		new WebDriverWait(driver, 60).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='Search Text : ']/following::td[1]/input[@type='text']")));
		driver.findElement(By.xpath("//*[text()='Search Text : ']/following::td[1]/input[@type='text']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[text()='Search Text : ']/following::td[1]/input[@type='text']"))
				.sendKeys(CustomText);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Add']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Add']")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class=' widthforCmb']/div/img")));
		driver.findElement(By.xpath("//td[@class=' widthforCmb']/div/img")).click();
		Thread.sleep(5000);

		Utilities.clickAndEnterText("Numeric Field",
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='Numeric Field']",
				driver);

		new WebDriverWait(driver, 60).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='From Value : ']/following::td[1]/input[@type='text']")));
		driver.findElement(By.xpath("//*[text()='From Value : ']/following::td[1]/input[@type='text']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[text()='From Value : ']/following::td[1]/input[@type='text']"))
				.sendKeys(NumericField);

		new WebDriverWait(driver, 60).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='To Value : ']/following::td[1]/input[@type='text']")));
		driver.findElement(By.xpath("//*[text()='To Value : ']/following::td[1]/input[@type='text']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[text()='To Value : ']/following::td[1]/input[@type='text']"))
				.sendKeys(NumericField);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Add']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Add']")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Search']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Search']")).click();
		Thread.sleep(5000);

		int headersize = Utilities.totalSize(
				"//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div",
				driver);

		int index1 = 0;
		int index2 = 0;

		for (int i = 1; i <= headersize; i++) {
			String HeaderName = driver.findElement(By
					.xpath("//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
							+ i + "]/div"))
					.getText();
			if (HeaderName.equalsIgnoreCase("CustomText")) {
				index1 = i;
			}
			if (HeaderName.equalsIgnoreCase("Numeric Field")) {
				index2 = i;
			}
		}

		String a1 = driver
				.findElement(By.xpath("//*[contains(text(),'" + documentid + "')]/ancestor::tr/td[" + index1 + "]/div"))
				.getText();

		String a2 = driver
				.findElement(By.xpath("//*[contains(text(),'" + documentid + "')]/ancestor::tr/td[" + index2 + "]/div"))
				.getText();

		if (a1.contains(CustomText) && a2.contains(NumericField)) {

			System.out.println(" Data is getting Fetched Based on Custom Text : [" + a1 + "]" + " and Numeric Field : ["
					+ a2 + "] with Document No : [" + documentid + "]");
		}

		else {
			System.out.println(" Data is not getting Fetched Based on Custom Text : [" + a1 + "]"
					+ " and Numeric Field : [" + a2 + "] with Document No : [" + documentid + "]");

		}

		new WebDriverWait(driver, 60).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='as__PurchaseRequisitionList']/a[1]")));
		driver.findElement(By.xpath("//li[@id='as__PurchaseRequisitionList']/a[1]")).click();
		Thread.sleep(5000);
	}
	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public static void EnterPR_Report1(WebDriver driver, String documentid, String CustomText, String NumericField,
			String DropDown) throws InterruptedException, AWTException, IOException {
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//map[@name='AlienAreas3']/area[26]")));
		driver.findElement(By.xpath("//map[@name='AlienAreas3']/area[26]")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Advanced Search']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Advanced Search']")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class=' widthforCmb']/div/img")));
		driver.findElement(By.xpath("//td[@class=' widthforCmb']/div/img")).click();
		Thread.sleep(5000);

		// -------------------- OR CASE 3 : CF=D & NF=1 Result : PRDoc1
		// -------------------------------------------------------------
		// -------------------- OR CASE 4 : CF=D & NF=2 Result : PRDoc2
		// -------------------------------------------------------------

		Utilities.clickAndEnterText("CustomText",
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='CustomText']",
				driver);

		new WebDriverWait(driver, 60).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='Search Text : ']/following::td[1]/input[@type='text']")));
		driver.findElement(By.xpath("//*[text()='Search Text : ']/following::td[1]/input[@type='text']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[text()='Search Text : ']/following::td[1]/input[@type='text']"))
				.sendKeys(CustomText);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Add']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Add']")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@id='as']//*[text()='Filter Conjunction']/following ::td[1]/div/img")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Filter Conjunction']/following ::td[1]/div/img"))
				.click();
		Thread.sleep(5000);

		Utilities.clickAndEnterText("OR",
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='OR']", driver);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class=' widthforCmb']/div/img")));
		driver.findElement(By.xpath("//td[@class=' widthforCmb']/div/img")).click();
		Thread.sleep(5000);

		Utilities.clickAndEnterText("Numeric Field",
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='Numeric Field']",
				driver);

		new WebDriverWait(driver, 60).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='From Value : ']/following::td[1]/input[@type='text']")));
		driver.findElement(By.xpath("//*[text()='From Value : ']/following::td[1]/input[@type='text']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[text()='From Value : ']/following::td[1]/input[@type='text']"))
				.sendKeys(NumericField);

		new WebDriverWait(driver, 60).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='To Value : ']/following::td[1]/input[@type='text']")));
		driver.findElement(By.xpath("//*[text()='To Value : ']/following::td[1]/input[@type='text']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[text()='To Value : ']/following::td[1]/input[@type='text']"))
				.sendKeys(NumericField);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Add']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Add']")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Search']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Search']")).click();
		Thread.sleep(5000);

		int headersize = Utilities.totalSize(
				"//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div",
				driver);

		int index3 = 0;
		int index4 = 0;

		for (int i = 1; i <= headersize; i++) {
			String HeaderName = driver.findElement(By
					.xpath("//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
							+ i + "]/div"))
					.getText();
			if (HeaderName.equalsIgnoreCase("CustomText")) {
				index3 = i;
			}
			if (HeaderName.equalsIgnoreCase("Numeric Field")) {
				index4 = i;
			}
		}

		String a3 = driver
				.findElement(By.xpath("//*[contains(text(),'" + documentid + "')]/ancestor::tr/td[" + index3 + "]/div"))
				.getText();

		String a4 = driver
				.findElement(By.xpath("//*[contains(text(),'" + documentid + "')]/ancestor::tr/td[" + index4 + "]/div"))
				.getText();

		if (!a3.contains(CustomText) || a4.contains(NumericField)) {

			System.out.println(" Data is getting Fetched Based on Custom Text : [" + a3 + "]" + " OR Numeric Field : ["
					+ a4 + "] with Document No : [" + documentid + "]");
		}

		else {
			System.out.println(" Data is not getting Fetched Based on Custom Text : [" + a3 + "]"
					+ " OR Numeric Field : [" + a4 + "] with Document No : [" + documentid + "]");

		}

		new WebDriverWait(driver, 60).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='as__PurchaseRequisitionList']/a[1]")));
		driver.findElement(By.xpath("//li[@id='as__PurchaseRequisitionList']/a[1]")).click();
		Thread.sleep(5000);

	}
	// --------------------------------------------------------------------------------------------------------------------------

	public static void EnterPR_Report2(WebDriver driver, String[] documentid, String CustomText, String NumericField,
			String DropDown) throws InterruptedException, AWTException, IOException {
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//map[@name='AlienAreas3']/area[26]")));
		driver.findElement(By.xpath("//map[@name='AlienAreas3']/area[26]")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Advanced Search']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Advanced Search']")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class=' widthforCmb']/div/img")));
		driver.findElement(By.xpath("//td[@class=' widthforCmb']/div/img")).click();
		Thread.sleep(5000);

		// ----------------

		Utilities.clickAndEnterText("CustomText",
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='CustomText']",
				driver);

		new WebDriverWait(driver, 60).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='Search Text : ']/following::td[1]/input[@type='text']")));
		driver.findElement(By.xpath("//*[text()='Search Text : ']/following::td[1]/input[@type='text']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[text()='Search Text : ']/following::td[1]/input[@type='text']"))
				.sendKeys(CustomText);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Add']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Add']")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class=' widthforCmb']/div/img")));
		driver.findElement(By.xpath("//td[@class=' widthforCmb']/div/img")).click();
		Thread.sleep(5000);

		Utilities.clickAndEnterText("Drop Down",
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='Drop Down']",
				driver);

		new WebDriverWait(driver, 60).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='Search Text : ']/following::td[1]/div/input")));
		driver.findElement(By.xpath("//*[text()='Search Text : ']/following::td[1]/div/input")).click();
		Thread.sleep(5000);

		Utilities.clickAndEnterText(DropDown,
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='X']", driver);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Add']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Add']")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Search']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Search']")).click();
		Thread.sleep(5000);

		int headersize = Utilities.totalSize(
				"//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div",
				driver);

		int index3 = 0;
		int index4 = 0;

		for (int i = 1; i <= headersize; i++) {
			String HeaderName = driver.findElement(By
					.xpath("//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
							+ i + "]/div"))
					.getText();
			if (HeaderName.equalsIgnoreCase("CustomText")) {
				index3 = i;
			}
			if (HeaderName.equalsIgnoreCase("Drop Down")) {
				index4 = i;
			}
		}

		String doc1 = driver.findElement(By.xpath("//*[contains(text(),'PRDoc2')]")).getText();

		String custf1 = driver.findElement(By
				.xpath("//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
						+ index3 + "]"))
				.getText();

		String dropd1 = driver.findElement(By
				.xpath("//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
						+ index4 + "]"))
				.getText();

		String doc2 = driver.findElement(By.xpath("//*[contains(text(),'PRDoc3')]")).getText();

		String custf2 = driver.findElement(By
				.xpath("//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
						+ index3 + "]"))
				.getText();

		String dropd2 = driver.findElement(By
				.xpath("//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
						+ index4 + "]"))
				.getText();

		String actualResult1[] = { doc1, custf1, dropd1 };

		String actualResult2[] = { doc2, custf2, dropd2 };

		String ExpectedResult1[] = { "PRDoc2", "A", "X" };

		String ExpectedResult2[] = { "PRDoc3", "A", "X" };

		for (int k = 0; k < actualResult1.length; k++) {

			if (actualResult1[k].equalsIgnoreCase(ExpectedResult1[k])
					&& actualResult2[k].equalsIgnoreCase(ExpectedResult2[k])) {
				System.out.println(" Data is getting Fetched Based on : [" + actualResult1[k] + "] AND ["
						+ actualResult2[k] + "]");
			} else {
				System.out.println(" Data is not getting Fetched Based on : [" + ExpectedResult1[k] + "] AND ["
						+ ExpectedResult2[k] + "]");
			}
		}

		new WebDriverWait(driver, 60).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='as__PurchaseRequisitionList']/a[1]")));
		driver.findElement(By.xpath("//li[@id='as__PurchaseRequisitionList']/a[1]")).click();
		Thread.sleep(5000);

	}

	public static void EnterPR_Report3(WebDriver driver, String[] documentid, String CustomText, String NumericField,
			String DropDown) throws InterruptedException, AWTException, IOException {
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//map[@name='AlienAreas3']/area[26]")));
		driver.findElement(By.xpath("//map[@name='AlienAreas3']/area[26]")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Advanced Search']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Advanced Search']")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@id='as']//*[text()='Filter Conjunction']/following ::td[1]/div/img")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Filter Conjunction']/following ::td[1]/div/img"))
				.click();
		Thread.sleep(5000);

		Utilities.clickAndEnterText("OR",
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='OR']", driver);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class=' widthforCmb']/div/img")));
		driver.findElement(By.xpath("//td[@class=' widthforCmb']/div/img")).click();
		Thread.sleep(5000);

		Utilities.clickAndEnterText("Numeric Field",
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='Numeric Field']",
				driver);

		new WebDriverWait(driver, 60).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='From Value : ']/following::td[1]/input[@type='text']")));
		driver.findElement(By.xpath("//*[text()='From Value : ']/following::td[1]/input[@type='text']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[text()='From Value : ']/following::td[1]/input[@type='text']"))
				.sendKeys(NumericField);

		new WebDriverWait(driver, 60).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='To Value : ']/following::td[1]/input[@type='text']")));
		driver.findElement(By.xpath("//*[text()='To Value : ']/following::td[1]/input[@type='text']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[text()='To Value : ']/following::td[1]/input[@type='text']"))
				.sendKeys(NumericField);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Add']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Add']")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class=' widthforCmb']/div/img")));
		driver.findElement(By.xpath("//td[@class=' widthforCmb']/div/img")).click();
		Thread.sleep(5000);

		Utilities.clickAndEnterText("Drop Down",
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='Drop Down']",
				driver);

		new WebDriverWait(driver, 60).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='Search Text : ']/following::td[1]/div/input")));
		driver.findElement(By.xpath("//*[text()='Search Text : ']/following::td[1]/div/input")).click();
		Thread.sleep(5000);

		Utilities.clickAndEnterText(DropDown,
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='X']", driver);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Add']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Add']")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Search']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Search']")).click();
		Thread.sleep(5000);

		int headersize = Utilities.totalSize(
				"//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div",
				driver);

		int index3 = 0;
		int index4 = 0;

		for (int i = 1; i <= headersize; i++) {
			String HeaderName = driver.findElement(By
					.xpath("//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
							+ i + "]/div"))
					.getText();
			if (HeaderName.equalsIgnoreCase("Numeric Field")) {
				index3 = i;
			}
			if (HeaderName.equalsIgnoreCase("Drop Down")) {
				index4 = i;
			}
		}

		String doc1 = driver.findElement(By.xpath("//*[contains(text(),'PRDoc2')]")).getText();

		String numf1 = driver.findElement(By
				.xpath("//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
						+ index3 + "]"))
				.getText();

		String dropd1 = driver.findElement(By
				.xpath("//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
						+ index4 + "]"))
				.getText();

		String doc2 = driver.findElement(By.xpath("//*[contains(text(),'PRDoc3')]")).getText();

		String numf2 = driver.findElement(By
				.xpath("//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
						+ index3 + "]"))
				.getText();

		String dropd2 = driver.findElement(By
				.xpath("//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
						+ index4 + "]"))
				.getText();

		String ActDocmts[] = { doc1, doc2 };
		String ExpDocmts[] = { "PRDoc2", "PRDoc3" };

		String ActNum1[] = { numf1, numf2 };
		String ExpNum1[] = { "2", "2" };

		String ActDropd1[] = { dropd1, dropd2 };
		String ExpDropd1[] = { "X", "X" };

		for (int i = 0; i < ExpDocmts.length; i++) {
			if (ActDocmts[i].contains(ExpDocmts[i]) && ActNum1[i].contains(ExpNum1[i])
					&& ActDropd1[i].contains(ExpDropd1[i])) {
				System.out.println("matched");
			} else {
				System.out.println("342434matched");
			}
		}
		new WebDriverWait(driver, 60).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='as__PurchaseRequisitionList']/a[1]")));
		driver.findElement(By.xpath("//li[@id='as__PurchaseRequisitionList']/a[1]")).click();
		Thread.sleep(5000);
	}

	public static void EnterPR_Report4(WebDriver driver, String[] documentid, String CustomText, String NumericField,
			String DropDown) throws InterruptedException, AWTException, IOException {

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//map[@name='AlienAreas3']/area[26]")));
		driver.findElement(By.xpath("//map[@name='AlienAreas3']/area[26]")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Advanced Search']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Advanced Search']")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@id='as']//*[text()='Filter Conjunction']/following ::td[1]/div/img")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Filter Conjunction']/following ::td[1]/div/img"))
				.click();
		Thread.sleep(5000);

		Utilities.clickAndEnterText("OR",
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='OR']", driver);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class=' widthforCmb']/div/img")));
		driver.findElement(By.xpath("//td[@class=' widthforCmb']/div/img")).click();
		Thread.sleep(5000);

		Utilities.clickAndEnterText("CustomText",
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='CustomText']",
				driver);

		new WebDriverWait(driver, 60).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='Search Text : ']/following::td[1]/input[@type='text']")));
		driver.findElement(By.xpath("//*[text()='Search Text : ']/following::td[1]/input[@type='text']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[text()='Search Text : ']/following::td[1]/input[@type='text']"))
				.sendKeys(CustomText);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Add']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Add']")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class=' widthforCmb']/div/img")));
		driver.findElement(By.xpath("//td[@class=' widthforCmb']/div/img")).click();
		Thread.sleep(5000);

		Utilities.clickAndEnterText("Drop Down",
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='Drop Down']",
				driver);

		new WebDriverWait(driver, 60).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='Search Text : ']/following::td[1]/div/input")));
		driver.findElement(By.xpath("//*[text()='Search Text : ']/following::td[1]/div/input")).click();
		Thread.sleep(5000);

		Utilities.clickAndEnterText(DropDown,
				"//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div//*[text()='X']", driver);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Add']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Add']")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Search']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Search']")).click();
		Thread.sleep(5000);

		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='as']//*[text()='Search']")));
		driver.findElement(By.xpath("//div[@id='as']//*[text()='Search']")).click();
		Thread.sleep(5000);

		int headersize = Utilities.totalSize(
				"//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div",
				driver);

		int index3 = 0;
		int index4 = 0;

		for (int i = 1; i <= headersize; i++) {
			String HeaderName = driver.findElement(By
					.xpath("//div[@id='PurchaseRequisitionList']/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
							+ i + "]/div"))
					.getText();
			if (HeaderName.equalsIgnoreCase("CustomText")) {
				index3 = i;
			}
			if (HeaderName.equalsIgnoreCase("Drop Down")) {
				index4 = i;
			}
		}

	}
}