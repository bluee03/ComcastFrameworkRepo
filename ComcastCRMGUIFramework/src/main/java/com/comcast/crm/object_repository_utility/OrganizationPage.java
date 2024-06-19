package com.comcast.crm.object_repository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	
	WebDriver driver;
	public OrganizationPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}
      
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createOrgImage;
	
	@FindBy(name = "search_text")
	private WebElement searchfield;
	
	@FindBy(id = "bas_searchfield")
	private WebElement searchDropdown;
	
	@FindBy(name = "submit")
	private WebElement searchButton;

	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getSearchfield() {
		return searchfield;
	}

	public WebElement getSearchDropdown() {
		return searchDropdown;
	}

	public WebElement getCreateOrgImage() {
		return createOrgImage;
	}
	
	
        
    	
}
