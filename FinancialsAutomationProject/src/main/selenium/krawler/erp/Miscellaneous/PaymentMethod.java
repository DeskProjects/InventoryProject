package krawler.erp.Miscellaneous;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import krawler.erp.inventory.InvUtilities;
import krawler.erp.page.Utilities;

public class PaymentMethod {

	// *********************************** Create PaymentMethod
	// ****************************************
	public static void create_PaymentMethod(String documentid, String accountName, String showCsCP, String showLoan,
			WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_PaymentMethod.properties");
			driver.navigate().refresh();
			Utilities.isLoadingDisappear(120, driver);
			Utilities.click_element(pro.getProperty("MiscellaneousExpander"), driver);
			Utilities.HoverandClick(pro.getProperty("PaymentMethodLink"), driver);
			Thread.sleep(2000);

			int indOfmethodName = 0, indOfacctName = 0, indOfshow1 = 0, indOfshow2 = 0;
			int totalHeader = Utilities.totalSize("//*[text()='Method Name']/ancestor::tr/td", driver);
			for (int i = 1; i <= totalHeader; i++) {
				String HeaderName = driver
						.findElement(By.xpath("//*[text()='Method Name']/ancestor::tr/td[" + i + "]/div")).getText();
				if (HeaderName.equalsIgnoreCase("Method Name")) {
					indOfmethodName = i;
				}
				if (HeaderName.equalsIgnoreCase("Account Name")) {
					indOfacctName = i;
				}
				if (HeaderName.equalsIgnoreCase("Show in CS/CP")) {
					indOfshow1 = i;
				}
				if (HeaderName.equalsIgnoreCase("Show in Loan")) {
					indOfshow2 = i;
				}
			}

			int totalPresentTax = Utilities.totalSize("//div[@class='x-grid3-viewport']/div[2]/div/div", driver);
			String xpathOfInput = "//*[contains(@class,'x-grid-editor') and contains(@style,'visible')]/input[1]";
			for (int lastRow = 1; lastRow <= totalPresentTax; lastRow++) {
				if (lastRow == totalPresentTax) {
					// Enter Tax Name
					Utilities.click_element("//div[@class='x-grid3-viewport']/div[2]/div/div[" + lastRow
							+ "]/table/tbody/tr/td[" + indOfmethodName + "]/div", driver);
					Utilities.enterTextInDropDown("PayM" + documentid, xpathOfInput, driver);
					// Enter Account Name
					Utilities.click_element("//div[@class='x-grid3-viewport']/div[2]/div/div[" + lastRow
							+ "]/table/tbody/tr/td[" + indOfacctName + "]/div", driver);
					Utilities.enterTextInDropDown(accountName, "//*[@id='accountid']/following::input[1]", driver);
					// Show Cpp
					Utilities.click_element("//div[@class='x-grid3-viewport']/div[2]/div/div[" + lastRow
							+ "]/table/tbody/tr/td[" + indOfshow1 + "]/div", driver);
					Utilities.enterTextandSelect(showCsCP, "//*[@id='autopopulateincpcs']/following::input[1]", driver);
					// Show Cpp
					Utilities.click_element("//div[@class='x-grid3-viewport']/div[2]/div/div[" + lastRow
							+ "]/table/tbody/tr/td[" + indOfshow2 + "]/div", driver);
					Utilities.enterTextandSelect(showLoan, "//*[@id='autopopulateinloan']/following::input[1]", driver);
				}
			}
			Utilities.clickButton("Update", 200, driver);
			Utilities.clickButton("OK", 200, driver);
			Utilities.clickButton("Close", 200, driver);
			System.out.println("Payment Method [" + "PayM" + documentid + "] created !!!");
		} catch (Exception EX) {
			System.out.println("Payment Method [" + "PayM" + documentid + "] is NOT created !!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ********************************* Verification
	// ***********************************

	public static void verify_PaymentMethod(String documentid, String accountName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			final Properties pro = Utilities.fetchProValue("OR_PaymentMethod.properties");
			Utilities.refresh();
			documentid = "PayM" + documentid;
			Utilities.waitandClick(pro.getProperty("MiscellaneousExpander"), driver);
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			Thread.sleep(1000);

			WebElement element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					return webDriverArg.findElement(By.xpath(pro.getProperty("PaymentMethodLink")));
				}
			});
			element.click();
			Thread.sleep(3000);

			List<WebElement> headercol = driver
					.findElements(By.xpath("//div[@class='x-grid3-viewport']/div[1]/div/div/table/thead/tr/td/div"));
			int headerSize = headercol.size();

