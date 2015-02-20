package com.epam.jmp.concurrency.model.service;

import com.epam.jmp.concurrency.model.News;
import com.epam.jmp.concurrency.model.dao.NewsDao;

public class LocalNewsService implements NewsService {
	private NewsDao newsDao;

	public LocalNewsService() {
	}
	
	public LocalNewsService(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	
	@Override
	public boolean upsertNews(News news) {
		return newsDao.mergeNews(news);
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
}
