package com.epam.jmp.classloading.module.impl;

import com.epam.jmp.classloading.module.MathModule;

public class Multiply extends MathModule {

	@Override
	public String getName() {
		return "mul";
	}

	@Override
	protected double getInitializer() {
		return 1;
	}

	@Override
	protected double processElement(double acc, double arg) {
		return acc * arg;
	}
}
