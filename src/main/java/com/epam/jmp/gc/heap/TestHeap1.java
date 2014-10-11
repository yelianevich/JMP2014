package com.epam.jmp.gc.heap;

import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestHeap1 {
	static final Logger logger = LogManager.getLogger(TestHeap1.class);

	public static void main(String[] args) throws Exception {
		int count = 0;
		while (true) {
			new LinkedList<Object>();
			count++;
			logger.info(count);
			Thread.sleep(100);
		}
	}
}
