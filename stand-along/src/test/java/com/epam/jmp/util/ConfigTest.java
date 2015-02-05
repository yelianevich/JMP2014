package com.epam.jmp.util;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ConfigTest {

	@Test
	public void shouldInitBundelCorretly() throws Exception {
		Config config = Config.INST;
		assertThat(config, notNullValue());
	}

	@Test
	public void shouldReturnInputFolder() {
		String pathStr = Config.INST.prop(Config.INPUT_FOLDER);
		Path path = Paths.get(pathStr);
		assertThat(path, notNullValue());
	}

	@Test
	public void shouldReturnErrorFolder() throws Exception {
		String pathStr = Config.INST.prop(Config.ERROR_FOLDER);
		Path path = Paths.get(pathStr);
		assertThat(path, notNullValue());
	}

	@Test
	public void shouldReturnPositiveReadTimeout() throws Exception {
		String readTimeoutStr = Config.INST.prop(Config.READ_TIMEOUT);
		int timeout = Integer.parseInt(readTimeoutStr, 10);
		assertThat(timeout, greaterThan(0));
	}

	@Test
	public void shouldReturnPositiveThreadCount() throws Exception {
		String threadCountStr = Config.INST.prop(Config.THREADS_COUNT);
		int threadCount = Integer.parseInt(threadCountStr, 10);
		assertThat(threadCount, greaterThan(0));
	}

}
