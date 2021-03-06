package krawler.erp.IndianGST;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class GST_Sales_Invoice

{

	public static void createSalesInvoice_IntraState(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {

			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();

			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			{
				String Form_SGST = "";
				String Form_CGST = "";
				String Form_CESS = "";

				// HashMap<String,String> hs = new HashMap<String,String>();

				for (int i = 1; i <= 3; i++) {
					String message = driver.findElement(By.xpath(
							"//*[text()='Output CGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
							.getText();

					if (message.equalsIgnoreCase("Output SGST Amount")) {
						Form_SGST = driver.findElement(
								By.xpath("//*[text()='Output SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText(); // A B C
						System.out.println("SGST Amount" + Form_SGST);

						// hs.put("Input SGST Amount", Form_SGST);

					}

					else if (message.equalsIgnoreCase("Output CGST Amount")) {
						Form_CGST = driver.findElement(
								By.xpath("//*[text()='Output SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText(); // B A A
						System.out.println("CGST Amount" + Form_CGST);

						// hs.put("Input CGST Amount", Form_CGST);

					}

					else if (message.equalsIgnoreCase("CESS Amount")) {
						Form_CESS = driver.findElement(
								By.xpath("//*[text()='Output SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText(); // C C B
						System.out.println("CESS Amount" + Form_CESS);

						// hs.put("CESS Amount", Form_CESS);
					}

				}

				// String input_SGST_Amount = (String)hs.get("Input SGST
				// Amount");

				String Form_Total_Tax_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount"))).getText();

				System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);

				Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount")))
						.getText();

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Customer_Close = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
				Customer_Close.click();

				Thread.sleep(1000);

				WebElement Statutory_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
				Statutory_Add.click();

				Thread.sleep(1000);

				WebElement Report_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
				Report_Add.click();

				Thread.sleep(1000);

				WebElement GSTR_Report_Add = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
				GSTR_Report_Add.click();

				Thread.sleep(1000);

				// GSTR3B report click - (//*[@class='x-tree-node'])
				// [59]/div/img[2]

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

				WebElement View_3_1_A = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_1_A"))));
				View_3_1_A.click();

				Thread.sleep(1000);

				// Input -->

				WebElement Search_ID = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
				Search_ID.sendKeys(Form_Document_No2);

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

				String GSTR3B_Taxable_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();

				System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);

				Thread.sleep(1000);

				// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

				String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
						.getText();

				System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);

				Thread.sleep(1000);

				if (Form_SGST.equals(GSTR3B_SGST_Amount)
						&& (Form_CGST.equals(GSTR3B_CGST_Amount) && (Form_CESS.equals(GSTR3B_CESS_Amount)
								&& (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
										&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
												&& (Form_Total_Amount.equals(GSTR3B_Total_Amount))))))) {
					System.out.println(
							" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values ");
				} else {
					System.out.println("Values are Not Matching");
				}

				System.out.println("********************************************************************");

				System.out.println("********************************************************************");

				WebElement GSTR3B_Detailed_Report_Close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Detailed_Report_Close"))));
				GSTR3B_Detailed_Report_Close.click();

				Thread.sleep(1000);

				WebElement GSTR3B_Summery_Report_Close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Summery_Report_Close"))));
				GSTR3B_Summery_Report_Close.click();

				Thread.sleep(1000);

				// (//*[@class='x-panel-header x-unselectable x-accordion-hd'])
				// [16]/div

				// WebElement Statutory_Minimise =
				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'StatutoryNavigationPanelID')]/div[1]/div[1]")));
				// Statutory_Minimise.click();

				Thread.sleep(5000);

				//// *[contains(@id,'StatutoryNavigationPanelID')]/div[1]/div[1]

				driver.navigate().refresh();

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	// ******************************************************************************************************************************************************************

	public static void createSalesInvoice_InterState(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			// //*[text()='Product Category:
			// (None)']/parent::div/following-sibling::div/div[1]/table/tbody/tr/td[1]/div/div
			// <-- Actual path

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			// WebElement SelectPro2 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath
			// ("(//*[@class='x-grid3-col x-grid3-cell x-grid3-td-checker
			// x-grid3-cell-first ']) [3]")));
			// SelectPro2.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			// WebElement Price_ok =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td/table/tbody/tr/td[2]/em)
			// [1]")));
			// Price_ok.click();
			// Thread.sleep(1000);

			// For 1st product add

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();

			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			{

				String Form_IGST = null;
				String Form_IGST_CESS = null;

				// HashMap<String,String> hs = new HashMap<String,String>();

				for (int i = 1; i <= 2; i++) {
					String message = driver.findElement(By.xpath(
							"//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
							.getText();

					if (message.equalsIgnoreCase("Output IGST Amount")) {
						Form_IGST = driver.findElement(
								By.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText();
						System.out.println("IGST Amount" + Form_IGST);

						// hs.put("Input IGST Amount", Form_SGST);

					}

					else if (message.equalsIgnoreCase("CESS Amount")) {
						Form_IGST_CESS = driver.findElement(
								By.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText();
						System.out.println("CESS1 Amount" + Form_IGST_CESS);

						// hs.put("CESS Amount", Form_CESS);
					}

				}

				// String input_SGST_Amount = (String)hs.get("Input SGST
				// Amount");

				String Form_Total_Tax_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount"))).getText();

				System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);

				Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount")))
						.getText();

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Customer_Close = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
				Customer_Close.click();

				Thread.sleep(1000);

				WebElement Statutory_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
				Statutory_Add.click();

				Thread.sleep(1000);

				WebElement Report_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
				Report_Add.click();

				Thread.sleep(1000);

				WebElement GSTR_Report_Add = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
				GSTR_Report_Add.click();

				Thread.sleep(1000);

				// GSTR3B report click - (//*[@class='x-tree-node'])
				// [59]/div/img[2]

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

				WebElement View_3_1_A = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_1_A"))));
				View_3_1_A.click();

				Thread.sleep(1000);

				// Input -->

				WebElement Search_ID = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
				Search_ID.sendKeys(Form_Document_No2);

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

				String GSTR3B_Taxable_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();

				System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);

				Thread.sleep(1000);

				// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

				String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
						.getText();

				System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);

				Thread.sleep(1000);

				if (Form_IGST.equals(GSTR3B_IGST_Amount) && (Form_IGST_CESS.equals(GSTR3B_CESS_Amount)
						&& (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
								&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
										&& (Form_Total_Amount.equals(GSTR3B_Total_Amount)))))) {
					System.out.println(
							" All values of IGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values ");
				} else {
					System.out.println("Values are Not Matching");
				}

				System.out.println("********************************************************************");

				System.out.println("********************************************************************");

				WebElement GSTR3B_Detailed_Report_Close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Detailed_Report_Close"))));
				GSTR3B_Detailed_Report_Close.click();

				Thread.sleep(1000);

				WebElement GSTR3B_Summery_Report_Close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Summery_Report_Close"))));
				GSTR3B_Summery_Report_Close.click();

				Thread.sleep(1000);

				// (//*[@class='x-panel-header x-unselectable x-accordion-hd'])
				// [16]/div

				// WebElement Statutory_Minimise =
				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'StatutoryNavigationPanelID')]/div[1]/div[1]")));
				// Statutory_Minimise.click();

				Thread.sleep(5000);

				//// *[contains(@id,'StatutoryNavigationPanelID')]/div[1]/div[1]

				driver.navigate().refresh();

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_SEZ_WPAY_InterState(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);
			// WebElement NA =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sequenceFormatCombobox15goodsreceipt']")));
			// //sequenceFormatCombobox15goodsreceipt
			// NA.sendKeys("NA");
			// NA.sendKeys(Keys.ENTER);
			// WebElement VQN =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*//*[@id='invoiceNo15goodsreceipt']")));
			// ////*[@id='invoiceNo15goodsreceipt']
			// VQN.sendKeys(documentid1);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			// //*[text()='Product Category:
			// (None)']/parent::div/following-sibling::div/div[1]/table/tbody/tr/td[1]/div/div
			// <-- Actual path

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			// WebElement SelectPro2 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath
			// ("(//*[@class='x-grid3-col x-grid3-cell x-grid3-td-checker
			// x-grid3-cell-first ']) [3]")));
			// SelectPro2.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			// WebElement Price_ok =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td/table/tbody/tr/td[2]/em)
			// [1]")));
			// Price_ok.click();
			// Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			// For 2nd product add

			/*
			 * WebElement Quantity1 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.
			 * xpath("(//*[@class='x-grid3-viewport']) [1]/div[2]/div[1]/div[2]/table/tbody/tr/td[16]"
			 * ))); Quantity1.click();
			 * 
			 * 
			 * 
			 * robot.delay(1000); robot.keyPress(KeyEvent.VK_DOWN);
			 * robot.delay(1000); robot.keyPress(KeyEvent.VK_1);
			 * robot.delay(1000); robot.keyRelease(KeyEvent.VK_1);
			 * robot.delay(1000);
			 * 
			 * Thread.sleep(1000);
			 * 
			 * 
			 * //memo15goodsreceipt
			 * 
			 * 
			 * //WebElement Memo2 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//
			 */
			/*
			 * [@id='memo2Invoice']"))); ////*[@id='invoiceNo15goodsreceipt']
			 * Memo.sendKeys(Memo_value);
			 * 
			 * 
			 * Thread.sleep(5000);
			 * 
			 */

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();

			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			{

				String Form_IGST = null;
				String Form_IGST_CESS = null;

				// HashMap<String,String> hs = new HashMap<String,String>();

				for (int i = 1; i <= 2; i++) {
					String message = driver.findElement(By.xpath(
							"//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
							.getText();

					if (message.equalsIgnoreCase("Output IGST Amount")) {
						Form_IGST = driver.findElement(
								By.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText();
						System.out.println("IGST Amount" + Form_IGST);

						// hs.put("Input IGST Amount", Form_SGST);

					}

					else if (message.equalsIgnoreCase("CESS Amount")) {
						Form_IGST_CESS = driver.findElement(
								By.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText();
						System.out.println("CESS1 Amount" + Form_IGST_CESS);

						// hs.put("CESS Amount", Form_CESS);
					}

				}

				// String input_SGST_Amount = (String)hs.get("Input SGST
				// Amount");

				String Form_Total_Tax_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount"))).getText();

				System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);

				Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount")))
						.getText();

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Customer_Close = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
				Customer_Close.click();

				Thread.sleep(1000);

				WebElement Statutory_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
				Statutory_Add.click();

				Thread.sleep(1000);

				WebElement Report_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
				Report_Add.click();

				Thread.sleep(1000);

				WebElement GSTR_Report_Add = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
				GSTR_Report_Add.click();

				Thread.sleep(1000);

				// GSTR3B report click - (//*[@class='x-tree-node'])
				// [59]/div/img[2]

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

				WebElement View_3_1_B = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_1_B"))));
				View_3_1_B.click();

				Thread.sleep(1000);

				// Input -->

				WebElement Search_ID = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
				Search_ID.sendKeys(Form_Document_No2);

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

				String GSTR3B_Taxable_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();

				System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);

				Thread.sleep(1000);

				// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

				String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
						.getText();

				System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);

				Thread.sleep(1000);

				if (Form_IGST.equals(GSTR3B_IGST_Amount) && (Form_IGST_CESS.equals(GSTR3B_CESS_Amount)
						&& (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
								&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
										&& (Form_Total_Amount.equals(GSTR3B_Total_Amount)))))) {
					System.out.println(
							" All values of IGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values ");
				} else {
					System.out.println("Values are Not Matching");
				}

				System.out.println("********************************************************************");

				System.out.println("********************************************************************");

				WebElement GSTR3B_Detailed_Report_Close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Detailed_Report_Close"))));
				GSTR3B_Detailed_Report_Close.click();

				Thread.sleep(1000);

				WebElement GSTR3B_Summery_Report_Close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Summery_Report_Close"))));
				GSTR3B_Summery_Report_Close.click();

				Thread.sleep(1000);

				// (//*[@class='x-panel-header x-unselectable x-accordion-hd'])
				// [16]/div

				// WebElement Statutory_Minimise =
				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'StatutoryNavigationPanelID')]/div[1]/div[1]")));
				// Statutory_Minimise.click();

				Thread.sleep(5000);

				//// *[contains(@id,'StatutoryNavigationPanelID')]/div[1]/div[1]

				driver.navigate().refresh();

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_SEZ_WOPAY_InterState(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);
			// WebElement NA =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sequenceFormatCombobox15goodsreceipt']")));
			// //sequenceFormatCombobox15goodsreceipt
			// NA.sendKeys("NA");
			// NA.sendKeys(Keys.ENTER);
			// WebElement VQN =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*//*[@id='invoiceNo15goodsreceipt']")));
			// ////*[@id='invoiceNo15goodsreceipt']
			// VQN.sendKeys(documentid1);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			// //*[text()='Product Category:
			// (None)']/parent::div/following-sibling::div/div[1]/table/tbody/tr/td[1]/div/div
			// <-- Actual path

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			// WebElement SelectPro2 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath
			// ("(//*[@class='x-grid3-col x-grid3-cell x-grid3-td-checker
			// x-grid3-cell-first ']) [3]")));
			// SelectPro2.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			// WebElement Price_ok =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td/table/tbody/tr/td[2]/em)
			// [1]")));
			// Price_ok.click();
			// Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			// For 2nd product add

			/*
			 * WebElement Quantity1 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.
			 * xpath("(//*[@class='x-grid3-viewport']) [1]/div[2]/div[1]/div[2]/table/tbody/tr/td[16]"
			 * ))); Quantity1.click();
			 * 
			 * 
			 * 
			 * robot.delay(1000); robot.keyPress(KeyEvent.VK_DOWN);
			 * robot.delay(1000); robot.keyPress(KeyEvent.VK_1);
			 * robot.delay(1000); robot.keyRelease(KeyEvent.VK_1);
			 * robot.delay(1000);
			 * 
			 * Thread.sleep(1000);
			 * 
			 * 
			 * //memo15goodsreceipt
			 * 
			 * 
			 * //WebElement Memo2 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//
			 */
			/*
			 * [@id='memo2Invoice']"))); ////*[@id='invoiceNo15goodsreceipt']
			 * Memo.sendKeys(Memo_value);
			 * 
			 * 
			 * Thread.sleep(5000);
			 * 
			 */

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();

			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

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

			WebElement Customer_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
			Customer_Close.click();

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

			WebElement View_3_1_B = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_1_B"))));
			View_3_1_B.click();

			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No2);

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

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
					.getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);

			Thread.sleep(1000);

			if ((Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
					&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
							&& (Form_Total_Amount.equals(GSTR3B_Total_Amount))))) {
				System.out
						.println(" All values Total Tax, SubTotal , Total Amount matching with GSTR3B report values ");
			} else {
				System.out.println("Values are Not Matching");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			WebElement GSTR3B_Detailed_Report_Close = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Detailed_Report_Close"))));
			GSTR3B_Detailed_Report_Close.click();

			Thread.sleep(1000);

			WebElement GSTR3B_Summery_Report_Close = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Summery_Report_Close"))));
			GSTR3B_Summery_Report_Close.click();

			Thread.sleep(1000);

			// (//*[@class='x-panel-header x-unselectable x-accordion-hd'])
			// [16]/div

			// WebElement Statutory_Minimise =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'StatutoryNavigationPanelID')]/div[1]/div[1]")));
			// Statutory_Minimise.click();

			Thread.sleep(5000);

			//// *[contains(@id,'StatutoryNavigationPanelID')]/div[1]/div[1]

			driver.navigate().refresh();

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_Export_WPAY_InterState(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);
			// WebElement NA =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sequenceFormatCombobox15goodsreceipt']")));
			// //sequenceFormatCombobox15goodsreceipt
			// NA.sendKeys("NA");
			// NA.sendKeys(Keys.ENTER);
			// WebElement VQN =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*//*[@id='invoiceNo15goodsreceipt']")));
			// ////*[@id='invoiceNo15goodsreceipt']
			// VQN.sendKeys(documentid1);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			// //*[text()='Product Category:
			// (None)']/parent::div/following-sibling::div/div[1]/table/tbody/tr/td[1]/div/div
			// <-- Actual path

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			// WebElement SelectPro2 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath
			// ("(//*[@class='x-grid3-col x-grid3-cell x-grid3-td-checker
			// x-grid3-cell-first ']) [3]")));
			// SelectPro2.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			// WebElement Price_ok =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td/table/tbody/tr/td[2]/em)
			// [1]")));
			// Price_ok.click();
			// Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			// For 2nd product add

			/*
			 * WebElement Quantity1 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.
			 * xpath("(//*[@class='x-grid3-viewport']) [1]/div[2]/div[1]/div[2]/table/tbody/tr/td[16]"
			 * ))); Quantity1.click();
			 * 
			 * 
			 * 
			 * robot.delay(1000); robot.keyPress(KeyEvent.VK_DOWN);
			 * robot.delay(1000); robot.keyPress(KeyEvent.VK_1);
			 * robot.delay(1000); robot.keyRelease(KeyEvent.VK_1);
			 * robot.delay(1000);
			 * 
			 * Thread.sleep(1000);
			 * 
			 * 
			 * //memo15goodsreceipt
			 * 
			 * 
			 * //WebElement Memo2 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//
			 */
			/*
			 * [@id='memo2Invoice']"))); ////*[@id='invoiceNo15goodsreceipt']
			 * Memo.sendKeys(Memo_value);
			 * 
			 * 
			 * Thread.sleep(5000);
			 * 
			 */

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();

			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			{

				String Form_IGST = null;

				// HashMap<String,String> hs = new HashMap<String,String>();

				for (int i = 1; i <= 1; i++) {
					String message = driver.findElement(By.xpath(
							"//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
							.getText();

					if (message.equalsIgnoreCase("Output IGST Amount")) {
						Form_IGST = driver.findElement(
								By.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText();
						System.out.println("IGST Amount" + Form_IGST);

						// hs.put("Input IGST Amount", Form_SGST);

					}

				}

				// String input_SGST_Amount = (String)hs.get("Input SGST
				// Amount");

				String Form_Total_Tax_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount"))).getText();

				System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);

				Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount")))
						.getText();

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Customer_Close = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
				Customer_Close.click();

				Thread.sleep(1000);

				WebElement Statutory_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
				Statutory_Add.click();

				Thread.sleep(1000);

				WebElement Report_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
				Report_Add.click();

				Thread.sleep(1000);

				WebElement GSTR_Report_Add = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
				GSTR_Report_Add.click();

				Thread.sleep(1000);

				// GSTR3B report click - (//*[@class='x-tree-node'])
				// [59]/div/img[2]

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

				WebElement View_3_1_B = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_1_B"))));
				View_3_1_B.click();

				Thread.sleep(1000);

				// Input -->

				WebElement Search_ID = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
				Search_ID.sendKeys(Form_Document_No2);

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

				String GSTR3B_Taxable_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();

				System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);

				Thread.sleep(1000);

				// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

				String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
						.getText();

				System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);

				Thread.sleep(1000);

				if (Form_IGST.equals(GSTR3B_IGST_Amount) && (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
						&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
								&& (Form_Total_Amount.equals(GSTR3B_Total_Amount))))) {
					System.out.println(
							" All values of IGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values ");
				} else {
					System.out.println("Values are Not Matching");
				}

				System.out.println("********************************************************************");

				System.out.println("********************************************************************");

				WebElement GSTR3B_Detailed_Report_Close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Detailed_Report_Close"))));
				GSTR3B_Detailed_Report_Close.click();

				Thread.sleep(1000);

				WebElement GSTR3B_Summery_Report_Close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Summery_Report_Close"))));
				GSTR3B_Summery_Report_Close.click();

				Thread.sleep(1000);

				// (//*[@class='x-panel-header x-unselectable x-accordion-hd'])
				// [16]/div

				// WebElement Statutory_Minimise =
				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'StatutoryNavigationPanelID')]/div[1]/div[1]")));
				// Statutory_Minimise.click();

				Thread.sleep(5000);

				//// *[contains(@id,'StatutoryNavigationPanelID')]/div[1]/div[1]

				driver.navigate().refresh();

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_Export_WOPAY_InterState(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);
			// WebElement NA =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sequenceFormatCombobox15goodsreceipt']")));
			// //sequenceFormatCombobox15goodsreceipt
			// NA.sendKeys("NA");
			// NA.sendKeys(Keys.ENTER);
			// WebElement VQN =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*//*[@id='invoiceNo15goodsreceipt']")));
			// ////*[@id='invoiceNo15goodsreceipt']
			// VQN.sendKeys(documentid1);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			// //*[text()='Product Category:
			// (None)']/parent::div/following-sibling::div/div[1]/table/tbody/tr/td[1]/div/div
			// <-- Actual path

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			// WebElement SelectPro2 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath
			// ("(//*[@class='x-grid3-col x-grid3-cell x-grid3-td-checker
			// x-grid3-cell-first ']) [3]")));
			// SelectPro2.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			// WebElement Price_ok =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td/table/tbody/tr/td[2]/em)
			// [1]")));
			// Price_ok.click();
			// Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			// For 2nd product add

			/*
			 * WebElement Quantity1 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.
			 * xpath("(//*[@class='x-grid3-viewport']) [1]/div[2]/div[1]/div[2]/table/tbody/tr/td[16]"
			 * ))); Quantity1.click();
			 * 
			 * 
			 * 
			 * robot.delay(1000); robot.keyPress(KeyEvent.VK_DOWN);
			 * robot.delay(1000); robot.keyPress(KeyEvent.VK_1);
			 * robot.delay(1000); robot.keyRelease(KeyEvent.VK_1);
			 * robot.delay(1000);
			 * 
			 * Thread.sleep(1000);
			 * 
			 * 
			 * //memo15goodsreceipt
			 * 
			 * 
			 * //WebElement Memo2 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//
			 */
			/*
			 * [@id='memo2Invoice']"))); ////*[@id='invoiceNo15goodsreceipt']
			 * Memo.sendKeys(Memo_value);
			 * 
			 * 
			 * Thread.sleep(5000);
			 * 
			 */

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();

			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

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

			WebElement Customer_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
			Customer_Close.click();

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

			WebElement View_3_1_B = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_1_B"))));
			View_3_1_B.click();

			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No2);

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

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
					.getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);

			Thread.sleep(1000);

			if ((Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
					&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
							&& (Form_Total_Amount.equals(GSTR3B_Total_Amount))))) {
				System.out
						.println(" All values Total Tax, SubTotal , Total Amount matching with GSTR3B report values ");
			} else {
				System.out.println("Values are Not Matching");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			WebElement GSTR3B_Detailed_Report_Close = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Detailed_Report_Close"))));
			GSTR3B_Detailed_Report_Close.click();

			Thread.sleep(1000);

			WebElement GSTR3B_Summery_Report_Close = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Summery_Report_Close"))));
			GSTR3B_Summery_Report_Close.click();

			Thread.sleep(1000);

			// (//*[@class='x-panel-header x-unselectable x-accordion-hd'])
			// [16]/div

			// WebElement Statutory_Minimise =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'StatutoryNavigationPanelID')]/div[1]/div[1]")));
			// Statutory_Minimise.click();

			Thread.sleep(5000);

			//// *[contains(@id,'StatutoryNavigationPanelID')]/div[1]/div[1]

			driver.navigate().refresh();

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_NIL_Rated_Exempted(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);
			// WebElement NA =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sequenceFormatCombobox15goodsreceipt']")));
			// //sequenceFormatCombobox15goodsreceipt
			// NA.sendKeys("NA");
			// NA.sendKeys(Keys.ENTER);
			// WebElement VQN =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*//*[@id='invoiceNo15goodsreceipt']")));
			// ////*[@id='invoiceNo15goodsreceipt']
			// VQN.sendKeys(documentid1);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			// //*[text()='Product Category:
			// (None)']/parent::div/following-sibling::div/div[1]/table/tbody/tr/td[1]/div/div
			// <-- Actual path

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			// WebElement SelectPro2 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath
			// ("(//*[@class='x-grid3-col x-grid3-cell x-grid3-td-checker
			// x-grid3-cell-first ']) [3]")));
			// SelectPro2.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			// WebElement Price_ok =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td/table/tbody/tr/td[2]/em)
			// [1]")));
			// Price_ok.click();
			// Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			// For 2nd product add

			/*
			 * WebElement Quantity1 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.
			 * xpath("(//*[@class='x-grid3-viewport']) [1]/div[2]/div[1]/div[2]/table/tbody/tr/td[16]"
			 * ))); Quantity1.click();
			 * 
			 * 
			 * 
			 * robot.delay(1000); robot.keyPress(KeyEvent.VK_DOWN);
			 * robot.delay(1000); robot.keyPress(KeyEvent.VK_1);
			 * robot.delay(1000); robot.keyRelease(KeyEvent.VK_1);
			 * robot.delay(1000);
			 * 
			 * Thread.sleep(1000);
			 * 
			 * 
			 * //memo15goodsreceipt
			 * 
			 * 
			 * //WebElement Memo2 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//
			 */
			/*
			 * [@id='memo2Invoice']"))); ////*[@id='invoiceNo15goodsreceipt']
			 * Memo.sendKeys(Memo_value);
			 * 
			 * 
			 * Thread.sleep(5000);
			 * 
			 */

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();

			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();

			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

			Thread.sleep(500);

			String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount"))).getText();

			System.out.println("Total Amount:- " + Form_Total_Amount);

			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Customer_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
			Customer_Close.click();

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

			WebElement View_3_1_C = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_1_C"))));
			View_3_1_C.click();

			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No2);

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

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
					.getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);

			Thread.sleep(1000);

			if (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount) && (Form_Total_Amount.equals(GSTR3B_Total_Amount))) {
				System.out.println(" All values of SubTotal , Total Amount matching with GSTR3B report values ");
			} else {
				System.out.println("Values are Not Matching");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_NonGST(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);
			// WebElement NA =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sequenceFormatCombobox15goodsreceipt']")));
			// //sequenceFormatCombobox15goodsreceipt
			// NA.sendKeys("NA");
			// NA.sendKeys(Keys.ENTER);
			// WebElement VQN =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*//*[@id='invoiceNo15goodsreceipt']")));
			// ////*[@id='invoiceNo15goodsreceipt']
			// VQN.sendKeys(documentid1);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			// //*[text()='Product Category:
			// (None)']/parent::div/following-sibling::div/div[1]/table/tbody/tr/td[1]/div/div
			// <-- Actual path

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			// WebElement SelectPro2 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath
			// ("(//*[@class='x-grid3-col x-grid3-cell x-grid3-td-checker
			// x-grid3-cell-first ']) [3]")));
			// SelectPro2.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			// WebElement Price_ok =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td/table/tbody/tr/td[2]/em)
			// [1]")));
			// Price_ok.click();
			// Thread.sleep(1000);

			// For 1st product add

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			// For 2nd product add

			/*
			 * WebElement Quantity1 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.
			 * xpath("(//*[@class='x-grid3-viewport']) [1]/div[2]/div[1]/div[2]/table/tbody/tr/td[16]"
			 * ))); Quantity1.click();
			 * 
			 * 
			 * 
			 * robot.delay(1000); robot.keyPress(KeyEvent.VK_DOWN);
			 * robot.delay(1000); robot.keyPress(KeyEvent.VK_1);
			 * robot.delay(1000); robot.keyRelease(KeyEvent.VK_1);
			 * robot.delay(1000);
			 * 
			 * Thread.sleep(1000);
			 * 
			 * 
			 * //memo15goodsreceipt
			 * 
			 * 
			 * //WebElement Memo2 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//
			 */
			/*
			 * [@id='memo2Invoice']"))); ////*[@id='invoiceNo15goodsreceipt']
			 * Memo.sendKeys(Memo_value);
			 * 
			 * 
			 * Thread.sleep(5000);
			 * 
			 */

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();

			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();

			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

			Thread.sleep(500);

			String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount"))).getText();

			System.out.println("Total Amount:- " + Form_Total_Amount);

			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Customer_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
			Customer_Close.click();

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

			WebElement View_3_1_E = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_1_E"))));
			View_3_1_E.click();

			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Form_Document_No2);

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

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
					.getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);

			Thread.sleep(1000);

			if (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount) && (Form_Total_Amount.equals(GSTR3B_Total_Amount))) {
				System.out.println(" All values of SubTotal , Total Amount matching with GSTR3B report values ");
			} else {
				System.out.println("Values are Not Matching");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_InterState_3_2_A(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);
			// WebElement NA =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sequenceFormatCombobox15goodsreceipt']")));
			// //sequenceFormatCombobox15goodsreceipt
			// NA.sendKeys("NA");
			// NA.sendKeys(Keys.ENTER);
			// WebElement VQN =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*//*[@id='invoiceNo15goodsreceipt']")));
			// ////*[@id='invoiceNo15goodsreceipt']
			// VQN.sendKeys(documentid1);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			// //*[text()='Product Category:
			// (None)']/parent::div/following-sibling::div/div[1]/table/tbody/tr/td[1]/div/div
			// <-- Actual path

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			// WebElement SelectPro2 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath
			// ("(//*[@class='x-grid3-col x-grid3-cell x-grid3-td-checker
			// x-grid3-cell-first ']) [3]")));
			// SelectPro2.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			// WebElement Price_ok =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td/table/tbody/tr/td[2]/em)
			// [1]")));
			// Price_ok.click();
			// Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			// For 2nd product add

			/*
			 * WebElement Quantity1 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.
			 * xpath("(//*[@class='x-grid3-viewport']) [1]/div[2]/div[1]/div[2]/table/tbody/tr/td[16]"
			 * ))); Quantity1.click();
			 * 
			 * 
			 * 
			 * robot.delay(1000); robot.keyPress(KeyEvent.VK_DOWN);
			 * robot.delay(1000); robot.keyPress(KeyEvent.VK_1);
			 * robot.delay(1000); robot.keyRelease(KeyEvent.VK_1);
			 * robot.delay(1000);
			 * 
			 * Thread.sleep(1000);
			 * 
			 * 
			 * //memo15goodsreceipt
			 * 
			 * 
			 * //WebElement Memo2 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//
			 */
			/*
			 * [@id='memo2Invoice']"))); ////*[@id='invoiceNo15goodsreceipt']
			 * Memo.sendKeys(Memo_value);
			 * 
			 * 
			 * Thread.sleep(5000);
			 * 
			 */

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();

			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			{

				String Form_IGST = null;
				String Form_IGST_CESS = null;

				// HashMap<String,String> hs = new HashMap<String,String>();

				for (int i = 1; i <= 2; i++) {
					String message = driver.findElement(By.xpath(
							"//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
							.getText();

					if (message.equalsIgnoreCase("Output IGST Amount")) {
						Form_IGST = driver.findElement(
								By.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText();
						System.out.println("IGST Amount" + Form_IGST);

						// hs.put("Input IGST Amount", Form_SGST);

					}

					else if (message.equalsIgnoreCase("CESS Amount")) {
						Form_IGST_CESS = driver.findElement(
								By.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText();
						System.out.println("CESS1 Amount" + Form_IGST_CESS);

						// hs.put("CESS Amount", Form_CESS);
					}

				}

				// String input_SGST_Amount = (String)hs.get("Input SGST
				// Amount");

				String Form_Total_Tax_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount"))).getText();

				System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);

				Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount")))
						.getText();

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Customer_Close = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
				Customer_Close.click();

				Thread.sleep(1000);

				WebElement Statutory_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
				Statutory_Add.click();

				Thread.sleep(1000);

				WebElement Report_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
				Report_Add.click();

				Thread.sleep(1000);

				WebElement GSTR_Report_Add = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
				GSTR_Report_Add.click();

				Thread.sleep(1000);

				// GSTR3B report click - (//*[@class='x-tree-node'])
				// [59]/div/img[2]

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

				Thread.sleep(5000);

				// View -->

				WebElement View_3_2_A = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_2_A"))));
				View_3_2_A.click();

				Thread.sleep(3000);

				// Input -->

				WebElement Search_ID = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
				Search_ID.sendKeys(Form_Document_No2);

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

				String GSTR3B_Taxable_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();

				System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);

				Thread.sleep(1000);

				// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

				String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
						.getText();

				System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);

				Thread.sleep(1000);

				if (Form_IGST.equals(GSTR3B_IGST_Amount) && (Form_IGST_CESS.equals(GSTR3B_CESS_Amount)
						&& (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
								&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
										&& (Form_Total_Amount.equals(GSTR3B_Total_Amount)))))) {
					System.out.println(
							" All values of IGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values ");
				} else {
					System.out.println("Values are Not Matching");
				}

				System.out.println("********************************************************************");

				System.out.println("********************************************************************");

				WebElement GSTR3B_Detailed_Report_Close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Detailed_Report_Close"))));
				GSTR3B_Detailed_Report_Close.click();

				Thread.sleep(1000);

				WebElement GSTR3B_Summery_Report_Close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Summery_Report_Close"))));
				GSTR3B_Summery_Report_Close.click();

				Thread.sleep(1000);

				// (//*[@class='x-panel-header x-unselectable x-accordion-hd'])
				// [16]/div

				// WebElement Statutory_Minimise =
				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'StatutoryNavigationPanelID')]/div[1]/div[1]")));
				// Statutory_Minimise.click();

				Thread.sleep(5000);

				//// *[contains(@id,'StatutoryNavigationPanelID')]/div[1]/div[1]

				driver.navigate().refresh();

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_InterState_3_2_B(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);
			// WebElement NA =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sequenceFormatCombobox15goodsreceipt']")));
			// //sequenceFormatCombobox15goodsreceipt
			// NA.sendKeys("NA");
			// NA.sendKeys(Keys.ENTER);
			// WebElement VQN =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*//*[@id='invoiceNo15goodsreceipt']")));
			// ////*[@id='invoiceNo15goodsreceipt']
			// VQN.sendKeys(documentid1);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			// //*[text()='Product Category:
			// (None)']/parent::div/following-sibling::div/div[1]/table/tbody/tr/td[1]/div/div
			// <-- Actual path

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			// WebElement SelectPro2 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath
			// ("(//*[@class='x-grid3-col x-grid3-cell x-grid3-td-checker
			// x-grid3-cell-first ']) [3]")));
			// SelectPro2.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			// WebElement Price_ok =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td/table/tbody/tr/td[2]/em)
			// [1]")));
			// Price_ok.click();
			// Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			// For 2nd product add

			/*
			 * WebElement Quantity1 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.
			 * xpath("(//*[@class='x-grid3-viewport']) [1]/div[2]/div[1]/div[2]/table/tbody/tr/td[16]"
			 * ))); Quantity1.click();
			 * 
			 * 
			 * 
			 * robot.delay(1000); robot.keyPress(KeyEvent.VK_DOWN);
			 * robot.delay(1000); robot.keyPress(KeyEvent.VK_1);
			 * robot.delay(1000); robot.keyRelease(KeyEvent.VK_1);
			 * robot.delay(1000);
			 * 
			 * Thread.sleep(1000);
			 * 
			 * 
			 * //memo15goodsreceipt
			 * 
			 * 
			 * //WebElement Memo2 =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//
			 */
			/*
			 * [@id='memo2Invoice']"))); ////*[@id='invoiceNo15goodsreceipt']
			 * Memo.sendKeys(Memo_value);
			 * 
			 * 
			 * Thread.sleep(5000);
			 * 
			 */

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();

			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			{

				String Form_IGST = null;
				String Form_IGST_CESS = null;

				// HashMap<String,String> hs = new HashMap<String,String>();

				for (int i = 1; i <= 2; i++) {
					String message = driver.findElement(By.xpath(
							"//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
							.getText();

					if (message.equalsIgnoreCase("Output IGST Amount")) {
						Form_IGST = driver.findElement(
								By.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText();
						System.out.println("IGST Amount" + Form_IGST);

						// hs.put("Input IGST Amount", Form_SGST);

					}

					else if (message.equalsIgnoreCase("CESS Amount")) {
						Form_IGST_CESS = driver.findElement(
								By.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText();
						System.out.println("CESS1 Amount" + Form_IGST_CESS);

						// hs.put("CESS Amount", Form_CESS);
					}

				}

				// String input_SGST_Amount = (String)hs.get("Input SGST
				// Amount");

				String Form_Total_Tax_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount"))).getText();

				System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);

				Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount")))
						.getText();

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Customer_Close = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
				Customer_Close.click();

				Thread.sleep(1000);

				WebElement Statutory_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
				Statutory_Add.click();

				Thread.sleep(1000);

				WebElement Report_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
				Report_Add.click();

				Thread.sleep(1000);

				WebElement GSTR_Report_Add = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
				GSTR_Report_Add.click();

				Thread.sleep(1000);

				// GSTR3B report click - (//*[@class='x-tree-node'])
				// [59]/div/img[2]

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

				Thread.sleep(5000);

				// View -->

				WebElement View_3_2_B = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_2_B"))));
				View_3_2_B.click();

				Thread.sleep(3000);

				// Input -->

				WebElement Search_ID = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
				Search_ID.sendKeys(Form_Document_No2);

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

				String GSTR3B_Taxable_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();

				System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount);

				Thread.sleep(1000);

				// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

				String GSTR3B_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount")))
						.getText();

				System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount);

				Thread.sleep(1000);

				if (Form_IGST.equals(GSTR3B_IGST_Amount) && (Form_IGST_CESS.equals(GSTR3B_CESS_Amount)
						&& (Form_Total_Tax_Amount.equals(GSTR3B_Total_Tax_Amount)
								&& (Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
										&& (Form_Total_Amount.equals(GSTR3B_Total_Amount)))))) {
					System.out.println(
							" All values of IGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values ");
				} else {
					System.out.println("Values are Not Matching");
				}

				System.out.println("********************************************************************");

				System.out.println("********************************************************************");

				WebElement GSTR3B_Detailed_Report_Close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Detailed_Report_Close"))));
				GSTR3B_Detailed_Report_Close.click();

				Thread.sleep(1000);

				WebElement GSTR3B_Summery_Report_Close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR3B_Summery_Report_Close"))));
				GSTR3B_Summery_Report_Close.click();

				Thread.sleep(1000);

				driver.navigate().refresh();

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_IntraState_GSTR1(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {

			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			// //*[text()='Product Category:
			// (None)']/parent::div/following-sibling::div/div[1]/table/tbody/tr/td[1]/div/div
			// <-- Actual path

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			// WebElement SelectPro2 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath
			// ("(//*[@class='x-grid3-col x-grid3-cell x-grid3-td-checker
			// x-grid3-cell-first ']) [3]")));
			// SelectPro2.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			// WebElement Price_ok =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td/table/tbody/tr/td[2]/em)
			// [1]")));
			// Price_ok.click();
			// Thread.sleep(1000);

			// For 1st product add

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();
			System.out.println("Document No :-" + Form_Document_No1);
			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			{
				String Form_SGST = "";
				String Form_CGST = "";
				String Form_CESS = "";

				// HashMap<String,String> hs = new HashMap<String,String>();

				for (int i = 1; i <= 3; i++) {
					String message = driver.findElement(By.xpath(
							"//*[text()='Output CGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
							.getText();

					if (message.equalsIgnoreCase("Output SGST Amount")) {
						Form_SGST = driver.findElement(
								By.xpath("//*[text()='Output SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText(); // A B C
						System.out.println("SGST Amount" + Form_SGST);

						// hs.put("Input SGST Amount", Form_SGST);

					}

					else if (message.equalsIgnoreCase("Output CGST Amount")) {
						Form_CGST = driver.findElement(
								By.xpath("//*[text()='Output SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText(); // B A A
						System.out.println("CGST Amount" + Form_CGST);

						// hs.put("Input CGST Amount", Form_CGST);

					}

					else if (message.equalsIgnoreCase("CESS Amount")) {
						Form_CESS = driver.findElement(
								By.xpath("//*[text()='Output SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText(); // C C B
						System.out.println("CESS Amount" + Form_CESS);

						// hs.put("CESS Amount", Form_CESS);
					}

				}

				// String input_SGST_Amount = (String)hs.get("Input SGST
				// Amount");

				String Form_Total_Tax_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount"))).getText();

				System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);

				Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount")))
						.getText();

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Customer_Close = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
				Customer_Close.click();

				Thread.sleep(1000);

				WebElement Statutory_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
				Statutory_Add.click();

				Thread.sleep(1000);

				WebElement Report_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
				Report_Add.click();

				Thread.sleep(1000);

				WebElement GSTR_Report_Add = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
				GSTR_Report_Add.click();

				Thread.sleep(1000);

				WebElement GSTR1_Report_Click = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Report_Click"))));
				GSTR1_Report_Click.click();
				Thread.sleep(1000);

				// From Date -

				WebElement From_Date = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Date"))));
				From_Date.click();
				Thread.sleep(1000);

				// Click on Today -->

				WebElement From_Today = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Today"))));
				From_Today.click();
				Thread.sleep(1000);

				// To Date -

				WebElement To_Date = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Date"))));
				To_Date.click();

				Thread.sleep(1000);

				// Click on Today -->

				WebElement To_Today = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Today"))));
				To_Today.click();

				Thread.sleep(1000);

				// To fetch -->

				WebElement Fetch_Summery = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Fetch_Summery"))));
				Fetch_Summery.click();

				Thread.sleep(1000);

				// View -->

				WebElement B2B_Invoices_Section_View = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("B2B_Invoices_Section_View"))));
				B2B_Invoices_Section_View.click();

				Thread.sleep(1000);

				// Input -->

				WebElement GSTR1_Search_ID = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
				GSTR1_Search_ID.sendKeys(Form_Document_No2);

				Thread.sleep(2000);

				// To fetch -->

				// WebElement Fetch_Details =
				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
				// Fetch_Details.click();
				// Thread.sleep(1000);

				String GSTR1_SGST_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR1_SGST_Amount")))
						.getText();

				System.out.println("GSTR1 SGST Amount:- " + GSTR1_SGST_Amount);

				Thread.sleep(500);

				String GSTR1_CGST_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR1_CGST_Amount")))
						.getText();

				System.out.println("GSTR1 CGST Amount:- " + GSTR1_CGST_Amount);

				Thread.sleep(500);

				// 3B_CESS Amount

				String GSTR1_CESS_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR1_CESS_Amount")))
						.getText();

				System.out.println("GSTR1 CESS Amount:- " + GSTR1_CESS_Amount);

				Thread.sleep(500);

				String GSTR1_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR1_Taxable_Amount")))
						.getText();

				System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR1_Taxable_Amount);

				Thread.sleep(1000);

				// 3B_Total Tax Amount

				String GSTR1_Total_Tax_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("GSTR1_Total_Tax_Amount"))).getText();

				System.out.println("GSTR1 Total Tax Amount:- " + GSTR1_Total_Tax_Amount);

				Thread.sleep(500);

				// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

				String GSTR1_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR1_Total_Amount")))
						.getText();

				System.out.println("GSTR1 Total Amount :- " + GSTR1_Total_Amount);

				Thread.sleep(1000);

				if (Form_SGST.equals(GSTR1_SGST_Amount) && (Form_CGST.equals(GSTR1_CGST_Amount)
						&& (Form_CESS.equals(GSTR1_CESS_Amount) && (Form_SubTotal_Amount.equals(GSTR1_Taxable_Amount))
								&& (Form_Total_Tax_Amount.equals(GSTR1_Total_Tax_Amount)
										&& (Form_Total_Amount.equals(GSTR1_Total_Amount)))))) {
					System.out.println(
							" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section B2B Invoices (4A,4B,4C,6B,6C) ");
				}

				else {
					System.out.println(
							"Values are Not Matching with GSTR1 report values in section B2B Invoices (4A,4B,4C,6B,6C) ");
				}

				System.out.println("********************************************************************");

				System.out.println("********************************************************************");

				driver.navigate().refresh();

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_InterState_GSTR1(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "1";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();
			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			{

				String Form_IGST = null;
				String Form_IGST_CESS = null;

				// HashMap<String,String> hs = new HashMap<String,String>();

				for (int i = 1; i <= 2; i++) {
					String message = driver.findElement(By.xpath(
							"//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
							.getText();

					if (message.equalsIgnoreCase("Output IGST Amount")) {
						Form_IGST = driver.findElement(
								By.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText();
						System.out.println("IGST Amount" + Form_IGST);

						// hs.put("Input IGST Amount", Form_SGST);

					}

					else if (message.equalsIgnoreCase("CESS Amount")) {
						Form_IGST_CESS = driver.findElement(
								By.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText();
						System.out.println("CESS1 Amount" + Form_IGST_CESS);

						// hs.put("CESS Amount", Form_CESS);
					}

				}

				String Form_Total_Tax_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount"))).getText();
				System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
				Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
						.getText();
				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
				Thread.sleep(500);

				String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount")))
						.getText();
				System.out.println("Total Amount:- " + Form_Total_Amount);
				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Customer_Close = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
				Customer_Close.click();
				Thread.sleep(1000);

				WebElement Statutory_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
				Statutory_Add.click();

				Thread.sleep(1000);

				WebElement Report_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
				Report_Add.click();
				Thread.sleep(1000);

				WebElement GSTR_Report_Add = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
				GSTR_Report_Add.click();
				Thread.sleep(1000);

				WebElement GSTR1_Report_Click = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Report_Click"))));
				GSTR1_Report_Click.click();
				Thread.sleep(1000);

				// From Date -

				WebElement From_Date = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Date"))));
				From_Date.click();
				Thread.sleep(1000);

				// Click on Today -->

				WebElement From_Today = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Today"))));
				From_Today.click();
				Thread.sleep(1000);

				// To Date -

				WebElement To_Date = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Date"))));
				To_Date.click();
				Thread.sleep(1000);

				// Click on Today -->

				WebElement To_Today = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Today"))));
				To_Today.click();
				Thread.sleep(1000);

				// To fetch -->

				WebElement Fetch_Summery = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Fetch_Summery"))));
				Fetch_Summery.click();
				Thread.sleep(1000);

				// View -->

				WebElement B2B_Invoices_Section_View = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("B2B_Invoices_Section_View"))));
				B2B_Invoices_Section_View.click();
				Thread.sleep(1000);

				// Input -->

				WebElement GSTR1_Search_ID = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
				GSTR1_Search_ID.sendKeys(Form_Document_No2);
				Thread.sleep(2000);

				// To fetch -->

				// WebElement Fetch_Details =
				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
				// Fetch_Details.click();
				// Thread.sleep(1000);

				String GSTR1_IGST_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR1_IGST_Amount")))
						.getText();
				System.out.println("GSTR1 IGST Amount:- " + GSTR1_IGST_Amount);
				Thread.sleep(500);

				// 3B_CESS Amount

				String GSTR1_CESS_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR1_CESS_Amount")))
						.getText();
				System.out.println("GSTR1 CESS Amount:- " + GSTR1_CESS_Amount);
				Thread.sleep(500);

				String GSTR1_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR1_Taxable_Amount")))
						.getText();
				System.out.println("GSTR1 Taxable Amount (Subtotal):- " + GSTR1_Taxable_Amount);
				Thread.sleep(1000);

				// 3B_Total Tax Amount

				String GSTR1_Total_Tax_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("GSTR1_Total_Tax_Amount"))).getText();
				System.out.println("GSTR1 Total Tax Amount:- " + GSTR1_Total_Tax_Amount);
				Thread.sleep(500);

				String GSTR1_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR1_Total_Amount")))
						.getText();
				System.out.println("GSTR1 Total Amount :- " + GSTR1_Total_Amount);
				Thread.sleep(1000);

				if (Form_IGST.equals(GSTR1_IGST_Amount) && (Form_IGST_CESS.equals(GSTR1_CESS_Amount)
						&& (Form_SubTotal_Amount.equals(GSTR1_Taxable_Amount))
						&& (Form_Total_Tax_Amount.equals(GSTR1_Total_Tax_Amount)
								&& (Form_Total_Amount.equals(GSTR1_Total_Amount))))) {
					System.out.println(
							" All values of IGST, CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section B2B Invoices (4A,4B,4C,6B,6C) ");
				}

				else {
					System.out.println(
							"Values are Not Matching with GSTR1 report values in section B2B Invoices (4A,4B,4C,6B,6C) ");
				}

				System.out.println("********************************************************************");

				System.out.println("********************************************************************");

				driver.navigate().refresh();

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_SEZ_WOPAY_InterState_GSTR1(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "1";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();
			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

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

			WebElement Customer_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
			Customer_Close.click();
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

			WebElement GSTR1_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Report_Click"))));
			GSTR1_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -

			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement B2B_Invoices_Section_View = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("B2B_Invoices_Section_View"))));
			B2B_Invoices_Section_View.click();
			Thread.sleep(1000);

			// Input -->

			WebElement GSTR1_Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
			GSTR1_Search_ID.sendKeys(Form_Document_No2);
			Thread.sleep(2000);

			// To fetch -->

			// WebElement Fetch_Details =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			// Fetch_Details.click();
			// Thread.sleep(1000);

			String GSTR1_Taxable_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR1_Taxable_Amount")))
					.getText();
			System.out.println("GSTR1 Taxable Amount (Subtotal):- " + GSTR1_Taxable_Amount);
			Thread.sleep(1000);

			// 3B_Total Tax Amount

			String GSTR1_Total_Tax_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR1_Total_Tax_Amount")))
					.getText();
			System.out.println("GSTR1 Total Tax Amount:- " + GSTR1_Total_Tax_Amount);
			Thread.sleep(500);

			String GSTR1_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("GSTR1_Total_Amount")))
					.getText();
			System.out.println("GSTR1 Total Amount :- " + GSTR1_Total_Amount);
			Thread.sleep(1000);

			if ((Form_SubTotal_Amount.equals(GSTR1_Taxable_Amount)
					&& (Form_Total_Tax_Amount.equals(GSTR1_Total_Tax_Amount)
							&& (Form_Total_Amount.equals(GSTR1_Total_Amount))))) {
				System.out.println(
						" All values of IGST, CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section B2B Invoices (4A,4B,4C,6B,6C) ");
			}

			else {
				System.out.println(
						"Values are Not Matching with GSTR1 report values in section B2B Invoices (4A,4B,4C,6B,6C) ");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_UnRegistered_IntraState_GSTR1(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {

			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			// //*[text()='Product Category:
			// (None)']/parent::div/following-sibling::div/div[1]/table/tbody/tr/td[1]/div/div
			// <-- Actual path

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			// WebElement SelectPro2 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath
			// ("(//*[@class='x-grid3-col x-grid3-cell x-grid3-td-checker
			// x-grid3-cell-first ']) [3]")));
			// SelectPro2.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			// WebElement Price_ok =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td/table/tbody/tr/td[2]/em)
			// [1]")));
			// Price_ok.click();
			// Thread.sleep(1000);

			// For 1st product add

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "1";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price_in_SI"))));
			Unit_Price_in_SI.click();

			String Unit_price = "250000";
			WebElement Unit_Price_Clear_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_SI"))));
			Unit_Price_Clear_in_SI.clear();
			Unit_Price_Clear_in_SI.sendKeys(Unit_price);
			Thread.sleep(1000);

			WebElement Save1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save1.click();
			Thread.sleep(2000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();
			System.out.println("Document No :-" + Form_Document_No1);
			Thread.sleep(1000);

			String s1 = Form_Document_No1;
			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			{
				String Form_SGST = "";
				String Form_CGST = "";
				String Form_CESS = "";

				// HashMap<String,String> hs = new HashMap<String,String>();

				for (int i = 1; i <= 3; i++) {
					String message = driver.findElement(By.xpath(
							"//*[text()='Output CGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
							.getText();

					if (message.equalsIgnoreCase("Output SGST Amount")) {
						Form_SGST = driver.findElement(
								By.xpath("//*[text()='Output SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText(); // A B C
						System.out.println("SGST Amount" + Form_SGST);

						// hs.put("Input SGST Amount", Form_SGST);

					}

					else if (message.equalsIgnoreCase("Output CGST Amount")) {
						Form_CGST = driver.findElement(
								By.xpath("//*[text()='Output SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText(); // B A A
						System.out.println("CGST Amount" + Form_CGST);

						// hs.put("Input CGST Amount", Form_CGST);

					}

					else if (message.equalsIgnoreCase("CESS Amount")) {
						Form_CESS = driver.findElement(
								By.xpath("//*[text()='Output SGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText(); // C C B
						System.out.println("CESS Amount" + Form_CESS);

						// hs.put("CESS Amount", Form_CESS);
					}

				}

				// String input_SGST_Amount = (String)hs.get("Input SGST
				// Amount");

				String Form_Total_Tax_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount"))).getText();

				System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);

				Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount")))
						.getText();

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Customer_Close = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
				Customer_Close.click();

				Thread.sleep(1000);

				WebElement Statutory_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
				Statutory_Add.click();

				Thread.sleep(1000);

				WebElement Report_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
				Report_Add.click();

				Thread.sleep(1000);

				WebElement GSTR_Report_Add = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
				GSTR_Report_Add.click();

				Thread.sleep(1000);

				WebElement GSTR1_Report_Click = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Report_Click"))));
				GSTR1_Report_Click.click();
				Thread.sleep(1000);

				// From Date -

				WebElement From_Date = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Date"))));
				From_Date.click();
				Thread.sleep(1000);

				// Click on Today -->

				WebElement From_Today = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Today"))));
				From_Today.click();
				Thread.sleep(1000);

				// To Date -

				WebElement To_Date = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Date"))));
				To_Date.click();

				Thread.sleep(1000);

				// Click on Today -->

				WebElement To_Today = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Today"))));
				To_Today.click();

				Thread.sleep(1000);

				// To fetch -->

				WebElement Fetch_Summery = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Fetch_Summery"))));
				Fetch_Summery.click();

				Thread.sleep(1000);

				// View -->

				WebElement B2C_Small_Invoices_View = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("B2C_Small_Invoices_View"))));
				B2C_Small_Invoices_View.click();
				Thread.sleep(1000);

				// Input -->

				WebElement GSTR1_Search_ID = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
				GSTR1_Search_ID.sendKeys(Form_Document_No2);
				Thread.sleep(2000);

				String GSTR1_SGST_Amount = "";
				String GSTR1_CGST_Amount = "";
				String GSTR1_CESS_Amount = "";
				String GSTR1_Taxable_Amount = "";
				String GSTR1_Total_Tax_Amount = "";
				String GSTR1_Total_Amount = "";
				String GSTR1_IGST_Amount = "";

				for (int i = 10; i <= 16; i++)

				{
					String Tax_Name = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]"))
							.getText();

					if (Tax_Name.equalsIgnoreCase("Taxable Amount")) {

						GSTR1_Taxable_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 Taxable Amount (Subtotal):- " + GSTR1_Taxable_Amount);

					}

					else if (Tax_Name.equalsIgnoreCase("Integrated Tax Amount"))

					{

						GSTR1_IGST_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 IGST Amount:- " + GSTR1_IGST_Amount);

					}

					else if (Tax_Name.equalsIgnoreCase("Central Tax Amount"))

					{

						GSTR1_CGST_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 CGST Amount:- " + GSTR1_CGST_Amount);

					}

					else if (Tax_Name.equalsIgnoreCase("State Tax Amount"))

					{

						GSTR1_SGST_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 SGST Amount:- " + GSTR1_SGST_Amount);

					}

					else if (Tax_Name.equalsIgnoreCase("Total Tax Amount"))

					{

						GSTR1_Total_Tax_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 Total tax Amount:- " + GSTR1_Total_Tax_Amount);

					}

					else if (Tax_Name.equalsIgnoreCase("Cess Amount"))

					{

						GSTR1_CESS_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 CESS Amount:- " + GSTR1_CESS_Amount);

					}

					else if (Tax_Name.equalsIgnoreCase("Total Amount Incl Tax"))

					{

						GSTR1_Total_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);

					}

				}

				if (Form_SGST.equals(GSTR1_SGST_Amount) && (Form_CGST.equals(GSTR1_CGST_Amount)
						&& (Form_CESS.equals(GSTR1_CESS_Amount) && (Form_SubTotal_Amount.equals(GSTR1_Taxable_Amount))
								&& (Form_Total_Tax_Amount.equals(GSTR1_Total_Tax_Amount)
										&& (Form_Total_Amount.equals(GSTR1_Total_Amount)))))) {
					System.out.println(
							" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section B2C Small Invoices ");
				}

				else {
					System.out
							.println("Values are Not Matching with GSTR1 report values in section B2C Small Invoices ");
				}

				System.out.println("********************************************************************");

				System.out.println("********************************************************************");

				driver.navigate().refresh();

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_UnRegistered_InterState_GSTR1(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "1";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price_in_SI"))));
			Unit_Price_in_SI.click();

			String Unit_price = "250000";
			WebElement Unit_Price_Clear_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_SI"))));
			Unit_Price_Clear_in_SI.clear();
			Unit_Price_Clear_in_SI.sendKeys(Unit_price);
			Thread.sleep(1000);

			WebElement Save1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save1.click();
			Thread.sleep(2000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();
			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			{

				String Form_IGST = null;
				String Form_IGST_CESS = null;

				// HashMap<String,String> hs = new HashMap<String,String>();

				for (int i = 1; i <= 2; i++) {
					String message = driver.findElement(By.xpath(
							"//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
							.getText();

					if (message.equalsIgnoreCase("Output IGST Amount")) {
						Form_IGST = driver.findElement(
								By.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText();
						System.out.println("IGST Amount" + Form_IGST);

						// hs.put("Input IGST Amount", Form_SGST);

					}

					else if (message.equalsIgnoreCase("CESS Amount")) {
						Form_IGST_CESS = driver.findElement(
								By.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText();
						System.out.println("CESS1 Amount" + Form_IGST_CESS);

						// hs.put("CESS Amount", Form_CESS);
					}

				}

				String Form_Total_Tax_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount"))).getText();
				System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
				Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
						.getText();
				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
				Thread.sleep(500);

				String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount")))
						.getText();
				System.out.println("Total Amount:- " + Form_Total_Amount);
				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Customer_Close = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
				Customer_Close.click();
				Thread.sleep(1000);

				WebElement Statutory_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
				Statutory_Add.click();

				Thread.sleep(1000);

				WebElement Report_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
				Report_Add.click();
				Thread.sleep(1000);

				WebElement GSTR_Report_Add = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
				GSTR_Report_Add.click();
				Thread.sleep(1000);

				WebElement GSTR1_Report_Click = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Report_Click"))));
				GSTR1_Report_Click.click();
				Thread.sleep(1000);

				// From Date -

				WebElement From_Date = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Date"))));
				From_Date.click();
				Thread.sleep(1000);

				// Click on Today -->

				WebElement From_Today = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Today"))));
				From_Today.click();
				Thread.sleep(1000);

				// To Date -

				WebElement To_Date = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Date"))));
				To_Date.click();
				Thread.sleep(1000);

				// Click on Today -->

				WebElement To_Today = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Today"))));
				To_Today.click();
				Thread.sleep(1000);

				// To fetch -->

				WebElement Fetch_Summery = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Fetch_Summery"))));
				Fetch_Summery.click();
				Thread.sleep(1000);

				// View -->

				WebElement B2C_Large_Invoices_View = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("B2C_Large_Invoices_View"))));
				B2C_Large_Invoices_View.click();
				Thread.sleep(1000);

				// Input -->

				WebElement GSTR1_Search_ID = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
				GSTR1_Search_ID.sendKeys(Form_Document_No2);
				Thread.sleep(2000);

				// To fetch -->

				// WebElement Fetch_Details =
				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
				// Fetch_Details.click();
				// Thread.sleep(1000);

				String GSTR1_SGST_Amount = "";
				String GSTR1_CGST_Amount = "";
				String GSTR1_CESS_Amount = "";
				String GSTR1_Taxable_Amount = "";
				String GSTR1_Total_Tax_Amount = "";
				String GSTR1_Total_Amount = "";
				String GSTR1_IGST_Amount = "";

				for (int i = 9; i <= 15; i++)

				{
					String Tax_Name = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]"))
							.getText();

					if (Tax_Name.equalsIgnoreCase("Taxable Amount")) {

						GSTR1_Taxable_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 Taxable Amount (Subtotal):- " + GSTR1_Taxable_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Integrated Tax Amount"))

					{

						GSTR1_IGST_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 IGST Amount:- " + GSTR1_IGST_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Central Tax Amount"))

					{

						GSTR1_CGST_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 CGST Amount:- " + GSTR1_CGST_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("State Tax Amount"))

					{

						GSTR1_SGST_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 SGST Amount:- " + GSTR1_SGST_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Total Tax Amount"))

					{

						GSTR1_Total_Tax_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 Total tax Amount:- " + GSTR1_Total_Tax_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Cess Amount"))

					{

						GSTR1_CESS_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 CESS Amount:- " + GSTR1_CESS_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Total Amount Incl Tax"))

					{

						GSTR1_Total_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);
						Thread.sleep(500);

					}

				}

				if (Form_IGST.equals(GSTR1_IGST_Amount) && (Form_IGST_CESS.equals(GSTR1_CESS_Amount)
						&& (Form_SubTotal_Amount.equals(GSTR1_Taxable_Amount))
						&& (Form_Total_Tax_Amount.equals(GSTR1_Total_Tax_Amount)
								&& (Form_Total_Amount.equals(GSTR1_Total_Amount))))) {
					System.out.println(
							" All values of IGST, CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section B2C Large Invoices (5A, 5B)");
				}

				else {
					System.out.println(
							"Values are Not Matching with GSTR1 report values in section B2C Large Invoices (5A, 5B)");
				}

				System.out.println("********************************************************************");

				System.out.println("********************************************************************");

				driver.navigate().refresh();

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_UnRegistered_InterState_GSTR1_Amount_less(WebDriver driver,
			String Customer_Code, String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "1";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price_in_SI"))));
			Unit_Price_in_SI.click();

			String Unit_price = "2750";
			WebElement Unit_Price_Clear_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_SI"))));
			Unit_Price_Clear_in_SI.clear();
			Unit_Price_Clear_in_SI.sendKeys(Unit_price);
			Thread.sleep(1000);

			WebElement Save1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save1.click();
			Thread.sleep(2000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();
			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			{

				String Form_IGST = null;
				String Form_IGST_CESS = null;

				// HashMap<String,String> hs = new HashMap<String,String>();

				for (int i = 1; i <= 2; i++) {
					String message = driver.findElement(By.xpath(
							"//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
							.getText();

					if (message.equalsIgnoreCase("Output IGST Amount")) {
						Form_IGST = driver.findElement(
								By.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText();
						System.out.println("IGST Amount" + Form_IGST);

						// hs.put("Input IGST Amount", Form_SGST);

					}

					else if (message.equalsIgnoreCase("CESS Amount")) {
						Form_IGST_CESS = driver.findElement(
								By.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText();
						System.out.println("CESS1 Amount" + Form_IGST_CESS);

						// hs.put("CESS Amount", Form_CESS);
					}

				}

				String Form_Total_Tax_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount"))).getText();
				System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
				Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
						.getText();
				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
				Thread.sleep(500);

				String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount")))
						.getText();
				System.out.println("Total Amount:- " + Form_Total_Amount);
				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Customer_Close = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
				Customer_Close.click();
				Thread.sleep(1000);

				WebElement Statutory_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
				Statutory_Add.click();

				Thread.sleep(1000);

				WebElement Report_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
				Report_Add.click();
				Thread.sleep(1000);

				WebElement GSTR_Report_Add = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
				GSTR_Report_Add.click();
				Thread.sleep(1000);

				WebElement GSTR1_Report_Click = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Report_Click"))));
				GSTR1_Report_Click.click();
				Thread.sleep(1000);

				// From Date -

				WebElement From_Date = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Date"))));
				From_Date.click();
				Thread.sleep(1000);

				// Click on Today -->

				WebElement From_Today = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Today"))));
				From_Today.click();
				Thread.sleep(1000);

				// To Date -

				WebElement To_Date = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Date"))));
				To_Date.click();
				Thread.sleep(1000);

				// Click on Today -->

				WebElement To_Today = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Today"))));
				To_Today.click();
				Thread.sleep(1000);

				// To fetch -->

				WebElement Fetch_Summery = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Fetch_Summery"))));
				Fetch_Summery.click();
				Thread.sleep(1000);

				// View -->

				WebElement B2C_Small_Invoices_View = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("B2C_Small_Invoices_View"))));
				B2C_Small_Invoices_View.click();
				Thread.sleep(1000);

				// Input -->

				WebElement GSTR1_Search_ID = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
				GSTR1_Search_ID.sendKeys(Form_Document_No2);
				Thread.sleep(2000);

				// To fetch -->

				// WebElement Fetch_Details =
				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
				// Fetch_Details.click();
				// Thread.sleep(1000);

				String GSTR1_SGST_Amount = "";
				String GSTR1_CGST_Amount = "";
				String GSTR1_CESS_Amount = "";
				String GSTR1_Taxable_Amount = "";
				String GSTR1_Total_Tax_Amount = "";
				String GSTR1_Total_Amount = "";
				String GSTR1_IGST_Amount = "";

				for (int i = 10; i <= 16; i++)

				{
					String Tax_Name = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]"))
							.getText();

					if (Tax_Name.equalsIgnoreCase("Taxable Amount")) {

						GSTR1_Taxable_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 Taxable Amount (Subtotal):- " + GSTR1_Taxable_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Integrated Tax Amount"))

					{

						GSTR1_IGST_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 IGST Amount:- " + GSTR1_IGST_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Central Tax Amount"))

					{

						GSTR1_CGST_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 CGST Amount:- " + GSTR1_CGST_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("State Tax Amount"))

					{

						GSTR1_SGST_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 SGST Amount:- " + GSTR1_SGST_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Total Tax Amount"))

					{

						GSTR1_Total_Tax_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 Total tax Amount:- " + GSTR1_Total_Tax_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Cess Amount"))

					{

						GSTR1_CESS_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 CESS Amount:- " + GSTR1_CESS_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Total Amount Incl Tax"))

					{

						GSTR1_Total_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);
						Thread.sleep(500);

					}

				}

				System.out.println("Hi Team");

				if (Form_IGST.equals(GSTR1_IGST_Amount) && (Form_IGST_CESS.equals(GSTR1_CESS_Amount)
						&& (Form_SubTotal_Amount.equals(GSTR1_Taxable_Amount))
						&& (Form_Total_Tax_Amount.equals(GSTR1_Total_Tax_Amount)
								&& (Form_Total_Amount.equals(GSTR1_Total_Amount))))) {
					System.out.println(
							" All values of IGST, CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section B2C Small Invoices");
				}

				else {
					System.out
							.println("Values are Not Matching with GSTR1 report values in section B2C Small Invoices");
				}

				System.out.println("********************************************************************");

				System.out.println("********************************************************************");

				driver.navigate().refresh();

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_UnRegistered_Export_WPAY_InterState_GSTR1_Export_Invoices(WebDriver driver,
			String Customer_Code, String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "1";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price_in_SI"))));
			Unit_Price_in_SI.click();

			String Unit_price = "2859.27";
			WebElement Unit_Price_Clear_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_SI"))));
			Unit_Price_Clear_in_SI.clear();
			Unit_Price_Clear_in_SI.sendKeys(Unit_price);
			Thread.sleep(1000);

			WebElement Save1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save1.click();
			Thread.sleep(2000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();
			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			{

				String Form_IGST = null;

				// HashMap<String,String> hs = new HashMap<String,String>();

				for (int i = 1; i <= 1; i++) {
					String message = driver.findElement(By.xpath(
							"//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i + "]/td[1]"))
							.getText();

					if (message.equalsIgnoreCase("Output IGST Amount")) {
						Form_IGST = driver.findElement(
								By.xpath("//*[text()='Output IGST Amount ']/ancestor::div[1]/table[1]/tbody/tr[" + i
										+ "]/td[2]"))
								.getText();
						System.out.println("IGST Amount" + Form_IGST);

						// hs.put("Input IGST Amount", Form_SGST);

					}

				}

				String Form_Total_Tax_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount"))).getText();
				System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
				Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
						.getText();
				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
				Thread.sleep(500);

				String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount")))
						.getText();
				System.out.println("Total Amount:- " + Form_Total_Amount);
				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Customer_Close = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
				Customer_Close.click();
				Thread.sleep(1000);

				WebElement Statutory_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
				Statutory_Add.click();

				Thread.sleep(1000);

				WebElement Report_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
				Report_Add.click();
				Thread.sleep(1000);

				WebElement GSTR_Report_Add = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
				GSTR_Report_Add.click();
				Thread.sleep(1000);

				WebElement GSTR1_Report_Click = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Report_Click"))));
				GSTR1_Report_Click.click();
				Thread.sleep(1000);

				// From Date -

				WebElement From_Date = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Date"))));
				From_Date.click();
				Thread.sleep(1000);

				// Click on Today -->

				WebElement From_Today = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Today"))));
				From_Today.click();
				Thread.sleep(1000);

				// To Date -

				WebElement To_Date = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Date"))));
				To_Date.click();
				Thread.sleep(1000);

				// Click on Today -->

				WebElement To_Today = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Today"))));
				To_Today.click();
				Thread.sleep(1000);

				// To fetch -->

				WebElement Fetch_Summery = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Fetch_Summery"))));
				Fetch_Summery.click();
				Thread.sleep(1000);

				// View -->

				WebElement Export_Invoices_6A_View = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Export_Invoices_6A_View"))));
				Export_Invoices_6A_View.click();
				Thread.sleep(1000);

				// Input -->

				WebElement GSTR1_Search_ID = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
				GSTR1_Search_ID.sendKeys(Form_Document_No2);
				Thread.sleep(2000);

				// To fetch -->

				// WebElement Fetch_Details =
				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
				// Fetch_Details.click();
				// Thread.sleep(1000);

				String GSTR1_SGST_Amount = "";
				String GSTR1_CGST_Amount = "";
				String GSTR1_CESS_Amount = "";
				String GSTR1_Taxable_Amount = "";
				String GSTR1_Total_Tax_Amount = "";
				String GSTR1_Total_Amount = "";
				String GSTR1_IGST_Amount = "";

				for (int i = 9; i <= 15; i++)

				{
					String Tax_Name = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]"))
							.getText();

					if (Tax_Name.equalsIgnoreCase("Taxable Amount")) {

						GSTR1_Taxable_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 Taxable Amount (Subtotal):- " + GSTR1_Taxable_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Integrated Tax Amount"))

					{

						GSTR1_IGST_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 IGST Amount:- " + GSTR1_IGST_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Central Tax Amount"))

					{

						GSTR1_CGST_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 CGST Amount:- " + GSTR1_CGST_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("State Tax Amount"))

					{

						GSTR1_SGST_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 SGST Amount:- " + GSTR1_SGST_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Total Tax Amount"))

					{

						GSTR1_Total_Tax_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 Total tax Amount:- " + GSTR1_Total_Tax_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Cess Amount"))

					{

						GSTR1_CESS_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 CESS Amount:- " + GSTR1_CESS_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Total Amount Incl Tax"))

					{

						GSTR1_Total_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);
						Thread.sleep(500);

					}

				}

				if (Form_IGST.equals(GSTR1_IGST_Amount) && (Form_SubTotal_Amount.equals(GSTR1_Taxable_Amount))
						&& (Form_Total_Tax_Amount.equals(GSTR1_Total_Tax_Amount)
								&& (Form_Total_Amount.equals(GSTR1_Total_Amount)))) {
					System.out.println(
							" All values of IGST, Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section Export Invoices (6A)");
				}

				else {
					System.out.println(
							"Values are Not Matching with GSTR1 report values in section Export Invoices (6A)");
				}

				System.out.println("********************************************************************");

				System.out.println("********************************************************************");

				driver.navigate().refresh();

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_UnRegistered_Export_WOPAY_InterState_GSTR1_Export_Invoices(WebDriver driver,
			String Customer_Code, String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "1";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);
			Thread.sleep(1000);

			WebElement Unit_Price_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price_in_SI"))));
			Unit_Price_in_SI.click();

			String Unit_price = "9627.53";
			WebElement Unit_Price_Clear_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Price1_in_SI"))));
			Unit_Price_Clear_in_SI.clear();
			Unit_Price_Clear_in_SI.sendKeys(Unit_price);
			Thread.sleep(1000);

			WebElement Save1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save1.click();
			Thread.sleep(2000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(2000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();
			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			{

				String Form_Total_Tax_Amount = driver
						.findElement(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount"))).getText();
				System.out.println("Total Tax Amount:- " + Form_Total_Tax_Amount);
				Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
						.getText();
				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);
				Thread.sleep(500);

				String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount")))
						.getText();
				System.out.println("Total Amount:- " + Form_Total_Amount);
				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Customer_Close = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
				Customer_Close.click();
				Thread.sleep(1000);

				WebElement Statutory_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Statutory_Add"))));
				Statutory_Add.click();

				Thread.sleep(1000);

				WebElement Report_Add = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Report_Add"))));
				Report_Add.click();
				Thread.sleep(1000);

				WebElement GSTR_Report_Add = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR_Report_Add"))));
				GSTR_Report_Add.click();
				Thread.sleep(1000);

				WebElement GSTR1_Report_Click = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Report_Click"))));
				GSTR1_Report_Click.click();
				Thread.sleep(1000);

				// From Date -

				WebElement From_Date = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Date"))));
				From_Date.click();
				Thread.sleep(1000);

				// Click on Today -->

				WebElement From_Today = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Today"))));
				From_Today.click();
				Thread.sleep(1000);

				// To Date -

				WebElement To_Date = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Date"))));
				To_Date.click();
				Thread.sleep(1000);

				// Click on Today -->

				WebElement To_Today = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Today"))));
				To_Today.click();
				Thread.sleep(1000);

				// To fetch -->

				WebElement Fetch_Summery = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Fetch_Summery"))));
				Fetch_Summery.click();
				Thread.sleep(1000);

				// View -->

				WebElement Export_Invoices_6A_View = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Export_Invoices_6A_View"))));
				Export_Invoices_6A_View.click();
				Thread.sleep(1000);

				// Input -->

				WebElement GSTR1_Search_ID = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
				GSTR1_Search_ID.sendKeys(Form_Document_No2);
				Thread.sleep(2000);

				// To fetch -->

				// WebElement Fetch_Details =
				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
				// Fetch_Details.click();
				// Thread.sleep(1000);

				String GSTR1_SGST_Amount = "";
				String GSTR1_CGST_Amount = "";
				String GSTR1_CESS_Amount = "";
				String GSTR1_Taxable_Amount = "";
				String GSTR1_Total_Tax_Amount = "";
				String GSTR1_Total_Amount = "";
				String GSTR1_IGST_Amount = "";

				for (int i = 9; i <= 15; i++)

				{
					String Tax_Name = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]"))
							.getText();

					if (Tax_Name.equalsIgnoreCase("Taxable Amount")) {

						GSTR1_Taxable_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 Taxable Amount (Subtotal):- " + GSTR1_Taxable_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Integrated Tax Amount"))

					{

						GSTR1_IGST_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 IGST Amount:- " + GSTR1_IGST_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Central Tax Amount"))

					{

						GSTR1_CGST_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 CGST Amount:- " + GSTR1_CGST_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("State Tax Amount"))

					{

						GSTR1_SGST_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 SGST Amount:- " + GSTR1_SGST_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Total Tax Amount"))

					{

						GSTR1_Total_Tax_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 Total tax Amount:- " + GSTR1_Total_Tax_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Cess Amount"))

					{

						GSTR1_CESS_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 CESS Amount:- " + GSTR1_CESS_Amount);
						Thread.sleep(500);

					}

					else if (Tax_Name.equalsIgnoreCase("Total Amount Incl Tax"))

					{

						GSTR1_Total_Amount = driver.findElement(By
								.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
										+ i + "]/div/div"))
								.getText();
						System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);
						Thread.sleep(500);

					}

				}

				System.out.println("Hi Team");

				if (Form_SubTotal_Amount.equals(GSTR1_Taxable_Amount)
						&& (Form_Total_Amount.equals(GSTR1_Total_Amount))) {
					System.out.println(
							" All values of  SubTotal , Total Amount matching with GSTR1 report values in section Export Invoices (6A)");
				}

				else {
					System.out.println(
							"Values are Not Matching with GSTR1 report values in section Export Invoices (6A)");
				}

				System.out.println("********************************************************************");

				System.out.println("********************************************************************");

				driver.navigate().refresh();

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createSalesInvoice_NIL_Rated_Exempted_Non_GST(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Sales_Invoice_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sales_Invoice_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Sales_Invoice_Module.click();

			WebElement Customer = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer")))); //// *[@id='customer60vendorquotationundefined']
			Customer.sendKeys(Customer_Code);
			Thread.sleep(1500);
			Customer.sendKeys(Keys.ENTER);
			Thread.sleep(1500);

			WebElement Product_Add = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Add"))));
			Product_Add.click();
			Thread.sleep(1000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(5000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();

			WebElement ADD_in_SI = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_SI"))));
			ADD_in_SI.click();
			Thread.sleep(1000);

			WebElement Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity"))));
			Quantity.click();

			String quantity = "2";
			WebElement Quantity1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Quantity1"))));
			Quantity1.sendKeys(quantity);

			Thread.sleep(1000);

			WebElement Invoice_Memo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Invoice_Memo"))));
			Invoice_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1000);

			String Form_Document_No1 = driver.findElement(By.xpath(GST_pro.getProperty("Form_Document_No1"))).getText();

			System.out.println("Document No :-" + Form_Document_No1);

			Thread.sleep(1000);

			String s1 = Form_Document_No1;

			String Form_Document_No2 = s1.substring(0, 8); // extracting few
															// characters
			System.out.println(Form_Document_No2);

			System.out.println("Sales Invoice Created Successfully :- " + Form_Document_No2);

			System.out.println("********************************************************************");

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_SubTotal_Amount")))
					.getText();

			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

			Thread.sleep(500);

			String Form_Total_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Form_Total_Amount"))).getText();

			System.out.println("Total Amount:- " + Form_Total_Amount);

			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Customer_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Close"))));
			Customer_Close.click();

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

			WebElement GSTR1_Report_Click = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Report_Click"))));
			GSTR1_Report_Click.click();
			Thread.sleep(1000);

			// From Date -

			WebElement From_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Date"))));
			From_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement From_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_From_Today"))));
			From_Today.click();
			Thread.sleep(1000);

			// To Date -

			WebElement To_Date = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Date"))));
			To_Date.click();
			Thread.sleep(1000);

			// Click on Today -->

			WebElement To_Today = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_To_Today"))));
			To_Today.click();
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Summery = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Fetch_Summery"))));
			Fetch_Summery.click();
			Thread.sleep(1000);

			// View -->

			WebElement Nil_Rated_Invoices_8A_8B_8C_8D_View = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Nil_Rated_Invoices_8A_8B_8C_8D_View"))));
			Nil_Rated_Invoices_8A_8B_8C_8D_View.click();
			Thread.sleep(1000);

			// Input -->

			WebElement GSTR1_Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
			GSTR1_Search_ID.sendKeys(Form_Document_No2);
			Thread.sleep(2000);

			// To fetch -->

			// WebElement Fetch_Details =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			// Fetch_Details.click();
			// Thread.sleep(1000);

			String GSTR1_SGST_Amount = "";
			String GSTR1_CGST_Amount = "";
			String GSTR1_CESS_Amount = "";
			String GSTR1_Taxable_Amount = "";
			String GSTR1_Total_Tax_Amount = "";
			String GSTR1_Total_Amount = "";
			String GSTR1_IGST_Amount = "";

			for (int i = 9; i <= 15; i++)

			{
				String Tax_Name = driver.findElement(By
						.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]"))
						.getText();

				if (Tax_Name.equalsIgnoreCase("Taxable Amount")) {

					GSTR1_Taxable_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div"))
							.getText();
					System.out.println("GSTR1 Taxable Amount (Subtotal):- " + GSTR1_Taxable_Amount);
					Thread.sleep(500);

				}

				else if (Tax_Name.equalsIgnoreCase("Integrated Tax Amount"))

				{

					GSTR1_IGST_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div"))
							.getText();
					System.out.println("GSTR1 IGST Amount:- " + GSTR1_IGST_Amount);
					Thread.sleep(500);

				}

				else if (Tax_Name.equalsIgnoreCase("Central Tax Amount"))

				{

					GSTR1_CGST_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div"))
							.getText();
					System.out.println("GSTR1 CGST Amount:- " + GSTR1_CGST_Amount);
					Thread.sleep(500);

				}

				else if (Tax_Name.equalsIgnoreCase("State Tax Amount"))

				{

					GSTR1_SGST_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div"))
							.getText();
					System.out.println("GSTR1 SGST Amount:- " + GSTR1_SGST_Amount);
					Thread.sleep(500);

				}

				else if (Tax_Name.equalsIgnoreCase("Total Tax Amount"))

				{

					GSTR1_Total_Tax_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div"))
							.getText();
					System.out.println("GSTR1 Total tax Amount:- " + GSTR1_Total_Tax_Amount);
					Thread.sleep(500);

				}

				else if (Tax_Name.equalsIgnoreCase("Cess Amount"))

				{

					GSTR1_CESS_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div"))
							.getText();
					System.out.println("GSTR1 CESS Amount:- " + GSTR1_CESS_Amount);
					Thread.sleep(500);

				}

				else if (Tax_Name.equalsIgnoreCase("Total Amount Incl Tax"))

				{

					GSTR1_Total_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div"))
							.getText();
					System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);
					Thread.sleep(500);

				}

			}

			System.out.println("Hi Team");

			if (Form_SubTotal_Amount.equals(GSTR1_Taxable_Amount) && (Form_Total_Amount.equals(GSTR1_Total_Amount))) {
				System.out.println(
						" All values of  SubTotal , Total Amount matching with GSTR1 report values in Section Nil Rated Invoices - 8A, 8B, 8C, 8D");
			}

			else {
				System.out.println(
						"Values are Not Matching with GSTR1 report values in Section Nil Rated Invoices - 8A, 8B, 8C, 8D");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

}
