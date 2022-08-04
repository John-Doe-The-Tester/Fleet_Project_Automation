package com.fleetApp.pages;

import com.fleetApp.utilities.BrowserUtils;
import com.fleetApp.utilities.Driver;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class CreateCarPage extends BasePage {
    @FindBy(xpath = "//a[@title='Create Car']")
    public WebElement createCarButton;

    @FindBy(xpath = "//td[@data-column-label=\"License Plate\"]")
    public WebElement row1;

    @FindBy(xpath = "//td[@data-column-label=\"Driver\"]")
    public WebElement row2;

    @FindBy(xpath = "//td[@data-column-label=\"Location\"]")
    public WebElement row3;

    @FindBy(xpath = "//td[@data-column-label=\"Chassis Number\"]")
    public WebElement row4;

    @FindBy(xpath = "//td[@data-column-label=\"Model Year\"]")
    public WebElement row5;

    @FindBy(xpath = "//td[@data-column-label=\"Last Odometer\"]")
    public WebElement row6;

    @FindBy(xpath = "//td[@data-column-label=\"Immatriculation Date\"]")
    public WebElement row7;

    @FindBy(xpath = "//td[@data-column-label=\"First Contract Date\"]")
    public WebElement row8;
    @FindBy(xpath = "//td[@data-column-label=\"Catalog Value (VAT Incl.)\"]")
    public WebElement row9;
    @FindBy(xpath = "//td[@data-column-label=\"Seats Number\"]")
    public WebElement row10;
    @FindBy(xpath = "//td[@data-column-label=\"Doors Number\"]")
    public WebElement row11;
    @FindBy(xpath = "//td[@data-column-label=\"Color\"]")
    public WebElement row12;
    @FindBy(xpath = "//td[@data-column-label=\"Transmission\"]")
    public WebElement row13;
    @FindBy(xpath = "//td[@data-column-label=\"Fuel Type\"]")
    public WebElement row14;
    @FindAll({
            @FindBy(xpath = "(//td[@data-column-label=\"CO2 Emissions\"])[1]"),
            @FindBy(xpath = "(//td[16])[1]"),
            @FindBy(xpath = "(//tbody/tr/td[16])[1]"),
            @FindBy(linkText = "22")
    })
    public WebElement row15;
    @FindBy(xpath = "//td[@data-column-label=\"Horsepower\"]")
    public WebElement row16;
    @FindBy(xpath = "//td[@data-column-label=\"Horsepower Taxation\"]")
    public WebElement row17;
    @FindBy(xpath = "//td[@data-column-label=\"Power (kW)\"]")
    public WebElement row18;

    @FindBy(css = "div[class='message-item message']")
    public WebElement savedTitleOnGeneralInformationPage;


    @FindBy(xpath = "(//button[@data-toggle='dropdown'])[1]")
    public WebElement dropdownWebtableShowElement;

    @FindBy(xpath = "//input[@data-name='field__license-plate']")
    public WebElement licence_plate_inputbox;

    @FindBy(xpath = "//input[@data-name='field__driver']")
    public WebElement driver_inputbox;

    @FindBy(xpath = "//input[@data-name='field__location']")
    public WebElement location_inputbox;

    @FindBy(xpath = "//input[@data-name='field__chassis-number']")
    public WebElement chassis_inputbox;

    @FindBy(xpath = "//input[@data-name='field__model-year']")
    public WebElement modelYear_inputbox;

    @FindBy(xpath = "//input[@data-name='field__last-odometer']")
    public WebElement lastOdometer_inputbox;

    @FindBy(xpath = "//input[@data-name='field__catalog-value']")
    public WebElement catalogValue_inputbox;

    @FindBy(xpath = "//input[@data-name='field__seats-number']")
    public WebElement seatNumber_inputbox;

    @FindBy(xpath = "//input[@data-name='field__doors-number']")
    public WebElement doorsNumber_inputbox;

    @FindBy(xpath = "//input[@data-name='field__color']")
    public WebElement color_inputbox;

    @FindBy(xpath = "//input[@data-name='field__c-o2-emissions']")
    public WebElement emissions_inputbox;

    @FindBy(xpath = "//input[@data-name='field__horsepower']")
    public WebElement horsePower_inputbox;

    @FindBy(xpath = "//input[@data-name='field__horsepower-taxation']")
    public WebElement horsePowerTaxation_inputbox;

    @FindBy(xpath = "//input[@data-name='field__power']")
    public WebElement power_inputbox;

    @FindBy(xpath = "//input[@placeholder='Choose a date']")
    public WebElement immatriculationDate_inputbox;

    @FindBy(className = "select2-chosen")
    public WebElement transmission;

    @FindBy(xpath = "(//*[@class='select2-chosen'])[2]")
    public WebElement fuelType;

    @FindBy(xpath = "(//input[@placeholder='Choose a date'])[2]")
    public WebElement firstContractDate_inputbox;

    @FindBy(xpath = "(//button[@type='submit'])[1]")
    public WebElement saveAndCloseButton;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div[1]/div[2]/div[3]/form/div[2]/div[2]/div/div[1]/div[2]/div/fieldset/div/div[2]/div[2]/div/div/label")
    public List<WebElement> myTags;

    public void newCarGenerator() throws InterruptedException {

        Faker faker = new Faker();
        Random random = new Random();

        waitUntilLoaderScreenDisappear();

        String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String date1 = month[random.nextInt(11) + 1] + " " + (random.nextInt(29) + 1) + ", " + (random.nextInt(40) + 1980);
        String date2 = month[random.nextInt(11) + 1] + " " + (random.nextInt(29) + 1) + ", " + (random.nextInt(40) + 1980);

        Driver.getDriver().navigate().refresh();
        licence_plate_inputbox.sendKeys(faker.bothify("##????").toUpperCase());
        myTags.get(random.nextInt(7)).click();
        driver_inputbox.sendKeys(faker.name().fullName());
        location_inputbox.sendKeys(faker.address().city().toUpperCase());
        chassis_inputbox.sendKeys(faker.bothify("######"));
        modelYear_inputbox.sendKeys(random.nextInt(40) + 1980 + "");
        lastOdometer_inputbox.sendKeys(random.nextInt(80 - 20) * 1000 + "");
        BrowserUtils.scrollToElement(immatriculationDate_inputbox);
        immatriculationDate_inputbox.sendKeys(date1);
        immatriculationDate_inputbox.sendKeys(Keys.ESCAPE);
        firstContractDate_inputbox.sendKeys(date2);
        firstContractDate_inputbox.sendKeys(Keys.ESCAPE);
        catalogValue_inputbox.sendKeys(random.nextInt(80 - 5) * 1000 + "");
        seatNumber_inputbox.sendKeys(random.nextInt(4) + 3 + "");
        doorsNumber_inputbox.sendKeys(random.nextInt(5) + 2 + "");
        color_inputbox.sendKeys(StringUtils.capitalize(faker.color().name()));
        transmission.click();
        Driver.getDriver().findElements(By.xpath("/html/body/div[8]/ul[2]/li")).get(random.nextInt(2)).click();
        Thread.sleep(1000);
        fuelType.click();
        Driver.getDriver().findElements(By.xpath("/html/body/div[9]/ul[2]/li")).get(random.nextInt(4)).click();
        emissions_inputbox.sendKeys(random.nextInt(9 - 1) * 0.1 + "");
        horsePower_inputbox.sendKeys(random.nextInt(180) + 120 + "");
        horsePowerTaxation_inputbox.sendKeys(random.nextInt(9 - 1) * 0.1 + "");
        power_inputbox.sendKeys(random.nextInt(300 - 20) + "");


    }

}
