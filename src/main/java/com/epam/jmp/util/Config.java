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

	private Config() {
		bundle = ResourceBundle.getBundle(BUNDLE_NAME, Locale.ROOT);
	}
	
	public String prop(String key) {
		return bundle.getString(key);
	}

}
