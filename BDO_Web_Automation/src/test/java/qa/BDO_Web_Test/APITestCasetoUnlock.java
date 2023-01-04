package qa.BDO_Web_Test;
 

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


import java.util.HashMap;
import java.util.Map;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.jayway.jsonpath.JsonPath;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;

import com.microsoft.playwright.options.RequestOptions;



public class APITestCasetoUnlock {


	Playwright playwright;
	APIRequestContext apiRequestContext;
	JsonPath JSON;
	String Token;
	
	@BeforeClass
	
	public void setup()
	{
		playwright= Playwright.create();
		

Map<String,String> headers= new HashMap<>();

headers.put("Authorization", "Basic UXJHNHdRajZXbEtDd0VQOEhZQWw4azdvQUNpNEdZWXM6blV4enh6c2ZjVklDZXhFQw==");
headers.put("Content-Type", "application/x-www-form-urlencoded");		
headers.put("Cookie","AAWSALBAPP-0=AAAAAAAAAACAxWv6S8JIeYkGpvdmYEpZNKWIPRh/9s3Qi62Indd5eXNtAy5ClBUdlXYK5IZq7feoHQUygr5/Z4on/k1PnP/s2iHjiqdVG1MpMltxZPxvXPuT1I1V6zdVfVJxwWCCFZfS+nd1ieb7TIpFf7UpitIOJZ/mtW1v/irm1oELDwA94DQadbfTi1PCmKTCkGgeegOTtkyri2vxTgUVFa1Mj8zF8kQ78Q==; AWSALBAPP-1=_remove_; AWSALBAPP-2=_remove_; AWSALBAPP-3=_remove_; AWSALBAPP-0=AAAAAAAAAAB1qX0owtSp/hmhvK9KYAzwqlT3RgbYjeTWEVo3ek6xouE+VkhDSongjaI1tmoYszPewbz1rqTKzDfU11+2qg8ucLLLaLh2za2DVpUsqmVKc6+64uz1h9S5iehdHO65xTWBWt3hD5B8L2Z5dXPc6XbsglyQlUTe7jL+W0craNm14nJx1VrZHXUSxlejf8FtorhGoWzf9+99g8klRIz2ikjqNJJa9g==; AWSALBAPP-1=_remove_; AWSALBAPP-2=_remove_; AWSALBAPP-3=_remove_");
apiRequestContext =playwright.request().newContext(new APIRequest.NewContextOptions()
         .setBaseURL("https://bdo-unibank-dr.apigee.net")
         .setExtraHTTPHeaders(headers));		
	}

	
	public String getDataString1(HashMap<String, String> params) throws UnsupportedEncodingException{
	    StringBuilder result = new StringBuilder();
	    boolean first = true;
	    for(Map.Entry<String, String> entry : params.entrySet()){
	        if (first)
	            first = false;
	        else
	            result.append("&");    
	        result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
	        result.append("=");
	        result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
	    }    
	    return result.toString();
	}
	
	@Test
	
	public void APITesttoGenerateAccessToken() throws UnsupportedEncodingException
	{

HashMap<String, String> payloadComment = new HashMap<>();


payloadComment.put("username","admin1");
payloadComment.put("password","ChangeIt10!");
payloadComment.put("grant_type","password");

APIResponse response= apiRequestContext.post("/v1/auth/token", RequestOptions.create().setData(getDataString1(payloadComment)));
System.out.println(response.text());
System.out.println(response.status());

JsonObject json = new Gson().fromJson(response.text(), JsonObject.class);

Token = json.get("access_token").getAsString();
System.out.println(Token);
	}

//System.out.println(json.get("access_token"));




 
	@Test
	
	public void APITesttoUnlockUser() throws UnsupportedEncodingException
	{
		
		Map<String,String> headers1= new HashMap<>();

		headers1.put("Authorization", "Bearer"+" "+Token);
		System.out.print("Bearer"+" "+Token);
		headers1.put("Content-Type", "application/json");		
		//headers1.put("Cookie","AWSALB=ffrSvigNyOzQspov/8Xj5IZ9IE1Fo+Gby/sXWxCBap+CxYimmnUu4aF+4AfzHDH55cEHoSjY0gy8rG9GR6TJ9UcBjV3+hJMPz9PLiMIIG7FP6oVNe/7tMj3mbmoN; AWSALBCORS=ffrSvigNyOzQspov/8Xj5IZ9IE1Fo+Gby/sXWxCBap+CxYimmnUu4aF+4AfzHDH55cEHoSjY0gy8rG9GR6TJ9UcBjV3+hJMPz9PLiMIIG7FP6oVNe/7tMj3mbmoN; AWSALBAPP-0=AAAAAAAAAAD8nj/Mu9ibloQHsu7JrokV8/irjT8E7YU7dy6H09izBuukpjy8BR2CMYm/T0lbEMQixt4sIDB6Nu7H169Kak9UC73VO2POG4DVeFLqO/Gc1syIYKCF35tKnQ0hSQ8lFXuA8TWT; AWSALBAPP-1=_remove_; AWSALBAPP-2=_remove_; AWSALBAPP-3=_remove_");
		apiRequestContext =playwright.request().newContext(new APIRequest.NewContextOptions()
		         .setBaseURL("https://bdo-unibank-dr.apigee.net")
		         .setExtraHTTPHeaders(headers1));
		
		HashMap<String, String> payload= new HashMap<>();


		payload.put("status","ACTIVE");
		payload.put("retryCount","0");
		

		APIResponse response1= apiRequestContext.put("/v1/auth/accounts/BBNDB007", RequestOptions.create().setData(payload));
		System.out.println(response1.text());
		System.out.println(response1.status());


		
	}
	
	@AfterTest

	public void tearDown()
	{
		apiRequestContext.dispose();
		playwright.close();
		
	}
	
}
