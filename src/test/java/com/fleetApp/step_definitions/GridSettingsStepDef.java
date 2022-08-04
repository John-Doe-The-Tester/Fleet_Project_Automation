package com.fleetApp.step_definitions;

import com.fleetApp.pages.GridSettingsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class GridSettingsStepDef {

	private GridSettingsPage gridSettingsPage = new GridSettingsPage();

	@Then("Grid Settings menu shows up")
	public void grid_settings_menu_shows_up() {
		gridSettingsPage.isDisplayedGridSettings();
	}


	@Then("Grid Settings list options are as listed below")
	public void grid_settings_list_options_are_as_listed_below(List<String> expectedOptions) {
		List <String> actualOptions = gridSettingsPage.getAllGridSettingsOptions();
		System.out.println("actual options....: " + actualOptions);
		System.out.println("expected options..: " + expectedOptions);
		Assert.assertEquals(expectedOptions,actualOptions);
	}

	@When("The user types following column names into quick search field")
	public void the_user_types_following_column_names_into_quick_search_field(List <String> columns) {
		gridSettingsPage.findCollumn(columns);
	}

	@Then("The relevant column shows up")
	public void the_relevant_column_shows_up() {
		//there is nothing inside this method,
		//because the previous method also does assertion inside of it
		//This situation is kind of limitation of Cucumber
		//Sometimes scenarios can not be divided
	}

}
