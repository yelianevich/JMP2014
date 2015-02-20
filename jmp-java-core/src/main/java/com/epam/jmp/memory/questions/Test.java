package com.epam.jmp.memory.questions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Test {
	private static final Logger LOG = LogManager.getLogger(ClassWithStatic.class);

	public static void main(String[] args) {
		ClassWithStatic classWithStatic = new ClassWithStatic();
		LOG.info(classWithStatic);
	}

}
