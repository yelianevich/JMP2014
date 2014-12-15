package com.epam.jmp.classloading.module.impl;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.epam.jmp.classloading.module.MathModule;

public class MultiplyTest {

	private MathModule mul = new Multiply();

	@Test
	public void shouldReturnNameMul() {
		assertThat(mul.getName(), is("mul"));
	}

	@Test
	public void twoTimesTwoIsFour() {
		assertThat(mul.process(2, 2), is(4.0));
	}

	@Test
	public void twoTimesTwoTimesTwoIsEight() {
		assertThat(mul.process(2, 2, 2), is(8.0));
	}

}
