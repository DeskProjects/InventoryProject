package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ImportCustomer {

	public static void import_customer(String customer, String fileType, String dateFormat, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ImportCustomer.properties");
			String filPathXLS = Utilities.importFile("ImportCustomerExl.xlsx");
			String filPathCSV = Utilities.importFile("ImportCustomerCsv.csv");
			String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";
			WebDriverWait wait = new WebDriverWait(driver, 120);

			Utilities.waitandClick(pro.getProperty("customerReportIcon"), driver);
			Utilities.isElementGone(xpathOfLoading, 180, driver);
			Utilities.clickButton("Import", 1000, driver);

			if (fileType.equalsIgnoreCase("XLS")) {
				Utilities.justHover(pro.getProperty("HoverImportCustomer"), driver);
				Utilities.click_element(pro.getProperty("ImportXLFile"), driver);
				Utilities.enterTextNormalBox(filPathXLS, pro.getProperty("browseBtn"), driver);
				Utilities.click_element(pro.getProperty("NextBtn"), driver);
				Utilities.click_element(pro.getProperty("NextBtn2"), driver);
			}

			else if (fileType.equalsIgnoreCase("CSV")) {
				Utilities.justHover(pro.getProperty("HoverImportCustomer"), driver);
				Utilities.click_element(pro.getProperty("ImportCSVFile"), driver);
				Utilities.enterTextNormalBox(filPathCSV, pro.getProperty("browseBtn"), driver);
				Utilities.enterTextandSelect(dateFormat, "//input[@id='dateFormat']/following::input[1]", driver);
				Utilities.click_element(pro.getProperty("NextBtn"), driver);
			}
			Utilities.click_element("//span[contains(@style,'color:red') and text()='Customer Name']", driver); // just
																												// to
																												// check
																												// wether
																												// loaded
																												// or
																												// mot
			Utilities.clickButton("Auto Map Columns", 500, driver);
			Utilities.clickButton("Analyze Data", 1000, driver);

			String isReadytoImport = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("checkImportReady"))))
					.getText();
			// System.out.println(isReadytoImport);
			double errorOrnot = Utilities.getIntegerFrmString(isReadytoImport);
			// System.out.println(errorOrnot);

			if (errorOrnot > 0) {
				// ERROR while import
				int totalProd = Utilities.totalSize(pro.getProperty("totalAddedCust"), driver);
				for (int i = 1; i <= totalProd; i++) {
					driver.findElement(By
							.xpath("//div[text()='Row No.']/ancestor::div[@class='x-grid3-header']/following-sibling::div/div/div["
									+ i + "]/table/tbody/tr/td[1]/div"))
							.click();
					Thread.sleep(1000);
					String invalidRec = driver
							.findElement(By.xpath("//b[contains(text(),'Validation Details')]/parent::*")).getText();
					System.out.println(invalidRec);
				}
				System.out.println(
						"* * * * * * Customer [" + fileType + "] file is NOT imported Plz check Log!!!!* * * * * * ");
				driver.navigate().refresh();
				Assert.assertTrue(false);
			}
			if (errorOrnot == 0) {
				Utilities.clickButton("Import Data", 1000, driver);
				Utilities.click_element("//button[text()='View Import Log']", driver);
				Thread.sleep(1500);
				String importLogMsg = wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("importLogMessage"))))
						.getText();
				if (importLogMsg.equalsIgnoreCase("All records are imported successfully.")) {
					System.out.println("* * * * * * Customer [" + fileType + "] file is Imported!!!!* * * * * * ");
				} else if (importLogMsg.contains("Failed to import")) {
					System.out.println("* * * * * * Customer [" + fileType
							+ "] file is NOT imported Plz check Log!!!!* * * * * * ");
					driver.navigate().refresh();
					Assert.assertTrue(false);
				} else if (importLogMsg.contains("Pending")) {
					System.out.println("* * * * * * Customer [" + fileType
							+ "] file is imported but in Pending Plz check after some time !!!!* * * * * * ");
				}
			}
			driver.navigate().refresh();
			Utilities.isLoadingDisappear(120, driver);
		} catch (Exception EX) {
			System.out.println("* * * * * * Customer [" + fileType + "] file is NOT imported !!!!* * * * * * ");
			Utilities.handleError(EX, driver);
		}
	}

	// ************* Import Customer verification *********************

	public static void importCustomer_validation(String custIDwithComma, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			Properties pro = Utilities.fetchProValue("OR_ImportCustomer.properties");
			WebDriverWait wait = new WebDriverWait(driver, 60);
			String[] expectedID = custIDwithComma.split(",");

			WebElement createcustomerIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("customerReportIcon"))));
			createcustomerIcon.click();
			Thread.sleep(1500);

			// add this before QUICK SEARCh
			WebElement longwait = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//div[@id='CustomerDetails']/div/div/div/div/div/div[2]/div/div[2]/div/div/div/div/div/div[2]/div/div[1]/table/tbody/tr/td[1]")));
			if (longwait.isEnabled()) {
				Thread.sleep(2000);

				List<WebElement> totlProd = driver.findElements(By.xpath(
						"//div[@id='CustomerDetails']//div[@class='x-grid3-viewport']/div[2]/div/div/table/tbody"));
				int totalProductCnt = totlProd.size();

				for (int i = 1; i <= totalProductCnt; i++) {
					for (int k = 0; k < expectedID.length; k++) {
						String check = driver.findElement(
								By.xpath("//div[@id='CustomerDetails']//div[@class='x-grid3-viewport']/div[2]/div/div["
										+ i + "]/table/tbody/tr/td[3]/div/a"))
								.getText();
						if (check.equalsIgnoreCase(expectedID[k])) {
							String cusName = driver.findElement(By
									.xpath("//div[@id='CustomerDetails']//div[@class='x-grid3-viewport']/div[2]/div/div["
											+ i + "]/table/tbody/tr/td[4]/div/div"))
									.getText();
							System.out.println(
									"*** Matched Customer ID :[" + check + "] & Name as [" + cusName + "] ***");
						}
					}

				}
			}
			String closeTab = "//*[@id='as__mainCustomerDetails']/a[1]";
			Utilities.HoverandClick(closeTab, driver);
		} catch (Exception EX) {
			System.out.println("* * * * * * Customer file is NOT imported !!!!* * * * * * ");
			Utilities.handleError(EX, driver);
		}

	}

}
