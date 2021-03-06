package com.epam.jmp.concurrency.job;

import static java.util.stream.Collectors.joining;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.epam.jmp.concurrency.service.NewsService;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

@Component("importJob")
public class ImportJob implements Runnable {
    private static final Logger LOG = LogManager.getLogger(ImportJob.class);

    @Value("${common.input.folder.read.timeout:5}")
    private Long readTimeout;

    @Value("${common.error.folder}")
    private String errorFolder;

    @Value("#{${common.input.folder.threads} ?: T(Runtime).getRuntime().availableProcessors()}")
    private int threadCount;

    private final FilesReader filesReader;
    private final NewsService newsService;
    private ScheduledExecutorService scheduler;
    private ExecutorService executor;

    @Autowired
    public ImportJob(FilesReader filesReader, NewsService newsService) {
        this.filesReader = filesReader;
        this.newsService = newsService;
    }

    @PostConstruct
    public void init() {
        scheduler = Executors.newSingleThreadScheduledExecutor(threadFactory("import-schedule-%d"));
        executor = Executors.newFixedThreadPool(threadCount, threadFactory("import-worker-%d"));
    }

    private ThreadFactory threadFactory(String nameFormat) {
        return new ThreadFactoryBuilder()
                .setDaemon(true)
                .setNameFormat(nameFormat)
                .build();
    }

    @Override
    public void run() {
        Path errorDir = Paths.get(errorFolder);
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
