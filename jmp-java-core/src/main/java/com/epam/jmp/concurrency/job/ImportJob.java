package com.epam.jmp.concurrency.job;

import static com.epam.jmp.util.Config.ERROR_FOLDER;
import static com.epam.jmp.util.Config.READ_TIMEOUT;
import static com.epam.jmp.util.Config.THREADS_COUNT;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.epam.jmp.concurrency.files.FilesReader;
import com.epam.jmp.concurrency.model.service.NewsService;
import com.epam.jmp.util.Config;

@Component
@Qualifier("importJob")
public class ImportJob implements Runnable {
	private static final Logger LOG = LogManager.getLogger(ImportJob.class);
	private final ScheduledExecutorService scheduler;
	private final FilesReader filesReader;
	private final NewsService newsService;
	private final ExecutorService executor;

	@Autowired
	public ImportJob(FilesReader filesReader, NewsService newsService) {
		this.filesReader = filesReader;
		this.newsService = newsService;
		scheduler = Executors.newSingleThreadScheduledExecutor(run -> {
			Thread t = new Thread(run, "TransactionReader");
			t.setDaemon(true);
			return t;
		});
		executor = Executors.newFixedThreadPool(getThreadCount());
	}

	private int getThreadCount() {
		String threadCountStr = Config.INST.prop(THREADS_COUNT);
		return isBlank(threadCountStr)
				? Runtime.getRuntime().availableProcessors()
				: Integer.parseInt(threadCountStr);
	}

	@Override
	public void run() {
		long readTimeout = Long.parseLong(Config.INST.prop(READ_TIMEOUT));
		Path errorDir = Paths.get(Config.INST.prop(ERROR_FOLDER));
		scheduler.scheduleWithFixedDelay(() -> {
			LOG.info("Import started");
			List<Path> files = filesReader.readFiles();
			String filesString = files.stream()
					.map(Path::getFileName)
					.map(Path::toString)
					.collect(joining(", "));
			LOG.info(files.size() + " files to import: " + filesString);
			files.forEach(file -> {
				Callable<Boolean> importTask = new NewsImportProcessor(file, errorDir, newsService);
				executor.submit(importTask);
			});
		}, 0, readTimeout, TimeUnit.SECONDS);
	}
}
