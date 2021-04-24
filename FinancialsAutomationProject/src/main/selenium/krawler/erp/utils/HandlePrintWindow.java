package krawler.erp.utils;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import krawler.erp.page.Utilities;

public class HandlePrintWindow {

	// ************* Handle Print Window by using Sikuli tool
	// *************************************************
	public static void closePrintWindow(String ParentWindow, WebDriver driver) throws InterruptedException {
		/*
		String xpathOfLoading = "//*[contains(@style,'visible')]//*[contains(text(),'Please wait..')]";
		Utilities.isLoadingDisappear(120, driver);
		Utilities.isElementGone(xpathOfLoading, 120, driver);
		*/
		Thread.sleep(3000);

		try {
			Utilities.sikuliHandle("Print_Cancel");
		} catch (Exception ex) {
			System.out.println(ex);
		}

		Set s = driver.getWindowHandles(); // this method will gives you the
											// handles of all opened windows
		Iterator ite = s.iterator();
		while (ite.hasNext()) {
			String popupHandle = ite.next().toString();
			if (!popupHandle.contains(ParentWindow)) {
				driver.switchTo().window(popupHandle);
				driver.close(); // close print popup
				driver.switchTo().window(ParentWindow);
				Thread.sleep(2000);
			}
		}
	}

	// ---------- below method is Exampple not a Utils method
	// *************************************************************
	public void m1(WebDriver driver) {
		int headerCnt = Utilities.totalSize("//*[text()='Payment No']/ancestor::tr/td/div", driver);
		int indOfamtDue = 0, indOfamtPaid = 0;
		for (int i = 1; i <= headerCnt; i++) {
			try {
				Actions action = new Actions(driver);
				WebElement hover = driver
						.findElement(By.xpath("//*[text()='Payment No']/ancestor::tr/td[" + i + "]/div"));
				action.moveToElement(hover).build().perform();
				String myName = driver.findElement(By.xpath("//*[text()='Payment No']/ancestor::tr/td[" + i + "]/div"))
						.getText();
				// System.out.println(myName);
				if (myName.equalsIgnoreCase("Amount Due")) {
					indOfamtDue = i;
				}
				if (myName.equalsIgnoreCase("Amount Paid")) {
					indOfamtPaid = i;
				}
			} catch (Exception No) {
				// skip blank field
			}
		}
	}

}
