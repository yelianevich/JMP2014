package com.epam.jmp.memory;

import java.util.ArrayList;
import java.util.List;

public final class HeapOutOfMemory {

	public static void main(String[] args) {
		long i = 0;
		List<long[]> live = new ArrayList<>();
		while (true) {
			++i;
			long[] garbage = { 1L, 2L, 3L, 4L, 5L, 6L };
			if (i % 5 == 0) {
				live.add(garbage);
			}
		}
	}

}
