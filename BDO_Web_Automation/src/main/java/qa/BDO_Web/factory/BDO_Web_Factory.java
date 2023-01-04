package qa.BDO_Web.factory;

import java.util.HashMap;
import java.util.Map;


import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BDO_Web_Factory {
	
	Playwright playwright;
	public APIRequestContext apiRequestContext;
	Browser browser;
	BrowserContext brContext;
	Page page;
	
	
	public Page initBrowser(String browserName)
	{
		System.out.println("The browser name is:"+ browserName);
		playwright= Playwright.create();
		
		
		switch(browserName.toLowerCase())
		{
		case "chrome":
			browser= playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			break;
			
		case "chromium":
			browser= playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
			
		case "firefox":
			browser= playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
				
		case "safari":
			browser= playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
			
			default:
				System.out.println("Please pass right bowser name");
				break;
				
		} 
		
		brContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(1750,1080));
		page= brContext.newPage();
		page.navigate("https://app.tst.bdo.live.backbaseservices.com/retail-app/redirect");
		

		Map<String,String> headers= new HashMap<>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		headers.put("Authorization", "Basic WFBPQXY3aVk3TWFjUXhvTXFxSXFrVWZTazhjU2ZDeWg6Mld0QTk1cDNwbVI2eEJCdA==");
		headers.put("User-Agent", "PostmanRuntime/7.29.2");
		headers.put("Accept", "*/*");
		headers.put("Accept-Encoding", "gzip, deflate, br");
		headers.put("Connection", "keep-alive");
		headers.put("Cookie", "AWSALB=4GVYluXDVweikA+1TASjKsJ91C7LZoOaXzVjPIOYFdFrUxZ/lbu87YdFc9nE+av07XbcxdEZwVRJYHt0uo9XDqHvh6gSsWct4p8xdxyFbNiBL7+53WdZlvkYz9aQ; AWSALBCORS=4GVYluXDVweikA+1TASjKsJ91C7LZoOaXzVjPIOYFdFrUxZ/lbu87YdFc9nE+av07XbcxdEZwVRJYHt0uo9XDqHvh6gSsWct4p8xdxyFbNiBL7+53WdZlvkYz9aQ; AWSALB=YRFlONsz63uG5bltIP5rUPRgfUGJOIRvi79iw1/ZFo1aKlQ+EjL1Oh01yrl6pobmbqIcHNt6bXJn7VEGXZNXDvd2doBzjrh5kzzLraAGjWnF6666MnOrryzvxjkQ; AWSALBCORS=YRFlONsz63uG5bltIP5rUPRgfUGJOIRvi79iw1/ZFo1aKlQ+EjL1Oh01yrl6pobmbqIcHNt6bXJn7VEGXZNXDvd2doBzjrh5kzzLraAGjWnF6666MnOrryzvxjkQ; AWSALBAPP-1=remove; AWSALBAPP-2=remove; AWSALBAPP-3=remove; AWSALBAPP-0=AAAAAAAAAAD/A02X5lDniK0hpOtuUx7sOWx2MT4MsmFLflwuVtS6Y9jSKt40H7JFIfH0EmBZRtyJ0QbDyhtLFEEfK1b57a/RL0rG1LaSFRwZGfZGy6aAa6lQ0AqjwV0Kbw9dxtVOvFBRARbb; AWSALBAPP-0=AAAAAAAAAAC2aJOtczqsxvyOJ25NFikw4yUrxSELxPdl9t1Wt5CwzsCmV19i8CylbgsT/PAZTa28Fm+Wp40mNkt4fqJbAELnMIWV4cXnBFtQFIlwRbPELtYYuyZ/bteijYarrHjZstg/Q1jk; AWSALBAPP-1=remove; AWSALBAPP-2=remove; AWSALBAPP-3=remove");
		
        apiRequestContext =playwright.request().newContext(new APIRequest.NewContextOptions()
             .setBaseURL("https://bdo-unibank-dr.apigee.net/v1")
             .setExtraHTTPHeaders(headers));				
		
		
		return page;
	}

}
