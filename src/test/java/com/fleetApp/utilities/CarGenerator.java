package com.fleetApp.utilities;

import com.fleetApp.pages.CreateCarPage;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Random;

public class CarGenerator {


    public static void newCarGenerator() {

        Faker faker = new Faker();
        Random random = new Random();
        CreateCarPage createCarPage = new CreateCarPage();

        createCarPage.waitUntilLoaderScreenDisappear();

        String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String date1 = month[random.nextInt(11) + 1] + " " + (random.nextInt(29) + 1) + ", " + (random.nextInt(40) + 1980);
        String date2 = month[random.nextInt(11) + 1] + " " + (random.nextInt(29) + 1) + ", " + (random.nextInt(40) + 1980);

        Driver.getDriver().navigate().refresh();
        createCarPage.licence_plate_inputbox.sendKeys(faker.bothify("##????").toUpperCase());
        createCarPage.myTags.get(random.nextInt(7)).click();
        createCarPage.driver_inputbox.sendKeys(faker.name().fullName());
        createCarPage.location_inputbox.sendKeys(faker.address().city().toUpperCase());
        createCarPage.chassis_inputbox.sendKeys(faker.bothify("######"));
        createCarPage.modelYear_inputbox.sendKeys(random.nextInt(40) + 1980 + "");
        createCarPage.lastOdometer_inputbox.sendKeys(random.nextInt(80 - 20) * 1000 + "");
        BrowserUtils.scrollToElement(createCarPage.immatriculationDate_inputbox);
        createCarPage.immatriculationDate_inputbox.sendKeys(date1);
        createCarPage.immatriculationDate_inputbox.sendKeys(Keys.ESCAPE);
        createCarPage.firstContractDate_inputbox.sendKeys(date2);
        createCarPage.firstContractDate_inputbox.sendKeys(Keys.ESCAPE);
        createCarPage.catalogValue_inputbox.sendKeys(random.nextInt(80 - 5) * 1000 + "");
        createCarPage.seatNumber_inputbox.sendKeys(random.nextInt(4) + 3 + "");
        createCarPage.doorsNumber_inputbox.sendKeys(random.nextInt(5) + 2 + "");
        createCarPage.color_inputbox.sendKeys(StringUtils.capitalize(faker.color().name()));
        createCarPage.transmission.click();
        Driver.getDriver().findElements(By.xpath("/html/body/div[8]/ul[2]/li")).get(random.nextInt(2)).click();
        BrowserUtils.sleep(10);
        createCarPage.fuelType.click();
        Driver.getDriver().findElements(By.xpath("/html/body/div[9]/ul[2]/li")).get(random.nextInt(4)).click();
        createCarPage.emissions_inputbox.sendKeys(random.nextInt(9 - 1) * 0.1 + "");
        createCarPage.horsePower_inputbox.sendKeys(random.nextInt(180) + 120 + "");
        createCarPage.horsePowerTaxation_inputbox.sendKeys(random.nextInt(9 - 1) * 0.1 + "");
        createCarPage.power_inputbox.sendKeys(random.nextInt(300 - 20) + "");


    }
}
