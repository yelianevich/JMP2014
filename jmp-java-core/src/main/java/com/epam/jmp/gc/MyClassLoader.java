package com.epam.jmp.gc;

public class MyClassLoader extends ClassLoader {
	public MyClassLoader() {
		super();
	}

	public Class<?> _defineClass(String name, byte[] byteCodes) {
		return super.defineClass(name, byteCodes, 0, byteCodes.length);
	}
}
