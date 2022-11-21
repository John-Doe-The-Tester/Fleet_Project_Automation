package com.fleetApp.step_definitions;

import com.fleetApp.pages.GeneralInfoPage;
import com.fleetApp.pages.VehicleTablePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class DeleteCarStepDef {

	private String carInfo;

	private VehicleTablePage vehicleAllCarsPage = new VehicleTablePage();
	private GeneralInfoPage generalInfoPage = new GeneralInfoPage();

	@Then("The user can see {string} button")
	public void the_user_can_see_button(String btn) {
		vehicleAllCarsPage.verifyVisibilityThreeDotMenuButtons(btn);
	}

	@Then("Delete Confirmation pop up should be displayed")
	public void delete_confirmation_pop_up_should_be_displayed() {
		vehicleAllCarsPage.verifyDeleteConfPopUpDisplayed();
	}

	@When("The user clicks on Yes-Delete btn")
	public void driver_click_on_yes_delete_btn() {
		vehicleAllCarsPage.clickYesDelete();
	}

	@Then("{string} gets {string} message")
	public void driver_gets_message(String userType, String messageText) {
		vehicleAllCarsPage.verifyWarningMessageDriver(userType, messageText);
	}

	@Then("The user saves {string} of the car")
	public void the_user_saves_of_the_car(String carInfo) {
		this.carInfo = generalInfoPage.getCarInfoAsString(carInfo);
	}

	@Then("The deleted car is also removed from the table")
	public void the_deleted_car_is_also_removed_from_the_table() {
		boolean isRemoved = vehicleAllCarsPage.searchForCar(this.carInfo);
		Assert.assertFalse(isRemoved);
	}


}
