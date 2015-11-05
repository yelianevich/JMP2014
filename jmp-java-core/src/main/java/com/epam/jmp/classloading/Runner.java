package com.epam.jmp.classloading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.jmp.classloading.loader.ModuleEngine;
import com.epam.jmp.classloading.loader.impl.MathModuleEngine;
import com.epam.jmp.classloading.module.MathModule;

public class Runner {
	private static final Logger LOG = LogManager.getLogger(Runner.class
			.getName());

	public static void main(String[] args) {
		String jarPath = args[0];

		ModuleEngine<MathModule> engine = new MathModuleEngine(jarPath);
		Map<String, MathModule> modules = Collections.emptyMap();
		modules = engine.loadModules();

		LOG.info("Available Math Modules:");
		for (String key : modules.keySet()) {
			LOG.info(key);
		}
		try (Scanner scanner = new Scanner(System.in)) {
			for (;;) {
				LOG.info("Please specify module name");
				String moduleName = scanner.nextLine();

				MathModule module = modules.get(moduleName);
				if (module == null) {
					LOG.info("Operation not found");
					continue;
				}

				LOG.info("Please specify operation arguments");
				String argsInput = scanner.nextLine();

				Integer[] ops = parseInts(argsInput);
				LOG.info("Result = " + module.process(ops));
			}
		}
	}

	private static Integer[] parseInts(String argsInput) {
		List<Integer> xs = new ArrayList<>();
		try (Scanner intScanner = new Scanner(argsInput)) {
			while (intScanner.hasNextInt()) {
				xs.add(intScanner.nextInt());
			}
		}
		return xs.toArray(new Integer[0]);
	}
}
