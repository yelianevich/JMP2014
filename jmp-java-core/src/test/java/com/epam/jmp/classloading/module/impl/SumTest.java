package com.epam.jmp.classloading.module.impl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.epam.jmp.classloading.module.MathModule;

public class SumTest {

	MathModule sum = new Sum();
	
	@Test
	public void shouldReturnNameSum() {
		assertThat(sum.getName(), is("sum"));
	}
	
	@Test
	public void shouldReturnTwoOnOnePlusOne() {
		assertThat(sum.process(1, 1), is(2.0));
	}
	
	@Test
	public void shouldReturnFiveOnOnePlusOnePlusThree() {
		assertThat(sum.process(1,1,3), is(5.0));
	}

}
