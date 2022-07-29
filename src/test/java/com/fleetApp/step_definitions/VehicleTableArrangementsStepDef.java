package com.fleetApp.step_definitions;

import com.fleetApp.pages.VehicleAllCarsPage;
import com.fleetApp.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class VehicleTableArrangementsStepDef {

	private VehicleAllCarsPage vehicleAllCarsPage = new VehicleAllCarsPage();

	@When("The user looks at the View Per Page")
	public void the_user_looks_at_the_view_per_page() {
		vehicleAllCarsPage.isDisplayedViewPerPage();
	}

	@Then("The default value is {string}")
	public void the_default_value_is(String expectedValue) {
		String actualValue = vehicleAllCarsPage.getValueViewPerPage();
		Assert.assertEquals(expectedValue,actualValue);
	}

	@When("The user clicks on the View Per Page")
	public void the_user_clicks_on_the_view_per_page() {
		vehicleAllCarsPage.clickViewPerPage();
	}

	@Then("The options are as listed below and clickable")
	public void the_options_are_as_listed_below_and_clickable(List<String> expectedOptions) {
		vehicleAllCarsPage.selectAllOptionsViewPerPage(expectedOptions);
	}

	//-----------------------------------

	@When("The user selects the {string} from View Per Page")
	public void the_user_selects_the_from_View_Per_Page(String option) {
		vehicleAllCarsPage.selectOptionViewPerPage(option);
	}

	@Then("The user should see {string} results on the page")
	public void the_user_should_see_results_on_the_page(String expectedTotalRowNumber) {
		vehicleAllCarsPage.verifyNumberOfDisplayedRows(expectedTotalRowNumber);
	}

	@When("The user clicks on {string} column in the table")
	public void the_user_clicks_on_column_in_the_table(String columnName) {
		vehicleAllCarsPage.clickColumn(columnName);
	}

	@When("The user clicks on {string} column again")
	public void the_user_clicks_on_column_again(String columnName) {
		vehicleAllCarsPage.clickColumn(columnName);
	}

	@Then("The {string} column is sorted in {string} order")
	public void the_column_is_sorted_in_order(String columnName, String order) {
		vehicleAllCarsPage.isColumnSorted(columnName,order);
	}

}
