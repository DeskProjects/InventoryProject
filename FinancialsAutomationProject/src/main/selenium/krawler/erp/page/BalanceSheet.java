package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.utils.SikulliUtil;

public class BalanceSheet {

	// ************************ Compare Balance Sheet
	// **************************************

	public static String validate_BlanceSheet(WebDriver driver) throws InterruptedException, AWTException, IOException {
		String CurrentYearEarning = null;
		try {
			Properties pro = Utilities.fetchProValue("OR_BalanceSheet.properties");
			WebDriverWait wait = new WebDriverWait(driver, 30);

			Utilities.waitandClick(pro.getProperty("balSheetIcon"), driver);
			Thread.sleep(3000);

			Utilities.clickButton("Fetch", 500, driver);
			Thread.sleep(1000);
			WebElement sheetCheckBox = new WebDriverWait(driver, 50)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("sheetCheckBox"))));

			if (sheetCheckBox.isEnabled()) {
				Thread.sleep(1000);
				Utilities.clickButton("Expand", 1500, driver);
				Thread.sleep(1000);

				WebElement OpeningAmount = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("OpeningAmountXpath"))));
				String sheet1openingAmt = OpeningAmount.getText();

				WebElement PeriodAmount = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PeriodAmountXpath"))));
				String sheet1PeriodAmount = PeriodAmount.getText();

				WebElement Amount = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AmountXpath"))));
				String sheet1Amount = Amount.getText();
				// System.out.println("Data from sheet 1 : >>>>> "+
				// sheet1openingAmt +" <<<< "+ sheet1PeriodAmount +" >>>> "+
				// sheet1Amount + " <<<<<<");

				// ************** move to sheet 2 *********************

				WebElement OpeningAmount2 = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("OpeningAmountXpath2"))));
				String sheet2openingAmt = OpeningAmount2.getText();

				WebElement PeriodAmount2 = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PeriodAmountXpath2"))));
				String sheet2PeriodAmount = PeriodAmount2.getText();

				WebElement Amount2 = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AmountXpath2"))));
				String sheet2Amount = Amount2.getText();
				// System.out.println("Data from sheet 2 : >>>>> "+
				// sheet2openingAmt +" <<<< "+ sheet2PeriodAmount +" >>>> "+
				// sheet2Amount + " <<<<<<");

				if (Amount.isDisplayed() && Amount2.isDisplayed()) {
					assertEquals(sheet1openingAmt, sheet2openingAmt);
					System.out.println(
							"******* Opening ammount matched with UI :[" + sheet2openingAmt + "]*********************");

					assertEquals(sheet1PeriodAmount, sheet2PeriodAmount);
					System.out.println("******* Period ammount matched with UI :[" + sheet2PeriodAmount
							+ "]*********************");

					assertEquals(sheet1Amount, sheet2Amount);
					System.out.println(
							"******* Ammount matched with UI :[" + sheet2openingAmt + "]*********************");
				}

				String xpathExpression = "//*[@id='periodviewnewbsheet']//*[text()='Current Year Earnings']/ancestor::td/following::td[3]/div";
				CurrentYearEarning = driver.findElement(By.xpath(xpathExpression)).getText();
				System.out.println(
						"***************** Test case [Balance sheet comparison] is successfully completed ******************* ");
				Utilities.report_ScreenShot("Balance Sheet", driver);
			} else {
				System.out.println(
						"***************** Balance Sheet Not Loaded Properly [wait for 60 sceonds]  ******************* ");
			}

			Thread.sleep(1000);
			WebElement ClsReport = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseBtn"))));
			ClsReport.click();

		} catch (Exception EX) {
			System.out.println("***************** Test case [Balance sheet comparison] is FAILED ******************* ");
			Utilities.handleError(EX, driver);
		}
		return CurrentYearEarning;
	}

	// **************************** get Current year earning
	// ****************************

	public static String CurrentYearEarnings(WebDriver driver) throws InterruptedException, AWTException, IOException {

		String CurrentYearEarning = null;
		validate_BlanceSheet(driver);
		Thread.sleep(3000);

		try {
			String xpathExpression = "//*[@id='periodviewnewbsheet']//*[text()='Current Year Earnings']/ancestor::td/following::td[3]/div";
			CurrentYearEarning = driver.findElement(By.xpath(xpathExpression)).getText();
		} catch (Exception EXX) {
			EXX.printStackTrace();
		}
		Utilities.refresh();
		return CurrentYearEarning;

	}

	// ********************* EXPORT Balnce Sheet
	// *************************************

	public static void Export_BlanceSheet(WebDriver driver) throws InterruptedException, AWTException, IOException {

		try {
			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");
			Properties pro2 = Utilities.fetchProValue("OR_BalanceSheet.properties");
			String ExportButtonName = "Export ";

			WebDriverWait wait = new WebDriverWait(driver, 30);
			Utilities.HoverandClick(pro2.getProperty("balSheetIcon"), driver);
			Thread.sleep(2000);

			Utilities.clickButton("Fetch", 500, driver);

			Utilities.clickCheckBox(pro2.getProperty("sheetCheckBox"), "uncheck", driver);
			Thread.sleep(1000);

			// Export to CSV Split view
			String allTypeCSV[] = { pro2.getProperty("SplitView"), pro2.getProperty("StandardView") };

			for (int i = 0; i < allTypeCSV.length; i++) {
				Utilities.clickButton(ExportButtonName, 500, driver);
				Utilities.justHover(pro.getProperty("ExportToCSV"), driver);
				Utilities.HoverandClick(allTypeCSV[i], driver);
				SikulliUtil.sikulli_waitClick(driver, "CSVtype");
				// SikulliUtil.sikulli_waitClick(driver,"CloseButton");
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
			}
			System.out.println("*******  CSV Type completed **************");

			// PDF
			String allTypePDF[] = { pro2.getProperty("TreeView"), pro2.getProperty("StandardView") };

			for (int i = 0; i < allTypePDF.length; i++) {
				Utilities.clickButton(ExportButtonName, 500, driver);
				String parentWindow = driver.getWindowHandle();
				Utilities.justHover(pro.getProperty("ExportToPDF"), driver);
				Utilities.HoverandClick(allTypePDF[i], driver);
				SikulliUtil.sikulli_waitClick(driver, "PDFtype");
				Thread.sleep(1500);

				Set s = driver.getWindowHandles(); // this method will gives you
													// the handles of all opened
													// windows
				Iterator ite = s.iterator();
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
				Utilities.sikuliHandleExport(ExportFunction.button_path("BalanceSheetIcon"));
			}
			System.out.println("*******  PDF Type completed **************");

			// Excel
			String allTypeExcel[] = { pro2.getProperty("SplitView"), pro2.getProperty("StandardView") };

			for (int i = 0; i < allTypeExcel.length; i++) {
				Utilities.clickButton(ExportButtonName, 500, driver);
				Utilities.justHover(pro2.getProperty("ExportToExcel"), driver);
				Utilities.HoverandClick(allTypeExcel[i], driver);
				SikulliUtil.sikulli_waitClick(driver, "XLStype");
				// SikulliUtil.sikulli_waitClick(driver,"CloseButton");
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
			}
			System.out.println("*******  Excel Type completed **************");

			Utilities.HoverandClick(pro2.getProperty("CloseBtn"), driver);
			System.out.println("*******  Balance Sheet Exported successfully !!!! **************");
		}

		catch (Exception EX) {
			System.out.println("***************** FAILED to EXPORT BALANCE SHEET Plz Check !!!! ******************* ");
			Utilities.handleError(EX, driver);
		}
	}
}
