package com.epam.jmp.concurrency.model.service;

import com.epam.jmp.concurrency.model.News;
import com.epam.jmp.concurrency.model.dao.NewsDao;

public interface NewsService {

	boolean upsertNews(News news);

	void setNewsDao(NewsDao dao);

}
