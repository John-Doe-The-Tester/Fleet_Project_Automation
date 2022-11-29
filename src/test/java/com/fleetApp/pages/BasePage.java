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

	protected static String userType;

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
	@FindBy(xpath = "(//div[@class='loader-frame'])[last()]")
	protected WebElement loaderMask;


	public void waitUntilLoaderScreenDisappear() {
		try {
			WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);
			wait.until(ExpectedConditions.invisibilityOf(loaderMask));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getPageTitle(){
		return driver.getTitle();
	}

	public void navigateToModule(String menuName) { //Fleet
		By menuLocator = By.xpath("//span[text()[normalize-space() = '" + menuName + "']]");
		BrowserUtils.clickWithTryCatch(menuLocator);
	}

	public void navigateToModule(String menuName, String subMenuName) { //Fleet - Vehicles
		By menuLocator = By.xpath("//span[text()[normalize-space() = '" + menuName + "']]");
		BrowserUtils.clickWithTryCatch(menuLocator);

		By subMenuLocator = By.xpath("//span[text()[normalize-space() = '" + subMenuName + "']]");
		BrowserUtils.clickWithTryCatch(subMenuLocator);
	}

	public void navigateToModule(String menuName, String subMenuName, String subSubmenu) {
		By menuLocator1 = By.xpath("//span[text()[normalize-space() = '" + menuName + "']]");
		BrowserUtils.clickWithTryCatch(menuLocator1);

		By menuLocator2 = By.xpath("//span[text()[normalize-space() = '" + subMenuName + "']]");
		BrowserUtils.clickWithTryCatch(menuLocator2);

		By menuLocator3 = By.xpath("//span[text()[normalize-space() = '" + subSubmenu + "']]");
		BrowserUtils.clickWithTryCatch(menuLocator3);
	}

	public void goBack(){
		driver.navigate().back();
	}


}
