 package com.comcast.crm.contacttest;  

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic_Fileutility.ExcelUtility;
import com.comcast.crm.generic_Fileutility.FileUtility;
import com.comcast.crm.generic_Webdriverutility.JavaUtility;
import com.comcast.crm.generic_Webdriverutility.WebDriverUtility;

public class CreateContactsWithOrgTest {    
	

	public static void main(String[] args)  throws Throwable {
		
		FileUtility flib= new FileUtility();
		ExcelUtility elib= new ExcelUtility();
		JavaUtility jlib= new JavaUtility();
		WebDriverUtility wlib= new WebDriverUtility();
		//  read data from properties file
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

	

		// To read data from excel file
		String CONLASTNAME =elib.getDataFromExcel("cont", 7, 3);
		String ORGNAME= elib.getDataFromExcel("cont", 7, 2)+jlib.getRandomNumber();
        // Step 5: test scripts
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
		driver.get(URL);
		wlib.waitForPageToLoad(driver);
        driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step 7:Navigate to organization module
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		driver.findElement(By.name("button")).click();

		// verify Header msg Expected Result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(ORGNAME)) {
			System.out.println(ORGNAME+"is created successfully===>PASS");
		} else {
			System.out.println(ORGNAME+"is not created====>FAIL");
		}
		
		//navigate to contact module

		driver.findElement(By.linkText("Contacts")).click();
		// click on create contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Enter all the mandatory details and save it
		driver.findElement(By.name("lastname")).sendKeys(CONLASTNAME);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// switch driver control to child
		wlib.switchToTabOnUrl(driver, "module=Accounts");
	
		driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + ORGNAME + "']")).click();
		
	
		// switch to parent window
		wlib.switchToTabOnUrl(driver, "module=Contacts");
         

		driver.findElement(By.name("button")).click();
		
		// verify Header msg Expected Result
		headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		   if (headerInfo.contains(CONLASTNAME)) {
			System.out.println("Contact name="+CONLASTNAME+"is verified===>PASS");
		} else {
			System.out.println("Contact name="+CONLASTNAME+"is not verified====>FAIL");
		}
		// verify inner orgname
		String ActualOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		
		if (ActualOrgName.contains(ORGNAME)) {
			System.out.println("Organization name="+ORGNAME+"is verified===>PASS");
		} else {
			System.out.println("Organization name="+ORGNAME+"is not verified====>FAIL");
		}
		
		driver.quit();
		}
    }
