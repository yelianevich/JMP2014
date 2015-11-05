package com.epam.jmp.util;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.jmp.concurrency.config.ConcurrencyConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConcurrencyConfig.class)
public class ConfigTest {

	@Autowired
	private Environment env;
	
	@Test
	public void shouldInitBundelCorretly() throws Exception {
		assertThat(env, notNullValue());
	}

	@Test
	public void shouldReturnInputFolder() {
		String pathStr = env.getProperty(Constants.INPUT_FOLDER);
		Path path = Paths.get(pathStr);
		assertThat(path, notNullValue());
	}

	@Test
	public void shouldReturnErrorFolder() throws Exception {
		String pathStr = env.getProperty(Constants.ERROR_FOLDER);
		Path path = Paths.get(pathStr);
		assertThat(path, notNullValue());
	}

	@Test
	public void shouldReturnPositiveReadTimeout() throws Exception {
		String readTimeoutStr = env.getProperty(Constants.READ_TIMEOUT);
		int timeout = Integer.parseInt(readTimeoutStr, 10);
		assertThat(timeout, greaterThan(0));
	}

	@Test
	public void shouldReturnPositiveThreadCountOrBlank() throws Exception {
		String threadCountStr = env.getProperty(Constants.THREADS_COUNT);
		int threadCount = isNotBlank(threadCountStr)
				? Integer.parseInt(threadCountStr)
				: 4;
		assertThat(threadCount, greaterThan(0));
	}

}
