package krawler.erp.MP_RPModule;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class MPDocumentCreationforAmountDue {

	public static WebDriver MP_PI(String documentID, String vendorid, WebDriver driver) {

		try {

			String documentID1 = "MPPI" + documentID;

			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("MakePaymentAgainstVendorCheck"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.waitandSendkey(pro.getProperty("SequenceFormat"), driver, "NA");
			Utilities.waitandSendkey(pro.getProperty("DocumentNo"), driver, documentID1);
			Utilities.waitandSendkey(pro.getProperty("vendorID"), driver, vendorid);
			Thread.sleep(1000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			int e = driver
					.findElements(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			for (int i = 1; i < e + 1; i++) {

				String header = driver.findElement(By
						.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				// System.out.println(header);
				if (header.equals("Document Type")) {

					WebElement doctype = driver.findElement(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					doctype.click();
					Thread.sleep(2000);
					Utilities.sendText("Invoice");
					Thread.sleep(2000);
					Utilities.Enter();
				} else if (header.equals("Document Number")) {
					WebElement docno = driver.findElement(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					docno.click();
					Utilities.waitandSendkey(pro.getProperty("QuickSearchInvoice"), driver, documentID);
					Thread.sleep(2000);
					Utilities.waitandClick(pro.getProperty("SelectInvoice"), driver);
					Utilities.clickButton("Submit", 2000, driver);

				}

				else if (header.equals("Enter Amount")) {
					WebElement Amount = driver.findElement(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
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

		}
		return driver;

	}

	public static WebDriver MP_CN(String documentID, String vendorid, WebDriver driver) {

		try {
			String documentID1 = "MPCN" + documentID;

			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("MakePaymentAgainstVendorCheck"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.waitandSendkey(pro.getProperty("SequenceFormat"), driver, "NA");
			Utilities.waitandSendkey(pro.getProperty("DocumentNo"), driver, documentID1);
			Utilities.waitandSendkey(pro.getProperty("vendorID"), driver, vendorid);
			Thread.sleep(1000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			int e = driver
					.findElements(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			for (int i = 1; i < e + 1; i++) {

				String header = driver.findElement(By
						.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				// System.out.println(header);
				if (header.equals("Document Type")) {

					WebElement doctype = driver.findElement(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					doctype.click();
					Thread.sleep(2000);
					Utilities.sendText("Credit Note");
					Thread.sleep(2000);
					Utilities.Enter();
				} else if (header.equals("Document Number")) {
					WebElement docno = driver.findElement(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					docno.click();
					Utilities.waitandSendkey(pro.getProperty("QuickSearchCreditNote"), driver, documentID);
					Thread.sleep(2000);
					Utilities.waitandClick(pro.getProperty("SelectCreditNote"), driver);
					Utilities.clickButton("Submit", 2000, driver);

				}

				else if (header.equals("Enter Amount")) {
					WebElement Amount = driver.findElement(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
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

	public static WebDriver MPCust_RE(String documentID, String vendorid, WebDriver driver) {

		try {
			String documentID1 = "MPCusRE" + documentID;

			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("MakePaymentAgainstCustomer"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.waitandSendkey(pro.getProperty("SequenceFormat"), driver, "NA");
			Utilities.waitandSendkey(pro.getProperty("DocumentNo"), driver, documentID1);
			Utilities.waitandSendkey(pro.getProperty("vendorID"), driver, vendorid);
			Thread.sleep(1000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			int e = driver
					.findElements(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			for (int i = 1; i < e + 1; i++) {

				String header = driver.findElement(By
						.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				// System.out.println(header);
				if (header.equals("Document Type")) {

					WebElement doctype = driver.findElement(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					doctype.click();
					Thread.sleep(2000);
					Utilities.sendText("Refund / Deposit");
					Thread.sleep(2000);
					Utilities.Enter();
				} else if (header.equals("Document Number")) {
					WebElement docno = driver.findElement(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					docno.click();
					Utilities.waitandSendkey(pro.getProperty("requicksearch"), driver, documentID);
					Thread.sleep(2000);
					Utilities.waitandClick(pro.getProperty("SelectRefDocu"), driver);
					Utilities.clickButton("Submit", 2000, driver);

				}

				else if (header.equals("Enter Amount")) {
					WebElement Amount = driver.findElement(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
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

	public static WebDriver MPCust_CN(String documentID, String vendorid, WebDriver driver) {

		try {
			String documentID1 = "MPCusCN" + documentID;

			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("MakePaymentAgainstCustomer"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.waitandSendkey(pro.getProperty("SequenceFormat"), driver, "NA");
			Utilities.waitandSendkey(pro.getProperty("DocumentNo"), driver, documentID1);
			Utilities.waitandSendkey(pro.getProperty("vendorID"), driver, vendorid);
			Thread.sleep(1000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			int e = driver
					.findElements(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			for (int i = 1; i < e + 1; i++) {

				String header = driver.findElement(By
						.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				// System.out.println(header);
				if (header.equals("Document Type")) {

					WebElement doctype = driver.findElement(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					doctype.click();
					Thread.sleep(2000);
					Utilities.sendText("Credit Note");
					Thread.sleep(2000);
					Utilities.Enter();
				} else if (header.equals("Document Number")) {
					WebElement docno = driver.findElement(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					docno.click();
					Utilities.waitandSendkey(pro.getProperty("QuickSearchCreditNote"), driver, documentID);
					Thread.sleep(2000);
					Utilities.waitandClick(pro.getProperty("firstCreditNote"), driver);
					Utilities.clickButton("Submit", 2000, driver);

				}

				else if (header.equals("Enter Amount")) {
					WebElement Amount = driver.findElement(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
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

	public static WebDriver MPCust_RE2(String documentID, String vendorid, WebDriver driver) {

		try {
			String documentID1 = "MPCusRE" + documentID;

			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Utilities.waitandClick(pro.getProperty("MakePaymentAgainstCustomer"), driver);
			Utilities.waitandClick(pro.getProperty("SubmitButton1"), driver);
			Utilities.waitandSendkey(pro.getProperty("SequenceFormat"), driver, "NA");
			Utilities.waitandSendkey(pro.getProperty("DocumentNo"), driver, documentID1);
			Utilities.waitandSendkey(pro.getProperty("vendorID"), driver, vendorid);
			Thread.sleep(1000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			int e = driver
					.findElements(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			for (int i = 1; i < e + 1; i++) {

				String header = driver.findElement(By
						.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				// System.out.println(header);
				if (header.equals("Document Type")) {

					WebElement doctype = driver.findElement(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					Thread.sleep(2000);
					doctype.click();

					Utilities.enterTextandSelect("Refund / Deposit",
							"//*[contains(@style,'visible') and contains(@class,'x-layer x-editor x-small-editor')]//*[@id='type']/following::input[1]",
							driver);
					Utilities.waitandClick(pro.getProperty("Memo"), driver);

					Thread.sleep(2000);

				} else if (header.equals("Enter Amount")) {
					WebElement Amount = driver.findElement(By
							.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td["
									+ i + "]/div"));
					Amount.click();
					Thread.sleep(1000);
					Utilities.sendText("200");
					Thread.sleep(1000);
				}

			}

			Utilities.waitandClick(pro.getProperty("Memo"), driver);
			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			try {
				Utilities.clickButton("Yes", 1000, driver);
			} catch (Exception Ex) {

			}
			Utilities.clickButton("OK", 1000, driver);
			Utilities.waitandClick(pro.getProperty("CloseMakePayment"), driver);

		} catch (Exception Ex) {

		}
		return driver;

	}
}