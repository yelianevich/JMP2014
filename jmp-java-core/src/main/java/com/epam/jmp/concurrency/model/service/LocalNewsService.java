package com.epam.jmp.concurrency.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.jmp.concurrency.model.News;
import com.epam.jmp.concurrency.model.dao.NewsDao;

@Service
public class LocalNewsService implements NewsService {

	@Autowired
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
