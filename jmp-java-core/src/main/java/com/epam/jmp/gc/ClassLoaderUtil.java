package com.epam.jmp.gc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: Ivan_Spresov Date: 3/7/14
 */
public class ClassLoaderUtil {
	public static byte[] loadByteCode(Class<?> clazz, String className) throws IOException {
		String fileName = "/" + className.replaceAll("\\.", "/") + ".class";
		InputStream is = clazz.getResourceAsStream(fileName);
		ByteArrayOutputStream buffer = new ByteArrayOutputStream(4096);
		byte[] buff = new byte[1024];
		int i;
		while ((i = is.read(buff)) >= 0) {
			buffer.write(buff, 0, i);
		}

		return buffer.toByteArray();
	}
}
