package com.epam.jmp.memory;

import com.epam.jmp.gc.ClassLoaderUtil;
import com.epam.jmp.gc.MyClassLoader;
import com.epam.jmp.gc.PermGenTest;

// -XX:MaxMetaspaceSize=12m -Xmx100m -XX:+UnlockCommercialFeatures -XX:+FlightRecorder
public final class PermGenOutOfMemory {

	public static void main(String[] args) throws Exception {
		Class<?> clazz = PermGenTest.class;
		byte[] buffer = ClassLoaderUtil.loadByteCode(clazz, clazz.getName());

		MyClassLoader loader = new MyClassLoader();
		for (long index = 0; index < Long.MAX_VALUE; index++) {
			String newClassName = "_"
					+ String.format("%0" + (clazz.getSimpleName().length() - 1) + "d", index);
			byte[] newClassData = new String(buffer, "latin1").replaceAll(clazz.getSimpleName(),
					newClassName).getBytes("latin1");
			// loader = new MyClassLoader(); // uncomment and GC will remove classes from Metaspace
			loader._defineClass(clazz.getName().replace(clazz.getSimpleName(), newClassName),
					newClassData);
			Thread.sleep(100);
		}
	}

}
