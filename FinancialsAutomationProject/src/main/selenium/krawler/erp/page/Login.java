package krawler.erp.page;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
	public static WebDriver loginERP(String url, String id, String pwd, int implicitWait)
			throws InterruptedException, IOException {

		String driverPath = System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
		Properties pro = Utilities.fetchProValue("OR_Login.properties");
		driver.get(url);

		// System.out.println(pro.getProperty("login"));
		Utilities.enterTextNormalBox(id, pro.getProperty("login"), driver);
		Utilities.enterTextNormalBox(pwd, pro.getProperty("pwd"), driver);
		Utilities.HoverandClick(pro.getProperty("loginButton"), driver);

		Thread.sleep(5000);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		if (driver.getTitle().contains("Workspace - ERP")) {
			System.out.println("Successsfully login to ERP's Home page. Welcome to World of Automation");

		} else if (driver.getTitle().contains("Workspace - CRM")) {
			System.out.println("Successsfully login to CRM's Home page. Welcome to World of Automation");
		} else {
			Thread.sleep(7000);
			System.out.println("Unable to login home page");
		}
		return driver;
	}

	/*
	 * public static void main(String[] args) throws InterruptedException,
	 * IOException { loginERP(); }
	 */

}
