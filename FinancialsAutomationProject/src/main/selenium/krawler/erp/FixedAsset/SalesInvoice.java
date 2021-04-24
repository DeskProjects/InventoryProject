package krawler.erp.FixedAsset;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SalesInvoice {

	public static WebDriver driver;

	public void loginERP() throws InterruptedException, IOException, AWTException {

		String dir = System.getProperty("user.dir");
		String directorypath = dir + "\\src\\Propertiesfile\\OR_TestDemo.properties";
		File file = new File(directorypath);
		FileInputStream fis = new FileInputStream(file);
		Properties pro = new Properties();
		pro.load(fis);

		System.setProperty("webdriver.chrome.driver", pro.getProperty("Driverpath"));
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(pro.getProperty("URL")); // Get browser URL

		// Get User name

		WebElement element = driver.findElement(By.xpath("//Input[@id ='UserName']"));
		element.click();
		element.sendKeys("admin");
		Thread.sleep(3000);

		element = driver.findElement(By.xpath("//Input[@id='Password']")); // Get
																			// Password
		element.click();
		element.sendKeys("1234");
		Thread.sleep(3000);

		element = driver.findElement(By.xpath("//Input[@id ='LoginButton']")); // Get
																				// login
																				// button
		element.click();
		Thread.sleep(3000);

		element = driver.findElement(By.xpath("//area[@onclick='callInvoice(false,null)']")); // Find
																								// Invoice
																								// Icon
		element.click();
		Thread.sleep(3000);

		element = driver.findElement(By.xpath("//*[@id='customer2Invoice']")); // Get
																				// Customer
																				// Name
		element.clear();
		element.sendKeys("Amol1");
		Thread.sleep(3000);// pro
		element.sendKeys(Keys.ENTER);

		/*
		 * Thread.sleep(3000);//pro element=driver.findElement(By.xpath(
		 * "//input[@name='prdiscount']//following::input[1]")); //Get Link
		 * element.clear(); Thread.sleep(3000);//pro element.sendKeys("Yes");
		 * Thread.sleep(3000);//pro element.sendKeys(Keys.ENTER);
		 * 
		 * 
		 * Thread.sleep(3000);//pro
		 * element=driver.findElement(By.xpath("//*[@name='fromLinkCombo']"));
		 * //Get Link To (Module Name) element.clear(); Thread.sleep(3000);//pro
		 * element.sendKeys("Sales Order"); Thread.sleep(3000);//pro
		 * element.sendKeys(Keys.ENTER);
		 * 
		 * 
		 * Thread.sleep(3000);//pro element=driver.findElement(By.xpath(
		 * "//input[@id='poNumberID2Invoice']//following::span[1]/img[2]"));
		 * element.click(); Thread.sleep(2000);//pro
		 * 
		 * 
		 * element=driver.findElement(By.xpath("//*[@id='poNumberID2Invoice']"))
		 * ; //Get Link To (Module Number) element.sendKeys("SON100");
		 * Thread.sleep(3000);//pro
		 * 
		 * element=driver.findElement(By.
		 * xpath("//div[@class='x-layer x-combo-list ' and contains(@style, 'visible')]/div/div/table/tbody/tr/td"
		 * )); // Sales Order number path element.click(); Thread.sleep(2000);
		 * 
		 * element=driver.findElement(By.xpath("//*[@id='memo2Invoice']")); //
		 * Get Memo element.click(); Thread.sleep(1500);
		 */// pro
		// element.sendKeys("MEMO");

		/*
		 * Thread.sleep(3000);//pro element=driver.findElement(By.xpath(
		 * ".//*[@id='sequenceFormatCombobox2Invoice']")); //Get Sequence Number
		 * element.clear(); Thread.sleep(3000);//pro element.sendKeys("NA");
		 * element.click(); Thread.sleep(3000);//pro
		 * element.sendKeys(Keys.ENTER);
		 */

		Thread.sleep(3000);// pro
		element = driver.findElement(By.xpath("//*[@id='porefno2Invoice']")); // Customer
																				// PO
																				// Reference
																				// Number
		element.sendKeys("1234");

		/*
		 * Thread.sleep(3000);//pro
		 * element=driver.findElement(By.xpath("//*[@id='shipdate2Invoice']"));
		 * //Delivery Date WebElement InvoiceDate_Verify =
		 * driver.findElement(By.xpath("//*[@id='invoiceDate2Invoice']")); //Get
		 * Invoice Date Path and Store String InvoiceDate =
		 * InvoiceDate_Verify.getAttribute("value");
		 * element.sendKeys(InvoiceDate);
		 * 
		 * Thread.sleep(3000);//pro element=driver.findElement(By.xpath(
		 * "//input[@name='partialinv']//following::input[1]")); //Is Partial
		 * Invoice Field element.clear(); Thread.sleep(3000);//pro
		 * element.sendKeys("Yes"); Thread.sleep(3000);//pro
		 * element.sendKeys(Keys.ENTER);
		 * 
		 * Thread.sleep(3000);//pro element=driver.findElement(By.xpath(
		 * "//input[@id='partialinvamt2Invoice']")); // Enter Partial Amount (%)
		 * element.sendKeys("10");
		 */

		// Thread.sleep(3000);//pro
		// element=driver.findElement(By.xpath("//input[@id='invoiceNo2Invoice']"));
		// element.sendKeys("12345");

		/*
		 * Thread.sleep(3000);//pro element=driver.findElement(By.xpath(
		 * "//input[@id='autogenerateDO2Invoice']")); // Auto Generate Delivery
		 * Order Thread.sleep(3000);//pro element.click();
		 */

		/*
		 * Thread.sleep(3000);//pro element=driver.findElement(By.xpath(
		 * "//input[@id='sequenceformatDo2Invoice']")); element.clear();
		 * Thread.sleep(3000);//pro element.sendKeys("NA");
		 * 
		 * Thread.sleep(3000);//pro element.sendKeys(Keys.ENTER);
		 * 
		 * Thread.sleep(3000);//pro element=driver.findElement(By.xpath(
		 * "//input[@id='deliveryONo2Invoice']")); // Delivery Order number
		 * element.sendKeys(DONumber);
		 */

		/*
		 * Thread.sleep(3000);//pro element=driver.findElement(By.xpath(
		 * "//input[@id='includeprotax2Invoice']")); // Include Product Tax
		 * element.clear(); element.sendKeys("No"); Thread.sleep(3000);
		 * element.sendKeys(Keys.ENTER);
		 */

		Thread.sleep(3000);
		element = driver.findElement(By.xpath(
				"//div[@id='Invoiceeditproductdetailsgrid']//div[@class='x-grid3-scroller']/div/div/table/tbody/tr/td[5]/div/div")); // Click
																																		// on
																																		// Add
																																		// product
																																		// button
		element.click();

		Thread.sleep(3000);
		element = driver.findElement(By.xpath(
				"//div[@id='InvoiceeditproductdetailsgridProductSelectionWindow']/div[2]/div/div/div/div/div/div/div[2]/div/div/div[2]/div/div/div[2]/div[1]/table/tbody/tr/td[1]"));
		element.click(); // Select first product

		Thread.sleep(3000);
		element = driver.findElement(By.xpath(
				"//div[@id='InvoiceeditproductdetailsgridProductSelectionWindow']/div[2]/div/div/div/div/div/div/div[2]/div/div/div[2]/div/div/div[2]/div[2]/table/tbody/tr/td[1]"));
		element.click(); //// Select Second product

		Thread.sleep(3000);// pro
		element = driver.findElement(By.xpath("//button[text()='Add']")); // Click
																			// on
																			// Add
																			// button
		element.click();

		Thread.sleep(3000);// pro
		element = driver.findElement(By.xpath(
				"//*[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td[16]/div")); // Enter
																																// Quantity
																																// in
																																// first
																																// product
		element.click();
		Thread.sleep(1500);// pro
		sendText("1");

		Thread.sleep(3000);// pro
		element = driver.findElement(By.xpath(
				"//*[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[2]/table/tbody/tr/td[16]/div")); // Enter
																																// Quantity
																																// in
																																// Second
																																// product
		element.click();
		Thread.sleep(1500);// pro
		sendText("2");

		/*
		 * Thread.sleep(3000);//pro element=driver.findElement(By.
		 * xpath("//div[@class='x-grid3-row   x-grid3-dirty-row ']/table/tbody/tr/td[32]/div"
		 * )); // Line Level Tax element.click();
		 * element.sendKeys("GST(DS)@7.00%"); Thread.sleep(3000);//pro
		 * element.sendKeys(Keys.ENTER);
		 * 
		 * 
		 * Thread.sleep(3000);//pro element=driver.findElement(By.xpath(
		 * "//input[@id='includetax2Invoice']")); // Global Level Include Total
		 * Tax element.clear(); element.sendKeys("No"); Thread.sleep(3000);//pro
		 * element.sendKeys(Keys.ENTER);
		 */

		/*
		 * Thread.sleep(3000);//pro
		 * element=driver.findElement(By.xpath("//input[@id='tax2Invoice']"));
		 * // Global Level Tax element.clear();
		 * element.sendKeys("GST(DS)@7.00%"); Thread.sleep(3000);//pro
		 * element.sendKeys(Keys.ENTER);
		 * 
		 * Thread.sleep(3000);//pro
		 * element=driver.findElement(By.xpath("//*[@id='memo2Invoice']")); //
		 * Get Memo element.click(); Thread.sleep(1500);//pro
		 * element.sendKeys("MEMO");
		 */

		String text = "10";
		String xpathOfelement = "//div[@id='invoicetermgridpanelInvoice']/div/div/div[2]/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td[2]/div";
		Actions action = new Actions(driver);
		WebElement hoverElementandClick = new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfelement)));
		Thread.sleep(500);
		action.moveToElement(hoverElementandClick).build().perform();
		Thread.sleep(500);
		hoverElementandClick.click();

		StringSelection stringSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);

		Thread.sleep(1000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyPress(KeyEvent.VK_DELETE);
		robot.keyRelease(KeyEvent.VK_DELETE);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);

		element = driver.findElement(By.xpath("//*[@id='memo2Invoice']")); // Get
																			// Memo
		element.click();

		/*
		 * Thread.sleep(3000);//pro
		 * element=driver.findElement(By.xpath("//*[@id='showaddressInvoice']"))
		 * ; // Get Show Address //element=driver.findElement(By.
		 * xpath("//button[text()='Show Address']")); element.click();
		 * 
		 * Thread.sleep(3000);//pro
		 * element=driver.findElement(By.xpath("//*[@name='billingAddress']"));
		 * // Get Billling Address //element=driver.findElement(By.
		 * xpath("//button[text()='Show Address']"));
		 * element.sendKeys("Deskera");
		 * 
		 * Thread.sleep(3000);//pro
		 * element=driver.findElement(By.xpath("//*[@name='billingCity']")); //
		 * Get Billling City Address //element=driver.findElement(By.
		 * xpath("//button[text()='Show Address']")); element.sendKeys("Pune");
		 * 
		 * Thread.sleep(3000);//pro
		 * element=driver.findElement(By.xpath("//*[@name='billingState']")); //
		 * Get Billling State Address //element=driver.findElement(By.
		 * xpath("//button[text()='Show Address']"));
		 * element.sendKeys("Maharashtra");
		 * 
		 * Thread.sleep(3000);//pro
		 * element=driver.findElement(By.xpath("//*[@name='copyadress']")); //
		 * Click on Same as Billing Address button element.click();
		 * 
		 * Thread.sleep(3000);//pro element=driver.findElement(By.xpath(
		 * "//div[@id='addressDetailWindow']/div[2]/div[2]/div/div/div/div/div/table/tbody/tr/td[1]/table/tbody/tr/td[2]/em/button"
		 * )); // Click on Show Address Save button element.click();
		 * 
		 * Thread.sleep(3000);//pro
		 * element=driver.findElement(By.xpath("//button[text()='OK']")); //
		 * Click on Ok button element.click();
		 */

		Thread.sleep(3000);// pro
		element = driver.findElement(By.xpath("//button[text()='Save']")); // Click
																			// on
																			// Document
																			// Save
																			// button
		element.click();

		/*
		 * Thread.sleep(3000);//pro element=driver.findElement(By.
		 * xpath("//button[text()='Save as Draft']")); // Click on Save as Draft
		 * button element.click();
		 * 
		 * Thread.sleep(3000);//pro element=driver.findElement(By.
		 * xpath("//button[text()='Save and Create New']")); // Click on Save
		 * and Create New button element.click();
		 */

		Thread.sleep(3000);// pro
		element = driver.findElement(By.xpath("//button[text()='Yes']")); // Click
																			// on
																			// Credit
																			// limit
																			// Yes
																			// button
		element.click();

		Thread.sleep(3000);// pro
		element = driver.findElement(By.xpath("//button[text()='Yes']")); // Click
																			// on
																			// Yes
																			// button
		element.click();

		Thread.sleep(100);
		WebElement InvoiceNo = driver.findElement(By.xpath("//div[@class='x-window-mc']/div/div/div/span/b[2]")); // Get
																													// Invoice
																													// number
																													// path
																													// and
																													// store
		String Invoice = InvoiceNo.getText();
		System.out.println(Invoice);

		String withoutComma = Invoice.replaceFirst(",", "");
		System.out.println(withoutComma);

		Thread.sleep(3000);// pro
		element = driver.findElement(By.xpath("//button[text()='OK']")); // Click
																			// on
																			// Ok
																			// button
		element.click();

		Thread.sleep(3000);// pro
		element = driver.findElement(By.xpath("//li[@id='as__Invoice']/a[1]")); // Close
																				// Invoice
																				// form
																				// tab
		element.click();

		Thread.sleep(3000);// pro
		element = driver.findElement(By.xpath("//map[@name='AlienAreas3']/area[10]")); // Click
																						// on
																						// Invoice
																						// report
		element.click();

		Thread.sleep(3000);// pro
		element = driver.findElement(By.xpath("//input[@id='quickSearch16InvoiceListEntry']")); // Get
																								// Quick
																								// search
																								// field
		element.sendKeys(withoutComma);

		Thread.sleep(2000);
		int e = driver
				.findElements(By
						.xpath(".//*[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
				.size();
		System.out.println(e);
		WebElement InvoiceNo_Verify = driver
				.findElement(By.xpath(".//table[@class='x-grid3-row-table']/tbody/tr/td[7]/div/a']")); // Get
																										// Invoice
																										// number
																										// path
																										// and
																										// store
		String Invoice1 = InvoiceNo_Verify.getText();
		System.out.println(Invoice1);

		Thread.sleep(3000);// pro
		element = driver.findElement(By.xpath(".//table[@class='x-grid3-row-table']/tbody/tr/td[7]/div/a']"));
		element.getText();

	}

	public static void sendText(String text) throws InterruptedException, AWTException {
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);

		Thread.sleep(1000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public static void main(String[] args) throws InterruptedException, AWTException, IOException {
		// TODO Auto-generated method stub

		SalesInvoice object = new SalesInvoice();
		object.loginERP();

	}
}
