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

public class GST_Output_Rule

{
	public static void Import_Output_GST_Rule(WebDriver driver) throws InterruptedException, AWTException, IOException

	{

		Properties GST_pro = Utilities.fetchProValue("OR_IndianGST.properties");

		try

		{
			driver.navigate().refresh();
			Thread.sleep(4000);

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement Masters_Module = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Masters_Module"))));
			Masters_Module.click();
			Thread.sleep(500);

			WebElement GST_Tax_Masters = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GST_Tax_Masters"))));
			GST_Tax_Masters.click();
			Thread.sleep(500);

			WebElement GST_Output_Rule_Report = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("GST_Output_Rule_Report"))));
			GST_Output_Rule_Report.click();
			Thread.sleep(500);

			WebElement Import_Button = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Import_Button"))));
			Import_Button.click();
			Thread.sleep(1000);

			WebElement Import_CSV_file_icon = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Import_CSV_file_icon"))));
			Import_CSV_file_icon.click();
			Thread.sleep(1000);

			String filPathCSV = Utilities.importFile("OutputGST.csv");

			WebElement browseFile = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Choose_file"))));
			browseFile.sendKeys(filPathCSV);
			Thread.sleep(1000);

			WebElement Date_Format_dropdown = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Date_Format_dropdown"))));
			Date_Format_dropdown.click();
			Thread.sleep(1000);

			WebElement Select_Date_Format = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Select_Date_Format"))));
			Select_Date_Format.click();
			Thread.sleep(500);

			WebElement Next_button = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Next_button"))));
			Next_button.click();
			Thread.sleep(2000);

			WebElement Auto_Map_Columns_button = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Auto_Map_Columns_button"))));
			Auto_Map_Columns_button.click();
			Thread.sleep(1000);

			WebElement Analyze_Data_button = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Analyze_Data_button"))));
			Analyze_Data_button.click();
			Thread.sleep(1000);

			String Success_Import = "All records are valid, Please click " + "\"Import Data\"" + " to continue.";

			String Get_Validation_Message = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Get_Validation"))))
					.getText();

			if (Success_Import.equals(Get_Validation_Message))

			{

				WebElement Import_Data_button = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Import_Data_button"))));
				Import_Data_button.click();
				Thread.sleep(5000);

				WebElement Import_Status = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Import_Status"))));

				WebElement Import_Status_close = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(GST_pro.getProperty("Import_Status_close"))));
				Import_Status_close.click();
				Thread.sleep(1000);

				System.out.println("Output Import rules added successfully");

				System.out.println(
						"**************************************************************************************");

				driver.navigate().refresh();

			}

			else {
				System.out.println("Failed to Import records");

			}

		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);

		}

	}

}
