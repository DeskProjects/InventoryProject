package krawler.erp.IndianGST;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class GST_Purchase_Invoice {

	public static void createPurchaseInvoice_UnRegistered_Import(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException {

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {

			// String Vendor_Code = "VC00008" ;
			// String Product_ID = "PID00010" ;
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			// JavascriptExecutor executor = (JavascriptExecutor)driver;

			// executor.executeScript("document.getElementById("").value='55555';");

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			Thread.sleep(2000);
			SelectProduct.click();

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			// Utilities.clickCheckBox("//*[contains(text(),'has been saved
			// successfully')]", "uncheck", driver);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			// System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			// Fetch values

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();

			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);

			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();

			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

			Thread.sleep(500);

			String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount"))).getText();

			System.out.println("Total Amount:- " + Form_Total_Amount);

			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1000);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -

			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_4_A_1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_4_A_1"))));
			View_4_A_1.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();

			Thread.sleep(2000);

			// 3B_Total Tax Amount

			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();

			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);

			Thread.sleep(500);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();

			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
					.getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);

			Thread.sleep(1000);

			if ((Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
					&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
							&& (Form_Total_Amount.equals(GSTR3B_Total_Amount))))) {
				System.out.println(
						" All values of Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 4.A.1 section");
			}

			else {
				System.out.println("Values are Not Matching in 4.A.1 section");

			}

			driver.navigate().refresh();

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

	public static void createPurchaseInvoice_UnRegistered_Import_Service(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException {

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {

			// String Vendor_Name = "VC00002" ;
			// String Product_ID = "PID00025" ;
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();
			Thread.sleep(1000);

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			// JavascriptExecutor executor = (JavascriptExecutor)driver;

			// executor.executeScript("document.getElementById("").value='55555';");

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			Thread.sleep(2000);
			SelectProduct.click();

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			// Utilities.clickCheckBox("//*[contains(text(),'has been saved
			// successfully')]", "uncheck", driver);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			// System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			// Fetch values

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();

			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);

			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();

			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

			Thread.sleep(500);

			String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount"))).getText();

			System.out.println("Total Amount:- " + Form_Total_Amount);

			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1000);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -

			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_4_A_2 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_4_A_2"))));
			View_4_A_2.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();

			Thread.sleep(2000);

			// 3B_Total Tax Amount

			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();

			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);

			Thread.sleep(500);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();

			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);

			Thread.sleep(1000);

			String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
					.getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);

			Thread.sleep(1000);

			if ((Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
					&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
							&& (Form_Total_Amount.equals(GSTR3B_Total_Amount))))) {
				System.out.println(
						" All values of Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 4.A.2 section");
			}

			else {
				System.out.println("Values are Not Matching in 4.A.2 section");

			}

			driver.navigate().refresh();

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

	public static void createRCMPurchaseInvoice_Registered_IntraState(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement RCM_In_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("RCM_In_PI"))));
			RCM_In_PI.click();
			Thread.sleep(500);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(2000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(1000);

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			Thread.sleep(2000);

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();

			Thread.sleep(2000);

			// Fetch values

			// if (vendorid.equalsIgnoreCase("VC000012"))

			String Form_SGST = "";
			String Form_CGST = "";
			String Form_CESS = "";

			// HashMap<String,String> hs = new HashMap<String,String>();

			for (int i = 1; i <= 3; i++) {
				String message = driver.findElement(By
						.xpath("//*[text()='Output CGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
						.getText();

				if (message.equalsIgnoreCase("Output SGST Amount")) {
					Form_SGST = driver.findElement(By.xpath(
							"//*[text()='Output SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText(); // A B C
					System.out.println("SGST Amount" + Form_SGST);

					// hs.put("Input SGST Amount", Form_SGST);

				}

				else if (message.equalsIgnoreCase("Output CGST Amount")) {
					Form_CGST = driver.findElement(By.xpath(
							"//*[text()='Output SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText(); // B A A
					System.out.println("CGST Amount" + Form_CGST);

					// hs.put("Input CGST Amount", Form_CGST);

				}

				else if (message.equalsIgnoreCase("CESS Amount")) {
					Form_CESS = driver.findElement(By.xpath(
							"//*[text()='Output SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText(); // C C B
					System.out.println("CESS Amount" + Form_CESS);

					// hs.put("CESS Amount", Form_CESS);
				}

			}

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();
			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();
			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1500);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			// GSTR3B report click - (//*[@class='x-tree-node']) [59]/div/img[2]

			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -

			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_4_A_3 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_4_A_3"))));
			View_4_A_3.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_SGST Amount

			String GSTR3B_SGST_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_SGST_Amount")))
					.getText();
			System.out.println("GSTR3B SGST Amount:- " + GSTR3B_SGST_Amount);
			Thread.sleep(500);

			// 3B_CGST Amount

			String GSTR3B_CGST_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CGST_Amount")))
					.getText();
			System.out.println("GSTR3B CGST Amount:- " + GSTR3B_CGST_Amount);
			Thread.sleep(500);

			// 3B_CESS Amount

			String GSTR3B_CESS_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CESS_Amount")))
					.getText();
			System.out.println("GSTR3B CESS Amount:- " + GSTR3B_CESS_Amount);
			Thread.sleep(500);

			// 3B_Total Tax Amount

			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);
			Thread.sleep(500);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);
			Thread.sleep(500);

			if (Form_SGST.equals(GSTR3B_SGST_Amount) && (Form_CGST.equals(GSTR3B_CGST_Amount)
					&& (Form_CESS.equals(GSTR3B_CESS_Amount) && (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
							&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)))))) {
				System.out.println(
						" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 4.A.3 section");
			} else {
				System.out.println("Values are Not Matching in 4.A.3 section");
			}

			driver.navigate().refresh();
		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

	public static void createRCMPurchaseInvoice_Registered_InterState(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement RCM_In_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("RCM_In_PI"))));
			RCM_In_PI.click();
			Thread.sleep(500);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(2000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(1000);

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			Thread.sleep(2000);

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();

			Thread.sleep(2000);

			// Fetch values

			String Form_IGST = null;
			String Form_CESS = null;

			// HashMap<String,String> hs = new HashMap<String,String>();

			for (int i = 1; i <= 2; i++) {
				String message = driver.findElement(By
						.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
						.getText();

				if (message.equalsIgnoreCase("Output IGST Amount")) {
					Form_IGST = driver.findElement(By.xpath(
							"//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText();
					System.out.println("IGST Amount" + Form_IGST);

					// hs.put("Input IGST Amount", Form_SGST);

				}

				else if (message.equalsIgnoreCase("CESS Amount")) {
					Form_CESS = driver.findElement(By.xpath(
							"//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText();
					System.out.println("CESS1 Amount" + Form_CESS);

					// hs.put("CESS Amount", Form_CESS);
				}

			}

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();
			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();
			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1500);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			// GSTR3B report click - (//*[@class='x-tree-node']) [59]/div/img[2]

			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -

			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_4_A_3 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_4_A_3"))));
			View_4_A_3.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_IGST Amount

			String GSTR3B_IGST_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_IGST_Amount")))
					.getText();
			System.out.println("GSTR3B IGST Amount:- " + GSTR3B_IGST_Amount);
			Thread.sleep(500);

			// 3B_CESS Amount

			String GSTR3B_CESS_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CESS_Amount")))
					.getText();
			System.out.println("GSTR3B CESS Amount:- " + GSTR3B_CESS_Amount);
			Thread.sleep(500);

			// 3B_Total Tax Amount

			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);
			Thread.sleep(500);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);
			Thread.sleep(500);

			if (Form_IGST.equals(GSTR3B_IGST_Amount)
					&& (Form_CESS.equals(GSTR3B_CESS_Amount) && (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
							&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount))))) {

				System.out.println(
						" All values of IGST, CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 4.A.3 section");

			}

			else

			{
				System.out.println("Values are Not Matching in 4.A.3 section");
			}

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

	public static void createRCMPurchaseInvoice_Registered_IntraState_SalesSide(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement RCM_In_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("RCM_In_PI"))));
			RCM_In_PI.click();
			Thread.sleep(500);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(2000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(1000);

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			Thread.sleep(2000);

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();

			Thread.sleep(2000);

			// Fetch values

			// if (vendorid.equalsIgnoreCase("VC000012"))

			String Form_SGST = "";
			String Form_CGST = "";
			String Form_CESS = "";

			// HashMap<String,String> hs = new HashMap<String,String>();

			for (int i = 1; i <= 3; i++) {
				String message = driver.findElement(By
						.xpath("//*[text()='Output CGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
						.getText();

				if (message.equalsIgnoreCase("Output SGST Amount")) {
					Form_SGST = driver.findElement(By.xpath(
							"//*[text()='Output SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText(); // A B C
					System.out.println("SGST Amount" + Form_SGST);

					// hs.put("Input SGST Amount", Form_SGST);

				}

				else if (message.equalsIgnoreCase("Output CGST Amount")) {
					Form_CGST = driver.findElement(By.xpath(
							"//*[text()='Output SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText(); // B A A
					System.out.println("CGST Amount" + Form_CGST);

					// hs.put("Input CGST Amount", Form_CGST);

				}

				else if (message.equalsIgnoreCase("CESS Amount")) {
					Form_CESS = driver.findElement(By.xpath(
							"//*[text()='Output SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText(); // C C B
					System.out.println("CESS Amount" + Form_CESS);

					// hs.put("CESS Amount", Form_CESS);
				}

			}

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();
			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();
			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1500);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			// GSTR3B report click - (//*[@class='x-tree-node']) [59]/div/img[2]

			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -

			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_3_1_D = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_1_D"))));
			View_3_1_D.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_SGST Amount

			String GSTR3B_SGST_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_SGST_Amount")))
					.getText();
			System.out.println("GSTR3B SGST Amount:- " + GSTR3B_SGST_Amount);
			Thread.sleep(500);

			// 3B_CGST Amount

			String GSTR3B_CGST_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CGST_Amount")))
					.getText();
			System.out.println("GSTR3B CGST Amount:- " + GSTR3B_CGST_Amount);
			Thread.sleep(500);

			// 3B_CESS Amount

			String GSTR3B_CESS_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CESS_Amount")))
					.getText();
			System.out.println("GSTR3B CESS Amount:- " + GSTR3B_CESS_Amount);
			Thread.sleep(500);

			// 3B_Total Tax Amount

			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);
			Thread.sleep(500);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);
			Thread.sleep(500);

			if (Form_SGST.equals(GSTR3B_SGST_Amount) && (Form_CGST.equals(GSTR3B_CGST_Amount)
					&& (Form_CESS.equals(GSTR3B_CESS_Amount) && (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
							&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)))))) {
				System.out.println(
						" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 3.1.D  section");
			} else {
				System.out.println("Values are Not Matching in 3.1.D  section");
			}

			driver.navigate().refresh();
		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

	public static void createRCMPurchaseInvoice_Registered_InterState_SalesSide(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement RCM_In_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("RCM_In_PI"))));
			RCM_In_PI.click();
			Thread.sleep(500);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(2000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(1000);

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			Thread.sleep(2000);

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();

			Thread.sleep(2000);

			// Fetch values

			String Form_IGST = null;
			String Form_CESS = null;

			// HashMap<String,String> hs = new HashMap<String,String>();

			for (int i = 1; i <= 2; i++) {
				String message = driver.findElement(By
						.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
						.getText();

				if (message.equalsIgnoreCase("Output IGST Amount")) {
					Form_IGST = driver.findElement(By.xpath(
							"//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText();
					System.out.println("IGST Amount" + Form_IGST);

					// hs.put("Input IGST Amount", Form_SGST);

				}

				else if (message.equalsIgnoreCase("CESS Amount")) {
					Form_CESS = driver.findElement(By.xpath(
							"//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText();
					System.out.println("CESS1 Amount" + Form_CESS);

					// hs.put("CESS Amount", Form_CESS);
				}

			}

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();
			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();
			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1500);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			// GSTR3B report click - (//*[@class='x-tree-node']) [59]/div/img[2]

			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -

			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_3_1_D = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_1_D"))));
			View_3_1_D.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_IGST Amount

			String GSTR3B_IGST_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_IGST_Amount")))
					.getText();
			System.out.println("GSTR3B IGST Amount:- " + GSTR3B_IGST_Amount);
			Thread.sleep(500);

			// 3B_CESS Amount

			String GSTR3B_CESS_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CESS_Amount")))
					.getText();
			System.out.println("GSTR3B CESS Amount:- " + GSTR3B_CESS_Amount);
			Thread.sleep(500);

			// 3B_Total Tax Amount

			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);
			Thread.sleep(500);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);
			Thread.sleep(500);

			if (Form_IGST.equals(GSTR3B_IGST_Amount)
					&& (Form_CESS.equals(GSTR3B_CESS_Amount) && (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
							&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount))))) {

				System.out.println(
						" All values of IGST, CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 3.1.D section");

			}

			else

			{
				System.out.println("Values are Not Matching in 3.1.D  section");
			}

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

	public static void createPurchaseInvoice_IntraState(WebDriver driver, String Vendor_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(2000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(1000);

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			Thread.sleep(2000);

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();

			Thread.sleep(2000);

			// Fetch values

			String Form_SGST = "";
			String Form_CGST = "";
			String Form_CESS = "";

			// HashMap<String,String> hs = new HashMap<String,String>();

			for (int i = 1; i <= 3; i++) {
				String message = driver
						.findElement(By.xpath(
								"//*[text()='Input CGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
						.getText();

				if (message.equalsIgnoreCase("Input SGST Amount")) {
					Form_SGST = driver.findElement(By.xpath(
							"//*[text()='Input SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText(); // A B C
					System.out.println("SGST Amount" + Form_SGST);

					// hs.put("Input SGST Amount", Form_SGST); Input CGST Amount
					// CESS Amount Input SGST Amount

				}

				else if (message.equalsIgnoreCase("Input CGST Amount")) {
					Form_CGST = driver.findElement(By.xpath(
							"//*[text()='Input SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText(); // B A A
					System.out.println("CGST Amount" + Form_CGST);

					// hs.put("Input CGST Amount", Form_CGST);

				}

				else if (message.equalsIgnoreCase("CESS Amount")) {
					Form_CESS = driver.findElement(By.xpath(
							"//*[text()='Input SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText(); // C C B
					System.out.println("CESS Amount" + Form_CESS);

					// hs.put("CESS Amount", Form_CESS);
				}

			}

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();
			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();
			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1500);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			// GSTR3B report click - (//*[@class='x-tree-node']) [59]/div/img[2]

			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -

			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_4_A_5 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_4_A_5"))));
			View_4_A_5.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_SGST Amount

			String GSTR3B_SGST_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_SGST_Amount")))
					.getText();
			System.out.println("GSTR3B SGST Amount:- " + GSTR3B_SGST_Amount);
			Thread.sleep(500);

			// 3B_CGST Amount

			String GSTR3B_CGST_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CGST_Amount")))
					.getText();
			System.out.println("GSTR3B CGST Amount:- " + GSTR3B_CGST_Amount);
			Thread.sleep(500);

			// 3B_CESS Amount

			String GSTR3B_CESS_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CESS_Amount")))
					.getText();
			System.out.println("GSTR3B CESS Amount:- " + GSTR3B_CESS_Amount);
			Thread.sleep(500);

			// 3B_Total Tax Amount

			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);
			Thread.sleep(500);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);
			Thread.sleep(500);

			if (Form_SGST.equals(GSTR3B_SGST_Amount) && (Form_CGST.equals(GSTR3B_CGST_Amount)
					&& (Form_CESS.equals(GSTR3B_CESS_Amount) && (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
							&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)))))) {
				System.out.println(
						" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 4.A.5  section");
			} else {
				System.out.println("Values are Not Matching in 4.A.5  section");
			}

			driver.navigate().refresh();
		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

	public static void createPurchaseInvoice_InterState(WebDriver driver, String Vendor_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(2000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(1000);

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			Thread.sleep(2000);

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();

			Thread.sleep(2000);

			// Fetch values

			String Form_IGST = null;
			String Form_CESS = null;

			for (int i = 1; i <= 2; i++) {
				String message = driver
						.findElement(By.xpath(
								"//*[text()='Input IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
						.getText();

				if (message.equalsIgnoreCase("Input IGST Amount")) {
					Form_IGST = driver.findElement(By.xpath(
							"//*[text()='Input IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText();
					System.out.println("IGST Amount" + Form_IGST);

					// hs.put("Input IGST Amount", Form_SGST);

				}

				else if (message.equalsIgnoreCase("CESS Amount")) {
					Form_CESS = driver.findElement(By.xpath(
							"//*[text()='Input IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText();
					System.out.println("CESS Amount" + Form_CESS);

					// hs.put("CESS Amount", Form_CESS);
				}

			}

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();
			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();
			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1500);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			// 3B click
			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -
			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_4_A_5 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_4_A_5"))));
			View_4_A_5.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_IGST Amount

			String GSTR3B_IGST_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_IGST_Amount")))
					.getText();
			System.out.println("GSTR3B IGST Amount:- " + GSTR3B_IGST_Amount);
			Thread.sleep(500);

			// 3B_CESS Amount

			String GSTR3B_CESS_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CESS_Amount")))
					.getText();
			System.out.println("GSTR3B CESS Amount:- " + GSTR3B_CESS_Amount);
			Thread.sleep(500);

			// 3B_Total Tax Amount

			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);
			Thread.sleep(500);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);
			Thread.sleep(500);

			if (Form_IGST.equals(GSTR3B_IGST_Amount)
					&& (Form_CESS.equals(GSTR3B_CESS_Amount) && (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
							&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount))))) {

				System.out.println(
						" All values of IGST, CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 4.A.5 section");

			}

			else

			{
				System.out.println("Values are Not Matching in 4.A.5  section");
			}

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

	public static void createPurchaseInvoice_SEZ_WPAY_IntraState(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(2000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(1000);

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			Thread.sleep(2000);

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(2000);

			// Fetch values

			String Form_IGST = null;
			String Form_CESS = null;

			for (int i = 1; i <= 2; i++) {
				String message = driver
						.findElement(By.xpath(
								"//*[text()='Input IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
						.getText();

				if (message.equalsIgnoreCase("Input IGST Amount")) {
					Form_IGST = driver.findElement(By.xpath(
							"//*[text()='Input IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText();
					System.out.println("IGST Amount" + Form_IGST);

					// hs.put("Input IGST Amount", Form_SGST);

				}

				else if (message.equalsIgnoreCase("CESS Amount")) {
					Form_CESS = driver.findElement(By.xpath(
							"//*[text()='Input IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[2]"))
							.getText();
					System.out.println("CESS Amount" + Form_CESS);

					// hs.put("CESS Amount", Form_CESS);
				}

			}

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();
			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();
			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1500);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			// 3B click
			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -
			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_4_A_5 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_4_A_5"))));
			View_4_A_5.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_IGST Amount

			String GSTR3B_IGST_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_IGST_Amount")))
					.getText();
			System.out.println("GSTR3B IGST Amount:- " + GSTR3B_IGST_Amount);
			Thread.sleep(500);

			// 3B_CESS Amount

			String GSTR3B_CESS_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CESS_Amount")))
					.getText();
			System.out.println("GSTR3B CESS Amount:- " + GSTR3B_CESS_Amount);
			Thread.sleep(500);

			// 3B_Total Tax Amount

			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);
			Thread.sleep(500);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);
			Thread.sleep(500);

			if (Form_IGST.equals(GSTR3B_IGST_Amount)
					&& (Form_CESS.equals(GSTR3B_CESS_Amount) && (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
							&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount))))) {

				System.out.println(
						" All values of IGST, CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 4.A.5 section");

			}

			else

			{
				System.out.println("Values are Not Matching in 4.A.5  section");
			}

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

	public static void createPurchaseInvoice_IntraState_Section_5_1(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(2000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(1000);

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			Thread.sleep(2000);

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(2000);

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();
			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();
			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
			Thread.sleep(500);

			String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount"))).getText();
			System.out.println("Total Amount:- " + Form_Total_Amount);
			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1500);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			// GSTR3B report click - (//*[@class='x-tree-node']) [59]/div/img[2]

			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -
			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_5_1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_5_1"))));
			View_5_1.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Tax Amount
			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);
			Thread.sleep(500);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);
			Thread.sleep(500);

			String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
					.getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);
			Thread.sleep(1000);

			if (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
					&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
							&& (Form_Total_Amount.equals(GSTR3B_Total_Amount)))) {
				System.out.println(
						" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 5_1  section");
			} else {
				System.out.println("Values are Not Matching in 5_1  section");
			}

			driver.navigate().refresh();
		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

	public static void createPurchaseInvoice_InterState_Section_5_1(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(2000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(1000);

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			Thread.sleep(2000);

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();

			Thread.sleep(2000);

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();
			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();
			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
			Thread.sleep(500);

			String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount"))).getText();
			System.out.println("Total Amount:- " + Form_Total_Amount);
			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1500);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			// GSTR3B report click - (//*[@class='x-tree-node']) [59]/div/img[2]

			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -
			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_5_1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_5_1"))));
			View_5_1.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Tax Amount
			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);
			Thread.sleep(500);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);
			Thread.sleep(500);

			String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
					.getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);
			Thread.sleep(1000);

			if ((Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
					&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
							&& (Form_Total_Amount.equals(GSTR3B_Total_Amount))))) {
				System.out.println(
						" All values of Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 5_1  section");
			} else {
				System.out.println("Values are Not Matching in 5_1  section");
			}

			driver.navigate().refresh();
		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

	public static void createPurchaseInvoice_IntraState_Section_5_2(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(2000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(1000);

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			Thread.sleep(2000);

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(2000);

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();
			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();
			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
			Thread.sleep(500);

			String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount"))).getText();
			System.out.println("Total Amount:- " + Form_Total_Amount);
			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1500);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			// GSTR3B report click - (//*[@class='x-tree-node']) [59]/div/img[2]

			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -
			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_5_2 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_5_2"))));
			View_5_2.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Tax Amount
			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);
			Thread.sleep(500);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);
			Thread.sleep(500);

			String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
					.getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);
			Thread.sleep(1000);

			if (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
					&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
							&& (Form_Total_Amount.equals(GSTR3B_Total_Amount)))) {
				System.out.println(
						" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 5_1  section");
			} else {
				System.out.println("Values are Not Matching in 5_1  section");
			}

			driver.navigate().refresh();
		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

	public static void createPurchaseInvoice_InterState_Section_5_2(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(2000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(1000);

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			Thread.sleep(2000);

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();

			Thread.sleep(2000);

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();
			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();
			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
			Thread.sleep(500);

			String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount"))).getText();
			System.out.println("Total Amount:- " + Form_Total_Amount);
			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1500);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			// GSTR3B report click - (//*[@class='x-tree-node']) [59]/div/img[2]

			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -
			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_5_2 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_5_2"))));
			View_5_2.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Tax Amount
			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);
			Thread.sleep(500);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);
			Thread.sleep(500);

			String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
					.getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);
			Thread.sleep(1000);

			if ((Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
					&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
							&& (Form_Total_Amount.equals(GSTR3B_Total_Amount))))) {
				System.out.println(
						" All values of Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 5_1  section");
			} else {
				System.out.println("Values are Not Matching in 5_1  section");
			}

			driver.navigate().refresh();
		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

	public static void createPurchaseInvoice_IntraState_Section_5_3(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(2000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(1000);

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			Thread.sleep(2000);

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(2000);

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();
			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();
			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
			Thread.sleep(500);

			String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount"))).getText();
			System.out.println("Total Amount:- " + Form_Total_Amount);
			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1500);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			// GSTR3B report click - (//*[@class='x-tree-node']) [59]/div/img[2]

			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -
			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_5_3 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_5_3"))));
			View_5_3.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Tax Amount
			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);
			Thread.sleep(500);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);
			Thread.sleep(500);

			String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
					.getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);
			Thread.sleep(1000);

			if (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
					&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
							&& (Form_Total_Amount.equals(GSTR3B_Total_Amount)))) {
				System.out.println(
						" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 5_3  section");
			} else {
				System.out.println("Values are Not Matching in 5_3  section");
			}

			driver.navigate().refresh();
		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

	public static void createPurchaseInvoice_InterState_Section_5_3(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(2000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(1000);

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			Thread.sleep(2000);

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();

			Thread.sleep(2000);

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();
			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();
			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
			Thread.sleep(500);

			String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount"))).getText();
			System.out.println("Total Amount:- " + Form_Total_Amount);
			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1500);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			// GSTR3B report click - (//*[@class='x-tree-node']) [59]/div/img[2]

			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -
			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_5_3 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_5_3"))));
			View_5_3.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Tax Amount
			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);
			Thread.sleep(500);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);
			Thread.sleep(500);

			String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
					.getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);
			Thread.sleep(1000);

			if ((Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
					&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
							&& (Form_Total_Amount.equals(GSTR3B_Total_Amount))))) {
				System.out.println(
						" All values of Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 5_3  section");
			} else {
				System.out.println("Values are Not Matching in 5_3  section");
			}

			driver.navigate().refresh();
		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

	public static void createPurchaseInvoice_IntraState_Section_5_4(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(2000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(1000);

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			Thread.sleep(2000);

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(2000);

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();
			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();
			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
			Thread.sleep(500);

			String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount"))).getText();
			System.out.println("Total Amount:- " + Form_Total_Amount);
			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1500);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			// GSTR3B report click - (//*[@class='x-tree-node']) [59]/div/img[2]

			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -
			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_5_4 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_5_4"))));
			View_5_4.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Tax Amount
			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);
			Thread.sleep(500);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);
			Thread.sleep(500);

			String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
					.getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);
			Thread.sleep(1000);

			if (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
					&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
							&& (Form_Total_Amount.equals(GSTR3B_Total_Amount)))) {
				System.out.println(
						" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 5_4  section");
			} else {
				System.out.println("Values are Not Matching in 5_4  section");
			}

			driver.navigate().refresh();
		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

	public static void createPurchaseInvoice_InterState_Section_5_4(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Module")))); //// area[@onclick='callVendorQuotation()']
																												//// ,
																												//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Module.click();

			String expForname = "//input[@id='customer15goodsreceipt']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(2000);

			WebElement SearchProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SearchProduct"))));
			SearchProduct.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(1000);

			WebElement Product_ADD_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_ADD_in_PI"))));
			Product_ADD_in_PI.click();
			Thread.sleep(1000);

			WebElement Price_ok = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Price_ok"))));
			Price_ok.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);
			Thread.sleep(1000);

			WebElement Memo_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price"))));
			Unit_Price.click();

			String Unit_Price2 = "200";
			WebElement Unit_Price1_in_PI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_PI"))));
			Unit_Price1_in_PI.sendKeys(Unit_Price2);
			Thread.sleep(1000);

			WebElement Memo_in_PI1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_PI"))));
			Memo_in_PI1.sendKeys(Memo_value);
			Thread.sleep(1000);

			// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No"))).getText();

			Thread.sleep(2000);

			System.out.println("Purchase Invoice Created Successfully :- " + Form_Document_No);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();

			Thread.sleep(2000);

			String Form_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount")))
					.getText();
			System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
			Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();
			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
			Thread.sleep(500);

			String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount"))).getText();
			System.out.println("Total Amount:- " + Form_Total_Amount);
			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close"))));
			Close.click();
			Thread.sleep(1500);

			WebElement Statutory_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
			Statutory_Add.click();
			Thread.sleep(1000);

			WebElement Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
			Report_Add.click();
			Thread.sleep(1000);

			WebElement GSTR_Report_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
			GSTR_Report_Add.click();
			Thread.sleep(1000);

			// GSTR3B report click - (//*[@class='x-tree-node']) [59]/div/img[2]

			WebElement GSTR3B_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Report_Click"))));
			GSTR3B_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -
			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement View_5_4 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_5_4"))));
			View_5_4.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Tax Amount
			String GSTR3B_Total_Tax_Amount = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount);
			Thread.sleep(500);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount")))
					.getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);
			Thread.sleep(500);

			String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
					.getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);
			Thread.sleep(1000);

			if ((Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
					&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
							&& (Form_Total_Amount.equals(GSTR3B_Total_Amount))))) {
				System.out.println(
						" All values of Total Tax, SubTotal , Total Amount matching with GSTR3B report values in 5_4  section");
			} else {
				System.out.println("Values are Not Matching in 5_4  section");
			}

			driver.navigate().refresh();
		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		System.out.println("********************************************************************");
		System.out.println("********************************************************************");

	}

}
