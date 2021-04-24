package krawler.erp.utils;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import krawler.erp.page.Utilities;

public class SikulliUtil {

	// Give itteration number to wait & click sikuli Image
	public static void sikulli_waitClick(WebDriver driver, String buttonName)
			throws InterruptedException, AWTException, IOException {
		boolean success = false;
		for (int num_try = 1; !success && num_try < 21; num_try++) {
			try {
				clickBtn_ExportSpecific(buttonName);
				Thread.sleep(2000);
				success = true;
			} catch (Exception e) { // waitMore
				if (num_try == 20) {
					Utilities.handleError(e, driver);
				}
			}
		}
	}

	// Click button using Sikuli tool for EXPORT only
	public static void clickBtn_ExportSpecific(String buttonName) throws InterruptedException, FindFailed {
		String file_path = null;
		file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + buttonName + ".PNG";
		Screen screen = new Screen();
		Pattern click_button = new Pattern(file_path);
		Thread.sleep(500);
		screen.click(click_button);
	}

}
