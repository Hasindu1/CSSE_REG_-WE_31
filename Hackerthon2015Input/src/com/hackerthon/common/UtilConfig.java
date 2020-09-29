package com.hackerthon.common;

import java.util.Properties;

import com.hackerthon.config.GlobalConstant;


public class UtilConfig {

	public static final Properties prop = new Properties();

	static {
		try {
			prop.load(UtilQuery.class.getResourceAsStream(GlobalConstant.CONFIG_PROP_PATH));
		} catch (Exception e) {
			
		}
	}
}
