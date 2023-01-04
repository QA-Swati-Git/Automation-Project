package qa.BDO_Web_Test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import qa.BDO_Web.Pages.BDO_HomePage;
import qa.BDO_Web.factory.BDO_Web_Factory;

public class HomePage {
	
	
	BDO_Web_Factory bwf;
	Page page;
	BDO_HomePage homepage;
	
	@BeforeTest
	
	public void setup()
	{
		bwf= new BDO_Web_Factory();
		page=bwf.initBrowser("chromium");
		homepage= new BDO_HomePage(page);		
	}
	
	
	
	@Test
	public void ValidateHomePageElements()
	{
		String Actualtitle= homepage.getHomePageTitle();
		String Actuallabel= homepage.GetHomePageComponenets();
		String Actualurl= homepage.getHomePageurl();
		
		Assert.assertEquals(Actualtitle, "BDO Online");
		Assert.assertEquals(Actuallabel, "Username");
		
		
		System.out.println("Page Title of application is:"+ Actualtitle);
		System.out.println("First label of the page is:"+ Actuallabel);
		System.out.println("Complete url of the Application:"+ Actualurl);	
	}
	
	@AfterTest

	public void terDown()
	{
		page.context().browser().close();
		
	}
}
