package krawler.erp.IndianGST;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class GST_HSN_Master

{
	public static String HSN_Code = null;

	public static String create_GST_HSN(WebDriver driver) throws InterruptedException, AWTException, IOException

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
			Search_Masters.sendKeys("HSN");
			Thread.sleep(500);

			WebElement HSN_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("HSN_Click"))));
			HSN_Click.click();
			Thread.sleep(1000);

			String HSN_Code = "88888888";

			WebElement Search_HSN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Entity"))));
			Search_HSN.sendKeys(HSN_Code);
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

				WebElement HSN_Enter = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("HSN_Enter"))));
				HSN_Enter.sendKeys(HSN_Code);
				Thread.sleep(500);

				WebElement Add_HSN_Description = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Add_HSN_Description"))));
				Add_HSN_Description.sendKeys("Fertilisers");
				Thread.sleep(500);

				WebElement HSN_Save = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Save"))));
				HSN_Save.click();
				Thread.sleep(2000);

				WebElement Master_Configuration_OK = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(GST_pro.getProperty("Master_Configuration_OK"))));
				Master_Configuration_OK.click();
				Thread.sleep(1000);

				WebElement Master_Configuration_Close = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(GST_pro.getProperty("Master_Configuration_Close"))));
				Master_Configuration_Close.click();
				Thread.sleep(1000);

				System.out.println("HSN Code created successsfully");

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

		return HSN_Code;

	}

	public static void edit_GST_HSN(WebDriver driver) throws InterruptedException, AWTException, IOException

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
			Search_Masters.sendKeys("HSN");
			Thread.sleep(500);

			WebElement HSN_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("HSN_Click"))));
			HSN_Click.click();
			Thread.sleep(1000);

			String HSN_Code = "88888888";

			WebElement Search_HSN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Entity"))));
			Search_HSN.sendKeys(HSN_Code);
			Thread.sleep(1000);

			WebElement Entity_Refresh1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Refresh"))));
			Entity_Refresh1.click();
			Thread.sleep(1000);

			String Get_HSN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Get_Entity"))))
					.getText();

			if (HSN_Code.equals(Get_HSN))

			{

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
				Thread.sleep(1000);

				WebElement Add_HSN_Description = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Add_HSN_Description"))));
				Add_HSN_Description.clear();
				Thread.sleep(100);

				WebElement Add_HSN_Description1 = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Add_HSN_Description"))));
				Add_HSN_Description1.sendKeys("Fertilisers & Chemicals");
				Thread.sleep(500);

				WebElement HSN_Save = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Save"))));
				HSN_Save.click();
				Thread.sleep(2000);

				WebElement Master_Configuration_OK = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(GST_pro.getProperty("Master_Configuration_OK"))));
				Master_Configuration_OK.click();
				Thread.sleep(1000);

				WebElement Master_Configuration_Close = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(GST_pro.getProperty("Master_Configuration_Close"))));
				Master_Configuration_Close.click();
				Thread.sleep(1000);

				System.out.println("HSN edited successsfully");

				System.out.println(
						"**************************************************************************************");

				driver.navigate().refresh();

			}

			else {
				System.out.println("Unable to delete HSN");
			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

	public static void delete_GST_HSN(WebDriver driver) throws InterruptedException, AWTException, IOException

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
			Search_Masters.sendKeys("HSN");
			Thread.sleep(500);

			WebElement HSN_Click = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("HSN_Click"))));
			HSN_Click.click();
			Thread.sleep(1000);

			String HSN_Code = "88888888";

			WebElement Search_HSN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Search_Entity"))));
			Search_HSN.sendKeys(HSN_Code);
			Thread.sleep(1000);

			WebElement Entity_Refresh1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Entity_Refresh"))));
			Entity_Refresh1.click();
			Thread.sleep(1000);

			String Get_HSN = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Get_Entity"))))
					.getText();

			if (HSN_Code.equals(Get_HSN))

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

				WebElement Master_Configuration_OK = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(GST_pro.getProperty("Master_Configuration_OK"))));
				Master_Configuration_OK.click();
				Thread.sleep(1000);

				WebElement Master_Configuration_Close = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(GST_pro.getProperty("Master_Configuration_Close"))));
				Master_Configuration_Close.click();
				Thread.sleep(1000);

				System.out.println("HSN deleted successsfully");

				System.out.println(
						"**************************************************************************************");

				driver.navigate().refresh();

			}

			else {
				System.out.println("Unable to delete HSN");
			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

}
