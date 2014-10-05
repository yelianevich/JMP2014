package com.epam.jmp.classloading.module.impl;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.epam.jmp.classloading.module.MathModule;

public class MultiplyTest {

	private MathModule mul = new Multiply();

	@Test
	public void shouldReturnNameMultiply() {
		assertThat(mul.getName(), is("Multiply"));
	}

	@Test
	public void twoTimesTwoIsFour() throws Exception {
		assertThat(mul.process(2, 2), is(4.0));
	}

	@Test
	public void twoTimesTwoTimesTwoIsEight() throws Exception {
		assertThat(mul.process(2, 2, 2), is(8.0));
	}

}
