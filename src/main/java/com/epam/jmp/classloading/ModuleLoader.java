package com.epam.jmp.classloading;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;

public class ModuleLoader extends ClassLoader {
	private static final Logger LOG = LogManager.getLogger(ModuleLoader.class
			.getName());
	private String jarPath;

	public ModuleLoader(String jarPath) {
		this.jarPath = jarPath;
	}

	public ModuleLoader(String jarPath, ClassLoader parent) {
		super(parent);
		this.jarPath = jarPath;
	}

	public Class<?> findClass(String name) {
		try {
			byte[] clazzBytes = fetchFromJar(name);
			return defineClass(name, clazzBytes, 0, clazzBytes.length);
		} catch (ClassFormatException e) {
			LOG.error("Class is corrupted", e);
		}
		return null;
	}

	private byte[] fetchFromJar(String name) {
		byte[] clazzBytes = null;
		try (JarFile jarFile = new JarFile(jarPath)) {
			JarEntry clazzEntry = jarFile.stream()
					.filter(je -> je.getName().equals(name)).findFirst().get();
			InputStream in = jarFile.getInputStream(clazzEntry);
			BufferedInputStream is = new BufferedInputStream(in);
			int size = (int) clazzEntry.getSize();
			clazzBytes = new byte[size];

			int offset = 0;
			int numRead = 0;
			while (offset < size
					&& (numRead = is.read(clazzBytes, offset, size - offset)) >= 0) {
				offset += numRead;
			}
		} catch (IOException e1) {
			LOG.error("Cannot process jar file");
		}
		return clazzBytes;
	}

}
