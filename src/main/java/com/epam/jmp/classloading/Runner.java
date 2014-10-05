package com.epam.jmp.classloading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.jmp.classloading.module.MathModule;

public class Runner {
	private static final Logger LOG = LogManager.getLogger("HelloWorld");

	public static void main(String[] args) {
		LOG.info("Starting app");

		String modulePath = args[0];

		ModuleEngine engine = new ModuleEngine(modulePath);
		Map<String, MathModule> modules = engine.loadModules();

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

				List<Integer> xs = new ArrayList<>();
				try (Scanner intScanner = new Scanner(argsInput)) {
					while (intScanner.hasNextInt()) {
						xs.add(intScanner.nextInt());
					}
				}
				Integer[] ops = xs.toArray(new Integer[0]);
				LOG.info("Args: " + Arrays.toString(ops));
			}
		}
	}
}
