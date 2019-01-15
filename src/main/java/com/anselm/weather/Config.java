package com.anselm.weather;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private static final String URL_TOKEN_QUESTION = "?";
	
	private static final String URL_TOKEN_AMPERSAND = "&";
	
	private static final String URL_TOKEN_EQUALS = "=";
	
	private String configFileName = "application.properties";
	
	InputStream inputStream;
	
	public String getProperty(String property) throws IOException {
		String propVal = null;
		
		try {
			Properties prop = this.getProperties();
			inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);
			
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException(
						"config file '" + configFileName + "' not found in the classpath"
				);
			}
			
			propVal = prop.getProperty(property);
		} catch (Exception ex) {
			System.out.println("Exception: " + ex);
		} finally {
				inputStream.close();
		}
		
		return propVal;
	}
	
	public String getWeatherApiUrlForCity(int cityId) throws IOException {
		String baseUrl = this.getProperty("weather.api.base_url");
		String forecastEndpoint = this.getProperty("weather.api.forecast.endpoint");
		String apiAppId = this.getProperty("weather.api.app_id");
		String apiUnits = this.getProperty("weather.api.units");
		String paramKeyCityId = this.getProperty("weather.api.param_key.city_id");
		String paramKeyAppId = this.getProperty("weather.api.param_key.app_id");
		String paramKeyUnits = this.getProperty("weather.api.param_key.units");
		String url = baseUrl + forecastEndpoint + Config.URL_TOKEN_QUESTION + paramKeyCityId + 
				Config.URL_TOKEN_EQUALS + cityId + Config.URL_TOKEN_AMPERSAND + 
				paramKeyAppId + Config.URL_TOKEN_EQUALS + apiAppId + Config.URL_TOKEN_AMPERSAND +
				paramKeyUnits + Config.URL_TOKEN_EQUALS + apiUnits;
		
		return url;
	}
	
	private Properties getProperties() {
		return new Properties();
	}
}
