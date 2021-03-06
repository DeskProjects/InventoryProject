package krawler.erp.IndianGST;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class GST_Customer_Master

{

	public static final String Customer_Code = null;

	public static String createCustomer_Registered_IntraState(WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{
		String Customer_Code = "";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CustomerMasterModule = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CustomerMasterModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(2000);
			CustomerMasterModule.click();

			WebElement Custo_Seq_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_Seq_dropdown")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(2000);
			Custo_Seq_dropdown.click();

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			WebElement Select_Seq_Format = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Seq_Format")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(2000);
			Select_Seq_Format.click();
			Thread.sleep(2000);

			// To get value from disabled field

			// String Customer_Code = "";

			if (driver instanceof JavascriptExecutor)

			{
				Customer_Code = (String) ((JavascriptExecutor) driver)
						.executeScript("return document.getElementById('acccode41personalDetailCustomerTab').value");

				WebElement Customer_Name = wait
						.until(ExpectedConditions.elementToBeClickable(By.id("accname41personalDetailCustomerTab")));

				Customer_Name.click();

				Thread.sleep(2000);

				Customer_Name.sendKeys(Customer_Code);

			}

			System.out.println(Customer_Code);

			//// *[@id='venodorAvailableToAgentCheck42personalDetailVendorTab']

			WebElement Customer_Agent_Unselect = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Agent_Unselect"))));
			Thread.sleep(2000);
			Customer_Agent_Unselect.click();
			Thread.sleep(2000);

			// //*[@id='creditTerm42personalDetailVendorTab']/following-sibling::img[1]

			WebElement Credit_term__dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_term__dropdown"))));
			Thread.sleep(2000);
			Credit_term__dropdown.click();

			WebElement Select_Credit_Term = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Credit_Term"))));
			Thread.sleep(2000);
			Select_Credit_Term.click();
			Thread.sleep(2000);

			// //input[@name='limit']

			// Credit Sales Limit *

			WebElement Credit_Sales_Limit_IntraState = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Sales_Limit_IntraState"))));
			Thread.sleep(2000);
			Credit_Sales_Limit_IntraState.sendKeys("200000");

			// (//*[@class='x-combo-list-inner']) [2]/div[contains(text(),'NET
			// 0')]

			// GST Registration Type*:

			//// *[@id='GSTINRegistrationType42personalDetailVendorTab']/following-sibling::img

			WebElement Custo_GST_Registration_Type_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_GST_Registration_Type_dropdown"))));
			Thread.sleep(2000);
			Custo_GST_Registration_Type_dropdown.click();

			// --> Registered (//*[@class='x-layer x-combo-list '])
			// [3]/div/div[2]/table/tbody/tr/td[contains(text(),'Registered')]

			WebElement Select_Custo_GST_Registration_Type = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Custo_GST_Registration_Type"))));
			Thread.sleep(2000);
			Select_Custo_GST_Registration_Type.click();

			// GSTIN Input

			WebElement Custo_GSTIN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_GSTIN"))));
			Thread.sleep(2000);
			Custo_GSTIN.sendKeys("27AAHCA6383Q1ZW");
			Thread.sleep(2000);

			// Vendor type

			// //*[@id='CustomerVendorType42personalDetailVendorTab']/following-sibling::img[1]

			WebElement Customer_Type_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Type_dropdown"))));
			Thread.sleep(2000);
			Customer_Type_dropdown.click();

			// --> Registered (//*[@class='x-layer x-combo-list '])
			// [3]/div/div[2]/table/tbody/tr/td[contains(text(),'Registered')]

			WebElement Select_Customer_Type = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_Type"))));
			Thread.sleep(2000);
			Select_Customer_Type.click();

			// Entity
			// //*[@id='Custom_Entity']/following-sibling::img[1]

			WebElement Custo_Entity_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_Entity_dropdown"))));
			Thread.sleep(2000);
			Custo_Entity_dropdown.click();

			// --> Pune //*[@class='x-layer x-combo-list
			// ']/div[1]/div[*]/table/tbody/tr/td[contains(text(),'Pune')]

			WebElement Select_Custo_Entity = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Custo_Entity"))));
			Thread.sleep(2000);
			Select_Custo_Entity.click();

			// Vendor Personal Detail Save

			// //*[@id='savepersonalDetailVendorTab']/tbody/tr/td[*]/em/button

			WebElement Customer_Personal_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Personal_Detail_Save"))));
			Thread.sleep(2000);
			Customer_Personal_Detail_Save.click();

			// Confirm Yes

			//// *[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Yes')]
			//// <--- General code only change text

			WebElement Confirm_Yes = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
			Thread.sleep(2000);
			Confirm_Yes.click();

			// Success Ok

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Ok')]

			WebElement Success_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
			Thread.sleep(2000);
			Success_OK.click();

			// Shift Vendor Contact Detail

			// //*[@id='mainVendorPanel__contactDetailVendorTab']/a[*]/em/span[*]/span[contains(text(),'Vendor
			// Contact Detail')]

			WebElement Shift_Customer_Contact_Detail = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Shift_Customer_Contact_Detail"))));
			Thread.sleep(2000);
			Shift_Customer_Contact_Detail.click();

			// Billing Address

			// //*[@id='billingAddress43contactDetailVendorTab']

			WebElement Customr_Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customr_Address"))));
			Customr_Address.sendKeys("Commerzone");
			Thread.sleep(2000);

			// Billing Address
			// //*[@id='billingStateCombo43contactDetailVendorTab']

			WebElement Customer_State = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_State"))));
			Customer_State.sendKeys("a");
			Thread.sleep(2000);

			// //*[@class='x-layer x-combo-list
			// ']/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Maharashtra')]

			WebElement Select_Customer_State = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_State"))));
			Thread.sleep(2000);
			Select_Customer_State.click();

			// Copy Address
			// //*[@name='copyadress']

			WebElement Copy_Customer_Address = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_Address"))));
			Thread.sleep(2000);
			Copy_Customer_Address.click();

			// Save Address details //Vendor Contact Detail
			// (//*[@class='x-panel-bbar x-panel-bbar-noborder'])
			// [2]/div/table/tbody/tr/td/table/tbody/tr/td[*]/em[1]/button[contains(text(),'Save')]

			WebElement Customer_Contact_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Contact_Detail_Save"))));
			Thread.sleep(2000);
			Customer_Contact_Detail_Save.click();

			// Information save

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]

			WebElement Custo_Information_OK = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_Information_OK"))));
			Thread.sleep(2000);
			Custo_Information_OK.click();

			// Close vendor panel

			//// *[@id='as__mainVendorPanel']/a[1]

			WebElement Close_Customer_Form = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Customer_Form"))));
			Thread.sleep(2000);
			Close_Customer_Form.click();

			System.out.println("Customer Created Successfully :- " + Customer_Code);

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Customer_Code;

	}

	public static String copyCustomer_Registered_InterState(WebDriver driver, String Customer_Code)
			throws InterruptedException, AWTException, IOException

	{
		// Customer_Code = "CC00009";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		WebDriverWait wait = new WebDriverWait(driver, 30);

		Thread.sleep(5000);
		WebElement Customer_Management = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Management"))));
		Customer_Management.click();
		Thread.sleep(5000);

		WebElement Search_Customer = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Customer"))));
		Search_Customer.sendKeys(Customer_Code);
		Thread.sleep(5000);

		WebElement Customer_Select = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Select"))));
		Customer_Select.click();
		Thread.sleep(2000);

		WebElement Manage_Customer = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Manage_Customer"))));
		Manage_Customer.click();
		Thread.sleep(2000);

		WebElement Copy_Customer = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer"))));
		Copy_Customer.click();
		Thread.sleep(2000);

		if (driver instanceof JavascriptExecutor)

		{
			Customer_Code = (String) ((JavascriptExecutor) driver)
					.executeScript("return document.getElementById('acccode41copy-personalDetailCustomerTab').value");

			Thread.sleep(2000);

			WebElement Customer_Name_Copy = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("accname41copy-personalDetailCustomerTab")));

			Customer_Name_Copy.clear();

			Customer_Name_Copy.sendKeys(Customer_Code);
			Thread.sleep(2000);

		}

		WebElement Uncheck_Sales_Person = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Uncheck_Sales_Person"))));
		Uncheck_Sales_Person.click();
		Thread.sleep(2000);

		WebElement Custo_GSTIN = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_GSTIN"))));
		Thread.sleep(2000);
		Custo_GSTIN.sendKeys("27AAHCA6383Q1ZW");
		Thread.sleep(2000);

		WebElement Copy_Customer_Personal_Detail_Save = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_Personal_Detail_Save"))));
		Thread.sleep(2000);
		Copy_Customer_Personal_Detail_Save.click();

		// Confirm Yes

		//// *[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Yes')]
		//// <--- General code only change text

		WebElement Confirm_Yes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
		Thread.sleep(2000);
		Confirm_Yes.click();

		// Success Ok

		// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Ok')]

		WebElement Success_OK = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
		Thread.sleep(2000);
		Success_OK.click();

		// Shift Vendor Contact Detail

		// //*[@id='mainVendorPanel__contactDetailVendorTab']/a[*]/em/span[*]/span[contains(text(),'Vendor
		// Contact Detail')]

		WebElement Copy_Shift_Customer_Contact_Detail = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Shift_Customer_Contact_Detail"))));
		Thread.sleep(2000);
		Copy_Shift_Customer_Contact_Detail.click();

		// Billing Address

		// //*[@id='billingAddress43contactDetailVendorTab']

		// Billing Address
		// //*[@id='billingStateCombo43contactDetailVendorTab']

		WebElement Copy_Customer_State = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_State"))));

		Copy_Customer_State.clear();

		Thread.sleep(2000);
		Copy_Customer_State.sendKeys("a");
		Thread.sleep(2000);

		// //*[@class='x-layer x-combo-list
		// ']/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Maharashtra')]

		WebElement Select_State1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_State1"))));
		Thread.sleep(2000);
		Select_State1.click();

		// Copy Address
		// //*[@name='copyadress']

		WebElement Copy_Address = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Address"))));
		Thread.sleep(2000);
		Copy_Address.click();

		// Save Address details //Vendor Contact Detail
		// (//*[@class='x-panel-bbar x-panel-bbar-noborder'])
		// [2]/div/table/tbody/tr/td/table/tbody/tr/td[*]/em[1]/button[contains(text(),'Save')]

		WebElement Copy_Customer_Contact_Detail_Save = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_Contact_Detail_Save"))));
		Thread.sleep(2000);
		Copy_Customer_Contact_Detail_Save.click();

		// Information save

		// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]

		// WebElement Information_OK =
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]")));
		// Thread.sleep(2000);
		// Information_OK.click();

		// Close vendor panel

		//// *[@id='as__mainVendorPanel']/a[1]

		WebElement Close_Copy_Customer_Form = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Copy_Customer_Form"))));

		Thread.sleep(1000);

		Close_Copy_Customer_Form.click();

		Thread.sleep(2000);

		// as__mainVendorDetails

		WebElement Close_Accounts_Receivables = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Accounts_Receivables"))));
		Thread.sleep(2000);
		Close_Accounts_Receivables.click();

		System.out.println("Customer Created Successfully :- " + Customer_Code);

		System.out.println("********************************************************************");

		Thread.sleep(2000);

		driver.navigate().refresh();

		Thread.sleep(2000);

		return Customer_Code;

	}

	public static String createCustomer_UnRegistered_IntraState(WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{
		String Customer_Code = "";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CustomerMasterModule = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CustomerMasterModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(2000);
			CustomerMasterModule.click();

			WebElement Custo_Seq_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_Seq_dropdown")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(2000);
			Custo_Seq_dropdown.click();

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			WebElement Select_Seq_Format = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Seq_Format")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(2000);
			Select_Seq_Format.click();
			Thread.sleep(2000);

			// To get value from disabled field

			// String Customer_Code = "";

			if (driver instanceof JavascriptExecutor)

			{
				Customer_Code = (String) ((JavascriptExecutor) driver)
						.executeScript("return document.getElementById('acccode41personalDetailCustomerTab').value");

				WebElement Customer_Name = wait
						.until(ExpectedConditions.elementToBeClickable(By.id("accname41personalDetailCustomerTab")));

				Customer_Name.click();

				Thread.sleep(2000);

				Customer_Name.sendKeys(Customer_Code);

			}

			System.out.println(Customer_Code);

			//// *[@id='venodorAvailableToAgentCheck42personalDetailVendorTab']

			WebElement Customer_Agent_Unselect = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Agent_Unselect"))));
			Thread.sleep(2000);
			Customer_Agent_Unselect.click();
			Thread.sleep(2000);

			// //*[@id='creditTerm42personalDetailVendorTab']/following-sibling::img[1]

			WebElement Credit_term__dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_term__dropdown"))));
			Thread.sleep(2000);
			Credit_term__dropdown.click();

			WebElement Select_Credit_Term = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Credit_Term"))));
			Thread.sleep(2000);
			Select_Credit_Term.click();
			Thread.sleep(2000);

			// //input[@name='limit']

			// Credit Sales Limit *

			WebElement Credit_Sales_Limit_IntraState = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Sales_Limit_IntraState"))));
			Thread.sleep(2000);
			Credit_Sales_Limit_IntraState.sendKeys("200000");

			// (//*[@class='x-combo-list-inner']) [2]/div[contains(text(),'NET
			// 0')]

			// GST Registration Type*:

			//// *[@id='GSTINRegistrationType42personalDetailVendorTab']/following-sibling::img

			WebElement Custo_GST_Registration_Type_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_GST_Registration_Type_dropdown"))));
			Thread.sleep(2000);
			Custo_GST_Registration_Type_dropdown.click();

			// --> Registered (//*[@class='x-layer x-combo-list '])
			// [3]/div/div[2]/table/tbody/tr/td[contains(text(),'Registered')]

			WebElement Select_Custo_GST_Registration_Type_Unreg = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Custo_GST_Registration_Type_Unreg"))));
			Thread.sleep(2000);
			Select_Custo_GST_Registration_Type_Unreg.click();

			// GSTIN Input

			// Vendor type

			// //*[@id='CustomerVendorType42personalDetailVendorTab']/following-sibling::img[1]

			WebElement Customer_Type_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Type_dropdown"))));
			Thread.sleep(2000);
			Customer_Type_dropdown.click();

			// --> Registered (//*[@class='x-layer x-combo-list '])
			// [3]/div/div[2]/table/tbody/tr/td[contains(text(),'Registered')]

			WebElement Select_Customer_Type = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_Type"))));
			Thread.sleep(2000);
			Select_Customer_Type.click();

			// Entity
			// //*[@id='Custom_Entity']/following-sibling::img[1]

			WebElement Custo_Entity_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_Entity_dropdown"))));
			Thread.sleep(2000);
			Custo_Entity_dropdown.click();

			// --> Pune //*[@class='x-layer x-combo-list
			// ']/div[1]/div[*]/table/tbody/tr/td[contains(text(),'Pune')]

			WebElement Select_Custo_Entity = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Custo_Entity"))));
			Thread.sleep(2000);
			Select_Custo_Entity.click();

			// Vendor Personal Detail Save

			// //*[@id='savepersonalDetailVendorTab']/tbody/tr/td[*]/em/button

			WebElement Customer_Personal_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Personal_Detail_Save"))));
			Thread.sleep(2000);
			Customer_Personal_Detail_Save.click();

			// Confirm Yes

			//// *[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Yes')]
			//// <--- General code only change text

			WebElement Confirm_Yes = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
			Thread.sleep(2000);
			Confirm_Yes.click();

			// Success Ok

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Ok')]

			WebElement Success_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
			Thread.sleep(2000);
			Success_OK.click();

			// Shift Vendor Contact Detail

			// //*[@id='mainVendorPanel__contactDetailVendorTab']/a[*]/em/span[*]/span[contains(text(),'Vendor
			// Contact Detail')]

			WebElement Shift_Customer_Contact_Detail = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Shift_Customer_Contact_Detail"))));
			Thread.sleep(2000);
			Shift_Customer_Contact_Detail.click();

			// Billing Address

			// //*[@id='billingAddress43contactDetailVendorTab']

			WebElement Customr_Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customr_Address"))));
			Customr_Address.sendKeys("Commerzone");
			Thread.sleep(2000);

			// Billing Address
			// //*[@id='billingStateCombo43contactDetailVendorTab']

			WebElement Customer_State = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_State"))));
			Customer_State.sendKeys("a");
			Thread.sleep(2000);

			// //*[@class='x-layer x-combo-list
			// ']/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Maharashtra')]

			WebElement Select_Customer_State = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_State"))));
			Thread.sleep(2000);
			Select_Customer_State.click();

			// Copy Address
			// //*[@name='copyadress']

			WebElement Copy_Customer_Address = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_Address"))));
			Thread.sleep(2000);
			Copy_Customer_Address.click();

			// Save Address details //Vendor Contact Detail
			// (//*[@class='x-panel-bbar x-panel-bbar-noborder'])
			// [2]/div/table/tbody/tr/td/table/tbody/tr/td[*]/em[1]/button[contains(text(),'Save')]

			WebElement Customer_Contact_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Contact_Detail_Save"))));
			Thread.sleep(2000);
			Customer_Contact_Detail_Save.click();

			// Information save

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]

			WebElement Custo_Information_OK = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_Information_OK"))));
			Thread.sleep(2000);
			Custo_Information_OK.click();

			// Close vendor panel

			//// *[@id='as__mainVendorPanel']/a[1]

			WebElement Close_Customer_Form = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Customer_Form"))));
			Thread.sleep(2000);
			Close_Customer_Form.click();

			System.out.println("Customer Created Successfully :- " + Customer_Code);

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Customer_Code;

	}

	public static String copyCustomer_UnRegistered_InterState(WebDriver driver, String Customer_Code)
			throws InterruptedException, AWTException, IOException

	{
		// Customer_Code = "CC00009";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		WebDriverWait wait = new WebDriverWait(driver, 30);

		Thread.sleep(5000);
		WebElement Customer_Management = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Management"))));
		Customer_Management.click();
		Thread.sleep(5000);

		WebElement Search_Customer = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Customer"))));
		Search_Customer.sendKeys(Customer_Code);
		Thread.sleep(5000);

		WebElement Customer_Select = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Select"))));
		Customer_Select.click();
		Thread.sleep(2000);

		WebElement Manage_Customer = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Manage_Customer"))));
		Manage_Customer.click();
		Thread.sleep(2000);

		WebElement Copy_Customer = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer"))));
		Copy_Customer.click();
		Thread.sleep(2000);

		if (driver instanceof JavascriptExecutor)

		{
			Customer_Code = (String) ((JavascriptExecutor) driver)
					.executeScript("return document.getElementById('acccode41copy-personalDetailCustomerTab').value");

			Thread.sleep(2000);

			WebElement Customer_Name_Copy = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("accname41copy-personalDetailCustomerTab")));

			Customer_Name_Copy.clear();

			Customer_Name_Copy.sendKeys(Customer_Code);
			Thread.sleep(2000);

		}

		WebElement Uncheck_Sales_Person = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Uncheck_Sales_Person"))));
		Uncheck_Sales_Person.click();
		Thread.sleep(2000);

		WebElement Copy_Customer_Personal_Detail_Save = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_Personal_Detail_Save"))));
		Thread.sleep(2000);
		Copy_Customer_Personal_Detail_Save.click();

		// Confirm Yes

		//// *[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Yes')]
		//// <--- General code only change text

		WebElement Confirm_Yes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
		Thread.sleep(2000);
		Confirm_Yes.click();

		// Success Ok

		// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Ok')]

		WebElement Success_OK = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
		Thread.sleep(2000);
		Success_OK.click();

		// Shift Vendor Contact Detail

		// //*[@id='mainVendorPanel__contactDetailVendorTab']/a[*]/em/span[*]/span[contains(text(),'Vendor
		// Contact Detail')]

		WebElement Copy_Shift_Customer_Contact_Detail = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Shift_Customer_Contact_Detail"))));
		Thread.sleep(2000);
		Copy_Shift_Customer_Contact_Detail.click();

		// Billing Address

		// //*[@id='billingAddress43contactDetailVendorTab']

		// Billing Address
		// //*[@id='billingStateCombo43contactDetailVendorTab']

		WebElement Copy_Customer_State = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_State"))));

		Copy_Customer_State.clear();

		Thread.sleep(2000);
		Copy_Customer_State.sendKeys("a");
		Thread.sleep(2000);

		// //*[@class='x-layer x-combo-list
		// ']/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Maharashtra')]

		WebElement Select_State1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_State1"))));
		Thread.sleep(2000);
		Select_State1.click();

		// Copy Address
		// //*[@name='copyadress']

		WebElement Copy_Address = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Address"))));
		Thread.sleep(2000);
		Copy_Address.click();

		// Save Address details //Vendor Contact Detail
		// (//*[@class='x-panel-bbar x-panel-bbar-noborder'])
		// [2]/div/table/tbody/tr/td/table/tbody/tr/td[*]/em[1]/button[contains(text(),'Save')]

		WebElement Copy_Customer_Contact_Detail_Save = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_Contact_Detail_Save"))));
		Thread.sleep(2000);
		Copy_Customer_Contact_Detail_Save.click();

		// Information save

		// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]

		// WebElement Information_OK =
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]")));
		// Thread.sleep(2000);
		// Information_OK.click();

		// Close vendor panel

		//// *[@id='as__mainVendorPanel']/a[1]

		WebElement Close_Copy_Customer_Form = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Copy_Customer_Form"))));

		Thread.sleep(1000);

		Close_Copy_Customer_Form.click();

		Thread.sleep(2000);

		// as__mainVendorDetails

		WebElement Close_Accounts_Receivables = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Accounts_Receivables"))));
		Thread.sleep(2000);
		Close_Accounts_Receivables.click();

		System.out.println("Customer Created Successfully :- " + Customer_Code);

		System.out.println("********************************************************************");

		Thread.sleep(2000);

		driver.navigate().refresh();

		Thread.sleep(2000);

		return Customer_Code;

	}

	public static String createCustomer_Composition_IntraState(WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{
		String Customer_Code = "";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CustomerMasterModule = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CustomerMasterModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(2000);
			CustomerMasterModule.click();

			WebElement Custo_Seq_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_Seq_dropdown")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(2000);
			Custo_Seq_dropdown.click();

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			WebElement Select_Seq_Format = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Seq_Format")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(2000);
			Select_Seq_Format.click();
			Thread.sleep(2000);

			// To get value from disabled field

			// String Customer_Code = "";

			if (driver instanceof JavascriptExecutor)

			{
				Customer_Code = (String) ((JavascriptExecutor) driver)
						.executeScript("return document.getElementById('acccode41personalDetailCustomerTab').value");

				WebElement Customer_Name = wait
						.until(ExpectedConditions.elementToBeClickable(By.id("accname41personalDetailCustomerTab")));

				Customer_Name.click();

				Thread.sleep(2000);

				Customer_Name.sendKeys(Customer_Code);

			}

			System.out.println(Customer_Code);

			//// *[@id='venodorAvailableToAgentCheck42personalDetailVendorTab']

			WebElement Customer_Agent_Unselect = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Agent_Unselect"))));
			Thread.sleep(2000);
			Customer_Agent_Unselect.click();
			Thread.sleep(2000);

			// //*[@id='creditTerm42personalDetailVendorTab']/following-sibling::img[1]

			WebElement Credit_term__dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_term__dropdown"))));
			Thread.sleep(2000);
			Credit_term__dropdown.click();

			WebElement Select_Credit_Term = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Credit_Term"))));
			Thread.sleep(2000);
			Select_Credit_Term.click();
			Thread.sleep(2000);

			// //input[@name='limit']

			// Credit Sales Limit *

			WebElement Credit_Sales_Limit_IntraState = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Sales_Limit_IntraState"))));
			Thread.sleep(2000);
			Credit_Sales_Limit_IntraState.sendKeys("200000");

			// (//*[@class='x-combo-list-inner']) [2]/div[contains(text(),'NET
			// 0')]

			// GST Registration Type*:

			//// *[@id='GSTINRegistrationType42personalDetailVendorTab']/following-sibling::img

			WebElement Custo_GST_Registration_Type_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_GST_Registration_Type_dropdown"))));
			Thread.sleep(2000);
			Custo_GST_Registration_Type_dropdown.click();

			// --> Registered (//*[@class='x-layer x-combo-list '])
			// [3]/div/div[2]/table/tbody/tr/td[contains(text(),'Registered')]

			WebElement Select_Custo_GST_Registration_Type_Composition = wait
					.until(ExpectedConditions.elementToBeClickable(
							By.xpath(GST_pro.getProperty("Select_Custo_GST_Registration_Type_Composition"))));
			Thread.sleep(2000);
			Select_Custo_GST_Registration_Type_Composition.click();

			// GSTIN Input

			WebElement Custo_GSTIN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_GSTIN"))));
			Thread.sleep(2000);
			Custo_GSTIN.sendKeys("27AAHCA6383Q1ZW");
			Thread.sleep(2000);

			// Vendor type

			// //*[@id='CustomerVendorType42personalDetailVendorTab']/following-sibling::img[1]

			WebElement Customer_Type_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Type_dropdown"))));
			Thread.sleep(2000);
			Customer_Type_dropdown.click();

			// --> Registered (//*[@class='x-layer x-combo-list '])
			// [3]/div/div[2]/table/tbody/tr/td[contains(text(),'Registered')]

			WebElement Select_Customer_Type = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_Type"))));
			Thread.sleep(2000);
			Select_Customer_Type.click();

			// Entity
			// //*[@id='Custom_Entity']/following-sibling::img[1]

			WebElement Custo_Entity_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_Entity_dropdown"))));
			Thread.sleep(2000);
			Custo_Entity_dropdown.click();

			// --> Pune //*[@class='x-layer x-combo-list
			// ']/div[1]/div[*]/table/tbody/tr/td[contains(text(),'Pune')]

			WebElement Select_Custo_Entity = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Custo_Entity"))));
			Thread.sleep(2000);
			Select_Custo_Entity.click();

			// Vendor Personal Detail Save

			// //*[@id='savepersonalDetailVendorTab']/tbody/tr/td[*]/em/button

			WebElement Customer_Personal_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Personal_Detail_Save"))));
			Thread.sleep(2000);
			Customer_Personal_Detail_Save.click();

			// Confirm Yes

			//// *[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Yes')]
			//// <--- General code only change text

			WebElement Confirm_Yes = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
			Thread.sleep(2000);
			Confirm_Yes.click();

			// Success Ok

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Ok')]

			WebElement Success_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
			Thread.sleep(2000);
			Success_OK.click();

			// Shift Vendor Contact Detail

			// //*[@id='mainVendorPanel__contactDetailVendorTab']/a[*]/em/span[*]/span[contains(text(),'Vendor
			// Contact Detail')]

			WebElement Shift_Customer_Contact_Detail = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Shift_Customer_Contact_Detail"))));
			Thread.sleep(2000);
			Shift_Customer_Contact_Detail.click();

			// Billing Address

			// //*[@id='billingAddress43contactDetailVendorTab']

			WebElement Customr_Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customr_Address"))));
			Customr_Address.sendKeys("Commerzone");
			Thread.sleep(2000);

			// Billing Address
			// //*[@id='billingStateCombo43contactDetailVendorTab']

			WebElement Customer_State = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_State"))));
			Customer_State.sendKeys("a");
			Thread.sleep(2000);

			// //*[@class='x-layer x-combo-list
			// ']/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Maharashtra')]

			WebElement Select_Customer_State = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_State"))));
			Thread.sleep(2000);
			Select_Customer_State.click();

			// Copy Address
			// //*[@name='copyadress']

			WebElement Copy_Customer_Address = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_Address"))));
			Thread.sleep(2000);
			Copy_Customer_Address.click();

			// Save Address details //Vendor Contact Detail
			// (//*[@class='x-panel-bbar x-panel-bbar-noborder'])
			// [2]/div/table/tbody/tr/td/table/tbody/tr/td[*]/em[1]/button[contains(text(),'Save')]

			WebElement Customer_Contact_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Contact_Detail_Save"))));
			Thread.sleep(2000);
			Customer_Contact_Detail_Save.click();

			// Information save

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]

			WebElement Custo_Information_OK = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_Information_OK"))));
			Thread.sleep(2000);
			Custo_Information_OK.click();

			// Close vendor panel

			//// *[@id='as__mainVendorPanel']/a[1]

			WebElement Close_Customer_Form = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Customer_Form"))));
			Thread.sleep(2000);
			Close_Customer_Form.click();

			System.out.println("Customer Created Successfully :- " + Customer_Code);

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Customer_Code;

	}

	public static String createCustomer_Composition_InterState(WebDriver driver, String Customer_Code)
			throws InterruptedException, AWTException, IOException

	{
		// Customer_Code = "CC00009";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		WebDriverWait wait = new WebDriverWait(driver, 30);

		Thread.sleep(5000);
		WebElement Customer_Management = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Management"))));
		Customer_Management.click();
		Thread.sleep(5000);

		WebElement Search_Customer = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Customer"))));
		Search_Customer.sendKeys(Customer_Code);
		Thread.sleep(5000);

		WebElement Customer_Select = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Select"))));
		Customer_Select.click();
		Thread.sleep(2000);

		WebElement Manage_Customer = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Manage_Customer"))));
		Manage_Customer.click();
		Thread.sleep(2000);

		WebElement Copy_Customer = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer"))));
		Copy_Customer.click();
		Thread.sleep(2000);

		if (driver instanceof JavascriptExecutor)

		{
			Customer_Code = (String) ((JavascriptExecutor) driver)
					.executeScript("return document.getElementById('acccode41copy-personalDetailCustomerTab').value");

			Thread.sleep(2000);

			WebElement Customer_Name_Copy = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("accname41copy-personalDetailCustomerTab")));

			Customer_Name_Copy.clear();

			Customer_Name_Copy.sendKeys(Customer_Code);
			Thread.sleep(2000);

		}

		WebElement Uncheck_Sales_Person = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Uncheck_Sales_Person"))));
		Uncheck_Sales_Person.click();
		Thread.sleep(2000);

		WebElement Custo_GSTIN = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_GSTIN"))));
		Thread.sleep(2000);
		Custo_GSTIN.sendKeys("27AAHCA6383Q1ZW");
		Thread.sleep(2000);

		WebElement Copy_Customer_Personal_Detail_Save = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_Personal_Detail_Save"))));
		Thread.sleep(2000);
		Copy_Customer_Personal_Detail_Save.click();

		// Confirm Yes

		//// *[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Yes')]
		//// <--- General code only change text

		WebElement Confirm_Yes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
		Thread.sleep(2000);
		Confirm_Yes.click();

		// Success Ok

		// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Ok')]

		WebElement Success_OK = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
		Thread.sleep(2000);
		Success_OK.click();

		// Shift Vendor Contact Detail

		// //*[@id='mainVendorPanel__contactDetailVendorTab']/a[*]/em/span[*]/span[contains(text(),'Vendor
		// Contact Detail')]

		WebElement Copy_Shift_Customer_Contact_Detail = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Shift_Customer_Contact_Detail"))));
		Thread.sleep(2000);
		Copy_Shift_Customer_Contact_Detail.click();

		// Billing Address

		// //*[@id='billingAddress43contactDetailVendorTab']

		// Billing Address
		// //*[@id='billingStateCombo43contactDetailVendorTab']

		WebElement Copy_Customer_State = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_State"))));

		Copy_Customer_State.clear();

		Thread.sleep(2000);
		Copy_Customer_State.sendKeys("a");
		Thread.sleep(2000);

		// //*[@class='x-layer x-combo-list
		// ']/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Maharashtra')]

		WebElement Select_State1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_State1"))));
		Thread.sleep(2000);
		Select_State1.click();

		// Copy Address
		// //*[@name='copyadress']

		WebElement Copy_Address = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Address"))));
		Thread.sleep(2000);
		Copy_Address.click();

		// Save Address details //Vendor Contact Detail
		// (//*[@class='x-panel-bbar x-panel-bbar-noborder'])
		// [2]/div/table/tbody/tr/td/table/tbody/tr/td[*]/em[1]/button[contains(text(),'Save')]

		WebElement Copy_Customer_Contact_Detail_Save = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_Contact_Detail_Save"))));
		Thread.sleep(2000);
		Copy_Customer_Contact_Detail_Save.click();

		// Information save

		// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]

		// WebElement Information_OK =
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]")));
		// Thread.sleep(2000);
		// Information_OK.click();

		// Close vendor panel

		//// *[@id='as__mainVendorPanel']/a[1]

		WebElement Close_Copy_Customer_Form = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Copy_Customer_Form"))));

		Thread.sleep(1000);

		Close_Copy_Customer_Form.click();

		Thread.sleep(2000);

		// as__mainVendorDetails

		WebElement Close_Accounts_Receivables = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Accounts_Receivables"))));
		Thread.sleep(2000);
		Close_Accounts_Receivables.click();

		System.out.println("Customer Created Successfully :- " + Customer_Code);

		System.out.println("********************************************************************");

		return Customer_Code;

	}

	public static String createCustomer_SEZ_WPAY(WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{
		String Customer_Code = "";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CustomerMasterModule = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CustomerMasterModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(2000);
			CustomerMasterModule.click();

			WebElement Custo_Seq_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_Seq_dropdown")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(2000);
			Custo_Seq_dropdown.click();

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			WebElement Select_Seq_Format = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Seq_Format")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(2000);
			Select_Seq_Format.click();
			Thread.sleep(2000);

			// To get value from disabled field

			// String Customer_Code = "";

			if (driver instanceof JavascriptExecutor)

			{
				Customer_Code = (String) ((JavascriptExecutor) driver)
						.executeScript("return document.getElementById('acccode41personalDetailCustomerTab').value");

				WebElement Customer_Name = wait
						.until(ExpectedConditions.elementToBeClickable(By.id("accname41personalDetailCustomerTab")));

				Customer_Name.click();

				Thread.sleep(2000);

				Customer_Name.sendKeys(Customer_Code);

			}

			System.out.println(Customer_Code);

			//// *[@id='venodorAvailableToAgentCheck42personalDetailVendorTab']

			WebElement Customer_Agent_Unselect = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Agent_Unselect"))));
			Thread.sleep(2000);
			Customer_Agent_Unselect.click();
			Thread.sleep(2000);

			// //*[@id='creditTerm42personalDetailVendorTab']/following-sibling::img[1]

			WebElement Credit_term__dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_term__dropdown"))));
			Thread.sleep(2000);
			Credit_term__dropdown.click();

			WebElement Select_Credit_Term = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Credit_Term"))));
			Thread.sleep(2000);
			Select_Credit_Term.click();
			Thread.sleep(2000);

			// //input[@name='limit']

			// Credit Sales Limit *

			WebElement Credit_Sales_Limit_IntraState = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Sales_Limit_IntraState"))));
			Thread.sleep(2000);
			Credit_Sales_Limit_IntraState.sendKeys("200000");

			// (//*[@class='x-combo-list-inner']) [2]/div[contains(text(),'NET
			// 0')]

			// GST Registration Type*:

			//// *[@id='GSTINRegistrationType42personalDetailVendorTab']/following-sibling::img

			WebElement Custo_GST_Registration_Type_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_GST_Registration_Type_dropdown"))));
			Thread.sleep(2000);
			Custo_GST_Registration_Type_dropdown.click();

			// --> Registered (//*[@class='x-layer x-combo-list '])
			// [3]/div/div[2]/table/tbody/tr/td[contains(text(),'Registered')]

			WebElement Select_Custo_GST_Registration_Type = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Custo_GST_Registration_Type"))));
			Thread.sleep(2000);
			Select_Custo_GST_Registration_Type.click();

			// GSTIN Input

			WebElement Custo_GSTIN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_GSTIN"))));
			Thread.sleep(2000);
			Custo_GSTIN.sendKeys("27AAHCA6383Q1ZW");
			Thread.sleep(2000);

			WebElement Customer_Type_dropdown_SEZ_WAY = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Type_dropdown_SEZ_WAY"))));
			Thread.sleep(2000);
			Customer_Type_dropdown_SEZ_WAY.click();

			// --> Registered (//*[@class='x-layer x-combo-list '])
			// [3]/div/div[2]/table/tbody/tr/td[contains(text(),'Registered')]

			WebElement Select_Customer_Type_SEZ_WPAY = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_Type_SEZ_WPAY"))));
			Thread.sleep(2000);
			Select_Customer_Type_SEZ_WPAY.click();

			// Entity
			// //*[@id='Custom_Entity']/following-sibling::img[1]

			WebElement Custo_Entity_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_Entity_dropdown"))));
			Thread.sleep(2000);
			Custo_Entity_dropdown.click();

			// --> Pune //*[@class='x-layer x-combo-list
			// ']/div[1]/div[*]/table/tbody/tr/td[contains(text(),'Pune')]

			WebElement Select_Custo_Entity = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Custo_Entity"))));
			Thread.sleep(2000);
			Select_Custo_Entity.click();

			// Vendor Personal Detail Save

			// //*[@id='savepersonalDetailVendorTab']/tbody/tr/td[*]/em/button

			WebElement Customer_Personal_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Personal_Detail_Save"))));
			Thread.sleep(2000);
			Customer_Personal_Detail_Save.click();

			// Confirm Yes

			//// *[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Yes')]
			//// <--- General code only change text

			WebElement Confirm_Yes = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
			Thread.sleep(2000);
			Confirm_Yes.click();

			// Success Ok

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Ok')]

			WebElement Success_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
			Thread.sleep(2000);
			Success_OK.click();

			// Shift Vendor Contact Detail

			// //*[@id='mainVendorPanel__contactDetailVendorTab']/a[*]/em/span[*]/span[contains(text(),'Vendor
			// Contact Detail')]

			WebElement Shift_Customer_Contact_Detail = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Shift_Customer_Contact_Detail"))));
			Thread.sleep(2000);
			Shift_Customer_Contact_Detail.click();

			// Billing Address

			// //*[@id='billingAddress43contactDetailVendorTab']

			WebElement Customr_Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customr_Address"))));
			Customr_Address.sendKeys("Commerzone");
			Thread.sleep(2000);

			// Billing Address
			// //*[@id='billingStateCombo43contactDetailVendorTab']

			WebElement Customer_State = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_State"))));
			Customer_State.sendKeys("a");
			Thread.sleep(2000);

			// //*[@class='x-layer x-combo-list
			// ']/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Maharashtra')]

			WebElement Select_Customer_State = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_State"))));
			Thread.sleep(2000);
			Select_Customer_State.click();

			// Copy Address
			// //*[@name='copyadress']

			WebElement Copy_Customer_Address = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_Address"))));
			Thread.sleep(2000);
			Copy_Customer_Address.click();

			// Save Address details //Vendor Contact Detail
			// (//*[@class='x-panel-bbar x-panel-bbar-noborder'])
			// [2]/div/table/tbody/tr/td/table/tbody/tr/td[*]/em[1]/button[contains(text(),'Save')]

			WebElement Customer_Contact_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Contact_Detail_Save"))));
			Thread.sleep(2000);
			Customer_Contact_Detail_Save.click();

			// Information save

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]

			WebElement Custo_Information_OK = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_Information_OK"))));
			Thread.sleep(2000);
			Custo_Information_OK.click();

			// Close vendor panel

			//// *[@id='as__mainVendorPanel']/a[1]

			WebElement Close_Customer_Form = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Customer_Form"))));
			Thread.sleep(2000);
			Close_Customer_Form.click();

			System.out.println("Customer Created Successfully :- " + Customer_Code);

			System.out.println("********************************************************************");

			driver.navigate().refresh();
			Thread.sleep(2000);

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Customer_Code;

	}

	public static String copyCustomer_SEZ_WOPAY(WebDriver driver, String Customer_Code)
			throws InterruptedException, AWTException, IOException

	{
		// Customer_Code = "CC00009";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		WebDriverWait wait = new WebDriverWait(driver, 30);

		Thread.sleep(5000);
		WebElement Customer_Management = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Management"))));
		Customer_Management.click();
		Thread.sleep(5000);

		WebElement Search_Customer = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Customer"))));
		Search_Customer.sendKeys(Customer_Code);
		Thread.sleep(5000);

		WebElement Customer_Select = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Select"))));
		Customer_Select.click();
		Thread.sleep(2000);

		WebElement Manage_Customer = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Manage_Customer"))));
		Manage_Customer.click();
		Thread.sleep(2000);

		WebElement Copy_Customer = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer"))));
		Copy_Customer.click();
		Thread.sleep(2000);

		if (driver instanceof JavascriptExecutor)

		{
			Customer_Code = (String) ((JavascriptExecutor) driver)
					.executeScript("return document.getElementById('acccode41copy-personalDetailCustomerTab').value");

			Thread.sleep(2000);

			WebElement Customer_Name_Copy = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("accname41copy-personalDetailCustomerTab")));

			Customer_Name_Copy.clear();

			Customer_Name_Copy.sendKeys(Customer_Code);
			Thread.sleep(2000);

		}

		WebElement Uncheck_Sales_Person = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Uncheck_Sales_Person"))));
		Uncheck_Sales_Person.click();
		Thread.sleep(2000);

		WebElement Custo_GSTIN = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_GSTIN"))));
		Thread.sleep(2000);
		Custo_GSTIN.sendKeys("27AAHCA6383Q1ZW");
		Thread.sleep(2000);

		WebElement Customer_Type_dropdown_SEZ_WOAY = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Type_dropdown_SEZ_WOAY"))));
		Thread.sleep(2000);
		Customer_Type_dropdown_SEZ_WOAY.click();

		// --> Registered (//*[@class='x-layer x-combo-list '])
		// [3]/div/div[2]/table/tbody/tr/td[contains(text(),'Registered')]

		WebElement Select_Customer_Type_SEZ_WOPAY = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_Type_SEZ_WOPAY"))));
		Thread.sleep(2000);
		Select_Customer_Type_SEZ_WOPAY.click();

		WebElement Copy_Customer_Personal_Detail_Save = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_Personal_Detail_Save"))));
		Thread.sleep(2000);
		Copy_Customer_Personal_Detail_Save.click();

		// Confirm Yes

		//// *[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Yes')]
		//// <--- General code only change text

		WebElement Confirm_Yes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
		Thread.sleep(2000);
		Confirm_Yes.click();

		// Success Ok

		// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Ok')]

		WebElement Success_OK = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
		Thread.sleep(2000);
		Success_OK.click();

		// Shift Vendor Contact Detail

		// //*[@id='mainVendorPanel__contactDetailVendorTab']/a[*]/em/span[*]/span[contains(text(),'Vendor
		// Contact Detail')]

		WebElement Copy_Shift_Customer_Contact_Detail = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Shift_Customer_Contact_Detail"))));
		Thread.sleep(2000);
		Copy_Shift_Customer_Contact_Detail.click();

		// Billing Address

		// //*[@id='billingAddress43contactDetailVendorTab']

		// Billing Address
		// //*[@id='billingStateCombo43contactDetailVendorTab']

		WebElement Copy_Customer_State = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_State"))));

		Copy_Customer_State.clear();

		Thread.sleep(2000);
		Copy_Customer_State.sendKeys("a");
		Thread.sleep(2000);

		// //*[@class='x-layer x-combo-list
		// ']/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Maharashtra')]

		WebElement Select_State1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_State1"))));
		Thread.sleep(2000);
		Select_State1.click();

		// Copy Address
		// //*[@name='copyadress']

		WebElement Copy_Address = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Address"))));
		Thread.sleep(2000);
		Copy_Address.click();

		// Save Address details //Vendor Contact Detail
		// (//*[@class='x-panel-bbar x-panel-bbar-noborder'])
		// [2]/div/table/tbody/tr/td/table/tbody/tr/td[*]/em[1]/button[contains(text(),'Save')]

		WebElement Copy_Customer_Contact_Detail_Save = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_Contact_Detail_Save"))));
		Thread.sleep(2000);
		Copy_Customer_Contact_Detail_Save.click();

		// Information save

		// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]

		// WebElement Information_OK =
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]")));
		// Thread.sleep(2000);
		// Information_OK.click();

		// Close vendor panel

		//// *[@id='as__mainVendorPanel']/a[1]

		WebElement Close_Copy_Customer_Form = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Copy_Customer_Form"))));

		Thread.sleep(1000);

		Close_Copy_Customer_Form.click();

		Thread.sleep(2000);

		// as__mainVendorDetails

		WebElement Close_Accounts_Receivables = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Accounts_Receivables"))));
		Thread.sleep(2000);
		Close_Accounts_Receivables.click();

		System.out.println("Customer Created Successfully :- " + Customer_Code);

		System.out.println("********************************************************************");

		return Customer_Code;

	}

	public static String createCustomer_Export_WPAY(WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{
		String Customer_Code = "";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement CustomerMasterModule = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("CustomerMasterModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(2000);
			CustomerMasterModule.click();

			WebElement Custo_Seq_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_Seq_dropdown")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(2000);
			Custo_Seq_dropdown.click();

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			WebElement Select_Seq_Format = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Seq_Format")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(2000);
			Select_Seq_Format.click();
			Thread.sleep(2000);

			// To get value from disabled field

			// String Customer_Code = "";

			if (driver instanceof JavascriptExecutor)

			{
				Customer_Code = (String) ((JavascriptExecutor) driver)
						.executeScript("return document.getElementById('acccode41personalDetailCustomerTab').value");

				WebElement Customer_Name = wait
						.until(ExpectedConditions.elementToBeClickable(By.id("accname41personalDetailCustomerTab")));

				Customer_Name.click();

				Thread.sleep(2000);

				Customer_Name.sendKeys(Customer_Code);

			}

			System.out.println(Customer_Code);

			//// *[@id='venodorAvailableToAgentCheck42personalDetailVendorTab']

			WebElement Customer_Agent_Unselect = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Agent_Unselect"))));
			Thread.sleep(2000);
			Customer_Agent_Unselect.click();
			Thread.sleep(2000);

			// //*[@id='creditTerm42personalDetailVendorTab']/following-sibling::img[1]

			WebElement Credit_term__dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_term__dropdown"))));
			Thread.sleep(2000);
			Credit_term__dropdown.click();

			WebElement Select_Credit_Term = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Credit_Term"))));
			Thread.sleep(2000);
			Select_Credit_Term.click();
			Thread.sleep(2000);

			// //input[@name='limit']

			// Credit Sales Limit *

			WebElement Credit_Sales_Limit_IntraState = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Sales_Limit_IntraState"))));
			Thread.sleep(2000);
			Credit_Sales_Limit_IntraState.sendKeys("200000");

			// (//*[@class='x-combo-list-inner']) [2]/div[contains(text(),'NET
			// 0')]

			// GST Registration Type*:

			//// *[@id='GSTINRegistrationType42personalDetailVendorTab']/following-sibling::img

			WebElement Custo_GST_Registration_Type_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_GST_Registration_Type_dropdown"))));
			Thread.sleep(2000);
			Custo_GST_Registration_Type_dropdown.click();

			// --> Registered (//*[@class='x-layer x-combo-list '])
			// [3]/div/div[2]/table/tbody/tr/td[contains(text(),'Registered')]

			WebElement Select_Custo_GST_Registration_Type_Unreg = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Custo_GST_Registration_Type_Unreg"))));
			Thread.sleep(2000);
			Select_Custo_GST_Registration_Type_Unreg.click();

			// GSTIN Input

			WebElement Customer_Type_dropdown_SEZ_WAY = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Type_dropdown_SEZ_WAY"))));
			Thread.sleep(2000);
			Customer_Type_dropdown_SEZ_WAY.click();

			// --> Registered (//*[@class='x-layer x-combo-list '])
			// [3]/div/div[2]/table/tbody/tr/td[contains(text(),'Registered')]

			WebElement Customer_Type_dropdown_Export_WAY = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Type_dropdown_Export_WAY"))));
			Thread.sleep(2000);
			Customer_Type_dropdown_Export_WAY.click();

			// Entity
			// //*[@id='Custom_Entity']/following-sibling::img[1]

			WebElement Custo_Entity_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_Entity_dropdown"))));
			Thread.sleep(2000);
			Custo_Entity_dropdown.click();

			// --> Pune //*[@class='x-layer x-combo-list
			// ']/div[1]/div[*]/table/tbody/tr/td[contains(text(),'Pune')]

			WebElement Select_Custo_Entity = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Custo_Entity"))));
			Thread.sleep(2000);
			Select_Custo_Entity.click();

			// Vendor Personal Detail Save

			// //*[@id='savepersonalDetailVendorTab']/tbody/tr/td[*]/em/button

			WebElement Customer_Personal_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Personal_Detail_Save"))));
			Thread.sleep(2000);
			Customer_Personal_Detail_Save.click();

			// Confirm Yes

			//// *[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Yes')]
			//// <--- General code only change text

			WebElement Confirm_Yes = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
			Thread.sleep(2000);
			Confirm_Yes.click();

			// Success Ok

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Ok')]

			WebElement Success_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
			Thread.sleep(2000);
			Success_OK.click();

			// Shift Vendor Contact Detail

			// //*[@id='mainVendorPanel__contactDetailVendorTab']/a[*]/em/span[*]/span[contains(text(),'Vendor
			// Contact Detail')]

			WebElement Shift_Customer_Contact_Detail = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Shift_Customer_Contact_Detail"))));
			Thread.sleep(2000);
			Shift_Customer_Contact_Detail.click();

			// Billing Address

			// //*[@id='billingAddress43contactDetailVendorTab']

			WebElement Customr_Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customr_Address"))));
			Customr_Address.sendKeys("Commerzone");
			Thread.sleep(2000);

			// Billing Address
			// //*[@id='billingStateCombo43contactDetailVendorTab']

			// //*[@id='x-form-el-billingState40contactDetailCustomerTab']/input

			WebElement Customer_State_Export = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_State_Export"))));
			Customer_State_Export.sendKeys("England");
			Thread.sleep(2000);

			// //*[@class='x-layer x-combo-list
			// ']/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Maharashtra')]

			// Copy Address
			// //*[@name='copyadress']

			WebElement Copy_Customer_Address = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_Address"))));
			Thread.sleep(2000);
			Copy_Customer_Address.click();

			// Save Address details //Vendor Contact Detail
			// (//*[@class='x-panel-bbar x-panel-bbar-noborder'])
			// [2]/div/table/tbody/tr/td/table/tbody/tr/td[*]/em[1]/button[contains(text(),'Save')]

			WebElement Customer_Contact_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Contact_Detail_Save"))));
			Thread.sleep(2000);
			Customer_Contact_Detail_Save.click();

			// Information save

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]

			WebElement Custo_Information_OK = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_Information_OK"))));
			Thread.sleep(2000);
			Custo_Information_OK.click();

			// Close vendor panel

			//// *[@id='as__mainVendorPanel']/a[1]

			WebElement Close_Customer_Form = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Customer_Form"))));
			Thread.sleep(2000);
			Close_Customer_Form.click();

			System.out.println("Customer Created Successfully :- " + Customer_Code);

			System.out.println("********************************************************************");

			driver.navigate().refresh();
			Thread.sleep(2000);

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Customer_Code;

	}

	public static String copyCustomer_Export_WOPAY(WebDriver driver, String Customer_Code)
			throws InterruptedException, AWTException, IOException

	{
		// Customer_Code = "CC00009";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		WebDriverWait wait = new WebDriverWait(driver, 30);

		Thread.sleep(5000);
		WebElement Customer_Management = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Management"))));
		Customer_Management.click();
		Thread.sleep(5000);

		WebElement Search_Customer = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Customer"))));
		Search_Customer.sendKeys(Customer_Code);
		Thread.sleep(5000);

		WebElement Customer_Select = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Select"))));
		Customer_Select.click();
		Thread.sleep(2000);

		WebElement Manage_Customer = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Manage_Customer"))));
		Manage_Customer.click();
		Thread.sleep(2000);

		WebElement Copy_Customer = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer"))));
		Copy_Customer.click();
		Thread.sleep(2000);

		if (driver instanceof JavascriptExecutor)

		{
			Customer_Code = (String) ((JavascriptExecutor) driver)
					.executeScript("return document.getElementById('acccode41copy-personalDetailCustomerTab').value");

			Thread.sleep(2000);

			WebElement Customer_Name_Copy = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("accname41copy-personalDetailCustomerTab")));

			Customer_Name_Copy.clear();

			Customer_Name_Copy.sendKeys(Customer_Code);
			Thread.sleep(2000);

		}

		WebElement Uncheck_Sales_Person = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Uncheck_Sales_Person"))));
		Uncheck_Sales_Person.click();
		Thread.sleep(2000);

		WebElement Customer_Type_dropdown_SEZ_WOAY = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Customer_Type_dropdown_SEZ_WOAY"))));
		Thread.sleep(2000);
		Customer_Type_dropdown_SEZ_WOAY.click();

		WebElement Select_Customer_Type_Export_WOPAY = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_Type_Export_WOPAY"))));
		Thread.sleep(2000);
		Select_Customer_Type_Export_WOPAY.click();

		WebElement Copy_Customer_Personal_Detail_Save = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_Personal_Detail_Save"))));
		Thread.sleep(2000);
		Copy_Customer_Personal_Detail_Save.click();

		// Confirm Yes

		//// *[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Yes')]
		//// <--- General code only change text

		WebElement Confirm_Yes = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
		Thread.sleep(2000);
		Confirm_Yes.click();

		// Success Ok

		// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Ok')]

		WebElement Success_OK = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
		Thread.sleep(2000);
		Success_OK.click();

		// Shift Vendor Contact Detail

		// //*[@id='mainVendorPanel__contactDetailVendorTab']/a[*]/em/span[*]/span[contains(text(),'Vendor
		// Contact Detail')]

		WebElement Copy_Shift_Customer_Contact_Detail = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Shift_Customer_Contact_Detail"))));
		Thread.sleep(2000);
		Copy_Shift_Customer_Contact_Detail.click();

		WebElement Copy_Address = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Address"))));
		Thread.sleep(2000);
		Copy_Address.click();

		// Save Address details //Vendor Contact Detail
		// (//*[@class='x-panel-bbar x-panel-bbar-noborder'])
		// [2]/div/table/tbody/tr/td/table/tbody/tr/td[*]/em[1]/button[contains(text(),'Save')]

		WebElement Copy_Customer_Contact_Detail_Save = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Customer_Contact_Detail_Save"))));
		Thread.sleep(2000);
		Copy_Customer_Contact_Detail_Save.click();

		// Information save

		// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]

		// WebElement Information_OK =
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]")));
		// Thread.sleep(2000);
		// Information_OK.click();

		// Close vendor panel

		//// *[@id='as__mainVendorPanel']/a[1]

		WebElement Close_Copy_Customer_Form = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Copy_Customer_Form"))));

		Thread.sleep(1000);

		Close_Copy_Customer_Form.click();

		Thread.sleep(2000);

		// as__mainVendorDetails

		WebElement Close_Accounts_Receivables = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Accounts_Receivables"))));
		Thread.sleep(2000);
		Close_Accounts_Receivables.click();

		System.out.println("Customer Created Successfully :- " + Customer_Code);

		System.out.println("********************************************************************");

		return Customer_Code;

	}

}
