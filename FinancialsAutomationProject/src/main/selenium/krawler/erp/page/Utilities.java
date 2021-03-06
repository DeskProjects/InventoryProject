package krawler.erp.page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.FindsByXPath;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

import com.google.common.base.Function;

public class Utilities {
	public static Properties fetchProValue(String repositoryName) throws IOException {
		String dir = System.getProperty("user.dir");
		String directorypath = dir + "\\src\\main\\selenium\\krawler\\erp\\objectRepository\\" + repositoryName;
		File file = new File(directorypath);
		FileInputStream fis = new FileInputStream(file);
		Properties pro = new Properties();
		pro.load(fis);
		return pro;
	}
	// -----------------------------------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------------------------------------------
	// Basic Methods
	public static void handleError(Exception EX, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		System.out.println("TestCase Fail Due to following Reasons:");
		EX.printStackTrace();
		Utilities.screenShot(driver);
		Thread.sleep(1000);
		driver.navigate().refresh();
		Assert.assertTrue(false);
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

	public static String currentDateTime() {
		Date currentDate = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd HH-mm-ss");
		String currentDateString = df.format(currentDate);
		return currentDateString;
	}

	public static String currentDate(String dateFormat) {
		Date currentDate = new Date();
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		String currentDateString = df.format(currentDate);
		return currentDateString;
	}

	public static void screenShot(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy
		// somewhere
		String Name = currentDateTime();
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\FailedScreenShot\\" + Name + ".png"));
		System.out.println("Check fail test screenshot inside - FailedScreenShot folder");
	}

	public static void report_ScreenShot(String Name, WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String date = currentDate("dd-MM-yyyy");
		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\ReportsScreenshot\\" + Name + "-" + date + ".png"));
		System.out.println("Check screenshot here inside - ReportsScreenshot folder");
	}

	public static void zoomIn() throws AWTException {
		Robot r1 = new Robot();
		r1.keyPress(KeyEvent.VK_CONTROL);
		r1.keyPress(KeyEvent.VK_0);
		r1.keyRelease(KeyEvent.VK_0);
		r1.keyRelease(KeyEvent.VK_CONTROL);

	}

	public static void zoomOut() throws AWTException, InterruptedException {

		Robot r = new Robot();
		Thread.sleep(500);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_SUBTRACT);
		r.keyRelease(KeyEvent.VK_SUBTRACT);
		r.keyPress(KeyEvent.VK_SUBTRACT);
		r.keyRelease(KeyEvent.VK_SUBTRACT);
		r.keyPress(KeyEvent.VK_SUBTRACT);
		r.keyRelease(KeyEvent.VK_SUBTRACT);
		r.keyPress(KeyEvent.VK_SUBTRACT);
		r.keyRelease(KeyEvent.VK_SUBTRACT);
		r.keyPress(KeyEvent.VK_SUBTRACT);
		r.keyRelease(KeyEvent.VK_SUBTRACT);
		r.keyPress(KeyEvent.VK_SUBTRACT);
		r.keyRelease(KeyEvent.VK_SUBTRACT);
		r.keyPress(KeyEvent.VK_SUBTRACT);
		r.keyRelease(KeyEvent.VK_SUBTRACT);
		r.keyRelease(KeyEvent.VK_CONTROL);
	}

