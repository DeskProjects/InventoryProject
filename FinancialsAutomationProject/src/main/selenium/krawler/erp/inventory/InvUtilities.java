package krawler.erp.inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InvUtilities {

	// INVENTORY EXPANDER
	public static void expandInventory(WebDriver driver) throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement element = null;
		try {
			element = new WebDriverWait(driver, 2).until(
					ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='inventory' and @class='x-panel ']")));
			Thread.sleep(1000);
			// means Inventory option already Opened
		} catch (Exception Ex) {
			element = new WebDriverWait(driver, 2).until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id='inventory' and contains(@class,'collapsed')]")));
			Thread.sleep(1000);
			if (element.isDisplayed()) {
				WebElement clickExp = new WebDriverWait(driver, 10)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='inventory']/div[1]/div")));
				action.moveToElement(clickExp).build().perform();
				Thread.sleep(500);
				clickExp.click();
			}
		}
	}

	// Entry EXPANDER
	public static void expandEntry(WebDriver driver) throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement element = null;
		try {
			element = new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[text()='Entry']/ancestor::div[contains(@class,'x-tree-node-expanded')]")));
			Thread.sleep(1000);
			// means Entry option already Opened
		} catch (Exception Ex) {
			element = new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//div[@id='inventory']//*[text()='Entry']/ancestor::div[contains(@class,'x-tree-node-collapsed')]")));
			Thread.sleep(1000);
			if (element.isDisplayed()) {
				WebElement clickExp = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(
						By.xpath(".//*[@id='inventory']/div[2]/div/div/div/div/div/div/div/ul/div/li[1]/div/img[1]")));
				action.moveToElement(clickExp).build().perform();
				Thread.sleep(500);
				clickExp.click();
			}
		}
	}

	// Reports EXPANDER
	public static void expandReport(WebDriver driver) throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement element = null;
		try {
			element = new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[text()='Reports']/ancestor::div[contains(@class,'x-tree-node-expanded')]")));
			Thread.sleep(1000);
			// means Entry option already Opened
		} catch (Exception Ex) {
			element = new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//div[@id='inventory']//*[text()='Reports']/ancestor::div[contains(@class,'x-tree-node-collapsed')]")));
			Thread.sleep(1000);
			if (element.isDisplayed()) {
				WebElement clickExp = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(
						By.xpath(".//*[@id='inventory']/div[2]/div/div/div/div/div/div/div/ul/div/li[2]/div/img[1]")));
				action.moveToElement(clickExp).build().perform();
				Thread.sleep(500);
				clickExp.click();
			}
		}
	}

	// alwaysExpand
	public static void enableExpander(WebDriver driver) throws InterruptedException {
		try {
			WebElement expIcon = new WebDriverWait(driver, 5)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='navigationpanel']/div[1]/div")));
			if (expIcon.isEnabled()) {
				Thread.sleep(1000);
			}
		} catch (Exception Ex) {
			WebElement expIcon = new WebDriverWait(driver, 5).until(ExpectedConditions
					.elementToBeClickable(By.xpath(".//*[@id='navigationpanel']/following-sibling::div[2]/div[1]")));
			if (expIcon.isEnabled()) {
				Thread.sleep(1500);
				expIcon.click();
			}
		}
	}

	// select item from NormalDrop Down
	public static void selectFromNormalDropDown(String text, String expForname, WebDriver driver)
			throws InterruptedException {
		WebElement enterText = null;
		enterText = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(expForname)));
		enterText.clear();
		enterText.sendKeys(text);
		Thread.sleep(1000);
		WebElement element = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]//*[contains(text(),'"
						+ text + "')]")));
		Thread.sleep(500);
		element.click();
		Thread.sleep(500);
	}

	// JobWorkOut EXPANDER
	public static void Expand_JobWorkOutMenu(WebDriver driver) throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement element = null;
		try {
			element = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[@id='jobworkoutmanagementNavigationPanelID' and @class='x-panel ']")));
			Thread.sleep(1000);
			// means Inventory option already Opened
		} catch (Exception Ex) {
			element = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[@id='jobworkoutmanagementNavigationPanelID' and contains(@class,'collapsed')]")));
			Thread.sleep(1000);
			if (element.isDisplayed()) {
				WebElement clickExp = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(
						By.xpath(".//*[@id='jobworkoutmanagementNavigationPanelID']/div[1]/div")));
				action.moveToElement(clickExp).build().perform();
				Thread.sleep(500);
				clickExp.click();
			}
		}
	}

	// Entry JobWorkOut Expander
	public static void Expand_JobWorkOutEntry(WebDriver driver) throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement element = null;
		try {
			element = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[text()='Entry']/ancestor::div[contains(@class,'x-tree-node-expanded')]")));
			Thread.sleep(1000);
			// means Entry option already Opened
		} catch (Exception Ex) {
			element = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//div[@id='jobworkoutmanagementNavigationPanelID']//*[text()='Entry']/ancestor::div[contains(@class,'x-tree-node-collapsed')]")));
			Thread.sleep(1000);
			if (element.isDisplayed()) {
				WebElement clickExp = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By
						.xpath(".//*[@id='jobworkoutmanagementNavigationPanelID']/div[2]/div/div/div/div/div/div/div/ul/div/li[1]/div/img[1]")));
				action.moveToElement(clickExp).build().perform();
				Thread.sleep(500);
				clickExp.click();
			}
		}
	}

	// Reports EXPANDER
	public static void Expand_JobWorkOutReport(WebDriver driver) throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement element = null;
		try {
			element = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(
					By.xpath("//*[text()='Reports']/ancestor::div[contains(@class,'x-tree-node-expanded')]")));
			Thread.sleep(1000);
			// means Entry option already Opened
		} catch (Exception Ex) {
			element = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//div[@id='jobworkoutmanagementNavigationPanelID']//*[text()='Reports']/ancestor::div[contains(@class,'x-tree-node-collapsed')]")));
			Thread.sleep(1000);
			if (element.isDisplayed()) {
				WebElement clickExp = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By
						.xpath(".//*[@id='jobworkoutmanagementNavigationPanelID']/div[2]/div/div/div/div/div/div/div/ul/div/li[2]/div/img[1]")));
				action.moveToElement(clickExp).build().perform();
				Thread.sleep(500);
				clickExp.click();
			}
		}
	}

}
