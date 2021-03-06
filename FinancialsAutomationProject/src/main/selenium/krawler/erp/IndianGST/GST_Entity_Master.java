package krawler.erp.IndianGST;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.host.URL;

import krawler.erp.page.Utilities;

public class GST_Entity_Master

{

	public static String create_GST_Entity(WebDriver driver) throws InterruptedException, AWTException, IOException

	{
		String Entity_Name = null;
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Masters_Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Masters_Module"))));
			Masters_Module.click();
			Thread.sleep(500);

			WebElement Dimesions = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Dimesions"))));
			Dimesions.click();
			Thread.sleep(500);

			WebElement Search_Masters = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Masters"))));
			Search_Masters.sendKeys("Entity");
			Thread.sleep(500);

			WebElement Entity_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Click"))));
			Entity_Click.click();
			Thread.sleep(1000);

			Entity_Name = "Nashik";

			WebElement Search_Entity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Entity"))));
			Search_Entity.sendKeys(Entity_Name);
			Thread.sleep(1000);

			WebElement Entity_Refresh1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Refresh"))));
			Entity_Refresh1.click();
			Thread.sleep(1000);

			String Message = "There is no record to display.";

			String Get_Empty_Message = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Get_Empty_Message"))))
					.getText();

			if (Message.equals(Get_Empty_Message)) {

				WebElement Master_Item_Menu = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Master_Item_Menu"))));
				Master_Item_Menu.click();
				Thread.sleep(1000);

				WebElement Add_Master_Item = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Add_Master_Item"))));
				Add_Master_Item.click();
				Thread.sleep(500);

				WebElement Entity_Enter = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Enter"))));
				Entity_Enter.sendKeys("Nashik");
				Thread.sleep(500);

				WebElement Pin_Code = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Pin_Code"))));
				Pin_Code.sendKeys("411006");
				Thread.sleep(500);

				WebElement Entity_GSTIN = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_GSTIN"))));
				Entity_GSTIN.sendKeys("27");
				Thread.sleep(500);

				WebElement Entity_City = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_City"))));
				Entity_City.sendKeys("Nashik");
				Thread.sleep(500);

				WebElement Entity_State = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_State"))));
				Entity_State.sendKeys("Mahara");
				Thread.sleep(200);

				WebElement Entity_State_Click = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_State_Click"))));
				Entity_State_Click.click();
				Thread.sleep(200);

				WebElement Entity_Save = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Save"))));
				Entity_Save.click();
				Thread.sleep(2000);

				WebElement Entity_Refresh = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Refresh"))));
				Entity_Refresh.click();
				Thread.sleep(5000);

				WebElement Master_Configuration_OK = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(GST_pro.getProperty("Master_Configuration_OK"))));
				Master_Configuration_OK.click();
				Thread.sleep(1000);