	public static void refresh() throws AWTException, InterruptedException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_F5);
		r.keyRelease(KeyEvent.VK_F5);
		Thread.sleep(8000);
	}

	public static void writeSerialsToFile(String fileName, String[] serials) throws Exception {
		FileWriter fw = null;
		try {
			String dir = System.getProperty("user.dir");
			String directorypath = dir + "/test-output/" + fileName;
			File serialFile = new File(directorypath);
			// check if file exist, otherwise create the file before writing
			if (!serialFile.exists()) {
				serialFile.createNewFile();
			}
			fw = new FileWriter(directorypath);
			for (int i = 0; i < serials.length; i++) {
				fw.write(serials[i] + "\n");
			}

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			fw.close();
		}
	}

	public static String[] readSerialsFromFile(String fileName) throws Exception {
		FileReader fr = null;
		String dir = System.getProperty("user.dir");
		String directorypath = dir + "/test-output/" + fileName;
		List<String> lines = new ArrayList<String>();
		BufferedReader bufferedReader = null;
		List<String> listWithoutDuplicates = null;
		try {
			File serialFile = new File(directorypath);
			if (serialFile != null && serialFile.exists()) {
				fr = new FileReader(directorypath);
				bufferedReader = new BufferedReader(fr);

				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					lines.add(line);
				}
				HashSet<String> listToSet = new HashSet<String>(lines);
				listWithoutDuplicates = new ArrayList<String>(listToSet);
			}

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (fr != null) {
				fr.close();
			}

		}
		if (listWithoutDuplicates != null) {
			return listWithoutDuplicates.toArray(new String[listWithoutDuplicates.size()]);
		} else {
			return null;
		}

	}

	public static boolean isNullOrEmpty(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		return false;
	}

	public static void clickExpander(WebDriver driver) throws InterruptedException {
		WebElement expIcon = new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='navigationpanel']/div[1]/div")));
		if (expIcon.isDisplayed()) {
			Thread.sleep(500);
			expIcon.click();
		}
	}

	/*
	 * //alwaysCollapse public static void disableExpander1(WebDriver driver)
	 * throws InterruptedException{ try{ WebElement expIcon= new
	 * WebDriverWait(driver,
	 * 5).until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * ".//*[@id='navigationpanel']/following-sibling::div[2]/div[1]")));
	 * if(expIcon.isEnabled()){ Thread.sleep(500); } } catch(Exception Ex){
	 * WebElement expIcon= new WebDriverWait(driver,
	 * 5).until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * ".//*[@id='navigationpanel']/div[1]/div"))); if(expIcon.isEnabled()){
	 * Thread.sleep(500); expIcon.click(); } } }
	 */
	// alwaysCollapse
	public static void disableExpander(WebDriver driver) throws InterruptedException, AWTException, IOException {

		boolean success = false;
		for (int num_try = 1; !success && num_try <= 30; num_try++) {
			WebElement element = null;
			try {
				element = driver.findElement(By.xpath(
						".//*[@id='navigationpanel' and contains(@class,'x-panel-collapsed')]/following-sibling::div[2]/div[1]"));
				Thread.sleep(500);
				success = true;
			} catch (Exception EX) {
				Utilities.click_element(".//*[@id='navigationpanel']/div[1]/div[1]", driver);
				if (num_try == 30) {
					// Utilities.handleError(EX, driver);
				}
			}
		}

	}

	// alwaysExpand
	public static void enableExpander(WebDriver driver) throws InterruptedException {
		try {
			WebElement expIcon = new WebDriverWait(driver, 2)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='navigationpanel']/div[1]/div")));
			if (expIcon.isEnabled()) {
				Thread.sleep(1000);
			}
		} catch (Exception Ex) {
			WebElement expIcon = new WebDriverWait(driver, 2).until(ExpectedConditions
					.elementToBeClickable(By.xpath(".//*[@id='navigationpanel']/following-sibling::div[2]/div[1]")));
			if (expIcon.isEnabled()) {
				Thread.sleep(1500);
				expIcon.click();
			}
		}
	}

	// **************** Panel Control *****************************
	public static void sidePanel(boolean panelStatus, WebDriver driver) {

		try {
			if (panelStatus == false) {
				WebElement expIcon = new WebDriverWait(driver, 30).until(
						ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='navigationpanel']/div[1]/div")));
				if (expIcon.isDisplayed()) {
					Thread.sleep(1000);
					expIcon.click();
				}
			}

			else if (panelStatus == true) {
				WebElement expIcon = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(
						By.xpath(".//*[@id='navigationpanel']/following-sibling::div[2]/div[1]")));
				if (expIcon.isDisplayed()) {
					Thread.sleep(1000);
					expIcon.click();
				}
			}
		} catch (Exception selected) {
			System.out.println("Expander already closed/Opened !! ");
		}
	}
	// * * * * * * * * * * * * * * * * * *

	// Handle any Clickable button
	public static void clickButton(String buttonName, int sleepFor, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			WebElement button = new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='" + buttonName + "']")));
			if (button.isDisplayed()) {
				Thread.sleep(sleepFor);
				button.click();
			}
		} catch (Exception FailToClick) {
			System.out.println(">>>>>> Unable to click Button [ " + buttonName + " ] pls check");
			Utilities.handleError(FailToClick, driver);
		}
	}

	// Enter text where dropdown & enter needed make sure tag start from
	// >>>INPUT<<<<
	public static void enterTextInDropDown(String text, String expForname, WebDriver driver)
			throws InterruptedException {
		WebElement enterText = new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(expForname)));
		enterText.clear();
		Thread.sleep(500);
		enterText.sendKeys(text);
		enterText.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
	}

	// Enter text in Normal BOX >>>INPUT<<<<
	public static void enterTextNormalBox(String text, String expForname, WebDriver driver)
			throws InterruptedException {
		WebElement enterText = new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(expForname)));
		enterText.clear();
		Thread.sleep(500);
		enterText.sendKeys(text);
		Thread.sleep(100);
	}

	public static void enterTextSpecialBox(String text, String expForname, WebDriver driver)
			throws InterruptedException {
		WebElement enterText = new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(expForname)));
		Thread.sleep(500);
		enterText.sendKeys("\b\b\b\b\b\b\b\b" + text);
		Thread.sleep(100);
	}

	/*---------select item from NormalDrop Down---------*/
	public static void enterTextandSelectCUSTOM(String text, WebElement element, WebDriver driver) {
		WebElement enterText = element;
		boolean success = false;
		int seconds = 30;
		for (int num_try = 1; !success && num_try <= seconds; num_try++) {
			try {
				enterText.clear();
				enterText.sendKeys(text);
				Thread.sleep(1000);
				WebElement element1 = driver.findElement(
						By.xpath("//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]//*[text()='"
								+ text + "']"));
				element1.click();
				Thread.sleep(1500);
				success = true;
			} catch (Exception EX) {
				EX.printStackTrace();
			}
		}
	}
	
	
	// select item from NormalDrop Down
	public static void enterTextandSelect(String text, String expForname, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		WebElement enterText = null;
		boolean success = false;
		for (int num_try = 1; !success && num_try < 10; num_try++) {
			try {
				enterText = driver.findElement(By.xpath(expForname));
				enterText.clear();
				enterText.sendKeys(text);
				Thread.sleep(1000);
				WebElement element = driver.findElement(
						By.xpath("//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]//*[text()='"
								+ text + "']"));
				element.click();
				Thread.sleep(1500);
				success = true;
			} catch (Exception EX) {
				if (num_try == 9) {
					Utilities.handleError(EX, driver);
				}
			}
		}
	}

	// -------- Enter Qty or Amount or Price at Line label
	public static void enter_LinelabelAmount(String text, WebDriver driver) throws InterruptedException {
		WebElement enterText = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[@class='x-layer x-editor x-small-editor x-grid-editor' and contains(@style,'visible')]//input[@type='text']")));
		Thread.sleep(500);
		enterText.sendKeys("\b\b\b\b\b\b\b\b" + text);
		Thread.sleep(500);
	}

	// for above method [ enter_LinelabelAmount(String text,WebDriver driver) ]
	public static void enter_LinelabelAmount_thenEnter(WebDriver driver) throws InterruptedException {
		WebElement enterText = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[@class='x-layer x-editor x-small-editor x-grid-editor' and contains(@style,'visible')]//input[@type='text']")));
		enterText.sendKeys(Keys.ENTER);
	}

	// Click button using Sikuli tool
	public static void sikuliHandle(String btnName) throws InterruptedException, FindFailed {
		String button_path = System.getProperty("user.dir") + "\\sikulliSnaps\\" + btnName + ".PNG";
		System.out.println(button_path);
		Screen screen = new Screen();
		Pattern click_button = new Pattern(button_path);
		Thread.sleep(500);
		boolean success = false;
		for (int num_try = 1; !success && num_try < 5; num_try++) {
			try {
				screen.click(click_button);
				Thread.sleep(3000);
				success = true;
			} catch (Exception Ex) {
				if (num_try == 2) {
				}
			}
		}
	}

	// Click button using Sikuli tool for EXPORT
	public static void sikuliHandleExport(String button_path) throws InterruptedException, FindFailed {
		// String button_path =
		// System.getProperty("user.dir")+"\\sikulliSnaps\\"+ btnName +".PNG";
		Screen screen = new Screen();
		Pattern click_button = new Pattern(button_path);
		Thread.sleep(500);
		screen.click(click_button);
		Thread.sleep(500);
	}

	// Check or Uncheck checkBox button
	public static void clickCheckBox(String checkBoxXpath, String chkOrUnchk, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		WebElement chkBox = null;
		boolean success = false;
		for (int num_try = 1; !success && num_try < 10; num_try++) {
			try {
				chkBox = driver.findElement(By.xpath(checkBoxXpath));
				if (chkBox.isEnabled()) {
					Thread.sleep(1000);
					if (chkOrUnchk.equalsIgnoreCase("CHECK")) {
						if (!chkBox.isSelected()) {
							chkBox.click();
							Thread.sleep(1500);
							success = true;
						} else {
							// already checked
							Thread.sleep(1500);
							success = true;
						}
					} else if (chkOrUnchk.equalsIgnoreCase("UNCHECK")) {
						if (chkBox.isSelected()) {
							chkBox.click();
							Thread.sleep(1500);
							success = true;
						} else {
							// already Unchecked
							Thread.sleep(1500);
							success = true;
						}
					}
				}
			} catch (Exception Ex) {
				if (num_try == 9) {
					Utilities.handleError(Ex, driver);
				}
			}
		}
	}

	// Select any item from porper DropDown

	public static void selectItemfromDropDown(String drpDwnArrow, String itemInputBoxLocator, String itemName,
			String rslTable, WebDriver driver) throws InterruptedException, AWTException {
		// to open dropdown
		Utilities.HoverandClick(drpDwnArrow, driver);
		// send text in dropdown area
		Utilities.enterTextNormalBox(itemName, itemInputBoxLocator, driver);
		// select listed result [item]
		Utilities.HoverandClick(rslTable, driver);
		// select ok arrow
		Utilities.HoverandClick(drpDwnArrow, driver);
	}

	// * * * * * Return File path * * * * *
	public static String importFile(String fileNamewithExt) throws InterruptedException, FindFailed {
		String File_path = System.getProperty("user.dir") + "\\ImportFile\\" + fileNamewithExt;
		return File_path;

	}

	// ***********************************************************************
	// Hover to Element & perfrom click
	public static void HoverandClick(String xpathOfelement, WebDriver driver) throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement hoverElementandClick = new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfelement)));
		Thread.sleep(500);
		action.moveToElement(hoverElementandClick).build().perform();
		Thread.sleep(500);
		hoverElementandClick.click();
		Thread.sleep(1000);
	}

	// Explicit wait for 30 sec then Click
	public static void waitandClick(String xpathOfelement, WebDriver driver) throws InterruptedException {
		WebElement element = new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfelement)));
		Thread.sleep(1000);
		;
		element.click();
		Thread.sleep(500);
	}

	// handle other Element clickable error
	public static void click_element(String xpathExpression, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		{
			WebElement element = null;
			boolean success = false;
			int seconds = 30;
			for (int num_try = 1; !success && num_try <= seconds; num_try++) {
				try {
					element = driver.findElement(By.xpath(xpathExpression));
					element.click();
					Thread.sleep(1000);
					success = true;
				} catch (Exception EX) {
					Thread.sleep(2000);
					if (num_try == seconds) {
						Utilities.handleError(EX, driver);
					}
				}
			}
		}
	}

	// Just Hover
	public static void justHover(String xpathOfelement, WebDriver driver) throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement hoverElementandClick = new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfelement)));
		action.moveToElement(hoverElementandClick).build().perform();
		Thread.sleep(1000);
	}

	// *************** getText ****************************

	public static String getText(String xpathOfelement, WebDriver driver) {
		WebElement element = new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOfelement)));
		String myText = element.getText();
		return myText;
	}

	// *************** sizeOfTotalElements ****************************

	public static int totalSize(String xpathOfelement, WebDriver driver) {
		List<WebElement> elements = driver.findElements(By.xpath(xpathOfelement));
		int elemensSize = elements.size();
		return elemensSize;
	}

	// click & enter text on Line Level e.g Like Quantity
	public static void clickAndEnterText(String text, String xpathOfelement, WebDriver driver)
			throws InterruptedException, AWTException {
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

	}

	// to check line level product is added or not OR to see for loaded
	public static void formLoaded(WebDriver driver) throws InterruptedException {
		WebElement chkBox = new WebDriverWait(driver, 45).until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[text()='Product ID']/ancestor::div[3]/following::div[1]/div[1]/div[2]/table/tbody/tr//*[@class='x-grid3-row-checker']")));

		if (chkBox.isDisplayed()) {
			Thread.sleep(1000);
			// System.out.println("**** form loaded ***** ");
		}
	}

	// =====Wait to till disappear any element (e.g: Loading Screen, Laoding
	// ite, )=====
	public static void isElementGone(String xpath, int waitSeconds, WebDriver driver) throws InterruptedException {
		boolean isGone = false;
		for (int num_try = 1; !isGone && num_try <= waitSeconds; num_try++) {
			WebElement element = null;
			try {
				element = driver.findElement(By.xpath(xpath));
				Thread.sleep(1000);
				if (element.isDisplayed()) {
					// wait More
					if (num_try == waitSeconds) {
						driver.navigate().refresh(); // Refresh page as elements
														// still on page for
														// Givn amt of time
					}
				}
			} catch (Exception Ex) {
				isGone = true;
				Thread.sleep(1000);
			}
		}
	}

	// =====Wait to till disappear [Loading ....] (e.g: Loading Screen, Laoding
	// ite, ) <hardCoded>=====
	public static void isLoadingDisappear(int waitSeconds, WebDriver driver) throws InterruptedException {
		boolean isGone = false;
		for (int num_try = 1; !isGone && num_try <= waitSeconds; num_try++) {
			WebElement element = null;
			try {
				element = driver.findElement(By.xpath("//*[text()='Loading...']"));
				Thread.sleep(1000);
				if (element.isDisplayed()) {
					// wait More
					if (num_try == waitSeconds) {
						driver.navigate().refresh(); // Refresh page as elements
														// still on page for
														// Givn amt of time
					}
				}
			} catch (Exception Ex) {
				isGone = true;
				Thread.sleep(500);
			}
		}
	}

	// Open Product Master by closing Create Product
	public static void openProductMaster(WebDriver driver) throws IOException, InterruptedException {
		Properties pro = Utilities.fetchProValue("OR_ProductMaster.properties");
		Utilities.waitandClick(pro.getProperty("createProductIcon"), driver);
		boolean isGone = false;
		for (int num_try = 1; !isGone && num_try <= 100; num_try++) {
			try {
				WebElement closeTab = driver.findElement(By.xpath("//*[@id='as__productwin']/a[1]"));
				closeTab.click();
				if (driver.findElement(By.xpath("//*[contains(@style,'visible')]//*[text()='Warning']"))
						.isDisplayed()) {
					Thread.sleep(500);
					driver.findElement(By.xpath("//button[text()='Yes']")).click();
					isGone = true;
				}
			} catch (Exception Ex) {
				Thread.sleep(666);
				// System.out.println(num_try);
			}
		}
	}

	// For checking form is loading or not
	public static void formloading(WebDriver driver, String UtilitiesName, String ModuleIcon, String ModuleName,
			String closeform) throws IOException, InterruptedException, AWTException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Properties pro = Utilities.fetchProValue(UtilitiesName);

		Utilities.waitandClick(pro.getProperty(ModuleIcon), driver);

		WebElement Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Save']")));

		if (Element.isDisplayed()) {
			System.out.println(ModuleName + " Form is Opened Sucessfully");
		} else {
			System.out.println("Unable to Open " + ModuleName + " Form");
		}
		Utilities.waitandClick(pro.getProperty(closeform), driver);
		Utilities.clickButton("Yes", 1000, driver);
	}

	// **************************** Get Integer/Double Value from a String
	// ***************************************
	public static double getIntegerFrmString(String whichString) {
		whichString = whichString.replace("(", "");
		whichString = whichString.replace(")", "");
		Scanner st = new Scanner(whichString);

		try {
			while (!st.hasNextDouble()) {
				st.next();
			}
		} catch (Exception Ex) {
		}
		double value = st.nextDouble();
		return value;
		// System.out.println(value);
	}

	// **************************** Select only /n records from Report
	// ***************************************
	public static void selectRecords(int numberOfRecords, WebDriver driver) throws InterruptedException {
		String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";
		Utilities.isElementGone(xpathOfLoading, 180, driver);
		Thread.sleep(250);
		driver.findElement(By.xpath("//*[text()='Show']/parent::td/following::td[1]//input")).click();
		driver.findElement(By.xpath("//*[contains(@style,'visible')]//*[text()='" + numberOfRecords + "']")).click();
		Thread.sleep(500);
	}

	// **************************** for checking report is loading or not
	// ***************************************
	public static void Freportloading(WebDriver driver, String ORName, String ReportIcOn, String XpathsORname,
			String ReportName, String CloseReport) throws InterruptedException, IOException {

		Properties pro = Utilities.fetchProValue(ORName);
		Utilities.waitandClick(pro.getProperty(ReportIcOn), driver);
		String text = driver.findElement(By.xpath(pro.getProperty(XpathsORname))).getText();
		// System.out.println(text);
		String text1 = "There is no record to display.";
		// System.out.println(text1);
		if (text.startsWith(text1)) {
			System.out.println(ReportName + " is loaded Successfully");
		} else {
			System.out.println("Failed to load " + ReportName);
		}
		Utilities.waitandClick(pro.getProperty(CloseReport), driver);
	}

	public static void ReportLoading(WebDriver driver, String UtilitiesName, String ReportName, String ModuleName,
			String closeform) throws IOException, InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			Properties pro = Utilities.fetchProValue(UtilitiesName);

			Utilities.waitandClick(pro.getProperty(ReportName), driver);

			WebElement Element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Fetch']")));

			if (Element.isDisplayed()) {
				System.out.println(ModuleName + " report is Opened Sucessfully");
			} else {
				System.out.println("Unable to Open " + ModuleName + " report");
			}

			Thread.sleep(2000);
			Utilities.waitandClick(pro.getProperty(closeform), driver);
		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

	/*********************
	 * For handling WebElement by Send Value
	 *************************/

	public static void waitandSendkey(String RepositoryName, WebDriver driver, String Value)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 2000);
		WebElement Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(RepositoryName)));
		Element.clear();
		Element.sendKeys(Value);
		Thread.sleep(1500);
		Element.sendKeys(Keys.ENTER);

	}

	/************************** For Performance testing *******************/

	public static void reportloadtime(WebDriver driver, String ORName, String ReportIcon, String XpathNameORName,
			String ReportName, String CloseReport) {

		try {
			Properties pro = Utilities.fetchProValue(ORName);
			WebDriverWait wait = new WebDriverWait(driver, 2000);

			Utilities.waitandClick(pro.getProperty(ReportIcon), driver);
			Utilities.clickButton("Fetch", 2000, driver);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(new Date());

			WebElement BS = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty(XpathNameORName))));

			if (BS.isDisplayed()) {
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				long diff = cal2.getTimeInMillis() - cal1.getTimeInMillis();
				System.out.println(
						ReportName + "Report loaded in Seconds = " + diff / 1000 % 60 + " | Milli Seconds = " + diff);
			} else {
				System.out.println("unable to load Report" + ReportName);
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				long diff = cal2.getTimeInMillis() - cal1.getTimeInMillis();
				System.out.println(
						ReportName + "Report loaded in Seconds = " + diff / 1000 % 60 + " | Milli Seconds = " + diff);
			}
			Utilities.waitandClick(pro.getProperty(CloseReport), driver);
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	public static void reportloadtimeWithoutfetch(WebDriver driver, String ORName, String ReportIcon,
			String XpathNameORName, String ReportName, String CloseReport) {

		try {
			Properties pro = Utilities.fetchProValue(ORName);
			WebDriverWait wait = new WebDriverWait(driver, 2000);

			Utilities.waitandClick(pro.getProperty(ReportIcon), driver);

			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(new Date());

			WebElement BS = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty(XpathNameORName))));

			if (BS.isDisplayed()) {
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				long diff = cal2.getTimeInMillis() - cal1.getTimeInMillis();
				System.out.println(
						ReportName + "Report loaded in Seconds = " + diff / 1000 % 60 + " | Milli Seconds = " + diff);
			} else {
				System.out.println("unable to load Report" + ReportName);
			}
			Utilities.waitandClick(pro.getProperty(CloseReport), driver);
		} catch (Exception EX) {
			EX.printStackTrace();
		}
	}

	/************************* For Robot Class *******************************/

	public static void Enter() throws AWTException, InterruptedException {
		Robot R = new Robot();
		R.keyPress(KeyEvent.VK_ENTER);
		R.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
	}

	public static void Tab() throws AWTException, InterruptedException {
		Robot R = new Robot();
		R.keyPress(KeyEvent.VK_TAB);
		R.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);

	}

	public static void doubleClick(String elmxpath, WebDriver driver) {
		try {
			WebElement element = driver.findElement(By.xpath(elmxpath));
			Actions action = new Actions(driver);
			action.doubleClick(element).perform();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/******************* Rounding ***********************/

	public static double round(double Rval, String companyid) {

		int Rpl = 2;
		// if(Constants.CompanyPreferencePrecisionMap.containsKey(companyid)) {
		// Rpl=(Integer)
		// Constants.CompanyPreferencePrecisionMap.get(companyid).get(Constants.amountdecimalforcompany);
		// }
		// val = val + 1/Math.pow(10,Wtf.AMOUNT_DIGIT_AFTER_DECIMAL+2);
		double p = (double) Math.pow(10, Rpl);
		double sign = 1;
		if (Rval < 0) {
			sign = -1;
		}

		Rval = Math.abs(Rval) + 1 / (double) Math.pow(10, Rpl + 10);
		Rval = Rval * p;
		double tmp = Math.round(Rval);
		return (double) (tmp / p) * (sign);
	}
}
