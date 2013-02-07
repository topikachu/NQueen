package com.hp.hplab.example;

import static org.junit.Assert.*;

import java.util.Enumeration;
import java.util.Properties;

import org.junit.Test;

public class PropertiesReaderTest {

	@Test
	public void test() {
		PropertiesReader reader=new PropertiesReader();
	    Properties	properties =reader.getResourceProperties("example.properties");
	    assertEquals("1", properties.get("a"));
	    assertEquals("2", properties.get("b"));
		 Enumeration<?> enumerations = properties.propertyNames();
		while (enumerations.hasMoreElements()){
			String key=(String) enumerations.nextElement();
			System.out.println(properties.get(key));
		}
	}

}
