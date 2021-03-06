package krawler.erp.BankReconcilation;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.EmailFunctionality;
import krawler.erp.page.Utilities;

public class DocumentCreationForBR {
	public static WebDriver Create_Makepayment(WebDriver driver, String documentID, String BankName, String vendor_ID,
			String DocumentCurrency, String ForexCurrecny1) {

		try {
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstVendor.properties");
			WebDriverWait wait = new WebDriverWait(driver, 50);
			Thread.sleep(2000);

			Utilities.waitandClick(pro.getProperty("MakePaymentIcon"), driver);
			Thread.sleep(2000);

			Utilities.waitandClick(pro.getProperty("MakePaymentAgainstVendorCheck"), driver);
			Utilities.clickButton("Submit", 1000, driver);
			Utilities.waitandSendkey(pro.getProperty("SequenceFormat"), driver, "NA");
			Utilities.Enter();
			Utilities.waitandSendkey(pro.getProperty("DocumentNo"), driver, "MPVenAd" + documentID);
			Utilities.Enter();
			Utilities.waitandSendkey(pro.getProperty("vendorID"), driver, vendor_ID);
			Utilities.Enter();
			Utilities.waitandSendkey(pro.getProperty("PaymentMethod"), driver, BankName);
			Utilities.Enter();
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}
			} catch (Exception Ex) {

			}

			Utilities.waitandSendkey(pro.getProperty("DocumentCurrency"), driver, DocumentCurrency);
			Utilities.Enter();
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			if (DocumentCurrency.equals(ForexCurrecny1)) {
				Utilities.clickButton("Yes", 2000, driver);
			}

			if (!BankName.equals("Cash")) {
				// WebElement Che =
				// driver.findElement(By.xpath(pro.getProperty("ChequeSeq")));
				// Che.click();
				// Che.clear();
				// Che.sendKeys("NA");
				Utilities.waitandSendkey(pro.getProperty("ChequeSequence"), driver, "NA");
				Utilities.Tab();
				Utilities.waitandSendkey(pro.getProperty("ChequeNo"), driver, "CNo" + documentID);
				Utilities.Tab();
			}
			List<WebElement> header = driver.findElements(By.xpath(
					"//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"));
			int totalHeadCnt = header.size();

