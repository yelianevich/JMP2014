package com.epam.jmp.concurrency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.jmp.concurrency.job.ImportJob;
import com.sun.imageio.plugins.common.I18N;

public final class ConcurrencyDemo {
	private static final Logger LOG = LogManager.getLogger(ConcurrencyDemo.class);

	public static void main(String[] args) throws InterruptedException {
		Runnable importJob = new ImportJob();
		importJob.run();
	}

}
