package com.fleetApp.pages;

import com.fleetApp.utilities.BrowserUtils;
import com.fleetApp.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePage {

	protected WebDriver driver;

	@FindBy(css = "span.title-level-1")
	public List<WebElement> menuOptions;

	@FindBy(css = ".oro-subtitle")
	public WebElement pageSubTitle;


	//constructor
	BasePage() {
		this.driver = Driver.getDriver();
		PageFactory.initElements(driver, this);
	}

	//---locators----------------
	@FindBy(css = "div[class='loader-mask shown']")
	@CacheLookup
	protected WebElement loaderMask;


	public void waitUntilLoaderScreenDisappear() {
		try {
			WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
			wait.until(ExpectedConditions.invisibilityOf(loaderMask));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getPageTitle(){
		return driver.getTitle();
	}

	public void navigateToModule(String menuName) { //Fleet
		WebElement menu1 = driver.findElement(By.xpath("//span[text()[normalize-space() = '" + menuName + "']]"));
		BrowserUtils.clickWithWait(menu1,2);
	}

	public void navigateToModule(String menuName, String subMenuName) { //Fleet - Vehicles
		WebElement menu1 = driver.findElement(By.xpath("//span[text()[normalize-space() = '" + menuName + "']]"));
		BrowserUtils.clickWithWait(menu1,2);

		WebElement menu2 = driver.findElement(By.xpath("//span[text()[normalize-space() = '" + subMenuName + "']]"));
		BrowserUtils.clickWithWait(menu2,2);
	}

	public void navigateToModule(String menuName, String subMenuName, String subSubmenu) {
		WebElement menu1 = driver.findElement(By.xpath("//span[text()[normalize-space() = '" + menuName + "']]"));
		BrowserUtils.clickWithWait(menu1,2);

		WebElement menu2 = driver.findElement(By.xpath("//span[text()[normalize-space() = '" + subMenuName + "']]"));
		BrowserUtils.clickWithWait(menu2,2);

		WebElement menu3 = driver.findElement(By.xpath("//span[text()[normalize-space() = '" + subSubmenu + "']]"));
		BrowserUtils.clickWithWait(menu3,2);
	}

	public void goBack(){
		driver.navigate().back();
	}


}
