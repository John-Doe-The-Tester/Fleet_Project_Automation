package com.fleetApp.pages;

import com.fleetApp.utilities.BrowserUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FiltersPage extends BasePage {

	@FindBy(xpath = "//a[text()='Manage filters']")
	private WebElement manageFilterBtn;

	@FindBy(css = "div.btn.filter-criteria-selector.oro-drop-opener.oro-dropdown-toggle.filter-default-value")
	private List<WebElement> allFiltersOnThePage;

	@FindBy(xpath = "//div[@class='ui-multiselect-menu ui-corner-all select-filter-widget dropdown-menu']//*[@type='search']")
	private WebElement manageFilterSearchBox;

	@FindBy(css = "li[style='']")
	private WebElement founfFilterNameSearched;

	@FindBy(css = "button.btn.dropdown-toggle")
	private WebElement filterMethodsBtn;

	@FindBy(css = "div[class='filter-value'] input[name='value']")
	private WebElement startField;

	@FindBy(css = "div[class='filter-value'] input[name='value_end']")
	private WebElement endField;

	@FindBy(xpath = "//div[@class='filter-value']//div[contains(@class,'filter')]")
	private List<WebElement> startAndEndFields;

	@FindBy(css = "button.btn.btn-primary.filter-update")
	private WebElement updateBtn;

	@FindBy(css = "ul.select2-choices")
	private WebElement optionsDropdownFilter;

	@FindBy(css = "div.btn-group.btn-block.open li")
	private List<WebElement> allFilterMethods;


	public void isManageFilterDisplayed() {
		BrowserUtils.waitForVisibility(manageFilterBtn, 3);
		Assert.assertTrue(manageFilterBtn.isDisplayed());
	}

	public void clickManageFilter() {
		BrowserUtils.clickWithWait(manageFilterBtn, 3);
	}

	public void clickFilter(List<String> filterName) {
		BrowserUtils.wait(1);

		for (String s : filterName) {
			By filterLocator = By.cssSelector("li>label[title='" + s + "']");
			BrowserUtils.clickWithWait(filterLocator, 3);
		}
	}

	public void clickFilter(String filterName) {
		BrowserUtils.wait(1);
		By filterLocator = By.cssSelector("li>label[title='" + filterName + "']");
		BrowserUtils.clickWithWait(filterLocator, 3);

		//list returns all filters, since we have only one filter applied
		//we used .get(0)
		BrowserUtils.clickWithWait(allFiltersOnThePage.get(0),3);
	}

	public void clickFilterMethods(){
		BrowserUtils.clickWithWait(filterMethodsBtn,2);
	}

	public List<String> getAppliedFilters() {
		BrowserUtils.wait(1);
		List<String> allFiltersApplied = BrowserUtils.getElementsText(allFiltersOnThePage);
		return allFiltersApplied;
	}

	public void verifyFiltersRemoved() {
		BrowserUtils.wait(1);
		for (WebElement filter : allFiltersOnThePage) {
			Assert.assertFalse(filter.isDisplayed());
		}
	}

	public List<String> typeFilterName(List<String> expectedFilterNames) {
		List<String> actualFilterNames = new ArrayList<>();

		for (String filterName : expectedFilterNames) {
			BrowserUtils.sendKeysWithWait(manageFilterSearchBox, filterName, 2);
			BrowserUtils.wait(1);
			actualFilterNames.add(founfFilterNameSearched.getText());
			manageFilterSearchBox.clear();
		}
		return actualFilterNames;
	}

	public List<String> getFilterMethods(){
		List<String> actualMethods = BrowserUtils.getElementsText(allFilterMethods);
		return actualMethods;
	}

	public void selectFilterMethod(String methodName){
		int size = allFilterMethods.size();

		for (WebElement allFilterMethod : allFilterMethods) {
			String actualMethodName = allFilterMethod.getText();
			if (actualMethodName.equalsIgnoreCase(methodName)) {
				BrowserUtils.clickWithWait(allFilterMethod, 2);
				break;
			}
		}
	}

	public void enterMethodValues(String startVal, String endVal){
		BrowserUtils.wait(1);
		BrowserUtils.sendKeysWithWait(startField,startVal,2);
		BrowserUtils.sendKeysWithWait(endField,endVal,2);
		BrowserUtils.clickWithWait(updateBtn,2);
	}

	public void enterMethodValues(String val){
		BrowserUtils.wait(1);
		BrowserUtils.sendKeysWithWait(startField,val,2);
		BrowserUtils.clickWithWait(updateBtn,2);
	}

	public List<WebElement>  verifyFilteredTableData(String filterName){
		By dataLocator = By.cssSelector("tr[class='grid-row'] [data-column-label='"+filterName+"']");
		List<WebElement> allData = driver.findElements(dataLocator);
		return allData;
	}

	public void enterNonNumericValues(String val){
		BrowserUtils.wait(1);
		//if we have two fields (start and end)
		if (endField.isDisplayed()) {
			BrowserUtils.sendKeysWithWait(startField,val,2);
			BrowserUtils.sendKeysWithWait(endField,val,2);
		} else {
			BrowserUtils.sendKeysWithWait(startField,val,2);
		}
	}

	public void getValues(){
		BrowserUtils.wait(1);
		if (endField.isDisplayed()) {
			String value1 = startField.getAttribute("value");
			String value2 = endField.getAttribute("value");
			Assert.assertEquals("", value1);
			Assert.assertEquals("", value2);
		} else {
			String value1 = startField.getAttribute("value");
			Assert.assertEquals("", value1);
		}
	}

	public void selectOptionFromDropdown(String option){
		BrowserUtils.clickWithWait(optionsDropdownFilter,2);
		By optionLocator = By.xpath("//div[text()='" + option + "']");
		BrowserUtils.clickWithWait(optionLocator,3);
	}

}
