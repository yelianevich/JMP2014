package com.epam.jmp.gc.heap;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestHeap1 {
	static final Logger LOG = LogManager.getLogger(TestHeap1.class);

	public static void main(String[] args) throws Exception {
		List<Long[]> live = new ArrayList<>();
		int count = 0;
		while (true) {
			Long[] garbage = new Long[] {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L};
			count++;
			if (count % 25 == 0) {
				live.add(garbage);
				LOG.info(count);
			}
			Thread.sleep(100);
		}
	}
}
