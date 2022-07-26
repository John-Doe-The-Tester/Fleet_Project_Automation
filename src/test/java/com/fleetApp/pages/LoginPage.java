package com.fleetApp.pages;

import com.fleetApp.utilities.BrowserUtils;
import com.fleetApp.utilities.ConfigurationReader;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	//---locators----------------

	@FindBy(id = "prependedInput")
	private WebElement usernameField;

	@FindBy(id = "prependedInput2")
	private WebElement passwordField;

	@FindBy(id = "_submit")
	private WebElement loginBtn;

	@FindBy(css = ".alert.alert-error")
	private WebElement invalidCredenrialMessage;

	@FindBy(linkText = "Forgot your password?")
	private WebElement forgotYourPasswordLink;

	@FindBy(className = "custom-checkbox__icon")
	private WebElement rememberMeBtn;

	//---methods-----------------

	public void goLoginPage() {

		driver.get(ConfigurationReader.get("url"));
	}


	public void loginAsUserType(String userType){

		String password = "";
		String username = "";

		if (userType.toLowerCase().equals("driver")) {
			username = ConfigurationReader.get("driver_username");
			password = ConfigurationReader.get("driver_password");
		}
		else if (userType.toLowerCase().equals("sales manager")) {
			username = ConfigurationReader.get("sales_manager_username");
			password = ConfigurationReader.get("sales_manager_password");
		}
		else if (userType.toLowerCase().equals("store manager")) {
			username = ConfigurationReader.get("store_manager_username");
			password = ConfigurationReader.get("store_manager_password");
		}

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginBtn.click();
	}


	public void loginWithCredentials(String username, String password){

		try {
			usernameField.clear();
			passwordField.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginBtn.click();
	}

	public String getPageTitle(){
		return driver.getTitle();
	}

	//method overloading
	public String getWarningMessage(String field){
		String message = "";

		switch (field) {

			case "username":
				message = usernameField.getAttribute("validationMessage");
				System.out.println("Message is: " + message);
				break;

			case "password":
				message = passwordField.getAttribute("validationMessage");
				System.out.println("Message is: " + message);
			break;
		}

		return message;

	}

	//method overloading
	public String getWarningMessage(){
		BrowserUtils.waitForText(invalidCredenrialMessage,"Invalid");
		return invalidCredenrialMessage.getText();
	}

	public String isBulletSign(){
		return passwordField.getAttribute("type");
	}

	public void clickForgotYourPassword(){
		BrowserUtils.clickWithWait(forgotYourPasswordLink,3);
	}

	public void clickRememberMeBtn(){
		BrowserUtils.clickWithWait(rememberMeBtn,3);
	}

	public void enterUsernameOrPassword(String value, String field){
		switch (field) {
			case "username":
				BrowserUtils.sendKeysWithWait(usernameField,value,3);
				break;
			case "password":
				BrowserUtils.sendKeysWithWait(passwordField,value,3);
				break;
		}
	}

	public void hitEnterKey(){
		driver.switchTo().activeElement().sendKeys(Keys.ENTER);
	}



}
