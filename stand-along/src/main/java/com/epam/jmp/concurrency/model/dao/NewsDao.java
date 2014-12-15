package com.epam.jmp.concurrency.model.dao;

import java.util.List;

import com.epam.jmp.concurrency.model.News;

public interface NewsDao {

	News getNewsById(int id);

	List<News> getNews();

	boolean mergeNews(News news);

}