			int methodNamIndex = 0, accNameIndex = 0, showCSIndex = 0, showLoanIndex = 0;

			for (int headIndex = 1; headIndex <= headerSize; headIndex++) {
				String header = driver.findElement(By.xpath(
						"//div[@class='x-grid3-viewport']/div[1]/div/div/table/thead/tr/td[" + headIndex + "]/div"))
						.getText();
				if (header.equals("Method Name")) {
					methodNamIndex = headIndex;

				} else if (header.equals("Account Name")) {
					accNameIndex = headIndex;
				} else if (header.equals("Show in CS/CP")) {
					showCSIndex = headIndex;
				} else if (header.equals("Show in Loan")) {
					showLoanIndex = headIndex;
				}
			}

			List<WebElement> totalrow = driver
					.findElements(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div"));
			int totalsize = totalrow.size();

			// System.out.println("Total row > "+ totalsize);

			for (int j = 1; j <= totalsize; j++) {
				String verifyPM = driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
						+ "]/table/tbody/tr/td[" + methodNamIndex + "]/div")).getText();
				if (verifyPM.equals(documentid)) {
					assertEquals(documentid,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
									+ "]/table/tbody/tr/td[" + methodNamIndex + "]/div")).getText());
					System.out.println("Payment method name is matched with UI > " + documentid);

					assertEquals(accountName,
							driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
									+ "]/table/tbody/tr/td[" + accNameIndex + "]/div")).getText());
					System.out.println("Account name is matched with UI > " + accountName);
					break;
				}
			}

			System.out.println("Payment Method [" + documentid + "] is Verified !!!");
			Utilities.clickButton("Close", 200, driver);

		} catch (Exception EX) {
			System.out.println("Payment Method [" + documentid + "] is NOT Verified !!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ************************************ Edit & Delete
	// *********************************************

	public static void verify_EditDeletePaymentMethod(String documentid, String editaccountName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			final Properties pro = Utilities.fetchProValue("OR_PaymentMethod.properties");
			Utilities.refresh();
			documentid = "PayM" + documentid;
			Utilities.waitandClick(pro.getProperty("MiscellaneousExpander"), driver);

			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS);
			Thread.sleep(1000);

			WebElement element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					return webDriverArg.findElement(By.xpath(pro.getProperty("PaymentMethodLink")));
				}
			});
			element.click();
			Thread.sleep(3000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					return webDriverArg.findElement(By.xpath(pro.getProperty("editdropDwn")));
				}
			});
			element.click();
			Thread.sleep(2000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					return webDriverArg.findElement(By.xpath(pro.getProperty("editselectAll")));
				}
			});
			element.click();
			Thread.sleep(2000);

			List<WebElement> headercol = driver
					.findElements(By.xpath("//div[@class='x-grid3-viewport']/div[1]/div/div/table/thead/tr/td/div"));
			int headerSize = headercol.size();

			int methodNamIndex = 0, accNameIndex = 0, showCSIndex = 0, showLoanIndex = 0;

			for (int headIndex = 1; headIndex <= headerSize; headIndex++) {
				String header = driver.findElement(By.xpath(
						"//div[@class='x-grid3-viewport']/div[1]/div/div/table/thead/tr/td[" + headIndex + "]/div"))
						.getText();
				if (header.equals("Method Name")) {
					methodNamIndex = headIndex;

				} else if (header.equals("Account Name")) {
					accNameIndex = headIndex;
				} else if (header.equals("Show in CS/CP")) {
					showCSIndex = headIndex;
				} else if (header.equals("Show in Loan")) {
					showLoanIndex = headIndex;
				}
			}

			List<WebElement> totalrow = driver
					.findElements(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div"));
			int totalsize = totalrow.size();
			int foundIndex = 0;
			Robot r = new Robot();
			// System.out.println("Total row > "+ totalsize);

			for (int j = 1; j <= totalsize; j++) {
				String editPM = driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
						+ "]/table/tbody/tr/td[" + methodNamIndex + "]/div")).getText();
				if (editPM.equals(documentid)) {
					Thread.sleep(1000);
					foundIndex = j;
					driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
							+ "]/table/tbody/tr/td[" + accNameIndex + "]/div")).click();
					Thread.sleep(2000);
					r.keyPress(KeyEvent.VK_CONTROL);
					r.keyPress(KeyEvent.VK_A);
					r.keyRelease(KeyEvent.VK_A);
					r.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(1000);
					WebElement acname = driver
							.findElement(By.xpath("//input[@name='accountid']/following-sibling::input"));
					acname.sendKeys(editaccountName);
					Thread.sleep(2000);
					r.keyPress(KeyEvent.VK_ENTER);
					r.keyRelease(KeyEvent.VK_ENTER);

					Thread.sleep(2000);
					driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
							+ "]/table/tbody/tr/td[" + showCSIndex + "]/div")).click();
					Thread.sleep(2000);
					r.keyPress(KeyEvent.VK_CONTROL);
					r.keyPress(KeyEvent.VK_A);
					r.keyRelease(KeyEvent.VK_A);
					r.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(1000);
					WebElement coshow = driver
							.findElement(By.xpath("//input[@name='autopopulateincpcs']/following-sibling::input"));
					coshow.sendKeys("No");
					Thread.sleep(2000);
					r.keyPress(KeyEvent.VK_ENTER);
					r.keyRelease(KeyEvent.VK_ENTER);
					r.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);

					Thread.sleep(2000);
					r.keyPress(KeyEvent.VK_CONTROL);
					r.keyPress(KeyEvent.VK_A);
					r.keyRelease(KeyEvent.VK_A);
					r.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(1000);
					WebElement loan = driver
							.findElement(By.xpath("//input[@name='autopopulateinloan']/following-sibling::input"));
					loan.sendKeys("No");
					Thread.sleep(2000);
					r.keyPress(KeyEvent.VK_ENTER);
					r.keyRelease(KeyEvent.VK_ENTER);

					// remove focus
					driver.findElement(By.xpath(
							"//div[@class='x-grid3-viewport']/div[2]/div/div[" + j + "]/table/tbody/tr/td[6]/div"))
							.click();
					Thread.sleep(1000);
				}
			}

			Utilities.clickButton("Update", 200, driver);
			Utilities.clickButton("OK", 200, driver);
			Thread.sleep(2500);

			try {
				String editverifyPM = driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div["
						+ foundIndex + "]/table/tbody/tr/td[" + accNameIndex + "]/div")).getText();
				if (editverifyPM.equals(editaccountName)) {
					System.out.println("Edit test case for [" + documentid + "] is Pass !!");
				}
			} catch (Exception Editfaul) {
				System.out.println("Edit test case for [" + documentid + "] is FAIL !!!!!!!!!");
			}

			Utilities.clickButton("Close", 200, driver);

			// **************************Handle
			// Delete*****************************

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					return webDriverArg.findElement(By.xpath(pro.getProperty("PaymentMethodLink")));
				}
			});
			element.click();
			Thread.sleep(2000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					return webDriverArg.findElement(By.xpath(pro.getProperty("editdropDwn")));
				}
			});
			element.click();
			Thread.sleep(2000);

			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver webDriverArg) {
					return webDriverArg.findElement(By.xpath(pro.getProperty("deleteAll")));
				}
			});
			element.click();
			Thread.sleep(2000);

			for (int j = 1; j <= totalsize; j++) {
				String delPM = driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div[" + j
						+ "]/table/tbody/tr/td[" + methodNamIndex + "]/div")).getText();
				if (delPM.equals(documentid)) {
					Thread.sleep(1000);
					driver.findElement(By.xpath(
							"//div[@class='x-grid3-viewport']/div[2]/div/div[" + j + "]/table/tbody/tr/td[7]/div/div"))
							.click();
					Utilities.clickButton("Yes", 400, driver);
					Thread.sleep(1000);
					Utilities.clickButton("Update", 400, driver);
					Utilities.clickButton("OK", 400, driver);
					break;
				}
			}

			Thread.sleep(2000);
			try {
				List<WebElement> aftrDelrow = driver
						.findElements(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div"));
				int aftrDelsize = aftrDelrow.size();
				for (int i = 1; i <= aftrDelsize; i++) {
					String delcheckPM = driver.findElement(By.xpath("//div[@class='x-grid3-viewport']/div[2]/div/div["
							+ i + "]/table/tbody/tr/td[" + methodNamIndex + "]/div")).getText();
					if (delcheckPM.equals(documentid)) {
						System.out.println("DELETE test case for [" + documentid + "] is FAIL !!!!!!!!!!!!!!");
					}
				}
			} catch (Exception delFail) {
				System.out.println("DELETE test case for [" + documentid + "] is FAIL !!!!!!!!!");
			}

			System.out.println("DELETE test case for [" + documentid + "] is Pass !!");
			Utilities.clickButton("Close", 200, driver);

		}

		catch (Exception EX) {
			System.out.println("Payment Method Edit-Delete test case for [" + documentid + "] is Fail !!!");
			Utilities.handleError(EX, driver);
		}
	}
}
