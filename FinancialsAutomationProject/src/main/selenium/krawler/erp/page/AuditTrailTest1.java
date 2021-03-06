package krawler.erp.page;

import java.awt.AWTException;
import java.awt.Window;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.http.auth.AUTH;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import krawler.erp.page.Utilities;
import krawler.erp.testCases.SmokeTestSuiteSG.B.BaseSetUp;

public class AuditTrailTest1 extends BaseSetUp {

	// Create entry for PR/VQ/PO/PO(EXP)/SO Modules in AuditTrail

	public static void Audit_testCreate1(String documentID, String moduleName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		Properties AuditTrail = Utilities.fetchProValue("OR_AuditTrail.properties");
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAudit"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAudit"))).click();
			System.out.println("clicked on Audit Trail");
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnSearch"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnSearch"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("EnterDocNo"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("EnterDocNo"))).sendKeys(documentID);
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnStartDateSelector"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("StartToday"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("FetchButton"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("FetchButton"))).click();
			Thread.sleep(5000);

			String userName = driver.findElement(By.xpath(AuditTrail.getProperty("Username"))).getText();

			Thread.sleep(5000);
			List<WebElement> total = driver.findElements(
					By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div"));
			int totalfound = total.size();

			String a3 = "User " + userName + " has added new " + moduleName + " " + documentID;
			String a5 = "User " + userName + " has deleted " + moduleName + " Permanently " + "Copy" + documentID;
			String a7 = "User " + userName + " has updated " + moduleName + " " + "Copy" + documentID;
			String a9 = "User " + userName + " has added new " + moduleName + " " + "Copy" + documentID;
			String a1 = "User " + userName + " has deleted a " + moduleName + " Permanently " + "Copy" + documentID;

			for (int i = 1; i <= totalfound; i++) {

				String line1 = driver.findElement(
						By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[2]/div/div"))
						.getText();

				if (line1.matches(a5) || line1.matches(a1)) {
					System.out.println("Module " + moduleName + " is Deleted and entry is captured in Audit Trail : "
							+ "Copy" + documentID);
				}

				if (line1.contains(a7)) {
					System.out.println("Module " + moduleName + " is Edited and entry is captured in Audit Trail : "
							+ "Copy" + documentID);

				}

				if (line1.matches(a9))

				{
					System.out.println("Module " + moduleName + " is Copied and entry is captured in Audit Trail : "
							+ "Copy" + documentID);
				}

				if (line1.matches(a3)) {

					System.out.println("Module " + moduleName + " is Created and entry is captured in Audit Trail : "
							+ documentID);
				}

			}

			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("CloseAudittab"))))
					.click();

			Thread.sleep(5000);

		}

		catch (Exception EX1) {
			EX1.printStackTrace();
			Utilities.handleError(EX1, driver);
		}
	}

	// Create entry for Customer Quotation Module in AuditTrail

	public static void Audit_testCreate2(String documentID, String moduleName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		Properties AuditTrail = Utilities.fetchProValue("OR_AuditTrail.properties");
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAudit"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAudit"))).click();
			System.out.println("clicked on Audit Trail");
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnSearch"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnSearch"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("EnterDocNo"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("EnterDocNo"))).sendKeys(documentID);
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnStartDateSelector"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("StartToday"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("FetchButton"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("FetchButton"))).click();
			Thread.sleep(5000);

			String userName = driver.findElement(By.xpath(AuditTrail.getProperty("Username"))).getText();

			Thread.sleep(5000);
			List<WebElement> total = driver.findElements(
					By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div"));
			int totalfound = total.size();

			String b1 = "User " + userName + " has added new " + moduleName + " " + documentID + " "
					+ "with Sequence Format NA";
			String b2 = "User " + userName + " has deleted a " + moduleName + " Permanently " + "Copy" + documentID;
			String b3 = "User " + userName + " has updated " + moduleName + " " + "Copy" + documentID + " "
					+ "with Sequence Format NA";
			String b4 = "User " + userName + " has added new " + moduleName + " " + "Copy" + documentID + " "
					+ "with Sequence Format NA";

			for (int i = 1; i <= totalfound; i++) {

				String line1 = driver.findElement(
						By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[2]/div/div"))
						.getText();

				if (line1.matches(b2)) {
					System.out.println("Module " + moduleName + " is Deleted and entry is captured in Audit Trail : "
							+ "Copy" + documentID);
				}

				if (line1.contains(b3)) {
					System.out.println("Module " + moduleName + " is Edited and entry is captured in Audit Trail : "
							+ "Copy" + documentID);

				}

				if (line1.matches(b4))

				{
					System.out.println("Module " + moduleName + " is Copied and entry is captured in Audit Trail : "
							+ "Copy" + documentID);
				}

				if (line1.matches(b1)) {

					System.out.println("Module " + moduleName + " is Created and entry is captured in Audit Trail : "
							+ documentID);
				}

			}

			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("CloseAudittab"))))
					.click();

			Thread.sleep(5000);

		}

		catch (Exception EX1) {
			EX1.printStackTrace();
			Utilities.handleError(EX1, driver);
		}
	}

	// Cash Purchase , CP Expense , Vendor Invoice , Vendor Invoice Exp , Csh
	// Sales , Sales Invoice

	public static void Audit_testCreate3(String documentID, String moduleName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		Properties AuditTrail = Utilities.fetchProValue("OR_AuditTrail.properties");
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAudit"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAudit"))).click();
			System.out.println("clicked on Audit Trail");
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnSearch"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnSearch"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("EnterDocNo"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("EnterDocNo"))).sendKeys(documentID);
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnStartDateSelector"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("StartToday"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("FetchButton"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("FetchButton"))).click();
			Thread.sleep(5000);

			String userName = driver.findElement(By.xpath(AuditTrail.getProperty("Username"))).getText();

			Thread.sleep(5000);
			List<WebElement> total = driver.findElements(
					By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div"));
			int totalfound = total.size();

			String b5 = "User " + userName + " has added new " + moduleName + " " + documentID + " " + "with JE No:"; // original

			String b9 = "User " + userName + " has added new " + moduleName + " " + documentID; // copy
																								// cash
																								// sales

			String b14 = "User " + userName + " has added new " + moduleName + " " + documentID + "withJE No:"; // sales
																												// Invoice
																												// copy

			String b6 = "User " + userName + " has deleted " + moduleName + " Permanently " + "Copy" + documentID
					+ " along with the JE No. "; // original

			String b10 = "User " + userName + " has deleted " + moduleName + " Permanently. " + "Copy" + documentID
					+ " along with the JE No. "; // cash sales delete.

			String b13 = "User " + userName + " has deleted Sales Invoice Permanently " + "Copy" + documentID
					+ " along with the JE No. "; // Sales Invoice Delete Entry

			String b7 = "User " + userName + " has updated " + moduleName + " " + "Copy" + documentID + " "
					+ "with JE No:"; // original

			String b11 = "User " + userName + " has updated " + moduleName + " " + "Copy" + documentID; // edit
																										// cash
																										// sales

			String b15 = "User " + userName + " has updated " + moduleName + " " + "Copy" + documentID + "with JE No:"; // Sales
																														// Invoice
																														// Edit

			String b8 = "User " + userName + " has added new " + moduleName + " " + documentID + " " + "with JE No:";// original5

			String b12 = "User " + userName + " has added new " + moduleName + " " + documentID; // create
																									// cash
																									// sales

			String b16 = "User " + userName + " has added new " + moduleName + " " + documentID + "with JE No:"; // create
																													// Sales
																													// Invoice

			Thread.sleep(5000);

			for (int i = 1; i <= totalfound; i++) {
				String line1 = driver.findElement(
						By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[2]/div/div"))
						.getText();

				if (line1.contains(b6) || line1.contains(b10) || line1.contains(b13)) {
					System.out.println("Module " + moduleName + " is Deleted and entry is captured in Audit Trail : "
							+ "Copy" + documentID);
				}

				if (line1.contains(b7) || line1.contains(b11) || line1.contains(b15)) {
					System.out.println("Module " + moduleName + " is Edited and entry is captured in Audit Trail : "
							+ "Copy" + documentID);

				}

				if (line1.contains(b8) || line1.contains(b9) || line1.contains(b14))

				{
					System.out.println("Module " + moduleName + " is Copied and entry is captured in Audit Trail : "
							+ "Copy" + documentID);
				}

				if (line1.contains(b5) || line1.contains(b12) || line1.contains(b16)) {

					System.out.println("Module " + moduleName + " is Created and entry is captured in Audit Trail : "
							+ documentID);
				}

			}

			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("CloseAudittab"))))
					.click();

			Thread.sleep(5000);
		}

		catch (Exception EX1) {
			EX1.printStackTrace();
			Utilities.handleError(EX1, driver);
		}
	}

	// Customer Master

	public static void Audit_testCreate4(String customerid, String moduleName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		Properties AuditTrail = Utilities.fetchProValue("OR_AuditTrail.properties");
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAudit"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAudit"))).click();
			System.out.println("clicked on Audit Trail");
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnSearch"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnSearch"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("EnterDocNo"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("EnterDocNo"))).sendKeys(customerid);
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnStartDateSelector"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("StartToday"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("FetchButton"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("FetchButton"))).click();
			Thread.sleep(5000);

			String userName = driver.findElement(By.xpath(AuditTrail.getProperty("Username"))).getText();

			Thread.sleep(5000);
			List<WebElement> total = driver.findElements(
					By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div"));
			int totalfound = total.size();

			String cust1 = "User " + userName + " has deleted a Customer"; // customer
																			// delete
			String cust2 = "User " + userName + " has updated customer"; // customer
																			// edit
			String cust3 = "User " + userName + " has added new customer"; // customer
																			// create/copy

			for (int i = 1; i <= totalfound; i++) {

				String line1 = driver.findElement(
						By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[2]/div/div"))
						.getText();

				if (line1.contains(cust1)) {
					System.out.println("Module " + moduleName + " is Deleted and entry is captured in Audit Trail : "
							+ "Copy" + customerid);
				}

				if (line1.contains(cust2)) {
					System.out.println("Module " + moduleName + " is Edited and entry is captured in Audit Trail : "
							+ "Copy" + customerid);

				}

				if (line1.contains(cust3) && line1.contains("Copy"))

				{
					System.out.println("Module " + moduleName + " is Copied and entry is captured in Audit Trail : "
							+ "Copy" + customerid);
				}

				if (line1.contains(cust3) && !line1.contains("Copy")) {

					System.out.println("Module " + moduleName + " is Created and entry is captured in Audit Trail : "
							+ customerid);
				}

			}

			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("CloseAudittab"))))
					.click();

			Thread.sleep(5000);

		}

		catch (Exception EX1) {
			EX1.printStackTrace();
			Utilities.handleError(EX1, driver);
		}
	}

	// Vendor Master

	public static void Audit_testCreate5(String vendorid, String moduleName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		Properties AuditTrail = Utilities.fetchProValue("OR_AuditTrail.properties");
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAudit"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAudit"))).click();
			System.out.println("clicked on Audit Trail");
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnSearch"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnSearch"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("EnterDocNo"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("EnterDocNo"))).sendKeys(vendorid);
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnStartDateSelector"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("StartToday"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("FetchButton"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("FetchButton"))).click();
			Thread.sleep(5000);

			String userName = driver.findElement(By.xpath(AuditTrail.getProperty("Username"))).getText();

			Thread.sleep(5000);
			List<WebElement> total = driver.findElements(
					By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div"));
			int totalfound = total.size();

			String vend1 = "User " + userName + " has deleted a Vendor"; // Vendor
																			// delete
			String vend2 = "User " + userName + " has updated vendor"; // Vendor
																		// edit
			String vend3 = "User " + userName + " has added new vendor"; // Vendor
																			// create/copy

			for (int i = 1; i <= totalfound; i++) {

				String line1 = driver.findElement(
						By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[2]/div/div"))
						.getText();

				if (line1.contains(vend1)) {
					System.out.println("Module " + moduleName + " is Deleted and entry is captured in Audit Trail : "
							+ "Copy" + vendorid);
				}

				if (line1.contains(vend2)) {
					System.out.println("Module " + moduleName + " is Edited and entry is captured in Audit Trail : "
							+ "Copy" + vendorid);

				}

				if (line1.contains(vend3) && line1.contains("Copy"))

				{
					System.out.println("Module " + moduleName + " is Copied and entry is captured in Audit Trail : "
							+ "Copy" + vendorid);
				}

				if (line1.contains(vend3) && !line1.contains("Copy")) {

					System.out.println(
							"Module " + moduleName + " is Created and entry is captured in Audit Trail : " + vendorid);
				}

			}

			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("CloseAudittab"))))
					.click();

			Thread.sleep(5000);

		}

		catch (Exception EX1) {
			EX1.printStackTrace();
			Utilities.handleError(EX1, driver);
		}
	}

	// Product Master

	public static void Audit_testCreate6(String productid, String moduleName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		Properties AuditTrail = Utilities.fetchProValue("OR_AuditTrail.properties");
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAudit"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAudit"))).click();
			System.out.println("clicked on Audit Trail");
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnSearch"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnSearch"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("EnterDocNo"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("EnterDocNo"))).sendKeys(productid);
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnStartDateSelector"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("StartToday"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("FetchButton"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("FetchButton"))).click();
			Thread.sleep(5000);

			String userName = driver.findElement(By.xpath(AuditTrail.getProperty("Username"))).getText();

			Thread.sleep(5000);
			List<WebElement> total = driver.findElements(
					By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div"));
			int totalfound = total.size();

			String pro1 = "User " + userName + " has deleted Product"; // Product
																		// delete
			String pro2 = "User " + userName + " has updated Product"; // Product
																		// edit
			String pro3 = "User " + userName + " has added Product"; // Product
																		// create/copy

			for (int i = 1; i <= totalfound; i++) {
				String line1 = driver.findElement(
						By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[2]/div/div"))
						.getText();

				if (line1.contains(pro1)) {
					System.out.println("Module " + moduleName + " is Deleted and entry is captured in Audit Trail : "
							+ "Copy" + productid);
				}

				if (line1.contains(pro2)) {
					System.out.println("Module " + moduleName + " is Edited and entry is captured in Audit Trail : "
							+ "Copy" + productid);

				}

				if (line1.contains(pro3) && line1.contains("Copy"))

				{
					System.out.println("Module " + moduleName + " is Copied and entry is captured in Audit Trail : "
							+ "Copy" + productid);
				}

				if (line1.contains(pro3) && !line1.contains("Copy")) {

					System.out.println(
							"Module " + moduleName + " is Created and entry is captured in Audit Trail : " + productid);
				}

			}

			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("CloseAudittab"))))
					.click();

			Thread.sleep(5000);

		} catch (Exception EX1) {
			EX1.printStackTrace();
			Utilities.handleError(EX1, driver);
		}
	}

	// Goods Receipt and Delivery Order without JE

	public static void Audit_testCreate7(String documentID, String moduleName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		Properties AuditTrail = Utilities.fetchProValue("OR_AuditTrail.properties");
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAudit"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAudit"))).click();
			System.out.println("clicked on Audit Trail");
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnSearch"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnSearch"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("EnterDocNo"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("EnterDocNo"))).sendKeys(documentID);
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnStartDateSelector"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("StartToday"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("FetchButton"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("FetchButton"))).click();
			Thread.sleep(5000);

			String userName = driver.findElement(By.xpath(AuditTrail.getProperty("Username"))).getText();

			Thread.sleep(5000);
			List<WebElement> total = driver.findElements(
					By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div"));
			int totalfound = total.size();

			String gr1 = "User " + userName + " has added new " + moduleName + " " + documentID; // create
																									// Goods
																									// receipt/DO

			String gr4 = "User " + userName + " has added new " + moduleName + " " + "Copy" + documentID; // Goods
																											// receipt
																											// Copy/DO

			String gr2 = "User " + userName + " has deleted " + moduleName + " Permanently "; // Delete
																								// Goods
																								// receipt/DO

			String gr3 = "User " + userName + " has updated " + moduleName + " " + "Copy" + documentID; // Edit
																										// GR/DO

			Thread.sleep(5000);

			for (int i = 1; i <= totalfound; i++) {
				String line1 = driver.findElement(
						By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[2]/div/div"))
						.getText();

				if (line1.contains(gr2)) {
					System.out.println("Module " + moduleName + " is Deleted and entry is captured in Audit Trail : "
							+ "Copy" + documentID);
				}

				if (line1.contains(gr3)) {
					System.out.println("Module " + moduleName + " is Edited and entry is captured in Audit Trail : "
							+ "Copy" + documentID);

				}

				if (line1.contains(gr4))

				{
					System.out.println("Module " + moduleName + " is Copied and entry is captured in Audit Trail : "
							+ "Copy" + documentID);
				}

				if (line1.contains(gr1)) {

					System.out.println("Module " + moduleName + " is Created and entry is captured in Audit Trail : "
							+ documentID);
				}

			}

			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("CloseAudittab"))))
					.click();

			Thread.sleep(5000);
		}

		catch (Exception EX1) {
			EX1.printStackTrace();
			Utilities.handleError(EX1, driver);
		}
	}

	// Debit Purchase Invoice & Credit Sales Invoice
	public static void Audit_testCreate8(String documentID, String Account, String moduleName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		Properties AuditTrail = Utilities.fetchProValue("OR_AuditTrail.properties");
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAudit"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAudit"))).click();
			System.out.println("clicked on Audit Trail");
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnSearch"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnSearch"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("EnterDocNo"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("EnterDocNo"))).sendKeys(documentID);
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnStartDateSelector"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("StartToday"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("FetchButton"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("FetchButton"))).click();
			Thread.sleep(5000);

			String userName = driver.findElement(By.xpath(AuditTrail.getProperty("Username"))).getText();

			Thread.sleep(5000);
			List<WebElement> total = driver.findElements(
					By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div"));
			int totalfound = total.size();

			System.out.println(totalfound);

			String dnpi1 = "User " + userName + " has added a " + moduleName + " " + documentID + " "
					+ "with Vendor Invoice(s)"; // create debitnote with Vendor
												// Invoice

			String cnsi1 = "User " + userName + " has added a " + moduleName + " " + documentID + " "
					+ "with Customer Invoice"; // create credit note with sales
												// Invoice

			String dnpi2 = "User " + userName + " has updated a " + moduleName + " " + "Copy" + documentID + " "
					+ "with Vendor Invoice(s)"; // edit debitnote with Vendor
												// Invoice

			String cnsi2 = "User " + userName + " has updated a " + moduleName + " " + "Copy" + documentID + " "
					+ "with Customer Invoice"; // edit creditnote with customer
												// invoice

			String dnpi3 = "User " + userName + " has deleted " + moduleName + " Permanently " + documentID
					+ " along with the JE No. "; // deleted debitnote with
													// Vendor Invoice

			String cnsi3 = "User " + userName + " has deleted " + moduleName + " Permanently " + documentID
					+ " along with the JE No. "; // deleted creditnote with
													// customer Invoice

			System.out.println(dnpi3);

			Thread.sleep(5000);

			if (Account.equals(vendorid)) {

				for (int i = 1; i <= totalfound; i++) {
					String line1 = driver.findElement(By
							.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
									+ i + "]/table/tbody/tr/td[2]/div/div"))
							.getText();

					if (line1.contains(dnpi3)) {
						System.out.println("Module " + moduleName
								+ " is Deleted and entry is captured in Audit Trail : " + documentID);

					}

					if (line1.contains(dnpi2)) {
						System.out.println("Module " + moduleName + " is Edited and entry is captured in Audit Trail : "
								+ documentID);

					}

					if (line1.contains(dnpi1)) {

						System.out.println("Module " + moduleName
								+ " is Created and entry is captured in Audit Trail : " + documentID);
					}

				}
			}

			else if (Account.equals(customerid)) {

				for (int i = 1; i <= totalfound; i++) {
					String line1 = driver.findElement(By
							.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
									+ i + "]/table/tbody/tr/td[2]/div/div"))
							.getText();

					if (line1.contains(cnsi3)) {
						System.out.println("Module " + moduleName
								+ " is Deleted and entry is captured in Audit Trail : " + documentID);
					}

					if (line1.contains(cnsi2)) {
						System.out.println("Module " + moduleName + " is Edited and entry is captured in Audit Trail : "
								+ documentID);

					}

					if (line1.contains(cnsi1)) {

						System.out.println("Module " + moduleName
								+ " is Created and entry is captured in Audit Trail : " + documentID);
					}

				}
			}

			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("CloseAudittab"))))
					.click();

			Thread.sleep(5000);
		}

		catch (Exception EX1) {
			EX1.printStackTrace();
			Utilities.handleError(EX1, driver);
		}
	}

	// DN & CN otherwise,undercharge,overcharge,vendor,customer

	public static void Audit_testCreate9(String documentID, String Account, String moduleName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		Properties AuditTrail = Utilities.fetchProValue("OR_AuditTrail.properties");
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAudit"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAudit"))).click();
			System.out.println("clicked on Audit Trail");
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnSearch"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnSearch"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("EnterDocNo"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("EnterDocNo"))).sendKeys(documentID);
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnStartDateSelector"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("StartToday"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("FetchButton"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("FetchButton"))).click();
			Thread.sleep(5000);

			String userName = driver.findElement(By.xpath(AuditTrail.getProperty("Username"))).getText();

			Thread.sleep(5000);
			List<WebElement> total = driver.findElements(
					By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div"));
			int totalfound = total.size();

			System.out.println(totalfound);

			String dn1pi1 = "User " + userName + " has added a " + moduleName + " " + documentID; // create
																									// debitnote
																									// with
																									// Vendor
																									// Invoice

			String cn1si1 = "User " + userName + " has added a " + moduleName + " " + documentID; // create
																									// credit
																									// note
																									// with
																									// sales
																									// Invoice

			String dn1pi2 = "User " + userName + " has updated a " + moduleName + " " + "Copy" + documentID; // edit
																												// debitnote
																												// with
																												// Vendor
																												// Invoice

			String cn1si2 = "User " + userName + " has updated a " + moduleName + " " + "Copy" + documentID; // edit
																												// creditnote
																												// with
																												// customer
																												// invoice

			String dn1pi3 = "User " + userName + " has deleted a " + moduleName + " Permanently " + documentID
					+ " along with the JE No. "; // deleted debitnote

			String cn1si3 = "User " + userName + " has deleted a " + moduleName + " Permanently " + documentID
					+ " along with the JE No. "; // deleted creditnote
			Thread.sleep(5000);

			if (Account.equals(vendorid)) {

				for (int i = 1; i <= totalfound; i++) {
					String line1 = driver.findElement(By
							.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
									+ i + "]/table/tbody/tr/td[2]/div/div"))
							.getText();

					if (line1.contains(dn1pi3)) {
						System.out.println("Module " + moduleName
								+ " is Deleted and entry is captured in Audit Trail : " + documentID);

					}

					if (line1.contains(dn1pi2)) {
						System.out.println("Module " + moduleName + " is Edited and entry is captured in Audit Trail : "
								+ documentID);

					}

					if (line1.contains(dn1pi1)) {

						System.out.println("Module " + moduleName
								+ " is Created and entry is captured in Audit Trail : " + documentID);
					}

				}
			}

			else if (Account.equals(customerid)) {

				for (int i = 1; i <= totalfound; i++) {
					String line1 = driver.findElement(By
							.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
									+ i + "]/table/tbody/tr/td[2]/div/div"))
							.getText();

					if (line1.contains(cn1si3)) {
						System.out.println("Module " + moduleName
								+ " is Deleted and entry is captured in Audit Trail : " + documentID);
					}

					if (line1.contains(cn1si2)) {
						System.out.println("Module " + moduleName + " is Edited and entry is captured in Audit Trail : "
								+ documentID);

					}

					if (line1.contains(cn1si1)) {

						System.out.println("Module " + moduleName
								+ " is Created and entry is captured in Audit Trail : " + documentID);
					}

				}
			}

			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("CloseAudittab"))))
					.click();

			Thread.sleep(5000);
		}

		catch (Exception EX1) {
			EX1.printStackTrace();
			Utilities.handleError(EX1, driver);
		}
	}

	// Make Payment

	public static void Audit_testCreate10(String documentID, String moduleName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		Properties AuditTrail = Utilities.fetchProValue("OR_AuditTrail.properties");
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAudit"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAudit"))).click();
			System.out.println("clicked on Audit Trail");
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnSearch"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnSearch"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("EnterDocNo"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("EnterDocNo"))).sendKeys(documentID);
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnStartDateSelector"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("StartToday"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("FetchButton"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("FetchButton"))).click();
			Thread.sleep(5000);

			String userName = driver.findElement(By.xpath(AuditTrail.getProperty("Username"))).getText();

			Thread.sleep(5000);
			List<WebElement> total = driver.findElements(
					By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div"));
			int totalfound = total.size();

			String mp1 = "User " + userName + " has made a " + moduleName + " " + documentID; // create
																								// Make
																								// Pay

			String mp2 = "User " + userName + " has made a " + moduleName + " " + "Copy" + documentID; // Copy
																										// Make
																										// Pay

			String mp3 = "User " + userName + " has updated a " + moduleName + " " + "Copy" + documentID; // Edit
																											// Make
																											// Pay

			String mp4 = "User " + userName + " has deleted a " + moduleName + " Permanently " + documentID + " "
					+ " along with the JE No. "; // Delete Make Pay

			Thread.sleep(5000);

			for (int i = 1; i <= totalfound; i++) {
				String line1 = driver.findElement(
						By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[2]/div/div"))
						.getText();

				if (line1.contains(mp4)) {
					System.out.println("Module " + moduleName + " is Deleted and entry is captured in Audit Trail : "
							+ "Copy" + documentID);
				}

				if (line1.contains(mp3)) {
					System.out.println("Module " + moduleName + " is Edited and entry is captured in Audit Trail : "
							+ "Copy" + documentID);

				}

				if (line1.contains(mp2))

				{
					System.out.println("Module " + moduleName + " is Copied and entry is captured in Audit Trail : "
							+ "Copy" + documentID);
				}

				if (line1.contains(mp1)) {

					System.out.println("Module " + moduleName + " is Created and entry is captured in Audit Trail : "
							+ documentID);
				}

			}

			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("CloseAudittab"))))
					.click();

			Thread.sleep(5000);
		}

		catch (Exception EX1) {
			EX1.printStackTrace();
			Utilities.handleError(EX1, driver);
		}
	}

	// Receive Payment

	public static void Audit_testCreate11(String documentID, String moduleName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		Properties AuditTrail = Utilities.fetchProValue("OR_AuditTrail.properties");
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAudit"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAudit"))).click();
			System.out.println("clicked on Audit Trail");
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnSearch"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnSearch"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("EnterDocNo"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("EnterDocNo"))).sendKeys(documentID);
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnStartDateSelector"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("StartToday"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("FetchButton"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("FetchButton"))).click();
			Thread.sleep(5000);

			String userName = driver.findElement(By.xpath(AuditTrail.getProperty("Username"))).getText();

			Thread.sleep(5000);
			List<WebElement> total = driver.findElements(
					By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div"));
			int totalfound = total.size();

			String rp1 = "User " + userName + " has made a " + "Customer Receive Payment" + " " + documentID; // create
																												// Make
																												// Pay
																												// customer

			String rp5 = "User " + userName + " has made a " + "Vendor Receive Payment" + " " + documentID; // create
																											// MakePay
																											// Vendor

			String rp8 = "User " + userName + " has made a " + "GL Code Receive Payment" + " " + documentID; // create
																												// MakePay
																												// GL
																												// Code

			String rp2 = "User " + userName + " has made a " + "Customer Receive Payment" + " " + "Copy" + documentID; // Copy
																														// Make
																														// Pay
																														// customer

			String rp6 = "User " + userName + " has made a " + "Vendor Receive Payment" + " " + "Copy" + documentID; // Copy
																														// Make
																														// Pay
																														// Vendor

			String rp9 = "User " + userName + " has made a " + "GL Code Receive Payment" + " " + documentID; // Copy
																												// MakePay
																												// GL
																												// Code

			String rp3 = "User " + userName + " has updated a " + "Customer Receive Payment" + " " + "Copy"
					+ documentID; // Edit Make Pay customer

			String rp7 = "User " + userName + " has updated a " + "Vendor Receive Payment" + " " + "Copy" + documentID; // Edit
																														// Make
																														// Pay
																														// Vendor

			String rp10 = "User " + userName + " has updated a " + "GL Code Receive Payment" + " " + "Copy"
					+ documentID; // edit MakePay GL Code edit

			String rp4 = "User " + userName + " has deleted a Receipt" + " Permanently " + documentID + " "
					+ " along with the JE No. "; // Delete Make Pay

			Thread.sleep(5000);

			for (int i = 1; i <= totalfound; i++) {
				String line1 = driver.findElement(
						By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[2]/div/div"))
						.getText();

				if (line1.contains(rp4)) {
					System.out.println("Module " + moduleName + " is Deleted and entry is captured in Audit Trail : "
							+ "Copy" + documentID);
				}

				if (line1.contains(rp3) || line1.contains(rp7) || line1.contains(rp10)) {
					System.out.println("Module " + moduleName + " is Edited and entry is captured in Audit Trail : "
							+ "Copy" + documentID);

				}

				if (line1.contains(rp2) || line1.contains(rp6) || line1.contains(rp9))

				{
					System.out.println("Module " + moduleName + " is Copied and entry is captured in Audit Trail : "
							+ "Copy" + documentID);
				}

				if (line1.contains(rp1) || line1.contains(rp5) || line1.contains(rp8)) {

					System.out.println("Module " + moduleName + " is Created and entry is captured in Audit Trail : "
							+ documentID);
				}

			}

			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("CloseAudittab"))))
					.click();

			Thread.sleep(5000);
		}

		catch (Exception EX1) {
			EX1.printStackTrace();
			Utilities.handleError(EX1, driver);
		}
	}

	// Journal Entry

	public static void Audit_testCreate12(String documentID, String moduleName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		Properties AuditTrail = Utilities.fetchProValue("OR_AuditTrail.properties");
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAudit"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAudit"))).click();
			System.out.println("clicked on Audit Trail");
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnSearch"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnSearch"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("EnterDocNo"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("EnterDocNo"))).sendKeys(documentID);
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnStartDateSelector"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("StartToday"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("FetchButton"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("FetchButton"))).click();
			Thread.sleep(5000);

			String userName = driver.findElement(By.xpath(AuditTrail.getProperty("Username"))).getText();

			Thread.sleep(5000);
			List<WebElement> total = driver.findElements(
					By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div"));
			int totalfound = total.size();

			String je1 = "User " + userName + " has added new " + moduleName + " " + documentID; // create
																									// JE

			String je2 = "User " + userName + " has added new " + moduleName + " " + "Copy" + documentID; // Copy
																											// JE

			String je3 = "User " + userName + " has updated " + moduleName + " " + "Copy" + documentID; // Edit
																										// JE

			String je4 = "User " + userName + " has deleted " + moduleName + " Permanently " + documentID; // Delete
																											// JE

			Thread.sleep(5000);

			for (int i = 1; i <= totalfound; i++) {
				String line1 = driver.findElement(
						By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[2]/div/div"))
						.getText();

				if (line1.contains(je4)) {
					System.out.println("Module " + moduleName + " is Deleted and entry is captured in Audit Trail : "
							+ "Copy" + documentID);
				}

				if (line1.contains(je3)) {
					System.out.println("Module " + moduleName + " is Edited and entry is captured in Audit Trail : "
							+ "Copy" + documentID);

				}

				if (line1.contains(je2))

				{
					System.out.println("Module " + moduleName + " is Copied and entry is captured in Audit Trail : "
							+ "Copy" + documentID);
				}

				if (line1.contains(je1)) {

					System.out.println("Module " + moduleName + " is Created and entry is captured in Audit Trail : "
							+ documentID);
				}

			}

			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("CloseAudittab"))))
					.click();

			Thread.sleep(5000);
		}

		catch (Exception EX1) {
			EX1.printStackTrace();
			Utilities.handleError(EX1, driver);
		}
	}

	// Purchase Return and Sales Return

	public static void Audit_testCreate13(String documentID, String moduleName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		Properties AuditTrail = Utilities.fetchProValue("OR_AuditTrail.properties");
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAudit"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAudit"))).click();
			System.out.println("clicked on Audit Trail");
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnSearch"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnSearch"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("EnterDocNo"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("EnterDocNo"))).sendKeys(documentID);
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnStartDateSelector"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("StartToday"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("FetchButton"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("FetchButton"))).click();
			Thread.sleep(5000);

			String userName = driver.findElement(By.xpath(AuditTrail.getProperty("Username"))).getText();

			Thread.sleep(5000);
			List<WebElement> total = driver.findElements(
					By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div"));
			int totalfound = total.size();

			String pr1 = "User " + userName + " has added a new " + moduleName + " " + documentID; // create
																									// purchase
																									// retrun
																									// only/debit
																									// note

			String sr1 = "User " + userName + " has added a new " + moduleName + " " + documentID; // create
																									// sales
																									// retrun
																									// only/debit
																									// note

			String sr4 = "User " + userName + " has added a new " + moduleName + " " + documentID; // create
																									// sales
																									// retrun
																									// cash

			String pr2 = "User " + userName + " has added a new " + moduleName + " " + "Copy" + documentID; // Copy
																											// purchase
																											// retrun
																											// only/debit
																											// note

			String sr2 = "User " + userName + " has added a new " + moduleName + " " + "Copy" + documentID; // Copy
																											// sales
																											// retrun
																											// only/debit
																											// note

			String sr5 = "User " + userName + " has added a new " + moduleName + " " + "Copy" + documentID; // Copy
																											// sales
																											// retrun
																											// cash

			String pr3 = "User " + userName + " has updated " + moduleName + " " + "Copy" + documentID; // Edit
																										// purchase
																										// retrun
																										// only/debit
																										// note

			String sr6 = "User " + userName + " has updated " + moduleName + " " + "Copy" + documentID; // Edit
																										// sales
																										// retrun
																										// only/debit
																										// note

			String sr7 = "User " + userName + " has updated " + moduleName + " " + "Copy" + documentID; // Edit
																										// sales
																										// retrun
																										// cash

			String pr4 = "User " + userName + " has deleted " + moduleName + " Permanently " + documentID; // Delete
																											// purchase
																											// retrun
																											// only/debit
																											// note/sr
																											// only/credit/cash

			Thread.sleep(5000);

			for (int i = 1; i <= totalfound; i++) {
				String line1 = driver.findElement(
						By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[2]/div/div"))
						.getText();

				if (line1.contains(pr4)) {
					System.out.println("Module " + moduleName + " is Deleted and entry is captured in Audit Trail : "
							+ "Copy" + documentID);
				}

				if (line1.contains(pr3) || line1.contains(sr6) || line1.contains(sr7)) {
					System.out.println("Module " + moduleName + " is Edited and entry is captured in Audit Trail : "
							+ "Copy" + documentID);

				}

				if (line1.contains(pr2) || line1.contains(sr2) || line1.contains(sr5))

				{
					System.out.println("Module " + moduleName + " is Copied and entry is captured in Audit Trail : "
							+ "Copy" + documentID);
				}

				if (line1.contains(pr1) || line1.contains(sr1) || line1.contains(sr4)) {

					System.out.println("Module " + moduleName + " is Created and entry is captured in Audit Trail : "
							+ documentID);
				}

			}

			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("CloseAudittab"))))
					.click();

			Thread.sleep(5000);
		}

		catch (Exception EX1) {
			EX1.printStackTrace();
			Utilities.handleError(EX1, driver);
		}
	}

	// COA , Payment Method , Tax

	public static void Audit_testCreate14(String documentID, String moduleName, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		Properties AuditTrail = Utilities.fetchProValue("OR_AuditTrail.properties");
		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAdministrator"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnAudit"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnAudit"))).click();
			System.out.println("clicked on Audit Trail");
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnSearch"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("ClickOnSearch"))).click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("EnterDocNo"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("EnterDocNo"))).sendKeys(documentID);
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions
							.elementToBeClickable(By.xpath(AuditTrail.getProperty("ClickOnStartDateSelector"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("StartToday"))))
					.click();
			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("FetchButton"))));
			driver.findElement(By.xpath(AuditTrail.getProperty("FetchButton"))).click();
			Thread.sleep(5000);

			String userName = driver.findElement(By.xpath(AuditTrail.getProperty("Username"))).getText();

			Thread.sleep(5000);
			List<WebElement> total = driver.findElements(
					By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div"));
			int totalfound = total.size();

			String coa1 = "User " + userName + " has added " + moduleName + " " + documentID; // create
																								// coa
			String pm1 = "User " + userName + " has added " + moduleName + " " + documentID; // create
																								// payment
																								// menthod
			String tx1 = "User " + userName + " has added " + moduleName; // create
																			// payment
																			// menthod

			String coa2 = "User " + userName + " has updated " + moduleName + " " + documentID; // Edit
																								// coa
			String pm2 = "User " + userName + " has updated " + moduleName + " " + documentID; // Edit
																								// payment
																								// menthod

			String coa3 = "User " + userName + " has deleted " + moduleName + " Permanently " + documentID; // Delete
																											// purchase
																											// retrun
																											// only/debit
																											// note/sr
																											// only/credit/cash
			String pm3 = "User " + userName + " has deleted " + moduleName + " Permanently " + documentID; // Deleted
																											// payment
																											// menthod
			String tx3 = "User " + userName + " has deleted " + moduleName;

			Thread.sleep(5000);

			for (int i = 1; i <= totalfound; i++) {
				String line1 = driver.findElement(
						By.xpath("//div[@id='auditTrail']/div/div/div/div/div[2]/div/div/div/div/div[1]/div[2]/div/div["
								+ i + "]/table/tbody/tr/td[2]/div/div"))
						.getText();

				if (line1.contains(coa3) || line1.contains(pm3) || line1.contains(tx3)) {
					System.out.println("Module " + moduleName + " is Deleted and entry is captured in Audit Trail : "
							+ documentID);
				}

				if (line1.contains(coa2) || line1.contains(pm2)) {
					System.out.println(
							"Module " + moduleName + " is Edited and entry is captured in Audit Trail : " + documentID);

				}

				if (line1.contains(coa1) || line1.contains(pm1) || line1.contains(tx1)) {

					System.out.println("Module " + moduleName + " is Created and entry is captured in Audit Trail : "
							+ documentID);
				}

			}

			Thread.sleep(5000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(AuditTrail.getProperty("CloseAudittab"))))
					.click();

			Thread.sleep(5000);
		}

		catch (Exception EX1) {
			EX1.printStackTrace();
			Utilities.handleError(EX1, driver);
		}
	}
}
