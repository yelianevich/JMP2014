package com.epam.jmp.gc.heap;

public class TestHeap5 {
	public static void main(String[] args) {
		Object[] ref = new Object[1];
		while (true) {
			ref = new Object[] {ref};
		}
	}
}
