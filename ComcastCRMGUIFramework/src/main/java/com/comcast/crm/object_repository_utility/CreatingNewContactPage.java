 package com.comcast.crm.object_repository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	
	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "lastname")
	private WebElement lastNameTxtfield;
	
	@FindBy(name = "button")
	private WebElement savebutton;
	
	@FindBy(name = "support_start_date")
	private WebElement startDate;
	
	@FindBy(name = "support_end_date")
	private WebElement endDate;
	

	public WebElement getStartDate() {
		return startDate;
	}

	public WebElement getEndDate() {
		return endDate;
	}

	public WebElement getLastNameTxtfield() {
		return lastNameTxtfield;
	}

	public WebElement getSavebutton() {
		return savebutton;
	}
	
	public void createContactWithSupportDate(String lastName,String startdate,String enddate ) {
		lastNameTxtfield.sendKeys(lastName);
		startDate.clear();
		startDate.sendKeys(startdate);
		endDate.clear();
		endDate.sendKeys(enddate);
		savebutton.click();
	}
	
	
}
