package com.epam.jmp.memory.phantom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Phrase implements Disposable {
	private static final Logger LOG = LogManager.getLogger(Phrase.class);
	private String phrase;
	
	public Phrase(String phrase) {
		this.phrase = phrase;
	}
	
	@Override
	public void dispose() {
		LOG.info(phrase);
	}

}
