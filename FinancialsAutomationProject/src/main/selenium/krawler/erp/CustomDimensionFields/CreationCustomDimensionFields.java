package krawler.erp.CustomDimensionFields;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.jna.platform.win32.Advapi32;

import krawler.erp.page.Utilities;

public class CreationCustomDimensionFields {

	// Creating Custom Field and Dimensions

	public static void create_CustDim(WebDriver driver) {

		try {

			Properties AdvSrc_CusDim = Utilities.fetchProValue("OR_Adv_SearchCustomDim.properties");
			Thread.sleep(3000);

			new WebDriverWait(driver, 60).until(ExpectedConditions
					.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("ClickOnAdministrator"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("ClickOnAdministrator"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 60).until(ExpectedConditions
					.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("ClickOnMasterConfig"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("ClickOnMasterConfig"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("ClickOnCustomField"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("ClickOnCustomField"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldName"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldName"))).click();
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldName"))).sendKeys("CustomText");
			Thread.sleep(3000);

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("EnterToolTipName"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterToolTipName"))).click();
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterToolTipName"))).sendKeys("CustomText");
			Thread.sleep(3000);

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldType"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldType"))).click();
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldType"))).sendKeys("Text Field");
			Utilities.enterTextandSelect("Text Field", AdvSrc_CusDim.getProperty("EnterFieldType"), driver);
			Thread.sleep(3000);

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("EnterMaxLength"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterMaxLength"))).click();
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterMaxLength"))).sendKeys("20");
			Thread.sleep(3000);

			String arrCheck[] = { "SiCscheck", "PiCpCheck", "debitNote", "creditNote", "MPcheck", "RPcheck", "JEcheck",
					"POcheck", "SOcheck", "GRcheck", "DOcheck", "SRcheck", "PRcheck", "CQcheck", "VQcheck",
					"PReqcheck" };
			int xyz = arrCheck.length;
			System.out.println(xyz);

			WebElement checkBox = null;
			for (int i = 0; i < arrCheck.length; i++) {
				checkBox = driver.findElement(By.xpath(AdvSrc_CusDim.getProperty(arrCheck[i])));
				if (!checkBox.isSelected()) {
					checkBox.click();
					Thread.sleep(1000);
					System.out.println(i);
				}
			}

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("AddCustomField"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("AddCustomField"))).click();
			Thread.sleep(5000);

			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("ClickOk"))).click();

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("ClickOnCustomField"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("ClickOnCustomField"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldName"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldName"))).click();
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldName"))).sendKeys("Numeric Field");
			Thread.sleep(3000);

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldType"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldType"))).click();
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldType"))).sendKeys("Numeric Field");
			Utilities.enterTextandSelect("Numeric Field", AdvSrc_CusDim.getProperty("EnterFieldType"), driver);
			Thread.sleep(3000);

			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("DecPre"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("DecPre"))).clear();
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("DecPre"))).sendKeys("4");

			String arrCheck1[] = { "SiCscheck", "PiCpCheck", "debitNote", "creditNote", "MPcheck", "RPcheck", "JEcheck",
					"POcheck", "SOcheck", "GRcheck", "DOcheck", "SRcheck", "PRcheck", "CQcheck", "VQcheck",
					"PReqcheck" };
			int xyz1 = arrCheck1.length;
			// System.out.println(xyz1);

			WebElement checkBox1 = null;
			for (int i = 0; i < arrCheck1.length; i++) {
				checkBox1 = driver.findElement(By.xpath(AdvSrc_CusDim.getProperty(arrCheck1[i])));
				if (!checkBox1.isSelected()) {
					checkBox1.click();
					Thread.sleep(1000);
					System.out.println(i);
				}
			}
			Thread.sleep(3000);

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("AddCustomField"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("AddCustomField"))).click();
			Thread.sleep(5000);

			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("ClickOk"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("ClickOnCustomField"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("ClickOnCustomField"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldName"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldName"))).click();
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldName"))).sendKeys("Drop Down");
			Thread.sleep(3000);

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("EnterToolTipName"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterToolTipName"))).click();
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterToolTipName"))).sendKeys("Drop Down");
			Thread.sleep(3000);

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldType"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldType"))).click();
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("EnterFieldType"))).sendKeys("Drop Down");
			Utilities.enterTextandSelect("Drop Down", AdvSrc_CusDim.getProperty("EnterFieldType"), driver);
			Thread.sleep(3000);

			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("MultipleVal"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("MultipleVal"))).click();
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("MultipleVal"))).sendKeys("X;Y;Z");
			Thread.sleep(3000);

			String arrCheck2[] = { "SiCscheck", "PiCpCheck", "debitNote", "creditNote", "MPcheck", "RPcheck", "JEcheck",
					"POcheck", "SOcheck", "GRcheck", "DOcheck", "SRcheck", "PRcheck", "CQcheck", "VQcheck",
					"PReqcheck" };
			int xyz2 = arrCheck2.length;
			// System.out.println(xyz1);

			WebElement checkBox2 = null;
			for (int i = 0; i < arrCheck1.length; i++) {
				checkBox2 = driver.findElement(By.xpath(AdvSrc_CusDim.getProperty(arrCheck1[i])));
				if (!checkBox2.isSelected()) {
					checkBox2.click();
					Thread.sleep(1000);
					System.out.println(i);
				}
			}
			Thread.sleep(3000);

			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AdvSrc_CusDim.getProperty("AddCustomField"))));
			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("AddCustomField"))).click();
			Thread.sleep(5000);

			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("ClickOk"))).click();
			Thread.sleep(5000);

			driver.findElement(By.xpath(AdvSrc_CusDim.getProperty("CloseMaster"))).click();

		} catch (Exception EX1) {
			EX1.printStackTrace();
		}
	}

}
