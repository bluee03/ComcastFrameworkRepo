package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic_Fileutility.ExcelUtility;
import com.comcast.crm.generic_Fileutility.FileUtility;
import com.comcast.crm.generic_Webdriverutility.JavaUtility;
import com.comcast.crm.generic_Webdriverutility.WebDriverUtility;
import com.comcast.crm.object_repository_utility.CreatingNewOrganizationPage;
import com.comcast.crm.object_repository_utility.HomePage;
import com.comcast.crm.object_repository_utility.LoginPage;
import com.comcast.crm.object_repository_utility.OrganizationInfoPage;
import com.comcast.crm.object_repository_utility.OrganizationPage;

public class DeleteOrgTest {

	public static void main(String[] args) throws Throwable {

		// create object of generic utility
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib= new WebDriverUtility();

		// read data from properties file
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		// To read data from excel file
		String ORGNAME = elib.getDataFromExcel("org", 10, 2) + jlib.getRandomNumber();

		// test scripts
		WebDriver driver = null;

		if (BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

		// To login to application
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL,USERNAME, PASSWORD);

		// Navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organization button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgImage().click();

		// Enter all the details and create new organizatin
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(ORGNAME);
		
		// verify Header msg Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if (actOrgName.contains(ORGNAME)) {
			System.out.println(ORGNAME + "\t" + "is verified==PASS");
		} else {
			System.out.println(ORGNAME + "\t" + "is not verified==FAIL");

		}
		//Go back to organization page
		hp.getOrgLink().click();
		
		//search for organization
		op.getSearchfield().sendKeys(ORGNAME);
		wlib.select(op.getSearchDropdown(), "Organization Name");
		op.getSearchButton().click();
		//In dynamic Webtable select &delete org
		driver.findElement(By.xpath("//a[text()='"+ORGNAME+"']/../../td[8]/a[text()='del']")).click();
		wlib.switchToAlertAndAccept(driver); 
		System.out.println("Deleted successfully");
        // logout
		hp.logout();

		driver.quit();
		}

}
