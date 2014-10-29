package com.epam.jmp.concurrency.job;

import static com.epam.jmp.util.Config.READ_TIMEOUT;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.jmp.concurrency.files.FilesReader;
import com.epam.jmp.util.Config;

public class ReaderJob implements Runnable {
	private static final Logger LOG = LogManager.getLogger(ReaderJob.class);
	private final ScheduledExecutorService reader;
	private final FilesReader filesReader;

	public ReaderJob(FilesReader filesReader) {
		this.filesReader = filesReader;
		reader = Executors.newSingleThreadScheduledExecutor(run -> {
			Thread t = new Thread(run, "TransactionReader");
			t.setDaemon(true);
			return t;
		});
	}

	@Override
	public void run() {
		int readTimeout = Integer.parseInt(Config.INST.prop(READ_TIMEOUT));
		reader.scheduleWithFixedDelay(() -> filesReader.readFiles(), readTimeout, readTimeout,
				TimeUnit.SECONDS);
	}

}
