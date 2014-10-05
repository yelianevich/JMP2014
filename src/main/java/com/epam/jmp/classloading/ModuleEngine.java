package com.epam.jmp.classloading;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.jmp.classloading.module.MathModule;

public class ModuleEngine {
	private static final Logger LOG = LogManager.getLogger(ModuleEngine.class
			.getName());
	private String pathToJarFile;

	public ModuleEngine(String pathToJarDir) {
		this.pathToJarFile = pathToJarDir;
	}

	public Map<String, MathModule> loadModules() throws MalformedURLException {
		URL[] urls = { new URL("jar:file:" + pathToJarFile + "!/") };
		ClassLoader cl = URLClassLoader.newInstance(urls);
		Map<String, MathModule> modules = new HashMap<String, MathModule>();

		try (JarFile jarFile = new JarFile(pathToJarFile)) {
			Enumeration<JarEntry> jarEntries = jarFile.entries();
			while (jarEntries.hasMoreElements()) {
				JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
				if (jarEntry.isDirectory()
						|| !jarEntry.getName().endsWith(".class")) {
					continue;
				}
				String className = jarEntry.getName().substring(0,
						jarEntry.getName().length() - 6);
				className = className.replace('/', '.');
				Class<?> clazz = cl.loadClass(className);
				if (MathModule.class.isAssignableFrom(clazz)) {
					MathModule module = (MathModule) clazz.newInstance();
					modules.put(module.getName(), module);
				}
			}
		} catch (IOException e1) {
			LOG.error("Cannot process jar file");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			LOG.error("Cannot load class " + e.getMessage());
		}

		return modules;
	}

}
