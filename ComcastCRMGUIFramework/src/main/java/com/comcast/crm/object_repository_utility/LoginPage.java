package com.comcast.crm.object_repository_utility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic_Webdriverutility.WebDriverUtility;
import com.mysql.jdbc.Driver;
/**
 * @author Pankaj
 * 
 * Contains Login Page Elements & business lib like login()
 * 
 */
public class LoginPage extends WebDriverUtility {
	// Rule-1 Create separate java class
	// Rule-2 object creation
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		// PageFactory.initElements(driver, LoginPage.class);
		// instaed of this we will use
		PageFactory.initElements(driver, this);// this keyword refers to currentclass object
	}

	@FindBy(name = "user_name")
	private WebElement usernameText;

	@FindBy(name = "user_password")
	private WebElement passwordText;

	@FindBy(id = "submitButton")
	private WebElement loginButton;
	// Rule 3: object initialization

	// Rule 4: Object Encapsulation
	public WebElement getUsernameText() {
		return usernameText;
	}

	public WebElement getPasswordText() {
		return passwordText;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
	/**
	 * login to app based on username ,password,url arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	public void loginToApp(String url, String username, String password) {
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();

		usernameText.sendKeys(username);
		passwordText.sendKeys(password);
		loginButton.click();

	}

}
