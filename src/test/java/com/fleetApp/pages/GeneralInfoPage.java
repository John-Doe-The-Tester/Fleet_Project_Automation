package com.fleetApp.pages;

import com.fleetApp.utilities.BrowserUtils;
import com.fleetApp.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GeneralInfoPage extends BasePage {

	@FindBy(css = "div.items.list-box.list-shaped div.list-item strong")
	private List<WebElement> allEvents;


	public void clickAnyBtn(String btnName) {
		WebElement btn = Driver.get().findElement(By.xpath("//*[text()[normalize-space() = '" + btnName + "']]"));
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

}
//div[@class='pull-left btn-group icons-holder']//*[text()='Add Event']