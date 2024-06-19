package practice.TestNG;
/**
 * test class for
 * @author Pankaj
 * 
 */

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.object_repository_utility.LoginPage;

public class SearchContactTest extends BaseClass{

	/**
	 * scenario:login()==>createcontact()==verify
	 */
	
	@Test
	public void searchContactTest() {
		/*step 1:login to app*/
		LoginPage lp =new LoginPage(driver);
		lp.loginToApp("url", "admin", "password");
		
		
		
	}
	
	
	
}
