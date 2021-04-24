package krawler.erp.IndianGST;

import java.awt.AWTException;
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

public class GST_Credit_Note {

	public static void createCreditNote_IntraState(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			// //button[text()='Submit']

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']"; // selectCustomerundefinedcreditnotepanel

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			// Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");

			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]

			Click_Advertising.click();

			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]

			Document_Number.click();

			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]

			Search_Product_in_CN.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "100.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println(Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();

			System.out.println(Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println(Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();

			Thread.sleep(1000);

			String Form_CGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CGST_in_CN"))))
					.getText();

			System.out.println("CGST Amount in Credit Note form :- " + Form_CGST_in_CN);

			String Form_SGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_SGST_in_CN"))))
					.getText();

			System.out.println("SGST Amount in Credit Note form :- " + Form_SGST_in_CN);

			String Form_CESS_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CESS_in_CN"))))
					.getText();

			System.out.println("CESS Amount in Credit Note form :- " + Form_CESS_in_CN);

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement View_3_1_A = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_1_A"))));
			View_3_1_A.click();

			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Credit_Note_No1);

			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();

			Thread.sleep(1000);

			// 3B_SGST Amount

			String GSTR3B_SGST_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_SGST_Amount_of_CN"))).getText();

			System.out.println("GSTR3B SGST Amount:- " + GSTR3B_SGST_Amount_of_CN);

			Thread.sleep(500);

			// 3B_CGST Amount

			String GSTR3B_CGST_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CGST_Amount_of_CN"))).getText();

			System.out.println("GSTR3B CGST Amount:- " + GSTR3B_CGST_Amount_of_CN);

			Thread.sleep(500);

			// 3B_CESS Amount

			String GSTR3B_CESS_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CESS_Amount_of_CN"))).getText();

			System.out.println("GSTR3B CESS Amount:- " + GSTR3B_CESS_Amount_of_CN);

			Thread.sleep(500);

			// 3B_Total Tax Amount

			String GSTR3B_Total_Tax_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount_of_CN);

			Thread.sleep(500);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN);

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN);

			Thread.sleep(1000);

			if (Form_SGST_in_CN.equals(GSTR3B_SGST_Amount_of_CN) && (Form_CGST_in_CN.equals(GSTR3B_CGST_Amount_of_CN)
					&& (Form_CESS_in_CN.equals(GSTR3B_CESS_Amount_of_CN)
							&& (Form_Total_Tax_Amount_in_CN.equals(GSTR3B_Total_Tax_Amount_of_CN)
									&& (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN)
											&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN)))))))

			{
				System.out.println(
						" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values ");
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

	public static void createCreditNote_InterState(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();
			// (//*[@class='x-form-field-wrap '] [1])/img

			// (//input[@name='rectype']) [2]

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			// //button[text()='Submit']

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));
			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]
			Search_Product_in_CN.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "100.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println(Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();
			System.out.println(Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();

			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			// String Form_Total_Amount = "("+"-"+ Form_Total_Amount1+ ")" ;

			// Integer result1 = Integer.valueOf(Form_Total_Amount1);
			// float Form_Total_Amount = (-result1);

			System.out.println(Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();
			Thread.sleep(1000);

			// To Get tax //
			// //div[@id='TermSelGrid']/div/div/div/div/div/div/div/div/div/div/div[2]/div/div[*]/table/tbody/tr/td[12]

			String Form_IGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_IGST_in_CN"))))
					.getText();
			System.out.println("IGST Amount in Credit Note form :- " + Form_IGST_in_CN);

			// System.out.println(Form_IGST_in_CN);

			String Form_CESS_in_CN1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CESS_in_CN1"))))
					.getText();
			System.out.println("CESS Amount in Credit Note form :- " + Form_CESS_in_CN1);

			// //div[@class='x-tool x-tool-close ']

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			// (//div[@class='x-layer x-editor x-small-editor x-grid-editor'])
			// [3]/div/input[2]

			// (//div[@class='x-layer x-combo-list '])
			// [3]/div/div[contains(text(),'Rounding Difference.')]

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);
			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement View_3_1_A = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_1_A"))));
			View_3_1_A.click();

			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Credit_Note_No1);

			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();

			Thread.sleep(1000);

			// 3B_IGST Amount

			String GSTR3B_IGST_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_IGST_Amount_of_CN"))).getText();

			System.out.println("GSTR3B IGST Amount:- " + GSTR3B_IGST_Amount_of_CN);

			Thread.sleep(500);

			String GSTR3B_CESS_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CESS_Amount_of_CN"))).getText();

			System.out.println("GSTR3B CESS Amount:- " + GSTR3B_CESS_Amount_of_CN);

			Thread.sleep(500);

			// 3B_Total Tax Amount

			String GSTR3B_Total_Tax_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount_of_CN);

			Thread.sleep(500);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN);

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN);

			Thread.sleep(1000);

			if (Form_IGST_in_CN.equals(GSTR3B_IGST_Amount_of_CN) && (Form_CESS_in_CN1.equals(GSTR3B_CESS_Amount_of_CN)
					&& (Form_Total_Tax_Amount_in_CN.equals(GSTR3B_Total_Tax_Amount_of_CN)
							&& (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN)
									&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN))))))

			{
				System.out.println(
						" All values of IGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values  ");
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

	public static void createCreditNote_SEZ_WOPAY_InterState(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();
			// (//*[@class='x-form-field-wrap '] [1])/img

			// (//input[@name='rectype']) [2]

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			// //button[text()='Submit']

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			// Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");

			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]

			Click_Advertising.click();

			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]

			Document_Number.click();

			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]

			Search_Product_in_CN.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "100.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println(Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();

			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			// String Form_Total_Amount = "("+"-"+ Form_Total_Amount1+ ")" ;

			// Integer result1 = Integer.valueOf(Form_Total_Amount1);
			// float Form_Total_Amount = (-result1);

			System.out.println(Form_Total_Amount_in_CN);

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			// (//div[@class='x-layer x-editor x-small-editor x-grid-editor'])
			// [3]/div/input[2]

			// (//div[@class='x-layer x-combo-list '])
			// [3]/div/div[contains(text(),'Rounding Difference.')]

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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
			Search_ID.sendKeys(Credit_Note_No1);

			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();

			Thread.sleep(1000);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN);

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN);

			Thread.sleep(1000);

			if ((Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN)
					&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN))))

			{
				System.out.println(" All values of  SubTotal , Total Amount matching with GSTR3B report values  ");
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

	public static void createCreditNote_SEZ_WPAY_InterState(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();
			// (//*[@class='x-form-field-wrap '] [1])/img

			// (//input[@name='rectype']) [2]

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			// //button[text()='Submit']

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			// Thread.sleep(1000);

			//// *[@class='x-layer x-combo-list ']/div/div/table/tbody/tr/td[2]

			// WebElement Select_Customer =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer"))));
			// ////map[@name='AlienAreas1']//area[1]
			// WebElement Memo_in_CN =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			// ////map[@name='AlienAreas1']//area[1]
			// Memo_in_CN.click();
			// Thread.sleep(1000);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			// Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");

			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]

			Click_Advertising.click();

			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]

			Document_Number.click();

			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]

			Search_Product_in_CN.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "100.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println(Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();
			System.out.println(Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();

			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			System.out.println(Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();

			Thread.sleep(1000);

			// WebElement Tax1_in_CN =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax1_in_CN"))));
			// Tax1_in_CN.click();
			// Thread.sleep(1000);

			// To Get tax //
			// //div[@id='TermSelGrid']/div/div/div/div/div/div/div/div/div/div/div[2]/div/div[*]/table/tbody/tr/td[12]

			String Form_IGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_IGST_in_CN"))))
					.getText();
			// Integer result2 = Integer.valueOf(Form_CGST1);
			// float Form_CGST = (-result2);

			// String Form_CGST = "("+"-"+ Form_CGST1+ ")" ;

			System.out.println("IGST Amount in Credit Note form :- " + Form_IGST_in_CN);

			// System.out.println(Form_IGST_in_CN);

			String Form_CESS_in_CN1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CESS_in_CN1"))))
					.getText();
			System.out.println("CESS Amount in Credit Note form :- " + Form_CESS_in_CN1);

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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
			Search_ID.sendKeys(Credit_Note_No1);

			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();

			Thread.sleep(1000);

			// 3B_IGST Amount

			String GSTR3B_IGST_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_IGST_Amount_of_CN"))).getText();

			System.out.println("GSTR3B IGST Amount:- " + GSTR3B_IGST_Amount_of_CN);

			Thread.sleep(500);

			String GSTR3B_CESS_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CESS_Amount_of_CN"))).getText();

			System.out.println("GSTR3B CESS Amount:- " + GSTR3B_CESS_Amount_of_CN);

			Thread.sleep(500);

			// 3B_Total Tax Amount

			String GSTR3B_Total_Tax_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount_of_CN);

			Thread.sleep(500);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN);

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN);

			Thread.sleep(1000);

			if (Form_IGST_in_CN.equals(GSTR3B_IGST_Amount_of_CN) && (Form_CESS_in_CN1.equals(GSTR3B_CESS_Amount_of_CN)
					&& (Form_Total_Tax_Amount_in_CN.equals(GSTR3B_Total_Tax_Amount_of_CN)
							&& (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN)
									&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN))))))

			{
				System.out.println(
						" All values of IGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values  ");
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

	public static void createCreditNote_Export_WPAY_InterState(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			// //button[text()='Submit']

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			// Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");

			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]

			Click_Advertising.click();

			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]

			Document_Number.click();

			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]

			Search_Product_in_CN.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "100.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println(Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();
			System.out.println(Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();

			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			System.out.println(Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();

			Thread.sleep(1000);

			// To Get tax //
			// //div[@id='TermSelGrid']/div/div/div/div/div/div/div/div/div/div/div[2]/div/div[*]/table/tbody/tr/td[12]

			String Form_IGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_IGST_in_CN"))))
					.getText();
			// Integer result2 = Integer.valueOf(Form_CGST1);
			// float Form_CGST = (-result2);

			// String Form_CGST = "("+"-"+ Form_CGST1+ ")" ;

			System.out.println("IGST Amount in Credit Note form :- " + Form_IGST_in_CN);

			// System.out.println(Form_IGST_in_CN);

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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
			Search_ID.sendKeys(Credit_Note_No1);

			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();

			Thread.sleep(1000);

			// 3B_IGST Amount

			String GSTR3B_IGST_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_IGST_Amount_of_CN"))).getText();

			System.out.println("GSTR3B IGST Amount:- " + GSTR3B_IGST_Amount_of_CN);

			Thread.sleep(500);

			// 3B_Total Tax Amount

			String GSTR3B_Total_Tax_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount_of_CN);

			Thread.sleep(500);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN);

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN);

			Thread.sleep(1000);

			if (Form_IGST_in_CN.equals(GSTR3B_IGST_Amount_of_CN)
					&& (Form_Total_Tax_Amount_in_CN.equals(GSTR3B_Total_Tax_Amount_of_CN)
							&& (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN)
									&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN)))))

			{
				System.out.println(
						" All values of IGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values  ");
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

	public static void createCreditNote_Export_WOPAY_InterState(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();
			// (//*[@class='x-form-field-wrap '] [1])/img

			// (//input[@name='rectype']) [2]

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			// //button[text()='Submit']

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			// Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");

			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]

			Click_Advertising.click();

			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]

			Document_Number.click();

			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]

			Search_Product_in_CN.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			// WebElement SelectPro2 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath
			// ("(//*[@class='x-grid3-col x-grid3-cell x-grid3-td-checker
			// x-grid3-cell-first ']) [3]")));
			// SelectPro2.click();

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "100.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println(Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();

			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			// String Form_Total_Amount = "("+"-"+ Form_Total_Amount1+ ")" ;

			// Integer result1 = Integer.valueOf(Form_Total_Amount1);
			// float Form_Total_Amount = (-result1);

			System.out.println(Form_Total_Amount_in_CN);

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			// (//div[@class='x-layer x-editor x-small-editor x-grid-editor'])
			// [3]/div/input[2]

			// (//div[@class='x-layer x-combo-list '])
			// [3]/div/div[contains(text(),'Rounding Difference.')]

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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
			Search_ID.sendKeys(Credit_Note_No1);

			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();

			Thread.sleep(1000);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN);

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN);

			Thread.sleep(1000);

			if ((Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN)
					&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN))))

			{
				System.out.println(" All values of  SubTotal , Total Amount matching with GSTR3B report values  ");
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

	public static void createCreditNote_NIL_Rated_Exempted(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();
			// (//*[@class='x-form-field-wrap '] [1])/img

			// (//input[@name='rectype']) [2]

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			// //button[text()='Submit']

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			//// *[@class='x-layer x-combo-list ']/div/div/table/tbody/tr/td[2]

			// WebElement Memo_in_CN =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			// ////map[@name='AlienAreas1']//area[1]
			// Thread.sleep(1000);
			// Memo_in_CN.click();
			// Thread.sleep(1000);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			// Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");

			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]

			Click_Advertising.click();

			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]

			Document_Number.click();

			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]

			Search_Product_in_CN.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			// WebElement SelectPro2 =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath
			// ("(//*[@class='x-grid3-col x-grid3-cell x-grid3-td-checker
			// x-grid3-cell-first ']) [3]")));
			// SelectPro2.click();

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "100.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println(Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();

			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			// String Form_Total_Amount = "("+"-"+ Form_Total_Amount1+ ")" ;

			// Integer result1 = Integer.valueOf(Form_Total_Amount1);
			// float Form_Total_Amount = (-result1);

			System.out.println(Form_Total_Amount_in_CN);

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			// (//div[@class='x-layer x-editor x-small-editor x-grid-editor'])
			// [3]/div/input[2]

			// (//div[@class='x-layer x-combo-list '])
			// [3]/div/div[contains(text(),'Rounding Difference.')]

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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
			Search_ID.sendKeys(Credit_Note_No1);

			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();

			Thread.sleep(1000);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN);

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN);

			Thread.sleep(1000);

			if ((Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN)
					&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN))))

			{
				System.out.println(" All values of  SubTotal , Total Amount matching with GSTR3B report values ");
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

	public static void createCreditNote_NonGST(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();
			// (//*[@class='x-form-field-wrap '] [1])/img

			// (//input[@name='rectype']) [2]

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			// //button[text()='Submit']

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			// WebElement Seq_dropdown =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='selectCustomerundefinedcreditnotepanel']/following-sibling::img")));
			// ////map[@name='AlienAreas1']//area[1]
			// Thread.sleep(1000);
			// Seq_dropdown.click();

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			// Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");

			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]

			Click_Advertising.click();

			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]

			Document_Number.click();

			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]

			Search_Product_in_CN.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "100.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println(Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();

			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			System.out.println(Form_Total_Amount_in_CN);

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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
			Search_ID.sendKeys(Credit_Note_No1);

			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();

			Thread.sleep(1000);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN);

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN);

			Thread.sleep(1000);

			if ((Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN)
					&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN))))

			{
				System.out.println(" All values of  SubTotal , Total Amount matching with GSTR3B report values ");
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

	public static void createCreditNote_InterState_3_2_A(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();
			// (//*[@class='x-form-field-wrap '] [1])/img

			// (//input[@name='rectype']) [2]

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			// //button[text()='Submit']

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			// Thread.sleep(1000);

			//// *[@class='x-layer x-combo-list ']/div/div/table/tbody/tr/td[2]

			// WebElement Select_Customer =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer"))));
			// ////map[@name='AlienAreas1']//area[1]
			// WebElement Memo_in_CN =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			// ////map[@name='AlienAreas1']//area[1]
			// Memo_in_CN.click();
			// Thread.sleep(1000);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			// Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");

			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]

			Click_Advertising.click();

			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]
			Search_Product_in_CN.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "100.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println(Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();
			System.out.println(Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();

			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			// String Form_Total_Amount = "("+"-"+ Form_Total_Amount1+ ")" ;

			// Integer result1 = Integer.valueOf(Form_Total_Amount1);
			// float Form_Total_Amount = (-result1);

			System.out.println(Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();

			Thread.sleep(1000);

			// WebElement Tax1_in_CN =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax1_in_CN"))));
			// Tax1_in_CN.click();
			// Thread.sleep(1000);

			// To Get tax //
			// //div[@id='TermSelGrid']/div/div/div/div/div/div/div/div/div/div/div[2]/div/div[*]/table/tbody/tr/td[12]

			String Form_IGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_IGST_in_CN"))))
					.getText();
			// Integer result2 = Integer.valueOf(Form_CGST1);
			// float Form_CGST = (-result2);

			// String Form_CGST = "("+"-"+ Form_CGST1+ ")" ;

			System.out.println("IGST Amount in Credit Note form :- " + Form_IGST_in_CN);

			// System.out.println(Form_IGST_in_CN);

			String Form_CESS_in_CN1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CESS_in_CN1"))))
					.getText();
			// String Form_CESS = "("+"-"+ Form_CESS1+ ")" ;

			// Integer result4 = Integer.valueOf(Form_CESS1);
			// float Form_CESS = (-result4);
			// System.out.println(Form_CESS_in_CN1);

			System.out.println("CESS Amount in Credit Note form :- " + Form_CESS_in_CN1);

			// //div[@class='x-tool x-tool-close ']

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			// (//div[@class='x-layer x-editor x-small-editor x-grid-editor'])
			// [3]/div/input[2]

			// (//div[@class='x-layer x-combo-list '])
			// [3]/div/div[contains(text(),'Rounding Difference.')]

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement View_3_2_A = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_2_A"))));
			View_3_2_A.click();

			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Credit_Note_No1);

			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();

			Thread.sleep(1000);

			// 3B_IGST Amount

			String GSTR3B_IGST_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_IGST_Amount_of_CN"))).getText();

			System.out.println("GSTR3B IGST Amount:- " + GSTR3B_IGST_Amount_of_CN);

			Thread.sleep(500);

			String GSTR3B_CESS_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CESS_Amount_of_CN"))).getText();

			System.out.println("GSTR3B CESS Amount:- " + GSTR3B_CESS_Amount_of_CN);

			Thread.sleep(500);

			// 3B_Total Tax Amount

			String GSTR3B_Total_Tax_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount_of_CN);

			Thread.sleep(500);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN);

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN);

			Thread.sleep(1000);

			if (Form_IGST_in_CN.equals(GSTR3B_IGST_Amount_of_CN) && (Form_CESS_in_CN1.equals(GSTR3B_CESS_Amount_of_CN)
					&& (Form_Total_Tax_Amount_in_CN.equals(GSTR3B_Total_Tax_Amount_of_CN)
							&& (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN)
									&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN))))))

			{
				System.out.println(
						" All values of IGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values  ");
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

	public static void createCreditNote_InterState_3_2_B(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();
			// (//*[@class='x-form-field-wrap '] [1])/img

			// (//input[@name='rectype']) [2]

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			// //button[text()='Submit']

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			// Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));
			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]
			Search_Product_in_CN.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "100.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println(Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();
			System.out.println(Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();

			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			System.out.println(Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();

			Thread.sleep(1000);

			String Form_IGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_IGST_in_CN"))))
					.getText();
			System.out.println("IGST Amount in Credit Note form :- " + Form_IGST_in_CN);

			String Form_CESS_in_CN1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CESS_in_CN1"))))
					.getText();
			System.out.println("CESS Amount in Credit Note form :- " + Form_CESS_in_CN1);

			// //div[@class='x-tool x-tool-close ']

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			// (//div[@class='x-layer x-editor x-small-editor x-grid-editor'])
			// [3]/div/input[2]

			// (//div[@class='x-layer x-combo-list '])
			// [3]/div/div[contains(text(),'Rounding Difference.')]

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement View_3_2_B = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_3_2_B"))));
			View_3_2_B.click();

			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Credit_Note_No1);

			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();

			Thread.sleep(1000);

			// 3B_IGST Amount

			String GSTR3B_IGST_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_IGST_Amount_of_CN"))).getText();

			System.out.println("GSTR3B IGST Amount:- " + GSTR3B_IGST_Amount_of_CN);

			Thread.sleep(500);

			String GSTR3B_CESS_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CESS_Amount_of_CN"))).getText();

			System.out.println("GSTR3B CESS Amount:- " + GSTR3B_CESS_Amount_of_CN);

			Thread.sleep(500);

			// 3B_Total Tax Amount

			String GSTR3B_Total_Tax_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount_of_CN);

			Thread.sleep(500);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN);

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount_of_CN"))).getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN);

			Thread.sleep(1000);

			if (Form_IGST_in_CN.equals(GSTR3B_IGST_Amount_of_CN) && (Form_CESS_in_CN1.equals(GSTR3B_CESS_Amount_of_CN)
					&& (Form_Total_Tax_Amount_in_CN.equals(GSTR3B_Total_Tax_Amount_of_CN)
							&& (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN)
									&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN))))))

			{
				System.out.println(
						" All values of IGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR3B report values  ");
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

	public static void createcreditNote_Import_InterState(WebDriver driver, String Customer_Code, String Product_ID)
			throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule"))));
			CreditNoteModule.click();
			Thread.sleep(1000);

			WebElement Credit_Note_Against_Vendor = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Against_Vendor"))));
			Credit_Note_Against_Vendor.click();
			Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account"))));
			Select_Account.click();

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");

			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising"))));

			Click_Advertising.click();

			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number"))));

			Document_Number.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "100.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			Thread.sleep(1000);
			System.out.println("Form Subtoatl amount in CN :-" + Form_Subtotal_in_CN);

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();
			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println("Form Total amount in CN :-" + Form_Total_Amount_in_CN);

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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
			Search_ID.sendKeys(Credit_Note_No1);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount_of_CN_against_vendor"))).getText();

			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN_against_vendor);

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount_of_CN_against_vendor"))).getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN_against_vendor);

			Thread.sleep(1000);

			if ((Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN_against_vendor)
					&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN_against_vendor))))

			{
				System.out.println(
						" All values of  SubTotal , Total Amount matching with GSTR3B report values in 4.A.1 section ");
			} else {
				System.out.println("Values are Not Matching  in 4.A.1 section");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

	}

	public static void createcreditNote_Import_InterState_Service(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule"))));
			CreditNoteModule.click();
			Thread.sleep(1000);

			WebElement Credit_Note_Against_Vendor = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Against_Vendor"))));
			Credit_Note_Against_Vendor.click();
			Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account"))));
			Select_Account.click();

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");

			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising"))));

			Click_Advertising.click();

			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number"))));

			Document_Number.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "100.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			Thread.sleep(1000);
			System.out.println("Form Subtoatl amount in CN :-" + Form_Subtotal_in_CN);

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();
			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println("Form Total amount in CN :-" + Form_Total_Amount_in_CN);

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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
			Search_ID.sendKeys(Credit_Note_No1);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount_of_CN_against_vendor"))).getText();

			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN_against_vendor);

			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount_of_CN_against_vendor"))).getText();

			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN_against_vendor);

			Thread.sleep(1000);

			if ((Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN_against_vendor)
					&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN_against_vendor))))

			{
				System.out.println(
						" All values of  SubTotal , Total Amount matching with GSTR3B report values in 4.A.2 section ");
			} else {
				System.out.println("Values are Not Matching  in 4.A.2 section");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

	}

	public static void createCreditNote_IntraState_Purchase_side(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule"))));
			CreditNoteModule.click();
			Thread.sleep(1000);

			WebElement Credit_Note_Against_Vendor = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Against_Vendor"))));
			Credit_Note_Against_Vendor.click();
			Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";
			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account"))));
			Select_Account.click();

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));
			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising"))));
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number"))));

			Document_Number.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();

			String Form_Subtotal1 = "100.00";
			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;
			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("SubTotal Amount in Credit Note form :- " + Form_Subtotal_in_CN);
			Thread.sleep(1000);

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();
			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();
			System.out.println("Total Tax Amount in Credit Note form :- " + Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println("Total Amount in Credit Note form :- " + Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();

			Thread.sleep(1000);

			String Form_CGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CGST_in_CN"))))
					.getText();
			System.out.println("CGST Amount in Credit Note form :- " + Form_CGST_in_CN);

			String Form_SGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_SGST_in_CN"))))
					.getText();
			System.out.println("SGST Amount in Credit Note form :- " + Form_SGST_in_CN);

			String Form_CESS_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CESS_in_CN"))))
					.getText();
			System.out.println("CESS Amount in Credit Note form :- " + Form_CESS_in_CN);

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement View_4_A_5 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_4_A_5"))));
			View_4_A_5.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Credit_Note_No1);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_SGST Amount

			String GSTR3B_SGST_Amount_of_CN = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_SGST_Amount")))
					.getText();
			System.out.println("GSTR3B SGST Amount:- " + GSTR3B_SGST_Amount_of_CN);
			Thread.sleep(500);

			// 3B_CGST Amount

			String GSTR3B_CGST_Amount_of_CN = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CGST_Amount")))
					.getText();
			System.out.println("GSTR3B CGST Amount:- " + GSTR3B_CGST_Amount_of_CN);
			Thread.sleep(500);

			// 3B_CESS Amount

			String GSTR3B_CESS_Amount_of_CN = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CESS_Amount")))
					.getText();
			System.out.println("GSTR3B CESS Amount:- " + GSTR3B_CESS_Amount_of_CN);
			Thread.sleep(500);

			// 3B_Total Tax Amount

			String GSTR3B_Total_Tax_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount_of_CN);
			Thread.sleep(500);

			// 3B_Total Taxable Amount

			String GSTR3B_Taxable_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			// //div[contains(@id,'GSTR3BDetailedReport')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[13]/div/div

			String GSTR3B_Total_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount"))).getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			if ((Form_SGST_in_CN.equals(GSTR3B_SGST_Amount_of_CN)) && (Form_CGST_in_CN.equals(GSTR3B_CGST_Amount_of_CN))
					&& (Form_CESS_in_CN.equals(GSTR3B_CESS_Amount_of_CN))
					&& (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN_against_vendor)
							&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN_against_vendor))))

			{
				System.out.println(
						" All values of  SubTotal , Total Amount matching with GSTR3B report values in 4.A.5 section ");
			} else {
				System.out.println("Values are Not Matching  in 4.A.5 section");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

	}

	public static void createCreditNote_InterState_Purchase_side(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule"))));
			CreditNoteModule.click();
			Thread.sleep(1000);

			WebElement Credit_Note_Against_Vendor = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Against_Vendor"))));
			Credit_Note_Against_Vendor.click();
			Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account"))));
			Select_Account.click();

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");

			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising"))));

			Click_Advertising.click();

			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number"))));

			Document_Number.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			String Form_Subtotal1 = "100.00";
			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("SubTotal Amount in Credit Note form :- " + Form_Subtotal_in_CN);
			Thread.sleep(1000);

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();
			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();
			System.out.println("Total Tax Amount in Credit Note form :- " + Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println("Total Amount in Credit Note form :- " + Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();
			Thread.sleep(1000);

			String Form_IGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_IGST_in_CN"))))
					.getText();
			System.out.println("IGST Amount in Credit Note form :- " + Form_IGST_in_CN);

			String Form_CESS_in_CN1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CESS_in_CN1"))))
					.getText();
			System.out.println("CESS Amount in Credit Note form :- " + Form_CESS_in_CN1);

			// //div[@class='x-tool x-tool-close ']

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement View_4_A_5 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_4_A_5"))));
			View_4_A_5.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Credit_Note_No1);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			String GSTR3B_IGST_Amount_of_CN = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_IGST_Amount")))
					.getText();
			System.out.println("GSTR3B IGST Amount:- " + GSTR3B_IGST_Amount_of_CN);
			Thread.sleep(500);

			String GSTR3B_CESS_Amount_of_CN = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CESS_Amount")))
					.getText();
			System.out.println("GSTR3B CESS Amount:- " + GSTR3B_CESS_Amount_of_CN);
			Thread.sleep(500);

			// Tax amount
			String GSTR3B_Total_Tax_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount_of_CN);
			Thread.sleep(500);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			String GSTR3B_Total_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount"))).getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			if ((Form_IGST_in_CN.equals(GSTR3B_IGST_Amount_of_CN) && (Form_CESS_in_CN1.equals(GSTR3B_CESS_Amount_of_CN)
					&& (Form_Total_Tax_Amount_in_CN.equals(GSTR3B_Total_Tax_Amount_of_CN)
							&& (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN_against_vendor)
									&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN_against_vendor)))))))

			{
				System.out.println(
						" All values of  SubTotal , Total Amount matching with GSTR3B report values in 4.A.5 section ");
			} else {
				System.out.println("Values are Not Matching  in 4.A.5 section");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

	}

	public static void createCreditNote_SEZ_WPAY_IntraState_Purchase_side(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule"))));
			CreditNoteModule.click();
			Thread.sleep(1000);

			WebElement Credit_Note_Against_Vendor = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Against_Vendor"))));
			Credit_Note_Against_Vendor.click();
			Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account"))));
			Select_Account.click();

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");

			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising"))));

			Click_Advertising.click();

			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number"))));

			Document_Number.click();

			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));

			Search_Product.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			String Form_Subtotal1 = "100.00";
			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("SubTotal Amount in Credit Note form :- " + Form_Subtotal_in_CN);
			Thread.sleep(1000);

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();
			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();
			System.out.println("Total Tax Amount in Credit Note form :- " + Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println("Total Amount in Credit Note form :- " + Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();
			Thread.sleep(1000);

			String Form_IGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_IGST_in_CN"))))
					.getText();
			System.out.println("IGST Amount in Credit Note form :- " + Form_IGST_in_CN);

			String Form_CESS_in_CN1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CESS_in_CN1"))))
					.getText();
			System.out.println("CESS Amount in Credit Note form :- " + Form_CESS_in_CN1);

			// //div[@class='x-tool x-tool-close ']

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement View_4_A_5 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_4_A_5"))));
			View_4_A_5.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Credit_Note_No1);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			String GSTR3B_IGST_Amount_of_CN = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_IGST_Amount")))
					.getText();
			System.out.println("GSTR3B IGST Amount:- " + GSTR3B_IGST_Amount_of_CN);
			Thread.sleep(500);

			String GSTR3B_CESS_Amount_of_CN = driver.findElement(By.xpath(GST_pro.getProperty("GSTR3B_CESS_Amount")))
					.getText();
			System.out.println("GSTR3B CESS Amount:- " + GSTR3B_CESS_Amount_of_CN);
			Thread.sleep(500);

			// Tax amount
			String GSTR3B_Total_Tax_Amount_of_CN = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Tax_Amount"))).getText();
			System.out.println("GSTR3B Total Tax Amount:- " + GSTR3B_Total_Tax_Amount_of_CN);
			Thread.sleep(500);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			String GSTR3B_Total_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount"))).getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			if ((Form_IGST_in_CN.equals(GSTR3B_IGST_Amount_of_CN) && (Form_CESS_in_CN1.equals(GSTR3B_CESS_Amount_of_CN)
					&& (Form_Total_Tax_Amount_in_CN.equals(GSTR3B_Total_Tax_Amount_of_CN)
							&& (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN_against_vendor)
									&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN_against_vendor)))))))

			{
				System.out.println(
						" All values of  SubTotal , Total Amount matching with GSTR3B report values in 4.A.5 section ");
			} else {
				System.out.println("Values are Not Matching  in 4.A.5 section");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

	}

	public static void createCreditNote_IntraState_Purchase_side_Section_5_1(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule"))));
			CreditNoteModule.click();
			Thread.sleep(1000);

			WebElement Credit_Note_Against_Vendor = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Against_Vendor"))));
			Credit_Note_Against_Vendor.click();
			Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account"))));
			Select_Account.click();
			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising"))));
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number"))));
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			String Form_Subtotal1 = "100.00";
			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("SubTotal Amount in Credit Note form :- " + Form_Subtotal_in_CN);
			Thread.sleep(1000);

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();
			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println("Total Amount in Credit Note form :- " + Form_Total_Amount_in_CN);

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);
			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement View_5_1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_5_1"))));
			View_5_1.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Credit_Note_No1);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			String GSTR3B_Total_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount"))).getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			if (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN_against_vendor)
					&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN_against_vendor)))

			{
				System.out.println(
						" All values of  SubTotal , Total Amount matching with GSTR3B report values in 5.1 section ");
			}

			else {
				System.out.println("Values are Not Matching  in 5.1 section");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

	}

	public static void createCreditNote_InterState_Purchase_side_Section_5_1(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule"))));
			CreditNoteModule.click();
			Thread.sleep(1000);

			WebElement Credit_Note_Against_Vendor = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Against_Vendor"))));
			Credit_Note_Against_Vendor.click();
			Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account"))));
			Select_Account.click();
			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising"))));
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number"))));
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			String Form_Subtotal1 = "100.00";
			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("SubTotal Amount in Credit Note form :- " + Form_Subtotal_in_CN);
			Thread.sleep(1000);

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();
			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println("Total Amount in Credit Note form :- " + Form_Total_Amount_in_CN);

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);
			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement View_5_1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_5_1"))));
			View_5_1.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Credit_Note_No1);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			String GSTR3B_Total_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount"))).getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			if (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN_against_vendor)
					&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN_against_vendor)))

			{
				System.out.println(
						" All values of  SubTotal , Total Amount matching with GSTR3B report values in 5.1 section ");
			}

			else {
				System.out.println("Values are Not Matching  in 5.1 section");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

	}

	public static void createCreditNote_IntraState_Purchase_side_Section_5_2(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule"))));
			CreditNoteModule.click();
			Thread.sleep(1000);

			WebElement Credit_Note_Against_Vendor = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Against_Vendor"))));
			Credit_Note_Against_Vendor.click();
			Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account"))));
			Select_Account.click();
			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising"))));
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number"))));
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			String Form_Subtotal1 = "100.00";
			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("SubTotal Amount in Credit Note form :- " + Form_Subtotal_in_CN);
			Thread.sleep(1000);

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();
			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println("Total Amount in Credit Note form :- " + Form_Total_Amount_in_CN);

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);
			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement View_5_2 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_5_2"))));
			View_5_2.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Credit_Note_No1);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			String GSTR3B_Total_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount"))).getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			if (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN_against_vendor)
					&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN_against_vendor)))

			{
				System.out.println(
						" All values of  SubTotal , Total Amount matching with GSTR3B report values in 5.2 section ");
			}

			else {
				System.out.println("Values are Not Matching  in 5.2 section");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

	}

	public static void createCreditNote_InterState_Purchase_side_Section_5_2(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule"))));
			CreditNoteModule.click();
			Thread.sleep(1000);

			WebElement Credit_Note_Against_Vendor = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Against_Vendor"))));
			Credit_Note_Against_Vendor.click();
			Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account"))));
			Select_Account.click();
			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising"))));
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number"))));
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			String Form_Subtotal1 = "100.00";
			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("SubTotal Amount in Credit Note form :- " + Form_Subtotal_in_CN);
			Thread.sleep(1000);

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();
			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println("Total Amount in Credit Note form :- " + Form_Total_Amount_in_CN);

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);
			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement View_5_1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_5_1"))));
			View_5_1.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Credit_Note_No1);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			String GSTR3B_Total_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount"))).getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			if (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN_against_vendor)
					&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN_against_vendor)))

			{
				System.out.println(
						" All values of  SubTotal , Total Amount matching with GSTR3B report values in 5.2 section ");
			}

			else {
				System.out.println("Values are Not Matching  in 5.2 section");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

	}

	public static void createCreditNote_IntraState_Purchase_side_Section_5_3(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule"))));
			CreditNoteModule.click();
			Thread.sleep(1000);

			WebElement Credit_Note_Against_Vendor = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Against_Vendor"))));
			Credit_Note_Against_Vendor.click();
			Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account"))));
			Select_Account.click();
			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising"))));
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number"))));
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			String Form_Subtotal1 = "100.00";
			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("SubTotal Amount in Credit Note form :- " + Form_Subtotal_in_CN);
			Thread.sleep(1000);

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();
			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println("Total Amount in Credit Note form :- " + Form_Total_Amount_in_CN);

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);
			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement View_5_3 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_5_3"))));
			View_5_3.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Credit_Note_No1);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			String GSTR3B_Total_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount"))).getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			if (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN_against_vendor)
					&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN_against_vendor)))

			{
				System.out.println(
						" All values of  SubTotal , Total Amount matching with GSTR3B report values in 5.2 section ");
			}

			else {
				System.out.println("Values are Not Matching  in 5.2 section");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

	}

	public static void createCreditNote_InterState_Purchase_side_Section_5_3(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule"))));
			CreditNoteModule.click();
			Thread.sleep(1000);

			WebElement Credit_Note_Against_Vendor = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Against_Vendor"))));
			Credit_Note_Against_Vendor.click();
			Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account"))));
			Select_Account.click();
			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising"))));
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number"))));
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			String Form_Subtotal1 = "100.00";
			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("SubTotal Amount in Credit Note form :- " + Form_Subtotal_in_CN);
			Thread.sleep(1000);

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();
			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println("Total Amount in Credit Note form :- " + Form_Total_Amount_in_CN);

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);
			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement View_5_3 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_5_3"))));
			View_5_3.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Credit_Note_No1);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			String GSTR3B_Total_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount"))).getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			if (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN_against_vendor)
					&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN_against_vendor)))

			{
				System.out.println(
						" All values of  SubTotal , Total Amount matching with GSTR3B report values in 5.3 section ");
			}

			else {
				System.out.println("Values are Not Matching  in 5.3 section");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

	}

	public static void createCreditNote_IntraState_Purchase_side_Section_5_4(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule"))));
			CreditNoteModule.click();
			Thread.sleep(1000);

			WebElement Credit_Note_Against_Vendor = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Against_Vendor"))));
			Credit_Note_Against_Vendor.click();
			Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account"))));
			Select_Account.click();
			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising"))));
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number"))));
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			String Form_Subtotal1 = "100.00";
			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("SubTotal Amount in Credit Note form :- " + Form_Subtotal_in_CN);
			Thread.sleep(1000);

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();
			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println("Total Amount in Credit Note form :- " + Form_Total_Amount_in_CN);

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);
			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement View_5_4 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_5_4"))));
			View_5_4.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Credit_Note_No1);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			String GSTR3B_Total_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount"))).getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			if (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN_against_vendor)
					&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN_against_vendor)))

			{
				System.out.println(
						" All values of  SubTotal , Total Amount matching with GSTR3B report values in 5.4 section ");
			}

			else {
				System.out.println("Values are Not Matching  in 5.4 section");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

	}

	public static void createCreditNote_InterState_Purchase_side_Section_5_4(WebDriver driver, String Vendor_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule"))));
			CreditNoteModule.click();
			Thread.sleep(1000);

			WebElement Credit_Note_Against_Vendor = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Against_Vendor"))));
			Credit_Note_Against_Vendor.click();
			Thread.sleep(1000);

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";
			Utilities.enterTextandSelect(Vendor_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account"))));
			Select_Account.click();
			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising"))));
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number"))));
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product"))));
			Search_Product.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			String Form_Subtotal1 = "100.00";
			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("SubTotal Amount in Credit Note form :- " + Form_Subtotal_in_CN);
			Thread.sleep(1000);

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();
			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println("Total Amount in Credit Note form :- " + Form_Total_Amount_in_CN);

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);
			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement View_5_4 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("View_5_4"))));
			View_5_4.click();
			Thread.sleep(1000);

			// Input -->

			WebElement Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_ID"))));
			Search_ID.sendKeys(Credit_Note_No1);
			Thread.sleep(1000);

			// To fetch -->

			WebElement Fetch_Details = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Fetch_Details"))));
			Fetch_Details.click();
			Thread.sleep(1000);

			// 3B_Total Taxable Amount
			String GSTR3B_Taxable_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Taxable_Amount"))).getText();
			System.out.println("GSTR3B Taxable Amount (Subtotal):- " + GSTR3B_Taxable_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			String GSTR3B_Total_Amount_of_CN_against_vendor = driver
					.findElement(By.xpath(GST_pro.getProperty("GSTR3B_Total_Amount"))).getText();
			System.out.println("GSTR3B Total Amount :- " + GSTR3B_Total_Amount_of_CN_against_vendor);
			Thread.sleep(1000);

			if (Form_Subtotal_in_CN.equals(GSTR3B_Taxable_Amount_of_CN_against_vendor)
					&& (Form_Total_Amount_in_CN.equals(GSTR3B_Total_Amount_of_CN_against_vendor)))

			{
				System.out.println(
						" All values of  SubTotal , Total Amount matching with GSTR3B report values in 5.4 section ");
			}

			else {
				System.out.println("Values are Not Matching  in 5.4 section");
			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);

		}

	}

	public static void createCreditNote_Unregistered_IntraState_GSTR1_B2CS(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			// String Customer_Code = "CC00001";
			// String Product_ID = "PID00005";
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			// //button[text()='Submit']

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']"; // selectCustomerundefinedcreditnotepanel

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			// Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");

			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]

			Click_Advertising.click();

			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]

			Document_Number.click();

			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]

			Search_Product_in_CN.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "2,30,000.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("Credit Note Form SubTotal :-" + Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();

			System.out.println("Credit Note Form Total Tax Amount :-" + Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println("Credit Note Form Total Amount :-" + Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();

			Thread.sleep(1000);

			String Form_CGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CGST_in_CN"))))
					.getText();

			System.out.println("CGST Amount in Credit Note form :- " + Form_CGST_in_CN);

			String Form_SGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_SGST_in_CN"))))
					.getText();

			System.out.println("SGST Amount in Credit Note form :- " + Form_SGST_in_CN);

			String Form_CESS_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CESS_in_CN"))))
					.getText();

			System.out.println("CESS Amount in Credit Note form :- " + Form_CESS_in_CN);

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement B2C_Small_Invoices_View = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("B2C_Small_Invoices_View"))));
			B2C_Small_Invoices_View.click();
			Thread.sleep(1000);

			// Input -->

			WebElement GSTR1_Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
			GSTR1_Search_ID.sendKeys(Credit_Note_No1);
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
									+ i + "]/div/div/label"))
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
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 CGST Amount:- " + GSTR1_CGST_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("State Tax Amount"))

				{

					GSTR1_SGST_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 SGST Amount:- " + GSTR1_SGST_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Total Tax Amount"))

				{

					GSTR1_Total_Tax_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total tax Amount:- " + GSTR1_Total_Tax_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Cess Amount"))

				{

					GSTR1_CESS_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 CESS Amount:- " + GSTR1_CESS_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Total Amount Incl Tax"))

				{

					GSTR1_Total_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);

				}

			}

			if (Form_SGST_in_CN.equals(GSTR1_SGST_Amount)
					&& (Form_CGST_in_CN.equals(GSTR1_CGST_Amount) && (Form_CESS_in_CN.equals(GSTR1_CESS_Amount)
							&& (Form_Total_Tax_Amount_in_CN.equals(GSTR1_Total_Tax_Amount)
									&& (Form_Subtotal_in_CN.equals(GSTR1_Taxable_Amount)
											&& (Form_Total_Amount_in_CN.equals(GSTR1_Total_Amount)))))))

			{

				System.out.println(
						" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section B2C Small Invoices ");

			} else {

				System.out.println("Values are Not Matching in section B2C Small Invoices");

			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createCreditNote_Registered_IntraState_GSTR1_CN_DN_Reg(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			// String Customer_Code = "CC00000";
			// String Product_ID = "PID00000";
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			// //button[text()='Submit']

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']"; // selectCustomerundefinedcreditnotepanel

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			// Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");

			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]

			Click_Advertising.click();

			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]

			Document_Number.click();

			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]

			Search_Product_in_CN.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "1,125.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("Credit Note Form SubTotal :-" + Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();

			System.out.println("Credit Note Form Total Tax Amount :-" + Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println("Credit Note Form Total Amount :-" + Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();

			Thread.sleep(1000);

			String Form_CGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CGST_in_CN"))))
					.getText();

			System.out.println("CGST Amount in Credit Note form :- " + Form_CGST_in_CN);

			String Form_SGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_SGST_in_CN"))))
					.getText();

			System.out.println("SGST Amount in Credit Note form :- " + Form_SGST_in_CN);

			String Form_CESS_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CESS_in_CN"))))
					.getText();

			System.out.println("CESS Amount in Credit Note form :- " + Form_CESS_in_CN);

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement Credit_Debit_Note_Registered_9B_View = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Debit_Note_Registered_9B_View"))));
			Credit_Debit_Note_Registered_9B_View.click();
			Thread.sleep(1000);

			// Input -->

			WebElement GSTR1_Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
			GSTR1_Search_ID.sendKeys(Credit_Note_No1);
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
									+ i + "]/div/div/label"))
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
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 CGST Amount:- " + GSTR1_CGST_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("State Tax Amount"))

				{

					GSTR1_SGST_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 SGST Amount:- " + GSTR1_SGST_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Total Tax Amount"))

				{

					GSTR1_Total_Tax_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total tax Amount:- " + GSTR1_Total_Tax_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Cess Amount"))

				{

					GSTR1_CESS_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 CESS Amount:- " + GSTR1_CESS_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Total Amount Incl Tax"))

				{

					GSTR1_Total_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);

				}

			}

			if (Form_SGST_in_CN.equals(GSTR1_SGST_Amount)
					&& (Form_CGST_in_CN.equals(GSTR1_CGST_Amount) && (Form_CESS_in_CN.equals(GSTR1_CESS_Amount)
							&& (Form_Total_Tax_Amount_in_CN.equals(GSTR1_Total_Tax_Amount)
									&& (Form_Subtotal_in_CN.equals(GSTR1_Taxable_Amount)
											&& (Form_Total_Amount_in_CN.equals(GSTR1_Total_Amount)))))))

			{

				System.out.println(
						" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section Credit / Debit Note (Registered - 9B) ");

			} else {

				System.out.println("Values are Not Matching in section Credit / Debit Note (Registered - 9B)");

			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createCreditNote_Registered_InterState_GSTR1_CN_DN_Reg(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));
			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]
			Search_Product_in_CN.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "112.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("Credit Note Form SubTotal :-" + Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();
			System.out.println("Credit Note Form Total Tax Amount :-" + Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();

			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			// String Form_Total_Amount = "("+"-"+ Form_Total_Amount1+ ")" ;

			// Integer result1 = Integer.valueOf(Form_Total_Amount1);
			// float Form_Total_Amount = (-result1);

			System.out.println("Credit Note Form Total Amount :-" + Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();
			Thread.sleep(1000);

			// To Get tax //
			// //div[@id='TermSelGrid']/div/div/div/div/div/div/div/div/div/div/div[2]/div/div[*]/table/tbody/tr/td[12]

			String Form_IGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_IGST_in_CN"))))
					.getText();
			System.out.println("IGST Amount in Credit Note form :- " + Form_IGST_in_CN);

			// System.out.println(Form_IGST_in_CN);

			String Form_CESS_in_CN1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CESS_in_CN1"))))
					.getText();
			System.out.println("CESS Amount in Credit Note form :- " + Form_CESS_in_CN1);

			// //div[@class='x-tool x-tool-close ']

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			// (//div[@class='x-layer x-editor x-small-editor x-grid-editor'])
			// [3]/div/input[2]

			// (//div[@class='x-layer x-combo-list '])
			// [3]/div/div[contains(text(),'Rounding Difference.')]

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);
			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement Credit_Debit_Note_Registered_9B_View = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Debit_Note_Registered_9B_View"))));
			Credit_Debit_Note_Registered_9B_View.click();
			Thread.sleep(1000);

			// Input -->

			WebElement GSTR1_Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
			GSTR1_Search_ID.sendKeys(Credit_Note_No1);
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
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Taxable Amount (Subtotal):- " + GSTR1_Taxable_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Integrated Tax Amount"))

				{

					GSTR1_IGST_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
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
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total tax Amount:- " + GSTR1_Total_Tax_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Cess Amount"))

				{

					GSTR1_CESS_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 CESS Amount:- " + GSTR1_CESS_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Total Amount Incl Tax"))

				{

					GSTR1_Total_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);

				}

			}

			if (Form_IGST_in_CN.equals(GSTR1_IGST_Amount) && (Form_CESS_in_CN1.equals(GSTR1_CESS_Amount)
					&& (Form_Total_Tax_Amount_in_CN.equals(GSTR1_Total_Tax_Amount)
							&& (Form_Subtotal_in_CN.equals(GSTR1_Taxable_Amount)
									&& (Form_Total_Amount_in_CN.equals(GSTR1_Total_Amount))))))

			{
				System.out.println(
						" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section Credit / Debit Note (Registered - 9B) ");

			}

			else {
				System.out.println(
						"Values are Not Matching with GSTR1 report values in section Credit / Debit Note (Registered - 9B)");
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

	public static void createCreditNote_Composition_IntraState_GSTR1_CN_DN_Reg(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			// String Customer_Code = "CC00001";
			// String Product_ID = "PID00005";
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			// //button[text()='Submit']

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']"; // selectCustomerundefinedcreditnotepanel

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			// Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));

			Select_Account1.sendKeys("Advertising");

			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]

			Click_Advertising.click();

			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]

			Document_Number.click();

			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]

			Search_Product_in_CN.sendKeys(Product_ID);

			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "1,125.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("Credit Note Form SubTotal :-" + Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();

			System.out.println("Credit Note Form Total Tax Amount :-" + Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;
			System.out.println("Credit Note Form Total Amount :-" + Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();

			Thread.sleep(1000);

			String Form_CGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CGST_in_CN"))))
					.getText();

			System.out.println("CGST Amount in Credit Note form :- " + Form_CGST_in_CN);

			String Form_SGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_SGST_in_CN"))))
					.getText();

			System.out.println("SGST Amount in Credit Note form :- " + Form_SGST_in_CN);

			String Form_CESS_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CESS_in_CN"))))
					.getText();

			System.out.println("CESS Amount in Credit Note form :- " + Form_CESS_in_CN);

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);

			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement Credit_Debit_Note_Registered_9B_View = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Debit_Note_Registered_9B_View"))));
			Credit_Debit_Note_Registered_9B_View.click();
			Thread.sleep(1000);

			// Input -->

			WebElement GSTR1_Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
			GSTR1_Search_ID.sendKeys(Credit_Note_No1);
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
									+ i + "]/div/div/label"))
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
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 CGST Amount:- " + GSTR1_CGST_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("State Tax Amount"))

				{

					GSTR1_SGST_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 SGST Amount:- " + GSTR1_SGST_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Total Tax Amount"))

				{

					GSTR1_Total_Tax_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total tax Amount:- " + GSTR1_Total_Tax_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Cess Amount"))

				{

					GSTR1_CESS_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 CESS Amount:- " + GSTR1_CESS_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Total Amount Incl Tax"))

				{

					GSTR1_Total_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);

				}

			}

			if (Form_SGST_in_CN.equals(GSTR1_SGST_Amount)
					&& (Form_CGST_in_CN.equals(GSTR1_CGST_Amount) && (Form_CESS_in_CN.equals(GSTR1_CESS_Amount)
							&& (Form_Total_Tax_Amount_in_CN.equals(GSTR1_Total_Tax_Amount)
									&& (Form_Subtotal_in_CN.equals(GSTR1_Taxable_Amount)
											&& (Form_Total_Amount_in_CN.equals(GSTR1_Total_Amount)))))))

			{

				System.out.println(
						" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section Credit / Debit Note (Registered - 9B) ");

			} else {

				System.out.println("Values are Not Matching in section Credit / Debit Note (Registered - 9B)");

			}

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

			driver.navigate().refresh();

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void createCreditNote_Composition_InterState_GSTR1_CN_DN_Reg(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));
			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]
			Search_Product_in_CN.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "112.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("Credit Note Form SubTotal :-" + Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();
			System.out.println("Credit Note Form Total Tax Amount :-" + Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();

			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			// String Form_Total_Amount = "("+"-"+ Form_Total_Amount1+ ")" ;

			// Integer result1 = Integer.valueOf(Form_Total_Amount1);
			// float Form_Total_Amount = (-result1);

			System.out.println("Credit Note Form Total Amount :-" + Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();
			Thread.sleep(1000);

			// To Get tax //
			// //div[@id='TermSelGrid']/div/div/div/div/div/div/div/div/div/div/div[2]/div/div[*]/table/tbody/tr/td[12]

			String Form_IGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_IGST_in_CN"))))
					.getText();
			System.out.println("IGST Amount in Credit Note form :- " + Form_IGST_in_CN);

			// System.out.println(Form_IGST_in_CN);

			String Form_CESS_in_CN1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CESS_in_CN1"))))
					.getText();
			System.out.println("CESS Amount in Credit Note form :- " + Form_CESS_in_CN1);

			// //div[@class='x-tool x-tool-close ']

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			// (//div[@class='x-layer x-editor x-small-editor x-grid-editor'])
			// [3]/div/input[2]

			// (//div[@class='x-layer x-combo-list '])
			// [3]/div/div[contains(text(),'Rounding Difference.')]

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);
			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement Credit_Debit_Note_Registered_9B_View = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Debit_Note_Registered_9B_View"))));
			Credit_Debit_Note_Registered_9B_View.click();
			Thread.sleep(1000);

			// Input -->

			WebElement GSTR1_Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
			GSTR1_Search_ID.sendKeys(Credit_Note_No1);
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
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Taxable Amount (Subtotal):- " + GSTR1_Taxable_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Integrated Tax Amount"))

				{

					GSTR1_IGST_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
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
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total tax Amount:- " + GSTR1_Total_Tax_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Cess Amount"))

				{

					GSTR1_CESS_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 CESS Amount:- " + GSTR1_CESS_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Total Amount Incl Tax"))

				{

					GSTR1_Total_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);

				}

			}

			if (Form_IGST_in_CN.equals(GSTR1_IGST_Amount) && (Form_CESS_in_CN1.equals(GSTR1_CESS_Amount)
					&& (Form_Total_Tax_Amount_in_CN.equals(GSTR1_Total_Tax_Amount)
							&& (Form_Subtotal_in_CN.equals(GSTR1_Taxable_Amount)
									&& (Form_Total_Amount_in_CN.equals(GSTR1_Total_Amount))))))

			{
				System.out.println(
						" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section Credit / Debit Note (Registered - 9B) ");

			}

			else {
				System.out.println(
						"Values are Not Matching with GSTR1 report values in section Credit / Debit Note (Registered - 9B)");
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

	public static void createCreditNote_SEZ_WPAY_InterState_GSTR1_CN_DN_Reg(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			// String Customer_Code ="CC00031";
			// String Product_ID = "PID00014";
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));
			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]
			Search_Product_in_CN.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "112.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("Credit Note Form SubTotal :-" + Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();
			System.out.println("Credit Note Form Total Tax Amount :-" + Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();

			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			// String Form_Total_Amount = "("+"-"+ Form_Total_Amount1+ ")" ;

			// Integer result1 = Integer.valueOf(Form_Total_Amount1);
			// float Form_Total_Amount = (-result1);

			System.out.println("Credit Note Form Total Amount :-" + Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();
			Thread.sleep(1000);

			// To Get tax //
			// //div[@id='TermSelGrid']/div/div/div/div/div/div/div/div/div/div/div[2]/div/div[*]/table/tbody/tr/td[12]

			String Form_IGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_IGST_in_CN"))))
					.getText();
			System.out.println("IGST Amount in Credit Note form :- " + Form_IGST_in_CN);

			// System.out.println(Form_IGST_in_CN);

			String Form_CESS_in_CN1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CESS_in_CN1"))))
					.getText();
			System.out.println("CESS Amount in Credit Note form :- " + Form_CESS_in_CN1);

			// //div[@class='x-tool x-tool-close ']

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			// (//div[@class='x-layer x-editor x-small-editor x-grid-editor'])
			// [3]/div/input[2]

			// (//div[@class='x-layer x-combo-list '])
			// [3]/div/div[contains(text(),'Rounding Difference.')]

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);
			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement Credit_Debit_Note_Registered_9B_View = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Debit_Note_Registered_9B_View"))));
			Credit_Debit_Note_Registered_9B_View.click();
			Thread.sleep(1000);

			// Input -->

			WebElement GSTR1_Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
			GSTR1_Search_ID.sendKeys(Credit_Note_No1);
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
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Taxable Amount (Subtotal):- " + GSTR1_Taxable_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Integrated Tax Amount"))

				{

					GSTR1_IGST_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
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
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total tax Amount:- " + GSTR1_Total_Tax_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Cess Amount"))

				{

					GSTR1_CESS_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 CESS Amount:- " + GSTR1_CESS_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Total Amount Incl Tax"))

				{

					GSTR1_Total_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);

				}

			}

			if (Form_IGST_in_CN.equals(GSTR1_IGST_Amount) && (Form_CESS_in_CN1.equals(GSTR1_CESS_Amount)
					&& (Form_Total_Tax_Amount_in_CN.equals(GSTR1_Total_Tax_Amount)
							&& (Form_Subtotal_in_CN.equals(GSTR1_Taxable_Amount)
									&& (Form_Total_Amount_in_CN.equals(GSTR1_Total_Amount))))))

			{
				System.out.println(
						" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section Credit / Debit Note (Registered - 9B) ");

			}

			else {
				System.out.println(
						"Values are Not Matching with GSTR1 report values in section Credit / Debit Note (Registered - 9B)");
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

	public static void createCreditNote_SEZ_WOPAY_InterState_GSTR1_CN_DN_Reg(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));
			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]
			Search_Product_in_CN.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "112.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("Credit Note Form SubTotal :-" + Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();

			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			// (//div[@class='x-layer x-editor x-small-editor x-grid-editor'])
			// [3]/div/input[2]

			// (//div[@class='x-layer x-combo-list '])
			// [3]/div/div[contains(text(),'Rounding Difference.')]

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);
			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement Credit_Debit_Note_Registered_9B_View = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Debit_Note_Registered_9B_View"))));
			Credit_Debit_Note_Registered_9B_View.click();
			Thread.sleep(1000);

			// Input -->

			WebElement GSTR1_Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
			GSTR1_Search_ID.sendKeys(Credit_Note_No1);
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
									+ i + "]/div/div/label"))
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
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);

				}

			}

			if ((Form_Subtotal_in_CN.equals(GSTR1_Taxable_Amount)
					&& (Form_Total_Amount_in_CN.equals(GSTR1_Total_Amount))))

			{
				System.out.println(
						" All values of CGST , SGST , CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section Credit / Debit Note (Registered - 9B) ");

			}

			else {
				System.out.println(
						"Values are Not Matching with GSTR1 report values in section Credit / Debit Note (Registered - 9B)");
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

	public static void createCreditNote_UnRegistered_InterState_GSTR1_CN_DN_UnReg(WebDriver driver,
			String Customer_Code, String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));
			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]
			Search_Product_in_CN.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "2,23,674.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("Credit Note Form SubTotal :-" + Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();
			System.out.println("Credit Note Form Total Tax Amount :-" + Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();

			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			// String Form_Total_Amount = "("+"-"+ Form_Total_Amount1+ ")" ;

			// Integer result1 = Integer.valueOf(Form_Total_Amount1);
			// float Form_Total_Amount = (-result1);

			System.out.println("Credit Note Form Total Amount :-" + Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();
			Thread.sleep(1000);

			// To Get tax //
			// //div[@id='TermSelGrid']/div/div/div/div/div/div/div/div/div/div/div[2]/div/div[*]/table/tbody/tr/td[12]

			String Form_IGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_IGST_in_CN"))))
					.getText();
			System.out.println("IGST Amount in Credit Note form :- " + Form_IGST_in_CN);

			// System.out.println(Form_IGST_in_CN);

			String Form_CESS_in_CN1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_CESS_in_CN1"))))
					.getText();
			System.out.println("CESS Amount in Credit Note form :- " + Form_CESS_in_CN1);

			// //div[@class='x-tool x-tool-close ']

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			// (//div[@class='x-layer x-editor x-small-editor x-grid-editor'])
			// [3]/div/input[2]

			// (//div[@class='x-layer x-combo-list '])
			// [3]/div/div[contains(text(),'Rounding Difference.')]

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);
			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement Credit_Debit_Note_UnRegistered_9B_View = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Debit_Note_UnRegistered_9B_View"))));
			Credit_Debit_Note_UnRegistered_9B_View.click();
			Thread.sleep(1000);

			// Input -->

			WebElement GSTR1_Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
			GSTR1_Search_ID.sendKeys(Credit_Note_No1);
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
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Taxable Amount (Subtotal):- " + GSTR1_Taxable_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Integrated Tax Amount"))

				{

					GSTR1_IGST_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
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
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total tax Amount:- " + GSTR1_Total_Tax_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Cess Amount"))

				{

					GSTR1_CESS_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 CESS Amount:- " + GSTR1_CESS_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Total Amount Incl Tax"))

				{

					GSTR1_Total_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);

				}

			}

			if (Form_IGST_in_CN.equals(GSTR1_IGST_Amount) && (Form_CESS_in_CN1.equals(GSTR1_CESS_Amount)
					&& (Form_Total_Tax_Amount_in_CN.equals(GSTR1_Total_Tax_Amount)
							&& (Form_Subtotal_in_CN.equals(GSTR1_Taxable_Amount)
									&& (Form_Total_Amount_in_CN.equals(GSTR1_Total_Amount))))))

			{
				System.out.println(
						" All values of IGST, CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section Credit / Debit Note (UnRegistered - 9B) ");

			}

			else {
				System.out.println(
						"Values are Not Matching with GSTR1 report values in section Credit / Debit Note (UnRegistered - 9B)");
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

	public static void createCreditNote_UnRegistered_Export_WPAY_InterState_GSTR1_CN_DN_UnReg(WebDriver driver,
			String Customer_Code, String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));
			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]
			Search_Product_in_CN.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "2,23,674.00";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("Credit Note Form SubTotal :-" + Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Tax_Amount_in_CN = wait
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Tax_Amount_in_CN"))))
					.getText();
			System.out.println("Credit Note Form Total Tax Amount :-" + Form_Total_Tax_Amount_in_CN);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();

			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			System.out.println("Credit Note Form Total Amount :-" + Form_Total_Amount_in_CN);

			WebElement Tax_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_in_CN"))));
			Tax_in_CN.click();
			Thread.sleep(1000);

			// To Get tax //
			// //div[@id='TermSelGrid']/div/div/div/div/div/div/div/div/div/div/div[2]/div/div[*]/table/tbody/tr/td[12]

			String Form_IGST_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_IGST_in_CN"))))
					.getText();
			System.out.println("IGST Amount in Credit Note form :- " + Form_IGST_in_CN);

			// System.out.println(Form_IGST_in_CN);

			WebElement Tax_Close_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Tax_Close_in_CN"))));
			Tax_Close_in_CN.click();

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);
			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement Credit_Debit_Note_UnRegistered_9B_View = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Debit_Note_UnRegistered_9B_View"))));
			Credit_Debit_Note_UnRegistered_9B_View.click();
			Thread.sleep(1000);

			// Input -->

			WebElement GSTR1_Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
			GSTR1_Search_ID.sendKeys(Credit_Note_No1);
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
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Taxable Amount (Subtotal):- " + GSTR1_Taxable_Amount);

				}

				else if (Tax_Name.equalsIgnoreCase("Integrated Tax Amount"))

				{

					GSTR1_IGST_Amount = driver.findElement(By
							.xpath("//div[starts-with(@id,'GSTR1SummaryDetails')]/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td["
									+ i + "]/div/div/label"))
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
									+ i + "]/div/div/label"))
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
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);

				}

			}

			if (Form_IGST_in_CN.equals(GSTR1_IGST_Amount) && (Form_Total_Tax_Amount_in_CN.equals(GSTR1_Total_Tax_Amount)
					&& (Form_Subtotal_in_CN.equals(GSTR1_Taxable_Amount)
							&& (Form_Total_Amount_in_CN.equals(GSTR1_Total_Amount)))))

			{
				System.out.println(
						" All values of IGST, CESS ,Total Tax, SubTotal , Total Amount matching with GSTR1 report values in section Credit / Debit Note (UnRegistered - 9B) ");

			}

			else {
				System.out.println(
						"Values are Not Matching with GSTR1 report values in section Credit / Debit Note (UnRegistered - 9B)");
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

	public static void createCreditNote_UnRegistered_Export_WOPAY_InterState_GSTR1_CN_DN_UnReg(WebDriver driver,
			String Customer_Code, String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));
			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]
			Search_Product_in_CN.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "5,37,716.92";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("Credit Note Form SubTotal :-" + Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			System.out.println("Credit Note Form Total Amount :-" + Form_Total_Amount_in_CN);

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			// (//div[@class='x-layer x-editor x-small-editor x-grid-editor'])
			// [3]/div/input[2]

			// (//div[@class='x-layer x-combo-list '])
			// [3]/div/div[contains(text(),'Rounding Difference.')]

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			// //button[text()='Save']

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			// //button[text()='Yes']

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			// //button[text()='OK']

			// //span[@class='wtf-mb-text']/b[1]

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);
			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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

			WebElement Credit_Debit_Note_UnRegistered_9B_View = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Debit_Note_UnRegistered_9B_View"))));
			Credit_Debit_Note_UnRegistered_9B_View.click();
			Thread.sleep(1000);

			// Input -->

			WebElement GSTR1_Search_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTR1_Search_ID"))));
			GSTR1_Search_ID.sendKeys(Credit_Note_No1);
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
									+ i + "]/div/div/label"))
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
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);

				}

			}

			if (Form_Subtotal_in_CN.equals(GSTR1_Taxable_Amount)
					&& (Form_Total_Amount_in_CN.equals(GSTR1_Total_Amount)))

			{
				System.out.println(
						" All values of SubTotal , Total Amount matching with GSTR1 report values in section Credit / Debit Note (UnRegistered - 9B) ");

			}

			else {
				System.out.println(
						"Values are Not Matching with GSTR1 report values in section Credit / Debit Note (UnRegistered - 9B)");
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

	public static void createCreditNote_NIL_Rated_Exempted_Non_GST(WebDriver driver, String Customer_Code,
			String Product_ID) throws InterruptedException, AWTException, IOException

	{
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			// String Customer_Code = "CC00002";
			// String Product_ID = "PID00004";
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement CreditNoteModule = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CreditNoteModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			CreditNoteModule.click();

			WebElement Credit_Note_Otherwise = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Otherwise")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Credit_Note_Otherwise.click();

			WebElement Submit = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Submit"))));
			Thread.sleep(1000);
			Submit.click();

			String expForname = "//input[@id='selectCustomerundefinedcreditnotepanel']";

			Utilities.enterTextandSelect(Customer_Code, expForname, driver);

			WebElement Select_Account = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account")))); //// map[@name='AlienAreas1']//area[1]
			Select_Account.click();
			Thread.sleep(1000);

			WebElement Select_Account1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Account1"))));
			Select_Account1.sendKeys("Advertising");
			Thread.sleep(1000);

			WebElement Click_Advertising = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Click_Advertising")))); //// map[@name='AlienAreas1']//area[1]
			Click_Advertising.click();
			Thread.sleep(1000);

			WebElement Document_Number = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Document_Number")))); //// map[@name='AlienAreas1']//area[1]
			Document_Number.click();
			Thread.sleep(2000);

			WebElement Search_Product_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Product_in_CN")))); //// map[@name='AlienAreas1']//area[1]
			Search_Product_in_CN.sendKeys(Product_ID);
			Thread.sleep(2000);

			WebElement SelectProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("SelectProduct"))));
			SelectProduct.click();
			Thread.sleep(2000);

			WebElement ADD_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ADD_in_CN"))));
			ADD_in_CN.click();
			Thread.sleep(1000);

			WebElement Amount_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount_in_CN"))));
			Amount_in_CN.click();
			// ₹
			String Form_Subtotal1 = "983.92";

			String Form_Subtotal_in_CN = "₹ " + Form_Subtotal1;

			WebElement Amount1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Amount1_in_CN"))));
			Amount1_in_CN.sendKeys(Form_Subtotal_in_CN);
			System.out.println("Credit Note Form SubTotal :-" + Form_Subtotal_in_CN);

			Thread.sleep(1000);

			// //textarea[@name='memo']

			WebElement Memo_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Memo_in_CN"))));
			Memo_in_CN.click();

			Thread.sleep(1000);

			String Form_Total_Amount1_in_CN = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Form_Total_Amount1_in_CN"))))
					.getText();
			String Form_Total_Amount_in_CN = "₹ " + Form_Total_Amount1_in_CN;

			System.out.println("Credit Note Form Total Amount :-" + Form_Total_Amount_in_CN);

			WebElement Reason_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_in_CN"))));
			Reason_in_CN.click();

			// Rounding Difference.

			WebElement Reason1_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason1_in_CN"))));
			Reason1_in_CN.sendKeys("Rounding Difference");
			Thread.sleep(1000);

			WebElement Reason_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Reason_Click"))));
			Reason_Click.click();
			Thread.sleep(500);

			WebElement Save_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Save_in_CN"))));
			Save_in_CN.click();
			Thread.sleep(500);

			WebElement Yes_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Yes_in_CN"))));
			Yes_in_CN.click();
			Thread.sleep(1000);

			String Credit_Note_No = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_No"))))
					.getText();
			System.out.println(Credit_Note_No);
			String s2 = Credit_Note_No;

			String Credit_Note_No1 = s2.substring(0, 8); // extracting few
															// characters
			System.out.println(Credit_Note_No1);

			System.out.println("Credit Note Created Successfully :- " + Credit_Note_No1);

			System.out.println("********************************************************************");

			WebElement Success_OK_in_CN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK_in_CN"))));
			Success_OK_in_CN.click();
			Thread.sleep(500);

			// //li[@id='as__creditnotepanel']/a[1]

			WebElement Credit_Note_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Note_Close"))));
			Credit_Note_Close.click();
			Thread.sleep(500);

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
			GSTR1_Search_ID.sendKeys(Credit_Note_No1);
			Thread.sleep(2000);

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
									+ i + "]/div/div/label"))
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
									+ i + "]/div/div/label"))
							.getText();
					System.out.println("GSTR1 Total Amount:- " + GSTR1_Total_Amount);

				}

			}

			if (Form_Subtotal_in_CN.equals(GSTR1_Taxable_Amount)
					&& (Form_Total_Amount_in_CN.equals(GSTR1_Total_Amount)))

			{
				System.out.println(
						" All values of SubTotal , Total Amount matching with GSTR1 report values in Section Nil Rated Invoices - 8A, 8B, 8C, 8D");

			}

			else {
				System.out.println(
						"Values are Not Matching with GSTR1 report values in Section Nil Rated Invoices - 8A, 8B, 8C, 8D");
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

}
