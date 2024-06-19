package com.comcast.crm.object_repository_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {

	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	private WebElement orgName;
	public WebElement getOrgName() {
		return orgName;
	}
	
	@FindBy(name="button")
	private WebElement saveButton;
	public WebElement getSaveButton() {
		return saveButton;
	}
	
	@FindBy(name="industry")
	private WebElement indusDrop;
    public WebElement getIndusDrop() {
		return indusDrop;
	}

	@FindBy(name = "accounttype")
	private WebElement typeDrop;
    public WebElement getTypeDrop() {
		return typeDrop;
	}
    
    @FindBy(id = "phone")
    private WebElement phonenumtxtfield;
    public WebElement getPhonenumtxtfield() {
		return phonenumtxtfield;
	}

	//business method
	public void createOrg(String orgname) {
		orgName.sendKeys(orgname);
		saveButton.click();
	}
	
	public void createOrgWithIndAndType(String orgname,String industry,String type) {
		orgName.sendKeys(orgname);
		Select sel= new Select(indusDrop);
		sel.selectByVisibleText(industry);
		Select sel2= new Select(typeDrop);
		sel2.selectByVisibleText(type);
		saveButton.click();
	}
}
