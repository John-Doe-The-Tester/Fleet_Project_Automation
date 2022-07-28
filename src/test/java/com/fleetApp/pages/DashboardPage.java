package com.fleetApp.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    //no need to explicitly write constructor, because it will use its parents constructor.

    //PageFactory is a class which  has a static initElements method that initialize all WebElements

    @FindBy(css = "[class='breadcrumb']")
    public WebElement breadCrumb;


}
