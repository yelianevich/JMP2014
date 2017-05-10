package com.epam.jmp.concurrency.service;

import com.epam.jmp.concurrency.dao.NewsDao;
import com.epam.jmp.concurrency.model.News;

public interface NewsService {

    boolean upsertNews(News news);

    void setNewsDao(NewsDao dao);

}
