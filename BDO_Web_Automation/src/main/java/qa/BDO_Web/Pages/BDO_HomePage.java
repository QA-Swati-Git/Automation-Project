 package qa.BDO_Web.Pages;

import com.microsoft.playwright.Page;

public class BDO_HomePage {
	
    private Page page;
	//private String BDOimg = "div.bb-stack__item bb-stack__item--fill identity-bg-layout";
	//private String BDOlogo = "div.bb-stack__item identity-logo bb-block bb-block--lg";
	private String BDOheader = "h1#kc-page-title";
	private String BDOLabel1 = "div.label text=Username";
	//private String BDOLabel2 = "div.form-group bb-form-field bb-form-field--md for=Password";
	private String BDOinputBox1 = "input#username";
	private String BDOinputBox2 = "input#password";
	private String BDOsbbutton = "input[name=login]";
	private String BDOLabel3 = "input text=Need help logging in?";
	private String BDOlink1 = "a[text= I'd like to get my username]";
	private String BDOlink2 ="a[text=I'd like to reset my password]";
	private String BDOOldLoginlink ="a[text=Log in to the old Online Banking website]"; 
			
			
	
	
	//page Constructor
	public BDO_HomePage(Page page)
	{
		this.page = page;
	}
	
	public String getHomePageTitle()
	{
		return page.title();
	}
	
	public String getHomePageurl()
	{
		return page.url();
	}
	
	public String GetHomePageComponenets()
	{
		String firstlabel = page.locator(BDOheader).textContent();	
		System.out.println("Application header name is:"+ firstlabel.trim());
		
		//String secondlabel = page.locator(BDOLabel2).textContent();	
		//System.out.println("Application first label name is:"+ secondlabel.trim());
		
		String inputBox1 = page.locator(BDOinputBox1).textContent();	
		System.out.println("Application first inputbox name is:"+ inputBox1);
		
		String inputBox2 = page.locator(BDOinputBox2).textContent();	
		System.out.println("Application second inputbox name is:"+ inputBox2);
		
		String sbbutton = page.locator(BDOsbbutton).textContent();
		System.out.println("Name of Button on home page is:"+ sbbutton);
		
		String Label3 = page.locator(BDOLabel3).textContent();	
		System.out.println("Application third label name is:"+ Label3);
		
		String link1 = page.locator(BDOlink1).textContent();	
		System.out.println("First link on home page is:"+ link1);
		
		String link2 = page.locator(BDOlink2).textContent();	
		System.out.println("First link on home page is:"+ link2);
		
		String OldLoginlink = page.locator(BDOOldLoginlink).textContent();
		System.out.println("Old login link name is:"+ OldLoginlink);
		
		return page.locator(BDOLabel1).first().textContent();
		
		
	}
	
	
}
