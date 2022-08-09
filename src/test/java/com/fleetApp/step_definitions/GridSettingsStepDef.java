package com.fleetApp.step_definitions;

import com.fleetApp.pages.GridSettingsPage;
import com.fleetApp.pages.VehicleAllCarsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class GridSettingsStepDef {

	private GridSettingsPage gridSettingsPage = new GridSettingsPage();
	private VehicleAllCarsPage vehicleAllCarsPage = new VehicleAllCarsPage();

	@Then("Grid Settings menu shows up")
	public void grid_settings_menu_shows_up() {
		gridSettingsPage.isDisplayedGridSettings();
	}


	@Then("Grid Settings list options are as listed below")
	public void grid_settings_list_options_are_as_listed_below(List<String> expectedOptions) {
		List<String> actualOptions = gridSettingsPage.getAllGridSettingsOptions();
		System.out.println("actual options....: " + actualOptions);
		System.out.println("expected options..: " + expectedOptions);
		Assert.assertEquals(expectedOptions, actualOptions);
	}

	@When("The user types following column names into quick search field")
	public void the_user_types_following_column_names_into_quick_search_field(List<String> columns) {
		gridSettingsPage.findCollumn(columns);
	}

	@Then("The relevant column shows up in the grid settings")
	public void the_relevant_column_shows_up_in_the_grid_settings() {
		//there is nothing inside this method,
		//because the previous method also does assertion inside it
		//This situation is kind of limitation of Cucumber
		//Sometimes scenarios can not be divided
	}


	@When("The user clicks on the following columns from grid settings")
	public void the_user_clicks_on_the_following_columns_from_grid_settings(List<String> columns) {
		gridSettingsPage.deselectAllColumns();
		gridSettingsPage.clickColumn(columns);
	}

	@Then("The relevant column shows up in the fleet - vehicles table")
	public void the_relevant_column_shows_up_in_the_fleet_vehicles_table() {
		//there is nothing inside this method,
		//because the previous method also does assertion inside it
		//This situation is kind of limitation of Cucumber
		//Sometimes scenarios can not be divided
	}


	@When("The user can drag {string} column and drop to the index {int}")
	public void the_user_can_drag_column_and_drop_to_the_index(String column, Integer index) {
		gridSettingsPage.dragAndDrop(column,index);
	}

	@Then("{string} column is positioned at index {int}")
	public void column_is_positioned_at_index(String column, Integer index) {
		gridSettingsPage.verifyColumnPosition(column,index);
	}


	@Then("The user can see the same order in the fleet vehicle table as in the grid settings")
	public void the_user_can_see_the_same_order_in_the_fleet_vehicle_table_as_in_the_grid_settings() {
		String expectedOrder = gridSettingsPage.getGridSettingsColumnOrder().toString();
		String actualOrder = vehicleAllCarsPage.getVehicleTableColumnOrder().toString();

		expectedOrder = expectedOrder.toLowerCase().replace(",","").replace(" ", "");
		actualOrder = actualOrder.toLowerCase().replace(",","").replace(" ", "");

		System.out.println("expected order: " + expectedOrder);
		System.out.println("actual order..: " + actualOrder);

		Assert.assertEquals(expectedOrder, actualOrder);
	}


}