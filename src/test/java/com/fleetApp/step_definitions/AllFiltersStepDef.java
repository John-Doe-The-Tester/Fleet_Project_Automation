package com.fleetApp.step_definitions;

import com.fleetApp.pages.FiltersPage;
import com.fleetApp.utilities.BrowserUtils;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AllFiltersStepDef {

	private String filterName;
	private List<String> expectedFilterNames;
	private List<String> actualFilterNames;

	private FiltersPage filtersPage = new FiltersPage();

	@Then("Manage Filter button shows up")
	public void manage_filter_button_shows_up() {
		filtersPage.isManageFilterDisplayed();
	}

	@When("The user clicks on Manage Filter button")
	public void the_user_clicks_on_manage_filter_button() {
		filtersPage.clickManageFilter();
	}

	@When("The user clicks on the filters below")
	public void the_user_clicks_on_the_filters_below(List<String> filterNames) {
		//close and open manage filter
		filtersPage.clickManageFilter();
		filtersPage.clickManageFilter();

		this.expectedFilterNames = filterNames;
		filtersPage.clickFilter(filterNames);
	}

	@Then("Filters must be applied on the table")
	public void filters_must_be_applied_on_the_table() {
		this.actualFilterNames = filtersPage.getAppliedFilters();

		for (int i = 0; i < actualFilterNames.size(); i++) {
			Assert.assertTrue(actualFilterNames.get(i).contains(expectedFilterNames.get(i)));
		}
	}

	@When("The user search for the filters below by typing")
	public void the_user_search_for_the_filters_below_by_typing(List<String> filterNames) {
		this.expectedFilterNames = filterNames;
		this.actualFilterNames = filtersPage.typeFilterName(filterNames);
	}

	@Then("Filters must be found")
	public void filters_must_be_found() {
		Assert.assertEquals(expectedFilterNames, actualFilterNames);
	}

	@Then("All filters on the page should be removed")
	public void all_filters_on_the_page_should_be_removed() {
		BrowserUtils.wait(1);
		filtersPage.verifyFiltersRemoved();
	}

	@And("The user selects {string} filter")
	public void theUserSelectsFilter(String filterName) {
		this.filterName = filterName;
		filtersPage.clickFilter(filterName);
	}

	@When("The user clicks on methods")
	public void the_user_clicks_on_methods() {
		filtersPage.clickFilterMethods();
	}

	@Then("The user must see the methods below")
	public void the_user_must_see_the_methods_below(List<String> expectedMethods) {
		List<String> actualMethods = filtersPage.getFilterMethods();
		Assert.assertEquals(expectedMethods,actualMethods);
	}


	@When("The user selects {string} method")
	public void the_user_selects_method(String methodName) {
		filtersPage.selectFilterMethod(methodName);
	}

	@When("The user enters {string} and {string}")
	public void the_user_enters_and(String val1, String val2) {
		filtersPage.enterMethodValues(val1,val2);
	}

	@When("The user enters {string}")
	public void the_user_enters_and(String val) {
		filtersPage.enterMethodValues(val);
	}

	@Then("All the results in the table are between {string} and {string}")
	public void all_the_results_in_the_table_are_between_and(String val1, String val2) {
		BrowserUtils.wait(1);
		List<WebElement> allData = filtersPage.verifyFilteredTableData(this.filterName);
		for (WebElement dataWE : allData) {
			BrowserUtils.wait(0.1);
			double data = Double.parseDouble(dataWE.getText().replace(",",""));
			Assert.assertTrue(data >= Double.parseDouble(val1) && data <= Double.parseDouble(val2));
		}
	}

	@Then("All the results in the table are equals to {string}")
	public void all_the_results_in_the_table_are_between_and(String val) {
		BrowserUtils.wait(1);
		List<WebElement> allData = filtersPage.verifyFilteredTableData(this.filterName);
		for (WebElement dataWE : allData) {
			BrowserUtils.wait(0.1);
			double data = Double.parseDouble(dataWE.getText().replace(",",""));
			Assert.assertEquals(data, Double.parseDouble(val), 0.0);
		}
	}

	@Then("All the results in the table are more than {string}")
	public void all_the_results_in_the_table_are_more_than(String val) {
		BrowserUtils.wait(1);
		List<WebElement> allData = filtersPage.verifyFilteredTableData(this.filterName);
		for (WebElement dataWE : allData) {
			BrowserUtils.wait(0.1);
			double data = Double.parseDouble(dataWE.getText().replace(",",""));
			Assert.assertTrue(data >= Double.parseDouble(val));
		}
	}


	@Then("All the results in the table are less than {string}")
	public void all_the_results_in_the_table_are_less_than(String val) {
		BrowserUtils.wait(1);
		List<WebElement> allData = filtersPage.verifyFilteredTableData(this.filterName);
		for (WebElement dataWE : allData) {
			BrowserUtils.wait(0.1);
			double data = Double.parseDouble(dataWE.getText().replace(",",""));
			Assert.assertTrue(data <= Double.parseDouble(val));
		}
	}

	@Then("All the results in the table are empty")
	public void all_the_results_in_the_table_are_empty() {
		BrowserUtils.wait(1);
		List<WebElement> allData = filtersPage.verifyFilteredTableData(this.filterName);
		for (WebElement dataWE : allData) {
			BrowserUtils.wait(0.1);
			Assert.assertNull(dataWE.getText());
		}
	}

	@When("The user enters non-numeric {string}")
	public void the_user_enters_non_numeric(String value) {
		filtersPage.enterNonNumericValues(value);
	}

	@Then("Fields shouldn't accept non-numeric values")
	public void fields_shouldn_t_accept_non_numeric_values() {
		filtersPage.getValues();
	}

	@When("The user selects {string} method from dropdown")
	public void the_user_selects_method_from_dropdown(String option) {
		filtersPage.selectOptionFromDropdown(option);
	}


}
