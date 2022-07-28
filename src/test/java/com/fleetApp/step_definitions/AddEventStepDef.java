package com.fleetApp.step_definitions;

import com.fleetApp.pages.AddEventPage;
import com.fleetApp.pages.GeneralInfoPage;
import com.fleetApp.pages.HomePage;
import com.fleetApp.pages.VehicleAllCarsPage;
import com.fleetApp.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AddEventStepDef {

	private HomePage homePage = new HomePage();
	private VehicleAllCarsPage vehicleAllCarsPage = new VehicleAllCarsPage();
	private GeneralInfoPage generalInfoPage = new GeneralInfoPage();
	private AddEventPage addEventPage = new AddEventPage();

	@And("The user navigates to {string} - {string} module")
	public void theUserNavigatesToModule(String menu1, String menu2) {
		homePage.navigateToModule(menu1,menu2);
		homePage.waitUntilLoaderScreenDisappear();
	}


	@When("The user clicks on any row on the Vehicle - All Cars page")
	public void the_user_clicks_on_any_row_on_the_vehicle_all_cars_page() {
		vehicleAllCarsPage.clickAnyRow();
		vehicleAllCarsPage.waitUntilLoaderScreenDisappear();
	}

	@When("The user clicks on {string} button")
	public void the_user_clicks_on_button(String btnName) {
		generalInfoPage.clickAnyBtn(btnName);
		homePage.waitUntilLoaderScreenDisappear();
	}

	@Then("{string} page pops up")
	public void add_event_page_pops_up(String pageName) {
		Assert.assertEquals(pageName, addEventPage.getAddEventPageName());
	}

	@And("The user closes Add Event page")
	public void theUserClosesAddEventPage() {
		addEventPage.closeAddEventPage();
	}

	@Then("The user can click any of the colors")
	public void theUserCanClickAnyOfTheColors() {
		addEventPage.clickandVerifyColors();

	}

	@Then("The user can click {string} button")
	public void theUserCanClickButton(String btnName) {
		addEventPage.clickAnyBtn(btnName);
	}

	@Then("The user can click any of the repeat options below")
	public void theUserCanClickAnyOfTheRepeatOptionsBelow(List<String> repeatOptions) {
		addEventPage.clickRepeatOptions(repeatOptions);
	}

	@Then("The user can click any of the ending options below")
	public void theUserCanClickAnyOfTheEndingOptionsBelow(List<String> endingOptions) {
		addEventPage.clickEndingOptions(endingOptions);
	}
}
