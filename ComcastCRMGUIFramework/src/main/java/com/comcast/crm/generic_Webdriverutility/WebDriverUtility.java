package com.comcast.crm.generic_Webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
    //implicit wait
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
	
	//explicit wait
	public void waitForElementPresent(WebDriver driver,WebElement element) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		}
	
	//switch driver control to other window
	public void switchToTabOnUrl(WebDriver driver,String partialURL) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it= set.iterator();
		while(it.hasNext()) {
			String windowid = it.next();
			driver.switchTo().window(windowid);
			String Url = driver.getCurrentUrl();
			  if(Url.contains(partialURL)) {
				break;                }
			  }
		}
//	for(String windowid:sets) {
//	driver.switchTo().window(windowid);
//	String curl = driver.getCurrentUrl();
//	if(curl.contains("module=Contacts")) {
//		break;
//	}
//}
	public void switchToTabOnTitle(WebDriver driver,String partialTitle) {
		 Set<String> sets = driver.getWindowHandles();
		 Iterator<String> it = sets.iterator();
		 while(it.hasNext()) {
			 String windowid = it.next();
			 driver.switchTo().window(windowid);
			 String actUrl = driver.getTitle();
			 if(actUrl.contains(partialTitle)) {
				 break;
			 }
		 }
	}
	//switch to frame
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver,String nameId) {
		driver.switchTo().frame(nameId);
	}
	public void switchToFrame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}
	
	//switch driver control to alert
	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
		}
	public void switchToAlertAndCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	//handle dropdown
	public void select(WebElement element,String text) {
		Select sel= new Select(element);
		sel.selectByVisibleText(text);
	}
	public void select(WebElement element,int index) {
		Select sel= new Select(element);
		sel.selectByIndex(index);
	}
	
	//mouse handling
	public void moveMouseOnElement(WebDriver driver,WebElement element) {
		Actions act= new Actions(driver);
		act.moveToElement(element).perform();
	}
	public void doubleClick(WebDriver driver,WebElement element) {
	 
		Actions act= new Actions(driver);
		act.doubleClick();
	}
	
	
	
}
