package com.epam.jmp.concurrency.impl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.jmp.test.util.TestData;

public class NewsImportProcessorTest {
	private Logger LOG = LogManager.getLogger(LocalNewsService.class);
	private NewsImportProcessor importProcessor;
	private Path newsFile = Paths.get("tempXmlFile");
	private Path errorDir = Paths.get("errorDir");
	private NewsService newsService = new LocalNewsService();

	@Before
	public void setUp() throws Exception {
		Files.deleteIfExists(newsFile);
		newsFile = Files.write(newsFile, TestData.getNewsXmlStr(), StandardOpenOption.CREATE_NEW);

		Files.deleteIfExists(errorDir);
		errorDir = Files.createDirectory(errorDir);
		importProcessor = new NewsImportProcessor(newsFile, errorDir, newsService);
	}

	@Test
	public void test() {
		Boolean imported = importProcessor.call();
		assertThat(imported, is(true));
	}

	@After
	public void tearDown() throws IOException {
		if (!Files.deleteIfExists(newsFile)) {
			LOG.info("News file was deleted");
		}
		if (!Files.deleteIfExists(errorDir.resolve(newsFile))) {
			LOG.info("Error dir was empty. No error news files.");
		}
		Files.deleteIfExists(errorDir);
	}
}
