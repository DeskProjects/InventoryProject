package krawler.erp.FixedAsset;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

	public static void main(String[] args) throws InterruptedException, AWTException {
		// TODO Auto-generated method stub

		// System.setProperty("webdriver.chrome.driver","D:/chromeDriver.exe");
		// System.setProperty("webdriver.chrome.driver","C:\\Users\\krawler\\Downloads\\chromedriver\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\krawler\\Downloads\\chromedriver_win32_3.2\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// WebDriver driver = new FirefoxDriver();

		driver.get("http://192.168.0.208:8080/dotcomsmoketestingv21/a/208asset/");
		Thread.sleep(2000);

		// driver.manage().window().maximize();

		driver.findElement(By.xpath("//*[@id='UserName']")).sendKeys("admin");

		driver.findElement(By.xpath("//*[@id='Password']")).sendKeys("1234");

		driver.findElement(By.xpath("//*[@id='LoginButton']")).click();

		Thread.sleep(5000);

		driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/div[2]/div/div/div/div/div/map[1]/area[3]"))
				.click();

		WebElement e1 = driver.findElement(By.xpath("//*[@id='customer33purchasereceipt']"));
		e1.sendKeys("VID002");
		Thread.sleep(2000);
		e1.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		WebElement e = driver.findElement(By.xpath("//*[@id='sequenceFormatCombobox33purchasereceipt']"));
		e.clear();
		e.sendKeys("NA");
		Thread.sleep(2000);
		e.sendKeys(Keys.ENTER);

		WebElement f = driver.findElement(By.xpath("//*[@id='invoiceNo33purchasereceipt']"));
		f.sendKeys("12345");

		f.sendKeys(Keys.ENTER);

		// driver.findElement(By.xpath("//*[@id='sequenceFormatCombobox33purchasereceipt']")).sendKeys("NA");

		Thread.sleep(2000);

		driver.findElement(By
				.xpath("html/body/div[2]/div/div/div[2]/div[2]/div/div[2]/div/div[1]/div[1]/div/div/div[4]/div[2]/div/div/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td[5]/div/div"))
				.click();
		Thread.sleep(2000);

		driver.findElement(By
				.xpath("html/body/div[2]/div/div/div[2]/div[2]/div/div[2]/div/div[1]/div[1]/div/div/div[4]/div[2]/div/div/div/div/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td[7]/div"))
				.sendKeys("11");

		// driver.findElement(By.xpath("//*[@id='wtf-gen9']")).sendKeys("11");

	}

}