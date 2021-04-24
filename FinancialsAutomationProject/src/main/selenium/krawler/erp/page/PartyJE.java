package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PartyJE {

	public static void create_partyJE(String documentid, String customerid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Properties NormalJE = Utilities.fetchProValue("OR_PartyJE.properties");
			documentid = "Pje" + documentid;

			Utilities.waitandClick(NormalJE.getProperty("ClcikOnJEIcon"), driver);
			Utilities.click_element(NormalJE.getProperty("SelectPartyJEOption"), driver);
			Utilities.HoverandClick(NormalJE.getProperty("ClickOnSubmit"), driver);
			Thread.sleep(2000);

			Utilities.enterTextandSelect("NA", NormalJE.getProperty("SequenceFormat"), driver);
			Utilities.HoverandClick("//*[@name='memo']", driver);
			Utilities.enterTextNormalBox(documentid, NormalJE.getProperty("EnterJENo."), driver);

			int headerSize = Utilities.totalSize("//*[text()='Account']/ancestor::tr/td/div", driver);
			int indOfAcc = 0, indOfdebit = 0, indOfcredit = 0;

			for (int i = 1; i <= headerSize; i++) {
				String headerName = driver.findElement(By.xpath("//*[text()='Account']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				if (headerName.equalsIgnoreCase("Account")) {
					indOfAcc = i;
				} else if (headerName.equalsIgnoreCase("Debit Amount")) {
					indOfdebit = i;
				} else if (headerName.equalsIgnoreCase("Credit Amount")) {
					indOfcredit = i;
				}
			}

			// enter Debit Account
			Utilities.HoverandClick(
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfAcc + "]/div",
					driver);
			Utilities.enterTextandSelect(customerid, "//*[@id='accountid']/following::input[1]", driver);
			Utilities.clickAndEnterText("10",
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[1]/table/tbody/tr/td["
							+ indOfdebit + "]/div",
					driver);
			Utilities.HoverandClick("//*[@name='memo']", driver);
			// enter Credit Account
			Utilities.HoverandClick(
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfAcc + "]/div",
					driver);
			Utilities.enterTextandSelect(vendorid, "//*[@id='accountid']/following::input[1]", driver);
			Utilities.clickAndEnterText("10",
					"//*[text()='Account']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr/td["
							+ indOfcredit + "]/div",
					driver);
			Utilities.HoverandClick("//*[@name='memo']", driver);

			Utilities.HoverandClick(NormalJE.getProperty("ClickOnSaveButtton"), driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.HoverandClick(NormalJE.getProperty("CloseFormPartyJE"), driver);

			System.out.println("Party JE [" + documentid + "] created Success !!!");
		} catch (Exception EX) {
			System.out.println("Party JE [" + documentid + "] NOT created !!!");
			Utilities.handleError(EX, driver);
		}
	}

	// ************************************************************Verification*********************************************************************

	public static void verify_partyJE(String documentID, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_PartyJE.properties");
			documentID = "Pje" + documentID;

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("JournalEntryReport"))));
			driver.findElement(By.xpath(pro.getProperty("JournalEntryReport"))).click();
			Thread.sleep(5000);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentID);
			search.sendKeys(Keys.TAB);

			WebElement fetchbtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("FetchButton"))));
			fetchbtn.click();
			Thread.sleep(2000);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg24NormalJournalEntryDetails']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg24NormalJournalEntryDetails']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				// System.out.println(header);
				if (header.equals("Entry Number")) {
					assertEquals(documentID,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg24NormalJournalEntryDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("JE Number Verified");
				}

				else if (header.equals("Description")) {
					assertEquals("Party Journal Entry",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg24NormalJournalEntryDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
					System.out.println("Party Journal Entry");
				}
			}
			System.out.println("Party JE [" + documentID + "] verified Success !!!");
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseJournalEntryReport"))));
			driver.findElement(By.xpath(pro.getProperty("CloseJournalEntryReport"))).click();
			Thread.sleep(500);

		} catch (Exception EX) {
			System.out.println("Party JE [" + documentID + "] NOT verified !!!");
			Utilities.handleError(EX, driver);
		}

	}

	public static void CopyEditDelete_partyJE(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		// Add code if We want to use EXTRA Account while Editing
	}

}
