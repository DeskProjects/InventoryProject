package krawler.erp.page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import krawler.erp.testCases.SmokeTestSuiteSG.A.BaseSetUp;

public class EmailFunctionality extends BaseSetUp {

	public static String emailAdd = BaseSetUp.emailAddress;

	// ***************************** Email after Save
	// **************************************
	// email id: automationtestdeskera@gmail.com
	// pass : krawler123
	public static void email_AfterSave(String documentid, String subjectLine, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		/*
		 * String mailBody =
		 * "This email generated after Save for Automation Test on ["+Utilities.
		 * currentDateTime()+"] for document No. - ["+ documentid +"]"
		 * +"\n\n -By\n  Amol Gaikwad"; try {
		 * Utilities.click_element("//button[text()='Email']", driver); // Enter
		 * Email address Utilities.click_element("//*[@name='to']", driver);
		 * Utilities.enterTextNormalBox(emailAdd, "//*[@name='to']", driver); //
		 * Enter subject Utilities.click_element("//*[@name='subject']",
		 * driver); Utilities.enterTextNormalBox("[Automation Test]"+ " "+
		 * subjectLine, "//*[@name='subject']", driver);
		 * 
		 * WebElement frame1 =
		 * driver.findElement(By.xpath("//*[@name='message']/following::iframe")
		 * ); driver.switchTo().frame(frame1); WebElement editable =
		 * driver.switchTo().activeElement(); editable.clear();
		 * editable.sendKeys(mailBody); driver.switchTo().defaultContent();
		 * 
		 * Utilities.click_element("//*[@name='emailcopy']", driver);
		 * Utilities.clickButton("Send", 500, driver);
		 * Utilities.clickButton("OK", 0, driver);
		 * Utilities.isLoadingDisappear(120, driver);
		 * System.out.println("Email has been successfully sent documnet ["+
		 * documentid +"] !!!"); } catch (Exception EX) {
		 * System.out.println("Email NOT sent for documnet ["+ documentid
		 * +"] !!!"); Utilities.handleError(EX, driver); }
		 */}

	// ***************************** Email from Report
	// **************************************

	public static void email_fromReports(String documentid, String subjectLine, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		/*
		 * 
		 * String
		 * emailAdd="automationtestdeskera@gmail.com; amol.gaikwad@deskera.com";
		 * //rahul.kaushik@krawlernetworks.com"); try { WebElement
		 * Emailbutton=new WebDriverWait(driver,30).until(ExpectedConditions.
		 * elementToBeClickable(By.xpath("//button[text()='Email']")));
		 * if(Emailbutton.isEnabled()) { Thread.sleep(500); Emailbutton.click();
		 * Thread.sleep(1000);
		 * 
		 * WebElement To=new WebDriverWait(driver,30).until(ExpectedConditions.
		 * visibilityOfElementLocated(By.name("to"))); To.clear();
		 * Thread.sleep(1500); To.sendKeys(emailAdd); Thread.sleep(1500);
		 * 
		 * WebElement subject=new
		 * WebDriverWait(driver,30).until(ExpectedConditions.
		 * visibilityOfElementLocated(By.name("subject"))); subject.clear();
		 * Thread.sleep(500);
		 * subject.sendKeys("[Automation Test][Email from Report section] "+
		 * subjectLine); Thread.sleep(1000);
		 * 
		 * Robot r =new Robot();
		 * 
		 * r.keyPress(KeyEvent.VK_TAB); Thread.sleep(1000);
		 * r.keyPress(KeyEvent.VK_TAB); Thread.sleep(500);
		 * r.keyRelease(KeyEvent.VK_TAB); Thread.sleep(1000);
		 * 
		 * r.keyPress(KeyEvent.VK_CONTROL); Thread.sleep(500);
		 * r.keyPress(KeyEvent.VK_A); Thread.sleep(500);
		 * r.keyRelease(KeyEvent.VK_CONTROL); Thread.sleep(500);
		 * r.keyRelease(KeyEvent.VK_A); Thread.sleep(1000);
		 * 
		 * Utilities.
		 * sendText("This email generated from [REPORT MODULE] during Automation Test on ["
		 * +Utilities.currentDateTime()+"] for document No. - ["+ documentid
		 * +"]" +"\n\n -By\n  Amol Gaikwad"); Thread.sleep(2000);
		 * 
		 * Utilities.clickButton("Send", 500, driver); Thread.sleep(1000);
		 * 
		 * r.keyPress(KeyEvent.VK_SPACE); r.keyRelease(KeyEvent.VK_SPACE);
		 * 
		 * System.out.println("Email has been successfully sent document ["+
		 * documentid +"] !!!");
		 * 
		 * 
		 * } } catch (Exception EX) {
		 * System.out.println("Email NOT sent for document ["+ documentid
		 * +"] !!!"); Utilities.handleError(EX, driver); }
		 * 
		 */}

}