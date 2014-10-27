package com.epam.jmp.concurrency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.jmp.concurrency.bitcoin.TransactionReader;

public final class ConcurrencyDemo {
	private static final Logger LOG = LogManager.getLogger(ConcurrencyDemo.class);

	public static void main(String[] args) throws InterruptedException {
		TransactionReader reader = new TransactionReader();
		reader.startReading();
		Thread.sleep(10000);
		LOG.info("main thread has terminaited");
	}

}
