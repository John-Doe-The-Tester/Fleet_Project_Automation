package com.fleetApp.pages;

import com.fleetApp.utilities.BrowserUtils;
import com.fleetApp.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VehicleAllCarsPage extends BasePage {

	@FindBy(xpath = "//tr[@class='grid-row row-click-action'][5]")
	private WebElement anyRow;

	@FindBy(css = "tr.grid-row")
	private List<WebElement> allRows;

	@FindBy(css = "div.btn-group button.btn.dropdown-toggle")
	private WebElement viewPerPage;

	@FindBy(css = "ul.dropdown-menu.pull-right a.dropdown-item")
	private List<WebElement> viewPerPageOptions;

	@FindBy(css = "td[data-column-label='Model Year']")
	private List<WebElement> allYears;


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

	public void clickViewPerPage(){
		BrowserUtils.clickWithWait(viewPerPage,2);
	}

	public void isDisplayedViewPerPage(){
		BrowserUtils.waitForVisibility(viewPerPage,5);
		Assert.assertTrue(viewPerPage.isDisplayed());
	}

	public String getValueViewPerPage(){
		BrowserUtils.waitForVisibility(viewPerPage,5);
		return viewPerPage.getText();
	}

	public void selectAllOptionsViewPerPage(List<String> expectedOptions){

		for (int i = 0; i < viewPerPageOptions.size(); i++) {
			clickViewPerPage();

			//click the option (10,25,50,100)
			BrowserUtils.clickWithWait(viewPerPageOptions.get(i),2);

			//verify  if selected
			BrowserUtils.wait(1);
			Assert.assertEquals(expectedOptions.get(i),viewPerPage.getText());

		}
	}

	public void selectOptionViewPerPage(String option){
		BrowserUtils.clickWithWait(viewPerPage,5);
		BrowserUtils.wait(1);
		WebElement optionWE = driver.findElement(By.xpath("//a[@class='dropdown-item'][text()[normalize-space() ='" + option + "']]"));
		BrowserUtils.clickWithWait(optionWE,2);
	}

	public void verifyNumberOfDisplayedRows(String expectedTotalRows){
		BrowserUtils.wait(0.5);
		System.out.println("Expected rows are: " + expectedTotalRows);
		System.out.println("Actual rows are: " + allRows.size());

		Assert.assertTrue(allRows.size() <= Integer.valueOf(expectedTotalRows));
		BrowserUtils.wait(1);
	}

	public void clickColumn(String column){
		WebElement columnWE = Driver.get().findElement(By.xpath("//*[text()='" + column + "']"));
		BrowserUtils.clickWithJSExe(columnWE);
		BrowserUtils.wait(1);
	}

	public void isColumnSorted(String column, String order){
		List<WebElement> actualValuesWE = driver.findElements(By.cssSelector("td[data-column-label='" + column + "']"));
		List<String> expetedValuesString = new ArrayList<>();
		List<String> actualValuesString = new ArrayList<>();

		//get text of each value and pass another list as a string
		for (int i = 0; i < actualValuesWE.size(); i++) {
			BrowserUtils.waitForVisibility(actualValuesWE.get(i),1);
			actualValuesString.add(actualValuesWE.get(i).getText());
			//create another list which is just a copy of curent list
			expetedValuesString = new ArrayList<>(actualValuesString);
		}

		if (order.toLowerCase().equals("ascending")) {
			//sort in ascending order
			Collections.sort(actualValuesString);
			Assert.assertEquals(expetedValuesString,actualValuesString);

			System.out.println("expected list: " + expetedValuesString);
			System.out.println("actual list: " + actualValuesString);
		} else if (order.toLowerCase().equals("descending")) {
			//sort in descending order
			Collections.sort(actualValuesString,Collections.reverseOrder());
			Assert.assertEquals(expetedValuesString,actualValuesString);

			System.out.println("expected list: " + expetedValuesString);
			System.out.println("actual list: " + actualValuesString);
		}
	}

	public void clickRightTopButtons(String button){
		WebElement buttonWE = driver.findElement(By.cssSelector("a[title='"+button+"']"));
		BrowserUtils.clickWithWait(buttonWE,2);
	}

}
