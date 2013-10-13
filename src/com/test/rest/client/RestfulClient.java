package com.test.rest.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class RestfulClient {

	public static void main(String[] args) throws IOException 
	{
		
	/*
		URL url = new URL("http://localhost:8080/RESTfulSpringSecurity-master/rest/api/json/5");  
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();   
        
        if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
 
          
        conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		  //Set basic authentication in the header  
        String userPassword = "jack:jill";      
        String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());   
        conn.setRequestProperty("Authorization", "Basic " + encoding);  
		
	
  
        conn.setDoOutput(true);  
        conn.setDoInput(true);  
        conn.setUseCaches(false);  
          
      
          
        //Submit user and password.  A JSON object will return that contains session name and session id for this user  
       /* String formParameters = "username=" + URLEncoder.encode("user1", "UTF-8") + "&password=" + URLEncoder.encode("user1password", "UTF-8");  
                           
       //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");   
       // conn.setRequestProperty("Content-Length", Integer.toString(formParameters.length()));  
             
       // DataOutputStream out = new DataOutputStream(conn.getOutputStream());  
          
      //  out.writeBytes(formParameters);  
       // out.flush();  
      //  out.close();  
          
       // System.out.println(conn.getResponseCode()); 
       // 
        BufferedReader br = new BufferedReader(new InputStreamReader(
    			(conn.getInputStream())));
     
    		String output;
    		System.out.println("Output from Server .... \n");
    		while ((output = br.readLine()) != null) {
    			System.out.println(output);
    		}
     
    		conn.disconnect();
    		*/
		
         // Essentially return a new HttpClient(), but can be pulled from Spring context
		String webPage = "http://localhost:8080/RESTfulSpringSecurity-master/rest/api/json/5";
		String name = "jack";
		String password = "jill";

		String authString = name + ":" + password;
		System.out.println("auth string: " + authString);
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
		System.out.println("Base64 encoded auth string: " + authStringEnc);

		URL url = new URL(webPage);
		URLConnection urlConnection = url.openConnection();
		urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
		InputStream is = urlConnection.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);

		int numCharsRead;
		char[] charArray = new char[1024];
		StringBuffer sb = new StringBuffer();
		while ((numCharsRead = isr.read(charArray)) > 0) {
			sb.append(charArray, 0, numCharsRead);
		}
		String result = sb.toString();

		System.out.println("*** BEGIN ***");
		System.out.println(result);
		System.out.println("*** END ***");

	}
	

}
