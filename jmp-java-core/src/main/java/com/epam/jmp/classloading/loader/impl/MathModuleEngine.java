package com.epam.jmp.classloading.loader.impl;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.jmp.classloading.loader.ModuleEngine;
import com.epam.jmp.classloading.loader.ModuleLoader;
import com.epam.jmp.classloading.module.MathModule;

public class MathModuleEngine implements ModuleEngine<MathModule> {
    private static final Logger LOG = LogManager.getLogger(MathModuleEngine.class.getName());

    private String pathToJarFile;
    private ClassLoader classLoader;

    public MathModuleEngine(String pathToJarDir) {
        this.pathToJarFile = pathToJarDir;
        this.classLoader = new ModuleLoader(pathToJarDir);
    }

    @Override
    public Map<String, MathModule> loadModules() {
        Map<String, MathModule> modules = new HashMap<>();

        try (JarFile jarFile = new JarFile(pathToJarFile)) {
            Enumeration<JarEntry> jarEntries = jarFile.entries();
            while (jarEntries.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
                boolean hasNoClassExt = !jarEntry.getName().endsWith(".class");
                if (hasNoClassExt || jarEntry.isDirectory()) {
                    continue;
                }
                String entryName = jarEntry.getName();
                String className = entryName.substring(0,
                        entryName.length() - 6);
                className = className.replace('/', '.');

                Class<?> clazz = classLoader.loadClass(className);
                LOG.info(className + " was loaded by " + clazz.getClassLoader());
                if (MathModule.class.isAssignableFrom(clazz) && !clazz.isInterface()) {
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
