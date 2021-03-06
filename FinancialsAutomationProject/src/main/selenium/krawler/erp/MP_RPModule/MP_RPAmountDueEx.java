package krawler.erp.MP_RPModule;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class MP_RPAmountDueEx {

	public static void MP_PIEXlinkAmountDueVeri(WebDriver driver, String documentID, String LinkingDocumentID,
			String linkingAmount) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");
			Properties pro1 = Utilities.fetchProValue("OR_CashPurchase.properties");

			Utilities.waitandClick(pro.getProperty("PaymentMadeReport"), driver);
			// Thread.sleep(2000);
			// Utilities.zoomOut();
			// Thread.sleep(2000);
			Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);

			String MPTotalAmount = "";

			String MPAmountDue = "";

			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Amount Paid")) {
					MPTotalAmount = driver.findElement(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				} else if (header.equals("Amount Due")) {
					MPAmountDue = driver.findElement(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}
			}
			String MPTotalAmount2 = MPTotalAmount.substring(MPTotalAmount.indexOf(" "), MPTotalAmount.length());
			MPTotalAmount2 = MPTotalAmount2.replace(",", "");
			double MPTotAmount2 = Double.parseDouble(MPTotalAmount2);

			String MPAmountDue2 = MPAmountDue.substring(MPAmountDue.indexOf(" "), MPAmountDue.length());
			MPAmountDue2 = MPAmountDue2.replace(",", "");
			double MPAmouDue2 = Double.parseDouble(MPAmountDue2);
			// System.out.println("Act"+MPAmouDue2);

			double linkingAmount2 = Double.parseDouble(linkingAmount);
			// System.out.println("Link"+linkingAmount2);
			double finalMPdue = MPTotAmount2 - linkingAmount2;
			// System.out.println("FINAl"+finalMPdue);
			if (finalMPdue == MPAmouDue2) {
				System.out.println("******* Amount Due is correct for Make Payment  :[" + documentID + "] ******* ");

			} else {
				System.out.println("******* Amount Due is Incorrect for Make Payment  :[" + documentID + "]  *******");

			}

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);

			Utilities.waitandClick(pro1.getProperty("SalesInvoiceReport"), driver);
			Thread.sleep(2000);
			Utilities.waitandSendkey(pro1.getProperty("QuickSearch"), driver, LinkingDocumentID);

			int m = driver
					.findElements(By
							.xpath("//div[@id='gridmsg21GRListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();

			String PIAmountDue = "";
			String PITotalAmount = "";

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

				else if (header.equals("Total Amount")) {

					PITotalAmount = driver.findElement(By
							.xpath("//div[@id='gridmsg21GRListEntry']/div/div/div/div/div[2]/div/div/table/tbody/tr/td["
									+ i + "]/div"))
							.getText();
				}

			}

			String PIAmountDue2 = PIAmountDue.substring(PIAmountDue.indexOf(" "), PIAmountDue.length());
			PIAmountDue2 = PIAmountDue2.replace(",", "");
			double pidue = Double.parseDouble(PIAmountDue2);
			// System.out.println("PI="+pidue);

			String PITotalAmount2 = PITotalAmount.substring(PITotalAmount.indexOf(" "), PITotalAmount.length());
			PITotalAmount2 = PITotalAmount2.replace(",", "");
			double PITotAm = Double.parseDouble(PITotalAmount2);
			// System.out.println("PI="+pidue);

			// System.out.println("Link"+linkingAmount2);
			double finalPIdue = PITotAm - linkingAmount2;

			if (finalPIdue == pidue) {
				System.out.println(
						"******* Amount Due is correct for Purchase Invoice  :[" + LinkingDocumentID + "] ******* ");

			} else {
				System.out.println(
						"******* Amount Due is Incorrect for Purchase Invoice  :[" + LinkingDocumentID + "]  *******");

			}

			Utilities.refresh();
			Utilities.clickExpander(driver);

		} catch (Exception Ex) {
			System.out.println("******* Fail  to verify Amount Due  for Make Payment  :[" + documentID
					+ "] and Purchase Invoice :[" + LinkingDocumentID + "] checlk LOG *******");
			Utilities.handleError(Ex, driver);

		}

	}

	public static void MP_CNVenEXlinkAmountDueVeri(WebDriver driver, String documentID, String LinkingDocumentID,
			String linkingAmount) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");
			Properties pro2 = Utilities.fetchProValue("OR_CreditNote_PurchaseInvoice.properties");
			Utilities.waitandClick(pro.getProperty("PaymentMadeReport"), driver);
			// Thread.sleep(2000);
			// Utilities.zoomOut();
			// Thread.sleep(2000);
			Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);

			String MPTotalAmount = "";

			String MPAmountDue = "";

			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Amount Paid")) {
					MPTotalAmount = driver.findElement(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				} else if (header.equals("Amount Due")) {
					MPAmountDue = driver.findElement(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}
			}
			String MPTotalAmount2 = MPTotalAmount.substring(MPTotalAmount.indexOf(" "), MPTotalAmount.length());
			MPTotalAmount2 = MPTotalAmount2.replace(",", "");
			double MPTotAmount2 = Double.parseDouble(MPTotalAmount2);

			String MPAmountDue2 = MPAmountDue.substring(MPAmountDue.indexOf(" "), MPAmountDue.length());
			MPAmountDue2 = MPAmountDue2.replace(",", "");
			double MPAmouDue2 = Double.parseDouble(MPAmountDue2);
			// System.out.println("Act"+MPAmouDue2);

			double linkingAmount2 = Double.parseDouble(linkingAmount);
			// System.out.println("Link"+linkingAmount2);
			double finalMPdue = MPTotAmount2 - linkingAmount2;
			// System.out.println("FINAl"+finalMPdue);
			if (finalMPdue == MPAmouDue2) {
				System.out.println("******* Amount Due is correct for Make Payment  :[" + documentID + "] ******* ");

			} else {
				System.out.println("******* Amount Due is Incorrect for Make Payment  :[" + documentID + "]  *******");

			}

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);
			Utilities.waitandClick(pro2.getProperty("CreditNoteReport"), driver);
			WebDriverWait wait = new WebDriverWait(driver, 50);
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro2.getProperty("DocumentCheckBox"))));
			Utilities.waitandSendkey(pro2.getProperty("CNTypeDD"), driver, "Credit Note for Vendors");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro2.getProperty("DocumentCheckBox"))));
			Utilities.waitandSendkey(pro2.getProperty("QuickSearch"), driver, LinkingDocumentID);

			int g = driver
					.findElements(By
							.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(g);

			String CNAmountDue = "";
			String CNTotalAmount = "";

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
				} else if (header.equals("Amount")) {

					CNTotalAmount = driver.findElement(By
							.xpath("//div[@id='gridmsg19CreditNoteDetails']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
					// System.out.println("a"+CNAmountDue);
				}

			}

			String CNAmountDue2 = CNAmountDue.substring(CNAmountDue.indexOf(" "), CNAmountDue.length());
			CNAmountDue2 = CNAmountDue2.replace(",", "");
			double CNdue = Double.parseDouble(CNAmountDue2);

			String CNTotalAmount2 = CNTotalAmount.substring(CNTotalAmount.indexOf(" "), CNTotalAmount.length());
			CNTotalAmount2 = CNTotalAmount2.replace(",", "");
			double CNTotAm = Double.parseDouble(CNTotalAmount2);
			// System.out.println("PI="+pidue);

			// System.out.println("Link"+linkingAmount2);
			double finalPIdue = CNTotAm - linkingAmount2;

			if (finalPIdue == CNdue) {
				System.out.println("******* Amount Due is correct for Credit Note for Vendor  :[" + LinkingDocumentID
						+ "] ******* ");

			} else {
				System.out.println("******* Amount Due is Incorrect for Credit Note for Vendor  :[" + LinkingDocumentID
						+ "]  *******");

			}

			Utilities.refresh();
			Utilities.clickExpander(driver);

		} catch (Exception Ex) {
			System.out.println("******* Fail  to verify Amount Due  for Make Payment  :[" + documentID
					+ "] and Credit Note for Vendor :[" + LinkingDocumentID + "] checlk LOG *******");
			Utilities.handleError(Ex, driver);

		}

	}

	public static void MP_RPEXlinkAmountDueVeri(WebDriver driver, String documentID, String LinkingDocumentID,
			String linkingAmount) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");
			Properties pro2 = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("PaymentMadeReport"), driver);
			// Thread.sleep(2000);
			// Utilities.zoomOut();
			// Thread.sleep(2000);
			Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);

			String MPTotalAmount = "";

			String MPAmountDue = "";

			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Amount Paid")) {
					MPTotalAmount = driver.findElement(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				} else if (header.equals("Amount Due")) {
					MPAmountDue = driver.findElement(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}
			}
			String MPTotalAmount2 = MPTotalAmount.substring(MPTotalAmount.indexOf(" "), MPTotalAmount.length());
			MPTotalAmount2 = MPTotalAmount2.replace(",", "");
			double MPTotAmount2 = Double.parseDouble(MPTotalAmount2);

			String MPAmountDue2 = MPAmountDue.substring(MPAmountDue.indexOf(" "), MPAmountDue.length());
			MPAmountDue2 = MPAmountDue2.replace(",", "");
			double MPAmouDue2 = Double.parseDouble(MPAmountDue2);
			// System.out.println("Act"+MPAmouDue2);

			double linkingAmount2 = Double.parseDouble(linkingAmount);
			// System.out.println("Link"+linkingAmount2);
			double finalMPdue = MPTotAmount2 - linkingAmount2;
			// System.out.println("FINAl"+finalMPdue);
			if (finalMPdue == MPAmouDue2) {
				System.out.println("******* Amount Due is correct for Make Payment  :[" + documentID + "] ******* ");

			} else {
				System.out.println("******* Amount Due is Incorrect for Make Payment  :[" + documentID + "]  *******");

			}

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);
			Utilities.waitandClick(pro2.getProperty("ReceivePaymentReport"), driver);
			Thread.sleep(2000);
			// Utilities.zoomOut();

			Utilities.waitandSendkey(pro2.getProperty("QuickSearch"), driver, LinkingDocumentID);
			Thread.sleep(2000);

			int g = driver
					.findElements(By
							.xpath("//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(g);

			String RPAmountDue = "";
			// System.out.println("a"+RPAmountDue);
			String RPTotalAmount = "";
			// System.out.println("a"+RPTotalAmount);

			Thread.sleep(2000);
			for (int i = 1; i < g + 1; i++) {

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				// System.out.println(header);

				if (header.equals("Amount Due")) {

					RPAmountDue = driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();

				} else if (header.equals("Amount Received")) {

					RPTotalAmount = driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();

				}

			}

			String RPAmountDue2 = RPAmountDue.substring(RPAmountDue.indexOf(" "), RPAmountDue.length());
			RPAmountDue2 = RPAmountDue2.replace(",", "");
			double RPFinDue2 = Double.parseDouble(RPAmountDue2);

			String RPTotalAmount2 = RPTotalAmount.substring(RPTotalAmount.indexOf(" "), RPTotalAmount.length());
			RPTotalAmount2 = RPTotalAmount2.replace(",", "");
			double RPTotAmount2 = Double.parseDouble(RPTotalAmount2);
			// System.out.println("Act"+MPAmouDue2);

			double finalRPdue = RPTotAmount2 - linkingAmount2;
			// System.out.println("FINAl"+finalMPdue);
			if (finalRPdue == RPFinDue2) {
				System.out
						.println("******* Amount Due is correct for Receive payment   :[" + documentID + "] ******* ");

			} else {
				System.out.println(
						"******* Amount Due is Incorrect for Receive payment    :[" + documentID + "]  *******");

			}

			Utilities.refresh();
			Utilities.clickExpander(driver);

		} catch (Exception Ex) {
			System.out.println("******* Fail  to verify Amount Due  for Make Payment  :[" + documentID
					+ "] and Receive payment   :[" + LinkingDocumentID + "] checlk LOG *******");
			Utilities.handleError(Ex, driver);

		}

	}

	public static void RP_SIEXlinkAmountDueVeri(WebDriver driver, String documentID, String LinkingDocumentID,
			String linkingAmount) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");
			Properties pro1 = Utilities.fetchProValue("OR_CashSale.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentReport"), driver);
			// Thread.sleep(2000);
			// Utilities.zoomOut();
			// Thread.sleep(2000);
			Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);

			String RPTotalAmount = "";

			String RPAmountDue = "";

			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Amount Received")) {
					RPTotalAmount = driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				} else if (header.equals("Amount Due")) {
					RPAmountDue = driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}
			}
			String RPTotalAmount2 = RPTotalAmount.substring(RPTotalAmount.indexOf(" "), RPTotalAmount.length());
			RPTotalAmount2 = RPTotalAmount2.replace(",", "");
			double RPTotAmount2 = Double.parseDouble(RPTotalAmount2);

			String RPAmountDue2 = RPAmountDue.substring(RPAmountDue.indexOf(" "), RPAmountDue.length());
			RPAmountDue2 = RPAmountDue2.replace(",", "");
			double RPAmouDue2 = Double.parseDouble(RPAmountDue2);
			// System.out.println("Act"+MPAmouDue2);

			double linkingAmount2 = Double.parseDouble(linkingAmount);
			// System.out.println("Link"+linkingAmount2);
			double finalRPdue = RPTotAmount2 - linkingAmount2;
			// System.out.println("FINAl"+finalMPdue);
			if (finalRPdue == RPAmouDue2) {
				System.out.println("******* Amount Due is correct for Receive Payment  :[" + documentID + "] ******* ");

			} else {
				System.out
						.println("******* Amount Due is Incorrect for Receive Payment  :[" + documentID + "]  *******");

			}

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);

			Utilities.waitandClick(pro1.getProperty("SalesInvoiceReport"), driver);
			Thread.sleep(2000);
			Utilities.waitandSendkey(pro1.getProperty("QuickSearch"), driver, LinkingDocumentID);

			int m = driver
					.findElements(By
							.xpath("//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();

			String SIAmountDue = "";
			String SITotalAmount = "";

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

				else if (header.equals("Total Amount")) {

					SITotalAmount = driver.findElement(By
							.xpath("//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div/div[2]/div/div/table/tbody/tr/td["
									+ i + "]/div"))
							.getText();
				}

			}

			String SIAmountDue2 = SIAmountDue.substring(SIAmountDue.indexOf(" "), SIAmountDue.length());
			SIAmountDue2 = SIAmountDue2.replace(",", "");
			double Sidue = Double.parseDouble(SIAmountDue2);
			// System.out.println("PI="+pidue);

			String SITotalAmount2 = SITotalAmount.substring(SITotalAmount.indexOf(" "), SITotalAmount.length());
			SITotalAmount2 = SITotalAmount2.replace(",", "");
			double SITotAm = Double.parseDouble(SITotalAmount2);
			// System.out.println("PI="+pidue);

			// System.out.println("Link"+linkingAmount2);
			double finalSIdue = SITotAm - linkingAmount2;

			if (finalSIdue == Sidue) {
				System.out.println(
						"******* Amount Due is correct for Sales Invoice  :[" + LinkingDocumentID + "] ******* ");

			} else {
				System.out.println(
						"******* Amount Due is Incorrect for Sales Invoice  :[" + LinkingDocumentID + "]  *******");

			}

			Utilities.refresh();
			Utilities.clickExpander(driver);

		} catch (Exception Ex) {
			System.out.println("******* Fail  to verify Amount Due  for Receive Payment  :[" + documentID
					+ "] and Sales Invoice :[" + LinkingDocumentID + "] checlk LOG *******");
			Utilities.handleError(Ex, driver);

		}

	}

	public static void RP_DNEXlinkAmountDueVeri(WebDriver driver, String documentID, String LinkingDocumentID,
			String linkingAmount) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");
			Properties pro1 = Utilities.fetchProValue("OR_DebitNoteAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentReport"), driver);
			// Thread.sleep(2000);
			// Utilities.zoomOut();
			// Thread.sleep(2000);
			Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);

			String RPTotalAmount = "";

			String RPAmountDue = "";

			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Amount Received")) {
					RPTotalAmount = driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				} else if (header.equals("Amount Due")) {
					RPAmountDue = driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}
			}
			String RPTotalAmount2 = RPTotalAmount.substring(RPTotalAmount.indexOf(" "), RPTotalAmount.length());
			RPTotalAmount2 = RPTotalAmount2.replace(",", "");
			double RPTotAmount2 = Double.parseDouble(RPTotalAmount2);

			String RPAmountDue2 = RPAmountDue.substring(RPAmountDue.indexOf(" "), RPAmountDue.length());
			RPAmountDue2 = RPAmountDue2.replace(",", "");
			double RPAmouDue2 = Double.parseDouble(RPAmountDue2);
			// System.out.println("Act"+MPAmouDue2);

			double linkingAmount2 = Double.parseDouble(linkingAmount);
			// System.out.println("Link"+linkingAmount2);
			double finalRPdue = RPTotAmount2 - linkingAmount2;
			// System.out.println("FINAl"+finalMPdue);
			if (finalRPdue == RPAmouDue2) {
				System.out.println("******* Amount Due is correct for Receive Payment  :[" + documentID + "] ******* ");

			} else {
				System.out
						.println("******* Amount Due is Incorrect for Receive Payment  :[" + documentID + "]  *******");

			}

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);

			Utilities.waitandClick(pro1.getProperty("DebitNoteReport"), driver);
			WebDriverWait wait = new WebDriverWait(driver, 50);
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro2.getProperty("DocumentCheckBox"))));
			Utilities.waitandSendkey(pro1.getProperty("DNTypeDD"), driver, "Debit Note for Customers");
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro1.getProperty("DocumentCheckBox"))));

			int m = driver
					.findElements(By
							.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();

			String DNAmountDue = "";
			String DNTotalAmount = "";

			for (int i = 1; i < m + 1; i++) {

				String header = driver.findElement(By
						.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Amount Due")) {

					DNAmountDue = driver.findElement(By
							.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div/div/div/div[2]/div/div/table/tbody/tr/td["
									+ i + "]/div"))
							.getText();
				}

				else if (header.equals("Amount")) {

					DNTotalAmount = driver.findElement(By
							.xpath("//div[@id='gridmsg22DebitNoteDetails']/div/div/div/div/div[2]/div/div/table/tbody/tr/td["
									+ i + "]/div"))
							.getText();
				}

			}

			String DNAmountDue2 = DNAmountDue.substring(DNAmountDue.indexOf(" "), DNAmountDue.length());
			DNAmountDue2 = DNAmountDue2.replace(",", "");
			double DNdue = Double.parseDouble(DNAmountDue2);
			// System.out.println("PI="+pidue);

			String DNTotalAmount2 = DNTotalAmount.substring(DNTotalAmount.indexOf(" "), DNTotalAmount.length());
			DNTotalAmount2 = DNTotalAmount2.replace(",", "");
			double DNTotAm = Double.parseDouble(DNTotalAmount2);
			// System.out.println("PI="+pidue);

			// System.out.println("Link"+linkingAmount2);
			double finalDNdue = DNTotAm - linkingAmount2;

			if (finalDNdue == DNdue) {
				System.out
						.println("******* Amount Due is correct for Debit Note  :[" + LinkingDocumentID + "] ******* ");

			} else {
				System.out.println(
						"******* Amount Due is Incorrect for Debit Note :[" + LinkingDocumentID + "]  *******");

			}

			Utilities.refresh();
			Utilities.clickExpander(driver);

		} catch (Exception Ex) {
			System.out.println("******* Fail  to verify Amount Due  for Receive Payment  :[" + documentID
					+ "] and Debit Note :[" + LinkingDocumentID + "] checlk LOG *******");
			Utilities.handleError(Ex, driver);

		}

	}

	public static void RP_MPEXlinkAmountDueVeri(WebDriver driver, String documentID, String LinkingDocumentID,
			String linkingAmount) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");
			Properties pro2 = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro2.getProperty("ReceivePaymentReport"), driver);
			Thread.sleep(2000);
			// Utilities.zoomOut();

			Utilities.waitandSendkey(pro2.getProperty("QuickSearch"), driver, documentID);
			Thread.sleep(2000);

			int g = driver
					.findElements(By
							.xpath("//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(g);

			String RPAmountDue = "";
			// System.out.println("a"+RPAmountDue);
			String RPTotalAmount = "";
			// System.out.println("a"+RPTotalAmount);

			Thread.sleep(2000);
			for (int i = 1; i < g + 1; i++) {

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();
				// System.out.println(header);

				if (header.equals("Amount Due")) {

					RPAmountDue = driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();

				} else if (header.equals("Amount Received")) {

					RPTotalAmount = driver.findElement(By
							.xpath(".//div[@id='gridmsg20receiptReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();

				}

			}

			String RPAmountDue2 = RPAmountDue.substring(RPAmountDue.indexOf(" "), RPAmountDue.length());
			RPAmountDue2 = RPAmountDue2.replace(",", "");
			double RPFinDue2 = Double.parseDouble(RPAmountDue2);

			String RPTotalAmount2 = RPTotalAmount.substring(RPTotalAmount.indexOf(" "), RPTotalAmount.length());
			RPTotalAmount2 = RPTotalAmount2.replace(",", "");
			double RPTotAmount2 = Double.parseDouble(RPTotalAmount2);
			// System.out.println("Act"+MPAmouDue2);

			double linkingAmount2 = Double.parseDouble(linkingAmount);
			// System.out.println("Link"+linkingAmount2);

			double finalRPdue = RPTotAmount2 - linkingAmount2;
			// System.out.println("FINAl"+finalMPdue);
			if (finalRPdue == RPFinDue2) {
				System.out
						.println("******* Amount Due is correct for Receive payment   :[" + documentID + "] ******* ");

			} else {
				System.out.println(
						"******* Amount Due is Incorrect for Receive payment    :[" + documentID + "]  *******");

			}

			Utilities.waitandClick(pro.getProperty("Dashboard"), driver);

			Utilities.waitandClick(pro.getProperty("PaymentMadeReport"), driver);
			// Thread.sleep(2000);
			// Utilities.zoomOut();
			// Thread.sleep(2000);
			Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, LinkingDocumentID);

			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);

			Thread.sleep(3000);

			String MPTotalAmount = "";

			String MPAmountDue = "";

			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());

				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Amount Paid")) {
					MPTotalAmount = driver.findElement(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				} else if (header.equals("Amount Due")) {
					MPAmountDue = driver.findElement(By
							.xpath(".//div[@id='gridmsg23paymentReport']/div/div[2]/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
									+ i + "]/div"))
							.getText();
				}
			}
			String MPTotalAmount2 = MPTotalAmount.substring(MPTotalAmount.indexOf(" "), MPTotalAmount.length());
			MPTotalAmount2 = MPTotalAmount2.replace(",", "");
			double MPTotAmount2 = Double.parseDouble(MPTotalAmount2);

			String MPAmountDue2 = MPAmountDue.substring(MPAmountDue.indexOf(" "), MPAmountDue.length());
			MPAmountDue2 = MPAmountDue2.replace(",", "");
			double MPAmouDue2 = Double.parseDouble(MPAmountDue2);
			// System.out.println("Act"+MPAmouDue2);

			double finalMPdue = MPTotAmount2 - linkingAmount2;
			// System.out.println("FINAl"+finalMPdue);
			if (finalMPdue == MPAmouDue2) {
				System.out.println(
						"******* Amount Due is correct for Make Payment  :[" + LinkingDocumentID + "] ******* ");

			} else {
				System.out.println(
						"******* Amount Due is Incorrect for Make Payment  :[" + LinkingDocumentID + "]  *******");

			}

			Utilities.refresh();
			Utilities.clickExpander(driver);

		} catch (Exception Ex) {
			System.out.println("******* Fail  to verify Amount Due  for Make Payment  :[" + documentID
					+ "] and Receive payment   :[" + LinkingDocumentID + "] checlk LOG *******");
			Utilities.handleError(Ex, driver);

		}

	}

}
