package com.epam.jmp.classloading.module;


public abstract class MathModule {

	public abstract String getName();

	public final double process(Integer... args) {
		double res = getInitializer();

		for (Integer arg : args) {
			res = processElement(res, arg);
		}
		return res;
	}

	abstract protected double getInitializer();

	abstract protected double processElement(double acc, double arg);
}
