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

public class GST_Vendor_Master

{

	public static String createVendor_UnRegistered_Import(WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{
		String Vendor_Code = "";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			WebDriverWait wait = new WebDriverWait(driver, 30);

			Thread.sleep(1000);

			WebElement VendorMasterModule = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("VendorMasterModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			VendorMasterModule.click();
			// (//*[@class='x-form-field-wrap '] [1])/img

			WebElement Seq_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Seq_dropdown"))));
			Thread.sleep(1000);
			Seq_dropdown.click();
			Thread.sleep(1000);

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			WebElement Select_Vendor_Seq_Format = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Vendor_Seq_Format")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Select_Vendor_Seq_Format.click();
			Thread.sleep(1000);

			// To get value from disabled field

			if (driver instanceof JavascriptExecutor)

			{
				Vendor_Code = (String) ((JavascriptExecutor) driver)
						.executeScript("return document.getElementById('acccode42personalDetailVendorTab').value");

				WebElement Vendor_Name = wait
						.until(ExpectedConditions.elementToBeClickable(By.id(GST_pro.getProperty("Vendor_Name"))));

				Vendor_Name.click();

				Thread.sleep(1000);

				Vendor_Name.sendKeys(Vendor_Code);

			}

			//// *[@id='venodorAvailableToAgentCheck42personalDetailVendorTab']

			WebElement Agent_Unselect = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Agent_Unselect"))));
			Thread.sleep(1000);
			Agent_Unselect.click();
			Thread.sleep(1000);

			// //*[@id='creditTerm42personalDetailVendorTab']/following-sibling::img[1]

			WebElement Debit_term__dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Debit_term__dropdown"))));
			Thread.sleep(1000);
			Debit_term__dropdown.click();

			WebElement Select_Debit_Term = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Debit_Term"))));
			Thread.sleep(1000);
			Select_Debit_Term.click();
			Thread.sleep(1000);

			WebElement Credit_Purchase_Limit = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Purchase_Limit"))));
			Credit_Purchase_Limit.sendKeys("10000");
			Thread.sleep(1000);

			WebElement GST_Registration_Type_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("GST_Registration_Type_dropdown"))));
			Thread.sleep(1000);
			GST_Registration_Type_dropdown.click();

			WebElement Select_GST_Registration_Type_Unregistered = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_GST_Registration_Type_Unregistered"))));
			Thread.sleep(1000);
			Select_GST_Registration_Type_Unregistered.click();

			WebElement Vendor_Type_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Type_dropdown"))));
			Thread.sleep(1000);
			Vendor_Type_dropdown.click();

			WebElement Select_Vendor_Type_Import = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Vendor_Type_Import"))));
			Thread.sleep(1000);
			Select_Vendor_Type_Import.click();

			// Entity
			// //*[@id='Custom_Entity']/following-sibling::img[1]

			WebElement Entity_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_dropdown"))));
			Thread.sleep(1000);
			Entity_dropdown.click();

			// --> Pune //*[@class='x-layer x-combo-list
			// ']/div[1]/div[*]/table/tbody/tr/td[contains(text(),'Pune')]

			WebElement Select_Entity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Entity"))));
			Thread.sleep(1000);
			Select_Entity.click();

			// Vendor Personal Detail Save

			// //*[@id='savepersonalDetailVendorTab']/tbody/tr/td[*]/em/button

			WebElement Vendor_Personal_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Personal_Detail_Save"))));
			Thread.sleep(1000);
			Vendor_Personal_Detail_Save.click();

			// Confirm Yes

			//// *[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Yes')]
			//// <--- General code only change text

			WebElement Confirm_Yes = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
			Thread.sleep(1000);
			Confirm_Yes.click();

			// Success Ok

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Ok')]

			WebElement Success_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
			Thread.sleep(1000);
			Success_OK.click();

			// Shift Vendor Contact Detail

			// //*[@id='mainVendorPanel__contactDetailVendorTab']/a[*]/em/span[*]/span[contains(text(),'Vendor
			// Contact Detail')]

			WebElement Shift_Vendor_Contact_Detail = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Shift_Vendor_Contact_Detail"))));
			Thread.sleep(1000);
			Shift_Vendor_Contact_Detail.click();

			// Billing Address

			// //*[@id='billingAddress43contactDetailVendorTab']

			WebElement Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Address"))));
			Address.sendKeys("Commerzone");
			Thread.sleep(1000);

			// Billing Address
			// //*[@id='billingStateCombo43contactDetailVendorTab']

			WebElement Import_State = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Import_State"))));
			Import_State.sendKeys("USA");
			Thread.sleep(1000);

			// Copy Address
			// //*[@name='copyadress']

			WebElement Copy_Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Address"))));
			Thread.sleep(1000);
			Copy_Address.click();

			// Save Address details //Vendor Contact Detail
			// (//*[@class='x-panel-bbar x-panel-bbar-noborder'])
			// [2]/div/table/tbody/tr/td/table/tbody/tr/td[*]/em[1]/button[contains(text(),'Save')]

