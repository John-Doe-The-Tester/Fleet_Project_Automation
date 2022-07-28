package com.fleetApp.pages;

import com.fleetApp.utilities.BrowserUtils;
import com.fleetApp.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

	protected WebDriver driver;

	//constructor
	BasePage() {
		this.driver = Driver.get();
		PageFactory.initElements(driver, this);
	}

	//---locators----------------
	@FindBy(css = "div[class='loader-mask shown']")
	@CacheLookup
	protected WebElement loaderMask;


	public void waitUntilLoaderScreenDisappear() {
		try {
			WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
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
		menu1.click();
	}

	public void navigateToModule(String menuName, String subMenuName) { //Fleet - Vehicles
		WebElement menu1 = driver.findElement(By.xpath("//span[text()[normalize-space() = '" + menuName + "']]"));
		BrowserUtils.clickWithWait(menu1,1);

		WebElement menu2 = driver.findElement(By.xpath("//span[text()[normalize-space() = '" + subMenuName + "']]"));
		BrowserUtils.clickWithWait(menu2,1);
	}

	public void navigateToModule(String menuName, String subMenuName, String subSubmenu) {
		WebElement menu1 = driver.findElement(By.xpath("//span[text()[normalize-space() = '" + menuName + "']]"));
		BrowserUtils.clickWithWait(menu1,1);

		WebElement menu2 = driver.findElement(By.xpath("//span[text()[normalize-space() = '" + subMenuName + "']]"));
		BrowserUtils.clickWithWait(menu2,1);

		WebElement menu3 = driver.findElement(By.xpath("//span[text()[normalize-space() = '" + subSubmenu + "']]"));
		BrowserUtils.clickWithWait(menu3,1);
	}

	public void goBack(){
		driver.navigate().back();
	}


}
