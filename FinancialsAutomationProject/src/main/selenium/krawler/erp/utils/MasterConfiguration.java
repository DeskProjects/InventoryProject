package krawler.erp.utils;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import krawler.erp.page.Utilities;

public class MasterConfiguration {

	public static void disabledim(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_MasterConfig.properties");

			Utilities.waitandClick(pro.getProperty("AdministrationButton"), driver);
			Utilities.HoverandClick(pro.getProperty("masterConfigurationLink"), driver);
			Utilities.clickCheckBox(pro.getProperty("masterConfigurationLink"), "uncheck", driver);
			Thread.sleep(1000);
			// Utilities.disableExpander(driver);

			int headerSize = Utilities.totalSize("//*[text()='Master Group']/ancestor::tr/td/div", driver);
			int indOfMasterGrp = 0, indOfField = 0, indOfModuleNam = 0;

			for (int i = 1; i <= headerSize; i++) {
				String headrName = driver
						.findElement(By.xpath("//*[text()='Master Group']/ancestor::tr/td[" + i + "]/div")).getText();
				if (headrName.equalsIgnoreCase("Master Group")) {
					indOfMasterGrp = i;
				}
				if (headrName.equalsIgnoreCase("Field Type")) {
					indOfField = i;
				}
				if (headrName.equalsIgnoreCase("Module Name")) {
					indOfModuleNam = i;
				}
			}

			boolean flag = false;
			int totalFields = 0;
			String arrCheck[] = { "SiCscheck", "PiCpCheck", "debitNote", "creditNote", "MPcheck", "RPcheck", "JEcheck",
					"Customer", "Vendor", "POcheck", "SOcheck", "GRcheck", "DOcheck", "SRcheck", "PRcheck", "CQcheck",
					"VQcheck", "PReqcheck", "ProductService" };
			int totalGroup = Utilities
					.totalSize("//*[text()='Master Group']/ancestor::div[3]/following::div[1]/div/div", driver);

			for (int i = 1; i <= totalGroup; i++) //
			{
				String fieldName = null;
				WebElement checkBox = null;
				fieldName = driver
						.findElement(By.xpath("//*[text()='Master Group']/ancestor::div[3]/following::div[1]/div/div["
								+ i + "]/table/tbody/tr/td[" + indOfField + "]/div"))
						.getText();

				if (fieldName.contains("Line Item") || fieldName.contains("Custom Field")
						|| fieldName.contains("Dimension")) {
					System.out.println(fieldName);
					Utilities.click_element("//*[text()='Master Group']/ancestor::div[3]/following::div[1]/div/div[" + i
							+ "]/table/tbody/tr/td[" + indOfField + "]/div", driver);
					Thread.sleep(1500);

					Utilities.click_element(pro.getProperty("selectActiveDeactive"), driver);
					Thread.sleep(1000);
					for (int j = 0; j < arrCheck.length; j++) {
						checkBox = driver.findElement(By.xpath(pro.getProperty(arrCheck[j])));
						if (checkBox.isSelected()) {
							checkBox.click();
							Thread.sleep(500); // checkbox disabled
						}
					} // arr loop
					Utilities.clickButton("Save", 1000, driver);
					Utilities.clickButton("OK", 1500, driver);
					Thread.sleep(1500);
					flag = true;
					totalFields++;
				}
			}

			if (flag == false) {
				System.out.println("*****[ NOT found any Mandotory/custom field Dimension ]******");
			}
			if (flag == true) {
				System.out.println(
						"*****! Disabled total [" + totalFields + "] Mandotory/custom field Dimension !******");
			}

			Utilities.HoverandClick(pro.getProperty("closeMasterConfig"), driver);
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// ********** >>>>>>>>>>> ToHandle Expand/Collapse Scenario
	// *******************************
	public static void isExpanded(String xpathNeedToExpand, String xpathThatExpanded, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		Utilities.click_element(xpathNeedToExpand, driver);
		WebElement Expanded = null;
		boolean success = false;
		for (int num_try = 1; !success && num_try < 10; num_try++) {
			try {
				Expanded = driver.findElement(By.xpath(xpathThatExpanded));
				Thread.sleep(2000);
				success = true;
			} catch (Exception EX) {
				Thread.sleep(2000);
				Utilities.HoverandClick(xpathNeedToExpand, driver);
				if (num_try == 9) {
					System.out.println("Account is Not Expanded");
					Utilities.handleError(EX, driver);
				}
			}
		} // at the end data has been fully Expanded
	}

}
