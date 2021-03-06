package krawler.erp.page;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class SetDateforCycleCount {

	public static void setCalendar(String year_month_day, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		String date_month_yeararr[] = year_month_day.split("-");
		String year = date_month_yeararr[0];
		String month = date_month_yeararr[1];
		String date = date_month_yeararr[2];

		// System.out.println(date + month + year);

		final Properties pro1 = Utilities.fetchProValue("OR_CalendarPick.properties");
		Thread.sleep(2000);

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS).pollingEvery(2,
				TimeUnit.SECONDS);

		Utilities.HoverandClick(pro1.getProperty("DatePickerButton"), driver);
		Utilities.HoverandClick(pro1.getProperty("MonthYearButton"), driver);

		WebElement NextYearbutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(pro1.getProperty("NextYear"))));
		WebElement PreviousYearbutton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(pro1.getProperty("PreviousYear"))));

		int rowMax = 6, colMax = 4;
		int rowMin = 2, colMin = 3;
		String max = driver.findElement(By.xpath("//div[@class='x-date-mp']/table/tbody/tr[6]/td[4]")).getText();
		// System.out.println(">>MAXXX>>>" + max);
		// String max=
		// driver.findElement(By.xpath("//div[@class='x-date-mp']/table/tbody/tr["+rowMax+"]/td["+colMax+"]")).getText();
		// System.out.println(">>>>>" + max);
		String min = driver
				.findElement(By.xpath("//div[@class='x-date-mp']/table/tbody/tr[" + rowMin + "]/td[" + colMin + "]"))
				.getText();
		// System.out.println(">>>>>>" + min);

		WebElement MonthYearTable = driver.findElement(By.xpath(pro1.getProperty("MonthYearTable")));
		List<WebElement> MonthYearTablecolumns = MonthYearTable.findElements(By.tagName("td"));

		if (Integer.parseInt(year) > Integer.parseInt(max)) {
			// move next
			NextYearbutton.click();
			for (WebElement YearCell : MonthYearTablecolumns) {
				if (YearCell.getText().equals(year)) {
					YearCell.click();
				}
			}
		}

		else if (Integer.parseInt(year) < Integer.parseInt(min)) {
			// move previous
			boolean flag = false;
			do {
				PreviousYearbutton.click();
				int RowIndex = 0, ColIndex = 0;
				flag = false;
				// beginingIndex=0;
				WebElement found = null;
				for (RowIndex = 1; RowIndex <= 6; RowIndex++) {
					for (ColIndex = 1; ColIndex <= 4; ColIndex++) {
						if (driver.findElement(By.xpath(
								"//div[@class='x-date-mp']/table/tbody/tr[" + RowIndex + "]/td[" + ColIndex + "]"))
								.getText().equalsIgnoreCase(year)) {
							found = driver.findElement(By.xpath(
									"//div[@class='x-date-mp']/table/tbody/tr[" + RowIndex + "]/td[" + ColIndex + "]"));
							flag = true;
							found.click();
							break;
						}
					}
					if (flag) {
						break;
					}
				}
			} // do
			while (flag == false);
		}

		else if (Integer.parseInt(year) <= Integer.parseInt(max) && Integer.parseInt(year) >= Integer.parseInt(min)) {
			// same page
			for (WebElement YearCell : MonthYearTablecolumns) {
				if (YearCell.getText().equals(year)) {
					YearCell.click();
				}
			}
		}
		Thread.sleep(2000);

		for (WebElement YearCell : MonthYearTablecolumns) {
			if (YearCell.getText().equalsIgnoreCase(month)) {
				YearCell.click();
				Thread.sleep(2000);
			}
		}

		WebElement oKbutton = driver.findElement(By.xpath("//button[@class='x-date-mp-ok']"));
		oKbutton.click();
		Thread.sleep(2000);

		// *************************************
		WebElement dateMenu = driver.findElement(By.xpath(pro1.getProperty("DateTable")));
		// This are the columns of the from date picker table
		List<WebElement> columns = dateMenu.findElements(By.xpath("//td[@class='x-date-active']"));

		for (WebElement dateCell : columns) {
			if (dateCell.getText().equals(date)) {
				dateCell.click();
				Thread.sleep(2000);
			}
		}

	}
}