				WebElement Master_Configuration_Close = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(GST_pro.getProperty("Master_Configuration_Close"))));
				Master_Configuration_Close.click();
				Thread.sleep(1000);

				System.out.println("Entity created successsfully");

				System.out.println(
						"**************************************************************************************");

				driver.navigate().refresh();

			}

			else

			{
				System.out.println("Unable to create Entity");

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Entity_Name;

	}

	public static String edit_GST_Entity(WebDriver driver, String Entity)
			throws InterruptedException, AWTException, IOException

	{
		String Entity_Name = Entity;
		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Masters_Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Masters_Module"))));
			Masters_Module.click();
			Thread.sleep(500);

			WebElement Dimesions = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Dimesions"))));
			Dimesions.click();
			Thread.sleep(500);

			WebElement Search_Masters = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Masters"))));
			Search_Masters.sendKeys("Entity");
			Thread.sleep(500);

			WebElement Entity_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Click"))));
			Entity_Click.click();
			Thread.sleep(1000);

			Entity_Name = "Nashik";

			WebElement Search_Entity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Entity"))));
			Search_Entity.sendKeys(Entity_Name);
			Thread.sleep(1000);

			WebElement Entity_Refresh1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Refresh"))));
			Entity_Refresh1.click();
			Thread.sleep(1000);

			String Get_Entity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Get_Entity"))))
					.getText();

			if (Entity_Name.equals(Get_Entity)) {

				WebElement Check_Entity_Box = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Check_Entity_Box"))));
				Check_Entity_Box.click();
				Thread.sleep(1000);

				WebElement Master_Item_Menu = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Master_Item_Menu"))));
				Master_Item_Menu.click();
				Thread.sleep(1000);

				WebElement Edit_Master_Item = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Edit_Master_Item"))));
				Edit_Master_Item.click();
				Thread.sleep(500);

				WebElement Entity_Description = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Description"))));
				Entity_Description.sendKeys("Located in Maharashtra");
				Thread.sleep(500);

				WebElement Pin_Code1 = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Pin_Code"))));
				Pin_Code1.clear();
				Thread.sleep(500);

				WebElement Pin_Code = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Pin_Code"))));
				Pin_Code.sendKeys("413221");
				Thread.sleep(500);

				WebElement Entity_Save = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Save"))));
				Entity_Save.click();
				Thread.sleep(2000);

				// WebElement Entity_Refresh =
				// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Refresh"))));
				// Entity_Refresh.click();
				// Thread.sleep(5000);

				WebElement Master_Configuration_OK = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(GST_pro.getProperty("Master_Configuration_OK"))));
				Master_Configuration_OK.click();
				Thread.sleep(1000);

				WebElement Master_Configuration_Close = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(GST_pro.getProperty("Master_Configuration_Close"))));
				Master_Configuration_Close.click();
				Thread.sleep(1000);

				System.out.println("Entity edited successsfully");

				System.out.println(
						"**************************************************************************************");

				driver.navigate().refresh();

			}

			else

			{
				System.out.println("Unable to create Entity");

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

		return Entity_Name;

	}

	public static void delete_GST_Entity(WebDriver driver) throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Masters_Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Masters_Module"))));
			Masters_Module.click();
			Thread.sleep(500);

			WebElement Dimesions = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Dimesions"))));
			Dimesions.click();
			Thread.sleep(500);

			WebElement Search_Masters = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Masters"))));
			Search_Masters.sendKeys("Entity");
			Thread.sleep(500);

			WebElement Entity_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Click"))));
			Entity_Click.click();
			Thread.sleep(1000);

			String Entity_Name = "Nashik";

			WebElement Search_Entity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Entity"))));
			Search_Entity.sendKeys(Entity_Name);
			Thread.sleep(1000);

			WebElement Entity_Refresh1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Refresh"))));
			Entity_Refresh1.click();
			Thread.sleep(1000);

			String Get_Entity = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Get_Entity"))))
					.getText();

			if (Entity_Name.equals(Get_Entity))

			{

				WebElement Check_Entity_Box = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Check_Entity_Box"))));
				Check_Entity_Box.click();
				Thread.sleep(1000);

				WebElement Master_Item_Menu = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Master_Item_Menu"))));
				Master_Item_Menu.click();
				Thread.sleep(1000);

				WebElement Delete_Master_Item = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Delete_Master_Item"))));
				Delete_Master_Item.click();
				Thread.sleep(1000);

				WebElement Delete_Master_Item_Ok = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(GST_pro.getProperty("Delete_Master_Item_Ok"))));
				Delete_Master_Item_Ok.click();
				Thread.sleep(500);

				WebElement Entity_Refresh = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Refresh"))));
				Entity_Refresh.click();
				Thread.sleep(5000);

				WebElement Master_Configuration_OK = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(GST_pro.getProperty("Master_Configuration_OK"))));
				Master_Configuration_OK.click();
				Thread.sleep(1000);

				WebElement Master_Configuration_Close = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(GST_pro.getProperty("Master_Configuration_Close"))));
				Master_Configuration_Close.click();
				Thread.sleep(1000);

				System.out.println("Entity deleted successsfully");

				System.out.println(
						"**************************************************************************************");

				driver.navigate().refresh();

			}

			else {
				System.out.println("Unable to delete Entity");
			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

}
