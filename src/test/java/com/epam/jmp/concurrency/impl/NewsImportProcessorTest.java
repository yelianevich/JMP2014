package com.epam.jmp.concurrency.impl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

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
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.jmp.concurrency.job.NewsImportProcessor;
import com.epam.jmp.concurrency.model.News;
import com.epam.jmp.concurrency.model.service.LocalNewsService;
import com.epam.jmp.concurrency.model.service.NewsService;
import com.epam.jmp.test.util.TestData;

@RunWith(MockitoJUnitRunner.class)
public class NewsImportProcessorTest {
	private Logger LOG = LogManager.getLogger(LocalNewsService.class);
	private NewsImportProcessor importProcessor;
	private Path newsFile = Paths.get("tempXmlFile");
	private Path errorDir = Paths.get("errorDir");
	@Mock private NewsService newsService;

	@Before
	public void setUp() throws Exception {
		Files.deleteIfExists(newsFile);
		newsFile = Files.write(newsFile, TestData.getNewsXmlStr(), StandardOpenOption.CREATE_NEW);

		Files.deleteIfExists(errorDir);
		errorDir = Files.createDirectory(errorDir);

		given(newsService.upsertNews(any(News.class))).willAnswer((invocation) -> {
			LOG.info("upsert " + invocation.getArguments()[0]);
			return true;
		});
		importProcessor = new NewsImportProcessor(newsFile, errorDir, newsService);
	}

	@Test
	public void shouldImportNews() {
		Boolean imported = importProcessor.call();
		assertThat(imported, is(true));
		verify(newsService).upsertNews(any(News.class));
	}

	@After
	public void tearDown() throws IOException {
		Files.deleteIfExists(newsFile);
		Files.deleteIfExists(errorDir.resolve(newsFile));
		Files.deleteIfExists(errorDir);
	}
}
