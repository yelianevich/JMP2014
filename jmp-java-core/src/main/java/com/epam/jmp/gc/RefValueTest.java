package com.epam.jmp.gc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RefValueTest extends Object {
	static final Logger LOG = LogManager.getLogger(RefValueTest.class);

	public static void main(String[] args) {
		int a = 0;
		int[] b = {20};
		f(a, b);
		LOG.info(a + " " + b[0]);
		g(a, b);
		LOG.info(a + " " + b[0]);
	}

	private static void f(int a, int[] b) {
		a += 30;
		b[0] = 40;
	}

	private static void g(int a, int[] b) {
		a = 50;
		b = new int[] {60};
	}
}
