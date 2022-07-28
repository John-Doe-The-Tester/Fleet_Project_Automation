package com.fleetApp.pages;

import com.fleetApp.utilities.BrowserUtils;
import com.fleetApp.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GeneralInfoPage extends BasePage {

	public void clickAnyBtn(String btnName){
		WebElement btn = Driver.get().findElement(By.xpath("//*[text()[normalize-space() = '" + btnName + "']]"));
		BrowserUtils.clickWithWait(btn,2);
	}

}
//div[@class='pull-left btn-group icons-holder']//*[text()='Add Event']