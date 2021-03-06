package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TradingAndProfitLoss {

	// ************************ Trading And Profit Loss
	// **************************************

	public static void validate_Plcalculation(String expCurrentYearEarning, WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{
		String CurrentYearEarning = null;
		try {
			Properties pro = Utilities.fetchProValue("OR_TradingAndProfitLoss.properties");

			WebDriverWait wait = new WebDriverWait(driver, 30);
			String LoadingXpath = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";

			WebElement balSheetIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PLCalculationIcon"))));
			balSheetIcon.click();
			Thread.sleep(3000);

			Utilities.clickButton("Fetch", 500, driver);
			Utilities.isElementGone(LoadingXpath, 120, driver);
			WebElement textCurrentEarning = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//b[text()='Current Year Earnings']")));

			if (textCurrentEarning.isDisplayed()) {
				Thread.sleep(2000);
				// toMove focus
				driver.findElement(By.xpath("//input[@name='costCenterId']")).click();

				Utilities.clickButton("Expand", 1000, driver);
				Thread.sleep(300);

				List<WebElement> totalRow = driver
						.findElements(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div/table/tbody/tr/td[1]"));
				int rowCount = totalRow.size();

				for (int i = 1; i <= rowCount; i++) {
					String CuYearEarningCol = driver
							.findElement(By.xpath(
									"*//div[@class='x-grid3-viewport']/div[2]/div/div[" + i + "]/table/tbody/tr/td[1]"))
							.getText();
					if (CuYearEarningCol.equalsIgnoreCase("Current Year Earnings")) {
						CurrentYearEarning = driver.findElement(By.xpath(
								"*//div[@class='x-grid3-viewport']/div[2]/div/div[" + i + "]/table/tbody/tr/td[5]"))
								.getText();
						break;
					}
				}
				assertEquals(expCurrentYearEarning, CurrentYearEarning, ">>>> Not Verified as not matched ");
				Utilities.report_ScreenShot("Trading PL statement", driver);
				System.out.println("**** PL Calculation verified as Balncesheet current earning > ["
						+ expCurrentYearEarning + "] WITH TradingPL earning > [" + CurrentYearEarning + "] *****");
				Utilities.click_element("//*[@id='as__finalStmnt']/a[1]", driver);
			}

			else {
				System.out.println(
						"************************ Trading And Profit Loss NOT LOADED **************************************");
			}

		} catch (Exception EX) {
			System.out.println("***************** Test case [Balance sheet comparison] is FAILED ******************* ");
			Utilities.handleError(EX, driver);
		}
	}

	// ******************** Trading And Profit Loss EXPORT
	// ***********************************

	public static void Export_TradingProfitLoss(WebDriver driver) throws Exception {
		{
			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			WebDriverWait wait = new WebDriverWait(driver, 60);

			String ExportButtonName = "Export ";
			Properties pro2 = Utilities.fetchProValue("OR_TradingAndProfitLoss.properties");
			String reportIcon = pro2.getProperty("PLCalculationIcon");
			String waitForQuickSearch = "//b[text()='Current Year Earnings']";
			String closeReportPage = "//li[@id='as__finalStmnt']/a[1]";
			String parentWindow = driver.getWindowHandle();
			Set s = driver.getWindowHandles();
			Iterator ite = s.iterator();

			try {

				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(reportIcon)));
				element.click();
				Thread.sleep(1000);

				Utilities.clickButton("Fetch", 500, driver);

				try {
					// WebElement resultFound=new WebDriverWait(driver,
					// 30).until(ExpectedConditions.elementToBeClickable(By.xpath(waitForQuickSearch)));
					Thread.sleep(1000);
					clickCheckBoxExport(waitForQuickSearch, "uncheck", driver);

					Utilities.clickButton(ExportButtonName, 500, driver);

					Utilities.HoverandClick(pro.getProperty("ExportToCSV"), driver);
					Thread.sleep(3000);

					Utilities.sikuliHandleExport(button_path("CSVtype"));
					Thread.sleep(1500);
					Utilities.sikuliHandleExport(button_path("CloseButton"));
					Thread.sleep(1000);
					Utilities.sikuliHandleExport(button_path("ClsX"));

					System.out.println("* * * * * * EXPORT for [.CSV] completed successfully * * * * * * *");
					Thread.sleep(1500);

					/*
					 * // PDF Export MONTHLY Trade blance
					 * Utilities.clickButton(ExportButtonName, 500, driver);
					 * String parentWindow=driver.getWindowHandle(); Utilities.
					 * HoverandClick("//span[text()='Export Monthly Trading And Profit Loss']"
					 * , driver); Thread.sleep(15000);
					 * 
					 * Utilities.sikuliHandleExport(button_path("PDFtype") );
					 * Thread.sleep(1500);
					 * 
					 * Set s=driver.getWindowHandles(); //this method will gives
					 * you the handles of all opened windows Iterator
					 * ite=s.iterator();
					 * 
					 * while(ite.hasNext()) { String
					 * childWindow=ite.next().toString();
					 * if(!childWindow.contains(parentWindow)) {
					 * driver.switchTo().window(childWindow);
					 * Thread.sleep(1000); System.out.
					 * println("* * * * * * EXPORT for [PDF] completed successfully * * * * * * *"
					 * ); driver.close();
					 * driver.switchTo().window(parentWindow);
					 * Thread.sleep(1000); } }
					 */
					// Excel
					Utilities.clickButton(ExportButtonName, 500, driver);
					Utilities.HoverandClick(pro.getProperty("ExportToExcel"), driver);

					Utilities.sikuliHandleExport(button_path("XLStype"));
					Thread.sleep(1500);
					Utilities.sikuliHandleExport(button_path("CloseButton"));
					Thread.sleep(1000);
					Utilities.sikuliHandleExport(button_path("ClsX"));
					System.out.println("* * * * * * EXPORT for [.Excel] completed successfully * * * * * * *");
					Thread.sleep(1500);

					// PDF
					Utilities.clickButton(ExportButtonName, 500, driver);

					parentWindow = driver.getWindowHandle();
					Utilities.HoverandClick(pro.getProperty("ExportToPDF"), driver);
					Thread.sleep(3000);

					Utilities.sikuliHandleExport(button_path("PDFtype"));
					Thread.sleep(1000);

					s = driver.getWindowHandles(); // this method will gives you
													// the handles of all opened
													// windows
					ite = s.iterator();

					while (ite.hasNext()) {
						String childWindow = ite.next().toString();
						if (!childWindow.contains(parentWindow)) {
							driver.switchTo().window(childWindow);
							Thread.sleep(1000);
							System.out.println("* * * * * * EXPORT for [PDF] completed successfully * * * * * * *");
							driver.close();
							driver.switchTo().window(parentWindow);
							Thread.sleep(1000);
						}
					}

					element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(closeReportPage)));
					element.click();
					System.out.println(
							"* * * * * * EXPORT for module [Trading & Profit Loss] completed successfully * * * * * * *");

				}

				catch (Exception noData) {
					System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
					Utilities.handleError(noData, driver);
				}
			}

			catch (Exception EX) {
				System.out
						.println("* * * * * * !!!!!!! EXPORT for [Tradind & Profit Loss] FAILED !!!!!! * * * * * * *");
				Utilities.handleError(EX, driver);
			}

		}

	}

	// get button path for Sikulli
	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;

	}

	// Check or Uncheck checkBox button
	public static void clickCheckBoxExport(String checkBoxXpath, String chkOrUnchk, WebDriver driver)
			throws InterruptedException {
		WebElement chkBox = new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(checkBoxXpath)));

		if (chkBox.isEnabled()) {
			Thread.sleep(1300);
			if (chkOrUnchk.equalsIgnoreCase("CHECK")) {
				if (!chkBox.isSelected()) {
					chkBox.click();
					Thread.sleep(1500);
				}
			} else if (chkOrUnchk.equalsIgnoreCase("UNCHECK")) {
				if (chkBox.isSelected()) {
					chkBox.click();
					Thread.sleep(1000);
				}
			}
		}
	}

}
