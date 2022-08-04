package com.fleetApp.step_definitions;

import com.fleetApp.pages.GeneralInfoPage;
import com.fleetApp.pages.VehicleAllCarsPage;
import com.fleetApp.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class VehicleGeneralInfoStepDef {

	private GeneralInfoPage generalInfoPage = new GeneralInfoPage();
	private VehicleAllCarsPage vehicleAllCarsPage = new VehicleAllCarsPage();
	private List<String> allCarInfoFromRow;
	private List<String> allCarInfoFromGeneralInfo;
	
	
	@Then("The user is on the General Information page")
	public void the_user_is_on_the_general_information_page() {
		generalInfoPage.getPageTitle();
	}


	@When("The user hover over the three dots at the end of any row")
	public void the_user_hover_over_the_three_dots_at_the_end_of_any_row() {
		vehicleAllCarsPage.hoverOverThreeDots();
	}

	@When("The user clicks on the {string} icon")
	public void the_user_clicks_on_the_icon(String btn) {
		vehicleAllCarsPage.clickThreeDotMenuButtons(btn);
	}

	@Then("The user can see {string}, {string} and {string} buttons")
	public void the_user_can_see_and_buttons(String btn1, String btn2, String btn3) {
		generalInfoPage.isVisibleEditDeleteAddEvent(btn1, btn2, btn3);
	}

	@Then("The user can not see {string}, {string} and {string} buttons")
	public void the_user_can_not_see_and_buttons(String btn1, String btn2, String btn3) {
		generalInfoPage.isNotVisibleEditDeleteAddEvent(btn1, btn2, btn3);
	}

	@When("The user saves car info of any row randomly")
	public void the_user_saves_car_info_of_any_row_randomly() {
		allCarInfoFromRow = vehicleAllCarsPage.getCarInfoFromRow();
		vehicleAllCarsPage.waitUntilLoaderScreenDisappear();
	}

	@When("The user clicks on the same row on the Vehicle - All Cars page")
	public void the_user_clicks_on_thw_same_row_on_the_vehicle_all_cars_page() {
		vehicleAllCarsPage.clickAnyRow();
		vehicleAllCarsPage.waitUntilLoaderScreenDisappear();
	}

	@Then("All the car info must be the same as in the General Info page")
	public void all_the_car_info_must_be_the_same_as_in_the_General_Info_page() {
		allCarInfoFromGeneralInfo = generalInfoPage.getCarInfoFromGeneralInfo();

		System.out.println("car info from row.........: " + allCarInfoFromRow);
		System.out.println("car info from general info: " + allCarInfoFromGeneralInfo);

		Assert.assertEquals(allCarInfoFromGeneralInfo, allCarInfoFromRow);
	}


}
