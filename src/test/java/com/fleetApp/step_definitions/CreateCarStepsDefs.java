package com.fleetApp.step_definitions;

import com.fleetApp.pages.CreateCarPage;
import com.fleetApp.pages.DashboardPage;
import com.fleetApp.utilities.BrowserUtils;
import com.fleetApp.utilities.CarGenerator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class CreateCarStepsDefs {

    CreateCarPage createCarPage = new CreateCarPage();
    DashboardPage dashboardPage= new DashboardPage();

    @And("the user navigates to {string} {string}")
    public void theUserNavigatesTo(String tab, String module) {
        dashboardPage.navigateToModule(tab, module);

    }

    @Then("the user clicks on Create Car button")
    public void theUserClicksOnCreateCarButton() {
        createCarPage.waitUntilLoaderScreenDisappear();
        BrowserUtils.sleep(5);
        BrowserUtils.clickWithJSExe(createCarPage.createCarButton);
    }

    @And("the user enters new Car information")
    public void theUserEntersNewCarInformation() throws InterruptedException {
        CarGenerator.newCarGenerator();

    }

    @Then("the user clicks on save changes button")
    public void theUserClicksOnSaveChangesButton() {

        createCarPage.saveAndCloseButton.click();


    }
}
