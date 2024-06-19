package practice.TestNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic_Fileutility.ExcelUtility;

public class GetProductInfoTestInAmazon {

	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName,String productName) {
		
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://www.amazon.in");
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		//capture product Info
//		String x="//span[text()='"+productName+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span/span[2]/span[2]";
//		String price=driver.findElement(By.xpath(x)).getText();
//		System.out.println(price);
		
	 	String price = driver.findElement(By.xpath("//span[text()='"+productName+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span/span[2]/span[2]")).getText();
	    System.out.println(price);
	    driver.quit();
		
	}
     
		
		@DataProvider
		public Object[][] getData() throws Throwable{
			ExcelUtility elib =new ExcelUtility();
		    int rowcount=elib.getRowCount("Product");
			System.out.println(rowcount);
			Object[][] objArr= new Object[rowcount][2];
		
			for(int i=0;i<objArr.length;i++) {
			objArr[i][0]=elib.getDataFromExcel("product", (i+1), 0);
			objArr[i][1]=elib.getDataFromExcel("product", (i+1), 1);
			}
		
			return objArr;
		}
		
		
	}
	

