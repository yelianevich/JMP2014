package com.epam.jmp.memory.questions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClassWithStatic {
	private static final Logger LOG = LogManager.getLogger(ClassWithStatic.class);
	private int instInt = 1;
	private static int staticInt = 1;

	{
		LOG.info("instInt before first logical block = " + instInt);
		LOG.info("logical block 1");
		instInt = 2;
	}

	public ClassWithStatic() {
		LOG.info("Constructor");
		LOG.info("instInt = " + instInt);
		LOG.info("staticInt = " + staticInt);
	}

	{
		LOG.info("logical block 2");
		instInt = 3;
	}

	static {
		LOG.info("staticInt before static logical block = " + staticInt);
		LOG.info("static logical block 2");
		staticInt = 999;
	}

	@Override
	public String toString() {
		return "ClassWithStatic [instInt=" + instInt + "]";
	}
}
