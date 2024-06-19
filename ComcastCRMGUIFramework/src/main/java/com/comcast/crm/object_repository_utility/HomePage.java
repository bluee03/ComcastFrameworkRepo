package com.comcast.crm.object_repository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Organizations")
	private WebElement orgLink;// Rule 2:object creation

	@FindBy(linkText = "Contacts")
	private WebElement contLink;

	@FindBy(linkText = "Campaigns")
	private WebElement campaignsLink;

	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminIcon;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signoutBtn;

	public WebElement getAdminIcon() {
		return adminIcon;
	}

	public WebElement getSignoutBtn() {
		return signoutBtn;
	}

	// Generate getters
	 public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContLink() {
		return contLink;
	}

	public WebElement getCampaignsLink() {
		return campaignsLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}
	
	

	// Business library(if multiple actions are getting performed
	public void navigateToCampaignPage() {
		Actions act = new Actions(driver);
		act.moveToElement(moreLink).perform();
		 campaignsLink.click();
	}
	
	public void logout() {
		Actions act = new Actions(driver);
		act.moveToElement(adminIcon).perform();
		signoutBtn.click();
	}
	

}
