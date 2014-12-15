package com.epam.jmp.util;

import java.util.Locale;
import java.util.ResourceBundle;

public enum Config {
	INST;

	private ResourceBundle bundle;

	private static final String BUNDLE_NAME = "jmp";

	public static final String INPUT_FOLDER = "common.input.folder";
	public static final String ERROR_FOLDER = "common.error.folder";
	public static final String READ_TIMEOUT = "common.input.folder.read.timeout";
	public static final String THREADS_COUNT = "common.input.folder.threads";

	public static final String DB_DRIVER = "db.driver.name";
	public static final String DB_URL = "db.url";
	public static final String DB_USER = "db.username";
	public static final String DB_PASSWORD = "db.password";

	private Config() {
		bundle = ResourceBundle.getBundle(BUNDLE_NAME, Locale.ROOT);
	}

	public String prop(String key) {
		return bundle.getString(key);
	}

}
