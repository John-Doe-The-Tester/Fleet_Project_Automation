package com.fleetApp.pages;

import com.fleetApp.utilities.BrowserUtils;
import com.fleetApp.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

	@FindBy(xpath = "//label[@class='dib'][2]")
	private WebElement totalPage;

	@FindBy(xpath = "//label[@class='dib'][3]")
	private WebElement totalRecording;

	@FindBy(css = "a[data-grid-pagination-direction='next']")
	private WebElement nextPageBtn;

	@FindBy(css = "a[data-grid-pagination-direction='prev']")
	private WebElement previousPageBtn;

	@FindBy(css = "input[type='number']")
	private WebElement currentPageNumberWE;

	@FindBy(css = "div.extra-actions-panel")
	private WebElement exportGrid;

	@FindBy(css = "div.message")
	private WebElement csvXlsxSuccess;


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
		BrowserUtils.waitClickability(columnWE,5);
		BrowserUtils.clickWithJSExe(columnWE);
		BrowserUtils.wait(1);
	}

	public void isColumnSorted(String column, String order){
		List<String> actualValuesString = saveColumnOrder(column);
		List<String> expetedValuesString = new ArrayList<>();

		//create another list which is just a copy of curent list
		expetedValuesString = new ArrayList<>(actualValuesString);

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

	public List<String> saveColumnOrder(String column){
		List<WebElement> actualValuesWE = driver.findElements(By.cssSelector("td[data-column-label='" + column + "']"));
		List<String> actualValuesString = new ArrayList<>();

		//get text of each value and pass another list as a string
		for (int i = 0; i < actualValuesWE.size(); i++) {
			BrowserUtils.waitForVisibility(actualValuesWE.get(i),1);
			actualValuesString.add(actualValuesWE.get(i).getText());
		}

		return actualValuesString;
	}

	public void clickRightTopButtons(String button){
		WebElement buttonWE = driver.findElement(By.cssSelector("a[title='"+button+"']"));
		BrowserUtils.clickWithWait(buttonWE,2);
		BrowserUtils.wait(0.7);
	}

	public void isDisplayedTotalPage(){
		BrowserUtils.waitForVisibility(totalPage,5);
		Assert.assertTrue(totalPage.isDisplayed());
	}
	public void isDisplayedTotalRecordings(){
		BrowserUtils.waitForVisibility(totalRecording,5);
		Assert.assertTrue(totalRecording.isDisplayed());
	}

	public void clickNextPreviousPageBtn(String direction){
		switch (direction) {
			case "next":
				nextPageBtn.click();
				break;

			case "previous":
				previousPageBtn.click();
				break;
		}
	}

	public String getCurrentPageNumberWE(){
		BrowserUtils.waitClickability(currentPageNumberWE,5);
		return currentPageNumberWE.getAttribute("value");
	}

	public void isEnabledNextPreviousBtn(String direction, String currentPageNumber){
		System.out.println("current page number from method: " + currentPageNumber);

		switch (direction) {
			case "previous":
				if (currentPageNumber.trim().equals("1")) {
					BrowserUtils.wait(10);
					System.out.println("if is true");
					Assert.assertFalse(previousPageBtn.isEnabled());
				}
				break;

			case "next":
				BrowserUtils.wait(1);
				String lastPage = totalPage.getText().split(" ")[1].trim();
				System.out.println("last page: " + lastPage);
				if (currentPageNumber.equals(lastPage)) {
					Assert.assertTrue(!nextPageBtn.isEnabled());
				}
				break;
		}


	}

	public void gotoLastPage(){
		String lastPage = totalPage.getText().split(" ")[1].trim();
		System.out.println("last page: " + lastPage);
		BrowserUtils.waitClickability(currentPageNumberWE,5);
		currentPageNumberWE.clear();
		currentPageNumberWE.sendKeys(lastPage, Keys.ENTER);
	}

	public void gotoFirstPage(){
		BrowserUtils.waitClickability(currentPageNumberWE,5);
		currentPageNumberWE.clear();
		currentPageNumberWE.sendKeys("1", Keys.ENTER);
	}

	public void downloadDataCSVorXLSX(String type){
		BrowserUtils.clickWithWait(exportGrid,5);
		WebElement typeWE = driver.findElement(By.cssSelector("a[title='" + type + "']"));
		BrowserUtils.clickWithWait(typeWE,2);
	}

	public void downloadCSVorXLSXsuccess(){
		BrowserUtils.waitForVisibility(csvXlsxSuccess,5);
		Assert.assertTrue(csvXlsxSuccess.isDisplayed());
	}



}
