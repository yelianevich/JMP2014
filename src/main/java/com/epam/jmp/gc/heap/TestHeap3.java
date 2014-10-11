package com.epam.jmp.gc.heap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TestHeap3 {
	static Logger logger = LogManager.getLogger(TestHeap3.class);

	public static void main(String[] args) {
		List<Object> list = new ArrayList();
		while (true) {
			list.add(new long[3000]);
			if (list.size() > 100) {
				logger.info(list.size());
				list.clear();
			}
		}
	}
}
