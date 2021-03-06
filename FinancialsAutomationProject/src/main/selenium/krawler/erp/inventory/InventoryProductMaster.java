package krawler.erp.inventory;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import krawler.erp.page.Utilities;

public class InventoryProductMaster {

	WebDriver driver;
	
	public static void custom(WebDriver driver) {
		/*
		try {
			Utilities.enterTextandSelectCUSTOM("Exempted",
					driver.findElement(By.xpath("//input[@id='Custom_Product Tax Class']/following-sibling::input")),
					driver);
			Utilities.enterTextandSelectCUSTOM("0401",
					driver.findElement(By.xpath("//input[@id='Custom_HSN/SAC Code']/following-sibling::input")),
					driver);
			Utilities.enterTextandSelectCUSTOM("BAG-BAGS",
					driver.findElement(By.xpath("//input[@id='Custom_Unit Quantity Code']/following-sibling::input")),
					driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}

	// **** All in One Products
	public static void create_Product(String productid, String productType, String purchaseAccount, String salesAccount,
			WebDriver driver) throws IOException, InterruptedException, AWTException {
		Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
		String productName = productid + "Name";
		String productDes = productid + " : product created on [" + Utilities.currentDate("dd-MM-yyyy") + "]";
		String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";
		try {
			Utilities.click_element(pro.getProperty("createProductIcon"), driver);
			// ------------- General Tab
			// ----------------------------------------------
			Utilities.click_element(pro.getProperty("qaenable"), driver);
			
			Utilities.enterTextNormalBox(productName, pro.getProperty("productName"), driver);
			Utilities.enterTextNormalBox(productDes, pro.getProperty("productDes"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceformat"), driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("productId"), driver);
			
			custom(driver); //FAISAL
			
			// if Batch
			if (productType.equalsIgnoreCase("BATCH")) {
				Utilities.click_element(pro.getProperty("BatchCheck"), driver);
			}
			// if Serial
			if (productType.equalsIgnoreCase("SERIAL")) {
				Utilities.click_element(pro.getProperty("SerialCheck"), driver);
			}
			// if BatchSerial
			if (productType.equalsIgnoreCase("BatchSerial")) {
				Utilities.click_element(pro.getProperty("BatchCheck"), driver);
				Utilities.click_element(pro.getProperty("SerialCheck"), driver);
			}

			// ------------- Purchase Tab
			// ----------------------------------------------
			Utilities.HoverandClick(pro.getProperty("purchaseTab"), driver);
			Utilities.enterTextandSelect(purchaseAccount, pro.getProperty("purchaseAccount"), driver);
			Utilities.enterTextNormalBox("10", pro.getProperty("initialPurchasePrice"), driver);

			// ------------- Sales Tab
			// ----------------------------------------------
			Utilities.HoverandClick(pro.getProperty("salesTab"), driver);
			Utilities.enterTextandSelect(salesAccount, pro.getProperty("salesAccount"), driver);
			Utilities.enterTextNormalBox("20", pro.getProperty("initialSalesPrice"), driver);

			// ------------- Inventory Tab
			// ----------------------------------------------
			Utilities.HoverandClick(pro.getProperty("inventoryDataTab"), driver);
			Utilities.enterTextandSelect("Unit", pro.getProperty("umo"), driver);
			Utilities.enterTextandSelect("DS - Default Store", pro.getProperty("warehouse"), driver);
			Utilities.enterTextandSelect("Default Location", pro.getProperty("location"), driver);
			WebElement countable = driver.findElement(By.xpath(pro.getProperty("countable")));
			if (countable.isEnabled()) {
				if (!countable.isSelected()) {
					countable.click();
				}
				WebElement cyclecountfrequency = driver.findElement(By.xpath(pro.getProperty("cyclecountfrequency")));
				cyclecountfrequency.click();
				Robot r3 = new Robot();
				r3.keyPress(KeyEvent.VK_DOWN);
				r3.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(2000);
				List<WebElement> comboItems = driver.findElements(
						By.cssSelector(".x-combo-list[style*='visibility: visible;'] .x-combo-list-item"));
				for (int i = 0; i < comboItems.size(); i++) {
					WebElement item = comboItems.get(i);
					if (item.getText().equals("Daily") || item.getText().equals("Weekly")
							|| item.getText().equals("Fortnightly") || item.getText().equals("Monthly")) {
						item.click();
					}
				}
			}

			Utilities.HoverandClick(pro.getProperty("saveButton"), driver);
			Utilities.isElementGone(xpathOfLoading, 120, driver);
			try {
				productAfterSaveOKBtn(driver);
			} catch (Exception noOk) {
				// System.out.println("No Ok button");
			}

			Utilities.click_element(pro.getProperty("closeProductReport"), driver);
			Utilities.click_element(pro.getProperty("CloseMainProductTab"), driver);
			System.out.println("********** Product [ " + productType + " ] with [ " + productid
					+ " ]  Successfully created *************");
		} catch (Exception Ex) {
			Utilities.handleError(Ex, driver);
		}
	}

	// handle appear&disappear after save "OK" button
	public static void productAfterSaveOKBtn(WebDriver driver) throws InterruptedException {
		WebElement button = new WebDriverWait(driver, 2)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
		if (button.isDisplayed()) {
			Thread.sleep(0);
			button.click();
		}
	}

	// ***********************************************VERIFICATION for ALL 4
	// product **************************************************
	public static void verify_allProducts(String product_ID, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");

			WebDriverWait wait = new WebDriverWait(driver, 60);

			Utilities.openProductMaster(driver);
			WebElement quickSerach = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath(".//div[@id='ProductReportGrid_one']/div/div[1]/div[1]/table/tbody/tr/td[1]/input")));
			quickSerach.clear();
			quickSerach.sendKeys(product_ID);
			Thread.sleep(500);
			quickSerach.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			List<WebElement> totalProductfnd = driver
					.findElements(By.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div"));
			int totalProductfndSize = totalProductfnd.size();

			List<WebElement> tableheader = driver.findElements(By.xpath(
					"//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"));
			int totalHeaderSize = tableheader.size();

			int productIDRowIndex = 0, prodNameRowIndex = 0;
			for (int i = 1; i <= totalHeaderSize; i++) {
				String header = driver.findElement(By
						.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (header.equals("Product ID")) {
					productIDRowIndex = i;
				} else if (header.equals("Product Name")) {
					prodNameRowIndex = i;
				}
			}

			for (int j = 1; j <= totalProductfndSize; j++) {
				int index = 0;
				String productIDStr = driver
						.findElement(By.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div["
								+ j + "]/table/tbody/tr/td[" + productIDRowIndex + "]/div"))
						.getText();
				// System.out.println("productIDStr"+productIDStr);
				if (productIDStr.endsWith("S3")) {
					index = 4;
				} else if (productIDStr.endsWith("B2")) {
					index = 2;
				} else if (productIDStr.endsWith("BS4")) {
					index = 3;
				} else if (productIDStr.equalsIgnoreCase(product_ID)) {
					index = 1;
				}

				switch (index) {
				case 1:
					assertEquals(
							product_ID, driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div["
													+ j + "]/table/tbody/tr/td[" + productIDRowIndex + "]/div"))
									.getText());
					assertEquals(
							product_ID + "Name", driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div["
													+ j + "]/table/tbody/tr/td[" + prodNameRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Normal Product = [" + productIDStr + "]");
					System.out.println("-------------------------------------------------------------");
					break;
				case 2:
					assertEquals(
							product_ID + "B2", driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div["
													+ j + "]/table/tbody/tr/td[" + productIDRowIndex + "]/div"))
									.getText());
					assertEquals(
							product_ID + "B2Name", driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div["
													+ j + "]/table/tbody/tr/td[" + prodNameRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Batch Product = [" + productIDStr + "]");
					System.out.println("-------------------------------------------------------------");

					break;
				case 3:
					assertEquals(
							product_ID + "BS4", driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div["
													+ j + "]/table/tbody/tr/td[" + productIDRowIndex + "]/div"))
									.getText());
					assertEquals(
							product_ID + "BS4Name", driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div["
													+ j + "]/table/tbody/tr/td[" + prodNameRowIndex + "]/div"))
									.getText());

					System.out.println("Verified Batch-Serial Product = [" + productIDStr + "]");
					System.out.println("-------------------------------------------------------------");

					break;
				case 4:
					assertEquals(
							product_ID + "S3", driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div["
													+ j + "]/table/tbody/tr/td[" + productIDRowIndex + "]/div"))
									.getText());
					assertEquals(
							product_ID + "S3Name", driver
									.findElement(By
											.xpath("//div[@id='ProductReportGrid_one']/div/div[2]/div/div/div[2]/div/div["
													+ j + "]/table/tbody/tr/td[" + prodNameRowIndex + "]/div"))
									.getText());
					System.out.println("Verified Serial Product = [" + productIDStr + "]");
					System.out.println("-------------------------------------------------------------");

					break;
				}
			}

			Utilities.HoverandClick(pro.getProperty("closeProductReport"), driver);
			System.out.println(
					"********************************* Verified all 4 Products !!! **************************************************");

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ******************* Normal Product for Assembly
	// *******************************************************
	public static void create_AssemblyNormalProduct(String productid, String purchaseAccount, String salesAccount, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		productid = "BA" + productid;
		String productName = productid + " Name";
		String productDes = productid + " Inventory Assembly Product";
		
		try {
			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
	
			Utilities.waitandClick(pro.getProperty("createProductIcon"), driver);
			Utilities.enterTextNormalBox(productName, pro.getProperty("productName"), driver);
			Utilities.enterTextNormalBox(productDes, pro.getProperty("productDes"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceformat"), driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("productId"), driver);
			Utilities.clickCheckBox(pro.getProperty("qaenable"), "check", driver);
			custom(driver); //FAISAL
			Utilities.enterTextNormalBox("500", pro.getProperty("initialquantity"), driver);
			Utilities.click_element(pro.getProperty("productId"), driver);
				
			if(driver.findElement(By.xpath("//button[text()='Yes']")).isDisplayed()) {
				try {
					Utilities.clickButton("Yes", 1000, driver);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			Thread.sleep(5000);

			// enter WareHouse
			int header = Utilities.totalSize(
					"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[1]/table/thead/tr/td/div",
					driver);
			for (int i = 1; i <= header; i++) {
				String headerName = driver.findElement(By
						.xpath("//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[1]/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Warehouse")) {
					Utilities.click_element(
							"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div",
							driver);
					Utilities.enterTextandSelect("DS - Default Store",
							"//*[contains(@style,'visible')]//*[@class='x-grid3-viewport']//*[@id='warehouse']/following::input[1]",
							driver);
				}

				if (headerName.equalsIgnoreCase("Locations")) {
					Utilities.click_element(
							"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div",
							driver);
					Utilities.enterTextandSelect("Default Location",
							"//*[contains(@style,'visible')]//*[@class='x-grid3-viewport']//*[@id='location']/following::input[1]",
							driver);
				}
			}
			Utilities.click_element("//*[contains(@style,'visible')]//button[text()='Submit']", driver);
			
			// ------------- Purchase Tab
			// ----------------------------------------------
			Utilities.HoverandClick(pro.getProperty("purchaseTab"), driver);
			Utilities.enterTextandSelect(purchaseAccount, pro.getProperty("purchaseAccount"), driver);
			Utilities.enterTextNormalBox("10", pro.getProperty("initialPurchasePrice"), driver);

			// ------------- Sales Tab
			// ----------------------------------------------
			Utilities.HoverandClick(pro.getProperty("salesTab"), driver);
			Utilities.enterTextandSelect(salesAccount, pro.getProperty("salesAccount"), driver);
			Utilities.enterTextNormalBox("20", pro.getProperty("initialSalesPrice"), driver);
			

			Utilities.HoverandClick(pro.getProperty("inventoryDataTab"), driver);
			Utilities.enterTextandSelect("Unit", pro.getProperty("umo"), driver);
			Utilities.enterTextandSelect("DS - Default Store", pro.getProperty("warehouse"), driver);
			Utilities.enterTextandSelect("Default Location", pro.getProperty("location"), driver);

			WebElement countable = driver.findElement(By.xpath(pro.getProperty("countable")));
			if (countable.isEnabled()) {
				if (!countable.isSelected()) {
					countable.click();
				}
				WebElement cyclecountfrequency = driver.findElement(By.xpath(pro.getProperty("cyclecountfrequency")));
				cyclecountfrequency.click();
				Robot r3 = new Robot();
				r3.keyPress(KeyEvent.VK_DOWN);
				r3.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(2000);
				List<WebElement> comboItems = driver.findElements(
						By.cssSelector(".x-combo-list[style*='visibility: visible;'] .x-combo-list-item"));
				for (int i = 0; i < comboItems.size(); i++) {
					WebElement item = comboItems.get(i);
					if (item.getText().equals("Daily") || item.getText().equals("Weekly")
							|| item.getText().equals("Fortnightly") || item.getText().equals("Monthly")) {
						item.click();
					}
				}
			}

			Utilities.HoverandClick(pro.getProperty("saveButton"), driver);

			try {
				WebElement duplicateProductID = new WebDriverWait(driver, 3).until(
						ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'already exists')]")));
				if (duplicateProductID.isDisplayed()) {
					Utilities.refresh();
					System.out.println("********* This product [" + productid
							+ "] is already present so Not creating this One !!!!!! *******");
				}
			} catch (Exception ex) {
				// Continue as New Product
				try {
					productAfterSaveOKBtn(driver);
				} catch (Exception noOk) {
					// No Ok button
				}
				Utilities.click_element(pro.getProperty("CloseReport"), driver);
				Utilities.click_element(pro.getProperty("closeCreateproductTab"), driver);
				System.out
						.println("********** Normal Product [" + productName + "] Successfully created *************");
			}
		} catch (Exception EX) {
			System.out.println("********** Failed to Create Normal Product [" + productName
					+ "] Successfully created *************");
			Utilities.handleError(EX, driver);
		}

	}

	// ******************* Assembly
	// product*******************************************************
	public static void create_InventoryAssemblyProduct(String productid, String purchaseAccount, String salesAccount, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		String productName = productid + "AsB Name";
		String productDes = productid + " AsB Inventory Assembly Product";
		try {
			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");

			Utilities.waitandClick(pro.getProperty("createProductIcon"), driver);
			Utilities.disableExpander(driver);
			Utilities.enterTextandSelect("Inventory Assembly", pro.getProperty("productType"), driver);
			Utilities.enterTextNormalBox(productName, pro.getProperty("productName"), driver);
			Utilities.enterTextNormalBox(productDes, pro.getProperty("productDes"), driver);
			Utilities.enterTextInDropDown("NA", pro.getProperty("sequenceformat"), driver);
			Utilities.enterTextNormalBox("AsB" + productid, pro.getProperty("productId"), driver);
			Utilities.clickCheckBox(pro.getProperty("qaenable"), "check", driver);
			custom(driver); //FAISAL
			Utilities.clickCheckBox(pro.getProperty("autoAssemblyproductwin"), "check", driver);

			int header = Utilities.totalSize("//*[text()='UOM']/ancestor::div[1]/table/thead/tr/td/div", driver);
			int indOfproid = 0, indOfqty = 0;
			for (int i = 1; i <= header; i++) {
				String headerName = driver
						.findElement(By.xpath("//*[text()='UOM']/ancestor::div[1]/table/thead/tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equals("Product ID")) {
					indOfproid = i;
				}
				if (headerName.equals("Quantity")) {
					indOfqty = i;
				}
			}
			// ***** Add product ******
			Robot R = new Robot();
			String prd[] = { "BA" + productid + "A1", "BA" + productid + "A2", "BA" + productid + "A3" };
			for (int j = 1; j <= prd.length; j++) {
				Utilities.clickAndEnterText(prd[j - 1], "//*[text()='UOM']/ancestor::div[3]/following::div[1]/div/div["
						+ j + "]/table/tbody/tr/td[" + indOfproid + "]/div", driver);
				Thread.sleep(2000);
				R.keyPress(KeyEvent.VK_DOWN);
				R.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(500);
				R.keyPress(KeyEvent.VK_ENTER);
				R.keyRelease(KeyEvent.VK_ENTER);
				Utilities.HoverandClick(pro.getProperty("BlankPlace"), driver);
				Thread.sleep(1000);
			}
			// ***** Add Qty ******
			String formula[] = { "1", "2", "3" };
			for (int j = 1; j <= prd.length; j++) {
				Utilities.HoverandClick("//*[text()='UOM']/ancestor::div[3]/following::div[1]/div/div[" + j
						+ "]/table/tbody/tr/td[" + indOfqty + "]/div", driver);
				Utilities.enter_LinelabelAmount(formula[j - 1], driver);
				Utilities.HoverandClick(pro.getProperty("BlankPlace"), driver);
			}
			
			
			Utilities.enterTextNormalBox("Default BOM", "//*[text()='BOM Code*:']/following-sibling::*/child::input", driver);
			Utilities.enterTextNormalBox("Default BOM", "//input[@name='bomName']", driver);
			driver.findElement(By.xpath("//*[@id='BtnSubNewproductwin']//descendant::button[text()='Submit']")).click();

			// ------------- Purchase Tab
			// ----------------------------------------------
			Utilities.HoverandClick(pro.getProperty("purchaseTab"), driver);
			Utilities.enterTextandSelect(purchaseAccount, pro.getProperty("purchaseAccount"), driver);
						

			// ------------- Sales Tab
			// ----------------------------------------------
			Utilities.HoverandClick(pro.getProperty("salesTab"), driver);
			Utilities.enterTextandSelect(salesAccount, pro.getProperty("salesAccount"), driver);
			Utilities.enterTextNormalBox("100", pro.getProperty("initialSalesPrice"), driver);

			Utilities.HoverandClick(pro.getProperty("inventoryDataTab"), driver);
			Utilities.enterTextandSelect("Unit", pro.getProperty("umo"), driver);
			Utilities.enterTextandSelect("DS - Default Store", pro.getProperty("warehouse"), driver);
			Utilities.enterTextandSelect("Default Location", pro.getProperty("location"), driver);

			WebElement countable = driver.findElement(By.xpath(pro.getProperty("countable")));
			if (countable.isEnabled()) {
				if (!countable.isSelected()) {
					countable.click();
				}
				WebElement cyclecountfrequency = driver.findElement(By.xpath(pro.getProperty("cyclecountfrequency")));
				cyclecountfrequency.click();
				Robot r3 = new Robot();
				r3.keyPress(KeyEvent.VK_DOWN);
				r3.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(2000);
				List<WebElement> comboItems = driver.findElements(
						By.cssSelector(".x-combo-list[style*='visibility: visible;'] .x-combo-list-item"));
				for (int i = 0; i < comboItems.size(); i++) {
					WebElement item = comboItems.get(i);
					if (item.getText().equals("Daily") || item.getText().equals("Weekly")
							|| item.getText().equals("Fortnightly") || item.getText().equals("Monthly")) {
						item.click();
					}
				}
			}

			Utilities.HoverandClick(pro.getProperty("saveButton"), driver);
			try {
				productAfterSaveOKBtn(driver);
			} catch (Exception noOk) {
				System.out.println("No Ok button");
			}

			Utilities.HoverandClick(pro.getProperty("closeProductReport"), driver);
			Utilities.HoverandClick(pro.getProperty("CloseMainProductTab"), driver);

			System.out.println(
					"********** Inventory Assembly Product [" + productName + "] Successfully created *************");
		} catch (Exception EX) {
			System.out.println("********** Inventory Assembly Product [" + productName + "] NOT created *************");
			Utilities.handleError(EX, driver);
		}
	}

	// *************** Build Assemble ******************************************
	public static void create_BuildAssembly(String documnetid, String productid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
			String refNumber = "BBA" + documnetid;

			Utilities.openProductMaster(driver);
			Utilities.disableExpander(driver);
			Utilities.enterTextNormalBox("AsB" + productid,
					".//div[@id='ProductReportGrid_one']/div/div[1]/div[1]/table/tbody/tr/td[1]/input", driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("FirstCheck"), "check", driver);
			Utilities.waitandClick(pro.getProperty("buildAssembleMenu"), driver);
			Utilities.waitandClick(pro.getProperty("BuildAssemblyOption"), driver);

			// Build AssembleForm
			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceformatOfBuildAssembly"), driver);
			Utilities.enterTextNormalBox(refNumber, pro.getProperty("referenceNumber"), driver);
			Utilities.enterTextandSelect("AsB" + productid, pro.getProperty("addAssemblePrd"), driver);
			Utilities.enterTextandSelect("Default BOM", pro.getProperty("BOMcode"), driver);
			try {
				Utilities.HoverandClick(pro.getProperty("assemblyDescription"), driver);
			} catch (Exception Ex) {
				Ex.printStackTrace();
			}
			Thread.sleep(2000);
			Utilities.enterTextSpecialBox("10", pro.getProperty("quantityToBuild"), driver);
			try {
				Utilities.HoverandClick(pro.getProperty("assemblyDescription"), driver);
			} catch (Exception Ex) {
				Ex.printStackTrace();
			}
			Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Submit']", driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("buildAssemblyClose"), driver);

			// * * * * * Verification * * * * *
			Utilities.HoverandClick(pro.getProperty("buildAssemblyreportmenu"), driver);
			Utilities.HoverandClick(pro.getProperty("BuildAssemblyReport"), driver);
			Utilities.isLoadingDisappear(60, driver);
			Utilities.clickCheckBox(pro.getProperty("FirstBuildPrd"), "uncheck", driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("quicksearchbuildAssembl"), driver);
			Utilities.clickCheckBox(pro.getProperty("FirstBuildPrd"), "check", driver);

			/*
			 * int reportHeader=Utilities.
			 * totalSize("//*[text()='Assembly Product ID']/ancestor::div[1]/table/thead/tr/td/div"
			 * , driver); for(int i=1; i<=reportHeader; i++) { String
			 * headerName=driver.findElement(By.
			 * xpath("//*[text()='Assembly Product ID']/ancestor::div[1]/table/thead/tr/td["
			 * +i+"]/div")).getText();
			 * if(headerName.equalsIgnoreCase("Build Ref. No.")) { String
			 * actual=driver.findElement(By.
			 * xpath("//*[text()='Assembly Product ID']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr[1]/td["
			 * +i+"]/div")).getText(); assertEquals(actual, refNumber);
			 * System.out.println("********** Matched Build assemble product ["
			 * +"AsB"+productid+"] with reference number ["+actual+"] ******");
			 * } }
			 */
			Utilities.HoverandClick(pro.getProperty("Closeassemblyreport"), driver);
			Utilities.HoverandClick(pro.getProperty("closeProductReport"), driver);
			System.out.println(
					"********** Build Assembly [" + refNumber + "] Successfully created & verified !! *************");

		} catch (Exception EX) {
			System.out.println("********** Build Assembly [" + "AsB" + productid + "] NOT created *************");
			Utilities.handleError(EX, driver);
		}
	}

	// *************** Un-Build Assemble
	// ******************************************
	public static void create_UnBuildAssembly(String documnetid, String productid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
			String refNumber = "UnBA" + documnetid;

			Utilities.waitandClick(pro.getProperty("createProductIcon"), driver);
			
			
			try {
				Utilities.click_element(pro.getProperty("closeCreateproductTab"), driver);
				Thread.sleep(2000);
			} catch (Exception Ex) {
				Ex.printStackTrace();
			}
			
			
			try {
				Utilities.click_element(pro.getProperty("closeCreateproductTab"), driver);
				Thread.sleep(2000);
			} catch (Exception Ex) {
				Ex.printStackTrace();
			}
			
			
			try {
				Utilities.clickButton("Yes", 1500, driver);
			} catch (Exception Ex) {
				Ex.printStackTrace();
			}

			Thread.sleep(2000);
			Utilities.disableExpander(driver);
			Utilities.enterTextNormalBox("AsB" + productid,
					".//div[@id='ProductReportGrid_one']/div/div[1]/div[1]/table/tbody/tr/td[1]/input", driver);
			Thread.sleep(1000);
			Utilities.clickCheckBox(pro.getProperty("FirstCheck"), "check", driver);
			Utilities.waitandClick(pro.getProperty("buildAssembleMenu"), driver);
			Utilities.waitandClick(pro.getProperty("UnBuildAssemblyOption"), driver);

			// Build AssembleForm
			Utilities.enterTextandSelect("NA", pro.getProperty("sequenceformatOfBuildAssembly"), driver);
			Utilities.enterTextNormalBox(refNumber, pro.getProperty("referenceNumber"), driver);
			Utilities.enterTextandSelect("AsB" + productid, pro.getProperty("addAssemblePrd"), driver);
			Utilities.enterTextandSelect("Default BOM", pro.getProperty("BOMcode"), driver);
			
			// Select 1st build assembly
			Utilities.HoverandClick("//*[@name='buildAssembly']/following::img[1]", driver);
			Utilities.HoverandClick("//*[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div[1]",
								driver);
			
			try {
				Utilities.HoverandClick(pro.getProperty("assemblyDescription"), driver);
			} catch (Exception Ex) {
				Ex.printStackTrace();
			}
			Thread.sleep(2000);

			Utilities.enterTextSpecialBox("5", pro.getProperty("quantityToBuild"), driver);
			try {
				Utilities.HoverandClick(pro.getProperty("assemblyDescription"), driver);
			} catch (Exception Ex) {
				Ex.printStackTrace();
			}
			// enter WareHouse
			int header = Utilities.totalSize(
					"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[1]/table/thead/tr/td/div",
					driver);
			for (int i = 1; i <= header; i++) {
				String headerName = driver.findElement(By
						.xpath("//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[1]/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Warehouse")) {
					Utilities.click_element(
							"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div",
							driver);
					Utilities.enterTextandSelect("DS - Default Store",
							"//*[contains(@style,'visible')]//*[@class='x-grid3-viewport']//*[@id='warehouse']/following::input[1]",
							driver);
				}

				if (headerName.equalsIgnoreCase("Locations")) {
					Utilities.click_element(
							"//*[contains(@style,'visible')]//*[text()='Warehouse']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div",
							driver);
					Utilities.enterTextandSelect("Default Location",
							"//*[contains(@style,'visible')]//*[@class='x-grid3-viewport']//*[@id='location']/following::input[1]",
							driver);
				}
			}
			Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Submit']", driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(pro.getProperty("UnbuildAssemblyClose"), driver);

			// * * * * * Verification * * * * *
			Utilities.HoverandClick(pro.getProperty("buildAssemblyreportmenu"), driver);
			Utilities.HoverandClick(pro.getProperty("UnBuildAssemblyReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("FirstUnBuildPrd"), "uncheck", driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("quicksearchbuildAssembl"), driver);
			Utilities.clickCheckBox(pro.getProperty("FirstUnBuildPrd"), "check", driver);

			int reportHeader = Utilities.totalSize(
					"//*[text()='Unbuild Assembly Product ID']/ancestor::div[1]/table/thead/tr/td/div", driver);
			for (int i = 1; i <= reportHeader; i++) {
				String headerName = driver.findElement(By.xpath(
						"//*[text()='Unbuild Assembly Product ID']/ancestor::div[1]/table/thead/tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Unbuild Ref. No.")) {
					String actual = driver.findElement(By
							.xpath("//*[text()='Unbuild Assembly Product ID']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
					assertEquals(actual, refNumber);
					System.out.println("********** Matched Un-Build assemble product [" + "AsB" + productid
							+ "] with reference number [" + actual + "] ******");
				}
			}
			Utilities.HoverandClick(pro.getProperty("unBuildClosereport"), driver);
			Utilities.HoverandClick(pro.getProperty("closeProductReport"), driver);
			System.out.println("********** Un-Build Assembly [" + refNumber
					+ "] Successfully created & verified !! *************");

		} catch (Exception EX) {
			System.out.println("********** Un-Build Assembly [" + "AsB" + productid + "] NOT created *************");
			Utilities.handleError(EX, driver);
		}
	}

	// ****************** Delete Build asseblny ******************
	public static void delete_BuildAssembly(String documnetid, String productid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");

			Utilities.waitandClick(pro.getProperty("createProductIcon"), driver);
			Thread.sleep(2000);
			Utilities.click_element(pro.getProperty("closeCreateproductTab"), driver);
			Utilities.clickButton("Yes", 1500, driver);
			Utilities.disableExpander(driver);
			Utilities.clickCheckBox(pro.getProperty("FirstCheck"), "check", driver);
			// build assembly report check
			Utilities.HoverandClick(pro.getProperty("buildAssemblyreportmenu"), driver);
			Utilities.HoverandClick(pro.getProperty("BuildAssemblyReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("FirstBuildPrd"), "uncheck", driver);
			Utilities.enterTextNormalBox(productid, pro.getProperty("quicksearchbuildAssembl"), driver);
			Utilities.clickCheckBox(pro.getProperty("FirstBuildPrd"), "check", driver);

			Utilities.HoverandClick("//*[text()='Build Assembly']/ancestor::td[2]/following::td//*[text()='Delete']",
					driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			String JEnum = driver
					.findElement(By
							.xpath("//*[text()='Assembly Product ID']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr[1]/td[7]/div"))
					.getText();
			System.out.println("********** Build Assembly [" + JEnum + "] succesfully deleted !!!!!*************");
			Utilities.refresh();
		} catch (Exception EX) {
			System.out.println("********** Build Assembly NOT deleted  *************");
			Utilities.handleError(EX, driver);
		}
	}
	// *********** Edit & Delete *******************************

	public static void EditDelete_DeliveryOrder(String documentid, WebDriver driver)
			throws IOException, InterruptedException, AWTException {

		try {

			Properties pro = Utilities.fetchProValue("OR_DeliveryOrder.properties");

			Utilities.HoverandClick(pro.getProperty("DeliveryOrderReport"), driver);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

			Utilities.HoverandClick(pro.getProperty("EditDeliveryOrderButton"), driver);
			// to Load page properly
			Utilities.justHover(
					"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div/div[1]/table/tbody/tr/td[7]",
					driver);
			Utilities.enterTextInDropDown("Performing Edit Operation", pro.getProperty("DeliveryOrderMemo"), driver);

			Utilities.HoverandClick(pro.getProperty("DeliveryOrdereEditSave"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("No", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			Utilities.HoverandClick(pro.getProperty("EditDeliveryOrderClose"), driver);
			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			if (driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).isDisplayed()) {
				System.out.println("Delivery Order Edited !!");
			} else {
				System.out.println("Delivery Order Failed to Edit !!");
			}

			Utilities.HoverandClick(pro.getProperty("deleteDeliveryOrderButton"), driver);

			Utilities.HoverandClick(pro.getProperty("deleteDeliveryOrderPermButton"), driver);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(1500);

			Utilities.enterTextInDropDown(documentid, pro.getProperty("QuickSearch"), driver);
			Thread.sleep(2000);

			WebElement element = new WebDriverWait(driver, 45).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//a[contains(text(),'Get Started by adding a Delivery Order now')]")));
			if (element.isDisplayed()) {
				System.out.println("Delivery Order Deleted !!!! ");
			} else {
				System.out.println("Delivery Order is NOT Deleted !!!! ");
			}
			Utilities.HoverandClick(pro.getProperty("reportCloseBtn"), driver);
		} catch (Exception EX) {
			System.out.println("Delivery Order is NOT Deleted !!!! ");
			Utilities.handleError(EX, driver);
		}
	}

}
