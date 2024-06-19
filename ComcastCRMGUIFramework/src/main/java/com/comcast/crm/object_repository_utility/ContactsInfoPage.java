package com.comcast.crm.object_repository_utility;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsInfoPage {
	
	WebDriver driver;
	public ContactsInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement contactheadermsg;
    public WebElement getContactheadermsg() {
		return contactheadermsg;
    }
		
	@FindBy(id = "dtlview_Last Name")
	private WebElement contactInnermsg;
	public WebElement getcontactInnermsg() {
		return contactInnermsg;
	}
	}
	
	

