package com.fleetApp.pages;

import com.fleetApp.utilities.BrowserUtils;
import com.fleetApp.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class GeneralInfoPage extends BasePage {

	@FindBy(css = "div.items.list-box.list-shaped div.list-item strong")
	private List<WebElement> allEvents;

	@FindBy(xpath = "//span[text()='General Information']")
	private WebElement generalInfoPage;

	@FindBy(css = "div.control-label")
	private List<WebElement> allCarInfo;


	public void clickAnyBtn(String btnName) {
		WebElement btn = Driver.getDriver().findElement(By.xpath("//*[text()[normalize-space() = '" + btnName + "']]"));
		BrowserUtils.clickWithWait(btn, 2);
	}

	public void isEventsDisplayed() {
		//just in case there is no any event
		//we use try-catch clock
		try {
			if (allEvents.size() == 0) {
				System.out.println("There is no event");
			} else {

				for (int i = 0; i < allEvents.size(); i++) {
					BrowserUtils.wait(0.5);
					System.out.println("Event: " + allEvents.get(i).getText());
					Assert.assertTrue(allEvents.get(i).isDisplayed());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getPageName(){
		BrowserUtils.waitForVisibility(generalInfoPage,2);
		Assert.assertTrue(generalInfoPage.isDisplayed());
	}

	public void isVisibleEditDeleteAddEvent(String btn1, String btn2, String btn3){
		WebElement btn1WE = driver.findElement(By.xpath("//*[normalize-space(text()) = '"+btn1+"']"));
		WebElement btn2WE = driver.findElement(By.xpath("//*[normalize-space(text()) = '"+btn2+"']"));
		WebElement btn3WE = driver.findElement(By.xpath("//*[normalize-space(text()) = '"+btn3+"']"));
		BrowserUtils.wait(1);

		Assert.assertTrue(btn1WE.isDisplayed());
		Assert.assertTrue(btn2WE.isDisplayed());
		Assert.assertTrue(btn3WE.isDisplayed());
	}

	public void isNotVisibleEditDeleteAddEvent(String btn1, String btn2, String btn3){
		WebElement btn1WE = driver.findElement(By.xpath("//*[normalize-space(text()) = '"+btn1+"']"));
		WebElement btn2WE = driver.findElement(By.xpath("//*[normalize-space(text()) = '"+btn2+"']"));
		WebElement btn3WE = driver.findElement(By.xpath("//*[normalize-space(text()) = '"+btn3+"']"));
		BrowserUtils.wait(1);

		Assert.assertFalse(btn1WE.isDisplayed());
		Assert.assertFalse(btn2WE.isDisplayed());
		Assert.assertFalse(btn3WE.isDisplayed());
	}

	public List<String> getCarInfoFromGeneralInfo(){
		List<WebElement> allCarInfoWE = this.allCarInfo;
		List<String> allCarInfoString = new ArrayList<>();
		BrowserUtils.waitForVisibility(generalInfoPage,2);

		//don't include the last three value cause they are irrelevant
		for (int i = 0; i < allCarInfoWE.size()-3; i++) {
			if (allCarInfoWE.get(i).getText().contains(",")) {
				allCarInfoString.add(allCarInfoWE.get(i).getText().replace(",",""));
				continue;
			}
			allCarInfoString.add(allCarInfoWE.get(i).getText());
		}

		return allCarInfoString;
	}

}