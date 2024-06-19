package practice.TestNG;

import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;


public class InvoiceTestRetryAnalyzer {


	@Test(retryAnalyzer = com.comcast.listenerutility.RetryListenerImp.class)
	public void activatesim() {
		System.out.println("execute createInvoiceTest");
		//Assert.assertEquals("", "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
	
}
