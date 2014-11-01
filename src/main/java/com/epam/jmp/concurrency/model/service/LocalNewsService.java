package com.epam.jmp.concurrency.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.jmp.concurrency.model.News;
import com.epam.jmp.concurrency.model.dao.NewsDao;

public class LocalNewsService implements NewsService {
	private Logger LOG = LogManager.getLogger(LocalNewsService.class);
	private NewsDao newsDao;

	@Override
	public boolean upsertNews(News news) {
		LOG.info(news + " is upserted");
		newsDao.mergeNews(news);
		return true;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
}
