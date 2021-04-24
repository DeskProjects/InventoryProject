package krawler.erp.IndianGST;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.ProductMaster;
import krawler.erp.page.Utilities;

public class GST_Product_Master {

	public static String createProduct_Normal(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		String Product_ID = "";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement ProductMasterModule = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ProductMasterModule"))));
			Thread.sleep(1000);
			ProductMasterModule.click();

			//// *[@id='sequenceformatproductwin']/following-sibling::img[1]

			WebElement Sequ_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sequ_dropdown"))));
			Thread.sleep(1000);
			Sequ_dropdown.click();

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			WebElement Select_Sequ_Format = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Sequ_Format"))));

			Select_Sequ_Format.click();
			Thread.sleep(1000);

			WebElement Product_Name = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Name"))));
			String Product_Name1 = "Product @ 12%";
			Product_Name.sendKeys(Product_Name1);
			Thread.sleep(1000);

			WebElement Product_Entity_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Entity_dropdown"))));
			Thread.sleep(1000);
			Product_Entity_dropdown.click();

			// --> Pune //*[@class='x-layer x-combo-list
			// ']/div[1]/div[*]/table/tbody/tr/td[contains(text(),'Pune')]

			WebElement Select_Product_Entity = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Entity"))));
			Thread.sleep(1000);
			Select_Product_Entity.click();

			// Unit Quantity code

			//// *[@name='Custom_Product Tax Class']/following-sibling::img[1]

			WebElement Product_Tax_Class_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Tax_Class_dropdown"))));
			Thread.sleep(1000);
			Product_Tax_Class_dropdown.click();

			// (//*[@class='x-layer x-combo-list '])
			// [4]/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Product
			// @ 12%')]
			WebElement Select_Product_Tax_Class = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Tax_Class"))));
			Select_Product_Tax_Class.click();
			Thread.sleep(1000);

			// HSN-SAC Code

			WebElement HSN_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("HSN_dropdown"))));
			Thread.sleep(1000);
			HSN_dropdown.click();

			WebElement Select_HSN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_HSN"))));
			Thread.sleep(1000);
			Select_HSN.click();

			WebElement Unit_Quantity_Code_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Quantity_Code_dropdown"))));
			Thread.sleep(1000);
			Unit_Quantity_Code_dropdown.click();

			// (//*[@class='x-layer x-combo-list '])
			// [8]/div/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'BAG-BAGS')]

			WebElement Select_Unit_Quantity_Code = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Unit_Quantity_Code"))));
			Thread.sleep(1000);
			Select_Unit_Quantity_Code.click();

			// Initial quantity
			// //*[@name='initialquantity']

			WebElement Initial_Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Initial_Quantity"))));
			Initial_Quantity.sendKeys("100");
			Thread.sleep(1000);

			// Click On LC

			WebElement License_Code = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("License_Code"))));
			License_Code.click();
			Thread.sleep(1000);

			try {

				WebElement Initial_quantity_alert = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Initial_quantity_alert"))));
				Initial_quantity_alert.click();
				Thread.sleep(1000);

			}

			catch (Exception noOk) {
				System.out.println("No such alert present now in product master");
			}

			// Product Warehouse
			// (//*[@id='serialdetailwindowsid'])
			// [4]/div/div/div/div/div[2]/div/div[1]/table/tbody/tr/td[2]/div

			// (//*[@class='x-window' and
			// contains(@style,'visible')]/div[2]/div/div/div/div/div[3]/div/div/div/div/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[2])
			// [1]

			WebElement Product_Initial_Warehouse = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Warehouse"))));
			Product_Initial_Warehouse.click();

			//// *[@class= ' x-form-text x-form-field x-form-focus']

			WebElement Product_Initial_Warehouse1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Warehouse1"))));
			Product_Initial_Warehouse1.sendKeys("DS - Default Store");
			Thread.sleep(1000);

			// //*[@class='x-combo-list-inner']/div[contains(text(),'DS -
			// Default Store')]

			WebElement Select_Product_Initial_Warehouse = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Initial_Warehouse"))));
			Select_Product_Initial_Warehouse.click();
			Thread.sleep(1000);

			WebElement Product_Initial_Location = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Location"))));
			Product_Initial_Location.click();

			//// *[@class= ' x-form-text x-form-field x-form-focus']

			WebElement Product_Initial_Location11 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Location11"))));
			Product_Initial_Location11.sendKeys("Default Location");
			Thread.sleep(1000);

			// //*[@class='x-combo-list-inner']/div[contains(text(),'DS -
			// Default Store')]

			WebElement Select_Product_Initial_location = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Initial_location"))));
			Select_Product_Initial_location.click();
			Thread.sleep(1000);

			WebElement Warehouse_Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Warehouse_Save"))));
			Warehouse_Save.click();
			Thread.sleep(1000);

			// General path --> (//*[@class='x-tab-panel-header
			// x-unselectable'])
			// [2]/div[*]/ul[*]/li[*]/a[*]/em[*]/span/span[contains(text(),'Purchase')]

			WebElement Product_Purchase = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Purchase"))));
			Product_Purchase.click();
			Thread.sleep(1000);

			// //*[@id='purchaseaccountidproductwin']/following-sibling::img[1]

			WebElement Product_Purchase_Account_Dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Purchase_Account_Dropdown"))));
			Product_Purchase_Account_Dropdown.click();
			Thread.sleep(1000);

			// //*[@class='x-layer x-combo-list
			// ']/div/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Purchases')]

			WebElement Select_Product_Purchase_Account = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Purchase_Account"))));
			Select_Product_Purchase_Account.click();
			Thread.sleep(1000);

			// //*[@id='initialpriceproductwin']

			WebElement Product_Initial_Purchase_Price = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Purchase_Price"))));
			Product_Initial_Purchase_Price.sendKeys("100");
			Thread.sleep(1000);

			WebElement Product_Sales = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Sales"))));
			Product_Sales.click();
			Thread.sleep(1000);

			// //*[@id='salesaccountidproductwin']/following-sibling::img[1]
			// ////*[@id='salesaccountidproductwin']

			WebElement Product_Sales_Account_Send = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Sales_Account_Send"))));
			Product_Sales_Account_Send.sendKeys("Sales");
			Thread.sleep(1000);

			// (//*[@class='x-layer x-combo-list '])
			// [1]/div/div/table/tbody/tr/td[2]

			WebElement Select_Product_Sales_Account = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Sales_Account"))));
			Select_Product_Sales_Account.click();
			Thread.sleep(1000);

			// //*[@id='initialsalespriceproductwin']

			WebElement Product_Initial_Sales_Price = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Sales_Price"))));
			Product_Initial_Sales_Price.sendKeys("200");
			Thread.sleep(1000);

			// Inventory Data

			WebElement Product_Inventory_Data = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Inventory_Data"))));
			Product_Inventory_Data.click();
			Thread.sleep(1000);

			// //*[@id='uomidproductwin']/following-sibling::img[1]

			//

			WebElement Stock_UOM_Send = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Stock_UOM_Send"))));
			Stock_UOM_Send.sendKeys("Unit");
			Thread.sleep(1000);

			WebElement Select_Stock_UOM = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Stock_UOM"))));
			Select_Stock_UOM.click();
			Thread.sleep(1000);

			// //*[@id='warehouseproductwin']

			WebElement Default_Warehouse_Send = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Default_Warehouse_Send"))));
			Default_Warehouse_Send.sendKeys("DS - Default Store");
			Thread.sleep(1000);

			WebElement Select_Default_Warehouse = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Default_Warehouse"))));
			Select_Default_Warehouse.click();
			Thread.sleep(1000);

			WebElement Default_Location_Send = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Default_Location_Send"))));
			Default_Location_Send.sendKeys("Default Location");
			Thread.sleep(1000);

			WebElement Select_Default_Location = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Default_Location"))));
			Select_Default_Location.click();
			Thread.sleep(1000);

			// Product Save

			// (//div[@class='x-tab-panel-bbar']/div/table/tbody/tr/td/table/tbody/tr/td[2]/em/button[contains(text(),'Save')])
			// [1]

			WebElement Product_Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Save"))));
			Product_Save.click();
			Thread.sleep(1000);

			// Confirm msg

			// //div[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td[2]/table/tbody/tr/td[2]/em/button

			WebElement ProductConfirm_Yes = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ProductConfirm_Yes"))));
			ProductConfirm_Yes.click();
			Thread.sleep(3000);

			// Product ok
			// //div[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td[1]/table/tbody/tr/td[2]/em/button

			/*
			 * WebElement Product_OK =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			 * GST_pro.getProperty("Product_OK")))); Product_OK.click();
			 * Thread.sleep(1000);
			 */

			// Product Close
			// //*[@id='as__productwin']/a[1]

			WebElement Product_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Close"))));
			Thread.sleep(2000);
			Product_Close.click();

			// //li[@id='as__mainProductDetails']/a[1]

			// (//tr[@class='x-grid3-hd-row'])
			// [2]/td[*]/div[contains(text(),'Product ID')]/a[1]

			WebElement menu = driver.findElement(By.xpath(GST_pro.getProperty("menu")));
			WebElement SUBMenu = driver.findElement(By.xpath(GST_pro.getProperty("SUBMenu")));

			Actions action = new Actions(driver);
			action.moveToElement(menu).perform();
			action.click(SUBMenu).perform();

			// WebElement Sorting_Click =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//tr[@class='x-grid3-hd-row'])
			// [2]/td[*]/div[contains(text(),'Product ID')]/a[1]")));
			// Sorting_Click.click();
			// Thread.sleep(1000);

			// Sort Descending

			// (//div[@class='x-layer x-menu'])
			// [3]/ul/li[*]/a[contains(text(),'Sort Descending')]

			WebElement Sort_Descending = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sort_Descending"))));
			Sort_Descending.click();
			Thread.sleep(1000);

			// (//div[@class='x-grid3-scroller'])
			// [2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[*]/div[1]/a[contains(text(),'PID')]

			WebElement Get_Product_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Get_Product_ID"))));
			Product_ID = Get_Product_ID.getText();
			Thread.sleep(1000);

			System.out.println(Product_ID);

			WebElement Product_Report_Close = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Report_Close"))));
			Product_Report_Close.click();
			Thread.sleep(1000);

			System.out.println("Product Created Successfully :- " + Product_ID);

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		Thread.sleep(1000);
		driver.navigate().refresh();
		Thread.sleep(1000);

		return Product_ID;

	}

	public static String createProduct_NIL_Rated(WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{
		String Product_ID = "";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement ProductMasterModule = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ProductMasterModule"))));
			Thread.sleep(1000);
			ProductMasterModule.click();

			//// *[@id='sequenceformatproductwin']/following-sibling::img[1]

			WebElement Sequ_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sequ_dropdown"))));
			Thread.sleep(1000);
			Sequ_dropdown.click();

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			WebElement Select_Sequ_Format = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Sequ_Format"))));

			Select_Sequ_Format.click();
			Thread.sleep(1000);

			WebElement Product_Name = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Name"))));
			String Product_Name1 = "Product @ 0%";
			Product_Name.sendKeys(Product_Name1);
			Thread.sleep(1000);

			WebElement Product_Entity_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Entity_dropdown"))));
			Thread.sleep(1000);
			Product_Entity_dropdown.click();

			// --> Pune //*[@class='x-layer x-combo-list
			// ']/div[1]/div[*]/table/tbody/tr/td[contains(text(),'Pune')]

			WebElement Select_Product_Entity = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Entity"))));
			Thread.sleep(1000);
			Select_Product_Entity.click();

			// Unit Quantity code

			//// *[@name='Custom_Unit Quantity Code']/following-sibling::img

			//// *[@name='Custom_Product Tax Class']/following-sibling::img[1]

			WebElement Product_Tax_Class_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Tax_Class_dropdown"))));
			Thread.sleep(1000);
			Product_Tax_Class_dropdown.click();

			// (//*[@class='x-layer x-combo-list '])
			// [4]/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Product
			// @ 12%')]
			WebElement Select_NIL_Rated_Product_Tax_Class = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_NIL_Rated_Product_Tax_Class"))));
			Select_NIL_Rated_Product_Tax_Class.click();
			Thread.sleep(1000);

			// HSN-SAC Code

			WebElement HSN_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("HSN_dropdown"))));
			Thread.sleep(1000);
			HSN_dropdown.click();

			WebElement Select_HSN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_HSN"))));
			Thread.sleep(1000);
			Select_HSN.click();

			WebElement Unit_Quantity_Code_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Quantity_Code_dropdown"))));
			Thread.sleep(1000);
			Unit_Quantity_Code_dropdown.click();

			// (//*[@class='x-layer x-combo-list '])
			// [8]/div/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'BAG-BAGS')]

			WebElement Select_Unit_Quantity_Code = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Unit_Quantity_Code"))));
			Thread.sleep(1000);
			Select_Unit_Quantity_Code.click();

			// Initial quantity
			// //*[@name='initialquantity']

			WebElement Initial_Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Initial_Quantity"))));
			Initial_Quantity.sendKeys("100");
			Thread.sleep(1000);

			// Click On LC

			WebElement License_Code = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("License_Code"))));
			License_Code.click();
			Thread.sleep(1000);

			try {

				WebElement Initial_quantity_alert = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Initial_quantity_alert"))));
				Initial_quantity_alert.click();
				Thread.sleep(1000);

			}

			catch (Exception noOk) {
				System.out.println("No such alert present now in product master");
			}

			// Product Warehouse
			// (//*[@id='serialdetailwindowsid'])
			// [4]/div/div/div/div/div[2]/div/div[1]/table/tbody/tr/td[2]/div

			// (//*[@class='x-window' and
			// contains(@style,'visible')]/div[2]/div/div/div/div/div[3]/div/div/div/div/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[2])
			// [1]

			WebElement Product_Initial_Warehouse = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Warehouse"))));
			Product_Initial_Warehouse.click();

			//// *[@class= ' x-form-text x-form-field x-form-focus']

			WebElement Product_Initial_Warehouse1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Warehouse1"))));
			Product_Initial_Warehouse1.sendKeys("DS - Default Store");
			Thread.sleep(1000);

			// //*[@class='x-combo-list-inner']/div[contains(text(),'DS -
			// Default Store')]

			WebElement Select_Product_Initial_Warehouse = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Initial_Warehouse"))));
			Select_Product_Initial_Warehouse.click();
			Thread.sleep(1000);

			WebElement Product_Initial_Location = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Location"))));
			Product_Initial_Location.click();

			//// *[@class= ' x-form-text x-form-field x-form-focus']

			WebElement Product_Initial_Location11 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Location11"))));
			Product_Initial_Location11.sendKeys("Default Location");
			Thread.sleep(1000);

			// //*[@class='x-combo-list-inner']/div[contains(text(),'DS -
			// Default Store')]

			WebElement Select_Product_Initial_location = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Initial_location"))));
			Select_Product_Initial_location.click();
			Thread.sleep(1000);

			WebElement Warehouse_Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Warehouse_Save"))));
			Warehouse_Save.click();
			Thread.sleep(1000);

			// General path --> (//*[@class='x-tab-panel-header
			// x-unselectable'])
			// [2]/div[*]/ul[*]/li[*]/a[*]/em[*]/span/span[contains(text(),'Purchase')]

			WebElement Product_Purchase = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Purchase"))));
			Product_Purchase.click();
			Thread.sleep(1000);

			// //*[@id='purchaseaccountidproductwin']/following-sibling::img[1]

			WebElement Product_Purchase_Account_Dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Purchase_Account_Dropdown"))));
			Product_Purchase_Account_Dropdown.click();
			Thread.sleep(1000);

			// //*[@class='x-layer x-combo-list
			// ']/div/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Purchases')]

			WebElement Select_Product_Purchase_Account = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Purchase_Account"))));
			Select_Product_Purchase_Account.click();
			Thread.sleep(1000);

			// //*[@id='initialpriceproductwin']

			WebElement Product_Initial_Purchase_Price = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Purchase_Price"))));
			Product_Initial_Purchase_Price.sendKeys("100");
			Thread.sleep(1000);

			WebElement Product_Sales = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Sales"))));
			Product_Sales.click();
			Thread.sleep(1000);

			// //*[@id='salesaccountidproductwin']/following-sibling::img[1]
			// ////*[@id='salesaccountidproductwin']

			WebElement Product_Sales_Account_Send = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Sales_Account_Send"))));
			Product_Sales_Account_Send.sendKeys("Sales");
			Thread.sleep(1000);

			// (//*[@class='x-layer x-combo-list '])
			// [1]/div/div/table/tbody/tr/td[2]

			WebElement Select_Product_Sales_Account = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Sales_Account"))));
			Select_Product_Sales_Account.click();
			Thread.sleep(1000);

			// //*[@id='initialsalespriceproductwin']

			WebElement Product_Initial_Sales_Price = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Sales_Price"))));
			Product_Initial_Sales_Price.sendKeys("200");
			Thread.sleep(1000);

			// Inventory Data

			WebElement Product_Inventory_Data = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Inventory_Data"))));
			Product_Inventory_Data.click();
			Thread.sleep(1000);

			// //*[@id='uomidproductwin']/following-sibling::img[1]

			//

			WebElement Stock_UOM_Send = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Stock_UOM_Send"))));
			Stock_UOM_Send.sendKeys("Unit");
			Thread.sleep(1000);

			WebElement Select_Stock_UOM = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Stock_UOM"))));
			Select_Stock_UOM.click();
			Thread.sleep(1000);

			// //*[@id='warehouseproductwin']

			WebElement Default_Warehouse_Send = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Default_Warehouse_Send"))));
			Default_Warehouse_Send.sendKeys("DS - Default Store");
			Thread.sleep(1000);

			WebElement Select_Default_Warehouse = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Default_Warehouse"))));
			Select_Default_Warehouse.click();
			Thread.sleep(1000);

			WebElement Default_Location_Send = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Default_Location_Send"))));
			Default_Location_Send.sendKeys("Default Location");
			Thread.sleep(1000);

			WebElement Select_Default_Location = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Default_Location"))));
			Select_Default_Location.click();
			Thread.sleep(1000);

			// Product Save

			// (//div[@class='x-tab-panel-bbar']/div/table/tbody/tr/td/table/tbody/tr/td[2]/em/button[contains(text(),'Save')])
			// [1]

			WebElement Product_Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Save"))));
			Product_Save.click();
			Thread.sleep(1000);

			// Confirm msg

			// //div[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td[2]/table/tbody/tr/td[2]/em/button

			WebElement ProductConfirm_Yes = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ProductConfirm_Yes"))));
			ProductConfirm_Yes.click();
			Thread.sleep(3000);

			// Product ok
			// //div[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td[1]/table/tbody/tr/td[2]/em/button

			/*
			 * WebElement Product_OK =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			 * GST_pro.getProperty("Product_OK")))); Product_OK.click();
			 * Thread.sleep(1000);
			 */

			// Product Close
			// //*[@id='as__productwin']/a[1]

			WebElement Product_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Close"))));
			Thread.sleep(2000);
			Product_Close.click();

			// //li[@id='as__mainProductDetails']/a[1]

			// (//tr[@class='x-grid3-hd-row'])
			// [2]/td[*]/div[contains(text(),'Product ID')]/a[1]

			WebElement menu = driver.findElement(By.xpath(GST_pro.getProperty("menu")));
			WebElement SUBMenu = driver.findElement(By.xpath(GST_pro.getProperty("SUBMenu")));

			Actions action = new Actions(driver);
			action.moveToElement(menu).perform();
			action.click(SUBMenu).perform();

			// WebElement Sorting_Click =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//tr[@class='x-grid3-hd-row'])
			// [2]/td[*]/div[contains(text(),'Product ID')]/a[1]")));
			// Sorting_Click.click();
			// Thread.sleep(1000);

			// Sort Descending

			// (//div[@class='x-layer x-menu'])
			// [3]/ul/li[*]/a[contains(text(),'Sort Descending')]

			WebElement Sort_Descending = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sort_Descending"))));
			Sort_Descending.click();
			Thread.sleep(5000);

			// (//div[@class='x-grid3-scroller'])
			// [2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[*]/div[1]/a[contains(text(),'PID')]

			WebElement Get_Product_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Get_Product_ID"))));
			Product_ID = Get_Product_ID.getText();
			Thread.sleep(1000);

			System.out.println(Product_ID);

			WebElement Product_Report_Close = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Report_Close"))));
			Thread.sleep(1000);
			Product_Report_Close.click();

			System.out.println("Product Created Successfully :- " + Product_ID);

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		Thread.sleep(1000);

		driver.navigate().refresh();

		Thread.sleep(1000);

		return Product_ID;

	}

	public static String createProduct_Exempted(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		String Product_ID = "";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement ProductMasterModule = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ProductMasterModule"))));
			Thread.sleep(1000);
			ProductMasterModule.click();

			//// *[@id='sequenceformatproductwin']/following-sibling::img[1]

			WebElement Sequ_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sequ_dropdown"))));
			Thread.sleep(1000);
			Sequ_dropdown.click();

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			WebElement Select_Sequ_Format = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Sequ_Format"))));

			Select_Sequ_Format.click();
			Thread.sleep(1000);

			WebElement Product_Name = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Name"))));
			String Product_Name1 = "Exempted";
			Product_Name.sendKeys(Product_Name1);
			Thread.sleep(1000);

			WebElement Product_Entity_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Entity_dropdown"))));
			Thread.sleep(1000);
			Product_Entity_dropdown.click();

			// --> Pune //*[@class='x-layer x-combo-list
			// ']/div[1]/div[*]/table/tbody/tr/td[contains(text(),'Pune')]

			WebElement Select_Product_Entity = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Entity"))));
			Thread.sleep(1000);
			Select_Product_Entity.click();

			// Unit Quantity code

			//// *[@name='Custom_Unit Quantity Code']/following-sibling::img

			//// *[@name='Custom_Product Tax Class']/following-sibling::img[1]

			WebElement Product_Tax_Class_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Tax_Class_dropdown"))));
			Thread.sleep(1000);
			Product_Tax_Class_dropdown.click();

			// (//*[@class='x-layer x-combo-list '])
			// [4]/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Product
			// @ 12%')]
			WebElement Select_Exempted_Product_Tax_Class = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Exempted_Product_Tax_Class"))));
			Select_Exempted_Product_Tax_Class.click();
			Thread.sleep(1000);

			// HSN-SAC Code

			WebElement HSN_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("HSN_dropdown"))));
			Thread.sleep(1000);
			HSN_dropdown.click();

			WebElement Select_HSN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_HSN"))));
			Thread.sleep(1000);
			Select_HSN.click();

			WebElement Unit_Quantity_Code_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Quantity_Code_dropdown"))));
			Thread.sleep(1000);
			Unit_Quantity_Code_dropdown.click();

			// (//*[@class='x-layer x-combo-list '])
			// [8]/div/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'BAG-BAGS')]

			WebElement Select_Unit_Quantity_Code = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Unit_Quantity_Code"))));
			Thread.sleep(1000);
			Select_Unit_Quantity_Code.click();

			// Initial quantity
			// //*[@name='initialquantity']

			WebElement Initial_Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Initial_Quantity"))));
			Initial_Quantity.sendKeys("100");
			Thread.sleep(1000);

			// Click On LC

			WebElement License_Code = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("License_Code"))));
			License_Code.click();
			Thread.sleep(1000);

			try {

				WebElement Initial_quantity_alert = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Initial_quantity_alert"))));
				Initial_quantity_alert.click();
				Thread.sleep(1000);

			}

			catch (Exception noOk) {
				System.out.println("No such alert present now in product master");
			}

			// Product Warehouse
			// (//*[@id='serialdetailwindowsid'])
			// [4]/div/div/div/div/div[2]/div/div[1]/table/tbody/tr/td[2]/div

			// (//*[@class='x-window' and
			// contains(@style,'visible')]/div[2]/div/div/div/div/div[3]/div/div/div/div/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[2])
			// [1]

			WebElement Product_Initial_Warehouse = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Warehouse"))));
			Product_Initial_Warehouse.click();

			//// *[@class= ' x-form-text x-form-field x-form-focus']

			WebElement Product_Initial_Warehouse1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Warehouse1"))));
			Product_Initial_Warehouse1.sendKeys("DS - Default Store");
			Thread.sleep(1000);

			// //*[@class='x-combo-list-inner']/div[contains(text(),'DS -
			// Default Store')]

			WebElement Select_Product_Initial_Warehouse = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Initial_Warehouse"))));
			Select_Product_Initial_Warehouse.click();
			Thread.sleep(1000);

			WebElement Product_Initial_Location = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Location"))));
			Product_Initial_Location.click();

			//// *[@class= ' x-form-text x-form-field x-form-focus']

			WebElement Product_Initial_Location11 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Location11"))));
			Product_Initial_Location11.sendKeys("Default Location");
			Thread.sleep(1000);

			// //*[@class='x-combo-list-inner']/div[contains(text(),'DS -
			// Default Store')]

			WebElement Select_Product_Initial_location = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Initial_location"))));
			Select_Product_Initial_location.click();
			Thread.sleep(1000);

			WebElement Warehouse_Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Warehouse_Save"))));
			Warehouse_Save.click();
			Thread.sleep(1000);

			// General path --> (//*[@class='x-tab-panel-header
			// x-unselectable'])
			// [2]/div[*]/ul[*]/li[*]/a[*]/em[*]/span/span[contains(text(),'Purchase')]

			WebElement Product_Purchase = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Purchase"))));
			Product_Purchase.click();
			Thread.sleep(1000);

			// //*[@id='purchaseaccountidproductwin']/following-sibling::img[1]

			WebElement Product_Purchase_Account_Dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Purchase_Account_Dropdown"))));
			Product_Purchase_Account_Dropdown.click();
			Thread.sleep(1000);

			// //*[@class='x-layer x-combo-list
			// ']/div/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Purchases')]

			WebElement Select_Product_Purchase_Account = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Purchase_Account"))));
			Select_Product_Purchase_Account.click();
			Thread.sleep(1000);

			// //*[@id='initialpriceproductwin']

			WebElement Product_Initial_Purchase_Price = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Purchase_Price"))));
			Product_Initial_Purchase_Price.sendKeys("100");
			Thread.sleep(1000);

			WebElement Product_Sales = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Sales"))));
			Product_Sales.click();
			Thread.sleep(1000);

			// //*[@id='salesaccountidproductwin']/following-sibling::img[1]
			// ////*[@id='salesaccountidproductwin']

			WebElement Product_Sales_Account_Send = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Sales_Account_Send"))));
			Product_Sales_Account_Send.sendKeys("Sales");
			Thread.sleep(1000);

			// (//*[@class='x-layer x-combo-list '])
			// [1]/div/div/table/tbody/tr/td[2]

			WebElement Select_Product_Sales_Account = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Sales_Account"))));
			Select_Product_Sales_Account.click();
			Thread.sleep(1000);

			// //*[@id='initialsalespriceproductwin']

			WebElement Product_Initial_Sales_Price = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Sales_Price"))));
			Product_Initial_Sales_Price.sendKeys("200");
			Thread.sleep(1000);

			// Inventory Data

			WebElement Product_Inventory_Data = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Inventory_Data"))));
			Product_Inventory_Data.click();
			Thread.sleep(1000);

			// //*[@id='uomidproductwin']/following-sibling::img[1]

			//

			WebElement Stock_UOM_Send = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Stock_UOM_Send"))));
			Stock_UOM_Send.sendKeys("Unit");
			Thread.sleep(1000);

			WebElement Select_Stock_UOM = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Stock_UOM"))));
			Select_Stock_UOM.click();
			Thread.sleep(1000);

			// //*[@id='warehouseproductwin']

			WebElement Default_Warehouse_Send = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Default_Warehouse_Send"))));
			Default_Warehouse_Send.sendKeys("DS - Default Store");
			Thread.sleep(1000);

			WebElement Select_Default_Warehouse = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Default_Warehouse"))));
			Select_Default_Warehouse.click();
			Thread.sleep(1000);

			WebElement Default_Location_Send = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Default_Location_Send"))));
			Default_Location_Send.sendKeys("Default Location");
			Thread.sleep(1000);

			WebElement Select_Default_Location = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Default_Location"))));
			Select_Default_Location.click();
			Thread.sleep(1000);

			// Product Save

			// (//div[@class='x-tab-panel-bbar']/div/table/tbody/tr/td/table/tbody/tr/td[2]/em/button[contains(text(),'Save')])
			// [1]

			WebElement Product_Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Save"))));
			Product_Save.click();
			Thread.sleep(3000);

			// Confirm msg

			// //div[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td[2]/table/tbody/tr/td[2]/em/button

			// Product ok
			// //div[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td[1]/table/tbody/tr/td[2]/em/button

			/*
			 * WebElement Product_OK =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			 * GST_pro.getProperty("Product_OK")))); Product_OK.click();
			 * Thread.sleep(1000);
			 * 
			 */
			// Product Close
			// //*[@id='as__productwin']/a[1]

			WebElement Product_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Close"))));
			Thread.sleep(2000);
			Product_Close.click();

			// //li[@id='as__mainProductDetails']/a[1]

			// (//tr[@class='x-grid3-hd-row'])
			// [2]/td[*]/div[contains(text(),'Product ID')]/a[1]

			WebElement menu = driver.findElement(By.xpath(GST_pro.getProperty("menu")));
			WebElement SUBMenu = driver.findElement(By.xpath(GST_pro.getProperty("SUBMenu")));

			Actions action = new Actions(driver);
			action.moveToElement(menu).perform();
			action.click(SUBMenu).perform();

			// WebElement Sorting_Click =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//tr[@class='x-grid3-hd-row'])
			// [2]/td[*]/div[contains(text(),'Product ID')]/a[1]")));
			// Sorting_Click.click();
			// Thread.sleep(1000);

			// Sort Descending

			// (//div[@class='x-layer x-menu'])
			// [3]/ul/li[*]/a[contains(text(),'Sort Descending')]

			WebElement Sort_Descending = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sort_Descending"))));
			Sort_Descending.click();
			Thread.sleep(5000);

			WebElement Get_Product_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Get_Product_ID"))));
			Product_ID = Get_Product_ID.getText();
			Thread.sleep(1000);

			System.out.println(Product_ID);

			WebElement Product_Report_Close = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Report_Close"))));
			Product_Report_Close.click();
			Thread.sleep(1000);

			System.out.println("Product Created Successfully :- " + Product_ID);

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		Thread.sleep(1000);

		driver.navigate().refresh();

		Thread.sleep(1000);

		return Product_ID;

	}

	public static String createProduct_NonGST(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		String Product_ID = "";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement ProductMasterModule = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ProductMasterModule"))));
			Thread.sleep(1000);
			ProductMasterModule.click();

			//// *[@id='sequenceformatproductwin']/following-sibling::img[1]

			WebElement Sequ_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sequ_dropdown"))));
			Thread.sleep(1000);
			Sequ_dropdown.click();

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			WebElement Select_Sequ_Format = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Sequ_Format"))));

			Select_Sequ_Format.click();
			Thread.sleep(1000);

			WebElement Product_Name = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Name"))));
			String Product_Name1 = "Non GST Product";
			Product_Name.sendKeys(Product_Name1);
			Thread.sleep(1000);

			WebElement Product_Entity_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Entity_dropdown"))));
			Thread.sleep(1000);
			Product_Entity_dropdown.click();

			// --> Pune //*[@class='x-layer x-combo-list
			// ']/div[1]/div[*]/table/tbody/tr/td[contains(text(),'Pune')]

			WebElement Select_Product_Entity = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Entity"))));
			Thread.sleep(1000);
			Select_Product_Entity.click();

			// Unit Quantity code

			//// *[@name='Custom_Unit Quantity Code']/following-sibling::img

			//// *[@name='Custom_Product Tax Class']/following-sibling::img[1]

			WebElement Product_Tax_Class_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Tax_Class_dropdown"))));
			Thread.sleep(1000);
			Product_Tax_Class_dropdown.click();

			// (//*[@class='x-layer x-combo-list '])
			// [4]/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Product
			// @ 12%')]
			WebElement Select_NonGST_Product_Tax_Class = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_NonGST_Product_Tax_Class"))));
			Select_NonGST_Product_Tax_Class.click();
			Thread.sleep(1000);

			// HSN-SAC Code

			WebElement HSN_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("HSN_dropdown"))));
			Thread.sleep(1000);
			HSN_dropdown.click();

			WebElement Select_HSN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_HSN"))));
			Thread.sleep(1000);
			Select_HSN.click();

			WebElement Unit_Quantity_Code_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Quantity_Code_dropdown"))));
			Thread.sleep(1000);
			Unit_Quantity_Code_dropdown.click();

			// (//*[@class='x-layer x-combo-list '])
			// [8]/div/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'BAG-BAGS')]

			WebElement Select_Unit_Quantity_Code = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Unit_Quantity_Code"))));
			Thread.sleep(1000);
			Select_Unit_Quantity_Code.click();

			// Initial quantity
			// //*[@name='initialquantity']

			WebElement Initial_Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Initial_Quantity"))));
			Initial_Quantity.sendKeys("100");
			Thread.sleep(1000);

			// Click On LC

			WebElement License_Code = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("License_Code"))));
			License_Code.click();
			Thread.sleep(1000);

			try {

				WebElement Initial_quantity_alert = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Initial_quantity_alert"))));
				Initial_quantity_alert.click();
				Thread.sleep(1000);

			}

			catch (Exception noOk) {
				System.out.println("No such alert present now in product master");
			}

			// Product Warehouse
			// (//*[@id='serialdetailwindowsid'])
			// [4]/div/div/div/div/div[2]/div/div[1]/table/tbody/tr/td[2]/div

			// (//*[@class='x-window' and
			// contains(@style,'visible')]/div[2]/div/div/div/div/div[3]/div/div/div/div/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[2])
			// [1]

			WebElement Product_Initial_Warehouse = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Warehouse"))));
			Product_Initial_Warehouse.click();

			//// *[@class= ' x-form-text x-form-field x-form-focus']

			WebElement Product_Initial_Warehouse1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Warehouse1"))));
			Product_Initial_Warehouse1.sendKeys("DS - Default Store");
			Thread.sleep(1000);

			// //*[@class='x-combo-list-inner']/div[contains(text(),'DS -
			// Default Store')]

			WebElement Select_Product_Initial_Warehouse = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Initial_Warehouse"))));
			Select_Product_Initial_Warehouse.click();
			Thread.sleep(1000);

			WebElement Product_Initial_Location = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Location"))));
			Product_Initial_Location.click();

			//// *[@class= ' x-form-text x-form-field x-form-focus']

			WebElement Product_Initial_Location11 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Location11"))));
			Product_Initial_Location11.sendKeys("Default Location");
			Thread.sleep(1000);

			// //*[@class='x-combo-list-inner']/div[contains(text(),'DS -
			// Default Store')]

			WebElement Select_Product_Initial_location = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Initial_location"))));
			Select_Product_Initial_location.click();
			Thread.sleep(1000);

			WebElement Warehouse_Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Warehouse_Save"))));
			Warehouse_Save.click();
			Thread.sleep(1000);

			// General path --> (//*[@class='x-tab-panel-header
			// x-unselectable'])
			// [2]/div[*]/ul[*]/li[*]/a[*]/em[*]/span/span[contains(text(),'Purchase')]

			WebElement Product_Purchase = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Purchase"))));
			Product_Purchase.click();
			Thread.sleep(1000);

			// //*[@id='purchaseaccountidproductwin']/following-sibling::img[1]

			WebElement Product_Purchase_Account_Dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Purchase_Account_Dropdown"))));
			Product_Purchase_Account_Dropdown.click();
			Thread.sleep(1000);

			// //*[@class='x-layer x-combo-list
			// ']/div/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Purchases')]

			WebElement Select_Product_Purchase_Account = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Purchase_Account"))));
			Select_Product_Purchase_Account.click();
			Thread.sleep(1000);

			// //*[@id='initialpriceproductwin']

			WebElement Product_Initial_Purchase_Price = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Purchase_Price"))));
			Product_Initial_Purchase_Price.sendKeys("100");
			Thread.sleep(1000);

			WebElement Product_Sales = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Sales"))));
			Product_Sales.click();
			Thread.sleep(1000);

			// //*[@id='salesaccountidproductwin']/following-sibling::img[1]
			// ////*[@id='salesaccountidproductwin']

			WebElement Product_Sales_Account_Send = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Sales_Account_Send"))));
			Product_Sales_Account_Send.sendKeys("Sales");
			Thread.sleep(1000);

			// (//*[@class='x-layer x-combo-list '])
			// [1]/div/div/table/tbody/tr/td[2]

			WebElement Select_Product_Sales_Account = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Sales_Account"))));
			Select_Product_Sales_Account.click();
			Thread.sleep(1000);

			// //*[@id='initialsalespriceproductwin']

			WebElement Product_Initial_Sales_Price = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Sales_Price"))));
			Product_Initial_Sales_Price.sendKeys("200");
			Thread.sleep(1000);

			// Inventory Data

			WebElement Product_Inventory_Data = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Inventory_Data"))));
			Product_Inventory_Data.click();
			Thread.sleep(1000);

			// //*[@id='uomidproductwin']/following-sibling::img[1]

			//

			WebElement Stock_UOM_Send = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Stock_UOM_Send"))));
			Stock_UOM_Send.sendKeys("Unit");
			Thread.sleep(1000);

			WebElement Select_Stock_UOM = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Stock_UOM"))));
			Select_Stock_UOM.click();
			Thread.sleep(1000);

			// //*[@id='warehouseproductwin']

			WebElement Default_Warehouse_Send = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Default_Warehouse_Send"))));
			Default_Warehouse_Send.sendKeys("DS - Default Store");
			Thread.sleep(1000);

			WebElement Select_Default_Warehouse = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Default_Warehouse"))));
			Select_Default_Warehouse.click();
			Thread.sleep(1000);

			WebElement Default_Location_Send = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Default_Location_Send"))));
			Default_Location_Send.sendKeys("Default Location");
			Thread.sleep(1000);

			WebElement Select_Default_Location = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Default_Location"))));
			Select_Default_Location.click();
			Thread.sleep(1000);

			// Product Save

			// (//div[@class='x-tab-panel-bbar']/div/table/tbody/tr/td/table/tbody/tr/td[2]/em/button[contains(text(),'Save')])
			// [1]

			WebElement Product_Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Save"))));
			Product_Save.click();
			Thread.sleep(3000);

			// Confirm msg

			// //div[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td[2]/table/tbody/tr/td[2]/em/button

			// Product ok
			// //div[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td[1]/table/tbody/tr/td[2]/em/button

			/*
			 * WebElement Product_OK =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			 * GST_pro.getProperty("Product_OK")))); Product_OK.click();
			 * Thread.sleep(1000);
			 * 
			 */
			// Product Close
			// //*[@id='as__productwin']/a[1]

			WebElement Product_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Close"))));
			Thread.sleep(2000);
			Product_Close.click();

			// //li[@id='as__mainProductDetails']/a[1]

			// (//tr[@class='x-grid3-hd-row'])
			// [2]/td[*]/div[contains(text(),'Product ID')]/a[1]

			WebElement menu = driver.findElement(By.xpath(GST_pro.getProperty("menu")));
			WebElement SUBMenu = driver.findElement(By.xpath(GST_pro.getProperty("SUBMenu")));

			Actions action = new Actions(driver);
			action.moveToElement(menu).perform();
			action.click(SUBMenu).perform();

			// WebElement Sorting_Click =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//tr[@class='x-grid3-hd-row'])
			// [2]/td[*]/div[contains(text(),'Product ID')]/a[1]")));
			// Sorting_Click.click();
			// Thread.sleep(1000);

			// Sort Descending

			// (//div[@class='x-layer x-menu'])
			// [3]/ul/li[*]/a[contains(text(),'Sort Descending')]

			WebElement Sort_Descending = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sort_Descending"))));
			Sort_Descending.click();
			Thread.sleep(3000);

			WebElement Get_Product_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Get_Product_ID"))));
			Product_ID = Get_Product_ID.getText();
			Thread.sleep(1000);

			System.out.println(Product_ID);

			WebElement Product_Report_Close = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Report_Close"))));
			Product_Report_Close.click();
			Thread.sleep(1000);

			System.out.println("Product Created Successfully :- " + Product_ID);

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Product_ID;

	}

	public static String createServiceTypeProduct(WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{
		String Product_ID = "";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement ProductMasterModule = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ProductMasterModule"))));
			Thread.sleep(1000);
			ProductMasterModule.click();

			WebElement Producttype_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Producttype_dropdown"))));
			Thread.sleep(1000);
			Producttype_dropdown.click();

			WebElement Service_product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Service_product"))));
			Thread.sleep(1000);
			Service_product.click();

			WebElement Sequ_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sequ_dropdown"))));
			Thread.sleep(1000);
			Sequ_dropdown.click();

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			WebElement Select_Sequ_Format = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Sequ_Format"))));

			Select_Sequ_Format.click();
			Thread.sleep(1000);

			WebElement Product_Name = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Name"))));
			String Product_Name1 = "Service @ 12%";
			Product_Name.sendKeys(Product_Name1);
			Thread.sleep(1000);

			WebElement Product_Entity_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Entity_dropdown"))));
			Thread.sleep(1000);
			Product_Entity_dropdown.click();

			// --> Pune //*[@class='x-layer x-combo-list
			// ']/div[1]/div[*]/table/tbody/tr/td[contains(text(),'Pune')]

			WebElement Select_Product_Entity = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Entity"))));
			Thread.sleep(1000);
			Select_Product_Entity.click();

			// Unit Quantity code

			//// *[@name='Custom_Product Tax Class']/following-sibling::img[1]

			WebElement Product_Tax_Class_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Tax_Class_dropdown"))));
			Thread.sleep(1000);
			Product_Tax_Class_dropdown.click();

			// (//*[@class='x-layer x-combo-list '])
			// [4]/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Product
			// @ 12%')]
			WebElement Select_Product_Tax_Class = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Tax_Class"))));
			Select_Product_Tax_Class.click();
			Thread.sleep(1000);

			// HSN-SAC Code

			WebElement HSN_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("HSN_dropdown"))));
			Thread.sleep(1000);
			HSN_dropdown.click();

			WebElement Select_HSN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_HSN"))));
			Thread.sleep(1000);
			Select_HSN.click();

			WebElement Unit_Quantity_Code_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Quantity_Code_dropdown"))));
			Thread.sleep(1000);
			Unit_Quantity_Code_dropdown.click();

			// (//*[@class='x-layer x-combo-list '])
			// [8]/div/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'BAG-BAGS')]

			WebElement Select_Unit_Quantity_Code = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Unit_Quantity_Code"))));
			Thread.sleep(1000);
			Select_Unit_Quantity_Code.click();

			// General path --> (//*[@class='x-tab-panel-header
			// x-unselectable'])
			// [2]/div[*]/ul[*]/li[*]/a[*]/em[*]/span/span[contains(text(),'Purchase')]

			WebElement Product_Purchase = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Purchase"))));
			Product_Purchase.click();
			Thread.sleep(1000);

			// //*[@id='purchaseaccountidproductwin']/following-sibling::img[1]

			WebElement Product_Purchase_Account_Dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Purchase_Account_Dropdown"))));
			Product_Purchase_Account_Dropdown.click();
			Thread.sleep(1000);

			// //*[@class='x-layer x-combo-list
			// ']/div/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Purchases')]

			WebElement Select_Product_Purchase_Account = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Purchase_Account"))));
			Select_Product_Purchase_Account.click();
			Thread.sleep(1000);

			// //*[@id='initialpriceproductwin']

			WebElement Product_Initial_Purchase_Price = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Purchase_Price"))));
			Product_Initial_Purchase_Price.sendKeys("100");
			Thread.sleep(1000);

			WebElement Product_Sales = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Sales"))));
			Product_Sales.click();
			Thread.sleep(1000);

			// //*[@id='salesaccountidproductwin']/following-sibling::img[1]
			// ////*[@id='salesaccountidproductwin']

			WebElement Product_Sales_Account_Send = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Sales_Account_Send"))));
			Product_Sales_Account_Send.sendKeys("Sales");
			Thread.sleep(1000);

			// (//*[@class='x-layer x-combo-list '])
			// [1]/div/div/table/tbody/tr/td[2]

			WebElement Select_Product_Sales_Account = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Sales_Account"))));
			Select_Product_Sales_Account.click();
			Thread.sleep(1000);

			// //*[@id='initialsalespriceproductwin']

			WebElement Product_Initial_Sales_Price = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Sales_Price"))));
			Product_Initial_Sales_Price.sendKeys("200");
			Thread.sleep(1000);

			// Inventory Data

			WebElement Product_Inventory_Data = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Inventory_Data"))));
			Product_Inventory_Data.click();
			Thread.sleep(1000);

			// //*[@id='uomidproductwin']/following-sibling::img[1]

			//

			WebElement Stock_UOM_Send = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Stock_UOM_Send"))));
			Stock_UOM_Send.sendKeys("Unit");
			Thread.sleep(1000);

			WebElement Select_Stock_UOM = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Stock_UOM"))));
			Select_Stock_UOM.click();
			Thread.sleep(1000);

			// Product Save

			// (//div[@class='x-tab-panel-bbar']/div/table/tbody/tr/td/table/tbody/tr/td[2]/em/button[contains(text(),'Save')])
			// [1]

			WebElement Product_Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Save"))));
			Product_Save.click();
			Thread.sleep(4000);

			// Confirm msg

			// //div[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td[2]/table/tbody/tr/td[2]/em/button

			WebElement ProductConfirm_Yes = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ProductConfirm_Yes"))));
			ProductConfirm_Yes.click();
			Thread.sleep(2000);

			try {
				WebElement duplicateProductID = new WebDriverWait(driver, 3).until(
						ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'already exists')]")));
				if (duplicateProductID.isDisplayed()) {
					Utilities.refresh();
					System.out.println("********* This product [" + Product_ID
							+ "] is already present so Not creating this One !!!!!! *******");
				}
			} catch (Exception ex) {
				// Continue as New Product
				try {
					ProductMaster.productAfterSaveOKBtn(driver);
				} catch (Exception noOk) {
					// No Ok button
				}
			}
			Utilities.click_element(GST_pro.getProperty("Product_Close"), driver);

			/*
			 * 
			 * WebElement Product_OK =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			 * GST_pro.getProperty("Product_OK")))); Product_OK.click();
			 * Thread.sleep(1000);
			 * 
			 * 
			 * // Product Close // //*[@id='as__productwin']/a[1]
			 * 
			 * WebElement Product_Close =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			 * GST_pro.getProperty("Product_Close")))); Product_Close.click();
			 * Thread.sleep(2000);
			 * 
			 */

			WebElement menu = driver.findElement(By.xpath(GST_pro.getProperty("menu")));
			WebElement SUBMenu = driver.findElement(By.xpath(GST_pro.getProperty("SUBMenu")));

			Actions action = new Actions(driver);
			action.moveToElement(menu).perform();
			action.click(SUBMenu).perform();

			WebElement Sort_Descending = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sort_Descending"))));
			Sort_Descending.click();
			Thread.sleep(2000);

			// (//div[@class='x-grid3-scroller'])
			// [2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[*]/div[1]/a[contains(text(),'PID')]

			WebElement Get_Product_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Get_Product_ID"))));
			Product_ID = Get_Product_ID.getText();
			Thread.sleep(1000);

			System.out.println(Product_ID);

			WebElement Product_Report_Close = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Report_Close"))));
			Product_Report_Close.click();
			Thread.sleep(1000);

			System.out.println("Service type Product Created Successfully :- " + Product_ID);

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Product_ID;

	}

	public static String createProduct_RCM(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		String Product_ID = "";

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement ProductMasterModule = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ProductMasterModule"))));
			Thread.sleep(1000);
			ProductMasterModule.click();

			//// *[@id='sequenceformatproductwin']/following-sibling::img[1]

			WebElement Sequ_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sequ_dropdown"))));
			Thread.sleep(1000);
			Sequ_dropdown.click();

			// //*[@class='x-layer x-combo-list
			// ']/div/div[contains(text(),'VC00000')]

			WebElement Select_Sequ_Format = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Sequ_Format"))));

			Select_Sequ_Format.click();
			Thread.sleep(1000);

			WebElement Product_Name = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Name"))));
			String Product_Name1 = "RCM @ 12%";
			Product_Name.sendKeys(Product_Name1);
			Thread.sleep(1000);

			WebElement Product_Entity_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Entity_dropdown"))));
			Thread.sleep(1000);
			Product_Entity_dropdown.click();

			// --> Pune //*[@class='x-layer x-combo-list
			// ']/div[1]/div[*]/table/tbody/tr/td[contains(text(),'Pune')]

			WebElement Select_Product_Entity = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Entity"))));
			Thread.sleep(1000);
			Select_Product_Entity.click();

			// Unit Quantity code

			//// *[@name='Custom_Product Tax Class']/following-sibling::img[1]

			WebElement Product_Tax_Class_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Tax_Class_dropdown"))));
			Thread.sleep(1000);
			Product_Tax_Class_dropdown.click();

			// (//*[@class='x-layer x-combo-list '])
			// [4]/div[*]/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Product
			// @ 12%')]
			WebElement Select_Product_Tax_Class = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Tax_Class"))));
			Select_Product_Tax_Class.click();
			Thread.sleep(1000);

			// HSN-SAC Code

			WebElement HSN_dropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("HSN_dropdown"))));
			Thread.sleep(1000);
			HSN_dropdown.click();

			WebElement Select_HSN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_HSN"))));
			Thread.sleep(1000);
			Select_HSN.click();

			WebElement Unit_Quantity_Code_dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Unit_Quantity_Code_dropdown"))));
			Thread.sleep(1000);
			Unit_Quantity_Code_dropdown.click();

			// (//*[@class='x-layer x-combo-list '])
			// [8]/div/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'BAG-BAGS')]

			WebElement Select_Unit_Quantity_Code = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Unit_Quantity_Code"))));
			Thread.sleep(1000);
			Select_Unit_Quantity_Code.click();

			// Initial quantity
			// //*[@name='initialquantity']

			WebElement Initial_Quantity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Initial_Quantity"))));
			Initial_Quantity.sendKeys("100");
			Thread.sleep(1000);

			// Click On LC

			WebElement License_Code = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("License_Code"))));
			License_Code.click();
			Thread.sleep(1000);

			try {

				WebElement Initial_quantity_alert = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Initial_quantity_alert"))));
				Initial_quantity_alert.click();
				Thread.sleep(1000);

			}

			catch (Exception noOk) {
				System.out.println("No such alert present now in product master");
			}

			// Product Warehouse
			// (//*[@id='serialdetailwindowsid'])
			// [4]/div/div/div/div/div[2]/div/div[1]/table/tbody/tr/td[2]/div

			// (//*[@class='x-window' and
			// contains(@style,'visible')]/div[2]/div/div/div/div/div[3]/div/div/div/div/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[2])
			// [1]

			WebElement Product_Initial_Warehouse = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Warehouse"))));
			Product_Initial_Warehouse.click();

			//// *[@class= ' x-form-text x-form-field x-form-focus']

			WebElement Product_Initial_Warehouse1 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Warehouse1"))));
			Product_Initial_Warehouse1.sendKeys("DS - Default Store");
			Thread.sleep(1000);

			// //*[@class='x-combo-list-inner']/div[contains(text(),'DS -
			// Default Store')]

			WebElement Select_Product_Initial_Warehouse = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Initial_Warehouse"))));
			Select_Product_Initial_Warehouse.click();
			Thread.sleep(1000);

			WebElement Product_Initial_Location = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Location"))));
			Product_Initial_Location.click();

			//// *[@class= ' x-form-text x-form-field x-form-focus']

			WebElement Product_Initial_Location11 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Location11"))));
			Product_Initial_Location11.sendKeys("Default Location");
			Thread.sleep(1000);

			// //*[@class='x-combo-list-inner']/div[contains(text(),'DS -
			// Default Store')]

			WebElement Select_Product_Initial_location = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Initial_location"))));
			Select_Product_Initial_location.click();
			Thread.sleep(1000);

			WebElement Warehouse_Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Warehouse_Save"))));
			Warehouse_Save.click();
			Thread.sleep(1000);

			WebElement RCM_Check = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("RCM_Check"))));
			RCM_Check.click();
			Thread.sleep(500);

			WebElement Product_Purchase = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Purchase"))));
			Product_Purchase.click();
			Thread.sleep(1000);

			// //*[@id='purchaseaccountidproductwin']/following-sibling::img[1]

			WebElement Product_Purchase_Account_Dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Purchase_Account_Dropdown"))));
			Product_Purchase_Account_Dropdown.click();
			Thread.sleep(1000);

			// //*[@class='x-layer x-combo-list
			// ']/div/div[*]/table[*]/tbody[*]/tr[*]/td[contains(text(),'Purchases')]

			WebElement Select_Product_Purchase_Account = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Purchase_Account"))));
			Select_Product_Purchase_Account.click();
			Thread.sleep(1000);

			// //*[@id='initialpriceproductwin']

			WebElement Product_Initial_Purchase_Price = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Purchase_Price"))));
			Product_Initial_Purchase_Price.sendKeys("100");
			Thread.sleep(1000);

			WebElement Product_Sales = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Sales"))));
			Product_Sales.click();
			Thread.sleep(1000);

			// //*[@id='salesaccountidproductwin']/following-sibling::img[1]
			// ////*[@id='salesaccountidproductwin']

			WebElement Product_Sales_Account_Send = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Sales_Account_Send"))));
			Product_Sales_Account_Send.sendKeys("Sales");
			Thread.sleep(1000);

			// (//*[@class='x-layer x-combo-list '])
			// [1]/div/div/table/tbody/tr/td[2]

			WebElement Select_Product_Sales_Account = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Product_Sales_Account"))));
			Select_Product_Sales_Account.click();
			Thread.sleep(1000);

			// //*[@id='initialsalespriceproductwin']

			WebElement Product_Initial_Sales_Price = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Initial_Sales_Price"))));
			Product_Initial_Sales_Price.sendKeys("200");
			Thread.sleep(1000);

			// Inventory Data

			WebElement Product_Inventory_Data = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Inventory_Data"))));
			Product_Inventory_Data.click();
			Thread.sleep(1000);

			// //*[@id='uomidproductwin']/following-sibling::img[1]

			//

			WebElement Stock_UOM_Send = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Stock_UOM_Send"))));
			Stock_UOM_Send.sendKeys("Unit");
			Thread.sleep(1000);

			WebElement Select_Stock_UOM = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Stock_UOM"))));
			Select_Stock_UOM.click();
			Thread.sleep(1000);

			// //*[@id='warehouseproductwin']

			WebElement Default_Warehouse_Send = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Default_Warehouse_Send"))));
			Default_Warehouse_Send.sendKeys("DS - Default Store");
			Thread.sleep(1000);

			WebElement Select_Default_Warehouse = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Default_Warehouse"))));
			Select_Default_Warehouse.click();
			Thread.sleep(1000);

			WebElement Default_Location_Send = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Default_Location_Send"))));
			Default_Location_Send.sendKeys("Default Location");
			Thread.sleep(1000);

			WebElement Select_Default_Location = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Default_Location"))));
			Select_Default_Location.click();
			Thread.sleep(1000);

			// Product Save

			// (//div[@class='x-tab-panel-bbar']/div/table/tbody/tr/td/table/tbody/tr/td[2]/em/button[contains(text(),'Save')])
			// [1]

			WebElement Product_Save = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Save"))));
			Product_Save.click();
			Thread.sleep(1000);

			// Confirm msg

			// //div[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td[2]/table/tbody/tr/td[2]/em/button

			WebElement ProductConfirm_Yes = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("ProductConfirm_Yes"))));
			ProductConfirm_Yes.click();
			Thread.sleep(3000);

			// Product ok
			// //div[@class='x-panel-btns
			// x-panel-btns-center']/table/tbody/tr/td[1]/table/tbody/tr/td[2]/em/button

			/*
			 * WebElement Product_OK =
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			 * GST_pro.getProperty("Product_OK")))); Product_OK.click();
			 * Thread.sleep(1000);
			 */

			// Product Close
			// //*[@id='as__productwin']/a[1]

			WebElement Product_Close = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Close"))));
			Thread.sleep(2000);
			Product_Close.click();

			// //li[@id='as__mainProductDetails']/a[1]

			// (//tr[@class='x-grid3-hd-row'])
			// [2]/td[*]/div[contains(text(),'Product ID')]/a[1]

			WebElement menu = driver.findElement(By.xpath(GST_pro.getProperty("menu")));
			WebElement SUBMenu = driver.findElement(By.xpath(GST_pro.getProperty("SUBMenu")));

			Actions action = new Actions(driver);
			action.moveToElement(menu).perform();
			action.click(SUBMenu).perform();

			// WebElement Sorting_Click =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//tr[@class='x-grid3-hd-row'])
			// [2]/td[*]/div[contains(text(),'Product ID')]/a[1]")));
			// Sorting_Click.click();
			// Thread.sleep(1000);

			// Sort Descending

			// (//div[@class='x-layer x-menu'])
			// [3]/ul/li[*]/a[contains(text(),'Sort Descending')]

			WebElement Sort_Descending = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Sort_Descending"))));
			Sort_Descending.click();
			Thread.sleep(1000);

			// (//div[@class='x-grid3-scroller'])
			// [2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[*]/div[1]/a[contains(text(),'PID')]

			WebElement Get_Product_ID = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Get_Product_ID"))));
			Product_ID = Get_Product_ID.getText();
			Thread.sleep(1000);

			System.out.println(Product_ID);

			WebElement Product_Report_Close = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Product_Report_Close"))));
			Product_Report_Close.click();
			Thread.sleep(1000);

			System.out.println("RCM type Product Created Successfully :- " + Product_ID);

			System.out.println("********************************************************************");

			System.out.println("********************************************************************");

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Product_ID;

	}

}
