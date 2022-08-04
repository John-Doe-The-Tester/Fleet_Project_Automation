package com.fleetApp.pages;

import com.fleetApp.utilities.BrowserUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class GridSettingsPage extends BasePage{

	@FindBy(xpath = "//div[@class='column-manager-title'][text()='Grid Settings']/..")
	private WebElement gridSettingsDiv;

	@FindBy(css = "td.title-cell label")
	private List<WebElement> allGridSettingsOptions;

	@FindBy(css = "input[placeholder='Quick Search']")
	private WebElement quickSearchfield;

	public void isDisplayedGridSettings(){
		BrowserUtils.waitForVisibility(gridSettingsDiv,2);
		Assert.assertTrue(gridSettingsDiv.isDisplayed());
	}

	public List<String> getAllGridSettingsOptions(){
		List<String> allOptionsString = new ArrayList<>();
		List<WebElement> allOptionsWE = this.allGridSettingsOptions;
		BrowserUtils.waitForVisibility(gridSettingsDiv,2);

		for (int i = 0; i < allOptionsWE.size(); i++) {
			allOptionsString.add(allOptionsWE.get(i).getText());
		}

		return allOptionsString;
	}

	public void findCollumn(List <String> columns){

		for (int i = 0; i < columns.size(); i++) {
			//clear the field in case it's not empty
			try {
				BrowserUtils.waitClickability(quickSearchfield,2);
				quickSearchfield.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}

			//search for a value
			BrowserUtils.sendKeysWithWait(quickSearchfield, columns.get(i),1);

			//get all the results of the previous search into an arraylist
			List<WebElement> resultsWE = driver.findElements(By.cssSelector("td.title-cell label"));
			List<String> resultsString = new ArrayList<>();
			BrowserUtils.wait(0.5);
			for (int j = 0; j < resultsWE.size(); j++) {
				if (resultsWE.get(j).isDisplayed()) {
					resultsString.add(resultsWE.get(j).getText());
				}
			}

			//verify the results contain our keyword
			System.out.println("query.: " + columns.get(i));
			System.out.println("result: " + resultsString);
			for (int j = 0; j < resultsString.size(); j++) {
				Assert.assertTrue(resultsString.get(j).contains(columns.get(i)));
			}
		}




	}


}
