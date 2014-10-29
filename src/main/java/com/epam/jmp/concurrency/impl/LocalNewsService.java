package com.epam.jmp.concurrency.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LocalNewsService implements NewsService {
	private Logger LOG = LogManager.getLogger(LocalNewsService.class);
	
	@Override
	public boolean upsertNews(News news) {
		LOG.info(news + " is upserted");
		return true;
	}
}
