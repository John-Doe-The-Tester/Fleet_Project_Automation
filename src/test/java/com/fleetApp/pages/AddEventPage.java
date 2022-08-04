package com.fleetApp.pages;

import com.fleetApp.utilities.BrowserUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddEventPage extends BasePage {

	@FindBy(xpath = "//span[text()[normalize-space() = 'Add Event']]")
	private WebElement addEventPage;

	@FindBy(css = "button[title='close']")
	private WebElement closeBtn;

	@FindBy(css = "span[class='color']")
	private List<WebElement> allColors;

	@FindBy(xpath = "//select[contains(@id,'recurrence-repeats-view')]")
	private WebElement repeatDropdownMenu;


	public String getAddEventPageName() {
		BrowserUtils.waitForVisibility(addEventPage,5);
		return addEventPage.getText();
	}


	public void closeAddEventPage(){
		BrowserUtils.clickWithWait(closeBtn,2);
	}


	public void clickandVerifyColors(){
		for (int i = 0; i < allColors.size(); i++) {
			BrowserUtils.clickWithWait(allColors.get(i),2);
			BrowserUtils.wait(0.5);
			Assert.assertTrue(allColors.get(i).getAttribute("outerHTML").contains("data-selected"));
		}
	}


	public void clickAnyBtn(String btnName){
		WebElement button = driver.findElement(By.xpath("//*[text()[normalize-space() = '" + btnName + "']]/../..//input"));
		BrowserUtils.clickWithWait(button,2);

		BrowserUtils.wait(0.5);
		Assert.assertTrue(button.isSelected());
	}

	public void clickRepeatOptions(List<String> allRepeatOptions){
		Select select = new Select(repeatDropdownMenu);

		for (int i = 0; i < select.getOptions().size(); i++) {
			String expectedOption = allRepeatOptions.get(i);
			BrowserUtils.wait(0.3);
			select.selectByVisibleText(expectedOption);

			String actualOption = select.getFirstSelectedOption().getText();
			BrowserUtils.wait(0.3);
			Assert.assertEquals(actualOption,expectedOption);
		}
	}

	public void clickEndingOptions(List<String> allEndingOptions){
		for (int i = 0; i < allEndingOptions.size(); i++) {
			WebElement endingOption = driver.findElement(By.xpath("//*[text()[normalize-space() = '" + allEndingOptions.get(i) + "']]/../..//input"));
			BrowserUtils.clickWithWait(endingOption,3);

			BrowserUtils.wait(0.3);
			Assert.assertTrue(endingOption.isSelected());
		}
	}

}
