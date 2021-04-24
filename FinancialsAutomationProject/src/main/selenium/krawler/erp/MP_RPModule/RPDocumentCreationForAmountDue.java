package krawler.erp.MP_RPModule;

import java.awt.event.KeyEvent;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class RPDocumentCreationForAmountDue {

	public static WebDriver RP_Advance(WebDriver driver, String documentID, String customerid, String EnterAmount) {
		try {
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReceivePaymentAgainstCustomerCheck"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.waitandSendkey(pro.getProperty("SequenceFormat"), driver, "NA");
			Utilities.waitandSendkey(pro.getProperty("DocumentNo"), driver, "RPAD" + documentID);
			Utilities.waitandSendkey(pro.getProperty("customerID"), driver, customerid);
			Thread.sleep(3000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			int g = driver
					.findElements(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size();

			for (int i = 1; i < g + 1; i++) {

				String header = driver.findElement(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Document Type")) {

					WebElement DocType = driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					DocType.click();
					Thread.sleep(2000);
					Utilities.sendText("Advanced / Deposit");
					Thread.sleep(1000);
					Utilities.Enter();
					Thread.sleep(2000);
					Utilities.waitandClick(pro.getProperty("Memo"), driver);
					Thread.sleep(1500);
				}

				else if (header.equals("Enter Amount")) {

					WebElement Amount = driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					Amount.click();
					Thread.sleep(2000);
					Utilities.sendText(EnterAmount);
					Thread.sleep(1000);
					Utilities.Enter();
					Thread.sleep(2000);
					Utilities.waitandClick(pro.getProperty("Memo"), driver);
					Thread.sleep(1500);

				}

			}

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}
			} catch (Exception Ex) {

			}

			Utilities.clickButton("OK", 1000, driver);
			Utilities.waitandClick(pro.getProperty("CloseMakePayment"), driver);

		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
		return driver;

	}

	public static WebDriver RP_Invoice(WebDriver driver, String documentID, String vendorid) {
		try {
			String documentID1 = "RPPI" + documentID;

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReceivePaymentAgainstCustomerCheck"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.waitandSendkey(pro.getProperty("SequenceFormat"), driver, "NA");
			Utilities.waitandSendkey(pro.getProperty("DocumentNo"), driver, documentID1);
			Utilities.waitandSendkey(pro.getProperty("customerID"), driver, vendorid);
			Thread.sleep(1000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			int e = driver
					.findElements(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size();
			// System.out.println(e);

			for (int i = 1; i < e + 1; i++) {

				String header = driver.findElement(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				// System.out.println(header);
				if (header.equals("Document Type")) {

					WebElement doctype = driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					doctype.click();
					Thread.sleep(2000);
					Utilities.sendText("Invoice");
					Thread.sleep(2000);
					Utilities.Enter();
				} else if (header.equals("Document Number")) {
					WebElement docno = driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					docno.click();
					Utilities.waitandSendkey(pro.getProperty("QuickSearchInvoice"), driver, "SI" + documentID);
					Thread.sleep(2000);
					Utilities.waitandClick(pro.getProperty("SelectInvoice"), driver);
					Utilities.clickButton("Submit", 2000, driver);

				}

				else if (header.equals("Enter Amount")) {
					WebElement Amount = driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					Amount.click();
					Utilities.sendText("500");

				}

			}

			Utilities.waitandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Save", 1000, driver);

			Utilities.clickButton("Yes", 1000, driver);
			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}
			} catch (Exception Ex) {

			}

			Utilities.clickButton("OK", 1000, driver);
			Utilities.waitandClick(pro.getProperty("CloseMakePayment"), driver);
		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
		return driver;

	}

	public static WebDriver RP_DebitNote(WebDriver driver, String documentID, String vendorid) {
		try {

			String documentID1 = "RPDN" + documentID;

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReceivePaymentAgainstCustomerCheck"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.waitandSendkey(pro.getProperty("SequenceFormat"), driver, "NA");
			Utilities.waitandSendkey(pro.getProperty("DocumentNo"), driver, documentID1);
			Utilities.waitandSendkey(pro.getProperty("customerID"), driver, vendorid);
			Thread.sleep(1000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			int e = driver
					.findElements(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size();
			// System.out.println(e);

			for (int i = 1; i < e + 1; i++) {

				String header = driver.findElement(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				// System.out.println(header);
				if (header.equals("Document Type")) {

					WebElement doctype = driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					doctype.click();
					Thread.sleep(2000);
					Utilities.sendText("Debit Note");
					Thread.sleep(2000);
					Utilities.Enter();
				} else if (header.equals("Document Number")) {
					WebElement docno = driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					docno.click();
					Utilities.waitandSendkey(pro.getProperty("QuickSearchDebitNote"), driver, documentID);
					Thread.sleep(2000);
					Utilities.waitandClick(pro.getProperty("SelectDebitNote"), driver);
					Utilities.clickButton("Submit", 2000, driver);

				}

				else if (header.equals("Enter Amount")) {
					WebElement Amount = driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					Amount.click();
					Utilities.sendText("50");

				}

			}

			Utilities.waitandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}
			} catch (Exception Ex) {

			}
			Utilities.clickButton("OK", 1000, driver);
			Utilities.waitandClick(pro.getProperty("CloseMakePayment"), driver);

		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
		return driver;

	}

	public static WebDriver RPCust_RE(String documentID, String vendorid, WebDriver driver) {

		try {
			String documentID1 = "RPCusRE" + documentID;

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReceivePaymentAgainstVendorCheck"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.waitandSendkey(pro.getProperty("SequenceFormat"), driver, "NA");
			Utilities.waitandSendkey(pro.getProperty("DocumentNo"), driver, documentID1);
			Utilities.waitandSendkey(pro.getProperty("vednorID"), driver, vendorid);
			Thread.sleep(1000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			int e = driver
					.findElements(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			for (int i = 1; i < e + 1; i++) {

				String header = driver.findElement(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				// System.out.println(header);
				if (header.equals("Document Type")) {

					WebElement doctype = driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					doctype.click();
					Thread.sleep(2000);
					Utilities.sendText("Refund / Deposit");
					Thread.sleep(2000);
					Utilities.Enter();
				} else if (header.equals("Document Number")) {
					WebElement docno = driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					docno.click();
					Utilities.waitandSendkey(pro.getProperty("requicksearch"), driver, documentID + "-2");
					Thread.sleep(2000);
					Utilities.waitandClick(pro.getProperty("SelectRefDocu"), driver);
					Utilities.clickButton("Submit", 2000, driver);

				}

				else if (header.equals("Enter Amount")) {
					WebElement Amount = driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					Amount.click();
					Utilities.sendText("50");

				}

			}

			Utilities.waitandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}
			} catch (Exception Ex) {

			}
			Utilities.clickButton("OK", 1000, driver);
			Utilities.waitandClick(pro.getProperty("CloseMakePayment"), driver);

		} catch (Exception Ex) {

		}
		return driver;

	}

	public static WebDriver RPCust_DE(String documentID, String vendorid, WebDriver driver) {

		try {
			String documentID1 = "RPCusDE" + documentID;

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReceivePaymentAgainstVendorCheck"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.waitandSendkey(pro.getProperty("SequenceFormat"), driver, "NA");
			Utilities.waitandSendkey(pro.getProperty("DocumentNo"), driver, documentID1);
			Utilities.waitandSendkey(pro.getProperty("vednorID"), driver, vendorid);
			Thread.sleep(1000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			int e = driver
					.findElements(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			for (int i = 1; i < e + 1; i++) {

				String header = driver.findElement(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				// System.out.println(header);
				if (header.equals("Document Type")) {

					WebElement doctype = driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					doctype.click();
					Thread.sleep(2000);
					Utilities.sendText("Refund / Deposit");
					Thread.sleep(2000);
					Utilities.Enter();
				} else if (header.equals("Enter Amount")) {
					WebElement Amount = driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					Amount.click();
					Utilities.sendText("100");

				}

			}

			Utilities.waitandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}
			} catch (Exception Ex) {

			}
			Utilities.clickButton("OK", 1000, driver);
			Utilities.waitandClick(pro.getProperty("CloseMakePayment"), driver);

		} catch (Exception Ex) {

		}
		return driver;

	}

	public static WebDriver RPCust_GL(String documentID, String vendorid, WebDriver driver, String BankName) {

		try {
			String documentID1 = "RPCusGL" + documentID;

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstGL.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("ReceivePaymentAgainstGLCheck"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.waitandSendkey(pro.getProperty("SequenceFormat"), driver, "NA");
			Utilities.waitandSendkey(pro.getProperty("DocumentNo"), driver, documentID1);
			Thread.sleep(1000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			int e = driver
					.findElements(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			for (int i = 1; i < e + 1; i++) {

				String header = driver.findElement(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				// System.out.println(header);
				if (header.equals("Account")) {

					WebElement doctype = driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					doctype.click();
					Thread.sleep(2000);
					Utilities.waitandClick(pro.getProperty("QuickSearchGLAccount"), driver);
					Utilities.sendText(BankName);
					Thread.sleep(1000);
					Utilities.waitandClick(pro.getProperty("SelectGLAccount"), driver);
					Utilities.clickButton("Submit", 2000, driver);
				} else if (header.equals("Enter Amount")) {
					WebElement Amount = driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					Amount.click();
					Utilities.sendText("100");

				}

			}

			Utilities.waitandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}
			} catch (Exception Ex) {

			}
			Utilities.clickButton("OK", 1000, driver);
			Utilities.waitandClick(pro.getProperty("CloseMakePayment"), driver);

		} catch (Exception Ex) {

		}
		return driver;

	}

}
