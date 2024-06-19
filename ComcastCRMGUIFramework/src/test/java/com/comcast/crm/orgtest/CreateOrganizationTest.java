package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic_Fileutility.ExcelUtility;
import com.comcast.crm.generic_Fileutility.FileUtility;
import com.comcast.crm.generic_Webdriverutility.JavaUtility;
import com.comcast.crm.generic_Webdriverutility.UtilityClassObject;
import com.comcast.crm.object_repository_utility.CreatingNewOrganizationPage;
import com.comcast.crm.object_repository_utility.HomePage;
import com.comcast.crm.object_repository_utility.LoginPage;
import com.comcast.crm.object_repository_utility.OrganizationInfoPage;
import com.comcast.crm.object_repository_utility.OrganizationPage;
import com.comcast.listenerutility.ListenerImplementationClass;

@Listeners(com.comcast.listenerutility.ListenerImplementationClass.class)
public class CreateOrganizationTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createOrganizationTest() throws Throwable, IOException {

		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		// To read data from excel file
		String ORGNAME = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();

		// Navigate to organization module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
        HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
 
		// click on create organization button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgImage().click();

		// Enter all the details and create new organizatin
		UtilityClassObject.getTest().log(Status.INFO, "create a new org");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(ORGNAME);
		UtilityClassObject.getTest().log(Status.INFO, ORGNAME+"==>create a new org");
		
		// verify Header msg Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if (actOrgName.contains(ORGNAME)) {
			System.out.println(ORGNAME +"\t"+"is verified==PASS");
		} else {
			System.out.println(ORGNAME +"\t"+"is not verified==FAIL");

		}
	}
	
	
	@Test(groups= "regressionTest")
	public void createOrganizationWithIndustryAndType() throws Throwable, IOException {
	
		        String ORGNAME = elib.getDataFromExcel("org", 4, 2)+jlib.getRandomNumber();
		        String industry = elib.getDataFromExcel("org", 4, 3);
		        String type = elib.getDataFromExcel("org", 4, 4);
		        
		        //Navigate to organization module
		        HomePage hp = new HomePage(driver);
				hp.getOrgLink().click();				
				
				//click on create organization button
				OrganizationPage op = new OrganizationPage(driver);
				op.getCreateOrgImage().click();		
				
			    //Enter all the madatory details and save it
				CreatingNewOrganizationPage cnop= new CreatingNewOrganizationPage(driver);
				cnop.createOrgWithIndAndType(ORGNAME, industry, type);
				 
				 //Verify Header ORGNAME info Expected result 
				String actindustry = driver.findElement(By.id("dtlview_Industry")).getText();
				if(actindustry.equals(industry)) {
					System.out.println(industry+"\t"+"verification successfull===>PASS");
				}else {
					System.out.println(industry+"\t"+"not verified ====>FAIL");
				}
				String actTYPE = driver.findElement(By.id("dtlview_Type")).getText();
				if(actTYPE.equals(type)) {
					System.out.println(type+" "+"verification successfull===>PASS");
				}else {
					System.out.println(type+" "+"not verified ====>FAIL");
				}
			}
	
	@Test(groups= "regressionTest")
	public void createOrganizationWithPhoneNumberTest() throws Throwable, IOException {
		
		
		        String ORGNAME = elib.getDataFromExcel("org", 7, 2)+jlib.getRandomNumber();
		        String phonenumber = elib.getDataFromExcel("org", 7, 3);
		       
		        //Navigate to organization module
		        HomePage hp = new HomePage(driver);
				hp.getOrgLink().click();
				
			    //click on create organization button
				OrganizationPage op = new OrganizationPage(driver);
				op.getCreateOrgImage().click();	
				
				//Enter all the mandatory details and save it
				CreatingNewOrganizationPage cnop= new CreatingNewOrganizationPage(driver);
				cnop.getOrgName().sendKeys(ORGNAME);
				cnop.getPhonenumtxtfield().sendKeys(phonenumber);
				cnop.getSaveButton().click();
				 
				 //Verify Header ORGNAME info Expected result 
				String actphonenum = driver.findElement(By.id("dtlview_Phone")).getText();
				if(actphonenum.equals(phonenumber)) {
					System.out.println(phonenumber+" is verified===>PASS");
				}else {
					System.out.println(phonenumber+" is not verified===>FAIL");

				}	
	}   
}
