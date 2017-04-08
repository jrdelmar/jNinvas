package com.jrdm.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.jrdm.model.NinvasProperties;

public class PropertyService {
	
	private NinvasProperties config = new NinvasProperties();
	private Properties prop = new Properties();
	
	/*public static void main(String[] args) {
		PropertyService sample = new PropertyService();
		sample.getConfig();
		
    }*/
	
	public NinvasProperties getConfig(){
		try {

			InputStream in = getClass().getResourceAsStream(MyConstants.PROP_CONFIG_FILE);

			prop.load(in);

			// get the property value
			config.setExploitSearchUrl(prop.getProperty("exploitsearchurl"));
			config.setDefaultShodanApiKey(prop.getProperty("defaultshodanapikey"));
			config.setGoogleApiKey(prop.getProperty("googlemapapikey"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return config;
	}

	public final String getDefaultShodanKey(){
		String apiKey = "";
		try {

			InputStream in = getClass().getResourceAsStream(MyConstants.PROP_CONFIG_FILE);
			prop.load(in);

			// get the property value
			apiKey = prop.getProperty("defaultshodanapikey");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return apiKey;
	}
	
	public PropertyService(){
		
	}


}
