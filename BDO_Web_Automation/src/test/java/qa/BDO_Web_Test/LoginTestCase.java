package qa.BDO_Web_Test;

import java.io.UnsupportedEncodingException;


import org.testng.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;


import qa.BDO_Web.Pages.BDO_Login;
import qa.BDO_Web.factory.BDO_Web_Factory;

public class LoginTestCase {
	
	
	BDO_Web_Factory bwf;
	Page page;
	BDO_Login login;
	
	@BeforeTest
	
	public void setup() throws UnsupportedEncodingException 
	{
		bwf= new BDO_Web_Factory();
		page=bwf.initBrowser("chromium");
		login= new BDO_Login(page);	
		
	}
	

@Test(priority = 1)
	public void LoginAttemptExhausted() throws UnsupportedEncodingException 
	{
		
		login.logintoApp("BBNDB010", "Password1");
		System.out.println("\nTC:05 The  login attempts exhausted:\n");
		login.LoginwhenAttemptsExhausted();
		
		login.APITesttoGenerateAccessToken();
		login.APITesttoUnlockUser();
		
	}


@Test(dataProvider = "credentials")
public void VerifyLoginCredentials(String Scenario, String Username, String Password) throws UnsupportedEncodingException{
	
login.logintoApp(Username, Password);		
if (Scenario.equals("EmptyUsername"))
{
	System.out.println("\nTC:001  No UserName and Valid Password:\n");
	
	String ErroronHeader = login.Loginwithemptyusername();

	Assert.assertEquals(ErroronHeader, "Enter username");	

}

else if(Scenario.equals("PasswordEmpty"))
{
	
	System.out.println("\nTC:002 Valid UserName and no Password:\n");
	
	String ErroronHeader1 = login.Loginwithemptypassword();
	
	Assert.assertEquals(ErroronHeader1, "Enter password");
}

else if(Scenario.equals("BothCredentialEmpty"))
{
	System.out.println("\nTC:003 No Username and No Password:\n");
	String ErroronHeader2 = login.Loginwithbothempty();
	
	Assert.assertEquals(ErroronHeader2, "Enter username and Enter password");
}

else if(Scenario.equals("InvalidUsernameandPassword"))
{
	System.out.println("\nTC:004 The expected error for Invalid Username and Password:\n");
	String ErroronHeader3 = login.LoginwithInvalidUsernameandPassword();
	
	Assert.assertEquals(ErroronHeader3, "Incorrect username or password.  Please try again.");
}

else {

	//System.out.println("\nTC:06 The expected message on successful login attempts:\n");
	//String ErroronHeader9 = login.ValidateLoginSuccess();
	
	System.out.println("User will continue to try login");
}
}


	@DataProvider(name="credentials")
	public Object[][] getData()
	{
		return new Object[][]{
				
				{"EmptyUsername","","Password@0007"},
				{"PasswordEmpty","BBNDB001",""},
				{"BothCredentialEmpty", "" ,""},
				{"InvalidUsernameandPassword","BBNDB0010","Password@0007"},
				{"InvalidPassword","BBNDB001","Test@20"},
				//{"AttemptExhausted","BBNDB001","Password@0007"},
				//{"ValidCredentials","BBNDB001","Test@2022"}
				
				};
	}


@Test (priority = 3)	
public void VaildateSuccessfullogin() 

{
		
	System.out.println("\nTC:06 The expected Username on successful login attempt:\n");
	login.logintoApp("BBNDB001","Password@0007");
	login.EnterOTP();
	String ErroronHeader9 = login.ValidateLoginSuccess();
	System.out.println("The username displayed:" + ErroronHeader9);
}

/*
@Test
public void ValidateLoginafterSessionOut()
{
	String SessionoutError ="Please log in again. For your security, we have logged you out due to inactivity or due to login from another device.";
	
	System.out.println("\nTC:007 The expected error when user login after session out:\n");
    
	//login("BBNDB001", "Test@2022");
	//page.setDefaultTimeout(500000);
	page.waitForTimeout(500000);
	page.locator("input#username").fill("BBNDB007");
	page.locator("input#password").fill("Password@1000");
	page.locator("input[name=login]").click();
	
	String ErroronHeader10 = login.Loginaftersessionisexpired();
	
	Assert.assertEquals(ErroronHeader10, SessionoutError);
}
*/
	@AfterTest

	public void tearDown()
	{
		page.context().browser().close();
		
	}
}
