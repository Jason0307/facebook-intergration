package org.springframework.social.quickstart.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	Properties properties = new Properties();
	private static PropertiesUtil instance;
	private PropertiesUtil(){
		try {
			InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("facebook.properties");
			properties.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static PropertiesUtil getInstance(){
		if(null == instance){
			instance = new PropertiesUtil();
		}
		return instance; 
	}

	public String getAppId() {
		return properties.getProperty("client_id");
	}
	
	public String getRedirectUrl() {
		return properties.getProperty("redirect_uri");
	}
	
	public String getDisplay() {
		return properties.getProperty("popup");
	}
	
	public String getResponseType() {
		return properties.getProperty("response_type");
	}
	
	
	
	public static void main(String[] args) {
		String appId = PropertiesUtil.getInstance().getAppId();
		
		System.out.println(appId);
	}
}
