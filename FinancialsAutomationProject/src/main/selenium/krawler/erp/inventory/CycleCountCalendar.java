package krawler.erp.inventory;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import krawler.erp.page.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class CycleCountCalendar {

	public static void setCycleCountFrequencies(String cycleCountDate, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			final Properties pro = Utilities.fetchProValue("OR_CycleCountCalendar.properties");
			InvUtilities.enableExpander(driver);
			InvUtilities.expandInventory(driver);
			InvUtilities.expandEntry(driver);
			Utilities.click_element(pro.getProperty("CycleCountCalendarLink"), driver);

			SimpleDateFormat inputDateFormater = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat outputDateFormater = new SimpleDateFormat("MMMM");
			int dateRowIndex = 0, frequencyRowIndex = 0;
			for (int rowIndex = 1; rowIndex <= driver
					.findElements(By
							.xpath(".//*[@id='CycleCountCalendarTabId']/div/div/div/div/div[2]/div/div/div[1]/div/div/table/thead/tr/td"))
					.size(); rowIndex++) {
				if (driver.findElement(By
						.xpath(".//*[@id='CycleCountCalendarTabId']/div/div/div/div/div[2]/div/div/div[1]/div/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText().equalsIgnoreCase("Date")) {
					dateRowIndex = rowIndex;
				} else if (driver.findElement(By
						.xpath(".//*[@id='CycleCountCalendarTabId']/div/div/div/div/div[2]/div/div/div[1]/div/div/table/thead/tr/td["
								+ rowIndex + "]/div"))
						.getText().equalsIgnoreCase("Frequency")) {
					frequencyRowIndex = rowIndex;
				}
			}

			// select time
			Utilities.HoverandClick(
					"//*[text()='" + cycleCountDate + "']/ancestor::tr/td[" + frequencyRowIndex + "]/div/div", driver);
			Utilities.click_element(pro.getProperty("freqCancel"), driver);
			Utilities.click_element(pro.getProperty("freqSelect"), driver);

			int totalTypes = Utilities
					.totalSize("//*[contains(@style,'visible') and @class='x-layer x-combo-list ']/div/div", driver);
			for (int i = 1; i <= totalTypes; i++) {
				Utilities.click_element(
						"//*[contains(@style,'visible') and @class='x-layer x-combo-list ']/div/div[" + i + "]//td",
						driver);
			}
			Utilities.click_element(pro.getProperty("saveCycleCountCalendarFrequencyBtn"), driver);
			Utilities.clickButton("OK", 1000, driver);
			Utilities.click_element(pro.getProperty("CycleCountCloseBtn"), driver);
			System.out.println("Cycle count frequency for date [" + cycleCountDate + "] is Set !!!!!");
		} catch (Exception EX) {
			System.out.println(
					"Failed to set Cycle count frequency for date [" + cycleCountDate + "] plz check Log !!!!!");
			Utilities.handleError(EX, driver);
		}
	}

}
