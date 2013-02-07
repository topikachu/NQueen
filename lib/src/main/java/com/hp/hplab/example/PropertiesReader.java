package com.hp.hplab.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	public Properties getResourceProperties(String propertiesFile){
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(propertiesFile);
		Properties properties= new Properties();
		try {
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
}
