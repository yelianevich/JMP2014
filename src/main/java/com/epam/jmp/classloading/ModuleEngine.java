package com.epam.jmp.classloading;

import java.util.HashMap;
import java.util.Map;

import com.epam.jmp.classloading.module.MathModule;

public class ModuleEngine {
	private String modulePath;

	public ModuleEngine(String modulePath) {
		this.modulePath = modulePath;
	}
	
	public Map<String, MathModule> loadModules() {
		return new HashMap<String, MathModule>();
	}
	
}
