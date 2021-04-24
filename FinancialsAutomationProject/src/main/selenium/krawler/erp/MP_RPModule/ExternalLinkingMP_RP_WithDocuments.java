package krawler.erp.MP_RPModule;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import krawler.erp.page.Utilities;

public class ExternalLinkingMP_RP_WithDocuments {

	public static void MP_PI_CN_RPAdExternalLinking(WebDriver driver, String documentID, String LinkingDocumentID,
			String linkingAmount) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");

			Utilities.waitandClick(pro.getProperty("PaymentMadeReport"), driver);
			Thread.sleep(2000);
			// Utilities.zoomOut();

			Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);
			Thread.sleep(2000);

			Utilities.waitandClick(pro.getProperty("DocumentCheckBox"), driver);
			Utilities.clickButton("Link Transaction", 200, driver);

			int i = driver
					.findElements(By
							.xpath("//div[@id='LinkAdvancePayment']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size();
			for (int k = 1; k <= i; k++) {
				String header = driver.findElement(By
						.xpath("//div[@id='LinkAdvancePayment']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ k + "]/div"))
						.getText();
				// System.out.println(header);

				if (header.equals("Amount")) {

					Utilities.clickAndEnterText(linkingAmount, "//*[@id='LinkAdvancePayment']//*[contains(text(),'"
							+ LinkingDocumentID + "')]/ancestor::tr/td[" + k + "]", driver);
					Utilities.Enter();
				}

			}

			Utilities.waitandClick(pro.getProperty("LinkTransactionButton"), driver);
			Utilities.clickButton("OK", 200, driver);
			Utilities.waitandClick(pro.getProperty("CloseLinkingTab"), driver);
			Utilities.waitandClick(pro.getProperty("ClosePaymentMadeReport"), driver);
			System.out.println("*******  Make Payment  :[" + documentID + "] is linked Sucessfully with PI/CN :["
					+ LinkingDocumentID + "]");

		} catch (Exception EX) {
			System.out.println("******* Unble To link Make Payment  :[" + documentID + "] with PI/CN :["
					+ LinkingDocumentID + "] checlk LOG *******");
			Utilities.handleError(EX, driver);

		}
	}

	public static void RP_SI_DN_MPAdExternalLinking(WebDriver driver, String documentID, String LinkingDocumentID,
			String linkingAmount) throws InterruptedException, AWTException, IOException {
		try {
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			Utilities.waitandClick(pro.getProperty("ReceivePaymentReport"), driver);
			Thread.sleep(2000);
			// Utilities.zoomOut();

			Utilities.waitandSendkey(pro.getProperty("QuickSearch"), driver, documentID);
			Thread.sleep(2000);

			Utilities.waitandClick(pro.getProperty("DocumentCheckBox"), driver);
			Utilities.clickButton("Link Transaction", 200, driver);

			int i = driver
					.findElements(By
							.xpath("//div[@id='LinkAdvanceReceipt']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size();
			for (int k = 1; k <= i; k++) {
				String header = driver.findElement(By
						.xpath("//div[@id='LinkAdvanceReceipt']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ k + "]/div"))
						.getText();
				// System.out.println(header);

				if (header.equals("Amount")) {

					Utilities.clickAndEnterText(linkingAmount, "//*[@id='LinkAdvanceReceipt']//*[contains(text(),'"
							+ LinkingDocumentID + "')]/ancestor::tr/td[" + k + "]", driver);
					Utilities.Enter();
				}

			}

			Utilities.waitandClick(pro.getProperty("LinkTransactionButton"), driver);
			Utilities.clickButton("OK", 200, driver);
			Utilities.waitandClick(pro.getProperty("CloseLinkingTab"), driver);
			Utilities.waitandClick(pro.getProperty("ClosePaymentMadeReport"), driver);
			System.out.println("*******  Receive Payment  :[" + documentID
					+ "] is linked Sucessfully with SI/DN/MP(Advance) :[" + LinkingDocumentID + "]");

		} catch (Exception EX) {
			System.out.println("******* Unble To link Receive Payment  :[" + documentID + "] with SI/DN/MP(Advance):["
					+ LinkingDocumentID + "] checlk LOG *******");
			Utilities.handleError(EX, driver);

		}
	}

}