			int indexOfDocType = 0, indexOfDocNo = 0, indexOfAmount = 0;
			Robot r = new Robot();
			for (int i = 1; i <= totalHeadCnt; i++) {
				String headName = driver.findElement(By
						.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (headName.equalsIgnoreCase("Document Type")) {
					indexOfDocType = i;
				} else if (headName.equalsIgnoreCase("Document Number")) {
					indexOfDocNo = i;
				} else if (headName.equalsIgnoreCase("Enter Amount")) {
					indexOfAmount = i;
				}
			}

			// System.out.println(indexOfDocType);System.out.println(indexOfDocNo);System.out.println(indexOfAmount);
			// add code for Advance-Deposite

			WebElement docTyp = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
							+ indexOfDocType + "]/div")));
			docTyp.click();
			Thread.sleep(2000);
			Utilities.sendText("Advanced / Deposit");
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);
			Thread.sleep(1500);

			WebElement ammount = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
							+ indexOfAmount + "]/div")));
			ammount.click();
			Thread.sleep(1500);
			Utilities.sendText("1000");
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);
			Thread.sleep(1500);

			Utilities.clickButton("Save", 2000, driver);
			Utilities.clickButton("Yes", 2000, driver);
			{
				try {
					WebElement button = new WebDriverWait(driver, 5)
							.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
					if (button.isDisplayed()) {
						Thread.sleep(20);
						button.click();
					}

				} catch (Exception Ex) {
				}
			}
			Utilities.clickButton("OK", 2000, driver);
			Utilities.waitandClick(pro.getProperty("CloseMakePayment"), driver);

		}

		catch (Exception EX) {
			EX.printStackTrace();
		}

		return driver;
	}

	public static void create_ReceivePaymentAgainstCustomer(String documentID, String AdvanceAmt, String InvoiceAmt,
			String CreditNoteAmt, String GLAmt, String CheBankname, String customer, WebDriver driver, String BankName,
			String DocumentCurrency, String ForexCurrecny1) throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstCustomer.properties");

			WebDriverWait wait = new WebDriverWait(driver, 45);

			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentIcon"))));
			element.click();
			Thread.sleep(2000);// pro

			element = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentAgainstCustomerCheck"))));
			element.click();
			Thread.sleep(1000);

			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
			element.click();
			Thread.sleep(1000);

			Utilities.enterTextandSelect("NA", pro.getProperty("SequenceFormat"), driver);

			Utilities.enterTextInDropDown("RPcust" + documentID, pro.getProperty("DocumentNo"), driver);
			Thread.sleep(2000);
			// Utilities.enterTextInDropDown(customer,
			// pro.getProperty("customerID"), driver);
			Utilities.enterTextandSelect(customer, pro.getProperty("customerID"), driver);

			Thread.sleep(1000);

			Utilities.waitandSendkey(pro.getProperty("PaymentMethod"), driver, BankName);
			Utilities.Enter();

			Utilities.waitandClick(pro.getProperty("Memo"), driver);
			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}

			} catch (Exception EX) {

			}

			/*
			 * try{ WebElement button=new
			 * WebDriverWait(driver,5).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath("//button[text()='Yes']")));
			 * if(button.isDisplayed()) { Thread.sleep(20); button.click(); }
			 * }catch(Exception Ex){
			 * 
			 * } //
			 */
			Utilities.waitandSendkey(pro.getProperty("DocumentCurrency"), driver, DocumentCurrency);
			Utilities.Enter();
			Utilities.waitandClick(pro.getProperty("Memo"), driver);
			/*
			 * try{ WebElement button=new
			 * WebDriverWait(driver,5).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath("//button[text()='Yes']")));
			 * if(button.isDisplayed()) { Thread.sleep(20); button.click(); }
			 * }catch(Exception Ex){
			 * 
			 * }
			 */
			if (DocumentCurrency.equals(ForexCurrecny1)) {
				Utilities.clickButton("Yes", 2000, driver);
			}

			if (!BankName.equals("Cash")) {
				Utilities.waitandSendkey(pro.getProperty("ChequeNo"), driver, "CheNO" + documentID);
				Utilities.Enter();

				// Below method can be used for bank name selection but due to
				// issue, used alernate method it can be used after ERP-43258
				// get fixed
				// Method 1
				/*
				 * Utilities.waitandSendkey(pro.getProperty("CheBankName"),
				 * driver, CheBankname); Thread.sleep(1000); Utilities.Enter();
				 */

				// Method 2
				Utilities.waitandClick(pro.getProperty("CheBankNameD"), driver);
				Robot R = new Robot();
				R.keyPress(KeyEvent.VK_DOWN);
				R.keyRelease(KeyEvent.VK_DOWN);
				R.keyPress(KeyEvent.VK_DOWN);
				Utilities.Enter();

			}

			List<WebElement> header = driver.findElements(By.xpath(
					"//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"));
			int totalHeadCnt = header.size();

			int indexOfDocType = 0, indexOfDocNo = 0, indexOfAmount = 0;
			Robot r = new Robot();
			for (int i = 1; i <= totalHeadCnt; i++) {
				String headName = driver.findElement(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (headName.equalsIgnoreCase("Document Type")) {
					indexOfDocType = i;
				} else if (headName.equalsIgnoreCase("Document Number")) {
					indexOfDocNo = i;
				} else if (headName.equalsIgnoreCase("Enter Amount")) {
					indexOfAmount = i;
				}
			}

			// System.out.println(indexOfDocType);System.out.println(indexOfDocNo);System.out.println(indexOfAmount);
			// add code for Advance-Deposite

			WebElement docTyp = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
							+ indexOfDocType + "]/div")));
			docTyp.click();
			Thread.sleep(2000);
			Utilities.sendText("Advanced / Deposit");
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);
			Thread.sleep(1500);

			WebElement ammount = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
							+ indexOfAmount + "]/div/div")));
			ammount.click();
			Thread.sleep(1500);
			Utilities.sendText(AdvanceAmt);
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);
			Thread.sleep(1500);
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SaveButton"))));
			driver.findElement(By.xpath(pro.getProperty("SaveButton"))).click();

			Thread.sleep(1000);
			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			try {
				WebElement button = new WebDriverWait(driver, 5)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
				if (button.isDisplayed()) {
					Thread.sleep(20);
					button.click();
				}
			} catch (Exception Ex) {

			}

			// Utilities.clickButton("Yes", 2000, driver);

			Utilities.clickButton("OK", 500, driver);

			// Email code
			// String subjectLine="Receive Payment Against Customer - testsmoke
			// - "+documentID;
			// EmailFunctionality.email_AfterSave(documentID,subjectLine,driver);

			Thread.sleep(1500);
			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseMakePayment"))));
			driver.findElement(By.xpath(pro.getProperty("CloseMakePayment"))).click();

			System.out.println("Receive Payment Against Customer is Pass for doc : -> " + documentID);

		} catch (Exception EX) {
			System.out.println("Receive Payment Against Customer >>>>>FAIL<<<<<< doc : - " + documentID);
			EX.printStackTrace();
			// Utilities.handleError(EX, driver);
		}
	}

	/****************************************************/

	public static void create_cashPurchase(String documentID, String productid, String vendorid, WebDriver driver,
			String BankName) throws InterruptedException, AWTException, IOException {
		try {
			documentID = "CshPur" + documentID;
			Properties CashPurchase = Utilities.fetchProValue("OR_CashPurchase.properties");
			Thread.sleep(3000);
			// WebDriverWait wait = new WebDriverWait(driver, 30);
			// clicked on Purchase Invoice
			// System.out.println(driver.findElement(By.xpath(CashPurchase.getProperty("CreateCashPurchaseIcon"))).isEnabled());
			new WebDriverWait(driver, 60).until(ExpectedConditions
					.elementToBeClickable(By.xpath(CashPurchase.getProperty("CreateCashPurchaseIcon"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("CreateCashPurchaseIcon"))).click();
			Thread.sleep(10000);// pro

			// enter vendor name
			WebElement vendor = driver.findElement(By.xpath(CashPurchase.getProperty("vendorName")));
			vendor.clear();
			vendor.sendKeys(vendorid);
			Thread.sleep(3000);// pro
			vendor.sendKeys(Keys.ENTER);

			// sequen format document no.
			WebElement squence = driver.findElement(By.xpath(CashPurchase.getProperty("sequenceFormat")));
			Thread.sleep(1000);// pro
			squence.clear();
			Thread.sleep(1000);
			squence.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// vendor invoice no.

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("vendorInvoiceNo"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("vendorInvoiceNo"))).sendKeys(documentID);

			Utilities.waitandSendkey(CashPurchase.getProperty("PaymentMethod"), driver, BankName);
			Utilities.Enter();

			Utilities.waitandClick(CashPurchase.getProperty("memo"), driver);

			Utilities.waitandSendkey(CashPurchase.getProperty("CheNO"), driver, "CheNo" + documentID);

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("addButton"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("addButton"))).click();

			// search product id
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("productId"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("productId"))).sendKeys(productid);
			Thread.sleep(4000);// pro

			// select check box
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("checkBox"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("checkBox"))).click();
			Thread.sleep(2000);// pro

			// click Add

			Utilities.waitandClick(CashPurchase.getProperty("addproduct"), driver);
			// Click on select NA of customer ID

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='purchasereceiptinvoicegrid']/div[2]/div/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='purchasereceiptinvoicegrid']/div[2]/div/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Quantity")) {
					driver.findElement(By
							.xpath("//div[@id='purchasereceiptinvoicegrid']/div[2]/div/div/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					break;
				}
			}

			/*
			 * new WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(CashPurchase.getProperty(
			 * "selectNAcustomerId")))); WebElement quantity =
			 * driver.findElement(By.xpath(CashPurchase.getProperty(
			 * "selectNAcustomerId"))); quantity.click();
			 * Thread.sleep(1000);//pro
			 */

			Thread.sleep(2000);
			Utilities.sendText("1");
			Thread.sleep(2000);

			driver.findElement(By.xpath(CashPurchase.getProperty("memo"))).click(); // memo
			// ------------------------------------------------------------------------------------------------------------------------------------------------

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("saveButton"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("saveButton"))).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("yesButton"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("yesButton"))).click();

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("okButton"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("okButton"))).click();
			Thread.sleep(1000);

			// Email code
			// String subjectLine="Cash Purchase(Product) - testsmoke -
			// "+documentID;
			// EmailFunctionality.email_AfterSave(documentID,subjectLine,driver);
			// Thread.sleep(1000);

			System.out.println("Cash Purchase(Product) Successfull for  - " + documentID);

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("clickOk"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("clickOk"))).click();

		} catch (Exception EX) {
			System.out.println("Cash Purchase(Product) !!!!! Failed !!!!!! for  - " + documentID);
			Utilities.handleError(EX, driver);
		}

	}

	/************************************/

	public static void create_cashSale(String documentID, String productid, String CustomerId, String BankName,
			WebDriver driver, String CheBankname) throws InterruptedException, AWTException, IOException {
		try {
			documentID = "CS" + documentID;
			Properties CashSale = Utilities.fetchProValue("OR_CashSale.properties");
			Thread.sleep(3000);
			// WebDriverWait wait = new WebDriverWait(driver, 30);

			// clicked on Purchase Invoice
			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("CreateCashSaleIcon"))));
			driver.findElement(By.xpath(CashSale.getProperty("CreateCashSaleIcon"))).click();
			Thread.sleep(3000);// pro

			// enter vendor name
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("CustomerId"))));
			WebElement customer = driver.findElement(By.xpath(CashSale.getProperty("CustomerId")));
			customer.clear();
			customer.sendKeys(CustomerId);
			Thread.sleep(3000);// pro
			customer.sendKeys(Keys.ENTER);

			// sequen format document no.
			WebElement squence = driver.findElement(By.xpath(CashSale.getProperty("sequenceFormat")));
			Thread.sleep(500);
			squence.clear();
			squence.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("CashSaleNo"))));
			driver.findElement(By.xpath(CashSale.getProperty("CashSaleNo"))).sendKeys(documentID);

			Utilities.waitandSendkey(CashSale.getProperty("PaymentMethod"), driver, BankName);
			Utilities.waitandClick(CashSale.getProperty("Memo"), driver);

			Utilities.waitandSendkey(CashSale.getProperty("CheNo"), driver, "CheNo" + documentID);

			// Below method can be used for bank name selection but due to
			// issue, used alernate method it can be used after ERP-43258 get
			// fixed
			// Method 1
			/*
			 * Utilities.waitandSendkey(pro.getProperty("CheBankName"), driver,
			 * CheBankname); Thread.sleep(1000); Utilities.Enter();
			 */

			// Method 2
			Utilities.waitandClick(CashSale.getProperty("BankName"), driver);
			Robot R = new Robot();
			R.keyPress(KeyEvent.VK_DOWN);
			R.keyRelease(KeyEvent.VK_DOWN);
			R.keyPress(KeyEvent.VK_DOWN);
			Utilities.Enter();

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("addButton"))));
			driver.findElement(By.xpath(CashSale.getProperty("addButton"))).click();

			// search product id
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("productId"))));
			driver.findElement(By.xpath(CashSale.getProperty("productId"))).sendKeys(productid);
			Thread.sleep(2000);// pro

			// select check box
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("clickOncheckBox"))));
			driver.findElement(By.xpath(CashSale.getProperty("clickOncheckBox"))).click();
			Thread.sleep(2000);// pro

			Utilities.waitandClick(CashSale.getProperty("addproduct"), driver);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//*[@id='salesreceipteditproductdetailsgrid']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size(); i++) {
				// System.out.println(driver.findElement(By.xpath("//*[@id='salesreceipteditproductdetailsgrid']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td{"+i+"]/div")).getText());
				if (driver.findElement(By
						.xpath("//*[@id='salesreceipteditproductdetailsgrid']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Quantity")) {
					driver.findElement(By
							.xpath("//*[@id='salesreceipteditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					break;
				}
			}
			Thread.sleep(2000);
			Utilities.sendText("1");
			Thread.sleep(2000);

			driver.findElement(By.xpath(CashSale.getProperty("Memo"))).click();

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("buttonSave"))));
			driver.findElement(By.xpath(CashSale.getProperty("buttonSave"))).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("clickYes"))));
			driver.findElement(By.xpath(CashSale.getProperty("clickYes"))).click();
			Thread.sleep(1500);

			Utilities.clickButton("OK", 1000, driver);
			Thread.sleep(1500);

			System.out.println("* * * * Cash Sales Created for :" + documentID);

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("CloseCashSale"))));
			driver.findElement(By.xpath(CashSale.getProperty("CloseCashSale"))).click();
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}

	/*******************************/
	public static void create_fundTransferJE(String documentID, String productid, String vendorid, String BankName,
			WebDriver driver, String AccountSecond) throws InterruptedException, AWTException, IOException

	{
		try {

			Properties NormalJE = Utilities.fetchProValue("OR_FundTransferJE.properties");
			documentID = "FuTr" + documentID;

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(NormalJE.getProperty("ClcikOnJEIcon"))));
			driver.findElement(By.xpath(NormalJE.getProperty("ClcikOnJEIcon"))).click();
			Thread.sleep(3000);// pro

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(NormalJE.getProperty("SelectFundsJEOption"))));
			driver.findElement(By.xpath(NormalJE.getProperty("SelectFundsJEOption"))).click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(NormalJE.getProperty("ClickOnSubmit"))));
			driver.findElement(By.xpath(NormalJE.getProperty("ClickOnSubmit"))).click();
			Thread.sleep(1000);

			// sequen format document no.
			WebElement squence = driver.findElement(By.xpath(NormalJE.getProperty("SequenceFormat")));
			squence.clear();
			squence.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(NormalJE.getProperty("EnterJENo."))));
			driver.findElement(By.xpath(NormalJE.getProperty("EnterJENo."))).sendKeys(documentID);

			// click on add button

			Utilities.waitandSendkey(NormalJE.getProperty("SelectPaymentMethod"), driver, BankName);
			Utilities.Enter();

			driver.findElement(By.xpath(NormalJE.getProperty("memo"))).click();
			Thread.sleep(1000);// pro

			Utilities.waitandSendkey(NormalJE.getProperty("CheNOSeq"), driver, "NA");
			Utilities.Enter();
			Utilities.waitandSendkey(NormalJE.getProperty("CheNO"), driver, "CheNo" + documentID);

			// enter credit value
			for (int i = 1; i <= driver
					.findElements(By
							.xpath(".//*[@id='JournalEntry3']/div[1]/div[1]/div/div/div/div[3]/div/div[1]/div/div[1]/div/div[1]/div[1]/table/thead/tr/td"))
					.size(); i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='requisitioneditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				if (driver.findElement(By
						.xpath(".//*[@id='JournalEntry3']/div[1]/div[1]/div/div/div/div[3]/div/div[1]/div/div[1]/div/div[1]/div[1]/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Credit Amount")) {
					Thread.sleep(1000);
					driver.findElement(By
							.xpath("//div[@id='JournalEntry3']/div/div/div/div/div/div[3]/div/div/div/div/div[2]/div/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					Thread.sleep(2000);
					Utilities.sendText("100");
					Thread.sleep(2000);// pro
					break;
				}
			}

			driver.findElement(By.xpath(NormalJE.getProperty("memo"))).click();
			Thread.sleep(1000);// pro

			// **********************Next line
			// *[@id='JournalEntry3']/div[1]/div[1]/div/div/div/div[3]/div/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/table/tbody/tr/td[1]/div
			WebElement nxtChLinechkBox = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
							".//*[@id='JournalEntry3']/div[1]/div[1]/div/div/div/div[3]/div/div[1]/div[1]/div[1]/div[2]/div[1]/div"))));

			if (nxtChLinechkBox.isDisplayed()) {
				Thread.sleep(2000);
				for (int i = 1; i <= driver
						.findElements(By
								.xpath(".//*[@id='JournalEntry3']/div[1]/div[1]/div/div/div/div[3]/div/div[1]/div/div[1]/div/div[1]/div[1]/table/thead/tr/td"))
						.size(); i++) {
					// System.out.println(driver.findElement(By.xpath("//div[@id='requisitioneditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
					if (driver.findElement(By
							.xpath(".//*[@id='JournalEntry3']/div[1]/div[1]/div/div/div/div[3]/div/div[1]/div/div[1]/div/div[1]/div[1]/table/thead/tr/td["
									+ i + "]/div"))
							.getText().equalsIgnoreCase("Account")) {
						driver.findElement(By
								.xpath("//div[@id='JournalEntry3']/div/div/div/div/div/div[3]/div/div/div/div/div[2]/div/div[2]/table/tbody/tr/td["
										+ i + "]/div"))
								.click();
						Thread.sleep(2000);
						Robot acc1 = new Robot();
						Utilities.sendText(AccountSecond);
						Thread.sleep(2000);// pro
						acc1.keyPress(KeyEvent.VK_DOWN);
						Thread.sleep(2000);
						acc1.keyPress(KeyEvent.VK_DOWN);
						acc1.keyPress(KeyEvent.VK_ENTER);
						acc1.keyRelease(KeyEvent.VK_ENTER);

						break;
					}
				}
			}
			// **********************

			driver.findElement(By.xpath(NormalJE.getProperty("memo"))).click();

			Thread.sleep(1000);// pro

			for (int i = 1; i <= driver
					.findElements(By
							.xpath(".//*[@id='JournalEntry3']/div[1]/div[1]/div/div/div/div[3]/div/div[1]/div/div[1]/div/div[1]/div[1]/table/thead/tr/td"))
					.size(); i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='requisitioneditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				if (driver.findElement(By
						.xpath(".//*[@id='JournalEntry3']/div[1]/div[1]/div/div/div/div[3]/div/div[1]/div/div[1]/div/div[1]/div[1]/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Debit Amount")) {
					Thread.sleep(1000);
					driver.findElement(By
							.xpath("//div[@id='JournalEntry3']/div/div/div/div/div/div[3]/div/div/div/div/div[2]/div/div[2]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					Thread.sleep(2000);
					Utilities.sendText("100");
					Thread.sleep(2000);// pro
					break;
				}
			}

			driver.findElement(By.xpath(NormalJE.getProperty("memo"))).click();
			Thread.sleep(1000);// pro

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(NormalJE.getProperty("ClickOnSaveButtton"))));
			driver.findElement(By.xpath(NormalJE.getProperty("ClickOnSaveButtton"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 500, driver);
			Thread.sleep(1000);

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(NormalJE.getProperty("CloseForm"))));
			driver.findElement(By.xpath(NormalJE.getProperty("CloseForm"))).click();

			System.out.println("JE for Fund Transfer[" + documentID + "] created success !!!!");

		} catch (Exception EX) {
			System.out.println("JE for Fund Transfer[" + documentID + "] NOT created !!!!");
			Utilities.handleError(EX, driver);
		}
	}

	public static void create_ReceivePayment_AgainstGL(String documentID, String accountname, WebDriver driver,
			String BankName, String CheBankname, String DocumentCurrency, String ForexCurrecny1)
			throws InterruptedException, AWTException, IOException {

		try {
			documentID = "RPGL" + documentID;
			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstGL.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentIcon"))));
			driver.findElement(By.xpath(pro.getProperty("ReceivePaymentIcon"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentAgainstGLCheck"))));
			driver.findElement(By.xpath(pro.getProperty("ReceivePaymentAgainstGLCheck"))).click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
			driver.findElement(By.xpath(pro.getProperty("SubmitButton1"))).click();
			Thread.sleep(1000);

			// sequence format document no.
			WebElement squenceRP = driver.findElement(By.xpath(pro.getProperty("SequenceFormat")));
			squenceRP.clear();
			squenceRP.sendKeys("NA");
			Thread.sleep(1000);
			squenceRP.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentNo"))));
			driver.findElement(By.xpath(pro.getProperty("DocumentNo"))).sendKeys(documentID);

			Utilities.waitandSendkey(pro.getProperty("PaymentMethod"), driver, BankName);
			Utilities.Enter();
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			Utilities.waitandSendkey(pro.getProperty("DocumentCurrency"), driver, DocumentCurrency);
			Utilities.Enter();
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			if (DocumentCurrency.equals(ForexCurrecny1)) {
				Utilities.clickButton("Yes", 2000, driver);
			}

			Utilities.waitandSendkey(pro.getProperty("Chequeno"), driver, "CheNO" + documentID);

			// Below method can be used for bank name selection but due to
			// issue, used alernate method it can be used after ERP-43258 get
			// fixed
			// Method 1
			/*
			 * Utilities.waitandSendkey(pro.getProperty("BankName"), driver,
			 * CheBankname); Thread.sleep(1000); Utilities.Enter();
			 */

			// Method 2
			Utilities.waitandClick(pro.getProperty("BankName2"), driver);
			Robot R = new Robot();
			R.keyPress(KeyEvent.VK_DOWN);
			R.keyRelease(KeyEvent.VK_DOWN);
			R.keyPress(KeyEvent.VK_DOWN);
			Utilities.Enter();

			WebElement memo = driver.findElement(By.xpath(pro.getProperty("Memo")));
			memo.click();
			memo.sendKeys("Receive Payment Against GL");

			Thread.sleep(1000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Account")) {
					driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					Thread.sleep(2000);

				}
			}

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearchGLAccount"))));
			driver.findElement(By.xpath(pro.getProperty("QuickSearchGLAccount"))).sendKeys(accountname);

			Thread.sleep(4000);// pro

			// enter vendor
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("SelectGLAccount"))));
			WebElement selectedGL = driver.findElement(By.xpath(pro.getProperty("SelectGLAccount")));
			selectedGL.click();

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
			driver.findElement(By.xpath(pro.getProperty("SubmitButton1"))).click();
			Thread.sleep(2000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Enter Amount")) {
					driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					Thread.sleep(2000);

					Thread.sleep(2000);
					Robot r2 = new Robot();
					r2.keyPress(KeyEvent.VK_CONTROL);
					r2.keyPress(KeyEvent.VK_A);
					r2.keyRelease(KeyEvent.VK_A);
					r2.keyRelease(KeyEvent.VK_CONTROL);
					r2.keyPress(KeyEvent.VK_DELETE);
					r2.keyRelease(KeyEvent.VK_DELETE);
					Utilities.sendText("100");
					Thread.sleep(2000);
					r2.keyPress(KeyEvent.VK_ENTER);
					r2.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(1000);
					break;
				}
			}
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SaveButton"))));
			driver.findElement(By.xpath(pro.getProperty("SaveButton"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 1000, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(2000);

			// Email code
			// String subjectLine="Receive Payment Against GL - testsmoke -
			// "+documentID;
			// EmailFunctionality.email_AfterSave(documentID,subjectLine,driver);
			// Thread.sleep(1000);

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseMakePayment"))));
			driver.findElement(By.xpath(pro.getProperty("CloseMakePayment"))).click();
			System.out
					.println("Receive Payment against GL completed successfully for doc id : [" + documentID + "]***");

		} catch (Exception EX) {
			System.out.println("RP against GL NOT CREATED !!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	/************************* RP For Vendor *************************/

	public static void create_ReceivePayment_Vendor(String documentID, String productid, String Refudamount,
			String vendor_ID, WebDriver driver, String BankName, String DocumentCurrency, String ForexCurrecny1)
			throws InterruptedException, AWTException, IOException {

		try {

			Properties pro = Utilities.fetchProValue("OR_ReceivePaymentAgainstVendor.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentIcon"))));
			driver.findElement(By.xpath(pro.getProperty("ReceivePaymentIcon"))).click();
			Thread.sleep(3000);

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(pro.getProperty("ReceivePaymentAgainstVendorCheck"))));
			driver.findElement(By.xpath(pro.getProperty("ReceivePaymentAgainstVendorCheck"))).click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
			driver.findElement(By.xpath(pro.getProperty("SubmitButton1"))).click();
			Thread.sleep(1000);

			// sequence format document no.
			WebElement squenceRP = driver.findElement(By.xpath(pro.getProperty("SequenceFormat")));
			squenceRP.clear();
			squenceRP.sendKeys("NA");
			Thread.sleep(1000);
			squenceRP.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentNo"))));
			driver.findElement(By.xpath(pro.getProperty("DocumentNo"))).sendKeys("RPVend" + documentID);
			Thread.sleep(1000);

			WebElement vendor = driver.findElement(By.xpath(pro.getProperty("vednorID")));
			vendor.clear();
			vendor.sendKeys(vendor_ID);
			Thread.sleep(2000);
			vendor.sendKeys(Keys.ENTER);
			Thread.sleep(1000);

			Utilities.waitandSendkey(pro.getProperty("PaymentMethod"), driver, BankName);
			Utilities.Enter();
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			Utilities.waitandSendkey(pro.getProperty("DocumentCurrency"), driver, DocumentCurrency);
			Utilities.Enter();
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			if (DocumentCurrency.equals(ForexCurrecny1)) {
				Utilities.clickButton("Yes", 2000, driver);
			}

			if (!BankName.equals("Cash")) {

				Utilities.waitandSendkey(pro.getProperty("Chequeno"), driver, "CheNO" + documentID);

				// Below method can be used for bank name selection but due to
				// issue, used alernate method it can be used after ERP-43258
				// get fixed
				// Method 1
				/*
				 * Utilities.waitandSendkey(pro.getProperty("BankName"), driver,
				 * CheBankname); Thread.sleep(1000); Utilities.Enter();
				 */

				// Method 2
				Utilities.waitandClick(pro.getProperty("BankName2"), driver);
				Robot R = new Robot();
				R.keyPress(KeyEvent.VK_DOWN);
				R.keyRelease(KeyEvent.VK_DOWN);
				R.keyPress(KeyEvent.VK_DOWN);
				Utilities.Enter();

			}

			WebElement memo = driver.findElement(By.xpath(pro.getProperty("Memo")));
			memo.click();
			memo.sendKeys("Receive Payment Against Vendor Advance");

			Thread.sleep(1000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Document Type")) {
					driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					Thread.sleep(2000);
					Utilities.sendText("Refund / Deposit");
					Thread.sleep(2000);
					Robot r1 = new Robot();
					r1.keyPress(KeyEvent.VK_ENTER);
					r1.keyRelease(KeyEvent.VK_ENTER);
					memo.click();
					Thread.sleep(2000);
				}
			}

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Enter Amount")) {
					driver.findElement(By
							.xpath("//div[@id='receiptwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					Thread.sleep(2000);
					Thread.sleep(2000);// pro
					Robot r2 = new Robot();
					r2.keyPress(KeyEvent.VK_CONTROL);
					r2.keyPress(KeyEvent.VK_A);
					r2.keyRelease(KeyEvent.VK_A);
					r2.keyRelease(KeyEvent.VK_CONTROL);
					r2.keyPress(KeyEvent.VK_DELETE);
					r2.keyRelease(KeyEvent.VK_DELETE);
					Utilities.sendText(Refudamount);
					Thread.sleep(2000);// pro
					r2.keyPress(KeyEvent.VK_ENTER);
					r2.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(1000);// pro
					driver.findElement(By.xpath(pro.getProperty("Memo"))).click();
					Thread.sleep(1000);// pro
					break;
				}
			}

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SaveButton"))));
			driver.findElement(By.xpath(pro.getProperty("SaveButton"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 1000, driver);
			Thread.sleep(1000);

			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(2000);

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseMakePayment"))));
			driver.findElement(By.xpath(pro.getProperty("CloseMakePayment"))).click();

			System.out.println("Receive Payment against Vendor successfully for doc id : [" + documentID + "]");
		} catch (Exception EX) {
			System.out.println("RP aginst VENDOR failed !!!!!!!!!!" + documentID);
			Utilities.handleError(EX, driver);
		}
	}

	/***************************** MP_for_Customer *************************/

	public static void MakePayment_Customer(String documentID, String customer, String RefunDepositAmt,
			String CreditNoteAmt, WebDriver driver, String BankName, String DocumentCurrency, String ForexCurrecny1)
			throws InterruptedException, AWTException, IOException {

		try {

			// documentID ="MPCust" + documentID;
			WebDriverWait wait = new WebDriverWait(driver, 45);
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstCustomer.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("MakePaymentIcon"))));
			driver.findElement(By.xpath(pro.getProperty("MakePaymentIcon"))).click();
			Thread.sleep(3000);// pro

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("MakePaymentAgainstCustomer"))));
			driver.findElement(By.xpath(pro.getProperty("MakePaymentAgainstCustomer"))).click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SubmitButton1"))));
			driver.findElement(By.xpath(pro.getProperty("SubmitButton1"))).click();
			Thread.sleep(1000);

			Utilities.enterTextInDropDown("NA", pro.getProperty("SequenceFormat"), driver);

			Utilities.enterTextInDropDown("MPCust" + documentID, pro.getProperty("DocumentNo"), driver);

			WebElement vendlocaotr = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("vendorID"))));
			vendlocaotr.clear();
			Thread.sleep(500);
			vendlocaotr.sendKeys(customer);
			Thread.sleep(2000);
			vendlocaotr.sendKeys(Keys.ENTER);
			Thread.sleep(1000);

			Utilities.waitandSendkey(pro.getProperty("PaymentMethod"), driver, BankName);
			Utilities.Enter();
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			Utilities.waitandSendkey(pro.getProperty("DocumentCurrency"), driver, DocumentCurrency);
			Utilities.Enter();
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			if (DocumentCurrency.equals(ForexCurrecny1)) {
				Utilities.clickButton("Yes", 2000, driver);
			}
			Utilities.waitandSendkey(pro.getProperty("ChequeSequence"), driver, "NA");
			Utilities.Tab();
			Utilities.waitandSendkey(pro.getProperty("ChequeNo"), driver, "CNo" + documentID);
			Utilities.Tab();

			List<WebElement> header = driver.findElements(By.xpath(
					"//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"));
			int totalHeadCnt = header.size();

			int indexOfDocType = 0, indexOfDocNo = 0, indexOfAmount = 0;
			Robot r = new Robot();
			for (int i = 1; i <= totalHeadCnt; i++) {
				String headName = driver.findElement(By
						.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (headName.equalsIgnoreCase("Document Type")) {
					indexOfDocType = i;
				} else if (headName.equalsIgnoreCase("Document Number")) {
					indexOfDocNo = i;
				} else if (headName.equalsIgnoreCase("Enter Amount")) {
					indexOfAmount = i;
				}
			}

			// add code for Refund / Deposit
			WebElement docTyp = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
							+ indexOfDocType + "]/div")));
			docTyp.click();
			Thread.sleep(2000);
			Utilities.sendText("Refund / Deposit");
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			Thread.sleep(1500);

			WebElement ammount = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//div[@id='paymentwindow']/div/div[1]/div/div/div/div[3]/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
							+ indexOfAmount + "]/div/div")));
			ammount.click();
			Thread.sleep(1500);
			Utilities.sendText(RefunDepositAmt);
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			Thread.sleep(1500);

			Utilities.clickButton("Save", 500, driver);
			Thread.sleep(1000);
			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);

			// Utilities.clickButton("Yes", 2000, driver);

			Utilities.clickButton("OK", 500, driver);

			// Email code
			// String subjectLine="Make Payment Against Customer - testsmoke -
			// "+documentID;
			// EmailFunctionality.email_AfterSave(documentID,subjectLine,driver);

			WebElement closeTab = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='as__paymentwindow']/a[1]")));
			Thread.sleep(1000);
			closeTab.click();

			System.out.println("MP against Customer is Pass for doc : -> " + documentID);

		}

		catch (Exception EX) {
			System.out.println("MakePayment_Customer [" + documentID + "] NOT CREATED  !!!!");
			Utilities.handleError(EX, driver);
		}

	}

	/************************ MP_FOR_GL ***********************/

	public static void create_makePayment_GL(String documentID, String productid, String accType, WebDriver driver,
			String BankName, String DocumentCurrency, String ForexCurrecny1)
			throws InterruptedException, AWTException, IOException {
		try {

			documentID = "MPaGL" + documentID;
			Properties pro = Utilities.fetchProValue("OR_MakePaymentAgainstGL.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("MakePaymentIcon"))));
			driver.findElement(By.xpath(pro.getProperty("MakePaymentIcon"))).click();
			Thread.sleep(5000);// pro

			new WebDriverWait(driver, 40)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AgainstGL"))));
			driver.findElement(By.xpath(pro.getProperty("AgainstGL"))).click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Submit']")));
			driver.findElement(By.xpath("//button[text()='Submit']")).click();
			Thread.sleep(1000);

			// sequen format document no.
			WebElement squenceRP = driver.findElement(By.xpath(pro.getProperty("SeqFormat")));
			squenceRP.clear();
			squenceRP.sendKeys("NA");
			Thread.sleep(1000);// pro
			squenceRP.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("paymentNumber"))));
			driver.findElement(By.xpath(pro.getProperty("paymentNumber"))).sendKeys(documentID);

			Utilities.waitandSendkey(pro.getProperty("PaymentMethod"), driver, BankName);
			Utilities.Enter();
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			Utilities.waitandSendkey(pro.getProperty("PaymentCurrency"), driver, DocumentCurrency);
			Utilities.Enter();
			Utilities.waitandClick(pro.getProperty("Memo"), driver);

			if (DocumentCurrency.equals(ForexCurrecny1)) {
				Utilities.clickButton("Yes", 2000, driver);
			}

			Utilities.waitandSendkey(pro.getProperty("ChequeSequence"), driver, "NA");
			Utilities.Enter();
			Utilities.waitandSendkey(pro.getProperty("ChequeNo"), driver, "CNo" + documentID);
			Utilities.Tab();

			Robot r = new Robot();
			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Account")) {
					driver.findElement(By
							.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div[2]/div/div/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					Thread.sleep(2000);
					new WebDriverWait(driver, 30).until(
							ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("accQuicksearch"))));
					driver.findElement(By.xpath(pro.getProperty("accQuicksearch"))).sendKeys(accType);
					Thread.sleep(5000);

					WebElement selectAc = new WebDriverWait(driver, 30)
							.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("accSelect"))));
					if (selectAc.isEnabled()) {
						selectAc.click();
					}

					new WebDriverWait(driver, 30)
							.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Submitbtn"))));
					driver.findElement(By.xpath(pro.getProperty("Submitbtn"))).click();
				}
			}

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equals("Enter Amount")) {
					driver.findElement(By
							.xpath("//div[@id='paymentwindowwrapperPanelNorth']/following-sibling::div[2]/div/div/div/div/div[2]/div/div/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					Thread.sleep(2000);
					Utilities.sendText("100");
				}
			}

			Thread.sleep(1000);
			WebElement savebtn = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("savebtn"))));
			if (savebtn.isEnabled()) {
				savebtn.click();
			}

			Utilities.clickButton("Yes", 1500, driver);

			Utilities.clickButton("OK", 1500, driver);

			// Email code
			// String subjectLine="Make Payment against GL "+documentID;
			// EmailFunctionality.email_AfterSave(documentID,subjectLine,driver);
			// Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseTabBtn")))).click();

			System.out.println("Make Payment against GL [" + documentID + "] created Successfully");

		} catch (Exception EX) {
			System.out.println("Make Payment against GL [" + documentID + "] is FAILED !!!!!!");
			Utilities.handleError(EX, driver);
		}
	}

	public static void create_ChartOfAccount(String documentID, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{
		String documentID1 = "COA" + documentID;
		try {

			Properties pro = Utilities.fetchProValue("OR_ChartOfAccount.properties");
			// clicked on Sales Return Document
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("iconCAO"))));
			driver.findElement(By.xpath(pro.getProperty("iconCAO"))).click();
			Thread.sleep(1000);// pro

			// Click On Submit Button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("addNewAcc"))));
			driver.findElement(By.xpath(pro.getProperty("addNewAcc"))).click();

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("accCode"))));
			driver.findElement(By.xpath(pro.getProperty("accCode"))).sendKeys(documentID1);
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("accName"))));
			driver.findElement(By.xpath(pro.getProperty("accName"))).sendKeys(documentID1);
			Thread.sleep(2000);

			Utilities.waitandClick(pro.getProperty("MasterType"), driver);

			WebElement grpid = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("groupID"))));
			grpid.clear();
			grpid.sendKeys("Bank");
			Thread.sleep(3000);// pro
			grpid.sendKeys(Keys.ENTER);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("saveBtn"))));
			driver.findElement(By.xpath(pro.getProperty("saveBtn"))).click();

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("okBtn"))));
			driver.findElement(By.xpath(pro.getProperty("okBtn"))).click();

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("coaClsBtn"))));
			driver.findElement(By.xpath(pro.getProperty("coaClsBtn"))).click();
			System.out.println("Chart of account " + documentID1 + " successfully created !! ");

		} catch (Exception EX) {
			System.out.println("Chart of account " + documentID1 + " NOT created !! ");
			Utilities.handleError(EX, driver);
		}
	}

}
