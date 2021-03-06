package krawler.erp.IndianGST;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class GST_Advanced_Received

{
	public static void createAdvancedReceived_IntraState(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Receive_Payment_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_Module.click();

			WebElement Receive_Payment_from_Customer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_from_Customer")))); //// area[@onclick='callVendorQuotation()']
																											//// ,
																											//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_from_Customer.click();

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='customer9receiptwindow']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Document_type_in_RP = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP"))));
			Select_Document_type_in_RP.click();

			WebElement Select_Document_type_in_RP1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP1"))));

			Select_Document_type_in_RP1.sendKeys("Advanced / Deposit");

			Thread.sleep(1000);

			WebElement Click_Advanced = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advanced"))));

			Click_Advanced.click();

			Thread.sleep(1000);

			WebElement Document_Number_in_RP = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number_in_RP"))));

			Document_Number_in_RP.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_RP"))));
			ADD_in_RP.click();
			Thread.sleep(1000);

			WebElement Amount_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_RP"))));
			Amount_in_RP.click();

			WebElement Amount1_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_RP"))));
			Amount1_in_RP.clear();

			WebElement Amount12_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount12_in_RP"))));
			Amount12_in_RP.click();

			Amount1_in_RP.sendKeys("100");

			WebElement AdvancedReceipt_Memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("AdvancedReceipt_Memo"))));
			AdvancedReceipt_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1500);

			String Form_Document_No2 = "";

			try

			{
				WebElement Doc_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@class='x-window x-window-plain x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));
				String Advanced_Receipt_No = driver.findElement(By.xpath(GST_pro.getProperty("Advanced_Receipt_No")))
						.getText();

				System.out.println("Document No :-" + Advanced_Receipt_No);

				String s1 = Advanced_Receipt_No;

				Form_Document_No2 = s1.substring(0, 7); // extracting few
														// characters
				System.out.println(Form_Document_No2);

				System.out.println("Advanced Receipt Created Successfully :- " + Form_Document_No2);

				System.out.println("********************************************************************");

			}

			catch (Exception a) {
				System.out.println("Element is not found");
			}

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

				String s2 = Form_SGST;

				String SGST = s2.substring(2, 6);

				double SGST_Amount = Double.parseDouble(SGST);

				// int SGST_Amount = Integer.parseInt(SGST);

				String s3 = Form_SGST;

				String CGST = s3.substring(2, 6);

				double CGST_Amount = Double.parseDouble(CGST);

				// int CGST_Amount = Integer.parseInt(CGST);

				String s4 = Form_CESS;

				String CESS = s4.substring(2, 6);

				double CESS_Amount = Double.parseDouble(CESS);

				// int CESS_Amount = Integer.parseInt(CESS);

				double x = SGST_Amount + CGST_Amount + CESS_Amount;

				double c = x;
				DecimalFormat f = new DecimalFormat("##.00");
				String Form_Total_Tax_Amount1 = f.format(c);

				// DecimalFormat df2 = new DecimalFormat(".##");

				// df2.format(x);

				// System.out.println("Total Tax Amount:- " + x );

				// String Form_Total_Tax_Amount1 = Double.toString(x);

				String Form_Total_Tax_Amount = "??? " + Form_Total_Tax_Amount1;

				System.out.println("Total Tax Amount:-" + Form_Total_Tax_Amount);

				// String Form_Total_Tax_Amount = Form_SGST + Form_CGST +
				// Form_CESS ;

				// System.out.println("Total Tax Amount:- " +
				// Form_Total_Tax_Amount1);

				// Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Receipt_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String s5 = Form_SubTotal_Amount;

				String SubTotal = s5.substring(2, 6);

				double SubTotal_Amount = Double.parseDouble(SubTotal);

				Thread.sleep(500);

				double y = SubTotal_Amount + x;

				double d = y;
				DecimalFormat f1 = new DecimalFormat("##.00");
				String Form_Total_Amount1 = f1.format(d);

				// System.out.println(Form_Total_Amount1);

				// System.out.println("Total Tax Amount:- " + x );

				// String Form_Total_Amount1 = Double.toString(y);

				String Form_Total_Amount = "??? " + Form_Total_Amount1;

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Receive_Payment_close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_close"))));
				Receive_Payment_close.click();

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

				driver.navigate().refresh();
				Thread.sleep(2000);

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createAdvancedReceived_InterState(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Receive_Payment_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_Module.click();

			WebElement Receive_Payment_from_Customer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_from_Customer")))); //// area[@onclick='callVendorQuotation()']
																											//// ,
																											//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_from_Customer.click();

			// WebElement Select_Customer_in_Receipt =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_in_Receipt"))));
			// ////*[@id='customer60vendorquotationundefined']
			// Select_Customer_in_Receipt.sendKeys(Customer_Code);
			// Thread.sleep(1000);
			// Select_Customer_in_Receipt.sendKeys(Keys.ENTER);
			// Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='customer9receiptwindow']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Document_type_in_RP = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP"))));
			Select_Document_type_in_RP.click();

			WebElement Select_Document_type_in_RP1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP1"))));

			Select_Document_type_in_RP1.sendKeys("Advanced / Deposit");

			Thread.sleep(1000);

			WebElement Click_Advanced = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advanced"))));

			Click_Advanced.click();

			Thread.sleep(1000);

			WebElement Document_Number_in_RP = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number_in_RP"))));

			Document_Number_in_RP.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_RP"))));
			ADD_in_RP.click();
			Thread.sleep(1000);

			WebElement Amount_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_RP"))));
			Amount_in_RP.click();

			WebElement Amount1_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_RP"))));
			Amount1_in_RP.clear();

			WebElement Amount12_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount12_in_RP"))));
			Amount12_in_RP.click();

			Amount1_in_RP.sendKeys("100");

			WebElement AdvancedReceipt_Memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("AdvancedReceipt_Memo"))));
			AdvancedReceipt_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1500);

			String Form_Document_No2 = "";

			try

			{ // WebElement Doc_no =
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='x-window
				// x-window-plain
				// x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));

				WebElement Doc_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@class='x-window x-window-plain x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));
				Doc_no.click();

				String Advanced_Receipt_No = driver.findElement(By.xpath(GST_pro.getProperty("Advanced_Receipt_No")))
						.getText();

				System.out.println("Document No :-" + Advanced_Receipt_No);

				Thread.sleep(1000);

				String s1 = Advanced_Receipt_No;

				Form_Document_No2 = s1.substring(0, 7); // extracting few
														// characters
				System.out.println(Form_Document_No2);

				System.out.println("Advanced Receipt Created Successfully :- " + Form_Document_No2);

				System.out.println("********************************************************************");

			}

			catch (Exception a) {
				System.out.println("Element is not found");
			}

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

				String s2 = Form_IGST;

				String IGST = s2.substring(2, 6);

				double IGST_Amount = Double.parseDouble(IGST);

				// int SGST_Amount = Integer.parseInt(SGST);

				String s4 = Form_IGST_CESS;

				String CESS = s4.substring(2, 6);

				double CESS_Amount = Double.parseDouble(CESS);

				// int CESS_Amount = Integer.parseInt(CESS);

				double x = IGST_Amount + CESS_Amount;

				double c = x;
				DecimalFormat f = new DecimalFormat("##.00");
				String Form_Total_Tax_Amount1 = f.format(c);

				// DecimalFormat df2 = new DecimalFormat(".##");

				// df2.format(x);

				// System.out.println("Total Tax Amount:- " + x );

				// String Form_Total_Tax_Amount1 = Double.toString(x);

				String Form_Total_Tax_Amount = "??? " + Form_Total_Tax_Amount1;

				System.out.println("Total Tax Amount:-" + Form_Total_Tax_Amount);

				// String Form_Total_Tax_Amount = Form_SGST + Form_CGST +
				// Form_CESS ;

				// System.out.println("Total Tax Amount:- " +
				// Form_Total_Tax_Amount1);

				// Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Receipt_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String s5 = Form_SubTotal_Amount;

				String SubTotal = s5.substring(2, 6);

				double SubTotal_Amount = Double.parseDouble(SubTotal);

				Thread.sleep(500);

				double y = SubTotal_Amount + x;

				double d = y;
				DecimalFormat f1 = new DecimalFormat("##.00");
				String Form_Total_Amount1 = f1.format(d);

				// System.out.println(Form_Total_Amount1);

				// System.out.println("Total Tax Amount:- " + x );

				// String Form_Total_Amount1 = Double.toString(y);

				String Form_Total_Amount = "??? " + Form_Total_Amount1;

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Receive_Payment_close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_close"))));
				Receive_Payment_close.click();

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

				driver.navigate().refresh();
				Thread.sleep(2000);

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createAdvancedReceived_SEZ_WPAY_InterState(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Receive_Payment_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_Module.click();

			WebElement Receive_Payment_from_Customer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_from_Customer")))); //// area[@onclick='callVendorQuotation()']
																											//// ,
																											//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_from_Customer.click();

			// WebElement Select_Customer_in_Receipt =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_in_Receipt"))));
			// ////*[@id='customer60vendorquotationundefined']
			// Select_Customer_in_Receipt.sendKeys(Customer_Code);
			// Thread.sleep(1000);
			// Select_Customer_in_Receipt.sendKeys(Keys.ENTER);
			// Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='customer9receiptwindow']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Document_type_in_RP = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP"))));
			Select_Document_type_in_RP.click();

			WebElement Select_Document_type_in_RP1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP1"))));

			Select_Document_type_in_RP1.sendKeys("Advanced / Deposit");

			Thread.sleep(1000);

			WebElement Click_Advanced = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advanced"))));

			Click_Advanced.click();

			Thread.sleep(1000);

			WebElement Document_Number_in_RP = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number_in_RP"))));

			Document_Number_in_RP.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_RP"))));
			ADD_in_RP.click();
			Thread.sleep(1000);

			WebElement Amount_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_RP"))));
			Amount_in_RP.click();

			WebElement Amount1_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_RP"))));
			Amount1_in_RP.clear();

			WebElement Amount12_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount12_in_RP"))));
			Amount12_in_RP.click();

			Amount1_in_RP.sendKeys("100");

			WebElement AdvancedReceipt_Memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("AdvancedReceipt_Memo"))));
			AdvancedReceipt_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1500);

			String Form_Document_No2 = "";

			try

			{
				WebElement Doc_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@class='x-window x-window-plain x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));

				String Advanced_Receipt_No = driver.findElement(By.xpath(GST_pro.getProperty("Advanced_Receipt_No")))
						.getText();

				System.out.println("Document No :-" + Advanced_Receipt_No);

				Thread.sleep(1000);

				String s1 = Advanced_Receipt_No;

				Form_Document_No2 = s1.substring(0, 7); // extracting few
														// characters
				System.out.println(Form_Document_No2);

				System.out.println("Advanced Receipt Created Successfully :- " + Form_Document_No2);

				System.out.println("********************************************************************");

			}

			catch (Exception a) {
				System.out.println("Element is not found");
			}

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

				String s2 = Form_IGST;

				String IGST = s2.substring(2, 6);

				double IGST_Amount = Double.parseDouble(IGST);

				// int SGST_Amount = Integer.parseInt(SGST);

				String s4 = Form_IGST_CESS;

				String CESS = s4.substring(2, 6);

				double CESS_Amount = Double.parseDouble(CESS);

				// int CESS_Amount = Integer.parseInt(CESS);

				double x = IGST_Amount + CESS_Amount;

				double c = x;
				DecimalFormat f = new DecimalFormat("##.00");
				String Form_Total_Tax_Amount1 = f.format(c);

				// DecimalFormat df2 = new DecimalFormat(".##");

				// df2.format(x);

				// System.out.println("Total Tax Amount:- " + x );

				// String Form_Total_Tax_Amount1 = Double.toString(x);

				String Form_Total_Tax_Amount = "??? " + Form_Total_Tax_Amount1;

				System.out.println("Total Tax Amount:-" + Form_Total_Tax_Amount);

				// String Form_Total_Tax_Amount = Form_SGST + Form_CGST +
				// Form_CESS ;

				// System.out.println("Total Tax Amount:- " +
				// Form_Total_Tax_Amount1);

				// Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Receipt_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String s5 = Form_SubTotal_Amount;

				String SubTotal = s5.substring(2, 6);

				double SubTotal_Amount = Double.parseDouble(SubTotal);

				Thread.sleep(500);

				double y = SubTotal_Amount + x;

				double d = y;
				DecimalFormat f1 = new DecimalFormat("##.00");
				String Form_Total_Amount1 = f1.format(d);

				// System.out.println(Form_Total_Amount1);

				// System.out.println("Total Tax Amount:- " + x );

				// String Form_Total_Amount1 = Double.toString(y);

				String Form_Total_Amount = "??? " + Form_Total_Amount1;

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Receive_Payment_close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_close"))));
				Receive_Payment_close.click();

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

				driver.navigate().refresh();
				Thread.sleep(2000);

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createAdvancedReceived_SEZ_WOPAY_InterState(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Receive_Payment_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_Module.click();

			WebElement Receive_Payment_from_Customer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_from_Customer")))); //// area[@onclick='callVendorQuotation()']
																											//// ,
																											//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_from_Customer.click();

			// WebElement Select_Customer_in_Receipt =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_in_Receipt"))));
			// ////*[@id='customer60vendorquotationundefined']
			// Select_Customer_in_Receipt.sendKeys(Customer_Code);
			// Thread.sleep(1000);
			// Select_Customer_in_Receipt.sendKeys(Keys.ENTER);
			// Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='customer9receiptwindow']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Document_type_in_RP = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP"))));
			Select_Document_type_in_RP.click();

			WebElement Select_Document_type_in_RP1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP1"))));

			Select_Document_type_in_RP1.sendKeys("Advanced / Deposit");

			Thread.sleep(1000);

			WebElement Click_Advanced = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advanced"))));

			Click_Advanced.click();

			Thread.sleep(1000);

			WebElement Document_Number_in_RP = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number_in_RP"))));

			Document_Number_in_RP.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_RP"))));
			ADD_in_RP.click();
			Thread.sleep(1000);

			WebElement Amount_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_RP"))));
			Amount_in_RP.click();

			WebElement Amount1_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_RP"))));
			Amount1_in_RP.clear();

			WebElement Amount12_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount12_in_RP"))));
			Amount12_in_RP.click();

			Amount1_in_RP.sendKeys("100");

			WebElement AdvancedReceipt_Memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("AdvancedReceipt_Memo"))));
			AdvancedReceipt_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1500);

			String Form_Document_No2 = "";

			try

			{
				WebElement Doc_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@class='x-window x-window-plain x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));

				String Advanced_Receipt_No = driver.findElement(By.xpath(GST_pro.getProperty("Advanced_Receipt_No")))
						.getText();

				System.out.println("Document No :-" + Advanced_Receipt_No);

				Thread.sleep(1000);

				String s1 = Advanced_Receipt_No;

				Form_Document_No2 = s1.substring(0, 7); // extracting few
														// characters
				System.out.println(Form_Document_No2);

				System.out.println("Advanced Receipt Created Successfully :- " + Form_Document_No2);

				System.out.println("********************************************************************");

			}

			catch (Exception a) {
				System.out.println("Element is not found");
			}

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			// String Form_Total_Tax_Amount = Form_SGST + Form_CGST + Form_CESS
			// ;

			// System.out.println("Total Tax Amount:- " +
			// Form_Total_Tax_Amount1);

			// Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Receipt_Amount"))).getText();

			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

			Thread.sleep(500);

			String s5 = Form_SubTotal_Amount;

			String SubTotal = s5.substring(2, 6);

			double SubTotal_Amount = Double.parseDouble(SubTotal);

			int x = 0;

			Thread.sleep(500);

			double y = SubTotal_Amount + x;

			double d = y;
			DecimalFormat f1 = new DecimalFormat("##.00");
			String Form_Total_Amount1 = f1.format(d);

			// System.out.println(Form_Total_Amount1);

			// System.out.println("Total Tax Amount:- " + x );

			// String Form_Total_Amount1 = Double.toString(y);

			String Form_Total_Amount = "??? " + Form_Total_Amount1;

			System.out.println("Total Amount:- " + Form_Total_Amount);

			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Receive_Payment_close = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_close"))));
			Receive_Payment_close.click();

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

			if ((((Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
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

			driver.navigate().refresh();

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createAdvancedReceived_Export_WPAY_InterState(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Receive_Payment_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_Module.click();

			WebElement Receive_Payment_from_Customer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_from_Customer")))); //// area[@onclick='callVendorQuotation()']
																											//// ,
																											//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_from_Customer.click();

			// WebElement Select_Customer_in_Receipt =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_in_Receipt"))));
			// ////*[@id='customer60vendorquotationundefined']
			// Select_Customer_in_Receipt.sendKeys(Customer_Code);
			// Thread.sleep(1000);
			// Select_Customer_in_Receipt.sendKeys(Keys.ENTER);
			// Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='customer9receiptwindow']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Document_type_in_RP = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP"))));
			Select_Document_type_in_RP.click();

			WebElement Select_Document_type_in_RP1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP1"))));

			Select_Document_type_in_RP1.sendKeys("Advanced / Deposit");

			Thread.sleep(1000);

			WebElement Click_Advanced = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advanced"))));

			Click_Advanced.click();

			Thread.sleep(1000);

			WebElement Document_Number_in_RP = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number_in_RP"))));

			Document_Number_in_RP.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_RP"))));
			ADD_in_RP.click();
			Thread.sleep(1000);

			WebElement Amount_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_RP"))));
			Amount_in_RP.click();

			WebElement Amount1_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_RP"))));
			Amount1_in_RP.clear();

			WebElement Amount12_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount12_in_RP"))));
			Amount12_in_RP.click();

			Amount1_in_RP.sendKeys("100");

			WebElement AdvancedReceipt_Memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("AdvancedReceipt_Memo"))));
			AdvancedReceipt_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1500);

			String Form_Document_No2 = "";

			try

			{
				WebElement Doc_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@class='x-window x-window-plain x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));

				String Advanced_Receipt_No = driver.findElement(By.xpath(GST_pro.getProperty("Advanced_Receipt_No")))
						.getText();

				System.out.println("Document No :-" + Advanced_Receipt_No);

				Thread.sleep(1000);

				String s1 = Advanced_Receipt_No;

				Form_Document_No2 = s1.substring(0, 7); // extracting few
														// characters
				System.out.println(Form_Document_No2);

				System.out.println("Advanced Receipt Created Successfully :- " + Form_Document_No2);

				System.out.println("********************************************************************");

			}

			catch (Exception a) {
				System.out.println("Element is not found");
			}

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

				String s2 = Form_IGST;

				String IGST = s2.substring(2, 6);

				double IGST_Amount = Double.parseDouble(IGST);

				// int SGST_Amount = Integer.parseInt(SGST);

				double x = IGST_Amount;

				double c = x;
				DecimalFormat f = new DecimalFormat("##.00");
				String Form_Total_Tax_Amount1 = f.format(c);

				// DecimalFormat df2 = new DecimalFormat(".##");

				// df2.format(x);

				// System.out.println("Total Tax Amount:- " + x );

				// String Form_Total_Tax_Amount1 = Double.toString(x);

				String Form_Total_Tax_Amount = "??? " + Form_Total_Tax_Amount1;

				System.out.println("Total Tax Amount:-" + Form_Total_Tax_Amount);

				// String Form_Total_Tax_Amount = Form_SGST + Form_CGST +
				// Form_CESS ;

				// System.out.println("Total Tax Amount:- " +
				// Form_Total_Tax_Amount1);

				// Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Receipt_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String s5 = Form_SubTotal_Amount;

				String SubTotal = s5.substring(2, 6);

				double SubTotal_Amount = Double.parseDouble(SubTotal);

				Thread.sleep(500);

				double y = SubTotal_Amount + x;

				double d = y;
				DecimalFormat f1 = new DecimalFormat("##.00");
				String Form_Total_Amount1 = f1.format(d);

				// System.out.println(Form_Total_Amount1);

				// System.out.println("Total Tax Amount:- " + x );

				// String Form_Total_Amount1 = Double.toString(y);

				String Form_Total_Amount = "??? " + Form_Total_Amount1;

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Receive_Payment_close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_close"))));
				Receive_Payment_close.click();

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

				driver.navigate().refresh();
				Thread.sleep(2000);

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createAdvancedReceived_Export_WOPAY_InterState(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Receive_Payment_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_Module.click();

			WebElement Receive_Payment_from_Customer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_from_Customer")))); //// area[@onclick='callVendorQuotation()']
																											//// ,
																											//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_from_Customer.click();

			// WebElement Select_Customer_in_Receipt =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_in_Receipt"))));
			// ////*[@id='customer60vendorquotationundefined']
			// Select_Customer_in_Receipt.sendKeys(Customer_Code);
			// Thread.sleep(1000);
			// Select_Customer_in_Receipt.sendKeys(Keys.ENTER);
			// Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='customer9receiptwindow']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Document_type_in_RP = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP"))));
			Select_Document_type_in_RP.click();

			WebElement Select_Document_type_in_RP1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP1"))));

			Select_Document_type_in_RP1.sendKeys("Advanced / Deposit");

			Thread.sleep(1000);

			WebElement Click_Advanced = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advanced"))));

			Click_Advanced.click();

			Thread.sleep(1000);

			WebElement Document_Number_in_RP = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number_in_RP"))));

			Document_Number_in_RP.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_RP"))));
			ADD_in_RP.click();
			Thread.sleep(1000);

			WebElement Amount_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_RP"))));
			Amount_in_RP.click();

			WebElement Amount1_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_RP"))));
			Amount1_in_RP.clear();

			WebElement Amount12_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount12_in_RP"))));
			Amount12_in_RP.click();

			Amount1_in_RP.sendKeys("100");

			WebElement AdvancedReceipt_Memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("AdvancedReceipt_Memo"))));
			AdvancedReceipt_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1500);

			String Form_Document_No2 = "";

			try

			{
				WebElement Doc_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@class='x-window x-window-plain x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));

				String Advanced_Receipt_No = driver.findElement(By.xpath(GST_pro.getProperty("Advanced_Receipt_No")))
						.getText();

				System.out.println("Document No :-" + Advanced_Receipt_No);

				String s1 = Advanced_Receipt_No;

				Form_Document_No2 = s1.substring(0, 7); // extracting few
														// characters
				System.out.println(Form_Document_No2);

				System.out.println("Advanced Receipt Created Successfully :- " + Form_Document_No2);

				System.out.println("********************************************************************");

			}

			catch (Exception a) {
				System.out.println("Element is not found");
			}

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			// String Form_Total_Tax_Amount = Form_SGST + Form_CGST + Form_CESS
			// ;

			// System.out.println("Total Tax Amount:- " +
			// Form_Total_Tax_Amount1);

			// Thread.sleep(500);

			// Subtotal --> (//*[@class='currency']) [17]

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Receipt_Amount"))).getText();

			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

			Thread.sleep(500);

			String s5 = Form_SubTotal_Amount;

			String SubTotal = s5.substring(2, 6);

			double SubTotal_Amount = Double.parseDouble(SubTotal);

			int x = 0;

			Thread.sleep(500);

			double y = SubTotal_Amount + x;

			double d = y;
			DecimalFormat f1 = new DecimalFormat("##.00");
			String Form_Total_Amount1 = f1.format(d);

			// System.out.println(Form_Total_Amount1);

			// System.out.println("Total Tax Amount:- " + x );

			// String Form_Total_Amount1 = Double.toString(y);

			String Form_Total_Amount = "??? " + Form_Total_Amount1;

			System.out.println("Total Amount:- " + Form_Total_Amount);

			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Receive_Payment_close = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_close"))));
			Receive_Payment_close.click();

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

			if ((((Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
					&& (Form_Total_Amount.equals(GSTR3B_Total_Amount)))))) {
				System.out.println(
						" All values of IGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values ");
			} else {
				System.out.println("Values are Not Matching");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();
			Thread.sleep(2000);

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createAdvancedReceived_NIL_Rated_Exempted(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Receive_Payment_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_Module.click();

			WebElement Receive_Payment_from_Customer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_from_Customer")))); //// area[@onclick='callVendorQuotation()']
																											//// ,
																											//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_from_Customer.click();

			// WebElement Select_Customer_in_Receipt =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_in_Receipt"))));
			// ////*[@id='customer60vendorquotationundefined']
			// Select_Customer_in_Receipt.sendKeys(Customer_Code);
			// Thread.sleep(1000);
			// Select_Customer_in_Receipt.sendKeys(Keys.ENTER);
			// Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='customer9receiptwindow']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Document_type_in_RP = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP"))));
			Select_Document_type_in_RP.click();

			WebElement Select_Document_type_in_RP1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP1"))));

			Select_Document_type_in_RP1.sendKeys("Advanced / Deposit");

			Thread.sleep(1000);

			WebElement Click_Advanced = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advanced"))));

			Click_Advanced.click();

			Thread.sleep(1000);

			WebElement Document_Number_in_RP = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number_in_RP"))));
			Document_Number_in_RP.click();
			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_RP"))));
			ADD_in_RP.click();
			Thread.sleep(1000);

			WebElement Amount_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_RP"))));
			Amount_in_RP.click();

			WebElement Amount1_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_RP"))));
			Amount1_in_RP.clear();

			WebElement Amount12_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount12_in_RP"))));
			Amount12_in_RP.click();

			Amount1_in_RP.sendKeys("100");

			WebElement AdvancedReceipt_Memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("AdvancedReceipt_Memo"))));
			AdvancedReceipt_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1500);

			String Form_Document_No2 = "";

			try

			{
				WebElement Doc_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@class='x-window x-window-plain x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));

				String Advanced_Receipt_No = driver.findElement(By.xpath(GST_pro.getProperty("Advanced_Receipt_No")))
						.getText();

				System.out.println("Document No :-" + Advanced_Receipt_No);

				String s1 = Advanced_Receipt_No;

				Form_Document_No2 = s1.substring(0, 7); // extracting few
														// characters
				System.out.println(Form_Document_No2);

				System.out.println("Advanced Receipt Created Successfully :- " + Form_Document_No2);

				System.out.println("********************************************************************");

			}

			catch (Exception a) {
				System.out.println("Element is not found");
			}

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			int CESS_Amount = 0;

			double x = CESS_Amount;

			double c = x;
			DecimalFormat f = new DecimalFormat("##.00");
			String Form_Total_Tax_Amount1 = f.format(c);

			String Form_Total_Tax_Amount = "??? " + Form_Total_Tax_Amount1;

			System.out.println("Total Tax Amount:-" + Form_Total_Tax_Amount);

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Receipt_Amount"))).getText();

			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

			Thread.sleep(500);

			String s5 = Form_SubTotal_Amount;

			String SubTotal = s5.substring(2, 6);

			double SubTotal_Amount = Double.parseDouble(SubTotal);

			Thread.sleep(500);

			double y = SubTotal_Amount + x;

			double d = y;
			DecimalFormat f1 = new DecimalFormat("##.00");
			String Form_Total_Amount1 = f1.format(d);

			// System.out.println(Form_Total_Amount1);

			// System.out.println("Total Tax Amount:- " + x );

			// String Form_Total_Amount1 = Double.toString(y);

			String Form_Total_Amount = "??? " + Form_Total_Amount1;

			System.out.println("Total Amount:- " + Form_Total_Amount);

			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Receive_Payment_close = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_close"))));
			Receive_Payment_close.click();

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

			if ((Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
					&& (Form_Total_Amount.equals(GSTR3B_Total_Amount)))) {
				System.out.println(" All values of SubTotal , Total Amount matching with GSTR3B report values ");
			} else {
				System.out.println("Values are Not Matching");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();
			Thread.sleep(2000);

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createAdvancedReceived_NonGST(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Receive_Payment_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_Module.click();

			WebElement Receive_Payment_from_Customer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_from_Customer")))); //// area[@onclick='callVendorQuotation()']
																											//// ,
																											//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_from_Customer.click();

			// WebElement Select_Customer_in_Receipt =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_in_Receipt"))));
			// ////*[@id='customer60vendorquotationundefined']
			// Select_Customer_in_Receipt.sendKeys(Customer_Code);
			// Thread.sleep(1000);
			// Select_Customer_in_Receipt.sendKeys(Keys.ENTER);
			// Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='customer9receiptwindow']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Document_type_in_RP = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP"))));
			Select_Document_type_in_RP.click();

			WebElement Select_Document_type_in_RP1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP1"))));

			Select_Document_type_in_RP1.sendKeys("Advanced / Deposit");

			Thread.sleep(1000);

			WebElement Click_Advanced = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advanced"))));

			Click_Advanced.click();

			Thread.sleep(1000);

			WebElement Document_Number_in_RP = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number_in_RP"))));
			Document_Number_in_RP.click();
			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_RP"))));
			ADD_in_RP.click();
			Thread.sleep(1000);

			WebElement Amount_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_RP"))));
			Amount_in_RP.click();

			WebElement Amount1_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_RP"))));
			Amount1_in_RP.clear();

			WebElement Amount12_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount12_in_RP"))));
			Amount12_in_RP.click();

			Amount1_in_RP.sendKeys("100");

			WebElement AdvancedReceipt_Memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("AdvancedReceipt_Memo"))));
			AdvancedReceipt_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1500);

			String Form_Document_No2 = "";

			try

			{
				WebElement Doc_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@class='x-window x-window-plain x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));

				String Advanced_Receipt_No = driver.findElement(By.xpath(GST_pro.getProperty("Advanced_Receipt_No")))
						.getText();

				System.out.println("Document No :-" + Advanced_Receipt_No);

				String s1 = Advanced_Receipt_No;

				Form_Document_No2 = s1.substring(0, 7); // extracting few
														// characters
				System.out.println(Form_Document_No2);

				System.out.println("Advanced Receipt Created Successfully :- " + Form_Document_No2);

				System.out.println("********************************************************************");

			}

			catch (Exception a) {
				System.out.println("Element is not found");
			}

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			int CESS_Amount = 0;

			double x = CESS_Amount;

			double c = x;
			DecimalFormat f = new DecimalFormat("##.00");
			String Form_Total_Tax_Amount1 = f.format(c);

			String Form_Total_Tax_Amount = "??? " + Form_Total_Tax_Amount1;

			System.out.println("Total Tax Amount:-" + Form_Total_Tax_Amount);

			String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Receipt_Amount"))).getText();

			System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

			Thread.sleep(500);

			String s5 = Form_SubTotal_Amount;

			String SubTotal = s5.substring(2, 6);

			double SubTotal_Amount = Double.parseDouble(SubTotal);

			Thread.sleep(500);

			double y = SubTotal_Amount + x;

			double d = y;
			DecimalFormat f1 = new DecimalFormat("##.00");
			String Form_Total_Amount1 = f1.format(d);

			String Form_Total_Amount = "??? " + Form_Total_Amount1;

			System.out.println("Total Amount:- " + Form_Total_Amount);

			Thread.sleep(500);

			System.out.println("********************************************************************");

			WebElement Receive_Payment_close = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_close"))));
			Receive_Payment_close.click();

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

			if ((Form_SubTotal_Amount.equals(GSTR3B_Taxable_Amount)
					&& (Form_Total_Amount.equals(GSTR3B_Total_Amount)))) {
				System.out.println(" All values of SubTotal , Total Amount matching with GSTR3B report values ");
			} else {
				System.out.println("Values are Not Matching");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();
			Thread.sleep(2000);

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createAdvancedReceived_InterState_3_2_A(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Receive_Payment_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_Module.click();

			WebElement Receive_Payment_from_Customer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_from_Customer")))); //// area[@onclick='callVendorQuotation()']
																											//// ,
																											//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_from_Customer.click();

			// WebElement Select_Customer_in_Receipt =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_in_Receipt"))));
			// ////*[@id='customer60vendorquotationundefined']
			// Select_Customer_in_Receipt.sendKeys(Customer_Code);
			// Thread.sleep(1000);
			// Select_Customer_in_Receipt.sendKeys(Keys.ENTER);
			// Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='customer9receiptwindow']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Document_type_in_RP = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP"))));
			Select_Document_type_in_RP.click();

			WebElement Select_Document_type_in_RP1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP1"))));

			Select_Document_type_in_RP1.sendKeys("Advanced / Deposit");

			Thread.sleep(1000);

			WebElement Click_Advanced = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advanced"))));

			Click_Advanced.click();

			Thread.sleep(1000);

			WebElement Document_Number_in_RP = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number_in_RP"))));
			Document_Number_in_RP.click();
			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_RP"))));
			ADD_in_RP.click();
			Thread.sleep(1000);

			WebElement Amount_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_RP"))));
			Amount_in_RP.click();

			WebElement Amount1_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_RP"))));
			Amount1_in_RP.clear();

			WebElement Amount12_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount12_in_RP"))));
			Amount12_in_RP.click();

			Amount1_in_RP.sendKeys("100");

			WebElement AdvancedReceipt_Memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("AdvancedReceipt_Memo"))));
			AdvancedReceipt_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1500);

			String Form_Document_No2 = "";

			try

			{
				WebElement Doc_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@class='x-window x-window-plain x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));

				String Advanced_Receipt_No = driver.findElement(By.xpath(GST_pro.getProperty("Advanced_Receipt_No")))
						.getText();

				System.out.println("Document No :-" + Advanced_Receipt_No);

				String s1 = Advanced_Receipt_No;

				Form_Document_No2 = s1.substring(0, 7); // extracting few
														// characters
				System.out.println(Form_Document_No2);

				System.out.println("Advanced Receipt Created Successfully :- " + Form_Document_No2);

				System.out.println("********************************************************************");

			}

			catch (Exception a) {
				System.out.println("Element is not found");
			}

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

				String s2 = Form_IGST;

				String IGST = s2.substring(2, 6);

				double IGST_Amount = Double.parseDouble(IGST);

				// int SGST_Amount = Integer.parseInt(SGST);

				String s4 = Form_IGST_CESS;

				String CESS = s4.substring(2, 6);

				double CESS_Amount = Double.parseDouble(CESS);

				// int CESS_Amount = Integer.parseInt(CESS);

				double x = IGST_Amount + CESS_Amount;

				double c = x;
				DecimalFormat f = new DecimalFormat("##.00");
				String Form_Total_Tax_Amount1 = f.format(c);

				// DecimalFormat df2 = new DecimalFormat(".##");

				// df2.format(x);

				// System.out.println("Total Tax Amount:- " + x );

				// String Form_Total_Tax_Amount1 = Double.toString(x);

				String Form_Total_Tax_Amount = "??? " + Form_Total_Tax_Amount1;

				System.out.println("Total Tax Amount:-" + Form_Total_Tax_Amount);

				// String Form_Total_Tax_Amount = Form_SGST + Form_CGST +
				// Form_CESS ;

				// System.out.println("Total Tax Amount:- " +
				// Form_Total_Tax_Amount1);

				// Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Receipt_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String s5 = Form_SubTotal_Amount;

				String SubTotal = s5.substring(2, 6);

				double SubTotal_Amount = Double.parseDouble(SubTotal);

				Thread.sleep(500);

				double y = SubTotal_Amount + x;

				double d = y;
				DecimalFormat f1 = new DecimalFormat("##.00");
				String Form_Total_Amount1 = f1.format(d);

				// System.out.println(Form_Total_Amount1);

				// System.out.println("Total Tax Amount:- " + x );

				// String Form_Total_Amount1 = Double.toString(y);

				String Form_Total_Amount = "??? " + Form_Total_Amount1;

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Receive_Payment_close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_close"))));
				Receive_Payment_close.click();

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

				WebElement View_3_2_A = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_2_A"))));
				View_3_2_A.click();

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

				driver.navigate().refresh();
				Thread.sleep(2000);

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createAdvancedReceived_InterState_3_2_B(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Receive_Payment_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_Module.click();

			WebElement Receive_Payment_from_Customer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_from_Customer")))); //// area[@onclick='callVendorQuotation()']
																											//// ,
																											//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_from_Customer.click();

			// WebElement Select_Customer_in_Receipt =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_in_Receipt"))));
			// ////*[@id='customer60vendorquotationundefined']
			// Select_Customer_in_Receipt.sendKeys(Customer_Code);
			// Thread.sleep(1000);
			// Select_Customer_in_Receipt.sendKeys(Keys.ENTER);
			// Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='customer9receiptwindow']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Document_type_in_RP = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP"))));
			Select_Document_type_in_RP.click();

			WebElement Select_Document_type_in_RP1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP1"))));

			Select_Document_type_in_RP1.sendKeys("Advanced / Deposit");

			Thread.sleep(1000);

			WebElement Click_Advanced = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advanced"))));

			Click_Advanced.click();

			Thread.sleep(1000);

			WebElement Document_Number_in_RP = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number_in_RP"))));

			Document_Number_in_RP.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_RP"))));
			ADD_in_RP.click();
			Thread.sleep(1000);

			WebElement Amount_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_RP"))));
			Amount_in_RP.click();

			WebElement Amount1_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_RP"))));
			Amount1_in_RP.clear();

			WebElement Amount12_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount12_in_RP"))));
			Amount12_in_RP.click();

			Amount1_in_RP.sendKeys("100");

			WebElement AdvancedReceipt_Memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("AdvancedReceipt_Memo"))));
			AdvancedReceipt_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1500);

			String Form_Document_No2 = "";

			try

			{
				WebElement Doc_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@class='x-window x-window-plain x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));

				String Advanced_Receipt_No = driver.findElement(By.xpath(GST_pro.getProperty("Advanced_Receipt_No")))
						.getText();

				System.out.println("Document No :-" + Advanced_Receipt_No);

				String s1 = Advanced_Receipt_No;

				Form_Document_No2 = s1.substring(0, 7); // extracting few
														// characters
				System.out.println(Form_Document_No2);

				System.out.println("Advanced Receipt Created Successfully :- " + Form_Document_No2);

				System.out.println("********************************************************************");

			}

			catch (Exception a) {
				System.out.println("Element is not found");
			}

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

				String s2 = Form_IGST;

				String IGST = s2.substring(2, 6);

				double IGST_Amount = Double.parseDouble(IGST);

				// int SGST_Amount = Integer.parseInt(SGST);

				String s4 = Form_IGST_CESS;

				String CESS = s4.substring(2, 6);

				double CESS_Amount = Double.parseDouble(CESS);

				// int CESS_Amount = Integer.parseInt(CESS);

				double x = IGST_Amount + CESS_Amount;

				double c = x;
				DecimalFormat f = new DecimalFormat("##.00");
				String Form_Total_Tax_Amount1 = f.format(c);

				String Form_Total_Tax_Amount = "??? " + Form_Total_Tax_Amount1;

				System.out.println("Total Tax Amount:-" + Form_Total_Tax_Amount);

				// String Form_Total_Tax_Amount = Form_SGST + Form_CGST +
				// Form_CESS ;

				// System.out.println("Total Tax Amount:- " +
				// Form_Total_Tax_Amount1);

				// Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Receipt_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String s5 = Form_SubTotal_Amount;

				String SubTotal = s5.substring(2, 6);

				double SubTotal_Amount = Double.parseDouble(SubTotal);

				Thread.sleep(500);

				double y = SubTotal_Amount + x;

				double d = y;
				DecimalFormat f1 = new DecimalFormat("##.00");
				String Form_Total_Amount1 = f1.format(d);

				// System.out.println(Form_Total_Amount1);

				// System.out.println("Total Tax Amount:- " + x );

				// String Form_Total_Amount1 = Double.toString(y);

				String Form_Total_Amount = "??? " + Form_Total_Amount1;

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Receive_Payment_close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_close"))));
				Receive_Payment_close.click();

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

				WebElement View_3_2_B = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_2_B"))));
				View_3_2_B.click();

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

				driver.navigate().refresh();
				Thread.sleep(2000);

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createAdvancedReceived_IntraState_GSTR1(WebDriver driver, String Customer_Code,
			String Product_ID1) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			String Product_ID = "PID00017";
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Receive_Payment_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_Module"))));
			Thread.sleep(1000);
			Receive_Payment_Module.click();

			WebElement Receive_Payment_from_Customer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_from_Customer"))));
			Thread.sleep(1000);
			Receive_Payment_from_Customer.click();

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='customer9receiptwindow']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Document_type_in_RP = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP"))));
			Select_Document_type_in_RP.click();

			WebElement Select_Document_type_in_RP1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP1"))));

			Select_Document_type_in_RP1.sendKeys("Advanced / Deposit");

			Thread.sleep(1000);

			WebElement Click_Advanced = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advanced"))));

			Click_Advanced.click();

			Thread.sleep(1000);

			WebElement Document_Number_in_RP = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number_in_RP"))));

			Document_Number_in_RP.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_RP"))));
			ADD_in_RP.click();
			Thread.sleep(1000);

			WebElement Amount_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_RP"))));
			Amount_in_RP.click();

			WebElement Amount1_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_RP"))));
			Amount1_in_RP.clear();

			WebElement Amount12_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount12_in_RP"))));
			Amount12_in_RP.click();

			Amount1_in_RP.sendKeys("267.39");

			WebElement AdvancedReceipt_Memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("AdvancedReceipt_Memo"))));
			AdvancedReceipt_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1500);

			String Form_Document_No2 = "";

			try

			{
				WebElement Doc_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@class='x-window x-window-plain x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));
				String Advanced_Receipt_No = driver.findElement(By.xpath(GST_pro.getProperty("Advanced_Receipt_No")))
						.getText();

				System.out.println("Document No :-" + Advanced_Receipt_No);

				String s1 = Advanced_Receipt_No;

				Form_Document_No2 = s1.substring(0, 7); // extracting few
														// characters
				System.out.println(Form_Document_No2);

				System.out.println("Advanced Receipt Created Successfully :- " + Form_Document_No2);

				System.out.println("********************************************************************");

			}

			catch (Exception a) {
				System.out.println("Element is not found");
			}

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

				String s2 = Form_SGST;

				String SGST = s2.substring(2, 6);

				double SGST_Amount = Double.parseDouble(SGST);

				// int SGST_Amount = Integer.parseInt(SGST);

				String s3 = Form_SGST;

				String CGST = s3.substring(2, 6);

				double CGST_Amount = Double.parseDouble(CGST);

				// int CGST_Amount = Integer.parseInt(CGST);

				String s4 = Form_CESS;

				String CESS = s4.substring(2, 6);

				double CESS_Amount = Double.parseDouble(CESS);

				// int CESS_Amount = Integer.parseInt(CESS);

				double x = SGST_Amount + CGST_Amount + CESS_Amount;

				double c = x;
				DecimalFormat f = new DecimalFormat("##.00");
				String Form_Total_Tax_Amount1 = f.format(c);

				// DecimalFormat df2 = new DecimalFormat(".##");

				// df2.format(x);

				// System.out.println("Total Tax Amount:- " + x );

				// String Form_Total_Tax_Amount1 = Double.toString(x);

				String Form_Total_Tax_Amount = "??? " + Form_Total_Tax_Amount1;

				System.out.println("Total Tax Amount:-" + Form_Total_Tax_Amount);

				// String Form_Total_Tax_Amount = Form_SGST + Form_CGST +
				// Form_CESS ;

				// System.out.println("Total Tax Amount:- " +
				// Form_Total_Tax_Amount1);

				// Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Receipt_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String s5 = Form_SubTotal_Amount;

				String SubTotal = s5.substring(2, 6);

				double SubTotal_Amount = Double.parseDouble(SubTotal);

				Thread.sleep(500);

				double y = SubTotal_Amount + x;

				double d = y;
				DecimalFormat f1 = new DecimalFormat("##.00");
				String Form_Total_Amount1 = f1.format(d);

				// System.out.println(Form_Total_Amount1);

				// System.out.println("Total Tax Amount:- " + x );

				// String Form_Total_Amount1 = Double.toString(y);

				String Form_Total_Amount = "??? " + Form_Total_Amount1;

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Receive_Payment_close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_close"))));
				Receive_Payment_close.click();

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

				WebElement Advanced_Received_GSTR1_View = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Advanced_Received_GSTR1_View"))));
				Advanced_Received_GSTR1_View.click();
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
							" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section Tax Liability(Advances received) - 11A(1), 11A(2)");
				}

				else {
					System.out.println(
							"Values are Not Matching with GSTR1 report values in section Tax Liability(Advances received) - 11A(1), 11A(2)");
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

	public static void createAdvancedReceived_IntraState_GSTR1_Advanced_Received(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			// String Customer_Code = "CC00056";
			// String Product_ID = "PID00023";
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Receive_Payment_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_Module.click();

			WebElement Receive_Payment_from_Customer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_from_Customer")))); //// area[@onclick='callVendorQuotation()']
																											//// ,
																											//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_from_Customer.click();

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='customer9receiptwindow']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Document_type_in_RP = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP"))));
			Select_Document_type_in_RP.click();

			WebElement Select_Document_type_in_RP1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP1"))));

			Select_Document_type_in_RP1.sendKeys("Advanced / Deposit");

			Thread.sleep(1000);

			WebElement Click_Advanced = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advanced"))));

			Click_Advanced.click();

			Thread.sleep(1000);

			WebElement Document_Number_in_RP = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number_in_RP"))));

			Document_Number_in_RP.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_RP"))));
			ADD_in_RP.click();
			Thread.sleep(1000);

			WebElement Amount_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_RP"))));
			Amount_in_RP.click();

			WebElement Amount1_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_RP"))));
			Amount1_in_RP.clear();

			WebElement Amount12_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount12_in_RP"))));
			Amount12_in_RP.click();

			Amount1_in_RP.sendKeys("100");

			WebElement AdvancedReceipt_Memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("AdvancedReceipt_Memo"))));
			AdvancedReceipt_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1500);

			String Form_Document_No2 = "";

			try

			{
				WebElement Doc_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@class='x-window x-window-plain x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));
				String Advanced_Receipt_No = driver.findElement(By.xpath(GST_pro.getProperty("Advanced_Receipt_No")))
						.getText();

				System.out.println("Document No :-" + Advanced_Receipt_No);

				String s1 = Advanced_Receipt_No;

				Form_Document_No2 = s1.substring(0, 7); // extracting few
														// characters
				System.out.println(Form_Document_No2);

				System.out.println("Advanced Receipt Created Successfully :- " + Form_Document_No2);

				System.out.println("********************************************************************");

			}

			catch (Exception a) {
				System.out.println("Element is not found");
			}

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

				String s2 = Form_SGST;

				String SGST = s2.substring(2, 6);

				double SGST_Amount = Double.parseDouble(SGST);

				// int SGST_Amount = Integer.parseInt(SGST);

				String s3 = Form_SGST;

				String CGST = s3.substring(2, 6);

				double CGST_Amount = Double.parseDouble(CGST);

				// int CGST_Amount = Integer.parseInt(CGST);

				String s4 = Form_CESS;

				String CESS = s4.substring(2, 6);

				double CESS_Amount = Double.parseDouble(CESS);

				// int CESS_Amount = Integer.parseInt(CESS);

				double x = SGST_Amount + CGST_Amount + CESS_Amount;

				double c = x;
				DecimalFormat f = new DecimalFormat("##.00");
				String Form_Total_Tax_Amount1 = f.format(c);

				// DecimalFormat df2 = new DecimalFormat(".##");

				// df2.format(x);

				// System.out.println("Total Tax Amount:- " + x );

				// String Form_Total_Tax_Amount1 = Double.toString(x);

				String Form_Total_Tax_Amount = "??? " + Form_Total_Tax_Amount1;

				System.out.println("Total Tax Amount:-" + Form_Total_Tax_Amount);

				// String Form_Total_Tax_Amount = Form_SGST + Form_CGST +
				// Form_CESS ;

				// System.out.println("Total Tax Amount:- " +
				// Form_Total_Tax_Amount1);

				// Thread.sleep(500);

				// Subtotal --> (//*[@class='currency']) [17]

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Receipt_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String s5 = Form_SubTotal_Amount;

				String SubTotal = s5.substring(2, 6);

				double SubTotal_Amount = Double.parseDouble(SubTotal);

				Thread.sleep(500);

				double y = SubTotal_Amount + x;

				double d = y;
				DecimalFormat f1 = new DecimalFormat("##.00");
				String Form_Total_Amount1 = f1.format(d);

				// System.out.println(Form_Total_Amount1);

				// System.out.println("Total Tax Amount:- " + x );

				// String Form_Total_Amount1 = Double.toString(y);

				String Form_Total_Amount = "??? " + Form_Total_Amount1;

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Receive_Payment_close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_close"))));
				Receive_Payment_close.click();

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

				WebElement Advanced_Received_GSTR1_View = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Advanced_Received_GSTR1_View"))));
				Advanced_Received_GSTR1_View.click();

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

				for (int i = 7; i <= 13; i++)

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
							" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section Tax Liability(Advances received) - 11A(1), 11A(2) ");
				}

				else {
					System.out.println(
							"Values are Not Matching with GSTR1 report values in section Tax Liability(Advances received) - 11A(1), 11A(2) ");
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

	public static void createAdvancedReceived_InterState_GSTR1_Advanced_Received(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			// String Customer_Code = "CC00048";
			// String Product_ID = "PID00023";
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Receive_Payment_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_Module.click();

			WebElement Receive_Payment_from_Customer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_from_Customer")))); //// area[@onclick='callVendorQuotation()']
																											//// ,
																											//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_from_Customer.click();

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='customer9receiptwindow']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Document_type_in_RP = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP"))));
			Select_Document_type_in_RP.click();

			WebElement Select_Document_type_in_RP1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP1"))));

			Select_Document_type_in_RP1.sendKeys("Advanced / Deposit");

			Thread.sleep(1000);

			WebElement Click_Advanced = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advanced"))));

			Click_Advanced.click();

			Thread.sleep(1000);

			WebElement Document_Number_in_RP = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number_in_RP"))));

			Document_Number_in_RP.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_RP"))));
			ADD_in_RP.click();
			Thread.sleep(1000);

			WebElement Amount_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_RP"))));
			Amount_in_RP.click();

			WebElement Amount1_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_RP"))));
			Amount1_in_RP.clear();

			WebElement Amount12_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount12_in_RP"))));
			Amount12_in_RP.click();

			Amount1_in_RP.sendKeys("100");

			WebElement AdvancedReceipt_Memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("AdvancedReceipt_Memo"))));
			AdvancedReceipt_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1500);

			String Form_Document_No2 = "";

			try

			{
				WebElement Doc_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@class='x-window x-window-plain x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));
				String Advanced_Receipt_No = driver.findElement(By.xpath(GST_pro.getProperty("Advanced_Receipt_No")))
						.getText();

				System.out.println("Document No :-" + Advanced_Receipt_No);

				String s1 = Advanced_Receipt_No;

				Form_Document_No2 = s1.substring(0, 7); // extracting few
														// characters
				System.out.println(Form_Document_No2);

				System.out.println("Advanced Receipt Created Successfully :- " + Form_Document_No2);

				System.out.println("********************************************************************");

			}

			catch (Exception a) {
				System.out.println("Element is not found");
			}

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

				String s2 = Form_IGST;

				String IGST = s2.substring(2, 6);

				double IGST_Amount = Double.parseDouble(IGST);

				// int SGST_Amount = Integer.parseInt(SGST);

				String s4 = Form_IGST_CESS;

				String CESS = s4.substring(2, 6);

				double CESS_Amount = Double.parseDouble(CESS);

				// int CESS_Amount = Integer.parseInt(CESS);

				double x = IGST_Amount + CESS_Amount;

				double c = x;
				DecimalFormat f = new DecimalFormat("##.00");
				String Form_Total_Tax_Amount1 = f.format(c);

				String Form_Total_Tax_Amount = "??? " + Form_Total_Tax_Amount1;

				System.out.println("Total Tax Amount:-" + Form_Total_Tax_Amount);

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Receipt_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String s5 = Form_SubTotal_Amount;

				String SubTotal = s5.substring(2, 6);

				double SubTotal_Amount = Double.parseDouble(SubTotal);
				Thread.sleep(500);

				double y = SubTotal_Amount + x;

				double d = y;
				DecimalFormat f1 = new DecimalFormat("##.00");
				String Form_Total_Amount1 = f1.format(d);

				// System.out.println(Form_Total_Amount1);

				// System.out.println("Total Tax Amount:- " + x );

				// String Form_Total_Amount1 = Double.toString(y);

				String Form_Total_Amount = "??? " + Form_Total_Amount1;

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Receive_Payment_close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_close"))));
				Receive_Payment_close.click();

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

				WebElement Advanced_Received_GSTR1_View = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Advanced_Received_GSTR1_View"))));
				Advanced_Received_GSTR1_View.click();

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

				for (int i = 7; i <= 13; i++)

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

				if (Form_IGST.equals(GSTR1_IGST_Amount) && (Form_IGST_CESS.equals(GSTR1_CESS_Amount)
						&& (Form_Total_Tax_Amount.equals(GSTR1_Total_Tax_Amount)
								&& (Form_SubTotal_Amount.equals(GSTR1_Taxable_Amount)
										&& (Form_Total_Amount.equals(GSTR1_Total_Amount))))))

				{

					System.out.println(
							" All values of IGST, CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section Tax Liability(Advances received) - 11A(1), 11A(2) ");

				}

				else {

					System.out.println(
							"Values are Not Matching with GSTR1 report values in section Tax Liability(Advances received) - 11A(1), 11A(2) ");

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

	public static void createAdvancedReceived_InterState_GSTR1_Advanced_Received_Export_WPAY(WebDriver driver,
			String Customer_Code, String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			// String Customer_Code = "CC00041";
			// String Product_ID = "PID00023";
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Receive_Payment_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_Module.click();

			WebElement Receive_Payment_from_Customer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_from_Customer")))); //// area[@onclick='callVendorQuotation()']
																											//// ,
																											//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_from_Customer.click();

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='customer9receiptwindow']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Document_type_in_RP = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP"))));
			Select_Document_type_in_RP.click();

			WebElement Select_Document_type_in_RP1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP1"))));

			Select_Document_type_in_RP1.sendKeys("Advanced / Deposit");

			Thread.sleep(1000);

			WebElement Click_Advanced = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advanced"))));

			Click_Advanced.click();

			Thread.sleep(1000);

			WebElement Document_Number_in_RP = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number_in_RP"))));

			Document_Number_in_RP.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_RP"))));
			ADD_in_RP.click();
			Thread.sleep(1000);

			WebElement Amount_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_RP"))));
			Amount_in_RP.click();

			WebElement Amount1_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_RP"))));
			Amount1_in_RP.clear();

			WebElement Amount12_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount12_in_RP"))));
			Amount12_in_RP.click();

			Amount1_in_RP.sendKeys("500.00");

			WebElement AdvancedReceipt_Memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("AdvancedReceipt_Memo"))));
			AdvancedReceipt_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1500);

			String Form_Document_No2 = "";

			try

			{
				WebElement Doc_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@class='x-window x-window-plain x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));
				String Advanced_Receipt_No = driver.findElement(By.xpath(GST_pro.getProperty("Advanced_Receipt_No")))
						.getText();

				System.out.println("Document No :-" + Advanced_Receipt_No);

				String s1 = Advanced_Receipt_No;

				Form_Document_No2 = s1.substring(0, 7); // extracting few
														// characters
				System.out.println(Form_Document_No2);

				System.out.println("Advanced Receipt Created Successfully :- " + Form_Document_No2);

				System.out.println("********************************************************************");

			}

			catch (Exception a) {
				System.out.println("Element is not found");
			}

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);
			{

				String Form_IGST = null;
				String Form_IGST_CESS = "0";

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

				String s2 = Form_IGST;

				String IGST = s2.substring(2, 6);

				double IGST_Amount = Double.parseDouble(IGST);

				// int SGST_Amount = Integer.parseInt(SGST);

				double x = IGST_Amount;

				double c = x;
				DecimalFormat f = new DecimalFormat("##.00");
				String Form_Total_Tax_Amount1 = f.format(c);

				String Form_Total_Tax_Amount = "??? " + Form_Total_Tax_Amount1;

				System.out.println("Total Tax Amount:-" + Form_Total_Tax_Amount);

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Receipt_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String s5 = Form_SubTotal_Amount;

				String SubTotal = s5.substring(2, 6);

				double SubTotal_Amount = Double.parseDouble(SubTotal);
				Thread.sleep(500);

				double y = SubTotal_Amount + x;

				double d = y;
				DecimalFormat f1 = new DecimalFormat("##.00");
				String Form_Total_Amount1 = f1.format(d);

				String Form_Total_Amount = "??? " + Form_Total_Amount1;

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Receive_Payment_close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_close"))));
				Receive_Payment_close.click();

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

				WebElement Advanced_Received_GSTR1_View = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Advanced_Received_GSTR1_View"))));
				Advanced_Received_GSTR1_View.click();

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

				for (int i = 7; i <= 13; i++)

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

				if (Form_IGST.equals(GSTR1_IGST_Amount) && (Form_Total_Tax_Amount.equals(GSTR1_Total_Tax_Amount)
						&& (Form_SubTotal_Amount.equals(GSTR1_Taxable_Amount)
								&& (Form_Total_Amount.equals(GSTR1_Total_Amount)))))

				{

					System.out.println(
							" All values of IGST,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section Tax Liability(Advances received) - 11A(1), 11A(2) ");

				}

				else {

					System.out.println(
							"Values are Not Matching with GSTR1 report values in section Tax Liability(Advances received) - 11A(1), 11A(2) ");

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

	public static void createAdvancedReceived_InterState_GSTR1_Advanced_Received_WOPAY(WebDriver driver,
			String Customer_Code, String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			// String Customer_Code = "CC00054";
			// String Product_ID = "PID00023";
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Receive_Payment_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_Module.click();

			WebElement Receive_Payment_from_Customer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_from_Customer")))); //// area[@onclick='callVendorQuotation()']
																											//// ,
																											//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_from_Customer.click();

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='customer9receiptwindow']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Document_type_in_RP = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP"))));
			Select_Document_type_in_RP.click();

			WebElement Select_Document_type_in_RP1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP1"))));

			Select_Document_type_in_RP1.sendKeys("Advanced / Deposit");

			Thread.sleep(1000);

			WebElement Click_Advanced = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advanced"))));

			Click_Advanced.click();

			Thread.sleep(1000);

			WebElement Document_Number_in_RP = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number_in_RP"))));

			Document_Number_in_RP.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_RP"))));
			ADD_in_RP.click();
			Thread.sleep(1000);

			WebElement Amount_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_RP"))));
			Amount_in_RP.click();

			WebElement Amount1_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_RP"))));
			Amount1_in_RP.clear();

			WebElement Amount12_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount12_in_RP"))));
			Amount12_in_RP.click();

			Amount1_in_RP.sendKeys("500.00");

			WebElement AdvancedReceipt_Memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("AdvancedReceipt_Memo"))));
			AdvancedReceipt_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1500);

			String Form_Document_No2 = "";

			try

			{
				WebElement Doc_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@class='x-window x-window-plain x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));
				String Advanced_Receipt_No = driver.findElement(By.xpath(GST_pro.getProperty("Advanced_Receipt_No")))
						.getText();

				System.out.println("Document No :-" + Advanced_Receipt_No);

				String s1 = Advanced_Receipt_No;

				Form_Document_No2 = s1.substring(0, 7); // extracting few
														// characters
				System.out.println(Form_Document_No2);

				System.out.println("Advanced Receipt Created Successfully :- " + Form_Document_No2);

				System.out.println("********************************************************************");

			}

			catch (Exception a) {
				System.out.println("Element is not found");
			}

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			{

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Receipt_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String s5 = Form_SubTotal_Amount;

				String SubTotal = s5.substring(2, 6);

				double SubTotal_Amount = Double.parseDouble(SubTotal);
				Thread.sleep(500);

				double y = SubTotal_Amount;

				double d = y;
				DecimalFormat f1 = new DecimalFormat("##.00");
				String Form_Total_Amount1 = f1.format(d);

				String Form_Total_Amount = "??? " + Form_Total_Amount1;

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Receive_Payment_close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_close"))));
				Receive_Payment_close.click();

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

				WebElement Advanced_Received_GSTR1_View = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Advanced_Received_GSTR1_View"))));
				Advanced_Received_GSTR1_View.click();

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

				for (int i = 7; i <= 13; i++)

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

				if (Form_SubTotal_Amount.equals(GSTR1_Taxable_Amount) && (Form_Total_Amount.equals(GSTR1_Total_Amount)))

				{

					System.out.println(
							" All values of SubTotal , Total Amount matching with GSTR1 report values in section Tax Liability(Advances received) - 11A(1), 11A(2) ");

				}

				else {

					System.out.println(
							"Values are Not Matching with GSTR1 report values in section Tax Liability(Advances received) - 11A(1), 11A(2) ");

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

	public static void createAdvancedReceived_NIL_Rated_Exempted_Non_GST(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			// String Customer_Code = "CC00002";
			// Product_ID = "PID00004";
			String Memo_value = "Hi Team";
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Receive_Payment_Module = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_Module")))); //// area[@onclick='callVendorQuotation()']
																														//// ,
																														//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_Module.click();

			WebElement Receive_Payment_from_Customer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_from_Customer")))); //// area[@onclick='callVendorQuotation()']
																											//// ,
																											//// //map[@name='AlienAreas1']/area[5]
			Thread.sleep(1000);
			Receive_Payment_from_Customer.click();

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='customer9receiptwindow']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Document_type_in_RP = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP"))));
			Select_Document_type_in_RP.click();

			WebElement Select_Document_type_in_RP1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Document_type_in_RP1"))));

			Select_Document_type_in_RP1.sendKeys("Advanced / Deposit");

			Thread.sleep(1000);

			WebElement Click_Advanced = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advanced"))));

			Click_Advanced.click();

			Thread.sleep(1000);

			WebElement Document_Number_in_RP = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number_in_RP"))));

			Document_Number_in_RP.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_RP"))));
			ADD_in_RP.click();
			Thread.sleep(1000);

			WebElement Amount_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_RP"))));
			Amount_in_RP.click();

			WebElement Amount1_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_RP"))));
			Amount1_in_RP.clear();

			WebElement Amount12_in_RP = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount12_in_RP"))));
			Amount12_in_RP.click();

			Amount1_in_RP.sendKeys("500.00");

			WebElement AdvancedReceipt_Memo = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("AdvancedReceipt_Memo"))));
			AdvancedReceipt_Memo.sendKeys(Memo_value);

			Thread.sleep(1000);

			///// Save first & then fetch values

			WebElement Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save"))));
			Save.click();
			Thread.sleep(1000);

			WebElement Yes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes"))));
			Yes.click();
			Thread.sleep(1500);

			String Form_Document_No2 = "";

			try

			{
				WebElement Doc_no = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//*[@class='x-window x-window-plain x-window-dlg']/div[2]/div/div/div/div/div[1]/div[2]/span/b[1]")));
				String Advanced_Receipt_No = driver.findElement(By.xpath(GST_pro.getProperty("Advanced_Receipt_No")))
						.getText();

				System.out.println("Document No :-" + Advanced_Receipt_No);

				String s1 = Advanced_Receipt_No;

				Form_Document_No2 = s1.substring(0, 7); // extracting few
														// characters
				System.out.println(Form_Document_No2);

				System.out.println("Advanced Receipt Created Successfully :- " + Form_Document_No2);

				System.out.println("********************************************************************");

			}

			catch (Exception a) {
				System.out.println("Element is not found");
			}

			WebElement OK = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("OK"))));
			OK.click();
			Thread.sleep(1000);

			{

				String Form_SubTotal_Amount = driver.findElement(By.xpath(GST_pro.getProperty("Receipt_Amount")))
						.getText();

				System.out.println("Sub Total Amount:- " + Form_SubTotal_Amount);

				Thread.sleep(500);

				String s5 = Form_SubTotal_Amount;

				String SubTotal = s5.substring(2, 6);

				double SubTotal_Amount = Double.parseDouble(SubTotal);
				Thread.sleep(500);

				double y = SubTotal_Amount;

				double d = y;
				DecimalFormat f1 = new DecimalFormat("##.00");
				String Form_Total_Amount1 = f1.format(d);

				String Form_Total_Amount = "??? " + Form_Total_Amount1;

				System.out.println("Total Amount:- " + Form_Total_Amount);

				Thread.sleep(500);

				System.out.println("********************************************************************");

				WebElement Receive_Payment_close = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Receive_Payment_close"))));
				Receive_Payment_close.click();

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

				WebElement Advanced_Received_GSTR1_View = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Advanced_Received_GSTR1_View"))));
				Advanced_Received_GSTR1_View.click();

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

				for (int i = 7; i <= 13; i++)

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

				if (Form_SubTotal_Amount.equals(GSTR1_Taxable_Amount) && (Form_Total_Amount.equals(GSTR1_Total_Amount)))

				{

					System.out.println(
							" All values of SubTotal , Total Amount matching with GSTR1 report values in section Tax Liability(Advances received) - 11A(1), 11A(2)");

				}

				else {

					System.out.println(
							"Values are Not Matching with GSTR1 report values in section Tax Liability(Advances received) - 11A(1), 11A(2)");

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

}