			WebElement Vendor_Contact_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Contact_Detail_Save"))));
			Thread.sleep(1000);
			Vendor_Contact_Detail_Save.click();

			// Information save

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]

			WebElement Information_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Information_OK"))));
			Thread.sleep(1000);
			Information_OK.click();

			// Close vendor panel

			//// *[@id='as__mainVendorPanel']/a[1]

			WebElement Close_Vendor_Form = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Vendor_Form"))));
			Thread.sleep(1000);
			Close_Vendor_Form.click();

			System.out.println("Import type Vendor Created Successfully :- " + Vendor_Code);

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Vendor_Code;

	}

	public static String createVendor_Registered_IntraState(WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{
		String Vendor_Code = "";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			WebDriverWait wait = new WebDriverWait(driver, 30);

			Thread.sleep(1000);

			WebElement VendorMasterModule = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("VendorMasterModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			VendorMasterModule.click();
			// (//*[@class='x-form-field-wrap '] [1])/img

			WebElement Seq_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Seq_dropdown"))));
			Thread.sleep(1000);
			Seq_dropdown.click();
			Thread.sleep(1000);

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			WebElement Select_Vendor_Seq_Format = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Vendor_Seq_Format")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Select_Vendor_Seq_Format.click();
			Thread.sleep(1000);

			// To get value from disabled field

			if (driver instanceof JavascriptExecutor)

			{

				Vendor_Code = (String) ((JavascriptExecutor) driver)
						.executeScript("return document.getElementById('acccode42personalDetailVendorTab').value");

				WebElement Vendor_Name = wait
						.until(ExpectedConditions.elementToBeClickable(By.id(GST_pro.getProperty("Vendor_Name"))));

				Vendor_Name.click();

				Thread.sleep(1000);

				Vendor_Name.sendKeys(Vendor_Code);

			}

			//// *[@id='venodorAvailableToAgentCheck42personalDetailVendorTab']

			WebElement Agent_Unselect = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Agent_Unselect"))));
			Thread.sleep(1000);
			Agent_Unselect.click();
			Thread.sleep(1000);

			// //*[@id='creditTerm42personalDetailVendorTab']/following-sibling::img[1]

			WebElement Debit_term__dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Debit_term__dropdown"))));
			Thread.sleep(1000);
			Debit_term__dropdown.click();

			WebElement Select_Debit_Term = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Debit_Term"))));
			Thread.sleep(1000);
			Select_Debit_Term.click();
			Thread.sleep(1000);

			WebElement Credit_Purchase_Limit = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Purchase_Limit"))));
			Credit_Purchase_Limit.sendKeys("10000");
			Thread.sleep(1000);

			WebElement GST_Registration_Type_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("GST_Registration_Type_dropdown"))));
			Thread.sleep(1000);
			GST_Registration_Type_dropdown.click();

			WebElement Select_Custo_GST_Registration_Type = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Custo_GST_Registration_Type"))));
			Thread.sleep(1000);
			Select_Custo_GST_Registration_Type.click();

			// GSTIN Input

			WebElement Custo_GSTIN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_GSTIN"))));
			Thread.sleep(1000);
			Custo_GSTIN.sendKeys("24AAACB4599E1ZR");
			Thread.sleep(1000);

			WebElement Vendor_Type_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Type_dropdown"))));
			Thread.sleep(1000);
			Vendor_Type_dropdown.click();

			WebElement Select_Customer_Type = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_Type"))));
			Thread.sleep(1000);
			Select_Customer_Type.click();

			// Entity
			// //*[@id='Custom_Entity']/following-sibling::img[1]

			WebElement Entity_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_dropdown"))));
			Thread.sleep(1000);
			Entity_dropdown.click();

			// --> Pune //*[@class='x-layer x-combo-list
			// ']/div[1]/div[*]/table/tbody/tr/td[contains(text(),'Pune')]

			WebElement Select_Entity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Entity"))));
			Thread.sleep(1000);
			Select_Entity.click();

			// Vendor Personal Detail Save

			// //*[@id='savepersonalDetailVendorTab']/tbody/tr/td[*]/em/button

			WebElement Vendor_Personal_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Personal_Detail_Save"))));
			Thread.sleep(1000);
			Vendor_Personal_Detail_Save.click();

			// Confirm Yes

			//// *[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Yes')]
			//// <--- General code only change text

			WebElement Confirm_Yes = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
			Thread.sleep(1000);
			Confirm_Yes.click();

			// Success Ok

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Ok')]

			WebElement Success_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
			Thread.sleep(1000);
			Success_OK.click();

			// Shift Vendor Contact Detail

			// //*[@id='mainVendorPanel__contactDetailVendorTab']/a[*]/em/span[*]/span[contains(text(),'Vendor
			// Contact Detail')]

			WebElement Shift_Vendor_Contact_Detail = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Shift_Vendor_Contact_Detail"))));
			Thread.sleep(1000);
			Shift_Vendor_Contact_Detail.click();

			// Billing Address

			WebElement Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Address"))));
			Address.sendKeys("Commerzone");
			Thread.sleep(1000);

			// Billing Address

			WebElement State = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("State"))));
			State.sendKeys("a");
			Thread.sleep(1000);

			// //*[@class='x-layer x-combo-list
			// ']/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Maharashtra')]

			WebElement Select_State = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_State"))));
			Thread.sleep(1000);
			Select_State.click();

			// Copy Address
			// //*[@name='copyadress']

			WebElement Copy_Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Address"))));
			Thread.sleep(1000);
			Copy_Address.click();

			// Save Address details //Vendor Contact Detail
			// (//*[@class='x-panel-bbar x-panel-bbar-noborder'])
			// [2]/div/table/tbody/tr/td/table/tbody/tr/td[*]/em[1]/button[contains(text(),'Save')]

			WebElement Vendor_Contact_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Contact_Detail_Save"))));
			Thread.sleep(1000);
			Vendor_Contact_Detail_Save.click();

			// Information save

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]

			WebElement Information_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Information_OK"))));
			Thread.sleep(1000);
			Information_OK.click();

			// Close vendor panel

			//// *[@id='as__mainVendorPanel']/a[1]

			WebElement Close_Vendor_Form = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Vendor_Form"))));
			Thread.sleep(1000);
			Close_Vendor_Form.click();

			System.out.println("Registered type Vendor Created Successfully :- " + Vendor_Code);

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Vendor_Code;

	}

	public static String copyVendor_Registered_InterState(WebDriver driver, String Vendor_Code)
			throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			WebDriverWait wait = new WebDriverWait(driver, 30);

			Thread.sleep(1000);

			WebElement Vendor_Management = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Management"))));
			Vendor_Management.click();
			Thread.sleep(3000);

			WebElement Search_Vendor = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Vendor"))));
			Search_Vendor.sendKeys(Vendor_Code);
			Thread.sleep(1500);

			WebElement Refresh_Button = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Refresh_Button"))));
			Refresh_Button.click();
			Thread.sleep(1500);

			WebElement Select = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select"))));
			Select.click();
			Thread.sleep(1000);

			WebElement Manage_Vendor = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Manage_Vendor"))));
			Manage_Vendor.click();
			Thread.sleep(1000);

			WebElement Copy_Vendor = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Vendor"))));
			Copy_Vendor.click();
			Thread.sleep(1000);

			if (driver instanceof JavascriptExecutor)

			{
				Vendor_Code = (String) ((JavascriptExecutor) driver)
						.executeScript("return document.getElementById('acccode42copy-personalDetailVendorTab').value");

				Thread.sleep(1000);

				// System.out.println(Vendor_Code);

				// accname42copy-personalDetailVendorTab

				WebElement Vendor_Name_Copy = wait
						.until(ExpectedConditions.elementToBeClickable(By.id("accname42copy-personalDetailVendorTab")));
				Vendor_Name_Copy.clear();

				Vendor_Name_Copy.sendKeys(Vendor_Code);

				Thread.sleep(1000);

			}

			WebElement GSTIN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTIN"))));
			Thread.sleep(1000);
			GSTIN.sendKeys("24AAACB4599E1ZR");
			Thread.sleep(1000);

			WebElement Copy_Vendor_Personal_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Vendor_Personal_Detail_Save"))));
			Thread.sleep(1000);
			Copy_Vendor_Personal_Detail_Save.click();

			// Confirm Yes

			WebElement Confirm_Yes = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
			Thread.sleep(1000);
			Confirm_Yes.click();

			// Success Ok

			WebElement Success_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
			Thread.sleep(1000);
			Success_OK.click();

			// Shift Vendor Contact Detail

			WebElement Copy_Shift_Vendor_Contact_Detail = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Shift_Vendor_Contact_Detail"))));
			Thread.sleep(1000);
			Copy_Shift_Vendor_Contact_Detail.click();

			// Billing Address

			WebElement Copy_State = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_State"))));
			Copy_State.clear();
			Thread.sleep(1000);
			Copy_State.sendKeys("a");
			Thread.sleep(1000);

			WebElement Select_State1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_State1"))));
			Thread.sleep(1000);
			Select_State1.click();

			WebElement Copy_Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Address"))));
			Thread.sleep(1000);
			Copy_Address.click();

			// Save Address details //Vendor Contact Detail

			WebElement Copy_Vendor_Contact_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Vendor_Contact_Detail_Save"))));
			Thread.sleep(1000);
			Copy_Vendor_Contact_Detail_Save.click();

			// Information save

			// Close vendor panel
			WebElement Close_Copy_Vendor_Form = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Copy_Vendor_Form"))));
			Thread.sleep(1000);
			Close_Copy_Vendor_Form.click();
			Thread.sleep(1000);

			// as__mainVendorDetails

			WebElement Close_Accounts_Payable = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='as__mainVendorDetails']/a[1]")));
			Thread.sleep(1000);
			Close_Accounts_Payable.click();

			System.out.println("Registered type Vendor Created Successfully :- " + Vendor_Code);

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Vendor_Code;

	}

	public static String createVendor_Registered_SEZ_WPAY_IntraState(WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{
		String Vendor_Code = "";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			WebDriverWait wait = new WebDriverWait(driver, 30);

			Thread.sleep(1000);

			WebElement VendorMasterModule = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("VendorMasterModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			VendorMasterModule.click();
			// (//*[@class='x-form-field-wrap '] [1])/img

			WebElement Seq_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Seq_dropdown"))));
			Thread.sleep(1000);
			Seq_dropdown.click();
			Thread.sleep(1000);

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			WebElement Select_Vendor_Seq_Format = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Vendor_Seq_Format")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Select_Vendor_Seq_Format.click();
			Thread.sleep(1000);

			// To get value from disabled field

			if (driver instanceof JavascriptExecutor)

			{

				Vendor_Code = (String) ((JavascriptExecutor) driver)
						.executeScript("return document.getElementById('acccode42personalDetailVendorTab').value");

				WebElement Vendor_Name = wait
						.until(ExpectedConditions.elementToBeClickable(By.id(GST_pro.getProperty("Vendor_Name"))));

				Vendor_Name.click();

				Thread.sleep(1000);

				Vendor_Name.sendKeys(Vendor_Code);

			}

			//// *[@id='venodorAvailableToAgentCheck42personalDetailVendorTab']

			WebElement Agent_Unselect = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Agent_Unselect"))));
			Thread.sleep(1000);
			Agent_Unselect.click();
			Thread.sleep(1000);

			// //*[@id='creditTerm42personalDetailVendorTab']/following-sibling::img[1]

			WebElement Debit_term__dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Debit_term__dropdown"))));
			Thread.sleep(1000);
			Debit_term__dropdown.click();

			WebElement Select_Debit_Term = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Debit_Term"))));
			Thread.sleep(1000);
			Select_Debit_Term.click();
			Thread.sleep(1000);

			WebElement Credit_Purchase_Limit = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Purchase_Limit"))));
			Credit_Purchase_Limit.sendKeys("10000");
			Thread.sleep(1000);

			WebElement GST_Registration_Type_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("GST_Registration_Type_dropdown"))));
			Thread.sleep(1000);
			GST_Registration_Type_dropdown.click();

			WebElement Select_Custo_GST_Registration_Type = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Custo_GST_Registration_Type"))));
			Thread.sleep(1000);
			Select_Custo_GST_Registration_Type.click();

			// GSTIN Input

			WebElement Custo_GSTIN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_GSTIN"))));
			Thread.sleep(1000);
			Custo_GSTIN.sendKeys("24AAACB4599E1ZR");
			Thread.sleep(1000);

			WebElement Vendor_Type_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Type_dropdown"))));
			Thread.sleep(1000);
			Vendor_Type_dropdown.click();

			WebElement Select_Customer_Type_SEZ_WPAY = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_Type_SEZ_WPAY"))));
			Thread.sleep(1000);
			Select_Customer_Type_SEZ_WPAY.click();

			// Entity
			// //*[@id='Custom_Entity']/following-sibling::img[1]

			WebElement Entity_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_dropdown"))));
			Thread.sleep(1000);
			Entity_dropdown.click();

			// --> Pune //*[@class='x-layer x-combo-list
			// ']/div[1]/div[*]/table/tbody/tr/td[contains(text(),'Pune')]

			WebElement Select_Entity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Entity"))));
			Thread.sleep(1000);
			Select_Entity.click();

			// Vendor Personal Detail Save

			// //*[@id='savepersonalDetailVendorTab']/tbody/tr/td[*]/em/button

			WebElement Vendor_Personal_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Personal_Detail_Save"))));
			Thread.sleep(1000);
			Vendor_Personal_Detail_Save.click();

			// Confirm Yes

			//// *[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Yes')]
			//// <--- General code only change text

			WebElement Confirm_Yes = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
			Thread.sleep(1000);
			Confirm_Yes.click();

			// Success Ok

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Ok')]

			WebElement Success_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
			Thread.sleep(1000);
			Success_OK.click();

			// Shift Vendor Contact Detail

			// //*[@id='mainVendorPanel__contactDetailVendorTab']/a[*]/em/span[*]/span[contains(text(),'Vendor
			// Contact Detail')]

			WebElement Shift_Vendor_Contact_Detail = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Shift_Vendor_Contact_Detail"))));
			Thread.sleep(1000);
			Shift_Vendor_Contact_Detail.click();

			// Billing Address

			WebElement Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Address"))));
			Address.sendKeys("Commerzone");
			Thread.sleep(1000);

			// Billing Address

			WebElement State = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("State"))));
			State.sendKeys("a");
			Thread.sleep(1000);

			// //*[@class='x-layer x-combo-list
			// ']/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Maharashtra')]

			WebElement Select_State = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_State"))));
			Thread.sleep(1000);
			Select_State.click();

			// Copy Address
			// //*[@name='copyadress']

			WebElement Copy_Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Address"))));
			Thread.sleep(1000);
			Copy_Address.click();

			// Save Address details //Vendor Contact Detail
			// (//*[@class='x-panel-bbar x-panel-bbar-noborder'])
			// [2]/div/table/tbody/tr/td/table/tbody/tr/td[*]/em[1]/button[contains(text(),'Save')]

			WebElement Vendor_Contact_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Contact_Detail_Save"))));
			Thread.sleep(1000);
			Vendor_Contact_Detail_Save.click();

			// Information save

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]

			WebElement Information_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Information_OK"))));
			Thread.sleep(1000);
			Information_OK.click();

			// Close vendor panel

			//// *[@id='as__mainVendorPanel']/a[1]

			WebElement Close_Vendor_Form = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Vendor_Form"))));
			Thread.sleep(1000);
			Close_Vendor_Form.click();

			System.out.println("SEZ(WPAY)  IntraState type Vendor Created Successfully :- " + Vendor_Code);

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Vendor_Code;

	}

	public static String copyVendor_Registered_SEZ_WPAY_InterState(WebDriver driver, String Vendor_Code)
			throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			WebDriverWait wait = new WebDriverWait(driver, 30);

			Thread.sleep(1000);

			WebElement Vendor_Management = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Management"))));
			Vendor_Management.click();
			Thread.sleep(3000);

			WebElement Search_Vendor = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Vendor"))));
			Search_Vendor.sendKeys(Vendor_Code);
			Thread.sleep(1500);

			WebElement Refresh_Button = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Refresh_Button"))));
			Refresh_Button.click();
			Thread.sleep(1500);

			WebElement Select = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select"))));
			Select.click();
			Thread.sleep(1000);

			WebElement Manage_Vendor = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Manage_Vendor"))));
			Manage_Vendor.click();
			Thread.sleep(1000);

			WebElement SEZ_Copy_Vendor = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Vendor"))));
			SEZ_Copy_Vendor.click();
			Thread.sleep(1000);

			if (driver instanceof JavascriptExecutor)

			{
				Vendor_Code = (String) ((JavascriptExecutor) driver)
						.executeScript("return document.getElementById('acccode42copy-personalDetailVendorTab').value");

				Thread.sleep(1000);

				// System.out.println(Vendor_Code);

				// accname42copy-personalDetailVendorTab

				WebElement Vendor_Name_Copy = wait
						.until(ExpectedConditions.elementToBeClickable(By.id("accname42copy-personalDetailVendorTab")));
				Vendor_Name_Copy.clear();

				Vendor_Name_Copy.sendKeys(Vendor_Code);

				Thread.sleep(1000);

			}

			WebElement GSTIN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTIN"))));
			Thread.sleep(1000);
			GSTIN.sendKeys("24AAACB4599E1ZR");
			Thread.sleep(1000);

			WebElement Copy_Vendor_Personal_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Vendor_Personal_Detail_Save"))));
			Thread.sleep(1000);
			Copy_Vendor_Personal_Detail_Save.click();

			// Confirm Yes

			WebElement Confirm_Yes = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
			Thread.sleep(1000);
			Confirm_Yes.click();

			// Success Ok

			WebElement Success_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
			Thread.sleep(1000);
			Success_OK.click();

			// Shift Vendor Contact Detail

			WebElement Copy_Shift_Vendor_Contact_Detail = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Shift_Vendor_Contact_Detail"))));
			Thread.sleep(1000);
			Copy_Shift_Vendor_Contact_Detail.click();

			// Billing Address

			WebElement Copy_State = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_State"))));
			Copy_State.clear();
			Thread.sleep(1000);
			Copy_State.sendKeys("a");
			Thread.sleep(1000);

			WebElement Select_State1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_State1"))));
			Thread.sleep(1000);
			Select_State1.click();

			WebElement Copy_Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Address"))));
			Thread.sleep(1000);
			Copy_Address.click();

			// Save Address details //Vendor Contact Detail

			WebElement Copy_Vendor_Contact_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Vendor_Contact_Detail_Save"))));
			Thread.sleep(1000);
			Copy_Vendor_Contact_Detail_Save.click();

			// Information save

			// Close vendor panel
			WebElement Close_Copy_Vendor_Form = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Copy_Vendor_Form"))));
			Thread.sleep(1000);
			Close_Copy_Vendor_Form.click();
			Thread.sleep(1000);

			// as__mainVendorDetails

			WebElement Close_Accounts_Payable = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='as__mainVendorDetails']/a[1]")));
			Thread.sleep(1000);
			Close_Accounts_Payable.click();

			System.out.println("SEZ(WPAY) InterState type Vendor Created Successfully :- " + Vendor_Code);

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Vendor_Code;

	}

	public static String createVendor_Composition_IntraState(WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{
		String Vendor_Code = "";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			WebDriverWait wait = new WebDriverWait(driver, 30);

			Thread.sleep(1000);

			WebElement VendorMasterModule = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("VendorMasterModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			VendorMasterModule.click();
			// (//*[@class='x-form-field-wrap '] [1])/img

			WebElement Seq_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Seq_dropdown"))));
			Thread.sleep(1000);
			Seq_dropdown.click();
			Thread.sleep(1000);

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			WebElement Select_Vendor_Seq_Format = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Vendor_Seq_Format")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Select_Vendor_Seq_Format.click();
			Thread.sleep(1000);

			// To get value from disabled field

			if (driver instanceof JavascriptExecutor)

			{

				Vendor_Code = (String) ((JavascriptExecutor) driver)
						.executeScript("return document.getElementById('acccode42personalDetailVendorTab').value");

				WebElement Vendor_Name = wait
						.until(ExpectedConditions.elementToBeClickable(By.id(GST_pro.getProperty("Vendor_Name"))));

				Vendor_Name.click();

				Thread.sleep(1000);

				Vendor_Name.sendKeys(Vendor_Code);

			}

			//// *[@id='venodorAvailableToAgentCheck42personalDetailVendorTab']

			WebElement Agent_Unselect = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Agent_Unselect"))));
			Thread.sleep(1000);
			Agent_Unselect.click();
			Thread.sleep(1000);

			// //*[@id='creditTerm42personalDetailVendorTab']/following-sibling::img[1]

			WebElement Debit_term__dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Debit_term__dropdown"))));
			Thread.sleep(1000);
			Debit_term__dropdown.click();

			WebElement Select_Debit_Term = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Debit_Term"))));
			Thread.sleep(1000);
			Select_Debit_Term.click();
			Thread.sleep(1000);

			WebElement Credit_Purchase_Limit = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Purchase_Limit"))));
			Credit_Purchase_Limit.sendKeys("10000");
			Thread.sleep(1000);

			WebElement GST_Registration_Type_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("GST_Registration_Type_dropdown"))));
			Thread.sleep(1000);
			GST_Registration_Type_dropdown.click();

			WebElement Select_Custo_GST_Registration_Type = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath(GST_pro.getProperty("Select_Custo_GST_Registration_Type_Composition"))));
			Thread.sleep(1000);
			Select_Custo_GST_Registration_Type.click();

			// GSTIN Input

			WebElement Custo_GSTIN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_GSTIN"))));
			Thread.sleep(1000);
			Custo_GSTIN.sendKeys("24AAACB4599E1ZR");
			Thread.sleep(1000);

			WebElement Vendor_Type_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Type_dropdown"))));
			Thread.sleep(1000);
			Vendor_Type_dropdown.click();

			WebElement Select_Customer_Type = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_Type"))));
			Thread.sleep(1000);
			Select_Customer_Type.click();

			// Entity
			// //*[@id='Custom_Entity']/following-sibling::img[1]

			WebElement Entity_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_dropdown"))));
			Thread.sleep(1000);
			Entity_dropdown.click();

			// --> Pune //*[@class='x-layer x-combo-list
			// ']/div[1]/div[*]/table/tbody/tr/td[contains(text(),'Pune')]

			WebElement Select_Entity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Entity"))));
			Thread.sleep(1000);
			Select_Entity.click();

			// Vendor Personal Detail Save

			// //*[@id='savepersonalDetailVendorTab']/tbody/tr/td[*]/em/button

			WebElement Vendor_Personal_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Personal_Detail_Save"))));
			Thread.sleep(1000);
			Vendor_Personal_Detail_Save.click();

			// Confirm Yes

			//// *[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Yes')]
			//// <--- General code only change text

			WebElement Confirm_Yes = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
			Thread.sleep(1000);
			Confirm_Yes.click();

			// Success Ok

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Ok')]

			WebElement Success_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
			Thread.sleep(1000);
			Success_OK.click();

			// Shift Vendor Contact Detail

			// //*[@id='mainVendorPanel__contactDetailVendorTab']/a[*]/em/span[*]/span[contains(text(),'Vendor
			// Contact Detail')]

			WebElement Shift_Vendor_Contact_Detail = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Shift_Vendor_Contact_Detail"))));
			Thread.sleep(1000);
			Shift_Vendor_Contact_Detail.click();

			// Billing Address

			WebElement Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Address"))));
			Address.sendKeys("Commerzone");
			Thread.sleep(1000);

			// Billing Address

			WebElement State = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("State"))));
			State.sendKeys("a");
			Thread.sleep(1000);

			// //*[@class='x-layer x-combo-list
			// ']/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Maharashtra')]

			WebElement Select_State = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_State"))));
			Thread.sleep(1000);
			Select_State.click();

			// Copy Address
			// //*[@name='copyadress']

			WebElement Copy_Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Address"))));
			Thread.sleep(1000);
			Copy_Address.click();

			// Save Address details //Vendor Contact Detail
			// (//*[@class='x-panel-bbar x-panel-bbar-noborder'])
			// [2]/div/table/tbody/tr/td/table/tbody/tr/td[*]/em[1]/button[contains(text(),'Save')]

			WebElement Vendor_Contact_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Contact_Detail_Save"))));
			Thread.sleep(1000);
			Vendor_Contact_Detail_Save.click();

			// Information save

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]

			WebElement Information_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Information_OK"))));
			Thread.sleep(1000);
			Information_OK.click();

			// Close vendor panel

			//// *[@id='as__mainVendorPanel']/a[1]

			WebElement Close_Vendor_Form = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Vendor_Form"))));
			Thread.sleep(1000);
			Close_Vendor_Form.click();

			System.out.println("Registered type Vendor Created Successfully :- " + Vendor_Code);

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Vendor_Code;

	}

	public static String copyVendor_Composition_InterState(WebDriver driver, String Vendor_Code)
			throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			WebDriverWait wait = new WebDriverWait(driver, 30);

			Thread.sleep(1000);

			WebElement Vendor_Management = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Management"))));
			Vendor_Management.click();
			Thread.sleep(3000);

			WebElement Search_Vendor = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Vendor"))));
			Search_Vendor.sendKeys(Vendor_Code);
			Thread.sleep(1500);

			WebElement Refresh_Button = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Refresh_Button"))));
			Refresh_Button.click();
			Thread.sleep(1500);

			WebElement Select = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select"))));
			Select.click();
			Thread.sleep(1000);

			WebElement Manage_Vendor = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Manage_Vendor"))));
			Manage_Vendor.click();
			Thread.sleep(1000);

			WebElement Copy_Vendor = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Vendor"))));
			Copy_Vendor.click();
			Thread.sleep(1000);

			if (driver instanceof JavascriptExecutor)

			{
				Vendor_Code = (String) ((JavascriptExecutor) driver)
						.executeScript("return document.getElementById('acccode42copy-personalDetailVendorTab').value");

				Thread.sleep(1000);

				// System.out.println(Vendor_Code);

				// accname42copy-personalDetailVendorTab

				WebElement Vendor_Name_Copy = wait
						.until(ExpectedConditions.elementToBeClickable(By.id("accname42copy-personalDetailVendorTab")));
				Vendor_Name_Copy.clear();

				Vendor_Name_Copy.sendKeys(Vendor_Code);

				Thread.sleep(1000);

			}

			WebElement GSTIN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GSTIN"))));
			Thread.sleep(1000);
			GSTIN.sendKeys("24AAACB4599E1ZR");
			Thread.sleep(1000);

			WebElement Copy_Vendor_Personal_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Vendor_Personal_Detail_Save"))));
			Thread.sleep(1000);
			Copy_Vendor_Personal_Detail_Save.click();

			// Confirm Yes

			WebElement Confirm_Yes = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
			Thread.sleep(1000);
			Confirm_Yes.click();

			// Success Ok

			WebElement Success_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
			Thread.sleep(1000);
			Success_OK.click();

			// Shift Vendor Contact Detail

			WebElement Copy_Shift_Vendor_Contact_Detail = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Shift_Vendor_Contact_Detail"))));
			Thread.sleep(1000);
			Copy_Shift_Vendor_Contact_Detail.click();

			// Billing Address

			WebElement Copy_State = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_State"))));
			Copy_State.clear();
			Thread.sleep(1000);
			Copy_State.sendKeys("a");
			Thread.sleep(1000);

			WebElement Select_State1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_State1"))));
			Thread.sleep(1000);
			Select_State1.click();

			WebElement Copy_Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Address"))));
			Thread.sleep(1000);
			Copy_Address.click();

			// Save Address details //Vendor Contact Detail

			WebElement Copy_Vendor_Contact_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Vendor_Contact_Detail_Save"))));
			Thread.sleep(1000);
			Copy_Vendor_Contact_Detail_Save.click();

			// Information save

			// Close vendor panel
			WebElement Close_Copy_Vendor_Form = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Copy_Vendor_Form"))));
			Thread.sleep(1000);
			Close_Copy_Vendor_Form.click();
			Thread.sleep(1000);

			// as__mainVendorDetails

			WebElement Close_Accounts_Payable = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='as__mainVendorDetails']/a[1]")));
			Thread.sleep(1000);
			Close_Accounts_Payable.click();

			System.out.println("Registered type Vendor Created Successfully :- " + Vendor_Code);

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Vendor_Code;

	}

	public static String createVendor_Registered_IntraState_Basic_SetUp(WebDriver driver, String Entity)
			throws InterruptedException, AWTException, IOException

	{
		String Vendor_Code = "";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			WebDriverWait wait = new WebDriverWait(driver, 30);

			Thread.sleep(1000);

			WebElement VendorMasterModule = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("VendorMasterModule")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			VendorMasterModule.click();
			// (//*[@class='x-form-field-wrap '] [1])/img

			WebElement Seq_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Seq_dropdown"))));
			Thread.sleep(1000);
			Seq_dropdown.click();
			Thread.sleep(1000);

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			WebElement Select_Vendor_Seq_Format = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Vendor_Seq_Format")))); //// map[@name='AlienAreas1']//area[1]
			Thread.sleep(1000);
			Select_Vendor_Seq_Format.click();
			Thread.sleep(1000);

			// To get value from disabled field

			if (driver instanceof JavascriptExecutor)

			{

				Vendor_Code = (String) ((JavascriptExecutor) driver)
						.executeScript("return document.getElementById('acccode42personalDetailVendorTab').value");

				WebElement Vendor_Name = wait
						.until(ExpectedConditions.elementToBeClickable(By.id(GST_pro.getProperty("Vendor_Name"))));

				Vendor_Name.click();

				Thread.sleep(1000);

				Vendor_Name.sendKeys(Vendor_Code);

			}

			//// *[@id='venodorAvailableToAgentCheck42personalDetailVendorTab']

			WebElement Agent_Unselect = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Agent_Unselect"))));
			Thread.sleep(1000);
			Agent_Unselect.click();
			Thread.sleep(1000);

			// //*[@id='creditTerm42personalDetailVendorTab']/following-sibling::img[1]

			WebElement Debit_term__dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Debit_term__dropdown"))));
			Thread.sleep(1000);
			Debit_term__dropdown.click();

			WebElement Select_Debit_Term = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Debit_Term"))));
			Thread.sleep(1000);
			Select_Debit_Term.click();
			Thread.sleep(1000);

			WebElement Credit_Purchase_Limit = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Credit_Purchase_Limit"))));
			Credit_Purchase_Limit.sendKeys("10000");
			Thread.sleep(1000);

			WebElement GST_Registration_Type_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("GST_Registration_Type_dropdown"))));
			Thread.sleep(1000);
			GST_Registration_Type_dropdown.click();

			WebElement Select_Custo_GST_Registration_Type = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Custo_GST_Registration_Type"))));
			Thread.sleep(1000);
			Select_Custo_GST_Registration_Type.click();

			// GSTIN Input

			WebElement Custo_GSTIN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Custo_GSTIN"))));
			Thread.sleep(1000);
			Custo_GSTIN.sendKeys("24AAACB4599E1ZR");
			Thread.sleep(1000);

			WebElement Vendor_Type_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Type_dropdown"))));
			Thread.sleep(1000);
			Vendor_Type_dropdown.click();

			WebElement Select_Customer_Type = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Customer_Type"))));
			Thread.sleep(1000);
			Select_Customer_Type.click();

			WebElement Entity2 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//input[starts-with(@id,'Custom_Entitywtf')]")));
			Entity2.clear();
			Thread.sleep(1000);

			WebElement Entity1 = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//input[starts-with(@id,'Custom_Entitywtf')]")));
			Entity1.sendKeys(Entity);
			Thread.sleep(1000);

			WebElement Select_Entity = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[@class='x-layer x-combo-list ']/div/div[2]/table/tbody/tr/td")));
			Select_Entity.click();
			Thread.sleep(1000);

			// Vendor Personal Detail Save

			// //*[@id='savepersonalDetailVendorTab']/tbody/tr/td[*]/em/button

			WebElement Vendor_Personal_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Personal_Detail_Save"))));
			Thread.sleep(1000);
			Vendor_Personal_Detail_Save.click();

			// Confirm Yes

			//// *[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Yes')]
			//// <--- General code only change text

			WebElement Confirm_Yes = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Confirm_Yes"))));
			Thread.sleep(1000);
			Confirm_Yes.click();

			// Success Ok

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em/button[contains(text(),'Ok')]

			WebElement Success_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Success_OK"))));
			Thread.sleep(1000);
			Success_OK.click();

			// Shift Vendor Contact Detail

			// //*[@id='mainVendorPanel__contactDetailVendorTab']/a[*]/em/span[*]/span[contains(text(),'Vendor
			// Contact Detail')]

			WebElement Shift_Vendor_Contact_Detail = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Shift_Vendor_Contact_Detail"))));
			Thread.sleep(1000);
			Shift_Vendor_Contact_Detail.click();

			// Billing Address

			WebElement Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Address"))));
			Address.sendKeys("Commerzone");
			Thread.sleep(1000);

			// Billing Address

			WebElement State = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("State"))));
			State.sendKeys("a");
			Thread.sleep(1000);

			// //*[@class='x-layer x-combo-list
			// ']/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Maharashtra')]

			WebElement Select_State = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_State"))));
			Thread.sleep(1000);
			Select_State.click();

			// Copy Address
			// //*[@name='copyadress']

			WebElement Copy_Address = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Copy_Address"))));
			Thread.sleep(1000);
			Copy_Address.click();

			// Save Address details //Vendor Contact Detail
			// (//*[@class='x-panel-bbar x-panel-bbar-noborder'])
			// [2]/div/table/tbody/tr/td/table/tbody/tr/td[*]/em[1]/button[contains(text(),'Save')]

			WebElement Vendor_Contact_Detail_Save = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Vendor_Contact_Detail_Save"))));
			Thread.sleep(1000);
			Vendor_Contact_Detail_Save.click();

			// Information save

			// //*[@class='x-panel-btns-ct']/div[*]/table[*]/tbody[*]/tr[*]/td[*]/table[*]/tbody[*]/tr[*]/td[*]/em[*]/button[contains(text(),'OK')]

			WebElement Information_OK = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Information_OK"))));
			Thread.sleep(1000);
			Information_OK.click();

			// Close vendor panel

			//// *[@id='as__mainVendorPanel']/a[1]

			WebElement Close_Vendor_Form = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Close_Vendor_Form"))));
			Thread.sleep(1000);
			Close_Vendor_Form.click();

			System.out.println("Registered type Vendor Created Successfully :- " + Vendor_Code);

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Vendor_Code;

	}

}