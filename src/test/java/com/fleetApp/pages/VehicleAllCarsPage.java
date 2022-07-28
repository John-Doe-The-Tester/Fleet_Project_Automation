package com.fleetApp.pages;

import com.fleetApp.utilities.BrowserUtils;
import com.fleetApp.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VehicleAllCarsPage extends BasePage {

	@FindBy(xpath = "//tr[@class='grid-row row-click-action'][5]")
	private WebElement anyRow;

	public void clickAnyRow() {
		//try to click the row three times
		//we use try-catch block with a for loop
		//because sometimes we might have a problem
		//clicking the row from the table

		for (int i = 0; i < 3; i++) {
			try {
				BrowserUtils.waitClickability(anyRow, 2);
				anyRow.click();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
