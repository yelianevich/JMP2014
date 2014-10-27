package com.epam.jmp.concurrency.bitcoin;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransactionReader {
	private static final Logger LOG = LogManager.getLogger(TransactionReader.class);
	private final ScheduledExecutorService reader;

	public TransactionReader() {
		reader = Executors.newSingleThreadScheduledExecutor(run -> {
			Thread t = new Thread(run, "TransactionReader");
			t.setDaemon(true);
			return t;
		});
	}

	public void startReading() {
		reader.scheduleWithFixedDelay(() -> LOG.info("reader reads"), 3, 2, TimeUnit.SECONDS);
	}

}
