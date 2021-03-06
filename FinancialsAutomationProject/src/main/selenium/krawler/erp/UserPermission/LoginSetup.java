package krawler.erp.UserPermission;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class LoginSetup {
	public static void Signout(WebDriver driver) throws InterruptedException, AWTException, IOException {
		try {

			Properties UP = Utilities.fetchProValue("OR_UserPermission.properties");
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);

			// Sign out

			Utilities.HoverandClick(UP.getProperty("SignOut"), driver);
			System.out.println("Now admin has been  signed out");
			System.out.println("\n");

		}

		catch (Exception EX)

		{

			Utilities.handleError(EX, driver);
		}

	}

	public static void SignIn(String Username, String Password, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			Properties pro = Utilities.fetchProValue("OR_Login.properties");
			Thread.sleep(1000);
			Utilities.enterTextNormalBox(Username, pro.getProperty("login"), driver);
			Utilities.enterTextNormalBox(Password, pro.getProperty("pwd"), driver);
			Utilities.HoverandClick(pro.getProperty("loginButton"), driver);
			System.out.println("Now User " + Username + "is signed in.");

		} catch (Exception EX)

		{

			Utilities.handleError(EX, driver);
		}
	}
}
