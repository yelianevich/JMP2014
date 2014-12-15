package com.epam.jmp.classloading.module.impl;

import com.epam.jmp.classloading.module.MathModule;

public class Sum extends MathModule {

	@Override
	public String getName() {
		return "sum";
	}

	@Override
	protected double getInitializer() {
		return 0;
	}

	@Override
	protected double processElement(double acc, double arg) {
		return acc + arg;
	}
}
