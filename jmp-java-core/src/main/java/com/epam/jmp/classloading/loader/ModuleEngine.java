package com.epam.jmp.classloading.loader;

import java.util.Map;

public interface ModuleEngine <T> {

    Map<String, T> loadModules();

}
