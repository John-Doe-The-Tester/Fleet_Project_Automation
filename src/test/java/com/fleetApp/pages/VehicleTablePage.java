package com.fleetApp.pages;

import com.fleetApp.utilities.BrowserUtils;
import com.fleetApp.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class VehicleTablePage extends BasePage {

	@FindBy(xpath = "//tr[@class='grid-row row-click-action'][5]")
	private WebElement anyRow;

	@FindBy(xpath = "//tr[@class='grid-row row-click-action'][5]//td")
	private List<WebElement> carInfoOfAnyRow;

	@FindBy(css = "tr.grid-row")
	private List<WebElement> allRows;

	@FindBy(css = "div.btn-group button.btn.dropdown-toggle")
	private WebElement viewPerPage;

	@FindBy(css = "div.message")
	private WebElement message;

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

	@FindBy(xpath = "(//div[@class='dropdown']//*[text()='...'])[5]")
	private WebElement threeDots5;

	@FindBy(xpath = "(//div[@class='dropdown']//*[text()='...'])[6]")
	private WebElement threeDots6;

	@FindBy(xpath = "//h3[text()='Delete Confirmation']")
	private WebElement deleteConfirmationText;

	@FindBy(css = "ul.dropdown-menu.dropdown-menu__action-cell.launchers-dropdown-menu.detach.dropdown-menu__floating")
	private WebElement threeDotsDivMenu;

	@FindBy(css = "a[title='Grid Settings']")
	private WebElement gridSettingsBtn;

	@FindBy(css = "a[title='Filters']")
	private WebElement filtersBtn;

	@FindBy(css = "a[title='Reset']")
	private WebElement resetBtn;

	@FindBy(xpath = "(//a[@class='btn ok btn-danger'])[1]")
	private WebElement yesDeleteBtn;


	@FindBy(css = "thead.grid-header th")
	private List<WebElement> allColumns;


	public void clickAnyRow() {
		//click any row with actions class
		BrowserUtils.clickWithMouseHoverAction(anyRow);

		//regular click method sometimes doesn't work
		//it works when we click twice
//		for (int i = 0; i < 2; i++) {
//			try {
//				BrowserUtils.waitClickability(anyRow, 2);
//				anyRow.click();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}

	}

	public void verifyDeleteConfPopUpDisplayed() {
		BrowserUtils.waitForVisibility(deleteConfirmationText, 3);
		Assert.assertTrue(deleteConfirmationText.isDisplayed());
	}

	public void clickViewPerPage() {
		BrowserUtils.clickWithWait(viewPerPage, 2);
	}

	public void isDisplayedViewPerPage() {
		BrowserUtils.waitForVisibility(viewPerPage, 5);
		Assert.assertTrue(viewPerPage.isDisplayed());
	}

	public String getValueViewPerPage() {
		BrowserUtils.waitForVisibility(viewPerPage, 5);
		return viewPerPage.getText();
	}

	public void selectAllOptionsViewPerPage(List<String> expectedOptions) {
		for (int i = 0; i < viewPerPageOptions.size(); i++) {
			clickViewPerPage();

			//click the option (10,25,50,100)
			BrowserUtils.clickWithWait(viewPerPageOptions.get(i), 2);

			//verify  if selected
			BrowserUtils.wait(1);
			Assert.assertEquals(expectedOptions.get(i), viewPerPage.getText());
		}
	}

	public void selectSingleOptionViewPerPage(String option) {
		BrowserUtils.clickWithWait(viewPerPage, 5);
		BrowserUtils.wait(1);
		WebElement optionWE = driver.findElement(By.xpath("//a[@class='dropdown-item'][text()[normalize-space() ='" + option + "']]"));
		BrowserUtils.clickWithWait(optionWE, 2);
	}

	public void verifyNumberOfDisplayedRows(String expectedTotalRows) {
		BrowserUtils.wait(0.5);
		System.out.println("Expected rows are: " + expectedTotalRows);
		System.out.println("Actual rows are: " + allRows.size());

		Assert.assertTrue(allRows.size() <= Integer.parseInt(expectedTotalRows));
		BrowserUtils.wait(1);
	}

	public void clickColumn(String column) {
		WebElement columnWE = Driver.getDriver().findElement(By.xpath("//*[text()='" + column + "']"));
		BrowserUtils.waitClickability(columnWE, 5);
		BrowserUtils.clickWithJSExe(columnWE);
		BrowserUtils.wait(1);
	}

	public void isColumnSorted(String column, String order) {
		List<String> actualValuesString = saveColumnOrder(column);
		List<String> expetedValuesString = new ArrayList<>();

		//create another list which is just a copy of curent list
		expetedValuesString = new ArrayList<>(actualValuesString);

		if (order.equalsIgnoreCase("ascending")) {
			//sort in ascending order
			Collections.sort(actualValuesString);
			Assert.assertEquals(expetedValuesString, actualValuesString);

			System.out.println("expected list: " + expetedValuesString);
			System.out.println("actual list: " + actualValuesString);
		} else if (order.equalsIgnoreCase("descending")) {
			//sort in descending order
			actualValuesString.sort(Collections.reverseOrder());
			Assert.assertEquals(expetedValuesString, actualValuesString);

			System.out.println("expected list: " + expetedValuesString);
			System.out.println("actual list: " + actualValuesString);
		}
	}

	public List<String> saveColumnOrder(String column) {
		List<WebElement> actualValuesWE = driver.findElements(By.cssSelector("td[data-column-label='" + column + "']"));
		List<String> actualValuesString = new ArrayList<>();

		//get text of each value and pass another list as a string
		for (WebElement element : actualValuesWE) {
			BrowserUtils.waitForVisibility(element, 1);
			actualValuesString.add(element.getText());
		}

		return actualValuesString;
	}

	public void clickRightTopButtons(String button) {
		BrowserUtils.wait(0.5);
		switch (button.toLowerCase()) {
			case "reset":
				BrowserUtils.clickWithWait(resetBtn, 7);
				break;

			case "grid settings":
				BrowserUtils.clickWithWait(gridSettingsBtn, 7);
				break;

			case "filters":
				BrowserUtils.clickWithWait(filtersBtn, 7);
				break;
		}

	}

	public void isDisplayedTotalPage() {
		BrowserUtils.waitForVisibility(totalPage, 5);
		Assert.assertTrue(totalPage.isDisplayed());
	}

	public void isDisplayedTotalRecordings() {
		BrowserUtils.waitForVisibility(totalRecording, 5);
		Assert.assertTrue(totalRecording.isDisplayed());
	}

	public void clickNextPreviousPageBtn(String direction) {
		switch (direction) {
			case "next":
				nextPageBtn.click();
				break;

			case "previous":
				previousPageBtn.click();
				break;
		}
	}

	public String getCurrentPageNumberWE() {
		BrowserUtils.waitClickability(currentPageNumberWE, 5);
		return currentPageNumberWE.getAttribute("value");
	}

	public void isEnabledNextPreviousBtn(String direction, String currentPageNumber) {
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
					Assert.assertFalse(nextPageBtn.isEnabled());
				}
				break;
		}

	}

	public void gotoLastPage() {
		String lastPage = totalPage.getText().split(" ")[1].trim();
		System.out.println("last page: " + lastPage);
		BrowserUtils.waitClickability(currentPageNumberWE, 5);
		currentPageNumberWE.clear();
		currentPageNumberWE.sendKeys(lastPage, Keys.ENTER);
	}

	public void gotoFirstPage() {
		BrowserUtils.waitClickability(currentPageNumberWE, 5);
		currentPageNumberWE.clear();
		currentPageNumberWE.sendKeys("1", Keys.ENTER);
	}

	public void downloadDataCSVorXLSX(String type) {
		BrowserUtils.clickWithWait(exportGrid, 5);
		WebElement typeWE = driver.findElement(By.cssSelector("a[title='" + type + "']"));
		BrowserUtils.clickWithWait(typeWE, 2);
	}

	public void downloadCSVorXLSXsuccess() {
		BrowserUtils.waitForVisibility(csvXlsxSuccess, 5);
		Assert.assertTrue(csvXlsxSuccess.isDisplayed());
	}

	public void clickThreeDotMenuButtons(String btn) {
		BrowserUtils.wait(1);
		By btnLocator = By.xpath("//li[@class='launcher-item']//*[@title='" + btn + "']");

		//we used list of web element, because sometimes hover action is
		//received by multiple elements at the same time, so we make sure
		//the last element (the one we look for) is displayed
		List<WebElement> btns = driver.findElements(btnLocator);
		BrowserUtils.clickManyTimes(btns.get(btns.size() - 1));
	}

	public void verifyVisibilityThreeDotMenuButtons(String btn) {
		BrowserUtils.wait(1);
		By btnLocator = By.cssSelector("li.launcher-item a[title='" + btn + "']");
		BrowserUtils.waitForVisibility(btnLocator, 2);

		//we used list of web element, because sometimes hover action is
		//received by multiple elements at the same time, so we make sure
		//the last element (the one we look for) is displayed
		List<WebElement> btns = driver.findElements(btnLocator);
		Assert.assertTrue(btns.get(btns.size() - 1).isDisplayed());
	}

	public void hoverOverThreeDots() {
		Actions actions = new Actions(driver);
		//it's strange but, web element receives the mouse event in second attempt
		//we try twice
//		actions.moveToElement(threeDots).pause(1000).click(threeDots).build().perform();

		try {
			for (int i = 0; i < 2; i++) {
				actions.moveToElement(threeDots5).pause(1000).build().perform();
				BrowserUtils.wait(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public List<String> getCarInfoFromRow() {
		List<String> allInfoString = new ArrayList<>();
		List<WebElement> allInfoWE = this.carInfoOfAnyRow;
		BrowserUtils.wait(1);

		//don't include the first and last values because it's not relevant
		for (int i = 1; i < allInfoWE.size() - 1; i++) {
			if (allInfoWE.get(i).getText().contains(",")) {
				allInfoString.add(allInfoWE.get(i).getText().replace(",", ""));
				continue;
			}
			allInfoString.add(allInfoWE.get(i).getText());
		}

		return allInfoString;
	}

	public List<String> getVehicleTableColumnOrder() {
		List<String> allColumnsString = new ArrayList<>();
		BrowserUtils.wait(0.5);

		for (int i = 1; i < allColumns.size() - 1; i++) {
			allColumnsString.add(allColumns.get(i).getText());
		}

		return allColumnsString;
	}

	public void clickYesDelete() {
		BrowserUtils.clickWithWait(yesDeleteBtn, 3);
	}

	public void verifyWarningMessageDriver(String userType, String messageText) {
		if (userType.equalsIgnoreCase("driver")) {
			String text = this.message.getText();
			Assert.assertTrue(message.isDisplayed());
			Assert.assertEquals(message.getText(), messageText);
		} else if (userType.equalsIgnoreCase("sales manager") || userType.equalsIgnoreCase("store manager")) {
			Assert.assertTrue(message.isDisplayed());
			Assert.assertEquals(message.getText(), messageText);
		}
	}

	public boolean searchForCar(String carInfo) {
		boolean isPresent = false;

		BrowserUtils.wait(2);
		List<String> allCarInfo = BrowserUtils.getElementsText(allRows);

		for (String s : allCarInfo) {
			if (s.contains(carInfo)) {
				isPresent = true;
				break;
			}
		}

		return isPresent;
	}

}
