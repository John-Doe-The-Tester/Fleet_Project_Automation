package com.fleetApp.step_definitions;

import com.fleetApp.pages.VehicleAllCarsPage;
import com.fleetApp.utilities.BrowserUtils;
import com.fleetApp.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

public class VehicleTableViewStepDef {

	private VehicleAllCarsPage vehicleAllCarsPage = new VehicleAllCarsPage();
	private String currentPageNumber;

	@Then("The user can see number of total pages at the top")
	public void theUserCanSeeNumberOfTotalPagesAtTheTop() {
		vehicleAllCarsPage.isDisplayedTotalPage();
	}


	@When("The user clicks on {string} page button")
	public void the_user_clicks_on_page_button(String direction) {
		currentPageNumber = vehicleAllCarsPage.getCurrentPageNumberWE();
		vehicleAllCarsPage.clickNextPreviousPageBtn(direction);
	}

	@Then("The user goes to next page")
	public void the_user_goes_to_next_page() {
		String actualPageNumber = vehicleAllCarsPage.getCurrentPageNumberWE();
		System.out.println("initialPage: " + currentPageNumber);
		System.out.println("actualPage: " + actualPageNumber);
		Assert.assertTrue(Integer.valueOf(currentPageNumber) + 1 == Integer.valueOf(actualPageNumber));
	}

	@Then("The user goes to previous page")
	public void the_user_goes_to_previous_page() {
		String actualPageNumber = vehicleAllCarsPage.getCurrentPageNumberWE();
		System.out.println("initialPage: " + currentPageNumber);
		System.out.println("actualPage: " + actualPageNumber);
		Assert.assertTrue(Integer.valueOf(currentPageNumber) - 1 == Integer.valueOf(actualPageNumber));
	}

	@When("The user is on the {string} page of vehicles table")
	public void the_user_is_on_the_page_of_vehicles_table(String currentPage) {
		currentPageNumber = vehicleAllCarsPage.getCurrentPageNumberWE();
		System.out.println("initialPage: " + currentPageNumber);
	}

	@When("The user is on the first page of vehicles table")
	public void the_user_is_on_the_first_page_of_vehicles_table() {
		currentPageNumber = vehicleAllCarsPage.getCurrentPageNumberWE();
		System.out.println("current page: " + currentPageNumber);
	}

	@When("The user is on the last page of vehicles table")
	public void the_user_is_on_the_last_page_of_vehicles_table() {
		vehicleAllCarsPage.gotoLastPage();
		currentPageNumber = vehicleAllCarsPage.getCurrentPageNumberWE();
		System.out.println("current page: " + currentPageNumber);
	}

	@Then("The user can not click on previous page button")
	public void the_user_can_not_click_on_previous_page_button() {
		vehicleAllCarsPage.clickNextPreviousPageBtn("previous");
		Assert.assertEquals(vehicleAllCarsPage.getCurrentPageNumberWE(), currentPageNumber);
	}

	@Then("The user can not click on next page button")
	public void the_user_can_not_click_on_next_page_button() {
		vehicleAllCarsPage.clickNextPreviousPageBtn("next");
		Assert.assertEquals(vehicleAllCarsPage.getCurrentPageNumberWE(), currentPageNumber);
	}

	@Then("The user can not click on {string} page button")
	public void the_user_can_not_click_on_page_button(String direction) {
		vehicleAllCarsPage.clickNextPreviousPageBtn(direction);
		currentPageNumber = vehicleAllCarsPage.getCurrentPageNumberWE();

//		vehicleAllCarsPage.isEnabledNextPreviousBtn(direction, currentPageNumber);
	}


	@Then("The user can see number of total recordings at the top")
	public void theUserCanSeeNumberOfTotalRecordingsAtTheTop() {
		vehicleAllCarsPage.isDisplayedTotalRecordings();
	}

	@When("The user downloads the data as a {string} file")
	public void the_user_downloads_the_data_as_a_file(String type) {
		vehicleAllCarsPage.downloadDataCSVorXLSX(type);
	}

	@Then("The user gets a success message")
	public void the_user_gets_a_success_message() {
		vehicleAllCarsPage.downloadCSVorXLSXsuccess();
	}

}
