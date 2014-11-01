package com.epam.jmp.concurrency.model.service;

import com.epam.jmp.concurrency.model.News;

public interface NewsService {

	boolean upsertNews(News news);

}
