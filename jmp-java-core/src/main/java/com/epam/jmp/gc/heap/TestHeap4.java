package com.epam.jmp.gc.heap;

public class TestHeap4 {
	public static void main(String[] args) {
		Object[] ref = new Object[1];
		while (true) {
			Object[] a = new Object[1];
			Object[] b = new Object[1];
			a[0] = b;
			b[0] = a;
			ref[0] = a;
		}
	}
}
