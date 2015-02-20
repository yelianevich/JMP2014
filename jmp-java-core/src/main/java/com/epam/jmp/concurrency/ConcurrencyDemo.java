package com.epam.jmp.concurrency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.jmp.concurrency.job.Import;

public final class ConcurrencyDemo {
	private static final Logger LOG = LogManager.getLogger(ConcurrencyDemo.class);

	public static void main(String[] args) throws InterruptedException {
		LOG.info("start importing");
		Runnable importing = new Import();
		importing.run();
		Thread.sleep(40000);
		LOG.info("importing finished");
	}

}
