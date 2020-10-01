package com.hackerthon.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.AbstractExecutorService;
import java.util.logging.Logger;

import com.hackerthon.config.GlobalConstant;


public class UtilConfig {

	public static final Properties prop = new Properties();
	public static final Logger log = Logger.getLogger(AbstractExecutorService.class.getName());

	static {
		try {
			prop.load(UtilQuery.class.getResourceAsStream(GlobalConstant.CONFIG_PROP_PATH));
		} catch (FileNotFoundException e) {
			log.warning(e.getMessage());
		} catch (IOException e) {
			log.warning(e.getMessage());
		}
	}
}
