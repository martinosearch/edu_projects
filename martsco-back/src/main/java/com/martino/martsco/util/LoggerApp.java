package com.martino.martsco.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerApp {
	private static final Logger logger = LoggerFactory.getLogger(LoggerApp.class);

	public static void info(String msg) {
		logger.info(msg);
	}

	public static void info(String msg, Exception e) {
		logger.info(msg, e);
	}

	public static void error(String msg, Exception ex) {
		logger.error(msg, ex);
	}
}
