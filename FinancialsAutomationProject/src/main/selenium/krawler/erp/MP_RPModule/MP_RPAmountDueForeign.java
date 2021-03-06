package krawler.erp.MP_RPModule;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class MP_RPAmountDueForeign {

	public static WebDriver MPAmountDue(String documentID, WebDriver driver) {
		try {

			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");
			Properties pro1 = Utilities.fetchProValue("OR_CashPurchase.properties");
			Properties pro2 = Utilities.fetchProValue("OR_CreditNote_PurchaseInvoice.properties");

			Properties pro3 = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("PaymentMadeReport"), driver);

			// Thread.sleep(2000);
			Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);
			Thread.sleep(2000);
			Utilities.waitandClick(pro.getProperty("DocumentCheckBox"), driver);
			Utilities.clickButton("Edit", 200, driver);

			String ExchangeRate = driver.findElement(By.xpath(pro.getProperty("ExchangeRate"))).getText();

			// System.out.println(ExchangeRate);
			// System.out.println(ExchangeRate.indexOf("="));
			String ER = ExchangeRate.substring(ExchangeRate.indexOf("="));

			// System.out.println("a="+ER);

			String ExchangeRate2 = ER.substring(ER.indexOf('=') + 2, ER.indexOf('=') + 20);

			double ER2 = Double.parseDouble(ExchangeRate2);

			// System.out.println("ExchangeRate2="+ExchangeRate2);

			int f = driver
					.findElements(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();
			// System.out.println(f);

			String AmounDue = "";

			String DocumentNo = "";

			String EnterAmount = "";

			String Documenttype = "";

			{ // for getting amount from MP view
				for (int k = 1; k < f + 1; k++) {
					// System.out.println(driver.findElement(By.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["+k+"]/div")).getText());
					String header = driver.findElement(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ k + "]/div"))
							.getText();

					if (header.equals("Document Type")) {
						Documenttype = driver.findElement(By
								.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ k + "]/div"))
								.getText();
					}

					if (header.equals("Document Number")) {
						DocumentNo = driver.findElement(By
								.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ k + "]/div"))
								.getText();
					}
					if (header.equals("Amount Due")) {
						AmounDue = driver.findElement(By
								.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ k + "]/div"))
								.getText();
					}

					if (header.equals("Enter Amount")) {
						EnterAmount = driver.findElement(By
								.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ k + "]/div"))
								.getText();
					}

				}
			}

			String AmounDue2 = AmounDue.substring(AmounDue.indexOf(" ") + 1, AmounDue.length());
			AmounDue2 = AmounDue2.replace(",", "");
			double b = Double.parseDouble(AmounDue2);
			System.out.println("MP=" + b);

			String EnterAmount2 = EnterAmount.replace(",", "");
			double a = Double.parseDouble(EnterAmount2);
			System.out.println("EnterAmount=" + a);

			double finaldue = Utilities.round((b - a) * ER2, "");

			System.out.println("PI=" + finaldue);

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);

			if (Documenttype.equals("Invoice")) {
				Utilities.waitandClick(pro1.getProperty("SalesInvoiceReport"), driver);
				Thread.sleep(2000);
				Utilities.waitandSendkey(pro1.getProperty("QuickSearch"), driver, DocumentNo);

				int m = driver
						.findElements(By
								.xpath("//div[@id='gridmsg21GRListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
						.size();

				String PIAmountDue = "";

				for (int i = 1; i < m + 1; i++) {

					String header = driver.findElement(By
							.xpath("//div[@id='gridmsg21GRListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();

					if (header.equals("Amount Due")) {

						PIAmountDue = driver.findElement(By
								.xpath("//div[@id='gridmsg21GRListEntry']/div/div/div/div/div[2]/div/div/table/tbody/tr/td["
										+ i + "]/div"))
								.getText();
					}
				}

				String PIAmountDue2 = PIAmountDue.substring(PIAmountDue.indexOf(" "), PIAmountDue.length());
				PIAmountDue2 = PIAmountDue2.replace(",", "");
				double pidue = Double.parseDouble(PIAmountDue2);
				System.out.println("PI=" + pidue);

				if (pidue == finaldue) {
					System.out.println("Amount Due of PI due is Correct");
				} else {
					System.out.println("Amount due of PI is InCorrect");
				}

			} else if (Documenttype.equals("Credit Note")) {

				Utilities.waitandClick(pro2.getProperty("CreditNoteReport"), driver);
				WebDriverWait wait = new WebDriverWait(driver, 50);
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro2.getProperty("DocumentCheckBox"))));
				Utilities.waitandSendkey(pro2.getProperty("CNTypeDD"), driver, "Credit Note for Vendors");
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(pro2.getProperty("DocumentCheckBox"))));
				Utilities.waitandSendkey(pro2.getProperty("QuickSearch"), driver, DocumentNo);

				int g = driver
						.findElements(By
								.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
						.size();
				// System.out.println(g);

				String CNAmountDue = "";

				Thread.sleep(2000);
				for (int i = 1; i < g + 1; i++) {

					String header = driver.findElement(By
							.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();
					// System.out.println(header);

					if (header.equals("Amount Due")) {

						CNAmountDue = driver.findElement(By
								.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
										+ i + "]/div"))
								.getText();
						// System.out.println("CN Amount Due="+CNAmountDue);
					}
				}

				String CNAmountDue2 = CNAmountDue.substring(CNAmountDue.indexOf(" "), CNAmountDue.length());
				CNAmountDue2 = CNAmountDue2.replace(",", "");
				double CNdue = Double.parseDouble(CNAmountDue2);

				if (CNdue == finaldue) {
					System.out.println("Amount Due of CN due is Correct");
				} else {
					System.out.println("Amount due of CN is InCorrect");
				}
			} else if (Documenttype.equals("Refund / Deposit")) {

				Utilities.waitandClick(pro3.getProperty("ReceivePaymentReport"), driver);

				WebDriverWait wait = new WebDriverWait(driver, 50);
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(pro3.getProperty("DocumentCheckBox"))));
				Utilities.waitandSendkey(pro3.getProperty("QuickSearch"), driver, DocumentNo);

				int g = driver
						.findElements(By
								.xpath("//div[@id='gridmsg20receiptReport']/div/div[2]/div/div/div[1]/div[1]/div/table/thead/tr/td/div"))
						.size();
				// System.out.println(g);

				String RPAmountDue = "";
				// System.out.println(RPAmountDue);
				Thread.sleep(2000);
				for (int i = 1; i < g + 1; i++) {

					String header = driver.findElement(By
							.xpath("//div[@id='gridmsg20receiptReport']/div/div[2]/div/div/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();
					// System.out.println(header);

					if (header.equals("Amount Due")) {

						RPAmountDue = driver.findElement(By
								.xpath("//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr/td["
										+ i + "]/div"))
								.getText();
						System.out.println("a" + RPAmountDue);
					}
				}

				String RPAmountDue2 = RPAmountDue.substring(RPAmountDue.indexOf(" "), RPAmountDue.length());
				RPAmountDue2 = RPAmountDue2.replace(",", "");
				double RPdue = Double.parseDouble(RPAmountDue2);

				if (RPdue == finaldue) {
					System.out.println("Amount Due of Customer RP  is Correct");
				} else {
					System.out.println("Amount due of Customer RP is InCorrect");
				}
			} else if (Documenttype.equals("Credit Note")) {

				Utilities.waitandClick(pro3.getProperty("ReceivePaymentReport"), driver);

				WebDriverWait wait = new WebDriverWait(driver, 50);
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(pro3.getProperty("DocumentCheckBox"))));
				Utilities.waitandSendkey(pro3.getProperty("QuickSearch"), driver, DocumentNo);

				int g = driver
						.findElements(By
								.xpath("//div[@id='gridmsg20receiptReport']/div/div[2]/div/div/div[1]/div[1]/div/table/thead/tr/td/div"))
						.size();
				// System.out.println(g);

				String RPAmountDue = "";
				// System.out.println(RPAmountDue);
				Thread.sleep(2000);
				for (int i = 1; i < g + 1; i++) {

					String header = driver.findElement(By
							.xpath("//div[@id='gridmsg20receiptReport']/div/div[2]/div/div/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();
					// System.out.println(header);

					if (header.equals("Amount Due")) {

						RPAmountDue = driver.findElement(By
								.xpath("//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr/td["
										+ i + "]/div"))
								.getText();
						System.out.println("a" + RPAmountDue);
					}
				}

				String RPAmountDue2 = RPAmountDue.substring(RPAmountDue.indexOf(" "), RPAmountDue.length());
				RPAmountDue2 = RPAmountDue2.replace(",", "");
				double RPdue = Double.parseDouble(RPAmountDue2);

				if (RPdue == finaldue) {
					System.out.println("Amount Due of Customer RP  is Correct");
				} else {
					System.out.println("Amount due of Customer RP is InCorrect");
				}
			}

			Utilities.refresh();
			Utilities.waitandClick("//div[@id='navigationpanel']/div[1]/div", driver);

		}

		catch (Exception Ex) {
			Ex.printStackTrace();
		}

		return driver;
	}

	public static WebDriver MPAmountDue2(String documentID, WebDriver driver) {
		try {

			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");
			Properties pro1 = Utilities.fetchProValue("OR_CashPurchase.properties");
			Properties pro2 = Utilities.fetchProValue("OR_CreditNote_PurchaseInvoice.properties");
			Properties pro3 = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("PaymentMadeReport"), driver);
			Thread.sleep(2000);
			// Utilities.zoomOut();
			// Thread.sleep(2000);
			Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);
			Thread.sleep(2000);
			Utilities.waitandClick(pro.getProperty("DocumentCheckBox"), driver);
			Utilities.clickButton("Edit", 200, driver);

			String ExchangeRate = driver.findElement(By.xpath(pro.getProperty("ExchangeRate"))).getText();

			// System.out.println(ExchangeRate);
			// System.out.println(ExchangeRate.indexOf("="));
			String ER = ExchangeRate.substring(ExchangeRate.indexOf("="));

			// System.out.println("a="+ER);

			String ExchangeRate2 = ER.substring(ER.indexOf('=') + 2, ER.indexOf('=') + 20);

			double ER2 = Double.parseDouble(ExchangeRate2);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Payment No")) {
					driver.findElement(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div/a"))
							.click();
				}
			}

			int f = driver
					.findElements(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();
			// System.out.println(f);

			String AmounDue = "";

			String DocumentNo = "";

			String EnterAmount = "";

			String Documenttype = "";

			{ // for getting amount from MP view
				for (int k = 1; k < f + 1; k++) {
					// System.out.println(driver.findElement(By.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["+k+"]/div")).getText());
					String header = driver.findElement(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ k + "]/div"))
							.getText();

					if (header.equals("Document Type")) {
						Documenttype = driver.findElement(By
								.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ k + "]/div"))
								.getText();
					}

					if (header.equals("Document Number")) {
						DocumentNo = driver.findElement(By
								.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ k + "]/div"))
								.getText();
					}
					if (header.equals("Amount Due")) {
						AmounDue = driver.findElement(By
								.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ k + "]/div"))
								.getText();
					}

					if (header.equals("Enter Amount")) {
						EnterAmount = driver.findElement(By
								.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ k + "]/div"))
								.getText();
					}

				}
			}

			String AmounDue2 = AmounDue.substring(AmounDue.indexOf(" ") + 1, AmounDue.length());
			AmounDue2 = AmounDue2.replace(",", "");
			double b = Double.parseDouble(AmounDue2);
			// System.out.println("b="+b);

			String EnterAmount2 = EnterAmount.replace(",", "");
			double a = Double.parseDouble(EnterAmount2);
			// System.out.println("a="+a);

			double finaldue = Utilities.round((b - a) * ER2, " ");
			System.out.println(finaldue);

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);
			if (Documenttype.equals("Credit Note")) {

				Utilities.waitandClick(pro2.getProperty("CreditNoteReport"), driver);
				WebDriverWait wait = new WebDriverWait(driver, 50);
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(pro2.getProperty("DocumentCheckBox"))));

				Utilities.waitandSendkey(pro2.getProperty("QuickSearch"), driver, DocumentNo);

				int g = driver
						.findElements(By
								.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
						.size();
				// System.out.println(g);

				String CNAmountDue = "";

				Thread.sleep(2000);
				for (int i = 1; i < g + 1; i++) {

					String header = driver.findElement(By
							.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();
					// System.out.println(header);

					if (header.equals("Amount Due")) {

						CNAmountDue = driver.findElement(By
								.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
										+ i + "]/div"))
								.getText();
						// System.out.println("a"+CNAmountDue);
					}
				}

				String CNAmountDue2 = CNAmountDue.substring(CNAmountDue.indexOf(" "), CNAmountDue.length());
				CNAmountDue2 = CNAmountDue2.replace(",", "");
				double CNdue = Double.parseDouble(CNAmountDue2);

				if (CNdue == finaldue) {
					System.out.println("Amount Due of CN otherwise due is Correct");
				} else {
					System.out.println("Amount due of CN otherwise is InCorrect");
				}
			}

			Utilities.refresh();
			Utilities.waitandClick("//div[@id='navigationpanel']/div[1]/div", driver);
		}

		catch (Exception Ex) {
			Ex.printStackTrace();
		}

		return driver;
	}

	public static WebDriver AmountDueGL(String documentID, WebDriver driver) {
		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("PaymentMadeReport"), driver);
			Thread.sleep(2000);
			// Utilities.zoomOut();
			// Thread.sleep(2000);
			Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);

			int k = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			String MPGLDue = "";
			for (int i = 1; i < k + 1; i++) {
				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (header.equals("Amount Due")) {
					MPGLDue = driver.findElement(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}
			}

			String MPGLDue2 = MPGLDue.substring(MPGLDue.indexOf(" "), MPGLDue.length());
			MPGLDue2 = MPGLDue2.replace(",", "");
			double CNdue = Double.parseDouble(MPGLDue2);

			if (CNdue == 0) {
				System.out.println("Amount Due of MP GL due is Correct");
			} else {
				System.out.println("Amount due of MP GL is InCorrect");
			}
			Utilities.refresh();
			Utilities.waitandClick("//div[@id='navigationpanel']/div[1]/div", driver);
		} catch (Exception EX) {
		}
		return driver;
	}

	public static WebDriver AmountDueAdvance(String documentID, WebDriver driver) {
		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("PaymentMadeReport"), driver);
			Thread.sleep(2000);
			// Utilities.zoomOut();

			Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);
			Thread.sleep(2000);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Payment No")) {
					driver.findElement(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div/a"))
							.click();
				}
			}

			int f = driver
					.findElements(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();
			// System.out.println(f);

			String EnterAmount = "";
			String Documenttype = "";
			for (int k = 1; k < f + 1; k++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["+k+"]/div")).getText());
				String header = driver.findElement(By
						.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ k + "]/div"))
						.getText();

				if (header.equals("Document Type")) {
					Documenttype = driver.findElement(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				}
				if (header.equals("Enter Amount")) {
					EnterAmount = driver.findElement(By
							.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				}

			}
			String EnterAmount2 = EnterAmount.replace(",", "");
			double finalam = Double.parseDouble(EnterAmount2);

			Utilities.waitandClick(pro.getProperty("CloseViewMP"), driver);
			Utilities.waitandClick(pro.getProperty("ClosePaymentMadeReport"), driver);

			String MPAdDue = "";
			if (Documenttype.equals("Advanced / Deposit")) {
				Utilities.waitandClick(pro.getProperty("PaymentMadeReport"), driver);
				Thread.sleep(2000);
				Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);
				Thread.sleep(2000);

				int k = driver
						.findElements(By
								.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
						.size();
				for (int i = 1; i < k + 1; i++) {
					String header = driver.findElement(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();
					if (header.equals("Amount Due")) {
						MPAdDue = driver.findElement(By
								.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
										+ i + "]/div"))
								.getText();
					}
				}

				String MPAdDue2 = MPAdDue.substring(MPAdDue.indexOf(" "), MPAdDue.length());
				MPAdDue2 = MPAdDue2.replace(",", "");
				double fMPAddue = Double.parseDouble(MPAdDue2);
				System.out.println(fMPAddue);

				if (fMPAddue == finalam) {
					System.out.println("Amount Due of MP Adavance due is Correct");
				} else {
					System.out.println("Amount due of MP Adavance is InCorrect");
				}
			}

			Utilities.refresh();
			Utilities.waitandClick("//div[@id='navigationpanel']/div[1]/div", driver);

		} catch (Exception EX) {
			EX.printStackTrace();
		}
		return driver;
	}

	public static WebDriver AmountDueRPAdvance(String documentID, WebDriver driver) {
		try {
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentReport"), driver);
			Thread.sleep(2000);
			// Utilities.zoomOut();

			Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);
			Thread.sleep(2000);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Receipt No.")) {
					driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div/a"))
							.click();
				}
			}

			int f = driver
					.findElements(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();
			// System.out.println(f);

			String EnterAmount = "";
			String Documenttype = "";
			for (int k = 1; k < f + 1; k++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["+k+"]/div")).getText());
				String header = driver.findElement(By
						.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ k + "]/div"))
						.getText();

				if (header.equals("Document Type")) {
					Documenttype = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				}
				if (header.equals("Enter Amount")) {
					EnterAmount = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				}

			}
			String EnterAmount2 = EnterAmount.replace(",", "");
			double finalam = Double.parseDouble(EnterAmount2);

			Utilities.waitandClick(pro.getProperty("CloseViewRP"), driver);
			Utilities.waitandClick(pro.getProperty("ClosePaymentMadeReport"), driver);

			String RPAdDue = "";
			if (Documenttype.equals("Advanced / Deposit")) {
				Utilities.waitandClick(pro.getProperty("ReceivePaymentReport"), driver);
				Thread.sleep(2000);
				Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);
				Thread.sleep(2000);

				int k = driver
						.findElements(By
								.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
						.size();
				for (int i = 1; i < k + 1; i++) {
					String header = driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();
					if (header.equals("Amount Due")) {
						RPAdDue = driver.findElement(By
								.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
										+ i + "]/div"))
								.getText();
					}
				}

				String RPAdDue2 = RPAdDue.substring(RPAdDue.indexOf(" "), RPAdDue.length());
				RPAdDue2 = RPAdDue2.replace(",", "");
				double fRPAdDue = Double.parseDouble(RPAdDue2);
				System.out.println(fRPAdDue);

				if (fRPAdDue == finalam) {
					System.out.println("Amount Due of RP Adavance due is Correct");
				} else {
					System.out.println("Amount due of RP Adavance is InCorrect");
				}
			}

			Utilities.refresh();
			Utilities.waitandClick("//div[@id='navigationpanel']/div[1]/div", driver);
		} catch (Exception EX) {
			EX.printStackTrace();
		}
		return driver;
	}

	public static WebDriver RPAmountDue(String documentID, WebDriver driver) {
		try {

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");
			Properties pro1 = Utilities.fetchProValue("OR_CashSale.properties");
			Properties pro2 = Utilities.fetchProValue("OR_DebitNoteAgainstCustomer.properties");

			Properties pro3 = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentReport"), driver);
			Thread.sleep(2000);
			// Utilities.zoomOut();
			// Thread.sleep(2000);
			Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);

			Thread.sleep(2000);
			Utilities.waitandClick(pro.getProperty("DocumentCheckBox"), driver);
			Utilities.clickButton("Edit", 200, driver);

			String ExchangeRate = driver.findElement(By.xpath(pro.getProperty("ExchangeRate"))).getText();

			System.out.println(ExchangeRate);
			System.out.println(ExchangeRate.indexOf("="));
			String ER = ExchangeRate.substring(ExchangeRate.indexOf("="));

			System.out.println("a=" + ER);

			String ExchangeRate2 = ER.substring(ER.indexOf('=') + 2, ER.indexOf('=') + 20);

			double ER2 = Double.parseDouble(ExchangeRate2);

			System.out.println("ExchangeRate2=" + ExchangeRate2);

			int f = driver
					.findElements(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();
			// System.out.println(f);

			String AmounDue = "";

			String DocumentNo = "";

			String EnterAmount = "";

			String Documenttype = "";

			{ // for getting amount from MP view
				for (int k = 1; k < f + 1; k++) {
					// System.out.println(driver.findElement(By.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["+k+"]/div")).getText());
					String header = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ k + "]/div"))
							.getText();

					if (header.equals("Document Type")) {
						Documenttype = driver.findElement(By
								.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ k + "]/div"))
								.getText();
					}

					if (header.equals("Document Number")) {
						DocumentNo = driver.findElement(By
								.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ k + "]/div"))
								.getText();
					}
					if (header.equals("Amount Due")) {
						AmounDue = driver.findElement(By
								.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ k + "]/div"))
								.getText();
					}

					if (header.equals("Enter Amount")) {
						EnterAmount = driver.findElement(By
								.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
										+ k + "]/div"))
								.getText();
					}

				}
			}

			String AmounDue2 = AmounDue.substring(AmounDue.indexOf(" ") + 1, AmounDue.length());
			AmounDue2 = AmounDue2.replace(",", "");
			double b = Double.parseDouble(AmounDue2);
			// System.out.println("b="+b);

			String EnterAmount2 = EnterAmount.replace(",", "");
			double a = Double.parseDouble(EnterAmount2);
			// System.out.println("a="+a);

			double finaldue = Utilities.round((b - a) * ER2, " ");
			System.out.println(finaldue);

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);

			if (Documenttype.equals("Invoice")) {
				Utilities.waitandClick(pro1.getProperty("SalesInvoiceReport"), driver);
				Thread.sleep(2000);
				Utilities.waitandSendkey(pro1.getProperty("QuickSearch"), driver, DocumentNo);

				int m = driver
						.findElements(By
								.xpath("//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
						.size();

				String SIAmountDue = "";

				for (int i = 1; i < m + 1; i++) {

					String header = driver.findElement(By
							.xpath("//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();

					if (header.equals("Amount Due")) {

						SIAmountDue = driver.findElement(By
								.xpath("//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div/div[2]/div/div/table/tbody/tr/td["
										+ i + "]/div"))
								.getText();
					}
				}

				String SIAmountDue2 = SIAmountDue.substring(SIAmountDue.indexOf(" "), SIAmountDue.length());
				SIAmountDue2 = SIAmountDue2.replace(",", "");
				double SIdue = Double.parseDouble(SIAmountDue2);
				System.out.println(SIdue);

				if (SIdue == finaldue) {
					System.out.println("Amount Due of SI due is Correct");
				} else {
					System.out.println("Amount due of SI is InCorrect");
				}

			} else if (Documenttype.equals("Debit Note")) {

				Utilities.waitandClick(pro2.getProperty("DebitNoteReport"), driver);
				WebDriverWait wait = new WebDriverWait(driver, 50);
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro2.getProperty("DocumentCheckBox"))));
				Utilities.waitandSendkey(pro2.getProperty("DNTypeDD"), driver, "Debit Note for Customers");
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(pro2.getProperty("DocumentCheckBox"))));
				Utilities.waitandSendkey(pro2.getProperty("QuickSearch"), driver, DocumentNo);

				int g = driver
						.findElements(By
								.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
						.size();
				// System.out.println(g);

				String DNAmountDue = "";

				Thread.sleep(2000);
				for (int i = 1; i < g + 1; i++) {

					String header = driver.findElement(By
							.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();
					// System.out.println(header);

					if (header.equals("Amount Due")) {

						DNAmountDue = driver.findElement(By
								.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
										+ i + "]/div"))
								.getText();
						// System.out.println("a"+DNAmountDue);
					}
				}

				String DNAmountDue2 = DNAmountDue.substring(DNAmountDue.indexOf(" "), DNAmountDue.length());
				DNAmountDue2 = DNAmountDue2.replace(",", "");
				double DNdue = Double.parseDouble(DNAmountDue2);

				if (DNdue == finaldue) {
					System.out.println("Amount Due of DN due is Correct");
				} else {
					System.out.println("Amount due of DN is InCorrect");
				}
			} else if (Documenttype.equals("Refund / Deposit")) {

				Utilities.waitandClick(pro3.getProperty("PaymentMadeReport"), driver);

				WebDriverWait wait = new WebDriverWait(driver, 50);
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(pro3.getProperty("DocumentCheckBox"))));
				Utilities.waitandSendkey(pro3.getProperty("QuickSearch"), driver, DocumentNo);

				int g = driver
						.findElements(By
								.xpath("//div[@id='gridmsg23paymentReport']/div/div[2]/div/div/div[1]/div[1]/div/table/thead/tr/td/div"))
						.size();
				// System.out.println(g);

				String MPAmountDue = "";
				// System.out.println(RPAmountDue);
				Thread.sleep(2000);
				for (int i = 1; i < g + 1; i++) {

					String header = driver.findElement(By
							.xpath("//div[@id='gridmsg23paymentReport']/div/div[2]/div/div/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();
					// System.out.println(header);

					if (header.equals("Amount Due")) {

						MPAmountDue = driver.findElement(By
								.xpath("//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr/td["
										+ i + "]/div"))
								.getText();
						System.out.println("a" + MPAmountDue);
					}
				}

				String MPAmountDue2 = MPAmountDue.substring(MPAmountDue.indexOf(" "), MPAmountDue.length());
				MPAmountDue2 = MPAmountDue2.replace(",", "");
				double MPdue = Double.parseDouble(MPAmountDue2);

				if (MPdue == finaldue) {
					System.out.println("Amount Due of Vendor MP  is Correct");
				} else {
					System.out.println("Amount due of Vendor MP is InCorrect");
				}
			} else if (Documenttype.equals("Credit Note")) {

				Utilities.waitandClick(pro3.getProperty("ReceivePaymentReport"), driver);

				WebDriverWait wait = new WebDriverWait(driver, 50);
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(pro3.getProperty("DocumentCheckBox"))));
				Utilities.waitandSendkey(pro3.getProperty("QuickSearch"), driver, DocumentNo);

				int g = driver
						.findElements(By
								.xpath("//div[@id='gridmsg20receiptReport']/div/div[2]/div/div/div[1]/div[1]/div/table/thead/tr/td/div"))
						.size();
				// System.out.println(g);

				String RPAmountDue = "";
				// System.out.println(RPAmountDue);
				Thread.sleep(2000);
				for (int i = 1; i < g + 1; i++) {

					String header = driver.findElement(By
							.xpath("//div[@id='gridmsg20receiptReport']/div/div[2]/div/div/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();
					// System.out.println(header);

					if (header.equals("Amount Due")) {

						RPAmountDue = driver.findElement(By
								.xpath("//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr/td["
										+ i + "]/div"))
								.getText();
						System.out.println("a" + RPAmountDue);
					}
				}

				String RPAmountDue2 = RPAmountDue.substring(RPAmountDue.indexOf(" "), RPAmountDue.length());
				RPAmountDue2 = RPAmountDue2.replace(",", "");
				double RPdue = Double.parseDouble(RPAmountDue2);

				if (RPdue == finaldue) {
					System.out.println("Amount Due of Customer RP  is Correct");
				} else {
					System.out.println("Amount due of Customer RP is InCorrect");
				}
			}

			Utilities.refresh();
			Utilities.waitandClick("//div[@id='navigationpanel']/div[1]/div", driver);
		}

		catch (Exception Ex) {
			Ex.printStackTrace();
		}

		return driver;
	}

	public static WebDriver AmountDueRPVenDE(String documentID, WebDriver driver) {
		try {
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentReport"), driver);
			Thread.sleep(2000);
			// Utilities.zoomOut();

			Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);
			Thread.sleep(2000);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Receipt No.")) {
					driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div/a"))
							.click();
				}
			}

			int f = driver
					.findElements(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size();
			// System.out.println(f);

			String EnterAmount = "";
			String Documenttype = "";
			for (int k = 1; k < f + 1; k++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='PaymentMainTabPanel']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["+k+"]/div")).getText());
				String header = driver.findElement(By
						.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ k + "]/div"))
						.getText();

				if (header.equals("Document Type")) {
					Documenttype = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				}
				if (header.equals("Enter Amount")) {
					EnterAmount = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td["
									+ k + "]/div"))
							.getText();
				}

			}
			String EnterAmount2 = EnterAmount.replace(",", "");
			double finalam = Double.parseDouble(EnterAmount2);

			Utilities.waitandClick(pro.getProperty("CloseViewRP"), driver);
			Utilities.waitandClick(pro.getProperty("ClosePaymentMadeReport"), driver);

			String RPAdDue = "";
			if (Documenttype.equals("Refund / Deposit")) {

				Utilities.waitandClick(pro.getProperty("ReceivePaymentReport"), driver);
				Thread.sleep(2000);
				Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);
				Thread.sleep(2000);

				int k = driver
						.findElements(By
								.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
						.size();
				for (int i = 1; i < k + 1; i++) {
					String header = driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();
					if (header.equals("Amount Due")) {
						RPAdDue = driver.findElement(By
								.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
										+ i + "]/div"))
								.getText();
					}
				}

				String RPAdDue2 = RPAdDue.substring(RPAdDue.indexOf(" "), RPAdDue.length());
				RPAdDue2 = RPAdDue2.replace(",", "");
				double fRPAdDue = Double.parseDouble(RPAdDue2);
				System.out.println(fRPAdDue);

				if (fRPAdDue == finalam) {
					System.out.println("Amount Due of RP Adavance due is Correct");
				} else {
					System.out.println("Amount due of RP Adavance is InCorrect");
				}
			}

			Utilities.refresh();
			Utilities.waitandClick("//div[@id='navigationpanel']/div[1]/div", driver);
		} catch (Exception EX) {
			EX.printStackTrace();
		}
		return driver;
	}

	public static WebDriver RPAmountDueGL(String documentID, WebDriver driver) {
		try {
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentReport"), driver);
			Thread.sleep(2000);
			// Utilities.zoomOut();
			// Thread.sleep(2000);
			Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);

			int k = driver
					.findElements(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td/div"))
					.size();
			String RPGLDue = "";
			for (int i = 1; i < k + 1; i++) {
				String header = driver.findElement(By
						.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				if (header.equals("Amount Due")) {
					RPGLDue = driver.findElement(By
							.xpath("//div[@id='receiptReport']/following::div[1]/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
									+ i + "]/div"))
							.getText();
				}
			}

			String RPGLDue2 = RPGLDue.substring(RPGLDue.indexOf(" "), RPGLDue.length());
			RPGLDue2 = RPGLDue2.replace(",", "");
			double CNdue = Double.parseDouble(RPGLDue2);

			if (CNdue == 0) {
				System.out.println("Amount Due of RP GL due is Correct");
			} else {
				System.out.println("Amount due of RP GL is InCorrect");
			}
			Utilities.refresh();
			Utilities.waitandClick("//div[@id='navigationpanel']/div[1]/div", driver);
		} catch (Exception EX) {
		}
		return driver;
	}

}
